package ai;

import common.map.tile;
import common.unit.unit;

import server.Barbarian;

public class Aiunit{

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
//#include <math.h>
//
//#include "city.h"
//#include "combat.h"
//#include "Game.game.h"
//#include "government.h"
//#include "log.h"
//#include "map.h"
//#include "mem.h"
//#include "packets.h"
//#include "pf_tools.h"
//#include "player.h"
//#include "rand.h"
//#include "shared.h"
//#include "timing.h"
//#include "unit.h"
//
//#include "barbarian.h"
//#include "citytools.h"
//#include "cityturn.h"
//#include "diplomats.h"
//#include "gotohand.h"
//#include "maphand.h"
//#include "settlers.h"
//#include "unithand.h"
//#include "unittools.h"
//
//#include "path_finding.h"
//#include "pf_tools.h"
//
//#include "advmilitary.h"
//#include "aiair.h"
//#include "aicity.h"
//#include "aidata.h"
//#include "aidiplomat.h"
//#include "aiexplorer.h"
//#include "aiferry.h"
//#include "aihand.h"
//#include "aihunt.h"
//#include "ailog.h"
//#include "aitools.h"
//
//#include "aiunit.h"
//
//public static final int LOGLEVEL_RECOVERY = Log.LOG_DEBUG;
//
//static void ai_manage_caravan(player pplayer, unit punit);
//static void ai_manage_barbarian_leader(player pplayer,
//				       unit leader);
//
//public static final int RAMPAGE_ANYTHING = 1;
//public static final int RAMPAGE_HUT_OR_BETTER = 99998;
//public static final int RAMPAGE_FREE_CITY_OR_BETTER = 99999;
//#define BODYGUARD_RAMPAGE_THRESHOLD (SHIELD_WEIGHTING * 4)
//static boolean ai_military_rampage(unit punit, int thresh_adj, 
//                                int thresh_move);
//static void ai_military_findjob(player pplayer,unit punit);
//static void ai_military_gohome(player pplayer,unit punit);
//static void ai_military_attack(player pplayer,unit punit);
//
//static int unit_move_turns(unit punit, tile ptile);
//static boolean unit_role_defender(int type);
//static int unit_def_rating_sq(unit punit, unit pdef);
//
///*
// * Cached values. Updated by update_simple_ai_types.
// *
// * This a hack to enable emulation of old loops previously hardwired
// * as 
// *    for (i = U_WARRIORS; i <= U_BATTLESHIP; i++)
// *
// * (Could probably just adjust the loops themselves fairly simply,
// * but this is safer for regression testing.)
// *
// * Not dealing with planes yet.
// *
// * Terminated by unittype.U_LAST.
// */
//int simple_ai_types[unittype.U_LAST];
//
///**************************************************************************
//  Move defenders around with airports. Since this expends all our 
//  movement, a valid question is - why don't we do this on turn end?
//  That's because we want to avoid emergency actions to protect the city
//  during the turn if that isn't necessary.
//**************************************************************************/
//static void ai_airlift(player pplayer)
//{
//  city most_needed;
//  int comparison;
//  unit transported;
//
//  do {
//    most_needed = null;
//    comparison = 0;
//    transported = null;
//
//    for (city pcity : pplayer.cities.data) {
//      if (pcity.ai.urgency > comparison && pcity.airlift) {
//        comparison = pcity.ai.urgency;
//        most_needed = pcity;
//      }
//    } }
//    if (!most_needed) {
//      comparison = 0;
//      for (city pcity : pplayer.cities.data) {
//        if (pcity.ai.danger > comparison && pcity.airlift) {
//          comparison = pcity.ai.danger;
//          most_needed = pcity;
//        }
//      } }
//    }
//    if (!most_needed) {
//      return;
//    }
//    comparison = 0;
//    for (unit punit : pplayer.units.data) {
//      tile ptile = (punit.tile);
//
//      if (ptile.city && ptile.city.ai.urgency == 0
//          && ptile.city.ai.danger - DEFENCE_POWER(punit) < comparison
//          && unit_can_airlift_to(punit, most_needed)
//          && DEFENCE_POWER(punit) > 2
//          && (punit.ai.ai_role == AIUNIT_NONE
//              || punit.ai.ai_role == AIUNIT_DEFEND_HOME)
//          && IS_ATTACKER(punit)) {
//        comparison = ptile.city.ai.danger;
//        transported = punit;
//      }
//    } }
//    if (!transported) {
//      return;
//    }
//    UNIT_LOG(Log.LOG_DEBUG, transported, "airlifted to defend %s", 
//             most_needed.name);
//    do_airline(transported, most_needed);
//  } while (true);
//}
//
///**************************************************************************
//  Similar to is_my_zoc(), but with some changes:
//  - destination (x0,y0) need not be adjacent?
//  - don't care about some directions?
//  
//  Note this function only makes sense for ground units.
//
//  Fix to bizarre did-not-find bug.  Thanks, Katvrr -- Syela
//**************************************************************************/
//static boolean could_be_my_zoc(unit myunit, tile ptile)
//{
//  assert(is_ground_unit(myunit));
//  
//  if (Map.same_pos(ptile, myunit.tile))
//    return false; /* can't be my zoc */
//  if (is_tiles_adjacent(ptile, myunit.tile)
//      && !Unit.is_non_allied_unit_tile(ptile, myunit.unit_owner()))
//    return false;
//
//  for(tile atile: util.adjc_tile_iterate(ptile)) {
//    if (!Terrain_H.is_ocean(atile.terrain)
//	&& Unit.is_non_allied_unit_tile(atile, myunit.unit_owner())) {
//      return false;
//    }
//  }
//  
//  return true;
//}

/**************************************************************************
  returns:
    0 if can't move
    1 if zoc_ok
   -1 if zoc could be ok?
**************************************************************************/
public static int could_unit_move_to_tile(unit punit, tile dest_tile)
{
//  enum unit_move_result result;
//
//  result =
//      test_unit_move_to_tile(punit.type, punit.unit_owner(),
//                             unit_activity.ACTIVITY_IDLE, punit.tile, 
//                             dest_tile, unit_flag(punit, F_IGZOC));
//  if (result == MR_OK) {
//    return 1;
//  }
//
//  if (result == MR_ZOC) {
//    if (could_be_my_zoc(punit, punit.tile)) {
//      return -1;
//    }
//  }
  return 0;
}


//  This is a much simplified form of assess_defense (see advmilitary.c),
//  but which doesn't use pcity.ai.wallvalue and just returns a boolean
//  value.  This is for use with "foreign" cities, especially non-ai
//  cities, where ai.wallvalue may be out of date or uninitialized --dwp
//***********************************************************************/
//static boolean has_defense(city pcity)
//{
//  unit_list_iterate((pcity.tile).units, punit) {
//    if (is_military_unit(punit) && get_defense_power(punit) != 0 && punit.hp != 0) {
//      return true;
//    }
//  }
//  }
//  return false;
//}
//
///**********************************************************************
// Precondition: A warmap must already be generated for the punit.
//
// Returns the minimal amount of turns required to reach the given
// destination position. The actual turn at which the unit will
// reach the given point depends on the movement points it has remaining.
//
// For example: Unit has a move rate of 3, path cost is 5, unit has 2
// move points left.
//
// path1 costs: first tile = 3, second tile = 2
//
// turn 0: points = 2, unit has to wait
// turn 1: points = 3, unit can move, points = 0, has to wait
// turn 2: points = 3, unit can move, points = 1
//
// path2 costs: first tile = 2, second tile = 3
//
// turn 0: points = 2, unit can move, points = 0, has to wait
// turn 1: points = 3, unit can move, points = 0
//
// In spite of the path costs being the same, units that take a path of the
// same length will arrive at different times. This function also does not 
// take into account ZOC or lack of hp affecting movement.
// 
// Note: even if a unit has only fractional move points left, there is
// still a possibility it could cross the tile.
//**************************************************************************/
//static int unit_move_turns(unit punit, tile ptile)
//{
//  int move_time;
//  int move_rate = punit.move_rate();
//
//  switch (punit.unit_type().move_type) {
//  case unit_move_type.LAND_MOVING:
//  
//   /* FIXME: IGTER units should have their move rates multiplied by 
//    * igter_speedup. Note: actually, igter units should never have their 
//    * move rates multiplied. The correct behaviour is to have every tile 
//    * they cross cost 1/3 of a movement point. ---RK */
// 
//    if (unit_flag(punit, F_IGTER)) {
//      move_rate *= 3;
//    }
//    move_time = WARMAP_COST(ptile) / move_rate;
//    break;
// 
//  case unit_move_type.SEA_MOVING:
//    move_time = WARMAP_SEACOST(ptile) / move_rate;
//    break;
// 
//  case unit_move_type.HELI_MOVING:
//  case unit_move_type.AIR_MOVING:
//     move_time = Map.real_map_distance(punit.tile, ptile) 
//                   * Unit_H.SINGLE_MOVE / move_rate;
//     break;
// 
//  default:
//    util.die("ai/aiunit.c:unit_move_turns: illegal move type %d",
//	punit.unit_type().move_type);
//    move_time = 0;
//  }
//  return move_time;
//}
// 
///*********************************************************************
//  In the words of Syela: "Using funky fprime variable instead of f in
//  the denom, so that def=1 units are penalized correctly."
//
//  Translation (GB): build_cost_balanced is used in the denominator of
//  the want equation (see, e.g.  find_something_to_kill) instead of
//  just build_cost to make AI build more balanced units (with def > 1).
//*********************************************************************/
//int build_cost_balanced(int type)
//{
//  unit_type ptype = get_unit_type(type);
//
//  return 2 * Unittype_P.unit_build_shield_cost(type) * ptype.attack_strength /
//      (ptype.attack_strength + ptype.defense_strength);
//}
//
//
///**************************************************************************
//  Return first city that contains a wonder being built on the given
//  continent.
//**************************************************************************/
//static city wonder_on_continent(player pplayer, 
//					Continent_id cont)
//{
//  for(city pcity : pplayer.cities.data){//    if (!(pcity.is_building_unit) 
//        && Improvement.is_wonder(pcity.currently_building)
//        && map_get_continent(pcity.tile) == cont) {
//      return pcity;
//  }
//  }
//  return null;
//}
//
///**************************************************************************
//  Return whether we should stay and defend a square, usually a city. Will
//  protect allied cities temporarily in case of grave danger.
//
//  FIXME: We should check for fortresses here.
//**************************************************************************/
//static boolean stay_and_defend(unit punit)
//{
//  city pcity = Map.map_get_city(punit.tile);
//  boolean has_defense = false;
//  int mydef;
//  int units = -2; /* WAG for grave danger threshold, seems to work */
//
//  if (!pcity) {
//    return false;
//  }
//  mydef = assess_defense_unit(pcity, punit, false);
//
//  unit_list_iterate((pcity.tile).units, pdef) {
//    if (assess_defense_unit(pcity, pdef, false) >= mydef
//	&& pdef != punit
//	&& pdef.homecity == pcity.id) {
//      has_defense = true;
//    }
//    units++;
//  } }
// 
//  /* Guess I better stay / you can live at home now */
//  if (!has_defense && pcity.ai.danger > 0 && punit.owner == pcity.owner) {
//    /* Change homecity to this city */
//    if (ai_unit_make_homecity(punit, pcity)) {
//      /* Very important, or will not stay -- Syela */
//      ai_unit_new_role(punit, AIUNIT_DEFEND_HOME, pcity.tile);
//      return true;
//    } /* else city cannot upkeep us! */
//  }
//
//  /* Treat grave danger anyway if danger is over threshold, which is the
//   * number of units currently in the city.  However, to avoid AI panics
//   * (this is common when enemy is huge), add a ceiling. */
//  if (pcity.ai.grave_danger > units && units <= 2) {
//    ai_unit_new_role(punit, AIUNIT_DEFEND_HOME, pcity.tile);
//    return true;
//  }
//
//  return false;
//}
//
///**************************************************************************
//  Attack rating of this kind of unit.
//**************************************************************************/
//int unittype_att_rating(int type, int veteran,
//                        int moves_left, int hp)
//{
//  return base_get_attack_power(type, veteran, moves_left) * hp
//         * get_unit_type(type).firepower / POWER_DIVIDER;
//}
//

//  Attack rating of this particular unit right now.
//***********************************************************************/
//static int unit_att_rating_now(unit punit)
//{
//  return unittype_att_rating(punit.type, punit.veteran,
//                             punit.moves_left, punit.hp);
//}
//

//  Attack rating of this particular unit assuming that it has a
//  complete move left.
//***********************************************************************/
//int unit_att_rating(unit punit)
//{
//  return unittype_att_rating(punit.type, punit.veteran,
//                             Unit_H.SINGLE_MOVE, punit.hp);
//}
//

//  Square of the previous function - used in actual computations.
//***********************************************************************/
//static int unit_att_rating_sq(unit punit)
//{
//  int v = unit_att_rating(punit);
//  return v * v;
//}
//

//  Defence rating of this particular unit against this attacker.
//***********************************************************************/
//static int unit_def_rating(unit attacker, unit defender)
//{
//  return get_total_defense_power(attacker, defender) *
//         (attacker.id != 0 ? defender.hp : defender.unit_type().hp) *
//         defender.unit_type().firepower / POWER_DIVIDER;
//}
//

//  Square of the previous function - used in actual computations.
//***********************************************************************/
//static int unit_def_rating_sq(unit attacker,
//                              unit defender)
//{
//  int v = unit_def_rating(attacker, defender);
//  return v * v;
//}
//

//  Basic (i.e. not taking attacker specific corections into account) 
//  defence rating of this particular unit.
//***********************************************************************/
//int unit_def_rating_basic(unit punit)
//{
//  return base_get_defense_power(punit) * punit.hp *
//         Unittype_P.unit_types[punit.type].firepower / POWER_DIVIDER;
//}
//

//  Square of the previous function - used in actual computations.
//***********************************************************************/
//int unit_def_rating_basic_sq(unit punit)
//{
//  int v = unit_def_rating_basic(punit);
//  return v * v;
//}
//
///**************************************************************************
//  Defence rating of def_type unit against att_type unit, squared.
//  See get_virtual_defense_power for the arguments att_type, def_type,
//  x, y, fortified and veteran.
//**************************************************************************/
//int unittype_def_rating_sq(int att_type, int def_type,
//                           tile ptile, boolean fortified, int veteran)
//{
//  int v = get_virtual_defense_power(att_type, def_type, ptile,
//                                    fortified, veteran) *
//          Unittype_P.unit_types[def_type].hp *
//          Unittype_P.unit_types[def_type].firepower / POWER_DIVIDER;
//  return v * v;
//}
//
///**************************************************************************
//Compute how much we want to kill certain victim we've chosen, counted in
//SHIELDs.
//
//FIXME?: The equation is not accurate as the other values can vary for other
//victims on same tile (we take values from best defender) - however I believe
//it's accurate just enough now and lost speed isn't worth that. --pasky
//
//Benefit is something like 'attractiveness' of the victim, how nice it would be
//to destroy it. Larger value, worse loss for enemy.
//
//Attack is the total possible attack power we can throw on the victim. Note that
//it usually comes squared.
//
//Loss is the possible loss when we would lose the unit we are attacking with (in
//SHIELDs).
//
//Vuln is vulnerability of our unit when attacking the enemy. Note that it
//usually comes squared as well.
//
//Victim count is number of victims stacked in the target tile. Note that we
//shouldn't treat cities as a stack (despite the code using this function) - the
//scaling is probably different. (extremely dodgy usage of it -- GB)
//**************************************************************************/
//int kill_desire(int benefit, int attack, int loss, int vuln, int victim_count)
//{
//  int desire;
//
//  /*         attractiveness     danger */ 
//  desire = ((benefit * attack - loss * vuln) * victim_count * SHIELD_WEIGHTING
//            / (attack + vuln * victim_count));
//
//  return desire;
//}
//
///**************************************************************************
//  Compute how much we want to kill certain victim we've chosen, counted in
//  SHIELDs.  See comment to kill_desire.
//
//  chance -- the probability the action will succeed, 
//  benefit -- the benefit (in shields) that we are getting in the case of 
//             success
//  loss -- the loss (in shields) that we suffer in the case of failure
//
//  Essentially returns the probabilistic average win amount:
//      benefit * chance - loss * (1 - chance)
//**************************************************************************/
//static int avg_benefit(int benefit, int loss, double chance)
//{
//  return (int)(((benefit + loss) * chance - loss) * SHIELD_WEIGHTING);
//}
//
///**************************************************************************
//  Calculates the value and cost of nearby allied units to see if we can
//  expect any help in our attack. Base function.
//**************************************************************************/
//static void reinforcements_cost_and_value(unit punit,
//					  tile ptile0,
//                                          int *value, int *cost)
//{
//  *cost = 0;
//  *value = 0;
//  for(tile ptile: util.square_tile_iterate(ptile0, 1)) {
//    for (unit aunit : ptile.units.data) {
//      if (aunit != punit
//	  && Player_P.pplayers_allied(punit.unit_owner(), aunit.unit_owner())) {
//        int val = unit_att_rating(aunit);
//
//        if (val != 0) {
//          *value += val;
//          *cost += Unittype_P.unit_build_shield_cost(aunit.type);
//        }
//      }
//    } }
//  }
//}
//
///*************************************************************************
//  Is there another unit which really should be doing this attack? Checks
//  all adjacent tiles and the tile we stand on for such units.
//**************************************************************************/
//static boolean is_my_turn(unit punit, unit pdef)
//{
//  int val = unit_att_rating_now(punit), cur, d;
//
//  CHECK_UNIT(punit);
//
//  for(tile ptile: util.square_tile_iterate(pdef.tile, 1)) {
//    for (unit aunit : ptile.units.data) {
//      if (aunit == punit || aunit.owner != punit.owner)
//	continue;
//      if (!can_unit_attack_all_at_tile(aunit, pdef.tile))
//	continue;
//      d = get_virtual_defense_power(aunit.type, pdef.type, pdef.tile,
//				    false, 0);
//      if (d == 0)
//	return true;		/* Thanks, Markus -- Syela */
//      cur = unit_att_rating_now(aunit) *
//	  get_virtual_defense_power(punit.type, pdef.type, pdef.tile,
//				    false, 0) / d;
//      if (cur > val && ai_fuzzy(punit.unit_owner(), true))
//	return false;
//    }
//    }
//  }
//  square_iterate_end;
//  return true;
//}
//
///*************************************************************************
//  This function appraises the location (x, y) for a quick hit-n-run 
//  operation.  We do not take into account reinforcements: rampage is for
//  loners.
//
//  Returns value as follows:
//    -RAMPAGE_FREE_CITY_OR_BETTER    
//             means undefended enemy city
//    -RAMPAGE_HUT_OR_BETTER    
//             means hut
//    RAMPAGE_ANYTHING ... RAMPAGE_HUT_OR_BETTER - 1  
//             is value of enemy unit weaker than our unit
//    0        means nothing found or error
//  Here the minus indicates that you need to enter the target tile (as 
//  opposed to attacking it, which leaves you where you are).
//**************************************************************************/
//static int ai_rampage_want(unit punit, tile ptile)
//{
//  player pplayer = punit.unit_owner();
//  unit pdef = get_defender(punit, ptile);
//
//  CHECK_UNIT(punit);
//  
//  if (pdef) {
//    
//    if (!can_unit_attack_tile(punit, ptile)) {
//      return 0;
//    }
//    
//    {
//      /* See description of kill_desire() about these variables. */
//      int attack = unit_att_rating_now(punit);
//      int benefit = stack_cost(pdef);
//      int loss = Unittype_P.unit_build_shield_cost(punit.type);
//
//      attack *= attack;
//      
//      /* If the victim is in the city/fortress, we correct the benefit
//       * with our health because there could be reprisal attacks.  We
//       * shouldn't send already injured units to useless suicide.
//       * Note that we do not specially encourage attacks against
//       * cities: rampage is a hit-n-run operation. */
//      if (!is_stack_vulnerable(ptile) 
//          && unit_list_size(&(ptile.units)) > 1) {
//        benefit = (benefit * punit.hp) / punit.unit_type().hp;
//      }
//      
//      /* If we have non-zero attack rating... */
//      if (attack > 0 && is_my_turn(punit, pdef)) {
//	double chance = unit_win_chance(punit, pdef);
//	int desire = avg_benefit(benefit, loss, chance);
//
//        /* No need to amortize, our operation takes one turn. */
//	UNIT_LOG(Log.LOG_DEBUG, punit, "Rampage: Desire %d to kill %s(%d,%d)",
//		 desire, Unittype_P.unit_name(pdef.type), TILE_XY(pdef.tile));
//
//        return MAX(0, desire);
//      }
//    }
//    
//  } else {
//    city pcity = Map.map_get_city(ptile);
//    
//    /* No defender... */
//    
//    /* ...and free foreign city waiting for us. Who would resist! */
//    if (pcity && Player_P.pplayers_at_war(pplayer, City.city_owner(pcity))
//        && COULD_OCCUPY(punit)) {
//      
//      return -RAMPAGE_FREE_CITY_OR_BETTER;
//    }
//    
//    /* ...or tiny pleasant hut here! */
//    if (Map.map_has_special(ptile, Terrain_H.S_HUT) && !is_barbarian(pplayer)) {
//      
//      return -RAMPAGE_HUT_OR_BETTER;
//    }
//    
//  }
//  
//  return 0;
//}
//
///*************************************************************************
//  Look for worthy targets within a one-turn horizon.
//*************************************************************************/
//static pf_path find_rampage_target(unit punit, 
//                                           int thresh_adj, int thresh_move)
//{
//  pf_map tgt_map;
//  pf_path path = null;
//  struct pf_parameter parameter;
//  /* Coordinates of the best target (initialize to silence compiler) */
//  tile ptile = punit.tile;
//  /* Want of the best target */
//  int max_want = 0;
//  player pplayer = punit.unit_owner();
// 
//  pft_fill_unit_attack_param(&parameter, punit);
//  
//  tgt_map = pf_create_map(&parameter);
//  while (pf_next(tgt_map)) {
//    struct pf_position pos;
//    int want;
//    boolean move_needed;
//    int thresh;
// 
//    pf_next_get_position(tgt_map, &pos);
//    
//    if (pos.total_MC > punit.moves_left) {
//      /* This is too far */
//      break;
//    }
//
//    if (ai_handicap(pplayer, H_TARGETS) 
//        && !Maphand.map_is_known_and_seen(pos.tile, pplayer)) {
//      /* The target is under fog of war */
//      continue;
//    }
//    
//    want = ai_rampage_want(punit, pos.tile);
//
//    /* Negative want means move needed even though the tiles are adjacent */
//    move_needed = (!is_tiles_adjacent(punit.tile, pos.tile)
//                   || want < 0);
//    /* Select the relevant threshold */
//    thresh = (move_needed ? thresh_move : thresh_adj);
//    want = (want < 0 ? -want : want);
//
//    if (want > max_want && want > thresh) {
//      /* The new want exceeds both the previous maximum 
//       * and the relevant threshold, so it's worth recording */
//      max_want = want;
//      ptile = pos.tile;
//    }
//  }
//
//  if (max_want > 0) {
//    /* We found something */
//    path = pf_get_path(tgt_map, ptile);
//    assert(path != null);
//  }
//
//  pf_destroy_map(tgt_map);
//  
//  return path;
//}
//
///*************************************************************************
//  Find and kill anything reachable within this turn and worth more than 
//  the relevant of the given thresholds until we have run out of juicy 
//  targets or movement.  The first threshold is for attacking which will 
//  leave us where we stand (attacking adjacent units), the second is for 
//  attacking distant (but within reach) targets.
//
//  For example, if unit is a bodyguard on duty, it should call
//    ai_military_rampage(punit, 100, RAMPAGE_FREE_CITY_OR_BETTER)
//  meaning "we will move _only_ to pick up a free city but we are happy to
//  attack adjacent squares as long as they are worthy of it".
//
//  Returns true if survived the rampage session.
//**************************************************************************/
//static boolean ai_military_rampage(unit punit, int thresh_adj, 
//                                int thresh_move)
//{
//  int count = punit.moves_left + 1; /* break any infinite loops */
//  pf_path path = null;
//  
//  CHECK_UNIT(punit);
//
//  assert(thresh_adj <= thresh_move);
//  /* This teaches the AI about the dangers inherent in occupychance. */
//  thresh_adj += ((thresh_move - thresh_adj) * Game.game.occupychance / 100);
//
//  while (count-- > 0 && punit.moves_left > 0
//         && (path = find_rampage_target(punit, thresh_adj, thresh_move))) {
//    if (!ai_unit_execute_path(punit, path)) {
//      /* Died */
//      count = -1;
//    }
//    pf_destroy_path(path);
//    path = null;
//  }
//
//  assert(!path);
//
//  return (count >= 0);
//}
//
///*************************************************************************
//  If we are not covering our charge's ass, go do it now. Also check if we
//  can kick some ass, which is always nice.
//**************************************************************************/
//static void ai_military_bodyguard(player pplayer, unit punit)
//{
//  unit aunit = Player_P.player_find_unit_by_id(pplayer, punit.ai.charge);
//  city acity = Game.find_city_by_id(punit.ai.charge);
//  tile ptile;
//
//  CHECK_UNIT(punit);
//
//  if (aunit && aunit.owner == punit.owner) {
//    /* protect a unit */
//    if (Unit.is_sailing_unit(aunit)) {
//      ptile = aunit.goto_tile;
//    } else {
//      ptile = aunit.tile;
//    }
//  } else if (acity && acity.owner == punit.owner) {
//    /* protect a city */
//    ptile = acity.tile;
//  } else {
//    /* should be impossible */
//    UNIT_LOG(LOGLEVEL_BODYGUARD, punit, "we lost our charge");
//    ai_unit_new_role(punit, AIUNIT_NONE, null);
//    return;
//  }
//
//  if (aunit) {
//    UNIT_LOG(LOGLEVEL_BODYGUARD, punit, "to meet charge %s#%d@(%d,%d){%d}",
//             aunit.unit_type().name, aunit.id, aunit.tile.x,
//	     aunit.tile.y,
//             aunit.ai.bodyguard);
//  } else if (acity) {
//    UNIT_LOG(LOGLEVEL_BODYGUARD, punit, "to guard %s", acity.name);
//  }
//
//  if (!Map.same_pos(punit.tile, ptile)) {
//    if (goto_is_sane(punit, ptile, true)) {
//      () ai_unit_goto(punit, ptile);
//    } else {
//      /* can't possibly get there to help */
//      ai_unit_new_role(punit, AIUNIT_NONE, null);
//    }
//  }
//  /* I had these guys set to just fortify, which is so dumb. -- Syela
//   * Instead we can attack adjacent units and maybe even pick up some free 
//   * cities! */
//  () ai_military_rampage(punit, BODYGUARD_RAMPAGE_THRESHOLD,
//                             RAMPAGE_FREE_CITY_OR_BETTER);
//}
//
///*************************************************************************
//  Tries to find a land tile adjacent to water and to our target 
//  (dest_x, dest_y).  Prefers tiles which are more defensible and/or
//  where we will have moves left.
//  FIXME: It checks if the ocean tile is in our Zone of Control?!
//**************************************************************************/
//boolean find_beachhead(unit punit, tile dest_tile,
//		    tile *beachhead_tile)
//{
//  int ok, best = 0;
//  int t;
//
//  CHECK_UNIT(punit);
//
//  for(tile tile1: util.adjc_tile_iterate(dest_tile)) {
//    ok = 0;
//    t = tile1.terrain;
//    if (WARMAP_SEACOST(tile1) <= 6 * THRESHOLD && !Terrain_H.is_ocean(t)) {
//      /* accessible beachhead */
//      for(tile tile2: util.adjc_tile_iterate(tile1)) {
//	if (Terrain_H.is_ocean(tile2.terrain)
//	    && is_my_zoc(punit.unit_owner(), tile2)) {
//	  ok++;
//	  goto OK;
//
//	  /* FIXME: The above used to be "break; out of adjc_iterate",
//	  but this is incorrect since the current adjc_iterate() is
//	  implemented as two nested loops.  */
//	}
//      }
//    OK:
//
//      if (ok > 0) {
//	/* accessible beachhead with zoc-ok water tile nearby */
//        ok = get_tile_type(t).defense_bonus;
//	if (Map.map_has_special(tile1, Terrain_H.S_RIVER))
//	  ok += (ok * Map.terrain_control.river_defense_bonus) / 100;
//        if (get_tile_type(t).movement_cost * Unit_H.SINGLE_MOVE <
//            punit.move_rate())
//	  ok *= 8;
//        ok += (6 * THRESHOLD - WARMAP_SEACOST(tile1));
//        if (ok > best) {
//	  best = ok;
//	  *beachhead_tile = tile1;
//	}
//      }
//    }
//  }
//
//  return (best > 0);
//}
//
///**************************************************************************
//find_beachhead() works only when city is not further that 1 tile from
//the sea. But Sea Raiders might want to attack cities inland.
//So this finds the nearest land tile on the same continent as the city.
//**************************************************************************/
//static void find_city_beach(city pc, unit punit,
//			    tile *dest_tile)
//{
//  tile best_tile = punit.tile;
//  int dist = 100;
//  int search_dist = Map.real_map_distance(pc.tile, punit.tile) - 1;
//
//  CHECK_UNIT(punit);
//  
//  for(tile tile1: util.square_tile_iterate(punit.tile, search_dist)) {
//    if (map_get_continent(tile1) == map_get_continent(pc.tile)
//        && Map.real_map_distance(punit.tile, tile1) < dist) {
//
//      dist = Map.real_map_distance(punit.tile, tile1);
//      best_tile = tile1;
//    }
//  }
//
//  *dest_tile = best_tile;
//}
//
///*************************************************************************
//  Does the unit with the id given have the flag L_DEFEND_GOOD?
//**************************************************************************/
//static boolean unit_role_defender(int type)
//{
//  if (Unittype_P.unit_types[type].move_type != unit_move_type.LAND_MOVING) {
//    return false; /* temporary kluge */
//  }
//  return (unit_has_role(type, L_DEFEND_GOOD));
//}
//
///*************************************************************************
//  See if we can find something to defend. Called both by wannabe
//  bodyguards and building want estimation code. Returns desirability
//  for using this unit as a bodyguard or for defending a city.
//
//  We do not consider units with higher movement than us, or units that
//  have different move type than us, as potential charges. Nor do we 
//  attempt to bodyguard units with higher defence than us, or military
//  units with higher attack than us.
//
//  Requires an initialized warmap!
//**************************************************************************/
//int look_for_charge(player pplayer, unit punit, 
//                    unit *aunit, city *acity)
//{
//  int dist, def, best = 0;
//  int toughness = unit_def_rating_basic_sq(punit);
//
//  if (toughness == 0) {
//    /* useless */
//    return 0; 
//  }
//
//  /* Unit bodyguard */
//  for (unit buddy : pplayer.units.data) {
//    if (buddy.ai.bodyguard != BODYGUARD_WANTED
//        || !goto_is_sane(punit, buddy.tile, true)
//        || buddy.move_rate() > punit.move_rate()
//        || DEFENCE_POWER(buddy) >= DEFENCE_POWER(punit)
//        || (is_military_unit(buddy) && Unit.get_transporter_capacity(buddy) == 0
//            && ATTACK_POWER(buddy) <= ATTACK_POWER(punit))
//        || buddy.unit_type().move_type != punit.unit_type().move_type) { 
//      continue;
//    }
//    dist = unit_move_turns(punit, buddy.tile);
//    def = (toughness - unit_def_rating_basic_sq(buddy));
//    if (def <= 0) {
//      continue; /* This should hopefully never happen. */
//    }
//    if (Unit.get_transporter_capacity(buddy) == 0) {
//      /* Reduce want based on distance. We can't do this for
//       * transports since they move around all the time, leading
//       * to hillarious flip-flops */
//      def = def >> (dist/2);
//    }
//    if (def > best) { 
//      *aunit = buddy; 
//      best = def; 
//    }
//  } }
//
//  /* City bodyguard */
//  if (punit.unit_type().move_type == unit_move_type.LAND_MOVING) {
//   for (city mycity : pplayer.cities.data) {
//    if (!goto_is_sane(punit, mycity.tile, true)
//        || mycity.ai.urgency == 0) {
//      continue; 
//    }
//    dist = unit_move_turns(punit, mycity.tile);
//    def = (mycity.ai.danger - assess_defense_quadratic(mycity));
//    if (def <= 0) {
//      continue;
//    }
//    def = def >> dist;
//    if (def > best && ai_fuzzy(pplayer, true)) { 
//      *acity = mycity; 
//      best = def; 
//    }
//   } }
//  }
//
//  UNIT_LOG(LOGLEVEL_BODYGUARD, punit, "look_for_charge, best=%d, " +
//           "type=%s(%d,%d)", best * 100 / toughness, *acity ? (*acity).name
//           : (*aunit ? Unittype_P.unit_name((*aunit).type) : ""), 
//           *acity ? (*acity).tile.x : (*aunit
//					      ? (*aunit).tile.y : 0),
//           *acity ? (*acity).tile.x : (*aunit
//					      ? (*aunit).tile.y : 0));
//  
//  return ((best * 100) / toughness);
//}
//

//  Find something to do with a unit. Also, check sanity of existing
//  missions.
//***********************************************************************/
//static void ai_military_findjob(player pplayer,unit punit)
//{
//  city pcity = null, *acity = null;
//  unit aunit;
//  int val, def;
//  int q = 0;
//  unit_type punittype = get_unit_type(punit.type);
//
//  CHECK_UNIT(punit);
//
///* tired of AI abandoning its cities! -- Syela */
//  if (punit.homecity != 0 && (pcity = Game.find_city_by_id(punit.homecity))) {
//    if (pcity.ai.danger != 0) { /* otherwise we can attack */
//      def = assess_defense(pcity);
//      if (Map.same_pos(punit.tile, pcity.tile)) {
//        /* I'm home! */
//        val = assess_defense_unit(pcity, punit, false); 
//        def -= val; /* old bad kluge fixed 980803 -- Syela */
///* only the least defensive unit may leave home */
///* and only if this does not jeopardize the city */
///* def is the defense of the city without punit */
//        if (unit_flag(punit, F_FIELDUNIT)) val = -1;
//        unit_list_iterate((pcity.tile).units, pdef)
//          if (is_military_unit(pdef) 
//              && pdef != punit 
//              && !unit_flag(pdef, F_FIELDUNIT)
//              && pdef.owner == punit.owner) {
//            if (assess_defense_unit(pcity, pdef, false) >= val) val = 0;
//          }
//        } /* was getting confused without the is_military part in */
//        if (unit_def_rating_basic_sq(punit) == 0) {
//          /* thanks, JMT, Paul */
//          q = 0;
//        } else { 
//          /* this was a WAG, but it works, so now it's just good code! 
//           * -- Syela */
//          q = (pcity.ai.danger * 2 
//               - (def * punit.unit_type().attack_strength /
//                  punit.unit_type().defense_strength));
//        }
//        if (val > 0 || q > 0) { /* Guess I better stay */
//          ;
//        } else q = 0;
//      } /* end if home */
//    } /* end if home is in danger */
//  } /* end if we have a home */
//
//  /* keep barbarians aggresive and primitive */
//  if (is_barbarian(pplayer)) {
//    if (can_unit_do_activity(punit, ACTIVITY_PILLAGE)
//	&& Barbarian.is_land_barbarian(pplayer)) {
//      /* land barbarians pillage */
//      ai_unit_new_role(punit, AIUNIT_PILLAGE, null);
//    } else {
//      ai_unit_new_role(punit, AIUNIT_ATTACK, null);
//    }
//    return;
//  }
//
//  if (punit.ai.charge != BODYGUARD_NONE) { /* I am a bodyguard */
//    aunit = Player_P.player_find_unit_by_id(pplayer, punit.ai.charge);
//    acity = Game.find_city_by_id(punit.ai.charge);
//
//    /* Check if city we are on our way to rescue is still in danger,
//     * or unit we should protect is still alive */
//    if ((aunit && aunit.ai.bodyguard != BODYGUARD_NONE 
//         && unit_def_rating_basic(punit) > unit_def_rating_basic(aunit)) 
//        || (acity && acity.owner == punit.owner && acity.ai.urgency != 0 
//            && acity.ai.danger > assess_defense_quadratic(acity))) {
//      assert(punit.ai.ai_role == AIUNIT_ESCORT);
//      return;
//    } else {
//      ai_unit_new_role(punit, AIUNIT_NONE, null);
//    }
//  }
//
//  /* ok, what if I'm somewhere new? - ugly, kludgy code by Syela */
//  if (stay_and_defend(punit)) {
//    UNIT_LOG(Log.LOG_DEBUG, punit, "stays to defend %s",
//             Map.map_get_city(punit.tile).name);
//    return;
//  }
//
//  if (pcity && q > 0 && pcity.ai.urgency > 0) {
//    UNIT_LOG(Log.LOG_DEBUG, punit, "decides to camp at home in %s", pcity.name);
//    ai_unit_new_role(punit, AIUNIT_DEFEND_HOME, pcity.tile);
//    return;
//  }
//
//  /* Is the unit badly damaged? */
//  if ((punit.ai.ai_role == AIUNIT_RECOVER
//       && punit.hp < punittype.hp)
//      || punit.hp < punittype.hp * 0.25) { /* WAG */
//    UNIT_LOG(LOGLEVEL_RECOVERY, punit, "set to hp recovery");
//    ai_unit_new_role(punit, AIUNIT_RECOVER, null);
//    return;
//  }
//
//  /* Make unit a seahunter? */
//  if (punit.ai.ai_role == AIUNIT_HUNTER) {
//    return; /* Continue mission. */
//  }
//  if (ai_hunter_qualify(pplayer, punit)) {
//    UNIT_LOG(LOGLEVEL_HUNT, punit, "is qualified as hunter");
//    if (ai_hunter_findjob(pplayer, punit) > 0) {
//      UNIT_LOG(LOGLEVEL_HUNT, punit, "set as HUNTER");
//      ai_unit_new_role(punit, AIUNIT_HUNTER, null);
//      return;
//    }
//  }
//
///* I'm not 100% sure this is the absolute best place for this... -- Syela */
//  generate_warmap(Map.map_get_city(punit.tile), punit);
///* I need this in order to call unit_move_turns, here and in look_for_charge */
//
//  if (pcity && q > 0) {
//    q *= 100;
//    q /= unit_def_rating_basic_sq(punit);
//    q >>= unit_move_turns(punit, pcity.tile);
//  }
//
//  val = 0; acity = null; aunit = null;
//  if (unit_role_defender(punit.type)) {
//    /* 
//     * This is a defending unit that doesn't need to stay put.
//     * It needs to defend something, but not necessarily where it's at.
//     * Therefore, it will consider becoming a bodyguard. -- Syela 
//     */
//    val = look_for_charge(pplayer, punit, &aunit, &acity);
//  }
//  if (pcity && q > val) {
//    UNIT_LOG(Log.LOG_DEBUG, punit, "decided not to go anywhere, sits in %s",
//             pcity.name);
//    ai_unit_new_role(punit, AIUNIT_DEFEND_HOME, pcity.tile);
//    return;
//  }
//  /* this is bad; riflemen might rather attack if val is low -- Syela */
//  if (acity) {
//    ai_unit_new_role(punit, AIUNIT_ESCORT, acity.tile);
//    punit.ai.charge = acity.id;
//    BODYGUARD_LOG(Log.LOG_DEBUG, punit, "going to defend city");
//  } else if (aunit) {
//    ai_unit_new_role(punit, AIUNIT_ESCORT, aunit.tile);
//    punit.ai.charge = aunit.id;
//    BODYGUARD_LOG(Log.LOG_DEBUG, punit, "going to defend unit");
//  } else if (ai_unit_attack_desirability(punit.type) != 0 ||
//      (pcity && !Map.same_pos(pcity.tile, punit.tile))) {
//     ai_unit_new_role(punit, AIUNIT_ATTACK, null);
//  } else {
//    UNIT_LOG(Log.LOG_DEBUG, punit, "nothing to do, sit where we are");
//    ai_unit_new_role(punit, AIUNIT_DEFEND_HOME, null); /* for default */
//  }
//}
//

//  Send a unit to its homecity.
//***********************************************************************/
//static void ai_military_gohome(player pplayer,unit punit)
//{
//  city pcity = Game.find_city_by_id(punit.homecity);
//
//  if (!pcity) {
//    /* Try to find a place to rest. Sitting duck out in the wilderness
//     * is generally a bad idea, since we protect no cities that way, and
//     * it looks silly. */
//    pcity = find_closest_owned_city(pplayer, punit.tile, false, null);
//  }
//
//  CHECK_UNIT(punit);
//
//  if (pcity) {
//    UNIT_LOG(Log.LOG_DEBUG, punit, "go home to %s(%d,%d)",
//             pcity.name, TILE_XY(pcity.tile)); 
//    if (Map.same_pos(punit.tile, pcity.tile)) {
//      UNIT_LOG(Log.LOG_DEBUG, punit, "go home successful; role AImprovement.I_NONE");
//      ai_unit_new_role(punit, AIUNIT_NONE, null);
//
//      /* aggro defense goes here -- Syela */
//      /* Attack anything that won't kill us */
//      () ai_military_rampage(punit, RAMPAGE_ANYTHING, 
//                                 RAMPAGE_ANYTHING);
//    } else {
//      () ai_gothere(pplayer, punit, pcity.tile);
//    }
//  }
//}
//
///***************************************************************************
// A rough estimate of time (measured in turns) to get to the enemy city, 
// taking into account ferry transfer.
// If boat == null, we will build a boat of type boattype right here, so
// we wouldn't have to walk to it.
//
// Requires ready warmap(s).  Assumes punit is ground or sailing.
//***************************************************************************/
//int turns_to_enemy_city(int our_type,  city acity,
//                        int speed, boolean go_by_boat, 
//                        unit boat, int boattype)
//{
//  switch(Unittype_P.unit_types[our_type].move_type) {
//  case unit_move_type.LAND_MOVING:
//    if (go_by_boat) {
//      int boatspeed = Unittype_P.unit_types[boattype].move_rate;
//      int move_time = (WARMAP_SEACOST(acity.tile)) / boatspeed;
//      
//      if (Unittype_P.unit_type_flag(boattype, F_TRIREME) && move_time > 2) {
//        /* FIXME: Should also check for LIGHTHOUSE */
//        /* Return something prohibitive */
//        return 999;
//      }
//      if (boat) {
//        /* Time to get to the boat */
//        move_time += (WARMAP_COST(boat.tile) + speed - 1) / speed;
//      }
//      
//      if (!Unittype_P.unit_type_flag(our_type, F_MARINES)) {
//        /* Time to get off the boat (Marines do it from the vessel) */
//        move_time += 1;
//      }
//      
//      return move_time;
//    } else {
//      /* We are walking */
//      return (WARMAP_COST(acity.tile) + speed - 1) / speed;
//    }
//  case unit_move_type.SEA_MOVING:
//    /* We are a boat: time to sail */
//    return (WARMAP_SEACOST(acity.tile) + speed - 1) / speed;
//  default: 
//    util.freelog(Log.LOG_ERROR, "ERROR: Unsupported move_type in time_to_enemy_city");
//    /* Return something prohibitive */
//    return 999;
//  }
//
//}
//
///************************************************************************
// Rough estimate of time (in turns) to catch up with the enemy unit.  
// FIXME: Take enemy speed into account in a more sensible way
//
// Requires precomputed warmap.  Assumes punit is ground or sailing. 
//************************************************************************/ 
//int turns_to_enemy_unit(int our_type, int speed, tile ptile,
//                        int enemy_type)
//{
//  int dist;
//
//  switch(Unittype_P.unit_types[our_type].move_type) {
//  case unit_move_type.LAND_MOVING:
//    dist = WARMAP_COST(ptile);
//    break;
//  case unit_move_type.SEA_MOVING:
//    dist = WARMAP_SEACOST(ptile);
//    break;
//  default:
//    /* Compiler warning */
//    dist = 0; 
//    util.freelog(Log.LOG_ERROR, "ERROR: Unsupported unit_type in time_to_enemy_city");
//    /* Return something prohibitive */
//    return 999;
//  }
//
//  /* if dist <= move_rate, we hit the enemy right now */    
//  if (dist > speed) { 
//    /* Weird attempt to take into account enemy running away... */
//    dist *= Unittype_P.unit_types[enemy_type].move_rate;
//    if (Unittype_P.unit_type_flag(enemy_type, F_IGTER)) {
//      dist *= 3;
//    }
//  }
//  
//  return (dist + speed - 1) / speed;
//}
//
///*************************************************************************
//  Mark invasion possibilities of punit in the surrounding cities. The
//  given radius limites the area which is searched for cities. The
//  center of the area is either the unit itself (dest == false) or the
//  destiniation of the current goto (dest == true). The invasion threat
//  is marked in pcity.ai.invasion via ORing the "which" argument (to
//  tell attack from sea apart from ground unit attacks). Note that
//  "which" should only have one bit set.
//
//  If dest == true then a valid goto is presumed.
//**************************************************************************/
//static void invasion_funct(unit punit, boolean dest, int radius,
//			   int which)
//{
//  tile ptile;
//  player pplayer = punit.unit_owner();
//  ai_data ai = ai_data_get(pplayer);
//
//  CHECK_UNIT(punit);
//
//  if (dest) {
//    ptile = punit.goto_tile;
//  } else {
//    ptile = punit.tile;
//  }
//
//  for(tile tile1: util.square_tile_iterate(ptile, radius)) {
//    city pcity = Map.map_get_city(tile1);
//
//    if (pcity
//        && HOSTILE_PLAYER(pplayer, ai, City.city_owner(pcity))
//	&& (pcity.ai.invasion & which) != which
//	&& (dest || !has_defense(pcity))) {
//      pcity.ai.invasion |= which;
//    }
//  }
//}
//
///*************************************************************************
//  Find something to kill! This function is called for units to find 
//  targets to destroy and for cities that want to know if they should
//  build offensive units. Target location returned in (x, y), want as
//  function return value.
//
//  punit.id == 0 means that the unit is virtual (considered to be built).
//**************************************************************************/
//int find_something_to_kill(player pplayer, unit punit,
//			   tile *dest_tile)
//{
//  ai_data ai = ai_data_get(pplayer);
//  /* basic attack */
//  int attack_value = unit_att_rating(punit);
//  /* Enemy defence rating */
//  int vuln;
//  /* Benefit from killing the target */
//  int benefit;
//  /* Number of enemies there */
//  int victim_count;
//  /* Want (amortized) of the operaton */
//  int want;
//  /* Best of all wants */
//  int best = 0;
//  /* Our total attack value with reinforcements */
//  int attack;
//  int move_time, move_rate;
//  Continent_id con = map_get_continent(punit.tile);
//  unit pdef;
//  int maxd, needferry;
//  /* Do we have access to sea? */
//  boolean harbor = false;
//  tile best_tile = null;
//  /* Build cost of the attacker (+adjustments) */
//  int bcost, bcost_bal;
//  boolean handicap = ai_handicap(pplayer, H_TARGETS);
//  unit ferryboat = null;
//  /* Type of our boat (a future one if ferryboat == null) */
//  int boattype = unittype.U_LAST;
//  boolean unhap = false;
//  city pcity;
//  /* this is a kluge, because if we don't set x and y with !punit.id,
//   * p_a_w isn't called, and we end up not wanting ironclads and therefore 
//   * never learning steam engine, even though ironclads would be very 
//   * useful. -- Syela */
//  int bk = 0; 
//
//  /*** Very preliminary checks */
//  *dest_tile = punit.tile;
//
//  if (!is_ground_unit(punit) && !Unit.is_sailing_unit(punit)) {
//    /* Don't know what to do with them! */
//    return 0;
//  }
//
//  if (attack_value == 0) {
//    /* A very poor attacker... */
//    return 0;
//  }
//
//  /*** Part 1: Calculate targets ***/
//  /* This horrible piece of code attempts to calculate the attractiveness of
//   * enemy cities as targets for our units, by checking how many units are
//   * going towards it or are near it already.
//   */
//
//  /* First calculate in nearby units */
//  for(player aplayer: Game.game.players){
//    /* See comment below in next usage of HOSTILE_PLAYER. */
//    if ((punit.id == 0 && !HOSTILE_PLAYER(pplayer, ai, aplayer))
//        || (punit.id != 0 && !Player_P.pplayers_at_war(pplayer, aplayer))) {
//      continue;
//    }
//    for (city acity : aplayer.cities.data) {
//      reinforcements_cost_and_value(punit, acity.tile,
//                                    &acity.ai.attack, &acity.ai.bcost);
//      acity.ai.invasion = 0;
//    } }
//  }
//
//  /* Second, calculate in units on their way there, and mark targets for
//   * invasion */
//  for (unit aunit : pplayer.units.data) {
//    if (aunit == punit) {
//      continue;
//    }
//
//    /* dealing with invasion stuff */
//    if (IS_ATTACKER(aunit)) {
//      if (aunit.activity == unit_activity.ACTIVITY_GOTO) {
//        invasion_funct(aunit, true, 0, (COULD_OCCUPY(aunit) ? 1 : 2));
//        if ((pcity = Map.map_get_city(aunit.goto_tile))) {
//          pcity.ai.attack += unit_att_rating(aunit);
//          pcity.ai.bcost += Unittype_P.unit_build_shield_cost(aunit.type);
//        } 
//      }
//      invasion_funct(aunit, false, aunit.move_rate() / Unit_H.SINGLE_MOVE,
//                     (COULD_OCCUPY(aunit) ? 1 : 2));
//    } else if (aunit.ai.passenger != 0 &&
//               !Map.same_pos(aunit.tile, punit.tile)) {
//      /* It's a transport with reinforcements */
//      if (aunit.activity == unit_activity.ACTIVITY_GOTO) {
//        invasion_funct(aunit, true, 1, 1);
//      }
//      invasion_funct(aunit, false, 2, 1);
//    }
//  } }
//  /* end horrible initialization subroutine */
//
//  /*** Part 2: Now pick one target ***/
//  /* We first iterate through all cities, then all units, looking
//   * for a viable target. We also try to gang up on the target
//   * to avoid spreading out attacks too widely to be inefficient.
//   */
//
//  pcity = Map.map_get_city(punit.tile);
//
//  if (pcity && (punit.id == 0 || pcity.id == punit.homecity)) {
//    /* I would have thought unhappiness should be taken into account 
//     * irrespectfully the city in which it will surface...  GB */ 
//    unhap = ai_assess_military_unhappiness(pcity, get_gov_pplayer(pplayer));
//  }
//
//  move_rate = punit.move_rate();
//  if (unit_flag(punit, F_IGTER)) {
//    move_rate *= 3;
//  }
//
//  maxd = Math.min(6, move_rate) * THRESHOLD + 1;
//
//  bcost = Unittype_P.unit_build_shield_cost(punit.type);
//  bcost_bal = build_cost_balanced(punit.type);
//
//  /* most flexible but costs milliseconds */
//  generate_warmap(Map.map_get_city(*dest_tile), punit);
//
//  if (is_ground_unit(punit)) {
//    int boatid = find_boat(pplayer, &best_tile, 2);
//    ferryboat = Player_P.player_find_unit_by_id(pplayer, boatid);
//  }
//
//  if (ferryboat) {
//    boattype = ferryboat.type;
//    really_generate_warmap(Map.map_get_city(ferryboat.tile),
//                           ferryboat, unit_move_type.SEA_MOVING);
//  } else {
//    boattype = best_role_unit_for_player(pplayer, L_FERRYBOAT);
//    if (boattype == unittype.U_LAST) {
//      /* We pretend that we can have the simplest boat -- to stimulate tech */
//      boattype = Unittype_P.get_role_unit(L_FERRYBOAT, 0);
//    }
//  }
//
//  if (is_ground_unit(punit) && punit.id == 0 
//      && Terrain.is_terrain_flag_near_tile(punit.tile)) {
//    harbor = true;
//  }
//
//  for(player aplayer: Game.game.players){
//    /* For the virtual unit case, which is when we are called to evaluate
//     * which units to build, we want to calculate in danger and which
//     * players we want to make war with in the future. We do _not_ want
//     * to do this when actually making attacks. */
//    if ((punit.id == 0 && !HOSTILE_PLAYER(pplayer, ai, aplayer))
//        || (punit.id != 0 && !Player_P.pplayers_at_war(pplayer, aplayer))) {
//      /* Not an enemy */
//      continue;
//    }
//
//    for (city acity : aplayer.cities.data) {
//      boolean go_by_boat = (is_ground_unit(punit)
//                         && !(goto_is_sane(punit, acity.tile, true) 
//                              && WARMAP_COST(acity.tile) < maxd));
//
//      if (!Terrain_H.is_ocean(acity.tile.terrain)
//          && unit_flag(punit, F_NO_LAND_ATTACK)) {
//        /* Can't attack this city. It is on land. */
//        continue;
//      }
//
//      if (handicap && !Maphand.map_is_known(acity.tile, pplayer)) {
//        /* Can't see it */
//        continue;
//      }
//
//      if (go_by_boat 
//          && (!(ferryboat || harbor)
//              || WARMAP_SEACOST(acity.tile) > 6 * THRESHOLD)) {
//        /* Too far or impossible to go by boat */
//        continue;
//      }
//      
//      if (Unit.is_sailing_unit(punit) 
//          && WARMAP_SEACOST(acity.tile) >= maxd) {
//        /* Too far to sail */
//        continue;
//      }
//      
//      if ((pdef = get_defender(punit, acity.tile))) {
//        vuln = unit_def_rating_sq(punit, pdef);
//        benefit = Unittype_P.unit_build_shield_cost(pdef.type);
//      } else { 
//        vuln = 0; 
//        benefit = 0; 
//      }
//      
//      move_time = turns_to_enemy_city(punit.type, acity, move_rate, 
//                                      go_by_boat, ferryboat, boattype);
//
//      if (move_time > 1) {
//        int def_type = ai_choose_defender_versus(acity, punit.type);
//        int v = unittype_def_rating_sq(punit.type, def_type,
//                                       acity.tile, false,
//                                       do_make_unit_veteran(acity, def_type));
//        if (v > vuln) {
//          /* They can build a better defender! */ 
//          vuln = v; 
//          benefit = Unittype_P.unit_build_shield_cost(def_type); 
//        }
//      }
//
//      if (COULD_OCCUPY(punit) || TEST_BIT(acity.ai.invasion, 0)) {
//        /* There are units able to occupy the city! */
//        benefit += 40;
//      }
//
//      attack = (attack_value + acity.ai.attack) 
//        * (attack_value + acity.ai.attack);
//      /* Avoiding handling upkeep aggregation this way -- Syela */
//      
//      /* AI was not sending enough reinforcements to totally wipe out a city
//       * and conquer it in one turn.  
//       * This variable enables total carnage. -- Syela */
//      victim_count 
//        = unit_list_size(&((acity.tile).units)) + 1;
//
//      if (!COULD_OCCUPY(punit) && !pdef) {
//        /* Nothing there to bash and we can't occupy! 
//         * Not having this check caused warships yoyoing */
//        want = 0;
//      } else if (move_time > THRESHOLD) {
//        /* Too far! */
//        want = 0;
//      } else if (COULD_OCCUPY(punit) && acity.ai.invasion == 2) {
//        /* Units able to occupy really needed there! */
//        want = bcost * SHIELD_WEIGHTING;
//      } else {
//        int a_squared = acity.ai.attack * acity.ai.attack;
//        
//        want = kill_desire(benefit, attack, (bcost + acity.ai.bcost), 
//                           vuln, victim_count);
//        if (benefit * a_squared > acity.ai.bcost * vuln) {
//          /* If there're enough units to do the job, we don't need this
//           * one. */
//          /* FIXME: The problem with ai.bcost is that bigger it is, less is
//           * our desire to go help other units.  Now suppose we need five
//           * cavalries to take over a city, we have four (which is not
//           * enough), then we will be severely discouraged to build the
//           * fifth one.  Where is logic in this??!?! --GB */
//          want -= kill_desire(benefit, a_squared, acity.ai.bcost, 
//                              vuln, victim_count);
//        }
//      }
//      want -= move_time * (unhap ? SHIELD_WEIGHTING + 2 * TRADE_WEIGHTING 
//                           : SHIELD_WEIGHTING);
//      /* build_cost of ferry */
//      needferry = (go_by_boat && !ferryboat
//		   ? Unittype_P.unit_build_shield_cost(boattype) : 0);
//      /* FIXME: add time to build the ferry? */
//      want = military_amortize(pplayer, Game.find_city_by_id(punit.homecity),
//                               want, MAX(1, move_time),
//			       bcost_bal + needferry);
//
//      /* BEGIN STEAM-ENGINES-ARE-OUR-FRIENDS KLUGE */
//      if (want <= 0 && punit.id == 0 && best == 0) {
//        int bk_e = military_amortize(pplayer,
//				     Game.find_city_by_id(punit.homecity),
//                                     benefit * SHIELD_WEIGHTING, 
//                                     MAX(1, move_time),
//				     bcost_bal + needferry);
//
//        if (bk_e > bk) {
//	  *dest_tile = acity.tile;
//          bk = bk_e;
//        }
//      }
//      /* END STEAM-ENGINES KLUGE */
//      
//      if (punit.id != 0 && ferryboat && is_ground_unit(punit)) {
//        UNIT_LOG(Log.LOG_DEBUG, punit, "in fstk with boat %s@(%d, %d) . %s@(%d, %d)" +
//                 " (go_by_boat=%d, move_time=%d, want=%d, best=%d)",
//                 ferryboat.unit_type().name, best_tile.x, best_tile.y,
//                 acity.name, TILE_XY(acity.tile), 
//                 go_by_boat, move_time, want, best);
//      }
//      
//      if (want > best && ai_fuzzy(pplayer, true)) {
//        /* Yes, we like this target */
//        if (punit.id != 0 && is_ground_unit(punit) 
//            && !unit_flag(punit, F_MARINES)
//            && map_get_continent(acity.tile) != con) {
//          /* a non-virtual ground unit is trying to attack something on 
//           * another continent.  Need a beachhead which is adjacent 
//           * to the city and an available ocean tile */
//	  tile btile;
//
//          if (find_beachhead(punit, acity.tile, &btile)) { 
//            best = want;
//	    *dest_tile = acity.tile;
//            /* the ferryboat needs to target the beachhead, but the unit 
//             * needs to target the city itself.  This is a little weird, 
//             * but it's the best we can do. -- Syela */
//          } /* else do nothing, since we have no beachhead */
//        } else {
//          best = want;
//	  *dest_tile = acity.tile;
//        } /* end need-beachhead else */
//      }
//    } }
//
//    attack = unit_att_rating_sq(punit);
//    /* I'm not sure the following code is good but it seems to be adequate. 
//     * I am deliberately not adding ferryboat code to the unit_list_iterate. 
//     * -- Syela */
//    for (unit aunit : aplayer.units.data) {
//      if (Map.map_get_city(aunit.tile)) {
//        /* already dealt with it */
//        continue;
//      }
//
//      if (handicap && !Maphand.map_is_known(aunit.tile, pplayer)) {
//        /* Can't see the target */
//        continue;
//      }
//
//      if ((unit_flag(aunit, F_HELP_WONDER) || unit_flag(aunit, F_TRADE_ROUTE))
//          && punit.id == 0) {
//        /* We will not build units just to chase caravans */
//        continue;
//      }
//
//      /* We have to assume the attack is diplomatically ok.
//       * We cannot use can_player_attack_tile, because we might not
//       * be at war with aplayer yet */
//      if (!can_unit_attack_all_at_tile(punit, aunit.tile)
//          || !(aunit == get_defender(punit, aunit.tile))) {
//        /* We cannot attack it, or it is not the main defender. */
//        continue;
//      }
//
//      if (is_ground_unit(punit) 
//          && (map_get_continent(aunit.tile) != con 
//              || WARMAP_COST(aunit.tile) >= maxd)) {
//        /* Impossible or too far to walk */
//        continue;
//      }
//
//      if (Unit.is_sailing_unit(punit)
//          && (!goto_is_sane(punit, aunit.tile, true)
//              || WARMAP_SEACOST(aunit.tile) >= maxd)) {
//        /* Impossible or too far to sail */
//        continue;
//      }
//
//      vuln = unit_def_rating_sq(punit, aunit);
//      benefit = Unittype_P.unit_build_shield_cost(aunit.type);
// 
//      move_time = turns_to_enemy_unit(punit.type, move_rate, 
//                                      aunit.tile, aunit.type);
//
//      if (move_time > THRESHOLD) {
//        /* Too far */
//        want = 0;
//      } else {
//        want = kill_desire(benefit, attack, bcost, vuln, 1);
//          /* Take into account maintainance of the unit */
//          /* FIXME: Depends on the government */
//        want -= move_time * SHIELD_WEIGHTING;
//        /* Take into account unhappiness 
//         * (costs 2 luxuries to compensate) */
//        want -= (unhap ? 2 * move_time * TRADE_WEIGHTING : 0);
//      }
//      want = military_amortize(pplayer, Game.find_city_by_id(punit.homecity),
//                               want, MAX(1, move_time), bcost_bal);
//      if (want > best && ai_fuzzy(pplayer, true)) {
//        best = want;
//	*dest_tile = aunit.tile;
//      }
//    } }
//  }
//
//  return(best);
//}
//
///**********************************************************************
//  Find safe city to recover in. An allied player's city is just as 
//  good as one of our own, since both replenish our hitpoints and
//  reduce unhappiness.
//
//  TODO: Actually check how safe the city is. This is a difficult
//  decision not easily taken, since we also want to protect unsafe
//  cities, at least most of the time.
//***********************************************************************/
//city find_nearest_safe_city(unit punit)
//{
//  player pplayer = punit.unit_owner();
//  city acity = null;
//  int best = 6 * THRESHOLD + 1, cur;
//  boolean ground = is_ground_unit(punit);
//
//  CHECK_UNIT(punit);
//
//  generate_warmap(Map.map_get_city(punit.tile), punit);
//  for(player aplayer: Game.game.players){
//    if (Player_P.pplayers_allied(pplayer,aplayer)) {
//      for (city pcity : aplayer.cities.data) {
//        if (ground) {
//          cur = WARMAP_COST(pcity.tile);
//          if (Effects.get_city_bonus(pcity, EFT_LAND_REGEN) > 0) {
//            cur /= 3;
//          }
//        } else {
//          cur = WARMAP_SEACOST(pcity.tile);
//          if (Effects.get_city_bonus(pcity, EFT_SEA_REGEN) > 0) {
//            cur /= 3;
//          }
//        }
//        if (cur < best) {
//          best = cur;
//          acity = pcity;
//        }
//      } }
//    }
//  }
//  if (best > 6 * THRESHOLD) {
//    return null;
//  }
//  return acity;
//}
//
///*************************************************************************
//  This does the attack until we have used up all our movement, unless we
//  should safeguard a city.  First we rampage nearby, then we go
//  looking for trouble elsewhere. If there is nothing to kill, sailing units 
//  go home, others explore while barbs go berserk.
//**************************************************************************/
//static void ai_military_attack(player pplayer, unit punit)
//{
//  tile dest_tile;
//  int id = punit.id;
//  int ct = 10;
//  city pcity = null;
//
//  CHECK_UNIT(punit);
//
//  /* First find easy nearby enemies, anything better than pillage goes.
//   * NB: We do not need to repeat ai_military_rampage, it is repeats itself
//   * until it runs out of targets. */
//  /* FIXME: 1. ai_military_rampage never checks if it should defend newly
//   * conquered cities.
//   * FIXME: 2. would be more convenient if it returned false if we run out 
//   * of moves too.*/
//  if (!ai_military_rampage(punit, RAMPAGE_ANYTHING, RAMPAGE_ANYTHING)) {
//    return; /* we util.died */
//  }
//  
//  if (punit.moves_left <= 0) {
//    return;
//  }
//
//  /* Main attack loop */
//  do {
//    if (stay_and_defend(punit)) {
//      /* This city needs defending, don't go outside! */
//      UNIT_LOG(Log.LOG_DEBUG, punit, "stayed to defend %s", 
//               Map.map_get_city(punit.tile).name);
//      return;
//    }
//
//    /* Then find enemies the hard way */
//    find_something_to_kill(pplayer, punit, &dest_tile);
//    if (!Map.same_pos(punit.tile, dest_tile)) {
//
//      if (!is_tiles_adjacent(punit.tile, dest_tile)
//          || !can_unit_attack_tile(punit, dest_tile)) {
//        /* Adjacent and can't attack usually means we are not marines
//         * and on a ferry. This fixes the problem (usually). */
//        UNIT_LOG(Log.LOG_DEBUG, punit, "mil att gothere . (%d,%d)", 
//                 dest_tile.x, dest_tile.y);
//        if (!ai_gothere(pplayer, punit, dest_tile)) {
//          /* Died or got stuck */
//          return;
//        }
//        if (punit.moves_left <= 0) {
//          return;
//        }
//        /* Either we're adjacent or we sitting on the tile. We might be
//         * sitting on the tile if the enemy that _was_ sitting there 
//         * attacked us and util.died _and_ we had enough movement to get there */
//        if (Map.same_pos(punit.tile, dest_tile)) {
//          UNIT_LOG(Log.LOG_DEBUG, punit, "mil att made it . (%d,%d)",
//                 dest_tile.x, dest_tile.y);
//          break;
//        }
//      }
//      
//      /* Close combat. fstk sometimes want us to attack an adjacent
//       * enemy that rampage wouldn't */
//      UNIT_LOG(Log.LOG_DEBUG, punit, "mil att bash . %d, %d",
//	       dest_tile.x, dest_tile.y);
//      if (!ai_unit_attack(punit, dest_tile)) {
//        /* Died */
//          return;
//      }
//
//    } else {
//      /* FIXME: This happens a bit too often! */
//      UNIT_LOG(Log.LOG_DEBUG, punit, "fstk didn't find us a worthy target!");
//      /* No worthy enemies found, so abort loop */
//      ct = 0;
//    }
//
//    ct--; /* infinite loops from railroads must be stopped */
//  } while (punit.moves_left > 0 && ct > 0);
//
//  /* Cleanup phase */
//  if (punit.moves_left == 0) {
//    return;
//  }
//  pcity = find_nearest_safe_city(punit);
//  if (Unit.is_sailing_unit(punit) && pcity) {
//    /* Sail somewhere */
//    () ai_unit_goto(punit, pcity.tile);
//  } else if (!is_barbarian(pplayer)) {
//    /* Nothing else to do, so try exploring. */
//    if (ai_manage_explorer(punit)) {
//      UNIT_LOG(Log.LOG_DEBUG, punit, "nothing else to do, so exploring");
//    } else if (Game.find_unit_by_id(id)) {
//      UNIT_LOG(Log.LOG_DEBUG, punit, "nothing to do - no more exploring either");
//    }
//  } else {
//    /* You can still have some moves left here, but barbarians should
//       not sit helplessly, but advance towards nearest known enemy city */
//    city pc;
//    tile ftile;
//
//    if ((pc = Aitools.dist_nearest_city(pplayer, punit.tile, false, true))) {
//      if (!Terrain_H.is_ocean(punit.tile.terrain)) {
//        UNIT_LOG(Log.LOG_DEBUG, punit, "Barbarian marching to conquer %s", pc.name);
//        () ai_gothere(pplayer, punit, pc.tile);
//      } else {
//        /* sometimes find_beachhead is not enough */
//        if (!find_beachhead(punit, pc.tile, &ftile)) {
//          find_city_beach(pc, punit, &ftile);
//        }
//        UNIT_LOG(Log.LOG_DEBUG, punit, "Barbarian sailing to %s", pc.name);
//        () ai_gothere(pplayer, punit, ftile);
//      }
//    }
//  }
//  if ((punit = Game.find_unit_by_id(id)) && punit.moves_left > 0) {
//    city pcity = Map.map_get_city(punit.tile);
//
//    if (pcity) {
//      ai_unit_new_role(punit, AIUNIT_DEFEND_HOME, pcity.tile);
//      /* FIXME: Send unit to nearest city needing more defence */
//      UNIT_LOG(Log.LOG_DEBUG, punit, "could not find work, sitting duck");
//    } else {
//      /* Going home */
//      UNIT_LOG(Log.LOG_DEBUG, punit, "sent home");
//      /* FIXME: Rehome & send us to nearest city needing more defence */
//      ai_military_gohome(pplayer, punit);
//    }
//  }
//}
//
///*************************************************************************
//  Use caravans for building wonders, or send caravans to establish
//  trade with a city on the same continent, owned by yourself or an
//  ally.
//**************************************************************************/
//static void ai_manage_caravan(player pplayer, unit punit)
//{
//  city pcity;
//  int tradeval, best_city = -1, best=0;
//  ai_data ai = ai_data_get(pplayer);
//
//  CHECK_UNIT(punit);
//
//  if (punit.ai.ai_role == AIUNIT_NONE) {
//    if ((pcity = wonder_on_continent(pplayer, 
//                                     map_get_continent(punit.tile))) 
//        && unit_flag(punit, F_HELP_WONDER)
//        && build_points_left(pcity) > (pcity.shield_surplus * 2)) {
//      if (!Map.same_pos(pcity.tile, punit.tile)) {
//        if (punit.moves_left == 0) {
//          return;
//        }
//        () ai_unit_goto(punit, pcity.tile);
//      } else {
//        /*
//         * We really don't want to just drop all caravans in immediately.
//         * Instead, we want to aggregate enough caravans to build instantly.
//         * -AJS, 990704
//         */
//	handle_unit_help_build_wonder(pplayer, punit.id);
//      }
//    } else {
//       /* A caravan without a home?  Kinda strange, but it might happen.  */
//       pcity=Player_P.player_find_city_by_id(pplayer, punit.homecity);
//       for(player aplayer: Game.game.players){
//         if (HOSTILE_PLAYER(pplayer, ai, aplayer)) {
//           continue;
//         }
//         city_list_iterate(pplayer.cities,pdest) {
//           if (pcity
//	       && City.can_cities_trade(pcity, pdest)
//	       && City.can_establish_trade_route(pcity, pdest)
//               && map_get_continent(pcity.tile) 
//                                == map_get_continent(pdest.tile)) {
//             tradeval=trade_between_cities(pcity, pdest);
//             if (tradeval != 0) {
//               if (best < tradeval) {
//                 best=tradeval;
//                 best_city=pdest.id;
//               }
//             }
//           }
//         } }
//       }
//       pcity = Player_P.player_find_city_by_id(pplayer, best_city);
//
//       if (pcity) {
//         if (!Map.same_pos(pcity.tile, punit.tile)) {
//           if (punit.moves_left == 0) {
//             return;
//           }
//           () ai_unit_goto(punit, pcity.tile);
//         } else {
//           handle_unit_establish_trade(pplayer, punit.id);
//        }
//      }
//    }
//  }
//}
//
///*************************************************************************
// This function goes wait a unit in a city for the hitpoints to recover. 
// If something is attacking our city, kill it yeahhh!!!.
//**************************************************************************/
//static void ai_manage_hitpoint_recovery(unit punit)
//{
//  player pplayer = punit.unit_owner();
//  city pcity = Map.map_get_city(punit.tile);
//  city safe = null;
//  unit_type punittype = get_unit_type(punit.type);
//
//  CHECK_UNIT(punit);
//
//  if (pcity) {
//    /* rest in city until the hitpoints are recovered, but attempt
//       to protect city from attack (and be opportunistic too)*/
//    if (ai_military_rampage(punit, RAMPAGE_ANYTHING, 
//                            RAMPAGE_FREE_CITY_OR_BETTER)) {
//      UNIT_LOG(LOGLEVEL_RECOVERY, punit, "recovering hit points.");
//    } else {
//      return; /* we util.died heroically defending our city */
//    }
//  } else {
//    /* goto to nearest city to recover hit points */
//    /* just before, check to see if we can occupy an undefended enemy city */
//    if (!ai_military_rampage(punit, RAMPAGE_FREE_CITY_OR_BETTER, 
//                             RAMPAGE_FREE_CITY_OR_BETTER)) { 
//      return; /* oops, we util.died */
//    }
//
//    /* find city to stay and go there */
//    safe = find_nearest_safe_city(punit);
//    if (safe) {
//      UNIT_LOG(LOGLEVEL_RECOVERY, punit, "going to %s to recover", safe.name);
//      if (!ai_unit_goto(punit, safe.tile)) {
//        util.freelog(LOGLEVEL_RECOVERY, "util.died trying to hide and recover");
//        return;
//      }
//    } else {
//      /* oops */
//      UNIT_LOG(LOGLEVEL_RECOVERY, punit, "didn't find a city to recover in!");
//      ai_unit_new_role(punit, AIUNIT_NONE, null);
//      ai_military_attack(pplayer, punit);
//      return;
//    }
//  }
//
//  /* is the unit still damaged? if true recover hit points, if not idle */
//  if (punit.hp == punittype.hp) {
//    /* we are ready to go out and kick ass again */
//    UNIT_LOG(LOGLEVEL_RECOVERY, punit, "ready to kick ass again!");
//    ai_unit_new_role(punit, AIUNIT_NONE, null);  
//    return;
//  }
//}
//
///**************************************************************************
//  Decide what to do with a military unit. It will be managed once only.
//  It is up to the caller to try again if it has moves left.
//**************************************************************************/
//void ai_manage_military(player pplayer, unit punit)
//{
//  int id = punit.id;
//
//  CHECK_UNIT(punit);
//
//  /* "Escorting" aircraft should not do anything. They are activated
//   * by their transport or charge. */
//  if (punit.ai.ai_role == AIUNIT_ESCORT && is_air_unit(punit)) {
//    return;
//  }
//
//  if ((punit.activity == ACTIVITY_SENTRY
//       || punit.activity == ACTIVITY_FORTIFIED)
//      && ai_handicap(pplayer, H_AWAY)) {
//    /* Don't move sentried or fortified units controlled by a player
//     * in away mode. */
//    return;
//  }
//
//  /* Since military units re-evaluate their actions every turn,
//     we must make sure that previously reserved ferry is freed. */
//  aiferry_clear_boat(punit);
//
//  ai_military_findjob(pplayer, punit);
//
//  switch (punit.ai.ai_role) {
//  case AIUNIT_AUTO_SETTLER:
//  case AIUNIT_BUILD_CITY:
//    ai_unit_new_role(punit, AIUNIT_NONE, null);
//    break;
//  case AIUNIT_DEFEND_HOME:
//    ai_military_gohome(pplayer, punit);
//    break;
//  case AIUNIT_ATTACK:
//    ai_military_attack(pplayer, punit);
//    break;
//  case AIUNIT_FORTIFY:
//    ai_military_gohome(pplayer, punit);
//    break;
//  case AIUNIT_RUNAWAY: 
//    break;
//  case AIUNIT_ESCORT: 
//    ai_military_bodyguard(pplayer, punit);
//    break;
//  case AIUNIT_PILLAGE:
//    Unithand.handle_unit_activity_request(punit, ACTIVITY_PILLAGE);
//    return; /* when you pillage, you have moves left, avoid later fortify */
//  case AIUNIT_EXPLORE:
//    () ai_manage_explorer(punit);
//    break;
//  case AIUNIT_RECOVER:
//    ai_manage_hitpoint_recovery(punit);
//    break;
//  case AIUNIT_HUNTER:
//    ai_hunter_manage(pplayer, punit);
//    break;
//  default:
//    assert(false);
//  }
//
//  /* If we are still alive, either sentry or fortify. */
//  if ((punit = Game.find_unit_by_id(id))) {
//    if (unit_list_find(&((punit.tile).units),
//        punit.ai.ferryboat)) {
//      Unithand.handle_unit_activity_request(punit, ACTIVITY_SENTRY);
//    } else if (punit.activity == unit_activity.ACTIVITY_IDLE) {
//      Unithand.handle_unit_activity_request(punit, ACTIVITY_FORTIFYING);
//    }
//  }
//}
//
///**************************************************************************
//  Barbarian units may disband spontaneously if their age is more then 5,
//  they are not in cities, and they are far from any enemy units. It is to 
//  remove barbarians that do not engage into any activity for a long time.
//**************************************************************************/
//static boolean unit_can_be_retired(unit punit)
//{
//  if (punit.fuel > 0) {	/* fuel abused for barbarian life span */
//    punit.fuel--;
//    return false;
//  }
//
//  if (City.is_allied_city_tile
//      ((punit.tile), punit.unit_owner())) return false;
//
//  /* check if there is enemy nearby */
//  for(tile ptile: util.square_tile_iterate(punit.tile, 3)) {
//    if (is_enemy_city_tile(ptile, punit.unit_owner()) ||
//	Unit.is_enemy_unit_tile(ptile, punit.unit_owner()))
//      return false;
//  }
//  square_iterate_end;
//
//  return true;
//}
//
///**************************************************************************
// manage one unit
// Careful: punit may have been destroyed upon return from this routine!
//
// Gregor: This function is a very limited approach because if a unit has
// several flags the first one in order of appearance in this function
// will be used.
//**************************************************************************/
//void ai_manage_unit(player pplayer, unit punit)
//{
//  unit bodyguard = Game.find_unit_by_id(punit.ai.bodyguard);
//
//  CHECK_UNIT(punit);
//
//  /* Don't manage the unit if it is under human orders. */
//  if (unit_has_orders(punit)) {
//    punit.ai.ai_role = AIUNIT_NONE;
//    return;
//  }
//
//  /* retire useless barbarian units here, before calling the management
//     function */
//  if( is_barbarian(pplayer) ) {
//    /* Todo: should be configurable */
//    if( unit_can_be_retired(punit) && Rand.myrand(100) > 90 ) {
//      Unittools.wipe_unit(punit);
//      return;
//    }
//    if( !is_military_unit(punit)
//	&& !unit_has_role(punit.type, unit_role_id.L_BARBARIAN_LEADER)) {
//      util.freelog(Log.LOG_VERBOSE, "Barbarians picked up non-military unit.");
//      return;
//    }
//  }
//
//  /* Check if we have lost our bodyguard. If we never had one, all
//   * fine. If we had one and lost it, ask for a new one. */
//  if (!bodyguard && punit.ai.bodyguard > BODYGUARD_NONE) {
//    UNIT_LOG(LOGLEVEL_BODYGUARD, punit, "lost bodyguard, asking for new");
//    punit.ai.bodyguard = BODYGUARD_WANTED;
//  }  
//
//  if (punit.moves_left <= 0) {
//    /* Can do nothing */
//    return;
//  }
//
//  if ((unit_flag(punit, Eunit_flag_id.F_DIPLOMAT))
//      || (unit_flag(punit, F_SPY))) {
//    ai_manage_diplomat(pplayer, punit);
//    return;
//  } else if (unit_flag(punit, Eunit_flag_id.F_SETTLERS)
//	     ||unit_flag(punit, Eunit_flag_id.F_CITIES)) {
//    ai_manage_settler(pplayer, punit);
//    return;
//  } else if (unit_flag(punit, F_TRADE_ROUTE)
//             || unit_flag(punit, F_HELP_WONDER)) {
//    ai_manage_caravan(pplayer, punit);
//    return;
//  } else if (unit_has_role(punit.type, unit_role_id.L_BARBARIAN_LEADER)) {
//    ai_manage_barbarian_leader(pplayer, punit);
//    return;
//  } else if (Unit.get_transporter_capacity(punit) > 0
//             && !unit_flag(punit, F_MISSILE_CARRIER)
//             && punit.ai.ai_role != AIUNIT_HUNTER) {
//    ai_manage_ferryboat(pplayer, punit);
//    return;
//  } else if (is_air_unit(punit)
//             && punit.ai.ai_role != AIUNIT_ESCORT) {
//    ai_manage_airunit(pplayer, punit);
//    return;
//  } else if (is_heli_unit(punit)) {
//    /* TODO: We can try using air-unit code for helicopters, just
//     * pretend they have fuel = HP / 3 or something. */
//    return;
//  } else if (is_military_unit(punit)) {
//    ai_manage_military(pplayer,punit); 
//    return;
//  } else {
//    int id = punit.id;
//    /* what else could this be? -- Syela */
//    if (!ai_manage_explorer(punit)
//        && Game.find_unit_by_id(id)) {
//      ai_unit_new_role(punit, AIUNIT_DEFEND_HOME, null);
//      ai_military_gohome(pplayer, punit);
//    }
//    return;
//  }
//}
//
///**************************************************************************
//  Master manage unit function.
//**************************************************************************/
//void ai_manage_units(player pplayer) 
//{
//  ai_airlift(pplayer);
//  for (unit punit : pplayer.units.data) {
//    ai_manage_unit(pplayer, punit);
//  }
//  /* Sometimes units wait for other units to move so we crudely
//   * solve it by moving everything again */ 
//  for (unit punit : pplayer.units.data) {
//    ai_manage_unit(pplayer, punit);
//  }
//}
//
///**************************************************************************
// Assign tech wants for techs to get better units with given role/flag.
// Returns the best we can build so far, or unittype.U_LAST if none.  (dwp)
//**************************************************************************/
//int ai_wants_role_unit(player pplayer, city pcity,
//                                int role, int want)
//{
//  int iunit;
//  Tech_Type_id itech;
//  int i, n;
//
//  n = num_role_units(role);
//  for (i=n-1; i>=0; i--) {
//    iunit = Unittype_P.get_role_unit(role, i);
//    if (City.can_build_unit(pcity, iunit)) {
//      return iunit;
//    } else {
//      /* careful; might be unable to build for non-tech reason... */
//      itech = get_unit_type(iunit).tech_requirement;
//      if (get_invention(pplayer, itech) != TECH_KNOWN) {
//	pplayer.ai.tech_want[itech] += want;
//      }
//    }
//  }
//  return unittype.U_LAST;
//}
//
///**************************************************************************
// As ai_wants_role_unit, but also set choice.choice if we can build something.
//**************************************************************************/
//void ai_choose_role_unit(player pplayer, city pcity,
//			 ai_choice choice, int role, int want)
//{
//  int iunit = ai_wants_role_unit(pplayer, pcity, role, want);
//  if (iunit != unittype.U_LAST)
//    choice.choice = iunit;
//}
//
///**************************************************************************
// Whether unit_type test is on the "upgrade path" of unit_type base,
// even if we can't upgrade now.
//**************************************************************************/
//boolean is_on_unit_upgrade_path(int test, int base)
//{
//  /* This is the real function: */
//  do {
//    base = Unittype_P.unit_types[base].obsoleted_by;
//    if (base == test) {
//      return true;
//    }
//  } while (base != -1);
//  return false;
//}
//
///*************************************************************************
//Barbarian leader tries to stack with other barbarian units, and if it's
//not possible it runs away. When on coast, it may disappear with 33% chance.
//**************************************************************************/
//static void ai_manage_barbarian_leader(player pplayer, unit leader)
//{
//  Continent_id con = map_get_continent(leader.tile);
//  int safest = 0;
//  tile safest_tile = leader.tile;
//  unit closest_unit = null;
//  int dist, mindist = 10000;
//
//  CHECK_UNIT(leader);
//
//  if (leader.moves_left == 0 || 
//      (!Terrain_H.is_ocean(leader.tile.terrain) &&
//       unit_list_size(&(leader.tile.units)) > 1) ) {
//      Unithand.handle_unit_activity_request(leader, ACTIVITY_SENTRY);
//      return;
//  }
//  /* the following takes much CPU time and could be avoided */
//  generate_warmap(Map.map_get_city(leader.tile), leader);
//
//  /* duck under own units */
//  for (unit aunit : pplayer.units.data) {
//    if (unit_has_role(aunit.type, unit_role_id.L_BARBARIAN_LEADER)
//	|| !is_ground_unit(aunit)
//	|| map_get_continent(aunit.tile) != con)
//      continue;
//
//    if (WARMAP_COST(aunit.tile) < mindist) {
//      mindist = WARMAP_COST(aunit.tile);
//      closest_unit = aunit;
//    }
//  } }
//
//  if (closest_unit
//      && !Map.same_pos(closest_unit.tile, leader.tile)
//      && (map_get_continent(leader.tile)
//          == map_get_continent(closest_unit.tile))) {
//    () ai_unit_goto(leader, closest_unit.tile);
//    return; /* sticks better to own units with this -- jk */
//  }
//
//  UNIT_LOG(Log.LOG_DEBUG, leader, "Barbarian leader needs to flee");
//  mindist = 1000000;
//  closest_unit = null;
//
//  for(player other_player: Game.game.players){
//    for (unit aunit : other_player.units.data) {
//      if (is_military_unit(aunit)
//	  && is_ground_unit(aunit)
//	  && map_get_continent(aunit.tile) == con) {
//	/* questionable assumption: aunit needs as many moves to reach us as we
//	   need to reach it */
//	dist = WARMAP_COST(aunit.tile) - aunit.move_rate();
//	if (dist < mindist) {
//	  util.freelog(Log.LOG_DEBUG, "Barbarian leader: closest enemy is %s at %d, %d, dist %d",
//                  Unittype_P.unit_name(aunit.type), aunit.tile.x,
//		  aunit.tile.y, dist);
//	  mindist = dist;
//	  closest_unit = aunit;
//	}
//      }
//    } }
//  }
//
//  /* Disappearance - 33% chance on coast, when older than barbarian life span */
//  if (Terrain.is_terrain_flag_near_tile(leader.tile) && leader.fuel == 0) {
//    if(Rand.myrand(3) == 0) {
//      UNIT_LOG(Log.LOG_DEBUG, leader, "barbarian leader disappearing...");
//      Unittools.wipe_unit(leader);
//      return;
//    }
//  }
//
//  if (!closest_unit) {
//    Unithand.handle_unit_activity_request(leader, unit_activity.ACTIVITY_IDLE);
//    UNIT_LOG(Log.LOG_DEBUG, leader, "Barbarian leader: no enemy.");
//    return;
//  }
//
//  generate_warmap(Map.map_get_city(closest_unit.tile), closest_unit);
//
//  do {
//    tile last_tile;
//
//    UNIT_LOG(Log.LOG_DEBUG, leader, "Barbarian leader: moves left: %d.",
//             leader.moves_left);
//
//    for(tile near_tile: util.square_tile_iterate(leader.tile, 1)) {
//      if (WARMAP_COST(near_tile) > safest
//	  && could_unit_move_to_tile(leader, near_tile) == 1) {
//	safest = WARMAP_COST(near_tile);
//	util.freelog(Log.LOG_DEBUG,
//		"Barbarian leader: safest is %d, %d, safeness %d",
//		near_tile.x, near_tile.y, safest);
//	safest_tile = near_tile;
//      }
//    } 
//    square_iterate_end;
//
//    UNIT_LOG(Log.LOG_DEBUG, leader, "Barbarian leader: fleeing to (%d,%d).", 
//             safest_tile.x, safest_tile.y);
//    if (Map.same_pos(leader.tile, safest_tile)) {
//      UNIT_LOG(Log.LOG_DEBUG, leader, 
//               "Barbarian leader: reached the safest position.");
//      Unithand.handle_unit_activity_request(leader, unit_activity.ACTIVITY_IDLE);
//      return;
//    }
//
//    last_tile = leader.tile;
//    () ai_unit_goto(leader, safest_tile);
//    if (Map.same_pos(leader.tile, last_tile)) {
//      /* Deep inside the goto handling code, in 
//	 server/unithand.c::handle_unite_move_request(), the server
//	 may decide that a unit is better off not moving this turn,
//	 because the unit doesn't have quite enough movement points
//	 remaining.  Unfortunately for us, this favor that the server
//	 code does may lead to an endless loop here in the barbarian
//	 leader code:  the BL will try to flee to a new location, execute 
//	 the goto, find that it's still at its present (unsafe) location,
//	 and repeat.  To break this loop, we test for the condition
//	 where the goto doesn't do anything, and break if this is
//	 the case. */
//      break;
//    }
//  } while (leader.moves_left > 0);
//}
//
///*************************************************************************
//  Updates the global array simple_ai_types.
//**************************************************************************/
//void update_simple_ai_types()
//{
//  int i = 0;
//
//  unit_type_iterate(id) {
//    if (unit_type_exists(id) && !Unittype_P.unit_type_flag(id, Eunit_flag_id.F_NONMIL)
//	&& !Unittype_P.unit_type_flag(id, F_MISSILE)
//	&& !Unittype_P.unit_type_flag(id, F_NO_LAND_ATTACK)
//        && get_unit_type(id).move_type != unit_move_type.AIR_MOVING
//	&& get_unit_type(id).transport_capacity < 8) {
//      simple_ai_types[i] = id;
//      i++;
//    }
//  } unit_type_iterate_end;
//
//  simple_ai_types[i] = unittype.U_LAST;
//}
}