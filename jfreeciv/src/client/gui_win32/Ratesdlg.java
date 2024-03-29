package client.gui_win32;

public class Ratesdlg{
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
//#include <windows.h>
//#include <windowsx.h>
//
//
//#include "fcintl.h"
//#include "Game.game.h"
//#include "government.h"
//#include "packets.h"
//#include "player.h"
//#include "shared.h"
//#include "support.h"
//#include "gui_main.h" 
//
//#include "gui_stuff.h"
//#include "mapview.h"
//
//#include "ratesdlg.h"
//
//
//extern struct connection aconnection;    
//extern HINSTANCE freecivhinst;
//static HWND ratesdlg;
//int rates_tax_value, rates_lux_value, rates_sci_value;     
//
///**************************************************************************
//
//**************************************************************************/
//static void rates_set_values(int tax, int no_tax_scroll,
//                             int lux, int no_lux_scroll,
//                             int sci, int no_sci_scroll)    
//{
//  HWND scroll;
//  char buf[64];
//  int tax_lock, lux_lock, sci_lock;
//  int maxrate;  
//  
//
//  tax_lock=IsDlgButtonChecked(ratesdlg,ID_RATES_TAXLOCK);
//  lux_lock=IsDlgButtonChecked(ratesdlg,ID_RATES_LUXURYLOCK);
//  sci_lock=IsDlgButtonChecked(ratesdlg,ID_RATES_SCIENCELOCK);
//  
//  maxrate=get_government_max_rate(Game.game.player_ptr.government);
//  /* This's quite a simple-minded "double check".. */     
//  tax=Math.min(tax, maxrate);
//  lux=Math.min(lux, maxrate);
//  sci=Math.min(sci, maxrate);
//  
//  if(tax+sci+lux!=100)
//    {                     
//      if((tax!=rates_tax_value))
//	{
//	  if(!lux_lock)
//	    lux=Math.min(MAX(100-tax-sci, 0), maxrate);
//	  if(!sci_lock)
//	    sci=Math.min(MAX(100-tax-lux, 0), maxrate);
//	}
//      else if((lux!=rates_lux_value))
//	{
//	  if(!tax_lock)
//	    tax=Math.min(MAX(100-lux-sci, 0), maxrate);
//	  if(!sci_lock)
//	    sci=Math.min(MAX(100-lux-tax, 0), maxrate);
//	}
//      else if((sci!=rates_sci_value))
//	{
//	  if(!lux_lock)
//	    lux=Math.min(MAX(100-tax-sci, 0), maxrate);
//	  if(!tax_lock)
//	    tax=Math.min(MAX(100-lux-sci, 0), maxrate);
//	}
//      
//      if(tax+sci+lux!=100) {
//	tax=rates_tax_value;
//	lux=rates_lux_value;
//	sci=rates_sci_value;
//	
//	rates_tax_value=-1;
//	rates_lux_value=-1;
//	rates_sci_value=-1;
//	
//	no_tax_scroll=0;
//	no_lux_scroll=0;
//	no_sci_scroll=0;
//      }
//      
//    }
//  
//  if (tax!=rates_tax_value)
//    {
//      scroll=GetDlgItem(ratesdlg,ID_RATES_TAX);
//      buf = util.my_snprintf( "%3d%%", tax); 
//      SetWindowText(GetNextSibling(scroll),buf);
//      
//      if (!no_tax_scroll)
//	{
//	  ScrollBar_SetPos(scroll,tax/10,true);
//	}
//      rates_tax_value=tax;
//    }
//  
//  if (lux!=rates_lux_value)
//    {
//      scroll=GetDlgItem(ratesdlg,ID_RATES_LUXURY);
//      buf = util.my_snprintf( "%3d%%", lux); 
//      SetWindowText(GetNextSibling(scroll),buf);
//      
//      if (!no_lux_scroll)
//	{
//	  ScrollBar_SetPos(scroll,lux/10,true);
//	}
//      rates_lux_value=lux;
//    }
//
//  if (sci!=rates_sci_value)
//    {
//      scroll=GetDlgItem(ratesdlg,ID_RATES_SCIENCE);
//      buf = util.my_snprintf( "%3d%%", sci); 
//      SetWindowText(GetNextSibling(scroll),buf);
//      
//      if (!no_sci_scroll)
//	{
//	  ScrollBar_SetPos(scroll,sci/10,true);
//	}
//      rates_sci_value=sci;
//    }
//}
//
///**************************************************************************
//
//**************************************************************************/
//static void handle_hscroll(HWND hWnd,HWND hWndCtl,UINT code,int pos) 
//{
//  int PosCur,PosMax,PosMin,id;
//  PosCur=ScrollBar_GetPos(hWndCtl);
//  ScrollBar_GetRange(hWndCtl,&PosMin,&PosMax);
//  switch(code)
//    {
//    case SB_LINELEFT: PosCur--; break;
//    case SB_LINERIGHT: PosCur++; break;
//    case SB_PAGELEFT: PosCur-=(PosMax-PosMin+1)/10; break;
//    case SB_PAGERIGHT: PosCur+=(PosMax-PosMin+1)/10; break;
//    case SB_LEFT: PosCur=PosMin; break;
//    case SB_RIGHT: PosCur=PosMax; break;
//    case SB_THUMBTRACK: PosCur=pos; break; 
//    default:
//      return;
//    }
//  id=GetDlgCtrlID(hWndCtl);
//  pos = min(10, max(0, PosCur));
//  if (id==ID_RATES_TAX)
//    {
//      int tax_value;
//      
//      tax_value=10*pos;
//      tax_value=Math.min(tax_value, 100);
//      rates_set_values(tax_value,0, rates_lux_value,0, rates_sci_value,0);  
//    }
//  else if (id==ID_RATES_LUXURY)
//    {
//      int lux_value;
//      
//      lux_value=10*pos;
//      lux_value=Math.min(lux_value, 100);
//      rates_set_values(rates_tax_value,0, lux_value,0, rates_sci_value,0);        
//    }
//  else
//    {
//      int sci_value;
//      
//      sci_value=10*pos;
//      sci_value=Math.min(sci_value, 100);
//      rates_set_values(rates_tax_value,0, rates_lux_value,0, sci_value,0);        
//    }
//}
//
///**************************************************************************
//
//**************************************************************************/
//static void scroll_minsize(POINT *rcmin,void *data)
//{
//  rcmin.y=15;
//  rcmin.x=300;
//}
//
///**************************************************************************
//
//**************************************************************************/
//static void scroll_setsize(RECT *rc,void *data)
//{
//  MoveWindow((HWND)data,rc.left,rc.top,
//	     rc.right-rc.left,
//	     rc.bottom-rc.top,true);
//}
//
///**************************************************************************
//
//**************************************************************************/
//static void scroll_del(void *data)
//{
//  DestroyWindow((HWND)data);
//}
//
///**************************************************************************
//
//**************************************************************************/
//static HWND ratesdlg_add_scroll(fcwin_box fcb,int id)
//{
//  HWND scroll;
//  scroll=CreateWindow("SCROLLBAR",null,
//		      WS_CHILD | WS_VISIBLE | SBS_HORZ,
//		      0,0,0,0,
//		      ratesdlg,
//		      (HMENU)id,
//		      freecivhinst,null);
//  fcwin_box_add_generic(fcb,scroll_minsize,scroll_setsize,scroll_del,
//			scroll,true,true,15);
//  return scroll;
//}
//
///**************************************************************************
//
//**************************************************************************/
//static LONG CALLBACK ratesdlg_proc(HWND hWnd,
//				   UINT message,
//				   WPARAM wParam,
//				   LPARAM lParam) 
//{
//  switch (message)
//    {
//    case WM_CREATE:
//    case WM_SIZE:
//    case WM_GETMINMAXINFO:
//      break;
//    case WM_DESTROY:
//      ratesdlg=null;
//      break;
//    case WM_CLOSE:
//      DestroyWindow(hWnd);
//      return true;
//    case WM_COMMAND:
//      switch(LOWORD(wParam))
//	{
//	case IDCANCEL:
//	  DestroyWindow(hWnd);
//	  break;
//	case IDOK:
//	  DestroyWindow(hWnd);
//	  dsend_packet_player_rates(&aconnection, rates_tax_value,
//				    rates_lux_value, rates_sci_value);
//	  break;
//	}
//      break;
//    case WM_HSCROLL:
//      HANDLE_WM_HSCROLL(hWnd,wParam,lParam,handle_hscroll);      
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
//popup_rates_dialog()
//{ 
//  char buf[64];    
//  rates_tax_value=-1;
//  rates_lux_value=-1;
//  rates_sci_value=-1;
//  if (!ratesdlg) {
//    fcwin_box hbox;
//    fcwin_box vbox;
//    ratesdlg=fcwin_create_layouted_window(ratesdlg_proc,
//					  ("Select tax, luxury " +
//					    "and science rates"),
//					  WS_OVERLAPPEDWINDOW,
//					  CW_USEDEFAULT,
//					  CW_USEDEFAULT,
//					  root_window,
//					  null,
//					  JUST_CLEANUP,
//					  null);
//    
//    vbox=fcwin_vbox_new(ratesdlg,false);
//    fcwin_box_add_static(vbox,"",ID_RATES_MAX,SS_CENTER,
//			 false,false,10);
//    hbox=fcwin_hbox_new(ratesdlg,false);
//    fcwin_box_add_groupbox(vbox,"Tax",hbox,0,false,false,10);
//    ratesdlg_add_scroll(hbox,ID_RATES_TAX);
//    fcwin_box_add_static(hbox,"100%",0,SS_RIGHT,false,false,20);
//    fcwin_box_add_checkbox(hbox,"Lock",ID_RATES_TAXLOCK,0,false,false,20);
//    
//    hbox=fcwin_hbox_new(ratesdlg,false);
//    fcwin_box_add_groupbox(vbox,"Luxury",hbox,0,false,false,10);  
//    ratesdlg_add_scroll(hbox,ID_RATES_LUXURY);
//    fcwin_box_add_static(hbox,"100%",0,SS_RIGHT,false,false,20);
//    fcwin_box_add_checkbox(hbox,"Lock",ID_RATES_LUXURYLOCK,0,false,false,20);
//
//    hbox=fcwin_hbox_new(ratesdlg,false);
//    fcwin_box_add_groupbox(vbox,"Science",hbox,0,false,false,10);
//    ratesdlg_add_scroll(hbox,ID_RATES_SCIENCE);
//    fcwin_box_add_static(hbox,"100%",0,SS_RIGHT,false,false,20);
//    fcwin_box_add_checkbox(hbox,"Lock",ID_RATES_SCIENCELOCK,0,false,false,20);
//    hbox=fcwin_hbox_new(ratesdlg,true);
//
//    fcwin_box_add_button(hbox,"Ok",IDOK,0,true,true,20);
//    fcwin_box_add_button(hbox,"Cancel",IDCANCEL,0,true,true,20);
//    fcwin_box_add_box(vbox,hbox,true,true,10);
//  
//    buf = util.my_snprintf( "%s max rate: %d%%",
//		get_government_name(Game.game.player_ptr.government),
//		get_government_max_rate(Game.game.player_ptr.government)); 
//    SetWindowText(GetDlgItem(ratesdlg,ID_RATES_MAX),buf);
//    ScrollBar_SetRange(GetDlgItem(ratesdlg,ID_RATES_TAX),0,10,true);
//    ScrollBar_SetRange(GetDlgItem(ratesdlg,ID_RATES_LUXURY),0,10,true);
//    ScrollBar_SetRange(GetDlgItem(ratesdlg,ID_RATES_SCIENCE),0,10,true);
//    rates_set_values( Game.game.player_ptr.economic.tax, 0,
//		      Game.game.player_ptr.economic.luxury, 0,
//		      Game.game.player_ptr.economic.science, 0 );          
//    
//    fcwin_set_box(ratesdlg,vbox);
//    ShowWindow(ratesdlg,SW_SHOWNORMAL);
//  }
//}
}