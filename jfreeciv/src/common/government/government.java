package common.government;

public class government {
	/* This is struct government itself.  All information about
	 * a form of government is contained inhere.
	 * -- SKi */
//	struct government
//	{
	  public int   index;			/* index into governments[] array */
//	  const char *name; /* Translated string - doesn't need freeing. */
//	  String name_orig; /* untranslated copy */
//	  String graphic_str;
//	  String graphic_alt;
	  public int   required_tech;		/* tech required to change to this gov */
//
//	  struct ruler_title *ruler_titles;
	  public int   num_ruler_titles;

	  public int   max_rate;		/* max individual Tax/Lux/Sci rate  */
	  public int   civil_war;              /* chance (from 100) of civil war in
					   right conditions */
	  public int   martial_law_max;	/* maximum number of units which can
					   enforce martial law */
	  public int   martial_law_per;        /* number of unhappy citizens made
					   content by each enforcer unit */
	  public int   empire_size_mod;	/* (signed) offset to game.cityfactor to
					   give city count when number of naturally
					   content citizens is decreased */
	  public int   empire_size_inc;	/* if non-zero, reduce one content citizen for
					   every empire_size_inc cities once #cities
					   exceeds game.cityfactor + empire_size_mod */
	  public int   rapture_size;		/* minimum city size for rapture; if 255,
					   rapture is (practically) impossible */

	  /* unit cost modifiers */
	  public int   unit_happy_cost_factor;
	  public int   unit_shield_cost_factor;
	  public int   unit_food_cost_factor;
	  public int   unit_gold_cost_factor;
	  
	  /* base cost that a city does not have to "pay" for */
	  public int   free_happy;
	  public int   free_shield;
	  public int   free_food;
	  public int   free_gold;
	  
	  /* government production penalties -- SKi */
	  public int   trade_before_penalty;
	  public int   shields_before_penalty;
	  public int   food_before_penalty;

	  /* government production penalties when celebrating */
	  public int   celeb_trade_before_penalty;
	  public int   celeb_shields_before_penalty;
	  public int   celeb_food_before_penalty;

	  /* government production bonuses -- SKi */
	  public int   trade_bonus;
	  public int   shield_bonus;
	  public int   food_bonus;

	  /* government production bonuses when celebrating */
	  public int   celeb_trade_bonus;
	  public int   celeb_shield_bonus;
	  public int   celeb_food_bonus;

	  /* corruption modifiers -- SKi */
	  public int   corruption_level;
	  public int   corruption_modifier;
	  public int   fixed_corruption_distance;
	  public int   corruption_distance_factor;
	  public int   extra_corruption_distance;
	  public int   corruption_max_distance_cap;
	  
	  /* waste modifiers, see governments.ruleset for more detail */
	  public int   waste_level;
	  public int   waste_modifier;
	  public int   fixed_waste_distance;
	  public int   waste_distance_factor;
	  public int   extra_waste_distance;
	  public int   waste_max_distance_cap;
	    
	  /* other flags: bits in enum government_flag_id order,
	     use government_has_flag() to access */
	  public int   flags;

//	  struct Sprite *sprite;
//	  
//	  char *helptext;
//	};

}
