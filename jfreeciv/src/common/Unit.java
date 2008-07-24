package common;
import static common.Player_P.pplayers_at_war;
import static common.Terrain_H.S_NO_SPECIAL;
import static common.Unittype_P.unit_flag;
import utility.Speclists;

import common.map.tile;
import common.player.player;
import common.unit.unit;
import common.unit.unit_activity;
import common.unittype.Eunit_flag_id;

public class Unit{
//#include "fcintl.h"
//#include "game.h"
//#include "log.h"
//#include "Map.map.h"
//#include "mem.h"
//#include "packets.h"
//#include "player.h"
//#include "shared.h"
//#include "support.h"
//#include "tech.h"
//
//#include "city.h"
//#include "unit.h"

	/***************************************************************************
	 * This function calculates the move rate of the unit taking into account
	 * the penalty for reduced hitpoints (affects sea and land units only) and
	 * the effects of wonders for sea units + veteran bonus.
	 * 
	 * FIXME: Use generalised improvements code instead of hardcoded wonder
	 * effects --RK
	 **************************************************************************/
	public static int unit_move_rate(unit punit) {
		//TODO
		return punit.move_rate();
	}

// /**************************************************************************
//bribe unit
//investigate
//poison
//make revolt
//establish embassy
//sabotage city
//**************************************************************************/
//
///**************************************************************************
//Whether a diplomat can move to a particular tile and perform a
//particular action there.
//**************************************************************************/
//boolean diplomat_can_do_action(unit pdiplomat,
//			    enum diplomat_actions action, 
//			    final tile ptile)
//{
//  if (!is_diplomat_action_available(pdiplomat, action, ptile)) {
//    return false;
//  }
//
//  if (!is_tiles_adjacent(pdiplomat.tile, ptile)
//      && !Map.same_pos(pdiplomat.tile, ptile)) {
//    return false;
//  }
//
//  if(pdiplomat.moves_left == 0)
//    return false;
//
//  return true;
//}
//
///**************************************************************************
//Whether a diplomat can perform a particular action at a particular
//tile.  This does _not_ check whether the diplomat can move there.
//If the action is DIPLOMAT_ANY_ACTION, checks whether there is any
//action the diplomat can perform at the tile.
//**************************************************************************/
//boolean is_diplomat_action_available(unit pdiplomat,
//				  enum diplomat_actions action, 
//				  final tile ptile)
//{
//  city pcity=map_get_city(ptile);
//
//  if (action!=DIPLOMAT_MOVE
//      && is_ocean(map_get_terrain(pdiplomat.tile))) {
//    return false;
//  }
//
//  if (pcity) {
//    if (pcity.owner != pdiplomat.owner
//       && real_map_distance(pdiplomat.tile, pcity.tile) <= 1) {
//      if(action==DIPLOMAT_SABOTAGE)
//	return pplayers_at_war(pdiplomat.unit_owner(), city_owner(pcity));
//      if(action==DIPLOMAT_MOVE)
//        return pplayers_allied(pdiplomat.unit_owner(), city_owner(pcity));
//      if (action == DIPLOMAT_EMBASSY && !is_barbarian(city_owner(pcity)) &&
//	  !player_has_embassy(pdiplomat.unit_owner(), city_owner(pcity)))
//	return true;
//      if(action==SPY_POISON &&
//	 pcity.size>1 &&
//	 unit_flag(pdiplomat, F_SPY))
//	return pplayers_at_war(pdiplomat.unit_owner(), city_owner(pcity));
//      if(action==DIPLOMAT_INVESTIGATE)
//        return true;
//      if (action == DIPLOMAT_STEAL && !is_barbarian(city_owner(pcity)))
//	return true;
//      if(action==DIPLOMAT_INCITE)
//        return !pplayers_allied(city_owner(pcity), pdiplomat.unit_owner());
//      if(action==DIPLOMAT_ANY_ACTION)
//        return true;
//      if (action==SPY_GET_SABOTAGE_LIST && unit_flag(pdiplomat, F_SPY))
//	return pplayers_at_war(pdiplomat.unit_owner(), city_owner(pcity));
//    }
//  } else { /* Action against a unit at a tile */
//    /* If it is made possible to do action against allied units
//       handle_unit_move_request() should be changed so that pdefender
//       is also set to allied units */
//    unit punit;
//
//    if ((action == SPY_SABOTAGE_UNIT || action == DIPLOMAT_ANY_ACTION) 
//        && unit_list_size(&ptile.units) == 1
//        && unit_flag(pdiplomat, F_SPY)) {
//      punit = unit_list_get(&ptile.units, 0);
//      if (pplayers_at_war(pdiplomat.unit_owner(), punit.unit_owner())) {
//        return true;
//      }
//    }
//
//    if ((action == DIPLOMAT_BRIBE || action == DIPLOMAT_ANY_ACTION)
//        && unit_list_size(&ptile.units) == 1) {
//      punit = unit_list_get(&ptile.units, 0);
//      if (!pplayers_allied(punit.unit_owner(), pdiplomat.unit_owner())) {
//        return true;
//      }
//    }
//  }
//  return false;
//}
//
///**************************************************************************
//FIXME: Maybe we should allow airlifts between allies
//**************************************************************************/
//boolean unit_can_airlift_to(unit punit, city pcity)
//{
//  if(punit.moves_left == 0)
//    return false;
//  if (!punit.tile.city) {
//    return false;
//  }
//  if (punit.tile.city == pcity) {
//    return false;
//  }
//  if (punit.tile.city.owner != pcity.owner) {
//    return false;
//  }
//  if (!punit.tile.city.airlift || !pcity.airlift) {
//    return false;
//  }
//  if (!is_ground_unit(punit))
//    return false;
//
//  return true;
//}
//
///****************************************************************************
//  Return true iff the unit is following client-side orders.
//****************************************************************************/
//boolean unit_has_orders(unit punit)
//{
//  return punit.has_orders;
//}
//
///**************************************************************************
//...
//**************************************************************************/
//boolean unit_can_help_build_wonder(unit punit, city pcity)
//{
//  if (!is_tiles_adjacent(punit.tile, pcity.tile)
//      && !Map.same_pos(punit.tile, pcity.tile))
//    return false;
//
//  return (unit_flag(punit, F_HELP_WONDER)
//	  && punit.owner == pcity.owner
//	  && !pcity.is_building_unit && is_wonder(pcity.currently_building)
//	  && (pcity.shield_stock
//	      < impr_build_shield_cost(pcity.currently_building)));
//}
//
//
///**************************************************************************
//...
//**************************************************************************/
//boolean unit_can_help_build_wonder_here(unit punit)
//{
//  city pcity = map_get_city(punit.tile);
//  return pcity && unit_can_help_build_wonder(punit, pcity);
//}
//
//
///**************************************************************************
//...
//**************************************************************************/
//boolean unit_can_est_traderoute_here(unit punit)
//{
//  city phomecity, *pdestcity;
//
//  return (unit_flag(punit, F_TRADE_ROUTE)
//	  && (pdestcity = map_get_city(punit.tile))
//	  && (phomecity = find_city_by_id(punit.homecity))
//	  && can_cities_trade(phomecity, pdestcity));
//}
//
///**************************************************************************
//...
//**************************************************************************/
//boolean unit_can_defend_here(unit punit)
//{
//  if (is_ground_unit(punit)
//      && is_ocean(map_get_terrain(punit.tile))) {
//    return false;
//  }
//  
//  return true;
//}
//
///**************************************************************************
//Returns the number of free spaces for ground units. Can be 0 or negative.
//**************************************************************************/
//int ground_unit_transporter_capacity(final tile ptile,
//				     player pplayer)
//{
//  int availability = 0;
//
//  for (unit punit : ptile.units.data) {
//    if (punit.unit_owner() == pplayer
//        || pplayers_allied(punit.unit_owner(), pplayer)) {
//      if (is_ground_units_transport(punit)
//	  && !(is_ground_unit(punit) && is_ocean(ptile.terrain))) {
//	availability += get_transporter_capacity(punit);
//      } else if (is_ground_unit(punit)) {
//	availability--;
//      }
//    }
//  }
//  }
//
//  return availability;
//}

