package common;
import static common.map.Map_H.*;
import static utility.shared.Shared_H.*;
import utility.Rand;

import common.city.city;
import common.map.Point;
import common.map.civ_map;
import common.map.tile;

public class Map{
//#include "city.h"
//#include "fcintl.h"
//#include "log.h"
//#include "mem.h"
//#include "packets.h"
//#include "rand.h"
//#include "shared.h"
//#include "support.h"
//#include "unit.h"
//
//#include "Map.map.h"

/* the very map */
	public static civ_map map;
//
///* these are initialized from the terrain ruleset */
//struct terrain_misc terrain_control;
//
///* used to compute neighboring tiles.
// *
// * using
// *   x1 = x + DIR_DX[dir];
// *   y1 = y + DIR_DY[dir];
// * will give you the tile as shown below.
// *   -------
// *   |0|1|2|
// *   |-+-+-|
// *   |3| |4|
// *   |-+-+-|
// *   |5|6|7|
// *   -------
// * Note that you must normalize x1 and y1 yourself.
// */
//final int DIR_DX[8] = { -1, 0, 1, -1, 1, -1, 0, 1 };
//final int DIR_DY[8] = { -1, -1, -1, 0, 0, 1, 1, 1 };
//
///* Names of specials.
// * (These must correspond to enum int in terrain.h.)
// */
//static final String int_names[] =
//{
//  N"Special1",
//  N"Road",
//  N"Irrigation",
//  N"Railroad",
//  N"Mine",
//  N"Pollution",
//  N"Hut",
//  N"Fortress",
//  N"Special2",
//  N"River",
//  N"Farmland",
//  N"Airbase",
//  N"Fallout"
//};
//
///****************************************************************************
//  Return a bitfield of the specials on the tile that are infrastructure.
//****************************************************************************/
//enum int get_tile_infrastructure_set(final tile ptile)
//{
//  return (ptile.special
//	  & (Terrain_H.S_ROAD | Terrain_H.S_RAILROAD | S_IRRIGATION | S_FARMLAND | S_MINE
//	     | S_FORTRESS | Terrain_H.S_AIRBASE));
//}
//
///***************************************************************
//  Return a (static) string with terrain name;
//  eg: "Hills"
//  eg: "Hills (Coals)"
//  eg: "Hills (Coals) [Pollution]"
//***************************************************************/
//final String map_get_tile_info_text(final tile ptile)
//{
//  static char s[64];
//  boolean first;
//  tile_type ptype = get_tile_type(ptile.terrain);
//
//  s = ptype.terrain_name;
//  if (tile_has_special(ptile, S_RIVER)) {
//    sz_strlcat(s, "/");
//    sz_strlcat(s, get_special_name(S_RIVER));
//  }
//
//  first = true;
//  if (tile_has_special(ptile, S_SPECIAL_1)) {
//    if (first) {
//      first = false;
//      sz_strlcat(s, " (");
//    } else {
//      sz_strlcat(s, "/");
//    }
//    sz_strlcat(s, ptype.special_1_name);
//  }
//  if (tile_has_special(ptile, S_SPECIAL_2)) {
//    if (first) {
//      first = false;
//      sz_strlcat(s, " (");
//    } else {
//      sz_strlcat(s, "/");
//    }
//    sz_strlcat(s, ptype.special_2_name);
//  }
//  if (!first) {
//    sz_strlcat(s, ")");
//  }
//
//  first = true;
//  if (tile_has_special(ptile, S_POLLUTION)) {
//    if (first) {
//      first = false;
//      sz_strlcat(s, " [");
//    } else {
//      sz_strlcat(s, "/");
//    }
//    sz_strlcat(s, get_special_name(S_POLLUTION));
//  }
//  if (tile_has_special(ptile, S_FALLOUT)) {
//    if (first) {
//      first = false;
//      sz_strlcat(s, " [");
//    } else {
//      sz_strlcat(s, "/");
//    }
//    sz_strlcat(s, get_special_name(S_FALLOUT));
//  }
//  if (!first) {
//    sz_strlcat(s, "]");
//  }
//
//  return s;
//}
//
///***************************************************************
//  Returns 1 if we are at a stage of the Game.game where the map
//  has not yet been generated/loaded.
//  (To be precise, returns 1 if map_allocate() has not yet been
//  called.)
//***************************************************************/
//boolean map_is_empty()
//{
//  return !Map.map.tiles;
//}
//
///***************************************************************
// put some sensible values into the map structure
//***************************************************************/
//void map_init()
//{
//  Map.map.topology_id = MAP_DEFAULT_TOPO;
//  Map.map.size = MAP_DEFAULT_SIZE;
//
//  /* The [xy]size values are set in map_init_topology.  It is initialized
//   * to a non-zero value because some places erronously use these values
//   * before they're initialized. */
//  Map.map.xsize = MAP_MIN_LINEAR_SIZE;  
//  Map.map.ysize = MAP_MIN_LINEAR_SIZE;
//
//  Map.map.seed = MAP_DEFAULT_SEED;
//  Map.map.riches                = MAP_DEFAULT_RICHES;
//  Map.map.huts                  = MAP_DEFAULT_HUTS;
//  Map.map.landpercent           = MAP_DEFAULT_LANDMASS;
//  Map.map.wetness               = MAP_DEFAULT_WETNESS;
//  Map.map.steepness             = MAP_DEFAULT_STEEPNESS;
//  Map.map.generator             = MAP_DEFAULT_GENERATOR;
//  Map.map.startpos              = MAP_DEFAULT_STARTPOS;
//  Map.map.tinyisles             = MAP_DEFAULT_TINYISLES;
//  Map.map.separatepoles         = MAP_DEFAULT_SEPARATE_POLES;
//  Map.map.alltemperate          = MAP_DEFAULT_ALLTEMPERATE;
//  Map.map.temperature           = MAP_DEFAULT_TEMPERATURE;
//  Map.map.tiles                 = null;
//  Map.map.num_continents        = 0;
//  Map.map.num_oceans            = 0;
//  Map.map.num_start_positions   = 0;
//  Map.map.have_specials         = false;
//  Map.map.have_rivers_overlay   = false;
//  Map.map.have_huts             = false;
//}
//
///**************************************************************************
//  Fill the iterate_outwards_indices array.  This may depend on the topology.
//***************************************************************************/
//static void generate_map_indices()
//{
//  int i = 0, nat_x, nat_y, tiles;
//  iter_index array = Map.map.iterate_outwards_indices;
//  int nat_center_x, nat_center_y, nat_min_x, nat_min_y, nat_max_x, nat_max_y;
//  int map_center_x, map_center_y;
//
//  /* These caluclations are done via tricky native math.  We need to make
//   * sure that when "exploring" positions in the iterate_outward we hit each
//   * position within the distance exactly once.
//   *
//   * To do this we pick a center position (at the center of the map, for
//   * convenience).  Then we iterate over all of the positions around it,
//   * accounting for wrapping, in native coordinates.  Note that some of the
//   * positions iterated over before will not even be real; the point is to
//   * use the native math so as to get the right behavior under different
//   * wrapping conditions.
//   *
//   * Thus the "center" position below is just an arbitrary point.  We choose
//   * the center of the map to make the min/max values (below) simpler. */
//  nat_center_x = Map.map.xsize / 2;
//  nat_center_y = Map.map.ysize / 2;
//  NATIVE_TO_MAP_POS(&map_center_x, &map_center_y,
//		    nat_center_x, nat_center_y);
//
//  /* If we wrap in a particular direction (X or Y) we only need to explore a
//   * half of a map-width in that direction before we hit the wrap point.  If
//   * not we need to explore the full width since we have to account for the
//   * worst-case where we start at one edge of the Map.map.  Of course if we try
//   * to explore too far the extra steps will just be skipped by the
//   * normalize check later on.  So the purpose at this point is just to
//   * get the right set of positions, relative to the start position, that
//   * may be needed for the iteration.
//   *
//   * If the map does wrap, we go Map.map.Nsize / 2 in each direction.  This
//   * gives a min value of 0 and a max value of Nsize-1, because of the
//   * center position chosen above.  This also avoids any off-by-one errors.
//   *
//   * If the map doesn't wrap, we go Map.map.Nsize-1 in each direction.  In this
//   * case we're not concerned with going too far and wrapping around, so we
//   * just have to make sure we go far enough if we're at one edge of the
//   * Map.map. */
//  nat_min_x = (topo_has_flag(TF_WRAPX) ? 0 : (nat_center_x - Map.map.xsize + 1));
//  nat_min_y = (topo_has_flag(TF_WRAPY) ? 0 : (nat_center_y - Map.map.ysize + 1));
//
//  nat_max_x = (topo_has_flag(TF_WRAPX)
//	       ? (Map.map.xsize - 1)
//	       : (nat_center_x + Map.map.xsize - 1));
//  nat_max_y = (topo_has_flag(TF_WRAPY)
//	       ? (Map.map.ysize - 1)
//	       : (nat_center_y + Map.map.ysize - 1));
//  tiles = (nat_max_x - nat_min_x + 1) * (nat_max_y - nat_min_y + 1);
//
//  array = fc_realloc(array, tiles * sizeof(*array));
//
//  for (nat_x = nat_min_x; nat_x <= nat_max_x; nat_x++) {
//    for (nat_y = nat_min_y; nat_y <= nat_max_y; nat_y++) {
//      int map_x, map_y, dx, dy;
//
//      /* Now for each position, we find the vector (in map coordinates) from
//       * the center position to that position.  Then we calculate the
//       * distance between the two points.  Wrapping is ignored at this
//       * point since the use of native positions means we should always have
//       * the shortest vector. */
//      NATIVE_TO_MAP_POS(&map_x, &map_y, nat_x, nat_y);
//      dx = map_x - map_center_x;
//      dy = map_y - map_center_y;
//
//      array[i].dx = dx;
//      array[i].dy = dy;
//      array[i].dist = map_vector_to_real_distance(dx, dy);
//      i++;
//    }
//  }
//  assert(i == tiles);
//
//  qsort(array, tiles, sizeof(*array), compare_iter_index);
//
//#if 0
//  for (i = 0; i < tiles; i++) {
//    util.freelog(Log.LOG_DEBUG, "%5d : (%3d,%3d) : %d",
//	    i, array[i].dx, array[i].dy, array[i].dist);
//  }
//#endif
//
//  Map.map.num_iterate_outwards_indices = tiles;
//  Map.map.iterate_outwards_indices = array;
//}
//
///****************************************************************************
//  map_init_topology needs to be called after Map.map.topology_id is changed.
//
//  If Map.map.size is changed, Map.map.xsize and Map.map.ysize must be set before
//  calling map_init_topology(true).  This is done by the mapgen code
//  (server) and packhand code (client).
//
//  If Map.map.xsize and Map.map.ysize are changed, call map_init_topology(false) to
//  calculate Map.map.size.  This should be done in the client or when loading
//  savegames, since the [xy]size values are already known.
//****************************************************************************/
//void map_init_topology(boolean set_sizes)
//{
//  enum direction8 dir;
//
//  if (!set_sizes) {
//    /* Set Map.map.size based on Map.map.xsize and Map.map.ysize. */
//    Map.map.size = (float)(Map.map.xsize * Map.map.ysize) / 1000.0 + 0.5;
//  }
//  
//  /* sanity check for iso topologies*/
//  assert(!MAP_IS_ISOMETRIC || (Map.map.ysize % 2) == 0);
//
//  /* The size and ratio must satisfy the minimum and maximum *linear*
//   * restrictions on width */
//  assert(MAP_WIDTH >= MAP_MIN_LINEAR_SIZE);
//  assert(MAP_HEIGHT >= MAP_MIN_LINEAR_SIZE);
//  assert(MAP_WIDTH <= MAP_MAX_LINEAR_SIZE);
//  assert(MAP_HEIGHT <= MAP_MAX_LINEAR_SIZE);
//
//  Map.map.num_valid_dirs = Map.map.num_cardinal_dirs = 0;
//  for (dir = 0; dir < 8; dir++) {
//    if (is_valid_dir(dir)) {
//      Map.map.valid_dirs[Map.map.num_valid_dirs] = dir;
//      Map.map.num_valid_dirs++;
//    }
//    if (is_cardinal_dir(dir)) {
//      Map.map.cardinal_dirs[Map.map.num_cardinal_dirs] = dir;
//      Map.map.num_cardinal_dirs++;
//    }
//  }
//  assert(Map.map.num_valid_dirs > 0 && Map.map.num_valid_dirs <= 8);
//  assert(Map.map.num_cardinal_dirs > 0
//	 && Map.map.num_cardinal_dirs <= Map.map.num_valid_dirs);
//}
//
///***************************************************************
//...
//***************************************************************/
//static void tile_init(tile ptile)
//{
//  ptile.terrain  = T_UNKNOWN;
//  ptile.special  = S_NO_SPECIAL;
//  ptile.known    = 0;
//  ptile.continent = 0;
//  ptile.city     = null;
//  unit_list_init(&ptile.units);
//  ptile.worked   = null; /* pointer to city working tile */
//  ptile.assigned = 0; /* bitvector */
//  ptile.owner    = null; /* Tile not claimed by any nation. */
//  ptile.client.hilite = HILITE_NONE; /* Area Selection in client. */
//  ptile.spec_sprite = null;
//}
//
///****************************************************************************
//  Step from the given tile in the given direction.  The new tile is returned,
//  or null if the direction is invalid or leads off the Map.map.
//****************************************************************************/
//tile mapstep(final tile ptile, enum direction8 dir)
//{
//  int x, y;
//
//  if (!is_valid_dir(dir)) {
//    return null;
//  }
//
//  DIRSTEP(x, y, dir);
//  x += ptile.x;
//  y += ptile.y;
//
//  return map_pos_to_tile(x, y);
//}

