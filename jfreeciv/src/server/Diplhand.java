package server;

public class Diplhand{

// Freeciv - Copyright (C) 1996 - A Kjeldberg, L Gregersen, P Unold
//   This program is free software; you can redistribute it and/or modify
//   it under the terms of the GNU General Public License as published by
//   the Free Software Foundation; either version 2, or (at your option)
//   any later version.
//
//   This program is distributed in the hope that it will be useful,
//   but WITHOUT ANY WARRANTY; without even the implied warranty of
//   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//   GNU General Public License for more details.
//***********************************************************************/
//
//#ifdef HAVE_CONFIG_H
//#include <config.h>
//#endif
//
//#include <stdio.h>
//#include <stdlib.h>
//
//#include "diptreaty.h"
//#include "events.h"
//#include "fcintl.h"
//#include "Game.game.h"
//#include "log.h"
//#include "Map.map.h"
//#include "mem.h"
//#include "packets.h"
//#include "player.h"
//#include "unit.h"
//
//#include "citytools.h"
//#include "cityturn.h"
//#include "Gamelog.gamelog.h"
//#include "maphand.h"
//#include "plrhand.h"
//#include "settlers.h"
//#include "unittools.h"
//
//#include "advdiplomacy.h"
//
//#include "diplhand.h"
//
//#define SPECLIST_TAG treaty
//#define SPECLIST_TYPE struct Treaty
//#include "speclist.h"
//
//#define treaty_list_iterate(list, p) \
//    TYPED_LIST_ITERATE(struct Treaty, list, p)
//#define treaty_list_iterate_end  LIST_ITERATE_END
//
//static Speclists<treaty> treaties;
//
///**************************************************************************
//...
//**************************************************************************/
//void diplhand_init()
//{
//  treaty_list_init(&treaties);
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void diplhand_free()
//{
//  treaty_list_unlink_all(&treaties);
//}
//
///**************************************************************************
//...
//**************************************************************************/
//Treaty find_treaty(player plr0, player plr1)
//{
//  for (treaty ptreaty : treaties.data) {
//    if ((ptreaty.plr0 == plr0 && ptreaty.plr1 == plr1) ||
//	(ptreaty.plr0 == plr1 && ptreaty.plr1 == plr0)) {
//      return ptreaty;
//    }
//  } }
//
//  return null;
//}
//
///**************************************************************************
//pplayer clicked the accept button. If he accepted the treaty we check the
//clauses. If both players have now accepted the treaty we execute the agreed
//clauses.
//**************************************************************************/
//void handle_diplomacy_accept_treaty_req(player pplayer,
//					int counterpart)
//{
//  Treaty ptreaty;
//  player pother;
//  boolean *player_accept, *other_accept;
//
//  if (!is_valid_player_id(counterpart) || pplayer.player_no == counterpart) {
//    return;
//  }
//
//  pother = get_player(counterpart);
//  ptreaty = find_treaty(pplayer, pother);
//
//  if (!ptreaty) {
//    return;
//  }
//
//  if (ptreaty.plr0 == pplayer) {
//    player_accept = &ptreaty.accept0;
//    other_accept = &ptreaty.accept1;
//  } else {
//    player_accept = &ptreaty.accept1;
//    other_accept = &ptreaty.accept0;
//  }
//
//  if (!*player_accept) {	/* Tries to accept. */
//
//    /* Check that player who accepts can keep what (s)he promises. */
//
//    for (clause pclause : ptreaty.clauses.data) {
//      city pcity = null;
//
//      if (pclause.from == pplayer) {
//	switch(pclause.type) {
//	case CLAUSE_EMBASSY:
//          if (player_has_embassy(pother, pplayer)) {
//            util.freelog(Log.LOG_ERROR, "%s tried to give embassy to %s, who already " +
//                    "has an embassy", pplayer.name, pother.name);
//            return;
//          }
//          break;
//	case CLAUSE_ADVANCE:
//          if (!tech_is_available(pother, pclause.value)) {
//	    /* It is impossible to give a technology to a civilization that
//	     * can never possess it (the client should enforce this). */
//	    util.freelog(Log.LOG_ERROR, "Treaty: The %s can't have tech %s",
//                    Nation.get_nation_name_plural(pother.nation),
//		    get_tech_name(pplayer, pclause.value));
//	    notify_player(pplayer,
//                          "Game: The %s can't accept %s.",
//                          Nation.get_nation_name_plural(pother.nation),
//			  get_tech_name(pplayer, pclause.value));
//	    return;
//          }
//	  if (get_invention(pplayer, pclause.value) != TECH_KNOWN) {
//	    util.freelog(Log.LOG_ERROR,
//                    "The %s don't know tech %s, but try to give it to the %s.",
//		    Nation.get_nation_name_plural(pplayer.nation),
//		    get_tech_name(pplayer, pclause.value),
//		    Nation.get_nation_name_plural(pother.nation));
//	    notify_player(pplayer,
//			  "Game: You don't have tech %s, you can't accept treaty.",
//			  get_tech_name(pplayer, pclause.value));
//	    return;
//	  }
//	  break;
//	case CLAUSE_CITY:
//	  pcity = find_city_by_id(pclause.value);
//	  if (!pcity) { /* Can't find out cityname any more. */
//	    notify_player(pplayer,
//			  ("City you are trying to give no longer exists, " +
//			    "you can't accept treaty."));
//	    return;
//	  }
//	  if (pcity.owner != pplayer.player_no) {
//	    notify_player(pplayer,
//			  "You are not owner of %s, you can't accept treaty.",
//			  pcity.name);
//	    return;
//	  }
//	  if (pcity.is_capital()) {
//	    notify_player(pplayer,
//			  ("Game: Your capital (%s) is requested, " +
//			    "you can't accept treaty."),
//			  pcity.name);
//	    return;
//	  }
//	  break;
//	case CLAUSE_ALLIANCE:
//          if (!pplayer_can_ally(pplayer, pother)) {
//	    notify_player(pplayer,
//			  ("Game: You are at war with one of %s's " +
//			    "allies - an alliance with %s is impossible."),
//			  pother.name, pother.name);
//            return;
//          }
//          if (!pplayer_can_ally(pother, pplayer)) {
//	    notify_player(pplayer,
//			  ("Game: %s is at war with one of your allies " +
//			    "- an alliance with %s is impossible."),
//			  pother.name, pother.name);
//            return;
//          }
//          break;
//	case CLAUSE_GOLD:
//	  if (pplayer.economic.gold < pclause.value) {
//	    notify_player(pplayer,
//			  ("Game: You don't have enough gold, " +
//			    "you can't accept treaty."));
//	    return;
//	  }
//	  break;
//	default:
//	  ; /* nothing */
//	}
//      }
//    } }
//  }
//
//  *player_accept = ! *player_accept;
//
//  dlsend_packet_diplomacy_accept_treaty(&pplayer.connections,
//					pother.player_no, *player_accept,
//					*other_accept);
//  dlsend_packet_diplomacy_accept_treaty(&pother.connections,
//					pplayer.player_no, *other_accept,
//					*player_accept);
//
//  if (ptreaty.accept0 && ptreaty.accept1) {
//    int nclauses = ptreaty.clauses.foo_list_size();
//
//    dlsend_packet_diplomacy_cancel_meeting(&pplayer.connections,
//					   pother.player_no,
//					   pplayer.player_no);
//    dlsend_packet_diplomacy_cancel_meeting(&pother.connections,
//					   pplayer.player_no,
// 					   pplayer.player_no);
//
//    notify_player(pplayer,
//		  PL("Game: A treaty containing %d clause was agreed upon.",
//		      "Game: A treaty containing %d clauses was agreed upon.",
//		      nclauses),
//		  nclauses);
//    notify_player(pother,
//		  PL("Game: A treaty containing %d clause was agreed upon.",
//		      "Game: A treaty containing %d clauses was agreed upon.",
//		      nclauses),
//		  nclauses);
//
//    /* Check that one who accepted treaty earlier still have everything
//       (s)he promised to give. */
//
//    for (clause pclause : ptreaty.clauses.data) {
//      city pcity;
//      if (pclause.from == pother) {
//	switch (pclause.type) {
//	case CLAUSE_CITY:
//	  pcity = find_city_by_id(pclause.value);
//	  if (!pcity) { /* Can't find out cityname any more. */
//	    notify_player(pplayer,
//			  ("Game: One of the cities %s is giving away is destroyed! " +
//			    "Treaty canceled!"),
//			  Nation.get_nation_name_plural(pother.nation));
//	    notify_player(pother,
//			  ("Game: One of the cities %s is giving away is destroyed! " +
//			    "Treaty canceled!"),
//			  Nation.get_nation_name_plural(pother.nation));
//	    goto cleanup;
//	  }
//	  if (pcity.owner != pother.player_no) {
//	    notify_player(pplayer,
//			  ("Game: The %s no longer control %s! " +
//			    "Treaty canceled!"),
//			  Nation.get_nation_name_plural(pother.nation),
//			  pcity.name);
//	    notify_player(pother,
//			  ("Game: The %s no longer control %s! " +
//			    "Treaty canceled!"),
//			  Nation.get_nation_name_plural(pother.nation),
//			  pcity.name);
//	    goto cleanup;
//	  }
//	  if (pcity.is_capital()) {
//	    notify_player(pother,
//			  ("Game: Your capital (%s) is requested, " +
//			    "you can't accept treaty."), pcity.name);
//	    goto cleanup;
//	  }
//
//	  break;
//	case CLAUSE_ALLIANCE:
//          /* We need to recheck this way since things might have
//           * changed. */
//          if (!pplayer_can_ally(pother, pplayer)) {
//	    notify_player(pplayer,
//			  ("Game: %s is at war with one of your " +
//			    "allies - an alliance with %s is impossible."),
//			  pother.name, pother.name);
//	    notify_player(pother,
//			  ("Game: You are at war with one of %s's " +
//			    "allies - an alliance with %s is impossible."),
//			  pplayer.name, pplayer.name);
//	    goto cleanup;
//          }
//          break;
//	case CLAUSE_GOLD:
//	  if (pother.economic.gold < pclause.value) {
//	    notify_player(pplayer,
//			  ("Game: The %s don't have the promised amount " +
//			    "of gold! Treaty canceled!"),
//			  Nation.get_nation_name_plural(pother.nation));
//	    notify_player(pother,
//			  ("Game: The %s don't have the promised amount " +
//			    "of gold! Treaty canceled!"),
//			  Nation.get_nation_name_plural(pother.nation));
//	    goto cleanup;
//	  }
//	  break;
//	default:
//	  ; /* nothing */
//	}
//      }
//    } }
//
//    if (pplayer.ai.control) {
//      ai_treaty_accepted(pplayer, pother, ptreaty);
//    }
//    if (pother.ai.control) {
//      ai_treaty_accepted(pother, pplayer, ptreaty);
//    }
//
//    for (clause pclause : ptreaty.clauses.data) {
//      player pgiver = pclause.from;
//      player pdest = (pplayer == pgiver) ? pother : pplayer;
//      enum diplstate_type old_diplstate = 
//        pgiver.diplstates[pdest.player_no].type;
//
//      switch (pclause.type) {
//      case CLAUSE_EMBASSY:
//        establish_embassy(pdest, pgiver); /* sic */
//        Plrhand.notify_player_ex(pgiver, null, E_TREATY_SHARED_VISION,
//                         "Game: You gave an embassy to %s.",
//                         pdest.name);
//        Plrhand.notify_player_ex(pdest, null, E_TREATY_SHARED_VISION,
//                         "Game: %s allowed you to create an embassy!",
//                         pgiver.name);
//        Gamelog.gamelog(GAMELOG_TREATY, GL_EMBASSY, pgiver, pdest);
//        break;
//      case CLAUSE_ADVANCE:
//        /* It is possible that two players open the diplomacy dialog
//         * and try to give us the same tech at the same time. This
//         * should be handled discreetly instead of giving a core dump. */
//        if (get_invention(pdest, pclause.value) == TECH_KNOWN) {
//	  util.freelog(Log.LOG_VERBOSE,
//                  "The %s already know tech %s, that %s want to give them.",
//		  Nation.get_nation_name_plural(pdest.nation),
//		  get_tech_name(pplayer, pclause.value),
//		  Nation.get_nation_name_plural(pgiver.nation));
//          break;
//        }
//	Plrhand.notify_player_ex(pdest, null, E_TECH_GAIN,
//			 "Game: You are taught the knowledge of %s.",
//			 get_tech_name(pdest, pclause.value));
//
//	notify_embassies(pdest, pgiver,
//			 "Game: The %s have acquired %s from the %s.",
//			 Nation.get_nation_name_plural(pdest.nation),
//			 get_tech_name(pdest, pclause.value),
//			 Nation.get_nation_name_plural(pgiver.nation));
//
//        Gamelog.gamelog(GAMELOG_TECH, pdest, pgiver, pclause.value, "acquire");
//        Gamelog.gamelog(GAMELOG_TREATY, GL_TECH, pgiver, pdest);
//	do_dipl_cost(pdest);
//
//	found_new_tech(pdest, pclause.value, false, true, A_NONE);
//	break;
//      case CLAUSE_GOLD:
//	notify_player(pdest, "Game: You get %d gold.", pclause.value);
//	pgiver.economic.gold -= pclause.value;
//	pdest.economic.gold += pclause.value;
//        Gamelog.gamelog(GAMELOG_TREATY, GL_GOLD, pgiver, pdest);
//	break;
//      case CLAUSE_MAP:
//	give_map_from_player_to_player(pgiver, pdest);
//	notify_player(pdest, "Game: You receive %s's worldmap.",
//		      pgiver.name);
//        Gamelog.gamelog(GAMELOG_TREATY, GL_MAP, pgiver, pdest);
//	break;
//      case CLAUSE_SEAMAP:
//	give_seamap_from_player_to_player(pgiver, pdest);
//	notify_player(pdest, "Game: You receive %s's seamap.",
//		      pgiver.name);
//        Gamelog.gamelog(GAMELOG_TREATY, GL_SEAMAP, pgiver, pdest);
//	break;
//      case CLAUSE_CITY:
//	{
//	  city pcity = find_city_by_id(pclause.value);
//
//	  if (!pcity) {
//	    util.freelog(Log.LOG_NORMAL,
//		    "Treaty city id %d not found - skipping clause.",
//		    pclause.value);
//	    break;
//	  }
//
//	  Plrhand.notify_player_ex(pdest, pcity.tile, E_CITY_TRANSFER,
//			   "Game: You receive city of %s from %s.",
//			   pcity.name, pgiver.name);
//
//	  Plrhand.notify_player_ex(pgiver, pcity.tile, E_CITY_LOST,
//			   "Game: You give city of %s to %s.",
//			   pcity.name, pdest.name);
//
//          Gamelog.gamelog(GAMELOG_LOSECITY, pgiver, pdest, pcity, "acquired");
//          Gamelog.gamelog(GAMELOG_TREATY, GL_CITY, pgiver, pdest, pcity);
//	  transfer_city(pdest, pcity, -1, true, true, false);
//	  break;
//	}
//      case CLAUSE_CEASEFIRE:
//	pgiver.diplstates[pdest.player_no].type=DS_CEASEFIRE;
//	pgiver.diplstates[pdest.player_no].turns_left=16;
//	pdest.diplstates[pgiver.player_no].type=DS_CEASEFIRE;
//	pdest.diplstates[pgiver.player_no].turns_left=16;
//	Plrhand.notify_player_ex(pgiver, null, E_TREATY_CEASEFIRE,
//			 "Game: You agree on a cease-fire with %s.",
//			 pdest.name);
//	Plrhand.notify_player_ex(pdest, null, E_TREATY_CEASEFIRE,
//			 "Game: You agree on a cease-fire with %s.",
//			 pgiver.name);
//        Gamelog.gamelog(GAMELOG_TREATY, GL_CEASEFIRE, pgiver, pdest);
//	if (old_diplstate == diplstate_type.DS_ALLIANCE) {
//	  update_players_after_alliance_breakup(pgiver, pdest);
//	}
//	check_city_workers(pplayer);
//	check_city_workers(pother);
//	break;
//      case CLAUSE_PEACE:
//	pgiver.diplstates[pdest.player_no].type=DS_PEACE;
//	pdest.diplstates[pgiver.player_no].type=DS_PEACE;
//	Plrhand.notify_player_ex(pgiver, null, E_TREATY_PEACE,
//			 "Game: You agree on a peace treaty with %s.",
//			 pdest.name);
//	Plrhand.notify_player_ex(pdest, null, E_TREATY_PEACE,
//			 "Game: You agree on a peace treaty with %s.",
//			 pgiver.name);
//        Gamelog.gamelog(GAMELOG_TREATY, GL_PEACE, pgiver, pdest);
//	if (old_diplstate == diplstate_type.DS_ALLIANCE) {
//	  update_players_after_alliance_breakup(pgiver, pdest);
//	}
//	check_city_workers(pplayer);
//	check_city_workers(pother);
//	break;
//      case CLAUSE_ALLIANCE:
//	pgiver.diplstates[pdest.player_no].type=diplstate_type.DS_ALLIANCE;
//	pdest.diplstates[pgiver.player_no].type=diplstate_type.DS_ALLIANCE;
//	Plrhand.notify_player_ex(pgiver, null, E_TREATY_ALLIANCE,
//			 "Game: You agree on an alliance with %s.",
//			 pdest.name);
//	Plrhand.notify_player_ex(pdest, null, E_TREATY_ALLIANCE,
//			 "Game: You agree on an alliance with %s.",
//			 pgiver.name);
//
//        Gamelog.gamelog(GAMELOG_TREATY, GL_ALLIANCE, pgiver, pdest);
//	check_city_workers(pplayer);
//	check_city_workers(pother);
//	break;
//      case CLAUSE_VISION:
//	give_shared_vision(pgiver, pdest);
//	Plrhand.notify_player_ex(pgiver, null, E_TREATY_SHARED_VISION,
//			 "Game: You give shared vision to %s.",
//			 pdest.name);
//	Plrhand.notify_player_ex(pdest, null, E_TREATY_SHARED_VISION,
//			 "Game: %s gives you shared vision.",
//			 pgiver.name);
//        Gamelog.gamelog(GAMELOG_TREATY, GL_VISION, pgiver, pdest);
//	break;
//      case CLAUSE_LAST:
//      case CLAUSE_UNUSED:
//        util.freelog(Log.LOG_ERROR, "Received bad clause type");
//        break;
//      }
//
//    } }
//  cleanup:
//    treaty_list_unlink(&treaties, ptreaty);
//    clear_treaty(ptreaty);
//    free(ptreaty);
//    Plrhand.send_player_info(pplayer, null);
//    Plrhand.send_player_info(pother, null);
//  }
//}
//
///****************************************************************************
//  Create an embassy. pplayer gets an embassy with aplayer.
//****************************************************************************/
//void establish_embassy(player pplayer, player aplayer)
//{
//  /* Establish the embassy. */
//  pplayer.embassy |= (1 << aplayer.player_no);
//  Plrhand.send_player_info(pplayer, pplayer);
//  Plrhand.send_player_info(pplayer, aplayer);  /* update player dialog with embassy */
//  Plrhand.send_player_info(aplayer, pplayer);  /* INFO_EMBASSY level info */
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void handle_diplomacy_remove_clause_req(player pplayer,
//					int counterpart, int giver,
//					enum clause_type type, int value)
//{
//  Treaty ptreaty;
//  player pgiver, *pother;
//
//  if (!is_valid_player_id(counterpart) || pplayer.player_no == counterpart
//      || !is_valid_player_id(giver)) {
//    return;
//  }
//
//  pother = get_player(counterpart);
//  pgiver = get_player(giver);
//
//  if (pgiver != pplayer && pgiver != pother) {
//    return;
//  }
//  
//  ptreaty = find_treaty(pplayer, pother);
//
//  if (ptreaty && remove_clause(ptreaty, pgiver, type, value)) {
//    dlsend_packet_diplomacy_remove_clause(&pplayer.connections,
//					  pother.player_no, giver, type,
//					  value);
//    dlsend_packet_diplomacy_remove_clause(&pother.connections,
//					  pplayer.player_no, giver, type,
//					  value);
//    if (pplayer.ai.control) {
//      ai_treaty_evaluate(pplayer, pother, ptreaty);
//    }
//    if (pother.ai.control) {
//      ai_treaty_evaluate(pother, pplayer, ptreaty);
//    }
//  }
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void handle_diplomacy_create_clause_req(player pplayer,
//					int counterpart, int giver,
//					enum clause_type type, int value)
//{
//  Treaty ptreaty;
//  player pgiver, *pother;
//
//  if (!is_valid_player_id(counterpart) || pplayer.player_no == counterpart
//      || !is_valid_player_id(giver)) {
//    return;
//  }
//
//  pother = get_player(counterpart);
//  pgiver = get_player(giver);
//
//  if (pgiver != pplayer && pgiver != pother) {
//    return;
//  }
//
//  ptreaty = find_treaty(pplayer, pother);
//
//  if (ptreaty && add_clause(ptreaty, pgiver, type, value)) {
//    /* 
//     * If we are trading cities, then it is possible that the
//     * dest is unaware of it's existence.  We have 2 choices,
//     * forbid it, or lighten that area.  If we assume that
//     * the giver knows what they are doing, then 2. is the
//     * most powerful option - I'll choose that for now.
//     *                           - Kris Bubendorfer
//     */
//    if (type == CLAUSE_CITY) {
//      city pcity = find_city_by_id(value);
//
//      if (pcity && !Maphand.map_is_known_and_seen(pcity.tile, pother))
//	give_citymap_from_player_to_player(pcity, pplayer, pother);
//    }
//
//    dlsend_packet_diplomacy_create_clause(&pplayer.connections,
//					  pother.player_no, giver, type,
//					  value);
//    dlsend_packet_diplomacy_create_clause(&pother.connections,
//					  pplayer.player_no, giver, type,
//					  value);
//    if (pplayer.ai.control) {
//      ai_treaty_evaluate(pplayer, pother, ptreaty);
//    }
//    if (pother.ai.control) {
//      ai_treaty_evaluate(pother, pplayer, ptreaty);
//    }
//  }
//}
//
///**************************************************************************
//...
//**************************************************************************/
//static void really_diplomacy_cancel_meeting(player pplayer,
//					    player pother)
//{
//  Treaty ptreaty = find_treaty(pplayer, pother);
//
//  if (ptreaty) {
//    dlsend_packet_diplomacy_cancel_meeting(&pother.connections,
//					   pplayer.player_no,
//					   pplayer.player_no);
//    notify_player(pother, "Game: %s canceled the meeting!", 
//		  pplayer.name);
//    /* Need to send to pplayer too, for multi-connects: */
//    dlsend_packet_diplomacy_cancel_meeting(&pplayer.connections,
//					   pother.player_no,
//					   pplayer.player_no);
//    notify_player(pplayer, "Game: Meeting with %s canceled.", 
//		  pother.name);
//    treaty_list_unlink(&treaties, ptreaty);
//    clear_treaty(ptreaty);
//    free(ptreaty);
//  }
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void handle_diplomacy_cancel_meeting_req(player pplayer,
//					 int counterpart)
//{
//  if (!is_valid_player_id(counterpart) || pplayer.player_no == counterpart) {
//    return;
//  }
//
//  really_diplomacy_cancel_meeting(pplayer, get_player(counterpart));
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void handle_diplomacy_init_meeting_req(player pplayer,
//				       int counterpart)
//{
//  player pother;
//
//  if (!is_valid_player_id(counterpart) || pplayer.player_no == counterpart) {
//    return;
//  }
//
//  pother = get_player(counterpart);
//
//  if (find_treaty(pplayer, pother)) {
//    return;
//  }
//
//  if (is_barbarian(pplayer) || is_barbarian(pother)) {
//    notify_player(pplayer, "Your diplomatic envoy was decapitated!");
//    return;
//  }
//
//  if (could_meet_with_player(pplayer, pother)) {
//    Treaty ptreaty;
//
//    ptreaty = fc_malloc(sizeof(struct Treaty));
//    init_treaty(ptreaty, pplayer, pother);
//    treaty_list_insert(&treaties, ptreaty);
//
//    dlsend_packet_diplomacy_init_meeting(&pplayer.connections,
//					 pother.player_no,
//					 pplayer.player_no);
//    dlsend_packet_diplomacy_init_meeting(&pother.connections,
//					 pplayer.player_no,
//					 pplayer.player_no);
//  }
//}
//
///**************************************************************************
//  Send information on any on-going diplomatic meetings for connection's
//  player.  (For re-connection in multi-connect case.)
//**************************************************************************/
//void send_diplomatic_meetings(connection dest)
//{
//  player pplayer = dest.player;
//
//  if (!pplayer) {
//    return;
//  }
//  for(player other_player: Game.game.players){
//    Treaty ptreaty = find_treaty(pplayer, other_player);
//
//    if (ptreaty) {
//      dsend_packet_diplomacy_init_meeting(dest, pplayer.player_no,
//					  other_player.player_no);
//      for (clause pclause : ptreaty.clauses.data) {
//	dsend_packet_diplomacy_create_clause(dest, pplayer.player_no,
//					     other_player.player_no,
//					     pclause.type,
//					     pclause.from.player_no);
//      } }
//    }
//  }
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void cancel_all_meetings(player pplayer)
//{
//  for(player pplayer2: Game.game.players){
//    if (find_treaty(pplayer, pplayer2)) {
//      really_diplomacy_cancel_meeting(pplayer, pplayer2);
//    }
//  }
//}
}