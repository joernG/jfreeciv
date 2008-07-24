package server;

import utility.Speclists;
import common.Connection;
import common.Game;
import common.event_type;
import common.map.tile;

import common.player.player;

public class Plrhand {
	// /**************************************************************************
	// ...
	// **************************************************************************/
	// void do_dipl_cost(player pplayer)
	// {
	// pplayer.research.bulbs_researched -=
	// (total_bulbs_required(pplayer) * Game.game.diplcost) / 100;
	// pplayer.research.changed_from = -1;
	// }
	//
	// /**************************************************************************
	// ...
	// **************************************************************************/
	// void do_free_cost(player pplayer)
	// {
	// pplayer.research.bulbs_researched -=
	// (total_bulbs_required(pplayer) * Game.game.freecost) / 100;
	// pplayer.research.changed_from = -1;
	// }
	//
	// /**************************************************************************
	// ...
	// **************************************************************************/
	// void do_conquer_cost(player pplayer)
	// {
	// pplayer.research.bulbs_researched -=
	// (total_bulbs_required(pplayer) * Game.game.conquercost) / 100;
	// pplayer.research.changed_from = -1;
	// }
	//
	// /**************************************************************************
	// Send end-of-turn notifications relevant to specified dests.
	// If dest is null, do all players, sending to pplayer.connections.
	// **************************************************************************/
	// void send_player_turn_notifications(Speclists<Connection> dest)
	// {
	// if (dest) {
	// conn_list_iterate(*dest, pconn) {
	// player pplayer = pconn.player;
	// if (pplayer) {
	// for (city pcity : pplayer.cities.data) {
	// send_city_turn_notifications(&pconn.self, pcity);
	// }
	// }
	// }
	// }
	// }
	// }
	// else {
	// for(player pplayer: Game.game.players){
	// for (city pcity : pplayer.cities.data) {
	// send_city_turn_notifications(&pplayer.connections, pcity);
	// } }
	// }
	// }
	//
	// send_global_city_turn_notifications(dest);
	// }
	//
	// /**************************************************************************
	// Give technologies to players with EFT_TECH_PARASITE (traditionally from
	// the Great Library).
	// **************************************************************************/
	// void do_tech_parasite_effect(player pplayer)
	// {
	// int mod;
	// struct effect_source_vector sources;
	//
	// /* Note that two EFT_TECH_PARASITE effects will combine into a single,
	// * much worse effect. */
	// if ((mod = get_player_bonus_sources(&sources, pplayer,
	// EFT_TECH_PARASITE)) > 0) {
	// char buf[512];
	//
	// buf[0] = '\0';
	// effect_source_vector_iterate(&sources, src) {
	// if (buf[0] != '\0') {
	// sz_strlcat(buf, ", ");
	// }
	// sz_strlcat(buf, get_improvement_name(src.building));
	// } effect_source_vector_iterate_end;
	//
	// tech_type_iterate(i) {
	// if (get_invention(pplayer, i) != TECH_KNOWN
	// && tech_is_available(pplayer, i)
	// && Game.game.global_advances[i] >= mod) {
	// Plrhand.notify_player_ex(pplayer, null, E_TECH_GAIN,
	// "Game: %s acquired from %s!",
	// get_tech_name(pplayer, i), buf);
	// Gamelog.gamelog(GAMELOG_TECH, pplayer, null, i, "steal");
	// notify_embassies(pplayer, null,
	// "Game: The %s have acquired %s from %s.",
	// Nation.get_nation_name_plural(pplayer.nation),
	// get_tech_name(pplayer, i), buf);
	//
	// do_free_cost(pplayer);
	// found_new_tech(pplayer, i, false, true, A_NONE);
	// break;
	// }
	// } tech_type_iterate_end;
	// }
	// effect_source_vector_free(&sources);
	// }
	//
	// /****************************************************************************
	// Check all players to see if they are dying. Kill them if so.
	//
	// WARNING: do not call this while doing any handling of players, units,
	// etc. If a player util.dies, all his units will be wiped and other data will
	// be overwritten.
	// ****************************************************************************/
	// void kill_dying_players()
	// {
	// for(player pplayer: Game.game.players){
	// if (pplayer.is_alive) {
	// if (pplayer.units.foo_list_size() == 0
	// && pplayer.cities.foo_list_size() == 0) {
	// pplayer.is_dying = true;
	// }
	// if (pplayer.is_dying) {
	// kill_player(pplayer);
	// }
	// }
	// }
	// }
	//
	// /**************************************************************************
	// Murder a player in cold blood.
	// **************************************************************************/
	// void kill_player(player pplayer) {
	// boolean palace;
	//
	// pplayer.is_dying = false; /* Can't get more dead than this. */
	// pplayer.is_alive = false;
	//
	// /* Remove shared vision from dead player to friends. */
	// for(player aplayer: Game.game.players){
	// if (gives_shared_vision(pplayer, aplayer)) {
	// remove_shared_vision(pplayer, aplayer);
	// }
	// }
	//    
	// cancel_all_meetings(pplayer);
	//
	// /* Show entire map for players who are *not* in a team. */
	// if (pplayer.team == TEAM_NONE) {
	// map_know_and_see_all(pplayer);
	// }
	//
	// if (is_barbarian(pplayer)) {
	// Gamelog.gamelog(GAMELOG_GENO, pplayer,
	// "The feared barbarian leader %s is no more");
	// return;
	// } else {
	// Plrhand.notify_player_ex(null, null, E_DESTROYED, "Game: The %s are no more!",
	// Nation.get_nation_name_plural(pplayer.nation));
	// Gamelog.gamelog(GAMELOG_GENO, pplayer, "%s civilization destroyed");
	// }
	//
	// /* Transfer back all cities not originally owned by player to their
	// rightful owners, if they are still around */
	// palace = Game.game.savepalace;
	// Game.game.savepalace = false; /* moving it around is dumb */
	// for (city pcity : pplayer.cities.data) {
	// if ((pcity.original != pplayer.player_no)
	// && (get_player(pcity.original).is_alive)) {
	// /* Transfer city to original owner, kill all its units outside of
	// a radius of 3, give verbose messages of every unit transferred,
	// and raze buildings according to raze chance (also removes palace) */
	// transfer_city(get_player(pcity.original), pcity, 3, true, true, true);
	// }
	// } }
	//
	// /* Remove all units that are still ours */
	// for (unit punit : pplayer.units.data) {
	// Unittools.wipe_unit(punit);
	// }
	//
	// /* Destroy any remaining cities */
	// for (city pcity : pplayer.cities.data) {
	// remove_city(pcity);
	// } }
	// Game.game.savepalace = palace;
	//
	// /* Ensure this dead player doesn't win with a spaceship.
	// * Now that would be truly unbelievably dumb - Per */
	// spaceship_init(&pplayer.spaceship);
	// send_spaceship_info(pplayer, null);
	//
	// send_player_info_c(pplayer, &Game.game.est_connections);
	// }
	//
	// /**************************************************************************
	// Player has a new technology (from somewhere). was_discovery is passed
	// on to upgrade_city_rails. Logging & notification is not done here as
	// it depends on how the tech came. If next_tech is other than A_NONE, this
	// is the next tech to research.
	// **************************************************************************/
	// void found_new_tech(player plr, int tech_found, boolean was_discovery,
	// boolean saving_bulbs, int next_tech)
	// {
	// boolean bonus_tech_hack = false;
	// boolean was_first = false;
	// boolean had_embassy[Shared_H.MAX_NUM_PLAYERS + Shared_H.MAX_NUM_BARBARIANS];
	// city pcity;
	//
	// for(player aplr: Game.game.players){
	// had_embassy[aplr.player_no]
	// = (get_player_bonus(aplr, EFT_HAVE_EMBASSIES) > 0);
	// }
	//
	// /* This is a hack which makes buggy team research work somehow
	// in 2.0 branch */
	// if (tech_exists(tech_found)
	// && get_invention(plr, tech_found) == TECH_KNOWN) {
	// util.freelog(Log.LOG_ERROR, "Error: found_new_tech() was called on already "
	// "researched technology %s for player %s",
	// get_tech_name(plr, tech_found), plr.name);
	// util.freelog(Log.LOG_ERROR, "Report this bug at <bugs@freeciv.org>\n"
	// "Here is some info you should attach:");
	// for(player eplayer: Game.game.players){
	// util.freelog(Log.LOG_ERROR,
	// "Player %s(team %d): researching %s;\n bulbs_researched %d; "
	// "techs_researched: %d; bulbs_last_turn: %d; Researched %s? %s",
	// eplayer.name,
	// eplayer.team,
	// get_tech_name(eplayer, eplayer.research.researching),
	// eplayer.research.bulbs_researched,
	// eplayer.research.techs_researched,
	// eplayer.research.bulbs_last_turn,
	// get_tech_name(plr, tech_found),
	// get_invention(eplayer, tech_found) == TECH_KNOWN ? "yes" : "no");
	// }
	// }
	//
	// /* HACK: A_FUTURE doesn't "exist" and is thus not "available". This may
	// * or may not be the correct thing to do. For these sanity checks we
	// * just special-case it. */
	// assert(tech_is_available(plr, tech_found) || tech_found == A_FUTURE);
	//
	// /* got_tech allows us to change research without applying techpenalty
	// * (without loosing bulbs) */
	// if (tech_found == plr.research.researching) {
	// plr.got_tech = true;
	// }
	//
	// plr.research.changed_from = -1;
	// plr.research.techs_researched++;
	// was_first = (Game.game.global_advances[tech_found] == 0);
	//
	// if (was_first) {
	// /* We used to have a Gamelog.gamelog() for first-researched, but not anymore. */
	//
	// /* Alert the owners of any wonders that have been made obsolete */
	// impr_type_iterate(id) {
	// if (Game.game.global_wonders[id] != 0 && is_wonder(id) &&
	// improvement_types[id].obsolete_by == tech_found &&
	// (pcity = find_city_by_id(Game.game.global_wonders[id]))) {
	// Plrhand.notify_player_ex(City.city_owner(pcity), null, E_WONDER_OBSOLETE,
	// "Game: Discovery of %s OBSOLETES %s in %s!",
	// get_tech_name(City.city_owner(pcity), tech_found),
	// get_improvement_name(id),
	// pcity.name);
	// }
	// } impr_type_iterate_end;
	// }
	//
	// government_iterate(gov) {
	// if (tech_found == gov.required_tech) {
	// Plrhand.notify_player_ex(plr, null, E_NEW_GOVERNMENT,
	// ("Game: Discovery of %s makes the government form %s"
	// " available. You may want to start a revolution."),
	// get_tech_name(plr, tech_found), gov.name);
	// }
	// } government_iterate_end;
	//
	// if (tech_flag(tech_found, TF_BONUS_TECH) && was_first) {
	// bonus_tech_hack = true;
	// }
	//
	// set_invention(plr, tech_found, TECH_KNOWN);
	// update_research(plr);
	// remove_obsolete_buildings(plr);
	// if (tech_flag(tech_found,TF_RAILROAD)) {
	// upgrade_city_rails(plr, was_discovery);
	// }
	//
	// /* enhance vision of units inside a fortress */
	// if (tech_flag(tech_found, TF_WATCHTOWER)) {
	// for (unit punit : plr.units.data) {
	// if (Map.map_has_special(punit.tile, S_FORTRESS)
	// && is_ground_unit(punit)) {
	// unfog_area(plr, punit.tile, get_watchtower_vision(punit));
	// fog_area(plr, punit.tile,
	// punit.unit_type().vision_range);
	// }
	// }
	// }
	// }
	//
	// if (tech_found == plr.ai.tech_goal) {
	// plr.ai.tech_goal = A_UNSET;
	// }
	//
	// if (tech_found == plr.research.researching && next_tech == A_NONE) {
	// /* try to pick new tech to research */
	// Tech_Type_id next_tech = choose_goal_tech(plr);
	//
	// if (next_tech != A_UNSET) {
	// Plrhand.notify_player_ex(plr, null, E_TECH_LEARNED,
	// ("Game: Learned %s. "
	// "Our scientists focus on %s, goal is %s."),
	// get_tech_name(plr, tech_found),
	// get_tech_name(plr, plr.research.researching),
	// get_tech_name(plr, plr.ai.tech_goal));
	// } else {
	// plr.research.researching = A_UNSET;
	// if (plr.ai.control || !was_discovery) {
	// choose_random_tech(plr);
	// } else if (is_future_tech(tech_found)) {
	// /* Continue researching future tech. */
	// plr.research.researching = A_FUTURE;
	// }
	//      
	// if (plr.research.researching != A_UNSET
	// && (!is_future_tech(plr.research.researching)
	// || !is_future_tech(tech_found))) {
	// Plrhand.notify_player_ex(plr, null, E_TECH_LEARNED,
	// ("Game: Learned %s. Scientists "
	// "choose to research %s."),
	// get_tech_name(plr, tech_found),
	// get_tech_name(plr, plr.research.researching));
	// } else if (plr.research.researching != A_UNSET) {
	// char buffer1[300];
	// char buffer2[300];
	//
	// buffer1 = util.my_snprintf( "Game: Learned %s. ",
	// get_tech_name(plr, plr.research.researching));
	// plr.future_tech++;
	// buffer2 = util.my_snprintf( "Researching %s.",
	// get_tech_name(plr, plr.research.researching));
	// Plrhand.notify_player_ex(plr, null, E_TECH_LEARNED, "%s%s", buffer1,
	// buffer2);
	// } else {
	// Plrhand.notify_player_ex(plr, null, E_TECH_LEARNED,
	// ("Game: Learned %s. Scientists "
	// "do not know what to research next."),
	// get_tech_name(plr, tech_found));
	// }
	// }
	// } else if (tech_found == plr.research.researching && next_tech > A_NONE)
	// {
	// /* Next target already determined. We always save bulbs. */
	// plr.research.researching = next_tech;
	// }
	//
	// if (!saving_bulbs && plr.research.bulbs_researched > 0) {
	// plr.research.bulbs_researched = 0;
	// }
	//
	// if (bonus_tech_hack) {
	// if (advances[tech_found].bonus_message) {
	// notify_player(plr, "Game: %s",
	// _(advances[tech_found].bonus_message));
	// } else {
	// notify_player(plr, ("Game: Great scientists from all the "
	// "world join your civilization: you get "
	// "an immediate advance."));
	// }
	// if (plr.research.researching == A_UNSET) {
	// choose_random_tech(plr);
	// }
	// tech_researched(plr);
	// }
	//
	// /*
	// * Inform all players about new global advances to give them a
	// * chance to obsolete buildings.
	// */
	// Gamehand.send_game_info(null);
	//
	// /*
	// * Inform player about his new tech.
	// */
	// Plrhand.send_player_info(plr, plr);
	//  
	// /*
	// * Update all cities if the new tech affects happiness.
	// */
	// if (tech_found == Game.game.rtech.cathedral_plus
	// || tech_found == Game.game.rtech.cathedral_minus
	// || tech_found == Game.game.rtech.colosseum_plus
	// || tech_found == Game.game.rtech.temple_plus) {
	// for (city pcity : plr.cities.data) {
	// city_refresh(pcity);
	// send_city_info(plr, pcity);
	// } }
	// }
	//
	// /*
	// * Send all player an updated info of the owner of the Marco Polo
	// * Wonder if this wonder has become obsolete.
	// */
	// for(player owner: Game.game.players){
	// if (had_embassy[owner.player_no]
	// && get_player_bonus(owner, EFT_HAVE_EMBASSIES) == 0) {
	// for(player other_player: Game.game.players){
	// Plrhand.send_player_info(owner, other_player);
	// }
	// }
	// }
	//
	// /* Update Team */
	// if (next_tech > A_NONE) {
	// /* Avoid unnecessary recursion. */
	// return;
	// }
	//
	// for(player aplayer: Game.game.players){
	// if (plr != aplayer
	// && players_on_same_team(aplayer, plr)
	// && aplayer.is_alive
	// && get_invention(aplayer, tech_found) != TECH_KNOWN) {
	// if (tech_exists(plr.research.researching)) {
	// Plrhand.notify_player_ex(aplayer, null, E_TECH_LEARNED,
	// ("Game: Learned %s in cooperation with %s. "
	// "Scientists choose to research %s."),
	// get_tech_name(aplayer, tech_found), plr.name,
	// get_tech_name(plr, plr.research.researching));
	// } else {
	// Plrhand.notify_player_ex(aplayer, null, E_TECH_LEARNED,
	// ("Game: Learned %s in cooperation with %s. "
	// "Scientists do not know what to research next."),
	// get_tech_name(aplayer, tech_found), plr.name);
	// }
	// found_new_tech(aplayer, tech_found, was_discovery, saving_bulbs,
	// plr.research.researching);
	// }
	// }
	// }
	//
	// /**************************************************************************
	// Player has a new future technology (from somewhere). Logging &
	// notification is not done here as it depends on how the tech came.
	// **************************************************************************/
	// void found_new_future_tech(player pplayer)
	// {
	// pplayer.future_tech++;
	// pplayer.research.techs_researched++;
	// }
	//
	// /**************************************************************************
	// Player has researched a new technology
	// **************************************************************************/
	// static void tech_researched(struct player* plr)
	// {
	// /* plr will be notified when new tech is chosen */
	//
	// if (!is_future_tech(plr.research.researching)) {
	// notify_embassies(plr, null,
	// "Game: The %s have researched %s.",
	// Nation.get_nation_name_plural(plr.nation),
	// get_tech_name(plr, plr.research.researching));
	//
	// } else {
	// notify_embassies(plr, null,
	// "Game: The %s have researched Future Tech. %d.",
	// Nation.get_nation_name_plural(plr.nation),
	// plr.future_tech);
	//  
	// }
	// Gamelog.gamelog(GAMELOG_TECH, plr, null, plr.research.researching);
	//
	// /* Deduct tech cost */
	// plr.research.bulbs_researched =
	// MAX(plr.research.bulbs_researched - total_bulbs_required(plr), 0);
	//
	// /* do all the updates needed after finding new tech */
	// found_new_tech(plr, plr.research.researching, true, true, A_NONE);
	// }
	//
	// /**************************************************************************
	// Called from each city to update the research.
	// **************************************************************************/
	// void update_tech(player plr, int bulbs)
	// {
	// int excessive_bulbs;
	//
	// /* count our research contribution this turn */
	// plr.research.bulbs_last_turn += bulbs;
	//
	// for(player pplayer: Game.game.players){
	// if (pplayer == plr) {
	// pplayer.research.bulbs_researched += bulbs;
	// } else if (pplayer.diplstates[plr.player_no].type ==
	// diplstate_type.DS_TEAM
	// && pplayer.is_alive) {
	// /* Share with union partner(s). We'll get in return later. */
	// pplayer.research.bulbs_researched += bulbs;
	// }
	// }
	//  
	// excessive_bulbs =
	// (plr.research.bulbs_researched - total_bulbs_required(plr));
	//
	// if (excessive_bulbs >= 0 && plr.research.researching != A_UNSET) {
	// tech_researched(plr);
	// if (plr.research.researching != A_UNSET) {
	// update_tech(plr, 0);
	// }
	// }
	// }
	//
	// /**************************************************************************
	// ...
	// **************************************************************************/
	// Tech_Type_id choose_goal_tech(player plr)
	// {
	// Tech_Type_id sub_goal;
	//
	// if (plr.ai.control) {
	// ai_next_tech_goal(plr); /* tech-AI has been changed */
	// }
	// sub_goal = get_next_tech(plr, plr.ai.tech_goal);
	//
	// if (sub_goal == A_UNSET) {
	// if (plr.ai.control || plr.research.techs_researched == 1) {
	// ai_next_tech_goal(plr);
	// sub_goal = get_next_tech(plr, plr.ai.tech_goal);
	// } else {
	// plr.ai.tech_goal = A_UNSET; /* clear goal when it is achieved */
	// }
	// }
	//
	// if (sub_goal != A_UNSET) {
	// choose_tech(plr, sub_goal);
	// }
	// return sub_goal;
	// }
	//
	// /**************************************************************************
	// Returns random researchable tech or A_FUTURE.
	// No side effects
	// **************************************************************************/
	// static Tech_Type_id pick_random_tech(player plr)
	// {
	// int researchable, chosen;
	//  
	// researchable = 0;
	// tech_type_iterate(i) {
	// if (get_invention(plr, i) == TECH_REACHABLE) {
	// researchable++;
	// }
	// } tech_type_iterate_end;
	//  
	// if (researchable == 0) {
	// return A_FUTURE;
	// }
	// chosen = Rand.myrand(researchable) + 1;
	//  
	// tech_type_iterate(i) {
	// if (get_invention(plr, i) == TECH_REACHABLE) {
	// chosen--;
	// if (chosen == 0) {
	// return i;
	// }
	// }
	// } tech_type_iterate_end;
	// assert(0!=1);
	// return A_NONE;
	// }
	//
	// /****************************************************************************
	// Gives a player random tech, which he hasn't researched yet. Applies
	// freecost
	// Returns the tech.
	// ****************************************************************************/
	// Tech_Type_id give_random_free_tech(struct player* pplayer)
	// {
	// Tech_Type_id tech;
	//  
	// tech = pick_random_tech(pplayer);
	// do_free_cost(pplayer);
	// found_new_tech(pplayer, tech, false, true, A_NONE);
	// return tech;
	// }
	//
	//
	// /**************************************************************************
	// ...
	// **************************************************************************/
	// void choose_random_tech(player plr)
	// {
	// if (plr.research.researching != A_UNSET) {
	// util.freelog(Log.LOG_ERROR, "Error: choose_random_tech should only be called "
	// "when research target is A_UNSET. Please report this "
	// "bug at <bugs@freeeciv.org>.");
	// }
	//  
	// do {
	// Tech_Type_id tech = pick_random_tech(plr);
	// choose_tech(plr, tech);
	// } while (plr.research.researching == A_UNSET);
	// }
	//
	// /**************************************************************************
	// ...
	// **************************************************************************/
	// void choose_tech(player plr, int tech)
	// {
	// if (plr.research.researching == tech) {
	// return;
	// }
	//
	// if (tech != A_FUTURE && get_invention(plr, tech) != TECH_REACHABLE) {
	// /* can't research this */
	// return;
	// }
	//  
	// if (!plr.got_tech && plr.research.changed_from == -1) {
	// plr.research.bulbs_researched_before = plr.research.bulbs_researched;
	// plr.research.changed_from = plr.research.researching;
	// /* subtract a penalty because we changed subject */
	// if (plr.research.bulbs_researched > 0) {
	// plr.research.bulbs_researched -=
	// ((plr.research.bulbs_researched * Game.game.techpenalty) / 100);
	// assert(plr.research.bulbs_researched >= 0);
	// }
	// } else if (tech == plr.research.changed_from) {
	// plr.research.bulbs_researched = plr.research.bulbs_researched_before;
	// plr.research.changed_from = -1;
	// }
	// plr.research.researching=tech;
	// if (plr.research.bulbs_researched > total_bulbs_required(plr)) {
	// tech_researched(plr);
	// }
	// }
	//
	// void choose_tech_goal(player plr, int tech)
	// {
	// if (plr.ai.tech_goal != tech) {
	// notify_player(plr, "Game: Technology goal is %s.",
	// get_tech_name(plr, tech));
	// plr.ai.tech_goal = tech;
	// }
	// }

