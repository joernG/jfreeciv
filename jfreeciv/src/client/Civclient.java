package client;

public class Civclient{

// Freeciv - Copyright (C) 1996 - A Kjeldberg, L Gregersen, P Unold
//   This program is free software; you can redistribute it and/or modify
//   it under the terms of the GNU General Public License as published by
//   the Free Software Foundation; either version 2, or (at your option)
//   any later version.
//
//   This program is distributed in the hope that it will be useful,
//   but WITHOUT ANY WARRANTY; without even the implied warranty of
//   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//   GNU General Public License for more details.
//***********************************************************************/
//
//#ifdef HAVE_CONFIG_H
//#include <config.h>
//#endif
//
//#ifdef WIN32_NATIVE
//#include <windows.h>	/* LoadLibrary() */
//#endif
//
//#include <assert.h>
//#include <stdio.h>
//#include <stdlib.h>
//#include <time.h>
//
//#include "capstr.h"
//#include "dataio.h"
//#include "diptreaty.h"
//#include "fciconv.h"
//#include "fcintl.h"
//#include "Game.game.h"
//#include "idex.h"
//#include "log.h"
//#include "Map.map.h"
//#include "mem.h"
//#include "netintf.h"
//#include "packets.h"
//#include "rand.h"
//#include "support.h"
//#include "version.h"
//
//#include "agents.h"
//#include "attribute.h"
//#include "audio.h"
//#include "chatline_g.h"
//#include "citydlg_g.h"
//#include "cityrepdata.h"
//#include "climisc.h"
//#include "clinet.h"
//#include "cma_core.h"		/* kludge */
//#include "connectdlg_g.h"
//#include "control.h" 
//#include "dialogs_g.h"
//#include "diplodlg_g.h"
//#include "goto.h"
//#include "gui_main_g.h"
//#include "helpdata.h"		/* boot_help_texts() */
//#include "mapctrl_g.h"
//#include "mapview_g.h"
//#include "menu_g.h"
//#include "messagewin_g.h"
//#include "options.h"
//#include "packhand.h"
//#include "pages_g.h"
//#include "plrdlg_g.h"
//#include "repodlgs_g.h"
//#include "tilespec.h"
//
//#include "civclient.h"
//
///* this is used in strange places, and is 'extern'd where
//   needed (hence, it is not 'extern'd in civclient.h) */
//boolean is_server = false;
//
//char *logfile = null;
//char *scriptfile = null;
//static char tileset_name[512] = "\0";
//char sound_plugin_name[512] = "\0";
//char sound_set_name[512] = "\0";
//char server_host[512] = "\0";
//char user_name[512] = "\0";
//char password[MAX_LEN_PASSWORD] = "\0";
//char metaserver[512] = "\0";
//int  server_port = -1;
//boolean auto_connect = false; /* true = skip "Connect to Freeciv Server" dialog */
//
//static enum client_states client_state = CLIENT_BOOT_STATE;
//
//int seconds_to_turndone;
//
///* true if an end turn request is blocked by busy agents */
//boolean waiting_for_end_turn = false;
//
///* 
// * true for the time between sending PACKET_TURN_DONE and receiving
// * PACKET_NEW_YEAR. 
// */
//boolean turn_done_sent = false;
//
///**************************************************************************
//  Convert a text string from the internal to the data encoding, when it
//  is written to the network.
//**************************************************************************/
//static char *put_conv(final String src, size_t *length)
//{
//  char *out = internal_to_data_string_malloc(src);
//
//  if (out) {
//    *length = out.length();
//    return out;
//  } else {
//    *length = 0;
//    return null;
//  }
//}
//
///**************************************************************************
//  Convert a text string from the data to the internal encoding when it is
//  first read from the network.  Returns false if the destination isn't
//  large enough or the source was bad.
//**************************************************************************/
//static boolean get_conv(char *dst, size_t ndst,
//		     final String src, size_t nsrc)
//{
//  char *out = data_to_internal_string_malloc(src);
//  boolean ret = true;
//  size_t len;
//
//  if (!out) {
//    dst[0] = '\0';
//    return false;
//  }
//
//  len = out.length();
//  if (ndst > 0 && len >= ndst) {
//    ret = false;
//    len = ndst - 1;
//  }
//
//  memcpy(dst, out, len);
//  dst[len] = '\0';
//  free(out);
//
//  return ret;
//}
//
///**************************************************************************
//  Set up charsets for the client.
//**************************************************************************/
//static void charsets_init()
//{
//  dio_set_put_conv_callback(put_conv);
//  dio_set_get_conv_callback(get_conv);
//}
//
///**************************************************************************
//...
//**************************************************************************/
//int main(int argc, char *argv[])
//{
//  int i, loglevel;
//  int ui_options = 0;
//  boolean ui_separator = false;
//  char *option=null;
//
//  /* Load win32 post-crash debugger */
//#ifdef WIN32_NATIVE
//# ifndef NDEBUG
//  if (LoadLibrary("exchndl.dll") == null) {
//#  ifdef DEBUG
//    fprintf(stderr, "exchndl.dll could not be loaded, no crash debugger\n");
//#  endif
//  }
//# endif
//#endif
//
//  init_nls();
//  audio_init();
//
//  /* default argument values are set in options.c */
//  loglevel=Log.LOG_NORMAL;
//
//  i = 1;
//
//  while (i < argc) {
//   if (ui_separator) {
//     argv[1 + ui_options] = argv[i];
//     ui_options++;
//   } else if (is_option("--help", argv[i])) {
//    fc_fprintf(stderr, ("Usage: %s [option ...]\n" +
//		      "Valid options are:\n"), argv[0]);
//    fc_fprintf(stderr, "  -a, --autoconnect\tSkip connect dialog\n");
//#ifdef DEBUG
//    fc_fprintf(stderr, ("  -d, --debug NUM\tSet debug log level (0 to 4," +
//                                  " or 4:file1,min,max:...)\n"));
//#else
//    fc_fprintf(stderr,
//	       "  -d, --debug NUM\tSet debug log level (0 to 3)\n");
//#endif
//    fc_fprintf(stderr,
//	       "  -h, --help\t\tPrint a summary of the options\n");
//    fc_fprintf(stderr, ("  -l, --log FILE\tUse FILE as logfile " +
//                      "(spawned server also uses this)\n"));
//    fc_fprintf(stderr, ("  -m, --meta HOST\t" +
//		      "Connect to the metaserver at HOST\n"));
//    fc_fprintf(stderr, "  -n, --name NAME\tUse NAME as name\n");
//    fc_fprintf(stderr,
//	       "  -p, --port PORT\tConnect to server port PORT\n");
//    fc_fprintf(stderr,
//	       "  -P, --Plugin PLUGIN\tUse PLUGIN for sound output %s\n",
//	    audio_get_all_plugin_names());
//    fc_fprintf(stderr, ("  -r, --read FILE\tRead startup script FILE " +
//                      "(for spawned server only)\n"));
//    fc_fprintf(stderr,
//	       "  -s, --server HOST\tConnect to the server at HOST\n");
//    fc_fprintf(stderr, "  -S, --Sound FILE\tRead sound tags from FILE\n");
//    fc_fprintf(stderr, ("  -t, --tiles FILE\t" +
//		      "Use data file FILE.tilespec for tiles\n"));
//    fc_fprintf(stderr, "  -v, --version\t\tPrint the version number\n");
//    fc_fprintf(stderr, ("      --\t\t" +
//		      "Pass any following options to the UI.\n" +
//		      "\t\t\tTry \"%s -- --help\" for more.\n"), argv[0]);
//    exit(EXIT_SUCCESS);
//   } else if (is_option("--version",argv[i])) {
//    fc_fprintf(stderr, "%s %s\n", freeciv_name_version(), client_string);
//    exit(EXIT_SUCCESS);
//   } else if ((option = get_option("--log",argv,&i,argc))) {
//      logfile = (option); /* never free()d */
//   } else  if ((option = get_option("--read", argv, &i, argc)))
//      scriptfile = (option); /* never free()d */
//   else if ((option = get_option("--name",argv,&i,argc)))
//      user_name = option;
//   else if ((option = get_option("--meta",argv,&i,argc)))
//      metaserver = option;
//   else if ((option = get_option("--Sound", argv, &i, argc)))
//      sound_set_name = option;
//   else if ((option = get_option("--Plugin", argv, &i, argc)))
//      sound_plugin_name = option;
//   else if ((option = get_option("--port",argv,&i,argc))) {
//     if(sscanf(option, "%d", &server_port) != 1) {
//       fc_fprintf(stderr,
//		  ("Invalid port \"%s\" specified with --port option.\n"),
//		  option);
//       fc_fprintf(stderr, "Try using --help.\n");
//        exit(EXIT_FAILURE);
//     }
//   } else if ((option = get_option("--server",argv,&i,argc)))
//      server_host = option;
//   else if (is_option("--autoconnect",argv[i]))
//      auto_connect = true;
//   else if ((option = get_option("--debug",argv,&i,argc))) {
//      loglevel=log_parse_level_str(option);
//      if (loglevel==-1) {
//	fc_fprintf(stderr,
//		   ("Invalid debug level \"%s\" specified with --debug " +
//		     "option.\n"), option);
//	fc_fprintf(stderr, "Try using --help.\n");
//        exit(EXIT_FAILURE);
//      }
//   } else if ((option = get_option("--tiles", argv, &i, argc)))
//      tileset_name = option;
//   else if (is_option("--", argv[i])) {
//     ui_separator = true;
//   } else { 
//      fc_fprintf(stderr, ("Unrecognized option: \"%s\"\n"), argv[i]);
//      exit(EXIT_FAILURE);
//   }
//   i++;
//  } /* of while */
//
//  /* Remove all options except those intended for the UI. */
//  argv[1 + ui_options] = null;
//  argc = 1 + ui_options;
//
//  /* disallow running as root -- too dangerous */
//  dont_run_as_root(argv[0], "freeciv_client");
//
//  log_init(logfile, loglevel, null);
//
//  /* after log_init: */
//
//  default_user_name = String.format( user_username());
//  if (!is_valid_username(default_user_name)) {
//    char buf[sizeof(default_user_name)];
//
//    buf = util.my_snprintf( "_%s", default_user_name);
//    if (is_valid_username(buf)) {
//      default_user_name = buf;
//    } else {
//      default_user_name = util.my_snprintf(
//		  "player%d", Rand.myrand(10000));
//    }
//  }
//
//  /* initialization */
//
//  conn_list_init(&Game.game.all_connections);
//  conn_list_init(&Game.game.est_connections);
//  conn_list_init(&Game.game.game_connections);
//
//  ui_init();
//  charsets_init();
//  my_init_network();
//  chatline_common_init();
//  init_messages_where();
//  init_city_report_data();
//  init_player_dlg_common();
//  settable_options_init();
//
//  load_general_options();
//
//  if (tileset_name[0] == '\0') {
//    tileset_name = default_tileset_name;
//  }
//  if (sound_set_name[0] == '\0') 
//    sound_set_name = default_sound_set_name; 
//  if (sound_plugin_name[0] == '\0')
//    sound_plugin_name = default_sound_plugin_name; 
//  if (server_host[0] == '\0')
//    server_host = default_server_host; 
//  if (user_name[0] == '\0')
//    user_name = default_user_name; 
//  if (metaserver[0] == '\0')
//    metaserver = default_metaserver; 
//  if (server_port == -1) server_port = default_server_port;
//
//
//  /* This seed is not saved anywhere; randoms in the client should
//     have cosmetic effects only (eg city name suggestions).  --dwp */
//  mysrand(time(null));
//
//  boot_help_texts();
//  if (!tilespec_read_toplevel(tileset_name)) {
//    /* get tile sizes etc */
//    exit(EXIT_FAILURE);
//  }
//
//  audio_real_init(sound_set_name, sound_plugin_name);
//  audio_play_music("music_start", null);
//
//  /* run gui-specific client */
//  ui_main(argc, argv);
//
//  /* termination */
//  ui_exit();
//  
//  /* not reached */
//  return EXIT_SUCCESS;
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void ui_exit()
//{
//  attribute_flush();
//  client_remove_all_cli_conn();
//  my_shutdown_network();
//
//  client_game_free();
//
//  exit(EXIT_SUCCESS);
//}
//
//
///**************************************************************************
//...
//**************************************************************************/
//void handle_packet_input(void *packet, int type)
//{
//  if (!client_handle_packet(type, packet)) {
//    util.freelog(Log.LOG_ERROR, "Received unknown packet (type %d) from server!",
//	    type);
//  }
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void user_ended_turn()
//{
//  send_turn_done();
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void send_turn_done()
//{
//  util.freelog(Log.LOG_DEBUG, "send_turn_done() turn_done_button_state=%d",
//	  get_turn_done_button_state());
//
//  if (!get_turn_done_button_state()) {
//    /*
//     * The turn done button is disabled but the user may have press
//     * the return key.
//     */
//
//    if (agents_busy()) {
//      waiting_for_end_turn = true;
//    }
//
//    return;
//  }
//
//  waiting_for_end_turn = false;
//  turn_done_sent = true;
//
//  attribute_flush();
//
//  send_packet_player_turn_done(&aconnection);
//
//  update_turn_done_button_state();
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void send_goto_unit(unit punit, tile dest_tile)
//{
//  dsend_packet_unit_goto(&aconnection, punit.id,
//			 dest_tile.x, dest_tile.y);
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void send_report_request(enum report_type type)
//{
//  dsend_packet_report_req(&aconnection, type);
//}
//
///**************************************************************************
// called whenever client is changed to pre-Game.game state.
//**************************************************************************/
//void client_game_init()
//{
//  game_init();
//  attribute_init();
//  agents_init();
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void client_game_free()
//{
//  free_client_goto();
//  free_help_texts();
//  attribute_free();
//  agents_free();
//  game_free();
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void set_client_state(enum client_states newstate)
//{
//  boolean connect_error = (client_state == CLIENT_PRE_GAME_STATE)
//      && (newstate == CLIENT_PRE_GAME_STATE);
//  enum client_states oldstate = client_state;
//
//  if (newstate == CLIENT_server_states.GAME_OVER_STATE) {
//    /*
//     * Extra kludge for end-Game.game handling of the CMA.
//     */
//    for (city pcity : Game.game.player_ptr.cities.data) {
//      if (cma_is_city_under_agent(pcity, null)) {
//        cma_release_city(pcity);
//      }
//    } }
//    popdown_all_city_dialogs();
//    popdown_all_game_dialogs();
//    set_unit_focus(null);
//  }
//
//  if (client_state != newstate) {
//
//    /* If changing from pre-Game.game state to _either_ select race
//       or running state, then we have finished getting ruleset data,
//       and should translate data, for joining running Game.game or for
//       selecting nations.  (Want translated nation names in nation
//       select dialog.)
//    */
//    if (client_state==CLIENT_PRE_GAME_STATE
//	&& (newstate==CLIENT_SELECT_RACE_STATE
//	    || newstate==CLIENT_GAME_RUNNING_STATE)) {
//      translate_data_names();
//      audio_stop();		/* stop intro sound loop */
//    }
//      
//    client_state=newstate;
//
//    if (client_state == CLIENT_GAME_RUNNING_STATE) {
//      load_ruleset_specific_options();
//      create_event(null, E_GAME_START, "Game started.");
//      precalc_tech_data();
//      update_research(Game.game.player_ptr);
//      role_unit_precalcs();
//      boot_help_texts();	/* reboot */
//      can_slide = false;
//      update_unit_focus();
//      can_slide = true;
//      set_client_page(PAGE_GAME);
//    }
//    else if (client_state == CLIENT_PRE_GAME_STATE) {
//      popdown_all_city_dialogs();
//      popdown_all_game_dialogs();
//      close_all_diplomacy_dialogs();
//      set_unit_focus(null);
//      clear_notify_window();
//      if (oldstate != CLIENT_BOOT_STATE) {
//	client_game_free();
//      }
//      client_game_init();
//      if (!aconnection.established) {
//	set_client_page(PAGE_MAIN);
//      } else {
//	set_client_page(PAGE_START);
//      }
//    }
//    update_menus();
//  }
//  if (!aconnection.established && client_state == CLIENT_PRE_GAME_STATE) {
//    gui_server_connect();
//    if (auto_connect) {
//      if (connect_error) {
//	util.freelog(Log.LOG_NORMAL,
//		"There was an error while auto connecting; aborting.");
//	exit(EXIT_FAILURE);
//      } else {
//	server_autoconnect();
//	auto_connect = false;	/* don't try this again */
//      }
//    } 
//  }
//  update_turn_done_button_state();
//  update_conn_list_dialog();
//}
//
//
///**************************************************************************
//...
//**************************************************************************/
//enum client_states get_client_state()
//{
//  return client_state;
//}
//
///**************************************************************************
//  Remove pconn from all connection lists in client, then free it.
//**************************************************************************/
//void client_remove_cli_conn(connection pconn)
//{
//  if (pconn.player) {
//    conn_list_unlink(&pconn.player.connections, pconn);
//  }
//  conn_list_unlink(&Game.game.all_connections, pconn);
//  conn_list_unlink(&Game.game.est_connections, pconn);
//  conn_list_unlink(&Game.game.game_connections, pconn);
//  assert(pconn != &aconnection);
//  free(pconn);
//}
//
///**************************************************************************
//  Remove (and free) all connections from all connection lists in client.
//  Assumes Game.game.all_connections is properly maintained with all connections.
//**************************************************************************/
//void client_remove_all_cli_conn()
//{
//  while (Game.game.all_connections.foo_list_size() > 0) {
//    connection pconn = conn_list_get(&Game.game.all_connections, 0);
//    client_remove_cli_conn(pconn);
//  }
//}
//
//void dealloc_id(int id); /* double kludge (suppress a possible warning) */
//void dealloc_id(int id) { }/* kludge */
//
///**************************************************************************
//..
//**************************************************************************/
//void send_attribute_block_request()
//{
//  send_packet_player_attribute_block(&aconnection);
//}
//
///**************************************************************************
//..
//**************************************************************************/
//void wait_till_request_got_processed(int request_id)
//{
//  input_from_server_till_request_got_processed(aconnection.sock,
//					       request_id);
//}
//
///**************************************************************************
//..
//**************************************************************************/
//boolean client_is_observer()
//{
//  return aconnection.established && aconnection.observer;
//}
//
///**************************************************************************
// This function should be called every 500ms. It lets the unit blink
// and update the timeout.
//**************************************************************************/
//void real_timer_callback()
//{
//  static boolean flip = false;
//
//  if (get_client_state() != CLIENT_GAME_RUNNING_STATE) {
//    return;
//  }
//
//  if (Game.game.player_ptr.is_connected && Game.game.player_ptr.is_alive &&
//      !Game.game.player_ptr.turn_done) {
//    int is_waiting = 0, is_moving = 0;
//
//    for(player pplayer: Game.game.players){
//      if (pplayer.is_alive && pplayer.is_connected) {
//	if (pplayer.turn_done) {
//	  is_waiting++;
//	} else {
//	  is_moving++;
//	}
//      }
//    }
//
//    if (is_moving == 1 && is_waiting > 0) {
//      update_turn_done_button(false);	/* stress the slow player! */
//    }
//  }
//
//  blink_active_unit();
//
//  if (flip) {
//    update_timeout_label();
//    if (seconds_to_turndone > 0) {
//      seconds_to_turndone--;
//    } else {
//      seconds_to_turndone = 0;
//    }
//  }
//
//  flip = !flip;
//}
//
///**************************************************************************
//  Returns true if the client can issue orders (such as giving unit
//  commands).  This function should be called each time before allowing the
//  user to give an order.
//**************************************************************************/
//boolean can_client_issue_orders()
//{
//  return (!client_is_observer()
//	  && get_client_state() == CLIENT_GAME_RUNNING_STATE);
//}
//
///**************************************************************************
//  Returns true iff the client can do diplomatic meetings with another 
//  given player.
//**************************************************************************/
//boolean can_meet_with_player(player pplayer)
//{
//  return (could_meet_with_player(Game.game.player_ptr, pplayer)
//          && can_client_issue_orders());
//}
//
///**************************************************************************
//  Returns true iff the client can get intelligence from another 
//  given player.
//**************************************************************************/
//boolean can_intel_with_player(player pplayer)
//{
//  return could_intel_with_player(Game.game.player_ptr, pplayer);
//}
//
///**************************************************************************
//  Return true if the client can change the view; i.e. if the mapview is
//  active.  This function should be called each time before allowing the
//  user to do mapview actions.
//**************************************************************************/
//boolean can_client_change_view()
//{
//  return (get_client_state() == CLIENT_GAME_RUNNING_STATE
//	  || get_client_state() == CLIENT_server_states.GAME_OVER_STATE);
//}
}