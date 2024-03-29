package ai;

import common.City;
import common.event_type;


public class Aicity{

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
//#include "city.h"
//#include "combat.h"
//#include "effects.h"
//#include "events.h"
//#include "fcintl.h"
//#include "Game.game.h"
//#include "government.h"
//#include "log.h"
//#include "Map.map.h"
//#include "packets.h"
//#include "player.h"
//#include "rand.h"
//#include "shared.h"
//#include "support.h"
//#include "unit.h"
//
//#include "cityhand.h"
//#include "citytools.h"
//#include "cityturn.h"
//#include "gotohand.h"
//#include "plrhand.h"
//#include "settlers.h"
//#include "unithand.h"
//#include "unittools.h"
//
//#include "advdomestic.h"
//#include "advmilitary.h"
//#include "aidata.h"
//#include "aihand.h"
//#include "ailog.h"
//#include "aitools.h"
//#include "aiunit.h"
//
//#include "aicity.h"
//
///* Iterate over cities within a certain range around a given city
// * (city_here) that exist within a given city list. */
//#define city_range_iterate(city_here, list, range, city)            \
//{                                                                   \
//  Continent_id continent = map_get_continent(city_here.tile);	    \
//  for (city city : list.data) {                                   \
//    if ((range == EFR_CITY && city == city_here)                    \
//        || (range == EFR_LOCAL && city == city_here)                \
//        || (range == EFR_CONTINENT                                  \
//            && map_get_continent(city.tile) == continent)	    \
//        || (range == EFR_PLAYER)) {
//#define city_range_iterate_end \
//  } } } }
//
//#define CITY_EMERGENCY(pcity)                        \
// (pcity.shield_surplus < 0 || City.city_unhappy(pcity)   \
//  || pcity.food_stock + pcity.food_surplus < 0)
//public static final int LOG_BUY = Log.LOG_DEBUG;
//
//static void resolve_city_emergency(player pplayer, city pcity);
//static void ai_sell_obsolete_buildings(city pcity);
//
///**************************************************************************
//  This calculates the usefulness of pcity to us. Note that you can pass
//  another player's ai_data structure here for evaluation by different
//  priorities.
//**************************************************************************/
//int ai_eval_calc_city(city pcity, ai_data ai)
//{
//  int i = (pcity.food_surplus * ai.food_priority
//           + pcity.shield_surplus * ai.shield_priority
//           + pcity.luxury_total * ai.luxury_priority
//           + pcity.tax_total * ai.gold_priority
//           + pcity.science_total * ai.science_priority
//           + pcity.ppl_happy[4] * ai.happy_priority
//           - pcity.ppl_unhappy[4] * ai.unhappy_priority
//           - pcity.ppl_angry[4] * ai.angry_priority
//           - pcity.pollution * ai.pollution_priority);
//
//  if (pcity.food_surplus < 0 || pcity.shield_surplus < 0) {
//    /* The city is unmaintainable, it can't be good */
//    i = Math.min(i, 0);
//  }
//
//  return i;
//}
//
///**************************************************************************
//  Calculates city want from some input values.
//**************************************************************************/
//static inline int city_want(player pplayer, city acity, 
//                            ai_data ai)
//{
//  int want = 0, food, trade, shields, lux, sci, tax;
//
//  get_food_trade_shields(acity, &food, &trade, &shields);
//  trade -= city_corruption(acity, trade);
//  shields -= city_waste(acity, shields);
//  get_tax_income(pplayer, trade, &sci, &lux, &tax);
//  sci += (acity.specialists[specialist_type.SP_SCIENTIST]
//	  * Game.game.rgame.specialists[specialist_type.SP_SCIENTIST].bonus);
//  lux += (acity.specialists[specialist_type.SP_ELVIS]
//	  * Game.game.rgame.specialists[specialist_type.SP_ELVIS].bonus);
//  tax += (acity.specialists[specialist_type.SP_TAXMAN]
//	  * Game.game.rgame.specialists[specialist_type.SP_TAXMAN].bonus);
//
//	for (int it = 0; i < game.num_impr_types; i++) {
//		if((acity).improvements[i] == Improvement.I_NONE) {
//			continue;
//		}
//    tax -= improvement_upkeep(acity, i);
//  } ;
//
//  want += food * ai.food_priority;
//  if (shields != 0) {
//    want += ((shields * get_city_shield_bonus(acity)) / 100)
//            * ai.shield_priority;
//    want -= city_pollution(acity, shields) * ai.pollution_priority;
//  }
//  if (lux > 0) {
//    want += ((lux * get_city_luxury_bonus(acity)) / 100)
//            * ai.luxury_priority;
//  }
//  if (sci > 0) {
//    want += ((sci * get_city_science_bonus(acity)) / 100)
//            * ai.science_priority;
//  }
//  if (tax > 0) {
//    tax *= get_city_tax_bonus(acity) / 100;
//  }
//  want += tax * ai.gold_priority;
//
//  return want;
//}
//
///**************************************************************************
//  Calculates want for some buildings by actually adding the building and
//  measuring the effect.
//**************************************************************************/
//static int base_want(player pplayer, city pcity, 
//                     int id)
//{
//  ai_data ai = ai_data_get(pplayer);
//  int final_want = 0;
//  city capital = pplayer.find_palace();
//
//  if (ai.impr_calc[id] == AI_IMPR_ESTIMATE) {
//    return 0; /* Nothing to calculate here. */
//  }
//
//  /* Add the improvement */
//  city_add_improvement(pcity, id);
//  if (Improvement.is_wonder(id)) {
//    Game.game.global_wonders[id] = pcity.id;
//  }
//
//  /* Stir, then compare notes */
//  city_range_iterate(pcity, pplayer.cities, ai.impr_range[id], acity) {
//    final_want += city_want(pplayer, acity, ai) - acity.ai.worth;
//  } city_range_iterate_end;
//
//  /* Restore */
//  City.city_remove_improvement(pcity, id);
//  if (Improvement.is_wonder(id)) {
//    Game.game.global_wonders[id] = 0;
//  }
//
//  /* Ensure that we didn't inadvertantly move our palace */
//  if (pplayer.find_palace() != capital) {
//    city_add_improvement(capital, get_building_for_effect(EFT_CAPITAL_CITY));
//  }
//
//  return final_want;
//}
//
///************************************************************************** 
//  Calculate effects. A few base variables:
//    c - number of cities we have in current range
//    u - units we have of currently affected type
//    v - the want for the improvement we are considering
//
//  This function contains a whole lot of WAGs. We ignore cond_* for now,
//  thinking that one day we may fulfill the cond_s anyway. In general, we
//  first add bonus for city improvements, then for wonders.
//
//  IDEA: Calculate per-continent aggregates of various data, and use this
//  for wonders below for better wonder placements.
//**************************************************************************/
//static void adjust_building_want_by_effects(city pcity, 
//                                            int id)
//{
//  player pplayer = City.city_owner(pcity);
//  impr_type pimpr = get_improvement_type(id);
//  int v = 0;
//  int cities[EFR_LAST];
//  int nplayers = Game.game.nplayers
//                 - Game.game.nbarbarians
//                 - team_count_members_alive(pplayer.team);
//  ai_data ai = ai_data_get(pplayer);
//  tile ptile = pcity.tile;
//  boolean capital = pcity.is_capital();
//  government gov = get_gov_pplayer(pplayer);
//
//  /* Base want is calculated above using a more direct approach. */
//  v += base_want(pplayer, pcity, id);
//  if (v != 0) {
//    CITY_LOG(Log.LOG_DEBUG, pcity, "%s base_want is %d (range=%d)", 
//             Improvement.get_improvement_name(id), v, ai.impr_range[id]);
//  }
//
//  /* Find number of cities per range.  */
//  cities[EFR_PLAYER] = pplayer.cities.foo_list_size();
//  cities[EFR_WORLD] = cities[EFR_PLAYER]; /* kludge. */
//
//  cities[EFR_CONTINENT] = ai.stats.cities[ptile.continent];
//
//  cities[EFR_CITY] = 1;
//  cities[EFR_LOCAL] = 0;
//
//  /* Calculate desire value. */
//  effect_type_vector_iterate(get_building_effect_types(id), ptype) {
//    effect_list_iterate(*get_building_effects(id, *ptype), peff) {
//      if (is_effect_useful(TARGET_BUILDING, pplayer, pcity, id,
//			   null, id, peff)) {
//	int amount = peff.value, c = cities[peff.range];
//        city palace = pplayer.find_palace();
//
//	switch (*ptype) {
//	  case EFT_PROD_TO_GOLD:
//            /* Since coinage contains some entirely spurious ruleset values,
//             * we need to return here with some spurious want. */
//            pcity.ai.building_want[id] = TRADE_WEIGHTING;
//            return;
//          /* These have already been evaluated in base_want() */
//          case EFT_CAPITAL_CITY:
//	  case EFT_UPKEEP_FREE:
//	  case EFT_POLLU_POP_PCT:
//	  case EFT_POLLU_PROD_PCT:
//	  case EFT_TRADE_PER_TILE:
//	  case EFT_TRADE_INC_TILE:
//	  case EFT_FOOD_INC_TILE:
//	  case EFT_TRADE_ADD_TILE:
//	  case EFT_PROD_INC_TILE:
//	  case EFT_PROD_PER_TILE:
//	  case EFT_PROD_ADD_TILE:
//	  case EFT_FOOD_PER_TILE:
//	  case EFT_FOOD_ADD_TILE:
//	  case EFT_PROD_BONUS:
//          case EFT_TAX_BONUS:
//          case EFT_SCIENCE_BONUS:
//	  case EFT_LUXURY_BONUS:
//          case EFT_CORRUPT_PCT:
//          case EFT_WASTE_PCT:
//	    break;
//
//          /* WAG evaluated effects */
//	  case EFT_INCITE_DIST_PCT:
//            if (palace) {
//              v += Map.real_map_distance(pcity.tile, palace.tile);
//            }
//            break;
//	  case EFT_MAKE_HAPPY:
//            /* TODO */
//            break;
//	  case EFT_UNIT_RECOVER:
//            /* TODO */
//            break;
//	  case EFT_NO_UNHAPPY:
//            v += (pcity.specialists[specialist_type.SP_ELVIS] + pcity.ppl_unhappy[4]) * 20;
//            break;
//	  case EFT_FORCE_CONTENT:
//	    if (!Government.government_has_flag(gov, G_NO_UNHAPPY_CITIZENS)) {
//	      v += (pcity.ppl_unhappy[4] + pcity.specialists[specialist_type.SP_ELVIS]) * 20;
//	      v += 5 * c;
//	    }
//	    break;
//	  case EFT_MAKE_CONTENT_MIL_PER:
//	  case EFT_MAKE_CONTENT:
//	    if (!Government.government_has_flag(gov, G_NO_UNHAPPY_CITIZENS)) {
//              v += Math.min(pcity.ppl_unhappy[4] + pcity.specialists[specialist_type.SP_ELVIS],
//                       amount) * 20;
//              v += Math.min(amount, 5) * c;
//	    }
//	    break;
//	  case EFT_MAKE_CONTENT_MIL:
//	    if (!Government.government_has_flag(gov, G_NO_UNHAPPY_CITIZENS)) {
//	      v += pcity.ppl_unhappy[4] * amount
//		* MAX(pcity.units_supported.foo_list_size()
//		    - gov.free_happy, 0) * 2;
//	      v += c * MAX(amount + 2 - gov.free_happy, 1);
//	    }
//	    break;
//	  case EFT_TECH_PARASITE:
//	    v += (total_bulbs_required(pplayer) * (100 - Game.game.freecost)
//		* (nplayers - amount)) / (nplayers * amount * 100);
//	    break;
//	  case EFT_GROWTH_FOOD:
//	    v += c * 4 + (amount / 7) * pcity.food_surplus;
//	    break;
//	  case EFT_AIRLIFT:
//            /* FIXME: We need some smart algorithm here. The below is 
//             * totally braindead. */
//	    v += c + Math.min(ai.stats.units.land, 13);
//	    break;
//	  case EFT_ANY_GOVERNMENT:
//	    if (!can_change_to_government(pplayer, ai.goal.govt.idx)) {
//	      v += Math.min(Math.min(ai.goal.govt.val, 65),
//		  num_unknown_techs_for_goal(pplayer, ai.goal.govt.req) * 10);
//	    }
//	    break;
//	  case EFT_ENABLE_NUKE:
//	    /* Treat nuke as a Cruise Missile upgrade */
//	    v += 20 + ai.stats.units.missiles * 5;
//	    break;
//	  case EFT_ENABLE_SPACE:
//	    if (Game.game.spacerace) {
//	      v += 5;
//	      if (ai.diplomacy.production_leader == pplayer) {
//		v += 100;
//	      }
//	    }
//	    break;
//	  case EFT_GIVE_IMM_TECH:
//	    if (!ai_wants_no_science(pplayer)) {
//	      v += amount * (Game.game.researchcost + 1);
//	    }
//	    break;
//	  case EFT_HAVE_EMBASSIES:
//	    v += 5 * nplayers;
//	    break;
//	  case EFT_REVEAL_CITIES:
//	  case EFT_NO_ANARCHY:
//	    break;  /* Useless for AI */
//	  case EFT_NO_SINK_DEEP:
//	    v += 15 + ai.stats.units.triremes * 5;
//	    break;
//	  case EFT_NUKE_PROOF:
//	    if (ai.threats.nuclear) {
//	      v += pcity.size * ptile.units.foo_list_size() * (capital + 1);
//	    }
//	    break;
//	  case EFT_REVEAL_MAP:
//	    if (!ai.explore.land_done || !ai.explore.sea_done) {
//	      v += 10;
//	    }
//	    break;
//	  case EFT_SIZE_UNLIMIT:
//	    amount = 20; /* really big city */
//	    /* there not being a break here is deliberate, mind you */
//	  case EFT_SIZE_ADJ: 
//            if (!city_can_grow_to(pcity, pcity.size + 1)) {
//	      v += pcity.food_surplus * ai.food_priority * amount;
//              if (pcity.size == Game.game.aqueduct_size) {
//                v += 30 * pcity.food_surplus;
//              }
//	    }
//	    v += c * amount * 4 / Game.game.aqueduct_size;
//	    break;
//	  case EFT_SS_STRUCTURAL:
//	  case EFT_SS_COMPONENT:
//	  case EFT_SS_MODULE:
//	    if (Game.game.spacerace
//	        /* If someone has started building spaceship already or
//		 * we have chance to win a spacerace */
//	        && (ai.diplomacy.spacerace_leader
//		    || ai.diplomacy.production_leader == pplayer)) {
//	      v += 95;
//	    }
//	    break;
//	  case EFT_SPY_RESISTANT:
//	    /* Uhm, problem: City Wall has -50% here!! */
//	    break;
//	  case EFT_SEA_MOVE:
//	    v += ai.stats.units.sea * 8 * amount;
//	    break;
//	  case EFT_UNIT_NO_LOSE_POP:
//	    v += unit_list_size(&(ptile.units)) * 2;
//	    break;
//	  case EFT_LAND_REGEN:
//	    v += 15 * c + ai.stats.units.land * 3;
//	    break;
//	  case EFT_SEA_REGEN:
//	    v += 15 * c + ai.stats.units.sea * 3;
//	    break;
//	  case EFT_AIR_REGEN:
//	    v += 15 * c + ai.stats.units.air * 3;
//	    break;
//	  case EFT_LAND_VET_COMBAT:
//	    v += 2 * c + ai.stats.units.land * 2;
//	    break;
//	  case EFT_LAND_VETERAN:
//	    v += 5 * c + ai.stats.units.land;
//	    break;
//	  case EFT_SEA_VETERAN:
//	    v += 5 * c + ai.stats.units.sea;
//	    break;
//	  case EFT_AIR_VETERAN:
//	    v += 5 * c + ai.stats.units.air;
//	    break;
//	  case EFT_UPGRADE_UNIT:
//	    v += ai.stats.units.upgradeable;
//	    if (amount == 1) {
//	      v *= 2;
//	    } else if (amount == 2) {
//	      v *= 3;
//	    } else {
//	      v *= 4;
//	    }
//	    break;
//	  case EFT_SEA_DEFEND:
//	    if (ai_handicap(pplayer, H_DEFENSIVE)) {
//	      v += amount / 10; /* make AI slow */
//	    }
//            if (Terrain_H.is_ocean(pcity.tile.terrain)) {
//              v += ai.threats.ocean[-map_get_continent(pcity.tile)]
//                   ? amount/5 : amount/20;
//            } else {
//              for(tile tile2: util.adjc_tile_iterate(pcity.tile)) {
//                if (Terrain_H.is_ocean(tile2.terrain)) {
//                  if (ai.threats.ocean[-map_get_continent(tile2)]) {
//                    v += amount/5;
//		    break;
//                  }
//                }
//              }
//            }
//	    v += (amount/20 + ai.threats.invasions - 1) * c; /* for wonder */
//	    if (capital && ai.threats.invasions) {
//	      v += amount; /* defend capital! */
//	    }
//	    break;
//	  case EFT_AIR_DEFEND:
//	    if (ai_handicap(pplayer, H_DEFENSIVE)) {
//	      v += amount / 15; /* make AI slow */
//	    }
//	    v += (ai.threats.air && ai.threats.continent[ptile.continent]) 
//	      ? amount/10 * 5 + amount/10 * c : c;
//	    break;
//	  case EFT_MISSILE_DEFEND:
//	    if (ai.threats.missile
//		&& (ai.threats.continent[ptile.continent] || capital)) {
//	      v += amount/10 * 5 + (amount/10 - 1) * c;
//	    }
//	    break;
//	  case effect_type.EFT_LAND_DEFEND:
//	    if (ai_handicap(pplayer, H_DEFENSIVE)) {
//	      v += amount / 10; /* make AI slow */
//	    }
//	    if (ai.threats.continent[ptile.continent]
//		|| capital
//		|| (ai.threats.invasions
//		  && is_water_adjacent_to_tile(pcity.tile))) {
//              v += amount / (!ai.threats.igwall ? (15 - capital * 5) : 15);
//	    }
//	    v += (1 + ai.threats.invasions + !ai.threats.igwall) * c;
//	    break;
//	  case EFT_NO_INCITE:
//	    if (!Government.government_has_flag(gov, G_UNBRIBABLE)) {
//	      v += MAX((Game.game.diplchance * 2 - Game.game.incite_cost.total_factor) / 2
//		  - Game.game.incite_cost.improvement_factor * 5
//		  - Game.game.incite_cost.unit_factor * 5, 0);
//	    }
//	    break;
//          case EFT_REGEN_REPUTATION:
//            v += (GAME_MAX_REPUTATION - pplayer.reputation) * 50 / 
//	            GAME_MAX_REPUTATION + 
//	          amount * 4;
//            break;
//	  case EFT_GAIN_AI_LOVE:
//            for(player aplayer: Game.game.players){
//              if (aplayer.ai.control) {
//                if (ai_handicap(pplayer, H_DEFENSIVE)) {
//                  v += amount / 10;
//                } else {
//                  v += amount / 20;
//                }
//              }
//            }
//            break;
//	  case EFT_LAST:
//	    util.freelog(Log.LOG_ERROR, "Bad effect type.");
//	    break;
//	}
//      }
//    } }
//  } effect_type_vector_iterate_end;
//
//  /* Reduce want if building gets obsoleted soon */
//  if (tech_exists(pimpr.obsolete_by)) {
//    v -= v / MAX(1, num_unknown_techs_for_goal(pplayer, pimpr.obsolete_by));
//   }
//
//  /* Adjust by building cost */
//  v -= pimpr.build_cost / (pcity.shield_surplus * 10 + 1);
//
//  /* Set */
//  pcity.ai.building_want[id] = v;
//}
//
///************************************************************************** 
//  Prime pcity.ai.building_want[]
//**************************************************************************/
//void ai_manage_buildings(player pplayer)
///* TODO:  RECALC_SPEED should be configurable to ai difficulty. -kauf  */
//public static final int RECALC_SPEED = 5;
//{
//  ai_data ai = ai_data_get(pplayer);
//
//  /* First find current worth of cities and cache this. */
//  for (city acity : pplayer.cities.data) {
//    acity.ai.worth = city_want(pplayer, acity, ai);
//  } }
//
//  for (int id = 0; id < Game.game.num_impr_types; id++) {
//    if (!can_player_build_improvement(pplayer, id)
//        || Improvement.improvement_obsolete(pplayer, id)) {
//      continue;
//    }
//    for (city pcity : pplayer.cities.data) {
//      if (pplayer.ai.control && pcity.ai.next_recalc > Game.game.turn) {
//        continue; /* do not recalc yet */
//      } else {
//        pcity.ai.building_want[id] = 0; /* do recalc */
//      }
//      if (City.city_got_building(pcity, id)
//          || pcity.shield_surplus == 0
//          || !City.can_build_improvement(pcity, id)
//          || improvement_redundant(pplayer, pcity, id, false)) {
//        continue; /* Don't build redundant buildings */
//      }
//      adjust_building_want_by_effects(pcity, id);
//      CITY_LOG(Log.LOG_DEBUG, pcity, "want to build %s with %d", 
//               Improvement.get_improvement_name(id), pcity.ai.building_want[id]);
//    } }
//  } ;
//
//  /* Reset recalc counter */
//  for (city pcity : pplayer.cities.data) {
//    if (pcity.ai.next_recalc <= Game.game.turn) {
//      /* This will spread recalcs out so that no one turn end is 
//       * much longer than others */
//      pcity.ai.next_recalc = Game.game.turn + Rand.myrand(RECALC_SPEED) + RECALC_SPEED;
//    }
//  } }
//}
//
///*************************************************************************** 
//  This function computes distances between cities for purpose of building 
//  crowds of water-consuming Caravans or smoggish Freights which want to add 
//  their brick to the wonder being built in pcity.
//
//  At the function entry point, our warmap is intact.  We need to do two 
//  things: (1) establish "downtown" for THIS city (which is an estimate of 
//  how much help we can expect when building a wonder) and 
//  (2) establish distance to pcity for ALL cities on our continent.
//
//  If there are more than one wondercity, things will get a bit random.
//****************************************************************************/
//static void establish_city_distances(player pplayer, 
//				     city pcity)
//{
//  int distance;
//  Continent_id wonder_continent;
//  int freight = best_role_unit(pcity, F_HELP_WONDER);
//  int moverate = (freight == unittype.U_LAST) ? Unit_H.SINGLE_MOVE
//                                     : get_unit_type(freight).move_rate;
//
//  if (!pcity.is_building_unit && Improvement.is_wonder(pcity.currently_building)) {
//    wonder_continent = map_get_continent(pcity.tile);
//  } else {
//    wonder_continent = 0;
//  }
//
//  pcity.ai.downtown = 0;
//  for (city othercity : pplayer.cities.data) {
//    distance = WARMAP_COST(othercity.tile);
//    if (wonder_continent != 0
//        && map_get_continent(othercity.tile) == wonder_continent) {
//      othercity.ai.distance_to_wonder_city = distance;
//    }
//
//    /* How many people near enough would help us? */
//    distance += moverate - 1; distance /= moverate;
//    pcity.ai.downtown += MAX(0, 5 - distance);
//  } }
//}
//
///**************************************************************************
//  Choose a build for the barbarian player.
//
//  TODO: Move this into advmilitary.c
//  TODO: It will be called for each city but doesn't depend on the city,
//  maybe cache it?  Although barbarians don't normally have many cities, 
//  so can be a bigger bother to cache it.
//**************************************************************************/
//static void ai_barbarian_choose_build(player pplayer, 
//				      ai_choice choice)
//{
//  int bestunit = -1;
//  int i, bestattack = 0;
//
//  /* Choose the best unit among the basic ones */
//  for(i = 0; i < num_role_units(unit_role_id.L_BARBARIAN_BUILD); i++) {
//    int iunit = Unittype_P.get_role_unit(unit_role_id.L_BARBARIAN_BUILD, i);
//
//    if (get_unit_type(iunit).attack_strength > bestattack) {
//      bestunit = iunit;
//      bestattack = get_unit_type(iunit).attack_strength;
//    }
//  }
//
//  /* Choose among those made available through other civ's research */
//  for(i = 0; i < num_role_units(unit_role_id.L_BARBARIAN_BUILD_TECH); i++) {
//    int iunit = Unittype_P.get_role_unit(unit_role_id.L_BARBARIAN_BUILD_TECH, i);
//
//    if (Game.game.global_advances[get_unit_type(iunit).tech_requirement] != 0
//	&& get_unit_type(iunit).attack_strength > bestattack) {
//      bestunit = iunit;
//      bestattack = get_unit_type(iunit).attack_strength;
//    }
//  }
//
//  /* If found anything, put it into the choice */
//  if (bestunit != -1) {
//    choice.choice = bestunit;
//    /* FIXME: 101 is the "overriding military emergency" indicator */
//    choice.want   = 101;
//    choice.type   = CT_ATTACKER;
//  } else {
//    util.freelog(Log.LOG_VERBOSE, "Barbarians don't know what to build!");
//  }
//}
//
///************************************************************************** 
//  Chooses what the city will build.  Is called after the military advisor
//  put it's choice into pcity.ai.choice and "settler advisor" put settler
//  want into pcity.founder_*.
//
//  Note that AI cheats -- it suffers no penalty for switching from unit to 
//  improvement, etc.
//**************************************************************************/
//static void ai_city_choose_build(player pplayer, city pcity)
//{
//  struct ai_choice newchoice;
//
//  init_choice(&newchoice);
//
//  if (ai_handicap(pplayer, H_AWAY)
//      && city_built_last_turn(pcity)
//      && pcity.ai.urgency == 0) {
//    /* Don't change existing productions unless we have to. */
//    return;
//  }
//
//  if( is_barbarian(pplayer) ) {
//    ai_barbarian_choose_build(pplayer, &(pcity.ai.choice));
//  } else {
//    /* FIXME: 101 is the "overriding military emergency" indicator */
//    if (pcity.ai.choice.want <= 100 || pcity.ai.urgency == 0) { 
//      domestic_advisor_choose_build(pplayer, pcity, &newchoice);
//      copy_if_better_choice(&newchoice, &(pcity.ai.choice));
//    }
//  }
//
//  /* Fallbacks */
//  if (pcity.ai.choice.want == 0) {
//    /* Fallbacks do happen with techlevel 0, which is now default. -- Per */
//    CITY_LOG(Log.LOG_VERBOSE, pcity, "Falling back - didn't want to build solutil.diers," +
//	     " settlers, or buildings");
//    pcity.ai.choice.want = 1;
//    if (best_role_unit(pcity, F_TRADE_ROUTE) != unittype.U_LAST) {
//      pcity.ai.choice.choice = best_role_unit(pcity, F_TRADE_ROUTE);
//      pcity.ai.choice.type = CT_NONMIL;
//    } else if (City.can_build_improvement(pcity, Game.game.default_building)) {
//      pcity.ai.choice.choice = Game.game.default_building;
//      pcity.ai.choice.type = CT_BUILDING;
//    } else if (best_role_unit(pcity, Eunit_flag_id.F_SETTLERS) != unittype.U_LAST) {
//      pcity.ai.choice.choice = best_role_unit(pcity, Eunit_flag_id.F_SETTLERS);
//      pcity.ai.choice.type = CT_NONMIL;
//    } else {
//      CITY_LOG(Log.LOG_VERBOSE, pcity, "Cannot even build a fallback " +
//	       "(caravan/coinage/settlers). Fix the ruleset!");
//      pcity.ai.choice.want = 0;
//    }
//  }
//
//  if (pcity.ai.choice.want != 0) { 
//    ASSERT_REAL_CHOICE_TYPE(pcity.ai.choice.type);
//
//    CITY_LOG(Log.LOG_DEBUG, pcity, "wants %s with desire %d.",
//	     (is_unit_choice_type(pcity.ai.choice.type) ?
//	      Unittype_P.unit_name(pcity.ai.choice.choice) :
//	      Improvement.get_improvement_name(pcity.ai.choice.choice)),
//	     pcity.ai.choice.want);
//    
//    if (!pcity.is_building_unit && Improvement.is_wonder(pcity.currently_building) 
//	&& (is_unit_choice_type(pcity.ai.choice.type) 
//	    || pcity.ai.choice.choice != pcity.currently_building))
//      notify_player_ex(null, pcity.tile, event_type.E_WONDER_STOPPED,
//		       "Game: The %s have stopped building The %s in %s.",
//		       Nation.get_nation_name_plural(pplayer.nation),
//		       City.get_impr_name_ex(pcity, pcity.currently_building),
//		       pcity.name);
//    
//    if (pcity.ai.choice.type == CT_BUILDING 
//	&& Improvement.is_wonder(pcity.ai.choice.choice)
//	&& (pcity.is_building_unit 
//	    || pcity.currently_building != pcity.ai.choice.choice)) {
//      notify_player_ex(null, pcity.tile, event_type.E_WONDER_STARTED,
//		       "Game: The %s have started building The %s in %s.",
//		       Nation.get_nation_name_plural(City.city_owner(pcity).nation),
//		       City.get_impr_name_ex(pcity, pcity.ai.choice.choice),
//		       pcity.name);
//      pcity.currently_building = pcity.ai.choice.choice;
//      pcity.is_building_unit = is_unit_choice_type(pcity.ai.choice.type);
//
//      /* Help other cities to send caravans to us */
//      generate_warmap(pcity, null);
//      establish_city_distances(pplayer, pcity);
//    } else {
//      pcity.currently_building = pcity.ai.choice.choice;
//      pcity.is_building_unit   = is_unit_choice_type(pcity.ai.choice.type);
//    }
//  }
//}
//
///************************************************************************** 
//...
//**************************************************************************/
//static void try_to_sell_stuff(player pplayer, city pcity)
//{
//  for (int id = 0; id < Game.game.num_impr_types; id++) {
//    if (Citytools.can_sell_building(pcity, id)
//	&& !building_has_effect(id, effect_type.EFT_LAND_DEFEND)) {
///* selling walls to buy defenders is counterproductive -- Syela */
//      really_handle_city_sell(pplayer, pcity, id);
//      break;
//    }
//  } ;
//}
//
///************************************************************************** 
//  Increase maxbuycost.  This variable indicates (via ai_gold_reserve) to 
//  the tax selection code how much money do we need for buying stuff.
//**************************************************************************/
//static void increase_maxbuycost(player pplayer, int new_value)
//{
//  pplayer.ai.maxbuycost = MAX(pplayer.ai.maxbuycost, new_value);
//}
//
///************************************************************************** 
//  Try to upgrade a city's units. limit is the last amount of gold we can
//  end up with after the upgrade. military is if we want to upgrade non-
//  military or military units.
//**************************************************************************/
//static void ai_upgrade_units(city pcity, int limit, boolean military)
//{
//  player pplayer = City.city_owner(pcity);
//  for (unit punit : pcity.tile.units.data) {
//    int id = can_upgrade_unittype(pplayer, punit.type);
//    if (military && (!is_military_unit(punit) || !is_ground_unit(punit))) {
//      /* Only upgrade military units this round */
//      continue;
//    } else if (!military && is_military_unit(punit)
//               && punit.unit_type().transport_capacity == 0) {
//      /* Only civilians or tranports this round */
//      continue;
//    }
//    if (id >= 0) {
//      int cost = unit_upgrade_price(pplayer, punit.type, id);
//      int real_limit = limit;
//      /* Triremes are DANGEROUS!! We'll do anything to upgrade 'em. */
//      if (unit_flag(punit, F_TRIREME)) {
//        real_limit = pplayer.ai.est_upkeep;
//      }
//      if (pplayer.economic.gold - cost > real_limit) {
//        CITY_LOG(LOG_BUY, pcity, "Upgraded %s to %s for %d (%s)",
//                 punit.unit_type().name, Unittype_P.unit_types[id].name, cost,
//                 military ? "military" : "civilian");
//        handle_unit_upgrade(City.city_owner(pcity), punit.id);
//      } else {
//        increase_maxbuycost(pplayer, cost);
//      }
//    }
//  } }
//}
//
///************************************************************************** 
//  Buy and upgrade stuff!
//**************************************************************************/
//static void ai_spend_gold(player pplayer)
//{
//  struct ai_choice bestchoice;
//  int cached_limit = ai_gold_reserve(pplayer);
//
//  /* Disband explorers that are at home but don't serve a purpose. 
//   * FIXME: This is a hack, and should be removed once we
//   * learn how to ferry explorers to new land. */
//  for (city pcity : pplayer.cities.data) {
//    tile ptile = pcity.tile;
//    for (unit punit : ptile.units.data) {
//      if (unit_has_role(punit.type, L_EXPLORER)
//          && pcity.id == punit.homecity
//          && pcity.ai.urgency == 0) {
//        CITY_LOG(LOG_BUY, pcity, "disbanding %s to increase production",
//                 Unittype_P.unit_name(punit.type));
//	handle_unit_disband(pplayer,punit.id);
//      }
//    }
//  } }
//  
//  do {
//    int limit = cached_limit; /* cached_limit is our gold reserve */
//    city pcity = null;
//    boolean expensive; /* don't buy when it costs x2 unless we must */
//    int buycost;
//
//    /* Find highest wanted item on the buy list */
//    init_choice(&bestchoice);
//    for (city acity : pplayer.cities.data) {
//      if (acity.ai.choice.want > bestchoice.want && ai_fuzzy(pplayer, true)) {
//        bestchoice.choice = acity.ai.choice.choice;
//        bestchoice.want = acity.ai.choice.want;
//        bestchoice.type = acity.ai.choice.type;
//        pcity = acity;
//      }
//    } }
//
//    /* We found nothing, so we're done */
//    if (bestchoice.want == 0) {
//      break;
//    }
//
//    /* Not dealing with this city a second time */
//    pcity.ai.choice.want = 0;
//
//    ASSERT_REAL_CHOICE_TYPE(bestchoice.type);
//
//    /* Try upgrade units at danger location (high want is usually danger) */
//    if (pcity.ai.urgency > 1) {
//      if (bestchoice.type == CT_BUILDING && Improvement.is_wonder(bestchoice.choice)) {
//        CITY_LOG(LOG_BUY, pcity, "Wonder being built in dangerous position!");
//      } else {
//        /* If we have urgent want, spend more */
//        int upgrade_limit = limit;
//        if (pcity.ai.urgency > 1) {
//          upgrade_limit = pplayer.ai.est_upkeep;
//        }
//        /* Upgrade only military units now */
//        ai_upgrade_units(pcity, upgrade_limit, true);
//      }
//    }
//
//    if (pcity.anarchy != 0 && bestchoice.type != CT_BUILDING) {
//      continue; /* Nothing we can do */
//    }
//
//    /* Cost to complete production */
//    buycost = City.city_buy_cost(pcity);
//
//    if (buycost <= 0) {
//      continue; /* Already completed */
//    }
//
//    if (bestchoice.type != CT_BUILDING
//        && Unittype_P.unit_type_flag(bestchoice.choice, Eunit_flag_id.F_CITIES)) {
//      if (Effects.get_city_bonus(pcity, EFT_GROWTH_FOOD) == 0
//          && pcity.size == 1
//          && city_granary_size(pcity.size)
//             > pcity.food_stock + pcity.food_surplus) {
//        /* Don't buy settlers in size 1 cities unless we grow next turn */
//        continue;
//      } else if (pplayer.cities.foo_list_size() > 6) {
//          /* Don't waste precious money buying settlers late Game.game
//           * since this raises taxes, and we want science. Adjust this
//           * again when our tax algorithm is smarter. */
//          continue;
//      }
//    } else {
//      /* We are not a settler. Therefore we increase the cash need we
//       * balance our buy desire with to keep cash at hand for emergencies 
//       * and for upgrades */
//      limit *= 2;
//    }
//
//    /* It costs x2 to buy something with no shields contributed */
//    expensive = (pcity.shield_stock == 0)
//                || (pplayer.economic.gold - buycost < limit);
//
//    if (bestchoice.type == CT_ATTACKER
//	&& buycost > Unittype_P.unit_build_shield_cost(bestchoice.choice) * 2) {
//       /* Too expensive for an offensive unit */
//       continue;
//    }
//
//    /* FIXME: Here Syela wanted some code to check if
//     * pcity was doomed, and we should therefore attempt
//     * to sell everything in it of non-military value */
//
//    if (pplayer.economic.gold - pplayer.ai.est_upkeep >= buycost
//        && (!expensive 
//            || (pcity.ai.grave_danger != 0 && assess_defense(pcity) == 0)
//            || (bestchoice.want > 200 && pcity.ai.urgency > 1))) {
//      /* Buy stuff */
//      CITY_LOG(LOG_BUY, pcity, "Crash buy of %s for %d (want %d)",
//               bestchoice.type != CT_BUILDING ? Unittype_P.unit_name(bestchoice.choice)
//               : Improvement.get_improvement_name(bestchoice.choice), buycost,
//               bestchoice.want);
//      really_handle_city_buy(pplayer, pcity);
//    } else if (pcity.ai.grave_danger != 0 
//               && bestchoice.type == CT_DEFENDER
//               && assess_defense(pcity) == 0) {
//      /* We have no gold but MUST have a defender */
//      CITY_LOG(LOG_BUY, pcity, "must have %s but can't afford it (%d < %d)!",
//	       Unittype_P.unit_name(bestchoice.choice), pplayer.economic.gold, buycost);
//      try_to_sell_stuff(pplayer, pcity);
//      if (pplayer.economic.gold - pplayer.ai.est_upkeep >= buycost) {
//        CITY_LOG(LOG_BUY, pcity, "now we can afford it (sold something)");
//        really_handle_city_buy(pplayer, pcity);
//      }
//      increase_maxbuycost(pplayer, buycost);
//    }
//  } while (true);
//
//  /* Civilian upgrades now */
//  for (city pcity : pplayer.cities.data) {
//    ai_upgrade_units(pcity, cached_limit, false);
//  } }
//
//  util.freelog(LOG_BUY, "%s wants to keep %d in reserve (tax factor %d)", 
//          pplayer.name, cached_limit, pplayer.ai.maxbuycost);
//}
//
///**************************************************************************
//  One of the top level AI functions.  It does (by calling other functions):
//  worker allocations,
//  build choices,
//  extra gold spending.
//**************************************************************************/
//void ai_manage_cities(player pplayer)
//{
//  pplayer.ai.maxbuycost = 0;
//
//  for (city pcity : pplayer.cities.data) {
//    if (CITY_EMERGENCY(pcity)) {
//      Cityturn.auto_arrange_workers(pcity); /* this usually helps */
//    }
//    if (CITY_EMERGENCY(pcity)) {
//      /* Fix critical shortages or unhappiness */
//      resolve_city_emergency(pplayer, pcity);
//    }
//    ai_sell_obsolete_buildings(pcity);
//    Citytools.sync_cities();
//  } }
//
//  ai_manage_buildings(pplayer);
//
//  /* Initialize the infrastructure cache, which is used shortly. */
//  initialize_infrastructure_cache(pplayer);
//  for (city pcity : pplayer.cities.data) {
//    /* Note that this function mungs the seamap, but we don't care */
//    military_advisor_choose_build(pplayer, pcity, &pcity.ai.choice);
//    /* because establish_city_distances doesn't need the seamap
//     * it determines downtown and distance_to_wondercity, 
//     * which ai_city_choose_build will need */
//    establish_city_distances(pplayer, pcity);
//    /* Will record its findings in pcity.settler_want */ 
//    contemplate_terrain_improvements(pcity);
//
//    if (pcity.ai.next_founder_want_recalc <= Game.game.turn) {
//      /* Will record its findings in pcity.founder_want */ 
//      contemplate_new_city(pcity);
//      /* Avoid recalculating all the time.. */
//      pcity.ai.next_founder_want_recalc = Game.game.turn + Rand.myrand(RECALC_SPEED) + RECALC_SPEED;
//    } 
//  } }
//
//  for (city pcity : pplayer.cities.data) {
//    ai_city_choose_build(pplayer, pcity);
//  } }
//
//  ai_spend_gold(pplayer);
//}
//
///**************************************************************************
//... 
//**************************************************************************/
//static boolean building_unwanted(player plr, int i)
//{
//  return (ai_wants_no_science(plr)
//          && building_has_effect(i, EFT_SCIENCE_BONUS));
//}
//
///**************************************************************************
//  Sell an obsolete building if there are any in the city.
//**************************************************************************/
//static void ai_sell_obsolete_buildings(city pcity)
//{
//  player pplayer = City.city_owner(pcity);
//
//	for (int it = 0; i < game.num_impr_types; i++) {
//	if((pcity).improvements[i] == Improvement.I_NONE) {
//		continue;
//	}
//    if(!Improvement.is_wonder(i) 
//       && !building_has_effect(i, effect_type.EFT_LAND_DEFEND)
//	      /* selling city walls is really, really dumb -- Syela */
//       && (is_building_replaced(pcity, i)
//	   || building_unwanted(City.city_owner(pcity), i))) {
//      Citytools.do_sell_building(pplayer, pcity, i);
//      notify_player_ex(pplayer, pcity.tile, event_type.E_IMP_SOLD,
//		       "Game: %s is selling %s (not needed) for %d.", 
//		       pcity.name, Improvement.get_improvement_name(i), 
//		       Improvement.impr_sell_gold(i));
//      return; /* max 1 building each turn */
//    }
//  } ;
//}
//
///**************************************************************************
//  This function tries desperately to save a city from going under by
//  revolt or starvation of food or resources. We do this by taking
//  over resources held by nearby cities and disbanding units.
//
//  TODO: Try to move units into friendly cities to reduce unhappiness
//  instead of disbanding. Also rather starve city than keep it in
//  revolt, as long as we don't lose settlers.
//
//  TODO: Make function that tries to save units by moving them into
//  cities that can upkeep them and change homecity rather than just
//  disband. This means we'll have to move this function to beginning
//  of AI turn sequence (before moving units).
//
//  "I don't care how slow this is; it will very rarely be used." -- Syela
//
//  Syela is wrong. It happens quite too often, mostly due to unhappiness.
//  Also, most of the time we are unable to resolve the situation. 
//**************************************************************************/
//static void resolve_city_emergency(player pplayer, city pcity)
//public static final int LOG_EMERGENCY = Log.LOG_DEBUG;
//{
//  Speclists<city> minilist;
//
//  util.freelog(LOG_EMERGENCY,
//          "Emergency in %s (%s, angry%d, unhap%d food%d, prod%d)",
//          pcity.name, City.city_unhappy(pcity) ? "unhappy" : "content",
//          pcity.ppl_angry[4], pcity.ppl_unhappy[4],
//          pcity.food_surplus, pcity.shield_surplus);
//
//  city_list_init(&minilist);
//  map_city_radius_iterate(pcity.tile, ptile) {
//    city acity = ptile.worked;
//    int city_map_x, city_map_y;
//    boolean is_valid;
//
//    if (acity && acity != pcity && acity.owner == pcity.owner)  {
//      if (Map.same_pos(acity.tile, ptile)) {
//        /* can't stop working city center */
//        continue;
//      }
//      util.freelog(Log.LOG_DEBUG, "%s taking over %s's square in (%d, %d)",
//              pcity.name, acity.name, ptile.x, ptile.y);
//      is_valid = City.map_to_city_map(&city_map_x, &city_map_y, acity, ptile);
//      assert(is_valid);
//      Citytools.server_remove_worker_city(acity, city_map_x, city_map_y);
//      acity.specialists[specialist_type.SP_ELVIS]++;
//      if (!city_list_find_id(&minilist, acity.id)) {
//	&minilist.foo_list_insert(acity);
//      }
//    }
//  } map_city_radius_iterate_end;
//  Cityturn.auto_arrange_workers(pcity);
//
//  if (!CITY_EMERGENCY(pcity)) {
//    util.freelog(LOG_EMERGENCY, "Emergency in %s resolved", pcity.name);
//    goto cleanup;
//  }
//
//  for (unit punit : pcity.units_supported.data) {
//    if (City.city_unhappy(pcity)
//        && punit.unhappiness != 0
//        && punit.ai.passenger == 0) {
//      UNIT_LOG(LOG_EMERGENCY, punit, "is causing unrest, disbanded");
//      handle_unit_disband(pplayer, punit.id);
//      Cityturn.city_refresh(pcity);
//    }
//  }
//
//  if (CITY_EMERGENCY(pcity)) {
//    util.freelog(LOG_EMERGENCY, "Emergency in %s remains unresolved", 
//            pcity.name);
//  } else {
//    util.freelog(LOG_EMERGENCY, 
//            "Emergency in %s resolved by disbanding unit(s)", pcity.name);
//  }
//
//  cleanup:
//  for (city acity : minilist.data) {
//    /* otherwise food total and stuff was wrong. -- Syela */
//    Cityturn.city_refresh(acity);
//    Cityturn.auto_arrange_workers(pcity);
//  } }
//
//  city_list_unlink_all(&minilist);
//
//  Citytools.sync_cities();
//}
//#undef LOG_EMERGENCY
}