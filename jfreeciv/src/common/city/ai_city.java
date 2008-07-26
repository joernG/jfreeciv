package common.city;

public class ai_city {
	//	struct ai_city {
	/* building desirabilities - easiest to handle them here -- Syela */
	public int  building_want[];//[B_LAST];    /* not sure these will always be < 256 */

	public int  danger;          /* danger to be compared to assess_defense */
	public boolean  diplomat_threat;         /* enemy diplomat or spy is near the city */
	public boolean  has_diplomat;            /* this city has diplomat or spy defender */
	public int  urgency;         /* how close the danger is; if zero, 
		                                   bodyguards can leave */
	public int  grave_danger;    /* danger, should show positive feedback */
	public int  wallvalue;                /* how much it helps for defenders to be 
		                                   ground units */
	public int  trade_want;               /* saves a zillion calculations */
	public ai_choice choice;      /* to spend gold in the right place only */
	public int  downtown;                 /* distance from neighbours, for locating 
		                                   wonders wisely */
	public int  distance_to_wonder_city;  /* wondercity will set this for us, 
		                                   avoiding paradox */
	public boolean  celebrate;               /* try to celebrate in this city */

	/* Used for caching when settlers evalueate which tile to improve,
		     and when we place workers. */
	public int  detox[][];//[CITY_MAP_SIZE][CITY_MAP_SIZE];
	public int  derad[][];//[CITY_MAP_SIZE][CITY_MAP_SIZE];
	public int  mine[][];//[CITY_MAP_SIZE][CITY_MAP_SIZE];
	public int  irrigate[][];//[CITY_MAP_SIZE][CITY_MAP_SIZE];
	public int  road[][];//[CITY_MAP_SIZE][CITY_MAP_SIZE];
	public int  railroad[][];//[CITY_MAP_SIZE][CITY_MAP_SIZE];
	public int  transform[][];//[CITY_MAP_SIZE][CITY_MAP_SIZE];
	public int  tile_value[][];//[CITY_MAP_SIZE][CITY_MAP_SIZE];

	/* so we can contemplate with warmap fresh and decide later */
	public int  settler_want, founder_want; /* for builder (F_SETTLERS) and founder (F_CITIES) */
	public int  next_founder_want_recalc; /* do not recalc founder_want every turn */
	public boolean  founder_boat; /* if the city founder will need a boat */
	public int  invasion; /* who's coming to kill us, for attack co-ordination */
	public int  attack, bcost; /* This is also for invasion - total power and value of
	 * all units coming to kill us. */

	public int  worth; /* Cache city worth here, sum of all weighted incomes */
	public int  next_recalc; /* Only recalc every Nth turn */
	//		};

}
