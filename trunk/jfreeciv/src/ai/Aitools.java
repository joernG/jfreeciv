package ai;

public class Aitools{

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
//#include "city.h"
//#include "combat.h"
//#include "game.h"
//#include "government.h"
//#include "log.h"
//#include "Map.map.h"
//#include "mem.h"
//#include "packets.h"
//#include "player.h"
//#include "shared.h"
//#include "unit.h"
//
//#include "citymap.h"
//#include "path_finding.h"
//#include "pf_tools.h"
//
//#include "airgoto.h"
//#include "barbarian.h"
//#include "citytools.h"
//#include "cityturn.h"
//#include "gotohand.h"
//#include "maphand.h"
//#include "plrhand.h"
//#include "score.h"
//#include "settlers.h"
//#include "unithand.h"
//#include "unittools.h"
//
//#include "advmilitary.h"
//#include "aicity.h"
//#include "aidata.h"
//#include "aiferry.h"
//#include "ailog.h"
//#include "aiunit.h"
//
//#include "aitools.h"
//
///**************************************************************************
//  Amortize a want modified by the shields (build_cost) we risk losing.
//  We add the build time of the unit(s) we risk to amortize delay.  The
//  build time is claculated as the build cost divided by the production
//  output of the unit's homecity or the city where we want to produce
//  the unit. If the city has less than average shield output, we
//  instead use the average, to encourage long-term thinking.
//**************************************************************************/
//int military_amortize(player pplayer, city pcity,
//                      int value, int delay, int build_cost)
//{
//  ai_data ai = ai_data_get(pplayer);
//  int city_output = (pcity ? pcity.shield_surplus : 1);
//  int output = MAX(city_output, ai.stats.average_production);
//  int build_time = build_cost / MAX(output, 1);
//
//  if (value <= 0) {
//    return 0;
//  }
//
//  return amortize(value, delay + build_time);
//}
//
///**********************************************************************
//  There are some signs that a player might be dangerous: We are at 
//  war with him, he has lousy reputation, he has done lots of ignoble 
//  things to us, he is an ally of one of our enemies (a ticking bomb
//  to be sure), or he is our war target.
//***********************************************************************/
//boolean is_player_dangerous(player pplayer, player aplayer)
//{
//  ai_data ai = ai_data_get(pplayer);
//  ai_dip_intel adip 
//    = &ai.diplomacy.player_intel[aplayer.player_no];
//
//  return (pplayer != aplayer)
//         && ((pplayers_at_war(pplayer, aplayer)
//           || ai.diplomacy.target == aplayer
//           || pplayer.diplstates[aplayer.player_no].has_reason_to_cancel != 0
//           || ai.diplomacy.acceptable_reputation > aplayer.reputation
//           || adip.is_allied_with_enemy));
//}
//
///*************************************************************************
//  This is a function to execute paths returned by the path-finding engine.
//
//  Brings our bodyguard along.
//  Returns false only if died.
//*************************************************************************/
//boolean ai_unit_execute_path(unit punit, pf_path path)
//{
//  int i;
//
//  /* We start with i = 1 for i = 0 is our present position */
//  for (i = 1; i < path.length; i++) {
//    tile ptile = path.positions[i].tile;
//    int id = punit.id;
//
//    if (Map.same_pos(punit.tile, ptile)) {
//      UNIT_LOG(Log.LOG_DEBUG, punit, "execute_path: waiting this turn");
//      return true;
//    }
//
//    /* We use ai_unit_move() for everything but the last step
//     * of the way so that we abort if unexpected opposition
//     * shows up. Any enemy on the target tile is expected to
//     * be our target and any attack there intentional. */
//    if (i == path.length - 1) {
//      () ai_unit_attack(punit, ptile);
//    } else {
//      () ai_unit_move(punit, ptile);
//    }
//    if (!find_unit_by_id(id)) {
//      /* Died... */
//      return false;
//    }
//
//    if (!Map.same_pos(punit.tile, ptile)) {
//      /* Stopped (or maybe fought) */
//      return true;
//    }
//  }
//
//  return true;
//}
//
///****************************************************************************
//  A helper function for ai_gothere.  Estimates the dangers we will
//  be facing at our destination and tries to find/request a bodyguard if 
//  needed.
//****************************************************************************/
//static void ai_gothere_bodyguard(unit punit, tile dest_tile)
//{
//  player pplayer = punit.unit_owner();
//  ai_data ai = ai_data_get(pplayer);
//  unsigned int danger = 0;
//  city dcity;
//  tile ptile;
//  
//  if (is_barbarian(punit.unit_owner())) {
//    /* barbarians must have more courage (ie less brains) */
//    punit.ai.bodyguard = BODYGUARD_NONE;
//    return;
//  }
//
//  /* Estimate enemy attack power. */
//  for (unit aunit : dest_tile.units.data) {
//    if (HOSTILE_PLAYER(pplayer, ai, aunit.unit_owner())) {
//      danger += unit_att_rating(aunit);
//    }
//  } }
//  dcity = map_get_city(dest_tile);
//  if (dcity && HOSTILE_PLAYER(pplayer, ai, city_owner(dcity))) {
//    /* Assume enemy will build another defender, add it's attack strength */
//    int d_type = ai_choose_defender_versus(dcity, punit.type);
//    danger += 
//      unittype_att_rating(d_type, do_make_unit_veteran(dcity, d_type), 
//                          Unit_H.SINGLE_MOVE, unit_types[d_type].hp);
//  }
//  danger *= POWER_DIVIDER;
//
//  /* If we are fast, there is less danger. */
//  danger /= (punit.unit_type().move_rate / Unit_H.SINGLE_MOVE);
//  if (unit_flag(punit, F_IGTER)) {
//    danger /= 1.5;
//  }
//
//  ptile = punit.tile;
//  /* We look for the bodyguard where we stand. */
//  if (!unit_list_find(&ptile.units, punit.ai.bodyguard)) {
//    int my_def = (punit.hp 
//                  * punit.unit_type().veteran[punit.veteran].power_fact
//		  * punit.unit_type().defense_strength
//                  * POWER_FACTOR);
//    
//    if (danger >= my_def) {
//      UNIT_LOG(LOGLEVEL_BODYGUARD, punit, 
//               "want bodyguard @(%d, %d) danger=%d, my_def=%d", 
//               TILE_XY(dest_tile), danger, my_def);
//      punit.ai.bodyguard = BODYGUARD_WANTED;
//    } else {
//      punit.ai.bodyguard = BODYGUARD_NONE;
//    }
//  }
//
//  /* What if we have a bodyguard, but don't need one? */
//}
//
//#define LOGLEVEL_GOTHERE Log.LOG_DEBUG
///****************************************************************************
//  This is ferry-enabled goto.  Should not normally be used for non-ferried 
//  units (i.e. planes or ships), use ai_unit_goto instead.
//
//  Return values: true if got to or next to our destination, false otherwise. 
//
//  TODO: A big one is rendezvous points.  When this is implemented, we won't
//  have to be at the coast to ask for a boat to come to us.
//
//  You MUST have warmap created before calling this function in order for 
//  find_beachhead to work here. This requirement should be removed.
//****************************************************************************/
//boolean ai_gothere(player pplayer, unit punit,
//                tile dest_tile)
//{
//  CHECK_UNIT(punit);
//
//  if (Map.same_pos(dest_tile, punit.tile)) {
//    /* Nowhere to go */
//    return true;
//  }
//
//  /* See if we need a bodyguard at our destination */
//  /* FIXME: If bodyguard is _really_ necessary, don't go anywhere */
//  ai_gothere_bodyguard(punit, dest_tile);
//
//  if (punit.transported_by > 0 
//      || !goto_is_sane(punit, dest_tile, true)) {
//    /* Must go by boat, call an aiferryboat function */
//    if (!aiferry_gobyboat(pplayer, punit, dest_tile)) {
//      return false;
//    }
//  }
//
//  /* Go where we should be going if we can, and are at our destination 
//   * if we are on a ferry */
//  if (goto_is_sane(punit, dest_tile, true) && punit.moves_left > 0) {
//    punit.goto_tile = dest_tile;
//    UNIT_LOG(LOGLEVEL_GOTHERE, punit, "Walking to (%d,%d)",
//	     dest_tile.x, dest_tile.y);
//    if (!ai_unit_goto(punit, dest_tile)) {
//      /* died */
//      return false;
//    }
//    /* liable to bump into someone that will kill us.  Should avoid? */
//  } else {
//    UNIT_LOG(LOGLEVEL_GOTHERE, punit, "Not moving");
//    return false;
//  }
//
//  if (punit.ai.ferryboat > 0 && punit.transported_by <= 0) {
//    /* We probably just landed, release our boat */
//    aiferry_clear_boat(punit);
//  }
//  
//  /* Dead unit shouldn't reach this point */
//  CHECK_UNIT(punit);
//  
//  return (Map.same_pos(punit.tile, dest_tile) 
//          || is_tiles_adjacent(punit.tile, dest_tile));
//}
//
///**************************************************************************
//  Go to specified destination but do not disturb existing role or activity
//  and do not clear the role's destination. Return false iff we died.
//
//  FIXME: add some logging functionality to replace GOTO_LOG()
//**************************************************************************/
//boolean ai_unit_goto(unit punit, tile ptile)
//{
//  goto_result result;
//  tile old_tile;
//  enum unit_activity activity = punit.activity;
//
//  old_tile = punit.goto_tile; /* May be null. */
//
//  CHECK_UNIT(punit);
//  /* TODO: log error on Map.same_pos with punit.x|y */
//  punit.goto_tile = ptile;
//  handle_unit_activity_request(punit, unit_activity.ACTIVITY_GOTO);
//  result = do_unit_goto(punit, goto_move_restriction.GOTO_MOVE_ANY, false);
//  if (result != GR_DIED) {
//    handle_unit_activity_request(punit, activity);
//    punit.goto_tile = old_tile; /* May be null. */
//    return true;
//  }
//  return false;
//}
//
///**************************************************************************
//  Ensure unit sanity by telling charge that we won't bodyguard it anymore,
//  tell bodyguard it can roam free if our job is done, add and remove city 
//  spot reservation, and set destination. If we set a unit to hunter, also
//  reserve its target, and try to load it with cruise missiles or nukes
//  to bring along.
//**************************************************************************/
//void ai_unit_new_role(unit punit, enum ai_unit_task task,
//		      tile ptile)
//{
//  unit charge = find_unit_by_id(punit.ai.charge);
//  unit bodyguard = find_unit_by_id(punit.ai.bodyguard);
//
//  /* If the unit is under (human) orders we shouldn't control it. */
//  assert(!unit_has_orders(punit));
//
//  UNIT_LOG(Log.LOG_DEBUG, punit, "changing role from %d to %d",
//           punit.activity, task);
//
//  /* Free our ferry.  Most likely it has been done already. */
//  if (task == AIUNIT_NONE || task == AIUNIT_DEFEND_HOME) {
//    aiferry_clear_boat(punit);
//  }
//
//  if (punit.activity == unit_activity.ACTIVITY_GOTO) {
//    /* It would indicate we're going somewhere otherwise */
//    handle_unit_activity_request(punit, unit_activity.ACTIVITY_IDLE);
//  }
//
//  if (punit.ai.ai_role == AIUNIT_BUILD_CITY) {
//    citymap_free_city_spot(punit.goto_tile, punit.id);
//  }
//
//  if (punit.ai.ai_role == AIUNIT_HUNTER) {
//    /* Clear victim's hunted bit - we're no longer chasing. */
//    unit target = find_unit_by_id(punit.ai.target);
//
//    if (target) {
//      target.ai.hunted &= ~(1 << punit.unit_owner().player_no);
//      UNIT_LOG(LOGLEVEL_HUNT, target, "no longer hunted (new role %d, old %d)",
//               task, punit.ai.ai_role);
//    }
//  }
//
//  if (charge && (charge.ai.bodyguard == punit.id)) {
//    /* ensure we don't let the unit believe we bodyguard it */
//    charge.ai.bodyguard = BODYGUARD_NONE;
//  }
//  punit.ai.charge = BODYGUARD_NONE;
//
//  punit.ai.ai_role = task;
//
//  /* Verify and set the goto destination.  Eventually this can be a lot more
//   * stringent, but for now we don't want to break things too badly. */
//  punit.goto_tile = ptile; /* May be null. */
//
//  if (punit.ai.ai_role == AIUNIT_NONE && bodyguard) {
//    ai_unit_new_role(bodyguard, AIUNIT_NONE, null);
//  }
//
//  /* Reserve city spot, _unless_ we want to add ourselves to a city. */
//  if (punit.ai.ai_role == AIUNIT_BUILD_CITY && !map_get_city(ptile)) {
//    citymap_reserve_city_spot(ptile, punit.id);
//  }
//  if (punit.ai.ai_role == AIUNIT_HUNTER) {
//    /* Set victim's hunted bit - the hunt is on! */
//    unit target = find_unit_by_id(punit.ai.target);
//
//    assert(target != null);
//    target.ai.hunted |= (1 << punit.unit_owner().player_no);
//    UNIT_LOG(LOGLEVEL_HUNT, target, "is being hunted");
//
//    /* Grab missiles lying around and bring them along */
//    if (unit_flag(punit, F_MISSILE_CARRIER)
//        || unit_flag(punit, F_CARRIER)) {
//      for (unit missile : punit.tile.units.data) {
//        if (missile.ai.ai_role != AIUNIT_ESCORT
//            && missile.transported_by == -1
//            && missile.owner == punit.owner
//            && unit_flag(missile, F_MISSILE)
//            && can_unit_load(missile, punit)) {
//          UNIT_LOG(LOGLEVEL_HUNT, missile, "loaded on hunter");
//          ai_unit_new_role(missile, AIUNIT_ESCORT, target.tile);
//          load_unit_onto_transporter(missile, punit);
//        }
//      } }
//    }
//  }
//}
//
///**************************************************************************
//  Try to make pcity our new homecity. Fails if we can't upkeep it. Assumes
//  success from server.
//**************************************************************************/
//boolean ai_unit_make_homecity(unit punit, city pcity)
//{
//  CHECK_UNIT(punit);
//  assert(punit.owner == pcity.owner);
//
//  if (punit.homecity == 0 && !unit_has_role(punit.type, L_EXPLORER)) {
//    /* This unit doesn't pay any upkeep while it doesn't have a homecity,
//     * so it would be stupid to give it one. There can also be good reasons
//     * why it doesn't have a homecity. */
//    /* However, until we can do something more useful with them, we
//       will assign explorers to a city so that they can be disbanded for 
//       the greater good -- Per */
//    return false;
//  }
//  if (pcity.shield_surplus - punit.unit_type().shield_cost >= 0
//      && pcity.food_surplus - punit.unit_type().food_cost >= 0) {
//    handle_unit_change_homecity(punit.unit_owner(), punit.id, pcity.id);
//    return true;
//  }
//  return false;
//}
//
///**************************************************************************
//  Move a bodyguard along with another unit. We assume that unit has already
//  been moved to (x, y) which is a valid, safe coordinate, and that our
//  bodyguard has not. This is an ai_unit_* auxiliary function, do not use 
//  elsewhere.
//**************************************************************************/
//static void ai_unit_bodyguard_move(int unitid, tile ptile)
//{
//  unit bodyguard = find_unit_by_id(unitid);
//  unit punit;
//  player pplayer;
//
//  assert(bodyguard != null);
//  pplayer = bodyguard.unit_owner();
//  assert(pplayer != null);
//  punit = find_unit_by_id(bodyguard.ai.charge);
//  assert(punit != null);
//
//  assert(punit.ai.bodyguard == bodyguard.id);
//  assert(bodyguard.ai.charge == punit.id);
//
//  if (!is_tiles_adjacent(ptile, bodyguard.tile)) {
//    return;
//  }
//
//  if (bodyguard.moves_left <= 0) {
//    /* should generally should not happen */
//    BODYGUARD_LOG(Log.LOG_DEBUG, bodyguard, "was left behind by charge");
//    return;
//  }
//
//  handle_unit_activity_request(bodyguard, unit_activity.ACTIVITY_IDLE);
//  () ai_unit_move(bodyguard, ptile);
//}
//
///**************************************************************************
//  Check if we have a bodyguard with sanity checking and error recovery.
//  Repair incompletely referenced bodyguards. When the rest of the bodyguard
//  mess is cleaned up, this repairing should be replaced with an assert.
//**************************************************************************/
//static boolean has_bodyguard(unit punit)
//{
//  unit guard;
//  if (punit.ai.bodyguard > BODYGUARD_NONE) {
//    if ((guard = find_unit_by_id(punit.ai.bodyguard))) {
//      if (guard.ai.charge != punit.id) {
//        BODYGUARD_LOG(LOG_VERBOSE, guard, "my charge didn't know about me!");
//      }
//      guard.ai.charge = punit.id; /* ensure sanity */
//      return true;
//    } else {
//      punit.ai.bodyguard = BODYGUARD_NONE;
//      UNIT_LOG(LOGLEVEL_BODYGUARD, punit, "bodyguard disappeared!");
//    }
//  }
//  return false;
//}
//
///**************************************************************************
//  Move and attack with an ai unit. We do not wait for server reply.
//**************************************************************************/
//boolean ai_unit_attack(unit punit, tile ptile)
//{
//  int sanity = punit.id;
//  boolean alive;
//
//  CHECK_UNIT(punit);
//  assert(punit.unit_owner().ai.control);
//  assert(is_tiles_adjacent(punit.tile, ptile));
//
//  handle_unit_activity_request(punit, unit_activity.ACTIVITY_IDLE);
//  () handle_unit_move_request(punit, ptile, false, false);
//  alive = (find_unit_by_id(sanity) != null);
//
//  if (alive && Map.same_pos(ptile, punit.tile)
//      && has_bodyguard(punit)) {
//    ai_unit_bodyguard_move(punit.ai.bodyguard, ptile);
//    /* Clumsy bodyguard might trigger an auto-attack */
//    alive = (find_unit_by_id(sanity) != null);
//  }
//
//  return alive;
//}
//
///**************************************************************************
//  Move an ai unit. Do not attack. Do not leave bodyguard.
//
//  This function returns only when we have a reply from the server and
//  we can tell the calling function what happened to the move request.
//  (Right now it is not a big problem, since we call the server directly.)
//**************************************************************************/
//boolean ai_unit_move(unit punit, tile ptile)
//{
//  unit bodyguard;
//  int sanity = punit.id;
//  player pplayer = punit.unit_owner();
//
//  CHECK_UNIT(punit);
//  assert(punit.unit_owner().ai.control);
//  assert(is_tiles_adjacent(punit.tile, ptile));
//
//  /* if enemy, stop and let ai attack function take this case */
//  if (is_enemy_unit_tile(ptile, pplayer)
//      || is_enemy_city_tile(ptile, pplayer)) {
//    UNIT_LOG(Log.LOG_DEBUG, punit, "movement halted due to enemy presence");
//    return false;
//  }
//
//  /* barbarians shouldn't enter huts */
//  if (is_barbarian(pplayer) && Map.tile_has_special(ptile, S_HUT)) {
//    return false;
//  }
//
//  /* don't leave bodyguard behind */
//  if (has_bodyguard(punit)
//      && (bodyguard = find_unit_by_id(punit.ai.bodyguard))
//      && Map.same_pos(punit.tile, bodyguard.tile)
//      && bodyguard.moves_left == 0) {
//    UNIT_LOG(LOGLEVEL_BODYGUARD, punit, "does not want to leave "
//             "its bodyguard");
//    return false;
//  }
//
//  /* Try not to end move next to an enemy if we can avoid it by waiting */
//  if (punit.moves_left <= map_move_cost(punit, ptile)
//      && punit.move_rate() > map_move_cost(punit, ptile)
//      && enemies_at(punit, ptile)
//      && !enemies_at(punit, punit.tile)) {
//    UNIT_LOG(Log.LOG_DEBUG, punit, "ending move early to stay out of trouble");
//    return false;
//  }
//
//  /* go */
//  handle_unit_activity_request(punit, unit_activity.ACTIVITY_IDLE);
//  () handle_unit_move_request(punit, ptile, false, true);
//
//  /* handle the results */
//  if (find_unit_by_id(sanity) && Map.same_pos(ptile, punit.tile)) {
//    if (has_bodyguard(punit)) {
//      ai_unit_bodyguard_move(punit.ai.bodyguard, ptile);
//    }
//    return true;
//  }
//  return false;
//}
//
///**************************************************************************
//This looks for the nearest city:
//If (x,y) is the land, it looks for cities only on the same continent
//unless (everywhere != 0)
//If (enemy != 0) it looks only for enemy cities
//If (pplayer != null) it looks for cities known to pplayer
//**************************************************************************/
//city dist_nearest_city(player pplayer, tile ptile,
//                               boolean everywhere, boolean enemy)
//{ 
//  city pc=null;
//  int best_dist = -1;
//  Continent_id con = map_get_continent(ptile);
//
//  for(player pplay: game.players){
//    /* If "enemy" is set, only consider cities whose owner we're at
//     * war with. */
//    if (enemy && pplayer && !pplayers_at_war(pplayer, pplay)) {
//      continue;
//    }
//
//    for (city pcity : pplay.cities.data) {
//      int city_dist = real_map_distance(ptile, pcity.tile);
//
//      /* Find the closest city known to the player with a matching
//       * continent. */
//      if ((best_dist == -1 || city_dist < best_dist)
//	  && (everywhere || con == 0
//	      || con == map_get_continent(pcity.tile))
//	  && (!pplayer || map_is_known(pcity.tile, pplayer))) {
//	best_dist = city_dist;
//        pc = pcity;
//      }
//    } }
//  }
//
//  return(pc);
//}
//
//
///**************************************************************************
//  Calculate the value of the target unit including the other units which
//  will die in a successful attack
//**************************************************************************/
//int stack_cost(unit pdef)
//{
//  int victim_cost = 0;
//
//  if (is_stack_vulnerable(pdef.tile)) {
//    /* lotsa people die */
//    for (unit aunit : pdef.tile.units.data) {
//      victim_cost += unit_build_shield_cost(aunit.type);
//    } }
//  } else {
//    /* Only one unit dies if attack is successful */
//    victim_cost = unit_build_shield_cost(pdef.type);
//  }
//  
//  return victim_cost;
//}
//
///**************************************************************************
//  Change government, pretty fast...
//**************************************************************************/
//void ai_government_change(player pplayer, int gov)
//{
//  if (gov == pplayer.government) {
//    return;
//  }
//
//  handle_player_change_government(pplayer, gov);
//
//  for (city pcity : pplayer.cities.data) {
//    auto_arrange_workers(pcity); /* update cities */
//  } }
//}
//
///**************************************************************************
//  Credits the AI wants to have in reserves. We need some gold to bribe
//  and incite cities.
//
//  "I still don't trust this function" -- Syela
//**************************************************************************/
//int ai_gold_reserve(player pplayer)
//{
//  int i = total_player_citizens(pplayer)*2;
//  return MAX(pplayer.ai.maxbuycost, i);
//}
//
///**************************************************************************
//  Sets the values of the choice to initial values.
//**************************************************************************/
//void init_choice(ai_choice choice)
//{
//  choice.choice = A_UNSET;
//  choice.want = 0;
//  choice.type = CT_NONE;
//  choice.need_boat = false;
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void adjust_choice(int value, ai_choice choice)
//{
//  choice.want = (choice.want *value)/100;
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void copy_if_better_choice(ai_choice cur, ai_choice best)
//{
//  if (cur.want > best.want) {
//    util.freelog(Log.LOG_DEBUG, "Overriding choice (%s, %d) with (%s, %d)",
//	    (best.type == CT_BUILDING ? 
//	     get_improvement_name(best.choice) : unit_types[best.choice].name), 
//	    best.want, 
//	    (cur.type == CT_BUILDING ? 
//	     get_improvement_name(cur.choice) : unit_types[cur.choice].name), 
//	    cur.want);
//    best.choice =cur.choice;
//    best.want = cur.want;
//    best.type = cur.type;
//    best.need_boat = cur.need_boat;
//  }
//}
//
///**************************************************************************
//  Returns true if pcity's owner is building any wonder in another city on
//  the same continent (if so, we may want to build a caravan here).
//**************************************************************************/
//static boolean is_building_other_wonder(city pcity)
//{
//  player pplayer = city_owner(pcity);
//
//  for (city acity : pplayer.cities.data) {
//    if (pcity != acity
//	&& !acity.is_building_unit
//	&& is_wonder(acity.currently_building)
//	&& (map_get_continent(acity.tile)
//	    == map_get_continent(pcity.tile))) {
//      return true;
//    }
//  } }
//
//  return false;
//}
//
///**************************************************************************
//  Choose improvement we like most and put it into ai_choice.
//  TODO: Clean, update the log calls.
//**************************************************************************/
//void ai_advisor_choose_building(city pcity, ai_choice choice)
//{ /* I prefer the ai_choice as a return value; gcc prefers it as an arg -- Syela */
//  Impr_Type_id id = B_LAST;
//  unsigned int danger = 0;
//  int downtown = 0, cities = 0;
//  int want=0;
//  player plr;
//        
//  plr = city_owner(pcity);
//     
//  /* too bad plr.score isn't kept up to date. */
//  city_list_iterate(plr.cities, acity)
//    danger += acity.ai.danger;
//    downtown += acity.ai.downtown;
//    cities++;
//  }
//
//  impr_type_iterate(i) {
//    if (!plr.ai.control
//        && (get_building_for_effect(EFT_CAPITAL_CITY) == i
//            || is_wonder(i))) {
//      continue; /* Humans should not be advised to build wonders or palace */
//    }
//    if (!is_wonder(i)
//	|| (!pcity.is_building_unit && is_wonder(pcity.currently_building)
//	    && pcity.shield_stock >= impr_build_shield_cost(i) / 2)
//	|| (!is_building_other_wonder(pcity)
//	    /* otherwise caravans will be killed! */
//	    && pcity.ai.grave_danger == 0
//	    && pcity.ai.downtown * cities >= downtown
//	    && pcity.ai.danger * cities <= danger)) {
//      /* Is this too many restrictions? */
//      /* trying to keep wonders in safe places with easy caravan
//       * access -- Syela */
//      if(pcity.ai.building_want[i]>want) {
//	/* we have to do the can_build check to avoid Built Granary.
//	 * Now Building Granary. */
//        if (can_build_improvement(pcity, i)) {
//          want = pcity.ai.building_want[i];
//          id = i;
//        } else {
//	  util.freelog(Log.LOG_DEBUG, "%s can't build %s", pcity.name,
//		  get_improvement_name(i));
//	}
//      } /* id is the building we like the best */
//    }
//  } impr_type_iterate_end;
//
//  if (want != 0) {
//    util.freelog(Log.LOG_DEBUG, "AI_Chosen: %s with desire = %d for %s",
//	    get_improvement_name(id), want, pcity.name);
//  } else {
//    util.freelog(Log.LOG_DEBUG, "AI_Chosen: None for %s", pcity.name);
//  }
//  choice.want = want;
//  choice.choice = id;
//  choice.type = CT_BUILDING;
//}
//
///**********************************************************************
//  "The following evaluates the unhappiness caused by military units
//  in the field (or aggressive) at a city when at Republic or 
//  Democracy.
//
//  Now generalised somewhat for government rulesets, though I'm not 
//  sure whether it is fully general for all possible parameters/
//  combinations." --dwp
//**********************************************************************/
//boolean ai_assess_military_unhappiness(city pcity,
//                                    government g)
//{
//  int free_happy;
//  int unhap = 0;
//
//  /* bail out now if happy_cost is 0 */
//  if (g.unit_happy_cost_factor == 0) {
//    return false;
//  }
//  
//  free_happy  = citygov_free_happy(pcity, g);
//
//  /* ??  This does the right thing for normal Republic and Democ -- dwp */
//  free_happy += get_city_bonus(pcity, EFT_MAKE_CONTENT_MIL);
//
//  for (unit punit : pcity.units_supported.data) {
//    int happy_cost = utype_happy_cost(punit.unit_type(), g);
//
//    if (happy_cost <= 0) {
//      continue;
//    }
//
//    /* See discussion/rules in common/city.c:city_support() */
//    if (!unit_being_aggressive(punit)) {
//      if (is_field_unit(punit)) {
//	happy_cost = 1;
//      } else {
//	happy_cost = 0;
//      }
//    }
//    if (happy_cost <= 0) {
//      continue;
//    }
//
//    if (get_city_bonus(pcity, EFT_MAKE_CONTENT_MIL_PER) > 0) {
//      happy_cost--;
//    }
//    adjust_city_free_cost(&free_happy, &happy_cost);
//    
//    if (happy_cost > 0) {
//      unhap += happy_cost;
//    }
//  } }
// 
//  if (unhap < 0) {
//    unhap = 0;
//  }
//  return (unhap > 0);
//}
//
///**************************************************************************
//  AI doesn't want the score for future techs.
//**************************************************************************/
//boolean ai_wants_no_science(player pplayer)
//{
//  return is_future_tech(pplayer.research.researching);
//}
}