	/***************************************************************************
	 * ...
	 **************************************************************************/
	public static int get_transporter_capacity(unit punit) {
		return punit.unit_type().transport_capacity;
	}

//	/***************************************************************************
//	 * ...
//	 **************************************************************************/
//	boolean is_ground_units_transport(unit punit) {
//		return (get_transporter_capacity(punit) > 0
//				&& !unit_flag(punit, F_MISSILE_CARRIER) && !unit_flag(punit,
//				F_CARRIER));
//	}
//
// /**************************************************************************
//...
//**************************************************************************/
//boolean is_air_units_transport(unit punit)
//{
//  return (get_transporter_capacity(punit) > 0
//	  && (unit_flag(punit, F_MISSILE_CARRIER)
//	      || unit_flag(punit, F_CARRIER)));
//}
//
///**************************************************************************
//...
//**************************************************************************/
//boolean is_sailing_unit(unit punit)
//{
//  return (punit.unit_type().move_type == SEA_MOVING);
//}
//
///**************************************************************************
//...
//**************************************************************************/
//boolean is_air_unit(unit punit)
//{
//  return (punit.unit_type().move_type == AIR_MOVING);
//}
//
///**************************************************************************
//...
//**************************************************************************/
//boolean is_heli_unit(unit punit)
//{
//  return (punit.unit_type().move_type == HELI_MOVING);
//}
//
///**************************************************************************
//...
//**************************************************************************/
//boolean is_ground_unit(unit punit)
//{
//  return (punit.unit_type().move_type == LAND_MOVING);
//}
//
///**************************************************************************
//  Is the unit capable of attacking?
//**************************************************************************/
//boolean is_attack_unit(unit punit)
//{
//  return (punit.unit_type().attack_strength > 0);
//}

