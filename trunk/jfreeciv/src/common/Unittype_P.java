package common;
import static port.util.*;

import static utility.shared.Shared_H.*;
import static common.unittype.unittype.*;
import static common.Game.*;

import common.unit.unit;
import common.unittype.Eunit_flag_id;
import common.unittype.unittype;

public class Unittype_P {
	// #include "astring.h"
	// #include "fcintl.h"
	// #include "game.h"
	// #include "government.h"
	// #include "mem.h"
	// #include "player.h"
	// #include "shared.h"
	// #include "support.h"
	// #include "tech.h"

	// #include "unittype.h"

	public static unittype unit_types[] = new unittype[U_LAST];
	/*
	 * the unit_types array is now setup in: server/ruleset.c (for the server)
	 * client/packhand.c (for the client)
	 */

	public static final String move_type_names[] = { "Land", "Sea", "Heli",
			"Air" };
	static final String flag_names[] = { "TradeRoute", "HelpWonder", "Missile",
			"IgZOC", "NonMil", "IgTer", "Carrier", "OneAttack", "Pikemen",
			"Horse", "IgWall", "FieldUnit", "AEGIS", "Fighter", "Marines",
			"Partial_Invis", "Settlers", "Diplomat", "Trireme", "Nuclear",
			"Spy", "Transform", "Paratroopers", "Airbase", "Cities", "IgTired",
			"Missile_Carrier", "No_Land_Attack", "AddToCity", "Fanatic",
			"GameLoss", "Unique", "Unbribable", "Undisbandable", "SuperSpy",
			"NoHome", "NoVeteran", "Bombarder", "CityBuster", "NoBuild" };
	static final String role_names[] = { "FirstBuild", "Explorer", "Hut",
			"HutTech", "Partisan", "DefendOk", "DefendGood", "AttackFast",
			"AttackStrong", "Ferryboat", "Barbarian", "BarbarianTech",
			"BarbarianBoat", "BarbarianBuild", "BarbarianBuildTech",
			"BarbarianLeader", "BarbarianSea", "BarbarianSeaTech", "Cities",
			"Settlers", "GameLoss", "Diplomat" };
	static final String unit_class_names[] = { "Air", "Helicopter", "Land",
			"Missile", "Nuclear", "Sea" };
	 /***********************************************************************
		 * Returns 1 if the unit_type "exists" in this game, 0 otherwise. A
		 * unit_type doesn't exist if one of: - id is out of range - the
		 * unit_type has been flagged as removed by setting its tech_requirement
		 * to A_LAST.
		 **********************************************************************/
//	boolean unit_type_exists(int id) {
//		if (id < 0 || id >= U_LAST || id >= game.num_unit_types)
//			return false;
//		else
//			return unit_types[id].tech_requirement != A_LAST;
//	}

//	/***************************************************************************
//	 * ...
//	 **************************************************************************/
//	boolean is_ground_unittype(int id) {
//		return (unit_types[id].move_type == LAND_MOVING);
//	}
//
//	/***************************************************************************
//	 * ...
//	 **************************************************************************/
//	boolean is_air_unittype(int id) {
//		return (unit_types[id].move_type == AIR_MOVING);
//	}
//
//	/***************************************************************************
//	 * ...
//	 **************************************************************************/
//	boolean is_heli_unittype(int id) {
//		return (unit_types[id].move_type == HELI_MOVING);
//	}
//
//	/***************************************************************************
//	 * ...
//	 **************************************************************************/
//	boolean is_water_unit(int id) {
//		return (unit_types[id].move_type == SEA_MOVING);
//	}
//
//	/***************************************************************************
//	 * ...
//	 **************************************************************************/
//	int utype_shield_cost(unit_type ut, government g) {
//		if (government_has_flag(g, G_FANATIC_TROOPS)
//				&& BV_ISSET(ut.flags, F_FANATIC)) {
//			return 0;
//		}
//		return ut.shield_cost * g.unit_shield_cost_factor;
//	}
//
//	/***************************************************************************
//	 * ...
//	 **************************************************************************/
//	int utype_food_cost(unit_type ut, government g) {
//		return ut.food_cost * g.unit_food_cost_factor;
//	}
//
//	/***************************************************************************
//	 * ...
//	 **************************************************************************/
//	int utype_happy_cost(unit_type ut, government g) {
//		return ut.happy_cost * g.unit_happy_cost_factor;
//	}
//
//	/***************************************************************************
//	 * ...
//	 **************************************************************************/
//	int utype_gold_cost(unit_type ut, government g) {
//		return ut.gold_cost * g.unit_gold_cost_factor;
//	}

