package common.unit;

import common.Game;
import common.Unittype_P;
import common.map.tile;
import common.player.player;
import common.unittype.unittype;

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

//	public int activity_target;
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
	public player unit_owner() {
		return (Game.game.players[owner]);
	}
	public int move_rate() {
		// TODO Auto-generated method stub
		int move_rate = 0;
//		int base_move_rate = punit.unit_type().move_rate
//				+ punit.unit_type().veteran[punit.veteran].move_bonus;
//
//		switch (punit.unit_type().move_type) {
//		case unit_move_type.LAND_MOVING:
//			move_rate = (base_move_rate * punit.hp) / punit.unit_type().hp;
//			break;
//
//		case unit_move_type.SEA_MOVING:
//			move_rate = (base_move_rate * punit.hp) / punit.unit_type().hp;
//
//			move_rate += (get_player_bonus(punit.unit_owner(), EFT_SEA_MOVE) * Unit_H.SINGLE_MOVE);
//
//			if (Player_P.player_knows_techs_with_flag(punit.unit_owner(), TF_BOAT_FAST)) {
//				move_rate += Unit_H.SINGLE_MOVE;
//			}
//
//			if (move_rate < 2 * Unit_H.SINGLE_MOVE) {
//				move_rate = Math.min(2 * Unit_H.SINGLE_MOVE, base_move_rate);
//			}
//			break;
//
//		case unit_move_type.HELI_MOVING:
//		case unit_move_type.AIR_MOVING:
//			move_rate = base_move_rate;
//			break;
//
//		default:
//			util.die("In common/unit.c:unit_move_rate: illegal move type %d",
//					punit.unit_type().move_type);
//		}
//
//		if (move_rate < Unit_H.SINGLE_MOVE && base_move_rate > 0) {
//			move_rate = Unit_H.SINGLE_MOVE;
//		}
		return move_rate;
	}
	/***************************************************************************
	 * ...
	 **************************************************************************/
	public static unittype get_unit_type(int id)
	{
		assert(id >= 0 && id < unittype.U_LAST && id < Game.game.num_unit_types);
		return Unittype_P.unit_types[id];
	}

	/***************************************************************************
	 * ...
	 **************************************************************************/
	public unittype unit_type() {
		return get_unit_type(type);
	}


}
