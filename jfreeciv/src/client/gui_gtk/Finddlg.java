package client.gui_gtk;

public class Finddlg{

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
//#include <stdio.h>
//#include <stdlib.h>
//#include <string.h>
//
//#include <gdk/gdkkeysyms.h>
//#include <gtk/gtk.h>
//
//#include "fcintl.h"
//#include "Game.game.h"
//#include "player.h"
//
//#include "dialogs.h"
//#include "gui_main.h"
//#include "mapview.h"
//
//#include "finddlg.h"
//
//static GtkWidget *find_dialog_shell;
//static GtkWidget *find_label;
//static GtkWidget *find_list;
//static GtkWidget *find_center_command;
//static GtkWidget *find_cancel_command;
//
//static void update_find_dialog(GtkWidget *find_list);
//
//static void find_center_command_callback(GtkWidget *w, gpointer data);
//static void find_cancel_command_callback(GtkWidget *w, gpointer data);
//static void find_list_callback(GtkWidget *w, gint row, gint column);
//
//tile original_tile;
//
///****************************************************************
//popup the dialog 10% inside the main-window 
//*****************************************************************/
//void popup_find_dialog()
//{
//  GtkWidget *scrolled;
//  GtkAccelGroup *accel=gtk_accel_group_new();
//
//  original_tile = get_center_tile_mapcanvas();
//
//  gtk_widget_set_sensitive(top_vbox, false);
//  
//  find_dialog_shell=gtk_widget_new(GTK_TYPE_DIALOG,
//				   "GtkWindow::title", "Find City",
//				   "GtkWindow::window_position",
//				     GTK_WIN_POS_MOUSE,
//				   "GtkObject::signal::delete_event",
//				     deleted_callback, null,
//				   null);
//  gtk_accel_group_attach(accel, GTK_OBJECT(find_dialog_shell));
//
//  find_label=gtk_widget_new(GTK_TYPE_FRAME,
//			    "GtkFrame::label", "Select a city:",
//			    "GtkWidget::parent",
//			      GTK_DIALOG(find_dialog_shell).vbox,
//			    "GtkWidget::visible", true,
//			    null);
//
//  find_list=gtk_clist_new(1);
//  gtk_clist_set_column_width(GTK_CLIST(find_list), 0,
//	GTK_CLIST(find_list).clist_window_width);
//  gtk_clist_set_auto_sort (GTK_CLIST (find_list), true);
//  scrolled = gtk_scrolled_window_new(null, null);
//  gtk_container_add(GTK_CONTAINER(scrolled), find_list);
//
//  gtk_scrolled_window_set_policy(GTK_SCROLLED_WINDOW(scrolled),
//                          GTK_POLICY_AUTOMATIC, GTK_POLICY_ALWAYS);
//  gtk_widget_set_usize(scrolled, 250, 300);
//  gtk_container_add(GTK_CONTAINER(find_label),scrolled);
//  gtk_widget_show(find_list);
//  gtk_widget_show(scrolled);
//  
//  find_center_command=
//    gtk_widget_new(GTK_TYPE_BUTTON,
//		   "GtkButton::label", "Center",
//		   "GtkWidget::parent",
//		     GTK_DIALOG(find_dialog_shell).action_area,
//		   "GtkObject::signal::clicked",
//		     find_center_command_callback, null,
//		   "GtkWidget::visible", true,
//		   null);
//  GTK_WIDGET_SET_FLAGS(find_center_command, GTK_CAN_DEFAULT);
//  gtk_widget_grab_default(find_center_command);
//
//  gtk_widget_add_accelerator(find_center_command, "clicked",
//	accel, GDK_Return, 0, 0);
//
//  find_cancel_command=
//    gtk_widget_new(GTK_TYPE_BUTTON,
//		   "GtkButton::label", "Cancel",
//		   "GtkWidget::parent",
//		     GTK_DIALOG(find_dialog_shell).action_area,
//		   "GtkObject::signal::clicked",
//		     find_cancel_command_callback, null,
//		   "GtkWidget::visible", true,
//		   null);
//  GTK_WIDGET_SET_FLAGS(find_cancel_command, GTK_CAN_DEFAULT);
//
//  gtk_widget_add_accelerator(find_cancel_command, "clicked",
//	accel, GDK_Escape, 0, 0);
//
//  gtk_signal_connect(GTK_OBJECT(find_list), "select_row",
//		GTK_SIGNAL_FUNC(find_list_callback), null);
//  
//  update_find_dialog(find_list);
//  gtk_widget_show(find_dialog_shell);
//}
//
//
//
///**************************************************************************
//...
//**************************************************************************/
//static void update_find_dialog(GtkWidget *find_list)
//{
//  int i;
//  
//  gchar *row[1];
//
//  gtk_clist_freeze(GTK_CLIST(find_list));
//  gtk_clist_clear(GTK_CLIST(find_list));
//
//  for(i=0; i<Game.game.nplayers; i++) {
//    city_list_iterate(Game.game.players[i].cities, pcity) 
//	row[0] = pcity.name;
//	gtk_clist_append(GTK_CLIST(find_list), row);
//    }
//
//  }
//  gtk_clist_thaw(GTK_CLIST(find_list));
//  gtk_widget_show_all(find_list);
//}
//
///**************************************************************************
//...
//**************************************************************************/
//static void popdown_find_dialog()
//{
//  gtk_widget_destroy(find_dialog_shell);
//  gtk_widget_set_sensitive(top_vbox, true);
//}
//
///**************************************************************************
//...
//**************************************************************************/
//static void find_center_command_callback(GtkWidget *w, gpointer data)
//{
//  city pcity;
//  GList *selection;
//
//  if ((selection=GTK_CLIST(find_list).selection))
//  {
//    gchar *string;
//    gint row = GPOINTER_TO_INT(selection.data);
//
//    gtk_clist_get_text(GTK_CLIST(find_list), row, 0, &string);
//
//    if(string&&(pcity=Game.game_find_city_by_name(string)))
//      center_tile_mapcanvas(pcity.tile);
//  }
//  
//  popdown_find_dialog();
//}
//
///**************************************************************************
//...
//**************************************************************************/
//static void find_cancel_command_callback(GtkWidget *w, gpointer data)
//{
//  center_tile_mapcanvas(original_tile);
//  popdown_find_dialog();
//}
//
///**************************************************************************
//...
//**************************************************************************/
//static void find_list_callback(GtkWidget *w, gint row, gint column)
//{
//  city pcity;
//  gchar *string;
//
//  gtk_clist_get_text(GTK_CLIST(find_list), row, 0, &string);
//
//  if(string&&(pcity=Game.game_find_city_by_name(string)))
//	center_tile_mapcanvas(pcity.tile);
//}
}