package common.aicore;

public class Aisupport{
///**********************************************************************
// Freeciv - Copyright (C) 2003 - The Freeciv Project
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
//#include "city.h"
//#include "Game.game.h"
//#include "Map.map.h"
//#include "player.h"
//#include "shared.h"
//#include "spaceship.h"
//#include "support.h"
//#include "tech.h"
//
//#include "aisupport.h"
//
///**********************************************************************
//  Find who is leading the space race. Returns null if nobody is.
//***********************************************************************/
//player player_leading_spacerace()
//{
//  player best = null;
//  int best_arrival = FC_INFINITY;
//  enum spaceship_state best_state = spaceship_state.SSHIP_NONE;
//
//  if (Game.game.spacerace == false) {
//    return null;
//  }
//
//  for(player pplayer: Game.game.players){
//    player_spaceship ship = &pplayer.spaceship;
//    int arrival = (int) ship.travel_time + ship.launch_year;
//
//    if (!pplayer.is_alive || is_barbarian(pplayer)
//        || ship.state == spaceship_state.SSHIP_NONE) {
//      continue;
//    }
//
//    if (ship.state != spaceship_state.SSHIP_LAUNCHED
//        && ship.state > best_state) {
//      best_state = ship.state;
//      best = pplayer;
//    } else if (ship.state == spaceship_state.SSHIP_LAUNCHED
//               && arrival < best_arrival) {
//      best_state = ship.state;
//      best_arrival = arrival;
//      best = pplayer;
//    }
//  }
//
//  return best;
//}
//
///**********************************************************************
//  Calculate average distances to other players. We calculate the 
//  average distance from all of our cities to the closest enemy city.
//***********************************************************************/
//int player_distance_to_player(player pplayer, player target)
//{
//  int cities = 0;
//  int dists = 0;
//
//  if (pplayer == target
//      || !target.is_alive
//      || !pplayer.is_alive
//      || pplayer.cities.foo_list_size() == 0
//      || target.cities.foo_list_size() == 0) {
//    return 1;
//  }
//
//  /* For all our cities, find the closest distance to an enemy city. */
//  for (city pcity : pplayer.cities.data) {
//    int min_dist = FC_INFINITY;
//
//    for (city c2 : target.cities.data) {
//      int dist = Map.real_map_distance(c2.tile, pcity.tile);
//
//      if (min_dist > dist) {
//        min_dist = dist;
//      }
//    } }
//    dists += min_dist;
//    cities++;
//  } }
//
//  return MAX(dists / cities, 1);
//}
//
///**********************************************************************
//  Rough calculation of the worth of pcity in gold.
//***********************************************************************/
//int city_gold_worth(city pcity)
//{
//  int worth;
//
//  worth = pcity.size * 150; /* reasonable base cost */
//  for (unit punit : pcity.units_supported.data) {
//    if (Map.same_pos(punit.tile, pcity.tile)) {
//      int id = punit.unit_type().obsoleted_by;
//
//      if (id >= 0 && City.can_build_unit_direct(pcity, id)) {
//        worth += unit_disband_shields(punit.type) / 2; /* obsolete */
//      } else {
//        worth += unit_disband_shields(punit.type); /* good stuff */
//      }
//    }
//  } }
	//for (int i = 0; i < Game.game.num_impr_types; i++) {
//	if((pcity).improvements[i] == Improvement.I_NONE) {
//		continue;
//	}
//    if (Improvement.improvement_types[i].Improvement.is_wonder && !wonder_obsolete(i)) {
//      worth += Improvement.impr_sell_gold(i);
//   } else {
//      worth += Improvement.impr_sell_gold(i) / 4;
//    }
//  } ;
//  if (City.city_unhappy(pcity)) {
//    worth *= 0.75;
//  }
//  return worth;
//}
}