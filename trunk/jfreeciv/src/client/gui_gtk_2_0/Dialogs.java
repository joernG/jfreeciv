package client.gui_gtk_2_0;

public class Dialogs{

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
//#include <stdarg.h>
//#include <stdio.h>
//#include <stdlib.h>
//#include <string.h>
//
//#include <gtk/gtk.h>
//#include <gdk/gdkkeysyms.h>
//
//#include "fcintl.h"
//#include "Game.game.h"
//#include "government.h"
//#include "Map.map.h"
//#include "log.h"
//#include "mem.h"
//#include "packets.h"
//#include "player.h"
//#include "rand.h"
//#include "support.h"
//
//#include "chatline.h"
//#include "citydlg.h"
//#include "civclient.h"
//#include "climisc.h"
//#include "clinet.h"
//#include "connectdlg_common.h"
//#include "control.h"
//#include "goto.h"
//#include "graphics.h"
//#include "gui_main.h"
//#include "gui_stuff.h"
//#include "mapview.h"
//#include "options.h"
//#include "packhand.h"
//#include "tilespec.h"
//
//#include "dialogs.h"
//#include "wldlg.h"
//
///******************************************************************/
//GtkWidget *message_dialog_start(GtkWindow *parent, final gchar *name,
//				final gchar *text);
//void message_dialog_add(GtkWidget *dshell, final gchar *label,
//			GCallback handler, gpointer data);
//void message_dialog_end(GtkWidget *dshell);
//
//void message_dialog_set_hide(GtkWidget *dshell, gboolean setting);
//
///******************************************************************/
//static GtkWidget  *races_shell;
//static GtkWidget  *races_nation_list;
//static GtkWidget  *races_leader;
//static GList      *races_leader_list;
//static GtkWidget  *races_sex[2];
//static GtkWidget  *races_city_style_list;
//static GtkTextBuffer *races_text;
//
///******************************************************************/
//static GtkWidget  *spy_tech_shell;
//static int         steal_advance;
//
///******************************************************************/
//static GtkWidget  *spy_sabotage_shell;
//static int         sabotage_improvement;
//
///******************************************************************/
//public static final int SELECT_UNIT_READY = 1;
//public static final int SELECT_UNIT_SENTRY = 2;
//
//static GtkWidget *unit_select_dialog_shell;
//static GtkTreeStore *unit_select_store;
//static GtkWidget *unit_select_view;
//static GtkTreePath *unit_select_path;
//static tile unit_select_ptile;
//
//static void select_random_race();
//  
//static void create_races_dialog();
//static void races_destroy_callback(GtkWidget *w, gpointer data);
//static void races_response(GtkWidget *w, gint response, gpointer data);
//static void races_nation_callback(GtkTreeSelection *select, gpointer data);
//static void races_leader_callback();
//static void races_sex_callback(GtkWidget *w, gpointer data);
//static void races_city_style_callback(GtkTreeSelection *select, gpointer data);
//static gboolean races_selection_func(GtkTreeSelection *select,
//				     GtkTreeModel *model, GtkTreePath *path,
//				     gboolean selected, gpointer data);
//
//static int selected_nation;
//static int selected_sex;
//static int selected_city_style;
//
//static int is_showing_pillage_dialog = false;
//static int unit_to_use_to_pillage;
//
//static int caravan_city_id;
//static int caravan_unit_id;
//
//static GtkWidget *diplomat_dialog;
//static int diplomat_id;
//static int diplomat_target_id;
//
//static GtkWidget *caravan_dialog;
//
///**************************************************************************
//  Popup a generic dialog to display some generic information.
//**************************************************************************/
//void popup_notify_dialog(final String caption, final String headline,
//			 final String lines)
//{
//  static gui_dialog shell;
//  GtkWidget *vbox, *label, *headline_label, *sw;
//
//  gui_dialog_new(&shell, GTK_NOTEBOOK(bottom_notebook));
//  gui_dialog_set_title(shell, caption);
//
//  gui_dialog_add_button(shell, GTK_STOCK_CLOSE, GTK_RESPONSE_CLOSE); 
//  gui_dialog_set_default_response(shell, GTK_RESPONSE_CLOSE);
//
//  vbox = gtk_vbox_new(false, 2);
//  gtk_box_pack_start(GTK_BOX(shell.vbox), vbox, true, true, 0);
//
//  headline_label = gtk_label_new(headline);   
//  gtk_box_pack_start(GTK_BOX(vbox), headline_label, false, false, 0);
//  gtk_widget_set_name(headline_label, "notify label");
//
//  gtk_label_set_justify(GTK_LABEL(headline_label), GTK_JUSTIFY_LEFT);
//  gtk_misc_set_alignment(GTK_MISC(headline_label), 0.0, 0.0);
//
//  sw = gtk_scrolled_window_new(null, null);
//  gtk_scrolled_window_set_shadow_type(GTK_SCROLLED_WINDOW(sw),
//				      GTK_SHADOW_ETCHED_IN);
//  gtk_scrolled_window_set_policy(GTK_SCROLLED_WINDOW(sw),
//				 GTK_POLICY_NEVER, GTK_POLICY_AUTOMATIC);
//  label = gtk_label_new(lines);
//  gtk_scrolled_window_add_with_viewport(GTK_SCROLLED_WINDOW(sw), label);
//
//  gtk_widget_set_name(label, "notify label");
//  gtk_label_set_justify(GTK_LABEL(label), GTK_JUSTIFY_LEFT);
//  gtk_misc_set_alignment(GTK_MISC(label), 0.0, 0.0);
//
//  gtk_box_pack_start(GTK_BOX(vbox), sw, true, true, 0);
//
//  gui_dialog_show_all(shell);
//
//  gui_dialog_set_default_size(shell, -1, 265);
//  gui_dialog_present(shell);
//
//  shell = null;
//}
//
///****************************************************************
//...
//*****************************************************************/
//static void notify_goto_response(GtkWidget *w, gint response)
//{
//  city pcity = null;
//  tile ptile = g_object_get_data(G_OBJECT(w), "tile");
//
//  switch (response) {
//  case 1:
//    center_tile_mapcanvas(ptile);
//    break;
//  case 2:
//    pcity = map_get_city(ptile);
//
//    if (center_when_popup_city) {
//      center_tile_mapcanvas(ptile);
//    }
//
//    if (pcity) {
//      popup_city_dialog(pcity, 0);
//    }
//    break;
//  }
//  gtk_widget_destroy(w);
//}
//
///**************************************************************************
//  Popup a dialog to display information about an event that has a
//  specific location.  The user should be given the option to goto that
//  location.
//**************************************************************************/
//void popup_notify_goto_dialog(final String headline, final String lines,
//			      tile ptile)
//{
//  GtkWidget *shell, *label, *goto_command, *popcity_command;
//  
//  shell = gtk_dialog_new_with_buttons(headline,
//        null,
//        0,
//        null);
//  setup_dialog(shell, toplevel);
//  gtk_dialog_set_default_response(GTK_DIALOG(shell), GTK_RESPONSE_CLOSE);
//  gtk_window_set_position(GTK_WINDOW(shell), GTK_WIN_POS_CENTER_ON_PARENT);
//
//  label = gtk_label_new(lines);
//  gtk_container_add(GTK_CONTAINER(GTK_DIALOG(shell).vbox), label);
//  gtk_widget_show(label);
//  
//  goto_command = gtk_stockbutton_new(GTK_STOCK_JUMP_TO,
//	"_Goto location");
//  gtk_dialog_add_action_widget(GTK_DIALOG(shell), goto_command, 1);
//  gtk_widget_show(goto_command);
//
//  popcity_command = gtk_stockbutton_new(GTK_STOCK_ZOOM_IN,
//	"_Popup City");
//  gtk_dialog_add_action_widget(GTK_DIALOG(shell), popcity_command, 2);
//  gtk_widget_show(popcity_command);
//
//  gtk_dialog_add_button(GTK_DIALOG(shell), GTK_STOCK_CLOSE,
//			GTK_RESPONSE_CLOSE);
//
//  if (!ptile) {
//    gtk_widget_set_sensitive(goto_command, false);
//    gtk_widget_set_sensitive(popcity_command, false);
//  } else {
//    city pcity;
//
//    pcity = map_get_city(ptile);
//    gtk_widget_set_sensitive(popcity_command,
//      (pcity && City.city_owner(pcity) == Game.game.player_ptr));
//  }
//
//  g_object_set_data(G_OBJECT(shell), "tile", ptile);
//
//  g_signal_connect(shell, "response", G_CALLBACK(notify_goto_response), null);
//  gtk_widget_show(shell);
//}
//
//
///****************************************************************
//...
//*****************************************************************/
//static void bribe_response(GtkWidget *w, gint response)
//{
//  if (response == GTK_RESPONSE_YES) {
//    request_diplomat_action(DIPLOMAT_BRIBE, diplomat_id,
//			    diplomat_target_id, 0);
//  }
//  gtk_widget_destroy(w);
//}
//
///****************************************************************
//...  Ask the server how much the bribe is
//*****************************************************************/
//static void diplomat_bribe_callback(GtkWidget *w, gpointer data)
//{
//  if (find_unit_by_id(diplomat_id) && find_unit_by_id(diplomat_target_id)) {
//    dsend_packet_unit_bribe_inq(&aconnection, diplomat_target_id);
//  }
//  gtk_widget_destroy(diplomat_dialog);
//}
//
///****************************************************************
//...
//*****************************************************************/
//void popup_bribe_dialog(unit punit)
//{
//  GtkWidget *shell;
//
//  if (unit_flag(punit, F_UNBRIBABLE)) {
//    shell = popup_message_dialog(GTK_WINDOW(toplevel), "Ooops...",
//                                 "This unit cannot be bribed!",
//                                 GTK_STOCK_OK, null, null, null);
//    gtk_window_present(GTK_WINDOW(shell));
//    return;
//  } else if (Game.game.player_ptr.economic.gold >= punit.bribe_cost) {
//    shell = gtk_message_dialog_new(null, 0,
//      GTK_MESSAGE_QUESTION, GTK_BUTTONS_YES_NO,
//      "Bribe unit for %d gold?\nTreasury contains %d gold.",
//      punit.bribe_cost, Game.game.player_ptr.economic.gold);
//    gtk_window_set_title(GTK_WINDOW(shell), "Bribe Enemy Unit");
//    setup_dialog(shell, toplevel);
//  } else {
//    shell = gtk_message_dialog_new(null,
//      0,
//      GTK_MESSAGE_INFO, GTK_BUTTONS_CLOSE,
//      "Bribing the unit costs %d gold.\nTreasury contains %d gold.",
//      punit.bribe_cost, Game.game.player_ptr.economic.gold);
//    gtk_window_set_title(GTK_WINDOW(shell), "Traitors Demand Too Much!");
//    setup_dialog(shell, toplevel);
//  }
//  gtk_window_present(GTK_WINDOW(shell));
//  
//  g_signal_connect(shell, "response", G_CALLBACK(bribe_response), null);
//}
//
///****************************************************************
//...
//*****************************************************************/
//static void diplomat_sabotage_callback(GtkWidget *w, gpointer data)
//{
//  if(find_unit_by_id(diplomat_id) && 
//     find_city_by_id(diplomat_target_id)) { 
//    request_diplomat_action(DIPLOMAT_SABOTAGE, diplomat_id,
//			    diplomat_target_id, -1);
//  }
//  gtk_widget_destroy(diplomat_dialog);
//}
//
///****************************************************************
//...
//*****************************************************************/
//static void diplomat_investigate_callback(GtkWidget *w, gpointer data)
//{
//  if(find_unit_by_id(diplomat_id) && 
//     (find_city_by_id(diplomat_target_id))) { 
//    request_diplomat_action(DIPLOMAT_INVESTIGATE, diplomat_id,
//			    diplomat_target_id, 0);
//  }
//  gtk_widget_destroy(diplomat_dialog);
//}
//
///****************************************************************
//...
//*****************************************************************/
//static void spy_sabotage_unit_callback(GtkWidget *w, gpointer data)
//{
//  request_diplomat_action(SPY_SABOTAGE_UNIT, diplomat_id,
//			  diplomat_target_id, 0);
//  gtk_widget_destroy(diplomat_dialog);
//}
//
///****************************************************************
//...
//*****************************************************************/
//static void diplomat_embassy_callback(GtkWidget *w, gpointer data)
//{
//  if(find_unit_by_id(diplomat_id) && 
//     (find_city_by_id(diplomat_target_id))) { 
//    request_diplomat_action(DIPLOMAT_EMBASSY, diplomat_id,
//			    diplomat_target_id, 0);
//  }
//  gtk_widget_destroy(diplomat_dialog);
//}
//
///****************************************************************
//...
//*****************************************************************/
//static void spy_poison_callback(GtkWidget *w, gpointer data)
//{
//  if(find_unit_by_id(diplomat_id) &&
//     (find_city_by_id(diplomat_target_id))) {
//    request_diplomat_action(SPY_POISON, diplomat_id, diplomat_target_id, 0);
//  }
//  gtk_widget_destroy(diplomat_dialog);
//}
//
///****************************************************************
//...
//*****************************************************************/
//static void diplomat_steal_callback(GtkWidget *w, gpointer data)
//{
//  if(find_unit_by_id(diplomat_id) && 
//     find_city_by_id(diplomat_target_id)) { 
//    request_diplomat_action(DIPLOMAT_STEAL, diplomat_id,
//			    diplomat_target_id, 0);
//  }
//  gtk_widget_destroy(diplomat_dialog);
//}
//
///****************************************************************
//...
//*****************************************************************/
//static void spy_advances_response(GtkWidget *w, gint response, gpointer data)
//{
//  if (response == GTK_RESPONSE_ACCEPT && steal_advance > 0) {
//    if (find_unit_by_id(diplomat_id) && 
//        find_city_by_id(diplomat_target_id)) { 
//      request_diplomat_action(DIPLOMAT_STEAL, diplomat_id,
//			      diplomat_target_id, steal_advance);
//    }
//  }
//  gtk_widget_destroy(spy_tech_shell);
//  spy_tech_shell = null;
//}
//
///****************************************************************
//...
//*****************************************************************/
//static void spy_advances_callback(GtkTreeSelection *select, gpointer data)
//{
//  GtkTreeModel *model;
//  GtkTreeIter it;
//
//  if (gtk_tree_selection_get_selected(select, &model, &it)) {
//    gtk_tree_model_get(model, &it, 1, &steal_advance, -1);
//    
//    gtk_dialog_set_response_sensitive(GTK_DIALOG(spy_tech_shell),
//      GTK_RESPONSE_ACCEPT, true);
//  } else {
//    steal_advance = 0;
//	  
//    gtk_dialog_set_response_sensitive(GTK_DIALOG(spy_tech_shell),
//      GTK_RESPONSE_ACCEPT, false);
//  }
//}
//
///****************************************************************
//...
//*****************************************************************/
//static void create_advances_list(player pplayer,
//				 player pvictim)
//{  
//  GtkWidget *sw, *label, *vbox, *view;
//  int i;
//  GtkListStore *store;
//  GtkCellRenderer *rend;
//  GtkTreeViewColumn *col;
//
//  spy_tech_shell = gtk_dialog_new_with_buttons("Steal Technology",
//    null,
//    0,
//    GTK_STOCK_CANCEL,
//    GTK_RESPONSE_CANCEL,
//    "_Steal",
//    GTK_RESPONSE_ACCEPT,
//    null);
//  setup_dialog(spy_tech_shell, toplevel);
//  gtk_window_set_position(GTK_WINDOW(spy_tech_shell), GTK_WIN_POS_MOUSE);
//
//  gtk_dialog_set_default_response(GTK_DIALOG(spy_tech_shell),
//				  GTK_RESPONSE_ACCEPT);
//
//  label = gtk_frame_new("Select Advance to Steal");
//  gtk_container_add(GTK_CONTAINER(GTK_DIALOG(spy_tech_shell).vbox), label);
//
//  vbox = gtk_vbox_new(false, 6);
//  gtk_container_add(GTK_CONTAINER(label), vbox);
//      
//  store = gtk_list_store_new(2, G_TYPE_STRING, G_TYPE_INT);
//
//  view = gtk_tree_view_new_with_model(GTK_TREE_MODEL(store));
//  g_object_unref(store);
//  gtk_tree_view_set_headers_visible(GTK_TREE_VIEW(view), false);
//
//  rend = gtk_cell_renderer_text_new();
//  col = gtk_tree_view_column_new_with_attributes(null, rend,
//						 "text", 0, null);
//  gtk_tree_view_append_column(GTK_TREE_VIEW(view), col);
//
//  label = g_object_new(GTK_TYPE_LABEL,
//    "use-underline", true,
//    "mnemonic-widget", view,
//    "label", "_Advances:",
//    "xalign", 0.0,
//    "yalign", 0.5,
//    null);
//  gtk_container_add(GTK_CONTAINER(vbox), label);
//  
//  sw = gtk_scrolled_window_new(null, null);
//  gtk_scrolled_window_set_shadow_type(GTK_SCROLLED_WINDOW(sw),
//				      GTK_SHADOW_ETCHED_IN);
//  gtk_container_add(GTK_CONTAINER(sw), view);
//
//  gtk_scrolled_window_set_policy(GTK_SCROLLED_WINDOW(sw),
//    GTK_POLICY_NEVER, GTK_POLICY_ALWAYS);
//  gtk_widget_set_size_request(sw, -1, 200);
//  
//  gtk_container_add(GTK_CONTAINER(vbox), sw);
//
//  /* Now populate the list */
//  if (pvictim) { /* you don't want to know what lag can do -- Syela */
//    GtkTreeIter it;
//    GValue value = { 0, };
//
//    for(i=A_FIRST; i<Game.game.num_tech_types; i++) {
//      if(get_invention(pvictim, i)==TECH_KNOWN && 
//	 (get_invention(pplayer, i)==TECH_UNKNOWN || 
//	  get_invention(pplayer, i)==TECH_REACHABLE)) {
//	gtk_list_store_append(store, &it);
//
//	g_value_init(&value, G_TYPE_STRING);
//	g_value_set_static_string(&value,
//				  get_tech_name(Game.game.player_ptr, i));
//	gtk_list_store_set_value(store, &it, 0, &value);
//	g_value_unset(&value);
//	gtk_list_store_set(store, &it, 1, i, -1);
//      }
//    }
//
//    gtk_list_store_append(store, &it);
//
//    g_value_init(&value, G_TYPE_STRING);
//    g_value_set_static_string(&value, "At Spy's Discretion");
//    gtk_list_store_set_value(store, &it, 0, &value);
//    g_value_unset(&value);
//    gtk_list_store_set(store, &it, 1, Game.game.num_tech_types, -1);
//  }
//
//  gtk_dialog_set_response_sensitive(GTK_DIALOG(spy_tech_shell),
//    GTK_RESPONSE_ACCEPT, false);
//  
//  gtk_widget_show_all(GTK_DIALOG(spy_tech_shell).vbox);
//
//  g_signal_connect(gtk_tree_view_get_selection(GTK_TREE_VIEW(view)), "changed",
//		   G_CALLBACK(spy_advances_callback), null);
//  g_signal_connect(spy_tech_shell, "response",
//		   G_CALLBACK(spy_advances_response), null);
//  
//  steal_advance = 0;
//
//  gtk_tree_view_focus(GTK_TREE_VIEW(view));
//}
//
///****************************************************************
//...
//*****************************************************************/
//static void spy_improvements_response(GtkWidget *w, gint response, gpointer data)
//{
//  if (response == GTK_RESPONSE_ACCEPT && sabotage_improvement > -2) {
//    if (find_unit_by_id(diplomat_id) && 
//        find_city_by_id(diplomat_target_id)) { 
//      request_diplomat_action(DIPLOMAT_SABOTAGE, diplomat_id,
//			      diplomat_target_id, sabotage_improvement + 1);
//    }
//  }
//  gtk_widget_destroy(spy_sabotage_shell);
//  spy_sabotage_shell = null;
//}
//
///****************************************************************
//...
//*****************************************************************/
//static void spy_improvements_callback(GtkTreeSelection *select, gpointer data)
//{
//  GtkTreeModel *model;
//  GtkTreeIter it;
//
//  if (gtk_tree_selection_get_selected(select, &model, &it)) {
//    gtk_tree_model_get(model, &it, 1, &sabotage_improvement, -1);
//    
//    gtk_dialog_set_response_sensitive(GTK_DIALOG(spy_sabotage_shell),
//      GTK_RESPONSE_ACCEPT, true);
//  } else {
//    sabotage_improvement = -2;
//	  
//    gtk_dialog_set_response_sensitive(GTK_DIALOG(spy_sabotage_shell),
//      GTK_RESPONSE_ACCEPT, false);
//  }
//}
//
///****************************************************************
//...
//*****************************************************************/
//static void create_improvements_list(player pplayer,
//				     city pcity)
//{  
//  GtkWidget *sw, *label, *vbox, *view;
//  GtkListStore *store;
//  GtkCellRenderer *rend;
//  GtkTreeViewColumn *col;
//  GtkTreeIter it;
//  
//  spy_sabotage_shell = gtk_dialog_new_with_buttons("Sabotage Improvements",
//    null,
//    0,
//    GTK_STOCK_CANCEL,
//    GTK_RESPONSE_CANCEL,
//    "_Sabotage", 
//    GTK_RESPONSE_ACCEPT,
//    null);
//  setup_dialog(spy_sabotage_shell, toplevel);
//  gtk_window_set_position(GTK_WINDOW(spy_sabotage_shell), GTK_WIN_POS_MOUSE);
//
//  gtk_dialog_set_default_response(GTK_DIALOG(spy_sabotage_shell),
//				  GTK_RESPONSE_ACCEPT);
//
//  label = gtk_frame_new("Select Improvement to Sabotage");
//  gtk_container_add(GTK_CONTAINER(GTK_DIALOG(spy_sabotage_shell).vbox), label);
//
//  vbox = gtk_vbox_new(false, 6);
//  gtk_container_add(GTK_CONTAINER(label), vbox);
//      
//  store = gtk_list_store_new(2, G_TYPE_STRING, G_TYPE_INT);
//
//  view = gtk_tree_view_new_with_model(GTK_TREE_MODEL(store));
//  g_object_unref(store);
//  gtk_tree_view_set_headers_visible(GTK_TREE_VIEW(view), false);
//
//  rend = gtk_cell_renderer_text_new();
//  col = gtk_tree_view_column_new_with_attributes(null, rend,
//						 "text", 0, null);
//  gtk_tree_view_append_column(GTK_TREE_VIEW(view), col);
//
//  label = g_object_new(GTK_TYPE_LABEL,
//    "use-underline", true,
//    "mnemonic-widget", view,
//    "label", "_Improvements:",
//    "xalign", 0.0,
//    "yalign", 0.5,
//    null);
//  gtk_container_add(GTK_CONTAINER(vbox), label);
//  
//  sw = gtk_scrolled_window_new(null, null);
//  gtk_scrolled_window_set_shadow_type(GTK_SCROLLED_WINDOW(sw),
//				      GTK_SHADOW_ETCHED_IN);
//  gtk_container_add(GTK_CONTAINER(sw), view);
//
//  gtk_scrolled_window_set_policy(GTK_SCROLLED_WINDOW(sw),
//    GTK_POLICY_NEVER, GTK_POLICY_ALWAYS);
//  gtk_widget_set_size_request(sw, -1, 200);
//  
//  gtk_container_add(GTK_CONTAINER(vbox), sw);
//
//  /* Now populate the list */
//  gtk_list_store_append(store, &it);
//  gtk_list_store_set(store, &it, 0, "City Production", 1, -1, -1);
//
//  built_impr_iterate(pcity, i) {
//    if (get_improvement_type(i).sabotage > 0) {
//      gtk_list_store_append(store, &it);
//      gtk_list_store_set(store, &it, 0, get_impr_name_ex(pcity, i), 1, i, -1);
//    }  
//  } built_impr_iterate_end;
//
//  gtk_list_store_append(store, &it);
//  gtk_list_store_set(store, &it, 0, "At Spy's Discretion", 1, B_LAST, -1);
//
//  gtk_dialog_set_response_sensitive(GTK_DIALOG(spy_sabotage_shell),
//    GTK_RESPONSE_ACCEPT, false);
//  
//  gtk_widget_show_all(GTK_DIALOG(spy_sabotage_shell).vbox);
//
//  g_signal_connect(gtk_tree_view_get_selection(GTK_TREE_VIEW(view)), "changed",
//		   G_CALLBACK(spy_improvements_callback), null);
//  g_signal_connect(spy_sabotage_shell, "response",
//		   G_CALLBACK(spy_improvements_response), null);
//
//  sabotage_improvement = -2;
//	  
//  gtk_tree_view_focus(GTK_TREE_VIEW(view));
//}
//
///****************************************************************
//...
//*****************************************************************/
//static void spy_steal_popup(GtkWidget *w, gpointer data)
//{
//  city pvcity = find_city_by_id(diplomat_target_id);
//  player pvictim = null;
//
//  if(pvcity)
//    pvictim = City.city_owner(pvcity);
//
///* it is concievable that pvcity will not be found, because something
//has happened to the city during latency.  Therefore we must initialize
//pvictim to null and account for !pvictim in create_advances_list. -- Syela */
//  
//  if(!spy_tech_shell){
//    create_advances_list(Game.game.player_ptr, pvictim);
//    gtk_window_present(GTK_WINDOW(spy_tech_shell));
//  }
//  gtk_widget_destroy(diplomat_dialog);
//}
//
///****************************************************************
// Requests up-to-date list of improvements, the return of
// which will trigger the popup_sabotage_dialog() function.
//*****************************************************************/
//static void spy_request_sabotage_list(GtkWidget *w, gpointer data)
//{
//  if(find_unit_by_id(diplomat_id) &&
//     (find_city_by_id(diplomat_target_id))) {
//    request_diplomat_action(SPY_GET_SABOTAGE_LIST, diplomat_id,
//			    diplomat_target_id, 0);
//  }
//  gtk_widget_destroy(diplomat_dialog);
//}
//
///****************************************************************
// Pops-up the Spy sabotage dialog, upon return of list of
// available improvements requested by the above function.
//*****************************************************************/
//void popup_sabotage_dialog(city pcity)
//{
//  if(!spy_sabotage_shell){
//    create_improvements_list(Game.game.player_ptr, pcity);
//    gtk_window_present(GTK_WINDOW(spy_sabotage_shell));
//  }
//}
//
///****************************************************************
//...  Ask the server how much the revolt is going to cost us
//*****************************************************************/
//static void diplomat_incite_callback(GtkWidget *w, gpointer data)
//{
//  if (find_unit_by_id(diplomat_id) && find_city_by_id(diplomat_target_id)) {
//    dsend_packet_city_incite_inq(&aconnection, diplomat_target_id);
//  }
//  gtk_widget_destroy(diplomat_dialog);
//}
//
///****************************************************************
//...
//*****************************************************************/
//static void incite_response(GtkWidget *w, gint response)
//{
//  if (response == GTK_RESPONSE_YES) {
//    request_diplomat_action(DIPLOMAT_INCITE, diplomat_id,
//			    diplomat_target_id, 0);
//  }
//  gtk_widget_destroy(w);
//}
//
///****************************************************************
//Popup the yes/no dialog for inciting, since we know the cost now
//*****************************************************************/
//void popup_incite_dialog(city pcity)
//{
//  GtkWidget *shell;
//  
//  if (pcity.incite_revolt_cost == INCITE_IMPOSSIBLE_COST) {
//    shell = gtk_message_dialog_new(null,
//      0,
//      GTK_MESSAGE_INFO, GTK_BUTTONS_CLOSE,
//      "You can't incite a revolt in %s.",
//      pcity.name);
//    gtk_window_set_title(GTK_WINDOW(shell), "City can't be incited!");
//  setup_dialog(shell, toplevel);
//  } else if (Game.game.player_ptr.economic.gold >= pcity.incite_revolt_cost) {
//    shell = gtk_message_dialog_new(null,
//      0,
//      GTK_MESSAGE_QUESTION, GTK_BUTTONS_YES_NO,
//      "Incite a revolt for %d gold?\nTreasury contains %d gold.",
//      pcity.incite_revolt_cost, Game.game.player_ptr.economic.gold);
//    gtk_window_set_title(GTK_WINDOW(shell), "Incite a Revolt!");
//    setup_dialog(shell, toplevel);
//  } else {
//    shell = gtk_message_dialog_new(null,
//      0,
//      GTK_MESSAGE_INFO, GTK_BUTTONS_CLOSE,
//      "Inciting a revolt costs %d gold.\nTreasury contains %d gold.",
//      pcity.incite_revolt_cost, Game.game.player_ptr.economic.gold);
//    gtk_window_set_title(GTK_WINDOW(shell), "Traitors Demand Too Much!");
//    setup_dialog(shell, toplevel);
//  }
//  gtk_window_present(GTK_WINDOW(shell));
//  
//  g_signal_connect(shell, "response", G_CALLBACK(incite_response), null);
//}
//
//
///****************************************************************
//  Callback from diplomat/spy dialog for "keep moving".
//  (This should only occur when entering allied city.)
//*****************************************************************/
//static void diplomat_keep_moving_callback(GtkWidget *w, gpointer data)
//{
//  unit punit;
//  city pcity;
//  
//  if( (punit=find_unit_by_id(diplomat_id))
//      && (pcity=find_city_by_id(diplomat_target_id))
//      && !Map.same_pos(punit.tile, pcity.tile)) {
//    request_diplomat_action(DIPLOMAT_MOVE, diplomat_id,
//			    diplomat_target_id, 0);
//  }
//  gtk_widget_destroy(diplomat_dialog);
//}
//
///****************************************************************
//...
//*****************************************************************/
//static void diplomat_destroy_callback(GtkWidget *w, gpointer data)
//{
//  diplomat_dialog = null;
//  process_diplomat_arrival(null, 0);
//}
//
//
///****************************************************************
//...
//*****************************************************************/
//static void diplomat_cancel_callback(GtkWidget *w, gpointer data)
//{
//  gtk_widget_destroy(diplomat_dialog);
//}
//
///****************************************************************
//...
//*****************************************************************/
//void popup_diplomat_dialog(unit punit, tile dest_tile)
//{
//  city pcity;
//  unit ptunit;
//  GtkWidget *shl;
//  char buf[128];
//
//  diplomat_id = punit.id;
//
//  if ((pcity = map_get_city(dest_tile))) {
//    /* Spy/Diplomat acting against a city */
//
//    diplomat_target_id = pcity.id;
//    buf = util.my_snprintf(
//		"Your %s has arrived at %s.\nWhat is your command?",
//		unit_name(punit.type), pcity.name);
//
//    if (!unit_flag(punit, F_SPY)){
//      shl = popup_message_dialog(GTK_WINDOW(toplevel),
//	" Choose Your Diplomat's Strategy", buf,
//	"Establish _Embassy", diplomat_embassy_callback, null,
//	"_Investigate City", diplomat_investigate_callback, null,
//	"_Sabotage City", diplomat_sabotage_callback, null,
//	"Steal _Technology", diplomat_steal_callback, null,
//	"Incite a _Revolt", diplomat_incite_callback, null,
//	"_Keep moving", diplomat_keep_moving_callback, null,
//	GTK_STOCK_CANCEL, diplomat_cancel_callback, null,
//	null);
//
//      if (!diplomat_can_do_action(punit, DIPLOMAT_EMBASSY, dest_tile))
//	message_dialog_button_set_sensitive(shl, 0, false);
//      if (!diplomat_can_do_action(punit, DIPLOMAT_INVESTIGATE, dest_tile))
//	message_dialog_button_set_sensitive(shl, 1, false);
//      if (!diplomat_can_do_action(punit, DIPLOMAT_SABOTAGE, dest_tile))
//	message_dialog_button_set_sensitive(shl, 2, false);
//      if (!diplomat_can_do_action(punit, DIPLOMAT_STEAL, dest_tile))
//	message_dialog_button_set_sensitive(shl, 3, false);
//      if (!diplomat_can_do_action(punit, DIPLOMAT_INCITE, dest_tile))
//	message_dialog_button_set_sensitive(shl, 4, false);
//      if (!diplomat_can_do_action(punit, DIPLOMAT_MOVE, dest_tile))
//	message_dialog_button_set_sensitive(shl, 5, false);
//    } else {
//       shl = popup_message_dialog(GTK_WINDOW(toplevel),
//	"Choose Your Spy's Strategy", buf,
//	"Establish _Embassy", diplomat_embassy_callback, null,
//	"_Investigate City", diplomat_investigate_callback, null,
//	"_Poison City", spy_poison_callback, null,
//	"Industrial _Sabotage", spy_request_sabotage_list, null,
//	"Steal _Technology", spy_steal_popup, null,
//	"Incite a _Revolt", diplomat_incite_callback, null,
//	"_Keep moving", diplomat_keep_moving_callback, null,
//	GTK_STOCK_CANCEL, diplomat_cancel_callback, null,
//	null);
//
//      if (!diplomat_can_do_action(punit, DIPLOMAT_EMBASSY, dest_tile))
//	message_dialog_button_set_sensitive(shl, 0, false);
//      if (!diplomat_can_do_action(punit, DIPLOMAT_INVESTIGATE, dest_tile))
//	message_dialog_button_set_sensitive(shl, 1, false);
//      if (!diplomat_can_do_action(punit, SPY_POISON, dest_tile))
//	message_dialog_button_set_sensitive(shl, 2, false);
//      if (!diplomat_can_do_action(punit, DIPLOMAT_SABOTAGE, dest_tile))
//	message_dialog_button_set_sensitive(shl, 3, false);
//      if (!diplomat_can_do_action(punit, DIPLOMAT_STEAL, dest_tile))
//	message_dialog_button_set_sensitive(shl, 4, false);
//      if (!diplomat_can_do_action(punit, DIPLOMAT_INCITE, dest_tile))
//	message_dialog_button_set_sensitive(shl, 5, false);
//      if (!diplomat_can_do_action(punit, DIPLOMAT_MOVE, dest_tile))
//	message_dialog_button_set_sensitive(shl, 6, false);
//     }
//
//    diplomat_dialog = shl;
//
//    message_dialog_set_hide(shl, true);
//    g_signal_connect(shl, "destroy",
//		     G_CALLBACK(diplomat_destroy_callback), null);
//    g_signal_connect(shl, "delete_event",
//		     G_CALLBACK(diplomat_cancel_callback), null);
//  } else { 
//    if ((ptunit = unit_list_get(&dest_tile.units, 0))){
//      /* Spy/Diplomat acting against a unit */ 
//       
//      diplomat_target_id = ptunit.id;
// 
//      shl = popup_message_dialog(GTK_WINDOW(toplevel),
//	"Subvert Enemy Unit",
//	(!unit_flag(punit, F_SPY))?
//	"Sir, the diplomat is waiting for your command":
//	"Sir, the spy is waiting for your command",
//	"_Bribe Enemy Unit", diplomat_bribe_callback, null,
//	"_Sabotage Enemy Unit", spy_sabotage_unit_callback, null,
//	GTK_STOCK_CANCEL, diplomat_cancel_callback, null,
//	null);
//
//      if (!diplomat_can_do_action(punit, DIPLOMAT_BRIBE, dest_tile)) {
//	message_dialog_button_set_sensitive(shl, 0, false);
//      }
//      if (!diplomat_can_do_action(punit, SPY_SABOTAGE_UNIT, dest_tile)) {
//	message_dialog_button_set_sensitive(shl, 1, false);
//      }
//
//      diplomat_dialog = shl;
//
//      message_dialog_set_hide(shl, true);
//      g_signal_connect(shl, "destroy",
//		       G_CALLBACK(diplomat_destroy_callback), null);
//      g_signal_connect(shl, "delete_event",
//		       G_CALLBACK(diplomat_cancel_callback), null);
//    }
//  }
//}
//
///****************************************************************
//...
//*****************************************************************/
//boolean diplomat_dialog_is_open()
//{
//  return diplomat_dialog != null;
//}
//
///****************************************************************
//...
//*****************************************************************/
//static void caravan_establish_trade_callback(GtkWidget *w, gpointer data)
//{
//  dsend_packet_unit_establish_trade(&aconnection, caravan_unit_id);
//}
//
//
///****************************************************************
//...
//*****************************************************************/
//static void caravan_help_build_wonder_callback(GtkWidget *w, gpointer data)
//{
//  dsend_packet_unit_help_build_wonder(&aconnection, caravan_unit_id);
//}
//
//
///****************************************************************
//...
//*****************************************************************/
//static void caravan_destroy_callback(GtkWidget *w, gpointer data)
//{
//  caravan_dialog = null;
//  process_caravan_arrival(null);
//}
//
//
//
///****************************************************************
//...
//*****************************************************************/
//void popup_caravan_dialog(unit punit,
//			  city phomecity, city pdestcity)
//{
//  char buf[128];
//  boolean can_establish, can_trade;
//  
//  buf = util.my_snprintf(
//	      "Your caravan from %s reaches the city of %s.\nWhat now?",
//	      phomecity.name, pdestcity.name);
//  
//  caravan_city_id=pdestcity.id; /* callbacks need these */
//  caravan_unit_id=punit.id;
//  
//  can_trade = can_cities_trade(phomecity, pdestcity);
//  can_establish = can_trade
//  		  && can_establish_trade_route(phomecity, pdestcity);
//  
//  caravan_dialog = popup_message_dialog(GTK_WINDOW(toplevel),
//    "Your Caravan Has Arrived", 
//    buf,
//    (can_establish ? "Establish _Traderoute" :
//    "Enter Marketplace"),caravan_establish_trade_callback, null,
//    "Help build _Wonder",caravan_help_build_wonder_callback, null,
//    "_Keep moving", null, null,
//    null);
//
//  g_signal_connect(caravan_dialog, "destroy",
//		   G_CALLBACK(caravan_destroy_callback), null);
//  
//  if (!can_trade) {
//    message_dialog_button_set_sensitive(caravan_dialog, 0, false);
//  }
//  
//  if (!unit_can_help_build_wonder(punit, pdestcity)) {
//    message_dialog_button_set_sensitive(caravan_dialog, 1, false);
//  }
//}
//
///****************************************************************
//...
//*****************************************************************/
//boolean caravan_dialog_is_open()
//{
//  return caravan_dialog != null;
//}
//
///****************************************************************
//...
//*****************************************************************/
//static void revolution_response(GtkWidget *w, gint response, gpointer data)
//{
//  int government = GPOINTER_TO_INT(data);
//
//  if (response == GTK_RESPONSE_YES) {
//    if (government == -1) {
//      start_revolution();
//    } else {
//      set_government_choice(government);
//    }
//  }
//  if (w) {
//    gtk_widget_destroy(w);
//  }
//}
//
///****************************************************************
//...
//*****************************************************************/
//void popup_revolution_dialog(int government)
//{
//  static GtkWidget *shell = null;
//
//  if (Game.game.player_ptr.revolution_finishes == -1) {
//    if (!shell) {
//      shell = gtk_message_dialog_new(null,
//	  0,
//	  GTK_MESSAGE_WARNING,
//	  GTK_BUTTONS_YES_NO,
//	  "You say you wanna revolution?");
//      gtk_window_set_title(GTK_WINDOW(shell), "Revolution!");
//      setup_dialog(shell, toplevel);
//
//      g_signal_connect(shell, "destroy",
//	  G_CALLBACK(gtk_widget_destroyed), &shell);
//    }
//    g_signal_connect(shell, "response",
//	G_CALLBACK(revolution_response), GINT_TO_POINTER(government));
//
//    gtk_window_present(GTK_WINDOW(shell));
//  } else {
//    revolution_response(shell, GTK_RESPONSE_YES, GINT_TO_POINTER(government));
//  }
//}
//
//
///****************************************************************
//...
//*****************************************************************/
//static void pillage_callback(GtkWidget *w, gpointer data)
//{
//  if (data) {
//    unit punit = find_unit_by_id(unit_to_use_to_pillage);
//    if (punit) {
//      request_new_unit_activity_targeted(punit,
//					 ACTIVITY_PILLAGE,
//					 GPOINTER_TO_INT(data));
//    }
//  }
//}
//
///****************************************************************
//...
//*****************************************************************/
//static void pillage_destroy_callback(GtkWidget *w, gpointer data)
//{
//  is_showing_pillage_dialog = false;
//}
//
///****************************************************************
//...
//*****************************************************************/
//void popup_pillage_dialog(unit punit,
//			  enum int may_pillage)
//{
//  GtkWidget *shl;
//
//  if (!is_showing_pillage_dialog) {
//    is_showing_pillage_dialog = true;
//    unit_to_use_to_pillage = punit.id;
//
//    shl = message_dialog_start(GTK_WINDOW(toplevel),
//			       "What To Pillage",
//			       "Select what to pillage:");
//
//    while (may_pillage != S_NO_SPECIAL) {
//      enum int what = get_preferred_pillage(may_pillage);
//
//      message_dialog_add(shl, map_get_infrastructure_text(what),
//			 G_CALLBACK(pillage_callback), GINT_TO_POINTER(what));
//
//      may_pillage &= (~(what | map_get_infrastructure_prerequisite(what)));
//    }
//
//    message_dialog_add(shl, GTK_STOCK_CANCEL, 0, 0);
//
//    message_dialog_end(shl);
//
//    g_signal_connect(shl, "destroy", G_CALLBACK(pillage_destroy_callback),
//		     null);   
//  }
//}
//
///****************************************************************
//...
//*****************************************************************/
//void message_dialog_button_set_sensitive(GtkWidget *shl, int button,
//					 gboolean state)
//{
//  char button_name[512];
//  GtkWidget *b;
//
//  button_name = util.my_snprintf( "button%d", button);
//
//  b = g_object_get_data(G_OBJECT(shl), button_name);
//  gtk_widget_set_sensitive(b, state);
//}
//
///****************************************************************
//...
//*****************************************************************/
//GtkWidget *message_dialog_start(GtkWindow *parent, final gchar *name,
//				final gchar *text)
//{
//  GtkWidget *dshell, *dlabel, *vbox, *bbox;
//
//  dshell = gtk_window_new(GTK_WINDOW_TOPLEVEL);
//  setup_dialog(dshell, toplevel);
//  gtk_window_set_position (GTK_WINDOW(dshell), GTK_WIN_POS_MOUSE);
//
//  gtk_window_set_title(GTK_WINDOW(dshell), name);
//
//  gtk_window_set_transient_for(GTK_WINDOW(dshell), parent);
//  gtk_window_set_destroy_with_parent(GTK_WINDOW(dshell), true);
//
//  vbox = gtk_vbox_new(false, 5);
//  gtk_container_add(GTK_CONTAINER(dshell),vbox);
//
//  gtk_container_set_border_width(GTK_CONTAINER(vbox), 5);
//
//  dlabel = gtk_label_new(text);
//  gtk_container_add(GTK_CONTAINER(vbox), dlabel);
//
//  bbox = gtk_vbutton_box_new();
//  gtk_box_set_spacing(GTK_BOX(bbox), 2);
//  gtk_container_add(GTK_CONTAINER(vbox), bbox);
//  
//  g_object_set_data(G_OBJECT(dshell), "bbox", bbox);
//  g_object_set_data(G_OBJECT(dshell), "nbuttons", GINT_TO_POINTER(0));
//  g_object_set_data(G_OBJECT(dshell), "hide", GINT_TO_POINTER(false));
//  
//  gtk_widget_show(vbox);
//  gtk_widget_show(dlabel);
//  
//  return dshell;
//}
//
///****************************************************************
//...
//*****************************************************************/
//static void message_dialog_clicked(GtkWidget *w, gpointer data)
//{
//  if (g_object_get_data(G_OBJECT(data), "hide")) {
//    gtk_widget_hide(GTK_WIDGET(data));
//  } else {
//    gtk_widget_destroy(GTK_WIDGET(data));
//  }
//}
//
///****************************************************************
//...
//*****************************************************************/
//void message_dialog_add(GtkWidget *dshell, final gchar *label,
//			GCallback handler, gpointer data)
//{
//  GtkWidget *button, *bbox;
//  char name[512];
//  int nbuttons;
//
//  bbox = g_object_get_data(G_OBJECT(dshell), "bbox");
//  nbuttons = GPOINTER_TO_INT(g_object_get_data(G_OBJECT(dshell), "nbuttons"));
//  g_object_set_data(G_OBJECT(dshell), "nbuttons", GINT_TO_POINTER(nbuttons+1));
//
//  name = util.my_snprintf( "button%d", nbuttons);
//
//  button = gtk_button_new_from_stock(label);
//  gtk_container_add(GTK_CONTAINER(bbox), button);
//  g_object_set_data(G_OBJECT(dshell), name, button);
//
//  if (handler) {
//    g_signal_connect(button, "clicked", handler, data);
//  }
//
//  g_signal_connect_after(button, "clicked",
//			 G_CALLBACK(message_dialog_clicked), dshell);
//}
//
///****************************************************************
//...
//*****************************************************************/
//void message_dialog_end(GtkWidget *dshell)
//{
//  GtkWidget *bbox;
//  
//  bbox = g_object_get_data(G_OBJECT(dshell), "bbox");
//  
//  gtk_widget_show_all(bbox);
//  gtk_widget_show(dshell);  
//}
//
///****************************************************************
//...
//*****************************************************************/
//void message_dialog_set_hide(GtkWidget *dshell, gboolean setting)
//{
//  g_object_set_data(G_OBJECT(dshell), "hide", GINT_TO_POINTER(setting));
//}
//
///****************************************************************
//...
//*****************************************************************/
//GtkWidget *popup_message_dialog(GtkWindow *parent, final gchar *dialogname,
//				final gchar *text, ...)
//{
//  GtkWidget *dshell;
//  va_list args;
//  gchar *name;
//  int i;
//
//  dshell = message_dialog_start(parent, dialogname, text);
//  
//  i = 0;
//  va_start(args, text);
//
//  while ((name = va_arg(args, gchar *))) {
//    GCallback handler;
//    gpointer data;
//
//    handler = va_arg(args, GCallback);
//    data = va_arg(args, gpointer);
//
//    message_dialog_add(dshell, name, handler, data);
//  }
//
//  va_end(args);
//
//  message_dialog_end(dshell);
//
//  return dshell;
//}
//
///**************************************************************************
//...
//**************************************************************************/
//static void unit_select_row_activated(GtkTreeView *view, GtkTreePath *path)
//{
//  GtkTreeIter it;
//  unit punit;
//  gint id;
//
//  gtk_tree_model_get_iter(GTK_TREE_MODEL(unit_select_store), &it, path);
//  gtk_tree_model_get(GTK_TREE_MODEL(unit_select_store), &it, 0, &id, -1);
// 
//  if ((punit = player_find_unit_by_id(Game.game.player_ptr, id))) {
//    set_unit_focus(punit);
//  }
//
//  gtk_widget_destroy(unit_select_dialog_shell);
//}
//
///**************************************************************************
//...
//**************************************************************************/
//static void unit_select_append(unit punit, GtkTreeIter *it,
//    			       GtkTreeIter *parent)
//{
//  GdkPixbuf *pix;
//  unit_type ptype = punit.unit_type();
//
//  pix = gdk_pixbuf_new(GDK_COLORSPACE_RGB, true, 8,
//      UNIT_TILE_WIDTH, UNIT_TILE_HEIGHT);
//
//  {
//    struct canvas canvas_store;
//
//    canvas_store.type = CANVAS_PIXBUF;
//    canvas_store.v.pixbuf = pix;   
//
//    gdk_pixbuf_fill(pix, 0x00000000);
//    put_unit(punit, &canvas_store, 0, 0);
//  }
//
//  gtk_tree_store_append(unit_select_store, it, parent);
//  gtk_tree_store_set(unit_select_store, it,
//      0, punit.id,
//      1, pix,
//      2, _(ptype.name),
//      -1);
//  g_object_unref(pix);
//
//  if (punit == get_unit_in_focus()) {
//    unit_select_path =
//      gtk_tree_model_get_path(GTK_TREE_MODEL(unit_select_store), it);
//  }
//}
//
///**************************************************************************
//...
//**************************************************************************/
//static void unit_select_recurse(int root_id, GtkTreeIter *it_root)
//{
//  for (unit pleaf : unit_select_ptile.units.data) {
//    GtkTreeIter it_leaf;
//
//    if (pleaf.transported_by == root_id) {
//      unit_select_append(pleaf, &it_leaf, it_root);
//      if (pleaf.occupy > 0) {
//	unit_select_recurse(pleaf.id, &it_leaf);
//      }
//    }
//  } }
//}
//
///**************************************************************************
//...
//**************************************************************************/
//static void refresh_unit_select_dialog()
//{
//  if (unit_select_dialog_shell) {
//    gtk_tree_store_clear(unit_select_store);
//
//    unit_select_recurse(-1, null);
//    gtk_tree_view_expand_all(GTK_TREE_VIEW(unit_select_view));
//
//    if (unit_select_path) {
//      gtk_tree_view_set_cursor(GTK_TREE_VIEW(unit_select_view),
//	  unit_select_path, null, false);
//      gtk_tree_path_free(unit_select_path);
//      unit_select_path = null;
//    }
//  }
//}
//
///****************************************************************
//...
//*****************************************************************/
//static void unit_select_destroy_callback(GtkObject *object, gpointer data)
//{
//  unit_select_dialog_shell = null;
//}
//
///****************************************************************
//...
//*****************************************************************/
//static void unit_select_cmd_callback(GtkWidget *w, gint rid, gpointer data)
//{
//  tile ptile = unit_select_ptile;
//
//  switch (rid) {
//  case SELECT_UNIT_READY:
//    {
//      unit pmyunit = null;
//
//      for (unit punit : ptile.units.data) {
//        if (Game.game.player_idx == punit.owner) {
//          pmyunit = punit;
//
//          /* Activate this unit. */
//	  punit.focus_status = FOCUS_AVAIL;
//	  if (unit_has_orders(punit)) {
//	    request_orders_cleared(punit);
//	  }
//	  if (punit.activity != unit_activity.ACTIVITY_IDLE || punit.ai.control) {
//	    punit.ai.control = false;
//	    request_new_unit_activity(punit, unit_activity.ACTIVITY_IDLE);
//	  }
//        }
//      } }
//
//      if (pmyunit) {
//        /* Put the focus on one of the activated units. */
//        set_unit_focus(pmyunit);
//      }
//    }
//    break;
//
//  case SELECT_UNIT_SENTRY:
//    {
//      for (unit punit : ptile.units.data) {
//        if (Game.game.player_idx == punit.owner) {
//          if ((punit.activity == unit_activity.ACTIVITY_IDLE) &&
//              !punit.ai.control &&
//              can_unit_do_activity(punit, ACTIVITY_SENTRY)) {
//            request_new_unit_activity(punit, ACTIVITY_SENTRY);
//          }
//        }
//      } }
//    }
//    break;
//
//  default:
//    break;
//  }
//  
//  gtk_widget_destroy(unit_select_dialog_shell);
//}
//
///****************************************************************
//...
//*****************************************************************/
//public static final int NUM_UNIT_SELECT_COLUMNS = 2;
//
//void popup_unit_select_dialog(tile ptile)
//{
//  if (!unit_select_dialog_shell) {
//    GtkTreeStore *store;
//    GtkWidget *shell, *view, *sw, *hbox;
//    GtkWidget *ready_cmd, *sentry_cmd, *close_cmd;
//
//    static final String titles[NUM_UNIT_SELECT_COLUMNS] = {
//      N"Unit",
//      N"Name"
//    };
//    static boolean titles_done;
//
//    GType types[NUM_UNIT_SELECT_COLUMNS+1] = {
//      G_TYPE_INT,
//      GDK_TYPE_PIXBUF,
//      G_TYPE_STRING
//    };
//    int i;
//
//
//    shell = gtk_dialog_new_with_buttons("Unit selection",
//      null,
//      0,
//      null);
//    unit_select_dialog_shell = shell;
//    setup_dialog(shell, toplevel);
//    g_signal_connect(shell, "destroy",
//      G_CALLBACK(unit_select_destroy_callback), null);
//    gtk_window_set_position(GTK_WINDOW(shell), GTK_WIN_POS_MOUSE);
//    g_signal_connect(shell, "response",
//      G_CALLBACK(unit_select_cmd_callback), null);
//
//    hbox = gtk_hbox_new(false, 0);
//    gtk_container_add(GTK_CONTAINER(GTK_DIALOG(shell).vbox), hbox);
//
//    intl_slist(ARRAY_SIZE(titles), titles, &titles_done);
//
//    store = gtk_tree_store_newv(ARRAY_SIZE(types), types);
//    unit_select_store = store;
//
//    view = gtk_tree_view_new_with_model(GTK_TREE_MODEL(store));
//    unit_select_view = view;
//    g_object_unref(store);
// 
//    for (i = 1; i < ARRAY_SIZE(types); i++) {
//      GtkTreeViewColumn *column;
//      GtkCellRenderer *render;
//
//      column = gtk_tree_view_column_new();
//      gtk_tree_view_column_set_title(column, titles[i-1]);
//
//      switch (types[i]) {
//	case G_TYPE_STRING:
//	  render = gtk_cell_renderer_text_new();
//	  gtk_tree_view_column_pack_start(column, render, true);
//	  gtk_tree_view_column_set_attributes(column, render, "text", i, null);
//	  break;
//	default:
//	  render = gtk_cell_renderer_pixbuf_new();
//	  gtk_tree_view_column_pack_start(column, render, false);
//	  gtk_tree_view_column_set_attributes(column, render,
//	      "pixbuf", i, null);
//	  break;
//      }
//      gtk_tree_view_append_column(GTK_TREE_VIEW(view), column);
//    }
//
//    g_signal_connect(view, "row_activated",
//	G_CALLBACK(unit_select_row_activated), null);
//
//
//    sw = gtk_scrolled_window_new(null, null);
//    gtk_widget_set_size_request(sw, -1, 300);
//    gtk_scrolled_window_set_shadow_type(GTK_SCROLLED_WINDOW(sw),
//	GTK_SHADOW_ETCHED_IN);
//    gtk_scrolled_window_set_policy(GTK_SCROLLED_WINDOW(sw),
//	GTK_POLICY_NEVER, GTK_POLICY_AUTOMATIC);
//    gtk_container_add(GTK_CONTAINER(sw), view);
//    gtk_box_pack_start(GTK_BOX(hbox), sw, true, true, 0);
//
//
//    ready_cmd =
//    gtk_dialog_add_button(GTK_DIALOG(shell),
//      "_Ready all", SELECT_UNIT_READY);
//
//    gtk_button_box_set_child_secondary(
//      GTK_BUTTON_BOX(GTK_DIALOG(shell).action_area),
//      ready_cmd, true);
//
//    sentry_cmd =
//    gtk_dialog_add_button(GTK_DIALOG(shell),
//      "_Sentry idle", SELECT_UNIT_SENTRY);
//
//    gtk_button_box_set_child_secondary(
//      GTK_BUTTON_BOX(GTK_DIALOG(shell).action_area),
//      sentry_cmd, true);
//
//    close_cmd =
//    gtk_dialog_add_button(GTK_DIALOG(shell),
//      GTK_STOCK_CLOSE, GTK_RESPONSE_CLOSE);
//
//    gtk_dialog_set_default_response(GTK_DIALOG(shell), GTK_RESPONSE_CLOSE);
//
//    gtk_widget_show_all(GTK_DIALOG(shell).vbox);
//    gtk_widget_show_all(GTK_DIALOG(shell).action_area);
//  }
//
//  unit_select_ptile = ptile;
//  refresh_unit_select_dialog();
//
//  gtk_window_present(GTK_WINDOW(unit_select_dialog_shell));
//}
//
///****************************************************************
//...
//*****************************************************************/
//static void create_races_dialog()
//{
//  GtkWidget *shell;
//  GtkWidget *cmd;
//  GtkWidget *vbox, *hbox, *table;
//  GtkWidget *frame, *label, *combo;
//  GtkWidget *notebook, *text;
//  
//  GtkWidget *list, *sw;
//  GtkTreeSelection *select;
//  
//  GtkListStore *store;
//  GtkCellRenderer *render;
//  GtkTreeViewColumn *column;
//  int i;
//  
//  shell =
//    gtk_dialog_new_with_buttons("What Nation Will You Be?",
//				null,
//				GTK_DIALOG_MODAL,
//				"_Disconnect",
//				GTK_RESPONSE_CANCEL,
//				GTK_STOCK_OK,
//				GTK_RESPONSE_ACCEPT,
//				null);
//  races_shell = shell;
//  setup_dialog(shell, toplevel);
//
//  gtk_window_set_position(GTK_WINDOW(shell), GTK_WIN_POS_CENTER_ON_PARENT);
//  gtk_window_set_default_size(GTK_WINDOW(shell), -1, 310);
//
//  cmd = gtk_dialog_add_button(GTK_DIALOG(shell),
//      GTK_STOCK_QUIT, GTK_RESPONSE_CLOSE);
//  gtk_button_box_set_child_secondary(
//      GTK_BUTTON_BOX(GTK_DIALOG(shell).action_area), cmd, true);
//  gtk_widget_show(cmd);
//
//  frame = gtk_frame_new("Select a nation");
//  gtk_container_add(GTK_CONTAINER(GTK_DIALOG(shell).vbox), frame);
//
//  hbox = gtk_hbox_new(false, 18);
//  gtk_container_set_border_width(GTK_CONTAINER(hbox), 3);
//  gtk_container_add(GTK_CONTAINER(frame), hbox);
//
//  vbox = gtk_vbox_new(false, 2);
//  gtk_container_add(GTK_CONTAINER(hbox), vbox);
//
//  /* Nation list. */
//  store = gtk_list_store_new(5, G_TYPE_INT, G_TYPE_BOOLEAN,
//      GDK_TYPE_PIXBUF, G_TYPE_STRING, G_TYPE_STRING);
//  gtk_tree_sortable_set_sort_column_id(GTK_TREE_SORTABLE(store),
//      3, GTK_SORT_ASCENDING);
//
//  list = gtk_tree_view_new_with_model(GTK_TREE_MODEL(store));
//  races_nation_list = list;
//  g_object_unref(store);
//
//  select = gtk_tree_view_get_selection(GTK_TREE_VIEW(list));
//  g_signal_connect(select, "changed", G_CALLBACK(races_nation_callback), null);
//  gtk_tree_selection_set_select_function(select, races_selection_func,
//      null, null);
//  label = g_object_new(GTK_TYPE_LABEL,
//      "use-underline", true,
//      "mnemonic-widget", list,
//      "label", "_Nations:",
//      "xalign", 0.0,
//      "yalign", 0.5,
//      null);
//  gtk_box_pack_start(GTK_BOX(vbox), label, false, false, 0);
//
//  sw = gtk_scrolled_window_new(null, null);
//  gtk_scrolled_window_set_shadow_type(GTK_SCROLLED_WINDOW(sw),
//      GTK_SHADOW_ETCHED_IN);
//  gtk_scrolled_window_set_policy(GTK_SCROLLED_WINDOW(sw),
//      GTK_POLICY_NEVER, GTK_POLICY_ALWAYS);
//  gtk_container_add(GTK_CONTAINER(sw), list);
//  gtk_box_pack_start(GTK_BOX(vbox), sw, true, true, 0);
//
//  render = gtk_cell_renderer_pixbuf_new();
//  column = gtk_tree_view_column_new_with_attributes("Flag", render,
//      "pixbuf", 2, null);
//  gtk_tree_view_append_column(GTK_TREE_VIEW(list), column);
//  render = gtk_cell_renderer_text_new();
//  column = gtk_tree_view_column_new_with_attributes("Nation", render,
//      "text", 3, "strikethrough", 1, null);
//  gtk_tree_view_column_set_sort_column_id(column, 3);
//  gtk_tree_view_append_column(GTK_TREE_VIEW(list), column);
//  render = gtk_cell_renderer_text_new();
//  g_object_set(render, "style", PANGO_STYLE_ITALIC, null);
//  column = gtk_tree_view_column_new_with_attributes("Class", render,
//      "text", 4, "strikethrough", 1, null);
//  gtk_tree_view_column_set_sort_column_id(column, 4);
//  gtk_tree_view_append_column(GTK_TREE_VIEW(list), column);
//
//  /* Populate nation list store. */
//  for (i = 0; i < Game.game.playable_nation_count; i++) {
//    nation_type nation;
//    SPRITE *s;
//    GdkPixbuf *img;
//    GtkTreeIter it;
//    GValue value = { 0, };
//
//    nation = get_nation_by_idx(i);
//
//    gtk_list_store_append(store, &it);
//
//    s = crop_blankspace(nation.flag_sprite);
//    img = gdk_pixbuf_new_from_sprite(s);
//    free_sprite(s);
//    gtk_list_store_set(store, &it, 0, i, 1, false, 2, img, -1);
//    g_object_unref(img);
//
//    g_value_init(&value, G_TYPE_STRING);
//    g_value_set_static_string(&value, nation.name);
//    gtk_list_store_set_value(store, &it, 3, &value);
//    g_value_unset(&value);
//
//    g_value_init(&value, G_TYPE_STRING);
//    g_value_set_static_string(&value, Q_(nation.class));
//    gtk_list_store_set_value(store, &it, 4, &value);
//    g_value_unset(&value);
//  }
//
//
//  /* Right side. */
//  notebook = gtk_notebook_new();
//  gtk_notebook_set_tab_pos(GTK_NOTEBOOK(notebook), GTK_POS_BOTTOM);
//  gtk_container_add(GTK_CONTAINER(hbox), notebook);
//
//  /* Properties pane. */
//  label = gtk_label_new_with_mnemonic("_Properties");
//
//  vbox = gtk_vbox_new(false, 6);
//  gtk_container_set_border_width(GTK_CONTAINER(vbox), 6);
//  gtk_notebook_append_page(GTK_NOTEBOOK(notebook), vbox, label);
//
//  table = gtk_table_new(3, 4, false); 
//  gtk_table_set_row_spacings(GTK_TABLE(table), 2);
//  gtk_table_set_col_spacing(GTK_TABLE(table), 0, 12);
//  gtk_table_set_col_spacing(GTK_TABLE(table), 1, 12);
//  gtk_box_pack_start(GTK_BOX(vbox), table, false, false, 0);
//
//  /* Leader. */ 
//  combo = gtk_combo_new();
//  races_leader = combo;
//  gtk_combo_disable_activate(GTK_COMBO(combo));
//  gtk_combo_set_value_in_list(GTK_COMBO(combo), false, false);
//  label = g_object_new(GTK_TYPE_LABEL,
//      "use-underline", true,
//      "mnemonic-widget", GTK_COMBO(combo).entry,
//      "label", "_Leader:",
//      "xalign", 0.0,
//      "yalign", 0.5,
//      null);
//  gtk_table_attach_defaults(GTK_TABLE(table), label, 0, 1, 0, 2);
//  gtk_table_attach_defaults(GTK_TABLE(table), combo, 1, 3, 0, 1);
//
//  cmd = gtk_radio_button_new_with_mnemonic(null, "_Female");
//  races_sex[0] = cmd;
//  gtk_table_attach_defaults(GTK_TABLE(table), cmd, 1, 2, 1, 2);
//
//  cmd = gtk_radio_button_new_with_mnemonic_from_widget(GTK_RADIO_BUTTON(cmd),
//      "_Male");
//  races_sex[1] = cmd;
//  gtk_table_attach_defaults(GTK_TABLE(table), cmd, 2, 3, 1, 2);
//
//  /* City style. */
//  store = gtk_list_store_new(3, G_TYPE_INT,
//      GDK_TYPE_PIXBUF, G_TYPE_STRING);
//
//  list = gtk_tree_view_new_with_model(GTK_TREE_MODEL(store));
//  races_city_style_list = list;
//  g_object_unref(store);
//  gtk_tree_view_set_headers_visible(GTK_TREE_VIEW(list), false);
//  g_signal_connect(gtk_tree_view_get_selection(GTK_TREE_VIEW(list)), "changed",
//      G_CALLBACK(races_city_style_callback), null);
//
//  sw = gtk_scrolled_window_new(null, null);
//  gtk_scrolled_window_set_shadow_type(GTK_SCROLLED_WINDOW(sw),
//      GTK_SHADOW_ETCHED_IN);
//  gtk_scrolled_window_set_policy(GTK_SCROLLED_WINDOW(sw),
//      GTK_POLICY_NEVER, GTK_POLICY_NEVER);
//  gtk_container_add(GTK_CONTAINER(sw), list);
//  gtk_table_attach(GTK_TABLE(table), sw, 1, 3, 2, 4,
//      GTK_EXPAND|GTK_FILL, GTK_EXPAND|GTK_FILL, 0, 0);
//
//  label = g_object_new(GTK_TYPE_LABEL,
//      "use-underline", true,
//      "mnemonic-widget", list,
//      "label", "_City Styles:",
//      "xalign", 0.0,
//      "yalign", 0.5,
//      null);
//  gtk_table_attach_defaults(GTK_TABLE(table), label, 0, 1, 2, 3);
//
//  render = gtk_cell_renderer_pixbuf_new();
//  column = gtk_tree_view_column_new_with_attributes(null, render,
//      "pixbuf", 1, null);
//  gtk_tree_view_append_column(GTK_TREE_VIEW(list), column);
//  render = gtk_cell_renderer_text_new();
//  column = gtk_tree_view_column_new_with_attributes(null, render,
//      "text", 2, null);
//  gtk_tree_view_append_column(GTK_TREE_VIEW(list), column);
//
//  gtk_table_set_row_spacing(GTK_TABLE(table), 1, 12);
//
//  /* Populate city style store. */
//  for (i = 0; i < Game.game.styles_count; i++) {
//    GdkPixbuf *img;
//    SPRITE *s;
//    int last;
//    GtkTreeIter it;
//
//    if (city_styles[i].techreq != A_NONE) {
//      continue;
//    }
//
//    gtk_list_store_append(store, &it);
//
//    last = city_styles[i].tiles_num-1;
//
//    s = crop_blankspace(sprites.city.tile[i][last]);
//    img = gdk_pixbuf_new_from_sprite(s);
//    free_sprite(s);
//    gtk_list_store_set(store, &it, 0, i, 1, img, 2,
//                       get_city_style_name(i), -1);
//    g_object_unref(img);
//  }
//
//  /* Legend pane. */
//  label = gtk_label_new_with_mnemonic("L_egend");
//
//  vbox = gtk_vbox_new(false, 6);
//  gtk_container_set_border_width(GTK_CONTAINER(vbox), 6);
//  gtk_notebook_append_page(GTK_NOTEBOOK(notebook), vbox, label);
//
//  text = gtk_text_view_new();
//  races_text = gtk_text_view_get_buffer(GTK_TEXT_VIEW(text));
//  gtk_text_view_set_wrap_mode(GTK_TEXT_VIEW(text), GTK_WRAP_WORD);
//  gtk_text_view_set_editable(GTK_TEXT_VIEW(text), false);
//  gtk_text_view_set_cursor_visible(GTK_TEXT_VIEW(text), false);
//  gtk_text_view_set_left_margin(GTK_TEXT_VIEW(text), 6);
//  gtk_text_view_set_right_margin(GTK_TEXT_VIEW(text), 6);
//
//  gtk_box_pack_start(GTK_BOX(vbox), text, true, true, 0);
//
//  /* Signals. */
//  g_signal_connect(shell, "destroy",
//      G_CALLBACK(races_destroy_callback), null);
//  g_signal_connect(shell, "response",
//      G_CALLBACK(races_response), null);
//
//  g_signal_connect(GTK_COMBO(races_leader).list, "select_child",
//      G_CALLBACK(races_leader_callback), null);
//
//  g_signal_connect(races_sex[0], "toggled",
//      G_CALLBACK(races_sex_callback), GINT_TO_POINTER(0));
//  g_signal_connect(races_sex[1], "toggled",
//      G_CALLBACK(races_sex_callback), GINT_TO_POINTER(1));
//
//  /* Init. */
//  selected_nation = -1;
//
//  /* Finish up. */
//  gtk_dialog_set_default_response(GTK_DIALOG(shell), GTK_RESPONSE_ACCEPT);
//
//  gtk_widget_show_all(GTK_DIALOG(shell).vbox);
//}
//
///****************************************************************
//  popup the dialog 10% inside the main-window 
// *****************************************************************/
//void popup_races_dialog()
//{
//  create_races_dialog();
//  gtk_window_present(GTK_WINDOW(races_shell));
//
//  select_random_race();
//}
//
///****************************************************************
//  ...
// *****************************************************************/
//void popdown_races_dialog()
//{
//  if (races_shell) {
//    gtk_widget_destroy(races_shell);
//  }
//
//  /* We're probably starting a new Game.game, maybe with a new ruleset.
//     So we warn the worklist dialog. */
//  blank_max_unit_size();
//}
//
//
///****************************************************************
//  ...
// *****************************************************************/
//static void races_destroy_callback(GtkWidget *w, gpointer data)
//{
//  g_list_free(races_leader_list);
//  races_leader_list = null;
//
//  races_shell = null;
//}
//
///****************************************************************
//  ...
// *****************************************************************/
//static gint cmp_func(gfinalpointer ap, gfinalpointer bp)
//{
//  return strcmp((final String)ap, (final String)bp);
//}
//
///****************************************************************
//  Selects a leader.
//  Updates the gui elements.
// *****************************************************************/
//static void select_random_leader()
//{
//  leader leaders;
//  int i, nleaders;
//  GList *items = null;
//  GtkWidget *text, *list;
//  gchar *name;
//  boolean unique;
//
//  text = GTK_COMBO(races_leader).entry;
//  list = GTK_COMBO(races_leader).list;
//  name = gtk_editable_get_chars(GTK_EDITABLE(text), 0, -1);
//
//  if (name[0] == '\0'
//      || g_list_find_custom(races_leader_list, name, cmp_func)) {
//    unique = false;
//  } else {
//    unique = true;
//  }
//
//  leaders = get_nation_leaders(selected_nation, &nleaders);
//  for (i = 0; i < nleaders; i++) {
//    items = g_list_append(items, leaders[i].name);
//  }
//
//  /* Populate combo box with minimum signal noise. */
//  g_signal_handlers_block_by_func(list, races_leader_callback, null);
//  gtk_combo_set_popdown_strings(GTK_COMBO(races_leader), items);
//  gtk_entry_set_text(GTK_ENTRY(text), "");
//  g_signal_handlers_unblock_by_func(list, races_leader_callback, null);
//
//  g_list_free(races_leader_list);
//  races_leader_list = items;
//
//  if (unique) {
//    gtk_entry_set_text(GTK_ENTRY(text), name);
//  } else {
//    i = Rand.myrand(nleaders);
//    gtk_entry_set_text(GTK_ENTRY(text), g_list_nth_data(items, i));
//  }
//
//  g_free(name);
//}
//
///****************************************************************
//  Selectes a random race and the appropriate city style.
//  Updates the gui elements and the selected_* variables.
// *****************************************************************/
//static void select_random_race()
//{
//  GtkTreeModel *model;
//
//  model = gtk_tree_view_get_model(GTK_TREE_VIEW(races_nation_list));
//
//  /* This has a possibility of infinite loop in case
//   * Game.game.playable_nation_count < Game.game.nplayers. */
//  while (true) {
//    GtkTreePath *path;
//    GtkTreeIter it;
//    int nation;
//
//    nation = Rand.myrand(Game.game.playable_nation_count);
//
//    path = gtk_tree_path_new();
//    gtk_tree_path_append_index(path, nation);
//
//    if (gtk_tree_model_get_iter(model, &it, path)) {
//      gboolean chosen;
//
//      gtk_tree_model_get(model, &it, 1, &chosen, -1);
//
//      if (!chosen) {
//	gtk_tree_view_set_cursor(GTK_TREE_VIEW(races_nation_list), path,
//	    null, false);
//	gtk_tree_path_free(path);
//	return;
//      }
//    }
//
//    gtk_tree_path_free(path);
//  }
//}
//
///**************************************************************************
//  ...
// **************************************************************************/
//void races_toggles_set_sensitive(boolean *nations_used)
//{
//  GtkTreeModel *model;
//  GtkTreeIter it;
//  GtkTreePath *path;
//  gboolean chosen;
//
//  if (!races_shell) {
//    return;
//  }
//
//  model = gtk_tree_view_get_model(GTK_TREE_VIEW(races_nation_list));
//
//  if (gtk_tree_model_get_iter_first(model, &it)) {
//    do {
//      int nation;
//
//      gtk_tree_model_get(model, &it, 0, &nation, -1);
//
//      chosen = nations_used[nation];
//      gtk_list_store_set(GTK_LIST_STORE(model), &it, 1, chosen, -1);
//
//    } while (gtk_tree_model_iter_next(model, &it));
//  }
//
//  gtk_tree_view_get_cursor(GTK_TREE_VIEW(races_nation_list), &path, null);
//  if (path) {
//    gtk_tree_model_get_iter(model, &it, path);
//    gtk_tree_model_get(model, &it, 1, &chosen, -1);
//
//    if (chosen) {
//      select_random_race();
//    }
//
//    gtk_tree_path_free(path);
//  }
//}
//
///**************************************************************************
//  ...
// **************************************************************************/
//static void races_nation_callback(GtkTreeSelection *select, gpointer data)
//{
//  GtkTreeModel *model;
//  GtkTreeIter it;
//
//  if (gtk_tree_selection_get_selected(select, &model, &it)) {
//    gboolean chosen;
//    nation_type nation;
//
//    gtk_tree_model_get(model, &it, 0, &selected_nation, 1, &chosen, -1);
//    nation = get_nation_by_idx(selected_nation);
//
//    if (chosen) {
//      select_random_race();
//    } else {
//      int cs, i, j;
//      GtkTreePath *path;
//      
//      select_random_leader();
//      
//      /* Select city style for chosen nation. */
//      cs = get_nation_city_style(selected_nation);
//      for (i = 0, j = 0; i < Game.game.styles_count; i++) {
//        if (city_styles[i].techreq != A_NONE) {
//	  continue;
//	}
//
//	if (i < cs) {
//	  j++;
//	} else {
//	  break;
//	}
//      }
//
//      path = gtk_tree_path_new();
//      gtk_tree_path_append_index(path, j);
//      gtk_tree_view_set_cursor(GTK_TREE_VIEW(races_city_style_list), path,
//			       null, false);
//      gtk_tree_path_free(path);
//
//      /* Update nation legend text. */
//      gtk_text_buffer_set_text(races_text, nation.legend , -1);
//    }
//
//  } else {
//    selected_nation = -1;
//  }
//}
//
///**************************************************************************
//...
//**************************************************************************/
//static void races_leader_callback()
//{
//  final gchar *name;
//
//  name = gtk_entry_get_text(GTK_ENTRY(GTK_COMBO(races_leader).entry));
//
//  if (check_nation_leader_name(selected_nation, name)) {
//    selected_sex = get_nation_leader_sex(selected_nation, name);
//    gtk_toggle_button_set_active(GTK_TOGGLE_BUTTON(races_sex[selected_sex]),
//				 true);
//  }
//}
//
///**************************************************************************
//...
//**************************************************************************/
//static void races_sex_callback(GtkWidget *w, gpointer data)
//{
//  selected_sex = GPOINTER_TO_INT(data);
//}
//
///**************************************************************************
//...
//**************************************************************************/
//static gboolean races_selection_func(GtkTreeSelection *select,
//				     GtkTreeModel *model, GtkTreePath *path,
//				     gboolean selected, gpointer data)
//{
//  GtkTreeIter it;
//  gboolean chosen;
//
//  gtk_tree_model_get_iter(model, &it, path);
//  gtk_tree_model_get(model, &it, 1, &chosen, -1);
//  return (!chosen || selected);
//}
//
///**************************************************************************
//...
//**************************************************************************/
//static void races_city_style_callback(GtkTreeSelection *select, gpointer data)
//{
//  GtkTreeModel *model;
//  GtkTreeIter it;
//
//  if (gtk_tree_selection_get_selected(select, &model, &it)) {
//    gtk_tree_model_get(model, &it, 0, &selected_city_style, -1);
//  } else {
//    selected_city_style = -1;
//  }
//}
//
///**************************************************************************
//...
//**************************************************************************/
//static void races_response(GtkWidget *w, gint response, gpointer data)
//{
//  if (response == GTK_RESPONSE_ACCEPT) {
//    final String s;
//
//    if (selected_nation == -1) {
//      append_output_window("You must select a nation.");
//      return;
//    }
//
//    if (selected_sex == -1) {
//      append_output_window("You must select your sex.");
//      return;
//    }
//
//    if (selected_city_style == -1) {
//      append_output_window("You must select your city style.");
//      return;
//    }
//
//    s = gtk_entry_get_text(GTK_ENTRY(GTK_COMBO(races_leader).entry));
//
//    /* Perform a minimum of sanity test on the name. */
//    /* This could call is_allowed_player_name if it were available. */
//    if (s.length() == 0) {
//      append_output_window("You must type a legal name.");
//      return;
//    }
//
//    dsend_packet_nation_select_req(&aconnection, selected_nation,
//				   selected_sex, s, selected_city_style);
//  } else if (response == GTK_RESPONSE_CLOSE) {
//    exit(EXIT_SUCCESS);
//
//  } else {
//    popdown_races_dialog();
//    disconnect_from_server();
//    client_kill_server(false);
//  }
//}
//
//
///**************************************************************************
//  Adjust tax rates from main window
//**************************************************************************/
//gboolean taxrates_callback(GtkWidget * w, GdkEventButton * ev, gpointer data)
//{
//  common_taxrates_callback((size_t) data);
//  return true;
//}
//
///**************************************************************************
//...
//**************************************************************************/
//static void nuke_children(gpointer data, gpointer user_data)
//{
//  if (data != user_data) {
//    if (GTK_IS_WINDOW(data) && GTK_WINDOW(data).type == GTK_WINDOW_TOPLEVEL) {
//      gtk_widget_destroy(data);
//    }
//  }
//}
//

//  This function is called when the client disconnects or the Game.game is
//  over.  It should close all dialog windows for that Game.game.
//***********************************************************************/
//void popdown_all_game_dialogs()
//{
//  GList *res;
//
//  gui_dialog_destroy_all();
//
//
//  res = gtk_window_list_toplevels();
//
//  g_list_foreach(res, (GFunc)g_object_ref, null);
//  g_list_foreach(res, nuke_children, toplevel);
//  g_list_foreach(res, (GFunc)g_object_unref, null);
//
//  g_list_free(res);
//}
//
}