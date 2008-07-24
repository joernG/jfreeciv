package server.generator;

public class Temperature_map{

//   Copyright (C) 2004 - Marcelo J. Burda
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
//#include "Map.map.h"
//
//#include "height_map.h"
//#include "temperature_map.h"
//#include "mapgen_topology.h"
//#include "utilities.h" 
//
//static int *temperature_map;
//
//#define tmap(ptile) (temperature_map[(ptile).index])
//
///**************************************************************
//  Return true if temperateure_map is initialized
//**************************************************************/
//boolean temperature_is_initialized()
//{
//  return temperature_map != null;
//}
///*********************************************************
// return true if the tile has tt temperature type
//**********************************************************/
//boolean tmap_is(final tile ptile, temperature_type tt)
//{
//  return BOOL_VAL(tmap(ptile) & (tt));
//}
//
///*****************************************************************
// return true if at last one tile has tt temperature type
//****************************************************************/
//boolean is_temperature_type_near(final tile ptile, temperature_type tt) 
//{
//  for(tile tile1: util.adjc_tile_iterate(ptile)) {
//    if (BOOL_VAL(tmap(tile1) & (tt))) {
//      return true;
//    };
//  }
//  return false;
//}
//
///**************************************************************************** 
//   Free the tmap
// ****************************************************************************/
//void destroy_tmap()
//{
//  assert(temperature_map != null);
//  free(temperature_map);
//  temperature_map = null;
//}
//
///***************************************************************************
// * create_tmap initialize the temperature_map
// * if arg is false, create a dumy tmap == map_colattitude
// * to be used if hmap or oceans are not placed gen 2-4
// ***************************************************************************/
//void create_tmap(boolean real)
//{
//  int i;
//
//  /* if map is defined this is not changed */
//  /* TO DO load if from scenario Game.game with tmap */
//  assert(temperature_map == null); /* to debug, never load a this time */
//  if (temperature_map != null) {
//    return;
//  }
//
//  temperature_map = fc_malloc(sizeof(int) * Map_H.MAX_MAP_INDEX);
//  for(tile ptile :  Map.map.tiles){
//  
//     /* the base temperature is equal to base map_colatitude */
//    int t = map_colatitude(ptile);
//    if (!real) {
//      tmap(ptile) = t;
//    } else {
//      /* height land can be 30% collest */
//      float height = - 0.3 * MAX(0, hmap(ptile) - hmap_shore_level) 
//	  / (hmap_max_level - hmap_shore_level); 
//      /* near ocean temperature can be 15 % more "temperate" */
//      float temperate = 0.15 * (Map.map.temperature / 100 - t / MAX_COLATITUDE) * 
//	  2 * Math.min (50 ,count_ocean_near_tile(ptile, false, true)) /
//	  100;
//      
//      tmap(ptile) =  t * (1.0 + temperate) * (1.0 + height);
//    }
//  }
//  /* adjust to get well sizes frequencies */
//  /* Notice: if colatitude is load from a scenario never call adjust has
//             scenario maybe has a odd colatitude ditribution and adjust will
//	     brack it */
//  if (!Map.map.alltemperate) {
//    adjust_int_map(temperature_map, MAX_COLATITUDE);
//  }
//  /* now simplify to 4 base values */ 
//  for (i = 0; i < Map_H.MAX_MAP_INDEX; i++) {
//    int t = temperature_map[i];
//
//    if (t >= TROPICAL_LEVEL) {
//      temperature_map[i] = TT_TROPICAL;
//    } else if (t >= COLD_LEVEL) {
//      temperature_map[i] = TT_TEMPERATE;
//    } else if (t >= 2 * ICE_BASE_LEVEL) {
//      temperature_map[i] = TT_COLD;
//    } else {
//      temperature_map[i] = TT_FROZEN;
//    }
//  } 
//}
}