package port;

import java.util.List;

import common.map.tile;

public class util {
	//TODO
	public static void fc_printf(String format, Object ... message){
	}
	public static String my_snprintf(String format, Object ... message){
		return String.format(format, message);
	}
	public static void freelog(int logError, String string, Object ...args)
	{
	}
	public static void die(String msg){
	}
	public static boolean isLetter(char ch){
		return Character.isLetter(ch);
	}
	public static boolean isLetter(String s){
		for(int i=0; i<=s.length(); i++){
			char ch = s.charAt(i);
			if(! Character.isLetter(ch)) return false;
		}
		return true;
	}
//	/* Iterate through all map positions adjacent to the given center map
//	 * position, with normalization.  The order of positions is unspecified. */
//	#define adjc_iterate(center_tile, itr_tile)				    \
//	{									    \
//	  /* Written as a wrapper to adjc_dir_iterate since it's the cleanest and   \
//	   * most efficient. */							    \
//	  adjc_dir_iterate(center_tile, itr_tile, ADJC_ITERATE_dir_itr) {
//
//	#define adjc_iterate_end                                                    \
//	  } adjc_dir_iterate_end;                                                   \
//	}
//
//	#define adjc_dir_iterate(center_tile, itr_tile, dir_itr)		    \
//	  adjc_dirlist_iterate(center_tile, itr_tile, dir_itr,			    \
//			       map.valid_dirs, map.num_valid_dirs)
//
//	#define adjc_dir_iterate_end adjc_dirlist_iterate_end
//
//	#define cardinal_adjc_iterate(center_tile, itr_tile)			    \
//	  adjc_dirlist_iterate(center_tile, itr_tile, _dir_itr,			    \
//			       map.cardinal_dirs, map.num_cardinal_dirs)
//
//	#define cardinal_adjc_iterate_end adjc_dirlist_iterate_end
//
//	#define cardinal_adjc_dir_iterate(center_tile, itr_tile, dir_itr)	    \
//	  adjc_dirlist_iterate(center_tile, itr_tile, dir_itr,			    \
//			       map.cardinal_dirs, map.num_cardinal_dirs)
//
//	#define cardinal_adjc_dir_iterate_end adjc_dirlist_iterate_end
//
//	/* Iterate through all tiles adjacent to a tile using the given list of
//	 * directions.  dir_itr is the directional value, (center_x, center_y) is
//	 * the center tile (which must be normalized), and (x_itr, y_itr) is the
//	 * position corresponding to dir_itr.
//	 *
//	 * This macro should not be used directly.  Instead, use adjc_dir_iterate
//	 * or cartesian_adjacent_iterate. */
//	#define adjc_dirlist_iterate(center_tile, itr_tile, dir_itr,		    \
//				     dirlist, dircount)				    \
//	{									    \
//	  const struct tile *_center_tile = (center_tile);			    \
//	  struct tile *itr_tile;						    \
//	  int _dir_index, _x_itr, _y_itr;					    \
//	  enum direction8 dir_itr;						    \
//	  bool _is_border = is_border_tile(_center_tile, 1);			    \
//										    \
//	  for (_dir_index = 0; _dir_index < (dircount); _dir_index++) {		    \
//	    dir_itr = dirlist[_dir_index];					    \
//	    DIRSTEP(_x_itr, _y_itr, dir_itr);					    \
//	    _x_itr += _center_tile->x;						    \
//	    _y_itr += _center_tile->y;						    \
//	    if (_is_border && !normalize_map_pos(&_x_itr, &_y_itr)) {		    \
//	      continue;								    \
//	    }									    \
//	    itr_tile = map.tiles + map_pos_to_index(_x_itr, _y_itr);
//
//	#define adjc_dirlist_iterate_end					    \
//	    }									    \
//	}

	public static List<tile> adjc_tile_iterate(tile ptile) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	public static List<tile> square_tile_iterate(tile tile0, int i) {
		// TODO Auto-generated method stub
		return null;
	}
}
