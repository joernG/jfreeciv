package client.gui_sdl;

public class Dialogs{

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
//                          dialogs.c  -  description
//                             -------------------
//    begin                : Wed Jul 24 2002
//    copyright            : (C) 2002 by Rafał Bursig
//    email                : Rafał Bursig <bursig@poczta.fm>
//***************************************************************************/
//
//#ifdef HAVE_CONFIG_H
//#include <config.h>
//#endif
//
//#include <stdio.h>
//#include <stdlib.h>
//#include <string.h>
//
//#include <SDL/SDL.h>
//
//#include "climap.h" /* for tile_get_known() */
//#include "combat.h"
//#include "fcintl.h"
//#include "log.h"
//#include "Game.game.h"
//#include "government.h"
//#include "Map.map.h"
//
//#include "gui_mem.h"
//
//#include "packets.h"
//#include "player.h"
//#include "rand.h"
//#include "support.h"
//
//#include "chatline.h"
//#include "civclient.h"
//#include "clinet.h"
//#include "control.h"
//#include "goto.h"
//#include "packhand.h"
//
//#include "colors.h"
//#include "cma_fe.h"
//
//#include "citydlg.h"
//#include "graphics.h"
//#include "unistring.h"
//#include "gui_string.h"
//#include "gui_stuff.h"
//#include "gui_zoom.h"
//#include "gui_id.h"
//#include "gui_tilespec.h"
//#include "gui_main.h"
//#include "repodlgs.h"
//#include "finddlg.h"
//#include "messagewin.h"
//#include "wldlg.h"
//#include "inteldlg.h"
//#include "plrdlg.h"
//#include "gotodlg.h"
//#include "helpdlg.h"
//
//#include "mapview.h"
//#include "mapctrl.h"
//#include "mapctrl_common.h"
//#include "options.h"
//#include "optiondlg.h"
//#include "tilespec.h"
//
//#include "dialogs.h"
//
//#define create_active_iconlabel(pBuf, pDest, pStr, pString, pCallback)   \
//do { 									 \
//  pStr = create_str16_from_char(pString, 10);				 \
//  pStr.style |= TTF_STYLE_BOLD;					 \
//  pBuf = create_iconlabel(null, pDest, pStr, 				 \
//    	     (WF_DRAW_THEME_TRANSPARENT|WF_DRAW_TEXT_LABEL_WITH_SPACE)); \
//  pBuf.string16.bgcol.unused = 128;					 \
//  pBuf.string16.render = 3;						 \
//  pBuf.action = pCallback;						 \
//} while(0)
//
//
//extern boolean is_unit_move_blocked;
//
//static void popdown_unit_select_dialog();
//static void popdown_advanced_terrain_dialog();
//static void popdown_terrain_info_dialog();
//static void popdown_caravan_dialog();
//static void popdown_diplomat_dialog();
//static void popdown_pillage_dialog();
//static void popdown_incite_dialog();
//static void popdown_connect_dialog();
//static void popdown_bribe_dialog();
//static void popdown_revolution_dialog();
//static void popdown_unit_upgrade_dlg();
//

//  ...
//***********************************************************************/
//void put_window_near_map_tile(GUI pWindow,
//  		int window_width, int window_height, int x, int y)
//{
//  int canvas_x, canvas_y;
//  
//  if (tile_to_canvas_pos(&canvas_x, &canvas_y, x, y)) {
//    if (canvas_x + NORMAL_TILE_WIDTH + window_width >= pWindow.dst.w)
//    {
//      if (canvas_x - window_width < 0) {
//	pWindow.size.x = (pWindow.dst.w - window_width) / 2;
//      } else {
//	pWindow.size.x = canvas_x - window_width;
//      }
//    } else {
//      pWindow.size.x = canvas_x + NORMAL_TILE_WIDTH;
//    }
//    
//    canvas_y += (NORMAL_TILE_HEIGHT - window_height) / 2;
//    if (canvas_y + window_height >= pWindow.dst.h)
//    {
//      pWindow.size.y = pWindow.dst.h - window_height - 1;
//    } else {
//      if (canvas_y < 0)
//      {
//	pWindow.size.y = 0;
//      } else {
//        pWindow.size.y = canvas_y;
//      }
//    }
//  } else {
//    pWindow.size.x = (pWindow.dst.w - window_width) / 2;
//    pWindow.size.y = (pWindow.dst.h - window_height) / 2;
//  }
//  
//}
//
//

//  This function is called when the client disconnects or the Game.game is
//  over.  It should close all dialog windows for that Game.game.
//***********************************************************************/
//void popdown_all_game_dialogs()
//{
//  popdown_caravan_dialog();  
//  popdown_unit_select_dialog();
//  popdown_advanced_terrain_dialog();
//  popdown_terrain_info_dialog();
//  popdown_newcity_dialog();
//  podown_optiondlg();
//  popdown_diplomat_dialog();
//  popdown_pillage_dialog();
//  popdown_incite_dialog();
//  popdown_connect_dialog();
//  popdown_bribe_dialog();
//  popdown_find_dialog();
//  popdown_revolution_dialog();
//  popdown_all_science_dialogs();
//  popdown_meswin_dialog();
//  popdown_worklist_editor();
//  popdown_economy_report_dialog();
//  popdown_activeunits_report_dialog();
//  popdown_intel_dialog();
//  popdown_players_nations_dialog();
//  popdown_players_dialog();
//  popdown_goto_airlift_dialog();
//  popdown_unit_upgrade_dlg();
//  popdown_help_dialog();
//  
//  /* clear gui buffer */
//  if (get_client_state() == CLIENT_PRE_GAME_STATE) {
//    draw_city_names = false;
//    draw_city_productions = false;
//    SDL_FillRect(Main.text, null, 0x0);
//    SDL_FillRect(Main.gui, null, 0x0);
//  }
//}
//
///* ======================================================================= */
//
///**************************************************************************
//  Find the my unit's (focus) chance of success at attacking/defending the
//  given enemy unit.  Return false if the values cannot be determined (e.g., no
//  units given).
//**************************************************************************/
//static boolean sdl_get_chance_to_win(int *att_chance, int *def_chance,
//		       		unit enemy_unit, unit my_unit)
//{
//  
//  if (!my_unit || null==enemy_unit) {
//    return false;
//  }
//
//  /* chance to win when active unit is attacking the selected unit */
//  *att_chance = unit_win_chance(my_unit, enemy_unit) * 100;
//
//  /* chance to win when selected unit is attacking the active unit */
//  *def_chance = (1.0 - unit_win_chance(enemy_unit, my_unit)) * 100;
//
//  return true;
//}
//
///**************************************************************************
//  Popup a dialog to display information about an event that has a
//  specific location.  The user should be given the option to goto that
//  location.
//**************************************************************************/
//void popup_notify_goto_dialog(final String headline, final String lines,
//			      int x, int y)
//{
//  util.freelog(Log.LOG_NORMAL, "popup_notify_goto_dialog : PORT ME\n \
//  			a: %s\nb: %s",headline, lines );
//}
//
///* ----------------------------------------------------------------------- */
//ADVANCED_DLG pNotifyDlg = null;
//
///**************************************************************************
//...
//**************************************************************************/
//static int notify_dialog_window_callback(GUI pWindow)
//{
//  return std_move_window_group_callback(pNotifyDlg.pBeginWidgetList,
//  								pWindow);
//}
//
///**************************************************************************
//...
//**************************************************************************/
//static int exit_notify_dialog_callback(GUI pWidget)
//{
//  if(pNotifyDlg) {
//    popdown_window_group_dialog(pNotifyDlg.pBeginWidgetList,
//					    pNotifyDlg.pEndWidgetList);
//    FREE(pNotifyDlg.pScroll);
//    FREE(pNotifyDlg);
//    flush_dirty();
//  }
//  return -1;
//}
//
///**************************************************************************
//  Popup a generic dialog to display some generic information.
//**************************************************************************/
//void popup_notify_dialog(final String caption, final String headline,
//			 final String lines)
//{
//  GUI pBuf, *pWindow;
//  SDL_String16 *pStr;
//  SDL_Surface *pHeadline, *pLines;
//  SDL_Rect dst;
//  int w = 0, h;
//  
//  if (pNotifyDlg) {
//    return;
//  }
//  
//  pNotifyDlg = MALLOC(sizeof(struct ADVANCED_DLG));
//   
//  pStr = create_str16_from_char(caption, 12);
//  pStr.style |= TTF_STYLE_BOLD;
//  
//  pWindow = create_window(null, pStr, 10, 10, 0);
//  
//  pWindow.action = notify_dialog_window_callback;
//  set_wstate(pWindow, FC_WS_NORMAL);
//  w = MAX(w, pWindow.size.w);
//  
//  add_to_gui_list(ID_WINDOW, pWindow);
//  pNotifyDlg.pEndWidgetList = pWindow;
//  
//  /* ---------- */
//  /* create exit button */
//  pBuf = create_themeicon(pTheme.Small_CANCEL_Icon, pWindow.dst,
//  			  			WF_DRAW_THEME_TRANSPARENT);
//  pBuf.action = exit_notify_dialog_callback;
//  set_wstate(pBuf, FC_WS_NORMAL);
//  pBuf.key = SDLK_ESCAPE;
//  w += (pBuf.size.w + 10);
//  
//  add_to_gui_list(ID_BUTTON, pBuf);
//  pNotifyDlg.pBeginWidgetList = pBuf;
//    
//  pStr = create_str16_from_char(headline, 16);
//  pStr.style |= TTF_STYLE_BOLD;
//  
//  pHeadline = create_text_surf_from_str16(pStr);
//    
//  if(lines && *lines != '\0') {
//    change_ptsize16(pStr, 12);
//    pStr.style &= ~TTF_STYLE_BOLD;
//    copy_chars_to_string16(pStr, lines);
//    pLines = create_text_surf_from_str16(pStr);
//  } else {
//    pLines = null;
//  }
//  
//  FREESTRING16(pStr);
//  
//  w = MAX(w, pHeadline.w);
//  if(pLines) {
//    w = MAX(w, pLines.w);
//  }
//  w += 60;
//  h = WINDOW_TILE_HIGH + 1 + FRAME_WH + 10 + pHeadline.h + 10;
//  if(pLines) {
//    h += pLines.h + 10;
//  }
//  pWindow.size.x = (Main.screen.w - w) / 2;
//  pWindow.size.y = (Main.screen.h - h) / 2;
//  
//  resize_window(pWindow, null,
//  	get_game_colorRGB(COLOR_STD_BACKGROUND_BROWN), w, h);
//	
//  dst.x = (pWindow.size.w - pHeadline.w) / 2;
//  dst.y = WINDOW_TILE_HIGH + 11;
//  
//  SDL_BlitSurface(pHeadline, null, pWindow.theme, &dst);
//  if(pLines) {
//    dst.y += pHeadline.h + 10;
//    if(pHeadline.w < pLines.w) {
//      dst.x = (pWindow.size.w - pLines.w) / 2;
//    }
//     
//    SDL_BlitSurface(pLines, null, pWindow.theme, &dst);
//  }
//  
//  FREESURFACE(pHeadline);
//  FREESURFACE(pLines);
//  
//  /* exit button */
//  pBuf = pWindow.prev; 
//  pBuf.size.x = pWindow.size.x + pWindow.size.w - pBuf.size.w - FRAME_WH - 1;
//  pBuf.size.y = pWindow.size.y + 1 + (WINDOW_TILE_HIGH - pBuf.size.h) / 2;
//    
//  /* redraw */
//  redraw_group(pNotifyDlg.pBeginWidgetList, pWindow, 0);
//  flush_rect(pWindow.size);
//}
//
///* =======================================================================*/
///* ======================== UNIT UPGRADE DIALOG ========================*/
///* =======================================================================*/
//static SMALL_DLG pUnit_Upgrade_Dlg = null;
//
///****************************************************************
//...
//*****************************************************************/
//static int upgrade_unit_window_callback(GUI pWindow)
//{
//  return std_move_window_group_callback(
//  			pUnit_Upgrade_Dlg.pBeginWidgetList, pWindow);
//}
//
///****************************************************************
//...
//*****************************************************************/
//static int cancel_upgrade_unit_callback(GUI pWidget)
//{
//  popdown_unit_upgrade_dlg();
//  /* enable city dlg */
//  enable_city_dlg_widgets();
//  flush_dirty();
//  return -1;
//}
//
///****************************************************************
//...
//*****************************************************************/
//static int ok_upgrade_unit_window_callback(GUI pWidget)
//{
//  unit pUnit = pWidget.data.unit;
//  popdown_unit_upgrade_dlg();
//  /* enable city dlg */
//  enable_city_dlg_widgets();
//  free_city_units_lists();
//  request_unit_upgrade(pUnit);
//  flush_dirty();
//  return -1;
//}
//
///****************************************************************
//...
//*****************************************************************/
//void popup_unit_upgrade_dlg(unit pUnit, boolean city)
//{
//  
//  int ut1, ut2;
//  int value = 9999, hh, ww = 0;
//  char cBuf[128];
//  GUI pBuf = null, *pWindow;
//  SDL_String16 *pStr;
//  SDL_Surface *pText;
//  SDL_Rect dst;
//  
//  ut1 = pUnit.type;
//  
//  if (pUnit_Upgrade_Dlg || !unit_type_exists(ut1)) {
//    /* just in case */
//    flush_dirty();
//    return;
//  }
//    
//  pUnit_Upgrade_Dlg = MALLOC(sizeof(struct SMALL_DLG));
//
//  ut2 = can_upgrade_unittype(Game.game.player_ptr, ut1);
//  
//  if (ut2 != -1) {
//    value = unit_upgrade_price(Game.game.player_ptr, ut1, ut2);
//  
//    if (Game.game.player_ptr.economic.gold >= value) {
//      cBuf = util.my_snprintf(
//    	      ("Upgrade %s to %s for %d gold?\n" +
//                "Treasury contains %d gold."),
//	  Unittype_P.unit_types[ut1].name, Unittype_P.unit_types[ut2].name,
//	  value, Game.game.player_ptr.economic.gold);
//    } else {
//      cBuf = util.my_snprintf(
//          ("Upgrading %s to %s costs %d gold.\n" +
//            "Treasury contains %d gold."),
//          Unittype_P.unit_types[ut1].name, Unittype_P.unit_types[ut2].name,
//          value, Game.game.player_ptr.economic.gold);
//    }
//  } else {
//    cBuf = util.my_snprintf(
//        "Sorry: cannot upgrade %s.", Unittype_P.unit_types[ut1].name);
//  }
//  
//  hh = WINDOW_TILE_HIGH + 1;
//  pStr = create_str16_from_char("Upgrade Obsolete Units", 12);
//  pStr.style |= TTF_STYLE_BOLD;
//
//  pWindow = create_window(null, pStr, 100, 100, 0);
//
//  pWindow.action = upgrade_unit_window_callback;
//  set_wstate(pWindow, FC_WS_NORMAL);
//
//  pUnit_Upgrade_Dlg.pEndWidgetList = pWindow;
//
//  add_to_gui_list(ID_WINDOW, pWindow);
//
//  /* ============================================================= */
//  
//  /* create text label */
//  pStr = create_str16_from_char(cBuf, 10);
//  pStr.style |= (TTF_STYLE_BOLD|SF_CENTER);
//  pStr.fgcol.r = 255;
//  pStr.fgcol.g = 255;
//  /*pStr.forecol.b = 255; */
//  
//  pText = create_text_surf_from_str16(pStr);
//  FREESTRING16(pStr);
//  
//  hh += (pText.h + 10);
//  ww = MAX(ww, pText.w + 20);
//  
//  /* cancel button */
//  pBuf = create_themeicon_button_from_chars(pTheme.CANCEL_Icon,
//			    pWindow.dst, "Cancel", 12, 0);
//
//  clear_wflag(pBuf, WF_DRAW_FRAME_AROUND_WIDGET);
//  pBuf.action = cancel_upgrade_unit_callback;
//  set_wstate(pBuf, FC_WS_NORMAL);
//
//  hh += (pBuf.size.h + 20);
//  
//  add_to_gui_list(ID_BUTTON, pBuf);
//  
//  if ((ut2 != -1) && (Game.game.player_ptr.economic.gold >= value)) {
//    pBuf = create_themeicon_button_from_chars(pTheme.OK_Icon, pWindow.dst,
//					      "Upgrade", 12, 0);
//        
//    clear_wflag(pBuf, WF_DRAW_FRAME_AROUND_WIDGET);
//    pBuf.action = ok_upgrade_unit_window_callback;
//    set_wstate(pBuf, FC_WS_NORMAL);
//    pBuf.data.unit = pUnit;    
//    add_to_gui_list(ID_BUTTON, pBuf);
//    pBuf.size.w = MAX(pBuf.size.w, pBuf.next.size.w);
//    pBuf.next.size.w = pBuf.size.w;
//    ww = MAX(ww, 30 + pBuf.size.w * 2);
//  } else {
//    ww = MAX(ww, pBuf.size.w + 20);
//  }
//  /* ============================================ */
//  
//  pUnit_Upgrade_Dlg.pBeginWidgetList = pBuf;
//  if(city) {
//    pWindow.size.x = Main.event.motion.x;
//    pWindow.size.y = Main.event.motion.y;
//  } else {
//    put_window_near_map_tile(pWindow,
//  		ww + DOUBLE_FRAME_WH, hh, pUnit.x, pUnit.y);
//  }
//    
//  resize_window(pWindow, null,
//		get_game_colorRGB(COLOR_STD_BACKGROUND_BROWN),
//		ww + DOUBLE_FRAME_WH, hh);
//  
//  /* setup rest of widgets */
//  /* label */
//  dst.x = FRAME_WH + (ww - DOUBLE_FRAME_WH - pText.w) / 2;
//  dst.y = WINDOW_TILE_HIGH + 11;
//  SDL_BlitSurface(pText, null, pWindow.theme, &dst);
//  FREESURFACE(pText);
//   
//  /* cancel button */
//  pBuf = pWindow.prev;
//  pBuf.size.y = pWindow.size.y + pWindow.size.h - pBuf.size.h - 10;
//  
//  if ((ut2 != -1) && (Game.game.player_ptr.economic.gold >= value)) {
//    /* sell button */
//    pBuf = pBuf.prev;
//    pBuf.size.x = pWindow.size.x + (ww - (2 * pBuf.size.w + 10)) / 2;
//    pBuf.size.y = pBuf.next.size.y;
//    
//    /* cancel button */
//    pBuf.next.size.x = pBuf.size.x + pBuf.size.w + 10;
//  } else {
//    /* x position of cancel button */
//    pBuf.size.x = pWindow.size.x +
//			    pWindow.size.w - FRAME_WH - pBuf.size.w - 10;
//  }
//  
//  
//  /* ================================================== */
//  /* redraw */
//  redraw_group(pUnit_Upgrade_Dlg.pBeginWidgetList, pWindow, 0);
//    
//  sdl_dirty_rect(pWindow.size);
//  flush_dirty();
//  
//}
//
///****************************************************************
//...
//*****************************************************************/
//static void popdown_unit_upgrade_dlg()
//{
//  if (pUnit_Upgrade_Dlg) {
//    popdown_window_group_dialog(pUnit_Upgrade_Dlg.pBeginWidgetList,
//			      pUnit_Upgrade_Dlg.pEndWidgetList);
//    FREE(pUnit_Upgrade_Dlg);
//  }
//}
//
///* =======================================================================*/
///* ======================== UNIT SELECTION DIALOG ========================*/
///* =======================================================================*/
//static ADVANCED_DLG pUnit_Select_Dlg = null;
//
///**************************************************************************
//...
//**************************************************************************/
//static int unit_select_window_callback(GUI pWindow)
//{
//  return std_move_window_group_callback(pUnit_Select_Dlg.pBeginWidgetList,
//  								pWindow);
//}
//
///**************************************************************************
//...
//**************************************************************************/
//static int exit_unit_select_callback( GUI pWidget )
//{
//  popdown_unit_select_dialog();
//  is_unit_move_blocked = false;
//  return -1;
//}
//
///**************************************************************************
//...
//**************************************************************************/
//static int unit_select_callback( GUI pWidget )
//{
//  unit pUnit = Player_P.player_find_unit_by_id(Game.game.player_ptr,
//                                   MAX_ID - pWidget.ID);
//
//  popdown_unit_select_dialog();
//  if (pUnit) {
//    request_new_unit_activity(pUnit, unit_activity.ACTIVITY_IDLE);
//    set_unit_focus(pUnit);
//  }
//
//  return -1;
//}
//
///**************************************************************************
//  Popdown a dialog window to select units on a particular tile.
//**************************************************************************/
//static void popdown_unit_select_dialog()
//{
//  if (pUnit_Select_Dlg) {
//    is_unit_move_blocked = false;
//    popdown_window_group_dialog(pUnit_Select_Dlg.pBeginWidgetList,
//			pUnit_Select_Dlg.pEndWidgetList);
//				   
//    FREE(pUnit_Select_Dlg.pScroll);
//    FREE(pUnit_Select_Dlg);
//    flush_dirty();
//  }
//}
//
///**************************************************************************
//  Popup a dialog window to select units on a particular tile.
//**************************************************************************/
//void popup_unit_select_dialog(tile ptile)
//{
//  GUI pBuf = null, *pWindow;
//  SDL_String16 *pStr;
//  unit pUnit = null, *pFocus = get_unit_in_focus();
//  unit_type pUnitType;
//  char cBuf[255];  
//  int i, w = 0, h, n;
//  
//  public static final int NUM_SEEN = 20;
//  
//  n = ptile.units.foo_list_size();
//  
//  if (!n || pUnit_Select_Dlg) {
//    return;
//  }
//  
//  is_unit_move_blocked = true;  
//  pUnit_Select_Dlg = MALLOC(sizeof(struct ADVANCED_DLG));
//    
//  cBuf = util.my_snprintf("%s (%d)", "Unit selection" , n);
//  pStr = create_str16_from_char(cBuf , 12);
//  pStr.style |= TTF_STYLE_BOLD;
//  
//  pWindow = create_window(null, pStr, 10, 10, WF_DRAW_THEME_TRANSPARENT);
//  
//  pWindow.action = unit_select_window_callback;
//  set_wstate(pWindow, FC_WS_NORMAL);
//  w = MAX(w, pWindow.size.w);
//  
//  add_to_gui_list(ID_UNIT_SELLECT_DLG_WINDOW, pWindow);
//  pUnit_Select_Dlg.pEndWidgetList = pWindow;
//  /* ---------- */
//  /* create exit button */
//  pBuf = create_themeicon(pTheme.Small_CANCEL_Icon, pWindow.dst,
//  			  			WF_DRAW_THEME_TRANSPARENT);
//  pBuf.action = exit_unit_select_callback;
//  set_wstate(pBuf, FC_WS_NORMAL);
//  pBuf.key = SDLK_ESCAPE;
//  w += (pBuf.size.w + 10);
//  
//  add_to_gui_list(ID_UNIT_SELLECT_DLG_EXIT_BUTTON, pBuf);
//    
//  /* ---------- */
//  h = WINDOW_TILE_HIGH + 1 + FRAME_WH;
//  
//  for(i = 0; i < n; i++) {
//    pUnit = unit_list_get(&ptile.units, i);
//    pUnitType = pUnit.unit_type();
//        
//    if(pUnit.owner == Game.game.player_idx) {
//      cBuf = util.my_snprintf( "Contact %s (%d / %d) %s(%d,%d,%d) %s",
//            pUnit.veteran ? "Veteran" : "" ,
//            pUnit.hp, pUnitType.hp,
//            pUnitType.name,
//            pUnitType.attack_strength,
//            pUnitType.defense_strength,
//            (pUnitType.move_rate / Unit_H.SINGLE_MOVE),
//	    unit_activity_text(pUnit));
//    } else {
//      int att_chance, def_chance;
//      
//      cBuf = util.my_snprintf( "%s %s %s(A:%d D:%d M:%d FP:%d) HP:%d%%",
//            Nation.get_nation_by_plr(pUnit.unit_owner()).name,
//            (pUnit.veteran ? "Veteran" : ""),
//            pUnitType.name,
//            pUnitType.attack_strength,
//            pUnitType.defense_strength,
//            (pUnitType.move_rate / Unit_H.SINGLE_MOVE),
//      	    pUnitType.firepower,
//	    (pUnit.hp * 100 / pUnitType.hp + 9) / 10);
//      
//      /* calculate chance to win */
//      if (sdl_get_chance_to_win(&att_chance, &def_chance, pUnit, pFocus)) {
//          cat_snprintf(cBuf, sizeof(cBuf), " CtW: Att:%d%% Def:%d%%",
//               att_chance, def_chance);
//      }
//    }
//    
//    create_active_iconlabel(pBuf, pWindow.dst,
//    		pStr, cBuf, unit_select_callback);
//            
//    add_to_gui_list(MAX_ID - pUnit.id , pBuf);
//    
//    w = MAX(w, pBuf.size.w);
//    h += pBuf.size.h;
//    if(pUnit.owner == Game.game.player_idx) {
//      set_wstate(pBuf, FC_WS_NORMAL);
//    }
//    
//    if (i > NUM_SEEN - 1)
//    {
//      set_wflag(pBuf , WF_HIDDEN);
//    }
//    
//  }
//  pUnit_Select_Dlg.pBeginWidgetList = pBuf;
//  pUnit_Select_Dlg.pBeginActiveWidgetList = pBuf;
//  pUnit_Select_Dlg.pEndActiveWidgetList = pWindow.prev.prev;
//  pUnit_Select_Dlg.pActiveWidgetList = pWindow.prev.prev;
//  
//  w += (DOUBLE_FRAME_WH + 2);
//  if (n > NUM_SEEN)
//  {
//    n = create_vertical_scrollbar(pUnit_Select_Dlg, 1, NUM_SEEN, true, true);
//    w += n;
//    
//    /* ------- window ------- */
//    h = WINDOW_TILE_HIGH + 1 +
//	    NUM_SEEN * pWindow.prev.prev.size.h + FRAME_WH;
//  }
//  
//  put_window_near_map_tile(pWindow, w, h, pUnit.x, pUnit.y);
//  resize_window(pWindow, null, null, w, h);
//    
//  if(pUnit_Select_Dlg.pScroll) {
//    w -= n;
//  }
//
//  w -= (DOUBLE_FRAME_WH + 2);
//  
//  /* exit button */
//  pBuf = pWindow.prev; 
//  pBuf.size.x = pWindow.size.x + pWindow.size.w - pBuf.size.w - FRAME_WH - 1;
//  pBuf.size.y = pWindow.size.y + 1;
//  pBuf = pBuf.prev;
//  
//  setup_vertical_widgets_position(1, pWindow.size.x + FRAME_WH + 1,
//		  pWindow.size.y + WINDOW_TILE_HIGH + 1, w, 0,
//		  pUnit_Select_Dlg.pBeginActiveWidgetList, pBuf);
//    
//  if(pUnit_Select_Dlg.pScroll) {
//    setup_vertical_scrollbar_area(pUnit_Select_Dlg.pScroll,
//	pWindow.size.x + pWindow.size.w - FRAME_WH,
//    	pWindow.size.y + WINDOW_TILE_HIGH + 1,
//    	pWindow.size.h - (FRAME_WH + WINDOW_TILE_HIGH + 1), true);
//  }
//  
//  /* ==================================================== */
//  /* redraw */
//  redraw_group(pUnit_Select_Dlg.pBeginWidgetList, pWindow, 0);
//
//  flush_rect(pWindow.size);
//}
//
///* ====================================================================== */
///* ============================ TERRAIN INFO ============================ */
///* ====================================================================== */
//static SMALL_DLG pTerrain_Info_Dlg = null;
//
//
///**************************************************************************
//  Popdown terrain information dialog.
//**************************************************************************/
//static int terrain_info_window_dlg_callback(GUI pWindow)
//{
//  return std_move_window_group_callback(pTerrain_Info_Dlg.pBeginWidgetList,
//  								pWindow);
//}
//
///**************************************************************************
//  Popdown terrain information dialog.
//**************************************************************************/
//static void popdown_terrain_info_dialog()
//{
//  if (pTerrain_Info_Dlg) {
//    popdown_window_group_dialog(pTerrain_Info_Dlg.pBeginWidgetList,
//				pTerrain_Info_Dlg.pEndWidgetList);
//    FREE(pTerrain_Info_Dlg);
//    flush_dirty();
//  }
//}
//
///**************************************************************************
//  Popdown terrain information dialog.
//**************************************************************************/
//static int exit_terrain_info_dialog(GUI pButton)
//{
//  popdown_terrain_info_dialog();
//  return -1;
//}
//
///***************************************************************
//  Return a (static) string with terrain defense bonus;
//***************************************************************/
//final String sdl_get_tile_defense_info_text(tile pTile)
//{
//  static char buffer[64];
//  int bonus = (tile_types[pTile.terrain].defense_bonus - 10) * 10;
//  
//  if((pTile.special & Terrain_H.S_RIVER) == Terrain_H.S_RIVER) {
//    bonus += Map.terrain_control.river_defense_bonus;
//  }
//  if((pTile.special & Terrain_H.S_FORTRESS) == Terrain_H.S_FORTRESS) {
//    bonus += Map.terrain_control.fortress_defense_bonus;
//  }
//  
//  buffer = util.my_snprintf( "Terrain Defense Bonus: +%d%% ", bonus);
//  
//  return buffer;
//}
//
///***************************************************************
//  Return a (static) string with terrain name;
//  eg: "Hills \n Defense : + 100%"
//  eg: "Hills (Coals) \n Defense : + 100%"
//  eg: "Hills (Coals) \n [Pollution] \n Defense : + 100%"
//***************************************************************/
//final String sdl_map_get_tile_info_text(tile pTile)
//{
//  static char s[128];
//  boolean first;
//    
//  s = util.my_snprintf( "%s", tile_types[pTile.terrain].terrain_name);
//  if((pTile.special & Terrain_H.S_RIVER) == Terrain_H.S_RIVER) {
//    sz_strlcat(s, "/");
//    sz_strlcat(s, get_special_name(Terrain_H.S_RIVER));
//  }
//
//  first = true;
//  if ((pTile.special & S_SPECIAL_1) == S_SPECIAL_1) {
//    first = false;
//    cat_snprintf(s, sizeof(s), " (%s", tile_types[pTile.terrain].special_1_name);
//  }
//  if ((pTile.special & S_SPECIAL_2) == S_SPECIAL_2) {
//    if (first) {
//      first = false;
//      sz_strlcat(s, " (");
//    } else {
//      sz_strlcat(s, "/");
//    }
//    sz_strlcat(s, tile_types[pTile.terrain].special_2_name);
//  }
//  if (!first) {
//    sz_strlcat(s, ")");
//  }
//
//  first = true;
//  if ((pTile.special & S_POLLUTION) == S_POLLUTION) {
//    first = false;
//    cat_snprintf(s, sizeof(s), "\n[%s", get_special_name(S_POLLUTION));
//  }
//  if ((pTile.special & S_FALLOUT) == S_FALLOUT) {
//    if (first) {
//      first = false;
//      sz_strlcat(s, "\n[");
//    } else {
//      sz_strlcat(s, "/");
//    }
//    sz_strlcat(s, get_special_name(S_FALLOUT));
//  }
//  if (!first) {
//    sz_strlcat(s, "]");
//  }
//  
//  return s;
//}
//
///**************************************************************************
//  Popup terrain information dialog.
//**************************************************************************/
//static void popup_terrain_info_dialog(SDL_Surface *pDest,
//					tile pTile , int x , int y)
//{
//  SDL_Surface *pSurf;
//  GUI pBuf, *pWindow;
//  SDL_String16 *pStr;  
//  char cBuf[256];  
//
//  if (pTerrain_Info_Dlg) {
//    return;
//  }
//      
//  pSurf = get_terrain_surface(x, y);
//  pTerrain_Info_Dlg = MALLOC(sizeof(struct SMALL_DLG));
//    
//  /* ----------- */  
//  cBuf = util.my_snprintf( "%s [%d,%d]", "Terrain Info", x , y);
//  
//  pWindow = create_window(pDest, create_str16_from_char(cBuf , 12), 10, 10, 0);
//  pWindow.string16.style |= TTF_STYLE_BOLD;
//  
//  pWindow.action = terrain_info_window_dlg_callback;
//  set_wstate(pWindow, FC_WS_NORMAL);
//  
//  add_to_gui_list(ID_TERRAIN_INFO_DLG_WINDOW, pWindow);
//  pTerrain_Info_Dlg.pEndWidgetList = pWindow;
//  /* ---------- */
//  
//  if(tile_get_known(x, y) >= TILE_KNOWN_FOGGED) {
//  
//    cBuf = util.my_snprintf( "Terrain: %s\nFood/Prod/Trade: %s\n%s",
//		sdl_map_get_tile_info_text(pTile),
//		map_get_tile_fpt_text(x, y),
//    		sdl_get_tile_defense_info_text(pTile));
//        
//    if (Map.tile_has_special(pTile, Terrain_H.S_HUT))
//    { 
//      sz_strlcat(cBuf, "\nMinor Tribe Village");
//    }
//    else
//    {
//      if (get_tile_infrastructure_set(pTile))
//      {
//	cat_snprintf(cBuf, sizeof(cBuf), "\nInfrastructure: %s",
//				map_get_infrastructure_text(pTile.special));
//      }
//    }
//    
//    if (Game.game.borders > 0 && !pTile.city) {
//      player_diplstate ds = Game.game.player_ptr.diplstates;
//      final String diplo_nation_plural_adjectives[DS_LAST] =
//    			{Q"?nation:Neutral", Q"?nation:Hostile",
//     			"" /* unused, DS_CEASEFIRE*/, Q"?nation:Peaceful",
//			  Q"?nation:Friendly", Q"?nation:Mysterious"};
//			  
//      if (pTile.owner == Game.game.player_ptr){
//        cat_snprintf(cBuf, sizeof(cBuf), "\nOur Territory");
//      } else if (pTile.owner) {
//        if (ds[pTile.owner.player_no].type == DS_CEASEFIRE){
//	  int turns = ds[pTile.owner.player_no].turns_left;
//
//	  cat_snprintf(cBuf, sizeof(cBuf),
//	  		PL("\n%s territory (%d turn ceasefire)",
//			    "\n%s territory (%d turn ceasefire)", turns),
//		 		get_nation_name(pTile.owner.nation), turns);
//        } else {
//	  cat_snprintf(cBuf, sizeof(cBuf), "\nTerritory of the %s %s",
//		diplo_nation_plural_adjectives[ds[pTile.owner.player_no].type],
//		    	Nation.get_nation_name_plural(pTile.owner.nation));
//        }
//      } else {
//        cat_snprintf(cBuf, sizeof(cBuf), "\nUnclaimed territory");
//      }
//    }
//    
//    if (pTile.city) {
//      /* Look at city owner, not tile owner (the two should be the same, if
//       * borders are in use). */
//      player pOwner = City.city_owner(pTile.city);
//      player_diplstate ds = Game.game.player_ptr.diplstates;
//      final String diplo_city_adjectives[DS_LAST] =
//    		{Q"?city:Neutral", Q"?city:Hostile",
//     		"" /*unused, DS_CEASEFIRE */, Q"?city:Peaceful",
//		  Q"?city:Friendly", Q"?city:Mysterious"};
//		  
//      cat_snprintf(cBuf, sizeof(cBuf), "\nCity of %s", pTile.city.name);
//      if (City.city_got_citywalls(pTile.city)) {
//        cat_snprintf(cBuf, sizeof(cBuf), " with City Walls");
//      }		  
//      if (pOwner && pOwner != Game.game.player_ptr) {
//	if (ds[pOwner.player_no].type == DS_CEASEFIRE) {
//	  int turns = ds[pOwner.player_no].turns_left;
//
//          cat_snprintf(cBuf, sizeof(cBuf), PL("\n(%s, %d turn ceasefire)",
//				       "\n(%s, %d turn ceasefire)", turns),
//		 		get_nation_name(pOwner.nation), turns);
//        } else {
//          /* TRANS: "\nCity: <name> (<nation>,<diplomatic_state>)" */
//          cat_snprintf(cBuf, sizeof(cBuf), "\n(%s,%s)",
//		  get_nation_name(pOwner.nation),
//		  	diplo_city_adjectives[ds[pOwner.player_no].type]);
//	}
//      }
//    }
//  }
//  else
//  {
//    cBuf = util.my_snprintf( "Terrain : UNKNOWN");
//  }
//  
//   
//    
//  pStr = create_str16_from_char(cBuf, 12);
//  pStr.style |= SF_CENTER;
//  pBuf = create_iconlabel(pSurf, pWindow.dst, pStr, WF_FREE_THEME);
//  
//  pBuf.size.h += NORMAL_TILE_HEIGHT / 2;
//  
//  add_to_gui_list(ID_LABEL, pBuf);
//  
//  /* ------ window ---------- */
//  pWindow.size.w = pBuf.size.w + 20;
//  pWindow.size.h = pBuf.size.h + WINDOW_TILE_HIGH + 1 + FRAME_WH;
//
//  put_window_near_map_tile(pWindow, pWindow.size.w, pWindow.size.h, x, y);
//  resize_window(pWindow, null,
//	  get_game_colorRGB(COLOR_STD_BACKGROUND_BROWN),
//				  pWindow.size.w, pWindow.size.h);
//  
//  /* ------------------------ */
//  
//  pBuf.size.x = pWindow.size.x + 10;
//  pBuf.size.y = pWindow.size.y + WINDOW_TILE_HIGH + 1;
//  
//  pBuf = create_themeicon(pTheme.Small_CANCEL_Icon, pWindow.dst,
//  			  			WF_DRAW_THEME_TRANSPARENT);
//  pBuf.size.x = pWindow.size.x + pWindow.size.w - pBuf.size.w - FRAME_WH-1;
//  pBuf.size.y = pWindow.size.y + 1;
//  pBuf.action = exit_terrain_info_dialog;
//  set_wstate(pBuf, FC_WS_NORMAL);
//  pBuf.key = SDLK_ESCAPE;
//  
//  add_to_gui_list(ID_TERRAIN_INFO_DLG_EXIT_BUTTON, pBuf);
//    
//  pTerrain_Info_Dlg.pBeginWidgetList = pBuf;
//  /* --------------------------------- */
//  /* redraw */
//  redraw_group(pTerrain_Info_Dlg.pBeginWidgetList, pWindow, 0);
//  sdl_dirty_rect(pWindow.size);
//  flush_dirty();
//}
///* ====================================================================== */
///* ========================= ADVANCED_TERRAIN_MENU ====================== */
///* ====================================================================== */
//static ADVANCED_DLG pAdvanced_Terrain_Dlg = null;
//
///**************************************************************************
//  Popdown a generic dialog to display some generic information about
//  terrain : tile, units , cities, etc.
//**************************************************************************/
//static void popdown_advanced_terrain_dialog()
//{
//  if (pAdvanced_Terrain_Dlg) {
//    is_unit_move_blocked = false;
//    popdown_window_group_dialog(pAdvanced_Terrain_Dlg.pBeginWidgetList,
//			pAdvanced_Terrain_Dlg.pEndWidgetList);
//				   
//    FREE(pAdvanced_Terrain_Dlg.pScroll);
//    FREE(pAdvanced_Terrain_Dlg);
//  }
//}
//
///**************************************************************************
//  ...
//**************************************************************************/
//static int advanced_terrain_window_dlg_callback(GUI pWindow)
//{
//  return std_move_window_group_callback(pAdvanced_Terrain_Dlg.pBeginWidgetList,
//  								pWindow);
//}
//
///**************************************************************************
//  ...
//**************************************************************************/
//static int exit_advanced_terrain_dlg_callback(GUI pWidget)
//{
//  popdown_advanced_terrain_dialog();
//  flush_dirty();
//  return -1;
//}
//
///**************************************************************************
//  ...
//**************************************************************************/
//static int terrain_info_callback(GUI pWidget)
//{
//  int x = pWidget.data.cont.id0;
//  int y = pWidget.data.cont.id1;
//    
//  lock_buffer(pWidget.dst);  
//  popdown_advanced_terrain_dialog();
//
//  popup_terrain_info_dialog(get_locked_buffer(), map_get_tile(x , y), x, y);
//  unlock_buffer();
//  return -1;
//}
//
///**************************************************************************
//  ...
//**************************************************************************/
//static int zoom_to_city_callback(GUI pWidget)
//{
//  city pCity = pWidget.data.city;
//  
//  lock_buffer(pWidget.dst);
//  popdown_advanced_terrain_dialog();
//
//  popup_city_dialog(pCity, 0);
//  return -1;
//}
//
///**************************************************************************
//  ...
//**************************************************************************/
//static int change_production_callback(GUI pWidget)
//{
//  city pCity = pWidget.data.city;
//  popdown_advanced_terrain_dialog();
//  popup_worklist_editor(pCity, &(pCity.worklist));
//  return -1;
//}
//
///**************************************************************************
//  ...
//**************************************************************************/
//static int hurry_production_callback(GUI pWidget)
//{
//  city pCity = pWidget.data.city;
//  
//  lock_buffer(pWidget.dst);  
//  popdown_advanced_terrain_dialog();
//
//  popup_hurry_production_dialog(pCity, get_locked_buffer());
//  unlock_buffer();
//  return -1;
//}
//
///**************************************************************************
//  ...
//**************************************************************************/
//static int cma_callback(GUI pWidget)
//{
//  city pCity = pWidget.data.city;
//  popdown_advanced_terrain_dialog();
//  popup_city_cma_dialog(pCity);
//  return -1;
//}
//
///**************************************************************************
//...
//**************************************************************************/
//static int adv_unit_select_callback(GUI pWidget)
//{
//  unit pUnit = pWidget.data.unit;
//
//  popdown_advanced_terrain_dialog();
//  
//  if (pUnit) {
//    request_new_unit_activity(pUnit, unit_activity.ACTIVITY_IDLE);
//    set_unit_focus(pUnit);
//  }
//
//  return -1;
//}
//
///**************************************************************************
//...
//**************************************************************************/
//static int adv_unit_select_all_callback(GUI pWidget)
//{
//  unit pUnit = pWidget.data.unit;
//
//  popdown_advanced_terrain_dialog();
//  
//  if (pUnit) {
//    activate_all_units(pUnit.x, pUnit.y);
//  }
//  return -1;
//}
//
///**************************************************************************
//...
//**************************************************************************/
//static int adv_unit_sentry_idle_callback(GUI pWidget)
//{
//  unit pUnit = pWidget.data.unit;
//
//  popdown_advanced_terrain_dialog();
//  
//  if (pUnit) {
//    tile ptile = map_get_tile(pUnit.x, pUnit.y);
//    for (unit punit : ptile.units.data) {
//      if (Game.game.player_idx == punit.owner && (punit.activity == unit_activity.ACTIVITY_IDLE)
//	 && !punit.ai.control && can_unit_do_activity(punit, ACTIVITY_SENTRY)) {
//        request_new_unit_activity(punit, ACTIVITY_SENTRY);
//      }
//    } }
//  }
//  return -1;
//}
//
///**************************************************************************
//  ...
//**************************************************************************/
//static int goto_here_callback(GUI pWidget)
//{
//  int x = pWidget.data.cont.id0;
//  int y = pWidget.data.cont.id1;
//    
//  popdown_advanced_terrain_dialog();
//  
//  /* may not work */
//  send_goto_unit(get_unit_in_focus(), x, y);
//  return -1;
//}
//
//
///**************************************************************************
//  ...
//**************************************************************************/
//static int patrol_here_callback(GUI pWidget)
//{
//  int x = pWidget.data.cont.id0;
//  int y = pWidget.data.cont.id1;
//  unit pUnit = get_unit_in_focus();
//    
//  popdown_advanced_terrain_dialog();
//  
//  if(pUnit) {
//    enter_goto_state(pUnit);
//    /* may not work */
//    do_unit_patrol_to(pUnit, x, y);
//    exit_goto_state();
//  }
//  return -1;
//}
//
///**************************************************************************
//  ...
//**************************************************************************/
//static int paradrop_here_callback(GUI pWidget)
//{
//  int x = pWidget.data.cont.id0;
//  int y = pWidget.data.cont.id1;
//    
//  popdown_advanced_terrain_dialog();
//  
//  /* may not work */
//  do_unit_paradrop_to(get_unit_in_focus(), x, y);
//  return -1;
//}
//
///**************************************************************************
//  ...
//**************************************************************************/
//static int unit_help_callback(GUI pWidget)
//{
//  int unit_id = MAX_ID - pWidget.ID;
//    
//  popdown_advanced_terrain_dialog();
//  popup_unit_info(unit_id);
//  return -1;
//}
//
//
///**************************************************************************
//  Popup a generic dialog to display some generic information about
//  terrain : tile, units , cities, etc.
//**************************************************************************/
//void popup_advanced_terrain_dialog(int x , int y)
//{
//  GUI pWindow = null, *pBuf = null;
//  tile pTile;
//  city pCity;
//  unit pFocus_Unit;
//  SDL_String16 *pStr;
//  SDL_Rect area;
//  CONTAINER pCont;
//  char cBuf[255]; 
//  int n, w = 0, h, units_h = 0;
//    
//  if (pAdvanced_Terrain_Dlg) {
//    return;
//  }
//  
//  pTile = map_get_tile(x, y);
//  pCity = pTile.city;
//  n = pTile.units.foo_list_size();
//  pFocus_Unit = get_unit_in_focus();
//  
//  if (!n && !pCity && !pFocus_Unit)
//  {
//    popup_terrain_info_dialog(null, pTile, x, y);
//    return;
//  }
//    
//  h = WINDOW_TILE_HIGH + 3 + FRAME_WH;
//  is_unit_move_blocked = true;
//    
//  pAdvanced_Terrain_Dlg = MALLOC(sizeof(struct ADVANCED_DLG));
//  
//  pCont = MALLOC(sizeof(struct CONTAINER));
//  pCont.id0 = x;
//  pCont.id1 = y;
//  
//  pStr = create_str16_from_char("Advanced Menu" , 12);
//  pStr.style |= TTF_STYLE_BOLD;
//  
//  pWindow = create_window(null, pStr, 10, 10, WF_DRAW_THEME_TRANSPARENT);
//    
//  pWindow.action = advanced_terrain_window_dlg_callback;
//  set_wstate(pWindow , FC_WS_NORMAL);
//  w = MAX(w , pWindow.size.w);
//  
//  add_to_gui_list(ID_TERRAIN_ADV_DLG_WINDOW, pWindow);
//  pAdvanced_Terrain_Dlg.pEndWidgetList = pWindow;
//  /* ---------- */
//  /* exit button */
//  pBuf = create_themeicon(pTheme.Small_CANCEL_Icon, pWindow.dst,
//  			  			WF_DRAW_THEME_TRANSPARENT);
//  
//  w += pBuf.size.w + 10;
//  pBuf.action = exit_advanced_terrain_dlg_callback;
//  set_wstate(pBuf, FC_WS_NORMAL);
//  pBuf.key = SDLK_ESCAPE;
//  
//  add_to_gui_list(ID_TERRAIN_ADV_DLG_EXIT_BUTTON, pBuf);
//  /* ---------- */
//  
//  pStr = create_str16_from_char("Terrain Info" , 10);
//  pStr.style |= TTF_STYLE_BOLD;
//   
//  pBuf = create_iconlabel(null, pWindow.dst, pStr , 
//    (WF_DRAW_THEME_TRANSPARENT|WF_DRAW_TEXT_LABEL_WITH_SPACE|WF_FREE_DATA));
//
//  pBuf.string16.render = 3;
//  pBuf.string16.bgcol.unused = 128;
//    
//  pBuf.data.cont = pCont;
//  
//  pBuf.action = terrain_info_callback;
//  set_wstate(pBuf, FC_WS_NORMAL);
//  
//  add_to_gui_list(ID_LABEL, pBuf);
//    
//  w = MAX(w, pBuf.size.w);
//  h += pBuf.size.h;
//
//  /* ---------- */  
//  if (pCity && pCity.owner == Game.game.player_idx)
//  {
//    /* separator */
//    pBuf = create_iconlabel(null, pWindow.dst, null, WF_FREE_THEME);
//    
//    add_to_gui_list(ID_SEPARATOR, pBuf);
//    h += pBuf.next.size.h;
//    /* ------------------ */
//    
//    cBuf = util.my_snprintf( "Zoom to : %s", pCity.name );
//    
//    create_active_iconlabel(pBuf, pWindow.dst,
//		    pStr, cBuf, zoom_to_city_callback);
//    pBuf.data.city = pCity;
//    set_wstate(pBuf, FC_WS_NORMAL);
//  
//    add_to_gui_list(ID_LABEL, pBuf);
//    
//    w = MAX(w, pBuf.size.w);
//    h += pBuf.size.h;
//    /* ----------- */
//    
//    create_active_iconlabel(pBuf, pWindow.dst, pStr,
//	    "Change Production", change_production_callback);
//	    
//    pBuf.data.city = pCity;
//    set_wstate(pBuf, FC_WS_NORMAL);
//  
//    add_to_gui_list(ID_LABEL, pBuf);
//    
//    w = MAX(w, pBuf.size.w);
//    h += pBuf.size.h;
//    /* -------------- */
//    
//    create_active_iconlabel(pBuf, pWindow.dst, pStr,
//	    "Hurry production", hurry_production_callback);
//	    
//    pBuf.data.city = pCity;
//    set_wstate(pBuf, FC_WS_NORMAL);
//  
//    add_to_gui_list(ID_LABEL, pBuf);
//    
//    w = MAX(w, pBuf.size.w);
//    h += pBuf.size.h;
//    /* ----------- */
//  
//    create_active_iconlabel(pBuf, pWindow.dst, pStr,
//	    "Change C.M.A Settings", cma_callback);
//	    
//    pBuf.data.city = pCity;
//    set_wstate(pBuf, FC_WS_NORMAL);
//  
//    add_to_gui_list(ID_LABEL, pBuf);
//    
//    w = MAX(w, pBuf.size.w);
//    h += pBuf.size.h;
//    
//  }
//  /* ---------- */
//  
//  if(pFocus_Unit && (pFocus_Unit.x != x || pFocus_Unit.y != y)) {
//    /* separator */
//    pBuf = create_iconlabel(null, pWindow.dst, null, WF_FREE_THEME);
//    
//    add_to_gui_list(ID_SEPARATOR, pBuf);
//    h += pBuf.next.size.h;
//    /* ------------------ */
//        
//    create_active_iconlabel(pBuf, pWindow.dst, pStr, "Goto here",
//						    goto_here_callback);
//    pBuf.data.cont = pCont;
//    set_wstate(pBuf, FC_WS_NORMAL);
//        
//    add_to_gui_list(MAX_ID - 1000 - pFocus_Unit.id, pBuf);
//    
//    w = MAX(w, pBuf.size.w);
//    h += pBuf.size.h;
//    /* ----------- */
//    
//    create_active_iconlabel(pBuf, pWindow.dst, pStr, "Patrol here",
//						    patrol_here_callback);
//    pBuf.data.cont = pCont;
//    set_wstate(pBuf, FC_WS_NORMAL);
//        
//    add_to_gui_list(MAX_ID - 1000 - pFocus_Unit.id, pBuf);
//    
//    w = MAX(w, pBuf.size.w);
//    h += pBuf.size.h;
//    /* ----------- */
//
//#if 0 /* FIXME: specific connect buttons */
//    if(unit_flag(pFocus_Unit, Eunit_flag_id.F_SETTLERS)) {
//      create_active_iconlabel(pBuf, pWindow.dst, pStr, "Connect here",
//						    connect_here_callback);
//      pBuf.data.cont = pCont;
//      set_wstate(pBuf, FC_WS_NORMAL);
//  
//      add_to_gui_list(ID_LABEL, pBuf);
//    
//      w = MAX(w, pBuf.size.w);
//      h += pBuf.size.h;
//      
//    }
//#endif
//
//    if(can_unit_paradrop(pFocus_Unit) && pTile.known &&
//      !(Terrain_H.is_ocean(pTile.terrain) && is_ground_unit(pFocus_Unit)) &&
//      !(Unit.is_sailing_unit(pFocus_Unit) && (!Terrain_H.is_ocean(pTile.terrain) || !pCity)) &&
//      !(((pCity && pplayers_non_attack(Game.game.player_ptr, City.city_owner(pCity))) 
//      || is_non_attack_unit_tile(pTile, Game.game.player_ptr))) &&
//      (pFocus_Unit.unit_type().paratroopers_range >=
//	    Map.real_map_distance(pFocus_Unit.x, pFocus_Unit.y, x, y))) {
//	      
//      create_active_iconlabel(pBuf, pWindow.dst, pStr, "Paradrop here",
//						    paradrop_here_callback);
//      pBuf.data.cont = pCont;
//      set_wstate(pBuf, FC_WS_NORMAL);
//  
//      add_to_gui_list(ID_LABEL, pBuf);
//    
//      w = MAX(w, pBuf.size.w);
//      h += pBuf.size.h;
//      
//    }
//
//  }
//  pAdvanced_Terrain_Dlg.pBeginWidgetList = pBuf;
//  
//  /* ---------- */
//  if (n)
//  {
//    int i;
//    unit pUnit;
//    unit_type pUnitType = null;
//    units_h = 0;  
//    /* separator */
//    pBuf = create_iconlabel(null, pWindow.dst, null, WF_FREE_THEME);
//    
//    add_to_gui_list(ID_SEPARATOR, pBuf);
//    h += pBuf.next.size.h;
//    /* ---------- */
//    if (n > 1)
//    {
//      unit pDefender, *pAttacker;
//      GUI pLast = pBuf;
//      SDL_Color BLACK = {0, 0, 0, 255};
//      boolean reset = false;
//      int my_units = 0;
//      
//      public static final int ADV_NUM_SEEN = 15;
//      
//      pDefender = (pFocus_Unit ? get_defender(pFocus_Unit, x, y) : null);
//      pAttacker = (pFocus_Unit ? get_attacker(pFocus_Unit, x, y) : null);
//      for(i=0; i<n; i++) {
//        pUnit = unit_list_get(&pTile.units, i);
//	if (pUnit == pFocus_Unit) {
//	  continue;
//	}
//        pUnitType = pUnit.unit_type();
//        if(pUnit.owner == Game.game.player_idx) {
//          cBuf = util.my_snprintf(
//            "Activate %s (%d / %d) %s (%d,%d,%d) %s",
//            pUnit.veteran ? "Veteran" : "" ,
//            pUnit.hp, pUnitType.hp,
//            pUnitType.name,
//            pUnitType.attack_strength,
//            pUnitType.defense_strength,
//            (pUnitType.move_rate / Unit_H.SINGLE_MOVE),
//	    unit_activity_text(pUnit));
//    
//	  create_active_iconlabel(pBuf, pWindow.dst, pStr,
//	       cBuf, adv_unit_select_callback);
//          pBuf.data.unit = pUnit;
//          set_wstate(pBuf, FC_WS_NORMAL);
//	  add_to_gui_list(ID_LABEL, pBuf);
//	  my_units++;
//	} else {
//	  int att_chance, def_chance;
//	  
//          cBuf = util.my_snprintf( "%s %s %s (A:%d D:%d M:%d FP:%d) HP:%d%%",
//            Nation.get_nation_by_plr(pUnit.unit_owner()).name,
//            (pUnit.veteran ? "Veteran" : ""),
//            pUnitType.name,
//            pUnitType.attack_strength,
//            pUnitType.defense_strength,
//            (pUnitType.move_rate / Unit_H.SINGLE_MOVE),
//      	    pUnitType.firepower,
//	    ((pUnit.hp * 100) / pUnitType.hp));
//    
//          /* calculate chance to win */
//          if (sdl_get_chance_to_win(&att_chance, &def_chance, pUnit, pFocus_Unit)) {
//            cat_snprintf(cBuf, sizeof(cBuf), " CtW: Att:%d%% Def:%d%%",
//               att_chance, def_chance);
//	  }
//	  
//	  if (pAttacker && pAttacker == pUnit) {
//	    pStr.fgcol = *(get_game_colorRGB(COLOR_STD_RED));
//	    reset = true;
//	  } else {
//	    if (pDefender && pDefender == pUnit) {
//	      pStr.fgcol = *(get_game_colorRGB(COLOR_STD_GROUND));
//	      reset = true;
//	    }
//	  }
//	  
//	  create_active_iconlabel(pBuf, pWindow.dst, pStr, cBuf, null);
//          
//	  if (reset) {
//	    pStr.fgcol = BLACK;
//	    reset = false;
//	  }
//	  
//	  add_to_gui_list(ID_LABEL, pBuf);
//	}
//	    
//        w = MAX(w, pBuf.size.w);
//        units_h += pBuf.size.h;
//	
//        if (i > ADV_NUM_SEEN - 1)
//        {
//          set_wflag(pBuf, WF_HIDDEN);
//        }
//        
//      }
//      
//      pAdvanced_Terrain_Dlg.pEndActiveWidgetList = pLast.prev;
//      pAdvanced_Terrain_Dlg.pActiveWidgetList = pLast.prev;
//      pAdvanced_Terrain_Dlg.pBeginWidgetList = pBuf;
//      pAdvanced_Terrain_Dlg.pBeginActiveWidgetList = pBuf;
//            
//      if(n > ADV_NUM_SEEN)
//      {
//        units_h = ADV_NUM_SEEN * pBuf.size.h;
//	n = create_vertical_scrollbar(pAdvanced_Terrain_Dlg,
//					1, ADV_NUM_SEEN, true, true);
//	w += n;
//      }
//
//      if (my_units > 1) {
//	
//	cBuf = util.my_snprintf( "%s (%d)", "Ready all", my_units);
//	create_active_iconlabel(pBuf, pWindow.dst, pStr,
//	       cBuf, adv_unit_select_all_callback);
//        pBuf.data.unit = pAdvanced_Terrain_Dlg.pEndActiveWidgetList.data.unit;
//        set_wstate(pBuf, FC_WS_NORMAL);
//	pBuf.ID = ID_LABEL;
//	DownAdd(pBuf, pLast);
//	h += pBuf.size.h;
//	
//	cBuf = util.my_snprintf( "%s (%d)", "Sentry idle", my_units);
//	create_active_iconlabel(pBuf, pWindow.dst, pStr,
//	       cBuf, adv_unit_sentry_idle_callback);
//        pBuf.data.unit = pAdvanced_Terrain_Dlg.pEndActiveWidgetList.data.unit;
//        set_wstate(pBuf, FC_WS_NORMAL);
//	pBuf.ID = ID_LABEL;
//	DownAdd(pBuf, pLast.prev);
//	h += pBuf.size.h;
//	
//	/* separator */
//        pBuf = create_iconlabel(null, pWindow.dst, null, WF_FREE_THEME);
//        pBuf.ID = ID_SEPARATOR;
//	DownAdd(pBuf, pLast.prev.prev);
//        h += pBuf.next.size.h;
//	  
//      }
//      #undef ADV_NUM_SEEN
//    }
//    else
//    { /* n == 1 */
//      /* one unit - give orders */
//      pUnit = unit_list_get(&pTile.units, 0);
//      pUnitType = pUnit.unit_type();
//      if (pUnit != pFocus_Unit) {
//        if ((pCity && pCity.owner == Game.game.player_idx) ||
//	   (pUnit.owner == Game.game.player_idx))
//        {
//          cBuf = util.my_snprintf(
//            "Activate %s (%d / %d) %s (%d,%d,%d) %s",
//            pUnit.veteran ? "Veteran" : "" ,
//            pUnit.hp, pUnitType.hp,
//            pUnitType.name,
//            pUnitType.attack_strength,
//            pUnitType.defense_strength,
//            (pUnitType.move_rate / Unit_H.SINGLE_MOVE),
//	    unit_activity_text(pUnit));
//    
//	  create_active_iconlabel(pBuf, pWindow.dst, pStr,
//	    		cBuf, adv_unit_select_callback);
//	  pBuf.data.unit = pUnit;
//          set_wstate(pBuf, FC_WS_NORMAL);
//	
//          add_to_gui_list(ID_LABEL, pBuf);
//    
//          w = MAX(w, pBuf.size.w);
//          units_h += pBuf.size.h;
//	  /* ---------------- */
//	  /* separator */
//          pBuf = create_iconlabel(null, pWindow.dst, null, WF_FREE_THEME);
//    
//          add_to_gui_list(ID_SEPARATOR, pBuf);
//          h += pBuf.next.size.h;
//        } else {
//	  int att_chance, def_chance;
//	
//          cBuf = util.my_snprintf( "%s %s %s (A:%d D:%d M:%d FP:%d) HP:%d%%",
//            Nation.get_nation_by_plr(pUnit.unit_owner()).name,
//            (pUnit.veteran ? "Veteran" : ""),
//            pUnitType.name,
//            pUnitType.attack_strength,
//            pUnitType.defense_strength,
//            (pUnitType.move_rate / Unit_H.SINGLE_MOVE),
//      	    pUnitType.firepower,
//	    ((pUnit.hp * 100) / pUnitType.hp));
//    
//	    /* calculate chance to win */
//            if (sdl_get_chance_to_win(&att_chance, &def_chance, pUnit, pFocus_Unit)) {
//              cat_snprintf(cBuf, sizeof(cBuf), " CtW: Att:%d%% Def:%d%%",
//                 att_chance, def_chance);
//	    }
//	    create_active_iconlabel(pBuf, pWindow.dst, pStr, cBuf, null);          
//	    add_to_gui_list(ID_LABEL, pBuf);
//            w = MAX(w, pBuf.size.w);
//            units_h += pBuf.size.h;
//	    /* ---------------- */
//	    
//	    /* separator */
//            pBuf = create_iconlabel(null, pWindow.dst, null, WF_FREE_THEME);
//    
//            add_to_gui_list(ID_SEPARATOR, pBuf);
//            h += pBuf.next.size.h;
//	
//        }
//      }
//      /* ---------------- */
//      cBuf = util.my_snprintf(
//            "View Civiliopedia entry for %s", pUnitType.name);
//      create_active_iconlabel(pBuf, pWindow.dst, pStr,
//	    cBuf, unit_help_callback);
//      set_wstate(pBuf , FC_WS_NORMAL);
//      add_to_gui_list(MAX_ID - pUnit.type, pBuf);
//    
//      w = MAX(w, pBuf.size.w);
//      units_h += pBuf.size.h;
//      /* ---------------- */  
//      pAdvanced_Terrain_Dlg.pBeginWidgetList = pBuf;
//    }
//    
//  }
//  /* ---------- */
//  
//  w += (DOUBLE_FRAME_WH + 2);
//  
//  h += units_h;
//  
//  put_window_near_map_tile(pWindow, w, h, x, y);      
//  resize_window(pWindow, null, null, w, h);
//  
//  w -= (DOUBLE_FRAME_WH + 2);
//  
//  if (pAdvanced_Terrain_Dlg.pScroll)
//  {
//    units_h = n;
//  }
//  else
//  {
//    units_h = 0;
//  }
//  
//  /* exit button */
//  pBuf = pWindow.prev;
//  
//  pBuf.size.x = pWindow.size.x + pWindow.size.w-pBuf.size.w-FRAME_WH-1;
//  pBuf.size.y = pWindow.size.y + 1;
//  
//  /* terrain info */
//  pBuf = pBuf.prev;
//  
//  pBuf.size.x = pWindow.size.x + FRAME_WH + 1;
//  pBuf.size.y = pWindow.size.y + WINDOW_TILE_HIGH + 2;
//  pBuf.size.w = w;
//  h = pBuf.size.h;
//  
//  area.x = 10;
//  area.h = 2;
//  
//  pBuf = pBuf.prev;
//  while(pBuf)
//  {
//    
//    if (pBuf == pAdvanced_Terrain_Dlg.pEndActiveWidgetList)
//    {
//      w -= units_h;
//    }
//    
//    pBuf.size.w = w;
//    pBuf.size.x = pBuf.next.size.x;
//    pBuf.size.y = pBuf.next.size.y + pBuf.next.size.h;
//    
//    if (pBuf.ID == ID_SEPARATOR)
//    {
//      FREESURFACE(pBuf.theme);
//      pBuf.size.h = h;
//      pBuf.theme = create_surf(w , h , SDL_SWSURFACE);
//    
//      area.y = pBuf.size.h / 2 - 1;
//      area.w = pBuf.size.w - 20;
//      
//      SDL_FillRect(pBuf.theme, &area, 64);
//      SDL_SetColorKey(pBuf.theme, SDL_SRCCOLORKEY|SDL_RLEACCEL, 0x0);
//    }
//    
//    if(pBuf == pAdvanced_Terrain_Dlg.pBeginWidgetList || 
//      pBuf == pAdvanced_Terrain_Dlg.pBeginActiveWidgetList) {
//      break;
//    }
//    pBuf = pBuf.prev;
//  }
//  
//  if (pAdvanced_Terrain_Dlg.pScroll)
//  {
//    setup_vertical_scrollbar_area(pAdvanced_Terrain_Dlg.pScroll,
//	pWindow.size.x + pWindow.size.w - FRAME_WH,
//    	pAdvanced_Terrain_Dlg.pEndActiveWidgetList.size.y,
//    	pWindow.size.y - pAdvanced_Terrain_Dlg.pEndActiveWidgetList.size.y +
//	pWindow.size.h - FRAME_WH, true);
//  }
//  
//  /* -------------------- */
//  /* redraw */
//  redraw_group(pAdvanced_Terrain_Dlg.pBeginWidgetList, pWindow, 0);
//
//  flush_rect(pWindow.size);
//}
//
///* ====================================================================== */
///* ============================ CARAVAN DIALOG ========================== */
///* ====================================================================== */
//static SMALL_DLG pCaravan_Dlg = null;
//
//static int caravan_dlg_window_callback(GUI pWindow)
//{
//  return std_move_window_group_callback(pCaravan_Dlg.pBeginWidgetList, pWindow);
//}
//
///****************************************************************
//...
//*****************************************************************/
//static int caravan_establish_trade_callback(GUI pWidget)
//{
//  popdown_caravan_dialog();
//
//  dsend_packet_unit_establish_trade(&aconnection, pWidget.data.cont.id0);
//  return -1;
//}
//
//
///****************************************************************
//...
//*****************************************************************/
//static int caravan_help_build_wonder_callback(GUI pWidget)
//{
//  popdown_caravan_dialog();
//
//  dsend_packet_unit_help_build_wonder(&aconnection, pWidget.data.cont.id0);
//  return -1;
//}
//
//static int exit_caravan_dlg_callback(GUI pWidget)
//{
//  popdown_caravan_dialog();
//  process_caravan_arrival(null);
//  return -1;
//}
//  
//static void popdown_caravan_dialog()
//{
//  if (pCaravan_Dlg) {
//    is_unit_move_blocked = false;
//    popdown_window_group_dialog(pCaravan_Dlg.pBeginWidgetList,
//				pCaravan_Dlg.pEndWidgetList);
//    FREE(pCaravan_Dlg);
//    flush_dirty();
//  }
//}
//
///**************************************************************************
//  Popup a dialog giving a player choices when their caravan arrives at
//  a city (other than its home city).  Example:
//    - Establish traderoute.
//    - Help build wonder.
//    - Keep moving.
//**************************************************************************/
//void popup_caravan_dialog(unit pUnit,
//			  city pHomecity, city pDestcity)
//{
//  GUI pWindow = null, *pBuf = null;
//  SDL_String16 *pStr;
//  int w = 0, h;
//  CONTAINER pCont;
//  char cBuf[128];
//  
//  if (pCaravan_Dlg) {
//    return;
//  }
//  
//  pCont = MALLOC(sizeof(struct CONTAINER));
//  pCont.id0 = pUnit.id;
//  pCont.id1 = pDestcity.id;
//  
//  pCaravan_Dlg = MALLOC(sizeof(struct SMALL_DLG));
//  is_unit_move_blocked = true;
//  h = WINDOW_TILE_HIGH + 3 + FRAME_WH;
//      
//  cBuf = util.my_snprintf( "Your caravan has arrived at %s",
//							  pDestcity.name);
//  
//  /* window */
//  pStr = create_str16_from_char(cBuf, 12);
//  pStr.style |= TTF_STYLE_BOLD;
//  
//  pWindow = create_window(null, pStr, 10, 10, WF_DRAW_THEME_TRANSPARENT);
//    
//  pWindow.action = caravan_dlg_window_callback;
//  set_wstate(pWindow, FC_WS_NORMAL);
//  w = MAX(w, pWindow.size.w);
//  
//  add_to_gui_list(ID_CARAVAN_DLG_WINDOW, pWindow);
//  pCaravan_Dlg.pEndWidgetList = pWindow;
//    
//  /* ---------- */
//  if (City.can_cities_trade(pHomecity, pDestcity))
//  {
//    int revenue = get_caravan_enter_city_trade_bonus(pHomecity, pDestcity);
//    
//    if (City.can_establish_trade_route(pHomecity, pDestcity)) {
//      cBuf = util.my_snprintf(
//      		"Establish Traderoute with %s ( %d R&G + %d trade )",
//      		pHomecity.name, revenue,
//      			trade_between_cities(pHomecity, pDestcity));
//    } else {
//      revenue = (revenue + 2) / 3;
//      cBuf = util.my_snprintf(
//		"Enter Marketplace ( %d R&G bonus )", revenue);
//    }
//    
//    create_active_iconlabel(pBuf, pWindow.dst, pStr,
//	    cBuf, caravan_establish_trade_callback);
//    pBuf.data.cont = pCont;
//    set_wstate(pBuf, FC_WS_NORMAL);
//  
//    add_to_gui_list(ID_LABEL, pBuf);
//    
//    w = MAX(w, pBuf.size.w);
//    h += pBuf.size.h;
//  }
//  
//  /* ---------- */
//  if (unit_can_help_build_wonder(pUnit, pDestcity)) {
//        
//    create_active_iconlabel(pBuf, pWindow.dst, pStr,
//	"Help build Wonder", caravan_help_build_wonder_callback);
//    
//    pBuf.data.cont = pCont;
//    set_wstate(pBuf, FC_WS_NORMAL);
//  
//    add_to_gui_list(ID_LABEL, pBuf);
//    
//    w = MAX(w, pBuf.size.w);
//    h += pBuf.size.h;
//  }
//  /* ---------- */
//  
//  create_active_iconlabel(pBuf, pWindow.dst, pStr,
//	    "Keep moving", exit_caravan_dlg_callback);
//  
//  pBuf.data.cont = pCont;
//  set_wstate(pBuf, FC_WS_NORMAL);
//  set_wflag(pBuf, WF_FREE_DATA);
//  pBuf.key = SDLK_ESCAPE;
//  
//  add_to_gui_list(ID_LABEL, pBuf);
//    
//  w = MAX(w, pBuf.size.w);
//  h += pBuf.size.h;
//  /* ---------- */
//  pCaravan_Dlg.pBeginWidgetList = pBuf;
//  
//  /* setup window size and start position */
//  
//  pWindow.size.w = w + DOUBLE_FRAME_WH;
//  pWindow.size.h = h;
//  
//  auto_center_on_focus_unit();
//  put_window_near_map_tile(pWindow,
//  		w + DOUBLE_FRAME_WH, h, pUnit.x, pUnit.y);
//  resize_window(pWindow, null, null, pWindow.size.w, h);
//  
//  /* setup widget size and start position */
//    
//  pBuf = pWindow.prev;
//  setup_vertical_widgets_position(1,
//	pWindow.size.x + FRAME_WH,
//  	pWindow.size.y + WINDOW_TILE_HIGH + 2, w, 0,
//	pCaravan_Dlg.pBeginWidgetList, pBuf);
//  /* --------------------- */
//  /* redraw */
//  redraw_group(pCaravan_Dlg.pBeginWidgetList, pWindow, 0);
//
//  flush_rect(pWindow.size);
//}
//
///**************************************************************************
//  Is there currently a caravan dialog open?  This is important if there
//  can be only one such dialog at a time; otherwise return false.
//**************************************************************************/
//boolean caravan_dialog_is_open()
//{
//  return pCaravan_Dlg != null;
//}
//
///* ====================================================================== */
///* ============================ DIPLOMAT DIALOG ========================= */
///* ====================================================================== */
//static ADVANCED_DLG pDiplomat_Dlg = null;
//
///****************************************************************
//...
//*****************************************************************/
//static int diplomat_dlg_window_callback(GUI pWindow)
//{
//  return std_move_window_group_callback(pDiplomat_Dlg.pBeginWidgetList, pWindow);
//}
//
///****************************************************************
//...
//*****************************************************************/
//static int diplomat_embassy_callback(GUI pWidget)
//{
//  city pCity = pWidget.data.city;
//  int id = MAX_ID - pWidget.ID;
//  
//  popdown_diplomat_dialog();
//  if(pCity && Game.find_unit_by_id(id)) { 
//    request_diplomat_action(DIPLOMAT_EMBASSY, id, pCity.id, 0);
//  }
//
//  process_diplomat_arrival(null, 0);
//  return -1;
//}
//
///****************************************************************
//...
//*****************************************************************/
//static int diplomat_investigate_callback(GUI pWidget)
//{
//  city pCity = pWidget.data.city;
//  int id = MAX_ID - pWidget.ID;
//
//  lock_buffer(pWidget.dst);
//  popdown_diplomat_dialog();
//  if(pCity && Game.find_unit_by_id(id)) { 
//    request_diplomat_action(DIPLOMAT_INVESTIGATE, id, pCity.id, 0);
//  }
//
//  process_diplomat_arrival(null, 0);
//  return -1;
//}
//
///****************************************************************
//...
//*****************************************************************/
//static int spy_poison_callback( GUI pWidget )
//{
//  city pCity = pWidget.data.city;
//  int id = MAX_ID - pWidget.ID;
//
//  popdown_diplomat_dialog();
//  if(pCity && Game.find_unit_by_id(id)) { 
//    request_diplomat_action(SPY_POISON, id, pCity.id, 0);
//  }
//
//  process_diplomat_arrival(null, 0);
//  return -1;
//}
//
///****************************************************************
// Requests up-to-date list of improvements, the return of
// which will trigger the popup_sabotage_dialog() function.
//*****************************************************************/
//static int spy_sabotage_request(GUI pWidget)
//{
//  city pCity = pWidget.data.city;
//  int id = MAX_ID - pWidget.ID;
//  
//  lock_buffer(pWidget.dst);
//  popdown_diplomat_dialog();
//  if(pCity && Game.find_unit_by_id(id)) {
//    request_diplomat_action(SPY_GET_SABOTAGE_LIST, id, pCity.id, 0);
//  }
//  return -1;
//}
//
///****************************************************************
//...
//*****************************************************************/
//static int diplomat_sabotage_callback(GUI pWidget)
//{
//  city pCity = pWidget.data.city;
//  int id = MAX_ID - pWidget.ID;
//  
//  popdown_diplomat_dialog();
//  if(pCity && Game.find_unit_by_id(id)) { 
//    request_diplomat_action(DIPLOMAT_SABOTAGE, id, pCity.id, -1);
//  }
//
//  process_diplomat_arrival(null, 0);
//  return -1;
//}
///* --------------------------------------------------------- */
//
//static int spy_steal_dlg_window_callback(GUI pWindow)
//{
//  return std_move_window_group_callback(pDiplomat_Dlg.pBeginWidgetList, pWindow);
//}
//
//static int exit_spy_steal_dlg_callback(GUI pWidget)
//{
//  popdown_diplomat_dialog();
//  process_diplomat_arrival(null, 0);
//  return -1;  
//}
//
//static int spy_steal_callback(GUI pWidget)
//{
//  int steal_advance = MAX_ID - pWidget.ID;
//  int diplomat_target_id = pWidget.data.cont.id0;
//  int diplomat_id = pWidget.data.cont.id1;
//    
//  popdown_diplomat_dialog();
//  if(Game.find_unit_by_id(diplomat_id) && 
//    Game.find_city_by_id(diplomat_target_id)) { 
//    request_diplomat_action(DIPLOMAT_STEAL, diplomat_id,
//			    diplomat_target_id, steal_advance);
//  }
//
//  process_diplomat_arrival(null, 0);
//  return -1;
//}
//
///**************************************************************************
//  ...
//**************************************************************************/
//static int spy_steal_popup(GUI pWidget)
//{
//  city pVcity = pWidget.data.city;
//  int id = MAX_ID - pWidget.ID;
//  player pVictim = null;
//  CONTAINER pCont;
//  GUI pBuf = null;
//  GUI pWindow;
//  SDL_String16 *pStr;
//  SDL_Surface *pSurf;
//  int max_col, max_row, col, i, count = 0, w = 0, h;
//
//  lock_buffer(pWidget.dst);
//  popdown_diplomat_dialog();
//  
//  if(pVcity)
//  {
//    pVictim = City.city_owner(pVcity);
//  }
//  
//  if (pDiplomat_Dlg || !pVictim) {
//    remove_locked_buffer();
//    return 1;
//  }
//  
//  count = 0;
//  for(i=A_FIRST; i<Game.game.num_tech_types; i++) {
//    if (tech_is_available(Game.game.player_ptr, i)
//      && get_invention(pVictim, i)==TECH_KNOWN
//      && (get_invention(Game.game.player_ptr, i)==TECH_UNKNOWN
//      || get_invention(Game.game.player_ptr, i)==TECH_REACHABLE)) {
//	count++;
//      }
//  }
//  
//  if(!count) {    
//    /* if there is no known tech to steal then 
//       send steal order at Spy's Discretion */
//    int target_id = pVcity.id;
//
//    remove_locked_buffer();
//    request_diplomat_action(DIPLOMAT_STEAL, id, target_id, Game.game.num_tech_types);
//    return -1;
//  }
//    
//  pCont = MALLOC(sizeof(struct CONTAINER));
//  pCont.id0 = pVcity.id;
//  pCont.id1 = id;/* spy id */
//  
//  pDiplomat_Dlg = MALLOC(sizeof(struct ADVANCED_DLG));
//      
//  pStr = create_str16_from_char("Select Advance to Steal", 12);
//  pStr.style |= TTF_STYLE_BOLD;
//
//  pWindow = create_window(get_locked_buffer(), pStr, 10, 10, 0);
//  unlock_buffer();
//  
//  pWindow.action = spy_steal_dlg_window_callback;
//  set_wstate(pWindow , FC_WS_NORMAL);
//  w = MAX(0, pWindow.size.w + 8);
//  
//  add_to_gui_list(ID_CARAVAN_DLG_WINDOW, pWindow);
//  pDiplomat_Dlg.pEndWidgetList = pWindow;
//  /* ------------------ */
//  /* exit button */
//  pBuf = create_themeicon(pTheme.Small_CANCEL_Icon, pWindow.dst,
//  			  			WF_DRAW_THEME_TRANSPARENT);
//  
//  w += pBuf.size.w + 10;
//  pBuf.action = exit_spy_steal_dlg_callback;
//  set_wstate(pBuf, FC_WS_NORMAL);
//  pBuf.key = SDLK_ESCAPE;
//  
//  add_to_gui_list(ID_TERRAIN_ADV_DLG_EXIT_BUTTON, pBuf);  
//  /* ------------------------- */
//  
//  count++; /* count + at Spy's Discretion */
//  /* max col - 104 is steal tech widget width */
//  max_col = (Main.screen.w - DOUBLE_FRAME_WH - 2) / 104;
//  /* max row - 204 is steal tech widget height */
//  max_row = (Main.screen.h - (WINDOW_TILE_HIGH + 1 + 2 + FRAME_WH)) / 204;
//  
//  /* make space on screen for scrollbar */
//  if (max_col * max_row < count) {
//    max_col--;
//  }
//
//  if (count < max_col + 1) {
//    col = count;
//  } else {
//    if (count < max_col + 3) {
//      col = max_col - 2;
//    } else {
//      if (count < max_col + 5) {
//        col = max_col - 1;
//      } else {
//        col = max_col;
//      }
//    }
//  }
//  
//  pStr = create_string16(null, 0, 10);
//  pStr.style |= (TTF_STYLE_BOLD | SF_CENTER);
//  
//  count = 0;
//  h = col * max_row;
//  for(i=A_FIRST; i<Game.game.num_tech_types; i++) {
//    if (tech_is_available(Game.game.player_ptr, i)
//      && get_invention(pVictim, i)==TECH_KNOWN
//      && (get_invention(Game.game.player_ptr, i)==TECH_UNKNOWN
//      || get_invention(Game.game.player_ptr, i)==TECH_REACHABLE)) {
//    
//      count++;  
//      copy_chars_to_string16(pStr, advances[i].name);
//      pSurf = create_sellect_tech_icon(pStr, i, FULL_MODE);
//      pBuf = create_icon2(pSurf, pWindow.dst,
//      		WF_FREE_THEME | WF_DRAW_THEME_TRANSPARENT);
//
//      set_wstate(pBuf, FC_WS_NORMAL);
//      pBuf.action = spy_steal_callback;
//      pBuf.data.cont = pCont;
//
//      add_to_gui_list(MAX_ID - i, pBuf);
//    
//      if (count > h) {
//        set_wflag(pBuf, WF_HIDDEN);
//      }
//    }
//  }
//  
//  /* get spy tech */
//  i = unit_type(Game.find_unit_by_id(id)).tech_requirement;
//  copy_chars_to_string16(pStr, "At Spy's Discretion");
//  pSurf = create_sellect_tech_icon(pStr, i, FULL_MODE);
//	
//  pBuf = create_icon2(pSurf, pWindow.dst,
//    	(WF_FREE_THEME | WF_DRAW_THEME_TRANSPARENT| WF_FREE_DATA));
//  set_wstate(pBuf, FC_WS_NORMAL);
//  pBuf.action = spy_steal_callback;
//  pBuf.data.cont = pCont;
//    
//  add_to_gui_list(MAX_ID - Game.game.num_tech_types, pBuf);
//  count++;
//  
//  /* --------------------------------------------------------- */
//  FREESTRING16(pStr);
//  pDiplomat_Dlg.pBeginWidgetList = pBuf;
//  pDiplomat_Dlg.pBeginActiveWidgetList = pBuf;
//  pDiplomat_Dlg.pEndActiveWidgetList = pWindow.prev.prev;
//  
//  /* -------------------------------------------------------------- */
//  
//  i = 0;
//  if (count > col) {
//    count = (count + (col - 1)) / col;
//    if (count > max_row) {
//      pDiplomat_Dlg.pActiveWidgetList = pWindow.prev.prev;
//      count = max_row;
//      i = create_vertical_scrollbar(pDiplomat_Dlg, col, count, true, true);  
//    }
//  } else {
//    count = 1;
//  }
//
//  w = MAX(w, (col * pBuf.size.w + 2 + DOUBLE_FRAME_WH + i));
//  h = WINDOW_TILE_HIGH + 1 + count * pBuf.size.h + 2 + FRAME_WH;
//  pWindow.size.x = (Main.screen.w - w) / 2;
//  pWindow.size.y = (Main.screen.h - h) / 2;
//  
//  /* alloca window theme and win background buffer */
//  pSurf = get_logo_gfx();
//  if (resize_window(pWindow, pSurf, null, w, h))
//  {
//    FREESURFACE(pSurf);
//  }
//
//    /* exit button */
//  pBuf = pWindow.prev;
//  pBuf.size.x = pWindow.size.x + pWindow.size.w-pBuf.size.w-FRAME_WH-1;
//  pBuf.size.y = pWindow.size.y + 1;
//  
//  setup_vertical_widgets_position(col, pWindow.size.x + FRAME_WH + 1,
//		  pWindow.size.y + WINDOW_TILE_HIGH + 1, 0, 0,
//		  pDiplomat_Dlg.pBeginActiveWidgetList,
//  		  pDiplomat_Dlg.pEndActiveWidgetList);
//    
//  if(pDiplomat_Dlg.pScroll) {
//    setup_vertical_scrollbar_area(pDiplomat_Dlg.pScroll,
//	pWindow.size.x + pWindow.size.w - FRAME_WH,
//    	pWindow.size.y + WINDOW_TILE_HIGH + 1,
//    	pWindow.size.h - (FRAME_WH + WINDOW_TILE_HIGH + 1), true);
//  }
//
//  redraw_group(pDiplomat_Dlg.pBeginWidgetList, pWindow, false);
//  sdl_dirty_rect(pWindow.size);
//  
//  return -1;
//}
//
///****************************************************************
//...
//*****************************************************************/
//static int diplomat_steal_callback(GUI pWidget)
//{
//  city pCity = pWidget.data.city;
//  int id = MAX_ID - pWidget.ID;
//  
//  popdown_diplomat_dialog();
//  
//  if(pCity && Game.find_unit_by_id(id)) { 
//    request_diplomat_action(DIPLOMAT_STEAL, id, pCity.id, 0);
//  }
//
//  process_diplomat_arrival(null, 0);
//  return -1;
//}
//
///****************************************************************
//...  Ask the server how much the revolt is going to cost us
//*****************************************************************/
//static int diplomat_incite_callback(GUI pWidget)
//{
//  city pCity = pWidget.data.city;
//  int id = MAX_ID - pWidget.ID;
//  
//  lock_buffer(pWidget.dst);
//  popdown_diplomat_dialog();
//  
//  if(pCity && Game.find_unit_by_id(id)) { 
//    dsend_packet_city_incite_inq(&aconnection, pCity.id);
//  }
//  
//  return -1;
//}
//
///****************************************************************
//  Callback from diplomat/spy dialog for "keep moving".
//  (This should only occur when entering allied city.)
//*****************************************************************/
//static int diplomat_keep_moving_callback(GUI pWidget)
//{
//  unit pUnit = Game.find_unit_by_id(MAX_ID - pWidget.ID);
//  city pCity = pWidget.data.city;
//  
//  popdown_diplomat_dialog();
//  
//  if(pUnit && pCity && !Map.same_pos(pUnit.x, pUnit.y, pCity.x, pCity.y)) {
//    request_diplomat_action(DIPLOMAT_MOVE, pUnit.id, pCity.id, 0);
//  }
//  process_diplomat_arrival(null, 0);
//  
//  return -1;
//}
//
///****************************************************************
//...  Ask the server how much the bribe is
//*****************************************************************/
//static int diplomat_bribe_callback(GUI pWidget)
//{
//  unit pTunit = pWidget.data.unit;
//  int id = MAX_ID - pWidget.ID;
//  
//  lock_buffer(pWidget.dst);
//  popdown_diplomat_dialog();
//  
//  if(Game.find_unit_by_id(id) && pTunit) { 
//    dsend_packet_unit_bribe_inq(&aconnection, pTunit.id);
//  }
//   
//  return -1;
//}
//
///****************************************************************
//...
//*****************************************************************/
//static int spy_sabotage_unit_callback(GUI pWidget)
//{
//  int diplomat_id = MAX_ID - pWidget.ID;
//  int target_id = pWidget.data.unit.id;
//  
//  popdown_diplomat_dialog();
//  request_diplomat_action(SPY_SABOTAGE_UNIT, diplomat_id, target_id, 0);
//  return -1;
//}
//
///****************************************************************
//...
//*****************************************************************/
//static int diplomat_close_callback(GUI pWidget)
//{
//  popdown_diplomat_dialog();
//  process_diplomat_arrival(null, 0);
//  return -1;
//}
//
///**************************************************************************
//  Popdown a dialog giving a diplomatic unit some options when moving into
//  the target tile.
//**************************************************************************/
//static void popdown_diplomat_dialog()
//{
//  if (pDiplomat_Dlg) {
//    is_unit_move_blocked = false;
//    popdown_window_group_dialog(pDiplomat_Dlg.pBeginWidgetList,
//				pDiplomat_Dlg.pEndWidgetList);
//    FREE(pDiplomat_Dlg.pScroll);
//    FREE(pDiplomat_Dlg);
//    queue_flush();
//  }
//}
//
///**************************************************************************
//  Popup a dialog giving a diplomatic unit some options when moving into
//  the target tile.
//**************************************************************************/
//void popup_diplomat_dialog(unit pUnit, int target_x, int target_y)
//{
//  GUI pWindow = null, *pBuf = null;
//  SDL_String16 *pStr;
//  city pCity;
//  unit pTunit;
//  int w = 0, h;
//  boolean spy;
//  
//  if (pDiplomat_Dlg) {
//    return;
//  }
//  
//  is_unit_move_blocked = true;
//  pCity = Map.map_get_city(target_x, target_y);
//  spy = unit_flag(pUnit, F_SPY);
//  
//  pDiplomat_Dlg = MALLOC(sizeof(struct ADVANCED_DLG));
//  
//  h = WINDOW_TILE_HIGH + 3 + FRAME_WH;
//    
//  /* window */
//  if (pCity)
//  {
//    if(spy) {
//      pStr = create_str16_from_char("Choose Your Spy's Strategy" , 12);
//    }
//    else
//    {
//      pStr = create_str16_from_char("Choose Your Diplomat's Strategy", 12);
//    }
//  }
//  else
//  {
//    pStr = create_str16_from_char("Subvert Enemy Unit", 12);
//  }
//  
//  pStr.style |= TTF_STYLE_BOLD;
//  
//  pWindow = create_window(null, pStr, 10, 10, WF_DRAW_THEME_TRANSPARENT);
//    
//  pWindow.action = diplomat_dlg_window_callback;
//  set_wstate(pWindow, FC_WS_NORMAL);
//  w = MAX(w, pWindow.size.w + 8);
//  
//  add_to_gui_list(ID_CARAVAN_DLG_WINDOW, pWindow);
//  pDiplomat_Dlg.pEndWidgetList = pWindow;
//    
//  /* ---------- */
//  if((pCity))
//  {
//    /* Spy/Diplomat acting against a city */
//    
//    /* -------------- */
//    if (diplomat_can_do_action(pUnit, DIPLOMAT_EMBASSY, target_x, target_y))
//    {
//	
//      create_active_iconlabel(pBuf, pWindow.dst, pStr,
//	    "Establish Embassy", diplomat_embassy_callback);
//      
//      pBuf.data.city = pCity;
//      set_wstate(pBuf, FC_WS_NORMAL);
//  
//      add_to_gui_list(MAX_ID - pUnit.id, pBuf);
//    
//      w = MAX(w, pBuf.size.w);
//      h += pBuf.size.h;
//    }
//  
//    /* ---------- */
//    if (diplomat_can_do_action(pUnit, DIPLOMAT_INVESTIGATE, target_x, target_y)) {
//    
//      create_active_iconlabel(pBuf, pWindow.dst, pStr,
//			      "Investigate City",
//			      diplomat_investigate_callback);
//      pBuf.data.city = pCity;
//      set_wstate(pBuf, FC_WS_NORMAL);
//  
//      add_to_gui_list(MAX_ID - pUnit.id, pBuf);
//    
//      w = MAX(w, pBuf.size.w);
//      h += pBuf.size.h;
//    }
//  
//    /* ---------- */
//    if (spy && diplomat_can_do_action(pUnit, SPY_POISON, target_x, target_y)) {
//    
//      create_active_iconlabel(pBuf, pWindow.dst, pStr,
//	    "Poison City", spy_poison_callback);
//      
//      pBuf.data.city = pCity;
//      set_wstate(pBuf, FC_WS_NORMAL);
//  
//      add_to_gui_list(MAX_ID - pUnit.id, pBuf);
//    
//      w = MAX(w, pBuf.size.w);
//      h += pBuf.size.h;
//    }    
//    /* ---------- */
//    if (diplomat_can_do_action(pUnit, DIPLOMAT_SABOTAGE, target_x, target_y)) {
//    
//      create_active_iconlabel(pBuf, pWindow.dst, pStr,
//	    "Sabotage City", 
//      		spy ? spy_sabotage_request : diplomat_sabotage_callback);
//      
//      pBuf.data.city = pCity;
//      set_wstate(pBuf, FC_WS_NORMAL);
//  
//      add_to_gui_list(MAX_ID - pUnit.id, pBuf);
//    
//      w = MAX(w, pBuf.size.w);
//      h += pBuf.size.h;
//    }
//  
//    /* ---------- */
//    if (diplomat_can_do_action(pUnit, DIPLOMAT_STEAL, target_x, target_y)) {
//    
//      create_active_iconlabel(pBuf, pWindow.dst, pStr,
//	    "Steal Technology",
//      			spy ? spy_steal_popup : diplomat_steal_callback);
//      pBuf.data.city = pCity;
//      set_wstate(pBuf , FC_WS_NORMAL);
//  
//      add_to_gui_list(MAX_ID - pUnit.id , pBuf);
//    
//      w = MAX(w , pBuf.size.w);
//      h += pBuf.size.h;
//    }
//      
//    /* ---------- */
//    if (diplomat_can_do_action(pUnit, DIPLOMAT_INCITE, target_x, target_y)) {
//    
//      create_active_iconlabel(pBuf, pWindow.dst, pStr,
//	    "Incite a Revolt", diplomat_incite_callback);
//      pBuf.data.city = pCity;
//      set_wstate(pBuf , FC_WS_NORMAL);
//  
//      add_to_gui_list(MAX_ID - pUnit.id , pBuf);
//    
//      w = MAX(w , pBuf.size.w);
//      h += pBuf.size.h;
//    }
//      
//    /* ---------- */
//    if (diplomat_can_do_action(pUnit, DIPLOMAT_MOVE, target_x, target_y)) {
//    
//      create_active_iconlabel(pBuf, pWindow.dst, pStr,
//	    "Keep moving", diplomat_keep_moving_callback);
//      
//      pBuf.data.city = pCity;
//      set_wstate(pBuf , FC_WS_NORMAL);
//  
//      add_to_gui_list(MAX_ID - pUnit.id , pBuf);
//    
//      w = MAX(w , pBuf.size.w);
//      h += pBuf.size.h;
//    }
//  }
//  else
//  {
//    if((pTunit=unit_list_get(&map_get_tile(target_x, target_y).units, 0))){
//       /* Spy/Diplomat acting against a unit */ 
//      /* ---------- */
//      if (diplomat_can_do_action(pUnit, DIPLOMAT_BRIBE, target_x, target_y)) {
//    
//        create_active_iconlabel(pBuf, pWindow.dst, pStr,
//	    "Bribe Enemy Unit", diplomat_bribe_callback);
//	
//        pBuf.data.unit = pTunit;
//        set_wstate(pBuf , FC_WS_NORMAL);
//  
//        add_to_gui_list(MAX_ID - pUnit.id , pBuf);
//    
//        w = MAX(w , pBuf.size.w);
//        h += pBuf.size.h;
//      }
//      
//      /* ---------- */
//      if (diplomat_can_do_action(pUnit, SPY_SABOTAGE_UNIT, target_x, target_y)) {
//    
//        create_active_iconlabel(pBuf, pWindow.dst, pStr,
//	    "Sabotage Enemy Unit", spy_sabotage_unit_callback);
//	
//        pBuf.data.unit = pTunit;
//        set_wstate(pBuf , FC_WS_NORMAL);
//  
//        add_to_gui_list(MAX_ID - pUnit.id , pBuf);
//    
//        w = MAX(w , pBuf.size.w);
//        h += pBuf.size.h;
//      }
//    }
//  }
//  /* ---------- */
//  
//  create_active_iconlabel(pBuf, pWindow.dst, pStr,
//	    "Cancel", diplomat_close_callback);
//    
//  set_wstate(pBuf , FC_WS_NORMAL);
//  pBuf.key = SDLK_ESCAPE;
//  
//  add_to_gui_list(ID_LABEL , pBuf);
//    
//  w = MAX(w , pBuf.size.w);
//  h += pBuf.size.h;
//  /* ---------- */
//  pDiplomat_Dlg.pBeginWidgetList = pBuf;
//  
//  /* setup window size and start position */
//  
//  pWindow.size.w = w + DOUBLE_FRAME_WH;
//  pWindow.size.h = h;
//  
//  auto_center_on_focus_unit();
//  put_window_near_map_tile(pWindow,
//  		w + DOUBLE_FRAME_WH, h, pUnit.x, pUnit.y);
//  resize_window(pWindow, null, null, pWindow.size.w, h);
//  
//  /* setup widget size and start position */
//    
//  pBuf = pWindow.prev;
//  setup_vertical_widgets_position(1,
//	pWindow.size.x + FRAME_WH,
//  	pWindow.size.y + WINDOW_TILE_HIGH + 2, w, 0,
//	pDiplomat_Dlg.pBeginWidgetList, pBuf);
//  
//  /* --------------------- */
//  /* redraw */
//  redraw_group(pDiplomat_Dlg.pBeginWidgetList, pWindow, 0);
//
//  flush_rect(pWindow.size);
//  
//}
//
///**************************************************************************
//  Return whether a diplomat dialog is open.  This is important if there
//  can be only one such dialog at a time; otherwise return false.
//**************************************************************************/
//boolean diplomat_dialog_is_open()
//{
//  return pDiplomat_Dlg != null;
//}
//
///* ====================================================================== */
///* ============================ SABOTAGE DIALOG ========================= */
///* ====================================================================== */
///* Sabotage Dlg use ADVANCED_TERRAIN_DLG struct and funct*/
//
//static int sabotage_impr_callback(GUI pWidget)
//{
//  int sabotage_improvement = MAX_ID - pWidget.ID;
//  int diplomat_target_id = pWidget.data.cont.id0;
//  int diplomat_id = pWidget.data.cont.id1;
//    
//  popdown_advanced_terrain_dialog();
//  
//  if(sabotage_improvement == 1000)
//  {
//    sabotage_improvement = -1;
//  }
//  
//  if(Game.find_unit_by_id(diplomat_id)
//    && Game.find_city_by_id(diplomat_target_id)) { 
//    request_diplomat_action(DIPLOMAT_SABOTAGE, diplomat_id,
//			    diplomat_target_id, sabotage_improvement + 1);
//  }
//
//  process_diplomat_arrival(null, 0);
//  return -1;
//}
//
///****************************************************************
// Pops-up the Spy sabotage dialog, upon return of list of
// available improvements requested by the above function.
//*****************************************************************/
//void popup_sabotage_dialog(city pCity)
//{
//  GUI pWindow = null, *pBuf = null , *pLast = null;
//  CONTAINER pCont;
//  unit pUnit = get_unit_in_focus();
//  SDL_String16 *pStr;
//  SDL_Rect area;
//  int n, w = 0, h, imp_h = 0;
//  
//  if (pAdvanced_Terrain_Dlg || !pUnit || !unit_flag(pUnit, F_SPY)) {
//    return;
//  }
//  
//  is_unit_move_blocked = true;
//  h = WINDOW_TILE_HIGH + 3 + FRAME_WH;
//    
//  pAdvanced_Terrain_Dlg = MALLOC(sizeof(struct ADVANCED_DLG));
//  
//  pCont = MALLOC(sizeof(struct CONTAINER));
//  pCont.id0 = pCity.id;
//  pCont.id1 = pUnit.id;/* spy id */
//    
//  pStr = create_str16_from_char("Select Improvement to Sabotage" , 12);
//  pStr.style |= TTF_STYLE_BOLD;
//  
//  pWindow = create_window(get_locked_buffer(),
//		  pStr, 10, 10, WF_DRAW_THEME_TRANSPARENT);
//  unlock_buffer();
//    
//  pWindow.action = advanced_terrain_window_dlg_callback;
//  set_wstate(pWindow, FC_WS_NORMAL);
//  w = MAX(w, pWindow.size.w);
//  
//  add_to_gui_list(ID_TERRAIN_ADV_DLG_WINDOW, pWindow);
//  pAdvanced_Terrain_Dlg.pEndWidgetList = pWindow;
//  /* ---------- */
//  /* exit button */
//  pBuf = create_themeicon(pTheme.Small_CANCEL_Icon, pWindow.dst,
//  			  			WF_DRAW_THEME_TRANSPARENT);
//  w += pBuf.size.w + 10;
//  pBuf.action = exit_advanced_terrain_dlg_callback;
//  set_wstate(pBuf, FC_WS_NORMAL);
//  pBuf.key = SDLK_ESCAPE;
//  
//  add_to_gui_list(ID_TERRAIN_ADV_DLG_EXIT_BUTTON, pBuf);
//  /* ---------- */
//  
//  create_active_iconlabel(pBuf, pWindow.dst, pStr,
//	    "City Production", sabotage_impr_callback);
//  pBuf.data.cont = pCont;  
//  set_wstate(pBuf, FC_WS_NORMAL);
//  set_wflag(pBuf, WF_FREE_DATA);
//  add_to_gui_list(MAX_ID - 1000, pBuf);
//    
//  w = MAX(w, pBuf.size.w);
//  h += pBuf.size.h;
//
//  /* separator */
//  pBuf = create_iconlabel(null, pWindow.dst, null, WF_FREE_THEME);
//    
//  add_to_gui_list(ID_SEPARATOR, pBuf);
//  h += pBuf.next.size.h;
//  /* ------------------ */
//  n = 0;
//	for (int imp = 0; imp < Game.game.num_impr_types; imp++) {
//	if((pCity).improvements[imp] == Improvement.I_NONE) {
//		continue;
//	}
//    if (get_improvement_type(imp).sabotage > 0) {
//      
//      create_active_iconlabel(pBuf, pWindow.dst, pStr,
//	      (char *) City.get_impr_name_ex(pCity, imp),
//				      sabotage_impr_callback);
//      pBuf.data.cont = pCont;
//      set_wstate(pBuf , FC_WS_NORMAL);
//  
//      add_to_gui_list(MAX_ID - imp, pBuf);
//    
//      w = MAX(w , pBuf.size.w);
//      imp_h += pBuf.size.h;
//      
//      if (!pAdvanced_Terrain_Dlg.pEndActiveWidgetList)
//      {
//	pAdvanced_Terrain_Dlg.pEndActiveWidgetList = pBuf;
//      }
//    
//      if (imp > 9)
//      {
//        set_wflag(pBuf, WF_HIDDEN);
//      }
//      
//      n++;    
//      /* ----------- */
//    }  
//  } ;
//  pAdvanced_Terrain_Dlg.pBeginActiveWidgetList = pBuf;
//  
//  if(n) {
//    /* separator */
//    pBuf = create_iconlabel(null, pWindow.dst, null, WF_FREE_THEME);
//    
//    add_to_gui_list(ID_SEPARATOR, pBuf);
//    h += pBuf.next.size.h;
//  /* ------------------ */
//  }
//  
//  create_active_iconlabel(pBuf, pWindow.dst, pStr, "At Spy's Discretion",
//			  sabotage_impr_callback);
//  pBuf.data.cont = pCont;  
//  set_wstate(pBuf, FC_WS_NORMAL);
//  
//  add_to_gui_list(MAX_ID - Improvement.B_LAST, pBuf);
//    
//  w = MAX(w, pBuf.size.w);
//  h += pBuf.size.h;
//  /* ----------- */
//  
//  pLast = pBuf;
//  pAdvanced_Terrain_Dlg.pBeginWidgetList = pBuf;
//  pAdvanced_Terrain_Dlg.pActiveWidgetList =
//			      pAdvanced_Terrain_Dlg.pEndActiveWidgetList;
//  
//  /* ---------- */
//  if (n > 10)
//  {
//    imp_h = 10 * pBuf.size.h;
//    
//    n = create_vertical_scrollbar(pAdvanced_Terrain_Dlg,
//		  1, 10, true, true);
//    w += n;
//  }
//  /* ---------- */
//  
//  
//  w += DOUBLE_FRAME_WH;
//  
//  h += imp_h;
//  
//  auto_center_on_focus_unit();
//  put_window_near_map_tile(pWindow, w, h, pUnit.x, pUnit.y);        
//  resize_window(pWindow, null, null, w, h);
//  
//  w -= DOUBLE_FRAME_WH;
//  
//  if (pAdvanced_Terrain_Dlg.pScroll)
//  {
//    w -= n;
//    imp_h = pBuf.size.w;
//  }
//  else
//  {
//    imp_h = 0;
//  }
//  
//  /* exit button */
//  pBuf = pWindow.prev;
//  pBuf.size.x = pWindow.size.x + pWindow.size.w-pBuf.size.w-FRAME_WH-1;
//  pBuf.size.y = pWindow.size.y + 1;
//  
//  /* Production sabotage */
//  pBuf = pBuf.prev;
//  
//  pBuf.size.x = pWindow.size.x + FRAME_WH;
//  pBuf.size.y = pWindow.size.y + WINDOW_TILE_HIGH + 2;
//  pBuf.size.w = w;
//  h = pBuf.size.h;
//  
//  area.x = 10;
//  area.h = 2;
//  
//  pBuf = pBuf.prev;
//  while(pBuf)
//  {
//    
//    if (pBuf == pAdvanced_Terrain_Dlg.pEndActiveWidgetList)
//    {
//      w -= imp_h;
//    }
//    
//    pBuf.size.w = w;
//    pBuf.size.x = pBuf.next.size.x;
//    pBuf.size.y = pBuf.next.size.y + pBuf.next.size.h;
//    
//    if (pBuf.ID == ID_SEPARATOR)
//    {
//      FREESURFACE(pBuf.theme);
//      pBuf.size.h = h;
//      pBuf.theme = create_surf(w, h, SDL_SWSURFACE);
//    
//      area.y = pBuf.size.h / 2 - 1;
//      area.w = pBuf.size.w - 20;
//      
//      SDL_FillRect(pBuf.theme , &area, 64);
//      SDL_SetColorKey(pBuf.theme, SDL_SRCCOLORKEY|SDL_RLEACCEL, 0x0);
//    }
//    
//    if (pBuf == pLast) {
//      break;
//    }
//    pBuf = pBuf.prev;  
//  }
//  
//  if (pAdvanced_Terrain_Dlg.pScroll)
//  {
//    setup_vertical_scrollbar_area(pAdvanced_Terrain_Dlg.pScroll,
//	pWindow.size.x + pWindow.size.w - FRAME_WH,
//    	pAdvanced_Terrain_Dlg.pEndActiveWidgetList.size.y,
//    	pWindow.size.y - pAdvanced_Terrain_Dlg.pEndActiveWidgetList.size.y +
//	    pWindow.size.h - FRAME_WH, true);
//  }
//  
//  /* -------------------- */
//  /* redraw */
//  redraw_group(pAdvanced_Terrain_Dlg.pBeginWidgetList, pWindow, 0);
//
//  flush_rect(pWindow.size);
//  
//}
//
///* ====================================================================== */
///* ============================== INCITE DIALOG ========================= */
///* ====================================================================== */
//static SMALL_DLG pIncite_Dlg = null;
//
///****************************************************************
//...
//*****************************************************************/
//static int incite_dlg_window_callback(GUI pWindow)
//{
//  return std_move_window_group_callback(pIncite_Dlg.pBeginWidgetList, pWindow);
//}
//
///****************************************************************
//...
//*****************************************************************/
//static int diplomat_incite_yes_callback(GUI pWidget)
//{
//  int diplomat_id = MAX_ID - pWidget.ID;
//  int target_id = pWidget.data.city.id;
//  
//  popdown_incite_dialog();
//
//  request_diplomat_action(DIPLOMAT_INCITE, diplomat_id, target_id, 0);
//  return -1;
//}
//
///****************************************************************
//...
//*****************************************************************/
//static int exit_incite_dlg_callback(GUI pWidget)
//{
//  popdown_incite_dialog();
//  process_diplomat_arrival(null, 0);
//  return -1;
//}
//
///**************************************************************************
//  Popdown a window asking a diplomatic unit if it wishes to incite the
//  given enemy city.
//**************************************************************************/
//static void popdown_incite_dialog()
//{
//  if (pIncite_Dlg) {
//    is_unit_move_blocked = false;
//    popdown_window_group_dialog(pIncite_Dlg.pBeginWidgetList,
//				pIncite_Dlg.pEndWidgetList);
//    FREE(pIncite_Dlg);
//    flush_dirty();
//  }
//}
//
///**************************************************************************
//  Popup a window asking a diplomatic unit if it wishes to incite the
//  given enemy city.
//**************************************************************************/
//void popup_incite_dialog(city pCity)
//{
//  GUI pWindow = null, *pBuf = null;
//  SDL_String16 *pStr;
//  unit pUnit;
//  char cBuf[255]; 
//  int w = 0, h;
//  boolean exit = false;
//  
//  if (pIncite_Dlg) {
//    return;
//  }
//  
//  /* ugly hack */
//  pUnit = get_unit_in_focus();
//    
//  if (!pUnit || !unit_flag(pUnit,F_SPY)) {
//    return;
//  }
//    
//  is_unit_move_blocked = true;
//  pIncite_Dlg = MALLOC(sizeof(struct SMALL_DLG));
//  
//  h = WINDOW_TILE_HIGH + 3 + FRAME_WH;
//      
//  /* window */
//  pStr = create_str16_from_char("Incite a Revolt!", 12);
//    
//  pStr.style |= TTF_STYLE_BOLD;
//  
//  pWindow = create_window(get_locked_buffer(),
//			  pStr, 10, 10, WF_DRAW_THEME_TRANSPARENT);
//  unlock_buffer();
//    
//  pWindow.action = incite_dlg_window_callback;
//  set_wstate(pWindow, FC_WS_NORMAL);
//  w = MAX(w, pWindow.size.w + 8);
//  
//  add_to_gui_list(ID_INCITE_DLG_WINDOW, pWindow);
//  pIncite_Dlg.pEndWidgetList = pWindow;
//  
//  if (pCity.incite_revolt_cost == INCITE_IMPOSSIBLE_COST) {
//    
//    /* exit button */
//    pBuf = create_themeicon(pTheme.Small_CANCEL_Icon, pWindow.dst,
//  			  			WF_DRAW_THEME_TRANSPARENT);  
//    w += pBuf.size.w + 10;
//    pBuf.action = exit_incite_dlg_callback;
//    set_wstate(pBuf, FC_WS_NORMAL);
//    pBuf.key = SDLK_ESCAPE;
//  
//    add_to_gui_list(ID_INCITE_DLG_EXIT_BUTTON, pBuf);
//    exit = true;
//    /* --------------- */
//    
//    cBuf = util.my_snprintf( "You can't incite a revolt in %s.",
//		pCity.name);
//
//    create_active_iconlabel(pBuf, pWindow.dst, pStr, cBuf, null);
//        
//    add_to_gui_list(ID_LABEL , pBuf);
//    
//    w = MAX(w , pBuf.size.w);
//    h += pBuf.size.h;
//    /*------------*/
//    create_active_iconlabel(pBuf, pWindow.dst, pStr,
//	    "City can't be incited!", null);
//        
//    add_to_gui_list(ID_LABEL , pBuf);
//    
//    w = MAX(w , pBuf.size.w);
//    h += pBuf.size.h;
//    
//  } else if (Game.game.player_ptr.economic.gold >= pCity.incite_revolt_cost) {
//    cBuf = util.my_snprintf(
//		"Incite a revolt for %d gold?\nTreasury contains %d gold.", 
//		pCity.incite_revolt_cost, Game.game.player_ptr.economic.gold);
//    
//    create_active_iconlabel(pBuf, pWindow.dst, pStr, cBuf, null);
//        
//  
//    add_to_gui_list(ID_LABEL , pBuf);
//    
//    w = MAX(w, pBuf.size.w);
//    h += pBuf.size.h;
//    
//    /*------------*/
//    create_active_iconlabel(pBuf, pWindow.dst, pStr,
//	    "Yes" , diplomat_incite_yes_callback);
//    
//    pBuf.data.city = pCity;
//    set_wstate(pBuf, FC_WS_NORMAL);
//  
//    add_to_gui_list(MAX_ID - pUnit.id, pBuf);
//    
//    w = MAX(w, pBuf.size.w);
//    h += pBuf.size.h;
//    /* ------- */
//    create_active_iconlabel(pBuf, pWindow.dst, pStr,
//	    "No" , exit_incite_dlg_callback);
//    
//    set_wstate(pBuf, FC_WS_NORMAL);
//    pBuf.key = SDLK_ESCAPE;
//    
//    add_to_gui_list(ID_LABEL, pBuf);
//    
//    w = MAX(w, pBuf.size.w);
//    h += pBuf.size.h;
//    
//  } else {
//    /* exit button */
//    pBuf = create_themeicon(pTheme.Small_CANCEL_Icon, pWindow.dst,
//  			  			WF_DRAW_THEME_TRANSPARENT);
//    w += pBuf.size.w + 10;
//    pBuf.action = exit_incite_dlg_callback;
//    set_wstate(pBuf, FC_WS_NORMAL);
//    pBuf.key = SDLK_ESCAPE;
//  
//    add_to_gui_list(ID_INCITE_DLG_EXIT_BUTTON, pBuf);
//    exit = true;
//    /* --------------- */
//
//    cBuf = util.my_snprintf(
//		("Inciting a revolt costs %d gold.\n" +
//		  "Treasury contains %d gold."), 
//		pCity.incite_revolt_cost, Game.game.player_ptr.economic.gold);
//    
//    create_active_iconlabel(pBuf, pWindow.dst, pStr, cBuf, null);
//        
//  
//    add_to_gui_list(ID_LABEL, pBuf);
//    
//    w = MAX(w, pBuf.size.w);
//    h += pBuf.size.h;
//    
//    /*------------*/
//    create_active_iconlabel(pBuf, pWindow.dst, pStr,
//	    "Traitors Demand Too Much!", null);
//        
//    add_to_gui_list(ID_LABEL , pBuf);
//    
//    w = MAX(w, pBuf.size.w);
//    h += pBuf.size.h;
//  }
//  pIncite_Dlg.pBeginWidgetList = pBuf;
//  
//  /* setup window size and start position */
//  
//  pWindow.size.w = w + DOUBLE_FRAME_WH;
//  pWindow.size.h = h;
//  
//  auto_center_on_focus_unit();
//  put_window_near_map_tile(pWindow,
//  		w + DOUBLE_FRAME_WH, h, pCity.x, pCity.y);
//  resize_window(pWindow, null, null, pWindow.size.w, h);
//  
//  /* setup widget size and start position */
//  pBuf = pWindow;
//  
//  if (exit)
//  {/* exit button */
//    pBuf = pBuf.prev;
//    pBuf.size.x = pWindow.size.x + pWindow.size.w-pBuf.size.w-FRAME_WH-1;
//    pBuf.size.y = pWindow.size.y + 1;
//  }
//  
//  pBuf = pBuf.prev;
//  setup_vertical_widgets_position(1,
//	pWindow.size.x + FRAME_WH,
//  	pWindow.size.y + WINDOW_TILE_HIGH + 2, w, 0,
//	pIncite_Dlg.pBeginWidgetList, pBuf);
//    
//  /* --------------------- */
//  /* redraw */
//  redraw_group(pIncite_Dlg.pBeginWidgetList, pWindow, 0);
//
//  flush_rect(pWindow.size);
//  
//}
//
///* ====================================================================== */
///* ============================ BRIBE DIALOG ========================== */
///* ====================================================================== */
//static SMALL_DLG pBribe_Dlg = null;
//
//static int bribe_dlg_window_callback(GUI pWindow)
//{
//  return std_move_window_group_callback(pBribe_Dlg.pBeginWidgetList, pWindow);
//}
//
//static int diplomat_bribe_yes_callback(GUI pWidget)
//{
//  int diplomat_id = MAX_ID - pWidget.ID;
//  int target_id = pWidget.data.unit.id;
//
//  popdown_bribe_dialog();
//  
//  request_diplomat_action(DIPLOMAT_BRIBE, diplomat_id, target_id, 0);
//  return -1;
//}
//
//static int exit_bribe_dlg_callback(GUI pWidget)
//{
//  popdown_bribe_dialog();
//  return -1;
//}
//
///**************************************************************************
//  Popdown a dialog asking a diplomatic unit if it wishes to bribe the
//  given enemy unit.
//**************************************************************************/
//static void popdown_bribe_dialog()
//{
//  if (pBribe_Dlg) {
//    is_unit_move_blocked = false;
//    popdown_window_group_dialog(pBribe_Dlg.pBeginWidgetList,
//				pBribe_Dlg.pEndWidgetList);
//    FREE(pBribe_Dlg);
//    flush_dirty();
//  }
//}
//
///**************************************************************************
//  Popup a dialog asking a diplomatic unit if it wishes to bribe the
//  given enemy unit.
//**************************************************************************/
//void popup_bribe_dialog(unit pUnit)
//{
//  GUI pWindow = null, *pBuf = null;
//  SDL_String16 *pStr;
//  unit pDiplomatUnit;
//  char cBuf[255]; 
//  int w = 0, h;
//  boolean exit = false;
//  
//  if (pBribe_Dlg) {
//    return;
//  }
//    
//  /* ugly hack */
//  pDiplomatUnit = get_unit_in_focus();
//  
//  if (!pDiplomatUnit || !(unit_flag(pDiplomatUnit, F_SPY) ||
//		    unit_flag(pDiplomatUnit, Eunit_flag_id.F_DIPLOMAT))) {
//    remove_locked_buffer();
//    return;
//  }
//  
//  is_unit_move_blocked = true;
//  pBribe_Dlg = MALLOC(sizeof(struct SMALL_DLG));
//  
//  h = WINDOW_TILE_HIGH + 3 + FRAME_WH;
//      
//  /* window */
//  pStr = create_str16_from_char("Bribe Enemy Unit", 12);
//    
//  pStr.style |= TTF_STYLE_BOLD;
//  
//  pWindow = create_window(get_locked_buffer(),
//			  pStr, 10, 10, WF_DRAW_THEME_TRANSPARENT);
//  unlock_buffer();
//    
//  pWindow.action = bribe_dlg_window_callback;
//  set_wstate(pWindow, FC_WS_NORMAL);
//  w = MAX(w, pWindow.size.w + 8);
//  
//  add_to_gui_list(ID_BRIBE_DLG_WINDOW, pWindow);
//  pBribe_Dlg.pEndWidgetList = pWindow;
//  
//  if(Game.game.player_ptr.economic.gold >= pUnit.bribe_cost) {
//    cBuf = util.my_snprintf(
//		"Bribe unit for %d gold?\nTreasury contains %d gold.", 
//		pUnit.bribe_cost, Game.game.player_ptr.economic.gold);
//    
//    create_active_iconlabel(pBuf, pWindow.dst, pStr, cBuf, null);
//  
//    add_to_gui_list(ID_LABEL, pBuf);
//    
//    w = MAX(w, pBuf.size.w);
//    h += pBuf.size.h;
//    
//    /*------------*/
//    create_active_iconlabel(pBuf, pWindow.dst, pStr,
//	    "Yes", diplomat_bribe_yes_callback);
//    pBuf.data.unit = pUnit;
//    set_wstate(pBuf, FC_WS_NORMAL);
//  
//    add_to_gui_list(MAX_ID - pDiplomatUnit.id, pBuf);
//    
//    w = MAX(w, pBuf.size.w);
//    h += pBuf.size.h;
//    /* ------- */
//    create_active_iconlabel(pBuf, pWindow.dst, pStr,
//	    "No" , exit_bribe_dlg_callback);
//    
//    set_wstate(pBuf , FC_WS_NORMAL);
//    pBuf.key = SDLK_ESCAPE;
//    
//    add_to_gui_list(ID_LABEL, pBuf);
//    
//    w = MAX(w, pBuf.size.w);
//    h += pBuf.size.h;
//    
//  } else {
//    /* exit button */
//    pBuf = create_themeicon(pTheme.Small_CANCEL_Icon, pWindow.dst,
//  			  			WF_DRAW_THEME_TRANSPARENT);
//    w += pBuf.size.w + 10;
//    pBuf.action = exit_bribe_dlg_callback;
//    set_wstate(pBuf, FC_WS_NORMAL);
//    pBuf.key = SDLK_ESCAPE;
//  
//    add_to_gui_list(ID_BRIBE_DLG_EXIT_BUTTON, pBuf);
//    exit = true;
//    /* --------------- */
//
//    cBuf = util.my_snprintf(
//		("Bribing the unit costs %d gold.\n" +
//		  "Treasury contains %d gold."), 
//		pUnit.bribe_cost, Game.game.player_ptr.economic.gold);
//    
//    create_active_iconlabel(pBuf, pWindow.dst, pStr, cBuf, null);
//  
//    add_to_gui_list(ID_LABEL, pBuf);
//    
//    w = MAX(w, pBuf.size.w);
//    h += pBuf.size.h;
//    
//    /*------------*/
//    create_active_iconlabel(pBuf, pWindow.dst, pStr,
//	    "Traitors Demand Too Much!", null);
//        
//    add_to_gui_list(ID_LABEL, pBuf);
//    
//    w = MAX(w, pBuf.size.w);
//    h += pBuf.size.h;
//  }
//  pBribe_Dlg.pBeginWidgetList = pBuf;
//  
//  /* setup window size and start position */
//  
//  pWindow.size.w = w + DOUBLE_FRAME_WH;
//  pWindow.size.h = h;
//  
//  auto_center_on_focus_unit();
//  put_window_near_map_tile(pWindow,
//  		w + DOUBLE_FRAME_WH, h, pDiplomatUnit.x, pDiplomatUnit.y);      
//  resize_window(pWindow, null, null, pWindow.size.w, h);
//  
//  /* setup widget size and start position */
//  pBuf = pWindow;
//  
//  if (exit)
//  {/* exit button */
//    pBuf = pBuf.prev;
//    pBuf.size.x = pWindow.size.x + pWindow.size.w-pBuf.size.w-FRAME_WH-1;
//    pBuf.size.y = pWindow.size.y + 1;
//  }
//  
//  pBuf = pBuf.prev;
//  setup_vertical_widgets_position(1,
//	pWindow.size.x + FRAME_WH,
//  	pWindow.size.y + WINDOW_TILE_HIGH + 2, w, 0,
//	pBribe_Dlg.pBeginWidgetList, pBuf);
//  
//  /* --------------------- */
//  /* redraw */
//  redraw_group(pBribe_Dlg.pBeginWidgetList, pWindow, 0);
//
//  flush_rect(pWindow.size);
//}
//
///* ====================================================================== */
///* ============================ PILLAGE DIALOG ========================== */
///* ====================================================================== */
//static SMALL_DLG pPillage_Dlg = null;
//
//static int pillage_window_callback(GUI pWindow)
//{
//  return std_move_window_group_callback(pPillage_Dlg.pBeginWidgetList, pWindow);
//}
//
//static int pillage_callback(GUI pWidget)
//{
//  
//  unit pUnit = pWidget.data.unit;
//  enum int what = MAX_ID - pWidget.ID;
//  
//  popdown_pillage_dialog();
//  
//  if (pUnit) 
//  {
//    request_new_unit_activity_targeted(pUnit, ACTIVITY_PILLAGE, what);
//  }
//  
//  return -1;
//}
//
//static int exit_pillage_dlg_callback(GUI pWidget)
//{
//  popdown_pillage_dialog();
//  return -1;
//}
//
///**************************************************************************
//  Popdown a dialog asking the unit which improvement they would like to
//  pillage.
//**************************************************************************/
//static void popdown_pillage_dialog()
//{
//  if (pPillage_Dlg) {
//    is_unit_move_blocked = false;
//    popdown_window_group_dialog(pPillage_Dlg.pBeginWidgetList,
//				pPillage_Dlg.pEndWidgetList);
//    FREE(pPillage_Dlg);
//    flush_dirty();
//  }
//}
//
///**************************************************************************
//  Popup a dialog asking the unit which improvement they would like to
//  pillage.
//**************************************************************************/
//void popup_pillage_dialog(unit pUnit,
//			  enum int may_pillage)
//{
//  GUI pWindow = null, *pBuf = null;
//  SDL_String16 *pStr;
//  enum int what;
//  int w = 0, h;
//  
//  if (pPillage_Dlg) {
//    return;
//  }
//  
//  is_unit_move_blocked = true;
//  pPillage_Dlg = MALLOC(sizeof(struct SMALL_DLG));
//  
//  h = WINDOW_TILE_HIGH + 3 + FRAME_WH;
//      
//  /* window */
//  pStr = create_str16_from_char("What To Pillage" , 12);
//  pStr.style |= TTF_STYLE_BOLD;
//  
//  pWindow = create_window(null, pStr , 10, 10, WF_DRAW_THEME_TRANSPARENT);
//    
//  pWindow.action = pillage_window_callback;
//  set_wstate(pWindow, FC_WS_NORMAL);
//  w = MAX(w, pWindow.size.w);
//  
//  add_to_gui_list(ID_PILLAGE_DLG_WINDOW, pWindow);
//  pPillage_Dlg.pEndWidgetList = pWindow;
//    
//  /* ---------- */
//  /* exit button */
//  pBuf = create_themeicon(pTheme.Small_CANCEL_Icon, pWindow.dst,
//  			  			WF_DRAW_THEME_TRANSPARENT);
//  w += pBuf.size.w + 10;
//  pBuf.action = exit_pillage_dlg_callback;
//  set_wstate(pBuf, FC_WS_NORMAL);
//  pBuf.key = SDLK_ESCAPE;
//  
//  add_to_gui_list(ID_PILLAGE_DLG_EXIT_BUTTON, pBuf);
//  /* ---------- */
//  
//  while (may_pillage != S_NO_SPECIAL) {
//    what = get_preferred_pillage(may_pillage);
//    
//    create_active_iconlabel(pBuf, pWindow.dst, pStr,
//	    (char *) get_special_name(what), pillage_callback);
//    pBuf.data.unit = pUnit;
//    set_wstate(pBuf, FC_WS_NORMAL);
//  
//    add_to_gui_list(MAX_ID - what, pBuf);
//    
//    w = MAX(w, pBuf.size.w);
//    h += pBuf.size.h;
//        
//    may_pillage &= (~(what | map_get_infrastructure_prerequisite(what)));
//  }
//  pPillage_Dlg.pBeginWidgetList = pBuf;
//  
//  /* setup window size and start position */
//  
//  pWindow.size.w = w + DOUBLE_FRAME_WH;
//  pWindow.size.h = h;
//  
//  put_window_near_map_tile(pWindow,
//  		w + DOUBLE_FRAME_WH, h, pUnit.x, pUnit.y);      
//  resize_window(pWindow, null, null, pWindow.size.w, h);
//  
//  /* setup widget size and start position */
//
//  /* exit button */  
//  pBuf = pWindow.prev;
//  pBuf.size.x = pWindow.size.x + pWindow.size.w-pBuf.size.w-FRAME_WH-1;
//  pBuf.size.y = pWindow.size.y + 1;
//
//  /* first special to pillage */
//  pBuf = pBuf.prev;
//  setup_vertical_widgets_position(1,
//	pWindow.size.x + FRAME_WH,
//  	pWindow.size.y + WINDOW_TILE_HIGH + 2, w, 0,
//	pPillage_Dlg.pBeginWidgetList, pBuf);
//
//  /* --------------------- */
//  /* redraw */
//  redraw_group(pPillage_Dlg.pBeginWidgetList, pWindow, 0);
//
//  flush_rect(pWindow.size);
//}
//
///* ======================================================================= */
///* =========================== CONNECT DIALOG ============================ */
///* ======================================================================= */
//static SMALL_DLG pConnect_Dlg = null;
//
///**************************************************************************
//  Popdown a dialog asking the unit how they want to "connect" their
//  location to the destination.
//**************************************************************************/
//static void popdown_connect_dialog()
//{
//  if (pConnect_Dlg) {
//    is_unit_move_blocked = false;
//    popdown_window_group_dialog(pConnect_Dlg.pBeginWidgetList,
//				pConnect_Dlg.pEndWidgetList);
//    FREE(pConnect_Dlg);
//  }
//}
//
///**************************************************************************
//                                  Revolutions
//**************************************************************************/
//static SMALL_DLG pRevolutionDlg = null;
//
///**************************************************************************
//  ...
//**************************************************************************/
//static int revolution_dlg_ok_callback(GUI pButton)
//{
//  start_revolution();
//
//  popdown_revolution_dialog();
//  
//  SDL_Client_Flags |= CF_REVOLUTION;
//  
//  flush_dirty();
//  return (-1);
//}
//
///**************************************************************************
//  ...
//**************************************************************************/
//static int revolution_dlg_cancel_callback(GUI pCancel_Button)
//{
//  popdown_revolution_dialog();
//  flush_dirty();
//  return (-1);
//}
//
///**************************************************************************
//  ...
//**************************************************************************/
//static int move_revolution_dlg_callback(GUI pWindow)
//{
//  return std_move_window_group_callback(pRevolutionDlg.pBeginWidgetList, pWindow);
//}
//
///**************************************************************************
//  Close the revolution dialog.
//**************************************************************************/
//static void popdown_revolution_dialog()
//{
//  if(pRevolutionDlg) {
//    popdown_window_group_dialog(pRevolutionDlg.pBeginWidgetList,
//			      pRevolutionDlg.pEndWidgetList);
//    FREE(pRevolutionDlg);
//    enable_and_redraw_revolution_button();
//  }
//}
//
///* ==================== Public ========================= */
//
///**************************************************************************
//  Popup a dialog asking if the player wants to start a revolution.
//**************************************************************************/
//void popup_revolution_dialog()
//{
//  SDL_Surface *pLogo;
//  SDL_String16 pStr = null;
//  GUI pLabel = null;
//  GUI pWindow = null;
//  GUI pCancel_Button = null;
//  GUI pOK_Button = null;
//  int ww;
//
//  if(pRevolutionDlg) {
//    return;
//  }
//  
//  pRevolutionDlg = MALLOC(sizeof(struct SMALL_DLG));
//    
//  /* create ok button */
//  pOK_Button =
//      create_themeicon_button_from_chars(pTheme.Small_OK_Icon,
//			  Main.gui, "Revolution!", 10, 0);
//
//  /* create cancel button */
//  pCancel_Button =
//      create_themeicon_button_from_chars(pTheme.Small_CANCEL_Icon,
//  					Main.gui, "Cancel", 10, 0);
//  
//  /* correct sizes */
//  pCancel_Button.size.w += 6;
//
//  /* create text label */
//  pStr = create_str16_from_char("You say you wanna revolution?", 10);
//  pStr.style |= (TTF_STYLE_BOLD|SF_CENTER);
//  pStr.fgcol.r = 255;
//  pStr.fgcol.g = 255;
//  /* pStr.fgcol.b = 255; */
//  pLabel = create_iconlabel(null, Main.gui, pStr, 0);
//
//  /* create window */
//  pStr = create_str16_from_char("REVOLUTION!", 12);
//  pStr.style |= TTF_STYLE_BOLD;
//  if ((pOK_Button.size.w + pCancel_Button.size.w + 30) >
//      pLabel.size.w + 20) {
//    ww = pOK_Button.size.w + pCancel_Button.size.w + 30;
//  } else {
//    ww = pLabel.size.w + 20;
//  }
//
//  pWindow = create_window(Main.gui, pStr, ww,
//	pOK_Button.size.h + pLabel.size.h + WINDOW_TILE_HIGH + 25, 0);
//
//  /* set actions */
//  pWindow.action = move_revolution_dlg_callback;
//  pCancel_Button.action = revolution_dlg_cancel_callback;
//  pOK_Button.action = revolution_dlg_ok_callback;
//
//  /* set keys */
//  pOK_Button.key = SDLK_RETURN;
//  
//  /* I make this hack to center label on window */
//  pLabel.size.w = pWindow.size.w;
//
//  /* set start positions */
//  pWindow.size.x = (Main.screen.w - pWindow.size.w) / 2;
//  pWindow.size.y = (Main.screen.h - pWindow.size.h) / 2;
//
//  pOK_Button.size.x = pWindow.size.x + 10;
//  pOK_Button.size.y = pWindow.size.y + pWindow.size.h -
//      pOK_Button.size.h - 10;
//
//  pCancel_Button.size.y = pOK_Button.size.y;
//  pCancel_Button.size.x = pWindow.size.x + pWindow.size.w -
//      pCancel_Button.size.w - 10;
//  pLabel.size.x = pWindow.size.x;
//  pLabel.size.y = pWindow.size.y + WINDOW_TILE_HIGH + 5;
//
//  /* create window background */
//  pLogo = get_logo_gfx();
//  if (resize_window
//      (pWindow, pLogo, null, pWindow.size.w, pWindow.size.h)) {
//    FREESURFACE(pLogo);
//  }
//  
//  SDL_SetAlpha(pWindow.theme, 0x0, 0x0);
//  /* enable widgets */
//  set_wstate(pCancel_Button, FC_WS_NORMAL);
//  set_wstate(pOK_Button, FC_WS_NORMAL);
//  set_wstate(pWindow, FC_WS_NORMAL);
//
//  /* add widgets to main list */
//  pRevolutionDlg.pEndWidgetList = pWindow;
//  add_to_gui_list(ID_REVOLUTION_DLG_WINDOW, pWindow);
//  add_to_gui_list(ID_REVOLUTION_DLG_LABEL, pLabel);
//  add_to_gui_list(ID_REVOLUTION_DLG_CANCEL_BUTTON, pCancel_Button);
//  add_to_gui_list(ID_REVOLUTION_DLG_OK_BUTTON, pOK_Button);
//  pRevolutionDlg.pBeginWidgetList = pOK_Button;
//
//  /* redraw */
//  redraw_group(pOK_Button, pWindow, 0);
//  sdl_dirty_rect(pWindow.size);
//  flush_dirty();
//}
//
///**************************************************************************
//                                Nation Wizard
//**************************************************************************/
//static ADVANCED_DLG pNationDlg = null;
//static SMALL_DLG pHelpDlg = null;
//  
//struct NAT {
//  Uint8 nation_city_style;	/* sellected city style */
//  Uint8 selected_leader;	/* if not unique . sellected leader */
//  Sint8 nation;			/* sellected nation */
//  boolean leader_sex;		/* sellected leader sex */
//  GUI pChange_Sex;
//  GUI pName_Edit;
//  GUI pName_Next;
//  GUI pName_Prev;
//};
//
//static int nations_dialog_callback(GUI pWindow);
//static int nation_button_callback(GUI pNation);
//static int start_callback(GUI pStart_Button);
//static int disconnect_callback(GUI pButton);
//static int next_name_callback(GUI pNext_Button);
//static int prev_name_callback(GUI pPrev_Button);
//static int change_sex_callback(GUI pSex);
//static void select_random_leader(int nation);
//static void change_nation_label();
//
///**************************************************************************
//  ...
//**************************************************************************/
//static int nations_dialog_callback(GUI pWindow)
//{
//  if(sellect_window_group_dialog(pNationDlg.pBeginWidgetList, pWindow)) {
//    flush_rect(pWindow.size);
//  }      
//  return -1;
//}
//
///**************************************************************************
//  ...
//**************************************************************************/
//static int start_callback(GUI pStart_Button)
//{
//  NAT pSetup = (NAT )(pNationDlg.pEndWidgetList.data.ptr);
//  char *pStr = convert_to_chars(pSetup.pName_Edit.string16.text);
//
//  /* perform a minimum of sanity test on the name */
//  if (pStr.length() == 0) {
//    append_output_window("You must type a legal name.");
//    pSellected_Widget = pStart_Button;
//    set_wstate(pStart_Button, FC_WS_SELLECTED);
//    redraw_tibutton(pStart_Button);
//    flush_rect(pStart_Button.size);
//    return (-1);
//  }
//
//  dsend_packet_nation_select_req(&aconnection, pSetup.nation,
//				 pSetup.leader_sex, pStr,
//				 pSetup.nation_city_style);
//  FREE(pStr);
//
//  return -1;
//}
//
///**************************************************************************
//  ...
//**************************************************************************/
//static int change_sex_callback(GUI pSex)
//{
//  NAT pSetup = (NAT )(pNationDlg.pEndWidgetList.data.ptr);
//    
//  if (pSetup.leader_sex) {
//    copy_chars_to_string16(pSetup.pChange_Sex.string16, "Female");
//  } else {
//    copy_chars_to_string16(pSetup.pChange_Sex.string16, "Male");
//  }
//  pSetup.leader_sex = !pSetup.leader_sex;
//  
//  if (pSex) {
//    pSellected_Widget = pSex;
//    set_wstate(pSex, FC_WS_SELLECTED);
//
//    redraw_ibutton(pSex);
//    flush_rect(pSex.size);
//  }
//  return -1;
//}
//
///**************************************************************************
//  ...
//**************************************************************************/
//static int next_name_callback(GUI pNext)
//{
//  int dim;
//  NAT pSetup = (NAT )(pNationDlg.pEndWidgetList.data.ptr);
//  leader leaders = get_nation_leaders(pSetup.nation, &dim);
//    
//  pSetup.selected_leader++;
//  
//  /* change leadaer sex */
//  if (pSetup.leader_sex != leaders[pSetup.selected_leader].is_male) {
//    change_sex_callback(null);
//  }
//    
//  /* change leadaer name */
//  copy_chars_to_string16(pSetup.pName_Edit.string16,
//  				leaders[pSetup.selected_leader].name);
//  
//  if ((dim - 1) == pSetup.selected_leader) {
//    set_wstate(pSetup.pName_Next, FC_WS_DISABLED);
//  }
//
//  if (get_wstate(pSetup.pName_Prev) == FC_WS_DISABLED) {
//    set_wstate(pSetup.pName_Prev, FC_WS_NORMAL);
//  }
//
//  if (!(get_wstate(pSetup.pName_Next) == FC_WS_DISABLED)) {
//    pSellected_Widget = pSetup.pName_Next;
//    set_wstate(pSetup.pName_Next, FC_WS_SELLECTED);
//  }
//
//  redraw_edit(pSetup.pName_Edit);
//  redraw_tibutton(pSetup.pName_Prev);
//  redraw_tibutton(pSetup.pName_Next);
//  dirty_rect(pSetup.pName_Edit.size.x - pSetup.pName_Prev.size.w,
//  		pSetup.pName_Edit.size.y,
//		pSetup.pName_Edit.size.w + pSetup.pName_Prev.size.w +
//  		pSetup.pName_Next.size.w, pSetup.pName_Edit.size.h);
//  
//  redraw_ibutton(pSetup.pChange_Sex);
//  sdl_dirty_rect(pSetup.pChange_Sex.size);
//  
//  flush_dirty();
//  return -1;
//}
//
///**************************************************************************
//  ...
//**************************************************************************/
//static int prev_name_callback(GUI pPrev)
//{
//  int dim;
//  NAT pSetup = (NAT )(pNationDlg.pEndWidgetList.data.ptr);
//  leader leaders = get_nation_leaders(pSetup.nation, &dim);
//    
//  pSetup.selected_leader--;
//
//  /* change leadaer sex */
//  if (pSetup.leader_sex != leaders[pSetup.selected_leader].is_male) {
//    change_sex_callback(null);
//  }
//  
//  /* change leadaer name */
//  copy_chars_to_string16(pSetup.pName_Edit.string16,
//  				leaders[pSetup.selected_leader].name);
//  
//  if (!pSetup.selected_leader) {
//    set_wstate(pSetup.pName_Prev, FC_WS_DISABLED);
//  }
//
//  if (get_wstate(pSetup.pName_Next) == FC_WS_DISABLED) {
//    set_wstate(pSetup.pName_Next, FC_WS_NORMAL);
//  }
//
//  if (!(get_wstate(pSetup.pName_Prev) == FC_WS_DISABLED)) {
//    pSellected_Widget = pSetup.pName_Prev;
//    set_wstate(pSetup.pName_Prev, FC_WS_SELLECTED);
//  }
//
//  redraw_edit(pSetup.pName_Edit);
//  redraw_tibutton(pSetup.pName_Prev);
//  redraw_tibutton(pSetup.pName_Next);
//  dirty_rect(pSetup.pName_Edit.size.x - pSetup.pName_Prev.size.w,
//  		pSetup.pName_Edit.size.y, pSetup.pName_Edit.size.w +
//  		pSetup.pName_Prev.size.w + pSetup.pName_Next.size.w,
//		pSetup.pName_Edit.size.h);
//  
//  redraw_ibutton(pSetup.pChange_Sex);
//  sdl_dirty_rect(pSetup.pChange_Sex.size);
//  
//  flush_dirty();
//  return -1;
//}
//
///**************************************************************************
//  ...
//**************************************************************************/
//static int disconnect_callback(GUI pButton)
//{
//  popdown_races_dialog();
//  disconnect_from_server();
//  return -1;
//}
//
///**************************************************************************
//  ...
//**************************************************************************/
//static int city_style_callback(GUI pWidget)
//{
//  NAT pSetup = (NAT )(pNationDlg.pEndWidgetList.data.ptr);
//  GUI pGUI = get_widget_pointer_form_main_list(MAX_ID - 1000 -
//					    pSetup.nation_city_style);
//  set_wstate(pGUI, FC_WS_NORMAL);
//  redraw_icon2(pGUI);
//  sdl_dirty_rect(pGUI.size);
//  
//  set_wstate(pWidget, FC_WS_DISABLED);
//  redraw_icon2(pWidget);
//  sdl_dirty_rect(pWidget.size);
//  
//  pSetup.nation_city_style = MAX_ID - 1000 - pWidget.ID;
//  
//  flush_dirty();
//  pSellected_Widget = null;
//  return -1;
//}
//
///**************************************************************************
//  ...
//**************************************************************************/
//static int help_dlg_callback(GUI pWindow)
//{
//  return -1;
//}
//
///**************************************************************************
//  ...
//**************************************************************************/
//static int cancel_help_dlg_callback(GUI pWidget)
//{
//  if (pHelpDlg) {
//    popdown_window_group_dialog(pHelpDlg.pBeginWidgetList,
//			pHelpDlg.pEndWidgetList);
//    FREE(pHelpDlg);
//    if (pWidget) {
//      flush_dirty();
//    }
//  }
//  return -1;
//}
//
///**************************************************************************
//   ...
//**************************************************************************/
//static int nation_button_callback(GUI pNationButton)
//{
//      
//  set_wstate(pNationButton, FC_WS_SELLECTED);
//  pSellected_Widget = pNationButton;
//  
//  if (Main.event.button.button == SDL_BUTTON_LEFT) {
//    NAT pSetup = (NAT )(pNationDlg.pEndWidgetList.data.ptr);
//      
//    if (pSetup.nation == MAX_ID - pNationButton.ID) {
//      redraw_widget(pNationButton);
//      flush_rect(pNationButton.size);
//      return -1;
//    }
//  
//    pSetup.nation = MAX_ID - pNationButton.ID;
//  
//    change_nation_label();
//  
//    enable(MAX_ID - 1000 - pSetup.nation_city_style);
//    pSetup.nation_city_style = get_nation_city_style(pSetup.nation);
//    disable(MAX_ID - 1000 - pSetup.nation_city_style);
//    
//    select_random_leader(pSetup.nation);
//    
//    redraw_group(pNationDlg.pBeginWidgetList, pNationDlg.pEndWidgetList, 0);
//    flush_rect(pNationDlg.pEndWidgetList.size);
//  } else {
//    /* pop up legend info */
//    int w = 0, h;
//    GUI pWindow, *pCancel;
//    SDL_String16 *pStr;
//    SDL_Surface *pText, *pText2;
//    SDL_Rect area;
//    nation_type pNation = Nation.get_nation_by_idx(MAX_ID - pNationButton.ID);
//      
//    redraw_widget(pNationButton);
//    sdl_dirty_rect(pNationButton.size);
//  
//    if (!pHelpDlg) {
//    
//      pHelpDlg = MALLOC(sizeof(struct SMALL_DLG));
//    
//      pStr = create_str16_from_char("Nation's Legend", 12);
//      pStr.style |= TTF_STYLE_BOLD;
//  
//      pWindow = create_window(null, pStr, 10, 10, 0);
//      pWindow.action = help_dlg_callback;
//      w = pWindow.size.w;
//      set_wstate(pWindow, FC_WS_NORMAL);
//    
//      pHelpDlg.pEndWidgetList = pWindow;
//      add_to_gui_list(ID_WINDOW, pWindow);
//    
//      pCancel = create_themeicon_button_from_chars(pTheme.CANCEL_Icon,
//    				pWindow.dst, "Cancel", 14, 0);
//      pCancel.action = cancel_help_dlg_callback;
//      set_wstate(pCancel, FC_WS_NORMAL);
//      pCancel.key = SDLK_ESCAPE;
//      add_to_gui_list(ID_BUTTON, pCancel);
//      pHelpDlg.pBeginWidgetList = pCancel;
//    } else {
//      pWindow = pHelpDlg.pEndWidgetList;
//      pCancel = pHelpDlg.pBeginWidgetList;
//      /* undraw window */
//      blit_entire_src(pWindow.gfx, pWindow.dst,
//				      pWindow.size.x, pWindow.size.y);
//      sdl_dirty_rect(pWindow.size);
//    }
//    
//    if (pNation.legend && *(pNation.legend) != '\0') {
//      pStr = create_str16_from_char(pNation.legend, 12);
//    } else {
//      pStr = create_str16_from_char("SORRY... NO INFO", 12);
//    }
//    
//    pStr.fgcol = *get_game_colorRGB(COLOR_STD_WHITE);
//    pText = create_text_surf_smaller_that_w(pStr, Main.screen.w - 20);
//  
//    copy_chars_to_string16(pStr, pNation.name_plural);
//    pText2 = create_text_surf_from_str16(pStr);
//  
//    FREESTRING16(pStr);
//    
//    /* create window background */
//    w = MAX(w, pText2.w + 20);
//    w = MAX(w, pText.w + 20);
//    w = MAX(w, pCancel.size.w + 20);
//    h = WINDOW_TILE_HIGH + 10 + pText2.h + 10 + pText.h + 10 +
//  			pCancel.size.h + 10 + FRAME_WH;
//  
//    pWindow.size.x = (pWindow.dst.w - w) / 2;
//    pWindow.size.y = (pWindow.dst.h - h) / 2;
//  
//    resize_window(pWindow, null,
//  	get_game_colorRGB(COLOR_STD_BACKGROUND_BROWN), w, h);
//  
//    area.x = 10;
//    area.y = WINDOW_TILE_HIGH + 10;
//    SDL_BlitSurface(pText2, null, pWindow.theme, &area);
//    area.y += (pText2.h + 10);
//    FREESURFACE(pText2);
//  
//    SDL_BlitSurface(pText, null, pWindow.theme, &area);
//    FREESURFACE(pText);
//  
//    pCancel.size.x = pWindow.size.x + (pWindow.size.w - pCancel.size.w) / 2;
//    pCancel.size.y = pWindow.size.y +
//			pWindow.size.h - pCancel.size.h - 10 - FRAME_WH;
//  
//    /* redraw */
//    redraw_group(pCancel, pWindow, 0);
//
//    sdl_dirty_rect(pWindow.size);
//  
//    flush_dirty();
//
//  }
//  
//  return -1;
//}
//
///* =========================================================== */
//
///**************************************************************************
//   ...
//**************************************************************************/
//static void change_nation_label()
//{
//  SDL_Surface *pTmp_Surf, *pTmp_Surf_zoomed;
//  GUI pWindow = pNationDlg.pEndWidgetList;
//  NAT pSetup = (NAT )(pWindow.data.ptr);  
//  GUI pLabel = pSetup.pName_Edit.next;
//  nation_type pNation = Nation.get_nation_by_idx(pSetup.nation);
//  
//  pTmp_Surf = make_flag_surface_smaler(GET_SURF(pNation.flag_sprite));
//  pTmp_Surf_zoomed = ZoomSurface(pTmp_Surf, 5.0, 5.0, 1);
//  SDL_SetColorKey(pTmp_Surf_zoomed, SDL_SRCCOLORKEY|SDL_RLEACCEL,
//    		getpixel(pTmp_Surf_zoomed, pTmp_Surf_zoomed.w - 1,
//  						pTmp_Surf_zoomed.h - 1));
//  FREESURFACE(pTmp_Surf);
//  FREESURFACE(pLabel.theme);
//  
//  pLabel.theme = pTmp_Surf_zoomed;
//  copy_chars_to_string16(pLabel.string16, pNation.name_plural);
//  
//  remake_label_size(pLabel);
//  
//  pLabel.size.x = pWindow.size.x + pWindow.size.w / 2 +
//  				(pWindow.size.w/2 - pLabel.size.w) / 2;
//    
//}
//
///**************************************************************************
//  Selectes a leader and the appropriate sex. Updates the gui elements
//  and the selected_* variables.
//**************************************************************************/
//static void select_random_leader(int nation)
//{
//  int dim;
//  NAT pSetup = (NAT )(pNationDlg.pEndWidgetList.data.ptr);
//  leader leaders = get_nation_leaders(nation, &dim);
//  
//    
//  pSetup.selected_leader = Rand.myrand(dim);
//  copy_chars_to_string16(pSetup.pName_Edit.string16,
//  				leaders[pSetup.selected_leader].name);
//  
//  /* initialize leader sex */
//  pSetup.leader_sex = leaders[pSetup.selected_leader].is_male;
//
//  if (pSetup.leader_sex) {
//    copy_chars_to_string16(pSetup.pChange_Sex.string16, "Male");
//  } else {
//    copy_chars_to_string16(pSetup.pChange_Sex.string16, "Female");
//  }
//  
//  if (dim > 1) {
//    if (pSetup.selected_leader) {
//      set_wstate(pSetup.pName_Prev, FC_WS_NORMAL);
//    }
//
//    if ((dim - 1) != pSetup.selected_leader) {
//      set_wstate(pSetup.pName_Next, FC_WS_NORMAL);
//    }
//  }
//  
//}
//
///**************************************************************************
//  Popup the nation selection dialog.
//**************************************************************************/
//void popup_races_dialog()
//{
//  GUI pWindow, *pWidget = null, *pBuf, *pLast_City_Style;
//  SDL_String16 *pStr;
//  int i, len = 0;
//  int w = 10, h = 10;
//  SDL_Surface *pTmp_Surf, *pTmp_Surf_zoomed = null;
//  SDL_Surface *pMain_Bg, *pText_Name, *pText_Class;
//  SDL_Color color = {255,255,255,128};
//  SDL_Rect dst;
//  NAT pSetup;
//    
//  public static final int TARGETS_ROW = 4;
//  public static final int TARGETS_COL = 3;
//  
//  if (pNationDlg) {
//    return;
//  }
//  
//  pNationDlg = MALLOC(sizeof(struct ADVANCED_DLG));
//  
//  /* create window widget */
//  pStr = create_str16_from_char("What nation will you be?", 12);
//  pStr.style |= TTF_STYLE_BOLD;
//  
//  pWindow = create_window(null, pStr, w, h, WF_FREE_DATA);
//  pWindow.action = nations_dialog_callback;
//  set_wstate(pWindow, FC_WS_NORMAL);
//  pSetup = MALLOC(sizeof(struct NAT));
//  pWindow.data.ptr = (void *)pSetup;
//    
//  pNationDlg.pEndWidgetList = pWindow;
//  add_to_gui_list(ID_NATION_WIZARD_WINDOW, pWindow);
//  /* --------------------------------------------------------- */
//  /* create nations list */
//
//  /* Create Imprv Background Icon */
//  pTmp_Surf = create_surf(96, 96, SDL_SWSURFACE);
//  pMain_Bg = SDL_DisplayFormatAlpha(pTmp_Surf);
//  SDL_FillRect(pMain_Bg, null, SDL_MapRGBA(pMain_Bg.format, color.r,
//					    color.g, color.b, color.unused));
//  putframe(pMain_Bg, 0, 0, pMain_Bg.w - 1, pMain_Bg.h - 1, 0xFF000000);
//  FREESURFACE(pTmp_Surf);
//  
//  pStr = create_string16(null, 0, 12);
//  pStr.style |= (SF_CENTER|TTF_STYLE_BOLD);
//  pStr.render = 3;
//  pStr.bgcol = color;
//
//  /* fill list */
//  pText_Class = null;
//  for (i = 0; i < Game.game.playable_nation_count; i++) {
//    
//    nation_type pNation = Nation.get_nation_by_idx(i);
//    
//    pTmp_Surf = make_flag_surface_smaler(GET_SURF(pNation.flag_sprite));
//    pTmp_Surf_zoomed = ZoomSurface(pTmp_Surf, 3.0, 3.0, 1);
//    SDL_SetColorKey(pTmp_Surf_zoomed, SDL_SRCCOLORKEY,
//    		getpixel(pTmp_Surf_zoomed, pTmp_Surf_zoomed.w - 1,
//  						pTmp_Surf_zoomed.h - 1));
//    SDL_SetAlpha(pTmp_Surf_zoomed, 0x0, 0x0);
//    FREESURFACE(pTmp_Surf);
//
//    pTmp_Surf = crop_rect_from_surface(pMain_Bg, null);
//          
//    copy_chars_to_string16(pStr, pNation.name_plural);
//    change_ptsize16(pStr, 12);
//    pText_Name = create_text_surf_smaller_that_w(pStr, pTmp_Surf.w - 4);
//    SDL_SetAlpha(pText_Name, 0x0, 0x0);
//    
//    if (pNation.class && *(pNation.class) != '\0') {
//      copy_chars_to_string16(pStr, pNation.class);
//      change_ptsize16(pStr, 10);
//      pText_Class = create_text_surf_smaller_that_w(pStr, pTmp_Surf.w - 4);
//      SDL_SetAlpha(pText_Class, 0x0, 0x0);
//    }
//    
//    dst.x = (pTmp_Surf.w - pTmp_Surf_zoomed.w) / 2;
//    len = pTmp_Surf_zoomed.h +
//	    10 + pText_Name.h + (pText_Class ? pText_Class.h : 0);
//    dst.y = (pTmp_Surf.h - len) / 2;
//    SDL_BlitSurface(pTmp_Surf_zoomed, null, pTmp_Surf, &dst);
//    dst.y += (pTmp_Surf_zoomed.h + 10);
//    FREESURFACE(pTmp_Surf_zoomed);
//    
//    dst.x = (pTmp_Surf.w - pText_Name.w) / 2;
//    SDL_BlitSurface(pText_Name, null, pTmp_Surf, &dst);
//    dst.y += pText_Name.h;
//    FREESURFACE(pText_Name);
//    
//    if (pText_Class) {
//      dst.x = (pTmp_Surf.w - pText_Class.w) / 2;
//      SDL_BlitSurface(pText_Class, null, pTmp_Surf, &dst);
//      FREESURFACE(pText_Class);
//    }
//    
//    pWidget = create_icon2(pTmp_Surf, pWindow.dst,
//    			(WF_DRAW_THEME_TRANSPARENT|WF_FREE_THEME));
//    
//    set_wstate(pWidget, FC_WS_NORMAL);
//    
//    pWidget.action = nation_button_callback;
//
//    w = MAX(w, pWidget.size.w);
//    h = MAX(h, pWidget.size.h);
//    
//    add_to_gui_list(MAX_ID - i, pWidget);
//    
//    if(i > (TARGETS_ROW * TARGETS_COL - 1)) {
//      set_wflag(pWidget, WF_HIDDEN);
//    }
//    
//  }
//  
//  FREESURFACE(pMain_Bg);
//    
//  pNationDlg.pEndActiveWidgetList = pWindow.prev;
//  pNationDlg.pBeginActiveWidgetList = pWidget;
//  pNationDlg.pBeginWidgetList = pWidget;
//    
//  if(Game.game.playable_nation_count > TARGETS_ROW * TARGETS_COL) {
//      pNationDlg.pActiveWidgetList = pNationDlg.pEndActiveWidgetList;
//      create_vertical_scrollbar(pNationDlg,
//		    		TARGETS_COL, TARGETS_ROW, true, true);
//  }
//  
//  /* ----------------------------------------------------------------- */
//    
//  /* nation name */
//  
//  pSetup.nation = Rand.myrand(Game.game.playable_nation_count);
//  pSetup.nation_city_style = get_nation_city_style(pSetup.nation);
//  
//  copy_chars_to_string16(pStr, Nation.get_nation_by_idx(pSetup.nation).name_plural);
//  change_ptsize16(pStr, 36);
//  pStr.render = 2;
//  pStr.fgcol = color;
//  pTmp_Surf = make_flag_surface_smaler(
//  		GET_SURF(Nation.get_nation_by_idx(pSetup.nation).flag_sprite));
//  pTmp_Surf_zoomed = ZoomSurface(pTmp_Surf, 5.0, 5.0, 1);
//  SDL_SetColorKey(pTmp_Surf_zoomed, SDL_SRCCOLORKEY|SDL_RLEACCEL,
//    		getpixel(pTmp_Surf_zoomed, pTmp_Surf_zoomed.w - 1,
//  						pTmp_Surf_zoomed.h - 1));
//  FREESURFACE(pTmp_Surf);
//  
//  pWidget = create_iconlabel(pTmp_Surf_zoomed, pWindow.dst, pStr,
//  			(WF_ICON_ABOVE_TEXT|WF_ICON_CENTER|WF_FREE_GFX));
//  pBuf = pWidget;
//  add_to_gui_list(ID_LABEL, pWidget);
//  
//  /* create leader name edit */
//  pWidget = create_edit_from_unichars(null, pWindow.dst,
//						  null, 0, 16, 200, 0);
//  pWidget.size.h = 30;
//  
//  set_wstate(pWidget, FC_WS_NORMAL);
//  add_to_gui_list(ID_NATION_WIZARD_LEADER_NAME_EDIT, pWidget);
//  pSetup.pName_Edit = pWidget;
//  
//  /* create next leader name button */
//  pWidget = create_themeicon_button(pTheme.R_ARROW_Icon,
//						  pWindow.dst, null, 0);
//  pWidget.action = next_name_callback;
//  clear_wflag(pWidget, WF_DRAW_FRAME_AROUND_WIDGET);
//  add_to_gui_list(ID_NATION_WIZARD_NEXT_LEADER_NAME_BUTTON, pWidget);
//  pWidget.size.h = pWidget.next.size.h;
//  pSetup.pName_Next = pWidget;
//  
//  /* create prev leader name button */
//  pWidget = create_themeicon_button(pTheme.L_ARROW_Icon,
//  						pWindow.dst, null, 0);
//  pWidget.action = prev_name_callback;
//  clear_wflag(pWidget, WF_DRAW_FRAME_AROUND_WIDGET);
//  add_to_gui_list(ID_NATION_WIZARD_PREV_LEADER_NAME_BUTTON, pWidget);
//  pWidget.size.h = pWidget.next.size.h;
//  pSetup.pName_Prev = pWidget;
//  
//  /* change sex button */
//  
//  pWidget = create_icon_button_from_chars(null, pWindow.dst, "Male", 14, 0);
//  pWidget.action = change_sex_callback;
//  pWidget.size.w = 100;
//  pWidget.size.h = 22;
//  set_wstate(pWidget, FC_WS_NORMAL);
//  pSetup.pChange_Sex = pWidget;
//  
//  /* add to main widget list */
//  add_to_gui_list(ID_NATION_WIZARD_CHANGE_SEX_BUTTON, pWidget);
//
//  /* ---------------------------------------------------------- */
//  i = 0;
//  while (i < Game.game.styles_count) {
//    if (city_styles[i].techreq == A_NONE) {
//      pWidget = create_icon2(GET_SURF(sprites.city.tile[i][2]),
//			pWindow.dst, WF_DRAW_THEME_TRANSPARENT);
//      
//      pWidget.action = city_style_callback;
//      if (i != pSetup.nation_city_style) {
//        set_wstate(pWidget, FC_WS_NORMAL);
//      }
//      len = pWidget.size.w;
//      add_to_gui_list(MAX_ID - 1000 - i, pWidget);
//      i++;
//      break;
//    }
//    i++;
//  }
//
//  len += 3;
//
//  for (; (i < Game.game.styles_count && i < 64); i++) {
//    if (city_styles[i].techreq == A_NONE) {
//      pWidget = create_icon2(GET_SURF(sprites.city.tile[i][2]),
//				pWindow.dst, WF_DRAW_THEME_TRANSPARENT);
//      pWidget.action = city_style_callback;
//      if (i != pSetup.nation_city_style) {
//        set_wstate(pWidget, FC_WS_NORMAL);
//      }
//      len += (pWidget.size.w + 3);
//      add_to_gui_list(MAX_ID - 1000 - i, pWidget);
//    }
//  }
//  pLast_City_Style = pWidget;
//  /* ---------------------------------------------------------- */
//  
//  /* create disconnection button */
//  pWidget = create_themeicon_button_from_chars(pTheme.BACK_Icon, pWindow.dst,
//					 "Disconnect", 12, 0);
//  pWidget.action = disconnect_callback;
//  set_wstate(pWidget, FC_WS_NORMAL);
//  
//  add_to_gui_list(ID_NATION_WIZARD_DISCONNECT_BUTTON, pWidget);
//
//  /* create start button */
//  pWidget =
//      create_themeicon_button_from_chars(pTheme.FORWARD_Icon, pWindow.dst,
//				"Start", 12, WF_ICON_CENTER_RIGHT);
//  pWidget.action = start_callback;
//
//  pWidget.size.w += 60;
//  set_wstate(pWidget, FC_WS_NORMAL);
//  add_to_gui_list(ID_NATION_WIZARD_START_BUTTON, pWidget);
//  pWidget.size.w = MAX(pWidget.size.w, pWidget.next.size.w);
//  pWidget.next.size.w = pWidget.size.w;
//  
//  pNationDlg.pBeginWidgetList = pWidget;
//  /* ---------------------------------------------------------- */
//      
//  
//  pWindow.size.x = (Main.screen.w - 640) / 2;
//  pWindow.size.y = (Main.screen.h - 480) / 2;
//    
//  pMain_Bg = get_logo_gfx();
//  if(resize_window(pWindow, pMain_Bg, null, 640, 480)) {
//    FREESURFACE(pMain_Bg);
//  }
//  
//  /* nations */
//  
//  h = pNationDlg.pEndActiveWidgetList.size.h * TARGETS_ROW;
//  i = (pWindow.size.h - WINDOW_TILE_HIGH - h) / 2;
//  setup_vertical_widgets_position(TARGETS_COL,
//	pWindow.size.x + FRAME_WH + 10,
//	pWindow.size.y + WINDOW_TILE_HIGH + i,
//	  0, 0, pNationDlg.pBeginActiveWidgetList,
//			  pNationDlg.pEndActiveWidgetList);
//  
//  if(pNationDlg.pScroll) {
//    SDL_Rect area;
//  
//    w = pNationDlg.pEndActiveWidgetList.size.w * TARGETS_COL;    
//    setup_vertical_scrollbar_area(pNationDlg.pScroll,
//	pWindow.size.x + FRAME_WH + w + 12,
//    	pWindow.size.y + WINDOW_TILE_HIGH + i,	h, false);
//    
//    area.x = FRAME_WH + w + 11;
//    area.y = WINDOW_TILE_HIGH + i;
//    area.w = pNationDlg.pScroll.pUp_Left_Button.size.w + 2;
//    area.h = h;
//    SDL_FillRectAlpha(pWindow.theme, &area, &color);
//    putframe(pWindow.theme, area.x, area.y - 1,
//		area.x + area.w, area.y + area.h, 0xFF000000);
//  }
//   
//  /* Sellected Nation Name */
//  pBuf.size.x = pWindow.size.x + pWindow.size.w / 2 +
//  				(pWindow.size.w/2 - pBuf.size.w) / 2;
//  pBuf.size.y = pWindow.size.y + WINDOW_TILE_HIGH + 50;
//  
//  /* Leader Name Edit */
//  pBuf = pBuf.prev;
//  pBuf.size.x = pWindow.size.x + pWindow.size.w / 2 +
//  				(pWindow.size.w/2 - pBuf.size.w) / 2;
//  pBuf.size.y = pWindow.size.y + (pWindow.size.h - pBuf.size.h) / 2;
//  
//  /* Next Leader Name Button */
//  pBuf = pBuf.prev;
//  pBuf.size.x = pBuf.next.size.x + pBuf.next.size.w;
//  pBuf.size.y = pBuf.next.size.y;
//  
//  /* Prev Leader Name Button */
//  pBuf = pBuf.prev;
//  pBuf.size.x = pBuf.next.next.size.x - pBuf.size.w;
//  pBuf.size.y = pBuf.next.size.y;
//  
//  /* Change Leader Sex Button */
//  pBuf = pBuf.prev;
//  pBuf.size.x = pWindow.size.x + pWindow.size.w / 2 +
//  				(pWindow.size.w/2 - pBuf.size.w) / 2;
//  pBuf.size.y = pBuf.next.size.y + pBuf.next.size.h + 20;
//  
//  /* First City Style Button */
//  pBuf = pBuf.prev;
//  pBuf.size.x = pWindow.size.x + pWindow.size.w / 2 +
//  				(pWindow.size.w/2 - len) / 2;
//  pBuf.size.y = pBuf.next.size.y + pBuf.next.size.h + 20;
//  
//  /* Rest City Style Buttons */
//  if (pBuf != pLast_City_Style) {
//    do {
//      pBuf = pBuf.prev;
//      pBuf.size.x = pBuf.next.size.x + pBuf.next.size.w + 3;
//      pBuf.size.y = pBuf.next.size.y;
//    } while (pLast_City_Style != pBuf);
//  }
//  
//  /* Disconnect Button */
//  pBuf = pBuf.prev;
//  pBuf.size.x = pWindow.size.x + pWindow.size.w / 2 +
//  				(pWindow.size.w/2 - pBuf.size.w) / 2;
//  pBuf.size.y = pBuf.next.size.y + pBuf.next.size.h + 20;
//  
//  /* Start Button */
//  pBuf = pBuf.prev;
//  pBuf.size.x = pBuf.next.size.x;
//  pBuf.size.y = pBuf.next.size.y + pBuf.next.size.h + 10;
//  
//  /* -------------------------------------------------------------------- */
//  
//  select_random_leader(pSetup.nation);
//  
//  redraw_group(pNationDlg.pBeginWidgetList, pWindow, 0);
//  
//  flush_rect(pWindow.size);
//  
//}
//
///**************************************************************************
//  Close the nation selection dialog.  This should allow the user to
//  (at least) select a unit to activate.
//**************************************************************************/
//void popdown_races_dialog()
//{
//  if (pNationDlg) {
//    popdown_window_group_dialog(pNationDlg.pBeginWidgetList,
//			  pNationDlg.pEndWidgetList);
//
//    cancel_help_dlg_callback(null);
//    FREE(pNationDlg.pScroll);
//    FREE(pNationDlg);
//  }
//}
//
///**************************************************************************
//  In the nation selection dialog, make already-taken nations unavailable.
//  This information is contained in the packet_nations_used packet.
//**************************************************************************/
//void races_toggles_set_sensitive(boolean *nations_used)
//{
//  NAT pSetup = (NAT )(pNationDlg.pEndWidgetList.data.ptr);
//  int nation;
//  boolean change = false;
//  GUI pNat;
//
//  for (nation = 0; nation < Game.game.playable_nation_count; nation++) {
//    if (nations_used[nation]) {
//      util.freelog(Log.LOG_DEBUG,"  [%d]: %d = %s", nation, nations_used[nation],
//	      get_nation_name(nation));
//
//      pNat = get_widget_pointer_form_main_list(MAX_ID - nation);
//      set_wstate(pNat, FC_WS_DISABLED);
//    
//      if (nation == pSetup.nation) {
//        change = true;
//      }
//    }
//  }
//  
//  if (change) {
//    do {
//      pSetup.nation = Rand.myrand(Game.game.playable_nation_count);
//      pNat = get_widget_pointer_form_main_list(MAX_ID - pSetup.nation);
//    } while(get_wstate(pNat) == FC_WS_DISABLED);
//    if (get_wstate(pSetup.pName_Edit) == FC_WS_PRESSED) {
//      force_exit_from_event_loop();
//      set_wstate(pSetup.pName_Edit, FC_WS_NORMAL);
//    }
//    change_nation_label();
//    enable(MAX_ID - 1000 - pSetup.nation_city_style);
//    pSetup.nation_city_style = get_nation_city_style(pSetup.nation);
//    disable(MAX_ID - 1000 - pSetup.nation_city_style);
//    select_random_leader(pSetup.nation);
//  }
//  redraw_group(pNationDlg.pBeginWidgetList, pNationDlg.pEndWidgetList, 0);
//  flush_rect(pNationDlg.pEndWidgetList.size);
//}
}