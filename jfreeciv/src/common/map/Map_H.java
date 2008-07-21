package common.map;

import static common.Map.*;

import common.Map;

import utility.shared.Shared_H;

public class Map_H {
//	enum topo_flag {
//		  /* Bit-values. */
//		  /* Changing these values will break map_init_topology. */
//		  TF_WRAPX = 1,
//		  TF_WRAPY = 2,
//		  TF_ISO = 4,
//		  TF_HEX = 8
//		};
	public static int TF_WRAPX = 1;
	public static int TF_WRAPY = 2;
	public static int TF_ISO = 4;
	public static int TF_HEX = 8;

//	#define MAP_IS_ISOMETRIC (topo_has_flag(TF_ISO) || topo_has_flag(TF_HEX))
	public static boolean MAP_IS_ISOMETRIC(){
		return (topo_has_flag(TF_ISO) || topo_has_flag(TF_HEX));
	}
//	#define CURRENT_TOPOLOGY (Map.map.topology_id)
	public static int CURRENT_TOPOLOGY = map.topology_id;
//	#define topo_has_flag(flag) ((CURRENT_TOPOLOGY & (flag)) != 0)
	public static boolean topo_has_flag(int flag){
		return ((CURRENT_TOPOLOGY & (flag)) != 0);
	}
	
	
//	/* Maximum value of index (for sanity checks and allocations) */
//	#define MAX_MAP_INDEX (Map.map.xsize * Map.map.ysize)
	public static final int  MAX_MAP_INDEX = (Map.map.xsize * Map.map.ysize) ;
//
//	#ifdef DEBUG
//	#define CHECK_MAP_POS(x,y) assert(is_normal_map_pos((x),(y)))
//	#define CHECK_NATIVE_POS(x, y) assert((x) >= 0 && (x) < Map.map.xsize \
//					      && (y) >= 0 && (y) < Map.map.ysize)
//	#define CHECK_INDEX(index) assert((index) >= 0 && (index) < MAX_MAP_INDEX)
//	#else
//	#define CHECK_MAP_POS(x,y) ((void)0)
//	#define CHECK_NATIVE_POS(x, y) ((void)0)
//	#define CHECK_INDEX(index) ((void)0)
//	#endif
//
//	#define native_pos_to_index(nat_x, nat_y)                                   \
//	  (CHECK_NATIVE_POS((nat_x), (nat_y)),					    \
//	   (nat_x) + (nat_y) * Map.map.xsize)
	public static int native_pos_to_index(int nat_x, int nat_y){                                   
//	  (CHECK_NATIVE_POS((nat_x), (nat_y)),					    
		return (nat_x) + (nat_y) * Map.map.xsize;
	}
//	#define index_to_native_pos(pnat_x, pnat_y, index)                          \
//	  (*(pnat_x) = (index) % Map.map.xsize,                                         \
//	   *(pnat_y) = (index) / Map.map.xsize)
//
//	/* Obscure math.  See explanation in doc/HACKING. */
//	#define NATIVE_TO_MAP_POS(pmap_x, pmap_y, nat_x, nat_y)                     \
//	  (MAP_IS_ISOMETRIC							    \
//	   ? (*(pmap_x) = ((nat_y) + ((nat_y) & 1)) / 2 + (nat_x),                  \
//	      *(pmap_y) = (nat_y) - *(pmap_x) + Map.map.xsize)                          \
//	   : (*(pmap_x) = (nat_x), *(pmap_y) = (nat_y)))
//
//	#define MAP_TO_NATIVE_POS(pnat_x, pnat_y, map_x, map_y)                     \
//	  (MAP_IS_ISOMETRIC							    \
//	   ? (*(pnat_y) = (map_x) + (map_y) - Map.map.xsize,                            \
//	      *(pnat_x) = (2 * (map_x) - *(pnat_y) - (*(pnat_y) & 1)) / 2)          \
//	   : (*(pnat_x) = (map_x), *(pnat_y) = (map_y)))
	public static Point MAP_TO_NATIVE_POS(int pnat_x, int pnat_y, int map_x, int map_y)  {
		int x, y;
		if(MAP_IS_ISOMETRIC()){ 
			y = (map_x) + (map_y) - Map.map.xsize;                           
			x = (2 * (map_x) - y - (y & 1)) / 2;
		}else{
			x = (map_x);
			y = (map_y);
		}
		return  new Point(x,y);
	}
//
//	#define NATURAL_TO_MAP_POS(pmap_x, pmap_y, nat_x, nat_y)                    \
//	  (MAP_IS_ISOMETRIC							    \
//	   ? (*(pmap_x) = ((nat_y) + (nat_x)) / 2,                                  \
//	      *(pmap_y) = (nat_y) - *(pmap_x) + Map.map.xsize)                          \
//	   : (*(pmap_x) = (nat_x), *(pmap_y) = (nat_y)))
//
//	#define MAP_TO_NATURAL_POS(pnat_x, pnat_y, map_x, map_y)                    \
//	  (MAP_IS_ISOMETRIC							    \
//	   ? (*(pnat_y) = (map_x) + (map_y) - Map.map.xsize,                            \
//	      *(pnat_x) = 2 * (map_x) - *(pnat_y))                                  \
//	   : (*(pnat_x) = (map_x), *(pnat_y) = (map_y)))
//
//
//	/* Provide a block to convert from map to native coordinates.  This allows
//	 * you to use a native version of the map position within the block.  Note
//	 * that the native position is declared as final and can't be changed
//	 * inside the block. */
//	#define do_in_native_pos(nat_x, nat_y, map_x, map_y)                        \
//	{                                                                           \
//	  int _nat_x, _nat_y;                                                       \
//	  MAP_TO_NATIVE_POS(&_nat_x, &_nat_y, map_x, map_y);			    \
//	  {                                                                         \
//	    final int nat_x = _nat_x, nat_y = _nat_y;
//
//	#define do_in_native_pos_end                                                \
//	  }                                                                         \
//	}
//
//	/* Provide a block to convert from map to natural coordinates. This allows
//	 * you to use a natural version of the map position within the block.  Note
//	 * that the natural position is declared as final and can't be changed
//	 * inside the block. */
//	#define do_in_natural_pos(ntl_x, ntl_y, map_x, map_y)                        \
//	{                                                                           \
//	  int _ntl_x, _ntl_y;                                                       \
//	  MAP_TO_NATURAL_POS(&_ntl_x, &_ntl_y, map_x, map_y);			    \
//	  {                                                                         \
//	    final int ntl_x = _ntl_x, ntl_y = _ntl_y;
//
//	#define do_in_natural_pos_end                                                \
//	  }                                                                         \
//	}
//
//	/* Width and height of the map, in native coordinates. */
//	public static final int NATIVE_WIDTH = Map.map.xsize;
//	public static final int NATIVE_HEIGHT = Map.map.ysize;
//
//	/* Width and height of the map, in natural coordinates. */
//	#define NATURAL_WIDTH (MAP_IS_ISOMETRIC ? 2 * Map.map.xsize : Map.map.xsize)
//	public static final int NATURAL_HEIGHT = Map.map.ysize;
//
//	#define MAP_WIDTH  \
//	  (MAP_IS_ISOMETRIC ? (Map.map.xsize + Map.map.ysize / 2) : Map.map.xsize)
//	#define MAP_HEIGHT \
//	  (MAP_IS_ISOMETRIC ? (Map.map.xsize + Map.map.ysize / 2) : Map.map.ysize)
//	  
//	static inline int map_pos_to_index(int map_x, int map_y);
//
//	/* index_to_map_pos(int *, int *, int) inverts map_pos_to_index */
//	#define index_to_map_pos(pmap_x, pmap_y, index) \
//	  (CHECK_INDEX(index),                          \
//	   index_to_native_pos(pmap_x, pmap_y, index),  \
//	   NATIVE_TO_MAP_POS(pmap_x, pmap_y, *(pmap_x), *(pmap_y)))
//
//	#define DIRSTEP(dest_x, dest_y, dir)	\
//	(    (dest_x) = DIR_DX[(dir)],      	\
//	     (dest_y) = DIR_DY[(dir)])

	
	
