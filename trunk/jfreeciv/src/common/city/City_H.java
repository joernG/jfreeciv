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

}
