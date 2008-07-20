package common.map;

import static common.Map.*;

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
//	#define CURRENT_TOPOLOGY (map.topology_id)
	public static int CURRENT_TOPOLOGY = map.topology_id;
//	#define topo_has_flag(flag) ((CURRENT_TOPOLOGY & (flag)) != 0)
	public static boolean topo_has_flag(int flag){
		return ((CURRENT_TOPOLOGY & (flag)) != 0);
	}
	
	
//	/* Maximum value of index (for sanity checks and allocations) */
//	#define MAX_MAP_INDEX (map.xsize * map.ysize)
//
//	#ifdef DEBUG
//	#define CHECK_MAP_POS(x,y) assert(is_normal_map_pos((x),(y)))
//	#define CHECK_NATIVE_POS(x, y) assert((x) >= 0 && (x) < map.xsize \
//					      && (y) >= 0 && (y) < map.ysize)
//	#define CHECK_INDEX(index) assert((index) >= 0 && (index) < MAX_MAP_INDEX)
//	#else
//	#define CHECK_MAP_POS(x,y) ((void)0)
//	#define CHECK_NATIVE_POS(x, y) ((void)0)
//	#define CHECK_INDEX(index) ((void)0)
//	#endif
//
//	#define native_pos_to_index(nat_x, nat_y)                                   \
//	  (CHECK_NATIVE_POS((nat_x), (nat_y)),					    \
//	   (nat_x) + (nat_y) * map.xsize)
	public static int native_pos_to_index(int nat_x, int nat_y){                                   
//	  (CHECK_NATIVE_POS((nat_x), (nat_y)),					    
		return (nat_x) + (nat_y) * map.xsize;
	}
//	#define index_to_native_pos(pnat_x, pnat_y, index)                          \
//	  (*(pnat_x) = (index) % map.xsize,                                         \
//	   *(pnat_y) = (index) / map.xsize)
//
//	/* Obscure math.  See explanation in doc/HACKING. */
//	#define NATIVE_TO_MAP_POS(pmap_x, pmap_y, nat_x, nat_y)                     \
//	  (MAP_IS_ISOMETRIC							    \
//	   ? (*(pmap_x) = ((nat_y) + ((nat_y) & 1)) / 2 + (nat_x),                  \
//	      *(pmap_y) = (nat_y) - *(pmap_x) + map.xsize)                          \
//	   : (*(pmap_x) = (nat_x), *(pmap_y) = (nat_y)))
//
//	#define MAP_TO_NATIVE_POS(pnat_x, pnat_y, map_x, map_y)                     \
//	  (MAP_IS_ISOMETRIC							    \
//	   ? (*(pnat_y) = (map_x) + (map_y) - map.xsize,                            \
//	      *(pnat_x) = (2 * (map_x) - *(pnat_y) - (*(pnat_y) & 1)) / 2)          \
//	   : (*(pnat_x) = (map_x), *(pnat_y) = (map_y)))
	public static Point MAP_TO_NATIVE_POS(int pnat_x, int pnat_y, int map_x, int map_y)  {
		int x, y;
		if(MAP_IS_ISOMETRIC()){ 
			y = (map_x) + (map_y) - map.xsize;                           
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
//	      *(pmap_y) = (nat_y) - *(pmap_x) + map.xsize)                          \
//	   : (*(pmap_x) = (nat_x), *(pmap_y) = (nat_y)))
//
//	#define MAP_TO_NATURAL_POS(pnat_x, pnat_y, map_x, map_y)                    \
//	  (MAP_IS_ISOMETRIC							    \
//	   ? (*(pnat_y) = (map_x) + (map_y) - map.xsize,                            \
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
//	#define NATIVE_WIDTH map.xsize
//	#define NATIVE_HEIGHT map.ysize
//
//	/* Width and height of the map, in natural coordinates. */
//	#define NATURAL_WIDTH (MAP_IS_ISOMETRIC ? 2 * map.xsize : map.xsize)
//	#define NATURAL_HEIGHT map.ysize
//
//	#define MAP_WIDTH  \
//	  (MAP_IS_ISOMETRIC ? (map.xsize + map.ysize / 2) : map.xsize)
//	#define MAP_HEIGHT \
//	  (MAP_IS_ISOMETRIC ? (map.xsize + map.ysize / 2) : map.ysize)
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
//	  for (_index = 0; _index < map.num_iterate_outwards_indices; _index++) {   \
//	    if (map.iterate_outwards_indices[_index].dist > _max_dist) {	    \
//	      break;								    \
//	    }									    \
//	    dx_itr = map.iterate_outwards_indices[_index].dx;			    \
//	    dy_itr = map.iterate_outwards_indices[_index].dy;			    \
//	    _x_itr = dx_itr + _start_tile->x;					    \
//	    _y_itr = dy_itr + _start_tile->y;					    \
//	    if (_is_border && !normalize_map_pos(&_x_itr, &_y_itr)) {		    \
//	      continue;								    \
//	    }									    \
//	    tile_itr = map.tiles + map_pos_to_index(_x_itr, _y_itr);

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
		  || ptile.nat_x >= map.xsize - xdist
		  || ptile.nat_y >= map.ysize - ydist);
	}


}
