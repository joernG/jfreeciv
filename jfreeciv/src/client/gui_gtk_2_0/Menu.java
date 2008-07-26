package client.gui_gtk_2_0;

public class Menu{

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
//#include <stdio.h>
//#include <stdlib.h>
//#include <string.h>
//
//#include <gtk/gtk.h>
//
//#include "astring.h"
//#include "fcintl.h"
//#include "log.h"
//#include "government.h"
//#include "map.h"
//#include "mem.h"
//#include "support.h"
//#include "unit.h"
//
//#include "chatline.h"
//#include "cityrep.h"
//#include "civclient.h"
//#include "clinet.h"
//#include "connectdlg_common.h"
//#include "connectdlg.h"
//#include "control.h"
//#include "dialogs.h"
//#include "finddlg.h"
//#include "gotodlg.h"
//#include "graphics.h"
//#include "gui_main.h"
//#include "gui_stuff.h"
//#include "helpdlg.h"
//#include "mapctrl.h"   /* center_on_unit */
//#include "messagedlg.h"
//#include "messagewin.h"
//#include "optiondlg.h"
//#include "options.h"
//#include "packhand.h"
//#include "pages.h"
//#include "plrdlg.h"
//#include "ratesdlg.h"
//#include "repodlgs.h"
//#include "spaceshipdlg.h"
//#include "wldlg.h"
//
//#include "menu.h"
//
//static GtkItemFactory *item_factory = null;
//static GtkWidget *main_menubar = null;
//GtkAccelGroup *toplevel_accel = null;
//static enum unit_activity road_activity;
//
//static void menus_rename(final String path, final String s);
//
///****************************************************************
//...
//*****************************************************************/
//enum MenuID {
//  MENU_END_OF_LIST=0,
//
//  MENU_GAME_OPTIONS,
//  MENU_GAME_MSG_OPTIONS,
//  MENU_GAME_SAVE_SETTINGS,
//  MENU_GAME_SERVER_OPTIONS1,
//  MENU_GAME_SERVER_OPTIONS2,
//  MENU_GAME_SAVE_GAME,
//  MENU_GAME_SAVE_QUICK, 
//  MENU_GAME_OUTPUT_LOG,
//  MENU_GAME_CLEAR_OUTPUT,
//  MENU_GAME_LEAVE,
//  MENU_GAME_QUIT,
//  
//  MENU_GOVERNMENT_TAX_RATE,
//  MENU_GOVERNMENT_FIND_CITY,
//  MENU_GOVERNMENT_WORKLISTS,
//  MENU_GOVERNMENT_REVOLUTION,
//
//  MENU_VIEW_SHOW_MAP_GRID,
//  MENU_VIEW_SHOW_NATIONAL_BORDERS,
//  MENU_VIEW_SHOW_CITY_NAMES,
//  MENU_VIEW_SHOW_CITY_GROWTH_TURNS,
//  MENU_VIEW_SHOW_CITY_PRODUCTIONS,
//  MENU_VIEW_SHOW_TERRAIN,
//  MENU_VIEW_SHOW_COASTLINE,
//  MENU_VIEW_SHOW_ROADS_RAILS,
//  MENU_VIEW_SHOW_IRRIGATION,
//  MENU_VIEW_SHOW_MINES,
//  MENU_VIEW_SHOW_FORTRESTerrain_H.S_AIRBASE,
//  MENU_VIEW_SHOW_SPECIALS,
//  MENU_VIEW_SHOW_POLLUTION,
//  MENU_VIEW_SHOW_CITIES,
//  MENU_VIEW_SHOW_UNITS,
//  MENU_VIEW_SHOW_FOCUS_UNIT,
//  MENU_VIEW_SHOW_FOG_OF_WAR,
//  MENU_VIEW_CENTER_VIEW,
//
//  MENU_ORDER_BUILD_CITY,     /* shared with BUILD_WONDER */
//  MENU_ORDER_ROAD,           /* shared with TRADEROUTE */
//  MENU_ORDER_IRRIGATE,
//  MENU_ORDER_MINE,
//  MENU_ORDER_TRANSFORM,
//  MENU_ORDER_FORTRESS,       /* shared with FORTIFY */
//  MENU_ORDER_AIRBASE,
//  MENU_ORDER_POLLUTION,      /* shared with PARADROP */
//  MENU_ORDER_FALLOUT,
//  MENU_ORDER_SENTRY,
//  MENU_ORDER_PILLAGE,
//  MENU_ORDER_HOMECITY,
//  MENU_ORDER_UNLOAD_TRANSPORTER,
//  MENU_ORDER_LOAD,
//  MENU_ORDER_UNLOAD,
//  MENU_ORDER_WAKEUP_OTHERS,
//  MENU_ORDER_AUTO_SETTLER,   /* shared with AUTO_ATTACK */
//  MENU_ORDER_AUTO_EXPLORE,
//  MENU_ORDER_CONNECT_ROAD,
//  MENU_ORDER_CONNECT_RAIL,
//  MENU_ORDER_CONNECT_IRRIGATE,
//  MENU_ORDER_PATROL,
//  MENU_ORDER_GOTO,
//  MENU_ORDER_GOTO_CITY,
//  MENU_ORDER_RETURN,
//  MENU_ORDER_DISBAND,
//  MENU_ORDER_DIPLOMAT_DLG,
//  MENU_ORDER_NUKE,
//  MENU_ORDER_WAIT,
//  MENU_ORDER_DONE,
//
//  MENU_REPORT_CITIES,
//  MENU_REPORT_UNITS,
//  MENU_REPORT_PLAYERS,
//  MENU_REPORT_ECONOMY,
//  MENU_REPORT_SCIENCE,
//  MENU_REPORT_WOW,
//  MENU_REPORT_TOP_CITIES,
//  MENU_REPORT_MESSAGES,
//  MENU_REPORT_DEMOGRAPHIC,
//  MENU_REPORT_SPACESHIP,
//
//  MENU_HELP_LANGUAGES,
//  MENU_HELP_CONNECTING,
//  MENU_HELP_CONTROLS,
//  MENU_HELP_CHATLINE,
//  MENU_HELP_WORKLIST_EDITOR,
//  MENU_HELP_CMA,
//  MENU_HELP_PLAYING,
//  MENU_HELP_IMPROVEMENTS,
//  MENU_HELP_UNITS,
//  MENU_HELP_COMBAT,
//  MENU_HELP_ZOC,
//  MENU_HELP_TECH,
//  MENU_HELP_TERRAIN,
//  MENU_HELP_WONDERS,
//  MENU_HELP_GOVERNMENT,
//  MENU_HELP_HAPPINESS,
//  MENU_HELP_SPACE_RACE,
//  MENU_HELP_COPYING,
//  MENU_HELP_ABOUT
//};
//
//
///****************************************************************
//  This is the response callback for the dialog with the message:
//  Leaving a local game will end it!
//****************************************************************/
//static void leave_local_game_response(GtkWidget* dialog, gint response)
//{
//  gtk_widget_destroy(dialog);
//  if (response == GTK_RESPONSE_OK) {
//    /* It might be killed already */
//    if (aconnection.used) {
//      /* It will also kill the server */
//      disconnect_from_server();
//    }
//  }
//}
//
///****************************************************************
//  ...
// *****************************************************************/
//static void game_menu_callback(gpointer callback_data,
//    guint callback_action, GtkWidget *widget)
//{
//  switch(callback_action) {
//  case MENU_GAME_OPTIONS:
//    popup_option_dialog();
//    break;
//  case MENU_GAME_MSG_OPTIONS:
//    popup_messageopt_dialog();
//    break;
//  case MENU_GAME_SAVE_SETTINGS:
//    save_options();
//    break;
//  case MENU_GAME_SERVER_OPTIONS1:
//    send_report_request(REPORT_SERVER_OPTIONS1);
//    break;
//  case MENU_GAME_SERVER_OPTIONS2:
//    send_report_request(REPORT_SERVER_OPTIONS2);
//    break;
//  case MENU_GAME_SAVE_GAME:
//    popup_save_dialog();
//    break;
//  case MENU_GAME_SAVE_QUICK:
//    send_save_game(null);
//    break;
//  case MENU_GAME_OUTPUT_LOG:
//    log_output_window();
//    break;
//  case MENU_GAME_CLEAR_OUTPUT:
//    clear_output_window();
//    break;
//  case MENU_GAME_LEAVE:
//    if (is_server_running()) {
//      GtkWidget* dialog = gtk_message_dialog_new(null,
//	  0,
//	  GTK_MESSAGE_WARNING,
//	  GTK_BUTTONS_OK_CANCEL,
//	  "Leaving a local game will end it!");
//      setup_dialog(dialog, toplevel);
//      gtk_window_set_position(GTK_WINDOW(dialog), GTK_WIN_POS_MOUSE);
//      g_signal_connect(dialog, "response", 
//	  G_CALLBACK(leave_local_game_response), null);
//      gtk_window_present(GTK_WINDOW(dialog));
//    } else {
//      disconnect_from_server();
//    }
//    break;
//  case MENU_GAME_QUIT:
//    popup_quit_dialog();
//    break;
//  }
//}
//
//
///****************************************************************
//...
//*****************************************************************/
//static void government_menu_callback(gpointer callback_data,
//				  guint callback_action, GtkWidget *widget)
//{
//  switch(callback_action) {
//  case MENU_GOVERNMENT_TAX_RATE:
//    popup_rates_dialog();
//    break;
//  case MENU_GOVERNMENT_FIND_CITY:
//    popup_find_dialog();
//    break;
//  case MENU_GOVERNMENT_WORKLISTS:
//    popup_worklists_report();
//    break;
//  case MENU_GOVERNMENT_REVOLUTION:
//    popup_revolution_dialog(-1);
//    break;
//  }
//}
//
//
//static void menus_set_sensitive(final String, int);
///****************************************************************
//...
//*****************************************************************/
//static void view_menu_callback(gpointer callback_data, guint callback_action,
//			       GtkWidget *widget)
//{
//  switch(callback_action) {
//  case MENU_VIEW_SHOW_MAP_GRID:
//    if (draw_map_grid ^ GTK_CHECK_MENU_ITEM(widget).active)
//      key_map_grid_toggle();
//    break;
//  case MENU_VIEW_SHOW_NATIONAL_BORDERS:
//    if (draw_borders ^ GTK_CHECK_MENU_ITEM(widget).active) {
//      key_map_borders_toggle();
//    }
//    break;
//  case MENU_VIEW_SHOW_CITY_NAMES:
//    if (draw_city_names ^ GTK_CHECK_MENU_ITEM(widget).active) {
//      key_city_names_toggle();
//      menus_set_sensitive("<main>/_View/City G_rowth", draw_city_names);
//    }
//    break;
//  case MENU_VIEW_SHOW_CITY_GROWTH_TURNS:
//    if (draw_city_growth ^ GTK_CHECK_MENU_ITEM(widget).active)
//      key_city_growth_toggle();
//    break;
//  case MENU_VIEW_SHOW_CITY_PRODUCTIONS:
//    if (draw_city_productions ^ GTK_CHECK_MENU_ITEM(widget).active)
//      key_city_productions_toggle();
//    break;
//  case MENU_VIEW_SHOW_TERRAIN:
//    if (draw_terrain ^ GTK_CHECK_MENU_ITEM(widget).active) {
//      key_terrain_toggle();
//      menus_set_sensitive("<main>/View/Coastline", !draw_terrain);
//    }
//    break;
//  case MENU_VIEW_SHOW_COASTLINE:
//    if (draw_coastline ^ GTK_CHECK_MENU_ITEM(widget).active)
//      key_coastline_toggle();
//    break;
//  case MENU_VIEW_SHOW_ROADS_RAILS:
//    if (draw_roads_rails ^ GTK_CHECK_MENU_ITEM(widget).active)
//      key_roads_rails_toggle();
//    break;
//  case MENU_VIEW_SHOW_IRRIGATION:
//    if (draw_irrigation ^ GTK_CHECK_MENU_ITEM(widget).active)
//      key_irrigation_toggle();
//    break;
//  case MENU_VIEW_SHOW_MINES:
//    if (draw_mines ^ GTK_CHECK_MENU_ITEM(widget).active)
//      key_mines_toggle();
//    break;
//  case MENU_VIEW_SHOW_FORTRESTerrain_H.S_AIRBASE:
//    if (draw_fortress_airbase ^ GTK_CHECK_MENU_ITEM(widget).active)
//      key_fortress_airbase_toggle();
//    break;
//  case MENU_VIEW_SHOW_SPECIALS:
//    if (draw_specials ^ GTK_CHECK_MENU_ITEM(widget).active)
//      key_specials_toggle();
//    break;
//  case MENU_VIEW_SHOW_POLLUTION:
//    if (draw_pollution ^ GTK_CHECK_MENU_ITEM(widget).active)
//      key_pollution_toggle();
//    break;
//  case MENU_VIEW_SHOW_CITIES:
//    if (draw_cities ^ GTK_CHECK_MENU_ITEM(widget).active)
//      key_cities_toggle();
//    break;
//  case MENU_VIEW_SHOW_UNITS:
//    if (draw_units ^ GTK_CHECK_MENU_ITEM(widget).active) {
//      key_units_toggle();
//      menus_set_sensitive("<main>/View/Focus Unit", !draw_units);
//    }
//    break;
//  case MENU_VIEW_SHOW_FOCUS_UNIT:
//    if (draw_focus_unit ^ GTK_CHECK_MENU_ITEM(widget).active)
//      key_focus_unit_toggle();
//    break;
//  case MENU_VIEW_SHOW_FOG_OF_WAR:
//    if (draw_fog_of_war ^ GTK_CHECK_MENU_ITEM(widget).active)
//      key_fog_of_war_toggle();
//    break;
//  case MENU_VIEW_CENTER_VIEW:
//    center_on_unit();
//    break;
//  }
//}
//
//
///****************************************************************
//...
//*****************************************************************/
//static void orders_menu_callback(gpointer callback_data,
//				 guint callback_action, GtkWidget *widget)
//{
//  switch(callback_action) {
//   case MENU_ORDER_BUILD_CITY:
//    if (get_unit_in_focus()) {
//      unit punit = get_unit_in_focus();
//      /* Enable the button for adding to a city in all cases, so we
//	 get an eventual error message from the server if we try. */
//      if (can_unit_add_or_build_city(punit)) {
//	key_unit_build_city();
//      } else {
//	key_unit_build_wonder();
//      }
//    }
//    break;
//   case MENU_ORDER_ROAD:
//    if (get_unit_in_focus()) {
//      if (unit_can_est_traderoute_here(get_unit_in_focus()))
//	key_unit_traderoute();
//      else
//	key_unit_road();
//    }
//    break;
//   case MENU_ORDER_IRRIGATE:
//    key_unit_irrigate();
//    break;
//   case MENU_ORDER_MINE:
//    key_unit_mine();
//    break;
//   case MENU_ORDER_TRANSFORM:
//    key_unit_transform();
//    break;
//   case MENU_ORDER_FORTRESS:
//    if (get_unit_in_focus()) {
//      if (can_unit_do_activity(get_unit_in_focus(), ACTIVITY_FORTRESS))
//	key_unit_fortress();
//      else
//	key_unit_fortify();
//    }
//    break;
//   case MENU_ORDER_AIRBASE:
//    key_unit_airbase(); 
//    break;
//   case MENU_ORDER_POLLUTION:
//    if (get_unit_in_focus()) {
//      if (can_unit_paradrop(get_unit_in_focus()))
//	key_unit_paradrop();
//      else
//	key_unit_pollution();
//    }
//    break;
//   case MENU_ORDER_FALLOUT:
//    key_unit_fallout();
//    break;
//   case MENU_ORDER_SENTRY:
//    key_unit_sentry();
//    break;
//   case MENU_ORDER_PILLAGE:
//    key_unit_pillage();
//    break;
//   case MENU_ORDER_HOMECITY:
//    key_unit_homecity();
//    break;
//   case MENU_ORDER_UNLOAD_TRANSPORTER:
//    key_unit_unload_all();
//    break;
//  case MENU_ORDER_LOAD:
//    request_unit_load(get_unit_in_focus(), null);
//    break;
//  case MENU_ORDER_UNLOAD:
//    request_unit_unload(get_unit_in_focus());
//    break;
//   case MENU_ORDER_WAKEUP_OTHERS:
//    key_unit_wakeup_others();
//    break;
//   case MENU_ORDER_AUTO_SETTLER:
//    if(get_unit_in_focus())
//      request_unit_auto(get_unit_in_focus());
//    break;
//   case MENU_ORDER_AUTO_EXPLORE:
//    key_unit_auto_explore();
//    break;
//   case MENU_ORDER_CONNECT_ROAD:
//    key_unit_connect(ACTIVITY_ROAD);
//    break;
//   case MENU_ORDER_CONNECT_RAIL:
//    key_unit_connect(ACTIVITY_RAILROAD);
//    break;
//   case MENU_ORDER_CONNECT_IRRIGATE:
//    key_unit_connect(ACTIVITY_IRRIGATE);
//    break;
//   case MENU_ORDER_PATROL:
//    key_unit_patrol();
//    break;
//   case MENU_ORDER_GOTO:
//    key_unit_goto();
//    break;
//   case MENU_ORDER_GOTO_CITY:
//    if(get_unit_in_focus())
//      popup_goto_dialog();
//    break;
//   case MENU_ORDER_RETURN:
//    if (get_unit_in_focus()) {
//      request_unit_return(get_unit_in_focus());
//    }
//    break;
//   case MENU_ORDER_DISBAND:
//    key_unit_disband();
//    break;
//   case MENU_ORDER_DIPLOMAT_DLG:
//    key_unit_diplomat_actions();
//    break;
//   case MENU_ORDER_NUKE:
//    key_unit_nuke();
//    break;
//   case MENU_ORDER_WAIT:
//    key_unit_wait();
//    break;
//   case MENU_ORDER_DONE:
//    key_unit_done();
//    break;
//  }
//}
//
//
///****************************************************************
//...
//*****************************************************************/
//static void reports_menu_callback(gpointer callback_data,
//				  guint callback_action, GtkWidget *widget)
//{
//  switch(callback_action) {
//   case MENU_REPORT_CITIES:
//    raise_city_report_dialog();
//    break;
//   case MENU_REPORT_UNITS:
//    raise_activeunits_report_dialog();
//    break;
//  case MENU_REPORT_PLAYERS:
//    raise_players_dialog();
//    break;
//   case MENU_REPORT_ECONOMY:
//    raise_economy_report_dialog();
//    break;
//   case MENU_REPORT_SCIENCE:
//    raise_science_dialog();
//    break;
//   case MENU_REPORT_WOW:
//    send_report_request(REPORT_WONDERS_OF_THE_WORLD);
//    break;
//   case MENU_REPORT_TOP_CITIES:
//    send_report_request(REPORT_TOP_5_CITIES);
//    break;
//  case MENU_REPORT_MESSAGES:
//    raise_meswin_dialog();
//    break;
//   case MENU_REPORT_DEMOGRAPHIC:
//    send_report_request(REPORT_DEMOGRAPHIC);
//    break;
//   case MENU_REPORT_SPACESHIP:
//    popup_spaceship_dialog(Game.game.player_ptr);
//    break;
//  }
//}
//
//
///****************************************************************
//...
//*****************************************************************/
//static void help_menu_callback(gpointer callback_data,
//			       guint callback_action, GtkWidget *widget)
//{
//  switch(callback_action) {
//  case MENU_HELP_LANGUAGES:
//    popup_help_dialog_string(HELP_LANGUAGES_ITEM);
//    break;
//  case MENU_HELP_CONNECTING:
//    popup_help_dialog_string(HELP_CONNECTING_ITEM);
//    break;
//  case MENU_HELP_CONTROLS:
//    popup_help_dialog_string(HELP_CONTROLS_ITEM);
//    break;
//  case MENU_HELP_CHATLINE:
//    popup_help_dialog_string(HELP_CHATLINE_ITEM);
//    break;
//  case MENU_HELP_WORKLIST_EDITOR:
//    popup_help_dialog_string(HELP_WORKLIST_EDITOR_ITEM);
//    break;
//  case MENU_HELP_CMA:
//    popup_help_dialog_string(HELP_CMA_ITEM);
//    break;
//  case MENU_HELP_PLAYING:
//    popup_help_dialog_string(HELP_PLAYING_ITEM);
//    break;
//  case MENU_HELP_IMPROVEMENTS:
//    popup_help_dialog_string(HELP_IMPROVEMENTS_ITEM);
//    break;
//  case MENU_HELP_UNITS:
//    popup_help_dialog_string(HELP_UNITS_ITEM);
//    break;
//  case MENU_HELP_COMBAT:
//    popup_help_dialog_string(HELP_COMBAT_ITEM);
//    break;
//  case MENU_HELP_ZOC:
//    popup_help_dialog_string(HELP_ZOC_ITEM);
//    break;
//  case MENU_HELP_TECH:
//    popup_help_dialog_string(HELP_TECHS_ITEM);
//    break;
//  case MENU_HELP_TERRAIN:
//    popup_help_dialog_string(HELP_TERRAIN_ITEM);
//    break;
//  case MENU_HELP_WONDERS:
//    popup_help_dialog_string(HELP_WONDERS_ITEM);
//    break;
//  case MENU_HELP_GOVERNMENT:
//    popup_help_dialog_string(HELP_GOVERNMENT_ITEM);
//    break;
//  case MENU_HELP_HAPPINESS:
//    popup_help_dialog_string(HELP_HAPPINESS_ITEM);
//    break;
//  case MENU_HELP_SPACE_RACE:
//    popup_help_dialog_string(HELP_SPACE_RACE_ITEM);
//    break;
//  case MENU_HELP_COPYING:
//    popup_help_dialog_string(HELP_COPYING_ITEM);
//    break;
//  case MENU_HELP_ABOUT:
//    popup_help_dialog_string(HELP_ABOUT_ITEM);
//    break;
//  }
//}
//
///* This is the GtkItemFactoryEntry structure used to generate new menus.
//          Item 1: The menu path. The letter after the underscore indicates an
//                  accelerator key once the menu is open.
//          Item 2: The accelerator key for the entry
//          Item 3: The callback function.
//          Item 4: The callback action.  This changes the parameters with
//                  which the function is called.  The default is 0.
//          Item 5: The item type, used to define what kind of an item it is.
//                  Here are the possible values:
//
//                  null               . "<Item>" +
//                  ""                 . "<Item>" +
//                  "<Title>"          . create a title item
//                  "<Item>"           . create a simple item
//                  "<CheckItem>"      . create a check item
//                  "<ToggleItem>"     . create a toggle item
//                  "<RadioItem>"      . create a radio item
//                  <path>             . path of a radio item to link against
//                  "<Separator>"      . create a separator
//                  "<Branch>"         . create an item to hold sub items
//                  "<LastBranch>"     . create a right justified branch 
//
//Important: The underscore is NOT just for show (see Item 1 above)!
//           At the top level, use with "Alt" key to open the menu.
//	   Some less often used commands in the Order menu are not underscored
//	   due to possible conflicts.
//*/
//static GtkItemFactoryEntry menu_items[]	=
//{
//  /* Game menu ... */
//  { "/" N"_Game",					null,
//	null,			0,					"<Branch>"	},
//  { "/" N"Game" "/tearoff1",				null,
//	null,			0,					"<Tearoff>"	},
//  { "/" N"Game" "/" N"Local _Options",		null,
//	game_menu_callback,	MENU_GAME_OPTIONS					},
//  { "/" N"Game" "/" N"_Message Options",		null,
//	game_menu_callback,	MENU_GAME_MSG_OPTIONS					},
//  { "/" N"Game" "/" N"Sa_ve Settings",		null,
//	game_menu_callback,	MENU_GAME_SAVE_SETTINGS					},
//  { "/" N"Game" "/sep2",				null,
//	null,			0,					"<Separator>"	},
//  { "/" N"Game" "/" N"_Initial Server Options",null,
//	game_menu_callback,	MENU_GAME_SERVER_OPTIONS1				},
//  { "/" N"Game" "/" N"Server O_ptions",	null,
//	game_menu_callback,	MENU_GAME_SERVER_OPTIONS2				},
//  { "/" N"Game" "/sep3",				null,
//	null,			0,					"<Separator>"	},
//  { "/" N"Game" "/" N"_Save Game",		null,
//	game_menu_callback,	MENU_GAME_SAVE_QUICK, 			"<StockItem>",
//	GTK_STOCK_SAVE									},
//  { "/" N"Game" "/" N"Save Game _As...",		null,
//	game_menu_callback,	MENU_GAME_SAVE_GAME,			"<StockItem>",
//	GTK_STOCK_SAVE_AS								},
//  { "/" N"Game" "/sep4",				null,
//	null,			0,					"<Separator>"	},
//  { "/" N"Game" "/" N"E_xport Log",		null,
//	game_menu_callback,	MENU_GAME_OUTPUT_LOG					},
//  { "/" N"Game" "/" N"Clear _Log",		null,
//	game_menu_callback,	MENU_GAME_CLEAR_OUTPUT					},
//  { "/" N"Game" "/sep6",				null,
//	null,			0,					"<Separator>"	},
//  { "/" N"Game" "/" N"L_eave",		null,
//	game_menu_callback,	MENU_GAME_LEAVE					},
//  { "/" N"Game" "/" N"_Quit",			null,
//	game_menu_callback,	MENU_GAME_QUIT,				"<StockItem>",
//	GTK_STOCK_QUIT									},
//  /* Government menu ... */
//  { "/" N"Gover_nment",					null,
//	null,			0,					"<Branch>"	},
//  { "/" N"Government" "/tearoff1",			null,
//	null,			0,					"<Tearoff>"	},
//  { "/" N"Government" "/" N"_Tax Rates",		"<shift>t",
//	government_menu_callback,	MENU_GOVERNMENT_TAX_RATE			},
//  { "/" N"Government" "/sep1",				null,
//	null,			0,					"<Separator>"	},
//  { "/" N"Government" "/" N"_Find City",		"<shift>f",
//	government_menu_callback,	MENU_GOVERNMENT_FIND_CITY			},
//  { "/" N"Government" "/" N"_Worklists",		"<control>l",
//	government_menu_callback,	MENU_GOVERNMENT_WORKLISTS			},
//  { "/" N"Government" "/sep2",				null,
//	null,			0,					"<Separator>"	},
//  { "/" N"Government" "/" N"_Change Government",           null,
//	null,			0,					"<Branch>"	},
//  { "/" N"Government" "/" N"_Change Government" "/" N"_Revolution...",
//                                                        "<shift>r",
//	government_menu_callback,	MENU_GOVERNMENT_REVOLUTION			},
//  { "/" N"_Government" "/" N"_Change Government" "/sep1", null,
//	null,			0,					"<Separator>"	},
//  /* View menu ... */
//  { "/" N"_View",					null,
//	null,			0,					"<Branch>"	},
//  { "/" N"View" "/tearoff1",				null,
//	null,			0,					"<Tearoff>"	},
//  { "/" N"View" "/" N"Map _Grid",			"<control>g",
//	view_menu_callback,	MENU_VIEW_SHOW_MAP_GRID,		"<CheckItem>"	},
//  { "/" N"View" "/" N"National _Borders",		"<control>b",
//	view_menu_callback,	MENU_VIEW_SHOW_NATIONAL_BORDERS,	"<CheckItem>"	},
//  { "/" N"View" "/" N"City _Names",		"<control>n",
//	view_menu_callback,	MENU_VIEW_SHOW_CITY_NAMES,		"<CheckItem>"	},
//  { "/" N"View" "/" N"City G_rowth",		"<control>r",
//	view_menu_callback,	MENU_VIEW_SHOW_CITY_GROWTH_TURNS,
//	"<CheckItem>"	},
//  { "/" N"View" "/" N"City _Productions",		"<control>p",
//	view_menu_callback,	MENU_VIEW_SHOW_CITY_PRODUCTIONS,	"<CheckItem>"	},
//  { "/" N"View" "/sep1",				null,
//	null,			0,					"<Separator>"	},
//  { "/" N"View" "/" N"Terrain",                   null,
//        view_menu_callback,     MENU_VIEW_SHOW_TERRAIN,	                "<CheckItem>"   },
//  { "/" N"View" "/" N"Coastline",	                null,
//        view_menu_callback,     MENU_VIEW_SHOW_COASTLINE,       	"<CheckItem>"   },
//  { "/" N"View" "/" N"Improvements",		null,
//	null,			0,					"<Branch>"	},
//  { "/" N"View" "/" N"Improvements" "/tearoff1",	null,
//	null,			0,					"<Tearoff>"	},
//  { "/" N"View" "/" N"Improvements" "/" N"Roads & Rails", null,
//	view_menu_callback,	MENU_VIEW_SHOW_ROADS_RAILS,		"<CheckItem>"	},
//  { "/" N"View" "/" N"Improvements" "/" N"Irrigation", null,
//	view_menu_callback,	MENU_VIEW_SHOW_IRRIGATION,		"<CheckItem>"	},
//  { "/" N"View" "/" N"Improvements" "/" N"Mines",	null,
//	view_menu_callback,	MENU_VIEW_SHOW_MINES,			"<CheckItem>"	},
//  { "/" N"View" "/" N"Improvements" "/" N"Fortress & Airbase", null,
//	view_menu_callback,	MENU_VIEW_SHOW_FORTRESTerrain_H.S_AIRBASE,	"<CheckItem>"	},
//  { "/" N"View" "/" N"Specials",			null,
//	view_menu_callback,	MENU_VIEW_SHOW_SPECIALS,		"<CheckItem>"	},
//  { "/" N"View" "/" N"Pollution & Fallout",	null,
//	view_menu_callback,	MENU_VIEW_SHOW_POLLUTION,		"<CheckItem>"	},
//  { "/" N"View" "/" N"Cities",			null,
//	view_menu_callback,	MENU_VIEW_SHOW_CITIES,			"<CheckItem>"	},
//  { "/" N"View" "/" N"Units",			null,
//	view_menu_callback,	MENU_VIEW_SHOW_UNITS,			"<CheckItem>"	},
//  { "/" N"View" "/" N"Focus Unit",		null,
//	view_menu_callback,	MENU_VIEW_SHOW_FOCUS_UNIT,		"<CheckItem>"	},
//  { "/" N"View" "/" N"Fog of War",		null,
//	view_menu_callback,	MENU_VIEW_SHOW_FOG_OF_WAR,		"<CheckItem>"	},
//  { "/" N"View" "/sep2",				null,
//	null,			0,					"<Separator>"	},
//  { "/" N"View" "/" N"_Center View",		"c",
//	view_menu_callback,	MENU_VIEW_CENTER_VIEW					},
//  /* Orders menu ... */
//  { "/" N"_Orders",					null,
//	null,			0,					"<Branch>"	},
//  { "/" N"Orders" "/tearoff1",			null,
//	null,			0,					"<Tearoff>"	},
//  { "/" N"Orders" "/" N"_Build City",		"b",
//	orders_menu_callback,	MENU_ORDER_BUILD_CITY					},
//  { "/" N"Orders" "/" N"Build _Road",		"r",
//	orders_menu_callback,	MENU_ORDER_ROAD						},
//  { "/" N"Orders" "/" N"Build _Irrigation",	"i",
//	orders_menu_callback,	MENU_ORDER_IRRIGATE					},
//  { "/" N"Orders" "/" N"Build _Mine",		"m",
//	orders_menu_callback,	MENU_ORDER_MINE						},
//  { "/" N"Orders" "/" N"Transf_orm Terrain",	"o",
//	orders_menu_callback,	MENU_ORDER_TRANSFORM					},
//  { "/" N"Orders" "/" N"Build _Fortress",		"f",
//	orders_menu_callback,	MENU_ORDER_FORTRESS					},
//  { "/" N"Orders" "/" N"Build Airbas_e",		"e",
//	orders_menu_callback,	MENU_ORDER_AIRBASE					},
//  { "/" N"Orders" "/" N"Clean _Pollution",	"p",
//	orders_menu_callback,	MENU_ORDER_POLLUTION					},
//  { "/" N"Orders" "/" N"Clean _Nuclear Fallout",	"n",
//	orders_menu_callback,	MENU_ORDER_FALLOUT					},
//  { "/" N"Orders" "/sep1",			null,
//	null,			0,					"<Separator>"	},
//  { "/" N"Orders" "/" N"_Sentry",			"s",
//	orders_menu_callback,	MENU_ORDER_SENTRY					},
//  { "/" N"Orders" "/" N"Pillage",		        "<shift>p",
//	orders_menu_callback,	MENU_ORDER_PILLAGE					},
//  { "/" N"Orders" "/sep2",				null,
//	null,			0,					"<Separator>"	},
//  { "/" N"Orders" "/" N"Make _Homecity",		"h",
//	orders_menu_callback,	MENU_ORDER_HOMECITY					},
//  { "/" N"Orders" "/" N"_Load",			"l",
//    orders_menu_callback, MENU_ORDER_LOAD},
//  { "/" N"Orders" "/" N"_Unload Transporter",	"<shift>u",
//	orders_menu_callback,	MENU_ORDER_UNLOAD_TRANSPORTER					},
//  { "/" N"Orders" "/" N"_Unload",			"u",
//    orders_menu_callback, MENU_ORDER_UNLOAD},
//  { "/" N"Orders" "/" N"Wake up o_thers",		"<shift>w",
//	orders_menu_callback,	MENU_ORDER_WAKEUP_OTHERS				},
//  { "/" N"Orders" "/sep3",				null,
//	null,			0,					"<Separator>"	},
//  { "/" N"Orders" "/" N"_Auto Settler",		"a",
//	orders_menu_callback,	MENU_ORDER_AUTO_SETTLER					},
//  { "/" N"Orders" "/" N"Auto E_xplore",		"x",
//	orders_menu_callback,	MENU_ORDER_AUTO_EXPLORE					},
//  {"/" N"Orders" "/" N"_Connect" "/" N"_Road", "<ctrl><shift>r",
//   orders_menu_callback, MENU_ORDER_CONNECT_ROAD},
//  {"/" N"Orders" "/" N"_Connect" "/" N"Rai_l", "<ctrl><shift>l",
//   orders_menu_callback, MENU_ORDER_CONNECT_RAIL},
//  {"/" N"Orders" "/" N"_Connect" "/" N"_Irrigate", "<ctrl><shift>i",
//   orders_menu_callback, MENU_ORDER_CONNECT_IRRIGATE},
//  { "/" N"Orders" "/" N"Patrol (_Q)",		"q",
//	orders_menu_callback,	MENU_ORDER_PATROL					},
//  { "/" N"Orders" "/" N"_Go to",			"g",
//	orders_menu_callback,	MENU_ORDER_GOTO						},
//  { "/" N"Orders" "/" N"Go\\/Airlift to City",	"<shift>l",
//	orders_menu_callback,	MENU_ORDER_GOTO_CITY					},
//  { "/" N"Orders" "/" N"Return to nearest city",	"<shift>g",
//	orders_menu_callback,	MENU_ORDER_RETURN },
//  { "/" N"Orders" "/sep4",				null,
//	null,			0,					"<Separator>"	},
//  { "/" N"Orders" "/" N"Disband Unit",		"<shift>d",
//	orders_menu_callback,	MENU_ORDER_DISBAND					},
//  { "/" N"Orders" "/" N"Diplomat\\/Spy Actions",	"d",
//	orders_menu_callback,	MENU_ORDER_DIPLOMAT_DLG					},
//  { "/" N"Orders" "/" N"Explode Nuclear",        "<shift>n",
//	orders_menu_callback,	MENU_ORDER_NUKE						},
//  { "/" N"Orders" "/sep5",				null,
//	null,			0,					"<Separator>"	},
//  { "/" N"Orders" "/" N"_Wait",			"w",
//	orders_menu_callback,	MENU_ORDER_WAIT						},
//  { "/" N"Orders" "/" N"Done",			"space",
//	orders_menu_callback,	MENU_ORDER_DONE						},
//  /* Reports menu ... */
//  { "/" N"_Reports",					null,
//	null,			0,					"<Branch>"	},
//  { "/" N"Reports" "/tearoff1",			null,
//	null,			0,					"<Tearoff>"	},
//  { "/" N"Reports" "/" N"_Cities",		"F1",
//	reports_menu_callback,	MENU_REPORT_CITIES					},
//  { "/" N"Reports" "/" N"_Units",			"F2",
//	reports_menu_callback,	MENU_REPORT_UNITS					},
//  { "/" N"Reports" "/" N"_Players",		"F3",
//	reports_menu_callback,	MENU_REPORT_PLAYERS					},
//  { "/" N"Reports" "/" N"_Economy",		"F5",
//	reports_menu_callback,	MENU_REPORT_ECONOMY					},
//  { "/" N"Reports" "/" N"_Science",		"F6",
//	reports_menu_callback,	MENU_REPORT_SCIENCE					},
//  { "/" N"Reports" "/sep1",				null,
//	null,			0,					"<Separator>"	},
//  { "/" N"Reports" "/" N"_Wonders of the World",	"F7",
//	reports_menu_callback,	MENU_REPORT_WOW						},
//  { "/" N"Reports" "/" N"_Top Five Cities",	"F8",
//	reports_menu_callback,	MENU_REPORT_TOP_CITIES					},
//  { "/" N"Reports" "/" N"_Messages",		"F9",
//	reports_menu_callback,	MENU_REPORT_MESSAGES					},
//  { "/" N"Reports" "/" N"_Demographics",		"F11",
//	reports_menu_callback,	MENU_REPORT_DEMOGRAPHIC					},
//  { "/" N"Reports" "/" N"S_paceship",		"F12",
//	reports_menu_callback,	MENU_REPORT_SPACESHIP					},
//  /* Help menu ... */
//  { "/" N"_Help",					null,
//	null,			0,					"<Branch>"	},
//  { "/" N"Help" "/tearoff1",				null,
//	null,			0,					"<Tearoff>"	},
//  { "/" N"Help" "/" N"Language_s",		null,
//	help_menu_callback,	MENU_HELP_LANGUAGES					},
//  { "/" N"Help" "/" N"Co_nnecting",		null,
//	help_menu_callback,	MENU_HELP_CONNECTING					},
//  { "/" N"Help" "/" N"C_ontrols",			null,
//	help_menu_callback,	MENU_HELP_CONTROLS					},
//  { "/" N"Help" "/" N"C_hatline",			null,
//	help_menu_callback,	MENU_HELP_CHATLINE					},
//  { "/" N"Help" "/" N"_Worklist Editor",			null,
//	help_menu_callback,	MENU_HELP_WORKLIST_EDITOR				},
//  { "/" N"Help" "/" N"Citizen _Management",			null,
//	help_menu_callback,	MENU_HELP_CMA						},
//  { "/" N"Help" "/" N"_Playing",			null,
//	help_menu_callback,	MENU_HELP_PLAYING					},
//  { "/" N"Help" "/sep1",				null,
//	null,			0,					"<Separator>"	},
//  { "/" N"Help" "/" N"City _Improvements",        null,
//	help_menu_callback,	MENU_HELP_IMPROVEMENTS					},
//  { "/" N"Help" "/" N"_Units",			null,
//	help_menu_callback,	MENU_HELP_UNITS						},
//  { "/" N"Help" "/" N"Com_bat",			null,
//	help_menu_callback,	MENU_HELP_COMBAT					},
//  { "/" N"Help" "/" N"_ZOC",			null,
//	help_menu_callback,	MENU_HELP_ZOC						},
//  { "/" N"Help" "/" N"Techno_logy",		null,
//	help_menu_callback,	MENU_HELP_TECH						},
//  { "/" N"Help" "/" N"_Terrain",			null,
//	help_menu_callback,	MENU_HELP_TERRAIN					},
//  { "/" N"Help" "/" N"Won_ders",			null,
//	help_menu_callback,	MENU_HELP_WONDERS					},
//  { "/" N"Help" "/" N"_Government",		null,
//	help_menu_callback,	MENU_HELP_GOVERNMENT					},
//  { "/" N"Help" "/" N"Happin_ess",		null,
//	help_menu_callback,	MENU_HELP_HAPPINESS					},
//  { "/" N"Help" "/" N"Space _Race",		null,
//	help_menu_callback,	MENU_HELP_SPACE_RACE					},
//  { "/" N"Help" "/sep2",				null,
//	null,			0,					"<Separator>"	},
//  { "/" N"Help" "/" N"_Copying",			null,
//	help_menu_callback,	MENU_HELP_COPYING					},
//  { "/" N"Help" "/" N"_About",			null,
//	help_menu_callback,	MENU_HELP_ABOUT						}
//};
//
//#ifdef ENABLE_NLS
///****************************************************************
//  gettext-translates each "/" delimited component of menu path,
//  puts them back together, and returns as a static string.
//  Any component which is of form "<foo>" is _not_ translated.
//
//  Path should include underscores like in the menu itself.
//*****************************************************************/
//static char *menu_path_tok(char *path)
//{
//  boolean escaped = false;
//
//  while (*path) {
//    if (escaped) {
//      escaped = false;
//    } else {
//      if (*path == '\\') {
//        escaped = true;
//      } else if (*path == '/') {
//        *path = '\0';
//        return path;
//      }
//    }
//
//    path++;
//  }
//
//  return null;
//}
//#endif
//
///****************************************************************
//...
//*****************************************************************/
//static gchar *translate_func(final gchar *path, gpointer data)
//{
//#ifndef ENABLE_NLS
//    static gchar res[100];
//    
//    g_strlcpy(res, path, sizeof(res));
//#else
//    static struct astring in, out, tmp;   /* these are never free'd */
//    char *tok, *next, *t;
//    final String trn;
//    int len;
//    char *res;
//
//    /* copy to in so can modify with menu_path_tok: */
//    astr_minsize(&in, path.length()+1);
//    strcpy(in.str, path);
//    astr_minsize(&out, 1);
//    out.str[0] = '\0';
//    util.freelog(Log.LOG_DEBUG, "trans: %s", in.str);
//
//    tok = in.str;
//    do {
//      next = menu_path_tok(tok);
//
//      len = tok.length();
//      util.freelog(Log.LOG_DEBUG, "tok \"%s\", len %d", tok, len);
//      if (len == 0 || (tok[0] == '<' && tok[len-1] == '>')) {
//	t = tok;
//      } else {
//	trn = _(tok);
//	len = trn.length() + 1;	/* string plus leading '/' */
//	astr_minsize(&tmp, len+1);
//	sprintf(tmp.str, "/%s", trn);
//	t = tmp.str;
//	len = t.length();
//      }
//      astr_minsize(&out, out.n + len);
//      strcat(out.str, t);
//      util.freelog(Log.LOG_DEBUG, "t \"%s\", len %d, out \"%s\"", t, len, out.str);
//      tok = next+1;
//    } while (next);
//    res = out.str;
//#endif
//  
//  return res;
//}
//
///****************************************************************
//...
//*****************************************************************/
//static final String menu_path_remove_uline(final String path)
//{
//  static char res[100];
//  final String from;
//  char *to;
//  
//  from = path;
//  to = res;
//
//  do {
//    if (*from != '_') {
//      *(to++) = *from;
//    }
//  } while (*(from++));
//
//  return res;
//}
//
///****************************************************************
//  ...
// *****************************************************************/
//void setup_menus(GtkWidget *window, GtkWidget **menubar)
//{
//  final int nmenu_items = ARRAY_SIZE(menu_items);
//
//  toplevel_accel = gtk_accel_group_new();
//  item_factory = gtk_item_factory_new(GTK_TYPE_MENU_BAR, "<main>",
//      toplevel_accel);
//  gtk_item_factory_set_translate_func(item_factory, translate_func,
//      null, null);
//
//  gtk_accel_group_lock(toplevel_accel);
//  gtk_item_factory_create_items(item_factory, nmenu_items, menu_items, null);
//  gtk_window_add_accel_group(GTK_WINDOW(window), toplevel_accel);
//
//  main_menubar = gtk_item_factory_get_widget(item_factory, "<main>");
//  g_signal_connect(main_menubar, "destroy",
//      G_CALLBACK(gtk_widget_destroyed), &main_menubar);
//
//  if (menubar) {
//    *menubar = main_menubar;
//  }
//}
//
///****************************************************************
//...
//*****************************************************************/
//static void menus_set_sensitive(final String path, int sensitive)
//{
//  GtkWidget *item;
//
//  path = menu_path_remove_uline(path);
//
//  if(!(item = gtk_item_factory_get_item(item_factory, path))) {
//    util.freelog(Log.LOG_ERROR,
//	    "Can't set sensitivity for non-existent menu %s.", path);
//    return;
//  }
//
//  gtk_widget_set_sensitive(item, sensitive);
//}
//
///****************************************************************
//...
//*****************************************************************/
//static void menus_set_active(final String path, int active)
//{
//  GtkWidget *item;
//
//  path = menu_path_remove_uline(path);
//
//  if (!(item = gtk_item_factory_get_item(item_factory, path))) {
//    util.freelog(Log.LOG_ERROR,
//	    "Can't set active for non-existent menu %s.", path);
//    return;
//  }
//
//  gtk_check_menu_item_set_active(GTK_CHECK_MENU_ITEM(item), active);
//}
//
//#ifdef UNUSED 
///****************************************************************
//...
//*****************************************************************/
//static void menus_set_shown(final String path, int shown)
//{
//  GtkWidget *item;
//  
//  path = menu_path_remove_uline(path);
//  
//  if(!(item = gtk_item_factory_get_item(item_factory, path))) {
//    util.freelog(Log.LOG_ERROR, "Can't show non-existent menu %s.", path);
//    return;
//  }
//
//  if (shown) {
//    gtk_widget_show(item);
//  } else {
//    gtk_widget_hide(item);
//  }
//}
//#endif /* UNUSED */
//
///****************************************************************
//...
//*****************************************************************/
//static void menus_rename(final String path, final String s)
//{
//  GtkWidget *item;
//
//  path = menu_path_remove_uline(path);
//
//  if(!(item = gtk_item_factory_get_item(item_factory, path))) {
//    util.freelog(Log.LOG_ERROR, "Can't rename non-existent menu %s.", path);
//    return;
//  }
//
//  gtk_label_set_text_with_mnemonic(GTK_LABEL(GTK_BIN(item).child), s);
//}
//
///****************************************************************
//  The player has chosen a government from the menu.
//*****************************************************************/
//static void government_callback(GtkMenuItem *item, gpointer data)
//{
//  popup_revolution_dialog(GPOINTER_TO_INT(data));
//}
//
///****************************************************************************
//  Return the text for the tile, changed by the activity.
//
//  Should only be called for irrigation, mining, or transformation, and
//  only when the activity changes the base terrain type.
//****************************************************************************/
//static final String get_tile_change_menu_text(tile ptile,
//					     enum unit_activity activity)
//{
//  int old_terrain = ptile.terrain;
//  enum int old_special = ptile.special;
//  tile_type ptype = get_tile_type(ptile.terrain);
//  final String text;
//
//  /* Change the terrain manually to avoid any side effects. */
//  switch (activity) {
//  case ACTIVITY_IRRIGATE:
//    assert(ptype.irrigation_result != ptile.terrain
//	   && ptype.irrigation_result != Terrain_H.T_NONE);
//    map_irrigate_tile(ptile);
//    break;
//
//  case ACTIVITY_MINE:
//    assert(ptype.mining_result != ptile.terrain
//	   && ptype.mining_result != Terrain_H.T_NONE);
//    map_mine_tile(ptile);
//    break;
//
//  case ACTIVITY_TRANSFORM:
//    assert(ptype.transform_result != ptile.terrain
//	   && ptype.transform_result != Terrain_H.T_NONE);
//    map_transform_tile(ptile);
//    break;
//
//  default:
//    assert(0!=1);
//    return "-";
//  }
//
//  text = map_get_tile_info_text(ptile);
//
//  /* Restore the original state of the tile. */
//  ptile.terrain = old_terrain;
//  ptile.special = old_special;
//  Map.reset_move_costs(ptile);
//
//  return text;
//}
//
///****************************************************************
//Note: the menu strings should contain underscores as in the
//menu_items struct. The underscores will be removed elsewhere if
//the string is used for a lookup via gtk_item_factory_get_widget()
//*****************************************************************/
//void update_menus()
//{
//  if (!main_menubar) {
//    return;
//  }
//
//  menus_set_sensitive("<main>/_Game/Save Game _As...",
//		      can_client_access_hack()
//		      && get_client_state() >= CLIENT_GAME_RUNNING_STATE);
//  menus_set_sensitive("<main>/_Game/_Save Game", can_client_access_hack()
//		      && get_client_state() >= CLIENT_GAME_RUNNING_STATE);
//  menus_set_sensitive("<main>/_Game/Server O_ptions", 
//		      aconnection.established);
//  menus_set_sensitive("<main>/_Game/_Initial Server Options", 
//		      get_client_state() >= CLIENT_GAME_RUNNING_STATE);
//  menus_set_sensitive("<main>/_Game/L_eave", aconnection.established);
//
//  if (!can_client_change_view()) {
//    menus_set_sensitive("<main>/_Reports", false);
//    menus_set_sensitive("<main>/_Government", false);
//    menus_set_sensitive("<main>/_View", false);
//    menus_set_sensitive("<main>/_Orders", false);
//  } else {
//    unit punit;
//    final String path =
//      menu_path_remove_uline("<main>/_Government/_Change Government");
//    GtkWidget *parent = gtk_item_factory_get_widget(item_factory, path);
//
//    if (parent) {
//      GList *list, *iter;
//
//      /* remove previous government entries. */
//      list = gtk_container_get_children(GTK_CONTAINER(parent));
//      for (iter = g_list_nth(list, 2); iter; iter = g_list_next(iter)) {
//	gtk_widget_destroy(GTK_WIDGET(iter.data));
//      }
//      g_list_free(list);
//
//      /* add new government entries. */
//      government_iterate(g) {
//        if (g.index != Game.game.government_when_anarchy) {
//          GtkWidget *item, *image;
//          Sprite gsprite;
//	  char buf[256];
//
//	  buf = util.my_snprintf( "%s...", g.name);
//          item = gtk_image_menu_item_new_with_label(buf);
//
//	  if ((gsprite = g.sprite)) {
//	    image = gtk_image_new_from_pixmap(gsprite.pixmap, gsprite.mask);
//	    gtk_image_menu_item_set_image(GTK_IMAGE_MENU_ITEM(item), image);
//	    gtk_widget_show(image);
//	  }
//
//          g_signal_connect(item, "activate",
//            G_CALLBACK(government_callback), GINT_TO_POINTER(g.index));
//
//          if (!can_change_to_government(Game.game.player_ptr, g.index)) {
//            gtk_widget_set_sensitive(item, false);
//	  }
//
//          gtk_menu_shell_append(GTK_MENU_SHELL(parent), item);
//          gtk_widget_show(item);
//        }
//      } government_iterate_end;
//    }
//
//    menus_set_sensitive("<main>/_Reports", true);
//    menus_set_sensitive("<main>/_Government", true);
//    menus_set_sensitive("<main>/_View", true);
//    menus_set_sensitive("<main>/_Orders", can_client_issue_orders());
//
//    menus_set_sensitive("<main>/_Government/_Tax Rates",
//			Game.game.rgame.changable_tax
//                        && can_client_issue_orders());
//    menus_set_sensitive("<main>/_Government/_Worklists",
//			can_client_issue_orders());
//    menus_set_sensitive("<main>/_Government/_Change Government",
//			can_client_issue_orders());
//
//    menus_set_sensitive("<main>/_Reports/S_paceship",
//			(Game.game.player_ptr.spaceship.state!=spaceship_state.SSHIP_NONE));
//
//    menus_set_active("<main>/_View/Map _Grid", draw_map_grid);
//    menus_set_sensitive("<main>/_View/National _Borders", Game.game.borders > 0);
//    menus_set_active("<main>/_View/National _Borders", draw_borders);
//    menus_set_active("<main>/_View/City _Names", draw_city_names);
//    menus_set_sensitive("<main>/_View/City G_rowth", draw_city_names);
//    menus_set_active("<main>/_View/City G_rowth", draw_city_growth);
//    menus_set_active("<main>/_View/City _Productions", draw_city_productions);
//    menus_set_active("<main>/_View/Terrain", draw_terrain);
//    menus_set_active("<main>/_View/Coastline", draw_coastline);
//    menus_set_sensitive("<main>/_View/Coastline", !draw_terrain);
//    menus_set_active("<main>/_View/Improvements/Roads & Rails", draw_roads_rails);
//    menus_set_active("<main>/_View/Improvements/Irrigation", draw_irrigation);
//    menus_set_active("<main>/_View/Improvements/Mines", draw_mines);
//    menus_set_active("<main>/_View/Improvements/Fortress & Airbase", draw_fortress_airbase);
//    menus_set_active("<main>/_View/Specials", draw_specials);
//    menus_set_active("<main>/_View/Pollution & Fallout", draw_pollution);
//    menus_set_active("<main>/_View/Cities", draw_cities);
//    menus_set_active("<main>/_View/Units", draw_units);
//    menus_set_active("<main>/_View/Focus Unit", draw_focus_unit);
//    menus_set_sensitive("<main>/_View/Focus Unit", !draw_units);
//    menus_set_active("<main>/_View/Fog of War", draw_fog_of_war);
//
//    /* Remaining part of this function: Update Orders menu */
//
//    if (!can_client_issue_orders()) {
//      return;
//    }
//
//    if((punit=get_unit_in_focus())) {
//      final String irrfmt = "Change to %s (_I)";
//      final String minfmt = "Change to %s (_M)";
//      final String transfmt = "Transf_orm to %s";
//      char irrtext[128], mintext[128], transtext[128];
//      final String roadtext;
//      int  ttype;
//      tile_type       tinfo;
//
//      irrtext = String.format( "Build _Irrigation");
//      mintext = String.format( "Build _Mine");
//      transtext = String.format( "Transf_orm Terrain");
//      
//      /* Enable the button for adding to a city in all cases, so we
//	 get an eventual error message from the server if we try. */
//      menus_set_sensitive("<main>/_Orders/_Build City",
//			  can_unit_add_or_build_city(punit) ||
//			  unit_can_help_build_wonder_here(punit));
//      menus_set_sensitive("<main>/_Orders/Build _Road",
//                          (can_unit_do_activity(punit, ACTIVITY_ROAD) ||
//                           can_unit_do_activity(punit, ACTIVITY_RAILROAD) ||
//                           unit_can_est_traderoute_here(punit)));
//      menus_set_sensitive("<main>/_Orders/Build _Irrigation",
//                          can_unit_do_activity(punit, ACTIVITY_IRRIGATE));
//      menus_set_sensitive("<main>/_Orders/Build _Mine",
//                          can_unit_do_activity(punit, ACTIVITY_MINE));
//      menus_set_sensitive("<main>/_Orders/Transf_orm Terrain",
//			  can_unit_do_activity(punit, ACTIVITY_TRANSFORM));
//      menus_set_sensitive("<main>/_Orders/Build _Fortress",
//                          (can_unit_do_activity(punit, ACTIVITY_FORTRESS) ||
//                           can_unit_do_activity(punit, ACTIVITY_FORTIFYING)));
//      menus_set_sensitive("<main>/_Orders/Build Airbas_e",
//			  can_unit_do_activity(punit, ACTIVITY_AIRBASE));
//      menus_set_sensitive("<main>/_Orders/Clean _Pollution",
//                          (can_unit_do_activity(punit, ACTIVITY_POLLUTION) ||
//                           can_unit_paradrop(punit)));
//      menus_set_sensitive("<main>/_Orders/Clean _Nuclear Fallout",
//			  can_unit_do_activity(punit, ACTIVITY_FALLOUT));
//      menus_set_sensitive("<main>/_Orders/_Sentry",
//			  can_unit_do_activity(punit, ACTIVITY_SENTRY));
//      menus_set_sensitive("<main>/_Orders/Pillage",
//			  can_unit_do_activity(punit, ACTIVITY_PILLAGE));
//      menus_set_sensitive("<main>/_Orders/_Disband Unit",
//			  !unit_flag(punit, F_UNDISBANDABLE));
//      menus_set_sensitive("<main>/_Orders/Make _Homecity",
//			  can_unit_change_homecity(punit));
//      menus_set_sensitive("<main>/_Orders/_Unload Transporter",
//			  get_transporter_occupancy(punit) > 0);
//      menus_set_sensitive("<main>/_Orders/_Load",
//	can_unit_load(punit, find_transporter_for_unit(punit,
//						       punit.tile)));
//      menus_set_sensitive("<main>/_Orders/_Unload",
//	(can_unit_unload(punit, Game.find_unit_by_id(punit.transported_by))
//	 && can_unit_exist_at_tile(punit, punit.tile)));
//      menus_set_sensitive("<main>/_Orders/Wake up o_thers", 
//			  is_unit_activity_on_tile(ACTIVITY_SENTRY,
//                                                   punit.tile));
//      menus_set_sensitive("<main>/_Orders/_Auto Settler",
//                          can_unit_do_auto(punit));
//      menus_set_sensitive("<main>/_Orders/Auto E_xplore",
//                          can_unit_do_activity(punit, ACTIVITY_EXPLORE));
//      menus_set_sensitive("<main>/_Orders/_Connect/_Road",
//                          can_unit_do_connect(punit, ACTIVITY_ROAD));
//      menus_set_sensitive("<main>/_Orders/_Connect/_Rail",
//                          can_unit_do_connect(punit, ACTIVITY_RAILROAD));
//      menus_set_sensitive("<main>/_Orders/_Connect/_Irrigate",
//                          can_unit_do_connect(punit, ACTIVITY_IRRIGATE));
//      menus_set_sensitive("<main>/_Orders/Return to nearest city",
//			  !(is_air_unit(punit) || is_heli_unit(punit)));
//      menus_set_sensitive("<main>/_Orders/Diplomat\\/Spy Actions",
//                          (is_diplomat_unit(punit)
//                           && diplomat_can_do_action(punit, DIPLOMAT_ANY_ACTION,
//						     punit.tile)));
//      menus_set_sensitive("<main>/_Orders/Explode Nuclear",
//			  unit_flag(punit, F_NUCLEAR));
//      if (unit_flag(punit, F_HELP_WONDER))
//	menus_rename("<main>/_Orders/_Build City", "Help _Build Wonder");
//      else if (unit_flag(punit, Eunit_flag_id.F_CITIES)) {
//	if (Map.map_get_city(punit.tile))
//	  menus_rename("<main>/_Orders/_Build City", "Add to City (_B)");
//	else
//	  menus_rename("<main>/_Orders/_Build City", "_Build City");
//      }
//      else 
//	menus_rename("<main>/_Orders/_Build City", "_Build City");
// 
//      if (unit_flag(punit, F_TRADE_ROUTE))
//	menus_rename("<main>/_Orders/Build _Road", "Make Trade _Route");
//      else if (unit_flag(punit, Eunit_flag_id.F_SETTLERS)) {
//	if (Map.map_has_special(punit.tile, Terrain_H.S_ROAD)) {
//	  roadtext = "Build _Railroad";
//	  road_activity=ACTIVITY_RAILROAD;  
//	} 
//	else {
//	  roadtext = "Build _Road";
//	  road_activity=ACTIVITY_ROAD;  
//	}
//	menus_rename("<main>/_Orders/Build _Road", roadtext);
//      }
//      else
//	menus_rename("<main>/_Orders/Build _Road", "Build _Road");
//
//      ttype = punit.tile.terrain;
//      tinfo = get_tile_type(ttype);
//      if (tinfo.irrigation_result != Terrain_H.T_NONE
//	  && tinfo.irrigation_result != ttype) {
//	irrtext = util.my_snprintf( irrfmt,
//		    get_tile_change_menu_text(punit.tile,
//					      ACTIVITY_IRRIGATE));
//      } else if (Map.map_has_special(punit.tile, S_IRRIGATION)
//		 && Player_P.player_knows_techs_with_flag(Game.game.player_ptr,
//						 TF_FARMLAND)) {
//	irrtext = String.format( "Bu_ild Farmland");
//      }
//      if (tinfo.mining_result != Terrain_H.T_NONE
//	  && tinfo.mining_result != ttype) {
//	mintext = util.my_snprintf( minfmt,
//		    get_tile_change_menu_text(punit.tile, ACTIVITY_MINE));
//      }
//      if (tinfo.transform_result != Terrain_H.T_NONE
//	  && tinfo.transform_result != ttype) {
//	transtext = util.my_snprintf( transfmt,
//		    get_tile_change_menu_text(punit.tile,
//					      ACTIVITY_TRANSFORM));
//      }
//
//      menus_rename("<main>/_Orders/Build _Irrigation", irrtext);
//      menus_rename("<main>/_Orders/Build _Mine", mintext);
//      menus_rename("<main>/_Orders/Transf_orm Terrain", transtext);
//
//      if (can_unit_do_activity(punit, ACTIVITY_FORTIFYING)) {
//	menus_rename("<main>/_Orders/Build _Fortress", "_Fortify");
//      } else {
//	menus_rename("<main>/_Orders/Build _Fortress", "Build _Fortress");
//      }
//
//      if (unit_flag(punit, F_PARATROOPERS)) {
//	menus_rename("<main>/_Orders/Clean _Pollution", "_Paradrop");
//      } else {
//	menus_rename("<main>/_Orders/Clean _Pollution", "Clean _Pollution");
//      }
//
//      if (!unit_flag(punit, Eunit_flag_id.F_SETTLERS)) {
//	menus_rename("<main>/_Orders/_Auto Settler", "_Auto Attack");
//      } else {
//	menus_rename("<main>/_Orders/_Auto Settler", "_Auto Settler");
//      }
//
//      menus_set_sensitive("<main>/_Orders", true);
//    } else {
//      menus_set_sensitive("<main>/_Orders", false);
//    }
//  }
//}
}