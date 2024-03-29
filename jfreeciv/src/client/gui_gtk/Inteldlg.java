package client.gui_gtk;

public class Inteldlg{

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
//
//#include <gtk/gtk.h>
//
//#include "fcintl.h"
//#include "Game.game.h"
//#include "government.h"
//#include "packets.h"
//#include "player.h"
//#include "shared.h"
//#include "support.h"
//
//#include "clinet.h"
//
//#include "gui_main.h"
//#include "gui_stuff.h"
//#include "mapview.h"
//
//#include "inteldlg.h"
//
///******************************************************************/
//static GtkWidget *intel_dialog_shell;
///******************************************************************/
//
//
//static void intel_create_dialog(player p);
//static void intel_close_command_callback(GtkWidget *w, gpointer data);
//
///****************************************************************
//... 
//*****************************************************************/
//void popup_intel_dialog(player p)
//{
//  if(!intel_dialog_shell) {
//    intel_create_dialog(p);
//    gtk_set_relative_position(toplevel, intel_dialog_shell, 25, 25);
//    gtk_widget_show(intel_dialog_shell);
//    gtk_widget_set_sensitive(top_vbox, false);
//  }
//}
//
//
//
///****************************************************************
//...
//*****************************************************************/
//void intel_create_dialog(player p)
//{
//  GtkWidget *label, *hbox, *list, *close, *vbox, *scrolled;
//  char buf[64];
//  city pcity;
//
//  static char *tech_list_names_ptrs[Tech_H.A_LAST+1];
//  static char tech_list_names[Tech_H.A_LAST+1][200];
//  int i, j;
//  
//  
//  intel_dialog_shell=gtk_dialog_new();
//  gtk_signal_connect(GTK_OBJECT(intel_dialog_shell), "delete_event",
//        GTK_SIGNAL_FUNC(intel_close_command_callback), null);
//
//  gtk_window_set_title(GTK_WINDOW(intel_dialog_shell),
//	"Foreign Intelligence Report");
//
//  gtk_container_border_width(GTK_CONTAINER(intel_dialog_shell), 5);
//
//  buf = util.my_snprintf(
//	      "Intelligence Information for the %s Empire", 
//	      get_nation_name(p.nation));
//
//  label=gtk_frame_new(buf);
//  gtk_box_pack_start(GTK_BOX(GTK_DIALOG(intel_dialog_shell).vbox),
//	label, true, false, 2);
//
//  vbox=gtk_vbox_new(false,0);
//  gtk_container_add(GTK_CONTAINER(label), vbox);
//
//  hbox=gtk_hbox_new(false,0);
//  gtk_box_pack_start(GTK_BOX(vbox), hbox, true, false, 2);
//
//  buf = util.my_snprintf( "Ruler: %s %s", 
//	      get_ruler_title(p.government, p.is_male, p.nation), p.name);
//  label=gtk_label_new(buf);
//  gtk_box_pack_start(GTK_BOX(hbox), label, true, false, 5);
//  
//  buf = util.my_snprintf( "Government: %s",
//	      get_government_name(p.government));
//  label=gtk_label_new(buf);
//  gtk_box_pack_start(GTK_BOX(hbox), label, true, false, 5);
//
//  hbox=gtk_hbox_new(false,5);
//  gtk_box_pack_start(GTK_BOX(vbox), hbox, true, false, 2);
//
//  buf = util.my_snprintf( "Gold: %d", p.economic.gold);
//  label=gtk_label_new(buf);
//  gtk_box_pack_start(GTK_BOX(hbox), label, true, false, 5);
//
//  buf = util.my_snprintf( "Tax: %d%%", p.economic.tax);
//  label=gtk_label_new(buf);
//  gtk_box_pack_start(GTK_BOX(hbox), label, true, false, 5);
//
//  buf = util.my_snprintf( "Science: %d%%", p.economic.science);
//  label=gtk_label_new(buf);
//  gtk_box_pack_start(GTK_BOX(hbox), label, true, false, 5);
//
//  buf = util.my_snprintf( "Luxury: %d%%", p.economic.luxury);
//  label=gtk_label_new(buf);
//  gtk_box_pack_start(GTK_BOX(hbox), label, true, false, 5);
//
//  hbox=gtk_hbox_new(false, 5);
//  gtk_box_pack_start(GTK_BOX(vbox), hbox, true, false, 2);
//
//  buf = util.my_snprintf( "Researching: %s(%d/%d)",
//	      get_tech_name(p, p.research.researching),
//	      p.research.bulbs_researched, total_bulbs_required(p));
//
//  label=gtk_label_new(buf);
//  gtk_box_pack_start(GTK_BOX(hbox), label, true, false, 2);
//
//  pcity = p.find_palace();
//  buf = util.my_snprintf( "Capital: %s",
//	      (!pcity)?"(Unknown)":pcity.name);
//  label=gtk_label_new(buf);
//  gtk_box_pack_start(GTK_BOX(hbox), label, true, false, 2);
//
//  list=gtk_clist_new(1);
//  gtk_clist_set_column_width(GTK_CLIST(list), 0,
//			     GTK_CLIST(list).clist_window_width);
//  scrolled=gtk_scrolled_window_new(null,null);
//  gtk_container_add(GTK_CONTAINER(scrolled), list);
//
//  gtk_scrolled_window_set_policy(GTK_SCROLLED_WINDOW(scrolled),
//	GTK_POLICY_AUTOMATIC, GTK_POLICY_AUTOMATIC);
//  gtk_widget_set_usize(scrolled, 420, 300);
//
//  gtk_clist_freeze(GTK_CLIST(list));
//
//  for(i=A_FIRST, j=0; i<Game.game.num_tech_types; i++)
//    if(get_invention(p, i)==TECH_KNOWN) {
//      if(get_invention(Game.game.player_ptr, i)==TECH_KNOWN) {
//	sz_strlcpy(tech_list_names[j], advances[i].name);
//      } else {
//	my_snprintf(tech_list_names[j], sizeof(tech_list_names[j]),
//		    "%s*", advances[i].name);
//      }
//      tech_list_names_ptrs[j]=tech_list_names[j];
//      j++;
//    }
//  tech_list_names_ptrs[j]=null;
//  
//  for (i=0; i<j; i++)
//    gtk_clist_append(GTK_CLIST(list), &tech_list_names_ptrs[i]);
//  
//  gtk_clist_sort(GTK_CLIST(list));
//  gtk_clist_thaw(GTK_CLIST(list));
//  gtk_box_pack_start(GTK_BOX(GTK_DIALOG(intel_dialog_shell).vbox),
//	scrolled, true, false, 2);
//
//  close=gtk_button_new_with_label("Close");
//  gtk_box_pack_start(GTK_BOX(GTK_DIALOG(intel_dialog_shell).action_area),
//	close, true, true, 0);
//  GTK_WIDGET_SET_FLAGS(close,GTK_CAN_DEFAULT);
//  gtk_widget_grab_default(close);
//  
//  gtk_signal_connect(GTK_OBJECT(close), "clicked",
//	GTK_SIGNAL_FUNC(intel_close_command_callback), null);
//
//  gtk_widget_show_all(GTK_DIALOG(intel_dialog_shell).vbox);
//  gtk_widget_show_all(GTK_DIALOG(intel_dialog_shell).action_area);
//}
//
//
//
//
//
///**************************************************************************
//...
//**************************************************************************/
//void intel_close_command_callback(GtkWidget *w, gpointer data)
//{ 
//  gtk_widget_set_sensitive(top_vbox, true);
//  gtk_widget_destroy(intel_dialog_shell);
//  intel_dialog_shell=null;
//}
//
///****************************************************************************
//  Update the intelligence dialog for the given player.  This is called by
//  the core client code when that player's information changes.
//****************************************************************************/
//void update_intel_dialog(player p)
//{
//  /* PORTME */
//}
}