	/**************************************************************************
	 Initializes tech data for the player
	 **************************************************************************/
	public static void init_tech(player plr)
	{
//		tech_type_iterate(i) {
//			set_invention(plr, i, TECH_UNKNOWN);
//		} tech_type_iterate_end;
//		set_invention(plr, A_NONE, TECH_KNOWN);
//
//		plr.research.techs_researched = 1;
//
//		/* Mark the reachable techs */
//		update_research(plr);
//		if (choose_goal_tech(plr) == A_UNSET) {
//			choose_random_tech(plr);
//		}
	}

	/**************************************************************************
	 Gives initial techs to the player
	 **************************************************************************/
	public static void give_initial_techs(player plr)
	{
//		nation_type nation = get_nation_by_plr(plr);
//		int i;
//		/*
//		 * Give Game.game wide initial techs
//		 */
//		for (i = 0; i < MAX_NUM_TECH_LIST; i++) {
//			if (Game.game.rgame.global_init_techs[i] == A_LAST) {
//				break;
//			}
//			found_new_tech(plr, Game.game.rgame.global_init_techs[i], false, true,
//					A_NONE);
//		}
//
	// /*
	// * Give nation specific initial techs
	// */
	// for (i = 0; i < MAX_NUM_TECH_LIST; i++) {
	// if (nation.init_techs[i] == A_LAST) {
	// break;
	// }
	// found_new_tech(plr, nation.init_techs[i], false, true, A_NONE);
	// }
	}

