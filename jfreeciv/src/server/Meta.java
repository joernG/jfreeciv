package server;

import common.Game;

public class Meta{
	public static final boolean DEFAULT_META_SERVER_NO_SEND = true;
	public static final String DEFAULT_META_SERVER_ADDR = "http://meta.freeciv.org/metaserver.phtml";
	public static final int METASERVER_REFRESH_INTERVAL =  (3*60);
	public static final int METASERVER_MIN_UPDATE_INTERVAL = 7;

//#include "capstr.h"
//#include "connection.h"
//#include "dataio.h"
//#include "fcintl.h"
//#include "log.h"
//#include "mem.h"
//#include "netintf.h"
//#include "support.h"
//#include "timing.h"
//#include "version.h"
//
//#include "console.h"
//#include "srv_main.h"
//
//#include "meta.h"
//
//static boolean server_is_open = false;
//
//static union my_sockaddr meta_addr;
//static char  metaname[MAX_LEN_ADDR];
//static int   metaport;
//static char *metaserver_path;
//
//static char meta_patches[256] = "";
//static char meta_topic[256] = "";
//static char meta_message[256] = "";
//
///*************************************************************************
// the default metaserver patches for this server
//*************************************************************************/
//final String default_meta_patches_string()
//{
//  return "none";
//}
//
///*************************************************************************
// the default metaserver topic
//*************************************************************************/
//final String default_meta_topic_string()
//{
//  return "";
//}
//
///*************************************************************************
//  Return static string with default info line to send to metaserver.
//  This is a function (instead of a define) to keep meta.h clean of
//  including config.h and version.h
//*************************************************************************/
//final String default_meta_message_string()
//{
//#if IS_BETA_VERSION
//  return "unstable pre-" NEXT_STABLE_VERSION ": beware";
//#else
//#if IS_DEVEL_VERSION
//  return "development version: beware";
//#else
//  return "(default)";
//#endif
//#endif
//}
//
///*************************************************************************
// the metaserver patches
//*************************************************************************/
//final String get_meta_patches_string()
//{
//  return meta_patches;
//}
//
///*************************************************************************
// the metaserver topic
//*************************************************************************/
//final String get_meta_topic_string()
//{
//  return meta_topic;
//}
//
///*************************************************************************
// the metaserver message
//*************************************************************************/
//final String get_meta_message_string()
//{
//  return meta_message;
//}
//
///*************************************************************************
// set the metaserver patches string
//*************************************************************************/
//void set_meta_patches_string(final String string)
//{
//  meta_patches = string;
//}
//
///*************************************************************************
// set the metaserver topic string
//*************************************************************************/
//void set_meta_topic_string(final String string)
//{
//  meta_topic = string;
//}
//
///*************************************************************************
// set the metaserver message string
//*************************************************************************/
//void set_meta_message_string(final String string)
//{
//  meta_message = string;
//}
//
///*************************************************************************
//...
//*************************************************************************/
//char *meta_addr_port()
//{
//  return Srv_main.srvarg.metaserver_addr;
//}
//
///*************************************************************************
// we couldn't find or connect to the metaserver.
//*************************************************************************/
//static void metaserver_failed()
//{
//  con_puts(C_METAERROR, "Not reporting to the metaserver in this Game.game.");
//  con_flush();
//
//  server_close_meta();
//}
//
///*************************************************************************
// finalruct the POST message and send info to metaserver.
//*************************************************************************/
//static boolean send_to_metaserver(enum meta_flag flag)
//{
//  static char msg[8192];
//  static char str[8192];
//  int rest = sizeof(str);
//  int n = 0;
//  char *s = str;
//  char host[512];
//  char state[20];
//  int sock;
//
//  if (!server_is_open) {
//    return false;
//  }
//
//  if ((sock = socket(AF_INET, SOCK_STREAM, 0)) == -1) {
//    util.freelog(Log.LOG_ERROR, "Metaserver: can't open stream socket: %s",
//	    mystrerror());
//    metaserver_failed();
//    return false;
//  }
//
//  if (connect(sock, (sockaddr ) &meta_addr, sizeof(meta_addr)) == -1) {
//    util.freelog(Log.LOG_ERROR, "Metaserver: connect failed: %s", mystrerror());
//    metaserver_failed();
//    my_closesocket(sock);
//    return false;
//  }
//
//  switch(Srv_main.server_state) {
//  case server_states.PRE_GAME_STATE:
//    state = String.format( "Pregame");
//    break;
//  case SELECT_RACES_STATE:
//    state = String.format( "Nation Select");
//    break;
//  case server_states.RUN_GAME_STATE:
//    state = String.format( "Running");
//    break;
//  case server_states.GAME_OVER_STATE:
//    state = String.format( "Game Ended");
//    break;
//  default:
//    state = String.format( "Unknown");
//    break;
//  }
//
//  /* get hostname */
//  if (my_gethostname(host, sizeof(host)) != 0) {
//    host = String.format( "unknown");
//  }
//
//  s = String.format "host=%s&port=%d&state=%s&", host, Srv_main.srvarg.port, state);
//  s = end_of_strn(s, &rest);
//
//  if (flag == META_GOODBYE) {
//    mystrlcpy(s, "bye=1&", rest);
//    s = end_of_strn(s, &rest);
//  } else {
//    s = String.format "version=%s&", my_url_encode(VERSION_STRING));
//    s = end_of_strn(s, &rest);
//
//    s = String.format "patches=%s&", 
//                my_url_encode(get_meta_patches_string()));
//    s = end_of_strn(s, &rest);
//
//    s = String.format "capability=%s&", my_url_encode(our_capability));
//    s = end_of_strn(s, &rest);
//
//    s = String.format "topic=%s&",
//                my_url_encode(get_meta_topic_string()));
//    s = end_of_strn(s, &rest);
//
//    s = String.format "serverid=%s&",
//                my_url_encode(Srv_main.srvarg.serverid));
//    s = end_of_strn(s, &rest);
//
//    s = String.format "message=%s&",
//                my_url_encode(get_meta_message_string()));
//    s = end_of_strn(s, &rest);
//
//    /* NOTE: send info for ALL players or none at all. */
//    if (Game.get_num_human_and_ai_players() == 0) {
//      mystrlcpy(s, "dropplrs=1&", rest);
//      s = end_of_strn(s, &rest);
//    } else {
//      n = 0; /* a counter for players_available */
//
//      for(player plr: Game.game.players){
//        boolean is_player_available = true;
//        char type[15];
//        connection pconn = Connection.find_conn_by_user(plr.username);
//
//        if (!plr.is_alive) {
//          type = String.format( "Dead");
//        } else if (is_barbarian(plr)) {
//          type = String.format( "Barbarian");
//        } else if (plr.ai.control) {
//          type = String.format( "A.I.");
//        } else {
//          type = String.format( "Human");
//        }
//
//        s = String.format "plu[]=%s&", my_url_encode(plr.username));
//        s = end_of_strn(s, &rest);
//
//        s = String.format "plt[]=%s&", type);
//        s = end_of_strn(s, &rest);
//
//        s = String.format "pll[]=%s&", my_url_encode(plr.name));
//        s = end_of_strn(s, &rest);
//
//        s = String.format "pln[]=%s&",
//                    my_url_encode(plr.nation != Nation_H.NO_NATION_SELECTED 
//                                  ? Nation.get_nation_name_plural(plr.nation)
//                                  : "none"));
//        s = end_of_strn(s, &rest);
//
//        s = String.format "plh[]=%s&",
//                    pconn ? my_url_encode(pconn.addr) : "");
//        s = end_of_strn(s, &rest);
//
//        /* is this player available to take?
//         * TODO: there's some duplication here with 
//         * stdinhand.c:is_allowed_to_take() */
//        if (is_barbarian(plr) && !strchr(Game.game.allow_take, 'b')) {
//          is_player_available = false;
//        } else if (!plr.is_alive && !strchr(Game.game.allow_take, 'd')) {
//          is_player_available = false;
//        } else if (plr.ai.control
//            && !strchr(Game.game.allow_take, (Game.game.is_new_game ? 'A' : 'a'))) {
//          is_player_available = false;
//        } else if (!plr.ai.control
//            && !strchr(Game.game.allow_take, (Game.game.is_new_game ? 'H' : 'h'))) {
//          is_player_available = false;
//        }
//
//        if (pconn) {
//          is_player_available = false;
//        }
//
//        if (is_player_available) {
//          n++;
//        }
//      }
//
//      /* send the number of available players. */
//      s = String.format "available=%d&", n);
//      s = end_of_strn(s, &rest);
//    }
//
//    /* send some variables: should be listed in inverted order
//     * FIXME: these should be input from the settings array */
//    s = String.format "vn[]=%s&vv[]=%d&",
//                my_url_encode("timeout"), Game.game.timeout);
//    s = end_of_strn(s, &rest);
//
//    s = String.format "vn[]=%s&vv[]=%d&",
//                my_url_encode("year"), Game.game.year);
//    s = end_of_strn(s, &rest);
//
//    s = String.format "vn[]=%s&vv[]=%d&",
//                my_url_encode("turn"), Game.game.turn);
//    s = end_of_strn(s, &rest);
//
//    s = String.format "vn[]=%s&vv[]=%d&",
//                my_url_encode("endyear"), Game.game.end_year);
//    s = end_of_strn(s, &rest);
//
//    s = String.format "vn[]=%s&vv[]=%d&",
//                my_url_encode("minplayers"), Game.game.min_players);
//    s = end_of_strn(s, &rest);
//
//    s = String.format "vn[]=%s&vv[]=%d&",
//                my_url_encode("maxplayers"), Game.game.max_players);
//    s = end_of_strn(s, &rest);
//
//    s = String.format "vn[]=%s&vv[]=%s&",
//                my_url_encode("allowtake"), Game.game.allow_take);
//    s = end_of_strn(s, &rest);
//
//    s = String.format "vn[]=%s&vv[]=%d&",
//                my_url_encode("generator"), Map.map.generator);
//    s = end_of_strn(s, &rest);
//
//    s = String.format "vn[]=%s&vv[]=%d&",
//                my_url_encode("size"), Map.map.size);
//    s = end_of_strn(s, &rest);
//  }
//
//  n = msg = util.my_snprintf(
//    "POST %s HTTP/1.1\r\n" +
//    "Host: %s:%d\r\n" +
//    "Content-Type: application/x-www-form-urlencoded; charset=\"utf-8\"\r\n" +
//    "Content-Length: %lu\r\n" +
//    "\r\n" +
//    "%s\r\n",
//    metaserver_path,
//    metaname,
//    metaport,
//    (unsigned long) (sizeof(str) - rest + 1),
//    str
//  );
//
//  my_writesocket(sock, msg, n);
//
//  my_closesocket(sock);
//
//  return true;
//}
//
///*************************************************************************
// 
//*************************************************************************/
//void server_close_meta()
//{
//  server_is_open = false;
//}
//
///*************************************************************************
// lookup the correct address for the metaserver.
//*************************************************************************/
//void server_open_meta()
//{
//  final String path;
// 
//  if (metaserver_path) {
//    free(metaserver_path);
//    metaserver_path = null;
//  }
//  
//  if (!(path = my_lookup_httpd(metaname, &metaport, Srv_main.srvarg.metaserver_addr))) {
//    return;
//  }
//  
//  metaserver_path = (path);
//
//  if (!net_lookup_service(metaname, metaport, &meta_addr)) {
//    util.freelog(Log.LOG_ERROR, "Metaserver: bad address: [%s:%d].",
//            metaname, metaport);
//    metaserver_failed();
//    return;
//  }
//
//  if (meta_patches[0] == '\0') {
//    set_meta_patches_string(default_meta_patches_string());
//  }
//  if (meta_topic[0] == '\0') {
//    set_meta_topic_string(default_meta_topic_string());
//  }
//  if (meta_message[0] == '\0') {
//    set_meta_message_string(default_meta_message_string());
//  }
//
//  server_is_open = true;
//}
//
///**************************************************************************
// are we sending info to the metaserver?
//**************************************************************************/
//boolean is_metaserver_open()
//{
//  return server_is_open;
//}
//
///**************************************************************************
// control when we send info to the metaserver.
//**************************************************************************/
//boolean send_server_info_to_metaserver(enum meta_flag flag)
//{
//  static timer last_send_timer = null;
//  static boolean want_update;
//
//  /* if we're bidding farewell, ignore all timers */
//  if (flag == META_GOODBYE) { 
//    if (last_send_timer) {
//      free_timer(last_send_timer);
//      last_send_timer = null;
//    }
//    return send_to_metaserver(flag);
//  }
//
//  /* don't allow the user to spam the metaserver with updates */
//  if (last_send_timer && (read_timer_seconds(last_send_timer)
//                                          < METASERVER_MIN_UPDATE_INTERVAL)) {
//    if (flag == META_INFO) {
//      want_update = true; /* we couldn't update now, but update a.s.a.p. */
//    }
//    return false;
//  }
//
//  /* if we're asking for a refresh, only do so if 
//   * we've exceeded the refresh interval */
//  if ((flag == META_REFRESH) && !want_update && last_send_timer 
//      && (read_timer_seconds(last_send_timer) < METASERVER_REFRESH_INTERVAL)) {
//    return false;
//  }
//
//  /* start a new timer if we haven't already */
//  if (!last_send_timer) {
//    last_send_timer = new_timer(TIMER_USER, TIMER_ACTIVE);
//  }
//
//  clear_timer_start(last_send_timer);
//  want_update = false;
//  return send_to_metaserver(flag);
//}
}