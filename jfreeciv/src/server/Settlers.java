package server;

public class Settlers{

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
//#include <string.h>
//
//#include "city.h"
//#include "Game.game.h"
//#include "government.h"
//#include "log.h"
//#include "Map.map.h"
//#include "mem.h"
//#include "packets.h"
//#include "support.h"
//#include "timing.h"
//
//#include "citytools.h"
//#include "gotohand.h"
//#include "maphand.h"
//#include "plrhand.h"
//#include "unithand.h"
//#include "unittools.h"
//
//#include "aicity.h"
//#include "aidata.h"
//#include "ailog.h"
//#include "aisettler.h"
//#include "aitools.h"
//#include "aiunit.h"
//#include "citymap.h"
//
//#include "settlers.h"
//
//BV_DEFINE(nearness, Shared_H.MAX_NUM_PLAYERS + Shared_H.MAX_NUM_BARBARIANS);
//static nearness *territory;
//#define TERRITORY(ptile) territory[(ptile).index]
//
//BV_DEFINE(enemy_mask, Shared_H.MAX_NUM_PLAYERS + Shared_H.MAX_NUM_BARBARIANS);
//static enemy_mask enemies[Shared_H.MAX_NUM_PLAYERS + Shared_H.MAX_NUM_BARBARIANS];
//
//static boolean is_already_assigned(unit myunit, player pplayer,
//				tile ptile);
//
///**************************************************************************
//  Build a city and initialize AI infrastructure cache.
//**************************************************************************/
//static boolean ai_do_build_city(player pplayer, unit punit)
//{
//  tile ptile = punit.tile;
//  city pcity;
//
//  assert(pplayer == punit.unit_owner());
//  Unithand.handle_unit_activity_request(punit, unit_activity.ACTIVITY_IDLE);
//
//  /* Free city reservations */
//  ai_unit_new_role(punit, AIUNIT_NONE, null);
//
//  handle_unit_build_city(pplayer, punit.id,
//			 Citytools.city_name_suggestion(pplayer, ptile));
//  pcity = Map.map_get_city(ptile);
//  if (!pcity) {
//    util.freelog(Log.LOG_ERROR, "%s: Failed to build city at (%d, %d)", 
//            pplayer.name, TILE_XY(ptile));
//    return false;
//  }
//
//  /* We have to rebuild at least the cache for this city.  This event is
//   * rare enough we might as well build the whole thing.  Who knows what
//   * else might be cached in the future? */
//  assert(pplayer == City.city_owner(pcity));
//  initialize_infrastructure_cache(pplayer);
//
//  /* Init ai.choice. Handling ferryboats might use it. */
//  init_choice(&pcity.ai.choice);
//
//  return true;
//}
//
///**************************************************************************
//  Amortize means gradually paying off a cost or debt over time. In freeciv
//  terms this means we calculate how much less worth something is to us
//  depending on how long it will take to complete.
//
//  amortize(benefit, delay) returns benefit * ((MORT - 1)/MORT)^delay
//  (^ = to the power of)
//
//  Plus, it has tests to prevent the numbers getting too big.  It takes
//  advantage of the fact that (23/24)^12 approximately = 3/5 to chug 
//  through delay in chunks of 12, and then does the remaining 
//  multiplications of (23/24).
//**************************************************************************/
//int amortize(int benefit, int delay)
//{
//  int num = MORT - 1;
//  int denom;
//  int s = 1;
//  assert(delay >= 0);
//  if (benefit < 0) { s = -1; benefit *= s; }
//  while (delay > 0 && benefit != 0) {
//    denom = 1;
//    while (delay >= 12 && (benefit >> 28) == 0 && (denom >> 27) == 0) {
//      benefit *= 3;          /* this is a kluge but it is 99.9% accurate and saves time */
//      denom *= 5;      /* as long as MORT remains 24! -- Syela */
//      delay -= 12;
//    }
//    while ((benefit >> 25) == 0 && delay > 0 && (denom >> 25) == 0) {
//      benefit *= num;
//      denom *= MORT;
//      delay--;
//    }
//    if (denom > 1) { /* The "+ (denom/2)" makes the rounding correct */
//      benefit = (benefit + (denom/2)) / denom;
//    }
//  }
//  return(benefit * s);
//}
//
///**************************************************************************
//  Initialize the territory Map.map. 
//
//  TODO: Add borders support.
//**************************************************************************/
//void init_settlers()
//{
//  /* (Re)allocate map arrays.  Note that the server may run more than one
//   * Game.game so the realloc() is necessary. */
//  territory = fc_realloc(territory,
//                         Map.map.xsize * Map.map.ysize * sizeof(*territory));
//}
//
///**************************************************************************
//  Manages settlers.
//**************************************************************************/
//void ai_manage_settler(player pplayer, unit punit)
//{
//  punit.ai.control = true;
//  /* if BUILD_CITY must remain BUILD_CITY, otherwise turn into autosettler */
//  if (punit.ai.ai_role == AIUNIT_NONE) {
//    ai_unit_new_role(punit, AIUNIT_AUTO_SETTLER, null);
//  }
//  return;
//}
//
///**************************************************************************
// return 1 if there is already a unit on this square or one destined for it 
// (via goto)
//**************************************************************************/
//static boolean is_already_assigned(unit myunit, player pplayer, 
//    tile ptile)
//{
//  if (Map.same_pos(myunit.tile, ptile)
//      || (myunit.goto_tile /* HACK? */
//	  && Map.same_pos(myunit.goto_tile, ptile))) {
///* I'm still not sure this is exactly right -- Syela */
//    unit_list_iterate(ptile.units, punit)
//      if (myunit==punit) continue;
//      if (!Player_P.pplayers_allied(punit.unit_owner(), pplayer))
//        return true; /* oops, tile is occupied! */
//      if (unit_flag(punit, Eunit_flag_id.F_SETTLERS) && unit_flag(myunit, Eunit_flag_id.F_SETTLERS))
//        return true;
//    }
//    return false;
//  }
//  return TEST_BIT(ptile.assigned, pplayer.player_no);
//}
//
///**************************************************************************
//  Returns a measure of goodness of a tile to pcity.
//
//  FIXME: foodneed and prodneed are always 0.
//**************************************************************************/
//int city_tile_value(city pcity, int x, int y, 
//		    int foodneed, int prodneed)
//{
// int food = city_get_food_tile(x, y, pcity);
// int shield = city_get_shields_tile(x, y, pcity);
// int trade = city_get_trade_tile(x, y, pcity);
// int value = 0;
//
// /* Each food, trade, and shield gets a certain weighting.  We also benefit
//  * tiles that have at least one of an item - this promotes balance and 
//  * also accounts for INC_TILE effects. */
// value += food * FOOD_WEIGHTING;
// if (food > 0) {
//   value += FOOD_WEIGHTING / 2;
// }
// value += shield * SHIELD_WEIGHTING;
// if (shield > 0) {
//   value += SHIELD_WEIGHTING / 2;
// }
// value += trade * TRADE_WEIGHTING;
// if (trade > 0) {
//   value += TRADE_WEIGHTING / 2;
// }
//
// return value;
//}  
//
///**************************************************************************
//  Calculates the value of removing pollution at the given tile.
//
//    (map_x, map_y) is the map position of the tile.
//    (city_x, city_y) is the city position of the tile with respect to pcity.
//
//  The return value is the goodness of the tile after the cleanup.  This
//  should be compared to the goodness of the tile currently (see
//  city_tile_value(); note that this depends on the AI's weighting
//  values).
//**************************************************************************/
//static int ai_calc_pollution(city pcity, int city_x, int city_y,
//			     int best, tile ptile)
//{
//  int goodness;
//
//  if (!Map.map_has_special(ptile, S_POLLUTION)) {
//    return -1;
//  }
//  Map.map_clear_special(ptile, S_POLLUTION);
//  goodness = city_tile_value(pcity, city_x, city_y, 0, 0);
//  Map.map_set_special(ptile, S_POLLUTION);
//
//  /* FIXME: need a better way to guarantee pollution is cleaned up. */
//  goodness = (goodness + best + 50) * 2;
//
//  return goodness;
//}
//
///**************************************************************************
//  Calculates the value of removing fallout at the given tile.
//
//    (map_x, map_y) is the map position of the tile.
//    (city_x, city_y) is the city position of the tile with respect to pcity.
//
//  The return value is the goodness of the tile after the cleanup.  This
//  should be compared to the goodness of the tile currently (see
//  city_tile_value(); note that this depends on the AI's weighting
//  values).
//**************************************************************************/
//static int ai_calc_fallout(city pcity, player pplayer,
//			   int city_x, int city_y, int best,
//			   tile ptile)
//{
//  int goodness;
//
//  if (!Map.map_has_special(ptile, S_FALLOUT)) {
//    return -1;
//  }
//  Map.map_clear_special(ptile, S_FALLOUT);
//  goodness = city_tile_value(pcity, city_x, city_y, 0, 0);
//  Map.map_set_special(ptile, S_FALLOUT);
//
//  /* FIXME: need a better way to guarantee fallout is cleaned up. */
//  if (!pplayer.ai.control) {
//    goodness = (goodness + best + 50) * 2;
//  }
//
//  return goodness;
//}
//
///**************************************************************************
//  Returns true if tile at (map_x,map_y) is useful as a source of
//  irrigation.  This takes player vision into account, but allows the AI
//  to cheat.
//
//  This function should probably only be used by
//  is_wet_or_is_wet_cardinal_around, below.
//**************************************************************************/
//static boolean is_wet(player pplayer, tile ptile)
//{
//  int terrain;
//  enum int special;
//
//  /* FIXME: this should check a handicap. */
//  if (!pplayer.ai.control && !Maphand.map_is_known(ptile, pplayer)) {
//    return false;
//  }
//
//  terrain = ptile.terrain;
//  if (Terrain_H.is_ocean(terrain)) {
//    /* TODO: perhaps salt water should not be usable for irrigation? */
//    return true;
//  }
//
//  special = map_get_special(ptile);
//  if (contains_special(special, Terrain_H.S_RIVER)
//      || contains_special(special, S_IRRIGATION)) {
//    return true;
//  }
//
//  return false;
//}
//
///**************************************************************************
//  Returns true if there is an irrigation source adjacent to the given x, y
//  position.  This takes player vision into account, but allows the AI to
//  cheat. (See is_wet() for the definition of an irrigation source.)
//
//  This function exactly mimics is_water_adjacent_to_tile, except that it
//  checks vision.
//**************************************************************************/
//static boolean is_wet_or_is_wet_cardinal_around(player pplayer,
//					     tile ptile)
//{
//  if (is_wet(pplayer, ptile)) {
//    return true;
//  }
//
//  cardinal_for(tile tile1: util.adjc_tile_iterate(ptile)) {
//    if (is_wet(pplayer, tile1)) {
//      return true;
//    }
//  } cardinal_adjc_iterate_end;
//
//  return false;
//}
//
///**************************************************************************
//  Calculate the benefit of irrigating the given tile.
//
//    (map_x, map_y) is the map position of the tile.
//    (city_x, city_y) is the city position of the tile with respect to pcity.
//    pplayer is the player under consideration.
//
//  The return value is the goodness of the tile after the irrigation.  This
//  should be compared to the goodness of the tile currently (see
//  city_tile_value(); note that this depends on the AI's weighting
//  values).
//**************************************************************************/
//static int ai_calc_irrigate(city pcity, player pplayer,
//			    int city_x, int city_y, tile ptile)
//{
//  int goodness;
//  int old_terrain = ptile.terrain;
//  enum int old_special = ptile.special;
//  tile_type type = get_tile_type(old_terrain);
//  int new_terrain = type.irrigation_result;
//
//  if (old_terrain != new_terrain && new_terrain != Terrain_H.T_NONE) {
//    /* Irrigation would change the terrain type, clearing the mine
//     * in the process.  Calculate the benefit of doing so. */
//    if (ptile.city && Terrain_H.terrain_has_flag(new_terrain, TER_NO_CITIES)) {
//      return -1;
//    }
//    ptile.terrain = new_terrain;
//    Map.map_clear_special(ptile, S_MINE);
//    goodness = city_tile_value(pcity, city_x, city_y, 0, 0);
//    ptile.terrain = old_terrain;
//    ptile.special = old_special;
//    return goodness;
//  } else if (old_terrain == new_terrain
//	     && !Map.tile_has_special(ptile, S_IRRIGATION)
//	     && is_wet_or_is_wet_cardinal_around(pplayer, ptile)) {
//    /* The tile is currently unirrigated; irrigating it would put an
//     * S_IRRIGATE on it replacing any S_MINE already there.  Calculate
//     * the benefit of doing so. */
//    Map.map_clear_special(ptile, S_MINE);
//    Map.map_set_special(ptile, S_IRRIGATION);
//    goodness = city_tile_value(pcity, city_x, city_y, 0, 0);
//    ptile.special = old_special;
//    assert(ptile.terrain == old_terrain);
//    return goodness;
//  } else if (old_terrain == new_terrain
//	     && Map.tile_has_special(ptile, S_IRRIGATION)
//	     && !Map.tile_has_special(ptile, S_FARMLAND)
//	     && Player_P.player_knows_techs_with_flag(pplayer, TF_FARMLAND)
//	     && is_wet_or_is_wet_cardinal_around(pplayer, ptile)) {
//    /* The tile is currently irrigated; irrigating it more puts an
//     * S_FARMLAND on it.  Calculate the benefit of doing so. */
//    assert(!Map.tile_has_special(ptile, S_MINE));
//    Map.map_set_special(ptile, S_FARMLAND);
//    goodness = city_tile_value(pcity, city_x, city_y, 0, 0);
//    Map.map_clear_special(ptile, S_FARMLAND);
//    assert(ptile.terrain == old_terrain && ptile.special == old_special);
//    return goodness;
//  } else {
//    return -1;
//  }
//}
//
///**************************************************************************
//  Calculate the benefit of mining the given tile.
//
//    (map_x, map_y) is the map position of the tile.
//    (city_x, city_y) is the city position of the tile with respect to pcity.
//    pplayer is the player under consideration.
//
//  The return value is the goodness of the tile after the mining.  This
//  should be compared to the goodness of the tile currently (see
//  city_tile_value(); note that this depends on the AI's weighting
//  values).
//**************************************************************************/
//static int ai_calc_mine(city pcity,
//			int city_x, int city_y, tile ptile)
//{
//  int goodness;
//  int old_terrain = ptile.terrain;
//  enum int old_special = ptile.special;
//  tile_type type = get_tile_type(old_terrain);
//  int new_terrain = type.mining_result;
//
//  if (old_terrain != new_terrain && new_terrain != Terrain_H.T_NONE) {
//    /* Mining would change the terrain type, clearing the irrigation
//     * in the process.  Calculate the benefit of doing so. */
//    if (ptile.city && Terrain_H.terrain_has_flag(new_terrain, TER_NO_CITIES)) {
//      return -1;
//    }
//    ptile.terrain = new_terrain;
//    Map.map_clear_special(ptile, S_IRRIGATION);
//    Map.map_clear_special(ptile, S_FARMLAND);
//    goodness = city_tile_value(pcity, city_x, city_y, 0, 0);
//    ptile.terrain = old_terrain;
//    ptile.special = old_special;
//    return goodness;
//  } else if (old_terrain == new_terrain
//	     && !Map.tile_has_special(ptile, S_MINE)) {
//    /* The tile is currently unmined; mining it would put an S_MINE on it
//     * replacing any S_IRRIGATION/S_FARMLAND already there.  Calculate
//     * the benefit of doing so. */
//    Map.map_clear_special(ptile, S_IRRIGATION);
//    Map.map_clear_special(ptile, S_FARMLAND);
//    Map.map_set_special(ptile, S_MINE);
//    goodness = city_tile_value(pcity, city_x, city_y, 0, 0);
//    ptile.special = old_special;
//    assert(ptile.terrain == old_terrain);
//    return goodness;
//  } else {
//    return -1;
//  }
//  return goodness;
//}
//
///**************************************************************************
//  Calculate the benefit of transforming the given tile.
//
//    (ptile) is the map position of the tile.
//    (city_x, city_y) is the city position of the tile with respect to pcity.
//    pplayer is the player under consideration.
//
//  The return value is the goodness of the tile after the transform.  This
//  should be compared to the goodness of the tile currently (see
//  city_tile_value(); note that this depends on the AI's weighting
//  values).
//**************************************************************************/
//static int ai_calc_transform(city pcity,
//			     int city_x, int city_y, tile ptile)
//{
//  int goodness;
//  int old_terrain = ptile.terrain;
//  enum int old_special = ptile.special;
//  tile_type type = get_tile_type(old_terrain);
//  int new_terrain = type.transform_result;
//
//  if (old_terrain == new_terrain || new_terrain == Terrain_H.T_NONE) {
//    return -1;
//  }
//
//  if (Terrain_H.is_ocean(old_terrain) && !Terrain_H.is_ocean(new_terrain)
//      && !can_reclaim_ocean(ptile)) {
//    /* Can't change ocean into land here. */
//    return -1;
//  }
//  if (Terrain_H.is_ocean(new_terrain) && !Terrain_H.is_ocean(old_terrain)
//      && !can_channel_land(ptile)) {
//    /* Can't change land into ocean here. */
//    return -1;
//  }
//
//  if (ptile.city && Terrain_H.terrain_has_flag(new_terrain, TER_NO_CITIES)) {
//    return -1;
//  }
//
//  ptile.terrain = new_terrain;
//
//  if (get_tile_type(new_terrain).mining_result != new_terrain) {
//    Map.map_clear_special(ptile, S_MINE);
//  }
//  if (get_tile_type(new_terrain).irrigation_result != new_terrain) {
//    Map.map_clear_special(ptile, S_FARMLAND);
//    Map.map_clear_special(ptile, S_IRRIGATION);
//  }
//    
//  goodness = city_tile_value(pcity, city_x, city_y, 0, 0);
//
//  ptile.terrain = old_terrain;
//  ptile.special = old_special;
//
//  return goodness;
//}
//
///**************************************************************************
//  Calculate the attractiveness of building a road/rail at the given tile.
//
//  This calculates the overall benefit of connecting the civilization; this
//  is independent from the local tile (trade) bonus granted by the road.
//
//  "special" must be either Terrain_H.S_ROAD or Terrain_H.S_RAILROAD.
//**************************************************************************/
//static int road_bonus(tile ptile, enum int special)
//{
//  int bonus = 0, i;
//  boolean has_road[12], is_slow[12];
//  int dx[12] = {-1,  0,  1, -1, 1, -1, 0, 1,  0, -2, 2, 0};
//  int dy[12] = {-1, -1, -1,  0, 0,  1, 1, 1, -2,  0, 0, 2};
//  
//  assert(special == Terrain_H.S_ROAD || special == Terrain_H.S_RAILROAD);
//
//  for (i = 0; i < 12; i++) {
//    tile tile1 = map_pos_to_tile(ptile.x + dx[i], ptile.y + dy[i]);
//
//    if (!tile1) {
//      has_road[i] = false;
//      is_slow[i] = false; /* FIXME: should be true? */
//    } else {
//      tile_type ptype = get_tile_type(tile1.terrain);
//
//      has_road[i] = Map.tile_has_special(tile1, special);
//
//      /* If true, this value indicates that this tile does not need
//       * a road connector.  This is set for terrains which cannot have
//       * road or where road takes "too long" to build. */
//      is_slow[i] = (ptype.road_time == 0 || ptype.road_time > 5);
//
//      if (!has_road[i]) {
//	for (unit punit : tile1.units.data) {
//	  if (punit.activity == ACTIVITY_ROAD 
//              || punit.activity == ACTIVITY_RAILROAD) {
//	    /* If a road is being built here, consider as if it's already
//	     * built. */
//	    has_road[i] = true;
//          }
//	} }
//      }
//    }
//  }
//
//  /*
//   * Consider the following tile arrangement (numbered in hex):
//   *
//   *   8
//   *  012
//   * 93 4A
//   *  567
//   *   B
//   *
//   * these are the tiles defined by the (dx,dy) arrays above.
//   *
//   * Then the following algorithm is supposed to determine if it's a good
//   * idea to build a road here.  Note this won't work well for hex maps
//   * since the (dx,dy) arrays will not cover the same tiles.
//   *
//   * FIXME: if you can understand the algorithm below please rewrite this
//   * explanation!
//   */
//  if (has_road[0]
//      && !has_road[1] && !has_road[3]
//      && (!has_road[2] || !has_road[8])
//      && (!is_slow[2] || !is_slow[4] || !is_slow[7]
//	  || !is_slow[6] || !is_slow[5])) {
//    bonus++;
//  }
//  if (has_road[2]
//      && !has_road[1] && !has_road[4]
//      && (!has_road[7] || !has_road[10])
//      && (!is_slow[0] || !is_slow[3] || !is_slow[7]
//	  || !is_slow[6] || !is_slow[5])) {
//    bonus++;
//  }
//  if (has_road[5]
//      && !has_road[6] && !has_road[3]
//      && (!has_road[5] || !has_road[11])
//      && (!is_slow[2] || !is_slow[4] || !is_slow[7]
//	  || !is_slow[1] || !is_slow[0])) {
//    bonus++;
//  }
//  if (has_road[7]
//      && !has_road[6] && !has_road[4]
//      && (!has_road[0] || !has_road[9])
//      && (!is_slow[2] || !is_slow[3] || !is_slow[0]
//	  || !is_slow[1] || !is_slow[5])) {
//    bonus++;
//  }
//
//  /*   A
//   *  B*B
//   *  CCC
//   *
//   * We are at tile *.  If tile A has a road, and neither B tile does, and
//   * one C tile is a valid destination, then we might want a road here.
//   *
//   * Of course the same logic applies if you rotate the diagram.
//   */
//  if (has_road[1] && !has_road[4] && !has_road[3]
//      && (!is_slow[5] || !is_slow[6] || !is_slow[7])) {
//    bonus++;
//  }
//  if (has_road[3] && !has_road[1] && !has_road[6]
//      && (!is_slow[2] || !is_slow[4] || !is_slow[7])) {
//    bonus++;
//  }
//  if (has_road[4] && !has_road[1] && !has_road[6]
//      && (!is_slow[0] || !is_slow[3] || !is_slow[5])) {
//    bonus++;
//  }
//  if (has_road[6] && !has_road[4] && !has_road[3]
//      && (!is_slow[0] || !is_slow[1] || !is_slow[2])) {
//    bonus++;
//  }
//
//  return bonus;
//}
//
///**************************************************************************
//  Calculate the benefit of building a road at the given tile.
//
//    (map_x, map_y) is the map position of the tile.
//    (city_x, city_y) is the city position of the tile with respect to pcity.
//    pplayer is the player under consideration.
//
//  The return value is the goodness of the tile after the road is built.
//  This should be compared to the goodness of the tile currently (see
//  city_tile_value(); note that this depends on the AI's weighting
//  values).
//
//  This function does not calculate the benefit of being able to quickly
//  move units (i.e., of connecting the civilization).  See road_bonus() for
//  that calculation.
//**************************************************************************/
//static int ai_calc_road(city pcity, player pplayer,
//			int city_x, int city_y, tile ptile)
//{
//  int goodness;
//
//  if (!Terrain_H.is_ocean(ptile.terrain)
//      && (!Map.tile_has_special(ptile, Terrain_H.S_RIVER)
//	  || Player_P.player_knows_techs_with_flag(pplayer, TF_BRIDGE))
//      && !Map.tile_has_special(ptile, Terrain_H.S_ROAD)) {
//
//    /* HACK: calling Map.map_set_special here will have side effects, so we
//     * have to set it manually. */
//    assert((ptile.special & Terrain_H.S_ROAD) == 0);
//    ptile.special |= Terrain_H.S_ROAD;
//
//    goodness = city_tile_value(pcity, city_x, city_y, 0, 0);
//
//    ptile.special &= ~Terrain_H.S_ROAD;
//
//    return goodness;
//  } else {
//    return -1;
//  }
//}
//
///**************************************************************************
//  Calculate the benefit of building a railroad at the given tile.
//
//    (ptile) is the map position of the tile.
//    (city_x, city_y) is the city position of the tile with respect to pcity.
//    pplayer is the player under consideration.
//
//  The return value is the goodness of the tile after the railroad is built.
//  This should be compared to the goodness of the tile currently (see
//  city_tile_value(); note that this depends on the AI's weighting
//  values).
//
//  This function does not calculate the benefit of being able to quickly
//  move units (i.e., of connecting the civilization).  See road_bonus() for
//  that calculation.
//**************************************************************************/
//static int ai_calc_railroad(city pcity, player pplayer,
//			    int city_x, int city_y, tile ptile)
//{
//  int goodness;
//  enum int old_special;
//
//  if (!Terrain_H.is_ocean(ptile.terrain)
//      && Player_P.player_knows_techs_with_flag(pplayer, tech_flag_id.TF_RAILROAD)
//      && !Map.tile_has_special(ptile, Terrain_H.S_RAILROAD)) {
//    old_special = ptile.special;
//
//    /* HACK: calling Map.map_set_special here will have side effects, so we
//     * have to set it manually. */
//    ptile.special |= (Terrain_H.S_ROAD | Terrain_H.S_RAILROAD);
//
//    goodness = city_tile_value(pcity, city_x, city_y, 0, 0);
//
//    ptile.special = old_special;
//
//    return goodness;
//  } else {
//    return -1;
//  }
//}
//
///**************************************************************************
//  Tries to find a boat for our settler. Requires warmap to be initialized
//  with respect to x, y. cap is the requested capacity on the transport.
//  Note that it may return a transport with less than cap capacity if this
//  transport has zero move cost to x, y.
//
//  The "virtual boats" code is not used. It is probably too unreliable, 
//  since the AI switches its production back and forth continously.
//
//  FIXME: if there is a (free) boat in a city filled with units, 
//  ground_unit_transporter_capacity will return negative.
//  TODO: Kill me.  There is a reliable version of this, find_ferry.
//**************************************************************************/
//int find_boat(player pplayer, tile *ptile, int cap)
//{
//  int best = 22; /* arbitrary maximum distance, I will admit! */
//  int id = 0;
//  unit_list_iterate(pplayer.units, aunit)
//    if (is_ground_units_transport(aunit)) {
//      if (WARMAP_COST(aunit.tile) < best &&
//	  (WARMAP_COST(aunit.tile) == 0 ||
//	   ground_unit_transporter_capacity(aunit.tile,
//					    pplayer) >= cap)) {
//        id = aunit.id;
//        best = WARMAP_COST(aunit.tile);
//	*ptile = aunit.tile;
//      }
//    }
//  }
//  if (id != 0) return(id);
//  return(id);
//}
//
///**************************************************************************
//  Returns true if there are (other) ground units than punit stacked on
//  punit's tile.
//**************************************************************************/
//unit other_passengers(unit punit)
//{
//  unit_list_iterate(punit.tile.units, aunit)
//    if (is_ground_unit(aunit) && aunit != punit) return aunit;
//  }
//  return null;
//}
//
///****************************************************************************
//  Compares the best known tile improvement action with improving the tile
//  at (x,y) with activity act.  Calculates the value of improving the tile
//  by discounting the total value by the time it would take to do the work
//  and multiplying by some factor.
//****************************************************************************/
//static void consider_settler_action(player pplayer, 
//                                    enum unit_activity act, int extra, 
//                                    int new_tile_value, int old_tile_value,
//				    boolean in_use, int delay,
//				    int *best_value,
//				    int *best_old_tile_value,
//				    enum unit_activity *best_act,
//				    tile *best_tile,
//                                    tile ptile)
//{
//  int discount_value, base_value = 0;
//  int total_value;
//  boolean consider;
//
//  if (extra >= 0) {
//    consider = true;
//  } else {
//    consider = (new_tile_value > old_tile_value);
//  }
//
//  if (consider) {
//    int diff = new_tile_value - old_tile_value;
//
//    /* The 64x is because we are dealing with small ints, usually from 0-20,
//     * which are insufficiently large to use directly in amortize().  Tiles
//     * which are not currently in use do not give us an improvement until
//     * a citizen works them, so they are reduced in value by 1/4. */
//    base_value = diff * (in_use ? 64 : 16) + extra * 64;
//    base_value = MAX(0, base_value);
//
//    discount_value = amortize(base_value, delay);
//
//    /* The total value is (roughly) equal to the base value multiplied by
//     * d / (1 - d), where d is the discount. (discount_value / base value)
//     * The MAX is a guard against the base value being greater or equal
//     * than the discount value, which would only happen if it or the 
//     * delay is <= 0. */
//    total_value = ((discount_value * base_value)
//		   / (MAX(1, base_value - discount_value))) / 64;
//  } else {
//    total_value = 0;
//  }
//
//  if (total_value > *best_value
//      || (total_value == *best_value
//	  && old_tile_value > *best_old_tile_value)) {
//    util.freelog(Log.LOG_DEBUG,
//	    "Replacing (%d, %d) = %d with %s (%d, %d) = %d [d=%d b=%d]",
//	    TILE_XY(*best_tile), *best_value, get_activity_text(act),
//	    TILE_XY(ptile), total_value,
//            delay, base_value);
//    *best_value = total_value;
//    *best_old_tile_value = old_tile_value;
//    *best_act = act;
//    *best_tile = ptile;
//  }
//}
//
///**************************************************************************
//  Returns how much food a settler will consume out of the city's foodbox
//  when created. If unit has id zero it is assumed to be a virtual unit
//  inside a city.
//
//  FIXME: This function should be generalised and then moved into 
//  common/unittype.c - Per
//**************************************************************************/
//static int unit_foodbox_cost(unit punit)
//{
//  int cost = 30;
//
//  if (punit.id == 0) {
//    /* It is a virtual unit, so must start in a city... */
//    city pcity = Map.map_get_city(punit.tile);
//
//    /* The default is to lose 100%.  The growth bonus reduces this. */
//    int foodloss_pct = 100 - Effects.get_city_bonus(pcity, EFT_GROWTH_FOOD);
//
//    foodloss_pct = CLIP(0, foodloss_pct, 100);
//    assert(pcity != null);
//    cost = city_granary_size(pcity.size);
//    cost = cost * foodloss_pct / 100;
//  }
//
//  return cost;
//}
//
///**************************************************************************
//  Calculates a unit's food upkeep (per turn).
//**************************************************************************/
//static int unit_food_upkeep(unit punit)
//{
//  player pplayer = punit.unit_owner();
//  int upkeep = utype_food_cost(punit.unit_type(),
//			       get_gov_pplayer(pplayer));
//  if (punit.id != 0 && punit.homecity == 0)
//    upkeep = 0; /* thanks, Peter */
//
//  return upkeep;
//}
//
///****************************************************************************
//  Finds tiles to improve, using punit.
//
//  The returned value is the goodness of the best tile and action found.  If
//  this return value is >0, then (gx,gy) indicates the tile chosen and bestact
//  indicates the activity it wants to do.  If 0 is returned then there are no
//  worthwhile activities available.
//****************************************************************************/
//static int evaluate_improvements(unit punit,
//				 enum unit_activity *best_act,
//				 tile *best_tile)
//{
//  city mycity = Map.map_get_city(punit.tile);
//  player pplayer = punit.unit_owner();
//  boolean in_use;			/* true if the target square is being used
//				   by one of our cities */
//  Continent_id ucont     = map_get_continent(punit.tile);
//  int mv_rate         = punit.unit_type().move_rate;
//  int mv_turns;			/* estimated turns to move to target square */
//  int oldv;			/* current value of consideration tile */
//  int best_oldv = 9999;		/* oldv of best target so far; compared if
//				   newv==best_newv; not initialized to zero,
//				   so that newv=0 activities are not chosen */
//  int food_upkeep        = unit_food_upkeep(punit);
//  int food_cost          = unit_foodbox_cost(punit);
//  boolean can_rr = Player_P.player_knows_techs_with_flag(pplayer, tech_flag_id.TF_RAILROAD);
//
//  int best_newv = 0;
//  enemy_mask my_enemies = enemies[pplayer.player_no]; /* optimalization */
//
//  generate_warmap(mycity, punit);
//
//  for (city pcity : pplayer.cities.data) {
//#ifdef REALLY_DEBUG_THIS
//    util.freelog(Log.LOG_DEBUG, "Evaluating improvements for %s...", pcity.name);
//#endif
//    /* try to work near the city */
//    city_map_checked_iterate(pcity.tile, i, j, ptile) {
//      if (City.get_worker_city(pcity, i, j) == city_tile_type.C_TILE_UNAVAILABLE
//	  || Terrain_H.terrain_has_flag(pcity.tile.terrain, TER_UNSAFE)) {
//	/* Don't risk bothering with this tile. */
//	continue;
//      }
//      in_use = (City.get_worker_city(pcity, i, j) == city_tile_type.C_TILE_WORKER);
//      if (map_get_continent(ptile) == ucont
//	  && WARMAP_COST(ptile) <= THRESHOLD * mv_rate
//	  && !BV_CHECK_MASK(TERRITORY(ptile), my_enemies)
//	  /* pretty good, hope it's enough! -- Syela */
//	  && !is_already_assigned(punit, pplayer, ptile)) {
//	/* calling is_already_assigned once instead of four times
//	   for obvious reasons;  structure is much the same as it once
//	   was but subroutines are not -- Syela	*/
//	int time;
//	mv_turns = (WARMAP_COST(ptile)) / mv_rate;
//	oldv = city_tile_value(pcity, i, j, 0, 0);
//
//	/* now, consider various activities... */
//
//	time = mv_turns
//	  + get_turns_for_activity_at(punit, ACTIVITY_IRRIGATE, ptile);
//	consider_settler_action(pplayer, ACTIVITY_IRRIGATE, -1,
//				pcity.ai.irrigate[i][j], oldv, in_use, time,
//				&best_newv, &best_oldv, best_act, best_tile,
//				ptile);
//
//	if (unit_flag(punit, F_TRANSFORM)) {
//	  time = mv_turns
//	    + get_turns_for_activity_at(punit, ACTIVITY_TRANSFORM, ptile);
//	  consider_settler_action(pplayer, ACTIVITY_TRANSFORM, -1,
//				  pcity.ai.transform[i][j], oldv, in_use, time,
//				  &best_newv, &best_oldv, best_act, best_tile,
//				  ptile);
//	}
//
//	time = mv_turns
//	  + get_turns_for_activity_at(punit, ACTIVITY_MINE, ptile);
//	consider_settler_action(pplayer, ACTIVITY_MINE, -1,
//				pcity.ai.mine[i][j], oldv, in_use, time,
//				&best_newv, &best_oldv, best_act, best_tile,
//				ptile);
//
//	if (!Map.map_has_special(ptile, Terrain_H.S_ROAD)) {
//	  time = mv_turns
//	    + get_turns_for_activity_at(punit, ACTIVITY_ROAD, ptile);
//	  consider_settler_action(pplayer, ACTIVITY_ROAD,
//				  road_bonus(ptile, Terrain_H.S_ROAD) * 5,
//				  pcity.ai.road[i][j], oldv, in_use, time,
//				  &best_newv, &best_oldv, best_act, best_tile,
//				  ptile);
//
//	  if (can_rr) {
//	    /* Count road time plus rail time. */
//	    time += get_turns_for_activity_at(punit, ACTIVITY_RAILROAD, ptile);
//	    consider_settler_action(pplayer, ACTIVITY_ROAD,
//				    road_bonus(ptile, Terrain_H.S_RAILROAD) * 3,
//				    pcity.ai.railroad[i][j], oldv,
//				    in_use, time,
//				    &best_newv, &best_oldv,
//				    best_act, best_tile,
//				    ptile);
//	  }
//	} else if (!Map.map_has_special(ptile, Terrain_H.S_RAILROAD)
//		   && can_rr) {
//	  time = mv_turns
//	    + get_turns_for_activity_at(punit, ACTIVITY_RAILROAD, ptile);
//	  consider_settler_action(pplayer, ACTIVITY_RAILROAD,
//				  road_bonus(ptile, Terrain_H.S_RAILROAD) * 3,
//				  pcity.ai.railroad[i][j], oldv, in_use, time,
//				  &best_newv, &best_oldv,
//				  best_act, best_tile,
//				  ptile);
//	} /* end Terrain_H.S_ROAD else */
//
//	if (Map.map_has_special(ptile, S_POLLUTION)) {
//	  time = mv_turns
//	    + get_turns_for_activity_at(punit, ACTIVITY_POLLUTION, ptile);
//	  consider_settler_action(pplayer, ACTIVITY_POLLUTION,
//				  pplayer.ai.warmth,
//				  pcity.ai.detox[i][j], oldv, in_use, time,
//				  &best_newv, &best_oldv,
//				  best_act, best_tile,
//				  ptile);
//	}
//      
//	if (Map.map_has_special(ptile, S_FALLOUT)) {
//	  time = mv_turns
//	    + get_turns_for_activity_at(punit, ACTIVITY_FALLOUT, ptile);
//	  consider_settler_action(pplayer, ACTIVITY_FALLOUT,
//				  pplayer.ai.warmth,
//				  pcity.ai.derad[i][j], oldv, in_use, time,
//				  &best_newv, &best_oldv,
//				  best_act, best_tile,
//				  ptile);
//	}
//
//#ifdef REALLY_DEBUG_THIS
//	util.freelog(Log.LOG_DEBUG,
//		"(%d %d) I=%+-4d O=%+-4d M=%+-4d R=%+-4d RR=%+-4d P=%+-4d N=%+-4d",
//		i, j,
//		pcity.ai.irrigate[i][j], pcity.ai.transform[i][j],
//		pcity.ai.mine[i][j], pcity.ai.road[i][j],
//		pcity.ai.railroad[i][j], pcity.ai.detox[i][j],
//		pcity.ai.derad[i][j]);
//#endif
//      } /* end if we are a legal destination */
//    } city_map_checked_iterate_end;
//  } }
//
//  best_newv = (best_newv - food_upkeep * FOOD_WEIGHTING) * 100 / (40 + food_cost);
//  if (best_newv < 0)
//    best_newv = 0; /* Bad Things happen without this line! :( -- Syela */
//
//  if (best_newv > 0) {
//    util.freelog(Log.LOG_DEBUG,
//	    "Settler %d@(%d,%d) wants to %s at (%d,%d) with desire %d",
//	    punit.id, TILE_XY(punit.tile), get_activity_text(*best_act),
//	    TILE_XY(*best_tile), best_newv);
//  } else {
//    /* Fill in dummy values.  The callers should check if the return value
//     * is > 0 but this will avoid confusing them. */
//    *best_act = unit_activity.ACTIVITY_IDLE;
//    *best_tile = null;
//  }
//
//  return best_newv;
//}
//
///**************************************************************************
//  Find some work for our settlers and/or workers.
//**************************************************************************/
//public static final int LOG_SETTLER = Log.LOG_DEBUG;
//static void auto_settler_findwork(player pplayer, unit punit)
//{
//  struct cityresult result;
//  int best_impr = 0;            /* best terrain improvement we can do */
//  enum unit_activity best_act;
//  tile best_tile = null;
//  ai_data ai = ai_data_get(pplayer);
//
//  CHECK_UNIT(punit);
//
//  result.total = 0;
//  result.result = 0;
//
//  assert(pplayer && punit);
//  assert(unit_flag(punit, Eunit_flag_id.F_CITIES) || unit_flag(punit, Eunit_flag_id.F_SETTLERS));
//
//  /*** If we are on a city mission: Go where we should ***/
//
//  if (punit.ai.ai_role == AIUNIT_BUILD_CITY) {
//    tile ptile = punit.goto_tile;
//    int sanity = punit.id;
//
//    /* Check that the mission is still possible.  If the tile has become
//     * unavailable or the player has been autotoggled, call it off. */
//    if (!punit.unit_owner().ai.control
//	|| !city_can_be_built_here(ptile, punit)) {
//      UNIT_LOG(LOG_SETTLER, punit, "city founding mission failed");
//      ai_unit_new_role(punit, AIUNIT_NONE, null);
//      set_unit_activity(punit, unit_activity.ACTIVITY_IDLE);
//      Unittools.send_unit_info(null, punit);
//      return; /* avoid recursion at all cost */
//    } else {
//      /* Go there */
//      if ((!ai_gothere(pplayer, punit, ptile) && !Game.find_unit_by_id(sanity))
//          || punit.moves_left <= 0) {
//        return;
//      }
//      if (Map.same_pos(punit.tile, ptile)) {
//        if (!ai_do_build_city(pplayer, punit)) {
//          UNIT_LOG(Log.LOG_ERROR, punit, "could not make city on %s",
//                   map_get_tile_info_text(punit.tile));
//          ai_unit_new_role(punit, AIUNIT_NONE, null);
//        } else {
//          return; /* We came, we saw, we built... */
//        }
//      } else {
//        UNIT_LOG(LOG_SETTLER, punit, "could not go to target");
//        /* ai_unit_new_role(punit, AIUNIT_NONE, null); */
//        return;
//      }
//    }
//  }
//
//  CHECK_UNIT(punit);
//
//  /*** Try find some work ***/
//
//  if (unit_flag(punit, Eunit_flag_id.F_SETTLERS)) {
//    best_impr = evaluate_improvements(punit, &best_act, &best_tile);
//  }
//
//  if (unit_flag(punit, Eunit_flag_id.F_CITIES) && pplayer.ai.control) {
//    find_best_city_placement(punit, &result, true, false);
//    UNIT_LOG(LOG_SETTLER, punit, "city want %d (impr want %d)", result.result,
//             best_impr);
//    if (result.result > best_impr) {
//      if (Map.map_get_city(result.tile)) {
//        UNIT_LOG(LOG_SETTLER, punit, "immigrates to %s (%d, %d)", 
//                 Map.map_get_city(result.tile).name, TILE_XY(result.tile));
//      } else {
//        UNIT_LOG(LOG_SETTLER, punit, "makes city at (%d, %d)", 
//                 TILE_XY(result.tile));
//        if (punit.debug) {
//          print_cityresult(pplayer, &result, ai);
//        }
//      }
//      /* Go make a city! */
//      ai_unit_new_role(punit, AIUNIT_BUILD_CITY, result.tile);
//      if (result.other_tile) {
//	/* Reserve best other tile (if there is one). */
//	/* FIXME: what is an "other tile" and why would we want to reserve
//	 * it? */
//	citymap_reserve_tile(result.other_tile, punit.id);
//      }
//      punit.goto_tile = result.tile; /* TMP */
//    } else if (best_impr > 0) {
//      UNIT_LOG(LOG_SETTLER, punit, "improves terrain instead of founding");
//      /* Terrain improvements follows the old model, and is recalculated
//       * each turn. */
//      ai_unit_new_role(punit, AIUNIT_AUTO_SETTLER, best_tile);
//    } else {
//      UNIT_LOG(LOG_SETTLER, punit, "cannot find work");
//      ai_unit_new_role(punit, AIUNIT_NONE, null);
//      return;
//    }
//  } else {
//    /* We are a worker or engineer */
//    ai_unit_new_role(punit, AIUNIT_AUTO_SETTLER, best_tile);
//  }
//
//  /* Run the "autosettler" program */
//  if (punit.ai.ai_role == AIUNIT_AUTO_SETTLER) {
//    /* Mark the square as taken. */
//    if (best_tile) {
//      best_tile.assigned = best_tile.assigned | 1 << pplayer.player_no;
//    } else {
//      UNIT_LOG(Log.LOG_DEBUG, punit, "giving up trying to improve terrain");
//      return; /* We cannot do anything */
//    }
//    punit.goto_tile = best_tile; /* TMP */
//    if (do_unit_goto(punit, goto_move_restriction.GOTO_MOVE_ANY, false) == GR_DIED) {
//      return;
//    }
//    if (punit.moves_left > 0
//        && Map.same_pos(best_tile, punit.tile)) {
//      Unithand.handle_unit_activity_request(punit, best_act);
//      Unittools.send_unit_info(null, punit);
//      return;
//    }
//  }
//
//  /*** Recurse if we want to found a city ***/
//
//  if (punit.ai.ai_role == AIUNIT_BUILD_CITY) {
//    auto_settler_findwork(pplayer, punit);
//  }
//}
//#undef LOG_SETTLER
//
///**************************************************************************
//  Returns city_tile_value of the best tile worked by or available to pcity.
//**************************************************************************/
//static int best_worker_tile_value(city pcity)
//{
//  int best = 0;
//
//	for (int _itr = 0; _itr < City_H.CITY_MAP_SIZE * City_H.CITY_MAP_SIZE; _itr++) {	   
//	int x = _itr % City_H.CITY_MAP_SIZE, y = _itr / City_H.CITY_MAP_SIZE;	   
//
//	if (City.is_valid_city_coords(x, y)) {
//    if (City.is_city_center(x, y) 
//	|| City.get_worker_city(pcity, x, y) == city_tile_type.C_TILE_WORKER 
//	|| City.get_worker_city(pcity, x, y) == city_tile_type.C_TILE_EMPTY) {
//      int tmp = city_tile_value(pcity, x, y, 0, 0);
//
//      if (tmp > best) {
//	best = tmp;
//      }
//    }
//  } };
//  return best;
//}
//
///**************************************************************************
//  Do all tile improvement calculations and cache them for later.
//
//  These values are used in evaluate_improvements() so this function must
//  be called before doing that.  Currently this is only done when handling
//  auto-settlers or when the AI contemplates building worker units.
//**************************************************************************/
//void initialize_infrastructure_cache(player pplayer)
//{
//  for (city pcity : pplayer.cities.data) {
//    int best = best_worker_tile_value(pcity);
//
//	for (int _itr = 0; _itr < City_H.CITY_MAP_SIZE * City_H.CITY_MAP_SIZE; _itr++) {	   
//	int city_x = _itr % City_H.CITY_MAP_SIZE, city_y = _itr / City_H.CITY_MAP_SIZE;	   
//
//	if (City.is_valid_city_coords(city_x, city_y)) {
//      pcity.ai.detox[city_x][city_y] = -1;
//      pcity.ai.derad[city_x][city_y] = -1;
//      pcity.ai.mine[city_x][city_y] = -1;
//      pcity.ai.irrigate[city_x][city_y] = -1;
//      pcity.ai.transform[city_x][city_y] = -1;
//      pcity.ai.road[city_x][city_y] = -1;
//      pcity.ai.railroad[city_x][city_y] = -1;
//    } };
//
//    city_map_checked_iterate(pcity.tile,
//			     city_x, city_y, ptile) {
//#ifndef NDEBUG
//      int old_terrain = ptile.terrain;
//      enum int old_special = ptile.special;
//#endif
//
//      pcity.ai.detox[city_x][city_y]
//	= ai_calc_pollution(pcity, city_x, city_y, best, ptile);
//      pcity.ai.derad[city_x][city_y] =
//	ai_calc_fallout(pcity, pplayer, city_x, city_y, best, ptile);
//      pcity.ai.mine[city_x][city_y]
//	= ai_calc_mine(pcity, city_x, city_y, ptile);
//      pcity.ai.irrigate[city_x][city_y]
//        = ai_calc_irrigate(pcity, pplayer, city_x, city_y, ptile);
//      pcity.ai.transform[city_x][city_y]
//	= ai_calc_transform(pcity, city_x, city_y, ptile);
//
//      /* road_bonus() is handled dynamically later; it takes into
//       * account settlers that have already been assigned to building
//       * roads this turn. */
//      pcity.ai.road[city_x][city_y]
//	= ai_calc_road(pcity, pplayer, city_x, city_y, ptile);
//      pcity.ai.railroad[city_x][city_y] =
//	ai_calc_railroad(pcity, pplayer, city_x, city_y, ptile);
//
//      /* Make sure nothing was accidentally changed by these calculations. */
//      assert(old_terrain == ptile.terrain && old_special == ptile.special);
//    } city_map_checked_iterate_end;
//  } }
//}
//
///************************************************************************** 
//  Run through all the players settlers and let those on ai.control work 
//  automagically.
//**************************************************************************/
//void auto_settlers_player(player pplayer) 
//{
//  static timer t = null;      /* alloc once, never free */
//
//  t = renew_timer_start(t, TIMER_CPU, TIMER_DEBUG);
//
//  if (pplayer.ai.control) {
//    /* Set up our city Map.map. */
//    citymap_turn_init(pplayer);
//  }
//
//  /* Initialize the infrastructure cache, which is used shortly. */
//  initialize_infrastructure_cache(pplayer);
//
//  pplayer.ai.warmth = WARMING_FACTOR * (Game.game.heating > Game.game.warminglevel ? 2 : 1);
//
//  util.freelog(Log.LOG_DEBUG, "Warmth = %d, Game.game.globalwarming=%d",
//	  pplayer.ai.warmth, Game.game.globalwarming);
//
//  /* Auto-settle with a settler unit if it's under AI control (e.g. human
//   * player auto-settler mode) or if the player is an AI.  But don't
//   * auto-settle with a unit under orders even for an AI player - these come
//   * from the human player and take precedence. */
//  for (unit punit : pplayer.units.data) {
//    if ((punit.ai.control || pplayer.ai.control)
//	&& (unit_flag(punit, Eunit_flag_id.F_SETTLERS)
//	    || unit_flag(punit, Eunit_flag_id.F_CITIES))
//	&& !unit_has_orders(punit)) {
//      util.freelog(Log.LOG_DEBUG, "%s's settler at (%d, %d) is ai controlled.",
//	      pplayer.name, TILE_XY(punit.tile)); 
//      if (punit.activity == ACTIVITY_SENTRY) {
//	Unithand.handle_unit_activity_request(punit, unit_activity.ACTIVITY_IDLE);
//      }
//      if (punit.activity == unit_activity.ACTIVITY_GOTO && punit.moves_left > 0) {
//        Unithand.handle_unit_activity_request(punit, unit_activity.ACTIVITY_IDLE);
//      }
//      if (punit.activity == unit_activity.ACTIVITY_IDLE) {
//        auto_settler_findwork(pplayer, punit);
//      }
//    }
//  }
//  }
//  if (timer_in_use(t)) {
//    util.freelog(Log.LOG_VERBOSE, "%s's autosettlers consumed %g milliseconds.",
// 	    pplayer.name, 1000.0*read_timer_seconds(t));
//  }
//}
//
///************************************************************************** 
//  Marks tiles as assigned to a settler. If we are on our way to the tile,
//  it is only assigned with respect to our own calculations, ie other
//  players' autosettlers may race us to the spot. If we are on the spot,
//  the it is marked as assigned for all players.
//**************************************************************************/
//static void assign_settlers_player(player pplayer)
//{
//  int i = 1<<pplayer.player_no;
//  tile ptile;
//  unit_list_iterate(pplayer.units, punit)
//    if (unit_flag(punit, Eunit_flag_id.F_SETTLERS)
//	|| unit_flag(punit, Eunit_flag_id.F_CITIES)) {
//      if (punit.activity == unit_activity.ACTIVITY_GOTO) {
//        ptile = punit.goto_tile;
//        ptile.assigned = ptile.assigned | i; /* assigned for us only */
//      } else {
//        ptile = punit.tile;
//        ptile.assigned = 0xFFFFFFFF; /* assigned for everyone */
//      }
//    } else {
//      ptile = punit.tile;
//      ptile.assigned = ptile.assigned | (0xFFFFFFFF ^ i); /* assigned for everyone else */
//    }
//  }
//}
//
///************************************************************************** 
//  Clear previous turn's assignments, then assign autosettlers to uniquely
//  to tiles. This prevents autosettlers from messing with each others work.
//**************************************************************************/
//static void assign_settlers()
//{
//  for(tile ptile :  Map.map.tiles){
//    ptile.assigned = 0;
//  }
//
//  shuffled_for(player pplayer: Game.game.players){
//    assign_settlers_player(pplayer);
//  } shuffled_players_iterate_end;
//}
//
///************************************************************************** 
//  Assign a region of the map as belonging to a certain player for keeping
//  autosettlers out of enemy territory.
//**************************************************************************/
//static void assign_region(tile ptile, int player_no,
//			  int distance, int s)
//{
//  for(tile tile1: util.square_tile_iterate(ptile, distance)) {
//    if (s == 0 || Terrain.is_terrain_flag_near_tile(tile1)) {
//      BV_SET(TERRITORY(tile1), player_no);
//    }
//  }
//}
//
///**************************************************************************
//  Try to keep autosettlers out of enemy territory. We assign blocks of
//  territory to the enemy based on the location of his units and their
//  movement.
//
//  FIXME: We totally ignore the possibility of enemies getting to us
//  by road or rail. Whatever Syela says, this is just so broken.
//
//  NOTE: Having units with extremely high movement in the Game.game will
//  effectively make autosettlers run and hide and never come out again. 
//  The cowards.
//**************************************************************************/
//static void assign_territory_player(player pplayer)
//{
//  int n = pplayer.player_no;
//  unit_list_iterate(pplayer.units, punit)
//    if (punit.unit_type().attack_strength != 0) {
///* I could argue that phalanxes aren't really a threat, but ... */
//      if (Unit.is_sailing_unit(punit)) {
//        assign_region(punit.tile, n, 1 + punit.unit_type().move_rate / Unit_H.SINGLE_MOVE, 1);
//      } else if (is_ground_unit(punit)) {
//        assign_region(punit.tile, n, 1 + punit.unit_type().move_rate /
//             (unit_flag(punit, F_IGTER) ? 1 : 3), 0);
///* I realize this is not the most accurate, but I don't want to iterate
//road networks 100 times/turn, and I can't justifiably abort when I encounter
//already assigned territory.  If anyone has a reasonable alternative that won't
//noticeably slow the Game.game, feel free to replace this else{}  -- Syela */
//      } else {
//        assign_region(punit.tile, n, 1 + punit.unit_type().move_rate / Unit_H.SINGLE_MOVE, 0);
//      } 
//    }
//  }
//  for(city pcity : pplayer.cities.data){//    assign_region(pcity.tile, n, 3, 0);
//  }
//}
//
///**************************************************************************
//  This function is supposed to keep settlers out of enemy territory
//   -- Syela
//**************************************************************************/
//static void assign_territory()
//{
//  memset(territory, 0, Map.map.xsize * Map.map.ysize * sizeof(*territory));
//
//  for(player pplayer: Game.game.players){
//    assign_territory_player(pplayer);
//  }
//  /* An actual territorial assessment a la AI algorithms for go might be
//   * appropriate here.  I'm not sure it's necessary, so it's not here yet.
//   *  -- Syela
//   */
//}  
//
///**************************************************************************
//  Recalculate enemies[] table
//**************************************************************************/
//static void recount_enemy_masks()
//{
//  for(player player1: Game.game.players){
//    BV_CLR_ALL(enemies[player1.player_no]);
//    for(player player2: Game.game.players){
//      if (!Player_P.pplayers_allied(player1, player2))
//        BV_SET(enemies[player1.player_no], player2.player_no);
//    }
//  }
//}
//
///**************************************************************************
//  Initialize autosettler code.
//**************************************************************************/
//void auto_settlers_init()
//{
//  assign_settlers();
//  assign_territory();
//  recount_enemy_masks();
//}
//
///**************************************************************************
//  Return want for city settler. Note that we rely here on the fact that
//  ai_settler_init() has been run while doing autosettlers.
//**************************************************************************/
//void contemplate_new_city(city pcity)
//{
//  player pplayer = City.city_owner(pcity);
//  unit virtualunit;
//  int unit_type = best_role_unit(pcity, Eunit_flag_id.F_CITIES); 
//
//  if (unit_type == unittype.U_LAST) {
//    util.freelog(Log.LOG_DEBUG, "No Eunit_flag_id.F_CITIES role unit available");
//    return;
//  }
//
//  /* Create a localized "virtual" unit to do operations with. */
//  virtualunit = create_unit_virtual(pplayer, pcity, unit_type, 0);
//  virtualunit.tile = pcity.tile;
//
//  assert(pplayer.ai.control);
//
//  if (pplayer.ai.control) {
//    struct cityresult result;
//    boolean is_coastal = Terrain.is_terrain_flag_near_tile(pcity.tile);
//
//    find_best_city_placement(virtualunit, &result, is_coastal, is_coastal);
//
//    CITY_LOG(Log.LOG_DEBUG, pcity, "want(%d) to establish city at" +
//	     " (%d, %d) and will %s to get there", result.result, 
//	     TILE_XY(result.tile), 
//	     (result.virt_boat ? "build a boat" : 
//	      (result.overseas ? "use a boat" : "walk")));
//
//    pcity.ai.founder_want = (result.virt_boat ? 
//			      -result.result : result.result);
//    pcity.ai.founder_boat = result.overseas;
//  }
//  free(virtualunit);
//}
//
///**************************************************************************
//  Estimates the want for a terrain improver (aka worker) by creating a 
//  virtual unit and feeding it to evaluate_improvements.
//
//  TODO: AI does not ship Eunit_flag_id.F_SETTLERS around, only Eunit_flag_id.F_CITIES - Per
//**************************************************************************/
//void contemplate_terrain_improvements(city pcity)
//{
//  player pplayer = City.city_owner(pcity);
//  unit virtualunit;
//  int want;
//  tile best_tile = null; /* May be accessed by util.freelog() calls. */
//  enum unit_activity best_act;
//  tile ptile = pcity.tile;
//  ai_data ai = ai_data_get(pplayer);
//  int unit_type = best_role_unit(pcity, Eunit_flag_id.F_SETTLERS);
//
//  if (unit_type == unittype.U_LAST) {
//    util.freelog(Log.LOG_DEBUG, "No Eunit_flag_id.F_SETTLERS role unit available");
//    return;
//  }
//
//  /* Create a localized "virtual" unit to do operations with. */
//  virtualunit = create_unit_virtual(pplayer, pcity, unit_type, 0);
//  virtualunit.tile = pcity.tile;
//  want = evaluate_improvements(virtualunit, &best_act, &best_tile);
//  free(virtualunit);
//
//  /* Massage our desire based on available statistics to prevent
//   * overflooding with worker type units if they come cheap in
//   * the ruleset */
//  want /= MAX(1, ai.stats.workers[ptile.continent]
//                 / (ai.stats.cities[ptile.continent] + 1));
//  want -= Math.min(ai.stats.workers[ptile.continent], want);
//
//  CITY_LOG(Log.LOG_DEBUG, pcity, "wants %s with want %d to do %s at (%d,%d), " +
//           "we have %d workers and %d cities on the continent",
//	   Unittype_P.unit_name(unit_type), want, get_activity_text(best_act),
//	   TILE_XY(best_tile),
//           ai.stats.workers[ptile.continent], 
//           ai.stats.cities[ptile.continent]);
//  assert(want >= 0);
//  pcity.ai.settler_want = want;
//}
}