	// /**************************************************************************
	// Gives a player random tech, which he hasn't researched yet.
	// Returns the tech. Does not apply free cost.
	// **************************************************************************/
	// Tech_Type_id give_random_initial_tech(struct player* pplayer)
	// {
	// Tech_Type_id tech;
	//  
	// tech = pick_random_tech(pplayer);
	// found_new_tech(pplayer, tech, false, true, A_NONE);
	// return true;
	// }
	//
	// /**************************************************************************
	// If target has more techs than pplayer, pplayer will get a random of
	// these, the clients will both be notified and the conquer cost
	// penalty applied. Used for diplomats and city conquest.
	// **************************************************************************/
	// void get_a_tech(player pplayer, player target)
	// {
	// Tech_Type_id stolen_tech;
	// int j=0;
	//
	// tech_type_iterate(i) {
	// if (get_invention(pplayer, i) != TECH_KNOWN
	// && get_invention(target, i) == TECH_KNOWN
	// && tech_is_available(pplayer, i)) {
	// j++;
	// }
	// } tech_type_iterate_end;
	// if (j == 0) {
	// /* we've moved on to future tech */
	// if (target.future_tech > pplayer.future_tech) {
	// found_new_future_tech(pplayer);
	// stolen_tech = A_FUTURE;
	// } else {
	// return; /* nothing to learn here, move on */
	// }
	// return;
	// } else {
	// /* pick random tech */
	// j = Rand.myrand(j) + 1;
	// stolen_tech = A_NONE; /* avoid compiler warning */
	// tech_type_iterate(i) {
	// if (get_invention(pplayer, i) != TECH_KNOWN
	// && get_invention(target, i) == TECH_KNOWN
	// && tech_is_available(pplayer, i)) {
	// j--;
	// }
	// if (j == 0) {
	// stolen_tech = i;
	// break;
	// }
	// } tech_type_iterate_end;
	// assert(stolen_tech != A_NONE);
	// }
	// Gamelog.gamelog(GAMELOG_TECH, pplayer, target, stolen_tech, "steal");
	//
	// Plrhand.notify_player_ex(pplayer, null, E_TECH_GAIN,
	// "Game: You steal %s from the %s.",
	// get_tech_name(pplayer, stolen_tech),
	// Nation.get_nation_name_plural(target.nation));
	//
	// Plrhand.notify_player_ex(target, null, E_ENEMY_DIPLOMAT_THEFT,
	// "Game: The %s stole %s from you!",
	// Nation.get_nation_name_plural(pplayer.nation),
	// get_tech_name(pplayer, stolen_tech));
	//
	// notify_embassies(pplayer, target,
	// "Game: The %s have stolen %s from the %s.",
	// Nation.get_nation_name_plural(pplayer.nation),
	// get_tech_name(pplayer, stolen_tech),
	// Nation.get_nation_name_plural(target.nation));
	//
	// do_conquer_cost(pplayer);
	// found_new_tech(pplayer, stolen_tech, false, true, A_NONE);
	// }
	//
	// /**************************************************************************
	// Handle a client or AI request to change the tax/luxury/science rates.
	// This function does full sanity checking.
	// **************************************************************************/
	// void handle_player_rates(player pplayer,
	// int tax, int luxury, int science)
	// {
	// int maxrate;
	//
	// if (Srv_main.server_state != RUN_GAME_STATE) {
	// util.freelog(Log.LOG_ERROR, "received player_rates packet from %s before start",
	// pplayer.name);
	// notify_player(pplayer,
	// "Game: Cannot change rates before Game.game start.");
	// return;
	// }
	//	
	// if (tax + luxury + science != 100) {
	// return;
	// }
	// if (tax < 0 || tax > 100 || luxury < 0 || luxury > 100 || science < 0
	// || science > 100) {
	// return;
	// }
	// maxrate = get_government_max_rate (pplayer.government);
	// if (tax > maxrate || luxury > maxrate || science > maxrate) {
	// final String rtype;
	//
	// if (tax > maxrate) {
	// rtype = "Tax";
	// } else if (luxury > maxrate) {
	// rtype = "Luxury";
	// } else {
	// rtype = "Science";
	// }
	//
	// notify_player(pplayer, "Game: %s rate exceeds the max rate for %s.",
	// rtype, get_government_name(pplayer.government));
	// } else {
	// pplayer.economic.tax = tax;
	// pplayer.economic.luxury = luxury;
	// pplayer.economic.science = science;
	// Gamelog.gamelog(GAMELOG_RATECHANGE, pplayer);
	// conn_list_do_buffer(&pplayer.connections);
	// global_city_refresh(pplayer);
	// Plrhand.send_player_info(pplayer, pplayer);
	// conn_list_do_unbuffer(&pplayer.connections);
	// }
	// }
	//
	// /**************************************************************************
	// Set the player to be researching the given tech.
	//
	// If there are enough accumulated research points, the tech may be
	// acquired immediately.
	// **************************************************************************/
	// void handle_player_research(player pplayer, int tech)
	// {
	// if (tech != A_FUTURE && !tech_exists(tech)) {
	// return;
	// }
	//  
	// if (tech != A_FUTURE && get_invention(pplayer, tech) != TECH_REACHABLE) {
	// return;
	// }
	//
	// /* choose_tech and send update for all players on the team. */
	// for(player aplayer: Game.game.players){
	// if (pplayer == aplayer
	// || (pplayer.diplstates[aplayer.player_no].type == diplstate_type.DS_TEAM
	// && aplayer.is_alive)) {
	// choose_tech(aplayer, tech);
	// Plrhand.send_player_info(aplayer, aplayer);
	// }
	// }
	// }
	//
	// /**************************************************************************
	// ...
	// **************************************************************************/
	// void handle_player_tech_goal(player pplayer, int tech)
	// {
	// if (tech != A_FUTURE && !tech_exists(tech)) {
	// return;
	// }
	//  
	// if (tech != A_FUTURE && !tech_is_available(pplayer, tech)) {
	// return;
	// }
	//
	// if (tech == A_NONE) {
	// /* A_NONE "exists" but is not allowed as a tech goal. A_UNSET should
	// * be used instead. However the above checks may prevent the client from
	// * ever setting the goal to A_UNSET, meaning once a goal is set it
	// * can't be removed. */
	// return;
	// }
	//
	// choose_tech_goal(pplayer, tech);
	// Plrhand.send_player_info(pplayer, pplayer);
	//
	// /* Notify Team members */
	// for(player aplayer: Game.game.players){
	// if (pplayer != aplayer
	// && pplayer.diplstates[aplayer.player_no].type == diplstate_type.DS_TEAM
	// && aplayer.is_alive
	// && aplayer.ai.tech_goal != tech) {
	// handle_player_tech_goal(aplayer, tech);
	// }
	// }
	// }
	//
	// /**************************************************************************
	// Finish the revolution and set the player's government. Call this as soon
	// as the player has set a target_government and the revolution_finishes
	// turn has arrived.
	// **************************************************************************/
	// static void finish_revolution(player pplayer)
	// {
	// int government = pplayer.target_government;
	//
	// if (pplayer.target_government == Game.game.government_when_anarchy) {
	// assert(0!=1);
	// return;
	// }
	// if (pplayer.revolution_finishes > Game.game.turn) {
	// assert(0!=1);
	// return;
	// }
	//
	// pplayer.government = government;
	// /* Leave the target government the same. */
	//
	// util.freelog(Log.LOG_DEBUG,
	// "Revolution finished for %s. Government is %s. Revofin %d (%d).",
	// pplayer.name, get_government_name(government),
	// pplayer.revolution_finishes, Game.game.turn);
	// Plrhand.notify_player_ex(pplayer, null, E_REVOLT_DONE,
	// "Game: %s now governs the %s as a %s.",
	// pplayer.name,
	// Nation.get_nation_name_plural(pplayer.nation),
	// get_government_name(government));
	//
	// Gamelog.gamelog(GAMELOG_GOVERNMENT, pplayer);
	//
	// if (!pplayer.ai.control) {
	// /* Keep luxuries if we have any. Try to max out science. -GJW */
	// int max = get_government_max_rate(pplayer.government);
	//
	// pplayer.economic.science
	// = Math.min(100 - pplayer.economic.luxury, max);
	// pplayer.economic.tax
	// = Math.min(100 - pplayer.economic.luxury - pplayer.economic.science,
	// max);
	// pplayer.economic.luxury
	// = 100 - pplayer.economic.science - pplayer.economic.tax;
	// }
	//
	// check_player_government_rates(pplayer);
	// global_city_refresh(pplayer);
	// Plrhand.send_player_info(pplayer, pplayer);
	// }
	//
	// /**************************************************************************
	// Start a revolution. This can be called even for AI players; it will
	// call finish_revolution immediately if no revolution is needed.
	// **************************************************************************/
	// static void start_revolution(player pplayer)
	// {
	// pplayer.government = Game.game.government_when_anarchy;
	//
	// /* Set revolution_finishes value. */
	// if (pplayer.revolution_finishes > 0) {
	// /* Player already has an active revolution. */
	// } else if ((pplayer.ai.control && !ai_handicap(pplayer, H_REVOLUTION))
	// || get_player_bonus(pplayer, EFT_NO_ANARCHY)) {
	// /* AI players without the H_REVOLUTION handicap can skip anarchy */
	// pplayer.revolution_finishes = Game.game.turn;
	// } else if (Game.game.revolution_length == 0) {
	// pplayer.revolution_finishes = Game.game.turn + Rand.myrand(5) + 1;
	// } else {
	// pplayer.revolution_finishes = Game.game.turn + Game.game.revolution_length;
	// }
	//
	// util.freelog(Log.LOG_DEBUG,
	// "Revolution started for %s. Target government is %s. "
	// "Revofin %d (%d).",
	// pplayer.name, get_government_name(pplayer.target_government),
	// pplayer.revolution_finishes, Game.game.turn);
	// Plrhand.notify_player_ex(pplayer, null, E_REVOLT_START,
	// "Game: The %s have incited a revolt!",
	// Nation.get_nation_name_plural(pplayer.nation));
	// Gamelog.gamelog(GAMELOG_REVOLT, pplayer);
	//
	// /* Now see if the revolution is instantaneous. */
	// if (pplayer.revolution_finishes <= Game.game.turn
	// && pplayer.target_government != Game.game.government_when_anarchy) {
	// finish_revolution(pplayer);
	// return;
	// }
	//
	// check_player_government_rates(pplayer);
	// global_city_refresh(pplayer);
	// Plrhand.send_player_info(pplayer, pplayer);
	// }
	//
	// /**************************************************************************
	// Called by the client or AI to change government.
	// **************************************************************************/
	// void handle_player_change_government(player pplayer, int government)
	// {
	// if (government < 0 || government >= Game.game.government_count
	// || !can_change_to_government(pplayer, government)) {
	// return;
	// }
	//
	// pplayer.target_government = government;
	//
	// util.freelog(Log.LOG_DEBUG,
	// "Government changed for %s. Target government is %s; "
	// "old %s. Revofin %d, Turn %d.",
	// pplayer.name,
	// get_government_name(pplayer.target_government),
	// get_government_name(pplayer.government),
	// pplayer.revolution_finishes, Game.game.turn);
	//
	// if (pplayer.government == Game.game.government_when_anarchy) {
	// /* Already having a revolution. */
	// assert(pplayer.revolution_finishes >= 0);
	// if (pplayer.revolution_finishes <= Game.game.turn
	// && government != Game.game.government_when_anarchy) {
	// /* The revolution was already over. Now we should enter the new
	// * government immediately. */
	// finish_revolution(pplayer);
	// }
	// } else {
	// /* No revolution: start one. */
	// start_revolution(pplayer);
	// }
	//
	// util.freelog(Log.LOG_DEBUG,
	// "Government change complete for %s. Target government is %s; "
	// "now %s. Turn %d; revofin %d.",
	// pplayer.name,
	// get_government_name(pplayer.target_government),
	// get_government_name(pplayer.government),
	// Game.game.turn, pplayer.revolution_finishes);
	// }
	//
	// /**************************************************************************
	// See if the player has finished their revolution. This function should
	// be called at the beginning of a player's phase.
	// **************************************************************************/
	// void update_revolution(player pplayer)
	// {
	// /* The player's revolution counter is stored in the revolution_finishes
	// * field. This value has the following meanings:
	// * - If negative (-1), then the player is not in a revolution. In this
	// * case the player should never be in anarchy.
	// * - If positive, the player is in the middle of a revolution. In this
	// * case the value indicates the turn in which the revolution finishes.
	// * * If this value is > than the current turn, then the revolution is
	// * in progress. In this case the player should always be in anarchy.
	// * * If the value is == to the current turn, then the revolution is
	// * finished. The player may now choose a government. However the
	// * value isn't reset until the end of the turn. If the player has
	// * chosen a government by the end of the turn, then the revolution is
	// * over and the value is reset to -1.
	// * * If the player doesn't pick a government then the revolution
	// * continues. At this point the value is <= to the current turn,
	// * and the player can leave the revolution at any time. The value
	// * is reset at the end of any turn when a non-anarchy government is
	// * chosen.
	// */
	// util.freelog(Log.LOG_DEBUG, "Update revolution for %s. Current government %s, "
	// "target %s, revofin %d, turn %d.",
	// pplayer.name, get_government_name(pplayer.government),
	// get_government_name(pplayer.target_government),
	// pplayer.revolution_finishes, Game.game.turn);
	// if (pplayer.government == Game.game.government_when_anarchy
	// && pplayer.revolution_finishes <= Game.game.turn) {
	// if (pplayer.target_government != Game.game.government_when_anarchy) {
	// /* If the revolution is over and a target government is set, go into
	// * the new government. */
	// util.freelog(Log.LOG_DEBUG, "Update: finishing revolution for %s.",
	// pplayer.name);
	// finish_revolution(pplayer);
	// } else {
	// /* If the revolution is over but there's no target government set,
	// * alert the player. */
	// Plrhand.notify_player_ex(pplayer, null, E_REVOLT_DONE,
	// ("You should choose a new government from the "
	// "government menu."));
	// }
	// } else if (pplayer.government != Game.game.government_when_anarchy
	// && pplayer.revolution_finishes < Game.game.turn) {
	// /* Reset the revolution counter. If the player has another revolution
	// * they'll have to re-enter anarchy. */
	// util.freelog(Log.LOG_DEBUG, "Update: resetting revofin for %s.",
	// pplayer.name);
	// pplayer.revolution_finishes = -1;
	// Plrhand.send_player_info(pplayer, pplayer);
	// }
	// }
	//
	// /**************************************************************************
	// The following checks that government rates are acceptable for the present
	// form of government. Has to be called when switching governments or when
	// toggling from AI to human.
	// **************************************************************************/
	// void check_player_government_rates(player pplayer)
	// {
	// player_economic old_econ = pplayer.economic;
	// boolean changed = false;
	// player_limit_to_government_rates(pplayer);
	// if (pplayer.economic.tax != old_econ.tax) {
	// changed = true;
	// notify_player(pplayer,
	// "Game: Tax rate exceeded the max rate for %s; adjusted.",
	// get_government_name(pplayer.government));
	// }
	// if (pplayer.economic.science != old_econ.science) {
	// changed = true;
	// notify_player(pplayer,
	// "Game: Science rate exceeded the max rate for %s; adjusted.",
	// get_government_name(pplayer.government));
	// }
	// if (pplayer.economic.luxury != old_econ.luxury) {
	// changed = true;
	// notify_player(pplayer,
	// "Game: Luxury rate exceeded the max rate for %s; adjusted.",
	// get_government_name(pplayer.government));
	// }
	//
	// if (changed) {
	// Gamelog.gamelog(GAMELOG_RATECHANGE, pplayer);
	// }
	// }
	//
	// /**************************************************************************
	// After the alliance is breaken, we need to do two things:
	// - Inform clients that they cannot see units inside the former's ally
	// cities
	// - Remove units stacked together
	// **************************************************************************/
	// void update_players_after_alliance_breakup(struct player* pplayer,
	// struct player* pplayer2)
	// {
	// /* The client needs updated diplomatic state, because it is used
	// * during calculation of new states of occupied flags in cities */
	// Plrhand.send_player_info(pplayer, null);
	// Plrhand.send_player_info(pplayer2, null);
	// remove_allied_visibility(pplayer, pplayer2);
	// remove_allied_visibility(pplayer2, pplayer);
	// resolve_unit_stacks(pplayer, pplayer2, true);
	// }
	//
	//
	// /**************************************************************************
	// Handles a player cancelling a "pact" with another player.
	//
	// packet.id is id of player we want to cancel a pact with
	// packet.val1 is a special value indicating what kind of treaty we want
	// to break. If this is CLAUSE_VISION we break shared vision. If it is
	// a pact treaty type, we break one pact level. If it is CLAUSE_LAST
	// we break _all_ treaties and go straight to war.
	// **************************************************************************/
	// void handle_diplomacy_cancel_pact(player pplayer,
	// int other_player_id,
	// enum clause_type clause)
	// {
	// enum diplstate_type old_type;
	// enum diplstate_type new_type;
	// player pplayer2;
	// int reppenalty = 0;
	// boolean has_senate, repeat = false;
	//
	// if (!is_valid_player_id(other_player_id)) {
	// return;
	// }
	//
	// old_type = pplayer.diplstates[other_player_id].type;
	// pplayer2 = get_player(other_player_id);
	// has_senate = government_has_flag(get_gov_pplayer(pplayer), G_HAS_SENATE);
	//
	// /* can't break a pact with yourself */
	// if (pplayer == pplayer2) {
	// return;
	// }
	//
	// /* Can't break up a team, period. */
	// if (players_on_same_team(pplayer, pplayer2)) {
	// return;
	// }
	//
	// if (clause == CLAUSE_VISION) {
	// if (!gives_shared_vision(pplayer, pplayer2)) {
	// return;
	// }
	// remove_shared_vision(pplayer, pplayer2);
	// Plrhand.notify_player_ex(pplayer2, null, E_TREATY_BROKEN,
	// "%s no longer gives us shared vision!",
	// pplayer.name);
	// return;
	// }
	//
	// /* else, breaking a treaty */
	//
	// repeat_break_treaty:
	// /* check what the new status will be, and what will happen to our
	// reputation */
	// switch(old_type) {
	// case diplstate_type.DS_NO_CONTACT: /* possible if someone declares war on
	// our ally */
	// case DS_NEUTRAL:
	// new_type = diplstate_type.DS_WAR;
	// break;
	// case DS_CEASEFIRE:
	// new_type = DS_NEUTRAL;
	// reppenalty += GAME_MAX_REPUTATION/6;
	// break;
	// case DS_PEACE:
	// new_type = DS_NEUTRAL;
	// reppenalty += GAME_MAX_REPUTATION/5;
	// break;
	// case diplstate_type.DS_ALLIANCE:
	// new_type = DS_PEACE;
	// reppenalty += GAME_MAX_REPUTATION/4;
	// break;
	// default:
	// util.freelog(Log.LOG_ERROR, "non-pact diplstate in handle_player_cancel_pact");
	// return;
	// }
	//
	// /* do the change */
	// pplayer.diplstates[pplayer2.player_no].type =
	// pplayer2.diplstates[pplayer.player_no].type =
	// new_type;
	// pplayer.diplstates[pplayer2.player_no].turns_left =
	// pplayer2.diplstates[pplayer.player_no].turns_left =
	// 16;
	//
	// Gamelog.gamelog(GAMELOG_DIPLSTATE, pplayer, pplayer2, new_type);
	//
	// /* If the old state was alliance, the players' units can share tiles
	// illegally, and we need to call resolve_unit_stacks() */
	// if (old_type == diplstate_type.DS_ALLIANCE) {
	// update_players_after_alliance_breakup(pplayer, pplayer2);
	// }
	//
	// /* We want to go all the way to war, whatever the cost!
	// * This is only used by the AI. */
	// if (clause == CLAUSE_LAST && new_type != diplstate_type.DS_WAR) {
	// repeat = true;
	// old_type = new_type;
	// goto repeat_break_treaty;
	// }
	//
	// /* if there's a reason to cancel the pact, do it without penalty */
	// if (pplayer.diplstates[pplayer2.player_no].has_reason_to_cancel > 0) {
	// pplayer.diplstates[pplayer2.player_no].has_reason_to_cancel = 0;
	// if (has_senate && !repeat) {
	// Plrhand.notify_player_ex(pplayer, null, E_TREATY_BROKEN,
	// ("The senate passes your bill because of the "
	// "finalant provocations of the %s."),
	// Nation.get_nation_name_plural(pplayer2.nation));
	// }
	// }
	// /* no reason to cancel, apply penalty (and maybe suffer a revolution) */
	// /* FIXME: according to civII rules, republics and democracies
	// have different chances of revolution; maybe we need to
	// extend the govt rulesets to mimic this -- pt */
	// else {
	// pplayer.reputation = MAX(pplayer.reputation - reppenalty, 0);
	// Plrhand.notify_player_ex(pplayer, null, E_TREATY_BROKEN,
	// "Game: Your reputation is now %s.",
	// reputation_text(pplayer.reputation));
	// if (has_senate && pplayer.revolution_finishes < 0) {
	// if (Rand.myrand(GAME_MAX_REPUTATION) > pplayer.reputation) {
	// Plrhand.notify_player_ex(pplayer, null, E_ANARCHY,
	// ("Game: The senate decides to dissolve "
	// "rather than support your actions any longer."));
	// handle_player_change_government(pplayer, pplayer.government);
	// }
	// }
	// }
	//
	// Plrhand.send_player_info(pplayer, null);
	// Plrhand.send_player_info(pplayer2, null);
	//
	//
	// if (old_type == diplstate_type.DS_ALLIANCE) {
	// /* Inform clients about units that have been hidden. Units in cities
	// * and transporters are visible to allies but not visible once the
	// * alliance is broken. We have to call this after resolve_unit_stacks
	// * because that function may change units' locations. It also sends
	// * out new city info packets to tell the client about occupied cities,
	// * so it should also come after the send_player_info calls above. */
	// remove_allied_visibility(pplayer, pplayer2);
	// remove_allied_visibility(pplayer2, pplayer);
	// }
	//
	//
	// /*
	// * Refresh all cities which have a unit of the other side within
	// * city range.
	// */
	// check_city_workers(pplayer);
	// check_city_workers(pplayer2);
	//
	// Plrhand.notify_player_ex(pplayer, null, E_TREATY_BROKEN,
	// ("Game: The diplomatic state between the %s "
	// "and the %s is now %s."),
	// Nation.get_nation_name_plural(pplayer.nation),
	// Nation.get_nation_name_plural(pplayer2.nation),
	// diplstate_text(new_type));
	// Plrhand.notify_player_ex(pplayer2, null, E_TREATY_BROKEN,
	// ("Game: %s cancelled the diplomatic agreement! "
	// "The diplomatic state between the %s and the %s "
	// "is now %s."), pplayer.name,
	// Nation.get_nation_name_plural(pplayer2.nation),
	// Nation.get_nation_name_plural(pplayer.nation),
	// diplstate_text(new_type));
	//
	// /* Check fall-out of a war declaration. */
	// for(player other: Game.game.players){
	// if (other.is_alive && other != pplayer && other != pplayer2
	// && new_type == diplstate_type.DS_WAR && pplayers_allied(pplayer2, other)
	// && pplayers_allied(pplayer, other)) {
	// if (!players_on_same_team(pplayer, other)) {
	// /* If an ally declares war on another ally, break off your alliance
	// * to the aggressor. This prevents in-alliance wars, which are not
	// * permitted. */
	// Plrhand.notify_player_ex(other, null, E_TREATY_BROKEN,
	// ("Game: %s has attacked your ally %s! "
	// "You cancel your alliance to the aggressor."),
	// pplayer.name, pplayer2.name);
	// other.diplstates[pplayer.player_no].has_reason_to_cancel = 1;
	// handle_diplomacy_cancel_pact(pplayer, other.player_no,
	// CLAUSE_ALLIANCE);
	// } else {
	// /* We are in the same team as the agressor; we cannot break
	// * alliance with him. We trust our team mate and break alliance
	// * with the attacked player */
	// Plrhand.notify_player_ex(other, null, E_TREATY_BROKEN,
	// ("Game: Your team mate %s declared war on %s. "
	// "You are obligated to cancel alliance with %s."),
	// pplayer.name,
	// Nation.get_nation_name_plural(pplayer2.nation),
	// pplayer2.name);
	// handle_diplomacy_cancel_pact(other, pplayer2.player_no, CLAUSE_ALLIANCE);
	// }
	// }
	// }
	// }

