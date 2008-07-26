package common.map;

import common.city.iter_index;

public class civ_map {
//	struct civ_map {
		  public int topology_id;
//		  enum direction8 valid_dirs[8], cardinal_dirs[8];
		  public int num_valid_dirs, num_cardinal_dirs;
		  public iter_index []iterate_outwards_indices;
		  public int num_iterate_outwards_indices;
		  public int size; /* used to calculate [xy]size */
		  public int xsize, ysize; /* native dimensions */
		  public int seed;
		  public int riches;
		  public int huts;
		  public int landpercent;
		  public int generator;
		  public int startpos;
//		  bool tinyisles;
//		  bool separatepoles;
//		  bool alltemperate;
		  public int temperature;
		  public int wetness;
		  public int steepness;
		  public int num_start_positions;
//		  bool have_specials;
//		  bool have_huts;
//		  bool have_rivers_overlay;	/* only applies if !have_specials */
		  public int num_continents;
		  public int num_oceans;               /* not updated at the client */
		  public tile[] tiles;
//
//		  /* Only used by server. */
//		  struct start_position {
//		    struct tile *tile;
//		    int nation; /* May be Nation_H.NO_NATION_SELECTED. */
//		  } *start_positions;	/* allocated at runtime */
//		};


			/**************************************************************************
		Returns the total number of (real) positions (or tiles) on the Map.map.
			 **************************************************************************/
			public int map_num_tiles()
			{
				return xsize * ysize;
			}


}
