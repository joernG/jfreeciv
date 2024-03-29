package client.gui_sdl;

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
///***************************************************************************
//                          menu.c  -  description
//                             -------------------
//    begin                : Wed Sep 04 2002
//    copyright            : (C) 2002 by Rafał Bursig
//    email                : Rafał Bursig <bursig@poczta.fm>
// ***************************************************************************/
//
//#ifdef HAVE_CONFIG_H
//#include <config.h>
//#endif
//
//#include <stdlib.h>
//#include <string.h>
//
//#include <SDL/SDL.h>
//
//#include "fcintl.h"
//#include "map.h"
//
//#include "gui_mem.h"
//#include "support.h"
//#include "unit.h"
//
//#include "civclient.h" /* get_client_state */
//#include "clinet.h" /* aconection */
//#include "control.h"
//#include "gotodlg.h"
//#include "dialogs.h"
//#include "graphics.h"
//#include "gui_string.h"
//#include "gui_stuff.h"		/* gui */
//#include "gui_id.h"
//#include "gui_tilespec.h"
//#include "gui_main.h"
//
//#include "helpdlg.h"
//#include "mapctrl.h"		/* center_on_unit */
//#include "mapview.h"
//
//#include "menu.h"
//
//extern GUI pOptions_Button;
//  
//static GUI pBeginOrderWidgetList;
//static GUI pEndOrderWidgetList;
//  
//static GUI pOrder_Automate_Unit_Button;
//static GUI pOrder_Build_AddTo_City_Button;
//static GUI pOrder_Mine_Button;
//static GUI pOrder_Irrigation_Button;
//static GUI pOrder_Road_Button;
//static GUI pOrder_Transform_Button;
//static GUI pOrder_Trade_Button;
//  
//#define local_show(ID)                                                \
//  clear_wflag(get_widget_pointer_form_ID(pBeginOrderWidgetList, ID ), \
//	      WF_HIDDEN)
//
//#define local_hide(ID)                                             \
//  set_wflag(get_widget_pointer_form_ID(pBeginOrderWidgetList, ID), \
//	    WF_HIDDEN )
//
//
///**************************************************************************
//  ...
//**************************************************************************/
//static int unit_order_callback(GUI pOrder_Widget)
//{
//  unit pUnit = get_unit_in_focus();
//
//  set_wstate(pOrder_Widget, FC_WS_SELLECTED);
//  pSellected_Widget = pOrder_Widget;
//
//  if (!pUnit) {
//    return -1;
//  }
//
//  switch (pOrder_Widget.ID) {
//  case ID_UNIT_ORDER_BUILD_CITY:
//    /* Enable the button for adding to a city in all cases, so we
//       get an eventual error message from the server if we try. */
//    key_unit_build_city();
//    break;
//  case ID_UNIT_ORDER_BUILD_WONDER:
//    key_unit_build_wonder();
//    break;
//  case ID_UNIT_ORDER_ROAD:
//    key_unit_road();
//    break;
//  case ID_UNIT_ORDER_TRADEROUTE:
//    key_unit_traderoute();
//    break;
//  case ID_UNIT_ORDER_IRRIGATE:
//    key_unit_irrigate();
//    break;
//  case ID_UNIT_ORDER_MINE:
//    key_unit_mine();
//    break;
//  case ID_UNIT_ORDER_TRANSFORM:
//    key_unit_transform();
//    break;
//  case ID_UNIT_ORDER_FORTRESS:
//    key_unit_fortress();
//    break;
//  case ID_UNIT_ORDER_FORTIFY:
//    key_unit_fortify();
//    break;
//  case ID_UNIT_ORDER_AIRBASE:
//    key_unit_airbase();
//    break;
//  case ID_UNIT_ORDER_POLLUTION:
//    key_unit_pollution();
//    break;
//  case ID_UNIT_ORDER_PARADROP:
//    key_unit_paradrop();
//    break;
//  case ID_UNIT_ORDER_FALLOUT:
//    key_unit_fallout();
//    break;
//  case ID_UNIT_ORDER_SENTRY:
//    key_unit_sentry();
//    break;
//  case ID_UNIT_ORDER_PILLAGE:
//    key_unit_pillage();
//    break;
//  case ID_UNIT_ORDER_HOMECITY:
//    key_unit_homecity();
//    break;
//  case ID_UNIT_ORDER_UNLOAD:
//    key_unit_unload_all();
//    break;
//  case ID_UNIT_ORDER_WAKEUP_OTHERS:
//    key_unit_wakeup_others();
//    break;
//  case ID_UNIT_ORDER_AUTOMATION:
//    request_unit_auto(pUnit);
//    break;
//  case ID_UNIT_ORDER_AUTO_EXPLORE:
//    key_unit_auto_explore();
//    break;
//  case ID_UNIT_ORDER_CONNECT:
//    key_unit_connect();
//    break;
//  case ID_UNIT_ORDER_PATROL:
//    key_unit_patrol();
//    break;
//  case ID_UNIT_ORDER_GOTO:
//    key_unit_goto();
//    break;
//  case ID_UNIT_ORDER_GOTO_CITY:
//    popup_goto_dialog();
//    break;
//  case ID_UNIT_ORDER_AIRLIFT:
//    popup_airlift_dialog();
//    break;
//  case ID_UNIT_ORDER_RETURN:
//    request_unit_return(pUnit);
//    break;
//  case ID_UNIT_ORDER_UPGRADE:
//    popup_unit_upgrade_dlg(pUnit, false);
//    break;
//  case ID_UNIT_ORDER_DISBAND:
//    key_unit_disband();
//    break;
//  case ID_UNIT_ORDER_DIPLOMAT_DLG:
//    key_unit_diplomat_actions();
//    break;
//  case ID_UNIT_ORDER_NUKE:
//    key_unit_nuke();
//    break;
//  case ID_UNIT_ORDER_WAIT:
//    key_unit_wait();
//    flush_dirty();
//    break;
//  case ID_UNIT_ORDER_DONE:
//    key_unit_done();
//    flush_dirty();
//    break;
//
//  default:
//    break;
//  }
//
//  return -1;
//}
//
///**************************************************************************
//  ...
//**************************************************************************/
//static boolean has_city_airport(city pCity)
//{
//  return (pCity && City.city_got_building(pCity, B_AIRPORT));
//}
//
//static Uint16 redraw_order_widgets()
//{
//  Uint16 count = 0;
//  GUI pTmpWidget = pBeginOrderWidgetList;
//
//  while (true) {
//
//    if (!(get_wflags(pTmpWidget) & WF_HIDDEN)) {
//
//      refresh_widget_background(pTmpWidget);
//      real_redraw_icon(pTmpWidget);
//      sdl_dirty_rect(pTmpWidget.size);
//      count++;
//    }
//
//    if (pTmpWidget == pEndOrderWidgetList) {
//      break;
//    }
//
//    pTmpWidget = pTmpWidget.next;
//  }
//
//  return count;
//}
//
//
//void set_new_order_widgets_dest_buffers()
//{
//  GUI pTmpWidget = pBeginOrderWidgetList;
//  if(!pEndOrderWidgetList) return;
//  while (pTmpWidget) {
//    pTmpWidget.dst = Main.gui;
//    if (pTmpWidget == pEndOrderWidgetList) {
//      break;
//    }
//    pTmpWidget = pTmpWidget.next;
//  }
//}
//
///**************************************************************************
//  ...
//**************************************************************************/
//static void set_new_order_widget_start_pos()
//{
//  GUI pMiniMap = get_minimap_window_widget();
//  GUI pInfoWind = get_unit_info_window_widget();
//  GUI pTmpWidget = pBeginOrderWidgetList;
//  Sint16 sx, sy, xx, yy = 0;
//  int count = 0, lines = 1, w = 0, count_on_line;
//
//  if (SDL_Client_Flags & CF_MINI_MAP_SHOW) {
//    xx = pMiniMap.size.x + pMiniMap.size.w + 10;
//  } else {
//    xx = pMiniMap.size.x + HIDDEN_MINI_MAP_W + 10;
//  }
//
//  w = (pInfoWind.size.x - 10) - xx;
//  
//  if (w < (pTmpWidget.size.w + 10) * 2) {
//    if(pMiniMap.size.h == pInfoWind.size.h) {
//      xx = 0;
//      w = Main.gui.w;
//      yy = pInfoWind.size.h;
//    } else {
//      if (pMiniMap.size.h > pInfoWind.size.h) {
//        w = Main.gui.w - xx - 20;
//        if (w < (pTmpWidget.size.w + 10) * 2) {
//	  xx = 0;
//	  w = pMiniMap.size.w;
//	  yy = pMiniMap.size.h;
//        } else {
//          yy = pInfoWind.size.h;
//        }
//      } else {
//	w = pInfoWind.size.x - 20;
//        if (w < (pTmpWidget.size.w + 10) * 2) {
//	  xx = pInfoWind.size.x;
//	  w = pInfoWind.size.w;
//	  yy = pInfoWind.size.h;
//        } else {
//	  xx = 10;
//          yy = pMiniMap.size.h;
//        }
//      }
//    }
//  }
//    
//  count_on_line = w / (pTmpWidget.size.w + 5);
//
//  /* find how many to reposition */
//  while (true) {
//
//    if (!(get_wflags(pTmpWidget) & WF_HIDDEN)) {
//      count++;
//    }
//
//    if (pTmpWidget == pEndOrderWidgetList) {
//      break;
//    }
//
//    pTmpWidget = pTmpWidget.next;
//
//  }
//
//  pTmpWidget = pBeginOrderWidgetList;
//
//  if (count - count_on_line > 0) {
//
//    lines = (count + (count_on_line - 1)) / count_on_line;
//  
//    count = count_on_line - ((count_on_line * lines) - count);
//
//  }
//
//  sx = xx + (w - count * (pTmpWidget.size.w + 5)) / 2;
//
//  sy = pTmpWidget.dst.h - yy - lines * (pTmpWidget.size.h + 5);
//
//  while (true) {
//
//    if (!(get_wflags(pTmpWidget) & WF_HIDDEN)) {
//
//      pTmpWidget.size.x = sx;
//      pTmpWidget.size.y = sy;
//
//      count--;
//      sx += (pTmpWidget.size.w + 5);
//      if (!count) {
//	count = count_on_line;
//	lines--;
//
//	sx = xx + (w - count * (pTmpWidget.size.w + 5)) / 2;
//
//	sy = pTmpWidget.dst.h - yy - lines * (pTmpWidget.size.h + 5);
//      }
//
//    }
//
//    if (pTmpWidget == pEndOrderWidgetList) {
//      break;
//    }
//
//    pTmpWidget = pTmpWidget.next;
//
//  }
//}
//
///* ================================ PUBLIC ================================ */
//
///**************************************************************************
//  ...
//**************************************************************************/
//void create_units_order_widgets()
//{
//  GUI pBuf = null;
//  char cBuf[128];
//  Uint16 *unibuf;  
//  size_t len;
//  
//  cBuf = util.my_snprintf("%s%s", "No orders", " (Space)");
//  pBuf = create_themeicon(pTheme.ODone_Icon, Main.gui,
//			  (WF_HIDDEN | WF_DRAW_THEME_TRANSPARENT |
//			   WF_WIDGET_HAS_INFO_LABEL));
//  set_wstate(pBuf, FC_WS_NORMAL);
//  pBuf.action = unit_order_callback;
//  pBuf.string16 = create_str16_from_char(cBuf, 10);
//  pBuf.key = SDLK_SPACE;
//  add_to_gui_list(ID_UNIT_ORDER_DONE, pBuf);
//  pEndOrderWidgetList = pBuf;
//
//  cBuf = util.my_snprintf("%s%s", "Wait", " (W)");
//  pBuf = create_themeicon(pTheme.OWait_Icon, Main.gui,
//			  (WF_HIDDEN | WF_DRAW_THEME_TRANSPARENT |
//			   WF_WIDGET_HAS_INFO_LABEL));
//  set_wstate(pBuf, FC_WS_NORMAL);
//  pBuf.action = unit_order_callback;
//  pBuf.string16 = create_str16_from_char(cBuf, 10);
//  pBuf.key = SDLK_w;
//  add_to_gui_list(ID_UNIT_ORDER_WAIT, pBuf);
//
//  cBuf = util.my_snprintf("%s%s", "Explode Nuclear", " (Shift + N)");
//  pBuf = create_themeicon(pTheme.ONuke_Icon, Main.gui,
//			  (WF_HIDDEN | WF_DRAW_THEME_TRANSPARENT |
//			   WF_WIDGET_HAS_INFO_LABEL));
//  set_wstate(pBuf, FC_WS_NORMAL);
//  pBuf.action = unit_order_callback;
//  pBuf.string16 = create_str16_from_char(cBuf, 10);
//  pBuf.key = SDLK_n;
//  pBuf.mod = KMOD_SHIFT;
//  add_to_gui_list(ID_UNIT_ORDER_NUKE, pBuf);
//
//  cBuf = util.my_snprintf("%s%s", "Diplomat|Spy Actions", " (D)");
//  pBuf = create_themeicon(pTheme.OSpy_Icon, Main.gui,
//			  (WF_HIDDEN | WF_DRAW_THEME_TRANSPARENT |
//			   WF_WIDGET_HAS_INFO_LABEL));
//  set_wstate(pBuf, FC_WS_NORMAL);
//  pBuf.action = unit_order_callback;
//  pBuf.string16 = create_str16_from_char(cBuf, 10);
//  pBuf.key = SDLK_d;
//  add_to_gui_list(ID_UNIT_ORDER_DIPLOMAT_DLG, pBuf);
//
//  cBuf = util.my_snprintf("%s%s", "Disband", " (Shift + D)");
//  pBuf = create_themeicon(pTheme.ODisband_Icon, Main.gui,
//			  (WF_HIDDEN | WF_DRAW_THEME_TRANSPARENT |
//			   WF_WIDGET_HAS_INFO_LABEL));
//  set_wstate(pBuf, FC_WS_NORMAL);
//  pBuf.action = unit_order_callback;
//  pBuf.string16 = create_str16_from_char(cBuf, 10);
//  pBuf.key = SDLK_d;
//  pBuf.mod = KMOD_SHIFT;
//  add_to_gui_list(ID_UNIT_ORDER_DISBAND, pBuf);
//
//  cBuf = util.my_snprintf("%s%s", "Upgrade", " (Shift + U)");
//  pBuf = create_themeicon(pTheme.Order_Icon, Main.gui,
//			  (WF_HIDDEN | WF_DRAW_THEME_TRANSPARENT |
//			   WF_WIDGET_HAS_INFO_LABEL));
//  set_wstate(pBuf, FC_WS_NORMAL);
//  pBuf.action = unit_order_callback;
//  pBuf.string16 = create_str16_from_char(cBuf, 10);
//  pBuf.key = SDLK_u;
//  pBuf.mod = KMOD_SHIFT;
//  add_to_gui_list(ID_UNIT_ORDER_UPGRADE, pBuf);
//
//  cBuf = util.my_snprintf("%s%s", "Return to nearest city", " (Shift + G)");
//  pBuf = create_themeicon(pTheme.OReturn_Icon, Main.gui,
//			  (WF_HIDDEN | WF_DRAW_THEME_TRANSPARENT |
//			   WF_WIDGET_HAS_INFO_LABEL));
//  set_wstate(pBuf, FC_WS_NORMAL);
//  pBuf.action = unit_order_callback;
//  pBuf.string16 = create_str16_from_char(cBuf, 10);
//  pBuf.key = SDLK_g;
//  pBuf.mod = KMOD_SHIFT;
//  add_to_gui_list(ID_UNIT_ORDER_RETURN, pBuf);
//  
//  cBuf = util.my_snprintf("%s%s", "Goto City", " (L)");
//  pBuf = create_themeicon(pTheme.OGotoCity_Icon, Main.gui,
//			  (WF_HIDDEN | WF_DRAW_THEME_TRANSPARENT |
//			   WF_WIDGET_HAS_INFO_LABEL));
//  set_wstate(pBuf, FC_WS_NORMAL);
//  pBuf.action = unit_order_callback;
//  pBuf.string16 = create_str16_from_char(cBuf, 10);
//  pBuf.key = SDLK_l;
//  add_to_gui_list(ID_UNIT_ORDER_GOTO_CITY, pBuf);
//
//  cBuf = util.my_snprintf("%s%s", "Airlift", " (L)");
//  pBuf = create_themeicon(pTheme.Order_Icon, Main.gui,
//			  (WF_HIDDEN | WF_DRAW_THEME_TRANSPARENT |
//			   WF_WIDGET_HAS_INFO_LABEL));
//  set_wstate(pBuf, FC_WS_NORMAL);
//  pBuf.action = unit_order_callback;
//  pBuf.string16 = create_str16_from_char(cBuf, 10);
//  pBuf.key = SDLK_l;
//  add_to_gui_list(ID_UNIT_ORDER_AIRLIFT, pBuf);
//  
//  cBuf = util.my_snprintf("%s%s", "Goto location", " (G)");
//  pBuf = create_themeicon(pTheme.OGoto_Icon, Main.gui,
//			  (WF_HIDDEN | WF_DRAW_THEME_TRANSPARENT |
//			   WF_WIDGET_HAS_INFO_LABEL));
//  set_wstate(pBuf, FC_WS_NORMAL);
//  pBuf.action = unit_order_callback;
//  pBuf.string16 = create_str16_from_char(cBuf, 10);
//  pBuf.key = SDLK_g;
//  add_to_gui_list(ID_UNIT_ORDER_GOTO, pBuf);
//
//  cBuf = util.my_snprintf("%s%s", "Patrol", " (Q)");
//  pBuf = create_themeicon(pTheme.OPatrol_Icon, Main.gui,
//			  (WF_HIDDEN | WF_DRAW_THEME_TRANSPARENT |
//			   WF_WIDGET_HAS_INFO_LABEL));
//  set_wstate(pBuf, FC_WS_NORMAL);
//  pBuf.action = unit_order_callback;
//  pBuf.string16 = create_str16_from_char(cBuf, 10);
//  pBuf.key = SDLK_q;
//  add_to_gui_list(ID_UNIT_ORDER_PATROL, pBuf);
//
//  cBuf = util.my_snprintf("%s%s", "Connect", " (Shift + C)");
//  pBuf = create_themeicon(pTheme.OAutoConnect_Icon, Main.gui,
//			  (WF_HIDDEN | WF_DRAW_THEME_TRANSPARENT |
//			   WF_WIDGET_HAS_INFO_LABEL));
//  set_wstate(pBuf, FC_WS_NORMAL);
//  pBuf.action = unit_order_callback;
//  pBuf.string16 = create_str16_from_char(cBuf, 10);
//  pBuf.key = SDLK_c;
//  pBuf.mod = KMOD_SHIFT;
//  add_to_gui_list(ID_UNIT_ORDER_CONNECT, pBuf);
//
//  cBuf = util.my_snprintf("%s%s", "Auto-Explore", " (X)");
//  pBuf = create_themeicon(pTheme.OAutoExp_Icon, Main.gui,
//			  (WF_HIDDEN | WF_DRAW_THEME_TRANSPARENT |
//			   WF_WIDGET_HAS_INFO_LABEL));
//  set_wstate(pBuf, FC_WS_NORMAL);
//  pBuf.action = unit_order_callback;
//  pBuf.string16 =
//      create_str16_from_char(cBuf, 10);
//  pBuf.key = SDLK_x;
//  add_to_gui_list(ID_UNIT_ORDER_AUTO_EXPLORE, pBuf);
//
//  cBuf = util.my_snprintf("%s%s", "Auto-Attack", " (A)");
//  len = cBuf.length();
//  cBuf = util.my_snprintf("%s%s", "Auto-Settler", " (A)");
//  len = MAX(len, cBuf.length());
//  
//  pBuf = create_themeicon(pTheme.OAutoSett_Icon, Main.gui,
//			  (WF_HIDDEN | WF_DRAW_THEME_TRANSPARENT |
//			   WF_WIDGET_HAS_INFO_LABEL));
//  set_wstate(pBuf, FC_WS_NORMAL);
//  pBuf.action = unit_order_callback;
//  len = (len + 1) * sizeof(Uint16);
//  unibuf = MALLOC(len);
//  convertcopy_to_utf16(unibuf, len, cBuf);
//  pBuf.string16 = create_string16(unibuf, len, 10);
//  pBuf.key = SDLK_a;
//  add_to_gui_list(ID_UNIT_ORDER_AUTOMATION, pBuf);
//  pOrder_Automate_Unit_Button = pBuf;
//  
//  cBuf = util.my_snprintf("%s%s", "Wake Up Others", " (Shift + W)");
//  pBuf = create_themeicon(pTheme.OWakeUp_Icon, Main.gui,
//			  (WF_HIDDEN | WF_DRAW_THEME_TRANSPARENT |
//			   WF_WIDGET_HAS_INFO_LABEL));
//  set_wstate(pBuf, FC_WS_NORMAL);
//  pBuf.action = unit_order_callback;
//  pBuf.string16 = create_str16_from_char(cBuf, 10);
//  pBuf.key = SDLK_w;
//  pBuf.mod = KMOD_SHIFT;
//  add_to_gui_list(ID_UNIT_ORDER_WAKEUP_OTHERS, pBuf);
//
//  cBuf = util.my_snprintf("%s%s", "Unload", " (U)");
//  pBuf = create_themeicon(pTheme.OUnload_Icon, Main.gui,
//			  (WF_HIDDEN | WF_DRAW_THEME_TRANSPARENT |
//			   WF_WIDGET_HAS_INFO_LABEL));
//  set_wstate(pBuf, FC_WS_NORMAL);
//  pBuf.action = unit_order_callback;
//  pBuf.string16 = create_str16_from_char(cBuf, 10);
//  pBuf.key = SDLK_u;
//  add_to_gui_list(ID_UNIT_ORDER_UNLOAD, pBuf);
//
//  cBuf = util.my_snprintf("%s%s", "Find Homecity", " (H)");
//  pBuf = create_themeicon(pTheme.OHomeCity_Icon, Main.gui,
//			  (WF_HIDDEN | WF_DRAW_THEME_TRANSPARENT |
//			   WF_WIDGET_HAS_INFO_LABEL));
//  set_wstate(pBuf, FC_WS_NORMAL);
//  pBuf.action = unit_order_callback;
//  pBuf.string16 = create_str16_from_char(cBuf, 10);
//  pBuf.key = SDLK_h;
//  add_to_gui_list(ID_UNIT_ORDER_HOMECITY, pBuf);
//
//  cBuf = util.my_snprintf("%s%s", "Pillage", " (Shift + P)");
//  pBuf = create_themeicon(pTheme.OPillage_Icon, Main.gui,
//			  (WF_HIDDEN | WF_DRAW_THEME_TRANSPARENT |
//			   WF_WIDGET_HAS_INFO_LABEL));
//  set_wstate(pBuf, FC_WS_NORMAL);
//  pBuf.action = unit_order_callback;
//  pBuf.string16 = create_str16_from_char(cBuf, 10);
//  pBuf.key = SDLK_p;
//  pBuf.mod = KMOD_SHIFT;
//  add_to_gui_list(ID_UNIT_ORDER_PILLAGE, pBuf);
//
//  cBuf = util.my_snprintf("%s%s", "Sentry", " (S)");
//  pBuf = create_themeicon(pTheme.OSentry_Icon, Main.gui,
//			  (WF_HIDDEN | WF_DRAW_THEME_TRANSPARENT |
//			   WF_WIDGET_HAS_INFO_LABEL));
//  set_wstate(pBuf, FC_WS_NORMAL);
//  pBuf.action = unit_order_callback;
//  pBuf.string16 = create_str16_from_char(cBuf, 10);
//  pBuf.key = SDLK_s;
//  add_to_gui_list(ID_UNIT_ORDER_SENTRY, pBuf);
//
//  cBuf = util.my_snprintf("%s%s", "Clean Nuclear Fallout", " (N)");
//  pBuf = create_themeicon(pTheme.OFallout_Icon, Main.gui,
//			  (WF_HIDDEN | WF_DRAW_THEME_TRANSPARENT |
//			   WF_WIDGET_HAS_INFO_LABEL));
//  set_wstate(pBuf, FC_WS_NORMAL);
//  pBuf.action = unit_order_callback;
//  pBuf.string16 = create_str16_from_char(cBuf, 10);
//  pBuf.key = SDLK_n;
//  add_to_gui_list(ID_UNIT_ORDER_FALLOUT, pBuf);
//
//  cBuf = util.my_snprintf("%s%s", "Paradrop", " (P)");
//  pBuf = create_themeicon(pTheme.OParaDrop_Icon, Main.gui,
//			  (WF_HIDDEN | WF_DRAW_THEME_TRANSPARENT |
//			   WF_WIDGET_HAS_INFO_LABEL));
//  set_wstate(pBuf, FC_WS_NORMAL);
//  pBuf.action = unit_order_callback;
//  pBuf.string16 =  create_str16_from_char(cBuf, 10);
//  pBuf.key = SDLK_p;
//  add_to_gui_list(ID_UNIT_ORDER_PARADROP, pBuf);
//
//  cBuf = util.my_snprintf("%s%s", "Clean Pollution", " (P)");
//  pBuf = create_themeicon(pTheme.OPollution_Icon, Main.gui,
//			  (WF_HIDDEN | WF_DRAW_THEME_TRANSPARENT |
//			   WF_WIDGET_HAS_INFO_LABEL));
//  set_wstate(pBuf, FC_WS_NORMAL);
//  pBuf.action = unit_order_callback;
//  pBuf.string16 = create_str16_from_char(cBuf, 10);
//  pBuf.key = SDLK_p;
//  add_to_gui_list(ID_UNIT_ORDER_POLLUTION, pBuf);
//
//  cBuf = util.my_snprintf("%s%s", "Build Airbase", " (E)");
//  pBuf = create_themeicon(pTheme.OAirBase_Icon, Main.gui,
//			  (WF_HIDDEN | WF_DRAW_THEME_TRANSPARENT |
//			   WF_WIDGET_HAS_INFO_LABEL));
//  set_wstate(pBuf, FC_WS_NORMAL);
//  pBuf.action = unit_order_callback;
//  pBuf.string16 = create_str16_from_char(cBuf, 10);
//  pBuf.key = SDLK_e;
//  add_to_gui_list(ID_UNIT_ORDER_AIRBASE, pBuf);
//
//  cBuf = util.my_snprintf("%s%s", "Fortify", " (F)");
//  pBuf = create_themeicon(pTheme.OFortify_Icon, Main.gui,
//			  (WF_HIDDEN | WF_DRAW_THEME_TRANSPARENT |
//			   WF_WIDGET_HAS_INFO_LABEL));
//  set_wstate(pBuf, FC_WS_NORMAL);
//  pBuf.action = unit_order_callback;
//  pBuf.string16 = create_str16_from_char(cBuf, 10);
//  pBuf.key = SDLK_f;
//  add_to_gui_list(ID_UNIT_ORDER_FORTIFY, pBuf);
//
//  cBuf = util.my_snprintf("%s%s", "Build Fortress", " (F)");
//  pBuf = create_themeicon(pTheme.OFortress_Icon, Main.gui,
//			  (WF_HIDDEN | WF_DRAW_THEME_TRANSPARENT |
//			   WF_WIDGET_HAS_INFO_LABEL));
//  set_wstate(pBuf, FC_WS_NORMAL);
//  pBuf.action = unit_order_callback;
//  pBuf.string16 = create_str16_from_char(cBuf, 10);
//  pBuf.key = SDLK_f;
//  add_to_gui_list(ID_UNIT_ORDER_FORTRESS, pBuf);
//
//  cBuf = util.my_snprintf("%s%s", "Transform Tile", " (O)");
//  pBuf = create_themeicon(pTheme.OTransform_Icon, Main.gui,
//			  (WF_HIDDEN | WF_DRAW_THEME_TRANSPARENT |
//			   WF_WIDGET_HAS_INFO_LABEL));
//  set_wstate(pBuf, FC_WS_NORMAL);
//  pBuf.action = unit_order_callback;
//  pBuf.string16 = create_str16_from_char(cBuf, 10);
//  pBuf.key = SDLK_o;
//  pOrder_Transform_Button = pBuf;
//  add_to_gui_list(ID_UNIT_ORDER_TRANSFORM, pBuf);
//
//  cBuf = util.my_snprintf("%s%s", "Build Mine", " (M)");
//  pBuf = create_themeicon(pTheme.OMine_Icon, Main.gui,
//			  (WF_HIDDEN | WF_DRAW_THEME_TRANSPARENT |
//			   WF_WIDGET_HAS_INFO_LABEL));
//  set_wstate(pBuf, FC_WS_NORMAL);
//  pBuf.action = unit_order_callback;
//  pBuf.string16 = create_str16_from_char(cBuf, 10);
//  pBuf.key = SDLK_m;
//  add_to_gui_list(ID_UNIT_ORDER_MINE, pBuf);
//  pOrder_Mine_Button = pBuf;
//
//  cBuf = util.my_snprintf("%s%s", "Build Irrigation", " (I)");
//  pBuf = create_themeicon(pTheme.OIrrigation_Icon, Main.gui,
//			  (WF_HIDDEN | WF_DRAW_THEME_TRANSPARENT |
//			   WF_WIDGET_HAS_INFO_LABEL));
//  set_wstate(pBuf, FC_WS_NORMAL);
//  pBuf.action = unit_order_callback;
//  pBuf.key = SDLK_i;
//  add_to_gui_list(ID_UNIT_ORDER_IRRIGATE, pBuf);
//  pBuf.string16 = create_str16_from_char(cBuf, 10);
//  pOrder_Irrigation_Button = pBuf;
//
//  cBuf = util.my_snprintf("%s%s", "Form Traderoute", " (R)");
//  pBuf = create_themeicon(pTheme.OTrade_Icon, Main.gui,
//			  (WF_HIDDEN | WF_DRAW_THEME_TRANSPARENT |
//			   WF_WIDGET_HAS_INFO_LABEL));
//  set_wstate(pBuf, FC_WS_NORMAL);
//  pBuf.action = unit_order_callback;
//  pBuf.string16 = create_str16_from_char(cBuf, 10);
//  pBuf.key = SDLK_r;
//  pOrder_Trade_Button = pBuf;
//  add_to_gui_list(ID_UNIT_ORDER_TRADEROUTE, pBuf);
//
//  cBuf = util.my_snprintf("%s%s %d %s",
//			"Build Railroad", " (R)", 999, 
//			PL("turn", "turns", 999));
//  len = cBuf.length();
//  cBuf = util.my_snprintf("%s%s %d %s",
//			"Build Road", " (R)", 999, 
//			PL("turn", "turns", 999));
//  len = MAX(len, cBuf.length());
//  
//  pBuf = create_themeicon(pTheme.ORoad_Icon, Main.gui,
//			  (WF_HIDDEN | WF_DRAW_THEME_TRANSPARENT |
//			   WF_WIDGET_HAS_INFO_LABEL));
//  set_wstate(pBuf, FC_WS_NORMAL);
//  pBuf.action = unit_order_callback;
//  len = (len + 1) * sizeof(Uint16);
//  unibuf = MALLOC(len);
//  convertcopy_to_utf16(unibuf, len, cBuf);
//  pBuf.string16 = create_string16(unibuf, len, 10);
//  pBuf.key = SDLK_r;
//  pOrder_Road_Button = pBuf;
//  add_to_gui_list(ID_UNIT_ORDER_ROAD, pBuf);
//
//  cBuf = util.my_snprintf("%s%s", "Help Build Wonder", " (B)");
//  pBuf = create_themeicon(pTheme.OWonder_Icon, Main.gui,
//			  (WF_HIDDEN | WF_DRAW_THEME_TRANSPARENT |
//			   WF_WIDGET_HAS_INFO_LABEL));
//  set_wstate(pBuf, FC_WS_NORMAL);
//  pBuf.action = unit_order_callback;
//  pBuf.string16 = create_str16_from_char(cBuf, 10);
//  pBuf.key = SDLK_b;
//  add_to_gui_list(ID_UNIT_ORDER_BUILD_WONDER, pBuf);
//
//  cBuf = util.my_snprintf("%s%s", "Add to City", " (B)");
//  len = cBuf.length();
//  cBuf = util.my_snprintf("%s%s", "Build New City", " (B)");
//  len = MAX(len, cBuf.length());
//      
//  pBuf = create_themeicon(pTheme.OBuildCity_Icon, Main.gui,
//			  (WF_HIDDEN | WF_DRAW_THEME_TRANSPARENT |
//			   WF_WIDGET_HAS_INFO_LABEL));
//  set_wstate(pBuf, FC_WS_NORMAL);
//  pBuf.action = unit_order_callback;
//  len = (len + 1) * sizeof(Uint16);
//  unibuf = MALLOC(len);
//  convertcopy_to_utf16(unibuf, len, cBuf);
//  pBuf.string16 = create_string16(unibuf, len, 10);
//  pBuf.key = SDLK_b;
//  add_to_gui_list(ID_UNIT_ORDER_BUILD_CITY, pBuf);
//  pOrder_Build_AddTo_City_Button = pBuf;
//  pBeginOrderWidgetList = pBuf;
//
//
//  SDL_Client_Flags |= CF_ORDERS_WIDGETS_CREATED;
//}
//
///**************************************************************************
//  ...
//**************************************************************************/
//void update_order_widget()
//{
//  set_new_order_widget_start_pos();
//  redraw_order_widgets();
//}
//
///**************************************************************************
//  ...
//**************************************************************************/
//void undraw_order_widgets()
//{
//  GUI pTmpWidget = pBeginOrderWidgetList;
//  SDL_Rect dest;
//
//  while (true) {
//
//    if (!(get_wflags(pTmpWidget) & WF_HIDDEN) && (pTmpWidget.gfx)) {
//
//      dest.x = pTmpWidget.size.x;
//      dest.y = pTmpWidget.size.y;
//
//      SDL_BlitSurface(pTmpWidget.gfx, null, pTmpWidget.dst, &dest);
//      
//      sdl_dirty_rect(pTmpWidget.size);
//
//    }
//
//    if (pTmpWidget == pEndOrderWidgetList) {
//      break;
//    }
//
//    pTmpWidget = pTmpWidget.next;
//  }
//}
//
///**************************************************************************
//  ...
//**************************************************************************/
//void free_bcgd_order_widgets()
//{
//  GUI pTmpWidget = pBeginOrderWidgetList;
//
//  while (true) {
//
//    FREESURFACE(pTmpWidget.gfx);
//
//    if (pTmpWidget == pEndOrderWidgetList) {
//      break;
//    }
//
//    pTmpWidget = pTmpWidget.next;
//  }
//}
//
//
///* ============================== Native =============================== */
//
///**************************************************************************
//  Update all of the menus (sensitivity, etc.) based on the current state.
//**************************************************************************/
//void update_menus()
//{
//  static Uint16 counter = 0;
//  unit pUnit = null;
//  static char cBuf[128];
//  
//  if (get_client_state() != CLIENT_GAME_RUNNING_STATE) {
//
//    SDL_Client_Flags |= CF_GANE_JUST_STARTED;
//	
//    set_wflag(pOptions_Button, WF_HIDDEN);
//    if (SDL_Client_Flags & CF_MAP_UNIT_W_CREATED) {
//      GUI pWidget = get_unit_info_window_widget();
//	
//      /* economy button */
//      pWidget = pWidget.prev;
//      set_wflag(pWidget, WF_HIDDEN);
//      
//      /* research button */
//      pWidget = pWidget.prev;
//      set_wflag(pWidget, WF_HIDDEN);
//            
//      /* revolution button */
//      pWidget = pWidget.prev;
//      set_wflag(pWidget, WF_HIDDEN);
//      
//      /* show/hide unit's window button */
//      pWidget = pWidget.prev;
//      set_wflag(pWidget, WF_HIDDEN);
//      
//      /* ------------------------------------ */
//      /* mini map window */
//      pWidget = pWidget.prev;
//      
//      /* new turn button */
//      pWidget = pWidget.prev;
//      set_wflag(pWidget, WF_HIDDEN);
//      
//      /* players button */
//      pWidget = pWidget.prev;
//      set_wflag(pWidget, WF_HIDDEN);
//      
//      /* find city button */
//      pWidget = pWidget.prev;
//      set_wflag(pWidget, WF_HIDDEN);
//      
//      /* units button */
//      pWidget = pWidget.prev;
//      set_wflag(pWidget, WF_HIDDEN);
//      
//      /* show/hide log window button */
//      pWidget = pWidget.prev;
//      set_wflag(pWidget, WF_HIDDEN);
//      
//      /* toggle minimap mode button */
//      pWidget = pWidget.prev;
//      set_wflag(pWidget, WF_HIDDEN);
//      
//      /* show/hide minimap button */
//      pWidget = pWidget.prev;
//      set_wflag(pWidget, WF_HIDDEN);
//      
//    }
//
//    if (SDL_Client_Flags & CF_ORDERS_WIDGETS_CREATED) {
//      hide_group(pBeginOrderWidgetList, pEndOrderWidgetList);
//    }
//
//  } else {
//    if (get_wstate(pEndOrderWidgetList) == FC_WS_DISABLED) {
//      enable_group(pBeginOrderWidgetList, pEndOrderWidgetList);
//    }
//    
//    if (counter) {
//      undraw_order_widgets();
//    }
//
//    if (SDL_Client_Flags & CF_GANE_JUST_STARTED) {
//      GUI pWidget = get_unit_info_window_widget();
//	
//      SDL_Client_Flags &= ~CF_GANE_JUST_STARTED;
//
//      clear_wflag(pOptions_Button, WF_HIDDEN);
//      real_redraw_icon(pOptions_Button);
//      sdl_dirty_rect(pOptions_Button.size);
//      
//      /* economy button */
//      pWidget = pWidget.prev;
//      clear_wflag(pWidget, WF_HIDDEN);
//      
//      /* research button */
//      pWidget = pWidget.prev;
//      clear_wflag(pWidget, WF_HIDDEN);
//            
//      /* revolution button */
//      pWidget = pWidget.prev;
//      clear_wflag(pWidget, WF_HIDDEN);
//      
//      /* show/hide unit's window button */
//      pWidget = pWidget.prev;
//      clear_wflag(pWidget, WF_HIDDEN);
//      
//      /* ------------------------------------ */
//      /* mini map window */
//      pWidget = pWidget.prev;
//      
//      /* new turn button */
//      pWidget = pWidget.prev;
//      clear_wflag(pWidget, WF_HIDDEN);
//      
//      /* players button */
//      pWidget = pWidget.prev;
//      clear_wflag(pWidget, WF_HIDDEN);
//      
//      /* find city button */
//      pWidget = pWidget.prev;
//      clear_wflag(pWidget, WF_HIDDEN);
//      
//      /* units button */
//      pWidget = pWidget.prev;
//      if (pWidget.size.y < pWidget.dst.h - pWidget.size.h * 2) {
//        clear_wflag(pWidget, WF_HIDDEN);
//      }
//      
//      /* show/hide log window button */
//      pWidget = pWidget.prev;
//      if (pWidget.size.y < pWidget.dst.h - pWidget.size.h * 2) {
//        clear_wflag(pWidget, WF_HIDDEN);
//      }
//      
//      /* toggle minimap mode button */
//      pWidget = pWidget.prev;
//      if (pWidget.size.y < pWidget.dst.h - pWidget.size.h * 2) {
//        clear_wflag(pWidget, WF_HIDDEN);
//      }
//      
//      /* show/hide minimap button */
//      pWidget = pWidget.prev;
//      clear_wflag(pWidget, WF_HIDDEN);
//      
//      counter = 0;
//    }
//
//    pUnit = get_unit_in_focus();
//
//    if (pUnit && !pUnit.ai.control) {
//      city pHomecity;
//      int time;
//      tile pTile = map_get_tile(pUnit.x, pUnit.y);
//      int terrain = pTile.terrain;
//      
//      if (!counter) {
//	local_show(ID_UNIT_ORDER_GOTO);
//	local_show(ID_UNIT_ORDER_DISBAND);
//
//	local_show(ID_UNIT_ORDER_WAIT);
//	local_show(ID_UNIT_ORDER_DONE);
//      }
//
//      /* Enable the button for adding to a city in all cases, so we
//       * get an eventual error message from the server if we try. */
//
//      if (can_unit_add_or_build_city(pUnit)) {
//	if(pTile.city) {
//	  cBuf = util.my_snprintf("%s%s", "Add to City", " (B)");
//	} else {
//	  cBuf = util.my_snprintf("%s%s", "Build New City", " (B)");
//	}
//	copy_chars_to_string16(pOrder_Build_AddTo_City_Button.string16, cBuf);
//	clear_wflag(pOrder_Build_AddTo_City_Button, WF_HIDDEN);
//      } else {
//	set_wflag(pOrder_Build_AddTo_City_Button, WF_HIDDEN);
//      }
//
//      if (unit_can_help_build_wonder_here(pUnit)) {
//	local_show(ID_UNIT_ORDER_BUILD_WONDER);
//      } else {
//	local_hide(ID_UNIT_ORDER_BUILD_WONDER);
//      }
//
//      time = can_unit_do_activity(pUnit, ACTIVITY_RAILROAD);
//      if (can_unit_do_activity(pUnit, ACTIVITY_ROAD) || time) {
//	if(time) {
//	  time = map_build_rail_time(pUnit.x, pUnit.y);
//	  cBuf = util.my_snprintf("%s%s %d %s",
//			"Build Railroad", " (R)", time , 
//			PL("turn", "turns", time));
//	  pOrder_Road_Button.theme = pTheme.ORailRoad_Icon;
//	} else {
//	  time = map_build_road_time(pUnit.x, pUnit.y);
//	  cBuf = util.my_snprintf("%s%s %d %s",
//			"Build Road", " (R)", time , 
//			PL("turn", "turns", time));
//	  pOrder_Road_Button.theme = pTheme.ORoad_Icon;
//	}
//	copy_chars_to_string16(pOrder_Road_Button.string16, cBuf);
//	clear_wflag(pOrder_Road_Button, WF_HIDDEN);
//      } else {
//	set_wflag(pOrder_Road_Button, WF_HIDDEN);
//      }
//      
//	/* unit_can_est_traderoute_here(pUnit) */
//      if (pTile.city && unit_flag(pUnit, F_TRADE_ROUTE)
//        && (pHomecity = Game.find_city_by_id(pUnit.homecity))
//	&& City.can_cities_trade(pHomecity, pTile.city)) {
//	int revenue = get_caravan_enter_city_trade_bonus(pHomecity, pTile.city);
//	
//        if (City.can_establish_trade_route(pHomecity, pTile.city)) {
//          cBuf = util.my_snprintf(
//      		"Form Traderoute with %s ( %d R&G + %d trade ) (R)",
//      		pHomecity.name, revenue,
//      			trade_between_cities(pHomecity, pTile.city));
//	} else {
//          revenue = (revenue + 2) / 3;
//          cBuf = util.my_snprintf(
//		"Trade with %s ( %d R&G bonus ) (R)", pHomecity.name, revenue);
//        }
//	copy_chars_to_string16(pOrder_Trade_Button.string16, cBuf);
//	clear_wflag(pOrder_Trade_Button, WF_HIDDEN);
//      } else {
//	set_wflag(pOrder_Trade_Button, WF_HIDDEN);
//      }
//
//      if (can_unit_do_activity(pUnit, ACTIVITY_IRRIGATE)) {
//	time = map_build_irrigation_time(pUnit.x, pUnit.y);
//	switch (terrain) {
//	  /* set Crop Forest Icon */
//	case T_FOREST:
//	case T_JUNGLE:  
//	  cBuf = util.my_snprintf("%s %s%s %d %s",
//			"Cut Down to",
//			tile_types[tile_types[terrain].irrigation_result
//				].terrain_name
//			," (I)", time , PL("turn", "turns", time));
//	  pOrder_Irrigation_Button.theme = pTheme.OCutDownForest_Icon;
//	  break;
//	case T_SWAMP:
//	  cBuf = util.my_snprintf("%s %s%s %d %s",
//			"Irrigate to",
//			tile_types[tile_types[terrain].irrigation_result
//				].terrain_name
//			," (I)", time , PL("turn", "turns", time));
//	  pOrder_Irrigation_Button.theme = pTheme.OIrrigation_Icon;
//	  break;
//	  /* set Irrigation Icon */
//	default:
//	  cBuf = util.my_snprintf("%s%s %d %s",
//			"Build Irrigation", " (I)", time , 
//			PL("turn", "turns", time));
//	  pOrder_Irrigation_Button.theme = pTheme.OIrrigation_Icon;
//	  break;
//	}
//	copy_chars_to_string16(pOrder_Irrigation_Button.string16, cBuf);
//	clear_wflag(pOrder_Irrigation_Button, WF_HIDDEN);
//      } else {
//	set_wflag(pOrder_Irrigation_Button, WF_HIDDEN);
//      }
//
//      if (can_unit_do_activity(pUnit, ACTIVITY_MINE)) {
//	time = map_build_mine_time(pUnit.x, pUnit.y);
//	switch (terrain) {
//	  /* set Irrigate Icon . make swamp */
//	case T_FOREST:
//	  cBuf = util.my_snprintf("%s %s%s %d %s",
//			"Irrigate to",
//			tile_types[tile_types[terrain].mining_result
//				].terrain_name
//			," (M)", time , PL("turn", "turns", time));
//	  pOrder_Mine_Button.theme = pTheme.OIrrigation_Icon;
//	  break;
//	  /* set Forest Icon . plant Forrest*/
//	case T_JUNGLE:
//	case T_PLAINS:
//	case T_GRASSLAND:
//	case T_SWAMP:
//	  cBuf = util.my_snprintf("%s%s %d %s",
//			"Plant Forest", " (M)", time , 
//			PL("turn", "turns", time));
//	  pOrder_Mine_Button.theme = pTheme.OPlantForest_Icon;
//	  break;
//	  /* set Mining Icon */
//	default:
//	  cBuf = util.my_snprintf("%s%s %d %s",
//			"Build Mine", " (M)", time , 
//			PL("turn", "turns", time));
//	  pOrder_Mine_Button.theme = pTheme.OMine_Icon;
//	  break;
//	}
//        copy_chars_to_string16(pOrder_Mine_Button.string16, cBuf);
//	clear_wflag(pOrder_Mine_Button, WF_HIDDEN);
//      } else {
//	set_wflag(pOrder_Mine_Button, WF_HIDDEN);
//      }
//
//      if (can_unit_do_activity(pUnit, ACTIVITY_TRANSFORM)) {
//	time = map_transform_time(pUnit.x, pUnit.y);
//	cBuf = util.my_snprintf("%s %s%s %d %s",
//	  "Transform to",
//	  tile_types[tile_types[terrain].transform_result].terrain_name,
//			" (M)", time , 
//			PL("turn", "turns", time));
//	copy_chars_to_string16(pOrder_Transform_Button.string16, cBuf);
//	clear_wflag(pOrder_Transform_Button, WF_HIDDEN);
//      } else {
//	set_wflag(pOrder_Transform_Button, WF_HIDDEN);
//      }
//
//      if (!pTile.city && can_unit_do_activity(pUnit, ACTIVITY_FORTRESS)) {
//	local_show(ID_UNIT_ORDER_FORTRESS);
//      } else {
//	local_hide(ID_UNIT_ORDER_FORTRESS);
//      }
//
//      if (can_unit_do_activity(pUnit, ACTIVITY_FORTIFYING)) {
//	local_show(ID_UNIT_ORDER_FORTIFY);
//      } else {
//	local_hide(ID_UNIT_ORDER_FORTIFY);
//      }
//
//      if (!pTile.city && can_unit_do_activity(pUnit, ACTIVITY_AIRBASE)) {
//	local_show(ID_UNIT_ORDER_AIRBASE);
//      } else {
//	local_hide(ID_UNIT_ORDER_AIRBASE);
//      }
//
//      if (can_unit_do_activity(pUnit, ACTIVITY_POLLUTION)) {
//	local_show(ID_UNIT_ORDER_POLLUTION);
//      } else {
//	local_hide(ID_UNIT_ORDER_POLLUTION);
//      }
//
//      if (can_unit_paradrop(pUnit)) {
//	local_show(ID_UNIT_ORDER_PARADROP);
//      } else {
//	local_hide(ID_UNIT_ORDER_PARADROP);
//      }
//
//      if (can_unit_do_activity(pUnit, ACTIVITY_FALLOUT)) {
//	local_show(ID_UNIT_ORDER_FALLOUT);
//      } else {
//	local_hide(ID_UNIT_ORDER_FALLOUT);
//      }
//
//      if (can_unit_do_activity(pUnit, ACTIVITY_SENTRY)) {
//	local_show(ID_UNIT_ORDER_SENTRY);
//      } else {
//	local_hide(ID_UNIT_ORDER_SENTRY);
//      }
//
//      if (can_unit_do_activity(pUnit, ACTIVITY_PILLAGE)) {
//	local_show(ID_UNIT_ORDER_PILLAGE);
//      } else {
//	local_hide(ID_UNIT_ORDER_PILLAGE);
//      }
//
//      if (pTile.city && can_unit_change_homecity(pUnit)
//	&& pTile.city.id != pUnit.homecity) {
//	local_show(ID_UNIT_ORDER_HOMECITY);
//      } else {
//	local_hide(ID_UNIT_ORDER_HOMECITY);
//      }
//
//      if (pUnit.occupy && get_transporter_occupancy(pUnit) > 0) {
//	local_show(ID_UNIT_ORDER_UNLOAD);
//      } else {
//	local_hide(ID_UNIT_ORDER_UNLOAD);
//      }
//
//      if (is_unit_activity_on_tile(ACTIVITY_SENTRY, pUnit.x, pUnit.y)) {
//	local_show(ID_UNIT_ORDER_WAKEUP_OTHERS);
//      } else {
//	local_hide(ID_UNIT_ORDER_WAKEUP_OTHERS);
//      }
//
//      if (can_unit_do_auto(pUnit)) {
//	if (unit_flag(pUnit, Eunit_flag_id.F_SETTLERS)) {
//	  if(pOrder_Automate_Unit_Button.theme != pTheme.OAutoSett_Icon) {
//	    cBuf = util.my_snprintf("%s%s", "Auto-Settler", " (A)");
//	    pOrder_Automate_Unit_Button.theme = pTheme.OAutoSett_Icon;
//	    copy_chars_to_string16(pOrder_Automate_Unit_Button.string16, cBuf);
//	  }
//	} else {
//	  if(pOrder_Automate_Unit_Button.theme != pTheme.OAutoAtt_Icon) {
//	    cBuf = util.my_snprintf("%s%s", "Auto-Attack", " (A)");
//	    pOrder_Automate_Unit_Button.theme = pTheme.OAutoAtt_Icon;
//	    copy_chars_to_string16(pOrder_Automate_Unit_Button.string16, cBuf);
//	  }
//	}
//	clear_wflag(pOrder_Automate_Unit_Button, WF_HIDDEN);
//      } else {
//	set_wflag(pOrder_Automate_Unit_Button, WF_HIDDEN);
//      }
//
//      if (can_unit_do_activity(pUnit, ACTIVITY_EXPLORE)) {
//	local_show(ID_UNIT_ORDER_AUTO_EXPLORE);
//      } else {
//	local_hide(ID_UNIT_ORDER_AUTO_EXPLORE);
//      }
//
//      if (can_unit_do_connect(pUnit, unit_activity.ACTIVITY_IDLE)) {
//	local_show(ID_UNIT_ORDER_CONNECT);
//      } else {
//	local_hide(ID_UNIT_ORDER_CONNECT);
//      }
//
//      if (is_diplomat_unit(pUnit) &&
//	  diplomat_can_do_action(pUnit, DIPLOMAT_ANY_ACTION, pUnit.x,
//				 pUnit.y)) {
//	local_show(ID_UNIT_ORDER_DIPLOMAT_DLG);
//      } else {
//	local_hide(ID_UNIT_ORDER_DIPLOMAT_DLG);
//      }
//
//      if (unit_flag(pUnit, F_NUCLEAR)) {
//	local_show(ID_UNIT_ORDER_NUKE);
//      } else {
//	local_hide(ID_UNIT_ORDER_NUKE);
//      }
//
//      if (pTile.city && has_city_airport(pTile.city) && pTile.city.airlift) {
//	local_show(ID_UNIT_ORDER_AIRLIFT);
//	hide(ID_UNIT_ORDER_GOTO_CITY);
//      } else {
//	local_show(ID_UNIT_ORDER_GOTO_CITY);
//	local_hide(ID_UNIT_ORDER_AIRLIFT);
//      }
//
//      if (!pTile.city && !is_air_unittype(pUnit.type)) {
//        local_show(ID_UNIT_ORDER_RETURN);
//      } else {
//	local_hide(ID_UNIT_ORDER_RETURN);
//      }
//      
//      if (pTile.city && can_upgrade_unittype(Game.game.player_ptr, pUnit.type) != -1) {
//	local_show(ID_UNIT_ORDER_UPGRADE);
//      } else {
//	local_hide(ID_UNIT_ORDER_UPGRADE);
//      }
//            
//      set_new_order_widget_start_pos();
//      counter = redraw_order_widgets();
//
//    } else {
//      if (counter) {
//	hide_group(pBeginOrderWidgetList, pEndOrderWidgetList);
//      }
//
//      counter = 0;
//    }
//  }
//}
//
//void disable_order_buttons()
//{
//  undraw_order_widgets();
//  disable_group(pBeginOrderWidgetList, pEndOrderWidgetList);
//  redraw_order_widgets();
//}
//
//void enable_order_buttons()
//{
//  undraw_order_widgets();
//  enable_group(pBeginOrderWidgetList, pEndOrderWidgetList);
//  redraw_order_widgets();
//}
}