	/***************************************************************************
	 * This is the basis for following notify_conn* and notify_player*
	 * functions. Notify specified connections of an event of specified type
	 * (from events.h) and specified (x,y) coords associated with the event.
	 * Coords will only apply if Game.game has started and the conn's player knows
	 * that tile (or pconn.player==null && pconn.observer). If coords are not
	 * required, caller should specify (x,y) = (-1,-1); otherwise make sure that
	 * the coordinates have been normalized. For generic event use
	 * event_type.E_NOEVENT. (But current clients do not use (x,y) data for
	 * event_type.E_NOEVENT events.)
	 **************************************************************************/
	public static void vnotify_conn_ex(Speclists<Connection> dest, tile ptile,
			event_type event, final String format, Object... vargs) {
		// struct packet_chat_msg genmsg;
		//  
		// my_vsnprintf(genmsg.message, sizeof(genmsg.message), format, vargs);
		// genmsg.event = event;
		// genmsg.conn_id = -1;
		//
		// conn_list_iterate(*dest, pconn) {
		// if (Srv_main.server_state >= RUN_GAME_STATE
		// && ptile /* special case, see above */
		// && ((!pconn.player && pconn.observer)
		// || (pconn.player && Maphand.map_is_known(ptile, pconn.player)))) {
		// genmsg.x = ptile.x;
		// genmsg.y = ptile.y;
		// } else {
		// assert(Srv_main.server_state < RUN_GAME_STATE || !is_normal_map_pos(-1, -1));
		// genmsg.x = -1;
		// genmsg.y = -1;
		// }
		// send_packet_chat_msg(pconn, &genmsg);
		// }
		// }
	}

