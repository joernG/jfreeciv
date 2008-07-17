package client;

public class Packhand_gen{
//
// /****************************************************************************
// *                       THIS FILE WAS GENERATED                             *
// * Script: common/generate_packets.py                                        *
// * Input:  common/packets.def                                                *
// *                       DO NOT CHANGE THIS FILE                             *
// ****************************************************************************/
//
//
//
//#ifdef HAVE_CONFIG_H
//#include <config.h>
//#endif
//
//#include "packets.h"
//
//#include "packhand_gen.h"
//    
//boolean client_handle_packet(enum packet_type type, void *packet)
//{
//  switch(type) {
//  case PACKET_PROCESSING_STARTED:
//    handle_processing_started();
//    return true;
//
//  case PACKET_PROCESSING_FINISHED:
//    handle_processing_finished();
//    return true;
//
//  case PACKET_FREEZE_HINT:
//    handle_freeze_hint();
//    return true;
//
//  case PACKET_THAW_HINT:
//    handle_thaw_hint();
//    return true;
//
//  case PACKET_SERVER_JOIN_REPLY:
//    handle_server_join_reply(
//      ((packet_server_join_reply )packet).you_can_join,
//      ((packet_server_join_reply )packet).message,
//      ((packet_server_join_reply )packet).capability,
//      ((packet_server_join_reply )packet).challenge_file,
//      ((packet_server_join_reply )packet).conn_id);
//    return true;
//
//  case PACKET_AUTHENTICATION_REQ:
//    handle_authentication_req(
//      ((packet_authentication_req )packet).type,
//      ((packet_authentication_req )packet).message);
//    return true;
//
//  case PACKET_SERVER_SHUTDOWN:
//    handle_server_shutdown();
//    return true;
//
//  case PACKET_NATION_UNAVAILABLE:
//    handle_nation_unavailable(
//      ((packet_nation_unavailable )packet).nation);
//    return true;
//
//  case PACKET_SELECT_RACES:
//    handle_select_races();
//    return true;
//
//  case PACKET_NATION_SELECT_OK:
//    handle_nation_select_ok();
//    return true;
//
//  case PACKET_GAME_STATE:
//    handle_game_state(
//      ((packet_game_state )packet).value);
//    return true;
//
//  case PACKET_ENDGAME_REPORT:
//    handle_endgame_report(packet);
//    return true;
//
//  case PACKET_TILE_INFO:
//    handle_tile_info(packet);
//    return true;
//
//  case PACKET_GAME_INFO:
//    handle_game_info(packet);
//    return true;
//
//  case PACKET_MAP_INFO:
//    handle_map_info(
//      ((packet_map_info )packet).xsize,
//      ((packet_map_info )packet).ysize,
//      ((packet_map_info )packet).topology_id);
//    return true;
//
//  case PACKET_NUKE_TILE_INFO:
//    handle_nuke_tile_info(
//      ((packet_nuke_tile_info )packet).x,
//      ((packet_nuke_tile_info )packet).y);
//    return true;
//
//  case PACKET_CHAT_MSG:
//    handle_chat_msg(
//      ((packet_chat_msg )packet).message,
//      ((packet_chat_msg )packet).x,
//      ((packet_chat_msg )packet).y,
//      ((packet_chat_msg )packet).event,
//      ((packet_chat_msg )packet).conn_id);
//    return true;
//
//  case PACKET_CITY_REMOVE:
//    handle_city_remove(
//      ((packet_city_remove )packet).city_id);
//    return true;
//
//  case PACKET_CITY_INFO:
//    handle_city_info(packet);
//    return true;
//
//  case PACKET_CITY_SHORT_INFO:
//    handle_city_short_info(packet);
//    return true;
//
//  case PACKET_CITY_INCITE_INFO:
//    handle_city_incite_info(
//      ((packet_city_incite_info )packet).city_id,
//      ((packet_city_incite_info )packet).cost);
//    return true;
//
//  case PACKET_CITY_NAME_SUGGESTION_INFO:
//    handle_city_name_suggestion_info(
//      ((packet_city_name_suggestion_info )packet).unit_id,
//      ((packet_city_name_suggestion_info )packet).name);
//    return true;
//
//  case PACKET_CITY_SABOTAGE_LIST:
//    handle_city_sabotage_list(
//      ((packet_city_sabotage_list )packet).diplomat_id,
//      ((packet_city_sabotage_list )packet).city_id,
//      ((packet_city_sabotage_list )packet).improvements);
//    return true;
//
//  case PACKET_PLAYER_REMOVE:
//    handle_player_remove(
//      ((packet_player_remove )packet).player_id);
//    return true;
//
//  case PACKET_PLAYER_INFO:
//    handle_player_info(packet);
//    return true;
//
//  case PACKET_PLAYER_ATTRIBUTE_CHUNK:
//    handle_player_attribute_chunk(packet);
//    return true;
//
//  case PACKET_UNIT_REMOVE:
//    handle_unit_remove(
//      ((packet_unit_remove )packet).unit_id);
//    return true;
//
//  case PACKET_UNIT_INFO:
//    handle_unit_info(packet);
//    return true;
//
//  case PACKET_UNIT_SHORT_INFO:
//    handle_unit_short_info(packet);
//    return true;
//
//  case PACKET_UNIT_COMBAT_INFO:
//    handle_unit_combat_info(
//      ((packet_unit_combat_info )packet).attacker_unit_id,
//      ((packet_unit_combat_info )packet).defender_unit_id,
//      ((packet_unit_combat_info )packet).attacker_hp,
//      ((packet_unit_combat_info )packet).defender_hp,
//      ((packet_unit_combat_info )packet).make_winner_veteran);
//    return true;
//
//  case PACKET_UNIT_BRIBE_INFO:
//    handle_unit_bribe_info(
//      ((packet_unit_bribe_info )packet).unit_id,
//      ((packet_unit_bribe_info )packet).cost);
//    return true;
//
//  case PACKET_UNIT_DIPLOMAT_POPUP_DIALOG:
//    handle_unit_diplomat_popup_dialog(
//      ((packet_unit_diplomat_popup_dialog )packet).diplomat_id,
//      ((packet_unit_diplomat_popup_dialog )packet).target_id);
//    return true;
//
//  case PACKET_DIPLOMACY_INIT_MEETING:
//    handle_diplomacy_init_meeting(
//      ((packet_diplomacy_init_meeting )packet).counterpart,
//      ((packet_diplomacy_init_meeting )packet).initiated_from);
//    return true;
//
//  case PACKET_DIPLOMACY_CANCEL_MEETING:
//    handle_diplomacy_cancel_meeting(
//      ((packet_diplomacy_cancel_meeting )packet).counterpart,
//      ((packet_diplomacy_cancel_meeting )packet).initiated_from);
//    return true;
//
//  case PACKET_DIPLOMACY_CREATE_CLAUSE:
//    handle_diplomacy_create_clause(
//      ((packet_diplomacy_create_clause )packet).counterpart,
//      ((packet_diplomacy_create_clause )packet).giver,
//      ((packet_diplomacy_create_clause )packet).type,
//      ((packet_diplomacy_create_clause )packet).value);
//    return true;
//
//  case PACKET_DIPLOMACY_REMOVE_CLAUSE:
//    handle_diplomacy_remove_clause(
//      ((packet_diplomacy_remove_clause )packet).counterpart,
//      ((packet_diplomacy_remove_clause )packet).giver,
//      ((packet_diplomacy_remove_clause )packet).type,
//      ((packet_diplomacy_remove_clause )packet).value);
//    return true;
//
//  case PACKET_DIPLOMACY_ACCEPT_TREATY:
//    handle_diplomacy_accept_treaty(
//      ((packet_diplomacy_accept_treaty )packet).counterpart,
//      ((packet_diplomacy_accept_treaty )packet).I_accepted,
//      ((packet_diplomacy_accept_treaty )packet).other_accepted);
//    return true;
//
//  case PACKET_PAGE_MSG:
//    handle_page_msg(
//      ((packet_page_msg )packet).message,
//      ((packet_page_msg )packet).event);
//    return true;
//
//  case PACKET_CONN_INFO:
//    handle_conn_info(packet);
//    return true;
//
//  case PACKET_CONN_PING_INFO:
//    handle_conn_ping_info(packet);
//    return true;
//
//  case PACKET_CONN_PING:
//    handle_conn_ping();
//    return true;
//
//  case PACKET_BEFORE_NEW_YEAR:
//    handle_before_new_year();
//    return true;
//
//  case PACKET_START_TURN:
//    handle_start_turn();
//    return true;
//
//  case PACKET_NEW_YEAR:
//    handle_new_year(
//      ((packet_new_year )packet).year,
//      ((packet_new_year )packet).turn);
//    return true;
//
//  case PACKET_SPACESHIP_INFO:
//    handle_spaceship_info(packet);
//    return true;
//
//  case PACKET_RULESET_UNIT:
//    handle_ruleset_unit(packet);
//    return true;
//
//  case PACKET_RULESET_GAME:
//    handle_ruleset_game(packet);
//    return true;
//
//  case PACKET_RULESET_GOVERNMENT_RULER_TITLE:
//    handle_ruleset_government_ruler_title(packet);
//    return true;
//
//  case PACKET_RULESET_TECH:
//    handle_ruleset_tech(packet);
//    return true;
//
//  case PACKET_RULESET_GOVERNMENT:
//    handle_ruleset_government(packet);
//    return true;
//
//  case PACKET_RULESET_TERRAIN_CONTROL:
//    handle_ruleset_terrain_control(packet);
//    return true;
//
//  case PACKET_RULESET_NATION:
//    handle_ruleset_nation(packet);
//    return true;
//
//  case PACKET_RULESET_CITY:
//    handle_ruleset_city(packet);
//    return true;
//
//  case PACKET_RULESET_BUILDING:
//    handle_ruleset_building(packet);
//    return true;
//
//  case PACKET_RULESET_TERRAIN:
//    handle_ruleset_terrain(packet);
//    return true;
//
//  case PACKET_RULESET_CONTROL:
//    handle_ruleset_control(packet);
//    return true;
//
//  case PACKET_SINGLE_WANT_HACK_REPLY:
//    handle_single_want_hack_reply(
//      ((packet_single_want_hack_reply )packet).you_have_hack);
//    return true;
//
//  case PACKET_GAME_LOAD:
//    handle_game_load(packet);
//    return true;
//
//  case PACKET_OPTIONS_SETTABLE_CONTROL:
//    handle_options_settable_control(packet);
//    return true;
//
//  case PACKET_OPTIONS_SETTABLE:
//    handle_options_settable(packet);
//    return true;
//
//  case PACKET_RULESET_CACHE_GROUP:
//    handle_ruleset_cache_group(packet);
//    return true;
//
//  case PACKET_RULESET_CACHE_EFFECT:
//    handle_ruleset_cache_effect(packet);
//    return true;
//
//  default:
//    return false;
//  }
//}
}