package ai;

public class Ailog{

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
//#include <stdarg.h>
//
//#include "city.h"
//#include "log.h"
//#include "shared.h"
//#include "support.h"
//#include "unit.h"
//
//#include "gotohand.h"
//#include "plrhand.h"
//
//#include "aidata.h"
//#include "ailog.h"
//
///* General AI logging functions */
//
///**************************************************************************
//  Log player messages, they will appear like this
//    2: perrin [ti12 co6 lo5 e]  Increased love for a (now 9)
//  where ti is timer, co countdown and lo love for target, who is e.
//**************************************************************************/
//void PLAYER_LOG(int level, player pplayer, ai_data ai, 
//                final String msg)
//{
//  char targetbuffer[250];
//  char buffer[500];
//  char buffer2[500];
//  va_list ap;
//  int minlevel = Math.min(LOGLEVEL_CITY, level);
//
//  if (pplayer.debug) {
//    minlevel = Log.LOG_NORMAL;
//  } else if (minlevel > fc_log_level) {
//    return;
//  }
//
//  if (ai.diplomacy.target) {
//    targetbuffer = util.my_snprintf( "[ti%d co%d lo%d %s] ",
//                ai.diplomacy.timer, ai.diplomacy.countdown,
//                pplayer.ai.love[ai.diplomacy.target.player_no],
//                ai.diplomacy.target.name);
//  }
//  buffer = util.my_snprintf( "%s %s%s%s ", pplayer.name,
//              ai.diplomacy.target ? targetbuffer : "",
//              ai.diplomacy.spacerace_leader &&
//              ai.diplomacy.spacerace_leader.player_no == pplayer.player_no ? 
//                "(spacelead) " : "",
//              ai.diplomacy.alliance_leader.player_no == pplayer.player_no ?
//                "(*)" : "");
//
//  va_start(ap, msg);
//  my_vsnprintf(buffer2, sizeof(buffer2), msg, ap);
//  va_end(ap);
//
//  cat_snprintf(buffer, sizeof(buffer), buffer2);
//  if (pplayer.debug) {
//    Plrhand.notify_conn(&Game.game.est_connections, buffer);
//  }
//  util.freelog(minlevel, buffer);
//}
//
///**************************************************************************
//  Log city messages, they will appear like this
//    2: c's Romenna(5,35) [s1 d106 u11 g1] must have Archers ...
//**************************************************************************/
//void CITY_LOG(int level, city pcity, final String msg)
//{
//  char buffer[500];
//  char buffer2[500];
//  va_list ap;
//  int minlevel = Math.min(LOGLEVEL_CITY, level);
//
//  if (pcity.debug) {
//    minlevel = Log.LOG_NORMAL;
//  } else if (minlevel > fc_log_level) {
//    return;
//  }
//
//  buffer = util.my_snprintf( "%s's %s(%d,%d) [s%d d%d u%d g%d] ",
//              City.city_owner(pcity).name, pcity.name,
//              pcity.tile.x, pcity.tile.y, pcity.size,
//              pcity.ai.danger, pcity.ai.urgency,
//              pcity.ai.grave_danger);
//
//  va_start(ap, msg);
//  my_vsnprintf(buffer2, sizeof(buffer2), msg, ap);
//  va_end(ap);
//
//  cat_snprintf(buffer, sizeof(buffer), buffer2);
//  if (pcity.debug) {
//    Plrhand.notify_conn(&Game.game.est_connections, buffer);
//  }
//  util.freelog(minlevel, buffer);
//}
//
///**************************************************************************
//  Log unit messages, they will appear like this
//    2: c's Archers[139] (5,35).(0,0){0,0} stays to defend city
//  where [] is unit id, ().() are coordinates present and goto, and
//  {,} contains bodyguard and ferryboat ids.
//**************************************************************************/
//void UNIT_LOG(int level, unit punit, final String msg)
//{
//  char buffer[500];
//  char buffer2[500];
//  va_list ap;
//  int minlevel = Math.min(LOGLEVEL_UNIT, level);
//  int gx, gy;
//  boolean messwin = false; /* output to message window */
//
//  if (punit.debug) {
//    minlevel = Log.LOG_NORMAL;
//  } else {
//    /* Are we a virtual unit evaluated in a debug city?. */
//    if (punit.id == 0) {
//      city pcity = Map.map_get_city(punit.tile);
//
//      if (pcity && pcity.debug) {
//        minlevel = Log.LOG_NORMAL;
//        messwin = true;
//      }
//    }
//    if (minlevel > fc_log_level) {
//      return;
//    }
//  }
//
//  if (punit.goto_tile) {
//    gx = punit.goto_tile.x;
//    gy = punit.goto_tile.y;
//  } else {
//    gx = gy = -1;
//  }
//  
//  buffer = util.my_snprintf( "%s's %s[%d] (%d,%d).(%d,%d){%d,%d} ",
//              punit.unit_owner().name, punit.unit_type().name,
//              punit.id, punit.tile.x, punit.tile.y,
//	      gx, gy,
//              punit.ai.bodyguard, punit.ai.ferryboat);
//
//  va_start(ap, msg);
//  my_vsnprintf(buffer2, sizeof(buffer2), msg, ap);
//  va_end(ap);
//
//  cat_snprintf(buffer, sizeof(buffer), buffer2);
//  if (punit.debug || messwin) {
//    Plrhand.notify_conn(&Game.game.est_connections, buffer);
//  }
//  util.freelog(minlevel, buffer);
//}
//
///**************************************************************************
//  Log message for bodyguards. They will appear like this
//    2: ai4's bodyguard Mech. Inf.[485] (38,22){Riflemen:574@37,23} was ...
//  note that these messages are likely to wrap if long.
//**************************************************************************/
//void BODYGUARD_LOG(int level, unit punit, final String msg)
//{
//  char buffer[500];
//  int minlevel = Math.min(LOGLEVEL_BODYGUARD, level);
//  unit pcharge;
//  city pcity;
//  int id = -1;
//  tile ptile = null;
//  final String s = "none";
//
//  if (punit.debug) {
//    minlevel = Log.LOG_NORMAL;
//  } else if (minlevel > fc_log_level) {
//    return;
//  }
//
//  pcity = Game.find_city_by_id(punit.ai.charge);
//  pcharge = Game.find_unit_by_id(punit.ai.charge);
//  if (pcharge) {
//    ptile = pcharge.tile;
//    id = pcharge.id;
//    s = pcharge.unit_type().name;
//  } else if (pcity) {
//    ptile = pcity.tile;
//    id = pcity.id;
//    s = pcity.name;
//  }
//  buffer = util.my_snprintf(
//              "%s's bodyguard %s[%d] (%d,%d){%s:%d@%d,%d} ",
//              punit.unit_owner().name, punit.unit_type().name,
//              punit.id, punit.tile.x, punit.tile.y,
//	      s, id, ptile.x, ptile.y);
//  cat_snprintf(buffer, sizeof(buffer), msg);
//  if (punit.debug) {
//    Plrhand.notify_conn(&Game.game.est_connections, buffer);
//  }
//  util.freelog(minlevel, buffer);
//}
}