	/***************************************************************************
	 * See vnotify_conn_ex - this is just the "non-v" version, with varargs.
	 **************************************************************************/
	public static void notify_conn_ex(Speclists<Connection> dest, tile ptile,
			event_type event, final String format) {
		// va_list args;
		// va_start(args, format);
		// vnotify_conn_ex(dest, ptile, event, format, args);
		vnotify_conn_ex(dest, ptile, event, format, format);
		// va_end(args);
	}

	 /**************************************************************************
	 See vnotify_conn_ex - this is varargs, and cannot specify (x,y), event.
	 **************************************************************************/
	 public static void notify_conn(Speclists<Connection> dest, final String format, Object ...args)
	 {
//	 va_list args;
	
	 if (null==dest) {
	 dest = Game.game.est_connections;
	 }
//	 va_start(args, format);
	 vnotify_conn_ex(dest, null, event_type.E_NOEVENT, format, args);
//	 va_end(args);
	 }

	/***************************************************************************
	 * Similar to vnotify_conn_ex (see also), but takes player as "destination".
	 * If player != null, sends to all connections for that player. If player ==
	 * null, sends to all Game.game connections, to support old code, but this
	 * feature may go away - should use notify_conn with explicitly
	 * Game.game.est_connections or Game.game.game_connections as dest.
	 **************************************************************************/
	public static void notify_player_ex(final player pplayer, tile ptile,
			event_type event, final String format, Object... msg) {
		Speclists<Connection> dest;
		// va_list args;

		if (pplayer != null) {
			dest = pplayer.connections;
		} else {
			dest = Game.game.game_connections;
		}

		// va_start(args, format);
		vnotify_conn_ex(dest, ptile, event, format, msg);
		// va_end(args);
	}

