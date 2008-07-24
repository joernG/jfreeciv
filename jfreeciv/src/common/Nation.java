package common;

import utility.Log;

public class Nation{
//#include "fcintl.h"
//#include "Game.game.h"
//#include "government.h"
//#include "log.h"
//#include "mem.h"
//#include "player.h"
//#include "support.h"
//#include "tech.h"
//
//#include "nation.h"
//
//static nation_type nations = null;
//static struct team teams[MAX_NUM_TEAMS];
//
///***************************************************************
//  Returns 1 if nid is a valid nation id, else 0.
//  If returning 0, prints log message with given loglevel
//  quoting given func name, explaining problem.
//***************************************************************/
//static boolean bounds_check_nation_id(int nid, int loglevel,
//				  final String func_name)
//{
//  if (Game.game.nation_count==0) {
//    util.freelog(loglevel, "%s before nations setup", func_name);
//    return false;
//  }
//  if (nid < 0 || nid >= Game.game.nation_count) {
//    util.freelog(loglevel, "Bad nation id %d (count %d) in %s",
//	    nid, Game.game.nation_count, func_name);
//    return false;
//  }
//  return true;
//}
//
///***************************************************************
//Find nation by (translated) name
//***************************************************************/
//int find_nation_by_name(final String name)
//{
//  int i;
//
//  for(i=0; i<Game.game.nation_count; i++)
//     if(mystrcasecmp(name, get_nation_name (i)) == 0)
//	return i;
//
//  return NO_NATION_SELECTED;
//}
//
///***************************************************************
//Find nation by (untranslated) original name
//***************************************************************/
//int find_nation_by_name_orig(final String name)
//{
//  int i;
//
//  for(i=0; i<Game.game.nation_count; i++)
//     if(mystrcasecmp(name, get_nation_name_orig (i)) == 0)
//	return i;
//
//  return NO_NATION_SELECTED;
//}

	/***************************************************************************
	 * Returns (translated) name of the nation
	 **************************************************************************/
	public static final String get_nation_name(int nation) {
//		if (!bounds_check_nation_id(nation, Log.LOG_ERROR, "get_nation_name")) {
//			return "";
//		}
//		return nations[nation].name;
		return ""; //TODO
	}

// /***************************************************************
//Returns (untranslated) original name of the nation
//***************************************************************/
//final String get_nation_name_orig(int nation)
//{
//  if (!bounds_check_nation_id(nation, Log.LOG_ERROR, "get_nation_name_orig")) {
//    return "";
//  }
//  return nations[nation].name_orig;
//}
//
///***************************************************************
//Returns pointer to the array of the nation leader names, and
//sets dim to number of leaders.
//***************************************************************/
//leader get_nation_leaders(int nation, int *dim)
//{
//  if (!bounds_check_nation_id(nation, LOG_FATAL, "get_nation_leader_names")) {
//    util.die("wrong nation %d", nation);
//  }
//  *dim = nations[nation].leader_count;
//  return nations[nation].leaders;
//}
//
///****************************************************************************
//  Returns pointer to the preferred set of nations that can fork from the
//  nation.  The array is terminated by a NO_NATION_SELECTED value.
//****************************************************************************/
//int* get_nation_civilwar(int nation)
//{
//  return nations[nation].civilwar_nations;
//}
//
///***************************************************************
//Returns sex of given leader name. If names is not found,
//return 1 (meaning male).
//***************************************************************/
//boolean get_nation_leader_sex(int nation, final String name)
//{
//  int i;
//  
//  if (!bounds_check_nation_id(nation, Log.LOG_ERROR, "get_nation_leader_sex")) {
//    return false;
//  }
//  for (i = 0; i < nations[nation].leader_count; i++) {
//    if (strcmp(nations[nation].leaders[i].name, name) == 0) {
//      return nations[nation].leaders[i].is_male;
//    }
//  }
//  return true;
//}
//
///***************************************************************
//checks if given leader name exist for given nation.
//***************************************************************/
//boolean check_nation_leader_name(int nation, final String name)
//{
//  int i;
//  
//  if (!bounds_check_nation_id(nation, Log.LOG_ERROR, "check_nation_leader_name")) {
//    return true;			/* ? */
//  }
//  for (i = 0; i < nations[nation].leader_count; i++) {
//    if (strcmp(name, nations[nation].leaders[i].name) == 0) {
//      return true;
//    }
//  }
//  return false;
//}

