package common;

import common.game.civ_game;
import common.unit.unit;

public class Game{
//#include "capstr.h"
//#include "city.h"
//#include "cm.h"
//#include "connection.h"
//#include "fcintl.h"
//#include "government.h"
//#include "idex.h"
//#include "log.h"
//#include "Map.map.h"
//#include "mem.h"
//#include "nation.h"
//#include "packets.h"
//#include "player.h"
//#include "shared.h"
//#include "spaceship.h"
//#include "support.h"
//#include "tech.h"
//#include "unit.h"
//
//#include "Game.game.h"
//
//void dealloc_id(int id);
	public static civ_game game;
//
///*
//struct player_score {
//  int happy;
//  int content;
//  int unhappy;
//  int angry;
//  int taxmen;
//  int scientists;
//  int elvis;
//  int wonders;
//  int techs;
//  int landarea;
//  int settledarea;
//  int population;
//  int cities;
//  int units;
//  int pollution;
//  int literacy;
//  int bnp;
//  int mfg;
//  int spaceship;
//};
//*/
//
///**************************************************************************
//Count the # of thousand citizen in a civilisation.
//**************************************************************************/
//int civ_population(player pplayer)
//{
//  int ppl=0;
//  city_list_iterate(pplayer.cities, pcity)
//    ppl+=city_population(pcity);
//  }
//  return ppl;
//}
//
//
///**************************************************************************
//...
//**************************************************************************/
//city game_find_city_by_name(final String name)
//{
//  for(player pplayer: Game.game.players){
//    city pcity = city_list_find_name(&pplayer.cities, name);
//
//    if (pcity) {
//      return pcity;
//    }
//  }
//
//  return null;
//}
//
//
///**************************************************************************
//  Often used function to get a city pointer from a city ID.
//  City may be any city in the Game.game.  This now always uses fast idex
//  method, instead of looking through all cities of all players.
//**************************************************************************/
//city find_city_by_id(int id)
//{
//  return idex_lookup_city(id);
//}


	/***************************************************************************
	 * Find unit out of all units in Game.game: now uses fast idex method, instead of
	 * looking through all units of all players.
	 **************************************************************************/
	public static unit find_unit_by_id(int id) {
		//TODO: need a hashmap to find object quick
//		return idex_lookup_unit(id);
		return new unit();
	}

