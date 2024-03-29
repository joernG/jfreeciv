package common;
import common.city.city;
import common.map.tile;
import common.player.player;
import common.unit.unit;

public class Combat{
//#include "log.h"
//#include "map.h"
//#include "packets.h"
//#include "unit.h"
//
//#include "combat.h"

	/***************************************************************************
	 * Checks if player is restricted diplomatically from attacking the tile.
	 * Returns FLASE if 1) the tile is empty or 2) the tile contains a non-enemy
	 * city or 3) the tile contains a non-enemy unit
	 **************************************************************************/
	static boolean can_player_attack_tile(player pplayer, final tile ptile)
	{
		city pcity = ptile.city;

		/* 1. Is there anyone there at all? */
		if (null==pcity && (ptile.units.foo_list_size()) == 0) {
			return false;
		}

		/* 2. If there is a city there, can we attack it? */
		if (pcity!=null && !Player_P.pplayers_at_war(City.city_owner(pcity), pplayer)) {
			return false;
		}

		/* 3. Are we allowed to attack _all_ units there? */
		for (unit aunit : ptile.units.data) {
			if (!Player_P.pplayers_at_war(aunit.unit_owner(), pplayer)) {
				/* Enemy hiding behind a human/diplomatic shield */
				return false;
			}
		} 

		return true;
	}

	/***************************************************************************
	 * Checks if a unit can physically attack pdefender at the tile (assuming it
	 * is adjacent and at war).
	 * 
	 * Unit can NOT attack if: 1) it does not have any attack power. 2) it is
	 * not a fighter and defender is a flying unit (except city/airbase). 3) it
	 * is a ground unit without marine ability and it attacks from ocean. 4) it
	 * is a ground unit and it attacks a target on an ocean square. 5) it is a
	 * sailing unit without shore bombardment capability and it attempts to
	 * attack land.
	 * 
	 * Does NOT check: 1) Moves left 2) Adjacency 3) Diplomatic status
	 **************************************************************************/
	static boolean can_unit_attack_unit_at_tile(unit punit, unit pdefender,
			final tile dest_tile) {
//		int fromtile = punit.tile.terrain;
//		int totile = dest_tile.terrain;
//		city pcity = dest_tile.city;
//
//		/* 1. Can we attack _anything_ ? */
//		if (!is_military_unit(punit) || !is_attack_unit(punit)) {
//			return false;
//		}
//
//		/* 2. Only fighters can attack planes, except in city or airbase attacks */
//		if (!unit_flag(punit, F_FIGHTER) && is_air_unit(pdefender)
//				&& !(pcity || Map.map_has_special(dest_tile, Terrain_H.S_AIRBASE))) {
//			return false;
//		}
//
//		/* 3. Can't attack with ground unit from ocean, except for marines */
//		if (Terrain_H.is_ocean(fromtile) && is_ground_unit(punit)
//				&& !unit_flag(punit, F_MARINES)) {
//			return false;
//		}
//
//		/* 4. Ground units cannot attack water units */
//		if (Terrain_H.is_ocean(totile) && is_ground_unit(punit)) {
//			return false;
//		}
//
//		/* 5. Shore bombardement can be done by certain units only */
//		if (unit_flag(punit, F_NO_LAND_ATTACK) && !Terrain_H.is_ocean(totile)) {
//			return false;
//		}

		return true;
	}

	/***************************************************************************
	 * To attack a stack, unit must be able to attack every unit there (not
	 * including transported units).
	 **************************************************************************/
	public static boolean can_unit_attack_all_at_tile(unit punit,
			final tile ptile)
	{
		for (unit aunit : ptile.units.data) {
			/*
			 * HACK: we don't count transported units here. This prevents some
			 * bugs like a submarine carrying a cruise missile being
			 * invulnerable to other sea units. However from a gameplay
			 * perspective it's a hack, since players can load and unload their
			 * units manually to protect their transporters.
			 */
			if (aunit.transported_by == -1
					&& !can_unit_attack_unit_at_tile(punit, aunit, ptile)) {
				return false;
			}
		}

		return true;
	}