	/* This iterates outwards from the starting point. Every tile within max_dist
	 * will show up exactly once, in an outward (based on real map distance)
	 * order.  The returned values are always real and are normalized.  The
	 * starting position must be normal.
	 *
	 * See also iterate_outward() */
//	#define iterate_outward_dxy(start_tile, max_dist, tile_itr, dx_itr, dy_itr) \
//	{									    \
//	  final struct tile *_start_tile = (start_tile);			    \
//	  struct tile *tile_itr;						    \
//	  int _max_dist = (max_dist), _x_itr, _y_itr, dx_itr, dy_itr, _index;	    \
//	  bool _is_border = is_border_tile(_start_tile, _max_dist);		    \
//										    \
//	  for (_index = 0; _index < Map.map.num_iterate_outwards_indices; _index++) {   \
//	    if (Map.map.iterate_outwards_indices[_index].dist > _max_dist) {	    \
//	      break;								    \
//	    }									    \
//	    dx_itr = Map.map.iterate_outwards_indices[_index].dx;			    \
//	    dy_itr = Map.map.iterate_outwards_indices[_index].dy;			    \
//	    _x_itr = dx_itr + _start_tile->x;					    \
//	    _y_itr = dy_itr + _start_tile->y;					    \
//	    if (_is_border && !normalize_map_pos(&_x_itr, &_y_itr)) {		    \
//	      continue;								    \
//	    }									    \
//	    tile_itr = Map.map.tiles + map_pos_to_index(_x_itr, _y_itr);

//	#define iterate_outward_dxy_end						    \
//	  }                                                                         \
//	}
	