	// /**************************************************************************
//  In the server call Unittools.wipe_unit(), and never this function directly.
//**************************************************************************/
//void game_remove_unit(unit punit)
//{
//  city pcity;
//
//  util.freelog(Log.LOG_DEBUG, "game_remove_unit %d", punit.id);
//  util.freelog(Log.LOG_DEBUG, "removing unit %d, %s %s (%d %d) hcity %d",
//	  punit.id, Nation.get_nation_name(punit.unit_owner().nation),
//	  unit_name(punit.type), punit.tile.x, punit.tile.y,
//	  punit.homecity);
//
//  pcity = player_find_city_by_id(punit.unit_owner(), punit.homecity);
//  if (pcity) {
//    unit_list_unlink(&pcity.units_supported, punit);
//  }
//
//  if (pcity) {
//    util.freelog(Log.LOG_DEBUG, "home city %s, %s, (%d %d)", pcity.name,
//	    Nation.get_nation_name(City.city_owner(pcity).nation), pcity.tile.x,
//	    pcity.tile.y);
//  }
//
//  unit_list_unlink(&punit.tile.units, punit);
//  unit_list_unlink(&punit.unit_owner().units, punit);
//
//  idex_unregister_unit(punit);
//
//  if (is_server) {
//    dealloc_id(punit.id);
//  }
//  destroy_unit_virtual(punit);
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void game_remove_city(city pcity)
//{
//  util.freelog(Log.LOG_DEBUG, "game_remove_city %d", pcity.id);
//  util.freelog(Log.LOG_DEBUG, "removing city %s, %s, (%d %d)", pcity.name,
//	   Nation.get_nation_name(City.city_owner(pcity).nation), pcity.tile.x,
//	  pcity.tile.y);
//
//  city_map_checked_iterate(pcity.tile, x, y, map_tile) {
//    set_worker_city(pcity, x, y, C_TILE_EMPTY);
//  } city_map_checked_iterate_end;
//  city_list_unlink(&City.city_owner(pcity).cities, pcity);
//  map_set_city(pcity.tile, null);
//  idex_unregister_city(pcity);
//  remove_city_virtual(pcity);
//}
//
///***************************************************************
//...
//***************************************************************/
//void game_init()
//{
//  int i;
//  Game.game.is_new_game   = true;
//  Game.game.globalwarming = 0;
//  Game.game.warminglevel  = 8;
//  Game.game.nuclearwinter = 0;
//  Game.game.coolinglevel  = 8;
//  Game.game.gold          = GAME_DEFAULT_GOLD;
//  Game.game.tech          = GAME_DEFAULT_TECHLEVEL;
//  Game.game.skill_level   = GAME_DEFAULT_SKILL_LEVEL;
//  Game.game.timeout       = GAME_DEFAULT_TIMEOUT;
//  Game.game.timeoutint    = GAME_DEFAULT_TIMEOUTINT;
//  Game.game.timeoutintinc = GAME_DEFAULT_TIMEOUTINTINC;
//  Game.game.timeoutinc    = GAME_DEFAULT_TIMEOUTINC;
//  Game.game.timeoutincmult= GAME_DEFAULT_TIMEOUTINCMULT;
//  Game.game.timeoutcounter= 1;
//  Game.game.timeoutaddenemymove = GAME_DEFAULT_TIMEOUTADDEMOVE; 
//  Game.game.tcptimeout    = GAME_DEFAULT_TCPTIMEOUT;
//  Game.game.netwait       = GAME_DEFAULT_NETWAIT;
//  Game.game.last_ping     = 0;
//  Game.game.pingtimeout   = GAME_DEFAULT_PINGTIMEOUT;
//  Game.game.pingtime      = GAME_DEFAULT_PINGTIME;
//  Game.game.end_year      = GAME_DEFAULT_END_YEAR;
//  Game.game.year          = GAME_START_YEAR;
//  Game.game.turn          = 0;
//  Game.game.min_players   = GAME_DEFAULT_MIN_PLAYERS;
//  Game.game.max_players  = GAME_DEFAULT_MAX_PLAYERS;
//  Game.game.aifill      = GAME_DEFAULT_AIFILL;
//  Game.game.nplayers=0;
//  Game.game.researchcost = GAME_DEFAULT_RESEARCHCOST;
//  Game.game.diplcost    = GAME_DEFAULT_DIPLCOST;
//  Game.game.diplchance  = GAME_DEFAULT_DIPLCHANCE;
//  Game.game.freecost    = GAME_DEFAULT_FREECOST;
//  Game.game.conquercost = GAME_DEFAULT_CONQUERCOST;
//  Game.game.start_units = GAME_DEFAULT_START_UNITS;
//  Game.game.dispersion  = GAME_DEFAULT_DISPERSION;
//  Game.game.cityfactor  = GAME_DEFAULT_CITYFACTOR;
//  Game.game.citymindist = GAME_DEFAULT_CITYMINDIST;
//  Game.game.civilwarsize= GAME_DEFAULT_CIVILWARSIZE;
//  Game.game.contactturns= GAME_DEFAULT_CONTACTTURNS;
//  Game.game.rapturedelay= GAME_DEFAULT_RAPTUREDELAY;
//  Game.game.savepalace  = GAME_DEFAULT_SAVEPALACE;
//  Game.game.natural_city_names = GAME_DEFAULT_NATURALCITYNAMES;
//  Game.game.unhappysize = GAME_DEFAULT_UNHAPPYSIZE;
//  Game.game.angrycitizen= GAME_DEFAULT_ANGRYCITIZEN;
//  Game.game.foodbox     = GAME_DEFAULT_FOODBOX;
//  Game.game.aqueductloss= GAME_DEFAULT_AQUEDUCTLOSS;
//  Game.game.killcitizen = GAME_DEFAULT_KILLCITIZEN;
//  Game.game.scorelog    = GAME_DEFAULT_SCORELOG;
//  Game.game.techpenalty = GAME_DEFAULT_TECHPENALTY;
//  Game.game.civstyle    = GAME_DEFAULT_CIVSTYLE;
//  Game.game.razechance  = GAME_DEFAULT_RAZECHANCE;
//  Game.game.spacerace   = GAME_DEFAULT_SPACERACE;
//  Game.game.turnblock   = GAME_DEFAULT_TURNBLOCK;
//  Game.game.fogofwar    = GAME_DEFAULT_FOGOFWAR;
//  Game.game.fogofwar_old= Game.game.fogofwar;
//  Game.game.borders     = GAME_DEFAULT_BORDERS;
//  Game.game.happyborders = GAME_DEFAULT_HAPPYBORDERS;
//  Game.game.slow_invasions = GAME_DEFAULT_SLOW_INVASIONS;
//  Game.game.auto_ai_toggle = GAME_DEFAULT_AUTO_AI_TOGGLE;
//  Game.game.notradesize    = GAME_DEFAULT_NOTRADESIZE;
//  Game.game.fulltradesize  = GAME_DEFAULT_FULLTRADESIZE;
//  Game.game.barbarianrate  = GAME_DEFAULT_BARBARIANRATE;
//  Game.game.onsetbarbarian = GAME_DEFAULT_ONSETBARBARIAN;
//  Game.game.nbarbarians = 0;
//  Game.game.occupychance= GAME_DEFAULT_OCCUPYCHANCE;
//  Game.game.revolution_length = GAME_DEFAULT_REVOLUTION_LENGTH;
//
//  Game.game.heating     = 0;
//  Game.game.cooling     = 0;
//  Game.game.save_name = GAME_DEFAULT_SAVE_NAME;
//  Game.game.save_nturns=10;
//#ifdef HAVE_LIBZ
//  Game.game.save_compress_level = GAME_DEFAULT_COMPRESS_LEVEL;
//#else
//  Game.game.save_compress_level = GAME_NO_COMPRESS_LEVEL;
//#endif
//  Game.game.seed = GAME_DEFAULT_SEED;
//  Game.game.watchtower_vision=GAME_DEFAULT_WATCHTOWER_VISION;
//  Game.game.watchtower_extra_vision=GAME_DEFAULT_WATCHTOWER_EXTRA_VISION,
//  Game.game.allowed_city_names = GAME_DEFAULT_ALLOWED_CITY_NAMES;
//
//  Game.game.rulesetdir = GAME_DEFAULT_RULESETDIR;
//
//  Game.game.num_unit_types = 0;
//  Game.game.num_impr_types = 0;
//  Game.game.num_tech_types = 0;
// 
//  Game.game.nation_count = 0;
//  Game.game.government_count = 0;
//  Game.game.default_government = G_MAGIC;        /* flag */
//  Game.game.government_when_anarchy = G_MAGIC;   /* flag */
//  Game.game.ai_goal_government = G_MAGIC;        /* flag */
//
//  Game.game.default_building = B_LAST;
//  Game.game.palace_building = B_LAST;
//  Game.game.land_defend_building = B_LAST;
//
//  Game.game.demography = GAME_DEFAULT_DEMOGRAPHY;
//  Game.game.allow_take = GAME_DEFAULT_ALLOW_TAKE;
//
//  Game.game.save_options.save_random = true;
//  Game.game.save_options.save_players = true;
//  Game.game.save_options.save_known = true;
//  Game.game.save_options.save_starts = true;
//  Game.game.save_options.save_private_map = true;
//
//  init_our_capability();    
//  map_init();
//  idex_init();
//  cm_init();
//  
//  for(i=0; i<Shared_H.MAX_NUM_PLAYERS+Shared_H.MAX_NUM_BARBARIANS; i++)
//    player_init(&Game.game.players[i]);
//  for (i=0; i<A_LAST; i++)      /* Game.game.num_tech_types = 0 here */
//    Game.game.global_advances[i]=0;
//  for (i=0; i<B_LAST; i++)      /* Game.game.num_impr_types = 0 here */
//    Game.game.global_wonders[i]=0;
//  Game.game.player_idx=0;
//  Game.game.player_ptr=&Game.game.players[0];
//  terrain_control.river_help_text[0] = '\0';
//}
//
///***************************************************************
//  Remove all initialized players. This is all player slots, 
//  since we initialize them all on Game.game initialization.
//***************************************************************/
//static void game_remove_all_players()
//{
//  int i;
//
//  for (i = 0; i < Shared_H.MAX_NUM_PLAYERS + Shared_H.MAX_NUM_BARBARIANS; i++) {
//    game_remove_player(&Game.game.players[i]);
//  }
//
//  Game.game.nplayers=0;
//  Game.game.nbarbarians=0;
//}
//
///***************************************************************
//  Frees all memory of the Game.game.
//***************************************************************/
//void game_free()
//{
//  game_remove_all_players();
//  map_free();
//  idex_free();
//  ruleset_data_free();
//  cm_free();
//}
//
///***************************************************************
// Frees all memory which in objects which are read from a ruleset.
//***************************************************************/
//void ruleset_data_free()
//{
//  techs_free();
//  governments_free();
//  nations_free();
//  unit_types_free();
//  improvements_free();
//  city_styles_free();
//  tile_types_free();
//}
//
///***************************************************************
//...
//***************************************************************/
//void initialize_globals()
//{
//  for(player plr: Game.game.players){
//    for (city pcity : plr.cities.data) {
//      built_impr_iterate(pcity, i) {
//	if (is_wonder(i))
//	  Game.game.global_wonders[i] = pcity.id;
//      } built_impr_iterate_end;
//    } }
//  }
//}
//
///***************************************************************
//  Returns the next year in the Game.game.
//***************************************************************/
//int game_next_year(int year)
//{
//  int spaceshipparts, space_parts[3] = {0, 0, 0};
//
//  if (year == 1) /* hacked it to get rid of year 0 */
//    year = 0;
//
//    /* !McFred: 
//       - want year += 1 for spaceship.
//    */
//
//  /* test Game.game with 7 normal AI's, gen 4 map, foodbox 10, foodbase 0: 
//   * Gunpowder about 0 AD
//   * Railroad  about 500 AD
//   * Electricity about 1000 AD
//   * Refining about 1500 AD (212 active units)
//   * about 1750 AD
//   * about 1900 AD
//   */
//
//  /* Count how many of the different spaceship parts we can build.  Note this
//   * operates even if Enable_Space is not active. */
//  if (Game.game.spacerace) {
//    impr_type_iterate(impr) {
//      Tech_Type_id t = improvement_types[impr].tech_req;
//
//      if (!improvement_exists(impr)) {
//	continue;
//      }
//      if (building_has_effect(impr, EFT_SS_STRUCTURAL)
//	  && tech_exists(t) && Game.game.global_advances[t] != 0) {
//	space_parts[0] = 1;
//      }
//      if (building_has_effect(impr, EFT_SS_COMPONENT)
//	  && tech_exists(t) && Game.game.global_advances[t] != 0) {
//	space_parts[1] = 1;
//      }
//      if (building_has_effect(impr, EFT_SS_MODULE)
//	  && tech_exists(t) && Game.game.global_advances[t] != 0) {
//	space_parts[2] = 1;
//      }
//    } impr_type_iterate_end;
//  }
//  spaceshipparts = space_parts[0] + space_parts[1] + space_parts[2];
//
//  if( year >= 1900 || ( spaceshipparts>=3 && year>0 ) )
//    year += 1;
//  else if( year >= 1750 || spaceshipparts>=2 )
//    year += 2;
//  else if( year >= 1500 || spaceshipparts>=1 )
//    year += 5;
//  else if( year >= 1000 )
//    year += 10;
//  else if( year >= 0 )
//    year += 20;
//  else if( year >= -1000 ) /* used this line for tuning (was -1250) */
//    year += 25;
//  else
//    year += 50; 
//
//  if (year == 0) 
//    year = 1;
//
//  return year;
//}
//
///***************************************************************
//  Advance the Game.game year.
//***************************************************************/
//void game_advance_year()
//{
//  Game.game.year = game_next_year(Game.game.year);
//  Game.game.turn++;
//}
//
///***************************************************************
//...
//***************************************************************/
//void game_remove_player(player pplayer)
//{
//  if (pplayer.attribute_block.data) {
//    free(pplayer.attribute_block.data);
//    pplayer.attribute_block.data = null;
//    pplayer.attribute_block.length = 0;
//  }
//
//  if (pplayer.attribute_block_buffer.data) {
//    free(pplayer.attribute_block_buffer.data);
//    pplayer.attribute_block_buffer.data = null;
//    pplayer.attribute_block_buffer.length = 0;
//  }
//
//  if (pplayer.island_improv) {
//    free(pplayer.island_improv);
//    pplayer.island_improv = null;
//  }
//
//  conn_list_unlink_all(&pplayer.connections);
//
//  unit_list_iterate(pplayer.units, punit) 
//    game_remove_unit(punit);
//  }
//  assert(pplayer.units.foo_list_size() == 0);
//  unit_list_unlink_all(&pplayer.units);
//
//  city_list_iterate(pplayer.cities, pcity) 
//    game_remove_city(pcity);
//  }
//  assert(pplayer.cities.foo_list_size() == 0);
//  city_list_unlink_all(&pplayer.cities);
//
//  if (is_barbarian(pplayer)) Game.game.nbarbarians--;
//}
//
///***************************************************************
//...
//***************************************************************/
//void game_renumber_players(int plrno)
//{
//  int i;
//
//  for (i = plrno; i < Game.game.nplayers - 1; i++) {
//    Game.game.players[i]=Game.game.players[i+1];
//    Game.game.players[i].player_no=i;
//    conn_list_iterate(Game.game.players[i].connections, pconn)
//      pconn.player = &Game.game.players[i];
//    }
//  }
//
//  if(Game.game.player_idx>plrno) {
//    Game.game.player_idx--;
//    Game.game.player_ptr=&Game.game.players[Game.game.player_idx];
//  }
//
//  Game.game.nplayers--;
//
//  /* a bit of cleanup to keep connections sane */
//  conn_list_init(&Game.game.players[Game.game.nplayers].connections);
//  Game.game.players[Game.game.nplayers].is_connected = false;
//  Game.game.players[Game.game.nplayers].was_created = false;
//  Game.game.players[Game.game.nplayers].ai.control = false;
//  sz_strlcpy(Game.game.players[Game.game.nplayers].name, ANON_PLAYER_NAME);
//  sz_strlcpy(Game.game.players[Game.game.nplayers].username, Player_H.ANON_USER_NAME);
//}
//
///**************************************************************************
//get_player() - Return player struct pointer corresponding to player_id.
//               Eg: player_id = punit.owner, or pcity.owner
//**************************************************************************/
//player get_player(int player_id)
//{
//    return &Game.game.players[player_id];
//}
//
//boolean is_valid_player_id(int player_id)
//{
//  return player_id >= 0 && player_id < Game.game.nplayers;
//}
//
///**************************************************************************
//This function is used by is_wonder_useful to estimate if it is worthwhile
//to build the great library.
//**************************************************************************/
//int get_num_human_and_ai_players()
//{
//  return Game.game.nplayers-Game.game.nbarbarians;
//}
//
///***************************************************************
//  For various data, copy eg .name to .name_orig and put
//  translated version in .name
//  (These could be in the separate modules, but since they are
//  all almost the same, and all needed together, it seems a bit
//  easier to just do them all here.)
//***************************************************************/
//void translate_data_names()
//{
//  int i;
//
//  tech_type_iterate(tech_id) {
//    advance tthis = &advances[tech_id];
//
//    tthis.name = Q_(tthis.name_orig);
//  } tech_type_iterate_end;
//
//  unit_type_iterate(i) {
//    unit_type tthis = &Unittype_P.unit_types[i];
//
//    tthis.name = Q_(tthis.name_orig);
//  } unit_type_iterate_end;
//
//  impr_type_iterate(i) {
//    impr_type tthis = &improvement_types[i];
//
//    tthis.name = Q_(tthis.name_orig);
//  } impr_type_iterate_end;
//
//  terrain_type_iterate(i) {
//    tile_type tthis = get_tile_type(i);
//
//    tthis.terrain_name = ((strcmp(tthis.terrain_name_orig, "") != 0)
//			   ? Q_(tthis.terrain_name_orig) : "");
//
//    tthis.special_1_name = ((strcmp(tthis.special_1_name_orig, "") != 0)
//			     ? Q_(tthis.special_1_name_orig) : "");
//    tthis.special_2_name = ((strcmp(tthis.special_2_name_orig, "") != 0)
//			     ? Q_(tthis.special_2_name_orig) : "");
//  } terrain_type_iterate_end;
//
//  government_iterate(tthis) {
//    int j;
//
//    tthis.name = Q_(tthis.name_orig);
//    for(j=0; j<tthis.num_ruler_titles; j++) {
//      ruler_title that = &tthis.ruler_titles[j];
//
//      that.male_title = Q_(that.male_title_orig);
//      that.female_title = Q_(that.female_title_orig);
//    }
//  } government_iterate_end;
//  for (i=0; i<Game.game.nation_count; i++) {
//    nation_type tthis = get_nation_by_idx(i);
//
//    tthis.name = Q_(tthis.name_orig);
//    tthis.name_plural = Q_(tthis.name_plural_orig);
//  }
//  for (i=0; i<Game.game.styles_count; i++) {
//    citystyle tthis = &city_styles[i];
//
//    tthis.name = Q_(tthis.name_orig);
//  }
//
//}
//
///****************************************************************************
//  Return a prettily formatted string containing the population text.  The
//  population is passed in as the number of citizens, in thousands.
//****************************************************************************/
//final String population_to_text(int thousand_citizen)
//{
//  /* big_int_to_text can't handle negative values, and in any case we'd
//   * better not have a negative population. */
//  assert(thousand_citizen >= 0);
//  return big_int_to_text(thousand_citizen, 3);
//}
//
}