package client;

public class Cityrepdata{

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
//#include <stdio.h>
//#include <string.h>
//
//#include "city.h"
//#include "fcintl.h"
//#include "Game.game.h"
//#include "Map.map.h"
//#include "support.h"
//
//#include "cma_fec.h"
//#include "options.h"
//
//#include "cityrepdata.h"
//
///************************************************************************
// cr_entry = return an entry (one column for one city) for the city report
// These return ptrs to filled in static strings.
// Note the returned string may not be exactly the right length; that
// is handled later.
//*************************************************************************/
//
//static final String cr_entry_cityname(final city pcity)
//{
//  /* We used to truncate the name to 14 bytes.  This should not be needed
//   * in any modern GUI library and may give an invalid string if a
//   * multibyte character is clipped. */
//  return pcity.name;
//}
//
//static final String cr_entry_size(final city pcity)
//{
//  static char buf[8];
//  buf = util.my_snprintf( "%2d", pcity.size);
//  return buf;
//}
//
//static final String cr_entry_hstate_concise(final city pcity)
//{
//  static char buf[4];
//  buf = util.my_snprintf( "%s", (City.city_celebrating(pcity) ? "*" :
//				       (City.city_unhappy(pcity) ? "X" : " ")));
//  return buf;
//}
//
//static final String cr_entry_hstate_verbose(final city pcity)
//{
//  static char buf[16];
//  buf = util.my_snprintf( "%s",
//	      (City.city_celebrating(pcity) ? Q"?city_state:Rapture" :
//	       (City.city_unhappy(pcity) ? Q"?city_state:Disorder" :
//		Q"?city_state:Peace")));
//  return buf;
//}
//
//static final String cr_entry_workers(final city pcity)
//{
//  static char buf[32];
//  buf = util.my_snprintf( "%d/%d/%d/%d", pcity.ppl_happy[4],
//	      pcity.ppl_content[4], pcity.ppl_unhappy[4],
//	      pcity.ppl_angry[4]);
//  return buf;
//}
//
//static final String cr_entry_happy(final city pcity)
//{
//  static char buf[8];
//  buf = util.my_snprintf( "%2d", pcity.ppl_happy[4]);
//  return buf;
//}
//
//static final String cr_entry_content(final city pcity)
//{
//  static char buf[8];
//  buf = util.my_snprintf( "%2d", pcity.ppl_content[4]);
//  return buf;
//}
//
//static final String cr_entry_unhappy(final city pcity)
//{
//  static char buf[8];
//  buf = util.my_snprintf( "%2d", pcity.ppl_unhappy[4]);
//  return buf;
//}
//
//static final String cr_entry_angry(final city pcity)
//{
//  static char buf[8];
//  buf = util.my_snprintf( "%2d", pcity.ppl_angry[4]);
//  return buf;
//}
//
//static final String cr_entry_specialists(final city pcity)
//{
//  return specialists_string(pcity.specialists);
//}
//
//static final String cr_entry_entertainers(final city pcity)
//{
//  static char buf[8];
//  buf = util.my_snprintf( "%2d", pcity.specialists[specialist_type.SP_ELVIS]);
//  return buf;
//}
//
//static final String cr_entry_scientists(final city pcity)
//{
//  static char buf[8];
//  buf = util.my_snprintf( "%2d", pcity.specialists[specialist_type.SP_SCIENTIST]);
//  return buf;
//}
//
//static final String cr_entry_taxmen(final city pcity)
//{
//  static char buf[8];
//  buf = util.my_snprintf( "%2d", pcity.specialists[specialist_type.SP_TAXMAN]);
//  return buf;
//}
//
//static final String cr_entry_attack(final city pcity)
//{
//  static char buf[32];
//  int attack_best[4] = {-1, -1, -1, -1}, i;
//
//  for (unit punit : pcity.tile.units.data) {
//    /* What about allied units?  Should we just count them? */
//    attack_best[3] = get_unit_type(punit.type).attack_strength;
//
//    /* Now that the element is appended to the end of the list, we simply
//       do an insertion sort. */
//    for (i = 2; i >= 0 && attack_best[i] < attack_best[i + 1]; i--) {
//      int tmp = attack_best[i];
//      attack_best[i] = attack_best[i + 1];
//      attack_best[i + 1] = tmp;
//    }
//  } }
//
//  buf[0] = '\0';
//  for (i = 0; i < 3; i++) {
//    if (attack_best[i] >= 0) {
//      my_snprintf(buf + buf.length(), sizeof(buf) - buf.length(),
//		  "%s%d", (i > 0) ? "/" : "", attack_best[i]);
//    } else {
//      my_snprintf(buf + buf.length(), sizeof(buf) - buf.length(),
//		  "%s-", (i > 0) ? "/" : "");
//    }
//  }
//
//  return buf;
//}
//
//static final String cr_entry_defense(final city pcity)
//{
//  static char buf[32];
//  int defense_best[4] = {-1, -1, -1, -1}, i;
//
//  for (unit punit : pcity.tile.units.data) {
//    /* What about allied units?  Should we just count them? */
//    defense_best[3] = get_unit_type(punit.type).defense_strength;
//
//    /* Now that the element is appended to the end of the list, we simply
//       do an insertion sort. */
//    for (i = 2; i >= 0 && defense_best[i] < defense_best[i + 1]; i--) {
//      int tmp = defense_best[i];
//      defense_best[i] = defense_best[i + 1];
//      defense_best[i + 1] = tmp;
//    }
//  } }
//
//  buf[0] = '\0';
//  for (i = 0; i < 3; i++) {
//    if (defense_best[i] >= 0) {
//      my_snprintf(buf + buf.length(), sizeof(buf) - buf.length(),
//		  "%s%d", (i > 0) ? "/" : "", defense_best[i]);
//    } else {
//      my_snprintf(buf + buf.length(), sizeof(buf) - buf.length(),
//		  "%s-", (i > 0) ? "/" : "");
//    }
//  }
//
//  return buf;
//}
//
//static final String cr_entry_supported(final city pcity)
//{
//  static char buf[8];
//  int num_supported = 0;
//
//  for (unit punit : pcity.units_supported.data) {
//    num_supported++;
//  } }
//
//  buf = util.my_snprintf( "%2d", num_supported);
//
//  return buf;
//}
//
//static final String cr_entry_present(final city pcity)
//{
//  static char buf[8];
//  int num_present = 0;
//
//  for (unit punit : pcity.tile.units.data) {
//    num_present++;
//  } }
//
//  buf = util.my_snprintf( "%2d", num_present);
//
//  return buf;
//}
//
//static final String cr_entry_resources(final city pcity)
//{
//  static char buf[32];
//  buf = util.my_snprintf( "%d/%d/%d",
//	      pcity.food_surplus, 
//	      pcity.shield_surplus, 
//	      pcity.trade_prod);
//  return buf;
//}
//
//static final String cr_entry_foodplus(final city pcity)
//{
//  static char buf[8];
//  buf = util.my_snprintf( "%3d",
//	      pcity.food_surplus);
//  return buf;
//}
//
//static final String cr_entry_prodplus(final city pcity)
//{
//  static char buf[8];
//  buf = util.my_snprintf( "%3d",
//	      pcity.shield_surplus);
//  return buf;
//}
//
//static final String cr_entry_tradeplus(final city pcity)
//{
//  static char buf[8];
//  buf = util.my_snprintf( "%3d",
//	      pcity.trade_prod);
//  return buf;
//}
//
//static final String cr_entry_output(final city pcity)
//{
//  static char buf[32];
//  int golutil.die;
//
//  golutil.die = city_gold_surplus(pcity, pcity.tax_total);
//  buf = util.my_snprintf( "%s%d/%d/%d",
//	      (golutil.die < 0) ? "-" : (golutil.die > 0) ? "+" : "",
//	      (golutil.die < 0) ? (-golutil.die) : golutil.die,
//	      pcity.luxury_total,
//	      pcity.science_total);
//  return buf;
//}
//
//static final String cr_entry_gold(final city pcity)
//{
//  static char buf[8];
//  int income = city_gold_surplus(pcity, pcity.tax_total);
//  if (income > 0) {
//    buf = util.my_snprintf( "+%d", income);
//  } else {
//    buf = util.my_snprintf( "%3d", city_gold_surplus(pcity, pcity.tax_total));
//  }
//  return buf;
//}
//
//static final String cr_entry_luxury(final city pcity)
//{
//  static char buf[8];
//  buf = util.my_snprintf( "%3d",
//	      pcity.luxury_total);
//  return buf;
//}
//
//static final String cr_entry_science(final city pcity)
//{
//  static char buf[8];
//  buf = util.my_snprintf( "%3d",
//	      pcity.science_total);
//  return buf;
//}
//
//static final String cr_entry_food(final city pcity)
//{
//  static char buf[32];
//  buf = util.my_snprintf( "%d/%d",
//	      pcity.food_stock,
//	      city_granary_size(pcity.size) );
//  return buf;
//}
//
//static final String cr_entry_growturns(final city pcity)
//{
//  static char buf[8];
//  int turns = city_turns_to_grow(pcity);
//  if (turns == FC_INFINITY) {
//    /* 'never' wouldn't be easily translatable here. */
//    buf = util.my_snprintf( "-");
//  } else {
//    /* Shrinking cities get a negative value. */
//    buf = util.my_snprintf( "%4d", turns);
//  }
//  return buf;
//}
//
//static final String cr_entry_pollution(final city pcity)
//{
//  static char buf[8];
//  buf = util.my_snprintf( "%3d", pcity.pollution);
//  return buf;
//}
//
//static final String cr_entry_num_trade(final city pcity)
//{
//  static char buf[8];
//  buf = util.my_snprintf( "%d", city_num_trade_routes(pcity));
//  return buf;
//}
//
//static final String cr_entry_building(final city pcity)
//{
//  static char buf[128];
//  final String from_worklist =
//    worklist_is_empty(&pcity.worklist) ? "" :
//    concise_city_production ? "*" : "(worklist)";
//	
//  if (Effects.get_current_finalruction_bonus(pcity, effect_type.EFT_PROD_TO_GOLD) > 0) {
//    buf = util.my_snprintf( "%s (%d/X/X/X)%s",
//		City.get_impr_name_ex(pcity, pcity.currently_building),
//		MAX(0, pcity.shield_surplus), from_worklist);
//  } else {
//    int turns = city_turns_to_build(pcity, pcity.currently_building,
//				    pcity.is_building_unit, true);
//    char time[32];
//    final String name;
//    int cost;
//
//    if (turns < 999) {
//      time = util.my_snprintf( "%d", turns);
//    } else {
//      time = util.my_snprintf( "-");
//    }
//
//    if(pcity.is_building_unit) {
//      name = get_unit_type(pcity.currently_building).name;
//      cost = Unittype_P.unit_build_shield_cost(pcity.currently_building);
//    } else {
//      name = City.get_impr_name_ex(pcity, pcity.currently_building);
//      cost = Improvement.impr_build_shield_cost(pcity.currently_building);
//    }
//
//    buf = util.my_snprintf( "%s (%d/%d/%s/%d)%s", name,
//		pcity.shield_stock, cost, time, City.city_buy_cost(pcity),
//		from_worklist);
//  }
//
//  return buf;
//}
//
//static final String cr_entry_corruption(final city pcity)
//{
//  static char buf[8];
//  buf = util.my_snprintf( "%3d", pcity.corruption);
//  return buf;
//}
//
//static final String cr_entry_waste(final city pcity)
//{
//  static char buf[8];
//  buf = util.my_snprintf( "%3d", pcity.shield_waste);
//  return buf;
//}
//
//static final String cr_entry_cma(final city pcity)
//{
//  return cmafec_get_short_descr_of_city(pcity);
//}
//
///* City report options (which columns get shown)
// * To add a new entry, you should just have to:
// * - increment NUM_CREPORT_COLS in cityrepdata.h
// * - add a function like those above
// * - add an entry in the city_report_specs[] table
// */
//
///* This generates the function name and the tagname: */
//#define FUNC_TAG(var)  cr_entry_##var, #var 
//
//struct city_report_spec city_report_specs[] = {
//  { true, -15, 0, null,  N"?city:Name",      N"City Name",
//                                      FUNC_TAG(cityname) },
//  { true, 2, 1, null,  N"?size:Sz",        N"Size",
//                                      FUNC_TAG(size) },
//  { true,  -8, 1, null,  N"State",     N"Rapture/Peace/Disorder",
//                                      FUNC_TAG(hstate_verbose) },
//  { false,  1, 1, null,  null,            N"Concise *=Rapture, X=Disorder",
//                                      FUNC_TAG(hstate_concise) },
//  { true, 10, 1, N"Workers",
//    N"?happy/content/unhappy/angry:H/C/U/A",
//    N"Workers: Happy, Content, Unhappy, Angry",
//    FUNC_TAG(workers) },
//  { false, 2, 1, null, N"?Happy workers:H", N"Workers: Happy",
//    FUNC_TAG(happy) },
//  { false, 2, 1, null, N"?Content workers:C", N"Workers: Content",
//    FUNC_TAG(content) },
//  { false, 2, 1, null, N"?Unhappy workers:U", N"Workers: Unhappy",
//    FUNC_TAG(unhappy) },
//  { false, 2, 1, null, N"?Angry workers:A", N"Workers: Angry",
//    FUNC_TAG(angry) },
//  { false, 7, 1, N"Special",
//    N"?entertainers/scientists/taxmen:E/S/T",
//    N"Entertainers, Scientists, Taxmen",
//    FUNC_TAG(specialists) },
//  { false, 2, 1, null, N"?Entertainers:E", N"Entertainers",
//    FUNC_TAG(entertainers) },
//  { false, 2, 1, null, N"?Scientists:S", N"Scientists",
//    FUNC_TAG(scientists) },
//  { false, 2, 1, null, N"?Taxmen:T", N"Taxmen",
//    FUNC_TAG(taxmen) },
//  { false, 8, 1, N"Best", N"attack",
//    N"Best attacking units", FUNC_TAG(attack)},
//  { false, 8, 1, N"Best", N"defense",
//    N"Best defending units", FUNC_TAG(defense)},
//  { false, 2, 1, N"Units", N"?Supported (units):Sup",
//    N"Number of units supported", FUNC_TAG(supported) },
//  { false, 2, 1, N"Units", N"?Present (units):Prs",
//    N"Number of units present", FUNC_TAG(present) },
//
//  { true,  10, 1, N"Surplus", N"?food/prod/trade:F/P/T",
//                                 N"Surplus: Food, Production, Trade",
//                                      FUNC_TAG(resources) },
//  { false, 3, 1, null, N"?Food surplus:F+", N"Surplus: Food",
//    FUNC_TAG(foodplus) },
//  { false, 3, 1, null, N"?Production surplus:P+",
//    N"Surplus: Production", FUNC_TAG(prodplus) },
//  { false, 3, 1, null, N"?Trade surplus:T+", N"Surplus: Trade",
//    FUNC_TAG(tradeplus) },
//  { true,  10, 1, N"Economy", N"?gold/lux/sci:G/L/S",
//                                 N"Economy: Gold, Luxuries, Science",
//                                      FUNC_TAG(output) },
//  { false, 3, 1, null, N"?Gold:G", N"Economy: Gold",
//    FUNC_TAG(gold) },
//  { false, 3, 1, null, N"?Luxury:L", N"Economy: Luxury",
//    FUNC_TAG(luxury) },
//  { false, 3, 1, null, N"?Science:S", N"Economy: Science",
//    FUNC_TAG(science) },
//  { false,  1, 1, N"?trade_routes:n", N"?trade_routes:T",
//                                         N"Number of Trade Routes",
//                                      FUNC_TAG(num_trade) },
//  { true,   7, 1, N"Food", N"Stock", N"Food Stock",
//                                      FUNC_TAG(food) },
//  { false,  3, 1, null, N"?pollution:Pol",        N"Pollution",
//                                      FUNC_TAG(pollution) },
//  { false,  4, 1, N"Grow", N"Turns", N"Turns until growth/famine",
//    FUNC_TAG(growturns) },
//  { false,  3, 1, null, N"?corruption:Cor",        N"Corruption",
//                                      FUNC_TAG(corruption) },
//  { false,  3, 1, null, N"?waste:Was", N"Waste", FUNC_TAG(waste) },
//  { true,  15, 1, null, N"CMA",	      N"City Management Agent",
//                                      FUNC_TAG(cma) },
//  { true,   0, 1, N"Currently Building", N"(Stock,Target,Turns,Buy)",
//                                            N"Currently Building",
//                                      FUNC_TAG(building) }
//};
//
//#if 0
//final int num_report_cols = ARRAY_SIZE(city_report_specs);
//#endif
//
///******************************************************************
//Some simple wrappers:
//******************************************************************/
//int num_city_report_spec()
//{
//  return NUM_CREPORT_COLS;
//}
//boolean *city_report_spec_show_ptr(int i)
//{
//  return &(city_report_specs[i].show);
//}
//final String city_report_spec_tagname(int i)
//{
//  return city_report_specs[i].tagname;
//}
//
///******************************************************************
//  Initialize city report data.  Currently all this does is
//  pre-translate the fields (to make things easier on the GUI
//  writers).  Should be called before the GUI starts up.
//******************************************************************/
//void init_city_report_data()
//{
//  int i;
//
//  for (i = 0; i < NUM_CREPORT_COLS; i++) {
//    struct city_report_spec* p = &city_report_specs[i];
//
//    if (p.title1) {
//      p.title1 = Q_(p.title1);
//    }
//    if (p.title2) {
//      p.title2 = Q_(p.title2);
//    }
//    p.explanation = _(p.explanation);
//  }
//
//  assert(NUM_CREPORT_COLS == ARRAY_SIZE(city_report_specs));
//}
//
///**********************************************************************
//  This allows more intelligent sorting city report fields by column,
//  although it still does not give the preferred behavior for all
//  fields.
//
//  The GUI can give us the two fields and we will try to guess if
//  they are text or numeric fields. It returns a number less then,
//  equal to, or greater than 0 if field1 is less than, equal to, or
//  greater than field2, respectively. If we are given two text
//  fields, we will compare them as text. If we are given one text and
//  one numerical field, we will place the numerical field first.
//**********************************************************************/
//int cityrepfield_compare(final String field1, final String field2)
//{
//  int scanned1, scanned2;
//  int number1, number2;
//
//  scanned1 = sscanf(field1, "%d", &number1);
//  scanned2 = sscanf(field2, "%d", &number2);
//
//  if (scanned1 == 1 && scanned2 == 1) {
//    /* Both fields are numerical.  Compare them numerically. */
//    return number1 - number2;
//  } else if (scanned1 == 0 && scanned2 == 0) {
//    /* Both fields are text.  Compare them as strings. */
//    return strcmp(field1, field2);
//  } else {
//    /* One field is numerical and one field is text.  To preserve
//     * the logic of comparison sorting we must always sort one before
//     * the other. */
//    return scanned1 - scanned2;
//  }
//}
}