package common;

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
//
//		#define T_NONE (-3) /* A special flag meaning no terrain type. */
//		#define T_ANY (-2) /* A special flag that matches "any" terrain type. */
//		#define T_UNKNOWN (-1) /* An unknown terrain. */
//
//		/* The first terrain value and number of base terrains.  This is used in
//		 * loops.  T_COUNT may eventually be turned into a variable. */
//		#define T_FIRST 0
//		#define T_COUNT (game.terrain_count)
//
//		/* A hard limit on the number of terrains; useful for static arrays. */
//		#define MAX_NUM_TERRAINS MAX_NUM_ITEMS
//
//		/* Must match with terrain_flag_from_str in terrain.c. */
//		enum terrain_flag_id {
//		  TER_NO_BARBS, /* No barbarians summoned on this terrain. */
//		  TER_NO_POLLUTION, /* This terrain cannot be polluted. */
//		  TER_NO_CITIES, /* No cities on this terrain. */
//		  TER_STARTER, /* Players will start on this terrain type. */
//		  TER_CAN_HAVE_RIVER, /* Terrains with this type can have S_RIVER on them. */
//		  TER_UNSAFE_COAST,/*this tile is not safe as coast, (all ocean / ice) */ 
//		  TER_UNSAFE,  /*unsafe for all units (ice,...) */
//		  TER_OCEANIC, /* This is an ocean terrain. */
//		  TER_LAST
//		};
//		#define TER_FIRST (TER_NO_BARBS)
//		#define TER_COUNT (TER_LAST)
//		#define TER_MAX 64 /* Changing this breaks network compatability. */
//
//		enum known_type {
//		 TILE_UNKNOWN, TILE_KNOWN_FOGGED, TILE_KNOWN
//		};
}
