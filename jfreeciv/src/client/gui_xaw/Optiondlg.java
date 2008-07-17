package client.gui_xaw;

public class Optiondlg{

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
//#include <X11/Intrinsic.h>
//#include <X11/StringDefs.h>
//#include <X11/Xaw/Form.h>
//#include <X11/Xaw/Label.h>
//#include <X11/Xaw/List.h>
//#include <X11/Xaw/Command.h>
//#include <X11/Xaw/MenuButton.h>
//#include <X11/Xaw/SimpleMenu.h>
//#include <X11/Xaw/Scrollbar.h>
//#include <X11/Xaw/SmeBSB.h>
//#include <X11/Xaw/AsciiText.h>  
//#include <X11/Xaw/Toggle.h>     
//
//#include "events.h"
//#include "fcintl.h"
//#include "game.h"
//#include "packets.h"
//#include "player.h"
//#include "shared.h"
//#include "support.h"
//
//#include "chatline.h"
//#include "cityrep.h"
//#include "clinet.h"
//#include "gui_main.h"
//#include "gui_stuff.h"
//#include "mapview.h"
//#include "options.h"
//
//#include "optiondlg.h"
//
//static Widget option_dialog_shell;
//
///******************************************************************/
//void create_option_dialog();
//
//void option_ok_command_callback(Widget w, XtPointer client_data, 
//			        XtPointer call_data);
//void option_cancel_command_callback(Widget w, XtPointer client_data, 
//				    XtPointer call_data);
//
///****************************************************************
//... 
//*****************************************************************/
//void popup_option_dialog()
//{
//  char valstr[64];
//
//  create_option_dialog();
//
//  client_options_iterate(o) {
//    switch (o.type) {
//    case COT_BOOL:
//      XtVaSetValues((Widget) o.p_gui_data, XtNstate, *(o.p_bool_value),
//		    XtNlabel, *(o.p_bool_value) ? "Yes" : "No", null);
//      break;
//    case COT_INT:
//      my_snprintf(valstr, sizeof(valstr), "%d", *(o.p_int_value));
//      XtVaSetValues((Widget) o.p_gui_data, XtNstring, valstr, null);
//      break;
//    case COT_STR:
//      XtVaSetValues((Widget) o.p_gui_data,
//		    o.p_string_vals ? "label" : XtNstring,
//		    o.p_string_value, null);
//      break;
//    }
//  } client_options_iterate_end;
//
//  xaw_set_relative_position(toplevel, option_dialog_shell, 25, 25);
//  XtPopup(option_dialog_shell, XtGrabNone);
//  XtSetSensitive(main_form, false);
//}
//
//
//
///****************************************************************
//  Callback to change the entry for a string option that has
//  a fixed list of possible entries.
//*****************************************************************/
//static void stropt_change_callback(Widget w,
//				   XtPointer client_data,
//				   XtPointer call_data)
//{
//  char* val = (char*)client_data;
//
//  XtVaSetValues(XtParent(XtParent(w)), "label", val, null);
//}
//
///****************************************************************
//...
//*****************************************************************/
//void create_option_dialog()
//{
//  Widget option_form, option_label;
//  Widget option_ok_command, option_cancel_command;
//  Widget prev_widget, longest_label = 0;
//  size_t longest_len = 0;
//  
//  option_dialog_shell =
//    I_T(XtCreatePopupShell("optionpopup", transientShellWidgetClass,
//			   toplevel, null, 0));
//
//  option_form = XtVaCreateManagedWidget("optionform", 
//				        formWidgetClass, 
//				        option_dialog_shell, null);   
//
//  option_label =
//    I_L(XtVaCreateManagedWidget("optionlabel", labelWidgetClass, 
//				option_form, null));
//
//  prev_widget = option_label; /* init the prev-Widget */
//  client_options_iterate(o) {
//    final String descr = _(o.description);
//    size_t len = descr.length();
//
//    /* 
//     * Remember widget so we can reset the vertical position; need to
//     * do this because labels and toggles etc have different heights.
//     */
//    o.p_gui_data = (void *) prev_widget;
//
//    prev_widget = 
//      XtVaCreateManagedWidget("label", labelWidgetClass, option_form,
//			      XtNlabel, descr,
//			      XtNfromVert, prev_widget,
//			      null);
//    if (len > longest_len) {
//      longest_len = len;
//      longest_label = prev_widget;
//    }
//  } client_options_iterate_end;
//
//  client_options_iterate(o) {
//    /* 
//     * At the start of the loop o.p_gui_data will contain the widget
//     * which is above the label widget which is associated with this
//     * option.
//     */
//    switch (o.type) {
//    case COT_BOOL:
//      prev_widget =
//	XtVaCreateManagedWidget("toggle", toggleWidgetClass, option_form,
//				XtNfromHoriz, longest_label,
//				XtNfromVert, o.p_gui_data,
//				null);
//      XtAddCallback(prev_widget, XtNcallback, toggle_callback, null);
//      break;
//    case COT_STR:
//      if (o.p_string_vals) {
//	int i;
//	final String*vals = (*o.p_string_vals)();
//	Widget popupmenu;
//
//	prev_widget = XtVaCreateManagedWidget(o.name,
//					      menuButtonWidgetClass,
//					      option_form,
//					      XtNfromHoriz, longest_label,
//					      XtNfromVert, o.p_gui_data,
//					      null);
//
//	popupmenu = XtVaCreatePopupShell("menu",
//					 simpleMenuWidgetClass,
//					 prev_widget,
//					 null);
//
//	for (i = 0; vals[i]; i++) {
//	  Widget entry = XtVaCreateManagedWidget(vals[i], smeBSBObjectClass,
//						 popupmenu, null);
//	  XtAddCallback(entry, XtNcallback, stropt_change_callback,
//			(XtPointer)(vals[i]));
//	}
//
//	if (i == 0) {
//          /* We could disable this if there was just one possible choice,
//             too, but for values that are uninitialized (empty) this
//             would be confusing. */
//	  XtSetSensitive(prev_widget, false);
//	}
//
//	break;
//      }
//      /* else fall through */
//    case COT_INT:
//      prev_widget =
//	XtVaCreateManagedWidget("input", asciiTextWidgetClass, option_form,
//				XtNfromHoriz, longest_label,
//				XtNfromVert, o.p_gui_data,
//				null);
//      break;
//    }
//
//    /* store the final widget */
//    o.p_gui_data = (void *) prev_widget;
//  } client_options_iterate_end;
//
//  option_ok_command =
//    I_L(XtVaCreateManagedWidget("optionokcommand", commandWidgetClass,
//				option_form, XtNfromVert, prev_widget,
//				null));
//  
//  option_cancel_command =
//    I_L(XtVaCreateManagedWidget("optioncancelcommand", commandWidgetClass,
//				option_form, XtNfromVert, prev_widget,
//				null));
//	
//  XtAddCallback(option_ok_command, XtNcallback, 
//		option_ok_command_callback, null);
//  XtAddCallback(option_cancel_command, XtNcallback, 
//		option_cancel_command_callback, null);
//
//  XtRealizeWidget(option_dialog_shell);
//
//  xaw_horiz_center(option_label);
//}
//
//
///**************************************************************************
// Changes the label of the toggle widget to Yes/No depending on the state of
// the toggle.
//**************************************************************************/
//void toggle_callback(Widget w, XtPointer client_data, XtPointer call_data)
//{
//  Boolean b;
//
//  XtVaGetValues(w, XtNstate, &b, null);
//  XtVaSetValues(w, XtNlabel, b ? "Yes" : "No", null);
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void option_ok_command_callback(Widget w, XtPointer client_data, 
//			       XtPointer call_data)
//{
//  Boolean b;
//  int val;
//  XtPointer dp;
//
//  client_options_iterate(o) {
//    switch (o.type) {
//    case COT_BOOL:
//      b = *(o.p_bool_value);
//      XtVaGetValues((Widget) o.p_gui_data, XtNstate, o.p_bool_value, null);
//      if (b != *(o.p_bool_value) && o.change_callback) {
//	(o.change_callback)(o);
//      }
//      break;
//    case COT_INT:
//      val = *(o.p_int_value);
//      XtVaGetValues(o.p_gui_data, XtNstring, &dp, null);
//      sscanf(dp, "%d", o.p_int_value);
//      if (val != *(o.p_int_value) && o.change_callback) {
//	(o.change_callback)(o);
//      }
//      break;
//    case COT_STR:
//      XtVaGetValues(o.p_gui_data,
//		    o.p_string_vals ? "label" : XtNstring,
//		    &dp, null);
//      if (strcmp(o.p_string_value, dp)) {
//	mystrlcpy(o.p_string_value, dp, o.string_length);
//	if (o.change_callback) {
//	  (o.change_callback)(o);
//	}
//      }
//      break;
//    }
//  } client_options_iterate_end;
//
//  XtSetSensitive(main_form, true);
//  XtDestroyWidget(option_dialog_shell);
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void option_cancel_command_callback(Widget w, XtPointer client_data, 
//				    XtPointer call_data)
//{
//  XtSetSensitive(main_form, true);
//  XtDestroyWidget(option_dialog_shell);
//}
}