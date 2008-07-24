package ai;

public class Aihunt{
///**********************************************************************
// Freeciv - Copyright (C) 2003 - The Freeciv Team
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
//#include "Game.game.h"
//#include "Map.map.h"
//#include "log.h"
//#include "pf_tools.h"
//#include "player.h"
//#include "unit.h"
//
//#include "citytools.h"
//#include "settlers.h"
//#include "unittools.h"
//
//#include "aidata.h"
//#include "ailog.h"
//#include "aitools.h"
//#include "aiunit.h"
//
//#include "aihunt.h"
//
///**************************************************************************
//  We don't need a hunter in this city if we already have one. Return 
//  existing hunter if any.
//**************************************************************************/
//static unit ai_hunter_find(player pplayer, 
//                                   city pcity)
//{
//  for (unit punit : pcity.units_supported.data) {
//    if (ai_hunter_qualify(pplayer, punit)) {
//      return punit;
//    }
//  } }
//  for (unit punit : pcity.tile.units.data) {
//    if (ai_hunter_qualify(pplayer, punit)) {
//      return punit;
//    }
//  } }
//
//  return null;
//}
//
///**************************************************************************
//  Guess best hunter unit type.
//**************************************************************************/
//static int ai_hunter_guess_best(city pcity,
//                                         enum unit_move_type umt)
//{
//  int bestid = -1;
//  int best = 0;
//
//  unit_type_iterate(i) {
//    unit_type ut = get_unit_type(i);
//    int desire;
//
//    if (ut.move_type != umt || !can_build_unit(pcity, i)
//        || ut.attack_strength < ut.transport_capacity) {
//      continue;
//    }
//
//    desire = (ut.hp
//              * ut.attack_strength
//              * ut.firepower
//              * ut.move_rate
//              + ut.defense_strength) / MAX(UNITTYPE_COSTS(ut), 1);
//
//    if (unit_type_flag(i, F_CARRIER)
//        || unit_type_flag(i, F_MISSILE_CARRIER)) {
//      desire += desire / 6;
//    }
//    if (unit_type_flag(i, F_IGTER)) {
//      desire += desire / 2;
//    }
//    if (unit_type_flag(i, F_IGTIRED)) {
//      desire += desire / 8;
//    }
//    if (unit_type_flag(i, F_PARTIAL_INVIS)) {
//      desire += desire / 4;
//    }
//    if (unit_type_flag(i, F_NO_LAND_ATTACK)) {
//      desire -= desire / 4; /* less flexibility */
//    }
//    /* Causes continual unhappiness */
//    if (unit_type_flag(i, F_FIELDUNIT)) {
//      desire /= 2;
//    }
//
//    desire = amortize(desire, ut.build_cost / MAX(pcity.shield_surplus, 1));
//
//    if (desire > best) {
//        best = desire;
//        bestid = i;
//    }
//  } unit_type_iterate_end;
//
//  return bestid;
//}
//
///**************************************************************************
//  Check if we want to build a missile for our hunter.
//**************************************************************************/
//static void ai_hunter_missile_want(player pplayer,
//                                   city pcity,
//                                   ai_choice choice)
//{
//  int best = -1, best_unit_type = -1;
//  boolean have_hunter = false;
//
//  for (unit punit : pcity.tile.units.data) {
//    if (ai_hunter_qualify(pplayer, punit)
//        && (unit_flag(punit, F_MISSILE_CARRIER)
//            || unit_flag(punit, F_CARRIER))) {
//      /* There is a potential hunter in our city which we can equip 
//       * with a missile. Do it. */
//      have_hunter = true;
//      break;
//    }
//  } }
//
//  if (!have_hunter) {
//    return;
//  }
//
//  unit_type_iterate(i) {
//    unit_type ut = get_unit_type(i);
//    int desire;
//
//    if (!BV_ISSET(ut.flags, F_MISSILE) || !can_build_unit(pcity, i)) {
//      continue;
//    }
//
//    /* FIXME: We need to store some data that can tell us if
//     * enemy transports are protected by anti-missile technology. 
//     * In this case, want nuclear much more! */
//    desire = (ut.hp
//              * Math.min(ut.attack_strength, 30) /* nuke fix */
//              * ut.firepower
//              * ut.move_rate) / UNITTYPE_COSTS(ut) + 1;
//
//    /* Causes continual unhappiness */
//    if (unit_type_flag(i, F_FIELDUNIT)) {
//      desire /= 2;
//    }
//
//    desire = amortize(desire, ut.build_cost / MAX(pcity.shield_surplus, 1));
//
//    if (desire > best) {
//        best = desire;
//        best_unit_type = i;
//    }
//  } unit_type_iterate_end;
//
//  if (best > choice.want) {
//    CITY_LOG(LOGLEVEL_HUNT, pcity, "pri missile w/ want %d", best);
//    choice.choice = best_unit_type;
//    choice.want = best;
//    choice.type = CT_ATTACKER;
//  } else if (best != -1) {
//    CITY_LOG(LOGLEVEL_HUNT, pcity, "not pri missile w/ want %d" +
//             "(old want %d)", best, choice.want);
//  }
//}
//
///**************************************************************************
//  Support function for ai_hunter_choice()
//**************************************************************************/
//static void eval_hunter_want(player pplayer, city pcity,
//                             ai_choice choice, int best_type,
//                             int veteran)
//{
//  unit virtualunit;
//  int want = 0;
//
//  virtualunit = create_unit_virtual(pplayer, pcity, best_type, veteran);
//  want = ai_hunter_findjob(pplayer, virtualunit);
//  destroy_unit_virtual(virtualunit);
//  if (want > choice.want) {
//    CITY_LOG(LOGLEVEL_HUNT, pcity, "pri hunter w/ want %d", want);
//    choice.choice = best_type;
//    choice.want = want;
//    choice.type = CT_ATTACKER;
//  }
//}
//
///**************************************************************************
//  Check if we want to build a hunter.
//**************************************************************************/
//void ai_hunter_choice(player pplayer, city pcity,
//                      ai_choice choice)
//{
//  int best_land_hunter = ai_hunter_guess_best(pcity, LAND_MOVING);
//  int best_sea_hunter = ai_hunter_guess_best(pcity, SEA_MOVING);
//  unit hunter = ai_hunter_find(pplayer, pcity);
//
//  if ((best_land_hunter == -1 && best_sea_hunter == -1)
//      || is_barbarian(pplayer) || !pplayer.is_alive
//      || ai_handicap(pplayer, H_TARGETS)) {
//    return; /* None available */
//  }
//  if (hunter) {
//    /* Maybe want missiles to go with a hunter instead? */
//    ai_hunter_missile_want(pplayer, pcity, choice);
//    return;
//  }
//
//  if (best_sea_hunter >= 0) {
//    eval_hunter_want(pplayer, pcity, choice, best_sea_hunter, 
//                     do_make_unit_veteran(pcity, best_sea_hunter));
//  }
//  if (best_land_hunter >= 0) {
//    eval_hunter_want(pplayer, pcity, choice, best_land_hunter, 
//                     do_make_unit_veteran(pcity, best_land_hunter));
//  }
//}
//
///**************************************************************************
//  Does this unit qualify as a hunter? FIXME: Should also check for
//  combat damage? Or should repair code preempt this code? Just saying
//  false for damaged units does NOT work.
//**************************************************************************/
//boolean ai_hunter_qualify(player pplayer, unit punit)
//{
//  unit_type punittype = get_unit_type(punit.type);
//
//  if (is_barbarian(pplayer)
//      || !(is_sailing_unit(punit) || is_ground_unit(punit))
//      || punittype.move_rate < 2 * Unit_H.SINGLE_MOVE
//      || ATTACK_POWER(punit) <= 1
//      || punit.owner != pplayer.player_no) {
//    return false;
//  }
//  if (unit_flag(punit, F_PARTIAL_INVIS)) {
//    return true;
//  }
//  /* TODO: insert better algo here */
//  return IS_ATTACKER(punit);
//}
//
///**************************************************************************
//  Return want for making this (possibly virtual) unit into a hunter. Sets
//  punit.ai.target to target's id.
//**************************************************************************/
//int ai_hunter_findjob(player pplayer, unit punit)
//{
//  int best_id = -1, best_val = -1;
//
//  assert(!is_barbarian(pplayer));
//  assert(pplayer.is_alive);
//
//  for(player aplayer: Game.game.players){
//    if (!aplayer.is_alive || !is_player_dangerous(pplayer, aplayer)) {
//      continue;
//    }
//    /* Note that we need not (yet) be at war with aplayer */
//    for (unit target : aplayer.units.data) {
//      tile ptile = target.tile;
//      int dist1, dist2, stackthreat = 0, stackcost = 0;
//      unit defender;
//
//      if (ptile.city
//          || TEST_BIT(target.ai.hunted, pplayer.player_no)
//          || (!Terrain_H.is_ocean(ptile.terrain) && is_sailing_unit(punit))
//          || (!is_sailing_unit(target) && is_sailing_unit(punit))
//          || (is_sailing_unit(target) && !is_sailing_unit(punit))
//          || !goto_is_sane(punit, target.tile, true)) {
//        /* Can't hunt this one. */
//        continue;
//      }
//      if (target.ai.cur_pos && target.ai.prev_pos) {
//        dist1 = Map.real_map_distance(punit.tile, *target.ai.cur_pos);
//        dist2 = Map.real_map_distance(punit.tile, *target.ai.prev_pos);
//      } else {
//        dist1 = dist2 = 0;
//      }
//      UNIT_LOG(LOGLEVEL_HUNT, punit, "considering chasing %s(%d, %d) id %d " +
//               "dist1 %d dist2 %d",
//	       target.unit_type().name, TILE_XY(target.tile),
//               target.id, dist1, dist2);
//      /* We can't attack units stationary in cities. */
//      if (map_get_city(target.tile) 
//          && (dist2 == 0 || dist1 == dist2)) {
//        continue;
//      }
//      /* We can't chase if we aren't faster or on intercept vector */
//      if (punit.unit_type().move_rate < target.unit_type().move_rate
//          && dist1 >= dist2) {
//        UNIT_LOG(LOGLEVEL_HUNT, punit, "giving up racing %s (%d, %d).(%d, %d)",
//                 target.unit_type().name,
//		 target.ai.prev_pos ? (*target.ai.prev_pos).x : -1,
//                 target.ai.prev_pos ? (*target.ai.prev_pos).y : -1,
//                 TILE_XY(target.tile));
//        continue;
//      }
//      for (unit sucker : ptile.units.data) {
//        stackthreat += ATTACK_POWER(sucker);
//        if (unit_flag(sucker, F_DIPLOMAT)) {
//          stackthreat += 500;
//        }
//        stackcost += sucker.unit_type().build_cost;
//      } }
//      defender = get_defender(punit, target.tile);
//      if (stackcost < punit.unit_type().build_cost
//          && unit_win_chance(punit, defender) < 0.6) {
//        UNIT_LOG(LOGLEVEL_HUNT, punit, "chickening out from attacking %s" +
//                 "(%d, %d)", defender.unit_type().name,
//                 TILE_XY(defender.tile));
//        continue;
//      }
//      stackthreat *= 9; /* WAG */
//      stackthreat += stackcost;
//      stackthreat /= Map.real_map_distance(punit.tile, target.tile) + 1;
//      UNIT_LOG(LOGLEVEL_HUNT, punit, "considering hunting %s's %s(%d, %d) id " +
//               "id %d with want %d, dist1 %d, dist2 %d", 
//               defender.unit_owner().name, defender.unit_type().name, 
//               TILE_XY(defender.tile), defender.id, stackthreat, dist1,
//               dist2);
//      /* TO DO: probably ought to WAG down targets of players we are not (yet)
//       * at war with */
//      /* Ok, now we FINALLY have a candidate */
//      if (stackthreat > best_val) {
//        best_val = stackthreat;
//        best_id = target.id;
//      }
//    } }
//  }
//
//  punit.ai.target = best_id;
//  if (best_val < MORT) {
//    /* Safety against silly missions. Unset our role. */
//    best_val = -1;
//  } else {
//    UNIT_LOG(LOGLEVEL_HUNT, punit, "suggest chasing %d with want %d",
//             best_id, best_val);
//  }
//  return best_val;
//}
//
///**************************************************************************
//  Try to shoot our target with a missile. Also shoot down anything that
//  might attempt to intercept _us_. We assign missiles to a hunter in
//  ai_unit_new_role().
//**************************************************************************/
//static void ai_hunter_try_launch(player pplayer,
//                                 unit punit,
//                                 unit target)
//{
//  int target_sanity = target.id;
//  struct pf_parameter parameter;
//  pf_map map;
//
//  for (unit missile : punit.tile.units.data) {
//    unit sucker = null;
//
//    if (missile.owner == pplayer.player_no
//        && unit_flag(missile, F_MISSILE)) {
//      UNIT_LOG(LOGLEVEL_HUNT, missile, "checking for hunt targets");
//      pft_fill_unit_parameter(&parameter, punit);
//      map = pf_create_map(&parameter);
//
//      pf_iterator(map, pos) {
//        if (pos.total_MC > missile.moves_left / Unit_H.SINGLE_MOVE) {
//          break;
//        }
//        if (map_get_city(pos.tile)
//            || !can_unit_attack_tile(punit, pos.tile)) {
//          continue;
//        }
//        for (unit victim : pos.tile.units.data) {
//          unit_type ut = victim.unit_type();
//          enum diplstate_type ds = pplayer_get_diplstate(pplayer, 
//                                                         victim.unit_owner()).type;
//
//          if (ds != diplstate_type.DS_WAR) {
//            continue;
//          }
//          if (victim == target) {
//            sucker = victim;
//            UNIT_LOG(LOGLEVEL_HUNT, missile, "found primary target %d(%d, %d)" +
//                     " dist %d", victim.id, TILE_XY(victim.tile), 
//                     pos.total_MC);
//            break; /* Our target! Get him!!! */
//          }
//          if (ut.move_rate + victim.moves_left > pos.total_MC
//              && ATTACK_POWER(victim) > DEFENCE_POWER(punit)
//              && (ut.move_type == SEA_MOVING
//                  || ut.move_type == AIR_MOVING)) {
//            /* Threat to our carrier. Kill it. */
//            sucker = victim;
//            UNIT_LOG(LOGLEVEL_HUNT, missile, "found aux target %d(%d, %d)",
//                     victim.id, TILE_XY(victim.tile));
//            break;
//          }
//        } }
//        if (sucker) {
//          break; /* found something - kill it! */
//        }
//      } pf_iterator_end;
//      pf_destroy_map(map);
//      if (sucker) {
//        if (find_unit_by_id(missile.transported_by)) {
//          unload_unit_from_transporter(missile);
//        }
//        ai_unit_goto(missile, sucker.tile);
//        sucker = find_unit_by_id(target_sanity); /* Sanity */
//        if (sucker && is_tiles_adjacent(sucker.tile, missile.tile)) {
//          ai_unit_attack(missile, sucker.tile);
//        }
//        target = find_unit_by_id(target_sanity); /* Sanity */
//        break; /* try next missile, if any */
//      }
//    } /* if */
//  } }
//}
//
///**************************************************************************
//  Manage a hunter unit. We assume it has AIUNIT_HUNTER role and a valid
//  target in punit.ai.hunt.
//
//  Returns false if we could not use unit. If we return true, unit might
//  be dead.
//**************************************************************************/
//boolean ai_hunter_manage(player pplayer, unit punit)
//{
//  unit target = find_unit_by_id(punit.ai.target);
//  int sanity_own = punit.id;
//  int sanity_target;
//
//  CHECK_UNIT(punit);
//  assert(punit.ai.ai_role == AIUNIT_HUNTER);
//
//  /* Check that target is valid. */
//  if (!target
//      || !goto_is_sane(punit, target.tile, true)
//      || map_get_city(target.tile)
//      || !is_player_dangerous(pplayer, target.unit_owner())) {
//    UNIT_LOG(LOGLEVEL_HUNT, punit, "target vanished");
//    ai_unit_new_role(punit, AIUNIT_NONE, null);
//    return false;
//  }
//  UNIT_LOG(LOGLEVEL_HUNT, punit, "hunting %d(%d, %d)",
//	   target.id, TILE_XY(target.tile));
//  sanity_target = target.id;
//
//  /* Check if we can nuke it */
//  ai_hunter_try_launch(pplayer, punit, target);
//  target = find_unit_by_id(sanity_target);
//  if (!target){
//    UNIT_LOG(LOGLEVEL_HUNT, punit, "mission accomplished");
//    ai_unit_new_role(punit, AIUNIT_NONE, null);
//    return true;
//  }
//
//  /* Go towards it. */
//  if (!ai_unit_goto(punit, target.tile)) {
//    return true;
//  }
//
//  /* Check if we can nuke it now */
//  ai_hunter_try_launch(pplayer, punit, target);
//  target = find_unit_by_id(sanity_target);
//  if (!target){
//    UNIT_LOG(LOGLEVEL_HUNT, punit, "mission accomplished");
//    ai_unit_new_role(punit, AIUNIT_NONE, null);
//    return true;
//  }
//
//  /* If we are adjacent - RAMMING SPEED! */
//  if (is_tiles_adjacent(punit.tile, target.tile)) {
//    ai_unit_attack(punit, target.tile);
//    target = find_unit_by_id(sanity_target);
//  }
//
//  if (!find_unit_by_id(sanity_own)) {
//    return true;
//  }
//  if (!target) {
//    UNIT_LOG(LOGLEVEL_HUNT, punit, "mission accomplished");
//    ai_unit_new_role(punit, AIUNIT_NONE, null);
//  }
//
//  return true;
//}
}