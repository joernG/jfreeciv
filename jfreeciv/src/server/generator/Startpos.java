package server.generator;

public class Startpos{

// Freeciv - Copyright (C) 1996 - 2004 The Freeciv Project Team 
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
//#ifdef HAVE_CONFIG_H
//#include <config.h>
//#endif
//
//#include "log.h"
//#include "fcintl.h"
//
//#include "Map.map.h"
//
//#include "maphand.h"
//
//#include "mapgen_topology.h"
//#include "startpos.h"
//#include "temperature_map.h"
//#include "utilities.h"
//
//struct islands_data_type {
//  Continent_id id;
//  int size;
//  int gooutil.dies;
//  int starters;
//  int total;
//};
//static islands_data_type islands;
//static int *islands_index;
//
///****************************************************************************
//  Return an approximation of the goodness of a tile to a civilization.
//****************************************************************************/
//static int get_tile_value(tile ptile)
//{
//  int old_terrain;
//  enum int old_special;
//  int value, irrig_bonus, mine_bonus;
//
//  /* Give one point for each food / shield / trade produced. */
//  value = (get_food_tile(ptile)
//	   + get_shields_tile(ptile)
//	   + get_trade_tile(ptile));
//
//  old_terrain = ptile.terrain;
//  old_special = ptile.special;
//
//  Map.map_set_special(ptile, Terrain_H.S_ROAD);
//  map_irrigate_tile(ptile);
//  irrig_bonus = (get_food_tile(ptile)
//		 + get_shields_tile(ptile)
//		 + get_trade_tile(ptile)) - value;
//
//  ptile.terrain = old_terrain;
//  ptile.special = old_special;
//  Map.map_set_special(ptile, Terrain_H.S_ROAD);
//  map_mine_tile(ptile);
//  mine_bonus = (get_food_tile(ptile)
//		+ get_shields_tile(ptile)
//		+ get_trade_tile(ptile)) - value;
//
//  ptile.terrain = old_terrain;
//  ptile.special = old_special;
//
//  value += MAX(0, MAX(mine_bonus, irrig_bonus)) / 2;
//
//  return value;
//}
//
//struct start_filter_data {
//  int count;			/* Number of existing start positions. */
//  int min_value;
//  int *value;
//};
//
///**************************************************************************
//  Return true if (x,y) is a good starting position.
//
//  Bad places:
//  - Islands with no room.
//  - Non-suitable terrain;
//  - On a hut;
//  - Too close to another starter on the same continent:
//    'dist' is too close (Map.real_map_distance)
//    'nr' is the number of other start positions in
//    Map.map.start_positions to check for too closeness.
//**************************************************************************/
//static boolean is_valid_start_pos(final tile ptile, final void *dataptr)
//{
//  final start_filter_data pdata = dataptr;
//  int i;
//  islands_data_type island;
//  int cont_size, cont = map_get_continent(ptile);
//
//  /* Only start on certain terrain types. */  
//  if (pdata.value[ptile.index] < pdata.min_value) {
//      return false;
//  } 
//
//  assert(cont > 0);
//  if (islands[islands_index[cont]].starters == 0) {
//    return false;
//  }
//
//  /* Don't start on a hut. */
//  if (Map.map_has_special(ptile, Terrain_H.S_HUT)) {
//    return false;
//  }
//
//  /* A longstanding bug allowed starting positions to exist on poles,
//   * sometimes.  This hack prevents it by setting a fixed distance from
//   * the pole (dependent on map temperature) that a start pos must be.
//   * Cold and frozen tiles are not allowed for start pos placement. */
//  if (tmap_is(ptile, TT_NHOT)) {
//    return false;
//  }
//
//  /* Don't start too close to someone else. */
//  cont_size = get_continent_size(cont);
//  island = islands + islands_index[cont];
//  for (i = 0; i < pdata.count; i++) {
//    tile tile1 = Map.map.start_positions[i].tile;
//
//    if ((map_get_continent(ptile) == map_get_continent(tile1)
//	 && (Map.real_map_distance(ptile, tile1) * 1000 / pdata.min_value
//	     <= (sqrt(cont_size / island.total))))
//	|| (Map.real_map_distance(ptile, tile1) * 1000 / pdata.min_value < 5)) {
//      return false;
//    }
//  }
//  return true;
//}
//
///*************************************************************************
// help function for qsort
// *************************************************************************/
//static int compare_islands(final void *A_, final void *B_)
//{
//  final islands_data_type A = A_, *B = B_;
//
//  return B.gooutil.dies - A.gooutil.dies;
//}
//
///****************************************************************************
//  Initialize islands data.
//****************************************************************************/
//static void initialize_isle_data()
//{
//  int nr;
//
//  islands = fc_malloc((Map.map.num_continents + 1) * sizeof(*islands));
//  islands_index = fc_malloc((Map.map.num_continents + 1)
//			    * sizeof(*islands_index));
//
//  /* islands[0] is unused. */
//  for (nr = 1; nr <= Map.map.num_continents; nr++) {
//    islands[nr].id = nr;
//    islands[nr].size = get_continent_size(nr);
//    islands[nr].gooutil.dies = 0;
//    islands[nr].starters = 0;
//    islands[nr].total = 0;
//  }
//}
//
///****************************************************************************
//  A function that filters for TER_STARTER tiles.
//****************************************************************************/
//static boolean filter_starters(final tile ptile, final void *data)
//{
//  return Terrain_H.terrain_has_flag(ptile.terrain, TER_STARTER);
//}
//
///**************************************************************************
//  where do the different nations start on the map? well this function tries
//  to spread them out on the different islands.
//
//  MT_SIGLE: one player per isle
//  MT_2or3: 2 players per isle (maybe one isle with 3)
//  MT_ALL: all players in asingle isle
//  MT_VARIABLE: at least 2 player per isle
//  
//  Returns true on success
//**************************************************************************/
//boolean create_start_positions(enum start_mode mode)
//{
//  tile ptile;
//  int k, sum;
//  struct start_filter_data data;
//  int tile_value_aux[Map_H.MAX_MAP_INDEX], tile_value[Map_H.MAX_MAP_INDEX];
//  int min_gooutil.dies_per_player = 2000;
//  int total_gooutil.dies = 0;
//  /* this is factor is used to maximize land used in extreme little maps */
//  float efactor =  Game.game.nplayers / Map.map.size / 4; 
//  boolean failure = false;
//  boolean is_tmap = temperature_is_initialized();
//
//  if (!is_tmap) {
//    /* The temperature map has already been destroyed by the time start
//     * positions have been placed.  We check for this and then create a
//     * false temperature Map.map. This is used in the tmap_is() call above.
//     * We don't create a "real" map here because that requires the height
//     * map and other information which has already been destroyed. */
//    create_tmap(false);
//  }
//
//  /* Unsafe terrains separate continents, otherwise small areas of green
//   * near the poles could be populated by a civilization if that pole
//   * was part of a larger continent. */
//  assign_continent_numbers(true);
//
//  /* If the default is given, just use MT_VARIABLE. */
//  if (mode == MT_DEFAULT) {
//    mode = MT_VARIABLE;
//  }
//
//  /* get the tile value */
//  for(tile ptile :  Map.map.tiles){
//    tile_value_aux[ptile.index] = get_tile_value(ptile);
//  }
//
//  /* select the best tiles */
//  for(tile ptile :  Map.map.tiles){
//    int this_tile_value = tile_value_aux[ptile.index];
//    int lcount = 0, bcount = 0;
//
//    map_city_radius_iterate(ptile, ptile1) {
//      if (this_tile_value > tile_value_aux[ptile1.index]) {
//	lcount++;
//      } else if (this_tile_value < tile_value_aux[ptile1.index]) {
//	bcount++;
//      }
//    } map_city_radius_iterate_end;
//    if (lcount <= bcount) {
//      this_tile_value = 0;
//    }
//    tile_value[ptile.index] = 100 * this_tile_value;
//  }
//  /* get an average value */
//  smooth_int_map(tile_value, true);
//
//  initialize_isle_data();
//
//  /* oceans are not good for starters; discard them */
//  for(tile ptile :  Map.map.tiles){
//    if (!filter_starters(ptile, null)) {
//      tile_value[ptile.index] = 0;
//    } else {
//      islands[map_get_continent(ptile)].gooutil.dies += tile_value[ptile.index];
//      total_gooutil.dies += tile_value[ptile.index];
//    }
//  }
//
//  /* evaluate the best places on the map */
//  adjust_int_map_filtered(tile_value, 1000, null, filter_starters);
// 
//  /* Sort the islands so the best ones come first.  Note that islands[0] is
//   * unused so we just skip it. */
//  qsort(islands + 1, Map.map.num_continents,
//	sizeof(*islands), compare_islands);
//
//  /* If we can't place starters according to the first choice, change the
//   * choice. */
//  if (mode == MT_SINGLE && Map.map.num_continents < Game.game.nplayers + 3) {
//    mode = MT_2or3;
//  }
//
//  if (mode == MT_2or3 && Map.map.num_continents < Game.game.nplayers / 2 + 4) {
//    mode = MT_VARIABLE;
//  }
//
//  if (mode == MT_ALL 
//      && (islands[1].gooutil.dies < Game.game.nplayers * min_gooutil.dies_per_player
//	  || islands[1].gooutil.dies < total_gooutil.dies * (0.5 + 0.8 * efactor)
//	  / (1 + efactor))) {
//    mode = MT_VARIABLE;
//  }
//
//  /* the variable way is the last posibility */
//  if (mode == MT_VARIABLE) {
//    min_gooutil.dies_per_player = total_gooutil.dies * (0.65 + 0.8 * efactor) 
//      / (1 + efactor)  / Game.game.nplayers;
//  }
//
//  { 
//    int nr, to_place = Game.game.nplayers, first = 1;
//
//    /* inizialize islands_index */
//    for (nr = 1; nr <= Map.map.num_continents; nr++) {
//      islands_index[islands[nr].id] = nr;
//    }
//
//    /* searh for best first island for fairness */    
//    if ((mode == MT_SINGLE) || (mode == MT_2or3)) {
//      float var_gooutil.dies, best = HUGE_VAL;
//      int num_islands
//	= (mode == MT_SINGLE) ? Game.game.nplayers : (Game.game.nplayers / 2);
//
//      for (nr = 1; nr <= 1 + Map.map.num_continents - num_islands; nr++) {
//	if (islands[nr + num_islands - 1].gooutil.dies < min_gooutil.dies_per_player) {
//	  break;
//	}
//	var_gooutil.dies
//	    = (islands[nr].gooutil.dies - islands[nr + num_islands - 1].gooutil.dies)
//	    / (islands[nr + num_islands - 1].gooutil.dies);
//
//	if (var_gooutil.dies < best * 0.9) {
//	  best = var_gooutil.dies;
//	  first = nr;
//	}
//      }
//    }
//
//    /* set starters per isle */
//    if (mode == MT_ALL) {
//      islands[1].starters = to_place;
//      islands[1].total = to_place;
//      to_place = 0;
//    }
//    for (nr = 1; nr <= Map.map.num_continents; nr++) {
//      if (mode == MT_SINGLE && to_place > 0 && nr >= first) {
//	islands[nr].starters = 1;
//	islands[nr].total = 1;
//	to_place--;
//      }
//      if (mode == MT_2or3 && to_place > 0 && nr >= first) {
//	islands[nr].starters = 2 + (nr == 1 ? (Game.game.nplayers % 2) : 0);
//	to_place -= islands[nr].total = islands[nr].starters;
//      }
//
//      if (mode == MT_VARIABLE && to_place > 0) {
//	islands[nr].starters = MAX(1, islands[nr].gooutil.dies 
//				   / min_gooutil.dies_per_player);
//	to_place -= islands[nr].total = islands[nr].starters;
//      }
//    }
//  }
//
//  data.count = 0;
//  data.value = tile_value;
//  data.min_value = 900;
//  sum = 0;
//  for (k = 1; k <= Map.map.num_continents; k++) {
//    sum += islands[islands_index[k]].starters;
//    if (islands[islands_index[k]].starters != 0) {
//      util.freelog(Log.LOG_VERBOSE, "starters on isle %i", k);
//    }
//  }
//  assert(Game.game.nplayers <= data.count + sum);
//
//  /* now search for the best place and set start_positions */
//  Map.map.start_positions = fc_realloc(Map.map.start_positions,
//				   Game.game.nplayers
//				   * sizeof(*Map.map.start_positions));
//  while (data.count < Game.game.nplayers) {
//    if ((ptile = rand_map_pos_filtered(&data, is_valid_start_pos))) {
//      islands[islands_index[(int) map_get_continent(ptile)]].starters--;
//      Map.map.start_positions[data.count].tile = ptile;
//      Map.map.start_positions[data.count].nation = Nation_H.NO_NATION_SELECTED;
//      util.freelog(Log.LOG_DEBUG,
//	      "Adding %d,%d as starting position %d, %d gooutil.dies on islands.",
//	      TILE_XY(ptile), data.count,
//	      islands[islands_index[(int) map_get_continent(ptile)]].gooutil.dies);
//      data.count++;
//
//    } else {
//      data.min_value *= 0.9;
//      if (data.min_value <= 10) {
//	util.freelog(Log.LOG_ERROR,
//	        ("The server appears to have gotten into an infinite loop " +
//	          "in the allocation of starting positions.\n" +
//	          "Maybe the numbers of players/ia is too much for this Map.map.\n" +
//	          "Please report this bug at %s."), WEBSITE_URL);
//	failure = true;
//	break;
//      }
//    }
//  }
//  Map.map.num_start_positions = Game.game.nplayers;
//
//  free(islands);
//  free(islands_index);
//  islands = null;
//  islands_index = null;
//
//  if (!is_tmap) {
//    destroy_tmap();
//  }
//
//  return !failure;
//}
}