package server;
import utility.Rand;
import utility.Speclists;

import common.Connection;
import common.Game;
import common.map.tile;
import common.player.player;
import common.unit.unit;
import common.unittype.unittype;

public class Unittools{
//#include "city.h"
//#include "combat.h"
//#include "events.h"
//#include "fcintl.h"
//#include "government.h"
//#include "idex.h"
//#include "log.h"
//#include "Map.map.h"
//#include "mem.h"
//#include "packets.h"
//#include "player.h"
//#include "rand.h"
//#include "shared.h"
//#include "support.h"
//#include "unit.h"
//
//#include "barbarian.h"
//#include "citytools.h"
//#include "cityturn.h"
//#include "diplhand.h"
//#include "Gamelog.gamelog.h"
//#include "gotohand.h"
//#include "maphand.h"
//#include "plrhand.h"
//#include "sernet.h"
//#include "settlers.h"
//#include "srv_main.h"
//#include "unithand.h"
//#include "gamehand.h"
//
//#include "aiexplorer.h"
//#include "aitools.h"
//#include "aiunit.h"
//
//#include "unittools.h"
//
//
//static void unit_restore_hitpoints(player pplayer, unit punit);
//static void unit_restore_movepoints(player pplayer, unit punit);
//static void update_unit_activity(unit punit);
//static void wakeup_neighbor_sentries(unit punit);
//static void do_upgrade_effects(player pplayer);
//
//static void sentry_transported_idle_units(unit ptrans);
//
//static boolean maybe_cancel_patrol_due_to_enemy(unit punit);
//static int hp_gain_coord(unit punit);
//
//static void put_unit_onto_transporter(unit punit, unit ptrans);
//static void pull_unit_from_transporter(unit punit,
//				       unit ptrans);

/**************************************************************************
  returns a unit type with a given role, use -1 if you don't want a tech 
  role. Always try tech role and only if not available, return role unit.
**************************************************************************/
public static int find_a_unit_type(int role, int role_tech)
{
  int which[] = new int [unittype.U_LAST];
  int i, num=0;
//
//  if (role_tech != -1) {
//    for(i=0; i<num_role_units(role_tech); i++) {
//      int iunit = Unittype_P.get_role_unit(role_tech, i);
//      if (Game.game.global_advances[get_unit_type(iunit).tech_requirement] >= 2) {
//	which[num++] = iunit;
//      }
//    }
//  }
//  if(num==0) {
//    for(i=0; i<num_role_units(role); i++) {
//      which[num++] = Unittype_P.get_role_unit(role, i);
//    }
//  }
//  if(num==0) {
//    /* Ruleset code should ensure there is at least one unit for each
//     * possibly-required role, or check before calling this function.
//     */
//    util.die("No unit types in find_a_unit_type(%d,%d)!", role, role_tech);
//  }
  return which[Rand.myrand(num)];
}
//
///**************************************************************************
//  After a battle, after diplomatic aggression and after surviving trireme
//  loss chance, this routine is called to decide whether or not the unit
//  should become more experienced.
//
//  There is a specified chance for it to happen, (+50% if player got SUNTZU)
//  the chances are specified in the units.ruleset file.
//**************************************************************************/
//boolean maybe_make_veteran(unit punit)
//{
//  if (punit.veteran + 1 >= MAX_VET_LEVELS
//      || punit.unit_type().veteran[punit.veteran].name[0] == '\0'
//      || unit_flag(punit, F_NO_VETERAN)) {
//    return false;
//  } else {
//    player plr;
//    int mod = 100;
//
//    plr = get_player(punit.owner);
//
//    if (is_ground_unittype(punit.type)) {
//      mod += get_player_bonus(plr, EFT_LAND_VET_COMBAT);
//    }
//
//    /* The modification is tacked on as a multiplier to the base chance.
//     * For example with a base chance of 50% for green units and a modifier
//     * of +50% the end chance is 75%. */
//    if (Rand.myrand(100) < Game.game.veteran_chance[punit.veteran] * mod / 100) {
//      punit.veteran++;
//      return true;
//    }
//    return false;
//  }
//}
//
///**************************************************************************
//  This is the basic unit versus unit combat routine.
//  1) ALOT of modifiers bonuses etc is added to the 2 units rates.
//  2) If the attack is a bombardment, do rate attacks and don't kill the
//     defender, then return.
//  3) the combat loop, which continues until one of the units are dead
//  4) the aftermath, the loser (and potentially the stack which is below it)
//     is wiped, and the winner gets a chance of gaining veteran status
//**************************************************************************/
//void unit_versus_unit(unit attacker, unit defender,
//		      boolean bombard)
//{
//  int attackpower = get_total_attack_power(attacker,defender);
//  int defensepower = get_total_defense_power(attacker,defender);
//
//  int attack_firepower, defense_firepower;
//  get_modified_firepower(attacker, defender,
//			 &attack_firepower, &defense_firepower);
//
//  util.freelog(Log.LOG_VERBOSE, "attack:%d, defense:%d, attack firepower:%d, defense firepower:%d",
//	  attackpower, defensepower, attack_firepower, defense_firepower);
//
//  if (bombard) {
//    int i;
//    int rate = attacker.unit_type().bombard_rate;
//
//    for (i = 0; i < rate; i++) {
//      if (Rand.myrand(attackpower+defensepower) >= defensepower) {
//	defender.hp -= attack_firepower;
//      }
//    }
//
//    /* Don't kill the target. */
//    if (defender.hp <= 0) {
//      defender.hp = 1;
//    }
//    return;
//  }
//
//  if (attackpower == 0) {
//      attacker.hp=0; 
//  } else if (defensepower == 0) {
//      defender.hp=0;
//  }
//  while (attacker.hp>0 && defender.hp>0) {
//    if (Rand.myrand(attackpower+defensepower) >= defensepower) {
//      defender.hp -= attack_firepower;
//    } else {
//      attacker.hp -= defense_firepower;
//    }
//  }
//  if (attacker.hp<0) attacker.hp = 0;
//  if (defender.hp<0) defender.hp = 0;
//
//  if (attacker.hp > 0)
//    maybe_make_veteran(attacker); 
//  else if (defender.hp > 0)
//    maybe_make_veteran(defender);
//}
//
///***************************************************************************
//  Do unit auto-upgrades to players with the EFT_UNIT_UPGRADE effect
//  (traditionally from Leonardo's Workshop).
//****************************************************************************/
//static void do_upgrade_effects(player pplayer)
//{
//  int upgrades = get_player_bonus(pplayer, EFT_UPGRADE_UNIT);
//  Speclists<unit> candidates;
//
//  if (upgrades <= 0) {
//    return;
//  }
//
//  unit_list_init(&candidates);
//
//  for (unit punit : pplayer.units.data) {
//    /* We have to be careful not to strand units at sea, for example by
//     * upgrading a frigate to an ironclad while it was carrying a unit. */
//    if (test_unit_upgrade(punit, true) == UR_OK) {
//      unit_list_insert(&candidates, punit);	/* Potential candidate :) */
//    }
//  } }
//
//  while (upgrades > 0 && candidates.foo_list_size() > 0) {
//    /* Upgrade one unit.  The unit is chosen at random from the list of
//     * available candidates. */
//    int candidate_to_upgrade = Rand.myrand(candidates.foo_list_size());
//    unit punit = unit_list_get(&candidates, candidate_to_upgrade);
//    int upgrade_type = can_upgrade_unittype(pplayer, punit.type);
//
//    notify_player(pplayer,
//		  "Game: %s was upgraded for free to %s%s.",
//		  punit.unit_type().name,
//		  get_unit_type(upgrade_type).name,
//		  get_location_str_in(pplayer, punit.tile));
//
//    /* For historical reasons we negate the unit's veteran status.  Note that
//     * the upgraded unit may have the NoVeteran flag set. */
//    punit.veteran = 0;
//    assert(test_unit_upgrade(punit, true) == UR_OK);
//    upgrade_unit(punit, upgrade_type, true);
//    unit_list_unlink(&candidates, punit);
//    upgrades--;
//  }
//
//  unit_list_unlink_all(&candidates);
//}
//
///***************************************************************************
//  Pay the cost of supported units of one city
//***************************************************************************/
//void pay_for_units(player pplayer, city pcity)
//{
//  int potential_gold = 0;
//
//  built_impr_iterate(pcity, pimpr) {
//    potential_gold += impr_sell_gold(pimpr);
//  } built_impr_iterate_end;
//
//  for (unit punit : pcity.units_supported.data) {
//
//    if (pplayer.economic.gold + potential_gold < punit.upkeep_gold) {
//      /* We cannot upkeep this unit any longer and selling off city
//       * improvements will not help so we will have to disband */
//      assert(pplayer.economic.gold + potential_gold >= 0);
//      
//      Plrhand.notify_player_ex(pplayer, null, E_UNIT_LOST,
//		       "Not enough gold. %s disbanded",
//		       punit.unit_type().name);
//      wipe_unit(punit);
//    } else {
//      /* Gold can get negative here as city improvements will be sold
//       * afterwards to balance our budget. FIXME: Should units with gold 
//       * upkeep give gold when they are disbanded? */
//      pplayer.economic.gold -= punit.upkeep_gold;
//    }
//  }
//}
//
///***************************************************************************
//  1. Do Leonardo's Workshop upgrade if applicable.
//
//  2. Restore/decrease unit hitpoints.
//
//  3. Kill dead units.
//
//  4. Randomly kill units on unsafe terrain or unsafe-ocean.
//
//  5. Rescue airplanes by returning them to base automatically.
//
//  6. Decrease fuel of planes in the air.
//
//  7. Refuel planes that are in bases.
//
//  8. Kill planes that are out of fuel.
//****************************************************************************/
//void player_restore_units(player pplayer)
//{
//  /* 1) get Leonardo out of the way first: */
//  do_upgrade_effects(pplayer);
//
//  for (unit punit : pplayer.units.data) {
//
//    /* 2) Modify unit hitpoints. Helicopters can even lose them. */
//    unit_restore_hitpoints(pplayer, punit);
//
//    /* 3) Check that unit has hitpoints */
//    if (punit.hp<=0) {
//      /* This should usually only happen for heli units,
//	 but if any other units get 0 hp somehow, catch
//	 them too.  --dwp  */
//      Plrhand.notify_player_ex(pplayer, punit.tile, E_UNIT_LOST, 
//          "Game: Your %s has run out of hit points.", 
//          unit_name(punit.type));
//      Gamelog.gamelog(GAMELOG_UNITLOSS, punit, null, "out of hp");
//      wipe_unit(punit);
//      continue; /* Continue iterating... */
//    }
//
//    /* 4) Check for units on unsafe terrains. */
//    if (unit_flag(punit, F_TRIREME)) {
//      /* Triremes away from coast have a chance of death. */
//      /* Note if a trireme util.died on a TER_UNSAFE terrain, this would
//       * erronously give the high seas message.  This is impossible under
//       * the current rulesets. */
//      int loss_chance = unit_loss_pct(pplayer, punit.tile, punit);
//
//      if (Rand.myrand(100) < loss_chance) {
//        Plrhand.notify_player_ex(pplayer, punit.tile, E_UNIT_LOST, 
//                         "Game: Your %s has been lost on the high seas.",
//                         unit_name(punit.type));
//        Gamelog.gamelog(GAMELOG_UNITLOSS, punit, null, "lost at sea");
//        wipe_unit(punit);
//        continue; /* Continue iterating... */
//      } else if (loss_chance > 0) {
//        if (maybe_make_veteran(punit)) {
//	  Plrhand.notify_player_ex(pplayer, punit.tile, E_UNIT_BECAME_VET,
//                           ("Game: Your %s survived on the high seas " +
//	                   "and became more experienced!"), 
//                           unit_name(punit.type));
//        }
//      }
//    } else if (!(is_air_unit(punit) || is_heli_unit(punit))
//	       && (Rand.myrand(100) < unit_loss_pct(pplayer,
//					       punit.tile, punit))) {
//      /* All units may have a chance of dying if they are on TER_UNSAFE
//       * terrain. */
//      Plrhand.notify_player_ex(pplayer, punit.tile, E_UNIT_LOST,
//		       "Game: Your %s has been lost on unsafe terrain.",
//		       unit_name(punit.type));
//      Gamelog.gamelog(GAMELOG_UNITLOSS, punit, null, "unsafe terrain");
//      wipe_unit(punit);
//      continue;			/* Continue iterating... */
//    }
//
//    /* 5) Rescue planes if needed */
//    if (is_air_unit(punit)) {
//      /* Shall we emergency return home on the last vapors? */
//
//      /* I think this is strongly against the spirit of client goto.
//       * The problem is (again) that here we know too much. -- Zamar */
//
//      if (punit.fuel == 1
//	  && !is_airunit_refuel_point(punit.tile,
//				      punit.unit_owner(), punit.type, true)) {
//	iterate_outward(punit.tile, punit.moves_left/3, itr_tile) {
//	  if (is_airunit_refuel_point(itr_tile, punit.unit_owner(),
//				      punit.type, false)
//	      &&(Gotohand.air_can_move_between(punit.moves_left / 3, punit.tile,
//				      itr_tile, punit.unit_owner()) >= 0)) {
//	    free_unit_orders(punit);
//	    punit.goto_tile = itr_tile;
//	    set_unit_activity(punit, unit_activity.ACTIVITY_GOTO);
//	    () do_unit_goto(punit, goto_move_restriction.GOTO_MOVE_ANY, false);
//	    Plrhand.notify_player_ex(pplayer, punit.tile, event_type.E_NOEVENT, 
//			     "Game: Your %s has returned to refuel.",
//			     unit_name(punit.type));
//	    goto OUT;
//	  }
//	} iterate_outward_end;
//      }
//    OUT:
//
//      /* 6) Update fuel */
//      punit.fuel--;
//
//      /* 7) Automatically refuel air units in cities, airbases, and
//       *    transporters (carriers). */
//      if (map_get_city(punit.tile)
//	  || Map.map_has_special(punit.tile, Terrain_H.S_AIRBASE)
//	  || punit.transported_by != -1) {
//	punit.fuel=punit.unit_type().fuel;
//      }
//    }
//  }
//
//  /* 8) Check if there are air units without fuel */
//  for (unit punit : pplayer.units.data) {
//    if (is_air_unit(punit) && punit.fuel <= 0
//        && punit.unit_type().fuel > 0) {
//      Plrhand.notify_player_ex(pplayer, punit.tile, E_UNIT_LOST, 
//		       "Game: Your %s has run out of fuel.",
//		       unit_name(punit.type));
//      Gamelog.gamelog(GAMELOG_UNITLOSS, punit, null, "fuel");
//      wipe_unit(punit);
//    } 
//  }
//}
//
///****************************************************************************
//  add hitpoints to the unit, hp_gain_coord returns the amount to add
//  united nations will speed up the process by 2 hp's / turn, means helicopters
//  will actually not loose hp's every turn if player have that wonder.
//  Units which have moved don't gain hp, except the United Nations and
//  helicopter effects still occur.
//*****************************************************************************/
//static void unit_restore_hitpoints(player pplayer, unit punit)
//{
//  boolean was_lower;
//
//  was_lower=(punit.hp < punit.unit_type().hp);
//
//  if(!punit.moved) {
//    punit.hp+=hp_gain_coord(punit);
//  }
//
//  /* Bonus recovery HP (traditionally from the United Nations) */
//  punit.hp += get_player_bonus(pplayer, EFT_UNIT_RECOVER);
//
//  if(is_heli_unit(punit)) {
//    city pcity = map_get_city(punit.tile);
//    if(!pcity && !Map.map_has_special(punit.tile, Terrain_H.S_AIRBASE)
//       && punit.transported_by == -1) {
//      punit.hp-=punit.unit_type().hp/10;
//    }
//  }
//
//  if(punit.hp>=punit.unit_type().hp) {
//    punit.hp=punit.unit_type().hp;
//    if(was_lower&&punit.activity==ACTIVITY_SENTRY){
//      set_unit_activity(punit,unit_activity.ACTIVITY_IDLE);
//    }
//  }
//  if(punit.hp<0)
//    punit.hp=0;
//
//  punit.moved = false;
//  punit.paradropped = false;
//}
//  
///***************************************************************************
//  Move points are trivial, only modifiers to the base value is if it's
//  sea units and the player has certain wonders/techs. Then add veteran
//  bonus, if any.
//***************************************************************************/
//static void unit_restore_movepoints(player pplayer, unit punit)
//{
//  punit.moves_left = punit.move_rate();
//  punit.done_moving = false;
//}
//
///**************************************************************************
//  iterate through all units and update them.
//**************************************************************************/
//void update_unit_activities(player pplayer)
//{
//	for(unit punit: pplayer.units){
//    update_unit_activity(punit);
//  }
//}
//
///**************************************************************************
//  returns how many hp's a unit will gain on this square
//  depends on whether or not it's inside city or fortress.
//  barracks will regen landunits completely
//  airports will regen airunits  completely
//  ports    will regen navalunits completely
//  fortify will add a little extra.
//***************************************************************************/
//static int hp_gain_coord(unit punit)
//{
//  int hp;
//  city pcity;
//  if (unit_on_fortress(punit))
//    hp=punit.unit_type().hp/4;
//  else
//    hp=0;
//  if((pcity=map_get_city(punit.tile))) {
//    if ((get_city_bonus(pcity, EFT_LAND_REGEN) > 0
//	 && is_ground_unit(punit))
//	|| (get_city_bonus(pcity, EFT_AIR_REGEN) > 0
//	    && (is_air_unit(punit) || is_heli_unit(punit)))
//	|| (get_city_bonus(pcity, EFT_SEA_REGEN) > 0
//	    && is_sailing_unit(punit))) {
//      hp=punit.unit_type().hp;
//    }
//    else
//      hp=punit.unit_type().hp/3;
//  }
//  else if (!is_heli_unit(punit))
//    hp++;
//
//  if(punit.activity==ACTIVITY_FORTIFIED)
//    hp++;
//  
//  return hp;
//}
//
///**************************************************************************
//  Calculate the total amount of activity performed by all units on a tile
//  for a given task.
//**************************************************************************/
//static int total_activity (tile ptile, enum unit_activity act)
//{
//  int total = 0;
//
//  unit_list_iterate (ptile.units, punit)
//    if (punit.activity == act)
//      total += punit.activity_count;
//  }
//  return total;
//}
//
///**************************************************************************
//  Calculate the total amount of activity performed by all units on a tile
//  for a given task and target.
//**************************************************************************/
//static int total_activity_targeted(tile ptile, enum unit_activity act,
//				   enum int tgt)
//{
//  int total = 0;
//
//  unit_list_iterate (ptile.units, punit)
//    if ((punit.activity == act) && (punit.activity_target == tgt))
//      total += punit.activity_count;
//  }
//  return total;
//}
//
///***************************************************************************
//  Maybe settler/worker gains a veteran level?
//****************************************************************************/
//static boolean maybe_settler_become_veteran(unit punit)
//{
//  if (punit.veteran + 1 >= MAX_VET_LEVELS
//      || punit.unit_type().veteran[punit.veteran].name[0] == '\0'
//      || unit_flag(punit, F_NO_VETERAN)) {
//    return false;
//  }
//  if (unit_flag(punit, F_SETTLERS)
//      && Rand.myrand(100) < Game.game.work_veteran_chance[punit.veteran]) {
//    punit.veteran++;
//    return true;
//  }
//  return false;  
//}
//
///**************************************************************************
//  progress settlers in their current tasks, 
//  and units that is pillaging.
//  also move units that is on a goto.
//  restore unit move points (information needed for settler tasks)
//**************************************************************************/
//static void update_unit_activity(unit punit)
//{
//  player pplayer = punit.unit_owner();
//  int id = punit.id;
//  boolean unit_activity_done = false;
//  enum unit_activity activity = punit.activity;
//  enum ocean_land_change solvency = OLC_NONE;
//  tile ptile = punit.tile;
//  boolean check_adjacent_units = false;
//  
//  if (activity != unit_activity.ACTIVITY_IDLE && activity != ACTIVITY_FORTIFIED
//      && activity != unit_activity.ACTIVITY_GOTO && activity != ACTIVITY_EXPLORE) {
//    /*  We don't need the activity_count for the above */
//    punit.activity_count += get_activity_rate_this_turn(punit);
//
//    /* settler may become veteran when doing something useful */
//    if (activity != ACTIVITY_FORTIFYING && activity != ACTIVITY_SENTRY
//       && maybe_settler_become_veteran(punit)) {
//      Plrhand.notify_player_ex(pplayer, punit.tile, E_UNIT_BECAME_VET,
//	"Game: Your %s became more experienced!", unit_name(punit.type));
//    }
//    
//  }
//
//  unit_restore_movepoints(pplayer, punit);
//
//  if (activity == ACTIVITY_EXPLORE) {
//    boolean more_to_explore = ai_manage_explorer(punit);
//
//    if (!player_find_unit_by_id(pplayer, id)) {
//      /* Died */
//      return;
//    }
//
//    assert(punit.activity == ACTIVITY_EXPLORE); 
//    if (!more_to_explore) {
//      handle_unit_activity_request(punit, unit_activity.ACTIVITY_IDLE);
//    }
//    send_unit_info(null, punit);
//    return;
//  }
//
//  if (activity==ACTIVITY_PILLAGE) {
//    if (punit.activity_target == S_NO_SPECIAL) { /* case for old save files */
//      if (punit.activity_count >= 1) {
//	enum int what =
//	  get_preferred_pillage(
//	       get_tile_infrastructure_set(punit.tile));
//
//	if (what != S_NO_SPECIAL) {
//	  Map.map_clear_special(punit.tile, what);
//	  Maphand.update_tile_knowledge(punit.tile);
//	  set_unit_activity(punit, unit_activity.ACTIVITY_IDLE);
//	  check_adjacent_units = true;
//	}
//
//	/* If a watchtower has been pillaged, reduce sight to normal */
//	if (what == S_FORTRESS) {
//	  for (unit punit2 : punit.tile.units.data) {
//            player owner = punit2.unit_owner();
//            util.freelog(Log.LOG_VERBOSE, "Watchtower pillaged!");
//            if (player_knows_techs_with_flag(owner, TF_WATCHTOWER)) {
//              if (is_ground_unit(punit2)) {
//                /* Unfog (increase seen counter) first, fog (decrease counter)
//                 * later, so tiles that are within vision range both before and
//                 * after are not even temporarily marked fogged. */
//                unfog_area(owner, punit2.tile,
//                           punit2.unit_type().vision_range);
//                fog_area(owner, punit2.tile,
//                         get_watchtower_vision(punit2));
//              }
//            }
//          }
//	  }
//	}
//      }
//    }
//    else if (total_activity_targeted(punit.tile, ACTIVITY_PILLAGE, 
//                                     punit.activity_target) >= 1) {
//      enum int what_pillaged = punit.activity_target;
//
//      Map.map_clear_special(punit.tile, what_pillaged);
//      unit_list_iterate (punit.tile.units, punit2) {
//        if ((punit2.activity == ACTIVITY_PILLAGE) &&
//	    (punit2.activity_target == what_pillaged)) {
//	  set_unit_activity(punit2, unit_activity.ACTIVITY_IDLE);
//	  send_unit_info(null, punit2);
//	}
//      } }
//      Maphand.update_tile_knowledge(punit.tile);
//      
//      /* If a watchtower has been pillaged, reduce sight to normal */
//      if (what_pillaged == S_FORTRESS) {
//	util.freelog(Log.LOG_VERBOSE, "Watchtower(2) pillaged!");
//	for (unit punit2 : punit.tile.units.data) {
//          player owner = punit2.unit_owner();
//          if (player_knows_techs_with_flag(owner, TF_WATCHTOWER)) {
//            if (is_ground_unit(punit2)) {
//              /* Unfog (increase seen counter) first, fog (decrease counter)
//               * later, so tiles that are within vision range both before and
//               * after are not even temporarily marked fogged. */
//              unfog_area(owner, punit2.tile,
//                         punit2.unit_type().vision_range);
//              fog_area(owner, punit2.tile,
//                       get_watchtower_vision(punit2));
//            }
//          }
//        }
//	}
//      }
//    }  
//  }
//
//  if (activity==ACTIVITY_POLLUTION) {
//    if (total_activity (punit.tile, ACTIVITY_POLLUTION)
//	>= map_clean_pollution_time(punit.tile)) {
//      Map.map_clear_special(punit.tile, S_POLLUTION);
//      unit_activity_done = true;
//    }
//  }
//
//  if (activity==ACTIVITY_FALLOUT) {
//    if (total_activity (punit.tile, ACTIVITY_FALLOUT)
//	>= map_clean_fallout_time(punit.tile)) {
//      Map.map_clear_special(punit.tile, S_FALLOUT);
//      unit_activity_done = true;
//    }
//  }
//
//  if (activity==ACTIVITY_FORTRESS) {
//    if (total_activity (punit.tile, ACTIVITY_FORTRESS)
//	>= map_build_fortress_time(punit.tile)) {
//      map_set_special(punit.tile, S_FORTRESS);
//      unit_activity_done = true;
//      /* watchtower becomes effective */
//      for (unit punit : ptile.units.data) {
//        player owner = punit.unit_owner();
//        if (player_knows_techs_with_flag(owner, TF_WATCHTOWER)) {
//	  if (is_ground_unit(punit)) {
//            /* Unfog (increase seen counter) first, fog (decrease counter)
//             * later, so tiles that are within vision range both before and
//             * after are not even temporarily marked fogged. */
//	    unfog_area(owner, punit.tile,
//		       get_watchtower_vision(punit));
//	    fog_area(owner, punit.tile,
//		     punit.unit_type().vision_range);
//	  }
//	}
//      }
//      }
//    }
//  }
//
//  if (activity==ACTIVITY_AIRBASE) {
//    if (total_activity (punit.tile, ACTIVITY_AIRBASE)
//	>= map_build_airbase_time(punit.tile)) {
//      map_set_special(punit.tile, Terrain_H.S_AIRBASE);
//      unit_activity_done = true;
//    }
//  }
//  
//  if (activity==ACTIVITY_IRRIGATE) {
//    if (total_activity (punit.tile, ACTIVITY_IRRIGATE) >=
//        map_build_irrigation_time(punit.tile)) {
//      int old = punit.tile.terrain;
//      map_irrigate_tile(punit.tile);
//      solvency = check_terrain_ocean_land_change(punit.tile, old);
//      unit_activity_done = true;
//    }
//  }
//
//  if (activity==ACTIVITY_ROAD) {
//    if (total_activity (punit.tile, ACTIVITY_ROAD)
//	+ total_activity (punit.tile, ACTIVITY_RAILROAD) >=
//        map_build_road_time(punit.tile)) {
//      map_set_special(punit.tile, Terrain_H.S_ROAD);
//      unit_activity_done = true;
//    }
//  }
//
//  if (activity==ACTIVITY_RAILROAD) {
//    if (total_activity (punit.tile, ACTIVITY_RAILROAD)
//	>= map_build_rail_time(punit.tile)) {
//      map_set_special(punit.tile, Terrain_H.S_RAILROAD);
//      unit_activity_done = true;
//    }
//  }
//  
//  if (activity==ACTIVITY_MINE) {
//    if (total_activity (punit.tile, ACTIVITY_MINE) >=
//        map_build_mine_time(punit.tile)) {
//      int old = punit.tile.terrain;
//      map_mine_tile(punit.tile);
//      solvency = check_terrain_ocean_land_change(punit.tile, old);
//      unit_activity_done = true;
//      check_adjacent_units = true;
//    }
//  }
//
//  if (activity==ACTIVITY_TRANSFORM) {
//    if (total_activity (punit.tile, ACTIVITY_TRANSFORM) >=
//        map_transform_time(punit.tile)) {
//      int old = punit.tile.terrain;
//      map_transform_tile(punit.tile);
//      solvency = check_terrain_ocean_land_change(punit.tile, old);
//      unit_activity_done = true;
//      check_adjacent_units = true;
//    }
//  }
//
//  if (unit_activity_done) {
//    Maphand.update_tile_knowledge(punit.tile);
//    unit_list_iterate (punit.tile.units, punit2) {
//      if (punit2.activity == activity) {
//	set_unit_activity(punit2, unit_activity.ACTIVITY_IDLE);
//	send_unit_info(null, punit2);
//      }
//    } }
//  }
//
//  /* Some units nearby can not continue irrigating */
//  if (check_adjacent_units) {
//    for(tile ptile2: util.adjc_tile_iterate(punit.tile)) {
//      for (unit punit2 : ptile2.units.data) {
//        if (!can_unit_continue_current_activity(punit2)) {
//          handle_unit_activity_request(punit2, unit_activity.ACTIVITY_IDLE);
//        }
//      } }
//    }
//  }
//
//  if (activity==ACTIVITY_FORTIFYING) {
//    if (punit.activity_count >= 1) {
//      set_unit_activity(punit,ACTIVITY_FORTIFIED);
//    }
//  }
//
//  if (activity==unit_activity.ACTIVITY_GOTO) {
//    if (!punit.ai.control && (!is_military_unit(punit) ||
//       punit.ai.passenger != 0 || !pplayer.ai.control)) {
///* autosettlers otherwise waste time; idling them breaks assignment */
///* Stalling infantry on GOTO so I can see where they're GOing TO. -- Syela */
//      () do_unit_goto(punit, goto_move_restriction.GOTO_MOVE_ANY, true);
//    }
//    return;
//  }
//
//  if (unit_has_orders(punit)) {
//    if (!execute_orders(punit)) {
//      /* Unit util.died. */
//      return;
//    }
//  }
//
//  send_unit_info(null, punit);
//
//  for (unit punit2 : ptile.units.data) {
//    if (!can_unit_continue_current_activity(punit2))
//    {
//      handle_unit_activity_request(punit2, unit_activity.ACTIVITY_IDLE);
//    }
//  } }
//
//  /* Any units that landed in water or boats that landed on land as a
//     result of settlers changing terrain must be moved back into their
//     right environment.
//     We advance the unit_list iterator passed into this routine from
//     update_unit_activities() if we delete the unit it points to.
//     We go to START each time we moved a unit to avoid problems with the
//     tile unit list getting corrupted.
//
//     FIXME:  We shouldn't do this at all!  There seems to be another
//     bug which is expressed when units wind up on the "wrong" terrain;
//     this is the bug that should be fixed.  Also, introduction of the
//     "amphibious" movement category would allow the definition of units
//     that can "safely" change land<.ocean -- in which case all others
//     would (here) be summarily disbanded (suicide to accomplish their
//     task, for the greater good :).   --jjm
//  */
// START:
//  switch (solvency) {
//  case OLC_NONE:
//    break; /* nothing */
//
//  case OLC_LAND_TO_OCEAN:
//    for (unit punit2 : ptile.units.data) {
//      if (is_ground_unit(punit2)) {
//	/* look for nearby land */
//	for(tile ptile2: util.adjc_tile_iterate(punit.tile)) {
//	  if (!Terrain_H.is_ocean(ptile2.terrain)
//	      && !Unit.is_non_allied_unit_tile(ptile2, punit2.unit_owner())) {
//	    if (Unit.get_transporter_capacity(punit2) > 0)
//	      sentry_transported_idle_units(punit2);
//	    util.freelog(Log.LOG_VERBOSE,
//		    "Moved %s's %s due to changing land to sea at (%d, %d).",
//		    punit2.unit_owner().name, unit_name(punit2.type),
//		    punit2.tile.x, punit2.tile.y);
//	    Plrhand.notify_player_ex(punit2.unit_owner(),
//			     punit2.tile, E_UNIT_RELOCATED,
//			     ("Game: Moved your %s due to changing" +
//			       " land to sea."), unit_name(punit2.type));
//	    () move_unit(punit2, ptile2, 0);
//	    if (punit2.activity == ACTIVITY_SENTRY)
//	      handle_unit_activity_request(punit2, unit_activity.ACTIVITY_IDLE);
//	    goto START;
//	  }
//	}
//	/* look for nearby transport */
//	for(tile ptile2: util.adjc_tile_iterate(punit.tile)) {
//	  if (Terrain_H.is_ocean(ptile2.terrain)
//	      && ground_unit_transporter_capacity(ptile2,
//						  punit2.unit_owner()) > 0) {
//	    if (Unit.get_transporter_capacity(punit2) > 0)
//	      sentry_transported_idle_units(punit2);
//	    util.freelog(Log.LOG_VERBOSE,
//		    "Embarked %s's %s due to changing land to sea at (%d, %d).",
//		    punit2.unit_owner().name, unit_name(punit2.type),
//		    punit2.tile.x, punit2.tile.x);
//	    Plrhand.notify_player_ex(punit2.unit_owner(),
//			     punit2.tile, E_UNIT_RELOCATED,
//			     ("Game: Embarked your %s due to changing" +
//			       " land to sea."), unit_name(punit2.type));
//	    () move_unit(punit2, ptile2, 0);
//	    if (punit2.activity == ACTIVITY_SENTRY)
//	      handle_unit_activity_request(punit2, unit_activity.ACTIVITY_IDLE);
//	    goto START;
//	  }
//	}
//	/* if we get here we could not move punit2 */
//	util.freelog(Log.LOG_VERBOSE,
//		"Disbanded %s's %s due to changing land to sea at (%d, %d).",
//		punit2.unit_owner().name, unit_name(punit2.type),
//		punit2.tile.x, punit2.tile.y);
//	Plrhand.notify_player_ex(punit2.unit_owner(),
//			 punit2.tile, E_UNIT_LOST,
//			 ("Game: Disbanded your %s due to changing" +
//			   " land to sea."), unit_name(punit2.type));
//	wipe_unit_spec_safe(punit2, false);
//	goto START;
//      }
//    } }
//    break;
//  case OLC_OCEAN_TO_LAND:
//    for (unit punit2 : ptile.units.data) {
//      if (is_sailing_unit(punit2)) {
//	/* look for nearby water */
//	for(tile ptile2: util.adjc_tile_iterate(punit.tile)) {
//	  if (Terrain_H.is_ocean(ptile2.terrain)
//	      && !Unit.is_non_allied_unit_tile(ptile2, punit2.unit_owner())) {
//	    if (Unit.get_transporter_capacity(punit2) > 0)
//	      sentry_transported_idle_units(punit2);
//	    util.freelog(Log.LOG_VERBOSE,
//		    "Moved %s's %s due to changing sea to land at (%d, %d).",
//		    punit2.unit_owner().name, unit_name(punit2.type),
//		    punit2.tile.x, punit2.tile.y);
//	    Plrhand.notify_player_ex(punit2.unit_owner(),
//			     punit2.tile, E_UNIT_RELOCATED,
//			     ("Game: Moved your %s due to changing" +
//			       " sea to land."), unit_name(punit2.type));
//	    () move_unit(punit2, ptile2, 0);
//	    if (punit2.activity == ACTIVITY_SENTRY)
//	      handle_unit_activity_request(punit2, unit_activity.ACTIVITY_IDLE);
//	    goto START;
//	  }
//	}
//	/* look for nearby port */
//	for(tile ptile2: util.adjc_tile_iterate(punit.tile)) {
//	  if (City.is_allied_city_tile(ptile2, punit2.unit_owner())
//	      && !Unit.is_non_allied_unit_tile(ptile2, punit2.unit_owner())) {
//	    if (Unit.get_transporter_capacity(punit2) > 0)
//	      sentry_transported_idle_units(punit2);
//	    util.freelog(Log.LOG_VERBOSE,
//		    "Docked %s's %s due to changing sea to land at (%d, %d).",
//		    punit2.unit_owner().name, unit_name(punit2.type),
//		    punit2.tile.x, punit2.tile.y);
//	    Plrhand.notify_player_ex(punit2.unit_owner(),
//			     punit2.tile, E_UNIT_RELOCATED,
//			     ("Game: Docked your %s due to changing" +
//			       " sea to land."), unit_name(punit2.type));
//	    () move_unit(punit2, ptile2, 0);
//	    if (punit2.activity == ACTIVITY_SENTRY)
//	      handle_unit_activity_request(punit2, unit_activity.ACTIVITY_IDLE);
//	    goto START;
//	  }
//	}
//	/* if we get here we could not move punit2 */
//	util.freelog(Log.LOG_VERBOSE,
//		"Disbanded %s's %s due to changing sea to land at (%d, %d).",
//		punit2.unit_owner().name, unit_name(punit2.type),
//		punit2.tile.x, punit2.tile.y);
//	Plrhand.notify_player_ex(punit2.unit_owner(),
//			 punit2.tile, E_UNIT_LOST,
//			 ("Game: Disbanded your %s due to changing" +
//			   " sea to land."), unit_name(punit2.type));
//	wipe_unit_spec_safe(punit2, false);
//	goto START;
//      }
//    } }
//    break;
//  }
//}
//
///**************************************************************************
//  Returns a pointer to a (static) string which gives an informational
//  message about location (x,y), in terms of cities known by pplayer.
//  One of:
//    "in Foo City"  or  "at Foo City" (see below)
//    "outside Foo City" +
//    "near Foo City" +
//    "" (if no cities known)
//  There are two variants for the first case, one when something happens
//  inside the city, otherwise when it happens "at" but "outside" the city.
//  Eg, when an attacker fails, the attacker util.dies "at" the city, but
//  not "in" the city (since the attacker never made it in).
//  Don't call this function directly; use the wrappers below.
//**************************************************************************/
//static char *get_location_str(player pplayer, tile ptile, boolean use_at)
//{
//  static char buffer[MAX_LEN_NAME+64];
//  city incity, *nearcity;
//
//  incity = map_get_city(ptile);
//  if (incity) {
//    if (use_at) {
//      buffer = util.my_snprintf( " at %s", incity.name);
//    } else {
//      buffer = util.my_snprintf( " in %s", incity.name);
//    }
//  } else {
//    nearcity = Aitools.dist_nearest_city(pplayer, ptile, false, false);
//    if (nearcity) {
//      if (is_tiles_adjacent(ptile, nearcity.tile)) {
//	buffer = util.my_snprintf(
//		   " outside %s", nearcity.name);
//      } else {
//	buffer = util.my_snprintf(
//		    " near %s", nearcity.name);
//      }
//    } else {
//      buffer[0] = '\0';
//    }
//  }
//  return buffer;
//}
//
///**************************************************************************
//  See get_location_str() above.
//**************************************************************************/
//char *get_location_str_in(player pplayer, tile ptile)
//{
//  return get_location_str(pplayer, ptile, false);
//}
//
///**************************************************************************
//  See get_location_str() above.
//**************************************************************************/
//char *get_location_str_at(player pplayer, tile ptile)
//{
//  return get_location_str(pplayer, ptile, true);
//}
//
///**************************************************************************
//...
//**************************************************************************/
//goto_move_restriction get_activity_move_restriction(enum unit_activity activity)
//{
//  goto_move_restriction restr;
//
//  switch (activity) {
//  case ACTIVITY_IRRIGATE:
//    restr = GOTO_MOVE_CARDINAL_ONLY;
//    break;
//  case ACTIVITY_POLLUTION:
//  case ACTIVITY_ROAD:
//  case ACTIVITY_MINE:
//  case ACTIVITY_FORTRESS:
//  case ACTIVITY_RAILROAD:
//  case ACTIVITY_PILLAGE:
//  case ACTIVITY_TRANSFORM:
//  case ACTIVITY_AIRBASE:
//  case ACTIVITY_FALLOUT:
//    restr = GOTO_MOVE_STRAIGHTEST;
//    break;
//  default:
//    restr = goto_move_restriction.GOTO_MOVE_ANY;
//    break;
//  }
//
//  return (restr);
//}
//
///**************************************************************************
//...
//**************************************************************************/
//static boolean find_a_good_partisan_spot(city pcity, int u_type,
//				      tile *dst_tile)
//{
//  int bestvalue = 0;
//  /* coords of best tile in arg pointers */
//  map_city_radius_iterate(pcity.tile, ptile) {
//    int value;
//    if (Terrain_H.is_ocean(ptile.terrain)) {
//      continue;
//    }
//    if (ptile.city)
//      continue;
//    if (ptile.units.foo_list_size() > 0)
//      continue;
//    value = get_virtual_defense_power(unittype.U_LAST, u_type, ptile, false, 0);
//    value *= 10;
//
//    if (ptile.continent != map_get_continent(pcity.tile)) {
//      value /= 2;
//    }
//
//    value -= Rand.myrand(value/3);
//
//    if (value > bestvalue) {
//      *dst_tile = ptile;
//      bestvalue = value;
//    }
//  } map_city_radius_iterate_end;
//
//  return bestvalue > 0;
//}
//
///**************************************************************************
//  finds a spot around pcity and place a partisan.
//**************************************************************************/
//static void place_partisans(city pcity, int count)
//{
//  tile ptile = null;
//  int u_type = Unittype_P.get_role_unit(L_PARTISAN, 0);
//
//  while ((count--) > 0 && find_a_good_partisan_spot(pcity, u_type, &ptile)) {
//    unit punit;
//    punit = create_unit(City.city_owner(pcity), ptile, u_type, 0, 0, -1);
//    if (can_unit_do_activity(punit, ACTIVITY_FORTIFYING)) {
//      punit.activity = ACTIVITY_FORTIFIED; /* yes; directly fortified */
//      send_unit_info(null, punit);
//    }
//  }
//}
//
///**************************************************************************
//  if requirements to make partisans when a city is conquered is fullfilled
//  this routine makes a lot of partisans based on the city's size.
//  To be candidate for partisans the following things must be satisfied:
//  1) Guerilla warfare must be known by atleast 1 player
//  2) The owner of the city is the original player.
//  3) The player must know about communism and gunpowder
//  4) the player must run either a democracy or a communist society.
//**************************************************************************/
//void make_partisans(city pcity)
//{
//  player pplayer;
//  int i, partisans;
//
//  if (num_role_units(L_PARTISAN)==0)
//    return;
//  if (!tech_exists(Game.game.rtech.u_partisan)
//      || Game.game.global_advances[Game.game.rtech.u_partisan] == 0
//      || pcity.original != pcity.owner)
//    return;
//
//  if (!government_has_flag(get_gov_pcity(pcity), G_INSPIRES_PARTISANS))
//    return;
//  
//  pplayer = City.city_owner(pcity);
//  for(i=0; i<MAX_NUM_TECH_LIST; i++) {
//    int tech = Game.game.rtech.partisan_req[i];
//    if (tech == A_LAST) break;
//    if (get_invention(pplayer, tech) != TECH_KNOWN) return;
//    /* Was A_COMMUNISM and A_GUNPOWDER */
//  }
//  
//  partisans = Rand.myrand(1 + pcity.size/2) + 1;
//  if (partisans > 8) 
//    partisans = 8;
//  
//  place_partisans(pcity,partisans);
//}
//
///**************************************************************************
//Are there dangerous enemies at or adjacent to (x, y)?
//
//N.B. This function should only be used by (cheating) AI, as it iterates 
//through all units stacked on the tiles, an info not normally available 
//to the human player.
//**************************************************************************/
//boolean enemies_at(unit punit, tile ptile)
//{
//  int a = 0, d, db;
//  player pplayer = punit.unit_owner();
//  city pcity = ptile.city;
//
//  if (pcity && pplayers_allied(City.city_owner(pcity), punit.unit_owner())
//      && !Unit.is_non_allied_unit_tile(ptile, pplayer)) {
//    /* We will be safe in a friendly city */
//    return false;
//  }
//
//  /* Calculate how well we can defend at (x,y) */
//  db = get_tile_type(ptile.terrain).defense_bonus;
//  if (Map.map_has_special(ptile, S_RIVER))
//    db += (db * terrain_control.river_defense_bonus) / 100;
//  d = unit_def_rating_basic_sq(punit) * db;
//
//  for(tile ptile1: util.adjc_tile_iterate(ptile)) {
//    if (ai_handicap(pplayer, H_FOG)
//	&& !Maphand.map_is_known_and_seen(ptile1, punit.unit_owner())) {
//      /* We cannot see danger at (ptile1) => assume there is none */
//      continue;
//    }
//    for (unit enemy : ptile1.units.data) {
//      if (pplayers_at_war(enemy.unit_owner(), punit.unit_owner()) 
//          && can_unit_attack_unit_at_tile(enemy, punit, ptile)
//          && can_unit_attack_all_at_tile(enemy, ptile)) {
//	a += unit_att_rating(enemy);
//	if ((a * a * 10) >= d) {
//          /* The enemies combined strength is too big! */
//          return true;
//        }
//      }
//    } }
//  }
//
//  return false; /* as good a quick'n'dirty should be -- Syela */
//}
//
///**************************************************************************
//Teleport punit to city at cost specified.  Returns success.
//(If specified cost is -1, then teleportation costs all movement.)
//                         - Kris Bubendorfer
//**************************************************************************/
//boolean teleport_unit_to_city(unit punit, city pcity,
//			  int move_cost, boolean verbose)
//{
//  tile src_tile = punit.tile, *dst_tile = pcity.tile;
//
//  if (pcity.owner == punit.owner){
//    util.freelog(Log.LOG_VERBOSE, "Teleported %s's %s from (%d, %d) to %s",
//	    punit.unit_owner().name, unit_name(punit.type),
//	    src_tile.x, src_tile.y, pcity.name);
//    if (verbose) {
//      Plrhand.notify_player_ex(punit.unit_owner(), pcity.tile, event_type.E_NOEVENT,
//		       "Game: Teleported your %s to %s.",
//		       unit_name(punit.type), pcity.name);
//    }
//
//    /* Silently free orders since they won't be applicable anymore. */
//    free_unit_orders(punit);
//
//    if (move_cost == -1)
//      move_cost = punit.moves_left;
//    return move_unit(punit, dst_tile, move_cost);
//  }
//  return false;
//}
//
///**************************************************************************
//  Teleport or remove a unit due to stack conflict.
//**************************************************************************/
//void bounce_unit(unit punit, boolean verbose)
//{
//  player pplayer = punit.unit_owner();
//  city pcity = find_closest_owned_city(pplayer, punit.tile,
//                                               is_sailing_unit(punit), null);
//
//  if (pcity) {
//    () teleport_unit_to_city(punit, pcity, 0, verbose);
//  } else {
//    /* remove it */
//    if (verbose) {
//      Plrhand.notify_player_ex(punit.unit_owner(), punit.tile, event_type.E_NOEVENT,
//		       "Game: Disbanded your %s.",
//		       unit_name(punit.type));
//    }
//    wipe_unit(punit);
//  }
//}
//
//
///**************************************************************************
//  Throw pplayer's units from non allied cities
//
//  If verbose is true, pplayer gets messages about where each units goes.
//**************************************************************************/
//static void throw_units_from_illegal_cities(player pplayer,
//                                           boolean verbose)
//{
//  for (unit punit : pplayer.units.data) {
//    tile ptile = punit.tile;
//
//    if (ptile.city && !pplayers_allied(City.city_owner(ptile.city), pplayer)) {
//      bounce_unit(punit, verbose);
//    }
//  }    
//}
//
///**************************************************************************
//  For each pplayer's unit, check if we stack illegally, if so,
//  bounce both players' units. If on ocean tile, bounce everyone
//  to avoid drowning. This function assumes that cities are clean.
//
//  If verbose is true, the unit owner gets messages about where each
//  units goes.
//**************************************************************************/
//static void resolve_stack_conflicts(player pplayer,
//                                    player aplayer, boolean verbose)
//{
//  for (unit punit : pplayer.units.data) {
//    tile ptile = punit.tile;
//
//    if (Unit.is_non_allied_unit_tile(ptile, pplayer)) {
//      for (unit aunit : ptile.units.data) {
//        if (aunit.unit_owner() == pplayer
//            || aunit.unit_owner() == aplayer
//            || Terrain_H.is_ocean(ptile.terrain)) {
//          bounce_unit(aunit, verbose);
//        }
//      }
//    }    
//  }
//}
//				
///**************************************************************************
//  When in civil war or an alliance breaks there will potentially be units 
//  from both sides coexisting on the same squares.  This routine resolves 
//  this by first bouncing off non-allied units from their cities, then by 
//  bouncing both players' units in now illegal multiowner stacks.  To avoid
//  drowning due to removal of transports, we bounce everyone (including
//  third parties' units) from ocean tiles.
//
//  If verbose is true, the unit owner gets messages about where each
//  units goes.
//**************************************************************************/
//void resolve_unit_stacks(player pplayer, player aplayer,
//                         boolean verbose)
//{
//  throw_units_from_illegal_cities(pplayer, verbose);
//  throw_units_from_illegal_cities(aplayer, verbose);
//  
//  resolve_stack_conflicts(pplayer, aplayer, verbose);
//  resolve_stack_conflicts(aplayer, pplayer, verbose);
//}
//
///****************************************************************************
//  When two players cancel an alliance, a lot of units that were visible may
//  no longer be visible (this includes units in transporters and cities).
//  Call this function to inform the clients that these units are no longer
//  visible.  Note that this function should be called _after_
//  resolve_unit_stacks().
//****************************************************************************/
//void remove_allied_visibility(struct player* pplayer, struct player* aplayer)
//{
//  for (unit punit : aplayer.units.data) {
//    /* We don't know exactly which units have been hidden.  But only a unit
//     * whose tile is visible but who aren't visible themselves are
//     * candidates.  This solution just tells the client to drop all such
//     * units.  If any of these are unknown to the client the client will
//     * just ignore them. */
//    if (Maphand.map_is_known_and_seen(punit.tile, pplayer) &&
//        !can_player_see_unit(pplayer, punit)) {
//      unit_goes_out_of_sight(pplayer, punit);
//    }
//  } }
//
//  for (city pcity : aplayer.cities.data) {
//    /* The player used to know what units were in these cities.  Now that he
//     * doesn't, he needs to get a new short city packet updating the
//     * occupied status. */
//    if (Maphand.map_is_known_and_seen(pcity.tile, pplayer)) {
//      send_city_info(pplayer, pcity);
//    }
//  } }
//}
//
///**************************************************************************
//...
//**************************************************************************/
//boolean is_airunit_refuel_point(tile ptile, player pplayer,
//			     int type, boolean unit_is_on_tile)
//{
//  player_tile plrtile = map_get_player_tile(ptile, pplayer);
//
//  if ((City.is_allied_city_tile(ptile, pplayer)
//       && !Unit.is_non_allied_unit_tile(ptile, pplayer))
//      || (contains_special(plrtile.special, Terrain_H.S_AIRBASE)
//	  && !Unit.is_non_allied_unit_tile(ptile, pplayer)))
//    return true;
//
//  if (unit_type_flag(type, F_MISSILE)) {
//    int cap = missile_carrier_capacity(ptile, pplayer, false);
//    if (unit_is_on_tile)
//      cap++;
//    return cap>0;
//  } else {
//    int cap = airunit_carrier_capacity(ptile, pplayer, false);
//    if (unit_is_on_tile)
//      cap++;
//    return cap>0;
//  }
//}
//
///**************************************************************************
//  Really upgrades a single unit.
//
//  Before calling this function you should use unit_upgrade to test if
//  this is possible.
//
//  is_free: Leonardo upgrade for free, in all other cases the unit
//  owner has to pay
//
//  Note that this function is strongly tied to unit.c:test_unit_upgrade().
//**************************************************************************/
//void upgrade_unit(unit punit, int to_unit, boolean is_free)
//{
//  player pplayer = punit.unit_owner();
//  int range;
//  int old_mr = punit.move_rate(), old_hp = punit.unit_type().hp;
//
//  assert(test_unit_upgrade(punit, is_free) == UR_OK);
//
//  if (!is_free) {
//    pplayer.economic.gold -=
//	unit_upgrade_price(pplayer, punit.type, to_unit);
//  }
//
//  /* save old vision range */
//  if (Map.map_has_special(punit.tile, S_FORTRESS)
//      && unit_profits_of_watchtower(punit))
//    range = get_watchtower_vision(punit);
//  else
//    range = punit.unit_type().vision_range;
//
//  punit.type = to_unit;
//
//  /* Scale HP and MP, rounding down.  Be careful with integer arithmetic,
//   * and don't kill the unit.  unit_move_rate is used to take into account
//   * global effects like Magellan's Expedition. */
//  punit.hp = MAX(punit.hp * punit.unit_type().hp / old_hp, 1);
//  punit.moves_left = punit.moves_left * punit.move_rate() / old_mr;
//
//  conn_list_do_buffer(&pplayer.connections);
//
//  /* Apply new vision range
//   *
//   * Unfog (increase seen counter) first, fog (decrease counter)
//   * later, so tiles that are within vision range both before and
//   * after are not even temporarily marked fogged. */
//
//  if (Map.map_has_special(punit.tile, S_FORTRESS)
//      && unit_profits_of_watchtower(punit))
//    unfog_area(pplayer, punit.tile, get_watchtower_vision(punit));
//  else
//    unfog_area(pplayer, punit.tile,
//	       get_unit_type(to_unit).vision_range);
//
//  fog_area(pplayer, punit.tile, range);
//
//  send_unit_info(null, punit);
//  conn_list_do_unbuffer(&pplayer.connections);
//}
//
/************************************************************************* 
  Wrapper of the below
*************************************************************************/
public static unit create_unit(player pplayer, tile ptile, 
                         int type, int veteran_level, 
                         int homecity_id, int moves_left)
{
  return create_unit_full(pplayer, ptile, type, veteran_level, homecity_id, 
                          moves_left, -1, null);
}

/**************************************************************************
  Creates a unit, and set it's initial values, and put it into the right 
  lists.
  If moves_left is less than zero, unit will get max moves.
**************************************************************************/
public static unit create_unit_full(player pplayer, tile ptile,
			      int type, int veteran_level, 
                              int homecity_id, int moves_left, int hp_left,
			      unit ptrans)
{
//  unit punit = create_unit_virtual(pplayer, null, type, veteran_level);
//  city pcity;
//
//  /* Register unit */
//  punit.id = get_next_id_number();
//  idex_register_unit(punit);
//
//  assert(ptile != null);
//  punit.tile = ptile;
//
//  pcity = find_city_by_id(homecity_id);
//  if (unit_type_flag(type, F_NOHOME)) {
//    punit.homecity = 0; /* none */
//  } else {
//    punit.homecity = homecity_id;
//  }
//
//  if (hp_left >= 0) {
//    /* Override default full HP */
//    punit.hp = hp_left;
//  }
//
//  if (moves_left >= 0) {
//    /* Override default full MP */
//    punit.moves_left = Math.min(moves_left, punit.move_rate());
//  }
//
//  if (ptrans) {
//    /* Set transporter for unit. */
//    punit.transported_by = ptrans.id;
//  }
//
//  /* Assume that if moves_left < 0 then the unit is "fresh",
//   * and not moved; else the unit has had something happen
//   * to it (eg, bribed) which we treat as equivalent to moved.
//   * (Otherwise could pass moved arg too...)  --dwp */
//  punit.moved = (moves_left >= 0);
//
//  /* See if this is a spy that has been moved (corrupt and therefore 
//   * unable to establish an embassy. */
//  punit.foul = (moves_left != -1 && unit_flag(punit, F_SPY));
//
//  unit_list_insert(&pplayer.units, punit);
//  unit_list_insert(&ptile.units, punit);
//  if (pcity && !unit_type_flag(type, F_NOHOME)) {
//    assert(City.city_owner(pcity) == pplayer);
//    unit_list_insert(&pcity.units_supported, punit);
//    /* Refresh the unit's homecity. */
//    city_refresh(pcity);
//    send_city_info(pplayer, pcity);
//  }
//
//  if (Map.map_has_special(ptile, S_FORTRESS)
//      && unit_profits_of_watchtower(punit)) {
//    unfog_area(pplayer, punit.tile, get_watchtower_vision(punit));
//  } else {
//    unfog_area(pplayer, ptile, punit.unit_type().vision_range);
//  }
//
//  send_unit_info(null, punit);
//  maybe_make_contact(ptile, punit.unit_owner());
//  wakeup_neighbor_sentries(punit);
//
//  /* The unit may have changed the available tiles in nearby cities. */
//  map_city_radius_iterate(ptile, ptile1) {
//    city acity = map_get_city(ptile1);
//
//    if (acity) {
//      update_city_tile_status_map(acity, ptile);
//    }
//  } map_city_radius_iterate_end;
//
//  sync_cities();
//
//  return punit;
	return null;
}
//
///**************************************************************************
//We remove the unit and see if it's disappearance has affected the homecity
//and the city it was in.
//**************************************************************************/
//static void server_remove_unit(unit punit)
//{
//  city pcity = map_get_city(punit.tile);
//  city phomecity = find_city_by_id(punit.homecity);
//  tile unit_tile = punit.tile;
//  player unitowner = punit.unit_owner();
//
//#ifndef NDEBUG
//  for (unit pcargo : punit.tile.units.data) {
//    assert(pcargo.transported_by != punit.id);
//  } }
//#endif
//
//  /* Since settlers plot in new cities in the minimap before they
//     are built, so that no two settlers head towards the same city
//     spot, we need to ensure this reservation is cleared should
//     the settler util.die on the way. */
//  if ((punit.unit_owner().ai.control || punit.ai.control)
//      && punit.ai.ai_role != AIUNIT_NONE) {
//    ai_unit_new_role(punit, AIUNIT_NONE, null);
//  }
//
//  for(player pplayer: Game.game.players){
//    if (Maphand.map_is_known_and_seen(unit_tile, pplayer)) {
//      dlsend_packet_unit_remove(&pplayer.connections, punit.id);
//    }
//  }
//
//  remove_unit_sight_points(punit);
//
//  /* check if this unit had F_GAMELOSS flag */
//  if (unit_flag(punit, F_GAMELOSS) && punit.unit_owner().is_alive) {
//    notify_conn_ex(&Game.game.est_connections, punit.tile, E_UNIT_LOST,
//                   "Unable to defend %s, %s has lost the Game.game.",
//                   unit_name(punit.type), punit.unit_owner().name);
//    notify_player(punit.unit_owner(), ("Losing %s meant losing the Game.game! " +
//                  "Be more careful next time!"), unit_name(punit.type));
//    Gamelog.gamelog(GAMELOG_UNITGAMELOSS, punit);
//    punit.unit_owner().is_dying = true;
//  }
//
//  game_remove_unit(punit);
//  punit = null;
//
//  /* Hide any submarines that are no longer visible. */
//  conceal_hidden_units(unitowner, unit_tile);
//
//  /* This unit may have blocked tiles of adjacent cities. Update them. */
//  map_city_radius_iterate(unit_tile, ptile1) {
//    city pcity = map_get_city(ptile1);
//    if (pcity) {
//      update_city_tile_status_map(pcity, unit_tile);
//    }
//  } map_city_radius_iterate_end;
//  sync_cities();
//
//  if (phomecity) {
//    city_refresh(phomecity);
//    send_city_info(City.city_owner(phomecity), phomecity);
//  }
//  if (pcity && pcity != phomecity) {
//    city_refresh(pcity);
//    send_city_info(City.city_owner(pcity), pcity);
//  }
//  if (pcity && unit_tile.units.foo_list_size() == 0) {
//    /* The last unit in the city was killed: update the occupied flag. */
//    send_city_info(null, pcity);
//  }
//}
//
///**************************************************************************
//  Remove the unit, and passengers if it is a carrying any. Remove the 
//  _minimum_ number, eg there could be another boat on the square.
//**************************************************************************/
//void wipe_unit_spec_safe(unit punit, boolean wipe_cargo)
//{
//  tile ptile = punit.tile;
//  player pplayer = punit.unit_owner();
//  unit_type ptype = punit.unit_type();
//
//  /* First pull all units off of the transporter. */
//  if (Unit.get_transporter_capacity(punit) > 0) {
//    /* FIXME: there's no send_unit_info call below so these units aren't
//     * updated at the client.  I guess this works because the unit info
//     * will be sent eventually anyway, but it's surely a bug. */
//    for (unit pcargo : ptile.units.data) {
//      if (pcargo.transported_by == punit.id) {
//	/* Could use unload_unit_from_transporter here, but that would
//	 * call send_unit_info for the transporter unnecessarily. */
//	pull_unit_from_transporter(pcargo, punit);
//	if (pcargo.activity == ACTIVITY_SENTRY) {
//	  /* Activate sentried units - like planes on a disbanded carrier.
//	   * Note this will activate ground units even if they just change
//	   * transporter. */
//	  set_unit_activity(pcargo, unit_activity.ACTIVITY_IDLE);
//	}
//	send_unit_info(null, pcargo);
//      } 
//    } }
//  }
//
//  /* No need to wipe the cargo unless it's a ground transporter. */
//  wipe_cargo &= is_ground_units_transport(punit);
//
//  /* Now remove the unit. */
//  server_remove_unit(punit);
//
//  /* Finally reassign, bounce, or destroy all ground units at this location.
//   * There's no need to worry about air units; they can fly away. */
//  if (wipe_cargo
//      && Terrain_H.is_ocean(ptile.terrain)
//      && !map_get_city(ptile)) {
//    city pcity = null;
//    int capacity = ground_unit_transporter_capacity(ptile, pplayer);
//
//    /* Get rid of excess standard units. */
//    if (capacity < 0) {
//      for (unit pcargo : ptile.units.data) {
//	if (is_ground_unit(pcargo)
//	    && pcargo.transported_by == -1
//	    && !unit_flag(pcargo, F_UNDISBANDABLE)
//	    && !unit_flag(pcargo, F_GAMELOSS)) {
//	  server_remove_unit(pcargo);
//	  if (++capacity >= 0) {
//	    break;
//	  }
//	}
//      }
//    }
//
//    /* Get rid of excess undisbandable/gameloss units. */
//    if (capacity < 0) {
//      for (unit pcargo : ptile.units.data) {
//	if (is_ground_unit(pcargo) && pcargo.transported_by == -1) {
//	  if (unit_flag(pcargo, F_UNDISBANDABLE)) {
//	    pcity = find_closest_owned_city(pcargo.unit_owner(),
//					    pcargo.tile, true, null);
//	    if (pcity && teleport_unit_to_city(pcargo, pcity, 0, false)) {
//	      Plrhand.notify_player_ex(pplayer, ptile, event_type.E_NOEVENT,
//			       ("Game: %s escaped the destruction of %s, and " +
//				 "fled to %s."), pcargo.unit_type().name,
//			       ptype.name, pcity.name);
//	    }
//	  }
//	  if (!unit_flag(pcargo, F_UNDISBANDABLE) || !pcity) {
//	    Plrhand.notify_player_ex(pplayer, ptile, E_UNIT_LOST,
//			     "Game: %s lost when %s was lost.",
//			     pcargo.unit_type().name,
//			     ptype.name);
//	    Gamelog.gamelog(GAMELOG_UNITLOSS, pcargo, null, "transport lost");
//	    server_remove_unit(pcargo);
//	  }
//	  if (++capacity >= 0) {
//	    break;
//	  }
//	}
//      }
//    }
//
//    /* Reassign existing units.  This is an O(n^2) operation as written. */
//    for (unit ptrans : ptile.units.data) {
//      if (is_ground_units_transport(ptrans)) {
//	int occupancy = get_transporter_occupancy(ptrans);
//
//	for (unit pcargo : ptile.units.data) {
//	  if (occupancy >= Unit.get_transporter_capacity(ptrans)) {
//	    break;
//	  }
//	  if (is_ground_unit(pcargo) && pcargo.transported_by == -1) {
//	    put_unit_onto_transporter(pcargo, ptrans);
//	    occupancy++;
//	  }
//	} }
//      }
//    } }
//  }
//}

