package common;

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
//#include "game.h"
//#include "log.h"
//#include "Map.map.h"
//#include "mem.h"
//#include "shared.h" /* ARRAY_SIZE */
//#include "support.h"
//#include "tech.h"
//
//#include "improvement.h"
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
//
///**************************************************************************
//All the city improvements:
//Use get_improvement_type(id) to access the array.
//The improvement_types array is now setup in:
//   server/ruleset.c (for the server)
//   client/packhand.c (for the client)
//**************************************************************************/
//struct impr_type improvement_types[B_LAST];
//
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
//static void improvement_free(Impr_Type_id id)
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
//  impr_type_iterate(impr) {
//    improvement_free(impr);
//  } impr_type_iterate_end;
//}
//
///**************************************************************************
//Returns 1 if the improvement_type "exists" in this game, 0 otherwise.
//An improvement_type doesn't exist if one of:
//- id is out of range;
//- the improvement_type has been flagged as removed by setting its
//  tech_req to A_LAST;
//- it is a space part, and the spacerace is not enabled.
//Arguably this should be called improvement_type_exists, but that's too long.
//**************************************************************************/
//boolean improvement_exists(Impr_Type_id id)
//{
//  if (id<0 || id>=B_LAST || id>=game.num_impr_types)
//    return false;
//
//  if (!game.spacerace
//      && (building_has_effect(id, EFT_SS_STRUCTURAL)
//	  || building_has_effect(id, EFT_SS_COMPONENT)
//	  || building_has_effect(id, EFT_SS_MODULE))) {
//    /* This assumes that space parts don't have any other effects. */
//    return false;
//  }
//
//  return (improvement_types[id].tech_req!=A_LAST);
//}
//
///**************************************************************************
//...
//**************************************************************************/
//impr_type get_improvement_type(Impr_Type_id id)
//{
//  return &improvement_types[id];
//}
//
///**************************************************************************
//...
//**************************************************************************/
//final String get_improvement_name(Impr_Type_id id)
//{
//  return get_improvement_type(id).name; 
//}
//
///****************************************************************************
//  Get the original (untranslated) improvement name.
//****************************************************************************/
//final String get_improvement_name_orig(Impr_Type_id id)
//{
//  return get_improvement_type(id).name_orig; 
//}
//
///****************************************************************************
//  Returns the number of shields it takes to build this improvement.
//****************************************************************************/
//int impr_build_shield_cost(Impr_Type_id id)
//{
//  return improvement_types[id].build_cost;
//}
//
///****************************************************************************
//  Returns the amount of gold it takes to rush this improvement.
//****************************************************************************/
//int impr_buy_gold_cost(Impr_Type_id id, int shields_in_stock)
//{
//  int cost = 0, missing =
//      improvement_types[id].build_cost - shields_in_stock;
//
//  if (building_has_effect(id, EFT_PROD_TO_GOLD)) {
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
//
///****************************************************************************
//  Returns the amount of gold received when this improvement is sold.
//****************************************************************************/
//int impr_sell_gold(Impr_Type_id id)
//{
//  return improvement_types[id].build_cost;
//}
//
///**************************************************************************
//...
//**************************************************************************/
//boolean is_wonder(Impr_Type_id id)
//{
//  return (improvement_types[id].is_wonder);
//}
//
///**************************************************************************
//Does a linear search of improvement_types[].name
//Returns B_LAST if none match.
//**************************************************************************/
//Impr_Type_id find_improvement_by_name(final String s)
//{
//  impr_type_iterate(i) {
//    if (strcmp(improvement_types[i].name, s)==0)
//      return i;
//  } impr_type_iterate_end;
//
//  return B_LAST;
//}
//
///****************************************************************************
//  Does a linear search of improvement_types[].name_orig to find the
//  improvement that matches the given original (untranslated) name.  Returns
//  B_LAST if none match.
//****************************************************************************/
//Impr_Type_id find_improvement_by_name_orig(final String s)
//{
//  impr_type_iterate(i) {
//    if (mystrcasecmp(improvement_types[i].name_orig, s) == 0) {
//      return i;
//    }
//  } impr_type_iterate_end;
//
//  return B_LAST;
//}
//
///**************************************************************************
// Returns 1 if the improvement is obsolete, now also works for wonders
//**************************************************************************/
//boolean improvement_obsolete(final player pplayer, Impr_Type_id id) 
//{
//  if (!tech_exists(improvement_types[id].obsolete_by)) {
//    return false;
//  }
//
//  if (improvement_types[id].is_wonder) {
//    /* a wonder is obsolete, as soon as *any* player researched the
//       obsolete tech */
//   return game.global_advances[improvement_types[id].obsolete_by] != 0;
//  }
//
//  return (get_invention(pplayer, improvement_types[id].obsolete_by)
//	  ==TECH_KNOWN);
//}
//
///**************************************************************************
// Fills in lists of improvements at all impr_ranges that might affect the
// given city (owned by the given player)
//**************************************************************************/
//static void fill_ranges_improv_lists(Impr_Status *equiv_list[IR_LAST],
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
//                          &pplayer.island_improv[cont * game.num_impr_types];
//    }
//  }
//
//  equiv_list[IR_WORLD] = game.improvements;
//}
//
///**************************************************************************
// Checks whether the building is within the equiv_range of a building that
// replaces it
//**************************************************************************/
//boolean improvement_redundant(player pplayer, final city pcity,
//                          Impr_Type_id id, boolean want_to_build)
//{
//  enum impr_range i;
//  Impr_Status *equiv_list[IR_LAST];
//  Impr_Type_id *ept;
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
//         Impr_Status stat = equiv_list[i][*ept];
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
//          Impr_Status stat = equiv_list[i][*ept];
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
//boolean wonder_obsolete(Impr_Type_id id)
//{
//  return improvement_obsolete(null, id);
//}
//
///**************************************************************************
// Clears a list of improvements - sets them all to I_NONE
//**************************************************************************/
//void improvement_status_init(Impr_Status * improvements, size_t elements)
//{
//  /* 
//   * Since this function is called with elements!=game.num_impr_types
//   * impr_type_iterate can't used here.
//   */
//  Impr_Type_id i;
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
//boolean can_player_build_improvement_direct(player p, Impr_Type_id id)
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
//    if (game.global_wonders[id] != 0) return false;
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
//boolean can_player_build_improvement(player p, Impr_Type_id id)
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
//boolean can_player_eventually_build_improvement(player p, Impr_Type_id id)
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
//void mark_improvement(city pcity, Impr_Type_id id, Impr_Status status)
//{
//  enum impr_range range;
//  Impr_Status *improvements = null, *equiv_list[IR_LAST];
//
//  pcity.improvements[id] = status;
//  range = improvement_types[id].equiv_range;
//
//  /* Get the relevant improvement list */
//  fill_ranges_improv_lists(equiv_list, pcity, city_owner(pcity));
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
//  for(player pplayer: game.players){
//    pplayer.island_improv = fc_realloc(pplayer.island_improv,
//                                        (Map.map.num_continents + 1)
//                                        * game.num_impr_types
//                                        * sizeof(Impr_Status));
//  
//    /* We index into this array with the continent number, so don't use zero */
//    for (i = 1; i <= Map.map.num_continents; i++) {
//      improvement_status_init(&pplayer.island_improv[i * game.num_impr_types],
//                              game.num_impr_types);
//    } 
//
//    /* Fill the lists with existent improvements with Island equiv_range */
//    for (city pcity : pplayer.cities.data) {
//      Continent_id cont = map_get_continent(pcity.tile);
//      Impr_Status *improvs = 
//                           &pplayer.island_improv[cont * game.num_impr_types];
//
//      built_impr_iterate(pcity, id) {
//        if (improvement_types[id].equiv_range != IR_ISLAND) {
//          continue;
//        }
//    
//        improvs[id] = pcity.improvements[id];
//      } built_impr_iterate_end;
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
//  for(player pplayer: game.players){
//    for (city pcity : pplayer.cities.data) {
//      built_impr_iterate(pcity, i) {
//        if (improvement_obsolete(pplayer, i)) {
//          util.freelog(Log.LOG_DEBUG,"%s in %s is obsolete",
//                  improvement_types[i].name, pcity.name);
//          mark_improvement(pcity, i, I_OBSOLETE);
//          did_mark = true;
//        }
//      } built_impr_iterate_end;
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
//  built_impr_iterate((_pcity), i) {                                          \
//    if ((_pcity).improvements[i] == I_OBSOLETE) {                           \
//      continue;                                                              \
//    }                                                                        \
//                                                                             \
//    if (improvement_redundant(city_owner(_pcity), (_pcity), i, false)) {     \
//      util.freelog(Log.LOG_DEBUG,"%s in %s is redundant",                             \
//              improvement_types[i].name, (_pcity).name);                    \
//      mark_improvement((_pcity), i, I_REDUNDANT);                            \
//    } else {                                                                 \
//      util.freelog(Log.LOG_DEBUG,"%s in %s is active!",                               \
//             improvement_types[i].name, (_pcity).name);                     \
//      mark_improvement((_pcity), i, I_ACTIVE);                               \
//    }                                                                        \
//  } built_impr_iterate_end;                                                  \
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
//    for(player plr: game.players){
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