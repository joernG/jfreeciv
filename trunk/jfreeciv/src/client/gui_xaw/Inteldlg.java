package client.gui_xaw;

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
//#include <X11/Intrinsic.h>
//#include <X11/StringDefs.h>
//#include <X11/Xaw/Form.h>
//#include <X11/Xaw/Label.h>
//#include <X11/Xaw/Command.h>
//#include <X11/Xaw/SimpleMenu.h>
//#include <X11/Xaw/List.h>
//
//#include "fcintl.h"
//#include "game.h"
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
//#include "optiondlg.h"
//
//#include "inteldlg.h"
//
///******************************************************************/
//struct intel_dialog {
//  player pplayer;
//
//  Widget intel_dialog_shell;
//  Widget intel_form;
//  Widget intel_label;
//  Widget intel_close_command;
//  Widget intel_diplo_command;
//  boolean intel_dialog_shell_is_raised;
//
//  Widget intel_diplo_dialog_shell;
//  Widget intel_diplo_form;
//  Widget intel_diplo_label;
//  Widget intel_diplo_list;
//  Widget intel_diplo_close_command;
//  boolean intel_diplo_dialog_shell_is_raised;
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
//
///******************************************************************/
//
//intel_dialog get_intel_dialog(player pplayer);
//void popdown_intel_dialog(intel_dialog pdialog);
//void create_intel_dialog(intel_dialog pdialog, boolean raise);
//
//void intel_close_callback(Widget w, XtPointer client_data,
//			  XtPointer call_data);
//void intel_diplo_callback(Widget w, XtPointer client_data,
//			  XtPointer call_data);
//
//void popup_intel_diplo_dialog(player pplayer, boolean raise);
//void popdown_intel_diplo_dialog(intel_dialog pdialog);
//void create_intel_diplo_dialog(intel_dialog pdialog, boolean raise);
//void update_intel_diplo_dialog(intel_dialog pdialog);
//void intel_diplo_close_callback(Widget w, XtPointer client_data,
//				XtPointer call_data);
//
///****************************************************************
//  Get an intelligence dialog for the given player.
//*****************************************************************/
//intel_dialog get_intel_dialog(player pplayer)
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
//  Popup an intelligence dialog for the given player.
//*****************************************************************/
//void popup_intel_dialog(player pplayer)
//{
//  intel_dialog pdialog;
//  boolean raise = false; /* Drop when we'll have raise-parametered popup */
//
//  if (!(pdialog = get_intel_dialog(pplayer))) {
//    pdialog = fc_malloc(sizeof(struct intel_dialog));
//    pdialog.pplayer = pplayer;
//    pdialog.intel_dialog_shell = 0;
//    pdialog.intel_diplo_dialog_shell = 0;
//    dialog_list_insert(&dialog_list, pdialog);
//  }
//
//  if (!(pdialog.intel_dialog_shell)) {
//    create_intel_dialog(pdialog, raise);
//  }
//
//  xaw_set_relative_position(toplevel, pdialog.intel_dialog_shell, 25, 25);
//  XtPopup(pdialog.intel_dialog_shell, XtGrabNone);
//  if (pdialog.intel_dialog_shell_is_raised) {
//    XtSetSensitive(main_form, false);
//  }
//}
//
///****************************************************************
//  Close an intelligence dialog.
//*****************************************************************/
//void popdown_intel_dialog(intel_dialog pdialog)
//{
//  if (pdialog.intel_dialog_shell_is_raised) {
//    XtSetSensitive(main_form, true);
//  }
//  XtDestroyWidget(pdialog.intel_dialog_shell);
//  pdialog.intel_dialog_shell = 0;
//
//  if (!(pdialog.intel_diplo_dialog_shell)) {
//    dialog_list_unlink(&dialog_list, pdialog);
//    free(pdialog);
//  }
//}
//
///****************************************************************
//...
//*****************************************************************/
//void create_intel_dialog(intel_dialog pdialog, boolean raise)
//{
//  char buf[64];
//  city pcity;
//
//  static char *tech_list_names_ptrs[A_LAST+1];
//  static char tech_list_names[A_LAST+1][200];
//  int i, j;
//
//  pdialog.intel_dialog_shell_is_raised = raise;
//
//  I_T(pdialog.intel_dialog_shell =
//    XtCreatePopupShell("intelpopup",
//		       raise ? transientShellWidgetClass
//		       : topLevelShellWidgetClass,
//		       toplevel, null, 0));
//
//  pdialog.intel_form = XtVaCreateManagedWidget("intelform",
//						formWidgetClass,
//						pdialog.intel_dialog_shell,
//						null);
//
//  my_snprintf(buf, sizeof(buf),
//	      "Intelligence Information for the %s Empire",
//	      get_nation_name(pdialog.pplayer.nation));
//
//  pdialog.intel_label = I_L(XtVaCreateManagedWidget("inteltitlelabel",
//						     labelWidgetClass,
//						     pdialog.intel_form,
//						     XtNlabel, buf,
//						     null));
//
//  my_snprintf(buf, sizeof(buf), "Ruler: %s %s",
//	      get_ruler_title(pdialog.pplayer.government,
//			      pdialog.pplayer.is_male,
//			      pdialog.pplayer.nation),
//	      pdialog.pplayer.name);
//  XtVaCreateManagedWidget("intelnamelabel",
//			  labelWidgetClass,
//			  pdialog.intel_form,
//			  XtNlabel, buf,
//			  null);
//
//  my_snprintf(buf, sizeof(buf), "Government: %s",
//	      get_government_name(pdialog.pplayer.government));
//  XtVaCreateManagedWidget("intelgovlabel",
//			  labelWidgetClass,
//			  pdialog.intel_form,
//			  XtNlabel, buf,
//			  null);
//
//  my_snprintf(buf, sizeof(buf), "Gold: %d",
//	      pdialog.pplayer.economic.gold);
//  XtVaCreateManagedWidget("intelgoldlabel",
//			  labelWidgetClass,
//			  pdialog.intel_form,
//			  XtNlabel, buf,
//			  null);
//
//  my_snprintf(buf, sizeof(buf), "Tax: %d%%",
//	      pdialog.pplayer.economic.tax);
//  XtVaCreateManagedWidget("inteltaxlabel",
//			  labelWidgetClass,
//			  pdialog.intel_form,
//			  XtNlabel, buf,
//			  null);
//
//  my_snprintf(buf, sizeof(buf), "Science: %d%%",
//	      pdialog.pplayer.economic.science);
//  XtVaCreateManagedWidget("intelscilabel",
//			  labelWidgetClass,
//			  pdialog.intel_form,
//			  XtNlabel, buf,
//			  null);
//
//  my_snprintf(buf, sizeof(buf), "Luxury: %d%%",
//	      pdialog.pplayer.economic.luxury);
//  XtVaCreateManagedWidget("intelluxlabel",
//			  labelWidgetClass,
//			  pdialog.intel_form,
//			  XtNlabel, buf,
//			  null);
//
//  if (pdialog.pplayer.research.researching == A_UNSET) {
//    my_snprintf(buf, sizeof(buf), "Researching: %s(%d/%d)",
//		advances[A_NONE].name,
//		pdialog.pplayer.research.bulbs_researched,
//		total_bulbs_required(pdialog.pplayer));
//  } else {
//    my_snprintf(buf, sizeof(buf), "Researching: %s(%d/%d)",
//		get_tech_name(pdialog.pplayer,
//			      pdialog.pplayer.research.researching),
//		pdialog.pplayer.research.bulbs_researched,
//		total_bulbs_required(pdialog.pplayer));
//  }
//
//  XtVaCreateManagedWidget("intelreslabel",
//			  labelWidgetClass,
//			  pdialog.intel_form,
//			  XtNlabel, buf,
//			  null);
//
//  pcity = find_palace(pdialog.pplayer);
//  my_snprintf(buf, sizeof(buf), "Capital: %s",
//	      (!pcity)?"(Unknown)":pcity.name);
//  XtVaCreateManagedWidget("intelcapitallabel",
//			  labelWidgetClass,
//			  pdialog.intel_form,
//			  XtNlabel, buf,
//			  null);
//
//  for(i=A_FIRST, j=0; i<game.num_tech_types; i++)
//    if (get_invention(pdialog.pplayer, i) == TECH_KNOWN) {
//      if(get_invention(game.player_ptr, i)==TECH_KNOWN) {
//	sz_strlcpy(tech_list_names[j], advances[i].name);
//      } else {
//	my_snprintf(tech_list_names[j], sizeof(tech_list_names[j]),
//		    "%s*", advances[i].name);
//      }
//      tech_list_names_ptrs[j]=tech_list_names[j];
//      j++;
//    }
//  tech_list_names_ptrs[j]=0;
//
//  XtVaCreateManagedWidget("inteltechlist",
//			  listWidgetClass,
//			  pdialog.intel_form,
//			  XtNlist, tech_list_names_ptrs,
//			  null);
//
//  pdialog.intel_close_command =
//    I_L(XtVaCreateManagedWidget("intelclosecommand",
//				commandWidgetClass,
//				pdialog.intel_form, null));
//
//  pdialog.intel_diplo_command =
//    I_L(XtVaCreateManagedWidget("inteldiplocommand",
//				commandWidgetClass,
//				pdialog.intel_form, null));
//
//  XtAddCallback(pdialog.intel_close_command, XtNcallback,
//		intel_close_callback, (XtPointer)pdialog);
//  XtAddCallback(pdialog.intel_diplo_command, XtNcallback,
//		intel_diplo_callback,
//		INT_TO_XTPOINTER(pdialog.pplayer.player_no));
//  XtRealizeWidget(pdialog.intel_dialog_shell);
//
//  xaw_horiz_center(pdialog.intel_label);
//
//  XSetWMProtocols(display, XtWindow(pdialog.intel_dialog_shell), 
//		  &wm_delete_window, 1);
//  XtOverrideTranslations(pdialog.intel_dialog_shell,
//    XtParseTranslationTable("<Message>WM_PROTOCOLS: msg-close-intel()"));
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
//
///**************************************************************************
//...
//**************************************************************************/
//void intel_close_callback(Widget w, XtPointer client_data,
//			  XtPointer call_data)
//{
//  popdown_intel_dialog((intel_dialog )client_data);
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void intel_diplo_callback(Widget w, XtPointer client_data,
//			  XtPointer call_data)
//{
//  popup_intel_diplo_dialog(&game.players[XTPOINTER_TO_INT(client_data)],
//			   false);
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void intel_dialog_msg_close(Widget w)
//{
//  for (dialog pdialog : dialog_list.data) {
//    if (pdialog.intel_dialog_shell == w) {
//      popdown_intel_dialog(pdialog);
//      return;
//    }
//  } }
//}
//
///****************************************************************
//  Popup a player's relations dialog for the given player.
//*****************************************************************/
//void popup_intel_diplo_dialog(player pplayer, boolean raise)
//{
//  intel_dialog pdialog;
//  
//  if (!(pdialog = get_intel_dialog(pplayer))) {
//    pdialog = fc_malloc(sizeof(struct intel_dialog));
//    pdialog.pplayer = pplayer;
//    pdialog.intel_dialog_shell = 0;
//    pdialog.intel_diplo_dialog_shell = 0;
//    dialog_list_insert(&dialog_list, pdialog);
//  }
//
//  if (!(pdialog.intel_diplo_dialog_shell)) {
//    create_intel_diplo_dialog(pdialog, raise);
//  }
//
//  xaw_set_relative_position(toplevel,
//			    pdialog.intel_diplo_dialog_shell, 25, 25);
//  XtPopup(pdialog.intel_diplo_dialog_shell, XtGrabNone);
//  if (pdialog.intel_diplo_dialog_shell_is_raised) {
//    XtSetSensitive(main_form, false);
//  }
//}
//
///****************************************************************
//  Close a player's relations dialog.
//*****************************************************************/
//void popdown_intel_diplo_dialog(intel_dialog pdialog)
//{
//  if (pdialog.intel_diplo_dialog_shell_is_raised) {
//    XtSetSensitive(main_form, true);
//  }
//  XtDestroyWidget(pdialog.intel_diplo_dialog_shell);
//  pdialog.intel_diplo_dialog_shell = 0;
//
//  if (!(pdialog.intel_dialog_shell)) {
//    dialog_list_unlink(&dialog_list, pdialog);
//    free(pdialog);
//  }
//}
//
///****************************************************************
//  Create a player's relations dialog for the given player.
//*****************************************************************/
//void create_intel_diplo_dialog(intel_dialog pdialog, boolean raise)
//{
//  char buf[64];
//
//  pdialog.intel_diplo_dialog_shell_is_raised = raise;
//
//  pdialog.intel_diplo_dialog_shell =
//    I_IN(I_T(XtCreatePopupShell("inteldiplopopup",
//				raise ? transientShellWidgetClass
//				: topLevelShellWidgetClass,
//				toplevel, null, 0)));
//
//  pdialog.intel_diplo_form =
//    XtVaCreateManagedWidget("inteldiploform",
//			    formWidgetClass,
//			    pdialog.intel_diplo_dialog_shell,
//			    null);
//
//  my_snprintf(buf, sizeof(buf),
//	      "Intelligence Diplomacy Information for the %s Empire",
//	      get_nation_name(pdialog.pplayer.nation));
//
//  pdialog.intel_diplo_label =
//    I_L(XtVaCreateManagedWidget("inteldiplolabel",
//				labelWidgetClass,
//				pdialog.intel_diplo_form,
//				XtNlabel, buf,
//				null));
//   
//  my_snprintf(buf, sizeof(buf), "Ruler: %s %s", 
//	      get_ruler_title(pdialog.pplayer.government,
//			      pdialog.pplayer.is_male,
//			      pdialog.pplayer.nation),
//	      pdialog.pplayer.name);
//  XtVaCreateManagedWidget("inteldiplonamelabel", 
//			  labelWidgetClass, 
//			  pdialog.intel_diplo_form, 
//			  XtNlabel, buf,
//			  null);   
//
//  pdialog.intel_diplo_list =
//    XtVaCreateManagedWidget("inteldiplolist",
//			    listWidgetClass,
//			    pdialog.intel_diplo_form,
//			    null);
//
//  pdialog.intel_diplo_close_command =
//    I_L(XtVaCreateManagedWidget("inteldiploclosecommand",
//				commandWidgetClass,
//				pdialog.intel_diplo_form,
//				null));
//
//  XtAddCallback(pdialog.intel_diplo_close_command, XtNcallback,
//		intel_diplo_close_callback, (XtPointer)pdialog);
//
//  update_intel_diplo_dialog(pdialog);
//
//  XtRealizeWidget(pdialog.intel_diplo_dialog_shell);
//  
//  XSetWMProtocols(display, XtWindow(pdialog.intel_diplo_dialog_shell), 
//		  &wm_delete_window, 1);
//  XtOverrideTranslations(pdialog.intel_diplo_dialog_shell,
//    XtParseTranslationTable("<Message>WM_PROTOCOLS: msg-close-intel-diplo()"));
//}
//
///****************************************************************
//  Update a player's relations dialog for the given player.
//*****************************************************************/
//void update_intel_diplo_dialog(intel_dialog pdialog)
//{
//  int i;
//  Dimension width;
//  static char *namelist_ptrs[MAX_NUM_PLAYERS];
//  static char namelist_text[MAX_NUM_PLAYERS][72];
//  final player_diplstate state;
//
//  if (pdialog.intel_diplo_dialog_shell) {
//    i = 0;
//    for(player other: game.players){
//      if (other == pdialog.pplayer) {
//	continue;
//      }
//      state = pplayer_get_diplstate(pdialog.pplayer, other);
//      my_snprintf(namelist_text[i], sizeof(namelist_text[i]),
//		  "%-32s %-16s %-16s",
//		  other.name,
//		  get_nation_name(other.nation),
//		  diplstate_text(state.type));
//      namelist_ptrs[i] = namelist_text[i];
//      i++;
//    }
//
//    XawListChange(pdialog.intel_diplo_list, namelist_ptrs, i, 0, true);
//
//    XtVaGetValues(pdialog.intel_diplo_list, XtNwidth, &width, null);
//    XtVaSetValues(pdialog.intel_diplo_label, XtNwidth, width, null); 
//  }
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void intel_diplo_close_callback(Widget w, XtPointer client_data,
//			  XtPointer call_data)
//{
//  popdown_intel_diplo_dialog((intel_dialog )client_data);
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void intel_diplo_dialog_msg_close(Widget w)
//{
//  for (dialog pdialog : dialog_list.data) {
//    if (pdialog.intel_diplo_dialog_shell == w) {
//      popdown_intel_diplo_dialog(pdialog);
//      return;
//    }
//  } }
//}
}