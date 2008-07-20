package server.spaceace;

import common.play_spaceship.spaceship_state;

public class player_spaceship {
	public static final int NUM_SS_STRUCTURALS = 32;
	public static final int NUM_SS_COMPONENTS = 16;
	public static final int NUM_SS_MODULES = 12;

	/* how many of each part built, including any "unplaced": */
	public int structurals;
	public int components;
	public int modules;
	/* which structurals placed: (array of booleans) */
	public boolean structure[] = new boolean[NUM_SS_STRUCTURALS];
	/* which components and modules placed: (may or may not be connected) */
	public int fuel;
	public int propulsion;
	public int habitation;
	public int life_support;
	public int solar_panels;
	/* other stuff: */
	public spaceship_state state;
	public int launch_year;
	/* derived quantities: */
	public int population;
	public int mass;
	public float support_rate;
	public float energy_rate;
	public float success_rate;
	public float travel_time;

	class sship_part_info {
		public int x, y; /* position of tile centre */
		public int required; /* required for struct connection */
	}
	
	/***************************************************************************
	 * Initialize spaceship struct; could also be used to "cancel" a spaceship
	 * (eg, if/when capital-capture effect implemented).
	 **************************************************************************/
	public void init() {
		int i;

		structurals = components = modules = 0;
		for (i = 0; i < player_spaceship.NUM_SS_STRUCTURALS; i++) {
			structure[i] = false;
		}
		fuel = propulsion = 0;
		habitation = life_support = solar_panels = 0;
		state = spaceship_state.SSHIP_NONE;
		launch_year = 9999;

		population = mass = 0;
		support_rate = energy_rate = success_rate = travel_time = (float) 0.0;
	}

	/**********************************************************************
	Count the number of structurals placed; that is, in ship->structure[]
	**********************************************************************/
	public int num_spaceship_structurals_placed()
	{
	  int i, num = 0;
	  for(i=0; i<NUM_SS_STRUCTURALS; i++) {
	    if (structure[i]) num++;
	  }
	  return num;
	}

}