	/***************************************************************************
	 * Return the tile for the given native position, with wrapping.
	 * 
	 * This is a backend function used by map_pos_to_tile and
	 * native_pos_to_tile. It is called extremely often so it is made inline.
	 **************************************************************************/
	static tile base_native_pos_to_tile(int nat_x, int nat_y)
	{
		/*
		 * If the position is out of range in a non-wrapping direction, it is
		 * unreal.
		 */
		if (!((topo_has_flag(TF_WRAPX) || (nat_x >= 0 && nat_x < Map.map.xsize))
				&& (topo_has_flag(TF_WRAPY) || (nat_y >= 0 && nat_y < Map.map.ysize)))) {
			return null;
		}

		/* Wrap in X and Y directions, as needed. */
		if (topo_has_flag(TF_WRAPX)) {
			nat_x = FC_WRAP(nat_x, Map.map.xsize);
		}
		if (topo_has_flag(TF_WRAPY)) {
			nat_y = FC_WRAP(nat_y, Map.map.ysize);
		}

		return Map.map.tiles[ native_pos_to_index(nat_x, nat_y) ];
	}

	/***************************************************************************
	 * Return the tile for the given cartesian (map) position.
	 **************************************************************************/
	static tile map_pos_to_tile(int map_x, int map_y)
	{
		int nat_x=0, nat_y=0;

		if (null==Map.map.tiles) {
			return null;
		}

		/* Normalization is best done in native coordinates. */
		Point p = MAP_TO_NATIVE_POS(nat_x, nat_y, map_x, map_y);
		return base_native_pos_to_tile(p.x, p.y);
	}

