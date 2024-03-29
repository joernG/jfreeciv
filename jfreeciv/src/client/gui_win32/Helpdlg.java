package client.gui_win32;

public class Helpdlg{
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
//#include <assert.h>
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
//#include "genlist.h"
//#include "government.h"
//#include "mem.h"
//#include "shared.h"
//#include "tech.h"
//#include "unit.h"
//#include "Map.map.h"
//#include "support.h"
//#include "version.h"
// 
//#include "climisc.h"
//#include "colors.h"
//#include "graphics.h"
//#include "gui_stuff.h"
//#include "helpdata.h"
//#include "options.h"
//#include "tilespec.h"
//                                  
//#include "helpdlg.h"
//public static final int ID_HELP_LIST = 109;
//public static final int ID_HELP_TECH_LINK = 110;
//public static final int ID_HELP_UNIT_LINK = 111;
//public static final int ID_HELP_IMPROVEMENT_LINK = 112;
//public static final int ID_HELP_WONDER_LINK = 113;
//
///* HACK: we use a static string for convenience. */
//static char long_buffer[64000];
//
//extern HINSTANCE freecivhinst;
//extern HWND root_window;
//static HWND helpdlg_win;
//static HWND helpdlg_list;
//static HWND helpdlg_topic;
//static HWND helpdlg_text;
//static HWND helpdlg_close;
//static HWND help_ilabel[6];
//static HWND help_ulabel[5][5];
//static HWND help_tlabel[4][5];
//
//static POINT unitpos;
//static int unit_num=-1;
//
//fcwin_box helpdlg_hbox;
//static fcwin_box helpdlg_page_vbox;
//static void create_help_dialog();
//static void help_update_dialog(final help_item pitem);
///* static void create_help_page(enum help_page_type type);
// */
//static void select_help_item(int item);
//static void select_help_item_string(final String item,
//                                    enum help_page_type htype);
//char *help_ilabel_name[6] =
//{ N"Cost:", null, N"Upkeep:", null, N"Requirement:", null };
// 
//char *help_wlabel_name[6] =
//{ N"Cost:", null, N"Requirement:", null, N"Obsolete by:", null };
// 
//char *help_ulabel_name[5][5] =
//{
//    { N"Cost:",         null, null, N"Attack:",      null },
//    { N"Defense:",      null, null, N"Move:",        null },
//    { N"FirePower:",    null, null, N"Hitpoints:",   null },
//    { N"Basic Upkeep:", null, null, N"Vision:",      null },
//    { N"Requirement:",  null, null, N"Obsolete by:", null }
//};
// 
//char *help_tlabel_name[4][5] =
//{
//    { N"Move/Defense:",   null, null, N"Food/Res/Trade:",   null },
//    { N"Sp1 F/R/T:",      null, null, N"Sp2 F/R/T:",        null },
//    { N"Road Rslt/Time:", null, null, N"Irrig. Rslt/Time:", null },
//    { N"Mine Rslt/Time:", null, null, N"Trans. Rslt/Time:", null }
//};                                 
//
//static void help_draw_unit(HDC hdc,int i);
//
///**************************************************************************
//
//**************************************************************************/
//static enum help_page_type page_type_from_id(int id)
//{
//  switch(id){
//  case ID_HELP_WONDER_LINK:
//    return HELP_WONDER;
//  case ID_HELP_IMPROVEMENT_LINK:
//    return HELP_IMPROVEMENT;
//  case ID_HELP_UNIT_LINK:
//    return HELP_UNIT;
//  case ID_HELP_TECH_LINK:
//    return HELP_TECH;
//  }
//  return HELP_ANY;
//}
//
///**************************************************************************
//
//**************************************************************************/
//static LONG APIENTRY HelpdlgWndProc(HWND hWnd,UINT uMsg,
//				    UINT wParam,
//				    LONG lParam)
//{
//  PAINTSTRUCT ps;
//  HDC hdc;
//  switch (uMsg)
//    {
//    case WM_CREATE:
//      helpdlg_win=hWnd;
//      return 0;
//    case WM_DESTROY:
//      helpdlg_win=null;
//      break;
//    case WM_GETMINMAXINFO:
//    
//      break;
//    case WM_SIZE:
//      break;
//    case WM_CLOSE:
//      DestroyWindow(hWnd);
//      break;
//    case WM_PAINT:
//      hdc=BeginPaint(hWnd,(LPPAINTSTRUCT)&ps);
//      if (unit_num>=0)
//	help_draw_unit(hdc,unit_num);
//      EndPaint(hWnd,(LPPAINTSTRUCT)&ps);
//      break;
//    case WM_COMMAND:
//      switch (LOWORD(wParam))
//	{
//	case IDCANCEL:
//	  DestroyWindow(hWnd);
//	  break;
//	case ID_HELP_TECH_LINK:
//	case ID_HELP_UNIT_LINK:
//	case ID_HELP_WONDER_LINK:
//	case ID_HELP_IMPROVEMENT_LINK:
//	  {
//	    char s[128];
//	    GetWindowText((HWND)lParam,s,sizeof(s));
//	    if (strcmp(s, "(Never)") != 0 && strcmp(s, "None") != 0
//		&& strcmp(s, advances[A_NONE].name) != 0)
//	      select_help_item_string(s,page_type_from_id(LOWORD(wParam)));
//	    
//	  }
//	  break;
//	case ID_HELP_LIST:
//	  {
//	    if (HIWORD(wParam)==LBN_SELCHANGE)
//	      {
//		int row;
//		final help_item p = null;
//		
//		row=ListBox_GetCurSel(helpdlg_list);
//		help_items_iterate(pitem) {
//		  if ((row--)==0)
//		    {
//		      p=pitem;
//		      break;
//		    }
//		}
//		help_items_iterate_end;
//		
//		if (p)
//		  help_update_dialog(p);
//	      }
//	  }
//	  break;
//	}
//      break;
//    default:
//      return DefWindowProc(hWnd,uMsg,wParam,lParam);
//      
//    }
//  return 0;
//}
//
///**************************************************************************
//
//**************************************************************************/
//static void set_help_text(char *txt)
//{
//  char *newstr;
//  if ((txt)&&(txt.length()))
//    {
//      newstr=convertnl2crnl(txt);
//      SetWindowText(helpdlg_text,newstr);
//      free(newstr);
//    }
//}
//
//
//
///**************************************************************************
//
//**************************************************************************/
//static void edit_minsize(POINT *pt,void *data)
//{
//  char buf[32768];
//  HWND hwnd = (HWND) data;
//  HFONT old;
//  HFONT font;
//  HDC hdc;
//  RECT rc;
//  old = null; /* just silence gcc */
//  buf[0] = 0;
//  GetWindowText(hwnd, buf, sizeof(buf));
//  if (buf.length()<10) {
//    pt.x = 300;
//    pt.y = 300;
//    return;
//  }
//  
//  hdc = GetDC(hwnd);
//  if ((font = GetWindowFont(hwnd))) {
//    old = SelectObject(hdc, font);
//  }
//  rc.left = 0;
//  rc.right = 10000;
//  rc.top=0;
//  DrawText(hdc, buf, buf.length(), &rc, DT_CALCRECT);
//  pt.x = rc.right - rc.left + 40;
//  pt.y = 300;
//  if (font) {
//    SelectObject(hdc, old);
//  }
//  ReleaseDC(hwnd, hdc);
//}
//
///**************************************************************************
//
//**************************************************************************/
//static void edit_setsize(LPRECT rc, void *data)
//{
//  HWND hWnd;
//  hWnd=(HWND)data;
//  MoveWindow(hWnd,rc.left,rc.top,rc.right-rc.left,rc.bottom-rc.top,true);
//}
//
///**************************************************************************
//
//**************************************************************************/
//static void create_improvement_page(fcwin_box vbox)
//{
//  fcwin_box hbox;
//  int i;
//  hbox=fcwin_hbox_new(helpdlg_win,false);
//  fcwin_box_add_box(vbox,hbox,false,false,5);
//  for (i=0;i<5;i++)
//    {
//      help_ilabel[i]=fcwin_box_add_static(hbox,
//					     _(help_ilabel_name[i]),0,
//					     SS_LEFT,true,true,5);
//    }
//  help_ilabel[5]=fcwin_box_add_button(hbox,"",ID_HELP_TECH_LINK,0,true,true,5);
//}
//
//
///**************************************************************************
//
//**************************************************************************/
//static void create_wonder_page(fcwin_box vbox)
//{
//  fcwin_box hbox;
//  int i;
//  hbox=fcwin_hbox_new(helpdlg_win,false);
//  fcwin_box_add_box(vbox,hbox,false,false,5);
//  for(i=0;i<5;i++)
//    {
//      if (i==3)
//	help_ilabel[3]=fcwin_box_add_button(hbox,"",
//					    ID_HELP_TECH_LINK,
//					    0,true,true,5);
//      else
//	help_ilabel[i]=fcwin_box_add_static(hbox,
//					    _(help_wlabel_name[i]),0,
//					    SS_LEFT,true,true,5);      
//    }
//  help_ilabel[5]=fcwin_box_add_button(hbox,"",ID_HELP_TECH_LINK,
//				      0,true,true,5);
//  
//}
//
///*************************************************************************
//
//**************************************************************************/
//static void unit_minsize(POINT *min,void *data)
//{
//  min.x=UNIT_TILE_WIDTH;
//  min.y=UNIT_TILE_HEIGHT;
//}
//
///*************************************************************************
//
//**************************************************************************/
//static void unit_setsize(RECT *rc,void *data)
//{
//  unitpos.x=rc.left;
//  unitpos.y=rc.top;
//  InvalidateRect(helpdlg_win,rc,true);
//}
//
///**************************************************************************
//
//***************************************************************************/
//static void create_unit_page(fcwin_box vbox)
//{
//  int x,y;
//  fcwin_box hbox;
//  fcwin_box vbox_labels;
//  fcwin_box_add_generic(vbox,unit_minsize,unit_setsize,null,null,false,false,5);
//  hbox=fcwin_hbox_new(helpdlg_win,false);
//  fcwin_box_add_box(vbox,hbox,false,false,5);
//  for(x=0;x<5;x++) {
//    vbox_labels=fcwin_vbox_new(helpdlg_win,false);
//    fcwin_box_add_box(hbox,vbox_labels,true,true,15);
//    for(y=0;y<4;y++) {
//      help_ulabel[y][x]=
//	fcwin_box_add_static(vbox_labels, _(help_ulabel_name[y][x]), 0,
//			     SS_LEFT, false, false, 0);
//    }
//    if ((x==1)||(x==4))
//      help_ulabel[y][x]=
//	fcwin_box_add_button(vbox_labels, _(help_ulabel_name[y][x]),
//			     x==1?ID_HELP_TECH_LINK:ID_HELP_UNIT_LINK,
//			     0,true,true,0);
//    else
//      help_ulabel[y][x]=
//	fcwin_box_add_static(vbox_labels, _(help_ulabel_name[y][x]), 0,
//			     SS_LEFT, true, true, 0);
//  }
//}
//
///**************************************************************************
//
//**************************************************************************/
//static void create_terrain_page(fcwin_box vbox)
//{
//  int x,y;
//  fcwin_box hbox;
//  fcwin_box vbox_labels;  
//  hbox=fcwin_hbox_new(helpdlg_win,false);
//  fcwin_box_add_box(vbox,hbox,false,false,5);
//  for(x=0;x<5;x++) {
//    vbox_labels=fcwin_vbox_new(helpdlg_win,false);
//    fcwin_box_add_box(hbox,vbox_labels,true,true,15);
//    for(y=0;y<4;y++) {
//      help_tlabel[y][x]=
//	fcwin_box_add_static(vbox_labels, _(help_tlabel_name[y][x]), 0,
//			     SS_LEFT, false, false, 0);
//    }
//  }
//}
//
///**************************************************************************
//
//**************************************************************************/
//static void create_help_page(enum help_page_type type)
//{
//
//  RECT rc;
//  GetClientRect(helpdlg_win,&rc);
//  InvalidateRect(helpdlg_win,&rc,true);
//  fcwin_box_freeitem(helpdlg_hbox,1);
//  unit_num=-1;
//  helpdlg_page_vbox=fcwin_vbox_new(helpdlg_win,false);
//  helpdlg_topic=fcwin_box_add_static(helpdlg_page_vbox,
//				     "",0,SS_LEFT,false,false,5);
//  fcwin_box_add_box(helpdlg_hbox,helpdlg_page_vbox,true,true,5);
//  switch(type) {
//  case HELP_IMPROVEMENT:
//    create_improvement_page(helpdlg_page_vbox);
//    break;
//  case HELP_WONDER:
//    create_wonder_page(helpdlg_page_vbox);
//    break;
//  case HELP_UNIT:
//    create_unit_page(helpdlg_page_vbox);
//    break;
//  case HELP_TERRAIN:
//    create_terrain_page(helpdlg_page_vbox);
//    break;
//  default:
//    break;
//  }
//  if (type!=HELP_TECH)
//    fcwin_box_add_generic(helpdlg_page_vbox,edit_minsize,edit_setsize,null,
//			  helpdlg_text,true,true,5);
//  ShowWindow(helpdlg_text,type==HELP_TECH?SW_HIDE:SW_SHOWNORMAL);
//}
//
///**************************************************************************
//
//**************************************************************************/
//void create_help_dialog()
//{
//  fcwin_box vbox;
//  unit_num=-1;
//  helpdlg_win=fcwin_create_layouted_window(HelpdlgWndProc,
//					   "Freeciv Help Browser",
//					   WS_OVERLAPPEDWINDOW,
//					   CW_USEDEFAULT,CW_USEDEFAULT,
//					   root_window,
//					   null,
//					   JUST_CLEANUP,
//					   null);
//  
//  helpdlg_hbox=fcwin_hbox_new(helpdlg_win,false);
//  vbox=fcwin_vbox_new(helpdlg_win,false);
//  helpdlg_topic=fcwin_box_add_static(vbox,"",0,SS_LEFT,
//				     false,false,5);
//  helpdlg_text=CreateWindow("EDIT","",
//			    WS_CHILD | WS_VISIBLE |  ES_READONLY | 
//			    ES_WANTRETURN | 
//			    ES_AUTOHSCROLL | ES_LEFT | WS_VSCROLL |
//			    ES_AUTOVSCROLL | ES_MULTILINE | WS_BORDER,0,0,0,0,
//			    helpdlg_win,
//			    null,
//			    freecivhinst,
//			    null);
//  fcwin_box_add_generic(vbox,edit_minsize,edit_setsize,null,
//			helpdlg_text,true,true,5);
//  helpdlg_list=fcwin_box_add_list(helpdlg_hbox,6,ID_HELP_LIST, 
//		     LBS_NOTIFY | WS_VSCROLL,
//		     false,false,5);
//  fcwin_box_add_box(helpdlg_hbox,vbox,true,true,5);
//  vbox=fcwin_vbox_new(helpdlg_win,false);
//  fcwin_box_add_box(vbox,helpdlg_hbox,true,true,5);
//  helpdlg_close=fcwin_box_add_button(vbox,"Close",IDCANCEL,0,
//		       false,false,5);
//  help_items_iterate(pitem)
//    ListBox_AddString(helpdlg_list,pitem.topic);
//  help_items_iterate_end;
//  fcwin_set_box(helpdlg_win,vbox);
//  create_help_page(HELP_TEXT);
//  
//  fcwin_redo_layout(helpdlg_win);
//  
//}
//
///**************************************************************************
//...
//**************************************************************************/
//static void help_update_improvement(final help_item pitem,
//                                    char *title, int which)
//{
//  char buf[64000];
// 
//  create_help_page(HELP_IMPROVEMENT);
// 
//  if (which<Improvement.B_LAST) {
//    impr_type imp = &Improvement.improvement_types[which];
//    sprintf(buf, "%d", Improvement.impr_build_shield_cost(which));
//    SetWindowText(help_ilabel[1], buf);
//    sprintf(buf, "%d", imp.upkeep);
//    SetWindowText(help_ilabel[3], buf);
//    if (imp.tech_req == Tech_H.A_LAST) {
//      SetWindowText(help_ilabel[5], "(Never)");
//    } else {
//      SetWindowText(help_ilabel[5], advances[imp.tech_req].name);
//    }
///*    create_tech_tree(help_improvement_tree, 0, imp.tech_req, 3);*/
//  }
//  else {
//    SetWindowText(help_ilabel[1], "0");
//    SetWindowText(help_ilabel[3], "0");
//    SetWindowText(help_ilabel[5], "(Never)");
///*    create_tech_tree(help_improvement_tree, 0, Game.game.num_tech_types, 3);*/
//  }
//  helptext_building(buf, sizeof(buf), which, pitem.text);
//  set_help_text(buf);
//
//}
///**************************************************************************
//...
//**************************************************************************/
//static void help_update_wonder(final help_item pitem,
//                               char *title, int which)
//{
//  char buf[64000];
// 
//  create_help_page(HELP_WONDER);
// 
//  if (which<Improvement.B_LAST) {
//    impr_type imp = &Improvement.improvement_types[which];
//    sprintf(buf, "%d", Improvement.impr_build_shield_cost(which));
//    SetWindowText(help_ilabel[1], buf);
//    if (imp.tech_req == Tech_H.A_LAST) {
//      SetWindowText(help_ilabel[3], "(Never)");
//    } else {
//      SetWindowText(help_ilabel[3], advances[imp.tech_req].name);
//    }
//    if (tech_exists(imp.obsolete_by)) {
//      SetWindowText(help_ilabel[5], advances[imp.obsolete_by].name);
//    } else {
//      SetWindowText(help_ilabel[5], "(Never)");
//    }
//
//    /*    create_tech_tree(help_improvement_tree, 0, imp.tech_req, 3);*/
//  }
//  else {
//    /* can't find wonder */
//    SetWindowText(help_ilabel[1], "0");
//    SetWindowText(help_ilabel[3], "(Never)");
//    SetWindowText(help_ilabel[5], "None");
///*    create_tech_tree(help_improvement_tree, 0, Game.game.num_tech_types, 3); */
//  }
// 
//  helptext_building(buf, sizeof(buf), which, pitem.text);
//  set_help_text(buf);
//}                            
//
///**************************************************************************
//...
//**************************************************************************/
//static void help_update_terrain(final help_item pitem,
//				char *title, int i)
//{
//  char *buf = &long_buffer[0];
//  tile_type ptype = get_tile_type(i);
//
//  create_help_page(HELP_TERRAIN);
//
//  if (i < Terrain_H.T_COUNT) {
//    sprintf(buf, "%d/%d.%d",
//	    ptype.movement_cost,
//	    (int)(ptype.defense_bonus/10),
//	    ptype.defense_bonus%10);
//    SetWindowText (help_tlabel[0][1], buf);
//
//    sprintf(buf, "%d/%d/%d",
//	    ptype.food,
//	    ptype.shield,
//	    ptype.trade);
//    SetWindowText(help_tlabel[0][4], buf);
//
//    if (*(ptype.special_1_name)) {
//      sprintf(buf, "%s F/R/T:",
//	      ptype.special_1_name);
//      SetWindowText(help_tlabel[1][0], buf);
//      sprintf(buf, "%d/%d/%d",
//	      ptype.food_special_1,
//	      ptype.shield_special_1,
//	      ptype.trade_special_1);
//      SetWindowText(help_tlabel[1][1], buf);
//    } else {
//      SetWindowText(help_tlabel[1][0], " ");
//      SetWindowText(help_tlabel[1][1], " ");
//    }
//
//    if (*(ptype.special_2_name)) {
//      sprintf(buf, "%s F/R/T:",
//	      ptype.special_2_name);
//      SetWindowText(help_tlabel[1][3], buf);
//      sprintf(buf, "%d/%d/%d",
//	      ptype.food_special_2,
//	      ptype.shield_special_2,
//	      ptype.trade_special_2);
//      SetWindowText(help_tlabel[1][4], buf);
//    } else {
//      SetWindowText(help_tlabel[1][3], " ");
//      SetWindowText(help_tlabel[1][4], " ");
//    }
//
//    if (ptype.road_trade_incr > 0) {
//      sprintf(buf, "+%d Trade / %d",
//	      ptype.road_trade_incr,
//	      ptype.road_time);
//    } else if (ptype.road_time > 0) {
//      sprintf(buf, "no extra / %d",
//	      ptype.road_time);
//    } else {
//      strcpy(buf, "n/a");
//    }
//    SetWindowText(help_tlabel[2][1], buf);
//
//    strcpy(buf, "n/a");
//    if (ptype.irrigation_result == i) {
//      if (ptype.irrigation_food_incr > 0) {
//	sprintf(buf, "+%d Food / %d",
//		ptype.irrigation_food_incr,
//		ptype.irrigation_time);
//      }
//    } else if (ptype.irrigation_result != Terrain_H.T_NONE) {
//      sprintf(buf, "%s / %d",
//	      get_tile_type(ptype.irrigation_result).terrain_name,
//	      ptype.irrigation_time);
//    }
//    SetWindowText(help_tlabel[2][4], buf);
//
//    strcpy(buf, "n/a");
//    if (ptype.mining_result == i) {
//      if (ptype.mining_shield_incr > 0) {
//	sprintf(buf, "+%d Res. / %d",
//		ptype.mining_shield_incr,
//		ptype.mining_time);
//      }
//    } else if (ptype.mining_result != Terrain_H.T_NONE) {
//      sprintf(buf, "%s / %d",
//	      get_tile_type(ptype.mining_result).terrain_name,
//	      ptype.mining_time);
//    }
//    SetWindowText(help_tlabel[3][1], buf);
//
//    if (ptype.transform_result != Terrain_H.T_NONE) {
//      sprintf(buf, "%s / %d",
//	      get_tile_type(ptype.transform_result).terrain_name,
//	      ptype.transform_time);
//    } else {
//      strcpy(buf, "n/a");
//    }
//    SetWindowText(help_tlabel[3][4], buf);
//  }
//
//  helptext_terrain(buf, i, pitem.text);
//  set_help_text(buf);
//}
//
///*************************************************************************
//
//*************************************************************************/
//static void help_draw_unit(HDC hdc,int i)
//{
//  enum color_std bg_color;
//  RECT rc;
//  rc.top=unitpos.y;
//  rc.left=unitpos.x;
//  rc.bottom=unitpos.y+UNIT_TILE_HEIGHT;
//  rc.right=unitpos.x+UNIT_TILE_WIDTH;
//  
//  /* Give tile a background color, based on the type of unit */
//  switch (get_unit_type(i).move_type) {
//  case unit_move_type.LAND_MOVING: bg_color = COLOR_STD_GROUND; break;
//  case unit_move_type.SEA_MOVING:  bg_color = COLOR_STD_OCEAN;  break;
//  case unit_move_type.HELI_MOVING: bg_color = COLOR_STD_YELLOW; break;
//  case unit_move_type.AIR_MOVING:  bg_color = COLOR_STD_CYAN;   break;
//  default:          bg_color = COLOR_STD_BLACK;  break;
//  }
//  FillRect(hdc,&rc,brush_std[bg_color]);
//  
//  /* If we're using flags, put one on the tile */
//  if(!solid_color_behind_units)  {
//    Sprite flag=Nation.get_nation_by_plr(Game.game.player_ptr).flag_sprite;
//    draw_sprite(flag,hdc,unitpos.x,unitpos.y);
//  }
//  /* Finally, put a picture of the unit in the tile */
//  if(i<Game.game.num_unit_types) {
//    Sprite s=get_unit_type(i).sprite;
//    draw_sprite(s,hdc,unitpos.x,unitpos.y);
//  }
//  
//}
//
///**************************************************************************
//
//**************************************************************************/
//static void help_update_unit_type(final help_item pitem,
//				  char *title, int i)
//{
//  char *buf = &long_buffer[0];
//  create_help_page(HELP_UNIT);
//  unit_num=i;
//  if (i<Game.game.num_unit_types) {
//    unit_type utype = get_unit_type(i);
//    sprintf(buf, "%d", Unittype_P.unit_build_shield_cost(i));
//    SetWindowText(help_ulabel[0][1], buf);
//    sprintf(buf, "%d", utype.attack_strength);
//    SetWindowText(help_ulabel[0][4], buf);
//    sprintf(buf, "%d", utype.defense_strength);
//    SetWindowText(help_ulabel[1][1], buf);
//    sprintf(buf, "%d", utype.move_rate/3);
//    SetWindowText(help_ulabel[1][4], buf);
//    sprintf(buf, "%d", utype.firepower);
//    SetWindowText(help_ulabel[2][1], buf);
//    sprintf(buf, "%d", utype.hp);
//    SetWindowText(help_ulabel[2][4], buf);
//    SetWindowText(help_ulabel[3][1], helptext_unit_upkeep_str(i));
//    sprintf(buf, "%d", utype.vision_range);
//    SetWindowText(help_ulabel[3][4], buf);
//    if(utype.tech_requirement==Tech_H.A_LAST) {
//      SetWindowText(help_ulabel[4][1], "(Never)");
//    } else {
//      SetWindowText(help_ulabel[4][1], advances[utype.tech_requirement].name);
//    }
//    /*    create_tech_tree(help_improvement_tree, 0, utype.tech_requirement, 3);*/
//    if(utype.obsoleted_by==-1) {
//      SetWindowText(help_ulabel[4][4], "None");
//    } else {
//      SetWindowText(help_ulabel[4][4], get_unit_type(utype.obsoleted_by).name);
//    }
//
//    helptext_unit(buf, i, pitem.text);
//    set_help_text(buf);
//  }
//  else {
//    SetWindowText(help_ulabel[0][1], "0");
//    SetWindowText(help_ulabel[0][4], "0");
//    SetWindowText(help_ulabel[1][1], "0");
//    SetWindowText(help_ulabel[1][4], "0");
//    SetWindowText(help_ulabel[2][1], "0");
//    SetWindowText(help_ulabel[2][4], "0");
//    SetWindowText(help_ulabel[3][1], "0");
//    SetWindowText(help_ulabel[3][4], "0");
//
//    SetWindowText(help_ulabel[4][1], "(Never)");
///*    create_tech_tree(help_improvement_tree, 0, Tech_H.A_LAST, 3);*/
//    SetWindowText(help_ulabel[4][4], "None");
//    set_help_text(pitem.text);
//  }
//}
//
///**************************************************************************
//
//**************************************************************************/
//static void help_update_tech(final help_item pitem, char *title, int i)
//{
//  int j;
//  fcwin_box hbox;
//  char *buf= &long_buffer[0];
//
//  create_help_page(HELP_TECH);
//  if (!is_future_tech(i)) {
//    /*    
//	  create_tech_tree(GTK_CTREE(help_tree), i, TECH_TREE_DEPTH,
//	  TECH_TREE_EXPANDED_DEPTH, null);
//    */
//    helptext_tech(buf, i, pitem.text);
//    wordwrap_string(buf, 68);
//    fcwin_box_add_static(helpdlg_page_vbox,buf,0,SS_LEFT,false,false,5);
//
//    for (int j = 0; j < Game.game.num_impr_types; j++) {
//      if(i==Improvement.improvement_types[j].tech_req) {
//	hbox=fcwin_hbox_new(helpdlg_win,false);
//	fcwin_box_add_box(helpdlg_page_vbox,hbox,false,false,5);
//	fcwin_box_add_static(hbox,"Allows ",0,SS_LEFT,false,false,5);
//	fcwin_box_add_button(hbox,Improvement.improvement_types[j].name,
//			     Improvement.is_wonder(j)?
//			     ID_HELP_WONDER_LINK:ID_HELP_IMPROVEMENT_LINK,
//			     0,false,false,5);
//      }
//      if(i==Improvement.improvement_types[j].obsolete_by) {
//	hbox=fcwin_hbox_new(helpdlg_win,false);
//	fcwin_box_add_box(helpdlg_page_vbox,hbox,false,false,5);
//	fcwin_box_add_static(hbox,"Obsoletes ",0,SS_LEFT,false,false,5);
//	fcwin_box_add_button(hbox,Improvement.improvement_types[j].name,
//			     Improvement.is_wonder(j)?
//			     ID_HELP_WONDER_LINK:ID_HELP_IMPROVEMENT_LINK,
//			     0,false,false,5);
//      }
//    } ;
//
//    unit_type_iterate(j) {
//      if(i!=get_unit_type(j).tech_requirement) continue;
//      hbox=fcwin_hbox_new(helpdlg_win,false);
//      fcwin_box_add_box(helpdlg_page_vbox,hbox,false,false,5);
//      fcwin_box_add_static(hbox,"Allows ",0,SS_LEFT,false,false,5);
//      fcwin_box_add_button(hbox,get_unit_type(j).name,
//			   ID_HELP_UNIT_LINK,
//			   0,false,false,5);
//    } unit_type_iterate_end;
//
//    for (j = 0; j < Game.game.num_tech_types; j++) {
//      if(i==advances[j].req[0]) {
//        if(advances[j].req[1]==A_NONE) {
//	  hbox=fcwin_hbox_new(helpdlg_win,false);
//	  fcwin_box_add_box(helpdlg_page_vbox,hbox,false,false,5);
//	  fcwin_box_add_static(hbox,"Allows ",0,SS_LEFT,false,false,5);
//	  fcwin_box_add_button(hbox,advances[j].name,
//			       ID_HELP_TECH_LINK,0,false,false,5);
//	} else {
//	  hbox=fcwin_hbox_new(helpdlg_win,false);
//	  fcwin_box_add_box(helpdlg_page_vbox,hbox,false,false,5);
//	  fcwin_box_add_static(hbox,"Allows ",0,SS_LEFT,false,false,5);
//	  fcwin_box_add_button(hbox,advances[j].name,
//			       ID_HELP_TECH_LINK,0,false,false,5);
//	  fcwin_box_add_static(hbox," (with ",0,SS_LEFT,false,false,5);
//	  fcwin_box_add_button(hbox,advances[advances[j].req[1]].name,
//			       ID_HELP_TECH_LINK,0,false,false,5);
//	  fcwin_box_add_static(hbox,Q"?techhelp:).",
//			       0,SS_LEFT,false,false,5);
//	}
//      }
//      if (i==advances[j].req[1]) {
//	hbox=fcwin_hbox_new(helpdlg_win,false);
//	fcwin_box_add_box(helpdlg_page_vbox,hbox,false,false,5);
//	fcwin_box_add_static(hbox,"Allows ",0,SS_LEFT,false,false,5);
//	fcwin_box_add_button(hbox,advances[j].name,
//			     ID_HELP_TECH_LINK,0,false,false,5);
//	fcwin_box_add_static(hbox," (with ",0,SS_LEFT,false,false,5);
//	fcwin_box_add_button(hbox,advances[advances[j].req[0]].name,
//			     ID_HELP_TECH_LINK,0,false,false,5);
//	fcwin_box_add_static(hbox,Q"?techhelp:).",
//			     0,SS_LEFT,false,false,5);
//      }
//    }
//  }
//}
//
///**************************************************************************
//  This is currently just a text page, with special text:
//**************************************************************************/
//static void help_update_government(final help_item pitem,
//                                   char *title, government gov)
//{
//  char *buf = &long_buffer[0];
// 
//  if (!gov) {
//    strcat(buf, pitem.text);
//  } else {
//    helptext_government(buf, gov-governments, pitem.text);
//  }
//  create_help_page(HELP_TEXT);
//  set_help_text(buf);
//}
//              
///**************************************************************************
//...
//**************************************************************************/
//static void help_update_dialog(final help_item pitem)
//{
//  int i;
//  char *top;
//  /* figure out what kind of item is required for pitem ingo */
//
//  for (top = pitem.topic; *top == ' '; top++) {
//    /* nothing */
//  }
//  SetWindowText(helpdlg_text,"");
//
//  switch(pitem.type) {
//  case HELP_IMPROVEMENT:
//    i = find_improvement_by_name(top);
//    if(i!=Improvement.B_LAST && Improvement.is_wonder(i)) i = Improvement.B_LAST;
//    help_update_improvement(pitem, top, i);
//    break;
//  case HELP_WONDER:
//    i = find_improvement_by_name(top);
//    if(i!=Improvement.B_LAST && !Improvement.is_wonder(i)) i = Improvement.B_LAST;
//    help_update_wonder(pitem, top, i);
//    break;
//  case HELP_UNIT:
//    help_update_unit_type(pitem, top, find_unit_type_by_name(top));
//    break;
//  case HELP_TECH:
//    help_update_tech(pitem, top, find_tech_by_name(top));
//    break;
//  case HELP_TERRAIN:
//    help_update_terrain(pitem, top, get_terrain_by_name(top));
//    break;
//  case HELP_GOVERNMENT:
//    help_update_government(pitem, top, find_government_by_name(top));
//    break;
//  case HELP_TEXT:
//  default:
//    create_help_page(HELP_TEXT);
//    set_help_text(pitem.text);
//    break;
//  }
//  SetWindowText(helpdlg_topic,pitem.topic);
//  fcwin_redo_layout(helpdlg_win);
//  /* set_title_topic(pitem.topic); */
//  ShowWindow(helpdlg_win,SW_SHOWNORMAL);
//}
///**************************************************************************
//...
//**************************************************************************/
//static void select_help_item(int item)
//{
//  
//  ListBox_SetTopIndex(helpdlg_list,item);
//  ListBox_SetCurSel(helpdlg_list,item);
//}
//
///****************************************************************
//...
//*****************************************************************/
//static void select_help_item_string(final String item,
//                                    enum help_page_type htype)
//{
//  final help_item pitem;
//  int idx;
//
//  pitem = get_help_item_spec(item, htype, &idx);
//  if(idx==-1) idx = 0;
//  select_help_item(idx);
//  help_update_dialog(pitem);
//}
//
///**************************************************************************
//
//**************************************************************************/
//void
//popup_help_dialog_string(final String item)
//{
//  popup_help_dialog_typed(_(item), HELP_ANY); 
//}
//
///**************************************************************************
//
//**************************************************************************/
//void
//popup_help_dialog_typed(final String item, enum help_page_type htype)
//{
//  if (!helpdlg_win)
//    create_help_dialog();
//  select_help_item_string(item,htype);
//}
//
///**************************************************************************
//
//**************************************************************************/
//void
//popdown_help_dialog()
//{
//  DestroyWindow(helpdlg_win);
//}
}