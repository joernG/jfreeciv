package common.nation;

import common.Terrain_H;

public class city_name {
	/*
	 * The city_name structure holds information about a default choice for
	 * the city name.  The "name" field is, of course, just the name for
	 * the city.  The "river" and "terrain" fields are entries recording
	 * whether the terrain is present near the city - we give higher priority
	 * to cities which have matching terrain.  In the case of a river we only
	 * care if the city is _on_ the river, for other terrain features we give
	 * the bonus if the city is close to the terrain.  Both of these entries
	 * may hold a value of 0 (no preference), 1 (city likes the terrain), or -1
	 * (city doesn't like the terrain).
	 *
	 * This is controlled through the nation's ruleset like this:
	 *   cities = "Washington (ocean, river, swamp)", "New York (!mountains)"
	 */
//	typedef int ternary;
//	struct city_name {
	  public String name;
//	  ternary river;
//	  ternary terrain[MAX_NUM_TERRAINS];
	  public int river;
	  public int terrain[]= new int[Terrain_H.MAX_NUM_TERRAINS];
//	};
}
