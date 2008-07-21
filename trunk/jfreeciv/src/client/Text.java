package client;

public class Text{

// Freeciv - Copyright (C) 2002 - The Freeciv Poject
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
//#include <stdarg.h>
//#include <string.h>
//
//#include "Map.map.h"
//#include "combat.h"
//#include "fcintl.h"
//#include "log.h"
//#include "support.h"
//#include "climisc.h"
//#include "government.h"
//#include "civclient.h"
//
//#include "control.h"
//#include "goto.h"
//#include "text.h"
//
///*
// * An individual add(_line) string has to fit into GROW_TMP_SIZE. One buffer
// * of GROW_TMP_SIZE size will be allocated for the entire client.
// */
//#define GROW_TMP_SIZE	(1024)
//
///* 
// * Initial size of the buffer for each function.  It may later be
// * grown as needed.
// */
//public static final int START_SIZE = 10;
//
//static void real_add_line(char **buffer, size_t * buffer_size,
//			  final String format)
//fc__attribute((__format__(__printf__, 3, 4)));
//static void real_add(char **buffer, size_t * buffer_size,
//		     final String format)
//fc__attribute((__format__(__printf__, 3, 4)));
//
//#define add_line(...) real_add_line(&out,&out_size, __VA_ARGS__)
//#define add(...) real_add(&out,&out_size, __VA_ARGS__)
//
//#define INIT			\
//  static char *out = null;	\
//  static size_t out_size = 0;	\
//  if (!out) {			\
//    out_size = START_SIZE;	\
//    out = fc_malloc(out_size);	\
//  }				\
//  out[0] = '\0';
//
//#define RETURN	return out;
//
///****************************************************************************
//  Formats the parameters and appends them. Grows the buffer if
//  required.
//****************************************************************************/
//static void grow_printf(char **buffer, size_t *buffer_size,
//			final String format, va_list ap)
//{
//  size_t new_len;
//  static char buf[GROW_TMP_SIZE];
//
//  if (my_vsnprintf(buf, sizeof(buf), format, ap) == -1) {
//    die("Formatted string bigger than %lu", (unsigned long)sizeof(buf));
//  }
//
//  new_len = strlen(*buffer) + buf.length() + 1;
//
//  if (new_len > *buffer_size) {
//    /* It's important that we grow the buffer geometrically, otherwise the
//     * overhead adds up quickly. */
//    size_t new_size = MAX(new_len, *buffer_size * 2);
//
//    util.freelog(LOG_VERBOSE, "expand from %lu to %lu to add '%s'",
//	    (unsigned long)*buffer_size, (unsigned long)new_size, buf);
//
//    *buffer_size = new_size;
//    *buffer = fc_realloc(*buffer, *buffer_size);
//  }
//  mystrlcat(*buffer, buf, *buffer_size);
//}
//
///****************************************************************************
//  Add a full line of text to the buffer.
//****************************************************************************/
//static void real_add_line(char **buffer, size_t * buffer_size,
//			  final String format)
//{
//  va_list args;
//
//  if ((*buffer)[0] != '\0') {
//    real_add(buffer, buffer_size, "%s", "\n");
//  }
//
//  va_start(args, format);
//  grow_printf(buffer, buffer_size, format, args);
//  va_end(args);
//}
//
///****************************************************************************
//  Add the text to the buffer.
//****************************************************************************/
//static void real_add(char **buffer, size_t * buffer_size, final String format,
//		     ...)
//{
//  va_list args;
//
//  va_start(args, format);
//  grow_printf(buffer, buffer_size, format, args);
//  va_end(args);
//}
//
///***************************************************************
//  Return a (static) string with a tile's food/prod/trade
//***************************************************************/
//static final String map_get_tile_fpt_text(final tile ptile)
//{
//  static char s[64];
//  char food[16];
//  char shields[16];
//  char trade[16];
//  int x, before_penalty;
//  
//  government gov = get_gov_pplayer(game.player_ptr);
//  
//  x = get_food_tile(ptile);
//  before_penalty = gov.food_before_penalty;
//
//  if (before_penalty > 0 && x > before_penalty) {
//    food = util.my_snprintf( "%d(-1)", x);
//  } else {
//    food = util.my_snprintf( "%d", x);
//  }
//
//  x = get_shields_tile(ptile);
//  before_penalty = gov.shields_before_penalty;
//
//  if (before_penalty > 0 && x > before_penalty) {
//    shields = util.my_snprintf( "%d(-1)", x);
//  } else {
//    shields = util.my_snprintf( "%d", x);
//  }
//
//  x = get_trade_tile(ptile);
//  before_penalty = gov.trade_before_penalty;
//
//  if (before_penalty > 0 && x > before_penalty) {
//    trade = util.my_snprintf( "%d(-1)", x);
//  } else {
//    trade = util.my_snprintf( "%d", x);
//  }
//  
//  s = util.my_snprintf( "%s/%s/%s", food, shields, trade);
//  return s;
//}
//
///****************************************************************************
//  Text to popup on a middle-click in the mapview.
//****************************************************************************/
//final String popup_info_text(tile ptile)
//{
//  final String activity_text;
//  city pcity = ptile.city;
//  unit punit = find_visible_unit(ptile);
//  unit pfocus_unit = get_unit_in_focus();
//  final String diplo_nation_plural_adjectives[DS_LAST] =
//    {Q"?nation:Neutral", Q"?nation:Hostile",
//     "" /* unused, DS_CEASEFIRE*/,
//     Q"?nation:Peaceful", Q"?nation:Friendly", 
//     Q"?nation:Mysterious", Q"?nation:Friendly(team)"};
//  final String diplo_city_adjectives[DS_LAST] =
//    {Q"?city:Neutral", Q"?city:Hostile",
//     "" /*unused, DS_CEASEFIRE */,
//     Q"?city:Peaceful", Q"?city:Friendly", Q"?city:Mysterious",
//     Q"?city:Friendly(team)"};
//  INIT;
//
//#ifdef DEBUG
//  add_line("Location: (%d, %d) [%d]", 
//	   ptile.x, ptile.y, ptile.continent); 
//#endif /*DEBUG*/
//  add_line("Terrain: %s",  map_get_tile_info_text(ptile));
//  add_line("Food/Prod/Trade: %s",
//	   map_get_tile_fpt_text(ptile));
//  if (Map.tile_has_special(ptile, S_HUT)) {
//    add_line("Minor Tribe Village");
//  }
//  if (game.borders > 0 && !pcity) {
//    player owner = map_get_owner(ptile);
//    player_diplstate ds = game.player_ptr.diplstates;
//
//    if (owner == game.player_ptr){
//      add_line("Our Territory");
//    } else if (owner) {
//      if (ds[owner.player_no].type == DS_CEASEFIRE) {
//	int turns = ds[owner.player_no].turns_left;
//
//	/* TRANS: "Polish territory (5 turn ceasefire)" */
//	add_line(PL_("%s territory (%d turn ceasefire)",
//		     "%s territory (%d turn ceasefire)",
//		     turns),
//		 Nation.get_nation_name(owner.nation), turns);
//      } else {
//	/* TRANS: "Territory of the friendly Polish".  See the
//	 * ?nation adjectives. */
//	add_line("Territory of the %s %s",
//		 diplo_nation_plural_adjectives[ds[owner.player_no].type],
//		 Nation.Nation.get_nation_name_plural(owner.nation));
//      }
//    } else {
//      add_line("Unclaimed territory");
//    }
//  }
//  if (pcity) {
//    /* Look at city owner, not tile owner (the two should be the same, if
//     * borders are in use). */
//    player owner = city_owner(pcity);
//    player_diplstate ds = game.player_ptr.diplstates;
//
//    if (owner == game.player_ptr){
//      /* TRANS: "City: Warsaw (Polish)" */
//      add_line("City: %s (%s)", pcity.name,
//	       Nation.get_nation_name(owner.nation));
//    } else if (owner) {
//      if (ds[owner.player_no].type == DS_CEASEFIRE) {
//	int turns = ds[owner.player_no].turns_left;
//
//	/* TRANS:  "City: Warsaw (Polish, 5 turn ceasefire)" */
//        add_line(PL_("City: %s (%s, %d turn ceasefire)",
//				       "City: %s (%s, %d turn ceasefire)",
//				       turns),
//		 pcity.name,
//		 Nation.get_nation_name(owner.nation),
//		 turns);
//      } else {
//        /* TRANS: "City: Warsaw (Polish,friendly)" */
//        add_line("City: %s (%s,%s)", pcity.name,
//		 Nation.get_nation_name(owner.nation),
//		 diplo_city_adjectives[ds[owner.player_no].type]);
//      }
//    }
//    if (city_got_citywalls(pcity)) {
//      /* TRANS: previous lines gave other information about the city. */
//      add("%s"," with City Walls");
//    }
//
//    if (pfocus_unit) {
//      city hcity = find_city_by_id(pfocus_unit.homecity);
//
//      if (unit_flag(pfocus_unit, F_TRADE_ROUTE)
//	  && can_cities_trade(hcity, pcity)
//	  && can_establish_trade_route(hcity, pcity)) {
//	/* TRANS: "Trade from Warsaw: 5" */
//	add_line("Trade from %s: %d",
//		 hcity.name, trade_between_cities(hcity, pcity));
//      }
//    } 
//  } 
//  if (get_tile_infrastructure_set(ptile)) {
//    add_line("Infrastructure: %s",
//	     map_get_infrastructure_text(ptile.special));
//  }
//  activity_text = concat_tile_activity_text(ptile);
//  if (activity_text.length() > 0) {
//    add_line("Activity: %s", activity_text);
//  }
//  if (punit && !pcity) {
//    player owner = punit.unit_owner();
//    player_diplstate ds = game.player_ptr.diplstates;
//    unit_type ptype = punit.unit_type();
//    char vet[1024] = "";
//
//    if (owner == game.player_ptr){
//      city pcity;
//      char tmp[64] = {0};
//
//      pcity = player_find_city_by_id(game.player_ptr, punit.homecity);
//      if (pcity) {
//	tmp = util.my_snprintf( "/%s", pcity.name);
//      }
//      /* TRANS: "Unit: Musketeers (Polish/Warsaw)" */
//      add_line("Unit: %s (%s%s)", ptype.name,
//	  Nation.get_nation_name(owner.nation),
//	  tmp);
//    } else if (owner) {
//      if (ds[owner.player_no].type == DS_CEASEFIRE) {
//	int turns = ds[owner.player_no].turns_left;
//
//	/* TRANS:  "Unit: Musketeers (Polish, 5 turn ceasefire)" */
//        add_line(PL_("Unit: %s (%s, %d turn ceasefire)",
//				       "Unit: %s (%s, %d turn ceasefire)",
//				       turns),
//		 ptype.name,
//		 Nation.get_nation_name(owner.nation),
//		 turns);
//      } else {
//	/* TRANS: "Unit: Musketeers (Polish,friendly)" */
//	add_line("Unit: %s (%s,%s)", ptype.name,
//		 Nation.get_nation_name(owner.nation),
//		 diplo_city_adjectives[ds[owner.player_no].type]);
//      }
//    }
//
//    if (pfocus_unit) {
//      int att_chance = FC_INFINITY, def_chance = FC_INFINITY;
//      boolean found = false;
//
//      for (unit tile_unit : ptile.units.data) {
//	if (tile_unit.owner != pfocus_unit.owner) {
//	  int att = unit_win_chance(pfocus_unit, tile_unit) * 100;
//	  int def = (1.0 - unit_win_chance(tile_unit, pfocus_unit)) * 100;
//
//	  found = true;
//
//	  /* Presumably the best attacker and defender will be used. */
//	  att_chance = Math.min(att, att_chance);
//	  def_chance = Math.min(def, def_chance);
//	}
//      } }
//
//      if (found) {
//	/* TRANS: "Chance to win: A:95% D:46%" */
//	add_line("Chance to win: A:%d%% D:%d%%",
//		 att_chance, def_chance);
//      }
//    }
//
//    vet = util.my_snprintf( " (%s)",
//		_(punit.unit_type().veteran[punit.veteran].name));
//
//    /* TRANS: A is attack power, D is defense power, FP is firepower,
//     * HP is hitpoints (current and max). */
//    add_line("A:%d D:%d FP:%d HP:%d/%d%s",
//	     ptype.attack_strength, 
//	     ptype.defense_strength, ptype.firepower, punit.hp, 
//	     ptype.hp, vet);
//    if (owner == game.player_ptr
//	&& unit_list_size(&ptile.units) >= 2) {
//      /* TRANS: "5 more" units on this tile */
//      add("  (%d more)", unit_list_size(&ptile.units) - 1);
//    }
//  } 
//  RETURN;
//}
//
///****************************************************************************
//  Creates the activity progress text for the given tile.
//
//  This should only be used inside popup_info_text and should eventually be
//  made static.
//****************************************************************************/
//final String concat_tile_activity_text(tile ptile)
//{
//  int activity_total[ACTIVITY_LAST];
//  int activity_units[ACTIVITY_LAST];
//  int num_activities = 0;
//  int remains, turns, i;
//  INIT;
//
//  memset(activity_total, 0, sizeof(activity_total));
//  memset(activity_units, 0, sizeof(activity_units));
//
//  for (unit punit : ptile.units.data) {
//    activity_total[punit.activity] += punit.activity_count;
//    activity_total[punit.activity] += get_activity_rate_this_turn(punit);
//    activity_units[punit.activity] += get_activity_rate(punit);
//  } }
//
//  for (i = 0; i < ACTIVITY_LAST; i++) {
//    if (is_build_or_clean_activity(i) && activity_units[i] > 0) {
//      if (num_activities > 0) {
//	add("/");
//      }
//      remains = map_activity_time(i, ptile) - activity_total[i];
//      if (remains > 0) {
//	turns = 1 + (remains + activity_units[i] - 1) / activity_units[i];
//      } else {
//	/* activity will be finished this turn */
//	turns = 1;
//      }
//      add("%s(%d)", get_activity_text(i), turns);
//      num_activities++;
//    }
//  }
//
//  RETURN;
//}
//
//#define FAR_CITY_SQUARE_DIST (2*(6*6))
//
///****************************************************************************
//  Returns the text describing the city and its distance.
//****************************************************************************/
//final String get_nearest_city_text(city pcity, int sq_dist)
//{
//  INIT;
//
//  /* just to be sure */
//  if (!pcity) {
//    sq_dist = -1;
//  }
//
//  add((sq_dist >= FAR_CITY_SQUARE_DIST) ? "far from %s"
//      : (sq_dist > 0) ? "near %s"
//      : (sq_dist == 0) ? "in %s"
//      : "%s", pcity ? pcity.name : "");
//
//  RETURN;
//}
//
///****************************************************************************
//  Returns the unit description.
//****************************************************************************/
//final String unit_description(unit punit)
//{
//  int pcity_near_dist;
//  city pcity =
//      player_find_city_by_id(game.player_ptr, punit.homecity);
//  city pcity_near = get_nearest_city(punit, &pcity_near_dist);
//  unit_type ptype = punit.unit_type();
//  INIT;
//
//  add("%s", ptype.name);
//
//  if (ptype.veteran[punit.veteran].name[0] != '\0') {
//    add(" (%s)", _(ptype.veteran[punit.veteran].name));
//  }
//  add("\n");
//  add_line("%s", unit_activity_text(punit));
//
//  if (pcity) {
//    /* TRANS: "from Warsaw" */
//    add_line("from %s", pcity.name);
//  } else {
//    add("\n");
//  }
//
//  add_line("%s", get_nearest_city_text(pcity_near, pcity_near_dist));
//
//  RETURN;
//}
//
///****************************************************************************
//  Returns the text to display in the science dialog.
//****************************************************************************/
//final String science_dialog_text()
//{
//  int turns_to_advance;
//  player plr = game.player_ptr;
//  int ours = 0, theirs = 0;
//  INIT;
//
//  /* Sum up science */
//  for(player pplayer: game.players){
//    enum diplstate_type ds = pplayer_get_diplstate(plr, pplayer).type;
//
//    if (plr == pplayer) {
//      for (city pcity : pplayer.cities.data) {
//        ours += pcity.science_total;
//      } }
//    } else if (ds == diplstate_type.DS_TEAM) {
//      theirs += pplayer.research.bulbs_last_turn;
//    }
//  }
//
//  if (ours == 0 && theirs == 0) {
//    add("Progress: no research");
//    RETURN;
//  }
//  assert(ours >= 0 && theirs >= 0);
//  turns_to_advance = (total_bulbs_required(plr) + ours + theirs - 1)
//                     / (ours + theirs);
//  if (theirs == 0) {
//    /* Simple version, no techpool */
//    add(PL_("Progress: %d turn/advance (%d pts/turn)",
//	    "Progress: %d turns/advance (%d pts/turn)",
//	    turns_to_advance), turns_to_advance, ours);
//  } else {
//    /* Techpool version */
//    add(PL_("Progress: %d turn/advance (%d pts/turn, "
//	    "%d pts/turn from team)",
//	    "Progress: %d turns/advance (%d pts/turn, "
//	    "%d pts/turn from team)",
//	    turns_to_advance), turns_to_advance, ours, theirs);
//  }
//  RETURN;
//}
//
///****************************************************************************
//  Return the text for the label on the info panel.  (This is traditionally
//  shown to the left of the mapview.)
//****************************************************************************/
//final String get_info_label_text()
//{
//  INIT;
//
//  add_line("Population: %s",
//	     population_to_text(civ_population(game.player_ptr)));
//  add_line("Year: %s", Shared.textyear(game.year));
//  add_line("Gold: %d", game.player_ptr.economic.gold);
//  add_line("Tax: %d Lux: %d Sci: %d", game.player_ptr.economic.tax,
//	   game.player_ptr.economic.luxury,
//	   game.player_ptr.economic.science);
//  RETURN;
//}
//
///****************************************************************************
//  Return the title text for the unit info shown in the info panel.
//
//  FIXME: this should be renamed.
//****************************************************************************/
//final String get_unit_info_label_text1(unit punit)
//{
//  INIT;
//  
//  if (punit) {
//    unit_type ptype = punit.unit_type();
//
//    add("%s", ptype.name);
//  }
//  RETURN;
//}
//
///****************************************************************************
//  Return the text body for the unit info shown in the info panel.
//
//  FIXME: this should be renamed.
//****************************************************************************/
//final String get_unit_info_label_text2(unit punit)
//{
//  INIT;
//
//  /* This text should always have the same number of lines.  Otherwise the
//   * GUI widgets may be confused and try to resize themselves. */
//  if (punit) {
//    city pcity =
//	player_find_city_by_id(game.player_ptr, punit.homecity);
//    int infrastructure =
//	get_tile_infrastructure_set(punit.tile);
//
//    if (hover_unit == punit.id) {
//      add_line("Turns to target: %d", get_goto_turns());
//    } else {
//      add_line("%s", unit_activity_text(punit));
//    }
//
//    add_line("%s", map_get_tile_info_text(punit.tile));
//    if (infrastructure) {
//      add_line("%s", map_get_infrastructure_text(infrastructure));
//    } else {
//      add_line(" ");
//    }
//    if (pcity) {
//      add_line("%s", pcity.name);
//    } else {
//      add_line(" ");
//    }
//  } else {
//    add("\n\n\n");
//  }
//  RETURN;
//}
//
///****************************************************************************
//  Get a tooltip text for the info panel research indicator.  See
//  client_research_sprite().
//****************************************************************************/
//final String get_bulb_tooltip()
//{
//  INIT;
//
//  add(_("Shows your progress in researching "
//	"the current technology.\n%s: %d/%d."),
//      get_tech_name(game.player_ptr,
//		    game.player_ptr.research.researching),
//      game.player_ptr.research.bulbs_researched,
//      total_bulbs_required(game.player_ptr));
//  RETURN;
//}
//
///****************************************************************************
//  Get a tooltip text for the info panel global warning indicator.  See also
//  client_warming_sprite().
//****************************************************************************/
//final String get_global_warming_tooltip()
//{
//  INIT;
//
//  add("Shows the progress of global warming:\n%d.",
//      client_warming_sprite());
//  RETURN;
//}
//
///****************************************************************************
//  Get a tooltip text for the info panel nuclear winter indicator.  See also
//  client_cooling_sprite().
//****************************************************************************/
//final String get_nuclear_winter_tooltip()
//{
//  INIT;
//
//  add("Shows the progress of nuclear winter:\n%d.",
//      client_cooling_sprite());
//  RETURN;
//}
//
///****************************************************************************
//  Get a tooltip text for the info panel government indicator.  See also
//  get_government(...).sprite.
//****************************************************************************/
//final String get_government_tooltip()
//{
//  INIT;
//
//  add("Shows your current government:\n%s.",
//      get_government_name(game.player_ptr.government));
//  RETURN;
//}
//
///****************************************************************************
//  Returns a description of the given spaceship.  If there is no spaceship
//  (pship is null) then text with dummy values is returned.
//****************************************************************************/
//final String get_spaceship_descr(player_spaceship pship)
//{
//  struct player_spaceship ship;
//  INIT;
//
//  if (!pship) {
//    pship = &ship;
//    memset(&ship, 0, sizeof(ship));
//  }
//
//  /* TRANS: spaceship text; should have finalant width. */
//  add_line("Population:      %5d", pship.population);
//
//  /* TRANS: spaceship text; should have finalant width. */
//  add_line("Support:         %5d %%",
//	   (int) (pship.support_rate * 100.0));
//
//  /* TRANS: spaceship text; should have finalant width. */
//  add_line("Energy:          %5d %%",
//	   (int) (pship.energy_rate * 100.0));
//
//  /* TRANS: spaceship text; should have finalant width. */
//  add_line(PL_("Mass:            %5d ton",
//	       "Mass:            %5d tons", pship.mass), pship.mass);
//
//  if (pship.propulsion > 0) {
//    /* TRANS: spaceship text; should have finalant width. */
//    add_line("Travel time:     %5.1f years",
//	     (float) (0.1 * ((int) (pship.travel_time * 10.0))));
//  } else {
//    /* TRANS: spaceship text; should have finalant width. */
//    add_line("%s", "Travel time:        N/A     ");
//  }
//
//  /* TRANS: spaceship text; should have finalant width. */
//  add_line("Success prob.:   %5d %%",
//	   (int) (pship.success_rate * 100.0));
//
//  /* TRANS: spaceship text; should have finalant width. */
//  add_line("Year of arrival: %8s",
//	   (pship.state ==
//	    spaceship_state.SSHIP_LAUNCHED) ? Shared.textyear((int) (pship.launch_year +
//					      (int) pship.
//					      travel_time)) : "-   ");
//
//  RETURN;
//}
//
///****************************************************************************
//  Get the text showing the timeout.  This is generally disaplyed on the info
//  panel.
//****************************************************************************/
//final String get_timeout_label_text()
//{
//  INIT;
//
//  if (game.timeout <= 0) {
//    add("%s", Q"?timeout:off");
//  } else {
//    add("%s", format_duration(seconds_to_turndone));
//  }
//
//  RETURN;
//}
//
///****************************************************************************
//  Format a duration, in seconds, so it comes up in minutes or hours if
//  that would be more meaningful.
//
//  (7 characters, maximum.  Enough for, e.g., "99h 59m".)
//****************************************************************************/
//final String format_duration(int duration)
//{
//  INIT;
//
//  if (duration < 0) {
//    duration = 0;
//  }
//  if (duration < 60) {
//    add(Q"?seconds:%02ds", duration);
//  } else if (duration < 3600) {	/* < 60 minutes */
//    add(Q"?mins/secs:%02dm %02ds", duration / 60, duration % 60);
//  } else if (duration < 360000) {	/* < 100 hours */
//    add(Q"?hrs/mns:%02dh %02dm", duration / 3600, (duration / 60) % 60);
//  } else if (duration < 8640000) {	/* < 100 days */
//    add(Q"?dys/hrs:%02dd %02dh", duration / 86400,
//	(duration / 3600) % 24);
//  } else {
//    add("%s", Q"?duration:overflow");
//  }
//
//  RETURN;
//}
//
///****************************************************************************
//  Return text giving the ping time for the player.  This is generally used
//  used in the playerdlg.  This should only be used in playerdlg_common.c.
//****************************************************************************/
//final String get_ping_time_text(player pplayer)
//{
//  INIT;
//
//  if (conn_list_size(&pplayer.connections) > 0
//      && conn_list_get(&pplayer.connections, 0).ping_time != -1.0) {
//    double ping_time_in_ms =
//	1000 * conn_list_get(&pplayer.connections, 0).ping_time;
//
//    add("%6d.%02d ms", (int) ping_time_in_ms,
//	((int) (ping_time_in_ms * 100.0)) % 100);
//  }
//
//  RETURN;
//}
//
///****************************************************************************
//  Get the title for a "report".  This may include the city, economy,
//  military, trade, player, etc., reports.  Some clients may generate the
//  text themselves to get a better GUI layout.
//****************************************************************************/
//final String get_report_title(final String report_name)
//{
//  INIT;
//
//  add_line("%s", report_name);
//
//  /* TRANS: "Republic of the Polish" */
//  add_line("%s of the %s",
//	   get_government_name(game.player_ptr.government),
//	   Nation.Nation.get_nation_name_plural(game.player_ptr.nation));
//
//  add_line("%s %s: %s",
//	   get_ruler_title(game.player_ptr.government,
//			   game.player_ptr.is_male,
//			   game.player_ptr.nation), game.player_ptr.name,
//	   Shared.textyear(game.year));
//  RETURN;
//}
//
///****************************************************************************
//  Get the text describing buildings that affect happiness.
//****************************************************************************/
//final String get_happiness_buildings(final city pcity)
//{
//  int faces = 0;
//  struct effect_source_vector sources;
//  INIT;
//
//  add_line("Buildings: ");
//
//  get_city_bonus_sources(&sources, pcity, EFT_MAKE_CONTENT);
//  effect_source_vector_iterate(&sources, src) {
//    faces++;
//    add("%s. ", get_improvement_name(src.building));
//  } effect_source_vector_iterate_end;
//  effect_source_vector_free(&sources);
//
//  if (faces == 0) {
//    add("None. ");
//  }
//
//  RETURN;
//}
//
///****************************************************************************
//  Get the text describing wonders that affect happiness.
//****************************************************************************/
//final String get_happiness_wonders(final city pcity)
//{
//  int faces = 0;
//  struct effect_source_vector sources;
//  INIT;
//
//  add_line("Wonders: ");
//
//  get_city_bonus_sources(&sources, pcity, EFT_MAKE_HAPPY);
//  effect_source_vector_iterate(&sources, src) {
//    faces++;
//    add("%s. ", get_improvement_name(src.building));
//  } effect_source_vector_iterate_end;
//  effect_source_vector_free(&sources);
//
//  get_city_bonus_sources(&sources, pcity, EFT_FORCE_CONTENT);
//  effect_source_vector_iterate(&sources, src) {
//    faces++;
//    add("%s. ", get_improvement_name(src.building));
//  } effect_source_vector_iterate_end;
//  effect_source_vector_free(&sources);
//
//  get_city_bonus_sources(&sources, pcity, EFT_NO_UNHAPPY);
//  effect_source_vector_iterate(&sources, src) {
//    faces++;
//    add("%s. ", get_improvement_name(src.building));
//  } effect_source_vector_iterate_end;
//  effect_source_vector_free(&sources);
//
//  if (faces == 0) {
//    add("None. ");
//  }
//
//  RETURN;
//}
}