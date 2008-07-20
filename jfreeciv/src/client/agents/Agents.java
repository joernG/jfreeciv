package client.agents;

public class Agents{

// Freeciv - Copyright (C) 2001 - R. Falke
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
//#include <stdarg.h>
//#include <string.h>
//
//#include "capability.h"
//#include "hash.h"
//#include "log.h"
//#include "mem.h"
//#include "timing.h"
//
//#include "civclient.h"
//#include "clinet.h"
//#include "cma_core.h"
//#include "cma_fec.h"
//#include "mapctrl_g.h"
//#include "sha.h"
//
//#include "agents.h"
//
//public static final int DEBUG_REQUEST_IDS = false;
//public static final int DEBUG_TODO_LISTS = false;
//public static final int META_CALLBACKS_LOGLEVEL = LOG_DEBUG;
//public static final int PRINT_STATS_LOGLEVEL = LOG_DEBUG;
//public static final int DEBUG_FREEZE = false;
//public static final int MAX_AGENTS = 10;
//
//struct my_agent;
//
//struct call {
//  my_agent agent;
//  enum oct { OCT_NEW_TURN, OCT_UNIT, OCT_CITY, OCT_TILE } type;
//  enum callback_type cb_type;
//  int arg;
//};
//
//#define SPECLIST_TAG call
//#define SPECLIST_TYPE struct call
//#include "speclist.h"
//
//#define call_list_iterate(calllist, pcall) \
//    TYPED_LIST_ITERATE(struct call, calllist, pcall)
//#define call_list_iterate_end  LIST_ITERATE_END
//
///*
// * Main data structure. Contains all registered agents and all
// * outstanding calls.
// */
//static struct {
//  int entries_used;
//  struct my_agent {
//    struct agent agent;
//    int first_outstanding_request_id, last_outstanding_request_id;
//    struct {
//      timer network_wall_timer;
//      int wait_at_network, wait_at_network_requests;
//    } stats;
//  } entries[MAX_AGENTS];
//  Speclists<call> calls;
//} agents;
//
//static boolean initialized = false;
//static int frozen_level;
//static boolean currently_running = false;
//
///****************************************************************************
//  Return true iff the two agent calls are equal.
//****************************************************************************/
//static boolean calls_are_equal(final call pcall1,
//			    final call pcall2)
//{
//  if (pcall1.type != pcall2.type && pcall1.cb_type != pcall2.cb_type) {
//    return false;
//  }
//
//  switch (pcall1.type) {
//  case OCT_UNIT:
//  case OCT_CITY:
//  case OCT_TILE:
//    return (pcall1.arg == pcall2.arg);
//  case OCT_NEW_TURN:
//    return true;
//  }
//
//  assert(0);
//  return false;
//}
//
///***********************************************************************
// If the call described by the given arguments isn't contained in
// agents.calls list add the call to this list.
//***********************************************************************/
//static void enqueue_call(my_agent agent,
//			 enum oct type,
//			 enum callback_type cb_type, ...)
//{
//  va_list ap;
//  call pcall2;
//  int x, y, arg = 0;
//
//  va_start(ap, cb_type);
//
//  if (client_is_observer()) {
//    return;
//  }
//
//  switch (type) {
//  case OCT_UNIT:
//  case OCT_CITY:
//    arg = va_arg(ap, int);
//    break;
//  case OCT_TILE:
//    x = va_arg(ap, int);
//    y = va_arg(ap, int);
//    arg = map_pos_to_index(x, y);
//    break;
//  case OCT_NEW_TURN:
//    /* nothing */
//    break;
//  default:
//    assert(0);
//  }
//  va_end(ap);
//
//  pcall2 = fc_malloc(sizeof(struct call));
//
//  pcall2.agent = agent;
//  pcall2.type = type;
//  pcall2.cb_type = cb_type;
//  pcall2.arg = arg;
//
//  for (call pcall : agents.calls.data) {
//    if (calls_are_equal(pcall, pcall2)) {
//      free(pcall2);
//      return;
//    }
//  } }
//
//  call_list_insert(&agents.calls, pcall2);
//
//  if (DEBUG_TODO_LISTS) {
//    util.freelog(Log.LOG_NORMAL, "A: adding call");
//  }
//
//  update_turn_done_button_state();
//}
//
///***********************************************************************
// Helper.
//***********************************************************************/
//static int my_call_sort(final void *a, final void *b)
//{
//  final call c1 = (final call ) *(final void **) a;
//  final call c2 = (final call ) *(final void **) b;
//
//  return c1.agent.agent.level - c2.agent.agent.level;
//}
//
///***********************************************************************
// Return an outstanding call. The call is removed from the agents.calls
// list. Returns null if there no more outstanding calls.
//***********************************************************************/
//static call remove_and_return_a_call()
//{
//  call result;
//
//  if (call_list_size(&agents.calls) == 0) {
//    return null;
//  }
//
//  /* get calls to agents with low levels first */
//  call_list_sort(&agents.calls, my_call_sort);
//
//  result = call_list_get(&agents.calls, 0);
//  call_list_unlink(&agents.calls, result);
//
//  if (DEBUG_TODO_LISTS) {
//    util.freelog(Log.LOG_NORMAL, "A: removed call");
//  }
//  return result;
//}
//
///***********************************************************************
// Calls an callback of an agent as described in the given call.
//***********************************************************************/
//static void execute_call(final call call)
//{
//  if (call.type == OCT_NEW_TURN) {
//    call.agent.agent.turn_start_notify();
//  } else if (call.type == OCT_UNIT) {
//    call.agent.agent.unit_callbacks[call.cb_type] (call.arg);
//  } else if (call.type == OCT_CITY) {
//    call.agent.agent.city_callbacks[call.cb_type] (call.arg);
//  } else if (call.type == OCT_TILE) {
//    call.agent.agent.tile_callbacks[call.cb_type]
//      (index_to_tile(call.arg));
//  } else {
//    assert(0);
//  }
//}
//
///***********************************************************************
// Execute all outstanding calls. This method will do nothing if the
// dispatching is frozen (frozen_level > 0). Also call_handle_methods
// will ensure that only one instance is running at any given time.
//***********************************************************************/
//static void call_handle_methods()
//{
//  if (currently_running) {
//    return;
//  }
//  if (frozen_level > 0) {
//    return;
//  }
//  currently_running = true;
//
//  /*
//   * The following should ensure that the methods of agents which have
//   * a lower level are called first.
//   */
//  for (;;) {
//    call pcall;
//
//    pcall = remove_and_return_a_call();
//    if (!pcall) {
//      break;
//    }
//
//    execute_call(pcall);
//    free(pcall);
//  }
//
//  currently_running = false;
//
//  update_turn_done_button_state();
//}
//
///***********************************************************************
// Increase the frozen_level by one.
//***********************************************************************/
//static void freeze()
//{
//  if (!initialized) {
//    frozen_level = 0;
//    initialized = true;
//  }
//  if (DEBUG_FREEZE) {
//    util.freelog(Log.LOG_NORMAL, "A: freeze() current level=%d", frozen_level);
//  }
//  frozen_level++;
//}
//
///***********************************************************************
// Decrease the frozen_level by one. If the dispatching is not frozen
// anymore (frozen_level == 0) all outstanding calls are executed.
//***********************************************************************/
//static void thaw()
//{
//  if (DEBUG_FREEZE) {
//    util.freelog(Log.LOG_NORMAL, "A: thaw() current level=%d", frozen_level);
//  }
//  frozen_level--;
//  assert(frozen_level >= 0);
//  if (frozen_level == 0 && get_client_state() == CLIENT_GAME_RUNNING_STATE) {
//    call_handle_methods();
//  }
//}
//
///***********************************************************************
// Helper.
//***********************************************************************/
//static my_agent find_agent_by_name(final String agent_name)
//{
//  int i;
//
//  for (i = 0; i < agents.entries_used; i++) {
//    if (strcmp(agent_name, agents.entries[i].agent.name) == 0)
//      return &agents.entries[i];
//  }
//
//  assert(0);
//  return null;
//}
//
///***********************************************************************
// Returns true iff currently handled packet was caused by the given
// agent.
//***********************************************************************/
//static boolean is_outstanding_request(my_agent agent)
//{
//  if (agent.first_outstanding_request_id != 0 &&
//      aconnection.client.request_id_of_currently_handled_packet != 0 &&
//      agent.first_outstanding_request_id <=
//      aconnection.client.request_id_of_currently_handled_packet &&
//      agent.last_outstanding_request_id >=
//      aconnection.client.request_id_of_currently_handled_packet) {
//    util.freelog(LOG_DEBUG,
//	    "A:%s: ignoring packet; outstanding [%d..%d] got=%d",
//	    agent.agent.name,
//	    agent.first_outstanding_request_id,
//	    agent.last_outstanding_request_id,
//	    aconnection.client.request_id_of_currently_handled_packet);
//    return true;
//  }
//  return false;
//}
//
///***********************************************************************
// Print statistics for the given agent.
//***********************************************************************/
//static void print_stats(my_agent agent)
//{
//  util.freelog(PRINT_STATS_LOGLEVEL,
//	  "A:%s: waited %fs in total for network; "
//	  "requests=%d; waited %d times",
//	  agent.agent.name,
//	  read_timer_seconds(agent.stats.network_wall_timer),
//	  agent.stats.wait_at_network_requests,
//	  agent.stats.wait_at_network);
//}
//
///***********************************************************************
// Called once per client startup.
//***********************************************************************/
//void agents_init()
//{
//  agents.entries_used = 0;
//  call_list_init(&agents.calls);
//
//  /* Add init calls of agents here */
//  cma_init();
//  cmafec_init();
//  /*simple_historian_init();*/
//}
//
///***********************************************************************
// ...
//***********************************************************************/
//void agents_free()
//{
//  int i;
//
//  /* FIXME: doing this will wipe out any presets on disconnect.
//   * a proper solution should be to split up the client_free functions 
//   * for a simple disconnect and a client quit. for right now, we just
//   * let the OS free the memory on exit instead of doing it ourselves. */
//  /* cmafec_free(); */
//
//  for (;;) {
//    call pcall = remove_and_return_a_call();
//    if (!pcall) {
//      break;
//    }
//
//    free(pcall);
//  }
//
//  for (i = 0; i < agents.entries_used; i++) {
//    my_agent agent = &agents.entries[i];
//
//    free_timer(agent.stats.network_wall_timer);
//  }
//}
//
///***********************************************************************
// Registers an agent.
//***********************************************************************/
//void register_agent(final agent agent)
//{
//  my_agent priv_agent = &agents.entries[agents.entries_used];
//
//  assert(agents.entries_used < MAX_AGENTS);
//  assert(agent.level > 0);
//
//  memcpy(&priv_agent.agent, agent, sizeof(struct agent));
//
//  priv_agent.first_outstanding_request_id = 0;
//  priv_agent.last_outstanding_request_id = 0;
//
//  priv_agent.stats.network_wall_timer = new_timer(TIMER_USER, TIMER_ACTIVE);
//  priv_agent.stats.wait_at_network = 0;
//  priv_agent.stats.wait_at_network_requests = 0;
//
//  agents.entries_used++;
//}
//
///***********************************************************************
// Called from client/packhand.c.
//***********************************************************************/
//void agents_disconnect()
//{
//  util.freelog(META_CALLBACKS_LOGLEVEL, "agents_disconnect()");
//  initialized = false;
//}
//
///***********************************************************************
// Called from client/packhand.c.
//***********************************************************************/
//void agents_processing_started()
//{
//  util.freelog(META_CALLBACKS_LOGLEVEL, "agents_processing_started()");
//  freeze();
//}
//
///***********************************************************************
// Called from client/packhand.c.
//***********************************************************************/
//void agents_processing_finished()
//{
//  util.freelog(META_CALLBACKS_LOGLEVEL, "agents_processing_finished()");
//  thaw();
//}
//
///***********************************************************************
// Called from client/packhand.c.
//***********************************************************************/
//void agents_freeze_hint()
//{
//  util.freelog(META_CALLBACKS_LOGLEVEL, "agents_freeze_hint()");
//  freeze();
//}
//
///***********************************************************************
// Called from client/packhand.c.
//***********************************************************************/
//void agents_thaw_hint()
//{
//  util.freelog(META_CALLBACKS_LOGLEVEL, "agents_thaw_hint()");
//  thaw();
//}
//
///***********************************************************************
// Called from client/packhand.c.
//***********************************************************************/
//void agents_game_joined()
//{
//  util.freelog(META_CALLBACKS_LOGLEVEL, "agents_game_joined()");
//}
//
///***********************************************************************
// Called from client/packhand.c.
//***********************************************************************/
//void agents_game_start()
//{
//  util.freelog(META_CALLBACKS_LOGLEVEL, "agents_game_start()");
//  call_handle_methods();
//}
//
///***********************************************************************
// Called from client/packhand.c.
//***********************************************************************/
//void agents_before_new_turn()
//{
//  util.freelog(META_CALLBACKS_LOGLEVEL, "agents_before_new_turn()");
//}
//
///***********************************************************************
// Called from client/packhand.c.
//***********************************************************************/
//void agents_start_turn()
//{
//  util.freelog(META_CALLBACKS_LOGLEVEL, "agents_start_turn()");
//}
//
///***********************************************************************
// Called from client/packhand.c. See agents_unit_changed for a generic
// documentation.
//***********************************************************************/
//void agents_new_turn()
//{
//  int i;
//
//  for (i = 0; i < agents.entries_used; i++) {
//    my_agent agent = &agents.entries[i];
//
//    if (is_outstanding_request(agent)) {
//      continue;
//    }
//    if (agent.agent.turn_start_notify) {
//      enqueue_call(agent, OCT_NEW_TURN, CB_LAST);
//    }
//  }
//  /*
//   * call_handle_methods() isn't called here because the agents are
//   * frozen anyway.
//   */
//}
//
///***********************************************************************
// Called from client/packhand.c. A call is created and added to the
// list of outstanding calls if an agent wants to be informed about this
// event and the change wasn't caused by the agent. We then try (this
// may not be successful in every case since we can be frozen or another
// call_handle_methods may be running higher up on the stack) to execute
// all outstanding calls.
//***********************************************************************/
//void agents_unit_changed(unit punit)
//{
//  int i;
//
//  util.freelog(LOG_DEBUG,
//	  "A: agents_unit_changed(unit=%d) type=%s pos=(%d,%d) owner=%s",
//	  punit.id, unit_types[punit.type].name, TILE_XY(punit.tile),
//	  unit_owner(punit).name);
//
//  for (i = 0; i < agents.entries_used; i++) {
//    my_agent agent = &agents.entries[i];
//
//    if (is_outstanding_request(agent)) {
//      continue;
//    }
//    if (agent.agent.unit_callbacks[CB_CHANGE]) {
//      enqueue_call(agent, OCT_UNIT, CB_CHANGE, punit.id);
//    }
//  }
//  call_handle_methods();
//}
//
///***********************************************************************
// Called from client/packhand.c. See agents_unit_changed for a generic
// documentation.
//***********************************************************************/
//void agents_unit_new(unit punit)
//{
//  int i;
//
//  util.freelog(LOG_DEBUG,
//	  "A: agents_new_unit(unit=%d) type=%s pos=(%d,%d) owner=%s",
//	  punit.id, unit_types[punit.type].name, TILE_XY(punit.tile),
//	  unit_owner(punit).name);
//
//  for (i = 0; i < agents.entries_used; i++) {
//    my_agent agent = &agents.entries[i];
//
//    if (is_outstanding_request(agent)) {
//      continue;
//    }
//    if (agent.agent.unit_callbacks[CB_NEW]) {
//      enqueue_call(agent, OCT_UNIT, CB_NEW, punit.id);
//    }
//  }
//
//  call_handle_methods();
//}
//
///***********************************************************************
// Called from client/packhand.c. See agents_unit_changed for a generic
// documentation.
//***********************************************************************/
//void agents_unit_remove(unit punit)
//{
//  int i;
//
//  util.freelog(LOG_DEBUG,
//	  "A: agents_remove_unit(unit=%d) type=%s pos=(%d,%d) owner=%s",
//	  punit.id, unit_types[punit.type].name, TILE_XY(punit.tile),
//	  unit_owner(punit).name);
//
//  for (i = 0; i < agents.entries_used; i++) {
//    my_agent agent = &agents.entries[i];
//
//    if (is_outstanding_request(agent)) {
//      continue;
//    }
//    if (agent.agent.unit_callbacks[CB_REMOVE]) {
//      enqueue_call(agent, OCT_UNIT, CB_REMOVE, punit.id);
//    }
//  }
//
//  call_handle_methods();
//}
//
///***********************************************************************
// Called from client/packhand.c. See agents_unit_changed for a generic
// documentation.
//***********************************************************************/
//void agents_city_changed(city pcity)
//{
//  int i;
//
//  util.freelog(LOG_DEBUG, "A: agents_city_changed(city='%s'(%d)) owner=%s",
//	  pcity.name, pcity.id, city_owner(pcity).name);
//
//  for (i = 0; i < agents.entries_used; i++) {
//    my_agent agent = &agents.entries[i];
//
//    if (is_outstanding_request(agent)) {
//      continue;
//    }
//    if (agent.agent.city_callbacks[CB_CHANGE]) {
//      enqueue_call(agent, OCT_CITY, CB_CHANGE, pcity.id);
//    }
//  }
//
//  call_handle_methods();
//}
//
///***********************************************************************
// Called from client/packhand.c. See agents_unit_changed for a generic
// documentation.
//***********************************************************************/
//void agents_city_new(city pcity)
//{
//  int i;
//
//  util.freelog(LOG_DEBUG,
//	  "A: agents_city_new(city='%s'(%d)) pos=(%d,%d) owner=%s",
//	  pcity.name, pcity.id, TILE_XY(pcity.tile),
//	  city_owner(pcity).name);
//
//  for (i = 0; i < agents.entries_used; i++) {
//    my_agent agent = &agents.entries[i];
//
//    if (is_outstanding_request(agent)) {
//      continue;
//    }
//    if (agent.agent.city_callbacks[CB_NEW]) {
//      enqueue_call(agent, OCT_CITY, CB_NEW, pcity.id);
//    }
//  }
//
//  call_handle_methods();
//}
//
///***********************************************************************
// Called from client/packhand.c. See agents_unit_changed for a generic
// documentation.
//***********************************************************************/
//void agents_city_remove(city pcity)
//{
//  int i;
//
//  util.freelog(LOG_DEBUG,
//	  "A: agents_city_remove(city='%s'(%d)) pos=(%d,%d) owner=%s",
//	  pcity.name, pcity.id, TILE_XY(pcity.tile),
//	  city_owner(pcity).name);
//
//  for (i = 0; i < agents.entries_used; i++) {
//    my_agent agent = &agents.entries[i];
//
//    if (is_outstanding_request(agent)) {
//      continue;
//    }
//    if (agent.agent.city_callbacks[CB_REMOVE]) {
//      enqueue_call(agent, OCT_CITY, CB_REMOVE, pcity.id);
//    }
//  }
//
//  call_handle_methods();
//}
//
///***********************************************************************
// Called from client/packhand.c. See agents_unit_changed for a generic
// documentation.
// Tiles got removed because of FOW.
//***********************************************************************/
//void agents_tile_remove(tile ptile)
//{
//  int i;
//
//  util.freelog(LOG_DEBUG, "A: agents_tile_remove(tile=(%d, %d))", TILE_XY(ptile));
//
//  for (i = 0; i < agents.entries_used; i++) {
//    my_agent agent = &agents.entries[i];
//
//    if (is_outstanding_request(agent)) {
//      continue;
//    }
//    if (agent.agent.tile_callbacks[CB_REMOVE]) {
//      enqueue_call(agent, OCT_TILE, CB_REMOVE, ptile);
//    }
//  }
//
//  call_handle_methods();
//}
//
///***********************************************************************
// Called from client/packhand.c. See agents_unit_changed for a generic
// documentation.
//***********************************************************************/
//void agents_tile_changed(tile ptile)
//{
//  int i;
//
//  util.freelog(LOG_DEBUG, "A: agents_tile_changed(tile=(%d, %d))", TILE_XY(ptile));
//
//  for (i = 0; i < agents.entries_used; i++) {
//    my_agent agent = &agents.entries[i];
//
//    if (is_outstanding_request(agent)) {
//      continue;
//    }
//    if (agent.agent.tile_callbacks[CB_CHANGE]) {
//      enqueue_call(agent, OCT_TILE, CB_CHANGE, ptile);
//    }
//  }
//
//  call_handle_methods();
//}
//
///***********************************************************************
// Called from client/packhand.c. See agents_unit_changed for a generic
// documentation.
//***********************************************************************/
//void agents_tile_new(tile ptile)
//{
//  int i;
//
//  util.freelog(LOG_DEBUG, "A: agents_tile_new(tile=(%d, %d))", TILE_XY(ptile));
//
//  for (i = 0; i < agents.entries_used; i++) {
//    my_agent agent = &agents.entries[i];
//
//    if (is_outstanding_request(agent)) {
//      continue;
//    }
//    if (agent.agent.tile_callbacks[CB_NEW]) {
//      enqueue_call(agent, OCT_TILE, CB_NEW, ptile);
//    }
//  }
//
//  call_handle_methods();
//}
//
///***********************************************************************
// Called from an agent. This function will return until the last
// request has been processed by the server.
//***********************************************************************/
//void wait_for_requests(final String agent_name, int first_request_id,
//		       int last_request_id)
//{
//  my_agent agent = find_agent_by_name(agent_name);
//
//  if (DEBUG_REQUEST_IDS) {
//    util.freelog(Log.LOG_NORMAL, "A:%s: wait_for_request(ids=[%d..%d])",
//	    agent.agent.name, first_request_id, last_request_id);
//  }
//
//  assert(first_request_id != 0 && last_request_id != 0
//	 && first_request_id <= last_request_id);
//  assert(agent.first_outstanding_request_id == 0);
//  agent.first_outstanding_request_id = first_request_id;
//  agent.last_outstanding_request_id = last_request_id;
//
//  start_timer(agent.stats.network_wall_timer);
//  wait_till_request_got_processed(last_request_id);
//  stop_timer(agent.stats.network_wall_timer);
//
//  agent.stats.wait_at_network++;
//  agent.stats.wait_at_network_requests +=
//      (1 + (last_request_id - first_request_id));
//
//  if (DEBUG_REQUEST_IDS) {
//    util.freelog(Log.LOG_NORMAL, "A:%s: wait_for_request: ids=[%d..%d]; got it",
//	    agent.agent.name, first_request_id, last_request_id);
//  }
//
//  agent.first_outstanding_request_id = 0;
//
//  print_stats(agent);
//}
//
///***********************************************************************
// Adds a specific call for the given agent.
//***********************************************************************/
//void cause_a_unit_changed_for_agent(final String name_of_calling_agent,
//				    unit punit)
//{
//  my_agent agent = find_agent_by_name(name_of_calling_agent);
//
//  assert(agent.agent.unit_callbacks[CB_CHANGE] != null);
//  enqueue_call(agent, OCT_UNIT, CB_CHANGE, punit.id);
//  call_handle_methods();
//}
//
///***********************************************************************
// Adds a specific call for the given agent.
//***********************************************************************/
//void cause_a_city_changed_for_agent(final String name_of_calling_agent,
//				    city pcity)
//{
//  my_agent agent = find_agent_by_name(name_of_calling_agent);
//
//  assert(agent.agent.city_callbacks[CB_CHANGE] != null);
//  enqueue_call(agent, OCT_CITY, CB_CHANGE, pcity.id);
//  call_handle_methods();
//}
//
///***********************************************************************
// Returns true iff some agent is currently busy.
//***********************************************************************/
//boolean agents_busy()
//{
//  int i;
//
//  if (!initialized || call_list_size(&agents.calls) > 0 || frozen_level > 0
//      || currently_running) {
//    return true;
//  }
//
//  for (i = 0; i < agents.entries_used; i++) {
//    my_agent agent = &agents.entries[i];
//
//    if (agent.first_outstanding_request_id != 0) {
//      return true;
//    }
//  }
//  return false;
//}
}