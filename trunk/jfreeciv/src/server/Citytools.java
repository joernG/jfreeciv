package server;

import common.Game;
import common.event_type;
import common.city.city;
import common.map.tile;
import common.player.player;

public class Citytools{
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
//#include "city.h"
//#include "events.h"
//#include "fcintl.h"
//#include "government.h"
//#include "idex.h"
//#include "log.h"
//#include "Map.map.h"
//#include "mem.h"
//#include "player.h"
//#include "rand.h"
//#include "shared.h"
//#include "support.h"
//#include "tech.h"
//#include "unit.h"
//
//#include "barbarian.h"
//#include "cityturn.h"
//#include "Gamelog.gamelog.h"
//#include "maphand.h"
//#include "plrhand.h"
//#include "sanitycheck.h"
//#include "sernet.h"
//#include "settlers.h"
//#include "spacerace.h"
//#include "srv_main.h"
//#include "unithand.h"
//#include "unittools.h"
//
//#include "aicity.h"
//#include "aiunit.h"
//
//#include "citytools.h"
//
//static int evaluate_city_name_priority(tile ptile,
//				       city_name city_name,
//				       int default_priority);
//static String search_for_city_name(tile ptile, city_name city_names,
//				  player pplayer);
//static void server_set_tile_city(city pcity, int city_x, int city_y,
//				 enum city_tile_type type);
//static boolean update_city_tile_status(city pcity, int city_x,
//				    int city_y);
//
///****************************************************************************
//  Freeze the workers (citizens on tiles) for the city.  They will not be
//  auto-arranged until unfreeze_workers is called.
//
//  Long explanation:
//
//  Historically Cityturn.auto_arrange_workers was called every time a city changed.
//  If the city grew or shrunk, a new tile became available or was removed,
//  the function would be called.  However in at least one place this breaks.
//  In some operations (like transfer_city) multiple things may change and
//  the city is not left in a sane state in between.  Calling
//  Cityturn.auto_arrange_workers after each change means it's called with an "insane"
//  city.  This can lead at best to a failed sanity check with a wasted call,
//  or at worse to a more major bug.  The solution is freeze_workers and
//  thaw_workers.
//
//  Call freeze_workers to freeze the auto-arranging of citizens.  So long as
//  the freeze is in place no arrangement will be done for this city.  Any
//  call to Cityturn.auto_arrange_workers will just queue up an arrangement for later.
//  Later when thaw_workers is called, the freeze is removed and the
//  auto-arrange will be done if there is any arrangement pending.
//
//  Freezing may safely be done more than once.
//
//  It is thus always safe to call freeze and thaw around any set of city
//  actions.  However this is unlikely to be needed in very many places.
//****************************************************************************/
//static void freeze_workers(city pcity)
//{
//  pcity.server.workers_frozen++;
//}
//
///****************************************************************************
//  Thaw (unfreeze) the workers (citizens on tiles) for the city.  The workers
//  will be auto-arranged if there is an arrangement pending.  See explanation
//  in freeze_workers().
//****************************************************************************/
//static void thaw_workers(city pcity)
//{
//  pcity.server.workers_frozen--;
//  assert(pcity.server.workers_frozen >= 0);
//  if (pcity.server.workers_frozen == 0 && pcity.server.needs_arrange) {
//    Cityturn.auto_arrange_workers(pcity);
//  }
//}
//
///****************************************************************
//Returns the priority of the city name at the given position,
//using its own internal algorithm.  Lower priority values are
//more desired, and all priorities are non-negative.
//
//This function takes into account Game.game.natural_city_names, and
//should be able to deal with any future options we want to add.
//*****************************************************************/
//static int evaluate_city_name_priority(tile ptile,
//				       city_name city_name,
//				       int default_priority)
//{
//  /* Lower values mean higher priority. */
//  float priority = (float)default_priority;
//  int goodness;
//  int type;
//
//  /* Increasing this value will increase the difference caused by
//     (non-)matching terrain.  A matching terrain is mult_factor
//     "better" than an unlisted terrain, which is mult_factor
//     "better" than a non-matching terrain. */
//  final float mult_factor = 1.4;
//
//  /*
//   * If natural city names aren't being used, we just return the
//   * base value.  This will have the effect of the first-listed
//   * city being used.  We do this here rather than special-casing
//   * it elewhere because this localizes everything to this
//   * function, even though it's a bit inefficient.
//   */
//  if (!Game.game.natural_city_names) {
//    return default_priority;
//  }
//
//  /*
//   * Assuming we're using the natural city naming system, we use
//   * an internal alorithm to calculate the priority of each name.
//   * It's a pretty fuzzy algorithm; we basically do the following:
//   *   - Change the priority scale from 0..n to 10..n+10.  This means
//   *     each successive city is 10% lower priority than the first.
//   *   - Multiply by a semi-random number.  This has the effect of
//   *     decreasing rounding errors in the successive calculations,
//   *     as well as introducing a smallish random effect.
//   *   - Check over all the applicable terrains, and
//   *     multiply or divide the priority based on whether or not
//   *     the terrain matches.  See comment below.
//   */
//
//  priority += 10.0;
//  priority *= 10.0 + Rand.myrand(5);
//
//  /*
//   * The terrain priority in the city_name struct will be either
//   * -1, 0, or 1.  We therefore take this as-is if the terrain is
//   * present, or negate it if not.
//   *
//   * The reason we multiply as well as divide the value is so
//   * that cities that don't care what terrain they are on (which
//   * is the default) will be left in the middle of the pack.  If
//   * we _only_ multiplied (or divided), then cities that had more
//   * terrain labels would have their priorities hurt (or helped).
//   */
//  goodness = Map.map_has_special(ptile, S_RIVER) ?
//	      city_name.river : -city_name.river;
//  if (goodness > 0) {
//    priority /= mult_factor;
//  } else if (goodness < 0) {
//    priority *= mult_factor;
//  }
//
//  for (type = T_FIRST; type < T_COUNT; type++) {
//    /* Now we do the same for every available terrain. */
//    goodness = is_terrain_near_tile(ptile, type) ?
//		 city_name.terrain[type] : -city_name.terrain[type];
//    if (goodness > 0) {
//      priority /= mult_factor;
//    } else if (goodness < 0) {
//      priority *= mult_factor;
//    }
//  }
//
//  return (int)priority;	
//}
//
///**************************************************************************
//Checks if a city name belongs to default city names of a particular
//player.
//**************************************************************************/
//static boolean is_default_city_name(final String name, player pplayer)
//{
//  nation_type nation = get_nation_by_plr(pplayer);
//  int choice;
//
//  for (choice = 0; nation.city_names[choice].name; choice++) {
//    if (mystrcasecmp(name, nation.city_names[choice].name) == 0) {
//      return true;
//    }
//  }
//
//  return false;
//}
//
///****************************************************************
//Searches through a city name list (a struct city_name array)
//to pick the best available city name, and returns a pointer to
//it.  The function checks if the city name is available and calls
//evaluate_city_name_priority to determine the priority of the
//city name.  If the list has no valid entries in it, null will be
//returned.
//*****************************************************************/
//static String search_for_city_name(tile ptile, city_name city_names,
//				  player pplayer)
//{
//  int choice, best_priority = -1;
//  char* best_name = null;
//
//  for (choice = 0; city_names[choice].name; choice++) {
//    if (!game_find_city_by_name(city_names[choice].name)
//	&& is_allowed_city_name(pplayer, city_names[choice].name, null, 0)) {
//      int priority = evaluate_city_name_priority(ptile, &city_names[choice],
//						 choice);
//
//      if (best_priority == -1 || priority < best_priority) {
//	best_priority = priority;
//	best_name = city_names[choice].name;
//      }
//    }
//  }
//
//  return best_name;
//}
//
///**************************************************************************
//Checks, if a city name is allowed for a player. If not, reports a
//reason for rejection. There's 4 different modes:
//0: no restrictions,
//1: a city name has to be unique to player
//2: a city name has to be globally unique
//3: a city name has to be globally unique, and players can't use names
//   that are in another player's default city names. (E.g., Swedish may not
//   call new cities or rename old cities as Helsinki, because it's in
//   Finns' default city names.  Duplicated names may be used by
//   either nation.)
//**************************************************************************/
public static boolean is_allowed_city_name(player pplayer, final String city_name,
			  String error_buf, int bufsz)
{
//  connection pconn = Connection.find_conn_by_user(pplayer.username);
//
//  /* Mode 1: A city name has to be unique for each player. */
//  if (Game.game.allowed_city_names == 1 &&
//      city_list_find_name(&pplayer.cities, city_name)) {
//    if (error_buf) {
//      error_buf = String.format "You already have a city called %s.",
//		  city_name);
//    }
    return false;
  }

//  /* Modes 2,3: A city name has to be globally unique. */
//  if ((Game.game.allowed_city_names == 2 || Game.game.allowed_city_names == 3)
//      && game_find_city_by_name(city_name)) {
//    if (error_buf) {
//      error_buf = String.format
//		  "A city called %s already exists.", city_name);
//    }
//    return false;
//  }
//
//  /* General rule: any name in our ruleset is allowed. */
//  if (is_default_city_name(city_name, pplayer)) {
//    return true;
//  }
//
//  /* 
//   * Mode 3: Check that the proposed city name is not in another
//   * player's default city names.  Note the name will already have been
//   * allowed if it is in this player's default city names list.
//   */
//  if (Game.game.allowed_city_names == 3) {
//    player pother = null;
//
//    for(player player2: Game.game.players){
//      if (player2 != pplayer && is_default_city_name(city_name, player2)) {
//	pother = player2;
//	break;
//      }
//    }
//
//    if (pother != null) {
//      if (error_buf) {
//	error_buf = String.format ("Can't use %s as a city name. It is " +
//					"reserved for %s."),
//		    city_name, Nation.get_nation_name_plural(pother.nation));
//      }
//      return false;
//    }
//  }
//
//  /* To prevent abuse, only players with HACK access (usually local
//   * connections) can use non-ascii names.  Otherwise players could use
//   * confusing garbage names in multi-player games.
//   *
//   * We can even reach here for an AI player, if all the cities of the
//   * original nation are exhausted and the backup nations have non-ascii
//   * names in them. */
//  if (!util.isLetter(city_name)
//      && (!pconn || pconn.access_level != ALLOW_HACK)) {
//    if (error_buf) {
//      error_buf = String.format
//		  ("%s is not a valid name. Only ASCII or " +
//		    "ruleset names are allowed for cities."),
//		  city_name);
//    }
//    return false;
//  }
//
//
//  return true;
//}

