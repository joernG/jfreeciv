package common.map;

import utility.Speclists;

import common.city.city;
import common.player.player;
import common.unit.unit;

public class tile {
//	/*
//	 * The value of MOVE_COST_FOR_VALID_SEA_STEP has no particular
//	 * meaning. The value is only used for comparison. The value must be
//	 * <0.
//	 */
//	#define MOVE_COST_FOR_VALID_SEA_STEP	(-3)
//	#define MOVE_COST_FOR_VALID_AIR_STEP	(-3)


	/* Convenience macro for accessing tile coordinates.  This should only be
	 * used for debugging. */
//	#define TILE_XY(ptile) ((ptile) ? (ptile)->x : -1), \
//	                       ((ptile) ? (ptile)->y : -1)

//	struct tile {
	  public int x, y; /* Cartesian (map) coordinates of the tile. */
	  public int nat_x, nat_y; /* Native coordinates of the tile. */
	  public int index; /* Index coordinate of the tile. */
	  public int terrain;
//	  public int special;
	  public int special;
	  public city city;
//	  Speclists<unit> units;
	  public Speclists<unit> units;
//	  unsigned int known;   
	/*
	 * A bitvector on the server side, an enum known_type on the client side.
	 * Player_no is index
	 */
	  public int known;
	  public int assigned; /* these can save a lot of CPU usage -- Syela */
	  public city worked;      /* city working tile, or NULL if none */
//	  public Continent_id continent;
	  public int move_cost[]=new int [8]; /* don't know if this helps! */
	  public player owner;     /* Player owning this tile, or NULL. */
//	  struct {
//	    /* Area Selection in client. */
//	    public tile_hilite hilite;
//	  } client;
//	  char *spec_sprite;
//	};

}
