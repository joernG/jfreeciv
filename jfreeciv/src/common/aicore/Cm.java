package common.aicore;

import common.city.City_H;

public class Cm{

// Freeciv - Copyright (C) 2002 - The Freeciv Project
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
//#include <stdio.h> /* for sprintf */
//#include <stdlib.h>
//#include <string.h>
//
//#include "city.h"
//#include "fcintl.h"
//#include "Game.game.h"
//#include "government.h"
//#include "hash.h"
//#include "log.h"
//#include "Map.map.h"
//#include "mem.h"
//#include "shared.h"
//#include "support.h"
//#include "timing.h"
//
//#include "cm.h"
//
///*
// * Terms used
// * ==========
// *
// * Stats: food, shields, trade, luxury, science, and gold.
// * Production: amount of stats you get directly from farming tiles or
// *      having specialists.
// * Surplus: amount of stats you get, taking buildings, taxes, disorder, and
// *      any other effects into account.
// *
// * Happy State: disorder (unhappy), content (!unhappy && !happy) and
// * happy (happy)
// *
// * Tile type: usually, many tiles produce the same f/s/t.  So we bin the
// *      tiles into tile types.  Specialists are also a 'tile type.'
// *
// * Unlike the original CM code, which used a dynamic programming approach,
// * this code uses a branch-and-bound approach.  The DP approach allowed
// * cacheing, but it was hard to guarantee the correctness of the cache, so
// * it was usually tossed and recomputed.
// *
// * The B&B approach also allows a very simple greedy search, whereas the DP
// * approach required a lot of pre-computing.  And, it appears to be very
// * slightly faster.  It evaluates about half as many solutions, but each
// * candidate solution is more expensive due to the lack of cacheing.
// *
// * We use highly specific knowledge about how the city computes its stats
// * in two places:
// * - setting the min_production array.  Ideally the city should tell us.
// * - computing the weighting for tiles.  Ditto.
// */
//
//
///****************************************************************************
// Probably these declarations should be in cm.h in the long term.
//*****************************************************************************/
//struct cm_state;
//
//static cm_state cm_init_state(city pcity);
//static void cm_free_state(cm_state state);
//static void cm_find_best_solution(cm_state state,
//		     final cm_parameter final parameter,
//		     cm_result result);
//
///****************************************************************************
// defines, structs, globals, forward declarations
//*****************************************************************************/
//
//#ifdef DEBUG
//#define GATHER_TIME_STATS
//#define CM_DEBUG
//#endif
//
///* whether to print every query, or just at cm_free ; matters only
//   if GATHER_TIME_STATS is on */
//#define PRINT_TIME_STATS_EVERY_QUERY
//
//#define LOG_TIME_STATS                                  Log.LOG_DEBUG
//#define LOG_CM_STATE                                    Log.LOG_DEBUG
//#define LOG_LATTICE                                     Log.LOG_DEBUG
//#define LOG_REACHED_LEAF                                Log.LOG_DEBUG
//#define LOG_BETTER_LEAF                                 Log.LOG_DEBUG
//#define LOG_PRUNE_BRANCH                                Log.LOG_DEBUG
//
//#ifdef GATHER_TIME_STATS
//static struct {
//  struct one_perf {
//    timer wall_timer;
//    int query_count;
//    int apply_count;
//    final String name;
//  } greedy, opt;
//
//  one_perf current;
//} performance;
//
//static void print_performance(one_perf counts);
//#endif
//
///* Fitness of a solution.  */
//struct cm_fitness {
//  int weighted; /* weighted sum */
//  boolean sufficient; /* false => doesn't meet finalraints */
//};
//
//
///*
// * We have a cyclic structure here, so we need to forward-declare the
// * structs
// */
//struct cm_tile_type;
//struct cm_tile;
//
///*
// * A tile.  Has a pointer to the type, and the x/y coords.
// * Used mostly just for converting to cm_result.
// */
//struct cm_tile {
//  final cm_tile_type type;
//  int x, y; /* valid only if !is_specialist */
//};
//
///* define the tile_vector as array<cm_tile> */
//#define SPECVEC_TAG tile
//#define SPECVEC_TYPE struct cm_tile
//#include "specvec.h"
//
///* define the tile_type_vector as array <cm_tile_type*> */
//#define SPECVEC_TAG tile_type
//#define SPECVEC_TYPE cm_tile_type 
//#include "specvec.h"
//#define tile_type_vector_iterate(vector, var) { \
//    cm_tile_type var; \
//    TYPED_VECTOR_ITERATE(struct cm_tile_type*, vector, var##p) { \
//      var = *var##p; \
//      {
//#define tile_type_vector_iterate_end }} VECTOR_ITERATE_END; }
//
//static inline void tile_type_vector_add(tile_type_vector tthis,
//    cm_tile_type toadd) {
//  tile_type_vector_append(tthis, &toadd);
//}
//
//
///*
// * A tile type.
// * Holds the production (a hill produces 1/0/0);
// * Holds a list of which tiles match this (all the hills and tundra);
// * Holds a list of other types that are strictly better
// * (grassland 2/0/0, plains 1/1/0, but not gold mine 0/1/6).
// * Holds a list of other types that are strictly worse
// * (the grassland and plains hold a pointer to the hill).
// *
// * Specialists are special types; is_specialist is set, and the tile
// * vector is empty.  We can never run out of specialists.
//       */
//struct cm_tile_type {
//  int production[NUM_STATS];
//  double estimated_fitness; /* weighted sum of production */
//  boolean is_specialist;
//  enum specialist_type spec; /* valid only if is_specialist */
//  struct tile_vector tiles;  /* valid only if !is_specialist */
//  struct tile_type_vector better_types;
//  struct tile_type_vector worse_types;
//  int lattice_index; /* index in state.lattice */
//  int lattice_depth; /* depth = sum(#tiles) over all better types */
//};
//
//
///*
// * A partial solution.
// * Has the count of workers assigned to each lattice position, and
// * a count of idle workers yet unassigned.
// */
//struct partial_solution {
//  /* indices for these two match the lattice indices */
//  int *worker_counts;   /* number of workers on each type */
//  int *prereqs_filled;  /* number of better types filled up */
//
//  int production[NUM_STATS]; /* raw production, cached for the heuristic */
//  int idle;             /* number of idle workers */
//};
//
///*
// * State of the search.
// * This holds all the information needed to do the search, all in one
// * struct, in order to clean up the function calls.
// */
//struct cm_state {
//  /* input from the caller */
//  struct cm_parameter parameter;
//  /*mutable*/ city pcity;
//
//  /* the tile lattice */
//  struct tile_type_vector lattice;
//  struct tile_type_vector lattice_by_prod[NUM_STATS];
//
//  /* the best known solution, and its fitness */
//  struct partial_solution best;
//  struct cm_fitness best_value;
//
//  /* hard finalraints on production: any solution with less production than
//   * this fails to satisfy the finalraints, so we can stop investigating
//   * this branch.  A solution with more production than this may still
//   * fail (for being unhappy, for instance). */
//  int min_production[NUM_STATS];
//
//  /* the current solution we're examining. */
//  struct partial_solution current;
//
//  /*
//   * Where we are in the search.  When we add a worker to the current
//   * partial solution, we also push the tile type index on the stack.
// */
//  struct {
//    int *stack;
//    int size;
//  } choice;
//};
//
//
///* return #fields + specialist types */
//static int num_types(final cm_state state);
//
//
///* debugging functions */
//#ifdef CM_DEBUG
//static void print_tile_type(int loglevel, final cm_tile_type ptype,
//    final String prefix);
//static void print_lattice(int loglevel,
//    final tile_type_vector lattice);
//static void print_partial_solution(int loglevel,
//    final partial_solution soln,
//    final cm_state state);
//#else
//#define print_tile_type(loglevel, ptype, prefix)
//#define print_lattice(loglevel, lattice)
//#define print_partial_solution(loglevel, soln, state)
//#endif
//
//
///****************************************************************************
//  Initialize the CM data at the start of each Game.game.  Note the citymap
//  indices will not have been initialized yet (cm_init_citymap is called
//  when they are).
//****************************************************************************/
//void cm_init()
//{
//  /* In the B&B algorithm there's not really anything to initialize. */
//#ifdef GATHER_TIME_STATS
//  memset(&performance, 0, sizeof(performance));
//
//  performance.greedy.wall_timer = new_timer(TIMER_USER, TIMER_ACTIVE);
//  performance.greedy.name = "greedy";
//
//  performance.opt.wall_timer = new_timer(TIMER_USER, TIMER_ACTIVE);
//  performance.opt.name = "opt";
//#endif
//}
//
///****************************************************************************
//  Initialize the CM citymap data.  This function is called when the
//  city map indices are generated (basically when the topology is set,
//  shortly after the start of the Game.game).
//****************************************************************************/
//void cm_init_citymap()
//{
//  /* In the B&B algorithm there's not really anything to initialize. */
//}
//
///****************************************************************************
//  Clear the cache for a city.
//****************************************************************************/
//void cm_clear_cache(city pcity)
//{
//  /* The B&B algorithm doesn't have city caches so there's nothing to do. */
//}
//
///****************************************************************************
//  Called at the end of a Game.game to free any CM data.
//****************************************************************************/
//void cm_free()
//{
//#ifdef GATHER_TIME_STATS
//  print_performance(&performance.greedy);
//  print_performance(&performance.opt);
//
//  free_timer(performance.greedy.wall_timer);
//  free_timer(performance.opt.wall_timer);
//  memset(&performance, 0, sizeof(performance));
//#endif
//}
//
//
///***************************************************************************
//  Iterate over all valid city tiles (that is, don't iterate over tiles
//  off the edge of the world).
// ***************************************************************************/
//#define my_city_map_iterate(pcity, cx, cy) \
//  city_map_checked_iterate(pcity.tile, cx, cy, itr_tile)
//
//#define my_city_map_iterate_end city_map_checked_iterate_end;
//
//
///***************************************************************************
//  Functions of tile-types.
// ***************************************************************************/
//
///****************************************************************************
//  Set all production to zero and initialize the vectors for this tile type.
//****************************************************************************/
//static void tile_type_init(cm_tile_type type)
//{
//  memset(type, 0, sizeof(*type));
//  tile_vector_init(&type.tiles);
//  tile_type_vector_init(&type.better_types);
//  tile_type_vector_init(&type.worse_types);
//}
//
///****************************************************************************
//  Duplicate a tile type, except for the vectors - the vectors of the new tile
//  type will be empty.
//****************************************************************************/
//static cm_tile_type tile_type_dup(final cm_tile_type oldtype)
//{
//  cm_tile_type newtype = fc_malloc(sizeof(*newtype));
//
//  memcpy(newtype, oldtype, sizeof(*oldtype));
//  tile_vector_init(&newtype.tiles);
//  tile_type_vector_init(&newtype.better_types);
//  tile_type_vector_init(&newtype.worse_types);
//  return newtype;
//}
//
///****************************************************************************
//  Free all the storage in the tile type (but don't free the type itself).
//****************************************************************************/
//static void tile_type_destroy(cm_tile_type type)
//{
//  /* The call to vector_free() will magically free all the tiles in the
//   * vector. */
//  tile_vector_free(&type.tiles);
//  tile_type_vector_free(&type.better_types);
//  tile_type_vector_free(&type.worse_types);
//}
//
///****************************************************************************
//  Destroy and free all types in the vector, and the vector itself.  This
//  will free all memory associated with the vector.
//****************************************************************************/
//static void tile_type_vector_free_all(tile_type_vector vec)
//{
//  tile_type_vector_iterate(vec, type) {
//    /* Destroy all data in the type, and free the type itself. */
//    tile_type_destroy(type);
//    free(type);
//  } tile_type_vector_iterate_end;
//
//  tile_type_vector_free(vec);
//}
//
///****************************************************************************
//  Return true iff all categories of the two types are equal.  This means
//  all production outputs are equal and the is_specialist fields are also
//  equal.
//****************************************************************************/
//static boolean tile_type_equal(final cm_tile_type a,
//			    final cm_tile_type b)
//{
//  enum cm_stat stat;
//
//  for (stat = 0; stat < NUM_STATS; stat++) {
//    if (a.production[stat] != b.production[stat])  {
//      return false;
//    }
//  }
//
//  if (a.is_specialist != b.is_specialist) {
//    return false;
//  }
//
//  return true;
//}
//
///****************************************************************************
//  Return true if tile a is better or equal to tile b in all categories.
//
//  Specialists are considered better than workers (all else being equal)
//  since we have an unlimited number of them.
//****************************************************************************/
//static boolean tile_type_better(final cm_tile_type a,
//			     final cm_tile_type b)
//{
//  enum cm_stat stat;
//
//  for (stat = 0; stat < NUM_STATS; stat++) {
//    if (a.production[stat] < b.production[stat])  {
//      return false;
//    }
//  }
//
//  if (a.is_specialist && !b.is_specialist) {
//    /* If A is a specialist and B isn't, and all of A's production is at
//     * least as good as B's, then A is better because it doesn't tie up
//     * the map tile. */
//    return true;
//  } else if (!a.is_specialist && b.is_specialist) {
//    /* Vice versa. */
//    return false;
//  }
//
//  return true;
//}
//
///****************************************************************************
//  If there is a tile type in the vector that is equivalent to the given
//  type, return its index.  If not, return -1.
//
//  Equivalence is defined in tile_type_equal().
//****************************************************************************/
//static int tile_type_vector_find_equivalent(
//				final tile_type_vector vec,
//				final cm_tile_type ptype)
//{
//  int i;
//
//  for (i = 0; i < vec.size; i++) {
//    if (tile_type_equal(vec.p[i], ptype)) {
//      return i;
//    }
//  }
//
//  return -1;
//}
//
///****************************************************************************
//  Return the number of tiles of this type that can be worked.  For
//  is_specialist types this will always be infinite but for other types of
//  tiles it is limited by what's available in the citymap.
//****************************************************************************/
//static int tile_type_num_tiles(final cm_tile_type type)
//{
//  if(type.is_specialist) {
//    return FC_INFINITY;
//  } else {
//    return tile_vector_size(&type.tiles);
//  }
//}
//
///****************************************************************************
//  Return the number of tile types that are better than this type.
//
//  Note this isn't the same as the number of *tiles* that are better.  There
//  may be more than one tile of each type (see tile_type_num_tiles).
//****************************************************************************/
//static int tile_type_num_prereqs(final cm_tile_type ptype)
//{
//  return ptype.better_types.size;
//}
//
///****************************************************************************
//  Retrieve a tile type by index.  For a given state there are a certain
//  number of tile types, which may be iterated over using this function
//  as a lookup.
//****************************************************************************/
//static final cm_tile_type tile_type_get(final cm_state state,
//						int type)
//{
//  /* Sanity check the index. */
//  assert(type >= 0);
//  assert(type < state.lattice.size);
//  return state.lattice.p[type];
//}
//
///****************************************************************************
//  Retrieve a tile of a particular type by index.  For a given tile type
//  there are a certain number of tiles (1 or more), which may be iterated
//  over using this function for index.  Don't call this for is_specialist
//  types.  See also tile_type_num_tiles().
//****************************************************************************/
//static final cm_tile tile_get(final cm_tile_type ptype, int j)
//{
//  assert(j >= 0);
//  assert(j < ptype.tiles.size);
//  return &ptype.tiles.p[j];
//}
//
//
///**************************************************************************
//  Functions on the cm_fitness struct.
//**************************************************************************/
//
///****************************************************************************
//  Return true iff fitness A is strictly better than fitness B.
//****************************************************************************/
//static boolean fitness_better(struct cm_fitness a, struct cm_fitness b)
//{
//  if (a.sufficient != b.sufficient) {
//    return a.sufficient;
//  }
//  return a.weighted > b.weighted;
//}
//
///****************************************************************************
//  Return a fitness struct that is the worst possible result we can
//  represent.
//****************************************************************************/
//static struct cm_fitness worst_fitness()
//{
//  struct cm_fitness f;
//
//  f.sufficient = false;
//  f.weighted = -FC_INFINITY;
//  return f;
//}
//
///****************************************************************************
//  Compute the fitness of the given surplus (and disorder/happy status)
//  according to the weights and minimums given in the parameter.
//****************************************************************************/
//static struct cm_fitness compute_fitness(final int surplus[NUM_STATS],
//					 boolean disorder, boolean happy,
//					final cm_parameter parameter)
//{
//  enum cm_stat stat;
//  struct cm_fitness fitness;
//
//  fitness.sufficient = true;
//  fitness.weighted = 0;
//
//  for (stat = 0; stat < NUM_STATS; stat++) {
//    fitness.weighted += surplus[stat] * parameter.factor[stat];
//    if (surplus[stat] < parameter.minimal_surplus[stat]) {
//      fitness.sufficient = false;
//    }
//  }
//
//  if (happy) {
//    fitness.weighted += parameter.happy_factor;
//  } else if (parameter.require_happy) {
//    fitness.sufficient = false;
//  }
//
//  if (disorder && !parameter.allow_disorder) {
//    fitness.sufficient = false;
//  }
//
//  return fitness;
//}
//
///***************************************************************************
//  Handle struct partial_solution.
//  - perform a deep copy
//  - convert to city
//  - convert to cm_result
// ***************************************************************************/
//
///****************************************************************************
//  Allocate and initialize an empty solution.
//****************************************************************************/
//static void init_partial_solution(partial_solution into,
//                                  int ntypes, int idle)
//{
//  into.worker_counts = fc_calloc(ntypes, sizeof(*into.worker_counts));
//  into.prereqs_filled = fc_calloc(ntypes, sizeof(*into.prereqs_filled));
//  memset(into.production, 0, sizeof(into.production));
//  into.idle = idle;
//}
//
///****************************************************************************
//  Free all storage associated with the solution.  This is basically the
//  opposite of init_partial_solution.
//****************************************************************************/
//static void destroy_partial_solution(partial_solution into)
//{
//  free(into.worker_counts);
//  free(into.prereqs_filled);
//}
//
///****************************************************************************
//  Copy the source solution into the destination one (the destination
//  solution must already be allocated).
//****************************************************************************/
//static void copy_partial_solution(partial_solution dst,
//				  final partial_solution src,
//				  final cm_state state)
//{
//  memcpy(dst.worker_counts, src.worker_counts,
//	 sizeof(*dst.worker_counts) * num_types(state));
//  memcpy(dst.prereqs_filled, src.prereqs_filled,
//	 sizeof(*dst.prereqs_filled) * num_types(state));
//  memcpy(dst.production, src.production, sizeof(dst.production));
//  dst.idle = src.idle;
//}
//
//
///**************************************************************************
//  Evaluating a completed solution.
//**************************************************************************/
//
///****************************************************************************
//  Apply the solution to the city.
//
//  Note this function writes directly into the city's citymap, unlike most
//  other code which uses accessor functions.
//****************************************************************************/
//static void apply_solution(cm_state state,
//                           final partial_solution soln)
//{
//  city pcity = state.pcity;
//  int i;
//  int sumworkers = 0;
//
//#ifdef GATHER_TIME_STATS
//  performance.current.apply_count++;
//#endif
//
//  assert(soln.idle == 0);
//
//  /* Clear all specialists, and remove all workers from fields (except
//   * the city center). */
//  memset(&pcity.specialists, 0, sizeof(pcity.specialists));
//	for (int _itr = 0; _itr < City_H.CITY_MAP_SIZE * City_H.CITY_MAP_SIZE; _itr++) {	   
//		int x = _itr % City_H.CITY_MAP_SIZE, y = _itr / City_H.CITY_MAP_SIZE;	   
//
//		if (City.is_valid_city_coords(x, y)) {
//    if (City.is_city_center(x, y)) {
//      continue;
//    }
//    if (pcity.city_map[x][y] == city_tile_type.C_TILE_WORKER) {
//      pcity.city_map[x][y] = city_tile_type.C_TILE_EMPTY;
//    }
//  } };
//
//  /* Now for each tile type, find the right number of such tiles and set them
//   * as worked.  For specialists we just increase the number of specialists
//   * of that type. */
//  for (i = 0 ; i < num_types(state); i++) {
//    int nworkers = soln.worker_counts[i];
//    final cm_tile_type type;
//
//    if (nworkers == 0) {
//      /* No citizens of this type. */
//      continue;
//    }
//    sumworkers += nworkers;
//
//    type = tile_type_get(state, i);
//
//    if (type.is_specialist) {
//      /* Just increase the number of specialists. */
//      pcity.specialists[type.spec] += nworkers;
//    } else {
//      int j;
//
//      /* Place citizen workers onto the citymap tiles. */
//      for (j = 0; j < nworkers; j++) {
//        final cm_tile tile = tile_get(type, j);
//
//        pcity.city_map[tile.x][tile.y] = city_tile_type.C_TILE_WORKER;
//      }
//    }
//  }
//
//  /* Finally we must refresh the city to reset all the precomputed fields. */
//  generic_city_refresh(pcity, false, 0);
//  assert(sumworkers == pcity.size);
//}
//
///****************************************************************************
//  Convert the city's surplus numbers into an array.  Get the happy/disorder
//  values, too.  This fills in the surplus array and disorder and happy 
//  values based on the city's data.
//****************************************************************************/
//static void get_city_surplus(final city pcity,
//			     int surplus[NUM_STATS],
//			     boolean *disorder, boolean *happy)
//{
//  surplus[FOOD] = pcity.food_surplus;
//  surplus[SHIELD] = pcity.shield_surplus;
//  surplus[TRADE] = pcity.trade_prod;
//  surplus[GOLD] = city_gold_surplus(pcity, pcity.tax_total);
//  surplus[LUXURY] = pcity.luxury_total;
//  surplus[SCIENCE] = pcity.science_total;
//
//  *disorder = City.city_unhappy(pcity);
//  *happy = City.city_happy(pcity);
//}
//
///****************************************************************************
//  Compute the fitness of the solution.  This is a fairly expensive operation.
//****************************************************************************/
//static struct cm_fitness evaluate_solution(cm_state state,
//    final partial_solution soln)
//{
//  city pcity = state.pcity;
//  struct city backup;
//  int surplus[NUM_STATS];
//  boolean disorder, happy;
//
//  /* make a backup, apply and evaluate the solution, and restore.  This costs
//   * one "apply". */
//  memcpy(&backup, pcity, sizeof(backup));
//  apply_solution(state, soln);
//  get_city_surplus(pcity, surplus, &disorder, &happy);
//  memcpy(pcity, &backup, sizeof(backup));
//
//  return compute_fitness(surplus, disorder, happy, &state.parameter);
//}
//
///****************************************************************************
//  Convert the solution into a cm_result.  This is a fairly expensive
//  operation.
//****************************************************************************/
//static void convert_solution_to_result(cm_state state,
//				       final partial_solution soln,
//				       cm_result result)
//{
//  struct city backup;
//  struct cm_fitness fitness;
//
//  if (soln.idle != 0) {
//    /* If there are unplaced citizens it's not a real solution, so the
//     * result is invalid. */
//    result.found_a_valid = false;
//    return;
//  }
//
//  /* make a backup, apply and evaluate the solution, and restore */
//  memcpy(&backup, state.pcity, sizeof(backup));
//  apply_solution(state, soln);
//  cm_copy_result_from_city(state.pcity, result);
//  memcpy(state.pcity, &backup, sizeof(backup));
//
//  /* result.found_a_valid should be only true if it matches the
//     parameter ; figure out if it does */
//  fitness = compute_fitness(result.surplus, result.disorder,
//			    result.happy, &state.parameter);
//  result.found_a_valid = fitness.sufficient;
//}
//
///***************************************************************************
//  Compare functions to allow sorting lattice vectors.
// ***************************************************************************/
//
///****************************************************************************
//  All the sorting in this code needs to respect the partial order
//  in the lattice: if a is a parent of b, then a must sort before b.
//  This routine enforces that relatively cheaply (without looking through
//  the worse_types vectors of a and b), but requires that lattice_depth
//  has already been computed.
//****************************************************************************/
//static int compare_tile_type_by_lattice_order(final cm_tile_type a,
//					      final cm_tile_type b)
//{
//  enum cm_stat stat;
//
//  if (a == b) {
//    return 0;
//  }
//
//  /* Least depth first */
//  if (a.lattice_depth != b.lattice_depth) {
//    return a.lattice_depth - b.lattice_depth;
//  }
//
//  /* With equal depth, break ties arbitrarily, more production first. */
//  for (stat = 0; stat < NUM_STATS; stat++) {
//    if (a.production[stat] != b.production[stat]) {
//      return b.production[stat] - a.production[stat];
//    }
//  }
//
//  /* If we get here, then we have two copies of identical types, an error */
//  assert(0!=1);
//  return 0;
//}
//
///****************************************************************************
//  Sort by fitness.  Since fitness is monotone in the production,
//  if a has higher fitness than b, then a cannot be a child of b, so
//  this respects the partial order -- unless a and b have equal fitness.
//  In that case, use compare_tile_type_by_lattice_order.
//****************************************************************************/
//static int compare_tile_type_by_fitness(final void *va, final void *vb)
//{
//  cm_tile_type  final *a = va;
//  cm_tile_type  final *b = vb;
//  double diff;
//
//  if (*a == *b) {
//    return 0;
//  }
//
//  /* To avoid double.int roundoff problems, we call a result non-zero only
//   * if it's larger than 0.5. */
//  diff = (*b).estimated_fitness - (*a).estimated_fitness;
//  if (diff > 0.5) {
//    return 1; /* return value is int; don't round down! */
//  }
//  if (diff < -0.5) {
//    return -1;
//  }
//
//  return compare_tile_type_by_lattice_order(*a, *b);
//}
//
//static enum cm_stat compare_key;
//
///****************************************************************************
//  Compare by the production of type compare_key.
//  If a produces more food than b, then a cannot be a child of b, so
//  this respects the partial order -- unless a and b produce equal food.
//  In that case, use compare_tile_type_by_lattice_order.
//****************************************************************************/
//static int compare_tile_type_by_stat(final void *va, final void *vb)
//{
//  cm_tile_type  final *a = va;
//  cm_tile_type  final *b = vb;
//
//  if (*a == *b) {
//    return 0;
//  }
//
//  /* most production of what we care about goes first */
//  if ((*a).production[compare_key] != (*b).production[compare_key]) {
//    /* b-a so we sort big numbers first */
//    return (*b).production[compare_key] - (*a).production[compare_key];
//  }
//
//  return compare_tile_type_by_lattice_order(*a, *b);
//}
//
///***************************************************************************
//  Compute the tile-type lattice.
// ***************************************************************************/
//
///****************************************************************************
//  Compute the production of tile [x,y] and stuff it into the tile type.
//  Doesn't touch the other fields.
//****************************************************************************/
//static void compute_tile_production(final city pcity, int x, int y,
//				    cm_tile_type out)
//{
//  boolean is_celebrating = base_City.city_celebrating(pcity);
//
//  out.production[FOOD]
//    = base_city_get_food_tile(x, y, pcity, is_celebrating);
//  out.production[SHIELD]
//    = base_city_get_shields_tile(x, y, pcity, is_celebrating);
//  out.production[TRADE]
//    = base_city_get_trade_tile(x, y, pcity, is_celebrating);
//  out.production[GOLD] = out.production[SCIENCE]
//    = out.production[LUXURY] = 0;
//}
//
///****************************************************************************
//  Add the tile [x,y], with production indicated by type, to
//  the tile-type lattice.  'newtype' can be on the stack.
//  x/y are ignored if the type is a specialist.
//  If the type is new, it is linked in and the lattice_index set.
//  The lattice_depth is not set.
//****************************************************************************/
//static void tile_type_lattice_add(tile_type_vector lattice,
//				  final cm_tile_type newtype,
//				  int x, int y)
//{
//  cm_tile_type type;
//  int i;
//
//  i = tile_type_vector_find_equivalent(lattice, newtype);
//  if(i >= 0) {
//    /* We already have this type of tile; use it. */
//    type = lattice.p[i];
//  } else {
//    /* This is a new tile type; add it to the lattice. */
//    type = tile_type_dup(newtype);
//
//    /* link up to the types we dominate, and those that dominate us */
//    tile_type_vector_iterate(lattice, other) {
//      if (tile_type_better(other, type)) {
//        tile_type_vector_add(&type.better_types, other);
//        tile_type_vector_add(&other.worse_types, type);
//      } else if (tile_type_better(type, other)) {
//        tile_type_vector_add(&other.better_types, type);
//        tile_type_vector_add(&type.worse_types, other);
//      }
//    } tile_type_vector_iterate_end;
//
//    /* insert into the list */
//    type.lattice_index = lattice.size;
//    tile_type_vector_add(lattice, type);
//  }
//
//  /* Finally, add the tile to the tile type. */
//  if (!type.is_specialist) {
//    struct cm_tile tile;
//
//    tile.type = type;
//    tile.x = x;
//    tile.y = y;
//    tile_vector_append(&type.tiles, &tile);
//  }
//}
//
///*
// * Add the specialist types to the lattice.
// * This structure is necessary for now because each specialist
// * creates only one type of production and we need to map
// * indices from specialist_type to cm_stat.
// */
//struct spec_stat_pair {
//  enum specialist_type spec;
//  enum cm_stat stat;
//};
//final static struct spec_stat_pair pairs[specialist_type.getSize()] =  {
//  { specialist_type.SP_ELVIS, LUXURY },
//  { specialist_type.SP_SCIENTIST, SCIENCE },
//  { specialist_type.SP_TAXMAN, GOLD }
//};
//
///****************************************************************************
//  Create lattice nodes for each type of specialist.  This adds a new
//  tile_type for each specialist type.
//****************************************************************************/
//static void init_specialist_lattice_nodes(tile_type_vector lattice,
//					  final city pcity)
//{
//  struct cm_tile_type type;
//
//  tile_type_init(&type);
//  type.is_specialist = true;
//
//  /* for each specialist type, create a tile_type that has as production
//   * the bonus for the specialist (if the city is allowed to use it) */
//  specialist_type_iterate(i) {
//    if (City.city_can_use_specialist(pcity, pairs[i].spec)) {
//      type.spec = pairs[i].spec;
//      type.production[pairs[i].stat]
//        = Game.game.rgame.specialists[pairs[i].spec].bonus;
//
//      tile_type_lattice_add(lattice, &type, 0, 0);
//
//      type.production[pairs[i].stat] = 0;
//    }
//  } specialist_type_iterate_end;
//}
//
///****************************************************************************
//  Topologically sort the lattice.
//  Sets the lattice_depth field.
//  Important assumption in this code: we've computed the transitive
//  closure of the lattice. That is, better_types includes all types that
//  are better.
//****************************************************************************/
//static void top_sort_lattice(tile_type_vector lattice)
//{
//  int i;
//  boolean marked[lattice.size];
//  boolean will_mark[lattice.size];
//  struct tile_type_vector vectors[2];
//  tile_type_vector current, *next;
//
//  memset(marked, 0, sizeof(marked));
//  memset(will_mark, 0, sizeof(will_mark));
//
//  tile_type_vector_init(&vectors[0]);
//  tile_type_vector_init(&vectors[1]);
//  current = &vectors[0];
//  next = &vectors[1];
//
//  /* fill up 'next' */
//  tile_type_vector_iterate(lattice, ptype) {
//    if (tile_type_num_prereqs(ptype) == 0) {
//      tile_type_vector_add(next, ptype);
//    }
//  } tile_type_vector_iterate_end;
//
//  /* while we have nodes to process: mark the nodes whose prereqs have
//   * all been visited.  Then, store all the new nodes on the frontier. */
//  while (next.size != 0) {
//    /* what was the next frontier is now the current frontier */
//    tile_type_vector vtmp = current;
//
//    current = next;
//    next = vtmp;
//    next.size = 0; /* clear out the contents */
//
//    /* look over the current frontier and process everyone */
//    tile_type_vector_iterate(current, ptype) {
//      /* see if all prereqs were marked.  If so, decide to mark this guy,
//         and put all the descendents on 'next'.  */
//      boolean can_mark = true;
//      int sumdepth = 0;
//
//      if (will_mark[ptype.lattice_index]) {
//        continue; /* we've already been processed */
//      }
//      tile_type_vector_iterate(&ptype.better_types, better) {
//        if (!marked[better.lattice_index]) {
//          can_mark = false;
//          break;
//        } else {
//          sumdepth += tile_type_num_tiles(better);
//          if (sumdepth >= FC_INFINITY) {
//            /* if this is the case, then something better could
//               always be used, and the same holds for our children */
//            sumdepth = FC_INFINITY;
//            can_mark = true;
//            break;
//          }
//        }
//      } tile_type_vector_iterate_end;
//      if (can_mark) {
//        /* mark and put successors on the next frontier */
//        will_mark[ptype.lattice_index] = true;
//        tile_type_vector_iterate(&ptype.worse_types, worse) {
//          tile_type_vector_add(next, worse);
//        } tile_type_vector_iterate_end;
//
//        /* this is what we spent all this time computing. */
//        ptype.lattice_depth = sumdepth;
//      }
//    } tile_type_vector_iterate_end;
//
//    /* now, actually mark everyone and get set for next loop */
//    for (i = 0; i < lattice.size; i++) {
//      marked[i] = marked[i] || will_mark[i];
//      will_mark[i] = false;
//    }
//  }
//
//  tile_type_vector_free(&vectors[0]);
//  tile_type_vector_free(&vectors[1]);
//}
//
///****************************************************************************
//  Remove unreachable lattice nodes to speed up processing later.
//  This doesn't reduce the number of evaluations we do, it just
//  reduces the number of times we loop over and reject tile types
//  we can never use.
//
//  A node is unreachable if there are fewer available workers
//  than are needed to fill up all predecessors.  A node at depth
//  two needs three workers to be reachable, for example (two to fill
//  the predecessors, and one for the tile).  We remove a node if
//  its depth is equal to the city size, or larger.
//
//  We could clean up the tile arrays in each type (if we have two workers,
//  we can use only the first tile of a depth 1 tile type), but that
//  wouldn't save us anything later.
//****************************************************************************/
//static void clean_lattice(tile_type_vector lattice,
//			  final city pcity)
//{
//  int i, j; /* i is the index we read, j is the index we write */
//  struct tile_type_vector tofree;
//
//  /* We collect the types we want to remove and free them in one fell 
//     swoop at the end, in order to avoid memory errors.  */
//  tile_type_vector_init(&tofree);
//
//  for (i = 0, j = 0; i < lattice.size; i++) {
//    cm_tile_type ptype = lattice.p[i];
//
//    if (ptype.lattice_depth >= pcity.size) {
//      tile_type_vector_add(&tofree, ptype);
//    } else {
//      /* Remove links to children that are being removed. */
//
//      int ci, cj; /* 'c' for 'child'; read from ci, write to cj */
//
//      lattice.p[j] = ptype;
//      lattice.p[j].lattice_index = j;
//      j++;
//
//      for (ci = 0, cj = 0; ci < ptype.worse_types.size; ci++) {
//        final cm_tile_type ptype2 = ptype.worse_types.p[ci];
//
//        if (ptype2.lattice_depth < pcity.size) {
//          ptype.worse_types.p[cj] = ptype.worse_types.p[ci];
//          cj++;
//        }
//      }
//      ptype.worse_types.size = cj;
//    }
//  }
//  lattice.size = j;
//
//  tile_type_vector_free_all(&tofree);
//}
//
///****************************************************************************
//  Determine the estimated_fitness fields, and sort by that.
//  estimate_fitness is later, in a section of code that isolates
//  much of the domain-specific knowledge.
//****************************************************************************/
//static double estimate_fitness(final cm_state state,
//			       final int production[NUM_STATS]);
//
//static void sort_lattice_by_fitness(final cm_state state,
//				    tile_type_vector lattice)
//{
//  int i;
//
//  /* compute fitness */
//  tile_type_vector_iterate(lattice, ptype) {
//    ptype.estimated_fitness = estimate_fitness(state, ptype.production);
//  } tile_type_vector_iterate_end;
//
//  /* sort by it */
//  qsort(lattice.p, lattice.size, sizeof(*lattice.p),
//	compare_tile_type_by_fitness);
//
//  /* fix the lattice indices */
//  for (i = 0; i < lattice.size; i++) {
//    lattice.p[i].lattice_index = i;
//  }
//
//  util.freelog(LOG_LATTICE, "sorted lattice:");
//  print_lattice(LOG_LATTICE, lattice);
//}
//
///****************************************************************************
//  Create the lattice.
//****************************************************************************/
//static void init_tile_lattice(final city pcity,
//			      tile_type_vector lattice)
//{
//  struct cm_tile_type type;
//
//  /* add all the fields into the lattice */
//  tile_type_init(&type); /* init just once */
//  my_city_map_iterate(pcity, x, y) {
//    if (pcity.city_map[x][y] == city_tile_type.C_TILE_UNAVAILABLE) {
//      continue;
//    }
//    if (!City.is_city_center(x, y)) {
//      compute_tile_production(pcity, x, y, &type); /* clobbers type */
//      tile_type_lattice_add(lattice, &type, x, y); /* copy type if needed */
//    }
//  } my_city_map_iterate_end;
//
//  /* Add all the specialists into the lattice.  */
//  init_specialist_lattice_nodes(lattice, pcity);
//
//  /* Set the lattice_depth fields, and clean up unreachable nodes. */
//  top_sort_lattice(lattice);
//  clean_lattice(lattice, pcity);
//
//  /* All done now. */
//  print_lattice(LOG_LATTICE, lattice);
//}
//
//
///****************************************************************************
//
//               Handling the choice stack for the bb algorithm.
//
//****************************************************************************/
//
//
///****************************************************************************
//  Return true iff the stack is empty.
//****************************************************************************/
//static boolean choice_stack_empty(cm_state state)
//{
//  return state.choice.size == 0;
//}
//
///****************************************************************************
//  Return the last choice in the stack.
//****************************************************************************/
//static int last_choice(cm_state state)
//{
//  assert(!choice_stack_empty(state));
//  return state.choice.stack[state.choice.size - 1];
//}
//
///****************************************************************************
//  Return the number of different tile types.  There is one tile type for
//  each type specialist, plus one for each distinct (different amounts of
//  production) citymap tile.
//****************************************************************************/
//static int num_types(final cm_state state)
//{
//  return tile_type_vector_size(&state.lattice);
//}
//
///****************************************************************************
//  Update the solution to assign 'number' more workers on to tiles of the
//  given type.  'number' may be negative, in which case we're removing
//  workers.
//  We do lots of sanity checking, since many bugs can get caught here.
//****************************************************************************/
//static void add_workers(partial_solution soln,
//			int itype, int number,
//			final cm_state state)
//{
//  enum cm_stat stat;
//  final cm_tile_type ptype = tile_type_get(state, itype);
//  int newcount;
//  int old_worker_count = soln.worker_counts[itype];
//
//  if (number == 0) {
//    return;
//  }
//
//  /* update the number of idle workers */
//  newcount = soln.idle - number;
//  assert(newcount >= 0);
//  assert(newcount <= state.pcity.size);
//  soln.idle = newcount;
//
//  /* update the worker counts */
//  newcount = soln.worker_counts[itype] + number;
//  assert(newcount >= 0);
//  assert(newcount <= tile_type_num_tiles(ptype));
//  soln.worker_counts[itype] = newcount;
//
//  /* update prereqs array: if we are no longer full but we were,
//   * we need to decrement the count, and vice-versa. */
//  if (old_worker_count == tile_type_num_tiles(ptype)) {
//    assert(number < 0);
//    tile_type_vector_iterate(&ptype.worse_types, other) {
//      soln.prereqs_filled[other.lattice_index]--;
//      assert(soln.prereqs_filled[other.lattice_index] >= 0);
//    } tile_type_vector_iterate_end;
//  } else if (soln.worker_counts[itype] == tile_type_num_tiles(ptype)) {
//    assert(number > 0);
//    tile_type_vector_iterate(&ptype.worse_types, other) {
//      soln.prereqs_filled[other.lattice_index]++;
//      assert(soln.prereqs_filled[other.lattice_index]
//          <= tile_type_num_prereqs(other));
//    } tile_type_vector_iterate_end;
//  }
//
//  /* update production */
//  for (stat = 0 ; stat < NUM_STATS; stat++) {
//    newcount = soln.production[stat] + number * ptype.production[stat];
//    assert(newcount >= 0);
//    soln.production[stat] = newcount;
//  }
//}
//
///****************************************************************************
//  Add just one worker to the solution.
//****************************************************************************/
//static void add_worker(partial_solution soln,
//		       int itype, final cm_state state)
//{
//  add_workers(soln, itype, 1, state);
//}
//
///****************************************************************************
//  Remove just one worker from the solution.
//****************************************************************************/
//static void remove_worker(partial_solution soln,
//			  int itype, final cm_state state)
//{
//  add_workers(soln, itype, -1, state);
//}
//
///****************************************************************************
//  Remove a worker from the current solution, and pop once off the
//  choice stack.
//****************************************************************************/
//static void pop_choice(cm_state state)
//{
//  assert(!choice_stack_empty(state));
//  remove_worker(&state.current, last_choice(state), state);
//  state.choice.size--;
//}
//
///****************************************************************************
//  true if all tiles better than this type have been used.
//****************************************************************************/
//static boolean prereqs_filled(final partial_solution soln, int type,
//			   final cm_state state)
//{
//  final cm_tile_type ptype = tile_type_get(state, type);
//  int prereqs = tile_type_num_prereqs(ptype);
//
//  return soln.prereqs_filled[type] == prereqs;
//}
//
///****************************************************************************
//  Return the next choice to make after oldchoice.
//  A choice can be made if:
//  - we haven't used all the tiles
//  - we've used up all the better tiles
//  - using that choice, we have a hope of doing better than the best
//    solution so far.
//  If oldchoice == -1 then we return the first possible choice.
//****************************************************************************/
//static boolean choice_is_promising(cm_state state, int newchoice);
//
//static int next_choice(cm_state state, int oldchoice)
//{
//  int newchoice;
//
//  for (newchoice = oldchoice + 1;
//       newchoice < num_types(state); newchoice++) {
//    final cm_tile_type ptype = tile_type_get(state, newchoice);
//
//    if(!ptype.is_specialist && (state.current.worker_counts[newchoice]
//				 == tile_vector_size(&ptype.tiles))) {
//      /* we've already used all these tiles */
//      continue;
//    }
//    if (!prereqs_filled(&state.current, newchoice, state)) {
//      /* we could use a strictly better tile instead */
//      continue;
//    }
//    if (!choice_is_promising(state, newchoice)) {
//      /* heuristic says we can't beat the best going this way */
//      util.freelog(LOG_PRUNE_BRANCH, "--- pruning branch ---");
//      print_partial_solution(LOG_PRUNE_BRANCH, &state.current, state);
//      print_tile_type(LOG_PRUNE_BRANCH, tile_type_get(state, newchoice),
//          " + worker on ");
//      util.freelog(LOG_PRUNE_BRANCH, "--- branch pruned ---");
//      continue;
//    }
//    break;
//  }
//
//  /* returns num_types if no next choice was available. */
//  return newchoice;
//}
//
//
///****************************************************************************
//  Pick a sibling choice to the last choice.  This works down the branch to
//  see if a choice that actually looks worse may actually be better.
//****************************************************************************/
//static boolean take_sibling_choice(cm_state state)
//{
//  int oldchoice = last_choice(state);
//  int newchoice;
//
//  /* need to remove first, to run the heuristic */
//  remove_worker(&state.current, oldchoice, state);
//  newchoice = next_choice(state, oldchoice);
//
//  if (newchoice == num_types(state)) {
//    /* add back in so the caller can then remove it again. */
//    add_worker(&state.current, oldchoice, state);
//    return false;
//  } else {
//    add_worker(&state.current, newchoice, state);
//    state.choice.stack[state.choice.size-1] = newchoice;
//    /* choice.size is unchanged */
//    return true;
//  }
//}
//
///****************************************************************************
//  Go down from the current branch, if we can.
//  Thanks to the fact that the lattice is sorted by depth, we can keep the
//  choice stack sorted -- that is, we can start our next choice as
//  last_choice-1.  This keeps us from trying out all permutations of the
//  same combination.
//****************************************************************************/
//static boolean take_child_choice(cm_state state)
//{
//  int oldchoice, newchoice;
//
//  if (state.current.idle == 0) {
//    return false;
//  }
//
//  if (state.choice.size == 0) {
//    oldchoice = 0;
//  } else {
//    oldchoice = last_choice(state);
//  }
//
//  /* oldchoice-1 because we can use oldchoice again */
//  newchoice = next_choice(state, oldchoice - 1);
//
//  /* did we fail? */
//  if (newchoice == num_types(state)) {
//    return false;
//  }
//
//  /* now push the new choice on the choice stack */
//  add_worker(&state.current, newchoice, state);
//  state.choice.stack[state.choice.size] = newchoice;
//  state.choice.size++;
//  return true;
//}
//
//
///****************************************************************************
//  Complete the solution by choosing tiles in order off the given
//  tile lattice.
//****************************************************************************/
//static void complete_solution(partial_solution soln,
//			      final cm_state state,
//			      final tile_type_vector lattice)
//{
//  int last_choice = -1;
//  int i;
//
//  if (soln.idle == 0) {
//    return;
//  }
//
//  /* find the last worker type added (-1 if none) */
//  for (i = 0; i < num_types(state); i++) {
//    if (soln.worker_counts[i] != 0) {
//      last_choice = i;
//    }
//  }
//
//  /* While there are idle workers, pick up the next-best kind of tile,
//   * and assign the workers to the tiles.
//   * Respect lexicographic order and prerequisites.  */
//  tile_type_vector_iterate(lattice, ptype) {
//    int used = soln.worker_counts[ptype.lattice_index];
//    int total = tile_type_num_tiles(ptype);
//    int touse;
//
//    if (ptype.lattice_index < last_choice) {
//      /* lex-order: we can't use ptype (some other branch
//         will check this combination, or already did) */
//	continue;
//    }
//    if (!prereqs_filled(soln, ptype.lattice_index, state)) {
//      /* don't bother using this tile before all better tiles are used */
//      continue;
//    }
//
//    touse = total - used;
//    if (touse > soln.idle) {
//      touse = soln.idle;
//    }
//    add_workers(soln, ptype.lattice_index, touse, state);
//
//    if (soln.idle == 0) {
//      /* nothing left to do here */
//      return;
//    }
//  } tile_type_vector_iterate_end;
//}
//
///****************************************************************************
//  The heuristic:
//  A partial solution cannot produce more food than the amount of food it
//  currently generates plus then placing all its workers on the best food
//  tiles.  Similarly with all the other stats.
//  If we take the max along each production, and it's not better than the
//  best in at least one stat, the partial solution isn't worth anything.
//
//  This function computes the max-stats produced by a partial solution.
//****************************************************************************/
//static void compute_max_stats_heuristic(final cm_state state,
//					final partial_solution soln,
//					int production[NUM_STATS],
//					int check_choice)
//{
//  enum cm_stat stat;
//  struct partial_solution solnplus; /* will be soln, plus some tiles */
//
//  /* Production is whatever the solution produces, plus the
//     most possible of each kind of production the idle workers could
//     produce */
//
//  if (soln.idle == 1) {
//    /* Then the total solution is soln + this new worker.  So we know the
//       production exactly, and can shortcut the later code. */
//    enum cm_stat stat;
//    final cm_tile_type ptype = tile_type_get(state, check_choice);
//
//    memcpy(production, soln.production, sizeof(soln.production));
//    for (stat = 0; stat < NUM_STATS; stat++) {
//      production[stat] += ptype.production[stat];
//    }
//    return;
//  }
//
//  /* initialize solnplus here, after the shortcut check */
//  init_partial_solution(&solnplus, num_types(state), state.pcity.size);
//
//  for (stat = 0; stat < NUM_STATS; stat++) {
//    /* compute the solution that has soln, then the check_choice,
//       then complete it with the best available tiles for the stat. */
//    copy_partial_solution(&solnplus, soln, state);
//    add_worker(&solnplus, check_choice, state);
//    complete_solution(&solnplus, state, &state.lattice_by_prod[stat]);
//
//    production[stat] = solnplus.production[stat];
//  }
//
//  destroy_partial_solution(&solnplus);
//}
//
///****************************************************************************
//  A choice is unpromising if isn't better than the best in at least
//  one way.
//  A choice is also unpromising if any of the stats is less than the
//  absolute minimum (in practice, this matters a lot more).
//****************************************************************************/
//static boolean choice_is_promising(cm_state state, int newchoice)
//{
//  int production[NUM_STATS];
//  enum cm_stat stat;
//  boolean beats_best = false;
//
//  compute_max_stats_heuristic(state, &state.current, production, newchoice);
//
//  for (stat = 0; stat < NUM_STATS; stat++) {
//    if (production[stat] < state.min_production[stat]) {
//      util.freelog(LOG_PRUNE_BRANCH, "--- pruning: insufficient %s (%d < %d)",
//	      cm_get_stat_name(stat), production[stat],
//	      state.min_production[stat]);
//      return false;
//    }
//    if (production[stat] > state.best.production[stat]) {
//      beats_best = true;
//      /* may still fail to meet min at another production type, so
//       * don't short-circuit */
//    }
//  }
//  if (!beats_best) {
//    util.freelog(LOG_PRUNE_BRANCH, "--- pruning: best is better in all ways");
//  }
//  return beats_best;
//}
//
///****************************************************************************
//  These two functions are very specific for the default civ2-like ruleset.
//  These require the parameter to have been set.
//  FIXME -- this should be more general.
//****************************************************************************/
//static void init_min_production(cm_state state)
//{
//  int x = CITY_MAP_RADIUS, y = CITY_MAP_RADIUS;
//  int usage[NUM_STATS];
//  city pcity = state.pcity;
//  boolean is_celebrating = base_City.city_celebrating(pcity);
//  struct city backup;
//
//  /* make sure the city's numbers make sense (sometimes they don't,
//   * somehow) */
//  memcpy(&backup, pcity, sizeof(*pcity));
//  generic_city_refresh(pcity, false, null);
//
//  memset(state.min_production, 0, sizeof(state.min_production));
//
//  /* If the city is content, then we know the food usage is just
//   * prod-surplus; otherwise, we know it's at least 2*size but we
//   * can't easily compute the settlers. */
//  if (!City.city_unhappy(pcity)) {
//    usage[FOOD] = pcity.food_prod - pcity.food_surplus;
//  } else {
//    usage[FOOD] = pcity.size * 2;
//  }
//  state.min_production[FOOD] = usage[FOOD]
//    + state.parameter.minimal_surplus[FOOD]
//    - base_city_get_food_tile(x, y, pcity, is_celebrating);
//
//  /* surplus = (factories-waste) * production - shield_usage, so:
//   *   production = (surplus + shield_usage)/(factories-waste)
//   * waste >= 0, so:
//   *   production >= (surplus + usage)/factories
//   * Solving with surplus >= min_surplus, we get:
//   *   production >= (min_surplus + usage)/factories
//   * 'factories' is the pcity.shield_bonus/100.  Increase it a bit to avoid
//   * rounding errors.
//   *
//   * pcity.shield_prod = (factories-waste) * production.
//   * Therefore, shield_usage = pcity.shield_prod - pcity.shield_surplus
//   */
//  if (!City.city_unhappy(pcity)) {
//    double sbonus;
//
//    usage[SHIELD] = pcity.shield_prod - pcity.shield_surplus;
//
//    sbonus = ((double)pcity.shield_bonus) / 100.0;
//    sbonus += .1;
//    state.min_production[SHIELD]
//      = (usage[SHIELD] + state.parameter.minimal_surplus[SHIELD]) / sbonus;
//    state.min_production[SHIELD]
//      -= base_city_get_shields_tile(x, y, pcity, is_celebrating);
//  } else {
//    /* Dunno what the usage is, so it's pointless to set the
//     * min_production */
//    usage[SHIELD] = 0;
//    state.min_production[SHIELD] = 0;
//  }
//
//  /* we should be able to get a min_production on gold and trade, too;
//     also, lux, if require_happy, but not otherwise */
//
//  /* undo any effects from the refresh */
//  memcpy(pcity, &backup, sizeof(*pcity));
//}
//
///****************************************************************************
//  Estimate the fitness of a tile.  Tiles are put into the lattice in
//  fitness order, so that we start off choosing better tiles.
//  The estimate MUST be monotone in the inputs; if it isn't, then
//  the BB algorithm will fail.
//
//  The only fields of the state used are the city and parameter.
//****************************************************************************/
//static double estimate_fitness(final cm_state state,
//			       final int production[NUM_STATS]) {
//  final city pcity = state.pcity;
//  final player pplayer = get_player(pcity.owner);
//  enum cm_stat stat;
//  double estimates[NUM_STATS];
//  double sum = 0;
//
//  for (stat = 0; stat < NUM_STATS; stat++) {
//    estimates[stat] = production[stat];
//  }
//
//  /* sci/lux/gold get benefit from the tax rates (in percentage) */
//  estimates[SCIENCE] += pplayer.economic.science * estimates[TRADE] / 100.0;
//  estimates[LUXURY] += pplayer.economic.luxury  * estimates[TRADE] / 100.0;
//  estimates[GOLD] += pplayer.economic.tax     * estimates[TRADE] / 100.0;
//
//  /* now add in the bonuses (none for food or trade) (in percentage) */
//  estimates[SHIELD] *= pcity.shield_bonus / 100.0;
//  estimates[LUXURY] *= pcity.luxury_bonus / 100.0;
//  estimates[GOLD] *= pcity.tax_bonus / 100.0;
//  estimates[SCIENCE] *= pcity.science_bonus / 100.0;
//
//  /* finally, sum it all up, weighted by the parameter, but give additional
//   * weight to luxuries to take account of disorder/happy finalraints */
//  for (stat = 0; stat < NUM_STATS; stat++) {
//    sum += estimates[stat] * state.parameter.factor[stat];
//  }
//  sum += estimates[LUXURY];
//  return sum;
//}
//
//
//
///****************************************************************************
//  The high-level algorithm is:
//
//  for each idle worker,
//      non-deterministically choose a position for the idle worker to use
//
//  To implement this, we keep a stack of the choices we've made.
//  When we want the next node in the tree, we see if there are any idle
//  workers left.  We push an idle worker, and make it take the first field
//  in the lattice.  If there are no idle workers left, then we pop out
//  until we can make another choice.
//****************************************************************************/
//static boolean bb_next(cm_state state)
//{
//  /* if no idle workers, then look at our solution. */
//  if (state.current.idle == 0) {
//    struct cm_fitness value = evaluate_solution(state, &state.current);
//
//    print_partial_solution(LOG_REACHED_LEAF, &state.current, state);
//    if (fitness_better(value, state.best_value)) {
//      util.freelog(LOG_BETTER_LEAF, ". replaces previous best");
//      copy_partial_solution(&state.best, &state.current, state);
//      state.best_value = value;
//    }
//  }
//
//  /* try to move to a child branch, if we can.  If not (including if we're
//     at a leaf), then move to a sibling. */
//  if (!take_child_choice(state)) {
//    /* keep trying to move to a sibling branch, or popping out a level if
//       we're stuck (fully examined the current branch) */
//    while ((!choice_stack_empty(state)) && !take_sibling_choice(state)) {
//      pop_choice(state);
//    }
//
//    /* if we popped out all the way, we're done */
//    if (choice_stack_empty(state)) {
//      return true;
//    }
//  }
//
//  /* if we didn't detect that we were done, we aren't */
//  return false;
//}
//
///****************************************************************************
//  Initialize the state for the branch-and-bound algorithm.
//****************************************************************************/
//cm_state cm_init_state(city pcity)
//{
//  int numtypes;
//  enum cm_stat stat;
//  cm_state state = fc_malloc(sizeof(*state));
//
//  util.freelog(LOG_CM_STATE, "creating cm_state for %s (size %d)",
//	  pcity.name, pcity.size);
//
//  /* copy the arguments */
//  state.pcity = pcity;
//
//  /* create the lattice */
//  tile_type_vector_init(&state.lattice);
//  init_tile_lattice(pcity, &state.lattice);
//  numtypes = tile_type_vector_size(&state.lattice);
//
//  /* For the heuristic, make sorted copies of the lattice */
//  for (stat = 0; stat < NUM_STATS; stat++) {
//    tile_type_vector_init(&state.lattice_by_prod[stat]);
//    tile_type_vector_copy(&state.lattice_by_prod[stat], &state.lattice);
//    compare_key = stat;
//    qsort(state.lattice_by_prod[stat].p, state.lattice_by_prod[stat].size,
//	  sizeof(*state.lattice_by_prod[stat].p),
//	  compare_tile_type_by_stat);
//  }
//
//  /* We have no best solution yet, so its value is the worst possible. */
//  init_partial_solution(&state.best, numtypes, pcity.size);
//  state.best_value = worst_fitness();
//
//  /* Initialize the current solution and choice stack to empty */
//  init_partial_solution(&state.current, numtypes, pcity.size);
//  state.choice.stack = fc_malloc(pcity.size
//				  * sizeof(*state.choice.stack));
//  state.choice.size = 0;
//
//  return state;
//}
//
///****************************************************************************
//  Set the parameter for the state.  This is the first step in actually
//  solving anything.
//****************************************************************************/
//static void begin_search(cm_state state,
//			 final cm_parameter parameter)
//{
//#ifdef GATHER_TIME_STATS
//  start_timer(performance.current.wall_timer);
//  performance.current.query_count++;
//#endif
//
//  /* copy the parameter and sort the main lattice by it */
//  cm_copy_parameter(&state.parameter, parameter);
//  sort_lattice_by_fitness(state, &state.lattice);
//  init_min_production(state);
//
//  /* clear out the old solution */
//  state.best_value = worst_fitness();
//  destroy_partial_solution(&state.current);
//  init_partial_solution(&state.current, num_types(state),
//			state.pcity.size);
//  state.choice.size = 0;
//}
//
//
///****************************************************************************
//  Clean up after a search.
//  Currently, does nothing except stop the timer and output.
//****************************************************************************/
//static void end_search(cm_state state)
//{
//#ifdef GATHER_TIME_STATS
//  stop_timer(performance.current.wall_timer);
//
//#ifdef PRINT_TIME_STATS_EVERY_QUERY
//  print_performance(performance.current);
//#endif
//
//  performance.current = null;
//#endif
//}
//
///****************************************************************************
//  Release all the memory allocated by the state.
//****************************************************************************/
//void cm_free_state(cm_state state)
//{
//  enum cm_stat stat;
//
//  tile_type_vector_free_all(&state.lattice);
//  for (stat = 0; stat < NUM_STATS; stat++) {
//    tile_type_vector_free(&state.lattice_by_prod[stat]);
//  }
//  destroy_partial_solution(&state.best);
//  destroy_partial_solution(&state.current);
//  free(state.choice.stack);
//  free(state);
//}
//
//
///****************************************************************************
//  Run B&B until we find the best solution.
//****************************************************************************/
//void cm_find_best_solution(cm_state state,
//		      final cm_parameter final parameter,
//		     cm_result result) {
//#ifdef GATHER_TIME_STATS
//  performance.current = &performance.opt;
//#endif
//
//  begin_search(state, parameter);
//
//  /* search until we find a feasible solution */
//  while (!bb_next(state)) {
//    /* nothing */
//  }
//
//  /* convert to the caller's format */
//  convert_solution_to_result(state, &state.best, result);
//
//  end_search(state);
//}
//
///***************************************************************************
//  Wrapper that actually runs the branch & bound, and returns the best
//  solution.
// ***************************************************************************/
//void cm_query_result(city pcity,
//		     final cm_parameter param,
//		     cm_result result)
//{
//  cm_state state = cm_init_state(pcity);
//
//  /* Refresh the city.  Otherwise the CM can give wrong results or just be
//   * slower than necessary.  Note that cities are often passed in in an
//   * unrefreshed state (which should probably be fixed). */
//  generic_city_refresh(pcity, true, null);
//
//  cm_find_best_solution(state, param, result);
//  cm_free_state(state);
//}
//
//
///****************************************************************************
//  Return a translated name for the stat type.
//*****************************************************************************/
//final String cm_get_stat_name(enum cm_stat stat)
//{
//  switch (stat) {
//  case FOOD:
//    return "Food";
//  case SHIELD:
//    return "Shield";
//  case TRADE:
//    return "Trade";
//  case GOLD:
//    return "Gold";
//  case LUXURY:
//    return "Luxury";
//  case SCIENCE:
//    return "Science";
//  case NUM_STATS:
//    break;
//  }
//  util.die("Unknown stat value in cm_get_stat_name: %d", stat);
//  return null;
//}
//
///**************************************************************************
//  Returns true if the two cm_parameters are equal.
//**************************************************************************/
//boolean cm_are_parameter_equal(final cm_parameter final p1,
//			    final cm_parameter final p2)
//{
//  int i;
//
//  for (i = 0; i < NUM_STATS; i++) {
//    if (p1.minimal_surplus[i] != p2.minimal_surplus[i]) {
//      return false;
//    }
//    if (p1.factor[i] != p2.factor[i]) {
//      return false;
//    }
//  }
//  if (p1.require_happy != p2.require_happy) {
//    return false;
//  }
//  if (p1.allow_disorder != p2.allow_disorder) {
//    return false;
//  }
//  if (p1.allow_specialists != p2.allow_specialists) {
//    return false;
//  }
//  if (p1.happy_factor != p2.happy_factor) {
//    return false;
//  }
//
//  return true;
//}
//
///**************************************************************************
//  Copy the parameter from the source to the destination field.
//**************************************************************************/
//void cm_copy_parameter(cm_parameter dest,
//		       final cm_parameter final src)
//{
//  memcpy(dest, src, sizeof(struct cm_parameter));
//}
//
///**************************************************************************
//  Initialize the parameter to sane default values.
//**************************************************************************/
//void cm_init_parameter(cm_parameter dest)
//{
//  enum cm_stat stat;
//
//  for (stat = 0; stat < NUM_STATS; stat++) {
//    dest.minimal_surplus[stat] = 0;
//    dest.factor[stat] = 1;
//  }
//
//  dest.happy_factor = 1;
//  dest.require_happy = false;
//  dest.allow_disorder = false;
//  dest.allow_specialists = true;
//}
//
///***************************************************************************
//  Initialize the parameter to sane default values that will always produce
//  a result.
//***************************************************************************/
//void cm_init_emergency_parameter(cm_parameter dest)
//{
//  enum cm_stat stat;
//
//  for (stat = 0; stat < NUM_STATS; stat++) {
//    dest.minimal_surplus[stat] = -FC_INFINITY;
//    dest.factor[stat] = 1;
//  }
//
//  dest.happy_factor = 1;
//  dest.require_happy = false;
//  dest.allow_disorder = true;
//  dest.allow_specialists = true;
//}
//
///****************************************************************************
//  cm_result routines.
//****************************************************************************/
//int cm_count_worker(final city  pcity,
//		    final cm_result result)
//{
//  int count = 0;
//
//	for (int _itr = 0; _itr < City_H.CITY_MAP_SIZE * City_H.CITY_MAP_SIZE; _itr++) {	   
//	int x = _itr % City_H.CITY_MAP_SIZE, y = _itr / City_H.CITY_MAP_SIZE;	   
//
//	if (City.is_valid_city_coords(x, y)) {
//    if(result.worker_positions_used[x][y] && !City.is_city_center(x, y)) {
//      count++;
//    }
//  } };
//  return count;
//}
//
///****************************************************************************
//  Count the total number of specialists in the result.
//****************************************************************************/
//int cm_count_specialist(final city pcity,
//			final cm_result result)
//{
//  int count = 0;
//
//  specialist_type_iterate(spec) {
//    count += result.specialists[spec];
//  } specialist_type_iterate_end;
//
//  return count;
//}
//
///****************************************************************************
//  Copy the city's current setup into the cm result structure.
//****************************************************************************/
//void cm_copy_result_from_city(final city pcity,
//			      cm_result result)
//{
//  /* copy the map of where workers are */
//	for (int _itr = 0; _itr < City_H.CITY_MAP_SIZE * City_H.CITY_MAP_SIZE; _itr++) {	   
//	int x = _itr % City_H.CITY_MAP_SIZE, y = _itr / City_H.CITY_MAP_SIZE;	   
//
//	if (City.is_valid_city_coords(x, y)) {
//    result.worker_positions_used[x][y] =
//      (pcity.city_map[x][y] == city_tile_type.C_TILE_WORKER);
//  } };
//
//  /* copy the specialist counts */
//  specialist_type_iterate(spec) {
//    result.specialists[spec] = pcity.specialists[spec];
//  } specialist_type_iterate_end;
//
//  /* find the surplus production numbers */
//  get_city_surplus(pcity, result.surplus,
//      &result.disorder, &result.happy);
//
//  /* this is a valid result, in a sense */
//  result.found_a_valid = true;
//}
//
///****************************************************************************
//  Debugging routines.
//****************************************************************************/
//#ifdef CM_DEBUG
//static void snprint_production(char *buffer, size_t bufsz,
//			      final int production[NUM_STATS])
//{
//  int nout;
//
//  nout = snprintf(buffer, bufsz, "[%d %d %d %d %d %d]",
//		  production[FOOD], production[SHIELD],
//		  production[TRADE], production[GOLD],
//		  production[LUXURY], production[SCIENCE]);
//
//  assert(nout >= 0 && nout <= bufsz);
//}
//
///****************************************************************************
//  Print debugging data about a particular tile type.
//****************************************************************************/
//static void print_tile_type(int loglevel, final cm_tile_type ptype,
//			    final String prefix)
//{
//  char prodstr[256];
//
//  snprint_production(prodstr, sizeof(prodstr), ptype.production);
//  util.freelog(loglevel, "%s%s fitness %g depth %d, idx %d; %d tiles", prefix,
//	  prodstr, ptype.estimated_fitness, ptype.lattice_depth,
//	  ptype.lattice_index, tile_type_num_tiles(ptype));
//}
//
///****************************************************************************
//  Print debugging data about a whole B&B lattice.
//****************************************************************************/
//static void print_lattice(int loglevel,
//			  final tile_type_vector lattice)
//{
//  util.freelog(loglevel, "lattice has %u terrain types", (unsigned)lattice.size);
//  tile_type_vector_iterate(lattice, ptype) {
//    print_tile_type(loglevel, ptype, "  ");
//  } tile_type_vector_iterate_end;
//}
//
///****************************************************************************
//  Print debugging data about a partial CM solution.
//****************************************************************************/
//static void print_partial_solution(int loglevel,
//				   final partial_solution soln,
//				   final cm_state state)
//{
//  int i;
//  int last_type = 0;
//  char buf[256];
//
//  if(soln.idle != 0) {
//    util.freelog(loglevel, "** partial solution has %d idle workers", soln.idle);
//  } else {
//    util.freelog(loglevel, "** completed solution:");
//  }
//
//  snprint_production(buf, sizeof(buf), soln.production);
//  util.freelog(loglevel, "production: %s", buf);
//
//  util.freelog(loglevel, "tiles used:");
//  for (i = 0; i < num_types(state); i++) {
//    if (soln.worker_counts[i] != 0) {
//      snprintf(buf, sizeof(buf),
//          "  %d tiles of type ", soln.worker_counts[i]);
//      print_tile_type(loglevel, tile_type_get(state, i), buf);
//    }
//  }
//
//  for (i = 0; i < num_types(state); i++) {
//    if (soln.worker_counts[i] != 0) {
//      last_type = i;
//    }
//  }
//
//  util.freelog(loglevel, "tiles available:");
//  for (i = last_type; i < num_types(state); i++) {
//    final cm_tile_type ptype = tile_type_get(state, i);
//
//    if (soln.prereqs_filled[i] == tile_type_num_prereqs(ptype)
//	&& soln.worker_counts[i] < tile_type_num_tiles(ptype)) {
//      print_tile_type(loglevel, tile_type_get(state, i), "  ");
//    }
//  }
//}
//
//#endif /* CM_DEBUG */
//
//#ifdef GATHER_TIME_STATS
///****************************************************************************
//  Print debugging performance data.
//****************************************************************************/
//static void print_performance(one_perf counts)
//{
//  double s, ms;
//  double q;
//  int queries, applies;
//
//  s = read_timer_seconds(counts.wall_timer);
//  ms = 1000.0 * s;
//
//  queries = counts.query_count;
//  q = queries;
//
//  applies = counts.apply_count;
//
//  util.freelog(LOG_TIME_STATS,
//      "CM-%s: overall=%fs queries=%d %fms / query, %d applies",
//      counts.name, s, queries, ms / q, applies);
//}
//#endif
//
///****************************************************************************
//  Print debugging inormation about one city.
//****************************************************************************/
//void cm_print_city(final city pcity)
//{
//  util.freelog(Log.LOG_NORMAL, "print_city(city='%s'(id=%d))",
//          pcity.name, pcity.id);
//  util.freelog(Log.LOG_NORMAL,
//          "  size=%d, entertainers=%d, scientists=%d, taxmen=%d",
//          pcity.size, pcity.specialists[specialist_type.SP_ELVIS],
//          pcity.specialists[specialist_type.SP_SCIENTIST],
//          pcity.specialists[specialist_type.SP_TAXMAN]);
//  util.freelog(Log.LOG_NORMAL, "  workers at:");
//  my_city_map_iterate(pcity, x, y) {
//    if (pcity.city_map[x][y] == city_tile_type.C_TILE_WORKER) {
//      util.freelog(Log.LOG_NORMAL, "    (%2d,%2d)", x, y);
//    }
//  } my_city_map_iterate_end;
//
//  util.freelog(Log.LOG_NORMAL, "  food    = %3d (%+3d)",
//          pcity.food_prod, pcity.food_surplus);
//  util.freelog(Log.LOG_NORMAL, "  shield  = %3d (%+3d)",
//          pcity.shield_prod, pcity.shield_surplus);
//  util.freelog(Log.LOG_NORMAL, "  trade   = %3d", pcity.trade_prod);
//
//  util.freelog(Log.LOG_NORMAL, "  gold    = %3d (%+3d)", pcity.tax_total,
//          city_gold_surplus(pcity, pcity.tax_total));
//  util.freelog(Log.LOG_NORMAL, "  luxury  = %3d", pcity.luxury_total);
//  util.freelog(Log.LOG_NORMAL, "  science = %3d", pcity.science_total);
//}
//
//
///****************************************************************************
//  Print debugging inormation about a full CM result.
//****************************************************************************/
//void cm_print_result(final city pcity,
//		     final cm_result result)
//{
//  int y, i, worker = cm_count_worker(pcity, result);
//  util.freelog(Log.LOG_NORMAL, "print_result(result=%p)", result);
//  util.freelog(Log.LOG_NORMAL,
//      "print_result:  found_a_valid=%d disorder=%d happy=%d",
//      result.found_a_valid, result.disorder, result.happy);
//
//  util.freelog(Log.LOG_NORMAL, "print_result:  workers at:");
//  my_city_map_iterate(pcity, x, y) {
//    if (result.worker_positions_used[x][y]) {
//      util.freelog(Log.LOG_NORMAL, "print_result:    (%2d,%2d)", x, y);
//    }
//  } my_city_map_iterate_end;
//
//  for (y = 0; y < City_H.CITY_MAP_SIZE; y++) {
//    char line[City_H.CITY_MAP_SIZE + 1];
//    int x;
//
//    line[City_H.CITY_MAP_SIZE] = 0;
//
//    for (x = 0; x < City_H.CITY_MAP_SIZE; x++) {
//      if (!City.City.is_valid_city_coords(x, y)) {
//        line[x] = '-';
//      } else if (City.is_city_center(x, y)) {
//        line[x] = 'c';
//      } else if (result.worker_positions_used[x][y]) {
//        line[x] = 'w';
//      } else {
//        line[x] = '.';
//      }
//    }
//    util.freelog(Log.LOG_NORMAL, "print_result: %s", line);
//  }
//  util.freelog(Log.LOG_NORMAL,
//      "print_result:  people: (workers/specialists) %d/%s",
//      worker, specialists_string(result.specialists));
//
//  for (i = 0; i < NUM_STATS; i++) {
//    util.freelog(Log.LOG_NORMAL, "print_result:  %10s surplus=%d",
//        cm_get_stat_name(i), result.surplus[i]);
//  }
//}
}