	/***************************************************************************
	 * Is unit (1) diplomatically allowed to attack and (2) physically able to
	 * do so?
	 **************************************************************************/
	public static boolean can_unit_attack_tile(unit punit, final tile dest_tile) {
		if (!can_player_attack_tile(punit.unit_owner(), dest_tile)) {
			return false;
		}

		return can_unit_attack_all_at_tile(punit, dest_tile);
	}

// /***********************************************************************
//Returns the chance of the attacker winning, a number between 0 and 1.
//If you want the chance that the defender wins just use 1-chance(...)
//
//NOTE: this number can be _very_ small, fx in a battle between an
//ironclad and a battleship the ironclad has less than 1/100000 chance of
//winning.
//
//The algoritm calculates the probability of each possible number of HP's
//the attacker has left. Maybe that info should be preserved for use in
//the AI.
//***********************************************************************/
//double win_chance(int as, int ahp, int afp, int ds, int dhp, int dfp)
//{
//  /* number of rounds a unit can fight without dying */
//  int att_N_lose = (ahp + dfp - 1) / dfp;
//  int def_N_lose = (dhp + afp - 1) / afp;
//  /* Probability of losing one round */
//  double att_P_lose1 = (as + ds == 0) ? 0.5 : (double) ds / (as + ds);
//  double def_P_lose1 = 1 - att_P_lose1;
//
//  /*
//    This calculates 
//
//    binomial_coeff(def_N_lose-1 + lr, lr)
//      * def_P_lose1^(def_N_lose-1)
//      * att_P_lose1^(lr)
//      * def_P_lose1
//
//    for each possible number of rounds lost (rl) by the winning unit.
//    rl is of course less than the number of rounds the winning unit
//    should lose to lose all it's hit points.
//    The probabilities are then summed.
//
//    To see this is correct consider the set of series for all valid fights.
//    These series are the type (win, lose, lose...). The possible lenghts are
//    def_N_lose to def_N_lose+att_N_lose-1. A series is not valid unless it
//    contains def_N_lose wins, and one of the wins must be the last one, or
//    the series would be equivalent the a shorter series (the attacker would
//    have won one or more fights previously).
//    So since the last fight is a win we disregard it while calculating. Now
//    a series contains def_N_lose-1 wins. So for each possible lenght of a
//    series we find the probability of every valid series and then sum.
//    For a specific lenght (a "lr") every series have the probability
//      def_P_lose1^(def_N_lose-1) * att_P_lose1^(lr)
//    and then getting from that to the real series requires a win, ie factor
//    def_N_lose. The number of series with lenght (def_N_lose-1 + lr) and
//    "lr" lost fights is
//      binomial_coeff(def_N_lose-1 + lr, lr)
//    And by multiplying we get the formula on the top of this code block.
//    Adding the cumulative probability for each valid lenght then gives the
//    total probability.
//
//    We clearly have all valid series this way. To see that we have counted
//    none twice note that would require a series with a smaller series inbedded.
//    But since the smaller series already included def_N_lose wins, and the
//    larger series ends with a win, it would have too many wins and therefore
//    cannot exist.
//
//    In practice each binomial coefficient for a series lenght can be calculated
//    from the previous. In the coefficient (n, k) n is increased and k is
//    unchanged.
//    The "* def_P_lose1" is multiplied on the sum afterwards.
//
//    (lots of talk for so little code)
//  */
//
//  double binom_save = pow(def_P_lose1, (double)(def_N_lose - 1));
//  double accum_prob = binom_save; /* lr = 0 */
//
//  int lr; /* the number of Lost Rounds by the attacker */
//  for (lr = 1; lr < att_N_lose; lr++) {
//    /* update the coefficient */
//    int n = lr + def_N_lose - 1;
//    binom_save *= n;
//    binom_save /= lr;
//    binom_save *= att_P_lose1;
//    /* use it for this lr */
//    accum_prob += binom_save;
//  }
//  /* Every element of the sum needs a factor for the very last fight round */
//  accum_prob *= def_P_lose1;
//
//  return accum_prob;
//}
//
///**************************************************************************
//A unit's effective firepower depend on the situation.
//**************************************************************************/
//void get_modified_firepower(unit attacker, unit defender,
//			    int *att_fp, int *def_fp)
//{
//  *att_fp = attacker.unit_type().firepower;
//  *def_fp = defender.unit_type().firepower;
//
//  /* Check CityBuster flag */
//  if (unit_flag(attacker, F_CITYBUSTER)
//      && Map.map_get_city(defender.tile)) {
//    *att_fp *= 2;
//  }
//
//  /* pearl harbour - defender's firepower is reduced to one, 
//   *                 attacker's is multiplied by two         */
//  if (Unit.is_sailing_unit(defender) && Map.map_get_city(defender.tile)) {
//    *att_fp *= 2;
//    *def_fp = 1;
//  }
//  
//  /* 
//   * When attacked by fighters, helicopters have their firepower
//   * reduced to 1.
//   */
//  if (is_heli_unit(defender) && unit_flag(attacker, F_FIGHTER)) {
//    *def_fp = 1;
//  }
//
//  /* In land bombardment both units have their firepower reduced to 1 */
//  if (Unit.is_sailing_unit(attacker)
//      && !Terrain_H.is_ocean(defender.tile.terrain)
//      && is_ground_unit(defender)) {
//    *att_fp = 1;
//    *def_fp = 1;
//  }
//}
//
///**************************************************************************
//Returns a double in the range [0;1] indicating the attackers chance of
//winning. The calculation takes all factors into account.
//**************************************************************************/
//double unit_win_chance(unit attacker, unit defender)
//{
//  int def_power = get_total_defense_power(attacker, defender);
//  int att_power = get_total_attack_power(attacker, defender);
//
//  double chance;
//
//  int def_fp, att_fp;
//  get_modified_firepower(attacker, defender, &att_fp, &def_fp);
//
//  chance = win_chance(att_power, attacker.hp, att_fp,
//		      def_power, defender.hp, def_fp);
//
//  return chance;
//}
//
///**************************************************************************
//  a wrapper that returns whether or not a unit ignores citywalls
//**************************************************************************/
//static boolean unit_ignores_citywalls(unit punit)
//{
//  return (unit_flag(punit, F_IGWALL));
//}
//
///**************************************************************************
//  Takes into account unit move_type as well, and Walls variant.
//**************************************************************************/
//boolean unit_really_ignores_citywalls(unit punit)
//{
//  return (unit_ignores_citywalls(punit)
//	  || is_air_unit(punit)
//	  || Unit.is_sailing_unit(punit));
//}
//
///**************************************************************************
// a wrapper function returns 1 if the unit is on a square with fortress
//**************************************************************************/
//boolean unit_on_fortress(unit punit)
//{
//  return Map.map_has_special(punit.tile, Terrain_H.S_FORTRESS);
//}
//
///**************************************************************************
//  a wrapper function returns 1 if there is a sdi-defense close to the square
//**************************************************************************/
//city sdi_defense_close(player owner,
//			       final tile ptile)
//{
//  for(tile ptile1: util.square_tile_iterate(ptile, 2)) {
//    city pcity = Map.map_get_city(ptile1);
//    if (pcity && (!Player_P.pplayers_allied(City.city_owner(pcity), owner))
//	&& Effects.get_city_bonus(pcity, EFT_NUKE_PROOF) > 0) {
//      return pcity;
//    }
//  }
//
//  return null;
//}
//
///**************************************************************************
// Convenience wrapper for base_get_attack_power.
//**************************************************************************/
//int get_attack_power(unit punit)
//{
//  return base_get_attack_power(punit.type, punit.veteran,
//			       punit.moves_left);
//}
//
///**************************************************************************
// Returns the attack power, modified by moves left, and veteran
// status. Set moves_left to Unit_H.SINGLE_MOVE to disable the reduction of
// power caused by tired units.
//**************************************************************************/
//int base_get_attack_power(int type, int veteran, int moves_left)
//{
//  int power;
//
//  power = get_unit_type(type).attack_strength * POWER_FACTOR;
//  power *= get_unit_type(type).veteran[veteran].power_fact;
//
//  if (!Unittype_P.unit_type_flag(type, F_IGTIRED) && moves_left < Unit_H.SINGLE_MOVE) {
//    power = (power * moves_left) / Unit_H.SINGLE_MOVE;
//  }
//  return power;
//}
//
///**************************************************************************
//  Returns the defense power, modified by veteran status.
//**************************************************************************/
//int base_get_defense_power(unit punit)
//{
//  return punit.unit_type().defense_strength * POWER_FACTOR
//  	* punit.unit_type().veteran[punit.veteran].power_fact;
//}
//
///**************************************************************************
//  Returns the defense power, modified by terrain and veteran status.
//**************************************************************************/
//int get_defense_power(unit punit)
//{
//  int db, power = base_get_defense_power(punit);
//
//  db = get_tile_type(punit.tile.terrain).defense_bonus;
//  if (Map.map_has_special(punit.tile, Terrain_H.S_RIVER)) {
//    db += (db * Map.terrain_control.river_defense_bonus) / 100;
//  }
//  power = (power * db) / 10;
//
//  return power;
//}
//
///***************************************************************************
// return the modified attack power of a unit.  Currently they aren't any
// modifications...
//***************************************************************************/
//int get_total_attack_power(unit attacker, unit defender)
//{
//  int attackpower = get_attack_power(attacker);
//
//  return attackpower;
//}
//
///**************************************************************************
// Return an increased defensepower. Effects which increase the
// defensepower are:
//  - unit type effects (horse vs pikemen for example)
//  - defender in a fortress
//  - fortified defender
//
//May be called with a non-existing att_type to avoid any unit type
//effects.
//**************************************************************************/
//static int defense_multiplication(int att_type,
//				  int def_type,
//				  final tile ptile,
//				  int defensepower, boolean fortified)
//{
//  city pcity = Map.map_get_city(ptile);
//  int mod;
//
//  if (unit_type_exists(att_type)) {
//    if (Unittype_P.unit_type_flag(def_type, F_PIKEMEN)
//	&& Unittype_P.unit_type_flag(att_type, F_HORSE)) {
//      defensepower *= 2;
//    }
//
//    if (Unittype_P.unit_type_flag(def_type, F_AEGIS) &&
//	(is_air_unittype(att_type) || is_heli_unittype(att_type))) {
//      defensepower *= 5;
//    }
//         
//    if (is_air_unittype(att_type) && pcity) {
//      if ((mod = Effects.get_city_bonus(pcity, EFT_AIR_DEFEND)) > 0) {
//	defensepower = defensepower * (100 + mod) / 100;
//      }
//      if ((mod = Effects.get_city_bonus(pcity, EFT_MISSILE_DEFEND)) > 0
//	  && Unittype_P.unit_type_flag(att_type, F_MISSILE)) {
//	defensepower = defensepower * (100 + mod) / 100;
//      }
//    } else if (Unittype_P.is_water_unit(att_type) && pcity) {
//      if ((mod = Effects.get_city_bonus(pcity, EFT_SEA_DEFEND)) > 0) {
//	defensepower = defensepower * (100 + mod) / 100;
//      }
//    }
//    if (!Unittype_P.unit_type_flag(att_type, F_IGWALL)
//	&& (Unittype_P.is_ground_unittype(att_type) || is_heli_unittype(att_type))
//        && pcity
//        && (mod = Effects.get_city_bonus(pcity, effect_type.EFT_LAND_DEFEND)) > 0) {
//      defensepower = defensepower * (100 + mod) / 100;
//    }
//
//    if (Unittype_P.unit_type_flag(att_type, F_FIGHTER) && is_heli_unittype(def_type)) {
//      defensepower /= 2;
//    }
//  }
//
//  if (Map.map_has_special(ptile, Terrain_H.S_FORTRESS) && !pcity) {
//    defensepower +=
//	(defensepower * Map.terrain_control.fortress_defense_bonus) / 100;
//  }
//
//  if ((pcity || fortified) && Unittype_P.is_ground_unittype(def_type)) {
//    defensepower = (defensepower * 3) / 2;
//  }
//
//  return defensepower;
//}
//
///**************************************************************************
// May be called with a non-existing att_type to avoid any effects which
// depend on the attacker.
//**************************************************************************/
//int get_virtual_defense_power(int att_type, int def_type,
//			      final tile ptile,
//			      boolean fortified, int veteran)
//{
//  int defensepower = Unittype_P.unit_types[def_type].defense_strength;
//  int t = ptile.terrain;
//  int db;
//
//  if (Unittype_P.unit_types[def_type].move_type == unit_move_type.LAND_MOVING && Terrain_H.is_ocean(t)) {
//    /* Ground units on ship doesn't defend. */
//    return 0;
//  }
//
//  db = get_tile_type(t).defense_bonus;
//  if (Map.map_has_special(ptile, Terrain_H.S_RIVER)) {
//    db += (db * Map.terrain_control.river_defense_bonus) / 100;
//  }
//  defensepower *= db;
//  defensepower *= get_unit_type(def_type).veteran[veteran].power_fact;
//
//  return defense_multiplication(att_type, def_type, ptile, defensepower,
//				fortified);
//}
//
///***************************************************************************
// return the modified defense power of a unit.
// An veteran aegis cruiser in a mountain city with SAM and SDI defense 
// being attacked by a missile gets defense 288.
//***************************************************************************/
//int get_total_defense_power(unit attacker, unit defender)
//{
//  return defense_multiplication(attacker.type, defender.type,
//				defender.tile,
//				get_defense_power(defender),
//				defender.activity == ACTIVITY_FORTIFIED);
//}
//
///**************************************************************************
//A number indicating the defense strength.
//Unlike the one got from win chance this doesn't potentially get insanely
//small if the units are unevenly matched, unlike win_chance.
//**************************************************************************/
//static int get_defense_rating(unit attacker, unit defender)
//{
//  int afp, dfp;
//
//  int rating = get_total_defense_power(attacker, defender);
//  get_modified_firepower(attacker, defender, &afp, &dfp);
//
//  /* How many rounds the defender will last */
//  rating *= (defender.hp + afp-1)/afp;
//
//  rating *= dfp;
//
//  return rating;
//}

