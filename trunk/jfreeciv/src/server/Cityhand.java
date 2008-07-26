package server;

import port.util;
import utility.Log;

import common.City;
import common.Connection;
import common.Effects;
import common.Game;
import common.Improvement;
import common.Player_P;
import common.Unittype_P;
import common.event_type;
import common.worklist;
import common.city.city;
import common.city.city_tile_type;
import common.city.specialist_type;
import common.effects.effect_type;
import common.packet_gen.packet_city_incite_info;
import common.packet_gen.packet_city_name_suggestion_info;
import common.player.player;
import common.unit.unit;

public class Cityhand{
	/**************************************************************************
	 * Send city_name_suggestion packet back to requesting conn, with suggested
	 * name and with same id which was passed in (either unit id for city
	 * builder or existing city id for rename, we don't care here).
	 **************************************************************************/
	void handle_city_name_suggestion_req(player pplayer, int value)
	{
		unit punit = Player_P.player_find_unit_by_id(pplayer, value);

		if (null==punit) {
			return;
		}

		util.freelog(Log.LOG_VERBOSE, "handle_city_name_suggest_req(unit_pos=(%d,%d))",
				punit.tile.x, punit.tile.y);

		packet_city_name_suggestion_info.dlsend_packet_city_name_suggestion_info(pplayer.connections, value, 
				Citytools.city_name_suggestion(pplayer, punit.tile));
	}

	/**************************************************************************
...
	 **************************************************************************/
	void handle_city_change_specialist(player pplayer, int city_id,
			specialist_type from,
			specialist_type to)
	{
		city pcity = Player_P.player_find_city_by_id(pplayer, city_id);

		if (null==pcity) {
			return;
		}

		if (to.ordinal() < 0 || to.ordinal() >= specialist_type.SP_COUNT.ordinal()
				|| from.ordinal() < 0 || from.ordinal() >= specialist_type.SP_COUNT.ordinal()
				|| !City.city_can_use_specialist(pcity, to)
				|| pcity.specialists[from.ordinal()] == 0) {
			util.freelog(Log.LOG_ERROR, "Error in specialist change request from client.");
			return;
		}

		pcity.specialists[from.ordinal()]--;
		pcity.specialists[to.ordinal()]++;

		Sanitycheck.sanity_check_city(pcity);
		Cityturn.city_refresh(pcity);
		Citytools.send_city_info(pplayer, pcity);
	}

	/**************************************************************************
...
	 **************************************************************************/
	void handle_city_make_specialist(player pplayer, int city_id,
			int worker_x, int worker_y)
	{
		city pcity = Player_P.player_find_city_by_id(pplayer, city_id);

		if (null==pcity) {
			return;
		}
		if (City.is_city_center(worker_x, worker_y)) {
			Cityturn.auto_arrange_workers(pcity);
			Citytools.sync_cities();
			return;
		}
		if (City.is_worker_here(pcity, worker_x, worker_y)) {
			Citytools.server_remove_worker_city(pcity, worker_x, worker_y);
			pcity.specialists[specialist_type.SP_ELVIS.ordinal()]++;
			Cityturn.city_refresh(pcity);
			Citytools.sync_cities();
		} else {
			Plrhand.notify_player_ex(pplayer, pcity.tile, event_type.E_NOEVENT,
					"Game: You don't have a worker here."); 
		}
		Sanitycheck.sanity_check_city(pcity);
	}

	/**************************************************************************
...
	 **************************************************************************/
	void handle_city_make_worker(player pplayer, int city_id,
			int worker_x, int worker_y)
	{
		city pcity = Player_P.player_find_city_by_id(pplayer, city_id);
		int i;

		if (!City.is_valid_city_coords(worker_x, worker_y)) {
			util.freelog(Log.LOG_ERROR, "invalid city coords %d,%d in package",
					worker_x, worker_y);
			return;
		}

		if (null==pcity) {
			return;
		}

		if (City.is_city_center(worker_x, worker_y)) {
			Cityturn.auto_arrange_workers(pcity);
			Citytools.sync_cities();
			return;
		}

		if (City.city_specialists(pcity) == 0
				|| City.get_worker_city(pcity, worker_x, worker_y) != city_tile_type.C_TILE_EMPTY)
			return;

		Citytools.server_set_worker_city(pcity, worker_x, worker_y);

		for (i = 0; i < specialist_type.values().length; i++) {
			if (pcity.specialists[i] > 0) {
				pcity.specialists[i]--;
				break;
			}
		}
		assert(i < specialist_type.getSize());

		Sanitycheck.sanity_check_city(pcity);
		Cityturn.city_refresh(pcity);
		Citytools.sync_cities();
	}

