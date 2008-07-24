package common;

public class Terrain{
//#include "mem.h"		/* free */
//#include "rand.h"
//#include "shared.h"
//#include "support.h"
//#include "terrain.h"
//
//static struct tile_type tile_types[MAX_NUM_TERRAINS];
//
///***************************************************************
//...
//***************************************************************/
//tile_type get_tile_type(int type)
//{
//  if (type < 0 || type >= T_COUNT) {
//    /* HACK: return a dummy tile for out-of-range requests.  This is
//     * designed specifically to fix this problem in 2.0. */
//    static struct tile_type t_void;
//
//#if 0 /* Currently this assertion triggers all the time. */
//    assert(type >= 0 && type < T_COUNT);
//#endif
//    return &t_void;
//  }
//  return &tile_types[type];
//}
//
///****************************************************************************
//  Return the terrain type matching the name, or T_UNKNOWN if none matches.
//****************************************************************************/
//int get_terrain_by_name(final String name)
//{
//  int tt;
//
//  for (tt = T_FIRST; tt < T_COUNT; tt++) {
//    if (0 == strcmp (tile_types[tt].terrain_name, name)) {
//      return tt;
//    }
//  }
//
//  return T_UNKNOWN;
//}
//
///***************************************************************
//...
//***************************************************************/
//final String get_terrain_name(int type)
//{
//  return get_tile_type(type).terrain_name;
//}
//
///****************************************************************************
//  Return the terrain flag matching the given string, or TER_LAST if there's
//  no match.
//****************************************************************************/
//enum terrain_flag_id terrain_flag_from_str(final String s)
//{
//  enum terrain_flag_id flag;
//  final String flag_names[] = {
//    /* Must match terrain flags in terrain.h. */
//    "NoBarbs",
//    "NoPollution",
//    "NoCities",
//    "Starter",
//    "CanHaveRiver",
//    "UnsafeCoast",
//    "Unsafe",
//    "Oceanic"
//  };
//
//  assert(ARRAY_SIZE(flag_names) == TER_COUNT);
//
//  for (flag = TER_FIRST; flag < TER_LAST; flag++) {
//    if (mystrcasecmp(flag_names[flag], s) == 0) {
//      return flag;
//    }
//  }
//
//  return TER_LAST;
//}
//
///****************************************************************************
//  Return a random terrain that has the specified flag.
//****************************************************************************/
//int get_flag_terrain(enum terrain_flag_id flag)
//{
//  boolean has_flag[T_COUNT];
//  int count = 0;
//
//  terrain_type_iterate(t) {
//    if ((has_flag[t] = Terrain_H.terrain_has_flag(t, flag))) {
//      count++;
//    }
//  } terrain_type_iterate_end;
//
//  count = Rand.myrand(count);
//  terrain_type_iterate(t) {
//    if (has_flag[t]) {
//      if (count == 0) {
//       return t;
//      }
//      count--;
//    }
//  } terrain_type_iterate_end;
//
//  util.die("Reached end of get_flag_terrain!");
//  return T_NONE;
//}
//
///****************************************************************************
//  Free memory which is associated with terrain types.
//****************************************************************************/
//void tile_types_free()
//{
//  terrain_type_iterate(t) {
//    tile_type p = get_tile_type(t);
//
//    free(p.helptext);
//    p.helptext = null;
//  } terrain_type_iterate_end;
//}
//
///****************************************************************************
//  This iterator behaves like adjc_iterate or cardinal_adjc_iterate depending
//  on the value of card_only.
//****************************************************************************/
//#define variable_adjc_iterate(center_tile, itr_tile, card_only)		    \
//{									    \
//  enum direction8 *_dirlist;						    \
//  int _total;								    \
//  									    \
//  if (card_only) {							    \
//    _dirlist = Map.map.cardinal_dirs;					    \
//    _total = Map.map.num_cardinal_dirs;					    \
//  } else {								    \
//    _dirlist = Map.map.valid_dirs;						    \
//    _total = Map.map.num_valid_dirs;					    \
//  }									    \
//  									    \
//  adjc_dirlist_iterate(center_tile, itr_tile, _dir, _dirlist, _total) {
//
//#define variable_adjc_iterate_end		                            \
//  } adjc_dirlist_iterate_end;						    \
//}
//
//
///****************************************************************************
//  Returns true iff any adjacent tile contains the given terrain.
//****************************************************************************/
//boolean is_terrain_near_tile(final tile ptile, int t)
//{
//  for(tile adjc_tile: util.adjc_tile_iterate(ptile)) {
//    if (adjc_tile.terrain == t) {
//      return true;
//    }
//  }
//
//  return false;
//}
//
///****************************************************************************
//  Return the number of adjacent tiles that have the given terrain.
//****************************************************************************/
//int count_terrain_near_tile(final tile ptile,
//			    boolean cardinal_only, boolean percentage,
//			    int t)
//{
//  int count = 0, total = 0;
//
//  variable_adjc_iterate(ptile, adjc_tile, cardinal_only) {
//    if (adjc_tile.terrain == t) {
//      count++;
//    }
//    total++;
//  } variable_adjc_iterate_end;
//
//  if (percentage) {
//    count = count * 100 / total;
//  }
//  return count;
//}
//
///****************************************************************************
//  Returns true iff any tile adjacent to (map_x,map_y) has the given special.
//****************************************************************************/
//boolean is_special_near_tile(final tile ptile, enum int spe)
//{
//  for(tile adjc_tile: util.adjc_tile_iterate(ptile)) {
//    if (Map.map_has_special(adjc_tile, spe)) {
//      return true;
//    }
//  }
//
//  return false;
//}
//
///****************************************************************************
//  Returns the number of adjacent tiles that have the given map special.
//****************************************************************************/
//int count_special_near_tile(final tile ptile,
//			    boolean cardinal_only, boolean percentage,
//			    enum int spe)
//{
//  int count = 0, total = 0;
//
//  variable_adjc_iterate(ptile, adjc_tile, cardinal_only) {
//    if (Map.map_has_special(adjc_tile, spe)) {
//      count++;
//    }
//    total++;
//  } variable_adjc_iterate_end;
//
//  if (percentage) {
//    count = count * 100 / total;
//  }
//  return count;
//}
//
///****************************************************************************
//  Returns true iff any adjacent tile contains terrain with the given flag.
//****************************************************************************/
//boolean is_terrain_flag_near_tile(final tile ptile,
//			       enum terrain_flag_id flag)
//{
//  for(tile adjc_tile: util.adjc_tile_iterate(ptile)) {
//    if (Terrain_H.terrain_has_flag(adjc_tile.terrain, flag)) {
//      return true;
//    }
//  }
//
//  return false;
//}
//
///****************************************************************************
//  Return the number of adjacent tiles that have terrain with the given flag.
//****************************************************************************/
//int count_terrain_flag_near_tile(final tile ptile,
//				 boolean cardinal_only, boolean percentage,
//				 enum terrain_flag_id flag)
//{
//  int count = 0, total = 0;
//
//  variable_adjc_iterate(ptile, adjc_tile, cardinal_only) {
//    if (Terrain_H.terrain_has_flag(adjc_tile.terrain, flag)) {
//      count++;
//    }
//    total++;
//  } variable_adjc_iterate_end;
//
//  if (percentage) {
//    count = count * 100 / total;
//  }
//  return count;
//}
}