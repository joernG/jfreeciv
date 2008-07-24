package server;

import static common.City.is_city_option_set;
import static common.Combat.can_unit_attack_all_at_tile;
import static common.Combat.get_defender;
import static common.Game.find_unit_by_id;
import static common.Map.map;
import static common.Map.map_get_city;
import static common.Map.normalize_map_pos;
import static common.Player_P.ai_handicap;
import static common.Player_P.can_player_see_unit;
import static common.Unit.get_transporter_capacity;
import static common.Unit.is_enemy_unit_tile;
import static common.Unit.is_military_unit;
import static common.Unit.set_unit_activity;
import static common.Unit.unit_list_find;
import static common.Unittype_P.get_unit_name;
import static common.Unittype_P.unit_flag;
import static common.Unittype_P.unit_name;
import static common.city.City_H.CITYOPT_AUTOATTACK_BITS;
import static common.map.Map_H.map_pos_to_index;
import static common.player.Player_H.H_TARGETS;
import static server.Gotohand.calculate_move_cost;
import static server.Plrhand.notify_player_ex;
import static server.Unithand.handle_unit_activity_request;
import static server.Unittools.send_unit_info;
import port.util;
import utility.Log;
import utility.Speclists;

import common.Map;
import common.event_type;
import common.city.city;
import common.city.city_options;
import common.map.Map_H;
import common.map.tile;
import common.player.player;
import common.unit.goto_move_restriction;
import common.unit.unit;
import common.unit.unit_activity;
import common.unittype.Eunit_flag_id;

public class Autoattack {
	// TODO, tbd
	/*
	 * autoattack by: Sebastian Fischmeister <sfischme@nexus.lzk.tuwien.ac.at>
	 * and David Pfitzner <dwp@mso.anu.edu.au>
	 * 
	 * ToDo: - if have movement and enough hp remaining, should consider
	 * attacking multiple times if multiple targets (hard, because you have to
	 * calculate warmap for each choice . lots of cpu !!!) - create gui-client
	 * for diplomacy for players (in work by other persons)
	 * 
	 */

	/***************************************************************************
	 * FIXME: Calculate the attractiveness of attacking.
	 **************************************************************************/
	static unit search_best_target(player pplayer, city pcity, unit punit)	{
		Speclists<unit> targets;
		unit enemy=null, best_enemy = null;
		int score, best_score = 0;
		int mv_cost, range;

		/*
		 * if a 'Eunit_flag_id.F_ONEATTACK' unit, range is larger, because they
		 * are not going to return this turn anyways
		 */

		range = unit_flag(punit, Eunit_flag_id.F_ONEATTACK) ?
				punit.moves_left : punit.moves_left/2;

		/* attack the next to city */ 
		range = range<4 ? 3 : range;

		util.freelog(Log.LOG_DEBUG, "doing autoattack for %s (%d/%d) in %s," +
				" range %d(%d), city_options %d",
				unit_name(punit.type), punit.tile.x, punit.tile.y,
				pcity.name,
				range, punit.moves_left, pcity.city_options);

		tile _start_tile = (punit.tile);			    
		tile ptile ;						    
		int _max_dist = (range); 
		Integer _x_itr, _y_itr, dx_itr, dy_itr;
		int _index;	    
		boolean _is_border = Map_H.is_border_tile(_start_tile, _max_dist);		    

		for (_index = 0; _index < Map.map.num_iterate_outwards_indices; _index++) {   
			if (Map.map.iterate_outwards_indices[_index].dist > _max_dist) {	    
				break;								    
			}									    
			dx_itr = Map.map.iterate_outwards_indices[_index].dx;			    
			dy_itr = Map.map.iterate_outwards_indices[_index].dy;			    
			_x_itr = dx_itr + _start_tile.x;					    
			_y_itr = dy_itr + _start_tile.y;					    
			if (_is_border && !normalize_map_pos(_x_itr, _y_itr)) {		    
				continue;								    
			}									    
			//ptile = Map.map.tiles + map_pos_to_index(_x_itr, _y_itr);
			ptile = Map.map.tiles[ map_pos_to_index(_x_itr, _y_itr) ];
			if (Map.same_pos(punit.tile, ptile))
				continue;

			if (null!=map_get_city(ptile)) continue;
			/* don't attack enemy cities (or our own ;-) --dwp */

			targets = (ptile.units);
			if (targets.foo_list_size() == 0) continue;
			if (null==is_enemy_unit_tile(ptile, pplayer))
				continue;

			util.freelog(Log.LOG_DEBUG,  "found enemy unit/stack at %d,%d",
					ptile.x, ptile.y);
			enemy = get_defender(punit, ptile);
			if (null==enemy) {
				continue;
			}
			util.freelog(Log.LOG_DEBUG,  "defender is %s", unit_name(enemy.type));

			if (!is_city_option_set(pcity, city_options.CITYO_ATT_LAND.ordinal()
					+ enemy.unit_type().move_type.ordinal())) {
				util.freelog(Log.LOG_DEBUG, "wrong target type");
				continue;
			}

			mv_cost = calculate_move_cost(punit, ptile);
			if (mv_cost > range) {
				util.freelog(Log.LOG_DEBUG, "too far away: %d", mv_cost);
				continue;
			}

			/*
			 * Make sure the player can see both the location and the unit at
			 * that location, before allowing an auto-attack. Without this,
			 * units attack into locations they cannot see, and maybe submarines
			 * are made erroneously visible too.
			 * 
			 * Note, cheating AI may attack units it cannot see unless it has
			 * H_TARGETS handicap, but currently AI never uses auto-attack.
			 */
			if (ai_handicap(pplayer, H_TARGETS)
					&& !can_player_see_unit(pplayer, enemy)) {
				util.freelog(Log.LOG_DEBUG, "can't see %s at (%d,%d)", unit_name(enemy.type),
						ptile.x, ptile.y);
				continue;
			}

			/*
			 * Without this check, missiles are made useless for auto-attack as
			 * they get triggered by fighters and bombers and end up being
			 * wasted when they cannot engage.
			 */
			if (!can_unit_attack_all_at_tile(punit, ptile)) {
				util.freelog(Log.LOG_DEBUG, "%s at (%d,%d) cannot attack at (%d,%d)",
						unit_name(punit.type), punit.tile.x, punit.tile.y,
						ptile.x, ptile.y);
				continue;
			}

			/*
			 * perhaps there is a better algorithm in the ai-package -- fisch
			 */
			score = (enemy.unit_type().defense_strength + (enemy.hp / 2)
					+ (get_transporter_capacity(enemy) > 0 ? 1 : 0));

			if(null==best_enemy || score >= best_score) {
				best_score = score;
				best_enemy = enemy;
			}
		}
		if(null==best_enemy) return null;

		enemy = best_enemy;

		util.freelog(Log.LOG_DEBUG, "chosen target=%s (%d/%d)",
				get_unit_name(enemy.type), enemy.tile.x, enemy.tile.y);

		if((enemy.unit_type().defense_strength) >
		punit.unit_type().attack_strength*1.5) {
			notify_player_ex(pplayer, punit.tile, event_type.E_NOEVENT,
					("Game: Auto-Attack: %s's %s found a too " +
					"tough enemy (%s)"),
					pcity.name, unit_name(punit.type),
					unit_name(enemy.type));
			punit.ai.control=false;
			handle_unit_activity_request(punit, unit_activity.ACTIVITY_IDLE);
			return null;
		}

		return enemy;
	}

