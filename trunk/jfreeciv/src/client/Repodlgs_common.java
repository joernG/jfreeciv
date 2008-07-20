package client;

public class Repodlgs_common{

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
//#include "game.h"
//#include "government.h"
//#include "mem.h"		/* free */
//#include "support.h"		/* my_snprintf */
//
//#include "repodlgs_g.h"
//
//#include "civclient.h"		/* can_client_issue_orders */
//#include "control.h"
//#include "repodlgs_common.h"
//#include "packhand_gen.h"
//
//options_settable settable_options;
//int num_settable_options;
//
//char **options_categories;
//int num_options_categories;
//
///****************************************************************
//  Fills out the array of struct improvement_entry given by
//  entries. The array must be able to hold at least B_LAST entries.
//*****************************************************************/
//void get_economy_report_data(improvement_entry entries,
//			     int *num_entries_used, int *total_cost,
//			     int *total_income)
//{
//  *num_entries_used = 0;
//  *total_cost = 0;
//
//  impr_type_iterate(impr_id) {
//    if (!is_wonder(impr_id)) {
//      int count = 0, cost = 0;
//      for (city pcity : game.player_ptr.cities.data) {
//	if (city_got_building(pcity, impr_id)) {
//	  count++;
//	  cost += improvement_upkeep(pcity, impr_id);
//	}
//      }
//      }
//
//      if (count == 0) {
//	continue;
//      }
//
//      entries[*num_entries_used].type = impr_id;
//      entries[*num_entries_used].count = count;
//      entries[*num_entries_used].total_cost = cost;
//      entries[*num_entries_used].cost = cost / count;
//      (*num_entries_used)++;
//
//      /* Currently there is no building expense under anarchy.  It's
//       * not a good idea to hard-code this in the client, but what
//       * else can we do? */
//      if (game.player_ptr.government != game.government_when_anarchy) {
//        *total_cost += cost;
//      }
//    }
//  } impr_type_iterate_end;
//
//  *total_income = 0;
//
//  for (city pcity : game.player_ptr.cities.data) {
//    *total_income += pcity.tax_total;
//    if (get_current_finalruction_bonus(pcity, EFT_PROD_TO_GOLD) > 0) {
//      *total_income += MAX(0, pcity.shield_surplus);
//    }
//  } }
//}
//
///******************************************************************
//  Returns an array of units with gold_upkeep. Number of units in 
//  the array is added to num_entries_used.
//******************************************************************/
//void get_economy_report_units_data(unit_entry entries,
//				   int *num_entries_used, int *total_cost)
//{
//  int count, cost, partial_cost;
//  unit_type unittype;
//
//  unit_type_iterate(utype) {
//    unittype = get_unit_type(utype);
//    cost = utype_gold_cost(unittype, get_gov_pplayer(game.player_ptr));
//
//    if (cost == 0) {
//      continue;
//    }
//
//    count = 0;
//    partial_cost = 0;
//
//    for (city pcity : game.player_ptr.cities.data) {
//      for (unit punit : pcity.units_supported.data) {
//
//	if (punit.type == utype) {
//	  count++;
//	  partial_cost += punit.upkeep_gold;
//	}
//
//      } }
//    } }
//
//    if (count == 0) {
//      continue;
//    }
//
//    (*total_cost) += partial_cost;
//
//    entries[*num_entries_used].type = utype;
//    entries[*num_entries_used].count = count;
//    entries[*num_entries_used].cost = cost;
//    entries[*num_entries_used].total_cost = partial_cost;
//    (*num_entries_used)++;
//
//  } unit_type_iterate_end;
//}
//
//static int frozen_level = 0;
//
///******************************************************************
// Turn off updating of reports
//*******************************************************************/
//void report_dialogs_freeze()
//{
//  frozen_level++;
//}
//
///******************************************************************
// Turn on updating of reports
//*******************************************************************/
//void report_dialogs_thaw()
//{
//  frozen_level--;
//  assert(frozen_level >= 0);
//  if (frozen_level == 0) {
//    update_report_dialogs();
//  }
//}
//
///******************************************************************
// Turn on updating of reports
//*******************************************************************/
//void report_dialogs_force_thaw()
//{
//  frozen_level = 1;
//  report_dialogs_thaw();
//}
//
///******************************************************************
// ...
//*******************************************************************/
//boolean is_report_dialogs_frozen()
//{
//  return frozen_level > 0;
//}
//
///******************************************************************
// initialize settable_options[] and options_categories[]
//*******************************************************************/
//void settable_options_init()
//{
//  settable_options = null;
//  num_settable_options = 0;
//
//  options_categories = null;
//  num_options_categories = 0;
//}
//
///******************************************************************
// free settable_options[] and options_categories[]
//*******************************************************************/
//void settable_options_free()
//{
//  int i;
//
//  for (i = 0; i < num_settable_options; i++) {
//    if (settable_options[i].name) {
//      free(settable_options[i].name);
//    }
//    if (settable_options[i].short_help) {
//      free(settable_options[i].short_help);
//    }
//    if (settable_options[i].extra_help) {
//      free(settable_options[i].extra_help);
//    }
//    if (settable_options[i].strval) {
//      free(settable_options[i].strval);
//    }
//    if (settable_options[i].default_strval) {
//      free(settable_options[i].default_strval);
//    }
//  }
//  free(settable_options);
//
//  for (i = 0; i < num_options_categories; i++) {
//    free(options_categories[i]);
//  }
//  free(options_categories);
//
//  settable_options_init();
//}
//
///******************************************************************
// reinitialize the options_settable struct: allocate enough
// space for all the options that the server is going to send us.
//*******************************************************************/
//void handle_options_settable_control(
//                               packet_options_settable_control packet)
//{
//  int i; 
//
//  settable_options_free();
//
//  options_categories = fc_malloc(packet.ncategories * sizeof(char *));
//  num_options_categories = packet.ncategories;
//  
//  for (i = 0; i < num_options_categories; i++) {
//    options_categories[i] = mystrdup(packet.category_names[i]);
//  }
//
//  /* avoid a malloc of size 0 warning */
//  if (packet.nids == 0) {
//    return;
//  }
//
//  settable_options = fc_malloc(packet.nids * sizeof(struct options_settable));
//  num_settable_options = packet.nids;
//
//  for (i = 0; i < num_settable_options; i++) {
//    settable_options[i].name = null;
//    settable_options[i].short_help = null;
//    settable_options[i].extra_help = null;
//    settable_options[i].strval = null;
//    settable_options[i].default_strval = null;
//  }
//}
//
///******************************************************************
// Fill the settable_options array with an option.
// If we've filled the last option, popup the dialog.
//*******************************************************************/
//void handle_options_settable(packet_options_settable packet)
//{
//  int i = packet.id;
//
//  assert(i >= 0);
//
//  settable_options[i].name = mystrdup(packet.name);
//  settable_options[i].short_help = mystrdup(packet.short_help);
//  settable_options[i].extra_help = mystrdup(packet.extra_help);
//
//  settable_options[i].type = packet.type;
//  settable_options[i].category = packet.category;
//
//  switch (packet.type) {
//  case SSET_BOOL:
//    settable_options[i].val = packet.val;
//    settable_options[i].min = false;
//    settable_options[i].max = true;
//    settable_options[i].strval = null;
//    settable_options[i].default_strval = null;
//    break;
//  case SSET_INT:
//    settable_options[i].val = packet.val;
//    settable_options[i].min = packet.min;
//    settable_options[i].max = packet.max;
//    settable_options[i].strval = null;
//    settable_options[i].default_strval = null;
//    break;
//  case SSET_STRING:
//    settable_options[i].strval = mystrdup(packet.strval);
//    settable_options[i].default_strval = mystrdup(packet.default_strval);
//    break;
//  default:
//    assert(0);
//  }
//
//  /* if we've received all the options, pop up the settings dialog */
//  if (i == num_settable_options - 1) {
//    popup_settable_options_dialog();
//  }
//}
//
///****************************************************************************
//  Sell all improvements of the given type in all cities.  If "obsolete_only"
//  is specified then only those improvements that are replaced will be sold.
//
//  The "message" string will be filled with a GUI-friendly message about
//  what was sold.
//****************************************************************************/
//void sell_all_improvements(Impr_Type_id impr, boolean obsolete_only,
//			   char *message, size_t message_sz)
//{
//  int count = 0, gold = 0;
//
//  if (!can_client_issue_orders()) {
//    my_snprintf(message, message_sz, "You cannot sell improvements.");
//    return;
//  }
//
//  for (city pcity : game.player_ptr.cities.data) {
//    if (!pcity.did_sell && city_got_building(pcity, impr)
//	&& (!obsolete_only
//	    || improvement_obsolete(game.player_ptr, impr)
//	    || is_building_replaced(pcity, impr))) {
//      count++;
//      gold += impr_sell_gold(impr);
//      city_sell_improvement(pcity, impr);
//    }
//  } }
//
//  if (count > 0) {
//    my_snprintf(message, message_sz, "Sold %d %s for %d gold.",
//		count, get_improvement_name(impr), gold);
//  } else {
//    my_snprintf(message, message_sz, "No %s could be sold.",
//		get_improvement_name(impr));
//  }
//}
//
///****************************************************************************
//  Disband all supported units of the given type.  If in_cities_only is
//  specified then only units inside our cities will be disbanded.
//
//  The "message" string will be filled with a GUI-friendly message about
//  what was sold.
//****************************************************************************/
//void disband_all_units(int type, boolean in_cities_only,
//		       char *message, size_t message_sz)
//{
//  int count = 0;
//
//  if (!can_client_issue_orders()) {
//    /* TRANS: Obscure observer error. */
//    my_snprintf(message, message_sz, "You cannot disband units.");
//    return;
//  }
//
//  if (unit_type_flag(type, F_UNDISBANDABLE)) {
//    my_snprintf(message, message_sz, "%s cannot be disbanded.",
//		unit_name(type));
//    return;
//  }
//
//  for (city pcity : game.player_ptr.cities.data) {
//    /* Only supported units are disbanded.  Units with no homecity have no
//     * cost and are not disbanded. */
//    for (unit punit : pcity.units_supported.data) {
//      city incity = map_get_city(punit.tile);
//
//      if (punit.type == type
//	  && (!in_cities_only
//	      || (incity && city_owner(incity) == game.player_ptr))) {
//	count++;
//	request_unit_disband(punit);
//      }
//    } }
//  } }
//
//  if (count > 0) {
//    my_snprintf(message, message_sz, "Disbanded %d %s.",
//		count, unit_name(type));
//  } else {
//    my_snprintf(message, message_sz, "No %s could be disbanded.",
//		unit_name(type));
//  }
//}
}