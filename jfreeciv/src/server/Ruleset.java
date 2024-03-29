package server;

import common.Game;

public class Ruleset{

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
//#include <stdio.h>
//#include <stdlib.h>
//#include <string.h>
//
//#include "capability.h"
//#include "city.h"
//#include "fcintl.h"
//#include "Game.game.h"
//#include "government.h"
//#include "log.h"
//#include "Map.map.h"
//#include "mem.h"
//#include "nation.h"
//#include "packets.h"
//#include "registry.h"
//#include "shared.h"
//#include "support.h"
//#include "tech.h"
//#include "unit.h"
//
//#include "citytools.h"
//
//#include "aiunit.h"		/* update_simple_ai_types */
//
//#include "ruleset.h"
//
//static final char name_too_long[] = "Name \"%s\" too long; truncating.";
//#define check_name(name) (check_strlen(name, MAX_LEN_NAME, name_too_long))
//#define name_strlcpy(dst, src) (() sz_loud_strlcpy(dst, src, name_too_long))
//
//static void openload_ruleset_file(section_file file,
//				  final String whichset);
//static char *check_ruleset_capabilities(section_file file,
//					final String us_capstr,
//					final String filename);
//
//static int lookup_tech(section_file file, final String prefix,
//		       final String entry, boolean required, final String filename,
//		       final String description);
//static void lookup_tech_list(section_file file, final String prefix,
//			     final String entry, int *output, final String filename);
//static int lookup_unit_type(section_file file, final String prefix,
//			    final String entry, boolean required, final String filename,
//			    final String description);
//static int lookup_impr_type(section_file file, final String prefix,
//				     final String entry, boolean required,
//				     final String filename, final String description);
//static int lookup_government(section_file file, final String entry,
//			     final String filename);
//static int lookup_city_cost(section_file file, final String prefix,
//			    final String entry, final String filename);
//static char *lookup_helptext(section_file file, char *prefix);
//
//static int lookup_terrain(char *name, 
//                                             int tthis);
//
//static void load_tech_names(section_file file);
//static void load_Unittype_P.unit_names(section_file file);
//static void load_building_names(section_file file);
//static void load_government_names(section_file file);
//static void load_terrain_names(section_file file);
//static void load_citystyle_names(section_file file);
//static void load_nation_names(section_file file);
//static struct city_name* load_city_name_list(section_file file,
//					     final String secfile_str1,
//					     final String secfile_str2);
//
//static void load_ruleset_techs(section_file file);
//static void load_ruleset_units(section_file file);
//static void load_ruleset_buildings(section_file file);
//static void load_ruleset_governments(section_file file);
//static void load_ruleset_terrain(section_file file);
//static void load_ruleset_cities(section_file file);
//static void load_ruleset_nations(section_file file);
//
//static void load_ruleset_game();
//
//static void send_ruleset_techs(Speclists<Connection> dest);
//static void send_ruleset_units(Speclists<Connection> dest);
//static void send_ruleset_buildings(Speclists<Connection> dest);
//static void send_ruleset_terrain(Speclists<Connection> dest);
//static void send_ruleset_governments(Speclists<Connection> dest);
//static void send_ruleset_nations(Speclists<Connection> dest);
//static void send_ruleset_cities(Speclists<Connection> dest);
//static void send_ruleset_game(Speclists<Connection> dest);
//
///**************************************************************************
//  datafilename() wrapper: tries to match in two ways.
//  Returns null on failure, the (statically allocated) filename on success.
//**************************************************************************/
//static char *valid_ruleset_filename(final String subdir, final String whichset)
//{
//  char filename[512], *dfilename;
//
//  assert(subdir && whichset);
//
//  filename = util.my_snprintf( "%s/%s.ruleset", subdir, whichset);
//  dfilename = datafilename(filename);
//  if (dfilename)
//    return dfilename;
//
//  util.freelog(Log.LOG_VERBOSE, "Trying to load file from default ruleset directory " +
//	  "instead.");
//  filename = util.my_snprintf( "default/%s.ruleset", whichset);
//  dfilename = datafilename(filename);
//  if (dfilename)
//    return dfilename;
//
//  /* TRANS: message for an obscure ruleset error. */
//  util.freelog(Log.LOG_ERROR, "Trying alternative ruleset filename syntax.");
//  filename = util.my_snprintf( "%s_%s.ruleset", subdir, whichset);
//  dfilename = datafilename(filename);
//  if (dfilename)
//    return dfilename;
//
//  return(null);
//}
//
///**************************************************************************
//  Do initial section_file_load on a ruleset file.
//  "whichset" = "techs", "units", "buildings", "terrain", ...
//  Calls exit(EXIT_FAILURE) on failure.
//**************************************************************************/
//static void openload_ruleset_file(section_file file, final String whichset)
//{
//  char sfilename[512];
//  char *dfilename = valid_ruleset_filename(Game.game.rulesetdir, whichset);
//
//  if (!dfilename) {
//    util.freelog(LOG_FATAL,
//	    /* TRANS: message for an obscure ruleset error. */
//	    ("Could not find a readable \"%s\" ruleset file."), whichset);
//    exit(EXIT_FAILURE);
//  }
//
//  /* Need to save a copy of the filename for following message, since
//     section_file_load() may call datafilename() for includes. */
//
//  sfilename = dfilename;
//
//  if (!section_file_load_nodup(file, sfilename)) {
//    util.freelog(LOG_FATAL,
//	    /* TRANS: message for an obscure ruleset error. */
//	    ("Could not load ruleset file \"%s\"."), sfilename);
//    exit(EXIT_FAILURE);
//  }
//}
//
///**************************************************************************
//  Ruleset files should have a capabilities string datafile.options
//  This gets and returns that string, and checks that the required
//  capabilities specified are satisified.
//**************************************************************************/
//static char *check_ruleset_capabilities(section_file file,
//					final String us_capstr, final String filename)
//{
//  char *datafile_options;
//  
//  datafile_options = secfile_lookup_str(file, "datafile.options");
//  if (!has_capabilities(us_capstr, datafile_options)) {
//    util.freelog(LOG_FATAL, "Ruleset datafile appears incompatible:");
//    util.freelog(LOG_FATAL, ("file: \"%s\""), filename);
//    util.freelog(LOG_FATAL, "file options: %s", datafile_options);
//    util.freelog(LOG_FATAL, "supported options: %s", us_capstr);
//    exit(EXIT_FAILURE);
//  }
//  if (!has_capabilities(datafile_options, us_capstr)) {
//    util.freelog(LOG_FATAL, ("Ruleset datafile claims required option(s)" +
//			 " which we don't support:"));
//    util.freelog(LOG_FATAL, ("file: \"%s\""), filename);
//    util.freelog(LOG_FATAL, "file options: %s", datafile_options);
//    util.freelog(LOG_FATAL, "supported options: %s", us_capstr);
//    exit(EXIT_FAILURE);
//  }
//  return datafile_options;
//}
//
//
///**************************************************************************
// Lookup a string prefix.entry in the file and return the corresponding
// advances id.  If (!required), return Tech_H.A_LAST if match "Never" or can't match.
// If (required), util.die if can't match.  Note the first tech should have
// name "None" so that will always match.
// If description is not null, it is used in the warning message
// instead of prefix (eg pass unit.name instead of prefix="units2.u27")
//**************************************************************************/
//static int lookup_tech(section_file file, final String prefix,
//		       final String entry, boolean required, final String filename,
//		       final String description)
//{
//  char *sval;
//  int i;
//  
//  sval = secfile_lookup_str_default(file, null, "%s.%s", prefix, entry);
//  if (!sval || (!required && strcmp(sval, "Never") == 0)) {
//    i = Tech_H.A_LAST;
//  } else {
//    i = find_tech_by_name(sval);
//    if (i==Tech_H.A_LAST) {
//      util.freelog((required?LOG_FATAL:Log.LOG_ERROR),
//	   "for %s %s couldn't match tech \"%s\" (%s)",
//	   (description?description:prefix), entry, sval, filename);
//      if (required) {
//	exit(EXIT_FAILURE);
//      } else {
//	i = Tech_H.A_LAST;
//      }
//    }
//  }
//  return i;
//}
//
///**************************************************************************
// Lookup a prefix.entry string vector in the file and fill in the
// array, which should hold MAX_NUM_TECH_LIST items. The output array is
// either Tech_H.A_LAST terminated or full (contains MAX_NUM_TECH_LIST
// items). All valid entries of the output array are guaranteed to
// tech_exist(). There should be at least one value, but it may be "",
// meaning empty list.
//**************************************************************************/
//static void lookup_tech_list(section_file file, final String prefix,
//			     final String entry, int *output, final String filename)
//{
//  char **slist;
//  int i, nval;
//
//  /* pre-fill with Tech_H.A_LAST: */
//  for(i=0; i<MAX_NUM_TECH_LIST; i++) {
//    output[i] = Tech_H.A_LAST;
//  }
//  slist = secfile_lookup_str_vec(file, &nval, "%s.%s", prefix, entry);
//  if (nval==0) {
//    util.freelog(LOG_FATAL, "Missing string vector %s.%s (%s)",
//	    prefix, entry, filename);
//    exit(EXIT_FAILURE);
//  }
//  if (nval>MAX_NUM_TECH_LIST) {
//    util.freelog(LOG_FATAL, "String vector %s.%s too long (%d, max %d) (%s)",
//	    prefix, entry, nval, MAX_NUM_TECH_LIST, filename);
//    exit(EXIT_FAILURE);
//  }
//  if (nval==1 && strcmp(slist[0], "")==0) {
//    free(slist);
//    return;
//  }
//  for (i=0; i<nval; i++) {
//    char *sval = slist[i];
//    int tech = find_tech_by_name(sval);
//    if (tech==Tech_H.A_LAST) {
//      util.freelog(LOG_FATAL, "For %s %s (%d) couldn't match tech \"%s\" (%s)",
//	      prefix, entry, i, sval, filename);
//      exit(EXIT_FAILURE);
//    }
//    if (!tech_exists(tech)) {
//      util.freelog(LOG_FATAL, "For %s %s (%d) tech \"%s\" is removed (%s)",
//	      prefix, entry, i, sval, filename);
//      exit(EXIT_FAILURE);
//    }
//    output[i] = tech;
//    util.freelog(Log.LOG_DEBUG, "%s.%s,%d %s %d", prefix, entry, i, sval, tech);
//  }
//  free(slist);
//  return;
//}
//
///**************************************************************************
//  Lookup a prefix.entry string vector in the file and fill in the
//  array, which should hold Shared_H.MAX_NUM_BUILDING_LIST items. The output array is
//  either Improvement.B_LAST terminated or full (contains Shared_H.MAX_NUM_BUILDING_LIST
//  items). All valid entries of the output array are guaranteed to pass
//  improvement_exist(). There should be at least one value, but it may be
//  "", meaning an empty list.
//**************************************************************************/
//static void lookup_building_list(section_file file, final String prefix,
//				 final String entry, int *output,
//				 final String filename)
//{
//  char **slist;
//  int i, nval;
//
//  /* pre-fill with Improvement.B_LAST: */
//  for (i = 0; i < Shared_H.MAX_NUM_BUILDING_LIST; i++) {
//    output[i] = Improvement.B_LAST;
//  }
//  slist = secfile_lookup_str_vec(file, &nval, "%s.%s", prefix, entry);
//  if (nval == 0) {
//    util.freelog(LOG_FATAL, "Missing string vector %s.%s (%s)",
//	    prefix, entry, filename);
//    exit(EXIT_FAILURE);
//  }
//  if (nval > Shared_H.MAX_NUM_BUILDING_LIST) {
//    util.freelog(LOG_FATAL, "String vector %s.%s too long (%d, max %d) (%s)",
//	    prefix, entry, nval, Shared_H.MAX_NUM_BUILDING_LIST, filename);
//    exit(EXIT_FAILURE);
//  }
//  if (nval == 1 && strcmp(slist[0], "") == 0) {
//    free(slist);
//    return;
//  }
//  for (i = 0; i < nval; i++) {
//    char *sval = slist[i];
//    int building = find_improvement_by_name(sval);
//
//    if (building == Improvement.B_LAST) {
//      util.freelog(LOG_FATAL, "For %s %s (%d) couldn't match building \"%s\" (%s)",
//	      prefix, entry, i, sval, filename);
//      exit(EXIT_FAILURE);
//    }
//    output[i] = building;
//    util.freelog(Log.LOG_DEBUG, "%s.%s,%d %s %d", prefix, entry, i, sval, building);
//  }
//  free(slist);
//}
//
///**************************************************************************
// Lookup a string prefix.entry in the file and return the corresponding
// unit_type id.  If (!required), return -1 if match "None" or can't match.
// If (required), util.die if can't match.
// If description is not null, it is used in the warning message
// instead of prefix (eg pass unit.name instead of prefix="units2.u27")
//**************************************************************************/
//static int lookup_unit_type(section_file file, final String prefix,
//			    final String entry, boolean required, final String filename,
//			    final String description)
//{
//  char *sval;
//  int i;
//  
//  if (required) {
//    sval = secfile_lookup_str(file, "%s.%s", prefix, entry);
//  } else {
//    sval = secfile_lookup_str_default(file, "None", "%s.%s", prefix, entry);
//  }
//
//  if (strcmp(sval, "None")==0) {
//    i = -1;
//  } else {
//    i = find_unit_type_by_name(sval);
//    if (i==unittype.U_LAST) {
//      util.freelog((required?LOG_FATAL:Log.LOG_ERROR),
//	   "for %s %s couldn't match unit_type \"%s\" (%s)",
//	   (description?description:prefix), entry, sval, filename);
//      if (required) {
//	exit(EXIT_FAILURE);
//      } else {
//	i = -1;
//      }
//    }
//  }
//  return i;
//}
//
///**************************************************************************
// Lookup a string prefix.entry in the file and return the corresponding
// int.  If (!required), return Improvement.B_LAST if match "None" or can't match.
// If (required), util.die if can't match.
// If description is not null, it is used in the warning message
// instead of prefix (eg pass impr.name instead of prefix="imprs2.b27")
//**************************************************************************/
//static int lookup_impr_type(section_file file, final String prefix,
//				     final String entry, boolean required,
//				     final String filename, final String description)
//{
//  char *sval;
//  int id;
//
//  if (required) {
//    sval = secfile_lookup_str(file, "%s.%s", prefix, entry);
//  } else {
//    sval = secfile_lookup_str_default(file, "None", "%s.%s", prefix, entry);
//  }
//
//  if (strcmp(sval, "None")==0) {
//    id = Improvement.B_LAST;
//  } else {
//    id = find_improvement_by_name(sval);
//    if (id==Improvement.B_LAST) {
//      util.freelog((required?LOG_FATAL:Log.LOG_ERROR),
//	   "for %s %s couldn't match impr_type \"%s\" (%s)",
//	   (description?description:prefix), entry, sval, filename);
//      if (required) {
//	exit(EXIT_FAILURE);
//      }
//    }
//  }
//
//  return id;
//}
//
///**************************************************************************
//  Lookup entry in the file and return the corresponding government index;
//  util.dies if can't find/match.  filename is for error message.
//**************************************************************************/
//static int lookup_government(section_file file, final String entry,
//			     final String filename)
//{
//  char *sval;
//  government gov;
//  
//  sval = secfile_lookup_str(file, "%s", entry);
//  gov = find_government_by_name(sval);
//  if (!gov) {
//    util.freelog(LOG_FATAL, "for %s couldn't match government \"%s\" (%s)",
//	    entry, sval, filename);
//    exit(EXIT_FAILURE);
//  }
//  return gov.index;
//}
//
///**************************************************************************
//  Lookup entry in the file and return the corresponding city cost:
//  value if int, or G_CITY_SIZE_FREE is entry is "City_Size".
//  Dies if gets some other string.  filename is for error message.
//**************************************************************************/
//static int lookup_city_cost(section_file file, final String prefix,
//			    final String entry, final String filename)
//{
//  char *sval;
//  int ival = 0;
//  
//  sval = secfile_lookup_str_int(file, &ival, "%s.%s", prefix, entry);
//  if (sval) {
//    if (mystrcasecmp(sval, "City_Size") == 0) {
//      ival = G_CITY_SIZE_FREE;
//    } else {
//      util.freelog(LOG_FATAL, "Bad %s \"%s\" for %s (%s)",
//	      entry, sval, prefix, filename);
//      exit(EXIT_FAILURE);
//    }
//  }
//  return ival;
//}
//
///**************************************************************************
//  Lookup optional string, returning allocated memory or null.
//**************************************************************************/
//static char *lookup_string(section_file file, final String prefix,
//			   final String suffix)
//{
//  char *sval;
//  
//  sval = secfile_lookup_str_default(file, null, "%s.%s", prefix, suffix);
//  if (sval) {
//    sval = skip_leading_spaces(sval);
//    if (sval.length() > 0) {
//      return (sval);
//    }
//  }
//  return null;
//}
//
///**************************************************************************
//  Lookup optional helptext, returning allocated memory or null.
//**************************************************************************/
//static char *lookup_helptext(section_file file, char *prefix)
//{
//  return lookup_string(file, prefix, "helptext");
//}
//
///**************************************************************************
//  Look up a terrain name in the tile_types array and return its index.
//**************************************************************************/
//static int lookup_terrain(char *name, 
//                                             int tthis)
//{
//  int i;
//
//  if (*name == '\0' || (0 == strcmp(name, "none")) 
//      || (0 == strcmp(name, "no"))) {
//    return Terrain_H.T_NONE;
//  } else if (0 == strcmp(name, "yes")) {
//    return (tthis);
//  }
//
//  for (i = Terrain_H.T_FIRST; i < Terrain_H.T_COUNT; i++) {
//    if (0 == strcmp(name, get_tile_type(i).terrain_name)) {
//      return i;
//    }
//  }
//
//  /* TRANS: message for an obscure ruleset error. */
//  util.freelog(Log.LOG_ERROR, "Unknown terrain %s in entry %s.",
//	  name, get_tile_type(tthis).terrain_name);
//  return Terrain_H.T_NONE;
//}
//
///**************************************************************************
//  ...
//**************************************************************************/
//static void load_tech_names(section_file file)
//{
//  char **sec;
//  advance a;
//  int num_techs; /* number of techs in the ruleset (means without A_NONE)*/
//  int i;
//  final String filename = secfile_filename(file);
//
//  () section_file_lookup(file, "datafile.description");	/* unused */
//
//  /* The names: */
//  sec = secfile_get_secnames_prefix(file, "advance_", &num_techs);
//  util.freelog(Log.LOG_VERBOSE, "%d advances (including possibly unused)", num_techs);
//  if(num_techs == 0) {
//    util.freelog(LOG_FATAL, "No Advances?! (%s)", filename);
//    exit(EXIT_FAILURE);
//  }
//
//  if(num_techs + A_FIRST > Tech_H.A_LAST_REAL) {
//    util.freelog(LOG_FATAL, "Too many advances (%d, max %d) (%s)",
//	    num_techs, Tech_H.A_LAST_REAL-A_FIRST, filename);
//    exit(EXIT_FAILURE);
//  }
//
//  /* Initialize dummy tech A_NONE */
//  sz_strlcpy(advances[A_NONE].name_orig, "None");
//  advances[A_NONE].name = advances[A_NONE].name_orig;
//
//  Game.game.num_tech_types = num_techs + 1; /* includes A_NONE */
//
//  a = &advances[A_FIRST];
//  for (i = 0; i < num_techs; i++ ) {
//    char *name = secfile_lookup_str(file, "%s.name", sec[i]);
//    name_strlcpy(a.name_orig, name);
//    a.name = a.name_orig;
//    a++;
//  }
//  free(sec);
//}
//
///**************************************************************************
//  ...
//**************************************************************************/
//static void load_ruleset_techs(section_file file)
//{
//  char **sec;
//  advance a;
//  int num_techs; /* number of techs in the ruleset (means without A_NONE)*/
//  int i;
//  final String filename = secfile_filename(file);
//  
//  () check_ruleset_capabilities(file, "+1.9", filename);
//  sec = secfile_get_secnames_prefix(file, "advance_", &num_techs);
//
//  /* Initialize dummy tech A_NONE */
//  advances[A_NONE].req[0] = A_NONE;
//  advances[A_NONE].req[1] = A_NONE;
//  advances[A_NONE].flags = 0;
//  advances[A_NONE].root_req = Tech_H.A_LAST;
//
//  a = &advances[A_FIRST];
//  
//  for( i=0; i<num_techs; i++ ) {
//    char *sval, **slist;
//    int j,ival,nval;
//
//    a.req[0] = lookup_tech(file, sec[i], "req1", false, filename, a.name);
//    a.req[1] = lookup_tech(file, sec[i], "req2", false, filename, a.name);
//    a.root_req = lookup_tech(file, sec[i], "root_req", false,
//			      filename, a.name);
//
//    if ((a.req[0]==Tech_H.A_LAST && a.req[1]!=Tech_H.A_LAST) ||
//	(a.req[0]!=Tech_H.A_LAST && a.req[1]==Tech_H.A_LAST)) {
//      util.freelog(Log.LOG_ERROR, "for tech %s: \"Never\" with non-\"Never\" (%s)",
//	   a.name, filename);
//      a.req[0] = a.req[1] = Tech_H.A_LAST;
//    }
//    if (a.req[0]==A_NONE && a.req[1]!=A_NONE) {
//      util.freelog(Log.LOG_ERROR, "tech %s: should have \"None\" second (%s)",
//	   a.name, filename);
//      a.req[0] = a.req[1];
//      a.req[1] = A_NONE;
//    }
//
//    a.flags = 0;
//
//    slist = secfile_lookup_str_vec(file, &nval, "%s.flags", sec[i]);
//    for(j=0; j<nval; j++) {
//      sval = slist[j];
//      if(strcmp(sval,"")==0) {
//        continue;
//      }
//      ival = tech_flag_from_str(sval);
//      if (ival==TF_LAST) {
//        util.freelog(Log.LOG_ERROR, "for advance_type \"%s\": bad flag name \"%s\" (%s)",
//                a.name, sval, filename);
//      }
//      a.flags |= (1<<ival);
//    }
//    free(slist);
//
//    a.graphic_str = String.format(
//	       secfile_lookup_str_default(file, "-", "%s.graphic", sec[i]));
//    a.graphic_alt = String.format(
//	       secfile_lookup_str_default(file, "-",
//					  "%s.graphic_alt", sec[i]));
//    
//    a.helptext = lookup_helptext(file, sec[i]);    
//    a.bonus_message = lookup_string(file, sec[i], "bonus_message");
//    a.preset_cost =
//	secfile_lookup_int_default(file, -1, "%s.%s", sec[i], "cost");
//    a.num_reqs = 0;
//    
//    a++;
//  }
//
//  /* Propagate a root tech up into the tech tree.  Thus if a technology
//   * X has Y has a root tech, then any technology requiring X also has
//   * Y as a root tech. */
//restart:
//  for (i = A_FIRST; i < A_FIRST + num_techs; i++) {
//    a = &advances[i];
//    if (a.root_req != Tech_H.A_LAST && tech_exists(i)) {
//      int j;
//      boolean out_of_order = false;
//
//      /* Now find any tech depending on this technology and update it's
//       * root_req. */
//      for(j = A_FIRST; j < A_FIRST + num_techs; j++) {
//        advance b = &advances[j];
//        if ((b.req[0] == i || b.req[1] == i)
//            && b.root_req == Tech_H.A_LAST
//            && tech_exists(j)) {
//          b.root_req = a.root_req;
//	  if (j < i) {
//	    out_of_order = true;
//          }
//        }
//      }
//
//      if (out_of_order) {
//	/* HACK: If we just changed the root_tech of a lower-numbered
//	 * technology, we need to go back so that we can propagate the
//	 * root_tech up to that technology's parents... */
//	goto restart;   
//      }
//    }
//  }
//  /* Now rename Tech_H.A_LAST to A_NONE for consistency's sake */
//  for (i = A_NONE; i < A_FIRST + num_techs; i++) {
//    a = &advances[i];
//    if (a.root_req == Tech_H.A_LAST) {
//      a.root_req = A_NONE;
//    }
//  }
//
//  /* Some more consistency checking: 
//     Non-removed techs depending on removed techs is too
//     broken to fix by default, so util.die.
//  */
//  tech_type_iterate(i) {
//    if (i != A_NONE && tech_exists(i)) {
//      a = &advances[i];
//      /* We check for recursive tech loops later,
//       * in build_required_techs_helper. */
//      if (!tech_exists(a.req[0])) {
//	util.freelog(LOG_FATAL, "tech \"%s\": req1 leads to removed tech \"%s\" (%s)",
//	     a.name, advances[a.req[0]].name, filename);
//	exit(EXIT_FAILURE);
//      } 
//      if (!tech_exists(a.req[1])) {
//	util.freelog(LOG_FATAL, "tech \"%s\": req2 leads to removed tech \"%s\" (%s)",
//	     a.name, advances[a.req[1]].name, filename);
//	exit(EXIT_FAILURE);
//      }
//    }
//  } tech_type_iterate_end;
//
//  precalc_tech_data();
//
//  free(sec);
//  section_file_check_unused(file, filename);
//  section_file_free(file);
//}
//
///**************************************************************************
//  ...  
//**************************************************************************/
//static void load_Unittype_P.unit_names(section_file file)
//{
//  char **sec;
//  int nval;
//  final String filename = secfile_filename(file);
//
//  () section_file_lookup(file, "datafile.description");	/* unused */
//
//  /* The names: */
//  sec = secfile_get_secnames_prefix(file, "unit_", &nval);
//  util.freelog(Log.LOG_VERBOSE, "%d unit types (including possibly unused)", nval);
//  if(nval == 0) {
//    util.freelog(LOG_FATAL, "No units?! (%s)", filename);
//    exit(EXIT_FAILURE);
//  }
//  if(nval > unittype.U_LAST) {
//    util.freelog(LOG_FATAL, "Too many units (%d, max %d) (%s)",
//	    nval, unittype.U_LAST, filename);
//    exit(EXIT_FAILURE);
//  }
//
//  Game.game.num_unit_types = nval;
//
//  unit_type_iterate(i) {
//    char *name = secfile_lookup_str(file, "%s.name", sec[i]);
//
//    name_strlcpy(Unittype_P.unit_types[i].name_orig, name);
//    Unittype_P.unit_types[i].name = Unittype_P.unit_types[i].name_orig;
//  } unit_type_iterate_end;
//
//  free(sec);
//}
//
///**************************************************************************
//...  
//**************************************************************************/
//static void load_ruleset_units(section_file file)
//{
//  unit_type u;
//  int i, j, ival, nval, vet_levels, vet_levels_default;
//  char *sval, **slist, **sec;
//  final String filename = secfile_filename(file);
//  char **vnlist, **def_vnlist;
//  int *vblist, *def_vblist;
//
//  () check_ruleset_capabilities(file, "+1.9", filename);
//
//  /*
//   * Load up expanded veteran system values.
//   */
//  sec = secfile_get_secnames_prefix(file, "unit_", &nval);
//
//#define CHECK_VETERAN_LIMIT                                         \
//if (vet_levels_default > MAX_VET_LEVELS || vet_levels > MAX_VET_LEVELS) { \
//  util.freelog(LOG_FATAL, "Too many veteran levels, %d is the maximum!", \
//          MAX_VET_LEVELS);                                          \
//  exit(EXIT_FAILURE);                                               \
//}                                                                   \
//
//  /* level names */
//  def_vnlist = secfile_lookup_str_vec(file, &vet_levels_default,
//		  		"veteran_system.veteran_names");
//
//  unit_type_iterate(i) {
//    u = &Unittype_P.unit_types[i];
//
//    vnlist = secfile_lookup_str_vec(file, &vet_levels,
//                                    "%s.veteran_names", sec[i]);
//    CHECK_VETERAN_LIMIT
//    if (vnlist) {
//      /* unit has own veterancy settings */
//      for (j = 0; j < vet_levels; j++) {
//        sz_strlcpy(u.veteran[j].name, vnlist[j]);
//      }
//      free(vnlist);
//    } else {
//      /* apply defaults */  
//      for (j = 0; j < vet_levels_default; j++) {
//        sz_strlcpy(u.veteran[j].name, def_vnlist[j]);
//      }
//    }
//    /* We check for this value to determine how many veteran levels
//     * a unit type has */
//    for (; j < MAX_VET_LEVELS; j++) {
//      u.veteran[j].name[0] = '\0';
//    }
//  } unit_type_iterate_end;
//  free(def_vnlist);
//
//  /* power factor */
//  def_vblist = secfile_lookup_int_vec(file, &vet_levels_default,
//                                      "veteran_system.veteran_power_fact");
//  unit_type_iterate(i) {
//    u = &Unittype_P.unit_types[i];
//    vblist = secfile_lookup_int_vec(file, &vet_levels,
//                                    "%s.veteran_power_fact", sec[i]);
//    CHECK_VETERAN_LIMIT
//    if (vblist) {
//      for (j = 0; j < vet_levels; j++) {
//        u.veteran[j].power_fact = ((double)vblist[j]) / 100;
//      }
//      free(vblist);
//    } else {
//      for (j = 0; j < vet_levels_default; j++) {
//        u.veteran[j].power_fact = ((double)def_vblist[j]) / 100;
//      }
//    }
//  } unit_type_iterate_end;
//  if (def_vblist) {
//    free(def_vblist);
//  }
//
//  /* raise chance */
//  def_vblist = secfile_lookup_int_vec(file, &vet_levels_default,
//                                      "veteran_system.veteran_raise_chance");
//  CHECK_VETERAN_LIMIT
//  for (i = 0; i < vet_levels_default; i++) {
//    Game.game.veteran_chance[i] = def_vblist[i];
//  }
//  for (; i < MAX_VET_LEVELS; i++) {
//    Game.game.veteran_chance[i] = 0;
//  }
//  if (def_vblist) {
//    free(def_vblist);
//  }
//
//  /* work raise chance */
//  def_vblist = secfile_lookup_int_vec(file, &vet_levels_default,
//                                    "veteran_system.veteran_work_raise_chance");
//  CHECK_VETERAN_LIMIT
//  for (i = 0; i < vet_levels_default; i++) {
//    Game.game.work_veteran_chance[i] = def_vblist[i];
//  }
//  for (; i < MAX_VET_LEVELS; i++) {
//    Game.game.work_veteran_chance[i] = 0;
//  }
//  if (def_vblist) {
//    free(def_vblist);
//  }
//
//  /* highseas loss pct */
//  def_vblist = secfile_lookup_int_vec(file, &vet_levels_default,
//  		  	"veteran_system.veteran_highseas_loss_pct");
//  for (i = 0; i < vet_levels_default; i++) {
//    Game.game.trireme_loss_chance[i] = def_vblist[i];
//  }
//  for (; i < MAX_VET_LEVELS; i++) {
//    Game.game.trireme_loss_chance[i] = 50; /* default */
//  }
//  if (def_vblist) {
//    free(def_vblist);
//  }
//  
//  /* move bonus */
//  def_vblist = secfile_lookup_int_vec(file, &vet_levels_default,
//                                      "veteran_system.veteran_move_bonus");
//  unit_type_iterate(i) {
//    u = &Unittype_P.unit_types[i];
//    vblist = secfile_lookup_int_vec(file, &vet_levels,
//  		  	"%s.veteran_move_bonus", sec[i]);
//    CHECK_VETERAN_LIMIT
//    if (vblist) {
//      for (j = 0; j < vet_levels; j++) {
//        u.veteran[j].move_bonus = vblist[j];
//      }
//      free(vblist);
//    } else {
//      for (j = 0; j < vet_levels_default; j++) {
//        u.veteran[j].move_bonus = def_vblist[j];
//      }
//    }
//  } unit_type_iterate_end;
//  if (def_vblist) {
//    free(def_vblist);
//  }
//  
//  /* Tech requirement is used to flag removed Unittype_P.unit_types, which
//     we might want to know for other fields.  After this we
//     can use unit_type_exists()
//  */
//  unit_type_iterate(i) {
//    u = &Unittype_P.unit_types[i];
//    u.tech_requirement = lookup_tech(file, sec[i], "tech_req",
//				      false, filename, u.name);
//  } unit_type_iterate_end;
//  
//  unit_type_iterate(i) {
//    u = &Unittype_P.unit_types[i];
//    if (unit_type_exists(i)) {
//      u.obsoleted_by = lookup_unit_type(file, sec[i],
//					 "obsolete_by", false, filename, u.name);
//    } else {
//      () section_file_lookup(file, "%s.obsolete_by", sec[i]); /* unused */
//      u.obsoleted_by = -1;
//    }
//  } unit_type_iterate_end;
//
//  /* main stats: */
//  unit_type_iterate(i) {
//    u = &Unittype_P.unit_types[i];
//
//    u.impr_requirement =
//      find_improvement_by_name(secfile_lookup_str_default(file, "None", 
//					"%s.impr_req", sec[i]));
//
//    sval = secfile_lookup_str(file, "%s.move_type", sec[i]);
//    ival = unit_move_type_from_str(sval);
//    if (ival==0) {
//      util.freelog(LOG_FATAL, "for unit_type \"%s\": bad move_type %s (%s)",
//	   u.name, sval, filename);
//      exit(EXIT_FAILURE);
//    }
//    u.move_type = ival;
//    
//    u.sound_move = String.format(
//	       secfile_lookup_str_default(file, "-", "%s.sound_move",
//					  sec[i]));
//    u.sound_move_alt = String.format(
//	       secfile_lookup_str_default(file, "-", "%s.sound_move_alt",
//					  sec[i]));
//    u.sound_fight = String.format(
//	       secfile_lookup_str_default(file, "-", "%s.sound_fight",
//					  sec[i]));
//    u.sound_fight_alt = String.format(
//	       secfile_lookup_str_default(file, "-", "%s.sound_fight_alt",
//					  sec[i]));
//    
//    u.graphic_str = String.format(
//	       secfile_lookup_str(file,"%s.graphic", sec[i]));
//    u.graphic_alt = String.format(
//	       secfile_lookup_str_default(file, "-", "%s.graphic_alt", sec[i]));
//    
//    u.build_cost =
//      secfile_lookup_int(file,"%s.build_cost", sec[i]);
//    u.pop_cost =
//      secfile_lookup_int(file,"%s.pop_cost", sec[i]);
//    u.attack_strength =
//      secfile_lookup_int(file,"%s.attack", sec[i]);
//    u.defense_strength =
//      secfile_lookup_int(file,"%s.defense", sec[i]);
//    u.move_rate =
//      Unit_H.SINGLE_MOVE*secfile_lookup_int(file,"%s.move_rate", sec[i]);
//    
//    u.vision_range =
//      secfile_lookup_int(file,"%s.vision_range", sec[i]);
//    u.transport_capacity =
//      secfile_lookup_int(file,"%s.transport_cap", sec[i]);
//    u.hp = secfile_lookup_int(file,"%s.hitpoints", sec[i]);
//    u.firepower = secfile_lookup_int(file,"%s.firepower", sec[i]);
//    if (u.firepower <= 0) {
//      util.freelog(LOG_FATAL, "for unit_type \"%s\": firepower is %d but " +
//	      "must be at least 1.\nSet the unit's attack strength to 0 " +
//	      "if you want it to not have any attack ability. (%s)",
//	      u.name, u.firepower, filename);
//      exit(EXIT_FAILURE);
//    }
//    u.fuel = secfile_lookup_int(file,"%s.fuel", sec[i]);
//
//    u.happy_cost  = secfile_lookup_int(file, "%s.uk_happy", sec[i]);
//    u.shield_cost = secfile_lookup_int(file, "%s.uk_shield", sec[i]);
//    u.food_cost   = secfile_lookup_int(file, "%s.uk_food", sec[i]);
//    u.gold_cost   = secfile_lookup_int(file, "%s.uk_gold", sec[i]);
//
//    u.helptext = lookup_helptext(file, sec[i]);
//
//    u.paratroopers_range = secfile_lookup_int_default(file,
//        0, "%s.paratroopers_range", sec[i]);
//    u.paratroopers_mr_req = Unit_H.SINGLE_MOVE * secfile_lookup_int_default(file,
//        0, "%s.paratroopers_mr_req", sec[i]);
//    u.paratroopers_mr_sub = Unit_H.SINGLE_MOVE * secfile_lookup_int_default(file,
//        0, "%s.paratroopers_mr_sub", sec[i]);
//    u.bombard_rate = secfile_lookup_int_default(file,
//	0, "%s.bombard_rate", sec[i]);
//  } unit_type_iterate_end;
//  
//  /* flags */
//  unit_type_iterate(i) {
//    u = &Unittype_P.unit_types[i];
//    BV_CLR_ALL(u.flags);
//    assert(!Unittype_P.unit_type_flag(i, F_LAST-1));
//
//    slist = secfile_lookup_str_vec(file, &nval, "%s.flags", sec[i]);
//    for(j=0; j<nval; j++) {
//      sval = slist[j];
//      if(strcmp(sval,"")==0) {
//	continue;
//      }
//	ival = unit_flag_from_str(sval);
//      if (ival==F_LAST) {
//	util.freelog(Log.LOG_ERROR, "for unit_type \"%s\": bad flag name \"%s\" (%s)",
//	     u.name, sval, filename);
//      }
//      BV_SET(u.flags, ival);
//      assert(Unittype_P.unit_type_flag(i, ival));
//    }
//    free(slist);
//  } unit_type_iterate_end;
//    
//  /* roles */
//  unit_type_iterate(i) {
//    u = &Unittype_P.unit_types[i];
//    BV_CLR_ALL(u.roles);
//    
//    slist = secfile_lookup_str_vec(file, &nval, "%s.roles", sec[i] );
//    for(j=0; j<nval; j++) {
//      sval = slist[j];
//      if(strcmp(sval,"")==0) {
//	continue;
//      }
//      ival = unit_role_from_str(sval);
//      if (ival==L_LAST) {
//	util.freelog(Log.LOG_ERROR, "for unit_type \"%s\": bad role name \"%s\" (%s)",
//	     u.name, sval, filename);
//      }
//      BV_SET(u.roles, ival - L_FIRST);
//      assert(unit_has_role(i, ival));
//    }
//    free(slist);
//  } unit_type_iterate_end;
//
//  lookup_tech_list(file, "u_specials", "partisan_req",
//		   Game.game.rtech.partisan_req, filename);
//
//  /* Some more consistency checking: */
//  unit_type_iterate(i) {
//    if (unit_type_exists(i)) {
//      u = &Unittype_P.unit_types[i];
//      if (!tech_exists(u.tech_requirement)) {
//	util.freelog(Log.LOG_ERROR,
//		"unit_type \"%s\" depends on removed tech \"%s\" (%s)",
//		u.name, advances[u.tech_requirement].name, filename);
//	u.tech_requirement = Tech_H.A_LAST;
//      }
//      if (u.obsoleted_by!=-1 && !unit_type_exists(u.obsoleted_by)) {
//	util.freelog(Log.LOG_ERROR,
//		"unit_type \"%s\" obsoleted by removed unit \"%s\" (%s)",
//		u.name, Unittype_P.unit_types[u.obsoleted_by].name, filename);
//	u.obsoleted_by = -1;
//      }
//    }
//  } unit_type_iterate_end;
//
//  /* Setup roles and flags pre-calcs: */
//  role_unit_precalcs();
//     
//  /* Check some required flags and roles etc: */
//  if(num_role_units(Eunit_flag_id.F_CITIES)==0) {
//    util.freelog(LOG_FATAL, "No flag=cities units? (%s)", filename);
//    exit(EXIT_FAILURE);
//  }
//  if(num_role_units(Eunit_flag_id.F_SETTLERS)==0) {
//    util.freelog(LOG_FATAL, "No flag=settler units? (%s)", filename);
//    exit(EXIT_FAILURE);
//  }
//  if(num_role_units(L_EXPLORER)==0) {
//    util.freelog(LOG_FATAL, "No role=explorer units? (%s)", filename);
//    exit(EXIT_FAILURE);
//  }
//  if(num_role_units(L_FERRYBOAT)==0) {
//    util.freelog(LOG_FATAL, "No role=ferryboat units? (%s)", filename);
//    exit(EXIT_FAILURE);
//  }
//  if(num_role_units(L_HUT)==0) {
//    util.freelog(LOG_FATAL, "No role=hut units? (%s)", filename);
//    exit(EXIT_FAILURE);
//  }
//  if(num_role_units(L_FIRSTBUILD)==0) {
//    util.freelog(LOG_FATAL, "No role=firstbuild units? (%s)", filename);
//    exit(EXIT_FAILURE);
//  }
//  if(num_role_units(unit_role_id.L_BARBARIAN)==0) {
//    util.freelog(LOG_FATAL, "No role=barbarian units? (%s)", filename);
//    exit(EXIT_FAILURE);
//  }
//  if(num_role_units(unit_role_id.L_BARBARIAN_LEADER)==0) {
//    util.freelog(LOG_FATAL, "No role=barbarian leader units? (%s)", filename);
//    exit(EXIT_FAILURE);
//  }
//  if(num_role_units(unit_role_id.L_BARBARIAN_BUILD)==0) {
//    util.freelog(LOG_FATAL, "No role=barbarian build units? (%s)", filename);
//    exit(EXIT_FAILURE);
//  }
//  if(num_role_units(unit_role_id.L_BARBARIAN_BOAT)==0) {
//    util.freelog(LOG_FATAL, "No role=barbarian ship units? (%s)", filename);
//    exit(EXIT_FAILURE);
//  }
//  if(num_role_units(unit_role_id.L_BARBARIAN_SEA)==0) {
//    util.freelog(LOG_FATAL, "No role=sea raider barbarian units? (%s)", filename);
//    exit(EXIT_FAILURE);
//  }
//  u = &Unittype_P.unit_types[Unittype_P.get_role_unit(unit_role_id.L_BARBARIAN_BOAT,0)];
//  if(u.move_type != unit_move_type.SEA_MOVING) {
//    util.freelog(LOG_FATAL, "Barbarian boat (%s) needs to be a sea unit (%s)",
//            u.name, filename);
//    exit(EXIT_FAILURE);
//  }
//
//  /* pre-calculate Game.game.rtech.nav (tech for non-trireme ferryboat) */
//  Game.game.rtech.nav = Tech_H.A_LAST;
//  for(i=0; i<num_role_units(L_FERRYBOAT); i++) {
//    j = Unittype_P.get_role_unit(L_FERRYBOAT,i);
//    if (!Unittype_P.unit_type_flag(j, F_TRIREME)) {
//      j = get_unit_type(j).tech_requirement;
//      util.freelog(Log.LOG_DEBUG, "nav tech is %s", advances[j].name);
//      Game.game.rtech.nav = j;
//      break;
//    }
//  }
//
//  /* pre-calculate Game.game.rtech.u_partisan
//     (tech for first partisan unit, or Tech_H.A_LAST */
//  if (num_role_units(L_PARTISAN)==0) {
//    Game.game.rtech.u_partisan = Tech_H.A_LAST;
//  } else {
//    j = Unittype_P.get_role_unit(L_PARTISAN, 0);
//    j = Game.game.rtech.u_partisan = get_unit_type(j).tech_requirement;
//    util.freelog(Log.LOG_DEBUG, "partisan tech is %s", advances[j].name);
//  }
//
//  update_simple_ai_types();
//
//  free(sec);
//  section_file_check_unused(file, filename);
//  section_file_free(file);
//}
//
///**************************************************************************
//  ...  
//**************************************************************************/
//static void load_building_names(section_file file)
//{
//  char **sec;
//  int nval;
//  final String filename = secfile_filename(file);
//
//  () section_file_lookup(file, "datafile.description");	/* unused */
//
//  /* The names: */
//  sec = secfile_get_secnames_prefix(file, "building_", &nval);
//  util.freelog(Log.LOG_VERBOSE, "%d improvement types (including possibly unused)", nval);
//  if (nval == 0) {
//    util.freelog(LOG_FATAL, "No improvements?! (%s)", filename);
//    exit(EXIT_FAILURE);
//  }
//  if (nval > Improvement.B_LAST) {
//    util.freelog(LOG_FATAL, "Too many improvements (%d, max %d) (%s)",
//	    nval, Improvement.B_LAST, filename);
//    exit(EXIT_FAILURE);
//  }
//
//  Game.game.num_impr_types = nval;
//
//  for (int i = 0; i < Game.game.num_impr_types; i++) {
//    char *name = secfile_lookup_str(file, "%s.name", sec[i]);
//
//    name_strlcpy(Improvement.improvement_types[i].name_orig, name);
//    Improvement.improvement_types[i].name = Improvement.improvement_types[i].name_orig;
//  } ;
//
//  ruleset_cache_init();
//
//  free(sec);
//}
//
///**************************************************************************
//...  
//**************************************************************************/
//static void load_ruleset_buildings(section_file file)
//{
//  char **sec, *item, **list;
//  int i, j, k, nval, count;
//  impr_type b;
//  final String filename = secfile_filename(file);
//
//  () check_ruleset_capabilities(file, "+1.10.1", filename);
//
//  /* Parse effect source equivalency groups. */
//  sec = secfile_get_secnames_prefix(file, "group_", &nval);
//  for (i = 0; i < nval; i++) {
//    effect_group group;
//    String name="";
//
//    item = secfile_lookup_str(file, "%s.name", sec[i]);
//    name = item;
//
//    group = effect_group_new(name);
//
//    for (j = 0;
//	 (item = secfile_lookup_str_default(file, null,
//					    "%s.elements%d.building",
//					    sec[i], j));
//	 j++) {
//      int id;
//      enum effect_range range;
//      boolean survives;
//
//      if ((id = find_improvement_by_name(item)) == Improvement.B_LAST) {
//	util.freelog(Log.LOG_ERROR,
//		/* TRANS: Obscure ruleset error */
//		("Group %s lists unknown building: \"%s\" (%s)"),
//	    	name, item, filename);
//	continue;
//      }
//
//      item = secfile_lookup_str_default(file, null, "%s.elements%d.range",
//	  				sec[i], j);
//      if (item) {
//	if ((range = effect_range_from_str(item)) == EFR_LAST) {
//	  util.freelog(Log.LOG_ERROR,
//		  /* TRANS: Obscure ruleset error */
//		  ("Group %s lists bad range: \"%s\" (%s)"),
//		  name, item, filename);
//	  continue;
//	}
//      } else {
//	range = EFR_CITY;
//      }
//
//      survives
//	= secfile_lookup_bool_default(file, false, "%s.elements%d.survives",
//				      sec[i], j);
//
//      effect_group_add_element(group, id, range, survives);
//    }
//  }
//  free(sec);
//
//  sec = secfile_get_secnames_prefix(file, "building_", &nval);
//
//  for (i = 0; i < nval; i++) {
//    b = &Improvement.improvement_types[i];
//
//    b.tech_req = lookup_tech(file, sec[i], "tech_req", false, filename, b.name);
//
//    b.bldg_req = lookup_impr_type(file, sec[i], "bldg_req", false, filename, b.name);
//
//    list = secfile_lookup_str_vec(file, &count, "%s.terr_gate", sec[i]);
//    b.terr_gate = fc_malloc((count + 1) * sizeof(b.terr_gate[0]));
//    k = 0;
//    for (j = 0; j < count; j++) {
//      b.terr_gate[k] = get_terrain_by_name(list[j]);
//      if (b.terr_gate[k] == T_UNKNOWN) {
//	util.freelog(Log.LOG_ERROR,
//		"for %s terr_gate[%d] couldn't match terrain \"%s\" (%s)",
//		b.name, j, list[j], filename);
//      } else {
//	k++;
//      }
//    }
//    b.terr_gate[k] = Terrain_H.T_NONE;
//    free(list);
//
//    list = secfile_lookup_str_vec(file, &count, "%s.spec_gate", sec[i]);
//    b.spec_gate = fc_malloc((count + 1) * sizeof(b.spec_gate[0]));
//    k = 0;
//    for (j = 0; j < count; j++) {
//      b.spec_gate[k] = get_special_by_name(list[j]);
//      if (b.spec_gate[k] == S_NO_SPECIAL) {
//	util.freelog(Log.LOG_ERROR,
//		"for %s spec_gate[%d] couldn't match special \"%s\" (%s)",
//		b.name, j, list[j], filename);
//      } else {
//	k++;
//      }
//    }
//    b.spec_gate[k] = S_NO_SPECIAL;
//    free(list);
//
//    item = secfile_lookup_str(file, "%s.equiv_range", sec[i]);
//    b.equiv_range = impr_range_from_str(item);
//    if (b.equiv_range == IR_LAST) {
//      util.freelog(Log.LOG_ERROR,
//	      "for %s equiv_range couldn't match range \"%s\" (%s)",
//	      b.name, item, filename);
//      b.equiv_range = IR_NONE;
//    }
//
//    list = secfile_lookup_str_vec(file, &count, "%s.equiv_dupl", sec[i]);
//    b.equiv_dupl = fc_malloc((count + 1) * sizeof(b.equiv_dupl[0]));
//    k = 0;
//    for (j = 0; j < count; j++) {
//      b.equiv_dupl[k] = find_improvement_by_name(list[j]);
//      if (b.equiv_dupl[k] == Improvement.B_LAST) {
//	util.freelog(Log.LOG_ERROR,
//		"for %s equiv_dupl[%d] couldn't match improvement \"%s\" (%s)",
//		b.name, j, list[j], filename);
//      } else {
//	k++;
//      }
//    }
//    b.equiv_dupl[k] = Improvement.B_LAST;
//    free(list);
//
//    list = secfile_lookup_str_vec(file, &count, "%s.equiv_repl", sec[i]);
//    b.equiv_repl = fc_malloc((count + 1) * sizeof(b.equiv_repl[0]));
//    k = 0;
//    for (j = 0; j < count; j++) {
//      b.equiv_repl[k] = find_improvement_by_name(list[j]);
//      if (b.equiv_repl[k] == Improvement.B_LAST) {
//	util.freelog(Log.LOG_ERROR,
//		"for %s equiv_repl[%d] couldn't match improvement \"%s\" (%s)",
//		b.name, j, list[j], filename);
//      } else {
//	k++;
//      }
//    }
//    b.equiv_repl[k] = Improvement.B_LAST;
//    free(list);
//
//    b.obsolete_by = lookup_tech(file, sec[i], "obsolete_by",
//				 false, filename, b.name);
//    if (b.obsolete_by == A_NONE || !tech_exists(b.obsolete_by)) {
//      /* 
//       * The ruleset can specify "None" for a never-obsoleted
//       * improvement.  Currently this means A_NONE, which is an
//       * unnecessary special-case.  We use Tech_H.A_LAST to flag a
//       * never-obsoleted improvement in the code instead.
//       */
//      b.obsolete_by = Tech_H.A_LAST;
//    }
//
//    b.replaced_by = lookup_impr_type(file, sec[i], "replaced_by",
//				      false, filename, b.name);
//
//    b.is_wonder = secfile_lookup_bool(file, "%s.is_wonder", sec[i]);
//
//    b.build_cost = secfile_lookup_int(file, "%s.build_cost", sec[i]);
//
//    b.upkeep = secfile_lookup_int(file, "%s.upkeep", sec[i]);
//
//    b.sabotage = secfile_lookup_int(file, "%s.sabotage", sec[i]);
//
//    /* Parse building effects and add them to the effects ruleset cache. */
//    {
//      for (j = 0;
//	   (item = secfile_lookup_str_default(file, null, "%s.effect%d.name",
//					      sec[i], j));
//	   j++) {
//	int value;
//	enum effect_type eff;
//	enum effect_range range;
//	boolean survives;
//	enum effect_req_type type;
//	int req, equiv;
//
//	if ((eff = effect_type_from_str(item)) == EFT_LAST) {
//	  util.freelog(Log.LOG_ERROR,
//		  /* TRANS: Obscure ruleset error */
//		  ("Building %s lists unknown effect type: \"%s\" (%s)"),
//		  b.name, item, filename);
//	  continue;
//	}
//
//	item = secfile_lookup_str_default(file, null, "%s.effect%d.range",
//	    				  sec[i], j);
//	if (item) {
//	  if ((range = effect_range_from_str(item)) == EFR_LAST) {
//	    util.freelog(Log.LOG_ERROR,
//		    /* TRANS: Obscure ruleset error */
//		    ("Building %s lists bad range: \"%s\" (%s)"),
//		    b.name, item, filename);
//	    continue;
//	  }
//	} else {
//	  range = EFR_CITY;
//	}
//
//	survives = secfile_lookup_bool_default(file, false, "%s.effect%d.survives",
//					       sec[i], j);
//
//	value = secfile_lookup_int_default(file, 1, "%s.effect%d.value",
//					   sec[i], j);
//
//	/* Sometimes the ruleset will have to list "" here. */
//	item = secfile_lookup_str_default(file, "", "%s.effect%d.equiv",
//	    				  sec[i], j);
//	if (item[0] != '\0') {
//	  if ((equiv = find_effect_group_id(item)) == -1) {
//	    util.freelog(Log.LOG_ERROR,
//		    /* TRANS: Obscure ruleset error */
//		    ("Building %s lists bad effect group: \"%s\" (%s)"),
//		    b.name, item, filename);
//	    continue;
//	  }
//	} else {
//	  equiv = -1;
//	}
//
//	/* Sometimes the ruleset will have to list "" here. */
//	item = secfile_lookup_str_default(file, "", "%s.effect%d.req_type",
//	    				  sec[i], j);
//	if (item[0] != '\0') {
//	  if ((type = effect_req_type_from_str(item)) == REQ_LAST) {
//	    util.freelog(Log.LOG_ERROR,
//		    /* TRANS: Obscure ruleset error */
//		    ("Building %s has unknown req type: \"%s\" (%s)"),
//		    b.name, item, filename);
//	    continue;
//          }
//
//	  item = secfile_lookup_str_default(file, null, "%s.effect%d.req",
//					    sec[i], j);
//
//	  if (!item) {
//	    util.freelog(Log.LOG_ERROR,
//		    /* TRANS: Obscure ruleset error */
//		    "Building %s has missing requirement data (%s)",
//		    b.name, filename);
//	    continue;
//	  } else {
//	    req = parse_effect_requirement(i, type, item);
//	  }
//        } else {
//          type = REQ_NONE;
//          req = 0;
//        }
//
//	ruleset_cache_add(i, eff, range, survives, value, type, req, equiv);
//      }
//    }
//
//    b.graphic_str = String.format(
//	       secfile_lookup_str_default(file, "-", "%s.graphic", sec[i]));
//    b.graphic_alt = String.format(
//	    secfile_lookup_str_default(file, "-", "%s.graphic_alt", sec[i]));
//
//    b.soundtag = String.format(
//	       secfile_lookup_str_default(file, "-", "%s.sound", sec[i]));
//    b.soundtag_alt = String.format(
//	       secfile_lookup_str_default(file, "-", "%s.sound_alt",
//					  sec[i]));
//    b.helptext = lookup_helptext(file, sec[i]);
//  }
//
//  /*
//   * Hack to allow code that explicitly checks for Palace or City Walls
//   * to work.
//   */
//  Game.game.palace_building = get_building_for_effect(EFT_CAPITAL_CITY);
//  if (Game.game.palace_building == Improvement.B_LAST) {
//    util.freelog(LOG_FATAL,
//	    /* TRANS: Obscure ruleset error */
//	    "Cannot find any palace building");
//    exit(EXIT_FAILURE);
//  }
//
//  Game.game.land_defend_building = get_building_for_effect(effect_type.EFT_LAND_DEFEND);
//  if (Game.game.land_defend_building == Improvement.B_LAST) {
//    util.freelog(LOG_FATAL,
//	    /* TRANS: Obscure ruleset error */
//	    "Cannot find any land defend building");
//    exit(EXIT_FAILURE);
//  }
//
//  /* Some more consistency checking: */
//  for (int i = 0; i < Game.game.num_impr_types; i++) {
//    b = &Improvement.improvement_types[i];
//    if (Improvement.improvement_exists(i)) {
//      if (!tech_exists(b.tech_req)) {
//	util.freelog(Log.LOG_ERROR,
//		"improvement \"%s\": depends on removed tech \"%s\" (%s)",
//		b.name, advances[b.tech_req].name, filename);
//	b.tech_req = Tech_H.A_LAST;
//      }
//      if (b.obsolete_by != Tech_H.A_LAST
//	  && (b.obsolete_by == A_NONE || !tech_exists(b.obsolete_by))) {
//	util.freelog(Log.LOG_ERROR,
//		"improvement \"%s\": obsoleted by removed tech \"%s\" (%s)",
//		b.name, advances[b.obsolete_by].name, filename);
//	b.obsolete_by = Tech_H.A_LAST;
//      }
//    }
//  } ;
//
//  Game.game.aqueduct_size = secfile_lookup_int(file, "b_special.aqueduct_size");
//
//  item = secfile_lookup_str(file, "b_special.default");
//  if (*item != '\0') {
//    Game.game.default_building = find_improvement_by_name(item);
//    if (Game.game.default_building == Improvement.B_LAST) {
//      util.freelog(Log.LOG_ERROR,
//	      /* TRANS: Obscure ruleset error */
//	      ("Bad value \"%s\" for b_special.default (%s)"),
//	      item, filename);
//    }
//  } else {
//    Game.game.default_building = Improvement.B_LAST;
//  }
//
//  /* FIXME: remove all of the following when gen-impr implemented... */
//
//  Game.game.rtech.cathedral_plus =
//    lookup_tech(file, "b_special", "cathedral_plus", false, filename, null);
//  Game.game.rtech.cathedral_minus =
//    lookup_tech(file, "b_special", "cathedral_minus", false, filename, null);
//  Game.game.rtech.colosseum_plus =
//    lookup_tech(file, "b_special", "colosseum_plus", false, filename, null);
//  Game.game.rtech.temple_plus =
//    lookup_tech(file, "b_special", "temple_plus", false, filename, null);
//
//  free(sec);
//  section_file_check_unused(file, filename);
//  section_file_free(file);
//}
//
///**************************************************************************
//  ...  
//**************************************************************************/
//static void load_terrain_names(section_file file)
//{
//  int nval;
//  char **sec;
//  final String filename = secfile_filename(file);
//
//  () section_file_lookup(file, "datafile.description");	/* unused */
//
//  /* terrain names */
//
//  sec = secfile_get_secnames_prefix(file, "terrain_", &nval);
//  if (nval == 0) {
//    /* TRANS: Obscure ruleset error.  "%s" is a filename. */
//    util.freelog(LOG_FATAL, "Ruleset doesn't have any terrains (%s)",
//	    filename);
//    exit(EXIT_FAILURE);
//  }
//  Game.game.terrain_count = nval;
//
//  terrain_type_iterate(i) {
//    char *name = secfile_lookup_str(file, "%s.terrain_name", sec[i]);
//
//    name_strlcpy(get_tile_type(i).terrain_name_orig, name);
//    if (0 == strcmp(get_tile_type(i).terrain_name_orig, "unused")) {
//      get_tile_type(i).terrain_name_orig[0] = '\0';
//    }
//    get_tile_type(i).terrain_name = get_tile_type(i).terrain_name_orig;
//  } terrain_type_iterate_end;
//
//  free(sec);
//}
//
///**************************************************************************
//...  
//**************************************************************************/
//static void load_ruleset_terrain(section_file file)
//{
//  char *datafile_options;
//  int nval;
//  char **sec;
//  int j;
//  final String filename = secfile_filename(file);
//
//  datafile_options =
//    check_ruleset_capabilities(file, "+1.9", filename);
//
//  /* options */
//  Map.terrain_control.may_road =
//    secfile_lookup_bool_default(file, true, "options.may_road");
//  Map.terrain_control.may_irrigate =
//    secfile_lookup_bool_default(file, true, "options.may_irrigate");
//  Map.terrain_control.may_mine =
//    secfile_lookup_bool_default(file, true, "options.may_mine");
//  Map.terrain_control.may_transform =
//    secfile_lookup_bool_default(file, true, "options.may_transform");
//
//  /* parameters */
//
//  Map.terrain_control.ocean_reclaim_requirement_pct
//    = secfile_lookup_int_default(file, 9,
//				 "parameters.ocean_reclaim_requirement_pct");
//  Map.terrain_control.land_channel_requirement_pct
//    = secfile_lookup_int_default(file, 9,
//				 "parameters.land_channel_requirement_pct");
//  Map.terrain_control.river_move_mode =
//    secfile_lookup_int_default(file, RMV_FAST_STRICT, "parameters.river_move_mode");
//  Map.terrain_control.river_defense_bonus =
//    secfile_lookup_int_default(file, 50, "parameters.river_defense_bonus");
//  Map.terrain_control.river_trade_incr =
//    secfile_lookup_int_default(file, 1, "parameters.river_trade_incr");
//  {
//    char *s = secfile_lookup_str_default(file, "",
//      "parameters.river_help_text");
//    Map.terrain_control.river_help_text = s;
//  }
//  Map.terrain_control.fortress_defense_bonus =
//    secfile_lookup_int_default(file, 100, "parameters.fortress_defense_bonus");
//  Map.terrain_control.road_superhighway_trade_bonus =
//    secfile_lookup_int_default(file, 50, "parameters.road_superhighway_trade_bonus");
//  Map.terrain_control.rail_food_bonus =
//    secfile_lookup_int_default(file, 0, "parameters.rail_food_bonus");
//  Map.terrain_control.rail_shield_bonus =
//    secfile_lookup_int_default(file, 50, "parameters.rail_shield_bonus");
//  Map.terrain_control.rail_trade_bonus =
//    secfile_lookup_int_default(file, 0, "parameters.rail_trade_bonus");
//  Map.terrain_control.farmland_supermarket_food_bonus =
//    secfile_lookup_int_default(file, 50, "parameters.farmland_supermarket_food_bonus");
//  Map.terrain_control.pollution_food_penalty =
//    secfile_lookup_int_default(file, 50, "parameters.pollution_food_penalty");
//  Map.terrain_control.pollution_shield_penalty =
//    secfile_lookup_int_default(file, 50, "parameters.pollution_shield_penalty");
//  Map.terrain_control.pollution_trade_penalty =
//    secfile_lookup_int_default(file, 50, "parameters.pollution_trade_penalty");
//  Map.terrain_control.fallout_food_penalty =
//    secfile_lookup_int_default(file, 50, "parameters.fallout_food_penalty");
//  Map.terrain_control.fallout_shield_penalty =
//    secfile_lookup_int_default(file, 50, "parameters.fallout_shield_penalty");
//  Map.terrain_control.fallout_trade_penalty =
//    secfile_lookup_int_default(file, 50, "parameters.fallout_trade_penalty");
//
//  sec = secfile_get_secnames_prefix(file, "terrain_", &nval);
//
//  /* terrain details */
//
//  terrain_type_iterate(i) {
//    tile_type t = get_tile_type(i);
//      char *s1_name, *s2_name, **slist;
//
//      t.graphic_str = String.format(
//		 secfile_lookup_str(file,"%s.graphic", sec[i]));
//      t.graphic_alt = String.format(
//		 secfile_lookup_str(file,"%s.graphic_alt", sec[i]));
//
//      t.identifier = secfile_lookup_str(file, "%s.identifier", sec[i])[0];
//      for (j = Terrain_H.T_FIRST; j < i; j++) {
//	if (t.identifier == get_tile_type(j).identifier) {
//	  util.freelog(LOG_FATAL,
//		  /* TRANS: message for an obscure ruleset error. */
//		  "Terrains %s and %s have the same identifier.",
//		  t.terrain_name, get_tile_type(j).terrain_name);
//	  exit(EXIT_FAILURE);
//	}
//      }
//      if (t.identifier == UNKNOWN_TERRAIN_IDENTIFIER) {
//	/* TRANS: message for an obscure ruleset error. */
//	util.freelog(LOG_FATAL, ("'%c' cannot be used as a terrain identifier; " +
//			     "it is reserved."), UNKNOWN_TERRAIN_IDENTIFIER);
//	exit(EXIT_FAILURE);
//      }
//
//      t.movement_cost = secfile_lookup_int(file, "%s.movement_cost", sec[i]);
//      t.defense_bonus = secfile_lookup_int(file, "%s.defense_bonus", sec[i]);
//
//      t.food = secfile_lookup_int(file, "%s.food", sec[i]);
//      t.shield = secfile_lookup_int(file, "%s.shield", sec[i]);
//      t.trade = secfile_lookup_int(file, "%s.trade", sec[i]);
//
//      s1_name = secfile_lookup_str(file, "%s.special_1_name", sec[i]);
//      name_strlcpy(t.special_1_name_orig, s1_name);
//      if (0 == strcmp(t.special_1_name_orig, "none")) {
//	t.special_1_name_orig[0] = '\0';
//      }
//      t.special_1_name = t.special_1_name_orig;
//      t.food_special_1 = secfile_lookup_int(file, "%s.food_special_1", sec[i]);
//      t.shield_special_1 = secfile_lookup_int(file, "%s.shield_special_1", sec[i]);
//      t.trade_special_1 = secfile_lookup_int(file, "%s.trade_special_1", sec[i]);
//
//      s2_name = secfile_lookup_str(file, "%s.special_2_name", sec[i]);
//      name_strlcpy(t.special_2_name_orig, s2_name);
//      if (0 == strcmp(t.special_2_name_orig, "none")) {
//	t.special_2_name_orig[0] = '\0';
//      }
//      t.special_2_name = t.special_2_name_orig;
//      t.food_special_2 = secfile_lookup_int(file, "%s.food_special_2", sec[i]);
//      t.shield_special_2 = secfile_lookup_int(file, "%s.shield_special_2", sec[i]);
//      t.trade_special_2 = secfile_lookup_int(file, "%s.trade_special_2", sec[i]);
//      for(j=0; j<2; j++) {
//	sz_strlcpy(t.special[j].graphic_str,
//		   secfile_lookup_str(file,"%s.graphic_special_%d",
//				      sec[i], j+1));
//	sz_strlcpy(t.special[j].graphic_alt,
//		   secfile_lookup_str(file,"%s.graphic_special_%da",
//				      sec[i], j+1));
//      }
//
//      t.road_trade_incr =
//	secfile_lookup_int(file, "%s.road_trade_incr", sec[i]);
//      t.road_time = secfile_lookup_int(file, "%s.road_time", sec[i]);
//
//      t.irrigation_result =
//	lookup_terrain(secfile_lookup_str(file, "%s.irrigation_result", sec[i]), i);
//      t.irrigation_food_incr =
//	secfile_lookup_int(file, "%s.irrigation_food_incr", sec[i]);
//      t.irrigation_time = secfile_lookup_int(file, "%s.irrigation_time", sec[i]);
//
//      t.mining_result =
//	lookup_terrain(secfile_lookup_str(file, "%s.mining_result", sec[i]), i);
//      t.mining_shield_incr =
//	secfile_lookup_int(file, "%s.mining_shield_incr", sec[i]);
//      t.mining_time = secfile_lookup_int(file, "%s.mining_time", sec[i]);
//
//      t.transform_result =
//	lookup_terrain(secfile_lookup_str(file, "%s.transform_result", sec[i]), i);
//      t.transform_time = secfile_lookup_int(file, "%s.transform_time", sec[i]);
//      t.rail_time = 
//          secfile_lookup_int_default(file, 3, "%s.rail_time", sec[i]);
//      t.airbase_time = 
//          secfile_lookup_int_default(file, 3, "%s.airbase_time", sec[i]);
//      t.fortress_time = 
//          secfile_lookup_int_default(file, 3, "%s.fortress_time", sec[i]);
//      t.clean_pollution_time = 
//         secfile_lookup_int_default(file, 3, "%s.clean_pollution_time", sec[i]);
//      t.clean_fallout_time = 
//          secfile_lookup_int_default(file, 3, "%s.clean_fallout_time", sec[i]);
//
//      t.warmer_wetter_result
//	= lookup_terrain(secfile_lookup_str(file, "%s.warmer_wetter_result",
//					    sec[i]), i);
//      t.warmer_drier_result
//	= lookup_terrain(secfile_lookup_str(file, "%s.warmer_drier_result",
//					    sec[i]), i);
//      t.cooler_wetter_result
//	= lookup_terrain(secfile_lookup_str(file, "%s.cooler_wetter_result",
//					    sec[i]), i);
//      t.cooler_drier_result
//	= lookup_terrain(secfile_lookup_str(file, "%s.cooler_drier_result",
//					    sec[i]), i);
//
//      slist = secfile_lookup_str_vec(file, &nval, "%s.flags", sec[i]);
//      BV_CLR_ALL(t.flags);
//      for (j = 0; j < nval; j++) {
//	final String sval = slist[j];
//	enum terrain_flag_id flag = terrain_flag_from_str(sval);
//
//	if (flag == TER_LAST) {
//	  /* TRANS: message for an obscure ruleset error. */
//	  util.freelog(LOG_FATAL, "Terrain %s has unknown flag %s",
//		  t.terrain_name, sval);
//	  exit(EXIT_FAILURE);
//	} else {
//	  BV_SET(t.flags, flag);
//	}
//      }
//      free(slist);
//      
//      t.helptext = lookup_helptext(file, sec[i]);
//  } terrain_type_iterate_end;
//
//  free(sec);
//  section_file_check_unused(file, filename);
//  section_file_free(file);
//}
//
///**************************************************************************
//  ...  
//**************************************************************************/
//static void load_government_names(section_file file)
//{
//  int nval;
//  char **sec;
//  final String filename = secfile_filename(file);
//
//  () section_file_lookup(file, "datafile.description");	/* unused */
//
//  sec = secfile_get_secnames_prefix(file, "government_", &nval);
//  if (nval == 0) {
//    util.freelog(LOG_FATAL, "No governments!? (%s)", filename);
//    exit(EXIT_FAILURE);
//  } else if(nval > G_MAGIC) {
//    /* upper limit is really about 255 for 8-bit id values, but
//       use G_MAGIC elsewhere as a sanity check, and should be plenty
//       big enough --dwp */
//    util.freelog(LOG_FATAL, "Too many governments! (%d, max %d; %s)",
//	    nval, G_MAGIC, filename);
//    exit(EXIT_FAILURE);
//  }
//  governments_alloc(nval);
//
//  /* Government names are needed early so that get_government_by_name will
//   * work. */
//  government_iterate(gov) {
//    char *name = secfile_lookup_str(file, "%s.name", sec[gov.index]);
//
//    name_strlcpy(gov.name_orig, name);
//    gov.name = gov.name_orig;
//  } government_iterate_end;
//  free(sec);
//}
//
///**************************************************************************
//...  
//**************************************************************************/
//static void load_ruleset_governments(section_file file)
//{
//  int i, j, nval;
//  char *c;
//  char **sec, **slist;
//  final String filename = secfile_filename(file);
//
//  () check_ruleset_capabilities(file, "+1.9", filename);
//
//  sec = secfile_get_secnames_prefix(file, "government_", &nval);
//
//  Game.game.default_government
//    = lookup_government(file, "governments.default", filename);
//  
//  Game.game.government_when_anarchy
//    = lookup_government(file, "governments.when_anarchy", filename);
//  
//  Game.game.ai_goal_government
//    = lookup_government(file, "governments.ai_goal", filename);
//
//  util.freelog(Log.LOG_DEBUG, "govs: def %d, anarchy %d, ai_goal %d",
//	  Game.game.default_government, Game.game.government_when_anarchy,
//	  Game.game.ai_goal_government);
//  
//  /* Because player_init is called before rulesets are loaded we set
//   * all players governments here, if they have not been previously
//   * set (eg by loading Game.game).
//   */
//  for(i=0; i<Shared_H.MAX_NUM_PLAYERS+Shared_H.MAX_NUM_BARBARIANS; i++) {
//    if (Game.game.players[i].government == G_MAGIC) {
//      Game.game.players[i].government = Game.game.default_government;
//    }
//    if (Game.game.players[i].target_government == G_MAGIC) {
//      Game.game.players[i].target_government = Game.game.default_government;
//    }
//  }
//
//  /* easy ones: */
//  government_iterate(g) {
//    int i = g.index;
//    
//    g.required_tech
//      = lookup_tech(file, sec[i], "tech_req", false, filename, g.name);
//    
//    g.graphic_str = String.format(
//	       secfile_lookup_str(file, "%s.graphic", sec[i]));
//    g.graphic_alt = String.format(
//	       secfile_lookup_str(file, "%s.graphic_alt", sec[i]));
//    
//    g.martial_law_max = secfile_lookup_int(file, "%s.martial_law_max", sec[i]);
//    g.martial_law_per = secfile_lookup_int(file, "%s.martial_law_per", sec[i]);
//    g.max_rate = secfile_lookup_int(file, "%s.max_single_rate", sec[i]);
//    g.civil_war = secfile_lookup_int(file, "%s.civil_war_chance", sec[i]);
//    g.empire_size_mod = secfile_lookup_int(file, "%s.empire_size_mod", sec[i]);
//    g.empire_size_inc =
//      secfile_lookup_int_default(file, 0, "%s.empire_size_inc", sec[i]);
//    g.rapture_size = secfile_lookup_int(file, "%s.rapture_size", sec[i]);
//    
//    g.free_happy
//      = lookup_city_cost(file, sec[i], "unit_free_unhappy", filename);
//    g.free_shield
//      = lookup_city_cost(file, sec[i], "unit_free_shield", filename);
//    g.free_food
//      = lookup_city_cost(file, sec[i], "unit_free_food", filename);
//    g.free_gold
//      = lookup_city_cost(file, sec[i], "unit_free_gold", filename);
//
//    g.unit_happy_cost_factor
//      = secfile_lookup_int(file, "%s.unit_unhappy_factor", sec[i]);
//    g.unit_shield_cost_factor
//      = secfile_lookup_int(file, "%s.unit_shield_factor", sec[i]);
//    g.unit_food_cost_factor
//      = secfile_lookup_int(file, "%s.unit_food_factor", sec[i]);
//    g.unit_gold_cost_factor
//      = secfile_lookup_int(file, "%s.unit_gold_factor", sec[i]);
//
//    g.corruption_level
//      = secfile_lookup_int(file, "%s.corruption_level", sec[i]);
//    g.fixed_corruption_distance
//      = secfile_lookup_int(file, "%s.corruption_fixed_distance", sec[i]);
//    g.corruption_distance_factor
//      = secfile_lookup_int(file, "%s.corruption_distance_factor", sec[i]);
//    g.extra_corruption_distance
//      = secfile_lookup_int(file, "%s.corruption_extra_distance", sec[i]);
//    g.corruption_max_distance_cap
//      = secfile_lookup_int_default(file, 36, 
//        "%s.corruption_max_distance_cap", sec[i]); 
//
//    g.waste_level
//      = secfile_lookup_int(file, "%s.waste_level", sec[i]);
//    g.fixed_waste_distance
//      = secfile_lookup_int(file, "%s.waste_fixed_distance", sec[i]);
//    g.waste_distance_factor
//      = secfile_lookup_int(file, "%s.waste_distance_factor", sec[i]);
//    g.extra_waste_distance
//      = secfile_lookup_int(file, "%s.waste_extra_distance", sec[i]);
//    g.waste_max_distance_cap
//      = secfile_lookup_int_default(file, 36, "%s.waste_max_distance_cap", sec[i]); 
//
//    g.trade_bonus
//      = secfile_lookup_int(file, "%s.production_trade_bonus", sec[i]);
//    g.shield_bonus
//      = secfile_lookup_int(file, "%s.production_shield_bonus", sec[i]);
//    g.food_bonus
//      = secfile_lookup_int(file, "%s.production_food_bonus", sec[i]);
//
//    g.celeb_trade_bonus
//      = secfile_lookup_int(file, "%s.production_trade_bonus,1", sec[i]);
//    g.celeb_shield_bonus
//      = secfile_lookup_int(file, "%s.production_shield_bonus,1", sec[i]);
//    g.celeb_food_bonus
//      = secfile_lookup_int(file, "%s.production_food_bonus,1", sec[i]);
//
//    g.trade_before_penalty
//      = secfile_lookup_int(file, "%s.production_trade_penalty", sec[i]);
//    g.shields_before_penalty
//      = secfile_lookup_int(file, "%s.production_shield_penalty", sec[i]);
//    g.food_before_penalty
//      = secfile_lookup_int(file, "%s.production_food_penalty", sec[i]);
//
//    g.celeb_trade_before_penalty
//      = secfile_lookup_int(file, "%s.production_trade_penalty,1", sec[i]);
//    g.celeb_shields_before_penalty
//      = secfile_lookup_int(file, "%s.production_shield_penalty,1", sec[i]);
//    g.celeb_food_before_penalty
//      = secfile_lookup_int(file, "%s.production_food_penalty,1", sec[i]);
//    
//    g.helptext = lookup_helptext(file, sec[i]);
//  } government_iterate_end;
//
//  
//  /* flags: */
//  government_iterate(g) {
//    g.flags = 0;
//    slist = secfile_lookup_str_vec(file, &nval, "%s.flags", sec[g.index]);
//    for(j=0; j<nval; j++) {
//      char *sval = slist[j];
//      enum government_flag_id flag = government_flag_from_str(sval);
//      if (strcmp(sval, "-") == 0) {
//        continue;
//      }
//      if (flag == G_LAST_FLAG) {
//        util.freelog(LOG_FATAL, "government %s has unknown flag %s", g.name, sval);
//        exit(EXIT_FAILURE);
//      } else {
//        g.flags |= (1<<flag);
//      }
//    }
//    free(slist);
//  } government_iterate_end;
//
//  /* titles */
//  government_iterate(g) {
//    int i = g.index;
//    ruler_title title;
//
//    g.num_ruler_titles = 1;
//    g.ruler_titles = fc_calloc(1, sizeof(struct ruler_title));
//    title = &(g.ruler_titles[0]);
//
//    title.nation = DEFAULT_TITLE;
//    title.male_title_orig = String.format(
//	       secfile_lookup_str(file, "%s.ruler_male_title", sec[i]));
//    title.male_title = title.male_title_orig;
//    title.female_title_orig = String.format(
//	       secfile_lookup_str(file, "%s.ruler_female_title", sec[i]));
//    title.female_title = title.female_title_orig;
//  } government_iterate_end;
//
//  /* ai tech_hints: */
//  j = -1;
//  while((c = secfile_lookup_str_default(file, null,
//					"governments.ai_tech_hints%d.tech",
//					++j))) {
//    ai_gov_tech_hint hint = &ai_gov_tech_hints[j];
//
//    if (j >= MAX_NUM_TECH_LIST) {
//      util.freelog(LOG_FATAL, "Too many ai tech_hints in %s", filename);
//      exit(EXIT_FAILURE);
//    }
//    hint.tech = find_tech_by_name(c);
//    if (hint.tech == Tech_H.A_LAST) {
//      util.freelog(LOG_FATAL, "Could not match tech %s for gov ai_tech_hint %d (%s)",
//	      c, j, filename);
//      exit(EXIT_FAILURE);
//    }
//    if (!tech_exists(hint.tech)) {
//      util.freelog(LOG_FATAL, "For gov ai_tech_hint %d, tech \"%s\" is removed (%s)",
//	      j, c, filename);
//      exit(EXIT_FAILURE);
//    }
//    hint.turns_factor =
//      secfile_lookup_int(file, "governments.ai_tech_hints%d.turns_factor", j);
//    hint.final_factor =
//      secfile_lookup_int(file, "governments.ai_tech_hints%d.final_factor", j);
//    hint.get_first =
//      secfile_lookup_bool(file, "governments.ai_tech_hints%d.get_first", j);
//    hint.done =
//      secfile_lookup_bool(file, "governments.ai_tech_hints%d.done", j);
//  }
//  if (j<MAX_NUM_TECH_LIST) {
//    ai_gov_tech_hints[j].tech = Tech_H.A_LAST;
//  }
//
//  free(sec);
//  section_file_check_unused(file, filename);
//  section_file_free(file);
//}
//
///**************************************************************************
//  Send information in packet_ruleset_control (numbers of units etc, and
//  other miscellany) to specified connections.
//**************************************************************************/
//static void send_ruleset_control(Speclists<Connection> dest)
//{
//  struct packet_ruleset_control packet;
//  int i;
//
//  packet.aqueduct_size = Game.game.aqueduct_size;
//  packet.add_to_size_limit = Game.game.add_to_size_limit;
//  packet.notradesize = Game.game.notradesize;
//  packet.fulltradesize = Game.game.fulltradesize;
//
//  packet.rtech_cathedral_plus = Game.game.rtech.cathedral_plus;
//  packet.rtech_cathedral_minus = Game.game.rtech.cathedral_minus;
//  packet.rtech_colosseum_plus = Game.game.rtech.colosseum_plus;
//  packet.rtech_temple_plus = Game.game.rtech.temple_plus;
//
//  for(i=0; i<MAX_NUM_TECH_LIST; i++) {
//    packet.rtech_partisan_req[i] = Game.game.rtech.partisan_req[i];
//  }
//
//  packet.government_count = Game.game.government_count;
//  packet.government_when_anarchy = Game.game.government_when_anarchy;
//  packet.default_government = Game.game.default_government;
//
//  packet.num_unit_types = Game.game.num_unit_types;
//  packet.num_impr_types = Game.game.num_impr_types;
//  packet.num_tech_types = Game.game.num_tech_types;
//  packet.borders = Game.game.borders;
//  packet.happyborders = Game.game.happyborders;
//  packet.slow_invasions = Game.game.slow_invasions;
//
//  packet.nation_count = Game.game.nation_count;
//  packet.playable_nation_count = Game.game.playable_nation_count;
//  packet.style_count = Game.game.styles_count;
//  packet.terrain_count = Game.game.terrain_count;
//
//  for(i = 0; i < MAX_NUM_TEAMS; i++) {
//    sz_strlcpy(packet.team_name[i], team_get_by_id(i).name);
//  }
//
//  packet.default_building = Game.game.default_building;
//
//  lsend_packet_ruleset_control(dest, &packet);
//}
//
///**************************************************************************
//This checks if nations[pos] leader names are not already defined in any 
//previous nation, or twice in its own leader name table.
//If not return null, if yes return pointer to name which is repeated.
//**************************************************************************/
//static char *check_leader_names(int nation)
//{
//  int k;
//  nation_type pnation = Nation.get_nation_by_idx(nation);
//
//  for (k = 0; k < pnation.leader_count; k++) {
//    char *leader = pnation.leaders[k].name;
//    int i;
//    int nation2;
//
//    for (i = 0; i < k; i++) {
//      if (0 == strcmp(leader, pnation.leaders[i].name)) {
//	return leader;
//      }
//    }
//
//    for (nation2 = 0; nation2 < nation; nation2++) {
//      nation_type pnation2 = Nation.get_nation_by_idx(nation2);
//
//      for (i = 0; i < pnation2.leader_count; i++) {
//	if (0 == strcmp(leader, pnation2.leaders[i].name)) {
//	  return leader;
//	}
//      }
//    }
//  }
//  return null;
//}
//
///**************************************************************************
//  ...
//**************************************************************************/
//static void load_nation_names(section_file file)
//{
//  char **sec;
//  int i, j;
//
//  () section_file_lookup(file, "datafile.description");	/* unused */
//
//  sec = secfile_get_secnames_prefix(file, "nation", &Game.game.nation_count);
//  Game.game.playable_nation_count = Game.game.nation_count - 2;
//  util.freelog(Log.LOG_VERBOSE, "There are %d nations defined", Game.game.playable_nation_count);
//
//  if (Game.game.playable_nation_count < 0) {
//    util.freelog(LOG_FATAL,
//	    "There must be at least one nation defined; number is %d",
//	    Game.game.playable_nation_count);
//    exit(EXIT_FAILURE);
//  }
//  nations_alloc(Game.game.nation_count);
//
//  for( i=0; i<Game.game.nation_count; i++) {
//    char *name        = secfile_lookup_str(file, "%s.name", sec[i]);
//    char *name_plural = secfile_lookup_str(file, "%s.plural", sec[i]);
//    nation_type pl = Nation.get_nation_by_idx(i);
//
//    name_strlcpy(pl.name_orig, name);
//    name_strlcpy(pl.name_plural_orig, name_plural);
//
//    /* These are overwritten later when translations are done.  However
//     * in the meantime some code (like the check below) accesses the name
//     * directly.  This isn't great but it would take some work to fix. */
//    pl.name = pl.name_orig;
//    pl.name_plural = pl.name_plural_orig;
//
//    /* Check if nation name is already defined. */
//    for(j = 0; j < i; j++) {
//      if (0 == strcmp(get_nation_name(j), pl.name)
//	  || 0 == strcmp(Nation.get_nation_name_plural(j), pl.name_plural)) {
//        util.freelog(LOG_FATAL,
//		"Nation %s (the %s) defined twice; " +
//		"in section nation%d and section nation%d",
//		pl.name, pl.name_plural, j, i);
//        exit(EXIT_FAILURE);
//      }
//    }
//  }
//  free(sec);
//}
//
///**************************************************************************
//  This function loads a city name list from a section file.  The file and
//  two section names (which will be concatenated) are passed in.  The
//  malloc'ed city name list (which is all filled out) will be returned.
//**************************************************************************/
//static struct city_name* load_city_name_list(section_file file,
//					     final String secfile_str1,
//					     final String secfile_str2)
//{
//  int dim, j;
//  city_name city_names;
//  int value;
//
//  /* First we read the strings from the section file (above). */
//  char **cities = secfile_lookup_str_vec(file, &dim, "%s%s",
//                                         secfile_str1, secfile_str2);
//
//  /*
//   * Now we allocate enough room in the city_names array to store
//   * all the name data.  The array is null-terminated by
//   * having a null name at the end.
//   */
//  city_names = fc_calloc(dim + 1, sizeof(struct city_name));
//  city_names[dim].name = null;
//
//  /*
//   * Each string will be of the form
//   * "<cityname> (<label>, <label>, ...)".  The cityname is just the
//   * name for this city, while each "label" matches a terrain type
//   * for the city (or "river"), with a preceeding ! to negate it.
//   * The parentheses are optional (but necessary to have the
//   * settings, of course).  Our job is now to parse this into the
//   * city_name structure.
//   */
//  for (j = 0, value = 1; j < dim; j++, value++) {
//    char *name = strchr(cities[j], '(');
//
//    /*
//     * Now we wish to determine values for all of the city labels.
//     * A value of 0 means no preference (which is necessary so that
//     * the use of this is optional); -1 means the label is negated
//     * and 1 means it's labelled.  Mostly the parsing just involves
//     * a lot of ugly string handling...
//     */
//    memset(city_names[j].terrain, 0,
//	   Terrain_H.T_COUNT * sizeof(city_names[j].terrain[0]));
//    city_names[j].river = 0;
//
//    if (name) {
//      /*
//       * 0-terminate the original string, then find the
//       * close-parenthesis so that we can make sure we stop there.
//       */
//      char *next = strchr(name + 1, ')');
//      if (!next) {
//	util.freelog(Log.LOG_ERROR,
//	        "Badly formed city name %s in city name " +
//	        "ruleset \"%s%s\": unmatched parenthesis.",
//	        cities[j], secfile_str1, secfile_str2);
//	assert(false);
//      } else { /* if (!next) */
//        name[0] = next[0] = '\0';
//        name++;
//
//        /* Handle the labels one at a time. */
//        do {
//	  int setting;
//
//	  next = strchr(name, ',');
//	  if (next) {
//	    next[0] = '\0';
//	  }
//	  remove_leading_trailing_spaces(name);
//	
//	  /*
//	   * The ! is used to mark a negative, which is recorded
//	   * with a -1.  Otherwise we use a 1.
//	   */
//	  if (name[0] == '!') {
//	    name++;
//	    setting = -1;
//	  } else {
//	    setting = 1;
//	  }
//	
//	  if (mystrcasecmp(name, "river") == 0) {
//	    city_names[j].river = setting;
//	  } else {
//	    /* "handled" tracks whether we find a match (for error handling) */
//	    boolean handled = false;
//	    int type;
//	
//	    for (type = Terrain_H.T_FIRST; type < Terrain_H.T_COUNT && !handled; type++) {
//              /*
//               * Note that at this time (before a call to
//               * translate_data_names) the terrain_name fields contains an
//               * untranslated string.  Note that name of T_RIVER_UNUSED is "".
//               * However this is not a problem because we take care of rivers
//               * separately.
//               */
//	      if (mystrcasecmp(name,
//			       get_tile_type(type).terrain_name) == 0) {
//	        city_names[j].terrain[type] = setting;
//	        handled = true;
//	      }
//	    }
//	    if (!handled) {
//	      util.freelog(Log.LOG_ERROR, "Unreadable terrain description %s " +
//	              "in city name ruleset \"%s%s\" - skipping it.",
//	    	      name, secfile_str1, secfile_str2);
//	      assert(false);
//	    }
//	  }
//	  name = next ? next + 1 : null;
//        } while (name && name[0] != '\0');
//      } /* if (!next) */
//    } /* if (name) */
//    remove_leading_trailing_spaces(cities[j]);
//    city_names[j].name = (cities[j]);
//    if (check_name(city_names[j].name)) {
//      /* The ruleset contains a name that is too long.  This shouldn't
//	 happen - if it does, the author should get immediate feedback */
//      util.freelog(Log.LOG_ERROR, "City name %s in ruleset for %s%s is too long " +
//	      "- shortening it.",
//              city_names[j].name, secfile_str1, secfile_str2);
//      assert(false);
//      city_names[j].name[MAX_LEN_NAME - 1] = '\0';
//    }
//  }
//  if (cities) {
//    free(cities);
//  }
//  return city_names;
//}
//
///**************************************************************************
//Load nations.ruleset file
//**************************************************************************/
//static void load_ruleset_nations(section_file file)
//{
//  char *bad_leader, *g;
//  nation_type pl;
//  government gov;
//  int dim, val, i, j, k, nval;
//  String temp_name;
//  char **techs, **leaders, **sec, **civilwar_nations;
//  final String filename = secfile_filename(file);
//
//  () check_ruleset_capabilities(file, "+1.9", filename);
//
//  sec = secfile_get_secnames_prefix(file, "nation", &nval);
//
//  for( i=0; i<Game.game.nation_count; i++) {
//    pl = Nation.get_nation_by_idx(i);
//
//    /* nation leaders */
//
//    leaders = secfile_lookup_str_vec(file, &dim, "%s.leader", sec[i]);
//    if (dim > MAX_NUM_LEADERS) {
//      util.freelog(Log.LOG_ERROR, "Nation %s: too many leaders; only using %d of %d",
//	      pl.name, MAX_NUM_LEADERS, dim);
//      dim = MAX_NUM_LEADERS;
//    } else if (dim < 1) {
//      util.freelog(LOG_FATAL,
//	      "Nation %s: number of leaders is %d; at least one is required.",
//	      pl.name, dim);
//      exit(EXIT_FAILURE);
//    }
//    pl.leader_count = dim;
//    pl.leaders = fc_malloc(sizeof(*pl.leaders) * pl.leader_count);
//    for(j = 0; j < dim; j++) {
//      pl.leaders[j].name = (leaders[j]);
//      if (check_name(leaders[j])) {
//	pl.leaders[j].name[MAX_LEN_NAME - 1] = '\0';
//      }
//    }
//    free(leaders);
//
//    /* check if leader name is not already defined */
//    if( (bad_leader=check_leader_names(i)) ) {
//        util.freelog(LOG_FATAL, "Nation %s: leader %s defined more than once",
//		pl.name, bad_leader);
//        exit(EXIT_FAILURE);
//    }
//    /* read leaders'sexes */
//    leaders = secfile_lookup_str_vec(file, &dim, "%s.leader_sex", sec[i]);
//    if (dim != pl.leader_count) {
//      util.freelog(LOG_FATAL,
//	      "Nation %s: the leader sex count (%d) " +
//	      "is not equal to the number of leaders (%d)",
//              pl.name, dim, pl.leader_count);
//      exit(EXIT_FAILURE);
//    }
//    for (j = 0; j < dim; j++) {
//      if (0 == mystrcasecmp(leaders[j], "Male")) {
//        pl.leaders[j].is_male = true;
//      } else if (0 == mystrcasecmp(leaders[j], "Female")) {
//        pl.leaders[j].is_male = false;
//      } else {
//        util.freelog(Log.LOG_ERROR,
//		"Nation %s, leader %s: sex must be either Male or Female; " +
//		"assuming Male",
//		pl.name, pl.leaders[j].name);
//	pl.leaders[j].is_male = true;
//      }
//    }
//    free(leaders);
//
//    /* Flags */
//
//    pl.flag_graphic_str = String.format(
//	       secfile_lookup_str(file, "%s.flag", sec[i]));
//    pl.flag_graphic_alt = String.format(
//	       secfile_lookup_str(file, "%s.flag_alt", sec[i]));
//
//    /* Ruler titles */
//
//    j = -1;
//    while ((g = secfile_lookup_str_default(file, null,
//					   "%s.ruler_titles%d.government",
//					   sec[i], ++j))) {
//      char *male_name;
//      char *female_name;
//      
//      male_name = secfile_lookup_str(file, "%s.ruler_titles%d.male_title",
//				     sec[i], j);
//      female_name = secfile_lookup_str(file, "%s.ruler_titles%d.female_title",
//				       sec[i], j);
//
//      gov = find_government_by_name(g);
//      if (gov) {
//	check_name(male_name);
//	check_name(female_name);
//	/* Truncation is handled by set_ruler_title(). */
//	set_ruler_title(gov, i, male_name, female_name);
//      } else {
//	/* Log.LOG_VERBOSE rather than Log.LOG_ERROR so that can use single nation
//	   ruleset file with variety of government ruleset files: */
//        util.freelog(Log.LOG_VERBOSE,
//		"Nation %s: government %s not found", pl.name, g);
//      }
//    }
//
//    /* City styles */
//
//    temp_name = String.format(
//	       secfile_lookup_str_default(file, "-", "%s.city_style", sec[i]));
//    pl.city_style = get_style_by_name(temp_name);
//    if (pl.city_style == -1) {
//      util.freelog(Log.LOG_NORMAL,
//	      "Nation %s: city style %s is unknown, using default.", 
//	      pl.name_plural, temp_name);
//      pl.city_style = 0;
//    }
//
//    while (city_styles[pl.city_style].techreq != A_NONE) {
//      if (pl.city_style == 0) {
//	util.freelog(LOG_FATAL,
//	       "Nation %s: the default city style is not available " +
//	       "from the beginning", pl.name);
//	/* Note that we can't use temp_name here. */
//	exit(EXIT_FAILURE);
//      }
//      util.freelog(Log.LOG_ERROR,
//	      "Nation %s: city style %s is not available from beginning; " +
//	      "using default.", pl.name, temp_name);
//      pl.city_style = 0;
//    }
//
//    /* Civilwar nations */
//
//    civilwar_nations = secfile_lookup_str_vec(file, &dim,
//					      "%s.civilwar_nations", sec[i]);
//    pl.civilwar_nations = fc_malloc(sizeof(int) * (dim + 1));
//
//    for (j = 0, k = 0; k < dim; j++, k++) {
//      /* HACK: At this time, all the names are untranslated and the name_orig
//       * field is empty, so we must call find_nation_by_name instead of
//       * find_nation_by_name_orig. */
//      pl.civilwar_nations[j] = find_nation_by_name(civilwar_nations[k]);
//
//      if (pl.civilwar_nations[j] == Nation_H.NO_NATION_SELECTED) {
//	j--;
//	/* For nation authors this would probably be considered an error.
//	 * But it can happen normally.  The civ1 compatability ruleset only
//	 * uses the nations that were in civ1, so not all of the links will
//	 * exist. */
//	util.freelog(Log.LOG_VERBOSE,
//		"Civil war nation %s for nation %s not defined.",
//		civilwar_nations[k], pl.name);
//      }
//    }
//    free(civilwar_nations);
//
//    /* No test for duplicate nations is performed.  If there is a duplicate
//     * entry it will just cause that nation to have an increased probability
//     * of being chosen. */
//
//    pl.civilwar_nations[j] = Nation_H.NO_NATION_SELECTED;
//
//    /* Load nation specific initial items */
//    lookup_tech_list(file, sec[i], "init_techs", pl.init_techs, filename);
//    lookup_building_list(file, sec[i], "init_buildings", pl.init_buildings,
//			 filename);
//
//    /* AI techs */
//
//    techs = secfile_lookup_str_vec(file, &dim, "%s.tech_goals", sec[i]);
//    if( dim > MAX_NUM_TECH_GOALS ) {
//      util.freelog(Log.LOG_VERBOSE,
//	      "Only %d techs can be used from %d defined for nation %s",
//	      MAX_NUM_TECH_GOALS, dim, pl.name_plural);
//      dim = MAX_NUM_TECH_GOALS;
//    }
//    /* Below Log.LOG_VERBOSE rather than Log.LOG_ERROR so that can use single
//       nation ruleset file with variety of tech ruleset files: */
//    for( j=0; j<dim; j++) {
//      val = find_tech_by_name(techs[j]);
//      if(val == Tech_H.A_LAST) {
//	util.freelog(Log.LOG_VERBOSE, "Could not match tech goal \"%s\" for the %s",
//		techs[j], pl.name_plural);
//      } else if (!tech_exists(val)) {
//	util.freelog(Log.LOG_VERBOSE, "Goal tech \"%s\" for the %s doesn't exist",
//		techs[j], pl.name_plural);
//	val = Tech_H.A_LAST;
//      }
//      if(val != Tech_H.A_LAST && val != A_NONE) {
//	util.freelog(Log.LOG_DEBUG, "%s tech goal (%d) %3d %s", pl.name, j, val, techs[j]);
//	pl.goals.tech[j] = val;
//      }
//    }
//    util.freelog(Log.LOG_DEBUG, "%s %d tech goals", pl.name, j);
//    if(j==0) {
//      util.freelog(Log.LOG_VERBOSE, "No valid goal techs for %s", pl.name);
//    }
//    while( j < MAX_NUM_TECH_GOALS )
//      pl.goals.tech[j++] = A_UNSET;
//    if (techs) free(techs);
//
//    /* AI wonder & government */
//
//    temp_name = String.format( secfile_lookup_str(file, "%s.wonder", sec[i]));
//    val = find_improvement_by_name(temp_name);
//    /* Below Log.LOG_VERBOSE rather than Log.LOG_ERROR so that can use single
//       nation ruleset file with variety of building ruleset files: */
//    /* for any problems, leave as Improvement.B_LAST */
//    if(val == Improvement.B_LAST) {
//      util.freelog(Log.LOG_VERBOSE, "Didn't match goal wonder \"%s\" for %s", temp_name, pl.name);
//    } else if(!Improvement.improvement_exists(val)) {
//      util.freelog(Log.LOG_VERBOSE, "Goal wonder \"%s\" for %s doesn't exist", temp_name, pl.name);
//      val = Improvement.B_LAST;
//    } else if(!Improvement.is_wonder(val)) {
//      util.freelog(Log.LOG_VERBOSE, "Goal wonder \"%s\" for %s not a wonder", temp_name, pl.name);
//      val = Improvement.B_LAST;
//    }
//    pl.goals.wonder = val;
//    util.freelog(Log.LOG_DEBUG, "%s wonder goal %d %s", pl.name, val, temp_name);
//
//    temp_name = String.format( secfile_lookup_str(file, "%s.government", sec[i]));
//    gov = find_government_by_name(temp_name);
//    if(!gov) {
//      /* Log.LOG_VERBOSE rather than Log.LOG_ERROR so that can use single nation
//	 ruleset file with variety of government ruleset files: */
//      util.freelog(Log.LOG_VERBOSE, "Didn't match goal government name \"%s\" for %s",
//	      temp_name, pl.name);
//      val = Game.game.government_when_anarchy;  /* flag value (no goal) (?) */
//    } else {
//      val = gov.index;
//    }
//    pl.goals.government = val;
//
//    /* read "normal" city names */
//
//    pl.city_names = load_city_name_list(file, sec[i], ".cities");
//
//    /* class and legend */
//
//    pl.class =
//	(secfile_lookup_str_default(file, "", "%s.class", sec[i]));
//    if (check_strlen(pl.class, MAX_LEN_NAME, "Class '%s' is too long")) {
//      pl.class[MAX_LEN_NAME - 1] = '\0';
//    }
//
//    pl.legend =
//	(secfile_lookup_str_default(file, "", "%s.legend", sec[i]));
//    if (check_strlen(pl.legend, MAX_LEN_MSG, "Legend '%s' is too long")) {
//      pl.legend[MAX_LEN_MSG - 1] = '\0';
//    }
//  }
//
//  /* Calculate parent nations.  O(n^2) algorithm. */
//  for (i = 0; i < Game.game.nation_count; i++) {
//    int parents[Game.game.nation_count];
//    int count = 0;
//
//    pl = Nation.get_nation_by_idx(i);
//    for (j = 0; j < Game.game.nation_count; j++) {
//      nation_type p2 = Nation.get_nation_by_idx(j);
//
//      for (k = 0; p2.civilwar_nations[k] != Nation_H.NO_NATION_SELECTED; k++) {
//	if (p2.civilwar_nations[k] == i) {
//	  parents[count] = j;
//	  count++;
//	}
//      }
//    }
//
//    assert(sizeof(parents[0]) == sizeof(*pl.parent_nations));
//    pl.parent_nations = fc_malloc((count + 1) * sizeof(parents[0]));
//    memcpy(pl.parent_nations, parents, count * sizeof(parents[0]));
//    pl.parent_nations[count] = Nation_H.NO_NATION_SELECTED;
//  }
//
//  free(sec);
//  section_file_check_unused(file, filename);
//  section_file_free(file);
//}
//
///**************************************************************************
//  ...
//**************************************************************************/
//static void load_citystyle_names(section_file file)
//{
//  char **styles;
//  int nval, i;
//
//  () section_file_lookup(file, "datafile.description");	/* unused */
//
//  /* The sections: */
//  styles = secfile_get_secnames_prefix(file, "citystyle_", &nval);
//  city_styles_alloc(nval);
//
//  /* Get names, so can lookup for replacements: */
//  for (i = 0; i < Game.game.styles_count; i++) {
//    char *style_name = secfile_lookup_str(file, "%s.name", styles[i]);
//    name_strlcpy(city_styles[i].name_orig, style_name);
//    city_styles[i].name = city_styles[i].name_orig;
//  }
//  free(styles);
//}
//
///**************************************************************************
//Load cities.ruleset file
//**************************************************************************/
//static void load_ruleset_cities(section_file file)
//{
//  char **styles, *replacement;
//  int i, nval;
//  final String filename = secfile_filename(file);
//  char **specialist_names;
//
//  () check_ruleset_capabilities(file, "+1.9", filename);
//
//  /* Specialist options */
//  specialist_names = secfile_lookup_str_vec(file, &nval, "specialist.types");
//  if (nval != specialist_type.getSize()) {
//    util.freelog(LOG_FATAL, "There must be exactly %d types of specialist.",
//	    specialist_type.getSize());
//    exit(EXIT_FAILURE);
//  }
//
//  for (i = 0; i < nval; i++) {
//    final String name = specialist_names[i];
//
//    sz_strlcpy(Game.game.rgame.specialists[i].name, name);
//    Game.game.rgame.specialists[i].min_size
//      = secfile_lookup_int(file, "specialist.%s_min_size", name);
//    Game.game.rgame.specialists[i].bonus
//      = secfile_lookup_int(file, "specialist.%s_base_bonus", name);
//    
//  }
//  free(specialist_names);
//
//  Game.game.rgame.changable_tax = 
//    secfile_lookup_bool_default(file, true, "specialist.changable_tax");
//  Game.game.rgame.forced_science = 
//    secfile_lookup_int_default(file, 0, "specialist.forced_science");
//  Game.game.rgame.forced_luxury = 
//    secfile_lookup_int_default(file, 100, "specialist.forced_luxury");
//  Game.game.rgame.forced_gold = 
//    secfile_lookup_int_default(file, 0, "specialist.forced_gold");
//  if (Game.game.rgame.forced_science + Game.game.rgame.forced_luxury
//      + Game.game.rgame.forced_gold != 100) {
//    util.freelog(LOG_FATAL, "Forced taxes do not add up in ruleset!");
//    exit(EXIT_FAILURE);
//  }
//  if (Game.game.rgame.specialists[specialist_type.SP_ELVIS].min_size > 0) {
//    util.freelog(LOG_FATAL, "Elvises must be available without a " +
//	    "city size restriction!");
//    exit(EXIT_FAILURE);
//  }
//
//  /* City Parameters */
//
//  Game.game.add_to_size_limit =
//    secfile_lookup_int_default(file, 9, "parameters.add_to_size_limit");
//
//  /* City Styles ... */
//
//  styles = secfile_get_secnames_prefix(file, "citystyle_", &nval);
//
//  /* Get rest: */
//  for( i=0; i<Game.game.styles_count; i++) {
//    sz_strlcpy(city_styles[i].graphic, 
//	       secfile_lookup_str(file, "%s.graphic", styles[i]));
//    sz_strlcpy(city_styles[i].graphic_alt, 
//	       secfile_lookup_str(file, "%s.graphic_alt", styles[i]));
//    sz_strlcpy(city_styles[i].citizens_graphic, 
//	       secfile_lookup_str_default(file, "-", 
//	    		"%s.citizens_graphic", styles[i]));
//    sz_strlcpy(city_styles[i].citizens_graphic_alt, 
//	       secfile_lookup_str_default(file, "generic", 
//	    		"%s.citizens_graphic_alt", styles[i]));
//    city_styles[i].techreq = lookup_tech(file, styles[i], "tech", true,
//                                         filename, city_styles[i].name);
//    
//    replacement = secfile_lookup_str(file, "%s.replaced_by", styles[i]);
//    if( strcmp(replacement, "-") == 0) {
//      city_styles[i].replaced_by = -1;
//    } else {
//      city_styles[i].replaced_by = get_style_by_name(replacement);
//      if(city_styles[i].replaced_by == -1) {
//        util.freelog(LOG_FATAL, "Style %s replacement %s not found",
//                city_styles[i].name, replacement);
//        exit(EXIT_FAILURE);
//      }
//    }
//  }
//  free(styles);
//
//  section_file_check_unused(file, filename);
//  section_file_free(file);
//}
//
///**************************************************************************
//Load ruleset file
//**************************************************************************/
//static void load_ruleset_game()
//{
//  struct section_file file;
//  char *sval;
//  final String filename;
//  int *food_ini;
//
//  openload_ruleset_file(&file, "Game.game");
//  filename = secfile_filename(&file);
//  () check_ruleset_capabilities(&file, "+1.11.1", filename);
//  () section_file_lookup(&file, "datafile.description");	/* unused */
//
//  Game.game.rgame.min_city_center_food =
//    secfile_lookup_int(&file, "civstyle.min_city_center_food");
//  Game.game.rgame.min_city_center_shield =
//    secfile_lookup_int(&file, "civstyle.min_city_center_shield");
//  Game.game.rgame.min_city_center_trade =
//    secfile_lookup_int(&file, "civstyle.min_city_center_trade");
//
//  /* if the server variable citymindist is set (!= 0) the ruleset
//     setting is overwritten by citymindist */
//  if (Game.game.citymindist == 0) {
//    Game.game.rgame.min_dist_bw_cities =
//	secfile_lookup_int(&file, "civstyle.min_dist_bw_cities");
//    if (Game.game.rgame.min_dist_bw_cities < 1) {
//      util.freelog(Log.LOG_ERROR, "Bad value %i for min_dist_bw_cities. Using 2.",
//	      Game.game.rgame.min_dist_bw_cities);
//      Game.game.rgame.min_dist_bw_cities = 2;
//    }
//  } else {
//    Game.game.rgame.min_dist_bw_cities = Game.game.citymindist;
//  }
//
//  Game.game.rgame.init_vis_radius_sq =
//    secfile_lookup_int(&file, "civstyle.init_vis_radius_sq");
//
//  sval = secfile_lookup_str(&file, "civstyle.hut_overflight" );
//  if (mystrcasecmp(sval, "Nothing") == 0) {
//    Game.game.rgame.hut_overflight = OVERFLIGHT_NOTHING;
//  } else if (mystrcasecmp(sval, "Frighten") == 0) {
//    Game.game.rgame.hut_overflight = OVERFLIGHT_FRIGHTEN;
//  } else {
//    util.freelog(Log.LOG_ERROR, "Bad value %s for hut_overflight. Using " +
//            "\"Frighten\".", sval);
//    Game.game.rgame.hut_overflight = OVERFLIGHT_FRIGHTEN;
//  }
//
//  Game.game.rgame.pillage_select =
//      secfile_lookup_bool(&file, "civstyle.pillage_select");
//
//  sval = secfile_lookup_str(&file, "civstyle.nuke_contamination" );
//  if (mystrcasecmp(sval, "Pollution") == 0) {
//    Game.game.rgame.nuke_contamination = CONTAMINATION_POLLUTION;
//  } else if (mystrcasecmp(sval, "Fallout") == 0) {
//    Game.game.rgame.nuke_contamination = CONTAMINATION_FALLOUT;
//  } else {
//    util.freelog(Log.LOG_ERROR, "Bad value %s for nuke_contamination. Using " +
//            "\"Pollution\".", sval);
//    Game.game.rgame.nuke_contamination = CONTAMINATION_POLLUTION;
//  }
//
//  food_ini = secfile_lookup_int_vec(&file, &Game.game.rgame.granary_num_inis, 
//				    "civstyle.granary_food_ini");
//  if (Game.game.rgame.granary_num_inis > MAX_GRANARY_INIS) {
//    util.freelog(LOG_FATAL,
//	    "Too many granary_food_ini entries; %d is the maximum!",
//	    MAX_GRANARY_INIS);
//    exit(EXIT_FAILURE);
//  } else if (Game.game.rgame.granary_num_inis == 0) {
//    util.freelog(Log.LOG_ERROR, "No values for granary_food_ini. Using 1.");
//    Game.game.rgame.granary_num_inis = 1;
//    Game.game.rgame.granary_food_ini[0] = 1;
//  } else {
//    int i;
//
//    /* check for <= 0 entries */
//    for (i = 0; i < Game.game.rgame.granary_num_inis; i++) {
//      if (food_ini[i] <= 0) {
//	if (i == 0) {
//	  food_ini[i] = 1;
//	} else {
//	  food_ini[i] = food_ini[i - 1];
//	}
//	util.freelog(Log.LOG_ERROR, "Bad value for granary_food_ini[%i]. Using %i.",
//		i, food_ini[i]);
//      }
//      Game.game.rgame.granary_food_ini[i] = food_ini[i];
//    }
//  }
//  free(food_ini);
//
//  Game.game.rgame.granary_food_inc =
//    secfile_lookup_int(&file, "civstyle.granary_food_inc");
//  if (Game.game.rgame.granary_food_inc < 0) {
//    util.freelog(Log.LOG_ERROR, "Bad value %i for granary_food_inc. Using 100.",
//	    Game.game.rgame.granary_food_inc);
//    Game.game.rgame.granary_food_inc = 100;
//  }
//
//  Game.game.rgame.tech_cost_style =
//      secfile_lookup_int(&file, "civstyle.tech_cost_style");
//  if (Game.game.rgame.tech_cost_style < 0 || Game.game.rgame.tech_cost_style > 2) {
//    util.freelog(Log.LOG_ERROR, "Bad value %i for tech_cost_style. Using 0.",
//	    Game.game.rgame.tech_cost_style);
//    Game.game.rgame.tech_cost_style = 0;
//  }
//  Game.game.rgame.tech_cost_double_year = 
//      secfile_lookup_int_default(&file, 1, "civstyle.tech_cost_double_year");
//
//  Game.game.rgame.tech_leakage =
//      secfile_lookup_int(&file, "civstyle.tech_leakage");
//  if (Game.game.rgame.tech_leakage < 0 || Game.game.rgame.tech_leakage > 3) {
//    util.freelog(Log.LOG_ERROR, "Bad value %i for tech_leakage. Using 0.",
//	    Game.game.rgame.tech_leakage);
//    Game.game.rgame.tech_leakage = 0;
//  }
//
//  if (Game.game.rgame.tech_cost_style == 0 && Game.game.rgame.tech_leakage != 0) {
//    util.freelog(Log.LOG_ERROR,
//	    "Only tech_leakage 0 supported with tech_cost_style 0.");
//    util.freelog(Log.LOG_ERROR, "Switching to tech_leakage 0.");
//    Game.game.rgame.tech_leakage = 0;
//  }
//    
//  /* City incite cost */
//  Game.game.incite_cost.improvement_factor = 
//    secfile_lookup_int_default(&file, 1, "incite_cost.improvement_factor");
//  Game.game.incite_cost.unit_factor = 
//    secfile_lookup_int_default(&file, 1, "incite_cost.unit_factor");
//  Game.game.incite_cost.total_factor = 
//    secfile_lookup_int_default(&file, 100, "incite_cost.total_factor");
//
//  /* Slow invasions */
//  Game.game.slow_invasions = 
//    secfile_lookup_bool_default(&file, GAME_DEFAULT_SLOW_INVASIONS,
//                                "global_unit_options.slow_invasions");
//  
//  /* Load global initial items. */
//  lookup_tech_list(&file, "options", "global_init_techs",
//		   Game.game.rgame.global_init_techs, filename);
//  lookup_building_list(&file, "options", "global_init_buildings",
//		       Game.game.rgame.global_init_buildings, filename);
//
//  /* Enable/Disable killstack */
//  Game.game.rgame.killstack = secfile_lookup_bool(&file, "combat_rules.killstack");
//	
//  section_file_check_unused(&file, filename);
//  section_file_free(&file);
//}
//
///**************************************************************************
//  Send the units ruleset information (all individual units) to the
//  specified connections.
//**************************************************************************/
//static void send_ruleset_units(Speclists<Connection> dest)
//{
//  struct packet_ruleset_unit packet;
//  int i;
//
//  unit_type_iterate(utype_id) {
//    unit_type u = get_unit_type(utype_id);
//
//    packet.id = u-Unittype_P.unit_types;
//    packet.name = u.name_orig;
//    packet.sound_move = u.sound_move;
//    packet.sound_move_alt = u.sound_move_alt;
//    packet.sound_fight = u.sound_fight;
//    packet.sound_fight_alt = u.sound_fight_alt;
//    packet.graphic_str = u.graphic_str;
//    packet.graphic_alt = u.graphic_alt;
//    packet.move_type = u.move_type;
//    packet.build_cost = u.build_cost;
//    packet.pop_cost = u.pop_cost;
//    packet.attack_strength = u.attack_strength;
//    packet.defense_strength = u.defense_strength;
//    packet.move_rate = u.move_rate;
//    packet.tech_requirement = u.tech_requirement;
//    packet.impr_requirement = u.impr_requirement;
//    packet.vision_range = u.vision_range;
//    packet.transport_capacity = u.transport_capacity;
//    packet.hp = u.hp;
//    packet.firepower = u.firepower;
//    packet.obsoleted_by = u.obsoleted_by;
//    packet.fuel = u.fuel;
//    packet.flags = u.flags;
//    packet.roles = u.roles;
//    packet.happy_cost = u.happy_cost;
//    packet.shield_cost = u.shield_cost;
//    packet.food_cost = u.food_cost;
//    packet.gold_cost = u.gold_cost;
//    packet.paratroopers_range = u.paratroopers_range;
//    packet.paratroopers_mr_req = u.paratroopers_mr_req;
//    packet.paratroopers_mr_sub = u.paratroopers_mr_sub;
//    packet.bombard_rate = u.bombard_rate;
//    for (i = 0; i < MAX_VET_LEVELS; i++) {
//      sz_strlcpy(packet.veteran_name[i], u.veteran[i].name);
//      packet.power_fact[i] = u.veteran[i].power_fact;
//      packet.move_bonus[i] = u.veteran[i].move_bonus;
//    }
//    if (u.helptext) {
//      packet.helptext = u.helptext;
//    } else {
//      packet.helptext[0] = '\0';
//    }
//
//    lsend_packet_ruleset_unit(dest, &packet);
//  } unit_type_iterate_end;
//}
//
///**************************************************************************
//  Send the techs ruleset information (all individual advances) to the
//  specified connections.
//**************************************************************************/
//static void send_ruleset_techs(Speclists<Connection> dest)
//{
//  struct packet_ruleset_tech packet;
//
//  tech_type_iterate(tech_id) {
//    advance a = &advances[tech_id];
//
//    packet.id = tech_id;
//    packet.name = a.name_orig;
//    packet.graphic_str = a.graphic_str;
//    packet.graphic_alt = a.graphic_alt;	  
//    packet.req[0] = a.req[0];
//    packet.req[1] = a.req[1];
//    packet.root_req = a.root_req;
//    packet.flags = a.flags;
//    packet.preset_cost = a.preset_cost;
//    packet.num_reqs = a.num_reqs;
//    if (a.helptext) {
//      packet.helptext = a.helptext;
//    } else {
//      packet.helptext[0] = '\0';
//    }
//
//    lsend_packet_ruleset_tech(dest, &packet);
//  } tech_type_iterate_end;
//}
//
///**************************************************************************
//  Send the buildings ruleset information (all individual improvements and
//  wonders) to the specified connections.
//**************************************************************************/
//static void send_ruleset_buildings(Speclists<Connection> dest)
//{
//  for (int i = 0; i < Game.game.num_impr_types; i++) {
//    impr_type b = &Improvement.improvement_types[i];
//    struct packet_ruleset_building packet;
//
//    packet.id = i;
//    packet.name = b.name_orig;
//    packet.graphic_str = b.graphic_str;
//    packet.graphic_alt = b.graphic_alt;
//    packet.tech_req = b.tech_req;
//    packet.bldg_req = b.bldg_req;
//    packet.equiv_range = b.equiv_range;
//    packet.obsolete_by = b.obsolete_by;
//    packet.replaced_by = b.replaced_by;
//    packet.is_wonder = b.is_wonder;
//    packet.build_cost = b.build_cost;
//    packet.upkeep = b.upkeep;
//    packet.sabotage = b.sabotage;
//    packet.soundtag = b.soundtag;
//    packet.soundtag_alt = b.soundtag_alt;
//
//    if (b.helptext) {
//      packet.helptext = b.helptext;
//    } else {
//      packet.helptext[0] = '\0';
//    }
//
//#define T(elem,count,last) \
//    for (packet.count = 0; b.elem[packet.count] != last; packet.count++) { \
//      packet.elem[packet.count] =  b.elem[packet.count]; \
//    }
//
//    T(terr_gate, terr_gate_count, Terrain_H.T_NONE);
//    T(spec_gate, spec_gate_count, S_NO_SPECIAL);
//    T(equiv_dupl, equiv_dupl_count, Improvement.B_LAST);
//    T(equiv_repl, equiv_repl_count, Improvement.B_LAST);
//#undef T
//
//    lsend_packet_ruleset_building(dest, &packet);
//  } ;
//}
//
///**************************************************************************
//  Send the terrain ruleset information (Map.terrain_control, and the individual
//  terrain types) to the specified connections.
//**************************************************************************/
//static void send_ruleset_terrain(Speclists<Connection> dest)
//{
//  struct packet_ruleset_terrain packet;
//
//  lsend_packet_ruleset_terrain_control(dest, &Map.terrain_control);
//
//  terrain_type_iterate(i) {
//    tile_type t = get_tile_type(i);
//
//      packet.id = i;
//
//      packet.terrain_name = t.terrain_name_orig;
//      packet.graphic_str = t.graphic_str;
//      packet.graphic_alt = t.graphic_alt;
//
//      packet.movement_cost = t.movement_cost;
//      packet.defense_bonus = t.defense_bonus;
//
//      packet.food = t.food;
//      packet.shield = t.shield;
//      packet.trade = t.trade;
//
//      packet.special_1_name = t.special_1_name_orig;
//      packet.food_special_1 = t.food_special_1;
//      packet.shield_special_1 = t.shield_special_1;
//      packet.trade_special_1 = t.trade_special_1;
//
//      packet.special_2_name = t.special_2_name_orig;
//      packet.food_special_2 = t.food_special_2;
//      packet.shield_special_2 = t.shield_special_2;
//      packet.trade_special_2 = t.trade_special_2;
//
//      packet.graphic_str_special_1 = String.format( t.special[0].graphic_str);
//      packet.graphic_alt_special_1 = String.format( t.special[0].graphic_alt);
//
//      packet.graphic_str_special_2 = String.format( t.special[1].graphic_str);
//      packet.graphic_alt_special_2 = String.format( t.special[1].graphic_alt);
//
//      packet.road_trade_incr = t.road_trade_incr;
//      packet.road_time = t.road_time;
//
//      packet.irrigation_result = t.irrigation_result;
//      packet.irrigation_food_incr = t.irrigation_food_incr;
//      packet.irrigation_time = t.irrigation_time;
//
//      packet.mining_result = t.mining_result;
//      packet.mining_shield_incr = t.mining_shield_incr;
//      packet.mining_time = t.mining_time;
//
//      packet.transform_result = t.transform_result;
//      packet.transform_time = t.transform_time;
//      packet.rail_time = t.rail_time;
//      packet.airbase_time = t.airbase_time;
//      packet.fortress_time = t.fortress_time;
//      packet.clean_pollution_time = t.clean_pollution_time;
//      packet.clean_fallout_time = t.clean_fallout_time;
//
//      packet.flags = t.flags;
//
//      if (t.helptext) {
//	packet.helptext = t.helptext;
//      } else {
//	packet.helptext[0] = '\0';
//      }
//      
//      lsend_packet_ruleset_terrain(dest, &packet);
//  } terrain_type_iterate_end;
//}
//
///**************************************************************************
//  Send the government ruleset information to the specified connections.
//  One packet per government type, and for each type one per ruler title.
//**************************************************************************/
//static void send_ruleset_governments(Speclists<Connection> dest)
//{
//  struct packet_ruleset_government gov;
//  struct packet_ruleset_government_ruler_title title;
//  ruler_title p_title;
//  int j;
//
//  government_iterate(g) {
//    /* send one packet_government */
//    gov.id                 = g.index;
//
//    gov.required_tech    = g.required_tech;
//    gov.max_rate         = g.max_rate;
//    gov.civil_war        = g.civil_war;
//    gov.martial_law_max  = g.martial_law_max;
//    gov.martial_law_per  = g.martial_law_per;
//    gov.empire_size_mod  = g.empire_size_mod;
//    gov.empire_size_inc  = g.empire_size_inc;
//    gov.rapture_size     = g.rapture_size;
//    
//    gov.unit_happy_cost_factor  = g.unit_happy_cost_factor;
//    gov.unit_shield_cost_factor = g.unit_shield_cost_factor;
//    gov.unit_food_cost_factor   = g.unit_food_cost_factor;
//    gov.unit_gold_cost_factor   = g.unit_gold_cost_factor;
//    
//    gov.free_happy  = g.free_happy;
//    gov.free_shield = g.free_shield;
//    gov.free_food   = g.free_food;
//    gov.free_gold   = g.free_gold;
//
//    gov.trade_before_penalty = g.trade_before_penalty;
//    gov.shields_before_penalty = g.shields_before_penalty;
//    gov.food_before_penalty = g.food_before_penalty;
//
//    gov.celeb_trade_before_penalty = g.celeb_trade_before_penalty;
//    gov.celeb_shields_before_penalty = g.celeb_shields_before_penalty;
//    gov.celeb_food_before_penalty = g.celeb_food_before_penalty;
//
//    gov.trade_bonus = g.trade_bonus;
//    gov.shield_bonus = g.shield_bonus;
//    gov.food_bonus = g.food_bonus;
//
//    gov.celeb_trade_bonus = g.celeb_trade_bonus;
//    gov.celeb_shield_bonus = g.celeb_shield_bonus;
//    gov.celeb_food_bonus = g.celeb_food_bonus;
//
//    gov.corruption_level = g.corruption_level;
//    gov.fixed_corruption_distance = g.fixed_corruption_distance;
//    gov.corruption_distance_factor = g.corruption_distance_factor;
//    gov.extra_corruption_distance = g.extra_corruption_distance;
//    gov.corruption_max_distance_cap = g.corruption_max_distance_cap;
//    
//    gov.waste_level = g.waste_level;
//    gov.fixed_waste_distance = g.fixed_waste_distance;
//    gov.waste_distance_factor = g.waste_distance_factor;
//    gov.extra_waste_distance = g.extra_waste_distance;
//    gov.waste_max_distance_cap = g.waste_max_distance_cap;
//        
//    gov.flags = g.flags;
//    gov.num_ruler_titles = g.num_ruler_titles;
//
//    gov.name = g.name_orig;
//    gov.graphic_str = g.graphic_str;
//    gov.graphic_alt = g.graphic_alt;
//    
//    if (g.helptext) {
//      gov.helptext = g.helptext;
//    } else {
//      gov.helptext[0] = '\0';
//    }
//      
//    lsend_packet_ruleset_government(dest, &gov);
//    
//    /* send one packet_government_ruler_title per ruler title */
//    for(j=0; j<g.num_ruler_titles; j++) {
//      p_title = &g.ruler_titles[j];
//
//      title.gov = g.index;
//      title.id = j;
//      title.nation = p_title.nation;
//      title.male_title = p_title.male_title;
//      title.female_title = p_title.female_title;
//    
//      lsend_packet_ruleset_government_ruler_title(dest, &title);
//    }
//  } government_iterate_end;
//}
//
///**************************************************************************
//  Send the nations ruleset information (info on each nation) to the
//  specified connections.
//**************************************************************************/
//static void send_ruleset_nations(Speclists<Connection> dest)
//{
//  struct packet_ruleset_nation packet;
//  nation_type n;
//  int i, k;
//
//  assert(sizeof(packet.init_techs) == sizeof(n.init_techs));
//  assert(ARRAY_SIZE(packet.init_techs) == ARRAY_SIZE(n.init_techs));
//
//  for( k=0; k<Game.game.nation_count; k++) {
//    n = Nation.get_nation_by_idx(k);
//    packet.id = k;
//    packet.name = n.name_orig;
//    packet.name_plural = n.name_plural_orig;
//    packet.graphic_str = n.flag_graphic_str;
//    packet.graphic_alt = n.flag_graphic_alt;
//    packet.leader_count = n.leader_count;
//    for(i=0; i < n.leader_count; i++) {
//      sz_strlcpy(packet.leader_name[i], n.leaders[i].name);
//      packet.leader_sex[i] = n.leaders[i].is_male;
//    }
//    packet.city_style = n.city_style;
//    memcpy(packet.init_techs, n.init_techs, sizeof(packet.init_techs));
//    packet.class = n.class;
//    packet.legend = n.legend;
//
//    lsend_packet_ruleset_nation(dest, &packet);
//  }
//}
//
///**************************************************************************
//  Send the city-style ruleset information (each style) to the specified
//  connections.
//**************************************************************************/
//static void send_ruleset_cities(Speclists<Connection> dest)
//{
//  struct packet_ruleset_city city_p;
//  int k;
//
//  for( k=0; k<Game.game.styles_count; k++) {
//    city_p.style_id = k;
//    city_p.techreq = city_styles[k].techreq;
//    city_p.replaced_by = city_styles[k].replaced_by;
//
//    city_p.name = String.format( city_styles[k].name_orig);
//    city_p.graphic = String.format( city_styles[k].graphic);
//    city_p.graphic_alt = String.format( city_styles[k].graphic_alt);
//    city_p.citizens_graphic = String.format( city_styles[k].citizens_graphic);
//    city_p.citizens_graphic_alt = String.format(
//			 city_styles[k].citizens_graphic_alt);
//
//    lsend_packet_ruleset_city(dest, &city_p);
//  }
//}
//
///**************************************************************************
//  Send information in packet_ruleset_game (miscellaneous rules) to the
//  specified connections.
//**************************************************************************/
//static void send_ruleset_game(Speclists<Connection> dest)
//{
//  int i;
//  struct packet_ruleset_game misc_p;
//
//  specialist_type_iterate(sp) {
//    sz_strlcpy(misc_p.specialist_name[sp], Game.game.rgame.specialists[sp].name);
//    misc_p.specialist_min_size[sp] = Game.game.rgame.specialists[sp].min_size;
//    misc_p.specialist_bonus[sp] = Game.game.rgame.specialists[sp].bonus;
//  } specialist_type_iterate_end;
//  misc_p.changable_tax = Game.game.rgame.changable_tax;
//  misc_p.forced_science = Game.game.rgame.forced_science;
//  misc_p.forced_luxury = Game.game.rgame.forced_luxury;
//  misc_p.forced_gold = Game.game.rgame.forced_gold;
//  misc_p.min_city_center_food = Game.game.rgame.min_city_center_food;
//  misc_p.min_city_center_shield = Game.game.rgame.min_city_center_shield;
//  misc_p.min_city_center_trade = Game.game.rgame.min_city_center_trade;
//  misc_p.min_dist_bw_cities = Game.game.rgame.min_dist_bw_cities;
//  misc_p.init_vis_radius_sq = Game.game.rgame.init_vis_radius_sq;
//  misc_p.hut_overflight = Game.game.rgame.hut_overflight;
//  misc_p.pillage_select = Game.game.rgame.pillage_select;
//  misc_p.nuke_contamination = Game.game.rgame.nuke_contamination;
//  for (i = 0; i < MAX_GRANARY_INIS; i++) {
//    misc_p.granary_food_ini[i] = Game.game.rgame.granary_food_ini[i];
//  }
//  misc_p.granary_num_inis = Game.game.rgame.granary_num_inis;
//  misc_p.granary_food_inc = Game.game.rgame.granary_food_inc;
//  misc_p.tech_cost_style = Game.game.rgame.tech_cost_style;
//  misc_p.tech_leakage = Game.game.rgame.tech_leakage;
//  misc_p.tech_cost_double_year = Game.game.rgame.tech_cost_double_year;
//
//  memcpy(misc_p.trireme_loss_chance, Game.game.trireme_loss_chance, 
//         sizeof(Game.game.trireme_loss_chance));
//  memcpy(misc_p.work_veteran_chance, Game.game.work_veteran_chance, 
//         sizeof(Game.game.work_veteran_chance));
//  memcpy(misc_p.veteran_chance, Game.game.veteran_chance, 
//         sizeof(Game.game.veteran_chance));
//    
//  assert(sizeof(misc_p.global_init_techs) ==
//	 sizeof(Game.game.rgame.global_init_techs));
//  assert(ARRAY_SIZE(misc_p.global_init_techs) ==
//	 ARRAY_SIZE(Game.game.rgame.global_init_techs));
//  memcpy(misc_p.global_init_techs, Game.game.rgame.global_init_techs,
//	 sizeof(misc_p.global_init_techs));
//
//  misc_p.killstack = Game.game.rgame.killstack;
//  lsend_packet_ruleset_game(dest, &misc_p);
//}
//
///**************************************************************************
//...  
//**************************************************************************/
//void load_rulesets()
//{
//  struct section_file techfile, unitfile, buildfile, govfile, terrfile;
//  struct section_file cityfile, nationfile;
//
//  util.freelog(Log.LOG_NORMAL, "Loading rulesets");
//
//  openload_ruleset_file(&techfile, "techs");
//  load_tech_names(&techfile);
//
//  openload_ruleset_file(&buildfile, "buildings");
//  load_building_names(&buildfile);
//
//  openload_ruleset_file(&govfile, "governments");
//  load_government_names(&govfile);
//
//  openload_ruleset_file(&unitfile, "units");
//  load_Unittype_P.unit_names(&unitfile);
//
//  openload_ruleset_file(&terrfile, "terrain");
//  load_terrain_names(&terrfile);
//
//  openload_ruleset_file(&cityfile, "cities");
//  load_citystyle_names(&cityfile);
//
//  openload_ruleset_file(&nationfile, "nations");
//  load_nation_names(&nationfile);
//
//  load_ruleset_techs(&techfile);
//  load_ruleset_cities(&cityfile);
//  load_ruleset_governments(&govfile);
//  load_ruleset_units(&unitfile);
//  load_ruleset_terrain(&terrfile);    /* terrain must precede nations */
//  load_ruleset_buildings(&buildfile);
//  load_ruleset_nations(&nationfile);
//  load_ruleset_game();
//  translate_data_names();
//}
//
///**************************************************************************
//  Send all ruleset information to the specified connections.
//**************************************************************************/
//void send_rulesets(Speclists<Connection> dest)
//{
//  Connection.conn_list_do_buffer(dest);
//  lsend_packet_freeze_hint(dest);
//
//  send_ruleset_control(dest);
//  send_ruleset_game(dest);
//  send_ruleset_techs(dest);
//  send_ruleset_governments(dest);
//  send_ruleset_units(dest);
//  send_ruleset_terrain(dest);
//  send_ruleset_buildings(dest);
//  send_ruleset_nations(dest);
//  send_ruleset_cities(dest);
//  send_ruleset_cache(dest);
//
//  lsend_packet_thaw_hint(dest);
//  Connection.conn_list_do_unbuffer(dest);
//}
}