	/***************************************************************************
	 * ...
	 **************************************************************************/
	static void auto_attack_with_unit(player pplayer, city pcity, unit punit) {
		int id = punit.id;
		unit enemy;

		enemy = search_best_target(pplayer, pcity, punit);

		/* nothing found */
		if (null == enemy)
			return;

		util.freelog(Log.LOG_DEBUG, "launching attack");

		notify_player_ex(pplayer, enemy.tile, event_type.E_NOEVENT,
				"Game: Auto-Attack: %s's %s attacking %s's %s",
				pcity.name, unit_name(punit.type),
				enemy.unit_owner().name, unit_name(enemy.type));

		set_unit_activity(punit, unit_activity.ACTIVITY_GOTO);
		punit.goto_tile = enemy.tile;

		send_unit_info(null, punit);
		Gotohand.do_unit_goto(punit, goto_move_restriction.GOTO_MOVE_ANY, false);

		punit = find_unit_by_id(id);

		if (punit!=null) {
			set_unit_activity(punit, unit_activity.ACTIVITY_GOTO);
			punit.goto_tile = pcity.tile;
			send_unit_info(null, punit);

			Gotohand.do_unit_goto(punit, goto_move_restriction.GOTO_MOVE_ANY, false);

			if (null!=unit_list_find(pcity.tile.units, id)) {
				handle_unit_activity_request(punit, unit_activity.ACTIVITY_IDLE);
			}
		}

		return; /* or maybe: do you want to play again? */
	}

	/***************************************************************************
	 * ...
	 **************************************************************************/
	static void auto_attack_city(player pplayer, city pcity) {
		for (unit punit : pcity.tile.units.data) {
			if (punit.ai.control
					&& punit.activity == unit_activity.ACTIVITY_IDLE
					&& is_military_unit(punit)) {
				auto_attack_with_unit(pplayer, pcity, punit);
			}
		}
	}

	/***************************************************************************
	 * ...
	 **************************************************************************/
	static void auto_attack_player(player pplayer) {
		util.freelog(Log.LOG_DEBUG, "doing auto_attack for: %s", pplayer.name);

		for (city pcity : pplayer.cities.data) {
			/* fasten things up -- fisch */
			if ((pcity.city_options & CITYOPT_AUTOATTACK_BITS) != 0
					&& pcity.anarchy == 0) {
				auto_attack_city(pplayer, pcity);
			}
		}

		/*
		 * Now handle one-attack units which attacked the previous turn. These
		 * go home this turn, and don't auto-attack again until the next turn.
		 * This could be done better, but this is a start and should stop
		 * bombers from running out of fuel --dwp. (Normally units with
		 * ai.control do not have goto done automatically.)
		 */
		for (unit punit : pplayer.units.data) {
			if (punit.ai.control && is_military_unit(punit)
					&& punit.activity == unit_activity.ACTIVITY_GOTO
					&& punit.moves_left == punit.move_rate()) {
				Gotohand.do_unit_goto(punit,
						goto_move_restriction.GOTO_MOVE_ANY, false);
			}
		}
	}

	/***************************************************************************
	 * ...
	 **************************************************************************/
	void auto_attack() {
		// FIXME
		// static timer t = null; /* alloc once, never free */
		//
		// t = renew_timer_start(t, TIMER_CPU, TIMER_DEBUG);
		//
		// /* re-use shuffle order from civserver.c */
		// shuffled_for(player pplayer: game.players){
		// auto_attack_player(pplayer);
		// } shuffled_players_iterate_end;
		// if (timer_in_use(t)) {
		// util.freelog(Log.LOG_VERBOSE, "autoattack consumed %g milliseconds.",
		// 1000.0*read_timer_seconds(t));
		// }
	}
}