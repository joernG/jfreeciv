package client.gui_gtk_2_0;

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
//#include "gui_main.h"
//#include "gui_stuff.h"
//#include "mapview.h"
//#include "options.h"
//
//#include "inteldlg.h"
//
///******************************************************************/
//static final String table_text[] = {
//  N"Ruler:",
//  N"Government:",
//  N"Capital:",
//  N"Gold:",
//  null,
//  N"Tax:",
//  N"Science:",
//  N"Luxury:",
//  null,
//  N"Researching:"
//};
//
//enum table_label {
//  LABEL_RULER,
//  LABEL_GOVERNMENT,
//  LABEL_CAPITAL,
//  LABEL_GOLD,
//  LABEL_SEP1,
//  LABEL_TAX,
//  LABEL_SCIENCE,
//  LABEL_LUXURY,
//  LABEL_SEP2,
//  LABEL_RESEARCHING,
//  LABEL_LAST
//};
///******************************************************************/
//struct intel_dialog {
//  player pplayer;
//  GtkWidget *shell;
//
//  GtkTreeStore *diplstates;
//  GtkListStore *techs;
//  GtkWidget *table_labels[LABEL_LAST];
//};
//
//#define SPECLIST_TAG dialog
//#define SPECLIST_TYPE struct intel_dialog
//#include "speclist.h"
//
//#define dialog_list_iterate(dialoglist, pdialog) \
//    TYPED_LIST_ITERATE(struct intel_dialog, dialoglist, pdialog)
//#define dialog_list_iterate_end  LIST_ITERATE_END
//
//static Speclists<dialog> dialog_list;
//static boolean dialog_list_has_been_initialised = false;
///******************************************************************/
//
//
//static intel_dialog create_intel_dialog(player p);
//
///****************************************************************
//...
//*****************************************************************/
//static intel_dialog get_intel_dialog(player pplayer)
//{
//  if (!dialog_list_has_been_initialised) {
//    dialog_list_init(&dialog_list);
//    dialog_list_has_been_initialised = true;
//  }
//
//  for (dialog pdialog : dialog_list.data) {
//    if (pdialog.pplayer == pplayer) {
//      return pdialog;
//    }
//  } }
//
//  return null;
//}
//
///****************************************************************
//... 
//*****************************************************************/
//void popup_intel_dialog(player p)
//{
//  intel_dialog pdialog;
//
//  if (!(pdialog = get_intel_dialog(p))) {
//    pdialog = create_intel_dialog(p);
//  }
//
//  update_intel_dialog(p);
//
//  gtk_window_present(GTK_WINDOW(pdialog.shell));
//}
//
///****************************************************************
//...
//*****************************************************************/
//static void intel_destroy_callback(GtkWidget *w, gpointer data)
//{
//  intel_dialog pdialog = (intel_dialog )data;
//
//  dialog_list_unlink(&dialog_list, pdialog);
//
//  free(pdialog);
//}
//
///****************************************************************
//...
//*****************************************************************/
//static intel_dialog create_intel_dialog(player p)
//{
//  intel_dialog pdialog;
//
//  GtkWidget *shell, *notebook, *label, *sw, *view, *table, *alignment;
//  GtkCellRenderer *rend;
//  GtkTreeViewColumn *col;
//
//  int i;
// 
//  pdialog = fc_malloc(sizeof(*pdialog));
//  pdialog.pplayer = p;
// 
//  shell = gtk_dialog_new_with_buttons(null,
//      null,
//      0,
//      GTK_STOCK_CLOSE,
//      GTK_RESPONSE_CLOSE,
//      null);
//  pdialog.shell = shell;
//  gtk_window_set_default_size(GTK_WINDOW(shell), 350, 350);
//  setup_dialog(shell, toplevel);
//  gtk_dialog_set_default_response(GTK_DIALOG(shell), GTK_RESPONSE_CLOSE);
//
//  g_signal_connect(shell, "destroy",
//                   G_CALLBACK(intel_destroy_callback), pdialog);
//  g_signal_connect(shell, "response",
//                   G_CALLBACK(gtk_widget_destroy), null);
//
//  notebook = gtk_notebook_new();
//  gtk_notebook_set_tab_pos(GTK_NOTEBOOK(notebook), GTK_POS_BOTTOM);
//  gtk_container_add(GTK_CONTAINER(GTK_DIALOG(shell).vbox), notebook);
// 
//  /* overview tab. */
//  table = gtk_table_new(ARRAY_SIZE(table_text), 2, false);
//
//  gtk_table_set_row_spacings(GTK_TABLE(table), 2);
//  gtk_table_set_col_spacings(GTK_TABLE(table), 12);
//
//  alignment = gtk_alignment_new(0.0, 0.0, 0.0, 0.0);
//  gtk_container_set_border_width(GTK_CONTAINER(alignment), 6);
//  gtk_container_add(GTK_CONTAINER(alignment), table);
//
//  label = gtk_label_new_with_mnemonic("_Overview");
//  gtk_notebook_append_page(GTK_NOTEBOOK(notebook), alignment, label);
//
//  for (i = 0; i < ARRAY_SIZE(table_text); i++) {
//    if (table_text[i]) {
//      label = gtk_label_new(_(table_text[i]));
//      gtk_misc_set_alignment(GTK_MISC(label), 0.0, 0.5);
//      gtk_table_attach(GTK_TABLE(table), label,
//	  0, 1, i, i+1, GTK_FILL, GTK_FILL|GTK_EXPAND, 0, 0);
//
//      label = gtk_label_new(null);
//      pdialog.table_labels[i] = label;
//      gtk_misc_set_alignment(GTK_MISC(label), 0.0, 0.5);
//      gtk_table_attach(GTK_TABLE(table), label,
//	  1, 2, i, i+1, GTK_FILL, GTK_FILL|GTK_EXPAND, 0, 0);
//    } else {
//      pdialog.table_labels[i] = null;
//      gtk_table_set_row_spacing(GTK_TABLE(table), i, 12);
//    }
//  }
//
//  /* diplomacy tab. */
//  pdialog.diplstates = gtk_tree_store_new(1, G_TYPE_STRING);
//
//  view = gtk_tree_view_new_with_model(GTK_TREE_MODEL(pdialog.diplstates));
//  g_object_unref(pdialog.diplstates);
//  gtk_container_set_border_width(GTK_CONTAINER(view), 6);
//  gtk_tree_view_set_headers_visible(GTK_TREE_VIEW(view), false);
//
//  rend = gtk_cell_renderer_text_new();
//  col = gtk_tree_view_column_new_with_attributes(null, rend,
//    "text", 0, null);
//  gtk_tree_view_append_column(GTK_TREE_VIEW(view), col);
//
//  gtk_tree_view_expand_all(GTK_TREE_VIEW(view));
//
//  sw = gtk_scrolled_window_new(null,null);
//  gtk_scrolled_window_set_shadow_type(GTK_SCROLLED_WINDOW(sw),
//				      GTK_SHADOW_ETCHED_IN);
//  gtk_container_add(GTK_CONTAINER(sw), view);
//
//  gtk_scrolled_window_set_policy(GTK_SCROLLED_WINDOW(sw),
//	GTK_POLICY_AUTOMATIC, GTK_POLICY_AUTOMATIC);
//
//  alignment = gtk_alignment_new(0.0, 0.0, 1.0, 1.0);
//  gtk_container_set_border_width(GTK_CONTAINER(alignment), 6);
//  gtk_container_add(GTK_CONTAINER(alignment), sw);
//
//  label = gtk_label_new_with_mnemonic("_Diplomacy");
//  gtk_notebook_append_page(GTK_NOTEBOOK(notebook), alignment, label);
//
//  /* techs tab. */
//  pdialog.techs = gtk_list_store_new(2, G_TYPE_BOOLEAN, G_TYPE_STRING);
//  gtk_tree_sortable_set_sort_column_id(GTK_TREE_SORTABLE(pdialog.techs),
//      1, GTK_SORT_ASCENDING);
//
//  view = gtk_tree_view_new_with_model(GTK_TREE_MODEL(pdialog.techs));
//  g_object_unref(pdialog.techs);
//  gtk_container_set_border_width(GTK_CONTAINER(view), 6);
//  gtk_tree_view_set_headers_visible(GTK_TREE_VIEW(view), false);
//
//  rend = gtk_cell_renderer_toggle_new();
//  col = gtk_tree_view_column_new_with_attributes(null, rend,
//    "active", 0, null);
//  gtk_tree_view_append_column(GTK_TREE_VIEW(view), col);
//
//  rend = gtk_cell_renderer_text_new();
//  col = gtk_tree_view_column_new_with_attributes(null, rend,
//    "text", 1, null);
//  gtk_tree_view_append_column(GTK_TREE_VIEW(view), col);
//
//  sw = gtk_scrolled_window_new(null,null);
//  gtk_scrolled_window_set_shadow_type(GTK_SCROLLED_WINDOW(sw),
//				      GTK_SHADOW_ETCHED_IN);
//  gtk_container_add(GTK_CONTAINER(sw), view);
//
//  gtk_scrolled_window_set_policy(GTK_SCROLLED_WINDOW(sw),
//	GTK_POLICY_AUTOMATIC, GTK_POLICY_AUTOMATIC);
//
//  alignment = gtk_alignment_new(0.0, 0.0, 1.0, 1.0);
//  gtk_container_set_border_width(GTK_CONTAINER(alignment), 6);
//  gtk_container_add(GTK_CONTAINER(alignment), sw);
//
//  label = gtk_label_new_with_mnemonic("_Techs");
//  gtk_notebook_append_page(GTK_NOTEBOOK(notebook), alignment, label);
//
//  gtk_widget_show_all(GTK_DIALOG(shell).vbox);
//
//  &dialog_list.foo_list_insert(pdialog);
//
//  return pdialog;
//}
//
///****************************************************************************
//  Update the intelligence dialog for the given player.  This is called by
//  the core client code when that player's information changes.
//****************************************************************************/
//void update_intel_dialog(player p)
//{
//  intel_dialog pdialog = get_intel_dialog(p);
//
//  if (pdialog) {
//    GtkTreeIter diplstates[DS_LAST];
//    char buf[64];
//    int i;
//
//    /* window title. */
//    buf = util.my_snprintf(
//	"Foreign Intelligence: %s Empire", get_nation_name(p.nation));
//    gtk_window_set_title(GTK_WINDOW(pdialog.shell), buf);
//
//    /* diplomacy tab. */
//    gtk_tree_store_clear(pdialog.diplstates);
//
//    for (i = 0; i < ARRAY_SIZE(diplstates); i++) {
//      GtkTreeIter it;
//      GValue v = { 0, };
//
//      gtk_tree_store_append(pdialog.diplstates, &it, null);
//      g_value_init(&v, G_TYPE_STRING);
//      g_value_set_static_string(&v, diplstate_text(i));
//      gtk_tree_store_set_value(pdialog.diplstates, &it, 0, &v);
//      g_value_unset(&v);
//      diplstates[i] = it;
//    }
//
//    for(player other: Game.game.players){
//      final player_diplstate state;
//      GtkTreeIter it;
//      GValue v = { 0, };
//
//      if (other == p) {
//	continue;
//      }
//      state = pplayer_get_diplstate(p, other);
//      gtk_tree_store_append(pdialog.diplstates, &it,
//			    &diplstates[state.type]);
//      g_value_init(&v, G_TYPE_STRING);
//      g_value_set_static_string(&v, other.name);
//      gtk_tree_store_set_value(pdialog.diplstates, &it, 0, &v);
//      g_value_unset(&v);
//    }
//
//    /* techs tab. */
//    gtk_list_store_clear(pdialog.techs);
//
//    for(i=A_FIRST; i<Game.game.num_tech_types; i++)
//      if(get_invention(p, i)==TECH_KNOWN) {
//	GtkTreeIter it;
//
//	gtk_list_store_append(pdialog.techs, &it);
//
//	gtk_list_store_set(pdialog.techs, &it,
//			   0, (get_invention(Game.game.player_ptr, i)!=TECH_KNOWN),
//			   1, get_tech_name(p, i),
//			   -1);
//      }
//
//    /* table labels. */
//    for (i = 0; i < ARRAY_SIZE(pdialog.table_labels); i++) {
//      if (pdialog.table_labels[i]) {
//	city pcity;
//
//	switch (i) {
//	  case LABEL_RULER:
//	    buf = util.my_snprintf( "%s %s", 
//		get_ruler_title(p.government, p.is_male, p.nation), p.name);
//	    break;
//	  case LABEL_GOVERNMENT:
//	    buf = String.format( get_government_name(p.government));
//	    break;
//	  case LABEL_CAPITAL:
//	    pcity = p.find_palace();
//	    buf = String.format( (!pcity) ? "(Unknown)" : pcity.name);
//	    break;
//	  case LABEL_GOLD:
//	    buf = util.my_snprintf( "%d", p.economic.gold);
//	    break;
//	  case LABEL_TAX:
//	    buf = util.my_snprintf( "%d%%", p.economic.tax);
//	    break;
//	  case LABEL_SCIENCE:
//	    buf = util.my_snprintf( "%d%%", p.economic.science);
//	    break;
//	  case LABEL_LUXURY:
//	    buf = util.my_snprintf( "%d%%", p.economic.luxury);
//	    break;
//	  case LABEL_RESEARCHING:
//	    if (p.research.researching != A_NOINFO) {
//	      buf = util.my_snprintf( "%s(%d/%d)",
//		  get_tech_name(p, p.research.researching),
//		  p.research.bulbs_researched, total_bulbs_required(p));
//	    } else {
//	      buf = util.my_snprintf( "(Unknown)");
//	    }
//	    break;
//	  default:
//	    buf[0] = '\0';
//	    break;
//	}
//
//	if (buf[0] != '\0') {
//	  gtk_label_set_text(GTK_LABEL(pdialog.table_labels[i]), buf);
//	}
//      }
//    }
//  }
//}
//
}