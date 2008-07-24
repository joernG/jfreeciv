package server;
import static common.Connection.find_conn_by_user_prefix;
import static common.Player_P.find_player_by_name_prefix;
import static common.Player_P.pplayers_allied;
import static common.player.Player_H.ANON_PLAYER_NAME;
import static port.util.my_snprintf;
import static server.Stdinhand.SERVER_COMMAND_PREFIX;
import utility.Speclists;
import utility.shared.m_pre_result;

import common.Connection;
import common.Game;
import common.event_type;
import common.player.player;

public class Handchat{
	/*
	 * for form_chat_name() names
	 */
//	#define MAX_LEN_CHAT_NAME (2*MAX_LEN_NAME+10)   

	private static final String ALLIESCHAT_COMMAND_PREFIX = ".";

	/***************************************************************************
	 * Formulate a name for this Connection, prefering the player name when
	 * available and unambiguous (since this is the "standard" case), else use
	 * the username.
	 **************************************************************************/
	static String form_chat_name(Connection pconn, String buffer, int len)
	{
		player pplayer = pconn.player;

		if (null==pplayer
				|| pconn.observer
				|| pplayer.name.equals(ANON_PLAYER_NAME)) {
			buffer = my_snprintf("(%s)", pconn.username);
		} else {
			buffer = my_snprintf("%s", pplayer.name);
		}
		return buffer;
	}

	/***************************************************************************
	 * Complain to sender that name was ambiguous. 'player_conn' is 0 for player
	 * names, 1 for Connection names, 2 for attempt to send to an anonymous
	 * player.
	 **************************************************************************/
	static void complain_ambiguous(Connection pconn, final String name,
			int player_conn)
	{
//		char message[MAX_LEN_MSG];
		String message = "";

		switch(player_conn) {
		case 0:
			message = my_snprintf(
					"Game: %s is an ambiguous player name-prefix.", name);
			break;
		case 1:
			message = my_snprintf(
					"Game: %s is an ambiguous connection name-prefix.", name);
			break;
		case 2:
			message = my_snprintf(
					"Game: %s is an anonymous name. Use connection name", name);
			break;
		default:
			assert(0==1);
		}
		dsend_packet_chat_msg(pconn, message, -1, -1, event_type.E_NOEVENT, -1);
	}

	/***************************************************************************
	 * Send private message to single connection.
	 **************************************************************************/
	static void chat_msg_to_conn(Connection sender,	Connection dest, String msg)
	{
//		char sender_name[MAX_LEN_CHAT_NAME], dest_name[MAX_LEN_CHAT_NAME];
//		char message[MAX_LEN_MSG];
		String sender_name = null, dest_name = null, message;

		msg = (msg.trim());

		form_chat_name(sender, sender_name, 0);
		form_chat_name(dest, dest_name, 0);

		message = my_snprintf(".*%s* %s", dest_name, msg);
		dsend_packet_chat_msg(sender, message, -1, -1, event_type.E_NOEVENT, sender.id);

		if (sender != dest) {
			message = my_snprintf("*%s* %s", sender_name, msg);
			dsend_packet_chat_msg(dest, message, -1, -1, event_type.E_NOEVENT, sender.id);
		}
	}

	/***************************************************************************
	 * Send private message to multi-connected player.
	 **************************************************************************/
	static void chat_msg_to_player_multi(Connection sender,
			player pdest, String msg)
	{
//		char sender_name[MAX_LEN_CHAT_NAME], message[MAX_LEN_MSG];
		String sender_name = null, message;

		msg = (msg.trim());

		form_chat_name(sender, sender_name, 0);

		message = my_snprintf(".[%s] %s", pdest.name, msg);
		dsend_packet_chat_msg(sender, message, -1, -1, event_type.E_NOEVENT, sender.id);

		message = my_snprintf("[%s] %s", sender_name, msg);
		for (Connection dest_conn : pdest.connections.data) {
			if (dest_conn != sender) {
				dsend_packet_chat_msg(dest_conn, message,
						-1, -1, event_type.E_NOEVENT, sender.id);
			}
		} 
	}

