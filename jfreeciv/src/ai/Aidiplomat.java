package ai;


public class Aidiplomat{

// Freeciv - Copyright (C) 2002 - The Freeciv Project
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
//#include "government.h"
//#include "log.h"
//#include "mem.h"
//#include "packets.h"
//#include "path_finding.h"
//#include "pf_tools.h"
//#include "player.h"
//#include "shared.h"
//#include "timing.h"
//#include "unit.h"
//
//#include "barbarian.h"
//#include "citytools.h"
//#include "cityturn.h"
//#include "diplomats.h"
//#include "maphand.h"
//#include "settlers.h"
//#include "unithand.h"
//#include "unittools.h"
//
//#include "advmilitary.h"
//#include "aicity.h"
//#include "aidata.h"
//#include "aihand.h"
//#include "ailog.h"
//#include "aitools.h"
//#include "aiunit.h"
//
//#include "aidiplomat.h"
//
//public static final int LOG_DIPLOMAT = Log.LOG_DEBUG;
//public static final int LOG_DIPLOMAT_BUILD = Log.LOG_DEBUG;
//
//static void find_city_to_diplomat(player pplayer, unit punit,
//                                  city *ctarget, int *move_dist,
//                                  pf_map map);
//
///******************************************************************************
//  Number of improvements that can be sabotaged in pcity.
//******************************************************************************/
//static int count_sabotagable_improvements(city pcity)
//{
//  int count = 0;
//
//	for (int i = 0; i < game.num_impr_types; i++) {
//	if((pcity).improvements[i] == Improvement.I_NONE) {
//		continue;
//	}
//    if (get_improvement_type(i).sabotage > 0) {
//      count++;
//    }
//  } ;
//
//  return count;
//}
//
///******************************************************************************
//  Number of techs that we don't have and the enemy (tplayer) does.
//******************************************************************************/
//static int count_stealable_techs(player pplayer, player tplayer)
//{
//  int count = 0;
//
//  tech_type_iterate(index) {
//    if ((get_invention(pplayer, index) != TECH_KNOWN)
//        && (get_invention(tplayer, index) == TECH_KNOWN)) {
//      count++;
//    }
//  } tech_type_iterate_end;
//
//  return count;
//}
//
///**********************************************************************
//  Calculates our need for diplomats as defensive units. May replace
//  values in choice. The values 16000 and 3000 used below are totally
//  arbitrary but seem to work.
//***********************************************************************/
//void ai_choose_diplomat_defensive(player pplayer,
//                                  city pcity,
//                                  ai_choice choice, int def)
//{
//  /* Build a diplomat if our city is threatened by enemy diplomats, and
//     we have other defensive troops, and we don't already have a diplomat
//     to protect us. If we see an enemy diplomat and we don't have diplomat
//     tech... race it! */
//  if (def != 0 && pcity.ai.diplomat_threat && !pcity.ai.has_diplomat) {
//    int u = best_role_unit(pcity, Eunit_flag_id.F_DIPLOMAT);
//
//    if (u < unittype.U_LAST) {
//       util.freelog(LOG_DIPLOMAT_BUILD, 
//               "A defensive diplomat will be built in city %s.", pcity.name);
//       choice.want = 16000; /* diplomat more important than soldiers */
//       pcity.ai.urgency = 1;
//       choice.type = CT_DEFENDER;
//       choice.choice = u;
//    } else if (num_role_units(Eunit_flag_id.F_DIPLOMAT) > 0) {
//      /* We don't know diplomats yet... */
//      util.freelog(LOG_DIPLOMAT_BUILD,
//              "A defensive diplomat is wanted badly in city %s.", pcity.name);
//      u = Unittype_P.get_role_unit(Eunit_flag_id.F_DIPLOMAT, 0);
//      /* 3000 is a just a large number, but not hillariously large as the
//         previously used one. This is important for diplomacy later - Per */
//      if (u != unittype.U_LAST) {
//        pplayer.ai.tech_want[get_unit_type(u).tech_requirement] += 3000;
//      }
//    }
//  }
//}
//
///**********************************************************************
//  Calculates our need for diplomats as offensive units. May replace
//  values in choice.
//***********************************************************************/
//void ai_choose_diplomat_offensive(player pplayer,
//                                  city pcity,
//                                  ai_choice choice)
//{
//  int u = best_role_unit(pcity, Eunit_flag_id.F_DIPLOMAT);
//  ai_data ai = ai_data_get(pplayer);
//
//  if (u >= unittype.U_LAST) {
//    /* We don't know diplomats yet! */
//    return;
//  }
//
//  if (ai_handicap(pplayer, H_DIPLOMAT)) {
//    /* Diplomats are too tough on newbies */
//    return;
//  }
//
//  /* Do we have a good reason for building diplomats? */
//  {
//    pf_map map;
//    struct pf_parameter parameter;
//    unit_type ut = get_unit_type(u);
//    city acity;
//    int want, loss, p_success, p_failure, time_to_dest;
//    int gain_incite = 0, gain_theft = 0, gain = 1;
//    int incite_cost;
//    unit punit = create_unit_virtual(pplayer, pcity, u,
//                                             do_make_unit_veteran(pcity, u));
//
//    pft_fill_unit_parameter(&parameter, punit);
//    map = pf_create_map(&parameter);
//
//    find_city_to_diplomat(pplayer, punit, &acity, &time_to_dest, map);
//
//    pf_destroy_map(map);
//    destroy_unit_virtual(punit);
//
//    if (acity == null || BV_ISSET(ai.stats.diplomat_reservations, acity.id)) {
//      /* Found no target or city already considered */
//      return;
//    }
//    incite_cost = Cityturn.city_incite_cost(pplayer, acity);
//    if (HOSTILE_PLAYER(pplayer, ai, City.city_owner(acity))
//        && (incite_cost < INCITE_IMPOSSIBLE_COST)
//        && (incite_cost < pplayer.economic.gold - pplayer.ai.est_upkeep)) {
//      /* incite gain (FIXME: we should count wonders too but need to
//         cache that somehow to avoid CPU hog -- Per) */
//      gain_incite = acity.food_prod * FOOD_WEIGHTING
//                    + acity.shield_prod * SHIELD_WEIGHTING
//                    + (acity.luxury_total
//                       + acity.tax_total
//                       + acity.science_total) * TRADE_WEIGHTING;
//      gain_incite *= SHIELD_WEIGHTING; /* WAG cost to take city otherwise */
//      gain_incite -= incite_cost * TRADE_WEIGHTING;
//    }
//    if (City.city_owner(acity).research.techs_researched <
//             pplayer.research.techs_researched
//             && !Player_P.pplayers_allied(pplayer, City.city_owner(acity))) {
//      /* tech theft gain */
//      gain_theft = total_bulbs_required(pplayer) * TRADE_WEIGHTING;
//    }
//    gain = MAX(gain_incite, gain_theft);
//    loss = Unittype_P.unit_build_shield_cost(u) * SHIELD_WEIGHTING;
//
//    /* Probability to succeed, assuming no defending diplomat */
//    p_success = Game.game.diplchance;
//    /* Probability to lose our unit */
//    p_failure = (Unittype_P.unit_type_flag(u, F_SPY) ? 100 - p_success : 100);
//
//    /* Get the time to dest in turns (minimum 1 turn) */
//    time_to_dest = (time_to_dest + ut.move_rate - 1) / ut.move_rate;
//    /* Discourage long treks */
//    time_to_dest *= ((time_to_dest + 1) / 2);
//
//    /* Almost kill_desire */
//    want = (p_success * gain - p_failure * loss) / 100
//           - SHIELD_WEIGHTING * time_to_dest;
//    if (want <= 0) {
//      return;
//    }
//
//    want = military_amortize(pplayer, pcity, want, time_to_dest, 
//                             Unittype_P.unit_build_shield_cost(u));
//
//    if (!player_has_embassy(pplayer, City.city_owner(acity))
//        && want < 99) {
//        util.freelog(LOG_DIPLOMAT_BUILD,
//                "A diplomat desired in %s to establish an embassy with %s " +
//                "in %s", pcity.name, City.city_owner(acity).name, acity.name);
//        want = 99;
//    }
//    if (want > choice.want) {
//      util.freelog(LOG_DIPLOMAT_BUILD,
//              "%s, %s: %s is desired with want %d to spy in %s (incite " +
//              "want %d cost %d gold %d, tech theft want %d, ttd %d)",
//              pplayer.name, pcity.name, ut.name, want, acity.name, 
//              gain_incite, incite_cost, 
//              pplayer.economic.gold - pplayer.ai.est_upkeep, 
//              gain_theft, time_to_dest);
//      choice.want = want;
//      choice.type = CT_NONMIL; /* so we don't build barracks for it */
//      choice.choice = u;
//      BV_SET(ai.stats.diplomat_reservations, acity.id);
//    }
//  }
//}
//
///**************************************************************************
//  Check if something is on our receiving end for some nasty diplomat
//  business! Note that punit may util.die or be moved during this function. We
//  must be adjacent to target city.
//
//  We try to make embassy first, and abort if we already have one and target
//  is allied. Then we steal, incite, sabotage or poison the city, in that
//  order of priority.
//**************************************************************************/
//static void ai_diplomat_city(unit punit, city ctarget)
//{
//  player pplayer = punit.unit_owner();
//  player tplayer = City.city_owner(ctarget);
//  int count_impr = count_sabotagable_improvements(ctarget);
//  int count_tech = count_stealable_techs(pplayer, tplayer);
//  int gold_avail = pplayer.economic.gold - 2 * pplayer.ai.est_upkeep;
//  int incite_cost;
//
//  assert(pplayer.ai.control);
//
//  if (punit.moves_left == 0) {
//    UNIT_LOG(Log.LOG_ERROR, punit, "no moves left in ai_diplomat_city()!");
//  }
//
//  Unithand.handle_unit_activity_request(punit, unit_activity.ACTIVITY_IDLE);
//
//#define T(my_act,my_val)                                            \
//  if (diplomat_can_do_action(punit, my_act, ctarget.tile)) {	    \
//    util.freelog(LOG_DIPLOMAT, "Player %s's diplomat %d does " #my_act   \
//            " on %s", pplayer.name, punit.id, ctarget.name);     \
//    handle_unit_diplomat_action(pplayer, punit.id, my_act,         \
//                                ctarget.id, my_val);               \
//    return;                                                         \
//  }
//
//  if (!punit.foul) T(DIPLOMAT_EMBASSY,0);
//  if (Player_P.pplayers_allied(pplayer, tplayer)) {
//    return; /* Don't do the rest to allies */
//  }
//
//  if (count_tech > 0 
//      && (ctarget.steal == 0 || unit_flag(punit, F_SPY))) {
//    T(DIPLOMAT_STEAL,0);
//  } else {
//    UNIT_LOG(LOG_DIPLOMAT, punit, "We have already stolen from %s!",
//             ctarget.name);
//  }
//
//  incite_cost = Cityturn.city_incite_cost(pplayer, ctarget);
//  if (incite_cost <= gold_avail) {
//    T(DIPLOMAT_INCITE,0);
//  } else {
//    UNIT_LOG(LOG_DIPLOMAT, punit, "%s too expensive!", ctarget.name);
//  }
//
//  if (!Player_P.pplayers_at_war(pplayer, tplayer)) {
//    return; /* The rest are casus belli */
//  }
//  if (count_impr > 0) T(DIPLOMAT_SABOTAGE, Improvement.B_LAST+1);
//  T(SPY_POISON, 0); /* absolutely last resort */
//#undef T
//
//  /* This can happen for a number of odd and esoteric reasons  */
//  UNIT_LOG(LOG_DIPLOMAT, punit, "decides to stand idle outside " +
//           "enemy city %s!", ctarget.name);
//  ai_unit_new_role(punit, AIUNIT_NONE, null);
//}
//
///**************************************************************************
//  Returns (in ctarget) the closest city to send diplomats against, or null 
//  if none available on this continent.  punit can be virtual.
//**************************************************************************/
//static void find_city_to_diplomat(player pplayer, unit punit,
//                                  city *ctarget, int *move_dist,
//                                  pf_map map)
//{
//  boolean has_embassy;
//  int incite_cost = 0; /* incite cost */
//  boolean dipldef; /* whether target is protected by diplomats */
//
//  assert(punit != null);
//  *ctarget = null;
//  *move_dist = -1;
//
//  pf_iterator(map, pos) {
//    city acity;
//    player aplayer;
//    boolean can_incite;
//
//    acity = Map.map_get_city(pos.tile);
//
//    if (!acity) {
//      continue;
//    }
//    aplayer = City.city_owner(acity);
//
//    /* sneaky way of avoiding foul diplomat capture  -AJS */
//    has_embassy = player_has_embassy(pplayer, aplayer) || punit.foul;
//
//    if (aplayer == pplayer || is_barbarian(aplayer)
//        || (Player_P.pplayers_allied(pplayer, aplayer) && has_embassy)) {
//      continue; 
//    }
//
//    incite_cost = Cityturn.city_incite_cost(pplayer, acity);
//    can_incite = (incite_cost < INCITE_IMPOSSIBLE_COST);
//
//    dipldef = (count_diplomats_on_tile(acity.tile) > 0);
//    /* Three actions to consider:
//     * 1. establishing embassy OR
//     * 2. stealing techs OR
//     * 3. inciting revolt */
//    if (!has_embassy
//        || (acity.steal == 0 && pplayer.research.techs_researched < 
//            aplayer.research.techs_researched && !dipldef)
//        || (incite_cost < (pplayer.economic.gold - pplayer.ai.est_upkeep)
//            && can_incite && !dipldef)) {
//      /* We have the closest enemy city on the continent */
//      *ctarget = acity;
//      *move_dist = pos.total_MC;
//      break;
//    }
//  } pf_iterator_end;
//}
//
///**************************************************************************
//  Go to nearest/most threatened city (can be the current city too).
//**************************************************************************/
//static city ai_diplomat_defend(player pplayer,
//                                       unit punit,
//                                       int utype, pf_map map)
//{
//  int best_dist = 30; /* any city closer than this is better than none */
//  int best_urgency = 0;
//  city ctarget = null;
//  city pcity = Map.map_get_city(punit.tile);
//
//  if (pcity 
//      && count_diplomats_on_tile(pcity.tile) == 1
//      && pcity.ai.urgency > 0) {
//    /* Danger and we are only diplomat present - stay. */
//    return pcity;
//  }
//
//  pf_iterator(map, pos) {
//    city acity;
//    player aplayer;
//    int dipls, urgency;
//
//    acity = Map.map_get_city(pos.tile);
//    if (!acity) {
//      continue;
//    }
//    aplayer = City.city_owner(acity);
//    if (aplayer != pplayer) {
//      continue;
//    }
//
//    urgency = acity.ai.urgency;
//    dipls = (count_diplomats_on_tile(pos.tile)
//             - (Map.same_pos(pos.tile, punit.tile) ? 1 : 0));
//    if (dipls == 0 && acity.ai.diplomat_threat) {
//      /* We are _really_ needed there */
//      urgency = (urgency + 1) * 5;
//    } else if (dipls > 0) {
//      /* We are probably not needed there... */
//      urgency /= 3;
//    }
//
//    /* This formula may not be optimal, but it works. */
//    if (pos.total_MC > best_dist) {
//      /* punish city for being so far away */
//      urgency /= (float)(pos.total_MC / best_dist);
//    }
//
//    if (urgency > best_urgency) {
//      /* Found something worthy of our presence */
//      ctarget = acity;
//      best_urgency = urgency;
//      /* squelch divide-by-zero */
//      best_dist = MAX(pos.total_MC, 1);
//    }
//  } pf_iterator_end;
//
//  return ctarget;
//}
//
///**************************************************************************
//  Find units that we can reach, and bribe them. Returns true if survived
//  the ordeal, false if not or we expended all our movement.
//  Will try to bribe a ship on the coast as well as land stuff.
//**************************************************************************/
//static boolean ai_diplomat_bribe_nearby(player pplayer, 
//                                     unit punit, pf_map map)
//{
//  int gold_avail = pplayer.economic.gold - pplayer.ai.est_upkeep;
//  ai_data ai = ai_data_get(pplayer);
//
//  pf_iterator(map, pos) {
//    tile ptile = pos.tile;
//    boolean threat = false;
//    int newval, bestval = 0, cost;
//    unit pvictim = unit_list_get(&ptile.units, 0);
//    int sanity = punit.id;
//
//    if (pos.total_MC > punit.moves_left) {
//      /* Didn't find anything within range. */
//      break;
//    }
//
//    if (!pvictim
//        || !HOSTILE_PLAYER(pplayer, ai, pvictim.unit_owner())
//        || ptile.units.foo_list_size() > 1
//        || Map.map_get_city(pos.tile)
//        || Government.government_has_flag(get_gov_pplayer(pvictim.unit_owner()),
//                               G_UNBRIBABLE)) {
//      continue;
//    }
//
//    /* Calculate if enemy is a threat */
//    /* First find best defender on our tile */
//    for (unit aunit : ptile.units.data) {
//      newval = DEFENCE_POWER(aunit);
//      if (bestval < newval) {
//        bestval = newval;
//      }
//    } }
//    /* Compare with victim's attack power */
//    newval = ATTACK_POWER(pvictim);
//    if (newval > bestval
//        && pvictim.move_rate() > pos.total_MC) {
//      /* Enemy can probably kill us */
//      threat = true;
//    } else {
//      /* Enemy cannot reach us or probably not kill us */
//      threat = false;
//    }
//    /* Don't bribe settlers! */
//    if (unit_flag(pvictim, Eunit_flag_id.F_SETTLERS)
//        || unit_flag(pvictim, Eunit_flag_id.F_CITIES)) {
//      continue;
//    }
//    /* Should we make the expense? */
//    cost = pvictim.bribe_cost = unit_bribe_cost(pvictim);
//    if (!threat) {
//      /* Don't empty our treasure without good reason! */
//      gold_avail = pplayer.economic.gold - ai_gold_reserve(pplayer);
//    }
//    if (cost > gold_avail) {
//      /* Can't afford */
//      continue;
//    }
//
//    /* Found someone! */
//    {
//      tile ptile;
//      pf_path path;
//
//      ptile = mapstep(pos.tile, DIR_REVERSE(pos.dir_to_here));
//      path = pf_get_path(map, ptile);
//      if (!path || !ai_unit_execute_path(punit, path) 
//          || punit.moves_left <= 0) {
//        pf_destroy_path(path);
//        return false;
//      }
//      pf_destroy_path(path);
//    }
//
//    if (diplomat_can_do_action(punit, DIPLOMAT_BRIBE, pos.tile)) {
//      handle_unit_diplomat_action(pplayer, punit.id, DIPLOMAT_BRIBE,
//				  unit_list_get(&ptile.units, 0).id, -1);
//      /* autoattack might kill us as we move in */
//      if (Game.find_unit_by_id(sanity) && punit.moves_left > 0) {
//        return true;
//      } else {
//        return false;
//      }
//    } else {
//      /* usually because we ended move early due to another unit */
//      UNIT_LOG(LOG_DIPLOMAT, punit, "could not bribe target (%d, %d), " +
//               " %d moves left", TILE_XY(pos.tile), punit.moves_left);
//      return false;
//    }
//  } pf_iterator_end;
//
//  return (punit.moves_left > 0);
//}
//
///**************************************************************************
//  If we are the only diplomat in a threatened city, defend against enemy 
//  actions. The passive defense is set by Game.game.diplchance.  The active 
//  defense is to bribe units which end their move nearby. Our next trick is 
//  to look for enemy cities on our continent and do our diplomat things.
//
//  FIXME: It is important to establish contact with all civilizations, so
//  we should send diplomats by boat eventually. I just don't know how that
//  part of the code works, yet - Per
//**************************************************************************/
//void ai_manage_diplomat(player pplayer, unit punit)
//{
//  city pcity, *ctarget = null;
//  struct pf_parameter parameter;
//  pf_map map;
//  struct pf_position pos;
//
//  CHECK_UNIT(punit);
//
//  /* Generate map */
//  pft_fill_unit_parameter(&parameter, punit);
//  parameter.get_zoc = null; /* kludge */
//  map = pf_create_map(&parameter);
//
//  pcity = Map.map_get_city(punit.tile);
//
//  /* Look for someone to bribe */
//  if (!ai_diplomat_bribe_nearby(pplayer, punit, map)) {
//    /* Died or ran out of moves */
//    pf_destroy_map(map);
//    return;
//  }
//
//  /* If we are the only diplomat in a threatened city, then stay to defend */
//  pcity = Map.map_get_city(punit.tile); /* we may have moved */
//  if (pcity && count_diplomats_on_tile(punit.tile) == 1
//      && (pcity.ai.diplomat_threat || pcity.ai.urgency > 0)) {
//    UNIT_LOG(LOG_DIPLOMAT, punit, "stays to protect %s (urg %d)", 
//             pcity.name, pcity.ai.urgency);
//    ai_unit_new_role(punit, AIUNIT_NONE, null); /* abort mission */
//    pf_destroy_map(map);
//    return;
//  }
//
//  /* Check if existing target still makes sense */
//  if (punit.ai.ai_role == AIUNIT_ATTACK
//      || punit.ai.ai_role == AIUNIT_DEFEND_HOME) {
//    boolean failure = false;
//
//    ctarget = Map.map_get_city(punit.goto_tile);
//    if (pf_get_position(map, punit.goto_tile, &pos)
//        && ctarget) {
//      if (Map.same_pos(ctarget.tile, punit.tile)) {
//        failure = true;
//      } else if (Player_P.pplayers_allied(pplayer, City.city_owner(ctarget))
//          && punit.ai.ai_role == AIUNIT_ATTACK
//          && player_has_embassy(pplayer, City.city_owner(ctarget))) {
//        /* We probably incited this city with another diplomat */
//        failure = true;
//      } else if (!Player_P.pplayers_allied(pplayer, City.city_owner(ctarget))
//                 && punit.ai.ai_role == AIUNIT_DEFEND_HOME) {
//        /* We probably lost the city */
//        failure = true;
//      }
//    } else {
//      /* City vanished! */
//      failure = true;
//    }
//    if (failure) {
//      UNIT_LOG(LOG_DIPLOMAT, punit, "mission aborted");
//      ai_unit_new_role(punit, AIUNIT_NONE, null);
//    }
//  }
//
//  /* We may need a new map now. Both because we cannot get paths from an 
//   * old map, and we need paths to move, and because fctd below requires
//   * a new map for its iterator. */
//  if (!Map.same_pos(parameter.start_tile, punit.tile)
//      || punit.ai.ai_role == AIUNIT_NONE) {
//    pf_destroy_map(map);
//    pft_fill_unit_parameter(&parameter, punit);
//    parameter.get_zoc = null; /* kludge */
//    map = pf_create_map(&parameter);
//  }
//
//  /* If we are not busy, acquire a target. */
//  if (punit.ai.ai_role == AIUNIT_NONE) {
//    enum ai_unit_task task;
//    int move_dist; /* dummy */
//
//    find_city_to_diplomat(pplayer, punit, &ctarget, &move_dist, map);
//
//    if (ctarget) {
//      task = AIUNIT_ATTACK;
//      punit.ai.bodyguard = -1; /* want one */
//      UNIT_LOG(LOG_DIPLOMAT, punit, "going on attack");
//    } else if ((ctarget = ai_diplomat_defend(pplayer, punit,
//                                             punit.type, map)) != null) {
//      task = AIUNIT_DEFEND_HOME;
//      UNIT_LOG(LOG_DIPLOMAT, punit, "going to defend %s", ctarget.name);
//    } else if ((ctarget = find_closest_owned_city(pplayer, punit.tile, 
//						  true, null)) != null) {
//      /* This should only happen if the entire continent was suddenly
//       * conquered. So we head for closest coastal city and wait for someone
//       * to code ferrying for diplomats, or hostile attacks from the sea. */
//      task = AIUNIT_DEFEND_HOME;
//      UNIT_LOG(LOG_DIPLOMAT, punit, "going idle");
//    } else {
//      UNIT_LOG(LOG_DIPLOMAT, punit, "could not find a job");
//      pf_destroy_map(map);
//      return;
//    }
//
//    ai_unit_new_role(punit, task, ctarget.tile);
//    assert(punit.moves_left > 0 && ctarget 
//           && punit.ai.ai_role != AIUNIT_NONE);
//  }
//
//  CHECK_UNIT(punit);
//  if (ctarget == null) {
//    UNIT_LOG(Log.LOG_ERROR, punit, "ctarget not set (role==%d)",
//	     punit.ai.ai_role);
//    pf_destroy_map(map);
//    return;
//  }
//
//  /* GOTO unless we want to stay */
//  if (!Map.same_pos(punit.tile, ctarget.tile)) {
//    pf_path path;
//
//    path = pf_get_path(map, punit.goto_tile);
//    if (path && ai_unit_execute_path(punit, path) && punit.moves_left > 0) {
//      /* Check if we can do something with our destination now. */
//      if (punit.ai.ai_role == AIUNIT_ATTACK) {
//        int dist  = Map.real_map_distance(punit.tile, punit.goto_tile);
//        UNIT_LOG(LOG_DIPLOMAT, punit, "attack, dist %d to %s",
//                 dist, ctarget ? ctarget.name : "(none)");
//        if (dist == 1) {
//          /* Do our stuff */
//          ai_unit_new_role(punit, AIUNIT_NONE, null);
//          ai_diplomat_city(punit, ctarget);
//        }
//      }
//    }
//    pf_destroy_path(path);
//  }
//  pf_destroy_map(map);
//}
}