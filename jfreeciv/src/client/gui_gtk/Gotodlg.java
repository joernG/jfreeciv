package client.gui_gtk;

public class Gotodlg{

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
//#include "game.h"
//#include "map.h"
//#include "packets.h"
//#include "player.h"
//#include "support.h"
//#include "unit.h"
//
//#include "civclient.h"
//#include "clinet.h"
//#include "control.h"
//
//#include "dialogs.h"
//#include "gui_main.h"
//#include "gui_stuff.h"
//#include "mapview.h"
//
//#include "gotodlg.h"
//
//static GtkWidget *goto_dialog_shell;
//static GtkWidget *goto_label;
//static GtkWidget *goto_list;
//static GtkWidget *goto_center_command;
//static GtkWidget *goto_airlift_command;
//static GtkWidget *goto_all_toggle;
//static GtkWidget *goto_cancel_command;
//
//static void update_goto_dialog			(GtkWidget *goto_list);
//
//static void goto_cancel_command_callback	(GtkWidget *w, gpointer data);
//static void goto_goto_command_callback		(GtkWidget *w, gpointer data);
//static void goto_airlift_command_callback	(GtkWidget *w, gpointer data);
//static void goto_all_toggle_callback		(GtkWidget *w, gpointer data);
//static void goto_list_callback			(GtkWidget *w, gint row, gint column);
//static void goto_list_ucallback		(GtkWidget *w, gint row, gint column);
//
//tile original_tile;
//
///****************************************************************
//...
//*****************************************************************/
//void popup_goto_dialog_action()
//{
//  popup_goto_dialog();
//}
//
///****************************************************************
//popup the dialog 10% inside the main-window 
//*****************************************************************/
//void popup_goto_dialog()
//{
//  GtkWidget *scrolled;
//  GtkAccelGroup *accel=gtk_accel_group_new();
//
//  if (!can_client_issue_orders() || !get_unit_in_focus()) {
//    return;
//  }
//
//  original_tile = get_center_tile_mapcanvas();
//
//  gtk_widget_set_sensitive(top_vbox, false);
//  
//  goto_dialog_shell=gtk_dialog_new();
//  gtk_signal_connect(GTK_OBJECT(goto_dialog_shell), "delete_event",
//	GTK_SIGNAL_FUNC(deleted_callback), null);
//  gtk_accel_group_attach(accel, GTK_OBJECT(goto_dialog_shell));
//  gtk_window_set_position (GTK_WINDOW(goto_dialog_shell), GTK_WIN_POS_MOUSE);
//
//  gtk_window_set_title(GTK_WINDOW(goto_dialog_shell), "Goto/Airlift Unit");
//
//  goto_label=gtk_frame_new("Select destination");
//  gtk_box_pack_start(GTK_BOX(GTK_DIALOG(goto_dialog_shell).vbox),
//	goto_label, true, true, 0);
//
//  goto_list=gtk_clist_new(1);
//  gtk_clist_set_column_width(GTK_CLIST(goto_list), 0,
//			     GTK_CLIST(goto_list).clist_window_width);
//  gtk_clist_set_auto_sort (GTK_CLIST (goto_list), true);
//  scrolled=gtk_scrolled_window_new(null, null);
//  gtk_container_add(GTK_CONTAINER(scrolled), goto_list);
//
//  gtk_scrolled_window_set_policy(GTK_SCROLLED_WINDOW(scrolled),
//				 GTK_POLICY_AUTOMATIC, GTK_POLICY_ALWAYS);
//  gtk_widget_set_usize(scrolled, 250, 300);
//  gtk_container_add(GTK_CONTAINER(goto_label), scrolled);
//
//  goto_center_command=gtk_accelbutton_new("_Goto", accel);
//  gtk_box_pack_start(GTK_BOX(GTK_DIALOG(goto_dialog_shell).action_area),
//	goto_center_command, true, true, 0);
//  GTK_WIDGET_SET_FLAGS(goto_center_command, GTK_CAN_DEFAULT);
//  gtk_widget_grab_default (goto_center_command);
//
//  goto_airlift_command=gtk_accelbutton_new("Air_lift", accel);
//  gtk_box_pack_start(GTK_BOX(GTK_DIALOG(goto_dialog_shell).action_area),
//	goto_airlift_command, true, true, 0);
//  GTK_WIDGET_SET_FLAGS(goto_airlift_command, GTK_CAN_DEFAULT);
//
//  goto_all_toggle=gtk_toggle_button_new_with_label("All Cities");
//  gtk_box_pack_start(GTK_BOX(GTK_DIALOG(goto_dialog_shell).action_area),
//	goto_all_toggle, true, true, 0);
//  GTK_WIDGET_SET_FLAGS(goto_all_toggle, GTK_CAN_DEFAULT);
//
//  goto_cancel_command=gtk_accelbutton_new("_Cancel", accel);
//  gtk_box_pack_start(GTK_BOX(GTK_DIALOG(goto_dialog_shell).action_area),
//	goto_cancel_command, true, true, 0);
//  GTK_WIDGET_SET_FLAGS(goto_cancel_command, GTK_CAN_DEFAULT);
//
//  gtk_widget_add_accelerator(goto_cancel_command, "clicked",
//	accel, GDK_Escape, 0, 0);
//
//  gtk_signal_connect(GTK_OBJECT(goto_list), "select_row",
//		GTK_SIGNAL_FUNC(goto_list_callback), null);
//  gtk_signal_connect(GTK_OBJECT(goto_list), "unselect_row",
//		GTK_SIGNAL_FUNC(goto_list_ucallback), null);
//  gtk_signal_connect(GTK_OBJECT(goto_center_command), "clicked",
//		GTK_SIGNAL_FUNC(goto_goto_command_callback), null);
//  gtk_signal_connect(GTK_OBJECT(goto_airlift_command), "clicked",
//		GTK_SIGNAL_FUNC(goto_airlift_command_callback), null);
//  gtk_signal_connect(GTK_OBJECT(goto_all_toggle), "toggled",
//		GTK_SIGNAL_FUNC(goto_all_toggle_callback), null);
//  gtk_signal_connect(GTK_OBJECT(goto_cancel_command), "clicked",
//		GTK_SIGNAL_FUNC(goto_cancel_command_callback), null);
//
//  gtk_widget_show_all(GTK_DIALOG(goto_dialog_shell).vbox);
//  gtk_widget_show_all(GTK_DIALOG(goto_dialog_shell).action_area);
//
//  update_goto_dialog(goto_list);
//  gtk_widget_show(goto_dialog_shell);
//}
//
///**************************************************************************
//...
//**************************************************************************/
//static city get_selected_city()
//{
//  GList *selection = GTK_CLIST(goto_list).selection;
//  gchar *string;
//  int len;
//
//  if (!selection) {
//    return null;
//  }
//  
//  gtk_clist_get_text(GTK_CLIST(goto_list),
//		     GPOINTER_TO_INT(selection.data), 0, &string);
//
//  len = string.length();
//  if(len>3 && strcmp(string+len-3, "(A)")==0) {
//    char name[MAX_LEN_NAME];
//    mystrlcpy(name, string, Math.min(sizeof(name),len-2));
//    return game_find_city_by_name(name);
//  }
//  return game_find_city_by_name(string);
//}
//
///**************************************************************************
//...
//**************************************************************************/
//static void update_goto_dialog(GtkWidget *goto_list)
//{
//  int    i, j;
//  int    all_cities;
//  gchar *row	[1];
//  char   name	[MAX_LEN_NAME+3];
//  
//  all_cities=GTK_TOGGLE_BUTTON(goto_all_toggle).active;
//
//  gtk_clist_freeze(GTK_CLIST(goto_list));
//  gtk_clist_clear(GTK_CLIST(goto_list));
//  
//  row[0]=name;
//
//  for(i=0, j=0; i<game.nplayers; i++) {
//    if(!all_cities && i!=game.player_idx) continue;
//    city_list_iterate(game.players[i].cities, pcity) {
//      name = pcity.name;
//      /* FIXME: should use unit_can_airlift_to(). */
//      if (pcity.airlift) {
//	sz_strlcat(name, "(A)");
//      }
//      gtk_clist_append( GTK_CLIST( goto_list ), row );
//    }
//    }
//  }
//  gtk_clist_thaw(GTK_CLIST(goto_list));
//  gtk_widget_show_all(goto_list);
//}
//
///**************************************************************************
//...
//**************************************************************************/
//static void popdown_goto_dialog()
//{
//  gtk_widget_destroy(goto_dialog_shell);
//  gtk_widget_set_sensitive(top_vbox, true);
//}
//
///**************************************************************************
//...
//**************************************************************************/
//static void goto_list_callback(GtkWidget *w, gint row, gint column)
//{
//  city pdestcity;
//
//  if((pdestcity=get_selected_city())) {
//    unit punit=get_unit_in_focus();
//    center_tile_mapcanvas(pdestcity.tile);
//    if(punit && unit_can_airlift_to(punit, pdestcity)) {
//      gtk_widget_set_sensitive(goto_airlift_command, true);
//      return;
//    }
//  }
//}
//
///**************************************************************************
//...
//**************************************************************************/
//static void goto_list_ucallback(GtkWidget *w, gint row, gint column)
//{
//  gtk_widget_set_sensitive(goto_airlift_command, false);
//}
//
///**************************************************************************
//...
//**************************************************************************/
//static void goto_airlift_command_callback(GtkWidget *w, gpointer data)
//{
//  city pdestcity=get_selected_city();
//  if (pdestcity) {
//    unit punit=get_unit_in_focus();
//    if (punit) {
//      request_unit_airlift(punit, pdestcity);
//    }
//  }
//  popdown_goto_dialog();
//}
//
///**************************************************************************
//...
//**************************************************************************/
//static void goto_all_toggle_callback(GtkWidget *w, gpointer data)
//{
//  update_goto_dialog(goto_list);
//}
//
///**************************************************************************
//...
//**************************************************************************/
//static void goto_goto_command_callback(GtkWidget *w, gpointer data)
//{
//  city pdestcity=get_selected_city();
//  if (pdestcity) {
//    unit punit=get_unit_in_focus();
//    if (punit) {
//      send_goto_unit(punit, pdestcity.tile);
//    }
//  }
//  popdown_goto_dialog();
//}
//
///**************************************************************************
//...
//**************************************************************************/
//static void goto_cancel_command_callback(GtkWidget *w, gpointer data)
//{
//  center_tile_mapcanvas(original_tile);
//  popdown_goto_dialog();
//}
}