	/***************************************************************************
	 * Returns plural name of the nation.
	 **************************************************************************/
	public static String get_nation_name_plural(int nation) {
//		if (!bounds_check_nation_id(nation, Log.LOG_ERROR, "get_nation_name_plural")) {
//			return "";
//		}
//		return nations[nation].name_plural;
		return null;
	}

// /***************************************************************
//Returns pointer to a nation 
//***************************************************************/
//nation_type get_nation_by_plr(player plr)
//{
//  assert(plr != null);
//  if (!bounds_check_nation_id(plr.nation, LOG_FATAL, "get_nation_by_plr")) {
//    util.die("wrong nation %d", plr.nation);
//  }
//  return &nations[plr.nation];
//}
//
///***************************************************************
//  ...
//***************************************************************/
//nation_type get_nation_by_idx(int nation)
//{
//  if (!bounds_check_nation_id(nation, LOG_FATAL, "get_nation_by_idx")) {
//    util.die("wrong nation %d", nation);
//  }
//  return &nations[nation];
//}
//
///***************************************************************
// Allocate space for the given number of nations.
//***************************************************************/
//void nations_alloc(int num)
//{
//  nations = (nation_type )fc_calloc(num, sizeof(struct nation_type));
//  Game.game.nation_count = num;
//}
//
///***************************************************************
// De-allocate resources associated with the given nation.
//***************************************************************/
//static void nation_free(int nation)
//{
//  int i;
//  nation_type p = get_nation_by_idx(nation);
//
//  for (i = 0; i < p.leader_count; i++) {
//    free(p.leaders[i].name);
//  }
//  if (p.leaders) {
//    free(p.leaders);
//    p.leaders = null;
//  }
//  
//  if (p.class) {
//    free(p.class);
//    p.class = null;
//  }
//  
//  if (p.legend) {
//    free(p.legend);
//    p.legend = null;
//  }
//
//  if (p.civilwar_nations) {
//    free(p.civilwar_nations);
//    p.civilwar_nations = null;
//  }
//
//  if (p.parent_nations) {
//    free(p.parent_nations);
//    p.parent_nations = null;
//  }
//
//  nation_city_names_free(p.city_names);
//  p.city_names = null;
//}
//
///***************************************************************
// De-allocate the currently allocated nations.
//***************************************************************/
//void nations_free()
//{
//  int nation;
//
//  if (!nations) {
//    return;
//  }
//
//  for (nation = 0; nation < Game.game.nation_count; nation++) {
//    nation_free(nation);
//  }
//
//  free(nations);
//  nations = null;
//  Game.game.nation_count = 0;
//}
//
///***************************************************************
// deallocates an array of city names. needs to be separate so 
// server can use it individually (misc_city_names)
//***************************************************************/
//void nation_city_names_free(city_name city_names)
//{
//  int i;
//
//  if (city_names) {
//    /* 
//     * Unfortunately, this monstrosity of a loop is necessary given
//     * the setup of city_names.  But that setup does make things
//     * simpler elsewhere.
//     */
//    for (i = 0; city_names[i].name; i++) {
//      free(city_names[i].name);
//    }
//    free(city_names);
//  }
//}
//
///***************************************************************
//Returns nation's city style
//***************************************************************/
//int get_nation_city_style(int nation)
//{
//  if (!bounds_check_nation_id(nation, LOG_FATAL, "get_nation_city_style")) {
//    util.die("wrong nation %d", nation);
//  }
//  return nations[nation].city_style;
//}
//
///***************************************************************
//  Returns the id of a team given its name, or TEAM_NONE if 
//  not found.
//***************************************************************/
//Team_Type_id team_find_by_name(final String team_name)
//{
//  assert(team_name != null);
//
//  team_iterate(pteam) {
//     if(mystrcasecmp(team_name, pteam.name) == 0) {
//	return pteam.id;
//     }
//  } team_iterate_end;
//
//  return TEAM_NONE;
//}
//
///***************************************************************
//  Returns pointer to a team given its id
//***************************************************************/
//team team_get_by_id(Team_Type_id id)
//{
//  assert(id == TEAM_NONE || (id < MAX_NUM_TEAMS && id >= 0));
//  if (id == TEAM_NONE) {
//    return null;
//  }
//  return &teams[id];
//}
//
///***************************************************************
//  Count living members of given team
//***************************************************************/
//int team_count_members_alive(Team_Type_id id)
//{
//  team pteam = team_get_by_id(id);
//  int count = 0;
//
//  if (pteam == null) {
//    return 0;
//  }
//  assert(pteam.id < MAX_NUM_TEAMS && pteam.id != TEAM_NONE);
//  for(player pplayer: Game.game.players){
//    if (pplayer.is_alive && pplayer.team == pteam.id) {
//      count++;
//    }
//  }
//  return count;
//}
//
///***************************************************************
//  Set a player to a team. Removes previous team affiliation,
//  creates a new team if it does not exist.
//***************************************************************/
//void team_add_player(player pplayer, final String team_name)
//{
//  Team_Type_id team_id, i;
//
//  assert(pplayer != null && team_name != null);
//
//  /* find or create team */
//  team_id = team_find_by_name(team_name);
//  if (team_id == TEAM_NONE) {
//    /* see if we have another team available */
//    for (i = 0; i < MAX_NUM_TEAMS; i++) {
//      if (teams[i].id == TEAM_NONE) {
//        team_id = i;
//        break;
//      }
//    }
//    /* check if too many teams */
//    if (team_id == TEAM_NONE) {
//      util.die("Impossible: Too many teams!");
//    }
//    /* add another team */
//    teams[team_id].id = team_id;
//    sz_strlcpy(teams[team_id].name, team_name);
//  }
//  pplayer.team = team_id;
//}
//
///***************************************************************
//  Removes a player from a team, and removes the team if empty of
//  players
//***************************************************************/
//void team_remove_player(player pplayer)
//{
//  boolean others = false;
//
//  if (pplayer.team == TEAM_NONE) {
//    return;
//  }
//
//  assert(pplayer.team < MAX_NUM_TEAMS && pplayer.team >= 0);
//
//  /* anyone else using my team? */
//  for(player aplayer: Game.game.players){
//    if (aplayer.team == pplayer.team && aplayer != pplayer) {
//      others = true;
//      break;
//    }
//  }
//
//  /* no other team members left? remove team! */
//  if (!others) {
//    teams[pplayer.team].id = TEAM_NONE;
//  }
//  pplayer.team = TEAM_NONE;
//}

/***************************************************************
  Initializes team structure
***************************************************************/
public static void team_init()
{
//  Team_Type_id i;
//
//  assert(TEAM_NONE < 0 || TEAM_NONE >= MAX_NUM_TEAMS);
//
//  for (i = 0; i < MAX_NUM_TEAMS; i++) {
//    /* mark as unused */
//    teams[i].id = TEAM_NONE;
//    teams[i].name[0] = '\0';
//  }
}
}