	/***************************************************************************
	 * Military units are capable of enforcing martial law. Military ground and
	 * heli units can occupy empty cities -- see COULD_OCCUPY(punit). Some
	 * military units, like the Galleon, have no attack strength.
	 **************************************************************************/
	public static boolean is_military_unit(unit punit) {
		return !unit_flag(punit, Eunit_flag_id.F_NONMIL);
	}

///**************************************************************************
//...
//**************************************************************************/
//boolean is_diplomat_unit(unit punit)
//{
//  return (unit_flag(punit, F_DIPLOMAT));
//}
//
///**************************************************************************
//...
//**************************************************************************/
//static boolean is_ground_threat(player pplayer, unit punit)
//{
//  return (pplayers_at_war(pplayer, punit.unit_owner())
//	  && (unit_flag(punit, F_DIPLOMAT)
//	      || (is_ground_unit(punit) && is_military_unit(punit))));
//}
//
///**************************************************************************
//...
//**************************************************************************/
//boolean is_square_threatened(player pplayer, final tile ptile)
//{
//  square_iterate(ptile, 2, ptile1) {
//    for (unit punit : ptile1.units.data) {
//      if (is_ground_threat(pplayer, punit)) {
//	return true;
//      }
//    } }
//  } square_iterate_end;
//
//  return false;
//}
//
///**************************************************************************
//...
//**************************************************************************/
//boolean is_field_unit(unit punit)
//{
//  return unit_flag(punit, F_FIELDUNIT);
//}
//
//
///**************************************************************************
//  Is the unit one that is invisible on the Map.map. A unit is invisible if
//  it has the F_PARTIAL_INVIS flag or if it transported by a unit with
//  this flag.
//**************************************************************************/
//boolean is_hiding_unit(unit punit)
//{
//  unit transporter = find_unit_by_id(punit.transported_by);
//
//  return (unit_flag(punit, F_PARTIAL_INVIS)
//	  || (transporter && unit_flag(transporter, F_PARTIAL_INVIS)));
//}
//
///**************************************************************************
//...
//**************************************************************************/
//boolean kills_citizen_after_attack(unit punit)
//{
//  return TEST_BIT(game.killcitizen, (int) (punit.unit_type().move_type) - 1);
//}
//
///**************************************************************************
//...
//**************************************************************************/
//boolean can_unit_add_to_city(unit punit)
//{
//  return (test_unit_add_or_build_city(punit) == AB_ADD_OK);
//}
//
///**************************************************************************
//...
//**************************************************************************/
//boolean can_unit_build_city(unit punit)
//{
//  return (test_unit_add_or_build_city(punit) == AB_BUILD_OK);
//}
//
///**************************************************************************
//...
//**************************************************************************/
//boolean can_unit_add_or_build_city(unit punit)
//{
//  enum add_build_city_result r = test_unit_add_or_build_city(punit);
//  return (r == AB_BUILD_OK || r == AB_ADD_OK);
//}
//
///**************************************************************************
//...
//**************************************************************************/
//enum add_build_city_result test_unit_add_or_build_city(unit punit)
//{
//  city pcity = map_get_city(punit.tile);
//  boolean is_build = unit_flag(punit, F_CITIES);
//  boolean is_add = unit_flag(punit, F_ADD_TO_CITY);
//  int new_pop;
//
//  /* See if we can build */
//  if (!pcity) {
//    if (!is_build)
//      return AB_NOT_BUILD_UNIT;
//    if (punit.moves_left == 0)
//      return AB_NO_MOVES_BUILD;
//    if (!city_can_be_built_here(punit.tile, punit)) {
//      return AB_NOT_BUILD_LOC;
//    }
//    return AB_BUILD_OK;
//  }
//  
//  /* See if we can add */
//
//  if (!is_add)
//    return AB_NOT_ADDABLE_UNIT;
//  if (punit.moves_left == 0)
//    return AB_NO_MOVES_ADD;
//
//  assert(unit_pop_value(punit.type) > 0);
//  new_pop = pcity.size + unit_pop_value(punit.type);
//
//  if (new_pop > game.add_to_size_limit)
//    return AB_TOO_BIG;
//  if (pcity.owner != punit.owner)
//    return AB_NOT_OWNER;
//  if (!city_can_grow_to(pcity, new_pop))
//    return AB_NO_SPACE;
//  return AB_ADD_OK;
//}
//
///**************************************************************************
//...
//**************************************************************************/
//boolean can_unit_change_homecity_to(unit punit, city pcity)
//{
//  /* Requirements to change homecity:
//   *
//   * 1. Homeless cities can't change homecity (this is a feature since
//   *    being homeless is a big benefit).
//   * 2. The unit must be inside the city it is rehoming to.
//   * 3. Of course you can only have your own cities as homecity.
//   * 4. You can't rehome to the current homecity. */
//  return (punit && pcity
//	  && punit.homecity > 0
//	  && punit.tile.city
//	  && punit.tile.city.owner == punit.owner
//	  && punit.homecity != punit.tile.city.id);
//}
//  
///**************************************************************************
//  Return true iff the unit can change homecity at its current location.
//**************************************************************************/
//boolean can_unit_change_homecity(unit punit)
//{
//  return can_unit_change_homecity_to(punit, punit.tile.city);
//}
//
///**************************************************************************
//  Returns the speed of a unit doing an activity.  This depends on the
//  veteran level and the base move_rate of the unit (regardless of HP or
//  effects).  Usually this is just used for settlers but the value is also
//  used for military units doing fortify/pillage activities.
//
//  The speed is multiplied by ACTIVITY_COUNT.
//**************************************************************************/
//int get_activity_rate(unit punit)
//{
//  int fact = get_unit_type(punit.type).veteran[punit.veteran].power_fact;
//
//  /* The speed of the settler depends on its base move_rate, not on
//   * the number of moves actually remaining or the adjusted move rate.
//   * This means sea formers won't have their activity rate increased by
//   * Magellan's, and it means injured units work just as fast as
//   * uninjured ones.  Note the value is never less than Unit_H.SINGLE_MOVE. */
//  int move_rate = punit.unit_type().move_rate;
//
//  /* All settler actions are multiplied by ACTIVITY_COUNT. */
//  return ACTIVITY_FACTOR * fact * move_rate / Unit_H.SINGLE_MOVE;
//}
//
///**************************************************************************
//  Returns the amount of work a unit does (will do) on an activity this
//  turn.  Units that have no MP do no work.
//
//  The speed is multiplied by ACTIVITY_COUNT.
//**************************************************************************/
//int get_activity_rate_this_turn(unit punit)
//{
//  /* This logic is also coded in client/goto.c. */
//  if (punit.moves_left > 0) {
//    return get_activity_rate(punit);
//  } else {
//    return 0;
//  }
//}
//
///**************************************************************************
//  Return the estimated number of turns for the worker unit to start and
//  complete the activity at the given location.  This assumes no other
//  worker units are helping out.
//**************************************************************************/
//int get_turns_for_activity_at(unit punit,
//			      enum unit_activity activity,
//			      final tile ptile)
//{
//  /* FIXME: This is just an approximation since we don't account for
//   * get_activity_rate_this_turn. */
//  int speed = get_activity_rate(punit);
//  int time = map_activity_time(activity, ptile);
//
//  if (time >= 0 && speed >= 0) {
//    return (time - 1) / speed + 1; /* round up */
//  } else {
//    return FC_INFINITY;
//  }
//}
//
///**************************************************************************
//Return whether the unit can be put in auto-mode.
//(Auto-settler for settlers, auto-attack for military units.)
//**************************************************************************/
//boolean can_unit_do_auto(unit punit) 
//{
//  if (unit_flag(punit, F_SETTLERS)) {
//    return true;
//  }
//  if (is_military_unit(punit) && is_attack_unit(punit)
//      && map_get_city(punit.tile)) {
//    return true;
//  }
//  return false;
//}
//
///**************************************************************************
//  Return the name of the activity in a static buffer.
//**************************************************************************/
//final String get_activity_text(enum unit_activity activity)
//{
//  /* The switch statement has just the activities listed with no "default"
//   * handling.  This enables the compiler to detect missing entries
//   * automatically, and still handles everything correctly. */
//  switch (activity) {
//  case unit_activity.ACTIVITY_IDLE:
//    return "Idle";
//  case ACTIVITY_POLLUTION:
//    return "Pollution";
//  case ACTIVITY_ROAD:
//    return "Road";
//  case ACTIVITY_MINE:
//    return "Mine";
//  case ACTIVITY_IRRIGATE:
//    return "Irrigation";
//  case ACTIVITY_FORTIFYING:
//    return "Fortifying";
//  case ACTIVITY_FORTIFIED:
//    return "Fortified";
//  case ACTIVITY_FORTRESS:
//    return "Fortress";
//  case ACTIVITY_SENTRY:
//    return "Sentry";
//  case ACTIVITY_RAILROAD:
//    return "Railroad";
//  case ACTIVITY_PILLAGE:
//    return "Pillage";
//  case unit_activity.ACTIVITY_GOTO:
//    return "Goto";
//  case ACTIVITY_EXPLORE:
//    return "Explore";
//  case ACTIVITY_TRANSFORM:
//    return "Transform";
//  case ACTIVITY_AIRBASE:
//    return "Airbase";
//  case ACTIVITY_FALLOUT:
//    return "Fallout";
//  case ACTIVITY_UNKNOWN:
//  case ACTIVITY_PATROL_UNUSED:
//  case ACTIVITY_LAST:
//    break;
//  }
//
//  assert(0!=1);
//  return "Unknown";
//}
//
///****************************************************************************
//  Return true iff the given unit can be loaded into the transporter.
//****************************************************************************/
//boolean can_unit_load(unit pcargo, unit ptrans)
//{
//  /* This function needs to check EVERYTHING. */
//
//  if (!pcargo || !ptrans) {
//    return false;
//  }
//
//  /* Check positions of the units.  Of course you can't load a unit onto
//   * a transporter on a different tile... */
//  if (!Map.same_pos(pcargo.tile, ptrans.tile)) {
//    return false;
//  }
//
//  /* Double-check ownership of the units: you can load into an allied unit
//   * (of course only allied units can be on the same tile). */
//  if (!pplayers_allied(pcargo.unit_owner(), ptrans.unit_owner())) {
//    return false;
//  }
//
//  /* Only top-level transporters may be loaded or loaded into. */
//  if (pcargo.transported_by != -1 || ptrans.transported_by != -1) {
//    return false;
//  }
//
//  /* Recursive transporting is not allowed (for now). */
//  if (get_transporter_occupancy(pcargo) > 0) {
//    return false;
//  }
//
//  /* Make sure this transporter can carry this type of unit. */
//  if (is_ground_unit(pcargo)) {
//    if (!is_ground_units_transport(ptrans)) {
//      return false;
//    }
//  } else if (unit_flag(pcargo, F_MISSILE)) {
//    if (!is_air_units_transport(ptrans)) {
//      return false;
//    }
//  } else if (is_air_unit(pcargo) || is_heli_unit(pcargo)) {
//    if (!unit_flag(ptrans, F_CARRIER)) {
//      return false;
//    }
//  } else {
//    return false;
//  }
//
//  /* Make sure there's room in the transporter. */
//  return (get_transporter_occupancy(ptrans)
//	  < get_transporter_capacity(ptrans));
//}
//
///****************************************************************************
//  Return true iff the given unit can be unloaded from its current
//  transporter.
//
//  This function checks everything *except* the legality of the position
//  after the unloading.  The caller may also want to call
//  can_unit_exist_at_tile() to check this, unless the unit is unloading and
//  moving at the same time.
//****************************************************************************/
//boolean can_unit_unload(unit pcargo, unit ptrans)
//{
//  if (!pcargo || !ptrans) {
//    return false;
//  }
//
//  /* Make sure the unit's transporter exists and is known. */
//  if (pcargo.transported_by != ptrans.id) {
//    return false;
//  }
//
//  /* Only top-level transporters may be unloaded.  However the unit being
//   * unloaded may be transporting other units (well, at least it's allowed
//   * here: elsewhere this may be disallowed). */
//  if (ptrans.transported_by != -1) {
//    return false;
//  }
//
//  return true;
//}
//
///**************************************************************************
//Return whether the unit can be paradropped.
//That is if the unit is in a friendly city or on an Airbase
//special, have enough movepoints left and have not paradropped
//before in this turn.
//**************************************************************************/
//boolean can_unit_paradrop(unit punit)
//{
//  unit_type utype;
//
//  if (!unit_flag(punit, F_PARATROOPERS))
//    return false;
//
//  if(punit.paradropped)
//    return false;
//
//  utype = punit.unit_type();
//
//  if(punit.moves_left < utype.paratroopers_mr_req)
//    return false;
//
//  if (Map.tile_has_special(punit.tile, Terrain_H.S_AIRBASE)) {
//    return true;
//  }
//
//  if (!map_get_city(punit.tile)) {
//    return false;
//  }
//
//  return true;
//}
//
///**************************************************************************
//  Return whether the unit can bombard.
//  Basically if it is a bombarder, isn't being transported, and hasn't 
//  moved this turn.
//**************************************************************************/
//boolean can_unit_bombard(unit punit)
//{
//  if (!unit_flag(punit, F_BOMBARDER)) {
//    return false;
//  }
//
//  if (punit.transported_by != -1) {
//    return false;
//  }
//
//  return true;
//}
//
///**************************************************************************
//Check if the unit's current activity is actually legal.
//**************************************************************************/
//boolean can_unit_continue_current_activity(unit punit)
//{
//  enum unit_activity current = punit.activity;
//  enum int target = punit.activity_target;
//  enum unit_activity current2 = 
//              (current == ACTIVITY_FORTIFIED) ? ACTIVITY_FORTIFYING : current;
//  boolean result;
//
//  punit.activity = unit_activity.ACTIVITY_IDLE;
//  punit.activity_target = S_NO_SPECIAL;
//
//  result = can_unit_do_activity_targeted(punit, current2, target);
//
//  punit.activity = current;
//  punit.activity_target = target;
//
//  return result;
//}
//
///**************************************************************************
//...
//**************************************************************************/
//boolean can_unit_do_activity(unit punit, enum unit_activity activity)
//{
//  return can_unit_do_activity_targeted(punit, activity, S_NO_SPECIAL);
//}
//
///**************************************************************************
//  Return whether the unit can do the targeted activity at its current
//  location.
//**************************************************************************/
//boolean can_unit_do_activity_targeted(unit punit,
//				   enum unit_activity activity,
//				   enum int target)
//{
//  return can_unit_do_activity_targeted_at(punit, activity, target,
//					  punit.tile);
//}
//
///**************************************************************************
//  Return true if the unit can do the targeted activity at the given
//  location.
//
//  Note that if you make changes here you should also change the code for
//  autosettlers in server/settler.c. The code there does not use this
//  function as it would be a major CPU hog.
//**************************************************************************/
//boolean can_unit_do_activity_targeted_at(unit punit,
//				      enum unit_activity activity,
//				      enum int target,
//				      final tile ptile)
//{
//  player pplayer = punit.unit_owner();
//  tile_type type = get_tile_type(ptile.terrain);
//
//  switch(activity) {
//  case unit_activity.ACTIVITY_IDLE:
//  case unit_activity.ACTIVITY_GOTO:
//    return true;
//
//  case ACTIVITY_POLLUTION:
//    return (unit_flag(punit, F_SETTLERS)
//	    && Map.tile_has_special(ptile, S_POLLUTION));
//
//  case ACTIVITY_FALLOUT:
//    return (unit_flag(punit, F_SETTLERS)
//	    && Map.tile_has_special(ptile, S_FALLOUT));
//
//  case ACTIVITY_ROAD:
//    return (terrain_control.may_road
//	    && unit_flag(punit, F_SETTLERS)
//	    && !Map.tile_has_special(ptile, S_ROAD)
//	    && type.road_time != 0
//	    && (!Map.tile_has_special(ptile, S_RIVER)
//		|| player_knows_techs_with_flag(pplayer, TF_BRIDGE)));
//
//  case ACTIVITY_MINE:
//    /* Don't allow it if someone else is irrigating this tile.
//     * *Do* allow it if they're transforming - the mine may survive */
//    if (terrain_control.may_mine
//	&& unit_flag(punit, F_SETTLERS)
//	&& ((ptile.terrain == type.mining_result
//	     && !Map.tile_has_special(ptile, S_MINE))
//	    || (ptile.terrain != type.mining_result
//		&& type.mining_result != T_NONE
//		&& (!is_ocean(ptile.terrain)
//		    || is_ocean(type.mining_result)
//		    || can_reclaim_ocean(ptile))
//		&& (is_ocean(ptile.terrain)
//		    || !is_ocean(type.mining_result)
//		    || can_channel_land(ptile))
//		&& (!is_ocean(type.mining_result)
//		    || !map_get_city(ptile))))) {
//      for (unit tunit : ptile.units.data) {
//	if (tunit.activity == ACTIVITY_IRRIGATE) {
//	  return false;
//	}
//      } }
//      return true;
//    } else {
//      return false;
//    }
//
//  case ACTIVITY_IRRIGATE:
//    /* Don't allow it if someone else is mining this tile.
//     * *Do* allow it if they're transforming - the irrigation may survive */
//    if (terrain_control.may_irrigate
//	&& unit_flag(punit, F_SETTLERS)
//	&& (!Map.tile_has_special(ptile, S_IRRIGATION)
//	    || (!Map.tile_has_special(ptile, S_FARMLAND)
//		&& player_knows_techs_with_flag(pplayer, TF_FARMLAND)))
//	&& ((ptile.terrain == type.irrigation_result
//	     && is_water_adjacent_to_tile(ptile))
//	    || (ptile.terrain != type.irrigation_result
//		&& type.irrigation_result != T_NONE
//		&& (!is_ocean(ptile.terrain)
//		    || is_ocean(type.irrigation_result)
//		    || can_reclaim_ocean(ptile))
//		&& (is_ocean(ptile.terrain)
//		    || !is_ocean(type.irrigation_result)
//		    || can_channel_land(ptile))
//		&& (!is_ocean(type.irrigation_result)
//		    || !map_get_city(ptile))))) {
//      for (unit tunit : ptile.units.data) {
//	if (tunit.activity == ACTIVITY_MINE) {
//	  return false;
//	}
//      } }
//      return true;
//    } else {
//      return false;
//    }
//
//  case ACTIVITY_FORTIFYING:
//    return (is_ground_unit(punit)
//	    && punit.activity != ACTIVITY_FORTIFIED
//	    && !unit_flag(punit, F_SETTLERS)
//	    && !is_ocean(ptile.terrain));
//
//  case ACTIVITY_FORTIFIED:
//    return false;
//
//  case ACTIVITY_FORTRESS:
//    return (unit_flag(punit, F_SETTLERS)
//	    && !map_get_city(ptile)
//	    && player_knows_techs_with_flag(pplayer, TF_FORTRESS)
//	    && !Map.tile_has_special(ptile, S_FORTRESS)
//	    && !is_ocean(ptile.terrain));
//
//  case ACTIVITY_AIRBASE:
//    return (unit_flag(punit, F_AIRBASE)
//	    && player_knows_techs_with_flag(pplayer, TF_AIRBASE)
//	    && !Map.tile_has_special(ptile, Terrain_H.S_AIRBASE)
//	    && !is_ocean(ptile.terrain));
//
//  case ACTIVITY_SENTRY:
//    if (!can_unit_survive_at_tile(punit, punit.tile)
//	&& punit.transported_by == -1) {
//      /* Don't let units sentry on tiles they will die on. */
//      return false;
//    }
//    return true;
//
//  case ACTIVITY_RAILROAD:
//    /* if the tile has road, the terrain must be ok.. */
//    return (terrain_control.may_road
//	    && unit_flag(punit, F_SETTLERS)
//	    && Map.tile_has_special(ptile, S_ROAD)
//	    && !Map.tile_has_special(ptile, S_RAILROAD)
//	    && player_knows_techs_with_flag(pplayer, TF_RAILROAD));
//
//  case ACTIVITY_PILLAGE:
//    {
//      enum int pspresent = get_tile_infrastructure_set(ptile);
//      enum int psworking;
//
//      if (pspresent != S_NO_SPECIAL && is_ground_unit(punit)) {
//	psworking = get_unit_tile_pillage_set(ptile);
//	if (ptile.city && (contains_special(target, S_ROAD)
//			    || contains_special(target, S_RAILROAD))) {
//	  return false;
//	}
//	if (target == S_NO_SPECIAL) {
//	  if (ptile.city) {
//	    return ((pspresent & (~(psworking | S_ROAD | S_RAILROAD)))
//		    != S_NO_SPECIAL);
//	  } else {
//	    return ((pspresent & (~psworking)) != S_NO_SPECIAL);
//	  }
//	} else if (!game.rgame.pillage_select
//		   && target != get_preferred_pillage(pspresent)) {
//	  return false;
//	} else {
//	  return ((pspresent & (~psworking) & target) != S_NO_SPECIAL);
//	}
//      } else {
//	return false;
//      }
//    }
//
//  case ACTIVITY_EXPLORE:
//    return (is_ground_unit(punit) || is_sailing_unit(punit));
//
//  case ACTIVITY_TRANSFORM:
//    return (terrain_control.may_transform
//	    && type.transform_result != T_NONE
//	    && ptile.terrain != type.transform_result
//	    && (!is_ocean(ptile.terrain)
//		|| is_ocean(type.transform_result)
//		|| can_reclaim_ocean(ptile))
//	    && (is_ocean(ptile.terrain)
//		|| !is_ocean(type.transform_result)
//		|| can_channel_land(ptile))
//	    && (!terrain_has_flag(type.transform_result, TER_NO_CITIES)
//		|| !(map_get_city(ptile)))
//	    && unit_flag(punit, F_TRANSFORM));
//
//  case ACTIVITY_PATROL_UNUSED:
//  case ACTIVITY_LAST:
//  case ACTIVITY_UNKNOWN:
//    break;
//  }
//  util.freelog(Log.LOG_ERROR,
//	  "Unknown activity %d in can_unit_do_activity_targeted_at()",
//	  activity);
//  return false;
//}

