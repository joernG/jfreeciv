package client.gui_mui;

public class Plrdlg{

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
//#include <libraries/mui.h>
//#include <mui/NListview_MCC.h>
//
//#include <clib/alib_protos.h>
//#include <proto/exec.h>
//#include <proto/muimaster.h>
//#include <proto/graphics.h>
//
//#include "fcintl.h"
//#include "game.h"
//#include "packets.h"
//#include "player.h"
//#include "support.h"
//
//#include "chatline.h"
//#include "civclient.h"
//#include "climisc.h"
//#include "clinet.h"
//#include "inteldlg.h"
//#include "gui_main.h"
//#include "spaceshipdlg.h"
//#include "plrdlg.h"
//
///* Amiga Client stuff */
//#include "muistuff.h"
//
//static Object *player_wnd;
//static Object *player_players_listview;
//static Object *player_close_button;
//static Object *player_intelligence_button;
//static Object *player_meet_button;
//static Object *player_war_button;
//static Object *player_vision_button;
//static Object *player_spaceship_button;
//static struct Hook player_players_disphook;
//
//static void create_players_dialog();
//
///****************************************************************
// GUI Independend
//*****************************************************************/
//void request_diplomacy_init_meeting(int plrno0, int plrno1)
//{
//  struct packet_diplomacy_info pa;
//
//  pa.plrno0 = plrno0;
//  pa.plrno1 = plrno1;
//  pa.plrno_from = pa.plrno0;
//
//  send_packet_diplomacy_info(&aconnection, PACKET_DIPLOMACY_INIT_MEETING,
//			     &pa);
//}
//
///****************************************************************
//popup the dialog 10% inside the main-window 
//*****************************************************************/
//void popup_players_dialog()
//{
//  if (!player_wnd)
//    create_players_dialog();
//  if (player_wnd)
//  {
//    update_players_dialog();
//    set(player_wnd, MUIA_Window_Open, true);
//  }
//}
//
///****************************************************************
// Display function for the players listview
//*****************************************************************/
//HOOKPROTONH(players_render, void, char **array, APTR msg)
//{
//  ULONG playerno = (ULONG) msg;
//
//  if (playerno)
//  {
//    int i = playerno - 100;
//    static char idlebuf[32];
//    static char statebuf[32];
//    static char namebuf[32];
//    static char dsbuf[32];
//    static char repbuf[32];
//    final player_diplstate pds;
//
//    if (game.players[i].nturns_idle > 3)
//      idlebuf = util.my_snprintf(
//		  PL("(idle %d turn)", "(idle %d turns)",
//		      game.players[i].nturns_idle - 1),
//		  game.players[i].nturns_idle - 1);
//    else
//      idlebuf[0] = '\0';
//
//    if (game.players[i].is_alive)
//    {
//      if (game.players[i].is_connected)
//      {
//	if (game.players[i].turn_done)
//	  statebuf = String.format( "done");
//	else
//	  statebuf = String.format( "moving");
//      }
//      else
//	statebuf[0] = '\0';
//    }
//    else
//      statebuf = String.format( "R.I.P");
//
//    if (game.players[i].ai.control)
//      namebuf = util.my_snprintf( "*%-15s", game.players[i].name);
//    else
//      namebuf = util.my_snprintf( "%-16s", game.players[i].name);
//
//
//
//    /* text for diplstate type and turns -- not applicable if this is me */
//    if (i == game.player_idx) {
//      dsbuf = String.format( "-");
//    } else {
//      pds = pplayer_get_diplstate(game.player_ptr, get_player(i));
//      if (pds.type == DS_CEASEFIRE) {
//	dsbuf = util.my_snprintf( "%s (%d)",
//		    diplstate_text(pds.type), pds.turns_left);
//      } else {
//	dsbuf = util.my_snprintf( "%s",
//		    diplstate_text(pds.type));
//      }
//    }
//
//    /* text for reputation */
//    repbuf = util.my_snprintf(
//		reputation_text(game.players[i].reputation));
//
//    *array++ = namebuf;
//    *array++ = Nation.get_nation_name(game.players[i].nation);
//    *array++ = get_embassy_status(game.player_ptr, &game.players[i]);
//    *array++ = dsbuf;
//    *array++ = get_vision_status(game.player_ptr, &game.players[i]);
//    *array++ = repbuf;
//    *array++ = statebuf;
//    *array++ = (char*)player_addr_hack(&game.players[i]);  /* Fixme */
//    *array = idlebuf;
//  }
//  else
//  {
//    *array++ = "Name";
//    *array++ = "Nation";
//    *array++ = "Embassy";
//    *array++ = "Dipl.State";
//    *array++ = "Vision";
//    *array++ = "Reputation";
//    *array++ = "State";
//    *array++ = "Host";
//    *array = "Idle";
//  }
//}
//
///****************************************************************
//...
//*****************************************************************/
//static void players_active()
//{
//  LONG playerno;
//  DoMethod(player_players_listview, MUIM_NList_GetEntry, MUIV_NList_GetEntry_Active, &playerno);
//
//  if (playerno)
//  {
//    player pplayer;
//    playerno -= 100;
//
//    pplayer = get_player(playerno);
//
//    if (pplayer.spaceship.state != spaceship_state.SSHIP_NONE)
//      set(player_spaceship_button, MUIA_Disabled, false);
//    else
//      set(player_spaceship_button, MUIA_Disabled, true);
//
//    switch (pplayer_get_diplstate(game.player_ptr, pplayer).type) {
//      case diplstate_type.DS_WAR:
//      case diplstate_type.DS_NO_CONTACT:
//	   set(player_war_button, MUIA_Disabled, true);
//           break;
//
//      default:
//	   set(player_war_button, MUIA_Disabled, game.player_idx == playerno);
//           break;
//    }
//
//    set(player_vision_button, MUIA_Disabled,
//	!gives_shared_vision(game.player_ptr, pplayer));
//
//    set(player_meet_button, MUIA_Disabled, !can_meet_with_player(pplayer));
//    set(player_intelligence_button, MUIA_Disabled,
//	!can_intel_with_player(pplayer));
//    return;
//  }
//
//  set(player_meet_button, MUIA_Disabled, true);
//  set(player_intelligence_button, MUIA_Disabled, true);
//}
//
///**************************************************************************
// Callback for the Intelligence button
//**************************************************************************/
//static void players_intelligence()
//{
//  LONG playerno;
//  DoMethod(player_players_listview, MUIM_NList_GetEntry, MUIV_NList_GetEntry_Active, &playerno);
//
//  if (playerno)
//  {
//    playerno -= 100;
//
//    if (can_intel_with_player(&game.players[playerno])) {
//      popup_intel_dialog(&game.players[playerno]);
//    }
//  }
//}
//
///****************************************************************
// Callback for the Meet button
//*****************************************************************/
//static void players_meet()
//{
//  LONG playerno;
//  DoMethod(player_players_listview, MUIM_NList_GetEntry, MUIV_NList_GetEntry_Active, &playerno);
//
//  if (playerno)
//  {
//    playerno -= 100;
//
//    if (can_meet_with_player(&game.players[playerno])) {
//      request_diplomacy_init_meeting(game.player_idx, playerno);
//    } else {
//      append_output_window(("Game: You need an embassy to establish a " +
//                             "diplomatic meeting."));
//    }
//  }
//}
//
///****************************************************************
// Callback for the war button
//*****************************************************************/
//static void players_war()
//{
//  LONG playerno;
//  DoMethod(player_players_listview, MUIM_NList_GetEntry, MUIV_NList_GetEntry_Active, &playerno);
//
//  if(playerno)
//  {
//    struct packet_generic_values packet;
//
//    packet.id = playerno - 100;
//    packet.value1 = CLAUSE_CEASEFIRE;
//    send_packet_generic_values(&aconnection, PACKET_PLAYER_CANCEL_PACT,
//                               &packet);
//  }
//}
//
///**************************************************************************
// Callback for the vision button
//**************************************************************************/
//void players_vision()
//{
//  LONG playerno;
//  DoMethod(player_players_listview, MUIM_NList_GetEntry, MUIV_NList_GetEntry_Active, &playerno);
//
//  if(playerno)
//  {
//    struct packet_generic_values packet;
//
//    packet.id = playerno - 100;
//    packet.value1 = CLAUSE_VISION;
//    send_packet_generic_values(&aconnection,
//                               PACKET_PLAYER_CANCEL_PACT, &packet);
//  }
//}
//
///****************************************************************
// Callback for the spaceship button
//*****************************************************************/
//static void players_spaceship()
//{
//  LONG playerno;
//  DoMethod(player_players_listview, MUIM_NList_GetEntry, MUIV_NList_GetEntry_Active, &playerno);
//
//  if(playerno)
//  {
//    popup_spaceship_dialog(&game.players[playerno-100]);
//  }
//}
//
///****************************************************************
//...
//*****************************************************************/
//static void create_players_dialog()
//{
//  player_players_disphook.h_Entry = (HOOKFUNC) players_render;
//
//  player_wnd = WindowObject,
//    MUIA_Window_Title, "Players",
//    WindowContents, VGroup,
//	Child, player_players_listview = NListviewObject,
//	MUIA_NListview_NList, NListObject,
//	    MUIA_NList_DisplayHook, &player_players_disphook,
//	    MUIA_NList_Title, true,
//	    MUIA_NList_Format, ",,,,,,,,",
//	    End,
//	End,
//	Child, HGroup,
//	    Child, player_close_button = MakeButton("_Close"),
//	    Child, player_intelligence_button = MakeButton("_Intelligence"),
//	    Child, player_meet_button = MakeButton("_Meet"),
//	    Child, player_war_button = MakeButton("_Cancel Treaty"),
//            Child, player_vision_button = MakeButton("_Withdraw vision"),
//	    Child, player_spaceship_button = MakeButton("_Spaceship"),
//	    End,
//	End,
//    End;
//
//  if (player_wnd)
//  {
//    set(player_intelligence_button, MUIA_Disabled, true);
//    set(player_meet_button, MUIA_Disabled, true);
//    set(player_spaceship_button, MUIA_Disabled, true);
//    DoMethod(player_wnd, MUIM_Notify, MUIA_Window_CloseRequest, true, player_wnd, 3, MUIM_Set, MUIA_Window_Open, false);
//    DoMethod(player_close_button, MUIM_Notify, MUIA_Pressed, false, player_wnd, 3, MUIM_Set, MUIA_Window_Open, false);
//    DoMethod(player_intelligence_button, MUIM_Notify, MUIA_Pressed, false, app, 3, MUIM_CallHook, &civstandard_hook, players_intelligence);
//    DoMethod(player_meet_button, MUIM_Notify, MUIA_Pressed, false, app, 3, MUIM_CallHook, &civstandard_hook, players_meet);
//    DoMethod(player_war_button, MUIM_Notify, MUIA_Pressed, false, app, 3, MUIM_CallHook, &civstandard_hook, players_war);
//    DoMethod(player_vision_button, MUIM_Notify, MUIA_Pressed, false, app, 3, MUIM_CallHook, &civstandard_hook, players_vision);
//    DoMethod(player_spaceship_button, MUIM_Notify, MUIA_Pressed, false, app, 3, MUIM_CallHook, &civstandard_hook, players_spaceship);
//    DoMethod(player_players_listview, MUIM_Notify, MUIA_NList_Active, MUIV_EveryTime, app, 3, MUIM_CallHook, &civstandard_hook, players_active);
//    DoMethod(app, OM_ADDMEMBER, player_wnd);
//  }
//}
//
//
///**************************************************************************
//...
//**************************************************************************/
//void update_players_dialog()
//{
//  int i;
//
//  if (!player_wnd || is_plrdlg_frozen())
//    return;
//
//  set(player_players_listview, MUIA_NList_Quiet, true);
//  DoMethod(player_players_listview, MUIM_NList_Clear);
//  for (i = 0; i < game.nplayers; i++)
//  {
//    if(is_barbarian(&game.players[i]))
//      continue;
//
//    DoMethod(player_players_listview, MUIM_NList_InsertSingle, i + 100, MUIV_NList_Insert_Bottom);
//  }
//  set(player_players_listview, MUIA_NList_Quiet, false);
//}
}