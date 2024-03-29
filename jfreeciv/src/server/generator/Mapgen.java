package server.generator;

public class Mapgen{

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
//#include <stdlib.h>
//#include <string.h>
//#include <time.h>
//
//#include "fcintl.h"
//#include "game.h"
//#include "log.h"
//#include "map.h"
//#include "maphand.h" /* assign_continent_numbers(), MAP_NCONT */
//#include "mem.h"
//#include "rand.h"
//#include "shared.h"
//#include "srv_main.h"
//
//#include "height_map.h"
//#include "mapgen.h"
//#include "mapgen_topology.h"
//#include "startpos.h"
//#include "temperature_map.h"
//#include "utilities.h"
//
//
///* Old-style terrain enumeration: deprecated. */
//enum {
//  T_ARCTIC, T_DESERT, T_FOREST, T_GRASSLAND, T_HILLS, T_JUNGLE,
//  T_MOUNTAINS, T_OCEAN, T_PLAINS, T_SWAMP, T_TUNDRA,
//};
//
///* Wrappers for easy access.  They are a macros so they can be a lvalues.*/
//#define rmap(ptile) (river_map[ptile.index])
//
//static void make_huts(int number);
//static void add_specials(int prob);
//static void mapgenerator2();
//static void mapgenerator3();
//static void mapgenerator4();
//static void adjust_terrain_param();
//
//public static final int RIVERS_MAXTRIES = 32767;
//enum river_map_type {RS_BLOCKED = 0, RTerrain_H.S_RIVER = 1};
//
///* Array needed to mark tiles as blocked to prevent a river from
//   falling into itself, and for storing rivers temporarly.
//   A value of 1 means blocked.
//   A value of 2 means river.                            -Erik Sigra */
//static int *river_map;
//
//#define HAS_POLES (Map.map.temperature < 70 && !Map.map.alltemperate  )
//
///* These are the old parameters of terrains types in %
//   TODO: they depend on the hardcoded terrains */
//static int forest_pct = 0;
//static int desert_pct = 0;
//static int swamp_pct = 0;
//static int mountain_pct = 0;
//static int jungle_pct = 0;
//static int river_pct = 0;
// 
///****************************************************************************
// * Conditions used mainly in rand_map_pos_characteristic()
// ****************************************************************************/
///* WETNESS */
//
///* necessary condition of deserts placement */
//#define map_pos_is_dry(ptile)						\
//  (map_colatitude((ptile)) <= DRY_MAX_LEVEL				\
//   && map_colatitude((ptile)) > DRY_MIN_LEVEL				\
//   && count_ocean_near_tile((ptile), false, true) <= 35)
//typedef enum { WC_ALL = 200, WC_DRY, WC_NDRY } wetness_c;
//
///* MISCELANEOUS (OTHER CONDITIONS) */
//
///* necessary condition of swamp placement */
//static int hmap_low_level = 0;
//#define ini_hmap_low_level() \
//{ \
//hmap_low_level = (4 * swamp_pct  * \
//     (hmap_max_level - hmap_shore_level)) / 100 + hmap_shore_level; \
//}
///* should be used after having hmap_low_level initialized */
//#define map_pos_is_low(ptile) ((hmap((ptile)) < hmap_low_level))
//
//typedef enum { MC_NONE, MC_LOW, MC_NLOW } miscellaneous_c;
//
///***************************************************************************
// These functions test for conditions used in rand_map_pos_characteristic 
//***************************************************************************/
//
///***************************************************************************
//  Checks if the given location satisfy some wetness condition
//***************************************************************************/
//static boolean test_wetness(final tile ptile, wetness_c c)
//{
//  switch(c) {
//  case WC_ALL:
//    return true;
//  case WC_DRY:
//    return map_pos_is_dry(ptile);
//  case WC_NDRY:
//    return !map_pos_is_dry(ptile);
//  }
//  assert(0!=1);
//  return false;
//}
//
///***************************************************************************
//  Checks if the given location satisfy some miscellaneous condition
//***************************************************************************/
//static boolean test_miscellaneous(final tile ptile, miscellaneous_c c)
//{
//  switch(c) {
//  case MC_NONE:
//    return true;
//  case MC_LOW:
//    return map_pos_is_low(ptile);
//  case MC_NLOW:
//    return !map_pos_is_low(ptile);
//  }
//  assert(0!=1);
//  return false;
//}
//
///***************************************************************************
//  Passed as data to rand_map_pos_filtered() by rand_map_pos_characteristic()
//***************************************************************************/
//struct DataFilter {
//  wetness_c wc;
//  temperature_type tc;
//  miscellaneous_c mc;
//};
//
///****************************************************************************
//  A filter function to be passed to rand_map_pos_filtered().  See
//  rand_map_pos_characteristic for more explanation.
//****************************************************************************/
//static boolean condition_filter(final tile ptile, final void *data)
//{
//  final DataFilter filter = data;
//
//  return  not_placed(ptile) 
//       && tmap_is(ptile, filter.tc) 
//       && test_wetness(ptile, filter.wc) 
//       && test_miscellaneous(ptile, filter.mc) ;
//}
//
///****************************************************************************
//  Return random map coordinates which have some conditions and which are
//  not yet placed on pmap.
//  Returns false if there is no such position.
//****************************************************************************/
//static tile rand_map_pos_characteristic(wetness_c wc,
//						temperature_type tc,
//						miscellaneous_c mc )
//{
//  struct DataFilter filter;
//
//  filter.wc = wc;
//  filter.tc = tc;
//  filter.mc = mc;
//  return rand_map_pos_filtered(&filter, condition_filter);
//}
//
///**************************************************************************
//  we don't want huge areas of grass/plains, 
//  so we put in a hill here and there, where it gets too 'clean' 
//
//  Return true if the terrain at the given map position is "clean".  This
//  means that all the terrain for 2 squares around it is not mountain or hill.
//****************************************************************************/
//static boolean terrain_is_too_flat(tile ptile, 
//				int thill, int my_height)
//{
//  int higher_than_me = 0;
//  for(tile tile1: util.square_tile_iterate(ptile, 2)) {
//    if (hmap(tile1) > thill) {
//      return false;
//    }
//    if (hmap(tile1) > my_height) {
//      if (map_distance(ptile, tile1) == 1) {
//	return false;
//      }
//      if (++higher_than_me > 2) {
//	return false;
//      }
//    }
//  }
//
//  if ((thill - hmap_shore_level) * higher_than_me  > 
//      (my_height - hmap_shore_level) * 4) {
//    return false;
//  }
//  return true;
//}
//
///**************************************************************************
//  we don't want huge areas of hill/mountains, 
//  so we put in a plains here and there, where it gets too 'heigh' 
//
//  Return true if the terrain at the given map position is too heigh.
//****************************************************************************/
//static boolean terrain_is_too_high(tile ptile,
//				int thill, int my_height)
//{
//  for(tile tile1: util.square_tile_iterate(ptile, 1)) {
//    if (hmap(tile1) + (hmap_max_level - hmap_mountain_level) / 5 < thill) {
//      return false;
//    }
//  }
//  return true;
//}
//
///**************************************************************************
//  make_relief() will convert all squares that are higher than thill to
//  mountains and hills. Note that thill will be adjusted according to
//  the Map.map.steepness value, so increasing Map.map.mountains will result in
//  more hills and mountains.
//**************************************************************************/
//static void make_relief()
//{
//  /* Calculate the mountain level.  Map.map.mountains specifies the percentage
//   * of land that is turned into hills and mountains. */
//  hmap_mountain_level = ((hmap_max_level - hmap_shore_level)
//			 * (100 - Map.map.steepness)) / 100 + hmap_shore_level;
//
//  for(tile ptile :  Map.map.tiles){
//    if (not_placed(ptile) &&
//	((hmap_mountain_level < hmap(ptile) && 
//	  (Rand.myrand(10) > 5 
//	   || !terrain_is_too_high(ptile, hmap_mountain_level, hmap(ptile))))
//	 || terrain_is_too_flat(ptile, hmap_mountain_level, hmap(ptile)))) {
//      /* Randomly place hills and mountains on "high" tiles.  But don't
//       * put hills near the poles (they're too green). */
//      if (Rand.myrand(100) > 70 || tmap_is(ptile, TT_NHOT)) {
//	map_set_terrain(ptile, T_MOUNTAINS);
//	map_set_placed(ptile);
//      } else {
//	map_set_terrain(ptile, T_HILLS);
//	map_set_placed(ptile);
//      }
//    }
//  }
//}
//
///****************************************************************************
//  Add arctic and tundra squares in the arctic zone (that is, the coolest
//  10% of the map).  We also texture the pole (adding arctic, tundra, and
//  mountains).  This is used in generators 2-4.
//****************************************************************************/
//static void make_polar()
//{
//  for(tile ptile :  Map.map.tiles){  
//    if (tmap_is(ptile, TT_FROZEN)
//	|| (tmap_is(ptile, TT_COLD)
//	    && (Rand.myrand(10) > 7)
//	    && is_temperature_type_near(ptile, TT_FROZEN))) { 
//      map_set_terrain(ptile, T_ARCTIC);
//    }
//  }
//}
//
///*************************************************************************
// if separatepoles is set, return false if this tile has to keep ocean
//************************************************************************/
//static boolean ok_for_separate_poles(tile ptile)
//{
//  if (!Map.map.separatepoles) {
//    return true;
//  }
//  for(tile tile1: util.adjc_tile_iterate(ptile)) {
//    if (!Terrain_H.is_ocean(tile1.terrain) && 
//        map_get_continent(tile1) != 0) {
//      return false;
//    }
//  }
//  return true;
//}
//
///****************************************************************************
//  Place untextured land at the poles.  This is used by generators 1 and 5.
//****************************************************************************/
//static void make_polar_land()
//{
//  assign_continent_numbers(false);
//  for(tile ptile :  Map.map.tiles){
//    if ((tmap_is(ptile, TT_FROZEN ) &&
//	ok_for_separate_poles(ptile))
//	||
//	(tmap_is(ptile, TT_COLD ) &&
//	 (Rand.myrand(10) > 7) &&
//	 is_temperature_type_near(ptile, TT_FROZEN) &&
//	 ok_for_separate_poles(ptile))) {
//      map_set_terrain(ptile, T_UNKNOWN);
//      map_set_continent(ptile, 0);
//    } 
//  }
//}
//
///**************************************************************************
//  Recursively generate terrains.
//**************************************************************************/
//static void place_terrain(tile ptile, int diff, 
//                           int ter, int *to_be_placed,
//			   wetness_c        wc,
//			   temperature_type tc,
//			   miscellaneous_c  mc)
//{
//  if (*to_be_placed <= 0) {
//    return;
//  }
//  assert(not_placed(ptile));
//  map_set_terrain(ptile, ter);
//  map_set_placed(ptile);
//  (*to_be_placed)--;
//  
//  cardinal_for(tile tile1: util.adjc_tile_iterate(ptile)) {
//    int Delta = abs(map_colatitude(tile1) - map_colatitude(ptile)) / L_UNIT
//	+ abs(hmap(tile1) - (hmap(ptile))) /  H_UNIT;
//    if (not_placed(tile1) 
//	&& tmap_is(tile1, tc) 
//	&& test_wetness(tile1, wc)
// 	&& test_miscellaneous(tile1, mc)
//	&& Delta < diff 
//	&& Rand.myrand(10) > 4) {
//	place_terrain(tile1, diff - 1 - Delta, ter, to_be_placed, wc, tc, mc);
//    }
//  } cardinal_adjc_iterate_end;
//}
//
///**************************************************************************
//  a simple function that adds plains grassland or tundra to the 
//  current location.
//**************************************************************************/
//static void make_plain(tile ptile, int *to_be_placed )
//{
//  /* in cold place we get tundra instead */
//  if (tmap_is(ptile, TT_FROZEN)) {
//    map_set_terrain(ptile, T_ARCTIC); 
//  } else if (tmap_is(ptile, TT_COLD)) {
//    map_set_terrain(ptile, T_TUNDRA); 
//  } else {
//    if (Rand.myrand(100) > 50) {
//      map_set_terrain(ptile, T_GRASSLAND);
//    } else {
//      map_set_terrain(ptile, T_PLAINS);
//    }
//  }
//  map_set_placed(ptile);
//  (*to_be_placed)--;
//}
//
///**************************************************************************
//  make_plains converts all not yet placed terrains to plains (tundra, grass) 
//  used by generators 2-4
//**************************************************************************/
//static void make_plains()
//{
//  int to_be_placed;
//  for(tile ptile :  Map.map.tiles){
//    if (not_placed(ptile)) {
//      to_be_placed = 1;
//      make_plain(ptile, &to_be_placed);
//    }
//  }
//}
///**************************************************************************
// This place randomly a cluster of terrains with some characteristics
// **************************************************************************/
//#define PLACE_ONE_TYPE(count, alternate, ter, wc, tc, mc, weight) \
//  if((count) > 0) {                                       \
//    tile ptile;					  \
//    /* Place some terrains */                             \
//    if ((ptile = rand_map_pos_characteristic((wc), (tc), (mc)))) {	\
//      place_terrain(ptile, (weight), (ter), &(count), (wc),(tc), (mc));   \
//    } else {                                                             \
//      /* If rand_map_pos_temperature returns false we may as well stop*/ \
//      /* looking for this time and go to alternate type. */              \
//      (alternate) += (count); \
//      (count) = 0;            \
//    }                         \
//  }
//
///**************************************************************************
//  make_terrains calls make_forest, make_dessert,etc  with random free 
//  locations until there  has been made enough.
// Comment: funtions as make_swamp, etc. has to have a non 0 probability
// to place one terrains in called position. Else make_terrains will get
// in a infinite loop!
//**************************************************************************/
//static void make_terrains()
//{
//  int total = 0;
//  int forests_count = 0;
//  int jungles_count = 0;
//  int deserts_count = 0;
//  int alt_deserts_count = 0;
//  int plains_count = 0;
//  int swamps_count = 0;
//
//  for(tile ptile :  Map.map.tiles){
//    if (not_placed(ptile)) {
//     total++;
//    }
//  }
//
//  forests_count = total * forest_pct / (100 - mountain_pct);
//  jungles_count = total * jungle_pct / (100 - mountain_pct);
// 
//  deserts_count = total * desert_pct / (100 - mountain_pct); 
//  swamps_count = total * swamp_pct  / (100 - mountain_pct);
//
//  /* grassland, tundra,arctic and plains is counted in plains_count */
//  plains_count = total - forests_count - deserts_count
//      - swamps_count - jungles_count;
//
//  /* the placement loop */
//  do {
//    
//    PLACE_ONE_TYPE(forests_count , plains_count, T_FOREST,
//		   WC_ALL, TT_NFROZEN, MC_NONE, 60);
//    PLACE_ONE_TYPE(jungles_count, forests_count , T_JUNGLE,
//		   WC_ALL, TT_TROPICAL, MC_NONE, 50);
//    PLACE_ONE_TYPE(swamps_count, forests_count , T_SWAMP,
//		   WC_NDRY, TT_HOT, MC_LOW, 50);
//    PLACE_ONE_TYPE(deserts_count, alt_deserts_count , T_DESERT,
//		   WC_DRY, TT_NFROZEN, MC_NLOW, 80);
//    PLACE_ONE_TYPE(alt_deserts_count, plains_count , T_DESERT,
//		   WC_ALL, TT_NFROZEN, MC_NLOW, 40);
// 
//  /* make the plains and tundras */
//    if (plains_count > 0) {
//      tile ptile;
//
//      /* Don't use any restriction here ! */
//      if ((ptile = rand_map_pos_characteristic(WC_ALL, TT_ALL, MC_NONE))) {
//	make_plain(ptile, &plains_count);
//      } else {
//	/* If rand_map_pos_temperature returns false we may as well stop
//	 * looking for plains. */
//	plains_count = 0;
//      }
//    }
//  } while (forests_count > 0 || jungles_count > 0 
//	   || deserts_count > 0 || alt_deserts_count > 0 
//	   || plains_count > 0 || swamps_count > 0 );
//}
//
///*********************************************************************
// Help function used in make_river(). See the help there.
//*********************************************************************/
//static int river_test_blocked(tile ptile)
//{
//  if (TEST_BIT(rmap(ptile), RS_BLOCKED))
//    return 1;
//
//  /* any un-blocked? */
//  cardinal_for(tile tile1: util.adjc_tile_iterate(ptile)) {
//    if (!TEST_BIT(rmap(tile1), RS_BLOCKED))
//      return 0;
//  } cardinal_adjc_iterate_end;
//
//  return 1; /* none non-blocked |- all blocked */
//}
//
///*********************************************************************
// Help function used in make_river(). See the help there.
//*********************************************************************/
//static int river_test_rivergrid(tile ptile)
//{
//  return (count_special_near_tile(ptile, true, false, Terrain_H.S_RIVER) > 1) ? 1 : 0;
//}
//
///*********************************************************************
// Help function used in make_river(). See the help there.
//*********************************************************************/
//static int river_test_highlands(tile ptile)
//{
//  return (((ptile.terrain == T_HILLS) ? 1 : 0) +
//	  ((ptile.terrain == T_MOUNTAINS) ? 2 : 0));
//}
//
///*********************************************************************
// Help function used in make_river(). See the help there.
//*********************************************************************/
//static int river_test_adjacent_ocean(tile ptile)
//{
//  return 100 - count_ocean_near_tile(ptile, true, true);
//}
//
///*********************************************************************
// Help function used in make_river(). See the help there.
//*********************************************************************/
//static int river_test_adjacent_river(tile ptile)
//{
//  return 100 - count_special_near_tile(ptile, true, true, Terrain_H.S_RIVER);
//}
//
///*********************************************************************
// Help function used in make_river(). See the help there.
//*********************************************************************/
//static int river_test_adjacent_highlands(tile ptile)
//{
//  return (count_terrain_near_tile(ptile, true, true, T_HILLS)
//	  + 2 * count_terrain_near_tile(ptile, true, true, T_MOUNTAINS));
//}
//
///*********************************************************************
// Help function used in make_river(). See the help there.
//*********************************************************************/
//static int river_test_swamp(tile ptile)
//{
//  return (ptile.terrain != T_SWAMP) ? 1 : 0;
//}
//
///*********************************************************************
// Help function used in make_river(). See the help there.
//*********************************************************************/
//static int river_test_adjacent_swamp(tile ptile)
//{
//  return 100 - count_terrain_near_tile(ptile, true, true, T_SWAMP);
//}
//
///*********************************************************************
// Help function used in make_river(). See the help there.
//*********************************************************************/
//static int river_test_height_map(tile ptile)
//{
//  return hmap(ptile);
//}
//
///*********************************************************************
// Called from make_river. Marks all directions as blocked.  -Erik Sigra
//*********************************************************************/
//static void river_blockmark(tile ptile)
//{
//  util.freelog(Log.LOG_DEBUG, "Blockmarking (%d, %d) and adjacent tiles.",
//	  ptile.x, ptile.y);
//
//  rmap(ptile) |= (1u << RS_BLOCKED);
//
//  cardinal_for(tile tile1: util.adjc_tile_iterate(ptile)) {
//    rmap(tile1) |= (1u << RS_BLOCKED);
//  } cardinal_adjc_iterate_end;
//}
//
//struct test_func {
//  int (*func)(tile ptile);
//  boolean fatal;
//};
//
//public static final int NUM_TEST_FUNCTIONS = 9;
//static struct test_func test_funcs[NUM_TEST_FUNCTIONS] = {
//  {river_test_blocked,            true},
//  {river_test_rivergrid,          true},
//  {river_test_highlands,          false},
//  {river_test_adjacent_ocean,     false},
//  {river_test_adjacent_river,     false},
//  {river_test_adjacent_highlands, false},
//  {river_test_swamp,              false},
//  {river_test_adjacent_swamp,     false},
//  {river_test_height_map,         false}
//};
//
///********************************************************************
// Makes a river starting at (x, y). Returns 1 if it succeeds.
// Return 0 if it fails. The river is stored in river_map.
// 
// How to make a river path look natural
// =====================================
// Rivers always flow down. Thus rivers are best implemented on maps
// where every tile has an explicit height value. However, Freeciv has a
// flat map. But there are certain things that help the user imagine
// differences in height between tiles. The selection of direction for
// rivers should confirm and even amplify the user's image of the map's
// topology.
// 
// To decide which direction the river takes, the possible directions
// are tested in a series of test until there is only 1 direction
// left. Some tests are fatal. This means that they can sort away all
// remaining directions. If they do so, the river is aborted. Here
// follows a description of the test series.
// 
// * Falling into itself: fatal
//     (river_test_blocked)
//     This is tested by looking up in the river_map array if a tile or
//     every tile surrounding the tile is marked as blocked. A tile is
//     marked as blocked if it belongs to the current river or has been
//     evaluated in a previous iteration in the creation of the current
//     river.
//     
//     Possible values:
//     0: Is not falling into itself.
//     1: Is falling into itself.
//     
// * Forming a 4-river-grid: optionally fatal
//     (river_test_rivergrid)
//     A minimal 4-river-grid is formed when an intersection in the map
//     grid is surrounded by 4 river tiles. There can be larger river
//     grids consisting of several overlapping minimal 4-river-grids.
//     
//     Possible values:
//     0: Is not forming a 4-river-grid.
//     1: Is forming a 4-river-grid.
//
// * Highlands:
//     (river_test_highlands)
//     Rivers must not flow up in mountains or hills if there are
//     alternatives.
//     
//     Possible values:
//     0: Is not hills and not mountains.
//     1: Is hills.
//     2: Is mountains.
//
// * Adjacent ocean:
//     (river_test_adjacent_ocean)
//     Rivers must flow down to coastal areas when possible:
//
//     Possible values: 0-100
//
// * Adjacent river:
//     (river_test_adjacent_river)
//     Rivers must flow down to areas near other rivers when possible:
//
//     Possible values: 0-100
//					
// * Adjacent highlands:
//     (river_test_adjacent_highlands)
//     Rivers must not flow towards highlands if there are alternatives. 
//     
// * Swamps:
//     (river_test_swamp)
//     Rivers must flow down in swamps when possible.
//     
//     Possible values:
//     0: Is swamps.
//     1: Is not swamps.
//     
// * Adjacent swamps:
//     (river_test_adjacent_swamp)
//     Rivers must flow towards swamps when possible.
//
// * height_map:
//     (river_test_height_map)
//     Rivers must flow in the direction which takes it to the tile with
//     the lowest value on the height_map.
//     
//     Possible values:
//     n: height_map[...]
//     
// If these rules haven't decided the direction, the random number
// generator gets the desicion.                              -Erik Sigra
//*********************************************************************/
//static boolean make_river(tile ptile)
//{
//  /* Comparison value for each tile surrounding the current tile.  It is
//   * the suitability to continue a river to the tile in that direction;
//   * lower is better.  However rivers may only run in cardinal directions;
//   * the other directions are ignored entirely. */
//  int rd_comparison_val[8];
//
//  boolean rd_direction_is_valid[8];
//  int num_valid_directions, func_num, direction;
//
//  while (true) {
//    /* Mark the current tile as river. */
//    rmap(ptile) |= (1u << RTerrain_H.S_RIVER);
//    util.freelog(Log.LOG_DEBUG,
//	    "The tile at (%d, %d) has been marked as river in river_map.\n",
//	    ptile.x, ptile.y);
//
//    /* Test if the river is done. */
//    /* We arbitrarily make rivers end at the poles. */
//    if (count_special_near_tile(ptile, true, true, Terrain_H.S_RIVER) > 0
//	|| count_ocean_near_tile(ptile, true, true) > 0
//        || (ptile.terrain == T_ARCTIC 
//	    && map_colatitude(ptile) < 0.8 * COLD_LEVEL)) { 
//
//      util.freelog(Log.LOG_DEBUG,
//	      "The river ended at (%d, %d).\n", ptile.x, ptile.y);
//      return true;
//    }
//
//    /* Else choose a direction to continue the river. */
//    util.freelog(Log.LOG_DEBUG,
//	    "The river did not end at (%d, %d). Evaluating directions...\n",
//	    ptile.x, ptile.y);
//
//    /* Mark all available cardinal directions as available. */
//    memset(rd_direction_is_valid, 0, sizeof(rd_direction_is_valid));
//    cardinal_adjc_dir_iterate(ptile, tile1, dir) {
//      rd_direction_is_valid[dir] = true;
//    } cardinal_adjc_dir_iterate_end;
//
//    /* Test series that selects a direction for the river. */
//    for (func_num = 0; func_num < NUM_TEST_FUNCTIONS; func_num++) {
//      int best_val = -1;
//
//      /* first get the tile values for the function */
//      cardinal_adjc_dir_iterate(ptile, tile1, dir) {
//	if (rd_direction_is_valid[dir]) {
//	  rd_comparison_val[dir] = (test_funcs[func_num].func) (tile1);
//	  if (best_val == -1) {
//	    best_val = rd_comparison_val[dir];
//	  } else {
//	    best_val = Math.min(rd_comparison_val[dir], best_val);
//	  }
//	}
//      } cardinal_adjc_dir_iterate_end;
//      assert(best_val != -1);
//
//      /* should we abort? */
//      if (best_val > 0 && test_funcs[func_num].fatal) {
//	return false;
//      }
//
//      /* mark the less attractive directions as invalid */
//      cardinal_adjc_dir_iterate(ptile, tile1, dir) {
//	if (rd_direction_is_valid[dir]) {
//	  if (rd_comparison_val[dir] != best_val) {
//	    rd_direction_is_valid[dir] = false;
//	  }
//	}
//      } cardinal_adjc_dir_iterate_end;
//    }
//
//    /* Directions evaluated with all functions. Now choose the best
//       direction before going to the next iteration of the while loop */
//    num_valid_directions = 0;
//    cardinal_adjc_dir_iterate(ptile, tile1, dir) {
//      if (rd_direction_is_valid[dir]) {
//	num_valid_directions++;
//      }
//    } cardinal_adjc_dir_iterate_end;
//
//    if (num_valid_directions == 0) {
//      return false; /* river aborted */
//    }
//
//    /* One or more valid directions: choose randomly. */
//    util.freelog(Log.LOG_DEBUG, "mapgen.c: Had to let the random number" +
//	    " generator select a direction for a river.");
//    direction = Rand.myrand(num_valid_directions);
//    util.freelog(Log.LOG_DEBUG, "mapgen.c: direction: %d", direction);
//
//    /* Find the direction that the random number generator selected. */
//    cardinal_adjc_dir_iterate(ptile, tile1, dir) {
//      if (rd_direction_is_valid[dir]) {
//	if (direction > 0) {
//	  direction--;
//	} else {
//	  river_blockmark(ptile);
//	  ptile = tile1;
//	  break;
//	}
//      }
//    } cardinal_adjc_dir_iterate_end;
//    assert(direction == 0);
//
//  } /* end while; (Make a river.) */
//}
//
///**************************************************************************
//  Calls make_river until there are enough river tiles on the map. It stops
//  when it has tried to create RIVERS_MAXTRIES rivers.           -Erik Sigra
//**************************************************************************/
//static void make_rivers()
//{
//  tile ptile;
//
//  /* Formula to make the river density similar om different sized maps. Avoids
//     too few rivers on large maps and too many rivers on small maps. */
//  int desirable_riverlength =
//    river_pct *
//      /* The size of the map (poles counted in river_pct). */
//      Map.map.map_num_tiles() *
//      /* Rivers need to be on land only. */
//      Map.map.landpercent /
//      /* Adjustment value. Tested by me. Gives no rivers with 'set
//	 rivers 0', gives a reasonable amount of rivers with default
//	 settings and as many rivers as possible with 'set rivers 100'. */
//      5325;
//
//  /* The number of river tiles that have been set. */
//  int current_riverlength = 0;
//
//  int i; /* Loop variable. */
//
//  /* Counts the number of iterations (should increase with 1 during
//     every iteration of the main loop in this function).
//     Is needed to stop a potentially infinite loop. */
//  int iteration_counter = 0;
//
//  create_placed_map(); /* needed bu rand_map_characteristic */
//  set_all_ocean_tiles_placed();
//
//  river_map = fc_malloc(sizeof(int) * Map_H.MAX_MAP_INDEX);
//
//  /* The main loop in this function. */
//  while (current_riverlength < desirable_riverlength
//	 && iteration_counter < RIVERS_MAXTRIES) {
//
//    if (!(ptile = rand_map_pos_characteristic(WC_ALL, TT_NFROZEN,
//					      MC_NLOW))) {
//	break; /* mo more spring places */
//    }
//
//    /* Check if it is suitable to start a river on the current tile.
//     */
//    if (
//	/* Don't start a river on ocean. */
//	!Terrain_H.is_ocean(ptile.terrain)
//
//	/* Don't start a river on river. */
//	&& !Map.map_has_special(ptile, Terrain_H.S_RIVER)
//
//	/* Don't start a river on a tile is surrounded by > 1 river +
//	   ocean tile. */
//	&& (count_special_near_tile(ptile, true, false, Terrain_H.S_RIVER)
//	    + count_ocean_near_tile(ptile, true, false) <= 1)
//
//	/* Don't start a river on a tile that is surrounded by hills or
//	   mountains unless it is hard to find somewhere else to start
//	   it. */
//	&& (count_terrain_near_tile(ptile, true, true, T_HILLS)
//	    + count_terrain_near_tile(ptile, true, true, T_MOUNTAINS) < 90
//	    || iteration_counter >= RIVERS_MAXTRIES / 10 * 5)
//
//	/* Don't start a river on hills unless it is hard to find
//	   somewhere else to start it. */
//	&& (ptile.terrain != T_HILLS
//	    || iteration_counter >= RIVERS_MAXTRIES / 10 * 6)
//
//	/* Don't start a river on mountains unless it is hard to find
//	   somewhere else to start it. */
//	&& (ptile.terrain != T_MOUNTAINS
//	    || iteration_counter >= RIVERS_MAXTRIES / 10 * 7)
//
//	/* Don't start a river on arctic unless it is hard to find
//	   somewhere else to start it. */
//	&& (ptile.terrain != T_ARCTIC
//	    || iteration_counter >= RIVERS_MAXTRIES / 10 * 8)
//
//	/* Don't start a river on desert unless it is hard to find
//	   somewhere else to start it. */
//	&& (ptile.terrain != T_DESERT
//	    || iteration_counter >= RIVERS_MAXTRIES / 10 * 9)) {
//
//      /* Reset river_map before making a new river. */
//      for (i = 0; i < Map.map.xsize * Map.map.ysize; i++) {
//	river_map[i] = 0;
//      }
//
//      util.freelog(Log.LOG_DEBUG,
//	      "Found a suitable starting tile for a river at (%d, %d)." +
//	      " Starting to make it.",
//	      ptile.x, ptile.y);
//
//      /* Try to make a river. If it is OK, apply it to the Map.map. */
//      if (make_river(ptile)) {
//	whole_map_iterate(tile1) {
//	  if (TEST_BIT(rmap(tile1), RTerrain_H.S_RIVER)) {
//	    int t = tile1.terrain;
//
//	    if (!Terrain_H.terrain_has_flag(t, TER_CAN_HAVE_RIVER)) {
//	      /* We have to change the terrain to put a river here. */
//	      t = get_flag_terrain(TER_CAN_HAVE_RIVER);
//	      map_set_terrain(tile1, t);
//	    }
//	    Map.map_set_special(tile1, Terrain_H.S_RIVER);
//	    current_riverlength++;
//	    map_set_placed(tile1);
//	    util.freelog(Log.LOG_DEBUG, "Applied a river to (%d, %d).",
//		    tile1.x, tile1.y);
//	  }
//	}
//      }
//      else {
//	util.freelog(Log.LOG_DEBUG,
//		"mapgen.c: A river failed. It might have gotten stuck in a helix.");
//      }
//    } /* end if; */
//    iteration_counter++;
//    util.freelog(Log.LOG_DEBUG,
//	    "current_riverlength: %d; desirable_riverlength: %d; iteration_counter: %d",
//	    current_riverlength, desirable_riverlength, iteration_counter);
//  } /* end while; */
//  free(river_map);
//  destroy_placed_map();
//  river_map = null;
//}
//
///**************************************************************************
//  make land simply does it all based on a generated heightmap
//  1) with Map.map.landpercent it generates a ocean/grassland map 
//  2) it then calls the above functions to generate the different terrains
//**************************************************************************/
//static void make_land()
//{
//  
//  if (HAS_POLES) {
//    normalize_hmap_poles();
//  }
//  hmap_shore_level = (hmap_max_level * (100 - Map.map.landpercent)) / 100;
//  ini_hmap_low_level();
//  for(tile ptile :  Map.map.tiles){
//    map_set_terrain(ptile, T_UNKNOWN); /* set as oceans count is used */
//    if (hmap(ptile) < hmap_shore_level) {
//      map_set_terrain(ptile, T_OCEAN);
//    }
//  }
//  if (HAS_POLES) {
//    renormalize_hmap_poles();
//  } 
//
//  create_tmap(true); /* base temperature map, need hmap and oceans */
//  
//  if (HAS_POLES) { /* this is a hack to terrains set with not frizzed oceans*/
//    make_polar_land(); /* make extra land at poles*/
//  }
//
//  create_placed_map(); /* here it means land terrains to be placed */
//  set_all_ocean_tiles_placed();
//  make_relief(); /* base relief on map */
//  make_terrains(); /* place all exept mountains and hill */
//  destroy_placed_map();
//
//  make_rivers(); /* use a new placed_map. destroy older before call */
//}
//
///**************************************************************************
//  Returns if this is a 1x1 island
//**************************************************************************/
//static boolean is_tiny_island(tile ptile) 
//{
//  int t = ptile.terrain;
//
//  if (Terrain_H.is_ocean(t) || t == T_ARCTIC) {
//    /* The arctic check is needed for iso-maps: the poles may not have
//     * any cardinally adjacent land tiles, but that's okay. */
//    return false;
//  }
//
//  cardinal_for(tile tile1: util.adjc_tile_iterate(ptile)) {
//    if (!Terrain_H.is_ocean(tile1.terrain)) {
//      return false;
//    }
//  } cardinal_adjc_iterate_end;
//
//  return true;
//}
//
///**************************************************************************
//  Removes all 1x1 islands (sets them to ocean).
//**************************************************************************/
//static void remove_tiny_islands()
//{
//  for(tile ptile :  Map.map.tiles){
//    if (is_tiny_island(ptile)) {
//      map_set_terrain(ptile, T_OCEAN);
//      Map.map_clear_special(ptile, Terrain_H.S_RIVER);
//      map_set_continent(ptile, 0);
//    }
//  }
//}
//
///**************************************************************************
//  Debugging function to print information about the map that's been
//  generated.
//**************************************************************************/
//static void print_mapgen_map()
//{
//  final int loglevel = Log.LOG_DEBUG;
//  int terrain_count[Terrain_H.T_COUNT];
//  int total = 0;
//
//  terrain_type_iterate(t) {
//    terrain_count[t] = 0;
//  } terrain_type_iterate_end;
//
//  for(tile ptile :  Map.map.tiles){
//    int t = ptile.terrain;
//
//    assert(t >= 0 && t < Terrain_H.T_COUNT);
//    terrain_count[t]++;
//    if (!Terrain_H.is_ocean(t)) {
//      total++;
//    }
//  }
//
//  terrain_type_iterate(t) {
//    util.freelog(loglevel, "%20s : %4d %d%%  ",
//	    get_terrain_name(t), terrain_count[t],
//	    (terrain_count[t] * 100 + 50) / total);
//  } terrain_type_iterate_end;
//}
//
///**************************************************************************
//  See stdinhand.c for information on map generation methods.
//
//FIXME: Some continent numbers are unused at the end of this function, fx
//       removed completely by remove_tiny_islands.
//       When this function is finished various data is written to "islands",
//       indexed by continent numbers, so a simple renumbering would not
//       work...
//
//  If "autosize" is specified then mapgen will automatically size the map
//  based on the Map.map.size server parameter and the specified topology.  If
//  not Map.map.xsize and Map.map.ysize will be used.
//**************************************************************************/
//void map_fractal_generate(boolean autosize)
//{
//  /* save the current random state: */
//  RANDOM_STATE rstate = get_Rand.myrand_state();
// 
//  if (Map.map.seed==0)
//    Map.map.seed = (Rand.myrand(MAX_UINT32) ^ time(null)) & (MAX_UINT32 >> 1);
//
//  mysrand(Map.map.seed);
//
//  /* don't generate tiles with mapgen==0 as we've loaded them from file */
//  /* also, don't delete (the handcrafted!) tiny islands in a scenario */
//  if (Map.map.generator != 0) {
//    generator_init_topology(autosize);
//    map_allocate();
//    adjust_terrain_param();
//    /* if one mapgenerator fails, it will choose another mapgenerator */
//    /* with a lower number to try again */
//    
//    if (Map.map.generator == 3) {
//      /* 2 or 3 players per isle? */
//      if (Map.map.startpos == 2 || (Map.map.startpos == 3)) { 
//	mapgenerator4();
//      }
//      if (Map.map.startpos <= 1) {
//	/* single player per isle */
//	mapgenerator3();
//      }
//      if (Map.map.startpos == 4) {
//	/* "variable" single player */
//	mapgenerator2();
//      }
//    }
//
//    if (Map.map.generator == 2) {
//      make_pseudofractal1_hmap(1 + ((Map.map.startpos == 0
//				     || Map.map.startpos == 3)
//				    ? 0 : Game.game.nplayers));
//    }
//
//    if (Map.map.generator == 1) {
//      make_random_hmap(MAX(1, 1 + SQSIZE 
//			   - (Map.map.startpos ? Game.game.nplayers / 4 : 0)));
//    }
//
//    /* if hmap only generator make anything else */
//    if (Map.map.generator == 1 || Map.map.generator == 2) {
//      make_land();
//      free(height_map);
//      height_map = null;
//    }
//    if (!Map.map.tinyisles) {
//      remove_tiny_islands();
//    }
//  }
//
//  if (!temperature_is_initialized()) {
//    create_tmap(false);
//  }
//  
//  /* some scenarios already provide specials */
//  if (!Map.map.have_specials) {
//    add_specials(Map.map.riches);
//  }
//
//  if (!Map.map.have_huts) {
//    make_huts(Map.map.huts); 
//  }
//
//  /* restore previous random state: */
//  set_Rand.myrand_state(rstate);
//  destroy_tmap();
//
//  /* We don't want random start positions in a scenario which already
//   * provides them. */
//  if (Map.map.num_start_positions == 0) {
//    enum start_mode mode = MT_ALL;
//    boolean success;
//    
//    switch (Map.map.generator) {
//    case 0:
//    case 1:
//      mode = Map.map.startpos;
//      break;
//    case 2:
//      if (Map.map.startpos == 0) {
//        mode = MT_ALL;
//      } else {
//        mode = Map.map.startpos;
//      }
//      break;
//    case 3:
//      if (Map.map.startpos <= 1 || (Map.map.startpos == 4)) {
//        mode = MT_SINGLE;
//      } else {
//	mode = MT_2or3;
//      }
//      break;
//    }
//    
//    for(;;) {
//      success = create_start_positions(mode);
//      if (success) {
//        break;
//      }
//      
//      switch(mode) {
//        case MT_SINGLE:
//	  mode = MT_2or3;
//	  break;
//	case MT_2or3:
//	  mode = MT_ALL;
//	  break;
//	case MT_ALL:
//	  mode = MT_VARIABLE;
//	  break;
//	default:
//	  assert(0!=1);
//	  util.die("The server couldn't allocate starting positions.");
//      }
//    }
//
//
//  }
//
//  assign_continent_numbers(false);
//
//  print_mapgen_map();
//}
//
///**************************************************************************
// Convert parameters from the server into terrains percents parameters for
// the generators
//**************************************************************************/
//static void adjust_terrain_param()
//{
//  int polar = 2 * ICE_BASE_LEVEL * Map.map.landpercent / MAX_COLATITUDE ;
//  float factor = (100.0 - polar - Map.map.steepness * 0.8 ) / 10000;
//
//
//  mountain_pct = factor * Map.map.steepness * 90;
//
//  /* 27 % if wetness == 50 & */
//  forest_pct = factor * (Map.map.wetness * 40 + 700) ; 
//  jungle_pct = forest_pct * (MAX_COLATITUDE - TROPICAL_LEVEL) /
//               (MAX_COLATITUDE * 2);
//  forest_pct -= jungle_pct;
//
//  /* 3 - 11 % */
//  river_pct = (100 - polar) * (3 + Map.map.wetness / 12) / 100;
//
//  /* 6 %  if wetness == 50 && temperature == 50 */
//  swamp_pct = factor * MAX(0, 
//			   (Map.map.wetness * 9 - 150 + Map.map.temperature * 6));
//  desert_pct =factor * MAX(0,
//		(Map.map.temperature * 15 - 250 + (100 - Map.map.wetness) * 10)) ;
//}
//
///****************************************************************************
//  Return true if a safe tile is in a radius of 1.  This function is used to
//  test where to place specials on the sea.
//****************************************************************************/
//static boolean near_safe_tiles(tile ptile)
//{
//  for(tile tile1: util.square_tile_iterate(ptile, 1)) {
//    if (!Terrain_H.terrain_has_flag(tile1.terrain, TER_UNSAFE_COAST)) {
//      return true;
//    }	
//  }
//
//  return false;
//}
//
///**************************************************************************
//  this function spreads out huts on the map, a position can be used for a
//  hut if there isn't another hut close and if it's not on the ocean.
//**************************************************************************/
//static void make_huts(int number)
//{
//  int count = 0;
//  tile ptile;
//
//  create_placed_map(); /* here it means placed huts */
//
//  while (number * Map.map.map_num_tiles() >= 2000 && count++ < Map.map.map_num_tiles() * 2) {
//
//    /* Add a hut.  But not on a polar area, on an ocean, or too close to
//     * another hut. */
//    if ((ptile = rand_map_pos_characteristic(WC_ALL, TT_NFROZEN, MC_NONE))) {
//      if (Terrain_H.is_ocean(ptile.terrain)) {
//	map_set_placed(ptile); /* not good for a hut */
//      } else {
//	number--;
//	Map.map_set_special(ptile, Terrain_H.S_HUT);
//	set_placed_near_pos(ptile, 3);
//      }
//    }
//  }
//  destroy_placed_map();
//}
//
///****************************************************************************
//  Return true iff there's a special (i.e., SPECIAL_1 or SPECIAL_2) within
//  1 tile of the given map position.
//****************************************************************************/
//static boolean is_special_close(tile ptile)
//{
//  for(tile tile1: util.square_tile_iterate(ptile, 1)) {
//    if (Map.map_has_special(tile1, S_SPECIAL_1)
//	|| Map.map_has_special(tile1, S_SPECIAL_2)) {
//      return true;
//    }
//  }
//
//  return false;
//}
//
///****************************************************************************
//  Add specials to the map with given probability (out of 1000).
//****************************************************************************/
//static void add_specials(int prob)
//{
//  int ttype;
//
//  whole_map_iterate(ptile)  {
//    ttype = ptile.terrain;
//    if (!Terrain_H.is_ocean(ttype)
//	&& !is_special_close(ptile) 
//	&& Rand.myrand(1000) < prob) {
//      if (get_tile_type(ttype).special_1_name[0] != '\0'
//	  && (get_tile_type(ttype).special_2_name[0] == '\0'
//	      || (Rand.myrand(100) < 50))) {
//	Map.map_set_special(ptile, S_SPECIAL_1);
//      } else if (get_tile_type(ttype).special_2_name[0] != '\0') {
//	Map.map_set_special(ptile, S_SPECIAL_2);
//      }
//    } else if (Terrain_H.is_ocean(ttype) && near_safe_tiles(ptile) 
//	       && Rand.myrand(1000) < prob && !is_special_close(ptile)) {
//      if (get_tile_type(ttype).special_1_name[0] != '\0'
//	  && (get_tile_type(ttype).special_2_name[0] == '\0'
//	      || (Rand.myrand(100) < 50))) {
//        Map.map_set_special(ptile, S_SPECIAL_1);
//      } else if (get_tile_type(ttype).special_2_name[0] != '\0') {
//	Map.map_set_special(ptile, S_SPECIAL_2);
//      }
//    }
//  }
//  
//  Map.map.have_specials = true;
//}
//
///**************************************************************************
//  common variables for generator 2, 3 and 4
//**************************************************************************/
//struct gen234_state {
//  int isleindex, n, e, s, w;
//  long int totalmass;
//};
//
///**************************************************************************
//Returns a random position in the rectangle denoted by the given state.
//**************************************************************************/
//static tile get_random_map_position_from_state(
//					       final struct gen234_state
//					       *final pstate)
//{
//  int xn, yn;
//
//  assert((pstate.e - pstate.w) > 0);
//  assert((pstate.e - pstate.w) < Map.map.xsize);
//  assert((pstate.s - pstate.n) > 0);
//  assert((pstate.s - pstate.n) < Map.map.ysize);
//
//  xn = pstate.w + Rand.myrand(pstate.e - pstate.w);
//  yn = pstate.n + Rand.myrand(pstate.s - pstate.n);
//
//  return native_pos_to_tile(xn, yn);
//}
//
///**************************************************************************
//  fill an island with up four types of terrains, rivers have extra code
//**************************************************************************/
//static void fill_island(int coast, long int *bucket,
//			int warm0_weight, int warm1_weight,
//			int cold0_weight, int cold1_weight,
//			int warm0,
//			int warm1,
//			int cold0,
//			int cold1,
//			final gen234_state final pstate)
//{
//  int i, k, capac;
//  long int failsafe;
//
//  if (*bucket <= 0 ) return;
//  capac = pstate.totalmass;
//  i = *bucket / capac;
//  i++;
//  *bucket -= i * capac;
//
//  k= i;
//  failsafe = i * (pstate.s - pstate.n) * (pstate.e - pstate.w);
//  if (failsafe<0) {
//    failsafe= -failsafe;
//  }
//
//  if (warm0_weight + warm1_weight + cold0_weight + cold1_weight <= 0)
//    i= 0;
//
//  while (i > 0 && (failsafe--) > 0) {
//    tile ptile =  get_random_map_position_from_state(pstate);
//
//    if (map_get_continent(ptile) == pstate.isleindex &&
//	not_placed(ptile)) {
//
//      /* the first condition helps make terrain more contiguous,
//	 the second lets it avoid the coast: */
//      if ( ( i*3>k*2 
//	     || Terrain.is_terrain_near_tile(ptile, warm0) 
//	     || Terrain.is_terrain_near_tile(ptile, warm1) 
//	     || Rand.myrand(100)<50 
//	     || Terrain.is_terrain_near_tile(ptile, cold0) 
//	     || Terrain.is_terrain_near_tile(ptile, cold1) 
//	     )
//	   &&( !is_cardinally_adj_to_ocean(ptile) || Rand.myrand(100) < coast )) {
//	if (map_colatitude(ptile) < COLD_LEVEL) {
//	  map_set_terrain(ptile, (Rand.myrand(cold0_weight
//					+ cold1_weight) < cold0_weight) 
//			  ? cold0 : cold1);
//	  map_set_placed(ptile);
//	} else {
//	  map_set_terrain(ptile, (Rand.myrand(warm0_weight
//					+ warm1_weight) < warm0_weight) 
//			  ? warm0 : warm1);
//	  map_set_placed(ptile);
//	}
//      }
//      if (!not_placed(ptile)) i--;
//    }
//  }
//}
//
///**************************************************************************
//  fill an island with rivers
//**************************************************************************/
//static void fill_island_rivers(int coast, long int *bucket,
//			       final gen234_state final pstate)
//{
//  int i, k, capac;
//  long int failsafe;
//
//  if (*bucket <= 0 ) {
//    return;
//  }
//  capac = pstate.totalmass;
//  i = *bucket / capac;
//  i++;
//  *bucket -= i * capac;
//
//  k = i;
//  failsafe = i * (pstate.s - pstate.n) * (pstate.e - pstate.w);
//  if (failsafe < 0) {
//    failsafe = -failsafe;
//  }
//
//  while (i > 0 && (failsafe--) > 0) {
//    tile ptile = get_random_map_position_from_state(pstate);
//    if (map_get_continent(ptile) == pstate.isleindex
//	&& not_placed(ptile)) {
//
//      /* the first condition helps make terrain more contiguous,
//	 the second lets it avoid the coast: */
//      if ((i * 3 > k * 2 
//	   || count_special_near_tile(ptile, false, true, Terrain_H.S_RIVER) > 0
//	   || Rand.myrand(100) < 50)
//	  && (!is_cardinally_adj_to_ocean(ptile) || Rand.myrand(100) < coast)) {
//	if (is_water_adjacent_to_tile(ptile)
//	    && count_ocean_near_tile(ptile, false, true) < 50
//            && count_special_near_tile(ptile, false, true, Terrain_H.S_RIVER) < 35) {
//	  Map.map_set_special(ptile, Terrain_H.S_RIVER);
//	  i--;
//	}
//      }
//    }
//  }
//}
//
///****************************************************************************
//  Return true if the ocean position is near land.  This is used in the
//  creation of islands, so it differs logically from near_safe_tiles().
//****************************************************************************/
//static boolean is_near_land(tile ptile)
//{
//  /* Note this function may sometimes be called on land tiles. */
//  for(tile tile1: util.adjc_tile_iterate(ptile)) {
//    if (!Terrain_H.is_ocean(tile1.terrain)) {
//      return true;
//    }
//  }
//
//  return false;
//}
//
//static long int checkmass;
//
///**************************************************************************
//  finds a place and drop the island created when called with islemass != 0
//**************************************************************************/
//static boolean place_island(gen234_state pstate)
//{
//  int i=0, xn, yn;
//  tile ptile;
//
//  ptile = Map.rand_map_pos();
//
//  /* this helps a lot for maps with high landmass */
//  for (yn = pstate.n, xn = pstate.w;
//       yn < pstate.s && xn < pstate.e;
//       yn++, xn++) {
//    tile tile0 = native_pos_to_tile(xn, yn);
//    tile tile1 = native_pos_to_tile(xn + ptile.nat_x - pstate.w,
//					    yn + ptile.nat_y - pstate.n);
//
//    if (!tile0 || !tile1) {
//      return false;
//    }
//    if (hmap(tile0) != 0 && is_near_land(tile1)) {
//      return false;
//    }
//  }
//		       
//  for (yn = pstate.n; yn < pstate.s; yn++) {
//    for (xn = pstate.w; xn < pstate.e; xn++) {
//      tile tile0 = native_pos_to_tile(xn, yn);
//      tile tile1 = native_pos_to_tile(xn + ptile.nat_x - pstate.w,
//					      yn + ptile.nat_y - pstate.n);
//
//      if (!tile0 || !tile1) {
//	return false;
//      }
//      if (hmap(tile0) != 0 && is_near_land(tile1)) {
//	return false;
//      }
//    }
//  }
//
//  for (yn = pstate.n; yn < pstate.s; yn++) {
//    for (xn = pstate.w; xn < pstate.e; xn++) {
//      if (hmap(native_pos_to_tile(xn, yn)) != 0) {
//	tile tile1
//	  = native_pos_to_tile(xn + ptile.nat_x - pstate.w,
//			       yn + ptile.nat_y - pstate.n);
//
//	checkmass--; 
//	if (checkmass <= 0) {
//	  util.freelog(Log.LOG_ERROR, "mapgen.c: mass doesn't sum up.");
//	  return i != 0;
//	}
//
//        map_set_terrain(tile1, T_UNKNOWN);
//	map_unset_placed(tile1);
//
//	map_set_continent(tile1, pstate.isleindex);
//        i++;
//      }
//    }
//  }
//  pstate.s += ptile.nat_y - pstate.n;
//  pstate.e += ptile.nat_x - pstate.w;
//  pstate.n = ptile.nat_y;
//  pstate.w = ptile.nat_x;
//  return i != 0;
//}
//
///****************************************************************************
//  Returns the number of cardinally adjacent tiles have a non-zero elevation.
//****************************************************************************/
//static int count_card_adjc_elevated_tiles(tile ptile)
//{
//  int count = 0;
//
//  cardinal_for(tile tile1: util.adjc_tile_iterate(ptile)) {
//    if (hmap(tile1) != 0) {
//      count++;
//    }
//  } cardinal_adjc_iterate_end;
//
//  return count;
//}
//
///**************************************************************************
//  finds a place and drop the island created when called with islemass != 0
//**************************************************************************/
//static boolean create_island(int islemass, gen234_state pstate)
//{
//  int i;
//  long int tries=islemass*(2+islemass/20)+99;
//  boolean j;
//  tile ptile = native_pos_to_tile(Map.map.xsize / 2, Map.map.ysize / 2);
//
//  memset(height_map, '\0', sizeof(int) * Map.map.xsize * Map.map.ysize);
//  hmap(native_pos_to_tile(Map.map.xsize / 2, Map.map.ysize / 2)) = 1;
//  pstate.n = ptile.nat_y - 1;
//  pstate.w = ptile.nat_x - 1;
//  pstate.s = ptile.nat_y + 2;
//  pstate.e = ptile.nat_x + 2;
//  i = islemass - 1;
//  while (i > 0 && tries-.0) {
//    ptile = get_random_map_position_from_state(pstate);
//
//    if ((!near_singularity(ptile) || Rand.myrand(50) < 25 ) 
//	&& hmap(ptile) == 0 && count_card_adjc_elevated_tiles(ptile) > 0) {
//      hmap(ptile) = 1;
//      i--;
//      if (ptile.nat_y >= pstate.s - 1 && pstate.s < Map.map.ysize - 2) {
//	pstate.s++;
//      }
//      if (ptile.nat_x >= pstate.e - 1 && pstate.e < Map.map.xsize - 2) {
//	pstate.e++;
//      }
//      if (ptile.nat_y <= pstate.n && pstate.n > 2) {
//	pstate.n--;
//      }
//      if (ptile.nat_x <= pstate.w && pstate.w > 2) {
//	pstate.w--;
//      }
//    }
//    if (i < islemass / 10) {
//      int xn, yn;
//
//      for (yn = pstate.n; yn < pstate.s; yn++) {
//	for (xn = pstate.w; xn < pstate.e; xn++) {
//	  ptile = native_pos_to_tile(xn, yn);
//	  if (hmap(ptile) == 0 && i > 0
//	      && count_card_adjc_elevated_tiles(ptile) == 4) {
//	    hmap(ptile) = 1;
//            i--; 
//          }
//	}
//      }
//    }
//  }
//  if (tries<=0) {
//    util.freelog(Log.LOG_ERROR, "create_island ended early with %d/%d.",
//	    islemass-i, islemass);
//  }
//  
//  tries = Map.map.map_num_tiles() / 4;	/* on a 40x60 map, there are 2400 places */
//  while (!(j = place_island(pstate)) && (--tries) > 0) {
//    /* nothing */
//  }
//  return j;
//}
//
///*************************************************************************/
//
///**************************************************************************
//  make an island, fill every tile type except plains
//  note: you have to create big islands first.
//  Return true if successful.
//  min_specific_island_size is a percent value.
//***************************************************************************/
//static boolean make_island(int islemass, int starters,
//			gen234_state pstate,
//			int min_specific_island_size)
//{
//  /* int may be only 2 byte ! */
//  static long int tilefactor, balance, lastplaced;
//  static long int riverbuck, mountbuck, desertbuck, forestbuck, swampbuck;
//
//  int i;
//
//  if (islemass == 0) {
//    /* this only runs to initialise static things, not to actually
//     * create an island. */
//    balance = 0;
//    pstate.isleindex = Map.map.num_continents + 1;	/* 0= none, poles, then isles */
//
//    checkmass = pstate.totalmass;
//
//    /* caveat: this should really be sent to all players */
//    if (pstate.totalmass > 3000)
//      util.freelog(Log.LOG_NORMAL, "High landmass - this may take a few seconds.");
//
//    i = river_pct + mountain_pct + desert_pct + forest_pct + swamp_pct;
//    i = (i <= 90) ? 100 : i * 11 / 10;
//    tilefactor = pstate.totalmass / i;
//    riverbuck = -(long int) Rand.myrand(pstate.totalmass);
//    mountbuck = -(long int) Rand.myrand(pstate.totalmass);
//    desertbuck = -(long int) Rand.myrand(pstate.totalmass);
//    forestbuck = -(long int) Rand.myrand(pstate.totalmass);
//    swampbuck = -(long int) Rand.myrand(pstate.totalmass);
//    lastplaced = pstate.totalmass;
//  } else {
//
//    /* makes the islands this big */
//    islemass = islemass - balance;
//
//    /* don't create continents without a number */
//    if (pstate.isleindex >= MAP_NCONT) {
//      return false;
//    }
//
//    if (islemass > lastplaced + 1 + lastplaced / 50) {
//      /* don't create big isles we can't place */
//      islemass = lastplaced + 1 + lastplaced / 50;
//    }
//
//    /* isle creation does not perform well for nonsquare islands */
//    if (islemass > (Map.map.ysize - 6) * (Map.map.ysize - 6)) {
//      islemass = (Map.map.ysize - 6) * (Map.map.ysize - 6);
//    }
//
//    if (islemass > (Map.map.xsize - 2) * (Map.map.xsize - 2)) {
//      islemass = (Map.map.xsize - 2) * (Map.map.xsize - 2);
//    }
//
//    i = islemass;
//    if (i <= 0) {
//      return false;
//    }
//    assert(starters >= 0);
//    util.freelog(Log.LOG_VERBOSE, "island %i", pstate.isleindex);
//
//    /* keep trying to place an island, and decrease the size of
//     * the island we're trying to create until we succeed.
//     * If we get too small, return an error. */
//    while (!create_island(i, pstate)) {
//      if (i < islemass * min_specific_island_size / 100) {
//	return false;
//      }
//      i--;
//    }
//    i++;
//    lastplaced = i;
//    if (i * 10 > islemass) {
//      balance = i - islemass;
//    } else{
//      balance = 0;
//    }
//
//    util.freelog(Log.LOG_VERBOSE, "ini=%d, plc=%d, bal=%ld, tot=%ld",
//	    islemass, i, balance, checkmass);
//
//    i *= tilefactor;
//
//    riverbuck += river_pct * i;
//    fill_island_rivers(1, &riverbuck, pstate);
//
//    mountbuck += mountain_pct * i;
//    fill_island(20, &mountbuck,
//		3, 1, 3,1,
//		T_HILLS, T_MOUNTAINS, T_HILLS, T_MOUNTAINS,
//		pstate);
//    desertbuck += desert_pct * i;
//    fill_island(40, &desertbuck,
//		1, 1, 1, 1,
//		T_DESERT, T_DESERT, T_DESERT, T_TUNDRA,
//		pstate);
//    forestbuck += forest_pct * i;
//    fill_island(60, &forestbuck,
//		forest_pct, swamp_pct, forest_pct, swamp_pct,
//		T_FOREST, T_JUNGLE, T_FOREST, T_TUNDRA,
//		pstate);
//    swampbuck += swamp_pct * i;
//    fill_island(80, &swampbuck,
//		1, 1, 1, 1,
//		T_SWAMP, T_SWAMP, T_SWAMP, T_SWAMP,
//		pstate);
//
//    pstate.isleindex++;
//    Map.map.num_continents++;
//  }
//  return true;
//}
//
///**************************************************************************
//  fill ocean and make polar
//**************************************************************************/
//static void initworld(gen234_state pstate)
//{
//  height_map = fc_malloc(sizeof(int) * Map.map.ysize * Map.map.xsize);
//  create_placed_map(); /* land tiles which aren't placed yet */
//  create_tmap(false);
//  
//  for(tile ptile :  Map.map.tiles){
//    map_set_terrain(ptile, T_OCEAN);
//    map_set_continent(ptile, 0);
//    map_set_placed(ptile); /* not a land tile */
//    map_clear_all_specials(ptile);
//    map_set_owner(ptile, null);
//  }
//  
//  if (HAS_POLES) {
//    make_polar();
//  }
//  
//  /* Set poles numbers.  After the map is generated continents will 
//   * be renumbered. */
//  make_island(0, 0, pstate, 0);
//}  
//
///* This variable is the Default Minimum Specific Island Size, 
// * ie the smallest size we'll typically permit our island, as a % of
// * the size we wanted. So if we ask for an island of size x, the island 
// * creation will return if it would create an island smaller than
// *  x * DMSIS / 100 */
//public static final int DMSIS = 10;
//
///**************************************************************************
//  island base map generators
//**************************************************************************/
//static void mapgenerator2()
//{
//  long int totalweight;
//  struct gen234_state state;
//  gen234_state pstate = &state;
//  int i;
//  boolean done = false;
//  int spares= 1; 
//  /* finalant that makes up that an island actually needs additional space */
//
//  /* put 70% of land in big continents, 
//   *     20% in medium, and 
//   *     10% in small. */ 
//  int bigfrac = 70, midfrac = 20, smallfrac = 10;
//
//  if (Map.map.landpercent > 85) {
//    Map.map.generator = 1;
//    return;
//  }
//
//  pstate.totalmass = ((Map.map.ysize - 6 - spares) * Map.map.landpercent 
//                       * (Map.map.xsize - spares)) / 100;
//  totalweight = 100 * Game.game.nplayers;
//
//  assert(!placed_map_is_initialized());
//
//  while (!done && bigfrac > midfrac) {
//    done = true;
//
//    if (placed_map_is_initialized()) {
//      destroy_placed_map();
//      destroy_tmap();
//    }
//
//    initworld(pstate);
//    
//    /* Create one big island for each player. */
//    for (i = Game.game.nplayers; i > 0; i--) {
//      if (!make_island(bigfrac * pstate.totalmass / totalweight,
//                      1, pstate, 95)) {
//	/* we couldn't make an island at least 95% as big as we wanted,
//	 * and since we're trying hard to be fair, we need to start again,
//	 * with all big islands reduced slightly in size.
//	 * Take the size reduction from the big islands and add it to the 
//	 * small islands to keep overall landmass unchanged.
//	 * Note that the big islands can get very small if necessary, and
//	 * the smaller islands will not exist if we can't place them 
//         * easily. */
//	util.freelog(Log.LOG_VERBOSE,
//		"Island too small, trying again with all smaller islands.\n");
//	midfrac += bigfrac * 0.01;
//	smallfrac += bigfrac * 0.04;
//	bigfrac *= 0.95;
//	done = false;	
//	break;
//      }
//    }
//  }
//
//  if (bigfrac <= midfrac) {
//    /* We could never make adequately big islands. */
//    util.freelog(Log.LOG_NORMAL, "Falling back to generator %d.", 1);
//    Map.map.generator = 1;
//
//    /* init world created this map, destroy it before abort */
//    destroy_placed_map();
//    free(height_map);
//    height_map = null;
//    return;
//  }
//
//  /* Now place smaller islands, but don't worry if they're small,
//   * or even non-existent. One medium and one small per player. */
//  for (i = Game.game.nplayers; i > 0; i--) {
//    make_island(midfrac * pstate.totalmass / totalweight, 0, pstate, DMSIS);
//  }
//  for (i = Game.game.nplayers; i > 0; i--) {
//    make_island(smallfrac * pstate.totalmass / totalweight, 0, pstate, DMSIS);
//  }
//
//  make_plains();  
//  destroy_placed_map();
//  free(height_map);
//  height_map = null;
//
//  if (checkmass > Map.map.xsize + Map.map.ysize + totalweight) {
//    util.freelog(Log.LOG_VERBOSE, "%ld mass left unplaced", checkmass);
//  }
//}
//
///**************************************************************************
//On popular demand, this tries to mimick the generator 3 as best as possible.
//**************************************************************************/
//static void mapgenerator3()
//{
//  int spares= 1;
//  int j=0;
//  
//  long int islandmass,landmass, size;
//  long int maxmassdiv6=20;
//  int bigislands;
//  struct gen234_state state;
//  gen234_state pstate = &state;
//
//  if ( Map.map.landpercent > 80) {
//    Map.map.generator = 2;
//    return;
//  }
//
//  pstate.totalmass =
//      ((Map.map.ysize - 6 - spares) * Map.map.landpercent * (Map.map.xsize - spares)) /
//      100;
//
//  bigislands= Game.game.nplayers;
//
//  landmass = (Map.map.xsize * (Map.map.ysize - 6) * Map.map.landpercent)/100;
//  /* subtracting the arctics */
//  if (landmass > 3 * Map.map.ysize + Game.game.nplayers * 3){
//    landmass -= 3 * Map.map.ysize;
//  }
//
//
//  islandmass= (landmass)/(3 * bigislands);
//  if (islandmass < 4 * maxmassdiv6) {
//    islandmass = (landmass)/(2 * bigislands);
//  }
//  if (islandmass < 3 * maxmassdiv6 && Game.game.nplayers * 2 < landmass) {
//    islandmass= (landmass)/(bigislands);
//  }
//
//  if (Map.map.xsize < 40 || Map.map.ysize < 40 || Map.map.landpercent > 80) { 
//    util.freelog(Log.LOG_NORMAL, "Falling back to generator %d.", 2); 
//    Map.map.generator = 2;
//    return; 
//  }
//
//  if (islandmass < 2) {
//    islandmass = 2;
//  }
//  if(islandmass > maxmassdiv6 * 6) {
//    islandmass = maxmassdiv6 * 6;/* !PS: let's try this */
//  }
//
//  initworld(pstate);
//
//  while (pstate.isleindex - 2 <= bigislands && checkmass > islandmass &&
//         ++j < 500) {
//    make_island(islandmass, 1, pstate, DMSIS);
//  }
//
//  if (j == 500){
//    util.freelog(Log.LOG_NORMAL, "Generator 3 didn't place all big islands.");
//  }
//  
//  islandmass= (islandmass * 11)/8;
//  /*!PS: I'd like to mult by 3/2, but starters might make trouble then*/
//  if (islandmass < 2) {
//    islandmass= 2;
//  }
//
//  while (pstate.isleindex <= MAP_NCONT - 20 && checkmass > islandmass &&
//         ++j < 1500) {
//      if (j < 1000) {
//	size = Rand.myrand((islandmass+1)/2+1)+islandmass/2;
//      } else {
//	size = Rand.myrand((islandmass+1)/2+1);
//      }
//      if (size < 2) {
//        size=2;
//      }
//
//      make_island(size, (pstate.isleindex - 2 <= Game.game.nplayers) ? 1 : 0,
//		  pstate, DMSIS);
//  }
//
//  make_plains();  
//  destroy_placed_map();
//  free(height_map);
//  height_map = null;
//    
//  if (j == 1500) {
//    util.freelog(Log.LOG_NORMAL, "Generator 3 left %li landmass unplaced.", checkmass);
//  } else if (checkmass > Map.map.xsize + Map.map.ysize) {
//    util.freelog(Log.LOG_VERBOSE, "%ld mass left unplaced", checkmass);
//  }
//}
//
///**************************************************************************
//...
//**************************************************************************/
//static void mapgenerator4()
//{
//  int bigweight=70;
//  int spares= 1;
//  int i;
//  long int totalweight;
//  struct gen234_state state;
//  gen234_state pstate = &state;
//
//
//  /* no islands with mass >> sqr(min(xsize,ysize)) */
//
//  if (Game.game.nplayers < 2 || Map.map.landpercent > 80) {
//    Map.map.startpos = 1;
//    return;
//  }
//
//  if (Map.map.landpercent > 60) {
//    bigweight=30;
//  } else if (Map.map.landpercent > 40) {
//    bigweight=50;
//  } else {
//    bigweight=70;
//  }
//
//  spares = (Map.map.landpercent - 5) / 30;
//
//  pstate.totalmass =
//      ((Map.map.ysize - 6 - spares) * Map.map.landpercent * (Map.map.xsize - spares)) /
//      100;
//
//  /*!PS: The weights NEED to sum up to totalweight (dammit) */
//  totalweight = (30 + bigweight) * Game.game.nplayers;
//
//  initworld(pstate);
//
//  i = Game.game.nplayers / 2;
//  if ((Game.game.nplayers % 2) == 1) {
//    make_island(bigweight * 3 * pstate.totalmass / totalweight, 3, 
//		pstate, DMSIS);
//  } else {
//    i++;
//  }
//  while ((--i) > 0) {
//    make_island(bigweight * 2 * pstate.totalmass / totalweight, 2,
//		pstate, DMSIS);
//  }
//  for (i = Game.game.nplayers; i > 0; i--) {
//    make_island(20 * pstate.totalmass / totalweight, 0, pstate, DMSIS);
//  }
//  for (i = Game.game.nplayers; i > 0; i--) {
//    make_island(10 * pstate.totalmass / totalweight, 0, pstate, DMSIS);
//  }
//  make_plains();  
//  destroy_placed_map();
//  free(height_map);
//  height_map = null;
//
//  if (checkmass > Map.map.xsize + Map.map.ysize + totalweight) {
//    util.freelog(Log.LOG_VERBOSE, "%ld mass left unplaced", checkmass);
//  }
//}
//
//#undef DMSIS
}