	/***************************************************************************
	 * ...
	 **************************************************************************/
	static boolean unit_type_flag(int id, Eunit_flag_id flag) {
		// assert (flag >= 0 && flag < F_LAST);
		assert (flag != Eunit_flag_id.F_LAST);
		// return BV_ISSET(unit_types[id].flags, flag); //TODO
		return false;
	}

	/***************************************************************************
	 * ...
	 **************************************************************************/
	public static boolean unit_flag(unit punit, Eunit_flag_id flag) {
		return unit_type_flag(punit.type, flag);
	}

//	/***************************************************************************
//	 * ...
//	 **************************************************************************/
//	boolean unit_has_role(int id, int role) {
//		assert (role >= L_FIRST && role < L_LAST);
//		return BV_ISSET(unit_types[id].roles, role - L_FIRST);
//	}
//
//	/***************************************************************************
//	 * Returns the number of shields it takes to build this unit.
//	 **************************************************************************/
//	int unit_build_shield_cost(int id) {
//		return unit_types[id].build_cost;
//	}
//
//	/***************************************************************************
//	 * Returns the amount of gold it takes to rush this unit.
//	 **************************************************************************/
//	int unit_buy_gold_cost(int id, int shields_in_stock) {
//		int cost = 0, missing = unit_types[id].build_cost - shields_in_stock;
//		if (missing > 0) {
//			cost = 2 * missing + (missing * missing) / 20;
//		}
//		if (shields_in_stock == 0) {
//			cost *= 2;
//		}
//		return cost;
//	}
//
//	/***************************************************************************
//	 * Returns the number of shields received when this unit is disbanded.
//	 **************************************************************************/
//	int unit_disband_shields(int id) {
//		return unit_types[id].build_cost / 2;
//	}
//
//	/***************************************************************************
//	 * ...
//	 **************************************************************************/
//	int unit_pop_value(int id) {
//		return (unit_types[id].pop_cost);
//	}
	 /***********************************************************************
		 * Return the (translated) name of the unit type.
		 **********************************************************************/
	public static String unit_name(int id) {
		return (unit_types[id].name);
	}
	/***************************************************************************
	 * Return the original (untranslated) name of the unit type.
	 **************************************************************************/
	final String unit_name_orig(int id) {
		return unit_types[id].name_orig;
	}

	/***************************************************************************
	 * ...
	 **************************************************************************/
	public static final String get_unit_name(int id)
	{
		unittype ptype;
//		static char buffer[256];
		String buffer;
		ptype = unit.get_unit_type(id);
		if (ptype.fuel > 0) {
			buffer = my_snprintf("%s [%d/%d/%d(%d)]", ptype.name, ptype.attack_strength,
					ptype.defense_strength,
					ptype.move_rate/3,(ptype.move_rate/3)*ptype.fuel);
		} else {
			buffer = my_snprintf("%s [%d/%d/%d]", ptype.name, ptype.attack_strength,
					ptype.defense_strength, ptype.move_rate/3);
		}
		return buffer;
	}

