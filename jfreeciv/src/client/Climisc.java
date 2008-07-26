package client;

public class Climisc{

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

//This module contains various general - mostly highlevel - functions
//used throughout the client.
//***********************************************************************/
//
//#ifdef HAVE_CONFIG_H
//#include <config.h>
//#endif
//
//#include <assert.h>
//#include <stdarg.h>
//#include <stdio.h>
//#include <stdlib.h>
//#include <string.h>
//
//#include "city.h"
//#include "diptreaty.h"
//#include "fcintl.h"
//#include "Game.game.h"
//#include "log.h"
//#include "map.h"
//#include "packets.h"
//#include "spaceship.h"
//#include "support.h"
//
//#include "chatline_g.h"
//#include "citydlg_g.h"
//#include "cityrep_g.h"
//#include "civclient.h"
//#include "climap.h"
//#include "clinet.h"
//#include "control.h"
//#include "dialogs_g.h"
//#include "mapview_g.h"
//#include "messagewin_common.h"
//#include "packhand.h"
//#include "plrdlg_common.h"
//#include "repodlgs_common.h"
//#include "tilespec.h"
//
//#include "climisc.h"
//
///**************************************************************************
//...
//**************************************************************************/
//void client_remove_player(int plrno)
//{
//  game_remove_player(get_player(plrno));
//  game_renumber_players(plrno);
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void client_remove_unit(unit punit)
//{
//  city pcity;
//  tile ptile = punit.tile;
//  int hc = punit.homecity;
//  unit ufocus = get_unit_in_focus();
//
//  util.freelog(Log.LOG_DEBUG, "removing unit %d, %s %s (%d %d) hcity %d",
//	  punit.id, get_nation_name(punit.unit_owner().nation),
//	  unit_name(punit.type), TILE_XY(punit.tile), hc);
//
//  if (punit == ufocus) {
//    set_unit_focus(null);
//    game_remove_unit(punit);
//    punit = ufocus = null;
//    advance_unit_focus();
//  } else {
//    /* calculate before punit disappears, use after punit removed: */
//    boolean update = (ufocus
//		   && Map.same_pos(ufocus.tile, punit.tile));
//
//    game_remove_unit(punit);
//    punit = null;
//    if (update) {
//      update_unit_pix_label(ufocus);
//    }
//  }
//
//  pcity = map_get_city(ptile);
//  if (pcity) {
//    if (can_player_see_units_in_city(Game.game.player_ptr, pcity)) {
//      pcity.client.occupied =
//	(pcity.tile.units.foo_list_size() > 0);
//    }
//
//    refresh_city_dialog(pcity);
//    util.freelog(Log.LOG_DEBUG, "map city %s, %s, (%d %d)", pcity.name,
//	    get_nation_name(City.city_owner(pcity).nation),
//	    TILE_XY(pcity.tile));
//  }
//
//  pcity = Player_P.player_find_city_by_id(Game.game.player_ptr, hc);
//  if (pcity) {
//    refresh_city_dialog(pcity);
//    util.freelog(Log.LOG_DEBUG, "home city %s, %s, (%d %d)", pcity.name,
//	    get_nation_name(City.city_owner(pcity).nation),
//	    TILE_XY(pcity.tile));
//  }
//
//  refresh_tile_mapcanvas(ptile, false);
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void client_remove_city(city pcity)
//{
//  boolean effect_update;
//  tile ptile = pcity.tile;
//
//  util.freelog(Log.LOG_DEBUG, "removing city %s, %s, (%d %d)", pcity.name,
//	  get_nation_name(City.city_owner(pcity).nation), TILE_XY(ptile));
//
//  /* Explicitly remove all improvements, to properly remove any global effects
//     and to handle the preservation of "destroyed" effects. */
//  effect_update=false;
//
//  built_impr_iterate(pcity, i) {
//    effect_update = true;
//    city_remove_improvement(pcity, i);
//  } built_impr_iterate_end;
//
//  if (effect_update) {
//    /* nothing yet */
//  }
//
//  popdown_city_dialog(pcity);
//  game_remove_city(pcity);
//  city_report_dialog_update();
//  refresh_tile_mapcanvas(ptile, false);
//}
//
///**************************************************************************
//Change all cities building X to building Y, if possible.  X and Y
//could be improvements or units. X and Y are compound ids.
//**************************************************************************/
//void client_change_all(cid x, cid y)
//{
//  int fr_id = cid_id(x), to_id = cid_id(y);
//  boolean fr_is_unit = cid_is_unit(x), to_is_unit = cid_is_unit(y);
//  char buf[512];
//  int last_request_id = 0;
//
//  buf = util.my_snprintf(
//	      "Game: Changing production of every %s into %s.",
//	      fr_is_unit ? get_unit_type(fr_id).name :
//	      Improvement.get_improvement_name(fr_id),
//	      to_is_unit ? get_unit_type(to_id).
//	      name : Improvement.get_improvement_name(to_id));
//  append_output_window(buf);
//
//  connection_do_buffer(&aconnection);
//  city_list_iterate (Game.game.player_ptr.cities, pcity) {
//    if (((fr_is_unit &&
//	  (pcity.is_building_unit) &&
//	  (pcity.currently_building == fr_id)) ||
//	 (!fr_is_unit &&
//	  !(pcity.is_building_unit) &&
//	  (pcity.currently_building == fr_id))) &&
//	((to_is_unit &&
//	  City.can_build_unit (pcity, to_id)) ||
//	 (!to_is_unit &&
//	  City.can_build_improvement (pcity, to_id))))
//      {
//	last_request_id = city_change_production(pcity, to_is_unit, to_id);
//      }
//  } }
//
//  connection_do_unbuffer(&aconnection);
//  reports_freeze_till(last_request_id);
//}
//
///***************************************************************************
//  Return a string indicating one nation's embassy status with another
//***************************************************************************/
//final String get_embassy_status(player me, player them)
//{
//  if (me == them
//      || !them.is_alive
//      || !me.is_alive) {
//    return "-";
//  }
//  if (player_has_embassy(me, them)) {
//    if (player_has_embassy(them, me)) {
//      return Q"?embassy:Both";
//    } else {
//      return Q"?embassy:Yes";
//    }
//  } else if (player_has_embassy(them, me)) {
//    return Q"?embassy:With Us";
//  } else if (me.diplstates[them.player_no].contact_turns_left > 0
//             || them.diplstates[me.player_no].contact_turns_left > 0) {
//    return Q"?embassy:Contact";
//  } else {
//    return Q"?embassy:No Contact";
//  }
//}
//
///***************************************************************************
//  Return a string indicating one nation's shaed vision status with another
//***************************************************************************/
//final String get_vision_status(player me, player them)
//{
//  if (gives_shared_vision(me, them)) {
//    if (gives_shared_vision(them, me)) {
//      return Q"?vision:Both";
//    } else {
//      return Q"?vision:To Them";
//    }
//  } else if (gives_shared_vision(them, me)) {
//    return Q"?vision:To Us";
//  } else {
//    return "";
//  }
//}
//
///**************************************************************************
//Copy a string that describes the given clause into the return buffer.
//**************************************************************************/
//void client_diplomacy_clause_string(char *buf, int bufsiz,
//				    Clause pclause)
//{
//  city pcity;
//
//  switch(pclause.type) {
//  case CLAUSE_ADVANCE:
//    buf = String.format "The %s give %s",
//		Nation.get_nation_name_plural(pclause.from.nation),
//		get_tech_name(Game.game.player_ptr, pclause.value));
//    break;
//  case CLAUSE_CITY:
//    pcity = Game.find_city_by_id(pclause.value);
//    if (pcity) {
//      buf = String.format "The %s give %s",
//                  Nation.get_nation_name_plural(pclause.from.nation),
//		  pcity.name);
//    } else {
//      buf = String.format"The %s give unknown city.",
//                  Nation.get_nation_name_plural(pclause.from.nation));
//    }
//    break;
//  case CLAUSE_GOLD:
//    buf = String.format "The %s give %d gold",
//		Nation.get_nation_name_plural(pclause.from.nation),
//		pclause.value);
//    break;
//  case CLAUSE_MAP:
//    buf = String.format "The %s give their worldmap",
//		Nation.get_nation_name_plural(pclause.from.nation));
//    break;
//  case CLAUSE_SEAMAP:
//    buf = String.format "The %s give their seamap",
//		Nation.get_nation_name_plural(pclause.from.nation));
//    break;
//  case CLAUSE_CEASEFIRE:
//    buf = String.format "The parties agree on a cease-fire");
//    break;
//  case CLAUSE_PEACE:
//    buf = String.format "The parties agree on a peace");
//    break;
//  case CLAUSE_ALLIANCE:
//    buf = String.format "The parties create an alliance");
//    break;
//  case CLAUSE_VISION:
//    buf = String.format "The %s gives shared vision",
//		Nation.get_nation_name_plural(pclause.from.nation));
//    break;
//  case CLAUSE_EMBASSY:
//    buf = String.format "The %s gives an embassy",
//                Nation.get_nation_name_plural(pclause.from.nation));
//    break;
//  default:
//    assert(false);
//    if (bufsiz > 0) {
//      *buf = '\0';
//    }
//    break;
//  }
//}
//
///**************************************************************************
//Return the sprite index for the research indicator.
//**************************************************************************/
//int client_research_sprite()
//{
//  return (NUM_TILES_PROGRESS *
//	  Game.game.player_ptr.research.bulbs_researched) /
//      (total_bulbs_required(Game.game.player_ptr) + 1);
//}
//
///**************************************************************************
//Return the sprite index for the global-warming indicator.
//**************************************************************************/
//int client_warming_sprite()
//{
//  int index;
//  if ((Game.game.globalwarming <= 0) &&
//      (Game.game.heating < (NUM_TILES_PROGRESS / 2))) {
//    index = MAX(0, Game.game.heating);
//  } else {
//    index = Math.min(NUM_TILES_PROGRESS,
//		(MAX(0, 4 + Game.game.globalwarming) / 5) +
//		((NUM_TILES_PROGRESS / 2) - 1));
//  }
//  return index;
//}
//
///**************************************************************************
//Return the sprite index for the global-cooling indicator.
//**************************************************************************/
//int client_cooling_sprite()
//{
//  int index;
//  if ((Game.game.nuclearwinter <= 0) &&
//      (Game.game.cooling < (NUM_TILES_PROGRESS / 2))) {
//    index = MAX(0, Game.game.cooling);
//  } else {
//    index = Math.min(NUM_TILES_PROGRESS,
//		(MAX(0, 4 + Game.game.nuclearwinter) / 5) +
//		((NUM_TILES_PROGRESS / 2) - 1));
//  }
//  return index;
//}
//
///**************************************************************************
//Find something sensible to display. This is used to overwrite the
//intro gfx.
//**************************************************************************/
//void center_on_something()
//{
//  city pcity;
//  unit punit;
//
//  if (!can_client_change_view()) {
//    return;
//  }
//
//  can_slide = false;
//  if ((punit = get_unit_in_focus())) {
//    center_tile_mapcanvas(punit.tile);
//  } else if ((pcity = Game.game.player_ptr.find_palace())) {
//    /* Else focus on the capital. */
//    center_tile_mapcanvas(pcity.tile);
//  } else if (Game.game.player_ptr.cities.foo_list_size() > 0) {
//    /* Just focus on any city. */
//    pcity = city_list_get(&Game.game.player_ptr.cities, 0);
//    assert(pcity != null);
//    center_tile_mapcanvas(pcity.tile);
//  } else if (Game.game.player_ptr.units.foo_list_size() > 0) {
//    /* Just focus on any unit. */
//    punit = unit_list_get(&Game.game.player_ptr.units, 0);
//    assert(punit != null);
//    center_tile_mapcanvas(punit.tile);
//  } else {
//    tile ctile = native_pos_to_tile(Map.map.xsize / 2, Map.map.ysize / 2);
//
//    /* Just any known tile will do; search near the middle first. */
//    /* Iterate outward from the center tile.  We have to give a radius that
//     * is guaranteed to be larger than the map will be.  Although this is
//     * a misuse of Map.map.xsize and Map.map.ysize (which are native dimensions),
//     * it should give a sufficiently large radius. */
//    iterate_outward(native_pos_to_tile(Map.map.xsize / 2, Map.map.ysize / 2),
//		    Map.map.xsize + Map.map.ysize, ptile) {
//      if (tile_get_known(ptile) != TILE_UNKNOWN) {
//	ctile = ptile;
//	break;
//      }
//    } iterate_outward_end;
//
//    center_tile_mapcanvas(ctile);
//  }
//  can_slide = true;
//}
//
///**************************************************************************
//...
//**************************************************************************/
//cid cid_encode(boolean is_unit, int id)
//{
//  return id + (is_unit ? B_LAST : 0);
//}
//
///**************************************************************************
//...
//**************************************************************************/
//cid cid_encode_from_city(city  pcity)
//{
//  return cid_encode(pcity.is_building_unit, pcity.currently_building);
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void cid_decode(cid cid, boolean *is_unit, int *id)
//{
//  *is_unit = cid_is_unit(cid);
//  *id = cid_id(cid);
//}
//
///**************************************************************************
//...
//**************************************************************************/
//boolean cid_is_unit(cid cid)
//{
//  return (cid >= B_LAST);
//}
//
///**************************************************************************
//...
//**************************************************************************/
//int cid_id(cid cid)
//{
//  return (cid >= B_LAST) ? (cid - B_LAST) : cid;
//}
//
///**************************************************************************
//...
//**************************************************************************/
//wid wid_encode(boolean is_unit, boolean is_worklist, int id)
//{
//  assert(!is_unit || !is_worklist);
//
//  if (is_unit)
//    return id + B_LAST;
//  if (is_worklist)
//    return id + B_LAST + unittype.U_LAST;
//  return id;
//}
//
///**************************************************************************
//...
//**************************************************************************/
//boolean wid_is_unit(wid wid)
//{
//  assert(wid != WORKLIST_END);
//
//  return (wid >= B_LAST && wid < B_LAST + unittype.U_LAST);
//}
//
///**************************************************************************
//...
//**************************************************************************/
//boolean wid_is_worklist(wid wid)
//{
//  assert(wid != WORKLIST_END);
//
//  return (wid >= B_LAST + unittype.U_LAST);
//}
//
///**************************************************************************
//...
//**************************************************************************/
//int wid_id(wid wid)
//{
//  assert(wid != WORKLIST_END);
//
//  if (wid >= B_LAST + unittype.U_LAST)
//    return wid - (B_LAST + unittype.U_LAST);
//  if (wid >= B_LAST)
//    return wid - B_LAST;
//  return wid;
//}
//
///****************************************************************
//...
//*****************************************************************/
//boolean city_can_build_impr_or_unit(city pcity, cid cid)
//{
//  if (cid_is_unit(cid))
//    return City.can_build_unit(pcity, cid_id(cid));
//  else
//    return City.can_build_improvement(pcity, cid_id(cid));
//}
//
///****************************************************************
//...
//*****************************************************************/
//boolean city_unit_supported(city pcity, cid cid)
//{
//  if (cid_is_unit(cid)) {
//    int unit_type = cid_id(cid);
//
//    for (unit punit : pcity.units_supported.data) {
//      if (punit.type == unit_type)
//	return true;
//    }
//    }
//  }
//  return false;
//}
//
///****************************************************************
//...
//*****************************************************************/
//boolean city_unit_present(city pcity, cid cid)
//{
//  if (cid_is_unit(cid)) {
//    int unit_type = cid_id(cid);
//
//    for (unit punit : pcity.tile.units.data) {
//      if (punit.type == unit_type)
//	return true;
//    }
//    }
//  }
//  return false;
//}
//
///****************************************************************************
//  A TestCityFunc to tell whether the item is a building and is present.
//****************************************************************************/
//boolean city_building_present(city pcity, cid cid)
//{
//  if (!cid_is_unit(cid)) {
//    int impr_type = cid_id(cid);
//
//    return city_got_building(pcity, impr_type);
//  }
//  return false;
//}
//
///**************************************************************************
// Helper for name_and_sort_items.
//**************************************************************************/
//static int my_cmp(final void *p1, final void *p2)
//{
//  final item i1 = (final item ) p1;
//  final item i2 = (final item ) p2;
//
//  if (i1.section == i2.section)
//    return mystrcasecmp(i1.descr, i2.descr);
//  return (i1.section - i2.section);
//}
//
///**************************************************************************
// Takes an array of compound ids (cids). It will fill out an array of
// struct items and also sort it.
//
// section 0: normal buildings
// section 1: Capitalization
// section 2: Eunit_flag_id.F_NONMIL units
// section 3: other units
// section 4: wonders
//**************************************************************************/
//void name_and_sort_items(int *pcids, int num_cids, item items,
//			 boolean show_cost, city pcity)
//{
//  int i;
//
//  for (i = 0; i < num_cids; i++) {
//    boolean is_unit = cid_is_unit(pcids[i]);
//    int id = cid_id(pcids[i]), cost;
//    item pitem = &items[i];
//    final String name;
//
//    pitem.cid = pcids[i];
//
//    if (is_unit) {
//      name = get_unit_name(id);
//      cost = Unittype_P.unit_build_shield_cost(id);
//      pitem.section = unit_type_flag(id, Eunit_flag_id.F_NONMIL) ? 2 : 3;
//    } else {
//      name = get_impr_name_ex(pcity, id);
//      if (building_has_effect(id, effect_type.EFT_PROD_TO_GOLD)) {
//	cost = -1;
//	pitem.section = 1;
//      } else {
//	cost = Improvement.impr_build_shield_cost(id);
//	if (is_wonder(id)) {
//      	  pitem.section = 4;
//        } else {
//	  pitem.section = 0;
//	}
//      }
//    }
//
//    if (show_cost) {
//      if (cost < 0) {
//	pitem.descr = util.my_snprintf( "%s (XX)", name);
//      } else {
//	pitem.descr = util.my_snprintf( "%s (%d)", name, cost);
//      }
//    } else {
//      () mystrlcpy(pitem.descr, name, sizeof(pitem.descr));
//    }
//  }
//
//  qsort(items, num_cids, sizeof(struct item), my_cmp);
//}
//
///**************************************************************************
//...
//**************************************************************************/
//int collect_cids1(cid * dest_cids, city *selected_cities,
//		  int num_selected_cities, boolean append_units,
//		  boolean append_wonders, boolean change_prod,
//		  boolean (*test_func) (city , int))
//{
//  cid first = append_units ? B_LAST : 0;
//  cid last = (append_units
//	      ? Game.game.num_unit_types + B_LAST
//	      : Game.game.num_impr_types);
//  cid cid;
//  int items_used = 0;
//
//  for (cid = first; cid < last; cid++) {
//    boolean append = false;
//    int id = cid_id(cid);
//
//    if (!append_units && (append_wonders != is_wonder(id)))
//      continue;
//
//    if (!change_prod) {
//      for (city pcity : Game.game.player_ptr.cities.data) {
//	append |= test_func(pcity, cid);
//      }
//      }
//    } else {
//      int i;
//
//      for (i = 0; i < num_selected_cities; i++) {
//	append |= test_func(selected_cities[i], cid);
//      }
//    }
//
//    if (!append)
//      continue;
//
//    dest_cids[items_used] = cid;
//    items_used++;
//  }
//  return items_used;
//}
//
///**************************************************************************
// Collect the cids of all targets (improvements and units) which are
// currently built in a city.
//**************************************************************************/
//int collect_cids2(cid * dest_cids)
//{
//  boolean mapping[B_LAST + unittype.U_LAST];
//  int cids_used = 0;
//  cid cid;
//
//  memset(mapping, 0, sizeof(mapping));
//  for (city pcity : Game.game.player_ptr.cities.data) {
//    mapping[cid_encode_from_city(pcity)] = true;
//  }
//  }
//
//  for (cid = 0; cid < ARRAY_SIZE(mapping); cid++) {
//    if (mapping[cid]) {
//      dest_cids[cids_used] = cid;
//      cids_used++;
//    }
//  }
//  return cids_used;
//}
//
///**************************************************************************
// Collect the cids of all targets (improvements and units) which can
// be build in a city.
//**************************************************************************/
//int collect_cids3(cid * dest_cids)
//{
//  int cids_used = 0;
//
//  impr_type_iterate(id) {
//    if (can_player_build_improvement(Game.game.player_ptr, id)) {
//      dest_cids[cids_used] = cid_encode(false, id);
//      cids_used++;
//    }
//  } impr_type_iterate_end;
//
//  unit_type_iterate(id) {
//    if (can_player_build_unit(Game.game.player_ptr, id)) {
//      dest_cids[cids_used] = cid_encode(true, id);
//      cids_used++;
//    }
//  } unit_type_iterate_end
//
//  return cids_used;
//}
//
///**************************************************************************
// Collect the cids of all targets which can be build by this city or
// in general.
//**************************************************************************/
//int collect_cids4(cid * dest_cids, city pcity, boolean advanced_tech)
//{
//  int cids_used = 0;
//
//  impr_type_iterate(id) {
//    boolean can_build = can_player_build_improvement(Game.game.player_ptr, id);
//    boolean can_eventually_build =
//	can_player_eventually_build_improvement(Game.game.player_ptr, id);
//
//    /* If there's a city, can the city build the improvement? */
//    if (pcity) {
//      can_build = can_build && City.can_build_improvement(pcity, id);
//      can_eventually_build = can_eventually_build &&
//	  can_eventually_build_improvement(pcity, id);
//    }
//
//    if ((advanced_tech && can_eventually_build) ||
//	(!advanced_tech && can_build)) {
//      dest_cids[cids_used] = cid_encode(false, id);
//      cids_used++;
//    }
//  } impr_type_iterate_end;
//
//  unit_type_iterate(id) {
//    boolean can_build = can_player_build_unit(Game.game.player_ptr, id);
//    boolean can_eventually_build =
//	can_player_eventually_build_unit(Game.game.player_ptr, id);
//
//    /* If there's a city, can the city build the unit? */
//    if (pcity) {
//      can_build = can_build && City.can_build_unit(pcity, id);
//      can_eventually_build = can_eventually_build &&
//	  can_eventually_build_unit(pcity, id);
//    }
//
//    if ((advanced_tech && can_eventually_build) ||
//	(!advanced_tech && can_build)) {
//      dest_cids[cids_used] = cid_encode(true, id);
//      cids_used++;
//    }
//  } unit_type_iterate_end;
//
//  return cids_used;
//}
//
///**************************************************************************
// Collect the cids of all improvements which are built in the given city.
//**************************************************************************/
//int collect_cids5(cid * dest_cids, city pcity)
//{
//  int cids_used = 0;
//
//  assert(pcity != null);
//
//  built_impr_iterate(pcity, id) {
//    dest_cids[cids_used] = cid_encode(false, id);
//    cids_used++;
//  } built_impr_iterate_end;
//
//  return cids_used;
//}
//
///**************************************************************************
// Collect the wids of all possible targets of the given city.
//**************************************************************************/
//int collect_wids1(wid * dest_wids, city pcity, boolean wl_first, 
//                  boolean advanced_tech)
//{
//  cid cids[unittype.U_LAST + B_LAST];
//  int item, cids_used, wids_used = 0;
//  struct item items[unittype.U_LAST + B_LAST];
//
//  /* Fill in the global worklists now?                      */
//  /* perhaps judicious use of goto would be good here? -mck */
//  if (wl_first && Game.game.player_ptr.worklists[0].is_valid && pcity) {
//    int i;
//    for (i = 0; i < MAX_NUM_WORKLISTS; i++) {
//      if (Game.game.player_ptr.worklists[i].is_valid) {
//	dest_wids[wids_used] = wid_encode(false, true, i);
//	wids_used++;
//      }
//    }
//  }
//
//  /* Fill in improvements and units */
//  cids_used = collect_cids4(cids, pcity, advanced_tech);
//  name_and_sort_items(cids, cids_used, items, false, pcity);
//
//  for (item = 0; item < cids_used; item++) {
//    cid cid = items[item].cid;
//    dest_wids[wids_used] = wid_encode(cid_is_unit(cid), false, cid_id(cid));
//    wids_used++;
//  }
//
//  /* we didn't fill in the global worklists above */
//  if (!wl_first && Game.game.player_ptr.worklists[0].is_valid && pcity) {
//    int i;
//    for (i = 0; i < MAX_NUM_WORKLISTS; i++) {
//      if (Game.game.player_ptr.worklists[i].is_valid) {
//        dest_wids[wids_used] = wid_encode(false, true, i);
//        wids_used++;
//      }
//    }
//  }
//
//  return wids_used;
//}
//
///**************************************************************************
//...
//**************************************************************************/
//int num_supported_units_in_city(city pcity)
//{
//  unit_list plist;
//
//  if (pcity.owner != Game.game.player_idx) {
//    plist = &pcity.info_units_supported;
//  } else {
//    plist = &pcity.units_supported;
//  }
//
//  return plist.foo_list_size();
//}
//
///**************************************************************************
//...
//**************************************************************************/
//int num_present_units_in_city(city pcity)
//{
//  unit_list plist;
//
//  if (pcity.owner != Game.game.player_idx) {
//    plist = &pcity.info_units_present;
//  } else {
//    plist = &pcity.tile.units;
//  }
//
//  return plist.foo_list_size();
//}
//
///**************************************************************************
//  Handles a chat message.
//**************************************************************************/
//void handle_event(char *message, tile ptile,
//		  enum event_type event, int conn_id)
//{
//  int where = MW_OUTPUT;	/* where to display the message */
//  
//  if (event >= E_LAST)  {
//    /* Server may have added a new event; leave as MW_OUTPUT */
//    util.freelog(Log.LOG_NORMAL, "Unknown event type %d!", event);
//  } else if (event >= 0)  {
//    where = messages_where[event];
//  }
//
//  if (BOOL_VAL(where & MW_OUTPUT)
//      || get_client_state() != CLIENT_GAME_RUNNING_STATE) {
//    /* When the Game.game isn't running, the messages dialog isn't present and
//     * we want to send all messages to the chatline.  There shouldn't be
//     * any problem with server spam in pregame anyway. */
//    append_output_window_full(message, conn_id);
//  }
//  if (BOOL_VAL(where & MW_MESSAGES)) {
//    add_notify_window(message, ptile, event);
//  }
//  if (BOOL_VAL(where & MW_POPUP) &&
//      (!Game.game.player_ptr.ai.control || ai_popup_windows)) {
//    popup_notify_goto_dialog("Popup Request", message, ptile);
//  }
//
//  play_sound_for_event(event);
//}
//
///**************************************************************************
//  Creates a struct packet_generic_message packet and injects it via
//  handle_chat_msg.
//**************************************************************************/
//void create_event(tile ptile, enum event_type event,
//		  final String format)
//{
//  va_list ap;
//  char message[MAX_LEN_MSG];
//
//  va_start(ap, format);
//  my_vsnprintf(message, sizeof(message), format, ap);
//  va_end(ap);
//
//  handle_event(message, ptile, event, aconnection.id);
//}
//
///**************************************************************************
//  Writes the supplied string into the file civgame.log.
//**************************************************************************/
//void write_chatline_content(final String txt)
//{
//  FILE *fp = fopen("civgame.log", "w");	/* should allow choice of name? */
//
//  append_output_window("Exporting output window to civgame.log ...");
//  if (fp) {
//    fputs(txt, fp);
//    fclose(fp);
//    append_output_window("Export complete.");
//  } else {
//    append_output_window("Export failed, couldn't write to file.");
//  }
//}
//
///**************************************************************************
//  Freeze all reports and other GUI elements.
//**************************************************************************/
//void reports_freeze()
//{
//  util.freelog(Log.LOG_DEBUG, "reports_freeze");
//
//  meswin_freeze();
//  plrdlg_freeze();
//  report_dialogs_freeze();
//  output_window_freeze();
//}
//
///**************************************************************************
//  Freeze all reports and other GUI elements until the given request
//  was executed.
//**************************************************************************/
//void reports_freeze_till(int request_id)
//{
//  if (request_id != 0) {
//    reports_freeze();
//    set_reports_thaw_request(request_id);
//  }
//}
//
///**************************************************************************
//  Thaw all reports and other GUI elements.
//**************************************************************************/
//void reports_thaw()
//{
//  util.freelog(Log.LOG_DEBUG, "reports_thaw");
//
//  meswin_thaw();
//  plrdlg_thaw();
//  report_dialogs_thaw();
//  output_window_thaw();
//}
//
///**************************************************************************
//  Thaw all reports and other GUI elements unconditionally.
//**************************************************************************/
//void reports_force_thaw()
//{
//  meswin_force_thaw();
//  plrdlg_force_thaw();
//  report_dialogs_force_thaw();
//  output_window_force_thaw();
//}
//
///*************************************************************************
//...
//*************************************************************************/
//enum known_type map_get_known(final tile ptile,
//			      player pplayer)
//{
//  assert(pplayer == Game.game.player_ptr);
//  return tile_get_known(ptile);
//}
//
///**************************************************************************
//  Find city nearest to given unit and optionally return squared city
//  distance Parameter sq_dist may be null. Returns null only if no city is
//  known. Favors punit owner's cities over other cities if equally distant.
//**************************************************************************/
//city get_nearest_city(unit punit, int *sq_dist)
//{
//  city pcity_near;
//  int pcity_near_dist;
//
//  if ((pcity_near = map_get_city(punit.tile))) {
//    pcity_near_dist = 0;
//  } else {
//    pcity_near = null;
//    pcity_near_dist = -1;
//    for(player pplayer: Game.game.players){
//      for (city pcity_current : pplayer.cities.data) {
//        int dist = sq_map_distance(pcity_current.tile, punit.tile);
//        if (pcity_near_dist == -1 || dist < pcity_near_dist
//	    || (dist == pcity_near_dist
//		&& punit.owner == pcity_current.owner)) {
//          pcity_near = pcity_current;
//          pcity_near_dist = dist;
//        }
//      } }
//    }
//  }
//
//  if (sq_dist) {
//    *sq_dist = pcity_near_dist;
//  }
//
//  return pcity_near;
//}
//
///**************************************************************************
//  Called when the "Buy" button is pressed in the city report for every
//  selected city. Checks for coinage and sufficient funds or request the
//  purchase if everything is ok.
//**************************************************************************/
//void cityrep_buy(city pcity)
//{
//  int value = City.city_buy_cost(pcity);
//
//  if (Effects.get_current_finalruction_bonus(pcity, effect_type.EFT_PROD_TO_GOLD) > 0) {
//    char buf[512];
//
//    assert(!pcity.is_building_unit);
//    buf = util.my_snprintf(
//		"Game: You don't buy %s in %s!",
//		improvement_types[pcity.currently_building].name,
//		pcity.name);
//    append_output_window(buf);
//    return;
//  }
//
//  if (Game.game.player_ptr.economic.gold >= value) {
//    city_buy_production(pcity);
//  } else {
//    char buf[512];
//    final String name;
//
//    if (pcity.is_building_unit) {
//      name = get_unit_type(pcity.currently_building).name;
//    } else {
//      name = get_impr_name_ex(pcity, pcity.currently_building);
//    }
//
//    buf = util.my_snprintf(
//		"Game: %s costs %d gold and you only have %d gold.",
//		name, value, Game.game.player_ptr.economic.gold);
//    append_output_window(buf);
//  }
//}
//
//void common_taxrates_callback(int i)
//{
//  int tax_end, lux_end, sci_end, tax, lux, sci;
//  int delta = 10;
//
//  if (!can_client_issue_orders()) {
//    return;
//  }
//
//  lux_end = Game.game.player_ptr.economic.luxury;
//  sci_end = lux_end + Game.game.player_ptr.economic.science;
//  tax_end = 100;
//
//  lux = Game.game.player_ptr.economic.luxury;
//  sci = Game.game.player_ptr.economic.science;
//  tax = Game.game.player_ptr.economic.tax;
//
//  i *= 10;
//  if (i < lux_end) {
//    lux -= delta;
//    sci += delta;
//  } else if (i < sci_end) {
//    sci -= delta;
//    tax += delta;
//  } else {
//    tax -= delta;
//    lux += delta;
//  }
//  dsend_packet_player_rates(&aconnection, tax, lux, sci);
//}
}