	/***************************************************************************
	 * Finds the best defender on the tile, given an attacker. The diplomatic
	 * relationship of attacker and defender is ignored; the caller should check
	 * this.
	 **************************************************************************/
	public static unit get_defender(unit attacker, final tile ptile)
	{
		unit bestdef = null;
		int bestvalue = -1, best_cost = 0, rating_of_best = 0;

		/* Simply call win_chance with all the possible defenders in turn, and
		 * take the best one.  It currently uses build cost as a tiebreaker in
		 * case 2 units are identical, but this is crude as build cost does not
		 * neccesarily have anything to do with the value of a unit.  This function
		 * could be improved to take the value of the unit into account.  It would
		 * also be nice if the function was a bit more fuzzy about prioritizing,
		 * making it able to fx choose a 1a/9d unit over a 10a/10d unit. It should
		 * also be able to spare units without full hp's to some extent, as these
		 * could be more valuable later. */
//  for (unit defender : ptile.units.data) {
//    /* We used to skip over allied units, but the logic for that is
//     * complicated and is now handled elsewhere. */
//    if (unit_can_defend_here(defender)) {
//      boolean change = false;
//      int build_cost = Unittype_P.unit_build_shield_cost(defender.type);
//      int defense_rating = get_defense_rating(attacker, defender);
//      /* This will make units roughly evenly good defenders look alike. */
//      int unit_def 
//        = (int) (100000 * (1 - unit_win_chance(attacker, defender)));
//
//      assert(unit_def >= 0);
//
//      if (unit_def > bestvalue) {
//	change = true;
//      } else if (unit_def == bestvalue) {
//	if (build_cost < best_cost) {
//	  change = true;
//	} else if (build_cost == best_cost) {
//	  if (rating_of_best < defense_rating) {	
//	    change = true;
//	  }
//	}
//      }
//
//      if (change) {
//	bestvalue = unit_def;
//	bestdef = defender;
//	best_cost = build_cost;
//	rating_of_best = defense_rating;
//      }
//    }
//  } }
//
//  if (ptile.units.foo_list_size() > 0 && !bestdef) {
//    unit punit = unit_list_get(&ptile.units, 0);
//
//    util.freelog(Log.LOG_ERROR, "get_defender bug: %s's %s vs %s's %s (total %d" +
//            " units) on %s at (%d,%d). ", attacker.unit_owner().name,
//            attacker.unit_type().name, punit.unit_owner().name,
//            punit.unit_type().name, ptile.units.foo_list_size(), 
//            get_terrain_name(ptile.terrain), ptile.x, ptile.y);
//  }

		return bestdef;
	}
//
///**************************************************************************
//get unit at (x, y) that wants to kill defender.
//
//Works like get_defender; see comment there.
//This function is mostly used by the AI.
//**************************************************************************/
//unit get_attacker(unit defender, final tile ptile)
//{
//  unit bestatt = 0;
//  int bestvalue = -1, unit_a, best_cost = 0;
//
//  for (unit attacker : ptile.units.data) {
//    int build_cost = Unittype_P.unit_build_shield_cost(attacker.type);
//
//    if (Player_P.pplayers_allied(defender.unit_owner(), attacker.unit_owner())) {
//      return null;
//    }
//    unit_a = (int) (100000 * (unit_win_chance(attacker, defender)));
//    if (unit_a > bestvalue ||
//	(unit_a == bestvalue && build_cost < best_cost)) {
//      bestvalue = unit_a;
//      bestatt = attacker;
//      best_cost = build_cost;
//    }
//  } }
//
//  return bestatt;
//}
//
///**************************************************************************
//  Is it a city/fortress/air base or will the whole stack util.die in an attack
//**************************************************************************/
//boolean is_stack_vulnerable(final tile ptile)
//{
//  return !(ptile.city != null
//           || Map.map_has_special(ptile, Terrain_H.S_FORTRESS)
//           || Map.map_has_special(ptile, Terrain_H.S_AIRBASE)
//           || !Game.game.rgame.killstack);
//}
}