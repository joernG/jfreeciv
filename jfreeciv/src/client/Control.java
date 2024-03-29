package client;

public class Control{

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
//#include "fcintl.h"
//#include "log.h"
//#include "mem.h"
//
//#include "audio.h"
//#include "chatline_g.h"
//#include "citydlg_g.h"
//#include "civclient.h"
//#include "climap.h"
//#include "climisc.h"
//#include "clinet.h"
//#include "dialogs_g.h"
//#include "goto.h"
//#include "gui_main_g.h"
//#include "mapctrl_g.h"
//#include "mapview_g.h"
//#include "menu_g.h"
//#include "options.h"
//#include "tilespec.h"
//
//#include "control.h"
//
///* gui-dep code may adjust depending on tile size etc: */
//int num_units_below = MAX_NUM_UNITS_BELOW;
//
///* unit_focus points to the current unit in focus */
//static unit punit_focus = null;
//
///* The previously focused unit.  Focus can generally be recalled on this
// * unit with keypad 5.  FIXME: this is not reset when the client
// * disconnects. */
//static int previous_focus_id = -1;
//
///* These should be set via set_hover_state() */
//int hover_unit = 0; /* id of unit hover_state applies to */
//enum cursor_hover_state hover_state = HOVER_NONE;
//enum unit_activity connect_activity;
///* This may only be here until client goto is fully implemented.
//   It is reset each time the hower_state is reset. */
//boolean draw_goto_line = true;
//
///* units involved in current combat */
//static unit punit_attacking = null;
//static unit punit_defending = null;
//
///*
// * This variable is true iff a NON-AI controlled unit was focused this
// * turn.
// */
//boolean non_ai_unit_focus;
//
///*************************************************************************/
//
//static unit find_best_focus_candidate(boolean accept_current);
//static void store_focus();
//static unit quickselect(tile ptile,
//                        enum quickselect_type qtype);
//
///**************************************************************************
//...
//**************************************************************************/
//void set_hover_state(unit punit, enum cursor_hover_state state,
//		     enum unit_activity activity)
//{
//  assert(punit != null || state == HOVER_NONE);
//  assert(state == HOVER_CONNECT || activity == ACTIVITY_LAST);
//  draw_goto_line = true;
//  if (punit)
//    hover_unit = punit.id;
//  else
//    hover_unit = 0;
//  hover_state = state;
//  connect_activity = activity;
//  exit_goto_state();
//}
//
///**************************************************************************
//Center on the focus unit, if off-screen and auto_center_on_unit is true.
//**************************************************************************/
//void auto_center_on_focus_unit()
//{
//  if (punit_focus && auto_center_on_unit &&
//      !tile_visible_and_not_on_border_mapcanvas(punit_focus.tile)) {
//    center_tile_mapcanvas(punit_focus.tile);
//  }
//}
//
///**************************************************************************
//note: punit can be null
//We make sure that the previous focus unit is refreshed, if necessary,
//_after_ setting the new focus unit (otherwise if the previous unit is
//in a city, the refresh code draws the previous unit instead of the city).
//
// This function can be called directly from packhand.c as a result of
// Wakeup Focus (when a unit goes from Sentried to Idle in the server).
//**************************************************************************/
//void set_unit_focus(unit punit)
//{
//  unit punit_old_focus = punit_focus;
//
//  if (punit && punit.owner != Game.game.player_idx) {
//    util.freelog(Log.LOG_ERROR, "Trying to focus on another player's unit!");
//    return;
//  }
//
//  if (punit != punit_focus) {
//    store_focus();
//  }
//
//  /*
//   *  This should be the ONLY place we _modify_ punit_focus.
//   */
//  punit_focus = punit;
//
//  if (!can_client_change_view()) {
//    /* This function can be called to set the focus to null when
//     * disconnecting.  In this case we don't want any other actions! */
//    assert(punit == null);
//    return;
//  }
//
//  if(punit) {
//    auto_center_on_focus_unit();
//
//    punit.focus_status=FOCUS_AVAIL;
//    refresh_tile_mapcanvas(punit.tile, false);
//
//    if (unit_has_orders(punit)) {
//      /* Clear the focus unit's orders. */
//      request_orders_cleared(punit);
//    }
//    if (punit.activity != unit_activity.ACTIVITY_IDLE || punit.ai.control)  {
//      punit.ai.control = false;
//      refresh_unit_city_dialogs(punit);
//      request_new_unit_activity(punit, unit_activity.ACTIVITY_IDLE);
//    }
//  }
//  
//  /* avoid the old focus unit disappearing: */
//  if (punit_old_focus
//      && (!punit || !Map.same_pos(punit_old_focus.tile, punit.tile))) {
//    refresh_tile_mapcanvas(punit_old_focus.tile, false);
//  }
//
//  update_unit_info_label(punit);
//  update_menus();
//}
//
///**************************************************************************
// The only difference is that here we draw the "cross".
//**************************************************************************/
//void set_unit_focus_and_select(unit punit)
//{
//  set_unit_focus(punit);
//  if (punit) {
//    put_cross_overlay_tile(punit.tile);
//  }
//}
//
///**************************************************************************
//  Store the focus unit.  This is used so that we can return to the
//  previously focused unit with an appropriate keypress.
//**************************************************************************/
//static void store_focus()
//{
//  if (punit_focus) {
//    previous_focus_id = punit_focus.id;
//  }
//}
//
///**************************************************************************
//If there is no unit currently in focus, or if the current unit in
//focus should not be in focus, then get a new focus unit.
//We let GOTO-ing units stay in focus, so that if they have moves left
//at the end of the goto, then they are still in focus.
//**************************************************************************/
//void update_unit_focus()
//{
//  if (!punit_focus
//      || (punit_focus.activity != unit_activity.ACTIVITY_IDLE
//	  && !unit_has_orders(punit_focus)
//	  && punit_focus.activity != unit_activity.ACTIVITY_GOTO)
//      || punit_focus.done_moving
//      || punit_focus.moves_left == 0 
//      || punit_focus.ai.control) {
//    advance_unit_focus();
//  }
//}
//
///**************************************************************************
//...
//**************************************************************************/
//unit get_unit_in_focus()
//{
//  return punit_focus;
//}
//
///**************************************************************************
// This function may be called from packhand.c, via update_unit_focus(),
// as a result of packets indicating change in activity for a unit. Also
// called when user press the "Wait" command.
// 
// FIXME: Add feature to focus only units of a certain category.
//**************************************************************************/
//void advance_unit_focus()
//{
//  unit punit_old_focus = punit_focus;
//  unit candidate = find_best_focus_candidate(false);
//
//  set_hover_state(null, HOVER_NONE, ACTIVITY_LAST);
//  if (!can_client_change_view()) {
//    return;
//  }
//
//  if(!candidate) {
//    /* First try for "waiting" units. */
//    for (unit punit : Game.game.player_ptr.units.data) {
//      if(punit.focus_status == FOCUS_WAIT) {
//        punit.focus_status = FOCUS_AVAIL;
//      }
//    } }
//    candidate = find_best_focus_candidate(false);
//  }
//
//  /* Accept current focus unit as last resort. */
//  if (!candidate) {
//    candidate = find_best_focus_candidate(true);
//  }
//
//  set_unit_focus(candidate);
//
//  /* 
//   * Is the unit which just lost focus a non-AI unit? If yes this
//   * enables the auto end turn. 
//   */
//  if (punit_old_focus && !punit_old_focus.ai.control) {
//    non_ai_unit_focus = true;
//  }
//
//  /* 
//   * Handle auto-turn-done mode: If a unit was in focus (did move),
//   * but now none are (no more to move) and there was at least one
//   * non-AI unit this turn which was focused, then fake a Turn Done
//   * keypress.
//   */
//  if (auto_turn_done && punit_old_focus && !punit_focus && non_ai_unit_focus) {
//    key_end_turn();
//  }
//}
//
///**************************************************************************
// Find the nearest available unit for focus, excluding any current unit
// in focus unless "accept_current" is true.  If the current focus unit
// is the only possible unit, or if there is no possible unit, returns null.
//**************************************************************************/
//static unit find_best_focus_candidate(boolean accept_current)
//{
//  unit best_candidate;
//  int best_dist = 99999;
//  tile ptile;
//
//  if (punit_focus)  {
//    ptile = punit_focus.tile;
//  } else {
//    ptile = get_center_tile_mapcanvas();
//  }
//
//  best_candidate = null;
//  for (unit punit : Game.game.player_ptr.units.data) {
//    if ((punit != punit_focus || accept_current)
//      && punit.focus_status == FOCUS_AVAIL
//      && punit.activity == unit_activity.ACTIVITY_IDLE
//	&& !unit_has_orders(punit)
//      && punit.moves_left > 0
//      && !punit.done_moving
//      && !punit.ai.control) {
//        int d = sq_map_distance(punit.tile, ptile);
//        if (d < best_dist) {
//          best_candidate = punit;
//          best_dist = d;
//        }
//    }
//  } }
//  return best_candidate;
//}
//
///**************************************************************************
//Return a pointer to a visible unit, if there is one.
//**************************************************************************/
//unit find_visible_unit(tile ptile)
//{
//  unit panyowned = null, *panyother = null, *ptptother = null;
//
//  /* If no units here, return nothing. */
//  if (ptile.units.foo_list_size()==0) {
//    return null;
//  }
//
//  /* If a unit is attacking we should show that on top */
//  if (punit_attacking && Map.same_pos(punit_attacking.tile, ptile)) {
//    unit_list_iterate(ptile.units, punit)
//      if(punit == punit_attacking) return punit;
//    }
//  }
//
//  /* If a unit is defending we should show that on top */
//  if (punit_defending && Map.same_pos(punit_defending.tile, ptile)) {
//    unit_list_iterate(ptile.units, punit)
//      if(punit == punit_defending) return punit;
//    }
//  }
//
//  /* If the unit in focus is at this tile, show that on top */
//  if (punit_focus && Map.same_pos(punit_focus.tile, ptile)) {
//    unit_list_iterate(ptile.units, punit)
//      if(punit == punit_focus) return punit;
//    }
//  }
//
//  /* If a city is here, return nothing (unit hidden by city). */
//  if (ptile.city) {
//    return null;
//  }
//
//  /* Iterate through the units to find the best one we prioritize this way:
//       1: owned transporter.
//       2: any owned unit
//       3: any transporter
//       4: any unit
//     (always return first in stack). */
//  unit_list_iterate(ptile.units, punit)
//    if (punit.unit_owner() == Game.game.player_ptr) {
//      if (punit.transported_by == -1) {
//        if (Unit.get_transporter_capacity(punit) > 0) {
//	  return punit;
//        } else if (!panyowned) {
//	  panyowned = punit;
//        }
//      }
//    } else if (!ptptother && punit.transported_by == -1) {
//      if (Unit.get_transporter_capacity(punit) > 0) {
//	ptptother = punit;
//      } else if (!panyother) {
//	panyother = punit;
//      }
//    }
//  }
//
//  return (panyowned ? panyowned : (ptptother ? ptptother : panyother));
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void blink_active_unit()
//{
//  static boolean is_shown;
//  static unit pblinking_unit;
//  unit punit;
//
//  if ((punit = punit_focus)) {
//    if (punit != pblinking_unit) {
//      /* When the focus unit changes, we reset the is_shown flag. */
//      pblinking_unit = punit;
//      is_shown = true;
//    } else {
//      /* Reverse the shown status. */
//      is_shown = !is_shown;
//    }
//    set_focus_unit_hidden_state(!is_shown);
//    refresh_tile_mapcanvas(punit.tile, true);
//  }
//}
//
///**************************************************************************
//  Update unit icons (and arrow) in the information display, for specified
//  punit as the active unit and other units on the same square.  In practice
//  punit is almost always (or maybe strictly always?) the focus unit.
//  
//  Static vars store some info on current (ie previous) state, to avoid
//  unnecessary redraws; initialise to "flag" values to always redraw first
//  time.  In principle we _might_ need more info (eg ai.control, connecting),
//  but in practice this is enough?
//  
//  Used to store unit_ids for below units, to use for callbacks (now done
//  inside gui-dep set_unit_icon()), but even with ids here they would not
//  be enough information to know whether to redraw -- instead redraw every
//  time.  (Could store enough info to know, but is it worth it?)
//**************************************************************************/
//void update_unit_pix_label(unit punit)
//{
//  int i;
//
//  /* Check for any change in the unit's state.  This assumes that a unit's
//   * orders cannot be changed directly but must be removed and then reset. */
//  if (punit && get_client_state() != CLIENT_GAME_OVER_STATE) {
//    /* There used to be a complicated and bug-prone check here to see if
//     * the unit had actually changed.  This was misguided since the stacked
//     * units (below) are redrawn in any case.  Unless we write a general
//     * system for unit updates here we might as well just redraw it every
//     * time. */
//    set_unit_icon(-1, punit);
//
//    i = 0;			/* index into unit_below_canvas */
//    for (unit aunit : punit.tile.units.data) {
//      if (aunit != punit) {
//	if (i < num_units_below) {
//	  set_unit_icon(i, aunit);
//	}
//	i++;
//      }
//    }
//    }
//    
//    if (i > num_units_below) {
//      set_unit_icons_more_arrow(true);
//    } else {
//      set_unit_icons_more_arrow(false);
//      for(; i < num_units_below; i++) {
//	set_unit_icon(i, null);
//      }
//    }
//  } else {
//    for(i=-1; i<num_units_below; i++) {
//      set_unit_icon(i, null);
//    }
//    set_unit_icons_more_arrow(false);
//  }
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void set_units_in_combat(unit pattacker, unit pdefender)
//{
//  punit_attacking = pattacker;
//  punit_defending = pdefender;
//
//  if (punit_attacking == punit_focus || punit_defending == punit_focus) {
//    /* If one of the units is the focus unit, make sure hidden-focus is
//     * disabled.  We don't just do this as a check later because then
//     * with a blinking unit it would just disappear again right after the
//     * battle. */
//    set_focus_unit_hidden_state(false);
//  }
//}
//
///**************************************************************************
//  Add punit to queue of caravan arrivals, and popup a window for the
//  next arrival in the queue, if there is not already a popup, and
//  re-checking that a popup is appropriate.
//  If punit is null, just do for the next arrival in the queue.
//**************************************************************************/
//void process_caravan_arrival(unit punit)
//{
//  static struct genlist arrival_queue;
//  static boolean is_init_arrival_queue = false;
//  int *p_id;
//
//  /* arrival_queue is a list of individually malloc-ed ints with
//     punit.id values, for units which have arrived. */
//
//  if (!is_init_arrival_queue) {
//    genlist_init(&arrival_queue);
//    is_init_arrival_queue = true;
//  }
//
//  if (punit) {
//    p_id = fc_malloc(sizeof(int));
//    *p_id = punit.id;
//    genlist_insert(&arrival_queue, p_id, -1);
//  }
//
//  /* There can only be one dialog at a time: */
//  if (caravan_dialog_is_open()) {
//    return;
//  }
//  
//  while (genlist_size(&arrival_queue) > 0) {
//    int id;
//    
//    p_id = genlist_get(&arrival_queue, 0);
//    genlist_unlink(&arrival_queue, p_id);
//    id = *p_id;
//    free(p_id);
//    p_id = null;
//    punit = Player_P.player_find_unit_by_id(Game.game.player_ptr, id);
//
//    if (punit && (unit_can_help_build_wonder_here(punit)
//		  || unit_can_est_traderoute_here(punit))
//	&& (!Game.game.player_ptr.ai.control || ai_popup_windows)) {
//      city pcity_dest = Map.map_get_city(punit.tile);
//      city pcity_homecity = Game.find_city_by_id(punit.homecity);
//      if (pcity_dest && pcity_homecity) {
//	popup_caravan_dialog(punit, pcity_homecity, pcity_dest);
//	return;
//      }
//    }
//  }
//}
//
///**************************************************************************
//  Add punit/pcity to queue of diplomat arrivals, and popup a window for
//  the next arrival in the queue, if there is not already a popup, and
//  re-checking that a popup is appropriate.
//  If punit is null, just do for the next arrival in the queue.
//**************************************************************************/
//void process_diplomat_arrival(unit pdiplomat, int victim_id)
//{
//  static struct genlist arrival_queue;
//  static boolean is_init_arrival_queue = false;
//  int *p_ids;
//
//  /* arrival_queue is a list of individually malloc-ed int[2]s with
//     punit.id and pcity.id values, for units which have arrived. */
//
//  if (!is_init_arrival_queue) {
//    genlist_init(&arrival_queue);
//    is_init_arrival_queue = true;
//  }
//
//  if (pdiplomat && victim_id != 0) {
//    p_ids = fc_malloc(2*sizeof(int));
//    p_ids[0] = pdiplomat.id;
//    p_ids[1] = victim_id;
//    genlist_insert(&arrival_queue, p_ids, -1);
//  }
//
//  /* There can only be one dialog at a time: */
//  if (diplomat_dialog_is_open()) {
//    return;
//  }
//
//  while (genlist_size(&arrival_queue) > 0) {
//    int diplomat_id, victim_id;
//    city pcity;
//    unit punit;
//
//    p_ids = genlist_get(&arrival_queue, 0);
//    genlist_unlink(&arrival_queue, p_ids);
//    diplomat_id = p_ids[0];
//    victim_id = p_ids[1];
//    free(p_ids);
//    p_ids = null;
//    pdiplomat = Player_P.player_find_unit_by_id(Game.game.player_ptr, diplomat_id);
//    pcity = Game.find_city_by_id(victim_id);
//    punit = Game.find_unit_by_id(victim_id);
//
//    if (!pdiplomat || !unit_flag(pdiplomat, Eunit_flag_id.F_DIPLOMAT))
//      continue;
//
//    if (punit
//	&& is_diplomat_action_available(pdiplomat, DIPLOMAT_ANY_ACTION,
//					punit.tile)
//	&& diplomat_can_do_action(pdiplomat, DIPLOMAT_ANY_ACTION,
//				  punit.tile)) {
//      popup_diplomat_dialog(pdiplomat, punit.tile);
//      return;
//    } else if (pcity
//	       && is_diplomat_action_available(pdiplomat, DIPLOMAT_ANY_ACTION,
//					       pcity.tile)
//	       && diplomat_can_do_action(pdiplomat, DIPLOMAT_ANY_ACTION,
//					 pcity.tile)) {
//      popup_diplomat_dialog(pdiplomat, pcity.tile);
//      return;
//    }
//  }
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void request_unit_goto()
//{
//  unit punit = punit_focus;
//
//  if (!punit)
//    return;
//
//  if (hover_state != HOVER_GOTO) {
//    set_hover_state(punit, HOVER_GOTO, ACTIVITY_LAST);
//    update_unit_info_label(punit);
//    /* Not yet implemented for air units, including helicopters. */
//    if (is_air_unit(punit) || is_heli_unit(punit)) {
//      draw_goto_line = false;
//    } else {
//      enter_goto_state(punit);
//      create_line_at_mouse_pos();
//    }
//  } else if (!is_air_unit(punit) && !is_heli_unit(punit)) {
//    assert(goto_is_active());
//    goto_add_waypoint();
//  }
//}
//
///**************************************************************************
//  Return true if there are any units doing the activity on the tile.
//**************************************************************************/
//static boolean is_activity_on_tile(tile ptile,
//				enum unit_activity activity)
//{
//  for (unit punit : ptile.units.data) {
//    if (punit.activity == ACTIVITY_MINE) {
//      return true;
//    }
//  } }
//
//  return false;
//}
//
///**************************************************************************
//  Return whether the unit can connect with given activity (or with
//  any activity if activity arg is set to unit_activity.ACTIVITY_IDLE)
//
//  This function is client-specific.
//**************************************************************************/
//boolean can_unit_do_connect(unit punit, enum unit_activity activity) 
//{
//  player pplayer = punit.unit_owner();
//  int terrain = punit.tile.terrain;
//  tile_type ttype = get_tile_type(terrain);
//
//  /* HACK: This code duplicates that in
//   * can_unit_do_activity_targeted_at(). The general logic here is that
//   * the connect is allowed if both:
//   * (1) the unit can do that activity type, in general
//   * (2) either
//   *     (a) the activity has already been completed at this tile
//   *     (b) it can be done by the unit at this tile. */
//  switch (activity) {
//  case ACTIVITY_ROAD:
//    return Map.terrain_control.may_road
//      && unit_flag(punit, Eunit_flag_id.F_SETTLERS)
//      && (Map.tile_has_special(punit.tile, Terrain_H.S_ROAD)
//	  || (ttype.road_time != 0
//	      && (!Map.tile_has_special(punit.tile, Terrain_H.S_RIVER)
//		  || Player_P.player_knows_techs_with_flag(pplayer, TF_BRIDGE))));
//  case ACTIVITY_RAILROAD:
//    /* There is no check for existing road/rail; the connect is allowed
//     * regardless. It is assumed that if you know the tech_flag_id.TF_RAILROAD flag
//     * you must also know the TF_BRIDGE flag. */
//    return (Map.terrain_control.may_road
//	    && unit_flag(punit, Eunit_flag_id.F_SETTLERS)
//	    && Player_P.player_knows_techs_with_flag(pplayer, tech_flag_id.TF_RAILROAD));
//  case ACTIVITY_IRRIGATE:
//    /* Special case for irrigation: only irrigate to make S_IRRIGATION,
//     * never to transform tiles. */
//    return (Map.terrain_control.may_irrigate
//	    && unit_flag(punit, Eunit_flag_id.F_SETTLERS)
//	    && (Map.tile_has_special(punit.tile, S_IRRIGATION)
//		|| (terrain == ttype.irrigation_result
//		    && is_water_adjacent_to_tile(punit.tile)
//		    && !is_activity_on_tile(punit.tile, ACTIVITY_MINE))));
//  default:
//    break;
//  }
//
//  return false;
//}
//
///**************************************************************************
//prompt player for entering destination point for unit connect
//(e.g. connecting with roads)
//**************************************************************************/
//void request_unit_connect(enum unit_activity activity)
//{
//  if (!punit_focus || !can_unit_do_connect(punit_focus, activity)) {
//    return;
//  }
//
//  if (hover_state != HOVER_CONNECT || connect_activity != activity) {
//    /* Enter or change the hover connect state. */
//    set_hover_state(punit_focus, HOVER_CONNECT, activity);
//    update_unit_info_label(punit_focus);
//
//    enter_goto_state(punit_focus);
//    create_line_at_mouse_pos();
//  } else {
//    assert(goto_is_active());
//    goto_add_waypoint();
//  }
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void request_unit_unload_all(unit punit)
//{
//  tile ptile = punit.tile;
//  unit plast = null;
//
//  if(Unit.get_transporter_capacity(punit) == 0) {
//    append_output_window("Game: Only transporter units can be unloaded.");
//    return;
//  }
//
//  request_unit_wait(punit);    /* RP: unfocus the ship */
//
//  for (unit pcargo : ptile.units.data) {
//    if (pcargo.transported_by == punit.id) {
//      request_unit_unload(pcargo);
//
//      if (pcargo.activity == ACTIVITY_SENTRY) {
//	request_new_unit_activity(pcargo, unit_activity.ACTIVITY_IDLE);
//      }
//
//      if (pcargo.owner == Game.game.player_idx) {
//	plast = pcargo;
//      }
//    }
//  } }
//
//  if (plast) {
//    /* If the above unloading failed this focus will still happen.  That's
//     * probably a feature. */
//    set_unit_focus(plast);
//  }
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void request_unit_airlift(unit punit, city pcity)
//{
//  dsend_packet_unit_airlift(&aconnection, punit.id,pcity.id);
//}
//
///**************************************************************************
//  Return-and-recover for a particular unit.  This sets the unit to GOTO
//  the nearest city.
//**************************************************************************/
//void request_unit_return(unit punit)
//{
//  pf_path path;
//
//  if (is_air_unit(punit) || is_heli_unit(punit)) {
//    /* PF doesn't support air or helicopter units yet. */
//    return;
//  }
//
//  if ((path = path_to_nearest_allied_city(punit))) {
//    enum unit_activity activity = ACTIVITY_LAST;
//    int turns = pf_last_position(path).turn;
//
//    if (punit.hp + turns * get_player_bonus(Game.game.player_ptr,
//					     EFT_UNIT_RECOVER)
//	< punit.unit_type().hp) {
//      activity = ACTIVITY_SENTRY;
//    }
//    send_goto_path(punit, path, activity);
//    pf_destroy_path(path);
//  }
//}
//
///**************************************************************************
//(RP:) un-sentry all my own sentried units on punit's tile
//**************************************************************************/
//void request_unit_wakeup(unit punit)
//{
//  wakeup_sentried_units(punit.tile);
//}
//
///**************************************************************************
//  Request a diplomat to do a specific action.
//  - action : The action to be requested.
//  - dipl_id : The unit ID of the diplomatic unit.
//  - target_id : The ID of the target unit or city.
//  - value : For DIPLOMAT_STEAL or DIPLOMAT_SABOTAGE, the technology
//            or building to aim for (spies only).
//**************************************************************************/
//void request_diplomat_action(enum diplomat_actions action, int dipl_id,
//			     int target_id, int value)
//{
//  dsend_packet_unit_diplomat_action(&aconnection, dipl_id,action,target_id,value);
//}
//
//void wakeup_sentried_units(tile ptile)
//{
//  for (unit punit : ptile.units.data) {
//    if(punit.activity==ACTIVITY_SENTRY && Game.game.player_idx==punit.owner) {
//      request_new_unit_activity(punit, unit_activity.ACTIVITY_IDLE);
//    }
//  }
//  }
//}
//
///**************************************************************************
//Player pressed 'b' or otherwise instructed unit to build or add to city.
//If the unit can build a city, we popup the appropriate dialog.
//Otherwise, we just send a packet to the server.
//If this action is not appropriate, the server will respond
//with an appropriate message.  (This is to avoid duplicating
//all the server checks and messages here.)
//**************************************************************************/
//void request_unit_build_city(unit punit)
//{
//  if (can_unit_build_city(punit)) {
//    dsend_packet_city_name_suggestion_req(&aconnection, punit.id);
//    /* the reply will trigger a dialog to name the new city */
//  } else {
//    char name[] = "";
//    dsend_packet_unit_build_city(&aconnection, punit.id, name);
//  }
//}
//
///**************************************************************************
//  This function is called whenever the player pressed an arrow key.
//
//  We do NOT take into account that punit might be a caravan or a diplomat
//  trying to move into a city, or a diplomat going into a tile with a unit;
//  the server will catch those cases and send the client a package to pop up
//  a dialog. (the server code has to be there anyway as goto's are entirely
//  in the server)
//**************************************************************************/
//void request_move_unit_direction(unit punit, int dir)
//{
//  tile dest_tile;
//
//  /* Catches attempts to move off map */
//  dest_tile = mapstep(punit.tile, dir);
//  if (!dest_tile) {
//    return;
//  }
//
//  if (punit.moves_left > 0) {
//    dsend_packet_unit_move(&aconnection, punit.id,
//			   dest_tile.x, dest_tile.y);
//  } else {
//    /* Initiate a "goto" with direction keys for exhausted units. */
//    send_goto_unit(punit, dest_tile);
//  }
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void request_new_unit_activity(unit punit, enum unit_activity act)
//{
//  if (!can_client_issue_orders()) {
//    return;
//  }
//
//  dsend_packet_unit_change_activity(&aconnection, punit.id, act,
//				    S_NO_SPECIAL);
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void request_new_unit_activity_targeted(unit punit,
//					enum unit_activity act,
//					enum int tgt)
//{
//  dsend_packet_unit_change_activity(&aconnection, punit.id, act, tgt);
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void request_unit_disband(unit punit)
//{
//  dsend_packet_unit_disband(&aconnection, punit.id);
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void request_unit_change_homecity(unit punit)
//{
//  city pcity=Map.map_get_city(punit.tile);
//  
//  if (pcity) {
//    dsend_packet_unit_change_homecity(&aconnection, punit.id, pcity.id);
//  }
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void request_unit_upgrade(unit punit)
//{
//  city pcity=Map.map_get_city(punit.tile);
//
//  if (pcity) {
//    dsend_packet_unit_upgrade(&aconnection, punit.id);
//  }
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void request_unit_auto(unit punit)
//{
//  if (can_unit_do_auto(punit)) {
//    dsend_packet_unit_auto(&aconnection, punit.id);
//  } else {
//    append_output_window(("Game: Only settler units and military units" +
//			   " in cities can be put in auto-mode."));
//  }
//}
//
///****************************************************************************
//  Send a request to the server that the cargo be loaded into the transporter.
//
//  If ptransporter is null a transporter will be picked at random.
//****************************************************************************/
//void request_unit_load(unit pcargo, unit ptrans)
//{
//  if (!ptrans) {
//    ptrans = find_transporter_for_unit(pcargo, pcargo.tile);
//  }
//
//  if (can_client_issue_orders()
//      && can_unit_load(pcargo, ptrans)) {
//    dsend_packet_unit_load(&aconnection, pcargo.id, ptrans.id);
//
//    /* Sentry the unit.  Don't request_unit_sentry since this can give a
//     * recursive loop. */
//    dsend_packet_unit_change_activity(&aconnection, pcargo.id,
//				      ACTIVITY_SENTRY, S_NO_SPECIAL);
//  }
//}
//
///****************************************************************************
//  Send a request to the server that the cargo be unloaded from its current
//  transporter.
//****************************************************************************/
//void request_unit_unload(unit pcargo)
//{
//  unit ptrans = Game.find_unit_by_id(pcargo.transported_by);
//
//  if (can_client_issue_orders()
//      && ptrans
//      && can_unit_unload(pcargo, ptrans)
//      && can_unit_survive_at_tile(pcargo, pcargo.tile)) {
//    dsend_packet_unit_unload(&aconnection, pcargo.id, ptrans.id);
//
//    /* Activate the unit. */
//    dsend_packet_unit_change_activity(&aconnection, pcargo.id,
//				      unit_activity.ACTIVITY_IDLE, S_NO_SPECIAL);
//  }
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void request_unit_caravan_action(unit punit, enum packet_type action)
//{
//  if (!Map.map_get_city(punit.tile)) {
//    return;
//  }
//
//  if (action == PACKET_UNIT_ESTABLISH_TRADE) {
//    dsend_packet_unit_establish_trade(&aconnection, punit.id);
//  } else if (action == PACKET_UNIT_HELP_BUILD_WONDER) {
//    dsend_packet_unit_help_build_wonder(&aconnection, punit.id);
//  } else {
//    util.freelog(Log.LOG_ERROR, "Bad action (%d) in request_unit_caravan_action",
//	    action);
//  }
//}
//
///**************************************************************************
// Explode nuclear at a tile without enemy units
//**************************************************************************/
//void request_unit_nuke(unit punit)
//{
//  if(!unit_flag(punit, F_NUCLEAR)) {
//    append_output_window("Game: Only nuclear units can do this.");
//    return;
//  }
//  if(punit.moves_left == 0)
//    do_unit_nuke(punit);
//  else {
//    set_hover_state(punit, HOVER_NUKE, ACTIVITY_LAST);
//    update_unit_info_label(punit);
//  }
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void request_unit_paradrop(unit punit)
//{
//  if(!unit_flag(punit, F_PARATROOPERS)) {
//    append_output_window("Game: Only paratrooper units can do this.");
//    return;
//  }
//  if(!can_unit_paradrop(punit))
//    return;
//
//  set_hover_state(punit, HOVER_PARADROP, ACTIVITY_LAST);
//  update_unit_info_label(punit);
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void request_unit_patrol()
//{
//  unit punit = punit_focus;
//
//  if (!punit)
//    return;
//
//  if (is_air_unit(punit) || is_heli_unit(punit)) {
//    /* Same string as in do_unit_patrol_to. */
//    append_output_window(("Game: Sorry, airunit patrol " +
//			   "not yet implemented."));
//    return;
//  }
//
//  if (hover_state != HOVER_PATROL) {
//    set_hover_state(punit, HOVER_PATROL, ACTIVITY_LAST);
//    update_unit_info_label(punit);
//    /* Not yet implemented for air units, including helicopters. */
//    if (is_air_unit(punit) || is_heli_unit(punit)) {
//      draw_goto_line = false;
//    } else {
//      enter_goto_state(punit);
//      create_line_at_mouse_pos();
//    }
//  } else {
//    assert(goto_is_active());
//    goto_add_waypoint();
//  }
//}
//
///****************************************************************
//...
//*****************************************************************/
//void request_unit_sentry(unit punit)
//{
//  if(punit.activity!=ACTIVITY_SENTRY &&
//     can_unit_do_activity(punit, ACTIVITY_SENTRY))
//    request_new_unit_activity(punit, ACTIVITY_SENTRY);
//}
//
///****************************************************************
//...
//*****************************************************************/
//void request_unit_fortify(unit punit)
//{
//  if(punit.activity!=ACTIVITY_FORTIFYING &&
//     can_unit_do_activity(punit, ACTIVITY_FORTIFYING))
//    request_new_unit_activity(punit, ACTIVITY_FORTIFYING);
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void request_unit_pillage(unit punit)
//{
//  tile ptile = punit.tile;
//  enum int pspresent = get_tile_infrastructure_set(ptile);
//  enum int psworking =
//      get_unit_tile_pillage_set(punit.tile);
//  enum int what =
//      get_preferred_pillage(pspresent & (~psworking));
//  enum int would =
//      what | map_get_infrastructure_prerequisite(what);
//
//  if ((Game.game.rgame.pillage_select) &&
//      ((pspresent & (~(psworking | would))) != S_NO_SPECIAL)) {
//    popup_pillage_dialog(punit, (pspresent & (~psworking)));
//  } else {
//    request_new_unit_activity_targeted(punit, ACTIVITY_PILLAGE, what);
//  }
//}
//
///**************************************************************************
// Toggle display of grid lines on the map
//**************************************************************************/
//void request_toggle_map_grid() 
//{
//  if (!can_client_change_view()) {
//    return;
//  }
//
//  draw_map_grid^=1;
//  update_map_canvas_visible();
//}
//
///**************************************************************************
// Toggle display of national borders on the map
//**************************************************************************/
//void request_toggle_map_borders() 
//{
//  if (!can_client_change_view()) {
//    return;
//  }
//
//  draw_borders ^= 1;
//  update_map_canvas_visible();
//}
//
///**************************************************************************
// Toggle display of city names
//**************************************************************************/
//void request_toggle_city_names()
//{
//  if (!can_client_change_view()) {
//    return;
//  }
//
//  draw_city_names ^= 1;
//  update_map_canvas_visible();
//}
// 
// /**************************************************************************
// Toggle display of city growth (turns-to-grow)
//**************************************************************************/
//void request_toggle_city_growth()
//{
//  if (!can_client_change_view()) {
//    return;
//  }
//
//  draw_city_growth ^= 1;
//  update_map_canvas_visible();
//}
//
///**************************************************************************
// Toggle display of city productions
//**************************************************************************/
//void request_toggle_city_productions()
//{
//  if (!can_client_change_view()) {
//    return;
//  }
//
//  draw_city_productions ^= 1;
//  update_map_canvas_visible();
//}
//
///**************************************************************************
// Toggle display of terrain
//**************************************************************************/
//void request_toggle_terrain()
//{
//  if (!can_client_change_view()) {
//    return;
//  }
//
//  draw_terrain ^= 1;
//  update_map_canvas_visible();
//}
//
///**************************************************************************
// Toggle display of coastline
//**************************************************************************/
//void request_toggle_coastline()
//{
//  if (!can_client_change_view()) {
//    return;
//  }
//
//  draw_coastline ^= 1;
//  update_map_canvas_visible();
//}
//
///**************************************************************************
// Toggle display of roads and rails
//**************************************************************************/
//void request_toggle_roads_rails()
//{
//  if (!can_client_change_view()) {
//    return;
//  }
//
//  draw_roads_rails ^= 1;
//  update_map_canvas_visible();
//}
//
///**************************************************************************
// Toggle display of irrigation
//**************************************************************************/
//void request_toggle_irrigation()
//{
//  if (!can_client_change_view()) {
//    return;
//  }
//
//  draw_irrigation ^= 1;
//  update_map_canvas_visible();
//}
//
///**************************************************************************
// Toggle display of mines
//**************************************************************************/
//void request_toggle_mines()
//{
//  if (!can_client_change_view()) {
//    return;
//  }
//
//  draw_mines ^= 1;
//  update_map_canvas_visible();
//}
//
///**************************************************************************
// Toggle display of fortress and airbase
//**************************************************************************/
//void request_toggle_fortress_airbase()
//{
//  if (!can_client_change_view()) {
//    return;
//  }
//
//  draw_fortress_airbase ^= 1;
//  update_map_canvas_visible();
//}
//
///**************************************************************************
// Toggle display of specials
//**************************************************************************/
//void request_toggle_specials()
//{
//  if (!can_client_change_view()) {
//    return;
//  }
//
//  draw_specials ^= 1;
//  update_map_canvas_visible();
//}
//
///**************************************************************************
// Toggle display of pollution
//**************************************************************************/
//void request_toggle_pollution()
//{
//  if (!can_client_change_view()) {
//    return;
//  }
//
//  draw_pollution ^= 1;
//  update_map_canvas_visible();
//}
//
///**************************************************************************
// Toggle display of cities
//**************************************************************************/
//void request_toggle_cities()
//{
//  if (!can_client_change_view()) {
//    return;
//  }
//
//  draw_cities ^= 1;
//  update_map_canvas_visible();
//}
//
///**************************************************************************
// Toggle display of units
//**************************************************************************/
//void request_toggle_units()
//{
//  if (!can_client_change_view()) {
//    return;
//  }
//
//  draw_units ^= 1;
//  update_map_canvas_visible();
//}
//
///**************************************************************************
// Toggle display of focus unit
//**************************************************************************/
//void request_toggle_focus_unit()
//{
//  if (!can_client_change_view()) {
//    return;
//  }
//
//  draw_focus_unit ^= 1;
//  update_map_canvas_visible();
//}
//
///**************************************************************************
// Toggle display of fog of war
//**************************************************************************/
//void request_toggle_fog_of_war()
//{
//  if (!can_client_change_view()) {
//    return;
//  }
//
//  draw_fog_of_war ^= 1;
//  update_map_canvas_visible();
//  refresh_overview_canvas();
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void request_center_focus_unit()
//{
//  if (punit_focus) {
//    center_tile_mapcanvas(punit_focus.tile);
//  }
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void request_unit_wait(unit punit)
//{
//  punit.focus_status=FOCUS_WAIT;
//  if (punit == punit_focus) {
//    advance_unit_focus();
//    /* set_unit_focus(punit_focus); */  /* done in advance_unit_focus */
//  }
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void request_unit_move_done()
//{
//  if (punit_focus) {
//    punit_focus.focus_status = FOCUS_DONE;
//    advance_unit_focus();
//    /* set_unit_focus(punit_focus); */  /* done in advance_unit_focus */
//  }
//}
//
///**************************************************************************
//  Called to have the client move a unit from one location to another,
//  updating the graphics if necessary.  The caller must redraw the target
//  location after the move.
//**************************************************************************/
//void do_move_unit(unit punit, unit target_unit)
//{
//  tile ptile;
//  boolean was_teleported, do_animation;
//
//  was_teleported = !is_tiles_adjacent(punit.tile, target_unit.tile);
//  do_animation = (!was_teleported && smooth_move_unit_msec > 0);
//
//  ptile = punit.tile;
//
//  if (!was_teleported
//      && punit.activity != ACTIVITY_SENTRY
//      && punit.transported_by == -1) {
//    audio_play_sound(punit.unit_type().sound_move,
//		     punit.unit_type().sound_move_alt);
//  }
//
//  unit_list_unlink(&ptile.units, punit);
//
//  if (Game.game.player_idx == punit.owner
//      && auto_center_on_unit
//      && !unit_has_orders(punit)
//      && punit.activity != unit_activity.ACTIVITY_GOTO
//      && punit.activity != ACTIVITY_SENTRY
//      && !tile_visible_and_not_on_border_mapcanvas(target_unit.tile)) {
//    center_tile_mapcanvas(target_unit.tile);
//  }
//
//  if (punit.transported_by == -1) {
//    /* We have to refresh the tile before moving.  This will draw
//     * the tile without the unit (because it was unlinked above). */
//    if (Unittype_P.unit_type_flag(punit.type, Eunit_flag_id.F_CITIES)
//	&& punit.client.colored) {
//      /* For settlers with an overlay, redraw the entire area of the
//       * overlay. */
//      int width = get_citydlg_canvas_width();
//      int height = get_citydlg_canvas_height();
//      int canvas_x, canvas_y;
//
//      tile_to_canvas_pos(&canvas_x, &canvas_y, ptile);
//      update_map_canvas(canvas_x - (width - NORMAL_TILE_WIDTH) / 2,
//			canvas_y - (height - NORMAL_TILE_HEIGHT) / 2,
//			width, height);
//      overview_update_tile(ptile);
//    } else {
//      refresh_tile_mapcanvas(ptile, false);
//    }
//
//    if (do_animation) {
//      int dx, dy;
//
//      /* For the duration of the animation the unit exists at neither
//       * tile. */
//      map_distance_vector(&dx, &dy, punit.tile,
//			  target_unit.tile);
//      move_unit_map_canvas(punit, ptile, dx, dy);
//    }
//  }
//    
//  punit.tile = target_unit.tile;
//
//  &punit.tile.units.foo_list_insert(punit);
//
//  if (punit_focus == punit) update_menus();
//}
//
///**************************************************************************
// Handles everything when the user clicked a tile
//**************************************************************************/
//void do_map_click(tile ptile, enum quickselect_type qtype)
//{
//  city pcity = Map.map_get_city(ptile);
//  unit punit = Player_P.player_find_unit_by_id(Game.game.player_ptr, hover_unit);
//  boolean maybe_goto = false;
//
//  if (punit && hover_state != HOVER_NONE) {
//    switch (hover_state) {
//    case HOVER_NONE:
//      util.die("well; shouldn't get here :)");
//    case HOVER_GOTO:
//      do_unit_goto(ptile);
//      break;
//    case HOVER_NUKE:
//      if (Unit_H.SINGLE_MOVE * Map.real_map_distance(punit.tile, ptile)
//	  > punit.moves_left) {
//        append_output_window("Game: Too far for this unit.");
//      } else {
//	do_unit_goto(ptile);
//	/* note that this will be executed by the server after the goto */
//	if (!pcity)
//	  do_unit_nuke(punit);
//      }
//      break;
//    case HOVER_PARADROP:
//      do_unit_paradrop_to(punit, ptile);
//      break;
//    case HOVER_CONNECT:
//      do_unit_connect(punit, ptile, connect_activity);
//      break;
//    case HOVER_PATROL:
//      do_unit_patrol_to(punit, ptile);
//      break;	
//    }
//    set_hover_state(null, HOVER_NONE, ACTIVITY_LAST);
//    update_unit_info_label(punit);
//  }
//
//  /* Bypass stack or city popup if quickselect is specified. */
//  else if (qtype) {
//    unit qunit = quickselect(ptile, qtype);
//    if (qunit) {
//      set_unit_focus_and_select(qunit);
//      maybe_goto = keyboardless_goto;
//    }
//  }
//  /* Otherwise use popups. */
//  else if (pcity && Player_P.can_player_see_city_internals(Game.game.player_ptr, pcity)) {
//    popup_city_dialog(pcity, false);
//  }
//  else if (ptile.units.foo_list_size() == 0 && !pcity
//           && punit_focus) {
//    maybe_goto = keyboardless_goto;
//  }
//  else if (ptile.units.foo_list_size() == 1
//      && !unit_list_get(&ptile.units, 0).occupy) {
//    unit punit=unit_list_get(&ptile.units, 0);
//    if(Game.game.player_idx==punit.owner) {
//      if(can_unit_do_activity(punit, unit_activity.ACTIVITY_IDLE)) {
//        maybe_goto = keyboardless_goto;
//	set_unit_focus_and_select(punit);
//      }
//    } else if (pcity) {
//      /* Don't hide the unit in the city. */
//      popup_unit_select_dialog(ptile);
//    }
//  }
//  else if(ptile.units.foo_list_size() > 0) {
//    /* The stack list is always popped up, even if it includes enemy units.
//     * If the server doesn't want the player to know about them it shouldn't
//     * tell him!  The previous behavior would only pop up the stack if you
//     * owned a unit on the tile.  This gave cheating clients an advantage,
//     * and also showed you allied units if (and only if) you had a unit on
//     * the tile (inconsistent). */
//    popup_unit_select_dialog(ptile);
//  }
//
//  /* See mapctrl_common.c */
//  keyboardless_goto_start_tile = maybe_goto ? ptile : null;
//  keyboardless_goto_button_down = maybe_goto;
//  keyboardless_goto_active = false;
//}
//
///**************************************************************************
// Quickselecting a unit is normally done with <control> left, right click,
// or keypad / * for the current tile. Bypassing the stack popup is quite
// convenient, and can be tactically important in furious multiplayer games.
//**************************************************************************/
//static unit quickselect(tile ptile,
//                          enum quickselect_type qtype)
//{
//  int listsize = ptile.units.foo_list_size();
//  unit panytransporter = null,
//              *panymovesea  = null, *panysea  = null,
//              *panymoveland = null, *panyland = null,
//              *panymoveunit = null, *panyunit = null;
//
//  assert(qtype > SELECT_POPUP);
//
//  if (listsize == 0) {
//    return null;
//  } else if (listsize == 1) {
//    unit punit = unit_list_get(&ptile.units, 0);
//    return (Game.game.player_idx == punit.owner) ? punit : null;
//  }
//
//  /*  Quickselect priorities. Units with moves left
//   *  before exhausted. Focus unit is excluded.
//   *
//   *    SEA:  Transporter
//   *          Sea unit
//   *          Any unit
//   *
//   *    LAND: Military land unit
//   *          Non-combatant
//   *          Sea unit
//   *          Any unit
//   */
//
//    for (unit punit : ptile.units.data) {
//  if(Game.game.player_idx != punit.owner || punit == punit_focus) {
//    continue;
//  }
//  if (qtype == SELECT_SEA) {
//    /* Transporter. */
//    if (Unit.get_transporter_capacity(punit)) {
//      if (punit.moves_left > 0) {
//        return punit;
//      } else if (!panytransporter) {
//        panytransporter = punit;
//      }
//    }
//    /* Any sea, pref. moves left. */
//    else if (Unit.is_sailing_unit(punit)) {
//      if (punit.moves_left > 0) {
//        if (!panymovesea) {
//          panymovesea = punit;
//        }
//      } else if (!panysea) {
//          panysea = punit;
//      }
//    }
//  } else if (qtype == SELECT_LAND) {
//    if (is_ground_unit(punit))  {
//      if (punit.moves_left > 0) {
//        if (is_military_unit(punit)) {
//          return punit;
//        } else if (!panymoveland) {
//            panymoveland = punit;
//        }
//      } else if (!panyland) {
//        panyland = punit;
//      }
//    }
//    else if (Unit.is_sailing_unit(punit)) {
//      if (punit.moves_left > 0) {
//        panymovesea = punit;
//      } else {
//        panysea = punit;
//      }
//    }
//  }
//  if (punit.moves_left > 0 && !panymoveunit) {
//    panymoveunit = punit;
//  }
//  if (!panyunit) {
//    panyunit = punit;
//  }
//    } }
//
//  if (qtype == SELECT_SEA) {
//    if (panytransporter) {
//      return panytransporter;
//    } else if (panymovesea) {
//      return panymovesea;
//    } else if (panysea) {
//      return panysea;
//    } else if (panymoveunit) {
//      return panymoveunit;
//    } else if (panyunit) {
//      return panyunit;
//    }
//  }
//  else if (qtype == SELECT_LAND) {
//    if (panymoveland) {
//      return panymoveland;
//    } else if (panyland) {
//      return panyland;
//    } else if (panymovesea) {
//      return panymovesea;
//    } else if (panysea) {
//      return panysea;
//    } else if (panymoveunit) {
//      return panymoveunit;
//    } else if (panyunit) {
//      return panyunit;
//    }
//  }
//  return null;
//}
//
///**************************************************************************
// Finish the goto mode and let the unit which is stored in hover_unit move
// to a given location.
//**************************************************************************/
//void do_unit_goto(tile ptile)
//{
//  unit punit = Player_P.player_find_unit_by_id(Game.game.player_ptr, hover_unit);
//
//  if (hover_unit == 0 || hover_state != HOVER_GOTO)
//    return;
//
//  if (punit) {
//    if (!draw_goto_line) {
//      send_goto_unit(punit, ptile);
//    } else {
//      tile dest_tile;
//
//      draw_line(ptile);
//      dest_tile = get_line_dest();
//      if (ptile == dest_tile) {
//	send_goto_route(punit);
//      } else {
//	append_output_window("Game: Didn't find a route to the destination!");
//      }
//    }
//  }
//
//  set_hover_state(null, HOVER_NONE, ACTIVITY_LAST);
//}
//
///**************************************************************************
//Explode nuclear at a tile without enemy units
//**************************************************************************/
//void do_unit_nuke(unit punit)
//{
//  dsend_packet_unit_nuke(&aconnection, punit.id);
//}
//
///**************************************************************************
//  Paradrop to a location.
//**************************************************************************/
//void do_unit_paradrop_to(unit punit, tile ptile)
//{
//  dsend_packet_unit_paradrop_to(&aconnection, punit.id, ptile.x, ptile.y);
//}
// 
///**************************************************************************
//  Patrol to a location.
//**************************************************************************/
//void do_unit_patrol_to(unit punit, tile ptile)
//{
//  if (is_air_unit(punit) || is_heli_unit(punit)) {
//    append_output_window("Game: Sorry, airunit patrol not yet implemented.");
//    return;
//  } else {
//    tile dest_tile;
//
//    draw_line(ptile);
//    dest_tile = get_line_dest();
//    if (ptile == dest_tile
//        && !Unit.is_non_allied_unit_tile(ptile, punit.unit_owner())) {
//      send_patrol_route(punit);
//    } else {
//      append_output_window("Game: Didn't find a route to the destination!");
//    }
//  }
//
//  set_hover_state(null, HOVER_NONE, ACTIVITY_LAST);
//}
// 
///**************************************************************************
//  "Connect" to the given location.
//**************************************************************************/
//void do_unit_connect(unit punit, tile ptile,
//		     enum unit_activity activity)
//{
//  if (is_air_unit(punit) || is_heli_unit(punit)) {
//    append_output_window(("Game: Sorry, airunit connect " +
//			   "not yet implemented."));
//  } else {
//    tile dest_tile;
//
//    draw_line(ptile);
//    dest_tile = get_line_dest();
//    if (Map.same_pos(dest_tile, ptile)) {
//      send_connect_route(punit, activity);
//    } else {
//      append_output_window(("Game: Didn't find a route to " +
//			     "the destination!"));
//    }
//  }
//
//  set_hover_state(null, HOVER_NONE, ACTIVITY_LAST);
//}
// 
///**************************************************************************
// The 'Escape' key.
//**************************************************************************/
//void key_cancel_action()
//{
//  boolean popped = false;
//
//  cancel_tile_hiliting();
//
//  if (hover_state == HOVER_GOTO || hover_state == HOVER_PATROL)
//    if (draw_goto_line)
//      popped = goto_pop_waypoint();
//
//  if (hover_state != HOVER_NONE && !popped) {
//    unit punit = Player_P.player_find_unit_by_id(Game.game.player_ptr, hover_unit);
//
//    set_hover_state(null, HOVER_NONE, ACTIVITY_LAST);
//    update_unit_info_label(punit);
//
//    keyboardless_goto_button_down = false;
//    keyboardless_goto_active = false;
//    keyboardless_goto_start_tile = null;
//  }
//}
//
///**************************************************************************
//  Center the mapview on the player's capital, or print a failure message.
//**************************************************************************/
//void key_center_capital()
//{
//  city capital = Game.game.player_ptr.find_palace();
//
//  if (capital)  {
//    /* Center on the tile, and pop up the crosshair overlay. */
//    center_tile_mapcanvas(capital.tile);
//    put_cross_overlay_tile(capital.tile);
//  } else {
//    append_output_window("Game: Oh my! You seem to have no capital!");
//  }
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void key_end_turn()
//{
//  send_turn_done();
//}
//
///**************************************************************************
//  Recall the previous focus unit and focus on it.  See store_focus().
//**************************************************************************/
//void key_recall_previous_focus_unit()
//{
//  unit punit = Player_P.player_find_unit_by_id(Game.game.player_ptr,
//                                              previous_focus_id);
//  if (punit) {
//    set_unit_focus_and_select(punit);
//  }
//}
//
///**************************************************************************
//  Move the focus unit in the given direction.  Here directions are
//  defined according to the GUI, so that north is "up" in the interface.
//**************************************************************************/
//void key_unit_move(enum direction8 gui_dir)
//{
//  if (punit_focus) {
//    enum direction8 map_dir = gui_to_map_dir(gui_dir);
//    request_move_unit_direction(punit_focus, map_dir);
//  }
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void key_unit_build_city()
//{
//  if (punit_focus) {
//    request_unit_build_city(punit_focus);
//  }
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void key_unit_build_wonder()
//{
//  if (punit_focus && unit_flag(punit_focus, F_HELP_WONDER)) {
//    request_unit_caravan_action(punit_focus, PACKET_UNIT_HELP_BUILD_WONDER);
//  }
//}
//
///**************************************************************************
//handle user pressing key for 'Connect' command
//**************************************************************************/
//void key_unit_connect(enum unit_activity activity)
//{
//  if (punit_focus) {
//    request_unit_connect(activity);
//  }
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void key_unit_diplomat_actions()
//{
//  city pcity;		/* need pcity.id */
//  if (punit_focus
//     && is_diplomat_unit(punit_focus)
//     && (pcity = Map.map_get_city(punit_focus.tile))
//     && !diplomat_dialog_is_open()    /* confusing otherwise? */
//     && diplomat_can_do_action(punit_focus, DIPLOMAT_ANY_ACTION,
//			       punit_focus.tile))
//     process_diplomat_arrival(punit_focus, pcity.id);
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void key_unit_done()
//{
//  if (punit_focus) {
//    request_unit_move_done();
//  }
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void key_unit_goto()
//{
//  if (punit_focus) {
//    request_unit_goto();
//  }
//}
//
///**************************************************************************
//Explode nuclear at a tile without enemy units
//**************************************************************************/
//void key_unit_nuke()
//{
//  if (punit_focus) {
//    request_unit_nuke(punit_focus);
//  }
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void key_unit_paradrop()
//{
//  if (punit_focus && can_unit_paradrop(punit_focus)) {
//    request_unit_paradrop(punit_focus);
//  }
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void key_unit_patrol()
//{
//  if (punit_focus) {
//    request_unit_patrol();
//  }
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void key_unit_traderoute()
//{
//  if (punit_focus && unit_flag(punit_focus, F_TRADE_ROUTE)) {
//    request_unit_caravan_action(punit_focus, PACKET_UNIT_ESTABLISH_TRADE);
//  }
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void key_unit_unload_all()
//{
//  if (punit_focus) {
//    request_unit_unload_all(punit_focus);
//  }
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void key_unit_wait()
//{
//  if (punit_focus) {
//    request_unit_wait(punit_focus);
//  }
//}
//
///**************************************************************************
//...
//***************************************************************************/
//void key_unit_wakeup_others()
//{
//  if (punit_focus) {
//    request_unit_wakeup(punit_focus);
//  }
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void key_unit_airbase()
//{
//  if (punit_focus &&
//      can_unit_do_activity(punit_focus, ACTIVITY_AIRBASE)) {
//    request_new_unit_activity(punit_focus, ACTIVITY_AIRBASE);
//  }
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void key_unit_auto_attack()
//{
//  if (punit_focus && !unit_flag(punit_focus, Eunit_flag_id.F_SETTLERS) &&
//      can_unit_do_auto(punit_focus)) {
//    request_unit_auto(punit_focus);
//  }
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void key_unit_auto_explore()
//{
//  if (punit_focus &&
//      can_unit_do_activity(punit_focus, ACTIVITY_EXPLORE)) {
//    request_new_unit_activity(punit_focus, ACTIVITY_EXPLORE);
//  }
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void key_unit_auto_settle()
//{
//  if (punit_focus && unit_flag(punit_focus, Eunit_flag_id.F_SETTLERS) &&
//      can_unit_do_auto(punit_focus)) {
//    request_unit_auto(punit_focus);
//  }
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void key_unit_disband()
//{
//  if (punit_focus) {
//    request_unit_disband(punit_focus);
//  }
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void key_unit_fallout()
//{
//  if (punit_focus &&
//      can_unit_do_activity(punit_focus, ACTIVITY_FALLOUT)) {
//    request_new_unit_activity(punit_focus, ACTIVITY_FALLOUT);
//  }
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void key_unit_fortify()
//{
//  if (punit_focus &&
//      can_unit_do_activity(punit_focus, ACTIVITY_FORTIFYING)) {
//    request_new_unit_activity(punit_focus, ACTIVITY_FORTIFYING);
//  }
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void key_unit_fortress()
//{
//  if (punit_focus &&
//      can_unit_do_activity(punit_focus, ACTIVITY_FORTRESS)) {
//    request_new_unit_activity(punit_focus, ACTIVITY_FORTRESS);
//  }
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void key_unit_homecity()
//{
//  if (punit_focus) {
//    request_unit_change_homecity(punit_focus);
//  }
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void key_unit_irrigate()
//{
//  if (punit_focus &&
//      can_unit_do_activity(punit_focus, ACTIVITY_IRRIGATE)) {
//    request_new_unit_activity(punit_focus, ACTIVITY_IRRIGATE);
//  }
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void key_unit_mine()
//{
//  if (punit_focus &&
//      can_unit_do_activity(punit_focus, ACTIVITY_MINE)) {
//    request_new_unit_activity(punit_focus, ACTIVITY_MINE);
//  }
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void key_unit_pillage()
//{
//  if (punit_focus &&
//      can_unit_do_activity(punit_focus, ACTIVITY_PILLAGE)) {
//    request_unit_pillage(punit_focus);
//  }
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void key_unit_pollution()
//{
//  if (punit_focus &&
//      can_unit_do_activity(punit_focus, ACTIVITY_POLLUTION)) {
//    request_new_unit_activity(punit_focus, ACTIVITY_POLLUTION);
//  }
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void key_unit_road()
//{
//  if (punit_focus) {
//    if(can_unit_do_activity(punit_focus, ACTIVITY_ROAD))
//      request_new_unit_activity(punit_focus, ACTIVITY_ROAD);
//    else if(can_unit_do_activity(punit_focus, ACTIVITY_RAILROAD))
//      request_new_unit_activity(punit_focus, ACTIVITY_RAILROAD);
//  }
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void key_unit_sentry()
//{
//  if (punit_focus &&
//      can_unit_do_activity(punit_focus, ACTIVITY_SENTRY)) {
//    request_new_unit_activity(punit_focus, ACTIVITY_SENTRY);
//  }
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void key_unit_transform()
//{
//  if (punit_focus &&
//      can_unit_do_activity(punit_focus, ACTIVITY_TRANSFORM)) {
//    request_new_unit_activity(punit_focus, ACTIVITY_TRANSFORM);
//  }
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void key_map_grid_toggle()
//{
//  request_toggle_map_grid();
//}
//
///**************************************************************************
//  Toggle map borders on the mapview on/off based on a keypress.
//**************************************************************************/
//void key_map_borders_toggle()
//{
//  request_toggle_map_borders();
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void key_city_names_toggle()
//{
//  request_toggle_city_names();
//}
//
///**************************************************************************
//  Toggles the "show city growth turns" option by passing off the
//  request to another function...
//**************************************************************************/
//void key_city_growth_toggle()
//{
//  request_toggle_city_growth();
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void key_city_productions_toggle()
//{
//  request_toggle_city_productions();
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void key_terrain_toggle()
//{
//  request_toggle_terrain();
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void key_coastline_toggle()
//{
//  request_toggle_coastline();
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void key_roads_rails_toggle()
//{
//  request_toggle_roads_rails();
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void key_irrigation_toggle()
//{
//  request_toggle_irrigation();
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void key_mines_toggle()
//{
//  request_toggle_mines();
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void key_fortress_airbase_toggle()
//{
//  request_toggle_fortress_airbase();
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void key_specials_toggle()
//{
//  request_toggle_specials();
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void key_pollution_toggle()
//{
//  request_toggle_pollution();
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void key_cities_toggle()
//{
//  request_toggle_cities();
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void key_units_toggle()
//{
//  request_toggle_units();
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void key_focus_unit_toggle()
//{
//  request_toggle_focus_unit();
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void key_fog_of_war_toggle()
//{
//  request_toggle_fog_of_war();
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void key_quickselect(enum quickselect_type qtype)
//{
//  unit punit;
//
//  if(punit_focus) {
//    punit = quickselect(punit_focus.tile, qtype);
//    set_unit_focus_and_select(punit);
//  }
//}
}