	/***************************************************************************
	 * Return the tile for the given native position.
	 **************************************************************************/
	tile native_pos_to_tile(int nat_x, int nat_y) {
		if (null==Map.map.tiles) {
			return null;
		}

		return base_native_pos_to_tile(nat_x, nat_y);
	}

// /****************************************************************************
//  Return the tile for the given index position.
//****************************************************************************/
//tile index_to_tile(int index)
//{
//  if (null==Map.map.tiles) {
//    return null;
//  }
//
//  if (index >= 0 && index < Map_H.MAX_MAP_INDEX) {
//    return Map.map.tiles + index;
//  } else {
//    /* Unwrapped index coordinates are impossible, so the best we can do is
//     * return null. */
//    return null;
//  }
//}
//
///**************************************************************************
//  Return the player who owns this tile (or null if none).
//**************************************************************************/
//player map_get_owner(final tile ptile)
//{
//  return ptile.owner;
//}
//
///**************************************************************************
//  Set the owner of a tile (may be null).
//**************************************************************************/
//void map_set_owner(tile ptile, player owner)
//{
//  ptile.owner = owner;
//}
//
///***************************************************************
//...
//***************************************************************/
//static void tile_free(tile ptile)
//{
//  unit_list_unlink_all(&ptile.units);
//  if (ptile.spec_sprite) {
//    free(ptile.spec_sprite);
//    ptile.spec_sprite = null;
//  }
//}
//
///**************************************************************************
//  Allocate space for map, and initialise the tiles.
//  Uses current Map.map.xsize and Map.map.ysize.
//**************************************************************************/
//void map_allocate()
//{
//  util.freelog(Log.LOG_DEBUG, "map_allocate (was %p) (%d,%d)",
//	  Map.map.tiles, Map.map.xsize, Map.map.ysize);
//
//  assert(Map.map.tiles == null);
//  Map.map.tiles = fc_malloc(Map.map.xsize * Map.map.ysize * sizeof(struct tile));
//  for(tile ptile :  Map.map.tiles){
//    int index, nat_x, nat_y, map_x, map_y;
//
//    index = ptile - Map.map.tiles;
//    index_to_native_pos(&nat_x, &nat_y, index);
//    index_to_map_pos(&map_x, &map_y, index);
//    CHECK_INDEX(index);
//    CHECK_MAP_POS(map_x, map_y);
//    CHECK_NATIVE_POS(nat_x, nat_y);
//
//    /* HACK: these fields are declared final to keep anyone from changing
//     * them.  But we have to set them somewhere!  This should be the only
//     * place. */
//    *(int *)&ptile.index = index;
//    *(int *)&ptile.x = map_x;
//    *(int *)&ptile.y = map_y;
//    *(int *)&ptile.nat_x = nat_x;
//    *(int *)&ptile.nat_y = nat_y;
//
//    tile_init(ptile);
//  }
//
//  generate_city_map_indices();
//  generate_map_indices();
//}
//
///***************************************************************
//  Frees the allocated memory of the Map.map.
//***************************************************************/
//void map_free()
//{
//  if (Map.map.tiles) {
//    /* it is possible that map_init was called but not map_allocate */
//
//    for(tile ptile :  Map.map.tiles){
//      tile_free(ptile);
//    }
//
//    free(Map.map.tiles);
//    Map.map.tiles = null;
//  }
//}
//
///***************************************************************
//...
//***************************************************************/
//enum int get_special_by_name(final String name)
//{
//  int i;
//  enum int st = 1;
//
//  for (i = 0; i < ARRAY_SIZE(int_names); i++) {
//    if (0 == strcmp(name, int_names[i]))
//      return st;
//      
//    st <<= 1;
//  }
//
//  return S_NO_SPECIAL;
//}
//
///***************************************************************
//...
//***************************************************************/
//final String get_special_name(enum int type)
//{
//  int i;
//
//  for (i = 0; i < ARRAY_SIZE(int_names); i++) {
//    if ((type & 0x1) == 1) {
//      return _(int_names[i]);
//    }
//    type >>= 1;
//  }
//
//  return null;
//}
//
///****************************************************************************
//  Return the "distance" (which is really the Manhattan distance, and should
//  rarely be used) for a given vector.
//****************************************************************************/
//static int map_vector_to_distance(int dx, int dy)
//{
//  if (topo_has_flag(TF_HEX)) {
//    /* Hex: all directions are cardinal so the distance is equivalent to
//     * the real distance. */
//    return map_vector_to_real_distance(dx, dy);
//  } else {
//    return abs(dx) + abs(dy);
//  }
//}
//
///****************************************************************************
//  Return the "real" distance for a given vector.
//****************************************************************************/
//int map_vector_to_real_distance(int dx, int dy)
//{
//  if (topo_has_flag(TF_HEX)) {
//    if (topo_has_flag(TF_ISO)) {
//      /* Iso-hex: you can't move NE or SW. */
//      if ((dx < 0 && dy > 0)
//	  || (dx > 0 && dy < 0)) {
//	/* Diagonal moves in this direction aren't allowed, so it will take
//	 * the full number of moves. */
//	return abs(dx) + abs(dy);
//      } else {
//	/* Diagonal moves in this direction *are* allowed. */
//	return MAX(abs(dx), abs(dy));
//      }
//    } else {
//      /* Hex: you can't move SE or NW. */
//      if ((dx > 0 && dy > 0)
//	  || (dx < 0 && dy < 0)) {
//	/* Diagonal moves in this direction aren't allowed, so it will take
//	 * the full number of moves. */
//	return abs(dx) + abs(dy);
//      } else {
//	/* Diagonal moves in this direction *are* allowed. */
//	return MAX(abs(dx), abs(dy));
//      }
//    }
//  } else {
//    return MAX(abs(dx), abs(dy));
//  }
//}
//
///****************************************************************************
//  Return the sq_distance for a given vector.
//****************************************************************************/
//int map_vector_to_sq_distance(int dx, int dy)
//{
//  if (topo_has_flag(TF_HEX)) {
//    /* Hex: The square distance is just the square of the real distance; we
//     * don't worry about pythagorean calculations. */
//    int dist = map_vector_to_real_distance(dx, dy);
//
//    return dist * dist;
//  } else {
//    return dx * dx + dy * dy;
//  }
//}

/***************************************************************
...
***************************************************************/
public static int real_map_distance(final tile tile0, final tile tile1)
{
//  int dx, dy;
//
//  map_distance_vector(&dx, &dy, tile0, tile1);
//  return map_vector_to_real_distance(dx, dy);
  return 0;
}

///***************************************************************
//...
//***************************************************************/
//int sq_map_distance(final tile tile0, final tile tile1)
//{
//  /* We assume map_distance_vector gives us the vector with the
//     minimum squared distance. Right now this is true. */
//  int dx, dy;
//
//  map_distance_vector(&dx, &dy, tile0, tile1);
//  return map_vector_to_sq_distance(dx, dy);
//}
//
///***************************************************************
//...
//***************************************************************/
//int map_distance(final tile tile0, final tile tile1)
//{
//  /* We assume map_distance_vector gives us the vector with the
//     minimum map distance. Right now this is true. */
//  int dx, dy;
//
//  map_distance_vector(&dx, &dy, tile0, tile1);
//  return map_vector_to_distance(dx, dy);
//}
//
///*************************************************************************
//  This is used in mapgen for rivers going into ocen.  The name is 
//  intentionally made awkward to prevent people from using it in place of
//  Terrain_H.is_ocean_near_tile
//*************************************************************************/
//boolean is_cardinally_adj_to_ocean(final tile ptile)
//{
//  cardinal_for(tile tile1: util.adjc_tile_iterate(ptile)) {
//    if (Terrain_H.is_ocean(map_get_terrain(tile1))) {
//      return true;
//    }
//  } cardinal_adjc_iterate_end;
//
//  return false;
//}
//
///****************************************************************************
//  Return true if this ocean terrain is adjacent to a safe coastline.
//****************************************************************************/
//boolean is_safe_ocean(final tile ptile)
//{
//  for(tile tile1: util.adjc_tile_iterate(ptile)) {
//    int ter = map_get_terrain(tile1);
//    if (!Terrain_H.terrain_has_flag(ter, TER_UNSAFE_COAST) && ter != T_UNKNOWN) {
//      return true;
//    }
//  }
//
//  return false;
//}
//
///***************************************************************
//Returns whether you can put a city on land near enough to use
//the tile.
//***************************************************************/
//boolean is_sea_usable(final tile ptile)
//{
//  map_city_radius_iterate(ptile, tile1) {
//    if (!Terrain_H.is_ocean(map_get_terrain(tile1))) {
//      return true;
//    }
//  } map_city_radius_iterate_end;
//
//  return false;
//}
//
///***************************************************************
//...
//***************************************************************/
//int get_tile_food_base(final tile ptile)
//{
//  if (tile_has_special(ptile, S_SPECIAL_1)) 
//    return get_tile_type(ptile.terrain).food_special_1;
//  else if (tile_has_special(ptile, S_SPECIAL_2))
//    return get_tile_type(ptile.terrain).food_special_2;
//  else
//    return get_tile_type(ptile.terrain).food;
//}
//
///***************************************************************
//...
//***************************************************************/
//int get_tile_shield_base(final tile ptile)
//{
//  if (tile_has_special(ptile, S_SPECIAL_1))
//    return get_tile_type(ptile.terrain).shield_special_1;
//  else if(tile_has_special(ptile, S_SPECIAL_2))
//    return get_tile_type(ptile.terrain).shield_special_2;
//  else
//    return get_tile_type(ptile.terrain).shield;
//}
//
///***************************************************************
//...
//***************************************************************/
//int get_tile_trade_base(final tile ptile)
//{
//  if (tile_has_special(ptile, S_SPECIAL_1))
//    return get_tile_type(ptile.terrain).trade_special_1;
//  else if (tile_has_special(ptile, S_SPECIAL_2))
//    return get_tile_type(ptile.terrain).trade_special_2;
//  else
//    return get_tile_type(ptile.terrain).trade;
//}
//
///***************************************************************
//  Return a (static) string with special(s) name(s);
//  eg: "Mine"
//  eg: "Road/Farmland"
//***************************************************************/
//final String map_get_infrastructure_text(enum int spe)
//{
//  static char s[64];
//  char *p;
//  
//  s[0] = '\0';
//
//  /* Since railroad requires road, Road/Railroad is redundant */
//  if (contains_special(spe, Terrain_H.S_RAILROAD))
//    cat_snprintf(s, sizeof(s), "%s/", "Railroad");
//  else if (contains_special(spe, Terrain_H.S_ROAD))
//    cat_snprintf(s, sizeof(s), "%s/", "Road");
//
//  /* Likewise for farmland on irrigation */
//  if (contains_special(spe, S_FARMLAND))
//    cat_snprintf(s, sizeof(s), "%s/", "Farmland");
//  else if (contains_special(spe, S_IRRIGATION))
//    cat_snprintf(s, sizeof(s), "%s/", "Irrigation");
//
//  if (contains_special(spe, S_MINE))
//    cat_snprintf(s, sizeof(s), "%s/", "Mine");
//
//  if (contains_special(spe, S_FORTRESS))
//    cat_snprintf(s, sizeof(s), "%s/", "Fortress");
//
//  if (contains_special(spe, Terrain_H.S_AIRBASE))
//    cat_snprintf(s, sizeof(s), "%s/", "Airbase");
//
//  p = s + s.length() - 1;
//  if (*p == '/')
//    *p = '\0';
//
//  return s;
//}
//
///****************************************************************************
//  Return the prerequesites needed before building the given infrastructure.
//****************************************************************************/
//enum int map_get_infrastructure_prerequisite(enum int spe)
//{
//  enum int prereq = S_NO_SPECIAL;
//
//  if (contains_special(spe, Terrain_H.S_RAILROAD)) {
//    prereq |= Terrain_H.S_ROAD;
//  }
//  if (contains_special(spe, S_FARMLAND)) {
//    prereq |= S_IRRIGATION;
//  }
//
//  return prereq;
//}
//
///***************************************************************
//...
//***************************************************************/
//enum int get_preferred_pillage(enum int pset)
//{
//  if (contains_special(pset, S_FARMLAND))
//    return S_FARMLAND;
//  if (contains_special(pset, S_IRRIGATION))
//    return S_IRRIGATION;
//  if (contains_special(pset, S_MINE))
//    return S_MINE;
//  if (contains_special(pset, S_FORTRESS))
//    return S_FORTRESS;
//  if (contains_special(pset, Terrain_H.S_AIRBASE))
//    return Terrain_H.S_AIRBASE;
//  if (contains_special(pset, Terrain_H.S_RAILROAD))
//    return Terrain_H.S_RAILROAD;
//  if (contains_special(pset, Terrain_H.S_ROAD))
//    return Terrain_H.S_ROAD;
//  return S_NO_SPECIAL;
//}
//
///***************************************************************
//...
//***************************************************************/
//boolean is_water_adjacent_to_tile(final tile ptile)
//{
//  if (Terrain_H.is_ocean(ptile.terrain)
//      || tile_has_special(ptile, S_RIVER)
//      || tile_has_special(ptile, S_IRRIGATION))
//    return true;
//
//  cardinal_for(tile tile1: util.adjc_tile_iterate(ptile)) {
//    if (Terrain_H.is_ocean(tile1.terrain)
//	|| tile_has_special(tile1, S_RIVER)
//	|| tile_has_special(tile1, S_IRRIGATION))
//      return true;
//  } cardinal_adjc_iterate_end;
//
//  return false;
//}
//
///***************************************************************
//...
//***************************************************************/
//int map_build_road_time(final tile ptile)
//{
//  return get_tile_type(ptile.terrain).road_time * ACTIVITY_FACTOR;
//}
//
///***************************************************************
//...
//***************************************************************/
//int map_build_irrigation_time(final tile ptile)
//{
//  return get_tile_type(ptile.terrain).irrigation_time * ACTIVITY_FACTOR;
//}
//
///***************************************************************
//...
//***************************************************************/
//int map_build_mine_time(final tile ptile)
//{
//  return get_tile_type(ptile.terrain).mining_time * ACTIVITY_FACTOR;
//}
//
///***************************************************************
//...
//***************************************************************/
//int map_transform_time(final tile ptile)
//{
//  return get_tile_type(ptile.terrain).transform_time * ACTIVITY_FACTOR;
//}
//
///***************************************************************
//...
//***************************************************************/
//int map_build_rail_time(final tile ptile)
//{
//  return get_tile_type(ptile.terrain).rail_time * ACTIVITY_FACTOR;
//}
//
///***************************************************************
//...
//***************************************************************/
//int map_build_airbase_time(final tile ptile)
//{
//  return get_tile_type(ptile.terrain).airbase_time * ACTIVITY_FACTOR;
//}
//
///***************************************************************
//...
//***************************************************************/
//int map_build_fortress_time(final tile ptile)
//{
//  return get_tile_type(ptile.terrain).fortress_time * ACTIVITY_FACTOR;
//}
//
///***************************************************************
//...
//***************************************************************/
//int map_clean_pollution_time(final tile ptile)
//{
//  return get_tile_type(ptile.terrain).clean_pollution_time * ACTIVITY_FACTOR;
//}
//
///***************************************************************
//...
//***************************************************************/
//int map_clean_fallout_time(final tile ptile)
//{
//  return get_tile_type(ptile.terrain).clean_fallout_time * ACTIVITY_FACTOR;
//}
//
///***************************************************************
//  Time to complete given activity on given tile.
//***************************************************************/
//int map_activity_time(enum unit_activity activity, final tile ptile)
//{
//  switch (activity) {
//  case ACTIVITY_POLLUTION:
//    return map_clean_pollution_time(ptile);
//  case ACTIVITY_ROAD:
//    return map_build_road_time(ptile);
//  case ACTIVITY_MINE:
//    return map_build_mine_time(ptile);
//  case ACTIVITY_IRRIGATE:
//    return map_build_irrigation_time(ptile);
//  case ACTIVITY_FORTRESS:
//    return map_build_fortress_time(ptile);
//  case ACTIVITY_RAILROAD:
//    return map_build_rail_time(ptile);
//  case ACTIVITY_TRANSFORM:
//    return map_transform_time(ptile);
//  case ACTIVITY_AIRBASE:
//    return map_build_airbase_time(ptile);
//  case ACTIVITY_FALLOUT:
//    return map_clean_fallout_time(ptile);
//  default:
//    return 0;
//  }
//}
//
///***************************************************************
//...
//***************************************************************/
//static void clear_infrastructure(tile ptile)
//{
//  map_clear_special(ptile, S_INFRASTRUCTURE_MASK);
//}
//
///***************************************************************
//...
//***************************************************************/
//static void clear_dirtiness(tile ptile)
//{
//  map_clear_special(ptile, S_POLLUTION | S_FALLOUT);
//}
//
///***************************************************************
//...
//***************************************************************/
//void map_irrigate_tile(tile ptile)
//{
//  int now, result;
//  
//  now = ptile.terrain;
//  result = get_tile_type(now).irrigation_result;
//
//  if (now == result) {
//    if (map_has_special(ptile, S_IRRIGATION)) {
//      map_set_special(ptile, S_FARMLAND);
//    } else {
//      map_set_special(ptile, S_IRRIGATION);
//    }
//  } else if (result != T_NONE) {
//    map_set_terrain(ptile, result);
//    if (Terrain_H.is_ocean(result)) {
//      clear_infrastructure(ptile);
//      clear_dirtiness(ptile);
//
//      /* FIXME: When rest of code can handle
//       * rivers in oceans, don't clear this! */
//      map_clear_special(ptile, S_RIVER);
//    }
//    reset_move_costs(ptile);
//  }
//  map_clear_special(ptile, S_MINE);
//}
//
///***************************************************************
//...
//***************************************************************/
//void map_mine_tile(tile ptile)
//{
//  int now, result;
//  
//  now = ptile.terrain;
//  result = get_tile_type(now).mining_result;
//  
//  if (now == result) {
//    map_set_special(ptile, S_MINE);
//  } else if (result != T_NONE) {
//    map_set_terrain(ptile, result);
//    if (Terrain_H.is_ocean(result)) {
//      clear_infrastructure(ptile);
//      clear_dirtiness(ptile);
//
//      /* FIXME: When rest of code can handle
//       * rivers in oceans, don't clear this! */
//      map_clear_special(ptile, S_RIVER);
//    }
//    reset_move_costs(ptile);
//  }
//  map_clear_special(ptile, S_FARMLAND);
//  map_clear_special(ptile, S_IRRIGATION);
//}
//
///***************************************************************
//...
//***************************************************************/
//void change_terrain(tile ptile, int type)
//{
//  map_set_terrain(ptile, type);
//  if (Terrain_H.is_ocean(type)) {
//    clear_infrastructure(ptile);
//    clear_dirtiness(ptile);
//    map_clear_special(ptile, S_RIVER);	/* FIXME: When rest of code can handle
//					   rivers in oceans, don't clear this! */
//  }
//
//  reset_move_costs(ptile);
//
//  /* Clear mining/irrigation if resulting terrain type cannot support
//     that feature.  (With current rules, this should only clear mines,
//     but I'm including both cases in the most general form for possible
//     future ruleset expansion. -GJW) */
//  
//  if (get_tile_type(type).mining_result != type)
//    map_clear_special(ptile, S_MINE);
//
//  if (get_tile_type(type).irrigation_result != type)
//    map_clear_special(ptile, S_FARMLAND | S_IRRIGATION);
//}
//
///***************************************************************
//...
//***************************************************************/
//void map_transform_tile(tile ptile)
//{
//  int now, result;
//  
//  now = ptile.terrain;
//  result = get_tile_type(now).transform_result;
//  
//  if (result != T_NONE) {
//    change_terrain(ptile, result);
//  }
//}
//
///**************************************************************************
//This function returns true if the tile at the given location can be
//"reclaimed" from ocean into land.  This is the case only when there are
//a sufficient number of adjacent tiles that are not ocean.
//**************************************************************************/
//boolean can_reclaim_ocean(final tile ptile)
//{
//  int land_tiles = 100 - count_ocean_near_tile(ptile, false, true);
//
//  return land_tiles >= terrain_control.ocean_reclaim_requirement_pct;
//}
//
///**************************************************************************
//This function returns true if the tile at the given location can be
//"channeled" from land into ocean.  This is the case only when there are
//a sufficient number of adjacent tiles that are ocean.
//**************************************************************************/
//boolean can_channel_land(final tile ptile)
//{
//  int ocean_tiles = count_ocean_near_tile(ptile, false, true);
//
//  return ocean_tiles >= terrain_control.land_channel_requirement_pct;
//}
//
///***************************************************************
//  The basic cost to move punit from tile t1 to tile t2.
//  That is, tile_move_cost(), with pre-calculated tile pointers;
//  the tiles are assumed to be adjacent, and the (x,y)
//  values are used only to get the river bonus correct.
//
//  May also be used with punit==null, in which case punit
//  tests are not done (for unit-independent results).
//***************************************************************/
//static int tile_move_cost_ptrs(unit punit,
//			       final tile t1, final tile t2)
//{
//  boolean cardinal_move;
//
//  if (Game.game.slow_invasions
//      && punit 
//      && is_ground_unit(punit) 
//      && Terrain_H.is_ocean(t1.terrain)
//      && !Terrain_H.is_ocean(t2.terrain)) {
//    /* Ground units moving from sea to land lose all their movement
//     * if "slowinvasions" server option is turned on. */
//    return punit.moves_left;
//  }
//  if (punit && !is_ground_unit(punit))
//    return Unit_H.SINGLE_MOVE;
//  if (tile_has_special(t1, Terrain_H.S_RAILROAD) && tile_has_special(t2, Terrain_H.S_RAILROAD))
//    return MOVE_COST_RAIL;
///* return (punit.move_rate()/RAIL_MAX) */
//  if (punit && unit_flag(punit, F_IGTER))
//    return Unit_H.SINGLE_MOVE/3;
//  if (tile_has_special(t1, Terrain_H.S_ROAD) && tile_has_special(t2, Terrain_H.S_ROAD))
//    return MOVE_COST_ROAD;
//
//  if (tile_has_special(t1, S_RIVER) && tile_has_special(t2, S_RIVER)) {
//    cardinal_move = is_move_cardinal(t1, t2);
//    switch (terrain_control.river_move_mode) {
//    case RMV_NORMAL:
//      break;
//    case RMV_FAST_STRICT:
//      if (cardinal_move)
//	return MOVE_COST_RIVER;
//      break;
//    case RMV_FAST_RELAXED:
//      if (cardinal_move)
//	return MOVE_COST_RIVER;
//      else
//	return 2 * MOVE_COST_RIVER;
//    case RMV_FAST_ALWAYS:
//      return MOVE_COST_RIVER;
//    default:
//      break;
//    }
//  }
//
//  return(get_tile_type(t2.terrain).movement_cost*Unit_H.SINGLE_MOVE);
//}
//
///***************************************************************
//  tile_move_cost_ai is used to fill the move_cost array of struct
//  tile. The cached values of this array are used in server/gotohand.c
//  and client/goto.c. tile_move_cost_ai returns the move cost as
//  calculated by tile_move_cost_ptrs (with no unit pointer to get
//  unit-independent results) EXCEPT if either of the source or
//  destination tile is an ocean tile. Then the result of the method
//  shows if a ship can take the step from the source position to the
//  destination position (return value is MOVE_COST_FOR_VALID_SEA_STEP)
//  or not (return value is maxcost).
//
//  A ship can take the step if:
//    - both tiles are ocean or
//    - one of the tiles is ocean and the other is a city or is unknown
//***************************************************************/
//static int tile_move_cost_ai(tile tile0, tile tile1,
//			     int maxcost)
//{
//  assert(!is_server
//	 || (tile0.terrain != T_UNKNOWN && tile1.terrain != T_UNKNOWN));
//
//  if (Terrain_H.is_ocean(tile0.terrain) && Terrain_H.is_ocean(tile1.terrain)) {
//    return MOVE_COST_FOR_VALID_SEA_STEP;
//  }
//
//  if (Terrain_H.is_ocean(tile0.terrain)
//      && (tile1.city || tile1.terrain == T_UNKNOWN)) {
//    return MOVE_COST_FOR_VALID_SEA_STEP;
//  }
//
//  if (Terrain_H.is_ocean(tile1.terrain)
//      && (tile0.city || tile0.terrain == T_UNKNOWN)) {
//    return MOVE_COST_FOR_VALID_SEA_STEP;
//  }
//
//  if (Terrain_H.is_ocean(tile0.terrain) || Terrain_H.is_ocean(tile1.terrain)) {
//    return maxcost;
//  }
//
//  return tile_move_cost_ptrs(null, tile0, tile1);
//}
//
///***************************************************************
// ...
//***************************************************************/
//static void debug_log_move_costs(final String str, tile tile0)
//{
//  /* the %x don't work so well for oceans, where
//     move_cost[]==-3 ,.. --dwp
//  */
//  util.freelog(Log.LOG_DEBUG, "%s (%d, %d) [%x%x%x%x%x%x%x%x]", str,
//	  tile0.x, tile0.y,
//	  tile0.move_cost[0], tile0.move_cost[1],
//	  tile0.move_cost[2], tile0.move_cost[3],
//	  tile0.move_cost[4], tile0.move_cost[5],
//	  tile0.move_cost[6], tile0.move_cost[7]);
//}
//
///***************************************************************
//  Recalculate tile.move_cost[] for (x,y), and for adjacent
//  tiles in direction back to (x,y).  That is, call this when
//  something has changed on (x,y), eg roads, city, transform, etc.
//***************************************************************/
//void reset_move_costs(tile ptile)
//{
//  int maxcost = 72; /* should be big enough without being TOO big */
//
//  debug_log_move_costs("Resetting move costs for", ptile);
//
//  /* trying to move off the screen is the default */
//  memset(ptile.move_cost, maxcost, sizeof(ptile.move_cost));
//
//  adjc_dir_iterate(ptile, tile1, dir) {
//    ptile.move_cost[dir] = tile_move_cost_ai(ptile, tile1, maxcost);
//    /* reverse: not at all obfuscated now --dwp */
//    tile1.move_cost[DIR_REVERSE(dir)] =
//	tile_move_cost_ai(tile1, ptile, maxcost);
//  } adjc_dir_iterate_end;
//  debug_log_move_costs("Reset move costs for", ptile);
//}
//
///***************************************************************
//  Initialize tile.move_cost[] for all tiles, where move_cost[i]
//  is the unit-independent cost to move _from_ that tile, to
//  adjacent tile in direction specified by i.
//***************************************************************/
//void initialize_move_costs()
//{
//  int maxcost = 72; /* should be big enough without being TOO big */
//
//  for(tile ptile :  Map.map.tiles){
//    /* trying to move off the screen is the default */
//    memset(ptile.move_cost, maxcost, sizeof(ptile.move_cost));
//
//    adjc_dir_iterate(ptile, tile1, dir) {
//      ptile.move_cost[dir] = tile_move_cost_ai(ptile, tile1, maxcost);
//    }
//    adjc_dir_iterate_end;
//  }
//}
//
///***************************************************************
//  The cost to move punit from where it is to tile x,y.
//  It is assumed the move is a valid one, e.g. the tiles are adjacent.
//***************************************************************/
//int map_move_cost(unit punit, final tile ptile)
//{
//  return tile_move_cost_ptrs(punit, punit.tile, ptile);
//}
//
///***************************************************************
//...
//***************************************************************/
//boolean is_tiles_adjacent(final tile tile0, final tile tile1)
//{
//  return Map.real_map_distance(tile0, tile1) == 1;
//}
//
///***************************************************************
//...
//***************************************************************/
//Continent_id map_get_continent(final tile ptile)
//{
//  return ptile.continent;
//}
//
///***************************************************************
//...
//***************************************************************/
//void map_set_continent(tile ptile, Continent_id val)
//{
//  ptile.continent = val;
//}

