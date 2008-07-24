package client.gui_xaw;

public class Messagedlg{

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
//#include <X11/Xaw/Command.h>
//#include <X11/Xaw/SimpleMenu.h>
//#include <X11/Xaw/Scrollbar.h>
//#include <X11/Xaw/Toggle.h>     
//#include <X11/Xaw/Viewport.h>     
//
//#include "events.h"
//#include "fcintl.h"
//#include "Game.game.h"
//#include "packets.h"
//#include "player.h"
//#include "shared.h"
//#include "support.h"
//
//#include "clinet.h"
//#include "gui_main.h"
//#include "gui_stuff.h"
//#include "mapview.h"
//#include "optiondlg.h"
//#include "options.h"
//
//#include "messagedlg.h"
//
///*************************************************************************/
//Widget create_messageopt_dialog();
//void messageopt_ok_command_callback(Widget w, XtPointer client_data, 
//			               XtPointer call_data);
//void messageopt_cancel_command_callback(Widget w, XtPointer client_data, 
//					XtPointer call_data);
//static Widget messageopt_toggles[E_LAST][NUM_MW];
//
///**************************************************************************
//... 
//**************************************************************************/
//void popup_messageopt_dialog()
//{
//  Widget shell;
//  int i, j, state;
//
//  shell=create_messageopt_dialog();
//
//  /* Doing this here makes the "No"'s centered consistently */
//  for(i=0; i<E_LAST; i++) {
//    for(j=0; j<NUM_MW; j++) {
//      state = messages_where[i] & (1<<j);
//      XtVaSetValues(messageopt_toggles[i][j],
//		    XtNstate, state,
//		    XtNlabel, state? "Yes" : "No", null);
//    }
//  }
//  
//  xaw_set_relative_position(toplevel, shell, 15, 0);
//  XtPopup(shell, XtGrabNone);
//  XtSetSensitive(main_form, false);
//}
//
///**************************************************************************
//...
//**************************************************************************/
//Widget create_messageopt_dialog()
//{
//  Widget shell, form, title, scrolled, explanation, ok, cancel, col;
//  Widget colhead, space_head;
//  Widget label[E_LAST];
//  Widget longest_label = 0;
//  Widget toggle = 0;
//  int i, longest_len = 0;
//  Dimension width;
//  
//  shell = I_T(XtCreatePopupShell("messageoptpopup", transientShellWidgetClass,
//				 toplevel, null, 0));
//
//  form = XtVaCreateManagedWidget("messageoptform", formWidgetClass, 
//				 shell, null);   
//
//  title = I_L(XtVaCreateManagedWidget("messageopttitle", labelWidgetClass,
//				      form, null));
//
//  explanation = I_L(XtVaCreateManagedWidget("messageoptexpl", labelWidgetClass,
//					    form, null));
//
//  scrolled = XtVaCreateManagedWidget("messageoptscroll", viewportWidgetClass, 
//                                     form, null);
//
//  col = XtVaCreateManagedWidget("messageoptcol", formWidgetClass, 
//                                scrolled, null);
//
//  /* space_head labels are "empty" labels in column heading which are
//   * used so that we can arrange the finalraints without loops.
//   * They essentially act as vertical filler.
//   */
//  space_head = XtVaCreateManagedWidget("messageoptspacehead", labelWidgetClass,
//                                       col, null);
//  
//  colhead = I_L(XtVaCreateManagedWidget("messageoptcolhead", labelWidgetClass,
//                                        col, null));
//
//  for(i = 0; i < E_LAST; i++)  {
//    final String text = get_message_text(sorted_events[i]);
//    int len = text.length();
//
//    label[i] = XtVaCreateManagedWidget("label", labelWidgetClass, col,
//				       XtNlabel, text, XtNfromVert,
//				       (i == 0) ? space_head : label[i - 1],
//				       null);
//
//    if (len > longest_len) {
//      longest_len = len;
//      longest_label = label[i];
//    }
//
//    /* 
//     * The addition of a scrollbar screws things up. There must be a
//     * better way to do this.
//     */
//    XtVaGetValues(label[i], XtNwidth, &width, null);
//    XtVaSetValues(label[i], XtNwidth, width + 15, null);
//  }
//
//  XtVaGetValues(longest_label, XtNwidth, &width, null);
//  XtVaSetValues(space_head, XtNwidth, width + 15, null);
//  XtVaSetValues(colhead, XtNfromHoriz, space_head, null);
//
//  for (i = 0; i < E_LAST; i++) {
//    int j;
//
//    for (j = 0; j < NUM_MW; j++) {
//      toggle = XtVaCreateManagedWidget("toggle", toggleWidgetClass, col,
//				       XtNfromHoriz,
//				       (j == 0 ? space_head : toggle),
//				       XtNfromVert,
//				       (i == 0) ? space_head : label[i - 1],
//				       null);
//      XtAddCallback(toggle, XtNcallback, toggle_callback, null);
//      messageopt_toggles[sorted_events[i]][j] = toggle;
//    }
//  }
//
//  ok = I_L(XtVaCreateManagedWidget("messageoptokcommand",
//				   commandWidgetClass,
//				   form, null));
//  
//  cancel = I_L(XtVaCreateManagedWidget("messageoptcancelcommand",
//				       commandWidgetClass,
//				       form, null));
//	       
//  XtAddCallback(ok, XtNcallback, messageopt_ok_command_callback, 
//                (XtPointer)shell);
//  XtAddCallback(cancel, XtNcallback, messageopt_cancel_command_callback, 
//                (XtPointer)shell);
//  
//  XtRealizeWidget(shell);
//
//  xaw_horiz_center(title);
//  xaw_horiz_center(explanation);
//
//  return shell;
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void messageopt_cancel_command_callback(Widget w, XtPointer client_data, 
//					XtPointer call_data)
//{
//  XtSetSensitive(main_form, true);
//  XtDestroyWidget((Widget)client_data);
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void messageopt_ok_command_callback(Widget w, XtPointer client_data, 
//			               XtPointer call_data)
//{
//  int i, j;
//  Boolean b;
//  
//  XtSetSensitive(main_form, true);
//
//  for(i=0;i<E_LAST;i++)  {
//    messages_where[i] = 0;
//    for(j=0; j<NUM_MW; j++) {
//      XtVaGetValues(messageopt_toggles[i][j], XtNstate, &b, null);
//      if (b) messages_where[i] |= (1<<j);
//    }
//  }
//
//  XtDestroyWidget((Widget)client_data);
//}
}