	/**************************************************************************
...
	 **************************************************************************/
	public static void wipe_unit(unit punit)
	{
//		wipe_unit_spec_safe(punit, true);
	}

///**************************************************************************
//this is a highlevel routine
//the unit has been killed in combat => all other units on the
//tile util.dies unless ...
//**************************************************************************/
//void kill_unit(unit pkiller, unit punit)
//{
//  player pplayer   = punit.unit_owner();
//  player destroyer = pkiller.unit_owner();
//  char *loc_str = get_location_str_in(pplayer, punit.tile);
//  int num_killed[Shared_H.MAX_NUM_PLAYERS + Shared_H.MAX_NUM_BARBARIANS];
//  int ransom, unitcount = 0;
//  
//  /* barbarian leader ransom hack */
//  if( is_barbarian(pplayer) && unit_has_role(punit.type, unit_role_id.L_BARBARIAN_LEADER)
//      && (punit.tile.units.foo_list_size() == 1)
//      && (is_ground_unit(pkiller) || is_heli_unit(pkiller)) ) {
//    ransom = (pplayer.economic.gold >= 100)?100:pplayer.economic.gold;
//    Plrhand.notify_player_ex(destroyer, pkiller.tile, E_UNIT_WIN_ATT,
//		     "Game: Barbarian leader captured, %d gold ransom paid.",
//                     ransom);
//    destroyer.economic.gold += ransom;
//    pplayer.economic.gold -= ransom;
//    Plrhand.send_player_info(destroyer, null);   /* let me see my new gold :-) */
//    unitcount = 1;
//  }
//
//  if (unitcount == 0) {
//    unit_list_iterate(punit.tile.units, vunit)
//      if (pplayers_at_war(pkiller.unit_owner(), vunit.unit_owner()))
//	unitcount++;
//    }
//  }
//
//  if (!is_stack_vulnerable(punit.tile) || unitcount == 1) {
//    Plrhand.notify_player_ex(pplayer, punit.tile, E_UNIT_LOST,
//		     "Game: %s lost to an attack by %s's %s%s.",
//		     punit.unit_type().name, destroyer.name,
//		     unit_name(pkiller.type), loc_str);
//
//    Gamelog.gamelog(GAMELOG_UNITLOSS, punit, destroyer);
//    wipe_unit(punit);
//  } else { /* unitcount > 1 */
//    int i;
//    if (!(unitcount > 1)) {
//      util.die("Error in kill_unit, unitcount is %i", unitcount);
//    }
//    /* initialize */
//    for (i = 0; i<Shared_H.MAX_NUM_PLAYERS+Shared_H.MAX_NUM_BARBARIANS; i++) {
//      num_killed[i] = 0;
//    }
//
//    /* count killed units */
//    unit_list_iterate(punit.tile.units, vunit)
//      if (pplayers_at_war(pkiller.unit_owner(), vunit.unit_owner()))
//	num_killed[vunit.owner]++;
//    }
//
//    /* inform the owners */
//    for (i = 0; i<Shared_H.MAX_NUM_PLAYERS+Shared_H.MAX_NUM_BARBARIANS; i++) {
//      if (num_killed[i]>0) {
//	Plrhand.notify_player_ex(get_player(i), punit.tile, E_UNIT_LOST,
//			 PL("Game: You lost %d unit to an attack " +
//			     "from %s's %s%s.",
//			     "Game: You lost %d units to an attack " +
//			     "from %s's %s%s.",
//			     num_killed[i]), num_killed[i],
//			 destroyer.name, unit_name(pkiller.type),
//			 loc_str);
//      }
//    }
//
//    /* remove the units */
//    for (unit punit2 : punit.tile.units.data) {
//      if (pplayers_at_war(pkiller.unit_owner(), punit2.unit_owner())) {
//	Plrhand.notify_player_ex(punit2.unit_owner(), 
//			 punit2.tile, E_UNIT_LOST,
//			 ("Game: %s lost to an attack" +
//			   " from %s's %s."),
//			 punit2.unit_type().name, destroyer.name,
//			 unit_name(pkiller.type));
//
//        Gamelog.gamelog(GAMELOG_UNITLOSS, punit2, destroyer);
//	wipe_unit_spec_safe(punit2, false);
//      }
//    }
//    }
//  }
//}
//
///**************************************************************************
//  Package a unit_info packet.  This packet contains basically all
//  information about a unit.
//**************************************************************************/
//void package_unit(unit punit, packet_unit_info packet)
//{
//  packet.id = punit.id;
//  packet.owner = punit.owner;
//  packet.x = punit.tile.x;
//  packet.y = punit.tile.y;
//  packet.homecity = punit.homecity;
//  packet.veteran = punit.veteran;
//  packet.type = punit.type;
//  packet.movesleft = punit.moves_left;
//  packet.hp = punit.hp;
//  packet.activity = punit.activity;
//  packet.activity_count = punit.activity_count;
//  packet.unhappiness = punit.unhappiness;
//  packet.upkeep = punit.upkeep;
//  packet.upkeep_food = punit.upkeep_food;
//  packet.upkeep_gold = punit.upkeep_gold;
//  packet.ai = punit.ai.control;
//  packet.fuel = punit.fuel;
//  if (punit.goto_tile) {
//    packet.goto_dest_x = punit.goto_tile.x;
//    packet.goto_dest_y = punit.goto_tile.y;
//  } else {
//    packet.goto_dest_x = 255;
//    packet.goto_dest_y = 255;
//    assert(!is_normal_map_pos(255, 255));
//  }
//  packet.activity_target = punit.activity_target;
//  packet.paradropped = punit.paradropped;
//  packet.connecting = false;
//  packet.done_moving = punit.done_moving;
//  if (punit.transported_by == -1) {
//    packet.transported = false;
//    packet.transported_by = 0;
//  } else {
//    packet.transported = true;
//    packet.transported_by = punit.transported_by;
//  }
//  packet.occupy = get_transporter_occupancy(punit);
//  packet.has_orders = punit.has_orders;
//  if (punit.has_orders) {
//    int i;
//
//    packet.orders_length = punit.orders.length;
//    packet.orders_index = punit.orders.index;
//    packet.orders_repeat = punit.orders.repeat;
//    packet.orders_vigilant = punit.orders.vigilant;
//    for (i = 0; i < punit.orders.length; i++) {
//      packet.orders[i] = punit.orders.list[i].order;
//      packet.orders_dirs[i] = punit.orders.list[i].dir;
//      packet.orders_activities[i] = punit.orders.list[i].activity;
//    }
//  } else {
//    packet.orders_length = packet.orders_index = 0;
//    packet.orders_repeat = packet.orders_vigilant = false;
//    /* No need to initialize array. */
//  }
//}
//
///**************************************************************************
//  Package a short_unit_info packet.  This contains a limited amount of
//  information about the unit, and is sent to players who shouldn't know
//  everything (like the unit's owner's enemies).
//**************************************************************************/
//void package_short_unit(unit punit,
//			packet_unit_short_info packet,
//			enum unit_info_use packet_use,
//			int info_city_id, boolean new_serial_num)
//{
//  static unsigned int serial_num = 0;
//
//  /* a 16-bit unsigned number, never zero */
//  if (new_serial_num) {
//    serial_num = (serial_num + 1) & 0xFFFF;
//    if (serial_num == 0) {
//      serial_num++;
//    }
//  }
//  packet.serial_num = serial_num;
//  packet.packet_use = packet_use;
//  packet.info_city_id = info_city_id;
//
//  packet.id = punit.id;
//  packet.owner = punit.owner;
//  packet.x = punit.tile.x;
//  packet.y = punit.tile.y;
//  packet.veteran = punit.veteran;
//  packet.type = punit.type;
//  packet.hp = punit.hp;
//  packet.occupied = (get_transporter_occupancy(punit) > 0);
//  if (punit.activity == ACTIVITY_EXPLORE
//      || punit.activity == unit_activity.ACTIVITY_GOTO) {
//    packet.activity = unit_activity.ACTIVITY_IDLE;
//  } else {
//    packet.activity = punit.activity;
//  }
//
//  /* Transported_by information is sent to the client even for units that
//   * aren't fully known.  Note that for non-allied players, any transported
//   * unit can't be seen at all.  For allied players we have to know if
//   * transporters have room in them so that we can load units properly. */
//  if (punit.transported_by == -1) {
//    packet.transported = false;
//    packet.transported_by = 0;
//  } else {
//    packet.transported = true;
//    packet.transported_by = punit.transported_by;
//  }
//
//  packet.goes_out_of_sight = false;
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void unit_goes_out_of_sight(player pplayer, unit punit)
//{
//  struct packet_unit_short_info packet;
//
//  if (punit.unit_owner() == pplayer) {
//    /* The unit is about to util.die. No need to send an info. */
//  } else {
//    memset(&packet, 0, sizeof(packet));
//    packet.id = punit.id;
//    packet.goes_out_of_sight = true;
//    lsend_packet_unit_short_info(&pplayer.connections, &packet);
//  }
//}

