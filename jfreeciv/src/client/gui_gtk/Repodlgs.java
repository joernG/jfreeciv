package client.gui_gtk;

public class Repodlgs{

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
//#include <gdk/gdkkeysyms.h>
//#include <gtk/gtk.h>
//
//#include "fcintl.h"
//#include "Game.game.h"
//#include "government.h"
//#include "packets.h"
//#include "shared.h"
//#include "support.h"
//
//#include "civclient.h"
//#include "climisc.h"
//#include "clinet.h"
//#include "repodlgs_common.h"
//#include "control.h"
//#include "text.h"
//
//#include "cityrep.h"
//#include "dialogs.h"
//#include "gui_main.h"
//#include "gui_stuff.h"
//#include "helpdlg.h"
//#include "optiondlg.h"
//
//
//#include "repodlgs.h"
//
///******************************************************************/
//
//static void create_science_dialog(boolean make_modal);
//static void science_close_callback(GtkWidget * widget, gpointer data);
//static void science_help_callback(GtkWidget * w, gint row, gint column);
//static void science_change_callback(GtkWidget * widget, gpointer data);
//static void science_goal_callback(GtkWidget * widget, gpointer data);
//
///******************************************************************/
//static GtkWidget *science_dialog_shell = null;
//static GtkWidget *science_label;
//static GtkWidget *science_current_label, *science_goal_label;
//static GtkWidget *science_change_menu_button, *science_goal_menu_button;
//static GtkWidget *science_list[4], *science_help_toggle;
//static int science_dialog_shell_is_modal;
//static GtkWidget *popupmenu, *goalmenu;
//
///******************************************************************/
//static void create_economy_report_dialog(boolean make_modal);
//static void economy_close_callback(GtkWidget * w, gpointer data);
//static void economy_selloff_callback(GtkWidget * w, gpointer data);
//static void economy_list_callback(GtkWidget * w, gint row, gint column);
//static void economy_list_ucallback(GtkWidget * w, gint row, gint column);
//
//struct economy_row {
//  int is_impr;
//  int type;
//};
//static struct economy_row economy_row_type[unittype.U_LAST + Improvement.B_LAST];
//
//static GtkWidget *economy_dialog_shell = null;
//static GtkWidget *economy_label2;
//static GtkWidget *economy_list;
//static GtkWidget *sellall_command, *sellobsolete_command;
//static int economy_dialog_shell_is_modal;
//
///******************************************************************/
//static void create_activeunits_report_dialog(boolean make_modal);
//static void activeunits_close_callback(GtkWidget * w, gpointer data);
//static void activeunits_upgrade_callback(GtkWidget * w, gpointer data);
//static void activeunits_refresh_callback(GtkWidget * w, gpointer data);
//static void activeunits_list_callback(GtkWidget * w, gint row, gint column);
//static void activeunits_list_ucallback(GtkWidget * w, gint row, gint column);
//static int activeunits_type[unittype.U_LAST];
//static GtkWidget *activeunits_dialog_shell = null;
//static GtkWidget *activeunits_label2;
//static GtkWidget *activeunits_list;
//static GtkWidget *upgrade_command;
//
//static int activeunits_dialog_shell_is_modal;
//
///******************************************************************/
//static void create_endgame_report(packet_endgame_report packet);
//static void endgame_destroy_callback(GtkObject *object, gpointer data);
//
//static GtkWidget *endgame_report_shell;
//static GtkWidget *scores_list;
//static GtkWidget *close_endgame_command;
//static GtkWidget *sw;
//
//public static final int NUM_SCORE_COLS = 14;                
///******************************************************************/
//
///******************************************************************
//...
//*******************************************************************/
//void update_report_dialogs()
//{
//  if(is_report_dialogs_frozen()) return;
//  activeunits_report_dialog_update();
//  economy_report_dialog_update();
//  city_report_dialog_update(); 
//  science_dialog_update();
//}
//
//
///****************************************************************
//...
//*****************************************************************/
//void popup_science_dialog(boolean make_modal)
//{
//  if(!science_dialog_shell) {
//    science_dialog_shell_is_modal=make_modal;
//    
//    if(make_modal)
//      gtk_widget_set_sensitive(top_vbox, false);
//    
//    create_science_dialog(make_modal);
//    gtk_set_relative_position(toplevel, science_dialog_shell, 10, 10);
//
//    gtk_widget_show( science_dialog_shell );
//  }
//}
//
///****************************************************************
// Closes the science dialog.
//*****************************************************************/
//void popdown_science_dialog()
//{
//  if (science_dialog_shell) {
//    if (science_dialog_shell_is_modal) {
//      gtk_widget_set_sensitive(top_vbox, true);
//    }
//    gtk_widget_destroy(science_dialog_shell);
//    science_dialog_shell = null;
//  }
//}
//
///****************************************************************
//...
//*****************************************************************/
//void create_science_dialog(boolean make_modal)
//{
//  GtkWidget *close_command;
//  GtkWidget *frame, *hbox, *w;
//  GtkAccelGroup *accel=gtk_accel_group_new();
//  int i;
//  char text[512];
//
//  science_dialog_shell = gtk_dialog_new();
//  gtk_signal_connect( GTK_OBJECT(science_dialog_shell),"delete_event",
//        GTK_SIGNAL_FUNC(science_close_callback),null );
//  gtk_accel_group_attach(accel, GTK_OBJECT(science_dialog_shell));
//
//  gtk_window_set_title (GTK_WINDOW(science_dialog_shell), "Science");
//
//  text = util.my_snprintf( "no text set yet");
//  science_label = gtk_label_new(text);
//
//  gtk_box_pack_start( GTK_BOX( GTK_DIALOG(science_dialog_shell).vbox ),
//        science_label, false, false, 0 );
//
//  frame = gtk_frame_new("Researching");
//  gtk_box_pack_start( GTK_BOX( GTK_DIALOG(science_dialog_shell).vbox ),
//        frame, false, false, 0 );
//
//  hbox = gtk_hbox_new( true, 5 );
//  gtk_container_add(GTK_CONTAINER(frame), hbox);
//
//  science_change_menu_button = gtk_option_menu_new();
//  gtk_box_pack_start( GTK_BOX( hbox ), science_change_menu_button,true, true, 0 );
//
//  popupmenu = gtk_menu_new();
//  gtk_widget_show_all(popupmenu);
//
//  science_current_label=gtk_progress_bar_new();
//  gtk_progress_bar_set_bar_style(GTK_PROGRESS_BAR(science_current_label),
//				 GTK_PROGRESS_CONTINUOUS);
//  gtk_progress_set_show_text(GTK_PROGRESS(science_current_label), true);
//
//  gtk_box_pack_start( GTK_BOX( hbox ), science_current_label,true, false, 0 );
//  gtk_widget_set_usize(science_current_label, 0, 25);
//
//  science_help_toggle = gtk_check_button_new_with_label ("Help");
//  gtk_box_pack_start( GTK_BOX( hbox ), science_help_toggle, true, false, 0 );
//
//  frame = gtk_frame_new( "Goal");
//  gtk_box_pack_start( GTK_BOX( GTK_DIALOG(science_dialog_shell).vbox ),
//        frame, false, false, 0 );
//
//  hbox = gtk_hbox_new( true, 5 );
//  gtk_container_add(GTK_CONTAINER(frame),hbox);
//
//  science_goal_menu_button = gtk_option_menu_new();
//  gtk_box_pack_start( GTK_BOX( hbox ), science_goal_menu_button,true, true, 0 );
//
//  goalmenu = gtk_menu_new();
//  gtk_widget_show_all(goalmenu);
//
//  science_goal_label = gtk_label_new("");
//  gtk_box_pack_start( GTK_BOX( hbox ), science_goal_label, true, false, 0 );
//  gtk_widget_set_usize(science_goal_label, 0,25);
//
//  w = gtk_label_new("");
//  gtk_box_pack_start( GTK_BOX( hbox ), w,true, false, 0 );
//
//  hbox = gtk_hbox_new(true, 0);
//  gtk_box_pack_start(GTK_BOX(GTK_DIALOG(science_dialog_shell).vbox),
//		     hbox, true, true, 0);
//
//  for (i=0; i<4; i++) {
//    science_list[i] = gtk_clist_new(1);
//    gtk_box_pack_start(GTK_BOX(hbox), science_list[i], true, true, 0);
//    gtk_clist_set_column_auto_resize (GTK_CLIST (science_list[i]), 0, true);
//    gtk_signal_connect(GTK_OBJECT(science_list[i]), "select_row",
//		       GTK_SIGNAL_FUNC(science_help_callback), null);
//  }
//
//  close_command = gtk_button_new_with_label("Close");
//  gtk_box_pack_start( GTK_BOX( GTK_DIALOG(science_dialog_shell).action_area ),
//        close_command, true, true, 0 );
//  GTK_WIDGET_SET_FLAGS( close_command, GTK_CAN_DEFAULT );
//  gtk_widget_grab_default( close_command );
//
//  gtk_signal_connect( GTK_OBJECT( close_command ), "clicked",
//        GTK_SIGNAL_FUNC( science_close_callback ), null);
//
//  gtk_widget_show_all( GTK_DIALOG(science_dialog_shell).vbox );
//  gtk_widget_show_all( GTK_DIALOG(science_dialog_shell).action_area );
//
//  gtk_widget_add_accelerator(close_command, "clicked",
//	accel, GDK_Escape, 0, GTK_ACCEL_VISIBLE);
//
//  science_dialog_update();
//}
//
///****************************************************************
//...
//*****************************************************************/
//void science_change_callback(GtkWidget *widget, gpointer data)
//{
//  int to = GPOINTER_TO_INT(data);
//
//  if (GTK_TOGGLE_BUTTON(science_help_toggle).active) {
//    popup_help_dialog_typed(advances[to].name, HELP_TECH);
//    /* Following is to make the menu go back to the current research;
//     * there may be a better way to do this?  --dwp */
//    science_dialog_update();
//  } else {
//    gfloat pct;
//    char text[512];
//
//    gtk_widget_set_sensitive(science_change_menu_button,
//			     can_client_issue_orders());
//    text = util.my_snprintf( "%d/%d",
//		Game.game.player_ptr.research.bulbs_researched,
//		total_bulbs_required(Game.game.player_ptr));
//    pct=CLAMP((gfloat) Game.game.player_ptr.research.bulbs_researched /
//		total_bulbs_required(Game.game.player_ptr), 0.0, 1.0);
//
//    gtk_progress_set_percentage(GTK_PROGRESS(science_current_label), pct);
//    gtk_progress_set_format_string(GTK_PROGRESS(science_current_label), text);
//    
//    dsend_packet_player_research(&aconnection, to);
//  }
//}
//
///****************************************************************
//...
//*****************************************************************/
//void science_goal_callback(GtkWidget *widget, gpointer data)
//{
//  int to = GPOINTER_TO_INT(data);
//
//  if (GTK_TOGGLE_BUTTON(science_help_toggle).active) {
//    popup_help_dialog_typed(advances[to].name, HELP_TECH);
//    /* Following is to make the menu go back to the current goal;
//     * there may be a better way to do this?  --dwp */
//    science_dialog_update();
//  }
//  else {  
//    int steps = num_unknown_techs_for_goal(Game.game.player_ptr, to);
//    char text[512];
//
//    text = util.my_snprintf(
//		PL("(%d step)", "(%d steps)", steps), steps);
//    gtk_set_label(science_goal_label,text);
//
//    dsend_packet_player_tech_goal(&aconnection, to);
//  }
//}
//
///****************************************************************
//...
//*****************************************************************/
//static void science_close_callback(GtkWidget * widget, gpointer data)
//{
//  popdown_science_dialog();
//}
//
///****************************************************************
//...
//*****************************************************************/
//void science_help_callback(GtkWidget *w, gint row, gint column)
//{
//  gtk_clist_unselect_row(GTK_CLIST(w), row, column);
//
//  if (GTK_TOGGLE_BUTTON(science_help_toggle).active)
//  {
//    char *s;
//
//    gtk_clist_get_text(GTK_CLIST(w), row, column, &s);
//    if (*s != '\0')
//      popup_help_dialog_typed(s, HELP_TECH);
//    else
//      popup_help_dialog_string(HELP_TECHS_ITEM);
//  }
//}
//
///****************************************************************
//...
//*****************************************************************/
//static gint cmp_func(gfinalpointer a_p, gfinalpointer b_p)
//{
//  final gchar *a_str, *b_str;
//  gchar text_a[512], text_b[512];
//  gint a = GPOINTER_TO_INT(a_p), b = GPOINTER_TO_INT(b_p);
//
//  if (!is_future_tech(a)) {
//    a_str=advances[a].name;
//  } else {
//    text_a = util.my_snprintf( "Future Tech. %d",
//		a - Game.game.num_tech_types);
//    a_str=text_a;
//  }
//
//  if(!is_future_tech(b)) {
//    b_str=advances[b].name;
//  } else {
//    text_b = util.my_snprintf( "Future Tech. %d",
//		b - Game.game.num_tech_types);
//    b_str=text_b;
//  }
//
//  return strcmp(a_str,b_str);
//}
//
///****************************************************************
//...
//*****************************************************************/
//void science_dialog_update()
//{
//  if(science_dialog_shell) {
//  char text[512];
//  int i, j, hist;
//  static final String row[1];
//  GtkWidget *item;
//  GList *sorting_list = null;
//  gfloat pct;
//  int steps;
//
//  if (is_report_dialogs_frozen()) {
//    return;
//  }
//
//  gtk_set_label(science_label, science_dialog_text());
//
//  for (i=0; i<4; i++) {
//    gtk_clist_freeze(GTK_CLIST(science_list[i]));
//    gtk_clist_clear(GTK_CLIST(science_list[i]));
//  }
//
//  /* collect all researched techs in sorting_list */
//  for(i=A_FIRST; i<Game.game.num_tech_types; i++) {
//    if ((get_invention(Game.game.player_ptr, i)==TECH_KNOWN)) {
//      sorting_list = g_list_append(sorting_list, GINT_TO_POINTER(i));
//    }
//  }
//
//  /* sort them, and install them in the list */
//  sorting_list = g_list_sort(sorting_list, cmp_func);
//  for(i=0; i<g_list_length(sorting_list); i++) {
//    j = GPOINTER_TO_INT(g_list_nth_data(sorting_list, i));
//    row[0] = advances[j].name;
//    gtk_clist_append(GTK_CLIST(science_list[i%4]), (gchar **)row);
//  }
//  g_list_free(sorting_list);
//  sorting_list = null;
//
//  for (i=0; i<4; i++) {
//    gtk_clist_thaw(GTK_CLIST(science_list[i]));
//  }
//
//  gtk_widget_destroy(popupmenu);
//  popupmenu = gtk_menu_new();
//
//  text = util.my_snprintf( "%d/%d",
//	      Game.game.player_ptr.research.bulbs_researched,
//	      total_bulbs_required(Game.game.player_ptr));
//
//  pct=CLAMP((gfloat) Game.game.player_ptr.research.bulbs_researched /
//	    total_bulbs_required(Game.game.player_ptr), 0.0, 1.0);
//
//  gtk_progress_set_percentage(GTK_PROGRESS(science_current_label), pct);
//  gtk_progress_set_format_string(GTK_PROGRESS(science_current_label), text);
//
//  if (Game.game.player_ptr.research.researching == A_UNSET) {
//    item = gtk_menu_item_new_with_label(advances[A_NONE].name);
//    gtk_menu_shell_append(GTK_MENU_SHELL(popupmenu), item);
//  }
//
//  /* collect all techs which are reachable in the next step
//   * hist will hold afterwards the techid of the current choice
//   */
//  hist=0;
//  if (!is_future_tech(Game.game.player_ptr.research.researching)) {
//    for(i=A_FIRST; i<Game.game.num_tech_types; i++) {
//      if(get_invention(Game.game.player_ptr, i)!=TECH_REACHABLE)
//	continue;
//
//      if (i==Game.game.player_ptr.research.researching)
//	hist=i;
//      sorting_list = g_list_append(sorting_list, GINT_TO_POINTER(i));
//    }
//  } else {
//    sorting_list = g_list_append(sorting_list,
//				 GINT_TO_POINTER(Game.game.num_tech_types + 1 +
//						 Game.game.player_ptr.
//						 future_tech));
//  }
//
//  /* sort the list and build from it the menu */
//  sorting_list = g_list_sort(sorting_list, cmp_func);
//  for (i = 0; i < g_list_length(sorting_list); i++) {
//    final gchar *data;
//
//    if (GPOINTER_TO_INT(g_list_nth_data(sorting_list, i)) <
//	Game.game.num_tech_types) {
//      data=advances[GPOINTER_TO_INT(g_list_nth_data(sorting_list, i))].name;
//    } else {
//      text = util.my_snprintf( "Future Tech. %d",
//		  GPOINTER_TO_INT(g_list_nth_data(sorting_list, i))
//		  - Game.game.num_tech_types);
//      data=text;
//    }
//
//    item = gtk_menu_item_new_with_label(data);
//    gtk_menu_append(GTK_MENU(popupmenu), item);
//    if (data.length() > 0)
//      gtk_signal_connect(GTK_OBJECT(item), "activate",
//			 GTK_SIGNAL_FUNC(science_change_callback),
//			 g_list_nth_data(sorting_list, i));
//  }
//
//  gtk_widget_show_all(popupmenu);
//  gtk_menu_set_active(GTK_MENU(popupmenu),
//		      g_list_index(sorting_list, GINT_TO_POINTER(hist)));
//  g_list_free(sorting_list);
//  sorting_list = null;
//
//  gtk_option_menu_set_menu(GTK_OPTION_MENU(science_change_menu_button), 
//			   popupmenu);
//  gtk_widget_set_sensitive(science_change_menu_button,
//			   can_client_issue_orders());
//
//  gtk_widget_destroy(goalmenu);
//  goalmenu = gtk_menu_new();
//  
//  steps = num_unknown_techs_for_goal(Game.game.player_ptr,
//				     Game.game.player_ptr.ai.tech_goal);
//  text = util.my_snprintf( PL("(%d step)", "(%d steps)", steps),
//	      steps);
//  gtk_set_label(science_goal_label,text);
//
//  if (Game.game.player_ptr.ai.tech_goal == A_UNSET) {
//    item = gtk_menu_item_new_with_label(advances[A_NONE].name);
//    gtk_menu_append(GTK_MENU(goalmenu), item);
//  }
//
//  /* collect all techs which are reachable in under 11 steps
//   * hist will hold afterwards the techid of the current choice
//   */
//  hist=0;
//  for(i=A_FIRST; i<Game.game.num_tech_types; i++) {
//    if (tech_is_available(Game.game.player_ptr, i)
//        && get_invention(Game.game.player_ptr, i) != TECH_KNOWN
//        && advances[i].req[0] != Tech_H.A_LAST && advances[i].req[1] != Tech_H.A_LAST
//        && (num_unknown_techs_for_goal(Game.game.player_ptr, i) < 11
//	    || i == Game.game.player_ptr.ai.tech_goal)) {
//      if (i==Game.game.player_ptr.ai.tech_goal)
//	hist=i;
//      sorting_list = g_list_append(sorting_list, GINT_TO_POINTER(i));
//    }
//  }
//
//  /* sort the list and build from it the menu */
//  sorting_list = g_list_sort(sorting_list, cmp_func);
//  for (i = 0; i < g_list_length(sorting_list); i++) {
//    final gchar *data =
//	advances[GPOINTER_TO_INT(g_list_nth_data(sorting_list, i))].name;
//
//    item = gtk_menu_item_new_with_label(data);
//    gtk_menu_append(GTK_MENU(goalmenu), item);
//    gtk_signal_connect(GTK_OBJECT(item), "activate",
//		       GTK_SIGNAL_FUNC(science_goal_callback),
//		       g_list_nth_data(sorting_list, i));
//  }
//
//  gtk_widget_show_all(goalmenu);
//  gtk_menu_set_active(GTK_MENU(goalmenu),
//		      g_list_index(sorting_list, GINT_TO_POINTER(hist)));
//  g_list_free(sorting_list);
//  sorting_list = null;
//
//  gtk_option_menu_set_menu(GTK_OPTION_MENU(science_goal_menu_button), 
//			   goalmenu);
//  gtk_widget_set_sensitive(science_goal_menu_button,
//			   can_client_issue_orders());
//  }
//}
//
//
///****************************************************************
//
//                      ECONOMY REPORT DIALOG
// 
//****************************************************************/
//
///****************************************************************
//...
//****************************************************************/
//void popup_economy_report_dialog(boolean make_modal)
//{
//  if(!economy_dialog_shell) {
//      economy_dialog_shell_is_modal=make_modal;
//    
//      if(make_modal)
//	gtk_widget_set_sensitive(top_vbox, false);
//      
//      create_economy_report_dialog(make_modal);
//      gtk_set_relative_position(toplevel, economy_dialog_shell, 10, 10);
//
//      gtk_widget_show(economy_dialog_shell);
//   }
//}
//
///****************************************************************
// Close the economy report dialog.
//****************************************************************/
//void popdown_economy_report_dialog()
//{
//  if (economy_dialog_shell) {
//    if (economy_dialog_shell_is_modal) {
//      gtk_widget_set_sensitive(top_vbox, true);
//    }
//    gtk_widget_destroy(economy_dialog_shell);
//    economy_dialog_shell = null;
//  }
//}
//
///****************************************************************
//...
//*****************************************************************/
//void create_economy_report_dialog(boolean make_modal)
//{
//  GtkWidget *close_command, *scrolled;
//  static final String titles_[4] = { N"Building Name", N"Count",
//				    N"Cost", N"U Total" };
//  static gchar **titles;
//  int    i;
//  GtkAccelGroup *accel=gtk_accel_group_new();
//
//  if (!titles) titles = intl_slist(4, titles_);
//  
//  economy_dialog_shell = gtk_dialog_new();
//  gtk_signal_connect( GTK_OBJECT(economy_dialog_shell),"delete_event",
//        GTK_SIGNAL_FUNC(economy_close_callback),null );
//  gtk_accel_group_attach(accel, GTK_OBJECT(economy_dialog_shell));
//
//  gtk_window_set_title(GTK_WINDOW(economy_dialog_shell),"Economy");
//
//  economy_list = gtk_clist_new_with_titles( 4, titles );
//  gtk_clist_column_titles_passive(GTK_CLIST(economy_list));
//  scrolled = gtk_scrolled_window_new(null,null);
//  gtk_clist_set_selection_mode(GTK_CLIST (economy_list), GTK_SELECTION_EXTENDED);
//  gtk_container_add(GTK_CONTAINER(scrolled), economy_list);
//  gtk_scrolled_window_set_policy( GTK_SCROLLED_WINDOW( scrolled ),
//  			  GTK_POLICY_AUTOMATIC, GTK_POLICY_AUTOMATIC );
//  gtk_widget_set_usize(economy_list, 300, 150);
//
//  for ( i = 0; i < 4; i++ )
//    gtk_clist_set_column_auto_resize(GTK_CLIST(economy_list),i,true);
//
//  gtk_box_pack_start( GTK_BOX( GTK_DIALOG(economy_dialog_shell).vbox ),
//        scrolled, true, true, 0 );
//
//  economy_label2 = gtk_label_new("Total Cost:");
//  gtk_box_pack_start( GTK_BOX( GTK_DIALOG(economy_dialog_shell).vbox ),
//        economy_label2, false, false, 0 );
//
//  close_command = gtk_button_new_with_label("Close");
//  gtk_box_pack_start( GTK_BOX( GTK_DIALOG(economy_dialog_shell).action_area ),
//        close_command, true, true, 0 );
//  GTK_WIDGET_SET_FLAGS( close_command, GTK_CAN_DEFAULT );
//  gtk_widget_grab_default( close_command );
//
//  sellobsolete_command = gtk_button_new_with_label("Sell Obsolete");
//  gtk_widget_set_sensitive(sellobsolete_command, false);
//  gtk_box_pack_start( GTK_BOX( GTK_DIALOG(economy_dialog_shell).action_area ),
//        sellobsolete_command, true, true, 0 );
//  GTK_WIDGET_SET_FLAGS( sellobsolete_command, GTK_CAN_DEFAULT );
//
//  sellall_command  = gtk_button_new_with_label("Sell All");
//  gtk_widget_set_sensitive(sellall_command, false);
//  gtk_box_pack_start( GTK_BOX( GTK_DIALOG(economy_dialog_shell).action_area ),
//        sellall_command, true, true, 0 );
//  GTK_WIDGET_SET_FLAGS( sellall_command, GTK_CAN_DEFAULT );
//
//  gtk_signal_connect(GTK_OBJECT(economy_list), "select_row",
//	GTK_SIGNAL_FUNC(economy_list_callback), null);
//  gtk_signal_connect(GTK_OBJECT(economy_list), "unselect_row",
//	GTK_SIGNAL_FUNC(economy_list_ucallback), null);
//  gtk_signal_connect(GTK_OBJECT(close_command), "clicked",
//	GTK_SIGNAL_FUNC(economy_close_callback), null);
//  gtk_signal_connect(GTK_OBJECT(sellobsolete_command), "clicked",
//	GTK_SIGNAL_FUNC(economy_selloff_callback), GINT_TO_POINTER(false));
//  gtk_signal_connect(GTK_OBJECT(sellall_command), "clicked",
//	GTK_SIGNAL_FUNC(economy_selloff_callback), GINT_TO_POINTER(true));
//
//  gtk_widget_show_all( GTK_DIALOG(economy_dialog_shell).vbox );
//  gtk_widget_show_all( GTK_DIALOG(economy_dialog_shell).action_area );
//
//  gtk_widget_add_accelerator(close_command, "clicked",
//	accel, GDK_Escape, 0, GTK_ACCEL_VISIBLE);
//
//  economy_report_dialog_update();
//}
//
//
//
///****************************************************************
//  Called when a building type is selected in the economy list.
//*****************************************************************/
//void economy_list_callback(GtkWidget *w, gint row, gint column)
//{
//  int i = economy_row_type[row].type;
//  
//  if (economy_row_type[row].is_impr == true) {
//    boolean is_sellable = (i >= 0 && i < Game.game.num_impr_types && !Improvement.is_wonder(i));
//
//    gtk_widget_set_sensitive(sellobsolete_command, is_sellable
//			     && can_client_issue_orders()
//			     && Improvement.improvement_obsolete(Game.game.player_ptr, i));
//    gtk_widget_set_sensitive(sellall_command, is_sellable
//			     && can_client_issue_orders());
//  } else {
//    gtk_widget_set_sensitive(sellall_command, can_client_issue_orders());
//  }
//}
//
///****************************************************************
//...
//*****************************************************************/
//void economy_list_ucallback(GtkWidget *w, gint row, gint column)
//{
//  gtk_widget_set_sensitive(sellobsolete_command, false);
//  gtk_widget_set_sensitive(sellall_command, false);
//}
//
///****************************************************************
//...
//*****************************************************************/
//static void economy_close_callback(GtkWidget * w, gpointer data)
//{
//  popdown_economy_report_dialog();
//}
//
///****************************************************************
//...
//*****************************************************************/
//void economy_selloff_callback(GtkWidget *w, gpointer data)
//{
//  struct economy_row row_type;
//  char str[1024];
//  GList *selection;
//  int row;
//
//  while ((selection = GTK_CLIST(economy_list).selection)) {
//    row = GPOINTER_TO_INT(selection.data);
//
//    row_type = economy_row_type[row];
//    
//    if (row_type.is_impr == true) {
//      sell_all_improvements(row_type.type, data == null,
//			    str, sizeof(str));
//    } else {
//      disband_all_units(row_type.type, false, str, sizeof(str));
//    }
// 
//    gtk_clist_unselect_row(GTK_CLIST(economy_list), row, 0);
//    popup_notify_dialog("Sell-Off:","Results", str);
//    
//  }
//}
//
///****************************************************************
//...
//*****************************************************************/
//void economy_report_dialog_update()
//{
//  if (is_report_dialogs_frozen()) {
//    return;
//  }
//  if (economy_dialog_shell) {
//    int tax, total, i, entries_used, nbr_impr;
//    char   buf0 [64];
//    char   buf1 [64];
//    char   buf2 [64];
//    char   buf3 [64];
//    gchar *row  [4];
//    char economy_total[48];
//    struct improvement_entry entries[Improvement.B_LAST];
//    struct unit_entry entries_units[unittype.U_LAST];
//
//    gtk_clist_freeze(GTK_CLIST(economy_list));
//    gtk_clist_clear(GTK_CLIST(economy_list));
//
//    row[0] = buf0;
//    row[1] = buf1;
//    row[2] = buf2;
//    row[3] = buf3;
//
//    get_economy_report_data(entries, &entries_used, &total, &tax);
//
//    for (i = 0; i < entries_used; i++) {
//      improvement_entry p = &entries[i];
//
//      buf0 = util.my_snprintf( "%-20s", Improvement.get_improvement_name(p.type));
//      buf1 = util.my_snprintf( "%5d", p.count);
//      buf2 = util.my_snprintf( "%5d", p.cost);
//      buf3 = util.my_snprintf( "%6d", p.total_cost);
//
//      gtk_clist_append(GTK_CLIST(economy_list), row);
//
//      economy_row_type[i].is_impr = true;
//      economy_row_type[i].type = p.type;
//    }
//
//    nbr_impr = entries_used;
//    entries_used = 0;
//    get_economy_report_units_data(entries_units, &entries_used, &total);
//
//    for (i = 0; i < entries_used; i++) {
//      buf0 = util.my_snprintf( "%-20s",
//		  Unittype_P.unit_name(entries_units[i].type));
//      buf1 = util.my_snprintf( "%5d", entries_units[i].count);
//      buf2 = util.my_snprintf( "%5d", entries_units[i].cost);
//      buf3 = util.my_snprintf( "%6d", entries_units[i].total_cost);
//
//      gtk_clist_append(GTK_CLIST(economy_list), row);
//
//      economy_row_type[i + nbr_impr].is_impr = false;
//      economy_row_type[i + nbr_impr].type = entries_units[i].type;
//      
//    }
//
//    economy_total = util.my_snprintf(
//		"Income:%6d    Total Costs: %6d", tax, total); 
//    gtk_set_label(economy_label2, economy_total); 
//
//    gtk_widget_show_all(economy_list);
//    gtk_clist_thaw(GTK_CLIST(economy_list));
//  }  
//}
//
///****************************************************************
//
//                      ACTIVE UNITS REPORT DIALOG
// 
//****************************************************************/
//
//public static final int AU_COL = 7;
//
///****************************************************************
//...
//****************************************************************/
//void popup_activeunits_report_dialog(boolean make_modal)
//{
//  if(!activeunits_dialog_shell) {
//      activeunits_dialog_shell_is_modal=make_modal;
//    
//      if(make_modal)
//	gtk_widget_set_sensitive(top_vbox, false);
//      
//      create_activeunits_report_dialog(make_modal);
//      gtk_set_relative_position(toplevel, activeunits_dialog_shell, 10, 10);
//
//      gtk_widget_show(activeunits_dialog_shell);
//   }
//}
//
///****************************************************************
// Closes the units report dialog.
//****************************************************************/
//void popdown_activeunits_report_dialog()
//{
//  if (activeunits_dialog_shell) {
//    if (activeunits_dialog_shell_is_modal) {
//      gtk_widget_set_sensitive(top_vbox, true);
//    }
//    gtk_widget_destroy(activeunits_dialog_shell);
//    activeunits_dialog_shell = null;
//  }
//}
//
///****************************************************************
//...
//*****************************************************************/
//void create_activeunits_report_dialog(boolean make_modal)
//{
//  GtkWidget *close_command, *refresh_command;
//  static final String titles_[AU_COL]
//    = { N"Unit Type", N"U", N"In-Prog", N"Active",
//	N"Shield", N"Food", N"Gold" };
//  static gchar **titles;
//  int    i;
//  GtkAccelGroup *accel=gtk_accel_group_new();
//
//  if (!titles) titles = intl_slist(AU_COL, titles_);
//
//  activeunits_dialog_shell = gtk_dialog_new();
//  gtk_signal_connect( GTK_OBJECT(activeunits_dialog_shell),"delete_event",
//        GTK_SIGNAL_FUNC(activeunits_close_callback),null );
//  gtk_accel_group_attach(accel, GTK_OBJECT(activeunits_dialog_shell));
//
//  gtk_window_set_title(GTK_WINDOW(activeunits_dialog_shell),"Units");
//
//  activeunits_list = gtk_clist_new_with_titles( AU_COL, titles );
//  gtk_clist_column_titles_passive(GTK_CLIST(activeunits_list));
//  for ( i = 0; i < AU_COL; i++ ) {
//    gtk_clist_set_column_auto_resize(GTK_CLIST(activeunits_list),i,true);
//    if (i > 1) {
//      gtk_clist_set_column_justification (GTK_CLIST(activeunits_list),
//					  i, GTK_JUSTIFY_RIGHT);
//    }
//  }
//
//  gtk_box_pack_start( GTK_BOX( GTK_DIALOG(activeunits_dialog_shell).vbox ),
//        activeunits_list, true, true, 0 );
//
//  activeunits_label2 = gtk_label_new("Totals: ...");
//  gtk_box_pack_start( GTK_BOX( GTK_DIALOG(activeunits_dialog_shell).vbox ),
//        activeunits_label2, false, false, 0 );
//
//  close_command = gtk_button_new_with_label("Close");
//  gtk_box_pack_start( GTK_BOX( GTK_DIALOG(activeunits_dialog_shell).action_area ),
//        close_command, true, true, 0 );
//  GTK_WIDGET_SET_FLAGS( close_command, GTK_CAN_DEFAULT );
//  gtk_widget_grab_default( close_command );
//
//  upgrade_command = gtk_button_new_with_label("Upgrade");
//  gtk_widget_set_sensitive(upgrade_command, false);
//  gtk_box_pack_start( GTK_BOX( GTK_DIALOG(activeunits_dialog_shell).action_area ),
//        upgrade_command, true, true, 0 );
//  GTK_WIDGET_SET_FLAGS( upgrade_command, GTK_CAN_DEFAULT );
//
//  refresh_command = gtk_button_new_with_label("Refresh");
//  gtk_box_pack_start( GTK_BOX( GTK_DIALOG(activeunits_dialog_shell).action_area ),
//        refresh_command, true, true, 0 );
//  GTK_WIDGET_SET_FLAGS( refresh_command, GTK_CAN_DEFAULT );
//
//  gtk_signal_connect(GTK_OBJECT(activeunits_list), "select_row",
//	GTK_SIGNAL_FUNC(activeunits_list_callback), null);
//  gtk_signal_connect(GTK_OBJECT(activeunits_list), "unselect_row",
//	GTK_SIGNAL_FUNC(activeunits_list_ucallback), null);
//  gtk_signal_connect(GTK_OBJECT(close_command), "clicked",
//	GTK_SIGNAL_FUNC(activeunits_close_callback), null);
//  gtk_signal_connect(GTK_OBJECT(upgrade_command), "clicked",
//	GTK_SIGNAL_FUNC(activeunits_upgrade_callback), null);
//  gtk_signal_connect(GTK_OBJECT(refresh_command), "clicked",
//	GTK_SIGNAL_FUNC(activeunits_refresh_callback), null);
//
//  gtk_widget_show_all( GTK_DIALOG(activeunits_dialog_shell).vbox );
//  gtk_widget_show_all( GTK_DIALOG(activeunits_dialog_shell).action_area );
//
//  gtk_widget_add_accelerator(close_command, "clicked",
//	accel, GDK_Escape, 0, GTK_ACCEL_VISIBLE);
//
//  activeunits_report_dialog_update();
//}
//
///****************************************************************
//...
//*****************************************************************/
//void activeunits_list_callback(GtkWidget *w, gint row, gint column)
//{
//  if ((unit_type_exists(activeunits_type[row])) &&
//      (can_upgrade_unittype(Game.game.player_ptr, activeunits_type[row]) != -1))
//    gtk_widget_set_sensitive(upgrade_command, can_client_issue_orders());
//}
//
///****************************************************************
//...
//*****************************************************************/
//void activeunits_list_ucallback(GtkWidget *w, gint row, gint column)
//{
//  gtk_widget_set_sensitive(upgrade_command, false);
//}
//
///****************************************************************
//...
//*****************************************************************/
//static void upgrade_callback_yes(gpointer data)
//{
//  dsend_packet_unit_type_upgrade(&aconnection, GPOINTER_TO_INT(data));
//}
//
///****************************************************************
//...
//*****************************************************************/
//void activeunits_upgrade_callback(GtkWidget *w, gpointer data)
//{
//  char buf[512];
//  int ut1,ut2;
//  GList              *selection;
//  gint                row;
//
//  if ( !( selection = GTK_CLIST( activeunits_list ).selection ) )
//      return;
//
//  row = GPOINTER_TO_INT(selection.data);
//
//  ut1 = activeunits_type[row];
//  if (!(unit_type_exists (ut1)))
//    return;
//  /* puts(Unittype_P.unit_types[ut1].name); */
//
//  ut2 = can_upgrade_unittype(Game.game.player_ptr, activeunits_type[row]);
//
//  buf = util.my_snprintf(
//	  ("Upgrade as many %s to %s as possible for %d gold each?\n" +
//	    "Treasury contains %d gold."),
//	  Unittype_P.unit_types[ut1].name, Unittype_P.unit_types[ut2].name,
//	  unit_upgrade_price(Game.game.player_ptr, ut1, ut2),
//	  Game.game.player_ptr.economic.gold);
//
//  popup_message_dialog(top_vbox, /*"upgradedialog" */
//		       "Upgrade Obsolete Units", buf,
//		       dummy_close_callback, null, 
//		       "Yes",
//		       upgrade_callback_yes,
//		       GINT_TO_POINTER(activeunits_type[row]), "No",
//		       null, 0, 0);
//}
//
///****************************************************************
//...
//*****************************************************************/
//static void activeunits_close_callback(GtkWidget * w, gpointer data)
//{
//  popdown_activeunits_report_dialog();
//}
//
///****************************************************************
//...
//*****************************************************************/
//void activeunits_refresh_callback(GtkWidget *w, gpointer data)
//{
//  activeunits_report_dialog_update();
//}
//
///****************************************************************
//...
//*****************************************************************/
//void activeunits_report_dialog_update()
//{
//  struct repoinfo {
//    int active_count;
//    int upkeep_shield;
//    int upkeep_food;
//    int upkeep_gold;   
//    int building_count;
//  };
//  if(is_report_dialogs_frozen()) return;
//  if(activeunits_dialog_shell) {
//    int    i, k, can;
//    struct repoinfo unitarray[unittype.U_LAST];
//    struct repoinfo unittotals;
//    char   activeunits_total[100];
//    gchar *row[AU_COL];
//    char   buf[AU_COL][64];
//
//    gtk_clist_freeze(GTK_CLIST(activeunits_list));
//    gtk_clist_clear(GTK_CLIST(activeunits_list));
//
//    for (i = 0; i < ARRAY_SIZE(row); i++) {
//      row[i] = buf[i];
//    }
//
//    memset(unitarray, '\0', sizeof(unitarray));
//    for (unit punit : Game.game.player_ptr.units.data) {
//      (unitarray[punit.type].active_count)++;
//      if (punit.homecity) {
//	unitarray[punit.type].upkeep_shield += punit.upkeep;
//	unitarray[punit.type].upkeep_food += punit.upkeep_food;
//	unitarray[punit.type].upkeep_gold += punit.upkeep_gold;
//      }
//    }
//    }
//    city_list_iterate(Game.game.player_ptr.cities,pcity) {
//      if (pcity.is_building_unit &&
//	  (unit_type_exists (pcity.currently_building)))
//	(unitarray[pcity.currently_building].building_count)++;
//    }
//    }
//
//    k = 0;
//    memset(&unittotals, '\0', sizeof(unittotals));
//    unit_type_iterate(i) {
//      if ((unitarray[i].active_count > 0) || (unitarray[i].building_count > 0)) {
//	can = (can_upgrade_unittype(Game.game.player_ptr, i) != -1);
//        my_snprintf(buf[0], sizeof(buf[0]), "%-27s", Unittype_P.unit_name(i));
//	my_snprintf(buf[1], sizeof(buf[1]), "%c", can ? '*': '-');
//        my_snprintf(buf[2], sizeof(buf[2]), "%9d", unitarray[i].building_count);
//        my_snprintf(buf[3], sizeof(buf[3]), "%9d", unitarray[i].active_count);
//        my_snprintf(buf[4], sizeof(buf[4]), "%9d", unitarray[i].upkeep_shield);
//        my_snprintf(buf[5], sizeof(buf[5]), "%9d", unitarray[i].upkeep_food);
//	my_snprintf(buf[6], sizeof(buf[6]), "%9d", unitarray[i].upkeep_gold);
//
//	gtk_clist_append( GTK_CLIST( activeunits_list ), row );
//
//	activeunits_type[k]=(unitarray[i].active_count > 0) ? i : unittype.U_LAST;
//	k++;
//	unittotals.active_count += unitarray[i].active_count;
//	unittotals.upkeep_shield += unitarray[i].upkeep_shield;
//	unittotals.upkeep_food += unitarray[i].upkeep_food;
//	unittotals.upkeep_gold += unitarray[i].upkeep_gold;
//	unittotals.building_count += unitarray[i].building_count;
//      }
//    } unit_type_iterate_end;
//
//    /* horrible kluge, but I can't get gtk_label_set_justify() to work --jjm */
//    activeunits_total = util.my_snprintf(
//	    "Totals:                     %s%9d%s%9d%s%9d%s%9d%s%9d",
//	    "        ", unittotals.building_count,
//	    " ", unittotals.active_count,
//	    " ", unittotals.upkeep_shield,
//	    " ", unittotals.upkeep_food,
//	    " ", unittotals.upkeep_gold);
//    gtk_set_label(activeunits_label2, activeunits_total); 
//
//    gtk_widget_show_all(activeunits_list);
//    gtk_clist_thaw(GTK_CLIST(activeunits_list));
//
//    activeunits_list_ucallback(null, 0, 0);
//  }
//}
//
///****************************************************************
//
//                      FINAL REPORT DIALOG
// 
//****************************************************************/
//
///****************************************************************
//  Prepare the Final Report dialog, and fill it with 
//  statistics for each player.
//*****************************************************************/
//static void create_endgame_report(packet_endgame_report packet)
//{
//  static gchar **titles;
//  GtkAccelGroup *accel = gtk_accel_group_new();
//  char *row[NUM_SCORE_COLS];
//  char stat[NUM_SCORE_COLS][64];
//  int i;
//
//  static final String titles_[NUM_SCORE_COLS] = {
//    N"Player\n",
//    N"Score\n",
//    N"Population\n",
//    N"Trade\n(M goods)",
//    N"Production\n(M tons)",
//    N"Cities\n",
//    N"Technologies\n",
//    N"Military Service\n(months)",
//    N"Wonders\n",
//    N"Research Speed\n(%)",
//    N"Land Area\n(sq. mi.)",
//    N"Settled Area\n(sq. mi.)",
//    N"Literacy\n(%)",
//    N"Spaceship\n"
//  };
//
//  for (i = 0; i < ARRAY_SIZE(row); i++) {
//    row[i] = stat[i];
//  }      
//
//  if (!titles) {
//    titles = intl_slist(NUM_SCORE_COLS, titles_);
//  }
//
//  endgame_report_shell = gtk_dialog_new();
//  gtk_signal_connect(GTK_OBJECT(endgame_report_shell), "delete_event",
//                     GTK_SIGNAL_FUNC(endgame_destroy_callback), null);
//
//  gtk_accel_group_attach(accel, GTK_OBJECT(endgame_report_shell));
//  gtk_window_set_title(GTK_WINDOW(endgame_report_shell), 
//                       "The Greatest Civilizations in the world.");
//  scores_list = gtk_clist_new_with_titles(NUM_SCORE_COLS, titles);
//
//  sw = gtk_scrolled_window_new(null, null);
//  gtk_container_add(GTK_CONTAINER(sw), scores_list);
//  gtk_scrolled_window_set_policy(GTK_SCROLLED_WINDOW(sw),
//                                 GTK_POLICY_AUTOMATIC,
//                                 GTK_POLICY_AUTOMATIC);
//  gtk_widget_set_usize(sw, 700, 420); 
//
//  for (i = 0; i < NUM_SCORE_COLS; i++) {
//    gtk_clist_set_column_auto_resize(GTK_CLIST(scores_list), i, true);
//  }
//
//  gtk_box_pack_start(GTK_BOX(GTK_DIALOG(endgame_report_shell).vbox),
//                     sw, true, true, 0);
//
//  close_endgame_command = gtk_accelbutton_new("C_lose", accel);
//  gtk_box_pack_start(GTK_BOX(GTK_DIALOG(endgame_report_shell).action_area),
//                     close_endgame_command, true, true, 0);
//  GTK_WIDGET_SET_FLAGS(close_endgame_command, GTK_CAN_DEFAULT);
//  gtk_widget_grab_default(close_endgame_command);
//  gtk_signal_connect(GTK_OBJECT(close_endgame_command), "clicked",
//                     GTK_SIGNAL_FUNC(endgame_destroy_callback), null);
//
//  gtk_widget_show_all(GTK_DIALOG(endgame_report_shell).vbox);
//  gtk_widget_show_all(GTK_DIALOG(endgame_report_shell).action_area);
//
//  /* Insert score statistics into table. */
//  gtk_clist_freeze(GTK_CLIST(scores_list));
//  gtk_clist_clear(GTK_CLIST(scores_list));
//  for (i = 0; i < packet.nscores; i++) {
//    my_snprintf(stat[0], sizeof(stat[0]), "%s", get_player(packet.id[i]).name);
//    my_snprintf(stat[1], sizeof(stat[1]), "%d", packet.score[i]);
//    my_snprintf(stat[2], sizeof(stat[2]), "%d", packet.pop[i]);
//    my_snprintf(stat[3], sizeof(stat[3]), "%d", packet.bnp[i]);
//    my_snprintf(stat[4], sizeof(stat[4]), "%d", packet.mfg[i]);
//    my_snprintf(stat[5], sizeof(stat[5]), "%d", packet.cities[i]);
//    my_snprintf(stat[6], sizeof(stat[6]), "%d", packet.techs[i]);
//    my_snprintf(stat[7], sizeof(stat[7]), "%d", packet.mil_service[i]);
//    my_snprintf(stat[8], sizeof(stat[8]), "%d", packet.wonders[i]);
//    my_snprintf(stat[9], sizeof(stat[9]), "%d", packet.research[i]);
//    my_snprintf(stat[10], sizeof(stat[10]), "%d", packet.landarea[i]);
//    my_snprintf(stat[11], sizeof(stat[11]), "%d", packet.settledarea[i]);
//    my_snprintf(stat[12], sizeof(stat[12]), "%d", packet.literacy[i]);
//    my_snprintf(stat[13], sizeof(stat[13]), "%d", packet.spaceship[i]);
//    gtk_clist_append(GTK_CLIST(scores_list), row);
//  }
//  gtk_clist_thaw(GTK_CLIST(scores_list));
//}
//
///**************************************************************************
//  Show a dialog with player statistics at endgame.
//**************************************************************************/
//void popup_endgame_report_dialog(packet_endgame_report packet)
//{
//  if (!endgame_report_shell) {
//    create_endgame_report(packet);
//    gtk_set_relative_position(toplevel, endgame_report_shell, 10, 10);
//    gtk_widget_show(endgame_report_shell);
//  }
//}
//
///**************************************************************************
//  Close the endgame report.
//**************************************************************************/
//static void endgame_destroy_callback(GtkObject *object, gpointer data)
//{
//  if (endgame_report_shell) {
//    gtk_widget_destroy(endgame_report_shell);
//    endgame_report_shell = null;
//  }
//}
//
///*************************************************************************
//  Server options dialog
//*************************************************************************/
//void popup_settable_options_dialog()
//{
//  /* PORT ME */
//}
}