package client.gui_ftwl;

public class Gui_text{

// Freeciv - Copyright (C) 2004 - The Freeciv Poject
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
//
//#ifdef HAVE_CONFIG_H
//#include <config.h>
//#endif
//
//#include <assert.h>
//#include <stdarg.h>
//#include <string.h>
//
//#include "fcintl.h"
//#include "log.h"
//#include "support.h"
//
//#include "map.h"
//#include "combat.h"
//#include "government.h"
//
//#include "climisc.h"
//#include "civclient.h"
//#include "control.h"
//#include "goto.h"
//#include "text.h"
//
//#include "gui_text.h"
//
///*
// * An individual add(_line) string has to fit into GROW_TMP_SIZE. One buffer
// * of GROW_TMP_SIZE size will be allocated for the entire client.
// */
//#define GROW_TMP_SIZE	(1024)
//
///* 
// * Initial size of the buffer for each function.  It may later be
// * grown as needed.
// */
//public static final int START_SIZE = 10;
//
//static void real_add_line(char **buffer, size_t * buffer_size,
//			  final String format)
//fc__attribute((__format__(__printf__, 3, 4)));
//static void real_add(char **buffer, size_t * buffer_size,
//		     final String format)
//fc__attribute((__format__(__printf__, 3, 4)));
//
//#define add_line(...) real_add_line(&out,&out_size, __VA_ARGS__)
//#define add(...) real_add(&out,&out_size, __VA_ARGS__)
//
//#define INIT			\
//  static char *out = null;	\
//  static size_t out_size = 0;	\
//  if (!out) {			\
//    out_size = START_SIZE;	\
//    out = fc_malloc(out_size);	\
//  }				\
//  out[0] = '\0';
//
//#define RETURN	return out;
//
///****************************************************************************
//  Formats the parameters and appends them. Grows the buffer if
//  required.
//****************************************************************************/
//static void grow_printf(char **buffer, size_t *buffer_size,
//			final String format, va_list ap)
//{
//  size_t new_len;
//  static char buf[GROW_TMP_SIZE];
//
//  if (my_vsnprintf(buf, sizeof(buf), format, ap) == -1) {
//    util.die("Formatted string bigger than %lu", (unsigned long)sizeof(buf));
//  }
//
//  new_len = strlen(*buffer) + buf.length() + 1;
//
//  if (new_len > *buffer_size) {
//    /* It's important that we grow the buffer geometrically, otherwise the
//     * overhead adds up quickly. */
//    size_t new_size = MAX(new_len, *buffer_size * 2);
//
//    util.freelog(Log.LOG_VERBOSE, "expand from %lu to %lu to add '%s'",
//	    (unsigned long)*buffer_size, (unsigned long)new_size, buf);
//
//    *buffer_size = new_size;
//    *buffer = fc_realloc(*buffer, *buffer_size);
//  }
//  mystrlcat(*buffer, buf, *buffer_size);
//}
//
///****************************************************************************
//  Add a full line of text to the buffer.
//****************************************************************************/
//static void real_add_line(char **buffer, size_t * buffer_size,
//			  final String format)
//{
//  va_list args;
//
//  if ((*buffer)[0] != '\0') {
//    real_add(buffer, buffer_size, "%s", "\n");
//  }
//
//  va_start(args, format);
//  grow_printf(buffer, buffer_size, format, args);
//  va_end(args);
//}
//
///****************************************************************************
//  Add the text to the buffer.
//****************************************************************************/
//static void real_add(char **buffer, size_t * buffer_size, final String format,
//		     ...)
//{
//  va_list args;
//
//  va_start(args, format);
//  grow_printf(buffer, buffer_size, format, args);
//  va_end(args);
//}
//
///****************************************************************************
//  Return a short tooltip text for a terrain tile.
//****************************************************************************/
//final String mapview_get_terrain_tooltip_text(tile ptile)
//{
//  int infrastructure = get_tile_infrastructure_set(ptile);
//  INIT;
//
//#ifdef DEBUG
//  add_line("Location: (%d, %d) [%d]",
//	   ptile.x, ptile.y, ptile.continent);
//#endif
//  add_line("%s", map_get_tile_info_text(ptile));
//  if (infrastructure) {
//    add_line("%s",
//	     map_get_infrastructure_text(infrastructure));
//  }
//  RETURN;
//}
//
///****************************************************************************
//  Calculate the effects of various unit activities.
//****************************************************************************/
//static void calc_effect(enum unit_activity activity, tile ptile,
//			int diff[3])
//{
//  struct tile backup = *ptile;
//  int stats_before[3], stats_after[3];
//
//  stats_before[0] = get_food_tile(ptile);
//  stats_before[1] = get_shields_tile(ptile);
//  stats_before[2] = get_trade_tile(ptile);
//
//  /* BEWARE UGLY HACK AHEAD */
//
//  switch (activity) {
//  case ACTIVITY_ROAD:
//    Map.map_set_special(ptile, Terrain_H.S_ROAD);
//    break;
//  case ACTIVITY_RAILROAD:
//    Map.map_set_special(ptile, Terrain_H.S_RAILROAD);
//    break;
//  case ACTIVITY_MINE:
//    map_mine_tile(ptile);
//    break;
//
//  case ACTIVITY_IRRIGATE:
//    map_irrigate_tile(ptile);
//    break;
//
//  case ACTIVITY_TRANSFORM:
//    map_transform_tile(ptile);
//    break;
//  default:
//    assert(0!=1);
//  }
//
//  stats_after[0] = get_food_tile(ptile);
//  stats_after[1] = get_shields_tile(ptile);
//  stats_after[2] = get_trade_tile(ptile);
//
//  ptile.terrain = backup.terrain;
//  ptile.special = backup.special;
//  Map.reset_move_costs(ptile);
//  /* hopefully everything is now back in place */
//
//  diff[0] = stats_after[0] - stats_before[0];
//  diff[1] = stats_after[1] - stats_before[1];
//  diff[2] = stats_after[2] - stats_before[2];
//}
//
///****************************************************************************
//  Return a text describing what would be the results from a unit activity.
//****************************************************************************/
//static final String format_effect(enum unit_activity activity,
//				 unit punit)
//{
//  char parts[3][25];
//  int diff[3];
//  int n = 0;
//  INIT;
//
//  calc_effect(activity, punit.tile, diff);
//
//  if (diff[0] != 0) {
//    my_snprintf(parts[n], sizeof(parts[n]), "%+d food", diff[0]);
//    n++;
//  }
//
//  if (diff[1] != 0) {
//    my_snprintf(parts[n], sizeof(parts[n]), "%+d shield", diff[1]);
//    n++;
//  }
//
//  if (diff[2] != 0) {
//    my_snprintf(parts[n], sizeof(parts[n]), "%+d trade", diff[2]);
//    n++;
//  }
//  if (n == 0) {
//    add("none");
//  } else if (n == 1) {
//    add("%s", parts[0]);
//  } else if (n == 2) {
//    add("%s %s", parts[0], parts[1]);
//  } else if (n == 3) {
//    add("%s %s %s", parts[0], parts[1],		parts[2]);
//  } else {
//    assert(0!=1);
//  }
//  RETURN;
//}
//
///****************************************************************************
//  Return a short text for tooltip describing what a unit action does.
//****************************************************************************/
//final String mapview_get_unit_action_tooltip(unit punit,
//					    final String action,
//					    final String shortcut_)
//{
//  char shortcut[256];
//  INIT;
//
//  if (shortcut_) {
//    shortcut = util.my_snprintf( " (%s)", shortcut_);
//  } else {
//    shortcut = util.my_snprintf( "%s", "");
//  }
//
//  if (strcmp(action, "unit_fortifying") == 0) {
//    add_line("Fortify%s", shortcut);
//    add_line("Time: 1 turn, then until changed");
//    add_line("Effect: +50%% defense bonus");
//  } else if (strcmp(action, "unit_disband") == 0) {
//    add_line("Disband%s", shortcut);
//    add_line("Time: instantly, unit is destroyed");
//  } else if (strcmp(action, "unit_return_nearest") == 0) {
//    add_line("Return to nearest city%s", shortcut);
//    add_line("Time: unknown");
//  } else if (strcmp(action, "unit_sentry") == 0) {
//    add_line("Sentry%s", shortcut);
//    add_line("Time: instantly, lasts until changed");
//    add_line("Effect: Unit wakes up if enemy is near");
//  } else if (strcmp(action, "unit_add_to_city") == 0) {
//    add_line("Add to city%s", shortcut);
//    add_line("Time: instantly, unit is destroyed");
//    add_line("Effect: city size +1");
//  } else if (strcmp(action, "unit_build_city") == 0) {
//    add_line("Build city%s", shortcut);
//    add_line("Time: instantly, unit is destroyed");
//    add_line("Effect: create a city of size 1");
//  } else if (strcmp(action, "unit_road") == 0) {
//    add_line("Build road%s", shortcut);
//    add_line("Time: %d turns",
//          get_turns_for_activity_at(punit, ACTIVITY_ROAD, punit.tile));
//    add_line("Effect: %s",
//	     format_effect(ACTIVITY_ROAD, punit));
//  } else if (strcmp(action, "unit_irrigate") == 0) {
//    add_line("Build irrigation%s",shortcut);
//    add_line("Time: %d turns",
//      get_turns_for_activity_at(punit, ACTIVITY_IRRIGATE, punit.tile));
//    add_line("Effect: %s",
//	     format_effect(ACTIVITY_IRRIGATE, punit));
//  } else if (strcmp(action, "unit_mine") == 0) {
//    add_line("Build mine%s",shortcut);
//    add_line("Time: %d turns",
//	 get_turns_for_activity_at(punit, ACTIVITY_MINE, punit.tile));
//    add_line("Effect: %s",
//	     format_effect(ACTIVITY_MINE, punit));
//  } else if (strcmp(action, "unit_auto_settler") == 0) {
//    add_line("Auto-Settle%s",shortcut);
//    add_line("Time: unknown");
//    add_line("Effect: the computer performs settler activities");
//  } else {
//#if 0
//  ttype = punit.tile.terrain;
//  tinfo = get_tile_type(ttype);
//  if ((tinfo.irrigation_result != T_LAST)
//      && (tinfo.irrigation_result != ttype)) {
//    irrtext = util.my_snprintf( irrfmt,
//		(get_tile_type(tinfo.irrigation_result)).terrain_name);
//  } else if (Map.map_has_special(punit.tile, S_IRRIGATION)
//	     && Player_P.player_knows_techs_with_flag(Game.game.player_ptr, TF_FARMLAND)) {
//    irrtext = String.format( "Bu_ild Farmland");
//  }
//  if ((tinfo.mining_result != T_LAST) && (tinfo.mining_result != ttype)) {
//    mintext = util.my_snprintf( minfmt,
//		(get_tile_type(tinfo.mining_result)).terrain_name);
//  }
//  if ((tinfo.transform_result != T_LAST)
//      && (tinfo.transform_result != ttype)) {
//    transtext = util.my_snprintf( transfmt,
//		(get_tile_type(tinfo.transform_result)).terrain_name);
//  }
//
//  menus_rename("<main>/_Orders/Build _Irrigation", irrtext);
//  menus_rename("<main>/_Orders/Build _Mine", mintext);
//  menus_rename("<main>/_Orders/Transf_orm Terrain", transtext);
//#endif
//    add_line("tooltip for action %s isn't written yet",
//	     action);
//    util.freelog(Log.LOG_NORMAL, "warning: get_unit_action_tooltip: unknown action %s",
//	    action);
//  }
//  RETURN;
//}
//
///****************************************************************************
//  Get a tooltip for a possible city action.
//****************************************************************************/
//final String mapview_get_city_action_tooltip(city pcity,
//					    final String action,
//					    final String shortcut_)
//{
//  INIT;
//
//  if (strcmp(action, "city_buy") == 0) {
//    final String name;
//
//    if (pcity.is_building_unit) {
//      name = get_unit_type(pcity.currently_building).name;
//    } else {
//      name = City.get_impr_name_ex(pcity, pcity.currently_building);
//    }
//
//    add_line("Buy production");
//    add_line("Cost: %d (%d in treasury)",
//	     City.city_buy_cost(pcity), Game.game.player_ptr.economic.gold);
//    add_line("Producting: %s (%d turns)", name,
//	     city_turns_to_build(pcity, pcity.currently_building,
//				 pcity.is_building_unit, true));
//  } else {
//    add_line("tooltip for action %s isn't written yet", action);
//    util.freelog(Log.LOG_NORMAL,
//	    "warning: get_city_action_tooltip: unknown action %s", action);
//  }
//  RETURN;
//}  
//
///****************************************************************************
//  Get a short tooltip for a city.
//****************************************************************************/
//final String mapview_get_city_tooltip_text(city pcity)
//{
//  player owner = City.city_owner(pcity);
//  INIT;
//
//  add_line("%s", pcity.name);
//  add_line("%s", owner.name);
//  RETURN;
//}
//
///****************************************************************************
//  Get a longer tooltip for a city.
//****************************************************************************/
//final String mapview_get_city_info_text(city pcity)
//{
//  player owner = City.city_owner(pcity);
//  INIT;
//
//  add_line("City: %s (%s)", pcity.name,
//	   get_nation_name(owner.nation));
//  if (City.city_got_citywalls(pcity)) {
//    add(" with City Walls");
//  }
//  RETURN;
//}
//
///****************************************************************************
//  Get a tooltip for a unit.
//****************************************************************************/
//final String mapview_get_unit_tooltip_text(unit punit)
//{
//  unit_type ptype = punit.unit_type();
//  city pcity =
//      Player_P.player_find_city_by_id(Game.game.player_ptr, punit.homecity);
//  INIT;
//
//  add("%s", ptype.name);
//  if (ptype.veteran[punit.veteran].name[0] != '\0') {
//    add(" (%s)", ptype.veteran[punit.veteran].name);
//  }
//  add("\n");
//  add_line("%s", unit_activity_text(punit));
//  if (pcity) {
//    add_line("%s", pcity.name);
//  }
//  RETURN;
//}
//
///****************************************************************************
//  Get a longer tooltip for a unit.
//****************************************************************************/
//final String mapview_get_unit_info_text(unit punit)
//{
//  tile ptile = punit.tile;
//  final String activity_text = concat_tile_activity_text(ptile);
//  INIT;
//
//  if (activity_text.length()) {
//    add_line("Activity: %s", activity_text);
//  }
//  if (punit) {
//    char tmp[64] = { 0 };
//    unit_type ptype = punit.unit_type();
//
//    if (punit.owner == Game.game.player_idx) {
//      city pcity =
//	  Player_P.player_find_city_by_id(Game.game.player_ptr, punit.homecity);
//
//      if (pcity){
//	tmp = util.my_snprintf( "/%s", pcity.name);
//      }
//    }
//    add_line("Unit: %s(%s%s)", ptype.name,
//	     get_nation_name(punit.unit_owner().nation), tmp);
//    if (punit.owner != Game.game.player_idx) {
//      unit apunit = get_unit_in_focus();
//
//      if (apunit) {
//	/* chance to win when active unit is attacking the selected unit */
//	int att_chance = unit_win_chance(apunit, punit) * 100;
//	
//	/* chance to win when selected unit is attacking the active unit */
//	int def_chance = (1.0 - unit_win_chance(punit, apunit)) * 100;
//	
//	add_line("Chance to win: A:%d%% D:%d%%", att_chance, def_chance);
//      }
//    }
//    add_line("A:%d D:%d FP:%d HP:%d/%d%s",
//	     ptype.attack_strength,
//	     ptype.defense_strength, ptype.firepower, punit.hp,
//	     ptype.hp, punit.veteran ? " V" : "");
//  } 
//  RETURN;
//}
}