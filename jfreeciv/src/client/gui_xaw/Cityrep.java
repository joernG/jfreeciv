package client.gui_xaw;

import common.Game;

public class Cityrep{

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
//#include <X11/Xaw/MenuButton.h>
//#include <X11/Xaw/SimpleMenu.h>
//#include <X11/Xaw/SmeBSB.h>
//#include <X11/Xaw/Toggle.h>
//#include <X11/Xaw/Viewport.h>
//
//#include "city.h"
//#include "fcintl.h"
//#include "Game.game.h"
//#include "log.h"
//#include "mem.h"
//#include "packets.h"
//#include "shared.h"
//#include "support.h"
//#include "unit.h"
//
//#include "climisc.h"
//#include "clinet.h"
//
//#include "chatline.h"
//#include "citydlg.h"
//#include "cityrepdata.h"
//#include "gui_main.h"
//#include "gui_stuff.h"
//#include "mapview.h"
//#include "optiondlg.h"
//#include "options.h"
//#include "repodlgs.h"
//#include "text.h"
//
//#include "cityrep.h"
//
///******************************************************************/
//static void popup_chgall_dialog (Widget parent);
//
///******************************************************************/
//static Widget config_shell;
//static Widget config_toggle[NUM_CREPORT_COLS];
//
//void create_city_report_config_dialog();
//void popup_city_report_config_dialog();
//void config_ok_command_callback(Widget w, XtPointer client_data, 
//				XtPointer call_data);
//
//
///******************************************************************/
//void create_city_report_dialog(boolean make_modal);
//void city_close_callback(Widget w, XtPointer client_data, 
//			 XtPointer call_data);
//void city_center_callback(Widget w, XtPointer client_data, 
//			  XtPointer call_data);
//void city_popup_callback(Widget w, XtPointer client_data, 
//			 XtPointer call_data);
//void city_buy_callback(Widget w, XtPointer client_data, 
//		       XtPointer call_data);
//void city_chgall_callback(Widget w, XtPointer client_data,
//			  XtPointer call_data);
//void city_refresh_callback(Widget w, XtPointer client_data, 
//		       XtPointer call_data);
//void city_change_callback(Widget w, XtPointer client_data, 
//			  XtPointer call_data);
//void city_list_callback(Widget w, XtPointer client_data, 
//			XtPointer call_data);
//void city_config_callback(Widget w, XtPointer client_data, 
//			  XtPointer call_data);
//
//static Widget city_form;
//static Widget city_dialog_shell;
//static Widget city_label;
//static Widget city_viewport;
//static Widget city_list, city_list_label;
//static Widget city_center_command, city_popup_command, city_buy_command,
//       city_chgall_command, city_refresh_command, city_config_command;
//static Widget city_change_command, city_popupmenu;
//
//static int city_dialog_shell_is_modal;
//static city *cities_in_list = null;
//
//static char *dummy_city_list[]={ 
//  "    " +
//  " ",  " ",  " ",  " ",  " ",  " ",  " ",  " ",  " ",  " ",  " ",  " ",  " ",
//  " ",  " ",  " ",  0
//};
//
//
///****************************************************************
// Create the text for a line in the city report; n is size of buffer
//*****************************************************************/
//static void get_city_text(city pcity, char *text, int n)
//{
//  city_report_spec spec;
//  int i;
//
//  text[0] = '\0';		/* init for strcat */
//  for(i=0, spec=city_report_specs; i<NUM_CREPORT_COLS; i++, spec++) {
//    if(!spec.show) continue;
//
//    if(spec.space>0)
//      cat_snprintf(text, n, "%*s", spec.space, " ");
//
//    cat_snprintf(text, n, "%*s", spec.width, (spec.func)(pcity));
//  }
//}
//
///****************************************************************
// Return text line for the column headers for the city report
//*****************************************************************/
//static char *get_city_table_header()
//{
//  static char text[400];
//  city_report_spec spec;
//  int i, j;
//
//  text[0] = '\0';		/* init for strcat */
//  for(j=0; j<=1; j++) {
//    for(i=0, spec=city_report_specs; i<NUM_CREPORT_COLS; i++, spec++) {
//      if(!spec.show) continue;
//
//      if(spec.space>0)
//	cat_snprintf(text, sizeof(text), "%*s", spec.space, " ");
//
//      cat_snprintf(text, sizeof(text), "%*s", spec.width,
//		   (j ? (spec.title2 ? spec.title2 : "")
//		    : (spec.title1 ? spec.title1 : "")));
//    }
//    if (j==0) sz_strlcat(text, "\n");
//  }
//  return text;
//}
//
///****************************************************************
//
//                      CITY REPORT DIALOG
// 
//****************************************************************/
//
///****************************************************************
//...
//****************************************************************/
//void popup_city_report_dialog(boolean make_modal)
//{
//  if(!city_dialog_shell) {
//      Position x, y;
//      Dimension width, height;
//      
//      city_dialog_shell_is_modal=make_modal;
//    
//      if(make_modal)
//	XtSetSensitive(main_form, false);
//      
//      create_city_report_dialog(make_modal);
//      
//      XtVaGetValues(toplevel, XtNwidth, &width, XtNheight, &height, null);
//      
//      XtTranslateCoords(toplevel, (Position) width/10, (Position) height/10,
//			&x, &y);
//      XtVaSetValues(city_dialog_shell, XtNx, x, XtNy, y, null);
//      
//      XtPopup(city_dialog_shell, XtGrabNone);
//
//      /* force refresh of viewport so the scrollbar is added.
//       * Buggy sun athena requires this */
//      XtVaSetValues(city_viewport, XtNforceBars, true, null);
//   }
//}
//
///****************************************************************
//  Closes the cityrep dialog.
//****************************************************************/
//void popdown_city_report_dialog()
//{
//  if (city_dialog_shell) {
//    if (city_dialog_shell_is_modal) {
//      XtSetSensitive(main_form, true);
//    }
//    XtDestroyWidget(city_dialog_shell);
//    city_dialog_shell = 0;
//  }
//}
//
///****************************************************************
//...
//*****************************************************************/
//void create_city_report_dialog(boolean make_modal)
//{
//  Widget close_command;
//  final String report_title;
//  
//  city_dialog_shell =
//    I_T(XtVaCreatePopupShell("reportcitypopup", 
//			     (make_modal ? transientShellWidgetClass :
//			      topLevelShellWidgetClass),
//			     toplevel, null));
//
//  city_form = XtVaCreateManagedWidget("reportcityform", 
//				      formWidgetClass,
//				      city_dialog_shell,
//				      null);   
//
//  report_title=get_centered_report_title("Cities");
//  city_label = XtVaCreateManagedWidget("reportcitylabel", 
//				       labelWidgetClass, 
//				       city_form,
//				       XtNlabel, 
//				       report_title,
//				       null);
//  free((void *) report_title);
//  city_list_label = XtVaCreateManagedWidget("reportcitylistlabel", 
//				            labelWidgetClass, 
//				            city_form,
//					    XtNlabel,
//					    get_city_table_header(),
//				            null);
//  city_viewport = XtVaCreateManagedWidget("reportcityviewport", 
//				          viewportWidgetClass, 
//				          city_form, 
//				          null);
//  
//  city_list = XtVaCreateManagedWidget("reportcitylist", 
//				      listWidgetClass,
//				      city_viewport,
//                                      XtNlist,
//				      (XtArgVal)dummy_city_list,
//				      null);
//
//  close_command =
//    I_L(XtVaCreateManagedWidget("reportcityclosecommand", commandWidgetClass,
//				city_form, null));
//  
//  city_center_command =
//    I_L(XtVaCreateManagedWidget("reportcitycentercommand", commandWidgetClass,
//				city_form, null));
//
//  city_popup_command =
//    I_L(XtVaCreateManagedWidget("reportcitypopupcommand", commandWidgetClass,
//				city_form, null));
//
//  city_popupmenu = 0;
//
//  city_buy_command =
//    I_L(XtVaCreateManagedWidget("reportcitybuycommand",  commandWidgetClass,
//				city_form, null));
//
//  city_change_command =
//    I_L(XtVaCreateManagedWidget("reportcitychangemenubutton", 
//				menuButtonWidgetClass,
//				city_form, null));
//
//  city_chgall_command =
//    I_L(XtVaCreateManagedWidget("reportcitychgallcommand",
//				commandWidgetClass,
//				city_form, null));
//
//  city_refresh_command =
//    I_L(XtVaCreateManagedWidget("reportcityrefreshcommand",
//				commandWidgetClass,
//				city_form, null));
//
//  city_config_command =
//    I_L(XtVaCreateManagedWidget("reportcityconfigcommand",
//				commandWidgetClass,
//				city_form, null));
//
//  XtAddCallback(close_command, XtNcallback, city_close_callback, null);
//  XtAddCallback(city_center_command, XtNcallback, city_center_callback, null);
//  XtAddCallback(city_popup_command, XtNcallback, city_popup_callback, null);
//  XtAddCallback(city_buy_command, XtNcallback, city_buy_callback, null);
//  XtAddCallback(city_chgall_command, XtNcallback, city_chgall_callback, null);
//  XtAddCallback(city_refresh_command, XtNcallback, city_refresh_callback, null);
//  XtAddCallback(city_config_command, XtNcallback, city_config_callback, null);
//  XtAddCallback(city_list, XtNcallback, city_list_callback, null);
//  
//  XtRealizeWidget(city_dialog_shell);
//
//  if (!make_modal) { /* ?? dwp */
//    XSetWMProtocols(display, XtWindow(city_dialog_shell), 
//		    &wm_delete_window, 1);
//    XtOverrideTranslations(city_dialog_shell,
//	 XtParseTranslationTable("<Message>WM_PROTOCOLS: msg-close-city-report()"));
//  }
//
//  city_report_dialog_update();
//}
//
///****************************************************************
//...
//*****************************************************************/
//void city_list_callback(Widget w, XtPointer client_data, 
//			 XtPointer call_data)
//{
//  XawListReturnStruct *ret=XawListShowCurrent(city_list);
//  city pcity;
//
//  if(ret.list_index!=XAW_LIST_NONE && 
//     (pcity=cities_in_list[ret.list_index])) {
//    cid cids[unittype.U_LAST + Improvement.B_LAST];
//    struct item items[unittype.U_LAST + Improvement.B_LAST];
//    int cids_used = 0;
//    size_t i;
//
//    XtSetSensitive(city_change_command, true);
//    XtSetSensitive(city_center_command, true);
//    XtSetSensitive(city_popup_command, true);
//    XtSetSensitive(city_buy_command, city_can_buy(pcity));
//    if (city_popupmenu)
//      XtDestroyWidget(city_popupmenu);
//
//    city_popupmenu=XtVaCreatePopupShell("menu", 
//				        simpleMenuWidgetClass, 
//				        city_change_command,
//				        null);
//
//    for (int i = 0; i < Game.game.num_impr_types; i++) {
//      if (City.can_build_improvement(pcity, i)) {
//	cids[cids_used] = cid_encode(false, i);
//	cids_used++;
//      }
//    } ;
//
//    unit_type_iterate(i) {
//      if (City.can_build_unit(pcity, i)) {
//	cids[cids_used] = cid_encode(true, i);
//	cids_used++;
//      }
//    } unit_type_iterate_end;
//
//    name_and_sort_items(cids, cids_used, items, true, null);
//    
//    for (i = 0; i < cids_used; i++) {
//      Widget entry =
//	  XtVaCreateManagedWidget(items[i].descr, smeBSBObjectClass,
//				  city_popupmenu, null);
//      XtAddCallback(entry, XtNcallback, city_change_callback,
//		    INT_TO_XTPOINTER(items[i].cid));
//    }
//
//    if (cids_used == 0)
//      XtSetSensitive(city_change_command, false);
//  } else {
//    XtSetSensitive(city_change_command, false);
//    XtSetSensitive(city_center_command, false);
//    XtSetSensitive(city_popup_command, false);
//    XtSetSensitive(city_buy_command, false);
//  }
//}
//
///****************************************************************
//...
//*****************************************************************/
//void city_change_callback(Widget w, XtPointer client_data, 
//			 XtPointer call_data)
//{
//  XawListReturnStruct *ret=XawListShowCurrent(city_list);
//  city pcity;
//
//
//
//  if(ret.list_index!=XAW_LIST_NONE && 
//     (pcity=cities_in_list[ret.list_index])) {
//    cid my_cid = (cid) XTPOINTER_TO_INT(client_data);
//    Boolean unit;
//    int build_nr;
//      
//    unit = cid_is_unit(my_cid);
//    build_nr = cid_id(my_cid);
//
//    city_change_production(pcity, unit, build_nr);
//  }
//}
//
///****************************************************************
//...
//*****************************************************************/
//void city_buy_callback(Widget w, XtPointer client_data, XtPointer call_data)
//{
//  XawListReturnStruct *ret = XawListShowCurrent(city_list);
//
//  if (ret.list_index != XAW_LIST_NONE) {
//    city pcity = cities_in_list[ret.list_index];
//    if (pcity) {
//      cityrep_buy(pcity);
//    }
//  }
//}
//
///****************************************************************
//...
//*****************************************************************/
//void city_chgall_callback(Widget w, XtPointer client_data,
//			  XtPointer call_data)
//{
//  popup_chgall_dialog (XtParent (w));
//}
//
///****************************************************************
//...
//*****************************************************************/
//void city_refresh_callback(Widget w, XtPointer client_data,
//			   XtPointer call_data)
//{
//  /* added by Syela - I find this very useful */
//  XawListReturnStruct *ret = XawListShowCurrent(city_list);
//
//  if (ret.list_index != XAW_LIST_NONE) {
//    city pcity = cities_in_list[ret.list_index];
//
//    if (pcity) {
//      dsend_packet_city_refresh(&aconnection, pcity.id);
//    }
//  } else {
//    dsend_packet_city_refresh(&aconnection, 0);
//  }
//}
//
///****************************************************************
//...
//*****************************************************************/
//void city_close_callback(Widget w, XtPointer client_data, 
//			 XtPointer call_data)
//{
//  popdown_city_report_dialog();
//}
//
///****************************************************************
//...
//*****************************************************************/
//void cityrep_msg_close(Widget w)
//{
//  city_close_callback(w, null, null);
//}
//
///****************************************************************
//...
//*****************************************************************/
//void city_center_callback(Widget w, XtPointer client_data, 
//			  XtPointer call_data)
//{
//  XawListReturnStruct *ret=XawListShowCurrent(city_list);
//
//  if(ret.list_index!=XAW_LIST_NONE) {
//    city pcity;
//    if((pcity=cities_in_list[ret.list_index]))
//      center_tile_mapcanvas(pcity.tile);
//  }
//}
//
///****************************************************************
//...
//*****************************************************************/
//void city_popup_callback(Widget w, XtPointer client_data, 
//			 XtPointer call_data)
//{
//  XawListReturnStruct *ret=XawListShowCurrent(city_list);
//
//  if(ret.list_index!=XAW_LIST_NONE) {
//    city pcity;
//    if((pcity=cities_in_list[ret.list_index])) {
//      if (center_when_popup_city) {
//	center_tile_mapcanvas(pcity.tile);
//      }
//      popup_city_dialog(pcity, 0);
//    }
//  }
//}
//
///****************************************************************
//...
//*****************************************************************/
//void city_config_callback(Widget w, XtPointer client_data,
//			  XtPointer call_data)
//{
//  popup_city_report_config_dialog();
//}
//
//public static final int MAX_LEN_CITY_TEXT = 200;
///****************************************************************
//...
//*****************************************************************/
//void city_report_dialog_update()
//{
//  if(is_report_dialogs_frozen()) return;
//  if(city_dialog_shell) {
//    int i=0, n;
//    Dimension width;
//    static int n_alloc = 0;
//    static char **city_list_text = null;
//    final String report_title;
//
//    n = Game.game.player_ptr.cities.foo_list_size();
//    util.freelog(Log.LOG_DEBUG, "%d cities in report", n);
//    if(n_alloc == 0 || n > n_alloc) {
//      int j, n_prev = n_alloc;
//      
//      n_alloc *= 2;
//      if (!n_alloc || n_alloc < n) n_alloc = n + 1;
//      util.freelog(Log.LOG_DEBUG, "city report n_alloc increased to %d", n_alloc);
//      cities_in_list = fc_realloc(cities_in_list,
//				  n_alloc*sizeof(*cities_in_list));
//      city_list_text = fc_realloc(city_list_text, n_alloc*sizeof(char*));
//      for(j=n_prev; j<n_alloc; j++) {
//	city_list_text[j] = fc_malloc(MAX_LEN_CITY_TEXT);
//      }
//    }
//       
//    report_title=get_centered_report_title("Cities");
//    xaw_set_label(city_label, report_title);
//    free((void *) report_title);
//
//    xaw_set_label(city_list_label, get_city_table_header());
//    
//    if (city_popupmenu) {
//      XtDestroyWidget(city_popupmenu);
//      city_popupmenu = 0;
//    }    
//
//    /* Only sort once, in case any cities have duplicate/truncated names.
//     * Plus this should be much faster than sorting on ids which means
//     * having to find city corresponding to id for each comparison.
//     */
//    i=0;
//    for (city pcity : Game.game.player_ptr.cities.data) {
//      cities_in_list[i++] = pcity;
//    } }
//    assert(i==n);
//    qsort(cities_in_list, n, sizeof(struct city*), city_name_compare);
//    for(i=0; i<n; i++) {
//      get_city_text(cities_in_list[i], city_list_text[i], MAX_LEN_CITY_TEXT);
//    }
//    i = n;
//    if(!n) {
//      mystrlcpy(city_list_text[0], 
//		"                                                             ",
//		MAX_LEN_CITY_TEXT);
//      i=1;
//      cities_in_list[0]=null;
//    }
//
//    XawFormDoLayout(city_form, false);
//    XawListChange(city_list, city_list_text, i, 0, true);
//
//    XtVaGetValues(city_list, XtNlongest, &i, null);
//    width=i+10;
//    /* I don't know the proper way to set the width of this viewport widget.
//       Someone who knows is more than welcome to fix this */
//    XtVaSetValues(city_viewport, XtNwidth, width+15, null); 
//    XtVaSetValues(city_list_label, XtNwidth, width, null);
//    XtVaSetValues(city_label, XtNwidth, width+15, null);
//    XawFormDoLayout(city_form, true);
//
//    XtSetSensitive(city_change_command, false);
//    XtSetSensitive(city_center_command, false);
//    XtSetSensitive(city_popup_command, false);
//    XtSetSensitive(city_buy_command, false);
//  }
//}
//
///****************************************************************
//  Update the text for a single city in the city report
//*****************************************************************/
//void city_report_dialog_update_city(city pcity)
//{
//  int i;
//
//  if(is_report_dialogs_frozen()) return;
//  if(!city_dialog_shell) return;
//
//  for(i=0; cities_in_list[i]; i++)  {
//    if(cities_in_list[i]==pcity)  {
//      int n;
//      String *list;
//      Dimension w;
//      char new_city_line[MAX_LEN_CITY_TEXT];
//
//      XtVaGetValues(city_list, XtNnumberStrings, &n, XtNlist, &list, null);
//      if (0 != strcmp(pcity.name, list[i])) {
//	break;
//      }
//      get_city_text(pcity, new_city_line, sizeof(new_city_line));
//      if(strcmp(new_city_line, list[i])==0) return; /* no change */
//      mystrlcpy(list[i], new_city_line, MAX_LEN_CITY_TEXT);
//
//      /* It seems really inefficient to regenerate the whole list just to
//         change one line.  It's also annoying to have to set the size
//	 of each widget explicitly, since Xt is supposed to handle that. */
//      XawFormDoLayout(city_form, false);
//      XawListChange(city_list, list, n, 0, false);
//      XtVaGetValues(city_list, XtNlongest, &n, null);
//      w=n+10;
//      XtVaSetValues(city_viewport, XtNwidth, w+15, null);
//      XtVaSetValues(city_list_label, XtNwidth, w, null);
//      XtVaSetValues(city_label, XtNwidth, w+15, null);
//      XawFormDoLayout(city_form, true);
//      return;
//    }
//  }
//  city_report_dialog_update();
//}
//
///****************************************************************
// After a selection rectangle is defined, make the cities that
// are hilited on the canvas exclusively hilited in the
// City List window.
//*****************************************************************/
//void hilite_cities_from_canvas()
//{
//  /* PORTME */
//}
//
///****************************************************************
// Toggle a city's hilited status.
//*****************************************************************/
//void toggle_city_hilite(city pcity, boolean on_off)
//{
//  /* PORTME */
//}
//
///****************************************************************
//
//                      CITY REPORT CONFIGURE DIALOG
// 
//****************************************************************/
//
///****************************************************************
//... 
//*****************************************************************/
//void popup_city_report_config_dialog()
//{
//  int i;
//
//  if(config_shell)
//    return;
//  
//  create_city_report_config_dialog();
//
//  for(i=1; i<NUM_CREPORT_COLS; i++) {
//    XtVaSetValues(config_toggle[i],
//		  XtNstate, city_report_specs[i].show,
//		  XtNlabel, city_report_specs[i].show?"Yes":"No", null);
//  }
//
//  xaw_set_relative_position(city_dialog_shell, config_shell, 25, 25);
//  XtPopup(config_shell, XtGrabNone);
//  /* XtSetSensitive(main_form, false); */
//}
//
///****************************************************************
//...
//*****************************************************************/
//void create_city_report_config_dialog()
//{
//  Widget config_form, config_label, config_ok_command;
//  Widget config_optlabel=0, above;
//  city_report_spec spec;
//  char buf[64];
//  int i;
//  
//  config_shell = I_T(XtCreatePopupShell("cityconfig", 
//					transientShellWidgetClass,
//					toplevel, null, 0));
//
//  config_form = XtVaCreateManagedWidget("cityconfigform", 
//				        formWidgetClass, 
//				        config_shell, null);   
//
//  config_label = I_L(XtVaCreateManagedWidget("cityconfiglabel", 
//					     labelWidgetClass, 
//					     config_form, null));
//
//  for(i=1, spec=city_report_specs+i; i<NUM_CREPORT_COLS; i++, spec++) {
//    buf = util.my_snprintf( "%-32s", spec.explanation);
//    above = (i==1)?config_label:config_optlabel;
//
//    config_optlabel = XtVaCreateManagedWidget("cityconfiglabel", 
//					      labelWidgetClass,
//					      config_form,
//					      XtNlabel, buf,
//					      XtNfromVert, above,
//					      null);
//    
//    config_toggle[i] = XtVaCreateManagedWidget("cityconfigtoggle", 
//					       toggleWidgetClass, 
//					       config_form,
//					       XtNfromVert, above,
//					       XtNfromHoriz, config_optlabel,
//					       null);
//  }
//
//  config_ok_command =
//    I_L(XtVaCreateManagedWidget("cityconfigokcommand", 
//				commandWidgetClass,
//				config_form,
//				XtNfromVert, config_optlabel,
//				null));
//  
//  XtAddCallback(config_ok_command, XtNcallback, 
//		config_ok_command_callback, null);
//
//  for(i=1; i<NUM_CREPORT_COLS; i++) 
//    XtAddCallback(config_toggle[i], XtNcallback, toggle_callback, null);
//  
//  XtRealizeWidget(config_shell);
//
//  xaw_horiz_center(config_label);
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void config_ok_command_callback(Widget w, XtPointer client_data, 
//				XtPointer call_data)
//{
//  city_report_spec spec;
//  int i;
//  
//  XtDestroyWidget(config_shell);
//
//  for(i=1, spec=city_report_specs+i; i<NUM_CREPORT_COLS; i++, spec++) {
//    Boolean b;
//
//    XtVaGetValues(config_toggle[i], XtNstate, &b, null);
//    spec.show = (boolean) b;
//  }
//  config_shell=0;
//  city_report_dialog_update();
//}
//
///**************************************************************************
//
//                          CHANGE ALL DIALOG
// 
//**************************************************************************/
//
//struct chgall_data
//{
//  struct
//  {
//    Widget shell;
//    Widget fr;
//    Widget to;
//    Widget change;
//    Widget refresh;
//    Widget cancel;
//  } w;
//
//  int fr_count;
//  char *fr_list[unittype.U_LAST + Improvement.B_LAST];
//  cid fr_cids[unittype.U_LAST + Improvement.B_LAST];
//
//  int to_count;
//  char *to_list[unittype.U_LAST + Improvement.B_LAST];
//  cid to_cids[unittype.U_LAST + Improvement.B_LAST];
//
//  int fr_index;
//  int to_index;
//  int may_change;
//};
//
//static chgall_data chgall_state = null;
//
//static void chgall_accept_action (Widget w, XEvent *event,
//				  String *params, Cardinal *num_params);
//static void chgall_cancel_action (Widget w, XEvent *event,
//				  String *params, Cardinal *num_params);
//
//static void chgall_shell_destroy (Widget w, XtPointer client_data,
//				  XtPointer call_data);
//static void chgall_fr_list_callback (Widget w, XtPointer client_data,
//				     XtPointer call_data);
//static void chgall_to_list_callback (Widget w, XtPointer client_data,
//				     XtPointer call_data);
//static void chgall_change_command_callback (Widget w, XtPointer client_data,
//					    XtPointer call_data);
//static void chgall_refresh_command_callback (Widget w, XtPointer client_data,
//					     XtPointer call_data);
//static void chgall_cancel_command_callback (Widget w, XtPointer client_data,
//					    XtPointer call_data);
//
//static void chgall_update_widgets_state (chgall_data state);
//
///**************************************************************************
//...
//**************************************************************************/
//
//static void popup_chgall_dialog (Widget parent)
//{
//  chgall_data state;
//  Widget shell;
//  Widget main_form;
//  Widget fr_label;
//  Widget to_label;
//  Widget fr_viewport;
//  Widget to_viewport;
//  Position x, y;
//  Dimension width, height;
//  static int initialized = false;
//
//  if (!initialized)
//    {
//      static XtActionsRec actions[] =
//      {
//	{ "key-dialog-chgall-accept", chgall_accept_action },
//	{ "key-dialog-chgall-cancel", chgall_cancel_action }
//      };
//
//      initialized = true;
//
//      XtAppAddActions (app_context, actions, XtNumber (actions));
//    }
//
//  if (chgall_state)
//    {
//      XRaiseWindow (display, XtWindow (chgall_state.w.shell));
//      return;
//    }
//
//  shell =
//    I_T(XtCreatePopupShell
//    (
//     "chgalldialog",
//     transientShellWidgetClass,
//     parent,
//     null, 0
//    ));
//
//  state = chgall_state = fc_malloc (sizeof (struct chgall_data));
//  state.w.shell = shell;
//
//  XtAddCallback (state.w.shell, XtNdestroyCallback,
//		 chgall_shell_destroy, state);
//
//  main_form =
//    XtVaCreateManagedWidget
//    (
//     "chgallform",
//     formWidgetClass, 
//     state.w.shell,
//     null
//    );
//
//  fr_label =
//    I_LW(XtVaCreateManagedWidget
//    (
//     "chgallfrlabel",
//     labelWidgetClass, 
//     main_form,
//     null
//    ));
//
//  to_label =
//    I_LW(XtVaCreateManagedWidget
//    (
//     "chgalltolabel",
//     labelWidgetClass, 
//     main_form,
//     null
//    ));
//
//  fr_viewport =
//    XtVaCreateManagedWidget
//    (
//     "chgallfrviewport",
//     viewportWidgetClass,
//     main_form,
//     null
//    );
//
//  state.w.fr =
//    XtVaCreateManagedWidget
//    (
//     "chgallfrlist",
//     listWidgetClass,
//     fr_viewport,
//     null
//    );
//
//  to_viewport =
//    XtVaCreateManagedWidget
//    (
//     "chgalltoviewport",
//     viewportWidgetClass,
//     main_form,
//     null
//    );
//
//  state.w.to =
//    XtVaCreateManagedWidget
//    (
//     "chgalltolist",
//     listWidgetClass,
//     to_viewport,
//     null
//    );
//
//  state.w.change =
//    I_L(XtVaCreateManagedWidget
//    (
//     "chgallchangecommand",
//     commandWidgetClass,
//     main_form,
//     null
//    ));
//
//  state.w.refresh =
//    I_L(XtVaCreateManagedWidget
//    (
//     "chgallrefreshcommand",
//     commandWidgetClass,
//     main_form,
//     null
//    ));
//
//  state.w.cancel =
//    I_L(XtVaCreateManagedWidget
//    (
//     "chgallcancelcommand",
//     commandWidgetClass,
//     main_form,
//     null
//    ));
//
//  XtAddCallback (state.w.fr, XtNcallback,
//		 chgall_fr_list_callback, state);
//  XtAddCallback (state.w.to, XtNcallback,
//		 chgall_to_list_callback, state);
//  XtAddCallback (state.w.change, XtNcallback,
//		 chgall_change_command_callback, state);
//  XtAddCallback (state.w.refresh, XtNcallback,
//		 chgall_refresh_command_callback, state);
//  XtAddCallback (state.w.cancel, XtNcallback,
//		 chgall_cancel_command_callback, state);
//
//  chgall_refresh_command_callback (null, (XtPointer)state, null);
//
//  XtRealizeWidget (state.w.shell);
//  XtVaGetValues (parent, XtNwidth, &width, XtNheight, &height, null);
//  XtTranslateCoords (parent,
//		     (Position)(width / 20), (Position)(height / 20),
//		     &x, &y);
//  XtVaSetValues (state.w.shell, XtNx, x, XtNy, y, null);
//
//  XtPopup (state.w.shell, XtGrabNone);
//
//  /* force refresh of viewports so the scrollbar is added.
//   * Buggy sun athena requires this */
//  XtVaSetValues (fr_viewport, XtNforceBars, true, null);
//  XtVaSetValues (to_viewport, XtNforceBars, true, null);
//}
//
///**************************************************************************
//...
//**************************************************************************/
//
//static void chgall_accept_action (Widget w, XEvent *event,
//				  String *params, Cardinal *num_params)
//{
//  Widget target = XtNameToWidget (w, "chgallchangecommand");
//
//  if (target)
//    {
//      x_simulate_button_click (target);
//    }
//}
//
///**************************************************************************
//...
//**************************************************************************/
//
//static void chgall_cancel_action (Widget w, XEvent *event,
//				  String *params, Cardinal *num_params)
//{
//  Widget target = XtNameToWidget (w, "chgallcancelcommand");
//
//  if (target)
//    {
//      x_simulate_button_click (target);
//    }
//}
//
///**************************************************************************
//...
//**************************************************************************/
//
//static void chgall_shell_destroy(Widget w, XtPointer client_data,
//				 XtPointer call_data)
//{
//  int i;
//
//  for (i = 0; i < chgall_state.fr_count; i++) {
//    free(chgall_state.fr_list[i]);
//  }
//  for (i = 0; i < chgall_state.to_count; i++) {
//    free(chgall_state.to_list[i]);
//  }
//
//  chgall_state = null;
//
//  free(client_data);
//}
//
///**************************************************************************
//...
//**************************************************************************/
//
//static void chgall_fr_list_callback (Widget w, XtPointer client_data,
//				     XtPointer call_data)
//{
//  chgall_update_widgets_state ((chgall_data )client_data);
//}
//
///**************************************************************************
//...
//**************************************************************************/
//
//static void chgall_to_list_callback (Widget w, XtPointer client_data,
//				     XtPointer call_data)
//{
//  chgall_update_widgets_state ((chgall_data )client_data);
//}
//
///**************************************************************************
//...
//**************************************************************************/
//
//static void chgall_change_command_callback (Widget w, XtPointer client_data,
//					    XtPointer call_data)
//{
//  chgall_data state = (chgall_data )client_data;
//
//  chgall_update_widgets_state (state);
//
//  if (!(state.may_change))
//    {
//      return;
//    }
//
//  client_change_all(state.fr_cids[state.fr_index],
//		    state.to_cids[state.to_index]);
//
//  XtDestroyWidget (state.w.shell);
//}
//
///**************************************************************************
//...
//**************************************************************************/
//
//static void chgall_refresh_command_callback(Widget w,
//					    XtPointer client_data,
//					    XtPointer call_data)
//{
//  chgall_data state = (chgall_data ) client_data;
//  cid cids[unittype.U_LAST + Improvement.B_LAST];
//  struct item items[unittype.U_LAST + Improvement.B_LAST];
//  int i;
//
//  state.fr_count = collect_cids2(cids);
//  name_and_sort_items(cids, state.fr_count, items, false, null);
//  for (i = 0; i < state.fr_count; i++) {
//    state.fr_list[i] = (items[i].descr);
//    state.fr_cids[i] = items[i].cid;
//  }
//  XawListChange(state.w.fr, state.fr_list, state.fr_count, 0, false);
//
//  state.to_count = collect_cids3(cids);
//  name_and_sort_items(cids, state.to_count, items, true, null);
//  for (i = 0; i < state.to_count; i++) {
//    state.to_list[i] = (items[i].descr);
//    state.to_cids[i] = items[i].cid;
//  }
//  XawListChange(state.w.to, state.to_list, state.to_count, 0, false);
//
//  chgall_update_widgets_state(state);
//}
//
///**************************************************************************
//...
//**************************************************************************/
//
//static void chgall_cancel_command_callback (Widget w, XtPointer client_data,
//					    XtPointer call_data)
//{
//  chgall_data state = (chgall_data )client_data;
//
//  XtDestroyWidget (state.w.shell);
//}
//
///**************************************************************************
//...
//**************************************************************************/
//
//static void chgall_update_widgets_state (chgall_data state)
//{
//  state.fr_index = (XawListShowCurrent (state.w.fr)).list_index;
//  state.to_index = (XawListShowCurrent (state.w.to)).list_index;
//
//  state.may_change =
//      ((state.fr_index != XAW_LIST_NONE)
//       && (state.to_index != XAW_LIST_NONE)
//       && (state.fr_cids[state.fr_index] !=
//	   state.to_cids[state.to_index]));
//
//  XtSetSensitive (state.w.change, state.may_change);
//}
}