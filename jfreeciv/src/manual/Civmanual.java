package manual;

public class Civmanual{
///**********************************************************************
// Freeciv - Copyright (C) 2004 - The Freeciv Project
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
//#include <stdio.h>
//#include <stdlib.h>
//#include <string.h>
//
//#include "astring.h"
//#include "fciconv.h"
//#include "fcintl.h"
//#include "log.h"
//#include "mem.h"
//#include "registry.h"
//#include "shared.h"
//#include "support.h"
//
//#include "capability.h"
//#include "events.h"
//#include "Game.game.h"
//#include "improvement.h"
//#include "Map.map.h"
//#include "player.h"
//#include "version.h"
//
//#include "helpdata.h"
//#include "helpdlg_g.h"
//
//#include "citytools.h"
//#include "connecthand.h"
//#include "console.h"
//#include "diplhand.h"
//#include "gamehand.h"
//#include "Gamelog.gamelog.h"
//#include "plrhand.h"
//#include "report.h"
//#include "ruleset.h"
//#include "savegame.h"
//#include "srv_main.h"
//
//#include "stdinhand.h"
//#include "commands.h"
//#include "settings.h"
//
//#include "stdinhand_info.h"
//
//enum manuals {
//  MANUAL_SETTINGS,
//  MANUAL_COMMANDS,
//  MANUAL_TERRAIN,
//  MANUAL_BUILDINGS,
//  MANUAL_WONDERS,
//  MANUAL_COUNT
//};
//
///* This formats the manual for an HTML wiki. */
//#ifdef USE_HTML
//#define HEADER "<html><head><link rel=\"stylesheet\" type=\"text/css\" "\
//               "href=\"manual.css\" /></head><body>\n\n"
//#define SECTION_BEGIN "<h3>"
//#define SECTION_END "</h3>"
//#define IMAGE_BEGIN "<img src="
//#define IMAGE_END ".png\">"
//#define SEPARATOR " "
//#define TAIL "</body></html>"
//#else
//#define HEADER " "
//#define SECTION_BEGIN "==="
//#define SECTION_END "==="
//#define IMAGE_BEGIN "[[Image:"
//#define IMAGE_END ".png]]"
//#define SEPARATOR "----\n\n"
//#define TAIL " "
//#endif
//
///**************************************************************************
//  Useless stubs for compiling client code.
//**************************************************************************/
//void popup_help_dialog_string(final String item)
//{
//  /* Empty stub. */
//}
//
//void popdown_help_dialog()
//{
//  /* Empty stub. */
//}
//
///**************************************************************************
//  Write a server manual in html format, then quit.
//**************************************************************************/
//static boolean manual_command()
//{
//  FILE *doc;
//  char filename[40];
//  enum manuals manuals;
//
//  load_rulesets();
//  for (manuals = 0; manuals < MANUAL_COUNT; manuals++) {
//    int i;
//
//    snprintf(filename, sizeof(filename), "manual%d.html", manuals + 1);
//
//    if (!is_reg_file_for_access(filename, true)
//        || !(doc = fopen(filename, "w"))) {
//      util.die("Could not write manual file %s.", filename);
//      return false;
//    }
//
//    fprintf(doc, HEADER);
//
//    switch (manuals) {
//    case MANUAL_SETTINGS:
//      fprintf(doc, "<h1>Freeciv %s server options</h1>\n\n", VERSION_STRING);
//      for (i = 0; settings[i].name; i++) {
//	settings_s op = &settings[i];
//	static struct astring abuf = ASTRING_INIT;
//	final String help = _(op.extra_help);
//
//	astr_minsize(&abuf, help.length() + 10);
//	strcpy(abuf.str, help);
//	wordwrap_string(abuf.str, 76);
//	fprintf(doc, SEPARATOR);
//	fprintf(doc, "%s%s - %s%s\n\n", SECTION_BEGIN, op.name,
//		_(op.short_help), SECTION_END);
//	if (strlen(op.extra_help) > 0) {
//	  fprintf(doc, "<pre>%s</pre>\n\n", abuf.str);
//	}
//	fprintf(doc, "<p class=\"misc\">");
//	fprintf(doc, "Level: %s.<br>", _(sset_level_names[op.level]));
//	fprintf(doc, "Category: %s.<br>",
//		_(sset_category_names[op.category]));
//	if (op.to_client == SSET_SERVER_ONLY) {
//	  fprintf(doc, "Can only be used in server console. ");
//	}
//	if (sset_is_changeable(i)) {
//	  fprintf(doc, "Can be changed during a Game.game. ");
//	} else {
//	  fprintf(doc, "Can <b>not</b> be changed during a Game.game. ");
//	}
//	fprintf(doc, "</p>\n\n");
//	switch (op.type) {
//	case SSET_BOOL:
//	  fprintf(doc, ("<p class=\"bounds\">Minimum: 0, Default: %d, " +
//			 "Maximum: 1</p>\n\n"),
//		  op.bool_default_value ? 1 : 0);
//	  if (*(op.bool_value) != op.bool_default_value) {
//	    fprintf(doc, ("<p class=\"changed\">Value set to %d</p>\n\n"),
//		    *(op.bool_value));
//	  }
//	  break;
//	case SSET_INT:
//	  fprintf(doc, ("<p class=\"bounds\">Minimum: %d, Default: %d, " +
//			 "Maximum: %d</p>\n\n"),
//		  op.int_min_value, op.int_default_value,
//		  op.int_max_value);
//	  if (*(op.int_value) != op.int_default_value) {
//	    fprintf(doc, ("<p class=\"changed\">Value set to %d</p>\n\n"),
//		    *(op.int_value));
//	  }
//	  break;
//	case SSET_STRING:
//	  fprintf(doc, ("<p class=\"bounds\">Default: \"%s\"</p>\n\n"),
//		  op.string_default_value);
//	  if (strcmp(op.string_value, op.string_default_value) != 0) {
//	    fprintf(doc, ("<p class=\"changed\">Value set to %s</p>\n\n"),
//		    op.string_value);
//	  }
//	  break;
//	}
//      }
//      break;
//
//    case MANUAL_COMMANDS:
//      fprintf(doc, "<h1>Freeciv %s server commands</h1>\n\n",
//	      VERSION_STRING);
//      for (i = 0; i < CMD_NUM; i++) {
//	final command cmd = &commands[i];
//
//	fprintf(doc, SEPARATOR);
//	fprintf(doc, "%s%s  -  %s%s\n\n", SECTION_BEGIN, cmd.name,
//		_(cmd.short_help), SECTION_END);
//	if (cmd.synopsis) {
//	  fprintf(doc, ("<table>\n<tr>\n<td valign=\"top\">" +
//			 "<pre>Synopsis:</pre></td>\n<td>"));
//	  fprintf(doc, "<pre>%s</pre></td></tr></table>", _(cmd.synopsis));
//	}
//	fprintf(doc, ("<p class=\"level\">Level: %s</p>\n\n"),
//		cmdlevel_name(cmd.game_level));
//	if (cmd.game_level != cmd.pregame_level) {
//	  fprintf(doc, ("<p class=\"level\">Pregame level: %s</p>\n\n"),
//		  cmdlevel_name(cmd.pregame_level));
//	}
//	if (cmd.extra_help) {
//	  static struct astring abuf = ASTRING_INIT;
//	  final String help = _(cmd.extra_help);
//
//	  astr_minsize(&abuf, help.length()+1);
//	  strcpy(abuf.str, help);
//	  wordwrap_string(abuf.str, 76);
//	  fprintf(doc, "<p>Description:</p>\n\n");
//	  fprintf(doc, "<pre>%s</pre>\n\n", abuf.str);
//	}
//      }
//      break;
//
//    case MANUAL_TERRAIN:
//      fprintf(doc, "<h1>Freeciv %s terrain help</h1>\n\n",
//	      VERSION_STRING);
//      fprintf(doc, "<table border=1><tr><th>Terrain</th>");
//      fprintf(doc, "<th>Food/ Shield/ Trade</th>");
//      fprintf(doc, "<th>Special 1</th><th>Food/ Shield/ Trade</th>");
//      fprintf(doc, "<th>Special 2</th><th>Food/ Shield/ Trade</th>");
//      fprintf(doc, ("<th>Move cost</th><th>Defense</th><th>Road " +
//		     "+trade</th>\n"));
//      fprintf(doc, "<th>Irrigation +food</th><th>Mining +shields</th>\n");
//      fprintf(doc, "<th>Transform to</th>");
//      fprintf(doc, "</tr>\n");
//      terrain_type_iterate(id) {
//	tile_type ptype = get_tile_type(id);
//
//	if (ptype.defense_bonus == 0) {
//	  /* Must be a disabled piece of terrain */
//	  continue;
//	}
//
//	fprintf(doc, "<tr><td>%s%s%s %s</td>", IMAGE_BEGIN,
//		ptype.graphic_str,
//		IMAGE_END, get_terrain_name(id));
//	fprintf(doc, "<td>%d / %d / %d</td>",
//		ptype.food, ptype.shield, ptype.trade);
//
//	fprintf(doc, "<td>%s%s%s %s</td>", IMAGE_BEGIN,
//		ptype.special[0].graphic_str, IMAGE_END,
//		ptype.special_1_name);
//	fprintf(doc, "<td>%d / %d / %d</td>",
//		ptype.food_special_1, ptype.shield_special_1,
//		ptype.trade_special_1);
//
//	fprintf(doc, "<td>%s%s%s", IMAGE_BEGIN,
//		ptype.special[1].graphic_str, IMAGE_END);
//	fprintf(doc, " %s</td>", ptype.special_2_name);
//	fprintf(doc, "<td>%d / %d / %d</td>",
//		ptype.food_special_2, ptype.shield_special_2,
//		ptype.trade_special_2);
//
//	fprintf(doc, "<td>%d</td>\n", ptype.movement_cost);
//	fprintf(doc, "<td>%d0%%</td><td>%d</td><td>%d</td><td>%d</td>\n",
//		ptype.defense_bonus, ptype.road_trade_incr,
//		ptype.irrigation_food_incr, ptype.mining_shield_incr);
//	fprintf(doc, "<td>%s</td></tr>\n\n",
//		get_terrain_name(ptype.transform_result));
//      } terrain_type_iterate_end;
//      fprintf(doc, "</table><br><br><br><table border=1>");
//      fprintf(doc, "<caption>Time to perform action</caption>");
//      fprintf(doc, "<tr><th>Terrain</th>\n");
//      fprintf(doc, "<th>Road</th><th>Irrigation</th>\n");
//      fprintf(doc, "<th>Mining</th><th>Rail</th>\n");
//      fprintf(doc, "<th>Airbase</th><th>Fortress</th>\n");
//      fprintf(doc, "<th>Clean pollution</th><th>Clean fallout</th>\n");
//      fprintf(doc, "<th>Transform</th></tr>\n");
//      terrain_type_iterate(id) {
//	final String name = get_terrain_name(id);
//	tile_type ptype = get_tile_type(id);
//
//	if (ptype.terrain_name[0] == '\0') {
//	  /* Must be a disabled piece of terrain */
//	  continue;
//	}
//
//	fprintf(doc, "<tr><td>%s%s%s %s</td><td>%d</td>\n",
//		IMAGE_BEGIN, ptype.graphic_str, IMAGE_END, name,
//		ptype.road_time);
//	fprintf(doc, "<td>%d</td><td>%d</td><td>%d</td><td>%d</td>\n",
//		ptype.irrigation_time, ptype.mining_time,
//		ptype.rail_time, ptype.airbase_time);
//	fprintf(doc, "<td>%d</td><td>%d</td><td>%d</td><td>%d</td>\n",
//		ptype.fortress_time, ptype.clean_pollution_time,
//		ptype.clean_fallout_time, ptype.transform_time);
//	fprintf(doc, "</tr>\n\n");
//      } terrain_type_iterate_end;
//
//      fprintf(doc, "</table>\n");
//      break;
//
//    case MANUAL_BUILDINGS:
//      fprintf(doc, "<h1>Freeciv %s buildings help</h1>\n\n", VERSION_STRING);
//      for (int id = 0; id < Game.game.num_impr_types; id++) {
//	impr_type pimpr = get_improvement_type(id);
//	char buf[64000];
//
//	if (pimpr.is_wonder) {
//	  continue;
//	}
//
//	helptext_building(buf, sizeof(buf), id, null);
//	fprintf(doc, "%s%s%s\n\n", SECTION_BEGIN, Improvement.get_improvement_name(id),
//		SECTION_END);
//	fprintf(doc, "<table>\n");
//	fprintf(doc, "<tr><td>Cost: <td>%d</tr>\n", pimpr.build_cost);
//	fprintf(doc, "<tr><td>Upkeep: <td>%d</tr>\n", pimpr.upkeep);
//	if (tech_exists(pimpr.tech_req)) {
//	  fprintf(doc, "<tr><td>Tech required: <td>%s</tr>\n",
//		  advances[pimpr.tech_req].name);
//	}
//	if (tech_exists(pimpr.obsolete_by)) {
//	  fprintf(doc, "<tr><td>Obsoleted by: <td>%s</tr>\n",
//		  advances[pimpr.obsolete_by].name);
//	}
//	fprintf(doc, "</table>\n\n");
//	fprintf(doc, "<pre>%s</pre>\n\n", buf);
//      } ;
//      break;
//
//    case MANUAL_WONDERS:
//      fprintf(doc, "<h1>Freeciv %s wonders help</h1>\n\n", VERSION_STRING);
//      for (int id = 0; id < Game.game.num_impr_types; id++) {
//	impr_type pimpr = get_improvement_type(id);
//	char buf[64000];
//
//	if (!pimpr.is_wonder) {
//	  continue;
//	}
//
//	helptext_building(buf, sizeof(buf), id, null);
//	fprintf(doc, "%s%s%s\n\n", SECTION_BEGIN, Improvement.get_improvement_name(id),
//		SECTION_END);
//	fprintf(doc, "<table>\n");
//	fprintf(doc, "<tr><td>Cost: <td>%d</tr>\n", pimpr.build_cost);
//	fprintf(doc, "<tr><td>Upkeep: <td>%d</tr>\n", pimpr.upkeep);
//	if (tech_exists(pimpr.tech_req)) {
//	  fprintf(doc, "<tr><td>Tech required: <td>%s</tr>\n",
//		  advances[pimpr.tech_req].name);
//	}
//	if (tech_exists(pimpr.obsolete_by)) {
//	  fprintf(doc, "<tr><td>Obsoleted by: <td>%s</tr>\n",
//		  advances[pimpr.obsolete_by].name);
//	}
//	fprintf(doc, "</table>\n\n");
//	fprintf(doc, "<pre>%s</pre>\n\n", buf);
//      } ;
//      break;
//
//    case MANUAL_COUNT:
//      break;
//
//    } /* switch */
//
//    fprintf(doc, TAIL);
//    fclose(doc);
//  } /* manuals */
//
//  return true;
//}
//
//int main(int argc, char **argv)
//{
//  init_nls();
//  init_character_encodings("UTF-8", false);
//
//  manual_command();
//
//  return 0;
//}
}