	/**************************************************************************
...
	 **************************************************************************/
	void really_handle_city_sell(player pplayer, city pcity,
			int id)
	{  
		if (pcity.did_sell) {
			Plrhand.notify_player_ex(pplayer, pcity.tile, event_type.E_NOEVENT, 
					"Game: You have already sold something here this turn.");
			return;
		}

		if (!Citytools.can_sell_building(pcity, id))
			return;

		pcity.did_sell=true;
		Plrhand.notify_player_ex(pplayer, pcity.tile, event_type.E_IMP_SOLD,
				"Game: You sell %s in %s for %d gold.", 
				Improvement.get_improvement_name(id), pcity.name,
				Improvement.impr_sell_gold(id));
		Citytools.do_sell_building(pplayer, pcity, id);

		Cityturn.city_refresh(pcity);

		/* If we sold the walls the other players should see it */
		Citytools.send_city_info(null, pcity);
		Plrhand.send_player_info(pplayer, pplayer);
	}

	/**************************************************************************
...
	 **************************************************************************/
	void handle_city_sell(player pplayer, int city_id, int build_id)
	{
		city pcity = Player_P.player_find_city_by_id(pplayer, city_id);

		if (null==pcity || build_id >= Game.game.num_impr_types) {
			return;
		}
		really_handle_city_sell(pplayer, pcity, build_id);
	}

	/**************************************************************************
...
	 **************************************************************************/
	void really_handle_city_buy(player pplayer, city pcity)
	{
		final String name;
		int cost, total;

		assert(pcity!=null && Player_P.player_owns_city(pplayer, pcity));

		if (pcity.turn_founded == Game.game.turn) {
			Plrhand.notify_player_ex(pplayer, pcity.tile, event_type.E_NOEVENT,
					"Game: Cannot buy in city created this turn.");
			return;
		}

		if (!City.city_can_change_build(pcity)) {
			Plrhand.notify_player_ex(pplayer, pcity.tile, event_type.E_NOEVENT,
					"Game: You have already bought this turn.");
			return;
		}

		if (Effects.get_current_finalruction_bonus(pcity, effect_type.EFT_PROD_TO_GOLD) > 0) {
			assert(!pcity.is_building_unit);
			Plrhand.notify_player_ex(pplayer, pcity.tile, event_type.E_NOEVENT,
					"Game: You don't buy %s!",
					Improvement.improvement_types[pcity.currently_building].name);
			return;
		}

		if (pcity.is_building_unit && pcity.anarchy != 0) {
			Plrhand.notify_player_ex(pplayer, pcity.tile, event_type.E_NOEVENT, 
					"Game: Can't buy units when city is in disorder.");
			return;
		}

		if (pcity.is_building_unit) {
			name = Unittype_P.unit_types[pcity.currently_building].name;
			total = Unittype_P.unit_build_shield_cost(pcity.currently_building);
		} else {
			name = Improvement.get_improvement_name(pcity.currently_building);
			total = Improvement.impr_build_shield_cost(pcity.currently_building);
		}
		cost = City.city_buy_cost(pcity);
		if (cost == 0 || cost > pplayer.economic.gold) {
			return;
		}

		/*
		 * Need to make this more restrictive.  AI is sometimes buying
		 * things that force it to sell buildings due to upkeep problems.
		 * upkeep expense is only known in ai_manage_taxes().
		 * Also, we should sort this list so cheapest things are bought first,
		 * and/or take danger into account.
		 * AJS, 1999110
		 */

		pplayer.economic.gold-=cost;
		if (pcity.shield_stock < total){
			/* As we never put penalty on disbanded_shields, we can
			 * fully well add the missing shields there. */
			pcity.disbanded_shields += total - pcity.shield_stock;
			pcity.shield_stock=total; /* AI wants this -- Syela */
			pcity.did_buy = true;	/* !PS: no need to set buy flag otherwise */
		}
		Cityturn.city_refresh(pcity);

		Connection.conn_list_do_buffer(pplayer.connections);
		Plrhand.notify_player_ex(pplayer, pcity.tile, 
				pcity.is_building_unit?event_type.E_UNIT_BUY:event_type.E_IMP_BUY,
						"Game: %s bought in %s for %d gold.", 
						name, pcity.name, cost);
		Citytools.send_city_info(pplayer, pcity);
		Plrhand.send_player_info(pplayer,pplayer);
		Connection.conn_list_do_unbuffer(pplayer.connections);
	}

