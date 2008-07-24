package server;

import common.Connection;

import utility.Speclists;

public class Gamehand{

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
//#include <stdio.h> /* for remove() */ 
//
//#include "capability.h"
//#include "events.h"
//#include "fcintl.h"
//#include "improvement.h"
//#include "log.h"
//#include "mem.h"
//#include "packets.h"
//#include "rand.h"
//#include "registry.h"
//#include "shared.h"
//#include "support.h"
//
//#include "maphand.h"
//#include "plrhand.h"
//#include "unittools.h"
//
//#include "gamehand.h"
//#include "srv_main.h"
//
//#define CHALLENGE_ROOT "challenge"
//
//
///****************************************************************************
//  Initialize the Game.game.id variable to a random string of characters.
//****************************************************************************/
//static void init_game_id()
//{
//  static final char chars[] =
//    "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
//  int i;
//
//  for (i = 0; i < sizeof(Game.game.id) - 1; i++) {
//    Game.game.id[i] = chars[Rand.myrand(sizeof(chars) - 1)];
//  }
//  Game.game.id[i] = '\0';
//}
//
///****************************************************************************
//  Place a starting unit for the player.
//****************************************************************************/
//static void place_starting_unit(tile ptile, player pplayer,
//				char crole)
//{
//  int utype;
//  Eunit_flag_id role;
//
//  assert(!Unit.is_non_allied_unit_tile(ptile, pplayer));
//
//  /* For scenarios or dispersion, huts may coincide with player starts (in 
//   * other cases, huts are avoided as start positions).  Remove any such hut,
//   * and make sure to tell the client, since we may have already sent this
//   * tile (with the hut) earlier: */
//  if (Map.map_has_special(ptile, Terrain_H.S_HUT)) {
//    Map.map_clear_special(ptile, Terrain_H.S_HUT);
//    Maphand.update_tile_knowledge(ptile);
//    util.freelog(Log.LOG_VERBOSE, "Removed hut on start position for %s",
//	    pplayer.name);
//  }
//
//  /* Expose visible area. */
//  circle_iterate(ptile, Game.game.rgame.init_vis_radius_sq, ctile) {
//    Maphand.show_area(pplayer, ctile, 0);
//  } circle_iterate_end;
//
//  switch(crole) {
//  case 'c':
//    role = L_CITIES;
//    break;
//  case 'w':
//    role = L_SETTLERS;
//    break;
//  case 'x':
//    role = L_EXPLORER;
//    break;
//  case 'k':
//    role = L_GAMELOSS;
//    break;
//  case 's':
//    role = L_DIPLOMAT;
//    break;
//  case 'f':
//    role = L_FERRYBOAT;
//    break;
//  case 'd':
//    role = L_DEFEND_OK;
//    break;
//  case 'D':
//    role = L_DEFEND_GOOD;
//    break;
//  case 'a':
//    role = L_ATTACK_FAST;
//    break;
//  case 'A':
//    role = L_ATTACK_STRONG;
//    break;
//  default: 
//    assert(false);
//    return;
//  }
//
//  /* Create the unit of an appropriate type, if it exists */
//  if (num_role_units(role) > 0) {
//    utype = first_role_unit_for_player(pplayer, role);
//    if (utype == unittype.U_LAST) {
//      utype = Unittype_P.get_role_unit(role, 0);
//    }
//
//    /* We cannot currently handle sea units as start units. */
//    if (Unittype_P.unit_types[utype].move_type == SEA_MOVING) {
//      util.freelog(Log.LOG_ERROR, ("Sea moving start units are not yet supported, " +
//                           "%s not created."), Unittype_P.unit_types[utype].name);
//      notify_player(pplayer, ("Sea moving start units are not yet supported. " +
//                               "Nobody gets %s."), Unittype_P.unit_types[utype].name);
//      return;
//    }
//
//     Unittools.create_unit(pplayer, ptile, utype, false, 0, -1);
//  }
//}
//
///****************************************************************************
//  Initialize a new Game.game: place the players' units onto the map, etc.
//****************************************************************************/
//void init_new_game()
//{
//  final int NO_START_POS = -1;
//  int start_pos[Game.game.nplayers];
//  boolean pos_used[Map.map.num_start_positions];
//  int i, num_used = 0;
//
//  init_game_id();
//
//  /* Shuffle starting positions around so that they match up with the
//   * desired players. */
//
//  /* First set up some data fields. */
//  util.freelog(Log.LOG_VERBOSE, "Placing players at start positions.");
//  for (i = 0; i < Map.map.num_start_positions; i++) {
//    int n = Map.map.start_positions[i].nation;
//
//    pos_used[i] = false;
//    util.freelog(Log.LOG_VERBOSE, "%3d : (%2d,%2d) : %d : %s",
//	    i, Map.map.start_positions[i].tile.x,
//	    Map.map.start_positions[i].tile.y,
//	    n, (n >= 0 ? Nation.get_nation_name(n) : ""));
//  }
//  for(player pplayer: Game.game.players){
//    start_pos[pplayer.player_no] = NO_START_POS;
//  }
//
//  /* Second, assign a nation to a start position for that nation. */
//  util.freelog(Log.LOG_VERBOSE, "Assigning matching nations.");
//  for(player pplayer: Game.game.players){
//    for (i = 0; i < Map.map.num_start_positions; i++) {
//      assert(pplayer.nation != NO_NATION_SELECTED);
//      if (pplayer.nation == Map.map.start_positions[i].nation) {
//	util.freelog(Log.LOG_VERBOSE, "Start_pos %d matches player %d (%s).",
//		i, pplayer.player_no, Nation.get_nation_name(pplayer.nation));
//	start_pos[pplayer.player_no] = i;
//	pos_used[i] = true;
//	num_used++;
//      }
//    }
//  }
//
//  /* Third, assign players randomly to the remaining start positions. */
//  util.freelog(Log.LOG_VERBOSE, "Assigning random nations.");
//  for(player pplayer: Game.game.players){
//    if (start_pos[pplayer.player_no] == NO_START_POS) {
//      int which = Rand.myrand(Map.map.num_start_positions - num_used);
//
//      for (i = 0; i < Map.map.num_start_positions; i++) {
//	if (!pos_used[i]) {
//	  if (which == 0) {
//	    util.freelog(Log.LOG_VERBOSE,
//		    "Randomly assigning player %d (%s) to pos %d.",
//		    pplayer.player_no, Nation.get_nation_name(pplayer.nation), i);
//	    start_pos[pplayer.player_no] = i;
//	    pos_used[i] = true;
//	    num_used++;
//	    break;
//	  }
//	  which--;
//	}
//      }
//    }
//    assert(start_pos[pplayer.player_no] != NO_START_POS);
//  }
//
//  /* Loop over all players, creating their initial units... */
//  for(player pplayer: Game.game.players){
//    struct start_position pos
//      = Map.map.start_positions[start_pos[pplayer.player_no]];
//
//    /* don't give any units to observer */
//    if (pplayer.is_observer) {
//      continue;
//    }
//
//    /* Place the first unit. */
//    place_starting_unit(pos.tile, pplayer, Game.game.start_units[0]);
//  }
//
//  /* Place all other units. */
//  for(player pplayer: Game.game.players){
//    int i, x, y;
//    tile ptile;
//    struct start_position p
//      = Map.map.start_positions[start_pos[pplayer.player_no]];
//
//    /* don't give any units to observer */
//    if (pplayer.is_observer) {
//      continue;
//    }
//
//    for (i = 1; i < Game.game.start_units.length(); i++) {
//      do {
//	x = p.tile.x + Rand.myrand(2 * Game.game.dispersion + 1) - Game.game.dispersion;
//	y = p.tile.y + Rand.myrand(2 * Game.game.dispersion + 1) - Game.game.dispersion;
//      } while (!((ptile = map_pos_to_tile(x, y))
//		 && map_get_continent(p.tile) == map_get_continent(ptile)
//		 && !Terrain_H.is_ocean(ptile.terrain)
//		 && !Unit.is_non_allied_unit_tile(ptile, pplayer)));
//
//
//      /* Create the unit of an appropriate type. */
//      place_starting_unit(ptile, pplayer, Game.game.start_units[i]);
//    }
//  }
//
//  /* Initialise list of improvements with world-wide equiv_range */
//  improvement_status_init(Game.game.improvements, ARRAY_SIZE(Game.game.improvements));
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void send_start_turn_to_clients()
//{
//  lsend_packet_start_turn(&Game.game.game_connections);
//}
//
///**************************************************************************
//  Tell clients the year, and also update turn_done and nturns_idle fields
//  for all players.
//**************************************************************************/
//void send_year_to_clients(int year)
//{
//  struct packet_new_year apacket;
//  int i;
//  
//  for(i=0; i<Game.game.nplayers; i++) {
//    player pplayer = &Game.game.players[i];
//    pplayer.turn_done = false;
//    pplayer.nturns_idle++;
//  }
//
//  apacket.year = year;
//  apacket.turn = Game.game.turn;
//  lsend_packet_new_year(&Game.game.game_connections, &apacket);
//
//  /* Hmm, clients could add this themselves based on above packet? */
//  notify_conn_ex(&Game.game.game_connections, null, E_NEXT_YEAR, "Year: %s",
//		 Shared.textyear(year));
//}
//
//
///**************************************************************************
//  Send specified state; should be a CLIENT_GAME_*_STATE ?
//  (But note client also changes state from other events.)
//**************************************************************************/
//void send_game_state(Speclists<Connection> dest, int state)
//{
//  dlsend_packet_game_state(dest, state);
//}


