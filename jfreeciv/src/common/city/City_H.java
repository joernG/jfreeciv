package common.city;

public class City_H {
	/* first four bits are for auto-attack: */
	public static final int CITYOPT_AUTOATTACK_BITS =0xF;

	/* for new city: default auto-attack options all on, others off: */
	public static final int CITYOPT_DEFAULT = (CITYOPT_AUTOATTACK_BITS);


	/* Changing this requires updating CITY_TILES and network capabilities. */
	public static final int CITY_MAP_RADIUS = 2;

	/* Diameter of the workable city area.  Some places harcdode this number. */
	public static final int CITY_MAP_SIZE = (CITY_MAP_RADIUS * 2 + 1) ;

	/* Number of tiles a city can use */
//	public static final int CITY_TILES = city_tiles;

	public static final int INCITE_IMPOSSIBLE_COST = (1000 * 1000 * 1000);

	/*
	 * Number of traderoutes a city can have.
	 */
	public static final int NUM_TRADEROUTES	=	4;

	/*
	 * Size of the biggest possible city.
	 *
	 * The finalant may be changed since it isn't externally visible.
	 */
	public static final int MAX_CITY_SIZE			=		100;

//	/*
//	 * Iterate a city Map.map.  This iterates over all city positions in the
//	 * city map (i.e., positions that are workable by the city) in unspecified
//	 * order.
//	 */
//	#define city_map_iterate(x, y)						    \
//	{									    \
//	  int _itr;								    \
//	  									    \
//	  for (_itr = 0; _itr < CITY_MAP_SIZE * CITY_MAP_SIZE; _itr++) {	    \
//	    final int x = _itr % CITY_MAP_SIZE, y = _itr / CITY_MAP_SIZE;	    \
//	    									    \
//	    if (is_valid_city_coords(x, y)) {
//
//	#define city_map_iterate_end			                            \
//	    }									    \
//	  }									    \
//	}

}
