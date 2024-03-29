package client.gui_xaw;

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
//#include <X11/Intrinsic.h>
//#include <X11/StringDefs.h>
//#include <X11/Xaw/Form.h>
//#include <X11/Xaw/Label.h>
//#include <X11/Xaw/Command.h>
//#include <X11/Xaw/SimpleMenu.h>
//#include <X11/Xaw/List.h>
//#include <X11/Xaw/Viewport.h>
//#include <X11/Xaw/AsciiText.h>  
//#include <X11/IntrinsicP.h>
//
//#include "canvas.h"
//#include "pixcomm.h"
//
//#include "fcintl.h"
//#include "Game.game.h"
//#include "Map.map.h"
//#include "mem.h"
//#include "packets.h"
//#include "player.h"
//#include "shared.h"
//#include "spaceship.h"
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
//#include "repodlgs.h"
//#include "text.h"
//#include "tilespec.h"
//#include "climisc.h"
//
//#include "spaceshipdlg.h"
//
//struct spaceship_dialog {
//  player pplayer;
//  Widget shell;
//  Widget main_form;
//  Widget player_label;
//  Widget info_label;
//  Widget image_canvas;
//  Widget launch_command;
//  Widget close_command;
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
//spaceship_dialog get_spaceship_dialog(player pplayer);
//spaceship_dialog create_spaceship_dialog(player pplayer);
//void close_spaceship_dialog(spaceship_dialog pdialog);
//
//void spaceship_dialog_update_image(spaceship_dialog pdialog);
//void spaceship_dialog_update_info(spaceship_dialog pdialog);
//
//void spaceship_close_callback(Widget w, XtPointer client_data, XtPointer call_data);
//void spaceship_launch_callback(Widget w, XtPointer client_data, XtPointer call_data);
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
//     && pship.success_rate > 0) {
//    XtSetSensitive(pdialog.launch_command, true);
//  } else {
//    XtSetSensitive(pdialog.launch_command, false);
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
//  xaw_set_relative_position(toplevel, pdialog.shell, 10, 10);
//  XtPopup(pdialog.shell, XtGrabNone);
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
//    close_spaceship_dialog(pdialog);
//}
//
//
//
///****************************************************************
//...
//*****************************************************************/
//static void spaceship_image_canvas_expose(Widget w, XEvent *event,
//					  Region exposed, void *client_data)
//{
//  spaceship_dialog pdialog;
//  
//  pdialog=(spaceship_dialog )client_data;
//  spaceship_dialog_update_image(pdialog);
//}
//
//
///****************************************************************
//...
//*****************************************************************/
//spaceship_dialog create_spaceship_dialog(player pplayer)
//{
//  spaceship_dialog pdialog;
//
//  pdialog=fc_malloc(sizeof(struct spaceship_dialog));
//  pdialog.pplayer=pplayer;
//
//  pdialog.shell=XtVaCreatePopupShell(pplayer.name,
//				      topLevelShellWidgetClass,
//				      toplevel, 
//				      XtNallowShellResize, true, 
//				      null);
//  
//  pdialog.main_form=
//    XtVaCreateManagedWidget("spaceshipmainform", 
//			    formWidgetClass, 
//			    pdialog.shell, 
//			    null);
//  pdialog.player_label=
//    XtVaCreateManagedWidget("spaceshipplayerlabel", 
//			    labelWidgetClass,
//			    pdialog.main_form,
//			    null);
//
//  XtVaSetValues(pdialog.player_label, XtNlabel, (XtArgVal)pplayer.name, null);
//
//  pdialog.image_canvas=
//    XtVaCreateManagedWidget("spaceshipimagecanvas",
//			    xfwfcanvasWidgetClass,
//			    pdialog.main_form,
//			    "exposeProc", (XtArgVal)spaceship_image_canvas_expose,
//			    "exposeProcData", (XtArgVal)pdialog,
//			    XtNwidth,  sprites.spaceship.habitation.width * 7,
//			    XtNheight, sprites.spaceship.habitation.height * 7,
//			    null);
//
//  pdialog.info_label=
//    XtVaCreateManagedWidget("spaceshipinfolabel", 
//			    labelWidgetClass,
//			    pdialog.main_form,
//			    null);
//
//  
//  pdialog.close_command=
//    I_L(XtVaCreateManagedWidget("spaceshipclosecommand", commandWidgetClass,
//				pdialog.main_form, null));
//
//  pdialog.launch_command=
//    I_L(XtVaCreateManagedWidget("spaceshiplaunchcommand", commandWidgetClass,
//				pdialog.main_form, null));
//
//  XtAddCallback(pdialog.launch_command, XtNcallback, spaceship_launch_callback,
//		(XtPointer)pdialog);
//
//  XtAddCallback(pdialog.close_command, XtNcallback, spaceship_close_callback,
//		(XtPointer)pdialog);
//
//  &dialog_list.foo_list_insert(pdialog);
//
//  XtRealizeWidget(pdialog.shell);
//
//  refresh_spaceship_dialog(pdialog.pplayer);
//
//  XSetWMProtocols(display, XtWindow(pdialog.shell), &wm_delete_window, 1);
//  XtOverrideTranslations(pdialog.shell, 
//    XtParseTranslationTable ("<Message>WM_PROTOCOLS: msg-close-spaceship()"));
//
//  XtSetKeyboardFocus(pdialog.shell, pdialog.close_command);
//
//  return pdialog;
//}
//
///****************************************************************
//...
//*****************************************************************/
//void spaceship_dialog_update_info(spaceship_dialog pdialog)
//{
//  xaw_set_label(pdialog.info_label,
//		get_spaceship_descr(&pdialog.pplayer.spaceship));
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
//  XSetForeground(display, fill_bg_gc, colors_standard[COLOR_STD_BLACK]);
//  XFillRectangle(display, XtWindow(pdialog.image_canvas), fill_bg_gc, 0, 0,
//		 sprite.width * 7, sprite.height * 7);
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
//    XSetClipOrigin(display, civ_gc, x, y);
//    XSetClipMask(display, civ_gc, sprite.mask);
//    XCopyArea(display, sprite.pixmap, XtWindow(pdialog.image_canvas), 
//	      civ_gc, 
//	      0, 0,
//	      sprite.width, sprite.height, x, y);
//    XSetClipMask(display,civ_gc,None);
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
//    XSetClipOrigin(display, civ_gc, x, y);
//    XSetClipMask(display, civ_gc, sprite.mask);
//    XCopyArea(display, sprite.pixmap, XtWindow(pdialog.image_canvas), 
//	      civ_gc, 
//	      0, 0,
//	      sprite.width, sprite.height, x, y);
//    XSetClipMask(display,civ_gc,None);
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
//    XSetClipOrigin(display, civ_gc, x, y);
//    XSetClipMask(display, civ_gc, sprite.mask);
//    XCopyArea(display, sprite.pixmap, XtWindow(pdialog.image_canvas), 
//	      civ_gc, 
//	      0, 0,
//	      sprite.width, sprite.height, x, y);
//    XSetClipMask(display,civ_gc,None);
//  }
//}
//
//
///****************************************************************
//...
//*****************************************************************/
//void close_spaceship_dialog(spaceship_dialog pdialog)
//{
//  XtDestroyWidget(pdialog.shell);
//  dialog_list_unlink(&dialog_list, pdialog);
//
//  free(pdialog);
//}
//
///****************************************************************
//...
//*****************************************************************/
//void spaceshipdlg_key_close(Widget w)
//{
//  spaceshipdlg_msg_close(XtParent(XtParent(w)));
//}
//
///****************************************************************
//...
//*****************************************************************/
//void spaceshipdlg_msg_close(Widget w)
//{
//  for (dialog pdialog : dialog_list.data) {
//    if (pdialog.shell == w) {
//      close_spaceship_dialog(pdialog);
//      return;
//    }
//  } }
//}
//
//
///****************************************************************
//...
//*****************************************************************/
//void spaceship_close_callback(Widget w, XtPointer client_data, XtPointer call_data)
//{
//  close_spaceship_dialog((spaceship_dialog )client_data);
//}
//
//
///****************************************************************
//...
//*****************************************************************/
//void spaceship_launch_callback(Widget w, XtPointer client_data,
//			       XtPointer call_data)
//{
//  send_packet_spaceship_launch(&aconnection);
//  /* close_spaceship_dialog((spaceship_dialog )client_data); */
//}
}