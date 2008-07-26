package server;

import common.game.Game_H;

public class Settings{

// Freeciv - Copyright (C) 1996-2004 - The Freeciv Project
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
//#include "fcintl.h"
//
//#include "map.h"
//
//#include "Gamelog.gamelog.h"
//#include "report.h"
//#include "settings.h"
//#include "stdinhand.h"
//
///* Category names must match the values in enum sset_category. */
//final String sset_category_names[] = {N"Geological",
//				     N"Ecological",
//				     N"Sociological",
//				     N"Economic",
//				     N"Military",
//				     N"Scientific",
//				     N"Internal",
//				     N"Networking"};
//
///* Level names must match the values in enum sset_level. */
//final String sset_level_names[] = {N"None",
//				  N"All",
//				  N"Vital",
//				  N"Situational",
//				  N"Rare"};
//final int OLEVELS_NUM = ARRAY_SIZE(sset_level_names);
//
//
///**************************************************************************
//  A callback invoked when autotoggle is set.
//**************************************************************************/
//static boolean autotoggle_callback(boolean value, final String*reject_message)
//{
//  reject_message = null;
//  if (!value) {
//    return true;
//  }
//
//  for(player pplayer: Game.game.players){
//    if (!pplayer.ai.control && !pplayer.is_connected) {
//      toggle_ai_player_direct(null, pplayer);
//    }
//  }
//
//  return true;
//}
//
///*************************************************************************
//  Verify that a given allowtake string is valid.  See
//  Game.game.allow_take.
//*************************************************************************/
//static boolean allowtake_callback(final String value, final String*error_string)
//{
//  int len = value.length(), i;
//  boolean havecharacter_state = false;
//
//  /* We check each character individually to see if it's valid.  This
//   * does not check for duplicate entries.
//   *
//   * We also track the state of the machine.  havecharacter_state is
//   * true if the preceeding character was a primary label, e.g.
//   * NHhAadb.  It is false if the preceeding character was a modifier
//   * or if this is the first character. */
//
//  for (i = 0; i < len; i++) {
//    /* Check to see if the character is a primary label. */
//    if (strchr("HhAadbOo", value[i])) {
//      havecharacter_state = true;
//      continue;
//    }
//
//    /* If we've already passed a primary label, check to see if the
//     * character is a modifier. */
//    if (havecharacter_state && strchr("1234", value[i])) {
//      havecharacter_state = false;
//      continue;
//    }
//
//    /* Looks like the character was invalid. */
//    *error_string = ("Allowed take string contains invalid\n" +
//		      "characters.  Try \"help allowtake\".");
//    return false;
//  }
//
//  /* All characters were valid. */
//  *error_string = null;
//  return true;
//}
//
///*************************************************************************
//  Verify that a given startunits string is valid.  See
//  Game.game.start_units.
//*************************************************************************/
//static boolean startunits_callback(final String value, final String*error_string)
//{
//  int len = value.length(), i;
//  boolean have_founder = false;
//
//  /* We check each character individually to see if it's valid, and
//   * also make sure there is at least one city founder. */
//
//  for (i = 0; i < len; i++) {
//    /* Check for a city founder */
//    if (value[i] == 'c') {
//      have_founder = true;
//      continue;
//    }
//    if (strchr("cwxksdDaA", value[i])) {
//      continue;
//    }
//
//    /* Looks like the character was invalid. */
//    *error_string = ("Starting units string contains invalid\n" +
//		      "characters.  Try \"help startunits\".");
//    return false;
//  }
//
//  if (!have_founder) {
//    *error_string = ("Starting units string does not contain\n" +
//		      "at least one city founder.  Try \n" +
//		      "\"help startunits\".");
//    return false;
//  }
//  /* All characters were valid. */
//  *error_string = null;
//  return true;
//}
//
///*************************************************************************
//  Verify that a given maxplayers string is valid.
//*************************************************************************/
//static boolean maxplayers_callback(int value, final String*error_string)
//{
//  if (value < Game.game.nplayers) {
//    *error_string =("Number of players is higher than requested value; " +
//		     "Keeping old value.");
//    return false;
//  }
//
//  error_string = null;
//  return true;
//}
//
//#define GEN_BOOL(name, value, sclass, scateg, slevel, to_client,	\
//		 short_help, extra_help, func, default)			\
//  {name, sclass, to_client, short_help, extra_help, SSET_BOOL,		\
//      scateg, slevel, &value, default, func,				\
//      null, 0, null, 0, 0,						\
//      null, null, null, 0},
//
//#define GEN_INT(name, value, sclass, scateg, slevel, to_client,		\
//		short_help, extra_help, func, min, max, default)	\
//  {name, sclass, to_client, short_help, extra_help, SSET_INT,		\
//      scateg, slevel,							\
//      null, false, null,						\
//      &value, default, func, min, max,					\
//      null, null, null, 0},
//
//#define GEN_STRING(name, value, sclass, scateg, slevel, to_client,	\
//		   short_help, extra_help, func, default)		\
//  {name, sclass, to_client, short_help, extra_help, SSET_STRING,	\
//      scateg, slevel,							\
//      null, false, null,						\
//      null, 0, null, 0, 0,						\
//      value, default, func, sizeof(value)},
//
//#define GEN_END							\
//  {null, SSET_LAST, SSET_SERVER_ONLY, null, null, SSET_INT,	\
//      SSET_NUM_CATEGORIES, SSET_NONE,				\
//      null, false, null,					\
//      null, 0, null, 0, 0,					\
//      null, null, null},
//
//struct settings_s settings[] = {
//
//  /* These should be grouped by sclass */
//  
//  /* Map size parameters: adjustable if we don't yet have a map */  
//  GEN_INT("size", Map.map.size, SSET_MAP_SIZE,
//	  SSET_GEOLOGY, SSET_VITAL, SSET_TO_CLIENT,
//          N"Map size (in thousands of tiles)",
//          N("This value is used to determine the map dimensions.\n" +
//             "  size = 4 is a normal map of 4,000 tiles (default)\n" +
//             "  size = 20 is a huge map of 20,000 tiles"), null,
//          MAP_MIN_SIZE, MAP_MAX_SIZE, MAP_DEFAULT_SIZE)
//  GEN_INT("topology", Map.map.topology_id, SSET_MAP_SIZE,
//	  SSET_GEOLOGY, SSET_VITAL, SSET_TO_CLIENT,
//	  N"Map topology index",
//	  /* TRANS: do not edit the ugly ASCII art */
//	  N("Freeciv maps are always two-dimensional. They may wrap at " +
//	     "the north-south and east-west directions to form a flat map, " +
//	     "a cylinder, or a torus (donut). Individual tiles may be " +
//	     "rectangular or hexagonal, with either a classic or isometric " +
//	     "alignment - this should be set based on the tileset being " +
//	     "used.\n" +
//             "   0 Flat Earth (unwrapped)\n" +
//             "   1 Earth (wraps E-W)\n" +
//             "   2 Uranus (wraps N-S)\n" +
//             "   3 Donut World (wraps N-S, E-W)\n" +
//	     "   4 Flat Earth (isometric)\n" +
//	     "   5 Earth (isometric)\n" +
//	     "   6 Uranus (isometric)\n" +
//	     "   7 Donut World (isometric)\n" +
//	     "   8 Flat Earth (hexagonal)\n" +
//	     "   9 Earth (hexagonal)\n" +
//	     "  10 Uranus (hexagonal)\n" +
//	     "  11 Donut World (hexagonal)\n" +
//	     "  12 Flat Earth (iso-hex)\n" +
//	     "  13 Earth (iso-hex)\n" +
//	     "  14 Uranus (iso-hex)\n" +
//	     "  15 Donut World (iso-hex)\n" +
//	     "Classic rectangular:       Isometric rectangular:\n" +
//	     "      _________               /\\/\\/\\/\\/\\ \n" +
//	     "     |_|_|_|_|_|             /\\/\\/\\/\\/\\/ \n" +
//	     "     |_|_|_|_|_|             \\/\\/\\/\\/\\/\\\n" +
//	     "     |_|_|_|_|_|             /\\/\\/\\/\\/\\/ \n" +
//	     "                             \\/\\/\\/\\/\\/  \n" +
//	     "Hex tiles:                 Iso-hex:\n" +
//	     "  /\\/\\/\\/\\/\\/\\               _   _   _   _   _       \n" +
//	     "  | | | | | | |             / \\_/ \\_/ \\_/ \\_/ \\      \n" +
//	     "  \\/\\/\\/\\/\\/\\/\\             \\_/ \\_/ \\_/ \\_/ \\_/  \n" +
//	     "   | | | | | | |            / \\_/ \\_/ \\_/ \\_/ \\      \n" +
//	     "   \\/\\/\\/\\/\\/\\/             \\_/ \\_/ \\_/ \\_/ \\_/    \n"
//          ), null,
//	  MAP_MIN_TOPO, MAP_MAX_TOPO, MAP_DEFAULT_TOPO)
//
//  /* Map generation parameters: once we have a map these are of historical
//   * interest only, and cannot be changed.
//   */
//  GEN_INT("generator", Map.map.generator,
//	  SSET_MAP_GEN, SSET_GEOLOGY, SSET_VITAL,  SSET_TO_CLIENT,
//	  N"Method used to generate map",
//	  N("0 = Scenario map - no generator;\n" +
//	     "1 = Fully random height generator;              [4]\n" +
//	     "2 = Pseudo-fractal height generator;             [3]\n" +
//	     "3 = Island-based generator (fairer but boring)   [1]\n" +
//	     "\n" +
//	     "Numbers in [] give the default values for placement of " +
//	     "starting positions.  If the default value of startpos is " +
//	     "used then a startpos setting will be chosen based on the " +
//	     "generator.  See the \"startpos\" setting."), null,
//	  MAP_MIN_GENERATOR, MAP_MAX_GENERATOR, MAP_DEFAULT_GENERATOR)
//
//  GEN_INT("startpos", Map.map.startpos,
//	  SSET_MAP_GEN, SSET_GEOLOGY, SSET_VITAL,  SSET_TO_CLIENT,
//	  N"Method used to choose start positions",
//	  N("0 = Generator's choice.  Selecting this setting means\n" +
//	     "    the default value will be picked based on the generator\n" +
//	     "    chosen.  See the \"generator\" setting.\n" +
//	     "1 = Try to place one player per continent.\n" +
//	     "2 = Try to place two players per continent.\n" +
//	     "3 = Try to place all players on a single continent.\n" +
//	     "4 = Place players depending on size of continents.\n" +
//	     "Note: generators try to create the right number of continents " +
//	     "for the choice of start pos and to the number of players"),
//	  null, MAP_MIN_STARTPOS, MAP_MAX_STARTPOS, MAP_DEFAULT_STARTPOS)
//
//  GEN_BOOL("tinyisles", Map.map.tinyisles,
//	   SSET_MAP_GEN, SSET_GEOLOGY, SSET_RARE, SSET_TO_CLIENT,
//	   N"Presence of 1x1 islands",
//	   N"0 = no 1x1 islands; 1 = some 1x1 islands", null,
//	   MAP_DEFAULT_TINYISLES)
//
//  GEN_BOOL("separatepoles", Map.map.separatepoles,
//	   SSET_MAP_GEN, SSET_GEOLOGY, SSET_SITUATIONAL, SSET_TO_CLIENT,
//	   N"Whether the poles are separate continents",
//	   N("0 = continents may attach to poles; 1 = poles will " +
//	      "usually be separate"), null, 
//	   MAP_DEFAULT_SEPARATE_POLES)
//
//  GEN_BOOL("alltemperate", Map.map.alltemperate, 
//           SSET_MAP_GEN, SSET_GEOLOGY, SSET_RARE, SSET_TO_CLIENT,
//	   N"All the map is temperate",
//	   N"0 = normal Earth-like planet; 1 = all-temperate planet ",
//	   null, MAP_DEFAULT_ALLTEMPERATE)
//
//  GEN_INT("temperature", Map.map.temperature,
// 	  SSET_MAP_GEN, SSET_GEOLOGY, SSET_SITUATIONAL, SSET_TO_CLIENT,
// 	  N"Average temperature of the planet",
// 	  N("Small values will give a cold map, while larger values will " +
//             "give a hotter Map.map.\n" +
//	     "\n" +
//	     "100 means a very dry and hot planet with no polar arctic " +
//	     "zones, only tropical and dry zones.\n\n" +
//	     "70 means a hot planet with little polar ice.\n\n" +
//             "50 means a temperate planet with normal polar, cold, " +
//	     "temperate, and tropical zones; a desert zone overlaps " +
//	     "tropical and temperate zones.\n\n" +
//	     "30 means a cold planet with small tropical zones.\n\n" +
//	     "0 means a very cold planet with large polar zones and no " +
//	     "tropics"), 
//          null,
//  	  MAP_MIN_TEMPERATURE, MAP_MAX_TEMPERATURE, MAP_DEFAULT_TEMPERATURE)
// 
//  GEN_INT("landmass", Map.map.landpercent,
//	  SSET_MAP_GEN, SSET_GEOLOGY, SSET_SITUATIONAL, SSET_TO_CLIENT,
//	  N"Percentage of the map that is land",
//	  N("This setting gives the approximate percentage of the map " +
//	     "that will be made into land."), null,
//	  MAP_MIN_LANDMASS, MAP_MAX_LANDMASS, MAP_DEFAULT_LANDMASS)
//
//  GEN_INT("steepness", Map.map.steepness,
//	  SSET_MAP_GEN, SSET_GEOLOGY, SSET_SITUATIONAL, SSET_TO_CLIENT,
//	  N"Amount of hills/mountains",
//	  N("Small values give flat maps, while higher values give a " +
//	     "steeper map with more hills and mountains."), null,
//	  MAP_MIN_STEEPNESS, MAP_MAX_STEEPNESS, MAP_DEFAULT_STEEPNESS)
//
//  GEN_INT("wetness", Map.map.wetness,
// 	  SSET_MAP_GEN, SSET_GEOLOGY, SSET_SITUATIONAL, SSET_TO_CLIENT,
// 	  N"Amount of water on lands", 
//	  N("Small values mean lots of dry, desert-like land; " +
//	     "higher values give a wetter map with more swamps, " +
//	     "jungles, and rivers."), null, 
// 	  MAP_MIN_WETNESS, MAP_MAX_WETNESS, MAP_DEFAULT_WETNESS)
//
//  GEN_INT("mapseed", Map.map.seed,
//	  SSET_MAP_GEN, SSET_INTERNAL, SSET_RARE, SSET_SERVER_ONLY,
//	  N"Map generation random seed",
//	  N("The same seed will always produce the same map; " +
//	     "for zero (the default) a seed will be chosen based on " +
//	     "the time to give a random Map.map. This setting is usually " +
//	     "only of interest while debugging the Game.game."), null, 
//	  MAP_MIN_SEED, MAP_MAX_SEED, MAP_DEFAULT_SEED)
//
//  /* Map additional stuff: huts and specials.  gameseed also goes here
//   * because huts and specials are the first time the gameseed gets used (?)
//   * These are done when the Game.game starts, so these are historical and
//   * fixed after the Game.game has started.
//   */
//  GEN_INT("gameseed", Game.game.seed,
//	  SSET_MAP_ADD, SSET_INTERNAL, SSET_RARE, SSET_SERVER_ONLY,
//	  N"Game random seed",
//	  N("For zero (the default) a seed will be chosen based " +
//	     "on the time. This setting is usually " +
//	     "only of interest while debugging the Game.game"), null, 
//	  GAME_MIN_SEED, GAME_MAX_SEED, GAME_DEFAULT_SEED)
//
//  GEN_INT("specials", Map.map.riches,
//	  SSET_MAP_ADD, SSET_GEOLOGY, SSET_VITAL, SSET_TO_CLIENT,
//	  N("Amount of \"special\" resource squares"), 
//	  N("Special resources improve the basic terrain type they " +
//	     "are on. The server variable's scale is parts per " +
//	     "thousand."), null,
//	  MAP_MIN_RICHES, MAP_MAX_RICHES, MAP_DEFAULT_RICHES)
//
//  GEN_INT("huts", Map.map.huts,
//	  SSET_MAP_ADD, SSET_GEOLOGY, SSET_VITAL, SSET_TO_CLIENT,
//	  N"Amount of huts (minor tribe villages)",
//	  N("This setting gives the exact number of huts that will be " +
//	     "placed on the entire Map.map. Huts are small tribal villages " +
//	     "that may be investiged by units."), null,
//	  MAP_MIN_HUTS, MAP_MAX_HUTS, MAP_DEFAULT_HUTS)
//
//  /* Options affecting numbers of players and AI players.  These only
//   * affect the start of the Game.game and can not be adjusted after that.
//   * (Actually, minplayers does also affect reloads: you can't start a
//   * reload Game.game until enough players have connected (or are AI).)
//   */
//  GEN_INT("minplayers", Game.game.min_players,
//	  SSET_PLAYERS, SSET_INTERNAL, SSET_VITAL,
//          SSET_TO_CLIENT,
//	  N"Minimum number of players",
//	  N("There must be at least this many players (connected " +
//	     "human players or AI players) before the Game.game can start."),
//	  null,
//	  GAME_MIN_MIN_PLAYERS, GAME_MAX_MIN_PLAYERS, GAME_DEFAULT_MIN_PLAYERS)
//
//  GEN_INT("maxplayers", Game.game.max_players,
//	  SSET_PLAYERS, SSET_INTERNAL, SSET_VITAL, SSET_TO_CLIENT,
//	  N"Maximum number of players",
//          N("The maximal number of human and AI players who can be in " +
//             "the Game.game. When this number of players are connected in " +
//             "the pregame state, any new players who try to connect " +
//             "will be rejected."), maxplayers_callback,
//	  GAME_MIN_MAX_PLAYERS, GAME_MAX_MAX_PLAYERS, GAME_DEFAULT_MAX_PLAYERS)
//
//  GEN_INT("aifill", Game.game.aifill,
//	  SSET_PLAYERS, SSET_INTERNAL, SSET_VITAL, SSET_TO_CLIENT,
//	  N"Total number of players (including AI players)",
//	  N("If there are fewer than this many players when the " +
//	     "Game.game starts, extra AI players will be created to " +
//	     "increase the total number of players to the value of " +
//	     "this option."), null, 
//	  GAME_MIN_AIFILL, GAME_MAX_AIFILL, GAME_DEFAULT_AIFILL)
//
//  /* Game initialization parameters (only affect the first start of the Game.game,
//   * and not reloads).  Can not be changed after first start of Game.game.
//   */
//  GEN_STRING("startunits", Game.game.start_units,
//	     SSET_GAME_INIT, SSET_SOCIOLOGY, SSET_VITAL, SSET_TO_CLIENT,
//             N"List of players' initial units",
//             N("This should be a string of characters, each of which " +
//		"specifies a unit role. There must be at least one city " +
//		"founder in the string. The characters and their " +
//		"meanings are:\n" +
//		"    c   = City founder (eg., Settlers)\n" +
//		"    w   = Terrain worker (eg., Engineers)\n" +
//		"    x   = Explorer (eg., Explorer)\n" +
//		"    k   = Gameloss (eg., King)\n" +
//		"    s   = Diplomat (eg., Diplomat)\n" +
//		"    d   = Ok defense unit (eg., Warriors)\n" +
//		"    D   = Good defense unit (eg., Phalanx)\n" +
//		"    a   = Fast attack unit (eg., Horsemen)\n" +
//		"    A   = Strong attack unit (eg., Catapult)\n"),
//		startunits_callback, GAME_DEFAULT_START_UNITS)
//
//  GEN_INT("dispersion", Game.game.dispersion,
//	  SSET_GAME_INIT, SSET_SOCIOLOGY, SSET_SITUATIONAL, SSET_TO_CLIENT,
//	  N"Area where initial units are located",
//	  N("This is the radius within " +
//	     "which the initial units are dispersed."), null,
//	  GAME_MIN_DISPERSION, GAME_MAX_DISPERSION, GAME_DEFAULT_DISPERSION)
//
//  GEN_INT("gold", Game.game.gold,
//	  SSET_GAME_INIT, SSET_ECONOMICS, SSET_VITAL, SSET_TO_CLIENT,
//	  N"Starting gold per player", 
//	  N("At the beginning of the Game.game, each player is given this " +
//	     "much gold."), null,
//	  GAME_MIN_GOLD, GAME_MAX_GOLD, GAME_DEFAULT_GOLD)
//
//  GEN_INT("techlevel", Game.game.tech,
//	  SSET_GAME_INIT, SSET_SCIENCE, SSET_VITAL, SSET_TO_CLIENT,
//	  N"Number of initial techs per player", 
//	  N("At the beginning of the Game.game, each player is given this " +
//	     "many technologies. The technologies chosen are random for " +
//	     "each player."), null,
//	  GAME_MIN_TECHLEVEL, GAME_MAX_TECHLEVEL, GAME_DEFAULT_TECHLEVEL)
//
//  GEN_INT("researchcost", Game.game.researchcost,
//	  SSET_RULES, SSET_SCIENCE, SSET_SITUATIONAL, SSET_TO_CLIENT,
//	  N"Points required to gain a new tech",
//	  N("This affects how quickly players can research new " +
//	     "technology. Doubling its value will make all technologies " +
//	     "take twice as long to research."), null,
//	  GAME_MIN_RESEARCHCOST, GAME_MAX_RESEARCHCOST, 
//	  GAME_DEFAULT_RESEARCHCOST)
//
//  GEN_INT("techpenalty", Game.game.techpenalty,
//	  SSET_RULES, SSET_SCIENCE, SSET_RARE, SSET_TO_CLIENT,
//	  N"Percentage penalty when changing tech",
//	  N("If you change your current research technology, and you have " +
//	     "positive research points, you lose this percentage of those " +
//	     "research points. This does not apply if you have just gained " +
//	     "a technology this turn."), null,
//	  GAME_MIN_TECHPENALTY, GAME_MAX_TECHPENALTY,
//	  GAME_DEFAULT_TECHPENALTY)
//
//  GEN_INT("diplcost", Game.game.diplcost,
//	  SSET_RULES, SSET_SCIENCE, SSET_RARE, SSET_TO_CLIENT,
//	  N"Penalty when getting tech from treaty",
//	  N("For each technology you gain from a diplomatic treaty, you " +
//	     "lost research points equal to this percentage of the cost to " +
//	     "research a new technology. You can end up with negative " +
//	     "research points if this is non-zero."), null, 
//	  GAME_MIN_DIPLCOST, GAME_MAX_DIPLCOST, GAME_DEFAULT_DIPLCOST)
//
//  GEN_INT("conquercost", Game.game.conquercost,
//	  SSET_RULES, SSET_SCIENCE, SSET_RARE, SSET_TO_CLIENT,
//	  N"Penalty when getting tech from conquering",
//	  N("For each technology you gain by conquering an enemy city, you " +
//	     "lose research points equal to this percentage of the cost " +
//	     "to research a new technology. You can end up with negative " +
//	     "research points if this is non-zero."), null,
//	  GAME_MIN_CONQUERCOST, GAME_MAX_CONQUERCOST,
//	  GAME_DEFAULT_CONQUERCOST)
//
//  GEN_INT("freecost", Game.game.freecost,
//	  SSET_RULES, SSET_SCIENCE, SSET_RARE, SSET_TO_CLIENT,
//	  N"Penalty when getting a free tech",
//	  N("For each technology you gain \"for free\" (other than " +
//	     "covered by diplcost or conquercost: specifically, from huts " +
//	     "or from Great Library effects), you lose research points " +
//	     "equal to this percentage of the cost to research a new " +
//	     "technology. You can end up with negative research points if " +
//	     "this is non-zero."), 
//	  null, 
//	  GAME_MIN_FREECOST, GAME_MAX_FREECOST, GAME_DEFAULT_FREECOST)
//
//  GEN_INT("foodbox", Game.game.foodbox,
//	  SSET_RULES, SSET_ECONOMICS, SSET_SITUATIONAL, SSET_TO_CLIENT,
//	  N"Food required for a city to grow",
//	  N("This is the base amount of food required to grow a city. " +
//	     "This value is multiplied by another factor that comes from " +
//	     "the ruleset and is dependent on the size of the city."),
//	  null,
//	  GAME_MIN_FOODBOX, GAME_MAX_FOODBOX, GAME_DEFAULT_FOODBOX)
//
//  GEN_INT("aqueductloss", Game.game.aqueductloss,
//	  SSET_RULES, SSET_ECONOMICS, SSET_RARE, SSET_TO_CLIENT,
//	  N"Percentage food lost when need aqueduct",
//	  N("If a city would expand, but it can't because it needs " +
//	     "an Aqueduct (or Sewer System), it loses this percentage " +
//	     "of its foodbox (or half that amount if it has a " +
//	     "Granary)."), null, 
//	  GAME_MIN_AQUEDUCTLOSS, GAME_MAX_AQUEDUCTLOSS, 
//	  GAME_DEFAULT_AQUEDUCTLOSS)
//
//  /* Notradesize and fulltradesize used to have callbacks to prevent them
//   * from being set illegally (notradesize > fulltradesize).  However this
//   * provided a problem when setting them both through the client's settings
//   * dialog, since they cannot both be set atomically.  So the callbacks were
//   * removed and instead the Game.game now knows how to deal with invalid
//   * settings. */
//  GEN_INT("fulltradesize", Game.game.fulltradesize,
//	  SSET_RULES, SSET_ECONOMICS, SSET_RARE, SSET_TO_CLIENT,
//	  N"Minimum city size to get full trade",
//	  N("There is a trade penalty in all cities smaller than this. " +
//	     "The penalty is 100% (no trade at all) for sizes up to " +
//	     "notradesize, and decreases gradually to 0% (no penalty " +
//	     "except the normal corruption) for size=fulltradesize. " +
//	     "See also notradesize."), null, 
//	  GAME_MIN_FULLTRADESIZE, GAME_MAX_FULLTRADESIZE, 
//	  GAME_DEFAULT_FULLTRADESIZE)
//
//  GEN_INT("notradesize", Game.game.notradesize,
//	  SSET_RULES, SSET_ECONOMICS, SSET_RARE, SSET_TO_CLIENT,
//	  N"Maximum size of a city without trade",
//	  N("Cities do not produce any trade at all unless their size " +
//	     "is larger than this amount. The produced trade increases " +
//	     "gradually for cities larger than notradesize and smaller " +
//	     "than fulltradesize. See also fulltradesize."), null,
//	  GAME_MIN_NOTRADESIZE, GAME_MAX_NOTRADESIZE,
//	  GAME_DEFAULT_NOTRADESIZE)
//
//  GEN_INT("unhappysize", Game.game.unhappysize,
//	  SSET_RULES, SSET_SOCIOLOGY, SSET_RARE, SSET_TO_CLIENT,
//	  N"City size before people become unhappy",
//	  N("Before other adjustments, the first unhappysize citizens in a " +
//	     "city are content, and subsequent citizens are unhappy. " +
//	     "See also cityfactor."), null,
//	  GAME_MIN_UNHAPPYSIZE, GAME_MAX_UNHAPPYSIZE,
//	  GAME_DEFAULT_UNHAPPYSIZE)
//
//  GEN_BOOL("angrycitizen", Game.game.angrycitizen,
//	   SSET_RULES, SSET_SOCIOLOGY, SSET_SITUATIONAL, SSET_TO_CLIENT,
//	  N"Whether angry citizens are enabled",
//	  N("Introduces angry citizens like in civilization II. Angry " +
//	     "citizens have to become unhappy before any other class " +
//	     "of citizens may be considered. See also unhappysize, " +
//	     "cityfactor and governments."), null, 
//	  GAME_DEFAULT_ANGRYCITIZEN)
//
//  GEN_INT("cityfactor", Game.game.cityfactor,
//	  SSET_RULES, SSET_SOCIOLOGY, SSET_RARE, SSET_TO_CLIENT,
//	  N"Number of cities for higher unhappiness",
//	  N("When the number of cities a player owns is greater than " +
//	     "cityfactor, one extra citizen is unhappy before other " +
//	     "adjustments; see also unhappysize. This assumes a " +
//	     "Democracy; for other governments the effect occurs at " +
//	     "smaller numbers of cities."), null, 
//	  GAME_MIN_CITYFACTOR, GAME_MAX_CITYFACTOR, GAME_DEFAULT_CITYFACTOR)
//
//  GEN_INT("citymindist", Game.game.citymindist,
//	  SSET_RULES, SSET_SOCIOLOGY, SSET_SITUATIONAL, SSET_TO_CLIENT,
//	  N"Minimum distance between cities",
//	  N("When a player founds a new city, it is checked if there is " +
//	     "no other city in citymindist distance. For example, if " +
//	     "citymindist is 3, there have to be at least two empty " +
//	     "fields between two cities in every direction. If set " +
//	     "to 0 (default), the ruleset value will be used."),
//	  null,
//	  GAME_MIN_CITYMINDIST, GAME_MAX_CITYMINDIST,
//	  GAME_DEFAULT_CITYMINDIST)
//
//  GEN_INT("rapturedelay", Game.game.rapturedelay,
//	  SSET_RULES, SSET_SOCIOLOGY, SSET_SITUATIONAL, SSET_TO_CLIENT,
//          N"Number of turns between rapture effect",
//          N("Sets the number of turns between rapture growth of a city. " +
//             "If set to n a city will grow after celebrating for n+1 " +
//	     "turns."),
//	  null,
//          GAME_MIN_RAPTUREDELAY, GAME_MAX_RAPTUREDELAY,
//          GAME_DEFAULT_RAPTUREDELAY)
//
//  GEN_INT("razechance", Game.game.razechance,
//	  SSET_RULES, SSET_MILITARY, SSET_RARE, SSET_TO_CLIENT,
//	  N"Chance for conquered building destruction",
//	  N("When a player conquers a city, each city improvement has this " +
//	     "percentage chance to be destroyed."), null, 
//	  GAME_MIN_RAZECHANCE, GAME_MAX_RAZECHANCE, GAME_DEFAULT_RAZECHANCE)
//
//  GEN_INT("civstyle", Game.game.civstyle,
//	  SSET_RULES, SSET_MILITARY, SSET_RARE, SSET_TO_CLIENT,
//          N"civstyle is an obsolete setting",
//          N("This setting is obsolete; it does nothing in the current " +
//	     "version. It will be removed from future versions."), null,
//	  GAME_MIN_CIVSTYLE, GAME_MAX_CIVSTYLE, GAME_DEFAULT_CIVSTYLE)
//
//  GEN_INT("occupychance", Game.game.occupychance,
//	  SSET_RULES, SSET_MILITARY, SSET_RARE, SSET_TO_CLIENT,
//	  N"Chance of moving into tile after attack",
//	  N("If set to 0, combat is Civ1/2-style (when you attack, " +
//	     "you remain in place). If set to 100, attacking units " +
//	     "will always move into the tile they attacked if they win " +
//	     "the combat (and no enemy units remain in the tile). If " +
//	     "set to a value between 0 and 100, this will be used as " +
//	     "the percent chance of \"occupying\" territory."), null, 
//	  GAME_MIN_OCCUPYCHANCE, GAME_MAX_OCCUPYCHANCE, 
//	  GAME_DEFAULT_OCCUPYCHANCE)
//
//  GEN_INT("killcitizen", Game.game.killcitizen,
//	  SSET_RULES, SSET_MILITARY, SSET_RARE, SSET_TO_CLIENT,
//	  N"Reduce city population after attack",
//	  N("This flag indicates if city population is reduced " +
//	     "after successful attack of enemy unit, depending on " +
//	     "its movement type (OR-ed):\n" +
//	     "  1 = land\n" +
//	     "  2 = sea\n" +
//	     "  4 = heli\n" +
//	     "  8 = air"), null,
//	  GAME_MIN_KILLCITIZEN, GAME_MAX_KILLCITIZEN,
//	  GAME_DEFAULT_KILLCITIZEN)
//
//  GEN_INT("wtowervision", Game.game.watchtower_vision,
//	  SSET_RULES, SSET_MILITARY, SSET_RARE, SSET_TO_CLIENT,
//	  N"Range of vision for units in a fortress",
//	  N("If set to 1, it has no effect. " +
//	     "If 2 or larger, the vision range of a unit inside a " +
//	     "fortress is set to this value, if the necessary invention " +
//	     "has been made. This invention is determined by the flag " +
//	     "'Watchtower' in the techs ruleset. See also wtowerevision."), 
//	  null, 
//	  GAME_MIN_WATCHTOWER_VISION, GAME_MAX_WATCHTOWER_VISION, 
//	  GAME_DEFAULT_WATCHTOWER_VISION)
//
//  GEN_INT("wtowerevision", Game.game.watchtower_extra_vision,
//	  SSET_RULES, SSET_MILITARY, SSET_RARE, SSET_TO_CLIENT,
//	  N"Extra vision range for units in a fortress",
//	  N("If set to 0, it has no " +
//	     "effect. If larger than 0, the vision range of a unit is " +
//	     "raised by this value, if the unit is inside a fortress " +
//	     "and the invention determined by the flag 'Watchtower' " +
//	     "in the techs ruleset has been made. Always the larger " +
//	     "value of wtowervision and wtowerevision will be used. " +
//	     "Also see wtowervision."), null, 
//	  GAME_MIN_WATCHTOWER_EXTRA_VISION, GAME_MAX_WATCHTOWER_EXTRA_VISION, 
//	  GAME_DEFAULT_WATCHTOWER_EXTRA_VISION)
//
//  GEN_INT("borders", Game.game.borders,
//	  SSET_RULES, SSET_MILITARY, SSET_SITUATIONAL, SSET_TO_CLIENT,
//	  N"National borders radius",
//	  N("If this is set to greater than 0, then any land tiles " +
//	     "within the given radius of a city will be owned by that " +
//	     "nation. Special rules apply for ocean tiles or tiles within " +
//	     "range of more than one nation's cities."),
//	  null,
//	  GAME_MIN_BORDERS, GAME_MAX_BORDERS, GAME_DEFAULT_BORDERS)
//
//  GEN_BOOL("happyborders", Game.game.happyborders,
//	   SSET_RULES, SSET_MILITARY, SSET_SITUATIONAL,
//	   SSET_TO_CLIENT,
//	   N"Units inside borders cause no unhappiness",
//	   N("If this is set, units will not cause unhappiness when " +
//	      "inside your own borders."), null,
//	   GAME_DEFAULT_HAPPYBORDERS)
//
//  GEN_INT("diplomacy", Game.game.diplomacy,
//	  SSET_RULES, SSET_MILITARY, SSET_SITUATIONAL, SSET_TO_CLIENT,
//	  N"Ability to do diplomacy with other players",
//	  N("0 = default; diplomacy is enabled for everyone.\n\n" +
//	     "1 = diplomacy is only allowed between human players.\n\n" +
//	     "2 = diplomacy is only allowed between AI players.\n\n" +
//             "3 = diplomacy is restricted to teams.\n\n" +
//             "4 = diplomacy is disabled for everyone.\n\n" +
//             "You can always do diplomacy with players on your team."), null,
//	  GAME_MIN_DIPLOMACY, GAME_MAX_DIPLOMACY, GAME_DEFAULT_DIPLOMACY)
//
//  GEN_INT("citynames", Game.game.allowed_city_names,
//	  SSET_RULES, SSET_SOCIOLOGY, SSET_RARE, SSET_TO_CLIENT,
//	  N"Allowed city names",
//	  N("0 = There are no restrictions: players can have " +
//	     "multiple cities with the same names.\n\n" +
//	     "1 = City names have to be unique to a player: " +
//	     "one player can't have multiple cities with the same name.\n\n" +
//	     "2 = City names have to be globally unique: " +
//	     "all cities in a Game.game have to have different names.\n\n" +
//	     "3 = Like setting 2, but a player isn't allowed to use a " +
//	     "default city name of another nations unless it is a default " +
//	     "for their nation also."),
//	  null,
//	  GAME_MIN_ALLOWED_CITY_NAMES, GAME_MAX_ALLOWED_CITY_NAMES, 
//	  GAME_DEFAULT_ALLOWED_CITY_NAMES)
//  
//  /* Flexible rules: these can be changed after the Game.game has started.
//   *
//   * The distinction between "rules" and "flexible rules" is not always
//   * clearcut, and some existing cases may be largely historical or
//   * accidental.  However some generalizations can be made:
//   *
//   *   -- Low-level Game.game mechanics should not be flexible (eg, rulesets).
//   *   -- Options which would affect the Game.game "state" (city production etc)
//   *      should not be flexible (eg, foodbox).
//   *   -- Options which are explicitly sent to the client (eg, in
//   *      packet_game_info) should probably not be flexible, or at
//   *      least need extra care to be flexible.
//   */
//  GEN_INT("barbarians", Game.game.barbarianrate,
//	  SSET_RULES_FLEXIBLE, SSET_MILITARY, SSET_VITAL, SSET_TO_CLIENT,
//	  N"Barbarian appearance frequency",
//	  N("0 = no barbarians \n" +
//	     "1 = barbarians only in huts \n" +
//	     "2 = normal rate of barbarian appearance \n" +
//	     "3 = frequent barbarian uprising \n" +
//	     "4 = raging hordes, lots of barbarians"), null, 
//	  GAME_MIN_BARBARIANRATE, GAME_MAX_BARBARIANRATE, 
//	  GAME_DEFAULT_BARBARIANRATE)
//
//  GEN_INT("onsetbarbs", Game.game.onsetbarbarian,
//	  SSET_RULES_FLEXIBLE, SSET_MILITARY, SSET_VITAL, SSET_TO_CLIENT,
//	  N"Barbarian onset year",
//	  N"Barbarians will not appear before this year.", null,
//	  GAME_MIN_ONSETBARBARIAN, GAME_MAX_ONSETBARBARIAN, 
//	  GAME_DEFAULT_ONSETBARBARIAN)
//
//  GEN_INT("revolen", Game.game.revolution_length,
//	  SSET_RULES_FLEXIBLE, SSET_SOCIOLOGY, SSET_RARE, SSET_TO_CLIENT,
//	  N"Length in turns of revolution",
//	  N("When changing governments, a period of anarchy lasting this " +
//	     "many turns will occur. " +
//             "Setting this value to 0 will give a random " +
//             "length of 1-6 turns."), null, 
//	  GAME_MIN_REVOLUTION_LENGTH, GAME_MAX_REVOLUTION_LENGTH, 
//	  GAME_DEFAULT_REVOLUTION_LENGTH)
//
//  GEN_BOOL("fogofwar", Game.game.fogofwar,
//	   SSET_RULES_FLEXIBLE, SSET_MILITARY, SSET_RARE, SSET_TO_CLIENT,
//	   N"Whether to enable fog of war",
//	   N("If this is set to 1, only those units and cities within " +
//	      "the vision range of your own units and cities will be " +
//	      "revealed to you. You will not see new cities or terrain " +
//	      "changes in tiles not observed."), null, 
//	   GAME_DEFAULT_FOGOFWAR)
//
//  GEN_INT("diplchance", Game.game.diplchance,
//	  SSET_RULES_FLEXIBLE, SSET_MILITARY, SSET_SITUATIONAL, SSET_TO_CLIENT,
//	  N"Chance in diplomat/spy contests",
//	  /* xgettext:no-c-format */
//	  N("A diplomatic unit acting against a city which has one or " +
//	     "more defending diplomatic units has a diplchance " +
//	     "(percent) chance to defeat each such defender. Also, the " +
//	     "chance of a spy returning from a successful mission is " +
//	     "diplchance percent (diplomats never return).  This value is " +
//	     "also the basic chance of success for diplomats and spies. " +
//	     "Defending spies are generally twice as capable as " +
//	     "diplomats; veteran units are 50% more capable than " +
//	     "non-veteran ones."), null, 
//	  GAME_MIN_DIPLCHANCE, GAME_MAX_DIPLCHANCE, GAME_DEFAULT_DIPLCHANCE)
//
//  GEN_BOOL("spacerace", Game.game.spacerace,
//	   SSET_RULES_FLEXIBLE, SSET_SCIENCE, SSET_VITAL, SSET_TO_CLIENT,
//	   N"Whether to allow space race",
//	   N"If this option is set to 1, players can build spaceships.",
//	   null, 
//	   GAME_DEFAULT_SPACERACE)
//
//  GEN_INT("civilwarsize", Game.game.civilwarsize,
//	  SSET_RULES_FLEXIBLE, SSET_SOCIOLOGY, SSET_RARE, SSET_TO_CLIENT,
//	  N"Minimum number of cities for civil war",
//	  N("A civil war is triggered if a player has at least this " +
//	     "many cities and the player's capital is captured. If " +
//	     "this option is set to the maximum value, civil wars are " +
//	     "turned off altogether."), null, 
//	  GAME_MIN_CIVILWARSIZE, Game_H.GAME_MAX_CIVILWARSIZE, 
//	  GAME_DEFAULT_CIVILWARSIZE)
//
//  GEN_INT("contactturns", Game.game.contactturns,
//	  SSET_RULES_FLEXIBLE, SSET_MILITARY, SSET_RARE, SSET_TO_CLIENT,
//	  N"Turns until player contact is lost",
//	  N("Players may meet for diplomacy this number of turns " +
//	     "after their units have last met, even if they do not have " +
//	     "an embassy. If set to zero then players cannot meet unless " +
//	     "they have an embassy."),
//	  null,
//	  GAME_MIN_CONTACTTURNS, GAME_MAX_CONTACTTURNS, 
//	  GAME_DEFAULT_CONTACTTURNS)
//
//  GEN_BOOL("savepalace", Game.game.savepalace,
//	   SSET_RULES_FLEXIBLE, SSET_MILITARY, SSET_RARE, SSET_TO_CLIENT,
//	   N"Rebuild palace if capital is conquered",
//	   N("If this is set to 1, then when the capital is conquered the " +
//	      "palace " +
//	      "is automatically rebuilt for free in another randomly " +
//	      "choosen city. This is significant because the technology " +
//	      "requirement for building a palace will be ignored."),
//	   null,
//	   GAME_DEFAULT_SAVEPALACE)
//
//  GEN_BOOL("naturalcitynames", Game.game.natural_city_names,
//           SSET_RULES_FLEXIBLE, SSET_SOCIOLOGY, SSET_RARE, SSET_TO_CLIENT,
//           N"Whether to use natural city names",
//           N("If enabled, the default city names will be determined based " +
//              "on the surrounding terrain."),
//           null, GAME_DEFAULT_NATURALCITYNAMES)
//
//  /* Meta options: these don't affect the internal rules of the Game.game, but
//   * do affect players.  Also options which only produce extra server
//   * "output" and don't affect the actual Game.game.
//   * ("endyear" is here, and not RULES_FLEXIBLE, because it doesn't
//   * affect what happens in the Game.game, it just determines when the
//   * players stop playing and look at the score.)
//   */
//  GEN_STRING("allowtake", Game.game.allow_take,
//	     SSET_META, SSET_NETWORK, SSET_RARE, SSET_TO_CLIENT,
//             N"Players that users are allowed to take",
//             N("This should be a string of characters, each of which " +
//                "specifies a type or status of a civilization (player). " +
//                "Clients will only be permitted to take " +
//                "or observe those players which match one of the specified " +
//                "letters. This only affects future uses of the take or " +
//                "observe command; it is not retroactive. The characters " +
//		"and their meanings are:\n" +
//                "    o,O = Global observer\n" +
//                "    b   = Barbarian players\n" +
//                "    d   = Dead players\n" +
//                "    a,A = AI players\n" +
//                "    h,H = Human players\n" +
//                "The first description on this list which matches a " +
//                "player is the one which applies. Thus 'd' does not " +
//                "include dead barbarians, 'a' does not include dead AI " +
//                "players, and so on. Upper case letters apply before " +
//                "the Game.game has started, lower case letters afterwards.\n\n" +
//                "Each character above may be followed by one of the " +
//                "following numbers to allow or restrict the manner " +
//                "of connection:\n\n" +
//                "(none) = Controller allowed, observers allowed, " +
//                "can displace connections. (Displacing a connection means " +
//		"that you may take over a player, even if another user " +
//		"already controls that player.)\n\n" +
//                "1 = Controller allowed, observers allowed, " +
//                "can't displace connections;\n\n" +
//                "2 = Controller allowed, no observers allowed, " +
//                "can displace connections;\n\n" +
//                "3 = Controller allowed, no observers allowed, " +
//                "can't displace connections;\n\n" +
//                "4 = No controller allowed, observers allowed;\n\n"),
//                allowtake_callback, GAME_DEFAULT_ALLOW_TAKE)
//
//  GEN_BOOL("autotoggle", Game.game.auto_ai_toggle,
//	   SSET_META, SSET_NETWORK, SSET_SITUATIONAL, SSET_TO_CLIENT,
//	   N"Whether AI-status toggles with connection",
//	   N("If this is set to 1, AI status is turned off when a player " +
//	      "connects, and on when a player disconnects."),
//	   autotoggle_callback, GAME_DEFAULT_AUTO_AI_TOGGLE)
//
//  GEN_INT("endyear", Game.game.end_year,
//	  SSET_META, SSET_SOCIOLOGY, SSET_VITAL, SSET_TO_CLIENT,
//	  N"Year the Game.game ends", 
//	  N"The Game.game will end at the end of the given year.", null,
//	  GAME_MIN_END_YEAR, GAME_MAX_END_YEAR, GAME_DEFAULT_END_YEAR)
//
//  GEN_INT("timeout", Game.game.timeout,
//	  SSET_META, SSET_INTERNAL, SSET_VITAL, SSET_TO_CLIENT,
//	  N"Maximum seconds per turn",
//	  N("If all players have not hit \"Turn Done\" before this " +
//	     "time is up, then the turn ends automatically. Zero " +
//	     "means there is no timeout. In servers compiled with " +
//	     "debugging, a timeout " +
//	     "of -1 sets the autogame test mode. Use this with the command " +
//	     "\"timeoutincrease\" to have a dynamic timer."), null, 
//	   GAME_MIN_TIMEOUT, GAME_MAX_TIMEOUT, GAME_DEFAULT_TIMEOUT)
//
//  GEN_INT("timeaddenemymove", Game.game.timeoutaddenemymove,
//        SSET_META, SSET_INTERNAL, SSET_VITAL, SSET_TO_CLIENT,
//	  N"Timeout at least n seconds when enemy moved",
//	  N("Any time a unit moves when in sight of an enemy player, " +
//	     "the remaining timeout is set to this value if it was lower."),
//	  null, 0, GAME_MAX_TIMEOUT, GAME_DEFAULT_TIMEOUTADDEMOVE)
//  
//  GEN_INT("nettimeout", Game.game.tcptimeout,
//	  SSET_META, SSET_NETWORK, SSET_RARE, SSET_TO_CLIENT,
//	  N"Seconds to let a client's network connection block",
//	  N("If a network connection is blocking for a time greater than " +
//	     "this value, then the connection is closed. Zero " +
//	     "means there is no timeout (although connections will be " +
//	     "automatically disconnected eventually)."),
//	  null,
//	  GAME_MIN_TCPTIMEOUT, GAME_MAX_TCPTIMEOUT, GAME_DEFAULT_TCPTIMEOUT)
//
//  GEN_INT("netwait", Game.game.netwait,
//	  SSET_META, SSET_NETWORK, SSET_RARE, SSET_TO_CLIENT,
//	  N"Max seconds for network buffers to drain",
//	  N("The server will wait for up to the value of this " +
//	     "parameter in seconds, for all client connection network " +
//	     "buffers to unblock. Zero means the server will not " +
//	     "wait at all."), null, 
//	  GAME_MIN_NETWAIT, GAME_MAX_NETWAIT, GAME_DEFAULT_NETWAIT)
//
//  GEN_INT("pingtime", Game.game.pingtime,
//	  SSET_META, SSET_NETWORK, SSET_RARE, SSET_TO_CLIENT,
//	  N"Seconds between PINGs",
//	  N("The civserver will poll the clients with a PING request " +
//	     "each time this period elapses."), null, 
//	  GAME_MIN_PINGTIME, GAME_MAX_PINGTIME, GAME_DEFAULT_PINGTIME)
//
//  GEN_INT("pingtimeout", Game.game.pingtimeout,
//	  SSET_META, SSET_NETWORK, SSET_RARE,
//          SSET_TO_CLIENT,
//	  N"Time to cut a client",
//	  N("If a client doesn't reply to a PING in this time the " +
//	     "client is disconnected."), null, 
//	  GAME_MIN_PINGTIMEOUT, GAME_MAX_PINGTIMEOUT, GAME_DEFAULT_PINGTIMEOUT)
//
//  GEN_BOOL("turnblock", Game.game.turnblock,
//	   SSET_META, SSET_INTERNAL, SSET_SITUATIONAL, SSET_TO_CLIENT,
//	   N"Turn-blocking Game.game play mode",
//	   N("If this is set to 1 the Game.game turn is not advanced " +
//	      "until all players have finished their turn, including " +
//	      "disconnected players."), null, 
//	   GAME_DEFAULT_TURNBLOCK)
//
//  GEN_BOOL("fixedlength", Game.game.fixedlength,
//	   SSET_META, SSET_INTERNAL, SSET_SITUATIONAL, SSET_TO_CLIENT,
//	   N"Fixed-length turns play mode",
//	   N("If this is set to 1 the Game.game turn will not advance " +
//	      "until the timeout has expired, even if all players " +
//	      "have clicked on \"Turn Done\"."), null,
//	   false)
//
//  GEN_STRING("demography", Game.game.demography,
//	     SSET_META, SSET_INTERNAL, SSET_SITUATIONAL, SSET_TO_CLIENT,
//	     N"What is in the Demographics report",
//	     N("This should be a string of characters, each of which " +
//		"specifies the inclusion of a line of information " +
//		"in the Demographics report.\n" +
//		"The characters and their meanings are:\n" +
//		"    N = include Population\n" +
//		"    P = include Production\n" +
//		"    A = include Land Area\n" +
//		"    L = include Literacy\n" +
//		"    R = include Research Speed\n" +
//		"    S = include Settled Area\n" +
//		"    E = include Economics\n" +
//		"    M = include Military Service\n" +
//		"    O = include Pollution\n" +
//		"Additionally, the following characters control whether " +
//		"or not certain columns are displayed in the report:\n" +
//		"    q = display \"quantity\" column\n" +
//		"    r = display \"rank\" column\n" +
//		"    b = display \"best nation\" column\n" +
//		"The order of characters is not significant, but " +
//		"their capitalization is."),
//	     is_valid_demography, GAME_DEFAULT_DEMOGRAPHY)
//
//  GEN_INT("saveturns", Game.game.save_nturns,
//	  SSET_META, SSET_INTERNAL, SSET_VITAL, SSET_SERVER_ONLY,
//	  N"Turns per auto-save",
//	  N("The Game.game will be automatically saved per this number of " +
//	     "turns. Zero means never auto-save."), null, 
//	  0, 200, 10)
//
//  /* Could undef entire option if !HAVE_LIBZ, but this way users get to see
//   * what they're missing out on if they didn't compile with zlib?  --dwp
//   */
//#ifdef HAVE_LIBZ
//  GEN_INT("compress", Game.game.save_compress_level,
//	  SSET_META, SSET_INTERNAL, SSET_RARE, SSET_SERVER_ONLY,
//	  N"Savegame compression level",
//	  N("If non-zero, saved games will be compressed using zlib " +
//	     "(gzip format). Larger values will give better " +
//	     "compression but take longer. If the maximum is zero " +
//	     "this server was not compiled to use zlib."), null,
//
//	  GAME_MIN_COMPRESS_LEVEL, GAME_MAX_COMPRESS_LEVEL,
//	  GAME_DEFAULT_COMPRESS_LEVEL)
//#else
//  GEN_INT("compress", Game.game.save_compress_level,
//	  SSET_META, SSET_INTERNAL, SSET_RARE, SSET_SERVER_ONLY,
//	  N"Savegame compression level",
//	  N("If non-zero, saved games will be compressed using zlib " +
//	     "(gzip format). Larger values will give better " +
//	     "compression but take longer. If the maximum is zero " +
//	     "this server was not compiled to use zlib."), null, 
//
//	  GAME_NO_COMPRESS_LEVEL, GAME_NO_COMPRESS_LEVEL, 
//	  GAME_NO_COMPRESS_LEVEL)
//#endif
//
//  GEN_STRING("savename", Game.game.save_name,
//	     SSET_META, SSET_INTERNAL, SSET_VITAL, SSET_SERVER_ONLY,
//	     N"Auto-save name prefix",
//	     N("Automatically saved games will have name " +
//		"\"<prefix><year>.sav\". This setting sets " +
//		"the <prefix> part."), null,
//	     GAME_DEFAULT_SAVE_NAME)
//
//  GEN_BOOL("scorelog", Game.game.scorelog,
//	   SSET_META, SSET_INTERNAL, SSET_SITUATIONAL, SSET_SERVER_ONLY,
//	   N"Whether to log player statistics",
//	   N("If this is set to 1, player statistics are appended to " +
//	      "the file \"civscore.log\" every turn. These statistics " +
//	      "can be used to create power graphs after the Game.game."), null,
//	   GAME_DEFAULT_SCORELOG)
//
//  GEN_INT("Gamelog.gamelog", gamelog_level,
//	  SSET_META, SSET_INTERNAL, SSET_SITUATIONAL, SSET_SERVER_ONLY,
//	  N"Detail level for logging Game.game events",
//	  N("Only applies if the Game.game log feature is enabled " +
//	     "(with the -g command line option). " +
//	     "Levels: 0=no logging, 20=standard logging, 30=detailed " +
//	     "logging, 40=debuging logging."), null,
//	  0, 40, 20)
//
//  GEN_END
//};
//
///* The number of settings, not including the END. */
//final int SETTINGS_NUM = ARRAY_SIZE(settings) - 1;
}