	/*******************************************************************************
	 * Handle a chat message packet from client: 1. Work out whether it is a server
	 * command and if so run it; 2. Otherwise work out whether it is directed to a
	 * single player, or to a single Connection, and send there. (For a player, send
	 * to all clients connected as that player, in multi-connect case); 3. Or it may
	 * be intended for all allied players. 4. Else send to all connections
	 * (game.game_connections).
	 * 
	 * In case 2, there can sometimes be ambiguity between player and Connection
	 * names. By default this tries to match player name first, and only if that
	 * fails tries to match Connection name. User can override this and specify
	 * Connection only by using two colons ("::") after the destination name/prefix,
	 * instead of one.
	 * 
	 * The message sent will name the sender, and via format differences also
	 * indicates whether the recipient is either all connections, a single
	 * Connection, or multiple connections to a single player.
	 * 
	 * Message is also echoed back to sender (with different format), avoiding
	 * sending both original and echo if sender is in destination set.
	 ******************************************************************************/
	void handle_chat_msg_req(Connection pconn, String message)
	{
//		char sender_name[MAX_LEN_CHAT_NAME], chat[MAX_LEN_MSG];
		String sender_name = "", chat;
		int cp;
		boolean double_colon;

		/*
		 * this loop to prevent players from sending multiple lines which can be
		 * abused
		 */
//		for (cp = message; *cp != '\0'; cp++) {
//		if (*cp == '\n' || *cp == '\r') {
//		*cp='\0';
//		break;
//		}
//		}
		int endIndex1 = message.indexOf('\n');
		int endIndex2 = message.indexOf('\r');
		message = message.substring(0, endIndex1);

		/*
		 * Server commands are prefixed with '/', which is an obvious but
		 * confusing choice: even before this feature existed, novice players
		 * were trying /who, /nick etc. So consider this an incentive for IRC
		 * support, or change it in stdinhand.h - rp
		 */
		if (message.startsWith(SERVER_COMMAND_PREFIX)) {
			/* pass it to the command parser, which will chop the prefix off */
//			() handle_stdin_input(pconn, message, false); //TODO
			return; 
		}

		/* Send to allies command */
		if (message.startsWith(ALLIESCHAT_COMMAND_PREFIX)) {
//			char sender_name[MAX_LEN_CHAT_NAME];

			/* this won't work if we aren't attached to a player */
			if (null==pconn.player) {
				chat = my_snprintf(
				"Game: You are not attached to a player.");
				dsend_packet_chat_msg(pconn, chat, -1, -1, event_type.E_NOEVENT, -1);
				return;
			}

			message = ' ' + message.substring(1); /* replace command prefix */
			form_chat_name(pconn, sender_name, 0);
			chat = my_snprintf(
					"%s to allies: %s", sender_name,
					message.trim());
			/*
			 * FIXME: there should be a special case for the sender, like in
			 * chat_msg_to_player_multi().
			 */
			for(player aplayer: Game.game.players){
				if (!pplayers_allied(pconn.player, aplayer)) {
					continue;
				}
				dlsend_packet_chat_msg(aplayer.connections, chat, -1, -1,
						event_type.E_NOEVENT, pconn.id);
			}
			return;
		}

		/*
		 * Want to allow private messages with "player_name: message", (or
		 * "connection_name: message"), including unambiguously abbreviated
		 * player/Connection name, but also want to allow sensible use of ':'
		 * within messages, and _also_ want to notice intended private messages
		 * with (eg) mis-spelt name.
		 * 
		 * Approach:
		 * 
		 * If there is no ':', or ':' is first on line, message is global (send
		 * to all players) else if the ':' is double, try matching part before
		 * "::" against Connection names: for single match send to that
		 * Connection, for multiple matches complain, else goto heuristics
		 * below. else try matching part before (single) ':' against player
		 * names: for single match send to that player, for multiple matches
		 * complain else try matching against Connection names: for single match
		 * send to that Connection, for multiple matches complain else if some
		 * heuristics apply (a space anywhere before first ':') then treat as
		 * global message, else complain (might be a typo-ed intended private
		 * message)
		 */

		cp=message.indexOf(':');

		if (cp!=-1 && (cp != 0)) {
			m_pre_result match_result_player = null, match_result_conn = null;
			player pdest = null;
			Connection conn_dest = null;
//			char name[MAX_LEN_NAME];
			String name;
			int cpblank;

//			() mystrlcpy(name, message,
//			Math.min(sizeof(name), cp - message + 1));
			name = message.substring(0, cp);

			double_colon = (message.substring(cp+1, cp+1).equals(":"));
			if (double_colon) {
				conn_dest = find_conn_by_user_prefix(name, match_result_conn);
				if (match_result_conn == m_pre_result.M_PRE_AMBIGUOUS) {
					complain_ambiguous(pconn, name, 1);
					return;
				}
				if (conn_dest!=null && match_result_conn.ordinal() < m_pre_result.M_PRE_AMBIGUOUS.ordinal()) {
					chat_msg_to_conn(pconn, conn_dest, message.substring(0, cp+2));
					return;
				}
			} else {
				/* single colon */
				pdest = find_player_by_name_prefix(name, match_result_player);
				if (match_result_player == m_pre_result.M_PRE_AMBIGUOUS) {
					complain_ambiguous(pconn, name, 0);
					return;
				}
				if (pdest!=null && pdest.name.equals(ANON_PLAYER_NAME)) {
					complain_ambiguous(pconn, name, 2);
					return;
				}
				if (pdest!=null && match_result_player.ordinal() < m_pre_result.M_PRE_AMBIGUOUS.ordinal()) {
					int nconn = pdest.connections.foo_list_size();
					if (nconn==1) {
						chat_msg_to_conn(pconn, pdest.connections.foo_list_get(0), message.substring(0, cp+1));
						return;
					} else if (nconn>1) {
						chat_msg_to_player_multi(pconn, pdest, message.substring(0, cp));
						return;
					}
					/* else try for Connection name match before complaining */
				}
				conn_dest = find_conn_by_user_prefix(name, match_result_conn);
				if (match_result_conn == m_pre_result.M_PRE_AMBIGUOUS) {
					complain_ambiguous(pconn, name, 1);
					return;
				}
				if (conn_dest!=null && match_result_conn.ordinal() < m_pre_result.M_PRE_AMBIGUOUS.ordinal()) {
					chat_msg_to_conn(pconn, conn_dest, message.substring(0, cp+1));
					return;
				}
				if (pdest!=null && match_result_player.ordinal() < m_pre_result.M_PRE_AMBIGUOUS.ordinal()) {
					/* Would have done something above if connected */
					chat = my_snprintf(
							"Game: %s is not connected.", pdest.name);
					dsend_packet_chat_msg(pconn, chat, -1, -1, event_type.E_NOEVENT, -1);
					return;
				}
			}
			/*
			 * Didn't match; check heuristics to see if this is likely to be a
			 * global message
			 */
			cpblank=message.indexOf(' ');
			if (double_colon) {
				chat = my_snprintf(
						"Game: There is no Connection by the name %s.", name);
			} else {
				chat = my_snprintf(
						"Game: There is no player nor Connection by the name %s.",
						name);
			}
			dsend_packet_chat_msg(pconn, chat, -1, -1, event_type.E_NOEVENT, -1);
			return;
		}
		/* global message: */
		form_chat_name(pconn, sender_name, 0);
		chat = my_snprintf("<%s> %s", sender_name, message);
		dlsend_packet_chat_msg(Game.game.est_connections, chat,
				-1, -1, event_type.E_NOEVENT, pconn.id);
	}

	private static void dlsend_packet_chat_msg(Speclists<Connection> connections,
			String chat, int i, int j, event_type event, int id) {
		// TODO Auto-generated method stub
		
	}

	private static void dsend_packet_chat_msg(Connection pconn, String chat, int i,
			int j, event_type event, int k) {
		// TODO Auto-generated method stub
		
	}
}