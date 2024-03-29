package ai;

public class Aidata{

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
//#include "aisupport.h"
//#include "city.h"
//#include "effects.h"
//#include "Game.game.h"
//#include "government.h"
//#include "log.h"
//#include "Map.map.h"
//#include "mem.h"
//#include "unit.h"
//
//#include "citytools.h"
//#include "diplhand.h"
//#include "maphand.h"
//#include "settlers.h"
//#include "unittools.h"
//
//#include "advdiplomacy.h"
//#include "advmilitary.h"
//#include "aicity.h"
//#include "aiferry.h"
//#include "aihand.h"
//#include "ailog.h"
//#include "aitools.h"
//#include "aiunit.h"
//
//#include "aidata.h"
//
//static struct ai_data aidata[Shared_H.MAX_NUM_PLAYERS + Shared_H.MAX_NUM_BARBARIANS];
//
///**************************************************************************
//  Precalculates some important data about the improvements in the Game.game
//  that we use later in ai/aicity.c.  We mark improvements as 'calculate'
//  if we want to run a full test on them, as 'estimate' if we just want
//  to do some guesses on them, or as 'unused' is they are useless to us.
//  Then we find the largest range of calculatable effects in the
//  improvement and record it for later use.
//**************************************************************************/
//static void ai_data_city_impr_calc(player pplayer, ai_data ai)
//{
//  int count[AI_IMPR_LAST];
//
//  memset(count, 0, sizeof(count));
//
//  for (int id = 0; id < Game.game.num_impr_types; id++) {
//    ai.impr_calc[id] = AI_IMPR_ESTIMATE;
//
//    /* Find largest extension */
//    effect_type_vector_iterate(get_building_effect_types(id), ptype) {
//      switch (*ptype) {
//#if 0
//      /* TODO */
//      case EFT_FORCE_CONTENT:
//      case EFT_FORCE_CONTENT_PCT:
//      case EFT_MAKE_CONTENT:
//      case EFT_MAKE_CONTENT_MIL:
//      case EFT_MAKE_CONTENT_MIL_PER:
//      case EFT_MAKE_CONTENT_PCT:
//      case EFT_MAKE_HAPPY:
//#endif
//      case EFT_LUXURY_BONUS:
//      case EFT_SCIENCE_BONUS:
//      case EFT_TAX_BONUS:
//      case EFT_CAPITAL_CITY:
//      case EFT_CORRUPT_PCT:
//      case EFT_FOOD_ADD_TILE:
//      case EFT_FOOD_INC_TILE:
//      case EFT_FOOD_PER_TILE:
//      case EFT_POLLU_POP_PCT:
//      case EFT_POLLU_PROD_PCT:
//      case EFT_PROD_ADD_TILE:
//      case EFT_PROD_BONUS:
//      case EFT_PROD_INC_TILE:
//      case EFT_PROD_PER_TILE:
//      case EFT_TRADE_ADD_TILE:
//      case EFT_TRADE_INC_TILE:
//      case EFT_TRADE_PER_TILE:
//      case EFT_UPKEEP_FREE:
//      effect_list_iterate(*get_building_effects(id, *ptype), peff) {
//        ai.impr_calc[id] = AI_IMPR_CALCULATE;
//        if (peff.range > ai.impr_range[id]) {
//          ai.impr_range[id] = peff.range;
//        }
//      } }
//      break;
//      default:
//      /* Nothing! */
//      break;
//      }
//    } effect_type_vector_iterate_end;
//    
//  } ;
//}
//
///**************************************************************************
//  Analyze rulesets. Must be run after rulesets after loaded, unlike
//  _init, which must be run before savegames are loaded, which is usually
//  before rulesets.
//**************************************************************************/
//void ai_data_analyze_rulesets(player pplayer)
//{
//  ai_data ai = &aidata[pplayer.player_no];
//
//  ai_data_city_impr_calc(pplayer, ai);
//}
//
///**************************************************************************
//  This function is called each turn to initialize pplayer.ai.stats.units.
//**************************************************************************/
//static void count_my_units(player pplayer)
//{
//  ai_data ai = ai_data_get(pplayer);
//
//  memset(&ai.stats.units, 0, sizeof(ai.stats.units));
//
//  for (unit punit : pplayer.units.data) {
//    switch (punit.unit_type().move_type) {
//    case unit_move_type.LAND_MOVING:
//      ai.stats.units.land++;
//      break;
//    case unit_move_type.SEA_MOVING:
//      ai.stats.units.sea++;
//      break;
//    case unit_move_type.HELI_MOVING:
//    case unit_move_type.AIR_MOVING:
//      ai.stats.units.air++;
//      break;
//    }
//
//    if (unit_flag(punit, F_TRIREME)) {
//      ai.stats.units.triremes++;
//    }
//    if (unit_flag(punit, F_MISSILE)) {
//      ai.stats.units.missiles++;
//    }
//    if (can_upgrade_unittype(pplayer, punit.type) >= 0) {
//      ai.stats.units.upgradeable++;
//    }
//  } }
//}
//
///**************************************************************************
//  Make and cache lots of calculations needed for other functions.
//
//  Note: We use Map.map.num_continents here rather than pplayer.num_continents
//  because we are omniscient and don't care about such trivialities as who
//  can see what.
//
//  FIXME: We should try to find the lowest common defence strength of our
//  defending units, and ignore enemy units that are incapable of harming 
//  us, instead of just checking attack strength > 1.
//**************************************************************************/
//void ai_data_turn_init(player pplayer) 
//{
//  ai_data ai = &aidata[pplayer.player_no];
//  int i, nuke_units = num_role_units(F_NUCLEAR);
//  boolean danger_of_nukes = false;
//  int ally_strength = -1;
//  player ally_strongest = null;
//
//  /*** Threats ***/
//
//  ai.num_continents    = Map.map.num_continents;
//  ai.num_oceans        = Map.map.num_oceans;
//  ai.threats.continent = fc_calloc(ai.num_continents + 1, sizeof(boolean));
//  ai.threats.invasions = false;
//  ai.threats.air       = false;
//  ai.threats.nuclear   = 0; /* none */
//  ai.threats.ocean     = fc_calloc(ai.num_oceans + 1, sizeof(boolean));
//  ai.threats.igwall    = false;
//
//  for(player aplayer: Game.game.players){
//    if (!is_player_dangerous(pplayer, aplayer)) {
//      continue;
//    }
//
//    /* The idea is that if there aren't any hostile cities on
//     * our continent, the danger of land attacks is not big
//     * enough to warrant city walls. Concentrate instead on 
//     * coastal fortresses and hunting down enemy transports. */
//    for (city acity : aplayer.cities.data) {
//      Continent_id continent = map_get_continent(acity.tile);
//      ai.threats.continent[continent] = true;
//    } }
//
//    for (unit punit : aplayer.units.data) {
//      if (unit_flag(punit, F_IGWALL)) {
//        ai.threats.igwall = true;
//      }
//
//      if (Unit.is_sailing_unit(punit)) {
//        /* If the enemy has not started sailing yet, or we have total
//         * control over the seas, don't worry, keep attacking. */
//        if (is_ground_units_transport(punit)) {
//          ai.threats.invasions = true;
//        }
//
//        /* The idea is that while our enemies don't have any offensive
//         * seaborne units, we don't have to worry. Go on the offensive! */
//        if (punit.unit_type().attack_strength > 1) {
//	  if (Terrain_H.is_ocean(punit.tile.terrain)) {
//	    Continent_id continent = map_get_continent(punit.tile);
//	    ai.threats.ocean[-continent] = true;
//	  } else {
//	    for(tile tile2: util.adjc_tile_iterate(punit.tile)) {
//	      if (Terrain_H.is_ocean(tile2.terrain)) {
//	        Continent_id continent = map_get_continent(tile2);
//	        ai.threats.ocean[-continent] = true;
//	      }
//	    }
//	  }
//        } 
//        continue;
//      }
//
//      /* The next idea is that if our enemies don't have any offensive
//       * airborne units, we don't have to worry. Go on the offensive! */
//      if ((is_air_unit(punit) || is_heli_unit(punit))
//           && punit.unit_type().attack_strength > 1) {
//        ai.threats.air = true;
//      }
//
//      /* If our enemy builds missiles, worry about missile defence. */
//      if (unit_flag(punit, F_MISSILE)
//          && punit.unit_type().attack_strength > 1) {
//        ai.threats.missile = true;
//      }
//
//      /* If he builds nukes, worry a lot. */
//      if (unit_flag(punit, F_NUCLEAR)) {
//        danger_of_nukes = true;
//      }
//    } }
//
//    /* Check for nuke capability */
//    for (i = 0; i < nuke_units; i++) {
//      int nuke = Unittype_P.get_role_unit(F_NUCLEAR, i);
//      if (can_player_build_unit_direct(aplayer, nuke)) { 
//        ai.threats.nuclear = 1;
//      }
//    }
//  }
//
//  /* Increase from fear to terror if opponent actually has nukes */
//  if (danger_of_nukes) ai.threats.nuclear++; /* sum of both fears */
//
//  /*** Exploration ***/
//
//  ai.explore.land_done = true;
//  ai.explore.sea_done = true;
//  ai.explore.continent = fc_calloc(ai.num_continents + 1, sizeof(boolean));
//  ai.explore.ocean = fc_calloc(ai.num_oceans + 1, sizeof(boolean));
//  for(tile ptile :  Map.map.tiles){
//    Continent_id continent = map_get_continent(ptile);
//
//    if (Terrain_H.is_ocean(ptile.terrain)) {
//      if (ai.explore.sea_done && ai_handicap(pplayer, H_TARGETS) 
//          && !Maphand.map_is_known(ptile, pplayer)) {
//	/* We're not done there. */
//        ai.explore.sea_done = false;
//        ai.explore.ocean[-continent] = true;
//      }
//      /* skip rest, which is land only */
//      continue;
//    }
//    if (ai.explore.continent[ptile.continent]) {
//      /* we don't need more explaining, we got the point */
//      continue;
//    }
//    if (Map.map_has_special(ptile, Terrain_H.S_HUT) 
//        && (!ai_handicap(pplayer, H_HUTS)
//             || Maphand.map_is_known(ptile, pplayer))) {
//      ai.explore.land_done = false;
//      ai.explore.continent[continent] = true;
//      continue;
//    }
//    if (ai_handicap(pplayer, H_TARGETS) && !Maphand.map_is_known(ptile, pplayer)) {
//      /* this AI must explore */
//      ai.explore.land_done = false;
//      ai.explore.continent[continent] = true;
//    }
//  }
//
//  /*** Statistics ***/
//
//  ai.stats.workers = fc_calloc(ai.num_continents + 1, sizeof(int));
//  ai.stats.cities = fc_calloc(ai.num_continents + 1, sizeof(int));
//  ai.stats.average_production = 0;
//  for (city pcity : pplayer.cities.data) {
//    ai.stats.cities[(int)map_get_continent(pcity.tile)]++;
//    ai.stats.average_production += pcity.shield_surplus;
//  } }
//  ai.stats.average_production /= MAX(1, pplayer.cities.foo_list_size());
//  BV_CLR_ALL(ai.stats.diplomat_reservations);
//  for (unit punit : pplayer.units.data) {
//    tile ptile = punit.tile;
//
//    if (!Terrain_H.is_ocean(ptile.terrain) && unit_flag(punit, Eunit_flag_id.F_SETTLERS)) {
//      ai.stats.workers[(int)map_get_continent(punit.tile)]++;
//    }
//    if (unit_flag(punit, Eunit_flag_id.F_DIPLOMAT) && punit.ai.ai_role == AIUNIT_ATTACK) {
//      /* Heading somewhere on a mission, reserve target. */
//      city pcity = Map.map_get_city(punit.goto_tile);
//
//      if (pcity) {
//        BV_SET(ai.stats.diplomat_reservations, pcity.id);
//      }
//    }
//  } }
//  aiferry_init_stats(pplayer);
//
//  /*** Diplomacy ***/
//
//  if (pplayer.ai.control && !is_barbarian(pplayer)) {
//    ai_diplomacy_calculate(pplayer, ai);
//  }
//
//  /* Question: What can we accept as the reputation of a player before
//   * we start taking action to prevent us from being suckered?
//   * Answer: Very little. */
//  ai.diplomacy.acceptable_reputation =
//           GAME_DEFAULT_REPUTATION -
//           GAME_DEFAULT_REPUTATION / 4;
//  ai.diplomacy.acceptable_reputation_for_ceasefire =
//           GAME_DEFAULT_REPUTATION / 3;
//
//  /* Set per-player variables. We must set all players, since players 
//   * can be created during a turn, and we don't want those to have 
//   * invalid values. */
//  for (i = 0; i < Shared_H.MAX_NUM_PLAYERS + Shared_H.MAX_NUM_BARBARIANS; i++) {
//    player aplayer = get_player(i);
//
//    ai.diplomacy.player_intel[i].is_allied_with_enemy = null;
//    ai.diplomacy.player_intel[i].at_war_with_ally = null;
//    ai.diplomacy.player_intel[i].is_allied_with_ally = null;
//
//    /* Determine who is the leader of our alliance. That is,
//     * whoever has the more cities. */
//    if (Player_P.pplayers_allied(pplayer, aplayer)
//        && aplayer.cities.foo_list_size() > ally_strength) {
//      ally_strength = aplayer.cities.foo_list_size();
//      ally_strongest = aplayer;
//    }
//
//    for(player check_pl: Game.game.players){
//      if (check_pl == pplayer
//          || check_pl == aplayer
//          || !check_pl.is_alive) {
//        continue;
//      }
//      if (Player_P.pplayers_allied(aplayer, check_pl)
//          && pplayer_get_diplstate(pplayer, check_pl).type == diplstate_type.DS_WAR) {
//       ai.diplomacy.player_intel[i].is_allied_with_enemy = check_pl;
//      }
//      if (Player_P.pplayers_allied(pplayer, check_pl)
//          && pplayer_get_diplstate(aplayer, check_pl).type == diplstate_type.DS_WAR) {
//        ai.diplomacy.player_intel[i].at_war_with_ally = check_pl;
//      }
//      if (Player_P.pplayers_allied(aplayer, check_pl)
//          && Player_P.pplayers_allied(pplayer, check_pl)) {
//        ai.diplomacy.player_intel[i].is_allied_with_ally = check_pl;
//      }
//    }
//  }
//  if (ally_strongest != ai.diplomacy.alliance_leader) {
//    ai.diplomacy.alliance_leader = ally_strongest;
//  }
//  ai.diplomacy.spacerace_leader = player_leading_spacerace();
//  
//  ai.diplomacy.production_leader = null;
//  for(player aplayer: Game.game.players){
//    if (ai.diplomacy.production_leader == null
//        || ai.diplomacy.production_leader.score.mfg < aplayer.score.mfg) {
//      ai.diplomacy.production_leader = aplayer;
//    }
//  }
//
//  /*** Priorities ***/
//
//  /* NEVER set these to zero! Weight values are usually multiplied by 
//   * these values, so be careful with them. They are used in city 
//   * and government calculations, and food and shields should be 
//   * slightly bigger because we only look at surpluses there. They
//   * are all WAGs. */
//  ai.food_priority = FOOD_WEIGHTING;
//  ai.shield_priority = SHIELD_WEIGHTING;
//  if (ai_wants_no_science(pplayer)) {
//    ai.luxury_priority = TRADE_WEIGHTING;
//    ai.science_priority = 1;
//  } else {
//    ai.luxury_priority = 1;
//    ai.science_priority = TRADE_WEIGHTING;
//  }
//  ai.gold_priority = TRADE_WEIGHTING;
//  ai.happy_priority = 1;
//  ai.unhappy_priority = TRADE_WEIGHTING; /* danger */
//  ai.angry_priority = TRADE_WEIGHTING * 3; /* grave danger */
//  ai.pollution_priority = POLLUTION_WEIGHTING;
//
//  ai_best_government(pplayer);
//
//  /*** Interception engine ***/
//
//  /* We are tracking a unit if punit.ai.cur_pos is not null. If we
//   * are not tracking, start tracking by setting cur_pos. If we are, 
//   * fill prev_pos with previous cur_pos. This way we get the 
//   * necessary coordinates to calculate a probably trajectory. */
//  for(player aplayer: Game.game.players){
//    if (!aplayer.is_alive || aplayer == pplayer) {
//      continue;
//    }
//    for (unit punit : aplayer.units.data) {
//      if (!punit.ai.cur_pos) {
//        /* Start tracking */
//        punit.ai.cur_pos = &punit.ai.cur_struct;
//        punit.ai.prev_pos = null;
//      } else {
//        punit.ai.prev_struct = punit.ai.cur_struct;
//        punit.ai.prev_pos = &punit.ai.prev_struct;
//      }
//      *punit.ai.cur_pos = punit.tile;
//    } }
//  }
//
//  count_my_units(pplayer);
//}
//
///**************************************************************************
//  Clean up our mess.
//**************************************************************************/
//void ai_data_turn_done(player pplayer)
//{
//  ai_data ai = &aidata[pplayer.player_no];
//
//  free(ai.explore.ocean);     ai.explore.ocean = null;
//  free(ai.explore.continent); ai.explore.continent = null;
//  free(ai.threats.continent); ai.threats.continent = null;
//  free(ai.threats.ocean);
//  ai.threats.ocean = null;
//  free(ai.stats.workers);     ai.stats.workers = null;
//  free(ai.stats.cities);      ai.stats.cities = null;
//}
//
///**************************************************************************
//  Return a pointer to our data
//**************************************************************************/
//ai_data ai_data_get(player pplayer)
//{
//  ai_data ai = &aidata[pplayer.player_no];
//
//  if (ai.num_continents != Map.map.num_continents
//      || ai.num_oceans != Map.map.num_oceans) {
//    /* we discovered more continents, recalculate! */
//    ai_data_turn_done(pplayer);
//    ai_data_turn_init(pplayer);
//  }
//  return ai;
//}
//
///**************************************************************************
//  Initialize with sane values.
//**************************************************************************/
//void ai_data_init(player pplayer)
//{
//  ai_data ai = &aidata[pplayer.player_no];
//  int i;
//
//  ai.govt_reeval = 0;
//  ai.government_want = fc_realloc(ai.government_want,
//				   ((Game.game.government_count + 1)
//				    * sizeof(*ai.government_want)));
//  memset(ai.government_want, 0,
//	 (Game.game.government_count + 1) * sizeof(*ai.government_want));
//
//  ai.diplomacy.target = null;
//  ai.diplomacy.strategy = WIN_OPEN;
//  ai.diplomacy.timer = 0;
//  ai.diplomacy.countdown = 0;
//  ai.diplomacy.love_coeff = 4; /* 4% */
//  ai.diplomacy.love_incr = MAX_AI_LOVE * 4 / 100;
//  ai.diplomacy.req_love_for_peace = MAX_AI_LOVE * 8 / 100;
//  ai.diplomacy.req_love_for_alliance = MAX_AI_LOVE * 16 / 100;
//  ai.diplomacy.req_love_for_ceasefire = 0;
//  ai.diplomacy.alliance_leader = pplayer;
//
//  for (i = 0; i < Shared_H.MAX_NUM_PLAYERS + Shared_H.MAX_NUM_BARBARIANS; i++) {
//    ai.diplomacy.player_intel[i].spam = i % 5; /* pseudorandom */
//    ai.diplomacy.player_intel[i].distance = 1;
//    ai.diplomacy.player_intel[i].ally_patience = 0;
//    pplayer.ai.love[i] = 1;
//    ai.diplomacy.player_intel[i].asked_about_peace = 0;
//    ai.diplomacy.player_intel[i].asked_about_alliance = 0;
//    ai.diplomacy.player_intel[i].asked_about_ceasefire = 0;
//    ai.diplomacy.player_intel[i].warned_about_space = 0;
//  }
//}
}