package client.gui_sdl;

public class Gui_main{

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
///**********************************************************************
//                          gui_main.c  -  description
//                             -------------------
//    begin                : Sun Jun 30 2002
//    copyright            : (C) 2002 by Rafał Bursig
//    email                : Rafał Bursig <bursig@poczta.fm>
// **********************************************************************/
//
//#ifdef HAVE_CONFIG_H
//#include <config.h>
//#endif
//
//#include <assert.h>
//#include <errno.h>
//#include <stdlib.h>
//#include <string.h>
//
//#ifdef HAVE_LOCALE_H
//#include <locale.h>
//#endif
//
//#ifdef HAVE_UNISTD_H
//#include <unistd.h>
//#endif
//
//#ifdef WIN32_NATIVE
//#include <winsock.h>
//#endif
//
//#include <SDL/SDL.h>
//
//#include "fcintl.h"
//#include "fciconv.h"
//#include "log.h"
//#include "shared.h"
//#include "support.h"
//
//#include "gui_mem.h"
//
//#include "Game.game.h"
//#include "Map.map.h"
//#include "version.h"
//
//#include "gui_string.h"
//#include "gui_stuff.h"		/* gui */
//#include "gui_id.h"
//
//#include "chatline.h"
//#include "civclient.h"
//#include "climisc.h"
//#include "clinet.h"
//#include "colors.h"
//#include "connectdlg.h"
//#include "control.h"
//#include "dialogs.h"
//#include "gotodlg.h"
//#include "graphics.h"
//
//#include "timing.h"
//
//#include "helpdata.h"		/* boot_help_texts() */
//#include "mapctrl.h"
//#include "mapview.h"
//#include "menu.h"
//#include "optiondlg.h"
//#include "options.h"
//#include "spaceshipdlg.h"
//#include "resources.h"
//#include "tilespec.h"
//#include "gui_tilespec.h"
//#include "messagewin.h"
//#include "citydlg.h"
//#include "cityrep.h"
//
//#include "repodlgs.h"
//
//#include "gui_main.h"
//
///*#include "freeciv.ico"*/
//
//public static final int UNITS_TIMER_INTERVAL = 128;	/* milliseconds */
//public static final int MAP_SCROLL_TIMER_INTERVAL = 384;
//
//final String client_string = "gui-sdl";
//
//Uint32 SDL_Client_Flags = 0;
//Uint32 widget_info_counter = 0;
//int MOVE_STEP_X = DEFAULT_MOVE_STEP;
//int MOVE_STEP_Y = DEFAULT_MOVE_STEP;
//extern boolean draw_goto_patrol_lines;
//SDL_Event *pFlush_User_Event = null;
//boolean is_unit_move_blocked;
//boolean LSHIFT;
//boolean RSHIFT;
//boolean LCTRL;
//boolean RCTRL;
//boolean LALT;
//boolean do_focus_animation = true;
//SDL_Cursor **pAnimCursor = null;
//boolean do_cursor_animation = true;
//
///* ================================ Private ============================ */
//static SDL_Cursor **pStoreAnimCursor = null;
//static int net_socket = -1;
//static boolean autoconnect = false;
//static boolean is_map_scrolling = false;
//static enum direction8 scroll_dir;
//static SDL_Event *pNet_User_Event = null;
//static SDL_Event *pAnim_User_Event = null;
//static SDL_Event *pInfo_User_Event = null;
//static SDL_Event *pMap_Scroll_User_Event = null;
//static void print_usage(final String argv0);
//static void parse_options(int argc, char **argv);
//static void game_focused_unit_anim();
//      
//enum USER_EVENT_ID {
//  EVENT_ERROR = 0,
//  NET = 1,
//  ANIM = 2,
//  TRY_AUTO_CONNECT = 3,
//  SHOW_WIDGET_INFO_LABBEL = 4,
//  FLUSH = 5,
//  MAP_SCROLL = 6,
//  EXIT_FROM_EVENT_LOOP = 7
//};
//
//client_option gui_options[] = {
//  /* None. */
//};
//final int num_gui_options = ARRAY_SIZE(gui_options);
//
///* =========================================================== */
//
///**************************************************************************
//  Print extra usage information, including one line help on each option,
//  to stderr. 
//**************************************************************************/
//static void print_usage(final String argv0)
//{
//  /* add client-specific usage information here */
//  fc_fprintf(stderr, "Report bugs to <%s>.\n", BUG_EMAIL_ADDRESS);
//  fc_fprintf(stderr,
//	     "  -f,  --fullscreen\tStart Client in Fulscreen mode\n");
//  fc_fprintf(stderr, ("  -e,  --eventthread\tInit Event Subsystem in " +
//		       "other thread (only Linux and BeOS)\n"));
//}
//
///**************************************************************************
// search for command line options. right now, it's just help
// semi-useless until we have options that aren't the same across all clients.
//**************************************************************************/
//static void parse_options(int argc, char **argv)
//{
//  int i = 1;
//    
//  while (i < argc) {
//    if (is_option("--help", argv[i])) {
//      print_usage(argv[0]);
//      exit(EXIT_SUCCESS);
//    } else {
//      if (is_option("--fullscreen",argv[i])) {
//	SDL_Client_Flags |= CF_TOGGLED_FULLSCREEN;
//      } else {
//	if (is_option("--eventthread",argv[i])) {
//	  /* init events in other thread ( only linux and BeOS ) */  
//          SDL_InitSubSystem(SDL_INIT_EVENTTHREAD);
//        }
//      }
//    }
//    i++;
//  }
//  
//}
//
///**************************************************************************
//...
//**************************************************************************/
//static Uint16 main_key_down_handler(SDL_keysym Key, void *pData)
//{
//  static GUI pWidget;
//  if ((pWidget = MainWidgetListKeyScaner(Key)) != null) {
//    return widget_pressed_action(pWidget);
//  } else {
//    if (RSHIFT && (Key.sym == SDLK_RETURN)) {
//      /* input */
//      popup_input_line();
//    } else {
//      if (map_event_handler(Key)
//		&& get_client_state() == CLIENT_GAME_RUNNING_STATE) {
//        switch (Key.sym) {
//	  case SDLK_RETURN:
//	  case SDLK_KP_ENTER:
//	  {
//	    unit pUnit;
//	    city pCity;
//	    if((pUnit = get_unit_in_focus()) != null && 
//	      (pCity = map_get_tile(pUnit.x, pUnit.y).city) != null &&
//	      City.city_owner(pCity) == Game.game.player_ptr) {
//	      popup_city_dialog(pCity, false);
//	    } else {
//	      disable_focus_animation();
//	      key_end_turn();
//	    }
//	  }
//	  return ID_ERROR;
//
//	  case SDLK_F1:
//            popup_city_report_dialog(false);
//	  return ID_ERROR;
//	    
//	  case SDLK_F2:
//	    popup_activeunits_report_dialog(false);
//	  return ID_ERROR;
//	   
//	  case SDLK_F7:
//            send_report_request(REPORT_WONDERS_OF_THE_WORLD);
//          return ID_ERROR;
//	    
//          case SDLK_F8:
//            send_report_request(REPORT_TOP_5_CITIES);
//          return ID_ERROR;
//	    
//	  case SDLK_F10:
//            if(is_meswin_open()) {
//              popdown_meswin_dialog();
//	      /* copy_chars_to_string16(pWidget.string16, "Show Log (F10)"); */
//            } else {
//              popup_meswin_dialog();
//	      /* copy_chars_to_string16(pWidget.string16, "Hide Log (F10)"); */
//            }
//	    flush_dirty();
//          return ID_ERROR;
//	    	        
//	  case SDLK_F11:
//            send_report_request(REPORT_DEMOGRAPHIC);
//          return ID_ERROR;
//	    
//	  case SDLK_F12:
//            popup_spaceship_dialog(Game.game.player_ptr);
//          return ID_ERROR;
//	  
//	  default:
//	  return ID_ERROR;
//	}
//      }
//    }
//  }
//  
//  return ID_ERROR;
//}
//
///**************************************************************************
//...
//**************************************************************************/
//static Uint16 main_key_up_handler(SDL_keysym Key, void *pData)
//{
//  if(pSellected_Widget) {
//    unsellect_widget_action();
//  }
//  return ID_ERROR;
//}
//
///**************************************************************************
//...
//**************************************************************************/
//static Uint16 main_mouse_button_down_handler(SDL_MouseButtonEvent *pButtonEvent, void *pData)
//{
//  static GUI pWidget;
//  if ((pWidget = MainWidgetListScaner(pButtonEvent.x, pButtonEvent.y)) != null) {
//    return widget_pressed_action(pWidget);
//  } else {
//    button_down_on_map(pButtonEvent);
//  }
//  return ID_ERROR;
//}
//
//public static final int SCROLL_MAP_AREA = 8;
//
///**************************************************************************
//...
//**************************************************************************/
//static Uint16 main_mouse_motion_handler(SDL_MouseMotionEvent *pMotionEvent, void *pData)
//{
//  static GUI pWidget;
//    
//  if(draw_goto_patrol_lines) {
//    update_line(pMotionEvent.x, pMotionEvent.y);
//  }
//      
//  if ((pWidget = MainWidgetListScaner(pMotionEvent.x, pMotionEvent.y)) != null) {
//    widget_sellected_action(pWidget);
//  } else {
//    if (pSellected_Widget) {
//      unsellect_widget_action();
//    } else {
//      if (get_client_state() == CLIENT_GAME_RUNNING_STATE) {
//        static SDL_Rect rect;
//                  
//        rect.x = rect.y = 0;
//        rect.w = SCROLL_MAP_AREA;
//        rect.h = Main.Map.map.h;
//        if (is_in_rect_area(pMotionEvent.x, pMotionEvent.y, rect)) {
//	  is_map_scrolling = true;
//	  if (scroll_dir != DIR8_WEST) {
//	    pStoreAnimCursor = pAnimCursor;
//	    if (do_cursor_animation && pAnim.Cursors.MapScroll[SCROLL_WEST][1]) {
//	      pAnimCursor = pAnim.Cursors.MapScroll[SCROLL_WEST];
//	    } else {
//	      SDL_SetCursor(pAnim.Cursors.MapScroll[SCROLL_WEST][0]);
//	    }
//	    scroll_dir = DIR8_WEST;
//	  }
//        } else {
//	  rect.x = Main.Map.map.w - SCROLL_MAP_AREA;
//	  if (is_in_rect_area(pMotionEvent.x, pMotionEvent.y, rect)) {
//	    is_map_scrolling = true;
//	    if (scroll_dir != DIR8_EAST) {
//	      pStoreAnimCursor = pAnimCursor;
//	      if (do_cursor_animation && pAnim.Cursors.MapScroll[SCROLL_EAST][1]) {
//	        pAnimCursor = pAnim.Cursors.MapScroll[SCROLL_EAST];
//	      } else {
//	        SDL_SetCursor(pAnim.Cursors.MapScroll[SCROLL_EAST][0]);
//	      }
//	      scroll_dir = DIR8_EAST;
//	    }
//          } else {
//	    rect.x = rect.y = 0;
//            rect.w = Main.Map.map.w;
//            rect.h = SCROLL_MAP_AREA;
//	    if (is_in_rect_area(pMotionEvent.x, pMotionEvent.y, rect)) {
//	      is_map_scrolling = true;
//	      if (scroll_dir != DIR8_NORTH) {
//	        pStoreAnimCursor = pAnimCursor;
//		if (do_cursor_animation && pAnim.Cursors.MapScroll[SCROLL_NORTH][1]) {
//	  	  pAnimCursor = pAnim.Cursors.MapScroll[SCROLL_NORTH];
//	        } else {
//	          SDL_SetCursor(pAnim.Cursors.MapScroll[SCROLL_NORTH][0]);
//	        }
//	        scroll_dir = DIR8_NORTH;
//	      }
//            } else {
//              rect.y = Main.Map.map.h - SCROLL_MAP_AREA;
//	      if (is_in_rect_area(pMotionEvent.x, pMotionEvent.y, rect)) {
//	        is_map_scrolling = true;
//		if (scroll_dir != DIR8_SOUTH) {
//	          pStoreAnimCursor = pAnimCursor;
//		  if (do_cursor_animation && pAnim.Cursors.MapScroll[SCROLL_SOUTH][1]) {
//	  	    pAnimCursor = pAnim.Cursors.MapScroll[SCROLL_SOUTH];
//	          } else {
//	            SDL_SetCursor(pAnim.Cursors.MapScroll[SCROLL_SOUTH][0]);
//	          }
//	          scroll_dir = DIR8_SOUTH;
//		}
//              } else {
//	        if (is_map_scrolling) {
//	          if (pStoreAnimCursor) {
//		    pAnimCursor = pStoreAnimCursor;
//	          } else {
//		    SDL_SetCursor(pStd_Cursor);
//		    pAnimCursor = null;
//		  }
//	        }
//	        pStoreAnimCursor = null;
//	        is_map_scrolling = false;
//	      }
//	    } 
//	  }
//        }
//      }
//    }
//  }
//  
//  return ID_ERROR;
//}
//
///**************************************************************************
// this is called every TIMER_INTERVAL milliseconds whilst we are in 
// gui_main_loop() (which is all of the time) TIMER_INTERVAL needs to be .5s
//**************************************************************************/
//static void game_focused_unit_anim()
//{
//  static int flip;
//
//  if (get_client_state() == CLIENT_GAME_RUNNING_STATE) {
//
//    if (Game.game.player_ptr.is_connected && Game.game.player_ptr.is_alive
//	&& !Game.game.player_ptr.turn_done) {
//      int i, is_waiting, is_moving;
//
//      for (i = 0, is_waiting = 0, is_moving = 0; i < Game.game.nplayers; i++)
//	if (Game.game.players[i].is_alive && Game.game.players[i].is_connected) {
//	  if (Game.game.players[i].turn_done) {
//	    is_waiting++;
//	  } else {
//	    is_moving++;
//	  }
//	}
//
//      if (is_moving == 1 && is_waiting) {
//	update_turn_done_button(0);	/* stress the slow player! */
//      }
//    }
//
//    if(is_isometric && do_focus_animation && pAnim.num_tiles_focused_unit) {
//      real_blink_active_unit();
//    } else {
//      blink_active_unit();
//    }
//
//    if (flip) {
//      update_timeout_label();
//      if (seconds_to_turndone > 0) {
//	seconds_to_turndone--;
//      } else {
//	seconds_to_turndone = 0;
//      }
//    }
//
//    flip = !flip;
//  }
//    
//  return;
//}
//
//static void game_cursors_anim()
//{
//  static int cursor_anim_frame = 0;
//  if(do_cursor_animation && pAnimCursor && pAnimCursor[1]) {
//    SDL_SetCursor(pAnimCursor[cursor_anim_frame++]);
//    if (!pAnimCursor[cursor_anim_frame]) {
//      cursor_anim_frame = 0;
//    }
//  }
//}
//
//
///* ============================ Public ========================== */
//
///**************************************************************************
//...
//**************************************************************************/
//void add_autoconnect_to_timer()
//{
//  autoconnect = true;
//  pInfo_User_Event.user.code = TRY_AUTO_CONNECT;
//}
//
//void force_exit_from_event_loop()
//{
//  SDL_Event Event;
//  
//  Event.type = SDL_USEREVENT;
//  Event.user.code = EXIT_FROM_EVENT_LOOP;
//  Event.user.data1 = null;
//  Event.user.data2 = null;
//  
//  SDL_PushEvent(&Event);
//  
//}
//
///* This function may run in a separate event thread */
//int FilterMouseMotionEvents(final SDL_Event *event)
//{
//  if (event.type == SDL_MOUSEMOTION) {
//    static int x = 0, y = 0;
//    if((MOVE_STEP_X && (event.motion.x - x > MOVE_STEP_X
//      			|| x - event.motion.x > MOVE_STEP_X)) ||
//      (MOVE_STEP_Y && (event.motion.y - y > MOVE_STEP_Y
//    			|| y - event.motion.y > MOVE_STEP_Y))) {
//      x = event.motion.x;
//      y = event.motion.y;
//      return(1);    /* Catch it */
//    } else {
//      return(0);    /* Drop it, we've handled it */
//    }
//  }
//  return(1);
//}
//
///**************************************************************************
//...
//**************************************************************************/
//Uint16 gui_event_loop(void *pData,
//	void (*loop_action)(void *pData),
//	Uint16 (*key_down_handler)(SDL_keysym Key, void *pData),
//        Uint16 (*key_up_handler)(SDL_keysym Key, void *pData),
//	Uint16 (*mouse_button_down_handler)(SDL_MouseButtonEvent *pButtonEvent, void *pData),
//        Uint16 (*mouse_button_up_handler)(SDL_MouseButtonEvent *pButtonEvent, void *pData),
//        Uint16 (*mouse_motion_handler)(SDL_MouseMotionEvent *pMotionEvent, void *pData))
//{
//  Uint16 ID;
//  static struct timeval tv;
//  static fd_set civfdset;
//  Uint32 t1, t2, t3;
//  static int result, schot_nr = 0;
//  static char schot[32];
//
//  ID = ID_ERROR;
//  t3 = t1 = SDL_GetTicks();
//  while (ID == ID_ERROR) {
//    /* ========================================= */
//    /* net check with 10ms delay event loop */
//    if (net_socket >= 0) {
//      FD_ZERO(&civfdset);
//      FD_SET(net_socket, &civfdset);
//      tv.tv_sec = 0;
//      tv.tv_usec = 10000;/* 10ms*/
//    
//      result = select(net_socket + 1, &civfdset, null, null, &tv);
//      if(result < 0) {
//        if (errno != EINTR) {
//	  break;
//        } else {
//	  continue;
//        }
//      } else {
//        if (result > 0 && FD_ISSET(net_socket, &civfdset)) {
//	  SDL_PushEvent(pNet_User_Event);
//	}
//      }
//    } else { /* if connection is not establish */
//      SDL_Delay(10);
//    }
//    /* ========================================= */
//    
//    t2 = SDL_GetTicks();
//    if ((t2 - t1) > UNITS_TIMER_INTERVAL) {
//      if (widget_info_counter || autoconnect) {
//        if(widget_info_counter > 8) {
//          SDL_PushEvent(pInfo_User_Event);
//          widget_info_counter = 0;
//        } else {
//          widget_info_counter++;
//          SDL_PushEvent(pAnim_User_Event);
//        }
//      } else {
//        SDL_PushEvent(pAnim_User_Event);
//      }
//            
//      if (is_map_scrolling && (t2 - t3) > MAP_SCROLL_TIMER_INTERVAL) {
//	SDL_PushEvent(pMap_Scroll_User_Event);
//	t3 = SDL_GetTicks();
//      }
//      
//      t1 = SDL_GetTicks();
//    }
//    /* ========================================= */
//    
//    if(loop_action) {
//      loop_action(pData);
//    }
//    
//    /* ========================================= */
//    
//    while(SDL_PollEvent(&Main.event) == 1) {
//      switch (Main.event.type) {
//      case SDL_QUIT:
//	abort();
//      break;
//    
//      case SDL_KEYUP:
//        switch (Main.event.key.keysym.sym) {
//	  /* find if Shifts are released */
//	  case SDLK_RSHIFT:
//	    RSHIFT = false;
//	  break;
//	  case SDLK_LSHIFT:
//	    LSHIFT = false;
//	  break;
//	  case SDLK_LCTRL:
//	    LCTRL = false;
//	  break;
//	  case SDLK_RCTRL:
//	    RCTRL = false;
//	  break;
//	  case SDLK_LALT:
//	    LALT = false;
//	  break;
//	  default:
//	    if(key_up_handler) {
//	      ID = key_up_handler(Main.event.key.keysym, pData);
//	    }
//	  break;
//	}
//        break;
//      case SDL_KEYDOWN:
//	switch(Main.event.key.keysym.sym) {
//	  case SDLK_PRINT:
//	    util.freelog(Log.LOG_NORMAL, "Make screenshot nr. %d", schot_nr);
//	    schot = util.my_snprintf( "schot0%d.bmp", schot_nr++);
//	    SDL_SaveBMP(Main.screen, schot);
//	  break;
//	  
//  	  case SDLK_RSHIFT:
//	    /* Right Shift is Pressed */
//	    RSHIFT = true;
//	  break;
//	    
//	  case SDLK_LSHIFT:
//	    /* Left Shift is Pressed */
//	    LSHIFT = true;
//	  break;
//	    
//	  case SDLK_LCTRL:
//	    /* Left CTRL is Pressed */
//	    LCTRL = true;
//	  break;
//	   
//          case SDLK_RCTRL:
//	    /* Right CTRL is Pressed */
//	    RCTRL = true;
//	  break;
//	  
//	  case SDLK_LALT:
//	    /* Left ALT is Pressed */
//	    LALT = true;
//	  break;
//	  
//          default:
//	    if(key_down_handler) {
//	      ID = key_down_handler(Main.event.key.keysym, pData);
//	    }
//	  break;
//	}
//      break;
//      case SDL_MOUSEBUTTONDOWN:
//        if(mouse_button_down_handler) {
//	  ID = mouse_button_down_handler(&Main.event.button, pData);
//	}	
//      break;
//      case SDL_MOUSEBUTTONUP:
//	if(mouse_button_up_handler) {
//	  ID = mouse_button_up_handler(&Main.event.button, pData);
//	}
//      break;
//      case SDL_MOUSEMOTION:
//	if(mouse_motion_handler) {
//	  ID = mouse_motion_handler(&Main.event.motion, pData);
//	}	
//      break;
//      case SDL_USEREVENT:
//        switch(Main.event.user.code) {
//	  case NET:
//            input_from_server(net_socket);
//	  break;
//	  case ANIM:
//	    game_focused_unit_anim();
//	    game_cursors_anim();
//	  break;
//	  case SHOW_WIDGET_INFO_LABBEL:
//	    draw_widget_info_label();
//	  break;
//	  case TRY_AUTO_CONNECT:
//	    if (try_to_autoconnect()) {
//	      pInfo_User_Event.user.code = SHOW_WIDGET_INFO_LABBEL;
//	      autoconnect = false;
//	    }
//	  break;
//          case FLUSH:
//	    unqueue_flush();
//	  break;
//	  case MAP_SCROLL:
//	      scroll_mapview(scroll_dir);
//	  break;
//          case EXIT_FROM_EVENT_LOOP:
//	    return MAX_ID;
//	  default:
//	  break;
//        }    
//      break;
//	
//      }
//    }
//  }
//  
//  return ID;
//}
//
///* ============ Freeciv native Game.game function =========== */
//
///**************************************************************************
//  Do any necessary pre-initialization of the UI, if necessary.
//**************************************************************************/
//void ui_init()
//{
//  char device[20];
//  GUI pInit_String = null;
//  SDL_Surface *pBgd, *pTmp;
//  Uint32 iSDL_Flags;
//
//  init_character_encodings(INTERNAL_ENCODING);
//
//  SDL_Client_Flags = 0;
//  iSDL_Flags = SDL_INIT_VIDEO | SDL_INIT_NOPARACHUTE;
//  
//  /* auto center new windows in X enviroment */
//  putenv((char *)"SDL_VIDEO_CENTERED=yes");
//  
//  init_sdl(iSDL_Flags);
//  
//  util.freelog(Log.LOG_NORMAL, "Using Video Output: %s",
//	  SDL_VideoDriverName(device, sizeof(device)));
//  
//  /* create splash screen */  
//  pBgd = load_surf(datafilename("misc/intro.png"));
//  
//  if(pBgd && SDL_GetVideoInfo().wm_available) {
//    set_video_mode(pBgd.w, pBgd.h, SDL_SWSURFACE | SDL_ANYFORMAT | SDL_RESIZABLE);
//#if 0    
//    /*
//     * call this for other that X enviroments - currently not supported.
//     */
//    center_main_window_on_screen();
//#endif
//    SDL_BlitSurface(pBgd, null, Main.map, null);
//    putframe(Main.map, 0, 0, Main.Map.map.w - 1, Main.Map.map.h - 1,
//    			SDL_MapRGB(Main.Map.map.format, 255, 255, 255));
//    FREESURFACE(pBgd);
//    SDL_WM_SetCaption("SDLClient of Freeciv", "FreeCiv");
//  } else {
//    set_video_mode(640, 480, SDL_SWSURFACE | SDL_ANYFORMAT);
//    if(pBgd) {
//      blit_entire_src(pBgd, Main.map, (Main.Map.map.w - pBgd.w) / 2,
//    				      (Main.Map.map.h - pBgd.h) / 2);
//      FREESURFACE(pBgd);
//      SDL_Client_Flags |= CF_TOGGLED_FULLSCREEN;
//    } else {
//      SDL_FillRect(Main.map, null, SDL_MapRGB(Main.Map.map.format, 0, 0, 128));
//      SDL_WM_SetCaption("SDLClient of Freeciv", "FreeCiv");
//    }
//  }
//
//  /* create label beackground */
//  pTmp = create_surf(350, 50, SDL_SWSURFACE);
//  pBgd = SDL_DisplayFormatAlpha(pTmp);
//  FREESURFACE(pTmp);
//  
//  SDL_FillRect(pBgd, null, SDL_MapRGBA(pBgd.format, 255, 255, 255, 128));
//  putframe(pBgd, 0, 0, pBgd.w - 1, pBgd.h - 1, 0xFF000000);
//  SDL_SetAlpha(pBgd, 0x0, 0x0);
// 
//  
//  pInit_String = create_iconlabel(pBgd, Main.gui,
//	create_str16_from_char("Initializing Client", 20),
//				   WF_ICON_CENTER|WF_FREE_THEME);
//  pInit_String.string16.style |= SF_CENTER;
//  
//  draw_label(pInit_String,
//	     (Main.screen.w - pInit_String.size.w) / 2,
//	     (Main.screen.h - pInit_String.size.h) / 2);
//
//  flush_all();
//  
//  copy_chars_to_string16(pInit_String.string16,
//  			"Waiting for the beginning of the Game.game");
//  
//  init_gui_list(ID_WAITING_LABEL, pInit_String);
//  
//}
//
///**************************************************************************
//...
//**************************************************************************/
//static void clear_double_messages_call()
//{
//  int i;
//  /* clear double call */
//  for(i=0; i<E_LAST; i++) {
//    if (messages_where[i] & MW_MESSAGES)
//    {
//      messages_where[i] &= ~MW_OUTPUT;
//    }
//  }
//}
//
///**************************************************************************
//  The main loop for the UI.  This is called from main(), and when it
//  exits the client will exit.
//**************************************************************************/
//void ui_main(int argc, char *argv[])
//{
//  SDL_Event __Net_User_Event;
//  SDL_Event __Anim_User_Event;
//  SDL_Event __Info_User_Event;
//  SDL_Event __Flush_User_Event;
//  SDL_Event __pMap_Scroll_User_Event;
//  
//  parse_options(argc, argv);
//  
//  __Net_User_Event.type = SDL_USEREVENT;
//  __Net_User_Event.user.code = NET;
//  __Net_User_Event.user.data1 = null;
//  __Net_User_Event.user.data2 = null;
//  pNet_User_Event = &__Net_User_Event;
//
//  __Anim_User_Event.type = SDL_USEREVENT;
//  __Anim_User_Event.user.code = EVENT_ERROR;
//  __Anim_User_Event.user.data1 = null;
//  __Anim_User_Event.user.data2 = null;
//  pAnim_User_Event = &__Anim_User_Event;
//  
//  __Info_User_Event.type = SDL_USEREVENT;
//  __Info_User_Event.user.code = SHOW_WIDGET_INFO_LABBEL;
//  __Info_User_Event.user.data1 = null;
//  __Info_User_Event.user.data2 = null;
//  pInfo_User_Event = &__Info_User_Event;
//
//  __Flush_User_Event.type = SDL_USEREVENT;
//  __Flush_User_Event.user.code = FLUSH;
//  __Flush_User_Event.user.data1 = null;
//  __Flush_User_Event.user.data2 = null;
//  pFlush_User_Event = &__Flush_User_Event;
//
//  __pMap_Scroll_User_Event.type = SDL_USEREVENT;
//  __pMap_Scroll_User_Event.user.code = MAP_SCROLL;
//  __pMap_Scroll_User_Event.user.data1 = null;
//  __pMap_Scroll_User_Event.user.data2 = null;
//  pMap_Scroll_User_Event = &__pMap_Scroll_User_Event;
//  
//  smooth_move_unit_steps = 8;
//  update_city_text_in_refresh_tile = false;
//  draw_city_names = false;
//  draw_city_productions = false;
//  is_unit_move_blocked = false;
//  SDL_Client_Flags |= (CF_DRAW_PLAYERS_NEUTRAL_STATUS|
//  		       CF_DRAW_PLAYERS_WAR_STATUS|
//                       CF_DRAW_PLAYERS_CEASEFIRE_STATUS|
//                       CF_DRAW_PLAYERS_PEACE_STATUS|
//                       CF_DRAW_PLAYERS_ALLIANCE_STATUS|
//		       CF_CIV3_CITY_TEXT_STYLE|
//		       CF_DRAW_MAP_DITHER);
//		       
//  tilespec_load_tiles();
//  
//  load_cursors();
//  tilespec_setup_theme();
//  tilespec_setup_anim();
//  tilespec_setup_city_icons();
//  finish_loading_sprites();
//      
//  clear_double_messages_call();
//    
//  create_units_order_widgets();
//
//  setup_auxiliary_tech_icons();
//  unload_unused_graphics();
//  
//  if((SDL_Client_Flags & CF_TOGGLED_FULLSCREEN) == CF_TOGGLED_FULLSCREEN) {
//    set_video_mode(800, 600, SDL_SWSURFACE | SDL_ANYFORMAT | SDL_FULLSCREEN);
//    SDL_Client_Flags &= ~CF_TOGGLED_FULLSCREEN;
//  } else {
//    set_video_mode(640, 480, SDL_SWSURFACE | SDL_ANYFORMAT | SDL_RESIZABLE);
//#if 0    
//    /*
//     * call this for other that X enviroments - currently not supported.
//     */
//    center_main_window_on_screen();
//#endif
//  }
//    
//  /* SDL_WM_SetCaption("SDLClient of Freeciv", "FreeCiv"); */
//  
//  draw_intro_gfx();
//
//  mapview_canvas.tile_width = (mapview_canvas.width - 1)
//	  / NORMAL_TILE_WIDTH + 1;
//  mapview_canvas.tile_height = (mapview_canvas.height - 1)
//	  / NORMAL_TILE_HEIGHT + 1;
//
//  flush_all();
//
//  /* this need correct Main.screen size */
//  Init_MapView();
//  init_options_button();
//  set_new_order_widgets_dest_buffers();
//  
//  set_client_state(CLIENT_PRE_GAME_STATE);
//
//  /* Main Game.game loop */
//  gui_event_loop(null, null, main_key_down_handler, main_key_up_handler,
//  		 main_mouse_button_down_handler, null,
//		 main_mouse_motion_handler);
//
//  free_auxiliary_tech_icons();
//  tilespec_unload_theme();
//  tilespec_free_city_icons();
//  tilespec_free_anim();
//  unload_cursors();
//  free_font_system();
//  
//  quit_sdl();
//}
//
///**************************************************************************
//  Make a bell noise (beep).  This provides low-level sound alerts even
//  if there is no real sound support.
//**************************************************************************/
//void sound_bell()
//{
//  util.freelog(Log.LOG_DEBUG, "sound_bell : PORT ME");
//}
//
///**************************************************************************
//  Show Focused Unit Animation.
//**************************************************************************/
//void enable_focus_animation()
//{
//  pAnim_User_Event.user.code = ANIM;
//  SDL_Client_Flags |= CF_FOCUS_ANIMATION;
//  rebuild_focus_anim_frames();
//}
//
///**************************************************************************
//  Don't show Focused Unit Animation.
//**************************************************************************/
//void disable_focus_animation()
//{
//  pAnim_User_Event.user.code = EVENT_ERROR;
//  SDL_Client_Flags &= ~CF_FOCUS_ANIMATION;
//}
//
///**************************************************************************
//  Wait for data on the given socket.  Call input_from_server() when data
//  is ready to be read.
//**************************************************************************/
//void add_net_input(int sock)
//{
//  util.freelog(Log.LOG_DEBUG, "Connection UP (%d)", sock);
//  net_socket = sock;
//  autoconnect = false;
//  enable_focus_animation();
//  SDL_Client_Flags |= CF_REVOLUTION; /* force update revolution icon */
//}
//
///**************************************************************************
//  Stop waiting for any server network data.  See add_net_input().
//**************************************************************************/
//void remove_net_input()
//{
//  net_socket = (-1);
//  util.freelog(Log.LOG_DEBUG, "Connection DOWN... ");
//  disable_focus_animation();
//  draw_goto_patrol_lines = false;
//  if (pAnimCursor) {
//    SDL_SetCursor(pStd_Cursor);
//    pAnimCursor = null;
//    pStoreAnimCursor = null;
//  }
//}
}