	/*******************************************************************************
	 * assign a new task to a unit.
	 ******************************************************************************/
	public static void set_unit_activity(unit punit, unit_activity new_activity)
	{
		punit.activity=new_activity;
		punit.activity_count=0;
		punit.activity_target = S_NO_SPECIAL;
		if (new_activity == unit_activity.ACTIVITY_IDLE && punit.moves_left > 0) {
			/* No longer done. */
			punit.done_moving = false;
		}
	}

///**************************************************************************
//  assign a new targeted task to a unit.
//**************************************************************************/
//void set_unit_activity_targeted(unit punit,
//				enum unit_activity new_activity,
//				enum int new_target)
//{
//  set_unit_activity(punit, new_activity);
//  punit.activity_target = new_target;
//}
//
///**************************************************************************
//...
//**************************************************************************/
//boolean is_unit_activity_on_tile(enum unit_activity activity,
//			      final tile ptile)
//{
//  unit_list_iterate(ptile.units, punit) 
//    if(punit.activity==activity)
//      return true;
//  }
//  return false;
//}
//
///**************************************************************************
//...
//**************************************************************************/
//enum int get_unit_tile_pillage_set(final tile ptile)
//{
//  enum int tgt_ret = S_NO_SPECIAL;
//
//  unit_list_iterate(ptile.units, punit)
//    if(punit.activity==ACTIVITY_PILLAGE)
//      tgt_ret |= punit.activity_target;
//  }
//  return tgt_ret;
//}
//
///**************************************************************************
//  Return text describing the current unit's activity.
//**************************************************************************/
//final String unit_activity_text(unit punit)
//{
//  static char text[64];
//  final String moves_str;
//   
//  switch(punit.activity) {
//   case unit_activity.ACTIVITY_IDLE:
//     moves_str = "Moves";
//     if (is_air_unit(punit) && punit.unit_type().fuel > 0) {
//       int rate,f;
//       rate=punit.unit_type().move_rate/Unit_H.SINGLE_MOVE;
//       f=((punit.fuel)-1);
//      if ((punit.moves_left % Unit_H.SINGLE_MOVE) != 0) {
//	 if(punit.moves_left/Unit_H.SINGLE_MOVE>0) {
//	   text = util.my_snprintf( "%s: (%d)%d %d/%d", moves_str,
//		       ((rate*f)+(punit.moves_left/Unit_H.SINGLE_MOVE)),
//		       punit.moves_left/Unit_H.SINGLE_MOVE, punit.moves_left%Unit_H.SINGLE_MOVE,
//		       Unit_H.SINGLE_MOVE);
//	 } else {
//	   text = util.my_snprintf( "%s: (%d)%d/%d", moves_str,
//		       ((rate*f)+(punit.moves_left/Unit_H.SINGLE_MOVE)),
//		       punit.moves_left%Unit_H.SINGLE_MOVE, Unit_H.SINGLE_MOVE);
//	 }
//       } else {
//	 text = util.my_snprintf( "%s: (%d)%d", moves_str,
//		     rate*f+punit.moves_left/Unit_H.SINGLE_MOVE,
//		     punit.moves_left/Unit_H.SINGLE_MOVE);
//       }
//     } else {
//      if ((punit.moves_left % Unit_H.SINGLE_MOVE) != 0) {
//	 if(punit.moves_left/Unit_H.SINGLE_MOVE>0) {
//	   text = util.my_snprintf( "%s: %d %d/%d", moves_str,
//		       punit.moves_left/Unit_H.SINGLE_MOVE, punit.moves_left%Unit_H.SINGLE_MOVE,
//		       Unit_H.SINGLE_MOVE);
//	 } else {
//	   text = util.my_snprintf(
//		       "%s: %d/%d", moves_str, punit.moves_left%Unit_H.SINGLE_MOVE,
//		       Unit_H.SINGLE_MOVE);
//	 }
//       } else {
//	 text = util.my_snprintf(
//		     "%s: %d", moves_str, punit.moves_left/Unit_H.SINGLE_MOVE);
//       }
//     }
//     return text;
//   case ACTIVITY_POLLUTION:
//   case ACTIVITY_FALLOUT:
//   case ACTIVITY_ROAD:
//   case ACTIVITY_RAILROAD:
//   case ACTIVITY_MINE: 
//   case ACTIVITY_IRRIGATE:
//   case ACTIVITY_TRANSFORM:
//   case ACTIVITY_FORTIFYING:
//   case ACTIVITY_FORTIFIED:
//   case ACTIVITY_AIRBASE:
//   case ACTIVITY_FORTRESS:
//   case ACTIVITY_SENTRY:
//   case unit_activity.ACTIVITY_GOTO:
//   case ACTIVITY_EXPLORE:
//     return get_activity_text (punit.activity);
//   case ACTIVITY_PILLAGE:
//     if(punit.activity_target == S_NO_SPECIAL) {
//       return get_activity_text (punit.activity);
//     } else {
//       text = util.my_snprintf( "%s: %s",
//		   get_activity_text (punit.activity),
//		   map_get_infrastructure_text(punit.activity_target));
//       return (text);
//     }
//   default:
//    util.die("Unknown unit activity %d in unit_activity_text()", punit.activity);
//  }
//  return null;
//}