	/***************************************************************************
	 * Send the unit into to those connections in dest which can see the units
	 * at it's position, or the specified (x,y) (if different). Eg, use x and y
	 * as where the unit came from, so that the info can be sent if the other
	 * players can see either the target or destination tile. dest = null means
	 * all connections (Game.game.game_connections)
	 **************************************************************************/
	public static void send_unit_info_to_onlookers(Speclists<Connection> dest, unit punit,
			tile ptile, boolean remove_unseen)
	{
		// struct packet_unit_info info;
		// struct packet_unit_short_info sinfo;
		// boolean new_information_for_enemy = false;

		// if (!dest) {
		// dest = &Game.game.game_connections;
		// }

		// /*
		// * maybe the wrong position for that, but this is the lowlevel
		// function
		// * where we check if we have to increase timeout, or remove_turn_done
		// */

		// package_unit(punit, &info);
		// package_short_unit(punit, &sinfo, UNIT_INFO_IDENTITY, false, false);

		// conn_list_iterate(*dest, pconn) {
		// player pplayer = pconn.player;

		// if ((!pplayer && pconn.observer)
		// || (pplayer && pplayer.player_no == punit.owner)) {
		// send_packet_unit_info(pconn, &info);
		// } else if (pplayer) {
		// boolean see_in_old;
		// boolean see_in_new = can_player_see_unit_at(pplayer, punit,
		// punit.tile);

		// if (punit.tile == ptile) {
		// /* This is not about movement */
		// see_in_old = see_in_new;
		// } else {
		// see_in_old = can_player_see_unit_at(pplayer, punit, ptile);
		// }

		// if (see_in_new || see_in_old) {
		// /* First send movement */
		// send_packet_unit_short_info(pconn, &sinfo);

		// if (!see_in_new) {
		// /* Then remove unit if necessary */
		// unit_goes_out_of_sight(pplayer, punit);
		// }
		// if (pplayers_at_war(pplayer,punit.unit_owner())
		// && !pplayer.ai.control) {
		// /*
		// * increase_timeout_because_unit_moved(pplayer) possible
		// * here
		// */
		// new_information_for_enemy = true;
		// }
		// } else {
		// if (remove_unseen) {
		// dsend_packet_unit_remove(pconn, punit.id);
		// }
		// }
		// }
		// } }

		// if (Game.game.timeout != 0 && new_information_for_enemy){
		//		increase_timeout_because_unit_moved();
		//		}

	}

