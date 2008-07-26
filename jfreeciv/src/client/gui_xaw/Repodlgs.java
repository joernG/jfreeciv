package client.gui_xaw;

public class Repodlgs{

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
//#include <X11/Intrinsic.h>
//#include <X11/Shell.h>
//#include <X11/StringDefs.h>
//#include <X11/Xaw/Form.h>
//#include <X11/Xaw/Label.h>
//#include <X11/Xaw/SimpleMenu.h> 
//#include <X11/Xaw/Command.h>
//#include <X11/Xaw/List.h>
//#include <X11/Xaw/MenuButton.h>
//#include <X11/Xaw/SmeBSB.h>
//#include <X11/Xaw/Toggle.h>
//
//#include "city.h"
//#include "fcintl.h"
//#include "game.h"
//#include "government.h"
//#include "packets.h"
//#include "shared.h"
//#include "support.h"
//
//#include "cityrep.h"
//#include "climisc.h"
//#include "clinet.h"
//#include "dialogs.h"
//#include "gui_main.h"
//#include "gui_stuff.h"
//#include "helpdlg.h"
//#include "optiondlg.h"
//#include "text.h"
//
//#include "repodlgs.h"
//#include "repodlgs_common.h"
//
///******************************************************************/
//
//void create_science_dialog(boolean make_modal);
//void science_close_callback(Widget w, XtPointer client_data, 
//			    XtPointer call_data);
//void science_help_callback(Widget w, XtPointer client_data, 
//			   XtPointer call_data);
//void science_change_callback(Widget w, XtPointer client_data, 
//			     XtPointer call_data);
//void science_goal_callback(Widget w, XtPointer client_data, 
//			   XtPointer call_data);
//
//
///******************************************************************/
//static Widget science_dialog_shell;
//static Widget science_label;
//static Widget science_current_label, science_goal_label;
//static Widget science_change_menu_button, science_goal_menu_button;
//static Widget science_list, science_help_toggle;
//static Widget science_help_note;
//static int science_dialog_shell_is_modal;
//static Widget popupmenu, goalmenu;
//
///******************************************************************/
//void create_economy_report_dialog(boolean make_modal);
//void economy_close_callback(Widget w, XtPointer client_data, 
//			 XtPointer call_data);
//void economy_selloff_callback(Widget w, XtPointer client_data,
//                            XtPointer call_data);
//void economy_list_callback(Widget w, XtPointer client_data,
//                         XtPointer call_data);
//static int economy_improvement_type[B_LAST];
//
//static Widget economy_dialog_shell;
//static Widget economy_label, economy_label2;
//static Widget economy_list, economy_list_label;
//static Widget sellall_command, sellobsolete_command;
//static int economy_dialog_shell_is_modal;
//
///******************************************************************/
//void create_activeunits_report_dialog(boolean make_modal);
//void activeunits_close_callback(Widget w, XtPointer client_data, 
//			 XtPointer call_data);
//void activeunits_upgrade_callback(Widget w, XtPointer client_data, 
//			 XtPointer call_data);
//void activeunits_refresh_callback(Widget w, XtPointer client_data, 
//			 XtPointer call_data);
//void activeunits_list_callback(Widget w, XtPointer client_data, 
//                           XtPointer call_data);
//static int activeunits_type[unittype.U_LAST];
//
//static Widget activeunits_dialog_shell;
//static Widget activeunits_label, activeunits_label2;
//static Widget activeunits_list, activeunits_list_label;
//static Widget upgrade_command;
//
//static int activeunits_dialog_shell_is_modal;
///******************************************************************/
//
///******************************************************************
//...
//*******************************************************************/
//void update_report_dialogs()
//{
//  if(is_report_dialogs_frozen()) return;
//  activeunits_report_dialog_update();
//  economy_report_dialog_update();
//  city_report_dialog_update(); 
//  science_dialog_update();
//}
//
///****************************************************************
//...
//****************************************************************/
//final String get_centered_report_title(final String report_name)
//{
//  return create_centered_string(get_report_title(report_name));
//}
//
///****************************************************************
//...
//****************************************************************/
//static final String get_report_title_plus(final String report_name,
//					 final String additional)
//{
//  char buf[512];
//  
//  buf = util.my_snprintf( "%s%s", get_report_title(report_name),
//	      additional);
//
//  return create_centered_string(buf);
//}
//
///****************************************************************
//...
//************************ ***************************************/
//void popup_science_dialog(boolean make_modal)
//{
//
//  if(!science_dialog_shell) {
//    Position x, y;
//    Dimension width, height;
//    
//    science_dialog_shell_is_modal=make_modal;
//    
//    if(make_modal)
//      XtSetSensitive(main_form, false);
//    
//    create_science_dialog(make_modal);
//    
//    XtVaGetValues(toplevel, XtNwidth, &width, XtNheight, &height, null);
//    
//    XtTranslateCoords(toplevel, (Position) width/10, (Position) height/10,
//		      &x, &y);
//    XtVaSetValues(science_dialog_shell, XtNx, x, XtNy, y, null);
//    
//    XtPopup(science_dialog_shell, XtGrabNone);
//  }
//
//}
//
///****************************************************************
//  Closes the science dialog.
//*****************************************************************/
//void popdown_science_dialog()
//{
//  if (science_dialog_shell) {
//    if (science_dialog_shell_is_modal) {
//      XtSetSensitive(main_form, true);
//    }
//    XtDestroyWidget(science_dialog_shell);
//    science_dialog_shell = 0;
//  }
//}
//
///****************************************************************
//...
//*****************************************************************/
//void create_science_dialog(boolean make_modal)
//{
//  Widget science_form;
//  Widget  close_command;
//  final static char *tech_list_names_ptrs[A_LAST + 1];
//  int j, flag, num_list;
//  size_t i;
//  Dimension width;
//  char rate_text[128];
//  char current_text[512];
//  char goal_text[512];
//  final String report_title;
//  
//  if (Game.game.player_ptr.research.researching == A_UNSET) {
//    current_text = util.my_snprintf(
//		"Researching %s: %d/%d",
//		advances[A_NONE].name,
//		Game.game.player_ptr.research.bulbs_researched,
//		total_bulbs_required(Game.game.player_ptr));
//  } else {
//    current_text = util.my_snprintf(
//		"Researching %s: %d/%d",
//		get_tech_name(Game.game.player_ptr,
//			      Game.game.player_ptr.research.researching),
//		Game.game.player_ptr.research.bulbs_researched,
//		total_bulbs_required(Game.game.player_ptr));
//  }
//
//  if (Game.game.player_ptr.ai.tech_goal == A_UNSET) {
//    goal_text = util.my_snprintf(
//		"Goal: %s (%d steps)",
//		advances[A_NONE].name,
//		0);
//  } else {
//    goal_text = util.my_snprintf(
//		"Goal: %s (%d steps)",
//		advances[Game.game.player_ptr.ai.tech_goal].name,
//		num_unknown_techs_for_goal(Game.game.player_ptr,
//					   Game.game.player_ptr.ai.tech_goal));
//  }
//  
//  for(i=A_FIRST, j=0; i<Game.game.num_tech_types; i++)
//    if(get_invention(Game.game.player_ptr, i)==TECH_KNOWN) {
//      tech_list_names_ptrs[j]=advances[i].name;
//      j++;
//    }
//  tech_list_names_ptrs[j]=0;
//  qsort(tech_list_names_ptrs, j, sizeof(char *), compare_strings_ptrs);
//  num_list = j;
//  
//  science_dialog_shell =
//    I_T(XtVaCreatePopupShell("sciencepopup", 
//			     (make_modal ? transientShellWidgetClass :
//			      topLevelShellWidgetClass),
//			     toplevel, null));
//
//  science_form = XtVaCreateManagedWidget("scienceform", 
//					 formWidgetClass,
//					 science_dialog_shell,
//					 null);   
//  rate_text = util.my_snprintf( "\ntext not set yet");
//  report_title=get_report_title_plus("Science", rate_text);
//  science_label = XtVaCreateManagedWidget("sciencelabel", 
//					  labelWidgetClass, 
//					  science_form,
//					  XtNlabel, 
//					  report_title,
//					  null);
//  free((void *) report_title);
//
//  science_current_label = XtVaCreateManagedWidget("sciencecurrentlabel", 
//						  labelWidgetClass, 
//						  science_form,
//						  XtNlabel, 
//						  current_text,
//						  null);
//
//  science_goal_label = XtVaCreateManagedWidget("sciencegoallabel", 
//					       labelWidgetClass, 
//					       science_form,
//					       XtNlabel, goal_text,
//					       null);
//
//  science_change_menu_button =
//    I_L(XtVaCreateManagedWidget("sciencechangemenubutton", 
//				menuButtonWidgetClass,
//				science_form, null));
//
//  science_goal_menu_button =
//    I_L(XtVaCreateManagedWidget("sciencegoalmenubutton", 
//				menuButtonWidgetClass,
//				science_form, null));
//
//  science_help_note =
//    I_L(XtVaCreateManagedWidget("sciencehelpnote",
//				labelWidgetClass,
//				science_form, null));
//  
//  science_help_toggle = XtVaCreateManagedWidget("sciencehelptoggle", 
//						toggleWidgetClass, 
//						science_form,
//						null);
//  
//  science_list = XtVaCreateManagedWidget("sciencelist", 
//					 listWidgetClass,
//					 science_form,
//					 XtNlist, tech_list_names_ptrs,
//					 null);
//
//  close_command =
//    I_L(XtVaCreateManagedWidget("scienceclosecommand", 
//				commandWidgetClass,
//				science_form, null));
//  
//  
//  popupmenu=XtVaCreatePopupShell("menu", 
//				 simpleMenuWidgetClass, 
//				 science_change_menu_button, 
//				 null);
//
//  goalmenu=XtVaCreatePopupShell("menu", 
//				simpleMenuWidgetClass, 
//				science_goal_menu_button, 
//				null);
//
//  
//  for(i=A_FIRST, flag=0; i<Game.game.num_tech_types; i++)
//    if(get_invention(Game.game.player_ptr, i)==TECH_REACHABLE) {
//      Widget entry=
//      XtVaCreateManagedWidget(advances[i].name, smeBSBObjectClass, 
//			      popupmenu, null);
//      XtAddCallback(entry, XtNcallback, science_change_callback, 
//		    (XtPointer) i); 
//      flag=1;
//    }
//  
//  if(!flag)
//    XtSetSensitive(science_change_menu_button, false);
//  
// for(i=A_FIRST, flag=0; i<Game.game.num_tech_types; i++)
//    if (tech_is_available(Game.game.player_ptr, i)
//        && get_invention(Game.game.player_ptr, i) != TECH_KNOWN
//        && advances[i].req[0] != A_LAST && advances[i].req[1] != A_LAST
//        && (num_unknown_techs_for_goal(Game.game.player_ptr, i) < 11
//	    || i == Game.game.player_ptr.ai.tech_goal)) {
//      Widget entry=
//      XtVaCreateManagedWidget(advances[i].name, smeBSBObjectClass, 
//			      goalmenu, null);
//      XtAddCallback(entry, XtNcallback, science_goal_callback, 
//		    (XtPointer) i); 
//      flag=1;
//    }
//  
//  if(!flag)
//    XtSetSensitive(science_goal_menu_button, false);
//
//  XtAddCallback(close_command, XtNcallback, science_close_callback, null);
//  XtAddCallback(science_list, XtNcallback, science_help_callback, null);
//  XtAddCallback(science_help_toggle, XtNcallback, toggle_callback, null);
//
//  if(num_list>60) {
//    int ncol;
//    XtVaGetValues(science_list, XtNdefaultColumns, &ncol, null);
//    XtVaSetValues(science_list, XtNdefaultColumns, ncol+1, null);
//  }
//
//  XtRealizeWidget(science_dialog_shell);
//
//  if(!make_modal)  {
//    XSetWMProtocols(display, XtWindow(science_dialog_shell), 
//		    &wm_delete_window, 1);
//    XtOverrideTranslations(science_dialog_shell,
//      XtParseTranslationTable("<Message>WM_PROTOCOLS: msg-close-science-report()"));
//  }
//
//  width=500;
//  XtVaSetValues(science_label, XtNwidth, &width, null);
//
//  toggle_callback(science_help_toggle, null, null);
//  science_dialog_update();
//}
//
//
///****************************************************************
//...
//*****************************************************************/
//void science_change_callback(Widget w, XtPointer client_data, 
//			     XtPointer call_data)
//{
//  size_t to = (size_t) client_data;
//  Boolean b;
//
//  XtVaGetValues(science_help_toggle, XtNstate, &b, null);
//  if (b == true) {
//    popup_help_dialog_typed(advances[to].name, HELP_TECH);
//  } else {
//    dsend_packet_player_research(&aconnection, to);
//  }
//}
//
///****************************************************************
//...
//*****************************************************************/
//void science_goal_callback(Widget w, XtPointer client_data, 
//			   XtPointer call_data)
//{
//  size_t to = (size_t) client_data;
//  Boolean b;
//
//  XtVaGetValues(science_help_toggle, XtNstate, &b, null);
//  if (b == true) {
//    popup_help_dialog_typed(advances[to].name, HELP_TECH);
//  } else {
//    dsend_packet_player_tech_goal(&aconnection, to);
//  }
//}
//
//
///****************************************************************
//...
//*****************************************************************/
//void science_close_callback(Widget w, XtPointer client_data, 
//			    XtPointer call_data)
//{
//  popdown_science_dialog();
//}
//
//
///****************************************************************
//...
//*****************************************************************/
//void sciencereport_msg_close(Widget w)
//{
//  science_close_callback(w, null, null);
//}
//
//
///****************************************************************
//...
//*****************************************************************/
//void science_help_callback(Widget w, XtPointer client_data, 
//			    XtPointer call_data)
//{
//  XawListReturnStruct *ret=XawListShowCurrent(science_list);
//  Boolean b;
//
//  XtVaGetValues(science_help_toggle, XtNstate, &b, null);
//  if (b == true)
//    {
//      if(ret.list_index!=XAW_LIST_NONE)
//	popup_help_dialog_typed(ret.string, HELP_TECH);
//      else
//	popup_help_dialog_string(HELP_TECHS_ITEM);
//    }
//}
//
//
///****************************************************************
//...
//*****************************************************************/
//void science_dialog_update()
//{
//  if(is_report_dialogs_frozen()) return;
//  if(science_dialog_shell) {
//    char text[512];
//    static final String tech_list_names_ptrs[A_LAST + 1];
//    int j, flag;
//    size_t i;
//    final String report_title;
//    
//    report_title = get_report_title_plus("Science", science_dialog_text());
//    xaw_set_label(science_label, report_title);
//    free((void *) report_title);
//
//    if (Game.game.player_ptr.research.researching == A_UNSET) {
//      text = util.my_snprintf(
//		  "Researching %s: %d/%d",
//		  advances[A_NONE].name,
//		  Game.game.player_ptr.research.bulbs_researched,
//		  total_bulbs_required(Game.game.player_ptr));
//    } else {
//      text = util.my_snprintf(
//		  "Researching %s: %d/%d",
//		  get_tech_name(Game.game.player_ptr,
//				Game.game.player_ptr.research.researching),
//		  Game.game.player_ptr.research.bulbs_researched,
//		  total_bulbs_required(Game.game.player_ptr));
//    }
//
//    xaw_set_label(science_current_label, text);
//
//    if (Game.game.player_ptr.ai.tech_goal == A_UNSET) {
//      text = util.my_snprintf(
//		  "Goal: %s (%d steps)",
//		  advances[A_NONE].name,
//		  0);
//    } else {
//      text = util.my_snprintf(
//		  "Goal: %s (%d steps)",
//		  advances[Game.game.player_ptr.ai.tech_goal].name,
//		  num_unknown_techs_for_goal(Game.game.player_ptr,
//					    Game.game.player_ptr.ai.tech_goal));
//    }
//
//    xaw_set_label(science_goal_label, text);
//
//    for(i=A_FIRST, j=0; i<Game.game.num_tech_types; i++)
//      if(get_invention(Game.game.player_ptr, i)==TECH_KNOWN) {
//	tech_list_names_ptrs[j]=advances[i].name;
//	j++;
//      }
//    tech_list_names_ptrs[j]=0;
//    qsort(tech_list_names_ptrs, j, sizeof(char *), compare_strings_ptrs);
//
//    XawListChange(science_list, (char **)tech_list_names_ptrs, 0/*j*/, 0, 1);
//
//    XtDestroyWidget(popupmenu);
//    
//    popupmenu=XtVaCreatePopupShell("menu", 
//				   simpleMenuWidgetClass, 
//				   science_change_menu_button, 
//				   null);
//    
//      for(i=A_FIRST, flag=0; i<Game.game.num_tech_types; i++)
//      if(get_invention(Game.game.player_ptr, i)==TECH_REACHABLE) {
//	Widget entry=
//	  XtVaCreateManagedWidget(advances[i].name, smeBSBObjectClass, 
//				  popupmenu, null);
//	XtAddCallback(entry, XtNcallback, science_change_callback, 
//		      (XtPointer) i); 
//	flag=1;
//      }
//    
//    if(!flag)
//      XtSetSensitive(science_change_menu_button, false);
//
//    XtDestroyWidget(goalmenu);
//    
//    goalmenu=XtVaCreatePopupShell("menu", 
//				  simpleMenuWidgetClass, 
//				  science_goal_menu_button, 
//				  null);
//    
//    for(i=A_FIRST, flag=0; i<Game.game.num_tech_types; i++)
//      if (tech_is_available(Game.game.player_ptr, i)
//	  && get_invention(Game.game.player_ptr, i) != TECH_KNOWN
//	  && advances[i].req[0] != A_LAST && advances[i].req[1] != A_LAST
//	  && (num_unknown_techs_for_goal(Game.game.player_ptr, i) < 11
//	      || i == Game.game.player_ptr.ai.tech_goal)) {
//	Widget entry=
//	  XtVaCreateManagedWidget(advances[i].name, smeBSBObjectClass, 
//				  goalmenu, null);
//	XtAddCallback(entry, XtNcallback, science_goal_callback, 
//		      (XtPointer) i); 
//	flag=1;
//      }
//    
//    if(!flag)
//      XtSetSensitive(science_goal_menu_button, false);
//
//  }
//  
//}
//
///****************************************************************
//
//                      ECONOMY REPORT DIALOG
// 
//****************************************************************/
//
///****************************************************************
//...
//****************************************************************/
//void popup_economy_report_dialog(boolean make_modal)
//{
//  if(!economy_dialog_shell) {
//      Position x, y;
//      Dimension width, height;
//      
//      economy_dialog_shell_is_modal=make_modal;
//    
//      if(make_modal)
//	XtSetSensitive(main_form, false);
//      
//      create_economy_report_dialog(make_modal);
//      
//      XtVaGetValues(toplevel, XtNwidth, &width, XtNheight, &height, null);
//      
//      XtTranslateCoords(toplevel, (Position) width/10, (Position) height/10,
//			&x, &y);
//      XtVaSetValues(economy_dialog_shell, XtNx, x, XtNy, y, null);
//      
//      XtPopup(economy_dialog_shell, XtGrabNone);
//   }
//}
//
///****************************************************************
//  Closes the economy report.
//****************************************************************/
//void popdown_economy_report_dialog()
//{
//  if (economy_dialog_shell) {
//    if (economy_dialog_shell_is_modal) {
//      XtSetSensitive(main_form, true);
//    }
//    XtDestroyWidget(economy_dialog_shell);
//    economy_dialog_shell = 0;
//  }
//}
//
///****************************************************************
//...
//*****************************************************************/
//void create_economy_report_dialog(boolean make_modal)
//{
//  Widget economy_form;
//  Widget close_command;
//  final String report_title;
//  
//  economy_dialog_shell =
//    I_T(XtVaCreatePopupShell("reporteconomypopup", 
//			     (make_modal ? transientShellWidgetClass :
//			      topLevelShellWidgetClass),
//			     toplevel, null));
//
//  economy_form = XtVaCreateManagedWidget("reporteconomyform", 
//					 formWidgetClass,
//					 economy_dialog_shell,
//					 null);   
//
//  report_title=get_centered_report_title("Economy");
//  economy_label = XtVaCreateManagedWidget("reporteconomylabel", 
//				       labelWidgetClass, 
//				       economy_form,
//				       XtNlabel, report_title,
//				       null);
//  free((void *) report_title);
//
//  economy_list_label =
//    I_L(XtVaCreateManagedWidget("reporteconomylistlabel", labelWidgetClass, 
//				economy_form, null));
//  
//  economy_list
//    = XtVaCreateManagedWidget("reporteconomylist", listWidgetClass,
//			      economy_form, null);
//
//  economy_label2
//    = XtVaCreateManagedWidget("reporteconomylabel2", labelWidgetClass, 
//			      economy_form, null);
//
//  close_command =
//    I_L(XtVaCreateManagedWidget("reporteconomyclosecommand", commandWidgetClass,
//				economy_form, null));
//
//  sellobsolete_command =
//    I_L(XtVaCreateManagedWidget("reporteconomysellobsoletecommand", 
//				commandWidgetClass,
//				economy_form,
//				XtNsensitive, false,
//				null));
//
//  sellall_command  =
//    I_L(XtVaCreateManagedWidget("reporteconomysellallcommand", 
//				commandWidgetClass,
//				economy_form,
//				XtNsensitive, false,
//				null));
//	
//  XtAddCallback(economy_list, XtNcallback, economy_list_callback, null);
//  XtAddCallback(close_command, XtNcallback, economy_close_callback, null);
//  XtAddCallback(sellobsolete_command, XtNcallback, economy_selloff_callback, (XtPointer)0);
//  XtAddCallback(sellall_command, XtNcallback, economy_selloff_callback, (XtPointer)1);
//  XtRealizeWidget(economy_dialog_shell);
//
//  if(!make_modal)  {
//    XSetWMProtocols(display, XtWindow(economy_dialog_shell), 
//		    &wm_delete_window, 1);
//    XtOverrideTranslations(economy_dialog_shell,
//      XtParseTranslationTable("<Message>WM_PROTOCOLS: msg-close-economy-report()"));
//  }
//
//  economy_report_dialog_update();
//}
//
//
//
///****************************************************************
//  Called when a building type is selected in the economy list.
//*****************************************************************/
//void economy_list_callback(Widget w, XtPointer client_data, 
//			 XtPointer call_data)
//{
//  XawListReturnStruct *ret = XawListShowCurrent(economy_list);
//
//  if (ret.list_index != XAW_LIST_NONE) {
//    /* The user has selected an improvement type. */
//    int i = economy_improvement_type[ret.list_index];
//    boolean is_sellable = (i >= 0 && i < Game.game.num_impr_types && !is_wonder(i));
//
//    XtSetSensitive(sellobsolete_command, is_sellable
//		   && improvement_obsolete(Game.game.player_ptr, i));
//    XtSetSensitive(sellall_command, is_sellable);
//  } else {
//    /* No selection has been made. */
//    XtSetSensitive(sellobsolete_command, false);
//    XtSetSensitive(sellall_command, false);
//  }
//}
//
///****************************************************************
//...
//*****************************************************************/
//void economy_close_callback(Widget w, XtPointer client_data, 
//			 XtPointer call_data)
//{
//  popdown_economy_report_dialog();
//}
//
///****************************************************************
//...
//*****************************************************************/
//void economyreport_msg_close(Widget w)
//{
//  economy_close_callback(w, null, null);
//}
//
///****************************************************************
//...
//*****************************************************************/
//void economy_selloff_callback(Widget w, XtPointer client_data, 
//			    XtPointer call_data)
//{
//  char str[1024];
//  XawListReturnStruct *ret = XawListShowCurrent(economy_list);
//
//  if (ret.list_index == XAW_LIST_NONE) {
//    return;
//  }
//
//  sell_all_improvements(economy_improvement_type[ret.list_index],
//			client_data == null, str, sizeof(str));
//  popup_notify_dialog("Sell-Off:", "Results", str);
//}
//
///****************************************************************
//...
//*****************************************************************/
//void economy_report_dialog_update()
//{
//  if(is_report_dialogs_frozen()) return;
//  if(economy_dialog_shell) {
//    int i, entries_used, tax, total;
//    Dimension width; 
//    static char *economy_list_names_ptrs[B_LAST+1];
//    static char economy_list_names[B_LAST][200];
//    final String report_title;
//    char economy_total[48];
//    struct improvement_entry entries[B_LAST];
//    
//    report_title=get_centered_report_title("Economy");
//    xaw_set_label(economy_label, report_title);
//    free((void *) report_title);
//
//    get_economy_report_data(entries, &entries_used, &total, &tax);
//
//    for (i = 0; i < entries_used; i++) {
//      improvement_entry p = &entries[i];
//
//      my_snprintf(economy_list_names[i], sizeof(economy_list_names[i]),
//		  "%-20s%5d%5d%6d", Improvement.get_improvement_name(p.type),
//		  p.count, p.cost, p.total_cost);
//      economy_list_names_ptrs[i] = economy_list_names[i];
//      economy_improvement_type[i] = p.type;
//    }
//    
//    if (entries_used == 0) {
//      sz_strlcpy(economy_list_names[0],
//		 "                                          ");
//      economy_list_names_ptrs[0] = economy_list_names[0];
//      entries_used = 1;
//    }
//    economy_list_names_ptrs[entries_used] = null;
//
//    economy_total = util.my_snprintf(
//		"Income:%6d    Total Costs: %6d", tax, total); 
//    xaw_set_label(economy_label2, economy_total); 
//    
//    XawListChange(economy_list, economy_list_names_ptrs, 0, 0, 1);
//
//    XtVaGetValues(economy_list, XtNwidth, &width, null);
//    XtVaSetValues(economy_list_label, XtNwidth, width, null); 
//
//    XtVaSetValues(economy_label2, XtNwidth, width, null); 
//
//    XtVaSetValues(economy_label, XtNwidth, width, null); 
//
//  }
//  
//}
//
///****************************************************************
//
//                      ACTIVE UNITS REPORT DIALOG
// 
//****************************************************************/
//
///****************************************************************
//...
//****************************************************************/
//void popup_activeunits_report_dialog(boolean make_modal)
//{
//  if(!activeunits_dialog_shell) {
//      Position x, y;
//      Dimension width, height;
//      
//      activeunits_dialog_shell_is_modal=make_modal;
//    
//      if(make_modal)
//	XtSetSensitive(main_form, false);
//      
//      create_activeunits_report_dialog(make_modal);
//      
//      XtVaGetValues(toplevel, XtNwidth, &width, XtNheight, &height, null);
//      
//      XtTranslateCoords(toplevel, (Position) width/10, (Position) height/10,
//			&x, &y);
//      XtVaSetValues(activeunits_dialog_shell, XtNx, x, XtNy, y, null);
//      
//      XtPopup(activeunits_dialog_shell, XtGrabNone);
//   }
//}
//
///****************************************************************
//  Closes the active units report.
//*****************************************************************/
//void popdown_activeunits_report_dialog()
//{
//  if (activeunits_dialog_shell) {
//    if (activeunits_dialog_shell_is_modal) {
//      XtSetSensitive(main_form, true);
//    }
//    XtDestroyWidget(activeunits_dialog_shell);
//    activeunits_dialog_shell = 0;
//  }
//}
//
///****************************************************************
//...
//*****************************************************************/
//void create_activeunits_report_dialog(boolean make_modal)
//{
//  Widget activeunits_form;
//  Widget close_command, refresh_command;
//  final String report_title;
//  
//  activeunits_dialog_shell =
//    I_T(XtVaCreatePopupShell("reportactiveunitspopup", 
//			     (make_modal ? transientShellWidgetClass :
//			      topLevelShellWidgetClass),
//			     toplevel, null));
//
//  activeunits_form = XtVaCreateManagedWidget("reportactiveunitsform", 
//					 formWidgetClass,
//					 activeunits_dialog_shell,
//					 null);   
//
//  report_title=get_centered_report_title("Units");
//  activeunits_label = XtVaCreateManagedWidget("reportactiveunitslabel", 
//				       labelWidgetClass, 
//				       activeunits_form,
//				       XtNlabel, report_title,
//				       null);
//  free((void *) report_title);
//
//  activeunits_list_label =
//    I_L(XtVaCreateManagedWidget("reportactiveunitslistlabel", 
//				labelWidgetClass, 
//				activeunits_form,
//				null));
//  
//  activeunits_list = XtVaCreateManagedWidget("reportactiveunitslist", 
//				      listWidgetClass,
//				      activeunits_form,
//				      null);
//
//  activeunits_label2 = XtVaCreateManagedWidget("reportactiveunitslabel2", 
//				       labelWidgetClass, 
//				       activeunits_form,
//                                       XtNlabel, 
//				       "Totals: ...", 
//				       null);
//
//  close_command =
//    I_L(XtVaCreateManagedWidget("reportactiveunitsclosecommand", 
//				commandWidgetClass,
//				activeunits_form,
//				null));
//
//  upgrade_command =
//    I_L(XtVaCreateManagedWidget("reportactiveunitsupgradecommand", 
//				commandWidgetClass,
//				activeunits_form,
//				XtNsensitive, false,
//				null));
//
//  refresh_command =
//    I_L(XtVaCreateManagedWidget("reportactiveunitsrefreshcommand", 
//				commandWidgetClass,
//				activeunits_form,
//				null));
//
//  XtAddCallback(activeunits_list, XtNcallback, activeunits_list_callback, null);
//  XtAddCallback(close_command, XtNcallback, activeunits_close_callback, null);
//  XtAddCallback(upgrade_command, XtNcallback, activeunits_upgrade_callback, null);
//  XtAddCallback(refresh_command, XtNcallback, activeunits_refresh_callback, null);
//
//  XtRealizeWidget(activeunits_dialog_shell);
//
//  if(!make_modal)  {
//    XSetWMProtocols(display, XtWindow(activeunits_dialog_shell), 
//		    &wm_delete_window, 1);
//    XtOverrideTranslations(activeunits_dialog_shell,
//      XtParseTranslationTable("<Message>WM_PROTOCOLS: msg-close-units-report()"));
//  }
//
//  activeunits_report_dialog_update();
//}
//
///****************************************************************
//...
//*****************************************************************/
//void activeunits_list_callback(Widget w, XtPointer client_data, 
//			 XtPointer call_data)
//{
//  XawListReturnStruct *ret;
//  int inx;
//  int may_upgrade;
//
//  ret = XawListShowCurrent (activeunits_list);
//  inx = ret.list_index;
//
//  may_upgrade =
//    ((inx != XAW_LIST_NONE) &&
//     (unit_type_exists (activeunits_type[inx])) &&
//     (can_upgrade_unittype (Game.game.player_ptr, activeunits_type[inx]) != -1));
//
//  XtSetSensitive (upgrade_command, may_upgrade);
//}
//
///****************************************************************
//...
//*****************************************************************/
//static void upgrade_callback_yes(Widget w, XtPointer client_data, 
//                                 XtPointer call_data)
//{
//  dsend_packet_unit_type_upgrade(&aconnection, (size_t) client_data);
//  destroy_message_dialog(w);
//}
//
///****************************************************************
//...
//*****************************************************************/
//static void upgrade_callback_no(Widget w, XtPointer client_data, 
//                                XtPointer call_data)
//{
//  destroy_message_dialog(w);
//}
//
///****************************************************************
//...
//*****************************************************************/
//void activeunits_upgrade_callback(Widget w, XtPointer client_data, 
//			 XtPointer call_data)
//{
//  char buf[512];
//  int ut1,ut2;
//
//  XawListReturnStruct *ret;
//  ret=XawListShowCurrent(activeunits_list);
//
//  if(ret.list_index!=XAW_LIST_NONE) {
//    ut1 = activeunits_type[ret.list_index];
//    if (!(unit_type_exists (ut1))) {
//      return;
//    }
//    /* puts(Unittype_P.unit_types[ut1].name); */
//
//    ut2 = can_upgrade_unittype(Game.game.player_ptr,
//			       activeunits_type[ret.list_index]);
//
//    buf = util.my_snprintf(
//	    ("Upgrade as many %s to %s as possible for %d gold each?\n" +
//	      "Treasury contains %d gold."),
//	    Unittype_P.unit_types[ut1].name, Unittype_P.unit_types[ut2].name,
//	    unit_upgrade_price(Game.game.player_ptr, ut1, ut2),
//	    Game.game.player_ptr.economic.gold);
//
//    popup_message_dialog(toplevel, "upgradedialog", buf,
//			 upgrade_callback_yes,
//			 INT_TO_XTPOINTER(activeunits_type[ret.list_index]),
//			 0, upgrade_callback_no, 0, 0, null);
//  }
//}
//
///****************************************************************
//...
//*****************************************************************/
//void activeunits_close_callback(Widget w, XtPointer client_data, 
//			 XtPointer call_data)
//{
//  popdown_activeunits_report_dialog();
//}
//
///****************************************************************
//...
//*****************************************************************/
//void activeunits_refresh_callback(Widget w, XtPointer client_data, 
//				  XtPointer call_data)
//{
//  activeunits_report_dialog_update();
//}
//
///****************************************************************
//...
//*****************************************************************/
//void activeunits_msg_close(Widget w)
//{
//  activeunits_close_callback(w, null, null);
//}
//
///****************************************************************
//...
//*****************************************************************/
//void activeunits_report_dialog_update()
//{
//  struct repoinfo {
//    int active_count;
//    int upkeep_shield;
//    int upkeep_food;
//    /* int upkeep_gold;   FIXME: add gold when gold is implemented --jjm */
//    int building_count;
//  };
//  if(is_report_dialogs_frozen()) return;
//  if(activeunits_dialog_shell) {
//    int k;
//    Dimension width; 
//    static char *activeunits_list_names_ptrs[unittype.U_LAST+1];
//    static char activeunits_list_names[unittype.U_LAST][200];
//    struct repoinfo unitarray[unittype.U_LAST];
//    struct repoinfo unittotals;
//    final String report_title;
//    char activeunits_total[100];
//    
//    report_title = get_centered_report_title("Units");
//    xaw_set_label(activeunits_label, report_title);
//    free((void *) report_title);
//
//    memset(unitarray, '\0', sizeof(unitarray));
//    for (unit punit : Game.game.player_ptr.units.data) {
//      (unitarray[punit.type].active_count)++;
//      if (punit.homecity) {
//	unitarray[punit.type].upkeep_shield += punit.upkeep;
//	unitarray[punit.type].upkeep_food += punit.upkeep_food;
//      }
//    }
//    }
//    city_list_iterate(Game.game.player_ptr.cities,pcity) {
//      if (pcity.is_building_unit &&
//	  (unit_type_exists (pcity.currently_building)))
//	(unitarray[pcity.currently_building].building_count)++;
//    }
//    }
//
//    k = 0;
//    memset(&unittotals, '\0', sizeof(unittotals));
//    unit_type_iterate(i) {
//      if ((unitarray[i].active_count > 0) || (unitarray[i].building_count > 0)) {
//	my_snprintf
//	  (
//	   activeunits_list_names[k],
//	   sizeof(activeunits_list_names[k]),
//	   "%-27s%c%9d%9d%9d%9d",
//	   unit_name(i),
//	   can_upgrade_unittype(Game.game.player_ptr, i) != -1 ? '*': '-',
//	   unitarray[i].building_count,
//	   unitarray[i].active_count,
//	   unitarray[i].upkeep_shield,
//	   unitarray[i].upkeep_food
//	  );
//	activeunits_list_names_ptrs[k]=activeunits_list_names[k];
//	activeunits_type[k]=(unitarray[i].active_count > 0) ? i : unittype.U_LAST;
//	k++;
//	unittotals.active_count += unitarray[i].active_count;
//	unittotals.upkeep_shield += unitarray[i].upkeep_shield;
//	unittotals.upkeep_food += unitarray[i].upkeep_food;
//	unittotals.building_count += unitarray[i].building_count;
//      }
//    } unit_type_iterate_end;
//
//    if (k==0) {
//      sz_strlcpy(activeunits_list_names[0], "                                ");
//      activeunits_list_names_ptrs[0]=activeunits_list_names[0];
//      k=1;
//    }
//
//    activeunits_total = util.my_snprintf(
//	    "Totals:                     %9d%9d%9d%9d",
//	    unittotals.building_count, unittotals.active_count,
//	    unittotals.upkeep_shield, unittotals.upkeep_food);
//    xaw_set_label(activeunits_label2, activeunits_total); 
//
//    activeunits_list_names_ptrs[k]=0;
//    XawListChange(activeunits_list, activeunits_list_names_ptrs, 0, 0, 1);
//
//    XtVaGetValues(activeunits_list, XtNwidth, &width, null);
//    XtVaSetValues(activeunits_list_label, XtNwidth, width, null); 
//    XtVaSetValues(activeunits_label2, XtNwidth, width, null); 
//    XtVaSetValues(activeunits_label, XtNwidth, width, null); 
//
//    activeunits_list_callback(null, null, null);
//  }
//}
//
///****************************************************************
//  Show a dialog with player statistics at endgame.
//  TODO: Display all statistics in packet_endgame_report.
//*****************************************************************/
//void popup_endgame_report_dialog(packet_endgame_report packet)
//{
//  char buffer[150 * Shared_H.MAX_NUM_PLAYERS];
//  int i;
// 
//  buffer[0] = '\0';
//  for (i = 0; i < packet.nscores; i++) {
//    cat_snprintf(buffer, sizeof(buffer),
//                 PL("%2d: The %s ruler %s scored %d point\n",
//                     "%2d: The %s ruler %s scored %d points\n",
//                     packet.score[i]),
//                 i + 1,
//                 get_nation_name(get_player(packet.id[i]).nation),
//                 get_player(packet.id[i]).name,
//                 packet.score[i]);
//  }
//  popup_notify_dialog("Final Report:",
//                      "The Greatest Civilizations in the world.",
//                      buffer);
//}
//
///*************************************************************************
//  Server options dialog
//*************************************************************************/
//void popup_settable_options_dialog()
//{
//  /* PORT ME */
//}
}