package client.gui_win32;

public class Cityrep{
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
//#include <stdio.h>
//#include <stdlib.h>
//#include <string.h>
//                         
//#include <windows.h>
//#include <windowsx.h>
//
//#include "city.h"
//#include "fcintl.h"
//#include "Game.game.h"
//#include "packets.h"
//#include "shared.h"
//#include "support.h"
//#include "unit.h"
//#include "mem.h"
// 
//#include "chatline.h"
//#include "citydlg.h"
//#include "cityrepdata.h"
//#include "gui_main.h"
//#include "gui_stuff.h"
//#include "mapview.h"
//#include "optiondlg.h"
//#include "options.h"
//#include "repodlgs.h"
//#include "climisc.h"
//#include "text.h"
//                           
//#include "cityrep.h"
//extern HINSTANCE freecivhinst;
//extern HFONT font_12courier;
//extern struct connection aconnection;   
//int max_changemenu_id;
//int max_supportmenu_id;
//int max_presentmenu_id;
//int max_availablemenu_id;
//int max_improvement_id;
//static int city_sort_id;
//static int city_sort_order;
//static HMENU menu_shown; 
//static HWND sort_buttons[NUM_CREPORT_COLS];
//HWND cityrep_list;
//typedef boolean TestCityFunc(city , int);     
//
//public static final int ID_CITYREP_SORTBASE = 6400;
//public static final int ID_CHANGE_POPUP_BASE = 7000;
//public static final int ID_SUPPORTED_POPUP_BASE = 8000;
//public static final int ID_PRESENT_POPUP_BASE = 9000;
//public static final int ID_AVAILABLE_POPUP_BASE = 10000;
//public static final int ID_IMPROVEMENTS_POPUP_BASE = 11000;
//public static final int ID_CITYREP_POPUP_ALL = 6900;
//public static final int ID_CITYREP_POPUP_NO = 6910;
//public static final int ID_CITYREP_POPUP_INVERT = 6920;
//public static final int ID_CITYREP_POPUP_COASTAL = 6930;
//public static final int ID_CITYREP_POPUP_ISLAND = 6940;
//public static final int ID_CITYREP_CONFIG_BASE = 6500;
//#define NEG_VAL(x)  ((x)<0 ? (x) : (-x))     
//
//static RECT list_singlechar;
//static HWND hCityRep;
//static HWND hChangeAll;
//static HWND hCityRepConfig;
//extern HWND root_window;
//LONG APIENTRY ConfigCityRepProc(HWND hWnd,
//				UINT message,
//				UINT wParam,
//				LONG lParam);
///**************************************************************************
//
//**************************************************************************/
//static LONG APIENTRY city_report_proc(HWND hWnd,
//				      UINT uMsg,
//				      UINT wParam,
//				      LONG lParam);
//
//static void popup_city_report_config_dialog()
//{
//  int i;
//  if (!hCityRepConfig)
//    {
//      hCityRepConfig=
//	fcwin_create_layouted_window(ConfigCityRepProc,
//				     "Configure City Report",
//				     WS_OVERLAPPED | WS_CAPTION | WS_SYSMENU |
//				     WS_MINIMIZEBOX,
//				     20,20,
//				     hCityRep,null,
//				     REAL_CHILD,
//				     null);
//				  
//      ShowWindow(hCityRepConfig,SW_SHOWNORMAL);
//    }
//  for(i=1; i<NUM_CREPORT_COLS; i++) {
//    
//    CheckDlgButton(hCityRepConfig,i+ID_CITYREP_CONFIG_BASE,
//		   city_report_specs[i].show?BST_CHECKED:BST_UNCHECKED);
//  }
//			       
//}
//
//
///**************************************************************************
//
//**************************************************************************/
//static void create_cityrep_config_dlg(HWND hWnd)
//{
//  int i;
//  fcwin_box box;
//  city_report_spec spec;   
//  box=fcwin_vbox_new(hWnd,false);
//  fcwin_box_add_static_default(box,"Set columns shown",-1,SS_CENTER);
//  for(i=1, spec=city_report_specs+i; i<NUM_CREPORT_COLS; i++, spec++) {     
//    fcwin_box_add_checkbox(box, spec.explanation,
//			   ID_CITYREP_CONFIG_BASE+i,0,false,false,5);
//  }
//  fcwin_box_add_button(box,"Close",IDOK,0,false,false,15);
//  fcwin_set_box(hWnd,box);
//}
//
///**************************************************************************
//
//**************************************************************************/
//LONG APIENTRY ConfigCityRepProc(HWND hWnd,
//				UINT message,
//				UINT wParam,
//				LONG lParam)
//{
//  switch(message)
//    {
//    case WM_CREATE:
//      create_cityrep_config_dlg(hWnd);
//      return 0;
//    case WM_DESTROY:
//      hCityRepConfig=null;
//      break;
//    case WM_CLOSE:
//      DestroyWindow(hWnd);
//      break;
//    case WM_COMMAND:
//      if (LOWORD(wParam)==IDOK)
//	{
//	  city_report_spec spec;   
//	  int i;
//	  for(i=1, spec=city_report_specs+i; i<NUM_CREPORT_COLS; i++, spec++) 
//	    {   
//	      spec.show=IsDlgButtonChecked(hWnd,ID_CITYREP_CONFIG_BASE+i);  
//	    }
//	  DestroyWindow(hWnd);
//	  DestroyWindow(hCityRep);
//	  popup_city_report_dialog(false);
//	}
//      break;
//    default:
//      return DefWindowProc(hWnd,message,wParam,lParam); 
//    }
//  return 0;
//}
//
///**************************************************************************
//
//**************************************************************************/
//static void
//append_impr_or_unit_to_menu_sub(HMENU menu,
//				char *nothing_appended_text,
//				int append_units,
//				int append_wonders,
//				int change_prod,
//				TestCityFunc test_func,
//				int *selitems,
//				int selcount, int *idcount)
//{
//  cid cids[unittype.U_LAST + Improvement.B_LAST];
//  struct item items[unittype.U_LAST + Improvement.B_LAST];
//  int item, cids_used, num_selected_cities = 0;
//  city *selected_cities = null;
//
//  if (change_prod) {
//    int j;
//    HWND hList = GetDlgItem(hCityRep, ID_CITYREP_LIST);
//
//    num_selected_cities = selcount;
//    selected_cities =
//	fc_malloc(sizeof(*selected_cities) * num_selected_cities);
//    
//    for (j = 0; j < num_selected_cities; j++) {
//      selected_cities[j] = (city ) ListBox_GetItemData(hList,
//							       selitems[j]);
//    }
//  }
//
//  cids_used = collect_cids1(cids, selected_cities,
//			    num_selected_cities, append_units,
//			    append_wonders, change_prod, test_func);
//  if (selected_cities) {
//    free(selected_cities);
//  }
//  name_and_sort_items(cids, cids_used, items, change_prod, null);
//
//  for (item = 0; item < cids_used; item++) {
//    MENUITEMINFO iteminfo;
//
//    AppendMenu(menu, MF_STRING, (*idcount), items[item].descr);
//    iteminfo.dwItemData = items[item].cid;
//    iteminfo.fMask = MIIM_DATA;
//    iteminfo.cbSize = sizeof(MENUITEMINFO);
//    SetMenuItemInfo(menu, (*idcount), false, &iteminfo);
//    (*idcount)++;
//  }
//
//  if (cids_used == 0) {
//    AppendMenu(menu, MF_STRING, -1, nothing_appended_text);
//  }
//}
//
///**************************************************************************
//
//**************************************************************************/
//static void append_impr_or_unit_to_menu(HMENU menu,
//					int change_prod,
//					int append_improvements,
//					int append_units,
//					TestCityFunc test_func,
//					int *selitems, 
//					int selcount, 
//					int *idcount)
//{
//  if (append_improvements) {
//    /* Add all buildings */
//    append_impr_or_unit_to_menu_sub(menu, "No Buildings Available",
//				    false, false, change_prod,
//				    (boolean (*)(city , int))
//				    test_func,
//				    selitems, selcount, idcount);
//    /* Add a separator */
//    AppendMenu(menu, MF_SEPARATOR, -1, null);
//  }
//
//  if (append_units) {
//    /* Add all units */
//    append_impr_or_unit_to_menu_sub(menu, "No Units Available",
//				    true, false, change_prod,
//				    test_func,
//				    selitems, selcount, idcount);
//  }
//
//  if (append_improvements) {
//    if (append_units) {
//      /* Add a separator */
//	AppendMenu(menu, MF_SEPARATOR, -1, null);
//    }
//
//    /* Add all wonders */
//    append_impr_or_unit_to_menu_sub(menu, "No Wonders Available",
//				    false, true, change_prod,
//				    (boolean (*)(city , int))
//				    test_func,
//				    selitems, selcount, idcount);
//  }
//}
//
///****************************************************************
// Return text line for the column headers for the city report
//*****************************************************************/
//static void get_city_table_header(char *text[], int n)
//{
//  city_report_spec spec;
//  int i;
//  for(i=0, spec=city_report_specs; i<NUM_CREPORT_COLS; i++, spec++) {
//    my_snprintf(text[i], n, "%*s\n%*s",
//            NEG_VAL(spec.width), spec.title1 ? spec.title1: "",
//            NEG_VAL(spec.width), spec.title2 ? spec.title2: "");
//  }
//}             
//                  
///**************************************************************************
//
//**************************************************************************/
//static void get_city_text(city pcity, char *buf[], int n)
//{
//  city_report_spec spec;
//  int i;
// 
//  for(i=0, spec=city_report_specs; i<NUM_CREPORT_COLS; i++, spec++) {
//    buf[i][0]='\0';
//    if(!spec.show) continue;
//    my_snprintf(buf[i], n, "%*s", NEG_VAL(spec.width)-2, (spec.func)(pcity)); 
//  }
//}
//
///**************************************************************************
//
//**************************************************************************/
//static void cityrep_center(HWND hWnd)
//{
//  int selcount;
//  int id;
//  city pcity;
//  selcount=ListBox_GetSelCount(GetDlgItem(hWnd,ID_CITYREP_LIST));
//  if (selcount!=1)
//    return;
//  if (ListBox_GetSelItems(GetDlgItem(hWnd,ID_CITYREP_LIST),1,&id)!=1)
//    return;
//  pcity=(city )ListBox_GetItemData(GetDlgItem(hWnd,ID_CITYREP_LIST),
//					   id);
//  center_tile_mapcanvas(pcity.tile);
//}
//
///**************************************************************************
//
//**************************************************************************/
//static void cityrep_popup(HWND hWnd)
//{
//  int cityids[256];
//  int selcount;
//  int i;
//  city pcity;
//  selcount=ListBox_GetSelCount(GetDlgItem(hWnd,ID_CITYREP_LIST));
//  if (selcount==LB_ERR) return;
//  selcount=Math.min(256,selcount);
//  selcount=ListBox_GetSelItems(GetDlgItem(hWnd,ID_CITYREP_LIST),
//			       selcount,&cityids[0]);
//  for(i=0;i<selcount;i++)
//    {
//      pcity=(city )ListBox_GetItemData(GetDlgItem(hWnd,ID_CITYREP_LIST),
//				cityids[i]);
//      popup_city_dialog(pcity,0);
//    }
//}
//
///**************************************************************************
//
//**************************************************************************/
//static void cityrep_do_buy(HWND hWnd)
//{
//  int cityids[256];
//  int i;
//  int selcount = ListBox_GetSelCount(GetDlgItem(hWnd, ID_CITYREP_LIST));
//
//  if (selcount == LB_ERR) {
//    return;
//  }
//  selcount = Math.min(256, selcount);
//  selcount = ListBox_GetSelItems(GetDlgItem(hWnd, ID_CITYREP_LIST),
//				 selcount, &cityids[0]);
//  for (i = 0; i < selcount; i++) {
//    cityrep_buy((city ) ListBox_GetItemData(GetDlgItem(hWnd,
//							       ID_CITYREP_LIST),
//						    cityids[i]));
//  }
//}
//  
//#if 0
///**************************************************************************
//
//**************************************************************************/
//static void cityrep_refresh(HWND hWnd)
//{
//    int cityids[256];
//  int selcount;
//  int i;
//  city pcity; 
//  struct packet_generic_integer packet;    
//  selcount=ListBox_GetSelCount(GetDlgItem(hWnd,ID_CITYREP_LIST));
//  if ((selcount==LB_ERR)||(selcount==0))
//    {
//      packet.value = 0;
//      send_packet_generic_integer(&aconnection, PACKET_CITY_REFRESH, &packet);
//      return;
//    }
//  selcount=Math.min(256,selcount);
//  selcount=ListBox_GetSelItems(GetDlgItem(hWnd,ID_CITYREP_LIST),
//			       selcount,&cityids[0]);
//  for (i=0;i<selcount;i++)
//    {
//      pcity=(city )ListBox_GetItemData(GetDlgItem(hWnd,
//							  ID_CITYREP_LIST),
//					       cityids[i]);
//      packet.value = pcity.id;
//      send_packet_generic_integer(&aconnection, PACKET_CITY_REFRESH,
//				  &packet);      
//    }
//}
//#endif
//
///**************************************************************************
//
//**************************************************************************/
//
//static void cityrep_change(HWND hWnd)
//{    
//  int cityids[256];
//  int selcount;
//  HMENU popup;
//  RECT rc;
//  selcount=ListBox_GetSelCount(GetDlgItem(hWnd,ID_CITYREP_LIST));
//
//  if ((selcount==LB_ERR)||(selcount==0))
//    return;
//  selcount=Math.min(256,selcount);
//  selcount=ListBox_GetSelItems(GetDlgItem(hWnd,ID_CITYREP_LIST),
//			       selcount,&cityids[0]);
//  popup=CreatePopupMenu();
//  max_changemenu_id=ID_CHANGE_POPUP_BASE;
//  append_impr_or_unit_to_menu(popup,true,true,true,
//			      city_can_build_impr_or_unit,
//			      &cityids[0],selcount,&max_changemenu_id);
//  GetWindowRect(GetDlgItem(hWnd,ID_CITYREP_CHANGE),&rc);
//  menu_shown=popup;
//  TrackPopupMenu(popup,0,rc.left,rc.top,0,hWnd,null);
//}
//
///**************************************************************************
//
//**************************************************************************/
//static void cityrep_select(HWND hWnd)
//{
//  int cityids; /* We do not need to initialize it because selcount is 0
//		  We only have it to satisfy  append_impr_or_unit_to_menu */
//  int selcount;
//  RECT rc;
//  HMENU popup,submenu;
//  selcount=0;
//  popup=CreatePopupMenu();
//  AppendMenu(popup,MF_STRING,ID_CITYREP_POPUP_ALL,"All Cities");
//  AppendMenu(popup,MF_STRING,ID_CITYREP_POPUP_NO,"No Cities");
//  AppendMenu(popup,MF_STRING,ID_CITYREP_POPUP_INVERT,"Invert Selection");
//  AppendMenu(popup,MF_SEPARATOR,0,null);
//  AppendMenu(popup,MF_STRING,ID_CITYREP_POPUP_COASTAL,"Coastal Cities");
//  AppendMenu(popup,MF_STRING,ID_CITYREP_POPUP_ISLAND,"Same Island");
//  AppendMenu(popup,MF_SEPARATOR,0,null);  
//  submenu=CreatePopupMenu();
//  AppendMenu(popup,MF_POPUP,(UINT)submenu,"Supported Units");
//  max_supportmenu_id=ID_SUPPORTED_POPUP_BASE;
//
//  append_impr_or_unit_to_menu(submenu, false, false, true, 
//			      city_unit_supported,&cityids,selcount,
//			      &max_supportmenu_id);
//  submenu=CreatePopupMenu();
//  AppendMenu(popup,MF_POPUP,(UINT)submenu,"Units Present");
//  
//  max_presentmenu_id=ID_PRESENT_POPUP_BASE;
//  append_impr_or_unit_to_menu(submenu, false, false, true, 
//			      city_unit_present,&cityids,selcount,
//			      &max_presentmenu_id); 
//  
//  submenu=CreatePopupMenu();
//  AppendMenu(popup,MF_POPUP,(UINT)submenu,"Available To Build");
//  max_availablemenu_id=ID_AVAILABLE_POPUP_BASE;
//  append_impr_or_unit_to_menu(submenu, false, true, true,
//			      city_can_build_impr_or_unit,&cityids,selcount,
//			      &max_availablemenu_id);
//  
//  submenu=CreatePopupMenu(); 
//  AppendMenu(popup,MF_POPUP,(UINT)submenu,"Improvements in City");
//  max_improvement_id=ID_IMPROVEMENTS_POPUP_BASE;
//  append_impr_or_unit_to_menu(submenu, false, true, false,
//			      city_building_present,
//			      &cityids,selcount,
//			      &max_improvement_id);
//  
//  GetWindowRect(GetDlgItem(hWnd,ID_CITYREP_SELECT),&rc);
//  menu_shown=popup;
//  TrackPopupMenu(popup,0,rc.left,rc.top,0,hWnd,null);
//}
//
///**************************************************************************
//
//**************************************************************************/
//static void cityrep_change_menu(HWND hWnd, cid cid)
//{  
//  int cityids[256];
//  int selcount, i, last_request_id = 0;
//  city pcity; 
//  boolean is_unit = cid_is_unit(cid);
//  int number = cid_id(cid);
//  
//  selcount=ListBox_GetSelCount(GetDlgItem(hWnd,ID_CITYREP_LIST));
//  if (selcount==LB_ERR) return;
//  selcount=Math.min(256,selcount);
//  selcount=ListBox_GetSelItems(GetDlgItem(hWnd,ID_CITYREP_LIST),
//			       selcount,&cityids[0]);
//
//  connection_do_buffer(&aconnection);
//  for (i = 0; i < selcount; i++) {
//    pcity = (city ) ListBox_GetItemData(GetDlgItem(hWnd,
//							   ID_CITYREP_LIST),
//						cityids[i]);
//    last_request_id =
//      city_change_production(pcity, is_unit, number);
//    ListBox_SetSel(GetDlgItem(hWnd, ID_CITYREP_LIST), false, cityids[i]);
//  }
//
//  connection_do_unbuffer(&aconnection);
//  reports_freeze_till(last_request_id);
//}
//
///**************************************************************************
//
//**************************************************************************/
//static void list_all_select(HWND hLst,int state)
//{
//  int i;
//  int num;
//  num=ListBox_GetCount(hLst);
//  for (i=0;i<num;i++)
//    {
//      ListBox_SetSel(hLst,state,i);
//    }
//}
//
///**************************************************************************
//
//**************************************************************************/
//static void list_invert_select(HWND hLst)
//{
//  int i;
//  int num;
//  num=ListBox_GetCount(hLst);
//  for (i=0;i<num;i++)
//    {
//      ListBox_SetSel(hLst,!ListBox_GetSel(hLst,i),i);
//    }
//}
//
///**************************************************************************
//
//**************************************************************************/
//static void list_coastal_select(HWND hLst)
//{
//  int num,i;
//  city pcity;
//  num=ListBox_GetCount(hLst);
//  list_all_select(hLst,false);
//  for (i=0;i<num;i++)
//    {
//      pcity=(city )ListBox_GetItemData(hLst,i);
//      if (Terrain.is_terrain_flag_near_tile(pcity.tile)) {
//	ListBox_SetSel(hLst,true,i);
//      }
//    }
//}
//
///**************************************************************************
//
//**************************************************************************/
//static void list_sameisland_select(HWND hLst)
//{
//  city pcity;
//  int cityids[256];
//  int selcount;
//  int i,j;
//  int num;
//  selcount=ListBox_GetSelCount(hLst);
//  if (selcount==LB_ERR) return;
//    selcount=Math.min(256,selcount);
//  selcount=ListBox_GetSelItems(hLst,
//			       selcount,&cityids[0]);
//  num=ListBox_GetCount(hLst);
//  for (i=0;i<num;i++)
//    {
//      pcity=(city )ListBox_GetItemData(hLst,i);
//      for (j=0;j<selcount;j++)
//	{
//	  city selectedcity;
//	  selectedcity=(city )ListBox_GetItemData(hLst,cityids[j]);
//          if (map_get_continent(pcity.tile)
//              == map_get_continent(selectedcity.tile))
//	    {    
//	      ListBox_SetSel(hLst,true,i);
//	      break;
//	    }
//	}
//    }
//} 
//
///**************************************************************************
//
//**************************************************************************/
//static void list_impr_or_unit_select(HWND hLst, int num,
//				     TestCityFunc *test_func)
//{
//  int i,rows;
//  list_all_select(hLst,false);
//  rows=ListBox_GetCount(hLst);
//  for (i=0;i<rows;i++)
//    {
//      city pcity=(city )ListBox_GetItemData(hLst,i);
//      if (test_func(pcity,num))
//	ListBox_SetSel(hLst,true,i);
//    }
//}
//
///**************************************************************************
//
//**************************************************************************/
//static void menu_proc(HWND hWnd,int cmd, DWORD num)
//{
//  HWND hLst;
//  hLst=GetDlgItem(hWnd,ID_CITYREP_LIST);
//  if ((cmd>=ID_CHANGE_POPUP_BASE)&&
//      (cmd<max_changemenu_id))
//    {
//      cityrep_change_menu(hWnd,num);
//      max_changemenu_id=0;
//    }
//  if ((cmd>=ID_SUPPORTED_POPUP_BASE)&&
//      (cmd<max_supportmenu_id))
//    {
//      list_impr_or_unit_select(hLst,num,city_unit_supported);
//      max_supportmenu_id=0;
//    }
//  if ((cmd>=ID_PRESENT_POPUP_BASE)&&
//      (cmd<max_presentmenu_id))
//    {
//      list_impr_or_unit_select(hLst,num,city_unit_present);
//      max_presentmenu_id=0;
//    }
//  if ((cmd>=ID_AVAILABLE_POPUP_BASE)&&
//      (cmd<max_availablemenu_id))
//    {
//      list_impr_or_unit_select(hLst,num,city_can_build_impr_or_unit);
//      max_availablemenu_id=0;
//    }
//  if ((cmd>=ID_IMPROVEMENTS_POPUP_BASE)&&
//      (cmd<max_improvement_id))
//    {
//      list_impr_or_unit_select(hLst,num,city_building_present);
//      max_improvement_id=0;
//    }
//}
//
///**************************************************************************
//
//**************************************************************************/
//static LONG CALLBACK cityrep_changeall_proc(HWND hWnd,
//					    UINT message,
//					    WPARAM wParam,
//					    LPARAM lParam)  
//{
//  switch (message)
//    {
//    case WM_CREATE:
//      break;
//    case WM_CLOSE:
//      DestroyWindow(hWnd);
//      break;
//    case WM_SIZE:
//    case WM_GETMINMAXINFO:
//    case WM_DESTROY:    
//      hChangeAll=null;
//      break;
//    case WM_COMMAND:
//      switch (LOWORD(wParam))
//	{
//	case ID_PRODCHANGE_CANCEL:
//	  DestroyWindow(hWnd);
//	  hChangeAll=null;
//	  break;
//	case ID_PRODCHANGE_CHANGE:
//	  {
//	    int id;
//	    cid from, to;
//
//	    id=ListBox_GetCurSel(GetDlgItem(hWnd,ID_PRODCHANGE_FROM));
//	    if (id==LB_ERR)
//	      {
//		append_output_window(("Game: Select a unit or improvement" +
//				       " to change production from."));
//		break;        
//	      }
//	    from=ListBox_GetItemData(GetDlgItem(hWnd,ID_PRODCHANGE_FROM),
//				     id);
//	    id=ListBox_GetCurSel(GetDlgItem(hWnd,ID_PRODCHANGE_TO));
//	    if (id==LB_ERR)
//	      {
//		append_output_window(("Game: Select a unit or improvement" +
//				       " to change production to."));
//		break;          
//	      }
//	    to=ListBox_GetItemData(GetDlgItem(hWnd,ID_PRODCHANGE_TO),id);
//	    if (from==to) {
//	      append_output_window("Game: That's the same thing!");
//	      break;
//	    }
//	    client_change_all(from,to);
//	    DestroyWindow(hWnd);
//	    hChangeAll=null;
//	  }
//	  break;
//	  
//	}
//      break;
//    default:
//      return DefWindowProc(hWnd,message,wParam,lParam);   
//    }
//  return 0;
//}
//
///**************************************************************************
//
//**************************************************************************/
//static void cityrep_changeall(HWND hWnd)
//{ 
//  fcwin_box vbox;
//  fcwin_box hbox;
//  int selid;
//  cid cids[Improvement.B_LAST + unittype.U_LAST];
//  int cids_used;
//  cid selected_cid;
//  struct item items[unittype.U_LAST + Improvement.B_LAST];
//  int id,i;
//  HWND hDlg;
//  HWND hLst;
//  if (hChangeAll)
//    return;
//  hDlg=fcwin_create_layouted_window(cityrep_changeall_proc,
//				    "Change Production Everywhere",
//				    WS_OVERLAPPEDWINDOW,
//				    CW_USEDEFAULT,CW_USEDEFAULT,
//				    hCityRep,null,
//				    REAL_CHILD,
//				    null);
//  hbox=fcwin_hbox_new(hDlg,true);
//  vbox=fcwin_vbox_new(hDlg,false);
//  fcwin_box_add_static(vbox,"From:",0,SS_LEFT,false,false,5);
//  fcwin_box_add_list(vbox,10,ID_PRODCHANGE_FROM,
//		     WS_VSCROLL | WS_VISIBLE | LBS_HASSTRINGS,
//		     true,true,10);
//  fcwin_box_add_button(vbox,"Change",ID_PRODCHANGE_CHANGE,0,
//		       false,false,15);
//  fcwin_box_add_box(hbox,vbox,true,true,5);
//  vbox=fcwin_vbox_new(hDlg,false);
//  fcwin_box_add_static(vbox,"To:",0,SS_LEFT,false,false,5);
//  fcwin_box_add_list(vbox,10,ID_PRODCHANGE_TO,WS_VSCROLL | 
//		     WS_VISIBLE | LBS_HASSTRINGS,
//		     true,true,10);
//  fcwin_box_add_button(vbox,"Cancel",ID_PRODCHANGE_CANCEL,0,
//		       false,false,15);
//  fcwin_box_add_box(hbox,vbox,true,true,5);
//
//  selected_cid = -1;
//  selid = ListBox_GetCurSel(GetDlgItem(hWnd, ID_CITYREP_LIST));
//  if (selid != LB_ERR) {
//    selected_cid =
//	cid_encode_from_city((city )
//			     ListBox_GetItemData(GetDlgItem
//						 (hWnd, ID_CITYREP_LIST),
//						 selid));
//  }
//
//  cids_used = collect_cids2(cids);
//  name_and_sort_items(cids, cids_used, items, false, null);
//
//  hLst = GetDlgItem(hDlg, ID_PRODCHANGE_FROM);
//  for (i = 0; i < cids_used; i++) {
//    id = ListBox_AddString(hLst, items[i].descr);
//    ListBox_SetItemData(hLst, id, items[i].cid);
//    if (items[i].cid == selected_cid)
//      ListBox_SetCurSel(hLst, id);
//  }
//
//  cids_used = collect_cids3(cids);
//  name_and_sort_items(cids, cids_used, items, true, null);
//
//  hLst = GetDlgItem(hDlg, ID_PRODCHANGE_TO);
//  for (i = 0; i < cids_used; i++) {
//    id = ListBox_AddString(hLst, items[i].descr);
//    ListBox_SetItemData(hLst, id, items[i].cid);
//  }
//
//  fcwin_set_box(hDlg,hbox);
//  hChangeAll=hDlg;
//  ShowWindow(hDlg,SW_SHOWNORMAL);
//}
//
///**************************************************************************
//
//**************************************************************************/
//static void list_minsize(LPPOINT minsize,void *data)
//{
//  int i,width_total,width;
//  city_report_spec spec;
//  HDC hdc;
//  HFONT old;
//  HWND hWnd;
//  hWnd=(HWND)data;
//  hdc=GetDC(hWnd);
//  old=SelectObject(hdc,font_12courier);
//  DrawText(hdc,"X",1,&list_singlechar,DT_CALCRECT);
//  SelectObject(hdc,old);
//  ReleaseDC(hWnd,hdc);
//  width_total=0;
//  for(i=0, spec=city_report_specs; i<NUM_CREPORT_COLS; i++, spec++) {    
//    if (!spec.show) continue;
//    width=spec.width>0?spec.width:-spec.width;
//    width = MAX(strlen(spec.title1 ? spec.title1: ""), width);
//    width = MAX(strlen(spec.title2 ? spec.title2: ""), width);
//    width_total+=width+2;
//  }
//  minsize.x=(list_singlechar.right-list_singlechar.left)*width_total;
//  minsize.y=(list_singlechar.bottom-list_singlechar.top)*8;
//  
//}
//
///**************************************************************************
//
//**************************************************************************/
//static void list_setsize(LPRECT setsize,void *data)
//{
//  int i,x,y,button_h,w;  
//  city_report_spec spec;   
//  x=setsize.left;
//  y=setsize.top;
//  button_h=(list_singlechar.bottom-list_singlechar.top)*2+5;
//  for(i=0, spec=city_report_specs; i<NUM_CREPORT_COLS; i++, spec++) {
//    if (!sort_buttons[i]) continue;
//    w=spec.width>0?spec.width:-spec.width;
//    w = MAX(strlen(spec.title1 ? spec.title1: ""), w);
//    w = MAX(strlen(spec.title2 ? spec.title2: ""), w);
//    w+=2;
//    w*=(list_singlechar.right-list_singlechar.left);
//    MoveWindow(sort_buttons[i],x,y,w-1,button_h,true);
//    x+=w;
//  }   
//  MoveWindow(cityrep_list,setsize.left,y+button_h,
//	     setsize.right-setsize.left,
//	     setsize.bottom-setsize.top-button_h,true);
//}
//
///**************************************************************************
//
//**************************************************************************/
//static void list_del(void *data)
//{
//  int i;
//  for (i=0;i<NUM_CREPORT_COLS;i++)
//    {
//      if (sort_buttons[i])
//	{
//	  DestroyWindow(sort_buttons[i]);
//	  sort_buttons[i]=null;
//	}
//    }
//  DestroyWindow(cityrep_list);
//}
//
///**************************************************************************
//
//**************************************************************************/
//static void city_report_create(HWND hWnd)
//{
//  static char *titles   [NUM_CREPORT_COLS];
//  static char  buf      [NUM_CREPORT_COLS][64];   
//  city_report_spec spec;        
//  fcwin_box vbox;
//  fcwin_box hbox;
//  int i;
//  hbox=fcwin_hbox_new(hWnd,false);
//  fcwin_box_add_button_default(hbox,"Close",ID_CITYREP_CLOSE,0);
//  fcwin_box_add_button_default(hbox,"Center",ID_CITYREP_CENTER,0);
//  fcwin_box_add_button_default(hbox,"Popup",ID_CITYREP_POPUP,0);
//  fcwin_box_add_button_default(hbox,"Buy",ID_CITYREP_BUY,0);
//  fcwin_box_add_button_default(hbox,"Change",ID_CITYREP_CHANGE,0);
//  fcwin_box_add_button_default(hbox,"Change All",ID_CITYREP_CHANGEALL,0);
//  fcwin_box_add_button_default(hbox,"Refresh",ID_CITYREP_REFRESH,0);
//  fcwin_box_add_button_default(hbox,"Select",ID_CITYREP_SELECT,0);
//  fcwin_box_add_button_default(hbox,"Configure",ID_CITYREP_CONFIG,0);
//  vbox=fcwin_vbox_new(hWnd,false);
//  fcwin_box_add_static(vbox,get_report_title("City Advisor"),
//		       ID_CITYREP_TOP,SS_CENTER,false,false,0);
//  fcwin_box_add_generic(vbox,list_minsize,list_setsize,list_del,(void *)hWnd,
//			true,true,5);
//  fcwin_box_add_box(vbox,hbox,false,false,5);
//  
// 
//  for (i=0;i<NUM_CREPORT_COLS;i++)
//    titles[i]=buf[i];
//  
//  get_city_table_header(titles, sizeof(buf[0]));  
//  for(i=0, spec=city_report_specs; i<NUM_CREPORT_COLS; i++, spec++) {         
//    if (spec.show)
//      {
//	sort_buttons[i]=CreateWindow("BUTTON",titles[i],
//				     WS_CHILD | WS_VISIBLE | BS_MULTILINE,
//				     0,0,0,0,
//				     hWnd,
//				     (HMENU)(ID_CITYREP_SORTBASE+i),
//				     freecivhinst,
//				     null);
//	SendMessage(sort_buttons[i],
//		    WM_SETFONT,(WPARAM) font_12courier,MAKELPARAM(true,0));
// 				           }
//  }
//
//  cityrep_list=CreateWindow("LISTBOX",null,WS_CHILD | WS_VISIBLE | 
//			    LBS_HASSTRINGS | LBS_NOTIFY | LBS_EXTENDEDSEL | 
//			    WS_VSCROLL,
//			    0,0,0,0,
//			    hWnd,
//			    (HMENU)ID_CITYREP_LIST,
//			    freecivhinst,
//			    null);
//  SendMessage(cityrep_list,
//	      WM_SETFONT,(WPARAM) font_12courier,MAKELPARAM(true,0));
//  fcwin_set_box(hWnd,vbox);
//}
//
///**************************************************************************
//
//**************************************************************************/
//static LONG APIENTRY city_report_proc(HWND hWnd,
//				      UINT uMsg,
//				      UINT wParam,
//				      LONG lParam)
//
//{
//  int selcount;
//  int is_menu;
//  int cmd_id;
//  MENUITEMINFO iteminfo;
//  iteminfo.cbSize=sizeof(MENUITEMINFO);
//  iteminfo.fMask=MIIM_DATA;
//  switch(uMsg)
//    {
//    case WM_CREATE:
//      {
//	city_report_create(hWnd);
//      }
//      break;
//    case WM_DESTROY:
//      if (menu_shown) {
//	DestroyMenu(menu_shown);
//	menu_shown=null;
//      }
//      hCityRep=null;
//      break; 
//    case WM_GETMINMAXINFO:
//      break;
//    case WM_SIZE:
//    
//      break;
//    case WM_COMMAND:
//      is_menu=0;
//      cmd_id=LOWORD(wParam);
//      if (menu_shown)
//	{
//	  is_menu=GetMenuItemInfo(menu_shown,cmd_id,false,&iteminfo);
//	  DestroyMenu(menu_shown);
//	  menu_shown=null;
//	}
//      switch (cmd_id)
//	{
//	  
//	case ID_CITYREP_CLOSE:
//	  DestroyWindow(hCityRep);
//	  break;
//	case ID_CITYREP_LIST:
//	  selcount=ListBox_GetSelCount(GetDlgItem(hWnd,ID_CITYREP_LIST));
//	  if ((selcount==LB_ERR)||(selcount==0))
//	    {
//	      EnableWindow(GetDlgItem(hWnd,ID_CITYREP_CENTER),false);
//	      EnableWindow(GetDlgItem(hWnd,ID_CITYREP_POPUP),false);
//	      EnableWindow(GetDlgItem(hWnd,ID_CITYREP_BUY),false);
//	      EnableWindow(GetDlgItem(hWnd,ID_CITYREP_CHANGE),false);
//	    }
//	  else 
//	    {
//	      EnableWindow(GetDlgItem(hWnd,ID_CITYREP_CENTER),(selcount==1));
//	      EnableWindow(GetDlgItem(hWnd,ID_CITYREP_POPUP),true);
//	      EnableWindow(GetDlgItem(hWnd,ID_CITYREP_BUY),true);
//	      EnableWindow(GetDlgItem(hWnd,ID_CITYREP_CHANGE),true);
//	    }
//	  break; 
//	case ID_CITYREP_CENTER:
//	  cityrep_center(hWnd);
//	  break;
//	case ID_CITYREP_POPUP:
//	  cityrep_popup(hWnd);
//	  break;
//	case ID_CITYREP_BUY:
//	  cityrep_do_buy(hWnd);
//	  break;
//	case ID_CITYREP_CHANGE:
//	  cityrep_change(hWnd);
//	  break;
//	case ID_CITYREP_CHANGEALL:
//	  cityrep_changeall(hWnd);
//	  break;
//	case ID_CITYREP_SELECT:
//	  cityrep_select(hWnd);
//	  break;
//	case ID_CITYREP_CONFIG:
//	  popup_city_report_config_dialog();        
//	  break;
//	case ID_CITYREP_POPUP_ALL:
//	  list_all_select(GetDlgItem(hWnd,ID_CITYREP_LIST),true);
//	  break;
//	case ID_CITYREP_POPUP_NO:
//	  list_all_select(GetDlgItem(hWnd,ID_CITYREP_LIST),false);
//	  break;
//	case ID_CITYREP_POPUP_INVERT:
//	  list_invert_select(GetDlgItem(hWnd,ID_CITYREP_LIST));
//	  break;
//	case ID_CITYREP_POPUP_COASTAL:
//	  list_coastal_select(GetDlgItem(hWnd,ID_CITYREP_LIST));
//	  break;
//	case ID_CITYREP_POPUP_ISLAND:
//	  list_sameisland_select(GetDlgItem(hWnd,ID_CITYREP_LIST));
//	  break;
//	}
//      if ((cmd_id>=ID_CITYREP_SORTBASE)&&
//	  (cmd_id<(ID_CITYREP_SORTBASE+NUM_CREPORT_COLS)))
//      {
//	int new_sort_id;
//	new_sort_id=cmd_id-ID_CITYREP_SORTBASE;
//	if (new_sort_id==city_sort_id)
//	  {
//	    city_sort_order=!city_sort_order;
//	  }
//	else
//	  {
//	    city_sort_order=0;
//	    city_sort_id=new_sort_id;
//	  }
//	city_report_dialog_update();
//      }
//      if (is_menu)
//	{
//	  menu_proc(hWnd,cmd_id,iteminfo.dwItemData);
//	}
//      break;
//    case WM_CLOSE:
//      DestroyWindow(hCityRep);      
//      break;
//    default:
//      return DefWindowProc(hWnd,uMsg,wParam,lParam);
//    }
//  return 0;
//}
//
//
///**************************************************************************
//
//**************************************************************************/
//void
//popup_city_report_dialog(boolean make_modal)
//{
//  if (hCityRep) return;
//  hCityRep=
//    fcwin_create_layouted_window(city_report_proc,"City Report",
//				 WS_OVERLAPPED | WS_SYSMENU | WS_MINIMIZEBOX | 
//				 WS_MAXIMIZEBOX | WS_THICKFRAME,
//				 CW_USEDEFAULT,CW_USEDEFAULT,
//				 root_window,null,
//				 JUST_CLEANUP,
//				 null);
//  
//  hChangeAll=null;
//  city_report_dialog_update();
//  ShowWindow(hCityRep,SW_SHOWNORMAL);
//}
//
///**************************************************************************
//
//**************************************************************************/
//static int compare_cities(char *rowold[],char *row[])
//{
//  int tmp;
//  tmp=mystrcasecmp(rowold[city_sort_id],row[city_sort_id]);
//  return city_sort_order?tmp:-tmp;
//}
//
///**************************************************************************
//
//**************************************************************************/
//static void my_cityrep_add_city(HWND hLst,city pcity_new,char *full_row,
//				char *row[])
//{
//  int id;
//  int i,n,listcount;  
//  city_report_spec spec; 
//  char *rowold   [NUM_CREPORT_COLS];
//  char  buf   [NUM_CREPORT_COLS][64];
//  for (i=0, spec=city_report_specs;i<NUM_CREPORT_COLS;i++, spec++)
//    {
//      rowold[i] = buf[i];    
//    }
//  listcount=ListBox_GetCount(hLst);
//  for (n=0;n<listcount;n++)
//    {
//      city pcity;
//      pcity=(city )ListBox_GetItemData(hLst,n);
//      get_city_text(pcity,rowold,sizeof(buf[0]));
//      if (compare_cities(rowold,row)>0)
//	{
//	  id=ListBox_InsertString(hLst,n,full_row);
//	  ListBox_SetItemData(hLst,id,pcity_new);
//	  return;
//	}
//    }
//  ListBox_InsertString(hLst,listcount,full_row);
//  ListBox_SetItemData(hLst,listcount,pcity_new);
//
//}
//
///**************************************************************************
//
//**************************************************************************/
//void
//city_report_dialog_update()
//{ 
//  char *row   [NUM_CREPORT_COLS];
//  char  buf   [NUM_CREPORT_COLS][64];
//  char full_row[1024];
//  int   i;
//  city_report_spec spec; 
//  if(is_report_dialogs_frozen()) return;    
//  if (!hCityRep)
//    return;
//  SetWindowText(GetDlgItem(hCityRep, ID_CITYREP_TOP),
//		get_report_title("City Advisor"));
//  for (i=0, spec=city_report_specs;i<NUM_CREPORT_COLS;i++, spec++)
//    {
//      row[i] = buf[i];    
//    }
//  /* FIXME restore old selection */
//  ListBox_ResetContent(GetDlgItem(hCityRep,ID_CITYREP_LIST));
//  for (city pcity : Game.game.player_ptr.cities.data) {
//    get_city_text(pcity, row, sizeof(buf[0]));
//    full_row[0]=0;
//    for(i=0; i<NUM_CREPORT_COLS; i++)
//      {
//	sz_strlcat(full_row,buf[i]);
//      }
//    my_cityrep_add_city(GetDlgItem(hCityRep,ID_CITYREP_LIST),pcity,
//			full_row,row);
// 
//    
//  } }         
//}
//
//
///**************************************************************************
//
//**************************************************************************/
//void
//city_report_dialog_update_city(city pcity)
//{
//  char *row [NUM_CREPORT_COLS];
//  char  buf [NUM_CREPORT_COLS][64];
//  char full_row[512];
//  int   i,nCount;     
//  city_report_spec spec; 
//  HWND hLst; 
//  if(is_report_dialogs_frozen()) return;
//  if(!hCityRep) return;     
//  hLst=GetDlgItem(hCityRep,ID_CITYREP_LIST);
//  for (i=0, spec=city_report_specs;i<NUM_CREPORT_COLS;i++, spec++)
//    {
//      row[i] = buf[i];
//    }   
//  get_city_text(pcity, row, sizeof(buf[0]));    
//  full_row[0]=0;
//  for(i=0; i<NUM_CREPORT_COLS; i++)
//    {
//      sz_strlcat(full_row,buf[i]);
//    } 
//  nCount=ListBox_GetCount(hLst);
//  for(i=0;i<nCount;i++)
//    {
//      if (pcity==(city )ListBox_GetItemData(hLst,i))
//	{
//	  ListBox_DeleteString(hLst,i);
//	  ListBox_InsertString(hLst,i,full_row);
//	  ListBox_SetItemData(hLst,i,pcity);
//	}
//    }
//}
//
///****************************************************************
// After a selection rectangle is defined, make the cities that
// are hilited on the canvas exclusively hilited in the
// City List window.
//*****************************************************************/
//void hilite_cities_from_canvas()
//{
//  /* PORTME */
//}
//
///****************************************************************
// Toggle a city's hilited status.
//*****************************************************************/
//void toggle_city_hilite(city pcity, boolean on_off)
//{
//  /* PORTME */
//}
}