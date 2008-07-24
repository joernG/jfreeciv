package ai;

public class Aiair{

// Freeciv - Copyright (C) 2002 - The Freeciv Team
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
//#include "combat.h"
//#include "log.h"
//#include "Map.map.h"
//#include "player.h"
//#include "unit.h"
//
//#include "airgoto.h"
//#include "citytools.h"
//#include "gotohand.h"
//#include "maphand.h"
//#include "unithand.h"
//#include "unittools.h"
//
//#include "aitools.h"
//#include "aiunit.h"
//
//#include "aiair.h"
//
///**************************************************************************
// * Looks for nearest airbase for punit.
// * Returns 0 if not found.
// * TODO: 1. Use proper refuel_iterate, like in ai_find_strategic_airbase
// *       2. Special handicaps for planes running out of fuel
// *       IMO should be less restrictive than general H_MAP, H_FOG
// *************************************************************************/
//static boolean find_nearest_airbase(tile ptile, unit punit, 
//				 tile *airbase_tile)
//{
//  player pplayer = punit.unit_owner();
//  int moves_left = punit.moves_left / Unit_H.SINGLE_MOVE;
//
//  iterate_outward(ptile, moves_left, tile1) {
//    if (is_airunit_refuel_point(tile1, pplayer, punit.type, false)
//	&& (Gotohand.air_can_move_between (moves_left, ptile, tile1, pplayer) >= 0)) {
//      *airbase_tile = tile1;
//      return true;
//    }
//  } iterate_outward_end;
//
//  return false;
//}
//
///**********************************************************************
// * Very preliminary estimate for our intent to attack the tile (x, y).
// * Used by bombers only.
// **********************************************************************/
//static boolean ai_should_we_air_attack_tile(unit punit,
//					 tile ptile)
//{
//  city acity = map_get_city(ptile);
//
//  /* For a virtual unit (punit.id == 0), all targets are good */
//  /* TODO: There is a danger of producing too many units that will not 
//   * attack anything.  Production should not happen if there is an idle 
//   * unit of the same type nearby */
//  if (acity && !TEST_BIT(acity.ai.invasion, 0) && punit.id != 0) {
//    /* No ground troups are invading */
//    util.freelog(Log.LOG_DEBUG, "Don't want to attack %s, although we could", 
//            acity.name);
//    return false;
//  }
//
//  return true;
//}
//
///**********************************************************************
// * Returns an estimate for the profit gained through attack.
// * Assumes that the victim is within one day's flight
// **********************************************************************/
//static int ai_evaluate_tile_for_air_attack(unit punit, 
//					   tile dst_tile)
//{
//  unit pdefender = get_defender(punit, dst_tile);
//  /* unit costs in shields */
//  int balanced_cost, unit_cost, victim_cost = 0;
//  /* unit stats */
//  int unit_attack, victim_defence;
//  /* final answer */
//  int profit;
//  /* time spent in the air */
//  int sortie_time;
//public static final int PROB_MULTIPLIER = 100; /* should unify with those in combat.c */
//
//  if ((pdefender == null) 
//      || !can_unit_attack_all_at_tile(punit, dst_tile)) {
//    return 0;
//  }
//
//  /* Ok, we can attack, but is it worth it? */
//
//  /* Cost of our unit */
//  unit_cost = unit_build_shield_cost(punit.type);
//  /* This is to say "wait, ill unit will get better!" */
//  unit_cost = unit_cost * punit.unit_type().hp / punit.hp; 
//
//  /* Determine cost of enemy units */
//  victim_cost = stack_cost(pdefender);
//
//  /* Missile would util.die 100% so we adjust the victim_cost -- GB */
//  if (unit_flag(punit, F_MISSILE)) {
//    victim_cost -= unit_build_shield_cost(punit.type);
//  }
//
//  unit_attack = (int) (PROB_MULTIPLIER 
//                       * unit_win_chance(punit, pdefender));
//
//  victim_defence = PROB_MULTIPLIER - unit_attack;
//
//  balanced_cost = build_cost_balanced(punit.type);
//
//  sortie_time = (unit_flag(punit, Eunit_flag_id.F_ONEATTACK) ? 1 : 0);
//
//  profit = kill_desire(victim_cost, unit_attack, unit_cost, victim_defence, 1) 
//    - SHIELD_WEIGHTING + 2 * TRADE_WEIGHTING;
//  if (profit > 0) {
//    profit = military_amortize(punit.unit_owner(), 
//                               find_city_by_id(punit.homecity),
//                               profit, sortie_time, balanced_cost);
//    util.freelog(Log.LOG_DEBUG, 
//	    "%s at (%d, %d) is a worthy target with profit %d", 
//	    pdefender.unit_type().name, dst_tile.x, dst_tile.y,
//	    profit);
//  } else {
//    util.freelog(Log.LOG_DEBUG, 
//	    "%s(%d, %d): %s at (%d, %d) is unworthy with profit %d",
//	    punit.unit_type().name, punit.tile.x, punit.tile.y,
//	    pdefender.unit_type().name, dst_tile.x, dst_tile.y,
//	    profit);
//    profit = 0;
//  }
//
//  return profit;
//}
//  
//
///**********************************************************************
// * Find something to bomb
// * Air-units specific victim search
// * Returns the want for the best target, records target in punit's goto_dest
// * TODO: take counterattack dangers into account
// * TODO: make separate handicaps for air units seeing targets
// *       IMO should be more restrictive than general H_MAP, H_FOG
// *********************************************************************/
//static int find_something_to_bomb(unit punit, tile ptile)
//{
//  player pplayer = punit.unit_owner();
//  int max_dist = punit.moves_left / Unit_H.SINGLE_MOVE;
//  int best = 0;
//
//  if (Map.same_pos(ptile, punit.tile)) {
//    /* Unit is attacking from here */
//    max_dist = punit.moves_left / Unit_H.SINGLE_MOVE;
//  } else {
//    /* Unit will be attacking from another airbase */
//    max_dist = punit.unit_type(). move_rate / Unit_H.SINGLE_MOVE;
//  }
//
//  /* Adjust the max distance so Fighters can attack safely */
//  if (punit.fuel < 2) {
//    /* -1 is to take into account the attack itself */
//    max_dist = (max_dist - 1) / 2;
//  }
//  
//  /* Let's find something to bomb */
//  iterate_outward(ptile, max_dist, tile1) {
//
//    if (ai_handicap(pplayer, H_MAP) && !Maphand.map_is_known(tile1, pplayer)) {
//      /* The target tile is unknown */
//      continue;
//    }
//    if (ai_handicap(pplayer, H_FOG) 
//        && !Maphand.map_is_known_and_seen(tile1, pplayer)) {
//      /* The tile is fogged */
//      continue;
//    }
//
//    if (is_enemy_unit_tile(tile1, pplayer)
//        && ai_should_we_air_attack_tile(punit, tile1)
//	&& (Gotohand.air_can_move_between (max_dist, ptile, tile1, pplayer) >= 0)){
//      int new_best = ai_evaluate_tile_for_air_attack(punit, tile1);
//      if (new_best > best) {
//	punit.goto_tile = tile1;
//	best = new_best;
//	util.freelog(Log.LOG_DEBUG, "%s wants to attack tile (%d, %d)", 
//		punit.unit_type().name, tile1.x, tile1.y);
//      }
//    }
//
//  } iterate_outward_end;
//
//  return best;
//} 
//
///***********************************************************************
// * Iterates through reachable cities and appraises them as a possible 
// * base for air operations by (air)unit punit.
// **********************************************************************/
//static boolean ai_find_strategic_airbase(unit punit,
//				      tile *airbase_tile)
//{
//  refuel airbase;
//  Pqueue airbase_iterator;
//  int turns_to_dest = 0;
//  int best_worth = 0;
//  boolean found = false;
//
//  airbase_iterator 
//    = refuel_iterate_init(punit.unit_owner(), punit.tile,
//                          punit.tile, true, 
//                          punit.moves_left / Unit_H.SINGLE_MOVE, 
//                          punit.move_rate() / Unit_H.SINGLE_MOVE, 
//                          punit.unit_type().fuel);
//
//  while( (airbase = refuel_iterate_next(airbase_iterator)) != null) {
//    int target_worth;
//
//    if (turns_to_dest > 0 
//        && turns_to_dest < get_turns_to_refuel(airbase)){
//      /* We had found something already and it's closer than 
//       * anything else -- so take it */
//      break;
//    }
//
//    if ((target_worth
//	 = find_something_to_bomb(punit, get_refuel_tile(airbase))) > 0) {
//      city base_city 
//        = map_get_city(get_refuel_tile(airbase));
//     
//      if (base_city && base_city.ai.grave_danger != 0) {
//        /* Fly there immediately!! */
//	*airbase_tile = get_refuel_tile(airbase);
//        found = true;
//        break;
//      } else if (target_worth > best_worth) {
//        /* It's either a first find or it's better than the previous */
//	*airbase_tile = get_refuel_tile(airbase);
//        turns_to_dest = get_turns_to_refuel(airbase);
//        best_worth = target_worth;
//        found = true;
//        /* We can still look for something better */
//      }
//    }
//
//    refuel_iterate_process(airbase_iterator, airbase);
//  }
//
//  refuel_iterate_end(airbase_iterator);
//
//  return found;
//}
//
///************************************************************************
// * Trying to manage bombers and stuff.
// * If we are in the open {
// *   if moving intelligently on a valid GOTO, {
// *     carry on doing it.
// *   } else {
// *     go refuel
// *   }
// * } else {
// *   try to attack something
// * } 
// * TODO: distant target selection, support for fuel > 2
// ***********************************************************************/
//void ai_manage_airunit(player pplayer, unit punit)
//{
//  tile dst_tile = punit.tile;
//  /* Loop prevention */
//  int moves = punit.moves_left;
//  int id = punit.id;
//
//
//  CHECK_UNIT(punit);
//
//  if (!is_airunit_refuel_point(punit.tile, 
//			       pplayer, punit.type, false)) {
//    /* We are out in the open, what shall we do? */
//    tile refuel_tile;
//
//    if (punit.activity == unit_activity.ACTIVITY_GOTO
//      /* We are on a GOTO.  Check if it will get us anywhere */
//	&& is_airunit_refuel_point(punit.goto_tile, 
//				   pplayer, punit.type, false)
//	&& Gotohand.air_can_move_between (punit.moves_left/Unit_H.SINGLE_MOVE, 
//                                 punit.tile, punit.goto_tile,
//				 pplayer) >= 0) {
//      /* It's an ok GOTO, just go there */
//      ai_unit_goto(punit, punit.goto_tile);
//    } else if (find_nearest_airbase(punit.tile, punit, 
//			     &refuel_tile)) {
//      /* Go refuelling */
//      punit.goto_tile = refuel_tile;
//      util.freelog(Log.LOG_DEBUG, "Sent %s to refuel", punit.unit_type().name);
//      ai_unit_goto(punit, punit.goto_tile);
//    } else {
//      if (punit.fuel == 1) {
//	util.freelog(Log.LOG_DEBUG, "Oops, %s is fallin outta sky", 
//		punit.unit_type().name);
//      }
//      return;
//    }
//
//  } else if (punit.fuel == punit.unit_type().fuel) {
//    /* We only leave a refuel point when we are on full fuel */
//
//    if (find_something_to_bomb(punit, punit.tile) > 0) {
//      /* Found target, coordinates are in punit's goto_dest.
//       * TODO: separate attacking into a function, check for the best 
//       * tile to attack from */
//      assert(punit.goto_tile != null);
//      if (!ai_unit_goto(punit, punit.goto_tile)) {
//        return; /* util.died */
//      }
//
//      /* goto would be aborted: "Aborting GOTO for AI attack procedures"
//       * now actually need to attack */
//      /* We could use ai_military_findvictim here, but I don't trust it... */
//      handle_unit_activity_request(punit, unit_activity.ACTIVITY_IDLE);
//      if (is_tiles_adjacent(punit.tile, punit.goto_tile)) {
//        () Unithand.handle_unit_move_request(punit, punit.goto_tile,
//					true, false);
//      }
//    } else if (ai_find_strategic_airbase(punit, &dst_tile)) {
//      util.freelog(Log.LOG_DEBUG, "%s will fly to (%i, %i) (%s) to fight there",
//              punit.unit_type().name, dst_tile.x, dst_tile.y,
//              (map_get_city(dst_tile) ? 
//               map_get_city(dst_tile).name : ""));
//      punit.goto_tile = dst_tile;
//      ai_unit_goto(punit, punit.goto_tile);
//    } else {
//      util.freelog(Log.LOG_DEBUG, "%s cannot find anything to kill and is staying put", 
//              punit.unit_type().name);
//      handle_unit_activity_request(punit, unit_activity.ACTIVITY_IDLE);
//    }
//  }
//
//  if ((punit = find_unit_by_id(id)) != null && punit.moves_left > 0
//      && punit.moves_left != moves) {
//    /* We have moved this turn, might have ended up stuck out in the fields
//     * so, as a safety measure, let's manage again */
//    ai_manage_airunit(pplayer, punit);
//  }
//
//}
//
///*******************************************************************
// * Chooses the best available and usable air unit and records it in 
// * choice, if it's better than previous choice
// * The interface is somewhat different from other ai_choose, but
// * that's what it should be like, I believe -- GB
// ******************************************************************/
//boolean ai_choose_attacker_air(player pplayer, city pcity, 
//			    ai_choice choice)
//{
//  boolean want_something = false;
//
//  /* This AI doesn't know to build planes */
//  if (ai_handicap(pplayer, H_NOPLANES)) {
//    return false;
//  }
//
//  /* military_advisor_choose_build does something idiotic, 
//   * this function should not be called if there is danger... */
//  if (choice.want >= 100 && choice.type != CT_ATTACKER) {
//    return false;
//  }
//
//  if (!player_knows_techs_with_flag(pplayer, TF_BUILD_AIRBORNE)) {
//    return false;
//  }
//
//  unit_type_iterate(u_type) {
//    if (get_unit_type(u_type).move_type != AIR_MOVING) continue;
//    if (can_build_unit(pcity, u_type)) {
//      unit virtual_unit = 
//	create_unit_virtual(pplayer, pcity, u_type, 
//                            do_make_unit_veteran(pcity, u_type));
//      int profit = find_something_to_bomb(virtual_unit, pcity.tile);
//      if (profit > choice.want){
//	/* Update choice */
//	choice.want = profit;
//	choice.choice = u_type;
//	choice.type = CT_ATTACKER;
//	want_something = true;
//	util.freelog(Log.LOG_DEBUG, "%s wants to build %s (want=%d)",
//		pcity.name, get_unit_type(u_type).name, profit);
//      } else {
//      util.freelog(Log.LOG_DEBUG, "%s doesn't want to build %s (want=%d)",
//		pcity.name, get_unit_type(u_type).name, profit);
//      }
//      destroy_unit_virtual(virtual_unit);
//    }
//  } unit_type_iterate_end;
//
//  return want_something;
//}
}