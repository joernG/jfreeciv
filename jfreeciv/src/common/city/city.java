package common.city;

import utility.Speclists;
import common.worklist;
import common.map.tile;
import common.unit.unit;

public class city {
	// struct city {
	public int id;
	public int owner;
	public tile tile;
	// String name="";
	public String name;

	/* the people */
	public int size;

	/*
	 * How the citizens feel: ppl_*[0] is distribution before any of the
	 * modifiers below. ppl_*[1] is distribution after luxury. ppl_*[2] is
	 * distribution after after building effects. ppl_*[3] is distribution after
	 * units enfored martial order. ppl_*[4] is distribution after wonders.
	 * (final result.)
	 */
	// public int ppl_happy[5], ppl_content[5], ppl_unhappy[5], ppl_angry[5];
	
	 /* Specialists */
	 public int specialists[] = new int [specialist_type.getSize()];
	
	// /* trade routes */
	// public int trade[NUM_TRADEROUTES], trade_value[NUM_TRADEROUTES];
	
	 /* the productions */
	 public int food_prod, food_surplus;
	 /* Shield production is shields produced minus shield_waste*/
	 public int shield_prod, shield_surplus, shield_waste;
	 public int trade_prod, corruption, tile_trade;
	
	 /* Cached values for CPU savings. */
	 public int shield_bonus, luxury_bonus, tax_bonus, science_bonus;
	
	 /* the totals */
	 public int luxury_total, tax_total, science_total;
			  
	 /* the physics */
	 public int food_stock;
	 public int shield_stock;
	 public int pollution;
	 /* city can't be incited if INCITE_IMPOSSIBLE_COST */
	 public int incite_revolt_cost;
			   
	public boolean is_building_unit; /* boolean unit/improvement */
	 public int currently_building;
			  
	// int improvements[B_LAST];
	
	 public worklist worklist;
	
	public city_tile_type city_map[][] = new city_tile_type[City_H.CITY_MAP_SIZE][City_H.CITY_MAP_SIZE];
	//
	// Speclists<unit> units_supported;
	//
	// struct {
	// /* Only used at the client (the serer is omniscient). */
	// bool occupied;
	// bool happy, unhappy;
	//
	// /* The color is an index public into the city_colors array in
	// mapview_common */
	// bool colored;
	// public int color_index;
	// } client;
	//
	 public int steal; /* diplomats steal once; for spies, gets harder */
	/* turn states */
	public boolean did_buy;
	public boolean did_sell, is_updated;
	public int turn_last_built; /*
								 * The last year in which something was built
								 */
	public int changed_from_id; /*
								 * If changed this turn, what changed from (id)
								 */
	public boolean changed_from_is_unit; /*
									 * If changed this turn, what changed from
									 * (unit?)
									 */
	public int disbanded_shields; /*
									 * If you disband unit in a city. Count them
									 */
	public int caravan_shields; /*
								 * If caravan has helped city to build wonder.
								 */
	public int before_change_shields; /*
										 * If changed this turn, shields before
										 * penalty
										 */
	public int last_turns_shield_surplus; /* The surplus we had last turn. */
	public int anarchy; /* anarchy rounds count */
	public int rapture; /* rapture rounds count */
	public boolean was_happy;
	public boolean airlift;
	public int original; /* original owner */
	public int city_options; /* bitfield; positions as enum city_options */
	
	 /* server variable. indicates if the city map is synced with the client.
	 */
	public boolean synced;
	// struct {
	// /* If > 0, workers will not be rearranged until they are unfrozen. */
	// public int workers_frozen;
	//
	// /* If set, workers need to be arranged when the city is unfrozen. Only
	// * set inside Cityturn.auto_arrange_workers. */
	// bool needs_arrange;
	// } server;

	public int turn_founded; /* In which turn was the city founded? */

	/* info for dipl/spy investigation -- used only in client */
	public Speclists<unit> info_units_supported;
	public Speclists<unit> info_units_present;

	// struct ai_city ai;
	//		  bool debug;
	//		};
	
	/**************************************************************************
	  Return true iff this city is its nation's capital.  The capital city is
	  special-cased in a number of ways.
	**************************************************************************/
	public boolean is_capital()
	{
		//TODO
//	  return (get_city_bonus(pcity, EFT_CAPITAL_CITY) != 0);
		return false;
	}
	

}
