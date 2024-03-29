package server;

import port.util;
import server.maphand.player_tile;
import utility.Speclists;

import common.Connection;
import common.Game;
import common.city.city;
import common.map.tile;
import common.player.player;

public class Maphand{

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
//
//#include "events.h"
//#include "fcintl.h"
//#include "Game.game.h"
//#include "log.h"
//#include "Map.map.h"
//#include "mem.h"
//#include "nation.h"
//#include "packets.h"
//#include "rand.h"
//#include "support.h"
//#include "unit.h"
//
//#include "citytools.h"
//#include "cityturn.h"
//#include "plrhand.h"           /* Plrhand.notify_player */
//#include "sernet.h"
//#include "srv_main.h"
//#include "unithand.h"
//#include "unittools.h"
//
//#include "maphand.h"
//
//#define MAXIMUM_CLAIMED_OCEAN_SIZE (20)
//
///* Continent which is adjacent to a given ocean. -1 if the ocean is surrounded
//   by more than one continent */
//static Continent_id lake_surrounders[MAP_NCONT];
///* size of a given continent in tiles */
//static int continent_sizes[MAP_NCONT];
///* size of a given ocean in tiles */
//static int ocean_sizes[MAP_NCONT];
//
///**************************************************************************
//  Number this tile and nearby tiles (recursively) with the specified
//  continent number nr, using a flood-fill algorithm.
//
//  is_land tells us whether we are assigning continent numbers or ocean 
//  numbers.
//
//  if skip_unsafe is specified then "unsafe" terrains are skipped.  This
//  is useful for mapgen algorithms.
//**************************************************************************/
//static void assign_continent_flood(tile ptile, boolean is_land,
//				   int nr, boolean skip_unsafe)
//{
//  if (map_get_continent(ptile) != 0) {
//    return;
//  }
//  
//  if (skip_unsafe && Terrain_H.terrain_has_flag(ptile.terrain, TER_UNSAFE)) {
//    /* FIXME: This should check a specialized flag, not the TER_UNSAFE
//     * flag which may not even be present. */
//    return;
//  }
//
//  if (!XOR(is_land, Terrain_H.is_ocean(ptile.terrain))) {
//    return;
//  }
//
//  map_set_continent(ptile, nr);
//  
//  /* count the tile */
//  if (nr < 0) {
//    ocean_sizes[-nr]++;
//  } else {
//    continent_sizes[nr]++;
//  }
//
//  for(tile tile1: util.adjc_tile_iterate(ptile)) {
//    assign_continent_flood(tile1, is_land, nr, skip_unsafe);
//  }
//}
//
///**************************************************************************
//  Calculate lake_surrounders[] array
//**************************************************************************/
//static void recalculate_lake_surrounders()
//{
//  int i;
//
//  for (i = 1; i <= Map.map.num_oceans; i++) {
//    lake_surrounders[i] = 0;
//  }
//  
//  for(tile ptile :  Map.map.tiles){
//    Continent_id cont = map_get_continent(ptile);
//    if (!Terrain_H.is_ocean(ptile.terrain)) {
//      for(tile tile2: util.adjc_tile_iterate(ptile)) {
//        Continent_id cont2 = map_get_continent(tile2);
//	if (Terrain_H.is_ocean(tile2.terrain)) {
//	  if (lake_surrounders[-cont2] == 0) {
//	    lake_surrounders[-cont2] = cont;
//	  } else if (lake_surrounders[-cont2] != cont) {
//	    lake_surrounders[-cont2] = -1;
//	  }
//	}
//      }
//    }
//  }
//}
//
///**************************************************************************
//  Assigns continent and ocean numbers to all tiles, and set
//  Map.map.num_continents and Map.map.num_oceans.  Recalculates continent and
//  ocean sizes, and lake_surrounders[] arrays.
//
//  Continents have numbers 1 to Map.map.num_continents _inclusive_.
//  Oceans have (negative) numbers -1 to -Map.map.num_oceans _inclusive_.
//
//  If skip_unsafe is specified then unsafe terrains are not used to
//  connect continents.  This is useful for generator code so that polar
//  regions don't connect landmasses.
//**************************************************************************/
//void assign_continent_numbers(boolean skip_unsafe)
//{
//  int i;
//  
//  /* reset ocean/continent counters */
//  for (i = 0; i < MAP_NCONT; i++) {
//    ocean_sizes[i] = 0;
//    continent_sizes[i] = 0;
//  }
//  
//  /* Initialize */
//  Map.map.num_continents = 0;
//  Map.map.num_oceans = 0;
//
//  for(tile ptile :  Map.map.tiles){
//    map_set_continent(ptile, 0);
//  }
//
//  /* Assign new numbers */
//  for(tile ptile :  Map.map.tiles){
//    final int ter = ptile.terrain;
//
//    if (map_get_continent(ptile) != 0) {
//      /* Already assigned. */
//      continue;
//    }
//
//    if (!skip_unsafe || !Terrain_H.terrain_has_flag(ter, TER_UNSAFE)) {
//      if (!Terrain_H.is_ocean(ter)) {
//	Map.map.num_continents++;
//	assert(Map.map.num_continents < MAP_NCONT);
//	assign_continent_flood(ptile, true, Map.map.num_continents, skip_unsafe);
//      } else {
//	Map.map.num_oceans++;
//	assert(Map.map.num_oceans < MAP_NCONT);
//	assign_continent_flood(ptile, false, -Map.map.num_oceans, skip_unsafe);
//      }
//    }
//  }
//
//  recalculate_lake_surrounders();
//
//  util.freelog(Log.LOG_VERBOSE, "Map has %d continents and %d oceans", 
//	  Map.map.num_continents, Map.map.num_oceans);
//}
//
///**************************************************************************
//Used only in global_warming() and nuclear_winter() below.
//**************************************************************************/
//static boolean is_terrain_ecologically_wet(tile ptile)
//{
//  return (Map.map_has_special(ptile, Terrain_H.S_RIVER)
//	  || Terrain.is_terrain_flag_near_tile(ptile)
//	  || is_special_near_tile(ptile, Terrain_H.S_RIVER));
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void global_warming(int effect)
//{
//  int k;
//
//  util.freelog(Log.LOG_VERBOSE, "Global warming: %d", Game.game.heating);
//
//  k = Map.map.map_num_tiles();
//  while(effect > 0 && (k--) > 0) {
//    int old, new;
//    tile ptile;
//
//    ptile = Map.rand_map_pos();
//    old = ptile.terrain;
//    if (is_terrain_ecologically_wet(ptile)) {
//      new = get_tile_type(old).warmer_wetter_result;
//    } else {
//      new = get_tile_type(old).warmer_drier_result;
//    }
//    if (new != Terrain_H.T_NONE && old != new) {
//      effect--;
//      change_terrain(ptile, new);
//      update_tile_knowledge(ptile);
//      for (unit punit : ptile.units.data) {
//	if (!Unit.can_unit_continue_current_activity(punit)) {
//	  Unithand.handle_unit_activity_request(punit, unit_activity.ACTIVITY_IDLE);
//	}
//      } }
//    } else if (old == new) {
//      /* This counts toward warming although nothing is changed. */
//      effect--;
//    }
//  }
//
//  Plrhand.notify_player_ex(null, null, E_GLOBAL_ECO,
//		   "Game: Global warming has occurred!");
//  Plrhand.notify_player(null, ("Game: Coastlines have been flooded and vast " +
//			"ranges of grassland have become deserts."));
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void nuclear_winter(int effect)
//{
//  int k;
//
//  util.freelog(Log.LOG_VERBOSE, "Nuclear winter: %d", Game.game.cooling);
//
//  k = Map.map.map_num_tiles();
//  while(effect > 0 && (k--) > 0) {
//    int old, new;
//    tile ptile;
//
//    ptile = Map.rand_map_pos();
//    old = ptile.terrain;
//    if (is_terrain_ecologically_wet(ptile)) {
//      new = get_tile_type(old).cooler_wetter_result;
//    } else {
//      new = get_tile_type(old).cooler_drier_result;
//    }
//    if (new != Terrain_H.T_NONE && old != new) {
//      effect--;
//      change_terrain(ptile, new);
//      update_tile_knowledge(ptile);
//      for (unit punit : ptile.units.data) {
//	if (!Unit.can_unit_continue_current_activity(punit)) {
//	  Unithand.handle_unit_activity_request(punit, unit_activity.ACTIVITY_IDLE);
//	}
//      } }
//    } else if (old == new) {
//      /* This counts toward winter although nothing is changed. */
//      effect--;
//    }
//  }
//
//  Plrhand.notify_player_ex(null, null, E_GLOBAL_ECO,
//		   "Game: Nuclear winter has occurred!");
//  Plrhand.notify_player(null, ("Game: Wetlands have dried up and vast " +
//			"ranges of grassland have become tundra."));
//}
//
///***************************************************************
//To be called when a player gains the Railroad tech for the first
//time.  Sends a message, and then upgrade all city squares to
//railroads.  "discovery" just affects the message: set to
//   1 if the tech is a "discovery",
//   0 if otherwise acquired (conquer/trade/GLib).        --dwp
//***************************************************************/
//void upgrade_city_rails(player pplayer, boolean discovery)
//{
//  if (!(Map.terrain_control.may_road)) {
//    return;
//  }
//
//  Connection.conn_list_do_buffer(&pplayer.connections);
//
//  if (discovery) {
//    Plrhand.notify_player(pplayer,
//		  ("Game: New hope sweeps like fire through the country as " +
//		    "the discovery of railroad is announced.\n" +
//		    "      Workers spontaneously gather and upgrade all " +
//		    "cities with railroads."));
//  } else {
//    Plrhand.notify_player(pplayer,
//		  ("Game: The people are pleased to hear that your " +
//		    "scientists finally know about railroads.\n" +
//		    "      Workers spontaneously gather and upgrade all " +
//		    "cities with railroads."));
//  }
//  
//  for (city pcity : pplayer.cities.data) {
//    Map.map_set_special(pcity.tile, Terrain_H.S_RAILROAD);
//    update_tile_knowledge(pcity.tile);
//  }
//  }
//
//  Connection.conn_list_do_unbuffer(&pplayer.connections);
//}
//
/**************************************************************************
Return true iff the player me really gives shared vision to player them.
**************************************************************************/
static boolean really_gives_vision(player me, player them)
{
//  return TEST_BIT(me.really_gives_vision, them.player_no);
	return false;
}

/**************************************************************************
...
**************************************************************************/
static void buffer_shared_vision(player pplayer)
{
  for(player pplayer2: Game.game.players){
    if (really_gives_vision(pplayer, pplayer2))
      Connection.conn_list_do_buffer(pplayer2.connections);
  }
  Connection.conn_list_do_buffer(pplayer.connections);
}

/**************************************************************************
...
 **************************************************************************/
static void unbuffer_shared_vision(player pplayer)
{
	for(player pplayer2: Game.game.players){
		if (really_gives_vision(pplayer, pplayer2))
			Connection.conn_list_do_unbuffer(pplayer2.connections);
	}
	Connection.conn_list_do_unbuffer(pplayer.connections);
}

///**************************************************************************
//...
//**************************************************************************/
//void give_map_from_player_to_player(player pfrom, player pdest)
//{
//  buffer_shared_vision(pdest);
//  for(tile ptile :  Map.map.tiles){
//    give_tile_info_from_player_to_player(pfrom, pdest, ptile);
//  }
//  unbuffer_shared_vision(pdest);
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void give_seamap_from_player_to_player(player pfrom, player pdest)
//{
//  buffer_shared_vision(pdest);
//  for(tile ptile :  Map.map.tiles){
//    if (Terrain_H.is_ocean(ptile.terrain)) {
//      give_tile_info_from_player_to_player(pfrom, pdest, ptile);
//    }
//  }
//  unbuffer_shared_vision(pdest);
//}
//
///**************************************************************************
//...
//**************************************************************************/
public static void give_citymap_from_player_to_player(city pcity,
					player pfrom, player pdest)
{
//  buffer_shared_vision(pdest);
//  map_city_radius_iterate(pcity.tile, ptile) {
//    give_tile_info_from_player_to_player(pfrom, pdest, ptile);
//  } map_city_radius_iterate_end;
//  unbuffer_shared_vision(pdest);
}
//
///**************************************************************************
//  Send all tiles known to specified clients.
//  If dest is null means Game.game.game_connections.
//  
//  Note for multiple connections this may change "sent" multiple times
//  for single player.  This is ok, because "sent" data is just optimised
//  calculations, so it will be correct before this, for each connection
//  during this, and at end.
//**************************************************************************/
//void send_all_known_tiles(Speclists<Connection> dest)
//{
//  int tiles_sent;
//
//  if (dest==null) dest = &Game.game.game_connections;
//
//  /* send whole map piece by piece to each player to balance the load
//     of the send buffers better */
//  tiles_sent = 0;
//  Connection.conn_list_do_buffer(dest);
//
//  for(tile ptile :  Map.map.tiles){
//    tiles_sent++;
//    if ((tiles_sent % Map.map.xsize) == 0) {
//      Connection.conn_list_do_unbuffer(dest);
//      Sernet.flush_packets();
//      Connection.conn_list_do_buffer(dest);
//    }
//
//    conn_list_iterate(*dest, pconn) {
//      player pplayer = pconn.player;
//
//      if (!pplayer && !pconn.observer) {	/* no map needed */
//        continue;
//      }
//
//      if (!pplayer || map_is_known(ptile, pplayer)) {
//	send_tile_info_always(pplayer, &pconn.self, ptile);
//      }
//    } }
//  }
//
//  Connection.conn_list_do_unbuffer(dest);
//  Sernet.flush_packets();
//}
//
///**************************************************************************
//  Send tile information to all the clients in dest which know and see
//  the tile. If dest is null, sends to all clients (Game.game.game_connections)
//  which know and see tile.
//
//  Note that this function does not update the playermap.  For that call
//  update_tile_knowledge().
//**************************************************************************/
static void send_tile_info(Speclists<Connection> dest, tile ptile)
{
//  struct packet_tile_info info;
//
//  if (dest==null) dest = &Game.game.game_connections;
//
//  info.x = ptile.x;
//  info.y = ptile.y;
//  info.owner = ptile.owner ? ptile.owner.player_no : MAP_TILE_OWNER_NULL;
//  if (ptile.spec_sprite) {
//    info.spec_sprite = ptile.spec_sprite;
//  } else {
//    info.spec_sprite[0] = '\0';
//  }
//
//  conn_list_iterate(*dest, pconn) {
//    player pplayer = pconn.player;
//    if (!pplayer && !pconn.observer) {
//      continue;
//    }
//    if (!pplayer || map_is_known_and_seen(ptile, pplayer)) {
//      info.known = TILE_KNOWN;
//      info.type = ptile.terrain;
//      info.special = ptile.special;
//      info.continent = ptile.continent;
//      send_packet_tile_info(pconn, &info);
//    } else if (pplayer && map_is_known(ptile, pplayer)
//	       && map_get_seen(ptile, pplayer) == 0) {
//      /* Just update the owner */
//      player_tile plrtile = map_get_player_tile(ptile, pplayer);
//      info.known = TILE_KNOWN_FOGGED;
//      info.type = plrtile.terrain;
//      info.special = plrtile.special;
//      info.continent = ptile.continent;
//      send_packet_tile_info(pconn, &info);
//    }
//  }
//  
}
//
///**************************************************************************
//  Send the tile information, as viewed by pplayer, to all specified
//  connections.   The tile info is sent even if pplayer doesn't see or
//  know the tile (setting appropriate info.known), as required for
//  client drawing requirements in some cases (see doc/HACKING).  This function
//  does NOT update player knowledge; call update_player_tile_knowledge to
//  do that.
//  pplayer==null means send "real" data, for observers
//**************************************************************************/
//static void send_tile_info_always(player pplayer, Speclists<Connection> dest,
//			   tile ptile)
//{
//  struct packet_tile_info info;
//  player_tile plrtile;
//
//  info.x = ptile.x;
//  info.y = ptile.y;
//  info.owner = ptile.owner ? ptile.owner.player_no : MAP_TILE_OWNER_NULL;
//  if (ptile.spec_sprite) {
//    info.spec_sprite = ptile.spec_sprite;
//  } else {
//    info.spec_sprite[0] = '\0';
//  }
//
//  if (!pplayer) {
//    /* Observer sees all. */
//    info.known=TILE_KNOWN;
//    info.type = ptile.terrain;
//    info.special = ptile.special;
//    info.continent = ptile.continent;
//  } else if (map_is_known(ptile, pplayer)) {
//    if (map_get_seen(ptile, pplayer) != 0) {
//      /* Known and seen. */
//      info.known = TILE_KNOWN;
//    } else {
//      /* Known but not seen. */
//      info.known = TILE_KNOWN_FOGGED;
//    }
//    plrtile = map_get_player_tile(ptile, pplayer);
//    info.type = plrtile.terrain;
//    info.special = plrtile.special;
//    info.continent = ptile.continent;
//  } else {
//    /* Unknown (the client needs these sometimes to draw correctly). */
//    info.known = TILE_UNKNOWN;
//    info.type = ptile.terrain;
//    info.special = ptile.special;
//    info.continent = ptile.continent;
//  }
//  lsend_packet_tile_info(dest, &info);
//}
//
///**************************************************************************
//...
//**************************************************************************/
//static int map_get_pending_seen(player pplayer, tile ptile)
//{
//  return map_get_player_tile(ptile, pplayer).pending_seen;
//}
//
///**************************************************************************
//...
//**************************************************************************/
//static void map_set_pending_seen(player pplayer, tile ptile, int newv)
//{
//  map_get_player_tile(ptile, pplayer).pending_seen = newv;
//}
//
///**************************************************************************
//...
//**************************************************************************/
//static void increment_pending_seen(player pplayer, tile ptile)
//{
//  map_get_player_tile(ptile, pplayer).pending_seen += 1;
//}
//
/**************************************************************************
...
 **************************************************************************/
static void decrement_pending_seen(player pplayer, tile ptile)
{
	player_tile plr_tile = map_get_player_tile(ptile, pplayer);
	assert(plr_tile.pending_seen != 0);
	plr_tile.pending_seen -= 1;
}

///**************************************************************************
//...
//**************************************************************************/
//static void reveal_pending_seen(player pplayer, tile ptile, int len)
//{
//  for(tile tile1: util.square_tile_iterate(ptile, len)) {
//    int pseen = map_get_pending_seen(pplayer, tile1);
//    map_set_pending_seen(pplayer, tile1, 0);
//    while (pseen > 0) {
//      unfog_area(pplayer, tile1, 0);
//      pseen--;
//    }
//  }
//}
//
///*************************************************************************
// * Checks for hidden units around (x,y).  Such units can be invisible even
// * on a KNOWN_AND_SEEN tile, so unfogging might not reveal them.
// ************************************************************************/
//void reveal_hidden_units(player pplayer, tile ptile)
//{
//  for(tile tile1: util.adjc_tile_iterate(ptile)) {
//    for (unit punit : tile1.units.data) {
//      if (is_hiding_unit(punit)) {
//        /* send_unit_info will check whether it is visible */
//        Unittools.send_unit_info(pplayer, punit);
//      }
//    } }
//  }
//}
//
///*************************************************************************
//  Checks for hidden units around (x,y).  Such units can be invisible even
//  on a KNOWN_AND_SEEN tile, so fogging might not hide them.
//
//  Note, this must be called after the unit/vision source at ptile has
//  been removed, unlike remove_unit_sight_points.
//************************************************************************/
//void conceal_hidden_units(player pplayer, tile ptile)
//{
//  /* Remove vision of submarines.  This is extremely ugly and inefficient. */
//  for(tile tile1: util.adjc_tile_iterate(ptile)) {
//    for (unit phidden_unit : tile1.units.data) {
//      if (phidden_unit.transported_by == -1
//	  && is_hiding_unit(phidden_unit)) {
//	for(player pplayer2: Game.game.players){
//	  if ((pplayer2 == pplayer || really_gives_vision(pplayer, pplayer2))
//	      && !can_player_see_unit(pplayer2, phidden_unit)) {
//	    unit_goes_out_of_sight(pplayer2, phidden_unit);
//	  }
//	}
//      }
//    } }
//  }
//}
//
///**************************************************************************
//...
//**************************************************************************/
//static void really_unfog_area(player pplayer, tile ptile)
//{
//  city pcity;
//  boolean old_known = map_is_known(ptile, pplayer);
//
//  util.freelog(Log.LOG_DEBUG, "really unfogging %d,%d\n", TILE_XY(ptile));
//
//  map_set_known(ptile, pplayer);
//
//  /* send info about the tile itself 
//   * It has to be sent first because the client needs correct
//   * continent number before it can handle following packets
//   */
//  update_player_tile_knowledge(pplayer, ptile);
//  send_tile_info_always(pplayer, &pplayer.connections, ptile);
//
//  /* discover units */
//  unit_list_iterate(ptile.units, punit)
//    Unittools.send_unit_info(pplayer, punit);
//  }
//
//  /* discover cities */ 
//  reality_check_city(pplayer, ptile);
//  if ((pcity=Map.map_get_city(ptile)))
//    Citytools.send_city_info(pplayer, pcity);
//
//  /* If the tile was not known before we need to refresh the cities that
//     can use the tile. */
//  if (!old_known) {
//    map_city_radius_iterate(ptile, tile1) {
//      pcity = Map.map_get_city(tile1);
//      if (pcity && City.city_owner(pcity) == pplayer) {
//	update_city_tile_status_map(pcity, ptile);
//      }
//    } map_city_radius_iterate_end;
//    Citytools.sync_cities();
//  }
//}
//
///**************************************************************************
//  Add an extra point of visibility to a square centered at x,y with
//  sidelength 1+2*len, ie length 1 is normal sightrange for a unit.
//  pplayer may not be null.
//**************************************************************************/
public static void unfog_area(player pplayer, tile ptile, int len)
{
//  /* Did the tile just become visible?
//     - send info about units and cities and the tile itself */
//  buffer_shared_vision(pplayer);
//  for(tile tile1: util.square_tile_iterate(ptile, len)) {
//    /* the player himself */
//    shared_vision_change_seen(tile1, pplayer, +1);
//    if (map_get_seen(tile1, pplayer) == 1
//	|| !map_is_known(tile1, pplayer)) {
//      really_unfog_area(pplayer, tile1);
//    }
//
//    /* players (s)he gives shared vision */
//    for(player pplayer2: Game.game.players){
//      if (!really_gives_vision(pplayer, pplayer2)) {
//	continue;
//      }
//
//      if (map_get_seen(tile1, pplayer2) == 1
//	  || !map_is_known(tile1, pplayer2)) {
//	really_unfog_area(pplayer2, tile1);
//      }
//      reveal_pending_seen(pplayer2, tile1, 0);
//    }
//  }
//
//  reveal_pending_seen(pplayer, ptile, len);
//  unbuffer_shared_vision(pplayer);
}

/**************************************************************************
...
**************************************************************************/
static void really_fog_area(player pplayer, tile ptile)
{
//  util.freelog(Log.LOG_DEBUG, "Fogging %i,%i. Previous fog: %i.",
//	  TILE_XY(ptile), map_get_seen(ptile, pplayer));
// 
//  assert(map_get_seen(ptile, pplayer) == 0);
//
//  for(unit punit:ptile.units.data ){
//    unit_goes_out_of_sight(pplayer,punit);
//  }  
//
//  update_player_tile_last_seen(pplayer, ptile);
//  send_tile_info_always(pplayer, pplayer.connections, ptile);
}

/**************************************************************************
  Remove a point of visibility from a square centered at x,y with
  sidelength 1+2*len, ie length 1 is normal sightrange for a unit.
**************************************************************************/
public static void fog_area(player pplayer, tile ptile, int len)
{
  buffer_shared_vision(pplayer);
  for(tile tile1: util.square_tile_iterate(ptile, len)) {
    if (map_is_known(tile1, pplayer)) {
      /* the player himself */
      shared_vision_change_seen(tile1, pplayer, -1);
      if (map_get_seen(tile1, pplayer) == 0) {
	really_fog_area(pplayer, tile1);
      }

      /* players (s)he gives shared vision */
      for(player pplayer2: Game.game.players){
	if (!really_gives_vision(pplayer, pplayer2)) {
	  continue;
	}
	if (map_get_seen(tile1, pplayer2) == 0) {
	  really_fog_area(pplayer2, tile1);
	}
      }
    } else {
      decrement_pending_seen(pplayer, tile1);
    }
  }
  unbuffer_shared_vision(pplayer);
}

///**************************************************************************
//  Send basic map information: map size, topology, and is_earth.
//**************************************************************************/
//void send_map_info(Speclists<Connection> dest)
//{
//  struct packet_map_info minfo;
//
//  minfo.xsize=Map.map.xsize;
//  minfo.ysize=Map.map.ysize;
//  minfo.topology_id = Map.map.topology_id;
// 
//  lsend_packet_map_info(dest, &minfo);
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void map_fog_city_area(city pcity)
//{
//  if (!pcity) {
//    util.freelog(Log.LOG_ERROR, "Attempting to fog non-existent city");
//    return;
//  }
//
//  map_fog_pseudo_city_area(City.city_owner(pcity), pcity.tile);
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void map_unfog_city_area(city pcity)
//{
//  if (!pcity) {
//    util.freelog(Log.LOG_ERROR, "Attempting to unfog non-existent city");
//    return;
//  }
//
//  map_unfog_pseudo_city_area(City.city_owner(pcity), pcity.tile);
//}
//
/**************************************************************************
...
**************************************************************************/
static void shared_vision_change_seen(tile ptile, player pplayer, int change)
{
//  map_change_seen(ptile, pplayer, change);
//  map_change_own_seen(ptile, pplayer, change);
//
//  for(player pplayer2: Game.game.players){
//    if (really_gives_vision(pplayer, pplayer2))
//      map_change_seen(ptile, pplayer2, change);
//  }
}

/**************************************************************************
There doesn't have to be a city.
**************************************************************************/
static void map_unfog_pseudo_city_area(player pplayer, tile ptile)
{
//  util.freelog(Log.LOG_DEBUG, "Unfogging city area at %i,%i", TILE_XY(ptile));

  buffer_shared_vision(pplayer);
//  map_city_radius_iterate(ptile, tile1) {
//    if (map_is_known(tile1, pplayer)) {
//      unfog_area(pplayer, tile1, 0);
//    } else {
//      increment_pending_seen(pplayer, tile1);
//    }
//  } map_city_radius_iterate_end;
  unbuffer_shared_vision(pplayer);
}
//
///**************************************************************************
//There doesn't have to be a city.
//**************************************************************************/
static void map_fog_pseudo_city_area(player pplayer, tile ptile)
{
//  util.freelog(Log.LOG_DEBUG, "Fogging city area at %i,%i", TILE_XY(ptile));
//
//  buffer_shared_vision(pplayer);
//  map_city_radius_iterate(ptile, tile1) {
//    if (map_is_known(tile1, pplayer)) {
//      fog_area(pplayer, tile1, 0);
//    } else {
//      decrement_pending_seen(pplayer, tile1);
//    }
//  } map_city_radius_iterate_end;
//  unbuffer_shared_vision(pplayer);
}
//
///**************************************************************************
//For removing a unit. The actual removal is done in server_remove_unit
//**************************************************************************/
//void remove_unit_sight_points(unit punit)
//{
//  tile ptile = punit.tile;
//  player pplayer = punit.unit_owner();
//
//  util.freelog(Log.LOG_DEBUG, "Removing unit sight points at  %i,%i",
//	  TILE_XY(punit.tile));
//
//  if (Map.map_has_special(punit.tile, Terrain_H.S_FORTRESS)
//      && unit_profits_of_watchtower(punit))
//    fog_area(pplayer, ptile, Unittools.get_watchtower_vision(punit));
//  else
//    fog_area(pplayer, ptile, punit.unit_type().vision_range);
//}
//
///**************************************************************************
//Shows area even if still fogged. If the tile is not "seen" units are not
//shown
//**************************************************************************/
//static void really_show_area(player pplayer, tile ptile)
//{
//  city pcity;
//  boolean old_known = map_is_known(ptile, pplayer);
//
//  util.freelog(Log.LOG_DEBUG, "Showing %i,%i", TILE_XY(ptile));
//
//  if (!map_is_known_and_seen(ptile, pplayer)) {
//    map_set_known(ptile, pplayer);
//
//    /* as the tile may be fogged send_tile_info won't always do this for us */
//    update_player_tile_knowledge(pplayer, ptile);
//    update_player_tile_last_seen(pplayer, ptile);
//
//    send_tile_info_always(pplayer, &pplayer.connections, ptile);
//
//    /* remove old cities that exist no more */
//    reality_check_city(pplayer, ptile);
//    if ((pcity = Map.map_get_city(ptile))) {
//      /* as the tile may be fogged Citytools.send_city_info won't do this for us */
//      update_dumb_city(pplayer, pcity);
//      Citytools.send_city_info(pplayer, pcity);
//    }
//
//    if (map_get_seen(ptile, pplayer) != 0) {
//      unit_list_iterate(ptile.units, punit)
//	Unittools.send_unit_info(pplayer, punit);
//      }
//    }
//
//    /* If the tile was not known before we need to refresh the cities that
//       can use the tile. */
//    if (!old_known) {
//      map_city_radius_iterate(ptile, tile1) {
//	pcity = Map.map_get_city(tile1);
//	if (pcity && City.city_owner(pcity) == pplayer) {
//	  update_city_tile_status_map(pcity, ptile);
//	}
//      } map_city_radius_iterate_end;
//      Citytools.sync_cities();
//    }
//  }
//}

/**************************************************************************
Shows area, ie send terrain etc., even if still fogged, sans units and cities.
**************************************************************************/
public static void show_area(player pplayer, tile ptile, int len)
{
//  buffer_shared_vision(pplayer);
//  for(tile tile1: util.square_tile_iterate(ptile, len)) {
//    /* the player himself */
//    really_show_area(pplayer, tile1);
//
//    /* players (s)he gives shared vision */
//    for(player pplayer2: Game.game.players){
//      if (really_gives_vision(pplayer, pplayer2)) {
//	really_show_area(pplayer2, tile1);
//	reveal_pending_seen(pplayer2, tile1, 0);
//      }
//    }
//  }
//
//  reveal_pending_seen(pplayer, ptile, len);
//  unbuffer_shared_vision(pplayer);
}

/***************************************************************
...
***************************************************************/
public static boolean map_is_known(final tile ptile, player pplayer)
{
//  return TEST_BIT(ptile.known, pplayer.player_no);
  return false;
}

/***************************************************************
...
***************************************************************/
public static boolean map_is_known_and_seen(final tile ptile, player pplayer)
{
//  return TEST_BIT(ptile.known, pplayer.player_no)
//      && ((pplayer.private_map + ptile.index).seen != 0);
	return false;
}
//
/////***************************************************************
////Watch out - this can be true even if the tile is not known.
////***************************************************************/
static int map_get_seen(final tile ptile, player pplayer)
{
  return map_get_player_tile(ptile, pplayer).seen;
}

/***************************************************************
...
***************************************************************/
//void map_change_seen(tile ptile, player pplayer, int change)
//{
//  map_get_player_tile(ptile, pplayer).seen += change;
//  util.freelog(Log.LOG_DEBUG, "%d,%d, p: %d, change %d, result %d\n", TILE_XY(ptile),
//	  pplayer.player_no, change, map_get_player_tile(ptile,
//							 pplayer).seen);
//}
//
///***************************************************************
//...
//***************************************************************/
//static int map_get_own_seen(tile ptile, player pplayer)
//{
//  int own_seen = map_get_player_tile(ptile, pplayer).own_seen;
//  if (own_seen != 0) {
//    assert(map_is_known(ptile, pplayer));
//  }
//  return own_seen;
//}
//
///***************************************************************
//...
//***************************************************************/
//static void map_change_own_seen(tile ptile, player pplayer,
//				int change)
//{
//  map_get_player_tile(ptile, pplayer).own_seen += change;
//}
//
///***************************************************************
//...
//***************************************************************/
//void map_set_known(tile ptile, player pplayer)
//{
//  ptile.known |= (1u<<pplayer.player_no);
//}