	/***************************************************************
...
	 ***************************************************************/
	public static int map_get_terrain(tile ptile)
	{
		return ptile.terrain;
	}

///***************************************************************
//...
//***************************************************************/
//void map_set_terrain(tile ptile, int ter)
//{
//  ptile.terrain = ter;
//}
//
///***************************************************************
//...
//***************************************************************/
//enum int map_get_special(final tile ptile)
//{
//  return ptile.special;
//}

/***************************************************************
 Returns true iff the given special is found at the given map
 position.
***************************************************************/
public static boolean map_has_special(final tile ptile, int special)
{
  return contains_special(ptile.special, special);
}

	/***************************************************************************
	 * Returns true iff the given tile has the given special.
	 **************************************************************************/
	public static boolean tile_has_special(final tile ptile,
			int special)
	{
		return contains_special(ptile.special, special);
	}

	/***************************************************************************
	 * Returns true iff the given special is found in the given set.
	 **************************************************************************/
	static boolean contains_special(int set, int to_test_for) {
		// enum int masked = set & to_test_for;

		// assert(0 == (int) S_NO_SPECIAL);

		// /*
		// * contains_special should only be called with one S_* in
		// * to_test_for.
		// */
		// assert(masked == S_NO_SPECIAL || masked == to_test_for);

		// return masked == to_test_for;
		return false;
	}

///***************************************************************
//...
//***************************************************************/
//void map_set_special(tile ptile, enum int spe)
//{
//  ptile.special |= spe;
//
//  if (contains_special(spe, Terrain_H.S_ROAD) || contains_special(spe, Terrain_H.S_RAILROAD)) {
//    reset_move_costs(ptile);
//  }
//}

/***************************************************************
...
***************************************************************/
public static void map_clear_special(tile ptile, int spe)
{
  ptile.special &= ~spe;

  if (contains_special(spe, Terrain_H.S_ROAD) || contains_special(spe, Terrain_H.S_RAILROAD)) {
//    reset_move_costs(ptile);
  }
}

