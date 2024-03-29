package server;

import port.util;
import common.map.tile;
import common.player.player;
import common.unit.goto_move_restriction;
import common.unit.unit;

import server.gotohand.goto_result;
import utility.Log;

public class Gotohand {
	// #include "combat.h"
	// #include "Game.game.h"
	// #include "log.h"
	// #include "Map.map.h"
	// #include "mem.h"
	// #include "rand.h"
	//
	// #include "airgoto.h"
	// #include "maphand.h"
	// #include "settlers.h"
	// #include "unithand.h"
	// #include "unittools.h"
	//
	// #include "aitools.h"
	//
	// #include "gotohand.h"
	//
	// struct move_cost_map warmap;
	//
	// /*
	// * These settings should be either true or false. They are used for
	// * finding routes for airplanes - the airplane doesn't want to fly
	// * through occupied territory, but most territory will be either
	// * fogged or unknown entirely. See airspace_looks_safe(). Note that
	// * this is currently only used by the move-counting function
	// * air_can_move_between(), not by the path-finding function (whatever
	// * that may be).
	// */
	// #define AIR_ASSUMES_UNKNOWN_SAFE true
	// #define AIR_ASSUMES_FOGGED_SAFE true
	//
	// static boolean airspace_looks_safe(tile ptile, player pplayer);
	//
	//
	// /* These are used for all GOTO's */
	//
	// /* A byte must be able to hold this value i.e. is must be less than
	// 256. */
	// public static final int MAXCOST = 255;
	// public static final int MAXARRAYS = 10000;
	// public static final int ARRAYLENGTH = 10;
	//
	// struct mappos_array {
	// int first_pos;
	// int last_pos;
	// tile tile[ARRAYLENGTH];
	// mappos_array next_array;
	// };
	//
	// struct array_pointer {
	// mappos_array first_array;
	// mappos_array last_array;
	// };
	//
	// static mappos_array mappos_arrays[MAXARRAYS];
	// static struct array_pointer cost_lookup[MAXCOST];
	// static int array_count;
	// static int lowest_cost;
	// static int highest_cost;
	//
	//
	// /*************************************************************************
	// Used to check if the warmap distance to a position is "finite".
	// *************************************************************************/
	// boolean is_dist_finite(int dist)
	// {
	//
	// return (dist < MAXCOST);
	// }
	//
	// /**************************************************************************
	// ...
	// **************************************************************************/
	// static void init_queue()
	// {
	// int i;
	// static boolean is_initialized = false;
	//
	// if (!is_initialized) {
	// for (i = 0; i < MAXARRAYS; i++) {
	// mappos_arrays[i] = null;
	// }
	// is_initialized = true;
	// }
	//
	// for (i = 0; i < MAXCOST; i++) {
	// cost_lookup[i].first_array = null;
	// cost_lookup[i].last_array = null;
	// }
	// array_count = 0;
	// lowest_cost = 0;
	// highest_cost = 0;
	// }
	//
	// /**************************************************************************
	// ...
	// **************************************************************************/
	// static mappos_array get_empty_array()
	// {
	// mappos_array parray;
	// if (!mappos_arrays[array_count])
	// mappos_arrays[array_count] = fc_malloc(sizeof(struct mappos_array));
	// parray = mappos_arrays[array_count++];
	// parray.first_pos = 0;
	// parray.last_pos = -1;
	// parray.next_array = null;
	// return parray;
	// }
	//
	// /**************************************************************************
	// ...
	// **************************************************************************/
	// static void add_to_maPqueue(int cost, tile ptile)
	// {
	// mappos_array our_array;
	//
	// assert(cost < MAXCOST && cost >= 0);
	//
	// our_array = cost_lookup[cost].last_array;
	// if (!our_array) {
	// our_array = get_empty_array();
	// cost_lookup[cost].first_array = our_array;
	// cost_lookup[cost].last_array = our_array;
	// } else if (our_array.last_pos == ARRAYLENGTH-1) {
	// our_array.next_array = get_empty_array();
	// our_array = our_array.next_array;
	// cost_lookup[cost].last_array = our_array;
	// }
	//
	// our_array.tile[++(our_array.last_pos)] = ptile;
	// if (cost > highest_cost)
	// highest_cost = cost;
	// util.freelog(Log.LOG_DEBUG, "adding cost:%i at %i,%i", cost, ptile.x, ptile.y);
	// }
	//
	// /**************************************************************************
	// ...
	// **************************************************************************/
	// static tile get_from_maPqueue()
	// {
	// mappos_array our_array;
	// tile ptile;
	//
	// util.freelog(Log.LOG_DEBUG, "trying get");
	// while (lowest_cost < MAXCOST) {
	// if (lowest_cost > highest_cost)
	// return false;
	// our_array = cost_lookup[lowest_cost].first_array;
	// if (!our_array) {
	// lowest_cost++;
	// continue;
	// }
	// if (our_array.last_pos < our_array.first_pos) {
	// if (our_array.next_array) {
	// cost_lookup[lowest_cost].first_array = our_array.next_array;
	// continue; /* note NOT "lowest_cost++;" */
	// } else {
	// cost_lookup[lowest_cost].first_array = null;
	// lowest_cost++;
	// continue;
	// }
	// }
	// ptile = our_array.tile[our_array.first_pos];
	// our_array.first_pos++;
	// return ptile;
	// }
	// return null;
	// }
	//
	// /**************************************************************************
	// Reset the movecosts of the warmap.
	// **************************************************************************/
	// static void init_warmap(tile orig_tile, enum unit_move_type move_type)
	// {
	// if (warmap.size != Map_H.MAX_MAP_INDEX) {
	// warmap.cost = fc_realloc(warmap.cost,
	// Map_H.MAX_MAP_INDEX * sizeof(*warmap.cost));
	// warmap.seacost = fc_realloc(warmap.seacost,
	// Map_H.MAX_MAP_INDEX * sizeof(*warmap.seacost));
	// warmap.vector = fc_realloc(warmap.vector,
	// Map_H.MAX_MAP_INDEX * sizeof(*warmap.vector));
	// warmap.size = Map_H.MAX_MAP_INDEX;
	// }
	//
	// init_queue();
	//
	// switch (move_type) {
	// case unit_move_type.LAND_MOVING:
	// case unit_move_type.HELI_MOVING:
	// case unit_move_type.AIR_MOVING:
	// assert(sizeof(*warmap.cost) == sizeof(char));
	// memset(warmap.cost, MAXCOST, Map.map.xsize * Map.map.ysize);
	// WARMAP_COST(orig_tile) = 0;
	// break;
	// case unit_move_type.SEA_MOVING:
	// assert(sizeof(*warmap.seacost) == sizeof(char));
	// memset(warmap.seacost, MAXCOST, Map.map.xsize * Map.map.ysize);
	// WARMAP_SEACOST(orig_tile) = 0;
	// break;
	// default:
	// util.freelog(Log.LOG_ERROR, "Bad move_type in init_warmap().");
	// }
	// }
	//
	//
	// /**************************************************************************
	// This creates a movecostmap (warmap), which is a two-dimentional array
	// corresponding to the real Map.map. The value of a position is the number of
	// moves it would take to get there. If the function found no route the cost
	// is MAXCOST. (the value it is initialized with)
	// For sea units we let the map overlap onto the land one field, to allow
	// transporters and shore bombardment (ships can target the shore).
	// This map does NOT take enemy units onto account, nor ZOC.
	// Let generate_warmap interface to this function. Sometimes (in the AI when
	// using transports) this function will be called directly.
	//
	// It is an search via Dijkstra's Algorithm
	// (see fx http://odin.ee.uwa.edu.au/~morris/Year2/PLDS210/dijkstra.html)
	// ie it piles tiles on a queue, and then use those tiles to find more
	// tiles,
	// until all tiles we can reach are visited. To avoid making the warmap
	// potentially too big (heavy to calculate), the warmap is initialized with
	// a maxcost value, limiting the maximum length.
	//
	// Note that this function is responsible for 20% of the CPU usage of
	// freeciv...
	//
	// This function is used by the AI in various cases when it is searching for
	// something to do with a unit. Maybe we could make a version that processed
	// the tiles in order after how many moves it took the unit to get to them,
	// and then gave them to the unit. This would achive 2 things:
	// -The unit could stop the function the instant it found what it was
	// looking
	// for, or when it had already found something reasonably close and the
	// distance
	// to the tiles later being searched was reasoable big. In both cases
	// avoiding
	// to build the full warmap.
	// -The warmap would avoid examining a tile multiple times due to a series
	// of
	// path with succesively smaller cost, since we would always have the
	// smallest
	// possible cost the first time.
	// This would be done by inserting the tiles in a list after their move_cost
	// as they were found.
	// **************************************************************************/
	// void really_generate_warmap(city pcity, unit punit,
	// enum unit_move_type move_type)
	// {
	// int move_cost;
	// tile orig_tile;
	// boolean igter;
	// int maxcost = THRESHOLD * 6 + 2; /* should be big enough without being
	// TOO big */
	// tile ptile;
	// player pplayer;
	//
	// if (pcity) {
	// orig_tile = pcity.tile;
	// pplayer = City.city_owner(pcity);
	// } else {
	// orig_tile = punit.tile;
	// pplayer = punit.unit_owner();
	// }
	//
	// init_warmap(orig_tile, move_type);
	// add_to_maPqueue(0, orig_tile);
	//
	// if (punit && unit_flag(punit, F_IGTER))
	// igter = true;
	// else
	// igter = false;
	//
	// /* FIXME: Should this apply only to Eunit_flag_id.F_CITIES units? -- jjm */
	// if (punit
	// && unit_flag(punit, Eunit_flag_id.F_SETTLERS)
	// && punit.move_rate()==3)
	// maxcost /= 2;
	// /* (?) was punit.type == U_SETTLERS -- dwp */
	//
	// while ((ptile = get_from_maPqueue())) {
	// /* Just look up the cost value once. This is a minor optimization but
	// * it makes a big difference since this code is called so much. */
	// unsigned char cost = ((move_type == unit_move_type.SEA_MOVING)
	// ? WARMAP_SEACOST(ptile) : WARMAP_COST(ptile));
	//
	// adjc_dir_iterate(ptile, tile1, dir) {
	// switch (move_type) {
	// case unit_move_type.LAND_MOVING:
	// if (WARMAP_COST(tile1) <= cost)
	// continue; /* No need for all the calculations */
	//
	// if (Terrain_H.is_ocean(tile1.terrain)) {
	// if (punit && ground_unit_transporter_capacity(tile1, pplayer) > 0)
	// move_cost = Unit_H.SINGLE_MOVE;
	// else
	// continue;
	// } else if (Terrain_H.is_ocean(ptile.terrain)) {
	// int base_cost = get_tile_type(tile1.terrain).movement_cost *
	// Unit_H.SINGLE_MOVE;
	// move_cost = igter ? MOVE_COST_ROAD : Math.min(base_cost,
	// punit.move_rate());
	// } else if (igter)
	// /* NOT c = 1 (Syela) [why not? - Thue] */
	// move_cost = (ptile.move_cost[dir] != 0 ? Unit_H.SINGLE_MOVE : 0);
	// else if (punit)
	// move_cost = Math.min(ptile.move_cost[dir], punit.move_rate());
	// /* else c = ptile.move_cost[k];
	// This led to a bad bug where a unit in a swamp was considered too far away
	// */
	// else { /* we have a city */
	// int tmp = tile1.move_cost[DIR_REVERSE(dir)];
	// move_cost = (ptile.move_cost[dir] + tmp +
	// (ptile.move_cost[dir] > tmp ? 1 : 0))/2;
	// }
	//
	// move_cost += cost;
	// if (WARMAP_COST(tile1) > move_cost && move_cost < maxcost) {
	// WARMAP_COST(tile1) = move_cost;
	// add_to_maPqueue(move_cost, tile1);
	// }
	// break;
	//
	//
	// case unit_move_type.SEA_MOVING:
	// move_cost = Unit_H.SINGLE_MOVE;
	// move_cost += cost;
	// if (WARMAP_SEACOST(tile1) > move_cost && move_cost < maxcost) {
	// /* by adding the move_cost to the warmap regardless if we
	// can move between we allow for shore bombardment/transport
	// to inland positions/etc. */
	// WARMAP_SEACOST(tile1) = move_cost;
	// if (ptile.move_cost[dir] == MOVE_COST_FOR_VALID_SEA_STEP) {
	// add_to_maPqueue(move_cost, tile1);
	// }
	// }
	// break;
	// default:
	// move_cost = 0; /* silence compiler warning */
	// util.die("Bad/unimplemented move_type in really_generate_warmap().");
	// }
	// } adjc_dir_iterate_end;
	// }
	//
	// util.freelog(Log.LOG_DEBUG, "Generated warmap for (%d,%d).",
	// TILE_XY(orig_tile));
	// }
	//
	// /**************************************************************************
	// This is a wrapper for really_generate_warmap that checks if the warmap we
	// want is allready in existence. Also calls correctly depending on whether
	// pcity and/or punit is nun-null.
	// FIXME: Why is the movetype not used initialized on the warmap? Leaving it
	// for now.
	// **************************************************************************/
	// void generate_warmap(city pcity, unit punit)
	// {
	// util.freelog(Log.LOG_DEBUG, "Generating warmap, pcity = %s, punit = %s",
	// (pcity ? pcity.name : "null"),
	// (punit ? punit.unit_type().name : "null"));
	//
	// if (punit) {
	// /*
	// * Checking for an existing warmap. If the previous warmap was for
	// * a city and our unit is in that city, use city's warmap.
	// */
	// if (pcity && pcity == warmap.warcity) {
	// return;
	// }
	//
	// /*
	// * If the previous warmap was for the same unit and it's still
	// * correct (warmap.(sea)cost[x][y] == 0), reuse it.
	// */
	// if (warmap.warunit == punit &&
	// (Unit.is_sailing_unit(punit) ? (WARMAP_SEACOST(punit.tile) == 0)
	// : (WARMAP_COST(punit.tile) == 0))) {
	// return;
	// }
	//
	// pcity = null;
	// }
	//
	// warmap.warcity = pcity;
	// warmap.warunit = punit;
	//
	// if (punit) {
	// if (Unit.is_sailing_unit(punit)) {
	// really_generate_warmap(pcity, punit, unit_move_type.SEA_MOVING);
	// } else {
	// really_generate_warmap(pcity, punit, unit_move_type.LAND_MOVING);
	// }
	// warmap.orig_tile = punit.tile;
	// } else {
	// really_generate_warmap(pcity, punit, unit_move_type.LAND_MOVING);
	// really_generate_warmap(pcity, punit, unit_move_type.SEA_MOVING);
	// warmap.orig_tile = pcity.tile;
	// }
	// }
	//
	// /**************************************************************************
	// Return the direction that takes us most directly to dest_x,dest_y.
	// The direction is a value for use in DIR_DX[] and DIR_DY[] arrays.
	//
	// FIXME: This is a bit crude; this only gives one correct path, but
	// sometimes
	// there can be more than one straight path (fx going NW then W is the
	// same as going W then NW)
	// **************************************************************************/
	// static int straightest_direction(tile src_tile,
	// tile dst_tile)
	// {
	// int best_dir;
	// int diff_x, diff_y;
	//
	// /* Should we go up or down, east or west: diff_x/y is the "step" in x/y.
	// */
	// map_distance_vector(&diff_x, &diff_y, src_tile, dst_tile);
	//
	// if (diff_x == 0) {
	// best_dir = (diff_y > 0) ? DIR8_SOUTH : DIR8_NORTH;
	// } else if (diff_y == 0) {
	// best_dir = (diff_x > 0) ? DIR8_EAST : DIR8_WEST;
	// } else if (diff_x > 0) {
	// best_dir = (diff_y > 0) ? DIR8_SOUTHEAST : DIR8_NORTHEAST;
	// } else if (diff_x < 0) {
	// best_dir = (diff_y > 0) ? DIR8_SOUTHWEST : DIR8_NORTHWEST;
	// } else {
	// assert(0!=1);
	// best_dir = 0;
	// }
	//
	// return (best_dir);
	// }
	//
	// /**************************************************************************
	// Can we move between for ZOC? (only for land units). Includes a special
	// case only relevant for GOTOs (see below). came_from is a bit-vector
	// containing the directions we could have come from.
	// **************************************************************************/
	// static boolean goto_zoc_ok(unit punit, tile src_tile,
	// tile dest_tile, dir_vector came_from)
	// {
	// if (can_step_taken_wrt_to_zoc
	// (punit.type, punit.unit_owner(), src_tile, dest_tile))
	// return true;
	//
	// /*
	// * Both positions are currently enemy ZOC. Since the AI depend on
	// * it's units bumping into enemies while on GOTO it need to be able
	// * to plot a course which attacks enemy units.
	// *
	// * This code makes sure that if there is a unit in the way that the
	// * GOTO made a path over (attack), the unit's ZOC effect (which is
	// * now irrelevant, it is dead) doesn't have any effect.
	// *
	// * That is, if there is a unit standing on a tile that we (possibly)
	// * came from, we will not take it into account.
	// *
	// * If this wasn't here a path involving two enemy units would not be
	// * found by the algoritm.
	// */
	//
	// /* If we just started the GOTO the enemy unit blocking ZOC on the tile
	// we come from is still alive. */
	// if (Map.same_pos(src_tile, punit.tile)
	// && !Unit.is_non_allied_unit_tile(dest_tile, punit.unit_owner())) {
	// return false;
	// }
	//
	// {
	// player owner = punit.unit_owner();
	//
	// adjc_dir_iterate(src_tile, ptile, dir) {
	// /* if we didn't come from there */
	// if (!BV_ISSET(came_from, dir)
	// && !Terrain_H.is_ocean(ptile.terrain)
	// /* and there is an enemy there */
	// && Unit.is_enemy_unit_tile(ptile, owner)) {
	// /* then it counts in the zoc claculation */
	// return false;
	// }
	// } adjc_dir_iterate_end;
	// return true;
	// }
	// }
	//
	// /* DANGER_MOVE is the cost of movement assigned to "dangerous" tiles. It
	// * is arbitrarily larger than Unit_H.SINGLE_MOVE to make it a penalty. */
	// #define DANGER_MOVE (2 * Unit_H.SINGLE_MOVE + 1)
	//
	// /**************************************************************************
	// This function mark paths on the warmaps vector showing the shortest path
	// to
	// the destination.
	//
	// It is an search via Dijkstra's Algorithm
	// (see fx http://odin.ee.uwa.edu.au/~morris/Year2/PLDS210/dijkstra.html)
	// We start with a list with only one tile (the
	// starttile), and then tries to move in each of the 8 directions from
	// there.
	// This then gives us more tiles to more from. Repeat until we meet the
	// destination or there is no remaining tiles.
	// All possible paths coming out of the starttile are stored in
	// local_vector.
	// After we've reached the destination, only the paths connecting starttile
	// with the destination are copied from local_vector to the warmap's vector.
	//
	// Whenever we reach a tile we see how many movepoints it took to get there,
	// and compare it to eventual previous moves to the tile. If the route is
	// faster or just as fast we mark the direction from which we came on the
	// local_vector array and also record the new movecost. If we come to a
	// tile we have meet before, and the cost of the route we have taken is
	// smaller than the previous one we add the tile to the list again, to get
	// the tiles it leads to updated etc. The change in move_cost for the tile
	// will then propagate out.
	// We have a maxcost, as an upper limit for the length of gotos. When we
	// arrive at the GOTO destination with a path we know that we don't have to
	// follow other paths that have exceeded this length, and set maxcost to
	// length+1. (the +1 because we still want to find alternative paths of the
	// same length, and comparisons with maxcost are done with <).
	// When we have found all paths of the shortest length to the destination we
	// backtrack from the destination tile all the way back to the start tile,
	// marking the routes on the warmap's vector as we go. (again by piling
	// tiles
	// on the queue). find_a_direction() can then use these routes to choose a
	// path.
	// Note that some move_cost values, like for going into unknown territory
	// and
	// attacking while on GOTO is values not directly related to the move cost,
	// but rather meant to encourage/discourage certain behaviour.
	//
	// The restrictions GOTO_MOVE_CARDINAL_ONLY and GOTO_MOVE_STRAIGHTEST are
	// currently only used for settlers building irrigation and roads.
	//
	// To avoid RR loops (which may cause find_a_direction() to move a unit in
	// cute little cycles forever and ever...), when we have more than one path
	// to a tile and the second one is via RR (move_cost 0), we do NOT mark the
	// extra path.
	//
	// At one point we used dir_ok to test if we were going in the right
	// direction,
	// but the cost of the dir_ok call was too high in itself, and the penalty
	// sometimes lead to suboptimal paths chosen. The current implementation
	// have
	// the disadvantage that it will search until all move_cost's are larger
	// than
	// one found to the target, which can be expensive on RR (but not as bad as
	// you might think as most AI GOTO's will be long anyway).
	//
	// Often we'll already have a working warmap, So we could maybe avoid using
	// this function to find a path if really_generate_warmap marked the paths
	// on the warmap. On the other side, most of the time GOTOs will be
	// relatively
	// short, and doing extra work in really_generate_warmap, which will always
	// check a lot of tiles, may not be worth it. really_generate_warmap() is
	// also
	// more crude than this function, so it would have to be expanded (which
	// would
	// be _very_ expensive in CPU).
	//
	// FIXME: this is a bit of a mess in not respecting FoW, and only sometimes
	// respecting if a square is known. (not introduced by me though) -Thue
	// **************************************************************************/
	// static boolean find_the_shortest_path(unit punit,
	// tile dest_tile,
	// goto_move_restriction restriction)
	// {
	// player pplayer = punit.unit_owner();
	// boolean igter;
	// tile ptile, *orig_tile;
	// tile psrctile, *pdesttile;
	// enum unit_move_type move_type = punit.unit_type().move_type;
	// int maxcost = MAXCOST;
	// int move_cost, total_cost;
	// int straight_dir = 0; /* init to silence compiler warning */
	// dir_vector local_vector[Map_H.MAX_MAP_INDEX];
	// #define LOCAL_VECTOR(ptile) local_vector[(ptile).index]
	// unit pcargo;
	// /*
	// * Land/air units use warmap.cost while sea units use
	// * warmap.seacost. Some code will use both.
	// */
	// unsigned char *warmap_cost;
	//
	// orig_tile = punit.tile;
	//
	// if (Map.same_pos(dest_tile, orig_tile)) {
	// return true; /* [Kero] */
	// }
	//
	// BV_CLR_ALL(LOCAL_VECTOR(orig_tile));
	//
	// init_warmap(punit.tile, move_type);
	// warmap_cost = (move_type == unit_move_type.SEA_MOVING) ? warmap.seacost : warmap.cost;
	// add_to_maPqueue(0, orig_tile);
	//
	// if (punit && unit_flag(punit, F_IGTER))
	// igter = true;
	// else
	// igter = false;
	//
	// /* If we have a passenger abord a ship we must be sure he can disembark
	// This shouldn't be neccesary, as ZOC does not have an effect for sea-land
	// movement, but some code in aiunit.c assumes the goto work like this, so
	// I will leave it for now */
	// if (move_type == unit_move_type.SEA_MOVING) {
	// pcargo = other_passengers(punit);
	// if (pcargo)
	// if (Terrain_H.is_ocean(dest_tile.terrain) ||
	// !Unit.is_non_allied_unit_tile(dest_tile, pcargo.unit_owner())
	// || City.is_allied_city_tile(dest_tile, pcargo.unit_owner())
	// || unit_flag(pcargo, F_MARINES)
	// || is_my_zoc(pcargo.unit_owner(), dest_tile))
	// pcargo = null;
	// } else
	// pcargo = null;
	//
	// /* until we have found the shortest paths */
	// while ((ptile = get_from_maPqueue())) {
	// psrctile = ptile;
	//
	// if (restriction == GOTO_MOVE_STRAIGHTEST)
	// straight_dir = straightest_direction(ptile, dest_tile);
	//
	// /* Try to move to all tiles adjacent to x,y. The coordinats of the tile
	// we
	// try to move to are x1,y1 */
	// adjc_dir_iterate(ptile, tile1, dir) {
	// if ((restriction == GOTO_MOVE_CARDINAL_ONLY)
	// && !is_cardinal_dir(dir)) {
	// continue;
	// }
	//
	// pdesttile = tile1;
	//
	// switch (move_type) {
	// case unit_move_type.LAND_MOVING:
	// if (WARMAP_COST(tile1) <= WARMAP_COST(ptile))
	// continue; /* No need for all the calculations. Note that this also
	// excludes
	// RR loops, ie you can't create a cycle with the same move_cost */
	//
	// if (Terrain_H.is_ocean(pdesttile.terrain)) {
	// if (ground_unit_transporter_capacity(tile1, punit.unit_owner()) <= 0)
	// continue;
	// else
	// move_cost = 3;
	// } else if (Terrain_H.is_ocean(psrctile.terrain)) {
	// int base_cost = get_tile_type(pdesttile.terrain).movement_cost *
	// Unit_H.SINGLE_MOVE;
	// move_cost = igter ? 1 : Math.min(base_cost, punit.move_rate());
	// } else if (igter)
	// move_cost = (psrctile.move_cost[dir] != 0 ? Unit_H.SINGLE_MOVE : 0);
	// else
	// move_cost = Math.min(psrctile.move_cost[dir], punit.move_rate());
	//
	// if (!pplayer.ai.control && !Maphand.map_is_known(tile1, pplayer)) {
	// /* Don't go into the unknown. 5*Unit_H.SINGLE_MOVE is an arbitrary deterrent. */
	// move_cost = (restriction == GOTO_MOVE_STRAIGHTEST) ? Unit_H.SINGLE_MOVE :
	// 5*Unit_H.SINGLE_MOVE;
	// } else if (Unit.is_non_allied_unit_tile(pdesttile, punit.unit_owner())) {
	// if (Terrain_H.is_ocean(psrctile.terrain) && !unit_flag(punit, F_MARINES)) {
	// continue; /* Attempting to attack from a ship */
	// }
	//
	// if (!Map.same_pos(tile1, dest_tile)) {
	// /* Allow players to target anything */
	// if (pplayer.ai.control) {
	// if ((null==Unit.is_enemy_unit_tile(pdesttile, punit.unit_owner())
	// || !is_military_unit(punit))
	// && !is_diplomat_unit(punit)) {
	// continue; /* unit is non_allied and non_enemy, ie non_attack */
	// } else {
	// move_cost = Unit_H.SINGLE_MOVE; /* Attack cost */
	// }
	// } else {
	// continue; /* non-AI players don't attack on goto */
	// }
	// } else {
	// move_cost = Unit_H.SINGLE_MOVE;
	// }
	// } else if (is_non_allied_city_tile(pdesttile, punit.unit_owner())) {
	// if (Terrain_H.is_ocean(psrctile.terrain) && !unit_flag(punit, F_MARINES)) {
	// continue; /* Attempting to attack from a ship */
	// }
	//
	// if ((is_non_attack_city_tile(pdesttile, punit.unit_owner())
	// || !is_military_unit(punit))) {
	// if (!is_diplomat_unit(punit)
	// && !Map.same_pos(tile1, dest_tile)) {
	// /* Allow players to target anything */
	// continue;
	// } else {
	// /* Assign the basic move cost. There's a penalty for
	// * dangerous tiles. */
	// move_cost
	// = ((unit_loss_pct(punit.unit_owner(), tile1, punit) > 0)
	// ? DANGER_MOVE : Unit_H.SINGLE_MOVE);
	// }
	// }
	// } else if (!goto_zoc_ok(punit, ptile, tile1, LOCAL_VECTOR(ptile))) {
	// continue;
	// }
	//
	// if (restriction == GOTO_MOVE_STRAIGHTEST && dir == straight_dir)
	// move_cost /= Unit_H.SINGLE_MOVE;
	//
	// total_cost = move_cost + WARMAP_COST(ptile);
	// break;
	//
	// case unit_move_type.SEA_MOVING:
	// if (WARMAP_SEACOST(tile1) <= WARMAP_SEACOST(ptile))
	// continue; /* No need for all the calculations */
	//
	// /* allow ships to target a shore */
	// if (psrctile.move_cost[dir] != MOVE_COST_FOR_VALID_SEA_STEP
	// && !Map.same_pos(tile1, dest_tile)) {
	// continue;
	// } else if (unit_loss_pct(punit.unit_owner(), tile1, punit) > 0) {
	// /* Impose a penalty for travel over dangerous tiles. */
	// move_cost = DANGER_MOVE;
	// } else {
	// move_cost = Unit_H.SINGLE_MOVE;
	// }
	//
	// /* See previous comment on pcargo */
	// if (Map.same_pos(tile1, dest_tile) && pcargo
	// && move_cost < 20 * Unit_H.SINGLE_MOVE
	// && !is_my_zoc(pcargo.unit_owner(), ptile)) {
	// move_cost = 20 * Unit_H.SINGLE_MOVE;
	// }
	//
	// if (!pplayer.ai.control && !Maphand.map_is_known(tile1, pplayer))
	// move_cost = (restriction == GOTO_MOVE_STRAIGHTEST) ? Unit_H.SINGLE_MOVE :
	// 5*Unit_H.SINGLE_MOVE; /* arbitrary deterrent. */
	//
	// /* We don't allow attacks during GOTOs here; you can almost
	// always find a way around enemy units on sea */
	// if (!Map.same_pos(tile1, dest_tile)) {
	// if (Unit.is_non_allied_unit_tile(pdesttile, punit.unit_owner())
	// || is_non_allied_city_tile(pdesttile, punit.unit_owner()))
	// continue;
	// }
	//
	// if ((restriction == GOTO_MOVE_STRAIGHTEST) && (dir == straight_dir))
	// move_cost /= Unit_H.SINGLE_MOVE;
	//
	// total_cost = move_cost + WARMAP_SEACOST(ptile);
	//
	// /* For the ai, maybe avoid going close to enemies. */
	// if (pplayer.ai.control &&
	// WARMAP_SEACOST(ptile) < punit.moves_left
	// && total_cost < maxcost
	// && total_cost >= punit.moves_left - (Unit.get_transporter_capacity(punit) >
	// punit.unit_type().attack_strength ? 3 : 2)
	// && enemies_at(punit, tile1)) {
	// total_cost += punit.move_rate();
	// util.freelog(Log.LOG_DEBUG, "%s#%d@(%d,%d) dissuaded from (%d,%d) . (%d,%d)",
	// punit.unit_type().name, punit.id,
	// TILE_XY(punit.tile), TILE_XY(tile1), TILE_XY(dest_tile));
	// }
	// break;
	//
	// case unit_move_type.AIR_MOVING:
	// case unit_move_type.HELI_MOVING:
	// if (WARMAP_COST(tile1) <= WARMAP_COST(ptile))
	// continue; /* No need for all the calculations */
	//
	// move_cost = Unit_H.SINGLE_MOVE;
	// /* Planes could run out of fuel, therefore we don't care if territory
	// is unknown. Also, don't attack except at the destination. */
	//
	// if (!Map.same_pos(tile1, dest_tile)
	// && !airspace_looks_safe(tile1, pplayer)) {
	// /* If it's not our destination, we check if it's safe */
	// continue;
	// }
	//
	// if ((restriction == GOTO_MOVE_STRAIGHTEST) && (dir == straight_dir))
	// move_cost /= Unit_H.SINGLE_MOVE;
	// total_cost = move_cost + WARMAP_COST(ptile);
	// break;
	//
	// default:
	// move_cost = MAXCOST; /* silence compiler warning */
	// total_cost = MAXCOST; /* silence compiler warning */
	// util.die("Bad move_type in find_the_shortest_path().");
	// } /****** end switch ******/
	//
	// /* Add the route to our warmap if it is worth keeping */
	// if (total_cost < maxcost) {
	// if (warmap_cost[tile1.index] > total_cost) {
	// warmap_cost[tile1.index] = total_cost;
	// add_to_maPqueue(total_cost, tile1);
	// BV_CLR_ALL(LOCAL_VECTOR(tile1));
	// BV_SET(LOCAL_VECTOR(tile1), DIR_REVERSE(dir));
	// util.freelog(Log.LOG_DEBUG,
	// "Candidate: %s from (%d, %d) to (%d, %d), cost %d",
	// dir_get_name(dir), TILE_XY(ptile), TILE_XY(tile1),
	// total_cost);
	// } else if (warmap_cost[tile1.index] == total_cost) {
	// BV_SET(LOCAL_VECTOR(tile1), DIR_REVERSE(dir));
	// util.freelog(Log.LOG_DEBUG,
	// "Co-Candidate: %s from (%d, %d) to (%d, %d), cost %d",
	// dir_get_name(dir), TILE_XY(ptile), TILE_XY(tile1),
	// total_cost);
	// }
	// }
	//
	// if (Map.same_pos(tile1, dest_tile) && maxcost > total_cost) {
	// util.freelog(Log.LOG_DEBUG, "Found path, cost = %d", total_cost);
	// /* Make sure we stop searching when we have no hope of finding a shorter
	// path */
	// maxcost = total_cost + 1;
	// }
	// } adjc_dir_iterate_end;
	// }
	//
	// util.freelog(Log.LOG_DEBUG, "GOTO: (%d, %d) . (%d, %d), cost = %d",
	// TILE_XY(orig_tile), TILE_XY(dest_tile), maxcost - 1);
	//
	// if (maxcost == MAXCOST)
	// return false; /* No route */
	//
	// /*** Succeeded. The vector at the destination indicates which way we get
	// there.
	// Now backtrack to remove all the blind paths ***/
	// assert(sizeof(*warmap.vector) == sizeof(char));
	// memset(warmap.vector, 0, Map.map.xsize * Map.map.ysize);
	//
	// init_queue();
	// add_to_maPqueue(0, dest_tile);
	//
	// while (true) {
	// if (!(ptile = get_from_maPqueue()))
	// break;
	//
	// adjc_dir_iterate(ptile, tile1, dir) {
	// if ((restriction == GOTO_MOVE_CARDINAL_ONLY)
	// && !is_cardinal_dir(dir)) continue;
	//
	// if (BV_ISSET(LOCAL_VECTOR(ptile), dir)) {
	// move_cost = (move_type == unit_move_type.SEA_MOVING)
	// ? WARMAP_SEACOST(tile1)
	// : WARMAP_COST(tile1);
	//
	// add_to_maPqueue(MAXCOST-1 - move_cost, tile1);
	// /* Mark it on the warmap */
	// WARMAP_VECTOR(tile1) |= 1 << DIR_REVERSE(dir);
	// BV_CLR(LOCAL_VECTOR(ptile), dir); /* avoid repetition */
	// util.freelog(Log.LOG_DEBUG, "PATH-SEGMENT: %s from (%d, %d) to (%d, %d)",
	// dir_get_name(DIR_REVERSE(dir)),
	// TILE_XY(tile1), TILE_XY(ptile));
	// }
	// } adjc_dir_iterate_end;
	// }
	//
	// return true;
	// }
	//
	// /****************************************************************************
	// Can the player see that the given ocean tile is along the coastline?
	// ****************************************************************************/
	// static boolean is_coast_seen(tile ptile, player pplayer)
	// {
	// boolean ai_always_see_map = !ai_handicap(pplayer, H_MAP);
	//
	// for(tile adjc_tile: util.adjc_tile_iterate(ptile)) {
	// /* Is there land here, and if so can we see it? */
	// if (!Terrain_H.is_ocean(adjc_tile.terrain)
	// && (ai_always_see_map || Maphand.map_is_known(adjc_tile, pplayer))) {
	// return true;
	// }
	// }
	//
	// return false;
	// }
	//
	// /**************************************************************************
	// This is used to choose among the valid directions marked on the warmap
	// by the find_the_shortest_path() function. Returns a direction or -1 if
	// none could be found.
	//
	// Notes on the implementation:
	//
	// 1. fitness[8] contains the fitness of the directions. Bigger number means
	// safer direction. It takes into account: how well the unit will be
	// defended at the next tile, how many possible attackers there are
	// around it, whether it is likely to sink there (for triremes).
	//
	// 2. This function does not check for loops, which we currently rely on
	// find_the_shortest_path() to make sure there are none of. If the
	// warmap did contain a loop repeated calls of this function may result
	// in the unit going in cycles forever.
	//
	// 3. It doesn't check for ZOC as it has been done in
	// find_the_shortest_path (which is called every turn).
	//
	// **************************************************************************/
	// static int find_a_direction(unit punit,
	// goto_move_restriction restriction,
	// tile dest_tile)
	// {
	// #define UNIT_DEFENSE(punit, ptile, defence_multiplier) \
	// ((get_virtual_defense_power(unittype.U_LAST, (punit).type, (ptile), false, 0) * \
	// (defence_multiplier)) / 2)
	//
	// #define UNIT_RATING(punit, ptile, defence_multiplier) \
	// (UNIT_DEFENSE(punit, ptile, defence_multiplier) * (punit).hp)
	//
	// /*
	// * 6% of the fitness value is substracted for directions where the
	// * destination is unknown and we don't have enough move points.
	// */
	// public static final int UNKNOWN_FITNESS_PENALTY_PERCENT = 6;
	//
	// /*
	// * Use this if you want to indicate that this direction shouldn't be
	// * selected.
	// */
	// #define DONT_SELECT_ME_FITNESS (-1)
	//
	// int i, fitness[8], best_fitness = DONT_SELECT_ME_FITNESS;
	// unit passenger;
	// player pplayer = punit.unit_owner();
	// boolean afraid_of_sinking = (unit_flag(punit, F_TRIREME)
	// && get_player_bonus(pplayer,
	// EFT_NO_SINK_DEEP) == 0);
	//
	// /*
	// * If the destination is one step away, look around first or just go
	// * there?
	// */
	// boolean do_full_check = afraid_of_sinking;
	//
	// if (Terrain_H.is_ocean(punit.tile.terrain)) {
	// passenger = other_passengers(punit);
	// } else {
	// passenger = null;
	// }
	//
	// /*
	// * If we can get to the destination right away there is nothing to
	// * be gained from going round in little circles to move across
	// * desirable squares.
	// *
	// * Actually there are things to gain, in AI case, like the safety
	// * checks -- GB
	// */
	// if (!do_full_check) {
	// enum direction8 dir;
	//
	// if (base_get_direction_for_step(punit.tile, dest_tile, &dir)
	// && TEST_BIT(WARMAP_VECTOR(punit.tile), dir)
	// && !(restriction == GOTO_MOVE_CARDINAL_ONLY
	// && !is_cardinal_dir(dir))) {
	// return dir;
	// }
	// }
	//
	// for (i = 0; i < 8; i++) {
	// fitness[i] = DONT_SELECT_ME_FITNESS;
	// }
	//
	// /*
	// * Loop over all directions, fill the fitness array and update
	// * best_fitness.
	// */
	// adjc_dir_iterate(punit.tile, ptile, dir) {
	// int defence_multiplier, num_of_allied_units, best_friendly_defence,
	// base_move_cost;
	// city pcity = Map.map_get_city(ptile);
	// unit best_ally;
	//
	// /*
	// * Is it an allowed direction? is it marked on the warmap?
	// */
	// if (!TEST_BIT(WARMAP_VECTOR(punit.tile), dir)
	// || ((restriction == GOTO_MOVE_CARDINAL_ONLY)
	// && !is_cardinal_dir(dir))) {
	// /* make sure we don't select it later */
	// fitness[dir] = DONT_SELECT_ME_FITNESS;
	// continue;
	// }
	//
	// /*
	// * Determine the cost of the proposed move.
	// */
	// if (is_ground_unit(punit)) {
	// /* assuming move is valid, but what if unit is trying to board?
	// -- GB */
	// base_move_cost = punit.tile.move_cost[dir];
	// } else {
	// base_move_cost = Unit_H.SINGLE_MOVE;
	// }
	//
	// if (unit_flag(punit, F_IGTER) && base_move_cost >= MOVE_COST_ROAD) {
	// base_move_cost = MOVE_COST_ROAD;
	// }
	//
	// util.freelog(Log.LOG_DEBUG, "%d@(%d,%d) evaluating (%d,%d)[%d/%d]", punit.id,
	// TILE_XY(punit.tile), TILE_XY(ptile),
	// base_move_cost, punit.moves_left);
	//
	// /*
	// * Calculate the defence multiplier of this tile. Both the unit
	// * itself and its ally benefit from it.
	// */
	// defence_multiplier = 2;
	// if (pcity) {
	// /* This isn't very accurate. */
	// defence_multiplier += (Effects.get_city_bonus(pcity, effect_type.EFT_LAND_DEFEND)
	// + Effects.get_city_bonus(pcity, EFT_MISSILE_DEFEND)
	// + Effects.get_city_bonus(pcity, EFT_AIR_DEFEND)
	// + Effects.get_city_bonus(pcity, EFT_SEA_DEFEND)) / 100;
	// }
	//
	// /*
	// * Find the best ally unit at the target tile.
	// */
	// best_ally = null;
	// num_of_allied_units = 0;
	// {
	// /*
	// * Initialization only for the compiler since it is guarded by
	// * best_ally.
	// */
	// int rating_of_best_ally = 0;
	//
	// for (unit aunit : ptile.units.data) {
	// if (Player_P.pplayers_allied(aunit.unit_owner(), punit.unit_owner())) {
	// int rating_of_current_ally =
	// UNIT_RATING(aunit, ptile, defence_multiplier);
	// num_of_allied_units++;
	//
	// if (!best_ally || rating_of_current_ally > rating_of_best_ally) {
	// best_ally = aunit;
	// rating_of_best_ally = rating_of_current_ally;
	// }
	// }
	// } }
	// }
	//
	// if (best_ally) {
	// best_friendly_defence =
	// MAX(UNIT_DEFENSE(punit, ptile, defence_multiplier),
	// UNIT_DEFENSE(best_ally, ptile, defence_multiplier));
	// } else {
	// best_friendly_defence =
	// UNIT_DEFENSE(punit, ptile, defence_multiplier);
	// }
	//
	// /*
	// * Fill fitness[dir] based on the rating of punit and best_ally.
	// */
	// {
	// /* calculate some clever weights basing on defence stats */
	// int rating_of_ally, rating_of_unit =
	// UNIT_RATING(punit, ptile, defence_multiplier);
	//      
	// assert((best_ally != null) == (num_of_allied_units > 0));
	//
	// if (best_ally) {
	// rating_of_ally = UNIT_RATING(best_ally, ptile, defence_multiplier);
	// } else {
	// rating_of_ally = 0; /* equivalent of having 0 HPs */
	// }
	//
	// if (num_of_allied_units == 0) {
	// fitness[dir] = rating_of_unit;
	// } else if (pcity || Map.tile_has_special(ptile, Terrain_H.S_FORTRESS)) {
	// fitness[dir] = MAX(rating_of_unit, rating_of_ally);
	// } else if (rating_of_unit <= rating_of_ally) {
	// fitness[dir] = rating_of_ally * (num_of_allied_units /
	// (num_of_allied_units + 1));
	// } else {
	// fitness[dir] = Math.min(rating_of_unit * (num_of_allied_units + 1),
	// rating_of_unit * rating_of_unit *
	// (num_of_allied_units /
	// MAX((num_of_allied_units + 1),
	// (rating_of_ally *
	// (num_of_allied_units + 1)))));
	// }
	// }
	//    
	// /*
	// * In case we change directions next turn, roads and railroads are
	// * nice.
	// */
	// if (Map.tile_has_special(ptile, Terrain_H.S_ROAD) || Map.tile_has_special(ptile,
	// Terrain_H.S_RAILROAD)) {
	// fitness[dir] += 10;
	// }
	//
	// /*
	// * What is around the tile we are about to step to?
	// */
	// for(tile adjtile: util.adjc_tile_iterate(ptile)) {
	// if (!Maphand.map_is_known(adjtile, pplayer)) {
	// if (punit.moves_left < base_move_cost) {
	// /* Avoid the unknown */
	// fitness[dir] -=
	// (fitness[dir] * UNKNOWN_FITNESS_PENALTY_PERCENT) / 100;
	// } else {
	// /* nice but not important */
	// fitness[dir]++;
	// }
	// } else {
	// /* lookin for trouble */
	// if (punit.moves_left < base_move_cost + Unit_H.SINGLE_MOVE) {
	// /* can't fight */
	// for (unit aunit : adjtile.units.data) {
	// int attack_of_enemy;
	//
	// if (!Player_P.pplayers_at_war(aunit.unit_owner(), punit.unit_owner())) {
	// continue;
	// }
	//
	// attack_of_enemy = get_attack_power(aunit);
	// if (attack_of_enemy == 0) {
	// continue;
	// }
	//	    
	// if (passenger && !is_ground_unit(aunit)) {
	// /* really don't want that direction */
	// fitness[dir] = DONT_SELECT_ME_FITNESS;
	// } else {
	// fitness[dir] -=
	// best_friendly_defence * (aunit.hp * attack_of_enemy *
	// attack_of_enemy /
	// (attack_of_enemy *
	// attack_of_enemy +
	// best_friendly_defence *
	// best_friendly_defence));
	// }
	// } }
	// }
	// } /* end this-tile-is-seen else */
	// }
	//    
	// /*
	// * Try to make triremes safe
	// */
	// if (afraid_of_sinking && !is_coast_seen(ptile, pplayer)) {
	// if (punit.moves_left < 2 * Unit_H.SINGLE_MOVE) {
	// fitness[dir] = DONT_SELECT_ME_FITNESS;
	// continue;
	// }
	//
	// /* We have enough moves and will be able to get back. */
	// fitness[dir] = 1;
	// }
	//
	// /*
	// * Clip the fitness value.
	// */
	// if (fitness[dir] < 1 && (!punit.unit_owner().ai.control
	// || punit.ai.passenger == 0
	// || punit.moves_left >= 2 * Unit_H.SINGLE_MOVE)) {
	// fitness[dir] = 1;
	// }
	// if (fitness[dir] < 0) {
	// fitness[dir] = DONT_SELECT_ME_FITNESS;
	// }
	//
	// /*
	// * Better than the best known?
	// */
	// if (fitness[dir] != DONT_SELECT_ME_FITNESS
	// && fitness[dir] > best_fitness) {
	// best_fitness = fitness[dir];
	// util.freelog(Log.LOG_DEBUG, "New best = %d: dir=%d (%d, %d) . (%d, %d)",
	// best_fitness, dir, TILE_XY(punit.tile), TILE_XY(ptile));
	// }
	// } adjc_dir_iterate_end;
	//
	// if (best_fitness == DONT_SELECT_ME_FITNESS && afraid_of_sinking
	// && !is_safe_ocean(punit.tile)) {
	// /*
	// * We've got a trireme in the middle of the sea. With
	// * best_fitness==DONT_SELECT_ME_FITNESS, it'll end its turn right
	// * there and could very well util.die. We'll try to rescue.
	// */
	// util.freelog(Log.LOG_DEBUG,
	// "%s's trireme in trouble at (%d,%d), looking for coast",
	// pplayer.name, TILE_XY(punit.tile));
	//
	// adjc_dir_iterate(punit.tile, ptile, dir) {
	// if (is_coast_seen(ptile, pplayer)) {
	// fitness[dir] = 1;
	// best_fitness = 1;
	// util.freelog(Log.LOG_DEBUG, "found coast");
	// }
	// } adjc_dir_iterate_end;
	// }
	//
	// if (best_fitness == DONT_SELECT_ME_FITNESS) {
	// util.freelog(Log.LOG_DEBUG, "find_a_direction: no good direction found");
	// return -1;
	// }
	//
	// /*
	// * Find random direction out of the best ones selected.
	// */
	// for (;;) {
	// int dir = Rand.myrand(8);
	//
	// if (fitness[dir] == best_fitness) {
	// util.freelog(Log.LOG_DEBUG,
	// "find_a_direction: returning dir=%d with fitness=%d", dir,
	// fitness[dir]);
	// return dir;
	// }
	// }
	//
	// #undef UNIT_DEFENSE
	// #undef UNIT_RATING
	// #undef UNKNOWN_FITNESS_PENALTY_PERCENT
	// #undef DONT_SELECT_ME_FITNESS
	// }
	//
	// /**************************************************************************
	// Basic checks as to whether a GOTO is possible. The target (x,y) should
	// be on the same continent as punit is, up to embarkation/disembarkation.
	// **************************************************************************/
	// boolean goto_is_sane(unit punit, tile ptile, boolean omni)
	// {
	// player pplayer = punit.unit_owner();
	//
	// if (Map.same_pos(punit.tile, ptile)) {
	// return true;
	// }
	//
	// if (!(omni || Maphand.map_is_known_and_seen(ptile, pplayer))) {
	// /* The destination is in unknown -- assume sane */
	// return true;
	// }
	//
	// switch (punit.unit_type().move_type) {
	//
	// case unit_move_type.LAND_MOVING:
	// if (Terrain_H.is_ocean(ptile.terrain)) {
	// /* Going to a sea tile, the target should be next to our continent
	// * and with a boat */
	// if (ground_unit_transporter_capacity(ptile, pplayer) > 0) {
	// for(tile tmp_tile: util.adjc_tile_iterate(ptile)) {
	// if (map_get_continent(tmp_tile) == map_get_continent(punit.tile))
	// /* The target is adjacent to our continent! */
	// return true;
	// }
	// }
	// } else {
	// /* Going to a land tile: better be our continent */
	// if (map_get_continent(punit.tile) == map_get_continent(ptile)) {
	// return true;
	// } else {
	// /* Well, it's not our continent, but maybe we are on a boat
	// * adjacent to the target continent? */
	// for(tile tmp_tile: util.adjc_tile_iterate(punit.tile)) {
	// if (map_get_continent(tmp_tile) == map_get_continent(ptile)) {
	// return true;
	// }
	// }
	// }
	// }
	//      
	// return false;
	//
	// case unit_move_type.SEA_MOVING:
	// if (Terrain_H.is_ocean(ptile.terrain)
	// || Terrain.is_terrain_flag_near_tile(ptile)) {
	// /* The target is sea or is accessible from sea
	// * (allow for bombardment and visiting ports) */
	// return true;
	// }
	// return false;
	//
	// default:
	// return true;
	// }
	// }