	/***************************************************************************
	 * Just like Plrhand.notify_player_ex, but no (x,y) nor event type.
	 **************************************************************************/
	public static void notify_player(final player pplayer, final String format,
			Object... args) {
		Speclists<Connection> dest;
		// va_list args;

		if (pplayer != null) {
			dest = (Speclists<Connection>) pplayer.connections;
		} else {
			dest = Game.game.game_connections;
		}

		// va_start(args, format);
		vnotify_conn_ex(dest, null, event_type.E_NOEVENT, format, args);
		// va_end(args);
	}

	// /**************************************************************************
	// Send message to all players who have an embassy with pplayer,
	// but excluding pplayer and specified player.
	// **************************************************************************/
	// void notify_embassies(player pplayer, player exclude,
	// final String format)
	// {
	// struct packet_chat_msg genmsg;
	// va_list args;
	//
	// va_start(args, format);
	// my_vsnprintf(genmsg.message, sizeof(genmsg.message), format, args);
	// va_end(args);
	//
	// genmsg.x = -1;
	// genmsg.y = -1;
	// genmsg.event = event_type.E_NOEVENT;
	// genmsg.conn_id = -1;
	//
	// for(player other_player: Game.game.players){
	// if (player_has_embassy(other_player, pplayer)
	// && exclude != other_player
	// && pplayer != other_player) {
	// lsend_packet_chat_msg(&other_player.connections, &genmsg);
	// }
	// }
	// }
	//
	// /**************************************************************************
	// Send information about player src, or all players if src is null,
	// to specified clients dest (dest may not be null).
	//
	// Note: package_player_info contains incomplete info if it has null as a
	// dest arg and and info is < INFO_EMBASSY.
	// **************************************************************************/
	// void send_player_info_c(player src, Speclists<Connection> dest)
	// {
	// for(player pplayer: Game.game.players){
	// if(!src || pplayer==src) {
	// struct packet_player_info info;
	//
	// package_player_common(pplayer, &info);
	//
	// conn_list_iterate(*dest, pconn) {
	// if (pconn.player && pconn.player.is_observer) {
	// /* Global observer. */
	// package_player_info(pplayer, &info, pconn.player, INFO_FULL);
	// } else if (pconn.player) {
	// /* Players (including regular observers) */
	// package_player_info(pplayer, &info, pconn.player, INFO_MINIMUM);
	// } else {
	// package_player_info(pplayer, &info, null, INFO_MINIMUM);
	// }
	//
	// send_packet_player_info(pconn, &info);
	// } }
	// }
	// }
	// }
	
	 /**************************************************************************
	 Convenience form of send_player_info_c.
	 Send information about player src, or all players if src is null,
	 to specified players dest (that is, to dest.connections).
	 As convenience to old code, dest may be null meaning send to
	 Game.game.game_connections.
	 **************************************************************************/
	 public static void send_player_info(player src, player dest)
	 {
	// send_player_info_c(src, (dest ? &dest.connections :
	// &Game.game.game_connections));
	 }
	//
	// /**************************************************************************
	// Package player information that is always sent.
	// **************************************************************************/
	// static void package_player_common(player plr,
	// packet_player_info packet)
	// {
	// int i;
	//
	// packet.playerno=plr.player_no;
	// packet.name = plr.name;
	// packet.username = plr.username;
	// packet.nation=plr.nation;
	// packet.is_male=plr.is_male;
	// packet.team = plr.team;
	// packet.city_style=plr.city_style;
	//
	// packet.is_alive=plr.is_alive;
	// packet.is_connected=plr.is_connected;
	// packet.ai=plr.ai.control;
	// for (i = 0; i < Shared_H.MAX_NUM_PLAYERS + Shared_H.MAX_NUM_BARBARIANS; i++) {
	// packet.love[i] = plr.ai.love[i];
	// }
	// packet.barbarian_type = plr.ai.barbarian_type;
	// packet.reputation=plr.reputation;
	//
	// packet.turn_done=plr.turn_done;
	// packet.nturns_idle=plr.nturns_idle;
	// }
	//
	// /**************************************************************************
	// Package player info depending on info_level. We send everything to
	// plr's connections, we send almost everything to players with embassy
	// to plr, we send a little to players we are in contact with and almost
	// nothing to everyone else.
	//
	// Receiver may be null in which cases dummy values are sent for some
	// fields.
	// **************************************************************************/
	// static void package_player_info(player plr,
	// packet_player_info packet,
	// player receiver,
	// enum plr_info_level min_info_level)
	// {
	// int i;
	// enum plr_info_level info_level;
	//
	// if (receiver) {
	// info_level = player_info_level(plr, receiver);
	// if (info_level < min_info_level) {
	// info_level = min_info_level;
	// }
	// } else {
	// info_level = min_info_level;
	// }
	//
	// packet.gold = plr.economic.gold;
	// packet.government = plr.government;
	//
	// /* Send diplomatic status of the player to everyone they are in
	// * contact with. */
	// if (info_level >= INFO_EMBASSY
	// || (receiver
	// && receiver.diplstates[plr.player_no].contact_turns_left > 0)) {
	// packet.target_government = plr.target_government;
	// packet.embassy = plr.embassy;
	// packet.gives_shared_vision = plr.gives_shared_vision;
	// for(i = 0; i < Shared_H.MAX_NUM_PLAYERS + Shared_H.MAX_NUM_BARBARIANS; i++) {
	// packet.diplstates[i].type = plr.diplstates[i].type;
	// packet.diplstates[i].turns_left = plr.diplstates[i].turns_left;
	// packet.diplstates[i].contact_turns_left =
	// plr.diplstates[i].contact_turns_left;
	// packet.diplstates[i].has_reason_to_cancel =
	// plr.diplstates[i].has_reason_to_cancel;
	// }
	// } else {
	// packet.target_government = packet.government;
	// if (!receiver || !player_has_embassy(plr, receiver)) {
	// packet.embassy = 0;
	// } else {
	// packet.embassy = 1 << receiver.player_no;
	// }
	// if (!receiver || !gives_shared_vision(plr, receiver)) {
	// packet.gives_shared_vision = 0;
	// } else {
	// packet.gives_shared_vision = 1 << receiver.player_no;
	// }
	//
	// for (i = 0; i < Shared_H.MAX_NUM_PLAYERS + Shared_H.MAX_NUM_BARBARIANS; i++) {
	// packet.diplstates[i].type = DS_NEUTRAL;
	// packet.diplstates[i].turns_left = 0;
	// packet.diplstates[i].has_reason_to_cancel = 0;
	// packet.diplstates[i].contact_turns_left = 0;
	// }
	// /* We always know the player's relation to us */
	// if (receiver) {
	// int p_no = receiver.player_no;
	//
	// packet.diplstates[p_no].type = plr.diplstates[p_no].type;
	// packet.diplstates[p_no].turns_left = plr.diplstates[p_no].turns_left;
	// packet.diplstates[p_no].contact_turns_left =
	// plr.diplstates[p_no].contact_turns_left;
	// packet.diplstates[p_no].has_reason_to_cancel =
	// plr.diplstates[p_no].has_reason_to_cancel;
	// }
	// }
	//
	// /* Make absolutely sure - in case you lose your embassy! */
	// if (info_level >= INFO_EMBASSY
	// || (receiver
	// && pplayer_get_diplstate(plr, receiver).type == diplstate_type.DS_TEAM))
	// {
	// packet.bulbs_last_turn = plr.research.bulbs_last_turn;
	// } else {
	// packet.bulbs_last_turn = 0;
	// }
	//
	// /* Send most civ info about the player only to players who have an
	// * embassy. */
	// if (info_level >= INFO_EMBASSY) {
	// for (i = A_FIRST; i < Game.game.num_tech_types; i++) {
	// packet.inventions[i] = plr.research.inventions[i].state + '0';
	// }
	// packet.inventions[i] = '\0';
	// packet.tax = plr.economic.tax;
	// packet.science = plr.economic.science;
	// packet.luxury = plr.economic.luxury;
	// packet.bulbs_researched= plr.research.bulbs_researched;
	// packet.techs_researched= plr.research.techs_researched;
	// packet.researching = plr.research.researching;
	// packet.future_tech = plr.future_tech;
	// packet.revolution_finishes = plr.revolution_finishes;
	// } else {
	// for (i = A_FIRST; i < Game.game.num_tech_types; i++) {
	// packet.inventions[i] = '0';
	// }
	// packet.inventions[i] = '\0';
	// packet.tax = 0;
	// packet.science = 0;
	// packet.luxury = 0;
	// packet.bulbs_researched= 0;
	// packet.techs_researched= 0;
	// packet.researching = A_NOINFO;
	// packet.future_tech = 0;
	// packet.revolution_finishes = -1;
	// }
	//
	// /* We have to inform the client that the other players also know
	// * A_NONE. */
	// packet.inventions[A_NONE] = plr.research.inventions[A_NONE].state + '0';
	//
	// if (info_level >= INFO_FULL
	// || (receiver
	// && plr.diplstates[receiver.player_no].type == diplstate_type.DS_TEAM)) {
	// packet.tech_goal = plr.ai.tech_goal;
	// } else {
	// packet.tech_goal = A_UNSET;
	// }
	//
	// /*
	// * This may be an odd time to check these values but we can be sure
	// * to have a consistent state here.
	// */
	// assert(Srv_main.server_state != RUN_GAME_STATE
	// || ((tech_exists(plr.research.researching)
	// && plr.research.researching != A_NONE)
	// || is_future_tech(plr.research.researching)
	// || plr.research.researching == A_UNSET));
	// assert((tech_exists(plr.ai.tech_goal) && plr.ai.tech_goal != A_NONE)
	// || plr.ai.tech_goal == A_UNSET);
	// }
	//
	// /**************************************************************************
	// ...
	// **************************************************************************/
	// static enum plr_info_level player_info_level(player plr,
	// player receiver)
	// {
	// if (plr == receiver) {
	// return INFO_FULL;
	// }
	// if (receiver && player_has_embassy(receiver, plr)) {
	// return INFO_EMBASSY;
	// }
	// if (receiver && could_intel_with_player(receiver, plr)) {
	// return INFO_MEETING;
	// }
	// return INFO_MINIMUM;
	// }
	//
	// /**************************************************************************
	// Convenience function to return "reply" destination connection list
	// for player: pplayer.current_conn if set, else pplayer.connections.
	// **************************************************************************/
	// Speclists<Connection> player_reply_dest(player pplayer)
	// {
	// return (pplayer.current_conn ?
	// &pplayer.current_conn.self :
	// &pplayer.connections);
	// }
	//

	/********************************************************************** 
	The initmap option is used because we don't want to initialize the map
	before the x and y sizes have been determined
	***********************************************************************/
	public static void server_player_init(player pplayer, boolean initmap)
	{
		//		if (initmap) {
		//			player_map_allocate(pplayer);
		//		}
		//		pplayer.player_no = pplayer-Game.game.players;
		//		pplayer.team = TEAM_NONE;
		//		ai_data_init(pplayer);
	}


