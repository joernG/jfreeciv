package client.gui_xaw;

public class Dialogs{

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
//#include <stdarg.h>
//#include <stdio.h>
//#include <stdlib.h>
//
//#include <X11/Intrinsic.h>
//#include <X11/StringDefs.h>
//#include <X11/Xaw/AsciiText.h>  
//#include <X11/Xaw/Command.h>
//#include <X11/Xaw/Form.h>
//#include <X11/Xaw/Label.h>
//#include <X11/Xaw/List.h>
//#include <X11/Xaw/MenuButton.h>
//#include <X11/Xaw/SimpleMenu.h>
//#include <X11/Xaw/SmeBSB.h>
//#include <X11/Xaw/Toggle.h>     
//
//#include "fcintl.h"
//#include "Game.game.h"
//#include "government.h"
//#include "log.h"
//#include "Map.map.h"
//#include "mem.h"
//#include "packets.h"
//#include "player.h"
//#include "rand.h"
//#include "support.h"
//
//#include "chatline.h"
//#include "cityrep.h"	/* for popdown_city_report_dialog */
//#include "civclient.h"
//#include "climisc.h"
//#include "clinet.h"
//#include "control.h" /* request_xxx and set_unit_focus */
//#include "graphics.h"
//#include "gui_main.h"
//#include "gui_stuff.h"
//#include "mapctrl.h"
//#include "mapctrl_common.h"
//#include "mapview.h"
//#include "messagewin.h"	/* for popdown_meswin_dialog */
//#include "options.h"
//#include "packhand.h"
//#include "plrdlg.h"	/* for popdown_players_dialog */
//#include "repodlgs.h"	/* for popdown_xxx_dialog */
//#include "tilespec.h"
//
//#include "dialogs.h"
//
///******************************************************************/
//void popdown_notify_dialog();
//static Widget notify_dialog_shell;
//
///******************************************************************/
//static Widget races_dialog_shell=null;
//static Widget races_form, races_toggles_form, races_label;
//static Widget *races_toggles=null;
//static int *races_toggles_to_nations=null;
//static int *nation_to_race_toggle = null;
//static Widget races_leader_form, races_leader;
//static Widget races_leader_pick_popupmenu, races_leader_pick_menubutton;
//static Widget races_sex_toggles[2], races_sex_form, races_sex_label;
//static Widget races_style_form, races_style_label;
//static Widget *races_style_toggles=null;
//static Widget races_action_form;
//static Widget races_ok_command, races_disconnect_command, races_quit_command;
//
///******************************************************************/
//static Widget spy_tech_shell;
//static Widget spy_advances_list, spy_advances_list_label;
//static Widget spy_steal_command;
//
//static int spy_tech_shell_is_modal;
//static int advance_type[Tech_H.A_LAST+1];
//static int steal_advance = 0;
//
///******************************************************************/
//static Widget spy_sabotage_shell;
//static Widget spy_improvements_list, spy_improvements_list_label;
//static Widget spy_sabotage_command;
//
//static int spy_sabotage_shell_is_modal;
//static int improvement_type[Improvement.B_LAST+1];
//static int sabotage_improvement = 0;
//
///******************************************************************/
//public static final int MAX_SELECT_UNITS = 100;
//static Widget unit_select_dialog_shell;
//static Widget unit_select_form;
//static Widget unit_select_commands[MAX_SELECT_UNITS];
//static Widget unit_select_labels[MAX_SELECT_UNITS];
//static Pixmap unit_select_pixmaps[MAX_SELECT_UNITS];
//static int unit_select_ids[MAX_SELECT_UNITS];
//static int unit_select_no;
//
//void about_button_callback(Widget w, XtPointer client_data, 
//			    XtPointer call_data);
//
//void help_button_callback(Widget w, XtPointer client_data, 
//			    XtPointer call_data);
//
//void create_rates_dialog();
//void create_about_dialog();
//void create_help_dialog(Widget *shell);
//
//
///******************************************************************/
//static void create_races_dialog();
//static void races_leader_set_values(int race, int lead);
//static int races_buttons_get_current();
//static int races_sex_buttons_get_current();
//static int races_style_buttons_get_current();
//static void races_sex_buttons_set_current(int i);
//
//static int races_indirect_compare(final void *first, final void *second);
//
//static void races_toggles_callback(Widget w, XtPointer client_data, 
//				   XtPointer call_data);
//static void races_leader_pick_callback(Widget w, XtPointer client_data,
//				       XtPointer call_data);
//static void races_ok_command_callback(Widget w, XtPointer client_data, 
//				      XtPointer call_data);
//static void races_disconnect_command_callback(Widget w, XtPointer client_data, 
//					      XtPointer call_data);
//static void races_quit_command_callback(Widget w, XtPointer client_data, 
//					XtPointer call_data);
//
///******************************************************************/
//void unit_select_callback(Widget w, XtPointer client_data, 
//			    XtPointer call_data);
//void unit_select_all_callback(Widget w, XtPointer client_data, 
//			    XtPointer call_data);
//
//
///******************************************************************/
//static int city_style_idx[64];     /* translation table basic style.city_style  */
//static int city_style_ridx[64];    /* translation table the other way            */
//                                   /* they in fact limit the num of styles to 64 */
//static int b_s_num; /* num basic city styles, i.e. those that you can start with */
//
//
///******************************************************************/
//
//int is_showing_pillage_dialog = false;
//int unit_to_use_to_pillage;
//
//int caravan_city_id;
//int caravan_unit_id;
//
//boolean diplomat_dialog_open = false;
//int diplomat_id;
//int diplomat_target_id;
//
//city pcity_caravan_dest;
//unit punit_caravan;
//
//static Widget caravan_dialog;
//
///****************************************************************
//...
//*****************************************************************/
//static void notify_command_callback(Widget w, XtPointer client_data, 
//				    XtPointer call_data)
//{
//  popdown_notify_dialog();
//}
//
///****************************************************************
// ...
//*****************************************************************/
//static void select_random_race()
//{
//  /* try to find a free nation */
//  while (1) {
//    int race_toggle_index = Rand.myrand(Game.game.playable_nation_count);
//
//    if (XtIsSensitive(races_toggles[race_toggle_index])) {
//      x_simulate_button_click(races_toggles[race_toggle_index]);
//      break;
//    }
//  }
//}
//
///**************************************************************************
//  Popup a generic dialog to display some generic information.
//**************************************************************************/
//void popup_notify_dialog(final String caption, final String headline,
//			 final String lines)
//{
//  Widget notify_form, notify_command;
//  Widget notify_headline, notify_label;
//  Dimension width, width2;
//  
//  notify_dialog_shell = XtCreatePopupShell(caption,
//					   transientShellWidgetClass,
//					   toplevel, null, 0);
//
//  notify_form = XtVaCreateManagedWidget("notifyform", 
//					 formWidgetClass, 
//					 notify_dialog_shell, null);
//
//  notify_headline=XtVaCreateManagedWidget("notifyheadline", 
//			  labelWidgetClass, notify_form, 
//			  XtNlabel, headline,
//			  null);
//
//  
//  notify_label=XtVaCreateManagedWidget("notifylabel", 
//			  labelWidgetClass, notify_form, 
//			  XtNlabel, lines,
//			  null);   
//
//  notify_command =
//    I_L(XtVaCreateManagedWidget("notifycommand", 
//				commandWidgetClass,
//				notify_form,
//				null));
//
//  XtVaGetValues(notify_label, XtNwidth, &width, null);
//  XtVaGetValues(notify_headline, XtNwidth, &width2, null);
//  if(width>width2)
//    XtVaSetValues(notify_headline, XtNwidth, width, null); 
//  
//  XtAddCallback(notify_command, XtNcallback, notify_command_callback, null);
//  
//  xaw_set_relative_position(toplevel, notify_dialog_shell, 25, 5);
//  XtPopup(notify_dialog_shell, XtGrabNone);
//  XtSetSensitive(toplevel, false);
//}
//
///****************************************************************
//  Closes the notification dialog.
//*****************************************************************/
//void popdown_notify_dialog()
//{
//  if (notify_dialog_shell) {
//    XtDestroyWidget(notify_dialog_shell);
//    XtSetSensitive(toplevel, true);
//    notify_dialog_shell = 0;
//  }
//}
//
///****************************************************************
//...
//*****************************************************************/
//
///* surely this should use genlists??  --dwp */
//Speclists<widget> {
//  Widget w;
//  tile tile;
//  widget_list next;
//};
//static widget_list notify_goto_widget_list = null;
//
//static void notify_goto_widget_remove(Widget w)
//{
//  widget_list cur, *tmp;
//  cur=notify_goto_widget_list;
//  if (!cur)
//    return;
//  if (cur && cur.w == w) {
//    cur = cur.next;
//    free(notify_goto_widget_list);
//    notify_goto_widget_list = cur;
//    return;
//  }
//  for (; cur.next && cur.next.w!= w; cur=cur.next);
//  if (cur.next) {
//    tmp = cur.next;
//    cur.next = cur.next.next;
//    free(tmp);
//  }
//}
//
//static tile notify_goto_find_widget(Widget w)
//{
//  widget_list cur;
//
//  for (cur = notify_goto_widget_list; cur && cur.w !=w; cur = cur.next) {
//    /* Nothing. */
//  }
//
//  if (cur) {
//    return cur.tile;
//  } else {
//    return null;
//  }
//}
//
//static void notify_goto_add_widget_tile(Widget w, tile ptile)
//{
//  widget_list newwidget;
//
//  newwidget = fc_malloc(sizeof(*newwidget));
//  newwidget.w = w;
//  newwidget.tile = ptile;
//  newwidget.next = notify_goto_widget_list;
//  notify_goto_widget_list = newwidget;
//}
//
//static void notify_goto_command_callback(Widget w, XtPointer client_data, 
//			     XtPointer call_data)
//{
//  tile ptile =  notify_goto_find_widget(w);
//
//  center_tile_mapcanvas(ptile);
//  notify_goto_widget_remove(w);
//
//  XtDestroyWidget(XtParent(XtParent(w)));
//  XtSetSensitive(toplevel, true);
//}
//
//static void notify_no_goto_command_callback(Widget w, XtPointer client_data, 
//			     XtPointer call_data)
//{
//  notify_goto_widget_remove(w);
//  XtDestroyWidget(XtParent(XtParent(w)));
//  XtSetSensitive(toplevel, true);
//}
//
//
///**************************************************************************
//  Popup a dialog to display information about an event that has a
//  specific location.  The user should be given the option to goto that
//  location.
//**************************************************************************/
//void popup_notify_goto_dialog(final String headline, final String lines,
//			      tile ptile)
//{
//  Widget notify_dialog_shell, notify_form, notify_command, notify_goto_command;
//  Widget notify_headline, notify_label;
//  Dimension width, width2, width_1, width_2;
//  
//  if (!ptile) {
//    popup_notify_dialog("Message:", headline, lines);
//    return;
//  }
//  notify_dialog_shell = XtCreatePopupShell("Message:",
//					   transientShellWidgetClass,
//					   toplevel, null, 0);
//
//  notify_form = XtVaCreateManagedWidget("notifyform", 
//					 formWidgetClass, 
//					 notify_dialog_shell, null);
//
//  notify_headline=XtVaCreateManagedWidget("notifyheadline", 
//			  labelWidgetClass, notify_form, 
//			  XtNlabel, headline,
//			  null);
//
//  
//  notify_label=XtVaCreateManagedWidget("notifylabel", 
//			  labelWidgetClass, notify_form, 
//			  XtNlabel, lines,
//			  null);   
//
//  notify_command =
//    I_L(XtVaCreateManagedWidget("notifycommand", 
//				commandWidgetClass,
//				notify_form,
//				null));
//
//  notify_goto_command =
//    I_L(XtVaCreateManagedWidget("notifygotocommand", 
//				commandWidgetClass,
//				notify_form,
//				null));
//  
//  XtVaGetValues(notify_label, XtNwidth, &width, null);
//  XtVaGetValues(notify_headline, XtNwidth, &width2, null);
//  XtVaGetValues(notify_command, XtNwidth, &width_1, null);
//  XtVaGetValues(notify_goto_command, XtNwidth, &width_2, null);
//  if (width_1 + width_2 > width) width = width_1 + width_2;
//  if(width>width2)
//    XtVaSetValues(notify_headline, XtNwidth, width, null); 
//  
//  XtAddCallback(notify_command, XtNcallback, notify_no_goto_command_callback, null);
//  XtAddCallback(notify_goto_command, XtNcallback, notify_goto_command_callback, null);
//  notify_goto_add_widget_tile(notify_goto_command, ptile);
//  xaw_set_relative_position(toplevel, notify_dialog_shell, 25, 5);
//  XtPopup(notify_dialog_shell, XtGrabNone);
//  /*  XtSetSensitive(toplevel, false); */
//}
//
//
//
///****************************************************************
//...
//*****************************************************************/
//static void diplomat_bribe_yes_callback(Widget w, XtPointer client_data, 
//					XtPointer call_data)
//{
//  request_diplomat_action(DIPLOMAT_BRIBE, diplomat_id,
//			  diplomat_target_id, 0);
//
//  destroy_message_dialog(w);
//}
//
///****************************************************************
//...
//*****************************************************************/
//static void diplomat_bribe_no_callback(Widget w, XtPointer client_data, 
//				       XtPointer call_data)
//{
//  destroy_message_dialog(w);
//}
//
///****************************************************************
//...  Ask the server how much the bribe is
//*****************************************************************/
//static void diplomat_bribe_callback(Widget w, XtPointer client_data, 
//				    XtPointer call_data)
//{
//  destroy_message_dialog(w);
//
//  if (Game.find_unit_by_id(diplomat_id) && Game.find_unit_by_id(diplomat_target_id)) {
//    dsend_packet_unit_bribe_inq(&aconnection, diplomat_target_id);
//  }
//}
//
///****************************************************************
//...
//*****************************************************************/
//void popup_bribe_dialog(unit punit)
//{
//  char buf[128];
//
//  if (unit_flag(punit, F_UNBRIBABLE)) {
//    popup_message_dialog(toplevel, "diplomatbribedialog",
//                         "This unit cannot be bribed!",
//                         diplomat_bribe_no_callback, 0, 0, null);
//  } else if(Game.game.player_ptr.economic.gold>=punit.bribe_cost) {
//    buf = util.my_snprintf(
//		("Bribe unit for %d gold?\n" +
//		  "Treasury contains %d gold."), 
//		punit.bribe_cost, Game.game.player_ptr.economic.gold);
//    popup_message_dialog(toplevel, "diplomatbribedialog", buf,
//			 diplomat_bribe_yes_callback, 0, 0,
//			 diplomat_bribe_no_callback, 0, 0,
//			 null);
//  } else {
//    buf = util.my_snprintf(
//		("Bribing the unit costs %d gold.\n" +
//		  "Treasury contains %d gold."), 
//		punit.bribe_cost, Game.game.player_ptr.economic.gold);
//    popup_message_dialog(toplevel, "diplomatnogolddialog", buf,
//			 diplomat_bribe_no_callback, 0, 0,
//			 null);
//  }
//}
//
///****************************************************************
//...
//*****************************************************************/
//static void diplomat_sabotage_callback(Widget w, XtPointer client_data, 
//				       XtPointer call_data)
//{
//  destroy_message_dialog(w);
//  diplomat_dialog_open = false;
//
//  if(Game.find_unit_by_id(diplomat_id) && 
//     Game.find_city_by_id(diplomat_target_id)) { 
//    request_diplomat_action(DIPLOMAT_SABOTAGE, diplomat_id,
//			    diplomat_target_id, -1);
//  }
//
//  process_diplomat_arrival(null, 0);
//}
//
///****************************************************************
//...
//*****************************************************************/
//static void diplomat_embassy_callback(Widget w, XtPointer client_data, 
//				      XtPointer call_data)
//{
//  destroy_message_dialog(w);
//  diplomat_dialog_open = false;
//
//  if(Game.find_unit_by_id(diplomat_id) && 
//     (Game.find_city_by_id(diplomat_target_id))) { 
//    request_diplomat_action(DIPLOMAT_EMBASSY, diplomat_id,
//			    diplomat_target_id, 0);
//  }
//
//  process_diplomat_arrival(null, 0);
//}
//
///****************************************************************
//...
//*****************************************************************/
//static void diplomat_investigate_callback(Widget w, XtPointer client_data, 
//					  XtPointer call_data)
//{
//  destroy_message_dialog(w);
//  diplomat_dialog_open = false;
//
//  if(Game.find_unit_by_id(diplomat_id) && 
//     (Game.find_city_by_id(diplomat_target_id))) { 
//    request_diplomat_action(DIPLOMAT_INVESTIGATE, diplomat_id,
//			    diplomat_target_id, 0);
//  }
//
//  process_diplomat_arrival(null, 0);
//}
//
///****************************************************************
//...
//*****************************************************************/
//static void spy_sabotage_unit_callback(Widget w, XtPointer client_data, 
//				       XtPointer call_data)
//{
//  request_diplomat_action(SPY_SABOTAGE_UNIT, diplomat_id,
//			  diplomat_target_id, 0);
//
//  destroy_message_dialog(w);
//}
//
///****************************************************************
//...
//*****************************************************************/
//static void spy_poison_callback(Widget w, XtPointer client_data, 
//				XtPointer call_data)
//{
//  destroy_message_dialog(w);
//  diplomat_dialog_open = false;
//
//  if(Game.find_unit_by_id(diplomat_id) && 
//     (Game.find_city_by_id(diplomat_target_id))) { 
//    request_diplomat_action(SPY_POISON, diplomat_id, diplomat_target_id, 0);
//  }
//
//  process_diplomat_arrival(null, 0);
//}
//
///****************************************************************
//...
//*****************************************************************/
//static void diplomat_steal_callback(Widget w, XtPointer client_data, 
//				    XtPointer call_data)
//{
//  destroy_message_dialog(w);
//  diplomat_dialog_open = false;
//
//  if(Game.find_unit_by_id(diplomat_id) && 
//     Game.find_city_by_id(diplomat_target_id)) { 
//    request_diplomat_action(DIPLOMAT_STEAL, diplomat_id,
//			    diplomat_target_id, 0);
//  }
//
//  process_diplomat_arrival(null, 0);
//}
//
///****************************************************************
//...
//*****************************************************************/
//static void spy_close_tech_callback(Widget w, XtPointer client_data, 
//				    XtPointer call_data)
//{
//  if(spy_tech_shell_is_modal)
//    XtSetSensitive(main_form, true);
//  XtDestroyWidget(spy_tech_shell);
//  spy_tech_shell=0;
//
//  process_diplomat_arrival(null, 0);
//}
//
///****************************************************************
//...
//*****************************************************************/
//static void spy_close_sabotage_callback(Widget w, XtPointer client_data, 
//					XtPointer call_data)
//{
//  if(spy_sabotage_shell_is_modal)
//    XtSetSensitive(main_form, true);
//  XtDestroyWidget(spy_sabotage_shell);
//  spy_sabotage_shell=0;
//
//  process_diplomat_arrival(null, 0);
//}
//
///****************************************************************
//...
//*****************************************************************/
//static void spy_select_tech_callback(Widget w, XtPointer client_data, 
//				     XtPointer call_data)
//{
//  XawListReturnStruct *ret;
//  ret=XawListShowCurrent(spy_advances_list);
//  
//  if(ret.list_index!=XAW_LIST_NONE && advance_type[ret.list_index] != -1){
//    steal_advance = advance_type[ret.list_index];
//    XtSetSensitive(spy_steal_command, true);
//    return;
//  }
//  XtSetSensitive(spy_steal_command, false);
//}
//
///****************************************************************
//...
//*****************************************************************/
//static void spy_select_improvement_callback(Widget w, XtPointer client_data, 
//					    XtPointer call_data)
//{
//  XawListReturnStruct *ret;
//  ret=XawListShowCurrent(spy_improvements_list);
//  
//  if(ret.list_index!=XAW_LIST_NONE){
//    sabotage_improvement = improvement_type[ret.list_index];
//    XtSetSensitive(spy_sabotage_command, true);
//    return;
//  }
//  XtSetSensitive(spy_sabotage_command, false);
//}
//
///****************************************************************
//...
//*****************************************************************/
//static void spy_steal_callback(Widget w, XtPointer client_data, 
//			       XtPointer call_data)
//{  
//  XtDestroyWidget(spy_tech_shell);
//  spy_tech_shell = 0l;
//  
//  if(!steal_advance){
//    util.freelog(Log.LOG_ERROR, "Bug in spy steal tech code");
//    process_diplomat_arrival(null, 0);
//    return;
//  }
//  
//  if(Game.find_unit_by_id(diplomat_id) && 
//     Game.find_city_by_id(diplomat_target_id)) { 
//    request_diplomat_action(DIPLOMAT_STEAL, diplomat_id,
//			    diplomat_target_id, steal_advance);
//  }
//
//  process_diplomat_arrival(null, 0);
//}
//
///****************************************************************
//...
//*****************************************************************/
//static void spy_sabotage_callback(Widget w, XtPointer client_data, 
//				  XtPointer call_data)
//{  
//  XtDestroyWidget(spy_sabotage_shell);
//  spy_sabotage_shell = 0l;
//  
//  if(!sabotage_improvement){
//    util.freelog(Log.LOG_ERROR, "Bug in spy sabotage code");
//    process_diplomat_arrival(null, 0);
//    return;
//  }
//  
//  if(Game.find_unit_by_id(diplomat_id) && 
//     Game.find_city_by_id(diplomat_target_id)) { 
//    request_diplomat_action(DIPLOMAT_SABOTAGE, diplomat_id,
//			    diplomat_target_id, sabotage_improvement + 1);
//  }
//
//  process_diplomat_arrival(null, 0);
//}
//
///****************************************************************
//...
//*****************************************************************/
//static int create_advances_list(player pplayer,
//				player pvictim, boolean make_modal)
//{  
//  Widget spy_tech_form;
//  Widget close_command;
//  Dimension width1, width2; 
//  int i, j;
//
//  static final String advances_can_steal[Tech_H.A_LAST+1]; 
//
//  spy_tech_shell =
//    I_T(XtVaCreatePopupShell("spystealtechpopup", 
//			     (make_modal ? transientShellWidgetClass :
//			      topLevelShellWidgetClass),
//			     toplevel, null));  
//  
//  spy_tech_form = XtVaCreateManagedWidget("spystealtechform", 
//					     formWidgetClass,
//					     spy_tech_shell,
//					     null);   
//
//  spy_advances_list_label =
//    I_L(XtVaCreateManagedWidget("spystealtechlistlabel", labelWidgetClass, 
//				spy_tech_form, null));
//
//  spy_advances_list = XtVaCreateManagedWidget("spystealtechlist", 
//					      listWidgetClass,
//					      spy_tech_form,
//					      null);
//
//  close_command =
//    I_L(XtVaCreateManagedWidget("spystealtechclosecommand", commandWidgetClass,
//				spy_tech_form, null));
//  
//  spy_steal_command =
//    I_L(XtVaCreateManagedWidget("spystealtechcommand", commandWidgetClass,
//				spy_tech_form,
//				XtNsensitive, false,
//				null));
//  
//
//  XtAddCallback(spy_advances_list, XtNcallback, spy_select_tech_callback, null);
//  XtAddCallback(close_command, XtNcallback, spy_close_tech_callback, null);
//  XtAddCallback(spy_steal_command, XtNcallback, spy_steal_callback, null);
//  XtRealizeWidget(spy_tech_shell);
//
//  /* Now populate the list */
//  
//  j = 0;
//  advances_can_steal[j] = "NONE";
//  advance_type[j] = -1;
//
//  if (pvictim) { /* you don't want to know what lag can do -- Syela */
//    for(i=A_FIRST; i<Game.game.num_tech_types; i++) {
//      if(get_invention(pvictim, i)==TECH_KNOWN && 
//         (get_invention(pplayer, i)==TECH_UNKNOWN || 
//          get_invention(pplayer, i)==TECH_REACHABLE)) {
//      
//        advances_can_steal[j] = advances[i].name;
//        advance_type[j++] = i;
//      }
//    }
//    if(j > 0) {
//      advances_can_steal[j] = "At Spy's Discretion";
//      advance_type[j++] = Game.game.num_tech_types;
//    }
//  }
//
//  if(j == 0) j++;
//  advances_can_steal[j] = null; 
//  
//  XtSetSensitive(spy_steal_command, false);
//  
//  XawListChange(spy_advances_list, (char **)advances_can_steal, 0, 0, 1);
//  XtVaGetValues(spy_advances_list, XtNwidth, &width1, null);
//  XtVaGetValues(spy_advances_list_label, XtNwidth, &width2, null);
//  XtVaSetValues(spy_advances_list, XtNwidth, MAX(width1,width2), null); 
//  XtVaSetValues(spy_advances_list_label, XtNwidth, MAX(width1,width2), null); 
//
//  return j;
//}
//
///****************************************************************
//...
//*****************************************************************/
//static int create_improvements_list(player pplayer,
//				    city pcity, boolean make_modal)
//{  
//  Widget spy_sabotage_form;
//  Widget close_command;
//  Dimension width1, width2; 
//  int j;
//
//  static final String improvements_can_sabotage[Improvement.B_LAST+1]; 
//  
//  spy_sabotage_shell =
//    I_T(XtVaCreatePopupShell("spysabotageimprovementspopup", 
//			     (make_modal ? transientShellWidgetClass :
//			      topLevelShellWidgetClass),
//			     toplevel, null));  
//  
//  spy_sabotage_form = XtVaCreateManagedWidget("spysabotageimprovementsform", 
//					     formWidgetClass,
//					     spy_sabotage_shell,
//					     null);   
//
//  spy_improvements_list_label =
//    I_L(XtVaCreateManagedWidget("spysabotageimprovementslistlabel", 
//				labelWidgetClass, 
//				spy_sabotage_form,
//				null));
//
//  spy_improvements_list = XtVaCreateManagedWidget("spysabotageimprovementslist", 
//					      listWidgetClass,
//					      spy_sabotage_form,
//					      null);
//
//  close_command =
//    I_L(XtVaCreateManagedWidget("spysabotageimprovementsclosecommand", 
//				commandWidgetClass,
//				spy_sabotage_form,
//				null));
//  
//  spy_sabotage_command =
//    I_L(XtVaCreateManagedWidget("spysabotageimprovementscommand", 
//				commandWidgetClass,
//				spy_sabotage_form,
//				XtNsensitive, false,
//				null));
//  
//
//  XtAddCallback(spy_improvements_list, XtNcallback, spy_select_improvement_callback, null);
//  XtAddCallback(close_command, XtNcallback, spy_close_sabotage_callback, null);
//  XtAddCallback(spy_sabotage_command, XtNcallback, spy_sabotage_callback, null);
//  XtRealizeWidget(spy_sabotage_shell);
//
//  /* Now populate the list */
//  
//  j = 0;
//  improvements_can_sabotage[j] = "City Production";
//  improvement_type[j++] = -1;
//
//	for (int i = 0; i < game.num_impr_types; i++) {
//	if((pcity).improvements[i] == Improvement.I_NONE) {
//		continue;
//	}
//    if (get_improvement_type(i).sabotage > 0) {
//      improvements_can_sabotage[j] = City.get_impr_name_ex(pcity, i);
//      improvement_type[j++] = i;
//    }  
//  } ;
//
//  if(j > 1) {
//    improvements_can_sabotage[j] = "At Spy's Discretion";
//    improvement_type[j++] = Improvement.B_LAST;
//  } else {
//    improvement_type[0] = Improvement.B_LAST; /* fake "discretion", since must be production */
//  }
//
//  improvements_can_sabotage[j] = null;
//  
//  XtSetSensitive(spy_sabotage_command, false);
//  
//  XawListChange(spy_improvements_list, (String *) improvements_can_sabotage,
//		0, 0, 1);
//  XtVaGetValues(spy_improvements_list, XtNwidth, &width1, null);
//  XtVaGetValues(spy_improvements_list_label, XtNwidth, &width2, null);
//  XtVaSetValues(spy_improvements_list, XtNwidth, MAX(width1,width2), null); 
//  XtVaSetValues(spy_improvements_list_label, XtNwidth, MAX(width1,width2), null); 
//
//  return j;
//}
//
///****************************************************************
//...
//*****************************************************************/
//static void spy_steal_popup(Widget w, XtPointer client_data, 
//			    XtPointer call_data)
//{
//  city pvcity = Game.find_city_by_id(diplomat_target_id);
//  player pvictim = null;
//
//  if(pvcity)
//    pvictim = City.city_owner(pvcity);
//
///* it is concievable that pvcity will not be found, because something
//has happened to the city during latency.  Therefore we must initialize
//pvictim to null and account for !pvictim in create_advances_list. -- Syela */
//  
//  destroy_message_dialog(w);
//  diplomat_dialog_open = false;
//
//  if(!spy_tech_shell){
//    Position x, y;
//    Dimension width, height;
//    spy_tech_shell_is_modal=1;
//
//    create_advances_list(Game.game.player_ptr, pvictim, spy_tech_shell_is_modal);
//    
//    XtVaGetValues(toplevel, XtNwidth, &width, XtNheight, &height, null);
//    
//    XtTranslateCoords(toplevel, (Position) width/10, (Position) height/10,
//		      &x, &y);
//    XtVaSetValues(spy_tech_shell, XtNx, x, XtNy, y, null);
//    
//    XtPopup(spy_tech_shell, XtGrabNone);
//  }
//}
//
///****************************************************************
// Requests up-to-date list of improvements, the return of
// which will trigger the popup_sabotage_dialog() function.
//*****************************************************************/
//static void spy_request_sabotage_list(Widget w, XtPointer client_data,
//				      XtPointer call_data)
//{
//  destroy_message_dialog(w);
//  diplomat_dialog_open = false;
//
//  if(Game.find_unit_by_id(diplomat_id) &&
//     (Game.find_city_by_id(diplomat_target_id))) {
//    request_diplomat_action(SPY_GET_SABOTAGE_LIST, diplomat_id,
//			    diplomat_target_id, 0);
//  }
//}
//
///****************************************************************
// Pops-up the Spy sabotage dialog, upon return of list of
// available improvements requested by the above function.
//*****************************************************************/
//void popup_sabotage_dialog(city pcity)
//{  
//  if(!spy_sabotage_shell){
//    Position x, y;
//    Dimension width, height;
//    spy_sabotage_shell_is_modal=1;
//
//    create_improvements_list(Game.game.player_ptr, pcity, spy_sabotage_shell_is_modal);
//    
//    XtVaGetValues(toplevel, XtNwidth, &width, XtNheight, &height, null);
//    
//    XtTranslateCoords(toplevel, (Position) width/10, (Position) height/10,
//		      &x, &y);
//    XtVaSetValues(spy_sabotage_shell, XtNx, x, XtNy, y, null);
//    
//    XtPopup(spy_sabotage_shell, XtGrabNone);
//  }
//}
//
///****************************************************************
//...
//*****************************************************************/
//static void diplomat_incite_yes_callback(Widget w, XtPointer client_data, 
//					 XtPointer call_data)
//{
//  request_diplomat_action(DIPLOMAT_INCITE, diplomat_id,
//			  diplomat_target_id, 0);
//
//  destroy_message_dialog(w);
//
//  process_diplomat_arrival(null, 0);
//}
//
///****************************************************************
//...
//*****************************************************************/
//static void diplomat_incite_no_callback(Widget w, XtPointer client_data, 
//					XtPointer call_data)
//{
//  destroy_message_dialog(w);
//
//  process_diplomat_arrival(null, 0);
//}
//
//
///****************************************************************
//...  Ask the server how much the revolt is going to cost us
//*****************************************************************/
//static void diplomat_incite_callback(Widget w, XtPointer client_data, 
//				     XtPointer call_data)
//{
//  destroy_message_dialog(w);
//  diplomat_dialog_open = false;
//
//  if (Game.find_unit_by_id(diplomat_id) && Game.find_city_by_id(diplomat_target_id)) {
//    dsend_packet_city_incite_inq(&aconnection, diplomat_target_id);
//  }
//}
//
///****************************************************************
//...  Popup the yes/no dialog for inciting, since we know the cost now
//*****************************************************************/
//void popup_incite_dialog(city pcity)
//{
//  char buf[128];
//
//  if (pcity.incite_revolt_cost == INCITE_IMPOSSIBLE_COST) {
//    buf = util.my_snprintf( "You can't incite a revolt in %s.",
//		pcity.name);
//    popup_message_dialog(toplevel, "diplomatnogolddialog", buf,
//			 diplomat_incite_no_callback, 0, 0, null);
//  } else if (Game.game.player_ptr.economic.gold >= pcity.incite_revolt_cost) {
//    buf = util.my_snprintf(
//		("Incite a revolt for %d gold?\n" +
//		  "Treasury contains %d gold."), 
//		pcity.incite_revolt_cost, Game.game.player_ptr.economic.gold);
//   diplomat_target_id = pcity.id;
//   popup_message_dialog(toplevel, "diplomatrevoltdialog", buf,
//			diplomat_incite_yes_callback, 0, 0,
//			diplomat_incite_no_callback, 0, 0,
//			null);
//  } else {
//   buf = util.my_snprintf(
//	       ("Inciting a revolt costs %d gold.\n" +
//		 "Treasury contains %d gold."), 
//	       pcity.incite_revolt_cost, Game.game.player_ptr.economic.gold);
//   popup_message_dialog(toplevel, "diplomatnogolddialog", buf,
//			diplomat_incite_no_callback, 0, 0,
//			null);
//  }
//}
//
//
///****************************************************************
//  Callback from diplomat/spy dialog for "keep moving".
//  (This should only occur when entering allied city.)
//*****************************************************************/
//static void diplomat_keep_moving_callback(Widget w, XtPointer client_data, 
//					  XtPointer call_data)
//{
//  unit punit;
//  city pcity;
//  
//  destroy_message_dialog(w);
//  diplomat_dialog_open = false;
//
//  if( (punit=Game.find_unit_by_id(diplomat_id))
//      && (pcity=Game.find_city_by_id(diplomat_target_id))
//      && !Map.same_pos(punit.tile, pcity.tile)) {
//    request_diplomat_action(DIPLOMAT_MOVE, diplomat_id,
//			    diplomat_target_id, 0);
//  }
//  process_diplomat_arrival(null, 0);
//}
//
///****************************************************************
//...
//*****************************************************************/
//static void diplomat_cancel_callback(Widget w, XtPointer a, XtPointer b)
//{
//  destroy_message_dialog(w);
//  diplomat_dialog_open = false;
//
//  process_diplomat_arrival(null, 0);
//}
//
//
///****************************************************************
//...
//*****************************************************************/
//void popup_diplomat_dialog(unit punit, tile dest_tile)
//{
//  city pcity;
//  unit ptunit;
//  Widget shl;
//  char buf[128];
//
//  diplomat_id=punit.id;
//
//  if((pcity=Map.map_get_city(dest_tile))){
//    /* Spy/Diplomat acting against a city */
//
//    diplomat_target_id=pcity.id;
//    buf = util.my_snprintf(
//		"Your %s has arrived at %s.\nWhat is your command?",
//		Unittype_P.unit_name(punit.type), pcity.name);
//
//    if(!unit_flag(punit, F_SPY)){
//      shl=popup_message_dialog(toplevel, "diplomatdialog", buf,
//			       diplomat_embassy_callback, 0, 1,
//			       diplomat_investigate_callback, 0, 1,
//			       diplomat_sabotage_callback, 0, 1,
//			       diplomat_steal_callback, 0, 1,
//			       diplomat_incite_callback, 0, 1,
//			       diplomat_keep_moving_callback, 0, 1,
//			       diplomat_cancel_callback, 0, 0,
//			       null);
//      
//      if(!diplomat_can_do_action(punit, DIPLOMAT_EMBASSY, dest_tile))
//	XtSetSensitive(XtNameToWidget(shl, "*button0"), false);
//      if(!diplomat_can_do_action(punit, DIPLOMAT_INVESTIGATE, dest_tile))
//	XtSetSensitive(XtNameToWidget(shl, "*button1"), false);
//      if(!diplomat_can_do_action(punit, DIPLOMAT_SABOTAGE, dest_tile))
//	XtSetSensitive(XtNameToWidget(shl, "*button2"), false);
//      if(!diplomat_can_do_action(punit, DIPLOMAT_STEAL, dest_tile))
//	XtSetSensitive(XtNameToWidget(shl, "*button3"), false);
//      if(!diplomat_can_do_action(punit, DIPLOMAT_INCITE, dest_tile))
//	XtSetSensitive(XtNameToWidget(shl, "*button4"), false);
//      if(!diplomat_can_do_action(punit, DIPLOMAT_MOVE, dest_tile))
//	XtSetSensitive(XtNameToWidget(shl, "*button5"), false);
//    }else{
//      shl=popup_message_dialog(toplevel, "spydialog", buf,
//			       diplomat_embassy_callback, 0,  1,
//			       diplomat_investigate_callback, 0, 1,
//			       spy_poison_callback,0, 1,
//			       spy_request_sabotage_list, 0, 1,
//			       spy_steal_popup, 0, 1,
//			       diplomat_incite_callback, 0, 1,
//			       diplomat_keep_moving_callback, 0, 1,
//			       diplomat_cancel_callback, 0, 0,
//			       null);
//      
//      if(!diplomat_can_do_action(punit, DIPLOMAT_EMBASSY, dest_tile))
//	XtSetSensitive(XtNameToWidget(shl, "*button0"), false);
//      if(!diplomat_can_do_action(punit, DIPLOMAT_INVESTIGATE, dest_tile))
//	XtSetSensitive(XtNameToWidget(shl, "*button1"), false);
//      if(!diplomat_can_do_action(punit, SPY_POISON, dest_tile))
//	XtSetSensitive(XtNameToWidget(shl, "*button2"), false);
//      if(!diplomat_can_do_action(punit, DIPLOMAT_SABOTAGE, dest_tile))
//	XtSetSensitive(XtNameToWidget(shl, "*button3"), false);
//      if(!diplomat_can_do_action(punit, DIPLOMAT_STEAL, dest_tile))
//	XtSetSensitive(XtNameToWidget(shl, "*button4"), false);
//      if(!diplomat_can_do_action(punit, DIPLOMAT_INCITE, dest_tile))
//	XtSetSensitive(XtNameToWidget(shl, "*button5"), false);
//      if(!diplomat_can_do_action(punit, DIPLOMAT_MOVE, dest_tile))
//	XtSetSensitive(XtNameToWidget(shl, "*button6"), false);
//    }
//
//    diplomat_dialog_open = true;
//  }else{ 
//    if((ptunit=unit_list_get(&dest_tile.units, 0))){
//      /* Spy/Diplomat acting against a unit */
//      
//      final String message = !unit_flag(punit, F_SPY)
//	? "Sir, the diplomat is waiting for your command"
//	: "Sir, the spy is waiting for your command";
//      
//      diplomat_target_id=ptunit.id;
//
//      shl=popup_message_dialog(toplevel, "spybribedialog",
//			       message,
//			       diplomat_bribe_callback, 0, 0,
//			       spy_sabotage_unit_callback, 0, 0,
//			       diplomat_cancel_callback, 0, 0,
//			       null);
//	
//      if(!diplomat_can_do_action(punit, DIPLOMAT_BRIBE, dest_tile))
//	XtSetSensitive(XtNameToWidget(shl, "*button0"), false);
//      if(!diplomat_can_do_action(punit, SPY_SABOTAGE_UNIT, dest_tile))
//	XtSetSensitive(XtNameToWidget(shl, "*button1"), false);
//    }
//  }
//}
//
///****************************************************************
//...
//*****************************************************************/
//boolean diplomat_dialog_is_open()
//{
//  return diplomat_dialog_open;
//}
//
//
///****************************************************************
//...
//*****************************************************************/
//static void caravan_establish_trade_callback(Widget w, XtPointer client_data,
//					     XtPointer call_data)
//{
//  dsend_packet_unit_establish_trade(&aconnection, caravan_unit_id);
//  destroy_message_dialog(w);
//  caravan_dialog = 0;
//  process_caravan_arrival(null);
//}
//
//
///****************************************************************
//...
//*****************************************************************/
//static void caravan_help_build_wonder_callback(Widget w,
//					       XtPointer client_data,
//					       XtPointer call_data)
//{
//  dsend_packet_unit_help_build_wonder(&aconnection, caravan_unit_id);
//
//  destroy_message_dialog(w);
//  caravan_dialog = 0;
//  process_caravan_arrival(null);
//}
//
//
///****************************************************************
//...
//*****************************************************************/
//static void caravan_keep_moving_callback(Widget w, XtPointer client_data, 
//					 XtPointer call_data)
//{
//  destroy_message_dialog(w);
//  caravan_dialog = 0;
//  process_caravan_arrival(null);
//}
//
//
///****************************************************************
//...
//*****************************************************************/
//void popup_caravan_dialog(unit punit,
//			  city phomecity, city pdestcity)
//{
//  char buf[128];
//  
//  buf = util.my_snprintf(
//	      "Your caravan from %s reaches the city of %s.\nWhat now?",
//	      phomecity.name, pdestcity.name);
//  
//  caravan_city_id=pdestcity.id; /* callbacks need these */
//  caravan_unit_id=punit.id;
//  
//  caravan_dialog=popup_message_dialog(toplevel, "caravandialog", 
//			   buf,
//			   caravan_establish_trade_callback, 0, 0,
//			   caravan_help_build_wonder_callback, 0, 0,
//			   caravan_keep_moving_callback, 0, 0,
//			   null);
//  
//  if (!City.can_cities_trade(phomecity, pdestcity))
//    XtSetSensitive(XtNameToWidget(caravan_dialog, "*button0"), false);
//  
//  if(!unit_can_help_build_wonder(punit, pdestcity))
//    XtSetSensitive(XtNameToWidget(caravan_dialog, "*button1"), false);
//}
//
///****************************************************************
//...
//*****************************************************************/
//boolean caravan_dialog_is_open()
//{
//  return BOOL_VAL(caravan_dialog);
//}
//
///****************************************************************
//...
//*****************************************************************/
//static void revolution_callback_yes(Widget w, XtPointer client_data, 
//				    XtPointer call_data)
//{
//  int government = XTPOINTER_TO_INT(client_data);
//
//  if (government == -1) {
//    start_revolution();
//  } else {
//    /* Player have choosed government */
//    set_government_choice(government);
//  }
//  destroy_message_dialog(w);
//}
//
///****************************************************************
//...
//*****************************************************************/
//static void revolution_callback_no(Widget w, XtPointer client_data, 
//				   XtPointer call_data)
//{
//  destroy_message_dialog(w);
//}
//
//
//
///****************************************************************
//...
//*****************************************************************/
//void popup_revolution_dialog(int government)
//{
//  popup_message_dialog(toplevel, "revolutiondialog", 
//		       "You say you wanna revolution?",
//		       revolution_callback_yes,
//		       INT_TO_XTPOINTER(government), 0,
//		       revolution_callback_no, 0, 0,
//		       null);
//}
//
//
///****************************************************************
//...
//*****************************************************************/
//static void pillage_callback(Widget w, XtPointer client_data, 
//			     XtPointer call_data)
//{
//  if (!is_showing_pillage_dialog) {
//    destroy_message_dialog (w);
//    return;
//  }
//
//  if (client_data) {
//    unit punit = Game.find_unit_by_id (unit_to_use_to_pillage);
//    if (punit) {
//      request_new_unit_activity_targeted(punit, ACTIVITY_PILLAGE,
//					 XTPOINTER_TO_INT(client_data));
//    }
//  }
//
//  destroy_message_dialog (w);
//  is_showing_pillage_dialog = false;
//}
//
///****************************************************************
//...
//*****************************************************************/
//void popup_pillage_dialog(unit punit,
//			  enum int may_pillage)
//{
//  Widget shell, form, dlabel, button, prev;
//
//  if (is_showing_pillage_dialog) {
//    return;
//  }
//  is_showing_pillage_dialog = true;
//  unit_to_use_to_pillage = punit.id;
//
//  XtSetSensitive (toplevel, false);
//
//  shell = I_T(XtCreatePopupShell("pillagedialog", transientShellWidgetClass,
//				 toplevel, null, 0));
//  form = XtVaCreateManagedWidget ("form", formWidgetClass, shell, null);
//  dlabel = I_L(XtVaCreateManagedWidget("dlabel", labelWidgetClass, form, null));
//
//  prev = dlabel;
//  while (may_pillage) {
//    enum int what = get_preferred_pillage(may_pillage);
//
//    button =
//      XtVaCreateManagedWidget ("button", commandWidgetClass, form,
//			       XtNfromVert, prev,
//			       XtNlabel,
//			         (XtArgVal)(map_get_infrastructure_text (what)),
//			       null);
//    XtAddCallback(button, XtNcallback, pillage_callback,
//		  INT_TO_XTPOINTER(what));
//    prev = button;
//    may_pillage &= (~(what | map_get_infrastructure_prerequisite (what)));
//  }
//  button =
//    I_L(XtVaCreateManagedWidget("closebutton", commandWidgetClass, form,
//				XtNfromVert, prev,
//				null));
//  XtAddCallback (button, XtNcallback, pillage_callback, null);
//
//  xaw_set_relative_position (toplevel, shell, 10, 0);
//  XtPopup (shell, XtGrabNone);
//}
//
///****************************************************************
//  Parameters after named parameters should be in triplets:
//  - callback, callback_data, fixed_width 
//*****************************************************************/
//Widget popup_message_dialog(Widget parent, final String dialogname,
//			    final String text)
//{
//  va_list args;
//  Widget dshell, dform, button;
//  Position x, y;
//  Dimension width, height;
//  void (*fcb)(Widget, XtPointer, XtPointer);
//  XtPointer client_data;
//  char button_name[512];
//  int i, fixed_width;
//
//  XtSetSensitive(parent, false);
//  
//  I_T(dshell=XtCreatePopupShell(dialogname, transientShellWidgetClass,
//				parent, null, 0));
//  
//  dform=XtVaCreateManagedWidget("dform", formWidgetClass, dshell, null);
//
//  /* caller should i18n text as desired */
//  XtVaCreateManagedWidget("dlabel", labelWidgetClass, dform, 
//			  XtNlabel, (XtArgVal)text,
//			  null);   
//
//  i=0;
//  va_start(args, text);
//
//  while((fcb=((void(*)(Widget, XtPointer, XtPointer))(va_arg(args, void *))))) {
//    client_data=va_arg(args, XtPointer);
//    fixed_width=va_arg(args, int);
//    button_name = util.my_snprintf( "button%d", i++);
//    
//    button=XtVaCreateManagedWidget(button_name, commandWidgetClass, 
//				   dform, null);
//    if (fixed_width) {
//      I_LW(button);
//    } else {
//      I_L(button);
//    }
//    XtAddCallback(button, XtNcallback, fcb, client_data);
//  }
//  
//  va_end(args);
//
//  XtVaGetValues(parent, XtNwidth, &width, XtNheight, &height, null);
//  XtTranslateCoords(parent, (Position) width/10, (Position) height/10,
//		    &x, &y);
//  XtVaSetValues(dshell, XtNx, x, XtNy, y, null);
//  
//  XtPopup(dshell, XtGrabNone);
//
//  return dshell;
//}
//
///****************************************************************
//...
//*****************************************************************/
//void destroy_message_dialog(Widget button)
//{
//  XtSetSensitive(XtParent(XtParent(XtParent(button))), true);
//
//  XtDestroyWidget(XtParent(XtParent(button)));
//}
//
//static int number_of_columns(int n)
//{
//#if 0
//  /* This would require libm, which isn't worth it for this one little
//   * function.  Since MAX_SELECT_UNITS is 100 already, the ifs
//   * work fine.  */
//  double sqrt(); double ceil();
//  return ceil(sqrt((double)n/5.0));
//#else
//  assert(MAX_SELECT_UNITS == 100);
//  if(n<=5) return 1;
//  else if(n<=20) return 2;
//  else if(n<=45) return 3;
//  else if(n<=80) return 4;
//  else return 5;
//#endif
//}
//static int number_of_rows(int n)
//{
//  int c=number_of_columns(n);
//  return (n+c-1)/c;
//}
//
///****************************************************************
//popup the dialog 10% inside the main-window 
//*****************************************************************/
//void popup_unit_select_dialog(tile ptile)
//{
//  int i,n,r;
//  char buffer[512];
//  Arg args[4];
//  int nargs;
//  Widget unit_select_all_command, unit_select_close_command;
//  Widget firstcolumn=0,column=0;
//  Pixel bg;
//  unit unit_list[ptile.units.foo_list_size()];
//
//  XtSetSensitive(main_form, false);
//
//  unit_select_dialog_shell =
//    I_T(XtCreatePopupShell("unitselectdialogshell", 
//			   transientShellWidgetClass,
//			   toplevel, null, 0));
//
//  unit_select_form = XtVaCreateManagedWidget("unitselectform", 
//					     formWidgetClass, 
//					     unit_select_dialog_shell, null);
//
//  XtVaGetValues(unit_select_form, XtNbackground, &bg, null);
//  XSetForeground(display, fill_bg_gc, bg);
//
//  n = Math.min(MAX_SELECT_UNITS, ptile.units.foo_list_size());
//  r = number_of_rows(n);
//
//  fill_tile_unit_list(ptile, unit_list);
//
//  for(i=0; i<n; i++) {
//    unit punit = unit_list[i];
//    unit_type punittemp=punit.unit_type();
//    city pcity;
//    struct canvas store;
//    
//    if(!(i%r))  {
//      nargs=0;
//      if(i)  { XtSetArg(args[nargs], XtNfromHoriz, column); nargs++;}
//      column = XtCreateManagedWidget("column", formWidgetClass,
//				     unit_select_form,
//				     args, nargs);
//      if(!i) firstcolumn=column;
//    }
//
//    unit_select_ids[i]=punit.id;
//
//    pcity=Player_P.player_find_city_by_id(Game.game.player_ptr, punit.homecity);
//    
//    buffer = util.my_snprintf( "%s(%s)\n%s", 
//	    punittemp.name, 
//	    pcity ? pcity.name : "",
//	    unit_activity_text(punit));
//
//    unit_select_pixmaps[i]=XCreatePixmap(display, XtWindow(map_canvas), 
//					 UNIT_TILE_WIDTH, UNIT_TILE_HEIGHT,
//					 display_depth);
//
//    XFillRectangle(display, unit_select_pixmaps[i], fill_bg_gc,
//		   0, 0, UNIT_TILE_WIDTH, UNIT_TILE_HEIGHT);
//    store.pixmap = unit_select_pixmaps[i];
//    put_unit(punit, &store, 0, 0);
//
//    nargs=0;
//    XtSetArg(args[nargs], XtNbitmap, (XtArgVal)unit_select_pixmaps[i]);nargs++;
//    XtSetArg(args[nargs], XtNsensitive, 
//             can_unit_do_activity(punit, unit_activity.ACTIVITY_IDLE));nargs++;
//    if(i%r)  {
//      XtSetArg(args[nargs], XtNfromVert, unit_select_commands[i-1]); nargs++;
//    }
//    unit_select_commands[i]=XtCreateManagedWidget("unitselectcommands", 
//						  commandWidgetClass,
//						  column, args, nargs);
//
//    nargs=0;
//    XtSetArg(args[nargs], XtNlabel, (XtArgVal)buffer); nargs++;
//    XtSetArg(args[nargs], XtNfromHoriz, unit_select_commands[i]); nargs++;
//    if(i%r) {
//      XtSetArg(args[nargs], XtNfromVert, unit_select_commands[i-1]); nargs++;
//    }
//    unit_select_labels[i]=XtCreateManagedWidget("unitselectlabels", 
//						labelWidgetClass, 
//						column, args, nargs);
//
//    XtAddCallback(unit_select_commands[i],
//		  XtNdestroyCallback,free_bitmap_destroy_callback, null);
//    XtAddCallback(unit_select_commands[i],
//                  XtNcallback, unit_select_callback, null);
//  }
//
//  unit_select_no=i;
//
//  unit_select_close_command =
//    I_L(XtVaCreateManagedWidget("unitselectclosecommand", 
//				commandWidgetClass,
//				unit_select_form,
//				XtNfromVert, firstcolumn,
//				null));
//
//  unit_select_all_command =
//    I_L(XtVaCreateManagedWidget("unitselectallcommand", 
//				commandWidgetClass,
//				unit_select_form,
//				XtNfromVert, firstcolumn,
//				null));
//
//  XtAddCallback(unit_select_close_command, XtNcallback, unit_select_callback, null);
//  XtAddCallback(unit_select_all_command, XtNcallback, unit_select_all_callback, null);
//
//  xaw_set_relative_position(toplevel, unit_select_dialog_shell, 15, 10);
//  XtPopup(unit_select_dialog_shell, XtGrabNone);
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void unit_select_all_callback(Widget w, XtPointer client_data, 
//			      XtPointer call_data)
//{
//  int i;
//
//  XtSetSensitive(main_form, true);
//  XtDestroyWidget(unit_select_dialog_shell);
//  
//  for(i=0; i<unit_select_no; i++) {
//    unit punit = Player_P.player_find_unit_by_id(Game.game.player_ptr,
//						unit_select_ids[i]);
//    if(punit) {
//      set_unit_focus(punit);
//    }
//  }
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void unit_select_callback(Widget w, XtPointer client_data, 
//			    XtPointer call_data)
//{
//  int i;
//
//  XtSetSensitive(main_form, true);
//  XtDestroyWidget(unit_select_dialog_shell);
//
//  for(i=0; i<unit_select_no; i++) {
//
//    if(unit_select_commands[i]==w) {
//      unit punit = Player_P.player_find_unit_by_id(Game.game.player_ptr,
//						  unit_select_ids[i]);
//      if(punit) {
//	set_unit_focus(punit);
//      }
//      return;
//    }
//  }
//}
//
//
///****************************************************************
//popup the dialog 5% inside the main-window 
//*****************************************************************/
//void popup_races_dialog()
//{
//  Position x, y;
//  Dimension width, height;
//
//  XtSetSensitive(main_form, false);
//
//  create_races_dialog();
//
//  XtVaGetValues(toplevel, XtNwidth, &width, XtNheight, &height, null);
//
//  XtTranslateCoords(toplevel, (Position) width/20, (Position) height/20,
//		    &x, &y);
//  XtVaSetValues(races_dialog_shell, XtNx, x, XtNy, y, null);
//
//  XtPopup(races_dialog_shell, XtGrabNone);
//  XtSetKeyboardFocus(toplevel, races_dialog_shell);
//}
//
///****************************************************************
//...
//*****************************************************************/
//void popdown_races_dialog()
//{
//  if (races_dialog_shell) {
//  XtSetSensitive(main_form, true);
//  XtDestroyWidget(races_dialog_shell);
//  races_dialog_shell = null;
//  } /* else there is no dialog shell to destroy */
//}
//
///****************************************************************
//...
//*****************************************************************/
//void create_races_dialog()
//{
//  int per_row = 5;
//  int i, j, len, maxracelen, index;
//  String maxracename;
//  char namebuf[64];
//  int space;
//  XtWidgetGeometry geom;
//
//  maxracelen = 0;
//  for(i=0; i<Game.game.playable_nation_count; i++) {
//    len = strlen(Nation.get_nation_name(i));
//    maxracelen = MAX(maxracelen, len);
//  }
//  maxracelen = Math.min(maxracelen, MAX_LEN_NAME-1);
//  maxracename = util.my_snprintf( "%*s", maxracelen+2, "W");
//
//  races_dialog_shell = I_T(XtCreatePopupShell("racespopup", 
//					  transientShellWidgetClass,
//					  toplevel, null, 0));
//
//  races_form = XtVaCreateManagedWidget("racesform", 
//				       formWidgetClass, 
//				       races_dialog_shell, null);   
//
//  races_label = I_L(XtVaCreateManagedWidget("raceslabel", 
//				       labelWidgetClass, 
//				       races_form, null));  
//
//  races_toggles_form = XtVaCreateManagedWidget("racestogglesform", 
//					       formWidgetClass, 
//					       races_form, 
//					       XtNfromVert, races_label, 
//					       null);   
//
//  free(races_toggles);
//  races_toggles = fc_calloc(Game.game.playable_nation_count,sizeof(Widget));
//  free(races_toggles_to_nations);
//  races_toggles_to_nations = fc_calloc(Game.game.playable_nation_count,sizeof(int));
//
//  for( i = 0; i < ((Game.game.playable_nation_count-1)/per_row)+1; i++) {
//    index = i * per_row;
//    namebuf = util.my_snprintf( "racestoggle%d", index);
//    if( i == 0 ) {
//      races_toggles[index] =
//	XtVaCreateManagedWidget(namebuf,
//				toggleWidgetClass,
//				races_toggles_form,
//				XtNlabel, maxracename,
//				null);
//    } else {
//      races_toggles[index] =
//	XtVaCreateManagedWidget(namebuf,
//				toggleWidgetClass,
//				races_toggles_form,
//				XtNradioGroup,
//				races_toggles[index-1],
//				XtNfromVert,
//				races_toggles[index-per_row],
//				XtNlabel, maxracename,
//				null);
//    }
//
//    for( j = 1; j < per_row; j++) {
//      index = i * per_row + j;
//      if( index >= Game.game.playable_nation_count ) break;
//      namebuf = util.my_snprintf( "racestoggle%d", index);
//      if( i == 0 ) {
//	races_toggles[index] =
//	  XtVaCreateManagedWidget(namebuf,
//				  toggleWidgetClass,
//				  races_toggles_form,
//				  XtNradioGroup,
//				  races_toggles[index-1],
//				  XtNfromHoriz,
//				  races_toggles[index-1],
//				  XtNlabel, maxracename,
//				  null);
//      } else {
//	races_toggles[index] =
//	  XtVaCreateManagedWidget(namebuf,
//				  toggleWidgetClass,
//				  races_toggles_form,
//				  XtNradioGroup,
//				  races_toggles[index-1],
//				  XtNfromVert,
//				  races_toggles[index-per_row],
//				  XtNfromHoriz,
//				  races_toggles[index-1],
//				  XtNlabel, maxracename,
//				  null);
//      }
//    }
//  }
//
//  races_leader_form = XtVaCreateManagedWidget("racesleaderform",
//					      formWidgetClass,
//					      races_form,
//					      XtNfromVert, races_toggles_form,
//					      null);
//
//  XtVaGetValues(races_leader_form, XtNdefaultDistance, &space, null);
//  XtQueryGeometry(races_toggles[0], null, &geom);
//  races_leader = XtVaCreateManagedWidget("racesleader",
//					 asciiTextWidgetClass,
//					 races_leader_form,
//					 XtNeditType, XawtextEdit,
//					 XtNwidth,
//					   space + 2*(geom.width + geom.border_width),
//					 XtNstring, "",
//					 null);
//
//  races_leader_pick_popupmenu = 0;
//
//  races_leader_pick_menubutton =
//    I_L(XtVaCreateManagedWidget("racesleaderpickmenubutton",
//			    menuButtonWidgetClass,
//			    races_leader_form,
//			    XtNfromHoriz, races_leader,
//			    null));
//
//  races_sex_label = I_L(XtVaCreateManagedWidget("racessexlabel", 
//				            labelWidgetClass, 
//				            races_form, 
//					    XtNfromVert, races_leader_form, 
//					    null));  
//
//  races_sex_form = XtVaCreateManagedWidget("racessexform", 
//					   formWidgetClass, 
//					   races_form, 
//					   XtNfromVert, races_sex_label, 
//					   null);   
//
//  races_sex_toggles[0] =
//    I_L(XtVaCreateManagedWidget("racessextoggle0", 
//				toggleWidgetClass, 
//				races_sex_form,
//				null));
//
//  races_sex_toggles[1] =
//    I_L(XtVaCreateManagedWidget("racessextoggle1",
//				toggleWidgetClass, 
//				races_sex_form,
//				XtNfromHoriz, 
//				(XtArgVal)races_sex_toggles[0],
//				XtNradioGroup, 
//				races_sex_toggles[0], 
//				null));
//
//  /* find out styles that can be used at the Game.game beginning */
//  for(i=0,b_s_num=0; i<Game.game.styles_count && i<64; i++) {
//    if(city_styles[i].techreq == A_NONE) {
//      city_style_idx[b_s_num] = i;
//      city_style_ridx[i] = b_s_num;
//      b_s_num++;
//    }
//  }
//
//  races_style_label = I_L(XtVaCreateManagedWidget("racesstylelabel", 
//					      labelWidgetClass, 
//					      races_form,
//					      XtNfromVert, races_sex_form, 
//					      null));  
//
//  races_style_form = XtVaCreateManagedWidget("racesstyleform", 
//					       formWidgetClass, 
//					       races_form, 
//					       XtNfromVert, races_style_label, 
//					       null);   
//
//  free(races_style_toggles);
//  races_style_toggles = fc_calloc(b_s_num,sizeof(Widget));
//
//  for( i = 0; i < ((b_s_num-1)/per_row)+1; i++) {
//    index = i * per_row;
//    namebuf = util.my_snprintf( "racesstyle%d", index);
//    if( i == 0 ) {
//      races_style_toggles[index] =
//	XtVaCreateManagedWidget(namebuf,
//				toggleWidgetClass,
//				races_style_form,
//				XtNlabel, maxracename,
//				null);
//    } else {
//      races_style_toggles[index] =
//	XtVaCreateManagedWidget(namebuf,
//				toggleWidgetClass,
//				races_style_form,
//				XtNradioGroup,
//				races_style_toggles[index-1],
//				XtNfromVert,
//				races_style_toggles[index-per_row],
//				XtNlabel, maxracename,
//				null);
//    }
//
//    for( j = 1; j < per_row; j++) {
//      index = i * per_row + j;
//      if( index >= b_s_num ) break;
//      namebuf = util.my_snprintf( "racesstyle%d", index);
//      if( i == 0 ) {
//	races_style_toggles[index] =
//	  XtVaCreateManagedWidget(namebuf,
//				  toggleWidgetClass,
//				  races_style_form,
//				  XtNradioGroup,
//				  races_style_toggles[index-1],
//				  XtNfromHoriz,
//				  races_style_toggles[index-1],
//				  XtNlabel, maxracename,
//				  null);
//      } else {
//	races_style_toggles[index] =
//	  XtVaCreateManagedWidget(namebuf,
//				  toggleWidgetClass,
//				  races_style_form,
//				  XtNradioGroup,
//				  races_style_toggles[index-1],
//				  XtNfromVert,
//				  races_style_toggles[index-per_row],
//				  XtNfromHoriz,
//				  races_style_toggles[index-1],
//				  XtNlabel, maxracename,
//				  null);
//      }
//    }
//  }
//
//  races_action_form = XtVaCreateManagedWidget("racesactionform",
//					      formWidgetClass,
//					      races_form,
//					      XtNfromVert, races_style_form,
//					      null);
//
//  races_ok_command =
//    I_L(XtVaCreateManagedWidget("racesokcommand",
//				commandWidgetClass,
//				races_action_form,
//				null));
//
//  races_disconnect_command =
//    I_L(XtVaCreateManagedWidget("racesdisconnectcommand",
//				commandWidgetClass,
//				races_action_form,
//				XtNfromHoriz, races_ok_command,
//				null));
//
//  races_quit_command =
//    I_L(XtVaCreateManagedWidget("racesquitcommand",
//				commandWidgetClass,
//				races_action_form,
//				XtNfromHoriz, races_disconnect_command,
//				null));
//
//  XtAddCallback(races_disconnect_command, XtNcallback,
//		races_disconnect_command_callback, null);
//  XtAddCallback(races_quit_command, XtNcallback,
//		races_quit_command_callback, null);
//
//
//  for(i=0; i<Game.game.playable_nation_count; i++) {
//    XtAddCallback(races_toggles[i], XtNcallback,
//		  races_toggles_callback, INT_TO_XTPOINTER(i));
//  }
//
//
//  XtAddCallback(races_ok_command, XtNcallback,
//		races_ok_command_callback, null);
//
//
//  XtSetKeyboardFocus(races_form, races_leader);
//
//  XtRealizeWidget(races_dialog_shell);
//
//  for(i=0; i<Game.game.playable_nation_count; i++) {
//    races_toggles_to_nations[i] = i;
//  }
//  qsort(races_toggles_to_nations, i, sizeof(int), races_indirect_compare);
//
//  /* Build nation_to_race_toggle */
//  free(nation_to_race_toggle);
//  nation_to_race_toggle =
//      fc_calloc(Game.game.playable_nation_count, sizeof(int));
//  for (i = 0; i < Game.game.playable_nation_count; i++) {
//    nation_to_race_toggle[races_toggles_to_nations[i]] = i;
//  }
//
//  for(i=0; i<Game.game.playable_nation_count; i++) {
//    XtVaSetValues (races_toggles[i],
//		   XtNlabel, (XtArgVal)Nation.get_nation_name(races_toggles_to_nations[i]),
//		   null);
//  }
//
//  for(i=0; i<b_s_num; i++) {
//    XtVaSetValues(races_style_toggles[i], XtNlabel,
//		  (XtArgVal)city_styles[city_style_idx[i]].name, null);
//  }
//
//  select_random_race();
//}
//
///****************************************************************
//...
//*****************************************************************/
//void racesdlg_key_ok(Widget w)
//{
//  Widget ok = XtNameToWidget(XtParent(XtParent(w)), "*racesokcommand");
//  if (ok)
//    x_simulate_button_click(ok);
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void races_toggles_set_sensitive(boolean *nations_used)
//{
//  int i;
//
//  for (i = 0; i < Game.game.playable_nation_count; i++) {
//    XtSetSensitive(races_toggles[nation_to_race_toggle[i]], true);
//  }
//
//  for (i = 0; i < Game.game.playable_nation_count; i++) {
//    int nation = i, selected_nation = -1;
//
//    if (!nations_used[i]) {
//      continue;
//    }
//
//    if (races_buttons_get_current() != -1) {
//      selected_nation =
//	  races_toggles_to_nations[races_buttons_get_current()];
//    }
//
//    util.freelog(Log.LOG_DEBUG, "  [%d]: %d = %s", i, nation,
//	    Nation.get_nation_name(nation));
//
//    if (nation == selected_nation) {
//      XawToggleUnsetCurrent(races_toggles[0]);
//      XtSetSensitive(races_toggles[nation_to_race_toggle[nation]], false);
//      select_random_race();
//    } else {
//      XtSetSensitive(races_toggles[nation_to_race_toggle[nation]], false);
//    }
//  }
//}
//
///* We store this value locally in case it changes globally. */
//static int nation_count;
//
///**************************************************************************
//...
//**************************************************************************/
//void races_toggles_callback(Widget w, XtPointer client_data, 
//			    XtPointer call_data)
//{
//  int index = XTPOINTER_TO_INT(client_data);
//  int race = races_toggles_to_nations[index];
//  int j;
//  int leader_count;
//  leader leaders = get_nation_leaders(race, &leader_count);
//  Widget entry;
//
//  if(races_leader_pick_popupmenu)
//    XtDestroyWidget(races_leader_pick_popupmenu);
//
//  races_leader_pick_popupmenu =
//    XtVaCreatePopupShell("menu",
//			 simpleMenuWidgetClass,
//			 races_leader_pick_menubutton,
//			 null);
//
//  nation_count = Game.game.nation_count;
//
//  for(j=0; j<leader_count; j++) {
//    entry =
//      XtVaCreateManagedWidget(leaders[j].name,
//			      smeBSBObjectClass,
//			      races_leader_pick_popupmenu,
//			      null);
//    XtAddCallback(entry, XtNcallback, races_leader_pick_callback,
//		  INT_TO_XTPOINTER(nation_count * j + race));
//  }
//
//  races_leader_set_values(race, Rand.myrand(leader_count));
//
//  x_simulate_button_click
//  (
//   races_style_toggles[city_style_ridx[get_nation_city_style(race)]]
//  );
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void races_leader_pick_callback(Widget w, XtPointer client_data,
//				XtPointer call_data)
//{
//  int lead = XTPOINTER_TO_INT(client_data) / nation_count;
//  int race = XTPOINTER_TO_INT(client_data) - (nation_count * lead);
//
//  races_leader_set_values(race, lead);
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void races_leader_set_values(int race, int lead)
//{
//  int leader_count;
//  leader leaders = get_nation_leaders(race, &leader_count);
//
//  XtVaSetValues(races_leader, XtNstring, leaders[lead].name, null);
//  XawTextSetInsertionPoint(races_leader, strlen(leaders[lead].name));
//
//  races_sex_buttons_set_current(!leaders[lead].is_male);
//}
//
///**************************************************************************
//...
//**************************************************************************/
//int races_buttons_get_current()
//{
//  int i;
//  XtPointer dp, yadp;
//
//  if((Game.game.playable_nation_count)==1)
//    return 0;
//
//  if(!(dp=XawToggleGetCurrent(races_toggles[0])))
//    return -1;
//
//  for(i=0; i<Game.game.playable_nation_count; i++) {
//    XtVaGetValues(races_toggles[i], XtNradioData, &yadp, null);
//    if(dp==yadp)
//      return i;
//  }
//
//  return -1;
//}
//
///**************************************************************************
//...
//**************************************************************************/
//int races_sex_buttons_get_current()
//{
//  int i;
//  XtPointer dp, yadp;
//
//  if(!(dp=XawToggleGetCurrent(races_sex_toggles[0])))
//    return -1;
//
//  for(i=0; i<2; i++) {
//    XtVaGetValues(races_sex_toggles[i], XtNradioData, &yadp, null);
//    if(dp==yadp)
//      return i;
//  }
//
//  return -1;
//}
//
///**************************************************************************
//...
//**************************************************************************/
//int races_style_buttons_get_current()
//{
//  int i;
//  XtPointer dp, yadp;
//
//  if(b_s_num==1)
//    return 0;
//
//  if(!(dp=XawToggleGetCurrent(races_style_toggles[0])))
//    return -1;
//
//  for(i=0; i<b_s_num; i++) {
//    XtVaGetValues(races_style_toggles[i], XtNradioData, &yadp, null);
//    if(dp==yadp)
//      return i;
//  }
//
//  return -1;
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void races_sex_buttons_set_current(int i)
//{
//  XtPointer dp;
//
//  XtVaGetValues(races_sex_toggles[i], XtNradioData, &dp, null);
//
//  XawToggleSetCurrent(races_sex_toggles[0], dp);
//}
//
///**************************************************************************
//...
//**************************************************************************/
//int races_indirect_compare(final void *first, final void *second)
//{
//  int first_index;
//  int second_index;
//  final String first_string;
//  final String second_string;
//
//  first_index = *((final int *)first);
//  second_index = *((final int *)second);
//
//  first_string = Nation.get_nation_name(first_index);
//  second_string = Nation.get_nation_name(second_index);
//
//  return mystrcasecmp(first_string, second_string);
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void races_ok_command_callback(Widget w, XtPointer client_data, 
//			       XtPointer call_data)
//{
//  int selected_index, selected_sex, selected_style;
//  XtPointer dp;
//
//  if((selected_index=races_buttons_get_current())==-1) {
//    append_output_window("You must select a nation.");
//    return;
//  }
//
//  if((selected_sex=races_sex_buttons_get_current())==-1) {
//    append_output_window("You must select your sex.");
//    return;
//  }
//
//  if((selected_style=races_style_buttons_get_current())==-1) {
//    append_output_window("You must select your city style.");
//    return;
//  }
//
//  XtVaGetValues(races_leader, XtNstring, &dp, null);
//
//  /* perform a minimum of sanity test on the name */
//  if (dp.length() == 0) {
//    append_output_window("You must type a legal name.");
//    return;
//  }
//
//  dsend_packet_nation_select_req(&aconnection,
//				 races_toggles_to_nations[selected_index],
//				 selected_sex ? false : true,
//				 dp, city_style_idx[selected_style]);
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void races_disconnect_command_callback(Widget w, XtPointer client_data, 
//				       XtPointer call_data)
//{
//  popdown_races_dialog();
//  disconnect_from_server();
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void races_quit_command_callback(Widget w, XtPointer client_data, 
//				 XtPointer call_data)
//{
//  exit(EXIT_SUCCESS);
//}
//
///**************************************************************************
//  Frees a bitmap associated with a Widget when it is destroyed
//**************************************************************************/
//void free_bitmap_destroy_callback(Widget w, XtPointer client_data, 
//				  XtPointer call_data)
//{
//  Pixmap pm;
//
//  XtVaGetValues(w,XtNbitmap,&pm,null);
//  if(pm) XFreePixmap(XtDisplay(w),pm);
//}
//
///**************************************************************************
//  Destroys its widget.  Usefull for a popdown callback on pop-ups that
//  won't get resused.
//**************************************************************************/
//void destroy_me_callback(Widget w, XtPointer client_data, 
//			 XtPointer call_data)
//{
//  XtDestroyWidget(w);
//}
//
///**************************************************************************
//  Adjust tax rates from main window
//**************************************************************************/
//void taxrates_callback(Widget w, XtPointer client_data, XtPointer call_data)
//{
//  common_taxrates_callback((size_t) client_data);
//}
//

//  This function is called when the client disconnects or the Game.game is
//  over.  It should close all dialog windows for that Game.game.
//***********************************************************************/
//void popdown_all_game_dialogs()
//{
//  popdown_city_report_dialog();
//  popdown_meswin_dialog();
//  popdown_science_dialog();
//  popdown_economy_report_dialog();
//  popdown_activeunits_report_dialog();
//  popdown_players_dialog();
//  popdown_notify_dialog();
//}
}