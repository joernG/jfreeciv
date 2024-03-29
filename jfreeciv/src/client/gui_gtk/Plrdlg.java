package client.gui_gtk;

public class Plrdlg{

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
//#include "diptreaty.h"
//#include "fcintl.h"
//#include "Game.game.h"
//#include "log.h"
//#include "nation.h"
//#include "packets.h"
//#include "player.h"
//#include "support.h"
//
//#include "civclient.h"
//#include "climisc.h"
//#include "clinet.h"
//#include "text.h"
//#include "tilespec.h"
//
//#include "chatline.h"
//#include "colors.h"
//#include "graphics.h"
//#include "gui_main.h"
//#include "gui_stuff.h"
//#include "inteldlg.h"
//#include "spaceshipdlg.h"
//
//#include "plrdlg.h"
//
//static GtkWidget *players_dialog_shell;
//static GtkWidget *players_list;
//static GtkWidget *players_close_command;
//static GtkWidget *players_int_command;
//static GtkWidget *players_meet_command;
//static GtkWidget *players_war_command;
//static GtkWidget *players_vision_command;
//static GtkWidget *players_sship_command;
//
///* the sortable columns need two indexes */
//static int listindex_to_playerindex[Shared_H.MAX_NUM_PLAYERS];
//static int playerindex_to_listindex[Shared_H.MAX_NUM_PLAYERS];
//
///* a simple macro that makes an often used finalruct look readable */
//#define LI_2_PI(no) *(int*)(gtk_clist_get_row_data(GTK_CLIST(players_list), no))
//
//static SPRITE *flags[Shared_H.MAX_NUM_PLAYERS];
//
//static void create_players_dialog();
//static void players_button_callback(GtkWidget *w, gpointer data);
//static void players_meet_callback(GtkWidget *w, gpointer data);
//static void players_war_callback(GtkWidget *w, gpointer data);
//static void players_vision_callback(GtkWidget *w, gpointer data);
//static void players_intel_callback(GtkWidget *w, gpointer data);
//static void players_list_callback(GtkWidget *w, gint row, gint column);
//static void players_list_ucallback(GtkWidget *w, gint row, gint column);
//static void players_sship_callback(GtkWidget *w, gpointer data);
//
//public static final int NUM_COLUMNS = 14;		/* number of columns in total */
//public static final int DEF_SORT_COLUMN = 2; /* default sort column (1 = nation) */
//
///****************************************************************
//popup the dialog 10% inside the main-window 
//*****************************************************************/
//void popup_players_dialog()
//{
//  if(!players_dialog_shell){
//    create_players_dialog();
//    gtk_set_relative_position(toplevel, players_dialog_shell, 25, 25);
//
//    gtk_widget_show(players_dialog_shell);
//  }
//}
//
///****************************************************************
// Closes the players dialog.
//*****************************************************************/
//void popdown_players_dialog()
//{
//  if (players_dialog_shell) {
//    gtk_widget_destroy(players_dialog_shell);
//    players_dialog_shell = null;
//  }
//}
//
///*
// * Sort plrs by column...
// */
//static void sort_players_callback(GtkButton *button, gpointer *data)
//{
//  int sort_column = GPOINTER_TO_INT(data);
//  int i;
//
//  /* first - sort the clist */
//  if (sort_column == GTK_CLIST(players_list).sort_column) {
//    if (GTK_CLIST(players_list).sort_type == GTK_SORT_ASCENDING) {
//      gtk_clist_set_sort_type(GTK_CLIST(players_list), GTK_SORT_DESCENDING);
//    } else {
//      gtk_clist_set_sort_type(GTK_CLIST(players_list), GTK_SORT_ASCENDING);
//    }
//  } else {
//    gtk_clist_set_sort_type(GTK_CLIST(players_list), GTK_SORT_ASCENDING);
//    gtk_clist_set_sort_column(GTK_CLIST(players_list), sort_column);
//  }
//  gtk_clist_sort( GTK_CLIST(players_list));
//
//  /* second - update the index */
//  for (i=0; i<GTK_CLIST(players_list).rows; i++) {
//     playerindex_to_listindex[LI_2_PI(i)]=i;
//  }  
//}
//
//
///****************************************************************
//...
//*****************************************************************/
//void create_players_dialog()
//{
//  static final String titles_[NUM_COLUMNS] =
//      { N"Name", N"Flag", N"Nation",
//	N"Border", N"Team", N"AI",
//	N"Embassy", N"Dipl.State", N"Vision", N"Reputation",
//	N"State", N"Host", N"Idle", N"Ping"
//  };
//  static gchar **titles;
//  int i;
//  GtkAccelGroup *accel = gtk_accel_group_new();
//  GtkWidget *sw;
//
//  /* initialises the indexes, necessary for update_players_dialog */
//  for (i = 0; i < Shared_H.MAX_NUM_PLAYERS; i++) {
//    playerindex_to_listindex[i] = -1;
//    listindex_to_playerindex[i] = -1;
//  }
//
//  if (!titles) titles = intl_slist(NUM_COLUMNS, titles_);
//
//  players_dialog_shell = gtk_dialog_new();
//  gtk_window_set_default_size(GTK_WINDOW(players_dialog_shell), -1, 270);
//
//  gtk_box_set_homogeneous(GTK_BOX
//			  (GTK_DIALOG(players_dialog_shell).action_area),
//			  0);
//  gtk_signal_connect( GTK_OBJECT(players_dialog_shell),"delete_event",
//        GTK_SIGNAL_FUNC(players_button_callback),null);
//  gtk_accel_group_attach(accel, GTK_OBJECT(players_dialog_shell));
//
//  gtk_window_set_title(GTK_WINDOW(players_dialog_shell), "Players");
//
//  players_list=gtk_clist_new_with_titles(NUM_COLUMNS, titles);
//
//  for(i=0; i<NUM_COLUMNS; i++)
//    gtk_clist_set_column_auto_resize (GTK_CLIST (players_list), i, true);
//
//  sw = gtk_scrolled_window_new(null, null);
//  gtk_container_add(GTK_CONTAINER(sw), players_list);
//  gtk_scrolled_window_set_policy(GTK_SCROLLED_WINDOW(sw),
//				 GTK_POLICY_NEVER, GTK_POLICY_AUTOMATIC);
//  gtk_box_pack_start(GTK_BOX(GTK_DIALOG(players_dialog_shell).vbox),
//	sw, true, true, 0);
//
//  players_close_command=gtk_accelbutton_new("C_lose", accel);
//  gtk_box_pack_start(GTK_BOX(GTK_DIALOG(players_dialog_shell).action_area),
//	players_close_command, true, true, 0);
//  GTK_WIDGET_SET_FLAGS(players_close_command, GTK_CAN_DEFAULT);
//  gtk_widget_grab_default(players_close_command);
//
//  players_int_command=gtk_accelbutton_new("_Intelligence", accel);
//  gtk_widget_set_sensitive(players_int_command, false);
//  gtk_box_pack_start(GTK_BOX(GTK_DIALOG(players_dialog_shell).action_area),
//	players_int_command, true, true, 0);
//  GTK_WIDGET_SET_FLAGS(players_int_command, GTK_CAN_DEFAULT);
//
//  players_meet_command=gtk_accelbutton_new("_Meet", accel);
//  gtk_widget_set_sensitive(players_meet_command, false);
//  gtk_box_pack_start(GTK_BOX(GTK_DIALOG(players_dialog_shell).action_area),
//	players_meet_command, true, true, 0);
//  GTK_WIDGET_SET_FLAGS(players_meet_command, GTK_CAN_DEFAULT);
//
//  players_war_command=gtk_accelbutton_new("_Cancel Treaty", accel);
//  gtk_widget_set_sensitive(players_war_command, false);
//  gtk_box_pack_start(GTK_BOX(GTK_DIALOG(players_dialog_shell).action_area),
//	players_war_command, true, true, 0);
//  GTK_WIDGET_SET_FLAGS(players_war_command, GTK_CAN_DEFAULT);
//
//  players_vision_command=gtk_accelbutton_new("_Withdraw vision", accel);
//  gtk_widget_set_sensitive(players_vision_command, false);
//  gtk_box_pack_start(GTK_BOX(GTK_DIALOG(players_dialog_shell).action_area),
//	players_vision_command, true, true, 0);
//  GTK_WIDGET_SET_FLAGS(players_vision_command, GTK_CAN_DEFAULT);
//
//  players_sship_command=gtk_accelbutton_new("_Spaceship", accel);
//  gtk_widget_set_sensitive(players_sship_command, false);
//  gtk_box_pack_start(GTK_BOX(GTK_DIALOG(players_dialog_shell).action_area),
//	players_sship_command, true, true, 0);
//  GTK_WIDGET_SET_FLAGS(players_sship_command, GTK_CAN_DEFAULT);
//
//  gtk_signal_connect(GTK_OBJECT(players_list), "select_row",
//		GTK_SIGNAL_FUNC(players_list_callback), null);
//
//  gtk_signal_connect(GTK_OBJECT(players_list), "unselect_row",
//		GTK_SIGNAL_FUNC(players_list_ucallback), null);
//  
//  gtk_signal_connect(GTK_OBJECT(players_close_command), "clicked",
//		GTK_SIGNAL_FUNC(players_button_callback), null);
//
//  gtk_signal_connect(GTK_OBJECT(players_meet_command), "clicked",
//		GTK_SIGNAL_FUNC(players_meet_callback), null);
//
//  gtk_signal_connect(GTK_OBJECT(players_war_command), "clicked",
//		GTK_SIGNAL_FUNC(players_war_callback), null);
//
//  gtk_signal_connect(GTK_OBJECT(players_vision_command), "clicked",
//		GTK_SIGNAL_FUNC(players_vision_callback), null);
//
//  gtk_signal_connect(GTK_OBJECT(players_int_command), "clicked",
//		GTK_SIGNAL_FUNC(players_intel_callback), null);
//  
//  gtk_signal_connect(GTK_OBJECT(players_sship_command), "clicked",
//		GTK_SIGNAL_FUNC(players_sship_callback), null);
//
//  gtk_widget_show_all(GTK_DIALOG(players_dialog_shell).vbox);
//  gtk_widget_show_all(GTK_DIALOG(players_dialog_shell).action_area);
//
//  gtk_widget_add_accelerator(players_close_command, "clicked",
//	accel, GDK_Escape, 0, 0);
//
//  /* default sort column is nation name */
//  gtk_clist_set_sort_column(GTK_CLIST(players_list), DEF_SORT_COLUMN);
//
//  /* all colums but flag column are clickable */
//  for (i = 0; i < NUM_COLUMNS; i++) {
//    if (i != 1) {
//      gtk_signal_connect(GTK_OBJECT
//			 (GTK_CLIST(players_list).column[i].button),
//			 "clicked", GTK_SIGNAL_FUNC(sort_players_callback),
//			 GINT_TO_POINTER(i));
//    }
//  }
//
//  gtk_clist_clear(GTK_CLIST(players_list));
//  update_players_dialog();
//
//  /* don't resize the state column */
//  gtk_clist_set_column_auto_resize(GTK_CLIST(players_list), 8, false);
//}
//
///* 
// * Builds the text for the cells of a row in the player report. If
// * update is 1, only the changable entries are build.
// */
//static void build_row(final String*row, int i, int update)
//{
//  static String namebuf, flagbuf[1], aibuf[2], dsbuf[32],
//      repbuf[32], statebuf[32], idlebuf[32];
//  static final String colbuf = "";
//  final player_diplstate pds;
//
//  /* we assume that the player's name, flag,
//   * nation and color never change. */
//  if (update == 0) {
//    /* the playername */
//    namebuf = util.my_snprintf( "%-16s", Game.game.players[i].name);
//    row[0] = namebuf;
//
//    /* since flag is a pixmap, this can be empty */
//    flagbuf[0] = '\0';
//    row[1] = flagbuf;
//
//    /* the nation */
//    row[2] = (char *) Nation.get_nation_name(Game.game.players[i].nation);
//
//    /* the nation color, empty since it's a block of color (no text). */
//    row[3] = colbuf;
//
//    /* the team */
//    if (Game.game.players[i].team != TEAM_NONE) {
//      row[4] = (char *) team_get_by_id(Game.game.players[i].team).name;
//    } else {
//      row[4] = (char *) "";
//    }
//  }
//
//  /* text for name, plus AI marker */
//  aibuf[0] = (Game.game.players[i].ai.control ? '*' : '\0');
//  aibuf[1] = '\0';
//
//  /* text for diplstate type and turns -- not applicable if this is me */
//  if (i == Game.game.player_idx) {
//    strcpy(dsbuf, "-");
//  } else {
//    pds = pplayer_get_diplstate(Game.game.player_ptr, get_player(i));
//    if (pds.type == DS_CEASEFIRE) {
//      dsbuf = util.my_snprintf( "%s (%d)",
//		  diplstate_text(pds.type), pds.turns_left);
//    } else {
//      dsbuf = util.my_snprintf( "%s", diplstate_text(pds.type));
//    }
//  }
//
//  /* text for state */
//  if (Game.game.players[i].is_alive) {
//    if (Game.game.players[i].is_connected) {
//      if (Game.game.players[i].turn_done) {
//	statebuf = String.format( "done");
//      } else {
//	statebuf = String.format( "moving");
//      }
//    } else {
//      statebuf[0] = '\0';
//    }
//  } else {
//    statebuf = String.format( "R.I.P");
//  }
//
//  /* text for idleness */
//  if (Game.game.players[i].nturns_idle > 3) {
//    idlebuf = util.my_snprintf(
//		PL("(idle %d turn)", "(idle %d turns)",
//		    Game.game.players[i].nturns_idle - 1),
//		Game.game.players[i].nturns_idle - 1);
//  } else {
//    idlebuf[0] = '\0';
//  }
//
//  /* text for reputation */
//  repbuf = util.my_snprintf(
//	      reputation_text(Game.game.players[i].reputation));
//
//  /* assemble the whole lot */
//  row[5] = aibuf;
//  row[6] = get_embassy_status(Game.game.player_ptr, &Game.game.players[i]);
//  row[7] = dsbuf;
//  row[8] = get_vision_status(Game.game.player_ptr, &Game.game.players[i]);
//  row[9] = repbuf;
//  row[10] = statebuf;
//  row[11] = (char *) player_addr_hack(&Game.game.players[i]);	/* Fixme */
//  row[12] = idlebuf;
//  row[13] = get_ping_time_text(&Game.game.players[i]);
//}
//
//public static final int MIN_DIMENSION = 5;
//
///* 
// * Builds the flag pixmap.
// */
//static void build_flag(int playerindex)
//{
//  int start_x, start_y, end_x, end_y, flag_h, flag_w, newflag_h, newflag_w;
//  SPRITE *flag, *croped, *scaled;
//
//  flag = Nation.get_nation_by_plr(&Game.game.players[playerindex]).flag_sprite;
//  if (!flag) {
//    flags[playerindex] = null;
//    return;
//  }
//
//  /* calculate the bounding box ... */
//  sprite_get_bounding_box(flag, &start_x, &start_y, &end_x, &end_y);
//
//  assert(start_x != -1);
//  assert(start_y != -1);
//  assert(end_x != -1);
//  assert(end_y != -1);
//
//  flag_w = (end_x - start_x) + 1;
//  flag_h = (end_y - start_y) + 1;
//
//  /* if the flag is smaller then 5 x 5, something is wrong */
//  assert(flag_w >= MIN_DIMENSION && flag_h >= MIN_DIMENSION);
//  
//  /* croping */
//  croped = crop_sprite(flag, start_x, start_y, flag_w, flag_h, null, -1, -1);
//
//  /* scaling */
//  newflag_h = GTK_CLIST(players_list).row_height;
//  newflag_w = ((double) newflag_h / flag_h) * flag_w;
//
//  util.freelog(Log.LOG_DEBUG, "%dx%d %dx%d %dx%d", flag.width,
//	  flag.height, flag_w, flag_h, newflag_w, newflag_h);
//
//  scaled = sprite_scale(croped, newflag_w, newflag_h);
//  free_sprite(croped);
//
//  sprite_draw_black_border(scaled);
//
//  /* and finaly store the scaled flagsprite in the static flags array */
//  flags[playerindex] = scaled;
//}
//
//
///**************************************************************************
//...
//**************************************************************************/
//void update_players_dialog()
//{
//  if (players_dialog_shell && !is_plrdlg_frozen()) {
//    GdkColor *state_col;
//    GtkStyle *style;
//    final String row_texts[NUM_COLUMNS];
//    int i, j, row, sort_needed = 0;
//
//    gtk_clist_freeze(GTK_CLIST(players_list));
//
//    for(player pplayer: Game.game.players){
//      int i = pplayer.player_no;
//
//      /* skip barbarians */
//      if (is_barbarian(&Game.game.players[i])) {
//	continue;
//      }
//      row = playerindex_to_listindex[i];
//      if (row == -1) {
//	/* 
//	 * A nation is not in the player report yet. This happens when
//	 * the report is just opened and after a split.
//	 */
//	row = GTK_CLIST(players_list).rows;
//	build_row(row_texts, i, 0);
//	gtk_clist_append(GTK_CLIST(players_list), (gchar **)row_texts);
//	gtk_clist_set_row_data(GTK_CLIST(players_list), row,
//			       &(listindex_to_playerindex[row]));
//
//	build_flag(i);
//	if (flags[i]) {
//	  gtk_clist_set_pixmap(GTK_CLIST(players_list), row, 1,
//			       flags[i].pixmap, flags[i].mask);
//	}
//
//	listindex_to_playerindex[row] = i;
//	playerindex_to_listindex[i] = row;
//	sort_needed = 1;
//      } else {
//	build_row(row_texts, i, 1);
//	/* 
//	 * The nation already had a row in the player report. In that
//	 * case we just update the row. 
//	 */
//	for (j = 5; j < NUM_COLUMNS; j++) {
//	  gtk_clist_set_text(GTK_CLIST(players_list), row, j,
//			     row_texts[j]);
//	}
//      }
//
//      /* now add some eye candy ... */
//      switch (pplayer_get_diplstate(Game.game.player_ptr, get_player(i)).type) {
//      case diplstate_type.DS_WAR:
//	state_col = colors_standard[COLOR_STD_RED];
//	break;
//      case diplstate_type.DS_ALLIANCE:
//      case diplstate_type.DS_TEAM:
//	state_col = colors_standard[COLOR_STD_GROUND];
//	break;
//      default:
//	state_col = colors_standard[COLOR_STD_BLACK];
//      }
//      gtk_clist_set_foreground(GTK_CLIST(players_list), row, state_col);
//      /* Make the background of column 3 match the nation's border colour. */
//      style = gtk_style_new();
//      style.fg[GTK_STATE_NORMAL]
//	      = *(colors_standard[player_color(pplayer)]);
//      style.base[GTK_STATE_NORMAL]
//	      = *(colors_standard[player_color(pplayer)]);
//      gtk_clist_set_cell_style(GTK_CLIST(players_list), row, 3, style);
//    }
//
//    if (sort_needed) {
//      /* when new nations appear in the report, we have to sort it */
//      gtk_clist_sort(GTK_CLIST(players_list));
//      /* second - update the index */
//      for (i = 0; i < GTK_CLIST(players_list).rows; i++) {
//	playerindex_to_listindex[LI_2_PI(i)] = i;
//      }
//    }
//
//    gtk_clist_thaw(GTK_CLIST(players_list));
//    gtk_widget_show_all(players_list);
//  }
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void players_list_callback(GtkWidget * w, gint row, gint column)
//{
//  int player_index = LI_2_PI(row);
//  player pplayer = &Game.game.players[player_index];
//
//  if (pplayer.spaceship.state != spaceship_state.SSHIP_NONE)
//    gtk_widget_set_sensitive(players_sship_command, true);
//  else
//    gtk_widget_set_sensitive(players_sship_command, false);
//
//  switch (pplayer_get_diplstate
//	  (Game.game.player_ptr, get_player(player_index)).type) {
//  case diplstate_type.DS_WAR:
//  case diplstate_type.DS_NO_CONTACT:
//    gtk_widget_set_sensitive(players_war_command, false);
//    break;
//  default:
//    gtk_widget_set_sensitive(players_war_command,
//			     can_client_issue_orders()
//			     && Game.game.player_idx != player_index);
//  }
//
//  gtk_widget_set_sensitive(players_vision_command,
//			   can_client_issue_orders()
//			   && gives_shared_vision(Game.game.player_ptr, pplayer));
//
//  gtk_widget_set_sensitive(players_meet_command,
//                           can_meet_with_player(pplayer));
//  gtk_widget_set_sensitive(players_int_command,
//                           can_intel_with_player(pplayer));
//}
//
//void players_list_ucallback(GtkWidget *w, gint row, gint column)
//{
//  gtk_widget_set_sensitive(players_meet_command, false);
//  gtk_widget_set_sensitive(players_int_command, false);
//}
//
///**************************************************************************
//...
//**************************************************************************/
//static void players_button_callback(GtkWidget * w, gpointer data)
//{
//  popdown_players_dialog();
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void players_meet_callback(GtkWidget *w, gpointer data)
//{
//  GList *selection;
//  gint row;
//  int player_index;
//
//  if(!(selection=GTK_CLIST(players_list).selection))
//    return;
//
//  row = GPOINTER_TO_INT(selection.data);
//  player_index = LI_2_PI(row);
//
//  if (can_meet_with_player(&Game.game.players[player_index])) {
//    dsend_packet_diplomacy_init_meeting_req(&aconnection, player_index);
//  } else {
//    append_output_window(("Game: You need an embassy to " +
//			   "establish a diplomatic meeting."));
//  }
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void players_war_callback(GtkWidget *w, gpointer data)
//{
//  GList *selection = GTK_CLIST(players_list).selection;
//
//  if (!selection) {
//    return;
//  } else {
//    gint row = GPOINTER_TO_INT(selection.data);
//    /* can be any pact clause */
//    dsend_packet_diplomacy_cancel_pact(&aconnection, LI_2_PI(row),
//				       CLAUSE_CEASEFIRE);
//  }
//}
//
///**************************************************************************
//  ...
//**************************************************************************/
//void players_vision_callback(GtkWidget *w, gpointer data)
//{
//  GList *selection = GTK_CLIST(players_list).selection;
//
//  if (!selection) {
//    return;
//  } else {
//    gint row = GPOINTER_TO_INT(selection.data);
//    dsend_packet_diplomacy_cancel_pact(&aconnection, LI_2_PI(row),
//				       CLAUSE_VISION);
//  }
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void players_intel_callback(GtkWidget *w, gpointer data)
//{
//  GList *selection;
//  gint row;
//  int player_index;
//
//  if(!(selection=GTK_CLIST(players_list).selection))
//      return;
//
//  row = GPOINTER_TO_INT(selection.data);
//  player_index = LI_2_PI(row);
//
//  if (can_intel_with_player(&Game.game.players[player_index])) {
//    popup_intel_dialog(&Game.game.players[player_index]);
//  }
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void players_sship_callback(GtkWidget *w, gpointer data)
//{
//  GList *selection;
//  gint row;
//  int player_index;
//
//  if(!(selection=GTK_CLIST(players_list).selection))
//      return;
//
//  row = GPOINTER_TO_INT(selection.data);
//  player_index = LI_2_PI(row);
//
//  popup_spaceship_dialog(&Game.game.players[player_index]);
//}
}