	/***************************************************************
...
	 ***************************************************************/
	public static void map_clear_known(tile ptile, player pplayer)
	{
		ptile.known &= ~(1<<pplayer.player_no);
	}

///***************************************************************
//...
//***************************************************************/
//void map_know_all(player pplayer)
//{
//  for(tile ptile :  Map.map.tiles){
//    show_area(pplayer, ptile, 0);
//  }
//}
//
///***************************************************************
//...
//***************************************************************/
//void map_know_and_see_all(player pplayer)
//{
//  for(tile ptile :  Map.map.tiles){
//    unfog_area(pplayer, ptile, 0);
//  }
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void show_map_to_all()
//{
//  for(player pplayer: Game.game.players){
//    map_know_and_see_all(pplayer);
//  }
//}
//
///***************************************************************
//  Allocate space for map, and initialise the tiles.
//  Uses current Map.map.xsize and Map.map.ysize.
//****************************************************************/
//void player_map_allocate(player pplayer)
//{
//  pplayer.private_map =
//    fc_malloc(Map.map.xsize*Map.map.ysize*sizeof(struct player_tile));
//  for(tile ptile :  Map.map.tiles){
//    player_tile_init(ptile, pplayer);
//  }
//}
//
///***************************************************************
// frees a player's private Map.map.
//***************************************************************/
//void player_map_free(player pplayer)
//{
//  if (!pplayer.private_map) {
//    return;
//  }
//
//  for(tile ptile :  Map.map.tiles){
//    player_tile plrtile = map_get_player_tile(ptile, pplayer);
//
//    if (plrtile.city) {
//      free(plrtile.city);
//    }
//  }
//
//  free(pplayer.private_map);
//  pplayer.private_map = null;
//}
//
///***************************************************************
//We need to use use fogofwar_old here, so the player's tiles get
//in the same state as the other players' tiles.
//***************************************************************/
//static void player_tile_init(tile ptile, player pplayer)
//{
//  player_tile plrtile =
//    map_get_player_tile(ptile, pplayer);
//
//  plrtile.terrain = T_UNKNOWN;
//  plrtile.special = S_NO_SPECIAL;
//  plrtile.city = null;
//
//  plrtile.seen = 0;
//  plrtile.pending_seen = 0;
//  if (!Game.game.fogofwar_old) {
//    if (map_is_known(ptile, pplayer)) {
//      plrtile.seen = 1;
//    } else {
//      plrtile.pending_seen = 1;
//    }
//  }
//
//  plrtile.last_updated = GAME_START_YEAR;
//  plrtile.own_seen = plrtile.seen;
//}
// 
///***************************************************************
//...
//***************************************************************/
public static player_tile map_get_player_tile(final tile ptile,
					player pplayer)
{
//  return pplayer.private_map + ptile.index;
	return null;
}
//
///****************************************************************************
//  Give pplayer the correct knowledge about tile; return true iff
//  knowledge changed.
//
//  Note that unlike update_tile_knowledge, this function will not send any
//  packets to the client.  Callers may want to call send_tile_info() if this
//  function returns true.
//****************************************************************************/
//boolean update_player_tile_knowledge(player pplayer, tile ptile)
//{
//  player_tile plrtile = map_get_player_tile(ptile, pplayer);
//
//  if (plrtile.terrain != ptile.terrain
//      || plrtile.special != ptile.special) {
//    plrtile.terrain = ptile.terrain;
//    plrtile.special = ptile.special;
//    return true;
//  }
//  return false;
//}

