package ai;

public class Advmilitary{

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
//#include <string.h>
//
//#include "combat.h"
//#include "game.h"
//#include "government.h"
//#include "log.h"
//#include "map.h"
//
//#include "citytools.h"
//#include "cityturn.h"
//#include "gotohand.h"		/* warmap has been redeployed */
//#include "settlers.h"
//
//#include "aiair.h"
//#include "aicity.h"
//#include "aidata.h"
//#include "aidiplomat.h"
//#include "aihand.h"
//#include "aihunt.h"
//#include "ailog.h"
//#include "aitools.h"
//#include "aiunit.h"
//
//#include "advmilitary.h"
//
//static unsigned int assess_danger(city pcity);
//
///**************************************************************************
//  Choose the best unit the city can build to defend against attacker v.
//**************************************************************************/
//int ai_choose_defender_versus(city pcity, int v)
//{
//  int bestid = 0; /* ??? Zero is legal value! (Settlers by default) */
//  int j, m;
//  int best = 0;
//
//  simple_ai_unit_type_iterate(i) {
//    m = Unittype_P.unit_types[i].move_type;
//    if (City.can_build_unit(pcity, i) && (m == unit_move_type.LAND_MOVING || m == unit_move_type.SEA_MOVING)) {
//      j = get_virtual_defense_power(v, i, pcity.tile, false, false);
//      if (j > best || (j == best && Unittype_P.unit_build_shield_cost(i) <=
//                                    Unittype_P.unit_build_shield_cost(bestid))) {
//        best = j;
//        bestid = i;
//      }
//    }
//  } simple_ai_unit_type_iterate_end;
//
//  return bestid;
//}
//

//This function should assign a value to choice and want, where want is a value
//between 1 and 100.
//
//If choice is A_UNSET, this advisor doesn't want any particular tech
//researched at the moment.
//***********************************************************************/
//void military_advisor_choose_tech(player pplayer,
//				  ai_choice choice)
//{
//  /* This function hasn't been implemented yet. */
//  init_choice(choice);
//}
//
///**************************************************************************
//  Choose best attacker based on movement type. It chooses based on unit
//  desirability without regard to cost, unless costs are equal. This is
//  very wrong. FIXME, use amortize on time to build.
//**************************************************************************/
//static int ai_choose_attacker(city pcity,
//                                       enum unit_move_type which)
//{
//  int bestid = -1;
//  int best = -1;
//  int cur;
//
//  simple_ai_unit_type_iterate(i) {
//    cur = ai_unit_attack_desirability(i);
//    if (which == Unittype_P.unit_types[i].move_type) {
//      if (City.can_build_unit(pcity, i)
//          && (cur > best
//              || (cur == best
//                  && Unittype_P.unit_build_shield_cost(i)
//                     <= Unittype_P.unit_build_shield_cost(bestid)))) {
//        best = cur;
//        bestid = i;
//      }
//    }
//  } simple_ai_unit_type_iterate_end;
//
//  return (best <= 0 ? -1 : bestid);
//}
//
///**************************************************************************
//  Choose best defender based on movement type. It chooses based on unit
//  desirability without regard to cost, unless costs are equal. This is
//  very wrong. FIXME, use amortize on time to build.
//
//  We should only be passed with L_DEFEND_GOOD role for now, since this
//  is the only role being considered worthy of bodyguarding in findjob.
//**************************************************************************/
//static int ai_choose_bodyguard(city pcity,
//                                        enum unit_move_type move_type,
//                                        enum unit_role_id role)
//{
//  int bestid = -1;
//  int j, best = 0;
//
//  simple_ai_unit_type_iterate(i) {
//    /* Only consider units of given role, or any if L_LAST */
//    if (role != L_LAST) {
//      if (!unit_has_role(i, role)) {
//        continue;
//      }
//    }
//
//    /* Only consider units of same move type */
//    if (Unittype_P.unit_types[i].move_type != move_type) {
//      continue;
//    }
//
//    /* Now find best */
//    if (City.can_build_unit(pcity, i)) {
//      j = ai_unit_defence_desirability(i);
//      if (j > best || (j == best && Unittype_P.unit_build_shield_cost(i) <=
//                               Unittype_P.unit_build_shield_cost(bestid))) {
//        best = j;
//        bestid = i;
//      }
//    }
//  } simple_ai_unit_type_iterate_end;
//  return bestid;
//}
//

//Helper for assess_defense_quadratic and assess_defense_unit.
//***********************************************************************/
//static int base_assess_defense_unit(city pcity, unit punit,
//                                    boolean igwall, boolean quadratic,
//                                    int wall_value)
//{
//  int defense;
//  boolean do_wall = false;
//
//  if (!is_military_unit(punit)) {
//    return 0;
//  }
//
//  defense = get_defense_power(punit) * punit.hp;
//  if (!Unit.is_sailing_unit(punit)) {
//    defense *= punit.unit_type().firepower;
//    if (is_ground_unit(punit)) {
//      if (pcity) {
//        do_wall = (!igwall && City.city_got_citywalls(pcity));
//        defense *= 3;
//      }
//    }
//  }
//  defense /= POWER_DIVIDER;
//
//  if (quadratic) {
//    defense *= defense;
//  }
//
//  if (do_wall) {
//    defense *= wall_value;
//    defense /= 10;
//  }
//
//  return defense;
//}
//

//Need positive feedback in m_a_c_b and bodyguard routines. -- Syela
//***********************************************************************/
//int assess_defense_quadratic(city pcity)
//{
//  int defense = 0, walls = 0;
//  /* This can be an arg if needed, but we don't need to change it now. */
//  final boolean igwall = false;
//
//  /* wallvalue = 10, walls = 10,
//   * wallvalue = 40, walls = 20,
//   * wallvalue = 90, walls = 30 */
//
//  while (walls * walls < pcity.ai.wallvalue * 10)
//    walls++;
//
//  for (unit punit : pcity.tile.units.data) {
//    defense += base_assess_defense_unit(pcity, punit, igwall, false,
//                                        walls);
//  } }
//
//  if (defense > 1<<12) {
//    CITY_LOG(Log.LOG_VERBOSE, pcity, "Overflow danger in assess_defense_quadratic:" +
//             " %d", defense);
//    if (defense > 1<<15) {
//      defense = 1<<15; /* more defense than we know what to do with! */
//    }
//  }
//
//  return defense * defense;
//}
//
///**************************************************************************
//One unit only, mostly for findjob; handling boats correctly. 980803 -- Syela
//**************************************************************************/
//int assess_defense_unit(city pcity, unit punit, boolean igwall)
//{
//  return base_assess_defense_unit(pcity, punit, igwall, true,
//				  pcity.ai.wallvalue);
//}
//

//Most of the time we don't need/want positive feedback. -- Syela
//
//It's unclear whether this should treat settlers/caravans as defense. -- Syela
//TODO: It looks like this is never used while deciding if we should attack
//pcity, if we have pcity defended properly, so I think it should. --pasky
//***********************************************************************/
//static int assess_defense_backend(city pcity, boolean igwall)
//{
//  /* Estimate of our total city defensive might */
//  int defense = 0;
//
//  for (unit punit : pcity.tile.units.data) {
//    defense += assess_defense_unit(pcity, punit, igwall);
//  } }
//
//  return defense;
//}
//
///************************************************************************** 
//...
//**************************************************************************/
//int assess_defense(city pcity)
//{
//  return assess_defense_backend(pcity, false);
//}
//
///************************************************************************** 
//...
//**************************************************************************/
//static int assess_defense_igwall(city pcity)
//{
//  return assess_defense_backend(pcity, true);
//}
//
///************************************************************************** 
//  Compute actual danger depending on move rate of enemy and its distance.
//**************************************************************************/
//static unsigned int dangerfunct(int danger, int move_rate, int distance)
//{
//  /* XXX: I don't have a clue about these, it probably has something in common
//   * with the way how attack force is computed when attacker already spent some
//   * move points..? --pasky */
//  unsigned int num = move_rate * 4;
//  unsigned int denom = move_rate * 4;
//
//  if (move_rate == 0) {
//    return danger;
//  }
//
//  danger *= 2;
//
//  /* Turns to reach us.. */
//  while (distance >= move_rate) {
//    danger /= 2;
//    distance -= move_rate;
//  }
//
//  /* Moves in the last turn to reach us.. */
//  move_rate /= Unit_H.SINGLE_MOVE;
//  while (distance > 0 && distance >= move_rate) {
//    num *= 4;
//    denom *= 5;
//    distance -= move_rate;
//  }
//
//  /* Partial moves in the last turn.. */
//  while (distance > 0) {
//    denom += (denom + move_rate * 2) / (move_rate * 4);
//    distance--;
//  }
//
//  danger = (danger * num + (denom/2)) / denom;
//
//  return danger;
//}
//
///************************************************************************** 
//How dangerous a unit is for a city?
//**************************************************************************/
//static unsigned int assess_danger_unit(city pcity, unit punit)
//{
//  unsigned int danger;
//  boolean sailing;
//
//  if (unit_flag(punit, F_NO_LAND_ATTACK)) return 0;
//
//  sailing = Unit.is_sailing_unit(punit);
//  if (sailing && !Terrain.is_terrain_flag_near_tile(pcity.tile)) {
//    return 0;
//  }
//
//  danger = unit_att_rating(punit);
//  if (sailing && Effects.get_city_bonus(pcity, EFT_SEA_DEFEND) > 0) {
//    danger /= 2;
//  }
//  if (is_air_unit(punit) && Effects.get_city_bonus(pcity, EFT_AIR_DEFEND) > 0) {
//    danger /= 2;
//  }
//
//  return danger;
//}
//
///************************************************************************** 
//  Assess distance between punit and pcity.
//**************************************************************************/
//static int assess_distance(city pcity, unit punit,
//                           int move_rate)
//{
//  int distance = 0;
//  unit ferry = Game.find_unit_by_id(punit.transported_by);
//
//  if (Map.same_pos(punit.tile, pcity.tile)) {
//    return 0;
//  }
//
//  if (is_tiles_adjacent(punit.tile, pcity.tile)) {
//    distance = Unit_H.SINGLE_MOVE;
//  } else if (Unit.is_sailing_unit(punit)) {
//    distance = WARMAP_SEACOST(punit.tile);
//  } else if (!is_ground_unit(punit)) {
//    distance = Map.real_map_distance(punit.tile, pcity.tile)
//               * Unit_H.SINGLE_MOVE;
//  } else if (is_ground_unit(punit) && ferry) {
//    distance = WARMAP_SEACOST(ferry.tile); /* Sea travellers. */
//  } else if (unit_flag(punit, F_IGTER)) {
//    distance = Map.real_map_distance(punit.tile, pcity.tile);
//  } else {
//    distance = WARMAP_COST(punit.tile);
//  }
//
//  /* If distance = 9, a chariot is 1.5 turns away.  NOT 2 turns away. */
//  if (distance < Unit_H.SINGLE_MOVE) {
//    distance = Unit_H.SINGLE_MOVE;
//  }
//
//  return distance;
//}
//

//Call assess_danger() for all cities owned by pplayer.
//
//This is necessary to initialize some ai data before some ai calculations.
//***********************************************************************/
//void assess_danger_player(player pplayer)
//{
//  for (city pcity : pplayer.cities.data) {
//    assess_danger(pcity);
//  } }
//}
//

//  Set (overwrite) our want for a building. Syela tries to explain:
//   
//    My first attempt to allow ng_wa >= 200 led to stupidity in cities 
//    with no defenders and danger = 0 but danger > 0.  Capping ng_wa at 
//    100 + urgency led to a failure to buy walls as required.  Allowing 
//    want > 100 with !urgency led to the AI spending too much gold and 
//    falling behind on science.  I'm trying again, but this will require 
//    yet more tedious observation -- Syela
//   
//  The idea in this horrible function is that there is an enemy nearby
//  that can whack us, so let's build something that can defend against
//  him. If danger is urgent and overwhelming, danger is 200+, if it is
//  only overwhelming, set it depending on danger. If it is underwhelming,
//  set it to 100 pluss urgency.
//
//  This algorithm is very strange. But I created it by nesting up
//  Syela's convoluted if ... else logic, and it seems to work. -- Per
//***********************************************************************/
//static void ai_reevaluate_building(city pcity, int *value, 
//                                   unsigned int urgency, unsigned int danger, 
//                                   int defense)
//{
//  if (*value == 0 || danger <= 0) {
//    return;
//  }
//
//  *value = MAX(*value, 100 + MAX(0, urgency)); /* default */
//
//  if (urgency > 0 && danger > defense * 2) {
//    *value += 100;
//  } else if (defense != 0 && danger > defense) {
//    *value = MAX(danger * 100 / defense, *value);
//  }
//}
//

//  Create cached information about danger, urgency and grave danger to
//  our cities. 
//
//  Danger is a weight on how much power enemy units nearby have, which
//  is compared to our defence.
//
//  Urgency is the number of hostile units that can attack us in three
//  turns.
//
//  Grave danger is number of units that can attack us next turn.
//
//  Generates a warmap around pcity.
//
//  FIXME: We do not consider a paratrooper's mr_req and mr_sub
//  fields. Not a big deal, though.
//
//  FIXME: Due to the nature of assess_distance, a city will only be 
//  afraid of a boat laden with enemies if it stands on the coast (i.e.
//  is directly reachable by this boat).
//***********************************************************************/
//static unsigned int assess_danger(city pcity)
//{
//  int i;
//  int danger[5], defender[4];
//  player pplayer = City.city_owner(pcity);
//  boolean pikemen = false;
//  unsigned int urgency = 0;
//  int igwall_threat = 0;
//  tile ptile = pcity.tile;
//
//  memset(&danger, 0, sizeof(danger));
//
//  generate_warmap(pcity, null);	/* generates both land and sea maps */
//
//  pcity.ai.grave_danger = 0;
//  pcity.ai.diplomat_threat = false;
//  pcity.ai.has_diplomat = false;
//
//  for (unit punit : ptile.units.data) {
//    if (unit_flag(punit, Eunit_flag_id.F_DIPLOMAT)) pcity.ai.has_diplomat = true;
//    if (unit_flag(punit, F_PIKEMEN)) pikemen = true;
//  } }
//
//  for(player aplayer: Game.game.players){
//    if (!is_player_dangerous(City.city_owner(pcity), aplayer)) {
//      continue;
//    }
//    /* Note that we still consider the units of players we are not (yet)
//     * at war with. */
//
//    /* Look for enemy units */
//    for (unit punit : aplayer.units.data) {
//      int paramove = 0;
//      int move_rate = punit.move_rate();
//      unsigned int vulnerability = assess_danger_unit(pcity, punit);
//      int dist = assess_distance(pcity, punit, move_rate);
//      /* Although enemy units will not be in our cities,
//       * we might stll consider allies to be dangerous,
//       * so dist can be 0. */
//      boolean igwall = unit_really_ignores_citywalls(punit);
//
//      if (unit_flag(punit, F_PARATROOPERS)) {
//        paramove = punit.unit_type().paratroopers_range;
//      }
//
//      if ((is_ground_unit(punit) && vulnerability != 0)
//          || (is_ground_units_transport(punit))) {
//        if (dist <= move_rate * 3 || dist <= paramove + move_rate) {
//          urgency++;
//        }
//        if (dist <= move_rate || dist <= paramove + move_rate) {
//          pcity.ai.grave_danger++;
//        }
//      }
//      if (paramove > 0 && can_unit_paradrop(punit)) {
//        move_rate += paramove; /* gross simplification */
//      }
//
//      if (unit_flag(punit, F_HORSE)) {
//	if (pikemen) {
//	  vulnerability /= 2;
//	} else {
//	  () ai_wants_role_unit(pplayer, pcity, F_PIKEMEN,
//				    vulnerability * move_rate /
//				    MAX(dist * 2, 1));
//	}
//      }
//
//      if (unit_flag(punit, Eunit_flag_id.F_DIPLOMAT) && (dist <= 2 * move_rate)) {
//	pcity.ai.diplomat_threat = true;
//      }
//
//      vulnerability *= vulnerability; /* positive feedback */
//
//      if (!igwall) {
//        danger[1] += vulnerability * move_rate / MAX(dist, 1); /* walls */
//      } else if (Unit.is_sailing_unit(punit)) {
//        danger[2] += vulnerability * move_rate / MAX(dist, 1); /* coastal */
//      } else if (is_air_unit(punit) && !unit_flag(punit, F_NUCLEAR)) {
//        danger[3] += vulnerability * move_rate / MAX(dist, 1); /* SAM */
//      }
//      if (unit_flag(punit, F_MISSILE)) {
//        /* SDI */
//        danger[4] += vulnerability * move_rate / MAX(move_rate, dist);
//      }
//      if (!unit_flag(punit, F_NUCLEAR)) {
//        /* only SDI helps against NUCLEAR */
//        vulnerability = dangerfunct(vulnerability, move_rate, dist);
//        danger[0] += vulnerability;
//        if (igwall) {
//          igwall_threat += vulnerability;
//        }
//      }
//    } }
//  }
//
//  if (igwall_threat == 0) {
//    pcity.ai.wallvalue = 90;
//  } else {
//    pcity.ai.wallvalue = (danger[0] * 9 - igwall_threat * 8) 
//                           * 10 / (danger[0]);
//  }
//
//  /* Watch out for integer overflows */
//  for (i = 0; i < 5; i++) {
//    if (danger[i] < 0 || danger[i] > 1<<24) {
//      /* I hope never to see this! */
//      util.freelog(Log.LOG_ERROR, "Dangerous danger[%d] (%d) in %s.  Beware of " +
//              "overflow.", i, danger[i], pcity.name);
//      danger[i] = danger[i]>>2; /* reduce danger of overflow */
//    }
//  }
//
//  if (pcity.ai.grave_danger != 0) {
//    /* really, REALLY urgent to defend */
//    urgency += 10;
//  }
//
//  /* HACK: This needs changing if multiple improvements provide
//   * this effect. */
//  defender[0] = ai_find_source_building(pplayer, effect_type.EFT_LAND_DEFEND);
//  defender[1] = ai_find_source_building(pplayer, EFT_SEA_DEFEND);
//  defender[2] = ai_find_source_building(pplayer, EFT_AIR_DEFEND);
//  defender[3] = ai_find_source_building(pplayer, EFT_MISSILE_DEFEND);
//
//  if (defender[0] != Improvement.B_LAST) {
//    ai_reevaluate_building(pcity, &pcity.ai.building_want[defender[0]],
//	urgency, danger[1], assess_defense(pcity));
//  }
//  if (defender[1] != Improvement.B_LAST) {
//    ai_reevaluate_building(pcity, &pcity.ai.building_want[defender[1]],
//	urgency, danger[2], 
//	assess_defense_igwall(pcity));
//  }
//  if (defender[2] != Improvement.B_LAST) {
//    ai_reevaluate_building(pcity, &pcity.ai.building_want[defender[2]],
//	urgency, danger[3], 
//	assess_defense_igwall(pcity));
//  }
//  if (defender[3] != Improvement.B_LAST) {
//    ai_reevaluate_building(pcity, &pcity.ai.building_want[defender[3]],
//	urgency, danger[4], 
//	assess_defense_igwall(pcity));
//  }
//
//  pcity.ai.danger = danger[0];
//  pcity.ai.urgency = urgency;
//
//  return urgency;
//}
//
///************************************************************************** 
//  How much we would want that unit to defend a city? (Do not use this 
//  function to find bodyguards for ships or air units.)
//**************************************************************************/
//int ai_unit_defence_desirability(int i)
//{
//  int desire = get_unit_type(i).hp;
//  int attack = get_unit_type(i).attack_strength;
//  int defense = get_unit_type(i).defense_strength;
//
//  /* Sea and helicopters often have their firepower set to 1 when
//   * defending. We can't have such units as defenders. */
//  if (Unittype_P.unit_types[i].move_type != unit_move_type.SEA_MOVING
//      && Unittype_P.unit_types[i].move_type != unit_move_type.HELI_MOVING) {
//    /* Sea units get 1 firepower in Pearl Harbour,
//     * and helicopters very bad against air units */
//    desire *= get_unit_type(i).firepower;
//  }
//  desire *= defense;
//  desire += get_unit_type(i).move_rate / Unit_H.SINGLE_MOVE;
//  desire += attack;
//  if (Unittype_P.unit_type_flag(i, F_PIKEMEN)) {
//    desire += desire / 2;
//  }
//  if (Unittype_P.unit_type_flag(i, F_GAMELOSS)) {
//    desire /= 10; /* but might actually be worth it */
//  }
//  return desire;
//}
//
///************************************************************************** 
//  How much we would want that unit to attack with?
//**************************************************************************/
//int ai_unit_attack_desirability(int i)
//{
//  int desire = get_unit_type(i).hp;
//  int attack = get_unit_type(i).attack_strength;
//  int defense = get_unit_type(i).defense_strength;
//
//  desire *= get_unit_type(i).move_rate;
//  desire *= get_unit_type(i).firepower;
//  desire *= attack;
//  desire += defense;
//  if (Unittype_P.unit_type_flag(i, F_IGTER)) {
//    desire += desire / 2;
//  }
//  if (Unittype_P.unit_type_flag(i, F_GAMELOSS)) {
//    desire /= 10; /* but might actually be worth it */
//  }
//  if (Unittype_P.unit_type_flag(i, F_CITYBUSTER)) {
//    desire += desire / 2;
//  }
//  if (Unittype_P.unit_type_flag(i, F_IGTIRED)) {
//    desire += desire / 4;
//  }
//  if (Unittype_P.unit_type_flag(i, F_MARINES)) {
//    desire += desire / 4;
//  }
//  if (Unittype_P.unit_type_flag(i, F_IGWALL)) {
//    desire += desire / 4;
//  }
//  return desire;
//}
//
///************************************************************************** 
//  What would be the best defender for that city? Records the best defender 
//  type in choice. Also sets the technology want for the units we can't 
//  build yet.
//**************************************************************************/
//static void process_defender_want(player pplayer, city pcity,
//                                  unsigned int danger, ai_choice choice)
//{
//  boolean walls = City.city_got_citywalls(pcity);
//  boolean shore = Terrain.is_terrain_flag_near_tile(pcity.tile);
//  /* Technologies we would like to have. */
//  int tech_desire[unittype.U_LAST];
//  /* Our favourite unit. */
//  int best = 0;
//  int best_unit_type = 0; /* zero is settler but not a problem */
//
//  memset(tech_desire, 0, sizeof(tech_desire));
//  
//  simple_ai_unit_type_iterate (unit_type) {
//      int move_type = Unittype_P.unit_types[unit_type].move_type;
//    
//      /* How many technologies away it is? */
//      int tech_dist = num_unknown_techs_for_goal(pplayer,
//                        Unittype_P.unit_types[unit_type].tech_requirement);
//
//      /* How much we want the unit? */
//      int desire = ai_unit_defence_desirability(unit_type);
//
//      if (Unittype_P.unit_type_flag(unit_type, F_FIELDUNIT)) {
//        /* Causes unhappiness even when in defense, so not a good
//         * idea for a defender, unless it is _really_ good */
//       desire /= 2;
//      }      
//
//      desire /= POWER_DIVIDER/2; /* Good enough, no rounding errors. */
//      desire *= desire;
//      
//      if (City.can_build_unit(pcity, unit_type)) {
//        /* We can build the unit now... */
//      
//        if (walls && move_type == unit_move_type.LAND_MOVING) {
//          desire *= pcity.ai.wallvalue;
//          /* TODO: More use of POWER_FACTOR ! */
//          desire /= POWER_FACTOR;
//        }
//        
//        if ((desire > best ||
//             (desire == best && Unittype_P.unit_build_shield_cost(unit_type) <=
//                                Unittype_P.unit_build_shield_cost(best_unit_type)))
//            && Unittype_P.unit_build_shield_cost(unit_type) <= pcity.shield_stock + 40) {
//          best = desire;
//          best_unit_type = unit_type;
//        }
//        
//      } else if (tech_dist > 0 && (shore || move_type == unit_move_type.LAND_MOVING)
//                 && Unittype_P.unit_types[unit_type].tech_requirement != Tech_H.A_LAST) {
//        /* We first need to develop the tech required by the unit... */
//
//        /* Cost (shield equivalent) of gaining these techs. */
//        /* FIXME? Katvrr advises that this should be weighted more heavily in
//         * big danger. */
//        int tech_cost = total_bulbs_required_for_goal(pplayer,
//                          Unittype_P.unit_types[unit_type].tech_requirement) / 4
//                        / pplayer.cities.foo_list_size();
//        
//        /* Contrary to the above, we don't care if walls are actually built 
//         * - we're looking into the future now. */
//        if (move_type == unit_move_type.LAND_MOVING) {
//          desire *= pcity.ai.wallvalue;
//          desire /= POWER_FACTOR;
//        }
//
//        /* Yes, there's some similarity with kill_desire(). */
//        tech_desire[unit_type] = (desire * danger /
//				  (Unittype_P.unit_build_shield_cost(unit_type)
//				   + tech_cost));
//      }
//  } simple_ai_unit_type_iterate_end;
//  
//  if (!walls && Unittype_P.unit_types[best_unit_type].move_type == unit_move_type.LAND_MOVING) {
//    best *= pcity.ai.wallvalue;
//    best /= POWER_FACTOR;
//  }
//
//  if (best == 0) best = 1; /* Avoid division by zero below. */
//
//  /* Update tech_want for appropriate techs for units we want to build. */
//  simple_ai_unit_type_iterate (unit_type) {
//    if (tech_desire[unit_type] > 0) {
//      Tech_Type_id tech_req = Unittype_P.unit_types[unit_type].tech_requirement;
//      int desire = tech_desire[unit_type]
//                   * Unittype_P.unit_build_shield_cost(best_unit_type) / best;
//      
//      pplayer.ai.tech_want[tech_req] += desire;
//      
//      util.freelog(Log.LOG_DEBUG, "%s wants %s for defense with desire %d <%d>",
//              pcity.name, get_tech_name(pplayer, tech_req), desire,
//              tech_desire[unit_type]);
//    }
//  } simple_ai_unit_type_iterate_end;
//  
//  choice.choice = best_unit_type;
//  choice.want = danger;
//  choice.type = CT_DEFENDER;
//  return;
//}
//
///************************************************************************** 
//  This function decides, what unit would be best for erasing enemy. It is 
//  called, when we just want to kill something, we've found it but we don't 
//  have the unit for killing that built yet - here we'll choose the type of 
//  that unit.
//
//  We will also set increase the technology want to get units which could 
//  perform the job better.
//
//  I decided this funct wasn't confusing enough, so I made 
//  kill_something_with send it some more variables for it to meddle with. 
//  -- Syela
//
//  (x,y) is location of the target.
//  best_choice is pre-filled with our current choice, we only 
//  consider units of the same move_type as best_choice
//**************************************************************************/
//static void process_attacker_want(city pcity,
//                                  int value, int victim_unit_type,
//                                  int veteran, tile ptile,
//                                  ai_choice best_choice,
//                                  unit boat, int boattype)
//{
//  player pplayer = City.city_owner(pcity);
//  /* The enemy city.  acity == null means stray enemy unit */
//  city acity = Map.map_get_city(ptile);
//  boolean shore = Terrain.is_terrain_flag_near_tile(pcity.tile);
//  int orig_move_type = Unittype_P.unit_types[best_choice.choice].move_type;
//  int victim_count = 1;
//  int needferry = 0;
//  boolean unhap = ai_assess_military_unhappiness(pcity,
//                                              get_gov_pplayer(pplayer));
//
//  assert(orig_move_type == unit_move_type.SEA_MOVING || orig_move_type == unit_move_type.LAND_MOVING);
//
//  if (orig_move_type == unit_move_type.LAND_MOVING && !boat && boattype < unittype.U_LAST) {
//    /* cost of ferry */
//    needferry = Unittype_P.unit_build_shield_cost(boattype);
//  }
//  
//  if (!is_stack_vulnerable(ptile)) {
//    /* If it is a city, a fortress or an air base,
//     * we may have to whack it many times */
//    victim_count += unit_list_size(&(ptile.units));
//  }
//
//  simple_ai_unit_type_iterate (unit_type) {
//    Tech_Type_id tech_req = Unittype_P.unit_types[unit_type].tech_requirement;
//    int move_type = Unittype_P.unit_types[unit_type].move_type;
//    int tech_dist;
//    
//    if (tech_req != Tech_H.A_LAST) {
//      tech_dist = num_unknown_techs_for_goal(pplayer, tech_req);
//    } else {
//      tech_dist = 0;
//    }
//    
//    if ((move_type == unit_move_type.LAND_MOVING || (move_type == unit_move_type.SEA_MOVING && shore))
//        && tech_req != Tech_H.A_LAST
//        && (tech_dist > 0 ||
//            !City.can_build_unit_direct(pcity, Unittype_P.unit_types[unit_type].obsoleted_by))
//        && Unittype_P.unit_types[unit_type].attack_strength > 0 /* or we'll get SIGFPE */
//        && move_type == orig_move_type) {
//      /* TODO: Case for Airport. -- Raahul */
//      int will_be_veteran = (move_type == unit_move_type.LAND_MOVING
//	  || ai_find_source_building(pplayer, effect_type.EFT_SEA_VETERAN) != Improvement.B_LAST);
//      /* Cost (shield equivalent) of gaining these techs. */
//      /* FIXME? Katvrr advises that this should be weighted more heavily in big
//       * danger. */
//      int tech_cost = total_bulbs_required_for_goal(pplayer,
//                        Unittype_P.unit_types[unit_type].tech_requirement) / 4
//                      / pplayer.cities.foo_list_size();
//      int move_rate = Unittype_P.unit_types[unit_type].move_rate;
//      int move_time;
//      int bcost_balanced = build_cost_balanced(unit_type);
//      /* See description of kill_desire() for info about this variables. */
//      int bcost = Unittype_P.unit_build_shield_cost(unit_type);
//      int vuln;
//      int attack = unittype_att_rating(unit_type, will_be_veteran,
//                                       Unit_H.SINGLE_MOVE,
//                                       Unittype_P.unit_types[unit_type].hp);
//      /* Values to be computed */
//      int desire, want;
//      
//      /* Take into account reinforcements strength */
//      if (acity) attack += acity.ai.attack;
//
//      if (attack == 0) {
//       /* Yes, it can happen that a military unit has attack=1,
//        * for example militia with HP=1 (in civ1 ruleset). */ 
//	continue;
//      }
//      
//      attack *= attack;
//
//      if (Unittype_P.unit_type_flag(unit_type, F_IGTER)) {
//        /* TODO: Use something like IGTER_MOVE_COST. -- Raahul */
//        move_rate *= Unit_H.SINGLE_MOVE;
//      }
//
//      /* Set the move_time appropriatelly. */
//      if (acity) {
//        move_time = turns_to_enemy_city(unit_type, acity, move_rate,
//                                        (boattype < unittype.U_LAST), boat, boattype);
//      } else {
//        /* Target is in the field */
//        move_time = turns_to_enemy_unit(unit_type, move_rate, ptile,
//                                        victim_unit_type);
//      }
//
//      /* Estimate strength of the enemy. */
//      
//      vuln = unittype_def_rating_sq(unit_type, victim_unit_type,
//                                    ptile, false, veteran);
//
//      /* Not bothering to s/!vuln/!pdef/ here for the time being. -- Syela
//       * (this is noted elsewhere as terrible bug making warships yoyoing) 
//       * as the warships will go to enemy cities hoping that the enemy builds 
//       * something for them to kill*/
//      if (move_type != unit_move_type.LAND_MOVING && vuln == 0) {
//        desire = 0;
//        
//      } else if ((move_type == unit_move_type.LAND_MOVING || move_type == unit_move_type.HELI_MOVING)
//                 && acity && acity.ai.invasion == 2) {
//        desire = bcost * SHIELD_WEIGHTING;
//        
//      } else {
//        if (!acity) {
//          desire = kill_desire(value, attack, bcost, vuln, victim_count);
//        } else {
//          int city_attack = acity.ai.attack * acity.ai.attack;
//
//          /* See aiunit.c:find_something_to_kill() for comments. */
//          
//          desire = kill_desire(value, attack, (bcost + acity.ai.bcost), vuln,
//                               victim_count);
//          
//          if (value * city_attack > acity.ai.bcost * vuln) {
//            desire -= kill_desire(value, city_attack, acity.ai.bcost, vuln,
//                                  victim_count);
//          }
//        }
//      }
//      
//      desire -= tech_cost * SHIELD_WEIGHTING;
//      /* We can be possibly making some people of our homecity unhappy - then
//       * we don't want to travel too far away to our victims. */
//      /* TODO: Unify the 'unhap' dependency to some common function. */
//      desire -= move_time * (unhap ? SHIELD_WEIGHTING + 2 * TRADE_WEIGHTING
//                             : SHIELD_WEIGHTING);
//
//      want = military_amortize(pplayer, pcity, desire, MAX(1, move_time),
//                               bcost_balanced + needferry);
//      
//      if (want > 0) {
//        if (tech_dist > 0) {
//          /* This is a future unit, tell the scientist how much we need it */
//          pplayer.ai.tech_want[tech_req] += want;
//          
//          CITY_LOG(Log.LOG_DEBUG, pcity, "wants %s to build %s to punish %s@(%d,%d)" +
//                   " with desire %d", get_tech_name(pplayer, tech_req), 
//                   Unittype_P.unit_name(unit_type), (acity ? acity.name : "enemy"),
//                   TILE_XY(ptile), want);
//
//        } else if (want > best_choice.want) {
//          if (City.can_build_unit(pcity, unit_type)) {
//            /* This is a real unit and we really want it */
//
//            CITY_LOG(Log.LOG_DEBUG, pcity, "overriding %s(%d) with %s(%d)" +
//                     " [attack=%d,value=%d,move_time=%d,vuln=%d,bcost=%d]",
//                     Unittype_P.unit_name(best_choice.choice), best_choice.want,
//                     Unittype_P.unit_name(unit_type), want, attack, value, move_time,
//                     vuln, bcost);
//
//            best_choice.choice = unit_type;
//            best_choice.want = want;
//            best_choice.type = CT_ATTACKER;
//          } else if (City.can_build_improvement(pcity,
//                            get_unit_type(unit_type).impr_requirement)) {
//	    /* Building this unit requires a specific type of improvement.
//	     * So we build this improvement instead.  This may not be the
//	     * best behavior. */
//            int id = get_unit_type(unit_type).impr_requirement;
//
//            CITY_LOG(Log.LOG_DEBUG, pcity, "building %s to build %s",
//                     get_improvement_type(id).name,
//                     get_unit_type(unit_type).name);
//            best_choice.choice = id;
//            best_choice.want = want;
//            best_choice.type = CT_BUILDING;
//          } else {
//	    /* This should never happen? */
//	  }
//        }
//      }
//    }
//  } simple_ai_unit_type_iterate_end;
//}
//
///************************************************************************** 
//This function 
//1. receives (in myunit) a first estimate of what we would like to build.
//2. finds a potential victim for it.
//3. calculates the relevant stats of the victim.
//4. finds the best attacker for this type of victim (in process_attacker_want)
//5. if we still want to attack, records the best attacker in choice.
//**************************************************************************/
//static void kill_something_with(player pplayer, city pcity, 
//				unit myunit, ai_choice choice)
//{
//  ai_data ai = ai_data_get(pplayer);
//  /* Our attack rating (with reinforcements) */
//  int attack;
//  /* Benefit from fighting the target */
//  int benefit;
//  /* Enemy defender type */
//  int def_type;
//  /* Target coordinates */
//  tile ptile;
//  /* Our transport */
//  unit ferryboat = null;
//  /* Our target */
//  city acity;
//  /* Defender of the target city/tile */
//  unit pdef; 
//  /* Coordinates of the boat */
//  tile boat_tile = null;
//  /* Type of the boat (real or a future one) */
//  int boattype = unittype.U_LAST;
//  boolean go_by_boat;
//  /* Is the defender veteran? */
//  int def_vet;
//  struct ai_choice best_choice;
//
//  init_choice(&best_choice);
//  best_choice.choice = myunit.type;
//  best_choice.type = CT_ATTACKER;
//  best_choice.want = choice.want;
//
//  assert(is_military_unit(myunit) && !is_air_unit(myunit));
//
//  if (pcity.ai.danger != 0 && assess_defense(pcity) == 0) {
//    /* Defence comes first! */
//    return;
//  }
//
//  if (!is_ground_unit(myunit) && !Unit.is_sailing_unit(myunit)) {
//    util.freelog(Log.LOG_ERROR, "ERROR: Attempting to deal with non-trivial" +
//            " unit_type in kill_something_with");
//    return;
//  }
//
//  if (is_ground_unit(myunit)) {
//    int boatid = find_boat(pplayer, &boat_tile, 2);
//    ferryboat = Player_P.player_Game.find_unit_by_id(pplayer, boatid);
//  }
//
//  if (ferryboat) {
//    boattype = ferryboat.type;
//  } else {
//    boattype = best_role_unit_for_player(pplayer, L_FERRYBOAT);
//    if (boattype == unittype.U_LAST) {
//      /* We pretend that we can have the simplest boat -- to stimulate tech */
//      boattype = Unittype_P.get_role_unit(L_FERRYBOAT, 0);
//    }
//  }
//
//  best_choice.want = find_something_to_kill(pplayer, myunit, &ptile);
//
//  acity = Map.map_get_city(ptile);
//
//  if (myunit.id != 0) {
//    util.freelog(Log.LOG_ERROR, "ERROR: Non-virtual unit in kill_something_with!");
//    return;
//  }
//  
//  attack = unit_att_rating(myunit);
//  if (acity) {
//    attack += acity.ai.attack;
//  }
//  attack *= attack;
//  
//  if (acity) {
//    /* Our move rate */
//    int move_rate = Unittype_P.unit_types[myunit.type].move_rate;
//    /* Distance to target (in turns) */
//    int move_time;
//    /* Rating of enemy defender */
//    int vuln;
//
//    if (unit_flag(myunit, F_IGTER)) {
//      /* See comment in unit_move_turns */
//      move_rate *= 3;
//    }
//    
//    if (!HOSTILE_PLAYER(pplayer, ai, City.city_owner(acity))) {
//      /* Not a valid target */
//      return;
//    }
//
//    go_by_boat = (is_ground_unit(myunit)
//                  && !(WARMAP_COST(ptile) <= (Math.min(6, move_rate) * THRESHOLD)
//                       && goto_is_sane(myunit, acity.tile, true)));
//
//    move_time = turns_to_enemy_city(myunit.type, acity, move_rate, 
//                                    go_by_boat, ferryboat, boattype);
//
//    def_type = ai_choose_defender_versus(acity, myunit.type);
//    if (move_time > 1) {
//      def_vet = do_make_unit_veteran(acity, def_type);
//      vuln = unittype_def_rating_sq(myunit.type, def_type,
//                                    ptile, false, def_vet);
//      benefit = Unittype_P.unit_build_shield_cost(def_type);
//    } else {
//      vuln = 0;
//      benefit = 0;
//      def_vet = 0;
//    }
//
//    pdef = get_defender(myunit, ptile);
//    if (pdef) {
//      int m = unittype_def_rating_sq(myunit.type, pdef.type,
//                                     ptile, false, pdef.veteran);
//      if (vuln < m) {
//        vuln = m;
//        benefit = Unittype_P.unit_build_shield_cost(pdef.type);
//        def_vet = pdef.veteran;
//        def_type = pdef.type; 
//      }
//    }
//    if (COULD_OCCUPY(myunit) || TEST_BIT(acity.ai.invasion, 0)) {
//      /* bonus for getting the city */
//      benefit += 40;
//    }
//
//    /* end dealing with cities */
//  } else {
//
//    pdef = get_defender(myunit, ptile);
//    if (!pdef) {
//      /* Nobody to attack! */
//      return;
//    }
//
//    benefit = Unittype_P.unit_build_shield_cost(pdef.type);
//    go_by_boat = false;
//
//    def_type = pdef.type;
//    def_vet = pdef.veteran;
//    /* end dealing with units */
//  }
//  
//  if (!go_by_boat) {
//    process_attacker_want(pcity, benefit, def_type, def_vet, ptile, 
//                          &best_choice, null, unittype.U_LAST);
//  } else { 
//    /* Attract a boat to our city or retain the one that's already here */
//    best_choice.need_boat = true;
//    process_attacker_want(pcity, benefit, def_type, def_vet, ptile, 
//                          &best_choice, ferryboat, boattype);
//  }
//
//  if (best_choice.want > choice.want) {
//    /* We want attacker more that what we have selected before */
//    copy_if_better_choice(&best_choice, choice);
//    if (go_by_boat && !ferryboat) {
//      ai_choose_role_unit(pplayer, pcity, choice, L_FERRYBOAT, choice.want);
//    }
//    util.freelog(Log.LOG_DEBUG, "%s has chosen attacker, %s, want=%d",
//            pcity.name, Unittype_P.unit_types[choice.choice].name, choice.want);
//  } 
//}
//
///**********************************************************************
//... this function should assign a value to choice and want and type, 
//    where want is a value between 1 and 100.
//    if want is 0 this advisor doesn't want anything
//***********************************************************************/
//static void ai_unit_consider_bodyguard(city pcity,
//                                       int unit_type,
//                                       ai_choice choice)
//{
//  unit virtualunit;
//  player pplayer = City.city_owner(pcity);
//  unit aunit = null;
//  city acity = null;
//
//  virtualunit = create_unit_virtual(pplayer, pcity, unit_type,
//                                    do_make_unit_veteran(pcity, unit_type));
//
//  if (choice.want < 100) {
//    int want = look_for_charge(pplayer, virtualunit, &aunit, &acity);
//    if (want > choice.want) {
//      choice.want = want;
//      choice.choice = unit_type;
//      choice.type = CT_DEFENDER;
//    }
//  }
//  destroy_unit_virtual(virtualunit);
//}
//
///*********************************************************************
//  Before building a military unit, AI builds a barracks/port/airport
//  NB: It is assumed this function isn't called in an emergency 
//  situation, when we need a defender _now_.
// 
//  TODO: something more sophisticated, like estimating future demand
//  for military units, considering Sun Tzu instead.
//*********************************************************************/
//static void adjust_ai_unit_choice(city pcity, 
//                                  ai_choice choice)
//{
//  enum unit_move_type move_type;
//  player pplayer = City.city_owner(pcity);
//  int id;
//
//  /* Sanity */
//  if (!is_unit_choice_type(choice.type)) return;
//  if (Unittype_P.unit_type_flag(choice.choice, Eunit_flag_id.F_NONMIL)) return;
//  if (do_make_unit_veteran(pcity, choice.choice)) return;
//
//  move_type = get_unit_type(choice.choice).move_type;
//  switch(move_type) {
//  case unit_move_type.LAND_MOVING:
//    if ((id = ai_find_source_building(pplayer, effect_type.EFT_LAND_VETERAN)) != Improvement.B_LAST) {
//      choice.choice = id;
//      choice.type = CT_BUILDING;
//    }
//    break;
//  case unit_move_type.SEA_MOVING:
//    if ((id = ai_find_source_building(pplayer, effect_type.EFT_SEA_VETERAN)) != Improvement.B_LAST) {
//      choice.choice = id;
//      choice.type = CT_BUILDING;
//    }
//    break;
//  case unit_move_type.HELI_MOVING:
//  case unit_move_type.AIR_MOVING:
//    if ((id = ai_find_source_building(pplayer, effect_type.EFT_AIR_VETERAN)) != Improvement.B_LAST
//        && pcity.shield_surplus > Improvement.impr_build_shield_cost(id) / 10) {
//      /* Only build this if we have really high production */
//      choice.choice = id;
//      choice.type = CT_BUILDING;
//    }
//    break;
//  default:
//    util.freelog(Log.LOG_ERROR, "Unknown move_type in adjust_ai_unit_choice");
//    assert(false);
//  }
//}
//

//    This function selects either a defender or an attacker to be built.
//    It records its choice into ai_choice struct.
//    If coice.want is 0 this advisor doesn't want anything.
//***********************************************************************/
//void military_advisor_choose_build(player pplayer, city pcity,
//				   ai_choice choice)
//{
//  int unit_type;
//  unsigned int our_def, danger, urgency;
//  tile ptile = pcity.tile;
//  unit virtualunit;
//
//  init_choice(choice);
//
//  /* Note: assess_danger() creates a warmap for us. */
//  urgency = assess_danger(pcity);
//  /* Changing to quadratic to stop AI from building piles 
//   * of small units -- Syela */
//  /* It has to be AFTER assess_danger thanks to wallvalue. */
//  our_def = assess_defense_quadratic(pcity); 
//  util.freelog(Log.LOG_DEBUG, "%s: danger = %d, grave_danger = %d, our_def = %d",
//          pcity.name, pcity.ai.danger, pcity.ai.grave_danger, our_def);
//
//  ai_choose_diplomat_defensive(pplayer, pcity, choice, our_def);
//
//  /* Otherwise no need to defend yet */
//  if (pcity.ai.danger != 0) { 
//    int num_defenders = ptile.units.foo_list_size();
//    int land_id, sea_id, air_id;
//
//    /* First determine the danger.  It is measured in percents of our 
//     * defensive strength, capped at 200 + urgency */
//    if (pcity.ai.danger >= our_def) {
//      if (urgency == 0) {
//        /* don't waste money */
//        danger = 100;
//      } else if (our_def == 0) {
//        danger = 200 + urgency;
//      } else {
//        danger = Math.min(200, 100 * pcity.ai.danger / our_def) + urgency;
//      }
//    } else { 
//      danger = 100 * pcity.ai.danger / our_def;
//    }
//    if (pcity.shield_surplus <= 0 && our_def != 0) {
//      /* Won't be able to support anything */
//      danger = 0;
//    }
//
//    /* FIXME: 1. Will tend to build walls beofre coastal irrespectfully what
//     * type of danger we are facing
//     * 2. (80 - pcity.shield_stock) * 2 below is hardcoded price of walls */
//    /* We will build walls if we can and want and (have "enough" defenders or
//     * can just buy the walls straight away) */
//
//    /* HACK: This needs changing if multiple improvements provide
//     * this effect. */
//    land_id = ai_find_source_building(pplayer, effect_type.EFT_LAND_DEFEND);
//    sea_id = ai_find_source_building(pplayer, EFT_SEA_DEFEND);
//    air_id = ai_find_source_building(pplayer, EFT_AIR_DEFEND);
//
//    if (land_id != Improvement.B_LAST
//	&& pcity.ai.building_want[land_id] != 0 && our_def != 0 
//        && City.can_build_improvement(pcity, land_id)
//        && (danger < 101 || num_defenders > 1
//            || (pcity.ai.grave_danger == 0 
//                && pplayer.economic.gold > (80 - pcity.shield_stock) * 2)) 
//        && ai_fuzzy(pplayer, true)) {
//      /* NB: great wall is under domestic */
//      choice.choice = land_id;
//      /* building_want is hacked by assess_danger */
//      choice.want = pcity.ai.building_want[land_id];
//      if (urgency == 0 && choice.want > 100) {
//        choice.want = 100;
//      }
//      choice.type = CT_BUILDING;
//
//    } else if (sea_id != Improvement.B_LAST
//	       && pcity.ai.building_want[sea_id] != 0 && our_def != 0 
//               && City.can_build_improvement(pcity, sea_id) 
//               && (danger < 101 || num_defenders > 1) 
//               && ai_fuzzy(pplayer, true)) {
//      choice.choice = sea_id;
//      /* building_want is hacked by assess_danger */
//      choice.want = pcity.ai.building_want[sea_id];
//      if (urgency == 0 && choice.want > 100) {
//        choice.want = 100;
//      }
//      choice.type = CT_BUILDING;
//
//    } else if (air_id != Improvement.B_LAST
//	       && pcity.ai.building_want[air_id] != 0 && our_def != 0 
//               && City.can_build_improvement(pcity, air_id) 
//               && (danger < 101 || num_defenders > 1) 
//               && ai_fuzzy(pplayer, true)) {
//      choice.choice = air_id;
//      /* building_want is hacked by assess_danger */
//      choice.want = pcity.ai.building_want[air_id];
//      if (urgency == 0 && choice.want > 100) {
//        choice.want = 100;
//      }
//      choice.type = CT_BUILDING;
//
//    } else if (danger > 0 && num_defenders <= urgency) {
//      /* Consider building defensive units units */
//      process_defender_want(pplayer, pcity, danger, choice);
//      if (urgency == 0 && Unittype_P.unit_types[choice.choice].defense_strength == 1) {
//        if (Effects.get_city_bonus(pcity, EFT_LAND_REGEN) > 0) {
//          /* unlikely */
//          choice.want = Math.min(49, danger);
//        } else {
//          choice.want = Math.min(25, danger);
//        }
//      } else {
//        choice.want = danger;
//      }
//      util.freelog(Log.LOG_DEBUG, "%s wants %s to defend with desire %d.",
//                    pcity.name, get_unit_type(choice.choice).name,
//                    choice.want);
//    }
//  } /* ok, don't need to defend */
//
//  if (pcity.shield_surplus <= 0 
//      || pcity.ppl_unhappy[4] > pcity.ppl_unhappy[2]) {
//    /* Things we consider below are not life-saving so we don't want to 
//     * build them if our populace doesn't feel like it */
//    return;
//  }
//
//  /* Consider making a land bodyguard */
//  unit_type = ai_choose_bodyguard(pcity, unit_move_type.LAND_MOVING, L_DEFEND_GOOD);
//  if (unit_type >= 0) {
//    ai_unit_consider_bodyguard(pcity, unit_type, choice);
//  }
//
//  /* If we are in severe danger, don't consider attackers. This is probably
//     too general. In many cases we will want to buy attackers to counterattack.
//     -- Per */
//  if (choice.want > 100 && pcity.ai.grave_danger > 0) {
//    CITY_LOG(LOGLEVEL_BUILD, pcity, "severe danger (want %d), force defender",
//             choice.want);
//    return;
//  }
//
//  /* Consider making an offensive diplomat */
//  ai_choose_diplomat_offensive(pplayer, pcity, choice);
//
//  /* Consider making a sea bodyguard */
//  unit_type = ai_choose_bodyguard(pcity, unit_move_type.SEA_MOVING, L_DEFEND_GOOD);
//  if (unit_type >= 0) {
//    ai_unit_consider_bodyguard(pcity, unit_type, choice);
//  }
//
//  /* Consider making an airplane */
//  () ai_choose_attacker_air(pplayer, pcity, choice);
//
//  /* Check if we want a sailing attacker. Have to put sailing first
//     before we mung the seamap */
//  unit_type = ai_choose_attacker(pcity, unit_move_type.SEA_MOVING);
//  if (unit_type >= 0) {
//    virtualunit = create_unit_virtual(pplayer, pcity, unit_type,
//                                      do_make_unit_veteran(pcity, unit_type));
//    kill_something_with(pplayer, pcity, virtualunit, choice);
//    destroy_unit_virtual(virtualunit);
//  }
//
//  /* Consider a land attacker */
//  unit_type = ai_choose_attacker(pcity, unit_move_type.LAND_MOVING);
//  if (unit_type >= 0) {
//    virtualunit = create_unit_virtual(pplayer, pcity, unit_type, 1);
//    kill_something_with(pplayer, pcity, virtualunit, choice);
//    destroy_unit_virtual(virtualunit);
//  }
//
//  /* Consider a hunter */
//  ai_hunter_choice(pplayer, pcity, choice);
//
//  /* Consider veteran level enhancing buildings before non-urgent units */
//  adjust_ai_unit_choice(pcity, choice);
//
//  if (choice.want <= 0) {
//    CITY_LOG(LOGLEVEL_BUILD, pcity, "military advisor has no advice");
//  } else if (is_unit_choice_type(choice.type)) {
//    CITY_LOG(LOGLEVEL_BUILD, pcity, "military advisor choice: %s (want %d)",
//             Unittype_P.unit_types[choice.choice].name, choice.want);
//  } else {
//    CITY_LOG(LOGLEVEL_BUILD, pcity, "military advisor choice: %s (want %d)",
//             Improvement.improvement_types[choice.choice].name, choice.want);
//  }
//}
}