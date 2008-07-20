package common.unit;

import common.map.tile;

public class unit {
//	struct unit {
//	int type;
	public int type;
	public int id;
	public int owner;
	public tile tile;
	public int homecity;
	public int moves_left;
	public int hp;
	public int veteran;
	public int unhappiness;
	public int upkeep;
	public int upkeep_food;
	public int upkeep_gold;
	public int fuel;
	public int bribe_cost;
	public unit_ai ai;
	public unit_activity activity;
	public tile goto_tile; /* May be NULL. */

	/* The amount of work that has been done on the current activity.  This
	 * is counted in turns but is multiplied by ACTIVITY_COUNT (which allows
	 * fractional values in some cases). */
	public int activity_count;

//	public tile_special_type activity_target;
	public int activity_target;
//	enum unit_focus_status focus_status;
	public int ord_map, ord_city;
	/* ord_map and ord_city are the order index of this unit in tile.units
		     and city.units_supported; they are only used for save/reload */
	public boolean foul;
	public boolean debug;
	public boolean moved;
	public boolean paradropped;

	/* This value is set if the unit is done moving for this turn. This
	 * information is used by the client.  The invariant is:
	 *   - If the unit has no more moves, it's done moving.
	 *   - If the unit is on a goto but is waiting, it's done moving.
	 *   - Otherwise the unit is not done moving. */
	public boolean done_moving;

	public int transported_by;
	public int occupy; /* number of units that occupy transporter */
//	struct {
//		/* Equivalent to pcity.client.color.  Only for F_CITIES units. */
//		public boolean colored;
//		public int color_index;
//	} client;
//
//	public boolean has_orders;
//	struct {
//		public int length, index;
//		public boolean repeat;	/* The path is to be repeated on completion. */
//		public boolean vigilant;	/* Orders should be cleared if an enemy is met. */
//		unit_order list;
//	} orders;
//	};

}
