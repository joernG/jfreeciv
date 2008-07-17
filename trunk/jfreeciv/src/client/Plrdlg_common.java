package client;

public class Plrdlg_common{

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
//
//#include "connection.h"
//#include "fcintl.h"
//#include "game.h"
//#include "support.h"
//
//#include "climisc.h"
//#include "text.h"
//
//#include "plrdlg_g.h"
//
//#include "plrdlg_common.h"
//
//static int frozen_level = 0;
//
///******************************************************************
// Turn off updating of player dialog
//*******************************************************************/
//void plrdlg_freeze()
//{
//  frozen_level++;
//}
//
///******************************************************************
// Turn on updating of player dialog
//*******************************************************************/
//void plrdlg_thaw()
//{
//  frozen_level--;
//  assert(frozen_level >= 0);
//  if (frozen_level == 0) {
//    update_players_dialog();
//  }
//}
//
///******************************************************************
// Turn on updating of player dialog
//*******************************************************************/
//void plrdlg_force_thaw()
//{
//  frozen_level = 1;
//  plrdlg_thaw();
//}
//
///******************************************************************
// ...
//*******************************************************************/
//boolean is_plrdlg_frozen()
//{
//  return frozen_level > 0;
//}
//
///******************************************************************
// ...
//*******************************************************************/
//static final String col_name(player player)
//{
//  return player.name;
//}
//
///******************************************************************
// ...
//*******************************************************************/
//static final String col_username(player player)
//{
//  return player.username;
//}
//
///******************************************************************
// ...
//*******************************************************************/
//static final String col_nation(player player)
//{
//  return get_nation_name(player.nation);
//}
//
///******************************************************************
// ...
//*******************************************************************/
//static final String col_team(player player)
//{
//  if (player.team != TEAM_NONE) {
//    return team_get_by_id(player.team).name;
//  } else {
//    return "";
//  }
//}
//
///******************************************************************
// ...
//*******************************************************************/
//static boolean col_ai(player plr)
//{
//  return plr.ai.control;
//}
//
///******************************************************************
// ...
//*******************************************************************/
//static final String col_embassy(player player)
//{
//  return get_embassy_status(game.player_ptr, player);
//}
//
///******************************************************************
// ...
//*******************************************************************/
//static final String col_diplstate(player player)
//{
//  static char buf[100];
//  const player_diplstate pds;
//
//  if (player == game.player_ptr) {
//    return "-";
//  } else {
//    pds = pplayer_get_diplstate(game.player_ptr, player);
//    if (pds.type == DS_CEASEFIRE) {
//      my_snprintf(buf, sizeof(buf), "%s (%d)",
//		  diplstate_text(pds.type), pds.turns_left);
//      return buf;
//    } else {
//      return diplstate_text(pds.type);
//    }
//  }
//}
//
///******************************************************************
//  Return a string displaying the AI's love (or not) for you...
//*******************************************************************/
//static final String col_love(player player)
//{
//  if (player == game.player_ptr || !player.ai.control) {
//    return "-";
//  } else {
//    return love_text(player.ai.love[game.player_ptr.player_no]);
//  }
//}
//
///******************************************************************
// ...
//*******************************************************************/
//static final String col_vision(player player)
//{
//  return get_vision_status(game.player_ptr, player);
//}
//
///******************************************************************
// ...
//*******************************************************************/
//static final String col_reputation(player player)
//{
//  return reputation_text(player.reputation);
//}
//
///******************************************************************
// ...
//*******************************************************************/
//static final String col_state(player plr)
//{
//  if (plr.is_alive) {
//    if (plr.is_connected) {
//      if (plr.turn_done) {
//	return "done";
//      } else {
//	return "moving";
//      }
//    } else {
//      return "";
//    }
//  } else {
//    return "R.I.P";
//  }
//}
//
///******************************************************************
// ...
//*******************************************************************/
//static final String col_host(player player)
//{
//  return player_addr_hack(player);
//}
//
///******************************************************************
// ...
//*******************************************************************/
//static final String col_idle(player plr)
//{
//  int idle;
//  static char buf[100];
//
//  if (plr.nturns_idle > 3) {
//    idle = plr.nturns_idle - 1;
//  } else {
//    idle = 0;
//  }
//  my_snprintf(buf, sizeof(buf), "%d", idle);
//  return buf;
//}
//
///******************************************************************
// ...
//*******************************************************************/
//struct player_dlg_column player_dlg_columns[] = {
//  {true, COL_TEXT, N"?Player:Name", col_name, null, "name"},
//  {false, COL_TEXT, N"Username", col_username, null, "username"},
//  {true, COL_FLAG, N"Flag", null, null, "flag"},
//  {true, COL_TEXT, N"Nation", col_nation, null, "nation"},
//  {true, COL_COLOR, N"Border", null, null, "border"},
//  {true, COL_TEXT, N"Team", col_team, null, "team"},
//  {true, COL_BOOLEAN, N"AI", null, col_ai, "ai"},
//  {true, COL_TEXT, N"Attitude", col_love, null, "attitude"},
//  {true, COL_TEXT, N"Embassy", col_embassy, null, "embassy"},
//  {true, COL_TEXT, N"Dipl.State", col_diplstate, null, "diplstate"},
//  {true, COL_TEXT, N"Vision", col_vision, null, "vision"},
//  {true, COL_TEXT, N"Reputation", col_reputation, null, "reputation"},
//  {true, COL_TEXT, N"State", col_state, null, "state"},
//  {false, COL_TEXT, N"?Player_dlg:Host", col_host, null, "host"},
//  {false, COL_RIGHT_TEXT, N"?Player_dlg:Idle", col_idle, null, "idle"},
//  {false, COL_RIGHT_TEXT, N"Ping", get_ping_time_text, null, "ping"}
//};
//
//const int num_player_dlg_columns = ARRAY_SIZE(player_dlg_columns);
//
///******************************************************************
// ...
//*******************************************************************/
//int player_dlg_default_sort_column()
//{
//  return 3;
//}
//
///****************************************************************************
//  Translate all titles
//****************************************************************************/
//void init_player_dlg_common()
//{
//  int i;
//
//  for (i = 0; i < num_player_dlg_columns; i++) {
//    player_dlg_columns[i].title = Q_(player_dlg_columns[i].title);
//  }
//}
//
///**************************************************************************
//  The only place where this is used is the player dialog.
//  Eventually this should go the way of the dodo with everything here
//  moved into col_host above, but some of the older clients (+win32) still
//  use this function directly.
//
//  This code in this function is only really needed so that the host is
//  kept as a blank address if no one is controlling a player, but there are
//  observers.
//**************************************************************************/
//final String player_addr_hack(player pplayer)
//{ 
//  conn_list_iterate(pplayer.connections, pconn) {
//    if (!pconn.observer) {
//      return pconn.addr;
//    }
//  } conn_list_iterate_end;
//
//  return blank_addr_str;
//}   
}