	/***************************************************************************
	 * Remove any specials which may exist at these map co-ordinates.
	 **************************************************************************/
//	void map_clear_all_specials(tile ptile)
//	{
//		ptile.special = S_NO_SPECIAL;
//	}

	/***************************************************************************
	 * ...
	 **************************************************************************/
	public static city map_get_city(final tile ptile)
	{
		return ptile.city;
	}

	/*******************************************************************************
	 * ...
	 ******************************************************************************/
	public static void map_set_city(tile ptile, city pcity)
	{
		ptile.city = pcity;
	}

	/*******************************************************************************
	 * Are (x1,y1) and (x2,y2) really the same when adjusted? This function might be
	 * necessary ALOT of places...
	 ******************************************************************************/
	public static boolean same_pos(final tile tile1, final tile tile2)
	{
		assert(tile1 != null && tile2 != null);
		return (tile1 == tile2);
	}

	boolean is_real_map_pos(int x, int y)
	{
		return normalize_map_pos(x, y);
	}
	// /**************************************************************************
//Returns true iff the map position is normal. "Normal" here means that
//it is both a real/valid coordinate set and that the coordinates are in
//their canonical/proper form. In plain English: the coordinates must be
//on the Map.map.
//**************************************************************************/
//boolean is_normal_map_pos(int x, int y)
//{
//  int nat_x, nat_y;
//
//  MAP_TO_NATIVE_POS(&nat_x, &nat_y, x, y);
//  return nat_x >= 0 && nat_x < Map.map.xsize && nat_y >= 0 && nat_y < Map.map.ysize;
//}

