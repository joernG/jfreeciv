package client.gui_win32;

public class Menu{
///**********************************************************************
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
//#include <ctype.h> 
//#include <stdio.h>
//#include <stdlib.h>
//#include <string.h>  
//#include <windows.h>
//
//#include "astring.h"
//#include "capability.h"
//#include "fcintl.h"
//#include "log.h"
//#include "government.h"
//#include "Map.map.h"
//#include "mem.h"
//#include "support.h"
//#include "unit.h"
// 
//#include "chatline.h"
//#include "cityrep.h"
//#include "civclient.h"
//#include "clinet.h"
//#include "connectdlg_common.h"
//#include "control.h"
//#include "dialogs.h"
//#include "finddlg.h"
//#include "gotodlg.h"
//#include "gui_stuff.h"
//#include "gui_main.h"
//#include "helpdlg.h"
//
//#include "connectdlg.h"
//#include "mapctrl.h"   /* center_on_unit */
//#include "messagedlg.h"
//#include "messagewin.h"
//#include "optiondlg.h"
//#include "options.h"
//#include "packhand.h"
//#include "plrdlg.h"
//#include "ratesdlg.h"
//#include "repodlgs.h"
//#include "spaceshipdlg.h"
//#include "tilespec.h"
//#include "wldlg.h"   
//
//#include "menu.h"
//public static final int IDM_BEGIN = -3;
//public static final int IDM_SUBMENU = -2;
//public static final int IDM_SEPARATOR = -1;
//enum MenuID {
//  IDM_GAME_MENU=32,
//  IDM_GAME_LOCAL_OPTIONS,
//  IDM_GAME_MESSAGE_OPTIONS,
//  IDM_GAME_SAVE_SETTINGS,
//  IDM_GAME_SERVER_OPTIONS1,
//  IDM_GAME_SERVER_OPTIONS2,
//  IDM_GAME_SAVE_GAME,
//  IDM_GAME_SAVE_QUICK,
//  IDM_GAME_EXPORT_LOG, 
//  IDM_GAME_CLEAR_OUTPUT,
//  IDM_GAME_DISCONNECT,
//  IDM_GAME_QUIT,
//  
//  IDM_GOVERNMENT_MENU,
//  IDM_GOVERNMENT_TAX_RATE,
//  IDM_GOVERNMENT_FIND_CITY,
//  IDM_GOVERNMENT_WORKLISTS,
//  IDM_GOVERNMENT_REVOLUTION,
//  IDM_GOVERNMENT_CHANGE_FIRST,
//  IDM_GOVERNMENT_CHANGE_LAST = IDM_GOVERNMENT_CHANGE_FIRST + (G_MAGIC - 1),
//
//  IDM_VIEW_MENU,
//  IDM_VIEW_MAP_GRID, 
//  IDM_VIEW_CITY_NAMES,
//  IDM_VIEW_CITY_GROWTH,
//  IDM_VIEW_CITY_PRODUCTIONS,
//  IDM_VIEW_NATIONAL_BORDERS,
//  IDM_VIEW_TERRAIN,
//  IDM_VIEW_COASTLINE,
//  IDM_VIEW_ROADS_RAILS,
//  IDM_VIEW_IRRIGATION,
//  IDM_VIEW_MINES,
//  IDM_VIEW_FORTRESTerrain_H.S_AIRBASE,
//  IDM_VIEW_SPECIALS,
//  IDM_VIEW_POLLUTION,
//  IDM_VIEW_CITIES,
//  IDM_VIEW_UNITS,
//  IDM_VIEW_FOCUS_UNIT,
//  IDM_VIEW_FOG_OF_WAR,
//  IDM_VIEW_CENTER,
//
//  IDM_ORDERS_MENU,
//  IDM_ORDERS_BUILD_CITY,	/* shared with BUILD_WONDER */
//  IDM_ORDERTerrain_H.S_ROAD,		/* shared with TRADEROUTE */
//  IDM_ORDERS_IRRIGATE,
//  IDM_ORDERS_MINE,
//  IDM_ORDERS_TRANSFORM,
//  IDM_ORDERTerrain_H.S_FORTRESS,		/* shared with FORTIFY */
//  IDM_ORDERTerrain_H.S_AIRBASE, 
//  IDM_ORDERS_POLLUTION,		/* shared with PARADROP */
//  IDM_ORDERS_FALLOUT,
//  IDM_ORDERS_SENTRY,
//  IDM_ORDERS_PILLAGE,
//  IDM_ORDERS_HOMECITY,
//  IDM_ORDERS_LOAD,
//  IDM_ORDERS_UNLOAD,
//  IDM_ORDERS_WAKEUP_OTHERS,
//  IDM_ORDERS_AUTO_SETTLER,
//  IDM_ORDERS_AUTO_EXPLORE,
//  IDM_ORDERS_CONNECT_ROAD,
//  IDM_ORDERS_CONNECT_RAIL,
//  IDM_ORDERS_CONNECT_IRRIGATE,
//  IDM_ORDERS_PATROL,
//  IDM_ORDERS_GOTO,
//  IDM_ORDERS_GOTO_CITY,
//  IDM_ORDERS_RETURN,
//  IDM_ORDERS_DISBAND,
//  IDM_ORDERS_DIPLOMAT_DLG,
//  IDM_ORDERS_NUKE,
//  IDM_ORDERS_WAIT,
//  IDM_ORDERS_DONE,
//
//  IDM_REPORTS_MENU,
//  IDM_REPORTS_CITIES,
//  IDM_REPORTS_UNITS,
//  IDM_REPORTS_PLAYERS,
//  IDM_REPORTS_ECONOMY,
//  IDM_REPORTS_SCIENCE,
//  IDM_REPORTS_WONDERS,
//  IDM_REPORTS_TOP_CITIES,
//  IDM_REPORTS_MESSAGES,
//  IDM_REPORTS_DEMOGRAPHICS,
//  IDM_REPORTS_SPACESHIP,
//
//  IDM_HELP_MENU,
//  IDM_HELP_LANGUAGES,
//  IDM_HELP_CONNECTING,
//  IDM_HELP_CONTROLS,
//  IDM_HELP_CHATLINE,
//  IDM_HELP_WORKLIST_EDITOR,
//  IDM_HELP_CMA,
//  IDM_HELP_PLAYING,
//  IDM_HELP_IMPROVEMENTS,
//  IDM_HELP_UNITS,
//  IDM_HELP_COMBAT,
//  IDM_HELP_ZOC,
//  IDM_HELP_TECH,
//  IDM_HELP_TERRAIN,
//  IDM_HELP_WONDERS,
//  IDM_HELP_GOVERNMENT,
//  IDM_HELP_HAPPINESS,
//  IDM_HELP_SPACE_RACE,
//  IDM_HELP_COPYING,
//  IDM_HELP_ABOUT,
//
//  IDM_NUMPAD_BASE,
//  IDM_NUMPAD1,
//  IDM_NUMPAD2,
//  IDM_NUMPAD3,
//  IDM_NUMPAD4,
//  IDM_NUMPAD5,
//  IDM_NUMPAD6,
//  IDM_NUMPAD7,
//  IDM_NUMPAD8,
//  IDM_NUMPAD9
//};
//
//public static final int MAX_ACCEL = 512;
//static ACCEL numpadaccel[]={
//  { FVIRTKEY,VK_NUMPAD1,IDM_NUMPAD1},
//  { FVIRTKEY,VK_END,IDM_NUMPAD1},
//  { FVIRTKEY,VK_NUMPAD2,IDM_NUMPAD2},
//  { FVIRTKEY,VK_DOWN,IDM_NUMPAD2},
//  { FVIRTKEY,VK_NUMPAD3,IDM_NUMPAD3},
//  { FVIRTKEY,VK_NEXT,IDM_NUMPAD3},
//  { FVIRTKEY,VK_NUMPAD4,IDM_NUMPAD4},
//  { FVIRTKEY,VK_LEFT,IDM_NUMPAD4},
//  { FVIRTKEY,VK_NUMPAD5,IDM_NUMPAD5},
//  { FVIRTKEY,VK_NUMPAD6,IDM_NUMPAD6},
//  { FVIRTKEY,VK_RIGHT,IDM_NUMPAD6},
//  { FVIRTKEY,VK_NUMPAD7,IDM_NUMPAD7},
//  { FVIRTKEY,VK_HOME,IDM_NUMPAD7},
//  { FVIRTKEY,VK_NUMPAD8,IDM_NUMPAD8},
//  { FVIRTKEY,VK_UP,IDM_NUMPAD8},
//  { FVIRTKEY,VK_NUMPAD9,IDM_NUMPAD9},
//  { FVIRTKEY,VK_PRIOR,IDM_NUMPAD9},
//  { FVIRTKEY,VK_RETURN,ID_TURNDONE},
//  { FVIRTKEY,VK_ESCAPE,ID_ESCAPE}};
//static ACCEL menuaccel[MAX_ACCEL];
//static int accelcount;
//enum unit_activity road_activity;   
//
//struct my_menu {
//  char *name;
//  int id;
//};
//
///**************************************************************************
//  Create an accelerator table (keyboard shortcuts) from the scancode
//  array menuaccel[].
//**************************************************************************/
//HACCEL my_create_menu_acceltable()
//{
//  return CreateAcceleratorTable(menuaccel,accelcount);
//}
//
///**************************************************************************
//  Retrieve the scancode for a particular menu item's keyboard shortcut and
//  add it to the scancode array menuaccel[].
//**************************************************************************/
//static void my_add_menu_accelerator(char *item,int cmd)
//{
//  char *plus;
//  char *tab;
//  ACCEL newaccel;
//   
//  if (!accelcount) {
//    for (; accelcount < ARRAY_SIZE(numpadaccel); accelcount++) {
//      menuaccel[accelcount] = numpadaccel[accelcount];
//    }
//  }
// 
//  tab=strchr(item,'\t');
//  if(!tab)
//    return;
//  plus=strrchr(item,'+');
//  if (!plus)
//    plus=tab;
//  plus++;
//  tab++;
//  /* fkeys */
//  if ((*plus == 'F') && (Character.isDigit(plus[1]))) {
//    if (Character.isDigit(plus[2]))
//      newaccel.key=VK_F10+(plus[2]-'0');
//    else
//      newaccel.key=VK_F1+(plus[1]-'1');
//    newaccel.fVirt=FVIRTKEY;
//  } else if (*plus) { /* standard ascii */
//    newaccel.key = my_toupper(*plus);
//    newaccel.fVirt=FVIRTKEY;
//  } else {
//    return;
//  }
//  if (mystrncasecmp(plus,"Space",5)==0)
//    newaccel.key=VK_SPACE;
//  /* Modifiers (Alt,Shift,Ctl) */
//  if (strstr(tab, "Shift") != null) {
//    newaccel.fVirt |= FSHIFT;
//  }
//  if (strstr(tab, "Ctl") != null) {
//    newaccel.fVirt |= FCONTROL;
//  }
//  if (strstr(tab, "Alt") != null) {
//    newaccel.fVirt |= FALT;
//  }
//  newaccel.cmd=cmd;
//  if (accelcount<MAX_ACCEL) {
//    menuaccel[accelcount]=newaccel;
//    accelcount++;
//  }
//}
//
///**************************************************************************
//  Enable or disable a menu item by id.
//**************************************************************************/
//static void my_enable_menu(HMENU hmenu,int id,int state)
//{
//  if (state)
//    EnableMenuItem(hmenu,id,MF_BYCOMMAND | MF_ENABLED);
//  else
//    EnableMenuItem(hmenu,id,MF_BYCOMMAND | MF_GRAYED);
//}
//
///**************************************************************************
//  Check or uncheck a menu item by id.
//**************************************************************************/
//static void my_check_menu(HMENU hmenu, int id, int state)
//{
//  if (state)
//    CheckMenuItem(hmenu, id, MF_BYCOMMAND | MF_CHECKED);
//  else
//    CheckMenuItem(hmenu, id, MF_BYCOMMAND | MF_UNCHECKED);
//}
//
//static struct my_menu main_menu[] = {
//  {"",IDM_BEGIN},
//  {N"_Game",					IDM_SUBMENU},
//  {N"Local _Options",			IDM_GAME_LOCAL_OPTIONS},
//  {N"_Message Options",			IDM_GAME_MESSAGE_OPTIONS},
//  {N"Sa_ve Settings",			IDM_GAME_SAVE_SETTINGS},
//  {"", IDM_SEPARATOR},
//  {N"_Initial Server Options",		IDM_GAME_SERVER_OPTIONS1},
//  {N"Server O_ptions",			IDM_GAME_SERVER_OPTIONS2},
//  {"", IDM_SEPARATOR},
//  {N"_Save Game",				IDM_GAME_SAVE_QUICK},
//  {N"Save Game _As...",			IDM_GAME_SAVE_GAME},
//  {"", IDM_SEPARATOR},
//  {N"E_xport Log",				IDM_GAME_EXPORT_LOG},
//  {N"Clear _Log",				IDM_GAME_CLEAR_OUTPUT},
//  {"", IDM_SEPARATOR},
//  {N"_Disconnect",				IDM_GAME_DISCONNECT},
//  {N"_Quit"			"\tCtl+Q",	IDM_GAME_QUIT},
//  {null, 0},
//
//
//  {N"Gov_ernment",				IDM_SUBMENU},
//  {N"Tax Rates"		"\tShift+T",	IDM_GOVERNMENT_TAX_RATE},
//  { "", IDM_SEPARATOR},
//  {N"_Find City"		"\tCtl+F",	IDM_GOVERNMENT_FIND_CITY},
//  {N"Work_lists"		"\tCtl+W",	IDM_GOVERNMENT_WORKLISTS},
//  { "", IDM_SEPARATOR},
//  {N"_Change Government",			IDM_SUBMENU},
//  {N"_Revolution",				IDM_GOVERNMENT_REVOLUTION},
//  {"", IDM_SEPARATOR},
//  {null, 0},
//  {null, 0},
//
//
//  {N"_View",					IDM_SUBMENU},
//  {N"Map _Grid"		"\tCtl+G",	IDM_VIEW_MAP_GRID},
//  {N"National _Borders"	"\tCtl+B", 	IDM_VIEW_NATIONAL_BORDERS},
//  {N"City _Names"		"\tCtl+N",	IDM_VIEW_CITY_NAMES},
//  {N"City G_rowth"		"\tCtl+R", 	IDM_VIEW_CITY_GROWTH},
//  {N"City _Productions"	"\tCtl+P",	IDM_VIEW_CITY_PRODUCTIONS},
//  {N"Terrain",				IDM_VIEW_TERRAIN},
//  {N"Coastline",				IDM_VIEW_COASTLINE},
//  {N"Improvements",				IDM_SUBMENU},
//  {N"Roads & Rails",				IDM_VIEW_ROADS_RAILS},
//  {N"Irrigation",				IDM_VIEW_IRRIGATION},
//  {N"Mines",					IDM_VIEW_MINES},
//  {N"Fortress & Airbase",			IDM_VIEW_FORTRESTerrain_H.S_AIRBASE},
//  {null, 0},
//  {N"Specials",				IDM_VIEW_SPECIALS},
//  {N"Pollution & Fallout",			IDM_VIEW_POLLUTION},
//  {N"Cities",				IDM_VIEW_CITIES},
//  {N"Units",					IDM_VIEW_UNITS},
//  {N"Focus Unit",				IDM_VIEW_FOCUS_UNIT},
//  {N"Fog of War",				IDM_VIEW_FOG_OF_WAR},
//  {"", IDM_SEPARATOR},
//  {N"Center View"		"\tC",		IDM_VIEW_CENTER},
//  {null, 0},
//
//  {N"_Orders",				IDM_SUBMENU},
//  {N"_Build City"		"\tB",		IDM_ORDERS_BUILD_CITY},
//  {N"Build _Road"		"\tR",		IDM_ORDERTerrain_H.S_ROAD},
//  {N"Build _Irrigation"	"\tI",		IDM_ORDERS_IRRIGATE},
//  {N"Build _Mine"		"\tM",		IDM_ORDERS_MINE},
//  {N"Transf_orm to Hills"	"\tO",		IDM_ORDERS_TRANSFORM},
//  {N"Build _Fortress"	"\tF",		IDM_ORDERTerrain_H.S_FORTRESS},
//  {N"Build Airbas_e"		"\tE",		IDM_ORDERTerrain_H.S_AIRBASE},
//  {N"Clean _Pollution"	"\tP",		IDM_ORDERS_POLLUTION},
//  {N"Clean _Nuclear Fallout" "\tN",		IDM_ORDERS_FALLOUT},
//  {"", IDM_SEPARATOR},
//  {N"_Sentry"		"\tS",		IDM_ORDERS_SENTRY},
//  {N"Pillage"		"\tShift+P",	IDM_ORDERS_PILLAGE},
//  {"", IDM_SEPARATOR},
//  {N"Make _Homecity"		"\tH",		IDM_ORDERS_HOMECITY},
//  {N"_Load"			"\tL",		IDM_ORDERS_LOAD},
//  {N"_Unload"		"\tU",		IDM_ORDERS_UNLOAD},
//  {N"Wake up o_thers"	"\tShift+W",	IDM_ORDERS_WAKEUP_OTHERS},
//  {"", IDM_SEPARATOR},
//  {N"Auto Settler"		"\tA",		IDM_ORDERS_AUTO_SETTLER},
//  {N"Auto E_xplore"		"\tX",		IDM_ORDERS_AUTO_EXPLORE},
//  {N"Connect", 				IDM_SUBMENU},
//  {N"_Road"			"\tCtl+Shift+R",IDM_ORDERS_CONNECT_ROAD},
//  {N"Rai_l"			"\tCtl+Shift+L",IDM_ORDERS_CONNECT_RAIL},
//  {N"_Irrigate"		"\tCtl+Shift+I",IDM_ORDERS_CONNECT_IRRIGATE},
//  {null, 0},
//  {N"Patrol"			"\tQ",		IDM_ORDERS_PATROL},
//  {N"_Go to"			"\tG",		IDM_ORDERS_GOTO},
//  {N"Go/Airlift to City"	"\tShift+L",	IDM_ORDERS_GOTO_CITY},
//  {N"Return to nearest city" "\tShift+G",	IDM_ORDERS_RETURN},
//  {"", IDM_SEPARATOR},
//  {N"Disband Unit"		"\tShift+D",	IDM_ORDERS_DISBAND},
//  {N"Diplomat/Spy Actions"	"\tD", 		IDM_ORDERS_DIPLOMAT_DLG},
//  {N"Explode Nuclear"	"\tShift+N",	IDM_ORDERS_NUKE},
//  {"", IDM_SEPARATOR},
//  {N"_Wait"			"\tW",		IDM_ORDERS_WAIT},
//  {N"Done"			"\tSpace",	IDM_ORDERS_DONE},
//  {null, 0},
//
//
//  {N"_Reports",				IDM_SUBMENU},
//  {N"_Cities"		"\tF1",		IDM_REPORTS_CITIES},
//  {N"_Units"			"\tF2",		IDM_REPORTS_UNITS},
//  {N"_Players"		"\tF3",		IDM_REPORTS_PLAYERS},
//  {N"_Economy"		"\tF5",		IDM_REPORTS_ECONOMY},
//  {N"_Science"		"\tF6",		IDM_REPORTS_SCIENCE},
//  {"", IDM_SEPARATOR},
//  {N"_Wonders of the World"	"\tF7",		IDM_REPORTS_WONDERS},
//  {N"_Top Five Cities"	"\tF8",		IDM_REPORTS_TOP_CITIES},
//  {N"_Messages"		"\tF10",	IDM_REPORTS_MESSAGES},
//  {N"_Demographics"		"\tF11",	IDM_REPORTS_DEMOGRAPHICS},
//  {N"S_paceship"		"\tF12",	IDM_REPORTS_SPACESHIP},
//  {null, 0},
//
//
//  {N"_Help",					IDM_SUBMENU},
//  {N"Language_s",				IDM_HELP_LANGUAGES},
//  {N"Co_nnecting",				IDM_HELP_CONNECTING},
//  {N"C_ontrols",				IDM_HELP_CONTROLS},
//  {N"C_hatline",				IDM_HELP_CHATLINE},
//  {N"_Worklist Editor",			IDM_HELP_WORKLIST_EDITOR},
//  {N"Citizen _Management",			IDM_HELP_CMA},
//  {N"_Playing",				IDM_HELP_PLAYING},
//  {"", IDM_SEPARATOR},
//  {N"City _Improvements",			IDM_HELP_IMPROVEMENTS},
//  {N"_Units",				IDM_HELP_UNITS},
//  {N"Com_bat",				IDM_HELP_COMBAT},
//  {N"_ZOC",					IDM_HELP_ZOC},
//  {N"Techno_logy",				IDM_HELP_TECH},
//  {N"_Terrain",				IDM_HELP_TERRAIN},
//  {N"Won_ders",				IDM_HELP_WONDERS},
//  {N"_Government",				IDM_HELP_GOVERNMENT},
//  {N"Happin_ess",				IDM_HELP_HAPPINESS},
//  {N"Space _Race",				IDM_HELP_SPACE_RACE},
//  {"", IDM_SEPARATOR},
//  {N"_Copying",				IDM_HELP_COPYING},
//  {N"_About",				IDM_HELP_ABOUT},
//  {null, 0},
//  {null, 0}
//};
//
///**************************************************************************
//  Append an item to a menu or submenu.
//**************************************************************************/
//static int my_append_menu(HMENU menu, my_menu item, HMENU submenu)
//{
//  char menustr[256];
//  char *tr;
//  char translated[256];
//  char *accel;
//  char *menustr_p;
//  menustr = item.name;
//  if ((accel=strchr(menustr,'\t')))
//    {
//      my_add_menu_accelerator(item.name,item.id);
//      accel[0]=0;
//      accel++;
//    }
//  tr=_(menustr);
//  translated = tr;
//  if (accel)
//    {
//      sz_strlcat(translated,"\t");
//      sz_strlcat(translated,accel);
//    }
//  menustr_p=menustr;
//  tr=translated;
//  while(*tr) {
//    if (*tr=='_') {
//      *menustr_p='&';
//    } else {  
//      if (*tr=='&') {
//	*menustr_p='&';
//	menustr_p++;
//      }
//      *menustr_p=*tr;
//    }
//    tr++;
//    menustr_p++;
//  }
//  *menustr_p='\0';
//  if (submenu)
//    return AppendMenu(menu,MF_POPUP,(UINT)submenu,menustr);
//  return AppendMenu(menu,MF_STRING,item.id,menustr);
//}
//
///**************************************************************************
//  Create a window's menu from items.
//**************************************************************************/
//static HMENU my_create_menu(my_menu *items)
//{
//  HMENU menu;
//  if ((*items).id==IDM_BEGIN) {
//    menu=CreateMenu();
//    (*items)++;
//  }
//  else 
//    menu=CreatePopupMenu();
//  while(items[0].name)
//    {
//      if ((*items).id==IDM_SEPARATOR) {
//	AppendMenu(menu,MF_SEPARATOR,-1,null);
//      }
//      else if ((*items).id==IDM_SUBMENU) {
//	my_menu submenu_item;
//	HMENU submenu;
//	submenu_item=*items;
//	(*items)++;
//	submenu=my_create_menu(items);
//	my_append_menu(menu,submenu_item,submenu);
//      } else {
//	my_append_menu(menu,(*items),null);
//      }
//      (*items)++;
//    }
//  return menu;
//}
//
///**************************************************************************
//  Handle a keypress from the numpad.
//**************************************************************************/
//static void handle_numpad(int code)
//{
//  switch (code) { 
//  case IDM_NUMPAD_BASE + 1:
//    key_unit_move(DIR8_SOUTHWEST);
//    break;
//  case IDM_NUMPAD_BASE + 2:
//    key_unit_move(DIR8_SOUTH);
//    break;
//  case IDM_NUMPAD_BASE + 3:
//    key_unit_move(DIR8_SOUTHEAST);
//    break;
//  case IDM_NUMPAD_BASE + 4:
//    key_unit_move(DIR8_WEST);
//    break;
//  case IDM_NUMPAD_BASE + 5:
//    advance_unit_focus();
//    break;
//  case IDM_NUMPAD_BASE + 6:
//    key_unit_move(DIR8_EAST);
//    break;
//  case IDM_NUMPAD_BASE + 7:
//    key_unit_move(DIR8_NORTHWEST);
//    break;
//  case IDM_NUMPAD_BASE + 8:
//    key_unit_move(DIR8_NORTH);
//    break;
//  case IDM_NUMPAD_BASE + 9:
//    key_unit_move(DIR8_NORTHEAST);
//    break;
//  }
//}
//
///**************************************************************************
//  Handle all menu messages.  This includes the chatline.
//**************************************************************************/
//void handle_menu(int code)
//{
//  HMENU menu;
//  if ((code>IDM_NUMPAD_BASE)&&(code<IDM_NUMPAD_BASE+10)) {
//    handle_numpad(code);
//    return;
//  }
//  menu=GetMenu(root_window);
//  switch((enum MenuID)code)
//    {
//
//    case IDM_GAME_LOCAL_OPTIONS:
//      popup_option_dialog();
//      break;
//    case IDM_GAME_MESSAGE_OPTIONS:
//      popup_messageopt_dialog();
//      break;
//    case IDM_GAME_SAVE_SETTINGS:
//      save_options();
//      break;
//    case IDM_GAME_SERVER_OPTIONS1:
//      send_report_request(REPORT_SERVER_OPTIONS1);
//      break;   
//    case IDM_GAME_SERVER_OPTIONS2:
//      send_report_request(REPORT_SERVER_OPTIONS2);
//      break;     
//    case IDM_GAME_SAVE_GAME:
//      handle_save_load("Select Location to Save", true);
//      break;
//    case IDM_GAME_SAVE_QUICK:
//      send_save_game(null);
//      break;
//    case IDM_GAME_EXPORT_LOG:
//      log_output_window();
//      break;
//    case IDM_GAME_CLEAR_OUTPUT:
//      clear_output_window();
//      break;
//    case IDM_GAME_DISCONNECT:
//      disconnect_from_server();
//      break;
//    case IDM_GAME_QUIT:
//      exit(EXIT_SUCCESS);
//      break;
//
//
//    case IDM_GOVERNMENT_TAX_RATE:
//      popup_rates_dialog();
//      break;
//    case IDM_GOVERNMENT_FIND_CITY:
//      popup_find_dialog();
//      break;
//    case IDM_GOVERNMENT_WORKLISTS:
//      popup_worklists_report(Game.game.player_ptr);
//      break;
//    case IDM_GOVERNMENT_REVOLUTION:
//      popup_revolution_dialog(-1);
//      break;
//
//
//    case IDM_VIEW_MAP_GRID:
//      key_map_grid_toggle();
//      my_check_menu(menu, IDM_VIEW_MAP_GRID, draw_map_grid);
//      break;
//    case IDM_VIEW_NATIONAL_BORDERS:
//      key_map_borders_toggle();
//      my_check_menu(menu, IDM_VIEW_NATIONAL_BORDERS, draw_borders);
//      break;      
//    case IDM_VIEW_CITY_NAMES:
//      key_city_names_toggle();
//      my_check_menu(menu, IDM_VIEW_CITY_NAMES, draw_city_names);
//      break;
//    case IDM_VIEW_CITY_GROWTH:
//      key_city_growth_toggle();
//      my_check_menu(menu, IDM_VIEW_CITY_GROWTH, draw_city_growth);
//      break;
//    case IDM_VIEW_CITY_PRODUCTIONS:
//      key_city_productions_toggle();
//      my_check_menu(menu, IDM_VIEW_CITY_PRODUCTIONS, draw_city_productions);
//      break;
//    case IDM_VIEW_TERRAIN:
//      key_terrain_toggle();
//      my_check_menu(menu, IDM_VIEW_TERRAIN, draw_terrain);
//      my_enable_menu(menu, IDM_VIEW_COASTLINE, !draw_terrain);
//      break;
//    case IDM_VIEW_COASTLINE:
//      key_coastline_toggle();
//      my_check_menu(menu, IDM_VIEW_COASTLINE, draw_coastline);
//      break;
//    case IDM_VIEW_ROADS_RAILS:
//      key_roads_rails_toggle();
//      my_check_menu(menu, IDM_VIEW_ROADS_RAILS, draw_roads_rails);
//      break;
//    case IDM_VIEW_IRRIGATION:
//      key_irrigation_toggle();
//      my_check_menu(menu, IDM_VIEW_IRRIGATION, draw_irrigation);
//      break;
//    case IDM_VIEW_MINES:
//      key_mines_toggle();
//      my_check_menu(menu, IDM_VIEW_MINES, draw_mines);
//      break;
//    case IDM_VIEW_FORTRESTerrain_H.S_AIRBASE:
//      key_fortress_airbase_toggle();
//      my_check_menu(menu, IDM_VIEW_FORTRESTerrain_H.S_AIRBASE, draw_fortress_airbase);
//      break;
//    case IDM_VIEW_SPECIALS:
//      key_specials_toggle();
//      my_check_menu(menu, IDM_VIEW_SPECIALS, draw_specials);
//      break;
//    case IDM_VIEW_POLLUTION:
//      key_pollution_toggle();
//      my_check_menu(menu, IDM_VIEW_POLLUTION, draw_pollution);
//      break;
//    case IDM_VIEW_CITIES:
//      key_cities_toggle();
//      my_check_menu(menu, IDM_VIEW_CITIES, draw_cities);
//      break;
//    case IDM_VIEW_UNITS:
//      key_units_toggle();
//      my_check_menu(menu, IDM_VIEW_UNITS, draw_units);
//      my_enable_menu(menu, IDM_VIEW_FOCUS_UNIT, !draw_units); 
//      break;
//    case IDM_VIEW_FOCUS_UNIT:
//      key_focus_unit_toggle();
//      my_check_menu(menu, IDM_VIEW_FOCUS_UNIT, draw_focus_unit);
//      break;
//    case IDM_VIEW_FOG_OF_WAR:
//      key_fog_of_war_toggle();
//      my_check_menu(menu, IDM_VIEW_FOG_OF_WAR, draw_fog_of_war);
//      break;
//    case IDM_VIEW_CENTER:
//      center_on_unit();
//      break;
//
//
//
//    case IDM_ORDERS_BUILD_CITY:
//      if(get_unit_in_focus()) {
//	unit punit = get_unit_in_focus();
//	/* Enable the button for adding to a city in all cases, so we
//	   get an eventual error message from the server if we try. */
//	if (can_unit_add_or_build_city(punit)) {
//	  key_unit_build_city();
//	} else {
//	  key_unit_build_wonder();
//	}
//      }
//      break;
//    case IDM_ORDERTerrain_H.S_ROAD:
//      if (get_unit_in_focus()) {
//	if (unit_can_est_traderoute_here(get_unit_in_focus())) {
//	  key_unit_traderoute();
//	} else {
//	  key_unit_road();
//	}
//      }
//      break;
//    case IDM_ORDERS_IRRIGATE:
//      key_unit_irrigate();
//      break;
//    case IDM_ORDERS_MINE:
//      key_unit_mine();
//      break;
//    case IDM_ORDERS_TRANSFORM:
//      key_unit_transform();
//      break;
//    case IDM_ORDERTerrain_H.S_FORTRESS:
//      if (get_unit_in_focus()) {
//	if (can_unit_do_activity(get_unit_in_focus(), ACTIVITY_FORTRESS)) {
//	  key_unit_fortress();
//	} else {
//	  key_unit_fortify();
//	}
//      }
//      break;
//    case IDM_ORDERTerrain_H.S_AIRBASE:
//      key_unit_airbase();
//      break;
//    case IDM_ORDERS_POLLUTION:
//      if (can_unit_paradrop(get_unit_in_focus()))
//	key_unit_paradrop();
//      else
//	key_unit_pollution();
//      break;
//    case IDM_ORDERS_FALLOUT:
//      key_unit_fallout();
//      break;
//    case IDM_ORDERS_SENTRY:
//      key_unit_sentry();
//      break;
//    case IDM_ORDERS_PILLAGE:
//      key_unit_pillage();
//      break;
//    case IDM_ORDERS_HOMECITY:
//      key_unit_homecity();
//      break;
//    case IDM_ORDERS_LOAD:
//      request_unit_load(get_unit_in_focus(), null);
//      break;
//    case IDM_ORDERS_UNLOAD:
//      if (get_unit_in_focus()) {
//	unit punit = get_unit_in_focus();
//	if (can_unit_unload(punit, Game.find_unit_by_id(punit.transported_by))
//	 && can_unit_exist_at_tile(punit, punit.tile)) {
//	  request_unit_unload(punit);
//	} else if (get_transporter_occupancy(punit) > 0) {
//	  key_unit_unload_all();
//	}
//      }
//      break;
//    case IDM_ORDERS_WAKEUP_OTHERS:
//      key_unit_wakeup_others();
//      break;
//    case IDM_ORDERS_AUTO_SETTLER:
//      if (get_unit_in_focus()) {
//	request_unit_auto(get_unit_in_focus());
//      }
//      break;
//    case IDM_ORDERS_AUTO_EXPLORE:
//      key_unit_auto_explore();
//      break;
//    case IDM_ORDERS_CONNECT_ROAD:
//      key_unit_connect(ACTIVITY_ROAD);
//      break;
//    case IDM_ORDERS_CONNECT_RAIL:
//      key_unit_connect(ACTIVITY_RAILROAD);
//      break;
//    case IDM_ORDERS_CONNECT_IRRIGATE:
//      key_unit_connect(ACTIVITY_IRRIGATE);
//      break;
//    case IDM_ORDERS_PATROL:
//      key_unit_patrol();
//      break;
//    case IDM_ORDERS_GOTO:
//      key_unit_goto();
//      break;
//    case IDM_ORDERS_GOTO_CITY:
//      if(get_unit_in_focus()) {
//	popup_goto_dialog();
//      }
//      break;
//    case IDM_ORDERS_RETURN:
//      if (get_unit_in_focus()) {
//	request_unit_return(get_unit_in_focus());
//      }
//      break;
//    case IDM_ORDERS_DISBAND:
//      key_unit_disband();
//      break;
//    case IDM_ORDERS_DIPLOMAT_DLG:
//      key_unit_diplomat_actions();
//      break;
//    case IDM_ORDERS_NUKE:
//      key_unit_nuke();
//      break;
//    case IDM_ORDERS_WAIT:
//      key_unit_wait();
//      break;
//    case IDM_ORDERS_DONE:
//      key_unit_done();
//      break;
//
//
//
//    case IDM_REPORTS_CITIES:
//      popup_city_report_dialog(0);
//      break;
//    case IDM_REPORTS_UNITS:
//      popup_activeunits_report_dialog(0);
//      break;
//    case IDM_REPORTS_PLAYERS:
//      popup_players_dialog();
//      break;
//    case IDM_REPORTS_ECONOMY:
//      popup_economy_report_dialog(0);
//      break;  
//    case IDM_REPORTS_SCIENCE:
//      popup_science_dialog(0);
//      break;
//    case IDM_REPORTS_WONDERS:
//      send_report_request(REPORT_WONDERS_OF_THE_WORLD);
//      break;          
//    case IDM_REPORTS_TOP_CITIES:
//      send_report_request(REPORT_TOP_5_CITIES);
//      break;       
//    case IDM_REPORTS_MESSAGES:
//      popup_meswin_dialog();
//      break;
//    case IDM_REPORTS_DEMOGRAPHICS:
//      send_report_request(REPORT_DEMOGRAPHIC);
//      break;
//    case IDM_REPORTS_SPACESHIP:
//      popup_spaceship_dialog(Game.game.player_ptr);
//      break;
//
//
//    case ID_TURNDONE:
//      EnableWindow(turndone_button,false);
//      user_ended_turn();
//      break;
//    case ID_ESCAPE:
//      key_cancel_action();
//      break;
//      
//
//    case IDM_HELP_LANGUAGES:
//      popup_help_dialog_string(HELP_LANGUAGES_ITEM);
//      break;    
//    case IDM_HELP_CONNECTING:
//      popup_help_dialog_string(HELP_CONNECTING_ITEM);
//      break;
//    case IDM_HELP_CHATLINE:
//      popup_help_dialog_string(HELP_CHATLINE_ITEM);
//      break;
//    case IDM_HELP_CONTROLS:
//      popup_help_dialog_string(HELP_CONTROLS_ITEM);
//      break;
//    case IDM_HELP_WORKLIST_EDITOR:
//      popup_help_dialog_string(HELP_WORKLIST_EDITOR_ITEM);
//      break;
//    case IDM_HELP_CMA:
//      popup_help_dialog_string(HELP_CMA_ITEM);
//      break;
//    case IDM_HELP_PLAYING:
//      popup_help_dialog_string(HELP_PLAYING_ITEM);
//      break;
//    case IDM_HELP_IMPROVEMENTS:
//      popup_help_dialog_string(HELP_IMPROVEMENTS_ITEM);
//      break;
//    case IDM_HELP_UNITS:
//      popup_help_dialog_string(HELP_UNITS_ITEM);
//    break;
//    case IDM_HELP_COMBAT:
//      popup_help_dialog_string(HELP_COMBAT_ITEM);
//      break;
//    case IDM_HELP_ZOC:
//      popup_help_dialog_string(HELP_ZOC_ITEM);
//      break;
//    case IDM_HELP_TECH:
//      popup_help_dialog_string(HELP_TECHS_ITEM);
//      break;
//    case IDM_HELP_TERRAIN:
//      popup_help_dialog_string(HELP_TERRAIN_ITEM);
//      break;
//    case IDM_HELP_WONDERS:
//      popup_help_dialog_string(HELP_WONDERS_ITEM);
//      break;
//    case IDM_HELP_GOVERNMENT:
//      popup_help_dialog_string(HELP_GOVERNMENT_ITEM);
//      break;
//    case IDM_HELP_HAPPINESS:
//      popup_help_dialog_string(HELP_HAPPINESS_ITEM);
//      break;
//    case IDM_HELP_SPACE_RACE:
//      popup_help_dialog_string(HELP_SPACE_RACE_ITEM);
//      break;
//    case IDM_HELP_COPYING:
//      popup_help_dialog_string(HELP_COPYING_ITEM);
//      break;
//    case IDM_HELP_ABOUT:
//      popup_help_dialog_string(HELP_ABOUT_ITEM);
//      break;
//
//
//    case IDOK:
//      handle_chatline();
//      break;
//    default:
//      if ((enum MenuID)code >= IDM_GOVERNMENT_CHANGE_FIRST
//	   && (enum MenuID)code <= IDM_GOVERNMENT_CHANGE_LAST) {
//	popup_revolution_dialog(code - IDM_GOVERNMENT_CHANGE_FIRST);
//      }
//      break;
//    }
//}
//
//
///**************************************************************************
//  Rename an item in the menu by id.
//**************************************************************************/
//static void my_rename_menu(HMENU hmenu, int id, char *name)
//{
//  char menustr[256];
//  char *tr;
//  char translated[256];
//  char *accel;
//  char *menustr_p;
//  menustr = name;
//  if ((accel = strchr(menustr, '\t'))) {
//    accel[0]=0;
//    accel++;
//  }
//  tr=_(menustr);
//  translated = tr;
//  if (accel) {
//    sz_strlcat(translated, "\t");
//    sz_strlcat(translated, accel);
//  }
//  menustr_p = menustr;
//  tr = translated;
//  while(*tr) {
//    if (*tr == '_') {
//      *menustr_p = '&';
//    } else {  
//      if (*tr == '&') {
//	*menustr_p = '&';
//	menustr_p++;
//      }
//      *menustr_p = *tr;
//    }
//    tr++;
//    menustr_p++;
//  }
//  *menustr_p = '\0';
//
//  ModifyMenu(hmenu, id, MF_BYCOMMAND | MF_STRING, id, menustr);
//}
//
///**************************************************************************
//  Create the main menu for the root window.
//**************************************************************************/
//HMENU create_mainmenu()
//{
//  my_menu items=main_menu;
//  return my_create_menu(&items);
//}
//
///**************************************************************************
//  Update the status and names of all menu items.
//**************************************************************************/
//void
//update_menus()
//{
//  enum MenuID id;
//  HMENU menu;
//  menu=GetMenu(root_window);
//
//  my_enable_menu(menu, IDM_GAME_SAVE_GAME,
//		 can_client_access_hack()
//		 && get_client_state() >= CLIENT_GAME_RUNNING_STATE);
//  my_enable_menu(menu, IDM_GAME_SAVE_QUICK,
//		 can_client_access_hack()
//		 && get_client_state() >= CLIENT_GAME_RUNNING_STATE);
//  my_enable_menu(menu, IDM_GAME_SERVER_OPTIONS2,
//		 aconnection.established);
//  my_enable_menu(menu, IDM_GAME_SERVER_OPTIONS1,
//		 get_client_state() >= CLIENT_GAME_RUNNING_STATE);
//  my_enable_menu(menu, IDM_GAME_DISCONNECT,
//		 aconnection.established);
//
//  if (!can_client_change_view()) {
//
//    /* Disables IDM_GOVERNMENT_MENU, IDM_VIEW_MENU, IDM_ORDERS_MENU,
//     * and IDM_REPORTS_MENU */
//    for (id = IDM_GOVERNMENT_MENU + 1; id < IDM_HELP_MENU; id++) {
//      my_enable_menu(menu, id, false);
//    }
//
//  } else {
//    unit punit;
//    HMENU govts;
//    int i;
//
//    /* remove previous government entries. */
//    for (id = IDM_GOVERNMENT_CHANGE_FIRST; id <= IDM_GOVERNMENT_CHANGE_LAST;
//	 id++) {
//      DeleteMenu(menu, id, MF_BYCOMMAND);
//    }
//
//    /* WARNING: These numbers are very dependent on the menu layout */
//    govts = GetSubMenu(GetSubMenu(menu, 1), 5); 
//
//    /* add new government entries. */
//    for (i = 0; i < Game.game.government_count; i++) {
//      if (i != Game.game.government_when_anarchy) {
//	AppendMenu(govts, MF_STRING, IDM_GOVERNMENT_CHANGE_FIRST + i,
//		   governments[i].name);
//	my_enable_menu(menu, IDM_GOVERNMENT_CHANGE_FIRST + i, 
//		       can_change_to_government(Game.game.player_ptr, i)
//		       && can_client_issue_orders());
//      }
//    }
//    my_enable_menu(menu, IDM_GOVERNMENT_FIND_CITY, true);
//
//    /* Enables IDM_VIEW_MENU */
//    for (id = IDM_VIEW_MENU + 1; id < IDM_ORDERS_BUILD_CITY; id++) {
//      my_enable_menu(menu, id, true);
//    }
//
//    /* Enables IDM_REPORTS_MENU */
//    for (id = IDM_REPORTS_MENU + 1; id < IDM_HELP_MENU; id++) {
//      my_enable_menu(menu, id, true);
//    }
//
//    if (can_client_issue_orders()) {
//      my_enable_menu(menu, IDM_GOVERNMENT_TAX_RATE, Game.game.rgame.changable_tax);
//      my_enable_menu(menu, IDM_GOVERNMENT_WORKLISTS, true);
//      my_enable_menu(menu, IDM_GOVERNMENT_REVOLUTION, true);
//    }
//
//
//    my_enable_menu(menu, IDM_REPORTS_SPACESHIP, 
//		   (Game.game.player_ptr.spaceship.state!=spaceship_state.SSHIP_NONE));
//
//    my_check_menu(menu, IDM_VIEW_MAP_GRID, draw_map_grid);
//    my_enable_menu(menu, IDM_VIEW_NATIONAL_BORDERS, Game.game.borders > 0);
//    my_check_menu(menu, IDM_VIEW_NATIONAL_BORDERS, draw_borders);
//    my_check_menu(menu, IDM_VIEW_CITY_NAMES, draw_city_names);
//    my_enable_menu(menu, IDM_VIEW_CITY_GROWTH, draw_city_names);
//    my_check_menu(menu, IDM_VIEW_CITY_GROWTH, draw_city_growth);
//    my_check_menu(menu, IDM_VIEW_CITY_PRODUCTIONS, draw_city_productions);
//    my_check_menu(menu, IDM_VIEW_TERRAIN, draw_terrain);
//    my_check_menu(menu, IDM_VIEW_COASTLINE, draw_coastline);
//    my_enable_menu(menu, IDM_VIEW_COASTLINE, !draw_terrain);
//    my_check_menu(menu, IDM_VIEW_ROADS_RAILS, draw_roads_rails);
//    my_check_menu(menu, IDM_VIEW_IRRIGATION, draw_irrigation);
//    my_check_menu(menu, IDM_VIEW_MINES, draw_mines);
//    my_check_menu(menu, IDM_VIEW_FORTRESTerrain_H.S_AIRBASE, draw_fortress_airbase);
//    my_check_menu(menu, IDM_VIEW_SPECIALS, draw_specials);
//    my_check_menu(menu, IDM_VIEW_POLLUTION, draw_pollution);
//    my_check_menu(menu, IDM_VIEW_CITIES, draw_cities);
//    my_check_menu(menu, IDM_VIEW_UNITS, draw_units);
//    my_check_menu(menu, IDM_VIEW_FOCUS_UNIT, draw_focus_unit);
//    my_enable_menu(menu, IDM_VIEW_FOCUS_UNIT, !draw_units); 
//    my_check_menu(menu, IDM_VIEW_FOG_OF_WAR, draw_fog_of_war);
//
//    /* Remaining part of this function: Update Orders menu */
//
//    if (!can_client_issue_orders()) {
//      return;
//    }
//
//    if ((punit = get_unit_in_focus())) {
//      final String irrfmt = "Change to %s";
//      final String minfmt = "Change to %s";
//      final String transfmt = "Transform to %s";
//      char irrtext[128], mintext[128], transtext[128];
//      char *roadtext;
//      int  ttype;
//      tile_type tinfo;
//
//      irrtext = String.format( N"Build Irrigation" "\tI");
//      mintext = String.format( N"Build Mine" "\tM");
//      transtext = String.format( N"Transform Terrain" "\tO");
//      
//      /* Since the entire menu is disabled by default, enable the
//	 items with no checks. */
//      my_enable_menu(menu, IDM_ORDERS_PATROL, true);
//      my_enable_menu(menu, IDM_ORDERS_GOTO, true);
//      my_enable_menu(menu, IDM_ORDERS_GOTO_CITY, true);
//      my_enable_menu(menu, IDM_ORDERS_WAIT, true);
//      my_enable_menu(menu, IDM_ORDERS_DONE, true);
//
//      /* Enable the button for adding to a city in all cases, so we
//	 get an eventual error message from the server if we try. */
//      my_enable_menu(menu, IDM_ORDERS_BUILD_CITY,
//		     can_unit_add_or_build_city(punit)
//		     || unit_can_help_build_wonder_here(punit));
//      my_enable_menu(menu, IDM_ORDERTerrain_H.S_ROAD,
//                     can_unit_do_activity(punit, ACTIVITY_ROAD)
//		     || can_unit_do_activity(punit, ACTIVITY_RAILROAD)
//		     || unit_can_est_traderoute_here(punit));
//      my_enable_menu(menu, IDM_ORDERS_IRRIGATE,
//		     can_unit_do_activity(punit, ACTIVITY_IRRIGATE));
//      my_enable_menu(menu, IDM_ORDERS_MINE,
//		     can_unit_do_activity(punit, ACTIVITY_MINE));
//      my_enable_menu(menu, IDM_ORDERS_TRANSFORM,
//		     can_unit_do_activity(punit, ACTIVITY_TRANSFORM));
//      my_enable_menu(menu, IDM_ORDERTerrain_H.S_FORTRESS,
//		     can_unit_do_activity(punit, ACTIVITY_FORTRESS)
//		     || can_unit_do_activity(punit, ACTIVITY_FORTIFYING));
//      my_enable_menu(menu, IDM_ORDERTerrain_H.S_AIRBASE,
//		     can_unit_do_activity(punit, ACTIVITY_AIRBASE));
//      my_enable_menu(menu, IDM_ORDERS_POLLUTION,
//		     can_unit_do_activity(punit, ACTIVITY_POLLUTION)
//		     || can_unit_paradrop(punit));
//      my_enable_menu(menu, IDM_ORDERS_FALLOUT,
//		     can_unit_do_activity(punit, ACTIVITY_FALLOUT));
//      my_enable_menu(menu, IDM_ORDERS_SENTRY,
//		     can_unit_do_activity(punit, ACTIVITY_SENTRY));
//      my_enable_menu(menu, IDM_ORDERS_PILLAGE,
//		     can_unit_do_activity(punit, ACTIVITY_PILLAGE));
//      my_enable_menu(menu, IDM_ORDERS_DISBAND,
//		     !unit_flag(punit, F_UNDISBANDABLE));
//      my_enable_menu(menu, IDM_ORDERS_HOMECITY,
//		     can_unit_change_homecity(punit));
//      my_enable_menu(menu, IDM_ORDERS_LOAD,
//	can_unit_load(punit, find_transporter_for_unit(punit,
//						       punit.tile)));
//      my_enable_menu(menu, IDM_ORDERS_UNLOAD,
//	(can_unit_unload(punit, Game.find_unit_by_id(punit.transported_by))
//	 && can_unit_exist_at_tile(punit, punit.tile)) 
//	|| get_transporter_occupancy(punit) > 0);
//      my_enable_menu(menu, IDM_ORDERS_WAKEUP_OTHERS,
//		     is_unit_activity_on_tile(ACTIVITY_SENTRY,
//					      punit.tile));
//      my_enable_menu(menu, IDM_ORDERS_AUTO_SETTLER,
//		     can_unit_do_auto(punit));
//      my_enable_menu(menu, IDM_ORDERS_AUTO_EXPLORE,
//		     can_unit_do_activity(punit, ACTIVITY_EXPLORE));
//      my_enable_menu(menu, IDM_ORDERS_CONNECT_ROAD,
//		     can_unit_do_connect(punit, ACTIVITY_ROAD));
//      my_enable_menu(menu, IDM_ORDERS_CONNECT_RAIL,
//		     can_unit_do_connect(punit, ACTIVITY_RAILROAD));
//      my_enable_menu(menu, IDM_ORDERS_CONNECT_IRRIGATE,
//		     can_unit_do_connect(punit, ACTIVITY_IRRIGATE));
//      my_enable_menu(menu, IDM_ORDERS_RETURN,
//		     !(is_air_unit(punit) || is_heli_unit(punit)));
//      my_enable_menu(menu, IDM_ORDERS_DIPLOMAT_DLG,
//		     is_diplomat_unit(punit)
//		     && diplomat_can_do_action(punit, DIPLOMAT_ANY_ACTION,
//					       punit.tile));
//      my_enable_menu(menu, IDM_ORDERS_NUKE,
//		     unit_flag(punit, F_NUCLEAR));
//      if (unit_flag(punit, F_HELP_WONDER)) {
//	my_rename_menu(menu, IDM_ORDERS_BUILD_CITY, N"Help Build Wonder" +
//		       "\tB");
//      } else if (unit_flag(punit, Eunit_flag_id.F_CITIES)) {
//	if (Map.map_get_city(punit.tile)) {
//	  my_rename_menu(menu, IDM_ORDERS_BUILD_CITY, N"Add to City" +
//			 "\tB");
//	} else {
//	  my_rename_menu(menu, IDM_ORDERS_BUILD_CITY, N"Build City" +
//			 "\tB");
//	}
//      } else {
//	my_rename_menu(menu, IDM_ORDERS_BUILD_CITY, N"Build City" "\tB");
//      }
// 
//      if (unit_flag(punit, F_TRADE_ROUTE)) {
//	my_rename_menu(menu, IDM_ORDERTerrain_H.S_ROAD, N"Make Trade Route" "\tR");
//      } else if (unit_flag(punit, Eunit_flag_id.F_SETTLERS)) {
//	if (Map.map_has_special(punit.tile, Terrain_H.S_ROAD)) {
//	  roadtext = N"Build Railroad" "\tR";
//	  road_activity = ACTIVITY_RAILROAD;  
//	} else {
//	  roadtext = N"Build Road" "\tR";
//	  road_activity = ACTIVITY_ROAD;  
//	}
//	my_rename_menu(menu, IDM_ORDERTerrain_H.S_ROAD, roadtext);
//      } else {
//	my_rename_menu(menu, IDM_ORDERTerrain_H.S_ROAD, N"Build Road" "\tR");
//      }
//
//      ttype = punit.tile.terrain;
//      tinfo = get_tile_type(ttype);
//      if (tinfo.irrigation_result != Terrain_H.T_NONE
//	  && tinfo.irrigation_result != ttype) {
//	irrtext = util.my_snprintf( irrfmt,
//		    (get_tile_type(tinfo.irrigation_result)).terrain_name);
//	sz_strlcat(irrtext, "\tI");
//      } else if (Map.map_has_special(punit.tile, S_IRRIGATION)
//		 && Player_P.player_knows_techs_with_flag(Game.game.player_ptr,
//						 TF_FARMLAND)) {
//	irrtext = String.format( N"Build Farmland" "\tI");
//      }
//      if (tinfo.mining_result != Terrain_H.T_NONE
//	  && tinfo.mining_result != ttype) {
//	mintext = util.my_snprintf( minfmt,
//		    (get_tile_type(tinfo.mining_result)).terrain_name);
//	sz_strlcat(mintext, "\tM");
//      }
//      if (tinfo.transform_result != Terrain_H.T_NONE
//	  && tinfo.transform_result != ttype) {
//	transtext = util.my_snprintf( transfmt,
//		    (get_tile_type(tinfo.transform_result)).terrain_name);
//	sz_strlcat(transtext, "\tO");
//      }
//
//      my_rename_menu(menu, IDM_ORDERS_IRRIGATE, irrtext);
//      my_rename_menu(menu, IDM_ORDERS_MINE, mintext);
//      my_rename_menu(menu, IDM_ORDERS_TRANSFORM, transtext);
//
//      if (can_unit_do_activity(punit, ACTIVITY_FORTIFYING)) {
//	my_rename_menu(menu, IDM_ORDERTerrain_H.S_FORTRESS, N"Fortify" "\tF");
//      } else {
//	my_rename_menu(menu, IDM_ORDERTerrain_H.S_FORTRESS, N"Build Fortress" +
//		       "\tF");
//      }
//
//      if (unit_flag(punit, F_PARATROOPERS)) {
//	my_rename_menu(menu, IDM_ORDERS_POLLUTION, N"Paradrop" "\tP");
//      } else {
//	my_rename_menu(menu, IDM_ORDERS_POLLUTION, N"Clean Pollution" +
//		       "\tP");
//      }
//
//      if (!unit_flag(punit, Eunit_flag_id.F_SETTLERS)) {
//	my_rename_menu(menu, IDM_ORDERS_AUTO_SETTLER, N"Auto Attack" +
//		       "\tA");
//      } else {
//	my_rename_menu(menu, IDM_ORDERS_AUTO_SETTLER, N"Auto Settler" +
//		       "\tA");
//      }
//
//    } else {
//      /* Disables IDM_ORDERS_MENU */
//      for (id = IDM_ORDERS_MENU + 1; id < IDM_REPORTS_MENU; id++) {
//	my_enable_menu(menu, id, false);
//      }
//    }
//  }
//}
}