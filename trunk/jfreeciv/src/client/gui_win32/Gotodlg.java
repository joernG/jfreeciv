package client.gui_win32;

public class Gotodlg{

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
//#include <windows.h>
//#include <windowsx.h>
//
//#include "fcintl.h"
//#include "game.h"
//#include "map.h"
//#include "packets.h"
//#include "player.h"
//#include "support.h"
//#include "unit.h"
//
//#include "clinet.h"
//#include "civclient.h"
//#include "control.h"
//#include "dialogs.h"
//#include "gui_main.h"
//#include "gui_stuff.h"
//#include "mapview.h"
//
//
//#include "gotodlg.h"
//
//public static final int ID_GOTO = 100;
//public static final int ID_AIRLIFT = 101;
//public static final int ID_LIST = 102;
//public static final int ID_ALL = 103;
//
//static HWND goto_dialog;
//static HWND goto_list;
//
//static tile original_tile;
//
//static void update_goto_dialog(HWND list);
//static city get_selected_city();
//static int show_all_cities;
//
///****************************************************************
//
//*****************************************************************/
//static LONG CALLBACK goto_dialog_proc(HWND dlg,UINT message,
//				      WPARAM wParam,LPARAM lParam)
//{
//  city pdestcity;
//  switch(message)
//    {
//    case WM_CREATE:
//    case WM_GETMINMAXINFO:
//    case WM_SIZE:
//      break;
//    case WM_DESTROY:
//      goto_dialog=null;
//      break;
//    case WM_CLOSE:
//      center_tile_mapcanvas(original_tile); 
//      DestroyWindow(dlg);
//      break;
//    case WM_COMMAND:
//      switch(LOWORD(wParam))
//	{
//	case ID_LIST:
//	  if((pdestcity=get_selected_city())) {
//	    unit punit=get_unit_in_focus();
//	    center_tile_mapcanvas(pdestcity.tile);
//	    if(punit && unit_can_airlift_to(punit, pdestcity)) {
//	      EnableWindow(GetDlgItem(dlg,ID_AIRLIFT),true);
//	    } else {
//	      EnableWindow(GetDlgItem(dlg,ID_AIRLIFT),false);
//	    }
//	    break;
//	  }
//	  break;
//	case ID_ALL:
//	  show_all_cities=show_all_cities?0:1;
//	  update_goto_dialog(GetDlgItem(dlg,ID_LIST));
//	  break;
//	case ID_GOTO:
//	  {
//	    pdestcity=get_selected_city();
//	    if (pdestcity) {
//	      unit punit=get_unit_in_focus();
//	      if (punit) {
//		send_goto_unit(punit, pdestcity.tile);
//		DestroyWindow(dlg);
//	      }
//	    }
//	  }
//	  break;
//	case ID_AIRLIFT:
//	  {
//	    pdestcity=get_selected_city();
//	    if (pdestcity) {
//	      unit punit=get_unit_in_focus();
//	      if (punit) {
//		request_unit_airlift(punit, pdestcity);
//		DestroyWindow(dlg);
//	      }
//	    }
//	 
//	  }
//	  break;
//	case IDCANCEL:
//	  center_tile_mapcanvas(original_tile);
//	  DestroyWindow(dlg);
//	  break;
//	  
//	}
//      break;
//    default:
//      return DefWindowProc(dlg,message,wParam,lParam);
//    }
//  return 0;
//}
///****************************************************************
//...
//*****************************************************************/
//void
//popup_goto_dialog()
//{
//  fcwin_box hbox;
//  fcwin_box vbox;
//  if (goto_dialog)
//    return;
//  if (!can_client_change_view()) {
//    return;
//  }
//  if (get_unit_in_focus()==0)
//    return;
//
//  original_tile = get_center_tile_mapcanvas();
//  
//  goto_dialog=fcwin_create_layouted_window(goto_dialog_proc,
//					   "Goto/Airlift Unit",
//					   WS_OVERLAPPEDWINDOW,
//					   CW_USEDEFAULT,CW_USEDEFAULT,
//					   root_window,null,
//					   REAL_CHILD,
//					   null);
//  vbox=fcwin_vbox_new(goto_dialog,false);
//  hbox=fcwin_hbox_new(goto_dialog,true);
//  fcwin_box_add_static(vbox,"Goto/Airlift Unit",
//		       0,SS_LEFT,false,false,5);
//  goto_list=fcwin_box_add_list(vbox,10,ID_LIST,
//			       WS_VSCROLL | LBS_STANDARD,true,true,5);
//  fcwin_box_add_button(hbox,"Goto",ID_GOTO,0,true,true,15);
//
//  fcwin_box_add_button(hbox,"Airlift",ID_AIRLIFT,0,true,true,15);
//  EnableWindow(GetDlgItem(goto_dialog,ID_AIRLIFT),false);
//  fcwin_box_add_button(hbox,"All Cities",ID_ALL,
//		       0,true,true,15);
//  fcwin_box_add_button(hbox,"Cancel",IDCANCEL,0,true,true,15);
//  fcwin_box_add_box(vbox,hbox,false,false,5);
//  fcwin_set_box(goto_dialog,vbox);
//  update_goto_dialog(goto_list);
//  ShowWindow(goto_dialog,SW_SHOWNORMAL);
//}
//
///**************************************************************************
//...
//**************************************************************************/
//static void update_goto_dialog(HWND list)
//{
//  int    i, j;
//  char   name[MAX_LEN_NAME+3];
//
//  ListBox_ResetContent(list);
//  Button_SetState(GetDlgItem(goto_dialog,ID_ALL),show_all_cities);
//
//  for(i=0; i<game.nplayers; i++) {
//    if(!show_all_cities && i!=game.player_idx) continue;
//    city_list_iterate(game.players[i].cities, pcity) {
//      sz_strlcpy(name, pcity.name);
//      /* FIXME: should use unit_can_airlift_to(). */
//      if (pcity.airlift) {
//        sz_strlcat(name, "(A)");
//      }
//      j=ListBox_AddString(list,name);
//      ListBox_SetItemData(list,j,pcity.id);
//    }
//    }
//  }
//}
//
///**************************************************************************
//...
//**************************************************************************/
//static city get_selected_city()
//{
//  int selection;  
//  if ((selection=ListBox_GetCurSel(goto_list))==LB_ERR)
//    return 0;
//  return find_city_by_id(ListBox_GetItemData(goto_list,selection));
//
//}
}