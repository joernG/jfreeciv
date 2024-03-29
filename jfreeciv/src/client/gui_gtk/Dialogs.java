package client.gui_gtk;

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
//#include <gdk/gdkkeysyms.h>
//#include <gtk/gtk.h>
//
//#include "fcintl.h"
//#include "Game.game.h"
//#include "government.h"
//#include "log.h"
//#include "Map.map.h"
//#include "mem.h"
//#include "packets.h"
//#include "player.h"
//#include "rand.h"
//#include "support.h"
//
//#include "civclient.h"
//#include "climisc.h"
//#include "clinet.h"
//#include "control.h"
//#include "mapctrl_common.h"
//#include "options.h"
//#include "packhand.h"
//#include "tilespec.h"
//
//#include "chatline.h"
//#include "cityrep.h"	/* for popdown_city_report_dialog */
//#include "gui_main.h"
//#include "gui_stuff.h"
//#include "mapview.h"
//#include "messagewin.h"	/* for popdown_meswin_dialog */
//#include "plrdlg.h"	/* for popdown_players_dialog */
//#include "repodlgs.h"	/* for popdown_xxx_dialog */
//
//#include "dialogs.h"
//
///******************************************************************/
//static gint popup_mes_del_callback(GtkWidget *widget, GdkEvent *event,
//				   gpointer data);
//
///******************************************************************/
//static GtkWidget  *races_dialog_shell=null;
//static GtkWidget  *races_toggles_form[Shared_H.MAX_NUM_ITEMS];
//static GtkWidget  *races_by_name[Shared_H.MAX_NUM_ITEMS];
//static GtkWidget  *class[Shared_H.MAX_NUM_ITEMS];
//static GtkWidget  *legend[Shared_H.MAX_NUM_ITEMS];
//static GtkWidget  *legend_frame[Shared_H.MAX_NUM_ITEMS];
//static GtkWidget  *races_sex_toggles_form;
//static GtkWidget  *city_style_toggles_form;
//static GtkWidget  *races_ok_command;            /* ok button */
//static GtkWidget  *races_disc_command=null;     /* disc button */
//static GtkWidget  *races_quit_command=null;     /* quit button */
// /* toggle race */
//static GtkWidget **races_toggles[Shared_H.MAX_NUM_ITEMS];
//static GtkWidget *races_sex_toggles[2];		/* Male/Female */
//static GtkWidget **city_style_toggles = null;
//static GtkWidget *leader_name;			/* leader name */
//static GtkWidget *notebook;
//
//static int       num_classes;
//static char      *class_names[Shared_H.MAX_NUM_ITEMS];
//
//static GList *leader_strings = null;
//
///*
// * Contains a list of race ids sorted by the race name. Is valid as
// * long as the races_dialog is poped up. 
// */
//static GList *sorted_races_list[Shared_H.MAX_NUM_ITEMS];
//
///******************************************************************/
//static GtkWidget  *notify_dialog_shell;
//static GtkWidget  *notify_headline;
//static GtkWidget  *notify_label;
//
///******************************************************************/
//static GtkWidget  *spy_tech_shell;
//static GtkWidget  *spy_advances_list;
//static GtkWidget  *spy_steal_command;
//
//static int         spy_tech_shell_is_modal;
//static int         advance_type[Tech_H.A_LAST+1];
//static int         steal_advance = 0;
//
///******************************************************************/
//static GtkWidget  *spy_sabotage_shell;
//static GtkWidget  *spy_improvements_list;
//static GtkWidget  *spy_sabotage_command;
//
//static int         spy_sabotage_shell_is_modal;
//static int         improvement_type[Improvement.B_LAST+1];
//static int         sabotage_improvement = 0;
//
///******************************************************************/
//
//public static final int MAX_SELECT_UNITS = 100;
//static GtkWidget  *unit_select_dialog_shell;
//static GtkWidget  *unit_select_commands[MAX_SELECT_UNITS];
//static GtkWidget  *unit_select_labels[MAX_SELECT_UNITS];
//static int         unit_select_ids[MAX_SELECT_UNITS];
//static int         unit_select_no;
//
//static int races_buttons_get_current();
//static int sex_buttons_get_current();
//static int city_style_get_current();
//
//static void create_races_dialog();
//static void races_buttons_callback(GtkWidget *w, gpointer data);
//static void races_toggles_callback(GtkWidget *w, gpointer race_id_p);
//static void races_sex_toggles_callback(GtkWidget *w, gpointer data);
//static void races_by_name_callback(GtkWidget *w, gpointer data);
//static void leader_name_callback(GtkWidget *w, gpointer data);
//static void city_style_toggles_callback(GtkWidget *w, gpointer data);
//static void switch_page_callback(GtkNotebook * notebook,
//				 GtkNotebookPage * page, gint page_num,
//				 gpointer data);
//
//static int selected_nation;
//static int selected_leader;
//static boolean is_name_unique = false;
//static int selected_sex;
//static int selected_city_style;
//static int selected_class;
//static int city_style_idx[64];  /* translation table basic style.city_style */
//static int city_style_ridx[64]; /* translation table the other way. they     */
//                                /* in fact limit the num of styles to 64     */
//static int b_s_num; /* number of basic city styles, 
//                     * i.e. those that you can start with */
//
//static int caravan_city_id;
//static int caravan_unit_id;
//
//static int diplomat_dialog_open = 0;
//static int diplomat_id;
//static int diplomat_target_id;
//
//static GtkWidget *caravan_dialog;
//
//struct pillage_data {
//  int unit_id;
//  enum int what;
//};
//
///****************************************************************
//...
//*****************************************************************/
//static void notify_command_callback(GtkWidget *w, GtkWidget *t)
//{
//  popdown_notify_dialog();
//}
//
///****************************************************************
//...
//*****************************************************************/
//gint deleted_callback(GtkWidget *w, GdkEvent *ev, gpointer data)
//{
//  gtk_widget_set_sensitive( top_vbox, true );
//  return false;
//}
//
//
///****************************************************************
//...
//*****************************************************************/
//static void create_notify_dialog()
//{
//  GtkWidget *notify_command;
//  GtkWidget *notify_scrolled;
//  GtkAccelGroup *accel=gtk_accel_group_new();
//  
//  notify_dialog_shell = gtk_dialog_new();
//  gtk_signal_connect( GTK_OBJECT(notify_dialog_shell),"delete_event",
//	GTK_SIGNAL_FUNC(deleted_callback),null );
//  gtk_accel_group_attach(accel, GTK_OBJECT(notify_dialog_shell));
//  gtk_widget_set_name(notify_dialog_shell, "Freeciv");
//
//  gtk_container_border_width( GTK_CONTAINER(GTK_DIALOG(notify_dialog_shell).vbox), 5 );
//
//  notify_headline = gtk_label_new(null);   
//  gtk_box_pack_start( GTK_BOX( GTK_DIALOG(notify_dialog_shell).vbox ),
//	notify_headline, false, false, 0 );
//  gtk_widget_set_name(notify_headline, "notify label");
//
//  gtk_label_set_justify( GTK_LABEL( notify_headline ), GTK_JUSTIFY_LEFT );
//  gtk_misc_set_alignment(GTK_MISC(notify_headline), 0.0, 0.0);
//
//  notify_scrolled=gtk_scrolled_window_new(null,null);
//  gtk_scrolled_window_set_policy(GTK_SCROLLED_WINDOW(notify_scrolled),
//				 GTK_POLICY_NEVER, GTK_POLICY_AUTOMATIC);
//  notify_label = gtk_label_new(null);  
//  gtk_scrolled_window_add_with_viewport(GTK_SCROLLED_WINDOW (notify_scrolled),
//					notify_label);
//
//  gtk_widget_set_name(notify_label, "notify label");
//  gtk_label_set_justify( GTK_LABEL( notify_label ), GTK_JUSTIFY_LEFT );
//  gtk_misc_set_alignment(GTK_MISC(notify_label), 0.0, 0.0);
//
//  gtk_box_pack_start( GTK_BOX( GTK_DIALOG(notify_dialog_shell).vbox ),
//	notify_scrolled, true, true, 0 );
//
//
//  notify_command = gtk_button_new_with_label( "Close" );
//  gtk_box_pack_start( GTK_BOX( GTK_DIALOG(notify_dialog_shell).action_area ),
//	notify_command, true, true, 0 );
//  GTK_WIDGET_SET_FLAGS( notify_command, GTK_CAN_DEFAULT );
//  gtk_widget_grab_default( notify_command );
//  gtk_widget_add_accelerator(notify_command, "clicked",
//	accel, GDK_Escape, 0, 0);
//
//  gtk_signal_connect( GTK_OBJECT( notify_command ), "clicked",
//	GTK_SIGNAL_FUNC( notify_command_callback ), notify_dialog_shell );
//
//  
//  gtk_widget_show_all( GTK_DIALOG(notify_dialog_shell).vbox );
//  gtk_widget_show_all( GTK_DIALOG(notify_dialog_shell).action_area );
//
//  gtk_widget_set_usize(notify_dialog_shell, 0, 265);
//}
//
///****************************************************************
//...
//*****************************************************************/
//static void notify_dialog_update(final String caption, final String headline,
//				 final String lines)
//{
//  gtk_window_set_title(GTK_WINDOW(notify_dialog_shell), caption);
//  gtk_label_set_text(GTK_LABEL(notify_headline), headline);
//  gtk_label_set_text(GTK_LABEL(notify_label), lines);
//}
//
///**************************************************************************
//  Popup a generic dialog to display some generic information.
//**************************************************************************/
//void popup_notify_dialog(final String caption, final String headline,
//			 final String lines)
//{
//  if (!notify_dialog_shell) {
//    create_notify_dialog();
//    
//    gtk_set_relative_position(toplevel, notify_dialog_shell, 10, 10);
//  }
//
//  notify_dialog_update(caption, headline, lines);
//  gtk_window_show(GTK_WINDOW(notify_dialog_shell));
//}
//
///****************************************************************
// Closes the notify dialog.
//*****************************************************************/
//void popdown_notify_dialog()
//{
//  if (notify_dialog_shell) {
//    gtk_widget_destroy(notify_dialog_shell);
//    notify_dialog_shell = null;
//  }
//}
//
///****************************************************************
//...
//*****************************************************************/
//
///* surely this should use genlists??  --dwp */
//Speclists<widget> {
//  GtkWidget *w;
//  tile tile;
//  widget_list next;
//};
//static widget_list notify_goto_widget_list = null;
//
//static void notify_goto_widget_remove(GtkWidget *w)
//{
//  widget_list cur, *tmp;
//  cur=notify_goto_widget_list;
//  if (!cur)
//    return;
//  if (cur && cur.w == w) {
//    cur = cur.next;
//    free(notify_goto_widget_list);
//    notify_goto_widget_list = cur;
//    return;
//  }
//  for (; cur.next && cur.next.w!= w; cur=cur.next);
//  if (cur.next) {
//    tmp = cur.next;
//    cur.next = cur.next.next;
//    free(tmp);
//  }
//}
//
//static tile notify_goto_find_widget(GtkWidget *w)
//{
//  widget_list cur;
//
//  for (cur = notify_goto_widget_list; cur && cur.w !=w; cur = cur.next) {
//    /* Nothing */
//  }
//
//  if (cur) {
//    return cur.tile;
//  } else {
//    return null;
//  }
//}
//
//static void notify_goto_add_widget_tile(GtkWidget *w, tile ptile)
//{
//  widget_list newwidget;
//  newwidget = fc_malloc(sizeof(Speclists<widget>));
//  newwidget.w = w;
//  newwidget.tile = ptile;
//  newwidget.next = notify_goto_widget_list;
//  notify_goto_widget_list = newwidget;
//}
//
//static void notify_goto_command_callback(GtkWidget *w, gpointer data)
//{
//  tile ptile =  notify_goto_find_widget(w);
//
//  center_tile_mapcanvas(ptile);
//  notify_goto_widget_remove(w);
//
//  gtk_widget_destroy(w.parent.parent.parent);
//  gtk_widget_set_sensitive(top_vbox, true);
//}
//
//static void notify_no_goto_command_callback(GtkWidget *w, gpointer data)
//{
//  notify_goto_widget_remove(w);
//  gtk_widget_destroy(w.parent.parent.parent);
//  gtk_widget_set_sensitive(top_vbox, true);
//}
//
//static gint notify_deleted_callback(GtkWidget *widget, GdkEvent *event,
//				    gpointer data)
//{
//  notify_goto_widget_remove(widget);
//  gtk_widget_set_sensitive(top_vbox, true);
//  return false;
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
//  GtkWidget *notify_dialog_shell, *notify_command, *notify_goto_command;
//  GtkWidget *notify_label;
//  
//  if (!ptile) {
//    popup_notify_dialog("Message:", headline, lines);
//    return;
//  }
//  notify_dialog_shell = gtk_dialog_new();
//  gtk_signal_connect( GTK_OBJECT(notify_dialog_shell),"delete_event",
//	GTK_SIGNAL_FUNC(notify_deleted_callback),null );
//
//  gtk_window_set_title( GTK_WINDOW( notify_dialog_shell ), headline );
//
//  notify_label=gtk_label_new(lines);
//  gtk_box_pack_start( GTK_BOX( GTK_DIALOG(notify_dialog_shell).vbox ),
//	notify_label, true, true, 0 );
//
//  notify_command = gtk_button_new_with_label("Close");
//  gtk_box_pack_start( GTK_BOX( GTK_DIALOG(notify_dialog_shell).action_area ),
//	notify_command, true, true, 0 );
//
//  notify_goto_command = gtk_button_new_with_label("Goto and Close");
//  gtk_box_pack_start( GTK_BOX( GTK_DIALOG(notify_dialog_shell).action_area ),
//	notify_goto_command, true, true, 0 );
//  
//  gtk_signal_connect(GTK_OBJECT(notify_command), "clicked",
//		GTK_SIGNAL_FUNC(notify_no_goto_command_callback), null);
//  gtk_signal_connect(GTK_OBJECT(notify_goto_command), "clicked",
//		GTK_SIGNAL_FUNC(notify_goto_command_callback), null);
//  notify_goto_add_widget_tile(notify_goto_command, ptile);
//
//  gtk_set_relative_position(toplevel, notify_dialog_shell, 25, 25);
//
//  gtk_widget_show_all( GTK_DIALOG(notify_dialog_shell).vbox );
//  gtk_widget_show_all( GTK_DIALOG(notify_dialog_shell).action_area );
//  gtk_widget_show(notify_dialog_shell);
//
//  gtk_widget_set_sensitive(top_vbox, false);
//}
//
//
///****************************************************************
//...
//*****************************************************************/
//static void diplomat_bribe_yes_callback(gpointer data)
//{
//  request_diplomat_action(DIPLOMAT_BRIBE, diplomat_id,
//			  diplomat_target_id, 0);
//}
//
//
///****************************************************************
//...  Ask the server how much the bribe is
//*****************************************************************/
//static void diplomat_bribe_callback(gpointer data)
//{
//  if (Game.find_unit_by_id(diplomat_id) && Game.find_unit_by_id(diplomat_target_id)) {
//    dsend_packet_unit_bribe_inq(&aconnection, diplomat_target_id);
//  }
//}
// 
///****************************************************************
//...
//*****************************************************************/
//void popup_bribe_dialog(unit punit)
//{
//  char buf[128];
//  
//  if (unit_flag(punit, F_UNBRIBABLE)) {
//    popup_message_dialog(top_vbox, "Ooops...",
//			 "This unit cannot be bribed!",
//			 dummy_close_callback, null, "Darn", null, 0, 0);
//  } else if(Game.game.player_ptr.economic.gold>=punit.bribe_cost) {
//    buf = util.my_snprintf(
//		"Bribe unit for %d gold?\nTreasury contains %d gold.", 
//		punit.bribe_cost, Game.game.player_ptr.economic.gold);
//    popup_message_dialog(top_vbox, "Bribe Enemy Unit", buf,
//			 dummy_close_callback, null,
//			 "_Yes", diplomat_bribe_yes_callback, 0, 
//			 "_No", null, 0, 0);
//  } else {
//    buf = util.my_snprintf(
//		("Bribing the unit costs %d gold.\n" +
//		  "Treasury contains %d gold."), 
//		punit.bribe_cost, Game.game.player_ptr.economic.gold);
//    popup_message_dialog(top_vbox, "Traitors Demand Too Much!", buf,
//			 dummy_close_callback, null, "Darn", null, 0, 0);
//  }
//}
//
///****************************************************************
//...
//*****************************************************************/
//static void diplomat_sabotage_callback(gpointer data)
//{
//  diplomat_dialog_open=0;
//
//  if(Game.find_unit_by_id(diplomat_id) && 
//     Game.find_city_by_id(diplomat_target_id)) { 
//    request_diplomat_action(DIPLOMAT_SABOTAGE, diplomat_id,
//			    diplomat_target_id, -1);
//  }
//
//  process_diplomat_arrival(null, 0);
//}
//
///****************************************************************
//...
//*****************************************************************/
//static void diplomat_investigate_callback(gpointer data)
//{
//  diplomat_dialog_open=0;
//
//  if(Game.find_unit_by_id(diplomat_id) && 
//     (Game.find_city_by_id(diplomat_target_id))) { 
//    request_diplomat_action(DIPLOMAT_INVESTIGATE, diplomat_id,
//			    diplomat_target_id, 0);
//  }
//
//  process_diplomat_arrival(null, 0);
//}
//
///****************************************************************
//...
//*****************************************************************/
//static void spy_sabotage_unit_callback(gpointer data)
//{
//  request_diplomat_action(SPY_SABOTAGE_UNIT, diplomat_id,
//			  diplomat_target_id, 0);
//}
//
///****************************************************************
//...
//*****************************************************************/
//static void diplomat_embassy_callback(gpointer data)
//{
//  diplomat_dialog_open=0;
//
//  if(Game.find_unit_by_id(diplomat_id) && 
//     (Game.find_city_by_id(diplomat_target_id))) { 
//    request_diplomat_action(DIPLOMAT_EMBASSY, diplomat_id,
//			    diplomat_target_id, 0);
//  }
//
//  process_diplomat_arrival(null, 0);
//}
//
///****************************************************************
//...
//*****************************************************************/
//static void spy_poison_callback(gpointer data)
//{
//  diplomat_dialog_open=0;
//
//  if(Game.find_unit_by_id(diplomat_id) &&
//     (Game.find_city_by_id(diplomat_target_id))) {
//    request_diplomat_action(SPY_POISON, diplomat_id, diplomat_target_id, 0);
//  }
//
//  process_diplomat_arrival(null, 0);
//}
//
///****************************************************************
//...
//*****************************************************************/
//static void diplomat_steal_callback(gpointer data)
//{
//  if(Game.find_unit_by_id(diplomat_id) && 
//     Game.find_city_by_id(diplomat_target_id)) { 
//    request_diplomat_action(DIPLOMAT_STEAL,
//			    diplomat_id, diplomat_target_id, 0);
//  }
//
//  process_diplomat_arrival(null, 0);
//}
//
///****************************************************************
//...
//*****************************************************************/
//static void spy_close_tech_callback(GtkWidget *w, gpointer data)
//{
//
//  if(spy_tech_shell_is_modal)
//     gtk_widget_set_sensitive(top_vbox, true);
//  gtk_widget_destroy(spy_tech_shell);
//  spy_tech_shell = null;
//
//  process_diplomat_arrival(null, 0);
//}
//
///****************************************************************
//...
//*****************************************************************/
//static void spy_close_sabotage_callback(GtkWidget *w, gpointer data)
//{
//
//  if(spy_sabotage_shell_is_modal)
//     gtk_widget_set_sensitive(top_vbox, true);
//  gtk_widget_destroy(spy_sabotage_shell);
//  spy_sabotage_shell = null;
//
//  process_diplomat_arrival(null, 0);
//}
//
///****************************************************************
//...
//*****************************************************************/
//static void spy_select_tech_callback(GtkWidget *w, gint row, gint column)
//{
//  if (advance_type[row] != -1){
//    steal_advance = advance_type[row];
//    gtk_widget_set_sensitive(spy_steal_command, true);
//    return;
//  }
//}
//
///****************************************************************
//...
//*****************************************************************/
//static void spy_uselect_tech_callback(GtkWidget *w, gint row, gint column)
//{
//  gtk_widget_set_sensitive(spy_steal_command, false);
//}
//
///****************************************************************
//...
//*****************************************************************/
//static void spy_select_improvement_callback(GtkWidget *w, gint row,
//					    gint column)
//{
//  sabotage_improvement = improvement_type[row];
//  gtk_widget_set_sensitive(spy_sabotage_command, true);
//}
//
///****************************************************************
//...
//*****************************************************************/
//static void spy_uselect_improvement_callback(GtkWidget *w, gint row,
//					     gint column)
//{
//  gtk_widget_set_sensitive(spy_sabotage_command, false);
//}
//
///****************************************************************
//...
//*****************************************************************/
//static void spy_steal_callback(gpointer data)
//{  
//  gtk_widget_destroy(spy_tech_shell);
//  spy_tech_shell = null;
//  
//  if(!steal_advance){
//    util.freelog(Log.LOG_ERROR, "Bug in spy steal tech code.");
//    process_diplomat_arrival(null, 0);
//    return;
//  }
//  
//  if(Game.find_unit_by_id(diplomat_id) && 
//     Game.find_city_by_id(diplomat_target_id)) { 
//    request_diplomat_action(DIPLOMAT_STEAL, diplomat_id,
//			    diplomat_target_id, steal_advance);
//  }
//
//  process_diplomat_arrival(null, 0);
//}
//
///****************************************************************
//...
//*****************************************************************/
//static void spy_steal_button_callback(GtkWidget * w, gpointer data)
//{
//  spy_steal_callback(data);
//}
//
///****************************************************************
//...
//*****************************************************************/
//static void spy_sabotage_callback(GtkWidget *w, gpointer data)
//{  
//  gtk_widget_destroy(spy_sabotage_shell);
//  spy_sabotage_shell = null;
//  
//  if(!sabotage_improvement){
//    util.freelog(Log.LOG_ERROR, "Bug in spy sabotage code");
//    process_diplomat_arrival(null, 0);
//    return;
//  }
//  
//  if(Game.find_unit_by_id(diplomat_id) && 
//     Game.find_city_by_id(diplomat_target_id)) { 
//    request_diplomat_action(DIPLOMAT_SABOTAGE, diplomat_id,
//			    diplomat_target_id, sabotage_improvement + 1);
//  }
//
//  process_diplomat_arrival(null, 0);
//}
//
///****************************************************************
//...
//*****************************************************************/
//static int create_advances_list(player pplayer,
//				player pvictim, boolean make_modal)
//{  
//  GtkWidget *close_command, *scrolled;
//  int i, j;
//  static final String title_[1] = { N"Select Advance to Steal" };
//  static gchar **title;
//  GtkAccelGroup *accel=gtk_accel_group_new();
//
//  if (!title) title = intl_slist(1, title_);
//  
//  spy_tech_shell = gtk_dialog_new();
//  gtk_window_set_title(GTK_WINDOW(spy_tech_shell),"Steal Technology");
//  gtk_window_set_position (GTK_WINDOW(spy_tech_shell), GTK_WIN_POS_MOUSE);
//  gtk_accel_group_attach(accel, GTK_OBJECT(spy_tech_shell));
//  
//  spy_advances_list = gtk_clist_new_with_titles(1, title);
//  gtk_clist_column_titles_passive(GTK_CLIST(spy_advances_list));
//
//  scrolled = gtk_scrolled_window_new(null,null);
//  gtk_container_add(GTK_CONTAINER(scrolled),spy_advances_list);
//
//  gtk_scrolled_window_set_policy(GTK_SCROLLED_WINDOW(scrolled),
//			GTK_POLICY_AUTOMATIC, GTK_POLICY_ALWAYS);
//  gtk_widget_set_usize( scrolled, 180, 250 );
//  gtk_box_pack_start( GTK_BOX( GTK_DIALOG(spy_tech_shell).vbox ),
//	scrolled, true, true, 0 );
//  gtk_clist_set_column_width(GTK_CLIST(spy_advances_list), 0,
//        GTK_CLIST(spy_advances_list).clist_window_width);
//
//  close_command = gtk_button_new_with_label("Close");
//  gtk_box_pack_start( GTK_BOX( GTK_DIALOG(spy_tech_shell).action_area ),
//	close_command, true, true, 0 );
//  gtk_widget_add_accelerator(close_command, "clicked", accel, GDK_Escape, 0, 0);
//
//  spy_steal_command = gtk_button_new_with_label("Steal");
//  gtk_widget_set_sensitive(spy_steal_command, false);
//  gtk_box_pack_start( GTK_BOX( GTK_DIALOG(spy_tech_shell).action_area ),
//	spy_steal_command, true, true, 0 );
//
//
//  gtk_signal_connect(GTK_OBJECT(spy_advances_list), "select_row",
//	GTK_SIGNAL_FUNC(spy_select_tech_callback), null);
//  gtk_signal_connect(GTK_OBJECT(spy_advances_list), "unselect_row",
//	GTK_SIGNAL_FUNC(spy_uselect_tech_callback), null);
//  gtk_signal_connect(GTK_OBJECT(close_command), "clicked",
//	GTK_SIGNAL_FUNC(spy_close_tech_callback), null);
//  gtk_signal_connect(GTK_OBJECT(spy_steal_command), "clicked",
//	GTK_SIGNAL_FUNC(spy_steal_button_callback), null);
//
//  /* Now populate the list */
//  gtk_clist_freeze(GTK_CLIST(spy_advances_list));
//
//  j = 0;
//  advance_type[j] = -1;
//
//  if (pvictim) { /* you don't want to know what lag can do -- Syela */
//    final gchar *row[1];
//
//    for(i=A_FIRST; i<Game.game.num_tech_types; i++) {
//      if(get_invention(pvictim, i)==TECH_KNOWN && 
//	 (get_invention(pplayer, i)==TECH_UNKNOWN || 
//	  get_invention(pplayer, i)==TECH_REACHABLE)) {
//
//	row[0] = advances[i].name;
//	gtk_clist_append(GTK_CLIST(spy_advances_list), (gchar **)row);
//        advance_type[j++] = i;
//      }
//    }
//
//    if(j > 0) {
//      row[0] = "At Spy's Discretion";
//      gtk_clist_append(GTK_CLIST(spy_advances_list), (gchar **)row);
//      advance_type[j++] = Game.game.num_tech_types;
//    }
//  }
//
//  if(j == 0) {
//    static final String row_[1] = { N"NONE" };
//    static gchar **row;
//    
//    if (!row) row = intl_slist(1, row_);
//  
//    gtk_clist_append(GTK_CLIST(spy_advances_list), row);
//    j++;
//  }
//  gtk_clist_thaw(GTK_CLIST(spy_advances_list));
//
//  gtk_widget_set_sensitive(spy_steal_command, false);
//  
//  gtk_widget_show_all(GTK_DIALOG(spy_tech_shell).vbox);
//  gtk_widget_show_all(GTK_DIALOG(spy_tech_shell).action_area);
//  return j;
//}
//
///****************************************************************
//...
//*****************************************************************/
//static int create_improvements_list(player pplayer,
//				    city pcity, boolean make_modal)
//{  
//  GtkWidget *close_command, *scrolled;
//  int j;
//  gchar *row[1];
//  static final String title_[1] = { N"Select Improvement to Sabotage" };
//  static gchar **title;
//  GtkAccelGroup *accel=gtk_accel_group_new();
//
//  if (!title) title = intl_slist(1, title_);
//  
//  spy_sabotage_shell = gtk_dialog_new();
//  gtk_window_set_title(GTK_WINDOW(spy_sabotage_shell),"Sabotage Improvements");
//  gtk_window_set_position (GTK_WINDOW(spy_sabotage_shell), GTK_WIN_POS_MOUSE);
//  gtk_accel_group_attach(accel, GTK_OBJECT(spy_sabotage_shell));
//  
//  spy_improvements_list = gtk_clist_new_with_titles(1, title);
//  gtk_clist_column_titles_passive(GTK_CLIST(spy_improvements_list));
//  scrolled = gtk_scrolled_window_new(null,null);
//  gtk_container_add(GTK_CONTAINER(scrolled), spy_improvements_list);
//
//  gtk_scrolled_window_set_policy(GTK_SCROLLED_WINDOW(scrolled),
//			GTK_POLICY_AUTOMATIC, GTK_POLICY_ALWAYS);
//  gtk_widget_set_usize( scrolled, 180, 250 );
//  gtk_box_pack_start( GTK_BOX( GTK_DIALOG(spy_sabotage_shell).vbox ),
//	scrolled, true, true, 0 );
//  gtk_clist_set_column_width(GTK_CLIST(spy_improvements_list), 0,
//        GTK_CLIST(spy_improvements_list).clist_window_width);
//
//  close_command = gtk_button_new_with_label("Close");
//  gtk_box_pack_start( GTK_BOX( GTK_DIALOG(spy_sabotage_shell).action_area ),
//	close_command, true, true, 0 );
//  gtk_widget_add_accelerator(close_command, "clicked", accel, GDK_Escape, 0, 0);
//  
//  spy_sabotage_command = gtk_button_new_with_label("Sabotage");
//  gtk_widget_set_sensitive(spy_sabotage_command, false);
//  gtk_box_pack_start( GTK_BOX( GTK_DIALOG(spy_sabotage_shell).action_area ),
//	spy_sabotage_command, true, true, 0 );
//  
//
//  gtk_signal_connect(GTK_OBJECT(spy_improvements_list), "select_row",
//	GTK_SIGNAL_FUNC(spy_select_improvement_callback), null);
//  gtk_signal_connect(GTK_OBJECT(spy_improvements_list), "unselect_row",
//	GTK_SIGNAL_FUNC(spy_uselect_improvement_callback), null);
//  gtk_signal_connect(GTK_OBJECT(close_command), "clicked",
//	GTK_SIGNAL_FUNC(spy_close_sabotage_callback), null);
//  gtk_signal_connect(GTK_OBJECT(spy_sabotage_command), "clicked",
//	GTK_SIGNAL_FUNC(spy_sabotage_callback), null);
//
//  /* Now populate the list */
//  gtk_clist_freeze(GTK_CLIST(spy_improvements_list));
//
//  j = 0;
//  row[0] = "City Production";
//  gtk_clist_append(GTK_CLIST(spy_improvements_list), row);
//  improvement_type[j++] = -1;
//
//	for (int i = 0; i < game.num_impr_types; i++) {
//	if((pcity).improvements[i] == Improvement.I_NONE) {
//		continue;
//	}
//    if (get_improvement_type(i).sabotage > 0) {
//      row[0] = (char *) City.get_impr_name_ex(pcity, i);
//      gtk_clist_append(GTK_CLIST(spy_improvements_list), row);
//      improvement_type[j++] = i;
//    }  
//  } ;
//
//  if(j > 1) {
//    row[0] = "At Spy's Discretion";
//    gtk_clist_append(GTK_CLIST(spy_improvements_list), row);
//    improvement_type[j++] = Improvement.B_LAST;
//  } else {
//    improvement_type[0] = Improvement.B_LAST; /* fake "discretion", since must be production */
//  }
//
//  gtk_clist_thaw(GTK_CLIST(spy_improvements_list));
//
//  gtk_widget_set_sensitive(spy_sabotage_command, false);
//
//  gtk_widget_show_all(GTK_DIALOG(spy_sabotage_shell).vbox);
//  gtk_widget_show_all(GTK_DIALOG(spy_sabotage_shell).action_area);
//  return j;
//}
//
///****************************************************************
//...
//*****************************************************************/
//static void spy_steal_popup(GtkWidget *w, gpointer data)
//{
//  city pvcity = Game.find_city_by_id(diplomat_target_id);
//  player pvictim = null;
//
//  if(pvcity)
//    pvictim = City.city_owner(pvcity);
//
///* it is concievable that pvcity will not be found, because something
//has happened to the city during latency.  Therefore we must initialize
//pvictim to null and account for !pvictim in create_advances_list. -- Syela */
//  
//  diplomat_dialog_open=0;
//
//  if(!spy_tech_shell){
//    spy_tech_shell_is_modal=1;
//
//    create_advances_list(Game.game.player_ptr, pvictim, spy_tech_shell_is_modal);
//    gtk_set_relative_position (toplevel, spy_tech_shell, 10, 10);
//
//    gtk_widget_show(spy_tech_shell);
//  }
//}
//
///****************************************************************
// Requests up-to-date list of improvements, the return of
// which will trigger the popup_sabotage_dialog() function.
//*****************************************************************/
//static void spy_request_sabotage_list(gpointer data)
//{
//  if(Game.find_unit_by_id(diplomat_id) &&
//     (Game.find_city_by_id(diplomat_target_id))) {
//    request_diplomat_action(SPY_GET_SABOTAGE_LIST, diplomat_id,
//			    diplomat_target_id, 0);
//  }
//}
//
///****************************************************************
// Pops-up the Spy sabotage dialog, upon return of list of
// available improvements requested by the above function.
//*****************************************************************/
//void popup_sabotage_dialog(city pcity)
//{
//  if(!spy_sabotage_shell){
//    spy_sabotage_shell_is_modal=1;
//
//    create_improvements_list(Game.game.player_ptr, pcity, spy_sabotage_shell_is_modal);
//    gtk_set_relative_position (toplevel, spy_sabotage_shell, 10, 10);
//
//    gtk_widget_show(spy_sabotage_shell);
//  }
//}
//
///****************************************************************
//...
//*****************************************************************/
//static void diplomat_incite_yes_callback(gpointer data)
//{
//  request_diplomat_action(DIPLOMAT_INCITE, diplomat_id,
//			  diplomat_target_id, 0);
//}
//
///****************************************************************
//...
//*****************************************************************/
//static void diplomat_incite_close_callback(gpointer data)
//{
//  process_diplomat_arrival(null, 0);
//}
//
///****************************************************************
//...  Ask the server how much the revolt is going to cost us
//*****************************************************************/
//static void diplomat_incite_callback(gpointer data)
//{
//  if (Game.find_unit_by_id(diplomat_id) && Game.find_city_by_id(diplomat_target_id)) {
//    dsend_packet_city_incite_inq(&aconnection, diplomat_target_id);
//  }
//}
//
///****************************************************************
//Popup the yes/no dialog for inciting, since we know the cost now
//*****************************************************************/
//void popup_incite_dialog(city pcity)
//{
//  char buf[128];
//
//  if (pcity.incite_revolt_cost == INCITE_IMPOSSIBLE_COST) {
//    buf = util.my_snprintf( "You can't incite a revolt in %s.",
//		pcity.name);
//    popup_message_dialog(top_vbox, "City can't be incited!", buf,
//			 diplomat_incite_close_callback, null,
//			 "Darn", null, 0, 0);
//  } else if (Game.game.player_ptr.economic.gold >= pcity.incite_revolt_cost) {
//    buf = util.my_snprintf(
//		"Incite a revolt for %d gold?\nTreasury contains %d gold.", 
//		pcity.incite_revolt_cost, Game.game.player_ptr.economic.gold);
//   popup_message_dialog(top_vbox, "Incite a Revolt!", buf,
//			diplomat_incite_close_callback, null,
//		       "_Yes", diplomat_incite_yes_callback, 0,
//		       "_No", null, 0, 0);
//  } else {
//    buf = util.my_snprintf(
//		("Inciting a revolt costs %d gold.\n" +
//		  "Treasury contains %d gold."), 
//		pcity.incite_revolt_cost, Game.game.player_ptr.economic.gold);
//   popup_message_dialog(top_vbox, "Traitors Demand Too Much!", buf,
//			diplomat_incite_close_callback, null,
//		       "Darn", null, 0, 
//		       0);
//  }
//}
//
//
///****************************************************************
//  Callback from diplomat/spy dialog for "keep moving".
//  (This should only occur when entering allied city.)
//*****************************************************************/
//static void diplomat_keep_moving_callback(gpointer data)
//{
//  unit punit;
//  city pcity;
//  
//  if( (punit=Game.find_unit_by_id(diplomat_id))
//      && (pcity=Game.find_city_by_id(diplomat_target_id))
//      && !Map.same_pos(punit.tile, pcity.tile)) {
//    request_diplomat_action(DIPLOMAT_MOVE, diplomat_id,
//			    diplomat_target_id, 0);
//  }
//  process_diplomat_arrival(null, 0);
//}
//
///****************************************************************
//...
//*****************************************************************/
//static void diplomat_close_callback(gpointer data)
//{
//  diplomat_dialog_open = 0;
//
//  process_diplomat_arrival(null, 0);
//}
//
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
//  diplomat_id=punit.id;
//
//  if((pcity=Map.map_get_city(dest_tile))){
//    /* Spy/Diplomat acting against a city */
//
//    diplomat_target_id=pcity.id;
//    buf = util.my_snprintf(
//		"Your %s has arrived at %s.\nWhat is your command?",
//		Unittype_P.unit_name(punit.type), pcity.name);
//
//    if(!unit_flag(punit, F_SPY)){
//      shl=popup_message_dialog(top_vbox, /*"diplomatdialog"*/
//			       " Choose Your Diplomat's Strategy", buf,
//			       diplomat_close_callback, null,
//         		     "Establish _Embassy", diplomat_embassy_callback, 0,
//         		     "_Investigate City", diplomat_investigate_callback, 0,
//         		     "_Sabotage City", diplomat_sabotage_callback, 0,
//         		     "Steal _Technology", diplomat_steal_callback, 0,
//         		     "Incite a _Revolt", diplomat_incite_callback, 0,
//         		     "_Keep moving", diplomat_keep_moving_callback, 0,
//         		     "_Cancel", null, 0,
//         		     0);
//      
//      if(!diplomat_can_do_action(punit, DIPLOMAT_EMBASSY, dest_tile))
//       message_dialog_button_set_sensitive(shl,"button0",false);
//      if(!diplomat_can_do_action(punit, DIPLOMAT_INVESTIGATE, dest_tile))
//       message_dialog_button_set_sensitive(shl,"button1",false);
//      if(!diplomat_can_do_action(punit, DIPLOMAT_SABOTAGE, dest_tile))
//       message_dialog_button_set_sensitive(shl,"button2",false);
//      if(!diplomat_can_do_action(punit, DIPLOMAT_STEAL, dest_tile))
//       message_dialog_button_set_sensitive(shl,"button3",false);
//      if(!diplomat_can_do_action(punit, DIPLOMAT_INCITE, dest_tile))
//       message_dialog_button_set_sensitive(shl,"button4",false);
//      if(!diplomat_can_do_action(punit, DIPLOMAT_MOVE, dest_tile))
//       message_dialog_button_set_sensitive(shl,"button5",false);
//    }else{
//       shl = popup_message_dialog(top_vbox, /*"spydialog"*/
//		"Choose Your Spy's Strategy", buf,
//		diplomat_close_callback, null,
// 		"Establish _Embassy", diplomat_embassy_callback, 0,
// 		"_Investigate City", diplomat_investigate_callback, 0,
// 		"_Poison City", spy_poison_callback,0,
// 		"Industrial _Sabotage", spy_request_sabotage_list, 0,
// 		"Steal _Technology", spy_steal_popup, 0,
// 		"Incite a _Revolt", diplomat_incite_callback, 0,
// 		"_Keep moving", diplomat_keep_moving_callback, 0,
// 		"_Cancel", null, 0,
//		0);
// 
//      if(!diplomat_can_do_action(punit, DIPLOMAT_EMBASSY, dest_tile))
//       message_dialog_button_set_sensitive(shl,"button0",false);
//      if(!diplomat_can_do_action(punit, DIPLOMAT_INVESTIGATE, dest_tile))
//       message_dialog_button_set_sensitive(shl,"button1",false);
//      if(!diplomat_can_do_action(punit, SPY_POISON, dest_tile))
//       message_dialog_button_set_sensitive(shl,"button2",false);
//      if(!diplomat_can_do_action(punit, DIPLOMAT_SABOTAGE, dest_tile))
//       message_dialog_button_set_sensitive(shl,"button3",false);
//      if(!diplomat_can_do_action(punit, DIPLOMAT_STEAL, dest_tile))
//       message_dialog_button_set_sensitive(shl,"button4",false);
//      if(!diplomat_can_do_action(punit, DIPLOMAT_INCITE, dest_tile))
//       message_dialog_button_set_sensitive(shl,"button5",false);
//      if(!diplomat_can_do_action(punit, DIPLOMAT_MOVE, dest_tile))
//       message_dialog_button_set_sensitive(shl,"button6",false);
//     }
//
//    diplomat_dialog_open=1;
//   }else{ 
//     if((ptunit=unit_list_get(&dest_tile.units, 0))){
//       /* Spy/Diplomat acting against a unit */ 
//       
//       diplomat_target_id=ptunit.id;
// 
//       shl=popup_message_dialog(top_vbox, /*"spybribedialog"*/"Subvert Enemy Unit",
//                              (!unit_flag(punit, F_SPY))?
// 			      "Sir, the diplomat is waiting for your command":
// 			      "Sir, the spy is waiting for your command",
//				diplomat_close_callback, null,
// 			      "_Bribe Enemy Unit", diplomat_bribe_callback, 0,
// 			      "_Sabotage Enemy Unit", spy_sabotage_unit_callback, 0,
// 			      "_Cancel", null, 0,
// 			      0);
//        
//       if(!diplomat_can_do_action(punit, DIPLOMAT_BRIBE, dest_tile))
//        message_dialog_button_set_sensitive(shl,"button0",false);
//       if(!diplomat_can_do_action(punit, SPY_SABOTAGE_UNIT, dest_tile))
//        message_dialog_button_set_sensitive(shl,"button1",false);
//    }
//  }
//}
//
///****************************************************************
//...
//*****************************************************************/
//boolean diplomat_dialog_is_open()
//{
//  return diplomat_dialog_open;
//}
//
///****************************************************************
//...
//*****************************************************************/
//static void caravan_establish_trade_callback(gpointer data)
//{
//  dsend_packet_unit_establish_trade(&aconnection, caravan_unit_id);
//}
//
//
///****************************************************************
//...
//*****************************************************************/
//static void caravan_help_build_wonder_callback(gpointer data)
//{
//  dsend_packet_unit_help_build_wonder(&aconnection, caravan_unit_id);
//}
//
///****************************************************************
//...
//*****************************************************************/
//static void caravan_close_callback(gpointer data)
//{
//  caravan_dialog = null;
//  process_caravan_arrival(null);
//}
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
//  can_trade = City.can_cities_trade(phomecity, pdestcity);
//  can_establish = can_trade
//		 && City.can_establish_trade_route(phomecity, pdestcity);
//  
//  caravan_dialog = popup_message_dialog(top_vbox,
//					"Your Caravan Has Arrived", buf,
//					caravan_close_callback, null,
//			   (can_establish ? "Establish _Traderoute" :
//  			   "Enter Marketplace"),caravan_establish_trade_callback, 0,
//			   "Help build _Wonder",caravan_help_build_wonder_callback, 0,
//			   "_Keep moving",null, 0,
//			   0);
//  
//  if (!can_trade) {
//    message_dialog_button_set_sensitive(caravan_dialog, "button0", false);
//  }
//  
//  if (!unit_can_help_build_wonder(punit, pdestcity)) {
//    message_dialog_button_set_sensitive(caravan_dialog, "button1", false);
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
//static void revolution_callback_yes(gpointer data)
//{
//  start_revolution();
//}
//
///****************************************************************
//...
//*****************************************************************/
//void popup_revolution_dialog()
//{
//  popup_message_dialog(top_vbox, /*"revolutiondialog"*/"Revolution!", 
//		       "You say you wanna revolution?",
//		       dummy_close_callback, null, 
//		       "_Yes",revolution_callback_yes, 0,
//		       "_No",null, 0, 
//		       0);
//}
//
///****************************************************************
//...
//*****************************************************************/
//static void pillage_callback(gpointer data)
//{
//  pillage_data pdata = data;
//  unit punit = Game.find_unit_by_id(pdata.unit_id);
//
//  if (!punit) {
//    return;
//  }
//
//  request_new_unit_activity_targeted(punit, ACTIVITY_PILLAGE, pdata.what);
//}
//
///****************************************************************
//...
//*****************************************************************/
//static void pillage_close_callback(gpointer data)
//{
//  pillage_data pillage_datas = data;
//
//  free(pillage_datas);
//}
//
///****************************************************************
//...
//*****************************************************************/
//void popup_pillage_dialog(unit punit,
//			  enum int may_pillage)
//{
//  /* +1 for cancel button */
//  int i, num = 1;
//  enum int tmp = may_pillage;
//  button_descr buttons;
//  pillage_data datas;
//
//  while (tmp != S_NO_SPECIAL) {
//    enum int what = get_preferred_pillage(tmp);
//
//    tmp &= (~(what | map_get_infrastructure_prerequisite(what)));
//    num++;
//  }
//  buttons = fc_malloc(sizeof(struct button_descr) * num);
//  datas = fc_malloc(sizeof(struct pillage_data) * num);
//
//  for (i = 0; i < num - 1; i++) {
//    enum int what = get_preferred_pillage(may_pillage);
//
//    datas[i].unit_id = punit.id;
//    datas[i].what = what;
//
//    buttons[i].text = get_special_name(what);
//    buttons[i].callback = pillage_callback;
//    buttons[i].data = &datas[i];
//    buttons[i].sensitive = true;
//
//    may_pillage &= (~(what | map_get_infrastructure_prerequisite(what)));
//  }
//  buttons[num - 1].text = "Cancel";
//  buttons[num - 1].callback = null;
//
//  base_popup_message_dialog(top_vbox, "What To Pillage",
//			    "Select what to pillage:",
//			    pillage_close_callback, datas, num, buttons);
//}
//
///****************************************************************
//...
//*****************************************************************/
//static void popup_mes_close(GtkWidget *dialog_shell)
//{
//  GtkWidget *parent =
//      gtk_object_get_data(GTK_OBJECT(dialog_shell), "parent");
//  void (*close_callback) (gpointer) =
//      (void (*)(gpointer)) gtk_object_get_data(GTK_OBJECT(dialog_shell),
//					       "close_callback");
//  gpointer close_callback_data =
//      gtk_object_get_data(GTK_OBJECT(dialog_shell), "close_callback_data");
//  button_descr buttons =
//      gtk_object_get_data(GTK_OBJECT(dialog_shell), "buttons");
//
//  gtk_widget_set_sensitive(parent, true);
//  gtk_widget_unref(parent);
//
//  if (close_callback) {
//    (*close_callback)(close_callback_data);
//  }
//
//  free(buttons);
//}
//
///****************************************************************
//...
//*****************************************************************/
//static gint popup_mes_del_callback(GtkWidget * widget, GdkEvent * event,
//				   gpointer data)
//{
//  GtkWidget *dialog_shell = GTK_WIDGET(data);
//  void (*close_callback) (gpointer) =
//      (void (*)(gpointer)) gtk_object_get_data(GTK_OBJECT(dialog_shell),
//					       "close_callback");
//
//  if (close_callback) {
//    popup_mes_close(dialog_shell);
//    return false;
//  } else {
//    return true;
//  }
//}
//
///****************************************************************
//...
//*****************************************************************/
//static void popup_mes_handle_callback(GtkWidget * widget, gpointer data)
//{
//  GtkWidget *dialog_shell = GTK_WIDGET(data);
//  int button =
//	GPOINTER_TO_INT(gtk_object_get_data(GTK_OBJECT(widget), "button"));
//  button_descr buttons =
//      gtk_object_get_data(GTK_OBJECT(dialog_shell), "buttons");
//
//  if (buttons[button].callback) {
//    (*buttons[button].callback)(buttons[button].data);
//  }
//
//  popup_mes_close(dialog_shell);
//
//  gtk_widget_destroy(dialog_shell);
//}
//
///****************************************************************
//...
//*****************************************************************/
//void message_dialog_button_set_sensitive(GtkWidget * shl, final String bname,
//					 boolean state)
//{
//  GtkWidget *button = gtk_object_get_data(GTK_OBJECT(shl), bname);
//  gtk_widget_set_sensitive(button, state);
//}
//
///****************************************************************
//  Displays a dialog which shows several textual choices. The parent
//  widget is deactivated during the time the dialog is shown.
//
//  If the user clicks on one of the buttons the corresponding callback
//  is called. If the close_callback is set it is called afterwards.
//
//  If close_callback is unset the dialog can't be closed with the X
//  button. If close_callback is set and the user closes the dialog with
//  the X button the close_callback is called.
//
//  The dialog is automatically destroyed after use (button click or X
//  button).
//*****************************************************************/
//GtkWidget *base_popup_message_dialog(GtkWidget * parent, 
//				     final String dialogname,
//				     final String text,
//				     void (*close_callback) (gpointer),
//				     gpointer close_callback_data,
//				     int num_buttons,
//				     final button_descr buttons)
//{
//  GtkWidget *dshell, *dlabel, *vbox;
//  GtkAccelGroup *accel = gtk_accel_group_new();
//  int i;
//
//  /* 
//   * To restore the sensitivity later we have to make sure that parent
//   * still exists via a reference.
//   */
//  gtk_widget_ref(parent);
//  gtk_widget_set_sensitive(parent, false);
//  
//  dshell=gtk_window_new(GTK_WINDOW_TOPLEVEL);
//  gtk_window_set_position (GTK_WINDOW(dshell), GTK_WIN_POS_MOUSE);
//  gtk_accel_group_attach(accel, GTK_OBJECT(dshell));
//
//  gtk_object_set_data(GTK_OBJECT(dshell), "close_callback",
//		      (gpointer) close_callback);
//  gtk_object_set_data(GTK_OBJECT(dshell), "close_callback_data",
//		      (gpointer) close_callback_data);
//
//  gtk_signal_connect(GTK_OBJECT(dshell), "delete_event",
//		     GTK_SIGNAL_FUNC(popup_mes_del_callback),
//		     (gpointer) dshell);
//  gtk_window_set_title( GTK_WINDOW(dshell), dialogname );
//
//  vbox = gtk_vbox_new(0,true);
//  gtk_container_add(GTK_CONTAINER(dshell),vbox);
//
//  gtk_container_border_width(GTK_CONTAINER(vbox),5);
//
//  dlabel = gtk_label_new(text);
//  gtk_box_pack_start( GTK_BOX( vbox ), dlabel, true, false, 0 );
//
//  gtk_object_set_data(GTK_OBJECT(dshell), "parent",(gpointer)parent);
//  gtk_object_set_data(GTK_OBJECT(dshell), "buttons", (gpointer) buttons);
//
//  for (i = 0; i < num_buttons; i++) {
//    GtkWidget *button;
//    char button_name[512];
//
//    button = gtk_accelbutton_new(buttons[i].text, accel);
//    gtk_box_pack_start(GTK_BOX(vbox), button, true, false, 0);
//
//    button_name = util.my_snprintf( "button%d", i);
//    gtk_object_set_data(GTK_OBJECT(dshell), button_name, button);
//
//    gtk_object_set_data(GTK_OBJECT(button), "button", GINT_TO_POINTER(i));
//    
//    gtk_signal_connect(GTK_OBJECT(button), "clicked",
//		       GTK_SIGNAL_FUNC(popup_mes_handle_callback),
//		       (gpointer) dshell);
//
//    gtk_widget_set_sensitive(GTK_WIDGET(button), buttons[i].sensitive);
//
//    if (i == 0) {
//      gtk_widget_grab_focus(button);
//    }
//  }
//  
//  gtk_widget_show_all( vbox );
//
//  gtk_widget_show(dshell);  
//
//  return dshell;
//}
//
///****************************************************************
// Wrapper for base_popup_message_dialog.
//
// See also message_dialog_button_set_sensitive.
//*****************************************************************/
//GtkWidget *popup_message_dialog(GtkWidget * parent, final String dialogname,
//				final String text,
//				void (*close_callback) (gpointer),
//				gpointer close_callback_data, ...)
//{
//  va_list args;
//  int i, num = 0;
//  button_descr buttons;
//
//  va_start(args, close_callback_data);
//  while (va_arg(args, char *)) {
//    () va_arg(args, void *);
//    () va_arg(args, gpointer);
//    num++;
//  }
//  va_end(args);
//
//  buttons = fc_malloc(sizeof(struct button_descr) * num);
//
//  va_start(args, close_callback_data);
//  for (i = 0; i < num; i++) {
//    buttons[i].text = va_arg(args, char *);
//    buttons[i].callback = va_arg(args, void *);
//    buttons[i].data = va_arg(args, gpointer);
//    buttons[i].sensitive = true;
//  }
//  va_end(args);
//
//  return base_popup_message_dialog(parent, dialogname, text, close_callback,
//				   close_callback_data, num, buttons);
//}
//
///**************************************************************************
//...
//**************************************************************************/
//static void unit_select_all_callback(GtkWidget *w, gpointer data)
//{
//  int i;
//
//  gtk_widget_set_sensitive(top_vbox, true);
//  gtk_widget_destroy(unit_select_dialog_shell);
//  unit_select_dialog_shell = null;
//  
//  for(i=0; i<unit_select_no; i++) {
//    unit punit = Player_P.player_find_unit_by_id(Game.game.player_ptr,
//						unit_select_ids[i]);
//    if(punit) {
//      set_unit_focus(punit);
//    }
//  }
//}
//
///**************************************************************************
//...
//**************************************************************************/
//static void unit_select_callback(GtkWidget *w, int id)
//{
//  unit punit = Player_P.player_find_unit_by_id(Game.game.player_ptr, id);
//
//  if (punit) {
//    set_unit_focus(punit);
//  }
//
//  gtk_widget_set_sensitive(top_vbox, true);
//  gtk_widget_destroy(unit_select_dialog_shell);
//  unit_select_dialog_shell = null;
//}
//
//static int number_of_columns(int n)
//{
//#if 0
//  /* This would require libm, which isn't worth it for this one little
//   * function.  Since MAX_SELECT_UNITS is 100 already, the ifs
//   * work fine.  */
//  double sqrt(); double ceil();
//  return ceil(sqrt((double)n/5.0));
//#else
//  assert(MAX_SELECT_UNITS == 100);
//  if(n<=5) return 1;
//  else if(n<=20) return 2;
//  else if(n<=45) return 3;
//  else if(n<=80) return 4;
//  else return 5;
//#endif
//}
//static int number_of_rows(int n)
//{
//  int c=number_of_columns(n);
//  return (n+c-1)/c;
//}
//
///****************************************************************
//popup the dialog 10% inside the main-window 
//*****************************************************************/
//void popup_unit_select_dialog(tile ptile)
//{
//  int i,n,r;
//  char buffer[512];
//  GtkWidget *pix, *hbox, *table;
//  GtkWidget *unit_select_all_command, *unit_select_close_command;
//  boolean can_ready = false;
//  unit unit_list[ptile.units.foo_list_size()];
//
//  if (!unit_select_dialog_shell){
//  gtk_widget_set_sensitive(top_vbox, false);
//
//  unit_select_dialog_shell = gtk_dialog_new();
//  gtk_signal_connect(GTK_OBJECT(unit_select_dialog_shell), "delete_event",
//		     unit_select_callback, null);
//  gtk_window_set_position (GTK_WINDOW(unit_select_dialog_shell), GTK_WIN_POS_MOUSE);
//
//  gtk_window_set_title(GTK_WINDOW(unit_select_dialog_shell),
//	"Unit selection" );
//
//  n = Math.min(MAX_SELECT_UNITS, ptile.units.foo_list_size());
//  r = number_of_rows(n);
//
//  table=gtk_table_new(r, number_of_columns(n), false);
//  gtk_container_add(GTK_CONTAINER(GTK_DIALOG(unit_select_dialog_shell).vbox),
//	table);  
//
//  fill_tile_unit_list(ptile, unit_list);
//
//  for(i=0; i<n; i++) {
//    unit punit = unit_list[i];
//    unit_type punittemp=punit.unit_type();
//    city pcity;
//
//    /* The "Ready all" button is activated if any units are owned by us. */
//    can_ready = can_ready || (punit.unit_owner() == Game.game.player_ptr);
//
//    hbox = gtk_hbox_new(false,10);
//    gtk_table_attach_defaults(GTK_TABLE(table), hbox, 
//				(i/r), (i/r)+1,
//				(i%r), (i%r)+1);
//    gtk_container_border_width(GTK_CONTAINER(hbox),5);
//
//    unit_select_ids[i]=punit.id;
//
//    pcity=Player_P.player_find_city_by_id(Game.game.player_ptr, punit.homecity);
//
//    if (pcity) {
//      buffer = util.my_snprintf( "%s (%s)\n%s",
//		  punittemp.name, pcity.name, unit_activity_text(punit));
//    } else {
//      buffer = util.my_snprintf( "%s\n%s",
//		  punittemp.name, unit_activity_text(punit));
//    }
//
//    pix = gtk_pixcomm_new(root_window, UNIT_TILE_WIDTH, UNIT_TILE_HEIGHT);
//
//    unit_select_commands[i]=gtk_button_new();
//    gtk_widget_set_sensitive(unit_select_commands[i],
//       can_unit_do_activity(punit, unit_activity.ACTIVITY_IDLE) );
//    gtk_widget_set_usize(unit_select_commands[i],
//			 UNIT_TILE_WIDTH+4, UNIT_TILE_HEIGHT+4);
//    gtk_container_add(GTK_CONTAINER(unit_select_commands[i]), pix);
//    gtk_box_pack_start(GTK_BOX(hbox),unit_select_commands[i],
//       false, false, 0);
//
//    gtk_pixcomm_clear(GTK_PIXCOMM(pix), false);
//    put_unit_gpixmap(punit, GTK_PIXCOMM(pix));
//
//    unit_select_labels[i]=gtk_label_new(buffer);
//    gtk_box_pack_start(GTK_BOX(hbox),unit_select_labels[i],
//       false, false, 0);
//
//    gtk_signal_connect(GTK_OBJECT(unit_select_commands[i]), "clicked",
//       GTK_SIGNAL_FUNC(unit_select_callback), GINT_TO_POINTER(punit.id));
//  }
//  unit_select_no=i;
//
//
//  unit_select_close_command=gtk_button_new_with_label("Close");
//  gtk_box_pack_start( GTK_BOX(GTK_DIALOG(unit_select_dialog_shell).action_area),
//	unit_select_close_command, true, true, 0 );
//  GTK_WIDGET_SET_FLAGS( unit_select_close_command, GTK_CAN_DEFAULT );
//  gtk_widget_grab_default( unit_select_close_command );
//
//  unit_select_all_command=gtk_button_new_with_label("Ready all");
//  gtk_box_pack_start( GTK_BOX(GTK_DIALOG(unit_select_dialog_shell).action_area),
//	unit_select_all_command, true, true, 0 );
//  GTK_WIDGET_SET_FLAGS( unit_select_all_command, GTK_CAN_DEFAULT );
//  gtk_widget_set_sensitive(unit_select_all_command, can_ready);
//
//  gtk_signal_connect(GTK_OBJECT(unit_select_close_command), "clicked",
//	GTK_SIGNAL_FUNC(unit_select_callback), null);
//  gtk_signal_connect(GTK_OBJECT(unit_select_all_command), "clicked",
//	GTK_SIGNAL_FUNC(unit_select_all_callback), null);
//
//  gtk_widget_show_all( GTK_DIALOG(unit_select_dialog_shell).vbox );
//  gtk_widget_show_all( GTK_DIALOG(unit_select_dialog_shell).action_area );
//
//  gtk_set_relative_position(toplevel, unit_select_dialog_shell, 15, 10);
//  gtk_widget_show(unit_select_dialog_shell);
//  }
//}
//
//
///****************************************************************
//popup the dialog 10% inside the main-window 
//*****************************************************************/
//void popup_races_dialog()
//{
//  int class_id;
//  int width, height;
//
//  gtk_widget_set_sensitive(top_vbox, false);
//
//  create_races_dialog();
//
//  gtk_widget_show(races_dialog_shell);
//
//  /* The first tab is visible. The others aren't. */
//  width = legend_frame[0].allocation.width;
//  height = legend_frame[0].allocation.height;
//
//  /* 
//   * This is a hack to expand the legend label to take all the
//   * available space. 
//   */
//  for (class_id = 0; class_id < num_classes; class_id++) {
//    gtk_widget_set_usize(legend[class_id], width, -1);
//  }
//}
//
///****************************************************************
//...
//*****************************************************************/
//void popdown_races_dialog()
//{
//  if (races_dialog_shell) {
//    int class_id;
//
//    gtk_widget_set_sensitive(top_vbox, true);
//
//    /* 
//     * While dialog is being destroyed, it will toggle the race_toggle
//     * buttons in turn. eventually races_by_name_callback() will call
//     * select_random_race() will try to activate a now-destroyed
//     * button with typical results. Let's avoid that. (this is one of
//     * the reasons I hate GTK).
//     */
//
//    gtk_signal_disconnect_by_func(GTK_OBJECT(notebook),
//				  GTK_SIGNAL_FUNC(switch_page_callback),
//				  null);
//
//    for (class_id = 0; class_id < num_classes; class_id++) {
//      gtk_signal_disconnect_by_func(GTK_OBJECT
//				    (GTK_COMBO(races_by_name[class_id]).
//				     list),
//				    GTK_SIGNAL_FUNC(races_by_name_callback),
//				    null);
//
//      g_list_free(sorted_races_list[class_id]);
//      sorted_races_list[class_id] = null;
//      free(races_toggles[class_id]);
//      races_toggles[class_id] = null;
//    }
//    gtk_widget_destroy(races_dialog_shell);
//    races_dialog_shell = null;
//  }
//}
//
///****************************************************************
//...
//*****************************************************************/
//static gint cmp_func(gfinalpointer a_p, gfinalpointer b_p)
//{
//  return strcmp(get_nation_name(GPOINTER_TO_INT(a_p)),
//		get_nation_name(GPOINTER_TO_INT(b_p)));
//}
//
///****************************************************************
//Selectes a leader and the appropriate sex.
//Updates the gui elements and the selected_* variables.
//*****************************************************************/
//static void select_random_leader()
//{
//  int j, leader_num;
//  leader leaders;
//  String unique_name;
//  
//  /* weirdness happens by not doing it this way */
//  unique_name = String.format( 
//             gtk_entry_get_text(GTK_ENTRY(GTK_COMBO(leader_name).entry)));
//
//  gtk_signal_handler_block_by_func(GTK_OBJECT(GTK_COMBO(leader_name).list), 
//                                   leader_name_callback, null);
//  g_list_free(leader_strings);
//  leader_strings = null;
//
//  /* fill leader names combo box */
//  leaders = get_nation_leaders(selected_nation, &leader_num);
//  for(j = 0; j < leader_num; j++) {
//    leader_strings = g_list_append(leader_strings, leaders[j].name);
//  }
//  gtk_combo_set_value_in_list(GTK_COMBO(leader_name), false, false);
//  gtk_combo_set_popdown_strings(GTK_COMBO(leader_name), leader_strings);
//
//  gtk_signal_handler_unblock_by_func(GTK_OBJECT(GTK_COMBO(leader_name).list), 
//                                     leader_name_callback, null);
//  if (!is_name_unique) {
//    /* initialize leader names */
//    selected_leader = Rand.myrand(leader_num);
//    gtk_entry_set_text(GTK_ENTRY(GTK_COMBO(leader_name).entry),
//		       leaders[selected_leader].name);
//
//    /* initialize leader sex */
//    selected_sex = leaders[selected_leader].is_male;
//    gtk_toggle_button_set_state(GTK_TOGGLE_BUTTON(
//                              races_sex_toggles[selected_sex ? 0 : 1]), true);
//  } else {
//    gtk_entry_set_text(GTK_ENTRY(GTK_COMBO(leader_name).entry), unique_name);
//  }
//}
//
///****************************************************************
//Selectes a random race and the appropriate city style.
//Updates the gui elements and the selected_* variables.
//*****************************************************************/
//static void select_random_race()
//{
//  int class_id = selected_class;
//  int nations_in_class = g_list_length(sorted_races_list[class_id]);
//  int index;
//  int tries = 0;
//  
//  /* try to find a free nation */
//  while (true) {
//    index = Rand.myrand(nations_in_class);
//    selected_nation =
//	GPOINTER_TO_INT(g_list_nth_data(sorted_races_list[class_id], index));
//    if (GTK_WIDGET_SENSITIVE(races_toggles[class_id][index])) {
//      break;
//    }
//    if (tries++ > 1000) return;
//  }
//
//  /* initialize nation toggle array */
//  gtk_toggle_button_set_state(GTK_TOGGLE_BUTTON
//			      (races_toggles[class_id][index]), true);
//
//  /* initialize city style */
//  selected_city_style =
//                      city_style_ridx[get_nation_city_style(selected_nation)];
//  gtk_toggle_button_set_state(GTK_TOGGLE_BUTTON(
//			city_style_toggles[selected_city_style]), true);
//}
//
///****************************************************************
//...
//*****************************************************************/
//static void switch_page_callback(GtkNotebook * notebook,
//				 GtkNotebookPage * page, gint page_num,
//				 gpointer data)
//{
//  selected_class = page_num;
//  select_random_race();
//}
//
///****************************************************************
//...
//*****************************************************************/
//void create_races_dialog()
//{
//  int       i, class_id;
//  GSList    *sgroup = null;
//  GSList    *cgroup = null;
//  GtkWidget *frame, *label;
// 
//  races_dialog_shell = gtk_dialog_new();
//  gtk_window_set_default_size(GTK_WINDOW(races_dialog_shell), 10, 650);
//  
//  gtk_signal_connect(GTK_OBJECT(races_dialog_shell), "delete_event", 
//                     GTK_SIGNAL_FUNC(deleted_callback), null);
//
//  gtk_window_set_title(GTK_WINDOW(races_dialog_shell), 
//                       "What Nation Will You Be?");
//
//  frame = gtk_frame_new("Select a nation");
//  gtk_box_pack_start(GTK_BOX(GTK_DIALOG(races_dialog_shell).vbox),
//                     frame, true, true, 0);
//
//  /* ------- Add each nation to one of the class lists ------- */
//
//  num_classes = 1;
//  class_names[0] = "All";
//
//  for (i = 0; i < Game.game.playable_nation_count; i++) {
//    boolean found = false;
//    nation_type nation = Nation.get_nation_by_idx(i);
//
//    /* Find the nation's class. */
//    for (class_id = 1; class_id < num_classes; class_id++) {
//      if (strcmp(nation.class, class_names[class_id]) == 0) {
//	found = true;
//	break;
//      }
//    }
//
//    /* Append a new class. */
//    if (!found && num_classes < Shared_H.MAX_NUM_ITEMS) {
//      class_id = num_classes++;
//      class_names[class_id] = nation.class;
//    }
//
//    /* Add the nation to the class list. */
//    sorted_races_list[class_id] =
//      g_list_append(sorted_races_list[class_id], GINT_TO_POINTER(i));
//
//    /* Add the nation to the "All" class. */
//    sorted_races_list[0] =
//	g_list_append(sorted_races_list[0], GINT_TO_POINTER(i));
//  }
//
//  /* ------- create class notebook and add pages ------- */
//
//  notebook = gtk_notebook_new();
//  gtk_notebook_set_tab_pos(GTK_NOTEBOOK(notebook), GTK_POS_TOP);
//  gtk_container_add(GTK_CONTAINER(frame), notebook);
//
//  for (class_id = 0; class_id < num_classes; class_id++) {
//    GtkWidget *page, *label, *hbox, *scrolledwin;
//    int nations_in_class = g_list_length(sorted_races_list[class_id]);
//    int per_row, rows;
//    GList *race_names = null;
//    GSList *group = null;
//
//    util.freelog(Log.LOG_DEBUG, "  %s[%d] has %d nations",
//	    skip_intl_qualifier_prefix(class_names[class_id]), class_id,
//	    nations_in_class);
//    sorted_races_list[class_id] =
//	g_list_sort(sorted_races_list[class_id], cmp_func);
//
//    for (i = 0; i < nations_in_class; i++) {
//      race_names =
//	  g_list_append(race_names,
//		(gchar *)Nation.get_nation_by_idx(GPOINTER_TO_INT(g_list_nth_data
//			(sorted_races_list[class_id], i))).name);
//    }
//
//    per_row = 8;
//
//    if (nations_in_class == 0) {
//      rows = 0;
//    } else {
//      rows = ((nations_in_class - 1) / per_row) + 1;
//    }
//
//    page = gtk_vbox_new(false, 1);
//    label = gtk_label_new(Q_(class_names[class_id]));
//    gtk_notebook_append_page(GTK_NOTEBOOK(notebook), page, label);
//
//    scrolledwin = gtk_scrolled_window_new(null, null);
//    gtk_box_pack_start(GTK_BOX(page), scrolledwin,1,1,0);
//    gtk_scrolled_window_set_policy(GTK_SCROLLED_WINDOW(scrolledwin),
//				   GTK_POLICY_NEVER, GTK_POLICY_AUTOMATIC);
//
//    races_toggles_form[class_id] = gtk_table_new(per_row, rows, false);
//    gtk_scrolled_window_add_with_viewport(GTK_SCROLLED_WINDOW (scrolledwin),
//					  races_toggles_form[class_id]);
//
//    races_toggles[class_id] =
//	fc_calloc(nations_in_class, sizeof(GtkWidget *));
//
//    /* ------ add nation flag array to page ------ */
//
//    for (i = 0; i < g_list_length(sorted_races_list[class_id]); i++) {
//      gint nat_id =
//	  GPOINTER_TO_INT(g_list_nth_data(sorted_races_list[class_id], i));
//      SPRITE *s = crop_blankspace(Nation.get_nation_by_idx(nat_id).flag_sprite);
//      GtkWidget *flag = gtk_pixmap_new(s.pixmap, s.mask);
//
//      races_toggles[class_id][i] = gtk_radio_button_new(group);
//      gtk_misc_set_alignment(GTK_MISC(flag), 0, 0.5);
//      gtk_misc_set_padding(GTK_MISC(flag), 6, 4);
//
//      gtk_container_add(GTK_CONTAINER(races_toggles[class_id][i]), flag);
//      gtk_toggle_button_set_state(GTK_TOGGLE_BUTTON
//				  (races_toggles[class_id][i]), false);
//
//      group =
//	  gtk_radio_button_group(GTK_RADIO_BUTTON
//				 (races_toggles[class_id][i]));
//      gtk_table_attach_defaults(GTK_TABLE(races_toggles_form[class_id]),
//				races_toggles[class_id][i], i % per_row,
//				i % per_row + 1, i / per_row,
//				i / per_row + 1);
//    }
//
//    /* ------ add combobox to choose nation by name to page ------ */
//
//    hbox = gtk_hbox_new(false, 0);
//    gtk_box_pack_start(GTK_BOX(page), hbox, false, false, 5);
//
//    label = gtk_label_new("Nation:");
//    races_by_name[class_id] = gtk_combo_new();
//    gtk_editable_set_editable(GTK_EDITABLE
//			      (GTK_COMBO(races_by_name[class_id]).entry),
//			      false);
//
//    gtk_combo_set_popdown_strings(GTK_COMBO(races_by_name[class_id]),
//				  race_names);
//    gtk_combo_set_value_in_list(GTK_COMBO(races_by_name[class_id]), true,
//				false);
//    gtk_box_pack_start(GTK_BOX(hbox), label, false, false, 4);
//    gtk_box_pack_start(GTK_BOX(hbox), races_by_name[class_id],
//		       false, false, 0);
//
//    /* ------ add info about class and legend to page ------ */
//
//    hbox = gtk_hbox_new(false, 0);
//    gtk_box_pack_start(GTK_BOX(page), hbox, false, false, 5);
//    label = gtk_label_new("Class:");
//    gtk_box_pack_start(GTK_BOX(hbox), label, false, false, 0);
//    class[class_id] = gtk_label_new("content");
//    gtk_box_pack_start(GTK_BOX(hbox), class[class_id], false, false, 0);
//
//    hbox = gtk_hbox_new(false, 0);
//    gtk_box_pack_start(GTK_BOX(page), hbox, false, false, 5);
//    legend[class_id] = gtk_label_new("content");
//    gtk_label_set_line_wrap(GTK_LABEL(legend[class_id]), true);
//    gtk_label_set_justify(GTK_LABEL(legend[class_id]), GTK_JUSTIFY_FILL);
//
//    legend_frame[class_id] = gtk_frame_new("Description");
//    gtk_box_pack_start(GTK_BOX(hbox), legend_frame[class_id], true, true, 0);
//    gtk_container_add(GTK_CONTAINER(legend_frame[class_id]), legend[class_id]);
//    /* ------- contruction of one page finished ------- */
//  }
//
//  selected_class = 0;
//
//  /* ------- leader sex toggles ------- */
//
//  frame = gtk_frame_new("Leader");
//  gtk_box_pack_start(GTK_BOX(GTK_DIALOG(races_dialog_shell).vbox),
//                     frame, false, false, 0);
//
//  races_sex_toggles_form = gtk_hbox_new(false, 0);
//  gtk_container_add(GTK_CONTAINER(frame), races_sex_toggles_form); 
//
//  races_sex_toggles[0] = gtk_radio_button_new_with_label(sgroup, "Male");
//  gtk_toggle_button_set_state(GTK_TOGGLE_BUTTON( races_sex_toggles[0]),
//                              false);
//  sgroup = gtk_radio_button_group(GTK_RADIO_BUTTON(races_sex_toggles[0]));
//  gtk_box_pack_end(GTK_BOX(races_sex_toggles_form), races_sex_toggles[0],
//		   false, false, 0);
//  races_sex_toggles[1] = gtk_radio_button_new_with_label(sgroup, "Female");
//  gtk_toggle_button_set_state(GTK_TOGGLE_BUTTON(races_sex_toggles[1]),
//                              false);
//  sgroup = gtk_radio_button_group(GTK_RADIO_BUTTON(races_sex_toggles[1]));
//  gtk_box_pack_end(GTK_BOX(races_sex_toggles_form), races_sex_toggles[1],
//		   false, false, 0);
//  leader_name = gtk_combo_new();
//
//  label = gtk_label_new("Leader:");
//  gtk_box_pack_start(GTK_BOX(races_sex_toggles_form), label, false, false,
//		     0);
//  gtk_box_pack_start(GTK_BOX(races_sex_toggles_form), leader_name, false,
//		     false, 4);
// 
//  GTK_WIDGET_SET_FLAGS(leader_name, GTK_CAN_DEFAULT);
//  gtk_widget_grab_default(leader_name);
//
//  /* ------- city style toggles ------- */
//
//  /* find out styles that can be used at the Game.game beginning */
//   
//  for(i = 0, b_s_num = 0; i < Game.game.styles_count && i < 64; i++) {
//    if(city_styles[i].techreq == A_NONE) {
//      city_style_idx[b_s_num] = i;
//      city_style_ridx[i] = b_s_num;
//      b_s_num++;
//    }
//  }
//
//  free(city_style_toggles);
//  city_style_toggles = fc_calloc(b_s_num, sizeof(struct GtkWidget*));
//  
//  frame = gtk_frame_new("Select your city style");
//  gtk_box_pack_start(GTK_BOX(GTK_DIALOG(races_dialog_shell).vbox),
//                     frame, false, false, 0);
//
//  city_style_toggles_form = gtk_table_new(1, b_s_num, true);
//  gtk_container_add(GTK_CONTAINER(frame), city_style_toggles_form); 
//
//  for(i = 0; i < b_s_num; i++) {
//    GtkWidget *box, *sub_box;
//    SPRITE *s;
//
//    city_style_toggles[i] = gtk_radio_button_new(cgroup);
//    box = gtk_vbox_new(false, 0);
//    gtk_container_add(GTK_CONTAINER(city_style_toggles[i]), box);
//    gtk_box_pack_start(GTK_BOX(box),
//                       gtk_label_new(get_city_style_name(city_style_idx[i])),
//                       false, false, 4);
//    sub_box = gtk_hbox_new(false, 0);
//    gtk_container_add(GTK_CONTAINER(box), sub_box);
//    s = crop_blankspace(sprites.city.tile[i][0]);
//    gtk_box_pack_start(GTK_BOX(sub_box), gtk_pixmap_new(s.pixmap, s.mask),
// 		       false, false, 4);
//    if ((s.width < 80) && (city_styles[i].tiles_num > 1)){
//      s = crop_blankspace(sprites.city.tile[i][1]);
//      gtk_box_pack_start(GTK_BOX(sub_box), gtk_pixmap_new(s.pixmap, s.mask),
// 			 false, false, 4);
//    }
//    if ((s.width < 40) && (city_styles[i].tiles_num > 2)){
//      s = crop_blankspace(sprites.city.tile[i][2]);
//      gtk_box_pack_start(GTK_BOX(sub_box), gtk_pixmap_new(s.pixmap, s.mask),
// 			 false, false, 4);
//    }
//
//    gtk_toggle_button_set_state(GTK_TOGGLE_BUTTON(city_style_toggles[i]), 
//                                false);
//    cgroup = gtk_radio_button_group(GTK_RADIO_BUTTON(city_style_toggles[i]));
//    gtk_table_attach_defaults( GTK_TABLE(city_style_toggles_form), 
// 			       city_style_toggles[i],
// 			       i, i+1, 0, 1);
//  }
//
//  /* ------- OK/Disc/Quit buttons ------- */
//
//  races_ok_command = gtk_button_new_with_label("Ok");
//  GTK_WIDGET_SET_FLAGS(races_ok_command, GTK_CAN_DEFAULT);
//  gtk_box_pack_start(GTK_BOX(GTK_DIALOG(races_dialog_shell).action_area),
//                     races_ok_command, true, true, 0);
//
//  races_disc_command = gtk_button_new_with_label("Disconnect");
//  GTK_WIDGET_SET_FLAGS(races_disc_command, GTK_CAN_DEFAULT);
//  gtk_box_pack_start(GTK_BOX( GTK_DIALOG(races_dialog_shell).action_area),
//		     races_disc_command, true, true, 0);
//
//  races_quit_command = gtk_button_new_with_label("Quit");
//  GTK_WIDGET_SET_FLAGS(races_quit_command, GTK_CAN_DEFAULT);
//  gtk_box_pack_start(GTK_BOX( GTK_DIALOG(races_dialog_shell).action_area),
//		      races_quit_command, true, true, 0);
//
//  /* ------- connect callback functions ------- */
//
//  for (class_id = 0; class_id < num_classes; class_id++) {
//    for (i = 0; i < g_list_length(sorted_races_list[class_id]); i++) {
//      gtk_signal_connect(GTK_OBJECT(races_toggles[class_id][i]), "toggled",
//			 GTK_SIGNAL_FUNC(races_toggles_callback),
//			 g_list_nth_data(sorted_races_list[class_id], i));
//    }
//
//    gtk_signal_connect(GTK_OBJECT(GTK_COMBO(races_by_name[class_id]).list),
//		       "selection_changed",
//		       GTK_SIGNAL_FUNC(races_by_name_callback), null);
//  }
//
//  for(i = 0; i < 2; i++) {
//    gtk_signal_connect(GTK_OBJECT(races_sex_toggles[i]), "toggled",
//                       GTK_SIGNAL_FUNC(races_sex_toggles_callback), null);
//  }
//
//  for(i = 0; i < b_s_num; i++) {
//     gtk_signal_connect(GTK_OBJECT(city_style_toggles[i]), "toggled",
//	                GTK_SIGNAL_FUNC(city_style_toggles_callback), null);
//  }
//
//  gtk_signal_connect(GTK_OBJECT( GTK_COMBO(leader_name).list), 
//		      "selection_changed",
//		      GTK_SIGNAL_FUNC(leader_name_callback), null);
//
//  gtk_signal_connect(GTK_OBJECT(races_ok_command), "clicked",
//			GTK_SIGNAL_FUNC(races_buttons_callback), null);
//
//  gtk_signal_connect(GTK_OBJECT(races_disc_command), "clicked",
//		      GTK_SIGNAL_FUNC(races_buttons_callback), null);
//
//  gtk_signal_connect(GTK_OBJECT(races_quit_command), "clicked",
//		      GTK_SIGNAL_FUNC(races_buttons_callback), null);
//
//  gtk_signal_connect(GTK_OBJECT(notebook), "switch-page",
//		     GTK_SIGNAL_FUNC(switch_page_callback), null);
//
// 
//  /* ------- set initial selections ------- */
//
//  select_random_race();
//  select_random_leader();
//
//  gtk_widget_grab_default(races_ok_command);
//
//  gtk_widget_show_all(GTK_DIALOG(races_dialog_shell).vbox);
//  gtk_widget_show_all(GTK_DIALOG(races_dialog_shell).action_area);
//}
//
///**************************************************************************
//...
//**************************************************************************/ 
//static void races_by_name_callback(GtkWidget * w, gpointer data)
//{
//  int i, class_id = selected_class;
//  char *chosen =
//      gtk_entry_get_text(GTK_ENTRY(GTK_COMBO(races_by_name[class_id]).
//				   entry));
//
//  for (i = 0; i < g_list_length(sorted_races_list[class_id]); i++) {
//    if (strcmp(chosen,
//	       Nation.get_nation_by_idx(GPOINTER_TO_INT
//				 (g_list_nth_data
//				  (sorted_races_list[class_id],
//				   i))).name) == 0) {
//      if (GTK_WIDGET_SENSITIVE(races_toggles[class_id][i])) {
//	gtk_toggle_button_set_state(GTK_TOGGLE_BUTTON
//				    (races_toggles[class_id][i]), true);
//	break;
//      } else {
//	/* That one's taken */
//	select_random_race();
//      }
//    }
//  }
//} 
// 
///**************************************************************************
// ...
//**************************************************************************/
//void races_toggles_set_sensitive(boolean *nations_used)
//{
//  int i, class_id;
//
//  for (class_id = 0; class_id < num_classes; class_id++) {
//    int nations_in_class = g_list_length(sorted_races_list[class_id]);
//
//    for (i = 0; i < nations_in_class; i++) {
//      gtk_widget_set_sensitive(races_toggles[class_id][i], true);
//    }
//
//    for (i = 0; i < Game.game.playable_nation_count; i++) {
//      if (nations_used[i]) {
//	int index =
//	  g_list_index(sorted_races_list[class_id], GINT_TO_POINTER(i));
//
//	if (index != -1) {
//	  gtk_widget_set_sensitive(races_toggles[class_id][index], false);
//	}
//      }
//    }
//  }
//
//  if (nations_used[selected_nation]) {
//    select_random_race();
//  }
//}
//
///**************************************************************************
//...
//**************************************************************************/
//static void leader_name_callback(GtkWidget *w, gpointer data)
//{
//  int nation = races_buttons_get_current();
//  final String leader =
//                  gtk_entry_get_text(GTK_ENTRY(GTK_COMBO(leader_name).entry));
//
//  if (check_nation_leader_name(nation, leader)) {
//    is_name_unique = false;
//    selected_sex = get_nation_leader_sex(nation, leader);
//    gtk_toggle_button_set_state(
//           GTK_TOGGLE_BUTTON(races_sex_toggles[selected_sex ? 0 : 1]), true);
//  } else {
//    is_name_unique = true;
//  }
//}
//
///**************************************************************************
//...
//**************************************************************************/
//static void races_toggles_callback(GtkWidget * w, gpointer race_id_p)
//{
//  int class_id = selected_class;
//
//  if (!gtk_toggle_button_get_active(GTK_TOGGLE_BUTTON(w))) {
//    /* don't do anything if signal is untoggling the button */
//    return;
//  }
//
//  selected_nation = GPOINTER_TO_INT(race_id_p);
//
//  gtk_entry_set_text(GTK_ENTRY(GTK_COMBO(races_by_name[class_id]).entry),
//		     Nation.get_nation_by_idx(selected_nation).name);
//  gtk_label_set_text(GTK_LABEL(class[class_id]),
//		     Nation.get_nation_by_idx(selected_nation).class);
//  gtk_label_set_text(GTK_LABEL(legend[class_id]),
//		     Nation.get_nation_by_idx(selected_nation).legend);
//
//  select_random_leader();
//
//  selected_city_style =
//      city_style_ridx[get_nation_city_style(selected_nation)];
//  gtk_toggle_button_set_state(GTK_TOGGLE_BUTTON
//			      (city_style_toggles[selected_city_style]),
//			      true);
//}
//
///**************************************************************************
//...
//**************************************************************************/
//static void races_sex_toggles_callback(GtkWidget *w, gpointer data)
//{
//  selected_sex = (w == races_sex_toggles[0]) ? 1 : 0;
//}
//
///**************************************************************************
//...
//**************************************************************************/
//static void city_style_toggles_callback(GtkWidget *w, gpointer data)
//{
//  int i;
//
//  for(i = 0; i < b_s_num; i++) {
//    if(w == city_style_toggles[i]) {
//      selected_city_style = i;
//      return;
//    }
//  }
//}
//
///**************************************************************************
//...
//**************************************************************************/
//static int races_buttons_get_current()
//{
//  return selected_nation;
//}
//
///**************************************************************************
//...
//**************************************************************************/
//static int sex_buttons_get_current()
//{
//  return selected_sex;
//}
//
///**************************************************************************
//...
//**************************************************************************/
//static int city_style_get_current()
//{
//  return selected_city_style;
//}
//
///**************************************************************************
//...
//**************************************************************************/
//static void races_buttons_callback(GtkWidget *w, gpointer data)
//{
//  int selected, selected_sex, selected_style;
//  char *s;
//
//  if(w == races_quit_command) {
//    exit(EXIT_SUCCESS);
//  } else if(w == races_disc_command) {
//    popdown_races_dialog();
//    disconnect_from_server();
//    return;
//  }
//
//  if((selected = races_buttons_get_current()) == -1) {
//    append_output_window("You must select a nation.");
//    return;
//  }
//
//  if((selected_sex = sex_buttons_get_current()) == -1) {
//    append_output_window("You must select your sex.");
//    return;
//  }
//
//  if((selected_style = city_style_get_current()) == -1) {
//    append_output_window("You must select your city style.");
//    return;
//  }
//
//  s = gtk_entry_get_text(GTK_ENTRY(GTK_COMBO(leader_name).entry));
//
//  /* perform a minimum of sanity test on the name */
//  if (s.length() == 0) {
//    append_output_window("You must type a legal name.");
//    return;
//  }
//
//  dsend_packet_nation_select_req(&aconnection, selected,
//				 selected_sex, s,
//				 city_style_idx[selected_style]);
//  /* reset this variable */
//  is_name_unique = false;  
//}
//
///**************************************************************************
//  Destroys its widget.  Usefull for a popdown callback on pop-ups that
//  won't get resused.
//**************************************************************************/
//void destroy_me_callback(GtkWidget *w, gpointer data)
//{
//  gtk_widget_destroy(w);
//}
//
///**************************************************************************
//  Adjust tax rates from main window
//**************************************************************************/
//void taxrates_callback(GtkWidget * w, GdkEventButton * ev, gpointer data)
//{
//  common_taxrates_callback((size_t) data);
//}
//
//void dummy_close_callback(gpointer data){}
//

//  This function is called when the client disconnects or the Game.game is
//  over.  It should close all dialog windows for that Game.game.
//***********************************************************************/
//void popdown_all_game_dialogs()
//{
//  popdown_city_report_dialog();
//  popdown_meswin_dialog();
//  popdown_science_dialog();
//  popdown_economy_report_dialog();
//  popdown_activeunits_report_dialog();
//  popdown_players_dialog();
//  popdown_notify_dialog();
//}
}