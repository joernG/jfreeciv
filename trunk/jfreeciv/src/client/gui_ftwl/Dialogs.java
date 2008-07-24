package client.gui_ftwl;

public class Dialogs{

// Freeciv - Copyright (C) 2004 - The Freeciv Project
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
//#include "fcintl.h"
//#include "Game.game.h"
//#include "government.h"
//#include "packets.h"
//#include "rand.h"
//#include "shared.h"
//#include "support.h"
//
//#include "chatline_common.h"
//#include "clinet.h"
//#include "gui_main.h"
//#include "widget.h"
//
//#include "dialogs.h"
//#include "mapview.h"
//
//static sw_widget nations_window;
//#if 0
//static sw_widget nations_list;
//static sw_widget leaders_list;
//static sw_widget leaders_sex_list;
//static int selected_nation;
//#endif
//
///**************************************************************************
//  Popup a dialog to display information about an event that has a
//  specific location.  The user should be given the option to goto that
//  location.
//**************************************************************************/
//void popup_notify_goto_dialog(final String headline, final String lines,
//			      tile ptile)
//{
//  /* PORTME */
//}
//
///**************************************************************************
//  Popup a generic dialog to display some generic information.
//**************************************************************************/
//void popup_notify_dialog(final String caption, final String headline,
//			 final String lines)
//{
//  /* PORTME */
//}
//
//#if 0
///****************************************************************
// ...
//*****************************************************************/
//static void connect_callback(sw_widget list, void *data)
//{
//  int leader_count, leader=sw_list_get_selected_row(leaders_list);
//  leader leaders = get_nation_leaders(selected_nation, &leader_count);
//
//  if (strlen(leaders[leader].name) == 0) {
//    append_output_window("You must type a legal name.");
//    return;
//  }
//
//  dsend_packet_nation_select_req(&aconnection, selected_nation,
//				 sw_list_get_selected_row(leaders_sex_list)==0,
//				 leaders[leader].name,1);
//}
//
///****************************************************************
// ...
//*****************************************************************/
//static void select_random_nation()
//{
//  /* try to find a free nation */
//  while (1) {
//    int index = Rand.myrand(Game.game.playable_nation_count);
//
//    if (sw_list_is_row_enabled(nations_list, index)) {
//      sw_list_set_selected_row(nations_list, index, true);
//      break;
//    }
//  }
//}
//
///****************************************************************
//  Selects a leader.
//*****************************************************************/
//static void select_random_leader()
//{
//  int leader_count, i;
//  
//  get_nation_leaders(selected_nation, &leader_count);
//
//  i = Rand.myrand(leader_count);
//  sw_list_set_selected_row(leaders_list, i, true);
//}
//
///****************************************************************
// ...
//*****************************************************************/
//static void nations_list_selection_changed(sw_widget widget,
//					   void *data)
//{
//  int row = sw_list_get_selected_row(nations_list);
//  nation_type nation = get_nation_by_idx(row);
//  int leader_count, i;
//  leader leaders = get_nation_leaders(row, &leader_count);
//
//  selected_nation = row;
//
//  util.freelog(Log.LOG_DEBUG, "selcted %s\n", nation.name);
//  sw_list_clear(leaders_list);
//
//  for (i = 0; i < leader_count; i++) {
//    sw_widget label;
//    ct_string string;
//
//    string = ct_string_create(STYLE_NORMAL, 14,
//			      ct_extend_std_color(COLOR_STD_BLACK),
//			      COLOR_EXT_GRAY, leaders[i].name);
//    label = sw_label_create_text(root_window, string);
//    sw_list_set_item(leaders_list, 0, i, label);
//  }
//  select_random_leader();
//}
//
///****************************************************************
// ...
//*****************************************************************/
//static void leaders_list_selection_changed(sw_widget widget,
//					   void *data)
//{
//  int row = sw_list_get_selected_row(leaders_list);
//  int leader_count;
//  leader leaders = get_nation_leaders(selected_nation, &leader_count);
//
//  sw_list_set_selected_row(leaders_sex_list, leaders[row].is_male ? 0 : 1,
//			   false);
//}
//#endif
//
///**************************************************************************
//  Popup the nation selection dialog.
//**************************************************************************/
//void popup_races_dialog()
//{
//#if 0
//  ct_string string;
//  sw_widget label;
//  sw_widget button;
//  int i;
//
//  string = ct_string_create(STYLE_NORMAL, 14,
//			    ct_extend_std_color(COLOR_STD_BLACK),
//			    COLOR_EXT_GRAY, "Choose your nation");
//
//  nations_window =
//      sw_window_create(root_window, 600, 350, string, 0, true);
//  sw_widget_set_position(nations_window, 20, 120);
//  sw_widget_set_background_color(nations_window, COLOR_STD_RACE2);
//
//  string = ct_string_create(STYLE_NORMAL, 34,
//			    ct_extend_std_color(COLOR_STD_BLACK),
//			    COLOR_EXT_BLUE, "Ok");
//  button =
//      sw_button_create(nations_window, string, null, null,
//		       theme.button_background);
//  sw_widget_set_position(button, 30, 250);
//  sw_button_set_callback(button, connect_callback, null);
//
//  nations_list = sw_list_create(nations_window, 200, 200);
//  sw_widget_set_position(nations_list, 20, 20);
//  sw_list_add_buttons_and_vslider(nations_list, theme.button.up,
//				  theme.button.down,
//				  theme.button_background,
//				  theme.scrollbar.vertic);
//  sw_list_set_selection_changed_notify(nations_list,
//				       nations_list_selection_changed, null);
//
//  leaders_list = sw_list_create(nations_window, 200, 100);
//  sw_widget_set_position(leaders_list, 300, 20);
//  sw_list_add_buttons_and_vslider(leaders_list, theme.button.up,
//				  theme.button.down,
//				  theme.button_background,
//				  theme.scrollbar.vertic);
//  sw_list_set_selection_changed_notify(leaders_list,
//				       leaders_list_selection_changed, null);
//
//  leaders_sex_list = sw_list_create(nations_window, 200, 50);
//  sw_widget_set_position(leaders_sex_list, 300, 150);
//
//  string = ct_string_create(STYLE_NORMAL, 14,
//			    ct_extend_std_color(COLOR_STD_BLACK),
//			    COLOR_EXT_GRAY, "Male");
//  label = sw_label_create_text(root_window, string);
//  sw_list_set_item(leaders_sex_list, 0, 0, label);
//
//  string = ct_string_create(STYLE_NORMAL, 14,
//			    ct_extend_std_color(COLOR_STD_BLACK),
//			    COLOR_EXT_GRAY, "Female");
//  label = sw_label_create_text(root_window, string);
//  sw_list_set_item(leaders_sex_list, 0, 1, label);
//
//  for (i = 0; i < Game.game.playable_nation_count; i++) {
//    nation_type nation = get_nation_by_idx(i);
//
//    button = sw_button_create(nations_window, null,
//			      null, nation.flag_sprite, null);
//    sw_list_set_item(nations_list, 0, i, button);
//
//    string = ct_string_create(STYLE_NORMAL, 14,
//			      ct_extend_std_color(COLOR_STD_BLACK),
//			      COLOR_EXT_GRAY, nation.name);
//    label = sw_label_create_text(root_window, string);
//    sw_list_set_item(nations_list, 1, i, label);
//
//    string = ct_string_create(STYLE_ITALIC, 14,
//			      ct_extend_std_color(COLOR_STD_BLACK),
//			      COLOR_EXT_GRAY, Q_(nation.class));
//    label = sw_label_create_text(root_window, string);
//    sw_list_set_item(nations_list, 2, i, label);    
//  }
//  select_random_nation();
//#endif
//}
//
///**************************************************************************
//  Close the nation selection dialog.  This should allow the user to
//  (at least) select a unit to activate.
//**************************************************************************/
//void popdown_races_dialog()
//{
//  if (nations_window) {
//    sw_widget_destroy(nations_window);
//    nations_window = null;
//  }
//}
//
///**************************************************************************
//  Popup a dialog window to select units on a particular tile.
//**************************************************************************/
//void popup_unit_select_dialog(tile ptile)
//{
//  /* PORTME */
//}
//
///**************************************************************************
//  In the nation selection dialog, make already-taken nations unavailable.
//  This information is contained in the packet_nations_used packet.
//**************************************************************************/
//void races_toggles_set_sensitive(boolean *nations_used)
//{
//  /* PORTME */
//}
//
///**************************************************************************
//  Popup a dialog giving a player choices when their caravan arrives at
//  a city (other than its home city).  Example:
//    - Establish traderoute.
//    - Help build wonder.
//    - Keep moving.
//**************************************************************************/
//void popup_caravan_dialog(unit punit,
//			  city phomecity, city pdestcity)
//{
//  /* PORTME */
//}
//
///**************************************************************************
//  Is there currently a caravan dialog open?  This is important if there
//  can be only one such dialog at a time; otherwise return false.
//**************************************************************************/
//boolean caravan_dialog_is_open()
//{
//  /* PORTME */
//  return false;
//}
//
///**************************************************************************
//  Popup a dialog giving a diplomatic unit some options when moving into
//  the target tile.
//**************************************************************************/
//void popup_diplomat_dialog(unit punit, tile dest_tile)
//{
//  /* PORTME */
//}
//
///**************************************************************************
//  Return whether a diplomat dialog is open.  This is important if there
//  can be only one such dialog at a time; otherwise return false.
//**************************************************************************/
//boolean diplomat_dialog_is_open()
//{
//  /* PORTME */
//  return false;
//}
//
///**************************************************************************
//  Popup a window asking a diplomatic unit if it wishes to incite the
//  given enemy city.
//**************************************************************************/
//void popup_incite_dialog(city pcity)
//{
//  /* PORTME */
//}
//
///**************************************************************************
//  Popup a dialog asking a diplomatic unit if it wishes to bribe the
//  given enemy unit.
//**************************************************************************/
//void popup_bribe_dialog(unit punit)
//{
//  /* PORTME */
//}
//
///**************************************************************************
//  Popup a dialog asking a diplomatic unit if it wishes to sabotage the
//  given enemy city.
//**************************************************************************/
//void popup_sabotage_dialog(city pcity)
//{
//  /* PORTME */
//}
//
///**************************************************************************
//  Popup a dialog asking the unit which improvement they would like to
//  pillage.
//**************************************************************************/
//void popup_pillage_dialog(unit punit,
//			  enum int may_pillage)
//{
//  /* PORTME */
//}
//
///**************************************************************************
//  This function is called when the client disconnects or the Game.game is
//  over.  It should close all dialog windows for that Game.game.
//**************************************************************************/
//void popdown_all_game_dialogs()
//{
//  /* PORTME */
//  clear_focus_tile();
//}
}