	/**************************************************************************
...
	 **************************************************************************/
	void handle_city_worklist(player pplayer, int city_id,
			worklist w)
	{
		city pcity = Player_P.player_find_city_by_id(pplayer, city_id);

		if (null==pcity) {
			return;
		}

		worklist.copy_worklist(pcity.worklist, w);

		Citytools.send_city_info(pplayer, pcity);
	}

	/**************************************************************************
...
	 **************************************************************************/
	void handle_city_buy(player pplayer, int city_id)
	{
		city pcity = Player_P.player_find_city_by_id(pplayer, city_id);

		if (null==pcity) {
			return;
		}

		really_handle_city_buy(pplayer, pcity);
	}

	/**************************************************************************
...
	 **************************************************************************/
	void handle_city_refresh(player pplayer, int city_id)
	{
		if (city_id != 0) {
			city pcity = Player_P.player_find_city_by_id(pplayer, city_id);

			if (null==pcity) {
				return;
			}

			Cityturn.city_refresh(pcity);
			Citytools.send_city_info(pplayer, pcity);
		} else {
			Cityturn.global_city_refresh(pplayer);
		}
	}

	/**************************************************************************
...
	 **************************************************************************/
	void handle_city_change(player pplayer, int city_id, int build_id,
			boolean is_build_id_unit_id)
	{
		city pcity = Player_P.player_find_city_by_id(pplayer, city_id);

		if (null==pcity) {
			return;
		}

		if (pcity.is_building_unit == is_build_id_unit_id
				&& pcity.currently_building == build_id) {
			/* The client probably shouldn't send such a packet. */
			return;
		}

		if (is_build_id_unit_id && !City.can_build_unit(pcity, build_id))
			return;
		if (!is_build_id_unit_id && !City.can_build_improvement(pcity, build_id))
			return;
		if (pcity.did_buy && pcity.shield_stock > 0) {
			Plrhand.notify_player_ex(pplayer, pcity.tile, event_type.E_NOEVENT,
					"Game: You have bought this turn, can't change.");
			return;
		}

		Citytools.change_build_target(pplayer, pcity, build_id, is_build_id_unit_id,
				event_type.E_NOEVENT);

		Sanitycheck.sanity_check_city(pcity);
		Cityturn.city_refresh(pcity);
		Citytools.send_city_info(pplayer, pcity);
	}

	/**************************************************************************
...
	 **************************************************************************/
	void handle_city_rename(player pplayer, int city_id, String name)
	{
		city pcity = Player_P.player_find_city_by_id(pplayer, city_id);
//		char message[1024];
		String message = "";

		if (null==pcity) {
			return;
		}

		if (!Citytools.is_allowed_city_name(pplayer, name, message, message.length())) {
			Plrhand.notify_player_ex(pplayer, pcity.tile, event_type.E_NOEVENT,
					"Game: %s",  message);
			return;
		}

		pcity.name = name;
		Cityturn.city_refresh(pcity);
		Citytools.send_city_info(null, pcity);
	}

	/**************************************************************************
...
	 **************************************************************************/
	void handle_city_options_req(player pplayer, int city_id, int value)
	{
		city pcity = Player_P.player_find_city_by_id(pplayer, city_id);

		if (null==pcity) {
			return;
		}

		pcity.city_options = value;
		Citytools.send_city_info(pplayer, pcity);
	}

	/***************************************************************
  Tell the client the cost of inciting a revolt or bribing a unit.
  Only send result back to the requesting Connection, not all
  connections for that player.
	 ***************************************************************/
	void handle_city_incite_inq(Connection pc, int city_id)
	{
		player pplayer = pc.player;
		city pcity = Game.find_city_by_id(city_id);

		if (pplayer!=null && pcity!=null) {
			packet_city_incite_info.dsend_packet_city_incite_info(pc, city_id,
					Cityturn.city_incite_cost(pplayer, pcity));
		}
	}
}