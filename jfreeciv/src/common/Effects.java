package common;

import common.city.city;
import common.effects.effect_type;

public class Effects{

// Freeciv - Copyright (C) 2004 - The Freeciv Team
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
//#ifdef HAVE_CONFIG_H
//#include <config.h>
//#endif
//
//#include <assert.h>
//#include <ctype.h>
//#include <string.h>
//
//#include "fcintl.h"
//#include "log.h"
//#include "mem.h"
//#include "support.h"
//#include "shared.h" /* ARRAY_SIZE */
//
//#include "city.h"
//#include "effects.h"
//#include "Game.game.h"
//#include "government.h"
//#include "improvement.h"
//#include "Map.map.h"
//#include "packets.h"
//#include "player.h"
//#include "tech.h"
// 
///* Names of effect ranges.
// * (These must correspond to enum effect_range_id in effects.h.)
// * do not change these unless you know what you're doing! */
//static final String effect_range_names[EFR_LAST] = {
//  "Local",
//  "City",
//  "Continent",
//  "Player",
//  "World"
//};
//
///* Names of effect types.
// * (These must correspond to enum effect_type_id in effects.h.) */
//static final String effect_type_names[EFT_LAST] = {
//  "Tech_Parasite",
//  "Airlift",
//  "Any_Government",
//  "Capital_City",
//  "Corrupt_Pct",
//  "Waste_Pct",
//  "Enable_Nuke",
//  "Enable_Space",
//  "Food_Add_Tile",
//  /* TODO: "Food_Bonus", */
//  /* TODO: "Food_Pct", */
//  "Food_Inc_Tile",
//  "Food_Per_Tile",
//  "Force_Content",
//  /* TODO: "Force_Content_Pct", */
//  "Give_Imm_Tech",
//  "Growth_Food",
//  "Have_Embassies",
//  "Luxury_Bonus",
//  /* TODO: "Luxury_Pct", */
//  "Make_Content",
//  "Make_Content_Mil",
//  "Make_Content_Mil_Per",
//  /* TODO: "Make_Content_Pct", */
//  "Make_Happy",
//  "No_Anarchy",
//  "No_Sink_Deep",
//  "Nuke_Proof",
//  /* TODO: "Pollu_Adj", */
//  /* TODO: "Pollu_Pct", */
//  /* TODO: "Pollu_Pop_Adj", */
//  "Pollu_Pop_Pct",
//  /* TODO: "Pollu_Prod_Adj", */
//  "Pollu_Prod_Pct",
//  "Prod_Add_Tile",
//  "Prod_Bonus",
//  /* TODO: "Prod_Pct", */
//  "Prod_Inc_Tile",
//  "Prod_Per_Tile",
//  "Prod_To_Gold",
//  "Reveal_Cities",
//  "Reveal_Map",
//  /* TODO: "Incite_Dist_Adj", */
//  "Incite_Dist_Pct",
//  "Science_Bonus",
//  /* TODO: "Science_Pct", */
//  "Size_Adj",
//  "Size_Unlimit",
//  "SS_Structural",
//  "SS_Component",
//  "SS_Module",
//  "Spy_Resistant",
//  "Tax_Bonus",
//  /* TODO: "Tax_Pct", */
//  "Trade_Add_Tile",
//  /* TODO: "Trade_Bonus", */
//  /* TODO: "Trade_Pct", */
//  "Trade_Inc_Tile",
//  "Trade_Per_Tile",
//  "Sea_Move",
//  "Unit_No_Lose_Pop",
//  "Unit_Recover",
//  "Upgrade_Unit",
//  "Upkeep_Free",
//  "No_Unhappy",
//  "Land_Veteran",
//  "Sea_Veteran",
//  "Air_Veteran",
//  "Land_Vet_Combat",
//  /* TODO: "Sea_Vet_Combat", */
//  /* TODO: "Air_Vet_Combat", */
//  "Land_Regen",
//  "Sea_Regen",
//  "Air_Regen",
//  "Land_Defend",
//  "Sea_Defend",
//  "Air_Defend",
//  "Missile_Defend",
//  "No_Incite",
//  "Regen_Reputation",
//  "Gain_AI_Love"
//};
//
///**************************************************************************
//  Convert an effect range names to an enumerated value.  This is the
//  inverse of effect_range_name.
//
//  The check is case insensitive and returns EFR_LAST if no match is found.
//**************************************************************************/
//enum effect_range effect_range_from_str(final String str)
//{
//  enum effect_range effect_range;
//
//  assert(ARRAY_SIZE(effect_range_names) == EFR_LAST);
//
//  for (effect_range = 0; effect_range < EFR_LAST; effect_range++) {
//    if (0 == mystrcasecmp(effect_range_names[effect_range], str)) {
//      return effect_range;
//    }
//  }
//
//  return EFR_LAST;
//}
//
///**************************************************************************
//  Return the name for an effect range enumeration.  Returns null if the
//  effect_range is invalid.  This is the inverse of effect_range_from_str.
//**************************************************************************/
//final String effect_range_name(enum effect_range effect_range)
//{
//  assert(ARRAY_SIZE(effect_range_names) == EFR_LAST);
//  if (effect_range >= 0 && effect_range < EFR_LAST) {
//    return effect_range_names[effect_range];
//  } else {
//    assert(0!=1);
//    return null;
//  }
//}
//
///**************************************************************************
//  Convert effect type names to enum; case insensitive;
//  returns EFT_LAST if can't match.
//**************************************************************************/
//enum effect_type effect_type_from_str(final String str)
//{
//  enum effect_type effect_type;
//
//  assert(ARRAY_SIZE(effect_type_names) == EFT_LAST);
//
//  for (effect_type = 0; effect_type < EFT_LAST; effect_type++) {
//    if (0 == mystrcasecmp(effect_type_names[effect_type], str)) {
//      return effect_type;
//    }
//  }
//
//  return EFT_LAST;
//}
//
///**************************************************************************
//  Return the name for an effect type enumeration.  Returns null if the
//  effect_type is invalid.  This is the inverse of effect_type_from_str.
//**************************************************************************/
//final String effect_type_name(enum effect_type effect_type)
//{
//  assert(ARRAY_SIZE(effect_type_names) == EFT_LAST);
//  if (effect_type >= 0 && effect_type < EFT_LAST) {
//    return effect_type_names[effect_type];
//  } else {
//    assert(0!=1);
//    return null;
//  }
//}
//
///**************************************************************************
//  The code creates a ruleset cache on ruleset load. This finalant cache
//  is used to speed up effects queries.  After the cache is created it is
//  not modified again (though it may later be freed).
//
//  Since the cache is finalant, the server only needs to send effects data to
//  the client upon connect. It also means that an AI can do fast searches in
//  the effects space by trying the possible combinations of addition or
//  removal of buildings with the effects it cares about.
//
//
//  To know how much a target is being affected, simply use the convenience
//  functions:
//
//  * get_player_bonus
//  * get_city_bonus
//  * get_city_tile_bonus
//  * get_building_bonus
//
//  These functions require as arguments the target and the effect type to be
//  queried.
//
//  Effect sources are unique and at a well known place in the
//  data structures.  This allows the above queries to be fast:
//    - Look up the possible sources for the effect (O(1) lookup)
//    - For each source, find out if it is present (O(1) lookup per source).
//  The first is commonly called the "ruleset cache" and is stored statically
//  in this file.  The second is the "sources cache" and is stored all over.
//
//  Any type of effect range and "survives" is possible if we have a sources
//  cache for that combination.  For instance
//    - There is a sources cache of all existing buildings in a city; thus any
//      building effect in a city can have city range.
//    - There is a sources cache of all wonders in the world; thus any wonder
//      effect can have world range.
//    - There is a sources cache of all wonders for each player; thus any
//      wonder effect can have player range.
//    - There is a sources cache of all wonders ever built; thus any wonder
//      effect that survives can have world range.
//  However there is no sources cache for many of the possible sources.  For
//  instance non-unique buildings do not have a world-range sources cahce, so
//  you can't have a non-wonder building have a world-ranged effect.
//
//  The sources caches could easily be extended by generalizing it to a set
//  of arrays
//    Game.game.buildings[], pplayer.buildings[],
//    pisland.builidngs[], pcity.buildings[]
//  which would store the number of buildings of that type present by Game.game,
//  player, island (continent) or city.  This would allow non-surviving effects
//  to come from any building at any range.  However to allow surviving effects
//  a second set of arrays would be needed.  This should enable basic support
//  for small wonders and satellites.
//
//  No matter which sources caches are present, we should always know where
//  to look for a source and so the lookups will always be fast even as the
//  number of possible sources increases.
//**************************************************************************/
//
//static final String req_type_names[] = {
//  "None",
//  "Tech",
//  "Gov",
//  "Building",
//  "Special",
//  "Terrain"
//};
//
///*
// * A group lists which sources are in it and at what range.
// * Each effect also lists which group it is in.  So an effect is in the
// * group if its source is listed in the group, and the effect lists the
// * group as its own.
// *
// * Only the first applicable effect in a group will be active.  Any others
// * are simply ignored.
// *
// * Actually this isn't quite true.  The check is done on buildings, not
// * on effects.  A building in a group may obsolete the effect of a building
// * later in the group, even if the first building doesn't have any effects
// * that are actually in the group.  However only the effects of the second
// * building that are actually in the group will be obsoleted.
// */
//struct effect_group_element {
//  int source_building;
//  enum effect_range range;
//  boolean survives;
//};
//
//#define SPECLIST_TAG effect_group_element
//#define SPECLIST_TYPE struct effect_group_element
//#include "speclist.h"
//
//#define effect_group_element_list_iterate(list, elt) \
//  TYPED_LIST_ITERATE(struct effect_group_element, list, elt)
//#define effect_group_element_list_iterate_end  LIST_ITERATE_END
//
//struct effect_group {
//  char *name;
//  int id;
//  struct effect_group_element_list elements;
//};
//
//#define SPECLIST_TAG effect_group
//#define SPECLIST_TYPE struct effect_group
//#include "speclist.h"
//
//#define effect_group_list_iterate(list, pgroup) \
//  TYPED_LIST_ITERATE(struct effect_group, list, pgroup)
//#define effect_group_list_iterate_end  LIST_ITERATE_END
//
///**************************************************************************
//  Ruleset cache. The cache is created during ruleset loading and the data
//  is organized to enable fast queries.
//**************************************************************************/
//static struct {
//  struct {
//    /* This cache shows for each effect, which buildings provide it. */
//    struct building_vector buildings;
//
//    /* This array provides a full list of the effects of this type provided
//     * by each building.  (It's not really a cache, it's the real data.) */
//    Speclists<effect> buckets[Improvement.B_LAST];
//  } effects[EFT_LAST];
//
//  /* This cache shows for each building, which effect types it provides. */
//  struct {
//    struct effect_type_vector types;
//  } buildings[Improvement.B_LAST];
//} ruleset_cache;
//
//static struct effect_group_list groups;
//static int next_group_id;
//
//
///**************************************************************************
//  Get a vector of buildings which grant the effect type.
//**************************************************************************/
//static building_vector get_buildings_with_effect(enum effect_type e)
//{
//  return &ruleset_cache.effects[e].buildings;
//}
//
///**************************************************************************
//  Get a list of effects of this type granted by a building.
//**************************************************************************/
//effect_list get_building_effects(int building,
//					 enum effect_type effect_type)
//{
//  return &ruleset_cache.effects[effect_type].buckets[building];
//}
//
///**************************************************************************
//  Get a vector of effect types granted by a building.
//**************************************************************************/
//effect_type_vector get_building_effect_types(int id)
//{
//  return &ruleset_cache.buildings[id].types;
//}
//
///**************************************************************************
//  Get requirements type from string.  This is used when loading the
//  ruleset.  REQ_LAST is returned if no other match is found.
//**************************************************************************/
//enum effect_req_type effect_req_type_from_str(final String str)
//{
//  enum effect_req_type req_type;
//
//  assert(ARRAY_SIZE(req_type_names) == REQ_LAST);
//
//  for (req_type = 0; req_type < ARRAY_SIZE(req_type_names); req_type++) {
//    if (0 == mystrcasecmp(req_type_names[req_type], str)) {
//      return req_type;
//    }
//  }
//
//  return REQ_LAST;
//}
//
///**************************************************************************
//  Create a new effects group.
//**************************************************************************/
//effect_group effect_group_new(final String name)
//{
//  effect_group group;
//
//  /* Create the group. */
//  group = fc_malloc(sizeof(*group));
//  group.name = (name);
//  group.id = next_group_id++;
//  effect_group_element_list_init(&group.elements);
//
//  /* Add this group to the global list of groups. */
//  effect_group_list_insert_back(&groups, group);
//
//  return group;
//}
//
///**************************************************************************
//  Add a source to an existing effects group.
//**************************************************************************/
//void effect_group_add_element(effect_group group,
//			      int source_building,
//			      enum effect_range range, boolean survives)
//{
//  effect_group_element elt;
//
//  /* Create the element. */
//  elt = fc_malloc(sizeof(*elt));
//  elt.source_building = source_building;
//  elt.range = range;
//  elt.survives = survives;
//
//  /* Append it to the group. */
//  effect_group_element_list_insert_back(&group.elements, elt);
//}
//
///**************************************************************************
//  Find the id of an effects group by name.  Returns -1 if the group is not
//  found.
//**************************************************************************/
//int find_effect_group_id(final String name)
//{
//  int group_id = 0;
//
//  effect_for (group pgroup : groups.data) {
//    if (0 == mystrcasecmp(pgroup.name, name)) {
//      return group_id;
//    }
//    group_id++;
//  } effect_}
//
//  return -1;
//}
//
///**************************************************************************
//  Initialize the ruleset cache.  The ruleset cache should be emtpy
//  before this is done (so if it's previously been initialized, it needs
//  to be freed (see ruleset_cache_free) before it can be reused).
//**************************************************************************/
//void ruleset_cache_init()
//{
//  int i, j;
//
//  assert(ARRAY_SIZE(req_type_names) == REQ_LAST);
//
//  effect_group_list_init(&groups);
//  next_group_id = 0;
//
//  for (i = 0; i < ARRAY_SIZE(ruleset_cache.buildings); i++) {
//    effect_type_vector_init(get_building_effect_types(i));
//  }
//
//  for (i = 0; i < ARRAY_SIZE(ruleset_cache.effects); i++) {
//    building_vector_init(get_buildings_with_effect(i));
//
//    for (j = 0; j < ARRAY_SIZE(ruleset_cache.effects[i].buckets); j++) {
//      effect_list_init(get_building_effects(j, i));
//    }
//  }
//}
//
///**************************************************************************
//  Free the ruleset cache.  This should be called at the end of the Game.game or
//  when the client disconnects from the server.  See ruleset_cache_init.
//**************************************************************************/
//void ruleset_cache_free()
//{
//  int i, j;
//
//  for (i = 0; i < ARRAY_SIZE(ruleset_cache.buildings); i++) {
//    effect_type_vector_free(get_building_effect_types(i));
//  }
//
//  for (i = 0; i < ARRAY_SIZE(ruleset_cache.effects); i++) {
//    building_vector_free(get_buildings_with_effect(i));
//
//    for (j = 0; j < ARRAY_SIZE(ruleset_cache.effects[i].buckets); j++) {
//      effect_list_iterate(*get_building_effects(j, i), peffect) {
//	/* Allocated in ruleset_cache_add. */
//	free(peffect);
//      } }
//      effect_list_unlink_all(get_building_effects(j, i));
//    }
//  }
//}
//
///**************************************************************************
//  Parse the effect requirement.
//
//  In the effect
//
//    { "name", "value", "equiv", "req_type", "req" +
//      "Prod_Bonus", 25, "Generators", "Building", "Factory"
//    }
//
//  the req_type "Building" has already been parsed by req_type_from_str,
//  and the req "Factory" is passed as the req variable here.
//**************************************************************************/
//int parse_effect_requirement(int source,
//			     enum effect_req_type req_type,
//			     final String req_value)
//{
//  boolean problem;
//  int data = -1;
//  final government pgov;
//
//  switch (req_type) {
//  case REQ_NONE:
//    problem = false;
//    data = 0;
//    break;
//  case REQ_TECH:
//    data = find_tech_by_name(req_value);
//    problem = (Tech_H.A_LAST == data);
//    break;
//  case REQ_GOV:
//    pgov = find_government_by_name(req_value);
//    problem = (null == pgov);
//    if (pgov) {
//      data = pgov.index;
//    }
//    break;
//  case REQ_BUILDING:
//    data = find_improvement_by_name(req_value);
//    problem = (Improvement.B_LAST == data);
//    break;
//  case REQ_SPECIAL:
//    data = get_special_by_name(req_value);
//    problem = (S_NO_SPECIAL == data);
//    break;
//  case REQ_TERRAIN:
//    data = get_terrain_by_name(req_value);
//    problem = (T_UNKNOWN == data);
//    break;
//  default:
//    util.die("for %s: unimplemented requirement type '%d'",
//	Improvement.get_improvement_name(source), req_type);
//    return -1;
//  }
//
//  if (problem) {
//    util.freelog(Log.LOG_ERROR,
//	    /* TRANS: Obscure ruleset error. */
//	    "for building %s: bad effect requirement data '%s'",
//	    Improvement.get_improvement_name(source), req_value);
//    return -1;
//  } else {
//    return data;
//  }
//}
//
///**************************************************************************
//  Add effect to ruleset cache.
//**************************************************************************/
//void ruleset_cache_add(int source, enum effect_type effect_type,
//		       enum effect_range range, boolean survives, int eff_value,
//		       enum effect_req_type req_type, int req_value,
//		       int group_id)
//{
//  effect peffect;
//
//  /* Create the effect. */
//  peffect = fc_malloc(sizeof(*peffect));
//  peffect.range = range;
//  peffect.survives = survives;
//  peffect.value = eff_value;
//
//  /* Set effect's requirement data. */
//  peffect.req.type = req_type;
//  switch (req_type) {
//  case REQ_NONE:
//    break;
//  case REQ_TECH:
//    peffect.req.value.tech = req_value;
//    break;
//  case REQ_GOV:
//    peffect.req.value.gov = req_value;
//    break;
//  case REQ_BUILDING:
//    peffect.req.value.building = req_value;
//    break;
//  case REQ_SPECIAL:
//    peffect.req.value.special = req_value;
//    break;
//  case REQ_TERRAIN:
//    peffect.req.value.terrain = req_value;
//    break;
//  case REQ_LAST:
//    assert(0!=1);
//    break;
//  }
//
//  /* Find the effect's group. */
//  if (group_id >= 0) {
//    peffect.group = effect_group_list_get(&groups, group_id);
//  } else {
//    peffect.group = null;
//  }
//
//  /* Now add the effect to the ruleset cache. */
//  effect_list_insert_back(get_building_effects(source, effect_type),
//			  peffect);
//
//  /* Add building type to the effect type's buildings vector. */
//  {
//    building_vector vec;
//    int *pbldg;
//
//    vec = get_buildings_with_effect(effect_type);
//
//    /* Append this building to the list of buildings providing the effect.
//     * There is a sanity check to prevent it from being added more than
//     * once.  (It is possible the same building would have multiple effects
//     * of the same type, in which case we don't want to add the building
//     * to the list twice.  However this does assume that all effects from
//     * one building are processed before moving on to the next building. */
//    if (!(pbldg = building_vector_get(vec, -1)) || *pbldg != source) {
//      building_vector_append(vec, &source);
//    }
//  }
//
//  /* Add effect type to the building's effect types vector. */
//  {
//    effect_type_vector vec;
//    boolean exists = false;
//
//    vec = get_building_effect_types(source);
//
//    /* See if it's already in the list. */
//    effect_type_vector_iterate(vec, ptype) {
//      if (*ptype == effect_type) {
//	exists = true;
//	break;
//      }
//    } effect_type_vector_iterate_end;
//
//    /* And if not, append it. */
//    if (!exists) {
//      effect_type_vector_append(vec, &effect_type);
//    }
//  }
//}
//
///**************************************************************************
//  Send the ruleset cache groups data.
//**************************************************************************/
//static void send_ruleset_cache_groups(Speclists<Connection> dest)
//{
//  struct packet_ruleset_cache_group packet;
//  int i;
//
//  effect_for (group pgroup : groups.data) {
//    packet.name = pgroup.name;
//
//    packet.num_elements = effect_group_pgroup.elements.foo_list_size();
//    for (i = 0; i < packet.num_elements; i++) {
//      effect_group_element elt;
//
//      elt = effect_group_element_list_get(&pgroup.elements, i);
//      packet.source_buildings[i] = elt.source_building;
//      packet.ranges[i] = elt.range;
//      packet.survives[i] = elt.survives;
//    }
//
//    lsend_packet_ruleset_cache_group(dest, &packet);
//  } effect_}
//}
//
///**************************************************************************
//  Send the ruleset cache effects data.
//**************************************************************************/
//static void send_ruleset_cache_effects(Speclists<Connection> dest)
//{
//  struct packet_ruleset_cache_effect packet;
//  enum effect_type effect_type;
//
//  for (effect_type = 0; effect_type < EFT_LAST; effect_type++) {
//    packet.effect_type = effect_type;
//
//    building_vector_iterate(get_buildings_with_effect(effect_type),
//			    building) {
//      packet.id = *building;
//
//      effect_list_iterate(*get_building_effects(*building, effect_type),
//			  peffect) {
//	packet.range = peffect.range;
//	packet.survives = peffect.survives;
//        packet.eff_value = peffect.value;
//	packet.req_type = peffect.req.type;
//
//	if (peffect.group) {
//	  packet.group_id = peffect.group.id;
//	} else {
//	  packet.group_id = -1;
//	}
//
//	switch (packet.req_type) {
//	case REQ_NONE:
//	  packet.req_value = 0;
//	  break;
//	case REQ_TECH:
//	  packet.req_value = peffect.req.value.tech;
//	  break;
//	case REQ_GOV:
//	  packet.req_value = peffect.req.value.gov;
//	  break;
//	case REQ_BUILDING:
//	  packet.req_value = peffect.req.value.building;
//	  break;
//	case REQ_SPECIAL:
//	  packet.req_value = peffect.req.value.special;
//	  break;
//	case REQ_TERRAIN:
//	  packet.req_value = peffect.req.value.terrain;
//	  break;
//	case REQ_LAST:
//	  assert(0!=1);
//	  break;
//	}
//
//	lsend_packet_ruleset_cache_effect(dest, &packet);
//      } }
//    } building_vector_iterate_end;
//  }
//}
//
///**************************************************************************
//  Send the ruleset cache data over the network.
//**************************************************************************/
//void send_ruleset_cache(Speclists<Connection> dest)
//{
//  send_ruleset_cache_groups(dest);
//  send_ruleset_cache_effects(dest);
//}
//
///**************************************************************************
//  Returns a buildable, non-obsolete building that can provide the effect.
//
//  Note: this function is an inefficient hack to be used by the old AI.  It
//  will never find wonders, since that's not what the AI wants.
//**************************************************************************/
//int ai_find_source_building(player pplayer,
//				     enum effect_type effect_type)
//{
//  /* FIXME: this just returns the first building. it should return the best
//   * building instead. */
//  building_vector_iterate(get_buildings_with_effect(effect_type), pbldg) {
//    if (can_player_build_improvement(pplayer, *pbldg)
//	&& !Improvement.improvement_obsolete(pplayer, *pbldg)
//	&& !Improvement.is_wonder(*pbldg)) {
//      return *pbldg;
//    }
//  } building_vector_iterate_end;
//  return Improvement.B_LAST;
//}
//
///**************************************************************************
//  Get a building which grants this effect. Returns Improvement.B_LAST if there is none.
//**************************************************************************/
//int get_building_for_effect(enum effect_type effect_type)
//{
//  building_vector_iterate(get_buildings_with_effect(effect_type), pbldg) {
//    return *pbldg;
//  } building_vector_iterate_end;
//  return Improvement.B_LAST;
//}
//
///**************************************************************************
//  Returns true if the building has any effect bonuses of the given type.
//
//  Note that this function returns a boolean rather than an integer value
//  giving the exact bonus.  Finding the exact bonus requires knowing the
//  effect range and may take longer.  This function should only be used
//  in situations where the range doesn't matter.
//**************************************************************************/
//boolean building_has_effect(int id, enum effect_type effect)
//{
//  return (effect_list_size(get_building_effects(id, effect)) > 0);
//}
//
///**************************************************************************
//  Returns the number of total world buildings (this includes buildings
//  that have been destroyed).
//**************************************************************************/
//static int num_world_buildings_total(int building)
//{
//  if (Improvement.is_wonder(building)) {
//    return (Game.game.global_wonders[building] != 0) ? 1 : 0;
//  } else {
//    util.freelog(Log.LOG_ERROR,
//	    /* TRANS: Obscure ruleset error. */
//	    "World-ranged effects are only supported for wonders.");
//    return 0;
//  }
//}
//
///**************************************************************************
//  Returns the number of buildings of a certain type in the world.
//**************************************************************************/
//static int num_world_buildings(int id)
//{
//  if (Improvement.is_wonder(id)) {
//    return Game.find_city_by_id(Game.game.global_wonders[id]) ? 1 : 0;
//  } else {
//    util.freelog(Log.LOG_ERROR,
//	    /* TRANS: Obscure ruleset error. */
//	    "World-ranged effects are only supported for wonders.");
//    return 0;
//  }
//}
//
///**************************************************************************
//  Returns the number of buildings of a certain type owned by plr.
//**************************************************************************/
//static int num_player_buildings(final player pplayer,
//				int building)
//{
//  if (Improvement.is_wonder(building)) {
//    if (Player_P.player_find_city_by_id(pplayer, Game.game.global_wonders[building])) {
//      return 1;
//    } else {
//      return 0;
//    }
//  } else {
//    util.freelog(Log.LOG_ERROR,
//	    /* TRANS: Obscure ruleset error. */
//	    "Player-ranged effects are only supported for wonders.");
//    return 0;
//  }
//}
//
///**************************************************************************
//  Returns the number of buildings of a certain type on a continent.
//**************************************************************************/
//static int num_continent_buildings(final player pplayer,
//				   int continent, int building)
//{
//  if (Improvement.is_wonder(building)) {
//    final city pcity;
//
//    pcity = Player_P.player_find_city_by_id(pplayer, Game.game.global_wonders[building]);
//    if (pcity && map_get_continent(pcity.tile) == continent) {
//      return 1;
//    }
//  } else {
//    util.freelog(Log.LOG_ERROR,
//	    /* TRANS: Obscure ruleset error. */
//	    "Island-ranged effects are only supported for wonders.");
//  }
//  return 0;
//}
//
///**************************************************************************
//  Returns the number of buildings of a certain type in a city.
//**************************************************************************/
//static int num_city_buildings(final city pcity, int id)
//{
//  return (City.city_got_building(pcity, id) ? 1 : 0);
//}
//
///**************************************************************************
//  Is this target possible for the range involved?
//
//  For instance a city-ranged effect can't have a player as it's target.
//  See the comment at enum target_type.
//**************************************************************************/
//static boolean is_target_possible(enum target_type target,
//			       enum effect_range range)
//{
//  switch (target) {
//  case TARGET_PLAYER:
//    return (range >= EFR_PLAYER);
//  case TARGET_CITY:
//    return (range >= EFR_CITY);
//  case TARGET_BUILDING:
//    return (range >= EFR_LOCAL);
//  }
//  assert(0!=1);
//  return false;
//}
//
///**************************************************************************
//  How many of the source building are there within range of the target?
//
//  The target gives the type of the target.  The exact target is a player,
//  city, or building specified by the target_xxx arguments.
//
//  The range gives the range of the effect.
//
//  "Survives" specifies whether the effect is surviving.  If set then all
//  source buildings ever built are counted; if not then only living
//  buildings are counted.
//
//  source gives the building type of the source in question.
//
//  Note that this function does a lookup into the source caches to find
//  the number of available sources.  However not all source caches exist: if
//  the cache doesn't exist then we return 0.
//**************************************************************************/
//static int count_sources_in_range(enum target_type target,
//				  final player target_player,
//				  final city target_city,
//				  int target_building,
//				  enum effect_range range, boolean survives,
//				  int source)
//{
//  if (!is_target_possible(target, range)) {
//    return 0;
//  }
//
//  if (Improvement.improvement_obsolete(target_player, source)) {
//    return 0;
//  }
//
//  if (survives) {
//    if (range == EFR_WORLD) {
//      return num_world_buildings_total(source);
//    } else {
//      /* There is no sources cache for this. */
//      util.freelog(Log.LOG_ERROR,
//	      /* TRANS: Obscure ruleset error. */
//	      "Surviving effects are only supported at world range.");
//      return 0;
//    }
//  }
//
//  switch (range) {
//  case EFR_WORLD:
//    return num_world_buildings(source);
//  case EFR_PLAYER:
//    return num_player_buildings(target_player, source);
//  case EFR_CONTINENT:
//    {
//      int continent = map_get_continent(target_city.tile);
//
//      return num_continent_buildings(target_player, continent, source);
//    }
//  case EFR_CITY:
//    return num_city_buildings(target_city, source);
//  case EFR_LOCAL:
//    if (target_building == source) {
//      return num_city_buildings(target_city, source);
//    } else {
//      return 0;
//    }
//  case EFR_LAST:
//    break;
//  }
//  assert(0!=1);
//  return 0;
//}
//
///**************************************************************************
//  Is the effect from the source building redundant on the given target
//  (i.e. are its effects replaced by other sources in the group)?
//
//  target gives the type of the target
//  (player,pcity,building) gives the exact target
//  source is the source type of the effect
//  peffect is the exact effect
//**************************************************************************/
//static boolean is_effect_redundant(enum target_type target,
//				final player target_player,
//				final city target_city,
//				int target_building,
//				int source,
//				final effect peffect)
//{
//  if (!peffect.group) {
//    /* No group: the effect can't be redundant. */
//    return false;
//  }
//
//  /* If there is more than one building in the same effects "group", then
//   * only the first one that exists can be active. */
//  effect_group_for (element elt : peffect.group.elements.data) {
//    if (elt.source_building == source) {
//      return false;
//    } else {
//      if (count_sources_in_range(target, target_player, target_city,
//				 target_building, elt.range,
//				 elt.survives, elt.source_building) > 0) {
//	/* The effect from this source in the group makes peffect
//	 * redundant.  Note this causes the redundancy even if the
//	 * elt.source_building has no effects actually in the group! */
//	return true;
//      }
//    }
//  } effect_group_}
//
//  return false;
//}
//
///**************************************************************************
//  Checks the requirements of the effect to see if it is active on the
//  given target. (If the requirements are not met the effect should be
//  ignored.)
//
//  target gives the type of the target
//  (player,city,building,tile) give the exact target
//  source gives the source type of the effect
//  peffect gives the exact effect value
//
//  Make sure you give all aspects of the target when calling this function:
//  for instance if you have TARGET_CITY pass the city's owner as the target
//  player as well as the city itself as the target city.
//**************************************************************************/
//static boolean are_effect_reqs_active(enum target_type target,
//				   final player target_player,
//				   final city target_city,
//				   int target_building,
//				   final tile target_tile,
//				   int source,
//				   final effect peffect)
//{
//  /* Note the target may actually not exist.  In particular, effects that
//   * have a REQ_SPECIAL or REQ_TERRAIN may often be passed to this function
//   * with a city as their target.  In this case the requirement is simply
//   * not met. */
//  switch (peffect.req.type) {
//  case REQ_NONE:
//    return true;
//    break;
//  case REQ_TECH:
//    /* The requirement is filled if the player owns the tech. */
//    return target_player && (get_invention(target_player,
//					   peffect.req.value.tech)
//			     == TECH_KNOWN);
//    break;
//  case REQ_GOV:
//    /* The requirement is filled if the player is using the government. */
//    return target_player && (target_player.government
//			     == peffect.req.value.gov);
//    break;
//  case REQ_BUILDING:
//    /* The requirement is filled if there's at least one of the building
//     * in the city.  (This is a slightly nonstandard use of
//     * count_sources_in_range.) */
//    return (count_sources_in_range(target, target_player, target_city,
//				   target_building, EFR_CITY, false,
//				   peffect.req.value.building) > 0);
//    break;
//  case REQ_SPECIAL:
//    /* The requirement is filled if the tile has the special. */
//    return target_tile && Map.tile_has_special(target_tile,
//					   peffect.req.value.special);
//    break;
//  case REQ_TERRAIN:
//    /* The requirement is filled if the tile has the terrain. */
//    return target_tile && (target_tile.terrain
//			   == peffect.req.value.terrain);
//    break;
//  case REQ_LAST:
//    break;
//  }
//
//  assert(0!=1);
//  return false;
//}
//
///**************************************************************************
//  Can the effect from the source building be active at a certain target
//  (player, city or building)?  This doesn't check if the source exists,
//  but tells whether the effect from it would be active if the source did
//  exist.  It is thus useful to the AI in figuring out what sources to
//  try to obtain.
//
//  target gives the type of the target
//  (player,city,building,tile) give the exact target
//  source gives the source type of the effect
//  peffect gives the exact effect value
//**************************************************************************/
//boolean is_effect_useful(enum target_type target,
//		      final player target_player,
//		      final city target_city,
//		      int target_building,
//		      final tile target_tile,
//		      int source, final effect peffect)
//{
//  if (is_effect_redundant(target, target_player, target_city,
//			  target_building, source, peffect)) {
//    return false;
//  }
//  return are_effect_reqs_active(target, target_player, target_city,
//				target_building, target_tile,
//				source, peffect);
//}
//
///**************************************************************************
//  Is the effect from the source building active at a certain target (player,
//  city or building)?
//
//  This checks whether the source exists, whether it is made redundant by
//  another element in its group, and if its requirements are met.
//
//  target gives the type of the target
//  (player,city,building,tile) give the exact target
//  source gives the source type of the effect
//  peffect gives the exact effect value
//**************************************************************************/
//static boolean is_effect_active(enum target_type target,
//			     final player plr,
//			     final city pcity,
//			     int building,
//			     final tile ptile,
//			     int source,
//			     final effect peffect)
//{
//  if (count_sources_in_range(target, plr, pcity, building, peffect.range,
//			     peffect.survives, source) == 0) {
//    return false;
//  }
//  return is_effect_useful(target, plr, pcity, building,
//			  ptile, source, peffect);
//}
//
///**************************************************************************
//  Returns true if a building is replaced.  To be replaced, all its effects
//  must be made redundant by groups that it is in.
//**************************************************************************/
//boolean is_building_replaced(final city pcity, int building)
//{
//  boolean groups_present = false;
//
//  /* A building that has no effects is never redundant. */
//  effect_type_vector_iterate(get_building_effect_types(building), ptype) {
//    effect_list_iterate(*get_building_effects(building, *ptype), peffect) {
//      /* We use TARGET_BUILDING as the lowest common denominator.  Note that
//       * the building is its own target - but whether this is actually
//       * checked depends on the range of the effect. */
//      if (!is_effect_redundant(TARGET_BUILDING, City.city_owner(pcity), pcity,
//			       building, building, peffect)) {
//	return false;
//      }
//      if (peffect.group) {
//	groups_present = true;
//      }
//    } }
//  } effect_type_vector_iterate_end;
//
//  return groups_present;
//}
//
///**************************************************************************
//  Get the total value, for one effect type, of one source building type on
//  the given target.
//
//  target gives the type of the target
//  (player,city,building,tile) give the exact target
//  source gives the source type of the effect
//  effect_type gives the effect type to be considered
//**************************************************************************/
//static int get_effect_value(enum target_type target,
//			    final player target_player,
//			    final city target_city,
//			    int target_building,
//			    final tile target_tile,
//			    int source,
//			    enum effect_type effect_type)
//{
//  int value = 0;
//
//  /* Loop over all effects of this type provided by the given source. */
//  effect_list_iterate(*get_building_effects(source, effect_type), peffect) {
//    /* For each effect, see if it is active. */
//    if (is_effect_active(target, target_player, target_city,
//			 target_building, target_tile,
//			 source, peffect)) {
//      /* And if so add on the value. */
//      value += peffect.value;
//    }
//  } }
//
//  return value;
//}
//
///**************************************************************************
//  Returns the effect bonus of a given type for any target.
//
//  target gives the type of the target
//  (player,city,building,tile) give the exact target
//  effect_type gives the effect type to be considered
//
//  Returns the effect sources of this type _currently active_.
//
//  The returned vector must be freed (building_vector_free) when the caller
//  is done with it.
//**************************************************************************/
//static int get_target_bonus_sources(effect_source_vector sources,
//    				    enum target_type target,
//			  	    final player target_player,
//				    final city target_city,
//				    int target_building,
//				    final tile target_tile,
//				    enum effect_type effect_type)
//{
//  int bonus = 0;
//
//  if (sources) {
//    effect_source_vector_init(sources);
//  }
//
//  /* Loop over all sources that may provide this effect. */
//  building_vector_iterate(get_buildings_with_effect(effect_type), pbldg) {
//    int value;
//
//    /* And for each source, add on the amount of effect provided by it. */
//    value = get_effect_value(target, target_player, target_city,
//			     target_building, target_tile,
//			     *pbldg, effect_type);
//    bonus += value;
//
//    if (sources) {
//      struct effect_source e;
//
//      e.building = *pbldg;
//      e.effect_value = value;
//
//      if (value != 0) {
//	effect_source_vector_append(sources, &e);
//      }
//    }
//  } building_vector_iterate_end;
//
//  return bonus;
//}
//
///**************************************************************************
//  Returns the effect bonus for a player.
//**************************************************************************/
//int get_player_bonus(final player pplayer,
//		     enum effect_type effect_type)
//{
//  return get_target_bonus_sources(null, TARGET_PLAYER,
//				  pplayer, null, Improvement.B_LAST, null,
//				  effect_type);
//}
//
///**************************************************************************
//  Returns the effect bonus at a city.
//**************************************************************************/
public static int get_city_bonus(final city pcity, effect_type effect_type)
{
//  return get_target_bonus_sources(null, TARGET_CITY,
//			 	  City.city_owner(pcity), pcity, Improvement.B_LAST, null,
//				  effect_type);
	return 0;
}
//
///**************************************************************************
//  Returns the effect bonus at a city tile.
//**************************************************************************/
//int get_city_tile_bonus(final city pcity, final tile ptile,
//			enum effect_type effect_type)
//{
//  return get_target_bonus_sources(null, TARGET_CITY,
//				  City.city_owner(pcity), pcity, Improvement.B_LAST, ptile,
//				  effect_type);
//}
//
///**************************************************************************
//  Returns the effect bonus at a building.
//**************************************************************************/
//int get_building_bonus(final city pcity, int id,
//		       enum effect_type effect_type)
//{
//  return get_target_bonus_sources(null, TARGET_BUILDING,
//				  City.city_owner(pcity), pcity, id, null,
//				  effect_type);
//}
//
///**************************************************************************
//  Returns the effect sources of this type _currently active_ at the player.
//
//  The returned vector must be freed (building_vector_free) when the caller
//  is done with it.
//**************************************************************************/
//int get_player_bonus_sources(effect_source_vector sources,
//    final player pplayer, enum effect_type effect_type)
//{
//  return get_target_bonus_sources(sources, TARGET_PLAYER,
//			  	  pplayer, null, Improvement.B_LAST, null,
//				  effect_type);
//}
//
///**************************************************************************
//  Returns the effect sources of this type _currently active_ at the city.
//
//  The returned vector must be freed (building_vector_free) when the caller
//  is done with it.
//**************************************************************************/
//int get_city_bonus_sources(effect_source_vector sources,
//    final city pcity, enum effect_type effect_type)
//{
//  return get_target_bonus_sources(sources, TARGET_CITY,
//			 	  City.city_owner(pcity), pcity, Improvement.B_LAST, null,
//				  effect_type);
//}

/**************************************************************************
  Returns the effect bonus the currently-in-finalruction-item will provide.

  Note this is not called get_current_production_bonus because that would
  be confused with EFT_PROD_BONUS.
**************************************************************************/
public static int get_current_finalruction_bonus(final city pcity,
				   effect_type effect_type)
{
//  if (!pcity.is_building_unit) {
//    int bldg = pcity.currently_building;
//    int power = 0;
//
//    effect_list_iterate(*get_building_effects(bldg, effect_type), peffect) {
//      if (is_effect_useful(TARGET_BUILDING, City.city_owner(pcity),
//			   pcity, bldg, null, bldg, peffect)) {
//	power += peffect.value;
//      }
//    } }
//
//    return power;
//  }

  return 0;
}

}