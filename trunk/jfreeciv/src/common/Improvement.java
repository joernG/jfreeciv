package common;

import common.improvement.impr_type;
import common.player.player;

import utility.shared.Shared_H;

public class Improvement{

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
//#include "Game.game.h"
//#include "log.h"
//#include "Map.map.h"
//#include "mem.h"
//#include "shared.h" /* ARRAY_SIZE */
//#include "support.h"
//#include "tech.h"
//
//#include "improvement.h"

	/* B_LAST is a value which is guaranteed to be larger than all
	 * actual Impr_Type_id values.  It is used as a flag value;
	 * it can also be used for fixed allocations to ensure ability
	 * to hold full number of improvement types.  */
	public static final int B_LAST = Shared_H.MAX_NUM_ITEMS;

	/* Improvement status (for cities' lists of improvements)
	 * An enum or bitfield would be neater here, but we use a typedef for
	 * a) less memory usage and b) compatibility with old behaviour */
//	typedef unsigned char Impr_Status;
	public static final int  I_NONE      = 0;   /* Improvement not built */
	public static final int  I_ACTIVE    = 1;   /* Improvement built, and having its effect */
	public static final int  I_OBSOLETE  = 2;   /* Built, but obsoleted by a tech */
	public static final int  I_REDUNDANT = 3;   /* Built, but replaced by wonder/other building */
//
///* Names of impr ranges.
// * (These must correspond to enum impr_range_id in improvement.h.)
// * do not change these unless you know what you're doing! */
//static final String impr_range_names[] = {
//  "None",
//  "City",
//  "Island",
//  "Player",
//  "World"
//};

	/**************************************************************************
All the city improvements:
Use get_improvement_type(id) to access the array.
The improvement_types array is now setup in:
   server/ruleset.c (for the server)
   client/packhand.c (for the client)
	 **************************************************************************/
	public static impr_type improvement_types[] = new impr_type[B_LAST];

///**************************************************************************
//  Convert impr range names to enum; case insensitive;
//  returns IR_LAST if can't match.
//**************************************************************************/
//enum impr_range impr_range_from_str(final String str)
//{
//  enum impr_range ret_id;
//
//  assert(ARRAY_SIZE(impr_range_names) == IR_LAST);
//
//  for (ret_id = 0; ret_id < IR_LAST; ret_id++) {
//    if (0 == mystrcasecmp(impr_range_names[ret_id], str)) {
//      return ret_id;
//    }
//  }
//
//  return IR_LAST;
//}
//
///**************************************************************************
//  Return impr range name; null if bad id.
//**************************************************************************/
//final String impr_range_name(enum impr_range id)
//{
//  assert(ARRAY_SIZE(impr_range_names) == IR_LAST);
//
//  if (id < IR_LAST) {
//    return impr_range_names[id];
//  } else {
//    return null;
//  }
//}
//
///**************************************************************************
//  Frees the memory associated with this improvement.
//**************************************************************************/
//static void improvement_free(int id)
//{
//  impr_type p = get_improvement_type(id);
//
//  free(p.terr_gate);
//  p.terr_gate = null;
//
//  free(p.spec_gate);
//  p.spec_gate = null;
//
//  free(p.equiv_dupl);
//  p.equiv_dupl = null;
//
//  free(p.equiv_repl);
//  p.equiv_repl = null;
//
//  free(p.helptext);
//  p.helptext = null;
//}
//
///***************************************************************
// Frees the memory associated with all improvements.
//***************************************************************/
//void improvements_free()
//{
//  for (int impr = 0; impr < Game.game.num_impr_types; impr++) {
//    improvement_free(impr);
//  } ;
//}
//
///**************************************************************************
//Returns 1 if the improvement_type "exists" in this Game.game, 0 otherwise.
//An improvement_type doesn't exist if one of:
//- id is out of range;
//- the improvement_type has been flagged as removed by setting its
//  tech_req to Tech_H.A_LAST;
//- it is a space part, and the spacerace is not enabled.
//Arguably this should be called improvement_type_exists, but that's too long.
//**************************************************************************/
public static boolean improvement_exists(int id)
{
//  if (id<0 || id>=B_LAST || id>=Game.game.num_impr_types)
    return false;
//
//  if (!Game.game.spacerace
//      && (building_has_effect(id, EFT_SS_STRUCTURAL)
//	  || building_has_effect(id, EFT_SS_COMPONENT)
//	  || building_has_effect(id, EFT_SS_MODULE))) {
//    /* This assumes that space parts don't have any other effects. */
//    return false;
//  }
//
//  return (improvement_types[id].tech_req!=Tech_H.A_LAST);
}