	/***************************************************************************
	 * ...
	 **************************************************************************/
	public static unit unit_list_find(Speclists<unit> This, int id)
	{
		for(unit punit: This.data){
//		unit_list_iterate(*This, punit) {
			if (punit.id == id) {
				return punit;
			}
		} 

		return null;
	}

///**************************************************************************
// Comparison function for genlist_sort, sorting by ord_map:
// The indirection is a bit gory:
// Read from the right:
//   1. cast arg "a" to "ptr to void*"   (we're sorting a list of "void*"'s)
//   2. dereference to get the "void*"
//   3. cast that "void*" to a "struct unit*"
//**************************************************************************/
//static int compar_unit_ord_map(final void *a, final void *b)
//{
//  final unit ua, *ub;
//  ua = (final struct unit*) *(final void**)a;
//  ub = (final struct unit*) *(final void**)b;
//  return ua.ord_map - ub.ord_map;
//}
//
///**************************************************************************
// Comparison function for genlist_sort, sorting by ord_city: see above.
//**************************************************************************/
//static int compar_unit_ord_city(final void *a, final void *b)
//{
//  final unit ua, *ub;
//  ua = (final struct unit*) *(final void**)a;
//  ub = (final struct unit*) *(final void**)b;
//  return ua.ord_city - ub.ord_city;
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void unit_list_sort_ord_map(unit_list This)
//{
//  if (This.foo_list_size() > 1) {
//    genlist_sort(&This.list, compar_unit_ord_map);
//  }
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void unit_list_sort_ord_city(unit_list This)
//{
//  if (This.foo_list_size() > 1) {
//    genlist_sort(&This.list, compar_unit_ord_city);
//  }
//}