	// This function does _not_ close any connections attached to this player.
	// cut_connection is used for that.
	// ***********************************************************************/
	// void server_remove_player(player pplayer)
	// {
	// /* Not allowed after a Game.game has started */
	// if (!(Game.game.is_new_game && (Srv_main.server_state==server_states.PRE_GAME_STATE ||
	// Srv_main.server_state==SELECT_RACES_STATE))) {
	// util.die("You can't remove players after the Game.game has started!");
	// }
	//
	// util.freelog(Log.LOG_NORMAL, "Removing player %s.", pplayer.name);
	// notify_player(pplayer, "Game: You've been removed from the Game.game!");
	//
	// notify_conn(&Game.game.est_connections,
	// "Game: %s has been removed from the Game.game.", pplayer.name);
	//  
	// dlsend_packet_player_remove(&Game.game.game_connections, pplayer.player_no);
	//
	// /* Note it is ok to remove the _current_ item in a list_iterate. */
	// for (conn pconn : pplayer.connections.data) {
	// if (!unattach_connection_from_player(pconn)) {
	// util.die("player had a connection attached that didn't belong to it!");
	// }
	// } }
	//
	// game_remove_player(pplayer);
	// game_renumber_players(pplayer.player_no);
	// }
	//
	// /**************************************************************************
	// Update contact info.
	// **************************************************************************/
	// void make_contact(player pplayer1, player pplayer2,
	// tile ptile)
	// {
	// int player1 = pplayer1.player_no, player2 = pplayer2.player_no;
	//
	// if (pplayer1 == pplayer2
	// || !pplayer1.is_alive || !pplayer2.is_alive
	// || is_barbarian(pplayer1) || is_barbarian(pplayer2)) {
	// return;
	// }
	//
	// pplayer1.diplstates[player2].contact_turns_left = Game.game.contactturns;
	// pplayer2.diplstates[player1].contact_turns_left = Game.game.contactturns;
	//
	// if (pplayer_get_diplstate(pplayer1, pplayer2).type ==
	// diplstate_type.DS_NO_CONTACT) {
	// /* Set default new diplomatic state depending on Game.game.diplomacy
	// * server setting. Default is zero, which gives DS_NEUTRAL. */
	// enum diplstate_type dipstate = diplomacy_possible(pplayer1,pplayer2)
	// ? DS_NEUTRAL : diplstate_type.DS_WAR;
	//
	// pplayer1.diplstates[player2].type
	// = pplayer2.diplstates[player1].type
	// = dipstate;
	// Plrhand.notify_player_ex(pplayer1, ptile,
	// E_FIRST_CONTACT,
	// "Game: You have made contact with the %s, ruled by %s.",
	// Nation.get_nation_name_plural(pplayer2.nation), pplayer2.name);
	// Plrhand.notify_player_ex(pplayer2, ptile,
	// E_FIRST_CONTACT,
	// "Game: You have made contact with the %s, ruled by %s.",
	// Nation.get_nation_name_plural(pplayer1.nation), pplayer1.name);
	// Plrhand.send_player_info(pplayer1, pplayer2);
	// Plrhand.send_player_info(pplayer2, pplayer1);
	// Plrhand.send_player_info(pplayer1, pplayer1);
	// Plrhand.send_player_info(pplayer2, pplayer2);
	//
	// check_city_workers(pplayer1);
	// check_city_workers(pplayer2);
	// return;
	// } else {
	// assert(pplayer_get_diplstate(pplayer2, pplayer1).type !=
	// diplstate_type.DS_NO_CONTACT);
	// }
	// if (player_has_embassy(pplayer1, pplayer2)
	// || player_has_embassy(pplayer2, pplayer1)) {
	// return; /* Avoid sending too much info over the network */
	// }
	// Plrhand.send_player_info(pplayer1, pplayer1);
	// Plrhand.send_player_info(pplayer2, pplayer2);
	// }
	//
	// /**************************************************************************
	// Check if we make contact with anyone.
	// **************************************************************************/
	// void maybe_make_contact(tile ptile, player pplayer)
	// {
	// for(tile tile1: util.square_tile_iterate(ptile, 1)) {
	// city pcity = tile1.city;
	// if (pcity) {
	// make_contact(pplayer, City.city_owner(pcity), ptile);
	// }
	// for (unit punit : tile1.units.data) {
	// make_contact(pplayer, punit.unit_owner(), ptile);
	// } }
	// }
	// }
	//
	// /**************************************************************************
	// To be used only by shuffle_players() and shuffled_player() below:
	// **************************************************************************/
	// static player shuffled_plr[Shared_H.MAX_NUM_PLAYERS + Shared_H.MAX_NUM_BARBARIANS];
	// static int shuffled_nplayers = 0;
	//
	// /**************************************************************************
	// Shuffle or reshuffle the player order, storing in static variables above.
	// **************************************************************************/
	// void shuffle_players()
	// {
	// int i, pos;
	// player tmp_plr;
	//
	// util.freelog(Log.LOG_DEBUG, "shuffling %d players", Game.game.nplayers);
	//
	// /* Initialize array in unshuffled order: */
	// for(i=0; i<Game.game.nplayers; i++) {
	// shuffled_plr[i] = &Game.game.players[i];
	// }
	//
	// /* Now shuffle them: */
	// for(i=0; i<Game.game.nplayers-1; i++) {
	// /* for each run: shuffled[ <i ] is already shuffled [Kero+dwp] */
	// pos = i + Rand.myrand(Game.game.nplayers-i);
	// tmp_plr = shuffled_plr[i];
	// shuffled_plr[i] = shuffled_plr[pos];
	// shuffled_plr[pos] = tmp_plr;
	// }
	//
	// #ifdef DEBUG
	// for (i = 0; i < Game.game.nplayers; i++) {
	// util.freelog(Log.LOG_DEBUG, "Shuffling player %d as %d.",
	// i, shuffled_plr[i].player_no);
	// }
	// #endif
	//
	// /* Record how many players there were when shuffled: */
	// shuffled_nplayers = Game.game.nplayers;
	// }
	//
	// /**************************************************************************
	// Initialize the shuffled players list (as from a loaded savegame).
	// **************************************************************************/
	// void set_shuffled_players(int *shuffled_players)
	// {
	// int i;
	//
	// for (i = 0; i < Game.game.nplayers; i++) {
	// shuffled_plr[i] = get_player(shuffled_players[i]);
	//
	// util.freelog(Log.LOG_DEBUG, "Set shuffled player %d as %d.",
	// i, shuffled_plr[i].player_no);
	// }
	//
	// shuffled_nplayers = Game.game.nplayers;
	// }
	//
	// /**************************************************************************
	// Return i'th shuffled player. If number of players has grown between
	// re-shuffles, added players are given in unshuffled order at the end.
	// Number of players should not have shrunk.
	// **************************************************************************/
	// player shuffled_player(int i)
	// {
	// assert(i>=0 && i<Game.game.nplayers);
	//  
	// if (shuffled_nplayers == 0) {
	// util.freelog(Log.LOG_ERROR, "shuffled_player() called before shuffled");
	// return &Game.game.players[i];
	// }
	// /* This shouldn't happen: */
	// if (Game.game.nplayers < shuffled_nplayers) {
	// util.freelog(Log.LOG_ERROR, "number of players shrunk between shuffles (%d < %d)",
	// Game.game.nplayers, shuffled_nplayers);
	// return &Game.game.players[i]; /* ?? */
	// }
	// if (i < shuffled_nplayers) {
	// return shuffled_plr[i];
	// } else {
	// return &Game.game.players[i];
	// }
	// }
	//
	// /****************************************************************************
	// This function return one of the nations available from the
	// NO_NATION_SELECTED-terminated choices list. If no available nations in
	// this
	// file were found, return a random nation. If no nations are available,
	// util.die.
	// ****************************************************************************/
	// static int pick_available_nation(int *choices)
	// {
	// int *nations_used, i, num_nations_avail = Game.game.playable_nation_count,
	// pick;
	// int looking_for, pref_nations_avail = 0;
	//
	// /* Values of nations_used:
	// * 0: not available
	// * 1: available
	// * 2: preferred choice */
	// nations_used = fc_calloc(Game.game.playable_nation_count, sizeof(int));
	//
	// for (i = 0; i < Game.game.playable_nation_count; i++) {
	// nations_used[i] = 1; /* Available (for now) */
	// }
	//
	// for (i = 0; choices[i] != NO_NATION_SELECTED; i++) {
	// pref_nations_avail++;
	// nations_used[choices[i]] = 2; /* Preferred */
	// }
	//
	// for(player other_player: Game.game.players){
	// if (other_player.nation < Game.game.playable_nation_count) {
	// if (nations_used[other_player.nation] == 2) {
	// pref_nations_avail--;
	// }
	// nations_used[other_player.nation] = 0; /* Unavailable */
	// num_nations_avail--;
	// }
	// }
	//
	// assert(num_nations_avail > 0);
	// assert(pref_nations_avail >= 0);
	//
	// if (pref_nations_avail == 0) {
	// pick = Rand.myrand(num_nations_avail);
	// looking_for = 1; /* Use any available nation. */
	// } else {
	// pick = Rand.myrand(pref_nations_avail);
	// looking_for = 2; /* Use a preferred nation only. */
	// }
	//
	// for (i = 0; i < Game.game.playable_nation_count; i++){
	// if (nations_used[i] == looking_for) {
	// pick--;
	//      
	// if (pick < 0) {
	// break;
	// }
	// }
	// }
	//
	// free(nations_used);
	// return i;
	// }
	//
	// /**********************************************************************
	// This function creates a new player and copies all of it's science
	// research etc. Players are both thrown into anarchy and gold is
	// split between both players.
	// - Kris Bubendorfer
	// ***********************************************************************/
	// static player split_player(player pplayer)
	// {
	// int newplayer = Game.game.nplayers;
	// player cplayer = &Game.game.players[newplayer];
	// int *civilwar_nations = get_nation_civilwar(pplayer.nation);
	//
	// /* make a new player */
	// server_player_init(cplayer, true);
	//
	// /* select a new name and nation for the copied player. */
	// /* Rebel will always be an AI player */
	// cplayer.nation = pick_available_nation(civilwar_nations);
	// Srv_main.pick_ai_player_name(cplayer.nation, cplayer.name);
	//
	// cplayer.username = Player_H.ANON_USER_NAME;
	// cplayer.is_connected = false;
	// cplayer.government = Game.game.default_government;
	// cplayer.target_government = Game.game.default_government;
	// assert(cplayer.revolution_finishes < 0);
	// cplayer.capital = true;
	//
	// /* cplayer is not yet part of players_iterate which goes only
	// to Game.game.nplayers. */
	// for(player other_player: Game.game.players){
	// /* Barbarians are at war with everybody */
	// if (is_barbarian(other_player)) {
	// cplayer.diplstates[other_player.player_no].type = diplstate_type.DS_WAR;
	// other_player.diplstates[cplayer.player_no].type = diplstate_type.DS_WAR;
	// } else {
	// cplayer.diplstates[other_player.player_no].type =
	// diplstate_type.DS_NO_CONTACT;
	// other_player.diplstates[cplayer.player_no].type =
	// diplstate_type.DS_NO_CONTACT;
	// }
	//
	// cplayer.diplstates[other_player.player_no].has_reason_to_cancel = 0;
	// cplayer.diplstates[other_player.player_no].turns_left = 0;
	// cplayer.diplstates[other_player.player_no].contact_turns_left = 0;
	// other_player.diplstates[cplayer.player_no].has_reason_to_cancel = 0;
	// other_player.diplstates[cplayer.player_no].turns_left = 0;
	// other_player.diplstates[cplayer.player_no].contact_turns_left = 0;
	//    
	// /* Send so that other_player sees updated diplomatic info;
	// * pplayer will be sent later anyway
	// */
	// if (other_player != pplayer) {
	// Plrhand.send_player_info(other_player, other_player);
	// }
	// }
	// players_iterate_end;
	//
	// Game.game.nplayers++;
	// Game.game.max_players = Game.game.nplayers;
	//
	// /* Split the resources */
	//  
	// cplayer.economic.gold = pplayer.economic.gold;
	// cplayer.economic.gold /= 2;
	// pplayer.economic.gold -= cplayer.economic.gold;
	//
	// /* Copy the research */
	//
	// cplayer.research.bulbs_researched = 0;
	// cplayer.research.techs_researched = pplayer.research.techs_researched;
	// cplayer.research.researching = pplayer.research.researching;
	//
	// tech_type_iterate(i) {
	// cplayer.research.inventions[i] = pplayer.research.inventions[i];
	// } tech_type_iterate_end;
	// cplayer.turn_done = true; /* Have other things to think about -
	// paralysis*/
	// cplayer.embassy = 0; /* all embassies destroyed */
	//
	// /* Do the ai */
	//
	// cplayer.ai.control = true;
	// cplayer.ai.tech_goal = pplayer.ai.tech_goal;
	// cplayer.ai.prev_gold = pplayer.ai.prev_gold;
	// cplayer.ai.maxbuycost = pplayer.ai.maxbuycost;
	// cplayer.ai.handicap = pplayer.ai.handicap;
	// cplayer.ai.warmth = pplayer.ai.warmth;
	// set_ai_level_direct(cplayer, Game.game.skill_level);
	//
	// tech_type_iterate(i) {
	// cplayer.ai.tech_want[i] = pplayer.ai.tech_want[i];
	// } tech_type_iterate_end;
	//  
	// /* change the original player */
	// if (pplayer.government != Game.game.government_when_anarchy) {
	// pplayer.government = Game.game.government_when_anarchy;
	// pplayer.revolution_finishes = Game.game.turn + 1;
	// }
	// pplayer.research.bulbs_researched = 0;
	// pplayer.embassy = 0; /* all embassies destroyed */
	//
	// /* give splitted player the embassies to his team mates back, if any */
	// if (pplayer.team != TEAM_NONE) {
	// for(player pdest: Game.game.players){
	// if (pplayer.team == pdest.team
	// && pplayer != pdest) {
	// establish_embassy(pplayer, pdest);
	// }
	// }
	// }
	//
	// player_limit_to_government_rates(pplayer);
	//
	// /* copy the maps */
	//
	// give_map_from_player_to_player(pplayer, cplayer);
	//
	// /* Not sure if this is necessary, but might be a good idea
	// to avoid doing some ai calculations with bogus data:
	// */
	// ai_data_turn_init(cplayer);
	// assess_danger_player(cplayer);
	// if (pplayer.ai.control) {
	// assess_danger_player(pplayer);
	// }
	//
	// Gamelog.gamelog(EGamelog.GAMELOG_PLAYER, cplayer);
	//  
	// return cplayer;
	// }
	//

