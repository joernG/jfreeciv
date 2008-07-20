package ai;

public class Advspace{

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
//#include <assert.h>
//
//#include "government.h"
//#include "packets.h"
//#include "spaceship.h"
//
//#include "spacerace.h"
//
//#include "advspace.h"
//
///*
// *  This borrows heavily from autoplace code already in the client source.
// *  AJS, 19990610
// */
//
//boolean ai_spaceship_autoplace(player pplayer, player_spaceship ship)
//{
//  enum spaceship_place_type type;
//  int num, i;
//  boolean retval = false;
//  
//  while (ship.modules > (ship.habitation + ship.life_support
//		       + ship.solar_panels)) {
//    
//    type =
//      (ship.habitation==0)   ? spaceship_place_type.SSHIP_PLACE_HABITATION :
//      (ship.life_support==0) ? spaceship_place_type.SSHIP_PLACE_HABITATION :
//      (ship.solar_panels==0) ? spaceship_place_type.SSHIP_PLACE_HABITATION :
//      ((ship.habitation < ship.life_support)
//       && (ship.solar_panels*2 >= ship.habitation + ship.life_support + 1))
//                              ? spaceship_place_type.SSHIP_PLACE_HABITATION :
//      (ship.solar_panels*2 < ship.habitation + ship.life_support)
//                              ? spaceship_place_type.SSHIP_PLACE_HABITATION :
//      (ship.life_support<ship.habitation)
//                              ? spaceship_place_type.SSHIP_PLACE_HABITATION :
//      ((ship.life_support <= ship.habitation)
//       && (ship.solar_panels*2 >= ship.habitation + ship.life_support + 1))
//                              ? spaceship_place_type.SSHIP_PLACE_HABITATION :
//                                spaceship_place_type.SSHIP_PLACE_HABITATION;
//
//    if (type == spaceship_place_type.SSHIP_PLACE_HABITATION) {
//      num = ship.habitation + 1;
//    } else if(type == spaceship_place_type.SSHIP_PLACE_HABITATION) {
//      num = ship.life_support + 1;
//    } else {
//      num = ship.solar_panels + 1;
//    }
//    assert(num <= player_spaceship.NUM_SS_MODULES / 3);
//
//    handle_spaceship_place(pplayer, type, num);
//    retval = true;
//  }
//  while (ship.components > ship.fuel + ship.propulsion) {
//    if (ship.fuel <= ship.propulsion) {
//      type = spaceship_place_type.SSHIP_PLACE_FUEL;
//      num = ship.fuel + 1;
//    } else {
//      type = spaceship_place_type.SSHIP_PLACE_PROPULSION;
//      num = ship.propulsion + 1;
//    }
//    handle_spaceship_place(pplayer, type, num);
//    retval = true;
//  }
//  while (ship.structurals > num_spaceship_structurals_placed(ship)) {
//    /* Want to choose which structurals are most important.
//       Else we first want to connect one of each type of module,
//       then all placed components, pairwise, then any remaining
//       modules, or else finally in numerical order.
//    */
//    int req = -1;
//    
//    if (!ship.structure[0]) {
//      /* if we don't have the first structural, place that! */
//      type = spaceship_place_type.SSHIP_PLACE_STRUCTURAL;
//      num = 0;
//      handle_spaceship_place(pplayer, type, num);
//    }
//    
//    if (ship.habitation >= 1
//	&& !ship.structure[Spaceship.modules_info[0].required]) {
//      req = Spaceship.modules_info[0].required;
//    } else if (ship.life_support >= 1
//	       && !ship.structure[Spaceship.modules_info[1].required]) {
//      req = Spaceship.modules_info[1].required;
//    } else if (ship.solar_panels >= 1
//	       && !ship.structure[Spaceship.modules_info[2].required]) {
//      req = Spaceship.modules_info[2].required;
//    } else {
//      int i;
//      for(i=0; i<player_spaceship.NUM_SS_COMPONENTS; i++) {
//	if ((i%2==0 && ship.fuel > (i/2))
//	    || (i%2==1 && ship.propulsion > (i/2))) {
//	  if (!ship.structure[Spaceship.components_info[i].required]) {
//	    req = Spaceship.components_info[i].required;
//	    break;
//	  }
//	}
//      }
//    }
//    if (req == -1) {
//      for(i=0; i<player_spaceship.NUM_SS_MODULES; i++) {
//	if ((i%3==0 && ship.habitation > (i/3))
//	    || (i%3==1 && ship.life_support > (i/3))
//	    || (i%3==2 && ship.solar_panels > (i/3))) {
//	  if (!ship.structure[Spaceship.modules_info[i].required]) {
//	    req = Spaceship.modules_info[i].required;
//	    break;
//	  }
//	}
//      }
//    }
//    if (req == -1) {
//      for(i=0; i<player_spaceship.NUM_SS_STRUCTURALS; i++) {
//	if (!ship.structure[i]) {
//	  req = i;
//	  break;
//	}
//      }
//    }
//    /* sanity: */
//    assert(req!=-1);
//    assert(!ship.structure[req]);
//    
//    /* Now we want to find a structural we can build which leads to req.
//       This loop should bottom out, because everything leads back to s0,
//       and we made sure above that we do s0 first.
//     */
//    while(!ship.structure[Spaceship.structurals_info[req].required]) {
//      req = Spaceship.structurals_info[req].required;
//    }
//    type = spaceship_place_type.SSHIP_PLACE_STRUCTURAL;
//    num = req;
//    handle_spaceship_place(pplayer, type, num);
//    retval = true;
//  }
//  return retval;
//}
}