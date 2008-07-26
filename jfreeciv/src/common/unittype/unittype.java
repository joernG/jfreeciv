package common.unittype;
import utility.shared.Shared_H;

//typedef int Unit_Type_id;
///*
//  Above typedef replaces old "enum unit_type_id" (since no longer
//  enumerate the unit types); keep as typedef to help code be
//  self-documenting.
//
//  It could potentially be some other type; "unsigned char" would be
//  natural, since there are already built-in assumptions that values
//  are not too large (less than U_LAST = Shared_H.MAX_NUM_ITEMS) since they must
//  fit in 8-bit unsigned int for packets; and normal values are always
//  non-negative.  But note sometimes use (-1) for obsoleted_by and some
//  related uses, though these use already plain int rather than
//  Unit_Type_id?  (Ideally, these should probably have used U_LAST as
//  the flag value instead of (-1).)
//  
//  Decided to just use 'int' for several reasons:
//  - "natural integer type" may be faster on some platforms
//    (size advantage of using smaller type probably negligible);
//  - avoids any potential problems with (-1) values as mentioned above;
//  - avoids imposing any more limitations that there are already.
//*/
//  
//#define U_LAST Shared_H.MAX_NUM_ITEMS
///*
//  U_LAST is a value which is guaranteed to be larger than all
//  actual Unit_Type_id values.  It is used as a flag value;
//  it can also be used for fixed allocations to ensure able
//  to hold full number of unit types.
//*/
//
//enum unit_move_type {
//  unit_move_type.LAND_MOVING = 1, unit_move_type.SEA_MOVING, unit_move_type.HELI_MOVING, unit_move_type.AIR_MOVING
//};
//
///* Classes for unit types.
// * (These must correspond to unit_class_names[] in unit.c.)
// */
//enum unit_class_id {
//  UCL_AIR,
//  UCL_HELICOPTER,
//  UCL_LAND,
//  UCL_MISSILE,
//  UCL_NUCLEAR,
//  UCL_SEA,
//  UCL_LAST	/* keep this last */
//};
//
//typedef enum unit_class_id Unit_Class_id;


public class unittype {
	public static int U_LAST = Shared_H.MAX_NUM_ITEMS;
//	struct unit_type {
		  public String name; /* Translated string - doesn't need freeing. */
//		  String name_orig;	      /* untranslated */
		  public String name_orig;
//		  String graphic_str;
//		  String graphic_alt;
//		  String sound_move;
//		  String sound_move_alt;
//		  String sound_fight;
//		  String sound_fight_alt;
//		  struct Sprite *sprite;
		  public unit_move_type move_type;
		  public int build_cost;			/* Use wrappers to access this. */
		  public int pop_cost;  /* number of workers the unit contains (e.g., settlers, engineers)*/
		  public int attack_strength;
		  public int defense_strength;
		  public int move_rate;
		  public int tech_requirement;
		  public int impr_requirement;		/* should be int */
		  public int vision_range;
		  public int transport_capacity;
		  public int hp;
		  public int firepower;
		  public int obsoleted_by;
		  public int fuel;

//		  bv_flags flags;
//		  bv_roles roles;
//
//		  int happy_cost;  /* unhappy people in home city */
//		  int shield_cost; /* normal upkeep cost */
//		  int food_cost;   /* settler food cost */
//		  int gold_cost;   /* gold upkeep */
//
//		  int paratroopers_range; /* only valid for F_PARATROOPERS */
//		  int paratroopers_mr_req;
//		  int paratroopers_mr_sub;
//
//		  /* Additional values for the expanded veteran system */
//		  struct veteran_type veteran[MAX_VET_LEVELS];
//
//		  /* Values for bombardment */
//		  int bombard_rate;
//		  
//		  char *helptext;
//		};
}