	/****************************************************************************
  Update playermap knowledge for everybody who sees the tile, and send a
  packet to everyone whose info is changed.

  Note this only checks for changing of the terrain or special for the
  tile, since these are the only values held in the playermap.
	 ****************************************************************************/
	public static void update_tile_knowledge(tile ptile)
	{
		/* Players */
		for(player pplayer: Game.game.players){
//			if (map_is_known_and_seen(ptile, pplayer)) {
//				if (update_player_tile_knowledge(pplayer, ptile)) {
//					send_tile_info(&pplayer.connections, ptile);
//				}
//			}
		}

		/* Global observers */
		for (Connection pconn : Game.game.game_connections.data) {
			player pplayer = pconn.player;
			if (null==pplayer && pconn.observer) {
//				send_tile_info(&pconn.self, ptile);
			}
		} 
}

///***************************************************************
//...
//***************************************************************/
//void update_player_tile_last_seen(player pplayer, tile ptile)
//{
//  map_get_player_tile(ptile, pplayer).last_updated = Game.game.year;
//}
//
///***************************************************************
//...
//***************************************************************/
//static void really_give_tile_info_from_player_to_player(player pfrom,
//							player pdest,
//							tile ptile)
//{
//  player_tile from_tile, *dest_tile;
//  if (!map_is_known_and_seen(ptile, pdest)) {
//    /* I can just hear people scream as they try to comprehend this if :).
//     * Let me try in words:
//     * 1) if the tile is seen by pfrom the info is sent to pdest
//     *  OR
//     * 2) if the tile is known by pfrom AND (he has more recent info
//     *     OR it is not known by pdest)
//     */
//    if (map_is_known_and_seen(ptile, pfrom)
//	|| (map_is_known(ptile, pfrom)
//	    && (((map_get_player_tile(ptile, pfrom).last_updated
//		 > map_get_player_tile(ptile, pdest).last_updated))
//	        || !map_is_known(ptile, pdest)))) {
//      from_tile = map_get_player_tile(ptile, pfrom);
//      dest_tile = map_get_player_tile(ptile, pdest);
//      /* Update and send tile knowledge */
//      map_set_known(ptile, pdest);
//      dest_tile.terrain = from_tile.terrain;
//      dest_tile.special = from_tile.special;
//      dest_tile.last_updated = from_tile.last_updated;
//      send_tile_info_always(pdest, &pdest.connections, ptile);
//	
//      /* update and send city knowledge */
//      /* remove outdated cities */
//      if (dest_tile.city) {
//	if (!from_tile.city) {
//	  /* As the city was gone on the newer from_tile
//	     it will be removed by this function */
//	  reality_check_city(pdest, ptile);
//	} else /* We have a dest_city. update */
//	  if (from_tile.city.id != dest_tile.city.id)
//	    /* As the city was gone on the newer from_tile
//	       it will be removed by this function */
//	    reality_check_city(pdest, ptile);
//      }
//      /* Set and send new city info */
//      if (from_tile.city) {
//	if (!dest_tile.city) {
//	  dest_tile.city = fc_malloc(sizeof(struct dumb_city));
//	}
//	*dest_tile.city = *from_tile.city;
//	Citytools.send_city_info_at_tile(pdest, &pdest.connections, null, ptile);
//      }
//
//      reveal_pending_seen(pdest, ptile, 0);
//
//      map_city_radius_iterate(ptile, tile1) {
//	city pcity = Map.map_get_city(tile1);
//	if (pcity && City.city_owner(pcity) == pdest) {
//	  update_city_tile_status_map(pcity, ptile);
//	}
//      } map_city_radius_iterate_end;
//      Citytools.sync_cities();
//    }
//  }
//}
//
///***************************************************************
//...
//***************************************************************/
//static void give_tile_info_from_player_to_player(player pfrom,
//						 player pdest,
//						 tile ptile)
//{
//  really_give_tile_info_from_player_to_player(pfrom, pdest, ptile);
//
//  for(player pplayer2: Game.game.players){
//    if (!really_gives_vision(pdest, pplayer2))
//      continue;
//    really_give_tile_info_from_player_to_player(pfrom, pplayer2, ptile);
//  }
//}
//
///***************************************************************
//This updates all players' really_gives_vision field.
//If p1 gives p2 shared vision and p2 gives p3 shared vision p1
//should also give p3 shared vision.
//***************************************************************/
//static void create_vision_dependencies()
//{
//  int added;
//
//  for(player pplayer: Game.game.players){
//    pplayer.really_gives_vision = pplayer.gives_shared_vision;
//  }
//
//  /* In words: This terminates when it has run a round without adding
//     a dependency. One loop only propagates dependencies one level deep,
//     which is why we keep doing it as long as changes occur. */
//  do {
//    added = 0;
//    for(player pplayer: Game.game.players){
//      for(player pplayer2: Game.game.players){
//	if (really_gives_vision(pplayer, pplayer2)
//	    && pplayer != pplayer2) {
//	  for(player pplayer3: Game.game.players){
//	    if (really_gives_vision(pplayer2, pplayer3)
//		&& !really_gives_vision(pplayer, pplayer3)
//		&& pplayer != pplayer3) {
//	      pplayer.really_gives_vision |= (1<<pplayer3.player_no);
//	      added++;
//	    }
//	  }
//	}
//      }
//    }
//  } while (added > 0);
//}
//
///***************************************************************
//...
//***************************************************************/
//void give_shared_vision(player pfrom, player pto)
//{
//  int save_vision[Shared_H.MAX_NUM_PLAYERS+Shared_H.MAX_NUM_BARBARIANS];
//  if (pfrom == pto) return;
//  if (gives_shared_vision(pfrom, pto)) {
//    util.freelog(Log.LOG_ERROR, "Trying to give shared vision from %s to %s, " +
//	    "but that vision is already given!",
//	    pfrom.name, pto.name);
//    return;
//  }
//
//  for(player pplayer: Game.game.players){
//    save_vision[pplayer.player_no] = pplayer.really_gives_vision;
//  }
//
//  pfrom.gives_shared_vision |= 1<<pto.player_no;
//  create_vision_dependencies();
//  util.freelog(Log.LOG_DEBUG, "giving shared vision from %s to %s\n",
//	  pfrom.name, pto.name);
//
//  for(player pplayer: Game.game.players){
//    buffer_shared_vision(pplayer);
//    for(player pplayer2: Game.game.players){
//      if (really_gives_vision(pplayer, pplayer2)
//	  && !TEST_BIT(save_vision[pplayer.player_no], pplayer2.player_no)) {
//	util.freelog(Log.LOG_DEBUG, "really giving shared vision from %s to %s\n",
//	       pplayer.name, pplayer2.name);
//	for(tile ptile :  Map.map.tiles){
//	  int change = map_get_own_seen(ptile, pplayer);
//	  if (change != 0) {
//	    map_change_seen(ptile, pplayer2, change);
//	    if (map_get_seen(ptile, pplayer2) == change) {
//	      really_unfog_area(pplayer2, ptile);
//	      reveal_pending_seen(pplayer2, ptile, 0);
//	    }
//	  }
//	}
//
//	/* squares that are not seen, but which pfrom may have more recent
//	   knowledge of */
//	give_map_from_player_to_player(pplayer, pplayer2);
//      }
//    }
//    unbuffer_shared_vision(pplayer);
//  }
//
//  if (Srv_main.server_state == server_states.RUN_GAME_STATE)
//    Plrhand.send_player_info(pfrom, null);
//}
//
///***************************************************************
//...
//***************************************************************/
//void remove_shared_vision(player pfrom, player pto)
//{
//  int save_vision[Shared_H.MAX_NUM_PLAYERS+Shared_H.MAX_NUM_BARBARIANS];
//  assert(pfrom != pto);
//  if (!gives_shared_vision(pfrom, pto)) {
//    util.freelog(Log.LOG_ERROR, "Tried removing the shared vision from %s to %s, " +
//	    "but it did not exist in the first place!",
//	    pfrom.name, pto.name);
//    return;
//  }
//
//  for(player pplayer: Game.game.players){
//    save_vision[pplayer.player_no] = pplayer.really_gives_vision;
//  }
//
//  util.freelog(Log.LOG_DEBUG, "removing shared vision from %s to %s\n",
//	 pfrom.name, pto.name);
//
//  pfrom.gives_shared_vision &= ~(1<<pto.player_no);
//  create_vision_dependencies();
//
//  for(player pplayer: Game.game.players){
//    buffer_shared_vision(pplayer);
//    for(player pplayer2: Game.game.players){
//      if (!really_gives_vision(pplayer, pplayer2)
//	  && TEST_BIT(save_vision[pplayer.player_no], pplayer2.player_no)) {
//	util.freelog(Log.LOG_DEBUG, "really removing shared vision from %s to %s\n",
//	       pplayer.name, pplayer2.name);
//	for(tile ptile :  Map.map.tiles){
//	  int change = map_get_own_seen(ptile, pplayer);
//	  if (change > 0) {
//	    map_change_seen(ptile, pplayer2, -change);
//	    if (map_get_seen(ptile, pplayer2) == 0)
//	      really_fog_area(pplayer2, ptile);
//	  }
//	}
//      }
//    }
//    unbuffer_shared_vision(pplayer);
//  }
//
//  if (Srv_main.server_state == server_states.RUN_GAME_STATE) {
//    Plrhand.send_player_info(pfrom, null);
//  }
//}
//
///*************************************************************************
//...
//*************************************************************************/
//enum known_type map_get_known(final tile ptile,
//			      player pplayer)
//{
//  if (map_is_known(ptile, pplayer)) {
//    if (map_get_seen(ptile, pplayer) > 0) {
//      return TILE_KNOWN;
//    } else {
//      return TILE_KNOWN_FOGGED;
//    }
//  } else {
//    return TILE_UNKNOWN;
//  }
//}
//
///*************************************************************************
//...
//*************************************************************************/
//static void enable_fog_of_war_player(player pplayer)
//{
//  for(tile ptile :  Map.map.tiles){
//    if (map_is_known(ptile, pplayer)) {
//      fog_area(pplayer, ptile, 0);
//    } else {
//      decrement_pending_seen(pplayer, ptile);
//    }
//  }
//}
//
///*************************************************************************
//...
//*************************************************************************/
//void enable_fog_of_war()
//{
//  for(player pplayer: Game.game.players){
//    enable_fog_of_war_player(pplayer);
//  }
//}
//
///*************************************************************************
//...
//*************************************************************************/
//static void disable_fog_of_war_player(player pplayer)
//{
//  for(tile ptile :  Map.map.tiles){
//    if (map_is_known(ptile, pplayer)) {
//      unfog_area(pplayer, ptile, 0);
//    } else {
//      increment_pending_seen(pplayer, ptile);
//    }
//  }
//}
//
///*************************************************************************
//...
//*************************************************************************/
//void disable_fog_of_war()
//{
//  for(player pplayer: Game.game.players){
//    disable_fog_of_war_player(pplayer);
//  }
//}
//
///**************************************************************************
//  Set the tile to be a river if required.
//  It's required if one of the tiles nearby would otherwise be part of a
//  river to nowhere.
//  For simplicity, I'm assuming that this is the only exit of the river,
//  so I don't need to trace it across the continent.  --CJM
//  Also, note that this only works for R_AS_SPECIAL type rivers.  --jjm
//**************************************************************************/
//static void ocean_to_land_fix_rivers(tile ptile)
//{
//  /* clear the river if it exists */
//  Map.map_clear_special(ptile, Terrain_H.S_RIVER);
//
//  cardinal_for(tile tile1: util.adjc_tile_iterate(ptile)) {
//    if (Map.map_has_special(tile1, Terrain_H.S_RIVER)) {
//      boolean ocean_near = false;
//      cardinal_for(tile tile2: util.adjc_tile_iterate(tile1)) {
//        if (Terrain_H.is_ocean(tile2.terrain))
//          ocean_near = true;
//      } cardinal_adjc_iterate_end;
//      if (!ocean_near) {
//        Map.map_set_special(ptile, Terrain_H.S_RIVER);
//        return;
//      }
//    }
//  } cardinal_adjc_iterate_end;
//}
//
///**************************************************************************
//  Checks for terrain change between ocean and land.  Handles side-effects.
//  (Should be called after any potential ocean/land terrain changes.)
//  Also, returns an enum ocean_land_change, describing the change, if any.
//
//  if we did a land change, we try to avoid reassigning
//  continent numbers.
//**************************************************************************/
//enum ocean_land_change check_terrain_ocean_land_change(tile ptile,
//                                                int oldter)
//{
//  int newter = ptile.terrain;
//  enum ocean_land_change change_type = OLC_NONE;
//
//  if (Terrain_H.is_ocean(oldter) && !Terrain_H.is_ocean(newter)) {
//    /* ocean to land ... */
//    ocean_to_land_fix_rivers(ptile);
//    city_landlocked_sell_coastal_improvements(ptile);
//
//    change_type = OLC_OCEAN_TO_LAND;
//  } else if (!Terrain_H.is_ocean(oldter) && Terrain_H.is_ocean(newter)) {
//    /* land to ocean ... */
//    change_type = OLC_LAND_TO_OCEAN;
//  }
//
//  if (change_type != OLC_NONE) {
//    assign_continent_numbers(false);
//    allot_island_improvs();
//
//    /* New continent numbers for all tiles to all players */
//    send_all_known_tiles(null);
//    
//    map_update_borders_landmass_change(ptile);
//  }
//
//  return change_type;
//}
//
///*************************************************************************
//  Return pointer to the oldest adjacent city to this tile.  If
//  there is a city on the exact tile, that is returned instead.
//*************************************************************************/
//static city map_get_adjc_city(tile ptile)
//{
//  city closest = null;   /* Closest city */
//
//  if (ptile.city) {
//    return ptile.city;
//  }
//
//  for(tile tile1: util.adjc_tile_iterate(ptile)) {
//    if (tile1.city && 
//         (!closest || tile1.city.turn_founded < closest.turn_founded)) {
//      closest = tile1.city;
//    }
//  }
//
//  return closest;
//}
//
///*************************************************************************
//  Ocean tile can be claimed iff one of the following conditions stands:
//  a) it is an inland lake not larger than MAXIMUM_OCEAN_SIZE
//  b) it is adjacent to only one continent and not more than two ocean tiles
//  c) It is one tile away from a city (This function doesn't check it)
//  The city, which claims the ocean has to be placed on the correct continent.
//  in case a) The continent which surrounds the inland lake
//  in case b) The only continent which is adjacent to the tile
//  The correct continent is returned in *contp.
//*************************************************************************/
//static boolean is_claimed_ocean(tile ptile, Continent_id *contp)
//{
//  Continent_id cont = map_get_continent(ptile);
//  Continent_id cont2, other;
//  int ocean_tiles;
//  
//  if (get_ocean_size(-cont) <= MAXIMUM_CLAIMED_OCEAN_SIZE &&
//      lake_surrounders[-cont] > 0) {
//    *contp = lake_surrounders[-cont];
//    return true;
//  }
//  
//  other = 0;
//  ocean_tiles = 0;
//  for(tile tile2: util.adjc_tile_iterate(ptile)) {
//    cont2 = map_get_continent(tile2);
//    if (cont2 == cont) {
//      ocean_tiles++;
//    } else {
//      if (other == 0) {
//        other = cont2;
//      } else if (other != cont2) {
//        return false;
//      }
//    }
//  }
//  if (ocean_tiles <= 2) {
//    *contp = other;
//    return true;
//  } else {
//    return false;
//  }
//}
//
///*************************************************************************
//  Return pointer to the closest city to this tile, which must be
//  on the same continent if the city is not immediately adjacent.
//  If two or more cities are equally distant, then return the
//  oldest (i.e. the one with the lowest id). This also correctly
//  works for water bases in SMAC mode, and allows coastal cities
//  to claim one square of ocean. Inland lakes and long inlets act in
//  the same way as the surrounding continent's land tiles. If no cities
//  are within Game.game.borders distance, returns null.
//
//  NOTE: The behaviour of this function will eventually depend
//  upon some planned ruleset options.
//*************************************************************************/
//static city map_get_closest_city(tile ptile)
//{
//  city closest;  /* Closest city */
//
//  closest = map_get_adjc_city(ptile);
//  if (!closest) {
//    int distsq;		/* Squared distance to city */
//    /* integer arithmetic equivalent of (borders+0.5)**2 */
//    int cldistsq = Game.game.borders * (Game.game.borders + 1);
//    Continent_id cont = map_get_continent(ptile);
//
//    if (!Terrain_H.is_ocean(ptile.terrain) || is_claimed_ocean(ptile, &cont)) {
//      cities_iterate(pcity) {
//	if (map_get_continent(pcity.tile) == cont) {
//          distsq = sq_map_distance(pcity.tile, ptile);
//          if (distsq < cldistsq ||
//               (distsq == cldistsq &&
//                (!closest || closest.turn_founded > pcity.turn_founded))) {
//            closest = pcity;
//            cldistsq = distsq;
//          } 
//        }
//      } cities_iterate_end;
//    }
//  }
//
//  return closest;
//}
//
///*************************************************************************
//  Update tile worker states for all cities that have the given map tile
//  within their radius. Does not sync with client.
//
//  This function is inefficient and so should only be called when the
//  owner actually changes.
//*************************************************************************/
//static void tile_update_owner(tile ptile)
//{
//  /* This implementation is horribly inefficient, but this doesn't cause
//   * problems since it's not called often. */
//  cities_iterate(pcity) {
//    update_city_tile_status_map(pcity, ptile);
//  } cities_iterate_end;
//}
//
///*************************************************************************
//  Recalculate the borders around a given position.
//*************************************************************************/
//static void map_update_borders_recalculate_position(tile ptile)
//{
//  Speclists<city> cities_to_refresh;
//  
//  if (Game.game.happyborders > 0) {
//    city_list_init(&cities_to_refresh);
//  }
//  
//  if (Game.game.borders > 0) {
//    iterate_outward(ptile, Game.game.borders, tile1) {
//      city pccity = map_get_closest_city(tile1);
//      player new_owner = pccity ? get_player(pccity.owner) : null;
//
//      if (new_owner != map_get_owner(tile1)) {
//	map_set_owner(tile1, new_owner);
//	/* Note we call send_tile_info, not update_tile_knowledge here.
//	 * Borders information is sent to everyone who has seen the tile
//	 * before; it's not stored in the playermap. */
//	send_tile_info(null, tile1);
//	tile_update_owner(tile1);
//	/* Update happiness */
//	if (Game.game.happyborders > 0) {
//	  for (unit unit : tile1.units.data) {
//	    struct city* homecity = Game.find_city_by_id(unit.homecity);
//	    boolean already_listed = false;
//	    
//	    if (!homecity) {
//	      continue;
//	    }
//	    
//	    for (city city2 : cities_to_refresh.data) {
//	      if (city2 == homecity) {
//	        already_listed = true;
//		break;
//	      }
//	    } }
//	    
//	    if (!already_listed) {
//	      &cities_to_refresh.foo_list_insert(homecity);
//	    }
//
//	  } }
//	}
//      }
//    } iterate_outward_end;
//  }
// 
//  /* Update happiness in all homecities we have collected */ 
//  if (Game.game.happyborders > 0) {
//    for (city to_refresh : cities_to_refresh.data) {
//      Cityturn.city_refresh(to_refresh);
//      Citytools.send_city_info(City.city_owner(to_refresh), to_refresh);
//    } }
//    
//    city_list_unlink_all(&cities_to_refresh);
//  }
//}
//
///*************************************************************************
//  Modify national territories as resulting from a city being destroyed.
//  x,y coords for (already deleted) city's location.
//  Tile worker states are updated as necessary, but not sync'd with client.
//*************************************************************************/
public static void map_update_borders_city_destroyed(tile ptile)
{
//  map_update_borders_recalculate_position(ptile);
}
//
///*************************************************************************
//  Modify national territories resulting from a change of landmass.
//  Tile worker states are updated as necessary, but not sync'd with client.
//*************************************************************************/
//void map_update_borders_landmass_change(tile ptile)
//{
//  map_update_borders_recalculate_position(ptile);
//}
//
///*************************************************************************
//  Modify national territories resulting from new city or change of city
//  ownership.
//  Tile worker states are updated as necessary, but not sync'd with client.
//*************************************************************************/
static void map_update_borders_city_change(city pcity)
{
//  map_update_borders_recalculate_position(pcity.tile);
}
//
///*************************************************************************
//  Delete the territorial claims to all tiles.
//*************************************************************************/
//static void map_clear_borders()
//{
//  for(tile ptile :  Map.map.tiles){
//    map_set_owner(ptile, null);
//  }
//}
//
///*************************************************************************
//  Minimal code that calculates all national territories from scratch.
//*************************************************************************/
//static void map_calculate_territory()
//{
//  /* Clear any old territorial claims. */
//  map_clear_borders();
//
//  if (Game.game.borders > 0) {
//    /* Loop over all cities and claim territory. */
//    cities_iterate(pcity) {
//      /* Loop over all map tiles within this city's sphere of influence. */
//      iterate_outward(pcity.tile, Game.game.borders, ptile) {
//	city pccity = map_get_closest_city(ptile);
//
//	if (pccity) {
//	  map_set_owner(ptile, get_player(pccity.owner));
//	}
//      } iterate_outward_end;
//    } cities_iterate_end;
//  }
//}
//
///*************************************************************************
//  Calculate all national territories from scratch.  This can be slow, but
//  is only performed occasionally, i.e. after loading a saved Game.game. Doesn't
//  send any tile information to the clients. Tile worker states are updated
//  as necessary, but not sync'd with client.
//*************************************************************************/
//void map_calculate_borders()
//{
//  if (Game.game.borders > 0) {
//    map_calculate_territory();
//
//    /* Fix tile worker states. */
//    cities_iterate(pcity) {
//      map_city_radius_iterate(pcity.tile, tile1) {
//        update_city_tile_status_map(pcity, tile1);
//      } map_city_radius_iterate_end;
//    } cities_iterate_end;
//  }
//}
//
///*************************************************************************
//  Return size in tiles of the given continent(not ocean)
//*************************************************************************/
//int get_continent_size(Continent_id id)
//{
//  assert(id > 0);
//  return continent_sizes[id];
//}
//
///*************************************************************************
//  Return size in tiles of the given ocean. You should use positive ocean
//  number.
//*************************************************************************/
//int get_ocean_size(Continent_id id) 
//{
//  assert(id > 0);
//  return ocean_sizes[id];
//}
}