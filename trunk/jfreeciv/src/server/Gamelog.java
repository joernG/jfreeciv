package server;


public class Gamelog{
//#include "fcintl.h"
//#include "government.h"
//#include "log.h"
//#include "Map.map.h"
//#include "mem.h"
//#include "score.h"
//#include "srv_main.h"
//#include "support.h"
//
//#include "Gamelog.gamelog.h"
//#include "stdinhand.h"
//
//int gamelog_level;		/* also accessed from stdinhand.c */
//static char *gamelog_filename;
//
///* Stuff for gamelog_status() */
//struct player_score_entry {
//  int idx;
//  int value;
//};
//
//static void gamelog_status(char *buffer, int len);
//
///* must match the enum in Gamelog.gamelog.h */
//final String treaty_clause_strings[] = {
//  "Embassy",
//  "Tech",
//  "Gold",
//  "Map",
//  "Seamap",
//  "City",
//  "Ceasefire",
//  "Peace",
//  "Alliance",
//  "Team",
//  "Shared Vision"
//};
//
///* must match the enum in Gamelog.gamelog.h */
//final String endgame_strings[] = {
//  "None",
//  "Draw",
//  "Lone Win",
//  "Team Win",
//  "Allied Win"
//};
//
///**************************************************************************
//  Filename can be null, which means no logging.
//**************************************************************************/
//void gamelog_init(char *filename)
//{
//  gamelog_level = GAMELOG_FULL;
//  if (filename && filename.length() > 0) {
//    gamelog_filename = filename;
//  } else {
//    gamelog_filename = null;
//  }
//}
//
///**************************************************************************
//  ...
//**************************************************************************/
//void gamelog_set_level(int level)
//{
//  gamelog_level = level;
//}
//
///**************************************************************************
// Place the toplevel xml tags for each line. Also add the year and turn 
// attributes to the tag. "element" is the name of the tag.
//**************************************************************************/
//static void gamelog_put_prefix(char *buf, int len, final String element)
//{
//  char buf2[5000];
//
//  buf2 = util.my_snprintf( "<%s y=\"%d\" t=\"%d\">%s</%s>", element,
//              Game.game.year, Game.game.turn, buf, element);
//  
//  mystrlcpy(buf, buf2, len);
//}

