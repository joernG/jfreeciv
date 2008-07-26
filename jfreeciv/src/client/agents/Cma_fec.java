package client.agents;

public class Cma_fec{

// Freeciv - Copyright (C) 2001 - R. Falke
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
///**************************************************************************
// This is the common file for all front-end (Front End Common) for the
// citizen management agent (CMA).
//**************************************************************************/
//
//#ifdef HAVE_CONFIG_H
//#include <config.h>
//#endif
//
//#include <assert.h>
//#include <string.h>
//
//#include "fcintl.h"
//#include "Game.game.h"
//#include "log.h"
//#include "mem.h"
//#include "support.h"
//
//#include "agents.h"
//#include "attribute.h"
//
//#include "cma_fec.h"
//
//public static final int RESULT_COLUMNS = 10;
//public static final int BUFFER_SIZE = 100;
//public static final int MAX_LEN_PRESET_NAME = 80;
//
//struct cma_preset {
//  char *descr;
//  struct cm_parameter parameter;
//};
//
//#define SPECLIST_TAG preset
//#define SPECLIST_TYPE struct cma_preset
//#include "speclist.h"
//
//#define preset_list_iterate(presetlist, ppreset) \
//    TYPED_LIST_ITERATE(struct cma_preset, presetlist, ppreset)
//#define preset_list_iterate_end  LIST_ITERATE_END
//
//static Speclists<preset> preset_list;
//static boolean preset_list_has_been_initialized = false;
//
///****************************************************************************
// Is called if the Game.game removes a city. It will clear the 
// "fe parameter" attribute to reduce the size of the savegame.   
//*****************************************************************************/
//static void city_remove(int city_id)
//{
//  attr_city_set(ATTR_CITY_CMAFE_PARAMETER, city_id, 0, null);
//}
//
///**************************************************************************
// Initialize the presets if there are no presets loaded on startup.
//**************************************************************************/
//void cmafec_init()
//{
//  struct agent self;
//
//  if (!preset_list_has_been_initialized) {
//    preset_list_init(&preset_list);
//    preset_list_has_been_initialized = true;
//  }
//
//  memset(&self, 0, sizeof(self));
//  strcpy(self.name, "CMA");
//  self.level = 1;
//  self.city_callbacks[CB_REMOVE] = city_remove;
//  register_agent(&self);
//}
//
///**************************************************************************
// ...
//**************************************************************************/
//void cmafec_free()
//{
//  while (cmafec_preset_num() > 0) {
//    cmafec_preset_remove(0);
//  }
//}
//
///**************************************************************************
// Sets the front-end parameter.
//**************************************************************************/
//void cmafec_set_fe_parameter(city pcity,
//			     final cm_parameter final parameter)
//{
//  cma_set_parameter(ATTR_CITY_CMAFE_PARAMETER, pcity.id, parameter);
//}
//
///****************************************************************
// Return the front-end parameter for the given city. Returns a dummy
// parameter if no parameter was set.
//*****************************************************************/
//void cmafec_get_fe_parameter(city pcity, cm_parameter dest)
//{
//  struct cm_parameter parameter;
//
//  /* our fe_parameter could be stale. our agents parameter is uptodate */
//  if (cma_is_city_under_agent(pcity, &parameter)) {
//    cm_copy_parameter(dest, &parameter);
//    cmafec_set_fe_parameter(pcity, dest);
//  } else {
//    /* Create a dummy parameter to return. */
//    cm_init_parameter(dest);
//    if (!cma_get_parameter(ATTR_CITY_CMAFE_PARAMETER, pcity.id, dest)) {
//      /* We haven't seen this city before; store the dummy. */
//      cmafec_set_fe_parameter(pcity, dest);
//    }
//  }
//}
//
///**************************************************************************
// Adds a preset.
//**************************************************************************/
//void cmafec_preset_add(final String descr_name, cm_parameter pparam)
//{
//  cma_preset ppreset = fc_malloc(sizeof(struct cma_preset));
//
//  if (!preset_list_has_been_initialized) {
//    preset_list_init(&preset_list);
//    preset_list_has_been_initialized = true;
//  }
//
//  cm_copy_parameter(&ppreset.parameter, pparam);
//  ppreset.descr = fc_malloc(MAX_LEN_PRESET_NAME);
//  () mystrlcpy(ppreset.descr, descr_name, MAX_LEN_PRESET_NAME);
//  preset_list_insert(&preset_list, ppreset);
//}
//
///**************************************************************************
// Removes a preset.
//**************************************************************************/
//void cmafec_preset_remove(int index)
//{
//  cma_preset ppreset;
//
//  assert(index >= 0 && index < cmafec_preset_num());
//
//  ppreset = preset_list_get(&preset_list, index);
//  preset_list_unlink(&preset_list, ppreset);
//
//  free(ppreset.descr);
//  free(ppreset);
//}
//
///**************************************************************************
// Returns the indexed preset's description.
//**************************************************************************/
//char *cmafec_preset_get_descr(int index)
//{
//  cma_preset ppreset;
//
//  assert(index >= 0 && index < cmafec_preset_num());
//
//  ppreset = preset_list_get(&preset_list, index);
//  return ppreset.descr;
//}
//
///**************************************************************************
// Returns the indexed preset's parameter.
//**************************************************************************/
//final cm_parameter cmafec_preset_get_parameter(int index)
//{
//  cma_preset ppreset;
//
//  assert(index >= 0 && index < cmafec_preset_num());
//
//  ppreset = preset_list_get(&preset_list, index);
//  return &ppreset.parameter;
//}
//
///**************************************************************************
// Returns the index of the preset which matches the given
// parameter. Returns -1 if no preset could be found.
//**************************************************************************/
//int cmafec_preset_get_index_of_parameter(final struct cm_parameter
//					 *final parameter)
//{
//  int i;
//
//  for (i = 0; i < preset_list.foo_list_size(); i++) {
//    cma_preset ppreset = preset_list_get(&preset_list, i);
//    if (cm_are_parameter_equal(&ppreset.parameter, parameter)) {
//      return i;
//    }
//  }
//  return -1;
//}
//
///**************************************************************************
// Returns the total number of presets.
//**************************************************************************/
//int cmafec_preset_num()
//{
//  return preset_list.foo_list_size();
//}
//
///**************************************************************************
//...
//**************************************************************************/
//final String cmafec_get_short_descr_of_city(final city pcity)
//{
//  struct cm_parameter parameter;
//
//  if (!cma_is_city_under_agent(pcity, &parameter)) {
//    return "none";
//  } else {
//    return cmafec_get_short_descr(&parameter);
//  }
//}
//
///**************************************************************************
// Returns the description of the matching preset or "custom" if no
// preset could be found.
//**************************************************************************/
//final String cmafec_get_short_descr(final cm_parameter final
//				   parameter)
//{
//  int index = cmafec_preset_get_index_of_parameter(parameter);
//
//  if (index == -1) {
//    return "custom";
//  } else {
//    return cmafec_preset_get_descr(index);
//  }
//}
//
///**************************************************************************
//...
//**************************************************************************/
//static final String get_city_growth_string(city pcity, int surplus)
//{
//  int stock, cost, turns;
//  static char buffer[50];
//
//  if (surplus == 0) {
//    buffer = util.my_snprintf( "never");
//    return buffer;
//  }
//
//  stock = pcity.food_stock;
//  cost = city_granary_size(pcity.size);
//
//  stock += surplus;
//
//  if (stock >= cost) {
//    turns = 1;
//  } else if (surplus > 0) {
//    turns = ((cost - stock - 1) / surplus) + 1 + 1;
//  } else {
//    if (stock < 0) {
//      turns = -1;
//    } else {
//      turns = (stock / surplus);
//    }
//  }
//  buffer = util.my_snprintf( PL("%d turn", "%d turns", turns),
//	      turns);
//  return buffer;
//}
//
///**************************************************************************
//...
//**************************************************************************/
//static final String get_prod_complete_string(city pcity, int surplus)
//{
//  int stock, cost, turns;
//  static char buffer[50];
//
//  if (surplus <= 0) {
//    buffer = util.my_snprintf( "never");
//    return buffer;
//  }
//
//  stock = pcity.shield_stock;
//  if (pcity.is_building_unit) {
//    cost = Unittype_P.unit_build_shield_cost(pcity.currently_building);
//  } else {
//    if (Effects.get_current_finalruction_bonus(pcity, effect_type.EFT_PROD_TO_GOLD) > 0) {
//      buffer = util.my_snprintf(
//		  get_improvement_type(pcity.currently_building).name);
//      return buffer;
//    }
//    cost = Improvement.impr_build_shield_cost(pcity.currently_building);
//  }
//
//  stock += surplus;
//
//  if (stock >= cost) {
//    turns = 1;
//  } else if (surplus > 0) {
//    turns = ((cost - stock - 1) / surplus) + 1 + 1;
//  } else {
//    if (stock < 0) {
//      turns = -1;
//    } else {
//      turns = (stock / surplus);
//    }
//  }
//  buffer = util.my_snprintf( PL("%d turn", "%d turns", turns),
//	      turns);
//  return buffer;
//}
//
///**************************************************************************
//...
//**************************************************************************/
//final String cmafec_get_result_descr(city pcity,
//				    final cm_result final
//				    result,
//				    final cm_parameter final
//				    parameter)
//{
//  int j;
//  char buf[RESULT_COLUMNS][BUFFER_SIZE];
//  static char buffer[600];
//
//  if (!result.found_a_valid) {
//    for (j = 0; j < RESULT_COLUMNS; j++)
//      my_snprintf(buf[j], BUFFER_SIZE, "---");
//  } else {
//    for (j = 0; j < NUM_STATS; j++) {
//      my_snprintf(buf[j], BUFFER_SIZE, "%+3d", result.surplus[j]);
//    }
//
//    my_snprintf(buf[6], BUFFER_SIZE, "%d/%s%s",
//		pcity.size - cm_count_specialist(pcity, result),
//		specialists_string(result.specialists),
//		result.happy ? " happy" : "");
//
//    my_snprintf(buf[7], BUFFER_SIZE, "%s",
//		get_city_growth_string(pcity, result.surplus[FOOD]));
//    my_snprintf(buf[8], BUFFER_SIZE, "%s",
//		get_prod_complete_string(pcity, result.surplus[SHIELD]));
//    my_snprintf(buf[9], BUFFER_SIZE, "%s",
//		cmafec_get_short_descr(parameter));
//  }
//
//  buffer = util.my_snprintf(
//	      ("Name: %s\n" +
//		"Food:       %10s Gold:    %10s\n" +
//		"Production: %10s Luxury:  %10s\n" +
//		"Trade:      %10s Science: %10s\n" +
//		"\n" +
//		"    People (W/E/S/T): %s\n" +
//		"          City grows: %s\n" +
//		"Production completed: %s"),
//	      buf[9], buf[FOOD], buf[GOLD], buf[SHIELD], buf[LUXURY],
//	      buf[TRADE], buf[SCIENCE], buf[6], buf[7], buf[8]);
//
//  util.freelog(Log.LOG_DEBUG, "\n%s", buffer);
//  return buffer;
//}
//
//
///**************************************************************************
//  Create default cma presets for a new user (or without configuration file)
//**************************************************************************/
//void create_default_cma_presets()
//{
// int i;
// struct cm_parameter parameters[] = {
//   { /* very happy */
//     .minimal_surplus = {0, 0, 0, -20, 0, 0},
//     .require_happy = false,
//     .allow_disorder = false,
//     .allow_specialists = true,
//     .factor = {10, 5, 0, 4, 0, 4},
//     .happy_factor = 25
//   },
//   { /* max food */
//     .minimal_surplus = {-20, 0, 0, -20, 0, 0},
//     .require_happy = false,
//     .allow_disorder = false,
//     .allow_specialists = true,
//     .factor = {25, 5, 0, 4, 0, 4},
//     .happy_factor = 0
//   },
//   { /* max prod */
//     .minimal_surplus = {0, -20, 0, -20, 0, 0},
//     .require_happy = false,
//     .allow_disorder = false,
//     .allow_specialists = true,
//     .factor = {10, 25, 0, 4, 0, 4},
//     .happy_factor = 0
//   },
//   { /* max gold */
//     .minimal_surplus = {0, 0, 0, -20, 0, 0},
//     .require_happy = false,
//     .allow_disorder = false,
//     .allow_specialists = true,
//     .factor = {10, 5, 0, 25, 0, 4},
//     .happy_factor = 0
//   },
//   { /* max science */
//     .minimal_surplus = {0, 0, 0, -20, 0, 0},
//     .require_happy = false,
//     .allow_disorder = false,
//     .allow_specialists = true,
//     .factor = {10, 5, 0, 4, 0, 25},
//     .happy_factor = 0
//   }
// };
// final char* names[ARRAY_SIZE(parameters)] = {
//   N"?cma:Very happy",
//   N"?cma:Max food",
//   N"?cma:Max production",
//   N"?cma:Max gold",
//   N"?cma:Max science"
// };
//
// for (i = ARRAY_SIZE(parameters) - 1; i >= 0; i--) {
//   cmafec_preset_add(Q_(names[i]), &parameters[i]);
// }
//}
}