	/*
	 * Inline function definitions.  These are at the bottom because they may use
	 * elements defined above.
	 */

	public static int map_pos_to_index(Integer map_x, Integer map_y){
		//TODO
	  /* Note: writing this as a macro is hard; it needs temp variables. */
	  int nat_x, nat_y;

//	  CHECK_MAP_POS(map_x, map_y);
	  Point p = MAP_TO_NATIVE_POS(0, 0, map_x, map_y);
	  return native_pos_to_index(p.x, p.y);
	}
	/****************************************************************************
	  A "border position" is any map position that _may have_ positions within
	  real map distance dist that are non-normal.  To see its correctness,
	  consider the case where dist is 1 or 0.
	****************************************************************************/
	public static boolean is_border_tile( tile ptile, int dist)
	{
	  /* HACK: An iso-map compresses the value in the X direction but not in
	   * the Y direction.  Hence (x+1,y) is 1 tile away while (x,y+2) is also
	   * one tile away. */
	  int xdist = dist;
	  int ydist = (MAP_IS_ISOMETRIC() ? (2 * dist) : dist);

	  return (ptile.nat_x < xdist 
		  || ptile.nat_y < ydist
		  || ptile.nat_x >= Map.map.xsize - xdist
		  || ptile.nat_y >= Map.map.ysize - ydist);
	}

	/* Used for network transmission; do not change. */
	public static final int MAP_TILE_OWNER_NULL = Shared_H.MAX_UINT8;

	public static final int MAP_DEFAULT_HUTS = 50;
	public static final int MAP_MIN_HUTS = 0;
	public static final int MAP_MAX_HUTS = 500;

	/* Size of the map in thousands of tiles */
	public static final int MAP_DEFAULT_SIZE = 4;
	public static final int MAP_MIN_SIZE = 1;
	public static final int MAP_MAX_SIZE = 29;

	/* This defines the maximum linear size in _map_ coordinates.
	 * This must be smaller than 255 because of the way coordinates are sent
	 * across the network. */
	public static final int MAP_MAX_LINEAR_SIZE = 254;
	public static final int MAP_MIN_LINEAR_SIZE = 8;
	public static final int MAP_MAX_WIDTH = MAP_MAX_LINEAR_SIZE;
	public static final int MAP_MAX_HEIGHT = MAP_MAX_LINEAR_SIZE;

	public static final int MAP_ORIGINAL_TOPO = TF_WRAPX;
	public static final int MAP_DEFAULT_TOPO = TF_WRAPX;
	public static final int MAP_MIN_TOPO = 0;
	public static final int MAP_MAX_TOPO = 15;

	public static final int MAP_DEFAULT_SEED = 0;
	public static final int MAP_MIN_SEED = 0;
	public static final int MAP_MAX_SEED = (Shared_H.MAX_UINT32 >> 1);

	public static final int MAP_DEFAULT_LANDMASS = 30;
	public static final int MAP_MIN_LANDMASS = 15;
	public static final int MAP_MAX_LANDMASS = 85;

	public static final int MAP_DEFAULT_RICHES = 250;
	public static final int MAP_MIN_RICHES = 0;
	public static final int MAP_MAX_RICHES = 1000;

	public static final int MAP_DEFAULT_STEEPNESS = 30;
	public static final int MAP_MIN_STEEPNESS = 0;
	public static final int MAP_MAX_STEEPNESS = 100;

	public static final int MAP_DEFAULT_WETNESS = 50;
	public static final int MAP_MIN_WETNESS = 0;
	public static final int MAP_MAX_WETNESS = 100;

	public static final int MAP_DEFAULT_GENERATOR = 1;
	public static final int MAP_MIN_GENERATOR = 1;
	public static final int MAP_MAX_GENERATOR = 3;

	public static final int MAP_DEFAULT_STARTPOS = 0;
	public static final int MAP_MIN_STARTPOS = 0;
	public static final int MAP_MAX_STARTPOS = 4;

	public static final boolean MAP_DEFAULT_TINYISLES = false;
	public static final boolean MAP_MIN_TINYISLES = false;
	public static final boolean MAP_MAX_TINYISLES = true;

	public static final boolean MAP_DEFAULT_SEPARATE_POLES = true;
	public static final boolean MAP_MIN_SEPARATE_POLES = false;
	public static final boolean MAP_MAX_SEPARATE_POLES = true;

	public static final boolean MAP_DEFAULT_ALLTEMPERATE = false;
	public static final boolean MAP_MIN_ALLTEMPERATE = false;
	public static final boolean MAP_MAX_ALLTEMPERATE = true;

	public static final int MAP_DEFAULT_TEMPERATURE = 50;
	public static final int MAP_MIN_TEMPERATURE = 0;
	public static final int MAP_MAX_TEMPERATURE = 100;

}
