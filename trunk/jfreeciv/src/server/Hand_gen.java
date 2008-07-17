package server;

public class Hand_gen{
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
//#include "hand_gen.h"
//    
//boolean server_handle_packet(enum packet_type type, void *packet,
//			  player pplayer, connection pconn)
//{
//  switch(type) {
//  case PACKET_NATION_SELECT_REQ:
//    handle_nation_select_req(pplayer,
//      ((packet_nation_select_req )packet).nation_no,
//      ((packet_nation_select_req )packet).is_male,
//      ((packet_nation_select_req )packet).name,
//      ((packet_nation_select_req )packet).city_style);
//    return true;
//
//  case PACKET_CHAT_MSG_REQ:
//    handle_chat_msg_req(pconn,
//      ((packet_chat_msg_req )packet).message);
//    return true;
//
//  case PACKET_CITY_SELL:
//    handle_city_sell(pplayer,
//      ((packet_city_sell )packet).city_id,
//      ((packet_city_sell )packet).build_id);
//    return true;
//
//  case PACKET_CITY_BUY:
//    handle_city_buy(pplayer,
//      ((packet_city_buy )packet).city_id);
//    return true;
//
//  case PACKET_CITY_CHANGE:
//    handle_city_change(pplayer,
//      ((packet_city_change )packet).city_id,
//      ((packet_city_change )packet).build_id,
//      ((packet_city_change )packet).is_build_id_unit_id);
//    return true;
//
//  case PACKET_CITY_WORKLIST:
//    handle_city_worklist(pplayer,
//      ((packet_city_worklist )packet).city_id,
//      &((packet_city_worklist )packet).worklist);
//    return true;
//
//  case PACKET_CITY_MAKE_SPECIALIST:
//    handle_city_make_specialist(pplayer,
//      ((packet_city_make_specialist )packet).city_id,
//      ((packet_city_make_specialist )packet).worker_x,
//      ((packet_city_make_specialist )packet).worker_y);
//    return true;
//
//  case PACKET_CITY_MAKE_WORKER:
//    handle_city_make_worker(pplayer,
//      ((packet_city_make_worker )packet).city_id,
//      ((packet_city_make_worker )packet).worker_x,
//      ((packet_city_make_worker )packet).worker_y);
//    return true;
//
//  case PACKET_CITY_CHANGE_SPECIALIST:
//    handle_city_change_specialist(pplayer,
//      ((packet_city_change_specialist )packet).city_id,
//      ((packet_city_change_specialist )packet).from,
//      ((packet_city_change_specialist )packet).to);
//    return true;
//
//  case PACKET_CITY_RENAME:
//    handle_city_rename(pplayer,
//      ((packet_city_rename )packet).city_id,
//      ((packet_city_rename )packet).name);
//    return true;
//
//  case PACKET_CITY_OPTIONS_REQ:
//    handle_city_options_req(pplayer,
//      ((packet_city_options_req )packet).city_id,
//      ((packet_city_options_req )packet).value);
//    return true;
//
//  case PACKET_CITY_REFRESH:
//    handle_city_refresh(pplayer,
//      ((packet_city_refresh )packet).city_id);
//    return true;
//
//  case PACKET_CITY_INCITE_INQ:
//    handle_city_incite_inq(pconn,
//      ((packet_city_incite_inq )packet).city_id);
//    return true;
//
//  case PACKET_CITY_NAME_SUGGESTION_REQ:
//    handle_city_name_suggestion_req(pplayer,
//      ((packet_city_name_suggestion_req )packet).unit_id);
//    return true;
//
//  case PACKET_PLAYER_TURN_DONE:
//    handle_player_turn_done(pplayer);
//    return true;
//
//  case PACKET_PLAYER_RATES:
//    handle_player_rates(pplayer,
//      ((packet_player_rates )packet).tax,
//      ((packet_player_rates )packet).luxury,
//      ((packet_player_rates )packet).science);
//    return true;
//
//  case PACKET_PLAYER_CHANGE_GOVERNMENT:
//    handle_player_change_government(pplayer,
//      ((packet_player_change_government )packet).government);
//    return true;
//
//  case PACKET_PLAYER_RESEARCH:
//    handle_player_research(pplayer,
//      ((packet_player_research )packet).tech);
//    return true;
//
//  case PACKET_PLAYER_TECH_GOAL:
//    handle_player_tech_goal(pplayer,
//      ((packet_player_tech_goal )packet).tech);
//    return true;
//
//  case PACKET_PLAYER_ATTRIBUTE_BLOCK:
//    handle_player_attribute_block(pplayer);
//    return true;
//
//  case PACKET_PLAYER_ATTRIBUTE_CHUNK:
//    handle_player_attribute_chunk(pplayer, packet);
//    return true;
//
//  case PACKET_UNIT_MOVE:
//    handle_unit_move(pplayer,
//      ((packet_unit_move )packet).unit_id,
//      ((packet_unit_move )packet).x,
//      ((packet_unit_move )packet).y);
//    return true;
//
//  case PACKET_UNIT_BUILD_CITY:
//    handle_unit_build_city(pplayer,
//      ((packet_unit_build_city )packet).unit_id,
//      ((packet_unit_build_city )packet).name);
//    return true;
//
//  case PACKET_UNIT_DISBAND:
//    handle_unit_disband(pplayer,
//      ((packet_unit_disband )packet).unit_id);
//    return true;
//
//  case PACKET_UNIT_CHANGE_HOMECITY:
//    handle_unit_change_homecity(pplayer,
//      ((packet_unit_change_homecity )packet).unit_id,
//      ((packet_unit_change_homecity )packet).city_id);
//    return true;
//
//  case PACKET_UNIT_ESTABLISH_TRADE:
//    handle_unit_establish_trade(pplayer,
//      ((packet_unit_establish_trade )packet).unit_id);
//    return true;
//
//  case PACKET_UNIT_HELP_BUILD_WONDER:
//    handle_unit_help_build_wonder(pplayer,
//      ((packet_unit_help_build_wonder )packet).unit_id);
//    return true;
//
//  case PACKET_UNIT_GOTO:
//    handle_unit_goto(pplayer,
//      ((packet_unit_goto )packet).unit_id,
//      ((packet_unit_goto )packet).x,
//      ((packet_unit_goto )packet).y);
//    return true;
//
//  case PACKET_UNIT_ORDERS:
//    handle_unit_orders(pplayer, packet);
//    return true;
//
//  case PACKET_UNIT_AUTO:
//    handle_unit_auto(pplayer,
//      ((packet_unit_auto )packet).unit_id);
//    return true;
//
//  case PACKET_UNIT_LOAD:
//    handle_unit_load(pplayer,
//      ((packet_unit_load )packet).cargo_id,
//      ((packet_unit_load )packet).transporter_id);
//    return true;
//
//  case PACKET_UNIT_UNLOAD:
//    handle_unit_unload(pplayer,
//      ((packet_unit_unload )packet).cargo_id,
//      ((packet_unit_unload )packet).transporter_id);
//    return true;
//
//  case PACKET_UNIT_UPGRADE:
//    handle_unit_upgrade(pplayer,
//      ((packet_unit_upgrade )packet).unit_id);
//    return true;
//
//  case PACKET_UNIT_NUKE:
//    handle_unit_nuke(pplayer,
//      ((packet_unit_nuke )packet).unit_id);
//    return true;
//
//  case PACKET_UNIT_PARADROP_TO:
//    handle_unit_paradrop_to(pplayer,
//      ((packet_unit_paradrop_to )packet).unit_id,
//      ((packet_unit_paradrop_to )packet).x,
//      ((packet_unit_paradrop_to )packet).y);
//    return true;
//
//  case PACKET_UNIT_AIRLIFT:
//    handle_unit_airlift(pplayer,
//      ((packet_unit_airlift )packet).unit_id,
//      ((packet_unit_airlift )packet).city_id);
//    return true;
//
//  case PACKET_UNIT_BRIBE_INQ:
//    handle_unit_bribe_inq(pconn,
//      ((packet_unit_bribe_inq )packet).unit_id);
//    return true;
//
//  case PACKET_UNIT_TYPE_UPGRADE:
//    handle_unit_type_upgrade(pplayer,
//      ((packet_unit_type_upgrade )packet).type);
//    return true;
//
//  case PACKET_UNIT_DIPLOMAT_ACTION:
//    handle_unit_diplomat_action(pplayer,
//      ((packet_unit_diplomat_action )packet).diplomat_id,
//      ((packet_unit_diplomat_action )packet).action_type,
//      ((packet_unit_diplomat_action )packet).target_id,
//      ((packet_unit_diplomat_action )packet).value);
//    return true;
//
//  case PACKET_UNIT_CHANGE_ACTIVITY:
//    handle_unit_change_activity(pplayer,
//      ((packet_unit_change_activity )packet).unit_id,
//      ((packet_unit_change_activity )packet).activity,
//      ((packet_unit_change_activity )packet).activity_target);
//    return true;
//
//  case PACKET_DIPLOMACY_INIT_MEETING_REQ:
//    handle_diplomacy_init_meeting_req(pplayer,
//      ((packet_diplomacy_init_meeting_req )packet).counterpart);
//    return true;
//
//  case PACKET_DIPLOMACY_CANCEL_MEETING_REQ:
//    handle_diplomacy_cancel_meeting_req(pplayer,
//      ((packet_diplomacy_cancel_meeting_req )packet).counterpart);
//    return true;
//
//  case PACKET_DIPLOMACY_CREATE_CLAUSE_REQ:
//    handle_diplomacy_create_clause_req(pplayer,
//      ((packet_diplomacy_create_clause_req )packet).counterpart,
//      ((packet_diplomacy_create_clause_req )packet).giver,
//      ((packet_diplomacy_create_clause_req )packet).type,
//      ((packet_diplomacy_create_clause_req )packet).value);
//    return true;
//
//  case PACKET_DIPLOMACY_REMOVE_CLAUSE_REQ:
//    handle_diplomacy_remove_clause_req(pplayer,
//      ((packet_diplomacy_remove_clause_req )packet).counterpart,
//      ((packet_diplomacy_remove_clause_req )packet).giver,
//      ((packet_diplomacy_remove_clause_req )packet).type,
//      ((packet_diplomacy_remove_clause_req )packet).value);
//    return true;
//
//  case PACKET_DIPLOMACY_ACCEPT_TREATY_REQ:
//    handle_diplomacy_accept_treaty_req(pplayer,
//      ((packet_diplomacy_accept_treaty_req )packet).counterpart);
//    return true;
//
//  case PACKET_DIPLOMACY_CANCEL_PACT:
//    handle_diplomacy_cancel_pact(pplayer,
//      ((packet_diplomacy_cancel_pact )packet).other_player_id,
//      ((packet_diplomacy_cancel_pact )packet).clause);
//    return true;
//
//  case PACKET_REPORT_REQ:
//    handle_report_req(pconn,
//      ((packet_report_req )packet).type);
//    return true;
//
//  case PACKET_CONN_PONG:
//    handle_conn_pong(pconn);
//    return true;
//
//  case PACKET_SPACESHIP_LAUNCH:
//    handle_spaceship_launch(pplayer);
//    return true;
//
//  case PACKET_SPACESHIP_PLACE:
//    handle_spaceship_place(pplayer,
//      ((packet_spaceship_place )packet).type,
//      ((packet_spaceship_place )packet).num);
//    return true;
//
//  default:
//    return false;
//  }
//}
}