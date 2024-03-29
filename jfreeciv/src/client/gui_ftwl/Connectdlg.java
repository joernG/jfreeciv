package client.gui_ftwl;

public class Connectdlg{

// Freeciv - Copyright (C) 2004 - The Freeciv Project
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
//#include <errno.h>
//#include <stdio.h>		/* sscanf */
//
//#include "fcintl.h"
//#include "log.h"
//#include "support.h"
//
//#include "chatline_common.h"	/* for append_output_window */
//#include "civclient.h"
//#include "clinet.h"		/* for get_server_address */
//#include "gui_main.h"
//
//#include "connectdlg.h"
//#include "widget.h"
//#include "theme_engine.h"
//
//#ifdef HAVE_WINSOCK
//#define ECONNREFUSED WSAECONNREFUSED
//#endif
//
//static te_screen screen;
//
//static void try_to_autoconnect(void *data);
//
///**************************************************************************
//  this regenerates the player information from a loaded Game.game on the server.
//**************************************************************************/
//void handle_game_load(packet_game_load packet)
//{
//  /* PORT ME */
//}
//
///**************************************************************************
//  ...
//**************************************************************************/
//static void connect_callback()
//{
//  char errbuf[512];
//
//  user_name = String.format( te_edit_get_current_value(screen, "username"));
//  server_host = String.format( te_edit_get_current_value(screen, "server"));
//  sscanf(te_edit_get_current_value(screen, "port"), "%d", &server_port);
//
//  if (connect_to_server(user_name, server_host, server_port,
//			errbuf, sizeof(errbuf)) != -1) {
//    te_destroy_screen(screen);
//    screen = null;
//  } else {
//    append_output_window(errbuf);
//  }
//}
//
///**************************************************************************
//  ...
//**************************************************************************/
//static void button_callback(final String id)
//{
//  if (strcmp(id, "connect") == 0) {
//    connect_callback();
//  } else {
//    assert(0!=1);
//  }
//}
//
///**************************************************************************
//  ...
//**************************************************************************/
//static final String edit_get_initial_value(final String id)
//{
//  if (strcmp(id, "port") == 0) {
//    static char buf[10];
//
//    buf = util.my_snprintf( "%d", server_port);
//    return buf;
//  } else if (strcmp(id, "username") == 0) {
//    return user_name;
//  } else if (strcmp(id, "password") == 0) {
//    return "";
//  } else if (strcmp(id, "server") == 0) {
//    return server_host;
//  } else {
//    assert(0!=1);
//    return null;
//  }
//}
//
///**************************************************************************
//  ...
//**************************************************************************/
//static int edit_get_width(final String id)
//{
//  if (strcmp(id, "port") == 0) {
//    return 5;
//  } else if (strcmp(id, "username") == 0) {
//    return 10;
//  } else if (strcmp(id, "password") == 0) {
//    return 10;
//  } else if (strcmp(id, "server") == 0) {
//    return 10;
//  } else {
//    assert(0!=1);
//    return 0;
//  }
//}
//
///**************************************************************************
//  ...
//**************************************************************************/
//static final String info_get_value(final String id)
//{
//  if (0) {
//
//  } else {
//    assert(0!=1);
//    return null;
//  }
//}
//
///**************************************************************************
//  ...
//**************************************************************************/
//static void popup_connect_window()
//{
//  struct te_screen_env env;
//
//  env.info_get_value=info_get_value;
//  env.edit_get_initial_value=edit_get_initial_value;
//  env.edit_get_width=edit_get_width;
//  env.button_callback=button_callback;
//
////  screen = te_get_screen(root_window, "connect", &env, DEPTH_MIN + 2);
//}
//
///**************************************************************************
//  Provide an interface for connecting to a Freeciv server.
//**************************************************************************/
//void gui_server_connect()
//{
//  popup_connect_window();
//}
//
///**************************************************************************
//  Start trying to autoconnect to civserver.
//  Calls get_server_address(), then arranges for
//  autoconnect_callback(), which calls try_to_connect(), to be called
//  roughly every AUTOCONNECT_INTERVAL milliseconds, until success,
//  fatal error or user intervention.
//**************************************************************************/
//void server_autoconnect()
//{
//  char buf[512];
//  int outcome;
//
//  buf = util.my_snprintf(
//	      ("Auto-connecting to server \"%s\" at port %d as \"%s\""),
//	      server_host, server_port, user_name);
//  append_output_window(buf);
//  outcome = get_server_address(server_host, server_port, buf, sizeof(buf));
//  if (outcome < 0) {
//    util.freelog(LOG_FATAL,
//	    ("Error contacting server \"%s\" at port %d " +
//	      "as \"%s\":\n %s\n"),
//	    server_host, server_port, user_name, buf);
//    exit(EXIT_FAILURE);
//  }
//  try_to_autoconnect(null);
//}
//
///**************************************************************************
//  Make an attempt to autoconnect to the server.  If the server isn't
//  there yet, arrange for this routine to be called again in about
//  AUTOCONNECT_INTERVAL milliseconds.  If anything else goes wrong, log
//  a fatal error.
//
//  Return false iff autoconnect succeeds.
//**************************************************************************/
//static void try_to_autoconnect(void *data)
//{
//  char errbuf[512];
//  static int count = 0;
//
//  count++;
//
//  /* abort if after 10 seconds the server couldn't be reached */
//
//  if (AUTOCONNECT_INTERVAL * count >= 10000) {
//    util.freelog(LOG_FATAL,
//	    ("Failed to contact server \"%s\" at port " +
//	      "%d as \"%s\" after %d attempts"),
//	    server_host, server_port, user_name, count);
//    exit(EXIT_FAILURE);
//  }
//
//  switch (try_to_connect(user_name, errbuf, sizeof(errbuf))) {
//  case 0:
//    /* Success! */
//    return;
//  case ECONNREFUSED:
//    /* Server not available (yet) - wait & retry */
//    sw_add_timeout(AUTOCONNECT_INTERVAL, try_to_autoconnect, null);
//    return;
//  default:
//    /* All other errors are fatal */
//    util.freelog(LOG_FATAL,
//	    ("Error contacting server \"%s\" at port %d " +
//	      "as \"%s\":\n %s\n"),
//	    server_host, server_port, user_name, errbuf);
//    exit(EXIT_FAILURE);
//  }
//}
//
///**************************************************************************
//  ...
//**************************************************************************/
//void close_connection_dialog()
//{
//  /* I do not think this is right, but currently gui-fs depends on it.
//   * It is called from client common code when shutting down. So I am
//   * not changing it now. - Per */
////  te_destroy_screen(screen);
//}
//
///**************************************************************************
//  ...
//**************************************************************************/
//void handle_authentication_req(enum authentication_type type, char *message)
//{
//}
//
///**************************************************************************
//  ...
//**************************************************************************/
//void really_close_connection_dialog()
//{
//  close_connection_dialog();
//}
}