	/**************************************************************************
	 * ...
	 **************************************************************************/
	static impr_type get_improvement_type(int id) {
		return improvement_types[id];
	}

	/**************************************************************************
	 * ...
	 **************************************************************************/
	public static String get_improvement_name(int id) {
		return get_improvement_type(id).name;
	}

	///****************************************************************************
//  Get the original (untranslated) improvement name.
//****************************************************************************/
//final String get_improvement_name_orig(int id)
//{
//  return get_improvement_type(id).name_orig; 
//}

/****************************************************************************
  Returns the number of shields it takes to build this improvement.
****************************************************************************/
public static int impr_build_shield_cost(int id)
{
  return improvement_types[id].build_cost;
}

///****************************************************************************
//  Returns the amount of gold it takes to rush this improvement.
//****************************************************************************/
//int impr_buy_gold_cost(int id, int shields_in_stock)
//{
//  int cost = 0, missing =
//      improvement_types[id].build_cost - shields_in_stock;
//
//  if (building_has_effect(id, effect_type.EFT_PROD_TO_GOLD)) {
//    /* Can't buy capitalization. */
//    return 0;
//  }
//
//  if (missing > 0) {
//    cost = 2 * missing;
//  }
//
//  if (is_wonder(id)) {
//    cost *= 2;
//  }
//  if (shields_in_stock == 0) {
//    cost *= 2;
//  }
//  return cost;
//}

