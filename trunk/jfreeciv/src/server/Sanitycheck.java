package server;

public class Sanitycheck{

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
//#include "city.h"
//#include "Game.game.h"
//#include "log.h"
//#include "Map.map.h"
//#include "player.h"
//#include "terrain.h"
//#include "unit.h"
//
//#include "citytools.h"
//#include "maphand.h"
//#include "sanitycheck.h"
//#include "unittools.h"
//
//#ifdef SANITY_CHECKING
//
///**************************************************************************
//...
//**************************************************************************/
//static void check_specials()
//{
//  for(tile ptile :  Map.map.tiles){
//    int terrain = ptile.terrain;
//    enum int special = map_get_special(ptile);
//
//    if (contains_special(special, Terrain_H.S_RAILROAD))
//      assert(contains_special(special, Terrain_H.S_ROAD));
//    if (contains_special(special, S_FARMLAND))
//      assert(contains_special(special, S_IRRIGATION));
//    if (contains_special(special, S_SPECIAL_1))
//      assert(!contains_special(special,  S_SPECIAL_2));
//
//    if (contains_special(special, S_MINE))
//      assert(get_tile_type(terrain).mining_result == terrain);
//    if (contains_special(special, S_IRRIGATION))
//      assert(get_tile_type(terrain).irrigation_result == terrain);
//
//    assert(terrain >= T_FIRST && terrain < T_COUNT);
//  }
//}
//
///**************************************************************************
//...
//**************************************************************************/
//static void check_fow()
//{
//  for(tile ptile :  Map.map.tiles){
//    for(player pplayer: Game.game.players){
//      player_tile plr_tile = map_get_player_tile(ptile, pplayer);
//      /* underflow of unsigned int */
//      assert(plr_tile.seen < 60000);
//      assert(plr_tile.own_seen < 60000);
//      assert(plr_tile.pending_seen < 60000);
//
//      assert(plr_tile.own_seen <= plr_tile.seen);
//      if (Maphand.map_is_known(ptile, pplayer)) {
//	assert(plr_tile.pending_seen == 0);
//      }
//    }
//  }
//}
//
///**************************************************************************
//...
//**************************************************************************/
//static void check_misc()
//{
//  int nbarbs = 0;
//  for(player pplayer: Game.game.players){
//    if (is_barbarian(pplayer)) {
//      nbarbs++;
//    }
//  }
//  assert(nbarbs == Game.game.nbarbarians);
//
//  assert(Game.game.nplayers <= Shared_H.MAX_NUM_PLAYERS + Shared_H.MAX_NUM_BARBARIANS);
//}
//
///**************************************************************************
//...
//**************************************************************************/
//static void check_map()
//{
//  for(tile ptile :  Map.map.tiles){
//    city pcity = map_get_city(ptile);
//    int cont = map_get_continent(ptile), x, y;
//
//    CHECK_INDEX(ptile.index);
//    CHECK_MAP_POS(ptile.x, ptile.y);
//    CHECK_NATIVE_POS(ptile.nat_x, ptile.nat_y);
//
//    index_to_map_pos(&x, &y, ptile.index);
//    assert(x == ptile.x && y == ptile.y);
//
//    index_to_native_pos(&x, &y, ptile.index);
//    assert(x == ptile.nat_x && y == ptile.nat_y);
//
//    if (Terrain_H.is_ocean(ptile.terrain)) {
//      assert(cont < 0);
//      for(tile tile1: util.adjc_tile_iterate(ptile)) {
//	if (Terrain_H.is_ocean(tile1.terrain)) {
//	  assert(map_get_continent(tile1) == cont);
//	}
//      }
//    } else {
//      assert(cont > 0);
//      for(tile tile1: util.adjc_tile_iterate(ptile)) {
//	if (!Terrain_H.is_ocean(tile1.terrain)) {
//	  assert(map_get_continent(tile1) == cont);
//	}
//      }
//    }
//
//    if (pcity) {
//      assert(Map.same_pos(pcity.tile, ptile));
//    }
//
//    for (unit punit : ptile.units.data) {
//      assert(Map.same_pos(punit.tile, ptile));
//
//      /* Check diplomatic status of stacked units. */
//      for (unit punit2 : ptile.units.data) {
//	assert(pplayers_allied(punit.unit_owner(), punit2.unit_owner()));
//      } }
//      if (pcity) {
//	assert(pplayers_allied(punit.unit_owner(), City.city_owner(pcity)));
//      }
//    } }
//  }
//}
//
///**************************************************************************
//  Verify that the city has sane values.
//**************************************************************************/
//void real_sanity_check_city(city pcity, final String file, int line)
//{
//  int workers = 0;
//  player pplayer = City.city_owner(pcity);
//
//  assert(pcity.size >= 1);
//  assert(!Terrain_H.terrain_has_flag(pcity.tile.terrain,
//			   TER_NO_CITIES));
//
//  for (unit punit : pcity.units_supported.data) {
//    assert(punit.homecity == pcity.id);
//    assert(punit.unit_owner() == pplayer);
//  } }
//
//  /* Note that cities may be found on land or water. */
//
//  city_map_iterate(x, y) {
//    tile ptile;
//
//    if ((ptile = city_map_to_map(pcity, x, y))) {
//      player owner = map_get_owner(ptile);
//
//      switch (get_worker_city(pcity, x, y)) {
//      case C_TILE_EMPTY:
//	if (ptile.worked) {
//	  util.freelog(Log.LOG_ERROR, "Tile at %s.%d,%d marked as " +
//		  "empty but worked by %s!",
//		  pcity.name, TILE_XY(ptile),
//		  (ptile).worked.name);
//	}
//	if (is_enemy_unit_tile(ptile, pplayer)) {
//	  util.freelog(Log.LOG_ERROR, "Tile at %s.%d,%d marked as " +
//		  "empty but occupied by an enemy unit!",
//		  pcity.name, TILE_XY(ptile));
//	}
//	if (Game.game.borders > 0
//	    && owner && owner.player_no != pcity.owner) {
//	  util.freelog(Log.LOG_ERROR, "Tile at %s.%d,%d marked as " +
//		  "empty but in enemy territory!",
//		  pcity.name, TILE_XY(ptile));
//	}
//	if (!city_can_work_tile(pcity, x, y)) {
//	  /* Complete check. */
//	  util.freelog(Log.LOG_ERROR, "Tile at %s.%d,%d marked as " +
//		  "empty but is unavailable!",
//		  pcity.name, TILE_XY(ptile));
//	}
//	break;
//      case C_TILE_WORKER:
//	if ((ptile).worked != pcity) {
//	  util.freelog(Log.LOG_ERROR, "Tile at %s.%d,%d marked as " +
//		  "worked but main map disagrees!",
//		  pcity.name, TILE_XY(ptile));
//	}
//	if (is_enemy_unit_tile(ptile, pplayer)) {
//	  util.freelog(Log.LOG_ERROR, "Tile at %s.%d,%d marked as " +
//		  "worked but occupied by an enemy unit!",
//		  pcity.name, TILE_XY(ptile));
//	}
//	if (Game.game.borders > 0
//	    && owner && owner.player_no != pcity.owner) {
//	  util.freelog(Log.LOG_ERROR, "Tile at %s.%d,%d marked as " +
//		  "worked but in enemy territory!",
//		  pcity.name, TILE_XY(ptile));
//	}
//	if (!city_can_work_tile(pcity, x, y)) {
//	  /* Complete check. */
//	  util.freelog(Log.LOG_ERROR, "Tile at %s.%d,%d marked as " +
//		  "worked but is unavailable!",
//		  pcity.name, TILE_XY(ptile));
//	}
//	break;
//      case C_TILE_UNAVAILABLE:
//	if (city_can_work_tile(pcity, x, y)) {
//	  util.freelog(Log.LOG_ERROR, "Tile at %s.%d,%d marked as " +
//		  "unavailable but seems to be available!",
//		  pcity.name, TILE_XY(ptile));
//	}
//	break;
//      }
//    } else {
//      assert(get_worker_city(pcity, x, y) == C_TILE_UNAVAILABLE);
//    }
//  } city_map_iterate_end;
//
//  /* Sanity check city size versus worker and specialist counts. */
//  city_map_iterate(x, y) {
//    if (get_worker_city(pcity, x, y) == C_TILE_WORKER) {
//      workers++;
//    }
//  } city_map_iterate_end;
//  if (workers + city_specialists(pcity) != pcity.size + 1) {
//    util.die("%s is illegal (size%d w%d e%d t%d s%d) in %s line %d",
//        pcity.name, pcity.size, workers, pcity.specialists[SP_ELVIS],
//        pcity.specialists[SP_TAXMAN], pcity.specialists[SP_SCIENTIST], file, line);
//  }
//}
//
///**************************************************************************
//...
//**************************************************************************/
//static void check_cities()
//{
//  for(player pplayer: Game.game.players){
//    for (city pcity : pplayer.cities.data) {
//      assert(City.city_owner(pcity) == pplayer);
//
//      sanity_check_city(pcity);
//    } }
//  }
//
//  for(tile ptile :  Map.map.tiles){
//    if (ptile.worked) {
//      city pcity = ptile.worked;
//      int city_x, city_y;
//      boolean is_valid;
//
//      is_valid = map_to_city_map(&city_x, &city_y, pcity, ptile);
//      assert(is_valid);
//
//      if (pcity.city_map[city_x][city_y] != C_TILE_WORKER) {
//	util.freelog(Log.LOG_ERROR, "%d,%d is listed as being worked by %s " +
//		"on the map, but %s lists the tile %d,%d as having " +
//		"status %d\n",
//		TILE_XY(ptile), pcity.name, pcity.name, city_x, city_y,
//		pcity.city_map[city_x][city_y]);
//      }
//    }
//  }
//}
//
///**************************************************************************
//...
//**************************************************************************/
//static void check_units() {
//  for(player pplayer: Game.game.players){
//    for (unit punit : pplayer.units.data) {
//      tile ptile = punit.tile;
//      city pcity;
//      unit transporter = null, *transporter2 = null;
//
//      assert(punit.unit_owner() == pplayer);
//
//      if (punit.homecity != 0) {
//	pcity = player_find_city_by_id(pplayer, punit.homecity);
//	assert(pcity != null);
//	assert(City.city_owner(pcity) == pplayer);
//      }
//
//      if (!can_unit_continue_current_activity(punit)) {
//	util.freelog(Log.LOG_ERROR, "%s at %d,%d (%s) has activity %s, " +
//		"which it can't continue!",
//		punit.unit_type().name,
//		TILE_XY(ptile), map_get_tile_info_text(ptile),
//		get_activity_text(punit.activity));
//      }
//
//      pcity = map_get_city(ptile);
//      if (pcity) {
//	assert(pplayers_allied(City.city_owner(pcity), pplayer));
//      }
//
//      assert(punit.moves_left >= 0);
//      assert(punit.hp > 0);
//
//      if (punit.transported_by != -1) {
//        transporter = find_unit_by_id(punit.transported_by);
//        assert(transporter != null);
//
//	/* Make sure the transporter is on the tile. */
//	for (unit tile_unit : punit.tile.units.data) {
//	  if (tile_unit == transporter) {
//	    transporter2 = tile_unit;
//	  }
//	} }
//	assert(transporter2 != null);
//
//        /* Also in the list of owner? */
//        assert(player_find_unit_by_id(get_player(transporter.owner),
//				      punit.transported_by) != null);
//        assert(Map.same_pos(ptile, transporter.tile));
//
//        /* Transporter capacity will be checked when transporter itself
//	 * is checked */
//      }
//
//      /* Check for ground units in the ocean. */
//      if (!pcity
//	  && Terrain_H.is_ocean(ptile.terrain)
//	  && is_ground_unit(punit)) {
//        assert(punit.transported_by != -1);
//        assert(!is_ground_unit(transporter));
//        assert(is_ground_units_transport(transporter));
//      } else if (!pcity
//                 && !Terrain_H.is_ocean(ptile.terrain)
//	         && is_sailing_unit(punit)) {
//        assert(punit.transported_by != -1);
//        assert(!is_sailing_unit(transporter));
//        assert(false); /* assert(is_sailing_units_transport(transporter)); */
//      }
//
//      /* Check for over-full transports. */
//      assert(get_transporter_occupancy(punit)
//	     <= Unit.get_transporter_capacity(punit));
//    } }
//  }
//}
//
///**************************************************************************
//...
//**************************************************************************/
//static void check_players()
//{
//  int player_no;
//
//  for(player pplayer: Game.game.players){
//    int found_palace = 0;
//
//    if (!pplayer.is_alive) {
//      /* Don't do these checks.  Note there are some dead-players
//       * sanity checks below. */
//      continue;
//    }
//
//    for (city pcity : pplayer.cities.data) {
//      if (pcity.is_capital()) {
//	found_palace++;
//      }
//      assert(found_palace <= 1);
//    } }
//
//    for(player pplayer2: Game.game.players){
//      assert(pplayer.diplstates[pplayer2.player_no].type
//	     == pplayer2.diplstates[pplayer.player_no].type);
//      if (pplayer.diplstates[pplayer2.player_no].type == DS_CEASEFIRE) {
//	assert(pplayer.diplstates[pplayer2.player_no].turns_left
//	       == pplayer2.diplstates[pplayer.player_no].turns_left);
//      }
//    }
//
//    if (pplayer.revolution_finishes == -1) {
//      if (pplayer.government == Game.game.government_when_anarchy) {
//        util.freelog(LOG_FATAL, "%s's government is anarchy but does not finish",
//                pplayer.name);
//      }
//      assert(pplayer.government != Game.game.government_when_anarchy);
//    } else if (pplayer.revolution_finishes > Game.game.turn) {
//      assert(pplayer.government == Game.game.government_when_anarchy);
//    } else {
//      /* Things may vary in this case depending on when the sanity_check
//       * call is made.  No better check is possible. */
//    }
//  }
//
//  /* Sanity checks on living and dead players. */
//  for (player_no = 0; player_no < ARRAY_SIZE(Game.game.players); player_no++) {
//    player pplayer = &Game.game.players[player_no];
//
//    if (!pplayer.is_alive) {
//      /* Dead players' units and cities are disbanded in kill_player(). */
//      assert(pplayer.units.foo_list_size() == 0);
//      assert(pplayer.cities.foo_list_size() == 0);
//    }
//
//    /* Dying players shouldn't be left around.  But they are. */
//    assert(!pplayer.is_dying);
//  }
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void sanity_check()
//{
//  if (!map_is_empty()) {
//    /* Don't sanity-check the map if it hasn't been created yet (this
//     * happens when loading scenarios). */
//    check_specials();
//    check_map();
//    check_cities();
//    check_units();
//    check_fow();
//  }
//  check_misc();
//  check_players();
//}
//
//#endif /* SANITY_CHECKING */
}