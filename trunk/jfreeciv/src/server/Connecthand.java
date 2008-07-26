package server;

import utility.Speclists;
import common.Connection;
import common.player.player;

public class Connecthand{
//#include "capability.h"
//#include "capstr.h"
//#include "events.h"
//#include "fcintl.h"
//#include "log.h"
//#include "mem.h"
//#include "packets.h"
//#include "player.h"
//#include "support.h"
//#include "version.h"
//
//#include "auth.h"
//#include "diplhand.h"
//#include "gamehand.h"
//#include "Gamelog.gamelog.h"
//#include "maphand.h"
//#include "meta.h"
//#include "plrhand.h"
//#include "ruleset.h"
//#include "sernet.h"
//#include "srv_main.h"
//#include "stdinhand.h"
//
//#include "connecthand.h"

	/***************************************************************************
	 * This is used when a new player joins a server, before the Game.game has
	 * started. If pconn is null, is an AI, else a client.
	 * 
	 * N.B. this only attachs a Connection to a player if pconn.name ==
	 * player.username
	 **************************************************************************/
	public static void establish_new_connection(Connection pconn)
	{
		Speclists<Connection> dest = pconn.self;
  player pplayer;
//  struct packet_server_join_reply packet;
//  char hostname[512];
//
//  /* zero out the password */
//  memset(pconn.server.password, 0, sizeof(pconn.server.password));
//
//  /* send off login_replay packet */
//  packet.you_can_join = true;
//  packet.capability = our_capability;
//  packet.message = util.my_snprintf( "%s Welcome",
//              pconn.username);
//  packet.challenge_file = String.format( new_challenge_filename(pconn));
//  packet.conn_id = pconn.id;
//  send_packet_server_join_reply(pconn, &packet);
//
//  /* "establish" the Connection */
//  pconn.established = true;
//  pconn.server.status = AS_ESTABLISHED;
//
//  /* introduce the server to the Connection */
//  if (my_gethostname(hostname, sizeof(hostname)) == 0) {
//    Plrhand.notify_conn(dest, "Welcome to the %s Server running at %s port %d.",
//                freeciv_name_version(), hostname, Srv_main.srvarg.port);
//  } else {
//    Plrhand.notify_conn(dest, "Welcome to the %s Server at port %d.",
//                freeciv_name_version(), Srv_main.srvarg.port);
//  }
//
//  /* FIXME: this (getting messages about others logging on) should be a 
//   * message option for the client with event */
//
//  /* notify the console and other established connections that you're here */
//  util.freelog(Log.LOG_NORMAL, "%s has connected from %s.",
//          pconn.username, pconn.addr);
//  for (conn aconn : Game.game.est_connections.data) {
//    if (aconn != pconn) {
//      Plrhand.notify_conn(&aconn.self, "Server: %s has connected from %s.",
//                  pconn.username, pconn.addr);
//    }
//  } }
//
//  /* a player has already been created for this user, reconnect him */
//  if ((pplayer = find_player_by_user(pconn.username))) {
//    attach_connection_to_player(pconn, pplayer);
//
//    if (Srv_main.server_state == server_states.RUN_GAME_STATE) {
//      /* Player and other info is only updated when the Game.game is running.
//       * See the comment in lost_connection_to_client(). */
//      send_packet_freeze_hint(pconn);
//      send_rulesets(dest);
//      send_all_info(dest);
//      send_game_state(dest, CLIENT_GAME_RUNNING_STATE);
//      Plrhand.send_player_info(null,null);
//      send_diplomatic_meetings(pconn);
//      send_packet_thaw_hint(pconn);
//      send_packet_start_turn(pconn);
//    }
//
//    /* This must be done after the above info is sent, because it will
//     * generate a player-packet which can't be sent until (at least)
//     * rulesets are sent. */
//    if (Game.game.auto_ai_toggle && pplayer.ai.control) {
//      toggle_ai_player_direct(null, pplayer);
//    }
//
//    Gamelog.gamelog(EGamelog.GAMELOG_PLAYER, pplayer);
//
//  } else if (Srv_main.server_state == server_states.PRE_GAME_STATE && Game.game.is_new_game) {
//    if (!attach_connection_to_player(pconn, null)) {
//      Plrhand.notify_conn(dest, "Couldn't attach your Connection to new player.");
//      util.freelog(Log.LOG_VERBOSE, "%s is not attached to a player", pconn.username);
//    } else {
//      pconn.player.name = pconn.username;
//    }
//  }
//
//  /* remind the Connection who he is */
//  if (!pconn.player) {
//    Plrhand.notify_conn(dest, "You are logged in as '%s' connected to no player.",
//                pconn.username);
//  } else if (pconn.player.name.equals(ANON_PLAYER_NAME)) {
//    Plrhand.notify_conn(dest, ("You are logged in as '%s' connected to an " +
//                        "anonymous player."),
//		pconn.username);
//  } else {
//    Plrhand.notify_conn(dest, "You are logged in as '%s' connected to %s.",
//                pconn.username, pconn.player.name);
//  }
//
//  /* if need be, tell who we're waiting on to end the Game.game turn */
//  if (Srv_main.server_state == server_states.RUN_GAME_STATE && Game.game.turnblock) {
//    for(player cplayer: Game.game.players){
//      if (cplayer.is_alive
//          && !cplayer.ai.control
//          && !cplayer.turn_done
//          && cplayer != pconn.player) {  /* skip current player */
//        Plrhand.notify_conn(dest, ("Turn-blocking Game.game play: " +
//                            "waiting on %s to finish turn..."),
//                    cplayer.name);
//      }
//    }
//  }
//
//  /* if the Game.game is running, players can just view the Players menu? --dwp */
//  if (Srv_main.server_state != server_states.RUN_GAME_STATE) {
//    show_players(pconn);
//  }
//
//  send_conn_info(dest, &Game.game.est_connections);
//  conn_list_insert_back(&Game.game.est_connections, pconn);
//  send_conn_info(&Game.game.est_connections, dest);
//  () send_server_info_to_metaserver(META_INFO);
	}

/**************************************************************************
  send the rejection packet to the client.
**************************************************************************/
public static void reject_new_connection(final String msg, Connection pconn)
{
//  struct packet_server_join_reply packet;
//
//  /* zero out the password */
//  memset(pconn.server.password, 0, sizeof(pconn.server.password));
//
//  packet.you_can_join = false;
//  packet.capability = our_capability;
//  packet.message = msg;
//  packet.challenge_file[0] = '\0';
//  packet.conn_id = -1;
//  send_packet_server_join_reply(pconn, &packet);
//  util.freelog(Log.LOG_NORMAL, "Client rejected: %s.", pconn.conn_description());
//  flush_connection_send_buffer_all(pconn);
}

///**************************************************************************
// Returns false if the clients gets rejected and the Connection should be
// closed. Returns true if the client get accepted.
//**************************************************************************/
//boolean handle_login_request(Connection pconn, 
//                          packet_server_join_req req)
//{
//  char msg[MAX_LEN_MSG];
//  
//  util.freelog(Log.LOG_NORMAL, "Connection request from %s from %s",
//          req.username, pconn.addr);
//  
//  /* print server and client capabilities to console */
//  util.freelog(Log.LOG_NORMAL, "%s has client version %d.%d.%d%s",
//          pconn.username, req.major_version, req.minor_version,
//          req.patch_version, req.version_label);
//  util.freelog(Log.LOG_VERBOSE, "Client caps: %s", req.capability);
//  util.freelog(Log.LOG_VERBOSE, "Server caps: %s", our_capability);
//  pconn.capability = req.capability;
//  
//  /* Make sure the server has every capability the client needs */
//  if (!has_capabilities(our_capability, req.capability)) {
//    msg = util.my_snprintf(
//                ("The client is missing a capability that this server needs.\n" +
//                   "Server version: %d.%d.%d%s Client version: %d.%d.%d%s." +
//                   "  Upgrading may help!"),
//                MAJOR_VERSION, MINOR_VERSION, PATCH_VERSION, VERSION_LABEL,
//                req.major_version, req.minor_version,
//                req.patch_version, req.version_label);
//    reject_new_connection(msg, pconn);
//    util.freelog(Log.LOG_NORMAL, "%s was rejected: Mismatched capabilities.",
//            req.username);
//    return false;
//  }
//
//  /* Make sure the client has every capability the server needs */
//  if (!has_capabilities(req.capability, our_capability)) {
//    msg = util.my_snprintf(
//                ("The server is missing a capability that the client needs.\n" +
//                   "Server version: %d.%d.%d%s Client version: %d.%d.%d%s." +
//                   "  Upgrading may help!"),
//                MAJOR_VERSION, MINOR_VERSION, PATCH_VERSION, VERSION_LABEL,
//                req.major_version, req.minor_version,
//                req.patch_version, req.version_label);
//    reject_new_connection(msg, pconn);
//    util.freelog(Log.LOG_NORMAL, "%s was rejected: Mismatched capabilities.",
//            req.username);
//    return false;
//  }
//
//  remove_leading_trailing_spaces(req.username);
//
//  /* Name-sanity check: could add more checks? */
//  if (!is_valid_username(req.username)) {
//    msg = util.my_snprintf( "Invalid username '%s'", req.username);
//    reject_new_connection(msg, pconn);
//    util.freelog(Log.LOG_NORMAL, "%s was rejected: Invalid name [%s].",
//            req.username, pconn.addr);
//    return false;
//  } 
//
//  /* don't allow duplicate logins */
//  for (conn aconn : Game.game.all_connections.data) {
//    if (mystrcasecmp(req.username, aconn.username) == 0) { 
//      msg = util.my_snprintf( "'%s' already connected.", 
//                  req.username);
//      reject_new_connection(msg, pconn);
//      util.freelog(Log.LOG_NORMAL, "%s was rejected: Duplicate login name [%s].",
//              req.username, pconn.addr);
//      return false;
//    }
//  } }
//
//  if (Srv_main.srvarg.auth_enabled) {
//    return authenticate_user(pconn, req.username);
//  } else {
//    pconn.username = req.username;
//    establish_new_connection(pconn);
//    return true;
//  }
//}
//
///**************************************************************************
//  High-level server stuff when Connection to client is closed or lost.
//  Reports loss to log, and to other players if the Connection was a
//  player.  Also removes player if in pregame, applies auto_toggle, and
//  does check for turn done (since can depend on Connection/ai status).
//  Note caller should also call close_connection() after this, to do
//  lower-level close stuff.
//**************************************************************************/
//void lost_connection_to_client(Connection pconn)
//{
//  player pplayer = pconn.player;
//  final String desc = pconn.conn_description();
//
//  util.freelog(Log.LOG_NORMAL, "Lost Connection: %s.", desc);
//  
//  /* _Must_ avoid sending to pconn, in case pconn Connection is
//   * really lost (as opposed to server shutting it down) which would
//   * trigger an error on send and recurse back to here.
//   * Safe to unlink even if not in list: */
//  conn_list_unlink(&Game.game.est_connections, pconn);
//  delayed_disconnect++;
//  Plrhand.notify_conn(&Game.game.est_connections, "Game: Lost Connection: %s.", desc);
//
//  if (!pplayer) {
//    delayed_disconnect--;
//    return;
//  }
//
//  unattach_connection_from_player(pconn);
//
//  send_conn_info_remove(&pconn.self, &Game.game.est_connections);
//  if (Srv_main.server_state == server_states.RUN_GAME_STATE) {
//    /* Player info is only updated when the Game.game is running; this must be
//     * done consistently or the client will end up with inconsistent errors.
//     * At other times, the conn info (send_conn_info) is used by the client
//     * to display player information.  See establish_new_connection(). */
//    Plrhand.send_player_info(pplayer, null);
//  }
//  notify_if_first_access_level_is_available();
//
//  /* Cancel diplomacy meetings */
//  if (!pplayer.is_connected) { /* may be still true if multiple connections */
//    for(player other_player: Game.game.players){
//      if (find_treaty(pplayer, other_player)) {
//        handle_diplomacy_cancel_meeting_req(pplayer, other_player.player_no);
//      }
//    }
//  }
//
//  if (Game.game.is_new_game
//      && !pplayer.is_connected /* eg multiple controllers */
//      && !pplayer.ai.control    /* eg created AI player */
//      && (Srv_main.server_state == server_states.PRE_GAME_STATE 
//          || Srv_main.server_state == SELECT_RACES_STATE)) {
//    server_remove_player(pplayer);
//  } else {
//    if (Game.game.auto_ai_toggle
//        && !pplayer.ai.control
//        && !pplayer.is_connected /* eg multiple controllers */) {
//      toggle_ai_player_direct(null, pplayer);
//    }
//
//    Gamelog.gamelog(EGamelog.GAMELOG_PLAYER, pplayer);
//
//    check_for_full_turn_done();
//  }
//
//  delayed_disconnect--;
//}
//
///**************************************************************************
//  Fill in packet_conn_info from full Connection struct.
//**************************************************************************/
//static void package_conn_info(Connection pconn,
//                              packet_conn_info packet)
//{
//  packet.id           = pconn.id;
//  packet.used         = pconn.used;
//  packet.established  = pconn.established;
//  packet.player_num   = pconn.player ? pconn.player.player_no : -1;
//  packet.observer     = pconn.observer;
//  packet.access_level = pconn.access_level;
//
//  packet.username = pconn.username;
//  packet.addr = pconn.addr;
//  packet.capability = pconn.capability;
//}
//
///**************************************************************************
//  Handle both send_conn_info() and send_conn_info_removed(), depending
//  on 'remove' arg.  Sends conn_info packets for 'src' to 'dest', turning
//  off 'used' if 'remove' is specified.
//**************************************************************************/
//static void send_conn_info_arg(Speclists<Connection> src,
//                               Speclists<Connection> dest, boolean remove)
//{
//  struct packet_conn_info packet;
//  
//  conn_list_iterate(*src, psrc) {
//    package_conn_info(psrc, &packet);
//    if (remove) {
//      packet.used = false;
//    }
//    lsend_packet_conn_info(dest, &packet);
//  }
//  }
//}
//
///**************************************************************************
//  Send conn_info packets to tell 'dest' connections all about
//  'src' connections.
//**************************************************************************/
//void send_conn_info(Speclists<Connection> src, Speclists<Connection> dest)
//{
//  send_conn_info_arg(src, dest, false);
//}
//
///**************************************************************************
//  Like send_conn_info(), but turn off the 'used' bits to tell clients
//  to remove info about these connections instead of adding it.
//**************************************************************************/
//void send_conn_info_remove(Speclists<Connection> src, Speclists<Connection> dest)
//{
//  send_conn_info_arg(src, dest, true);
//}
//
///**************************************************************************
//  Setup pconn as a client connected to pplayer:
//  Updates pconn.player, pplayer.connections, pplayer.is_connected.
//
//  If pplayer is null, take the next available player that is not already 
//  associated.
//  Note "observer" connections do not count for is_connected. You must set
//       pconn.obserber to true before attaching!
//**************************************************************************/
//boolean attach_connection_to_player(Connection pconn,
//                                 player pplayer)
//{
//  /* if pplayer is null, attach to first non-connected player slot */
//  if (!pplayer) {
//    if (Game.game.nplayers > Game.game.max_players 
//        || Game.game.nplayers > Shared_H.MAX_NUM_PLAYERS + Shared_H.MAX_NUM_BARBARIANS) {
//      return false; 
//    } else {
//      pplayer = &Game.game.players[Game.game.nplayers];
//      Game.game.nplayers++;
//    }
//  }
//
//  if (!pconn.observer) {
//    pplayer.username = pconn.username;
//    pplayer.is_connected = true;
//  }
//
//  pconn.player = pplayer;
//  conn_list_insert_back(&pplayer.connections, pconn);
//  conn_list_insert_back(&Game.game.game_connections, pconn);
//
//  return true;
//}
//  
///**************************************************************************
//  Remove pconn as a client connected to pplayer:
//  Update pplayer.connections, pplayer.is_connected.
//
//  pconn remains a member of Game.game.est_connections.
//**************************************************************************/
//boolean unattach_connection_from_player(Connection pconn)
//{
//  if (!pconn.player) {
//    return false; /* no player is attached to this conn */
//  }
//
//  conn_list_unlink(&pconn.player.connections, pconn);
//  conn_list_unlink(&Game.game.game_connections, pconn);
//
//  pconn.player.is_connected = false;
//  pconn.observer = false;
//
//  /* If any other (non-observing) conn is attached to 
//   * this player, the player is still connected. */
//  for (conn aconn : pconn.player.connections.data) {
//    if (!aconn.observer) {
//      pconn.player.is_connected = true;
//      break;
//    }
//  } }
//
//  pconn.player = null;
//
//  return true;
//}
}