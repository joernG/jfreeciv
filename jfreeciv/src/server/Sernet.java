package server;


public class Sernet{

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
//#include <assert.h>
//#include <errno.h>
//#include <stdio.h>
//#include <stdlib.h>
//#include <string.h>
//#include <time.h>
//
//#ifdef HAVE_ARPA_INET_H
//#include <arpa/inet.h>
//#endif
//#ifdef HAVE_NETDB_H
//#include <netdb.h>
//#endif
//#ifdef HAVE_NETINET_IN_H
//#include <netinet/in.h>
//#endif
//#ifdef HAVE_PWD_H
//#include <pwd.h>
//#endif
//#ifdef HAVE_LIBREADLINE
//#include <readline/history.h>
//#include <readline/readline.h>
//#endif
//#ifdef HAVE_SYS_SELECT_H
//#include <sys/select.h>
//#endif
//#ifdef HAVE_SYS_SOCKET_H
//#include <sys/socket.h>
//#endif
//#ifdef HAVE_SYS_TIME_H
//#include <sys/time.h>
//#endif
//#ifdef HAVE_SYS_TYPES_H
//#include <sys/types.h>
//#endif
//#ifdef HAVE_SYS_UIO_H
//#include <sys/uio.h>
//#endif
//#ifdef HAVE_UNISTD_H
//#include <unistd.h>
//#endif
//#ifdef HAVE_WINSOCK
//#include <winsock.h>
//#endif
//
//#include "fciconv.h"
//
//#include "capability.h"
//#include "dataio.h"
//#include "events.h"
//#include "fcintl.h"
//#include "log.h"
//#include "mem.h"
//#include "netintf.h"
//#include "packets.h"
//#include "shared.h"
//#include "support.h"
//#include "timing.h"
//
//#include "auth.h"
//#include "connecthand.h"
//#include "console.h"
//#include "meta.h"
//#include "plrhand.h"
//#include "srv_main.h"
//#include "stdinhand.h"
//
//#include "sernet.h"
//
//static struct connection connections[MAX_NUM_CONNECTIONS];
//
//#ifdef GENERATING_MAC      /* mac network globals */
//TEndpointInfo serv_info;
//EndpointRef serv_ep;
//#else
//static int sock;
//static int socklan;
//#endif
//
//#if defined(__VMS)
//#  if defined(_VAX_)
//#    define lib$stop LIB$STOP
//#    define sys$qiow SYS$QIOW
//#    define sys$assign SYS$ASSIGN
//#  endif
//#  include <descrip.h>
//#  include <iodef.h>
//#  include <stsdef.h>
//#  include <starlet.h>
//#  include <lib$routines.h>
//#  include <efndef.h>
//   static unsigned long int tt_chan;
//   static char input_char = 0;
//   static char got_input = 0;
//   void user_interrupt_callback();
//#endif
//
//#define SPECLIST_TAG timer
//#define SPECLIST_TYPE struct timer
//#include "speclist.h"
//
//public static final int PROCESSING_TIME_STATISTICS = 0;
//
//static int server_accept_connection(int sockfd);
//static void start_processing_request(connection pconn,
//				     int request_id);
//static void finish_processing_request(connection pconn);
//static void ping_connection(connection pconn);
//static void send_ping_times_to_all();
//
//static void get_lanserver_announcement();
//static void send_lanserver_response();
//
//static boolean no_input = false;
//
///*****************************************************************************
//  This happens if you type an EOF character with nothing on the current line.
//*****************************************************************************/
//static void handle_stdin_close()
//{
//  /* Note this function may be called even if SOCKET_ZERO_ISNT_STDIN, so
//   * the preprocessor check has to come inside the function body.  But
//   * perhaps we want to do this even when SOCKET_ZERO_ISNT_STDIN? */
//#ifndef SOCKET_ZERO_ISNT_STDIN
//  util.freelog(Log.LOG_NORMAL, "Server cannot read standard input. Ignoring input.");
//  no_input = true;
//#endif
//}
//
//#ifdef HAVE_LIBREADLINE
///****************************************************************************/
//
//#define HISTORY_FILENAME  ".civserver_history"
//public static final int HISTORY_LENGTH = 100;
//
//static char *history_file = null;
//
//static boolean readline_handled_input = false;
//
//static boolean readline_initialized = false;
//
///*****************************************************************************
//...
//*****************************************************************************/
//static void handle_readline_input_callback(char *line)
//{
//  char *line_internal;
//
//  if (no_input)
//    return;
//
//  if (!line) {
//    handle_stdin_close();	/* maybe print an 'are you sure?' message? */
//    return;
//  }
//
//  if (line[0] != '\0')
//    add_history(line);
//
//  con_prompt_enter();		/* just got an 'Enter' hit */
//  line_internal = local_to_internal_string_malloc(line);
//  () handle_stdin_input(null, line_internal, false);
//  free(line_internal);
//
//  readline_handled_input = true;
//}
//
//#endif /* HAVE_LIBREADLINE */
//
///*****************************************************************************
//...
//*****************************************************************************/
//void close_connections_and_socket()
//{
//  int i;
//  lsend_packet_server_shutdown(&Game.game.all_connections);
//
//  for(i=0; i<MAX_NUM_CONNECTIONS; i++) {
//    if(connections[i].used) {
//      close_connection(&connections[i]);
//    }
//    conn_list_unlink_all(&connections[i].self);
//  }
//
//  /* Remove the Game.game connection lists and make sure they are empty. */
//  assert(Game.game.all_connections.foo_list_size() == 0);
//  conn_list_unlink_all(&Game.game.all_connections);
//  assert(Game.game.est_connections.foo_list_size() == 0);
//  conn_list_unlink_all(&Game.game.est_connections);
//  assert(Game.game.game_connections.foo_list_size() == 0);
//  conn_list_unlink_all(&Game.game.game_connections);
//
//  my_closesocket(sock);
//  my_closesocket(socklan);
//
//#ifdef HAVE_LIBREADLINE
//  if (history_file) {
//    write_history(history_file);
//    history_truncate_file(history_file, HISTORY_LENGTH);
//  }
//#endif
//
//  send_server_info_to_metaserver(META_GOODBYE);
//  server_close_meta();
//
//  my_shutdown_network();
//}
//
///*****************************************************************************
//  Used for errors and other strangeness.  As well as some direct uses, is
//  passed to packet routines as callback for when packet sending has a write
//  error.  Closes the connection cleanly, calling lost_connection_to_client()
//  to clean up server variables, notify other clients, etc.
//*****************************************************************************/
//static void close_socket_callback(connection pc)
//{
//  lost_connection_to_client(pc);
//  pc.close_connection();
//}

/****************************************************************************
  Attempt to flush all information in the send buffers for upto 'netwait'
  seconds.
*****************************************************************************/
public static void flush_packets()
{
//  int i;
//  int max_desc;
//  fd_set writefs, exceptfs;
//  struct timeval tv;
//  time_t start;
//
//  () time(&start);
//
//  for(;;) {
//    tv.tv_sec=(Game.game.netwait - (new Date() - start));
//    tv.tv_usec=0;
//
//    if (tv.tv_sec < 0)
//      return;
//
//    MY_FD_ZERO(&writefs);
//    MY_FD_ZERO(&exceptfs);
//    max_desc=-1;
//
//    for(i=0; i<MAX_NUM_CONNECTIONS; i++) {
//      connection pconn = &connections[i];
//      if(pconn.used && pconn.send_buffer.ndata > 0) {
//	FD_SET(pconn.sock, &writefs);
//	FD_SET(pconn.sock, &exceptfs);
//	max_desc=MAX(pconn.sock, max_desc);
//      }
//    }
//
//    if (max_desc == -1) {
//      return;
//    }
//
//    if(select(max_desc+1, null, &writefs, &exceptfs, &tv)<=0) {
//      return;
//    }
//
//    for(i=0; i<MAX_NUM_CONNECTIONS; i++) {   /* check for freaky players */
//      connection pconn = &connections[i];
//      if(pconn.used) {
//        if(FD_ISSET(pconn.sock, &exceptfs)) {
//	  util.freelog(Log.LOG_NORMAL, "cut connection %s due to exception data",
//		  pconn.conn_description());
//	  close_socket_callback(pconn);
//        } else {
//	  if(pconn.send_buffer && pconn.send_buffer.ndata > 0) {
//	    if(FD_ISSET(pconn.sock, &writefs)) {
//	      flush_connection_send_buffer_all(pconn);
//	    } else {
//	      if(Game.game.tcptimeout != 0 && pconn.last_write != 0
//		 && (new Date()>pconn.last_write + Game.game.tcptimeout)) {
//	        util.freelog(Log.LOG_NORMAL, "cut connection %s due to lagging player",
//			pconn.conn_description());
//		close_socket_callback(pconn);
//	      }
//	    }
//	  }
//        }
//      }
//    }
//  }
}

///*****************************************************************************
//Get and handle:
//- new connections,
//- input from connections,
//- input from server operator in stdin
//Returns:
//  0 if went past end-of-turn timeout
//  2 if force_end_of_sniff found to be set
//  1 otherwise (got and processed something?)
//This function also handles prompt printing, via the con_prompt_*
//functions.  That is, other functions should not need to do so.  --dwp
//*****************************************************************************/
//int sniff_packets()
//{
//  int i;
//  int max_desc;
//  fd_set readfs, writefs, exceptfs;
//  struct timeval tv;
//  static int year;
//#ifdef SOCKET_ZERO_ISNT_STDIN
//  char *bufptr;    
//#endif
//
//  con_prompt_init();
//
//#ifdef HAVE_LIBREADLINE
//  {
//    if (!no_input && !readline_initialized) {
//      char *home_dir = user_home_dir();
//      if (home_dir) {
//	history_file =
//	  fc_malloc(home_dir.length() + 1 + HISTORY_FILENAME.length() + 1);
//	if (history_file) {
//	  strcpy(history_file, home_dir);
//	  strcat(history_file, "/");
//	  strcat(history_file, HISTORY_FILENAME);
//	  using_history();
//	  read_history(history_file);
//	}
//      }
//
//      rl_initialize();
//      rl_callback_handler_install((char *) "> ", handle_readline_input_callback);
//      rl_attempted_completion_function = freeciv_completion;
//
//      readline_initialized = true;
//      atexit(rl_callback_handler_remove);
//    }
//  }
//#endif /* HAVE_LIBREADLINE */
//
//  if(year!=Game.game.year) {
//    if (Srv_main.server_state == server_states.RUN_GAME_STATE) year=Game.game.year;
//  }
//  if (Game.game.timeout == 0) {
//    /* Just in case someone sets timeout we keep Game.game.turn_start updated */
//    Game.game.turn_start = new Date(); //time(null);
//  }
//  
//  while(true) {
//    con_prompt_on();		/* accepting new input */
//    
//    if(force_end_of_sniff) {
//      force_end_of_sniff = false;
//      con_prompt_off();
//      return 2;
//    }
//
//    get_lanserver_announcement();
//
//    /* end server if no players for 'Srv_main.srvarg.quitidle' seconds */
//    if (Srv_main.srvarg.quitidle != 0 && Srv_main.server_state != server_states.PRE_GAME_STATE) {
//      static time_t last_noplayers;
//      if(Game.game.est_connections.foo_list_size() == 0) {
//	if (last_noplayers != 0) {
//	  if (new Date()>last_noplayers + Srv_main.srvarg.quitidle) {
//	    if (Srv_main.srvarg.exit_on_end) {
//	      save_game_auto();
//	    }
//	    set_meta_message_string("restarting for lack of players");
//	    util.freelog(Log.LOG_NORMAL, get_meta_message_string());
//	    () send_server_info_to_metaserver(META_INFO);
//
//            Srv_main.server_state = server_states.GAME_OVER_STATE;
//            force_end_of_sniff = true;
//            for (conn pconn : Game.game.est_connections.data) {
//              lost_connection_to_client(pconn);
//              pconn.close_connection();
//            } }
//
//	    if (Srv_main.srvarg.exit_on_end) {
//	      /* No need for anything more; just quit. */
//	      server_quit();
//	    }
//	  }
//	} else {
//          char buf[256];
//	  last_noplayers = new Date(); //time(null);
//	  
//	  buf = util.my_snprintf(
//		      "restarting in %d seconds for lack of players",
//		      Srv_main.srvarg.quitidle);
//          set_meta_message_string((final String)buf);
//	  util.freelog(Log.LOG_NORMAL, get_meta_message_string());
//	  () send_server_info_to_metaserver(META_INFO);
//	}
//      } else {
//        last_noplayers = 0;
//      }
//    }
//
//    /* Pinging around for statistics */
//    if (new Date() > (Game.game.last_ping + Game.game.pingtime)) {
//      /* send data about the previous run */
//      send_ping_times_to_all();
//
//      for (conn pconn : Game.game.all_connections.data) {
//	if ((pconn.server.ping_timers.foo_list_size() > 0
//	     &&
//	     read_timer_seconds(timer_list_get(pconn.server.ping_timers, 0))
//	     > Game.game.pingtimeout) || pconn.ping_time > Game.game.pingtimeout) {
//	  /* cut mute players, except for hack-level ones */
//	  if (pconn.access_level == cmdlevel_id.ALLOW_HACK) {
//	    util.freelog(Log.LOG_NORMAL,
//		    "ignoring ping timeout to hack-level connection %s",
//		    pconn.conn_description());
//	  } else {
//	    util.freelog(Log.LOG_NORMAL, "cut connection %s due to ping timeout",
//		    pconn.conn_description());
//	    close_socket_callback(pconn);
//	  }
//	} else {
//	  ping_connection(pconn);
//	}
//      } }
//      Game.game.last_ping = new Date(); //time(null);
//    }
//
//    /* if we've waited long enough after a failure, respond to the client */
//    for (conn pconn : Game.game.all_connections.data) {
//      if (Srv_main.srvarg.auth_enabled && pconn.server.status != AS_ESTABLISHED) {
//        process_authentication_status(pconn);
//      }
//    } conn_list_iterate_end
//
//    /* Don't wait if timeout == -1 (i.e. on auto games) */
//    if (Srv_main.server_state != server_states.PRE_GAME_STATE && Game.game.timeout == -1) {
//      () send_server_info_to_metaserver(META_REFRESH);
//      return 0;
//    }
//
//    tv.tv_sec=1;
//    tv.tv_usec=0;
//
//    MY_FD_ZERO(&readfs);
//    MY_FD_ZERO(&writefs);
//    MY_FD_ZERO(&exceptfs);
//
//    if (!no_input) {
//#ifdef SOCKET_ZERO_ISNT_STDIN
//      my_init_console();
//#else
//#   if !defined(__VMS)
//      FD_SET(0, &readfs);
//#   endif	
//#endif
//    }
//
//    FD_SET(sock, &readfs);
//    FD_SET(sock, &exceptfs);
//    max_desc=sock;
//
//    for(i=0; i<MAX_NUM_CONNECTIONS; i++) {
//      if(connections[i].used) {
//	FD_SET(connections[i].sock, &readfs);
//	if(connections[i].send_buffer.ndata > 0) {
//	  FD_SET(connections[i].sock, &writefs);
//	}
//	FD_SET(connections[i].sock, &exceptfs);
//        max_desc=MAX(connections[i].sock, max_desc);
//      }
//    }
//    con_prompt_off();		/* output doesn't generate a new prompt */
//
//    if(select(max_desc+1, &readfs, &writefs, &exceptfs, &tv)==0) { /* timeout */
//      () send_server_info_to_metaserver(META_REFRESH);
//      if(Game.game.timeout != 0
//	&& (new Date()>Game.game.turn_start + Game.game.timeout)
//	&& (Srv_main.server_state == server_states.RUN_GAME_STATE)){
//	con_prompt_off();
//	return 0;
//      }
//
//      if (!no_input) {
//#if defined(__VMS)
//      {
//	struct { short numchars; char firstchar; char reserved; int reserved2; } ttchar;
//	unsigned long status;
//	status = sys$qiow(EFN$C_ENF,tt_chan,IO$_SENSEMODE|IO$M_TYPEAHDCNT,0,0,0,
//			  &ttchar,sizeof(ttchar),0,0,0,0);
//	if (!$VMS_STATUS_SUCCESS(status)) lib$stop(status);
//	if (ttchar.numchars)
//	  FD_SET(0, &readfs);
//	else
//	  continue;
//      }
//#else  /* !__VMS */
//#ifndef SOCKET_ZERO_ISNT_STDIN
//      continue;
//#endif /* SOCKET_ZERO_ISNT_STDIN */
//#endif /* !__VMS */
//      }
//    }
//    if (Game.game.timeout == 0) {
//      /* Just in case someone sets timeout we keep Game.game.turn_start updated */
//      Game.game.turn_start = new Date(); //time(null);
//    }
//
//    if(FD_ISSET(sock, &exceptfs)) {	     /* handle Ctrl-Z suspend/resume */
//      continue;
//    }
//    if(FD_ISSET(sock, &readfs)) {	     /* new players connects */
//      util.freelog(Log.LOG_VERBOSE, "got new connection");
//      if(server_accept_connection(sock)==-1) {
//	util.freelog(Log.LOG_ERROR, "failed accepting connection");
//      }
//    }
//    for(i=0; i<MAX_NUM_CONNECTIONS; i++) {   /* check for freaky players */
//      connection pconn = &connections[i];
//      if(pconn.used && FD_ISSET(pconn.sock, &exceptfs)) {
// 	util.freelog(Log.LOG_ERROR, "cut connection %s due to exception data",
//		pconn.conn_description());
//	close_socket_callback(pconn);
//      }
//    }
//    
//#ifdef SOCKET_ZERO_ISNT_STDIN
//    if (!no_input && (bufptr = my_read_console())) {
//      char *bufptr_internal = local_to_internal_string_malloc(bufptr);
//
//      con_prompt_enter();	/* will need a new prompt, regardless */
//      handle_stdin_input(null, bufptr_internal, false);
//      free(bufptr_internal);
//    }
//#else  /* !SOCKET_ZERO_ISNT_STDIN */
//    if(!no_input && FD_ISSET(0, &readfs)) {    /* input from server operator */
//#ifdef HAVE_LIBREADLINE
//      rl_callback_read_char();
//      if (readline_handled_input) {
//	readline_handled_input = false;
//	con_prompt_enter_clear();
//      }
//      continue;
//#else  /* !HAVE_LIBREADLINE */
//      ssize_t didget;
//      char buf[BUF_SIZE+1];
//      char *buf_internal;
//      
//      didget = read(0, buf, BUF_SIZE);
//      if (didget <= 0) {
//        handle_stdin_close();
//	didget = 0; /* Avoid buffer underrun below. */
//      }
//
//      *(buf+didget)='\0';
//      con_prompt_enter();	/* will need a new prompt, regardless */
//      buf_internal = local_to_internal_string_malloc(buf);
//      handle_stdin_input(null, buf_internal, false);
//      free(buf_internal);
//#endif /* !HAVE_LIBREADLINE */
//    }
//    else
//#endif /* !SOCKET_ZERO_ISNT_STDIN */
//     
//    {                             /* input from a player */
//      for(i=0; i<MAX_NUM_CONNECTIONS; i++) {
//  	connection pconn = &connections[i];
//	if(pconn.used && FD_ISSET(pconn.sock, &readfs)) {
//	  if(read_socket_data(pconn.sock, pconn.buffer)>=0) {
//	    void *packet;
//	    enum packet_type type;
//	    boolean result;
//
//	    while (true) {
//	      packet = get_packet_from_connection(pconn, &type, &result);
//	      if (result) {
//		boolean command_ok;
//
//		pconn.server.last_request_id_seen =
//		    get_next_request_id(pconn.server.
//					last_request_id_seen);
//#if PROCESSING_TIME_STATISTICS
//		{
//		  int err;
//		  struct timeval start, end;
//		  struct timezone tz;
//		  long us;
//
//		  err = gettimeofday(&start, &tz);
//		  assert(!err);
//#endif
//		connection_do_buffer(pconn);
//		start_processing_request(pconn,
//					 pconn.server.
//					 last_request_id_seen);
//		command_ok = handle_packet_input(pconn, packet, type);
//		if (packet) {
//		  free(packet);
//		  packet = null;
//		}
//
//		finish_processing_request(pconn);
//		connection_do_unbuffer(pconn);
//		if (!command_ok) {
//		  pconn.close_connection();
//		}
//#if PROCESSING_TIME_STATISTICS
//		  err = gettimeofday(&end, &tz);
//		  assert(!err);
//
//		  us = (end.tv_sec - start.tv_sec) * 1000000 +
//		      end.tv_usec - start.tv_usec;
//
//		  util.freelog(Log.LOG_NORMAL,
//			  "processed request %d in %ld.%03ldms",
//			  pconn.server.last_request_id_seen, us / 1000,
//			  us % 1000);
//                }
//#endif
//	      } else {
//		break;
//	      }
//	    }
//	  } else {
//	    close_socket_callback(pconn);
//	  }
//	}
//      }
//
//      for(i=0; i<MAX_NUM_CONNECTIONS; i++) {
//        connection pconn = &connections[i];
//        if(pconn.used && pconn.send_buffer && pconn.send_buffer.ndata > 0) {
//	  if(FD_ISSET(pconn.sock, &writefs)) {
//	    flush_connection_send_buffer_all(pconn);
//	  } else {
//	    if(Game.game.tcptimeout != 0 && pconn.last_write != 0
//	       && (new Date()>pconn.last_write + Game.game.tcptimeout)) {
//	      util.freelog(Log.LOG_NORMAL, "cut connection %s due to lagging player",
//		      pconn.conn_description());
//	      close_socket_callback(pconn);
//	    }
//	  }
//        }
//      }
//    }
//    break;
//  }
//  con_prompt_off();
//
//  if (Game.game.timeout != 0 && (new Date() > Game.game.turn_start + Game.game.timeout)) {
//    return 0;
//  }
//  return 1;
//}
//
///********************************************************************
//  Make up a name for the connection, before we get any data from
//  it to use as a sensible name.  Name will be 'c' + integer,
//  guaranteed not to be the same as any other connection name,
//  nor player name nor user name, nor connection id (avoid possible
//  confusions).   Returns pointer to static buffer, and fills in
//  (*id) with chosen value.
//********************************************************************/
//static final String makeup_connection_name(int *id)
//{
//  static unsigned short i = 0;
//  static String name="";
//
//  for(;;) {
//    if (i==(unsigned short)-1) i++;              /* don't use 0 */
//    name = util.my_snprintf( "c%u", (unsigned int)++i);
//    if (!find_player_by_name(name)
//	&& !find_player_by_user(name)
//	&& !find_conn_by_id(i)
//	&& !Connection.find_conn_by_user(name)) {
//      *id = i;
//      return name;
//    }
//  }
//}
//  
///********************************************************************
//  Server accepts connection from client:
//  Low level socket stuff, and basic-initialize the connection struct.
//  Returns 0 on success, -1 on failure (bad accept(), or too many
//  connections).
//********************************************************************/
//static int server_accept_connection(int sockfd)
//{
//  /* This used to have size_t for some platforms.  If this is necessary
//   * it should be done with a configure check not a platform check. */
//#ifdef HAVE_SOCKLEN_T
//  socklen_t fromlen;
//#else
//  int fromlen;
//#endif
//
//  int new_sock;
//  union my_sockaddr fromend;
//  hostent from;
//  int i;
//
//  fromlen = sizeof(fromend);
//
//  if ((new_sock = accept(sockfd, &fromend.sockaddr, &fromlen)) == -1) {
//    util.freelog(Log.LOG_ERROR, "accept failed: %s", mystrerror());
//    return -1;
//  }
//
//  my_nonblock(new_sock);
//
//  from =
//      gethostbyaddr((char *) &fromend.sockaddr_in.sin_addr,
//		    sizeof(fromend.sockaddr_in.sin_addr), AF_INET);
//
//  for(i=0; i<MAX_NUM_CONNECTIONS; i++) {
//    connection pconn = &connections[i];
//    if (!pconn.used) {
//      connection_common_init(pconn);
//      pconn.sock = new_sock;
//      pconn.observer = false;
//      pconn.player = null;
//      pconn.capability[0] = '\0';
//      pconn.access_level = access_level_for_next_connection();
//      pconn.delayed_disconnect = false;
//      pconn.notify_of_writable_data = null;
//      pconn.server.currently_processed_request_id = 0;
//      pconn.server.last_request_id_seen = 0;
//      pconn.server.auth_tries = 0;
//      pconn.server.auth_settime = 0;
//      pconn.server.status = AS_NOT_ESTABLISHED;
//      pconn.server.ping_timers =
//	  fc_malloc(sizeof(*pconn.server.ping_timers));
//      timer_list_init(pconn.server.ping_timers);
//      pconn.ping_time = -1.0;
//      pconn.incoming_packet_notify = null;
//      pconn.outgoing_packet_notify = null;
//
//      pconn.username = String.format( makeup_connection_name(&pconn.id));
//      pconn.addr = String.format(
//		 (from ? from.
//		  h_name : inet_ntoa(fromend.sockaddr_in.sin_addr)));
//      pconn.server.ipaddr = String.format(
//                 inet_ntoa(fromend.sockaddr_in.sin_addr));
//
//      conn_list_insert_back(&Game.game.all_connections, pconn);
//  
//      util.freelog(Log.LOG_VERBOSE, "connection (%s) from %s (%s)", 
//              pconn.username, pconn.addr, pconn.server.ipaddr);
//      ping_connection(pconn);
//      return 0;
//    }
//  }
//
//  util.freelog(Log.LOG_ERROR, "maximum number of connections reached");
//  return -1;
//}
//
///********************************************************************
// open server socket to be used to accept client connections
// and open a server socket for server LAN announcements.
//********************************************************************/
//int server_open_socket()
//{
//  /* setup socket address */
//  union my_sockaddr src;
//  union my_sockaddr addr;
//  struct ip_mreq mreq;
//  final String group;
//  int opt;
//
//  /* Create socket for client connections. */
//  if((sock = socket(AF_INET, SOCK_STREAM, 0)) == -1) {
//    util.die("socket failed: %s", mystrerror());
//  }
//
//  opt=1; 
//  if(setsockopt(sock, SOL_SOCKET, SO_REUSEADDR, 
//		(char *)&opt, sizeof(opt)) == -1) {
//    util.freelog(Log.LOG_ERROR, "SO_REUSEADDR failed: %s", mystrerror());
//  }
//
//  if (!net_lookup_service(Srv_main.srvarg.bind_addr, Srv_main.srvarg.port, &src)) {
//    util.freelog(Log.LOG_ERROR, "Server: bad address: [%s:%d].",
//	    Srv_main.srvarg.bind_addr, Srv_main.srvarg.port);
//    exit(EXIT_FAILURE);
//  }
//
//  if(bind(sock, &src.sockaddr, sizeof (src)) == -1) {
//    util.freelog(LOG_FATAL, "bind failed: %s", mystrerror());
//    exit(EXIT_FAILURE);
//  }
//
//  if(listen(sock, MAX_NUM_CONNECTIONS) == -1) {
//    util.freelog(LOG_FATAL, "listen failed: %s", mystrerror());
//    exit(EXIT_FAILURE);
//  }
//
//  /* Create socket for server LAN announcements */
//  if ((socklan = socket(AF_INET, SOCK_DGRAM, 0)) < 0) {
//     util.freelog(Log.LOG_ERROR, "socket failed: %s", mystrerror());
//  }
//
//  if (setsockopt(socklan, SOL_SOCKET, SO_REUSEADDR,
//                 (char *)&opt, sizeof(opt)) == -1) {
//    util.freelog(Log.LOG_ERROR, "SO_REUSEADDR failed: %s", mystrerror());
//  }
//
//  my_nonblock(socklan);
//
//  group = get_multicast_group();
//
//  memset(&addr, 0, sizeof(addr));
//  addr.sockaddr_in.sin_family = AF_INET;
//  addr.sockaddr_in.sin_addr.s_addr = htonl(INADDR_ANY);
//  addr.sockaddr_in.sin_port = htons(SERVER_LAN_PORT);
//
//  if (bind(socklan, &addr.sockaddr, sizeof(addr)) < 0) {
//    util.freelog(Log.LOG_ERROR, "bind failed: %s", mystrerror());
//  }
//
//  mreq.imr_multiaddr.s_addr = inet_addr(group);
//  mreq.imr_interface.s_addr = htonl(INADDR_ANY);
//
//  if (setsockopt(socklan, IPPROTO_IP, IP_ADD_MEMBERSHIP,
//                 (final char*)&mreq, sizeof(mreq)) < 0) {
//    util.freelog(Log.LOG_ERROR, "setsockopt failed: %s", mystrerror());
//  }
//
//  close_socket_set_callback(close_socket_callback);
//  return 0;
//}
//
//
///********************************************************************
//...
//********************************************************************/
//void init_connections()
//{
//  int i;
//
//  conn_list_init(&Game.game.all_connections);
//  conn_list_init(&Game.game.est_connections);
//  conn_list_init(&Game.game.game_connections);
//
//  for(i=0; i<MAX_NUM_CONNECTIONS; i++) { 
//    connection pconn = &connections[i];
//    pconn.used = false;
//    conn_list_init(&pconn.self);
//    &pconn.self.foo_list_insert(pconn);
//  }
//#if defined(__VMS)
//  {
//    unsigned long status;
//    $DESCRIPTOR (tt_desc, "SYS$INPUT");
//    status = sys$assign(&tt_desc,&tt_chan,0,0);
//    if (!$VMS_STATUS_SUCCESS(status)) lib$stop(status);
//  }
//#endif
//}
//
///**************************************************************************
//...
//**************************************************************************/
//static void start_processing_request(connection pconn,
//				     int request_id)
//{
//  assert(request_id);
//  assert(pconn.server.currently_processed_request_id == 0);
//  util.freelog(Log.LOG_DEBUG, "start processing packet %d from connection %d",
//	  request_id, pconn.id);
//  send_packet_processing_started(pconn);
//  pconn.server.currently_processed_request_id = request_id;
//}
//
///**************************************************************************
//...
//**************************************************************************/
//static void finish_processing_request(connection pconn)
//{
//  assert(pconn.server.currently_processed_request_id);
//  util.freelog(Log.LOG_DEBUG, "finish processing packet %d from connection %d",
//	  pconn.server.currently_processed_request_id, pconn.id);
//  send_packet_processing_finished(pconn);
//  pconn.server.currently_processed_request_id = 0;
//}
//
///**************************************************************************
//...
//**************************************************************************/
//static void ping_connection(connection pconn)
//{
//  util.freelog(Log.LOG_DEBUG, "sending ping to %s (open=%d)",
//	  pconn.conn_description(),
//	  pconn.server.ping_timers.foo_list_size());
//  timer_list_insert_back(pconn.server.ping_timers,
//			 new_timer_start(TIMER_USER, TIMER_ACTIVE));
//  send_packet_conn_ping(pconn);
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void handle_conn_pong(connection pconn)
//{
//  timer timer;
//
//  if (pconn.server.ping_timers.foo_list_size() == 0) {
//    util.freelog(Log.LOG_NORMAL, "got unexpected pong from %s",
//	    pconn.conn_description());
//    return;
//  }
//
//  timer = timer_list_get(pconn.server.ping_timers, 0);
//  timer_list_unlink(pconn.server.ping_timers, timer);
//  pconn.ping_time = read_timer_seconds_free(timer);
//  util.freelog(Log.LOG_DEBUG, "got pong from %s (open=%d); ping time = %fs",
//	  pconn.conn_description(),
//	  pconn.server.ping_timers.foo_list_size(), pconn.ping_time);
//}
//
///**************************************************************************
//...
//**************************************************************************/
//static void send_ping_times_to_all()
//{
//  struct packet_conn_ping_info packet;
//  int i;
//
//  i = 0;
//  for (conn pconn : Game.game.game_connections.data) {
//    if (!pconn.used) {
//      continue;
//    }
//    i++;
//  } }
//
//  packet.connections = i;
//  packet.old_connections = Math.min(i, Shared_H.MAX_NUM_PLAYERS);
//
//  i = 0;
//  for (conn pconn : Game.game.game_connections.data) {
//    if (!pconn.used) {
//      continue;
//    }
//    assert(i < ARRAY_SIZE(packet.conn_id));
//    packet.conn_id[i] = pconn.id;
//    packet.ping_time[i] = pconn.ping_time;
//    if (i < packet.old_connections) {
//      packet.old_conn_id[i] = pconn.id;
//      packet.old_ping_time[i] = pconn.ping_time;
//    }
//    i++;
//  } }
//  lsend_packet_conn_ping_info(&Game.game.est_connections, &packet);
//}
//
///********************************************************************
//  Listen for UDP packets multicasted from clients requesting
//  announcement of servers on the LAN.
//********************************************************************/
//static void get_lanserver_announcement()
//{
//  char msgbuf[128];
//  struct data_in din;
//  int type;
//  fd_set readfs, exceptfs;
//  struct timeval tv;
//
//  FD_ZERO(&readfs);
//  FD_ZERO(&exceptfs);
//  FD_SET(socklan, &exceptfs);
//  FD_SET(socklan, &readfs);
//
//  tv.tv_sec = 0;
//  tv.tv_usec = 0;
//
//  while (select(socklan + 1, &readfs, null, &exceptfs, &tv) == -1) {
//    if (errno != EINTR) {
//      util.freelog(Log.LOG_ERROR, "select failed: %s", mystrerror());
//      return;
//    }
//    /* EINTR can happen sometimes, especially when compiling with -pg.
//     * Generally we just want to run select again. */
//  }
//
//  if (FD_ISSET(socklan, &readfs)) {
//    if (0 < recvfrom(socklan, msgbuf, sizeof(msgbuf), 0, null, null)) {
//      dio_input_init(&din, msgbuf, 1);
//      dio_get_uint8(&din, &type);
//      if (type == SERVER_LAN_VERSION) {
//        util.freelog(Log.LOG_DEBUG, "Received request for server LAN announcement.");
//        send_lanserver_response();
//      } else {
//        util.freelog(Log.LOG_DEBUG,
//                "Received invalid request for server LAN announcement.");
//      }
//    }
//  }
//}
//
///********************************************************************
//  This function broadcasts an UDP packet to clients with
//  that requests information about the server state.
//********************************************************************/
//static void send_lanserver_response()
//{
//  unsigned char buffer[MAX_LEN_PACKET];
//  char hostname[512];
//  char port[256];
//  char version[256];
//  char players[256];
//  char status[256];
//  struct data_out dout;
//  union my_sockaddr addr;
//  int socksend, setting = 1;
//  final String group;
//  size_t size;
//  unsigned char ttl;
//
//  /* Create a socket to broadcast to client. */
//  if ((socksend = socket(AF_INET,SOCK_DGRAM, 0)) < 0) {
//    util.freelog(Log.LOG_ERROR, "socket failed: %s", mystrerror());
//    return;
//  }
//
//  /* Set the UDP Multicast group IP address of the packet. */
//  group = get_multicast_group();
//  memset(&addr, 0, sizeof(addr));
//  addr.sockaddr_in.sin_family = AF_INET;
//  addr.sockaddr_in.sin_addr.s_addr = inet_addr(group);
//  addr.sockaddr_in.sin_port = htons(SERVER_LAN_PORT + 1);
//
//  /* Set the Time-to-Live field for the packet.  */
//  ttl = SERVER_LAN_TTL;
//  if (setsockopt(socksend, IPPROTO_IP, IP_MULTICAST_TTL, 
//                 (final char*)&ttl, sizeof(ttl))) {
//    util.freelog(Log.LOG_ERROR, "setsockopt failed: %s", mystrerror());
//    return;
//  }
//
//  if (setsockopt(socksend, SOL_SOCKET, SO_BROADCAST, 
//                 (final char*)&setting, sizeof(setting))) {
//    util.freelog(Log.LOG_ERROR, "setsockopt failed: %s", mystrerror());
//    return;
//  }
//
//  /* Create a description of server state to send to clients.  */
//  if (my_gethostname(hostname, sizeof(hostname)) != 0) {
//    hostname = String.format( "none");
//  }
//
//  version = util.my_snprintf( "%d.%d.%d%s",
//              MAJOR_VERSION, MINOR_VERSION, PATCH_VERSION, VERSION_LABEL);
//
//  switch (Srv_main.server_state) {
//  case server_states.PRE_GAME_STATE:
//    status = util.my_snprintf( "Pregame");
//    break;
//  case server_states.RUN_GAME_STATE:
//    status = util.my_snprintf( "Running");
//    break;
//  case server_states.GAME_OVER_STATE:
//    status = util.my_snprintf( "Game over");
//    break;
//  default:
//    status = util.my_snprintf( "Waiting");
//  }
//
//   players = util.my_snprintf( "%d",
//               Game.get_num_human_and_ai_players());
//   port = util.my_snprintf( "%d",
//              Srv_main.srvarg.port );
//
//  dio_output_init(&dout, buffer, sizeof(buffer));
//  dio_put_uint8(&dout, SERVER_LAN_VERSION);
//  dio_put_string(&dout, hostname);
//  dio_put_string(&dout, port);
//  dio_put_string(&dout, version);
//  dio_put_string(&dout, status);
//  dio_put_string(&dout, players);
//  dio_put_string(&dout, get_meta_message_string());
//  size = dio_output_used(&dout);
//
//  /* Sending packet to client with the information gathered above. */
//  if (sendto(socksend, buffer,  size, 0, &addr.sockaddr,
//      sizeof(addr)) < 0) {
//    util.freelog(Log.LOG_ERROR, "sendto failed: %s", mystrerror());
//    return;
//  }
//
//  my_closesocket(socksend);
//}
}