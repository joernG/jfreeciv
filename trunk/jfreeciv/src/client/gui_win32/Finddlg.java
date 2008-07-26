package client.gui_win32;

public class Finddlg{
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
//#include <windows.h>
//#include <windowsx.h>
//
//#include "fcintl.h"
//#include "Game.game.h"
//#include "player.h"
//#include "gui_main.h"
//#include "gui_stuff.h"
//
//#include "dialogs.h"
//#include "mapview.h"     
//#include "finddlg.h"
//
//extern HWND root_window;
//extern HINSTANCE freecivhinst;
//static HWND finddialog;
//
///**************************************************************************
//
//**************************************************************************/
//static void update_find_dialog()
//{
//  int i,id;
//  ListBox_ResetContent(GetDlgItem(finddialog,ID_FINDCITY_LIST));
//   
//  for(i=0; i<Game.game.nplayers; i++) {
//    city_list_iterate(Game.game.players[i].cities, pcity);
//    id=ListBox_AddString(GetDlgItem(finddialog,ID_FINDCITY_LIST),pcity.name);
//    ListBox_SetItemData(GetDlgItem(finddialog,ID_FINDCITY_LIST),id,i);
//    
//    }
// 
//  }        
//}
//
///**************************************************************************
//
//**************************************************************************/
//static LONG CALLBACK find_city_proc(HWND hWnd,
//				    UINT message,
//				    WPARAM wParam,
//				    LPARAM lParam)
//{
//  int id;
//  switch(message)
//    {                  
//    case WM_CREATE: 
//      break;
//    case WM_CLOSE:
//      DestroyWindow(hWnd);
//      break;
//    case WM_SIZE:
//    case WM_GETMINMAXINFO:
//    case WM_DESTROY:
//      break;
//    case WM_COMMAND:
//      switch (LOWORD(wParam))
//	{
//	case ID_FINDCITY_CENTER:
//	  id=ListBox_GetCurSel(GetDlgItem(hWnd,ID_FINDCITY_LIST));
//	  if (id!=LB_ERR)
//	    {
//	      char city_name[512];
//	      city pcity;    
//	      ListBox_GetText(GetDlgItem(hWnd,ID_FINDCITY_LIST),id,
//			      city_name);
//	      if ((pcity=Game.game_find_city_by_name(city_name)))
//		{
//		  center_tile_mapcanvas(pcity.tile);
//		}
//	      DestroyWindow(hWnd);
//	      finddialog=null;
//	    }
//	  break;
//	case ID_FINDCITY_CANCEL:
//	  DestroyWindow(hWnd);
//	  finddialog=null;
//	  break;
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
//void
//popup_find_dialog()
//{
//  if (!finddialog) {
//    fcwin_box vbox;
//    fcwin_box hbox;
//    finddialog=fcwin_create_layouted_window(find_city_proc,"Find City",
//					    WS_OVERLAPPEDWINDOW,
//					    CW_USEDEFAULT,CW_USEDEFAULT,
//					    root_window,null,
//					    REAL_CHILD,
//					    null);
//    vbox=fcwin_vbox_new(finddialog,false);
//    fcwin_box_add_static(vbox,"Select a city:",0,SS_LEFT,false,false,5);
//    fcwin_box_add_list(vbox,20,ID_FINDCITY_LIST,LBS_HASSTRINGS | LBS_STANDARD,
//		       true,true,15);
//    hbox=fcwin_hbox_new(finddialog,true);
//    fcwin_box_add_button(hbox,"Center",ID_FINDCITY_CENTER,0,true,true,25);
//    fcwin_box_add_button(hbox,"Cancel",ID_FINDCITY_CANCEL,0,true,true,25);
//    fcwin_box_add_box(vbox,hbox,false,false,5);
//    fcwin_set_box(finddialog,vbox);
//  }
//  update_find_dialog();
//  ShowWindow(finddialog,SW_SHOWNORMAL);
//}
}