	/**************************************************************************
  Send game_info packet; some server options and various stuff...
  dest==null means Game.game.game_connections
	 **************************************************************************/
	public static void send_game_info(Speclists<Connection> dest)
	{
//  struct packet_game_info ginfo;
//  int i;
//
//  if (!dest)
//    dest = &Game.game.game_connections;
//
//  ginfo.gold = Game.game.gold;
//  ginfo.tech = Game.game.tech;
//  ginfo.researchcost = Game.game.researchcost;
//  ginfo.skill_level = Game.game.skill_level;
//  ginfo.timeout = Game.game.timeout;
//  ginfo.end_year = Game.game.end_year;
//  ginfo.year = Game.game.year;
//  ginfo.turn = Game.game.turn;
//  ginfo.min_players = Game.game.min_players;
//  ginfo.max_players = Game.game.max_players;
//  ginfo.nplayers = Game.game.nplayers;
//  ginfo.globalwarming = Game.game.globalwarming;
//  ginfo.heating = Game.game.heating;
//  ginfo.nuclearwinter = Game.game.nuclearwinter;
//  ginfo.cooling = Game.game.cooling;
//  ginfo.diplomacy = Game.game.diplomacy;
//  ginfo.techpenalty = Game.game.techpenalty;
//  ginfo.foodbox = Game.game.foodbox;
//  ginfo.civstyle = Game.game.civstyle;
//  ginfo.spacerace = Game.game.spacerace;
//  ginfo.unhappysize = Game.game.unhappysize;
//  ginfo.angrycitizen = Game.game.angrycitizen;
//  ginfo.diplcost = Game.game.diplcost;
//  ginfo.freecost = Game.game.freecost;
//  ginfo.conquercost = Game.game.conquercost;
//  ginfo.cityfactor = Game.game.cityfactor;
//  for (i = 0; i < A_LAST /*Game.game.num_tech_types */ ; i++)
//    ginfo.global_advances[i] = Game.game.global_advances[i];
//  for (i = 0; i < B_LAST /*Game.game.num_impr_types */ ; i++)
//    ginfo.global_wonders[i] = Game.game.global_wonders[i];
//  /* the following values are computed every
//     time a packet_game_info packet is created */
//  if (Game.game.timeout != 0) {
//    ginfo.seconds_to_turndone =
//	Game.game.turn_start + Game.game.timeout - new Date(); //time(null);
//  } else {
//    /* unused but at least initialized */
//    ginfo.seconds_to_turndone = -1;
//  }
//
//  conn_list_iterate(*dest, pconn) {
//    /* ? fixme: check for non-players: */
//    ginfo.player_idx = (pconn.player ? pconn.player.player_no : -1);
//    send_packet_game_info(pconn, &ginfo);
//  }
//  }
	}

///**************************************************************************
//  adjusts Game.game.timeout based on various server options
//
//  timeoutint: adjust Game.game.timeout every timeoutint turns
//  timeoutinc: adjust Game.game.timeout by adding timeoutinc to it.
//  timeoutintinc: every time we adjust Game.game.timeout, we add timeoutintinc
//                 to timeoutint.
//  timeoutincmult: every time we adjust Game.game.timeout, we multiply timeoutinc
//                  by timeoutincmult
//**************************************************************************/
//int update_timeout()
//{
//  /* if there's no timer or we're doing autogame, do nothing */
//  if (Game.game.timeout < 1 || Game.game.timeoutint == 0) {
//    return Game.game.timeout;
//  }
//
//  if (Game.game.timeoutcounter >= Game.game.timeoutint) {
//    Game.game.timeout += Game.game.timeoutinc;
//    Game.game.timeoutinc *= Game.game.timeoutincmult;
//
//    Game.game.timeoutcounter = 1;
//    Game.game.timeoutint += Game.game.timeoutintinc;
//
//    if (Game.game.timeout > GAME_MAX_TIMEOUT) {
//      notify_conn_ex(&Game.game.game_connections, null, event_type.E_NOEVENT,
//		     ("The turn timeout has exceeded its maximum value, " +
//		       "fixing at its maximum"));
//      util.freelog(Log.LOG_DEBUG, "Game.game.timeout exceeded maximum value");
//      Game.game.timeout = GAME_MAX_TIMEOUT;
//      Game.game.timeoutint = 0;
//      Game.game.timeoutinc = 0;
//    } else if (Game.game.timeout < 0) {
//      notify_conn_ex(&Game.game.game_connections, null, event_type.E_NOEVENT,
//		     ("The turn timeout is smaller than zero, " +
//		       "fixing at zero."));
//      util.freelog(Log.LOG_DEBUG, "Game.game.timeout less than zero");
//      Game.game.timeout = 0;
//    }
//  } else {
//    Game.game.timeoutcounter++;
//  }
//
//  util.freelog(Log.LOG_DEBUG, "timeout=%d, inc=%d incmult=%d\n   " +
//	  "int=%d, intinc=%d, turns till next=%d",
//	  Game.game.timeout, Game.game.timeoutinc, Game.game.timeoutincmult,
//	  Game.game.timeoutint, Game.game.timeoutintinc,
//	  Game.game.timeoutint - Game.game.timeoutcounter);
//
//  return Game.game.timeout;
//}
//
///**************************************************************************
//  adjusts Game.game.turn_start when enemy moves an unit, we see it and the 
//  remaining timeout is smaller than the option
//  It's possible to use a simular function to do that per player.
//**************************************************************************/
//void increase_timeout_because_unit_moved()
//{
//  if (Game.game.timeout != 0){
//    int seconds_to_turndone = Game.game.turn_start + Game.game.timeout - new Date(); //time(null);
//
//    if (seconds_to_turndone < Game.game.timeoutaddenemymove){
//      Game.game.turn_start = new Date() - Game.game.timeout + Game.game.timeoutaddenemymove;
//      send_game_info(null);
//    }	
//  }
//}
//
///************************************************************************** 
//  generate challenge filename for this connection, cannot fail.
//**************************************************************************/
//static void gen_challenge_filename(connection pc)
//{
//}
//
///************************************************************************** 
//  get challenge filename for this connection.
//**************************************************************************/
//static final String get_challenge_filename(connection pc)
//{
//  static char filename[MAX_LEN_PATH];
//
//  filename = util.my_snprintf( "%s_%d_%d",
//      CHALLENGE_ROOT, Srv_main.srvarg.port, pc.id);
//
//  return filename;
//}
//
///************************************************************************** 
//  get challenge full filename for this connection.
//**************************************************************************/
//static final String get_challenge_fullname(connection pc)
//{
//  static char fullname[MAX_LEN_PATH];
//
//  interpret_tilde(fullname, sizeof(fullname), "~/.freeciv/");
//  sz_strlcat(fullname, get_challenge_filename(pc));
//
//  return fullname;
//}
//
///************************************************************************** 
//  find a file that we can write too, and return it's name.
//**************************************************************************/
//final String new_challenge_filename(connection pc)
//{
//  if (!has_capability("new_hack", pc.capability)) {
//    return "";
//  }
//
//  gen_challenge_filename(pc);
//  return get_challenge_filename(pc);
//}
//
//
///************************************************************************** 
//opens a file specified by the packet and compares the packet values with
//the file values. Sends an answer to the client once it's done.
//**************************************************************************/
//void handle_single_want_hack_req(connection pc,
//    				 final struct packet_single_want_hack_req
//				 *packet)
//{
//  struct section_file file;
//  char *token = null;
//  boolean you_have_hack = false;
//
//  if (!has_capability("new_hack", pc.capability)) {
//    dsend_packet_single_want_hack_reply(pc, false);
//    return ;
//  }
//
//  if (section_file_load_nodup(&file, get_challenge_fullname(pc))) {
//    token = secfile_lookup_str_default(&file, null, "challenge.token");
//    you_have_hack = (token && token.equals(packet.token));
//    section_file_free(&file);
//  }
//
//  if (!token) {
//    util.freelog(Log.LOG_DEBUG, "Failed to read authentication token");
//  }
//
//  if (you_have_hack) {
//    pc.access_level = ALLOW_HACK;
//  }
//
//  dsend_packet_single_want_hack_reply(pc, you_have_hack);
//}
}