	/***************************************************************************
	 * ...
	 **************************************************************************/
//	final String unit_class_name(Unit_Class_id id) {
//		if ((id >= 0) && (id < UCL_LAST)) {
//			return unit_class_names[id];
//		} else {
//			return "";
//		}
//	}
//	 /***********************************************************************
//		 * Return a string with all the names of units with this flag Return
//		 * null if no unit with this flag exists. The string must be free'd
//		 * 
//		 * TODO: if there are more than 4 units with this flag return a fallback
//		 * string (e.g. first unit name + "and similar units"
//		 **********************************************************************/
//	 final String get_units_with_flag_string(int flag)
//	 {
//	 int count = num_role_units(flag);
//	 if (count == 1) {
//	 return mystrdup(unit_name(get_role_unit(flag, 0)));
//	 }
//	 if (count > 0) {
//	 struct astring astr;
//	 astr_init(&astr);
//	 astr_minsize(&astr, 1);
//	 astr.str[0] = 0;
//	 while ((count--) > 0) {
//	 int u = get_role_unit(flag, count);
//	 final String unitname = unit_name(u);
//	 /* there should be something like astr_append() */
//	 astr_minsize(&astr, astr.n + unitname.length());
//	 strcat(astr.str, unitname);
//	 if (count == 1) {
//	 final String and_str = " and ";
//	 astr_minsize(&astr, astr.n + and_str.length());
//	 strcat(astr.str, and_str);
//	 } else {
//	 if (count != 0) {
//	 final String and_comma = Q"?and:, ";
//	 astr_minsize(&astr, astr.n + and_comma.length());
//	 strcat(astr.str, and_comma);
//	 } else {
//	 return astr.str;
//	 }
//	 }
//	 }
//	 }
//	 return null;
//	 }
//	 /***********************************************************************
//		 * ...
//		 **********************************************************************/
//	 int can_upgrade_unittype(player pplayer, int id)
//	 {
//	 int best_upgrade = -1;
//	 if (!can_player_build_unit_direct(pplayer, id))
//	 return -1;
//	 while (unit_type_exists(id = unit_types[id].obsoleted_by))
//	 if (can_player_build_unit_direct(pplayer, id))
//	 best_upgrade = id;
//	 return best_upgrade;
//	 }
//	 /***********************************************************************
//		 * Return the cost (gold) of upgrading a single unit of the specified
//		 * type to the new type. This price could (but currently does not)
//		 * depend on other attributes (like nation or government type) of the
//		 * player the unit belongs to.
//		 **********************************************************************/
//	 int unit_upgrade_price(final player final pplayer,
//	 final int from, final int to)
//	 {
//	 return unit_buy_gold_cost(to, unit_disband_shields(from));
//	 }
//	 /***********************************************************************
//		 * Returns the unit type that has the given (translated) name. Returns
//		 * U_LAST if none match.
//		 **********************************************************************/
//	 int find_unit_type_by_name(final String name)
//	 {
//	 /* Does a linear search of unit_types[].name */
//	 unit_type_iterate(i) {
//	 if (strcmp(unit_types[i].name, name) == 0) {
//	 return i;
//	 }
//	 } unit_type_iterate_end;
//	 return U_LAST;
//	 }
//	 /***********************************************************************
//		 * Returns the unit type that has the given original (untranslated)
//		 * name. Returns U_LAST if none match.
//		 **********************************************************************/
//	 int find_unit_type_by_name_orig(final String name_orig)
//	 {
//	 /* Does a linear search of unit_types[].name_orig. */
//	 unit_type_iterate(i) {
//	 if (mystrcasecmp(unit_types[i].name_orig, name_orig) == 0) {
//	 return i;
//	 }
//	 } unit_type_iterate_end;
//	 return U_LAST;
//	 }
//	 /***********************************************************************
//		 * Convert unit_move_type names to enum; case insensitive; returns 0 if
//		 * can't match.
//		 **********************************************************************/
//	 enum unit_move_type unit_move_type_from_str(final String s)
//	 {
//	 enum unit_move_type i;
//	 /* a compile-time check would be nicer, but this will do: */
//	 assert(ARRAY_SIZE(move_type_names) == (AIR_MOVING - LAND_MOVING + 1));
//	 for(i=LAND_MOVING; i<=AIR_MOVING; i++) {
//	 if (mystrcasecmp(move_type_names[i-LAND_MOVING], s)==0) {
//	 return i;
//	 }
//	 }
//	 return 0;
//	 }
//	 /***********************************************************************
//		 * Convert Unit_Class_id names to enum; case insensitive; returns
//		 * UCL_LAST if can't match.
//		 **********************************************************************/
//	 Unit_Class_id unit_class_from_str(final String s)
//	 {
//	 Unit_Class_id i;
//	 assert(ARRAY_SIZE(unit_class_names) == UCL_LAST);
//	 for (i = 0; i < UCL_LAST; i++) {
//	 if (mystrcasecmp(unit_class_names[i], s)==0) {
//	 return i;
//	 }
//	 }
//	 return UCL_LAST;
//	 }
//	 /***********************************************************************
//		 * Convert flag names to enum; case insensitive; returns F_LAST if can't
//		 * match.
//		 **********************************************************************/
//	 Eunit_flag_id unit_flag_from_str(final String s) {
//	 // Eunit_flag_id i;
//	 // assert (ARRAY_SIZE(flag_names) == F_LAST);
//	 // for (i = 0; i < F_LAST; i++) {
//	 // if (mystrcasecmp(flag_names[i], s) == 0) {
//	 // return i;
//	 // }
//	 // }
//	 return Eunit_flag_id.F_LAST;
//	 }
//	 /***********************************************************************
//		 * Convert role names to enum; case insensitive; returns L_LAST if can't
//		 * match.
//		 **********************************************************************/
//	 enum unit_role_id unit_role_from_str(final String s)
//	 {
//	 enum unit_role_id i;
//	 assert(ARRAY_SIZE(role_names) == (L_LAST - L_FIRST));
//	 for(i=L_FIRST; i<L_LAST; i++) {
//	 if (mystrcasecmp(role_names[i-L_FIRST], s)==0) {
//	 return i;
//	 }
//	 }
//	 return L_LAST;
//	 }
//	 /***********************************************************************
//		 * Whether player can build given unit somewhere, ignoring whether unit
//		 * is obsolete and assuming the player has a coastal city.
//		 **********************************************************************/
//	 boolean can_player_build_unit_direct(player p, int id)
//	 {
//	 Impr_Type_id impr_req;
//	 Tech_Type_id tech_req;
//	 if (!unit_type_exists(id))
//	 return false;
//	 if (unit_type_flag(id, F_NUCLEAR)
//	 && !get_player_bonus(p, EFT_ENABLE_NUKE) > 0)
//	 return false;
//	 if (unit_type_flag(id, F_NOBUILD)) {
//	 return false;
//	 }
//	 if (unit_type_flag(id, F_FANATIC)
//	 && !government_has_flag(get_gov_pplayer(p), G_FANATIC_TROOPS))
//	 return false;
//	 if (get_invention(p,unit_types[id].tech_requirement)!=TECH_KNOWN)
//	 return false;
//	 if (unit_type_flag(id, F_UNIQUE)) {
//	 /*
//		 * FIXME: This could be slow if we have lots of units. We could consider
//		 * keeping an array of unittypes updated with this info instead.
//		 */
//	 for (unit punit : p.units.data) {
//	 if (punit.type == id) {
//	 return false;
//	 }
//	 } }
//	 }
//	 /*
//		 * If the unit has a building requirement, we check to see if the player
//		 * can build that building. Note that individual cities may not have
//		 * that building, so they still may not be able to build the unit.
//		 */
//	 impr_req = unit_types[id].impr_requirement;
//	 tech_req = get_improvement_type(impr_req).tech_req;
//	 if (impr_req != B_LAST && get_invention(p, tech_req) != TECH_KNOWN) {
//	 return false;
//	 }
//	 return true;
//	 }
//	 /***********************************************************************
//		 * Whether player can build given unit somewhere; returns 0 if unit is
//		 * obsolete.
//		 **********************************************************************/
//	 boolean can_player_build_unit(player p, int id)
//	 {
//	 if (!can_player_build_unit_direct(p, id))
//	 return false;
//	 while(unit_type_exists((id = unit_types[id].obsoleted_by)))
//	 if (can_player_build_unit_direct(p, id))
//	 return false;
//	 return true;
//	 }
//	 /***********************************************************************
//		 * Whether player can _eventually_ build given unit somewhere -- ie,
//		 * returns 1 if unit is available with current tech OR will be available
//		 * with future tech. returns 0 if unit is obsolete.
//		 **********************************************************************/
//	 boolean can_player_eventually_build_unit(player p, int id)
//	 {
//	 if (!unit_type_exists(id)) {
//	 return false;
//	 }
//	 if (unit_type_flag(id, F_NOBUILD)) {
//	 return false;
//	 }
//	 while (unit_type_exists((id = unit_types[id].obsoleted_by))) {
//	 if (can_player_build_unit_direct(p, id)) {
//	 return false;
//	 }
//	 }
//	 return true;
//	 }
//	 /***********************************************************************
//		 * The following functions use static variables so we can quickly look
//		 * up which unit types have given flag or role. For these functions
//		 * flags and roles are considered to be in the same "space", and any
//		 * "role" argument can also be a "flag". Only units which pass
//		 * unit_type_exists are counted. Unit order is in terms of the order in
//		 * the units ruleset.
//		 **********************************************************************/
//	 static boolean first_init = true;
//	 static int n_with_role[L_LAST];
//	 static int *with_role[L_LAST];
//	 /***********************************************************************
//		 * Do the real work for role_unit_precalcs, for one role (or flag),
//		 * given by i.
//		 **********************************************************************/
//	 static void precalc_one(int i, boolean (*func_has)(int, int))
//	 {
//	 int j;
//	 /* Count: */
//	 unit_type_iterate(u) {
//	 if(unit_type_exists(u) && func_has(u, i)) {
//	 n_with_role[i]++;
//	 }
//	 } unit_type_iterate_end;
//	 if(n_with_role[i] > 0) {
//	 with_role[i] = fc_malloc(n_with_role[i]*sizeof(int));
//	 j = 0;
//	 unit_type_iterate(u) {
//	 if(unit_type_exists(u) && func_has(u, i)) {
//	 with_role[i][j++] = u;
//	 }
//	 } unit_type_iterate_end;
//	 assert(j==n_with_role[i]);
//	 }
//	 }
//	 /***********************************************************************
//		 * Initialize; it is safe to call this multiple times (eg, if units have
//		 * changed due to rulesets in client).
//		 **********************************************************************/
//	 void role_unit_precalcs()
//	 {
//	 int i;
//	 if(!first_init) {
//	 for(i=0; i<L_LAST; i++) {
//	 free(with_role[i]);
//	 }
//	 }
//	 for(i=0; i<L_LAST; i++) {
//	 with_role[i] = null;
//	 n_with_role[i] = 0;
//	 }
//	 for(i=0; i<F_LAST; i++) {
//	 precalc_one(i, unit_type_flag);
//	 }
//	 for(i=L_FIRST; i<L_LAST; i++) {
//	 precalc_one(i, unit_has_role);
//	 }
//	 first_init = false;
//	 }
//	 /***********************************************************************
//		 * How many unit types have specified role/flag.
//		 **********************************************************************/
//	 int num_role_units(int role)
//	 {
//	 assert((role>=0 && role<F_LAST) || (role>=L_FIRST && role<L_LAST));
//	 return n_with_role[role];
//	 }
//	 /***********************************************************************
//		 * Return index-th unit with specified role/flag. Index -1 means (n-1),
//		 * ie last one.
//		 **********************************************************************/
//	 int get_role_unit(int role, int index)
//	 {
//	 assert((role>=0 && role<F_LAST) || (role>=L_FIRST && role<L_LAST));
//	 if (index==-1) index = n_with_role[role]-1;
//	 assert(index>=0 && index<n_with_role[role]);
//	 return with_role[role][index];
//	 }
//	 /***********************************************************************
//		 * Return "best" unit this city can build, with given role/flag. Returns
//		 * U_LAST if none match. "Best" means highest unit type id.
//		 **********************************************************************/
//	 int best_role_unit(city pcity, int role)
//	 {
//	 int u;
//	 int j;
//	 assert((role>=0 && role<F_LAST) || (role>=L_FIRST && role<L_LAST));
//	 for(j=n_with_role[role]-1; j>=0; j--) {
//	 u = with_role[role][j];
//	 if (can_build_unit(pcity, u)) {
//	 return u;
//	 }
//	 }
//	 return U_LAST;
//	 }
//	 /***********************************************************************
//		 * Return "best" unit the player can build, with given role/flag.
//		 * Returns U_LAST if none match. "Best" means highest unit type id.
//		 * 
//		 * TODO: Cache the result per player?
//		 **********************************************************************/
//	 int best_role_unit_for_player(player pplayer, int role)
//	 {
//	 int j;
//	 assert((role >= 0 && role < F_LAST) || (role >= L_FIRST && role <
//	 L_LAST));
//	 for(j = n_with_role[role]-1; j >= 0; j--) {
//	 int utype = with_role[role][j];
//	 if (can_player_build_unit(pplayer, utype)) {
//	 return utype;
//	 }
//	 }
//	 return U_LAST;
//	 }
//	 /***********************************************************************
//		 * Return first unit the player can build, with given role/flag. Returns
//		 * U_LAST if none match. Used eg when placing starting units.
//		 **********************************************************************/
//	 int first_role_unit_for_player(player pplayer, int role)
//	 {
//	 int j;
//	 assert((role >= 0 && role < F_LAST) || (role >= L_FIRST && role <
//	 L_LAST));
//	 for(j = 0; j < n_with_role[role]; j++) {
//	 int utype = with_role[role][j];
//	 if (can_player_build_unit(pplayer, utype)) {
//	 return utype;
//	 }
//	 }
//	 return U_LAST;
//	 }
//	 /***********************************************************************
//		 * Frees the memory associated with this unit type.
//		 **********************************************************************/
//	 static void unit_type_free(int id)
//	 {
//	 unit_type p = get_unit_type(id);
//	 free(p.helptext);
//	 p.helptext = null;
//	 }
//	 /***********************************************************************
//		 * Frees the memory associated with all unit types.
//		 **********************************************************************/
//	 void unit_types_free()
//	 {
//	 unit_type_iterate(i) {
//	 unit_type_free(i);
//	 } unit_type_iterate_end;
//	 }
}