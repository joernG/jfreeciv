package server;
import static server.Meta.DEFAULT_META_SERVER_ADDR;
import static server.Meta.DEFAULT_META_SERVER_NO_SEND;
import server.srv_main.server_arguments;
import utility.Fciconv;
import utility.Log;
import utility.Shared;

import common.Nation;
import common.game.server_states;

public class Srv_main{
	public static final int DEFAULT_SOCK_PORT = 5555;

	/*
	 * this is used in strange places, and is 'extern'd where needed (hence, it
	 * is not 'extern'd in srv_main.h)
	 */
	boolean is_server = true;

	/* command-line arguments to server */
	public static server_arguments srvarg;

	/* server state information */
server_states server_state = server_states.PRE_GAME_STATE;
boolean nocity_send = false;

/* this global is checked deep down the netcode. 
   packets handling functions can set it to none-zero, to
   force end-of-tick asap
*/
boolean force_end_of_sniff;

/* List of which nations are available. */
boolean nations_available;

/* this counter creates all the id numbers used */
/* use get_next_id_number()                     */
static short global_id_counter=100;
//static unsigned char used_ids[8192]={0};

/* server initialized flag */
boolean has_been_srv_init = false;

/**************************************************************************
  Initialize the game seed.  This may safely be called multiple times.
**************************************************************************/
void init_game_seed()
{
//  if (game.seed == 0) {
//    /* We strip the high bit for now because neither game file nor
//       server options can handle unsigned ints yet. - Cedric */
//    game.seed = time(null) & (MAX_UINT32 >> 1);
//  }
// 
//  if (!myrand_is_init()) {
//    mysrand(game.seed);
//  }
}

/**************************************************************************
...
**************************************************************************/
public void srv_init()
{
	/* NLS init */
	Shared.init_nls();

	/* init server arguments... */

	srvarg.metaserver_no_send = DEFAULT_META_SERVER_NO_SEND;
	srvarg.metaserver_addr = DEFAULT_META_SERVER_ADDR;

	srvarg.bind_addr = null;
	srvarg.port = DEFAULT_SOCK_PORT;

	srvarg.loglevel = Log.LOG_NORMAL;

	srvarg.log_filename = null;
	srvarg.gamelog_filename = null;
	srvarg.load_filename = "";
	srvarg.script_filename = null;
	srvarg.saves_pathname = "";

	srvarg.quitidle = 0;
//	BV_CLR_ALL(srvarg.draw); //TODO

	srvarg.auth_enabled = false;
	srvarg.auth_allow_guests = false;
	srvarg.auth_allow_newusers = false;

	/* initialize teams */
	Nation.team_init();

	srvarg.save_ppm = false;

	/* mark as initialized */
	has_been_srv_init = true;

	/* init character encodings. */
	Fciconv.init_character_encodings(Fciconv.FC_DEFAULT_DATA_ENCODING, false);

	/* done */
	return;
}

///**************************************************************************
//  Returns true if any one game end condition is fulfilled, false otherwise
//**************************************************************************/
//static boolean is_game_over()
//{
//  int barbs = 0, alive = 0, observers = 0;
//  boolean all_allied;
//  player victor = null;
//
//  /* quit if we are past the year limit */
//  if (game.year > game.end_year) {
//    notify_conn_ex(&game.est_connections, null, E_GAME_END, 
//		   "Game ended in a draw as end year exceeded");
//    gamelog(GAMELOG_JUDGE, GL_DRAW, 
//            "Game ended in a draw as end year exceeded");
//    return true;
//  }
//
//  /* count barbarians and observers */
//  for(player pplayer: game.players){
//    if (is_barbarian(pplayer)) {
//      barbs++;
//    }
//    if (pplayer.is_observer) {
//      observers++;
//    }
//  }
//
//  /* the game does not quit if we are playing solo */
//  if (game.nplayers == (observers + barbs + 1)) {
//    return false;
//  } 
//
//  /* count the living */
//  for(player pplayer: game.players){
//    if (pplayer.is_alive && !is_barbarian(pplayer)) {
//      alive++;
//      victor = pplayer;
//    }
//  }
//
//  /* quit if we have team victory */
//  team_iterate(pteam) {
//    if (team_count_members_alive(pteam.id) == alive) {
//      notify_conn_ex(&game.est_connections, null, E_GAME_END,
//		     "Team victory to %s", pteam.name);
//      gamelog(GAMELOG_JUDGE, GL_TEAMWIN, pteam);
//      return true;
//    }
//  } team_iterate_end;
//
//  /* quit if only one player is left alive */
//  if (alive == 1) {
//    notify_conn_ex(&game.est_connections, null, E_GAME_END,
//		   "Game ended in victory for %s", victor.name);
//    gamelog(GAMELOG_JUDGE, GL_LONEWIN, victor);
//    return true;
//  } else if (alive == 0) {
//    notify_conn_ex(&game.est_connections, null, E_GAME_END, 
//		   "Game ended in a draw");
//    gamelog(GAMELOG_JUDGE, GL_DRAW);
//    return true;
//  }
//
//  /* quit if all remaining players are allied to each other */
//  all_allied = true;
//  for(player pplayer: game.players){
//    if (!pplayer.is_alive) {
//      continue;
//    }
//    for(player aplayer: game.players){
//      if (!pplayers_allied(pplayer, aplayer) && aplayer.is_alive) {
//        all_allied = false;
//        break;
//      }
//    }
//    if (!all_allied) {
//      break;
//    }
//  }
//  if (all_allied) {
//    notify_conn_ex(&game.est_connections, null, E_GAME_END, 
//		   "Game ended in allied victory");
//    gamelog(GAMELOG_JUDGE, GL_ALLIEDWIN);
//    return true;
//  }
//
//  return false;
//}
//
///**************************************************************************
//  Send all information for when game starts or client reconnects.
//  Ruleset information should have been sent before this.
//**************************************************************************/
//void send_all_info(Speclists<Connection> dest)
//{
//  conn_list_iterate(*dest, pconn) {
//      send_attribute_block(pconn.player,pconn);
//  }
//  }
//
//  send_game_info(dest);
//  send_map_info(dest);
//  send_player_info_c(null, dest);
//  send_conn_info(&game.est_connections, dest);
//  send_spaceship_info(null, dest);
//  send_all_known_tiles(dest);
//  send_all_known_cities(dest);
//  send_all_known_units(dest);
//  send_player_turn_notifications(dest);
//}
//
///**************************************************************************
//  Give map information to players with EFT_REVEAL_CITIES or
//  EFT_REVEAL_MAP effects (traditionally from the Apollo Program).
//**************************************************************************/
//static void do_reveal_effects()
//{
//  for(player pplayer: game.players){
//    if (get_player_bonus(pplayer, EFT_REVEAL_CITIES) > 0) {
//      for(player other_player: game.players){
//	for (city pcity : other_player.cities.data) {
//	  show_area(pplayer, pcity.tile, 0);
//	} }
//      }
//    }
//    if (get_player_bonus(pplayer, EFT_REVEAL_MAP) > 0) {
//      /* map_know_all will mark all unknown tiles as known and send
//       * tile, unit, and city updates as necessary.  No other actions are
//       * needed. */
//      map_know_all(pplayer);
//    }
//  }
//}
//
///**************************************************************************
//  Give contact to players with the EFT_HAVE_EMBASSIES effect (traditionally
//  from Marco Polo's Embassy).
//**************************************************************************/
//static void do_have_embassies_effect()
//{
//  for(player pplayer: game.players){
//    if (get_player_bonus(pplayer, EFT_HAVE_EMBASSIES) > 0) {
//      for(player pother: game.players){
//	/* Note this gives pplayer contact with pother, but doesn't give
//	 * pother contact with pplayer.  This may cause problems in other
//	 * parts of the code if we're not careful. */
//	make_contact(pplayer, pother, null);
//      }
//    }
//  }
//}
//
///**************************************************************************
//...
//**************************************************************************/
//static void update_environmental_upset(enum tile_special_type cause,
//				       int *current, int *accum, int *level,
//				       void (*upset_action_fn)(int))
//{
//  int count;
//
//  count = 0;
//  whole_map_iterate(ptile) {
//    if (map_has_special(ptile, cause)) {
//      count++;
//    }
//  } whole_map_iterate_end;
//
//  *current = count;
//  *accum += count;
//  if (*accum < *level) {
//    *accum = 0;
//  } else {
//    *accum -= *level;
//    if (myrand(200) <= *accum) {
//      upset_action_fn((map.xsize / 10) + (map.ysize / 10) + ((*accum) * 5));
//      *accum = 0;
//      *level+=4;
//    }
//  }
//
//  freelog(LOG_DEBUG,
//	  "environmental_upset: cause=%-4d current=%-2d level=%-2d accum=%-2d",
//	  cause, *current, *level, *accum);
//}
//
///**************************************************************************
// check for cease-fires running out; update reputation; update cancelling
// reasons
//**************************************************************************/
//static void update_diplomatics()
//{
//  for(player player1: game.players){
//    for(player player2: game.players){
//      player_diplstate pdiplstate =
//	  &player1.diplstates[player2.player_no];
//
//      pdiplstate.has_reason_to_cancel =
//	  MAX(pdiplstate.has_reason_to_cancel - 1, 0);
//
//      pdiplstate.contact_turns_left =
//	  MAX(pdiplstate.contact_turns_left - 1, 0);
//
//      if(pdiplstate.type == DS_CEASEFIRE) {
//	switch(--pdiplstate.turns_left) {
//	case 1:
//	  notify_player(player1,
//			_("Game: Concerned citizens point "
//  			  "out that the cease-fire with %s will run out soon."),
//			player2.name);
//  	  break;
//  	case 0:
//	  notify_player(player1,
//  			_("Game: The cease-fire with %s has "
//  			  "run out. You are now neutral towards the %s."),
//			player2.name,
//			get_nation_name_plural(player2.nation));
//	  pdiplstate.type = DS_NEUTRAL;
//	  check_city_workers(player1);
//	  check_city_workers(player2);
//  	  break;
//  	}
//        }
//    }
//    player1.reputation = 
//      MIN((get_player_bonus(player1, EFT_REGEN_REPUTATION) * 
//           GAME_MAX_REPUTATION / 1000) + 
//	  player1.reputation + GAME_REPUTATION_INCR,
//          GAME_MAX_REPUTATION);
//  }
//}
//
///**************************************************************************
//  Send packet which tells clients that the server is starting its
//  "end year" calculations (and will be sending end-turn updates etc).
//  (This is referred to as "before new year" in packet and client code.)
//**************************************************************************/
//static void before_end_year()
//{
//  lsend_packet_before_new_year(&game.est_connections);
//}
//
///**************************************************************************
//...
//**************************************************************************/
//static void ai_start_turn()
//{
//  shuffled_for(player pplayer: game.players){
//    if (pplayer.ai.control) {
//      ai_do_first_activities(pplayer);
//      flush_packets();			/* AIs can be such spammers... */
//    }
//  } shuffled_players_iterate_end;
//  kill_dying_players();
//}
//
///**************************************************************************
//Handle the beginning of each turn.
//Note: This does not give "time" to any player;
//      it is solely for updating turn-dependent data.
//**************************************************************************/
//static void begin_turn(boolean is_new_turn)
//{
//  freelog(LOG_DEBUG, "Begin turn");
//
//  if (is_new_turn) {
//    /* We build scores at the beginning and end of every turn.  We have to
//     * build them at the beginning so that the AI can use the data. */
//    for(player pplayer: game.players){
//      calc_civ_score(pplayer);
//    }
//  }
//
//  /* See if the value of fog of war has changed */
//  if (is_new_turn && game.fogofwar != game.fogofwar_old) {
//    if (game.fogofwar) {
//      enable_fog_of_war();
//      game.fogofwar_old = true;
//    } else {
//      disable_fog_of_war();
//      game.fogofwar_old = false;
//    }
//  }
//
//  if (is_new_turn) {
//    freelog(LOG_DEBUG, "Shuffleplayers");
//    shuffle_players();
//  }
//
//  sanity_check();
//}
//
///**************************************************************************
//  Begin a phase of movement.  This handles all beginning-of-phase actions
//  for one or more players.
//**************************************************************************/
//static void begin_phase(boolean is_new_phase)
//{
//  freelog(LOG_DEBUG, "Begin phase");
//
//  conn_list_do_buffer(&game.game_connections);
//
//  for(player pplayer: game.players){
//    freelog(LOG_DEBUG, "beginning player turn for #%d (%s)",
//	    pplayer.player_no, pplayer.name);
//    /* human players also need this for building advice */
//    ai_data_turn_init(pplayer);
//    ai_manage_buildings(pplayer);
//  }
//
//  for(player pplayer: game.players){
//    send_player_cities(pplayer);
//  }
//
//  flush_packets();  /* to curb major city spam */
//  conn_list_do_unbuffer(&game.game_connections);
//
//  shuffled_for(player pplayer: game.players){
//    update_revolution(pplayer);
//  } shuffled_players_iterate_end;
//
//  if (is_new_phase) {
//    /* Try to avoid hiding events under a diplomacy dialog */
//    for(player pplayer: game.players){
//      if (pplayer.ai.control && !is_barbarian(pplayer)) {
//	ai_diplomacy_actions(pplayer);
//      }
//    }
//
//    freelog(LOG_DEBUG, "Aistartturn");
//    ai_start_turn();
//  }
//
//  send_start_turn_to_clients();
//
//  sanity_check();
//}
//
///**************************************************************************
//  End a phase of movement.  This handles all end-of-phase actions
//  for one or more players.
//**************************************************************************/
//static void end_phase()
//{
//  freelog(LOG_DEBUG, "Endphase");
// 
//  /* 
//   * This empties the client Messages window; put this before
//   * everything else below, since otherwise any messages from the
//   * following parts get wiped out before the user gets a chance to
//   * see them.  --dwp
//   */
//  before_end_year();
//  for(player pplayer: game.players){
//    if (pplayer.research.researching == A_UNSET) {
//      if (choose_goal_tech(pplayer) == A_UNSET) {
//        choose_random_tech(pplayer);
//      }
//      update_tech(pplayer, 0);
//    }
//  }
//
//  /* Freeze sending of cities. */
//  nocity_send = true;
//
//  /* AI end of turn activities */
//  auto_settlers_init();
//  for(player pplayer: game.players){
//    if (pplayer.ai.control) {
//      ai_settler_init(pplayer);
//    }
//    auto_settlers_player(pplayer);
//    if (pplayer.ai.control) {
//      ai_do_last_activities(pplayer);
//    }
//  }
//
//  /* Refresh cities */
//  shuffled_for(player pplayer: game.players){
//    do_tech_parasite_effect(pplayer);
//    player_restore_units(pplayer);
//    update_city_activities(pplayer);
//    pplayer.research.changed_from=-1;
//    flush_packets();
//  } shuffled_players_iterate_end;
//
//  kill_dying_players();
//
//  /* Unfreeze sending of cities. */
//  nocity_send = false;
//  for(player pplayer: game.players){
//    send_player_cities(pplayer);
//    ai_data_turn_done(pplayer);
//  }
//  flush_packets();  /* to curb major city spam */
//
//  do_reveal_effects();
//  do_have_embassies_effect();
//
//  freelog(LOG_DEBUG, "Auto-Attack phase");
//  auto_attack();
//}
//
///**************************************************************************
//  Handle the end of each turn.
//**************************************************************************/
//static void end_turn()
//{
//  freelog(LOG_DEBUG, "Endturn");
//
//  /* Output some ranking and AI debugging info here. */
//  if (game.turn % 10 == 0) {
//    for(player pplayer: game.players){
//      gamelog(GAMELOG_INFO, pplayer);
//    }
//  }
//
//  /* We build scores at the beginning and end of every turn.  We have to
//   * build them at the end so that the history report can be built. */
//  for(player pplayer: game.players){
//    calc_civ_score(pplayer);
//  }
//
//  freelog(LOG_DEBUG, "Season of native unrests");
//  summon_barbarians(); /* wild guess really, no idea where to put it, but
//			  I want to give them chance to move their units */
//
//  update_environmental_upset(S_POLLUTION, &game.heating,
//			     &game.globalwarming, &game.warminglevel,
//			     global_warming);
//  update_environmental_upset(S_FALLOUT, &game.cooling,
//			     &game.nuclearwinter, &game.coolinglevel,
//			     nuclear_winter);
//  update_diplomatics();
//  make_history_report();
//  stdinhand_turn();
//  send_player_turn_notifications(null);
//
//  freelog(LOG_DEBUG, "Turn ended.");
//  game.turn_start = time(null);
//
//  freelog(LOG_DEBUG, "Gamenextyear");
//  game_advance_year();
//  after_game_advance_year();
//
//  freelog(LOG_DEBUG, "Updatetimeout");
//  update_timeout();
//
//  check_spaceship_arrivals();
//
//  freelog(LOG_DEBUG, "Sendplayerinfo");
//  send_player_info(null, null);
//
//  freelog(LOG_DEBUG, "Sendgameinfo");
//  send_game_info(null);
//
//  freelog(LOG_DEBUG, "Sendyeartoclients");
//  send_year_to_clients(game.year);
//}
//
///**************************************************************************
//  After game advance year stuff.
//**************************************************************************/
//static void after_game_advance_year()
//{
//  /* Unit end of turn activities */
//  shuffled_for(player pplayer: game.players){
//    update_unit_activities(pplayer); /* major network traffic */
//    flush_packets();
//    pplayer.turn_done = false;
//  } shuffled_players_iterate_end;
//}
//
///**************************************************************************
//Unconditionally save the game, with specified filename.
//Always prints a message: either save ok, or failed.
//
//Note that if !HAVE_LIBZ, then game.save_compress_level should never
//become non-zero, so no need to check HAVE_LIBZ explicitly here as well.
//**************************************************************************/
//void save_game(char *orig_filename)
//{
//  char filename[600];
//  char *dot;
//  struct section_file file;
//  timer timer_cpu, *timer_user;
//
//  if (!orig_filename) {
//    filename[0] = '\0';
//  } else {
//    sz_strlcpy(filename, orig_filename);
//  }
//
//  /* Strip extension. */
//  if ((dot = strchr(filename, '.'))) {
//    *dot = '\0';
//  }
//
//  /* If orig_filename is null or empty, use "civgame<year>m". */
//  if (filename[0] == '\0'){
//    my_snprintf(filename, sizeof(filename),
//	"%s%+05dm", game.save_name, game.year);
//  }
//  
//  timer_cpu = new_timer_start(TIMER_CPU, TIMER_ACTIVE);
//  timer_user = new_timer_start(TIMER_USER, TIMER_ACTIVE);
//    
//  section_file_init(&file);
//  game_save(&file);
//
//  /* Append ".sav" to filename. */
//  sz_strlcat(filename, ".sav");
//
//  if (game.save_compress_level > 0) {
//    /* Append ".gz" to filename. */
//    sz_strlcat(filename, ".gz");
//  }
//
//  if (!path_is_absolute(filename)) {
//    char tmpname[600];
//
//    /* Ensure the saves directory exists. */
//    make_dir(srvarg.saves_pathname);
//
//    sz_strlcpy(tmpname, srvarg.saves_pathname);
//    if (tmpname[0] != '\0') {
//      sz_strlcat(tmpname, "/");
//    }
//    sz_strlcat(tmpname, filename);
//    sz_strlcpy(filename, tmpname);
//  }
//
//  if(!section_file_save(&file, filename, game.save_compress_level))
//    con_write(C_FAIL, "Failed saving game as %s", filename);
//  else
//    con_write(C_OK, "Game saved as %s", filename);
//
//  section_file_free(&file);
//
//  freelog(LOG_VERBOSE, "Save time: %g seconds (%g apparent)",
//	  read_timer_seconds_free(timer_cpu),
//	  read_timer_seconds_free(timer_user));
//}
//
///**************************************************************************
//Save game with autosave filename, and call gamelog_save().
//**************************************************************************/
//void save_game_auto()
//{
//  char filename[512];
//
//  assert(game.save_name.length()<256);
//  
//  my_snprintf(filename, sizeof(filename),
//	      "%s%+05d.sav", game.save_name, game.year);
//  save_game(filename);
//  save_ppm();
//  gamelog(GAMELOG_STATUS);
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void start_game()
//{
//  if(server_state!=server_states.PRE_GAME_STATE) {
//    con_puts(C_SYNTAX, "The game is already running.");
//    return;
//  }
//
//  con_puts(C_OK, "Starting game.");
//
//  server_state=SELECT_RACES_STATE; /* loaded ??? */
//  force_end_of_sniff = true;
//}
//
///**************************************************************************
// Quit the server and exit.
//**************************************************************************/
//void server_quit()
//{
//  server_game_free();
//  close_connections_and_socket();
//  exit(EXIT_SUCCESS);
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void handle_report_req(connection pconn, enum report_type type)
//{
//  Speclists<Connection> dest = &pconn.self;
//  
//  if (server_state != RUN_GAME_STATE && server_state != GAME_OVER_STATE
//      && type != REPORT_SERVER_OPTIONS1 && type != REPORT_SERVER_OPTIONS2) {
//    freelog(LOG_ERROR, "Got a report request %d before game start", type);
//    return;
//  }
//
//  switch(type) {
//   case REPORT_WONDERS_OF_THE_WORLD:
//    report_wonders_of_the_world(dest);
//    break;
//   case REPORT_TOP_5_CITIES:
//    report_top_five_cities(dest);
//    break;
//   case REPORT_DEMOGRAPHIC:
//    report_demographics(pconn);
//    break;
//  case REPORT_SERVER_OPTIONS1:
//    report_settable_server_options(pconn, 1);
//    break;
//  case REPORT_SERVER_OPTIONS2:
//    report_settable_server_options(pconn, 2);
//    break;
//  case REPORT_SERVER_OPTIONS: /* obsolete */
//  default:
//    notify_conn(dest, "Game: request for unknown report (type %d)", type);
//  }
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void dealloc_id(int id)
//{
//  used_ids[id/8]&= 0xff ^ (1<<(id%8));
//}
//
///**************************************************************************
//...
//**************************************************************************/
//static boolean is_id_allocated(int id)
//{
//  return TEST_BIT(used_ids[id / 8], id % 8);
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void alloc_id(int id)
//{
//  used_ids[id/8]|= (1<<(id%8));
//}
//
///**************************************************************************
//...
//**************************************************************************/
//
//int get_next_id_number()
//{
//  while (is_id_allocated(++global_id_counter) || global_id_counter == 0) {
//    /* nothing */
//  }
//  return global_id_counter;
//}
//
///**************************************************************************
//Returns 0 if connection should be closed (because the clients was
//rejected). Returns 1 else.
//**************************************************************************/
//boolean handle_packet_input(connection pconn, void *packet, int type)
//{
//  player pplayer;
//
//  /* a null packet can be returned from receive_packet_goto_route() */
//  if (!packet)
//    return true;
//
//  /* 
//   * Old pre-delta clients (before 2003-11-28) send a
//   * PACKET_LOGIN_REQUEST (type 0) to the server. We catch this and
//   * reply with an old reject packet. Since there is no struct for
//   * this old packet anymore we build it by hand.
//   */
//  if (type == 0) {
//    unsigned char buffer[4096];
//    struct data_out dout;
//
//    freelog(LOG_ERROR,
//	    "Warning: rejecting old client %s", conn_description(pconn));
//
//    dio_output_init(&dout, buffer, sizeof(buffer));
//    dio_put_uint16(&dout, 0);
//
//    /* 1 == PACKET_LOGIN_REPLY in the old client */
//    dio_put_uint8(&dout, 1);
//
//    dio_put_bool32(&dout, false);
//    dio_put_string(&dout, _("Your client is too old. To use this server "
//			    "please upgrade your client to a CVS version "
//			    "later than 2003-11-28 or Freeciv 1.15.0 or "
//			    "later."));
//    dio_put_string(&dout, "");
//
//    {
//      size_t size = dio_output_used(&dout);
//      dio_output_rewind(&dout);
//      dio_put_uint16(&dout, size);
//
//      /* 
//       * Use send_connection_data instead of send_packet_data to avoid
//       * compression.
//       */
//      send_connection_data(pconn, buffer, size);
//    }
//
//    return false;
//  }
//
//  if (type == PACKET_SERVER_JOIN_REQ) {
//    return handle_login_request(pconn,
//				(packet_server_join_req ) packet);
//  }
//
//  /* May be received on a non-established connection. */
//  if (type == PACKET_AUTHENTICATION_REPLY) {
//    return handle_authentication_reply(pconn,
//				((packet_authentication_reply )
//				 packet).password);
//  }
//
//  if (type == PACKET_CONN_PONG) {
//    handle_conn_pong(pconn);
//    return true;
//  }
//
//  if (!pconn.established) {
//    freelog(LOG_ERROR, "Received game packet from unaccepted connection %s",
//	    conn_description(pconn));
//    return true;
//  }
//  
//  /* valid packets from established connections but non-players */
//  if (type == PACKET_CHAT_MSG_REQ) {
//    handle_chat_msg_req(pconn,
//			((packet_chat_msg_req ) packet).message);
//    return true;
//  }
//
//  if (type == PACKET_SINGLE_WANT_HACK_REQ) {
//    handle_single_want_hack_req(pconn,
//		                (packet_single_want_hack_req ) packet);
//    return true;
//  }
//
//  pplayer = pconn.player;
//
//  if(!pplayer) {
//    /* don't support these yet */
//    freelog(LOG_ERROR, "Received packet from non-player connection %s",
// 	    conn_description(pconn));
//    return true;
//  }
//
//  if (server_state != RUN_GAME_STATE
//      && type != PACKET_NATION_SELECT_REQ
//      && type != PACKET_CONN_PONG
//      && type != PACKET_REPORT_REQ) {
//    if (server_state == GAME_OVER_STATE) {
//      /* This can happen by accident, so we don't want to print
//	 out lots of error messages. Ie, we use LOG_DEBUG. */
//      freelog(LOG_DEBUG, "got a packet of type %d "
//			  "in GAME_OVER_STATE", type);
//    } else {
//      freelog(LOG_ERROR, "got a packet of type %d "
//	                 "outside RUN_GAME_STATE", type);
//    }
//    return true;
//  }
//
//  pplayer.nturns_idle=0;
//
//  if((!pplayer.is_alive || pconn.observer)
//     && !(type == PACKET_REPORT_REQ || type == PACKET_CONN_PONG)) {
//    freelog(LOG_ERROR, _("Got a packet of type %d from a "
//			 "dead or observer player"), type);
//    return true;
//  }
//  
//  /* Make sure to set this back to null before leaving this function: */
//  pplayer.current_conn = pconn;
//
//  if (!server_handle_packet(type, packet, pplayer, pconn)) {
//    freelog(LOG_ERROR, "Received unknown packet %d from %s",
//	    type, conn_description(pconn));
//  }
//
//  if (server_state == RUN_GAME_STATE) {
//    kill_dying_players();
//  }
//
//  pplayer.current_conn = null;
//  return true;
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void check_for_full_turn_done()
//{
//  /* fixedlength is only applicable if we have a timeout set */
//  if (game.fixedlength && game.timeout != 0)
//    return;
//
//  for(player pplayer: game.players){
//    if (game.turnblock) {
//      if (!pplayer.ai.control && pplayer.is_alive && !pplayer.turn_done)
//        return;
//    } else {
//      if(pplayer.is_connected && pplayer.is_alive && !pplayer.turn_done) {
//        return;
//      }
//    }
//  }
//
//  force_end_of_sniff = true;
//}
//
///**************************************************************************
//  Checks if the player name belongs to the default player names of a
//  particular player.
//**************************************************************************/
//static boolean is_default_nation_name(final String name,
//				   Nation_Type_id nation_id)
//{
//  final nation_type nation = get_nation_by_idx(nation_id);
//
//  int choice;
//
//  for (choice = 0; choice < nation.leader_count; choice++) {
//    if (mystrcasecmp(name, nation.leaders[choice].name) == 0) {
//      return true;
//    }
//  }
//
//  return false;
//}
//
///**************************************************************************
//  Check if this name is allowed for the player.  Fill out the error message
//  (a translated string to be sent to the client) if not.
//**************************************************************************/
//static boolean is_allowed_player_name(player pplayer,
//				   Nation_Type_id nation,
//				   final String name,
//				   char *error_buf, size_t bufsz)
//{
//  connection pconn = find_conn_by_user(pplayer.username);
//
//  /* An empty name is surely not allowed. */
//  if (name.length() == 0) {
//    if (error_buf) {
//      my_snprintf(error_buf, bufsz, "Please choose a non-blank name.");
//    }
//    return false;
//  }
//
//  /* Any name already taken is not allowed. */
//  for(player other_player: game.players){
//    if (other_player.nation == nation) {
//      if (error_buf) {
//	my_snprintf(error_buf, bufsz, "That nation is already in use.");
//      }
//      return false;
//    } else {
//      /* Check to see if name has been taken.
//       * Ignore case because matches elsewhere are case-insenstive.
//       * Don't limit this check to just players with allocated nation:
//       * otherwise could end up with same name as pre-created AI player
//       * (which have no nation yet, but will keep current player name).
//       * Also want to keep all player names strictly distinct at all
//       * times (for server commands etc), including during nation
//       * allocation phase.
//       */
//      if (other_player.player_no != pplayer.player_no
//	  && mystrcasecmp(other_player.name, name) == 0) {
//	if (error_buf) {
//	  my_snprintf(error_buf, bufsz,
//		      _("Another player already has the name '%s'.  Please "
//			"choose another name."), name);
//	}
//	return false;
//      }
//    }
//  }
//
//  /* Any name from the default list is always allowed. */
//  if (is_default_nation_name(name, nation)) {
//    return true;
//  }
//
//  /* To prevent abuse, only players with HACK access (usually local
//   * connections) can use non-ascii names.  Otherwise players could use
//   * confusing garbage names in multi-player games. */
//    /* FIXME: is there a better way to determine if a *player* has hack
//     * access? */
//  if (!is_ascii_name(name)
//      && (!pconn || pconn.access_level != ALLOW_HACK)) {
//    if (error_buf) {
//      my_snprintf(error_buf, bufsz, _("Please choose a name containing "
//				      "only ASCII characters."));
//    }
//    return false;
//  }
//
//  return true;
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void handle_nation_select_req(player pplayer,
//			      Nation_Type_id nation_no, boolean is_male,
//			      char *name, int city_style)
//{
//  int nation_used_count;
//  char message[1024];
//
//  if (server_state != SELECT_RACES_STATE) {
//    freelog(LOG_ERROR, _("Trying to alloc nation outside "
//			 "of SELECT_RACES_STATE!"));
//    return;
//  }  
//  
//  /* check sanity of the packet sent by client */
//  if (nation_no < 0 || nation_no >= game.nation_count ||
//      city_style < 0 || city_style >= game.styles_count ||
//      city_styles[city_style].techreq != A_NONE
//      || !nations_available[nation_no]) {
//    return;
//  }
//
//  remove_leading_trailing_spaces(name);
//
//  if (!is_allowed_player_name(pplayer, nation_no, name,
//			      message, sizeof(message))) {
//    notify_player(pplayer, "%s", message);
//    send_select_nation(pplayer);
//    return;
//  }
//
//  name[0] = my_toupper(name[0]);
//
//  notify_conn_ex(&game.game_connections, null, E_NATION_SELECTED,
//		 "Game: %s is the %s ruler %s.", pplayer.username,
//		 get_nation_name(nation_no), name);
//
//  /* inform player his choice was ok */
//  lsend_packet_nation_select_ok(&pplayer.connections);
//
//  pplayer.nation = nation_no;
//  sz_strlcpy(pplayer.name, name);
//  pplayer.is_male = is_male;
//  pplayer.city_style = city_style;
//
//  /* tell the other players, that the nation is now unavailable */
//  nation_used_count = 0;
//
//  for(player other_player: game.players){
//    if (other_player.nation == NO_NATION_SELECTED) {
//      send_select_nation(other_player);
//    } else {
//      nation_used_count++;	/* count used nations */
//    }
//  }
//
//  mark_nation_as_used(nation_no);
//
//  /* if there's no nation left, reject remaining players, sorry */
//  if( nation_used_count == game.playable_nation_count ) {   /* barb */
//    for(player other_player: game.players){
//      if (other_player.nation == NO_NATION_SELECTED) {
//	freelog(Log.LOG_NORMAL, "No nations left: Removing player %s.",
//		other_player.name);
//	notify_player(other_player,
//		      "Game: Sorry, there are no nations left.");
//	server_remove_player(other_player);
//      }
//    }
//  }
//}
//
///**************************************************************************
// Sends the currently collected selected nations to the given player.
//**************************************************************************/
//static void send_select_nation(player pplayer)
//{
//  struct packet_nation_unavailable packet;
//  Nation_Type_id nation;
//
//  lsend_packet_select_races(&pplayer.connections);
//
//  for (nation = 0; nation < game.playable_nation_count; nation++) {
//    if (!nations_available[nation]) {
//      packet.nation = nation;
//      lsend_packet_nation_unavailable(&pplayer.connections, &packet);
//    }
//  }
//}
//
///**************************************************************************
//  If all players have chosen the same nation class, return
//  this class, otherwise return null.
//**************************************************************************/  
//static char* find_common_class() 
//{
//  char* class = null;
//  struct nation_type* nation;
//
//  for(player pplayer: game.players){
//    if (pplayer.nation == NO_NATION_SELECTED) {
//      /* still undecided */
//      continue;  
//    }
//    nation = get_nation_by_idx(pplayer.nation);
//    assert(nation.class != null);
//    if (class == null) {
//       /* Set the class. */
//      class = nation.class;
//    } else if (strcmp(nation.class, class) != 0) {
//      /* Multiple classes are already being used. */
//      return null;
//    }
//  }
//
//  return class;
//}
//
///**************************************************************************
//  Select a random available nation.  If 'class' is non-null, then choose
//  a nation from that class if possible.
//**************************************************************************/
//static Nation_Type_id select_random_nation(final char* class)
//{
//  Nation_Type_id i, available[game.playable_nation_count];
//  int count = 0;
//
//  /* Determine which nations are available. */
//  for (i = 0; i < game.playable_nation_count; i++) {
//    nation_type nation = get_nation_by_idx(i);
//
//    if (nations_available[i]
//	&& (class == null || nation.class.equals(class))) {
//      available[count] = i;
//      count++;
//    }
//  }
//
//  /* Handle the case where no nations are possible. */
//  if (count == 0) {
//    if (class) {
//      /* Try other classes. */
//      return select_random_nation(null);
//    }
//
//    /* Or else return an invalid value. */
//    return NO_NATION_SELECTED;
//  }
//
//  /* Then pick one. */
//  return available[myrand(count)];
//}
//
///**************************************************************************
//generate_ai_players() - Selects a nation for players created with
//   server's "create <PlayerName>" command.  If <PlayerName> matches
//   one of the leader names for some nation, we choose that nation.
//   (I.e. if we issue "create Shaka" then we will make that AI player's
//   nation the Zulus if the Zulus have not been chosen by anyone else.
//   If they have, then we pick an available nation at random.)
//
//   After that, we check to see if the server option "aifill" is greater
//   than the number of players currently connected.  If so, we create the
//   appropriate number of players (game.aifill - game.nplayers) from
//   scratch, choosing a random nation and appropriate name for each.
//   
//   When we choose a nation randomly we try to consider only nations
//   that are in the same class as nations choosen by other players.
//   (I.e., if human player decides to play English, AI won't use Mordorians.)
//
//   If the AI player name is one of the leader names for the AI player's
//   nation, the player sex is set to the sex for that leader, else it
//   is chosen randomly.  (So if English are ruled by Elisabeth, she is
//   female, but if "Player 1" rules English, may be male or female.)
//**************************************************************************/
//static void generate_ai_players()
//{
//  Nation_Type_id nation;
//  char player_name[MAX_LEN_NAME];
//  player pplayer;
//  int i, old_nplayers;
//  char* common_class;
//
//  /* Select nations for AI players generated with server
//   * 'create <name>' command
//   */
//  common_class = find_common_class();
//  for (i=0; i<game.nplayers; i++) {
//    pplayer = &game.players[i];
//    
//    if (pplayer.nation != NO_NATION_SELECTED) {
//      continue;
//    }
//
//    /* See if the AI player matches a known leader name. */
//    for (nation = 0; nation < game.playable_nation_count; nation++) {
//      if (check_nation_leader_name(nation, pplayer.name)
//	  && nations_available[nation]) {
//	mark_nation_as_used(nation);
//	pplayer.nation = nation;
//	pplayer.city_style = get_nation_city_style(nation);
//	pplayer.is_male = get_nation_leader_sex(nation, pplayer.name);
//	break;
//      }
//    }
//    if (pplayer.nation != NO_NATION_SELECTED) {
//      continue;
//    }
//
//    nation = select_random_nation(common_class);
//    if (nation == NO_NATION_SELECTED) {
//      freelog(Log.LOG_NORMAL,
//	      "Ran out of nations.  AI controlled player %s not created.",
//	      pplayer.name);
//      server_remove_player(pplayer); 
//      /*
//       * Below decrement loop index 'i' so that the loop is redone with
//       * the current index (if 'i' is still less than new game.nplayers).
//       * This is because subsequent players in list will have been shifted
//       * down one spot by the remove, and may need handling.
//       */
//      i--;  
//      continue;
//    } else {
//      mark_nation_as_used(nation);
//      pplayer.nation = nation;
//      pplayer.city_style = get_nation_city_style(nation);
//      pplayer.is_male = (myrand(2) == 1);
//    }
//
//    announce_ai_player(pplayer);
//  }
//  
//  /* We do this again, because user could type:
//   * >create Hammurabi
//   * >set aifill 5
//   * Now we are sure that all AI-players will use historical class
//   */
//  common_class = find_common_class();
//
//  /* Create and pick nation and name for AI players needed to bring the
//   * total number of players to equal game.aifill
//   */
//
//  if (game.playable_nation_count < game.aifill) {
//    game.aifill = game.playable_nation_count;
//    freelog(Log.LOG_NORMAL,
//	     "Nation count smaller than aifill; aifill reduced to %d.",
//             game.playable_nation_count);
//  }
//
//  if (game.max_players < game.aifill) {
//    game.aifill = game.max_players;
//    freelog(Log.LOG_NORMAL,
//	     "Maxplayers smaller than aifill; aifill reduced to %d.",
//             game.max_players);
//  }
//
//  /* we don't want aifill to count global observers unless 
//   * aifill = MAX_NUM_PLAYERS */
//  i = 0;
//  for(player pplayer: game.players){
//    if (pplayer.is_observer) {
//      i++;
//    }
//  }
//  if (game.aifill == MAX_NUM_PLAYERS) {
//    i = 0;
//  }
//
//  for(;game.nplayers < game.aifill + i;) {
//    nation = select_random_nation(common_class);
//    assert(nation != NO_NATION_SELECTED);
//    mark_nation_as_used(nation);
//    pick_ai_player_name(nation, player_name);
//
//    old_nplayers = game.nplayers;
//    pplayer = get_player(old_nplayers);
//     
//    sz_strlcpy(pplayer.name, player_name);
//    sz_strlcpy(pplayer.username, ANON_USER_NAME);
//
//    freelog(Log.LOG_NORMAL, "%s has been added as an AI-controlled player.",
//            player_name);
//    notify_conn(null,
//		"Game: %s has been added as an AI-controlled player.",
//		player_name);
//
//    game.nplayers++;
//
//    if (!((game.nplayers == old_nplayers+1)
//	  && player_name.equals(pplayer.name))) {
//      con_write(C_FAIL, "Error creating new AI player: %s\n",
//		player_name);
//      break;			/* don't loop forever */
//    }
//      
//    pplayer.nation = nation;
//    pplayer.city_style = get_nation_city_style(nation);
//    pplayer.ai.control = true;
//    pplayer.ai.skill_level = game.skill_level;
//    if (check_nation_leader_name(nation, player_name)) {
//      pplayer.is_male = get_nation_leader_sex(nation, player_name);
//    } else {
//      pplayer.is_male = (myrand(2) == 1);
//    }
//    announce_ai_player(pplayer);
//    set_ai_level_direct(pplayer, pplayer.ai.skill_level);
//  }
//  () send_server_info_to_metaserver(META_INFO);
//}
//
///*************************************************************************
// Used in pick_ai_player_name() below; buf has size at least MAX_LEN_NAME;
//*************************************************************************/
//static boolean good_name(char *ptry, char *buf) {
//  if (!(find_player_by_name(ptry) || find_player_by_user(ptry))) {
//     () mystrlcpy(buf, ptry, MAX_LEN_NAME);
//     return true;
//  }
//  return false;
//}
//
///*************************************************************************
// pick_ai_player_name() - Returns a random ruler name picked from given nation
//     ruler names, given that nation's number. If that player name is already 
//     taken, iterates through all leader names to find unused one. If it fails
//     it iterates through "Player 1", "Player 2", ... until an unused name
//     is found.
// newname should point to a buffer of size at least MAX_LEN_NAME.
//*************************************************************************/
//void pick_ai_player_name(Nation_Type_id nation, char *newname) 
//{
//   int i, names_count;
//   leader leaders;
//
//   leaders = get_nation_leaders(nation, &names_count);
//
//   /* Try random names (scattershot), then all available,
//    * then "Player 1" etc:
//    */
//   for(i=0; i<names_count; i++) {
//     if (good_name(leaders[myrand(names_count)].name, newname)) {
//       return;
//     }
//   }
//   
//   for(i=0; i<names_count; i++) {
//     if (good_name(leaders[i].name, newname)) {
//       return;
//     }
//   }
//   
//   for(i=1; /**/; i++) {
//     char tempname[50];
//     my_snprintf(tempname, sizeof(tempname), "Player %d", i);
//     if (good_name(tempname, newname)) return;
//   }
//}
//
///*************************************************************************
//  Simply mark the nation as unavailable.
//*************************************************************************/
//static void mark_nation_as_used (Nation_Type_id nation) 
//{
//  assert(nations_available[nation]);
//  nations_available[nation] = false;
//}
//
///*************************************************************************
//...
//*************************************************************************/
//static void announce_ai_player (player pplayer) {
//   freelog(Log.LOG_NORMAL, "AI is controlling the %s ruled by %s.",
//                    get_nation_name_plural(pplayer.nation),
//                    pplayer.name);
//
//  for(player other_player: game.players){
//    notify_player(other_player,
//		  "Game: %s rules the %s.", pplayer.name,
//		  get_nation_name_plural(pplayer.nation));
//  }
//}
//
///**************************************************************************
//Play the game! Returns when server_state == GAME_OVER_STATE.
//**************************************************************************/
//static void main_loop()
//{
//  timer eot_timer;	/* time server processing at end-of-turn */
//  int save_counter = 0;
//  boolean is_new_turn = game.is_new_game;
//
//  /* We may as well reset is_new_game now. */
//  game.is_new_game = false;
//
//  eot_timer = new_timer_start(TIMER_CPU, TIMER_ACTIVE);
//
//  /* 
//   * This will freeze the reports and agents at the client.
//   * 
//   * Do this before the body so that the PACKET_THAW_HINT packet is
//   * balanced. 
//   */
//  lsend_packet_freeze_hint(&game.game_connections);
//
//  while(server_state==RUN_GAME_STATE) {
//    /* The beginning of a turn.
//     *
//     * We have to initialize data as well as do some actions.  However when
//     * loading a game we don't want to do these actions (like AI unit
//     * movement and AI diplomacy). */
//    begin_turn(is_new_turn);
//    begin_phase(is_new_turn);
//    is_new_turn = true;
//
//    force_end_of_sniff = false;
//
//    /* 
//     * This will thaw the reports and agents at the client.
//     */
//    lsend_packet_thaw_hint(&game.game_connections);
//
//    /* Before sniff (human player activites), report time to now: */
//    freelog(LOG_VERBOSE, "End/start-turn server/ai activities: %g seconds",
//	    read_timer_seconds(eot_timer));
//
//    /* Do auto-saves just before starting sniff_packets(), so that
//     * autosave happens effectively "at the same time" as manual
//     * saves, from the point of view of restarting and AI players.
//     * Post-increment so we don't count the first loop.
//     */
//    if(save_counter >= game.save_nturns && game.save_nturns>0) {
//      save_counter=0;
//      save_game_auto();
//    }
//    save_counter++;
//    
//    freelog(LOG_DEBUG, "sniffingpackets");
//    while (sniff_packets() == 1) {
//      /* nothing */
//    }
//
//    /* After sniff, re-zero the timer: (read-out above on next loop) */
//    clear_timer_start(eot_timer);
//    
//    conn_list_do_buffer(&game.game_connections);
//
//    sanity_check();
//
//    /* 
//     * This will freeze the reports and agents at the client.
//     */
//    lsend_packet_freeze_hint(&game.game_connections);
//
//    end_phase();
//    end_turn();
//    freelog(LOG_DEBUG, "Sendinfotometaserver");
//    () send_server_info_to_metaserver(META_REFRESH);
//
//    conn_list_do_unbuffer(&game.game_connections);
//
//    if (is_game_over()) {
//      server_state=GAME_OVER_STATE;
//    }
//  }
//
//  /* 
//   * This will thaw the reports and agents at the client.
//   */
//  lsend_packet_thaw_hint(&game.game_connections);
//
//  free_timer(eot_timer);
//}

/**************************************************************************
  Server initialization.
**************************************************************************/
public void srv_main()
{
  /* make sure it's initialized */
  if (!has_been_srv_init) {
    srv_init();
  }

//  my_init_network();
//
//  con_log_init(srvarg.log_filename, srvarg.loglevel);
//  gamelog_init(srvarg.gamelog_filename);
//  gamelog_set_level(GAMELOG_FULL);
//  gamelog(GAMELOG_BEGIN);
//  
//#if IS_BETA_VERSION
//  con_puts(Erfc_status.C_COMMENT, "");
//  con_puts(Erfc_status.C_COMMENT, beta_message());
//  con_puts(Erfc_status.C_COMMENT, "");
//#endif
//  
//  con_flush();
//
//  game_init();
//  stdinhand_init();
//  diplhand_init();
//
//  /* init network */  
//  init_connections(); 
//  server_open_socket();
//
//  /* load a saved game */
//  if (srvarg.load_filename[0] != '\0') {
//    () load_command(null, srvarg.load_filename, false);
//  } 
//
//  if(!(srvarg.metaserver_no_send)) {
//    freelog(Log.LOG_NORMAL, "Sending info to metaserver [%s]",
//	    meta_addr_port());
//    server_open_meta(); /* open socket for meta server */ 
//  }
//
//  () send_server_info_to_metaserver(META_INFO);
//
//  /* accept new players, wait for serverop to start..*/
//  server_state = server_states.PRE_GAME_STATE;
//
//  /* load a script file */
//  if (srvarg.script_filename
//      && !read_init_script(null, srvarg.script_filename)) {
//    exit(EXIT_FAILURE);
//  }
//
//  /* Run server loop */
//  while (true) {
//    srv_loop();
//
//    send_game_state(&game.game_connections, CLIENT_GAME_OVER_STATE);
//    report_final_scores();
//    show_map_to_all();
//    notify_conn(null, "Game: The game is over...");
//    gamelog(GAMELOG_JUDGE, GL_NONE);
//    send_server_info_to_metaserver(META_INFO);
//    if (game.save_nturns > 0) {
//      save_game_auto();
//    }
//    gamelog(GAMELOG_END);
//
//    /* Remain in GAME_OVER_STATE until players log out */
//    while (conn_list_size(&game.est_connections) > 0) {
//      () sniff_packets();
//    }
//
//    if (game.timeout == -1 || srvarg.exit_on_end) {
//      /* For autogames or if the -e option is specified, exit the server. */
//      server_quit();
//    }
//
//    /* Reset server */
//    server_game_free();
//    game_init();
//    game.is_new_game = true;
//    server_state = server_states.PRE_GAME_STATE;
//  }

  /* Technically, we won't ever get here. We exit via server_quit. */
}
//
///**************************************************************************
//  Server loop, run to set up one game.
//**************************************************************************/
//static void srv_loop()
//{
//  int i;
//  boolean start_nations;
//
//  freelog(Log.LOG_NORMAL, "Now accepting new client connections.");
//  while(server_state == server_states.PRE_GAME_STATE) {
//    sniff_packets(); /* Accepting commands. */
//  }
//
//  () send_server_info_to_metaserver(META_INFO);
//
//  if (game.is_new_game) {
//    load_rulesets();
//    /* otherwise rulesets were loaded when savegame was loaded */
//  }
//
//  nations_available
//    = fc_realloc(nations_available,
//		 game.playable_nation_count * sizeof(*nations_available));
//
//main_start_players:
//
//  send_rulesets(&game.game_connections);
//
//  if (map.num_start_positions > 0) {
//    start_nations = true;
//
//    for (i = 0; i < map.num_start_positions; i++) {
//      if (map.start_positions[i].nation == NO_NATION_SELECTED) {
//	start_nations = false;
//	break;
//      }
//    }
//  } else {
//    start_nations = false;
//  }
//
//  if (start_nations) {
//    for (i = 0; i < game.playable_nation_count; i++) {
//      nations_available[i] = false;
//    }
//    for (i = 0; i < map.num_start_positions; i++) {
//      nations_available[map.start_positions[i].nation] = true;
//    }
//    
//  } else {
//    for (i = 0; i < game.playable_nation_count; i++) {
//      nations_available[i] = true;
//    }
//  }
//
//  if (game.auto_ai_toggle) {
//    for(player pplayer: game.players){
//      if (!pplayer.is_connected && !pplayer.ai.control) {
//	toggle_ai_player_direct(null, pplayer);
//      }
//    }
//  }
//
//  /* Allow players to select a nation (case new game).
//   * AI players may not yet have a nation; these will be selected
//   * in generate_ai_players() later
//   */
//  server_state = RUN_GAME_STATE;
//  for(player pplayer: game.players){
//    ai_data_analyze_rulesets(pplayer);
//    if (pplayer.is_observer) {
//      pplayer.nation = OBSERVER_NATION;
//    } else if (pplayer.nation == NO_NATION_SELECTED && !pplayer.ai.control) {
//      send_select_nation(pplayer);
//      server_state = SELECT_RACES_STATE;
//    }
//  }
//
//  while(server_state == SELECT_RACES_STATE) {
//    boolean flag = false;
//
//    sniff_packets();
//
//    for(player pplayer: game.players){
//      if (pplayer.nation == NO_NATION_SELECTED && !pplayer.ai.control) {
//	flag = true;
//	break;
//      }
//    }
//
//    if (!flag) {
//      if (game.nplayers > 0) {
//	server_state = RUN_GAME_STATE;
//      } else {
//	con_write(Erfc_status.C_COMMENT,
//		  "Last player has disconnected: will need to restart.");
//	server_state = server_states.PRE_GAME_STATE;
//	while(server_state == server_states.PRE_GAME_STATE) {
//	  sniff_packets();
//	}
//	goto main_start_players;
//      }
//    }
//  }
//
//  init_game_seed();
//
//#ifdef TEST_RANDOM /* not defined anywhere, set it if you want it */
//  test_random1(200);
//  test_random1(2000);
//  test_random1(20000);
//  test_random1(200000);
//#endif
//
//  if (game.is_new_game) {
//    generate_ai_players();
//  }
//   
//  /* If we have a tile map, and map.generator==0, call map_fractal_generate
//   * anyway to make the specials, huts and continent numbers. */
//  if (map_is_empty() || (map.generator == 0 && game.is_new_game)) {
//    map_fractal_generate(true);
//  }
//
//  gamelog(GAMELOG_MAP);
//  /* start the game */
//
//  server_state = RUN_GAME_STATE;
//  () send_server_info_to_metaserver(META_INFO);
//
//  if(game.is_new_game) {
//    /* Before the player map is allocated (and initiailized)! */
//    game.fogofwar_old = game.fogofwar;
//
//    allot_island_improvs();
//
//    for(player pplayer: game.players){
//      player_map_allocate(pplayer);
//      init_tech(pplayer);
//      player_limit_to_government_rates(pplayer);
//      pplayer.economic.gold = game.gold;
//    }
//    
//    for(player pplayer: game.players){
//      int i;
//      boolean free_techs_already_given = false;
//
//      give_initial_techs(pplayer);
//    
//      for(player eplayer: game.players){
//        if (players_on_same_team(eplayer, pplayer) &&
//            eplayer.player_no < pplayer.player_no) {
//	  free_techs_already_given = true;
//	  break;
//        }
//      }
//      
//      if (free_techs_already_given) {
//        break;
//      }
//      for (i = 0; i < game.tech; i++) {
//        give_random_initial_tech(pplayer);
//      }
//    }
//    
//    if(game.is_new_game) {
//      /* If we're starting a new game, reset the max_players to be the
//       * number of players currently in the game.  But when loading a game
//       * we don't want to change it. */
//      game.max_players = game.nplayers;
//    }
//  }
//
//  /* Set up alliances based on team selections */
//  if (game.is_new_game) {
//   for(player pplayer: game.players){
//     for(player pdest: game.players){
//      if (players_on_same_team(pplayer, pdest)
//          && pplayer.player_no != pdest.player_no) {
//        pplayer.diplstates[pdest.player_no].type = diplstate_type.DS_TEAM;
//        give_shared_vision(pplayer, pdest);
//        pplayer.embassy |= (1 << pdest.player_no);
//      }
//    }
//   }
//  }
//
//  /* tell the gamelog about the players */
//  for(player pplayer: game.players){
//    gamelog(GAMELOG_PLAYER, pplayer);
//  }
//
//  /* tell the gamelog who is whose team */
//  team_iterate(pteam) {
//    gamelog(GAMELOG_TEAM, pteam);
//  } team_iterate_end;
//
//  initialize_move_costs(); /* this may be the wrong place to do this */
//  init_settlers(); /* create minimap and other settlers.c data */
//
//  if (!game.is_new_game) {
//    for(player pplayer: game.players){
//      if (pplayer.ai.control) {
//	set_ai_level_direct(pplayer, pplayer.ai.skill_level);
//      }
//    }
//  } else {
//    for(player pplayer: game.players){
//      ai_data_init(pplayer); /* Initialize this at last moment */
//    }
//  }
//  
//  /* We want to reset the timer as late as possible but before the info is
//   * sent to the clients */
//  game.turn_start = time(null);
//
//  lsend_packet_freeze_hint(&game.game_connections);
//  send_all_info(&game.game_connections);
//  lsend_packet_thaw_hint(&game.game_connections);
//  
//  if(game.is_new_game) {
//    init_new_game();
//
//    /* give global observers the entire map */
//    for(player pplayer: game.players){
//      if (pplayer.is_observer) {
//        map_know_and_see_all(pplayer);
//      }
//    }
//  }
//
//  send_game_state(&game.game_connections, CLIENT_GAME_RUNNING_STATE);
//
//  /*** Where the action is. ***/
//  main_loop();
//}
//
///**************************************************************************
// ...
//**************************************************************************/
//void server_game_free()
//{
//  for(player pplayer: game.players){
//    player_map_free(pplayer);
//  }
//  diplhand_free();
//  game_free();
//  stdinhand_free();
//  ruleset_cache_free();
//  BV_CLR_ALL(srvarg.draw);
//}
}