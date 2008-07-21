package server;
import static common.Game.game;
import static server.Plrhand.notify_player;
import static server.Plrhand.notify_player_ex;
import port.util;
import server.gamelog.EEndGameState;
import server.gamelog.EGamelog;
import server.spaceace.player_spaceship;
import utility.Log;
import utility.Shared;
import utility.Speclists;

import common.Connection;
import common.Nation;
import common.Spaceship;
import common.event_type;
import common.game.server_states;
import common.packet_gen.packet_spaceship_info;
import common.packets.spaceship_place_type;
import common.play_spaceship.spaceship_state;
import common.player.player;

public class Spacerace{


	/***************************************************************************
	 * Calculate and fill in the derived quantities about the spaceship. Data
	 * reverse engineered from Civ1. --dwp This could be in common, but its
	 * better for the client to take the values the server calculates, in case
	 * things change.
	 **************************************************************************/
	void spaceship_calc_derived(player_spaceship ship)
	{
		int i;
		/* these are how many are connected: */
		int fuel=0;
		int propulsion=0;
		int habitation=0;
		int life_support=0;
		int solar_panels=0;

		assert(ship.structurals <= player_spaceship.NUM_SS_STRUCTURALS);
		assert(ship.components <= player_spaceship.NUM_SS_COMPONENTS);
		assert(ship.modules <= player_spaceship.NUM_SS_MODULES);

		ship.mass = 0;
		ship.support_rate = ship.energy_rate =
			ship.success_rate = ship.travel_time = (float) 0.0;

		for(i=0; i<player_spaceship.NUM_SS_STRUCTURALS; i++) {
			if (ship.structure[i]) {
				ship.mass += (i<6) ? 200 : 100;
				/*
				 * s0 to s3 are heavier; actually in Civ1 its a bit stranger
				 * than this, but not worth figuring out --dwp
				 */
			}
		}
		for(i=0; i<ship.fuel; i++) {
			if (ship.structure[Spaceship.components_info[i*2].required]) fuel++;
		}
		for(i=0; i<ship.propulsion; i++) {
			if (ship.structure[Spaceship.components_info[i*2+1].required]) propulsion++;
		}
		for(i=0; i<ship.habitation; i++) {
			if (ship.structure[Spaceship.modules_info[i*3].required]) habitation++;
		}
		for(i=0; i<ship.life_support; i++) {
			if (ship.structure[Spaceship.modules_info[i*3+1].required]) life_support++;
		}
		for(i=0; i<ship.solar_panels; i++) {
			if (ship.structure[Spaceship.modules_info[i*3+2].required]) solar_panels++;
		}

		ship.mass += 1600 * (habitation + life_support)
		+ 400 * (solar_panels + propulsion + fuel);

		ship.population = habitation * 10000;

		if (habitation > 0) {
			ship.support_rate = (float) (life_support / (double) habitation);
		}
		if (life_support + habitation > 0) {
			ship.energy_rate = (float) (2.0 * solar_panels / (double)(life_support+habitation));
		}
		if (fuel>0 && propulsion>0) {
			ship.success_rate =
				(float) (Math.min(ship.support_rate, 1.0) * Math.min(ship.energy_rate, 1.0));
		}

		/*
		 * The Success% can be less by up to a few % in some cases (I think if P !=
		 * F or if P and/or F too small (eg <= 2?) ?) but probably not worth
		 * worrying about. Actually, the Civ1 manual suggests travel time is
		 * relevant. --dwp
		 */

		ship.travel_time = (float) (ship.mass / (200.0 * Math.min(propulsion,fuel) + 20.0));

	}

