package client.gui_mui;

import common.Game;

public class Citydlg{

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
//#include <stdio.h>
//#include <stdlib.h>
//#include <string.h>
//
//#include <libraries/mui.h>
//#include <mui/NListview_MCC.h>
//
//#include <clib/alib_protos.h>
//#include <proto/exec.h>
//#include <proto/utility.h>
//#include <proto/muimaster.h>
//#include <exec/memory.h>
//
//#include "city.h"
//#include "fcintl.h"
//#include "Game.game.h"
//#include "genlist.h"
//#include "government.h"
//#include "Map.map.h"
//#include "mem.h"
//#include "packets.h"
//#include "player.h"
//#include "shared.h"
//
//#include "text.h"
//
//#include "cityrep.h"
//#include "clinet.h"
//#include "cma_fec.h"
//#include "colors.h"
//#include "control.h"
//#include "dialogs.h"
//#include "graphics.h"
//#include "helpdlg.h"
//#include "inputdlg.h"
//#include "mapctrl.h"
//#include "mapview.h"
//#include "support.h"
//#include "repodlgs.h"
//#include "tilespec.h"
//#include "wldlg.h"
//
//#include "gui_main.h"
//#include "citydlg.h"
//#include "autogroupclass.h"
//#include "muistuff.h"
//#include "mapclass.h"
//#include "transparentstringclass.h"
//#include "worklistclass.h"
//
///* get 'Speclists<dialog>' and related functions: */
//#define SPECLIST_TAG dialog
//#define SPECLIST_TYPE struct city_dialog
//#include "speclist.h"
//
//#define dialog_list_iterate(dialoglist, pdialog) \
//    TYPED_LIST_ITERATE(struct city_dialog, dialoglist, pdialog)
//#define dialog_list_iterate_end  LIST_ITERATE_END
//
//
//enum { OVERVIEW_PAGE, UNITS_PAGE, WORKLIST_PAGE,
//  HAPPINESS_PAGE, CMA_PAGE, TRADE_PAGE, MISC_PAGE
//};
//
//enum info_style { NORMAL, ORANGE, RED, NUM_INFO_STYLES };
//
//public static final int NUM_CITIZENS_SHOWN = 25;
//public static final int NUM_CITY_OPTS = 5;
//public static final int NUM_INFO_FIELDS = 10;      /* number of fields in city_info */
//public static final int NUM_PAGES = 8;             /* the number of pages in city dialog notebook 
//                                 * (+1) if you change this, you must add an
//                                 * entry to misc_whichtab_label[] */
//
//public static final int NUM_HAPPINESS_MODIFIERS = 5;
//enum { CITIES, LUXURIES, BUILDINGS, UNITS, WONDERS };
//
//static void set_cityopt_values(city_dialog pdialog);
//
///******************************************************************
//* functions in cma_fec.c but as static
//******************************************************************/
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
//static final String final get_prod_complete_string(city pcity,
//						  int surplus)
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
//    if (pcity.currently_building == B_CAPITAL) {
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
//
//struct city_prod
//{
//  Object *wnd;
//  city pcity;
//  Object *available_listview;
//  struct Hook available_disphook;
//};
//
//struct city_info
//{
//  Object *group;
//  Object *food_text;
//  Object *shield_text;
//  Object *trade_text;
//
//  Object *gold_text;
//  Object *luxury_text;
//  Object *science_text;
//
//  Object *granary_text;
//  Object *growth_text;
//
//  Object *corruption_text;
//  Object *waste_text;
//  Object *pollution_text;
//};
//
//struct city_dialog
//{
//  city pcity;
//
//  Object *wnd;
//
//  Object *citizen_group;
//  Object *citizen_left_space;
//  Object *citizen_right_space;
//  Object *citizen2_group;
//
//  Object *name_transparentstring;
//  Object *title_text;
//
//  struct city_info overview_city_info;
//
//  Object *register_group;
//
//  /* Units */
//  Object *units_supported_group;
//  Object *units_present_group;
//
//  /* Worklist */
//  Object *worklist_wl;
//
//  /* Happines */
//  Object *happines_map;
//  struct city_info happines_city_info;
//  Object *happiness_citizen_group[NUM_HAPPINESS_MODIFIERS];
//  Object *happiness_citizen_text[NUM_HAPPINESS_MODIFIERS];
//
//  /* CMA */
//  Object *result_text[10];
//  Object *minimal_surplus_slider[6];
//  Object *factor_slider[7];
//  Object *celebrate_check;
//  Object *cma_change_button;
//  Object *cma_perm_button;
//  Object *cma_release_button;
//
//  /* Trade */
//  Object *trade_text;
//
//  /* Misc settings */
//  Object *misc_radio;
//  Object *misc_checks[NUM_CITY_OPTS];
//  Object *misc_next_radio;
//
//  Object *map_area;
//
//  Object *prod_gauge;
//  Object *buy_button;
//  Object *change_button;
//  struct Hook imprv_disphook;
//  Object *imprv_listview;
//  Object *sell_button;
//
//  Object *present_group; /* auto group */
//  Object *supported_group; /* auto group */
//
//  Object *close_button;
//  Object *activateunits_button;
//  Object *unitlist_button;
//
//  struct city_prod prod;
//
//  int sell_id;
//  Object *sell_wnd;
//};
//
///* End GUI Independed */
//
//
//static int new_dialog_def_page = OVERVIEW_PAGE;
//static int last_page = OVERVIEW_PAGE;
//
//static struct genlist dialog_list;
//static int dialog_list_has_been_initialised;
//
//static city_dialog get_city_dialog(city pcity);
//static city_dialog create_city_dialog(city pcity);
//static void close_city_dialog(city_dialog pdialog);
//
//static void city_dialog_update_improvement_list(city_dialog pdialog);
//static void city_dialog_update_title(city_dialog pdialog);
//static void city_dialog_update_supported_units(city_dialog pdialog, int id);
//static void city_dialog_update_present_units(city_dialog pdialog, int id);
//static void city_dialog_update_tradelist(city_dialog pdialog);
//static void city_dialog_update_citizens(city_dialog pdialog);
//static void city_dialog_update_information(city_dialog pdialog, city_info info);
//static void city_dialog_update_map(city_dialog pdialog);
//static void city_dialog_update_building(city_dialog pdialog);
//
//static void refresh_cma_dialog(city_dialog pdialog);
//static void refresh_happiness_dialog(city_dialog pdialog);
//
///****************************************************************
//...
//*****************************************************************/
//static city_dialog get_city_dialog(city pcity)
//{
//  struct genlist_iterator myiter;
//
//  if (!dialog_list_has_been_initialised)
//  {
//    genlist_init(&dialog_list);
//    dialog_list_has_been_initialised = 1;
//  }
//
//  genlist_iterator_init(&myiter, &dialog_list, 0);
//
//  for (; ITERATOR_PTR(myiter); ITERATOR_NEXT(myiter))
//    if (((city_dialog ) ITERATOR_PTR(myiter)).pcity == pcity)
//      return ITERATOR_PTR(myiter);
//
//  return 0;
//}
//
///****************************************************************
//...
//*****************************************************************/
//boolean city_dialog_is_open(city pcity)
//{
//  return get_city_dialog(pcity) != null;
//}
//
///****************************************************************
//...
//*****************************************************************/
//static void refresh_this_city_dialog(city_dialog pdialog)
//{
//  city pcity = pdialog.pcity;
//  int units = (unit_list_size(&map_get_tile(pcity.tile).units) ? true : false);
//
//  city_dialog_update_improvement_list(pdialog);
//  city_dialog_update_title(pdialog);
//  city_dialog_update_information(pdialog,&pdialog.overview_city_info);
//  city_dialog_update_information(pdialog,&pdialog.happines_city_info);
//  city_dialog_update_supported_units(pdialog, 0);
//  city_dialog_update_present_units(pdialog, 0);
//  city_dialog_update_tradelist(pdialog);
//  city_dialog_update_citizens(pdialog);
//  city_dialog_update_map(pdialog);
//  city_dialog_update_building(pdialog);
//
//  set(pdialog.activateunits_button, MUIA_Disabled, !units);
//  set(pdialog.unitlist_button, MUIA_Disabled, !units);
//}
//
///****************************************************************
// Refresh every city dialog displaying this city
//*****************************************************************/
//void refresh_city_dialog(city pcity)
//{
//  /* from get_city_dialog() */
//  struct genlist_iterator myiter;
//
//  if (!dialog_list_has_been_initialised)
//  {
//    genlist_init(&dialog_list);
//    dialog_list_has_been_initialised = 1;
//  }
//
//  genlist_iterator_init(&myiter, &dialog_list, 0);
//
//  for (; ITERATOR_PTR(myiter); ITERATOR_NEXT(myiter))
//  {
//    if (((city_dialog ) ITERATOR_PTR(myiter)).pcity == pcity)
//    {
//      city_dialog pdialog = (city_dialog ) ITERATOR_PTR(myiter);
//
//      refresh_this_city_dialog(pdialog);
//      if (pcity.owner != Game.game.player_idx)
//      {
//	/* Set the buttons we do not want live while a Diplomat investigates */
//	set(pdialog.buy_button, MUIA_Disabled, true);
//	set(pdialog.sell_button, MUIA_Disabled, true);
//	set(pdialog.change_button, MUIA_Disabled, true);
//	set(pdialog.activateunits_button, MUIA_Disabled, true);
//	set(pdialog.unitlist_button, MUIA_Disabled, true);
//      } else
//      {
//	refresh_happiness_dialog(pdialog);
//	refresh_cma_dialog(pdialog);
//      }
//    }
//  }
//
//  if (pcity.owner == Game.game.player_idx)
//  {
//    city_report_dialog_update_city(pcity);
//    economy_report_dialog_update();
//  }
//}
//
///****************************************************************
//...
//*****************************************************************/
//void refresh_unit_city_dialogs(unit punit)
//{
//  city pcity_sup, *pcity_pre;
//  city_dialog pdialog;
//
//  pcity_sup = Player_P.player_find_city_by_id(Game.game.player_ptr, punit.homecity);
//  pcity_pre = Map.map_get_city(punit.tile);
//
//  if (pcity_sup && (pdialog = get_city_dialog(pcity_sup)))
//    city_dialog_update_supported_units(pdialog, 0);
//
//  if (pcity_pre && (pdialog = get_city_dialog(pcity_pre)))
//    city_dialog_update_present_units(pdialog, 0);
//}
//
///****************************************************************
//popup the dialog 10% inside the main-window 
//*****************************************************************/
//void popup_city_dialog(city pcity, boolean make_modal)
//{
//  city_dialog pdialog;
//
//  if (!(pdialog = get_city_dialog(pcity)))
//    pdialog = create_city_dialog(pcity);
//
//  if (pdialog)
//  {
//    set_cityopt_values(pdialog);
//
//    if (new_dialog_def_page == NUM_PAGES-1) set(pdialog.register_group, MUIA_Group_ActivePage, last_page);
//    else set(pdialog.register_group, MUIA_Group_ActivePage, new_dialog_def_page);
//
//    nnset(pdialog.misc_next_radio, MUIA_Radio_Active, new_dialog_def_page);
//
//    set(pdialog.wnd, MUIA_Window_Open, true);
//  }
//}
//
///****************************************************************
//popdown the dialog 
//*****************************************************************/
//void popdown_city_dialog(city pcity)
//{
//  city_dialog pdialog;
//
//  if ((pdialog = get_city_dialog(pcity)))
//    close_city_dialog(pdialog);
//}
//
///****************************************************************
//popdown all dialogs
//*****************************************************************/
//void popdown_all_city_dialogs()
//{
//  if (!dialog_list_has_been_initialised)
//  {
//    return;
//  }
//  while (genlist_size(&dialog_list))
//  {
//    close_city_dialog(genlist_get(&dialog_list, 0));
//  }
//}
//
///****************************************************************/
//
//struct city_node
//{
//  struct MinNode node;
//  city pcity;
//};
//
//struct city_browse_msg
//{
//  city_dialog pdialog;
//  ULONG direction;
//};				/* used by city_browse() */
//
//struct city_map_msg
//{
//  city_dialog pdialog;
//  Map_Click click;
//};				/* used by city_click() */
//
//struct city_citizen_msg
//{
//  city_dialog pdialog;
//  ULONG type;
//};				/* used by city_citizen() */
//
//struct city_unit_msg
//{
//  city_dialog pdialog;
//  unit punit;
//};				/* used by city_present() */
//
///****************************************************************
// Sort the city list alphabetically
//*****************************************************************/
//static void city_list_sort_amiga(MinList list)
//{
//  boolean notfinished = true;
//
//  /* Sort list (quick & dirty bubble sort) */
//  while (notfinished)
//  {
//    city_node first;
//
//    /* Reset not finished flag */
//    notfinished = false;
//
//    /* Get first node */
//    if ((first = (city_node ) List_First(list)))
//    {
//      city_node second;
//
//      /* One bubble sort round */
//      while ((second = (city_node ) Node_Next(first)))
//      {
//	if (Stricmp(first.pcity.name, second.pcity.name) > 0)
//	{
//	  Remove((Node ) first);
//	  Insert((List ) list, (Node ) first, (Node ) second);
//	  notfinished = true;
//	}
//	else
//	  first = second;
//      }
//    }
//  }
//}
//
///**************************************************************************
// Display function for the listview in the production window
//**************************************************************************/
//HOOKPROTO(city_prod_display, int, char **array, APTR msg)
//{
//  static char name[256];
//  static char info[32];
//  static char cost[32];
//  static char rounds[32];
//  ULONG which = (ULONG) msg;
//
//  city pcity = (city ) hook.h_Data;
//
//  if (which)
//  {
//    if (which >= 10000)
//    {
//      if (which == 20000)
//      {
//	name = String.format( "\33u\338Units\33n");
//	info[0] = cost[0] = rounds[0] = 0;
//      }
//      else
//      {
//	if (which == 20001)
//	{
//	  name = String.format( "\33u\338Improvements\33n");
//	  info[0] = cost[0] = rounds[0] = 0;
//	}
//	else
//	{
//	  /* Unit */
//	  which -= 10000;
//	  name = String.format( Unittype_P.unit_name(which));
//
//	  {
//	    /* from unit.h get_unit_name() */
//	    unit_type ptype;
//	    ptype = get_unit_type(which);
//	    if (ptype.fuel > 0)
//	      info = util.my_snprintf( "%d/%d/%d(%d)", ptype.attack_strength,
//		      ptype.defense_strength,
//		ptype.move_rate / 3, (ptype.move_rate / 3) * ptype.fuel);
//	    else
//	      info = util.my_snprintf( "%d/%d/%d", ptype.attack_strength,
//		      ptype.defense_strength, ptype.move_rate / 3);
//
//	  }
//
//	  cost = util.my_snprintf(
//		      "%d", Unittype_P.unit_build_shield_cost(which));
//	  rounds = util.my_snprintf( "%d",
//		      city_turns_to_build(pcity, which, true, true));
//	}
//      }
//    }
//    else
//    {
//      which--;
//      name = String.format( get_improvement_type(which).name);
//      info[0] = 0;
//
//      {
//	/* from city.c City.get_impr_name_ex() */
//	if (wonder_replacement(pcity, which))
//        {
//          info = String.format( "*");
//	}
//	else
//	{
//	  if (Improvement.is_wonder(which))
//	  {
//	    info = String.format( "Wonder");
//	    if (Game.game.global_wonders[which])
//	      info = String.format( "Built");
//	    if (wonder_obsolete(which))
//	      info = String.format( "Obsolete");
//	  }
//	}
//      }
//
//      if (which != B_CAPITAL)
//      {
//	cost = util.my_snprintf( "%d", Improvement.impr_build_shield_cost(which));
//	rounds = util.my_snprintf( "%d",
//		    city_turns_to_build(pcity, which, false, true));
//      }
//      else
//      {
//	cost = String.format( "--");
//	rounds = String.format( "--");
//      }
//    }
//    *array++ = name;
//    *array++ = info;
//    *array++ = rounds;
//    *array++ = cost;
//    *array = null;
//  }
//  else
//  {
//    *array++ = "Type";
//    *array++ = "Info";
//    *array++ = "Rounds";
//    *array++ = "Cost";
//    *array = null;
//  }
//  return 0;
//}
//
///**************************************************************************
// Display function for the listview in the city window
//**************************************************************************/
//HOOKPROTO(city_imprv_display, int, char **array, APTR msg)
//{
//  static char name[256];
//  static char cost[32];
//  ULONG which = (ULONG) msg;
//
//  if (which)
//  {
//    city_dialog pdialog = (city_dialog ) hook.h_Data;
//    which--;
//    name = util.my_snprintf( "%s", City.get_impr_name_ex(pdialog.pcity, which));
//    cost = util.my_snprintf( "%d", improvement_upkeep(pdialog.pcity, which));
//    *array++ = name;
//    *array = cost;
//  }
//  else
//  {
//    *array++ = "Type";
//    *array = "Upkeep";
//  }
//  return 0;
//}
//
///**************************************************************************
// Must be called from the Application object so it is safe to
// dispose the window
//**************************************************************************/
//static int city_close_real(city_dialog *ppdialog)
//{
//  close_city_dialog(*ppdialog);
//  return 0;
//}
//
///**************************************************************************
// Callback for the Close Button (or CloseGadget)
//**************************************************************************/
//static void city_close(city_dialog *ppdialog)
//{
//  set((*ppdialog).wnd, MUIA_Window_Open, false);
//  DoMethod(app, MUIM_Application_PushMethod, app, 4, MUIM_CallHook, &civstandard_hook, city_close_real, *ppdialog);
//}
//
///**************************************************************************
// Callback for the Yes in the Buy confirmation window
//**************************************************************************/
//static void city_buy_yes(popup_message_data data)
//{
//  city pcity = (city ) data.data;
//
//  city_buy_production(pcity);
//  destroy_message_dialog(data.wnd);
//}
//
//
///**************************************************************************
// Callback for the No in the Sell confirmation window
//**************************************************************************/
//static void city_sell_no(popup_message_data data)
//{
//  city_dialog pdialog = (city_dialog )data.data;
//  destroy_message_dialog(data.wnd);
//  set(pdialog.sell_button, MUIA_Disabled, false);
//  pdialog.sell_wnd = null;
//  pdialog.sell_id = -1;
//}
//
///**************************************************************************
// Callback for the Yes in the Sell confirmation window
//**************************************************************************/
//static void city_sell_yes(popup_message_data data)
//{
//  city_dialog pdialog = (city_dialog ) data.data;
//
//  if (pdialog.sell_id >= 0) {
//    city_sell_improvement(pdialog.pcity, pdialog.sell_id);
//  }
//
//  destroy_message_dialog(data.wnd);
//  pdialog.sell_wnd = null;
//  pdialog.sell_id = -1;
//}
//
///**************************************************************************
// Callback to accept the options in the configure window
//**************************************************************************/
//static void cityopt_callback(city_dialog *ppdialog)
//{
//  city_dialog pdialog = *ppdialog;
//  city pcity = pdialog.pcity;
//
//  if (pcity)
//  {
//    struct packet_generic_values packet;
//    int i, new_options, newcitizen_index = xget(pdialog.misc_radio, MUIA_Radio_Active);
//
//    new_options = 0;
//    for (i = 0; i < NUM_CITY_OPTS; i++)
//    {
//      if (xget(pdialog.misc_checks[i], MUIA_Selected))
//	new_options |= (1 << i);
//    }
//
//    if (newcitizen_index == 1)  new_options |= (1 << CITYO_NEW_EINSTEIN);
//    else if (newcitizen_index == 2) new_options |= (1 << CITYO_NEW_TAXMAN);
//
//    packet.value1 = pcity.id;
//    packet.value2 = new_options;
//    send_packet_generic_values(&aconnection, PACKET_CITY_OPTIONS,
//			       &packet);
//  }
//}
//
///**************************************************************************
// Callback to set the next page
//**************************************************************************/
//static void misc_next_callback(city_dialog *ppdialog)
//{
//  city_dialog pdialog = *ppdialog;
//  new_dialog_def_page = xget(pdialog.misc_next_radio,MUIA_Radio_Active);
//}
//
///****************************************************************
// Callback for the City Name String
//*****************************************************************/
//static void city_rename_callback(city_dialog *ppdialog)
//{
//  city_dialog pdialog = *ppdialog;
//  char *name = (char*)xget(pdialog.name_transparentstring,
//			   MUIA_TransparentString_Contents);
//
//  city_rename(pdialog.pcity, name);
//}
//
///**************************************************************************
// Callback for the List button
//**************************************************************************/
//static void city_unitlist(city_dialog *ppdialog)
//{
//  city_dialog pdialog = *ppdialog;
//  tile ptile = map_get_tile(pdialog.pcity.tile);
//
//  if (ptile.units.foo_list_size())
//    popup_unit_select_dialog(ptile);
//}
//
///**************************************************************************
// Callback for the Activate All button
//**************************************************************************/
//static void city_activate_units(city_dialog *ppdialog)
//{
//  city_dialog pdialog = *ppdialog;
//
//  activate_all_units(pdialog.pcity.tile);
//}
//
///**************************************************************************
// Callback for the Change button
//**************************************************************************/
//static void city_change(city_dialog *ppdialog)
//{
//  popup_city_production_dialog((*ppdialog).pcity);
//}
//
///****************************************************************
//  Commit the changes to the worklist for the city.
//*****************************************************************/
//static void commit_city_worklist(worklist pwl, void *data)
//{
//  city_dialog pdialog = data;
//  int k, id;
//  boolean is_unit;
//
//  /* Update the worklist.  Remember, though -- the current build
//     target really isn't in the worklist; don't send it to the server
//     as part of the worklist.  Of course, we have to search through
//     the current worklist to find the first _now_available_ build
//     target (to cope with players who try mean things like adding a
//     Battleship to a city worklist when the player doesn't even yet
//     have the Map Making tech).  */
//
//  for (k = 0; k < MAX_LEN_WORKLIST; k++) {
//    int same_as_current_build;
//    if (!worklist_peek_ith(pwl, &id, &is_unit, k))
//      break;
//
//    same_as_current_build = id == pdialog.pcity.currently_building
//	&& is_unit == pdialog.pcity.is_building_unit;
//
//    /* Very special case: If we are currently building a wonder we
//       allow the finalruction to continue, even if we the wonder is
//       finished elsewhere, ie unbuildable. */
//    if (k == 0 && !is_unit && Improvement.is_wonder(id) && same_as_current_build) {
//      worklist_remove(pwl, k);
//      break;
//    }
//
//    /* If it can be built... */
//    if ((is_unit && City.can_build_unit(pdialog.pcity, id)) ||
//	(!is_unit && City.can_build_improvement(pdialog.pcity, id))) {
//      /* ...but we're not yet building it, then switch. */
//      if (!same_as_current_build) {
//	/* Change the current target */
//	city_change_production(pdialog.pcity, is_unit, id);
//      }
//
//      /* This item is now (and may have always been) the current
//         build target.  Drop it out of the worklist. */
//      worklist_remove(pwl, k);
//      break;
//    }
//  }
//
//  /* Send the rest of the worklist on its way. */
//  city_set_worklist(pdialog.pcity, pwl);
//}
//
///****************************************************************
//...
//*****************************************************************/
//static void cancel_city_worklist(void *data)
//{
//  city_dialog pdialog = (city_dialog )data;
//}
//
///**************************************************************************
// Callback for the browse buttons (switch the city which is display to
// the next or previous. Alphabetically)
//**************************************************************************/
//static void city_browse(city_browse_msg msg)
//{
//  player pplayer = get_player(msg.pdialog.pcity.owner);
//  city pcity_new;
//  struct MinList list;
//  city_node node;
//  NewList((List ) &list);
//
//  for(city pcity : pplayer.cities.data){//  {
//    city_node node = malloc_struct(struct city_node);
//    if (node)
//    {
//      node.pcity = pcity;
//      AddTail((List ) &list, (Node ) node);
//    }
//  }
//  city_list_iterate_end
//
//    city_list_sort_amiga(&list);
//
//  node = (city_node ) List_First(&list);
//  while (node)
//  {
//    if (node.pcity == msg.pdialog.pcity)
//      break;
//    node = (city_node ) Node_Next(node);
//  }
//
//  if (node)
//  {
//    if (msg.direction)
//    {
//      node = (city_node ) Node_Next(node);
//      if (!node)
//	node = (city_node ) List_First(&list);
//    }
//    else
//    {
//      node = (city_node ) Node_Prev(node);
//      if (!node)
//	node = (city_node ) List_Last(&list);
//    }
//  }
//
//  if (node)
//  {
//    if ((pcity_new = node.pcity))
//    {
//      msg.pdialog.pcity = pcity_new;
//      msg.pdialog.sell_id = -1;
//      if (msg.pdialog.sell_wnd)
//      {
//        destroy_message_dialog(msg.pdialog.sell_wnd);
//        msg.pdialog.sell_wnd = null;
//      }
//
//      set(msg.pdialog.map_area, MUIA_CityMap_City, pcity_new);
//      set(msg.pdialog.happines_map, MUIA_CityMap_City, pcity_new);
//      refresh_this_city_dialog(msg.pdialog);
//    }
//  }
//}
//
///**************************************************************************
// Callback for the Buy button
//**************************************************************************/
//static void city_buy(city_dialog *ppdialog)
//{
//  city_dialog pdialog = *ppdialog;
//  int value;
//  char *name;
//  char buf[512];
//
//  if (pdialog.pcity.is_building_unit)
//  {
//    name = get_unit_type(pdialog.pcity.currently_building).name;
//  }
//  else
//  {
//    name = City.get_impr_name_ex(pdialog.pcity, pdialog.pcity.currently_building);
//  }
//
//  value = City.city_buy_cost(pdialog.pcity);
//
//  if (Game.game.player_ptr.economic.gold >= value)
//  {
//    buf = util.my_snprintf( "Buy %s for %d gold?\nTreasury contains %d gold.",
//	    name, value, Game.game.player_ptr.economic.gold);
//
//    popup_message_dialog(pdialog.wnd, "Buy It!", buf,
//			 "_Yes", city_buy_yes, pdialog.pcity,
//			 "_No", message_close, 0,
//			 null);
//  }
//  else
//  {
//    buf = util.my_snprintf( "%s costs %d gold.\nTreasury contains %d gold.",
//	    name, value, Game.game.player_ptr.economic.gold);
//
//    popup_message_dialog(pdialog.wnd, "Buy It!", buf,
//			 "_Darn", message_close, 0,
//			 null);
//  }
//}
//
///**************************************************************************
// Callback for the Sell button
//**************************************************************************/
//static void city_sell(city_dialog *ppdialog)
//{
//  city_dialog pdialog = *ppdialog;
//  LONG sel = xget(pdialog.imprv_listview, MUIA_NList_Active);
//  if (sel >= 0 && !pdialog.sell_wnd)
//  {
//    LONG i = 0;
//    char buf[512];
//    DoMethod(pdialog.imprv_listview, MUIM_NList_GetEntry, sel, &i);
//
//    if (!i--)
//      return;
//
//    if (Improvement.is_wonder(i))
//      return;
//
//    buf = util.my_snprintf( "Sell %s for %d gold?",
//		City.get_impr_name_ex(pdialog.pcity, i), Improvement.impr_sell_gold(i));
//
//    pdialog.sell_id = i;
//    pdialog.sell_wnd = popup_message_dialog(pdialog.wnd,
//			"Sell It!", buf,
//			"_Yes", city_sell_yes, pdialog,
//			"_No", city_sell_no, pdialog,
//			null);
//    set(pdialog.sell_button, MUIA_Disabled, true);
//  }
//}
//
///**************************************************************************
// Callback if the user clicked on a map
//**************************************************************************/
//static void city_click(city_map_msg msg)
//{
//  city pcity = msg.pdialog.pcity;
//  int xtile = msg.click.x;
//  int ytile = msg.click.y;
//
//  if (City.City.is_valid_city_coords(xtile, ytile)) {
//    city_toggle_worker(pcity, xtile, ytile);
//  }
//}
//
///**************************************************************************
// Callback if the user clicked on a citizen
//**************************************************************************/
//static void city_citizen(city_citizen_msg msg)
//{
//  city_dialog pdialog = msg.pdialog;
//  specialist_type from = msg.type, to;
//
//  switch (from)  {
//  case CITIZEN_ELVIS:
//    to = specialist_type.SP_SCIENTIST;
//    break;
//
//  case CITIZEN_SCIENTIST:
//    to = specialist_type.SP_TAXMAN;
//    break;
//
//  case CITIZEN_TAXMAN:
//    to = specialist_type.SP_ELVIST;
//    break;
//
//  default:
//    assert(false);
//    return;
//  }
//
//  city_change_specialist(pdialog.pcity, from, to);
//}
//
///**************************************************************************
// Callback if the user clicked on a present unit
//**************************************************************************/
//static void city_present(city_unit_msg data)
//{
//  set_unit_focus_and_select(data.punit);
//}
//
///****************************************************************
// Must be called from the Application object so it is safe to
// dispose the window
//*****************************************************************/
//static void city_prod_close_real(city_prod *ppcprod)
//{
//  set((*ppcprod).wnd,MUIA_Window_Open,false);
//  DoMethod(app, OM_REMMEMBER, (*ppcprod).wnd);
//  MUI_DisposeObject((*ppcprod).wnd);
//  FreeVec(*ppcprod);
//}
//
///****************************************************************
// city_prod_destroy destroy the object after use
//*****************************************************************/
//static void city_prod_destroy(city_prod *ppcprod)
//{
//  set((*ppcprod).wnd, MUIA_Window_Open, false);
//  DoMethod(app, MUIM_Application_PushMethod, app, 4, MUIM_CallHook, &civstandard_hook, city_prod_close_real, *ppcprod);
//}
//
///**************************************************************************
// Callback for the Change button in the production window
//**************************************************************************/
//static void city_prod_change(city_prod *ppcprod)
//{
//  city_prod pcprod = *ppcprod;
//  LONG sel = xget(pcprod.available_listview, MUIA_NList_Active);
//  if (sel >= 0)
//  {
//    LONG which = 0;
//    LONG is_unit = 0;
//
//    DoMethod(pcprod.available_listview, MUIM_NList_GetEntry, sel, &which);
//    if (which >= 10000)
//    {
//      if (which == 20000 || which == 20001)
//	return;
//      which -= 10000;
//      is_unit = 1;
//    }
//    else
//      which--;
//
//    city_change_production(pcprod.pcity, is_unit, which);
//    city_prod_destroy(ppcprod);
//  }
//}
//
///**************************************************************************
// Callback for the Help button in the production window
//**************************************************************************/
//static void city_prod_help(city_prod *ppcprod)
//{
//  city_prod pcprod = *ppcprod;
//  LONG sel = xget(pcprod.available_listview, MUIA_NList_Active);
//  if (sel >= 0)
//  {
//    LONG which = 0;
//
//    DoMethod(pcprod.available_listview, MUIM_NList_GetEntry, sel, &which);
//    if (which >= 10000)
//    {
//      which -= 10000;
//      popup_help_dialog_typed(get_unit_type(which).name, HELP_UNIT);
//    }
//    else
//    {
//      which--;
//      if (Improvement.is_wonder(which))
//      {
//	popup_help_dialog_typed(Improvement.get_improvement_name(which), HELP_WONDER);
//      }
//      else
//      {
//	popup_help_dialog_typed(Improvement.get_improvement_name(which), HELP_IMPROVEMENT);
//      }
//    }
//  }
//}
//
///************************************************************************
// The HScales within the CMA has been changed
//*************************************************************************/
//static void city_cma_changed(city_dialog *ppdialog)
//{
//  city_dialog pdialog = *ppdialog; 
//  struct cm_parameter param;
//  int i;
//
//  cmafec_get_fe_parameter(pdialog.pcity, &param);
//  for (i = 0; i < NUM_STATS; i++) {
//    param.minimal_surplus[i] = (int)xget(pdialog.minimal_surplus_slider[i],MUIA_Numeric_Value);
//    param.factor[i] = (int)xget(pdialog.factor_slider[i],MUIA_Numeric_Value);
//  }
//  param.require_happy = xget(pdialog.celebrate_check, MUIA_Selected);
//  param.happy_factor = xget(pdialog.factor_slider[6],MUIA_Numeric_Value);
//
//  /* save the change */
//  cmafec_set_fe_parameter(pdialog.pcity, &param);
//
//  /* refreshes the cma */
//  if (cma_is_city_under_agent(pdialog.pcity, null)) {
//    cma_release_city(pdialog.pcity);
//    cma_put_city_under_agent(pdialog.pcity, &param);
//
//    /* unfog the city map if we were unable to put back under */
//    if (!cma_is_city_under_agent(pdialog.pcity, null)) {
//      refresh_city_dialog(pdialog.pcity);
//      return;			/* refreshing city refreshes cma */
//    } else {
//      city_report_dialog_update_city(pdialog.pcity);
//    }
//  }
//  refresh_cma_dialog(pdialog);
//}
//
///************************************************************************
//...
//*************************************************************************/
//static void city_cma_change(city_dialog *ppdialog)
//{
//  city_dialog pdialog = *ppdialog; 
//  struct cm_result result;
//  struct cm_parameter param;
//
//  cmafec_get_fe_parameter(pdialog.pcity, &param);
//  cm_query_result(pdialog.pcity, &param, &result);
//  cma_apply_result(pdialog.pcity, &result);
//}
//
///************************************************************************
//...
//*************************************************************************/
//static void city_cma_permanent(city_dialog *ppdialog)
//{
//  city_dialog pdialog = *ppdialog;
//  struct cm_parameter param;
//
//  cmafec_get_fe_parameter(pdialog.pcity, &param);
//  cma_put_city_under_agent(pdialog.pcity, &param);
//  refresh_city_dialog(pdialog.pcity);
//}
//
///************************************************************************
//...
//*************************************************************************/
//static void city_cma_release(city_dialog *ppdialog)
//{
//  city_dialog pdialog = *ppdialog; 
//
//  cma_release_city(pdialog.pcity);
//  refresh_city_dialog(pdialog.pcity);
//}
//
///**************************************************************************
// Allocate and initialize a new city production dialog
//**************************************************************************/
//void popup_city_production_dialog(city pcity)
//{
//  city_prod pcprod;
//  Object *change_button;
//  Object *help_button;
//  Object *cancel_button;
//
//  if(!(pcprod = (city_prod ) AllocVec(sizeof(struct city_prod), MEMF_CLEAR)))
//    return;
//
//  pcprod.pcity = pcity;
//  pcprod.available_disphook.h_Entry = (HOOKFUNC) city_prod_display;
//  pcprod.available_disphook.h_Data = pcity;
//
//  pcprod.wnd = WindowObject,
//    MUIA_Window_Title, "Freeciv - Cityproduction",
//    MUIA_Window_ID, MAKE_ID('P','R','O','D'),
//    WindowContents, VGroup,
//	Child, pcprod.available_listview = NListviewObject,
//	    MUIA_CycleChain, 1,
//	    MUIA_NListview_NList, NListObject,
//		MUIA_NList_DisplayHook, &pcprod.available_disphook,
//		MUIA_NList_Format, "BAR,P=\33c BAR,P=\33c BAR,P=\33r NOBAR,",
//		MUIA_NList_Title, true,
//		MUIA_NList_AutoVisible, true,
//		End,
//	    End,
//	Child, HGroup,
//	    Child, change_button = MakeButton("Chan_ge"),
//	    Child, help_button = MakeButton("_Help"),
//	    Child, cancel_button = MakeButton("_Cancel"),
//	    End,
//	End,
//    End;
//
//  if(pcprod.wnd)
//  {
//    int i, pos = 0, current = -1, improv = 0;
//
//    set(pcprod.available_listview, MUIA_NList_Quiet, true);
//
//    DoMethod(pcprod.available_listview, MUIM_NList_Clear);
//
//    for (int i = 0; i < Game.game.num_impr_types; i++) {
//      if (City.can_build_improvement(pcity, i))
//      {
//        improv = true;
//
//        DoMethod(pcprod.available_listview, MUIM_NList_InsertSingle, i + 1, MUIV_NList_Insert_Bottom);
//
//        if (i == pcity.currently_building && !pcity.is_building_unit)
//         current = pos++;
//
//        pos++;
//      }
//    } ;
//
//    if (improv)
//    {
//      DoMethod(pcprod.available_listview, MUIM_NList_InsertSingle, 20001, MUIV_NList_Insert_Top);
//      if (current == -1)
//        pos++;
//    }
//    DoMethod(pcprod.available_listview, MUIM_NList_InsertSingle, 20000, MUIV_NList_Insert_Bottom);
//
//    unit_type_iterate(i) {
//      if (City.can_build_unit(pcity, i))
//      {
//        DoMethod(pcprod.available_listview, MUIM_NList_InsertSingle, i + 10000, MUIV_NList_Insert_Bottom);
//
//        if(i == pcity.currently_building && pcity.is_building_unit)
//         current = pos++;
//
//        pos++;
//      }
//    } unit_type_iterate_end;
//
//    set(pcprod.available_listview, MUIA_NList_Quiet, false);
//
//    if(current != -1)
//    {
//      set(pcprod.available_listview, MUIA_NList_Active, current);
//    }
//
//    DoMethod(pcprod.wnd, MUIM_Notify, MUIA_Window_CloseRequest, true, app, 4, MUIM_CallHook, &civstandard_hook, city_prod_destroy, pcprod);
//    DoMethod(cancel_button, MUIM_Notify, MUIA_Pressed, false, MUIV_Notify_Self, 4, MUIM_CallHook, &civstandard_hook, city_prod_destroy, pcprod);
//    DoMethod(pcprod.available_listview, MUIM_Notify, MUIA_NList_DoubleClick, true, MUIV_Notify_Self, 4, MUIM_CallHook, &civstandard_hook, city_prod_change, pcprod);
//    DoMethod(change_button, MUIM_Notify, MUIA_Pressed, false, MUIV_Notify_Self, 4, MUIM_CallHook, &civstandard_hook, city_prod_change, pcprod);
//    DoMethod(help_button, MUIM_Notify, MUIA_Pressed, false, MUIV_Notify_Self, 4, MUIM_CallHook, &civstandard_hook, city_prod_help, pcprod);
//
//    DoMethod(app,OM_ADDMEMBER,pcprod.wnd);
//    SetAttrs(pcprod.wnd, MUIA_Window_Open, true, TAG_DONE);
//  }
//  else
//    FreeVec(pcprod);
//}
//
///**************************************************************************
// Creates the City Info Objects
//**************************************************************************/
//static Object *create_city_info(city_info info)
//{
//  info.group = ColGroup(2),
//	Child, TextObject, MUIA_Font, MUIV_Font_Tiny, MUIA_Text_Contents, "Food:", End,
//	Child, info.food_text = TextObject, MUIA_Font, MUIV_Font_Tiny, End,
//	Child, TextObject, MUIA_Font, MUIV_Font_Tiny, MUIA_Text_Contents, "Shields", End,
//	Child, info.shield_text = TextObject, MUIA_Font, MUIV_Font_Tiny, End,
//	Child, TextObject, MUIA_Font, MUIV_Font_Tiny, MUIA_Text_Contents, "Trade:", End,
//	Child, info.trade_text = TextObject, MUIA_Font, MUIV_Font_Tiny, End,
//	Child, VSpace(1), Child, VSpace(1),
//	Child, TextObject, MUIA_Font, MUIV_Font_Tiny, MUIA_Text_Contents, "Gold:", End,
//	Child, info.gold_text = TextObject, MUIA_Font, MUIV_Font_Tiny, End,
//	Child, TextObject, MUIA_Font, MUIV_Font_Tiny, MUIA_Text_Contents, "Luxury:", End,
//	Child, info.luxury_text = TextObject, MUIA_Font, MUIV_Font_Tiny, End,
//	Child, TextObject, MUIA_Font, MUIV_Font_Tiny, MUIA_Text_Contents, "Science:", End,
//	Child, info.science_text = TextObject, MUIA_Font, MUIV_Font_Tiny, End,
//	Child, VSpace(1), Child, VSpace(1),
//	Child, TextObject, MUIA_Font, MUIV_Font_Tiny, MUIA_Text_Contents, "Granary:", End,
//	Child, info.granary_text = TextObject, MUIA_Font, MUIV_Font_Tiny, End,
//	Child, TextObject, MUIA_Font, MUIV_Font_Tiny, MUIA_Text_Contents, "Change in:", End,
//	Child, info.growth_text = TextObject, MUIA_Font, MUIV_Font_Tiny, End,
//	Child, VSpace(1), Child, VSpace(1),
//	Child, TextObject, MUIA_Font, MUIV_Font_Tiny, MUIA_Text_Contents, "Corruption:", End,
//	Child, info.corruption_text = TextObject, MUIA_Font, MUIV_Font_Tiny, End,
//	Child, TextObject, MUIA_Font, MUIV_Font_Tiny, MUIA_Text_Contents, "Waste:", End,
//	Child, info.waste_text = TextObject, MUIA_Font, MUIV_Font_Tiny, End,
//	Child, TextObject, MUIA_Font, MUIV_Font_Tiny, MUIA_Text_Contents, "Pollution:", End,
//	Child, info.pollution_text = TextObject, MUIA_Font, MUIV_Font_Tiny, End,
//	End;
//
//  return info.group;
//}
//
///**************************************************************************
// Allocate and initialize a new city dialog
//**************************************************************************/
//static city_dialog create_city_dialog(city pcity)
//{
//  int i;
//  city_dialog pdialog;
//  static char *newcitizen_labels[4];
//  static char *page_labels[NUM_PAGES];
//  static char *misc_whichtab_label[NUM_PAGES+1];
//
//  Object *next_button, *prev_button;
//
//  newcitizen_labels[0] = "Workers";
//  newcitizen_labels[1] = "Scientists";
//  newcitizen_labels[2] = "Taxmen";
//  newcitizen_labels[3] = null;
//
//  page_labels[0] = "City Overview";
//  page_labels[1] = "Units";
//  page_labels[2] = "Worklist";
//  page_labels[3] = "Happiness";
//  page_labels[4] = "CMA";
//  page_labels[5] = "Trade Routes";
//  page_labels[6] = "Misc. Settings";
//
//  misc_whichtab_label[0] = "City Overview page";
//  misc_whichtab_label[1] = "Units page";
//  misc_whichtab_label[2] = "Worklist page";
//  misc_whichtab_label[3] = "Happiness page";
//  misc_whichtab_label[4] = "CMA page";
//  misc_whichtab_label[5] = "Trade Routes page";
//  misc_whichtab_label[6] = "This Misc. Settings page";
//  misc_whichtab_label[7] = "Last active page";
//
//  pdialog = AllocVec(sizeof(struct city_dialog), 0x10000);
//  if (!pdialog)
//    return null;
//
//  pdialog.pcity = pcity;
//
//  pdialog.imprv_disphook.h_Entry = (HOOKFUNC) city_imprv_display;
//  pdialog.imprv_disphook.h_Data = pdialog;
//
//  if (pcity.owner == Game.game.player_idx)
//  {
//    prev_button = MakeButton("_<");
//    next_button = MakeButton("_>");
//    pdialog.name_transparentstring = TransparentStringObject,
//	MUIA_CycleChain,1,
//	End;
//  } else prev_button = next_button = pdialog.name_transparentstring = null;
//
//  pdialog.wnd = WindowObject,
//    MUIA_Window_Title, "Freeciv - Cityview",
//    MUIA_Window_ID, MAKE_ID('C','I','T','Y'),
//    WindowContents, VGroup,
//      Child, HGroup,
//	Child, HVSpace,
//	prev_button?Child:TAG_IGNORE, prev_button,
//	Child, VGroup,
//	  MUIA_Weight, 200,
//	  pdialog.name_transparentstring ? Child:TAG_IGNORE, pdialog.name_transparentstring,
//	  Child, pdialog.title_text = TextObject,
//	    MUIA_Text_PreParse, "\033c",
//            End,
//	  End,
//	next_button?Child:TAG_IGNORE, next_button,
//	Child, HVSpace,
//	End,
//      Child, pdialog.citizen_group = HGroup,
//	Child, pdialog.citizen_left_space = HSpace(0),
//	Child, pdialog.citizen_right_space = HSpace(0),
//	End,
//
//      Child, pdialog.register_group = RegisterGroup(page_labels),
//      	MUIA_CycleChain,1,
//	Child, HGroup, /* City Overview */
//	  Child, VGroup,
//	    Child, HGroup,
//	      Child, VGroup,
//		Child, HorizLineTextObject("City Output"),
//		Child, create_city_info(&pdialog.overview_city_info),
//		Child, HVSpace,
//		End,
//	      Child, VGroup,
//		Child, HorizLineTextObject("Citymap"),
//		Child, HVSpace,
//		Child, pdialog.map_area = MakeCityMap(pcity),
//		Child, HVSpace,
//		End,
//	      End,
//
//	    Child, HorizLineTextObject("Units present"),
//	    Child, HGroup,
//	      Child, pdialog.present_group = AutoGroup,
//		MUIA_AutoGroup_DefVertObjects, 3,
//		End,
//              End,
//
//	    Child, HorizLineTextObject("Supported Units"),
//	    Child, pdialog.supported_group = AutoGroup,
//	      MUIA_AutoGroup_DefVertObjects, 3,
//	      End,
//            End,
//
//	  Child, VGroup,
//            Child, HorizLineTextObject("Production"),
//	    Child, HGroup,
//              Child, pdialog.prod_gauge = MyGaugeObject,
//	        GaugeFrame,
//	        MUIA_Gauge_Horiz, true,
//	        End,
//	      Child, pdialog.buy_button = MakeButton("_Buy"),
//	      Child, pdialog.change_button = MakeButton("Chan_ge"),
//	      End,
//
//	    Child, HorizLineTextObject("City Improvements"),
//	    Child, pdialog.imprv_listview = NListviewObject,
//	      MUIA_CycleChain, 1,
//	      MUIA_NListview_NList, NListObject,
//	        MUIA_NList_DisplayHook, &pdialog.imprv_disphook,
//	        MUIA_NList_Format, ",P=\033c",
//	        MUIA_NList_Title, true,
//	        End,
//	      End,
//	    Child, pdialog.sell_button = MakeButton("_Sell"),
//	    End,
//	  End,
///*
//	      Child, HVSpace,
//	      Child, pdialog.activateunits_button = MakeButton("_Activate Units"),
//	      Child, HVSpace,
//	      Child, pdialog.unitlist_button = MakeButton("_Unit List"),
//	      Child, HVSpace,
//*/
//
//        /* Units */
//	Child, VGroup,
//	    Child, HorizLineTextObject("Supported Units"),
//	    Child, pdialog.units_supported_group = AutoGroup, End,
//	    Child, HorizLineTextObject("Units present"),
//	    Child, pdialog.units_present_group = AutoGroup, End,
//	    End,
//
//        /* Worklist */
//	Child, pdialog.worklist_wl = WorklistObject,
//	    MUIA_Worklist_Worklist, &pdialog.pcity.worklist,
//	    MUIA_Worklist_City, pdialog.pcity,
//	    MUIA_Worklist_PatentData, pdialog,
//	    MUIA_Worklist_OkCallBack, commit_city_worklist,
//	    MUIA_Worklist_Embedded, true,
//	    End,
//
//        /* Happiness */
//	Child, VGroup,
//	  Child, HGroup,
//	    Child, VGroup,
//	      Child, HorizLineTextObject("Happiness"),
//	      Child, pdialog.happiness_citizen_group[0] = HGroup,Child,HVSpace,End,
//	      Child, pdialog.happiness_citizen_text[0] = TextObject,End,
//
//	      Child, pdialog.happiness_citizen_group[1] = HGroup,Child,HVSpace,End,
//	      Child, pdialog.happiness_citizen_text[1] = TextObject,End,
//
//	      Child, pdialog.happiness_citizen_group[2] = HGroup,Child,HVSpace,End,
//	      Child, pdialog.happiness_citizen_text[2] = TextObject,End,
//
//	      Child, pdialog.happiness_citizen_group[3] = HGroup,Child,HVSpace,End,
//	      Child, pdialog.happiness_citizen_text[3] = TextObject,End,
//
//	      Child, pdialog.happiness_citizen_group[4] = HGroup,Child,HVSpace,End,
//	      Child, pdialog.happiness_citizen_text[4] = TextObject,End,
//
//	      Child, HVSpace,
//	      End,
//	    Child, VGroup,
//	      Child, VSpace(10),
//	      Child, pdialog.happines_map = MakeCityMap(pcity),
//	      Child, HGroup,
//	        Child, HVSpace,
//	        Child, create_city_info(&pdialog.happines_city_info),
//	        Child, HVSpace,
//	        End,
//	      End,
//	    End,
//	  Child, HVSpace,
//	  End,
//	Child, VGroup, /* CMA Citizen Management Agent */
//
//	  Child, HGroup,
//	    Child, VGroup,
//	      Child, NListviewObject,
//	        MUIA_NListview_NList, NListObject,
//	          End,
//	        End,
//	      Child, HGroup,
//	        Child, MakeButton("_Add preset"),
//	        Child, MakeButton("_Delete preset"),
//	        End,
//	      End,
//	    Child, VGroup, /* Resultss and settings */
//	      Child, HorizLineTextObject("Results"),
//	      Child, HGroup,
//		Child, TextObject, MUIA_Font, MUIV_Font_Tiny, MUIA_Text_Contents,"Name:", MUIA_Weight, 0, End,
//		Child, pdialog.result_text[0] = TextObject, MUIA_Font, MUIV_Font_Tiny, End,
//		End,
//	      Child, VSpace(0),
//	      Child, ColGroup(4),
//		Child, TextObject, MUIA_Font, MUIV_Font_Tiny, MUIA_Text_Contents,"Food:", MUIA_Weight, 0, End,
//		Child, pdialog.result_text[1] = TextObject, MUIA_Font, MUIV_Font_Tiny, End,
//
//		Child, TextObject, MUIA_Font, MUIV_Font_Tiny, MUIA_Text_Contents,"Gold:", MUIA_Weight, 0, End,
//		Child, pdialog.result_text[4] = TextObject, MUIA_Font, MUIV_Font_Tiny, End,
//
//		Child, TextObject, MUIA_Font, MUIV_Font_Tiny, MUIA_Text_Contents,"Production:", MUIA_Weight, 0, End,
//		Child, pdialog.result_text[2] = TextObject, MUIA_Font, MUIV_Font_Tiny, End,
//
//		Child, TextObject, MUIA_Font, MUIV_Font_Tiny, MUIA_Text_Contents,"Luxury:", MUIA_Weight, 0, End,
//		Child, pdialog.result_text[5] = TextObject, MUIA_Font, MUIV_Font_Tiny, End,
//
//		Child, TextObject, MUIA_Font, MUIV_Font_Tiny, MUIA_Text_Contents,"Trade:", MUIA_Weight, 0, End,
//		Child, pdialog.result_text[3] = TextObject, MUIA_Font, MUIV_Font_Tiny, End,
//
//		Child, TextObject, MUIA_Font, MUIV_Font_Tiny, MUIA_Text_Contents,"Science:", MUIA_Weight, 0, End,
//		Child, pdialog.result_text[6] = TextObject, MUIA_Font, MUIV_Font_Tiny, End,
//	        End,
//	      Child, VSpace(0),
//	      Child, ColGroup(2),
//		Child, TextObject, MUIA_Font, MUIV_Font_Tiny, MUIA_Text_Contents,"People (W/E/S/T):", MUIA_Weight, 0, End,
//		Child, pdialog.result_text[7] = TextObject, MUIA_Font, MUIV_Font_Tiny, End,
//
//		Child, TextObject, MUIA_Font, MUIV_Font_Tiny, MUIA_Text_Contents,"City grows:", MUIA_Weight, 0, End,
//		Child, pdialog.result_text[8] = TextObject, MUIA_Font, MUIV_Font_Tiny, End,
//
//		Child, TextObject, MUIA_Font, MUIV_Font_Tiny, MUIA_Text_Contents,"Production completed:", MUIA_Weight, 0, End,
//		Child, pdialog.result_text[9] = TextObject, MUIA_Font, MUIV_Font_Tiny, End,
//	        End,
//	      Child, HorizLineObject,
//	      Child, ColGroup(3),
//	        Child, VSpace(0),
//	        Child, TextObject, MUIA_Text_Contents,"Minimal Surplus", MUIA_Text_PreParse, "\33c", End,
//	        Child, TextObject, MUIA_Text_Contents,"Factor", MUIA_Text_PreParse, "\33c", End,
//
//		Child, MakeLabel("Food"),
//		Child, pdialog.minimal_surplus_slider[0] = SliderObject, MUIA_Numeric_Min,-20, MUIA_Numeric_Max,20, End,
//		Child, pdialog.factor_slider[0] = SliderObject, MUIA_Numeric_Min,1, MUIA_Numeric_Max,25,End,
//
//		Child, MakeLabel("Shield"),
//		Child, pdialog.minimal_surplus_slider[1] = SliderObject, MUIA_Numeric_Min,-20, MUIA_Numeric_Max,20, End,
//		Child, pdialog.factor_slider[1] = SliderObject, MUIA_Numeric_Min,1, MUIA_Numeric_Max,25,End,
//
//		Child, MakeLabel("Trade"),
//		Child, pdialog.minimal_surplus_slider[2] = SliderObject, MUIA_Numeric_Min,-20, MUIA_Numeric_Max,20, End,
//		Child, pdialog.factor_slider[2] = SliderObject, MUIA_Numeric_Min,1, MUIA_Numeric_Max,25,End,
//
//		Child, MakeLabel("Gold"),
//		Child, pdialog.minimal_surplus_slider[3] = SliderObject, MUIA_Numeric_Min,-20, MUIA_Numeric_Max,20, End,
//		Child, pdialog.factor_slider[3] = SliderObject, MUIA_Numeric_Min,1, MUIA_Numeric_Max,25,End,
//
//		Child, MakeLabel("Luxury"),
//		Child, pdialog.minimal_surplus_slider[4] = SliderObject, MUIA_Numeric_Min,-20, MUIA_Numeric_Max,20, End,
//		Child, pdialog.factor_slider[4] = SliderObject, MUIA_Numeric_Min,1, MUIA_Numeric_Max,25,End,
//
//		Child, MakeLabel("Science"),
//		Child, pdialog.minimal_surplus_slider[5] = SliderObject, MUIA_Numeric_Min,-20, MUIA_Numeric_Max,20, End,
//		Child, pdialog.factor_slider[5] = SliderObject, MUIA_Numeric_Min,1, MUIA_Numeric_Max,25,End,
//
//		Child, MakeLabel("Celebrate"),
//		Child, pdialog.celebrate_check = MakeCheck("Celebrate",false),
//		Child, pdialog.factor_slider[6] = SliderObject, MUIA_Numeric_Min,1, MUIA_Numeric_Max,25,End,
//
//	        End,
//
//	      Child, HVSpace,
//	      Child, HGroup,
//		Child, pdialog.cma_change_button = MakeButton("_Change"),
//		Child, pdialog.cma_perm_button = MakeButton("C_hange permanent"),
//		Child, pdialog.cma_release_button = MakeButton("_Release city"),
//		End,
//	      End,
//	    End,
//	  End,
//
//	Child, pdialog.trade_text = TextObject, MUIA_Text_PreParse, "\33c", MUIA_Text_SetVMax, false,End,  /* Trade Route */
//
//        /* Misc settings */
//	Child, HGroup,
//	    Child, HVSpace,
//	    Child, VGroup,
//		Child, HorizLineTextObject("New citizens are"),
//		Child, pdialog.misc_radio = MUI_MakeObject(MUIO_Radio, null, newcitizen_labels),
//		Child, HorizLineTextObject("Auto attack vs"),
//		Child, ColGroup(4),
//		    Child, MakeLabelLeft("Land units"),
//		    Child, pdialog.misc_checks[0] = MakeCheck("Land units", false),
//
//		    Child, MakeLabelLeft("Sea units"),
//		    Child, pdialog.misc_checks[1] = MakeCheck("Sea units", false),
//
//		    Child, MakeLabelLeft("Helicopters"),
//		    Child, pdialog.misc_checks[2] = MakeCheck("Helicopters", false),
//
//		    Child, MakeLabelLeft("Air units"),
//		    Child, pdialog.misc_checks[3] = MakeCheck("Air units", false),
//		    End,
//	        Child, HorizLineObject,
//	        Child, HGroup,
//		    Child, MakeLabelLeft("_Disband if build settler at size 1"),
//		    Child, pdialog.misc_checks[4] = MakeCheck("_Disband if build settler at size 1", false),
//		    End,
//	        Child, HVSpace, 0,
//	        End,
//	    Child, HVSpace,
//	    Child, VGroup,
//	    	Child, HorizLineTextObject("Next time open"),
//	    	Child, pdialog.misc_next_radio = MUI_MakeObject(MUIO_Radio, null, misc_whichtab_label),
//	    	Child, HVSpace,
//	    	End,
//	    Child, HVSpace,
//	    End,
//	End,
//      Child, VGroup,
//	Child, HorizLineObject,
//	Child, HGroup,
//	  Child, HSpace(0),
//	  Child, pdialog.close_button = MakeButton("_Close"),
//	  End,
//        End,
//      End,
//    End;
//
//  if(pdialog.wnd)
//  {
//    DoMethod(pdialog.wnd, MUIM_Notify, MUIA_Window_CloseRequest, true, app, 4, MUIM_CallHook, &civstandard_hook, city_close, pdialog);
//    DoMethod(pdialog.close_button, MUIM_Notify, MUIA_Pressed, false, app, 4, MUIM_CallHook, &civstandard_hook, city_close, pdialog);
//    DoMethod(pdialog.unitlist_button, MUIM_Notify, MUIA_Pressed, false, app, 4, MUIM_CallHook, &civstandard_hook, city_unitlist, pdialog);
//    DoMethod(pdialog.activateunits_button, MUIM_Notify, MUIA_Pressed, false, app, 4, MUIM_CallHook, &civstandard_hook, city_activate_units, pdialog);
//
//    if (prev_button && next_button)
//    {
//      set(next_button, MUIA_Weight, 0);
//      set(prev_button, MUIA_Weight, 0);
//      DoMethod(prev_button, MUIM_Notify, MUIA_Pressed, false, app, 5, MUIM_CallHook, &civstandard_hook, city_browse, pdialog, 0);
//      DoMethod(next_button, MUIM_Notify, MUIA_Pressed, false, app, 5, MUIM_CallHook, &civstandard_hook, city_browse, pdialog, 1);
//    }
//
//    if (pdialog.name_transparentstring)
//    {
//      DoMethod(pdialog.name_transparentstring, MUIM_Notify,
//	       MUIA_TransparentString_Acknowledge, MUIV_EveryTime, app, 4,
//	       MUIM_CallHook, &civstandard_hook, city_rename_callback,
//	       pdialog);
//    }
//
//    for (i=0;i<6;i++)
//    {
//      DoMethod(pdialog.minimal_surplus_slider[i],MUIM_Notify,MUIA_Numeric_Value,MUIV_EveryTime, app, 4, MUIM_CallHook, &civstandard_hook, city_cma_changed, pdialog);
//      DoMethod(pdialog.factor_slider[i],MUIM_Notify,MUIA_Numeric_Value,MUIV_EveryTime, app, 4, MUIM_CallHook, &civstandard_hook, city_cma_changed, pdialog);
//    }
//    DoMethod(pdialog.celebrate_check,MUIM_Notify,MUIA_Selected,MUIV_EveryTime, app, 4, MUIM_CallHook, &civstandard_hook, city_cma_changed, pdialog);
//    DoMethod(pdialog.factor_slider[6],MUIM_Notify,MUIA_Numeric_Value,MUIV_EveryTime, app, 4, MUIM_CallHook, &civstandard_hook, city_cma_changed, pdialog);
//    DoMethod(pdialog.cma_change_button, MUIM_Notify, MUIA_Pressed, false, app, 4, MUIM_CallHook, &civstandard_hook, city_cma_change, pdialog);
//    DoMethod(pdialog.cma_perm_button, MUIM_Notify, MUIA_Pressed, false, app, 4, MUIM_CallHook, &civstandard_hook, city_cma_permanent, pdialog);
//    DoMethod(pdialog.cma_release_button, MUIM_Notify, MUIA_Pressed, false, app, 4, MUIM_CallHook, &civstandard_hook, city_cma_release, pdialog);
//
//    /* Misc notifies */
//    DoMethod(pdialog.misc_radio,MUIM_Notify,MUIA_Radio_Active, MUIV_EveryTime, app, 4, MUIM_CallHook, &civstandard_hook, cityopt_callback, pdialog);
//    DoMethod(pdialog.misc_checks[0],MUIM_Notify,MUIA_Selected, MUIV_EveryTime, app, 4, MUIM_CallHook, &civstandard_hook, cityopt_callback, pdialog);
//    DoMethod(pdialog.misc_checks[1],MUIM_Notify,MUIA_Selected, MUIV_EveryTime, app, 4, MUIM_CallHook, &civstandard_hook, cityopt_callback, pdialog);
//    DoMethod(pdialog.misc_checks[2],MUIM_Notify,MUIA_Selected, MUIV_EveryTime, app, 4, MUIM_CallHook, &civstandard_hook, cityopt_callback, pdialog);
//    DoMethod(pdialog.misc_checks[3],MUIM_Notify,MUIA_Selected, MUIV_EveryTime, app, 4, MUIM_CallHook, &civstandard_hook, cityopt_callback, pdialog);
//    DoMethod(pdialog.misc_checks[4],MUIM_Notify,MUIA_Selected, MUIV_EveryTime, app, 4, MUIM_CallHook, &civstandard_hook, cityopt_callback, pdialog);
//    DoMethod(pdialog.misc_next_radio, MUIM_Notify, MUIA_Radio_Active, MUIV_EveryTime, app, 4, MUIM_CallHook, &civstandard_hook, misc_next_callback, pdialog);
//
//    set(pdialog.buy_button, MUIA_Weight, 0);
//    set(pdialog.change_button, MUIA_Weight, 0);
//
//    DoMethod(pdialog.map_area, MUIM_Notify, MUIA_CityMap_Click, MUIV_EveryTime, app, 5, MUIM_CallHook, &civstandard_hook, city_click, pdialog, MUIV_TriggerValue);
//    DoMethod(pdialog.happines_map, MUIM_Notify, MUIA_CityMap_Click, MUIV_EveryTime, app, 5, MUIM_CallHook, &civstandard_hook, city_click, pdialog, MUIV_TriggerValue);
//    DoMethod(pdialog.change_button, MUIM_Notify, MUIA_Pressed, false, app, 4, MUIM_CallHook, &civstandard_hook, city_change, pdialog);
//    DoMethod(pdialog.buy_button, MUIM_Notify, MUIA_Pressed, false, app, 4, MUIM_CallHook, &civstandard_hook, city_buy, pdialog);
//    DoMethod(pdialog.sell_button, MUIM_Notify, MUIA_Pressed, false, app, 4, MUIM_CallHook, &civstandard_hook, city_sell, pdialog);
//
//    DoMethod(app, OM_ADDMEMBER, pdialog.wnd);
//
//    genlist_insert(&dialog_list, pdialog, 0);
//    refresh_city_dialog(pdialog.pcity);
//    return pdialog;
//  }
//
//  if (pdialog.wnd)
//    MUI_DisposeObject(pdialog.wnd);
//
//  FreeVec(pdialog);
//  return null;
//}
//
///****************************************************************
//...
//*****************************************************************/
//static void city_dialog_update_building(city_dialog pdialog)
//{
//  char buf[32], buf2[64], *descr;
//  city pcity = pdialog.pcity;
//  int max_shield, shield;
//
//  set(pdialog.buy_button, MUIA_Disabled, city_can_buy(pcity));
//  set(pdialog.sell_button, MUIA_Disabled, pcity.did_sell || pdialog.sell_wnd);
//
//  get_city_dialog_production(pcity, buf, sizeof(buf));
//
//  shield = pcity.shield_stock;
//  if (pcity.is_building_unit) {
//    max_shield = Unittype_P.unit_build_shield_cost(pcity.currently_building);
//    descr = get_unit_type(pcity.currently_building).name;
//  } else {
//    max_shield = Improvement.impr_build_shield_cost(pcity.currently_building);
//    descr = City.get_impr_name_ex(pcity, pcity.currently_building);
//  }
//
//  if (!worklist_is_empty(&pcity.worklist)) {
//    buf2 = util.my_snprintf( "%s (%s) (worklist)", buf, descr);
//  } else {
//    buf2 = util.my_snprintf( "%s (%s)", buf, descr);
//  }
//  
//  DoMethod(pdialog.prod_gauge, MUIM_MyGauge_SetGauge,
//	   shield, max_shield, buf2);
//}
//
//
///****************************************************************
//...
//*****************************************************************/
//static void city_dialog_update_information(city_dialog pdialog, city_info info)
//{
//  city pcity = pdialog.pcity;
//  int granaryturns;
//  int growthstyle;
//  int granarystyle;
//  int pollutionstyle;
//
//  if (pcity.food_surplus > 0) {
//    granaryturns = (city_granary_size(pcity.size) - pcity.food_stock +
//		    pcity.food_surplus - 1) / pcity.food_surplus;
//  } else if (pcity.food_surplus < 0) {
//    granaryturns = 1 - (pcity.food_stock / pcity.food_surplus);
//    /* turns before famine loss */
//  } else {
//    granaryturns = 999;
//  }
//
//  growthstyle = (granaryturns == 0 || pcity.food_surplus < 0) ? RED : NORMAL;
//  granarystyle = (pcity.food_surplus < 0 && granaryturns < 4) ? RED : NORMAL;
//  pollutionstyle = (pcity.pollution >= 10) ? RED : NORMAL;
//
//  settextf(info.food_text, "%2d (%+2d)", pcity.food_prod, pcity.food_surplus);
//  settextf(info.shield_text, "%2d (%+2d)", pcity.shield_prod + pcity.shield_waste, pcity.shield_surplus);
//  settextf(info.trade_text, "%2d (%+2d)", pcity.trade_prod + pcity.corruption, pcity.trade_prod);
//  settextf(info.gold_text, "%2d (%+2d)", pcity.tax_total, city_gold_surplus(pcity, pcity.tax_total));
//  settextf(info.luxury_text, "%2d", pcity.luxury_total);
//  settextf(info.science_text, "%2d", pcity.science_total);
//
//  set(info.granary_text, MUIA_Text_PreParse, granarystyle==RED?MUIX_B:"");
//  set(info.growth_text, MUIA_Text_PreParse, growthstyle==RED?MUIX_B:"");
//  set(info.pollution_text, MUIA_Text_PreParse, pollutionstyle==RED?MUIX_B:"");
//
//  settextf(info.granary_text, "%ld/%-ld", pcity.food_stock, city_granary_size(pcity.size));
//  if (granaryturns == 0) {
//    settext(info.growth_text, "blocked");
//  } else if (granaryturns == 999) {
//    settext(info.growth_text, "never");
//  } else {
//    char buf[64];
//    buf = util.my_snprintf(
//		PL("%d turn", "%d turns", granaryturns), granaryturns);
//    settext(info.growth_text,buf);
//  }
//
//  settextf(info.corruption_text, "%ld", pcity.corruption);
//  settextf(info.waste_text, "%ld", pcity.shield_waste);
//  settextf(info.pollution_text, "%ld", pcity.pollution);
//}
//
///****************************************************************
//...
//*****************************************************************/
//static void city_dialog_update_map(city_dialog pdialog)
//{
//  DoMethod(pdialog.map_area, MUIM_CityMap_Refresh);
//  DoMethod(pdialog.happines_map, MUIM_CityMap_Refresh);
//}
//
///****************************************************************
// Updates the displayed citizens. TODO: Optimize it (only one
// group)
//*****************************************************************/
//static void city_dialog_update_citizens(city_dialog pdialog)
//{
//  int i;
//  city pcity = pdialog.pcity;
//  struct citizen_type citizens[MAX_CITY_SIZE];
//
//  DoMethod(pdialog.citizen_group, MUIM_Group_InitChange);
//  if (pdialog.citizen2_group)
//  {
//    DoMethod(pdialog.citizen_group, OM_REMMEMBER, pdialog.citizen2_group);
//    MUI_DisposeObject(pdialog.citizen2_group);
//  }
//
//  pdialog.citizen2_group = HGroup, GroupSpacing(0), End;
//
//  /* Get a list of the citizens. */
//  get_city_citizen_types(pcity, 4, citizens);
//
//  for (i = 0; i < pcity.size; i++) {
//    Object *o = MakeSprite(get_citizen_sprite(citizens[i], i, pcity));
//
//    if (o) {
//      DoMethod(pdialog.citizen2_group, OM_ADDMEMBER, o);
//
//      /* HACK: set sensitivity on widgets */
//      switch (citizens[i]) {
//      case CITIZEN_ELVIS:
//      case CITIZEN_SCIENTIST:
//      case CITIZEN_TAXMAN:
//	DoMethod(o, MUIM_Notify, MUIA_Pressed, false, o, 5, MUIM_CallHook,
//		 &civstandard_hook, city_citizen, pdialog, citizens[i]);
//	break;
//      default:
//	break;
//      }
//    }
//  }
//
//  DoMethod(pdialog.citizen_group, OM_ADDMEMBER, pdialog.citizen2_group);
//
//  DoMethod(pdialog.citizen_group, MUIM_Group_Sort,
//	   pdialog.citizen_left_space, pdialog.citizen2_group, pdialog.citizen_right_space, null);
//
//  DoMethod(pdialog.citizen_group, MUIM_Group_ExitChange);
//}
//
///****************************************************************
//...
//*****************************************************************/
//static void city_dialog_update_supported_units(city_dialog pdialog,
//					int unitid)
//{
//  unit_list plist;
//  struct genlist_iterator myiter;
//  unit punit;
//
//  /* TODO: use unit id */
//  DoMethod(pdialog.supported_group, MUIM_Group_InitChange);
//  DoMethod(pdialog.supported_group, MUIM_AutoGroup_DisposeChilds);
//
//  if(pdialog.pcity.owner != Game.game.player_idx) {
//    plist = &(pdialog.pcity.info_units_supported);
//  } else {
//    plist = &(pdialog.pcity.units_supported);
//  }
//
//  genlist_iterator_init(&myiter, &(plist.list), 0);
//
//  for(;ITERATOR_PTR(myiter); ITERATOR_NEXT(myiter))
//  {
//    Object *o;
//
//    punit = (unit ) ITERATOR_PTR(myiter);
//    o = MakeSupportedUnit(punit);
//    if (o)
//      DoMethod(pdialog.supported_group, OM_ADDMEMBER, o);
//  }
//
//  DoMethod(pdialog.supported_group, MUIM_Group_ExitChange);
//
//
//  /* The same for the supported_group within the units tab */
//  DoMethod(pdialog.units_supported_group, MUIM_Group_InitChange);
//  DoMethod(pdialog.units_supported_group, MUIM_AutoGroup_DisposeChilds);
//
//  if(pdialog.pcity.owner != Game.game.player_idx) {
//    plist = &(pdialog.pcity.info_units_supported);
//  } else {
//    plist = &(pdialog.pcity.units_supported);
//  }
//
//  genlist_iterator_init(&myiter, &(plist.list), 0);
//
//  for(;ITERATOR_PTR(myiter); ITERATOR_NEXT(myiter))
//  {
//    Object *o;
//
//    punit = (unit ) ITERATOR_PTR(myiter);
//    o = MakeSupportedUnit(punit);
//    if (o)
//      DoMethod(pdialog.units_supported_group, OM_ADDMEMBER, o);
//  }
//
//  DoMethod(pdialog.units_supported_group, MUIM_Group_ExitChange);
//}
//
///****************************************************************
//...
//*****************************************************************/
//static void city_dialog_update_present_units(city_dialog pdialog, int unitid)
//{
//  unit_list plist;
//  struct genlist_iterator myiter;
//  unit punit;
//
//  /* TODO: use unit id */
//
//  DoMethod(pdialog.present_group, MUIM_Group_InitChange);
//  DoMethod(pdialog.present_group, MUIM_AutoGroup_DisposeChilds); 
//
//  if(pdialog.pcity.owner != Game.game.player_idx) {
//    plist = &(pdialog.pcity.info_units_present);
//  } else {
//    plist = &(map_get_tile(pdialog.pcity.tile).units);
//  }
//
//  genlist_iterator_init(&myiter, &(plist.list), 0);
//
//  for (; ITERATOR_PTR(myiter); ITERATOR_NEXT(myiter))
//  {
//    Object *o;
//
//    punit = (unit ) ITERATOR_PTR(myiter);
//    if ((o = MakePresentUnit(punit)))
//    {
//      DoMethod(o, MUIM_Notify, MUIA_Pressed, false, app, 5, MUIM_CallHook, &civstandard_hook, city_present, pdialog, punit);
//      DoMethod(pdialog.present_group, OM_ADDMEMBER, o);
//    }
//  }
//
//  DoMethod(pdialog.present_group, MUIM_Group_ExitChange);
//
//  /* The same for the present_group within the units tab */
//  DoMethod(pdialog.units_present_group, MUIM_Group_InitChange);
//  DoMethod(pdialog.units_present_group, MUIM_AutoGroup_DisposeChilds); 
//
//  if(pdialog.pcity.owner != Game.game.player_idx) {
//    plist = &(pdialog.pcity.info_units_present);
//  } else {
//    plist = &(map_get_tile(pdialog.pcity.tile).units);
//  }
//
//  genlist_iterator_init(&myiter, &(plist.list), 0);
//
//  for (; ITERATOR_PTR(myiter); ITERATOR_NEXT(myiter))
//  {
//    Object *o;
//
//    punit = (unit ) ITERATOR_PTR(myiter);
//    if ((o = MakePresentUnit(punit)))
//    {
//      DoMethod(o, MUIM_Notify, MUIA_Pressed, false, app, 5, MUIM_CallHook, &civstandard_hook, city_present, pdialog, punit);
//      DoMethod(pdialog.units_present_group, OM_ADDMEMBER, o);
//    }
//  }
//
//  DoMethod(pdialog.units_present_group, MUIM_Group_ExitChange);
//}
//
///****************************************************************
//...
//*****************************************************************/
//static void city_dialog_update_tradelist(city_dialog pdialog)
//{
//  int i, x = 0, total = 0;
//
//  static char cityname[64], buf[512];
//
//  buf[0] = '\0';
//
//  for (i = 0; i < City_H.NUM_TRADEROUTES; i++) {
//    if (pdialog.pcity.trade[i]) {
//      city pcity;
//      x = 1;
//      total += pdialog.pcity.trade_value[i];
//
//      if ((pcity = Game.find_city_by_id(pdialog.pcity.trade[i]))) {
//	cityname = util.my_snprintf( "%s", pcity.name);
//      } else {
//	cityname = util.my_snprintf( "%s", "Unknown");
//      }
//      my_snprintf(buf + buf.length(), sizeof(buf) - buf.length(),
//		  "Trade with %s gives %d trade.\n",
//		  cityname, pdialog.pcity.trade_value[i]);
//    }
//  }
//  if (!x) {
//    my_snprintf(buf + buf.length(), sizeof(buf) - buf.length(),
//		"No trade routes exist.");
//  } else {
//    my_snprintf(buf + buf.length(), sizeof(buf) - buf.length(),
//		"Total trade from trade routes: %d", total);
//  }
//  settext(pdialog.trade_text,buf);
//}
//
//
///****************************************************************
//...
//*****************************************************************/
//static void city_dialog_update_title(city_dialog pdialog)
//{
//  char *state;
//  if (City.city_unhappy(pdialog.pcity)) {
//    state = " - DISORDER";
//  } else if (City.city_celebrating(pdialog.pcity)) {
//    state = " - celebrating";
//  } else if (City.city_happy(pdialog.pcity)) {
//    state = " - happy";
//  } else state = null;
//
//  if (pdialog.name_transparentstring)
//  {
//    set(pdialog.name_transparentstring,MUIA_TransparentString_Contents, pdialog.pcity.name);
//    settextf(pdialog.title_text, "%s citizens%s",
//	     population_to_text(city_population(pdialog.pcity)),state?state:"");
//  } else
//  {
//    settextf(pdialog.title_text, "%s - %s citizens%s",
//	     pdialog.pcity.name,
//	     population_to_text(city_population(pdialog.pcity)),state?state:"");
//  }
//}
//
///****************************************************************
//...
//*****************************************************************/
//static void city_dialog_update_improvement_list(city_dialog pdialog)
//{
//  LONG j = 0, refresh = false, imprv;
//
//	for (int i = 0; i < Game.game.num_impr_types; i++) {
//	if((pdialog.pcity).improvements[i] == Improvement.I_NONE) {
//		continue;
//	}
//    DoMethod(pdialog.imprv_listview, MUIM_NList_GetEntry, j++, &imprv);
//    if (!imprv || imprv - 1 != i) {
//      refresh = true;
//      goto out;
//    }
//  } ;
// out:
//  /* check the case for to much improvements in list */
//  DoMethod(pdialog.imprv_listview, MUIM_NList_GetEntry, j, &imprv);
//
//  if(refresh || imprv)
//  {
//    set(pdialog.imprv_listview, MUIA_NList_Quiet, true);
//    DoMethod(pdialog.imprv_listview, MUIM_NList_Clear);
//
//	for (int i = 0; i < Game.game.num_impr_types; i++) {
//	if((pdialog.pcity).improvements[i] == Improvement.I_NONE) {
//		continue;
//	}
//      DoMethod(pdialog.imprv_listview, MUIM_NList_InsertSingle, i + 1,
//	       MUIV_NList_Insert_Bottom);
//    } ;
//
//    set(pdialog.imprv_listview, MUIA_NList_Quiet, false);
//  }
//}
//
//
///****************************************************************
// Refreshed the CMA
//*****************************************************************/
//static void refresh_cma_dialog(city_dialog pdialog)
//{
//  city pcity = pdialog.pcity;
//  struct cm_result result;
//  struct cm_parameter param;
//  int controlled = cma_is_city_under_agent(pcity, null);
//  int preset_index;
//  int i;
//
//  cmafec_get_fe_parameter(pcity, &param);
//
//  /* fill in result label */
//  cm_copy_result_from_city(pcity, &result);
//
//  if (!result.found_a_valid) {
//    for (i = 0; i < 10; i++)
//      settext(pdialog.result_text[i],"---");
//  } else {
//    settext(pdialog.result_text[0], cmafec_get_short_descr(&param));
//
//    settextf(pdialog.result_text[1], "%3ld(%+3ld)", result.production[FOOD], result.surplus[FOOD]);
//    settextf(pdialog.result_text[4], "%3ld(%+3ld)", result.production[SHIELD], result.surplus[SHIELD]);
//    settextf(pdialog.result_text[2], "%3ld(%+3ld)", result.production[TRADE], result.surplus[TRADE]);
//    settextf(pdialog.result_text[5], "%3ld(%+3ld)", result.production[GOLD], result.surplus[GOLD]);
//    settextf(pdialog.result_text[3], "%3ld(%+3ld)", result.production[LUXURY], result.surplus[LUXURY]);
//    settextf(pdialog.result_text[6], "%3ld(%+3ld)", result.production[SCIENCE], result.surplus[SCIENCE]);
//    settextf(pdialog.result_text[7], "%ld/%ld/%ld/%ld%s",
//		pcity.size - (result.entertainers + result.scientists +
//		 result.taxmen), result.entertainers, result.scientists,
//		result.taxmen, result.happy ? " happy" : "");
//    settext(pdialog.result_text[8], get_city_growth_string(pcity, result.surplus[FOOD]));
//    settext(pdialog.result_text[9], get_prod_complete_string(pcity, result.surplus[SHIELD]));
//  }
//
//  
//  /* if called from a hscale, we _don't_ want to do this */
//  for (i = 0; i < NUM_STATS; i++)
//  {
//    nnset(pdialog.minimal_surplus_slider[i],MUIA_Numeric_Value,param.minimal_surplus[i]);
//    nnset(pdialog.factor_slider[i],MUIA_Numeric_Value,param.factor[i]);
//  }
//  nnset(pdialog.celebrate_check, MUIA_Selected,param.require_happy);
//  nnset(pdialog.factor_slider[6], MUIA_Numeric_Value, param.happy_factor);
//
//#if 0
//  if (refresh != DONT_REFRESH_SELECT) {
//    /* highlight preset if parameter matches */
//    preset_index = cmafec_preset_get_index_of_parameter(&param);
//    if (preset_index != -1) {
//      allow_refreshes = 0;
//      gtk_clist_select_row(GTK_CLIST(pdialog.preset_list), preset_index, 0);
//      allow_refreshes = 1;
//    } else {
//      gtk_clist_unselect_all(GTK_CLIST(pdialog.preset_list));
//    }
//  }
//#endif
//
//  set(pdialog.cma_change_button, MUIA_Disabled, !(result.found_a_valid && !controlled));
//  set(pdialog.cma_perm_button, MUIA_Disabled, !(result.found_a_valid && !controlled));
//  set(pdialog.cma_release_button, MUIA_Disabled, !controlled);
//}
//
///****************************************************************
// Refreshed the CMA
//*****************************************************************/
//static void refresh_happiness_dialog(city_dialog pdialog)
//{
//  char buf[512], *bptr = buf;
//  int nleft = sizeof(buf);
//  int i;
//
//  city pcity = pdialog.pcity;
//  player pplayer = &Game.game.players[pcity.owner];
//  government g = Government.get_gov_pcity(pcity);
//  int cities = pplayer.cities.foo_list_size();
//  int content = Game.game.unhappysize;
//  int basis = Game.game.cityfactor + g.empire_size_mod;
//  int step = g.empire_size_inc;
//  int excess = cities - basis;
//  int penalty = 0;
//
//  int faces;
//  int mlmax = g.martial_law_max;
//
//  /* CITIES */
//  if (excess > 0) {
//    if (step > 0)
//      penalty = 1 + (excess - 1) / step;
//    else
//      penalty = 1;
//  } else {
//    excess = 0;
//    penalty = 0;
//  }
//
//  bptr = String.format
//	      "Cities: %d total, %d over threshold of %d cities.\n",
//	      cities, excess, basis);
//  bptr = end_of_strn(bptr, &nleft);
//
//  bptr = String.format "%d content before penalty with ", content);
//  bptr = end_of_strn(bptr, &nleft);
//  bptr = String.format "%d additional unhappy citizens.", penalty);
//  bptr = end_of_strn(bptr, &nleft);
//
//  settext(pdialog.happiness_citizen_text[0], buf);
//
//  /* LUXURY */
//  bptr = String.format "Luxury: %d total (maximum %d usable). ",
//	      pcity.luxury_total, 2 * pcity.size);
//
//  settext(pdialog.happiness_citizen_text[1], buf);
//
//
//  /* BUILDINGS */
//  settext(pdialog.happiness_citizen_text[2],
//	  get_happiness_buildings(pcity));
//
//
//  /* UNITS */
//  bptr = buf;
//  nleft = sizeof(buf);
//  bptr = String.format "Units: ");
//  bptr = end_of_strn(bptr, &nleft);
//
//  if (mlmax > 0) {
//    bptr = String.format "Martial law in effect (");
//    bptr = end_of_strn(bptr, &nleft);
//
//    if (mlmax == 100)
//      bptr = String.format "no maximum, ");
//    else
//      bptr = String.format PL("%d unit maximum, ",
//				   "%d units maximum", mlmax), mlmax);
//    bptr = end_of_strn(bptr, &nleft);
//
//    bptr = String.format "%d per unit). ", g.martial_law_per);
//  } else {
//    bptr = String.format
//		"Military units in the field may cause unhappiness. ");
//  }
//
//  settext(pdialog.happiness_citizen_text[3], buf);
//
//  /* WONDERS */
//  settext(pdialog.happiness_citizen_text[4],
//	  get_happiness_wonders(pcity));
//
//  /* the citizen's */
//  for(i=0;i<5;i++)
//  {
//    int j;
//    int num_citizens = pcity.size;
//    struct citizen_type citizens[MAX_CITY_SIZE];
//
//    DoMethod(pdialog.happiness_citizen_group[i],MUIM_Group_InitChange);
//    DisposeAllChilds(pdialog.happiness_citizen_group[i]);
//
//    get_city_citizen_types(pcity, i, citizens);
//
//    for (j = 0; j < num_citizens; j++) {
//      Object *obj;
//
//      obj = MakeSprite(get_citizen_sprite(citizens[j], j, pcity));
//      if (obj) {
//	DoMethod(pdialog.happiness_citizen_group[i], OM_ADDMEMBER, obj);
//      }
//    }
//    DoMethod(pdialog.happiness_citizen_group[i], OM_ADDMEMBER, HVSpace);
//    DoMethod(pdialog.happiness_citizen_group[i],MUIM_Group_ExitChange);
//  }
//}
//
///**************************************************************************
// refresh the city options (auto_[land, air, sea, helicopter] and 
// disband-is-size-1) in the misc page.
//**************************************************************************/
//static void set_cityopt_values(city_dialog pdialog)
//{
//  city pcity = pdialog.pcity;
//  int i, newcitizen_index;
//
//  for (i = 0; i < NUM_CITY_OPTS; i++)
//  {
//    set(pdialog.misc_checks[i], MUIA_Selected,
//	is_city_option_set(pcity, i));
//  }
//
//  if (is_city_option_set(pcity, CITYO_NEW_EINSTEIN))
//    newcitizen_index = 1;
//  else if (is_city_option_set(pcity, CITYO_NEW_TAXMAN))
//    newcitizen_index = 2;
//  else
//    newcitizen_index = 0;
//
//  set(pdialog.misc_radio, MUIA_Radio_Active, newcitizen_index);
//}
//
///****************************************************************
//...
//*****************************************************************/
//static void close_city_dialog(city_dialog pdialog)
//{
//  if (pdialog)
//  {
//    genlist_unlink(&dialog_list, pdialog);
//    for (unit psunit : pdialog.pcity.info_units_supported.data) {
//      free(psunit);
//    } }
//    unit_list_unlink_all(&(pdialog.pcity.info_units_supported));
//    for (unit psunit : pdialog.pcity.info_units_present.data) {
//      free(psunit);
//    } }
//    unit_list_unlink_all(&(pdialog.pcity.info_units_present));
//    set(pdialog.wnd, MUIA_Window_Open, false);
//    if (pdialog.sell_wnd)
//      destroy_message_dialog(pdialog.sell_wnd);
//
//    last_page = xget(pdialog.register_group,MUIA_Group_ActivePage);
//
//    DoMethod(app, OM_REMMEMBER, pdialog.wnd);
//    MUI_DisposeObject(pdialog.wnd);
//    FreeVec(pdialog);
//  }
//}
//
}