	/***************************************************************************
	 * ...
	 **************************************************************************/
	public static player unit_owner(unit punit)
	{
		return punit.unit_owner();
	}

///****************************************************************************
//  Measure the carrier (missile + airplane) capacity of the given tile for
//  a player.
//
//  In the future this should probably look at the actual occupancy of the
//  transporters.  However for now we only look at the potential capacity and
//  leave loading up to the caller.
//****************************************************************************/
//static void count_carrier_capacity(int *airall, int *misonly,
//				   final tile ptile,
//				   player pplayer,
//				   boolean count_units_with_extra_fuel)
//{
//  *airall = *misonly = 0;
//
//  for (unit punit : ptile.units.data) {
//    if (punit.unit_owner() == pplayer) {
//      if (unit_flag(punit, F_CARRIER)
//	  && !(is_ground_unit(punit) && is_ocean(ptile.terrain))) {
//	*airall += get_transporter_capacity(punit);
//	continue;
//      }
//      if (unit_flag(punit, F_MISSILE_CARRIER)
//	  && !(is_ground_unit(punit) && is_ocean(ptile.terrain))) {
//	*misonly += get_transporter_capacity(punit);
//	continue;
//      }
//
//      /* Don't count units which have enough fuel (>1) */
//      if (is_air_unit(punit)
//	  && (count_units_with_extra_fuel || punit.fuel <= 1)) {
//	if (unit_flag(punit, F_MISSILE)) {
//	  (*misonly)--;
//	} else {
//	  (*airall)--;
//	}
//      }
//    }
//  } }
//}
//
///**************************************************************************
//Returns the number of free spaces for missiles. Can be 0 or negative.
//**************************************************************************/
//int missile_carrier_capacity(final tile ptile,
//			     player pplayer,
//			     boolean count_units_with_extra_fuel)
//{
//  int airall, misonly;
//
//  count_carrier_capacity(&airall, &misonly, ptile, pplayer,
//			 count_units_with_extra_fuel);
//
//  /* Any extra air spaces can be used by missles, but if there aren't enough
//   * air spaces this doesn't bother missiles. */
//  return MAX(airall, 0) + misonly;
//}
//
///**************************************************************************
//Returns the number of free spaces for airunits (includes missiles).
//Can be 0 or negative.
//**************************************************************************/
//int airunit_carrier_capacity(final tile ptile,
//			     player pplayer,
//			     boolean count_units_with_extra_fuel)
//{
//  int airall, misonly;
//
//  count_carrier_capacity(&airall, &misonly, ptile, pplayer,
//			 count_units_with_extra_fuel);
//
//  /* Any extra missile spaces are useless to air units, but if there aren't
//   * enough missile spaces the missles must take up airunit capacity. */
//  return airall + Math.min(misonly, 0);
//}
//
///**************************************************************************
//Returns true if the tile contains an allied unit and only allied units.
//(ie, if your nation A is allied with B, and B is allied with C, a tile
//containing units from B and C will return false)
//**************************************************************************/
//unit is_allied_unit_tile(final tile ptile,
//				 player pplayer)
//{
//  unit punit = null;
//
//  for (unit cunit : ptile.units.data) {
//    if (pplayers_allied(pplayer, cunit.unit_owner()))
//      punit = cunit;
//    else
//      return null;
//  }
//  }
//
//  return punit;
//}

	/***************************************************************************
	 * is there an enemy unit on this tile?
	 **************************************************************************/
	public static unit is_enemy_unit_tile(final tile ptile,
			player pplayer) {
		for (unit punit : ptile.units.data) {
			if (pplayers_at_war(punit.unit_owner(), pplayer))
				return punit;
		}
		return null;
	}