	/***************************************************************************
	 * Send details of src's spaceship (or spaceships of all players if src is
	 * null) to specified destinations. If dest is null then
	 * game.game_connections is used.
	 **************************************************************************/
	void send_spaceship_info(player src, Speclists<Connection> dest)
	{
		int j;

		if (null==dest) dest = game.game_connections;

		for(player pplayer: game.players){
			if (null==src || pplayer == src) {
				packet_spaceship_info info = new packet_spaceship_info();
				player_spaceship ship = pplayer.spaceship;

				info.player_num = pplayer.player_no;
				info.sship_state = ship.state.ordinal();
				info.structurals = ship.structurals;
				info.components = ship.components;
				info.modules = ship.modules;
				info.fuel = ship.fuel;
				info.propulsion = ship.propulsion;
				info.habitation = ship.habitation;
				info.life_support = ship.life_support;
				info.solar_panels = ship.solar_panels;
				info.launch_year = ship.launch_year;
				info.population = ship.population;
				info.mass = ship.mass;
				info.support_rate = ship.support_rate;
				info.energy_rate = ship.energy_rate;
				info.success_rate = ship.success_rate;
				info.travel_time = ship.travel_time;

				for(j=0; j<player_spaceship.NUM_SS_STRUCTURALS; j++) {
					info.structure[j] = ship.structure[j] ? '1' : '0';
				}
				info.structure[j] = '\0';

				info.lsend_packet_spaceship_info(dest);
			}
		}
	}

	/***************************************************************************
	 * ...
	 **************************************************************************/
	void handle_spaceship_launch(player pplayer)
	{
		player_spaceship ship = pplayer.spaceship;
		int arrival;

		if (null==pplayer.find_palace()) {
			notify_player(pplayer,
					("Game: You need to have a capital in order to launch "+
					"your spaceship."));
			return;
		}
		if (ship.state.ordinal() >= spaceship_state.SSHIP_LAUNCHED.ordinal()) {
			notify_player(pplayer, "Game: Your spaceship is already launched!");
			return;
		}
		if (ship.state != spaceship_state.SSHIP_STARTED
				|| ship.success_rate == 0.0) {
			notify_player(pplayer, "Game: Your spaceship can't be launched yet!");
			return;
		}

		ship.state = spaceship_state.SSHIP_LAUNCHED;
		ship.launch_year = game.year;
		arrival = ship.launch_year + (int) ship.travel_time;

		notify_player_ex(null, null, event_type.E_SPACESHIP,
				("Game: The %s have launched a spaceship!  "+
				"It is estimated to arrive on Alpha Centauri in %s."),
				Nation.get_nation_name_plural(pplayer.nation),
				Shared.textyear(arrival));

		send_spaceship_info(pplayer, null);
	}