	/***************************************************************************
	 * Handles a unit goto (Duh?) Uses find_the_shortest_path() to find all the
	 * shortest paths to the goal. Uses find_a_direction() to choose between
	 * these.
	 * 
	 * If we have an air unit we use find_air_first_destination(punit,
	 * &waypoint_x, &waypoint_y) to get a waypoint to goto. The actual goto is
	 * still done with find_the_shortest_path(pplayer, punit, waypoint_x,
	 * waypoint_y, restriction)
	 **************************************************************************/
	static goto_result do_unit_goto(unit punit, goto_move_restriction restriction,
			boolean trigger_special_ability) {
		// player pplayer = punit.unit_owner();
		// int unit_id;
		goto_result status = goto_result.GR_WAITING;
		// tile ptile, *dest_tile, *waypoint_tile;

		// assert(!unit_has_orders(punit));

		// unit_id = punit.id;
		// dest_tile = waypoint_tile = punit.goto_tile;

		// if (Map.same_pos(punit.tile, dest_tile) ||
		// !goto_is_sane(punit, dest_tile, false)) {
		// punit.activity = unit_activity.ACTIVITY_IDLE;
		// Unittools.send_unit_info(null, punit);
		// if (Map.same_pos(punit.tile, dest_tile)) {
		// return GR_ARRIVED;
		// } else {
		// return GR_FAILED;
		// }
		// }

		// if(punit.moves_left == 0) {
		// Unittools.send_unit_info(null, punit);
		// return GR_OUT_OF_MOVEPOINTS;
		// }

		// if (is_air_unit(punit)) {
		// if (find_air_first_destination(punit, &waypoint_tile)) {
		// /* this is a special case for air units who do not always want to
		// move. */
		// if (Map.same_pos(waypoint_tile, punit.tile)) {
		// punit.done_moving = true;
		// Unittools.send_unit_info(null, punit);
		// return GR_WAITING; /* out of fuel */
		// }
		// } else {
		// util.freelog(Log.LOG_VERBOSE, "Did not find an airroute for "
		// "%s's %s at (%d, %d) . (%d, %d)",
		// pplayer.name, punit.unit_type().name,
		// TILE_XY(punit.tile), TILE_XY(dest_tile));
		// punit.activity = unit_activity.ACTIVITY_IDLE;
		// Unittools.send_unit_info(null, punit);
		// return GR_FAILED;
		// }
		// }

		// /* This has the side effect of marking the warmap with the possible
		// paths */
		// if (find_the_shortest_path(punit, waypoint_tile, restriction)) {
		// do { /* move the unit along the path chosen by
		// find_the_shortest_path() while
		// we can */
		// boolean last_tile, success;
		// unit penemy = null;
		// int dir;

		// if (punit.moves_left == 0) {
		// return GR_OUT_OF_MOVEPOINTS;
		// }

		// dir = find_a_direction(punit, restriction, waypoint_tile);
		// if (dir < 0) {
		// util.freelog(Log.LOG_DEBUG, "%s#%d@(%d,%d) stalling so it won't be killed.",
		// punit.unit_type().name, punit.id,
		// TILE_XY(punit.tile));
		// return GR_FAILED;
		// }

		// util.freelog(Log.LOG_DEBUG, "Going %s", dir_get_name(dir));
		// ptile = mapstep(punit.tile, dir);

		// penemy = Unit.is_enemy_unit_tile(ptile, punit.unit_owner());
		// assert(punit.moves_left > 0);

		// last_tile = Map.same_pos(ptile, punit.goto_tile);

		// /* Call Unithand.handle_unit_move_request for humans and ai_unit_move for AI
		// */
		// success = (!pplayer.ai.control
		// && Unithand.handle_unit_move_request(punit, ptile, false,
		// !(last_tile && trigger_special_ability)))
		// || (pplayer.ai.control && ai_unit_move(punit, ptile));

		// if (!Player_P.player_find_unit_by_id(pplayer, unit_id)) {
		// return GR_DIED; /* unit util.died during goto! */
		// }

		// if (!success && punit.moves_left > 0) {
		// /* failure other than ran out of movement during an
		// attempt to utilize the rand() move feature */
		// punit.activity=unit_activity.ACTIVITY_IDLE;
		// Unittools.send_unit_info(null, punit);
		// return GR_FAILED;
		// }

		// /* Don't attack more than once per goto */
		// if (penemy && !pplayer.ai.control) {
		// punit.activity = unit_activity.ACTIVITY_IDLE;
		// Unittools.send_unit_info(null, punit);
		// return GR_FOUGHT;
		// }

		// if(!Map.same_pos(ptile, punit.tile)) {
		// Unittools.send_unit_info(null, punit);
		// return GR_OUT_OF_MOVEPOINTS;
		// }

		// util.freelog(Log.LOG_DEBUG, "Moving on.");
		// } while(!Map.same_pos(ptile, waypoint_tile));
		// } else {
		// util.freelog(Log.LOG_VERBOSE, "Did not find the shortest path for "
		// "%s's %s at (%d, %d) . (%d, %d)",
		// pplayer.name, punit.unit_type().name,
		// TILE_XY(punit.tile), TILE_XY(dest_tile));
		// Unithand.handle_unit_activity_request(punit, unit_activity.ACTIVITY_IDLE);
		// Unittools.send_unit_info(null, punit);
		// return GR_FAILED;
		// }
		// /** Finished moving the unit for this turn **/

		// /* normally we would just do this unconditionally, but if we had an
		// airplane goto we might not be finished even if the loop exited */
		// if (Map.same_pos(punit.tile, dest_tile)) {
		// punit.activity = unit_activity.ACTIVITY_IDLE;
		// status = GR_ARRIVED;
		// } else {
		// /* we have a plane refueling at a waypoint */
		// status = GR_OUT_OF_MOVEPOINTS;
		// }

		// Unittools.send_unit_info(null, punit);
		return status;
	}

