package client.gui_win32;

public class Messagewin{
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
//#include "events.h"
//#include "fcintl.h"
//#include "game.h"
//#include "map.h"
//#include "mem.h"
//#include "packets.h"
//#include "player.h"
// 
//#include "chatline.h"
//#include "citydlg.h"
//#include "clinet.h"
//#include "colors.h"
//#include "gui_stuff.h"
//#include "mapview.h"
//#include "options.h"       
//
//#include "messagewin.h"
//
//public static final int ID_MESSAGEWIN_GOTO = 500;
//public static final int ID_MESSAGEWIN_POPUP = 501;
//public static final int ID_MESSAGEWIN_LIST = 502;
//
//extern HWND root_window;
//extern HINSTANCE freecivhinst;
//static HWND meswin_dlg;
//static fcwin_box meswin_box;
//static int max_list_width;
//
///**************************************************************************
//
//**************************************************************************/
//static LONG APIENTRY MsgdlgProc(HWND hWnd,
//				UINT message,
//				UINT wParam,
//				LONG lParam)
//{
//  switch(message)
//    {
//    case WM_CREATE:
//      return 0;
//    case WM_DESTROY:
//      meswin_box=null;
//      meswin_dlg=null;
//      break;
//    case WM_CLOSE:
//      DestroyWindow(hWnd);
//      break;
//    case WM_GETMINMAXINFO:
//      break;
//    case WM_SIZE:
//      break;
//  
//    case WM_COMMAND:
//      switch(LOWORD(wParam))
//	{
//	case IDCANCEL:
//	  DestroyWindow(hWnd);
//	  break;
//	case ID_MESSAGEWIN_LIST:
//	  {
//	    int id;
//	    id=ListBox_GetCurSel(GetDlgItem(hWnd,ID_MESSAGEWIN_LIST));
//	    if (id!=LB_ERR)
//	      {
//		EnableWindow(GetDlgItem(hWnd,ID_MESSAGEWIN_GOTO),true);
//		EnableWindow(GetDlgItem(hWnd,ID_MESSAGEWIN_POPUP),true);
//	      }
//	  }
//	  break;
//	case ID_MESSAGEWIN_GOTO:
//	  {
//	    int id = ListBox_GetCurSel(GetDlgItem(hWnd, ID_MESSAGEWIN_LIST));
//
//	    if (id != LB_ERR) {
//	      int row =
//		ListBox_GetItemData(GetDlgItem(hWnd, ID_MESSAGEWIN_LIST),
//				    id);
//	      meswin_goto(row);
//	    }   
//	  }
//	  break;
//	case ID_MESSAGEWIN_POPUP:
//	  {
//	    int id=ListBox_GetCurSel(GetDlgItem(hWnd,ID_MESSAGEWIN_LIST));
//	    if (id != LB_ERR) {
//	      int row =
//		  ListBox_GetItemData(GetDlgItem(hWnd, ID_MESSAGEWIN_LIST),
//				      id);
//	      meswin_popup_city(row);
//	    }
//	  }
//	  break;
//	}
//    default:
//      return DefWindowProc(hWnd,message,wParam,lParam);
//    }
//  return 0;
//}
//
//
///**************************************************************************
//
//**************************************************************************/
//static void create_meswin_dialog()
//{
//  HWND meswin_list;
//  fcwin_box hbox;
//  meswin_dlg=fcwin_create_layouted_window(MsgdlgProc,"Messages",
//					  WS_OVERLAPPEDWINDOW,
//					  CW_USEDEFAULT,
//					  CW_USEDEFAULT,
//					  root_window,
//					  null,
//					  REAL_CHILD,
//					  null);
//
//  meswin_box=fcwin_vbox_new(meswin_dlg,false);
//  meswin_list = fcwin_box_add_list(meswin_box,
//				   24,
//				   ID_MESSAGEWIN_LIST,
//				   LBS_NOTIFY | LBS_HASSTRINGS | WS_VSCROLL,
//				   true,true,5);
//  hbox=fcwin_hbox_new(meswin_dlg,true);
//  fcwin_box_add_button(hbox,"Close",IDCANCEL,0,true,true,5);
//  fcwin_box_add_button(hbox,"Goto location",ID_MESSAGEWIN_GOTO,0,
//		       true,true,5);
//  fcwin_box_add_button(hbox,"Popup City",ID_MESSAGEWIN_POPUP,0,
//		       true,true,5);
//  fcwin_box_add_box(meswin_box,hbox,false,false,5);
//  real_update_meswin_dialog();
//  fcwin_set_box(meswin_dlg,meswin_box);
//  ShowWindow(meswin_dlg,SW_SHOWNORMAL);
//}
//
///**************************************************************************
//
//**************************************************************************/
//void popup_meswin_dialog()
//{
//  int updated = 0;
// 
//  if(!meswin_dlg) {
//    create_meswin_dialog();
//    updated = 1;               /* create_ calls update_ */
//  }
//  
//}
//
///****************************************************************
//...
//*****************************************************************/
//boolean is_meswin_open()
//{
//  return meswin_dlg != 0;
//}
//
///**************************************************************************
//
//**************************************************************************/
//void real_update_meswin_dialog()
//{
//  int i, num = get_num_messages();
//  HWND hLst = GetDlgItem(meswin_dlg, ID_MESSAGEWIN_LIST);
//
//  max_list_width = 0;
//  ListBox_ResetContent(hLst);
//
//  for (i = 0; i < num; i++) {
//    int id = ListBox_AddString(hLst, get_message(i).descr);
//    RECT rc;
//
//    ListBox_SetItemData(hLst, id, i);
//    ListBox_GetItemRect(hLst, id, &rc);
//    max_list_width = MAX(max_list_width, rc.right - rc.left);
//  }
//  max_list_width += 20;
//  EnableWindow(GetDlgItem(meswin_dlg, ID_MESSAGEWIN_GOTO), false);
//  EnableWindow(GetDlgItem(meswin_dlg, ID_MESSAGEWIN_POPUP), false);
//}
}