	/***************************************************************************
	 * ...
	 **************************************************************************/
	void handle_spaceship_place(player pplayer,
			spaceship_place_type type, int num)
	{
		player_spaceship ship = pplayer.spaceship;

		if (ship.state == spaceship_state.SSHIP_NONE) {
			notify_player(pplayer, ("Game: Spaceship action received,"+
			" but you don't have a spaceship!"));
			return;
		}
		if (ship.state.ordinal() >= spaceship_state.SSHIP_LAUNCHED.ordinal()) {
			notify_player(pplayer, ("Game: You can't modify your"+
			" spaceship after launch!"));
			return;
		}
		if (type == spaceship_place_type.SSHIP_PLACE_STRUCTURAL) {
			if (num<0 || num>=player_spaceship.NUM_SS_STRUCTURALS || ship.structure[num]) {
				return;
			}
			if (ship.num_spaceship_structurals_placed() >= ship.structurals) {
				notify_player(pplayer, ("Game: You don't have any unplaced"+
				" Space Structurals!"));
				return;
			}
			if (num!=0 && !ship.structure[Spaceship.structurals_info[num].required]) {
				notify_player(pplayer, ("Game: That Space Structural"+
				" would not be connected!"));
				return;
			}
			ship.structure[num] = true;
			spaceship_calc_derived(ship);
			send_spaceship_info(pplayer, null);
			return;
		}
		if (type == spaceship_place_type.SSHIP_PLACE_FUEL) {
			if (ship.fuel != num-1) {
				return;
			}
			if (ship.fuel + ship.propulsion >= ship.components) {
				notify_player(pplayer, ("Game: You don't have any unplaced"+
				" Space Components!"));
				return;
			}
			if (num > player_spaceship.NUM_SS_COMPONENTS/2) {
				notify_player(pplayer, ("Game: Your spaceship already has"+
				" the maximum number of Fuel Components!"));
				return;
			}
			ship.fuel++;
			spaceship_calc_derived(ship);
			send_spaceship_info(pplayer, null);
			return;
		}
		if (type == spaceship_place_type.SSHIP_PLACE_PROPULSION) {
			if (ship.propulsion != num-1) {
				return;
			}
			if (ship.fuel + ship.propulsion >= ship.components) {
				notify_player(pplayer, ("Game: You don't have any unplaced"+
				" Space Components!"));
				return;
			}
			if (num > player_spaceship.NUM_SS_COMPONENTS/2) {
				notify_player(pplayer, ("Game: Your spaceship already has the"+
				" maximum number of Propulsion Components!"));
				return;
			}
			ship.propulsion++;
			spaceship_calc_derived(ship);
			send_spaceship_info(pplayer, null);
			return;
		}
		if (type == spaceship_place_type.SSHIP_PLACE_HABITATION) {
			if (ship.habitation != num-1) {
				return;
			}
			if (ship.habitation + ship.life_support + ship.solar_panels
					>= ship.modules) {
				notify_player(pplayer, ("Game: You don't have any unplaced"+
				" Space Modules!"));
				return;
			}
			if (num > player_spaceship.NUM_SS_MODULES/3) {
				notify_player(pplayer, ("Game: Your spaceship already has the"+
				" maximum number of Habitation Modules!"));
				return;
			}
			ship.habitation++;
			spaceship_calc_derived(ship);
			send_spaceship_info(pplayer, null);
			return;
		}
		if (type == spaceship_place_type.SSHIP_PLACE_HABITATION) {
			if (ship.life_support != num-1) {
				return;
			}
			if (ship.habitation + ship.life_support + ship.solar_panels
					>= ship.modules) {
				notify_player(pplayer, ("Game: You don't have any unplaced"+
				" Space Modules!"));
				return;
			}
			if (num > player_spaceship.NUM_SS_MODULES/3) {
				notify_player(pplayer, ("Game: Your spaceship already has the"+
				" maximum number of Life Support Modules!"));
				return;
			}
			ship.life_support++;
			spaceship_calc_derived(ship);
			send_spaceship_info(pplayer, null);
			return;
		}
		if (type == spaceship_place_type.SSHIP_PLACE_HABITATION) {
			if (ship.solar_panels != num-1) {
				return;
			}
			if (ship.habitation + ship.life_support + ship.solar_panels
					>= ship.modules) {
				notify_player(pplayer, ("Game: You don't have any unplaced"+
				" Space Modules!"));
				return;
			}
			if (num > player_spaceship.NUM_SS_MODULES/3) {
				notify_player(pplayer, ("Game: Your spaceship already has the"+
				" maximum number of Solar Panel Modules!"));
				return;
			}
			ship.solar_panels++;
			spaceship_calc_derived(ship);
			send_spaceship_info(pplayer, null);
			return;
		}
		util.freelog(Log.LOG_ERROR, "Received unknown spaceship place type %d from %s",
				type, pplayer.name);
	}



	/***************************************************************************
	 * ...
	 **************************************************************************/
	void spaceship_lost(player pplayer)
	{
		notify_player_ex(null, null, event_type.E_SPACESHIP,
				("Game: Without guidance from the capital, the %s "+
				"spaceship is lost!"),
				Nation.get_nation_name(pplayer.nation));
		pplayer.spaceship.init();
		send_spaceship_info(pplayer, null);
	}

	/***************************************************************************
	 * Use shuffled order to randomly resolve ties.
	 **************************************************************************/
	void check_spaceship_arrivals()
	{
		double arrival, best_arrival = 0.0;
		player best_pplayer = null;

		for(player pplayer: game.players){
			player_spaceship ship = pplayer.spaceship;

			if (ship.state == spaceship_state.SSHIP_LAUNCHED) {
				arrival = ship.launch_year + ship.travel_time;
				if (game.year >= (int)arrival
						&& (null==best_pplayer || arrival < best_arrival)) {
					best_arrival = arrival;
					best_pplayer = pplayer;
				}
			}
		} 
		if (best_pplayer!=null) {
			best_pplayer.spaceship.state = spaceship_state.SSHIP_ARRIVED;
			Srv_main.server_state = server_states.GAME_OVER_STATE;
			notify_player_ex(null, null, event_type.E_SPACESHIP,
					("Game: The %s spaceship has arrived "+
					"at Alpha Centauri."),
					Nation.get_nation_name(best_pplayer.nation));
			Gamelog.gamelog(EGamelog.GAMELOG_JUDGE, EEndGameState.GL_LONEWIN, best_pplayer);
		}
	}
}