	/***************************************************************************
	 * If the position is real, it will be normalized and true will be returned.
	 * If the position is unreal, it will be left unchanged and false will be
	 * returned.
	 * 
	 * Note, we need to leave x and y with sane values even in the unreal case.
	 * Some callers may for instance call nearest_real_pos on these values.
	 **************************************************************************/
	public static boolean normalize_map_pos(Integer x, Integer y)	{
		//TODO: use point to replace (x,y)
		tile ptile = map_pos_to_tile(x, y);

		if (ptile!=null) {
			x = ptile.x;
			y = ptile.y;
			return true;
		} else {
			return false;
		}
	}

///**************************************************************************
//Twiddle *x and *y to point the the nearest real tile, and ensure that the
//position is normalized.
//**************************************************************************/
//tile nearest_real_tile(int x, int y)
//{
//  int nat_x, nat_y;
//
//  MAP_TO_NATIVE_POS(&nat_x, &nat_y, x, y);
//  if (!topo_has_flag(TF_WRAPX)) {
//    nat_x = CLIP(0, nat_x, Map.map.xsize - 1);
//  }
//  if (!topo_has_flag(TF_WRAPY)) {
//    nat_y = CLIP(0, nat_y, Map.map.ysize - 1);
//  }
//  NATIVE_TO_MAP_POS(&x, &y, nat_x, nat_y);
//
//  return map_pos_to_tile(x, y);
//}
///****************************************************************************
//  Finds the difference between the two (unnormalized) positions, in
//  cartesian (map) coordinates.  Most callers should use map_distance_vector
//  instead.
//****************************************************************************/
//void base_map_distance_vector(int *dx, int *dy,
//			      int x0, int y0, int x1, int y1)
//{
//  if (topo_has_flag(TF_WRAPX) || topo_has_flag(TF_WRAPY)) {
//    /* Wrapping is done in native coordinates. */
//    MAP_TO_NATIVE_POS(&x0, &y0, x0, y0);
//    MAP_TO_NATIVE_POS(&x1, &y1, x1, y1);
//
//    /* Find the "native" distance vector. This corresponds closely to the
//     * map distance vector but is easier to wrap. */
//    *dx = x1 - x0;
//    *dy = y1 - y0;
//    if (topo_has_flag(TF_WRAPX)) {
//      /* Wrap dx to be in [-Map.map.xsize/2, Map.map.xsize/2). */
//      *dx = FC_WRAP(*dx + Map.map.xsize / 2, Map.map.xsize) - Map.map.xsize / 2;
//    }
//    if (topo_has_flag(TF_WRAPY)) {
//      /* Wrap dy to be in [-Map.map.ysize/2, Map.map.ysize/2). */
//      *dy = FC_WRAP(*dy + Map.map.ysize / 2, Map.map.ysize) - Map.map.ysize / 2;
//    }
//
//    /* Convert the native delta vector back to a pair of map positions. */
//    x1 = x0 + *dx;
//    y1 = y0 + *dy;
//    NATIVE_TO_MAP_POS(&x0, &y0, x0, y0);
//    NATIVE_TO_MAP_POS(&x1, &y1, x1, y1);
//  }
//
//  /* Find the final (map) vector. */
//  *dx = x1 - x0;
//  *dy = y1 - y0;
//}
//
///****************************************************************************
//  Topology function to find the vector which has the minimum "real"
//  distance between the map positions (x0, y0) and (x1, y1).  If there is
//  more than one vector with equal distance, no guarantee is made about
//  which is found.
//
//  Real distance is defined as the larger of the distances in the x and y
//  direction; since units can travel diagonally this is the "real" distance
//  a unit has to travel to get from point to point.
//
//  (See also: Map.real_map_distance, map_distance, and sq_map_distance.)
//
//  With the standard topology the ranges of the return value are:
//    -Map.map.xsize/2 <= dx <= Map.map.xsize/2
//    -Map.map.ysize   <  dy <  Map.map.ysize
//****************************************************************************/
//void map_distance_vector(int *dx, int *dy,
//			 final tile tile0,
//			 final tile tile1)
//{
//  base_map_distance_vector(dx, dy,
//			   tile0.x, tile0.y, tile1.x, tile1.y);
//}

/**************************************************************************
Random neighbouring square.
**************************************************************************/
public static tile rand_neighbour(final tile ptile)
{
//  int n;
//  tile tile1;
//
//  /* 
//   * list of all 8 directions 
//   */
//  enum direction8 dirs[8] = {
//    DIR8_NORTHWEST, DIR8_NORTH, DIR8_NORTHEAST, DIR8_WEST, DIR8_EAST,
//    DIR8_SOUTHWEST, DIR8_SOUTH, DIR8_SOUTHEAST
//  };
//
//  /* This clever loop by Trent Piepho will take no more than
//   * 8 tries to find a valid direction. */
//  for (n = 8; n > 0; n--) {
//    enum direction8 choice = (enum direction8) Rand.myrand(n);
//
//    /* this neighbour's OK */
//    tile1 = mapstep(ptile, dirs[choice]);
//    if (tile1) {
//      return tile1;
//    }
//
//    /* Choice was bad, so replace it with the last direction in the list.
//     * On the next iteration, one fewer choices will remain. */
//    dirs[choice] = dirs[n - 1];
//  }
//
//  assert(0!=1);			/* Are we on a 1x1 map with no wrapping??? */
  return null;
}

/**************************************************************************
 Random square anywhere on the Map.map.  Only normal positions (for which
 is_normal_map_pos returns true) will be found.
 **************************************************************************/
public static tile rand_map_pos()
{
	int nat_x = Rand.myrand(Map.map.xsize), nat_y = Rand.myrand(Map.map.ysize);

//	return native_pos_to_tile(nat_x, nat_y);
	return null;
}

///**************************************************************************
//  Give a random tile anywhere on the map for which the 'filter' function
//  returns true.  Return false if none can be found.  The filter may be
//  null if any position is okay; if non-null it shouldn't have any side
//  effects.
//**************************************************************************/
//tile rand_map_pos_filtered(void *data,
//				   boolean (*filter)(final tile ptile,
//						  final void *data))
//{
//  tile ptile;
//  int tries = 0;
//  final int max_tries = Map.map.xsize * Map.map.ysize / ACTIVITY_FACTOR;
//
//  /* First do a few quick checks to find a spot.  The limit on number of
//   * tries could use some tweaking. */
//  do {
//    ptile = Map.map.tiles + Rand.myrand(Map.map.xsize * Map.map.ysize);
//  } while (filter && !filter(ptile, data) && ++tries < max_tries);
//
//  /* If that fails, count all available spots and pick one.
//   * Slow but reliable. */
//  if (tries == max_tries) {
//    int count = 0, positions[Map.map.xsize * Map.map.ysize];
//
//    for(tile ptile :  Map.map.tiles){
//      if (filter(ptile, data)) {
//	positions[count] = ptile.index;
//	count++;
//      }
//    }
//
//    if (count == 0) {
//      return null;
//    }
//
//    return Map.map.tiles + positions[Rand.myrand(count)];
//  } else {
//    return ptile;
//  }
//}
//
///**************************************************************************
//Return the debugging name of the direction.
//**************************************************************************/
//final String dir_get_name(enum direction8 dir)
//{
//  /* a switch statement is used so the ordering can be changed easily */
//  switch (dir) {
//  case DIR8_NORTH:
//    return "N";
//  case DIR8_NORTHEAST:
//    return "NE";
//  case DIR8_EAST:
//    return "E";
//  case DIR8_SOUTHEAST:
//    return "SE";
//  case DIR8_SOUTH:
//    return "S";
//  case DIR8_SOUTHWEST:
//    return "SW";
//  case DIR8_WEST:
//    return "W";
//  case DIR8_NORTHWEST:
//    return "NW";
//  default:
//    return "[Undef]";
//  }
//}
//
///**************************************************************************
//  Returns the next direction clock-wise.
//**************************************************************************/
//enum direction8 dir_cw(enum direction8 dir)
//{
//  /* a switch statement is used so the ordering can be changed easily */
//  switch (dir) {
//  case DIR8_NORTH:
//    return DIR8_NORTHEAST;
//  case DIR8_NORTHEAST:
//    return DIR8_EAST;
//  case DIR8_EAST:
//    return DIR8_SOUTHEAST;
//  case DIR8_SOUTHEAST:
//    return DIR8_SOUTH;
//  case DIR8_SOUTH:
//    return DIR8_SOUTHWEST;
//  case DIR8_SOUTHWEST:
//    return DIR8_WEST;
//  case DIR8_WEST:
//    return DIR8_NORTHWEST;
//  case DIR8_NORTHWEST:
//    return DIR8_NORTH;
//  default:
//    assert(0!=1);
//    return -1;
//  }
//}
//
///**************************************************************************
//  Returns the next direction counter-clock-wise.
//**************************************************************************/
//enum direction8 dir_ccw(enum direction8 dir)
//{
//  /* a switch statement is used so the ordering can be changed easily */
//  switch (dir) {
//  case DIR8_NORTH:
//    return DIR8_NORTHWEST;
//  case DIR8_NORTHEAST:
//    return DIR8_NORTH;
//  case DIR8_EAST:
//    return DIR8_NORTHEAST;
//  case DIR8_SOUTHEAST:
//    return DIR8_EAST;
//  case DIR8_SOUTH:
//    return DIR8_SOUTHEAST;
//  case DIR8_SOUTHWEST:
//    return DIR8_SOUTH;
//  case DIR8_WEST:
//    return DIR8_SOUTHWEST;
//  case DIR8_NORTHWEST:
//    return DIR8_WEST;
//  default:
//    assert(0!=1);
//    return -1;
//  }
//}
//
///**************************************************************************
//  Returns true iff the given direction is a valid one.
//**************************************************************************/
//boolean is_valid_dir(enum direction8 dir)
//{
//  switch (dir) {
//  case DIR8_SOUTHEAST:
//  case DIR8_NORTHWEST:
//    /* These directions are invalid in hex topologies. */
//    return !(topo_has_flag(TF_HEX) && !topo_has_flag(TF_ISO));
//  case DIR8_NORTHEAST:
//  case DIR8_SOUTHWEST:
//    /* These directions are invalid in iso-hex topologies. */
//    return !(topo_has_flag(TF_HEX) && topo_has_flag(TF_ISO));
//  case DIR8_NORTH:
//  case DIR8_EAST:
//  case DIR8_SOUTH:
//  case DIR8_WEST:
//    return true;
//  default:
//    return false;
//  }
//}
//
///**************************************************************************
//  Returns true iff the given direction is a cardinal one.
//
//  Cardinal directions are those in which adjacent tiles share an edge not
//  just a vertex.
//**************************************************************************/
//boolean is_cardinal_dir(enum direction8 dir)
//{
//  switch (dir) {
//  case DIR8_NORTH:
//  case DIR8_SOUTH:
//  case DIR8_EAST:
//  case DIR8_WEST:
//    return true;
//  case DIR8_SOUTHEAST:
//  case DIR8_NORTHWEST:
//    /* These directions are cardinal in iso-hex topologies. */
//    return topo_has_flag(TF_HEX) && topo_has_flag(TF_ISO);
//  case DIR8_NORTHEAST:
//  case DIR8_SOUTHWEST:
//    /* These directions are cardinal in hexagonal topologies. */
//    return topo_has_flag(TF_HEX) && !topo_has_flag(TF_ISO);
//  }
//  return false;
//}
//
///**************************************************************************
//Return true and sets dir to the direction of the step if (end_x,
//end_y) can be reached from (start_x, start_y) in one step. Return
//false otherwise (value of dir is unchanged in this case).
//**************************************************************************/
//boolean base_get_direction_for_step(final tile start_tile,
//				 final tile end_tile,
//				 enum direction8 *dir)
//{
//  adjc_dir_iterate(start_tile, test_tile, test_dir) {
//    if (same_pos(end_tile, test_tile)) {
//      *dir = test_dir;
//      return true;
//    }
//  } adjc_dir_iterate_end;
//
//  return false;
//}
//
///**************************************************************************
//Return the direction which is needed for a step on the map from
//(start_x, start_y) to (end_x, end_y).
//**************************************************************************/
//int get_direction_for_step(final tile start_tile,
//			   final tile end_tile)
//{
//  enum direction8 dir;
//
//  if (base_get_direction_for_step(start_tile, end_tile, &dir)) {
//    return dir;
//  }
//
//  assert(0!=1);
//  return -1;
//}
//
///**************************************************************************
//  Returns true iff the move from the position (start_x,start_y) to
//  (end_x,end_y) is a cardinal one.
//**************************************************************************/
//boolean is_move_cardinal(final tile start_tile,
//		      final tile end_tile)
//{
//  return is_cardinal_dir(get_direction_for_step(start_tile, end_tile));
//}
//
///****************************************************************************
//  A "SINGULAR" position is any map position that has an abnormal number of
//  tiles in the radius of dist.
//
//  (map_x, map_y) must be normalized.
//
//  dist is the "real" map distance.
//****************************************************************************/
//boolean is_singular_tile(final tile ptile, int dist)
//{
//  do_in_natural_pos(ntl_x, ntl_y, ptile.x, ptile.y) {
//    /* Iso-natural coordinates are doubled in scale. */
//    dist *= MAP_IS_ISOMETRIC ? 2 : 1;
//
//    return ((!topo_has_flag(TF_WRAPX) 
//	     && (ntl_x < dist || ntl_x >= NATURAL_WIDTH - dist))
//	    || (!topo_has_flag(TF_WRAPY)
//		&& (ntl_y < dist || ntl_y >= NATURAL_HEIGHT - dist)));
//  } do_in_natural_pos_end;
//}
}