package client.gui_xaw;

public class Diplodlg{

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
//
//#include <X11/Intrinsic.h>
//#include <X11/StringDefs.h>
//#include <X11/Xaw/Form.h>
//#include <X11/Xaw/Label.h>
//#include <X11/Xaw/Command.h>
//#include <X11/Xaw/List.h>
//#include <X11/Xaw/SimpleMenu.h>
//#include <X11/Xaw/MenuButton.h>
//#include <X11/Xaw/SmeBSB.h>
//#include <X11/Xaw/SmeLine.h>
//#include <X11/Xaw/AsciiText.h>  
//#include <X11/Xaw/Viewport.h>
//
//#include "fcintl.h"
//#include "game.h"
//#include "government.h"
//#include "map.h"
//#include "mem.h"
//#include "packets.h"
//#include "player.h"
//#include "shared.h"
//#include "support.h"
//
//#include "chatline.h"
//#include "climisc.h"
//#include "clinet.h"
//#include "diptreaty.h"
//#include "gui_main.h"
//#include "gui_stuff.h"
//#include "mapview.h"
//
//#include "diplodlg.h"
//
//public static final int MAX_NUM_CLAUSES = 64;
//
//struct Diplomacy_dialog {
//  struct Treaty treaty;
//  
//  Widget dip_dialog_shell;
//  Widget dip_form, dip_main_form, dip_form0, dip_formm, dip_form1;
//  Widget dip_view;
//  
//  Widget dip_headline0;
//  Widget dip_headlinem;
//  Widget dip_headline1;
//
//  Widget dip_map_menubutton0;
//  Widget dip_map_menubutton1;
//  Widget dip_tech_menubutton0;
//  Widget dip_tech_menubutton1;
//  Widget dip_city_menubutton0;
//  Widget dip_city_menubutton1;
//  Widget dip_gold_label0;
//  Widget dip_gold_label1;
//  Widget dip_gold_input0;
//  Widget dip_gold_input1;
//  Widget dip_vision_button0;
//  Widget dip_vision_button1;
//  Widget dip_pact_menubutton;
//  
//  Widget dip_label;
//  Widget dip_clauselabel;
//  Widget dip_clauselist;
//  Widget dip_acceptlabel0;
//  Widget dip_acceptthumb0;
//  Widget dip_acceptlabel1;
//  Widget dip_acceptthumb1;
//  
//  Widget dip_accept_command;
//  Widget dip_close_command;
//
//  Widget dip_erase_clause_command;
//  
//  char clauselist_strings[MAX_NUM_CLAUSES+1][64];
//  char *clauselist_strings_ptrs[MAX_NUM_CLAUSES+1];
//};
//
//static char *dummy_clause_list_strings[]
//    = { "\n", "\n", "\n", "\n", "\n", "\n", 0 };
//
//public static final int SPECLIST_TAG = dialog;
//#define SPECLIST_TYPE struct Diplomacy_dialog
//#include "speclist.h"
//
//#define dialog_list_iterate(dialoglist, pdialog) \
//    TYPED_LIST_ITERATE(struct Diplomacy_dialog, dialoglist, pdialog)
//#define dialog_list_iterate_end  LIST_ITERATE_END
//
//static Speclists<dialog> dialog_list;
//static boolean dialog_list_list_has_been_initialised = false;
//
//Diplomacy_dialog create_diplomacy_dialog(player plr0, 
//						 player plr1);
//
//static Diplomacy_dialog find_diplomacy_dialog(int other_player_id);
//static void popup_diplomacy_dialog(int other_player_id);
//void diplomacy_dialog_close_callback(Widget w, XtPointer client_data, 
//				    XtPointer call_data);
//void diplomacy_dialog_map_callback(Widget w, XtPointer client_data, 
//				   XtPointer call_data);
//void diplomacy_dialog_seamap_callback(Widget w, XtPointer client_data, 
//				      XtPointer call_data);
//void diplomacy_dialog_erase_clause_callback(Widget w, XtPointer client_data, 
//					    XtPointer call_data);
//void diplomacy_dialog_accept_callback(Widget w, XtPointer client_data, 
//				      XtPointer call_data);
//void diplomacy_dialog_tech_callback(Widget w, XtPointer client_data, 
//				    XtPointer call_data);
//void diplomacy_dialog_city_callback(Widget w, XtPointer client_data, 
//				    XtPointer call_data);
//void diplomacy_dialog_ceasefire_callback(Widget w, XtPointer client_data,
//					 XtPointer call_data);
//void diplomacy_dialog_peace_callback(Widget w, XtPointer client_data,
//					XtPointer call_data);
//void diplomacy_dialog_alliance_callback(Widget w, XtPointer client_data,
//					XtPointer call_data);
//void diplomacy_dialog_vision_callback(Widget w, XtPointer client_data,
//				      XtPointer call_data);
//void close_diplomacy_dialog(Diplomacy_dialog pdialog);
//void update_diplomacy_dialog(Diplomacy_dialog pdialog);
//
//
///****************************************************************
//...
//*****************************************************************/
//void handle_diplomacy_accept_treaty(int counterpart, boolean I_accepted,
//				    boolean other_accepted)
//{
//  Diplomacy_dialog pdialog = find_diplomacy_dialog(counterpart);
//
//  if (!pdialog) {
//    return;
//  }
//
//  pdialog.treaty.accept0 = I_accepted;
//  pdialog.treaty.accept1 = other_accepted;
//
//  update_diplomacy_dialog(pdialog);
//}
//
///****************************************************************
//...
//*****************************************************************/
//void handle_diplomacy_init_meeting(int counterpart, int initiated_from)
//{
//  popup_diplomacy_dialog(counterpart);
//}
//
///****************************************************************
//...
//*****************************************************************/
//void handle_diplomacy_cancel_meeting(int counterpart, int initiated_from)
//{
//  Diplomacy_dialog pdialog = find_diplomacy_dialog(counterpart);
//
//  if (!pdialog) {
//    return;
//  }
//
//  close_diplomacy_dialog(pdialog);
//}
//
///****************************************************************
//...
//*****************************************************************/
//void handle_diplomacy_create_clause(int counterpart, int giver,
//				    enum clause_type type, int value)
//{
//  Diplomacy_dialog pdialog = find_diplomacy_dialog(counterpart);
//
//  if (!pdialog) {
//    return;
//  }
//
//  add_clause(&pdialog.treaty, get_player(giver), type, value);
//  update_diplomacy_dialog(pdialog);
//}
//
///****************************************************************
//...
//*****************************************************************/
//void handle_diplomacy_remove_clause(int counterpart, int giver,
//				    enum clause_type type, int value)
//{
//  Diplomacy_dialog pdialog = find_diplomacy_dialog(counterpart);
//
//  if (!pdialog) {
//    return;
//  }
//
//  remove_clause(&pdialog.treaty, get_player(giver), type, value);
//  update_diplomacy_dialog(pdialog);
//}
//
///****************************************************************
//popup the dialog 10% inside the main-window 
//*****************************************************************/
//static void popup_diplomacy_dialog(int other_player_id)
//{
//  Diplomacy_dialog pdialog = find_diplomacy_dialog(other_player_id);
//
//  if (game.player_ptr.ai.control) {
//    return;			/* Don't show if we are AI controlled. */
//  }
//
//  if (!pdialog) {
//    Position x, y;
//    Dimension width, height;
//
//    pdialog = create_diplomacy_dialog(game.player_ptr,
//				      get_player(other_player_id));
//    XtVaGetValues(toplevel, XtNwidth, &width, XtNheight, &height, null);
//    XtTranslateCoords(toplevel, (Position) width / 10,
//		      (Position) height / 10, &x, &y);
//    XtVaSetValues(pdialog.dip_dialog_shell, XtNx, x, XtNy, y, null);
//  }
//
//  XtPopup(pdialog.dip_dialog_shell, XtGrabNone);
//}
//
//
///****************************************************************
//...
//*****************************************************************/
//static int fill_diplomacy_tech_menu(Widget popupmenu, 
//				    player plr0, player plr1)
//{
//  int i, flag;
//  
//  for(i=A_FIRST, flag=0; i<game.num_tech_types; i++) {
//    if (get_invention(plr0, i) == TECH_KNOWN
//        && (get_invention(plr1, i) == TECH_UNKNOWN
//	    || get_invention(plr1, i) == TECH_REACHABLE)
//        && tech_is_available(plr1, i)) {
//      Widget entry=
//	XtVaCreateManagedWidget(advances[i].name, smeBSBObjectClass, 
//				popupmenu, null);
//      XtAddCallback(entry, XtNcallback, diplomacy_dialog_tech_callback,
//			 INT_TO_XTPOINTER((plr0.player_no << 24) |
//					 (plr1.player_no << 16) |
//					 i));
//      flag=1;
//    }
//  }
//  return flag;
//}
//
///****************************************************************
//Creates a sorted list of plr0's cities, excluding the capital and
//any cities not visible to plr1.  This means that you can only trade 
//cities visible to requesting player.  
//
//                            - Kris Bubendorfer
//*****************************************************************/
//static int fill_diplomacy_city_menu(Widget popupmenu, 
//				    player plr0, player plr1)
//{
//  int i = 0, j = 0, n = city_list_size(&plr0.cities);
//  city *city_list_ptrs;
//  if (n>0) {
//    city_list_ptrs = fc_malloc(sizeof(struct city*)*n);
//  } else {
//    city_list_ptrs = null;
//  }
//
//  for (city pcity : plr0.cities.data) {
//    if (!is_capital(pcity)) {
//      city_list_ptrs[i] = pcity;
//      i++;
//    }
//  } }
//
//  qsort(city_list_ptrs, i, sizeof(struct city*), city_name_compare);
//  
//  for(j=0; j<i; j++) {
//    Widget entry=
//      XtVaCreateManagedWidget(city_list_ptrs[j].name, smeBSBObjectClass, 
//			      popupmenu, null);
//    XtAddCallback(entry, XtNcallback, diplomacy_dialog_city_callback,
//		  INT_TO_XTPOINTER((plr0.player_no << 24) |
//				   (plr1.player_no << 16) |
//				   city_list_ptrs[j].id));
//  }
//  free(city_list_ptrs);
//  return i;
//}
//
//
///****************************************************************
//...
//*****************************************************************/
//Diplomacy_dialog create_diplomacy_dialog(player plr0, 
//						 player plr1)
//{
//  char buf[512], *pheadlinem;
//  Diplomacy_dialog pdialog;
//  Dimension width, height, maxwidth;
//  Widget popupmenu;
//  Widget entry;
//
//  pdialog=fc_malloc(sizeof(struct Diplomacy_dialog));
//  dialog_list_insert(&dialog_list, pdialog);
//  
//  init_treaty(&pdialog.treaty, plr0, plr1);
//  
//  pdialog.dip_dialog_shell =
//    I_T(XtCreatePopupShell("dippopupshell", topLevelShellWidgetClass,
//			   toplevel, null, 0));
//
//  pdialog.dip_form = XtVaCreateManagedWidget("dipform", 
//					      formWidgetClass, 
//					      pdialog.dip_dialog_shell,
//					      null);
//
//  pdialog.dip_main_form = XtVaCreateManagedWidget("dipmainform", 
//						   formWidgetClass, 
//						   pdialog.dip_form,
//						   null);
//  
//  pdialog.dip_form0 = XtVaCreateManagedWidget("dipform0", 
//					       formWidgetClass, 
//					       pdialog.dip_main_form, 
//					       null);
//  
//  pdialog.dip_formm = XtVaCreateManagedWidget("dipformm", 
//					       formWidgetClass, 
//					       pdialog.dip_main_form, 
//					       null);
//
//  pdialog.dip_form1 = XtVaCreateManagedWidget("dipform1", 
//					       formWidgetClass, 
//					       pdialog.dip_main_form, 
//					       null);
//  
//  my_snprintf(buf, sizeof(buf), "The %s offerings",
//	      get_nation_name(plr0.nation));
//  pdialog.dip_headline0=XtVaCreateManagedWidget("dipheadline0", 
//						 labelWidgetClass, 
//						 pdialog.dip_form0, 
//						 XtNlabel, buf,
//						 null);   
//
//  my_snprintf(buf, sizeof(buf), "The %s offerings",
//	      get_nation_name(plr1.nation));
//  pdialog.dip_headline1=XtVaCreateManagedWidget("dipheadline1", 
//						 labelWidgetClass, 
//						 pdialog.dip_form1, 
//						 XtNlabel, buf,
//						 null);   
//
//  
//  pdialog.dip_map_menubutton0 =
//    I_L(XtVaCreateManagedWidget("dipmapmenubutton0", 
//				menuButtonWidgetClass, 
//				pdialog.dip_form0, 
//				null));
//  popupmenu=XtVaCreatePopupShell("menu", 
//				 simpleMenuWidgetClass, 
//				 pdialog.dip_map_menubutton0, 
//				 null);
//  
//  entry=XtVaCreateManagedWidget("World-map", smeBSBObjectClass,
//				popupmenu, null);
//  XtAddCallback(entry, XtNcallback, diplomacy_dialog_map_callback,
//		(XtPointer)pdialog);
//  entry=XtVaCreateManagedWidget("Sea-map", smeBSBObjectClass,
//				popupmenu, null);
//  XtAddCallback(entry, XtNcallback, diplomacy_dialog_seamap_callback,
//		(XtPointer)pdialog);
//  
//  pdialog.dip_map_menubutton1 =
//    I_L(XtVaCreateManagedWidget("dipmapmenubutton1", 
//				menuButtonWidgetClass, 
//				pdialog.dip_form1, 
//				null));
//  popupmenu=XtVaCreatePopupShell("menu", 
//				 simpleMenuWidgetClass, 
//				 pdialog.dip_map_menubutton1, 
//				 null);
//  entry=XtVaCreateManagedWidget("World-map", smeBSBObjectClass,
//				popupmenu, null);
//  XtAddCallback(entry, XtNcallback, diplomacy_dialog_map_callback,
//		(XtPointer)pdialog);
//  entry=XtVaCreateManagedWidget("Sea-map", smeBSBObjectClass,
//				popupmenu, null);
//  XtAddCallback(entry, XtNcallback, diplomacy_dialog_seamap_callback,
//		(XtPointer)pdialog);
//  
//
//  pdialog.dip_tech_menubutton0 =
//    I_L(XtVaCreateManagedWidget("diptechmenubutton0", 
//				menuButtonWidgetClass,
//				pdialog.dip_form0,
//				null));
//  popupmenu=XtVaCreatePopupShell("menu", 
//				 simpleMenuWidgetClass, 
//				 pdialog.dip_tech_menubutton0, 
//				 null);
//
//  if(!fill_diplomacy_tech_menu(popupmenu, plr0, plr1))
//    XtSetSensitive(pdialog.dip_tech_menubutton0, false);
//  
//  
//  pdialog.dip_tech_menubutton1 =
//    I_L(XtVaCreateManagedWidget("diptechmenubutton1", 
//				menuButtonWidgetClass,
//				pdialog.dip_form1,
//				null));
//  popupmenu=XtVaCreatePopupShell("menu", 
//				 simpleMenuWidgetClass, 
//				 pdialog.dip_tech_menubutton1, 
//				 null);
//  if(!fill_diplomacy_tech_menu(popupmenu, plr1, plr0))
//    XtSetSensitive(pdialog.dip_tech_menubutton1, false);
//
//  /* Start of trade city code - Kris Bubendorfer */
//
//  pdialog.dip_city_menubutton0 =
//    I_L(XtVaCreateManagedWidget("dipcitymenubutton0", 
//				menuButtonWidgetClass,
//				pdialog.dip_form0,
//				null));
//  popupmenu=XtVaCreatePopupShell("menu", 
//				 simpleMenuWidgetClass, 
//				 pdialog.dip_city_menubutton0, 
//				 null);
//  
//  XtSetSensitive(pdialog.dip_city_menubutton0, 
//		 fill_diplomacy_city_menu(popupmenu, plr0, plr1));
//  
//  
//  pdialog.dip_city_menubutton1 =
//    I_L(XtVaCreateManagedWidget("dipcitymenubutton1", 
//				menuButtonWidgetClass,
//				pdialog.dip_form1,
//				null));
//  popupmenu=XtVaCreatePopupShell("menu", 
//				 simpleMenuWidgetClass, 
//				 pdialog.dip_city_menubutton1, 
//				 null);
//  
//  XtSetSensitive(pdialog.dip_city_menubutton1, 
//		 fill_diplomacy_city_menu(popupmenu, plr1, plr0));  
//  
//  /* End of trade city code */
//  
//  pdialog.dip_gold_input0=XtVaCreateManagedWidget("dipgoldinput0", 
//						   asciiTextWidgetClass,
//						   pdialog.dip_form0,
//						   null);
//
//  pdialog.dip_gold_input1=XtVaCreateManagedWidget("dipgoldinput1", 
//						   asciiTextWidgetClass,
//						   pdialog.dip_form1,
//						   null);
//  
//  my_snprintf(buf, sizeof(buf), "Gold(max %d)", plr0.economic.gold);
//  pdialog.dip_gold_label0=XtVaCreateManagedWidget("dipgoldlabel0", 
//						   labelWidgetClass,
//						   pdialog.dip_form0,
//						   XtNlabel, buf,
//						   null);
//
//  my_snprintf(buf, sizeof(buf), "Gold(max %d)", plr1.economic.gold);
//  pdialog.dip_gold_label1=XtVaCreateManagedWidget("dipgoldlabel1", 
//						   labelWidgetClass,
//						   pdialog.dip_form1,
//						   XtNlabel, buf,
//						   null);
//
//
//  pdialog.dip_vision_button0 =
//    I_L(XtVaCreateManagedWidget("dipvisionbutton0",
//				commandWidgetClass, 
//				pdialog.dip_form0,
//				null));
//  if (gives_shared_vision(plr0, plr1))
//    XtSetSensitive(pdialog.dip_vision_button0, false);
//
//  pdialog.dip_vision_button1 =
//    I_L(XtVaCreateManagedWidget("dipvisionbutton1",
//				commandWidgetClass, 
//				pdialog.dip_form1,
//				null));
//  if (gives_shared_vision(plr1, plr0))
//    XtSetSensitive(pdialog.dip_vision_button1, false);
//
//  XtAddCallback(pdialog.dip_vision_button0, XtNcallback, 
//		diplomacy_dialog_vision_callback, (XtPointer)pdialog);
//  XtAddCallback(pdialog.dip_vision_button1, XtNcallback, 
//		diplomacy_dialog_vision_callback, (XtPointer)pdialog);
//
//
//  pdialog.dip_pact_menubutton=
//    I_L(XtVaCreateManagedWidget("dippactmenubutton",
//				menuButtonWidgetClass,
//				pdialog.dip_form0,
//				null));
//  popupmenu=XtVaCreatePopupShell("menu", 
//				 simpleMenuWidgetClass, 
//				 pdialog.dip_pact_menubutton, 
//				 null);
//  entry=XtVaCreateManagedWidget(Q"?diplomatic_state:Cease-fire",
//				smeBSBObjectClass, popupmenu, null);
//  XtAddCallback(entry, XtNcallback, diplomacy_dialog_ceasefire_callback,
//		(XtPointer)pdialog);
//  entry=XtVaCreateManagedWidget(Q"?diplomatic_state:Peace",
//				smeBSBObjectClass, popupmenu, null);
//  XtAddCallback(entry, XtNcallback, diplomacy_dialog_peace_callback,
//		(XtPointer)pdialog);
//  entry=XtVaCreateManagedWidget(Q"?diplomatic_state:Alliance",
//				smeBSBObjectClass, popupmenu, null);
//  XtAddCallback(entry, XtNcallback, diplomacy_dialog_alliance_callback,
//		(XtPointer)pdialog);
//  
//  my_snprintf(buf, sizeof(buf),
//	      _("This Eternal Treaty\n"
//		 "marks the results of the diplomatic work between\n"
//		 "The %s %s %s\nand\nThe %s %s %s"),
//	  get_nation_name(plr0.nation),
//	  get_ruler_title(plr0.government, plr0.is_male, plr0.nation),
//	  plr0.name,
//	  get_nation_name(plr1.nation),
//	  get_ruler_title(plr1.government, plr1.is_male, plr1.nation),
//	  plr1.name);
//  
//  pheadlinem=create_centered_string(buf);
//  pdialog.dip_headline1=XtVaCreateManagedWidget("dipheadlinem", 
//						 labelWidgetClass, 
//						 pdialog.dip_formm,
//						 XtNlabel, pheadlinem,
//						 null);
//  
//  pdialog.dip_clauselabel =
//    I_L(XtVaCreateManagedWidget("dipclauselabel",
//				labelWidgetClass, 
//				pdialog.dip_formm, 
//				null));   
//  
//  pdialog.dip_view =  XtVaCreateManagedWidget("dipview",
//					       viewportWidgetClass, 
//					       pdialog.dip_formm, 
//					       null);
//  
//  
//  pdialog.dip_clauselist = XtVaCreateManagedWidget("dipclauselist",
//						    listWidgetClass, 
//						    pdialog.dip_view, 
//						    XtNlist, 
//						    (XtArgVal)dummy_clause_list_strings,
//						    null);
//
//  XtVaGetValues(pdialog.dip_headline1, XtNwidth, &width, null);
//  XtVaSetValues(pdialog.dip_view, XtNwidth, width, null); 
//  XtVaSetValues(pdialog.dip_clauselist, XtNwidth, width, null); 
//
//  my_snprintf(buf, sizeof(buf), "%s view:", get_nation_name(plr0.nation));
//  pdialog.dip_acceptlabel0=XtVaCreateManagedWidget("dipacceptlabel0",
//						    labelWidgetClass, 
//						    pdialog.dip_formm, 
//						    XtNlabel, buf,
//						    null);
//  pdialog.dip_acceptthumb0=XtVaCreateManagedWidget("dipacceptthumb0",
//						    labelWidgetClass, 
//						    pdialog.dip_formm, 
//						    XtNbitmap, get_thumb_pixmap(0),
//						    null);
//  my_snprintf(buf, sizeof(buf), "%s view:", get_nation_name(plr1.nation));
//  pdialog.dip_acceptlabel1=XtVaCreateManagedWidget("dipacceptlabel1",
//						    labelWidgetClass, 
//						    pdialog.dip_formm, 
//						    XtNlabel, buf,
//						    null);
//  pdialog.dip_acceptthumb1=XtVaCreateManagedWidget("dipacceptthumb1",
//						    labelWidgetClass, 
//						    pdialog.dip_formm, 
//						    null);
//
//  
//  pdialog.dip_erase_clause_command =
//    I_L(XtVaCreateManagedWidget("diperaseclausecommand",
//				commandWidgetClass, 
//				pdialog.dip_main_form, 
//				null));
//  
//  pdialog.dip_accept_command =
//    I_L(XtVaCreateManagedWidget("dipacceptcommand", 
//				commandWidgetClass, 
//				pdialog.dip_form,
//				null));
//
//  pdialog.dip_close_command =
//    I_L(XtVaCreateManagedWidget("dipclosecommand", 
//				commandWidgetClass,
//				pdialog.dip_form,
//				null));
//
//  XtAddCallback(pdialog.dip_close_command, XtNcallback, 
//		diplomacy_dialog_close_callback, (XtPointer)pdialog);
//  XtAddCallback(pdialog.dip_erase_clause_command, XtNcallback, 
//		diplomacy_dialog_erase_clause_callback, (XtPointer)pdialog);
//  XtAddCallback(pdialog.dip_accept_command, XtNcallback, 
//		diplomacy_dialog_accept_callback, (XtPointer)pdialog);
//
//
//  XtRealizeWidget(pdialog.dip_dialog_shell);
//
//
//  XtVaGetValues(pdialog.dip_map_menubutton0, XtNwidth, &maxwidth, null);
//  XtVaGetValues(pdialog.dip_tech_menubutton0, XtNwidth, &width, null);
//  XtVaGetValues(pdialog.dip_city_menubutton0, XtNwidth, &width, null);
//  maxwidth=MAX(width, maxwidth);
//  XtVaGetValues(pdialog.dip_gold_input0, XtNwidth, &width, null);
//  maxwidth=MAX(width, maxwidth);
//  XtVaSetValues(pdialog.dip_map_menubutton0, XtNwidth, maxwidth, null);
//  XtVaSetValues(pdialog.dip_tech_menubutton0, XtNwidth, maxwidth, null);
//  XtVaSetValues(pdialog.dip_city_menubutton0, XtNwidth, maxwidth, null);
//  XtVaSetValues(pdialog.dip_gold_input0,  XtNwidth, maxwidth, null);
//  
//  XtVaGetValues(pdialog.dip_formm, XtNheight, &height, null);
//  XtVaSetValues(pdialog.dip_form0, XtNheight, height, null); 
//  XtVaSetValues(pdialog.dip_form1, XtNheight, height, null); 
//
//
//  free(pheadlinem);
//
//  update_diplomacy_dialog(pdialog);
//  
//  return pdialog;
//}
//
//
///**************************************************************************
//...
//**************************************************************************/
//void update_diplomacy_dialog(Diplomacy_dialog pdialog)
//{
//  int i = 0;
//  final int n = sizeof(pdialog.clauselist_strings[0]);
//  
//  for (clause pclause : pdialog.treaty.clauses.data) {
//    client_diplomacy_clause_string(pdialog.clauselist_strings[i], n, pclause);
//    pdialog.clauselist_strings_ptrs[i]=pdialog.clauselist_strings[i];
//    i++;
//  } }
//
//  pdialog.clauselist_strings_ptrs[i]=0;
//  XawListChange(pdialog.dip_clauselist, pdialog.clauselist_strings_ptrs, 
//		0, 0, false);
//
///* force refresh of viewport so that scrollbar is added
//   sun seems to need this */ 
//  XtVaSetValues(pdialog.dip_view, XtNforceBars, false, null);
//  XtVaSetValues(pdialog.dip_view, XtNforceBars, true, null);
//
//  xaw_set_bitmap(pdialog.dip_acceptthumb0,
//		 get_thumb_pixmap(pdialog.treaty.accept0));
//  xaw_set_bitmap(pdialog.dip_acceptthumb1, 
//		 get_thumb_pixmap(pdialog.treaty.accept1));
//}
//
///****************************************************************
//...
//*****************************************************************/
//void diplomacy_dialog_tech_callback(Widget w, XtPointer client_data,
//				    XtPointer call_data)
//{
//  size_t choice = (size_t) client_data;
//  int giver = (choice >> 24) & 0xff, dest = (choice >> 16) & 0xff, other;
//  int tech = choice & 0xffff;
//
//  if (giver == game.player_idx) {
//    other = dest;
//  } else {
//    other = giver;
//  }
//
//  dsend_packet_diplomacy_create_clause_req(&aconnection, other, giver,
//					   CLAUSE_ADVANCE, tech);
//}
//
///****************************************************************
//Callback for trading cities
//                              - Kris Bubendorfer
//*****************************************************************/
//void diplomacy_dialog_city_callback(Widget w, XtPointer client_data,
//				    XtPointer call_data)
//{
//  size_t choice = (size_t) client_data;
//  int giver = (choice >> 24) & 0xff, dest = (choice >> 16) & 0xff, other;
//  int city = choice & 0xffff;
//
//  if (giver == game.player_idx) {
//    other = dest;
//  } else {
//    other = giver;
//  }
//
//  dsend_packet_diplomacy_create_clause_req(&aconnection, other, giver,
//					   CLAUSE_CITY, city);
//}
//
///****************************************************************
//...
//*****************************************************************/
//void diplomacy_dialog_erase_clause_callback(Widget w, XtPointer client_data, 
//					    XtPointer call_data)
//{
//  Diplomacy_dialog pdialog = (Diplomacy_dialog ) client_data;
//  XawListReturnStruct *ret = XawListShowCurrent(pdialog.dip_clauselist);
//
//  if (ret.list_index != XAW_LIST_NONE) {
//    int i = 0;
//
//    for (clause pclause : pdialog.treaty.clauses.data) {
//      if (i == ret.list_index) {
//	dsend_packet_diplomacy_remove_clause_req(&aconnection,
//						 pdialog.treaty.plr1.
//						 player_no,
//						 pclause.from.player_no,
//						 pclause.type,
//						 pclause.value);
//	return;
//      }
//      i++;
//    } }
//  }
//}
//
///****************************************************************
//...
//*****************************************************************/
//void diplomacy_dialog_map_callback(Widget w, XtPointer client_data,
//				   XtPointer call_data)
//{
//  Diplomacy_dialog pdialog = (Diplomacy_dialog ) client_data;
//  player pgiver =
//      (XtParent(XtParent(w)) ==
//       pdialog.dip_map_menubutton0) ? pdialog.treaty.plr0 : pdialog.
//      treaty.plr1;
//
//  dsend_packet_diplomacy_create_clause_req(&aconnection,
//					   pdialog.treaty.plr1.player_no,
//					   pgiver.player_no, CLAUSE_MAP, 0);
//}
//
///****************************************************************
//...
//*****************************************************************/
//void diplomacy_dialog_seamap_callback(Widget w, XtPointer client_data,
//				      XtPointer call_data)
//{
//  Diplomacy_dialog pdialog = (Diplomacy_dialog ) client_data;
//  player pgiver =
//      (XtParent(XtParent(w)) ==
//       pdialog.dip_map_menubutton0) ? pdialog.treaty.plr0 : pdialog.
//      treaty.plr1;
//
//  dsend_packet_diplomacy_create_clause_req(&aconnection,
//					   pdialog.treaty.plr1.player_no,
//					   pgiver.player_no, CLAUSE_SEAMAP,
//					   0);
//}
//
///****************************************************************
//...
//*****************************************************************/
//void diplomacy_dialog_vision_callback(Widget w, XtPointer client_data, 
//				   XtPointer call_data)
//{
//  Diplomacy_dialog pdialog = (Diplomacy_dialog ) client_data;
//  player pgiver = (w == pdialog.dip_vision_button0) ?
//      pdialog.treaty.plr0 : pdialog.treaty.plr1;
//
//  dsend_packet_diplomacy_create_clause_req(&aconnection,
//					   pdialog.treaty.plr1.player_no,
//					   pgiver.player_no, CLAUSE_VISION,
//					   0);
//}
//
///****************************************************************
//Generic add-a-clause function for adding pact types
//*****************************************************************/
//static void diplomacy_dialog_add_pact_clause(Widget w, XtPointer client_data,
//					     XtPointer call_data, int type)
//{
//  Diplomacy_dialog pdialog = (Diplomacy_dialog ) client_data;
//  player pgiver = (w == pdialog.dip_vision_button0) ?
//      pdialog.treaty.plr0 : pdialog.treaty.plr1;
//
//  dsend_packet_diplomacy_create_clause_req(&aconnection,
//					   pdialog.treaty.plr1.player_no,
//					   pgiver.player_no, type, 0);
//}
//
///****************************************************************
//The cease-fire widget was selected; add a cease-fire to the
//clauses
//*****************************************************************/
//void diplomacy_dialog_ceasefire_callback(Widget w, XtPointer client_data, 
//					 XtPointer call_data)
//{
//  diplomacy_dialog_add_pact_clause(w, client_data, call_data,
//				   CLAUSE_CEASEFIRE);
//}
//
///****************************************************************
//The peace widget was selected; add a peace treaty to the
//clauses
//*****************************************************************/
//void diplomacy_dialog_peace_callback(Widget w, XtPointer client_data, 
//				     XtPointer call_data)
//{
//  diplomacy_dialog_add_pact_clause(w, client_data, call_data,
//				   CLAUSE_PEACE);
//}
//
///****************************************************************
//The alliance widget was selected; add an alliance to the
//clauses
//*****************************************************************/
//void diplomacy_dialog_alliance_callback(Widget w, XtPointer client_data, 
//					XtPointer call_data)
//{
//  diplomacy_dialog_add_pact_clause(w, client_data, call_data,
//				   CLAUSE_ALLIANCE);
//}
//
//
///****************************************************************
//...
//*****************************************************************/
//void diplomacy_dialog_close_callback(Widget w, XtPointer client_data,
//				     XtPointer call_data)
//{
//  Diplomacy_dialog pdialog = (Diplomacy_dialog ) client_data;
//
//  dsend_packet_diplomacy_cancel_meeting_req(&aconnection,
//					    pdialog.treaty.plr1.player_no);
//  close_diplomacy_dialog(pdialog);
//}
//
//
///****************************************************************
//...
//*****************************************************************/
//void diplomacy_dialog_accept_callback(Widget w, XtPointer client_data, 
//				      XtPointer call_data)
//{
//  Diplomacy_dialog pdialog = (Diplomacy_dialog ) client_data;
//
//  dsend_packet_diplomacy_accept_treaty_req(&aconnection,
//					   pdialog.treaty.plr1.player_no);
//}
//
//
///*****************************************************************
//...
//*****************************************************************/
//void close_diplomacy_dialog(Diplomacy_dialog pdialog)
//{
//  XtDestroyWidget(pdialog.dip_dialog_shell);
//  
//  dialog_list_unlink(&dialog_list, pdialog);
//  free(pdialog);
//}
//
///*****************************************************************
//...
//*****************************************************************/
//static Diplomacy_dialog find_diplomacy_dialog(int other_player_id)
//{
//  player plr0 = game.player_ptr, *plr1 = get_player(other_player_id);
//
//  if (!dialog_list_list_has_been_initialised) {
//    dialog_list_init(&dialog_list);
//    dialog_list_list_has_been_initialised = true;
//  }
//
//  for (dialog pdialog : dialog_list.data) {
//    if ((pdialog.treaty.plr0 == plr0 && pdialog.treaty.plr1 == plr1) ||
//	(pdialog.treaty.plr0 == plr1 && pdialog.treaty.plr1 == plr0)) {
//      return pdialog;
//    }
//  } }
//
//  return null;
//}
//
///*****************************************************************
//...
//*****************************************************************/
//static Diplomacy_dialog find_diplomacy_by_input(Widget w)
//{
//  for (dialog pdialog : dialog_list.data) {
//    if ((pdialog.dip_gold_input0 == w) || (pdialog.dip_gold_input1 == w)) {
//      return pdialog;
//    }
//  } }
//
//  return null;
//}
//
///*****************************************************************
//...
//*****************************************************************/
//void diplodlg_key_gold(Widget w)
//{
//  Diplomacy_dialog pdialog = find_diplomacy_by_input(w);
//  
//  if (pdialog) {
//    player pgiver = (w == pdialog.dip_gold_input0) ?
//	pdialog.treaty.plr0 : pdialog.treaty.plr1;
//    XtPointer dp;
//    int amount;
//    
//    XtVaGetValues(w, XtNstring, &dp, null);
//    if (sscanf(dp, "%d", &amount) == 1 && amount >= 0
//	&& amount <= pgiver.economic.gold) {
//      dsend_packet_diplomacy_create_clause_req(&aconnection,
//					       pdialog.treaty.plr1.
//					       player_no, pgiver.player_no,
//					       CLAUSE_GOLD, amount);
//      XtVaSetValues(w, XtNstring, "", null);
//    }
//    else
//      append_output_window("Game: Invalid amount of gold specified.");
//  }
//}
//
///*****************************************************************
//  Close all dialogs, for when client disconnects from game.
//*****************************************************************/
//void close_all_diplomacy_dialogs()
//{
//  if (!dialog_list_list_has_been_initialised) {
//    return;
//  }
//
//  while (dialog_list_size(&dialog_list) > 0) {
//    close_diplomacy_dialog(dialog_list_get(&dialog_list, 0));
//  }
//}
}