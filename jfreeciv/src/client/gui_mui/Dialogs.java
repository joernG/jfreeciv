package client.gui_mui;

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
//#ifdef HAVE_CONFIG_H
//#include <config.h>
//#endif
//
//#include <stdio.h>
//#include <stdlib.h>
//#include <string.h>
//
//#include <mui/NListview_MCC.h>
//#include <libraries/mui.h>
//
//#include <clib/alib_protos.h>
//#include <proto/exec.h>
//#include <proto/utility.h>
//#include <proto/muimaster.h>
//
//#include "version.h"
//
//#include "fcintl.h"
//#include "game.h"
//#include "government.h"
//#include "mem.h"
//#include "player.h"
//#include "packets.h"
//#include "support.h"
//
//#include "chatline.h"
//#include "clinet.h"
//#include "connectdlg.h"
//#include "control.h"
//#include "dialogs.h"
//#include "gui_main.h"
//#include "mapview.h"
//#include "options.h"
//#include "packhand.h"
//
//#include "muistuff.h"
//#include "mapclass.h"
//#include "autogroupclass.h"
//
///****************************************************************
// ...
//*****************************************************************/
//void request_player_revolution()
//{
//  start_revolution();
//}
///****************************************************************
// ...
//*****************************************************************/
//void request_player_government(int g)
//{
//  struct packet_player_request packet;
//
//  packet.government=g;
//  send_packet_player_request(&aconnection, &packet, PACKET_PLAYER_GOVERNMENT);
//}
//
//static Object *unitsel_wnd;
//
//static int is_showing_government_dialog;
//
//static int is_showing_pillage_dialog = false;
//static int unit_to_use_to_pillage;
//
//static int diplomat_dialog_open;
//static int diplomat_id;
//static int diplomat_target_id;
//
//static int is_showing_unit_connect_dialog = false;
//static int unit_to_use_to_connect;
//static int connect_unit_x;
//static int connect_unit_y;
//
///****************************************************************
// Called from the Application object so it is safe to dispose the
// window
//*****************************************************************/
//static void notify_close_real(Object **pwnd)
//{
//  set(*pwnd,MUIA_Window_Open,false);
//  DoMethod(app, OM_REMMEMBER, *pwnd);
//  MUI_DisposeObject(*pwnd);
//}
//
///****************************************************************
// Close the notify window
//*****************************************************************/
//static void notify_close(Object **pwnd)
//{
//  set(*pwnd, MUIA_Window_Open, false);
//  DoMethod(app, MUIM_Application_PushMethod, app, 4, MUIM_CallHook, &civstandard_hook, notify_close_real, *pwnd);
//}
//
///****************************************************************
// Popup the notify window
//*****************************************************************/
//void popup_notify_dialog(final String caption, final String headline,
//			 final String lines)
//{
//  Object *wnd;
//  Object *listview;
//  Object *close_button;
//
//  wnd = WindowObject,
//    MUIA_Window_Title, (caption),	/* Never freed! */
//    MUIA_Window_ID, MAKE_ID('P','O','P','U'),
//    WindowContents, VGroup,
//      Child, TextObject,
//        MUIA_Text_Contents, headline,
//        MUIA_Text_PreParse, "\33c",
//        End,
//      Child, listview = NListviewObject,
//        MUIA_NListview_NList, NListObject,
//          ReadListFrame,
//          MUIA_Font, MUIV_Font_Fixed,
//          MUIA_NList_Input, false,
//          MUIA_NList_TypeSelect,MUIV_NList_TypeSelect_Char,
//          MUIA_NList_finalructHook, MUIV_NList_finalructHook_String,
//          MUIA_NList_DestructHook , MUIV_NList_DestructHook_String,
//          MUIA_NList_AutoCopyToClip, true,
//          End,
//        End,
//        Child, close_button = MakeButton("_Close"),
//      End,
//    End;
//
//  if(wnd)
//  {
//    DoMethod(wnd, MUIM_Notify, MUIA_Window_CloseRequest, true, app, 4, MUIM_CallHook, &civstandard_hook, notify_close, wnd);
//    DoMethod(close_button, MUIM_Notify, MUIA_Pressed, false, app, 4, MUIM_CallHook, &civstandard_hook, notify_close, wnd);
//    DoMethod(listview, MUIM_NList_Insert, lines, -2, MUIV_NList_Insert_Bottom);
//    DoMethod(app, OM_ADDMEMBER, wnd);
//    set(wnd,MUIA_Window_Open, true);
//  }
//}
//
///****************************************************************
// Close the notify window and goto position
//*****************************************************************/
//struct notify_goto_close_arg {
//  Object *pwnd;
//  int     x;
//  int     y;
//};
//static void notify_goto_close(notify_goto_close_arg obj)
//{
//  center_tile_mapcanvas(obj.x, obj.y);
//  set(obj.pwnd, MUIA_Window_Open, false);
//  DoMethod(app, MUIM_Application_PushMethod, app, 4, MUIM_CallHook, &civstandard_hook, notify_close_real, obj.pwnd);
//}
//
///****************************************************************
//...
//*****************************************************************/
//void popup_notify_goto_dialog(final String headline, final String lines,
//			      int x, int y)
//{
//  Object *wnd;
//  Object *close_button;
//  Object *goto_button;
//
//  wnd = WindowObject,
//    MUIA_Window_Title, headline,
//    MUIA_Window_ID, MAKE_ID('P','O','G','O'),
//    WindowContents, VGroup,
//      Child, TextObject,
//        MUIA_Text_Contents, lines,
//        MUIA_Text_PreParse, "\33c",
//        End,
//      Child, HGroup,
//        Child, close_button = MakeButton("_Close"),
//        Child, goto_button = MakeButton("_Goto and Close"),
//        End,
//      End,
//    End;
//
//  if(wnd)
//  {
//    DoMethod(wnd, MUIM_Notify, MUIA_Window_CloseRequest, true, app, 4, MUIM_CallHook, &civstandard_hook, notify_close, wnd);
//    DoMethod(close_button, MUIM_Notify, MUIA_Pressed, false, app, 4, MUIM_CallHook, &civstandard_hook, notify_close, wnd);
//    DoMethod(goto_button, MUIM_Notify, MUIA_Pressed, false, app, 6, MUIM_CallHook, &civstandard_hook, notify_goto_close, wnd, x, y);
//    DoMethod(app, OM_ADDMEMBER, wnd);
//    set(wnd,MUIA_Window_Open, true);
//  }
//}
//
///****************************************************************
// Callback for the Yes button in the bribe confirmation window
//*****************************************************************/
//static void diplomat_bribe_yes(popup_message_data data)
//{
//  request_diplomat_action(DIPLOMAT_BRIBE, diplomat_id,
//			  diplomat_target_id, 0);
//
//  message_close(data);
//}
//
///****************************************************************
// Popup the bribe confirmation window
//*****************************************************************/
//void popup_bribe_dialog(unit punit)
//{
//  char buf[128];
//  
//  if(Game.game.player_ptr.economic.gold>=punit.bribe_cost)
//  {
//    buf = util.my_snprintf("Bribe unit for %d gold?\nTreasury contains %d gold.",
//                punit.bribe_cost, Game.game.player_ptr.economic.gold);
//
//    popup_message_dialog(main_wnd, "Bribe Enemy Unit", buf,
//                         "Yes", diplomat_bribe_yes, 0,
//                         "No", message_close, 0,
//                         0);
//  } else
//  {
//    buf = util.my_snprintf( "Bribing the unit costs %d gold.\nTreasury contains %d gold.",
//                punit.bribe_cost, Game.game.player_ptr.economic.gold);
//
//    popup_message_dialog(main_wnd, "Traitors Demand Too Much!", buf,
//                         "Darn", message_close,0,
//                         0);
//  }
//}
//
//struct spy_data
//{
//  Object *wnd;
//  Object *listview;
//};
//
///****************************************************************
// Called from the Application object so it is safe to dispose the
// window
//*****************************************************************/
//static void spy_real_close(spy_data data)
//{
//  Object *wnd = data.wnd;
//  set(wnd,MUIA_Window_Open,false);
//  DoMethod(app,OM_REMMEMBER,data.wnd);
//  MUI_DisposeObject(wnd);
//}
//
///****************************************************************
// Close the spy window. Used as advance steal and improvemnet
// sabotage window
//*****************************************************************/
//static void spy_close(spy_data data)
//{
//  DoMethod(app, MUIM_Application_PushMethod, app, 4, MUIM_CallHook, &civstandard_hook, spy_real_close, data.wnd);
//}
//
///****************************************************************
// Callback for the steal button
//*****************************************************************/
//static void advance_steal(spy_data data)
//{
//  LONG which;
//  DoMethod(data.listview, MUIM_NList_GetEntry, MUIV_NList_GetEntry_Active,&which);
//
//  if(which)
//  {
//    which-=100;
//    if (Game.find_unit_by_id(diplomat_id) && 
//	Game.find_city_by_id(diplomat_target_id)) { 
//      request_diplomat_action(DIPLOMAT_STEAL, diplomat_id,
//			      diplomat_target_id, which);
//    }
//  }
//
//  spy_close(data);
//  process_diplomat_arrival(null, 0);
//}
//
///****************************************************************
// Display function for the technology listview
//*****************************************************************/
//HOOKPROTONH(advance_display, void, char **array, APTR msg)
//{
//  LONG which = (ULONG) msg;
//
//  if(which)
//  {
//    int tech = which-100;
//    if (tech == Game.game.num_tech_types) *array = "At Spy's Discretion";
//    else *array = advances[which-100].name;
//  }
//  else
//    *array = "Technology";
//}
//
//
///****************************************************************
//...
//*****************************************************************/
//static void create_advances_list(player pplayer,
//				player pvictim)
//{
//  Object *listview,*steal_button,*cancel_button,*wnd;
//  static struct Hook disphook;
//
//  disphook.h_Entry = (HOOKFUNC)advance_display;
//
//  wnd = WindowObject,
//    MUIA_Window_Title, "Steal Technology",
//    MUIA_Window_ID, MAKE_ID('S','P','S','T'),
//    WindowContents, VGroup,
//      Child, TextObject,
//        MUIA_Text_Contents, "Select Advance to Steal",
//        MUIA_Text_PreParse,"\33c",
//        End,
//      Child, listview = NListviewObject,
//          MUIA_NListview_NList, NListObject,
//          MUIA_NList_DisplayHook, &disphook,
//          End,
//        End,
//      Child, HGroup,
//        Child, steal_button = MakeButton("_Steal"),
//        Child, cancel_button = MakeButton("_Cancel"),
//        End,
//      End,
//    End;
//
//  if(wnd)
//  {
//    int i;
//    if (pvictim)
//    {
//      /* you don't want to know what lag can do -- Syela */
//      int any_tech = false;
//      for(i=A_FIRST; i<Game.game.num_tech_types; i++)
//      {
//        if(get_invention(pvictim, i)==TECH_KNOWN && (get_invention(pplayer, i)==TECH_UNKNOWN || get_invention(pplayer, i)==TECH_REACHABLE))
//        {
//          DoMethod(listview, MUIM_NList_InsertSingle, i+100,MUIV_NList_Insert_Bottom);
//          any_tech = true;
//        }
//      }
//
//      if (any_tech)
//	DoMethod(listview, MUIM_NList_InsertSingle, 100+Game.game.num_tech_types,MUIV_NList_Insert_Bottom);
//    }
//
//    DoMethod(wnd,MUIM_Notify, MUIA_Window_CloseRequest, true, app, 5, MUIM_CallHook, &civstandard_hook, spy_close, wnd, listview);
//    DoMethod(cancel_button,MUIM_Notify, MUIA_Pressed, false, app, 5, MUIM_CallHook, &civstandard_hook, spy_close, wnd, listview);
//    DoMethod(steal_button,MUIM_Notify, MUIA_Pressed, false, app, 5, MUIM_CallHook, &civstandard_hook, advance_steal, wnd, listview);
//    DoMethod(app,OM_ADDMEMBER,wnd);
//    set(wnd,MUIA_Window_Open, true);
//  }
//}
//
///****************************************************************
// Callback for the sabotage button
//*****************************************************************/
//static void imprv_sabotage(spy_data data)
//{
//  LONG which;
//  DoMethod(data.listview, MUIM_NList_GetEntry, MUIV_NList_GetEntry_Active,&which);
//  if(which)
//  {
//    if(Game.find_unit_by_id(diplomat_id) && Game.find_city_by_id(diplomat_target_id))
//    { 
//      request_diplomat_action(DIPLOMAT_SABOTAGE, diplomat_id,
//			      diplomat_target_id, which - 100 + 1);
//    }
//  }
//
//  spy_close(data);
//  process_diplomat_arrival(null, 0);
//}
//
//
///****************************************************************
// Display function for the sabotage listview
//*****************************************************************/
//HOOKPROTONH(imprv_display, void, char **array, APTR which)
//{
//  int imprv = ((LONG) which)-100;
//  if (imprv == -1) *array = "City Production";
//  else if (imprv == Improvement.B_LAST) *array = "At Spy's Discretion";
//  else *array = Improvement.get_improvement_name(imprv);
//}
//
//
///****************************************************************
//...
//*****************************************************************/
//static void create_improvements_list(city pcity)
//{  
//  Object *listview,*steal_button,*cancel_button,*wnd;
//  static struct Hook disphook;
//
//  disphook.h_Entry = (HOOKFUNC)imprv_display;
//
//  wnd = WindowObject,
//    MUIA_Window_Title, "Sabotage Improvements",
//    MUIA_Window_ID, MAKE_ID('S','P','I','P'),
//    WindowContents, VGroup,
//      Child, TextObject,
//        MUIA_Text_Contents, "Select Improvement to Sabotage",
//        MUIA_Text_PreParse,"\33c",
//        End,
//      Child, listview = NListviewObject,
//        MUIA_NListview_NList, NListObject,
//          MUIA_NList_DisplayHook, &disphook,
//          End,
//        End,
//      Child, HGroup,
//        Child, steal_button = MakeButton("_Sabotage"),
//        Child, cancel_button = MakeButton("_Cancel"),
//        End,
//      End,
//    End;
//
//  if(wnd)
//  {
//    int any_improvements=false;
//
//    DoMethod(listview, MUIM_NList_InsertSingle, 100-1,MUIV_NList_Insert_Bottom);
//	for (int i = 0; i < game.num_impr_types; i++) {
//	if((pcity).improvements[i] == Improvement.I_NONE) {
//		continue;
//	}
//      if (get_improvement_type(i).sabotage > 0) {
//      {
//        DoMethod(listview, MUIM_NList_InsertSingle, i+100,MUIV_NList_Insert_Bottom);
//        any_improvements = true;
//      }
//    } ;
//
//    if (any_improvements)
//    {
//      DoMethod(listview, MUIM_NList_InsertSingle, Improvement.B_LAST + 100, MUIV_NList_Insert_Bottom);
//    }
//
//    DoMethod(wnd,MUIM_Notify, MUIA_Window_CloseRequest, true, app, 4, MUIM_CallHook, &civstandard_hook, spy_close, wnd);
//    DoMethod(cancel_button,MUIM_Notify, MUIA_Pressed, false, app, 4, MUIM_CallHook, &civstandard_hook, spy_close, wnd);
//    DoMethod(steal_button,MUIM_Notify, MUIA_Pressed, false, app, 5, MUIM_CallHook, &civstandard_hook, imprv_sabotage, wnd, listview);
//    DoMethod(app,OM_ADDMEMBER,wnd);
//    set(wnd,MUIA_Window_Open, true);
//  }
//}
//
///****************************************************************
// Callback for the Yes button in the incite window
//*****************************************************************/
//static void diplomat_incite_yes(popup_message_data data)
//{
//  request_diplomat_action(DIPLOMAT_INCITE, diplomat_id,
//			  diplomat_target_id, 0);
//
//  message_close(data);
//  process_diplomat_arrival(null, 0);
//}
//
///****************************************************************
// Callback for the No button in the incite window
//*****************************************************************/
//static void diplomat_incite_no(popup_message_data data)
//{
//  message_close(data);
//  process_diplomat_arrival(null, 0);
//}
//
///****************************************************************
// Popup the yes/no dialog for inciting, since we know the cost now
//*****************************************************************/
//void popup_incite_dialog(city pcity)
//{
//  char buf[128];
//
//  if (pcity.incite_revolt_cost == INCITE_IMPOSSIBLE_COST) {
//    buf = util.my_snprintf( "You can't incite a revolt in %s.",
//		pcity.name);
//    popup_message_dialog(main_wnd, "City can't be incited!", buf,
//			 "Darn", diplomat_incite_no, 0, 0);
//  } else if (Game.game.player_ptr.economic.gold >= pcity.incite_revolt_cost) {
//    buf = util.my_snprintf("Incite a revolt for %d gold?\nTreasury contains %d gold.",
//            pcity.incite_revolt_cost, Game.game.player_ptr.economic.gold);
//    diplomat_target_id = pcity.id;
//
//    popup_message_dialog(main_wnd, "Incite a Revolt!", buf,
//                         "_Yes", diplomat_incite_yes, 0,
//                         "_No", diplomat_incite_no, 0,
//                         0);
//
//  } else
//  {
//    buf = util.my_snprintf( "Inciting a revolt costs %d gold.\nTreasury contains %d gold.",
//                pcity.incite_revolt_cost, Game.game.player_ptr.economic.gold);
//    popup_message_dialog(main_wnd, "Traitors Demand Too Much!", buf,
//                         "Darn", diplomat_incite_no,0,
//                         0);
//  }
//}
//
///****************************************************************
// Callback for Establish Embassy button
//*****************************************************************/
//static void diplomat_embassy(popup_message_data data)
//{
//  message_close(data);
//  diplomat_dialog_open = 0;
//
//   if(Game.find_unit_by_id(diplomat_id) && 
//     (Game.find_city_by_id(diplomat_target_id))) { 
//    request_diplomat_action(DIPLOMAT_EMBASSY, diplomat_id,
//			    diplomat_target_id, 0);
//  }
//  process_diplomat_arrival(null, 0);
//}
//
///****************************************************************
// Callback for Investigate button
//*****************************************************************/
//static void diplomat_investigate(popup_message_data data)
//{
//  message_close(data);
//  diplomat_dialog_open=0;
//
//  if(Game.find_unit_by_id(diplomat_id) && 
//     (Game.find_city_by_id(diplomat_target_id))) { 
//    request_diplomat_action(DIPLOMAT_INVESTIGATE, diplomat_id,
//			    diplomat_target_id, 0);
//  }
//  process_diplomat_arrival(null, 0);
//}
//
///****************************************************************
// Callback for Sabotage button
//*****************************************************************/
//static void diplomat_sabotage(popup_message_data data)
//{
//  message_close(data);
//  diplomat_dialog_open=0;
//
//  if(Game.find_unit_by_id(diplomat_id) && 
//     Game.find_city_by_id(diplomat_target_id)) { 
//    request_diplomat_action(DIPLOMAT_SABOTAGE, diplomat_id,
//			    diplomat_target_id, -1);
//  }
//  process_diplomat_arrival(null, 0);
//}
//
///****************************************************************
// Callback for Steal button
//*****************************************************************/
//static void diplomat_steal(popup_message_data data)
//{
//  message_close(data);
//  diplomat_dialog_open=0;
//
//  if(Game.find_unit_by_id(diplomat_id) && 
//     Game.find_city_by_id(diplomat_target_id)) { 
//    request_diplomat_action(DIPLOMAT_STEAL, diplomat_id,
//			    diplomat_target_id, 0);
//  }
//  process_diplomat_arrival(null, 0);
//}
//
///****************************************************************
// Callback for Incite button
// Ask the server how much the revolt is going to cost us
//*****************************************************************/
//static void diplomat_incite(popup_message_data data)
//{
//  city pcity;
//  struct packet_generic_integer packet;
//
//  message_close(data);
//  diplomat_dialog_open=0;
//
//  if(Game.find_unit_by_id(diplomat_id) && 
//     (pcity=Game.find_city_by_id(diplomat_target_id))) { 
//    packet.value = diplomat_target_id;
//    send_packet_generic_integer(&aconnection, PACKET_INCITE_INQ, &packet);
//  }
//}
//
///****************************************************************
// Callback for Bribe button for Units
//*****************************************************************/
//static void diplomat_bribe_unit(popup_message_data data)
//{
//  struct packet_generic_integer packet;
//
//  message_close(data);
//  
//  if(Game.find_unit_by_id(diplomat_id) && 
//     Game.find_unit_by_id(diplomat_target_id)) { 
//    packet.value = diplomat_target_id;
//    send_packet_generic_integer(&aconnection, PACKET_INCITE_INQ, &packet);
//  }
//}
//
///****************************************************************
//  Callback from diplomat/spy dialog for "keep moving".
//  (This should only occur when entering allied city.)
//*****************************************************************/
//static void diplomat_move(popup_message_data data)
//{
//  unit punit;
//  city pcity;
//  
//  message_close(data);
//  diplomat_dialog_open=0;
//
//  if( (punit=Game.find_unit_by_id(diplomat_id))
//      && (pcity=Game.find_city_by_id(diplomat_target_id))
//      && !Map.same_pos(punit.tile, pcity.tile)) {
//    request_diplomat_action(DIPLOMAT_MOVE, diplomat_id,
//			    diplomat_target_id, 0);
//  }
//  process_diplomat_arrival(null, 0);
//}
//
///****************************************************************
// Callback for Cancel Button
//*****************************************************************/
//static void diplomat_cancel(popup_message_data data)
//{
//  message_close(data);
//  diplomat_dialog_open=0;
//
//  process_diplomat_arrival(null, 0);
//}
//
///****************************************************************
// Callback for Poison button for Units
//*****************************************************************/
//static void spy_poison(popup_message_data data)
//{
//  message_close(data);
//  diplomat_dialog_open=0;
//
//  if(Game.find_unit_by_id(diplomat_id) &&
//    (Game.find_city_by_id(diplomat_target_id))) {
//    request_diplomat_action(SPY_POISON, diplomat_id, diplomat_target_id, 0);
//  }
//  process_diplomat_arrival(null, 0);
//}
//
///****************************************************************
// Requests up-to-date list of improvements, the return of
// which will trigger the popup_sabotage_dialog() function.
//*****************************************************************/
//static void spy_request_sabotage_list(popup_message_data data)
//{
//  message_close(data);
//  diplomat_dialog_open=0;
//
//  if(Game.find_unit_by_id(diplomat_id) &&
//     (Game.find_city_by_id(diplomat_target_id))) {
//    request_diplomat_action(SPY_GET_SABOTAGE_LIST, diplomat_id,
//			    diplomat_target_id, 0);
//  }
//}
//
///****************************************************************
// Pops-up the Spy sabotage dialog, upon return of list of
// available improvements requested by the above function.
//*****************************************************************/
//void popup_sabotage_dialog(city pcity)
//{
//  create_improvements_list(pcity);
//}
//
///****************************************************************
// Callback for Steal button
//*****************************************************************/
//static void spy_steal(popup_message_data data)
//{
//  city pvcity = Game.find_city_by_id(diplomat_target_id);
//  player pvictim;
//
//  message_close(data);
//  diplomat_dialog_open=0;
//
//  if(pvcity)
//  {
//    pvictim = City.city_owner(pvcity);
//    create_advances_list(Game.game.player_ptr, pvictim);
//  }
//}
//
///****************************************************************
// Callback for Sabotage button (for units)
//*****************************************************************/
//static void spy_sabotage_unit(popup_message_data data)
//{
//  request_diplomat_action(SPY_SABOTAGE_UNIT, diplomat_id,
//			  diplomat_target_id, 0);
//
//  message_close(data);
//}
//
///****************************************************************
//...
//*****************************************************************/
//void popup_diplomat_dialog(unit punit, int dest_x, int dest_y)
//{
//  /* optimize */
//  city pcity;
//  unit ptunit;
//
//  struct New_Msg_Dlg msg_dlg[9];
//  int i=0;
//
//  diplomat_id = punit.id;
//  
//  if((pcity=Map.map_get_city(dest_x, dest_y)))
//  {
//    /* Spy/Diplomat acting against a city */ 
// 
//    diplomat_target_id=pcity.id;
//
//    if(!unit_flag(punit, F_SPY))
//    {
//      if(diplomat_can_do_action(punit, DIPLOMAT_EMBASSY, dest_x, dest_y))
//      {
//      	msg_dlg[i].label = "_Establish embassy";
//      	msg_dlg[i].function = (APTR)diplomat_embassy;
//      	msg_dlg[i].data = null;
//      	i++;
//      }
//
//      if(diplomat_can_do_action(punit, DIPLOMAT_INVESTIGATE, dest_x, dest_y))
//      {
//      	msg_dlg[i].label = "_Investigate city",
//      	msg_dlg[i].function = (APTR)diplomat_investigate;
//      	msg_dlg[i].data = null;
//      	i++;
//      }
//
//      if(diplomat_can_do_action(punit, DIPLOMAT_SABOTAGE, dest_x, dest_y))
//      {
//      	msg_dlg[i].label = "_Sabotage city";
//        msg_dlg[i].function = (APTR)diplomat_sabotage;
//        msg_dlg[i].data = null;
//      	i++;
//      }
//
//      if(diplomat_can_do_action(punit, DIPLOMAT_STEAL, dest_x, dest_y))
//      {
//      	msg_dlg[i].label = "Steal _technology";
//      	msg_dlg[i].function = (APTR)diplomat_steal;
//      	msg_dlg[i].data = null;
//      	i++;
//      }
//
//      if(diplomat_can_do_action(punit, DIPLOMAT_INCITE, dest_x, dest_y))
//      {
//      	msg_dlg[i].label = "Incite a _revolt";
//      	msg_dlg[i].function = (APTR)diplomat_incite;
//      	msg_dlg[i].data = null;
//      	i++;
//      }
//
//      if(diplomat_can_do_action(punit, DIPLOMAT_MOVE, dest_x, dest_y))
//      {
//      	msg_dlg[i].label = "_Keep moving";
//      	msg_dlg[i].function = (APTR)diplomat_move;
//      	msg_dlg[i].data = null;
//      	i++;
//      }
//
//      msg_dlg[i].label = "_Cancel";
//      msg_dlg[i].function = (APTR)diplomat_cancel;
//      msg_dlg[i++].data = null;
//
//      msg_dlg[i].label = null;
//
//      popup_message_dialog_args(main_wnd, "Choose Your Diplomat's Strategy",
//                                "Sir, the diplomat is waiting for your command",
//                                msg_dlg);
//    } else
//    {
//      if(diplomat_can_do_action(punit, DIPLOMAT_EMBASSY, dest_x, dest_y))
//      {
//      	msg_dlg[i].label = "_Establish embassy";
//      	msg_dlg[i].function = (APTR)diplomat_embassy;
//      	msg_dlg[i].data = null;
//      	i++;
//      }
//
//      if(diplomat_can_do_action(punit, DIPLOMAT_INVESTIGATE, dest_x, dest_y))
//      {
//      	msg_dlg[i].label = "_Investigate city";
//      	msg_dlg[i].function = (APTR)diplomat_investigate;
//      	msg_dlg[i].data = null;
//      	i++;
//      }
//
//      if(diplomat_can_do_action(punit, SPY_POISON, dest_x, dest_y))
//      {
//      	msg_dlg[i].label = "_Poison city";
//      	msg_dlg[i].function = (APTR)spy_poison;
//      	msg_dlg[i].data = null;
//      	i++;
//      }
//
//      if(diplomat_can_do_action(punit, DIPLOMAT_SABOTAGE, dest_x, dest_y))
//      {
//      	msg_dlg[i].label = "Industrial _sabotage";
//      	msg_dlg[i].function = (APTR)spy_request_sabotage_list;
//      	msg_dlg[i].data = null;
//      	i++;
//      }
//
//      if(diplomat_can_do_action(punit, DIPLOMAT_STEAL, dest_x, dest_y))
//      {
//      	msg_dlg[i].label = "Steal _technology";
//      	msg_dlg[i].function = (APTR)spy_steal;
//      	msg_dlg[i].data = null;
//      	i++;
//      }
//
//      if(diplomat_can_do_action(punit, DIPLOMAT_INCITE, dest_x, dest_y))
//      {
//      	msg_dlg[i].label = "Incite a _revolt";
//      	msg_dlg[i].function = (APTR)diplomat_incite;
//      	msg_dlg[i].data = null;
//      	i++;
//      }
//
//      if(diplomat_can_do_action(punit, DIPLOMAT_MOVE, dest_x, dest_y))
//      {
//      	msg_dlg[i].label = "_Keep moving";
//      	msg_dlg[i].function = (APTR)diplomat_move;
//      	msg_dlg[i].data = null;
//      	i++;
//      }
//
//      msg_dlg[i].label = "_Cancel";
//      msg_dlg[i].function = (APTR)diplomat_cancel;
//      msg_dlg[i++].data = null;
//
//      msg_dlg[i].label = null;
//
//      popup_message_dialog_args(main_wnd, "Choose Your Spy's Strategy",
//                                "Sir, the spy is waiting for your command",
//                                msg_dlg);
//
//    }
//    diplomat_dialog_open=1;
//  } else
//  {
//    if((ptunit=unit_list_get(&map_get_tile(dest_x, dest_y).units, 0)))
//    {
//      /* Spy/Diplomat acting against a unit */ 
//      diplomat_target_id=ptunit.id;
// 
//      if(diplomat_can_do_action(punit, DIPLOMAT_BRIBE, dest_x, dest_y))
//      {
//      	msg_dlg[i].label = "_Bribe Enemy Unit";
//      	msg_dlg[i].function = (APTR)diplomat_bribe_unit;
//      	msg_dlg[i].data = null;
//      	i++;
//      }
//
//      if(diplomat_can_do_action(punit, SPY_SABOTAGE_UNIT, dest_x, dest_y))
//      {
//      	msg_dlg[i].label = "_Sabotage Enemy Unit";
//      	msg_dlg[i].function = (APTR)spy_sabotage_unit;
//      	msg_dlg[i].data = null;
//      	i++;
//      }
//
//      msg_dlg[i].label = "_Cancel";
//      msg_dlg[i].function = (APTR)message_close;
//      msg_dlg[i++].data = null;
//
//      msg_dlg[i].label = null;
//
//      popup_message_dialog_args(main_wnd, "Subvert Enemy Unit",
//                               (!unit_flag(punit, F_SPY))?
//                                "Sir, the diplomat is waiting for your command":
//                                "Sir, the spy is waiting for your command",
//                                msg_dlg);
//
//    }
//  }
//}
//
///****************************************************************
//...
//*****************************************************************/
//boolean diplomat_dialog_is_open()
//{
//  return diplomat_dialog_open;
//}
//
//
///****************************************************************/
//
//static int caravan_dialog;
//
//struct caravan_data
//{
//  int caravan_city_id;
//  int caravan_unit_id;
//};
//
//
///****************************************************************
// Callback for the Keep Moving button. Closes the window
//*****************************************************************/
//static void caravan_keep(popup_message_data data)
//{
//  message_close(data);
//  caravan_dialog = 0;
//  process_caravan_arrival(null);
//}
//
///****************************************************************
// Callback for the Help build wonder button
//*****************************************************************/
//static void caravan_help(popup_message_data data)
//{
//  caravan_data cd = (struct caravan_data*)data.data;
//  if(cd)
//  {
//    /* Should be made to request_xxx */
//    struct packet_unit_request req;
//    req.unit_id=cd.caravan_unit_id;
//    req.city_id=cd.caravan_city_id;
//    req.name[0]='\0';
//    send_packet_unit_request(&aconnection, &req, PACKET_UNIT_HELP_BUILD_WONDER);
//    free(cd);
//  }
//  caravan_keep(data);
//}
//
///****************************************************************
// Callback for the Establish Trade Route button
//*****************************************************************/
//static void caravan_establish(popup_message_data data)
//{
//  caravan_data cd = (struct caravan_data*)data.data;
//  if(cd)
//  {
//    /* Should be made to request_xxx */
//    struct packet_unit_request req;
//    req.unit_id=cd.caravan_unit_id;
//    req.city_id=cd.caravan_city_id;
//    req.name[0]='\0';
//    send_packet_unit_request(&aconnection, &req, PACKET_UNIT_ESTABLISH_TRADE);
//    free(cd);
//  }
//  caravan_keep(data);
//}
//
//
///****************************************************************
//...
//*****************************************************************/
//void popup_caravan_dialog(unit punit, city phomecity, city pdestcity)
//{
//  struct New_Msg_Dlg msg_dlg[4];
//  int i=0;
//  char buf[128];
//
//  buf = util.my_snprintf("Your caravan from %s reaches the city of %s.\nWhat now?",
//          phomecity.name, pdestcity.name);
//
//  if (City.can_cities_trade(phomecity, pdestcity))
//  {
//    caravan_data cd = malloc_struct(struct caravan_data);
//    if(cd)
//    {
//      cd.caravan_city_id = pdestcity.id;
//      cd.caravan_unit_id = punit.id;
//      if (City.can_establish_trade_route(phomecity, pdestcity))
//      {
//        msg_dlg[i].label = "_Establish traderoute";
//      } else {
//	 msg_dlg[i].label = "_Enter Marketplace";
//      }
//      msg_dlg[i].function = (APTR)caravan_establish;
//      msg_dlg[i].data = (APTR)cd;
//      i++;
//    }
//  }
//
//  if(unit_can_help_build_wonder(punit, pdestcity))
//  {
//    caravan_data cd = malloc_struct(struct caravan_data);
//    if(cd)
//    {
//      cd.caravan_city_id = pdestcity.id;
//      cd.caravan_unit_id = punit.id;
//
//      msg_dlg[i].label = "_Help build Wonder";
//      msg_dlg[i].function = (APTR)caravan_help;
//      msg_dlg[i].data = (APTR)cd;
//      i++;
//    }
//  }
//
//  msg_dlg[i].label = "_Keep moving";
//  msg_dlg[i].function = (APTR)caravan_keep;
//  msg_dlg[i].data = null;
//  i++; msg_dlg[i].label = null;
//
//  caravan_dialog = 1;
//  popup_message_dialog_args(main_wnd, "Your Caravan Has Arrived",
//                            buf,msg_dlg);
//}
//
///****************************************************************
//...
//*****************************************************************/
//boolean caravan_dialog_is_open()
//{
//  return caravan_dialog;
//}
//
///****************************************************************
// Callback for the Yes button in the revolution confirmation
// window
//*****************************************************************/
//static void revolution_yes( popup_message_data data)
//{
//  request_player_revolution();
//  destroy_message_dialog( data.wnd);
//}
//
///****************************************************************
//...
//*****************************************************************/
//void popup_revolution_dialog()
//{
//  popup_message_dialog(main_wnd, "Revolution!", "You say you wanna revolution?",
//                       "_Yes",revolution_yes, 0,
//                       "_No",message_close, 0,
//                       null);
//}
//
///****************************************************************
// Callback for a pillage button
//*****************************************************************/
//static void pillage_button(popup_message_data msg)
//{
//  is_showing_pillage_dialog = false;
//
//  if(msg.data)
//  {
//    unit punit = Game.find_unit_by_id (unit_to_use_to_pillage);
//    if (punit) {
//      request_new_unit_activity_targeted(punit,
//                                         ACTIVITY_PILLAGE,
//                                         (int)msg.data);
//    }
//  }
//  message_close(msg);
//}
//
///****************************************************************
//...
//*****************************************************************/
//void popup_pillage_dialog(unit punit,
//			  enum int may_pillage)
//{
//  if(!is_showing_pillage_dialog)
//  {
//    enum int may_pillage_save = may_pillage;
//    int count=0;
//
//    while (may_pillage != S_NO_SPECIAL)
//    {
//      enum int what = get_preferred_pillage(may_pillage);
//
//      may_pillage &= (~(what | map_get_infrastructure_prerequisite (what)));
//      count++;
//    }
//
//    if(count)
//    {
//      New_Msg_Dlg msg_dlg;
//
//      if((msg_dlg = (struct New_Msg_Dlg*)malloc(sizeof(struct New_Msg_Dlg)*(count+2))))
//      {
//	int i;
//
//	is_showing_pillage_dialog = true;
//	unit_to_use_to_pillage = punit.id;
//	may_pillage = may_pillage_save;
//
//	for(i=0;i<count;i++)
//	{
//	  enum int what = get_preferred_pillage (may_pillage);
//	
//          msg_dlg[i].label = (map_get_infrastructure_text(what));
//          msg_dlg[i].function = (APTR)pillage_button;
//          msg_dlg[i].data = (APTR)what;
//	
//          may_pillage &= (~(what | map_get_infrastructure_prerequisite (what)));
//        }
//	
//        msg_dlg[i].label = "_Cancel";
//        msg_dlg[i].function = (APTR)pillage_button;
//        msg_dlg[i].data = null;
//	
//        msg_dlg[++i].label = null;
//	
//        popup_message_dialog_args(main_wnd, "Freeciv - Pillage",
//                                  "Select what to pillage:",msg_dlg);
//
//        for(i=0;i<count;i++)
//        {
//          if(msg_dlg[i].label)
//            free(msg_dlg[i].label);
//        }
//
//        free(msg_dlg);
//      }
//    }
//  }
//}
//
///****************************************************************
// Callback for a connect button
//*****************************************************************/
//static void connect_button(popup_message_data msg)
//{
//  is_showing_unit_connect_dialog = false;
//
//  if(msg.data)
//  {
//    unit punit;
//    int activity = (int)msg.data;
//
//    if ((punit = Game.find_unit_by_id(unit_to_use_to_connect)))
//    {
//      if (activity != unit_activity.ACTIVITY_IDLE)
//      {
//        struct packet_unit_connect req;
//        req.activity_type = activity;
//        req.unit_id = punit.id;
//        req.dest_x = connect_unit_x;
//        req.dest_y = connect_unit_y;
//        send_packet_unit_connect(&aconnection, &req);
//      } else
//      {
//	update_unit_info_label(punit);
//      }
//    }
//  }
//  message_close(msg);
//}
//
///****************************************************************
//popup dialog which prompts for activity type (unit connect)
//*****************************************************************/
//void popup_unit_connect_dialog(unit punit, int dest_x, int dest_y)
//{
//  if(!is_showing_unit_connect_dialog)
//  {
//    int count = 0;
//    int activity;
//
//    for (activity = unit_activity.ACTIVITY_IDLE + 1; activity < ACTIVITY_LAST; activity++)
//    {
//      if (!can_unit_do_connect (punit, activity)) continue;
//      count++;
//    }
//
//    if (count)
//    {
//      New_Msg_Dlg msg_dlg;
//
//      is_showing_unit_connect_dialog = true;
//      unit_to_use_to_connect = punit.id;
//      connect_unit_x = dest_x;
//      connect_unit_y = dest_y;
//
//      if((msg_dlg = (struct New_Msg_Dlg*)malloc(sizeof(struct New_Msg_Dlg)*(count+2))))
//      {
//	int i=0;
//
//	for (activity = unit_activity.ACTIVITY_IDLE + 1; activity < ACTIVITY_LAST; activity++)
//	{
//	  if ( !can_unit_do_connect (punit, activity)) continue;
//          msg_dlg[i].label = (get_activity_text(activity));
//          msg_dlg[i].function = (APTR)connect_button;
//          msg_dlg[i].data = (APTR)activity;
//          i++;
//        }
//
//        msg_dlg[i].label = "_Cancel";
//        msg_dlg[i].function = (APTR)connect_button;
//        msg_dlg[i].data = null;
//
//        msg_dlg[++i].label = null;
//
//        popup_message_dialog_args(main_wnd, "Freeciv - Connect",
//                                  "Choose unit activity:",msg_dlg);
//
//        for(i=0;i<count;i++)
//        {
//          if(msg_dlg[i].label)
//            free(msg_dlg[i].label);
//        }
//
//      	free(msg_dlg);
//      }
//    }
//  }
//}
//
//
///****************************************************************
// Must be called from the Application object so it is safe to
// dispose the window
//*****************************************************************/
//static void message_close_real( Object **ppwnd)
//{
//  set(*ppwnd,MUIA_Window_Open,false);
//  DoMethod(app, OM_REMMEMBER, *ppwnd);
//  MUI_DisposeObject(*ppwnd);
//}
//
///****************************************************************
// This function can be used as a New_Msg_Dlg.function
//*****************************************************************/
//void message_close( popup_message_data data)
//{
//  destroy_message_dialog( data.wnd);
//}
//
///****************************************************************
// Popups a window which contains a (window) title, a text which
// information and buttons
//*****************************************************************/
//Object *popup_message_dialog_args( Object *parent, char *title, char *text, New_Msg_Dlg msg )
//{
//  Object *button_group;
//  Object *wnd;
//
//  int i=0;
//  while(msg[i].label) i++;
//
//  if(!i) return null;
//
//  wnd = WindowObject,
//    MUIA_Window_Title,title,
//    WindowContents, VGroup,
//      Child, TextObject,
//        MUIA_Text_Contents,text,
//        MUIA_Text_PreParse, "\33c",
//        End,
//      Child, button_group = GroupObject,
//        MUIA_Group_Horiz, i<4?true:false,
//        End,
//      End,
//    End;
//
//  if(wnd)
//  {
//    while(msg.label)
//    {
//      Object *o = MakeButton(msg.label);
//      if(msg.function)
//      {
//        DoMethod(button_group,OM_ADDMEMBER, o);
//        DoMethod(o,MUIM_Notify,MUIA_Pressed,false,MUIV_Notify_Self, 5, MUIM_CallHook,&civstandard_hook, msg.function, wnd, msg.data);
//      }
//      msg++;
//    }
//    DoMethod(app,OM_ADDMEMBER,wnd);
//    set(wnd,MUIA_Window_Open,true);
//  }
//  return wnd;
//}
//
///****************************************************************
// popup_message_dialog_args with variable parameters
//*****************************************************************/
//Object *popup_message_dialog( Object *parent, char *title, char *text, ... )
//{
//  return popup_message_dialog_args(parent, title, text, (New_Msg_Dlg )((&text)+1));
//}
//
///****************************************************************
// destroy_message_dialog destroy the object after use
//*****************************************************************/
//void destroy_message_dialog( Object *wnd)
//{
//  if(wnd)
//  {
//    set(wnd, MUIA_Window_Open, false);
//    /* Close the window better in the Application Object */
//    DoMethod(app, MUIM_Application_PushMethod, app, 4, MUIM_CallHook, &civstandard_hook, message_close_real, wnd);
//  }
//}
//
///****************************************************************
// Callback if a unit has been clicked
//*****************************************************************/
//static void unitsel_unit(unit *ppunit)
//{
//  unit punit = *ppunit;
//
//  if(punit && punit.owner == Game.game.player_idx) {
//    set_unit_focus_and_select(punit);
//  }
//  set(unitsel_wnd, MUIA_Window_Open, false);
//}
//
///****************************************************************
// Callback for the Ready All button
//*****************************************************************/
//static void unitsel_ready_all(tile *pptile)
//{
//  tile ptile = *pptile;
//
//  if(ptile)
//  {
//    int n,i;
//    n=ptile.units.foo_list_size();
//
//    if(n)
//    {
//      for(i=0;i<n;i++)
//      {
//        unit punit=unit_list_get(&ptile.units, i);
//        if(punit) {
//          set_unit_focus(punit);
//        }
//      }
//    }
//  }
//  set(unitsel_wnd, MUIA_Window_Open, false);
//}
//
///****************************************************************
//popup the unit selection dialog
//*****************************************************************/
//void popup_unit_select_dialog(tile ptile)
//{
//  static Object *window_group;
//  static Object *unit_group;
//  static Object *button_group;
//  static Object *readyall_button;
//
//  if(!unitsel_wnd)
//  {
//    Object *close_button;
//
//    unitsel_wnd = WindowObject,
//      MUIA_Window_Title, "Freeciv - Select an unit",
//      WindowContents, window_group = VGroup,
//        Child, button_group = HGroup,
//          Child, close_button = MakeButton("_Close"),
//          Child, readyall_button = MakeButton("_Ready All"),
//          End,
//        End,
//      End;
//
//    if(unitsel_wnd)
//    {
//      DoMethod(unitsel_wnd, MUIM_Notify, MUIA_Window_CloseRequest,true,unitsel_wnd, 3, MUIM_Set, MUIA_Window_Open,false);
//      DoMethod(close_button, MUIM_Notify, MUIA_Pressed,false,unitsel_wnd, 3, MUIM_Set, MUIA_Window_Open,false);
//      DoMethod(app,OM_ADDMEMBER,unitsel_wnd);
//    }
//  }
//
//  if(unitsel_wnd)
//  {
//    int i,n;
//    char buffer[512];
//
//    DoMethod(readyall_button, MUIM_KillNotify, MUIA_Pressed);
//    DoMethod(readyall_button, MUIM_Notify, MUIA_Pressed, false, app, 4, MUIM_CallHook, &civstandard_hook, unitsel_ready_all, ptile);
//
//    n=ptile.units.foo_list_size();
//
//    DoMethod(window_group, MUIM_Group_InitChange);
//    if(unit_group)
//    {
//      DoMethod(window_group, OM_REMMEMBER, unit_group);
//      MUI_DisposeObject(unit_group);
//    }
//
//    if(n)
//    {
//      unit_group = AutoGroup, VirtualFrame, End;
//
//      for(i=0;i<n;i++)
//      {
//        unit punit=unit_list_get(&ptile.units, i);
//        unit_type punittemp=punit.unit_type();
//        city pcity;
//
//        Object *o;
//        Object *unit_obj;
//
//        pcity = Player_P.player_find_city_by_id(Game.game.player_ptr, punit.homecity);
//
//        buffer = util.my_snprintf("%s%s\n%s\n%s", punittemp.name,
//               (punit.veteran) ? " (veteran)" : "", unit_activity_text(punit),pcity ? pcity.name : "");
//
//        o = HGroup,
//          Child, unit_obj = UnitObject,
//            ButtonFrame,
//            MUIA_InputMode, MUIV_InputMode_RelVerify,
//            MUIA_Unit_Unit,punit,
//            MUIA_Disabled, !can_unit_do_activity(punit, unit_activity.ACTIVITY_IDLE),
//            MUIA_CycleChain,1,
//            End,
//          Child, TextObject,
//            MUIA_Font, MUIV_Font_Tiny,
//            MUIA_Text_Contents, buffer,
//            End,
//          End;
//
//        if(o)
//        {
//          DoMethod(unit_obj,MUIM_Notify, MUIA_Pressed, false, unit_obj, 4, MUIM_CallHook, &civstandard_hook, unitsel_unit, punit);
//          DoMethod(unit_group, OM_ADDMEMBER, o);
//        }
//      }
//      DoMethod(window_group, OM_ADDMEMBER, unit_group);
//      DoMethod(window_group, MUIM_Group_Sort, unit_group, button_group, null);
//    } else unit_group = null;
//
//    DoMethod(window_group, MUIM_Group_ExitChange);
//
//    set(unitsel_wnd,MUIA_Window_Open,true);
//  }
//}
//
//
//static STRPTR styles_entries[64];
//static int styles_basic_index[64];
//static int styles_basic_nums;
//
//Object *nations_wnd;
//Object *nations_leader_string;
//Object *nations_leader_poplist;
//Object *nations_nation_listview;
//Object *nations_flag_sprite;
//Object *nations_sex_radio;
//Object *nations_styles_cycle;
//
///****************************************************************
// Get the nation id of the nation selected in the nations
// listview
//*****************************************************************/
//int get_active_nation()
//{
//  char *nationname;
//
//  DoMethod(nations_nation_listview, MUIM_List_GetEntry, MUIV_List_GetEntry_Active, &nationname);
//  if (!nationname) return 0;
//
//  return find_nation_by_name(nationname);
//}
//
///****************************************************************
//...
//*****************************************************************/
//static void nations_nation_active()
//{
//  int i, leader_count;
//  leader leaders;
//  Object *list = (Object*)xget(nations_leader_poplist,MUIA_Popobject_Object);
//  int nation = get_active_nation();
//
//  set(nations_flag_sprite, MUIA_Sprite_Sprite, Nation.get_nation_by_idx(nation).flag_sprite);
//
//  leaders = get_nation_leaders(nation, &leader_count);
//  setstring(nations_leader_string, leaders[0].name);
//
//  set(nations_sex_radio, MUIA_Radio_Active, leaders[0].is_male);
//
//  if(list)
//  {
//    DoMethod(list,MUIM_List_Clear);
//    for(i=0;i<leader_count;i++)
//    {
//      DoMethod(list,MUIM_List_InsertSingle, leaders[i], MUIV_List_Insert_Bottom);
//    }
//  }
//}
//
///****************************************************************
//...
//*****************************************************************/
//static void nations_ok()
//{
//  int selected, selected_sex, selected_style;
//  char *s;
//  struct packet_alloc_nation packet;
//
//  selected = get_active_nation();
//  selected_sex = xget(nations_sex_radio, MUIA_Radio_Active);
//  selected_style = xget(nations_styles_cycle, MUIA_Cycle_Active);
//  s = getstring(nations_leader_string);
//
//  /* perform a minimum of sanity test on the name */
//  packet.nation_no=selected;
//  packet.is_male = !selected_sex;
//  packet.city_style = styles_basic_index[selected_style];
//
//  packet.name = String.format( (char*)s);
//  
//  if (packet.name.length() == 0) {
//    append_output_window("You must type a legal name.");
//    return;
//  }
//
//  send_packet_alloc_nation(&aconnection, &packet);
//}
//
///****************************************************************
//...
//*****************************************************************/
//static void nations_disconnect()
//{
//  popdown_races_dialog();
//  disconnect_from_server();
//}
//
///****************************************************************
//...
//*****************************************************************/
//HOOKPROTONH(nations_obj2str, void, Object *list, Object *str)
//{
//  char *x;
//  int nation = get_active_nation();
//  DoMethod(list,MUIM_List_GetEntry,MUIV_List_GetEntry_Active,&x);
//  set(str,MUIA_String_Contents,x);
//  if(x) set(nations_sex_radio, MUIA_Radio_Active, get_nation_leader_sex(nation,x)?0:1);
//}
//
///****************************************************************
//...
//*****************************************************************/
//HOOKPROTONH(nations_str2obj, ULONG, Object *list, Object *str)
//{
//  char *x,*s;
//  int i;
//
//  get(str,MUIA_String_Contents,&s);
//
//  for (i=0;;i++)
//  {
//    DoMethod(list,MUIM_List_GetEntry,i,&x);
//    if (!x)
//    {
//      set(list,MUIA_List_Active,MUIV_List_Active_Off);
//      break;
//    }
//    else if (!stricmp(x,s))
//    {
//      set(list,MUIA_List_Active,i);
//      break;
//    }
//  }
//  return true;
//}
//
///****************************************************************
// Popup the nations (races) window
//*****************************************************************/
//void popup_races_dialog()
//{
//  if(!nations_wnd)
//  {
//    Object *nations_ok_button,*list;
//    Object *nations_disconnect_button;
//    Object *nations_quit_button;
//
//    int i;
//    static char *sex_labels[3];
//    static struct Hook objstr_hook;
//    static struct Hook strobj_hook;
//
//
//    sex_labels[0] = "Male";
//    sex_labels[1] = "Female";
//    sex_labels[2] = 0;
//    objstr_hook.h_Entry = (HOOKFUNC)nations_obj2str;
//    strobj_hook.h_Entry = (HOOKFUNC)nations_str2obj;
//
//    styles_basic_nums = 0;
//
//    for(i=0;i<Game.game.styles_count && i<64;i++)
//    {
//      if(city_styles[i].techreq == A_NONE)
//      {
//        styles_basic_index[styles_basic_nums] = i;
//        styles_entries[styles_basic_nums] = city_styles[i].name;
//        styles_basic_nums++;
//      }
//    }
//
//    styles_entries[styles_basic_nums] = null;
//
//    nations_wnd = WindowObject,
//        MUIA_Window_Title,  "Freeciv - Select a Nation",
//        MUIA_Window_ID, MAKE_ID('S','N','A','T'),
//        WindowContents, VGroup,
//            Child, HGroup,
//		Child, nations_nation_listview = ListviewObject,
//		    MUIA_HorizWeight, 50,
//		    MUIA_CycleChain, 1,
//		    MUIA_Listview_List, ListObject,
//			InputListFrame,
//			End,
//		    End,
//
//		Child, VGroup,
//		    Child, HVSpace,
//		    Child, HGroup,
//		    	Child, HVSpace,
//		    	Child, nations_flag_sprite = SpriteObject,
//		    	    MUIA_Sprite_Sprite,Nation.get_nation_by_idx(0).flag_sprite,
//		    	    MUIA_Sprite_Transparent, true,
//		    	    End,
//		    	Child, HVSpace,
//			End,
//	            Child, HGroup,
//        	        Child, MakeLabel("_Leader Name"),
//	                Child, nations_leader_poplist = PopobjectObject,
//        	            MUIA_Popstring_String, nations_leader_string = MakeString(null,40),
//                	    MUIA_Popstring_Button, PopButton(MUII_PopUp),
//	                    MUIA_Popobject_ObjStrHook, &objstr_hook,
//        	            MUIA_Popobject_StrObjHook, &strobj_hook,
//                	    MUIA_Popobject_Object, list = ListviewObject,
//                        	MUIA_Listview_List, ListObject,
//	                            InputListFrame,
//        	                    End,
//                	        End,
//	                    End,
//        	        Child, nations_sex_radio = MakeRadio(null,sex_labels),
//                	End,
//	            Child, HGroup,
//        	        Child, MakeLabel("_City Style"),
//                	Child, nations_styles_cycle = MakeCycle("_City Style",styles_entries),
//                	End,
//		    Child, HVSpace,
//                    End,
//                End,
//            Child, HGroup,
//                Child, nations_ok_button = MakeButton("_Ok"),
//                Child, nations_disconnect_button = MakeButton("_Disconnect"),
//                Child, nations_quit_button = MakeButton("_Quit"),
//                End,
//            End,
//        End;
//
//    if(nations_wnd)
//    {
//      DoMethod(nations_nation_listview, MUIM_List_Clear);
//      for(i=0;i<Game.game.playable_nation_count && i<64;i++)
//	DoMethod(nations_nation_listview, MUIM_List_InsertSingle, Nation.get_nation_name(i), MUIV_List_Insert_Sorted);
//
//      DoMethod(nations_nation_listview, MUIM_Notify, MUIA_List_Active, MUIV_EveryTime, app, 3, MUIM_CallHook, &civstandard_hook, nations_nation_active);
//      DoMethod(nations_ok_button, MUIM_Notify, MUIA_Pressed, false, app, 3, MUIM_CallHook, &civstandard_hook, nations_ok);
//      DoMethod(nations_disconnect_button, MUIM_Notify, MUIA_Pressed, false, app, 3, MUIM_CallHook, &civstandard_hook, nations_disconnect);
//      DoMethod(nations_wnd, MUIM_Notify, MUIA_Window_CloseRequest, true, app, 3, MUIM_CallHook, &civstandard_hook, nations_disconnect);
//
//      DoMethod(nations_quit_button, MUIM_Notify, MUIA_Pressed, false, app,2,MUIM_Application_ReturnID, MUIV_Application_ReturnID_Quit);
//      DoMethod(list,MUIM_Notify,MUIA_Listview_DoubleClick,true, nations_leader_poplist,2,MUIM_Popstring_Close,true);
//      DoMethod(app, OM_ADDMEMBER, nations_wnd);
//
//      set(nations_nation_listview, MUIA_List_Active, 0);
//      set(nations_wnd, MUIA_Window_DefaultObject, nations_nation_listview);
//    }
//  }
//
//  if(nations_wnd)
//  {
//    nations_nation_active();
//    set(nations_wnd,MUIA_Window_Open,true);
//  }
//}
//
///****************************************************************
//...
//*****************************************************************/
//void popdown_races_dialog()
//{
//  if(nations_wnd)
//  {
//    set(nations_wnd,MUIA_Window_Open,false);
//
//		/* This close and frees the window, we use notify_close for this
//       because it does the job */
//		notify_close(&nations_wnd);
//		nations_wnd = null;
//  }
//}
//
///****************************************************************
//...
//*****************************************************************/
//void races_toggles_set_sensitive(packet_nations_used packet)
//{
//  /* not implemented */
//}
//
///****************************************************************
// Callback for the Yes button in the upgrade dialog
//*****************************************************************/
//static void upgrade_yes(popup_message_data msg)
//{
//  if(msg.data)
//  {
//    unit punit = Game.find_unit_by_id ((int)msg.data);
//    if (punit) {
//      request_unit_upgrade(punit);
//    }
//  }
//  message_close(msg);
//}
//
///****************************************************************
// Only in the MUI client
//*****************************************************************/
//void popup_upgrade_dialog(unit punit)
//{
//  char buf[512];
//  int ut1,ut2;
//  int value;
//
//  ut1 = punit.type;
//  ut2 = can_upgrade_unittype(Game.game.player_ptr,ut1);
//
//  if (ut2 == -1)
//  {
//    /* this shouldn't generally happen, but it is conceivable */
//    buf = util.my_snprintf(
//		"Sorry: cannot upgrade %s.", Unittype_P.unit_types[ut1].name);
//    popup_message_dialog( main_wnd, "Upgrade Unit!", buf,
//			  "_Darn", message_close, 0,
//			  null);
//  } else
//  {
//    value = unit_upgrade_price(Game.game.player_ptr, ut1, ut2);
//
//    if (Game.game.player_ptr.economic.gold>=value)
//    {
//      buf = util.my_snprintf( ("Upgrade %s to %s for %d gold?\n" +
//	         "Treasury contains %d gold."),
//	         Unittype_P.unit_types[ut1].name, Unittype_P.unit_types[ut2].name,
//	         value, Game.game.player_ptr.economic.gold);
//      popup_message_dialog(main_wnd, "Upgrade Obsolete Units", buf,
//			   "_Yes", upgrade_yes, punit.id,
//			   "_No", message_close, 0,
//			     null);
//    } else
//    {
//	buf = util.my_snprintf( ("Upgrading %s to %s costs %d gold.\n" +
//	       "Treasury contains %d gold."),
//	       Unittype_P.unit_types[ut1].name, Unittype_P.unit_types[ut2].name,
//	       value, Game.game.player_ptr.economic.gold);
//	popup_message_dialog(main_wnd,
//			     "Upgrade Unit!", buf,
//			     "_Darn", message_close, 0,
//			     null);
//    }
//  }
//}
//

//  This function is called when the client disconnects or the game is
//  over.  It should close all dialog windows for that game.
//***********************************************************************/
//void popdown_all_game_dialogs()
//{
//  /* TODO */
//}
}