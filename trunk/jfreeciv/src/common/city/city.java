package common.city;

public class city {
//	struct city {
//		  int id;
//		  int owner;
//		  struct tile *tile;
//		  char name[MAX_LEN_NAME];
	public String name;
//
//		  /* the people */
//		  int size;
//
//		  /* How the citizens feel:
//		     ppl_*[0] is distribution before any of the modifiers below.
//		     ppl_*[1] is distribution after luxury.
//		     ppl_*[2] is distribution after after building effects.
//		     ppl_*[3] is distribution after units enfored martial order.
//		     ppl_*[4] is distribution after wonders. (final result.) */
//		  int ppl_happy[5], ppl_content[5], ppl_unhappy[5], ppl_angry[5];
//
//		  /* Specialists */
//		  int specialists[SP_COUNT];
//
//		  /* trade routes */
//		  int trade[NUM_TRADEROUTES], trade_value[NUM_TRADEROUTES];
//
//		  /* the productions */
//		  int food_prod, food_surplus;
//		  /* Shield production is shields produced minus shield_waste*/
//		  int shield_prod, shield_surplus, shield_waste; 
//		  int trade_prod, corruption, tile_trade;
//
//		  /* Cached values for CPU savings. */
//		  int shield_bonus, luxury_bonus, tax_bonus, science_bonus;
//
//		  /* the totals */
//		  int luxury_total, tax_total, science_total;
//		  
//		  /* the physics */
//		  int food_stock;
//		  int shield_stock;
//		  int pollution;
//		  /* city can't be incited if INCITE_IMPOSSIBLE_COST */
//		  int incite_revolt_cost;      
//		   
//		  bool is_building_unit;    /* boolean unit/improvement */
//		  int currently_building;
//		  
//		  Impr_Status improvements[B_LAST];
//
//		  struct worklist worklist;
//
//		  enum city_tile_type city_map[CITY_MAP_SIZE][CITY_MAP_SIZE];
//
//		  struct unit_list units_supported;
//
//		  struct {
//		    /* Only used at the client (the serer is omniscient). */
//		    bool occupied;
//		    bool happy, unhappy;
//
//		    /* The color is an index into the city_colors array in mapview_common */
//		    bool colored;
//		    int color_index;
//		  } client;
//
//		  int steal;		      /* diplomats steal once; for spies, gets harder */
//		  /* turn states */
//		  bool did_buy;
//		  bool did_sell, is_updated;
//		  int turn_last_built;	      /* The last year in which something was built */
//		  int changed_from_id;	      /* If changed this turn, what changed from (id) */
//		  bool changed_from_is_unit;   /* If changed this turn, what changed from (unit?) */
//		  int disbanded_shields;      /* If you disband unit in a city. Count them */
//		  int caravan_shields;        /* If caravan has helped city to build wonder. */
//		  int before_change_shields;  /* If changed this turn, shields before penalty */
//		  int last_turns_shield_surplus; /* The surplus we had last turn. */
//		  int anarchy;		      /* anarchy rounds count */ 
//		  int rapture;                /* rapture rounds count */ 
//		  bool was_happy;
//		  bool airlift;
//		  int original;			/* original owner */
//		  int city_options;		/* bitfield; positions as enum city_options */
//
//		  /* server variable. indicates if the city map is synced with the client. */
//		  bool synced;
//		  struct {
//		    /* If > 0, workers will not be rearranged until they are unfrozen. */
//		    int workers_frozen;
//
//		    /* If set, workers need to be arranged when the city is unfrozen.  Only
//		     * set inside auto_arrange_workers. */
//		    bool needs_arrange;
//		  } server;
//
//		  int turn_founded;		/* In which turn was the city founded? */
//
//		  /* info for dipl/spy investigation -- used only in client */
//		  struct unit_list info_units_supported;
//		  struct unit_list info_units_present;
//
//		  struct ai_city ai;
//		  bool debug;
//		};

}