	// civil_war_triggered:
	// * The capture of a capital is not a sure fire way to throw
	// and empire into civil war. Some governments are more susceptible
	// than others, here are the base probabilities:
	// Anarchy 90%
	// Despotism 80%
	// Monarchy 70%
	// Fundamentalism 60% (Only in civ2 ruleset)
	// Communism 50%
	// Republic 40%
	// Democracy 30%
	// * In addition each city in revolt adds 5%, each city in rapture
	// subtracts 5% from the probability of a civil war.
	// * If you have at least 1 turns notice of the impending loss of
	// your capital, you can hike luxuries up to the hightest value,
	// and by this reduce the chance of a civil war. In fact by
	// hiking the luxuries to 100% under Democracy, it is easy to
	// get massively negative numbers - guaranteeing imunity from
	// civil war. Likewise, 3 revolting cities under despotism
	// guarantees a civil war.
	// * This routine calculates these probabilities and returns true
	// if a civil war is triggered.
	// - Kris Bubendorfer
	// ***********************************************************************/
	// boolean civil_war_triggered(player pplayer)
	// {
	// /* Get base probabilities */
	//
	// int dice = Rand.myrand(100); /* Throw the dice */
	// int prob = get_government_civil_war_prob(pplayer.government);
	//
	// /* Now compute the contribution of the cities. */
	//  
	// city_list_iterate(pplayer.cities, pcity)
	// if (city_unhappy(pcity)) {
	// prob += 5;
	// }
	// if (city_celebrating(pcity)) {
	// prob -= 5;
	// }
	// }
	//
	// util.freelog(Log.LOG_VERBOSE, "Civil war chance for %s: prob %d, dice %d",
	// pplayer.name, prob, dice);
	//  
	// return(dice < prob);
	// }
	//
	// /**********************************************************************
	// Capturing a nation's capital is a devastating blow. This function
	// creates a new AI player, and randomly splits the original players
	// city list into two. Of course this results in a real mix up of
	// teritory - but since when have civil wars ever been tidy, or civil.
	//
	// Embassies: All embassies with other players are lost. Other players
	// retain their embassies with pplayer.
	// * Units: Units inside cities are assigned to the new owner
	// of the city. Units outside are transferred along
	// with the ownership of their supporting city.
	// If the units are in a unit stack with non rebel units,
	// then whichever units are nearest an allied city
	// are teleported to that city. If the stack is a
	// transport at sea, then all rebel units on the
	// transport are teleported to their nearest allied city.
	//
	// Cities: Are split randomly into 2. This results in a real
	// mix up of teritory - but since when have civil wars
	// ever been tidy, or for any matter civil?
	// *
	// One caveat, since the spliting of cities is random, you can
	// conceive that this could result in either the original player
	// or the rebel getting 0 cities. To prevent this, the hack below
	// ensures that each side gets roughly half, which ones is still
	// determined randomly.
	// - Kris Bubendorfer
	// ***********************************************************************/
	// void civil_war(player pplayer)
	// {
	// int i, j;
	// player cplayer;
	//
	// if (Game.game.nplayers >= Shared_H.MAX_NUM_PLAYERS) {
	// /* No space to make additional player */
	// util.freelog(Log.LOG_NORMAL, ("Could not throw %s into civil war - too many "
	// "players"), pplayer.name);
	// return;
	// }
	//
	// cplayer = split_player(pplayer);
	//
	// /* So that clients get the correct Game.game.nplayers: */
	// Gamehand.send_game_info(null);
	//  
	// /* Before units, cities, so clients know name of new nation
	// * (for debugging etc).
	// */
	// Plrhand.send_player_info(cplayer, null);
	// Plrhand.send_player_info(pplayer, null);
	//  
	// /* Now split the empire */
	//
	// util.freelog(Log.LOG_VERBOSE,
	// "%s's nation is thrust into civil war, created AI player %s",
	// pplayer.name, cplayer.name);
	// Plrhand.notify_player_ex(pplayer, null, E_CIVIL_WAR,
	// ("Game: Your nation is thrust into civil war, "
	// " %s is declared the leader of the rebel states."),
	// cplayer.name);
	//
	// i = pplayer.cities.foo_list_size()/2; /* number to flip */
	// j = pplayer.cities.foo_list_size(); /* number left to process */
	// for (city pcity : pplayer.cities.data) {
	// if (!pcity.is_capital()) {
	// if (i >= j || (i > 0 && Rand.myrand(2) == 1)) {
	// /* Transfer city and units supported by this city to the new owner
	//
	// We do NOT resolve stack conflicts here, but rather later.
	// Reason: if we have a transporter from one city which is carrying
	// a unit from another city, and both cities join the rebellion. We
	// resolved stack conflicts for each city we would teleport the first
	// of the units we met since the other would have another owner */
	// transfer_city(cplayer, pcity, -1, false, false, false);
	// util.freelog(Log.LOG_VERBOSE, "%s declares allegiance to %s",
	// pcity.name, cplayer.name);
	// Plrhand.notify_player_ex(pplayer, pcity.tile, E_CITY_LOST,
	// "Game: %s declares allegiance to %s.",
	// pcity.name, cplayer.name);
	// i--;
	// }
	// }
	// j--;
	// }
	// }
	//
	// i = 0;
	//
	// resolve_unit_stacks(pplayer, cplayer, false);
	//
	// notify_player(null,
	// ("Game: The capture of %s's capital and the destruction "
	// "of the empire's administrative\n"
	// " structures have sparked a civil war. "
	// "Opportunists have flocked to the rebel cause,\n"
	// " and the upstart %s now holds power in %d "
	// "rebel provinces."),
	// pplayer.name, cplayer.name,
	// cplayer.cities.foo_list_size());
	// }
	//
	// /**************************************************************************
	// The client has send as a chunk of the attribute block.
	// **************************************************************************/
	// void handle_player_attribute_chunk(player pplayer,
	// struct packet_player_attribute_chunk
	// *chunk)
	// {
	// generic_handle_player_attribute_chunk(pplayer, chunk);
	// }
	//
	// /**************************************************************************
	// The client request an attribute block.
	// **************************************************************************/
	// void handle_player_attribute_block(player pplayer)
	// {
	// send_attribute_block(pplayer, pplayer.current_conn);
	// }
	//
	// /**************************************************************************
	// ...
	// (Hmm, how should "turn done" work for multi-connected non-observer
	// players?)
	// **************************************************************************/
	// void handle_player_turn_done(player pplayer)
	// {
	// pplayer.turn_done = true;
	//
	// check_for_full_turn_done();
	//
	// Plrhand.send_player_info(pplayer, null);
	// }
}