	/***************************************************************************
	 * send the unit to the players who need the info dest = null means all
	 * connections (Game.game.game_connections)
	 **************************************************************************/
	public static void send_unit_info(player dest, unit punit)
	{
		Speclists<Connection> conn_dest = (dest!=null ? dest.connections
				: Game.game.game_connections);
		send_unit_info_to_onlookers(conn_dest, punit, punit.tile, false);
	}
	// /**************************************************************************
//  For each specified connections, send information about all the units
//  known to that player/conn.
//**************************************************************************/
//void send_all_known_units(Speclists<Connection> dest)
//{
//  int p;
//  
//  conn_list_do_buffer(dest);
//  conn_list_iterate(*dest, pconn) {
//    player pplayer = pconn.player;
//    if (!pconn.player && !pconn.observer) {
//      continue;
//    }
//    for(p=0; p<Game.game.nplayers; p++) { /* send the players units */
//      player unitowner = &Game.game.players[p];
//      for (unit punit : unitowner.units.data) {
//	if (!pplayer
//	    || Maphand.map_is_known_and_seen(punit.tile, pplayer)) {
//	  send_unit_info_to_onlookers(&pconn.self, punit,
//				      punit.tile, false);
//	}
//      }
//      }
//    }
//  }
//  }
//  conn_list_do_unbuffer(dest);
//  flush_packets();
//}
//
//
///**************************************************************************
//For all units which are transported by the given unit and that are
//currently idle, sentry them.
//**************************************************************************/
//static void sentry_transported_idle_units(unit ptrans)
//{
//  tile ptile = ptrans.tile;
//
//  for (unit pcargo : ptile.units.data) {
//    if (pcargo.transported_by == ptrans.id
//	&& pcargo.id != ptrans.id
//	&& pcargo.activity == unit_activity.ACTIVITY_IDLE) {
//      pcargo.activity = ACTIVITY_SENTRY;
//      send_unit_info(pcargo.unit_owner(), pcargo);
//    }
//  } }
//}
//
///**************************************************************************
//  Nuke a square: 1) remove all units on the square, and 2) halve the 
//  size of the city on the square.
//
//  If it isn't a city square or an ocean square then with 50% chance add 
//  some fallout, then notify the client about the changes.
//**************************************************************************/
//static void do_nuke_tile(player pplayer, tile ptile)
//{
//  city pcity = map_get_city(ptile);
//
//  for (unit punit : ptile.units.data) {
//    Plrhand.notify_player_ex(punit.unit_owner(), ptile, E_UNIT_LOST,
//		     "Game: Your %s was nuked by %s.",
//		     unit_name(punit.type),
//		     pplayer == punit.unit_owner() ? "yourself" : pplayer.name);
//    if (punit.unit_owner() != pplayer) {
//      Plrhand.notify_player_ex(pplayer,
//		       ptile, E_UNIT_WIN,
//		       "Game: %s's %s was nuked.",
//		       punit.unit_owner().name,
//		       unit_name(punit.type));
//    }
//    wipe_unit_spec_safe(punit, false);
//  } }
//
//  if (pcity) {
//    Plrhand.notify_player_ex(City.city_owner(pcity),
//		     ptile, E_CITY_NUKED,
//		     "Game: %s was nuked by %s.",
//		     pcity.name,
//		     pplayer == City.city_owner(pcity) ? "yourself" : pplayer.name);
//
//    if (City.city_owner(pcity) != pplayer) {
//      Plrhand.notify_player_ex(pplayer,
//		       ptile, E_CITY_NUKED,
//		       "Game: You nuked %s.",
//		       pcity.name);
//    }
//
//    city_reduce_size(pcity, pcity.size / 2);
//  }
//
//  if (!Terrain_H.is_ocean(ptile.terrain) && Rand.myrand(2) == 1) {
//    if (Game.game.rgame.nuke_contamination == CONTAMINATION_POLLUTION) {
//      if (!Map.map_has_special(ptile, S_POLLUTION)) {
//	map_set_special(ptile, S_POLLUTION);
//	Maphand.update_tile_knowledge(ptile);
//      }
//    } else {
//      if (!Map.map_has_special(ptile, S_FALLOUT)) {
//	map_set_special(ptile, S_FALLOUT);
//	Maphand.update_tile_knowledge(ptile);
//      }
//    }
//  }
//}
//
///**************************************************************************
//  Nuke all the squares in a 3x3 square around the center of the explosion
//  pplayer is the player that caused the explosion. You lose reputation
//  each time.
//**************************************************************************/
//void do_nuclear_explosion(player pplayer, tile ptile)
//{
//  for(tile ptile1: util.square_tile_iterate(ptile, 1)) {
//    do_nuke_tile(pplayer, ptile1);
//  }
//
//  /* Give reputation penalty to nuke users */
//  pplayer.reputation = MAX(pplayer.reputation - REPUTATION_LOSS_NUKE, 0);
//  Plrhand.send_player_info(pplayer, null);
//
//  notify_conn_ex(&Game.game.game_connections, ptile, E_NUKE,
//		 "Game: %s detonated a nuke!", pplayer.name);
//}
//
///**************************************************************************
//Move the unit if possible 
//  if the unit has less moves than it costs to enter a square, we roll the dice:
//  1) either succeed
//  2) or have it's moves set to 0
//  a unit can always move atleast once
//**************************************************************************/
// boolean try_move_unit(unit punit, tile dst_tile)
//{
//  if (Rand.myrand(1 + map_move_cost(punit, dst_tile)) > punit.moves_left
//      && punit.moves_left<punit.move_rate()) {
//    punit.moves_left=0;
//    send_unit_info(punit.unit_owner(), punit);
//  }
//  return punit.moves_left > 0;
//}
//
///**************************************************************************
//  go by airline, if both cities have an airport and neither has been used this
//  turn the unit will be transported by it and have it's moves set to 0
//**************************************************************************/
//boolean do_airline(unit punit, city city2)
//{
//  tile src_tile = punit.tile;
//  city city1 = src_tile.city;
//
//  if (!city1)
//    return false;
//  if (!unit_can_airlift_to(punit, city2))
//    return false;
//  if (get_transporter_occupancy(punit) > 0) {
//    return false;
//  }
//  city1.airlift = false;
//  city2.airlift = false;
//
//  Plrhand.notify_player_ex(punit.unit_owner(), city2.tile, event_type.E_NOEVENT,
//		   "Game: %s transported succesfully.",
//		   unit_name(punit.type));
//
//  () move_unit(punit, city2.tile, punit.moves_left);
//
//  /* airlift fields have changed. */
//  send_city_info(City.city_owner(city1), city1);
//  send_city_info(City.city_owner(city2), city2);
//
//  return true;
//}
//
///**************************************************************************
//  Returns whether the drop was made or not. Note that it also returns 1 
//  in the case where the drop was succesful, but the unit was killed by
//  barbarians in a hut.
//**************************************************************************/
//boolean do_paradrop(unit punit, tile ptile)
//{
//  player pplayer = punit.unit_owner();
//
//  if (!unit_flag(punit, F_PARATROOPERS)) {
//    Plrhand.notify_player_ex(pplayer, punit.tile, event_type.E_NOEVENT,
//                     "Game: This unit type can not be paradropped.");
//    return false;
//  }
//
//  if (!can_unit_paradrop(punit)) {
//    return false;
//  }
//
//  if (get_transporter_occupancy(punit) > 0) {
//    Plrhand.notify_player_ex(pplayer, punit.tile, event_type.E_NOEVENT,
//		     "Game: You cannot paradrop a transporter unit.");
//  }
//
//  if (!Maphand.map_is_known(ptile, pplayer)) {
//    Plrhand.notify_player_ex(pplayer, ptile, event_type.E_NOEVENT,
//                     "Game: The destination location is not known.");
//    return false;
//  }
//
//  if (Terrain_H.is_ocean(map_get_player_tile(ptile, pplayer).terrain)
//      && is_ground_unit(punit)) {
//    Plrhand.notify_player_ex(pplayer, ptile, event_type.E_NOEVENT,
//                     "Game: This unit cannot paradrop into ocean.");
//    return false;    
//  }
//
//  if (Maphand.map_is_known_and_seen(ptile, pplayer)
//      && ((ptile.city
//	  && pplayers_non_attack(pplayer, City.city_owner(ptile.city)))
//      || is_non_attack_unit_tile(ptile, pplayer))) {
//    Plrhand.notify_player_ex(pplayer, ptile, event_type.E_NOEVENT,
//                     "Game: Cannot attack unless you declare war first.");
//    return false;    
//  }
//
//  {
//    int range = punit.unit_type().paratroopers_range;
//    int distance = Map.real_map_distance(punit.tile, ptile);
//    if (distance > range) {
//      Plrhand.notify_player_ex(pplayer, ptile, event_type.E_NOEVENT,
//                       ("Game: The distance to the target (%i) " +
//                         "is greater than the unit's range (%i)."),
//                       distance, range);
//      return false;
//    }
//  }
//
//  if (Terrain_H.is_ocean(ptile.terrain)
//      && is_ground_unit(punit)) {
//    int srange = punit.unit_type().vision_range;
//
//    Maphand.show_area(pplayer, ptile, srange);
//
//    Plrhand.notify_player_ex(pplayer, ptile, E_UNIT_LOST,
//                     ("Game: Your %s paradropped into the ocean " +
//                       "and was lost."),
//                     punit.unit_type().name);
//    server_remove_unit(punit);
//    return true;
//  }
//
//  if ((ptile.city && pplayers_non_attack(pplayer, City.city_owner(ptile.city)))
//      || Unit.is_non_allied_unit_tile(ptile, pplayer)) {
//    int srange = punit.unit_type().vision_range;
//    Maphand.show_area(pplayer, ptile, srange);
//    maybe_make_contact(ptile, pplayer);
//    Plrhand.notify_player_ex(pplayer, ptile, E_UNIT_LOST_ATT,
//                     ("Game: Your %s was killed by enemy units at the " +
//                       "paradrop destination."),
//                     punit.unit_type().name);
//    server_remove_unit(punit);
//    return true;
//  }
//
//  /* All ok */
//  {
//    int move_cost = punit.unit_type().paratroopers_mr_sub;
//    punit.paradropped = true;
//    return move_unit(punit, ptile, move_cost);
//  }
//}
//
///**************************************************************************
//  Get gold from entering a hut.
//**************************************************************************/
//static void hut_get_gold(unit punit, int cred)
//{
//  player pplayer = punit.unit_owner();
//  Plrhand.notify_player_ex(pplayer, punit.tile, E_HUT_GOLD,
//		   "Game: You found %d gold.", cred);
//  pplayer.economic.gold += cred;
//}
//
///**************************************************************************
//  Get a tech from entering a hut.
//**************************************************************************/
//static void hut_get_tech(unit punit)
//{
//  player pplayer = punit.unit_owner();
//  Tech_Type_id new_tech;
//  final String tech_name;
//  
//  new_tech = give_random_free_tech(pplayer);
//
//  tech_name = get_tech_name(pplayer, new_tech);
//  Plrhand.notify_player_ex(pplayer, punit.tile, E_HUT_TECH,
//		   "Game: You found %s in ancient scrolls of wisdom.",
//		   tech_name);
//  Gamelog.gamelog(GAMELOG_TECH, pplayer, null, new_tech);
//  notify_embassies(pplayer, null, ("Game: The %s have acquired %s" +
//				    " from ancient scrolls of wisdom."),
//		   Nation.get_nation_name_plural(pplayer.nation), tech_name);
//}
//
///**************************************************************************
//  Get a mercenary unit from entering a hut.
//**************************************************************************/
//static void hut_get_mercenaries(unit punit)
//{
//  player pplayer = punit.unit_owner();
//  
//  Plrhand.notify_player_ex(pplayer, punit.tile, E_HUT_MERC,
//		   "Game: A band of friendly mercenaries joins your cause.");
//   create_unit(pplayer, punit.tile,
//		     find_a_unit_type(L_HUT, L_HUT_TECH), false,
//		     punit.homecity, -1);
//}
//
///**************************************************************************
//  Get barbarians from hut, unless close to a city.
//  Unit may util.die: returns 1 if unit is alive after, or 0 if it was killed.
//**************************************************************************/
//static boolean hut_get_barbarians(unit punit)
//{
//  player pplayer = punit.unit_owner();
//  boolean ok = true;
//
//  if (city_exists_within_city_radius(punit.tile, true)
//      || unit_flag(punit, F_GAMELOSS)) {
//    Plrhand.notify_player_ex(pplayer, punit.tile, E_HUT_BARB_CITY_NEAR,
//		     "Game: An abandoned village is here.");
//  } else {
//    /* save coords and type in case unit util.dies */
//    tile unit_tile = punit.tile;
//    int type = punit.type;
//
//    ok = unleash_barbarians(unit_tile);
//
//    if (ok) {
//      Plrhand.notify_player_ex(pplayer, unit_tile, E_HUT_BARB,
//		       "Game: You have unleashed a horde of barbarians!");
//    } else {
//      Plrhand.notify_player_ex(pplayer, unit_tile, E_HUT_BARB_KILLED,
//		       "Game: Your %s has been killed by barbarians!",
//		       unit_name(type));
//    }
//  }
//  return ok;
//}
//
///**************************************************************************
//  Get new city from hut, or settlers (nomads) if terrain is poor.
//**************************************************************************/
//static void hut_get_city(unit punit)
//{
//  player pplayer = punit.unit_owner();
//
//  if (city_can_be_built_here(punit.tile, null)) {
//    Plrhand.notify_player_ex(pplayer, punit.tile, E_HUT_CITY,
//		     "Game: You found a friendly city.");
//    create_city(pplayer, punit.tile,
//		city_name_suggestion(pplayer, punit.tile));
//
//    if (unit_flag(punit, F_CITIES) || unit_flag(punit, F_SETTLERS)) {
//      /* In case city was found during autosettler activities */
//      initialize_infrastructure_cache(pplayer);
//    }
//
//    /* Init ai.choice. Handling ferryboats might use it. */
//    init_choice(&punit.tile.city.ai.choice);
//
//  } else {
//    Plrhand.notify_player_ex(pplayer, punit.tile, E_HUT_SETTLER,
//		     ("Game: Friendly nomads are impressed by you," +
//		       " and join you."));
//     create_unit(pplayer, punit.tile, Unittype_P.get_role_unit(F_CITIES,0),
//		0, punit.homecity, -1);
//  }
//}
//
///**************************************************************************
//  Return 1 if unit is alive, and 0 if it was killed
//**************************************************************************/
//static boolean unit_enter_hut(unit punit)
//{
//  player pplayer = punit.unit_owner();
//  boolean ok = true;
//  int hut_chance = Rand.myrand(12);
//  
//  if (Game.game.rgame.hut_overflight==OVERFLIGHT_NOTHING && is_air_unit(punit)) {
//    return ok;
//  }
//
//  Map.map_clear_special(punit.tile, Terrain_H.S_HUT);
//  Maphand.update_tile_knowledge(punit.tile);
//
//  if (Game.game.rgame.hut_overflight==OVERFLIGHT_FRIGHTEN && is_air_unit(punit)) {
//    Plrhand.notify_player_ex(pplayer, punit.tile, event_type.E_NOEVENT,
//		     ("Game: Your overflight frightens the tribe;" +
//		       " they scatter in terror."));
//    return ok;
//  }
//  
//  /* AI with H_LIMITEDHUTS only gets 25 gold (or barbs if unlucky) */
//  if (pplayer.ai.control && ai_handicap(pplayer, H_LIMITEDHUTS) 
//      && hut_chance != 10) {
//    hut_chance = 0;
//  }
//
//  switch (hut_chance) {
//  case 0:
//    hut_get_gold(punit, 25);
//    break;
//  case 1: case 2: case 3:
//    hut_get_gold(punit, 50);
//    break;
//  case 4:
//    hut_get_gold(punit, 100);
//    break;
//  case 5: case 6: case 7:
//    hut_get_tech(punit);
//    break;
//  case 8: case 9:
//    hut_get_mercenaries(punit);
//    break;
//  case 10:
//    ok = hut_get_barbarians(punit);
//    break;
//  case 11:
//    hut_get_city(punit);
//    break;
//  }
//  Plrhand.send_player_info(pplayer, pplayer);       /* eg, gold */
//  return ok;
//}
//
///****************************************************************************
//  Put the unit onto the transporter.  Don't do any other work.
//****************************************************************************/
//static void put_unit_onto_transporter(unit punit, unit ptrans)
//{
//  /* In the future we may updated ptrans.occupancy. */
//  assert(punit.transported_by == -1);
//  punit.transported_by = ptrans.id;
//}
//
///****************************************************************************
//  Put the unit onto the transporter.  Don't do any other work.
//****************************************************************************/
//static void pull_unit_from_transporter(unit punit,
//				       unit ptrans)
//{
//  /* In the future we may updated ptrans.occupancy. */
//  assert(punit.transported_by == ptrans.id);
//  punit.transported_by = -1;
//}
//
///****************************************************************************
//  Put the unit onto the transporter, and tell everyone.
//****************************************************************************/
//void load_unit_onto_transporter(unit punit, unit ptrans)
//{
//  put_unit_onto_transporter(punit, ptrans);
//  send_unit_info(null, punit);
//  send_unit_info(null, ptrans);
//}
//
///****************************************************************************
//  Pull the unit off of the transporter, and tell everyone.
//****************************************************************************/
//void unload_unit_from_transporter(unit punit)
//{
//  unit ptrans = find_unit_by_id(punit.transported_by);
//
//  pull_unit_from_transporter(punit, ptrans);
//  send_unit_info(null, punit);
//  send_unit_info(null, ptrans);
//}
//
///*****************************************************************
//Will wake up any neighboring enemy sentry units or patrolling units
//*****************************************************************/
//static void wakeup_neighbor_sentries(unit punit)
//{
//  /* There may be sentried units with a sightrange>3, but we don't
//     wake them up if the punit is farther away than 3. */
//  for(tile ptile: util.square_tile_iterate(punit.tile, 3)) {
//    for (unit penemy : ptile.units.data) {
//      int range;
//      enum unit_move_type move_type = penemy.unit_type().move_type;
//      int terrain = ptile.terrain;
//
//      if (Map.map_has_special(ptile, S_FORTRESS)
//	  && unit_profits_of_watchtower(penemy))
//	range = get_watchtower_vision(penemy);
//      else
//	range = penemy.unit_type().vision_range;
//      
//      if (!pplayers_allied(punit.unit_owner(), penemy.unit_owner())
//	  && penemy.activity == ACTIVITY_SENTRY
//	  && range >= Map.real_map_distance(punit.tile, ptile)
//	  && can_player_see_unit(penemy.unit_owner(), punit)
//	  /* on board transport; don't awaken */
//	  && !(move_type == LAND_MOVING && Terrain_H.is_ocean(terrain))) {
//	set_unit_activity(penemy, unit_activity.ACTIVITY_IDLE);
//	send_unit_info(null, penemy);
//      }
//    } }
//  }
//
//  /* Wakeup patrolling units we bump into.
//     We do not wakeup units further away than 3 squares... */
//  for(tile ptile: util.square_tile_iterate(punit.tile, 3)) {
//    for (unit ppatrol : ptile.units.data) {
//      if (punit != ppatrol
//	  && unit_has_orders(ppatrol)
//	  && ppatrol.orders.vigilant) {
//	if (maybe_cancel_patrol_due_to_enemy(ppatrol)) {
//	  Plrhand.notify_player_ex(ppatrol.unit_owner(), ppatrol.tile, E_UNIT_ORDERS, 
//			   ("Game: Your %s cancelled patrol order because it " +
//			     "encountered a foreign unit."),
//			   unit_name(ppatrol.type));
//	}
//      }
//    } }
//  }
//}
//
///**************************************************************************
//Does: 1) updates  the units homecity and the city it enters/leaves (the
//         cities happiness varies). This also takes into account if the
//	 unit enters/leaves a fortress.
//      2) handles any huts at the units destination.
//      3) updates adjacent cities' unavailable tiles.
//
//FIXME: Sometimes it is not neccesary to send cities because the goverment
//       doesn't care if a unit is away or not.
//**************************************************************************/
//static void handle_unit_move_consequences(unit punit,
//					  tile src_tile,
//					  tile dst_tile)
//{
//  city fromcity = map_get_city(src_tile);
//  city tocity = map_get_city(dst_tile);
//  city homecity = null;
//  player pplayer = punit.unit_owner();
//  /*  government g = get_gov_pplayer(pplayer);*/
//  boolean refresh_homecity = false;
//
//  if (punit.homecity != 0)
//    homecity = find_city_by_id(punit.homecity);
//
//  if (tocity)
//    handle_unit_enter_city(punit, tocity);
//
//  /* We only do this for non-AI players to now make sure the AI turns
//     doesn't take too long. Perhaps we should make a special refresh_city
//     functions that only refreshed happiness. */
//  if (!pplayer.ai.control) {
//    /* might have changed owners or may be destroyed */
//    tocity = map_get_city(dst_tile);
//
//    if (tocity) { /* entering a city */
//      if (tocity.owner == punit.owner) {
//	if (tocity != homecity) {
//	  city_refresh(tocity);
//	  send_city_info(pplayer, tocity);
//	}
//      }
//
//      if (homecity) {
//        refresh_homecity = true;
//      }
//    }
//
//    if (fromcity) { /* leaving a city */
//      if (homecity) {
//	refresh_homecity = true;
//      }
//      if (fromcity != homecity && fromcity.owner == punit.owner) {
//	city_refresh(fromcity);
//	send_city_info(pplayer, fromcity);
//      }
//    }
//
//    /* entering/leaving a fortress or friendly territory */
//    if (homecity) {
//      if ((Game.game.happyborders > 0 && src_tile.owner != dst_tile.owner)
//          ||
//	  (Map.map_has_special(dst_tile, S_FORTRESS)
//	   && is_friendly_city_near(punit.unit_owner(), dst_tile))
//	  ||
//          (Map.map_has_special(src_tile, S_FORTRESS) 
//	   && is_friendly_city_near(punit.unit_owner(), src_tile))) {
//        refresh_homecity = true;
//      }
//    }
//    
//    if (refresh_homecity) {
//      city_refresh(homecity);
//      send_city_info(pplayer, homecity);
//    }
//  }
//
//
//  /* The unit block different tiles of adjacent enemy cities before and
//     after. Update the relevant cities. */
//
//  /* First check cities near the source. */
//  map_city_radius_iterate(src_tile, tile1) {
//    city pcity = map_get_city(tile1);
//
//    if (pcity && update_city_tile_status_map(pcity, src_tile)) {
//      auto_arrange_workers(pcity);
//      send_city_info(null, pcity);
//    }
//  } map_city_radius_iterate_end;
//  /* Then check cities near the destination. */
//  map_city_radius_iterate(dst_tile, tile1) {
//    city pcity = map_get_city(tile1);
//    if (pcity && update_city_tile_status_map(pcity, dst_tile)) {
//      auto_arrange_workers(pcity);
//      send_city_info(null, pcity);
//    }
//  } map_city_radius_iterate_end;
//  sync_cities();
//}
//
///**************************************************************************
//Check if the units activity is legal for a move , and reset it if it isn't.
//**************************************************************************/
//static void check_unit_activity(unit punit)
//{
//  if (punit.activity != unit_activity.ACTIVITY_IDLE
//      && punit.activity != ACTIVITY_SENTRY
//      && punit.activity != ACTIVITY_EXPLORE
//      && punit.activity != unit_activity.ACTIVITY_GOTO) {
//    set_unit_activity(punit, unit_activity.ACTIVITY_IDLE);
//  }
//}
//
///**************************************************************************
//  Moves a unit. No checks whatsoever! This is meant as a practical 
//  function for other functions, like do_airline, which do the checking 
//  themselves.
//
//  If you move a unit you should always use this function, as it also sets 
//  the transported_by unit field correctly. take_from_land is only relevant 
//  if you have set transport_units. Note that the src and dest need not be 
//  adjacent.
//**************************************************************************/
//boolean move_unit(unit punit, tile pdesttile, int move_cost)
//{
//  player pplayer = punit.unit_owner();
//  tile psrctile = punit.tile;
//  city pcity;
//  unit ptransporter = null;
//    
//  conn_list_do_buffer(&pplayer.connections);
//
//  /* Transporting units. We first make a list of the units to be moved and
//     then insert them again. The way this is done makes sure that the
//     units stay in the same order. */
//  if (Unit.get_transporter_capacity(punit) > 0) {
//    Speclists<unit> cargo_units;
//
//    /* First make a list of the units to be moved. */
//    unit_list_init(&cargo_units);
//    for (unit pcargo : psrctile.units.data) {
//      if (pcargo.transported_by == punit.id) {
//	unit_list_unlink(&psrctile.units, pcargo);
//	unit_list_insert(&cargo_units, pcargo);
//      }
//    } }
//
//    /* Insert them again. */
//    for (unit pcargo : cargo_units.data) {
//      unfog_area(pcargo.unit_owner(), pdesttile, pcargo.unit_type().vision_range);
//
//      /* Silently free orders since they won't be applicable anymore. */
//      free_unit_orders(pcargo);
//
//      pcargo.tile = pdesttile;
//
//      unit_list_insert(&pdesttile.units, pcargo);
//      check_unit_activity(pcargo);
//      send_unit_info_to_onlookers(null, pcargo, psrctile, false);
//      fog_area(pcargo.unit_owner(), psrctile, pcargo.unit_type().vision_range);
//      handle_unit_move_consequences(pcargo, psrctile, pdesttile);
//    } }
//    unit_list_unlink_all(&cargo_units);
//  }
//
//  /* We first unfog the destination, then move the unit and send the
//     move, and then fog the old territory. This means that the player
//     gets a chance to see the newly explored territory while the
//     client moves the unit, and both areas are visible during the
//     move */
//
//  /* Enhance vision if unit steps into a fortress */
//  if (unit_profits_of_watchtower(punit)
//      && Map.tile_has_special(pdesttile, S_FORTRESS)) {
//    unfog_area(pplayer, pdesttile, get_watchtower_vision(punit));
//  } else {
//    unfog_area(pplayer, pdesttile, punit.unit_type().vision_range);
//  }
//
//  unit_list_unlink(&psrctile.units, punit);
//  punit.tile = pdesttile;
//  punit.moved = true;
//  if (punit.transported_by != -1) {
//    ptransporter = find_unit_by_id(punit.transported_by);
//    pull_unit_from_transporter(punit, ptransporter);
//  }
//  punit.moves_left = MAX(0, punit.moves_left - move_cost);
//  if (punit.moves_left == 0) {
//    punit.done_moving = true;
//  }
//  unit_list_insert(&pdesttile.units, punit);
//  check_unit_activity(punit);
//
//  /*
//   * Transporter info should be send first becouse this allow us get right
//   * update_menu effect in client side.
//   */
//  
//  /*
//   * Send updated information to anyone watching that transporter was unload
//   * cargo.
//   */
//  if (ptransporter) {
//    send_unit_info(null, ptransporter);
//  }
//  
//  /* Send updated information to anyone watching.  If the unit moves
//   * in or out of a city we update the 'occupied' field.  Note there may
//   * be cities at both src and dest under some rulesets. */
//  send_unit_info_to_onlookers(null, punit, psrctile, false);
//    
//  /* Special checks for ground units in the ocean. */
//  if (!can_unit_survive_at_tile(punit, pdesttile)) {
//    ptransporter = find_transporter_for_unit(punit, pdesttile);
//    if (ptransporter) {
//      put_unit_onto_transporter(punit, ptransporter);
//    }
//
//    /* Set activity to sentry if boarding a ship. */
//    if (ptransporter && !pplayer.ai.control && !unit_has_orders(punit)
//	&& !can_unit_exist_at_tile(punit, pdesttile)) {
//      set_unit_activity(punit, ACTIVITY_SENTRY);
//    }
//
//    /*
//     * Transporter info should be send first becouse this allow us get right
//     * update_menu effect in client side.
//     */
//    
//    /*
//     * Send updated information to anyone watching that transporter has cargo.
//     */
//    if (ptransporter) {
//      send_unit_info(null, ptransporter);
//    }
//
//    /*
//     * Send updated information to anyone watching that unit is on transport.
//     * All players without shared vison with owner player get
//     * REMOVE_UNIT package.
//     */
//    send_unit_info_to_onlookers(null, punit, punit.tile, true);
//  }
//  
//  if ((pcity = map_get_city(psrctile))) {
//    refresh_dumb_city(pcity);
//  }
//  if ((pcity = map_get_city(pdesttile))) {
//    refresh_dumb_city(pcity);
//  }
//
//  /* The hidden units might not have been previously revealed 
//   * because when we did the unfogging, the unit was still 
//   * at (src_x, src_y) */
//  reveal_hidden_units(pplayer, pdesttile);
//
//  if (unit_profits_of_watchtower(punit)
//      && Map.tile_has_special(psrctile, S_FORTRESS)) {
//    fog_area(pplayer, psrctile, get_watchtower_vision(punit));
//  } else {
//    fog_area(pplayer, psrctile, punit.unit_type().vision_range);
//  }
//
//  /* Remove hidden units (like submarines) which aren't seen anymore. */
//  for(tile tile1: util.square_tile_iterate(psrctile, 1)) {
//    for(player pplayer: Game.game.players){
//      /* We're only concerned with known, unfogged tiles which may contain
//       * hidden units that are no longer visible.  These units will not
//       * have been handled by the fog code, above. */
//      if (map_get_known(tile1, pplayer) == TILE_KNOWN) {
//        for (unit punit2 : tile1.units.data) {
//          if (punit2 != punit && !can_player_see_unit(pplayer, punit2)) {
//	    unit_goes_out_of_sight(pplayer, punit2);
//	  }
//        } }
//      }
//    }
//  }
//
//  handle_unit_move_consequences(punit, psrctile, pdesttile);
//  wakeup_neighbor_sentries(punit);
//  maybe_make_contact(pdesttile, punit.unit_owner());
//
//  conn_list_do_unbuffer(&pplayer.connections);
//
//  /* Note, an individual call to move_unit may leave things in an unstable
//   * state (e.g., negative transporter capacity) if more than one unit is
//   * being moved at a time (e.g., bounce unit) and they are not done in the
//   * right order.  This is probably not a bug. */
//
//  if (Map.map_has_special(pdesttile, Terrain_H.S_HUT)) {
//    return unit_enter_hut(punit);
//  } else {
//    return true;
//  }
//}
//
///**************************************************************************
//  Maybe cancel the goto if there is an enemy in the way
//**************************************************************************/
//static boolean maybe_cancel_goto_due_to_enemy(unit punit, 
//                                           tile ptile)
//{
//  player pplayer = punit.unit_owner();
//  
//  if (Unit.is_non_allied_unit_tile(ptile, pplayer) 
//      || is_non_allied_city_tile(ptile, pplayer)) {
//    return true;
//  }
//
//  return false;
//}
//
///**************************************************************************
//  Maybe cancel the patrol as there is an enemy near.
//
//  If you modify the wakeup range you should change it in
//  wakeup_neighbor_sentries() too.
//**************************************************************************/
//static boolean maybe_cancel_patrol_due_to_enemy(unit punit)
//{
//  boolean cancel = false;
//  int range;
//  player pplayer = punit.unit_owner();
//
//  if (Map.map_has_special(punit.tile, S_FORTRESS)
//      && unit_profits_of_watchtower(punit))
//    range = get_watchtower_vision(punit);
//  else
//    range = punit.unit_type().vision_range;
//  
//  for(tile ptile: util.square_tile_iterate(punit.tile, range)) {
//    unit penemy =
//	Unit.is_non_allied_unit_tile(ptile, pplayer);
//    dumb_city pdcity = map_get_player_tile(ptile, pplayer).city;
//
//    if ((penemy && can_player_see_unit(pplayer, penemy))
//	|| (pdcity && !pplayers_allied(pplayer, get_player(pdcity.owner))
//	    && pdcity.occupied)) {
//      cancel = true;
//      break;
//    }
//  }
//
//  if (cancel) {
//    handle_unit_activity_request(punit, unit_activity.ACTIVITY_IDLE);
//  }
//
//  return cancel;
//}
//
///****************************************************************************
//  Cancel orders for the unit.
//****************************************************************************/
//static void cancel_orders(unit punit, char *dbg_msg)
//{
//  free_unit_orders(punit);
//  send_unit_info(null, punit);
//  util.freelog(Log.LOG_DEBUG, "%s", dbg_msg);
//}
//
///****************************************************************************
//  Executes a unit's orders stored in punit.orders.  The unit is put on idle
//  if an action fails or if "patrol" is set and an enemy unit is encountered.
//
//  The return value will be true if the unit lives, false otherwise.  (This
//  function used to return a goto_result enumeration, declared in gotohand.h.
//  But this enumeration was never checked by the caller and just lead to
//  confusion.  All the caller really needs to know is if the unit lived or
//  util.died; everything else is handled internally within execute_orders.)
//
//  If the orders are repeating the loop starts over at the beginning once it
//  completes.  To avoid infinite loops on railroad we stop for this
//  turn when the unit is back where it started, even if it have moves left.
//
//  A unit will attack under orders only on its final action.
//****************************************************************************/
//boolean execute_orders(unit punit)
//{
//  tile dst_tile;
//  boolean res, last_order;
//  int unitid = punit.id;
//  player pplayer = punit.unit_owner();
//  int moves_made = 0;
//  enum unit_activity activity;
//
//  assert(unit_has_orders(punit));
//
//  if (punit.activity != unit_activity.ACTIVITY_IDLE) {
//    /* Unit's in the middle of an activity; wait for it to finish. */
//    punit.done_moving = true;
//    return true;
//  }
//
//  util.freelog(Log.LOG_DEBUG, "Executing orders for %s %d",
//	  unit_name(punit.type), punit.id);   
//
//  /* Any time the orders are canceled we should give the player a message. */
//
//  while (true) {
//    struct unit_order order;
//
//    if (punit.moves_left == 0) {
//      /* FIXME: this check won't work when actions take 0 MP. */
//      util.freelog(Log.LOG_DEBUG, "  stopping because of no more move points");
//      return true;
//    }
//
//    if (punit.done_moving) {
//      util.freelog(Log.LOG_DEBUG, "  stopping because we're done this turn");
//      return true;
//    }
//
//    if (punit.orders.vigilant && maybe_cancel_patrol_due_to_enemy(punit)) {
//      /* "Patrol" orders are stopped if an enemy is near. */
//      cancel_orders(punit, "  stopping because of nearby enemy");
//      Plrhand.notify_player_ex(pplayer, punit.tile, E_UNIT_ORDERS,
//		       ("Game: Orders for %s aborted as there " +
//			 "are units nearby."),
//		       unit_name(punit.type));
//      return true;
//    }
//
//    if (moves_made == punit.orders.length) {
//      /* For repeating orders, don't repeat more than once per turn. */
//      util.freelog(Log.LOG_DEBUG, "  stopping because we ran a round");
//      punit.done_moving = true;
//      send_unit_info(null, punit);
//      return true;
//    }
//    moves_made++;
//
//    last_order = (!punit.orders.repeat
//		  && punit.orders.index + 1 == punit.orders.length);
//
//    order = punit.orders.list[punit.orders.index];
//    if (last_order) {
//      /* Clear the orders before we engage in the move.  That way any
//       * has_orders checks will yield false and this will be treated as
//       * a normal move.  This is important: for instance a caravan goto
//       * will popup the caravan dialog on the last move only. */
//      free_unit_orders(punit);
//    }
//
//    /* Advance the orders one step forward.  This is needed because any
//     * updates sent to the client as a result of the action should include
//     * the new index value.  Note that we have to send_unit_info somewhere
//     * after this point so that the client is properly updated. */
//    punit.orders.index++;
//
//    switch (order.order) {
//    case ORDER_FULL_MP:
//      if (punit.moves_left != punit.move_rate()) {
//	/* If the unit doesn't have full MP then it just waits until the
//	 * next turn.  We assume that the next turn it will have full MP
//	 * (there's no check for that). */
//	punit.done_moving = true;
//	util.freelog(Log.LOG_DEBUG, "  waiting this turn");
//	send_unit_info(null, punit);
//      }
//      break;
//    case ORDER_ACTIVITY:
//      activity = order.activity;
//      if (!can_unit_do_activity(punit, activity)) {
//	cancel_orders(punit, "  orders canceled because of failed activity");
//	Plrhand.notify_player_ex(pplayer, punit.tile, E_UNIT_ORDERS,
//			 ("Game: Orders for %s aborted since they " +
//			   "give an invalid activity."),
//			 unit_name(punit.type));
//	return true;
//      }
//      punit.done_moving = true;
//      set_unit_activity(punit, activity);
//      send_unit_info(null, punit);
//      break;
//    case ORDER_MOVE:
//      /* Move unit */
//      if (!(dst_tile = mapstep(punit.tile, order.dir))) {
//	cancel_orders(punit, "  move order sent us to invalid location");
//	Plrhand.notify_player_ex(pplayer, punit.tile, E_UNIT_ORDERS,
//			 ("Game: Orders for %s aborted since they " +
//			   "give an invalid location."),
//			 unit_name(punit.type));
//	return true;
//      }
//
//      if (!last_order
//	  && maybe_cancel_goto_due_to_enemy(punit, dst_tile)) {
//	cancel_orders(punit, "  orders canceled because of enemy");
//	Plrhand.notify_player_ex(pplayer, punit.tile, E_UNIT_ORDERS,
//			 ("Game: Orders for %s aborted as there " +
//			   "are units in the way."),
//			 unit_name(punit.type));
//	return true;
//      }
//
//      util.freelog(Log.LOG_DEBUG, "  moving to %d,%d",
//	      dst_tile.x, dst_tile.y);
//      res = Unithand.handle_unit_move_request(punit, dst_tile, false, !last_order);
//      if (!player_find_unit_by_id(pplayer, unitid)) {
//	util.freelog(Log.LOG_DEBUG, "  unit util.died while moving.");
//	/* A player notification should already have been sent. */
//	return false;
//      }
//
//      if (res && !Map.same_pos(dst_tile, punit.tile)) {
//	/* Movement succeeded but unit didn't move. */
//	util.freelog(Log.LOG_DEBUG, "  orders resulted in combat.");
//	send_unit_info(null, punit);
//	return true;
//      }
//
//      if (!res && punit.moves_left > 0) {
//	/* Movement failed (ZOC, etc.) */
//	cancel_orders(punit, "  attempt to move failed.");
//	Plrhand.notify_player_ex(pplayer, punit.tile, E_UNIT_ORDERS,
//			 ("Game: Orders for %s aborted because of " +
//			   "failed move."),
//			 unit_name(punit.type));
//	return true;
//      }
//
//      if (!res && punit.moves_left == 0) {
//	/* Movement failed (not enough MP).  Keep this move around for
//	 * next turn. */
//	util.freelog(Log.LOG_DEBUG, "  orders move failed (out of MP).");
//	if (unit_has_orders(punit)) {
//	  punit.orders.index--;
//	} else {
//	  /* FIXME: If this was the last move, the orders will already have
//	   * been freed, so we have to add them back on.  This is quite a
//	   * hack; one problem is that the no-orders unit has probably
//	   * already had its unit info sent out to the client. */
//	  punit.has_orders = true;
//	  punit.orders.length = 1;
//	  punit.orders.index = 0;
//	  punit.orders.repeat = false;
//	  punit.orders.vigilant = false;
//	  punit.orders.list = fc_malloc(sizeof(order));
//	  punit.orders.list[0] = order;
//	}
//	send_unit_info(null, punit);
//	return true;
//      }
//
//      break;
//    case ORDER_LAST:
//      cancel_orders(punit, "  client sent invalid order!");
//      Plrhand.notify_player_ex(pplayer, punit.tile, E_UNIT_ORDERS,
//		       "Game: Your %s has invalid orders.",
//		       unit_name(punit.type));
//      return true;
//    }
//
//    if (last_order) {
//      assert(punit.has_orders == false);
//      util.freelog(Log.LOG_DEBUG, "  stopping because orders are complete");
//      return true;
//    }
//
//    if (punit.orders.index == punit.orders.length) {
//      assert(punit.orders.repeat);
//      /* Start over. */
//      util.freelog(Log.LOG_DEBUG, "  repeating orders.");
//      punit.orders.index = 0;
//    }
//  } /* end while */
//}
//
///**************************************************************************
//...
//**************************************************************************/
//int get_watchtower_vision(unit punit)
//{
//  int base_vision = punit.unit_type().vision_range;
//
//  assert(base_vision > 0);
//  assert(Game.game.watchtower_vision > 0);
//  assert(Game.game.watchtower_extra_vision >= 0);
//
//  return MAX(base_vision,
//	     MAX(Game.game.watchtower_vision,
//		 base_vision + Game.game.watchtower_extra_vision));
//}
//
///**************************************************************************
//...
//**************************************************************************/
//boolean unit_profits_of_watchtower(unit punit)
//{
//  return (is_ground_unit(punit)
//	  && player_knows_techs_with_flag(punit.unit_owner(),
//					  TF_WATCHTOWER));
//}
}