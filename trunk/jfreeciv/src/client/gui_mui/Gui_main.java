package client.gui_mui;

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
//#ifdef HAVE_CONFIG_H
//#include <config.h>
//#endif
//
//#include <errno.h>
//#include <stdio.h>
//#include <stdlib.h>
//#include <string.h>
//
//#ifdef HAVE_UNISTD_H
//#include <unistd.h>
//#endif
//
//#include <exec/memory.h>
//#include <devices/timer.h>
//#include <intuition/sghooks.h>
//#include <libraries/gadtools.h>
//#include <libraries/mui.h>
//#include <mui/NListview_MCC.h>
//
//#include <clib/alib_protos.h>
//#include <clib/exec_protos.h>
//#include <clib/muimaster_protos.h>
//#include <clib/socket_protos.h>
//#include <clib/utility_protos.h>
//#include <clib/intuition_protos.h>
//
//#include <proto/exec.h>
//#include <proto/muimaster.h>
//#include <proto/socket.h>
//#include <proto/utility.h>
//#include <proto/intuition.h>
//
//#include <proto/usergroup.h>
//
//#include "capability.h"
//#include "fciconv.h"
//#include "fcintl.h"
//#include "mem.h"
//#include "netintf.h"
//#include "support.h"
//#include "version.h"
//
//#include "chatline.h"
//#include "citydlg_g.h"
//#include "cityrep_g.h"
//#include "civclient.h"
//#include "clinet.h"
//#include "control.h"
//#include "dialogs.h"
//#include "gui_main.h"
//#include "gotodlg.h"
//#include "finddlg.h"
//#include "helpdata.h"		/* boot_help_texts() */
//#include "mapctrl.h"
//#include "mapview.h"
//#include "menu_g.h"
//#include "messagedlg.h"
//#include "messagewin.h"
//#include "optiondlg.h"
//#include "options.h"
//#include "plrdlg.h"
//#include "ratesdlg.h"
//#include "repodlgs_g.h"
//#include "spaceshipdlg_g.h"
//#include "tilespec.h"
//#include "wldlg.h"
//
///* Amiga Client Stuff */
//#include "autogroupclass.h"
//#include "colortextclass.h"
//#include "historystringclass.h"
//#include "mapclass.h"
//#include "muistuff.h"
//#include "objecttreeclass.h"
//#include "overviewclass.h"
//#include "scrollbuttonclass.h"
//#include "transparentstringclass.h"
//#include "worklistclass.h"
//
//final String client_string = "gui-mui";
//
//client_option gui_options[] = {
//  /* None. */
//};
//final int num_gui_options = ARRAY_SIZE(gui_options);
//
///**************************************************************************
//  Print extra usage information, including one line help on each option,
//  to stderr.
//**************************************************************************/
//static void print_usage(final String argv0)
//{
//  /* add client-specific usage information here */
//  fc_fprintf(stderr, "Report bugs to <%s>.\n", BUG_EMAIL_ADDRESS); 
//}
//
///**************************************************************************
//...
//**************************************************************************/
//static void parse_options(int argc, char **argv)
//{
//  int i;
//
//  i = 1;
//  while (i < argc)
//  {
//    if (is_option("--help", argv[i]))
//    {
//      print_usage(argv[0]);
//      exit(EXIT_SUCCESS);
//    }
//    i += 1;
//  }
//}
//
///**************************************************************************
// ...
//**************************************************************************/
//static void handle_timer()
//{
//  real_timer_callback();
//}
//
//static boolean connected;		/* true, if connected to the server */
//static int connected_sock;
//
//Object *app;
//static Object *main_menu;
//static Object *main_order_menu;
//Object *main_wnd;
//Object *main_output_listview;
//static Object *main_chatline_string;
//
//Object *main_people_text;
//Object *main_year_text;
//Object *main_gold_text;
//Object *main_tax_text;
//static Object *main_info_group;
//static Object *main_turndone_group;
//Object *main_turndone_button;
//Object *main_econ_sprite[10];
//Object *main_bulb_sprite;
//Object *main_sun_sprite;
//Object *main_flake_sprite;
//Object *main_government_sprite;
//Object *main_timeout_text;
//
//Object *main_unitname_text;
//Object *main_moves_text;
//Object *main_terrain_text;
//Object *main_hometown_text;
//Object *main_unit_unit;
//Object *main_below_group;
//
//Object *main_map_area;
//static Object *main_map_hscroller;
//static Object *main_map_vscroller;
//static Object *main_map_scrollbutton;
//
//Object *main_overview_area;
//Object *main_overview_group;
//
//static MsgPort timer_port;
//static timerequest timer_req;
//static ULONG timer_outstanding;
//
//static Object *menu_find_item(ULONG udata);
//
///****************************************************************
// This describes the pull down menu
//*****************************************************************/
//#define MAKE_TITLE(name,userdata) {NM_TITLE,name,null,0,0,(APTR)userdata}
//#define MAKE_ITEM(name,userdata,shortcut,flags) {NM_ITEM,name,shortcut,flags,0,(APTR)userdata}
//#define MAKE_SUBITEM(name,userdata,shortcut,flags) {NM_SUB,name,shortcut,flags,0,(APTR)userdata}
//#define MAKE_SIMPLEITEM(name,userdata) {NM_ITEM,name,null, 0,0, (APTR)userdata}
//#define MAKE_SEPERATOR {NM_ITEM, NM_BARLABEL, null, 0, 0, (APTR)0}
//#define MAKE_END {NM_END,null,0,0,0,(APTR)0}
//
///* localized in init_gui() */
//static struct NewMenu MenuData[] =
//{
//  MAKE_TITLE(N"Game", MENU_GAME),
//  MAKE_SIMPLEITEM(N"Options...", MENU_GAME_OPTIONS),
//  MAKE_SIMPLEITEM(N"Message Options...", MENU_GAME_MSG_OPTIONS),
//  MAKE_SIMPLEITEM(N"Save Settings", MENU_GAME_SAVE_SETTINGS),
//  MAKE_SEPERATOR,
//  MAKE_ITEM(N"Players...", MENU_GAME_PLAYERS, "F3", NM_COMMANDSTRING),
//  MAKE_SIMPLEITEM(N"Message...", MENU_GAME_MESSAGES),
//  MAKE_SIMPLEITEM(N"Server opt initial...", MENU_GAME_SERVER_OPTIONS1),
//  MAKE_SIMPLEITEM(N"Server opt ongoing...", MENU_GAME_SERVER_OPTIONS2),
//  MAKE_SIMPLEITEM(N"Export Log", MENU_GAME_OUTPUT_LOG),
//  MAKE_SIMPLEITEM(N"Clear Log", MENU_GAME_CLEAR_OUTPUT),
//  MAKE_SIMPLEITEM(N"Disconnect", MENU_GAME_DISCONNECT),
//  MAKE_SEPERATOR,
//  MAKE_ITEM(N"Quit", MENU_GAME_QUIT, "Q", 0),
//
//  MAKE_TITLE(N"Kingdom", MENU_KINGDOM),
//  MAKE_ITEM(N"Tax Rate...", MENU_KINGDOM_TAX_RATE, "SHIFT T", NM_COMMANDSTRING),
//  MAKE_SEPERATOR,
//  MAKE_ITEM(N"Find City...", MENU_KINGDOM_FIND_CITY, "CTRL F", NM_COMMANDSTRING),
//  MAKE_ITEM(N"Worklists...", MENU_KINGDOM_WORKLISTS, "SHIFT L", NM_COMMANDSTRING),
//  MAKE_SEPERATOR,
//  MAKE_ITEM(N"REVOLUTION...", MENU_KINGDOM_REVOLUTION, "SHIFT R", NM_COMMANDSTRING),
//
//  MAKE_TITLE(N"View", MENU_VIEW),
//  MAKE_ITEM(N"Map Grid", MENU_VIEW_SHOW_MAP_GRID, "CTRL G", NM_COMMANDSTRING|MENUTOGGLE|CHECKIT),
//  MAKE_ITEM(N"City Names", MENU_VIEW_SHOW_CITY_NAMES,null,MENUTOGGLE|CHECKIT),
//  MAKE_ITEM(N"City Productions", MENU_VIEW_SHOW_CITY_PRODUCTIONS,null,MENUTOGGLE|CHECKIT),
//  MAKE_SEPERATOR,
//  MAKE_ITEM(N"Terrain", MENU_VIEW_SHOW_TERRAIN, null, MENUTOGGLE|CHECKIT),
//  MAKE_ITEM(N"Coastline", MENU_VIEW_SHOW_COASTLINE, null, MENUTOGGLE|CHECKIT),
//  MAKE_ITEM(N"Improvements",null,null,0),
//  MAKE_SUBITEM(N"Road & Rails", MENU_VIEW_SHOW_ROADS_RAILS, null, MENUTOGGLE|CHECKIT),
//  MAKE_SUBITEM(N"Irrigation", MENU_VIEW_SHOW_IRRIGATION, null, MENUTOGGLE|CHECKIT),
//  MAKE_SUBITEM(N"Mines", MENU_VIEW_SHOW_MINES, null, MENUTOGGLE|CHECKIT),
//  MAKE_SUBITEM(N"Fortress & Airbase", MENU_VIEW_SHOW_FORTRESTerrain_H.S_AIRBASE, null, MENUTOGGLE|CHECKIT),
//  MAKE_SUBITEM(N"Specials", MENU_VIEW_SHOW_SPECIALS, null, MENUTOGGLE|CHECKIT),
//  MAKE_SUBITEM(N"Pollution & Fallout", MENU_VIEW_SHOW_POLLUTION, null, MENUTOGGLE|CHECKIT),
//  MAKE_SUBITEM(N"Cities", MENU_VIEW_SHOW_CITIES, null, MENUTOGGLE|CHECKIT),
//  MAKE_SUBITEM(N"Units", MENU_VIEW_SHOW_UNITS, null, MENUTOGGLE|CHECKIT),
//  MAKE_SUBITEM(N"Focus Unit", MENU_VIEW_SHOW_FOCUS_UNIT, null, MENUTOGGLE|CHECKIT),
//  MAKE_SUBITEM(N"Fog of War", MENU_VIEW_SHOW_FOG_OF_WAR, null, MENUTOGGLE|CHECKIT),
//  MAKE_SEPERATOR,
//  MAKE_ITEM(N"Center View", MENU_VIEW_CENTER_VIEW, "c", NM_COMMANDSTRING),
//
//  MAKE_TITLE(N"Order", MENU_ORDER),
//  MAKE_ITEM(N"Build City", MENU_ORDER_BUILD_CITY, "b", NM_COMMANDSTRING),
//  MAKE_ITEM(N"Build Road", MENU_ORDER_ROAD, "r", NM_COMMANDSTRING),
//  MAKE_ITEM(N"Build Mine", MENU_ORDER_MINE, "m", NM_COMMANDSTRING),
//  MAKE_ITEM(N"Build Irrigation", MENU_ORDER_IRRIGATE, "i", NM_COMMANDSTRING),
//  MAKE_ITEM(N"Transform Terrain", MENU_ORDER_TRANSFORM, "o", NM_COMMANDSTRING),
//  MAKE_SEPERATOR,
//  MAKE_ITEM(N"Build Fortress", MENU_ORDER_FORTRESS, "SHIFT F", NM_COMMANDSTRING),
//  MAKE_ITEM(N"Build Airbase", MENU_ORDER_AIRBASE, "e", NM_COMMANDSTRING),
//  MAKE_ITEM(N"Clean Pollution", MENU_ORDER_POLLUTION, "p", NM_COMMANDSTRING),
//  MAKE_ITEM(N"Clean Nuclear Fallout", MENU_ORDER_FALLOUT, "n", NM_COMMANDSTRING),
//  MAKE_SEPERATOR,
//  MAKE_ITEM(N"Fortify", MENU_ORDER_FORTIFY, "f", NM_COMMANDSTRING),
//  MAKE_ITEM(N"Sentry", MENU_ORDER_SENTRY, "s", NM_COMMANDSTRING),
//  MAKE_ITEM(N"Pillage", MENU_ORDER_PILLAGE, "SHIFT P", NM_COMMANDSTRING),
//  MAKE_SEPERATOR,
//  MAKE_ITEM(N"Make Homecity", MENU_ORDER_HOMECITY, "h", NM_COMMANDSTRING),
//  MAKE_ITEM(N"Unload", MENU_ORDER_UNLOAD, "u", NM_COMMANDSTRING),
//  MAKE_ITEM(N"Wake up others", MENU_ORDER_WAKEUP_OTHERS, "SHIFT W", NM_COMMANDSTRING),
//  MAKE_SEPERATOR,
//  MAKE_ITEM(N"Auto Settler", MENU_ORDER_AUTO_SETTLER, "a", NM_COMMANDSTRING),
//  MAKE_ITEM(N"Auto Attack", MENU_ORDER_AUTO_ATTACK, "SHIFT A", NM_COMMANDSTRING),
//  MAKE_ITEM(N"Auto Explore", MENU_ORDER_AUTO_EXPLORE, "x", NM_COMMANDSTRING),
//  MAKE_ITEM(N"Connect", MENU_ORDER_CONNECT, "SHIFT C", NM_COMMANDSTRING),
//  MAKE_ITEM(N"Patrol", MENU_ORDER_PATROL, "q", NM_COMMANDSTRING),
//  MAKE_ITEM(N"Go to", MENU_ORDER_GOTO, "g", NM_COMMANDSTRING),
//  MAKE_ITEM(N"Go/Airlift to City", MENU_ORDER_GOTO_CITY, "l", NM_COMMANDSTRING),
//  MAKE_SEPERATOR,
//  MAKE_ITEM(N"Disband Unit", MENU_ORDER_DISBAND, "SHIFT D", NM_COMMANDSTRING),
//  MAKE_ITEM(N"Help Build Wonder", MENU_ORDER_BUILD_WONDER, "SHIFT B", NM_COMMANDSTRING),
//  MAKE_ITEM(N"Make Trade Route", MENU_ORDER_TRADEROUTE, "SHIFT M", NM_COMMANDSTRING),
//  MAKE_ITEM(N"Diplomat/Spy Actions", MENU_ORDER_DIPLOMAT_DLG, "SHIFT B", NM_COMMANDSTRING),
//  MAKE_ITEM(N"Explode Nuclear", MENU_ORDER_NUKE, "SHIFT N", NM_COMMANDSTRING),
//  MAKE_SEPERATOR,
//  MAKE_ITEM(N"Wait", MENU_ORDER_WAIT, "w", NM_COMMANDSTRING),
//  MAKE_ITEM(N"Done", MENU_ORDER_DONE, "SPACE", NM_COMMANDSTRING),
//
//  MAKE_TITLE(N"Report", MENU_REPORT),
//  MAKE_ITEM(N"City Report...", MENU_REPORT_CITY, "F1", NM_COMMANDSTRING),
//  MAKE_ITEM(N"Science Report...", MENU_REPORT_SCIENCE, "F6", NM_COMMANDSTRING),
//  MAKE_ITEM(N"Trade Report...", MENU_REPORT_TRADE, "F5", NM_COMMANDSTRING),
//  MAKE_ITEM(N"Military Report...", MENU_REPORT_MILITARY, "F2", NM_COMMANDSTRING),
//  MAKE_SEPERATOR,
//  MAKE_ITEM(N"Wonders of the World", MENU_REPORT_WOW, "F7", NM_COMMANDSTRING),
//  MAKE_ITEM(N"Top Five Cities", MENU_REPORT_TOP_CITIES, "F8", NM_COMMANDSTRING),
//  MAKE_ITEM(N"Demographics", MENU_REPORT_DEMOGRAPHIC, "F9", NM_COMMANDSTRING),
//  MAKE_ITEM(N"Spaceship", MENU_REPORT_SPACESHIP, "F10", NM_COMMANDSTRING),
//
//  MAKE_TITLE(N"Help", MENU_HELP),
//  MAKE_SIMPLEITEM(HELP_LANGUAGES_ITEM, MENU_HELP_LANGUAGES),
//  MAKE_SIMPLEITEM(HELP_CONNECTING_ITEM, MENU_HELP_CONNECTING),
//  MAKE_SIMPLEITEM(HELP_CONTROLS_ITEM, MENU_HELP_CONTROLS),
//  MAKE_SIMPLEITEM(HELP_CHATLINE_ITEM, MENU_HELP_CHATLINE),
//  MAKE_SIMPLEITEM(HELP_PLAYING_ITEM, MENU_HELP_PLAYING),
//  MAKE_SIMPLEITEM(HELP_IMPROVEMENTS_ITEM, MENU_HELP_IMPROVEMENTS),
//  MAKE_SIMPLEITEM(HELP_UNITS_ITEM, MENU_HELP_UNITS),
//  MAKE_SIMPLEITEM(HELP_COMBAT_ITEM, MENU_HELP_COMBAT),
//  MAKE_SIMPLEITEM(HELP_ZOC_ITEM, MENU_HELP_ZOC),
//  MAKE_SIMPLEITEM(HELP_TECHS_ITEM, MENU_HELP_TECH),
//  MAKE_SIMPLEITEM(HELP_TERRAIN_ITEM, MENU_HELP_TERRAIN),
//  MAKE_SIMPLEITEM(HELP_WONDERS_ITEM, MENU_HELP_WONDERS),
//  MAKE_SIMPLEITEM(HELP_GOVERNMENT_ITEM, MENU_HELP_GOVERNMENT),
//  MAKE_SIMPLEITEM(HELP_HAPPINESS_ITEM, MENU_HELP_HAPPINESS),
//  MAKE_SIMPLEITEM(HELP_SPACE_RACE_ITEM, MENU_HELP_SPACE_RACE),
//  MAKE_SEPERATOR,
//  MAKE_SIMPLEITEM(HELP_COPYING_ITEM, MENU_HELP_COPYING),
//  MAKE_SIMPLEITEM(HELP_ABOUT_ITEM, MENU_HELP_ABOUT),
//
//  MAKE_END
//};
//
///**************************************************************************
// Update the connected users list at pregame state.
//**************************************************************************/
//void update_conn_list_dialog()
//{
//  /* PORTME */
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void sound_bell()
//{
//  DisplayBeep(null);
//}
//
///****************************************************************
// Callback for the chatline
//*****************************************************************/
//static void inputline_return()	/* from chatline.c */
//{
//  char *theinput;
//  int contents;
//
//  if (!(theinput = (char *) xget(main_chatline_string, MUIA_String_Contents)))
//    return;
//
//  if (*theinput)
//  {
//    send_chat(theinput);
//    contents = true;
//  }
//  else
//    contents = false;
//
//  nnset(main_chatline_string, MUIA_String_Contents, "");
//  if (contents)
//    activate(main_chatline_string);
//}
//
///****************************************************************
// General Callback for freeciv used by the menu or keyboard
//*****************************************************************/
//static void control_callback(ULONG * value)
//{
//  if (*value)
//  {
//    switch (*value) {
//    case UNIT_NORTH:
//      key_unit_move(DIR8_NORTH);
//      break;
//    case UNIT_SOUTH:
//      key_unit_move(DIR8_SOUTH);
//      break;
//    case UNIT_EAST:
//      key_unit_move(DIR8_EAST);
//      break;
//    case UNIT_WEST:
//      key_unit_move(DIR8_WEST);
//      break;
//    case UNIT_NORTH_EAST:
//      key_unit_move(DIR8_NORTHEAST);
//      break;
//    case UNIT_NORTH_WEST:
//      key_unit_move(DIR8_NORTHWEST);
//      break;
//    case UNIT_SOUTH_EAST:
//      key_unit_move(DIR8_SOUTHEAST);
//      break;
//    case UNIT_SOUTH_WEST:
//      key_unit_move(DIR8_SOUTHWEST);
//      break;
//    case UNIT_POPUP_CITY:
//      {
//	unit punit;
//	if ((punit = get_unit_in_focus()))
//	{
//	  city pcity = map_get_city(punit.tile);
//	  if (pcity)
//	  {
//	    popup_city_dialog(pcity, 0);
//	  }
//	}
//      }
//      break;
//    case UNIT_POPUP_UNITLIST:
//      {
//	unit punit;
//	if ((punit = get_unit_in_focus()))
//	{
//	  tile ptile = map_get_tile(punit.tile);
//	  if (ptile)
//	  {
//	    popup_unit_select_dialog(ptile);
//	  }
//	}
//      }
//      break;
//    case UNIT_ESCAPE:
//      key_cancel_action();
//      break;
//    case UNIT_UPGRADE:
//      {
//	unit punit;
//	if ((punit = get_unit_in_focus()))
//	{
//	  popup_upgrade_dialog(punit);
//	}
//      }
//      break;
//    case END_TURN:
//      key_end_turn();
//      break;
//    case NEXT_UNIT:
//      advance_unit_focus();
//      break;
//
//    case MENU_GAME_OPTIONS:
//      popup_option_dialog();
//      break;
//    case MENU_GAME_MSG_OPTIONS:
//      popup_messageopt_dialog();
//      break;
//    case MENU_GAME_SAVE_SETTINGS:
//      save_options();
//      break;
//    case MENU_GAME_PLAYERS:
//      popup_players_dialog();
//      break;
//    case MENU_GAME_MESSAGES:
//      popup_meswin_dialog();
//      break;
//    case MENU_GAME_SERVER_OPTIONS1:
//      send_report_request(REPORT_SERVER_OPTIONS1);
//      break;
//    case MENU_GAME_SERVER_OPTIONS2:
//      send_report_request(REPORT_SERVER_OPTIONS2);
//      break;
//    case MENU_GAME_OUTPUT_LOG:
//      log_output_window();
//      break;
//    case MENU_GAME_CLEAR_OUTPUT:
//      clear_output_window();
//      break;
//    case MENU_GAME_DISCONNECT:
//      disconnect_from_server();
//      break;
//    case MENU_GAME_QUIT:
//      DoMethod(app, MUIM_Application_ReturnID, MUIV_Application_ReturnID_Quit);
//      break;
//
//    case MENU_KINGDOM_TAX_RATE:
//      popup_rates_dialog();
//      break;
//    case MENU_KINGDOM_FIND_CITY:
//      popup_find_dialog();
//      break;
//    case MENU_KINGDOM_WORKLISTS:
//      popup_worklists_dialog(game.player_ptr);
//      break;
//
//    case MENU_KINGDOM_REVOLUTION:
//      popup_revolution_dialog();
//      break;
//
//    case KEMAP_GRID_TOGGLE:
//      DoMethod(main_menu,MUIM_SetUData,MENU_VIEW_SHOW_MAP_GRID,MUIA_Menuitem_Checked,!draw_map_grid);
//      /* no break! */
//    case MENU_VIEW_SHOW_MAP_GRID:
//      if(draw_map_grid != xget(menu_find_item(MENU_VIEW_SHOW_MAP_GRID),
//			       MUIA_Menuitem_Checked))
//      {
//	request_toggle_map_grid();
//      }
//      break;
//    case MENU_VIEW_SHOW_CITY_NAMES:
//      if(draw_city_names != xget(menu_find_item(MENU_VIEW_SHOW_CITY_NAMES),
//				 MUIA_Menuitem_Checked))
//      {
//	request_toggle_city_names();
//      }
//      break;
//    case  MENU_VIEW_SHOW_CITY_PRODUCTIONS:
//          if(draw_city_productions != xget(menu_find_item(MENU_VIEW_SHOW_CITY_PRODUCTIONS), MUIA_Menuitem_Checked))
//	    request_toggle_city_productions();
//	  break;
//    case  MENU_VIEW_SHOW_TERRAIN:
//	  if (draw_terrain != xget(menu_find_item(MENU_VIEW_SHOW_TERRAIN),MUIA_Menuitem_Checked))
//	    request_toggle_terrain();
//	  break;
//    case  MENU_VIEW_SHOW_COASTLINE:
//	  if (draw_coastline != xget(menu_find_item(MENU_VIEW_SHOW_COASTLINE),MUIA_Menuitem_Checked))
//	    request_toggle_coastline();
//	  break;
//    case  MENU_VIEW_SHOW_ROADS_RAILS:
//	  if (draw_roads_rails != xget(menu_find_item(MENU_VIEW_SHOW_ROADS_RAILS),MUIA_Menuitem_Checked))
//            request_toggle_roads_rails();
//	  break;
//    case  MENU_VIEW_SHOW_IRRIGATION:
//	  if (draw_irrigation != xget(menu_find_item(MENU_VIEW_SHOW_IRRIGATION),MUIA_Menuitem_Checked))
//            request_toggle_irrigation();
//	  break;
//    case  MENU_VIEW_SHOW_MINES:
//	  if (draw_mines != xget(menu_find_item(MENU_VIEW_SHOW_MINES),MUIA_Menuitem_Checked))
//            request_toggle_mines();
//	  break;
//    case  MENU_VIEW_SHOW_FORTRESTerrain_H.S_AIRBASE:
//	  if (draw_fortress_airbase != xget(menu_find_item(MENU_VIEW_SHOW_FORTRESTerrain_H.S_AIRBASE),MUIA_Menuitem_Checked))
//            request_toggle_fortress_airbase();
//	  break;
//    case  MENU_VIEW_SHOW_SPECIALS:
//	  if (draw_specials != xget(menu_find_item(MENU_VIEW_SHOW_SPECIALS),MUIA_Menuitem_Checked))
//            request_toggle_specials();
//	  break;
//    case  MENU_VIEW_SHOW_POLLUTION:
//	  if (draw_pollution != xget(menu_find_item(MENU_VIEW_SHOW_POLLUTION),MUIA_Menuitem_Checked))
//            request_toggle_pollution();
//	  break;
//    case  MENU_VIEW_SHOW_CITIES:
//	  if (draw_cities != xget(menu_find_item(MENU_VIEW_SHOW_CITIES),MUIA_Menuitem_Checked))
//            request_toggle_cities();
//	  break;
//    case  MENU_VIEW_SHOW_UNITS:
//	  if (draw_specials != xget(menu_find_item(MENU_VIEW_SHOW_UNITS),MUIA_Menuitem_Checked))
//            request_toggle_units();
//	  break;
//    case  MENU_VIEW_SHOW_FOCUS_UNIT:
//	  if (draw_focus_unit != xget(menu_find_item(MENU_VIEW_SHOW_FOCUS_UNIT),MUIA_Menuitem_Checked))
//            request_toggle_focus_unit();
//	  break;
//    case  MENU_VIEW_SHOW_FOG_OF_WAR:
//	  if (draw_fog_of_war != xget(menu_find_item(MENU_VIEW_SHOW_FOG_OF_WAR),MUIA_Menuitem_Checked))
//            request_toggle_focus_unit();
//	  break;
//
//    case MENU_VIEW_CENTER_VIEW: request_center_focus_unit(); break;
//    case MENU_ORDER_AUTO_SETTLER: key_unit_auto_settle(); break;
//    case MENU_ORDER_AUTO_ATTACK: key_unit_auto_attack();break;
//    case MENU_ORDER_MINE: key_unit_mine(); break;
//    case MENU_ORDER_IRRIGATE: key_unit_irrigate(); break;
//    case MENU_ORDER_TRANSFORM: key_unit_transform(); break;
//    case MENU_ORDER_FORTRESS: key_unit_fortress(); break;
//    case MENU_ORDER_AIRBASE: key_unit_airbase(); break;
//    case MENU_ORDER_BUILD_CITY: key_unit_build_city(); break;
//    case MENU_ORDER_ROAD: key_unit_road(); break;
//    case MENU_ORDER_CONNECT:
//      if(get_unit_in_focus())
//        request_unit_connect();
//      break;
//    case MENU_ORDER_PATROL:
//      if(get_unit_in_focus())
//        request_unit_patrol();
//      break;
//    case MENU_ORDER_POLLUTION:
//      if (get_unit_in_focus()) {
//	if (can_unit_paradrop(get_unit_in_focus()))
//          key_unit_paradrop();
//	else
//          key_unit_pollution();
//      }
//      break;
//    case MENU_ORDER_FALLOUT:
//      key_unit_fallout();
//      break;
//    case MENU_ORDER_FORTIFY:
//      key_unit_fortify();
//      break;
//    case MENU_ORDER_SENTRY:
//      key_unit_sentry();
//      break;
//    case MENU_ORDER_PILLAGE:
//      key_unit_pillage();
//      break;
//    case MENU_ORDER_AUTO_EXPLORE:
//      key_unit_auto_explore();
//      break;
//    case MENU_ORDER_HOMECITY:
//      key_unit_homecity();
//      break;
//    case MENU_ORDER_WAIT:
//      key_unit_wait();
//      break;
//    case MENU_ORDER_UNLOAD:
//      key_unit_unload_all();
//      break;
//    case MENU_ORDER_WAKEUP_OTHERS:
//      key_unit_wakeup_others();
//      break;
//    case MENU_ORDER_GOTO:
//      key_unit_goto();
//      break;
//    case MENU_ORDER_GOTO_CITY:
//      if (get_unit_in_focus())
//	popup_goto_dialog();
//      break;
//    case MENU_ORDER_DISBAND:
//      key_unit_disband();
//      break;
//    case MENU_ORDER_BUILD_WONDER:
//      key_unit_build_wonder();
//      break;
//    case MENU_ORDER_TRADEROUTE:
//      key_unit_traderoute();
//      break;
//    case MENU_ORDER_DIPLOMAT_DLG:
//      if(get_unit_in_focus())
//        key_unit_diplomat_actions();
//      break;
//    case MENU_ORDER_DONE:
//      key_unit_done();
//      break;
//    case MENU_ORDER_NUKE:
//      key_unit_nuke();
//      break;
//
//    case MENU_REPORT_CITY:
//      popup_city_report_dialog(0);
//      break;
//    case MENU_REPORT_SCIENCE:
//      popup_science_dialog(0);
//      break;
//    case MENU_REPORT_TRADE:
//      popup_economy_report_dialog(0);
//      break;
//    case MENU_REPORT_MILITARY:
//      popup_activeunits_report_dialog(0);
//      break;
//    case MENU_REPORT_DEMOGRAPHIC:
//      send_report_request(REPORT_DEMOGRAPHIC);
//      break;
//    case MENU_REPORT_TOP_CITIES:
//      send_report_request(REPORT_TOP_5_CITIES);
//      break;
//    case MENU_REPORT_WOW:
//      send_report_request(REPORT_WONDERS_OF_THE_WORLD);
//      break;
//    case MENU_REPORT_SPACESHIP:
//      popup_spaceship_dialog(game.player_ptr);
//      break;
//
//    case MENU_HELP_CONTROLS:
//      popup_help_dialog_string(HELP_CONTROLS_ITEM);
//      break;
//    case MENU_HELP_PLAYING:
//      popup_help_dialog_string(HELP_PLAYING_ITEM);
//      break;
//    case MENU_HELP_IMPROVEMENTS:
//      popup_help_dialog_string(HELP_IMPROVEMENTS_ITEM);
//      break;
//    case MENU_HELP_UNITS:
//      popup_help_dialog_string(HELP_UNITS_ITEM);
//      break;
//    case MENU_HELP_COMBAT:
//      popup_help_dialog_string(HELP_COMBAT_ITEM);
//      break;
//    case MENU_HELP_ZOC:
//      popup_help_dialog_string(HELP_ZOC_ITEM);
//      break;
//    case MENU_HELP_TECH:
//      popup_help_dialog_string(HELP_TECHS_ITEM);
//      break;
//    case MENU_HELP_TERRAIN:
//      popup_help_dialog_string(HELP_TERRAIN_ITEM);
//      break;
//    case MENU_HELP_WONDERS:
//      popup_help_dialog_string(HELP_WONDERS_ITEM);
//      break;
//    case MENU_HELP_GOVERNMENT:
//      popup_help_dialog_string(HELP_GOVERNMENT_ITEM);
//      break;
//    case MENU_HELP_HAPPINESS:
//      popup_help_dialog_string(HELP_HAPPINESS_ITEM);
//      break;
//    case MENU_HELP_SPACE_RACE:
//      popup_help_dialog_string(HELP_SPACE_RACE_ITEM);
//      break;
//    case MENU_HELP_COPYING:
//      popup_help_dialog_string(HELP_COPYING_ITEM);
//      break;
//    case MENU_HELP_ABOUT:
//      popup_help_dialog_string(HELP_ABOUT_ITEM);
//      break;
//    case MENU_HELP_CONNECTING:
//      popup_help_dialog_string(HELP_CONNECTING_ITEM);
//      break;
//    case MENU_HELP_CHATLINE:
//      popup_help_dialog_string(HELP_CHATLINE_ITEM);
//      break;
//    case MENU_HELP_LANGUAGES:
//      popup_help_dialog_string(HELP_LANGUAGES_ITEM);
//      break;
//    }
//  }
//}
//
///****************************************************************
// Do a function on a special unit
//*****************************************************************/
//void do_unit_function(unit punit, ULONG value)
//{
//  if (value != UNIT_ACTIVATE)
//  {
//    unit punit_oldfocus = get_unit_in_focus();
//    set_unit_focus(punit);
//    control_callback(&value);
//    set_unit_focus(punit_oldfocus);
//  }
//  else
//  {
//    if(can_unit_do_activity(punit, unit_activity.ACTIVITY_IDLE)) {
//      set_unit_focus_and_select(punit);
//    }
//  }
//}
//
///****************************************************************
// Callback for Taxrates sprites in the main window
//*****************************************************************/
//static void taxrates_callback(LONG * number)
//{
//  int lux_end, sci_end;
//  size_t i;
//  int delta = 10;
//  struct packet_player_request packet;
//
//  if (!can_client_issue_orders()) {
//    return;
//  }
//
//  i = (size_t) * number;
//
//  lux_end = game.player_ptr.economic.luxury;
//  sci_end = lux_end + game.player_ptr.economic.science;
//
//  packet.luxury = game.player_ptr.economic.luxury;
//  packet.science = game.player_ptr.economic.science;
//  packet.tax = game.player_ptr.economic.tax;
//
//  i *= 10;
//  if (i < lux_end)
//  {
//    packet.luxury -= delta;
//    packet.science += delta;
//  }
//  else if (i < sci_end)
//  {
//    packet.science -= delta;
//    packet.tax += delta;
//  }
//  else
//  {
//    packet.tax -= delta;
//    packet.luxury += delta;
//  }
//  send_packet_player_request(&aconnection, &packet, PACKET_PLAYER_RATES);
//}
//
///****************************************************************
// Sends a timer
//*****************************************************************/
//static timerequest send_timer(ULONG secs, ULONG mics)
//{
//  timerequest treq = (timerequest ) AllocVec(sizeof(struct timerequest), MEMF_CLEAR | MEMF_PUBLIC);
//  if (treq)
//  {
//    *treq = *timer_req;
//    treq.tr_node.io_Command = TR_ADDREQUEST;
//    treq.tr_time.tv_secs = secs;
//    treq.tr_time.tv_micro = mics;
//    SendIO((IORequest ) treq);
//    timer_outstanding++;
//  }
//  return treq;
//}
//
///****************************************************************
// Cleanup the timer initialisations
//*****************************************************************/
//static void free_timer()
//{
//  if (timer_req)
//  {
//    if (timer_req.tr_node.io_Device)
//    {
//      while (timer_outstanding)
//      {
//	if (Wait(1L << timer_port.mp_SigBit | 4096) & 4096)
//	  break;
//	timer_outstanding--;
//      }
//
//      CloseDevice((IORequest ) timer_req);
//    }
//    DeleteIORequest(timer_req);
//  }
//
//  if (timer_port)
//    DeleteMsgPort(timer_port);
//}
//
///****************************************************************
// Initialize the timer stuff
//*****************************************************************/
//static int init_timer()
//{
//  if ((timer_port = CreateMsgPort()))
//  {
//    if (timer_req = (timerequest ) CreateIORequest(timer_port, sizeof(struct timerequest)))
//    {
//      if (!OpenDevice(TIMERNAME, UNIT_VBLANK, (IORequest ) timer_req, 0))
//      {
//	return true;
//      }
//      free_timer();
//    }
//  }
//  return false;
//}
//
///****************************************************************
// Remove the custom classes
//*****************************************************************/
//static void free_classes()
//{
//  delete_transparentstring_class();
//  delete_autogroup_class();
//  delete_colortext_class();
//  delete_historystring_class();
//  delete_scrollbutton_class();
//  delete_worklist_class();
//  delete_objecttree_class();
//  delete_map_class();
//  delete_overview_class();
//}
//
///****************************************************************
// Add custom classes
//*****************************************************************/
//static int init_classes()
//{
//  if (create_overview_class())
//    if (create_map_class())
//      if (create_objecttree_class())
//        if (create_worklist_class())
//          if (create_scrollbutton_class())
//	    if (create_historystring_class())
//	      if (create_colortext_class())
//		if (create_autogroup_class())
//		  if (create_transparentstring_class())
//		    return true;
//  return false;
//}
//
///****************************************************************
// Cleanup the gui initialisations
//*****************************************************************/
//static void free_gui()
//{
//  if (app)
//    MUI_DisposeObject(app);
//  free_classes();
//}
//
///****************************************************************
// Init gui
//*****************************************************************/
//static int init_gui()
//{
//#ifdef ENABLE_NLS
//  NewMenu nm;
//
//  for (nm = MenuData; nm.nm_Type != NM_END; nm++) {
//    if(nm.nm_Label != NM_BARLABEL)
//      nm.nm_Label = _(nm.nm_Label);
//  }
//#endif
//
//  init_civstandard_hook();
//  if (!init_classes())
//    return false;
//
//  app = ApplicationObject,
//    MUIA_Application_Title, "Freeciv Client",
//    MUIA_Application_Version, VERSIONSTRING,
//    MUIA_Application_Copyright, COPYRIGHTSTRING,
//    MUIA_Application_Author, AUTHORSTRING,
//    MUIA_Application_Description, "Client for Freeciv",
//    MUIA_Application_Base, "CIVCLIENT",
//
//    SubWindow, main_wnd = WindowObject,
//        MUIA_Window_Title, "Freeciv",
//        MUIA_Window_ID, MAKE_ID('M','A','I','N'),
//        MUIA_Window_Menustrip, main_menu = MakeMenu(MenuData),
//
//        WindowContents, VGroup,
//            Child, HGroup,
//                MUIA_Weight, 100,
//                Child, VGroup,
//                    MUIA_HorizWeight, 0,
//                    Child, main_overview_group = HGroup,
//                        Child, main_overview_area = MakeOverview(80, 50),
//                        End,
//                    Child, VGroup,
//                        TextFrame,
//                        Child, main_people_text = TextObject, End,
//                        Child, main_year_text = TextObject, End,
//                        Child, main_gold_text = TextObject, End,
//                        Child, main_tax_text = TextObject, End,
//                        End,
//                    Child, main_info_group = VGroup,
//                        Child, main_turndone_group = HGroup,
//                            Child, main_turndone_button = MakeButton("Turn\nDone"),
//                            End,
//                        End,
//                    Child, HGroup,
//                        TextFrame,
//                        Child, VGroup,
//                            Child, main_unitname_text = TextObject, End,
//                            Child, main_moves_text = TextObject, End,
//                            Child, main_terrain_text = TextObject, End,
//                            Child, main_hometown_text = TextObject, End,
//                            End,
//                        Child, main_unit_unit = UnitObject, End,
//                        End,
//                    Child, main_below_group = AutoGroup,
//			MUIA_AutoGroup_DefVertObjects, 1,
//			Child, HVSpace,
//			End,
//                    Child, RectangleObject, MUIA_Weight,1,End,
//                    End,
//
//                Child, ColGroup(2), /* Map and scrollers */
//                    MUIA_Group_Spacing, 0,
//                    Child, main_map_area = MakeMap(),
//                    Child, main_map_vscroller = ScrollbarObject,
//                        MUIA_Group_Horiz, false,
//                        End,
//
//                    Child, main_map_hscroller = ScrollbarObject,
//                        MUIA_Group_Horiz, true,
//                        End,
//                    Child, main_map_scrollbutton = ScrollButtonObject, End,
//                    End,
//                End,
//            Child, BalanceObject, End,
//            Child, NListviewObject,
//                MUIA_Weight, 30,
//                MUIA_NListview_NList, main_output_listview = NListObject,
//                    ReadListFrame,
//                    MUIA_CycleChain, 1,
//                    MUIA_NList_Input, false,
//                    MUIA_NList_TypeSelect, MUIV_NList_TypeSelect_Char,
//                    MUIA_NList_finalructHook, MUIV_NList_finalructHook_String,
//                    MUIA_NList_DestructHook, MUIV_NList_DestructHook_String,
//                    MUIA_NList_AutoCopyToClip, true,
//                    End,
//                End,
//            Child, main_chatline_string = HistoryStringObject,
//		StringFrame,
//		End,
//            End,
//        End,
//    End;
//
//  if (app)
//  {
//    LONG i = 0;
//
//    main_order_menu = (Object *) DoMethod(main_menu, MUIM_FindUData, MENU_ORDER);
//
//    /* Main Wnd */
//    DoMethod(main_wnd, MUIM_Notify, MUIA_Window_CloseRequest, true, app, 2, MUIM_Application_ReturnID, MUIV_Application_ReturnID_Quit);
//
//    SetAttrs(main_map_area,
//	     MUIA_CycleChain,1,
//	     MUIA_Map_Overview, main_overview_area,
//	     MUIA_Map_HScroller, main_map_hscroller,
//	     MUIA_Map_VScroller, main_map_vscroller,
//	     MUIA_Map_ScrollButton, main_map_scrollbutton,
//	     TAG_DONE);
//
//    set(main_wnd,MUIA_Window_DefaultObject, main_map_area);
//
//    DoMethod(main_map_area, MUIM_Notify, MUIA_Map_Click, MUIV_EveryTime, app, 4, MUIM_CallHook, &civstandard_hook, main_map_click, MUIV_TriggerValue);
//    DoMethod(main_map_area, MUIM_Notify, MUIA_Map_MouseMoved, true, app, 3, MUIM_CallHook, &civstandard_hook, create_line_at_mouse_pos);
//    DoMethod(main_chatline_string, MUIM_Notify, MUIA_String_Acknowledge, MUIV_EveryTime, app, 3, MUIM_CallHook, &civstandard_hook, inputline_return);
//    DoMethod(main_turndone_button, MUIM_Notify, MUIA_Pressed, false, main_wnd, 4, MUIM_CallHook, &civstandard_hook, control_callback, END_TURN);
//
//    DoMethod(main_wnd, MUIM_Notify, MUIA_Window_InputEvent, "rcommand q", main_wnd, 4, MUIM_CallHook, &civstandard_hook, control_callback, MENU_GAME_QUIT);
//    DoMethod(main_wnd, MUIM_Notify, MUIA_Window_InputEvent, "numericpad 4", main_wnd, 4, MUIM_CallHook, &civstandard_hook, control_callback, UNIT_WEST);
//    DoMethod(main_wnd, MUIM_Notify, MUIA_Window_InputEvent, "numericpad 6", main_wnd, 4, MUIM_CallHook, &civstandard_hook, control_callback, UNIT_EAST);
//    DoMethod(main_wnd, MUIM_Notify, MUIA_Window_InputEvent, "numericpad 8", main_wnd, 4, MUIM_CallHook, &civstandard_hook, control_callback, UNIT_NORTH);
//    DoMethod(main_wnd, MUIM_Notify, MUIA_Window_InputEvent, "numericpad 2", main_wnd, 4, MUIM_CallHook, &civstandard_hook, control_callback, UNIT_SOUTH);
//    DoMethod(main_wnd, MUIM_Notify, MUIA_Window_InputEvent, "numericpad 7", main_wnd, 4, MUIM_CallHook, &civstandard_hook, control_callback, UNIT_NORTH_WEST);
//    DoMethod(main_wnd, MUIM_Notify, MUIA_Window_InputEvent, "numericpad 9", main_wnd, 4, MUIM_CallHook, &civstandard_hook, control_callback, UNIT_NORTH_EAST);
//    DoMethod(main_wnd, MUIM_Notify, MUIA_Window_InputEvent, "numericpad 1", main_wnd, 4, MUIM_CallHook, &civstandard_hook, control_callback, UNIT_SOUTH_WEST);
//    DoMethod(main_wnd, MUIM_Notify, MUIA_Window_InputEvent, "numericpad 3", main_wnd, 4, MUIM_CallHook, &civstandard_hook, control_callback, UNIT_SOUTH_EAST);
//    DoMethod(main_wnd, MUIM_Notify, MUIA_Window_InputEvent, "numericpad 5", main_wnd, 4, MUIM_CallHook, &civstandard_hook, control_callback, NEXT_UNIT);
//    DoMethod(main_wnd, MUIM_Notify, MUIA_Window_InputEvent, "numericpad enter", main_wnd, 4, MUIM_CallHook, &civstandard_hook, control_callback, END_TURN);
//    DoMethod(main_wnd, MUIM_Notify, MUIA_Window_InputEvent, "return", main_wnd, 4, MUIM_CallHook, &civstandard_hook, control_callback, UNIT_POPUP_CITY);
//    DoMethod(main_wnd, MUIM_Notify, MUIA_Window_InputEvent, "escape", main_wnd, 4, MUIM_CallHook, &civstandard_hook, control_callback, UNIT_ESCAPE);
//    DoMethod(main_wnd, MUIM_Notify, MUIA_Window_InputEvent, "help", main_wnd, 4, MUIM_CallHook, &civstandard_hook, control_callback, MENU_HELP_ABOUT);
//
//    /* Menu */
//    while (MenuData[i].nm_Type != NM_END)
//    {
//      if(MenuData[i].nm_Flags & NM_COMMANDSTRING && MenuData[i].nm_CommKey &&
//      (MenuData[i].nm_UserData != (APTR) MENU_VIEW_SHOW_MAP_GRID) && (MenuData[i].nm_UserData != (APTR) MENU_KINGDOM_FIND_CITY))
//      {
//	DoMethod(main_wnd, MUIM_Notify, MUIA_Window_InputEvent, MenuData[i].nm_CommKey, main_wnd, 4, MUIM_CallHook, &civstandard_hook, control_callback, MenuData[i].nm_UserData);
//      }
//      i++;
//    }
//
//    /* Do this outside loop. The menu entry are upper case and thus would need SHIFT be pressed. */
//    DoMethod(main_wnd, MUIM_Notify, MUIA_Window_InputEvent, "ctrl f", main_wnd, 4, MUIM_CallHook, &civstandard_hook, control_callback, MENU_KINGDOM_FIND_CITY);
//    DoMethod(main_wnd, MUIM_Notify, MUIA_Window_InputEvent, "ctrl g", main_wnd, 4, MUIM_CallHook, &civstandard_hook, control_callback, KEMAP_GRID_TOGGLE);
//
//    DoMethod(main_wnd, MUIM_Notify, MUIA_Window_MenuAction, MUIV_EveryTime, main_wnd, 4, MUIM_CallHook, &civstandard_hook, control_callback, MUIV_TriggerValue);
//
//    append_output_window(
//      ("Freeciv is free software and you are welcome to distribute copies of" +
//       " it\nunder certain conditions; See the \"Copying\" item on the Help" +
//			  " menu.\nNow.. Go give'em hell!"));
//
//    return true;
//  }
//  return false;
//}
//
///****************************************************************
// The main loop
//*****************************************************************/
//static void loop()
//{
//  ULONG sigs = 0;
//  ULONG secs, mics;
//  CurrentTime(&secs, &mics);
//
//  while (DoMethod(app, MUIM_Application_NewInput, &sigs) != (ULONG) MUIV_Application_ReturnID_Quit)
//  {
//    ULONG timer_sig = (1L << timer_port.mp_SigBit);
//
//    if (sigs)
//    {
//      ULONG mask = sigs | SIGBREAKF_CTRL_C | SIGBREAKF_CTRL_D | timer_sig;
//      boolean force_timer = false;
//
//      if (connected)
//      {
//	fd_set readfs;
//	int sel;
//
//	MY_FD_ZERO(&readfs);
//	FD_SET(aconnection.sock, &readfs);
//
//	sel = WaitSelect(connected_sock + 1, &readfs, null, null, null, &mask);
//	if (sel > 0)
//	{
//	  if (FD_ISSET(connected_sock, &readfs))	/* handle_net_input(); in clinet.c */
//
//	    input_from_server(connected_sock);
//	}
//	else if (sel < 0)
//	{
//	  printf("%s\n", strerror(errno));
//	  break;
//	}
//      }
//      else
//	mask = Wait(mask);
//
//      if (!(mask & timer_sig))
//      {
//	ULONG newsecs, newmics;
//	CurrentTime(&newsecs, &newmics);
//	if (newsecs - secs > 4)
//	  force_timer = true;
//      }
//
//      if ((mask & SIGBREAKF_CTRL_C) || (mask & SIGBREAKF_CTRL_D))
//	break;
//      if ((mask & timer_sig) || force_timer)
//      {
//	timerequest tr;
//
//	/* Remove the timer from the port */
//	while ((tr = (timerequest ) GetMsg(timer_port)))
//	{
//	  FreeVec(tr);
//	  timer_outstanding--;
//	}
//
//	if (!timer_outstanding) send_timer(0, TIMER_INTERVAL * 1000);
//	CurrentTime(&secs, &mics);
//
//	handle_timer();
//      }
//
//      sigs = mask;
//    }
//  }
//
//}
//
///****************************************************************
// Find an item with the given Userdata
//*****************************************************************/
//static Object *menu_find_item(ULONG udata)
//{
//  if (udata >= MENU_ORDER && udata < MENU_REPORT)
//  {
//    return (Object *) DoMethod(main_order_menu, MUIM_FindUData, udata);
//  }
//  return (Object *) DoMethod(main_menu, MUIM_FindUData, udata);
//}
//
///****************************************************************
// Enable/Disable a menu entry
//*****************************************************************/
//static void menu_entry_sensitive(ULONG udata, ULONG sens)
//{
//  Object *item = menu_find_item(udata);
//  if (item)
//  {
//    if (xget(item, MUIA_Menuitem_Enabled) != sens)
//      set(item, MUIA_Menuitem_Enabled, sens);
//  }
//}
//
///****************************************************************
// Enable/Disable a menu title
//*****************************************************************/
//static void menu_title_sensitive(ULONG udata, ULONG sens)
//{
//  Object *item = menu_find_item(udata);
//  if (item)
//  {
//    if (xget(item, MUIA_Menu_Enabled) != sens)
//      set(item, MUIA_Menu_Enabled, sens);
//  }
//}
//
///****************************************************************
// Rename a menu entry
//*****************************************************************/
//static void menu_entry_rename(ULONG udata, char *newtitle, boolean force)
//{
//  Object *item = menu_find_item(udata);
//  if (item)
//  {
//    if (force || ((char *) xget(item, MUIA_Menuitem_Title) != newtitle))
//      set(item, MUIA_Menuitem_Title, newtitle);
//  }
//}
//
///**************************************************************************
// This function is called after the client succesfully
// has connected to the server
//**************************************************************************/
//void add_net_input(int sock)
//{
//  connected = true;
//  connected_sock = sock;
//}
//
///**************************************************************************
// This function is called if the client disconnects
// from the server
//**************************************************************************/
//void remove_net_input()
//{
//  connected_sock = 0;
//  connected = false;
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void update_menus() /* from menu.c */
//{
//  if (!can_client_change_view()) {
//    menu_title_sensitive(MENU_REPORT, false);
//    menu_title_sensitive(MENU_ORDER, false);
//    menu_title_sensitive(MENU_VIEW, false);
//    menu_title_sensitive(MENU_KINGDOM, false);
//  }
//  else
//  {
//    unit punit;
//    int i;
//    int any_cities = false;
//    punit = get_unit_in_focus();
//
//    for (i = 0; i < game.nplayers; i++)
//    {
//      if (city_list_size(&game.players[i].cities))
//      {
//	any_cities = true;
//	break;
//      }
//    }
//
//    menu_title_sensitive(MENU_REPORT, true);
//    menu_title_sensitive(MENU_ORDER, punit
//			 ? can_client_issue_orders() : false);
//    menu_title_sensitive(MENU_VIEW, true);
//    menu_title_sensitive(MENU_KINGDOM, true);
//
//    menu_title_sensitive(MENU_KINGDOM_TAX_RATE, can_client_issue_orders());
//    menu_title_sensitive(MENU_KINGDOM_WORKLISTS, can_client_issue_orders());
//    menu_title_sensitive(MENU_KINGDOM_REVOLUTION, can_client_issue_orders());
//
//    menu_entry_sensitive(MENU_REPORT_SPACESHIP, (game.player_ptr.spaceship.state != spaceship_state.SSHIP_NONE));
//
//    if (punit && can_client_issue_orders()) {
//      final String chgfmt = "Change to %s";
//      static char irrtext[64];
//      static char mintext[64];
//      static char transtext[64];
//      Terrain_type_id ttype;
//      tile_type tinfo;
//
//      set(main_menu, MUIA_Window_Menustrip, null);
//
//      menu_entry_sensitive(MENU_ORDER_BUILD_CITY,
//			   can_unit_add_or_build_city(punit));
//      menu_entry_sensitive(MENU_ORDER_ROAD, can_unit_do_activity(punit, ACTIVITY_ROAD) || can_unit_do_activity(punit, ACTIVITY_RAILROAD));
//      menu_entry_sensitive(MENU_ORDER_IRRIGATE, can_unit_do_activity(punit, ACTIVITY_IRRIGATE));
//      menu_entry_sensitive(MENU_ORDER_MINE, can_unit_do_activity(punit, ACTIVITY_MINE));
//      menu_entry_sensitive(MENU_ORDER_TRANSFORM, can_unit_do_activity(punit, ACTIVITY_TRANSFORM));
//      menu_entry_sensitive(MENU_ORDER_FORTRESS, can_unit_do_activity(punit, ACTIVITY_FORTRESS));
//
//      if (can_unit_paradrop(punit))
//      {
//	menu_entry_rename(MENU_ORDER_POLLUTION, "Paradrop", false);
//	menu_entry_sensitive(MENU_ORDER_POLLUTION, true);
//      }
//      else
//      {
//	menu_entry_rename(MENU_ORDER_POLLUTION, "Clean Pollution", false);
//	menu_entry_sensitive(MENU_ORDER_POLLUTION, can_unit_do_activity(punit, ACTIVITY_POLLUTION));
//      }
//
//      menu_entry_sensitive(MENU_ORDER_FALLOUT, can_unit_do_activity(punit, ACTIVITY_FALLOUT));
//
//      menu_entry_sensitive(MENU_ORDER_FORTIFY, can_unit_do_activity(punit, ACTIVITY_FORTIFYING));
//      menu_entry_sensitive(MENU_ORDER_AIRBASE, can_unit_do_activity(punit, ACTIVITY_AIRBASE));
//      menu_entry_sensitive(MENU_ORDER_SENTRY, can_unit_do_activity(punit, ACTIVITY_SENTRY));
//      menu_entry_sensitive(MENU_ORDER_PILLAGE, can_unit_do_activity(punit, ACTIVITY_PILLAGE));
//      menu_entry_sensitive(MENU_ORDER_HOMECITY, can_unit_change_homecity(punit));
//      menu_entry_sensitive(MENU_ORDER_UNLOAD,
//			   get_transporter_occupancy(punit) > 0);
//      menu_entry_sensitive(MENU_ORDER_WAKEUP_OTHERS, is_unit_activity_on_tile(ACTIVITY_SENTRY, punit.tile));
//      menu_entry_sensitive(MENU_ORDER_AUTO_SETTLER, (can_unit_do_auto(punit) && unit_flag(punit, F_SETTLERS)));
//      menu_entry_sensitive(MENU_ORDER_AUTO_ATTACK, (can_unit_do_auto(punit) && !unit_flag(punit, F_SETTLERS)));
//      menu_entry_sensitive(MENU_ORDER_AUTO_EXPLORE, can_unit_do_activity(punit, ACTIVITY_EXPLORE));
//      menu_entry_sensitive(MENU_ORDER_CONNECT, can_unit_do_connect(punit, unit_activity.ACTIVITY_IDLE));
//      menu_entry_sensitive(MENU_ORDER_GOTO_CITY, any_cities);
//      menu_entry_sensitive(MENU_ORDER_BUILD_WONDER, unit_can_help_build_wonder_here(punit));
//      menu_entry_sensitive(MENU_ORDER_TRADEROUTE, unit_can_est_traderoute_here(punit));
//      menu_entry_sensitive(MENU_ORDER_NUKE, unit_flag(punit, F_NUCLEAR));
//      menu_entry_sensitive(MENU_ORDER_DIPLOMAT_DLG, is_diplomat_unit(punit) &&
//        diplomat_can_do_action(punit, DIPLOMAT_ANY_ACTION, punit.tile));
//
//      if (unit_flag(punit, F_CITIES) && map_get_city(punit.tile))
//      {
//	menu_entry_rename(MENU_ORDER_BUILD_CITY, "Add to City", false);
//      }
//      else
//      {
//	menu_entry_rename(MENU_ORDER_BUILD_CITY, "Build City", false);
//      }
//
//      ttype = map_get_tile(punit.tile).terrain;
//      tinfo = get_tile_type(ttype);
//      if ((tinfo.irrigation_result != T_NONE)
//	  && (tinfo.irrigation_result != ttype)) {
//	irrtext = util.my_snprintf( chgfmt,
//		    (get_tile_type(tinfo.irrigation_result)).terrain_name);
//      } else if (map_has_special(punit.tile, S_IRRIGATION)
//		 && player_knows_techs_with_flag(game.player_ptr,
//						 TF_FARMLAND)) {
//	sz_strlcpy(irrtext, "Build Farmland");
//      } else {
//        sz_strlcpy(irrtext, "Build Irrigation");
//      }
//
//      if ((tinfo.mining_result != T_NONE)
//	  && (tinfo.mining_result != ttype)) {
//	mintext = util.my_snprintf( chgfmt,
//		    (get_tile_type(tinfo.mining_result)).terrain_name);
//      } else {
//        sz_strlcpy(mintext, "Build Mine");
//      }
//
//      if ((tinfo.transform_result != T_NONE)
//	  && (tinfo.transform_result != ttype)) {
//	transtext = util.my_snprintf( chgfmt,
//		    (get_tile_type(tinfo.transform_result)).terrain_name);
//      } else {
//        sz_strlcpy(transtext, "Transform Terrain");
//      }
//
//      if (map_has_special(punit.tile, S_ROAD)) {
//	menu_entry_rename(MENU_ORDER_ROAD, "Build Railroad", false);
//      } else {
//	menu_entry_rename(MENU_ORDER_ROAD, "Build Road", false);
//      }
//      menu_entry_rename(MENU_ORDER_IRRIGATE, irrtext, false);
//      menu_entry_rename(MENU_ORDER_MINE, mintext, false);
//      menu_entry_rename(MENU_ORDER_TRANSFORM, transtext, false);
//
//      set(main_menu, MUIA_Window_Menustrip, main_menu);
//    }
//  }
//}
//
///**************************************************************************
// Cleanup everything on exit
//**************************************************************************/
//void ui_exit()
//{
//  free_gui();
//  free_all_sprites();
//  free_timer();
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void ui_init()
//{
//}
//
///****************************************************************
// Entry for the client dependent part of the client
//*****************************************************************/
//void ui_main(int argc, char *argv[])
//{
//  parse_options(argc, argv);
//
//  atexit(ui_exit);
//
//  if (init_timer() && load_all_sprites())	/* includes tilespec_load_tiles */
//  {
//    if (init_gui())
//    {
//      Object *econ_group;
//
//      /* we need to load options again after this to pick up worklists */
////      load_options();
//
//      /* must be after load_options */
//      DoMethod(main_menu,MUIM_SetUData,MENU_VIEW_SHOW_MAP_GRID,MUIA_Menuitem_Checked,draw_map_grid);
//      DoMethod(main_menu,MUIM_SetUData,MENU_VIEW_SHOW_CITY_NAMES,MUIA_Menuitem_Checked,draw_city_names);
//      DoMethod(main_menu,MUIM_SetUData,MENU_VIEW_SHOW_CITY_PRODUCTIONS,MUIA_Menuitem_Checked,draw_city_productions);
//      DoMethod(main_menu,MUIM_SetUData,MENU_VIEW_SHOW_TERRAIN,MUIA_Menuitem_Checked,draw_terrain);
//      DoMethod(main_menu,MUIM_SetUData,MENU_VIEW_SHOW_COASTLINE,MUIA_Menuitem_Checked,draw_coastline);
//      DoMethod(main_menu,MUIM_SetUData,MENU_VIEW_SHOW_ROADS_RAILS,MUIA_Menuitem_Checked,draw_roads_rails);
//      DoMethod(main_menu,MUIM_SetUData,MENU_VIEW_SHOW_IRRIGATION,MUIA_Menuitem_Checked,draw_irrigation);
//      DoMethod(main_menu,MUIM_SetUData,MENU_VIEW_SHOW_MINES,MUIA_Menuitem_Checked,draw_mines);
//      DoMethod(main_menu,MUIM_SetUData,MENU_VIEW_SHOW_FORTRESTerrain_H.S_AIRBASE,MUIA_Menuitem_Checked,draw_fortress_airbase);
//      DoMethod(main_menu,MUIM_SetUData,MENU_VIEW_SHOW_SPECIALS,MUIA_Menuitem_Checked,draw_specials);
//      DoMethod(main_menu,MUIM_SetUData,MENU_VIEW_SHOW_POLLUTION,MUIA_Menuitem_Checked,draw_pollution);
//      DoMethod(main_menu,MUIM_SetUData,MENU_VIEW_SHOW_CITIES,MUIA_Menuitem_Checked,draw_cities);
//      DoMethod(main_menu,MUIM_SetUData,MENU_VIEW_SHOW_UNITS,MUIA_Menuitem_Checked,draw_specials);
//      DoMethod(main_menu,MUIM_SetUData,MENU_VIEW_SHOW_FOCUS_UNIT,MUIA_Menuitem_Checked,draw_focus_unit);
//      DoMethod(main_menu,MUIM_SetUData,MENU_VIEW_SHOW_FOG_OF_WAR,MUIA_Menuitem_Checked,draw_fog_of_war);
//
//      /* TODO: Move this into init_gui() */
//      main_bulb_sprite = MakeBorderSprite(sprites.bulb[0]);
//      main_sun_sprite = MakeBorderSprite(sprites.warming[0]);
//      main_flake_sprite = MakeBorderSprite(sprites.cooling[0]);
//      {
//	/* HACK: the UNHAPPY citizen is used for the government
//	 * when we don't know any better. */
//	struct citizen_type c = {.type = CITIZEN_UNHAPPY};
//	Sprite sprite = get_citizen_sprite(c, 0, null);
//
//	main_government_sprite = MakeBorderSprite(sprite);
//      }
//      main_timeout_text = TextObject, End;
//
//      econ_group = HGroup, GroupSpacing(0), End;
//      if (econ_group)
//      {
//	int i;
//	for (i = 0; i < 10; i++)
//	{
//	  Object *o = main_econ_sprite[i] = MakeSprite(sprites.warming[0]);
//	  if (o)
//	  {
//	    DoMethod(econ_group, OM_ADDMEMBER, o);
//	    DoMethod(o, MUIM_Notify, MUIA_Pressed, false, o, 4, MUIM_CallHook, &civstandard_hook, taxrates_callback, i);
//	  }
//	}
//      }
//
//      DoMethod(main_info_group, MUIM_Group_InitChange);
//      DoMethod(main_turndone_group, MUIM_Group_InitChange);
//
//      DoMethod(main_info_group, OM_ADDMEMBER, econ_group);
//      DoMethod(main_turndone_group, OM_ADDMEMBER, main_bulb_sprite);
//      DoMethod(main_turndone_group, OM_ADDMEMBER, main_sun_sprite);
//      DoMethod(main_turndone_group, OM_ADDMEMBER, main_flake_sprite);
//      DoMethod(main_turndone_group, OM_ADDMEMBER, main_government_sprite);
//      DoMethod(main_turndone_group, OM_ADDMEMBER, main_timeout_text);
//      DoMethod(main_info_group, MUIM_Group_Sort, econ_group, main_turndone_group, null);
//
//      DoMethod(main_turndone_group, MUIM_Group_ExitChange);
//      DoMethod(main_info_group, MUIM_Group_ExitChange);
//
//      set(main_wnd, MUIA_Window_Open, true);
//
//      if (xget(main_wnd, MUIA_Window_Open))
//      {
//	set_client_state(CLIENT_PRE_GAME_STATE);
//	send_timer(0, TIMER_INTERVAL * 1000);
//	loop();
//      }
//      else
//	printf("Couldn't open the main window (Gfx memory problem or screensize too small)\n");
//    }
//  }
//}
//
///**************************************************************************
//  Set one of the unit icons in information area based on punit.
//  Use punit==null to clear icon.
//  Index 'idx' is -1 for "active unit", or 0 to (num_units_below-1) for
//  units below.  Also updates unit_ids[idx] for idx>=0.
//  No real function for the Mui client.
//**************************************************************************/
//void set_unit_icon(int idx, unit punit)
//{
//}
//
///**************************************************************************
//  Set the "more arrow" for the unit icons to on(1) or off(0).
//  Maintains a static record of current state to avoid unnecessary redraws.
//  Note initial state should match initial gui setup (off).
//  No real function for the Mui client.
//**************************************************************************/
//void set_unit_icons_more_arrow(boolean onoff)
//{
//}
}