package server;

import common.Game;

public class Diplomats{

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
//
//#include "events.h"
//#include "fcintl.h"
//#include "government.h"
//#include "log.h"
//#include "player.h"
//#include "rand.h"
//
//#include "citytools.h"
//#include "cityturn.h"
//#include "diplhand.h"
//#include "Gamelog.gamelog.h"
//#include "gotohand.h"
//#include "plrhand.h"
//#include "unithand.h"
//#include "unittools.h"
//
//#include "diplomats.h"
//
//
///****************************************************************************/
//
//static void diplomat_charge_movement (unit pdiplomat,
//				      tile ptile);
//static boolean diplomat_success_vs_defender(unit patt, unit pdef,
//  						tile pdefender_tile);
//static boolean diplomat_infiltrate_tile(player pplayer, player cplayer,
//				     unit pdiplomat, tile ptile);
//static void diplomat_escape(player pplayer, unit pdiplomat,
//			    final city pcity);
//static void maybe_cause_incident(enum diplomat_actions action, player offender,
//				 unit victim_unit, city victim_city);
//
///******************************************************************************
//  Poison a city's water supply.
//
//  - Only a Spy can poison a city's water supply.
//  - Only allowed against players you are at war with.
//
//  - Check for infiltration success.  Our poisoner may not survive this.
//  - Only cities of size greater than one may be poisoned.
//  - If successful, reduces population by one point.
//
//  - The poisoner may be captured and executed, or escape to its home town.
//****************************************************************************/
//void spy_poison(player pplayer, unit pdiplomat,
//		city pcity)
//{
//  player cplayer;
//
//  /* Fetch target city's player.  Sanity checks. */
//  if (!pcity)
//    return;
//  cplayer = City.city_owner(pcity);
//  if (!cplayer || !Player_P.pplayers_at_war(pplayer, cplayer))
//    return;
//
//  util.freelog (Log.LOG_DEBUG, "poison: unit: %d", pdiplomat.id);
//
//  /* If not a Spy, can't poison. */
//  if (!unit_flag (pdiplomat, F_SPY))
//    return;
//
//  /* Check if the Diplomat/Spy succeeds against defending Diplomats/Spies. */
//  if (!diplomat_infiltrate_tile(pplayer, cplayer, pdiplomat, 
//                                pcity.tile)) {
//    return;
//  }
//
//  util.freelog (Log.LOG_DEBUG, "poison: infiltrated");
//
//  /* If city is too small, can't poison. */
//  if (pcity.size < 2) {
//    Plrhand.notify_player_ex(pplayer, pcity.tile, E_MY_DIPLOMAT_FAILED,
//		     ("Game: Your %s could not poison the water" +
//		       " supply in %s."),
//		     Unittype_P.unit_name(pdiplomat.type), pcity.name);
//    util.freelog (Log.LOG_DEBUG, "poison: target city too small");
//    return;
//  }
//
//  util.freelog (Log.LOG_DEBUG, "poison: succeeded");
//
//  /* Poison people! */
//  Cityturn.city_reduce_size(pcity, 1);
//
//  /* Notify everybody involved. */
//  Plrhand.notify_player_ex(pplayer, pcity.tile, E_MY_DIPLOMAT_POISON,
//		   "Game: Your %s poisoned the water supply of %s.",
//		   Unittype_P.unit_name(pdiplomat.type), pcity.name);
//  Plrhand.notify_player_ex(cplayer, pcity.tile, E_ENEMY_DIPLOMAT_POISON,
//		   ("Game: %s is suspected of poisoning the water supply" +
//		     " of %s."), pplayer.name, pcity.name);
//
//  /* Update clients. */
//  Cityturn.city_refresh (pcity);  
//  Citytools.send_city_info(null, pcity);
//
//  /* this may cause a diplomatic incident */
//  maybe_cause_incident(SPY_POISON, pplayer, null, pcity);
//
//  /* Now lets see if the spy survives. */
//  diplomat_escape(pplayer, pdiplomat, pcity);
//}
//
///******************************************************************************
//  Investigate a city.
//
//  - Either a Diplomat or Spy can investigate a city.
//  - Allowed against all players.
//
//  - It costs some minimal movement to investigate a city.
//
//  - Diplomats util.die after investigation.
//  - Spies always survive.  There is no risk.
//****************************************************************************/
//void diplomat_investigate(player pplayer, unit pdiplomat,
//			  city pcity)
//{
//  player cplayer;
//  boolean first_packet;
//  struct packet_unit_short_info unit_packet;
//  struct packet_city_info city_packet;
//
//  /* Fetch target city's player.  Sanity checks. */
//  if (!pcity)
//    return;
//  cplayer = City.city_owner (pcity);
//  if ((cplayer == pplayer) || !cplayer)
//    return;
//
//  util.freelog (Log.LOG_DEBUG, "investigate: unit: %d", pdiplomat.id);
//
//  /* Do It... */
//  update_dumb_city(pplayer, pcity);
//  /* Special case for a diplomat/spy investigating a city:
//     The investigator needs to know the supported and present
//     units of a city, whether or not they are fogged. So, we
//     send a list of them all before sending the city info.
//     As this is a special case we bypass send_unit_info. */
//  first_packet = true;
//  for (unit punit : pcity.units_supported.data) {
//    package_short_unit(punit, &unit_packet,
//                       UNIT_INFO_CITY_SUPPORTED, pcity.id, first_packet);
//    lsend_packet_unit_short_info(&pplayer.connections, &unit_packet);
//    first_packet = false;
//  } }
//  unit_list_iterate((pcity.tile).units, punit) {
//    package_short_unit(punit, &unit_packet,
//                       UNIT_INFO_CITY_PRESENT, pcity.id, first_packet);
//    lsend_packet_unit_short_info(&pplayer.connections, &unit_packet);
//    first_packet = false;
//  } }
//  /* Send city info to investigator's player.
//     As this is a special case we bypass Citytools.send_city_info. */
//  package_city(pcity, &city_packet, true);
//  lsend_packet_city_info(&pplayer.connections, &city_packet);
//
//  /* Charge a nominal amount of movement for this. */
//  (pdiplomat.moves_left)--;
//  if (pdiplomat.moves_left < 0) {
//    pdiplomat.moves_left = 0;
//  }
//
//  /* this may cause a diplomatic incident */
//  maybe_cause_incident(DIPLOMAT_INVESTIGATE, pplayer, null, pcity);
//
//  /* Spies always survive. Diplomats never do. */
//  if (!unit_flag (pdiplomat, F_SPY)) {
//    Unittools.wipe_unit(pdiplomat);
//  } else {
//    Unittools.send_unit_info (pplayer, pdiplomat);
//  }
//}
//
///******************************************************************************
//  Get list of improvements from city (for purposes of sabotage).
//
//  - Only a Spy can get a a city's sabotage list.
//
//  - Always successful; returns list.
//
//  - Spies always survive.
//
//  Only send back to the originating connection, if there is one. (?)
//****************************************************************************/
//void spy_get_sabotage_list(player pplayer, unit pdiplomat,
//			   city pcity)
//{
//  struct packet_city_sabotage_list packet;
//  char *p;
//
//  /* Send city improvements info to player. */
//  p = packet.improvements;
//
//  for (int i = 0; i < Game.game.num_impr_types; i++) {
//    *p++=City.city_got_building(pcity,i)?'1':'0';
//  } ;
//
//  *p='\0';
//  packet.diplomat_id = pdiplomat.id;
//  packet.city_id = pcity.id;
//  lsend_packet_city_sabotage_list(player_reply_dest(pplayer), &packet);
//
//  /* this may cause a diplomatic incident */
//  maybe_cause_incident(SPY_GET_SABOTAGE_LIST, pplayer, null, pcity);
//}
//
///******************************************************************************
//  Establish an embassy.
//
//  - Either a Diplomat or Spy can establish an embassy.
//
//  - "Foul" ambassadors are detected and executed.
//  - Barbarians always execute ambassadors.
//  - Otherwise, the embassy is created.
//  - It costs some minimal movement to establish an embassy.
//
//  - Diplomats are consumed in creation of embassy.
//  - Spies always survive.
//****************************************************************************/
//void diplomat_embassy(player pplayer, unit pdiplomat,
//		      city pcity)
//{
//  player cplayer;
//
//  /* Fetch target city's player.  Sanity checks. */
//  if (!pcity)
//    return;
//  cplayer = City.city_owner (pcity);
//  if ((cplayer == pplayer) || !cplayer)
//    return;
//
//  util.freelog (Log.LOG_DEBUG, "embassy: unit: %d", pdiplomat.id);
//
//  /* Check for "foul" ambassador. */
//  if (pdiplomat.foul) {
//    Plrhand.notify_player_ex(pplayer, pcity.tile, E_MY_DIPLOMAT_FAILED,
//		     ("Game: Your %s was executed in %s on suspicion" +
//		       " of spying.  The %s welcome future diplomatic" +
//		       " efforts providing the Ambassador is reputable."),
//		     Unittype_P.unit_name(pdiplomat.type),
//		     pcity.name, Nation.Nation.get_nation_name_plural(cplayer.nation));
//    Plrhand.notify_player_ex(cplayer, pcity.tile, E_ENEMY_DIPLOMAT_FAILED,
//		     ("You executed a %s the %s had sent to establish" +
//		       " an embassy in %s for being untrustworthy"),
//		     Unittype_P.unit_name(pdiplomat.type),
//		     Nation.Nation.get_nation_name_plural(pplayer.nation), pcity.name);
//    Unittools.wipe_unit(pdiplomat);
//    return;
//  }
//
//  /* Check for Barbarian response. */
//  if (is_barbarian (cplayer)) {
//    Plrhand.notify_player_ex(pplayer, pcity.tile, E_MY_DIPLOMAT_FAILED,
//		     "Game: Your %s was executed in %s by primitive %s.",
//		     Unittype_P.unit_name(pdiplomat.type),
//		     pcity.name, Nation.Nation.get_nation_name_plural(cplayer.nation));
//    Unittools.wipe_unit(pdiplomat);
//    return;
//  }
//
//  util.freelog (Log.LOG_DEBUG, "embassy: succeeded");
//
//  establish_embassy(pplayer, cplayer);
//
//  /* Notify everybody involved. */
//  Plrhand.notify_player_ex(pplayer, pcity.tile, E_MY_DIPLOMAT_EMBASSY,
//		   "Game: You have established an embassy in %s.",
//		   pcity.name);
//  Plrhand.notify_player_ex(cplayer, pcity.tile, E_ENEMY_DIPLOMAT_EMBASSY,
//		   "Game: The %s have established an embassy in %s.",
//		   Nation.Nation.get_nation_name_plural(pplayer.nation), pcity.name);
//  Gamelog.gamelog(GAMELOG_EMBASSY, pplayer, pcity);
//
//  /* Charge a nominal amount of movement for this. */
//  (pdiplomat.moves_left)--;
//  if (pdiplomat.moves_left < 0) {
//    pdiplomat.moves_left = 0;
//  }
//
//  /* this may cause a diplomatic incident */
//  maybe_cause_incident(DIPLOMAT_EMBASSY, pplayer, null, pcity);
//
//  /* Spies always survive. Diplomats never do. */
//  if (!unit_flag (pdiplomat, F_SPY)) {
//    Unittools.wipe_unit(pdiplomat);
//  } else {
//    Unittools.send_unit_info (pplayer, pdiplomat);
//  }
//}
//
///******************************************************************************
//  Sabotage an enemy unit.
//
//  - Only a Spy can sabotage an enemy unit.
//  - Only allowed against players you are at war with.
//
//  - Can't sabotage a unit if:
//    - It has only one hit point left.
//    - It's not the only unit on the square
//      (this is handled outside this function).
//  - If successful, reduces hit points by half of those remaining.
//
//  - The saboteur may be captured and executed, or escape to its home town.
//****************************************************************************/
//void spy_sabotage_unit(player pplayer, unit pdiplomat,
//		       unit pvictim)
//{
//  player uplayer;
//
//  /* Fetch target unit's player.  Sanity checks. */
//  if (!pvictim)
//    return;
//  uplayer = pvictim.unit_owner();
//  if (!uplayer || Player_P.pplayers_allied(pplayer, uplayer))
//    return;
//
//  util.freelog (Log.LOG_DEBUG, "sabotage-unit: unit: %d", pdiplomat.id);
//
//  /* If not a Spy, can't sabotage unit. */
//  if (!unit_flag (pdiplomat, F_SPY))
//    return;
//
//  /* If unit has too few hp, can't sabotage. */
//  if (pvictim.hp < 2) {
//    Plrhand.notify_player_ex(pplayer, pvictim.tile, E_MY_DIPLOMAT_FAILED,
//		     "Game: Your %s could not sabotage %s's %s.",
//		     Unittype_P.unit_name(pdiplomat.type),
//		     pvictim.unit_owner().name, Unittype_P.unit_name(pvictim.type));
//    util.freelog (Log.LOG_DEBUG, "sabotage-unit: unit has too few hit points");
//    return;
//  }
//
//  /* Check if the Diplomat/Spy succeeds against defending Diplomats/Spies. */
//  if (!diplomat_infiltrate_tile(pplayer, uplayer, pdiplomat, 
//                                pvictim.tile)) {
//    return;
//  }
//
//  util.freelog (Log.LOG_DEBUG, "sabotage-unit: succeeded");
//
//  /* Sabotage the unit by removing half its remaining hit points. */
//  pvictim.hp /= 2;
//  Unittools.send_unit_info(null, pvictim);
//
//  /* Notify everybody involved. */
//  Plrhand.notify_player_ex(pplayer, pvictim.tile, E_MY_DIPLOMAT_SABOTAGE,
//		   "Game: Your %s succeeded in sabotaging %s's %s.",
//		   Unittype_P.unit_name(pdiplomat.type),
//		   pvictim.unit_owner().name, Unittype_P.unit_name(pvictim.type));
//  Plrhand.notify_player_ex(uplayer, pvictim.tile,
//		   E_ENEMY_DIPLOMAT_SABOTAGE,
//		   "Game: Your %s was sabotaged by %s!",
//		   Unittype_P.unit_name(pvictim.type), pplayer.name);
//
//  /* this may cause a diplomatic incident */
//  maybe_cause_incident(SPY_SABOTAGE_UNIT, pplayer, pvictim, null);
//
//  /* Now lets see if the spy survives. */
//  diplomat_escape(pplayer, pdiplomat, null);
//}
//
///******************************************************************************
//  Bribe an enemy unit.
//
//  - Either a Diplomat or Spy can bribe an other players unit.
//  
//  - Can't bribe a unit if:
//    - Owner runs an unbribable government (e.g., democracy).
//    - Player doesn't have enough gold.
//    - It's not the only unit on the square
//      (this is handled outside this function).
//    - You are allied with the unit owner.
//  - Otherwise, the unit will be bribed.
//
//  - A successful briber will try to move onto the victim's square.
//****************************************************************************/
//void diplomat_bribe(player pplayer, unit pdiplomat,
//		    unit pvictim)
//{
//  player uplayer;
//  int diplomat_id;
//  tile victim_tile;
//  boolean vet = false;
//  unit gained_unit = null;
//  
//  /* Fetch target unit's player.  Sanity checks. */
//  if (!pvictim)
//    return;
//  uplayer = pvictim.unit_owner();
//  /* We might make it allowable in peace with a loss of reputation */
//  if (!uplayer || Player_P.pplayers_allied(pplayer, uplayer))
//    return;
//
//  util.freelog (Log.LOG_DEBUG, "bribe-unit: unit: %d", pdiplomat.id);
//
//  /* Update bribe cost. */
//  if (pvictim.bribe_cost == -1) {
//    util.freelog (Log.LOG_ERROR, "Bribe cost -1 in diplomat_bribe by %s",
//	     pplayer.name);
//    pvictim.bribe_cost = unit_bribe_cost (pvictim);
//  }
//
//  /* Check for unit from a bribable government. */
//  if (Government.government_has_flag(get_gov_pplayer(pvictim.unit_owner()),
//			  G_UNBRIBABLE)) {
//    Plrhand.notify_player_ex(pplayer, pdiplomat.tile,
//		     E_MY_DIPLOMAT_FAILED,
//		     "Game: You can't bribe a unit from this nation.");
//    util.freelog (Log.LOG_DEBUG, "bribe-unit: unit's government is unbribable");
//    return;
//  }
//
//  /* If player doesn't have enough gold, can't bribe. */
//  if (pplayer.economic.gold < pvictim.bribe_cost) {
//    Plrhand.notify_player_ex(pplayer, pdiplomat.tile,
//		     E_MY_DIPLOMAT_FAILED,
//		     ("Game: You don't have enough gold to" +
//		       " bribe %s's %s."),
//		     pvictim.unit_owner().name, Unittype_P.unit_name(pvictim.type));
//    util.freelog (Log.LOG_DEBUG, "bribe-unit: not enough gold");
//    return;
//  }
//
//  if (unit_flag(pvictim, F_UNBRIBABLE)) {
//    Plrhand.notify_player_ex(pplayer, pdiplomat.tile, E_MY_DIPLOMAT_FAILED,
//		     "Game: You cannot bribe %s!", Unittype_P.unit_name(pvictim.type));
//    return;
//  }
//
//  util.freelog (Log.LOG_DEBUG, "bribe-unit: succeeded");
//
//  /* Convert the unit to your cause. Fog is lifted in the create algorithm. */
//  gained_unit = Unittools.create_unit_full(pplayer, pvictim.tile,
//                                 pvictim.type, pvictim.veteran,
//                                 pdiplomat.homecity, pvictim.moves_left,
//                                 pvictim.hp, null);
//
//  /* Copy some more unit fields */
//  gained_unit.fuel        = pvictim.fuel;
//  gained_unit.foul        = pvictim.foul;
//  gained_unit.paradropped = pvictim.paradropped;
//
//  /* Inform owner about less than full fuel */
//  Unittools.send_unit_info(pplayer, gained_unit);
//
//  /* Check if the unit gained veteran level */
//  vet = maybe_make_veteran(pdiplomat);
//  
//  /* Notify everybody involved. */
//  if (vet) {
//    Plrhand.notify_player_ex(pplayer, pvictim.tile, E_MY_DIPLOMAT_BRIBE,
//		     ("Game: Your %s succeeded in bribing %s's %s" +
//		        " and became more experienced."),
//		     Unittype_P.unit_name(pdiplomat.type),
//		     pvictim.unit_owner().name, Unittype_P.unit_name(pvictim.type));
//  } else {
//    Plrhand.notify_player_ex(pplayer, pvictim.tile, E_MY_DIPLOMAT_BRIBE,
//		     "Game: Your %s succeeded in bribing %s's %s.",		     Unittype_P.unit_name(pdiplomat.type),
//		     pvictim.unit_owner().name, Unittype_P.unit_name(pvictim.type));
//  }
//  Plrhand.notify_player_ex(uplayer, pvictim.tile, E_ENEMY_DIPLOMAT_BRIBE,
//		   "Game: Your %s was bribed by %s.",
//		   Unittype_P.unit_name(pvictim.type), pplayer.name);
//
//  /* This costs! */
//  pplayer.economic.gold -= pvictim.bribe_cost;
//
//  /* This may cause a diplomatic incident */
//  maybe_cause_incident(DIPLOMAT_BRIBE, pplayer, pvictim, null);
//
//  /* Be sure to wipe the converted unit! */
//  victim_tile = pvictim.tile;
//  Unittools.wipe_unit(pvictim);
//
//  /* Now, try to move the briber onto the victim's square. */
//  diplomat_id = pdiplomat.id;
//  if (!Unithand.handle_unit_move_request(pdiplomat, victim_tile, false, false)) {
//    pdiplomat.moves_left = 0;
//  }
//  if (Player_P.player_find_unit_by_id(pplayer, diplomat_id)) {
//    Unittools.send_unit_info (pplayer, pdiplomat);
//  }
//
//  /* Update clients. */
//  Plrhand.send_player_info(pplayer, null);
//}
//
///****************************************************************************
//  Try to steal a technology from an enemy city.
//  If "technology" is Game.game.num_tech_types, steal a random technology.
//  Otherwise, steal the technology whose ID is "technology".
//  (Note: Only Spies can select what to steal.)
//
//  - Either a Diplomat or Spy can steal a technology.
//
//  - Check for infiltration success.  Our thief may not survive this.
//  - Check for basic success.  Again, our thief may not survive this.
//  - If a technology has already been stolen from this city at any time:
//    - Diplomats will be caught and executed.
//    - Spies will have an increasing chance of being caught and executed.
//  - Determine target, given arguments and finalraints.
//  - Steal technology!
//
//  - The thief may be captured and executed, or escape to its home town.
//
//  FIXME: It should give a loss of reputation to steal from a player you are
//  not at war with
//****************************************************************************/
//void diplomat_get_tech(player pplayer, unit pdiplomat, 
//		       city pcity, int technology)
//{
//  player cplayer;
//  int count, which, target;
//
//  /* Fetch target civilization's player.  Sanity checks. */
//  if (!pcity)
//    return;
//  cplayer = City.city_owner (pcity);
//  if ((cplayer == pplayer) || !cplayer)
//    return;
//
//  util.freelog (Log.LOG_DEBUG, "steal-tech: unit: %d", pdiplomat.id);
//
//  /* If not a Spy, do something random. */
//  if (!unit_flag (pdiplomat, F_SPY))
//    technology = Game.game.num_tech_types;
//
//  /* Check if the Diplomat/Spy succeeds against defending Diplomats/Spies. */
//  if (!diplomat_infiltrate_tile(pplayer, cplayer, pdiplomat, 
//                                pcity.tile)) {
//    return;
//  }
//
//  util.freelog (Log.LOG_DEBUG, "steal-tech: infiltrated");
//
//  /* Check if the Diplomat/Spy succeeds with his/her task. */
//  /* (Twice as difficult if target is specified.) */
//  /* (If already stolen from, impossible for Diplomats and harder for Spies.) */
//  if ((pcity.steal > 0) && (!unit_flag (pdiplomat, F_SPY))) {
//    /* Already stolen from: Diplomat always fails! */
//    count = 1;
//    util.freelog (Log.LOG_DEBUG, "steal-tech: difficulty: impossible");
//  } else {
//    /* Determine difficulty. */
//    count = 1;
//    if (technology < Game.game.num_tech_types) count++;
//    count += pcity.steal;
//    util.freelog (Log.LOG_DEBUG, "steal-tech: difficulty: %d", count);
//    /* Determine success or failure. */
//    while (count > 0) {
//      if (Rand.myrand (100) >= Game.game.diplchance) {
//	break;
//      }
//      count--;
//    }
//  }
//  if (count > 0) {
//    Plrhand.notify_player_ex(pplayer, pcity.tile, E_MY_DIPLOMAT_FAILED,
//		     ("Game: Your %s was caught in the attempt of" +
//		       " stealing technology from %s."),
//		     Unittype_P.unit_name(pdiplomat.type), pcity.name);
//    Plrhand.notify_player_ex(cplayer, pcity.tile, E_ENEMY_DIPLOMAT_FAILED,
//		     "Game: %s's %s failed to steal technology from %s.",
//		     pplayer.name, Unittype_P.unit_name(pdiplomat.type), pcity.name);
//    /* this may cause a diplomatic incident */
//    maybe_cause_incident(DIPLOMAT_STEAL, pplayer, null, pcity);
//    Unittools.wipe_unit(pdiplomat);
//    return;
//  }
//
//  util.freelog (Log.LOG_DEBUG, "steal-tech: succeeded");
//
//  /* Examine the civilization for technologies to steal. */
//  count = 0;
//  tech_type_iterate(index) {
//    if (get_invention(pplayer, index) != TECH_KNOWN
//	&& get_invention(cplayer, index) == TECH_KNOWN
//	&& tech_is_available(pplayer, index)) {
//      count++;
//    }
//  } tech_type_iterate_end;
//
//  util.freelog (Log.LOG_DEBUG, "steal-tech: count of technologies: %d", count);
//
//  /* Determine the target (-1 is future tech). */
//  if (count == 0) {
//    /*
//     * Either only future-tech or nothing to steal:
//     * If nothing to steal, say so, deduct movement cost and return.
//     */
//    if (cplayer.future_tech > pplayer.future_tech) {
//      target = -1;
//      util.freelog (Log.LOG_DEBUG, "steal-tech: targeted future-tech: %d", target);
//    } else {
//      Plrhand.notify_player_ex(pplayer, pcity.tile, E_MY_DIPLOMAT_FAILED,
//		       "Game: No new technology found in %s.",
//		       pcity.name);
//      diplomat_charge_movement (pdiplomat, pcity.tile);
//      Unittools.send_unit_info (pplayer, pdiplomat);
//      util.freelog (Log.LOG_DEBUG, "steal-tech: nothing to steal");
//      return;
//    }
//  } else if (technology >= Game.game.num_tech_types) {
//    /* Pick random technology to steal. */
//    target = -1;
//    which = Rand.myrand (count);
//    tech_type_iterate(index) {
//      if (get_invention(pplayer, index) != TECH_KNOWN
//	  && get_invention(cplayer, index) == TECH_KNOWN
//	  && tech_is_available(pplayer, index)) {
//	if (which > 0) {
//	  which--;
//	} else {
//	  target = index;
//	  break;
//	}
//      }
//    } tech_type_iterate_end;
//    util.freelog(Log.LOG_DEBUG, "steal-tech: random: targeted technology: %d (%s)",
//	    target, get_tech_name(pplayer, target));
//  } else {
//    /*
//     * Told which technology to steal:
//     * If not available, say so, deduct movement cost and return.
//     */
//    if ((get_invention (pplayer, technology) != TECH_KNOWN) &&
//	(get_invention (cplayer, technology) == TECH_KNOWN)) {
//	target = technology;
//	util.freelog(Log.LOG_DEBUG, "steal-tech: specified target technology: %d (%s)",
//		target, get_tech_name(pplayer, target));
//    } else {
//      Plrhand.notify_player_ex(pplayer, pcity.tile, E_MY_DIPLOMAT_FAILED,
//		       ("Game: Your %s could not find the %s technology" +
//			 " to steal in %s."),
//		       Unittype_P.unit_name(pdiplomat.type),
//		       get_tech_name(pplayer, technology), pcity.name);
//      diplomat_charge_movement (pdiplomat, pcity.tile);
//      Unittools.send_unit_info (pplayer, pdiplomat);
//      util.freelog(Log.LOG_DEBUG, "steal-tech: target technology not found: %d (%s)",
//	      technology, get_tech_name(pplayer, technology));
//      return;
//    }
//  }
//
//  /* Now, the fun stuff!  Steal some technology! */
//  if (target < 0) {
//    /* Steal a future-tech. */
//
//    /* Do it. */
//    found_new_future_tech(pplayer);
//
//    /* Report it. */
//    Plrhand.notify_player_ex(pplayer, pcity.tile, E_MY_DIPLOMAT_THEFT,
//		     "Game: Your %s stole Future Tech. %d from %s.",
//		     Unittype_P.unit_name(pdiplomat.type),
//		     pplayer.future_tech, cplayer.name);
//    Plrhand.notify_player_ex(cplayer, pcity.tile, E_ENEMY_DIPLOMAT_THEFT,
//		     "Game: Future Tech. %d stolen by %s %s from %s.",
//		     pplayer.future_tech, Nation.get_nation_name(pplayer.nation),
//		     Unittype_P.unit_name(pdiplomat.type), pcity.name);
//    util.freelog (Log.LOG_DEBUG, "steal-tech: stole future-tech %d",
//	     pplayer.future_tech);
//  } else {
//    /* Steal a technology. */
//
//    /* Do it. */
//    do_conquer_cost (pplayer);
//    found_new_tech (pplayer, target, false, true, A_NONE);
//    /* Report it. */
//    Plrhand.notify_player_ex(pplayer, pcity.tile, E_MY_DIPLOMAT_THEFT,
//		     "Game: Your %s stole %s from %s.",
//		     Unittype_P.unit_name(pdiplomat.type),
//		     get_tech_name(pplayer, target), cplayer.name);
//    Plrhand.notify_player_ex(cplayer, pcity.tile, E_ENEMY_DIPLOMAT_THEFT,
//		     "Game: %s's %s stole %s from %s.",
//		     pplayer.name, Unittype_P.unit_name(pdiplomat.type),
//		     get_tech_name(cplayer, target), pcity.name);
//    notify_embassies(pplayer, cplayer,
//		     "Game: The %s have stolen %s from the %s.",
//		     Nation.Nation.get_nation_name_plural(pplayer.nation),
//		     get_tech_name(cplayer, target),
//		     Nation.Nation.get_nation_name_plural(cplayer.nation));
//    util.freelog(Log.LOG_DEBUG, "steal-tech: stole %s",
//	    get_tech_name(cplayer, target));
//  }
//
//  Gamelog.gamelog(GAMELOG_TECH, pplayer, cplayer, target, "steal");
//
//  /* Update stealing player's science progress and research fields */
//  Plrhand.send_player_info(pplayer, pplayer);
//
//  /* Record the theft. */
//  (pcity.steal)++;
//
//  /* this may cause a diplomatic incident */
//  maybe_cause_incident(DIPLOMAT_STEAL, pplayer, null, pcity);
//
//  /* Check if a spy survives her mission. Diplomats never do. */
//  diplomat_escape(pplayer, pdiplomat, pcity);
//}
//
///**************************************************************************
//  Incite a city to disaffect.
//
//  - Either a Diplomat or Spy can incite a city to disaffect.
//
//  - Can't incite a city to disaffect if:
//    - Owner runs an unbribable government (e.g., democracy).
//    - Player doesn't have enough gold.
//    - You are allied with the city owner.
//  - Check for infiltration success.  Our provocateur may not survive this.
//  - Check for basic success.  Again, our provocateur may not survive this.
//  - Otherwise, the city will disaffect:
//    - Player gets the city.
//    - Player gets certain of the city's present/supported units.
//    - Player gets a technology advance, if any were unknown.
//
//  - The provocateur may be captured and executed, or escape to its home town.
//**************************************************************************/
//void diplomat_incite(player pplayer, unit pdiplomat,
//		     city pcity)
//{
//  player cplayer;
//  int revolt_cost;
//
//  /* Fetch target civilization's player.  Sanity checks. */
//  if (!pcity)
//    return;
//  cplayer = City.city_owner (pcity);
//  if (!cplayer || Player_P.pplayers_allied(cplayer, pplayer))
//    return;
//
//  util.freelog (Log.LOG_DEBUG, "incite: unit: %d", pdiplomat.id);
//
//  /* Check for city from a bribable government. */
//  if (Government.government_has_flag (Government.get_gov_pcity (pcity), G_UNBRIBABLE)) {
//    Plrhand.notify_player_ex(pplayer, pcity.tile, E_MY_DIPLOMAT_FAILED,
//		     "Game: You can't subvert a city from this nation.");
//    util.freelog (Log.LOG_DEBUG, "incite: city's government is unbribable");
//    return;
//  }
//
//  /* See if the city is subvertable. */
//  if (Effects.get_city_bonus(pcity, EFT_NO_INCITE) > 0) {
//    Plrhand.notify_player_ex(pplayer, pcity.tile, E_MY_DIPLOMAT_FAILED,
//		     "Game: You can't subvert this city.");
//    util.freelog (Log.LOG_DEBUG, "incite: city is protected");
//    return;
//  }
//
//  /* Get incite cost. */
//  revolt_cost = Cityturn.city_incite_cost(pplayer, pcity);
//
//  /* If player doesn't have enough gold, can't incite a revolt. */
//  if (pplayer.economic.gold < revolt_cost) {
//    Plrhand.notify_player_ex(pplayer, pcity.tile, E_MY_DIPLOMAT_FAILED,
//		     ("Game: You don't have enough gold to" +
//		       " subvert %s."), pcity.name);
//    util.freelog(Log.LOG_DEBUG, "incite: not enough gold");
//    return;
//  }
//
//  /* Check if the Diplomat/Spy succeeds against defending Diplomats/Spies. */
//  if (!diplomat_infiltrate_tile(pplayer, cplayer, pdiplomat, 
//                                pcity.tile)) {
//    return;
//  }
//
//  util.freelog (Log.LOG_DEBUG, "incite: infiltrated");
//
//  /* Check if the Diplomat/Spy succeeds with his/her task. */
//  if (Rand.myrand (100) >= Game.game.diplchance) {
//    Plrhand.notify_player_ex(pplayer, pcity.tile, E_MY_DIPLOMAT_FAILED,
//		     ("Game: Your %s was caught in the attempt" +
//		       " of inciting a revolt!"),
//		     Unittype_P.unit_name(pdiplomat.type));
//    Plrhand.notify_player_ex(cplayer, pcity.tile, E_ENEMY_DIPLOMAT_FAILED,
//		     ("Game: You caught %s %s attempting" +
//		       " to incite a revolt in %s!"),
//		     Nation.get_nation_name(pplayer.nation),
//		     Unittype_P.unit_name(pdiplomat.type), pcity.name);
//    Unittools.wipe_unit(pdiplomat);
//    return;
//  }
//
//  util.freelog (Log.LOG_DEBUG, "incite: succeeded");
//
//  /* Subvert the city to your cause... */
//
//  /* City loses some population. */
//  if (pcity.size > 1) {
//    Cityturn.city_reduce_size(pcity, 1);
//  }
//
//  /* This costs! */
//  pplayer.economic.gold -= revolt_cost;
//
//  /* Notify everybody involved. */
//  Gamelog.gamelog(EGamelog.GAMELOG_LOSECITY, cplayer, pplayer, pcity, "incited to revolt");
//
//  Plrhand.notify_player_ex(pplayer, pcity.tile, E_MY_DIPLOMAT_INCITE,
//		   "Game: Revolt incited in %s, you now rule the city!",
//		   pcity.name);
//  Plrhand.notify_player_ex(cplayer, pcity.tile, E_ENEMY_DIPLOMAT_INCITE,
//		   "Game: %s has revolted, %s influence suspected.",
//		   pcity.name, Nation.get_nation_name(pplayer.nation));
//
//  pcity.shield_stock = 0;
//  Cityturn.nullify_prechange_production(pcity);
//
//  /* You get a technology advance, too! */
//  Plrhand.get_a_tech (pplayer, cplayer);
//
//  /* this may cause a diplomatic incident */
//  maybe_cause_incident(DIPLOMAT_INCITE, pplayer, null, pcity);
//
//  /* Transfer city and units supported by this city (that
//     are within one square of the city) to the new owner.
//     Remember that pcity is destroyed as part of the transfer,
//     Which is why we do this last */
//  transfer_city (pplayer, pcity, 1, true, true, false);
//
//  /* Check if a spy survives her mission. Diplomats never do.
//   * _After_ transferring the city, or the city area is first fogged
//   * when the diplomat is removed, and then unfogged when the city
//   * is transferred. */
//  diplomat_escape(pplayer, pdiplomat, pcity);
//
//  /* Update the players gold in the client */
//  Plrhand.send_player_info(pplayer, pplayer);
//}  
//
///**************************************************************************
//  Sabotage enemy city's improvement or production.
//  If "improvement" is Improvement.B_LAST, sabotage a random improvement or production.
//  Else, if "improvement" is -1, sabotage current production.
//  Otherwise, sabotage the city improvement whose ID is "improvement".
//  (Note: Only Spies can select what to sabotage.)
//
//  - Either a Diplomat or Spy can sabotage an enemy city.
//  - The players must be at war
//
//  - Check for infiltration success.  Our saboteur may not survive this.
//  - Check for basic success.  Again, our saboteur may not survive this.
//  - Determine target, given arguments and finalraints.
//  - If specified, city walls and anything in a capital are 50% likely to fail.
//  - Do sabotage!
//
//  - The saboteur may be captured and executed, or escape to its home town.
//**************************************************************************/
//void diplomat_sabotage(player pplayer, unit pdiplomat,
//		       city pcity, int improvement)
//{
//  player cplayer;
//  int count, which, target;
//  final String prod;
//  /* Twice as difficult if target is specified. */
//  int success_prob = (improvement >= Improvement.B_LAST ? Game.game.diplchance 
//                      : Game.game.diplchance / 2); 
//
//  /* Fetch target city's player.  Sanity checks. */
//  if (!pcity)
//    return;
//  cplayer = City.city_owner (pcity);
//  if (!cplayer || !Player_P.pplayers_at_war(pplayer, cplayer))
//    return;
//
//  util.freelog (Log.LOG_DEBUG, "sabotage: unit: %d", pdiplomat.id);
//
//  /* If not a Spy, do something random. */
//  if (!unit_flag (pdiplomat, F_SPY))
//    improvement = Improvement.B_LAST;
//
//  /* Check if the Diplomat/Spy succeeds against defending Diplomats/Spies. */
//  if (!diplomat_infiltrate_tile(pplayer, cplayer, pdiplomat, 
//                                pcity.tile)) {
//    return;
//  }
//
//  util.freelog (Log.LOG_DEBUG, "sabotage: infiltrated");
//
//  /* Check if the Diplomat/Spy succeeds with his/her task. */
//  if (Rand.myrand (100) >= success_prob) {
//    Plrhand.notify_player_ex(pplayer, pcity.tile, E_MY_DIPLOMAT_FAILED,
//		     ("Game: Your %s was caught in the attempt" +
//		       " of industrial sabotage!"),
//		     Unittype_P.unit_name(pdiplomat.type));
//    Plrhand.notify_player_ex(cplayer, pcity.tile, E_ENEMY_DIPLOMAT_SABOTAGE,
//		     ("Game: You caught %s %s attempting" +
//		       " sabotage in %s!"),
//		     Nation.get_nation_name(pplayer.nation),
//		     Unittype_P.unit_name(pdiplomat.type), pcity.name);
//    Unittools.wipe_unit(pdiplomat);
//    return;
//  }
//
//  util.freelog (Log.LOG_DEBUG, "sabotage: succeeded");
//
//  /* Examine the city for improvements to sabotage. */
//  count = 0;
//	for (int impr = 0; impr < Game.game.num_impr_types; impr++) {
//		if((pcity).improvements[impr] == Improvement.I_NONE) {
//			continue;
//		}
//    if (get_improvement_type(impr).sabotage > 0) {
//      count++;
//    }
//  } ;
//
//  util.freelog (Log.LOG_DEBUG, "sabotage: count of improvements: %d", count);
//
//  /* Determine the target (-1 is production). */
//  if (improvement < 0) {
//    /* If told to sabotage production, do so. */
//    target = -1;
//    util.freelog (Log.LOG_DEBUG, "sabotage: specified target production: %d", target);
//  } else if (improvement >= Improvement.B_LAST) {
//    /*
//     * Pick random:
//     * 50/50 chance to pick production or some improvement.
//     * Won't pick something that doesn't exit.
//     * If nothing to do, say so, deduct movement cost and return.
//     */
//    if (count == 0 && pcity.shield_stock == 0) {
//      Plrhand.notify_player_ex(pplayer, pcity.tile, E_MY_DIPLOMAT_FAILED,
//		       ("Game: Your %s could not find anything to" +
//			 " sabotage in %s."), Unittype_P.unit_name(pdiplomat.type),
//		       pcity.name);
//      diplomat_charge_movement (pdiplomat, pcity.tile);
//      Unittools.send_unit_info (pplayer, pdiplomat);
//      util.freelog (Log.LOG_DEBUG, "sabotage: random: nothing to do");
//      return;
//    }
//    if (count == 0 || Rand.myrand (2) == 1) {
//      target = -1;
//      util.freelog (Log.LOG_DEBUG, "sabotage: random: targeted production: %d", target);
//    } else {
//      target = -1;
//      which = Rand.myrand (count);
//
//	for (int impr = 0; impr < Game.game.num_impr_types; impr++) {
//		if((pcity).improvements[impr] == Improvement.I_NONE) {
//			continue;
//		}
//	if (get_improvement_type(impr).sabotage > 0) {
//	  if (which > 0) {
//	    which--;
//	  } else {
//	    target = index;
//	    break;
//	  }
//	}
//      } ;
//
//      util.freelog (Log.LOG_DEBUG, "sabotage: random: targeted improvement: %d (%s)",
//	       target, Improvement.get_improvement_name (target));
//    }
//  } else {
//    /*
//     * Told which improvement to pick:
//     * If try for wonder or palace, complain, deduct movement cost and return.
//     * If not available, say so, deduct movement cost and return.
//     */
//    if (City.city_got_building (pcity, improvement)) {
//      if (get_improvement_type(improvement).sabotage > 0) {
//	target = improvement;
//	util.freelog (Log.LOG_DEBUG, "sabotage: specified target improvement: %d (%s)",
//	       target, Improvement.get_improvement_name (target));
//      } else {
//	Plrhand.notify_player_ex(pplayer, pcity.tile, E_MY_DIPLOMAT_FAILED,
//			 "Game: You cannot sabotage a %s!",
//			 Improvement.improvement_types[improvement].name);
//	diplomat_charge_movement (pdiplomat, pcity.tile);
//	Unittools.send_unit_info (pplayer, pdiplomat);
//	util.freelog (Log.LOG_DEBUG, "sabotage: disallowed target improvement: %d (%s)",
//	       improvement, Improvement.get_improvement_name (improvement));
//	return;
//      }
//    } else {
//      Plrhand.notify_player_ex(pplayer, pcity.tile, E_MY_DIPLOMAT_FAILED,
//		       ("Game: Your %s could not find the %s to" +
//			 " sabotage in %s."), Unittype_P.unit_name(pdiplomat.type),
//		       Improvement.get_improvement_name(improvement), pcity.name);
//      diplomat_charge_movement (pdiplomat, pcity.tile);
//      Unittools.send_unit_info (pplayer, pdiplomat);
//      util.freelog (Log.LOG_DEBUG, "sabotage: target improvement not found: %d (%s)",
//	       improvement, Improvement.get_improvement_name (improvement));
//      return;
//    }
//  }
//
//  /* Now, the fun stuff!  Do the sabotage! */
//  if (target < 0) {
//    /* Sabotage current production. */
//
//    /* Do it. */
//    pcity.shield_stock = 0;
//    Cityturn.nullify_prechange_production(pcity); /* Make it impossible to recover */
//
//    /* Report it. */
//    if (pcity.is_building_unit)
//      prod = Unittype_P.unit_name (pcity.currently_building);
//    else
//      prod = Improvement.get_improvement_name (pcity.currently_building);
//    Plrhand.notify_player_ex(pplayer, pcity.tile, E_MY_DIPLOMAT_SABOTAGE,
//		     ("Game: Your %s succeeded in destroying" +
//		       " the production of %s in %s."),
//		     Unittype_P.unit_name(pdiplomat.type), prod, pcity.name);
//    Plrhand.notify_player_ex(cplayer, pcity.tile, E_ENEMY_DIPLOMAT_SABOTAGE,
//		     ("Game: The production of %s was destroyed in %s," +
//		       " %s are suspected."), prod, pcity.name,
//		     Nation.Nation.get_nation_name_plural(pplayer.nation));
//    util.freelog (Log.LOG_DEBUG, "sabotage: sabotaged production");
//  } else {
//    int vulnerability;
//
//    /* Sabotage a city improvement. */
//
//    /*
//     * One last chance to get caught:
//     * If target was specified, and it is in the capital or are
//     * City Walls, then there is a 50% chance of getting caught.
//     */
//    vulnerability = get_improvement_type(target).sabotage;
//
//    vulnerability -= (vulnerability
//		      * Effects.get_city_bonus(pcity, EFT_SPY_RESISTANT) / 100);
//    if (Rand.myrand(100) >= vulnerability) {
//      /* Caught! */
//      Plrhand.notify_player_ex(pplayer, pcity.tile, E_MY_DIPLOMAT_FAILED,
//		       ("Game: Your %s was caught in the attempt" +
//			 " of sabotage!"), Unittype_P.unit_name(pdiplomat.type));
//      Plrhand.notify_player_ex(cplayer, pcity.tile,
//		       E_ENEMY_DIPLOMAT_FAILED,
//		       ("Game: You caught %s %s attempting" +
//			 " to sabotage the %s in %s!"),
//		       Nation.get_nation_name(pplayer.nation),
//		       Unittype_P.unit_name(pdiplomat.type),
//		       Improvement.get_improvement_name(target), pcity.name);
//      Unittools.wipe_unit(pdiplomat);
//      util.freelog (Log.LOG_DEBUG, "sabotage: caught in capital or on city walls");
//      return;
//    }
//
//    /* Report it. */
//    Plrhand.notify_player_ex(pplayer, pcity.tile, E_MY_DIPLOMAT_SABOTAGE,
//		     "Game: Your %s destroyed the %s in %s.",
//		     Unittype_P.unit_name(pdiplomat.type),
//		     Improvement.get_improvement_name(target), pcity.name);
//    Plrhand.notify_player_ex(cplayer, pcity.tile, E_ENEMY_DIPLOMAT_SABOTAGE,
//		     "Game: The %s destroyed the %s in %s.",
//		     Nation.Nation.get_nation_name_plural(pplayer.nation),
//		     Improvement.get_improvement_name(target), pcity.name);
//    util.freelog (Log.LOG_DEBUG, "sabotage: sabotaged improvement: %d (%s)",
//	       target, Improvement.get_improvement_name (target));
//
//    /* Do it. */
//    building_lost(pcity, target);
//  }
//
//  /* Update clients. */
//  Citytools.send_city_info(null, pcity);
//
//  /* this may cause a diplomatic incident */
//  maybe_cause_incident(DIPLOMAT_SABOTAGE, pplayer, null, pcity);
//
//  /* Check if a spy survives her mission. Diplomats never do. */
//  diplomat_escape(pplayer, pdiplomat, pcity);
//}
//
///**************************************************************************
//  This subtracts the destination movement cost from a diplomat/spy.
//**************************************************************************/
//static void diplomat_charge_movement (unit pdiplomat, tile ptile)
//{
//  pdiplomat.moves_left -=
//    map_move_cost (pdiplomat, ptile);
//  if (pdiplomat.moves_left < 0) {
//    pdiplomat.moves_left = 0;
//  }
//}
//
///**************************************************************************
//  This determines if a diplomat/spy succeeds against some defender,
//  who is also a diplomat or spy.
//  (Note: This is weird in order to try to conform to Civ2 rules.)
//
//  - Depends entirely upon Game.game.diplchance and the defender:
//    - Spies are much better.
//    - Veterans are somewhat better.
//
//  - Return true if the "attacker" succeeds.
//**************************************************************************/
//static boolean diplomat_success_vs_defender (unit pattacker, 
//	unit pdefender, tile pdefender_tile)
//{
//  int att = Game.game.diplchance;
//  int def = 100 - Game.game.diplchance;
//
//  if (unit_flag(pdefender, F_SUPERSPY)) {
//    return true;
//  }
//  if (unit_flag (pattacker, F_SPY)) {
//    att *= 2;
//  }
//  if (unit_flag (pdefender, F_SPY)) {
//    def *= 2;
//  }
//
//  att += (att/5.0) * pattacker.veteran;
//  def += (def/5.0) * pdefender.veteran;
//
//  if (pdefender_tile.city) {
//    def = def * (100 + Effects.get_city_bonus(pdefender_tile.city,
//				      EFT_SPY_RESISTANT)) / 100;
//  } else {
//    if (Map.tile_has_special(pdefender_tile, Terrain_H.S_FORTRESS)
//       || Map.tile_has_special(pdefender_tile, Terrain_H.S_AIRBASE)) {
//	def = (def * 5) / 4;/* +25% */ 
//    }
//  }
//  
//  return Rand.myrand(att) > Rand.myrand(def);
//}
//
///**************************************************************************
//  This determines if a diplomat/spy succeeds in infiltrating a tile.
//
//  - The infiltrator must go up against each defender.
//  - One or the other is eliminated in each contest.
//
//  - Return true if the infiltrator succeeds.
//**************************************************************************/
//static boolean diplomat_infiltrate_tile(player pplayer, 
//                                     player cplayer,
//			             unit pdiplomat,
//				     tile ptile)
//{
//  city pcity = ptile.city;
//
//  /* We don't need a _safe iterate since no transporters should be
//   * destroyed. */
//  for (unit punit : ptile.units.data) {
//    if (unit_flag(punit, Eunit_flag_id.F_DIPLOMAT) || unit_flag(punit, F_SUPERSPY)) {
//      /* A F_SUPERSPY unit may not acutally be a spy, but a superboss which 
//         we cannot allow puny diplomats from getting the better of. Note that 
//         diplomat_success_vs_defender(punit) is always true if the attacker
//         is F_SUPERSPY. Hence F_SUPERSPY vs F_SUPERSPY in a diplomatic contest
//         always kills the attacker. */
//      if (diplomat_success_vs_defender(pdiplomat, punit, ptile) 
//          && !unit_flag(punit, F_SUPERSPY)) {
//	/* Defending Spy/Diplomat util.dies. */
//
//	Plrhand.notify_player_ex(cplayer, ptile, E_MY_DIPLOMAT_FAILED,
//			 ("Game: Your %s has been eliminated defending %s" +
//			   " against a %s."), Unittype_P.unit_name(punit.type),
//		(pcity ? pcity.name : ""), Unittype_P.unit_name(pdiplomat.type));
//	Plrhand.notify_player_ex(pplayer, ptile, E_ENEMY_DIPLOMAT_FAILED,
//		 "Game: An enemy %s has been eliminated defending %s.",
//		Unittype_P.unit_name(punit.type), (pcity ? pcity.name : ""));
//
//	Unittools.wipe_unit(punit);
//        pdiplomat.moves_left = MAX(0, pdiplomat.moves_left - Unit_H.SINGLE_MOVE);
//        Unittools.send_unit_info(pplayer, pdiplomat);
//        return false;
//      } else {
//	/* Check to see if defending unit became more experienced */
//	boolean vet = maybe_make_veteran(punit);
//	
//	/* Attacking Spy/Diplomat util.dies. */
//
//	Plrhand.notify_player_ex(pplayer, ptile, E_MY_DIPLOMAT_FAILED,
//			 ("Game: Your %s was eliminated" +
//			   " by a defending %s."),
//			 Unittype_P.unit_name(pdiplomat.type), Unittype_P.unit_name(punit.type));
//	if (vet) {
//	  if (pcity) {
//	    Plrhand.notify_player_ex(cplayer, ptile,
//			     E_ENEMY_DIPLOMAT_FAILED,
//			     ("Game: Eliminated %s %s while infiltrating " +
//			       "%s. The defender became more experienced."),
//			     Nation.get_nation_name(pplayer.nation),
//			     Unittype_P.unit_name(pdiplomat.type), pcity.name);
//	  } else {
//	    Plrhand.notify_player_ex(cplayer, ptile,
//			     E_ENEMY_DIPLOMAT_FAILED,
//			     ("Game: Eliminated %s %s while infiltrating " +
//			       "our troops. The defender became more " +
//			       "experienced."),
//			     Nation.get_nation_name(pplayer.nation),
//			     Unittype_P.unit_name(pdiplomat.type));
//          }
//        } else {
//	  if (pcity) {
//	    Plrhand.notify_player_ex(cplayer, ptile,
//			     E_ENEMY_DIPLOMAT_FAILED,
//			     ("Game: Eliminated %s %s while infiltrating " +
//			       "%s."), Nation.get_nation_name(pplayer.nation),
//			     Unittype_P.unit_name(pdiplomat.type), pcity.name);
//	  } else {
//	    Plrhand.notify_player_ex(cplayer, ptile,
//			     E_ENEMY_DIPLOMAT_FAILED,
//			     ("Game: Eliminated %s %s while infiltrating " +
//			       "our troops."),
//			     Nation.get_nation_name(pplayer.nation),
//			     Unittype_P.unit_name(pdiplomat.type));
//	  }
//	}
//	Unittools.wipe_unit(pdiplomat);
//	return false;
//      }
//    }
//  } }
//
//  return true;
//}
//
///**************************************************************************
//  This determines if a diplomat/spy survives and escapes.
//  If "pcity" is null, assume action was in the field.
//
//  Spies have a Game.game.diplchance specified chance of survival (better 
//  if veteran):
//    - Diplomats always util.die.
//    - Escapes to home city.
//    - Escapee may become a veteran.
//**************************************************************************/
//static void diplomat_escape(player pplayer, unit pdiplomat,
//                            final city pcity)
//{
//  tile ptile;
//  int escapechance;
//  boolean vet;
//  city spyhome;
//
//  escapechance = Game.game.diplchance + pdiplomat.veteran * 5;
//  
//  if (pcity) {
//    ptile = pcity.tile;
//  } else {
//    ptile = pdiplomat.tile;
//  }
//  
//  /* find closest city for escape target */
//  spyhome = find_closest_owned_city(pdiplomat.unit_owner(), ptile, false, null);
//
//  if (spyhome
//      && unit_flag(pdiplomat, F_SPY)
//      && (Rand.myrand (100) < escapechance || unit_flag(pdiplomat, F_SUPERSPY))) {
//    /* Attacking Spy/Diplomat survives. */
//
//    /* may become a veteran */
//    vet = maybe_make_veteran(pdiplomat);
//    if (vet) {
//      Plrhand.notify_player_ex(pplayer, ptile, E_MY_DIPLOMAT_ESCAPE,
//		       ("Game: Your %s has successfully completed" +
//			 " her mission and returned unharmed to %s" +
//			 " and has become more experienced."),
//		       Unittype_P.unit_name(pdiplomat.type), spyhome.name);
//    } else {
//      Plrhand.notify_player_ex(pplayer, ptile, E_MY_DIPLOMAT_ESCAPE,
//		       ("Game: Your %s has successfully completed" +
//			 " her mission and returned unharmed to %s."),
//		       Unittype_P.unit_name(pdiplomat.type), spyhome.name);
//    }
//
//    /* being teleported costs all movement */
//    if (!teleport_unit_to_city (pdiplomat, spyhome, -1, false)) {
//      Unittools.send_unit_info (pplayer, pdiplomat);
//      util.freelog(Log.LOG_ERROR, "Bug in diplomat_escape: Spy can't teleport.");
//      return;
//    }
//
//    return;
//  } else {
//    if (pcity) {
//      Plrhand.notify_player_ex(pplayer, ptile, E_MY_DIPLOMAT_FAILED,
//			 ("Game: Your %s was captured after completing" +
//			   " her mission in %s."),
//			 Unittype_P.unit_name(pdiplomat.type), pcity.name);
//    } else {
//      Plrhand.notify_player_ex(pplayer, ptile, E_MY_DIPLOMAT_FAILED,
//			 ("Game: Your %s was captured after completing" +
//			   " her mission."), Unittype_P.unit_name(pdiplomat.type));
//    }
//  }
//
//  Unittools.wipe_unit(pdiplomat);
//}
//
///**************************************************************************
//...
//**************************************************************************/
//static void maybe_cause_incident(enum diplomat_actions action, player offender,
// 				 unit victim_unit, city victim_city)
//{
//  player victim_player = 0;
//  tile victim_tile = null;
//
//  if (victim_city) {
//    victim_tile = victim_city.tile;
//    victim_player = City.city_owner(victim_city);
//  } else if (victim_unit) {
//    victim_tile = victim_unit.tile;
//    victim_player = victim_unit.unit_owner();
//  } else {
//    util.die("No victim in call to maybe_cause_incident()");
//  }
//
//  if (!Player_P.pplayers_at_war(offender, victim_player) &&
//      (Rand.myrand(GAME_MAX_REPUTATION) - offender.reputation >
//       GAME_MAX_REPUTATION/2 - victim_player.reputation)) {
//    enum diplstate_type ds = pplayer_get_diplstate(offender, victim_player).type;
//    int punishment = 0;
//    switch (action) {
//    case DIPLOMAT_BRIBE:
//      Plrhand.notify_player_ex(offender, victim_tile, E_DIPLOMATIC_INCIDENT,
//		       ("Game: You have caused an incident while bribing " +
// 			 "%s's %s."),
// 		       victim_player.name,
// 		       Unittype_P.unit_name(victim_unit.type));
//      Plrhand.notify_player_ex(victim_player, victim_tile, E_DIPLOMATIC_INCIDENT,
//		       ("Game: %s has caused an incident while bribing " +
// 			 "your %s."),
// 		       offender.name,
// 		       Unittype_P.unit_name(victim_unit.type));
//      break;
//    case DIPLOMAT_STEAL:
//      Plrhand.notify_player_ex(offender, victim_tile, E_DIPLOMATIC_INCIDENT,
// 		       ("Game: You have caused an incident while stealing " +
// 			 "tech from %s."),
// 		       victim_player.name);
//      Plrhand.notify_player_ex(victim_player, victim_tile, E_DIPLOMATIC_INCIDENT,
//		       ("Game: %s has caused an incident while stealing " +
//			 "tech from you."),
//		       offender.name);
//      break;
//    case DIPLOMAT_INCITE:
//      Plrhand.notify_player_ex(offender, victim_tile, E_DIPLOMATIC_INCIDENT,
// 		       ("Game: You have caused an incident while inciting a " +
// 			 "revolt in %s."), victim_city.name);
//      Plrhand.notify_player_ex(victim_player, victim_tile, E_DIPLOMATIC_INCIDENT,
// 		       ("Game: %s have caused an incident while inciting a " +
// 			 "revolt in %s."), offender.name, victim_city.name);
//      break;
//    case DIPLOMAT_MOVE:
//    case DIPLOMAT_EMBASSY:
//    case DIPLOMAT_INVESTIGATE:
//    case SPY_GET_SABOTAGE_LIST:
//      return; /* These are not considered offences */
//    case DIPLOMAT_ANY_ACTION:
//    case SPY_POISON:
//    case SPY_SABOTAGE_UNIT:
//    case DIPLOMAT_SABOTAGE:
//      /* You can only do these when you are at war, so we should never
// 	 get inside this "if" */
//      util.die("Bug in maybe_cause_incident()");
//    }
//    switch (ds) {
//    case diplstate_type.DS_WAR:
//    case diplstate_type.DS_NO_CONTACT:
//      util.freelog(Log.LOG_VERBOSE,"Trying to cause an incident between players at war");
//      punishment = 0;
//      break;
//    case DS_NEUTRAL:
//      punishment = GAME_MAX_REPUTATION/20;
//      break;
//    case DS_CEASEFIRE:
//    case DS_PEACE:
//      punishment = GAME_MAX_REPUTATION/10;
//      break;
//    case diplstate_type.DS_ALLIANCE:
//    case diplstate_type.DS_TEAM:
//      punishment = GAME_MAX_REPUTATION/5;
//      break;
//    case DS_LAST:
//      assert(false);
//      break;
//    }
//    offender.reputation = MAX(offender.reputation - punishment, 0);
//    victim_player.diplstates[offender.player_no].has_reason_to_cancel = 2;
//    /* FIXME: Maybe we should try to cause a revolution is the offender's
//       government has a senate */
//    Plrhand.send_player_info(offender, null);
//    Plrhand.send_player_info(victim_player, null);
//  }
//
//  return;
//}
//
//
///**************************************************************************
// calculate how expensive it is to bribe the unit
// depends on distance to the capital, and government form
// settlers are half price
//
// Plus, the damage to the unit reduces the price.
//**************************************************************************/
//int unit_bribe_cost(unit punit)
//{  
//  government g = get_gov_pplayer(punit.unit_owner());
//  int cost;
//  city capital;
//  int dist;
//  int default_hp = punit.unit_type().hp;
//
//  cost = punit.unit_owner().economic.gold + 750;
//  capital = find_palace(punit.unit_owner());
//  if (capital) {
//    int tmp = map_distance(capital.tile, punit.tile);
//    dist=Math.min(32, tmp);
//  }
//  else
//    dist=32;
//  if (g.fixed_corruption_distance != 0)
//    dist = Math.min(g.fixed_corruption_distance, dist);
//  cost /= dist + 2;
//
//  cost *= Unittype_P.unit_build_shield_cost(punit.type) / 10;
//
//  /* FIXME: This is a weird one - should be replaced */
//  if (unit_flag(punit, Eunit_flag_id.F_CITIES)) 
//    cost/=2;
//
//  /* Cost now contains the basic bribe cost.  We now reduce it by:
//
//     cost = basecost/2 + basecost/2 * damage/hp
//     
//   */
//  
//  cost = (int)((float)cost/(float)2 + ((float)punit.hp/(float)default_hp) * ((float)cost/(float)2));
//  
//  return cost;
//}
//
///**************************************************************************
// return number of diplomats on this square.  AJS 20000130
//**************************************************************************/
//int count_diplomats_on_tile(tile ptile)
//{
//  int count = 0;
//
//  unit_list_iterate((ptile).units, punit)
//    if (unit_flag(punit, Eunit_flag_id.F_DIPLOMAT))
//      count++;
//  }
//  return count;
//}
}