package common;

import common.city.city;
import common.government.government;
import common.government.government_flag_id;
import common.player.player;

public class Government{

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
//#include "game.h"
//#include "log.h"
//#include "mem.h"
//#include "player.h"
//#include "shared.h"
//#include "support.h"
//#include "tech.h"
//
//#include "government.h"
//
///* TODO:
// * o Update and turn on government evaluation code.
// * o Check exact government rules vs Civ1,Civ2.
// * o The clients display wrong upkeep icons in the city display,
// *   not sure what to do here.  (Does the new terrain-ruleset code
// *   have icons that could be used here?)
// * o When change government, server should update cities and unit
// *   upkeep etc and send updated info to client.
// * o Implement actual cost for unit gold upkeep.
// * o Possibly remove ai_gov_tech_hint stuff?
// *   (See usage in ai_manage_cities() in aicity.c)
// * o Update help system, including dynamic help on governments.
// * o Test the new government evaluation code (AI).
// *   [ It seems fine to me, although it favours Democracy very early
// *   on. This is because of the huge trade bonus. -SKi ]
// * o Implement the features needed for fundamentalism:
// *   - A diplomatic penalty modifier when international incidents occur.
// *     (Spy places nuke in city, goes to war, etc).
// *     [ Is this one of those Civ II "features" best be ignored?  I am
// *     inclined to think so -SKi ]
// */
//
///*
// * WISHLIST:
// * o Features needed for CTP-style rules, just more trade, science and
// *   production modifiers.  (Just counting CTP governments, only
// *   basics).
// */
//
static government[] governments = null;
//
//struct ai_gov_tech_hint ai_gov_tech_hints[MAX_NUM_TECH_LIST];
//
//static final String flag_names[] = {
//  "Build_Veteran_Diplomats", "Revolution_When_Unhappy", "Has_Senate",
//  "Unbribable", "Inspires_Partisans", "Rapture_City_Growth",
//  "Fanatic_Troops", "No_Unhappy_Citizens", "Convert_Tithes_To_Money",
//  "Reduced_Research"
//};
//
///***************************************************************
//  Convert flag names to enum; case insensitive;
//  returns G_LAST_FLAG if can't match.
//***************************************************************/
//enum government_flag_id government_flag_from_str(final String s)
//{
//  enum government_flag_id i;
//
//  assert(ARRAY_SIZE(flag_names) == G_LAST_FLAG);
//  
//  for(i=G_FIRST_FLAG; i<G_LAST_FLAG; i++) {
//    if (mystrcasecmp(flag_names[i], s)==0) {
//      return i;
//    }
//  }
//  return G_LAST_FLAG;
//}
//
///****************************************************************************
//  Returns true iff the given government has the given flag.
//****************************************************************************/
public static boolean government_has_flag(final government gov,
			 government_flag_id flag)
{
//  assert(flag>=G_FIRST_FLAG && flag<G_LAST_FLAG);
//  return TEST_BIT(gov.flags, flag);
return false;
}

///****************************************************************************
//  Does a linear search of the governments to find the one that matches the
//  given (translated) name.  Returns null if none match.
//****************************************************************************/
//government find_government_by_name(final String name)
//{
//  government_iterate(gov) {
//    if (mystrcasecmp(gov.name, name) == 0) {
//      return gov;
//    }
//  } government_iterate_end;
//
//  return null;
//}
//
///****************************************************************************
//  Does a linear search of the governments to find the one that matches the
//  given original (untranslated) name.  Returns null if none match.
//****************************************************************************/
//government find_government_by_name_orig(final String name)
//{
//  government_iterate(gov) {
//    if (mystrcasecmp(gov.name_orig, name) == 0) {
//      return gov;
//    }
//  } government_iterate_end;
//
//  return null;
//}

/****************************************************************************
  Return the government with the given ID.
****************************************************************************/
static government get_government(int gov)
{
  assert(Game.game.government_count > 0 && gov >= 0
	 && gov < Game.game.government_count);
  assert(governments[gov].index == gov);
  return governments[gov];
}

/***************************************************************
...
***************************************************************/
static government get_gov_pplayer(player pplayer)
{
  assert(pplayer != null);
  return get_government(pplayer.government);
}

/****************************************************************************
  Return the government of the player who owns the city.
****************************************************************************/
public static government get_gov_pcity(final city pcity)
{
  assert(pcity != null);
  return get_gov_pplayer(City.city_owner(pcity));
}


///***************************************************************
//...
//***************************************************************/
//final String get_ruler_title(int gov, boolean male, int nation)
//{
//  government g = get_government(gov);
//  ruler_title best_match = null;
//  int i;
//
//  for(i=0; i<g.num_ruler_titles; i++) {
//    ruler_title title = &g.ruler_titles[i];
//    if (title.nation == DEFAULT_TITLE && !best_match) {
//      best_match = title;
//    } else if (title.nation == nation) {
//      best_match = title;
//      break;
//    }
//  }
//
//  if (best_match) {
//    return male ? best_match.male_title : best_match.female_title;
//  } else {
//    util.freelog(Log.LOG_ERROR,
//	    "get_ruler_title: found no title for government %d (%s) nation %d",
//	    gov, g.name, nation);
//    return male ? "Mr." : "Ms.";
//  }
//}

/***************************************************************
...
***************************************************************/
int get_government_max_rate(int type)
{
//  if(type == G_MAGIC)
//    return 100;
//  if(type >= 0 && type < Game.game.government_count)
//    return governments[type].max_rate;
  return 50;
}

/***************************************************************
Added for civil war probability computation - Kris Bubendorfer
***************************************************************/
public static int get_government_civil_war_prob(int type)
{
  if(type >= 0 && type < Game.game.government_count)
    return governments[type].civil_war;
  return 0;
}

///***************************************************************
//...
//***************************************************************/
//final String get_government_name(int type)
//{
//  if(type >= 0 && type < Game.game.government_count)
//    return governments[type].name;
//  return "";
//}
//
///***************************************************************
//  Can change to government if appropriate tech exists, and one of:
//   - no required tech (required is A_NONE)
//   - player has required tech
//   - we have an appropriate wonder
//***************************************************************/
//boolean can_change_to_government(player pplayer, int government)
//{
//  int req;
//
//  assert(Game.game.government_count > 0 &&
//	 government >= 0 && government < Game.game.government_count);
//
//  req = governments[government].required_tech;
//  if (!tech_is_available(pplayer, req)) {
//    /* If the technology doesn't "exist" or if there is no way we can
//     * ever get it, then we can't change to the gov type even if we have
//     * a wonder that would otherwise allow it. */
//    return false;
//  } else {
//    return (get_invention(pplayer, req) == TECH_KNOWN
//	    || get_player_bonus(pplayer, EFT_ANY_GOVERNMENT) > 0);
//  }
//}
//
///***************************************************************
//...
//***************************************************************/
//void set_ruler_title(government gov, int nation,
//                     final String male, final String female)
//{
//  ruler_title title;
//
//  gov.num_ruler_titles++;
//  gov.ruler_titles =
//    fc_realloc(gov.ruler_titles,
//      gov.num_ruler_titles*sizeof(struct ruler_title));
//  title = &(gov.ruler_titles[gov.num_ruler_titles-1]);
//
//  title.nation = nation;
//
//  title.male_title_orig = male;
//  title.male_title = title.male_title_orig;
//
//  title.female_title_orig = female;
//  title.female_title = title.female_title_orig;
//}
//
///***************************************************************
// Allocate space for the given number of governments.
//***************************************************************/
//void governments_alloc(int num)
//{
//  int index;
//
//  governments = fc_calloc(num, sizeof(struct government));
//  Game.game.government_count = num;
//
//  for (index = 0; index < num; index++) {
//    governments[index].index = index;
//  }
//}
//
///***************************************************************
// De-allocate resources associated with the given government.
//***************************************************************/
//static void government_free(government gov)
//{
//  free(gov.ruler_titles);
//  gov.ruler_titles = null;
//
//  free(gov.helptext);
//  gov.helptext = null;
//}
//
///***************************************************************
// De-allocate the currently allocated governments.
//***************************************************************/
//void governments_free()
//{
//  government_iterate(gov) {
//    government_free(gov);
//  } government_iterate_end;
//  free(governments);
//  governments = null;
//  Game.game.government_count = 0;
//}
}