	/****************************************************************************
	 * Returns the amount of gold received when this improvement is sold.
	 ****************************************************************************/
	public static int impr_sell_gold(int id) {
		return improvement_types[id].build_cost;
	}

/**************************************************************************
...
**************************************************************************/
public static boolean is_wonder(int id)
{
  return (improvement_types[id].is_wonder);
}

///**************************************************************************
//Does a linear search of improvement_types[].name
//Returns B_LAST if none match.
//**************************************************************************/
//int find_improvement_by_name(final String s)
//{
//  for (int i = 0; i < Game.game.num_impr_types; i++) {
//    if (strcmp(improvement_types[i].name, s)==0)
//      return i;
//  } ;
//
//  return B_LAST;
//}
//
///****************************************************************************
//  Does a linear search of improvement_types[].name_orig to find the
//  improvement that matches the given original (untranslated) name.  Returns
//  B_LAST if none match.
//****************************************************************************/
//int find_improvement_by_name_orig(final String s)
//{
//  for (int i = 0; i < Game.game.num_impr_types; i++) {
//    if (mystrcasecmp(improvement_types[i].name_orig, s) == 0) {
//      return i;
//    }
//  } ;
//
//  return B_LAST;
//}
//
///**************************************************************************
// Returns 1 if the improvement is obsolete, now also works for wonders
//**************************************************************************/
public static boolean improvement_obsolete(player pplayer, int id) 
{
//  if (!tech_exists(improvement_types[id].obsolete_by)) {
//    return false;
//  }
//
//  if (improvement_types[id].is_wonder) {
//    /* a wonder is obsolete, as soon as *any* player researched the
//       obsolete tech */
//   return Game.game.global_advances[improvement_types[id].obsolete_by] != 0;
//  }
//
//  return (get_invention(pplayer, improvement_types[id].obsolete_by)
//	  ==TECH_KNOWN);
	return false;
}
//
///**************************************************************************
// Fills in lists of improvements at all impr_ranges that might affect the
// given city (owned by the given player)
//**************************************************************************/
//static void fill_ranges_improv_lists(int *equiv_list[IR_LAST],
//                                     city pcity,
//                                     player pplayer)
//{ 
//  Continent_id cont = 0;
//  enum impr_range i;
//
//  for (i = IR_NONE; i < IR_LAST; i++) {
//    equiv_list[i] = null;
//  }
//
//  if (pcity) {
//    equiv_list[IR_CITY] = pcity.improvements;
//    cont = map_get_continent(pcity.tile);
//    /* Negative continents mean ocean cities. */
//  }
//
//  if (pplayer) {
//    equiv_list[IR_PLAYER] = pplayer.improvements;
//
//    if (cont > 0 && pplayer.island_improv) {
//      equiv_list[IR_ISLAND] =
//                          &pplayer.island_improv[cont * Game.game.num_impr_types];
//    }
//  }
//
//  equiv_list[IR_WORLD] = Game.game.improvements;
//}
//
///**************************************************************************
// Checks whether the building is within the equiv_range of a building that
// replaces it
//**************************************************************************/
//boolean improvement_redundant(player pplayer, final city pcity,
//                          int id, boolean want_to_build)
//{
//  enum impr_range i;
//  int *equiv_list[IR_LAST];
//  int *ept;
//
//  /* Make lists of improvements that affect this city */
//  fill_ranges_improv_lists(equiv_list, (city )pcity, pplayer);
//
//  /* For every improvement named in equiv_dupl or equiv_repl, check for
//     its presence in any of the lists (we check only for its presence, and
//     assume that it has the "equiv" effect even if it itself is redundant) */
//  for (ept = improvement_types[id].equiv_repl; ept && *ept != B_LAST; ept++) {
//    for (i = IR_CITY; i < IR_LAST; i++) {
//      if (equiv_list[i]) {
//         int stat = equiv_list[i][*ept];
//         if (stat != I_NONE && stat != I_OBSOLETE) return true;
//      }
//    }
//  }
//
//  /* equiv_dupl makes buildings redundant, but that shouldn't stop you
//     from building them if you really want to */
//  if (!want_to_build) {
//    for (ept = improvement_types[id].equiv_dupl; ept && *ept != B_LAST; ept++) {
//      for (i = IR_CITY; i < IR_LAST; i++) {
//        if (equiv_list[i]) {
//          int stat = equiv_list[i][*ept];
//          if (stat != I_NONE && stat != I_OBSOLETE) return true;
//        }
//      }
//    }
//  }
//  return false;
//}
//
///**************************************************************************
//...
//**************************************************************************/
//boolean wonder_obsolete(int id)
//{
//  return improvement_obsolete(null, id);
//}
//
///**************************************************************************
// Clears a list of improvements - sets them all to I_NONE
//**************************************************************************/
//void improvement_status_init(int * improvements, size_t elements)
//{
//  /* 
//   * Since this function is called with elements!=Game.game.num_impr_types
//   * impr_type_iterate can't used here.
//   */
//  int i;
//
//  for (i = 0; i < elements; i++) {
//    improvements[i] = I_NONE;
//  }
//}
//
///**************************************************************************
//   Whether player can build given building somewhere, ignoring whether it
//   is obsolete.
//**************************************************************************/
//boolean can_player_build_improvement_direct(player p, int id)
//{
//  impr_type impr;
//  boolean space_part = false;
//
//  /* This also checks if tech req is Never */
//  if (!improvement_exists(id)) {
//    return false;
//  }
//
//  if (!player_knows_improvement_tech(p, id)) {
//    return false;
//  }
//
//  impr = get_improvement_type(id);
//
//  /* Check for space part finalruction.  This assumes that space parts have
//   * no other effects. */
//  if (building_has_effect(id, EFT_SS_STRUCTURAL)) {
//    space_part = true;
//    if (p.spaceship.structurals >= player_spaceship.NUM_SS_STRUCTURALS) {
//      return false;
//    }
//  }
//  if (building_has_effect(id, EFT_SS_COMPONENT)) {
//    space_part = true;
//    if (p.spaceship.components >= player_spaceship.NUM_SS_COMPONENTS) {
//      return false;
//    }
//  }
//  if (building_has_effect(id, EFT_SS_MODULE)) {
//    space_part = true;
//    if (p.spaceship.modules >= player_spaceship.NUM_SS_MODULES) {
//      return false;
//    }
//  }
//  if (space_part &&
//      (!get_player_bonus(p, EFT_ENABLE_SPACE) > 0
//       || p.spaceship.state >= spaceship_state.SSHIP_LAUNCHED)) {
//    return false;
//  }
//
//  if (is_wonder(id)) {
//    /* Can't build wonder if already built */
//    if (Game.game.global_wonders[id] != 0) return false;
//  }
//
//  return true;
//}
//
///**************************************************************************
//  Whether player can _eventually_ build given building somewhere -- i.e.,
//  returns true if building is available with current tech OR will be
//  available with future tech.  Returns false if building is obsolete.
//**************************************************************************/
//boolean can_player_build_improvement(player p, int id)
//{
//  if (!can_player_build_improvement_direct(p, id)) {
//    return false;
//  }
//  if (improvement_obsolete(p, id)) {
//    return false;
//  }
//  return true;
//}
//
///**************************************************************************
//  Whether player can _eventually_ build given building somewhere -- i.e.,
//  returns true if building is available with current tech OR will be
//  available with future tech.  Returns false if building is obsolete.
//**************************************************************************/
//boolean can_player_eventually_build_improvement(player p, int id)
//{
//  if (!improvement_exists(id)) {
//    return false;
//  }
//  if (improvement_obsolete(p, id)) {
//    return false;
//  }
//  return true;
//}
//
///**************************************************************************
//  Marks an improvment to the status
//**************************************************************************/
//void mark_improvement(city pcity, int id, int status)
//{
//  enum impr_range range;
//  int *improvements = null, *equiv_list[IR_LAST];
//
//  pcity.improvements[id] = status;
//  range = improvement_types[id].equiv_range;
//
//  /* Get the relevant improvement list */
//  fill_ranges_improv_lists(equiv_list, pcity, City.city_owner(pcity));
//  improvements = equiv_list[range];
//
//  if (improvements) {
//    /* And set the same status */
//    improvements[id] = status;
//  }
//}
//
///**************************************************************************
//  Redimensions the lists of island-range improvements when number of
//  continents changes. 
//**************************************************************************/
//void allot_island_improvs()
//{
//  int i;
//
//  for(player pplayer: Game.game.players){
//    pplayer.island_improv = fc_realloc(pplayer.island_improv,
//                                        (Map.map.num_continents + 1)
//                                        * Game.game.num_impr_types
//                                        * sizeof(int));
//  
//    /* We index into this array with the continent number, so don't use zero */
//    for (i = 1; i <= Map.map.num_continents; i++) {
//      improvement_status_init(&pplayer.island_improv[i * Game.game.num_impr_types],
//                              Game.game.num_impr_types);
//    } 
//
//    /* Fill the lists with existent improvements with Island equiv_range */
//    for (city pcity : pplayer.cities.data) {
//      Continent_id cont = map_get_continent(pcity.tile);
//      int *improvs = 
//                           &pplayer.island_improv[cont * Game.game.num_impr_types];
//
//for (int i = 0; i < Game.game.num_impr_types; i++) {
//if((pcity).improvements[i] == I_NONE) {
//	continue;
//}
//        if (improvement_types[i].equiv_range != IR_ISLAND) {
//          continue;
//        }
//    
//        improvs[i] = pcity.improvements[i];
//      } ;
//    } }
//  }
//
//  improvements_update_redundant(null, null, 0, IR_WORLD);  
//}   
//
///**************************************************************************
//  Update the obsolete status of all improvements. This needs to be done 
//  when a tech is discovered.
//
//  For all players since wonders are obsoleted when anybody discovers the
//  obsolescence tech.
//
//  If we marked something as obsolete, we need to call 
//  improvements_update_redundant(), since it might have made something 
//  unredundant.
//**************************************************************************/
//void improvements_update_obsolete()
//{   
//  boolean did_mark = false;
//
//  for(player pplayer: Game.game.players){
//    for (city pcity : pplayer.cities.data) {
//for (int i = 0; i < game.num_impr_types; i++) {
//if((pcity).improvements[i] == I_NONE) {
//	continue;
//}
//        if (improvement_obsolete(pplayer, i)) {
//          util.freelog(Log.LOG_DEBUG,"%s in %s is obsolete",
//                  improvement_types[i].name, pcity.name);
//          mark_improvement(pcity, i, I_OBSOLETE);
//          did_mark = true;
//        }
//      } ;
//    } }
//  }
//
//  /* Ideally, we could track at what max range and for which players, but
//   * that's overoptimizing by a bit */
//  if (did_mark) {
//    improvements_update_redundant(null, null, 0, IR_WORLD);
//  }
//}
//
///**************************************************************************
//  Update the redundancy status of all improvements.
//
//  This needs to be done: 
//   o When an improvement is made obsolete (by tech discovery).
//   o When an improvement is built.
//   o When an improvement is destroyed.
//   o When islands are reallotted: cities might be 'rearranged' into 
//     equiv_range.
//
//  We only check improvements within the equiv_range range. 
//
//  N.B. We do not need to do multiple iterations: an 
//  improvement making another improvement redundant does not depend on 
//  whether it itself it redundant or not. having been built is all that 
//  counts.
//**************************************************************************/
//void improvements_update_redundant(player pplayer, city pcity,
//                                   Continent_id cont, enum impr_range range)
//{
//#define CHECK_CITY_IMPR(_pcity)                                              \
//{                                                                            \
//for (int impr = 0; impr < Game.game.num_impr_types; impr++) {
//	if((_pcity).improvements[impr] == I_NONE) {
//		continue;
//	}
//    if ((_pcity).improvements[i] == I_OBSOLETE) {                           \
//      continue;                                                              \
//    }                                                                        \
//                                                                             \
//    if (improvement_redundant(City.city_owner(_pcity), (_pcity), i, false)) {     \
//      util.freelog(Log.LOG_DEBUG,"%s in %s is redundant",                             \
//              improvement_types[i].name, (_pcity).name);                    \
//      mark_improvement((_pcity), i, I_REDUNDANT);                            \
//    } else {                                                                 \
//      util.freelog(Log.LOG_DEBUG,"%s in %s is active!",                               \
//             improvement_types[i].name, (_pcity).name);                     \
//      mark_improvement((_pcity), i, I_ACTIVE);                               \
//    }                                                                        \
//  } ;                                                  \
//}
//
//  switch (range) {
//  case IR_NONE:
//    break;
//  case IR_CITY:
//    assert(pcity != null);
//    CHECK_CITY_IMPR(pcity);
//    break;
//  case IR_WORLD:
//    for(player plr: Game.game.players){
//      for (city pcity2 : plr.cities.data) {
//        CHECK_CITY_IMPR(pcity2);
//      } }
//    }
//    break;
//  case IR_PLAYER:
//    assert(pplayer != null);
//    for (city pcity2 : pplayer.cities.data) {
//      CHECK_CITY_IMPR(pcity2);
//    } }
//    break;
//  case IR_ISLAND:
//    assert(cont > 0);
//    for (city pcity2 : pplayer.cities.data) {
//      if (map_get_continent(pcity2.tile) == cont) {
//        CHECK_CITY_IMPR(pcity2);
//      }
//    } }
//    break;
//  default:
//    break;
//  }
//
//#undef CHECK_CITY_IMPR 
//}
}