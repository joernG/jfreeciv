package client.gui_gtk_2_0;

public class Spaceshipdlg{

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
//#include "map.h"
//#include "mem.h"
//#include "packets.h"
//#include "player.h"
//#include "shared.h"
//#include "support.h"
//
//#include "clinet.h"
//#include "colors.h"
//#include "dialogs.h"
//#include "graphics.h"
//#include "gui_main.h"
//#include "gui_stuff.h"
//#include "helpdlg.h"
//#include "inputdlg.h"
//#include "mapctrl.h"
//#include "mapview.h"
//#include "options.h"
//#include "repodlgs.h"
//#include "spaceship.h"
//#include "tilespec.h"
//#include "climisc.h"
//#include "text.h"
//
//#include "spaceshipdlg.h"
//
//struct spaceship_dialog {
//  player pplayer;
//  gui_dialog shell;
//  GtkWidget *main_form;
//  GtkWidget *info_label;
//  GtkWidget *image_canvas;
//};
//
//#define SPECLIST_TAG dialog
//#define SPECLIST_TYPE struct spaceship_dialog
//#include "speclist.h"
//
//#define dialog_list_iterate(dialoglist, pdialog) \
//    TYPED_LIST_ITERATE(struct spaceship_dialog, dialoglist, pdialog)
//#define dialog_list_iterate_end  LIST_ITERATE_END
//
//static Speclists<dialog> dialog_list;
//static boolean dialog_list_has_been_initialised = false;
//
//static spaceship_dialog get_spaceship_dialog(player pplayer);
//static spaceship_dialog create_spaceship_dialog(struct player
//							*pplayer);
//
//static void spaceship_dialog_update_image(spaceship_dialog pdialog);
//static void spaceship_dialog_update_info(spaceship_dialog pdialog);
//
///****************************************************************
//...
//*****************************************************************/
//spaceship_dialog get_spaceship_dialog(player pplayer)
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
//void refresh_spaceship_dialog(player pplayer)
//{
//  spaceship_dialog pdialog;
//  player_spaceship pship;
//
//  if(!(pdialog=get_spaceship_dialog(pplayer)))
//    return;
//
//  pship=&(pdialog.pplayer.spaceship);
//
//  if(Game.game.spacerace
//     && pplayer.player_no == Game.game.player_idx
//     && pship.state == spaceship_state.SSHIP_STARTED
//     && pship.success_rate > 0.0) {
//    gui_dialog_set_response_sensitive(pdialog.shell,
//	GTK_RESPONSE_ACCEPT, true);
//  } else {
//    gui_dialog_set_response_sensitive(pdialog.shell,
//	GTK_RESPONSE_ACCEPT, false);
//  }
//  
//  spaceship_dialog_update_info(pdialog);
//  spaceship_dialog_update_image(pdialog);
//}
//
///****************************************************************
//popup the dialog 10% inside the main-window 
//*****************************************************************/
//void popup_spaceship_dialog(player pplayer)
//{
//  spaceship_dialog pdialog;
//  
//  if(!(pdialog=get_spaceship_dialog(pplayer)))
//    pdialog=create_spaceship_dialog(pplayer);
//
//  gui_dialog_raise(pdialog.shell);
//}
//
///****************************************************************
//popdown the dialog 
//*****************************************************************/
//void popdown_spaceship_dialog(player pplayer)
//{
//  spaceship_dialog pdialog;
//  
//  if((pdialog=get_spaceship_dialog(pplayer)))
//    gui_dialog_destroy(pdialog.shell);
//}
//
//
//
///****************************************************************
//...
//*****************************************************************/
//static gboolean spaceship_image_canvas_expose(GtkWidget *widget,
//					      GdkEventExpose *ev,
//					      gpointer data)
//{
//  spaceship_dialog pdialog;
//  
//  pdialog=(spaceship_dialog )data;
//  spaceship_dialog_update_image(pdialog);
//  return true;
//}
//
///****************************************************************
//...
//*****************************************************************/
//static void spaceship_destroy_callback(GtkWidget *w, gpointer data)
//{
//  spaceship_dialog pdialog = (spaceship_dialog )data;
//  
//  dialog_list_unlink(&dialog_list, pdialog);
//
//  free(pdialog);
//}
//
///****************************************************************
//...
//*****************************************************************/
//static void spaceship_response(gui_dialog dlg, int response)
//{
//  switch (response) {
//  case GTK_RESPONSE_ACCEPT:
//    {
//      send_packet_spaceship_launch(&aconnection);
//    }
//    break;
//
//  default:
//    gui_dialog_destroy(dlg);
//    break;
//  }
//}
//  
///****************************************************************
//...
//*****************************************************************/
//spaceship_dialog create_spaceship_dialog(player pplayer)
//{
//  spaceship_dialog pdialog;
//  GtkWidget *hbox, *frame;
//  
//  pdialog=fc_malloc(sizeof(struct spaceship_dialog));
//  pdialog.pplayer=pplayer;
//
//  gui_dialog_new(&pdialog.shell, GTK_NOTEBOOK(top_notebook));
//  gui_dialog_set_title(pdialog.shell, pplayer.name);
//
//  gui_dialog_add_button(pdialog.shell,
//      "_Launch", GTK_RESPONSE_ACCEPT);
//  gui_dialog_add_button(pdialog.shell,
//      GTK_STOCK_CLOSE, GTK_RESPONSE_CLOSE);
//
//  g_signal_connect(pdialog.shell.vbox, "destroy",
//		   G_CALLBACK(spaceship_destroy_callback), pdialog);
//  gui_dialog_response_set_callback(pdialog.shell, spaceship_response);
//
//  hbox=gtk_hbox_new(false, 5);
//  gtk_box_pack_start(GTK_BOX(pdialog.shell.vbox), hbox, false, false, 0);
//
//  frame=gtk_frame_new(null);
//  gtk_box_pack_start(GTK_BOX(hbox), frame, false, false, 0);
//
//  pdialog.image_canvas=gtk_drawing_area_new();
//  GTK_WIDGET_SET_FLAGS(pdialog.image_canvas, GTK_CAN_FOCUS);
//  gtk_widget_set_size_request(pdialog.image_canvas,
//			      sprites.spaceship.habitation.width*7,
//			      sprites.spaceship.habitation.height*7);
//
//  gtk_widget_set_events(pdialog.image_canvas, GDK_EXPOSURE_MASK);
//  gtk_container_add(GTK_CONTAINER(frame), pdialog.image_canvas);
//  gtk_widget_realize(pdialog.image_canvas);
//  
//  g_signal_connect(pdialog.image_canvas, "expose_event",
//		   G_CALLBACK(spaceship_image_canvas_expose), pdialog);
//
//  pdialog.info_label = gtk_label_new(get_spaceship_descr(null));
//
//  gtk_label_set_justify(GTK_LABEL(pdialog.info_label), GTK_JUSTIFY_LEFT);
//  gtk_misc_set_alignment(GTK_MISC(pdialog.info_label), 0.0, 0.0);
//
//  gtk_box_pack_start(GTK_BOX(hbox), pdialog.info_label, false, false, 0);
//  gtk_widget_set_name(pdialog.info_label, "spaceship label");
//
//  &dialog_list.foo_list_insert(pdialog);
//
//  gtk_widget_grab_focus(pdialog.image_canvas);
//
//  gui_dialog_show_all(pdialog.shell);
//
//  refresh_spaceship_dialog(pdialog.pplayer);
//
//  return pdialog;
//}
//
///****************************************************************
//...
//*****************************************************************/
//void spaceship_dialog_update_info(spaceship_dialog pdialog)
//{
//  gtk_label_set_text(GTK_LABEL(pdialog.info_label),
//		     get_spaceship_descr(&pdialog.pplayer.spaceship));
//}
//
///****************************************************************
//...
//Should also check connectedness, and show non-connected
//parts differently.
//*****************************************************************/
//void spaceship_dialog_update_image(spaceship_dialog pdialog)
//{
//  int i, j, k, x, y;  
//  Sprite sprite = sprites.spaceship.habitation;   /* for size */
//  player_spaceship ship = &pdialog.pplayer.spaceship;
//
//  gdk_gc_set_foreground(fill_bg_gc, colors_standard[COLOR_STD_BLACK]);
//  gdk_draw_rectangle(pdialog.image_canvas.window, fill_bg_gc, true,
//		0, 0, sprite.width * 7, sprite.height * 7);
//
//  for (i=0; i < player_spaceship.NUM_SS_MODULES; i++) {
//    j = i/3;
//    k = i%3;
//    if ((k==0 && j >= ship.habitation)
//	|| (k==1 && j >= ship.life_support)
//	|| (k==2 && j >= ship.solar_panels)) {
//      continue;
//    }
//    x = Spaceship.modules_info[i].x * sprite.width  / 4 - sprite.width / 2;
//    y = Spaceship.modules_info[i].y * sprite.height / 4 - sprite.height / 2;
//
//    sprite = (k==0 ? sprites.spaceship.habitation :
//	      k==1 ? sprites.spaceship.life_support :
//	             sprites.spaceship.solar_panels);
//    gdk_gc_set_clip_origin(civ_gc, x, y);
//    gdk_gc_set_clip_mask(civ_gc, sprite.mask);
//    gdk_draw_drawable(pdialog.image_canvas.window, civ_gc, sprite.pixmap, 
//	      0, 0,
//	      x, y,
//	      sprite.width, sprite.height);
//    gdk_gc_set_clip_mask(civ_gc,null);
//  }
//
//  for (i=0; i < player_spaceship.NUM_SS_COMPONENTS; i++) {
//    j = i/2;
//    k = i%2;
//    if ((k==0 && j >= ship.fuel)
//	|| (k==1 && j >= ship.propulsion)) {
//      continue;
//    }
//    x = Spaceship.components_info[i].x * sprite.width  / 4 - sprite.width / 2;
//    y = Spaceship.components_info[i].y * sprite.height / 4 - sprite.height / 2;
//
//    sprite = (k==0) ? sprites.spaceship.fuel : sprites.spaceship.propulsion;
//
//    gdk_gc_set_clip_origin(civ_gc, x, y);
//    gdk_gc_set_clip_mask(civ_gc, sprite.mask);
//    gdk_draw_drawable(pdialog.image_canvas.window, civ_gc, sprite.pixmap,
//	      0, 0,
//	      x, y,
//	      sprite.width, sprite.height);
//    gdk_gc_set_clip_mask(civ_gc,null);
//  }
//
//  sprite = sprites.spaceship.structural;
//
//  for (i=0; i < player_spaceship.NUM_SS_STRUCTURALS; i++) {
//    if (!ship.structure[i])
//      continue;
//    x = Spaceship.structurals_info[i].x * sprite.width  / 4 - sprite.width / 2;
//    y = Spaceship.structurals_info[i].y * sprite.height / 4 - sprite.height / 2;
//
//    gdk_gc_set_clip_origin(civ_gc, x, y);
//    gdk_gc_set_clip_mask(civ_gc, sprite.mask);
//    gdk_draw_drawable(pdialog.image_canvas.window, civ_gc, sprite.pixmap,
//	      0, 0,
//	      x, y,
//	      sprite.width, sprite.height);
//    gdk_gc_set_clip_mask(civ_gc,null);
//  }
//}
//
}