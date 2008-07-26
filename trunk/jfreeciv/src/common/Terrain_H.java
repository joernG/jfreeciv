package common;

import utility.shared.Shared_H;
import common.terrian.terrain_flag_id;

public class Terrain_H {
//	enum special_river_move {
//		  RMV_NORMAL=0, RMV_FAST_STRICT=1, RMV_FAST_RELAXED=2, RMV_FAST_ALWAYS=3
//		};
//
//		enum tile_special_type {
		  public static int S_NO_SPECIAL = 0;
		  public static int S_SPECIAL_1  =    1;
		  public static int S_ROAD = 2;
		  public static int S_IRRIGATION = 4;
		  public static int S_RAILROAD = 8;
		  public static int S_MINE = 16;
		  public static int S_POLLUTION = 32;
		  public static int S_HUT = 64;
		  public static int S_FORTRESS = 128;
		  public static int S_SPECIAL_2  =  256;
		  public static int S_RIVER = 512;
		  public static int S_FARMLAND = 1024;
		  public static int S_AIRBASE = 2048;
		  public static int S_FALLOUT = 4096;
//		};
//
//		#define S_ALL    \
//		 (  S_SPECIAL_1  \
//		  | S_ROAD       \
//		  | S_IRRIGATION \
//		  | S_RAILROAD   \
//		  | S_MINE       \
//		  | S_POLLUTION  \
//		  | S_HUT        \
//		  | S_FORTRESS   \
//		  | S_SPECIAL_2  \
//		  | S_RIVER      \
//		  | S_FARMLAND   \
//		  | S_AIRBASE    \
//		  | S_FALLOUT)
//
//		#define S_INFRASTRUCTURE_MASK \
//		  (S_ROAD                   \
//		   | S_RAILROAD             \
//		   | S_IRRIGATION           \
//		   | S_FARMLAND             \
//		   | S_MINE                 \
//		   | S_FORTRESS             \
//		   | S_AIRBASE)

		public static final int T_NONE = (-3); /* A special flag meaning no terrain type. */
		public static final int T_ANY = (-2); /* A special flag that matches "any" terrain type. */
		public static final int T_UNKNOWN = (-1); /* An unknown terrain. */

		/* The first terrain value and number of base terrains.  This is used in
		 * loops.  T_COUNT may eventually be turned into a variable. */
		public static final int T_FIRST = 0;
		public static final int T_COUNT = (Game.game.terrain_count);

		/* A hard limit on the number of terrains; useful for static arrays. */
		public static final int MAX_NUM_TERRAINS = Shared_H.MAX_NUM_ITEMS;
//		public static final int TER_FIRST = (TER_NO_BARBS);
//		public static final int TER_COUNT = (TER_LAST);
		public static final int TER_MAX = 64; /* Changing this breaks network compatability. */

//		enum known_type {
//		 TILE_UNKNOWN, TILE_KNOWN_FOGGED, TILE_KNOWN
//		};
		  /* Terrain-specific functions. */
		  public static boolean is_ocean(int x) {
			   return (terrain_has_flag((x), terrain_flag_id.TER_OCEANIC));
		  }

//		  #define terrain_has_flag(terr, flag)		\
//		  BV_ISSET(get_tile_type(terr)->flags, flag)
		  public static boolean terrain_has_flag(int terr, terrain_flag_id flag){
			  //	  BV_ISSET(get_tile_type(terr)->flags, flag)
			  return false;
}
}