	/****************************************************************
Come up with a default name when a new city is about to be built.
Handle running out of names etc. gracefully.  Maybe we should keep
track of which names have been rejected by the player, so that we do
not suggest them again?
Returned pointer points into internal data structures or static
buffer etc, and should be considered read-only (and not freed)
by caller.
	 *****************************************************************/
	public static String city_name_suggestion(player pplayer, tile ptile)
	{
//  int i = 0, j;
//  boolean nations_selected[Game.game.nation_count];
//  int nation_list[Game.game.nation_count], n;
//  int queue_size;
//
//  static final int num_tiles = Map_H.MAP_MAX_WIDTH * Map_H.MAP_MAX_HEIGHT; 
//
//  /* tempname must be static because it's returned below. */
//  static char tempname[MAX_LEN_NAME];
//
//  /* This function follows a straightforward algorithm to look through
//   * nations to find a city name.
//   *
//   * We start by adding the player's nation to the queue.  Then we proceed:
//   * - Pick a random nation from the queue.
//   * - If it has a valid city name, use that.
//   * - Otherwise, add all parent and child nations to the queue.
//   * - If the queue is empty, add all remaining nations to it and continue.
//   *
//   * Variables used:
//   * - nation_list is a queue of nations to look through.
//   * - nations_selected tells whether each nation is in the queue already
//   * - queue_size gives the size of the queue (number of nations in it).
//   * - i is the current position in the queue.
//   * Note that nations aren't removed from the queue after they're processed.
//   * New nations are just added onto the end.
//   */
//
//  util.freelog(Log.LOG_VERBOSE, "Suggesting city name for %s at (%d,%d)",
//	  pplayer.name, ptile.x, ptile.y);
//  
//  memset(nations_selected, 0, sizeof(nations_selected));
//
//  queue_size = 1;
//  nation_list[0] = pplayer.nation;
//  nations_selected[pplayer.nation] = true;
//
//  while (i < Game.game.nation_count) {
//    for (; i < queue_size; i++) {
//      nation_type nation;
//      String name;
//
//      {
//	/* Pick a random nation from the queue. */
//	final int which = i + Rand.myrand(queue_size - i);
//	final int tmp = nation_list[i];
//
//	nation_list[i] = nation_list[which];
//	nation_list[which] = tmp;
//      }
//
//      nation = get_nation_by_idx(nation_list[i]);
//      name = search_for_city_name(ptile, nation.city_names, pplayer);
//
//      util.freelog(Log.LOG_DEBUG, "Looking through %s.", nation.name);
//
//      if (name) {
//	return name;
//      }
//
//      /* Append the nation's parent nations into the search tree. */
//      for (j = 0; nation.parent_nations[j] != NO_NATION_SELECTED; j++) {
//	n = nation.parent_nations[j];
//	if (!nations_selected[n]) {
//	  nation_list[queue_size] = n;
//	  nations_selected[n] = true;
//	  queue_size++;
//	  util.freelog(Log.LOG_DEBUG, "Parent %s.", get_nation_by_idx(n).name);
//	}
//      }
//
//      /* Append the nation's civil war nations into the search tree. */
//      for (j = 0; nation.civilwar_nations[j] != NO_NATION_SELECTED; j++) {
//	n = nation.civilwar_nations[j];
//	if (!nations_selected[n]) {
//	  nation_list[queue_size] = n;
//	  nations_selected[n] = true;
//	  queue_size++;
//	  util.freelog(Log.LOG_DEBUG, "Child %s.", get_nation_by_idx(n).name);
//	}
//      }
//    }
//
//    /* Append all remaining nations. */
//    for (n = 0; n < Game.game.nation_count; n++) {
//      if (!nations_selected[n]) {
//	nation_list[queue_size] = n;
//	nations_selected[n] = true;
//	queue_size++;
//	util.freelog(Log.LOG_DEBUG, "Misc nation %s.", get_nation_by_idx(n).name);
//      }
//    }
//  }
//
//  for (i = 1; i <= num_tiles; i++ ) {
//    tempname = String.format "City no. %d", i);
//    if (!game_find_city_by_name(tempname)) {
//      return tempname;
//    }
//  }
//  
//  /* This had better be impossible! */
//  assert(false);
//  tempname = String.format( "A poorly-named city");
//  return tempname;
		return "";
}
	
