package client.gui_gtk;

public class Happiness{

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
//#include <gtk/gtk.h>
//
//#include "city.h"
//#include "fcintl.h"
//#include "game.h"
//#include "government.h"
//#include "mem.h"
//#include "support.h"
//
//#include "text.h"
//#include "tilespec.h"
//
//#include "graphics.h"
//#include "gui_main.h"
//#include "gui_stuff.h"
//#include "mapview.h"
//
//#include "happiness.h"
//
///* semi-arbitrary number that controls the width of the happiness widget */
//public static final int HAPPINESS_PIX_WIDTH = 23;
//
//public static final int NUM_HAPPINESS_MODIFIERS = 5;
//
//enum { CITIES, LUXURIES, BUILDINGS, UNITS, WONDERS };
//
//struct happiness_dialog {
//  city pcity;
//  GtkWidget *shell;
//  GtkWidget *cityname_label;
//  GtkWidget *hpixmaps[NUM_HAPPINESS_MODIFIERS];
//  GtkWidget *hlabels[NUM_HAPPINESS_MODIFIERS];
//  GtkWidget *close;
//};
//
//#define SPECLIST_TAG dialog
//#define SPECLIST_TYPE struct happiness_dialog
//#include "speclist.h"
//
//#define dialog_list_iterate(dialoglist, pdialog) \
//    TYPED_LIST_ITERATE(struct happiness_dialog, dialoglist, pdialog)
//#define dialog_list_iterate_end  LIST_ITERATE_END
//
//static Speclists<dialog> dialog_list;
//static boolean dialog_list_has_been_initialised = false;
//
//static GdkPixmap *create_happiness_pixmap(city pcity, int index);
//static happiness_dialog get_happiness_dialog(city pcity);
//static happiness_dialog create_happiness_dialog(struct city
//							*pcity);
//static void happiness_dialog_update_cities(struct happiness_dialog
//					   *pdialog);
//static void happiness_dialog_update_luxury(struct happiness_dialog
//					   *pdialog);
//static void happiness_dialog_update_buildings(struct happiness_dialog
//					      *pdialog);
//static void happiness_dialog_update_units(struct happiness_dialog
//					  *pdialog);
//static void happiness_dialog_update_wonders(struct happiness_dialog
//					    *pdialog);
//
///****************************************************************
//...
//*****************************************************************/
//static happiness_dialog get_happiness_dialog(city pcity)
//{
//  if (!dialog_list_has_been_initialised) {
//    dialog_list_init(&dialog_list);
//    dialog_list_has_been_initialised = true;
//  }
//
//  for (dialog pdialog : dialog_list.data) {
//    if (pdialog.pcity == pcity) {
//      return pdialog;
//    }
//  } }
//
//  return null;
//}
//
///**************************************************************************
//...
//**************************************************************************/
//static happiness_dialog create_happiness_dialog(city pcity)
//{
//  int i;
//  happiness_dialog pdialog;
//  GtkWidget *vbox;
//  /* GtkAccelGroup *accel=gtk_accel_group_new(); */
//
//  pdialog = fc_malloc(sizeof(struct happiness_dialog));
//  pdialog.pcity = pcity;
//
//  pdialog.shell = gtk_vbox_new(false, 0);
//
//  pdialog.cityname_label = gtk_frame_new("Happiness");
//  gtk_box_pack_start(GTK_BOX(pdialog.shell),
//		     pdialog.cityname_label, true, true, 0);
//
//  vbox = gtk_vbox_new(false, 0);
//  gtk_container_add(GTK_CONTAINER(pdialog.cityname_label), vbox);
//  gtk_container_border_width(GTK_CONTAINER(vbox), 2);
//
//
//  for (i = 0; i < NUM_HAPPINESS_MODIFIERS; i++) {
//    pdialog.hpixmaps[i] =
//	gtk_pixmap_new(create_happiness_pixmap(pcity, i), null);
//    gtk_box_pack_start(GTK_BOX(vbox), pdialog.hpixmaps[i], false, false,
//		       0);
//
//    pdialog.hlabels[i] = gtk_label_new("");
//    gtk_box_pack_start(GTK_BOX(vbox), pdialog.hlabels[i], true, false, 0);
//
//    gtk_misc_set_alignment(GTK_MISC(pdialog.hpixmaps[i]), 0, 0);
//    gtk_misc_set_alignment(GTK_MISC(pdialog.hlabels[i]), 0, 0);
//    gtk_label_set_justify(GTK_LABEL(pdialog.hlabels[i]),
//			  GTK_JUSTIFY_LEFT);
//
//    /* gtk_label_set_line_wrap(GTK_LABEL(pdialog.hlabels[i]), true); */
//  }
//
//  gtk_widget_show_all(pdialog.shell);
//
//  if (!dialog_list_has_been_initialised) {
//    dialog_list_init(&dialog_list);
//    dialog_list_has_been_initialised = true;
//  }
//
//  dialog_list_insert(&dialog_list, pdialog);
//
//  refresh_happiness_dialog(pcity);
//
//  return pdialog;
//}
//
///**************************************************************************
//...
//**************************************************************************/
//static GdkPixmap *create_happiness_pixmap(city pcity, int index)
//{
//  int i;
//  struct citizen_type citizens[MAX_CITY_SIZE];
//  int num_citizens = pcity.size;
//  int pix_width = HAPPINESS_PIX_WIDTH * SMALL_TILE_WIDTH;
//  int offset = Math.min(SMALL_TILE_WIDTH, pix_width / num_citizens);
//  int true_pix_width = (num_citizens - 1) * offset + SMALL_TILE_WIDTH;
//
//  GdkPixmap *happiness_pixmap = gdk_pixmap_new(root_window, true_pix_width,
//					       SMALL_TILE_HEIGHT, -1);
//  struct canvas canvas = {happiness_pixmap};
//
//  get_city_citizen_types(pcity, index, citizens);
//
//  for (i = 0; i < num_citizens; i++) {
//    canvas_put_sprite_full(&canvas,
//			   i * offset, 0,
//			   get_citizen_sprite(citizens[i], i, pcity));
//  }
//
//  return happiness_pixmap;
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void refresh_happiness_dialog(city pcity)
//{
//  int i;
//
//  happiness_dialog pdialog = get_happiness_dialog(pcity);
//
//  for (i = 0; i < 5; i++) {
//    gtk_pixmap_set(GTK_PIXMAP(pdialog.hpixmaps[i]),
//		   create_happiness_pixmap(pdialog.pcity, i), null);
//  }
//
//  happiness_dialog_update_cities(pdialog);
//  happiness_dialog_update_luxury(pdialog);
//  happiness_dialog_update_buildings(pdialog);
//  happiness_dialog_update_units(pdialog);
//  happiness_dialog_update_wonders(pdialog);
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void close_happiness_dialog(city pcity)
//{
//  happiness_dialog pdialog = get_happiness_dialog(pcity);
//
//  gtk_widget_hide(pdialog.shell);
//  dialog_list_unlink(&dialog_list, pdialog);
//
//  gtk_widget_destroy(pdialog.shell);
//  free(pdialog);
//}
//
///**************************************************************************
//...
//**************************************************************************/
//static void happiness_dialog_update_cities(struct happiness_dialog
//					   *pdialog)
//{
//  char buf[512], *bptr = buf;
//  int nleft = sizeof(buf);
//
//  city pcity = pdialog.pcity;
//  player pplayer = &game.players[pcity.owner];
//  government g = get_gov_pcity(pcity);
//  int cities = city_list_size(&pplayer.cities);
//  int content = game.unhappysize;
//  int basis = game.cityfactor + g.empire_size_mod;
//  int step = g.empire_size_inc;
//  int excess = cities - basis;
//  int penalty = 0;
//
//  if (excess > 0) {
//    if (step > 0)
//      penalty = 1 + (excess - 1) / step;
//    else
//      penalty = 1;
//  } else {
//    excess = 0;
//    penalty = 0;
//  }
//
//  bptr = String.format
//	      "Cities: %d total, %d over threshold of %d cities.\n",
//	      cities, excess, basis);
//  bptr = end_of_strn(bptr, &nleft);
//
//  bptr = String.format "%d content before penalty with ", content);
//  bptr = end_of_strn(bptr, &nleft);
//  bptr = String.format "%d additional unhappy citizens.", penalty);
//  bptr = end_of_strn(bptr, &nleft);
//
//  gtk_set_label(pdialog.hlabels[CITIES], buf);
//}
//
///**************************************************************************
//...
//**************************************************************************/
//static void happiness_dialog_update_luxury(struct happiness_dialog
//					   *pdialog)
//{
//  char buf[512], *bptr = buf;
//  int nleft = sizeof(buf);
//  city pcity = pdialog.pcity;
//
//  bptr = String.format "Luxury: %d total.",
//	      pcity.luxury_total);
//
//  gtk_set_label(pdialog.hlabels[LUXURIES], buf);
//}
//
///**************************************************************************
//...
//**************************************************************************/
//static void happiness_dialog_update_buildings(struct happiness_dialog
//					      *pdialog)
//{
//  gtk_set_label(pdialog.hlabels[BUILDINGS],
//		get_happiness_buildings(pdialog.pcity));
//}
//
///**************************************************************************
//...
//**************************************************************************/
//static void happiness_dialog_update_units(happiness_dialog pdialog)
//{
//  char buf[512], *bptr = buf;
//  int nleft = sizeof(buf);
//  city pcity = pdialog.pcity;
//  government g = get_gov_pcity(pcity);
//  int mlmax = g.martial_law_max;
//  int uhcfac = g.unit_happy_cost_factor;
//
//  bptr = String.format "Units: ");
//  bptr = end_of_strn(bptr, &nleft);
//
//  if (mlmax > 0) {
//    bptr = String.format "Martial law in effect (");
//    bptr = end_of_strn(bptr, &nleft);
//
//    if (mlmax == 100)
//      bptr = String.format "no maximum, ");
//    else
//      bptr = String.format PL("%d unit maximum, ",
//				   "%d units maximum, ", mlmax), mlmax);
//    bptr = end_of_strn(bptr, &nleft);
//
//    bptr = String.format "%d per unit). ", g.martial_law_per);
//  } 
//  else if (uhcfac > 0) {
//    bptr = String.format
//		"Military units in the field may cause unhappiness. ");
//  }
//  else {
//    bptr = String.format
//		"Military units have no happiness effect. ");
//
//  }
//
//  gtk_set_label(pdialog.hlabels[UNITS], buf);
//}
//
///**************************************************************************
//...
//**************************************************************************/
//static void happiness_dialog_update_wonders(struct happiness_dialog
//					    *pdialog)
//{
//  gtk_set_label(pdialog.hlabels[WONDERS],
//		get_happiness_wonders(pdialog.pcity));
//}
//
///**************************************************************************
//...
//**************************************************************************/
//GtkWidget *get_top_happiness_display(city pcity)
//{
//  return create_happiness_dialog(pcity).shell;
//}
}