	/***************************************************************************
	 * is there an non-allied unit on this tile?
	 **************************************************************************/
	public static unit is_non_allied_unit_tile(final tile ptile,
			player pplayer)
	{
//		for (unit punit : ptile.units.data) {
//			if (!pplayers_allied(punit.unit_owner(), pplayer))
//				return punit;
//		}

		return null;
	}

///**************************************************************************
// is there an unit we have peace or ceasefire with on this tile?
//**************************************************************************/
//unit is_non_attack_unit_tile(final tile ptile,
//				     player pplayer)
//{
//  for (unit punit : ptile.units.data) {
//    if (pplayers_non_attack(punit.unit_owner(), pplayer))
//      return punit;
//  }
//  }
//
//  return null;
//}
//
///**************************************************************************
//  Is this square controlled by the pplayer?
//
//  Here "is_my_zoc" means essentially a square which is *not* adjacent to an
//  enemy unit on a land tile.
//
//  Note this function only makes sense for ground units.
//
//  Since this function is also used in the client, it has to deal with some
//  client-specific features, like FoW and the fact that the client cannot 
//  see units inside enemy cities.
//**************************************************************************/
//boolean is_my_zoc(player pplayer, final tile ptile0)
//{
//  square_iterate(ptile0, 1, ptile) {
//    if (is_ocean(ptile.terrain)) {
//      continue;
//    }
//    if (is_non_allied_unit_tile(ptile, pplayer)) {
//      /* Note: in the client, the above function will return null 
//       * if there is a city there, even if the city is occupied */
//      return false;
//    }
//    
//    if (!is_server) {
//      city pcity = is_non_allied_city_tile(ptile, pplayer);
//
//      if (pcity 
//          && (pcity.client.occupied 
//              || map_get_known(ptile, pplayer) == TILE_KNOWN_FOGGED)) {
//        /* If the city is fogged, we assume it's occupied */
//        return false;
//      }
//    }
//  } square_iterate_end;
//
//  return true;
//}
//
///**************************************************************************
//  Takes into account unit move_type as well as IGZOC
//**************************************************************************/
//boolean unit_type_really_ignores_zoc(int type)
//{
//  return (!is_ground_unittype(type)) || (unit_type_flag(type, F_IGZOC));
//}
//
///**************************************************************************
//  Returns whether the unit is allowed (by ZOC) to move from (src_x,src_y)
//  to (dest_x,dest_y) (assumed adjacent).
//  You CAN move if:
//  1. You have units there already
//  2. Your unit isn't a ground unit
//  3. Your unit ignores ZOC (diplomat, freight, etc.)
//  4. You're moving from or to a city
//  5. You're moving from an ocean square (from a boat)
//  6. The spot you're moving from or to is in your ZOC
//**************************************************************************/
//boolean can_step_taken_wrt_to_zoc(int type,
//			       player unit_owner,
//			       final tile src_tile,
//			       final tile dst_tile)
//{
//  if (unit_type_really_ignores_zoc(type))
//    return true;
//  if (is_allied_unit_tile(dst_tile, unit_owner)) {
//    return true;
//  }
//  if (map_get_city(src_tile) || map_get_city(dst_tile)) {
//    return true;
//  }
//  if (is_ocean(map_get_terrain(src_tile))
//      || is_ocean(map_get_terrain(dst_tile))) {
//    return true;
//  }
//  return (is_my_zoc(unit_owner, src_tile)
//	  || is_my_zoc(unit_owner, dst_tile));
//}
//
///**************************************************************************
//...
//**************************************************************************/
//static boolean zoc_ok_move_gen(unit punit, final tile ptile1,
//			    final tile ptile2)
//{
//  return can_step_taken_wrt_to_zoc(punit.type, punit.unit_owner(),
//				   ptile1, ptile2);
//}
//
///**************************************************************************
//  Convenience wrapper for zoc_ok_move_gen(), using the unit's (x,y)
//  as the starting point.
//**************************************************************************/
//boolean zoc_ok_move(unit punit, final tile ptile)
//{
//  return zoc_ok_move_gen(punit, punit.tile, ptile);
//}
//
///****************************************************************************
//  Return true iff the unit can "exist" at this location.  This means it can
//  physically be present on the tile (without the use of a transporter).
//****************************************************************************/
//boolean can_unit_exist_at_tile(unit punit, final tile ptile)
//{
//  if (ptile.city) {
//    return true;
//  }
//
//  switch (unit_types[punit.type].move_type) {
//  case LAND_MOVING:
//    return !is_ocean(ptile.terrain);
//  case SEA_MOVING:
//    return is_ocean(ptile.terrain);
//  case AIR_MOVING:
//  case HELI_MOVING:
//    return true;
//  }
//  util.die("Invalid move type");
//  return false;
//}
//
///****************************************************************************
//  Return true iff the unit can "survive" at this location.  This means it can
//  not only be phsically present at the tile but will be able to survive
//  indefinitely on its own (without a transporter).  Units that require fuel
//  or have a danger of drowning are examples of non-survivable units.
//****************************************************************************/
//boolean can_unit_survive_at_tile(unit punit, final tile ptile)
//{
//  if (!can_unit_exist_at_tile(punit, ptile)) {
//    return false;
//  }
//
//  if (map_get_city(ptile)) {
//    return true;
//  }
//
//  /* TODO: check for dangerous positions (like triremes in deep water). */
//
//  switch (unit_types[punit.type].move_type) {
//  case LAND_MOVING:
//  case SEA_MOVING:
//    return true;
//  case AIR_MOVING:
//  case HELI_MOVING:
//    return Map.tile_has_special(punit.tile, Terrain_H.S_AIRBASE);
//  }
//  util.die("Invalid move type");
//  return true;
//}
//
///**************************************************************************
//  Convenience wrapper for test_unit_move_to_tile.
//**************************************************************************/
//boolean can_unit_move_to_tile(unit punit, final tile dst_tile,
//			   boolean igzoc)
//{
//  return MR_OK == test_unit_move_to_tile(punit.type, punit.unit_owner(),
//					 punit.activity,
//					 punit.tile, dst_tile,
//					 igzoc);
//}
//
///**************************************************************************
//  unit can be moved if:
//  1) the unit is idle or on server goto.
//  2) the target location is on the map
//  3) the target location is next to the unit
//  4) there are no non-allied units on the target tile
//  5) a ground unit can only move to ocean squares if there
//     is a transporter with free capacity
//  6) marines are the only units that can attack from a ocean square
//  7) naval units can only be moved to ocean squares or city squares
//  8) there are no peaceful but un-allied units on the target tile
//  9) there is not a peaceful but un-allied city on the target tile
//  10) there is no non-allied unit blocking (zoc) [or igzoc is true]
//**************************************************************************/
//enum unit_move_result test_unit_move_to_tile(int type,
//					     player unit_owner,
//					     enum unit_activity activity,
//					     final tile pfromtile,
//					     final tile ptotile,
//					     boolean igzoc)
//{
//  boolean zoc;
//  city pcity;
//
//  /* 1) */
//  if (activity != unit_activity.ACTIVITY_IDLE
//      && activity != unit_activity.ACTIVITY_GOTO) {
//    return MR_BAD_ACTIVITY;
//  }
//
//  /* 3) */
//  if (!is_tiles_adjacent(pfromtile, ptotile)) {
//    return MR_BAD_DESTINATION;
//  }
//
//  /* 4) */
//  if (is_non_allied_unit_tile(ptotile, unit_owner)) {
//    return MR_DESTINATION_OCCUPIED_BY_NON_ALLIED_UNIT;
//  }
//
//  if (unit_types[type].move_type == LAND_MOVING) {
//    /* 5) */
//    if (is_ocean(ptotile.terrain) &&
//	ground_unit_transporter_capacity(ptotile, unit_owner) <= 0) {
//      return MR_NO_SEA_TRANSPORTER_CAPACITY;
//    }
//
//    /* Moving from ocean */
//    if (is_ocean(pfromtile.terrain)) {
//      /* 6) */
//      if (!unit_type_flag(type, F_MARINES)
//	  && is_enemy_city_tile(ptotile, unit_owner)) {
//	return MR_BAD_TYPE_FOR_CITY_TAKE_OVER;
//      }
//    }
//  } else if (unit_types[type].move_type == SEA_MOVING) {
//    /* 7) */
//    if (!is_ocean(ptotile.terrain)
//	&& ptotile.terrain != T_UNKNOWN
//	&& (!City.is_allied_city_tile(ptotile, unit_owner)
//	    || !is_ocean_near_tile(ptotile))) {
//      return MR_DESTINATION_OCCUPIED_BY_NON_ALLIED_CITY;
//    }
//  }
//
//  /* 8) */
//  if (is_non_attack_unit_tile(ptotile, unit_owner)) {
//    return MR_NO_WAR;
//  }
//
//  /* 9) */
//  pcity = ptotile.city;
//  if (pcity && pplayers_non_attack(city_owner(pcity), unit_owner)) {
//    return MR_NO_WAR;
//  }
//
//  /* 10) */
//  zoc = igzoc
//    || can_step_taken_wrt_to_zoc(type, unit_owner, pfromtile, ptotile);
//  if (!zoc) {
//    return MR_ZOC;
//  }
//
//  return MR_OK;
//}
//
///**************************************************************************
//  Calculate the chance of losing (as a percentage) if it were to spend a
//  turn at the given location.
//
//  Note this function isn't really useful for AI planning, since it needs
//  to know more.  The AI code uses base_trireme_loss_pct and
//  base_unsafe_terrain_loss_pct directly.
//**************************************************************************/
//int unit_loss_pct(player pplayer, final tile ptile,
//		  unit punit)
//{
//  int loss_pct = 0;
//
//  /* Units are never lost if they're inside cities. */
//  if (map_get_city(ptile)) {
//    return 0; 
//  }
//
//  /* Trireme units may be lost if they stray from coastline. */
//  if (unit_flag(punit, F_TRIREME)) {
//    if (!is_safe_ocean(ptile)) {
//      loss_pct = base_trireme_loss_pct(pplayer, punit);
//    }
//  }
//
//  /* All units may be lost on unsafe terrain.  (Actually air units are
//   * exempt; see base_unsafe_terrain_loss_pct.) */
//  if (terrain_has_flag(map_get_terrain(ptile), TER_UNSAFE)) {
//    return loss_pct + base_unsafe_terrain_loss_pct(pplayer, punit);
//  }
//
//  return loss_pct;
//}
//
///**************************************************************************
//  Triremes have a varying loss percentage based on tech and veterancy
//  level.
//**************************************************************************/
//int base_trireme_loss_pct(player pplayer, unit punit)
//{
//  if (get_player_bonus(pplayer, EFT_NO_SINK_DEEP) > 0) {
//    return 0;
//  } else if (player_knows_techs_with_flag(pplayer, TF_REDUCE_TRIREME_LOSS2)) {
//    return game.trireme_loss_chance[punit.veteran] / 4;
//  } else if (player_knows_techs_with_flag(pplayer, TF_REDUCE_TRIREME_LOSS1)) {
//    return game.trireme_loss_chance[punit.veteran] / 2;
//  } else {
//    return game.trireme_loss_chance[punit.veteran];
//  }
//}
//
///**************************************************************************
//  All units except air units have a flat 15% chance of being lost.
//**************************************************************************/
//int base_unsafe_terrain_loss_pct(player pplayer, unit punit)
//{
//  return (is_air_unit(punit) || is_heli_unit(punit)) ? 0 : 15;
//}
//
///**************************************************************************
//An "aggressive" unit is a unit which may cause unhappiness
//under a Republic or Democracy.
//A unit is *not* aggressive if one or more of following is true:
//- zero attack strength
//- inside a city
//- ground unit inside a fortress within 3 squares of a friendly city
//**************************************************************************/
//boolean unit_being_aggressive(unit punit)
//{
//  if (!is_attack_unit(punit))
//    return false;
//  if (map_get_city(punit.tile))
//    return false;
//  if (game.borders > 0
//      && game.happyborders
//      && map_get_owner(punit.tile) == punit.unit_owner()) {
//    return false;
//  }
//  if (is_ground_unit(punit) &&
//      map_has_special(punit.tile, S_FORTRESS))
//    return !is_unit_near_a_friendly_city (punit);
//  
//  return true;
//}
//
///*
// * Returns true if given activity is some kind of building/cleaning.
// */
//boolean is_build_or_clean_activity(enum unit_activity activity)
//{
//  switch (activity) {
//  case ACTIVITY_POLLUTION:
//  case ACTIVITY_ROAD:
//  case ACTIVITY_MINE:
//  case ACTIVITY_IRRIGATE:
//  case ACTIVITY_FORTRESS:
//  case ACTIVITY_RAILROAD:
//  case ACTIVITY_TRANSFORM:
//  case ACTIVITY_AIRBASE:
//  case ACTIVITY_FALLOUT:
//    return true;
//  default:
//    return false;
//  }
//}
//
///**************************************************************************
//  Create a virtual unit skeleton. pcity can be null, but then you need
//  to set x, y and homecity yourself.
//**************************************************************************/
//unit create_unit_virtual(player pplayer, city pcity,
//                                 int type, int veteran_level)
//{
//  unit punit = fc_calloc(1, sizeof(struct unit));
//
//  punit.type = type;
//  punit.owner = pplayer.player_no;
//  if (pcity) {
//    punit.tile = pcity.tile;
//    punit.homecity = pcity.id;
//  } else {
//    punit.tile = null;
//    punit.homecity = 0;
//  }
//  punit.goto_tile = null;
//  punit.veteran = veteran_level;
//  punit.upkeep = 0;
//  punit.upkeep_food = 0;
//  punit.upkeep_gold = 0;
//  punit.unhappiness = 0;
//  /* A unit new and fresh ... */
//  punit.foul = false;
//  punit.debug = false;
//  punit.fuel = punit.unit_type().fuel;
//  punit.hp = punit.unit_type().hp;
//  punit.moves_left = punit.move_rate();
//  punit.moved = false;
//  punit.paradropped = false;
//  punit.done_moving = false;
//  if (is_barbarian(pplayer)) {
//    punit.fuel = BARBARIAN_LIFE;
//  }
//  punit.ai.cur_pos = null;
//  punit.ai.prev_pos = null;
//  punit.ai.target = 0;
//  punit.ai.hunted = 0;
//  punit.ai.control = false;
//  punit.ai.ai_role = AIUNIT_NONE;
//  punit.ai.ferryboat = 0;
//  punit.ai.passenger = 0;
//  punit.ai.bodyguard = 0;
//  punit.ai.charge = 0;
//  punit.bribe_cost = -1; /* flag value */
//  punit.transported_by = -1;
//  punit.focus_status = FOCUS_AVAIL;
//  punit.ord_map = 0;
//  punit.ord_city = 0;
//  set_unit_activity(punit, unit_activity.ACTIVITY_IDLE);
//  punit.occupy = 0;
//  punit.client.colored = false;
//  punit.has_orders = false;
//
//  return punit;
//}
//
///**************************************************************************
//  Free the memory used by virtual unit. By the time this function is
//  called, you should already have unregistered it everywhere.
//**************************************************************************/
//void destroy_unit_virtual(unit punit)
//{
//  free_unit_orders(punit);
//  free(punit);
//}
//
///**************************************************************************
//  Free and reset the unit's goto route (punit.pgr).  Only used by the
//  server.
//**************************************************************************/
//void free_unit_orders(unit punit)
//{
//  if (punit.has_orders) {
//    punit.goto_tile = null;
//    free(punit.orders.list);
//    punit.orders.list = null;
//  }
//  punit.has_orders = false;
//}
//
///****************************************************************************
//  Expensive function to check how many units are in the transport.
//****************************************************************************/
//int get_transporter_occupancy(unit ptrans)
//{
//  int occupied = 0;
//
//  for (unit pcargo : ptrans.tile.units.data) {
//    if (pcargo.transported_by == ptrans.id) {
//      occupied++;
//    }
//  } }
//
//  return occupied;
//}
//
///****************************************************************************
//  Find a transporter at the given location for the unit.
//****************************************************************************/
//unit find_transporter_for_unit(unit pcargo,
//				       final tile ptile)
//{ 
//  for (unit ptrans : ptile.units.data) {
//    if (can_unit_load(pcargo, ptrans)) {
//      return ptrans;
//    }
//  } }
//
//  return null;
//}
//
///***************************************************************************
//  Tests if the unit could be updated. Returns UR_OK if is this is
//  possible.
//
//  is_free should be set if the unit upgrade is "free" (e.g., Leonardo's).
//  Otherwise money is needed and the unit must be in an owned city.
//
//  Note that this function is strongly tied to unittools.c:upgrade_unit().
//***************************************************************************/
//enum unit_upgrade_result test_unit_upgrade(unit punit, boolean is_free)
//{
//  player pplayer = punit.unit_owner();
//  int to_unittype = can_upgrade_unittype(pplayer, punit.type);
//  city pcity;
//  int cost;
//
//  if (to_unittype == -1) {
//    return UR_NO_UNITTYPE;
//  }
//
//  if (!is_free) {
//    cost = unit_upgrade_price(pplayer, punit.type, to_unittype);
//    if (pplayer.economic.gold < cost) {
//      return UR_NO_MONEY;
//    }
//
//    pcity = map_get_city(punit.tile);
//    if (!pcity) {
//      return UR_NOT_IN_CITY;
//    }
//    if (city_owner(pcity) != pplayer) {
//      /* TODO: should upgrades in allied cities be possible? */
//      return UR_NOT_CITY_OWNER;
//    }
//  }
//
//  if (get_transporter_occupancy(punit) >
//      unit_types[to_unittype].transport_capacity) {
//    /* TODO: allow transported units to be reassigned.  Check for
//     * ground_unit_transporter_capacity here and make changes to
//     * upgrade_unit. */
//    return UR_NOT_ENOUGH_ROOM;
//  }
//
//  return UR_OK;
//}
//
///**************************************************************************
//  Find the result of trying to upgrade the unit, and a message that
//  most callers can use directly.
//**************************************************************************/
//enum unit_upgrade_result get_unit_upgrade_info(char *buf, size_t bufsz,
//					       unit punit)
//{
//  player pplayer = punit.unit_owner();
//  enum unit_upgrade_result result = test_unit_upgrade(punit, false);
//  int upgrade_cost;
//  int from_unittype = punit.type;
//  int to_unittype = can_upgrade_unittype(pplayer,
//						  punit.type);
//
//  switch (result) {
//  case UR_OK:
//    upgrade_cost = unit_upgrade_price(pplayer, from_unittype, to_unittype);
//    /* This message is targeted toward the GUI callers. */
//    buf = String.format ("Upgrade %s to %s for %d gold?\n" +
//			      "Treasury contains %d gold."),
//		unit_types[from_unittype].name, unit_types[to_unittype].name,
//		upgrade_cost, pplayer.economic.gold);
//    break;
//  case UR_NO_UNITTYPE:
//    buf = String.format
//		"Sorry, cannot upgrade %s (yet).",
//		unit_types[from_unittype].name);
//    break;
//  case UR_NO_MONEY:
//    upgrade_cost = unit_upgrade_price(pplayer, from_unittype, to_unittype);
//    buf = String.format
//		("Upgrading %s to %s costs %d gold.\n" +
//		  "Treasury contains %d gold."),
//		unit_types[from_unittype].name, unit_types[to_unittype].name,
//		upgrade_cost, pplayer.economic.gold);
//    break;
//  case UR_NOT_IN_CITY:
//  case UR_NOT_CITY_OWNER:
//    buf = String.format
//		"You can only upgrade units in your cities.");
//    break;
//  case UR_NOT_ENOUGH_ROOM:
//    buf = String.format
//		"Upgrading this %s would strand units it transports.",
//		unit_types[from_unittype].name);
//    break;
//  }
//
//  return result;
//}
}