	 /**************************************************************************
	 Calculate and return cost (in terms of move points) for unit to move
	 to specified destination.
	 Currently only used in autoattack.c
	 **************************************************************************/
	 public static int calculate_move_cost(unit punit, tile dest_tile)
	 {
		 //TODO:
		/* perhaps we should do some caching -- fisch */

//		if (is_air_unit(punit) || is_heli_unit(punit)) {
//			/*
//			 * The warmap only really knows about land and sea units, so for
//			 * these we just assume cost = distance. (times 3 for road factor).
//			 * (Could be wrong if there are things in the way.)
//			 */
//			return Unit_H.SINGLE_MOVE * Map.real_map_distance(punit.tile, dest_tile);
//		}
//
//		generate_warmap(null, punit);
//
//		if (Unit.is_sailing_unit(punit))
//			return WARMAP_SEACOST(dest_tile);
//		else
//			/* ground unit */
//			return WARMAP_COST(dest_tile);
		 return 0;
	}
	
	
	// /**************************************************************************
	// Returns true if the airspace at given map position _looks_ safe to
	// the given player. The airspace is unsafe if the player believes
	// there is an enemy unit on it. This is tricky, since we have to
	// consider what the player knows/doesn't know about the tile.
	// **************************************************************************/
	// static boolean airspace_looks_safe(tile ptile, player pplayer)
	// {
	// /*
	// * We do handicap checks for the player with ai_handicap(). This
	// * function returns true if the player is handicapped. For human
	// * players they'll always return true. This is the desired behavior.
	// */
	//
	// /* If the tile's unknown, we (may) assume it's safe. */
	// if (ai_handicap(pplayer, H_MAP) && !Maphand.map_is_known(ptile, pplayer)) {
	// return AIR_ASSUMES_UNKNOWN_SAFE;
	// }
	//
	// /* This is bad: there could be a city there that the player doesn't
	// know about. How can we check that? */
	// if (is_non_allied_city_tile(ptile, pplayer)) {
	// return false;
	// }
	//
	// /* If the tile's fogged we again (may) assume it's safe. */
	// if (ai_handicap(pplayer, H_FOG) &&
	// !Maphand.map_is_known_and_seen(ptile, pplayer)) {
	// return AIR_ASSUMES_FOGGED_SAFE;
	// }
	//
	// /* The tile is unfogged so we can check for enemy units on the
	// tile. */
	// return !Unit.is_non_allied_unit_tile(ptile, pplayer);
	// }
	