	/****************************************************************************
	  Return true iff the city can sell the given improvement.
	****************************************************************************/
	public static boolean can_sell_building(city pcity, int id)
	{
//	  return (city_got_building(pcity, id) ? !is_wonder(id) : false);
		return true;
	}
	
///**************************************************************************
//...
//**************************************************************************/
//city find_city_wonder(int id)
//{
//  return (Game.find_city_by_id(Game.game.global_wonders[id]));
//}
//
///**************************************************************************
//  calculate the remaining build points 
//**************************************************************************/
//int build_points_left(city pcity)
//{
//  int cost = Improvement.impr_build_shield_cost(pcity.currently_building);
//
//  return cost - pcity.shield_stock;
//}
//
///**************************************************************************
//  Will unit of this type be created as veteran?
//**************************************************************************/
//int do_make_unit_veteran(city pcity, int id)
//{
//  /* we current don't have any wonder or building that have influence on 
//     settler/worker units */
//  if (unit_type_flag(id, F_SETTLERS) || unit_type_flag(id, F_CITIES)) {
//    return 0;
//  }
//  
//  if (unit_type_flag(id, F_DIPLOMAT)) {
//    return (government_has_flag(get_gov_pcity(pcity), 
//                                G_BUILD_VETERAN_DIPLOMAT) ? 1 : 0);
//  }
//    
//  if (is_ground_unittype(id)) {
//    return (get_city_bonus(pcity, EFT_LAND_VETERAN) > 0) ? 1 : 0;
//  } else {
//    if (is_water_unit(id)) {
//      return (get_city_bonus(pcity, EFT_SEA_VETERAN) > 0) ? 1 : 0;
//    } else {
//      return (get_city_bonus(pcity, EFT_AIR_VETERAN) > 0) ? 1 : 0;
//    }
//  }
//  
//  return 0;
//}
//
///**************************************************************************
//  Return the cached shield bonus rate.  Don't confuse this with
//  get_city_shield_bonus which recomputes the value from scratch.
//**************************************************************************/
//int city_shield_bonus(city pcity)
//{
//  return pcity.shield_bonus;
//}
//
///**************************************************************************
//  Return the cached luxury bonus rate.  Don't confuse this with
//  get_city_luxury_bonus which recomputes the value from scratch.
//**************************************************************************/
//int city_luxury_bonus(city pcity)
//{
//  return pcity.luxury_bonus;
//}
//
///**************************************************************************
//  Return the cached tax bonus rate.  Don't confuse this with
//  get_city_tax_bonus which recomputes the value from scratch.
//**************************************************************************/
//int city_tax_bonus(city pcity)
//{
//  return pcity.tax_bonus;
//}
//
///**************************************************************************
//  Return the cached science bonus rate.  Don't confuse this with
//  get_city_science_bonus which recomputes the value from scratch.
//**************************************************************************/
//int city_science_bonus(city pcity)
//{
//  return pcity.science_bonus;
//}
//
///*********************************************************************
//Note: the old unit is not wiped here.
//***********************************************************************/
//static void transfer_unit(unit punit, city tocity,
//			  boolean verbose)
//{
//  player from_player = punit.unit_owner();
//  player to_player = City.city_owner(tocity);
//
//  if (from_player == to_player) {
//    util.freelog(Log.LOG_VERBOSE, "Changed homecity of %s's %s to %s",
//	    from_player.name, unit_name(punit.type), tocity.name);
//    if (verbose) {
//      notify_player(from_player, "Game: Changed homecity of %s to %s.",
//		    unit_name(punit.type), tocity.name);
//    }
//  } else {
//    city in_city = map_get_city(punit.tile);
//    if (in_city) {
//      util.freelog(Log.LOG_VERBOSE, "Transfered %s in %s from %s to %s",
//	      unit_name(punit.type), in_city.name,
//	      from_player.name, to_player.name);
//      if (verbose) {
//	notify_player(from_player, "Game: Transfered %s in %s from %s to %s.",
//		      unit_name(punit.type), in_city.name,
//		      from_player.name, to_player.name);
//      }
//    } else {
//      util.freelog(Log.LOG_VERBOSE, "Transfered %s from %s to %s",
//	      unit_name(punit.type),
//	      from_player.name, to_player.name);
//      if (verbose) {
//	notify_player(from_player, "Game: Transfered %s from %s to %s.",
//		      unit_name(punit.type),
//		      from_player.name, to_player.name);
//      }
//    }
//  }
//
//  /* FIXME: Creating a new unit and deleting the old one is a gross hack.
//   * instead we should make the change on the existing unit, even though
//   * it's more work. */
//  Unittools.create_unit_full(to_player, punit.tile, punit.type,
//			  punit.veteran, tocity.id, punit.moves_left,
//			  punit.hp,
//			  find_unit_by_id(punit.transported_by));
//}
//
///*********************************************************************
// Units in a bought city are transferred to the new owner, units 
// supported by the city, but held in other cities are updated to
// reflect those cities as their new homecity.  Units supported 
// by the bought city, that are not in a city square may be deleted.
//
// - Kris Bubendorfer <Kris.Bubendorfer@MCS.VUW.AC.NZ>
//
//pplayer: The player recieving the units if they are not disbanded and
//         are not in a city
//pvictim: The owner of the city the units are transferred from.
//units:   A list of units to be transferred, typically a cities unit list.
//pcity:   Default city the units are transferred to.
//exclude_city: The units cannot be transferred to this city.
//kill_outside: Units outside this range are deleted. -1 means no units
//              are deleted.
//verbose: Messages are sent to the involved parties.
//***********************************************************************/
//void transfer_city_units(player pplayer, player pvictim, 
//			 unit_list units, city pcity,
//			 city exclude_city,
//			 int kill_outside, boolean verbose)
//{
//  tile ptile = pcity.tile;
//
//  /* Transfer enemy units in the city to the new owner.
//   * Only relevant if we are transferring to another player. */
//  if (pplayer != pvictim) {
//	for(unit vunit: (ptile).units.data){
//      /* Don't transfer units already owned by new city-owner --wegge */ 
//      if (vunit.unit_owner() == pvictim) {
//	transfer_unit(vunit, pcity, verbose);
//	Unittools.wipe_unit(vunit);
//	unit_list_unlink(units, vunit);
//      } else if (!pplayers_allied(pplayer, vunit.unit_owner())) {
//        /* the owner of vunit is allied to pvictim but not to pplayer */
//        bounce_unit(vunit, verbose);
//      }
//    }
//  }
//
//  /* Any remaining units supported by the city are either given new home
//     cities or maybe destroyed */
//  unit_list_iterate_safe(*vunit, vunit) {
//	for(unit vunit: vunit){
//    city new_home_city = map_get_city(vunit.tile);
//    if (new_home_city && new_home_city != exclude_city
//	&& City.city_owner(new_home_city) == vunit.unit_owner()) {
//      /* unit is in another city: make that the new homecity,
//	 unless that city is actually the same city (happens if disbanding) */
//      transfer_unit(vunit, new_home_city, verbose);
//    } else if (kill_outside == -1
//	       || Map.real_map_distance(vunit.tile, ptile) <= kill_outside) {
//      /* else transfer to specified city. */
//      transfer_unit(vunit, pcity, verbose);
//    } else {
//      /* The unit is lost.  Call notify_player (in all other cases it is
//       * called autmatically). */
//      util.freelog(Log.LOG_VERBOSE, "Lost %s's %s at (%d,%d) when %s was lost.",
//	      vunit.unit_owner().name, unit_name(vunit.type),
//	      vunit.tile.x, vunit.tile.y, pcity.name);
//      if (verbose) {
//	Plrhand.notify_player_ex(vunit.unit_owner(), vunit.tile,
//			 E_UNIT_LOST,
//			 "Game: %s lost along with control of %s.",
//			 unit_name(vunit.type), pcity.name);
//      }
//    }
//
//    /* not deleting cargo as it may be carried by units transferred later.
//       no cargo deletion => no trouble with "units" list.
//       In cases where the cargo can be left without transport the calling
//       function should take that into account. */
//    Unittools.wipe_unit(vunit);
//  }
//}
//
///**********************************************************************
//dist_nearest_city (in ai.c) does not seem to do what I want or expect
//this function finds the closest friendly city to pos x,y.  I'm sure 
//there must be a similar function somewhere, I just can't find it.
//
//                               - Kris Bubendorfer 
//
//If sea_required, returned city must be adjacent to ocean.
//If pexclcity, do not return it as the closest city.
//Returns null if no satisfactory city can be found.
//***********************************************************************/
//city find_closest_owned_city(player pplayer, tile ptile,
//				     boolean sea_required, city pexclcity)
//{
//  int dist = -1;
//  city rcity = null;
//  city_list_iterate(pplayer.cities, pcity)
//    if ((Map.real_map_distance(ptile, pcity.tile) < dist || dist == -1) &&
//        (!sea_required || is_ocean_near_tile(pcity.tile)) &&
//        (!pexclcity || (pexclcity != pcity))) {
//      dist = Map.real_map_distance(ptile, pcity.tile);
//      rcity = pcity;
//    }
//  }
//
//  return rcity;
//}
//
///**************************************************************************
//  called when a player conquers a city, remove buildings (not wonders and 
//  always palace) with Game.game.razechance% chance, barbarians destroy more
//  set the city's shield stock to 0
//**************************************************************************/
//static void raze_city(city pcity)
//{
//  int razechance = Game.game.razechance;
//
//  /* We don't use city_remove_improvement here as the global effects
//     stuff has already been handled by transfer_city */
//  pcity.improvements[Game.game.palace_building] = I_NONE;
//
//  /* land barbarians are more likely to destroy city improvements */
//  if (is_land_barbarian(City.city_owner(pcity)))
//    razechance += 30;
//
//  built_impr_iterate(pcity, i) {
//    if (!is_wonder(i) && (Rand.myrand(100) < razechance)) {
//      pcity.improvements[i]=I_NONE;
//    }
//  } built_impr_iterate_end;
//
//  nullify_prechange_production(pcity);
//  pcity.shield_stock = 0;
//}
//
///**************************************************************************
//The following has to be called every time a city, pcity, has changed
//owner to update the city's traderoutes.
//**************************************************************************/
//static void reestablish_city_trade_routes(city pcity, int cities[]) 
//{
//  int i;
//  city oldtradecity;
//
//  for (i = 0; i < NUM_TRADEROUTES; i++) {
//    if (cities[i] != 0) {
//      oldtradecity = Game.find_city_by_id(cities[i]);
//      assert(oldtradecity != null);
//      if (can_cities_trade(pcity, oldtradecity)
//          && can_establish_trade_route(pcity, oldtradecity)) {   
//	establish_trade_route(pcity, oldtradecity);
//      }
//      /* refresh regardless; either it lost a trade route or
//	 the trade route revenue changed. */
//      Cityturn.city_refresh(oldtradecity);
//      send_city_info(City.city_owner(oldtradecity), oldtradecity);
//    }
//  }
//}
//
///**************************************************************************
//Create a palace in a random city. Used when the capital was conquered.
//**************************************************************************/
//static void build_free_palace(player pplayer,
//			       final String final old_capital_name)
//{
//  int size = pplayer.cities.foo_list_size();
//  city pnew_capital;
//
//  if (size == 0) {
//    /* The last city was removed or transferred to the enemy. R.I.P. */
//    return;
//  }
//
//  assert(pplayer.find_palace() == null);
//
//  pnew_capital = city_list_get(&pplayer.cities, Rand.myrand(size));
//
//  city_add_improvement(pnew_capital, Game.game.palace_building);
//
//  /*
//   * send_player_cities will recalculate all cities and send them to
//   * the client.
//   */
//  send_player_cities(pplayer);
//
//  notify_player(pplayer, ("Game: You lost your capital %s. A new palace " +
//			   "was built in %s."), old_capital_name,
//		pnew_capital.name);
//
//  /* 
//   * The enemy want to see the new capital in his intelligence
//   * report. 
//   */
//  send_city_info(null, pnew_capital);
//}
//
///**********************************************************************
//Handles all transactions in relation to transferring a city.
//
//The kill_outside and transfer_unit_verbose arguments are passed to
//transfer_city_units(), which is called in the middle of the function.
//***********************************************************************/
//void transfer_city(player ptaker, city pcity,
//		   int kill_outside, boolean transfer_unit_verbose,
//		   boolean resolve_stack, boolean raze)
//{
//  int i;
//  Speclists<unit> old_city_units;
//  player pgiver = City.city_owner(pcity);
//  int old_trade_routes[NUM_TRADEROUTES];
//  boolean had_palace = pcity.improvements[Game.game.palace_building] != I_NONE;
//  char old_city_name[MAX_LEN_NAME];
//
//  assert(pgiver != ptaker);
//
//  freeze_workers(pcity);
//
//  unit_list_init(&old_city_units);
//  for (unit punit : pcity.units_supported.data) {
//    unit_list_insert(&old_city_units, punit);
//    /* otherwise we might delete the homecity from under the unit
//       in the client. */
//    punit.homecity = 0;
//    Unittools.send_unit_info(null, punit);
//  } }
//  unit_list_unlink_all(&pcity.units_supported);
//  unit_list_init(&pcity.units_supported);
//
//  /* Remove all global improvement effects that this city confers (but
//     then restore the local improvement list - we need this to restore the
//     global effects for the new city owner) */
//  built_impr_iterate(pcity, i) {
//    city_remove_improvement(pcity, i);
//    pcity.improvements[i] = I_ACTIVE;
//  } built_impr_iterate_end;
//
//  give_citymap_from_player_to_player(pcity, pgiver, ptaker);
//  map_unfog_pseudo_city_area(ptaker, pcity.tile);
//
//  old_city_name = pcity.name;
//  if (Game.game.allowed_city_names == 1
//      && city_list_find_name(&ptaker.cities, pcity.name)) {
//    pcity.name = String.format(
//	       city_name_suggestion(ptaker, pcity.tile));
//    Plrhand.notify_player_ex(ptaker, pcity.tile, event_type.E_NOEVENT,
//		     ("You already had a city called %s." +
//		       " The city was renamed to %s."), old_city_name,
//		     pcity.name);
//  }
//
//  /* Has to follow the unfog call above. */
//  city_list_unlink(&pgiver.cities, pcity);
//  pcity.owner = ptaker.player_no;
//  city_list_insert(&ptaker.cities, pcity);
//
//  /* Update the national borders. */
//  map_update_borders_city_change(pcity);
//
//  transfer_city_units(ptaker, pgiver, &old_city_units,
//		      pcity, null,
//		      kill_outside, transfer_unit_verbose);
//  /* The units themselves are allready freed by transfer_city_units. */
//  unit_list_unlink_all(&old_city_units);
//  reset_move_costs(pcity.tile);
//
//  if (resolve_stack) {
//    resolve_unit_stacks(pgiver, ptaker, transfer_unit_verbose);
//  }
//
//  /* Update the city's trade routes. */
//  for (i = 0; i < NUM_TRADEROUTES; i++)
//    old_trade_routes[i] = pcity.trade[i];
//  for (i = 0; i < NUM_TRADEROUTES; i++) {
//    city pother_city = Game.find_city_by_id(pcity.trade[i]);
//
//    assert(pcity.trade[i] == 0 || pother_city != null);
//
//    if (pother_city) {
//      remove_trade_route(pother_city, pcity);
//    }
//  }
//  reestablish_city_trade_routes(pcity, old_trade_routes);
//
//  /*
//   * Give the new owner infos about all cities which have a traderoute
//   * with the transferred city.
//   */
//  for (i = 0; i < NUM_TRADEROUTES; i++) {
//    city pother_city = Game.find_city_by_id(pcity.trade[i]);
//    if (pother_city) {
//      reality_check_city(ptaker, pother_city.tile);
//      update_dumb_city(ptaker, pother_city);
//      send_city_info(ptaker, pother_city);
//    }
//  }
//
//  Cityturn.city_refresh(pcity);
//
//  /* 
//   * maybe_make_contact have to be called before
//   * update_city_tile_status_map below since the diplomacy status can
//   * influence if a tile is available.
//   */
//  maybe_make_contact(pcity.tile, ptaker);
//
//  map_city_radius_iterate(pcity.tile, ptile) {
//    update_city_tile_status_map(pcity, ptile);
//  } map_city_radius_iterate_end;
//  Cityturn.auto_arrange_workers(pcity);
//  thaw_workers(pcity);
//  if (raze)
//    raze_city(pcity);
//
//  /* Restore any global improvement effects that this city confers */
//  built_impr_iterate(pcity, i) {
//    city_add_improvement(pcity, i);
//  } built_impr_iterate_end;
//
//  /* Set production to something valid for pplayer, if not. */
//  if ((pcity.is_building_unit
//       && !City.can_build_unit_direct(pcity, pcity.currently_building))
//      || (!pcity.is_building_unit
//          && !City.can_build_improvement(pcity, pcity.currently_building))) {
//    advisor_choose_build(ptaker, pcity);
//  } 
//
//  send_city_info(null, pcity);
//
//  /* What wasn't obsolete for the old owner may be so now. */
//  remove_obsolete_buildings_city(pcity, true);
//
//  if (terrain_control.may_road
//      && player_knows_techs_with_flag (ptaker, TF_RAILROAD)
//      && !Map.map_has_special(pcity.tile, Terrain_H.S_RAILROAD)) {
//    notify_player(ptaker,
//		  ("Game: The people in %s are stunned by your" +
//		    " technological insight!\n" +
//		    "      Workers spontaneously gather and upgrade" +
//		    " the city with railroads."),
//		  pcity.name);
//    map_set_special(pcity.tile, Terrain_H.S_RAILROAD);
//    Maphand.update_tile_knowledge(pcity.tile);
//  }
//
//  map_fog_pseudo_city_area(pgiver, pcity.tile);
//
//  /* Build a new palace for free if the player lost her capital and
//     savepalace is on. */
//  if (had_palace && Game.game.savepalace) {
//    build_free_palace(pgiver, pcity.name);
//  }
//
//  Sanitycheck.sanity_check_city(pcity);
//  sync_cities();
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void create_city(player pplayer, tile ptile,
//		 final String name)
//{
//  city pcity;
//  int x_itr, y_itr;
//  nation_type nation = get_nation_by_plr(pplayer);
//
//  util.freelog(Log.LOG_DEBUG, "Creating city %s", name);
//
//  if (terrain_control.may_road) {
//    map_set_special(ptile, Terrain_H.S_ROAD);
//    if (player_knows_techs_with_flag(pplayer, TF_RAILROAD)) {
//      map_set_special(ptile, Terrain_H.S_RAILROAD);
//      Maphand.update_tile_knowledge(ptile);
//    }
//  }
//
//  /* It is possible that Maphand.update_tile_knowledge() already sent tile information
//   * to some players, but we don't want to make any special handling for
//   * those cases.  The network code may prevent asecond packet from being
//   * sent anyway. */
//  send_tile_info(null, ptile);
//
//  pcity = create_city_virtual(pplayer, ptile, name);
//  pcity.ai.trade_want = TRADE_WEIGHTING;
//  pcity.id = get_next_id_number();
//  idex_register_city(pcity);
//
//  if (!pplayer.capital) {
//    int i;
//    pplayer.capital = true;
//
//    for (i = 0; i < MAX_NUM_BUILDING_LIST; i++) {
//      if (Game.game.rgame.global_init_buildings[i] == B_LAST) {
//	break;
//      }
//      city_add_improvement(pcity, Game.game.rgame.global_init_buildings[i]);
//    }
//    for (i = 0; i < MAX_NUM_BUILDING_LIST; i++) {
//      if (nation.init_buildings[i] == B_LAST) {
//	break;
//      }
//      city_add_improvement(pcity, nation.init_buildings[i]);
//    }
//  }
//
//  /* Before arranging workers to show unknown land */
//  map_unfog_pseudo_city_area(pplayer, ptile);
//
//  map_set_city(ptile, pcity);
//
//  city_list_insert(&pplayer.cities, pcity);
//
//  /* it is possible to build a city on a tile that is already worked
//   * this will displace the worker on the newly-built city's tile -- Syela */
//  for (y_itr = 0; y_itr < City_H.CITY_MAP_SIZE; y_itr++) {
//    for (x_itr = 0; x_itr < City_H.CITY_MAP_SIZE; x_itr++) {
//      if (City.is_valid_city_coords(x_itr, y_itr)
//	  && city_can_work_tile(pcity, x_itr, y_itr))
//	pcity.city_map[x_itr][y_itr] = city_tile_type.C_TILE_EMPTY;
//      else
//	pcity.city_map[x_itr][y_itr] = city_tile_type.C_TILE_UNAVAILABLE;
//    }
//  }
//
//  /* Update the national borders.  This updates the citymap tile
//   * status and so must be done after the above. */
//  map_update_borders_city_change(pcity);
//
//  server_set_tile_city(pcity, City_H.CITY_MAP_SIZE/2, City_H.CITY_MAP_SIZE/2, city_tile_type.C_TILE_WORKER);
//  Cityturn.auto_arrange_workers(pcity);
//
//  Cityturn.city_refresh(pcity);
//
//  /* Put vision back to normal, if fortress acted as a watchtower */
//  if (Map.map_has_special(ptile, S_FORTRESS)) {
//    unit_list_iterate((ptile).units, punit) {
//      player owner = punit.unit_owner();
//      if (player_knows_techs_with_flag(owner, TF_WATCHTOWER)) {
//        unfog_area(owner, punit.tile,
//                   punit.unit_type().vision_range);
//        fog_area(owner, punit.tile, get_watchtower_vision(punit));
//      }
//    }
//    }
//  }
//  Map.map_clear_special(ptile, S_FORTRESS);
//  Maphand.update_tile_knowledge(ptile);
//
//  reset_move_costs(ptile);
///* I stupidly thought that setting Terrain_H.S_ROAD took care of this, but of course
//the city_id isn't set when Terrain_H.S_ROAD is set, so reset_move_costs doesn't allow
//sea movement at the point it's called.  This led to a problem with the
//warmap (but not the GOTOmap warmap) which meant the AI was very reluctant
//to use ferryboats.  I really should have identified this sooner. -- Syela */
//
//  pcity.synced = false;
//  send_city_info(null, pcity);
//  sync_cities(); /* Will also send pcity. */
//
//  Plrhand.notify_player_ex(pplayer, ptile, E_CITY_BUILD,
//		   "Game: You have founded %s", pcity.name);
//  maybe_make_contact(ptile, City.city_owner(pcity));
//
//  unit_list_iterate((ptile).units, punit) {
//    city home = Game.find_city_by_id(punit.homecity);
//
//    /* Catch fortress building, transforming into ocean, etc. */
//    if (!can_unit_continue_current_activity(punit)) {
//      handle_unit_activity_request(punit, unit_activity.ACTIVITY_IDLE);
//    }
//
//    /* Update happiness (the unit may no longer cause unrest). */
//    if (home) {
//      Cityturn.city_refresh(home);
//      send_city_info(City.city_owner(home), home);
//    }
//  } }
//  Sanitycheck.sanity_check_city(pcity);
//
//  Gamelog.gamelog(GAMELOG_FOUNDCITY, pcity);
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void remove_city(city pcity)
//{
//  int o;
//  player pplayer = City.city_owner(pcity);
//  tile ptile = pcity.tile;
//  boolean had_palace = pcity.improvements[Game.game.palace_building] != I_NONE;
//  String city_name = mystrdup(pcity.name);
//
//  built_impr_iterate(pcity, i) {
//    city_remove_improvement(pcity, i);
//  } built_impr_iterate_end;
//
//  /* This is cutpasted with modifications from transfer_city_units. Yes, it is ugly.
//     But I couldn't see a nice way to make them use the same code */
//  for (unit punit : pcity.units_supported.data) {
//    city new_home_city = map_get_city(punit.tile);
//    ptile = punit.tile;
//
//    if (new_home_city
//	&& new_home_city != pcity
//	&& City.city_owner(new_home_city) == pplayer) {
//      /* unit is in another city: make that the new homecity,
//	 unless that city is actually the same city (happens if disbanding) */
//      util.freelog(Log.LOG_VERBOSE, "Changed homecity of %s in %s",
//	      unit_name(punit.type), new_home_city.name);
//      notify_player(pplayer, "Game: Changed homecity of %s in %s.",
//		    unit_name(punit.type), new_home_city.name);
//      Unittools.create_unit_full(City.city_owner(new_home_city), ptile,
//			      punit.type, punit.veteran, new_home_city.id,
//			      punit.moves_left, punit.hp, null);
//    }
//
//    Unittools.wipe_unit(punit);
//  }
//
//  ptile = pcity.tile;
//
//  /* make sure ships are not left on land when city is removed. */
// MOVE_SEA_UNITS:
//  for (unit punit : ptile.units.data) {
//    boolean moved;
//    if (!punit
//	|| !Map.same_pos(punit.tile, ptile)
//	|| !is_sailing_unit(punit)) {
//      continue;
//    }
//
//    handle_unit_activity_request(punit, unit_activity.ACTIVITY_IDLE);
//    moved = false;
//    for(tile tile1: util.adjc_tile_iterate(ptile)) {
//      if (Terrain_H.is_ocean(tile1.terrain)) {
//	if (could_unit_move_to_tile(punit, tile1) == 1) {
//	  moved = Unithand.handle_unit_move_request(punit, tile1, false, true);
//	  if (moved) {
//	    Plrhand.notify_player_ex(punit.unit_owner(), null, event_type.E_NOEVENT,
//			     ("Game: Moved %s out of disbanded city %s " +
//			       "to avoid being landlocked."),
//			     punit.unit_type().name, pcity.name);
//	    goto OUT;
//	  }
//	}
//      }
//    }
//  OUT:
//    if (!moved) {
//      Plrhand.notify_player_ex(punit.unit_owner(), null, event_type.E_NOEVENT,
//		       ("Game: When %s was disbanded your %s could not " +
//			 "get out, and it was therefore stranded."),
//		       pcity.name, punit.unit_type().name);
//      Unittools.wipe_unit(punit);
//    }
//    /* We just messed with the unit list. Avoid trouble by starting over.
//       Note that the problem is reduced by one unit, so no loop trouble. */
//    goto MOVE_SEA_UNITS;
//  }
//
//  for (o = 0; o < NUM_TRADEROUTES; o++) {
//    city pother_city = Game.find_city_by_id(pcity.trade[o]);
//
//    assert(pcity.trade[o] == 0 || pother_city != null);
//
//    if (pother_city) {
//      remove_trade_route(pother_city, pcity);
//    }
//  }
//
//  /* idex_unregister_city() is called in game_remove_city() below */
//
//  /* dealloc_id(pcity.id); We do NOT dealloc cityID's as the cities may still be
//     alive in the client. As the number of removed cities is small the leak is
//     acceptable. */
//
//  game_remove_city(pcity);
//  map_update_borders_city_destroyed(ptile);
//
//  for(player other_player: Game.game.players){
//    if (Maphand.map_is_known_and_seen(ptile, other_player)) {
//      reality_check_city(other_player, ptile);
//    }
//  }
//
//  map_fog_pseudo_city_area(pplayer, ptile);
//
//  reset_move_costs(ptile);
//
//  /* Update available tiles in adjacent cities. */
//  map_city_radius_iterate(ptile, tile1) {
//    /* For every tile the city could have used. */
//    map_city_radius_iterate(tile1, tile2) {
//      /* We see what cities are inside reach of the tile. */
//      city pcity = map_get_city(tile2);
//      if (pcity) {
//	update_city_tile_status_map(pcity, tile1);
//      }
//    } map_city_radius_iterate_end;
//  } map_city_radius_iterate_end;
//
//  /* Build a new palace for free if the player lost her capital and
//     savepalace is on. */
//  if (had_palace && Game.game.savepalace) {
//    build_free_palace(pplayer, city_name);
//  }
//
//  free(city_name);
//
//  sync_cities();
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void handle_unit_enter_city(unit punit, city pcity)
//{
//  boolean do_civil_war = false;
//  int coins;
//  player pplayer = punit.unit_owner();
//  player cplayer = City.city_owner(pcity);
//
//  /* If not at war, may peacefully enter city. Or, if we cannot occupy
//   * the city, this unit entering will not trigger the effects below. */
//  if (!pplayers_at_war(pplayer, cplayer)
//      || !COULD_OCCUPY(punit)) {
//    return;
//  }
//
//  /* Okay, we're at war - invader captures/destroys city... */
//  
//  /* If a capital is captured, then spark off a civil war 
//     - Kris Bubendorfer
//     Also check spaceships --dwp
//  */
//  if (pcity.is_capital()
//      && (cplayer.spaceship.state == spaceship_state.SSHIP_STARTED
//          || cplayer.spaceship.state == spaceship_state.SSHIP_LAUNCHED)) {
//    spaceship_lost(cplayer);
//  }
//  
//  if (pcity.is_capital()
//      && cplayer.cities.foo_list_size() >= Game.game.civilwarsize
//      && Game.game.nplayers < Game.game.playable_nation_count
//      && Game.game.civilwarsize < GAME_MAX_CIVILWARSIZE
//      && get_num_human_and_ai_players() < Shared_H.MAX_NUM_PLAYERS
//      && civil_war_triggered(cplayer)) {
//    do_civil_war = true;
//  }
//
//  /* 
//   * We later remove a citizen. Lets check if we can save this since
//   * the city will be destroyed.
//   */
//  if (pcity.size <= 1) {
//    Plrhand.notify_player_ex(pplayer, pcity.tile, E_UNIT_WIN_ATT,
//		     "Game: You destroy %s completely.", pcity.name);
//    Plrhand.notify_player_ex(cplayer, pcity.tile, E_CITY_LOST, 
//		     "Game: %s has been destroyed by %s.", 
//		     pcity.name, pplayer.name);
//    Gamelog.gamelog(GAMELOG_LOSECITY, City.city_owner(pcity), pplayer, pcity, "destroyed");
//    remove_city(pcity);
//    if (do_civil_war) {
//      civil_war(cplayer);
//    }
//    return;
//  }
//
//  coins = cplayer.economic.gold;
//  coins = Rand.myrand((coins / 20) + 1) + (coins * (pcity.size)) / 200;
//  pplayer.economic.gold += coins;
//  cplayer.economic.gold -= coins;
//  Plrhand.send_player_info(cplayer, cplayer);
//  if (pcity.original != pplayer.player_no) {
//    Plrhand.notify_player_ex(pplayer, pcity.tile, E_UNIT_WIN_ATT, 
//		     ("Game: You conquer %s, your lootings accumulate" +
//		       " to %d gold!"), 
//		     pcity.name, coins);
//    Plrhand.notify_player_ex(cplayer, pcity.tile, E_CITY_LOST, 
//		     ("Game: %s conquered %s and looted %d gold" +
//		       " from the city."),
//		     pplayer.name, pcity.name, coins);
//    Gamelog.gamelog(GAMELOG_LOSECITY, City.city_owner(pcity), pplayer, pcity, "conquered");
//  } else {
//    Plrhand.notify_player_ex(pplayer, pcity.tile, E_UNIT_WIN_ATT, 
//		     ("Game: You have liberated %s!" +
//		       " Lootings accumulate to %d gold."),
//		     pcity.name, coins);
//    
//    Plrhand.notify_player_ex(cplayer, pcity.tile, E_CITY_LOST, 
//		     ("Game: %s liberated %s and looted %d gold" +
//		       " from the city."),
//		     pplayer.name, pcity.name, coins);
//
//    Gamelog.gamelog(GAMELOG_LOSECITY, City.city_owner(pcity), pplayer, pcity, "liberated");
//  }
//
//  get_a_tech(pplayer, cplayer);
//  make_partisans(pcity);
//
//  /* We transfer the city first so that it is in a consistent state when
//   * the size is reduced. */
//  transfer_city(pplayer, pcity , 0, true, true, true);
//  city_reduce_size(pcity, 1);
//  Plrhand.send_player_info(pplayer, pplayer); /* Update techs */
//
//  if (do_civil_war) {
//    civil_war(cplayer);
//  }
//}
//
///**************************************************************************
//  Returns true if the player owns a city which has a traderoute with
//  the given city.
//**************************************************************************/
//static boolean player_has_traderoute_with_city(player pplayer,
//					   city pcity)
//{
//  int i;
//
//  for (i = 0; i < NUM_TRADEROUTES; i++) {
//    city other = Game.find_city_by_id(pcity.trade[i]);
//    if (other && City.city_owner(other) == pplayer) {
//      return true;
//    }
//  }
//  return false;
//}
//
///**************************************************************************
//This fills out a package from a players dumb_city.
//**************************************************************************/
//static void package_dumb_city(struct player* pplayer, tile ptile,
//			      packet_city_short_info packet)
//{
//  dumb_city pdcity = map_get_player_tile(ptile, pplayer).city;
//  city pcity = map_get_city(ptile);
//
//  packet.id = pdcity.id;
//  packet.owner = pdcity.owner;
//  packet.x = ptile.x;
//  packet.y = ptile.y;
//  packet.name = pdcity.name;
//
//  packet.size = pdcity.size;
//
//  if (pcity && pcity.id == pdcity.id && pcity.is_capital()) {
//    packet.capital = true;
//  } else {
//    packet.capital = false;
//  }
//
//  packet.walls = pdcity.has_walls;
//  packet.occupied = pdcity.occupied;
//  packet.happy = pdcity.happy;
//  packet.unhappy = pdcity.unhappy;
//
//  if (pcity && player_has_traderoute_with_city(pplayer, pcity)) {
//    packet.tile_trade = pcity.tile_trade;
//  } else {
//    packet.tile_trade = 0;
//  }
//}
//
///**************************************************************************
//  Update plrtile information about the city, and send out information to
//  the clients if it has changed.
//**************************************************************************/
//void refresh_dumb_city(city pcity)
//{
//  for(player pplayer: Game.game.players){
//    if (Maphand.map_is_known_and_seen(pcity.tile, pplayer)
//	|| player_has_traderoute_with_city(pplayer, pcity)) {
//      if (update_dumb_city(pplayer, pcity)) {
//	struct packet_city_short_info packet;
//
//	if (City.city_owner(pcity) != pplayer) {
//	  /* Don't send the short_city information to someone who can see the
//	   * city's internals.  Doing so would really confuse the client. */
//	  package_dumb_city(pplayer, pcity.tile, &packet);
//	  lsend_packet_city_short_info(&pplayer.connections, &packet);
//	}
//      }
//    }
//  }
//
//  /* Don't send to non-player observers since they don't have 'dumb city'
//   * information. */
//}
//
///**************************************************************************
//  Broadcast info about a city to all players who observe the tile. 
//  If the player can see the city we update the city info first.
//  If not we just use the info from the players private Map.map.
//  See also comments to send_city_info_at_tile().
//  (Split off from send_city_info_at_tile() because that was getting
//  too difficult for me to understand... --dwp)
//**************************************************************************/
//static void broadcast_city_info(city pcity)
//{
//  player powner = City.city_owner(pcity);
//  struct packet_city_info packet;
//  struct packet_city_short_info sc_pack;
//
//  /* Send to everyone who can see the city. */
//  for(player pplayer: Game.game.players){
//    if (can_player_see_city_internals(pplayer, pcity)) {
//      if (!nocity_send || pplayer != City.city_owner(pcity)) {
//	update_dumb_city(powner, pcity);
//	package_city(pcity, &packet, false);
//	lsend_packet_city_info(&powner.connections, &packet);
//      }
//    } else {
//      if (Maphand.map_is_known_and_seen(pcity.tile, pplayer)
//	  || player_has_traderoute_with_city(pplayer, pcity)) {
//	update_dumb_city(pplayer, pcity);
//	package_dumb_city(pplayer, pcity.tile, &sc_pack);
//	lsend_packet_city_short_info(&pplayer.connections, &sc_pack);
//      }
//    }
//  }
//
//  /* send to non-player observers:
//   * should these only get dumb_city type info?
//   */
//  for (conn pconn : Game.game.game_connections.data) {
//    if (!pconn.player && pconn.observer) {
//      package_city(pcity, &packet, false);
//      send_packet_city_info(pconn, &packet);
//    }
//  }
//  }
//}
//
///**************************************************************************
//  Send to each client information about the cities it knows about.
//  dest may not be null
//**************************************************************************/
//void send_all_known_cities(Speclists<Connection> dest)
//{
//  Connection.conn_list_do_buffer(dest);
//  conn_list_iterate(*dest, pconn) {
//    player pplayer = pconn.player;
//    if (!pplayer && !pconn.observer) {
//      continue;
//    }
//    for(tile ptile :  Map.map.tiles){
//      if (!pplayer || map_get_player_tile(ptile, pplayer).city) {
//	send_city_info_at_tile(pplayer, &pconn.self, null, ptile);
//      }
//    }
//  }
//  }
//  Connection.conn_list_do_unbuffer(dest);
//  flush_packets();
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void send_player_cities(player pplayer)
//{
//  for (city pcity : pplayer.cities.data) {
//    Cityturn.city_refresh(pcity);
//    send_city_info(pplayer, pcity);
//  }
//  }
//}


/**************************************************************************
  A wrapper, accessing either broadcast_city_info() (dest==null),
  or a convenience case of send_city_info_at_tile().
  Must specify non-null pcity.
**************************************************************************/
public static void send_city_info(player dest, city pcity)
{
  assert(pcity != null);

//  if (Srv_main.server_state != RUN_GAME_STATE && Srv_main.server_state != server_states.GAME_OVER_STATE)
//    return;
//
//  if (dest == City.city_owner(pcity) && nocity_send)
//    return;
//
//  if (dest==null || dest == City.city_owner(pcity))
//    pcity.synced = true;
//  if (dest==null) {
//    broadcast_city_info(pcity);
//  } else {
//    send_city_info_at_tile(dest, &dest.connections, pcity, pcity.tile);
//  }
}

///**************************************************************************
//Send info about a city, as seen by pviewer, to dest (usually dest will
//be pviewer.connections). If pplayer can see the city we update the city
//info first. If not we just use the info from the players private Map.map.
//
//If (pviewer == null) this is for observers, who see everything (?)
//For this function dest may not be null.  See send_city_info() and
//broadcast_city_info().
//
//If pcity is non-null it should be same as map_get_city(x,y); if pcity
//is null, this function calls map_get_city(x,y) (it is ok if this
//returns null).
//
//Sometimes a player's map contain a city that doesn't actually exist. Use
//reality_check_city(pplayer, ptile) to update that. Remember to NOT send info
//about a city to a player who thinks the tile contains another city. If you
//want to update the clients info of the tile you must use
//reality_check_city(pplayer, ptile) first. This is generally taken care of
//automatically when a tile becomes visible.
//**************************************************************************/
//void send_city_info_at_tile(player pviewer, Speclists<Connection> dest,
//			    city pcity, tile ptile)
//{
//  player powner = null;
//  struct packet_city_info packet;
//  struct packet_city_short_info sc_pack;
//  dumb_city pdcity;
//
//  if (!pcity)
//    pcity = map_get_city(ptile);
//  if (pcity)
//    powner = City.city_owner(pcity);
//
//  if (powner && powner == pviewer) {
//    /* send info to owner */
//    /* This case implies powner non-null which means pcity non-null */
//    /* nocity_send is used to inhibit sending cities to the owner between
//       turn updates */
//    if (!nocity_send) {
//      /* send all info to the owner */
//      update_dumb_city(powner, pcity);
//      package_city(pcity, &packet, false);
//      lsend_packet_city_info(dest, &packet);
//    }
//  }
//  else {
//    /* send info to non-owner */
//    if (!pviewer) {	/* observer */
//      if (pcity) {
//	package_city(pcity, &packet, false);   /* should be dumb_city info? */
//	lsend_packet_city_info(dest, &packet);
//      }
//    } else {
//      if (!Maphand.map_is_known(ptile, pviewer)) {
//	Maphand.show_area(pviewer, ptile, 0);
//      }
//      if (Maphand.map_is_known_and_seen(ptile, pviewer)) {
//	if (pcity) {		/* it's there and we see it; update and send */
//	  update_dumb_city(pviewer, pcity);
//	  package_dumb_city(pviewer, ptile, &sc_pack);
//	  lsend_packet_city_short_info(dest, &sc_pack);
//	}
//      } else {			/* not seen; send old info */
//	pdcity = map_get_player_tile(ptile, pviewer).city;
//	if (pdcity) {
//	  package_dumb_city(pviewer, ptile, &sc_pack);
//	  lsend_packet_city_short_info(dest, &sc_pack);
//	}
//      }
//    }
//  }
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void package_city(city pcity, packet_city_info packet,
//		  boolean dipl_invest)
//{
//  int x, y, i;
//  String p;
//  packet.id=pcity.id;
//  packet.owner=pcity.owner;
//  packet.x = pcity.tile.x;
//  packet.y = pcity.tile.y;
//  packet.name = pcity.name;
//
//  packet.size=pcity.size;
//  for (i=0;i<5;i++) {
//    packet.ppl_happy[i]=pcity.ppl_happy[i];
//    packet.ppl_content[i]=pcity.ppl_content[i];
//    packet.ppl_unhappy[i]=pcity.ppl_unhappy[i];
//    packet.ppl_angry[i]=pcity.ppl_angry[i];
//  }
//  packet.specialists[specialist_type.SP_ELVIS] = pcity.specialists[specialist_type.SP_ELVIS];
//  packet.specialists[SP_SCIENTIST] = pcity.specialists[SP_SCIENTIST];
//  packet.specialists[SP_TAXMAN] = pcity.specialists[SP_TAXMAN];
//  for (i = 0; i < NUM_TRADEROUTES; i++) {
//    packet.trade[i]=pcity.trade[i];
//    packet.trade_value[i]=pcity.trade_value[i];
//  }
//
//  packet.food_prod=pcity.food_prod;
//  packet.food_surplus=pcity.food_surplus;
//  packet.shield_prod=pcity.shield_prod;
//  packet.shield_surplus=pcity.shield_surplus;
//  packet.trade_prod=pcity.trade_prod;
//  packet.tile_trade=pcity.tile_trade;
//  packet.corruption=pcity.corruption;
//  
//  packet.shield_waste=pcity.shield_waste;
//    
//  packet.luxury_total=pcity.luxury_total;
//  packet.tax_total=pcity.tax_total;
//  packet.science_total=pcity.science_total;
//  
//  packet.food_stock=pcity.food_stock;
//  packet.shield_stock=pcity.shield_stock;
//  packet.pollution=pcity.pollution;
//
//  packet.city_options=pcity.city_options;
//  
//  packet.is_building_unit=pcity.is_building_unit;
//  packet.currently_building=pcity.currently_building;
//
//  packet.turn_last_built=pcity.turn_last_built;
//  packet.turn_founded = pcity.turn_founded;
//  packet.changed_from_id=pcity.changed_from_id;
//  packet.changed_from_is_unit=pcity.changed_from_is_unit;
//  packet.before_change_shields=pcity.before_change_shields;
//  packet.disbanded_shields=pcity.disbanded_shields;
//  packet.caravan_shields=pcity.caravan_shields;
//  packet.last_turns_shield_surplus = pcity.last_turns_shield_surplus;
//
//  worklist.copy_worklist(&packet.worklist, &pcity.worklist);
//  packet.diplomat_investigate=dipl_invest;
//
//  packet.airlift = pcity.airlift;
//  packet.did_buy = pcity.did_buy;
//  packet.did_sell = pcity.did_sell;
//  packet.was_happy = pcity.was_happy;
//  for (y = 0; y < City_H.CITY_MAP_SIZE; y++) {
//    for (x = 0; x < City_H.CITY_MAP_SIZE; x++) {
//      packet.city_map[x + y * City_H.CITY_MAP_SIZE] = City.get_worker_city(pcity, x, y);
//    }
//  }
//
//  p = packet.improvements;
//
//  impr_type_iterate(i) {
//    *p++ = (city_got_building(pcity, i)) ? '1' : '0';
//  } impr_type_iterate_end;
//
//  *p = '\0';
//}
//
///**************************************************************************
//updates a players knowledge about a city. If the player_tile already
//contains a city it must be the same city (avoid problems by always calling
//reality_check_city() first)
//
//Returns true iff anything has changed for the player city (i.e., if the
//client needs to be updated with a *short* city packet).  This information
//is only used in refresh_dumb_cities; elsewhere the data is (of necessity)
//broadcast regardless.
//**************************************************************************/
//boolean update_dumb_city(player pplayer, city pcity)
//{
//  player_tile plrtile = map_get_player_tile(pcity.tile,
//						    pplayer);
//  dumb_city pdcity = plrtile.city;
//  /* pcity.occupied isn't used at the server, so we go straight to the
//   * unit list to check the occupied status. */
//  boolean occupied =
//    (pcity.tile.units.foo_list_size() > 0);
//  boolean happy = city_happy(pcity), unhappy = city_unhappy(pcity);
// 
//  if (pdcity
//      && pdcity.id == pcity.id
//      && pdcity.name.equals(pcity.name)
//      && pdcity.size == pcity.size
//      && pdcity.has_walls == city_got_citywalls(pcity)
//      && pdcity.occupied == occupied
//      && pdcity.happy == happy
//      && pdcity.unhappy == unhappy
//      && pdcity.owner == pcity.owner) {
//    return false;
//  }
//
//  if (!plrtile.city) {
//    pdcity = plrtile.city = fc_malloc(sizeof(struct dumb_city));
//    plrtile.city.id = pcity.id;
//  }
//  if (pdcity.id != pcity.id) {
//    util.freelog(Log.LOG_ERROR, "Trying to update old city (wrong ID)" +
//	    " at %i,%i for player %s",
//	    pcity.tile.x, pcity.tile.y, pplayer.name);
//    pdcity.id = pcity.id;   /* ?? */
//  }
//  pdcity.name = pcity.name;
//  pdcity.size = pcity.size;
//  pdcity.has_walls = city_got_citywalls(pcity);
//  pdcity.occupied = occupied;
//  pdcity.happy = happy;
//  pdcity.unhappy = unhappy;
//  pdcity.owner = pcity.owner;
//
//  return true;
//}
//
///**************************************************************************
//Removes outdated (nonexistant) cities from a player
//**************************************************************************/
//void reality_check_city(player pplayer,tile ptile)
//{
//  city pcity;
//  dumb_city pdcity = map_get_player_tile(ptile, pplayer).city;
//
//  if (pdcity) {
//    pcity = ptile.city;
//    if (!pcity || (pcity && pcity.id != pdcity.id)) {
//      dlsend_packet_city_remove(&pplayer.connections, pdcity.id);
//      free(pdcity);
//      map_get_player_tile(ptile, pplayer).city = null;
//    }
//  }
//}
//
///**************************************************************************
//  Remove the trade route between pc1 and pc2 (if one exists).
//**************************************************************************/
//void remove_trade_route(city pc1, city pc2)
//{
//  int i;
//
//  assert(pc1 && pc2);
//
//  for (i = 0; i < NUM_TRADEROUTES; i++) {
//    if (pc1.trade[i] == pc2.id)
//      pc1.trade[i] = 0;
//    if (pc2.trade[i] == pc1.id)
//      pc2.trade[i] = 0;
//  }
//}
//
///**************************************************************************
//  Remove/cancel the city's least valuable trade route.
//**************************************************************************/
//static void remove_smallest_trade_route(city pcity)
//{
//  int slot;
//
//  if (get_city_min_trade_route(pcity, &slot) <= 0) {
//    return;
//  }
//
//  remove_trade_route(pcity, Game.find_city_by_id(pcity.trade[slot]));
//}
//
///**************************************************************************
//  Establish a trade route.  Notice that there has to be space for them, 
//  so you should check can_establish_trade_route first.
//**************************************************************************/
//void establish_trade_route(city pc1, city pc2)
//{
//  int i;
//
//  if (city_num_trade_routes(pc1) == NUM_TRADEROUTES) {
//    remove_smallest_trade_route(pc1);
//  }
//
//  if (city_num_trade_routes(pc2) == NUM_TRADEROUTES) {
//    remove_smallest_trade_route(pc2);
//  }
//
//  for (i = 0; i < NUM_TRADEROUTES; i++) {
//    if (pc1.trade[i] == 0) {
//      pc1.trade[i]=pc2.id;
//      break;
//    }
//  }
//  for (i = 0; i < NUM_TRADEROUTES; i++) {
//    if (pc2.trade[i] == 0) {
//      pc2.trade[i]=pc1.id;
//      break;
//    }
//  }
//}

/****************************************************************************
  Sell the improvement from the city, and give the player the owner.  Not
  all buildings can be sold.

  I guess the player should always be the city owner?
****************************************************************************/
public static void do_sell_building(player pplayer, city pcity,
		      int id)
{
//  if (!is_wonder(id)) {
//    pplayer.economic.gold += Improvement.impr_sell_gold(id);
//    building_lost(pcity, id);
//  }
}

/****************************************************************************
  Destroy the improvement in the city straight-out.  Note that this is
  different from selling a building.
****************************************************************************/
//void building_lost(city pcity, int id)
//{
//  player owner = City.city_owner(pcity);
//  boolean was_capital = pcity.is_capital();
//
//  city_remove_improvement(pcity,id);
//  if ((was_capital && !pcity.is_capital())
//      && (owner.spaceship.state == spaceship_state.SSHIP_STARTED
//	  || owner.spaceship.state == spaceship_state.SSHIP_LAUNCHED)) {
//    /* If the capital was lost (by destruction of the palace) production on
//     * the spaceship is lost. */
//    spaceship_lost(owner);
//  }
//}

/**************************************************************************
  Change the build target.
**************************************************************************/
public static void change_build_target(player pplayer, city pcity,
			 int target, boolean is_unit, event_type event)
{
//  final String name;
//  final String source;
//
//  /* If the city is already building this thing, don't do anything */
//  if (pcity.is_building_unit == is_unit &&
//      pcity.currently_building == target) {
//    return;
//  }
//
//  /* Is the city no longer building a wonder? */
//  if(!pcity.is_building_unit && is_wonder(pcity.currently_building) &&
//     (event != E_IMP_AUTO && event != E_WORKLIST)) {
//    /* If the build target is changed because of an advisor's suggestion or
//       because the worklist advances, then the wonder was completed -- 
//       don't announce that the player has *stopped* building that wonder. 
//       */
//    Plrhand.notify_player_ex(null, pcity.tile, E_WONDER_STOPPED,
//		     "Game: The %s have stopped building The %s in %s.",
//		     Nation.get_nation_name_plural(pplayer.nation),
//		     get_impr_name_ex(pcity, pcity.currently_building),
//		     pcity.name);
//  }
//
//  /* Manage the city change-production penalty.
//     (May penalize, restore or do nothing to the shield_stock.) */
//  pcity.shield_stock = city_change_production_penalty(pcity, target, is_unit);
//
//  /* Change build target. */
//  pcity.currently_building = target;
//  pcity.is_building_unit = is_unit;
//
//  /* What's the name of the target? */
//  if (is_unit)
//    name = Unittype_P.unit_types[pcity.currently_building].name;
//  else
//    name = improvement_types[pcity.currently_building].name;
//
//  switch (event) {
//    case E_WORKLIST: source = " from the worklist"; break;
///* Should we give the AI auto code credit?
//    case E_IMP_AUTO: source = " as suggested by the AI advisor"; break;
//*/
//    default: source = ""; break;
//  }
//
//  /* Tell the player what's up. */
//  /* FIXME: this may give bad grammar when translated if the 'source'
//   * string can have multiple values. */
//  if (event != event_type.E_NOEVENT) {
//    Plrhand.notify_player_ex(pplayer, pcity.tile, event,
//		     /* TRANS: "<city> is building <production><source>." */
//		     "Game: %s is building %s%s.",
//		     pcity.name, name, source);
//  } else {
//    Plrhand.notify_player_ex(pplayer, pcity.tile, E_CITY_PRODUCTION_CHANGED,
//		     /* TRANS: "<city> is building <production>." */
//		     "Game: %s is building %s.", 
//		     pcity.name, name);
//  }
//
//  /* If the city is building a wonder, tell the rest of the world
//     about it. */
//  if (!pcity.is_building_unit && is_wonder(pcity.currently_building)) {
//    Plrhand.notify_player_ex(null, pcity.tile, E_WONDER_STARTED,
//		     "Game: The %s have started building The %s in %s.",
//		     Nation.get_nation_name_plural(pplayer.nation),
//		     get_impr_name_ex(pcity, pcity.currently_building),
//		     pcity.name);
//  }
}

///**************************************************************************
//...
//**************************************************************************/
//boolean can_place_worker_here(city pcity, int city_x, int city_y)
//{
//  return City.get_worker_city(pcity, city_x, city_y) == city_tile_type.C_TILE_EMPTY;
//}
//
///**************************************************************************
//Returns if a tile is available to be worked.
//Return true if the city itself is currently working the tile (and can
//continue.)
//city_x, city_y is in city map coords.
//**************************************************************************/
//boolean city_can_work_tile(city pcity, int city_x, int city_y)
//{
//  tile ptile;
//
//  if (!(ptile = city_map_to_map(pcity, city_x, city_y))) {
//    return false;
//  }
//  
//  if (is_enemy_unit_tile(ptile, City.city_owner(pcity))
//      && !City.is_city_center(city_x, city_y)) {
//    return false;
//  }
//
//  if (!Maphand.map_is_known(ptile, City.city_owner(pcity))) {
//    return false;
//  }
//
//  if (ptile.worked && ptile.worked != pcity) {
//    return false;
//  }
//
//  if (ptile.owner && ptile.owner.player_no != pcity.owner) {
//    return false;
//  }
//
//  return true;
//}
//
///**************************************************************************
//Sets tile worked status.
//city_x, city_y is in city map coords.
//You need to call sync_cities for the affected cities to be synced with the
//client.
//
//You should not use this function unless you really know what you are doing!
//Better functions are
//server_set_worker_city()
//server_remove_worker_city()
//update_city_tile_status()
//**************************************************************************/
//static void server_set_tile_city(city pcity, int city_x, int city_y,
//				 enum city_tile_type type)
//{
//  enum city_tile_type current;
//  assert(City.is_valid_city_coords(city_x, city_y));
//  current = pcity.city_map[city_x][city_y];
//  assert(current != type);
//
//  set_worker_city(pcity, city_x, city_y, type);
//  pcity.synced = false;
//
//  /* Update adjacent cities. */
//  {
//    tile ptile = city_map_to_map(pcity, city_x, city_y);
//
//    map_city_radius_iterate(ptile, tile1) {
//      city pcity2 = map_get_city(tile1);
//      if (pcity2 && pcity2 != pcity) {
//	int city_x2, city_y2;
//	boolean is_valid;
//
//	is_valid = map_to_city_map(&city_x2, &city_y2, pcity2, ptile);
//	assert(is_valid);
//	update_city_tile_status(pcity2, city_x2, city_y2);
//      }
//    } map_city_radius_iterate_end;
//  }
//}
//
///**************************************************************************
//city_x, city_y is in city map coords.
//You need to call sync_cities for the affected cities to be synced with the
//client.
//**************************************************************************/
public static void server_remove_worker_city(city pcity, int city_x, int city_y)
{
//  assert(City.is_valid_city_coords(city_x, city_y));
//  assert(City.get_worker_city(pcity, city_x, city_y) == city_tile_type.C_TILE_WORKER);
//  server_set_tile_city(pcity, city_x, city_y, city_tile_type.C_TILE_EMPTY);
}
//
///**************************************************************************
//city_x, city_y is in city map coords.
//You need to call sync_cities for the affected cities to be synced with the
//client.
//**************************************************************************/
public static void server_set_worker_city(city pcity, int city_x, int city_y)
{
//  assert(City.is_valid_city_coords(city_x, city_y));
//  assert(City.get_worker_city(pcity, city_x, city_y) == city_tile_type.C_TILE_EMPTY);
//  server_set_tile_city(pcity, city_x, city_y, city_tile_type.C_TILE_WORKER);
}
//
///****************************************************************************
//  Updates the worked status of the tile (in map coordinates) for the city.
//  If the status changes Cityturn.auto_arrange_workers may be called.  The caller needs
//  to call sync_cities afterward for the affected city to be synced with the
//  client.
//
//  It is safe to pass an out-of-range tile to this function.  The function
//  returns true if the tile is made unavailable.
//****************************************************************************/
//boolean update_city_tile_status_map(city pcity, tile ptile)
//{
//  int city_x, city_y;
//
//  if (map_to_city_map(&city_x, &city_y, pcity, ptile)) {
//    return update_city_tile_status(pcity, city_x, city_y);
//  } else {
//    return false;
//  }
//}
//
///**************************************************************************
//Updates the worked status of a tile.
//city_x, city_y is in city map coords.
//You need to call sync_cities for the affected cities to be synced with the
//client.
//
//Returns true iff a tile got available.
//**************************************************************************/
//static boolean update_city_tile_status(city pcity, int city_x,
//				    int city_y)
//{
//  enum city_tile_type current;
//  boolean is_available;
//  boolean result = false;
//
//  assert(City.is_valid_city_coords(city_x, city_y));
//
//  current = City.get_worker_city(pcity, city_x, city_y);
//  is_available = city_can_work_tile(pcity, city_x, city_y);
//
//  switch (current) {
//  case city_tile_type.C_TILE_WORKER:
//    if (!is_available) {
//      server_set_tile_city(pcity, city_x, city_y, city_tile_type.C_TILE_UNAVAILABLE);
//      pcity.specialists[specialist_type.SP_ELVIS]++; /* keep city sanity */
//      Cityturn.auto_arrange_workers(pcity); /* will place the displaced */
//      Cityturn.city_refresh(pcity);
//      send_city_info(null, pcity);
//    }
//    break;
//
//  case city_tile_type.C_TILE_UNAVAILABLE:
//    if (is_available) {
//      server_set_tile_city(pcity, city_x, city_y, city_tile_type.C_TILE_EMPTY);
//      result = true;
//    }
//    break;
//
//  case city_tile_type.C_TILE_EMPTY:
//    if (!is_available) {
//      server_set_tile_city(pcity, city_x, city_y, city_tile_type.C_TILE_UNAVAILABLE);
//    }
//    break;
//  }
//
//  return result;
//}

	/**************************************************************************
	 * ...
	 **************************************************************************/
	public static void sync_cities() {
		// if (nocity_send)
		// return;

		for (player pplayer : Game.game.players) {
			for (city pcity : pplayer.cities.data) {
				/* sending will set synced to 1. */
				if (!pcity.synced)
					send_city_info(pplayer, pcity);
			}
		}
	}

///**************************************************************************
//...
//**************************************************************************/
//void check_city_workers(player pplayer)
//{
//  for (city pcity : pplayer.cities.data) {
//    city_map_iterate(x, y) {
//      update_city_tile_status(pcity, x, y);
//    } city_map_iterate_end;
//  } }
//  sync_cities();
//}
//
///**************************************************************************
//  For each city adjacent to (x,y), check if landlocked.  If so, sell all
//  improvements in the city that depend upon being next to an ocean tile.
//  (Should be called after any ocean to land terrain changes.
//  Assumes no city at (x,y).)
//
//  N.B. Now uses info from buildings.ruleset to decide which buildings
//  to sell. In theory this could (should?) be generalised to sell
//  relevant buildings after any change of terrain/special type 
//**************************************************************************/
//void city_landlocked_sell_coastal_improvements(tile ptile)
//{
//  for(tile tile1: util.adjc_tile_iterate(ptile)) {
//    city pcity = map_get_city(tile1);
//
//    if (pcity && !is_ocean_near_tile(tile1)) {
//      player pplayer = City.city_owner(pcity);
//
//      /* Sell all buildings (but not Wonders) that must be next to the ocean */
//      built_impr_iterate(pcity, impr) {
//        int i = 0;
//
//        if (is_wonder(impr)) {
//          continue;
//        }
//
//        while (!Terrain_H.is_ocean(improvement_types[impr].terr_gate[i])
//               && improvement_types[impr].terr_gate[i] != T_NONE) {
//          i++;
//        }
//
//        if (Terrain_H.is_ocean(improvement_types[impr].terr_gate[i])
//            && !city_has_terr_spec_gate(pcity, impr)) {
//          do_sell_building(pplayer, pcity, impr);
//          Plrhand.notify_player_ex(pplayer, tile1, event_type.E_IMP_SOLD,
//                           ("Game: You sell %s in %s (now landlocked)" +
//                             " for %d gold."),
//                           Improvement.get_improvement_name(impr), pcity.name,
//                           Improvement.impr_sell_gold(impr)); 
//        }
//      } built_impr_iterate_end;
//    }
//  }
//}
}