	/**************************************************************************
 Does the heavy lifing. Note that this function takes variable arguments.
 This means that you'd better know what you're doing when altering a Gamelog.gamelog
 call.

 Following are the parameters to be passed given "level".
 See the case statement for more information on what the parameters are for:

  GAMELOG_BEGIN
    none.
  GAMELOG_END
    none.
  EGamelog.GAMELOG_JUDGE
    int
  GAMELOG_MAP
    none
  EGamelog.GAMELOG_PLAYER
    player 
  GAMELOG_TEAM
    team 
  GAMELOG_WONDER
    city 
  GAMELOG_FOUNDCITY
    city 
  EGamelog.GAMELOG_LOSECITY
    player 
    player 
    city 
    char *
  GAMELOG_DISBANDCITY
    city 
  GAMELOG_TECH
    player 
    player  (can be null)
    int
    char * (only present if second player is not null)
  GAMELOG_EMBASSY
    player 
    city 
  GAMELOG_GOVERNMENT
    player 
  GAMELOG_REVOLT
    player 
  GAMELOG_GENO
    player 
  GAMELOG_TREATY
    int
    player 
    player 
    city    (can be null, present only if int is GL_CITY)
  GAMELOG_DIPLSTATE
    player 
    player 
    int
  GAMELOG_STATUS
    none
  GAMELOG_FULL
    do not call this.
  GAMELOG_INFO
    player 
  GAMELOG_UNITLOSS
    unit 
    player  (can be null)
    char * (only present if player is null)
  GAMELOG_UNITGAMELOSS
    unit 
  GAMELOG_BUILD
    city 
  GAMELOG_RATECHANGE
    player 
  GAMELOG_EVERYTHING
     do not call this
  GAMELog.LOG_DEBUG
     do not call this

	 **************************************************************************/
	public static void gamelog(server.gamelog.EGamelog level, Object ...args)
	{
//  va_list args;
//  char buf[4096] = "", msg[512] = "";
//  char *word = null;
//  player pplayer = null, *pplayer2 = null;
//  city pcity = null;
//  unit punit = null;
//  team pteam = null;
//  int num;
//  FILE *fs;
//
//  if (!gamelog_filename) {
//    return;
//  }
//  if (level > gamelog_level) {
//    return;
//  }
//
//  fs = fopen(gamelog_filename, "a");
//  if (!fs) {
//    util.freelog(LOG_FATAL, ("Couldn't open gamelogfile \"%s\" for appending."), 
//	    gamelog_filename);
//    exit(EXIT_FAILURE);
//  }
//
//  va_start(args, level);
//
//  switch (level) {
//  case GAMELOG_GOVERNMENT:
//    pplayer = va_arg(args, player );
//
//    buf = util.my_snprintf(
//                "<n>%d</n><name>%s</name><m>%s form a %s</m>",
//                pplayer.player_no, get_government_name(pplayer.government),
//                Nation.get_nation_name_plural(pplayer.nation),
//                get_government_name(pplayer.government));
//    gamelog_put_prefix(buf, sizeof(buf), "gov");
//    break;
//  case GAMELOG_REVOLT:
//    pplayer = va_arg(args, player );
//
//    buf = util.my_snprintf( "<n>%d</n><m>%s</m>",
//                pplayer.player_no, Nation.get_nation_name_plural(pplayer.nation));
//    gamelog_put_prefix(buf, sizeof(buf), "rev");
//    break;
//  case GAMELOG_FOUNDCITY:
//    pcity = va_arg(args, city );
//
//    buf = util.my_snprintf( "<n>%d</n><name>%s</name>" +
//                "<x>%d</x><y>%d</y><m>%s (%d,%d) founded by the %s</m>",
//                City.city_owner(pcity).player_no,
//                pcity.name, pcity.tile.x, pcity.tile.y,
//                pcity.name, pcity.tile.x, pcity.tile.y,
//                Nation.get_nation_name_plural(City.city_owner(pcity).nation));
//    gamelog_put_prefix(buf, sizeof(buf), "cityf");
//    break;
//  case EGamelog.GAMELOG_LOSECITY:
//    pplayer = va_arg(args, player );
//    pplayer2 = va_arg(args, player );
//    pcity = va_arg(args, city );
//    word = va_arg(args, char *);
//
//    buf = util.my_snprintf( "<n1>%d</n1><n2>%d</n2>" +
//                "<name>%s</name><x>%d</x><y>%d</y>" +
//                "<m>%s (%s) (%d,%d) %s by %s</m>", 
//                pplayer.player_no, pplayer2.player_no,
//                pcity.name, pcity.tile.x, pcity.tile.y,
//                pcity.name, Nation.get_nation_name_plural(pplayer.nation),
//                pcity.tile.x, pcity.tile.y, word,
//                Nation.get_nation_name_plural(pplayer2.nation));
//    gamelog_put_prefix(buf, sizeof(buf), "cityl");
//    break;
//  case GAMELOG_DISBANDCITY:
//    pcity = va_arg(args, city );
//
//    buf = util.my_snprintf( "<n>%d</n><name>%s</name>" +
//                "<x>%d</x><y>%d</y><m>%s (%d, %d) disbanded by the %s</m>",
//                City.city_owner(pcity).player_no,
//                pcity.name, pcity.tile.x, pcity.tile.y,
//                pcity.name, pcity.tile.x, pcity.tile.y,
//                Nation.get_nation_name_plural(City.city_owner(pcity).nation));
//    gamelog_put_prefix(buf, sizeof(buf), "cityd");
//    break;
//  case GAMELOG_TREATY:
//    num = va_arg(args, int);
//    pplayer = va_arg(args, player );
//    pplayer2 = va_arg(args, player );
//
//    switch(num) {
//    case GL_EMBASSY:
//      msg = String.format( "<m>Treaty - Embassy between the %s and the %s</m>");
//      break;
//    case GL_TECH:
//      msg = String.format( "<m>Treaty - Tech given to the %s from the %s</m>");
//      break;
//    case GL_GOLD:
//      msg = String.format( "<m>Treaty - Gold given to the %s from the %s</m>");
//      break;
//    case GL_MAP:
//      msg = String.format( "<m>Treaty - Map given to the %s from the %s</m>");
//      break;
//    case GL_SEAMAP:
//      msg = String.format( "<m>Treaty - Seamap given to the %s from the %s</m>");
//      break;
//    case GL_CITY:
//      pcity = va_arg(args, city );
//      msg = String.format(
//                 "<m>Treaty - City (%s) given to the %s from the %s</m>");
//      break;
//    case GL_CEASEFIRE:
//      msg = String.format( "<m>Treaty - Ceasefire between the %s and the %s</m>");
//      break;
//    case GL_PEACE:
//      msg = String.format( "<m>Treaty - Peace between the %s and the %s</m>");
//      break;
//    case GL_ALLIANCE:
//      msg = String.format( "<m>Treaty - Alliance between the %s and the %s</m>");
//      break;
//    case GL_TEAM:
//      msg = String.format( "<m>Treaty - Team between the %s and the %s</m>");
//      break;
//    case GL_VISION:
//      msg = String.format(
//                 "<m>Treaty - Shared Vision between the %s and the %s</m>");
//      break;
//    default:
//      break;
//    }
//
//    if (pcity) {
//      buf = util.my_snprintf(
//                  "<n1>%d</n1><n2>%d</n2><city>%s</city><type>%s</type>",
//                  pplayer.player_no, pplayer2.player_no, pcity.name,
//                  treaty_clause_strings[num]);
//      cat_snprintf(buf, sizeof(buf), msg, 
//                  pcity.name, Nation.get_nation_name_plural(pplayer2.nation),
//                  Nation.get_nation_name_plural(pplayer.nation));
//    } else {
//      buf = util.my_snprintf(
//                  "<n1>%d</n1><n2>%d</n2><type>%s</type>",
//                  pplayer.player_no, pplayer2.player_no, 
//                  treaty_clause_strings[num]);
//      cat_snprintf(buf, sizeof(buf), msg,
//                  Nation.get_nation_name_plural(pplayer.nation),
//                  Nation.get_nation_name_plural(pplayer2.nation));
//    }
//    gamelog_put_prefix(buf, sizeof(buf), "treaty");
//    break;
//  case GAMELOG_DIPLSTATE:
//    pplayer = va_arg(args, player );
//    pplayer2 = va_arg(args, player );
//    num = va_arg(args, int);
//
//    buf = util.my_snprintf(
//                "<n1>%d</n1><n2>%d</n2><type>%s</type>",
//                pplayer.player_no, pplayer2.player_no,  diplstate_text(num));
//    cat_snprintf(buf, sizeof(buf),
//                "<m>The diplomatic state between the %s and the %s is %s</m>",
//                Nation.get_nation_name_plural(pplayer.nation),
//                Nation.get_nation_name_plural(pplayer2.nation), diplstate_text(num));
//    gamelog_put_prefix(buf, sizeof(buf), "dipl");
//    break;
//  case GAMELOG_TECH:
//    pplayer = va_arg(args, player );
//    pplayer2 = va_arg(args, player );
//    num = va_arg(args, int);
//
//    if (pplayer2) {
//      word = va_arg(args, char *);
//
//      buf = util.my_snprintf(
//                  "<n1>%d</n1><n2>%d</n2><name>%s</name>" +
//                  "<m>%s %s %s from the %s</m>",
//                  pplayer.player_no, pplayer2.player_no,
//                  get_tech_name(pplayer, (Tech_Type_id) num),
//                  Nation.get_nation_name_plural(pplayer.nation), word,
//                  get_tech_name(pplayer, (Tech_Type_id) num),
//                  Nation.get_nation_name_plural(pplayer2.nation));
//    } else {
//      buf = util.my_snprintf(
//                  "<n1>%d</n1><name>%s</name>" +
//                  "<m>%s discover %s</m>",
//                  pplayer.player_no,
//                  get_tech_name(pplayer, (Tech_Type_id) num),
//                  Nation.get_nation_name_plural(pplayer.nation),
//                  get_tech_name(pplayer, (Tech_Type_id) num));
//    }
//    gamelog_put_prefix(buf, sizeof(buf), "tech");
//    break;
//  case GAMELOG_UNITLOSS:
//    punit = va_arg(args, unit );
//    pplayer = va_arg(args, player );
//
//    if (pplayer) {
//      buf = util.my_snprintf(
//                  "<n1>%d</n1><n2>%d</n2><name>%s</name>" +
//                  "<m>%s lose %s to the %s</m>",
//                  punit.unit_owner().player_no, pplayer.player_no,
//                  Unittype_P.unit_name(punit.type),
//                  Nation.get_nation_name_plural(punit.unit_owner().nation),
//                  Unittype_P.unit_name(punit.type),
//                  Nation.get_nation_name_plural(pplayer.nation));
//    } else {
//      word = va_arg(args, char *);
//      buf = util.my_snprintf(
//                  "<n1>%d</n1><name>%s</name><m>%s lose %s (%s)</m>",
//                  punit.unit_owner().player_no, Unittype_P.unit_name(punit.type),
//                  Nation.get_nation_name_plural(punit.unit_owner().nation),
//                  Unittype_P.unit_name(punit.type), word);
//    }
//    gamelog_put_prefix(buf, sizeof(buf), "unitl");
//    break;
//  case GAMELOG_UNITGAMELOSS:
//    punit = va_arg(args, unit );
//
//    buf = util.my_snprintf(
//                "<n>%d</n><name>%s</name>" +
//                "<m>%s lost a Game.game loss unit and util.died</m>",
//                punit.unit_owner().player_no, Unittype_P.unit_name(punit.type),
//                Nation.get_nation_name_plural(punit.unit_owner().nation));
//    gamelog_put_prefix(buf, sizeof(buf), "gamel");
//    break;
//  case GAMELOG_EMBASSY:
//    pplayer = va_arg(args, player );
//    pcity = va_arg(args, city );
//
//    buf = util.my_snprintf(
//                "<n1>%d</n1><n2>%d</n2><name>%s</name><x>%d</x><y>%d</y>" +
//                "<m>%s establish an embassy in %s (%s) (%d,%d)</m>",
//                pplayer.player_no, City.city_owner(pcity).player_no,
//                pcity.name, pcity.tile.x, pcity.tile.y,
//                Nation.get_nation_name_plural(pplayer.nation),
//                pcity.name,
//                Nation.get_nation_name_plural(City.city_owner(pcity).nation),
//                pcity.tile.x, pcity.tile.y);
//    gamelog_put_prefix(buf, sizeof(buf), "embassy");
//    break;
//  case GAMELOG_BUILD:
//  case GAMELOG_WONDER:
//    pcity = va_arg(args, city );
//
//    buf = util.my_snprintf(
//                "<n>%d</n><city>%s</city><u>%d</u>" +
//                "<w>%d</w><name>%s</name><m>%s build %s in %s</m>",
//                City.city_owner(pcity).player_no, pcity.name,
//                pcity.is_building_unit ? 1 : 0,
//                (!pcity.is_building_unit 
//                 && Improvement.is_wonder(pcity.currently_building)) ? 1 : 0,
//                pcity.is_building_unit ? 
//                  Unittype_P.unit_types[pcity.currently_building].name :
//                  City.get_impr_name_ex(pcity, pcity.currently_building),
//                Nation.get_nation_name_plural(City.city_owner(pcity).nation),
//                pcity.is_building_unit ? 
//                  Unittype_P.unit_types[pcity.currently_building].name :
//                  City.get_impr_name_ex(pcity, pcity.currently_building),
//                pcity.name);
//    gamelog_put_prefix(buf, sizeof(buf), "build");
//    break;
//  case GAMELOG_GENO:
//    pplayer = va_arg(args, player );
//
//    buf = util.my_snprintf(
//                "<n>%d</n><b>%d</b><m>%s civilization destroyed</m>",
//                pplayer.player_no, is_barbarian(pplayer) ? 1 : 0,
//                is_barbarian(pplayer) ? get_nation_name(pplayer.nation)
//                                      : pplayer.name);
//    gamelog_put_prefix(buf, sizeof(buf), "geno");
//    break;
//  case GAMELOG_RATECHANGE:
//    pplayer = va_arg(args, player );
//
//    buf = util.my_snprintf( 
//                "<n>%d</n><tax>%d</tax><lux>%d</lux><sci>%d</sci>",
//                pplayer.player_no, pplayer.economic.tax, 
//                pplayer.economic.luxury, pplayer.economic.science);
//    gamelog_put_prefix(buf, sizeof(buf), "rates");
//    break;
//  case GAMELOG_INFO:
//    pplayer = va_arg(args, player );
//    {
//      int food = 0, shields = 0, trade = 0, settlers = 0;
//
//      for (unit punit : pplayer.units.data) {
//        if (unit_flag(punit, Eunit_flag_id.F_CITIES)) {
//          settlers++;
//        }
//      } }
//      for (city pcity : pplayer.cities.data) {
//        shields += pcity.shield_prod;
//        food += pcity.food_prod;
//        trade += pcity.trade_prod;
//      } }
//
//      buf = util.my_snprintf( "<n>%d</n><cities>%d</cities>" +
//                  "<pop>%d</pop><food>%d</food><prod>%d</prod>" +
//                  "<trade>%d</trade><settlers>%d</settlers><units>%d</units>",
//                  pplayer.player_no, pplayer.cities.foo_list_size(),
//                  total_player_citizens(pplayer), food, shields, trade, 
//                  settlers, pplayer.units.foo_list_size());
//    }
//    gamelog_put_prefix(buf, sizeof(buf), "info");
//    break;
//  case EGamelog.GAMELOG_PLAYER:
//    pplayer = va_arg(args, player );
//
//    buf = util.my_snprintf( "<n>%d</n><u>%s</u><c>%d</c>" +
//                "<ai>%s</ai><nat>%s</nat><l>%s</l>",
//                pplayer.player_no, pplayer.username,
//                pplayer.is_connected ? 1 : 0,
//                pplayer.ai.control ? 
//                  name_of_skill_level(pplayer.ai.skill_level) : "",
//                Nation.get_nation_name_plural(pplayer.nation), pplayer.name);
//    gamelog_put_prefix(buf, sizeof(buf), "player");
//    break;
//  case GAMELOG_TEAM:
//    pteam = va_arg(args, team );
//
//    buf = util.my_snprintf( "<id>%d</id><name>%s</name>",
//                                  pteam.id, pteam.name);
//    for(player aplayer: Game.game.players){
//      if (aplayer.team == pteam.id) {
//        cat_snprintf(buf, sizeof(buf), "<n>%d</n>", aplayer.player_no);
//      }
//    }
//    gamelog_put_prefix(buf, sizeof(buf), "team");
//    break;
//  case GAMELOG_BEGIN:
//    buf = util.my_snprintf(
//                "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
//                "<Gamelog.gamelog version=\"2.0\">");
//    break; 
//  case GAMELOG_END:
//    buf = util.my_snprintf( "</Gamelog.gamelog>");
//    break;
//  case EGamelog.GAMELOG_JUDGE:
//    num = va_arg(args, int);
//
//    switch(num) {
//    case GL_NONE:
//    case GL_DRAW:
//      buf = util.my_snprintf( "<type>%s</type>", endgame_strings[num]);
//      msg[0] = '\0';
//      break;
//    case EEndGameState.GL_LONEWIN:
//      pplayer = va_arg(args, player );
//
//      buf = util.my_snprintf( "<type>%s</type><n>%d</n>",
//                  endgame_strings[num], pplayer.player_no);
//      msg = util.my_snprintf(
//                  (pplayer.spaceship.state == spaceship_state.SSHIP_ARRIVED)
//                  ? "The %s spaceship has arrived at Alpha Centauri."
//                  : "Game ended in victory for the %s",
//                  Nation.get_nation_name_plural(pplayer.nation));
//      break;
//    case GL_ALLIEDWIN:
//      buf = util.my_snprintf( "<type>%s</type>", endgame_strings[num]);
//      for(player aplayer: Game.game.players){
//        if (aplayer.is_alive) {
//          cat_snprintf(buf, sizeof(buf), "<n>%d</n>", aplayer.player_no);
//        }
//      }
//      msg = util.my_snprintf( "Game ended in allied victory");
//      break;
//    case GL_TEAMWIN:
//      pteam = va_arg(args, team );
//      buf = util.my_snprintf( "<type>%s</type>", endgame_strings[num]);
//      for(player aplayer: Game.game.players){
//        if (aplayer.team == pteam.id) {
//          cat_snprintf(buf, sizeof(buf), "<n>%d</n>", aplayer.player_no);
//        }
//      }
//      msg = util.my_snprintf( "Team victory to %s", pteam.name);
//      break;
//    default:
//      break;
//    }
//    cat_snprintf(buf, sizeof(buf), "<m>%s</m>", msg);
//    gamelog_put_prefix(buf, sizeof(buf), "judge");
//    break;
//  case GAMELOG_MAP:
//    /* this is big, so it's special */
//    {
//      int nat_x, nat_y, i = 0;
//      char *mapline = fc_malloc(((Map.map.xsize + 1) * Map.map.ysize) + 1);
//
//      for (nat_y = 0; nat_y < Map.map.ysize; nat_y++) {
//        for (nat_x = 0; nat_x < Map.map.xsize; nat_x++) {
//          tile ptile = native_pos_to_tile(nat_x, nat_y);
//
//          mapline[i++] = Terrain_H.is_ocean(ptile.terrain) ? ' ' : '.';
//        }
//        mapline[i++] = '\n';
//      }
//      mapline[i] = '\0';
//
//      va_end(args);
//
//      fprintf(fs, "<map>\n%s</map>\n", mapline);
//      fflush(fs);
//      fclose(fs);
//      free(mapline);
//      return;
//    }
//    break;
//  case GAMELOG_STATUS:
//    gamelog_status(buf, sizeof(buf));
//    gamelog_put_prefix(buf, sizeof(buf), "status");
//    break;
//  default:
//    assert(0!=1);
//    break;
//  }
//
//  va_end(args);
//
//  fprintf(fs, "%s\n", buf);
//  fflush(fs);
//  fclose(fs);
}

///**************************************************************************
//  ...
//**************************************************************************/
//static int secompare1(final void *a, final void *b)
//{
//  return (((final player_score_entry )b).value -
//	  ((final player_score_entry )a).value);
//}
//
///**************************************************************************
//  Every time we save the Game.game, we also output to the Gamelog.gamelog the score
//  and status info.
//**************************************************************************/
//static void gamelog_status(char *buffer, int len) {
//
//  int i, count = 0, highest = -1;
//  player highest_plr = null;
//  struct player_score_entry size[Game.game.nplayers], rank[Game.game.nplayers];
//
//  for(player pplayer: Game.game.players){
//    if (!is_barbarian(pplayer)) {
//      rank[count].value = get_civ_score(pplayer);
//      rank[count].idx = pplayer.player_no;
//      size[count].value = total_player_citizens(pplayer);
//      size[count].idx = pplayer.player_no;
//      if (rank[count].value > highest) {
//        highest = rank[count].value;
//        highest_plr = pplayer;
//      }
//      count++;
//    }
//  }
//
//  /* Draws and team victories */
//  count = 0;
//  for(player pplayer: Game.game.players){
//    if (!is_barbarian(pplayer)) {
//      if ((BV_ISSET_ANY(Srv_main.srvarg.draw)
//           && BV_ISSET(Srv_main.srvarg.draw, pplayer.player_no))
//          || players_on_same_team(pplayer, highest_plr)) {
//        /* We win a shared victory, so equal the score. */
//        rank[count].value = highest;
//      }
//      count++;
//    }
//  }
//
//  buffer[0] = '\0';
//  qsort(rank, count, sizeof(struct player_score_entry), secompare1);
//
//  for (i = 0; i < count; i++) {
//    cat_snprintf(buffer, len, "<plr><no>%d</no><r>%d</r><s>%d</s></plr>",
//		 Game.game.players[rank[i].idx].player_no,
//                 rank[i].value, size[rank[i].idx].value);
//  }
//}
}