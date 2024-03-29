package client;

public class Messagewin_common{

// Freeciv - Copyright (C) 2002 - R. Falke
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
//#include <string.h>
//
//#include "fcintl.h"
//#include "map.h"
//#include "mem.h"
//
//#include "citydlg_g.h"
//#include "mapview_g.h"
//#include "messagewin_g.h"
//#include "options.h"
//
//#include "messagewin_common.h"
//
//static int frozen_level = 0;
//static boolean change = false;
//static message messages = null;
//static int messages_total = 0;
//static int messages_alloc = 0;
//
///******************************************************************
// Turn off updating of message window
//*******************************************************************/
//void meswin_freeze()
//{
//  frozen_level++;
//}
//
///******************************************************************
// Turn on updating of message window
//*******************************************************************/
//void meswin_thaw()
//{
//  frozen_level--;
//  assert(frozen_level >= 0);
//  if (frozen_level == 0) {
//    update_meswin_dialog();
//  }
//}
//
///******************************************************************
// Turn on updating of message window
//*******************************************************************/
//void meswin_force_thaw()
//{
//  frozen_level = 1;
//  meswin_thaw();
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void update_meswin_dialog()
//{
//  if (frozen_level > 0 || !change) {
//    return;
//  }
//
//  if (!is_meswin_open() && messages_total > 0 &&
//      (!Game.game.player_ptr.ai.control || ai_popup_windows)) {
//    popup_meswin_dialog();
//    change = false;
//    return;
//  }
//
//  if (is_meswin_open()) {
//    real_update_meswin_dialog();
//    change = false;
//  }
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void clear_notify_window()
//{
//  int i;
//
//  change = true;
//  for (i = 0; i < messages_total; i++) {
//    free(messages[i].descr);
//    messages[i].descr = null;
//  }
//  messages_total = 0;
//  update_meswin_dialog();
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void add_notify_window(char *message, tile ptile,
//		       enum event_type event)
//{
//  final size_t min_msg_len = 50;
//  final String game_prefix1 = "Game: ";
//  final String game_prefix2 = "Game: ";
//  size_t gp_len1 = game_prefix1.length();
//  size_t gp_len2 = game_prefix2.length();
//  char *s = fc_malloc(MAX(message.length(), min_msg_len) + 1);
//  int i, nspc;
//
//  change = true;
//
//  if (messages_total + 2 > messages_alloc) {
//    messages_alloc = messages_total + 32;
//    messages = fc_realloc(messages, messages_alloc * sizeof(*messages));
//  }
//
//  if (!message.equals(game_prefix1)) {
//    strcpy(s, message + gp_len1);
//  } else if (!message.equals(game_prefix2)) {
//    strcpy(s, message + gp_len2);
//  } else {
//    strcpy(s, message);
//  }
//
//  nspc = min_msg_len - s.length();
//  if (nspc > 0) {
//    strncat(s, "                                                  ", nspc);
//  }
//
//  messages[messages_total].tile = ptile;
//  messages[messages_total].event = event;
//  messages[messages_total].descr = s;
//  messages[messages_total].location_ok = (ptile != null);
//  messages[messages_total].visited = false;
//  messages_total++;
//
//  /* 
//   * Update the city_ok fields of all messages since the city may have
//   * changed owner.
//   */
//  for (i = 0; i < messages_total; i++) {
//    if (messages[i].location_ok) {
//      city pcity = Map.map_get_city(messages[i].tile);
//
//      messages[i].city_ok = (pcity && City.city_owner(pcity) == Game.game.player_ptr);
//    } else {
//      messages[i].city_ok = false;
//    }
//  }
//
//  update_meswin_dialog();
//}
//
///**************************************************************************
// Returns the pointer to a message.
//**************************************************************************/
//message get_message(int message_index)
//{
//  assert(message_index >= 0 && message_index < messages_total);
//  return &messages[message_index];
//}
//
///**************************************************************************
// Returns the number of message in the window.
//**************************************************************************/
//int get_num_messages()
//{
//  return messages_total;
//}
//
///**************************************************************************
// Sets the visited-state of a message
//**************************************************************************/
//void set_message_visited_state(int message_index, boolean state)
//{
//  messages[message_index].visited = state;
//}
//
///**************************************************************************
// Called from messagewin.c if the user clicks on the popup-city button.
//**************************************************************************/
//void meswin_popup_city(int message_index)
//{
//  assert(message_index < messages_total);
//
//  if (messages[message_index].city_ok) {
//    tile ptile = messages[message_index].tile;
//    city pcity = Map.map_get_city(ptile);
//
//    if (center_when_popup_city) {
//      center_tile_mapcanvas(ptile);
//    }
//
//    if (pcity && City.city_owner(pcity) == Game.game.player_ptr) {
//      /* If the event was the city being destroyed, pcity will be null
//       * and we'd better not try to pop it up.  It's also possible that
//       * events will happen on enemy cities; we generally don't want to pop
//       * those dialogs up either (although it's hard to be certain).
//       *
//       * In both cases, it would be better if the popup button weren't
//       * highlighted at all - this is left up to the GUI. */
//      popup_city_dialog(pcity, false);
//    }
//  }
//}
//
///**************************************************************************
// Called from messagewin.c if the user clicks on the goto button.
//**************************************************************************/
//void meswin_goto(int message_index)
//{
//  assert(message_index < messages_total);
//
//  if (messages[message_index].location_ok) {
//    center_tile_mapcanvas(messages[message_index].tile);
//  }
//}
//
///**************************************************************************
// Called from messagewin.c if the user double clicks on a message.
//**************************************************************************/
//void meswin_double_click(int message_index)
//{
//  assert(message_index < messages_total);
//
//  if (messages[message_index].city_ok
//      && is_city_event(messages[message_index].event)) {
//    meswin_popup_city(message_index);
//  } else if (messages[message_index].location_ok) {
//    meswin_goto(message_index);
//  }
//}
}