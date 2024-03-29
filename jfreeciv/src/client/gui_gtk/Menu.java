package client.gui_gtk;

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
//#include "government.h"
//#include "log.h"
//#include "Map.map.h"
//#include "mem.h"
//#include "support.h"
//#include "unit.h"
//
//#include "civclient.h"
//#include "clinet.h"
//#include "control.h"
//#include "options.h"
//#include "packhand.h"
//
//#include "chatline.h"
//#include "cityrep.h"
//#include "dialogs.h"
//#include "finddlg.h"
//#include "gotodlg.h"
//#include "gui_stuff.h"
//#include "helpdlg.h"
//#include "mapctrl.h"		/* center_on_unit */
//#include "messagedlg.h"
//#include "messagewin.h"
//#include "optiondlg.h"
//#include "plrdlg.h"
//#include "ratesdlg.h"
//#include "repodlgs.h"
//#include "spaceshipdlg.h"
//#include "wldlg.h"
//
//#include "menu.h"
//
//static GtkItemFactory *item_factory = null;
//static enum unit_activity road_activity;
//
//static void menus_rename(final String path, char *s);
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
//  MENU_GAME_OUTPUT_LOG,
//  MENU_GAME_CLEAR_OUTPUT,
//  MENU_GAME_DISCONNECT,
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
//...
//*****************************************************************/
//static void game_menu_callback(gpointer callback_data,
//			       guint callback_action, GtkWidget *widget)
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
//  case MENU_GAME_OUTPUT_LOG:
//    log_output_window();
//    break;
//  case MENU_GAME_CLEAR_OUTPUT:
//    clear_output_window();
//    break;
//  case MENU_GAME_DISCONNECT:
//    disconnect_from_server();
//    break;
//  case MENU_GAME_QUIT:
//    exit(EXIT_SUCCESS);
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
//    popup_worklists_report(Game.game.player_ptr);
//    break;
//  case MENU_GOVERNMENT_REVOLUTION:
//    popup_revolution_dialog();
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
//  case MENU_ORDER_UNLOAD_TRANSPORTER:
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
//    popup_city_report_dialog(0);
//    break;
//   case MENU_REPORT_UNITS:
//    popup_activeunits_report_dialog(0);
//    break;
//  case MENU_REPORT_PLAYERS:
//    popup_players_dialog();
//    break;
//   case MENU_REPORT_ECONOMY:
//    popup_economy_report_dialog(0);
//    break;
//   case MENU_REPORT_SCIENCE:
//    popup_science_dialog(0);
//    break;
//   case MENU_REPORT_WOW:
//    send_report_request(REPORT_WONDERS_OF_THE_WORLD);
//    break;
//   case MENU_REPORT_TOP_CITIES:
//    send_report_request(REPORT_TOP_5_CITIES);
//    break;
//  case MENU_REPORT_MESSAGES:
//    popup_meswin_dialog();
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
//  { "/" N"_Game" "/tearoff1",			null,
//	null,			0,					"<Tearoff>"	},
//  { "/" N"_Game" "/sep1",				null,
//	null,			0,					"<Separator>"	},
//  { "/" N"_Game" "/" N"_Local Options",		null,
//	game_menu_callback,	MENU_GAME_OPTIONS					},
//  { "/" N"_Game" "/" N"Messa_ge Options",		null,
//	game_menu_callback,	MENU_GAME_MSG_OPTIONS					},
//  { "/" N"_Game" "/" N"_Save Settings",		null,
//	game_menu_callback,	MENU_GAME_SAVE_SETTINGS					},
//  { "/" N"_Game" "/sep2",				null,
//	null,			0,					"<Separator>"	},
//  { "/" N"_Game" "/" N"Server Opt _initial",	null,
//	game_menu_callback,	MENU_GAME_SERVER_OPTIONS1				},
//  { "/" N"_Game" "/" N"Server Opt _ongoing",	null,
//	game_menu_callback,	MENU_GAME_SERVER_OPTIONS2				},
//  { "/" N"_Game" "/sep3",				null,
//	null,			0,					"<Separator>"	},
//  { "/" N"_Game" "/" N"_Export Log",		null,
//	game_menu_callback,	MENU_GAME_OUTPUT_LOG					},
//  { "/" N"_Game" "/" N"_Clear Log",		null,
//	game_menu_callback,	MENU_GAME_CLEAR_OUTPUT					},
//  { "/" N"_Game" "/sep4",				null,
//	null,			0,					"<Separator>"	},
//  { "/" N"_Game" "/" N"_Disconnect",		null,
//	game_menu_callback,	MENU_GAME_DISCONNECT					},
//  { "/" N"_Game" "/" N"_Quit",			"<control>q",
//	gtk_main_quit,		0							},
//  /* Government menu ... */
//  { "/" N"Gov_ernment",				null,
//	null,			0,					"<Branch>"	},
//  { "/" N"Gov_ernment" "/tearoff1",			null,
//	null,			0,					"<Tearoff>"	},
//  { "/" N"Gov_ernment" "/" N"_Tax Rates",		"<shift>t",
//	government_menu_callback,MENU_GOVERNMENT_TAX_RATE				},
//  { "/" N"Gov_ernment" "/sep1",			null,
//	null,			0,					"<Separator>"	},
//  { "/" N"Gov_ernment" "/" N"_Find City",		"<shift>f",
//	government_menu_callback,MENU_GOVERNMENT_FIND_CITY				},
//  { "/" N"Gov_ernment" "/" N"Work_lists",		"<shift>l",
//	government_menu_callback,MENU_GOVERNMENT_WORKLISTS				},
//  { "/" N"Gov_ernment" "/sep2",			null,
//	null,			0,					"<Separator>"	},
//  { "/" N"Gov_ernment" "/" N"_Change Government",	null,
//	null,           0,                  "<Branch>"    },
//  { "/" N"Gov_ernment" "/" N"_Change Government" "/" N"_Revolution!",
//                                                 "<shift>r",
//	government_menu_callback,	MENU_GOVERNMENT_REVOLUTION     },
//  { "/" N"Gov_ernment" "/" N"_Change Government" "/sep1", null,
//	null,			0,			"<Separator>"	},
//  /* View menu ... */
//  { "/" N"_View",					null,
//	null,			0,					"<Branch>"	},
//  { "/" N"_View" "/tearoff1",			null,
//	null,			0,					"<Tearoff>"	},
//  { "/" N"_View" "/" N"Map _Grid",			"<control>g",
//	view_menu_callback,	MENU_VIEW_SHOW_MAP_GRID,		"<CheckItem>"	},
//  { "/" N"_View" "/" N"National _Borders",		"<control>b",
//	view_menu_callback,	MENU_VIEW_SHOW_NATIONAL_BORDERS,	"<CheckItem>"	},
//  { "/" N"_View" "/" N"City _Names",		"<control>n",
//	view_menu_callback,	MENU_VIEW_SHOW_CITY_NAMES,		"<CheckItem>"	},
//  { "/" N"_View" "/" N"City G_rowth",		"<control>r",
//	view_menu_callback,	MENU_VIEW_SHOW_CITY_GROWTH_TURNS,
//	"<CheckItem>"	},
//  { "/" N"_View" "/" N"City _Productions",		"<control>p",
//	view_menu_callback,	MENU_VIEW_SHOW_CITY_PRODUCTIONS,	"<CheckItem>"	},
//  { "/" N"_View" "/sep1",				null,
//	null,			0,					"<Separator>"	},
//  { "/" N"_View" "/" N"Terrain",			null,
//        view_menu_callback,     MENU_VIEW_SHOW_TERRAIN,	                "<CheckItem>"   },
//  { "/" N"_View" "/" N"Coastline",		null,
//        view_menu_callback,     MENU_VIEW_SHOW_COASTLINE,       	"<CheckItem>"   },
//  { "/" N"_View" "/" N"Improvements",		null,
//	null,			0,					"<Branch>"	},
//  { "/" N"_View" "/" N"Improvements" "/tearoff1",	null,
//	null,			0,					"<Tearoff>"	},
//  { "/" N"_View" "/" N"Improvements" "/" N"Roads & Rails", null,
//	view_menu_callback,	MENU_VIEW_SHOW_ROADS_RAILS,		"<CheckItem>"	},
//  { "/" N"_View" "/" N"Improvements" "/" N"Irrigation", null,
//	view_menu_callback,	MENU_VIEW_SHOW_IRRIGATION,		"<CheckItem>"	},
//  { "/" N"_View" "/" N"Improvements" "/" N"Mines",	null,
//	view_menu_callback,	MENU_VIEW_SHOW_MINES,			"<CheckItem>"	},
//  { "/" N"_View" "/" N"Improvements" "/" N"Fortress & Airbase", null,
//	view_menu_callback,	MENU_VIEW_SHOW_FORTRESTerrain_H.S_AIRBASE,	"<CheckItem>"	},
//  { "/" N"_View" "/" N"Specials",			null,
//	view_menu_callback,	MENU_VIEW_SHOW_SPECIALS,		"<CheckItem>"	},
//  { "/" N"_View" "/" N"Pollution & Fallout",	null,
//	view_menu_callback,	MENU_VIEW_SHOW_POLLUTION,		"<CheckItem>"	},
//  { "/" N"_View" "/" N"Cities",			null,
//	view_menu_callback,	MENU_VIEW_SHOW_CITIES,			"<CheckItem>"	},
//  { "/" N"_View" "/" N"Units",			null,
//	view_menu_callback,	MENU_VIEW_SHOW_UNITS,			"<CheckItem>"	},
//  { "/" N"_View" "/" N"Focus Unit",		null,
//	view_menu_callback,	MENU_VIEW_SHOW_FOCUS_UNIT,		"<CheckItem>"	},
//  { "/" N"_View" "/" N"Fog of War",		null,
//	view_menu_callback,	MENU_VIEW_SHOW_FOG_OF_WAR,		"<CheckItem>"	},
//  { "/" N"_View" "/sep2",				null,
//	null,			0,					"<Separator>"	},
//  { "/" N"_View" "/" N"_Center View",		"c",
//	view_menu_callback,	MENU_VIEW_CENTER_VIEW					},
//  /* Orders menu ... */
//  { "/" N"_Orders",					null,
//	null,			0,					"<Branch>"	},
//  { "/" N"_Orders" "/tearoff1",			null,
//	null,			0,					"<Tearoff>"	},
//  { "/" N"_Orders" "/" N"_Build City",		"b",
//	orders_menu_callback,	MENU_ORDER_BUILD_CITY					},
//  { "/" N"_Orders" "/" N"Build _Road",		"r",
//	orders_menu_callback,	MENU_ORDER_ROAD						},
//  { "/" N"_Orders" "/" N"Build _Irrigation",	"i",
//	orders_menu_callback,	MENU_ORDER_IRRIGATE					},
//  { "/" N"_Orders" "/" N"Build _Mine",		"m",
//	orders_menu_callback,	MENU_ORDER_MINE						},
//  { "/" N"_Orders" "/" N"Transf_orm Terrain",	"o",
//	orders_menu_callback,	MENU_ORDER_TRANSFORM					},
//  { "/" N"_Orders" "/" N"Build _Fortress",	"f",
//	orders_menu_callback,	MENU_ORDER_FORTRESS					},
//  { "/" N"_Orders" "/" N"Build Airbas_e",		"e",
//	orders_menu_callback,	MENU_ORDER_AIRBASE					},
//  { "/" N"_Orders" "/" N"Clean _Pollution",	"p",
//	orders_menu_callback,	MENU_ORDER_POLLUTION					},
//  { "/" N"_Orders" "/" N"Clean _Nuclear Fallout",	"n",
//	orders_menu_callback,	MENU_ORDER_FALLOUT					},
//  { "/" N"_Orders" "/sep1",				null,
//	null,			0,					"<Separator>"	},
//  { "/" N"_Orders" "/" N"_Sentry",		"s",
//	orders_menu_callback,	MENU_ORDER_SENTRY					},
//  { "/" N"_Orders" "/" N"Pillage",		"<shift>p",
//	orders_menu_callback,	MENU_ORDER_PILLAGE					},
//  { "/" N"_Orders" "/sep2",				null,
//	null,			0,					"<Separator>"	},
//  { "/" N"_Orders" "/" N"Make _Homecity",		"h",
//	orders_menu_callback,	MENU_ORDER_HOMECITY					},
//  { "/" N"_Orders" "/" N"_Unload Transporter",	"<shift>u",
//	orders_menu_callback,	MENU_ORDER_UNLOAD_TRANSPORTER					},
//  { "/" N"_Orders" "/" N"Load",			"l",
//    orders_menu_callback, MENU_ORDER_LOAD},
//  { "/" N"_Orders" "/" N"Unload", 		"u",
//    orders_menu_callback, MENU_ORDER_UNLOAD},
//  { "/" N"_Orders" "/" N"Wake up o_thers",		"<shift>w",
//	orders_menu_callback,	MENU_ORDER_WAKEUP_OTHERS				},
//  { "/" N"_Orders" "/sep3",				null,
//	null,			0,					"<Separator>"	},
//  { "/" N"_Orders" "/" N"_Auto Settler",		"a",
//	orders_menu_callback,	MENU_ORDER_AUTO_SETTLER					},
//  { "/" N"_Orders" "/" N"Auto E_xplore",		"x",
//	orders_menu_callback,	MENU_ORDER_AUTO_EXPLORE					},
//  { "/" N"_Orders" "/" N"_Connect" "/" N"_Road", "<ctrl><shift>r",
//   orders_menu_callback, MENU_ORDER_CONNECT_ROAD},
//  { "/" N"_Orders" "/" N"_Connect" "/" N"Rai_l", "<ctrl><shift>l",
//   orders_menu_callback, MENU_ORDER_CONNECT_RAIL},
//  { "/" N"_Orders" "/" N"_Connect" "/" N"_Irrigate", "<ctrl><shift>i",
//   orders_menu_callback, MENU_ORDER_CONNECT_IRRIGATE},
//  { "/" N"_Orders" "/" N"Patrol (_Q)",		"q",
//	orders_menu_callback,	MENU_ORDER_PATROL					},
//  { "/" N"_Orders" "/" N"_Go to",			"g",
//	orders_menu_callback,	MENU_ORDER_GOTO						},
//  { "/" N"_Orders" "/" N"Go|Airlift to City",	"<shift>l",
//	orders_menu_callback,	MENU_ORDER_GOTO_CITY					},
//  { "/" N"_Orders" "/" N"Return to nearest city",	"<shift>g",
//	orders_menu_callback,	MENU_ORDER_RETURN },
//  { "/" N"_Orders" "/sep4",				null,
//	null,			0,					"<Separator>"	},
//  { "/" N"_Orders" "/" N"_Disband Unit",		"<shift>d",
//	orders_menu_callback,	MENU_ORDER_DISBAND					},
//  { "/" N"_Orders" "/" N"Diplomat|Spy Actions",	"d",
//	orders_menu_callback,	MENU_ORDER_DIPLOMAT_DLG					},
//  { "/" N"_Orders" "/" N"Explode Nuclear",        "<shift>n",
//	orders_menu_callback,	MENU_ORDER_NUKE						},
//  { "/" N"_Orders" "/sep5",				null,
//	null,			0,					"<Separator>"	},
//  { "/" N"_Orders" "/" N"_Wait",			"w",
//	orders_menu_callback,	MENU_ORDER_WAIT						},
//  { "/" N"_Orders" "/" N"Done",			"space",
//	orders_menu_callback,	MENU_ORDER_DONE						},
//  /* Reports menu ... */
//  { "/" N"_Reports",					null,
//	null,			0,					"<Branch>"	},
//  { "/" N"_Reports" "/tearoff1",			null,
//	null,			0,					"<Tearoff>"	},
//  { "/" N"_Reports" "/" N"_Cities",		"F1",
//	reports_menu_callback,	MENU_REPORT_CITIES					},
//  { "/" N"_Reports" "/" N"_Units",		"F2",
//	reports_menu_callback,	MENU_REPORT_UNITS					},
//  { "/" N"_Reports" "/" N"_Players",		"F3",
//	reports_menu_callback,	MENU_REPORT_PLAYERS					},
//  { "/" N"_Reports" "/" N"_Economy",		"F5",
//	reports_menu_callback,	MENU_REPORT_ECONOMY					},
//  { "/" N"_Reports" "/" N"_Science",		"F6",
//	reports_menu_callback,	MENU_REPORT_SCIENCE					},
//  { "/" N"_Reports" "/sep1",				null,
//	null,			0,					"<Separator>"	},
//  { "/" N"_Reports" "/" N"_Wonders of the World",	"F7",
//	reports_menu_callback,	MENU_REPORT_WOW						},
//  { "/" N"_Reports" "/" N"_Top Five Cities",	"F8",
//	reports_menu_callback,	MENU_REPORT_TOP_CITIES					},
//  { "/" N"_Reports" "/" N"_Messages",		"F10",
//	reports_menu_callback,	MENU_REPORT_MESSAGES					},
//  { "/" N"_Reports" "/" N"_Demographics",		"F11",
//	reports_menu_callback,	MENU_REPORT_DEMOGRAPHIC					},
//  { "/" N"_Reports" "/" N"S_paceship",		"F12",
//	reports_menu_callback,	MENU_REPORT_SPACESHIP					},
//  /* Help menu ... */
//  { "/" N"_Help",					null,
//	null,			0,					"<LastBranch>"	},
//  { "/" N"_Help" "/tearoff1",			null,
//	null,			0,					"<Tearoff>"	},
//  { "/" N"_Help" "/" N"Language_s",		null,
//	help_menu_callback,	MENU_HELP_LANGUAGES					},
//  { "/" N"_Help" "/" N"Co_nnecting",		null,
//	help_menu_callback,	MENU_HELP_CONNECTING					},
//  { "/" N"_Help" "/" N"C_ontrols",		null,
//	help_menu_callback,	MENU_HELP_CONTROLS					},
//  { "/" N"_Help" "/" N"C_hatline",		null,
//	help_menu_callback,	MENU_HELP_CHATLINE					},
//  { "/" N"_Help" "/" N"_Worklist Editor",		null,
//	help_menu_callback,	MENU_HELP_WORKLIST_EDITOR				},
//  { "/" N"_Help" "/" N"Citizen _Management",	null,
//	help_menu_callback,	MENU_HELP_CMA						},
//  { "/" N"_Help" "/" N"_Playing",			null,
//	help_menu_callback,	MENU_HELP_PLAYING					},
//  { "/" N"_Help" "/sep1",				null,
//	null,			0,					"<Separator>"	},
//  { "/" N"_Help" "/" N"City _Improvements",	null,
//	help_menu_callback,	MENU_HELP_IMPROVEMENTS					},
//  { "/" N"_Help" "/" N"_Units",			null,
//	help_menu_callback,	MENU_HELP_UNITS						},
//  { "/" N"_Help" "/" N"Com_bat",			null,
//	help_menu_callback,	MENU_HELP_COMBAT					},
//  { "/" N"_Help" "/" N"_ZOC",			null,
//	help_menu_callback,	MENU_HELP_ZOC						},
//  { "/" N"_Help" "/" N"Techno_logy",		null,
//	help_menu_callback,	MENU_HELP_TECH						},
//  { "/" N"_Help" "/" N"_Terrain",			null,
//	help_menu_callback,	MENU_HELP_TERRAIN					},
//  { "/" N"_Help" "/" N"Won_ders",			null,
//	help_menu_callback,	MENU_HELP_WONDERS					},
//  { "/" N"_Help" "/" N"_Government",		null,
//	help_menu_callback,	MENU_HELP_GOVERNMENT					},
//  { "/" N"_Help" "/" N"Happin_ess",		null,
//	help_menu_callback,	MENU_HELP_HAPPINESS					},
//  { "/" N"_Help" "/" N"Space _Race",		null,
//	help_menu_callback,	MENU_HELP_SPACE_RACE					},
//  { "/" N"_Help" "/sep2",				null,
//	null,			0,					"<Separator>"	},
//  { "/" N"_Help" "/" N"_Copying",			null,
//	help_menu_callback,	MENU_HELP_COPYING					},
//  { "/" N"_Help" "/" N"_About",			null,
//	help_menu_callback,	MENU_HELP_ABOUT						}
//};
//
//
///****************************************************************
//  gettext-translates each "/" delimited component of menu path,
//  puts them back together, and returns as a static string.
//  Any component which is of form "<foo>" is _not_ translated.
//
//  Path should include underscores like in the menu itself.
//*****************************************************************/
//static final String translate_menu_path(final String path, boolean remove_uline)
//{
//#ifndef ENABLE_NLS
//  static char res[100];
//  strcpy(res, path);
//#else
//  static struct astring in, out, tmp;   /* these are never free'd */
//  char *tok, *s, *trn, *t;
//  int len;
//  char *res;
//
//  /* copy to in so can modify with strtok: */
//  astr_minsize(&in, path.length()+1);
//  strcpy(in.str, path);
//  astr_minsize(&out, 1);
//  out.str[0] = '\0';
//  util.freelog(Log.LOG_DEBUG, "trans: %s", in.str);
//
//  s = in.str;
//  while ((tok=strtok(s, "/"))) {
//    len = tok.length();
//    util.freelog(Log.LOG_DEBUG, "tok \"%s\", len %d", tok, len);
//    if (len && tok[0] == '<' && tok[len-1] == '>') {
//      t = tok;
//    } else {
//      trn = _(tok);
//      len = trn.length() + 1;	/* string plus leading '/' */
//      astr_minsize(&tmp, len+1);
//      sprintf(tmp.str, "/%s", trn);
//      t = tmp.str;
//      len = t.length();
//    }
//    astr_minsize(&out, out.n + len);
//    strcat(out.str, t);
//    util.freelog(Log.LOG_DEBUG, "t \"%s\", len %d, out \"%s\"", t, len, out.str);
//    s = null;
//  }
//  res = out.str;
//#endif
//
//  if (remove_uline) {
//    char *from, *to;
//    from = to = res;
//    do {
//      if (*from != '_') {
//	*(to++) = *from;
//      }
//    } while (*(from++));
//  }
//
//  return res;
//}
//
///****************************************************************
//...
//*****************************************************************/
//void setup_menus(GtkWidget *window, GtkWidget **menubar)
//{
//  final int nmenu_items = ARRAY_SIZE(menu_items);
//  GtkAccelGroup *accel;
//  int i;
//
//  accel=gtk_accel_group_new();
//
//  item_factory=gtk_item_factory_new(GTK_TYPE_MENU_BAR, "<main>", accel);
//   
//  for (i = 0; i < nmenu_items; i++) {
//    menu_items[i].path =
//	(translate_menu_path(menu_items[i].path, false));
//  }
//  
//  gtk_item_factory_create_items(item_factory, nmenu_items, menu_items, null);
//
//  gtk_accel_group_attach(accel, GTK_OBJECT(window));
//
//  if(menubar)
//    *menubar=gtk_item_factory_get_widget(item_factory, "<main>");
//
//  /* kluge to get around gtk's interpretation of "/" in menu item names */
//  menus_rename("<main>/_Orders/Go|Airlift to City", "Go/Air_lift to City");
//  menus_rename("<main>/_Orders/Diplomat|Spy Actions", "_Diplomat/Spy Actions");
//}
//
///****************************************************************
//...
//*****************************************************************/
//static void menus_set_sensitive(final String path, int sensitive)
//{
//  GtkWidget *item;
//
//  path = translate_menu_path(path, true);
//  
//  if(!(item=gtk_item_factory_get_widget(item_factory, path))) {
//    util.freelog(Log.LOG_ERROR,
//	    "Can't set sensitivity for non-existent menu %s.", path);
//    return;
//  }
//
//  if(GTK_IS_MENU(item))
//    item=gtk_menu_get_attach_widget(GTK_MENU(item));
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
//  path = translate_menu_path(path, true);
//
//  if (!(item = gtk_item_factory_get_widget(item_factory, path))) {
//    util.freelog(Log.LOG_ERROR,
//	    "Can't set active for non-existent menu %s.", path);
//    return;
//  }
//
//  if (GTK_IS_MENU(item))
//    item = gtk_menu_get_attach_widget(GTK_MENU(item));
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
//  path = translate_menu_path(path, true);
//  
//  if(!(item=gtk_item_factory_get_widget(item_factory, path))) {
//    util.freelog(Log.LOG_ERROR, "Can't show non-existent menu %s.", path);
//    return;
//  }
//
//  if(GTK_IS_MENU(item))
//    item=gtk_menu_get_attach_widget(GTK_MENU(item));
//
//  if(shown)
//    gtk_widget_show(item);
//  else
//    gtk_widget_hide(item);
//}
//#endif /* UNUSED */
//
///****************************************************************
//...
//*****************************************************************/
//static void menus_rename(final String path, char *s)
//{
//  GtkWidget *item;
//  
//  path = translate_menu_path(path, true);
//  
//  if(!(item=gtk_item_factory_get_widget(item_factory, path))) {
//    util.freelog(Log.LOG_ERROR, "Can't rename non-existent menu %s.", path);
//    return;
//  }
//
//  if (GTK_IS_MENU(item))
//    item=gtk_menu_get_attach_widget(GTK_MENU(item));
//  
//  gtk_set_label(GTK_BIN(item).child, s);
//  gtk_label_parse_uline(GTK_LABEL(GTK_BIN(item).child), s);
//}
//
//
///****************************************************************
//  The player has chosen a government from the menu.
//*****************************************************************/
//static void government_callback(GtkMenuItem *item, gpointer data)
//{
//  set_government_choice(GPOINTER_TO_INT(data));
//}
//
///****************************************************************
//Note: the menu strings should contain underscores as in the
//menu_items struct. The underscores will be removed elsewhere if
//the string is used for a lookup via gtk_item_factory_get_widget()
//*****************************************************************/
//void update_menus()
//{
//  if (!can_client_change_view()) {
//    menus_set_sensitive("<main>/_Reports", false);
//    menus_set_sensitive("<main>/Gov_ernment", false);
//    menus_set_sensitive("<main>/_View", false);
//    menus_set_sensitive("<main>/_Orders", false);
//  } else {
//    unit punit;
//    GtkWidget *item;
//    final String path =
//	translate_menu_path("<main>/Gov_ernment/_Change Government", true);
//    GtkWidget *parent = gtk_item_factory_get_widget(item_factory, path);
//
//    if (parent) {
//      int i;
//      GList *iter, *iter_next;
//
//      /* remove previous government entries. */
//      iter = gtk_container_children(GTK_CONTAINER(parent));
//      for (iter = g_list_nth(iter, 2); iter; iter = iter_next) {
//        iter_next = iter.next;
//
//        gtk_container_remove(GTK_CONTAINER(parent), GTK_WIDGET(iter.data));
//      }
//
//      /* add new government entries. */
//      for (i = 0; i < Game.game.government_count; i++) {
//        government g = &governments[i];
//
//        if (i != Game.game.government_when_anarchy) {
//          item = gtk_menu_item_new_with_label(g.name);
//          gtk_widget_show(item);
//
//          gtk_signal_connect(GTK_OBJECT(item), "activate",
//            GTK_SIGNAL_FUNC(government_callback), GINT_TO_POINTER(g.index));
//
//          if (!can_change_to_government(Game.game.player_ptr, i)) {
//            gtk_widget_set_sensitive(item, false);
//	  }
//
//          gtk_menu_shell_append(GTK_MENU_SHELL(parent), item);
//          gtk_widget_show(item);
//        }
//      }
//    }
//
//    menus_set_sensitive("<main>/_Reports", true);
//    menus_set_sensitive("<main>/Gov_ernment", true);
//    menus_set_sensitive("<main>/_View", true);
//    menus_set_sensitive("<main>/_Orders", can_client_issue_orders());
//
//    menus_set_sensitive("<main>/Gov_ernment/_Tax Rates",
//			can_client_issue_orders());
//    menus_set_sensitive("<main>/Gov_ernment/Work_lists",
//			can_client_issue_orders());
//    menus_set_sensitive("<main>/Gov_ernment/_Change Government",
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
//      char *irrfmt = "Change to %s (_I)";
//      char *minfmt = "Change to %s (_M)";
//      char *transfmt = "Transf_orm to %s";
//      char irrtext[128], mintext[128], transtext[128];
//      char *roadtext;
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
//      menus_set_sensitive("<main>/_Orders/Make _Homecity",
//			  can_unit_change_homecity(punit));
//      menus_set_sensitive("<main>/_Orders/_Unload Transporter",
//			  get_transporter_occupancy(punit) > 0);
//      menus_set_sensitive("<main>/_Orders/Load",
//	can_unit_load(punit, find_transporter_for_unit(punit,
//						       punit.tile)));
//      menus_set_sensitive("<main>/_Orders/Unload",
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
//      menus_set_sensitive("<main>/_Orders/_Connect/Rai_l",
//                          can_unit_do_connect(punit, ACTIVITY_RAILROAD));
//      menus_set_sensitive("<main>/_Orders/_Connect/_Irrigate",
//                          can_unit_do_connect(punit, ACTIVITY_IRRIGATE));
//      menus_set_sensitive("<main>/_Orders/Return to nearest city",
//			  !(is_air_unit(punit) || is_heli_unit(punit)));
//      menus_set_sensitive("<main>/_Orders/_Disband Unit",
//                          !unit_flag(punit, F_UNDISBANDABLE));
//      menus_set_sensitive("<main>/_Orders/Diplomat|Spy Actions",
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
//      } else {
//	menus_rename("<main>/_Orders/Build _Road", "Build _Road");
//      }
//
//      ttype = punit.tile.terrain;
//      tinfo = get_tile_type(ttype);
//      if (tinfo.irrigation_result != Terrain_H.T_NONE
//	  && tinfo.irrigation_result != ttype) {
//	irrtext = util.my_snprintf( irrfmt,
//		    (get_tile_type(tinfo.irrigation_result)).terrain_name);
//      } else if (Map.map_has_special(punit.tile, S_IRRIGATION)
//		 && Player_P.player_knows_techs_with_flag(Game.game.player_ptr,
//						 TF_FARMLAND)) {
//	irrtext = String.format( "Bu_ild Farmland");
//      }
//      if (tinfo.mining_result != Terrain_H.T_NONE
//	  && tinfo.mining_result != ttype) {
//	mintext = util.my_snprintf( minfmt,
//		    (get_tile_type(tinfo.mining_result)).terrain_name);
//      }
//      if (tinfo.transform_result != Terrain_H.T_NONE
//	  && tinfo.transform_result != ttype) {
//	transtext = util.my_snprintf( transfmt,
//		    (get_tile_type(tinfo.transform_result)).terrain_name);
//      }
//
//      menus_rename("<main>/_Orders/Build _Irrigation", irrtext);
//      menus_rename("<main>/_Orders/Build _Mine", mintext);
//      menus_rename("<main>/_Orders/Transf_orm Terrain", transtext);
//
//      if (can_unit_do_activity(punit, ACTIVITY_FORTIFYING))
//	menus_rename("<main>/_Orders/Build _Fortress", "_Fortify");
//      else
//	menus_rename("<main>/_Orders/Build _Fortress", "Build _Fortress");
//
//      if (unit_flag(punit, F_PARATROOPERS))
//	menus_rename("<main>/_Orders/Clean _Pollution", "_Paradrop");
//      else
//	menus_rename("<main>/_Orders/Clean _Pollution", "Clean _Pollution");
//
//      if (!unit_flag(punit, Eunit_flag_id.F_SETTLERS))
//	menus_rename("<main>/_Orders/_Auto Settler", "_Auto Attack");
//      else
//	menus_rename("<main>/_Orders/_Auto Settler", "_Auto Settler");
//
//      menus_set_sensitive("<main>/_Orders", true);
//    }
//    else
//      menus_set_sensitive("<main>/_Orders", false);
//  }
//}
}