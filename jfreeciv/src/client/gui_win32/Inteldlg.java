package client.gui_win32;

public class Inteldlg{

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
//
//#include <windows.h>
//#include <windowsx.h>
//
//#include "fcintl.h"
//#include "game.h"
//#include "government.h"
//#include "packets.h"
//#include "player.h"
//#include "shared.h"
//#include "support.h"
//
//#include "clinet.h"
//#include "gui_main.h"
//#include "gui_stuff.h"
//#include "mapview.h"
//
//
//#include "inteldlg.h"
//
//static HWND intel_dialog;
//
///****************************************************************
//
//*****************************************************************/
//static LONG CALLBACK intel_proc(HWND dlg,UINT message,WPARAM wParam,LPARAM lParam)
//{
//  switch(message) {
//  case WM_SIZE:
//  case WM_GETMINMAXINFO:
//  case WM_CREATE:
//    break;
//  case WM_DESTROY:
//    intel_dialog=null;
//    break;
//  case WM_CLOSE:
//    DestroyWindow(intel_dialog);
//    break;
//  case WM_COMMAND:
//    if (LOWORD(wParam)==IDCANCEL)
//      DestroyWindow(intel_dialog);
//    break;
//  default:
//    return DefWindowProc(dlg,message,wParam,lParam);
//  }
//  return 0;
//}
//
///****************************************************************
//
//*****************************************************************/
//static void intel_create_dialog(player p)
//{
//  HWND lb;
//  char buf[64];
//  city pcity;
//  
//  fcwin_box hbox;
//  fcwin_box vbox;
//  
//
//  static char tech_list_names[A_LAST+1][200];
//  int i, j;
//  
//  intel_dialog=fcwin_create_layouted_window(intel_proc,
//					    "Foreign Intelligence Report",
//					    WS_OVERLAPPEDWINDOW,
//					    CW_USEDEFAULT,CW_USEDEFAULT,
//					    root_window,null,
//					    JUST_CLEANUP,
//					    null);
//  vbox=fcwin_vbox_new(intel_dialog,false);
//
//  my_snprintf(buf, sizeof(buf),
//              "Intelligence Information for the %s Empire", 
//              get_nation_name(p.nation));
//  fcwin_box_add_static(vbox,buf,0,SS_LEFT,false,false,5);
//  hbox=fcwin_hbox_new(intel_dialog,false);
//  
//  my_snprintf(buf, sizeof(buf), "Ruler: %s %s", 
//              get_ruler_title(p.government, p.is_male, p.nation), p.name);
//  fcwin_box_add_static(hbox,buf,0,SS_CENTER,true,true,10);
//
//  my_snprintf(buf, sizeof(buf), "Government: %s",  
//	      get_government_name(p.government));
//  fcwin_box_add_static(hbox,buf,0,SS_CENTER,true,true,10);
//  fcwin_box_add_box(vbox,hbox,false,false,5);
//  
//  hbox=fcwin_hbox_new(intel_dialog,false);
//  
//  my_snprintf(buf, sizeof(buf), "Gold: %d", p.economic.gold);
//  fcwin_box_add_static(hbox,buf,0,SS_CENTER,true,true,10);
//  
//  my_snprintf(buf, sizeof(buf), "Tax: %d%%", p.economic.tax);
//  fcwin_box_add_static(hbox,buf,0,SS_CENTER,true,true,10);
//  
//  my_snprintf(buf, sizeof(buf), "Science: %d%%", p.economic.science);
//  fcwin_box_add_static(hbox,buf,0,SS_CENTER,true,true,10);
//  
//  my_snprintf(buf, sizeof(buf), "Luxury: %d%%", p.economic.luxury);
//  fcwin_box_add_static(hbox,buf,0,SS_CENTER,true,true,10);
//  
//  fcwin_box_add_box(vbox,hbox,false,false,5);
//  
//  hbox=fcwin_hbox_new(intel_dialog,false);
//   
//  my_snprintf(buf, sizeof(buf), "Researching: %s(%d/%d)",
//	      get_tech_name(p, p.research.researching),
//	      p.research.bulbs_researched, total_bulbs_required(p));
//  fcwin_box_add_static(hbox,buf,0,SS_CENTER,true,true,10);
//  
//  pcity = find_palace(p);
//  my_snprintf(buf, sizeof(buf), "Capital: %s",
//              (!pcity)?"(Unknown)":pcity.name);
//  fcwin_box_add_static(hbox,buf,0,SS_CENTER,true,true,10);
//  
//  fcwin_box_add_box(vbox,hbox,false,false,5);
//
//  lb=fcwin_box_add_list(vbox,10,0,LBS_NOSEL | LBS_SORT | WS_VSCROLL,
//			true,true,5);
//  
//  for(i=A_FIRST, j=0; i<game.num_tech_types; i++)
//    if(get_invention(p, i)==TECH_KNOWN) {
//      if(get_invention(game.player_ptr, i)==TECH_KNOWN) {
//        sz_strlcpy(tech_list_names[j], advances[i].name);
//      } else {
//        my_snprintf(tech_list_names[j], sizeof(tech_list_names[j]),
//                    "%s*", advances[i].name);
//      }
//      ListBox_AddString(lb,tech_list_names[j]);
//      j++;
//    }
//
//  fcwin_box_add_button(vbox,"Close",IDCANCEL,0,false,false,5);
//  fcwin_set_box(intel_dialog,vbox);
//}
//
///****************************************************************
//... 
//*****************************************************************/
//void
//popup_intel_dialog(player p)
//{
//  if(!intel_dialog) {
//    intel_create_dialog(p);
//    ShowWindow(intel_dialog,SW_SHOWNORMAL);
//  }
//}
//
///****************************************************************************
//  Update the intelligence dialog for the given player.  This is called by
//  the core client code when that player's information changes.
//****************************************************************************/
//void update_intel_dialog(player p)
//{
//  /* PORTME */
//}
}