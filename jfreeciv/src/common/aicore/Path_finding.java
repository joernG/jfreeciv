package common.aicore;

public class Path_finding{
///**********************************************************************
// Freeciv - Copyright (C) 2003 - The Freeciv Project
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
//
//#include "Game.game.h"
//#include "log.h"
//#include "Map.map.h"
//#include "mem.h"
//#include "Pqueue.h"
//
//#include "path_finding.h"
//
///* For explanations on how to use this module, see path_finding.h */
//
//public static final int INITIAL_QUEUE_SIZE = 100;
//
///* Since speed is quite important to us and alloccation of large arrays is
// * slow, we try to pack info in the smallest types possible */
//typedef short mapindex_t;
//typedef unsigned char utiny_t;
//
///* ===================== Internal structures ====================== */
///*
// * Some comments on implementation: 
// * 1. cost (aka total_MC) is sum of MCs altered to fit to the turn_mode
// * see adjust_cost
// * 2. dir_to_here is for backtracking along the tree of shortest paths
// * 3. node_known_type, behavior, zoc_number and extra_tile are all cached 
// * values.  
// * It is possible to shove them into a separate array which is allocated 
// * only if a corresponding option in the parameter is set.  A less drastic 
// * measure would be to pack the first three into one byte.  All of there are 
// * time-saving measures and should be tested once we get an established 
// * user-base.
// */
//struct pf_node {
//  int cost;			/* total_MC */
//  int extra_cost;		/* total_EC */
//  utiny_t dir_to_here;		/* direction from which we came */
//
//  /* Cached values */
//  int extra_tile;		/* EC */
//  utiny_t node_known_type;
//  utiny_t behavior;
//  utiny_t zoc_number;		/* 1 if allied, 2 if my zoc, 0 otherwise */
//};
//
///* 
// * All danger-related fields go into here.  For speed we separate it
// * from mainstream node.
// */
//struct danger_node {
//  boolean is_dangerous;
//  boolean waited;			/* true if waited to get here */
//  struct pf_danger_pos {
//    enum direction8 dir;
//    int cost;
//    int extra_cost;
//  } *danger_segment;  	        /* Segment leading across the danger area
//                                 * back to the nearest safe node: 
//				 * need to remeber costs and stuff */
//};
//
//enum pf_node_status {
//  NS_UNINIT = 0,		/* memory is calloced, hence zero 
//				 * means uninitialised */
//  NS_NEW,			/* the optimal route isn't found yet */
//  NS_WAITING,			/* the optimal route is found,
//				 * considering waiting */
//  NS_PROCESSED			/* the optimal route is found */
//};
//
//
///*
// * The map structure itself.  (x, y) is the current position of the iteration
// * (aka internal buffer); index is map_pos_to_index(x, y);
// */
//struct pf_map {
//  tile tile;		/* The current position */
//  pf_parameter params;  /* Initial parameters */
//  Pqueue queue;         /* Queue of nodes we have reached but not 
//                                 * processed yet (NS_NEW), sorted by their 
//                                 * total_CC*/
//  pf_node lattice;      /* Lattice of nodes */
//  utiny_t *status;		/* Array of node statuses 
//				 * (enum pf_node_status really) */
//  Pqueue danger_queue;	/* Dangerous positions go there */
//  danger_node d_lattice;	/* Lattice with danger stuff */
//};
//
//static boolean danger_iterate_map(pf_map pf_map);
//static struct pf_path* danger_finalruct_path(final pf_map pf_map,
//					     tile ptile);
//static pf_path danger_get_path(pf_map pf_map,
//				       tile ptile);
//
//
///* =================== manipulating the cost ===================== */
//
///********************************************************************
//  Number of turns required to reach node
//********************************************************************/
//static int get_turn(final pf_map pf_map, int cost)
//{
//  /* Negative cost can happen when a unit initially has more MP than its
//   * move-rate (due to wonders transfer etc).  Although this may be a bug, 
//   * we'd better be ready.
//   *
//   * Note that cost==0 corresponds to the current turn with full MP. */
//  return (cost < 0 ? 0 : cost / pf_map.params.move_rate);
//}
//
///********************************************************************
//  Moves left after node is reached
//********************************************************************/
//static int get_moves_left(final pf_map pf_map, int cost)
//{
//  /* Cost may be negative; see get_turn(). */
//  return (cost < 0 ? pf_map.params.move_rate - cost
//          : pf_map.params.move_rate - (cost % pf_map.params.move_rate));
//}
//
///********************************************************************
//  Adjust MC to reflect the turn mode and the move_rate.
//********************************************************************/
//static int adjust_cost(final pf_map pf_map, int cost)
//{
//  assert(cost >= 0);
//
//  switch (pf_map.params.turn_mode) {
//  case TM_NONE:
//    break;
//  case TM_CAPPED:
//    cost = Math.min(cost, pf_map.params.move_rate);
//    break;
//  case TM_WORST_TIME:
//    cost = Math.min(cost, pf_map.params.move_rate);
//    {
//      int moves_left
//	  = get_moves_left(pf_map, pf_map.lattice[pf_map.tile.index].cost);
//
//      if (cost > moves_left) {
//	cost += moves_left;
//      }
//      break;
//    }
//  case TM_BEST_TIME:
//    {
//      int moves_left
//	  = get_moves_left(pf_map, pf_map.lattice[pf_map.tile.index].cost);
//
//      if (cost > moves_left) {
//	cost = moves_left;
//      }
//      break;
//    }
//  default:
//    util.die("unknown TM");
//  }
//  return cost;
//}
//
//
///* ===================== Path-finding proper =================== */
//
///******************************************************************
//  Calculates cached values of the target node: 
//  node_known_type and zoc
//******************************************************************/
//static void init_node(pf_map pf_map, pf_node  node, 
//		      tile ptile)
//{
//  pf_parameter params = pf_map.params;
//
//  /* We will change the status of the tile once we have put
//   * sensible values into node.cost */
//
//  /* Establish the "known" status of node */
//  if (params.omniscience) {
//    node.node_known_type = TILE_KNOWN;
//  } else {
//    node.node_known_type = map_get_known(ptile, params.owner);
//  }
//
//  /* Establish the tile behavior */
//  if (params.get_TB) {
//    node.behavior = params.get_TB(ptile, node.node_known_type, params);
//  } else {
//    /* The default */
//    node.behavior = TB_NORMAL;
//  }
//
//  if (params.get_zoc) {
//    boolean my_zoc = (ptile.city || Terrain_H.is_ocean(ptile.terrain)
//		   || params.get_zoc(params.owner, ptile));
//    /* ZoC rules cannot prevent us from moving into/attacking an occupied 
//     * tile.  Other rules can, but we don't care about them here. */ 
//    boolean occupied = (ptile.units.foo_list_size() > 0
//                     || ptile.city);
//
//    /* 2 means can move unrestricted from/into it, 
//     * 1 means can move unrestricted into it, but not necessarily from it */
//    node.zoc_number = (my_zoc ? 2 : (occupied ? 1 : 0));
//  }
//
//  /* Evaluate the extra cost of the destination */
//  if (params.get_EC) {
//    node.extra_tile = params.get_EC(ptile, node.node_known_type, params);
//  }
//}
//
///*****************************************************************
//  Obtain cost-of-path from pure cost and extra cost
//*****************************************************************/
//static int get_total_CC(pf_map pf_map, int cost, int extra)
//{
//  return PF_TURN_FACTOR * cost + extra * pf_map.params.move_rate;
//}
//
///**************************************************************************
//  Bare-bones PF iterator.  All Freeciv rules logic is hidden in get_costs
//  callback (compare to pf_next function).
//  Plan: 1. Process previous position
//        2. Get new nearest position and return it
//**************************************************************************/
//static boolean jumbo_iterate_map(pf_map pf_map)
//{
//  pf_node node = &pf_map.lattice[pf_map.tile.index];
//  mapindex_t index;
//
//  pf_map.status[pf_map.tile.index] = NS_PROCESSED;
//
//  /* Processing Stage */
//  /* The previous position is contained in {x,y} fields of map */
//
//  adjc_dir_iterate(pf_map.tile, tile1, dir) {
//    pf_node node1 = &pf_map.lattice[tile1.index];
//    utiny_t *status = &pf_map.status[tile1.index];
//    int priority;    
//
//
//    if (*status == NS_PROCESSED) {
//      /* This gives 15% speedup */
//      continue;
//    }
//
//    if (*status == NS_UNINIT) {
//      node1.cost = -1;
//    }
//
//    /* User-supplied callback get_costs takes care of everything (ZOC, 
//     * known, costs etc).  See explanations in path_finding.h */
//    priority = pf_map.params.get_costs(pf_map.tile, dir, tile1, 
//					 node.cost, node.extra_cost,
//					 &node1.cost, &node1.extra_cost, 
//					 pf_map.params);
//    if (priority >= 0) {
//      /* We found a better route to xy1, record it 
//       * (the costs are recorded already) */
//      *status = NS_NEW;
//      node1.dir_to_here = dir;
//      pf_map.queue.pq_insert(tile1.index, -priority);
//    }
//
//  } adjc_dir_iterate_end;
//
//  /* Get the next nearest node */
//  for (;;) {
//    boolean removed = pf_map.queue.pq_remove(&index);
//
//    if (!removed) {
//      return false;
//    }
//    if (pf_map.status[index] == NS_NEW) {
//      break;
//    }
//    /* If the node has already been processed, get the next one. */
//  }
//
//  pf_map.tile = index_to_tile(index);
//
//  return true;
//}
//
///*****************************************************************
//  Primary method for iterative path-finding.
//  Plan: 1. Process previous position
//        2. Get new nearest position and return it
//*****************************************************************/
//boolean pf_next(pf_map pf_map)
//{
//  mapindex_t index;
//  pf_node node = &pf_map.lattice[pf_map.tile.index];
//
//  if (pf_map.params.is_pos_dangerous) {
//    /* It's a lot different if is_pos_dangerous is defined */
//    return danger_iterate_map(pf_map);
//  }
//
//  if (pf_map.params.get_costs) {
//    /* It is somewhat different when we have the jumbo callback */
//    return jumbo_iterate_map(pf_map);
//  }
//
//  pf_map.status[pf_map.tile.index] = NS_PROCESSED;
//
//  /* There is no exit from DONT_LEAVE tiles! */
//  if (node.behavior != TB_DONT_LEAVE) {
//
//    /* Processing Stage */
//    /* The previous position is contained in {x,y} fields of map */
//
//    adjc_dir_iterate(pf_map.tile, tile1, dir) {
//      mapindex_t index1 = tile1.index;
//      pf_node node1 = &pf_map.lattice[index1];
//      utiny_t *status = &pf_map.status[index1];
//      int cost;
//      int extra = 0;
//
//      if (*status == NS_PROCESSED) {
//	/* This gives 15% speedup */
//	continue;
//      }
//
//      if (*status == NS_UNINIT) {
//	init_node(pf_map, node1, tile1);
//      }
//
//      /* Can we enter this tile at all? */
//      if (node1.behavior == TB_IGNORE) {
//	continue;
//      }
//
//      /* Is the move ZOC-ok? */
//      if (pf_map.params.get_zoc
//	  && !(node.zoc_number > 1 || node1.zoc_number > 0)) {
//	continue;
//      }
//
//      /* Evaluate the cost of the move */
//      if (node1.node_known_type == TILE_UNKNOWN) {
//	cost = pf_map.params.unknown_MC;
//      } else {
//	cost = pf_map.params.get_MC(pf_map.tile, dir, tile1,
//				      pf_map.params);
//      }
//      if (cost == PF_IMPOSSIBLE_MC) {
//	continue;
//      }
//      cost = adjust_cost(pf_map, cost);
//      if (cost == PF_IMPOSSIBLE_MC) {
//	continue;
//      }
//
//      /* Total cost at xy1.  Cost may be negative; see get_turn(). */
//      cost += node.cost;
//
//      /* Evaluate the extra cost if it's relevant */
//      if (pf_map.params.get_EC) {
//        extra = node.extra_cost;
//	/* Add the cached value */
//	extra += node1.extra_tile;
//      }
//
//      /* Update costs and add to queue, if we found a better route to xy1. */
//      {
//	int cost_of_path = get_total_CC(pf_map, cost, extra);
//
//	if (*status == NS_UNINIT
//	    || cost_of_path < get_total_CC(pf_map, node1.cost,
//					   node1.extra_cost)) {
//	  *status = NS_NEW;
//	  node1.extra_cost = extra;
//	  node1.cost = cost;
//	  node1.dir_to_here = dir;
//	  pf_map.queue.pq_insert( index1, -cost_of_path);
//	}
//      }
//
//    } adjc_dir_iterate_end;
//  }
//
//  /* Get the next nearest node */
//  for (;;) {
//    boolean removed = pf_map.queue.pq_remove( &index);
//
//    if (!removed) {
//      return false;
//    }
//    if (pf_map.status[index] == NS_NEW) {
//      /* Discard if this node has already been processed */
//      break;
//    }
//  }
//
//  pf_map.tile = index_to_tile(index);
//
//  return true;
//}
//
///******************************************************************
//  Allocates the memory for the Map.map.  No initialization.
//******************************************************************/
//static pf_map create_map(boolean with_danger)
//{
//  pf_map pf_map = fc_calloc(1, sizeof(struct pf_map));
//
//  pf_map.lattice = fc_malloc(Map_H.MAX_MAP_INDEX * sizeof(struct pf_node));
//  pf_map.queue = Pqueue.pq_create(INITIAL_QUEUE_SIZE);
//  pf_map.status = fc_calloc(Map_H.MAX_MAP_INDEX, sizeof(*(pf_map.status)));
//
//  if (with_danger) {
//    /* Initialize stuff for dangerous positions.
//     * Otherwise they stay null */
//    pf_map.d_lattice = fc_calloc(Map_H.MAX_MAP_INDEX, sizeof(struct danger_node));
//    pf_map.danger_queue = Pqueue.pq_create(INITIAL_QUEUE_SIZE);
//  }
//
//  return pf_map;
//}
//
///***************************************************************
//  Sets up the map according to the parameters
//  Does not do any iterations
//***************************************************************/
//pf_map pf_create_map(final pf_parameter final parameter)
//{
//  pf_map pf_map = create_map((parameter.is_pos_dangerous != null));
//
//  /* MC callback must be set */
//  assert(parameter.get_MC != null);
//
//  /* Copy parameters */
//  pf_map.params = fc_malloc(sizeof(struct pf_parameter));
//  *pf_map.params = *parameter;
//
//  /* Initialise starting coordinates */
//  pf_map.tile = pf_map.params.start_tile;
//
//  /* Initialise starting node */
//  init_node(pf_map, &pf_map.lattice[pf_map.tile.index], pf_map.tile);
//  /* This makes calculations of turn/moves_left more convenient, but we 
//   * need to subtract this value before we return cost to the user.  Note
//   * that cost may be negative if moves_left_initially > move_rate
//   * (see get_turn()). */
//  pf_map.lattice[pf_map.tile.index].cost = pf_map.params.move_rate
//      - pf_map.params.moves_left_initially;
//  pf_map.lattice[pf_map.tile.index].extra_cost = 0;
//  pf_map.lattice[pf_map.tile.index].dir_to_here = -1;
//  if (pf_map.params.is_pos_dangerous) {
//    /* The starting point is safe */
//    pf_map.d_lattice[pf_map.tile.index].is_dangerous = false;
//  }
//
//  return pf_map;
//}
//
///*********************************************************************
//  After usage the map must be destroyed.
//*********************************************************************/
//void pf_destroy_map(pf_map pf_map)
//{
//  free(pf_map.lattice);
//  pf_map.queue.pq_destroy();
//  free(pf_map.status);
//
//  free(pf_map.params);
//
//  /* Danger-related structs */
//  if (pf_map.d_lattice) {
//    int i;
//
//    /* Need to clean up the dangling danger_sements */
//    for (i = 0; i < Map_H.MAX_MAP_INDEX; i++) {
//      if (pf_map.d_lattice[i].danger_segment) {
//	free(pf_map.d_lattice[i].danger_segment);
//      }
//    }
//    free(pf_map.d_lattice);
//  }
//  if (pf_map.danger_queue) {
//    pf_map.danger_queue.pq_destroy();
//  }
//
//  free(pf_map);
//}
//
//
///* =================== Lifting info from the map ================ */
//
///*******************************************************************
//  Fill in the position which must be discovered already. A helper 
//  for *_get_position functions.
//*******************************************************************/
//static void fill_position(final pf_map pf_map, tile ptile,
//			     pf_position pos)
//{
//  mapindex_t index = ptile.index;
//  pf_node node = &pf_map.lattice[index];
//
//  /* Debug period only!  Please remove after PF is settled */
//  if (pf_map.status[index] != NS_PROCESSED
//      && !Map.same_pos(ptile, pf_map.tile)) {
//    util.die("pf_finalruct_path to an unreached destination");
//    return;
//  }
//
//  pos.tile = ptile;
//  pos.total_EC = node.extra_cost;
//  pos.total_MC = node.cost - pf_map.params.move_rate
//    + pf_map.params.moves_left_initially;
//  if (pf_map.params.turn_mode == TM_BEST_TIME ||
//      pf_map.params.turn_mode == TM_WORST_TIME) {
//    pos.turn = get_turn(pf_map, node.cost);
//    pos.moves_left = get_moves_left(pf_map, node.cost);
//  } else if (pf_map.params.turn_mode == TM_NONE ||
//	     pf_map.params.turn_mode == TM_CAPPED) {
//    pos.turn = -1;
//    pos.moves_left = -1;
//  } else {
//    util.die("unknown TC");
//  }
//
//  pos.dir_to_here = node.dir_to_here;
//  /* This field does not apply */
//  pos.dir_to_next_pos = -1;
//}
//
///*******************************************************************
//  Read all info about the current position into pos
//*******************************************************************/
//void pf_next_get_position(final pf_map pf_map,
//			  pf_position pos)
//{
//  fill_position(pf_map, pf_map.tile, pos);
//}
//
///*******************************************************************
//  Get info about position at (x, y) and put it in pos.  If (x, y) 
//  has not been reached yet, iterate the map until we reach it.
//  Should _always_ check the return value, forthe position might be 
//  unreachable.
//*******************************************************************/
//boolean pf_get_position(pf_map pf_map, tile ptile,
//		     pf_position pos)
//{
//  mapindex_t index = ptile.index;
//  utiny_t status = pf_map.status[index];
//
//  if (status == NS_PROCESSED || Map.same_pos(ptile, pf_map.tile)) {
//    /* We already reached (x,y) */
//    fill_position(pf_map, ptile, pos);
//    return true;
//  }
//
//  while (pf_next(pf_map)) {
//    if (Map.same_pos(ptile, pf_map.tile)) {
//      /* That's the one */
//      fill_position(pf_map, ptile, pos);
//      return true;
//    }
//  }
//
//  return false;
//}
//
///*******************************************************************
//  Read off the path to the node (x,y), which must already be 
//  discovered.  A helper for *get_path functions.
//*******************************************************************/
//static struct pf_path* finalruct_path(final pf_map pf_map, 
//                                      tile dest_tile)
//{
//  int i;
//  int index = dest_tile.index;
//  enum direction8 dir_next;
//  pf_path path;
//  tile ptile;
//
//  /* Debug period only!  Please remove after PF is settled */
//  assert(!pf_map.params.is_pos_dangerous);
//  if (pf_map.status[index] != NS_PROCESSED
//      && !Map.same_pos(dest_tile, pf_map.tile)) {
//    util.die("finalruct_path to an unreached destination");
//    return null;
//  }
//
//  ptile = dest_tile;
//  path = fc_malloc(sizeof(*path));
//
//  /* 1: Count the number of steps to get here.
//   * To do it, backtrack until we hit the starting point */
//  for (i = 0; ; i++) {
//    pf_node node = &pf_map.lattice[ptile.index];
//
//    if (Map.same_pos(ptile, pf_map.params.start_tile)) {
//      /* Ah-ha, reached the starting point! */
//      break;
//    }
//
//    dir_next = node.dir_to_here;
//
//    ptile = mapstep(ptile, DIR_REVERSE(dir_next));
//  }
//
//  /* 2: Allocate the memory */
//  path.length = i + 1;
//  path.positions = fc_malloc((i+1) * sizeof(*(path.positions)));
//
//  /* 3: Backtrack again and fill the positions this time */
//  ptile = dest_tile;
//  dir_next = -1;
//  for (; i >=0; i--) {
//    pf_node node = &pf_map.lattice[ptile.index];
//
//    fill_position(pf_map, ptile, &path.positions[i]);
//    /* fill_position doesn't set direction */
//    path.positions[i].dir_to_next_pos = dir_next;
//
//    dir_next = node.dir_to_here;
//
//    if (i > 0) {
//      /* Step further back, if we haven't finished yet */
//      ptile = mapstep(ptile, DIR_REVERSE(dir_next));
//    }
//  }
//
//  return path;
//}
//
///************************************************************************
//  Get the path to our current position
//************************************************************************/
//pf_path pf_next_get_path(final pf_map pf_map)
//{
//  if (!pf_map.params.is_pos_dangerous) {
//    return finalruct_path(pf_map, pf_map.tile);
//  } else {
//    /* It's very different in the presence of danger */
//    return danger_finalruct_path(pf_map, pf_map.tile);
//  }
//}
//
///************************************************************************
//  Get the path to x, y, put it in "path".  If (x, y) has not been reached 
//  yet, iterate the map until we reach it or run out of Map.map.
//************************************************************************/
//pf_path pf_get_path(pf_map pf_map, tile ptile)
//{
//  mapindex_t index = ptile.index;
//  utiny_t status = pf_map.status[index];
//
//  if (pf_map.params.is_pos_dangerous) {
//    /* It's very different in the presence of danger */
//    return danger_get_path(pf_map, ptile);
//  }
//
//  if (status == NS_PROCESSED || Map.same_pos(ptile, pf_map.tile)) {
//    /* We already reached (x,y) */
//    return finalruct_path(pf_map, ptile);
//  }
//
//  while (pf_next(pf_map)) {
//    if (Map.same_pos(ptile, pf_map.tile)) {
//      /* That's the one */
//      return finalruct_path(pf_map, ptile);
//    }
//  }
//
//  return null;
//}
//
///******************************************************************
//  Get the last position of "path"
//******************************************************************/
//pf_position pf_last_position(pf_path path)
//{
//  return &path.positions[path.length - 1];
//}
//
///************************************************************************
//  Printing a path
//************************************************************************/
//void pf_print_path(int log_level, final pf_path path)
//{
//  int i;
//
//  if (path) {
//    util.freelog(log_level, "PF: path (at %p) consists of %d positions:", path,
//	    path.length);
//  } else {
//    util.freelog(log_level, "PF: path is null");
//    return;
//  }
//
//  for (i = 0; i < path.length; i++) {
//    util.freelog(log_level,
//	    "PF:   %2d/%2d: (%2d,%2d) dir=%-2s cost=%2d (%2d, %d) EC=%d",
//	    i + 1, path.length,
//	    path.positions[i].tile.x,
//	    path.positions[i].tile.y,
//	    dir_get_name(path.positions[i].dir_to_next_pos),
//	    path.positions[i].total_MC, path.positions[i].turn,
//	    path.positions[i].moves_left, path.positions[i].total_EC);
//  }
//}
//
///************************************************************************
//  After use, a path must be destroyed.
//************************************************************************/
//void pf_destroy_path(pf_path path)
//{
//  if (path) {
//    free(path.positions);
//    free(path);
//  }
//}
//
//
///* ================ Danger-related stuff ======================= */
//
///************************************************************************
//  Calculates cached danger-related values of the target node
//************************************************************************/
//static void init_danger_node(pf_map pf_map,
//			     danger_node d_node,
//			     pf_node node, tile ptile)
//{
//  pf_parameter params = pf_map.params;
//
//  /* Is the tile dangerous (i.e. no ending turn there) */
//  if (params.is_pos_dangerous) {
//    d_node.is_dangerous =
//	params.is_pos_dangerous(ptile, node.node_known_type, params);
//  } else {
//    util.freelog(Log.LOG_ERROR, "PF: init_danger_node called without" +
//	    "is_pos_dangerous callback");
//  }
//
//}
//
///***********************************************************************
//  Creating path segment going back from d_node1 to a safe tile.
//***********************************************************************/
//static void create_danger_segment(pf_map pf_map,
//                                  danger_node d_node1)
//{
//  int i;
//  tile ptile = pf_map.tile;
//  pf_node node = &pf_map.lattice[ptile.index];
//  danger_node d_node = &pf_map.d_lattice[ptile.index];
//  int length = 0;
//
//  /* Allocating memory */
//  if (d_node1.danger_segment) {
//    /* FIXME: it is probably a major bug that create_danger_segment gets
//     * called more than once per node.  Here we prevent a memory leak when
//     * it happens, but who knows what other problems it could cause?  See
//     * PR#10613. */
//    free(d_node1.danger_segment);
//  }
//
//  /* First iteration for determining segment length */
//  while(d_node.is_dangerous) {
//    length++;
//    ptile = mapstep(ptile, DIR_REVERSE(node.dir_to_here));
//    node = &pf_map.lattice[ptile.index];
//    d_node = &pf_map.d_lattice[ptile.index];
//  }
//
//  /* Allocate memory for segment */
//  d_node1.danger_segment = fc_malloc(length * sizeof(struct pf_danger_pos));
//
//  /* Reset tile and node pointers for main iteration */
//  ptile = pf_map.tile;
//  node = &pf_map.lattice[ptile.index];
//
//  /* Now fill the positions */
//  for (i = 0; i < length; i++) {
//    /* Record the direction */
//    d_node1.danger_segment[i].dir = node.dir_to_here;
//    d_node1.danger_segment[i].cost = node.cost;
//    d_node1.danger_segment[i].extra_cost = node.extra_cost;
//    if (i == length - 1) {
//      /* The last dangerous node contains "waiting" info */
//      d_node1.waited = pf_map.d_lattice[ptile.index].waited;
//    }
//
//    /* Step further down the tree */
//    ptile = mapstep(ptile, DIR_REVERSE(node.dir_to_here));
//    node = &pf_map.lattice[ptile.index];
//  }
//
//  /* Make sure we reached a safe node */
//  assert(!pf_map.d_lattice[ptile.index].is_dangerous);
//}
//
///**********************************************************************
//  Adjust cost taking into account possibility of making the move
//**********************************************************************/
//static int danger_adjust_cost(final pf_map pf_map, int cost, 
//                              boolean to_danger, int moves_left)
//{
//
//  if (cost == PF_IMPOSSIBLE_MC) {
//    return PF_IMPOSSIBLE_MC;
//  }
//
//  cost = Math.min(cost, pf_map.params.move_rate);
//
//  if (pf_map.params.turn_mode == TM_BEST_TIME) {
//    if (to_danger && cost >= moves_left) {
//      /* We would have to end the turn on a dangerous tile! */
//      return PF_IMPOSSIBLE_MC;
//    }
//  } else {
//    /* Default is TM_WORST_TIME.  
//     * It should be specified explicitly though! */
//    if (cost > moves_left
//        || (to_danger && cost == moves_left)) {
//      /* This move is impossible (at least without waiting) 
//       * or we would end our turn on a dangerous tile */
//      return PF_IMPOSSIBLE_MC;
//    }
//  }
//
//  return cost;
//}
//
///*****************************************************************
//  Primary method for iterative path-finding in presence of danger
//  Notes: 
//  1. Whenever the path-finding stumbles upon a dangerous 
//  location, it goes into a sub-Dijkstra which processes _only_ 
//  dangerous locations, by means of a separate queue.  When this
//  sub-Dijkstra reaches a safe location, it records the segment of
//  the path going across the dangerous terrain.
//  2. Waiting is realised by inserting the (safe) tile back into 
//  the queue with a lower priority P.  This tile might pop back 
//  sooner than P, because there might be several copies of it in 
//  the queue already.  But that does not seem to present any 
//  problems.
//  3. For some purposes, NS_WAITING is just another flavour of NS_PROCESSED,
//  since the path to a NS_WAITING tile has already been found.
//  4. The code is arranged so that if the turn-mode is TM_WORST_TIME, a 
//  cavalry with non-full MP will get to a safe mountain tile only after 
//  waiting.  This waiting, although realised through NS_WAITING, is 
//  different from waiting before going into the danger area, so it will not 
//  be marked as "waiting" on the resulting paths.
//  5. This algorithm cannot guarantee the best safe segments across 
//  dangerous region.  However it will find a safe segment if there 
//  is one.  To gurantee the best (in terms of total_CC) safe segments 
//  across danger, supply get_EC which returns small extra on 
//  dangerous tiles.
//******************************************************************/
//static boolean danger_iterate_map(pf_map pf_map)
//{
//  mapindex_t index;
//  pf_node node = &pf_map.lattice[pf_map.tile.index];
//  danger_node d_node = &pf_map.d_lattice[pf_map.tile.index];
//
//  /* There is no exit from DONT_LEAVE tiles! */
//  if (node.behavior != TB_DONT_LEAVE) {
//    /* Cost at xy but taking into account waiting */
//    int loc_cost
//	= (pf_map.status[pf_map.tile.index] != NS_WAITING ? node.cost
//	   : node.cost + get_moves_left(pf_map, node.cost));
//
//    /* The previous position is contained in {x,y} fields of map */
//    adjc_dir_iterate(pf_map.tile, tile1, dir) {
//      mapindex_t index1 = tile1.index;
//      pf_node node1 = &pf_map.lattice[index1];
//      danger_node d_node1 = &pf_map.d_lattice[index1];
//      int cost;
//      int extra = 0;
//
//      /* Dangerous tiles can be updated even after being processed */
//      if ((pf_map.status[index1] == NS_PROCESSED 
//           || pf_map.status[index1] == NS_WAITING) 
//          && !d_node1.is_dangerous) {
//	continue;
//      }
//
//      /* Initialise target tile if necessary */
//      if (pf_map.status[index1] == NS_UNINIT) {
//	init_node(pf_map, node1, tile1);
//	init_danger_node(pf_map, d_node1, node1, tile1);
//      }
//
//      /* Can we enter this tile at all? */
//      if (node1.behavior == TB_IGNORE) {
//	continue;
//      }
//
//      /* Is the move ZOC-ok? */
//      if (pf_map.params.get_zoc
//	  && !(node.zoc_number > 1 || node1.zoc_number > 0)) {
//	continue;
//      }
//
//      /* Evaluate the cost of the move */
//      if (node1.node_known_type == TILE_UNKNOWN) {
//	cost = pf_map.params.unknown_MC;
//      } else {
//	cost = pf_map.params.get_MC(pf_map.tile, dir, tile1,
//				      pf_map.params);
//      }
//      if (cost == PF_IMPOSSIBLE_MC) {
//	continue;
//      }
//      cost = danger_adjust_cost(pf_map, cost, d_node1.is_dangerous,
//				get_moves_left(pf_map, loc_cost));
//
//      if (cost == PF_IMPOSSIBLE_MC) {
//	/* This move is deemed impossible */
//	continue;
//      }
//
//      /* Total cost at xy1 */
//      cost += loc_cost;
//
//      /* Evaluate the extra cost of the destination, if it's relevant */
//      if (pf_map.params.get_EC) {
//	extra = node1.extra_tile + node.extra_cost;
//      }
//
//      /* Update costs and add to queue, if this is a better route to xy1 */
//      if (!d_node1.is_dangerous) {
//	int cost_of_path = get_total_CC(pf_map, cost, extra);
//
//	if (pf_map.status[index1] == NS_UNINIT
//	    || (cost_of_path
//		< get_total_CC(pf_map, node1.cost, node1.extra_cost))) {
//	  node1.extra_cost = extra;
//	  node1.cost = cost;
//	  node1.dir_to_here = dir;
//	  if (d_node.is_dangerous) {
//	    /* Transition from red to blue, need to record the path back */
//	    create_danger_segment(pf_map, d_node1);
//	  } else {
//	    /* Clear the path back */
//	    if (d_node1.danger_segment) {
//	      free(d_node1.danger_segment);
//	      d_node1.danger_segment = null;
//	    }
//            /* We don't consider waiting to get to a safe tile as 
//             * "real" waiting */
//	    d_node1.waited = false;
//	  }
//	  pf_map.status[index1] = NS_NEW;
//	  pf_map.queue.pq_insert( index1, -cost_of_path);
//	}
//      } else {
//	/* The procedure is slightly different for dangerous nodes */
//	/* We will update costs if:
//	 * 1: we are here for the first time
//	 * 2: we can possibly go further across dangerous area or
//	 * 3: we can have lower extra and will not 
//	 *    overwrite anything useful */
//	if (pf_map.status[index1] == NS_UNINIT
//	    || (get_moves_left(pf_map, cost)
//		> get_moves_left(pf_map, node1.cost))
//	    || (get_total_CC(pf_map, cost, extra)
//		< get_total_CC(pf_map, node1.cost, node1.extra_cost)
//		&& pf_map.status[index1] == NS_PROCESSED)) {
//	  node1.extra_cost = extra;
//	  node1.cost = cost;
//	  node1.dir_to_here = dir;
//	  pf_map.status[index1] = NS_NEW;
//	  d_node1.waited = (pf_map.status[pf_map.tile.index]
//			     == NS_WAITING);
//	  /* Extra costs of all nodes in danger_queue are equal! */
//	  pf_map.danger_queue.pq_insert( index1, -cost);
//	}
//      }
//    }
//    adjc_dir_iterate_end;
//  }
//
//  if (!d_node.is_dangerous
//      && pf_map.status[pf_map.tile.index] != NS_WAITING
//      && (get_moves_left(pf_map, node.cost)
//	  < pf_map.params.move_rate)) {
//    /* Consider waiting at this node. 
//     * To do it, put it back into queue. */
//    pf_map.status[pf_map.tile.index] = NS_WAITING;
//    pf_map.queue.pq_insert( pf_map.tile.index,
//	      -get_total_CC(pf_map, get_moves_left(pf_map, node.cost)
//			    + node.cost, node.extra_cost));
//  } else {
//    pf_map.status[pf_map.tile.index] = NS_PROCESSED;
//  }
//
//  /* Get the next nearest node */
//
//  /* First try to get it from danger_queue */
//  if (!pf_map.danger_queue.pq_remove( &index)) {
//    /* No dangerous nodes to process, go for a safe one */
//    do {
//      if (!pf_map.queue.pq_remove( &index)) {
//	return false;
//      }
//    } while (pf_map.status[index] == NS_PROCESSED);
//  }
//
//  assert(pf_map.status[pf_map.tile.index] != NS_UNINIT);
//
//  pf_map.tile = index_to_tile(index);
//
//  if (pf_map.status[pf_map.tile.index] == NS_WAITING) {
//    /* We've already returned this node once, skip it */
//    util.freelog(Log.LOG_DEBUG, "Considering waiting at (%d, %d)",
//	    pf_map.tile.x, pf_map.tile.y);
//    return danger_iterate_map(pf_map);
//  } else if (pf_map.d_lattice[index].is_dangerous) {
//    /* We don't return dangerous tiles */
//    util.freelog(Log.LOG_DEBUG, "Reached dangerous tile (%d, %d)",
//	    pf_map.tile.x, pf_map.tile.y);
//    return danger_iterate_map(pf_map);
//  } else {
//    /* Just return it */
//    return true;
//  }
//}
//
///*******************************************************************
//  Read off the path to the node (x, y), but with danger
//  NB: will only find paths to safe tiles!
//*******************************************************************/
//static pf_path danger_finalruct_path(final pf_map pf_map,
//                                             tile ptile)
//{
//  pf_path path = fc_malloc(sizeof(*path));
//  int i;
//  enum direction8 dir_next = -1;
//  pf_danger_pos danger_seg = null;	/* For danger segments */
//  int segment_index = -1;                       /* For danger segments */
//  boolean waited = false;
//  pf_node node = &pf_map.lattice[ptile.index];
//  danger_node d_node = &pf_map.d_lattice[ptile.index];
//  int length = 1;
//  tile iter_tile = ptile;
//
//  if (pf_map.params.turn_mode != TM_BEST_TIME &&
//      pf_map.params.turn_mode != TM_WORST_TIME) {
//    util.die("illegal TM in path-finding with danger");
//    return null;
//  }
//
//  /* First iterate to find path length */
//  while(!Map.same_pos(iter_tile, pf_map.params.start_tile)) {
//
//    if (!d_node.is_dangerous && d_node.waited) {
//      length += 2;
//    } else {
//      length++;
//    }
//
//    if (!d_node.is_dangerous) {
//      /* We are in the normal node and dir_to_here field is valid */
//      dir_next = node.dir_to_here;
//      /* d_node.danger_segment is the indicator of what lies ahead
//       * if it's non-null, we are entering a danger segment, 
//       * if it's null, we are not on one so danger_seg should be null */
//      danger_seg = d_node.danger_segment;
//      segment_index = 0;
//    } else {
//      /* We are in a danger segment */
//      dir_next = danger_seg[segment_index].dir;
//      segment_index++;
//    }
//
//    /* Step backward */
//    iter_tile = mapstep(iter_tile, DIR_REVERSE(dir_next));
//    node = &pf_map.lattice[iter_tile.index];
//    d_node = &pf_map.d_lattice[iter_tile.index];
//  }
//
//  /* Allocate memory for path */
//  path.positions 
//    = fc_malloc(length * sizeof(struct pf_position));
//  path.length = length;
//
//  /* Reset variables for main iteration */
//  iter_tile = ptile;
//  node = &pf_map.lattice[ptile.index];
//  d_node = &pf_map.d_lattice[ptile.index];
//  waited = false;
//
//  for (i = length - 1; i >= 0; i--) {
//    boolean old_waited = false;
//
//    /* 1: Deal with waiting */
//    if (!d_node.is_dangerous) {
//      if (waited) {
//        /* Waited at _this_ tile, need to record it twice in the path.
//         * Here we record our state _after_ waiting (e.g. full move points) */
//        path.positions[i].tile = iter_tile;
//        path.positions[i].total_EC = node.extra_cost;
//        path.positions[i].turn = get_turn(pf_map, node.cost) + 1;
//        path.positions[i].moves_left = pf_map.params.move_rate;
//        path.positions[i].total_MC 
//          = ((path.positions[i].turn - 1) * pf_map.params.move_rate
//             + pf_map.params.moves_left_initially);
//        path.positions[i].dir_to_next_pos = dir_next;
//        /* Set old_waited so that we record -1 as a direction at the step 
//         * we were going to wait */
//        old_waited = true;
//        i--;
//      }
//      /* Update "waited" (d_node.waited means "waited to get here") */
//      waited = d_node.waited;
//    }
//
//    /* 2: Fill the current position */
//    path.positions[i].tile = iter_tile;
//    if (!d_node.is_dangerous) {
//      path.positions[i].total_MC = node.cost;
//      path.positions[i].total_EC = node.extra_cost;
//    } else {
//      /* When on dangerous tiles, must have a valid danger segment */
//      assert(danger_seg != null);
//      path.positions[i].total_MC = danger_seg[segment_index].cost;
//      path.positions[i].total_EC = danger_seg[segment_index].extra_cost;
//    } 
//    path.positions[i].turn = get_turn(pf_map, path.positions[i].total_MC);
//    path.positions[i].moves_left 
//      = get_moves_left(pf_map, path.positions[i].total_MC);
//    path.positions[i].total_MC -= pf_map.params.move_rate
//      - pf_map.params.moves_left_initially;
//    path.positions[i].dir_to_next_pos = (old_waited ? -1 : dir_next);
//
//    /* 3: Check if we finished */
//    if (i == 0) {
//      /* We should be back at the start now! */
//      assert(Map.same_pos(iter_tile, pf_map.params.start_tile));
//      return path;
//    }
//
//    /* 4: Calculate the next direction */
//    if (!d_node.is_dangerous) {
//      /* We are in the normal node and dir_to_here field is valid */
//      dir_next = node.dir_to_here;
//      /* d_node.danger_segment is the indicator of what lies ahead
//       * if it's non-null, we are entering a danger segment, 
//       * if it's null, we are not on one so danger_seg should be null */
//      danger_seg = d_node.danger_segment;
//      segment_index = 0;
//    } else {
//      /* We are in a danger segment */
//      dir_next = danger_seg[segment_index].dir;
//      segment_index++;
//    }
//
//    /* 5: Step further back */
//    iter_tile = mapstep(iter_tile, DIR_REVERSE(dir_next));
//    node = &pf_map.lattice[iter_tile.index];
//    d_node = &pf_map.d_lattice[iter_tile.index];
//
//  }
//
//  util.die("danger_get_path: cannot get to the starting point!");
//  return null;
//}
//
///************************************************************************
//  Danger version of pf_get_path.
//************************************************************************/
//static pf_path danger_get_path(pf_map pf_map,
//				       tile ptile)
//{
//  mapindex_t index = ptile.index;
//  utiny_t status = pf_map.status[index];
//  danger_node d_node = &pf_map.d_lattice[index];
//
//  if (d_node.is_dangerous) {
//    /* "Best" path to a dangerous tile is undefined */
//    /* TODO: return the "safest" path */
//    return null;
//  }
//
//  if (status == NS_PROCESSED || status == NS_WAITING 
//      || Map.same_pos(ptile, pf_map.tile)) {
//    /* We already reached (x,y) */
//    return danger_finalruct_path(pf_map, ptile);
//  }
//
//  while (pf_next(pf_map)) {
//    if (Map.same_pos(ptile, pf_map.tile)) {
//      /* That's the one */
//      return danger_finalruct_path(pf_map, ptile);
//    }
//  }
//
//  return null;
//}
//
///************************************************************************
//  Return current pf_parameter for given pf_map.
//************************************************************************/
//pf_parameter pf_get_parameter(pf_map map)
//{
//  return Map.map.params;
//}
}