	 /**************************************************************************
	 An air unit starts (src_x,src_y) with moves moves and want to go to
	 (dest_x,dest_y). It returns the number of moves left if this is
	 possible without running out of moves. It returns -1 if it is
	 impossible.
	
	 The function has 3 stages:
	 Try to rule out the possibility in O(1) time else
	 Try to quickly verify in O(moves) time else
	 Do an A* search using the warmap to completely search for the path.
	 **************************************************************************/
	 public static int air_can_move_between(int moves, tile src_tile,
	 tile dest_tile, player pplayer)
	 {
		 tile ptile;
		 // int dist, total_distance = Map.real_map_distance(src_tile, dest_tile);
		 //
		 // util.freelog(Log.LOG_DEBUG,
		 // "air_can_move_between(moves=%d, src=(%i,%i), "
		 // "dest=(%i,%i), player=%s)", moves, TILE_XY(src_tile),
		 // TILE_XY(dest_tile), pplayer.name);
		 //
		 // /* First we do some very simple O(1) checks. */
		 // if (total_distance > moves) {
		 // return -1;
		 // }
		 // if (total_distance == 0) {
		 // return moves;
		 // }
		 //
		 // /*
		 // * Then we do a fast O(n) straight-line check. It'll work so long
		 // * as the straight-line doesn't cross any unreal tiles, unknown
		 // * tiles, or enemy-controlled tiles. So, it should work most of the
		 // * time.
		 // */
		 // ptile = src_tile;
		 //  
		 // /*
		 // * We don't check the endpoint of the goto, since it is possible
		 // * that the endpoint is a tile which has an enemy which should be
		 // * attacked. But we do check that all points in between are safe.
		 // */
		 // for (dist = total_distance; dist > 1; dist--) {
		 // /* Warning: straightest_direction may not actually follow the
		 // straight line. */
		 // int dir = straightest_direction(ptile, dest_tile);
		 // tile new_tile;
		 //
		 // if (!(new_tile = mapstep(ptile, dir))
		 // || !airspace_looks_safe(new_tile, pplayer)) {
		 // break;
		 // }
		 // ptile = new_tile;
		 // }
		 // if (dist == 1) {
		 // /* Looks like the O(n) quicksearch worked. */
		 // assert(Map.real_map_distance(ptile, dest_tile) == 1);
		 // return moves - total_distance * MOVE_COST_AIR;
		 // }
		 //
		 // /*
		 // * Finally, we do a full A* search if this isn't one of the specical
		 // * cases from above. This is copied from find_the_shortest_path but
		 // * we use Map.real_map_distance as a minimum distance estimator for the
		 // * A* search. This distance estimator is used for the cost value in
		 // * the queue, but is not stored in the warmap itself.
		 //   *
		 //   * Note, A* is possible here but not in a normal FreeCiv path
		 //   * finding because planes always take 1 movement unit to move -
		 //   * which is not true of land units.
		 //   */
		 //  util.freelog(Log.LOG_DEBUG,
		 //	  "air_can_move_between: quick search didn't work. Lets try full.");
		 //
		 //  init_warmap(src_tile, unit_move_type.AIR_MOVING);
		 //
		 //  /* The 0 is inaccurate under A*, but it doesn't matter. */
		 //  add_to_maPqueue(0, src_tile);
		 //
		 //  while ((ptile = get_from_maPqueue())) {
		 //    adjc_dir_iterate(ptile, tile1, dir) {
		 //      if (WARMAP_COST(tile1) != MAXCOST) {
		 //	continue;
		 //      }
		 //
		 //      /*
		 //       * This comes before the airspace_looks_safe check because it's
		 //       * okay to goto into an enemy. 
		 //       */
		 //      if (Map.same_pos(tile1, dest_tile)) {
		 //	/* We're there! */
		 //	util.freelog(Log.LOG_DEBUG, "air_can_move_between: movecost: %i",
		 //		WARMAP_COST(ptile) + MOVE_COST_AIR);
		 //	/* The -MOVE_COST_AIR is because we haven't taken the final
		 //	   step yet. */
		 //	return moves - WARMAP_COST(ptile) - MOVE_COST_AIR;
		 //      }
		 //
		 //      /* We refuse to goto through unsafe airspace. */
		 //      if (airspace_looks_safe(tile1, pplayer)) {
		 //	int cost = WARMAP_COST(ptile) + MOVE_COST_AIR;
		 //
		 //	WARMAP_COST(tile1) = cost;
		 //
		 //	/* Now for A* we find the minimum total cost. */
		 //	cost += Map.real_map_distance(tile1, dest_tile);
		 //	if (cost <= moves) {
		 //	  add_to_maPqueue(cost, tile1);
		 //	}
		 //      }
		 //    } adjc_dir_iterate_end;
		 //  }

		 util.freelog(Log.LOG_DEBUG, "air_can_move_between: no route found");
		 return -1;
	 }
}