package ai;

public class Aiferry{

// Freeciv - Copyright (C) 2004 - The Freeciv Project
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
//#include "log.h"
//#include "unit.h"
//
//#include "path_finding.h"
//#include "pf_tools.h"
//
//#include "hand_gen.h"
//#include "unithand.h"
//#include "unittools.h"
//
//#include "aidata.h"
//#include "aiexplorer.h"
//#include "ailog.h"
//#include "aitools.h"
//#include "aiunit.h"
//
//#include "aiferry.h"
//
// 
///* =================== finalants with special meaning =================== */
//
///* 
// * This one is used only by ferryboats in ai.passenger field 
// */
//public static final int FERRY_AVAILABLE = -1;      /* Boat is looking for a passenger */
///* 
// * The below is used only by passengers in ai.ferryboat field 
// */ 
//public static final int FERRY_WANTED = -1;      /* Needs a boat */
//public static final int FERRY_NONE = 0;      /* Has no boa and doesn't need one */
//
//
///* =================== group log levels, debug options ================= */
//
///* Logging in ferry management functions */
//#define LOGLEVEL_FERRY Log.LOG_DEBUG
///* Logging in go_by_boat functions */
//#define LOGLEVEL_GOBYBOAT Log.LOG_DEBUG
///* Logging in find_ferry functions */
//#define LOGLEVEL_FINDFERRY Log.LOG_DEBUG
///* Extra consistency checks */
//public static final int DEBUG_FERRY_STATS = 0;
//
//
///* ========= managing statistics and boat/passanger assignments ======== */
//
///**************************************************************************
//  Call to initialize the ferryboat statistics
//**************************************************************************/
//void aiferry_init_stats(player pplayer)
//{
//  ai_data ai = ai_data_get(pplayer);
//
//  ai.stats.passengers = 0;
//  ai.stats.boats = 0;
//  ai.stats.available_boats = 0;
// 
//  for (unit punit : pplayer.units.data) {
//    if (Unit.is_sailing_unit(punit) && is_ground_units_transport(punit)) {
//      ai.stats.boats++;
//      if (punit.ai.passenger == FERRY_AVAILABLE) {
//	ai.stats.available_boats++;
//      }
//    }
//    if (punit.ai.ferryboat == FERRY_WANTED) {
//      UNIT_LOG(Log.LOG_DEBUG, punit, "wants a boat.");
//      ai.stats.passengers++;
//    }  
//  } }
//}
//
///**************************************************************************
//  Print the list of boats of pplayer.
//**************************************************************************/
//#if DEBUG_FERRY_STATS
//static void aiferry_print_stats(player pplayer)
//{
//  ai_data ai = ai_data_get(pplayer);
//  int n = 1;
//
//  util.freelog(Log.LOG_NORMAL, "Boat stats for %s[%d]", 
//	  pplayer.name, pplayer.player_no);
//  util.freelog(Log.LOG_NORMAL, "Registered: %d free out of total %d",
//	  ai.stats.available_boats, ai.stats.boats);
//  for (unit punit : pplayer.units.data) {
//    if (Unit.is_sailing_unit(punit) && is_ground_units_transport(punit)) {
//      util.freelog(Log.LOG_NORMAL, "#%d. %s[%d], psngr=%d", 
//	      n, punit.unit_type().name, punit.id, punit.ai.passenger);
//      n++;
//    }
//  } }
//}
//#endif /* DEBUG_FERRY_STATS */
//
///**************************************************************************
//  Use on a unit which no longer needs a boat. 
//**************************************************************************/
//void aiferry_clear_boat(unit punit)
//{
//  if (punit.ai.ferryboat == FERRY_WANTED) {
//    ai_data ai = ai_data_get(punit.unit_owner());
//
//    ai.stats.passengers--;
//  } else {
//    unit ferry = Game.find_unit_by_id(punit.ai.ferryboat);
//    
//    if (ferry && ferry.ai.passenger == punit.id) {
//      /* punit doesn't want us anymore */
//      ai_data_get(ferry.unit_owner()).stats.available_boats++;
//      ferry.ai.passenger = FERRY_AVAILABLE;
//    }
//  }
//
//  punit.ai.ferryboat = FERRY_NONE;
//}
//
///**************************************************************************
//  Request a boat for the unit.  Should only be used if the unit is on the
//  coast, otherwise ferries will not see it.
//**************************************************************************/
//static void aiferry_request_boat(unit punit)
//{
//    ai_data ai = ai_data_get(punit.unit_owner());
//
//    /* First clear the previous assignments (just in case) */
//    aiferry_clear_boat(punit);
//    /* Now add ourselves to the list of potential passengers */
//    UNIT_LOG(Log.LOG_DEBUG, punit, "requests a boat.");
//    ai.stats.passengers++;
//    punit.ai.ferryboat = FERRY_WANTED;
//
//}
//
///**************************************************************************
//  Assign the passenger to the boat and vice versa.
//**************************************************************************/
//static void aiferry_psngr_meet_boat(unit punit, unit pferry)
//{
//  /* First delete the unit from the list of passengers and 
//   * release its previous ferry */
//  aiferry_clear_boat(punit);
//
//  /* If ferry was available, update the stats */
//  if (pferry.ai.passenger == FERRY_AVAILABLE) {
//    ai_data_get(pferry.unit_owner()).stats.available_boats--;
//  }
//
//  /* Exchange the phone numbers */
//  punit.ai.ferryboat = pferry.id;
//  pferry.ai.passenger = punit.id;
//}
//
///**************************************************************************
//  Mark the ferry as available and update the statistics.
//**************************************************************************/
//static void aiferry_make_available(unit pferry)
//{
//  if (pferry.ai.passenger != FERRY_AVAILABLE) {
//    ai_data_get(pferry.unit_owner()).stats.available_boats++;
//    pferry.ai.passenger = FERRY_AVAILABLE;
//  }
//}
//
///**************************************************************************
//  Returns the number of available boats.  A simple accessor made to perform 
//  debug checks.
//**************************************************************************/
//static int aiferry_avail_boats(player pplayer)
//{
//  ai_data ai = ai_data_get(pplayer);
//
//  /* To developer: Switch this checking on when testing some new 
//   * ferry code. */
//  /* There is one "legitimate" reason for failing this check: a ferry got
//   * killed but wasn't taken off the register */
//#if DEBUG_FERRY_STATS
//  int boats = 0;
//
//  for (unit punit : pplayer.units.data) {
//    if (Unit.is_sailing_unit(punit) && is_ground_units_transport(punit) 
//	&& punit.ai.passenger == FERRY_AVAILABLE) {
//      boats++;
//    }
//  } }
//
//  if (boats != ai.stats.available_boats) {
//    util.freelog(Log.LOG_ERROR, "Player[%d] in turn %d: boats miscounted.",
//	    pplayer.player_no, Game.game.turn);
//    aiferry_print_stats(pplayer);
//  }
//#endif /* DEBUG_FERRY_STATS */
//
//  return ai.stats.available_boats;
//}
//
//
///* ================== functions to find a boat ========================= */
//
///**************************************************************************
//  Combined cost function for a land unit looking for a ferry.  The path 
//  finding first goes over the continent and then into the ocean where we 
//  actually look for ferry.  Thus moves land-to-sea are allowed and moves
//  sea-to-land are not.  A consequence is that we don't get into the cities
//  on other continent, which might station boats.  This defficiency seems to
//  be impossible to fix with the current PF structure, so it has to be
//  accounted for in the actual ferry search function.
//
//  For movements sea-to-sea the cost is collected via the extra cost 
//  call-back.  Doesn't care for enemy/neutral tiles, these should be 
//  excluded using a TB call-back.
//**************************************************************************/
//static int combined_land_sea_move(final tile src_tile,
//				  enum direction8 dir,
//				  final tile tgt_tile,
//                                  pf_parameter param)
//{
//  int move_cost;
//
//  if (Terrain_H.is_ocean(tgt_tile.terrain)) {
//    /* Any-to-Sea */
//    move_cost = 0;
//  } else if (Terrain_H.is_ocean(src_tile.terrain)) {
//    /* Sea-to-Land */
//    move_cost = PF_IMPOSSIBLE_MC;
//  } else {
//    /* Land-to-Land */
//    move_cost = src_tile.move_cost[dir];
//  }
//
//  return move_cost;
//}
//
///****************************************************************************
//  EC callback to account for the cost of sea moves by a ferry hurrying to 
//  pick our unit up.
//****************************************************************************/
//static int sea_move(final tile ptile, enum known_type known,
//                    pf_parameter param)
//{
//  if (Terrain_H.is_ocean(ptile.terrain)) {
//    /* Approximately TURN_FACTOR / average ferry move rate 
//     * we can pass a better guess of the move rate through param.data
//     * but we don't know which boat we will find out there */
//    return Unit_H.SINGLE_MOVE * PF_TURN_FACTOR / 12;
//  } else {
//    return 0;
//  }
//}
//
///****************************************************************************
//  Runs a few checks to determine if "boat" is a free boat that can carry
//  "cap" units of the same type as "punit" (the last one isn't implemented).
//****************************************************************************/
//static boolean is_boat_free(unit boat, unit punit, int cap)
//{
//  /* - Only ground-unit transporters are consider.
//   * - Units with orders are skipped (the AI doesn't control units with
//   *   orders).
//   * - Only boats that we own are eligible.
//   * - Only available boats or boats that are already dedicated to this unit
//   *   are eligible.
//   * - Only boats with enough remaining capacity are eligible.
//   */
//  return (is_ground_units_transport(boat)
//	  && !unit_has_orders(boat)
//	  && boat.owner == punit.owner
//	  && (boat.ai.passenger == FERRY_AVAILABLE
//	      || boat.ai.passenger == punit.id)
//	  && (Unit.get_transporter_capacity(boat) 
//	      - get_transporter_occupancy(boat) >= cap));
//}
//
///****************************************************************************
//  Proper and real PF function for finding a boat.  If you don't require
//  the path to the ferry, pass path=null.
//
//  WARNING: Due to the nature of this function and PF (see the comment of 
//  combined_land_sea_move), the path won't lead onto the boat itself.
//
//  FIXME: Actually check the capacity.
//****************************************************************************/
//int aiferry_find_boat(unit punit, int cap, pf_path *path)
//{
//  int best_turns = FC_INFINITY;
//  int best_id = 0;
//  struct pf_parameter param;
//  pf_map search_map;
//
//
//  UNIT_LOG(LOGLEVEL_FINDFERRY, punit, "asked find_ferry for a boat");
//
//  if (aiferry_avail_boats(punit.unit_owner()) <= 0 
//      && punit.ai.ferryboat <= 0) {
//    /* No boats to be found (the second check is to ensure that we are not 
//     * the ones keeping the last boat busy) */
//    return 0;
//  }
//
//  pft_fill_unit_parameter(&param, punit);
//  param.turn_mode = TM_WORST_TIME;
//  param.get_TB = no_fights_or_unknown;
//  param.get_EC = sea_move;
//  param.get_MC = combined_land_sea_move;
//
//  search_map = pf_create_map(&param);
//
//  pf_iterator(search_map, pos) {
//    int radius = (Terrain_H.is_ocean(pos.tile.terrain) ? 1 : 0);
//
//    if (pos.turn + pos.total_EC/PF_TURN_FACTOR > best_turns) {
//      /* Won't find anything better */
//      /* FIXME: This condition is somewhat dodgy */
//      break;
//    }
//    
//    for(tile ptile: util.square_tile_iterate(pos.tile, radius)) {
//      for (unit aunit : ptile.units.data) {
//        if (is_boat_free(aunit, punit, cap)) {
//          /* Turns for the unit to get to rendezvous pnt */
//          int u_turns = pos.turn;
//          /* Turns for the boat to get to the rendezvous pnt */
//          int f_turns = ((pos.total_EC / PF_TURN_FACTOR * 16 
//                          - aunit.moves_left) 
//                         / aunit.unit_type().move_rate);
//          int turns = MAX(u_turns, f_turns);
//          
//          if (turns < best_turns) {
//            UNIT_LOG(LOGLEVEL_FINDFERRY, punit, 
//                     "Found a potential boat %s[%d](%d,%d)",
//                     aunit.unit_type().name, aunit.id, aunit.tile.x,
//		     aunit.tile.y);
//	    if (path) {
//	      *path = pf_next_get_path(search_map);
//	    }
//            best_turns = turns;
//            best_id = aunit.id;
//          }
//        }
//      } }
//    }
//  } pf_iterator_end;
//  pf_destroy_map(search_map);
//
//  return best_id;
//}
//
///****************************************************************************
//  Find a boat within one move from us (i.e. a one we can board).
//
//  FIXME: Actually check the capacity.
//****************************************************************************/
//static int aiferry_find_boat_nearby(unit punit, int cap)
//{
//  UNIT_LOG(LOGLEVEL_FINDFERRY, punit, "asked find_ferry_nearby for a boat");
//
//  for(tile ptile: util.square_tile_iterate(punit.tile, 1)) {
//    for (unit aunit : ptile.units.data) {
//      if (is_boat_free(aunit, punit, cap)) {
//        return aunit.id;
//      }
//    } }
//  }
//
//  return 0;
//}
//
//
///* ============================= go by boat ============================== */
//
///****************************************************************************
//  This function is to be called if punit needs to use a boat to get to the 
//  destination.
//
//  Return values: true if got to or next to our destination, false otherwise. 
//
//  TODO: A big one is rendezvous points between units and boats.  When this is 
//  implemented, we won't have to be at the coast to ask for a boat to come 
//  to us.
//
//  You MUST have warmap created before calling this function in order for 
//  find_beachhead to work here.  This requirement should be removed.  For 
//  example, we can require that (dest_x,dest_y) is on a coast.  
//****************************************************************************/
//boolean aiferry_gobyboat(player pplayer, unit punit,
//		      tile dest_tile)
//{
//  if (punit.transported_by <= 0) {
//    /* We are not on a boat and we cannot walk */
//    int boatid;
//    unit ferryboat = null;
//
//    UNIT_LOG(LOGLEVEL_GOBYBOAT, punit, "will have to go to (%d,%d) by boat",
//             TILE_XY(dest_tile));
//
//    if (!Terrain.is_terrain_flag_near_tile(punit.tile)) {
//      pf_path path_to_ferry = null;
//      
//      boatid = aiferry_find_boat(punit, 2, &path_to_ferry);
//      if (boatid <= 0) {
//        UNIT_LOG(LOGLEVEL_GOBYBOAT, punit, 
//		 "in ai_gothere cannot find any boats.");
//        return false;
//      }
//
//      ferryboat = Game.find_unit_by_id(boatid);
//      UNIT_LOG(LOGLEVEL_GOBYBOAT, punit, "found boat[%d](%d,%d), going there", 
//	       boatid, TILE_XY(ferryboat.tile));
//      /* The path can be amphibious so we will stop at the coast.  
//       * It might not lead _onto_ the boat. */
//      if (!ai_unit_execute_path(punit, path_to_ferry)) { 
//        /* Died. */
//	pf_destroy_path(path_to_ferry);
//        return false;
//      }
//      pf_destroy_path(path_to_ferry);
//    }
//
//    if (!Terrain.is_terrain_flag_near_tile(punit.tile)) {
//      /* Still haven't reached the coast */
//      return false;
//    }
//
//    /* We are on the coast, look around for a boat */
//    boatid = aiferry_find_boat_nearby(punit, 2);
//    if (boatid <= 0) {
//      UNIT_LOG(LOGLEVEL_GOBYBOAT, punit, "requesting a boat.");
//      aiferry_request_boat(punit);
//      return false;
//    }
//
//    /* Ok, a boat found, try boarding it */
//    ferryboat = Game.find_unit_by_id(boatid);
//    UNIT_LOG(LOGLEVEL_GOBYBOAT, punit, "found a nearby boat[%d](%d,%d)",
//	     ferryboat.id, TILE_XY(ferryboat.tile));
//    /* Setting ferry now in hope it won't run away even 
//     * if we can't board it right now */
//    aiferry_psngr_meet_boat(punit, ferryboat);
//
//    if (is_tiles_adjacent(punit.tile, ferryboat.tile)) {
//      () ai_unit_move(punit, ferryboat.tile);
//    }
//
//    if (!can_unit_load(punit, ferryboat)) {
//      /* Something prevented us from boarding */
//      /* FIXME: this is probably a serious bug, but we just skip past
//       * it and continue. */
//      UNIT_LOG(LOGLEVEL_GOBYBOAT, punit, "couldn't board boat[%d](%d,%d)",
//	       ferryboat.id, TILE_XY(ferryboat.tile));
//      return false;
//    }
//
//    handle_unit_load(pplayer, punit.id, ferryboat.id);
//    assert(punit.transported_by > 0);
//  }
//
//  if (punit.transported_by > 0) {
//    /* We are on a boat, ride it! */
//    unit ferryboat = Game.find_unit_by_id(punit.transported_by);
//
//    /* Check if we are the passenger-in-charge */
//    if (is_boat_free(ferryboat, punit, 0)) {
//      tile beach_tile;     /* Destination for the boat */
//      unit bodyguard = Game.find_unit_by_id(punit.ai.bodyguard);
//
//      UNIT_LOG(LOGLEVEL_GOBYBOAT, punit, 
//	       "got boat[%d](moves left: %d), going (%d,%d)",
//               ferryboat.id, ferryboat.moves_left, TILE_XY(dest_tile));
//      aiferry_psngr_meet_boat(punit, ferryboat);
//
//      /* If the location is not accessible directly from sea
//       * or is defended and we are not marines, we will need a 
//       * landing beach */
//      if (!Terrain.is_terrain_flag_near_tile(dest_tile)
//          ||((is_non_allied_city_tile(dest_tile, pplayer) 
//              || Unit.is_non_allied_unit_tile(dest_tile, pplayer))
//             && !unit_flag(punit, F_MARINES))) {
//        if (!find_beachhead(punit, dest_tile, &beach_tile)) {
//          /* Nowhere to go */
//          return false;
//        }
//        UNIT_LOG(LOGLEVEL_GOBYBOAT, punit, 
//                 "Found beachhead (%d,%d)", TILE_XY(beach_tile));
//      } else {
//	beach_tile = dest_tile;
//      }
//
//      ferryboat.goto_tile = beach_tile;
//      punit.goto_tile = dest_tile;
//      /* Grab bodyguard */
//      if (bodyguard
//          && !Map.same_pos(punit.tile, bodyguard.tile)) {
//        if (!goto_is_sane(bodyguard, punit.tile, true)
//            || !ai_unit_goto(punit, punit.tile)) {
//          /* Bodyguard can't get there or util.died en route */
//          punit.ai.bodyguard = BODYGUARD_WANTED;
//          bodyguard = null;
//        } else if (bodyguard.moves_left <= 0) {
//          /* Wait for me, I'm cooooming!! */
//          UNIT_LOG(LOGLEVEL_GOBYBOAT, punit, "waiting for bodyguard");
//          return false;
//        } else {
//          /* Crap bodyguard. Got stuck somewhere. Ditch it! */
//          UNIT_LOG(LOGLEVEL_GOBYBOAT, punit, "ditching useless bodyguard");
//          punit.ai.bodyguard = BODYGUARD_WANTED;
//          ai_unit_new_role(bodyguard, AIUNIT_NONE, null);
//          bodyguard = null;
//        }
//      }
//      if (bodyguard) {
//        assert(Map.same_pos(punit.tile, bodyguard.tile));
//	handle_unit_load(pplayer, bodyguard.id, ferryboat.id);
//      }
//      if (!ai_unit_goto(ferryboat, beach_tile)) {
//        /* util.died */
//        return false;
//      }
//      if (!is_tiles_adjacent(ferryboat.tile, beach_tile)
//          && !Map.same_pos(ferryboat.tile, beach_tile)) {
//        /* We are in still transit */
//        return false;
//      }
//    } else {
//      /* Waiting for the boss to load and move us */
//      UNIT_LOG(LOGLEVEL_GOBYBOAT, punit, "Cannot command boat [%d]," +
//               " its boss is [%d]", 
//               ferryboat.id, ferryboat.ai.passenger);
//      return false;
//    }
//
//    UNIT_LOG(LOGLEVEL_GOBYBOAT, punit, "Our boat has arrived");
//    Unithand.handle_unit_activity_request(punit, unit_activity.ACTIVITY_IDLE);
//  }
//
//  return true;
//}
//
//
///* ===================== boat management ================================= */
//
///****************************************************************************
//  A helper for ai_manage_ferryboat.  Finds a passenger for the ferry.
//  Potential passengers signal the boats by setting their ai.ferry field to
//  FERRY_WANTED.
//
//  TODO: lift the path off the map
//****************************************************************************/
//static boolean aiferry_findcargo(unit pferry)
//{
//  /* Path-finding stuff */
//  pf_map map;
//  struct pf_parameter parameter;
//  int passengers = ai_data_get(pferry.unit_owner()).stats.passengers;
//
//  if (passengers <= 0) {
//    /* No passangers anywhere */
//    return false;
//  }
//
//  UNIT_LOG(LOGLEVEL_FERRY, pferry, "Ferryboat is looking for cargo.");
//
//  pft_fill_unit_overlap_param(&parameter, pferry);
//  /* If we have omniscience, we use it, since paths to some places
//   * might be "blocked" by unknown.  We don't want to fight though */
//  parameter.get_TB = no_fights;
//  
//  map = pf_create_map(&parameter);
//  while (pf_next(map)) {
//    struct pf_position pos;
//
//    pf_next_get_position(map, &pos);
//    
//    for (unit aunit : pos.tile.units.data) {
//      if (pferry.owner == aunit.owner 
//	  && (aunit.ai.ferryboat == FERRY_WANTED
//	      || aunit.ai.ferryboat == pferry.id)) {
//        UNIT_LOG(LOGLEVEL_FERRY, pferry, 
//                 "Found a potential cargo %s[%d](%d,%d), going there",
//                 aunit.unit_type().name, aunit.id, TILE_XY(aunit.tile));
//	pferry.goto_tile = aunit.tile;
//        /* Exchange phone numbers */
//        aiferry_psngr_meet_boat(aunit, pferry);
//        pf_destroy_map(map);
//        return true;
//      }
//    } }
//  }
//
//  /* false positive can happen if we cannot find a route to the passenger
//   * because of an internal sea or enemy blocking the route */
//  UNIT_LOG(LOGLEVEL_FERRY, pferry,
//           "AI Passengers counting reported false positive %d", passengers);
//  pf_destroy_map(map);
//  return false;
//}
//
///****************************************************************************
//  A helper for ai_manage_ferryboat.  Finds a city that wants a ferry.  It
//  might signal for the ferry using pcity.ai.choice.need_boat field or
//  it might simply be building a ferry of it's own.
//
//  The city found will be set as the goto destination.
//
//  TODO: lift the path off the map
//  TODO (possible): put this and ai_ferry_findcargo into one PF-loop.  This 
//  will save some code lines but will be faster in the rare cases when there
//  passengers that can not be reached ("false positive").
//****************************************************************************/
//static boolean aiferry_find_interested_city(unit pferry)
//{
//  /* Path-finding stuff */
//  pf_map map;
//  struct pf_parameter parameter;
//  /* Early termination condition */
//  int turns_horizon = FC_INFINITY;
//  /* Future return value */
//  boolean needed = false;
//
//  UNIT_LOG(LOGLEVEL_FERRY, pferry, "Ferry looking for a city that needs it");
//
//  pft_fill_unit_parameter(&parameter, pferry);
//  /* We are looking for our own cities, no need to look into the unknown */
//  parameter.get_TB = no_fights_or_unknown;
//  parameter.omniscience = false;
//  
//  map = pf_create_map(&parameter);
//  parameter.turn_mode = TM_WORST_TIME;
//
//  /* We want to consider the place we are currently in too, hence the 
//   * do-while loop */
//  do { 
//    struct pf_position pos;
//    city pcity;
//
//    pf_next_get_position(map, &pos);
//    if (pos.turn >= turns_horizon) {
//      /* Won't be able to find anything better than what we have */
//      break;
//    }
//
//    pcity = Map.map_get_city(pos.tile);
//    
//    if (pcity && pcity.owner == pferry.owner
//        && (pcity.ai.choice.need_boat 
//            || (pcity.is_building_unit
//		&& unit_has_role(pcity.currently_building, L_FERRYBOAT)))) {
//      boolean really_needed = true;
//      int turns = city_turns_to_build(pcity, pcity.currently_building,
//                                      pcity.is_building_unit, true);
//
//      UNIT_LOG(LOGLEVEL_FERRY, pferry, "%s (%d, %d) looks promising...", 
//               pcity.name, TILE_XY(pcity.tile));
//
//      if (pos.turn > turns && pcity.is_building_unit
//          && unit_has_role(pcity.currently_building, L_FERRYBOAT)) {
//        UNIT_LOG(LOGLEVEL_FERRY, pferry, "%s is NOT suitable: " +
//                 "will finish building its own ferry too soon", pcity.name);
//        continue;
//      }
//
//      if (turns >= turns_horizon) {
//        UNIT_LOG(LOGLEVEL_FERRY, pferry, "%s is NOT suitable: " +
//                 "has just started building", pcity.name);
//        continue;
//      }
//
//      for (unit aunit : pos.tile.units.data) {
//	if (aunit != pferry && aunit.owner == pferry.owner
//            && unit_has_role(aunit.type, L_FERRYBOAT)) {
//
//          UNIT_LOG(LOGLEVEL_FERRY, pferry, "%s is NOT suitable: " +
//                   "has another ferry", pcity.name);
//	  really_needed = false;
//	  break;
//	}
//      } }
//
//      if (really_needed) {
//        UNIT_LOG(LOGLEVEL_FERRY, pferry, "will go to %s unless we " +
//                 "find something better", pcity.name);
//	pferry.goto_tile = pos.tile;
//        turns_horizon = turns;
//        needed = true;
//      }
//    }
//  } while (pf_next(map));
//
//  pf_destroy_map(map);
//  return needed;
//}
//
///****************************************************************************
//  It's about 12 feet square and has a capacity of almost 1000 pounds.
//  It is well finalructed of teak, and looks seaworthy.
//
//  Manage ferryboat.  If there is a passenger-in-charge, we let it drive the 
//  boat.  If there isn't, appoint one from those we have on board.
//
//  If there is no one aboard, look for potential cargo.  If none found, 
//  explore and then go to the nearest port.
//****************************************************************************/
//void ai_manage_ferryboat(player pplayer, unit punit)
//{
//  city pcity;
//
//  CHECK_UNIT(punit);
//
//  /* Try to recover hitpoints if we are in a city, before we do anything */
//  if (punit.hp < punit.unit_type().hp 
//      && (pcity = Map.map_get_city(punit.tile))) {
//    UNIT_LOG(LOGLEVEL_FERRY, punit, "waiting in %s to recover hitpoints", 
//             pcity.name);
//    return;
//  }
//
//  /* Check if we are an empty barbarian boat and so not needed */
//  if (is_barbarian(pplayer) && get_transporter_occupancy(punit) == 0) {
//    Unittools.wipe_unit(punit);
//    return;
//  }
//
//  do {
//    /* Do we have the passenger-in-charge on board? */
//    tile ptile = punit.tile;
//
//    if (punit.ai.passenger > 0) {
//      unit psngr = Game.find_unit_by_id(punit.ai.passenger);
//      
//      /* If the passenger-in-charge is adjacent, we should wait for it to 
//       * board.  We will pass control to it later. */
//      if (!psngr 
//	  || Map.real_map_distance(punit.tile, psngr.tile) > 1) {
//	UNIT_LOG(LOGLEVEL_FERRY, punit, 
//		 "recorded passenger[%d] is not on board, checking for others",
//		 punit.ai.passenger);
//	punit.ai.passenger = 0;
//      }
//    }
//
//    if (punit.ai.passenger <= 0) {
//      unit candidate = null;
//    
//      /* Try to select passanger-in-charge from among our passengers */
//      for (unit aunit : ptile.units.data) {
//        if (aunit.unit_owner() != pplayer 
//            || (aunit.ai.ferryboat != punit.id 
//                && aunit.ai.ferryboat != FERRY_WANTED)) {
//          continue;
//        }
//      
//        if (aunit.ai.ai_role != AIUNIT_ESCORT) {
//          candidate = aunit;
//          break;
//        } else {
//          /* Bodyguards shouldn't be in charge of boats so continue looking */
//          candidate = aunit;
//        }
//      } }
//      
//      if (candidate) {
//        UNIT_LOG(LOGLEVEL_FERRY, punit, 
//                 "appointed %s[%d] our passenger-in-charge",
//                 candidate.unit_type().name, candidate.id);
//        aiferry_psngr_meet_boat(candidate, punit);
//      }
//    }
//
//    if (punit.ai.passenger > 0) {
//      int bossid = punit.ai.passenger;    /* Loop prevention */
//      unit boss = Game.find_unit_by_id(punit.ai.passenger);
//      int id = punit.id;                  /* To check if survived */
//
//      assert(boss != null);
//
//      if (unit_flag(boss, Eunit_flag_id.F_SETTLERS) || unit_flag(boss, Eunit_flag_id.F_CITIES)) {
//        /* Temporary hack: settlers all go in the end, forcing them 
//         * earlier might mean uninitialised cache, so just wait for them */
//        return;
//      }
//
//      UNIT_LOG(LOGLEVEL_FERRY, punit, "passing control to %s[%d]",
//		boss.unit_type().name, boss.id);
//      ai_manage_unit(pplayer, boss);
//    
//      if (!Game.find_unit_by_id(id) || punit.moves_left <= 0) {
//        return;
//      }
//      if (Game.find_unit_by_id(bossid)) {
//	if (Map.same_pos(punit.tile, boss.tile)) {
//	  /* The boss decided to stay put on the ferry. We aren't moving. */
//	  return;
//	} else if (get_transporter_occupancy(punit) != 0) {
//	  /* The boss isn't on the ferry, and we have other passengers?
//	   * Forget about him. */
//	  punit.ai.passenger = 0;
//	}
//      }
//    } else {
//      /* Cannot select a passenger-in-charge */
//      break;
//    }
//  } while (get_transporter_occupancy(punit) != 0);
//
//  /* Not carrying anyone, even the ferryman */
//
//  if (IS_ATTACKER(punit) && punit.moves_left > 0) {
//     /* AI used to build frigates to attack and then use them as ferries 
//      * -- Syela */
//     UNIT_LOG(LOGLEVEL_FERRY, punit, "passing ferry over to attack code");
//     ai_manage_military(pplayer, punit);
//     return;
//  }
//
//  UNIT_LOG(LOGLEVEL_FERRY, punit, "Ferryboat is not carrying anyone.");
//  aiferry_make_available(punit);
//  Unithand.handle_unit_activity_request(punit, unit_activity.ACTIVITY_IDLE);
//  ai_unit_new_role(punit, AIUNIT_NONE, null);
//  CHECK_UNIT(punit);
//
//  /* Try to find passengers */
//  if (aiferry_findcargo(punit)) {
//    UNIT_LOG(LOGLEVEL_FERRY, punit, "picking up cargo");
//    ai_unit_goto(punit, punit.goto_tile);
//    return;
//  }
//
//  /* Try to find a city that needs a ferry */
//  if (aiferry_find_interested_city(punit)) {
//    if (Map.same_pos(punit.tile, punit.goto_tile)) {
//      UNIT_LOG(LOGLEVEL_FERRY, punit, "staying in city that needs us");
//      return;
//    } else {
//      UNIT_LOG(LOGLEVEL_FERRY, punit, "going to city that needs us");
//      () ai_unit_goto(punit, punit.goto_tile);
//      return;
//    }
//  }
//
//  UNIT_LOG(LOGLEVEL_FERRY, punit, "Passing control of ferry to explorer code");
//  () ai_manage_explorer(punit);
//
//  if (punit.moves_left > 0) {
//    city pcity = find_nearest_safe_city(punit);
//    if (pcity) {
//      punit.goto_tile = pcity.tile;
//      UNIT_LOG(LOGLEVEL_FERRY, punit, "No work, going home");
//      () ai_unit_goto(punit, pcity.tile);
//    }
//  }
// 
//  return;
//}
}