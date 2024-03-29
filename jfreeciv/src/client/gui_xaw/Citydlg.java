package client.gui_xaw;

import common.Game;

public class Citydlg{

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
//#include <X11/Xaw/MenuButton.h>
//#include <X11/Xaw/SimpleMenu.h>
//#include <X11/Xaw/SmeBSB.h>
//#include <X11/Xaw/List.h>
//#include <X11/Xaw/Viewport.h>
//#include <X11/Xaw/AsciiText.h>  
//#include <X11/Xaw/Toggle.h>     
//#include <X11/IntrinsicP.h>
//
//#include "pixcomm.h"
//#include "canvas.h"
//
//#include "city.h"
//#include "fcintl.h"
//#include "Game.game.h"
//#include "genlist.h"
//#include "Map.map.h"
//#include "mem.h"
//#include "packets.h"
//#include "player.h"
//#include "shared.h"
//#include "support.h"
//
//#include "cma_fec.h"
//
//#include "cityrep.h"
//#include "citydlg.h"
//#include "cma_fe.h"
//#include "colors.h"
//#include "control.h" /* request_xxx and set_unit_focus */
//#include "dialogs.h"
//#include "graphics.h"
//#include "gui_main.h"
//#include "gui_stuff.h"
//#include "helpdlg.h"
//#include "inputdlg.h"
//#include "mapctrl.h"
//#include "mapview.h"
//#include "optiondlg.h"		/* for toggle_callback */
//#include "repodlgs.h"
//#include "wldlg.h"
//
//#include "citydlg_common.h"
//#include "climap.h"
//#include "climisc.h"
//#include "clinet.h"
//#include "options.h"
//#include "text.h"
//#include "tilespec.h"
//
//#include "cityicon.ico"
//
//public static final int MIN_NUM_CITIZENS = 22;
//public static final int MAX_NUM_CITIZENS = 50;
//public static final int DEFAULT_NUM_CITIZENS = 38;
//public static final int MIN_NUM_UNITS = 8;
//public static final int MAX_NUM_UNITS = 20;
//public static final int DEFAULT_NUM_UNITS = 11;
//
//struct city_dialog {
//  city pcity;
//
//  int num_citizens_shown;
//  int num_units_shown;
//
//  Widget shell;
//  Widget main_form;
//  Widget left_form;
//  Widget cityname_label;
//  Widget *citizen_labels;
//  Widget production_label;
//  Widget output_label;
//  Widget storage_label;
//  Widget pollution_label;
//  Widget sub_form;
//  Widget map_canvas;
//  Widget sell_command;
//  Widget close_command, rename_command, trade_command, activate_command;
//  Widget show_units_command, cityopt_command, cma_command;
//  Widget building_label, progress_label, buy_command, change_command,
//    worklist_command, worklist_label;
//  Widget improvement_viewport, improvement_list;
//  Widget support_unit_label;
//  Widget *support_unit_pixcomms;
//  Widget support_unit_next_command;
//  Widget support_unit_prev_command;
//  Widget present_unit_label;
//  Widget *present_unit_pixcomms;
//  Widget present_unit_next_command;
//  Widget present_unit_prev_command;
//  Widget change_list;
//  Widget rename_input;
//  Widget worklist_shell;
//  
//  int sell_id;
//  
//  int support_unit_base;
//  int present_unit_base;
//  char improvlist_names[Improvement.B_LAST+1][64];
//  char *improvlist_names_ptrs[Improvement.B_LAST+1];
//  
//  char *change_list_names_ptrs[Improvement.B_LAST+1+unittype.U_LAST+1+1];
//  char change_list_names[Improvement.B_LAST+1+unittype.U_LAST+1][200];
//  int change_list_ids[Improvement.B_LAST+1+unittype.U_LAST+1];
//  int change_list_num_improvements;
//
//  int is_modal;
//};
//
//#define SPECLIST_TAG dialog
//#define SPECLIST_TYPE struct city_dialog
//#include "speclist.h"
//
//#define dialog_list_iterate(dialoglist, pdialog) \
//    TYPED_LIST_ITERATE(struct city_dialog, dialoglist, pdialog)
//#define dialog_list_iterate_end  LIST_ITERATE_END
//
//static Speclists<dialog> dialog_list;
//static boolean dialog_list_has_been_initialised = false;
//
//static city_dialog get_city_dialog(city pcity);
//static city_dialog create_city_dialog(city pcity, boolean make_modal);
//static void close_city_dialog(city_dialog pdialog);
//
//static void city_dialog_update_improvement_list(city_dialog pdialog);
//static void city_dialog_update_title(city_dialog pdialog);
//static void city_dialog_update_supported_units(city_dialog pdialog, int id);
//static void city_dialog_update_present_units(city_dialog pdialog, int id);
//static void city_dialog_update_citizens(city_dialog pdialog);
//static void city_dialog_update_production(city_dialog pdialog);
//static void city_dialog_update_output(city_dialog pdialog);
//static void city_dialog_update_building(city_dialog pdialog);
//static void city_dialog_update_storage(city_dialog pdialog);
//static void city_dialog_update_pollution(city_dialog pdialog);
//
//static void sell_callback(Widget w, XtPointer client_data, XtPointer call_data);
//static void buy_callback(Widget w, XtPointer client_data, XtPointer call_data);
//static void change_callback(Widget w, XtPointer client_data, XtPointer call_data);
//static void worklist_callback(Widget w, XtPointer client_data, XtPointer call_data);
//void commit_city_worklist(worklist pwl, void *data);
//void cancel_city_worklist(void *data);
//static void close_callback(Widget w, XtPointer client_data, XtPointer call_data);
//static void rename_callback(Widget w, XtPointer client_data, XtPointer call_data);
//static void trade_callback(Widget w, XtPointer client_data, XtPointer call_data);
//static void activate_callback(Widget w, XtPointer client_data, XtPointer call_data);
//static void show_units_callback(Widget W, XtPointer client_data, XtPointer call_data);
//static void units_next_prev_callback(Widget W, XtPointer client_data,
//				     XtPointer call_data);
//static void unitupgrade_callback_yes(Widget w, XtPointer client_data,
//				     XtPointer call_data);
//static void unitupgrade_callback_no(Widget w, XtPointer client_data,
//				    XtPointer call_data);
//static void upgrade_callback(Widget w, XtPointer client_data, XtPointer call_data);
//
//static void present_units_callback(Widget w, XtPointer client_data, 
//				   XtPointer call_data);
//static void cityopt_callback(Widget w, XtPointer client_data, 
//			     XtPointer call_data);
//static void cma_callback(Widget w, XtPointer client_data,
//                         XtPointer call_data);
//static void popdown_cityopt_dialog();
//
///****************************************************************
//...
//*****************************************************************/
//static void get_contents_of_pollution(city_dialog pdialog,
//				      char *retbuf, int n)
//{
//  city pcity;
//  int pollution=0;
//
//  if (pdialog) {
//    pcity=pdialog.pcity;
//    pollution=pcity.pollution;
//  }
//
//  retbuf = String.format "Pollution:    %3d", pollution);
//}
//
///****************************************************************
//...
//*****************************************************************/
//static void get_contents_of_storage(city_dialog pdialog,
//				    char *retbuf, int n)
//{
//  city pcity;
//  int foodstock=0;
//  int foodbox=0;
//
//  if (pdialog) {
//    pcity=pdialog.pcity;
//    foodstock=pcity.food_stock;
//    foodbox=city_granary_size(pcity.size);
//  }
//
//  /* We used to mark cities with a granary with a "*" here. */
//  retbuf = String.format "Granary:  %3d/%-3d",
//	      foodstock, foodbox);
//}
//
///****************************************************************
//...
//*****************************************************************/
//static void get_contents_of_production(city_dialog pdialog,
//				       char *retbuf, int n)
//{
//  city pcity;
//  int foodprod=0;
//  int foodsurplus=0;
//  int shieldprod=0;
//  int shieldsurplus=0;
//  int tradeprod=0;
//  int tradesurplus=0;
//
//  if (pdialog) {
//    pcity=pdialog.pcity;
//    foodprod=pcity.food_prod;
//    foodsurplus=pcity.food_surplus;
//    shieldprod=pcity.shield_prod + pcity.shield_waste;
//    shieldsurplus=pcity.shield_surplus;
//    tradeprod=pcity.trade_prod+pcity.corruption;
//    tradesurplus=pcity.trade_prod;
//  }
//
//  retbuf = String.format
//	  ("Food:  %3d (%+-4d)\n" +
//	    "Prod:  %3d (%+-4d)\n" +
//	    "Trade: %3d (%+-4d)"),
//	  foodprod, foodsurplus,
//	  shieldprod, shieldsurplus,
//	  tradeprod, tradesurplus);
//}
//
///****************************************************************
//...
//*****************************************************************/
//static void get_contents_of_output(city_dialog pdialog,
//				   char *retbuf, int n)
//{
//  city pcity;
//  int goldtotal=0;
//  int goldsurplus=0;
//  int luxtotal=0;
//  int scitotal=0;
//
//  if (pdialog) {
//    pcity=pdialog.pcity;
//    goldtotal=pcity.tax_total;
//    goldsurplus=city_gold_surplus(pcity, pcity.tax_total);
//    luxtotal=pcity.luxury_total;
//    scitotal=pcity.science_total;
//  }
//
//  retbuf = String.format 
//	  ("Gold:  %3d (%+-4d)\n" +
//	    "Lux:   %3d\n" +
//	    "Sci:   %3d"),
//	  goldtotal, goldsurplus,
//	  luxtotal,
//	  scitotal);
//}
//
///****************************************************************
//...
//*****************************************************************/
//static void get_contents_of_progress(city_dialog pdialog,
//				     char *retbuf, int n)
//{
//  get_city_dialog_production(pdialog ? pdialog.pcity : null, retbuf, n);
//}
//
///****************************************************************
//...
//*****************************************************************/
//static void get_contents_of_worklist(city_dialog pdialog,
//				     char *retbuf, int n)
//{
//  city pcity = pdialog ? pdialog.pcity : null;
//
//  if (pcity && worklist_is_empty(&pcity.worklist)) {
//    mystrlcpy(retbuf, "(is empty)", n);
//  } else {
//    mystrlcpy(retbuf, "(in prog.)", n);
//  }
//}
//
///****************************************************************
//...
//*****************************************************************/
//city_dialog get_city_dialog(city pcity)
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
///****************************************************************
//...
//*****************************************************************/
//boolean city_dialog_is_open(city pcity)
//{
//  return get_city_dialog(pcity) != null;
//}
//
///****************************************************************
//...
//*****************************************************************/
//void refresh_city_dialog(city pcity)
//{
//  city_dialog pdialog;
//  
//  if((pdialog=get_city_dialog(pcity))) {
//    struct canvas store = {XtWindow(pdialog.map_canvas)};
//
//    city_dialog_update_improvement_list(pdialog);
//    city_dialog_update_title(pdialog);
//    city_dialog_update_supported_units(pdialog, 0);
//    city_dialog_update_present_units(pdialog, 0);
//    city_dialog_update_citizens(pdialog);
//    city_dialog_redraw_map(pdialog.pcity, &store);
//    city_dialog_update_production(pdialog);
//    city_dialog_update_output(pdialog);
//    city_dialog_update_building(pdialog);
//    city_dialog_update_storage(pdialog);
//    city_dialog_update_pollution(pdialog);
//
//    XtSetSensitive(pdialog.trade_command,
//    		   city_num_trade_routes(pcity)?true:false);
//    XtSetSensitive(pdialog.activate_command,
//		   pcity.tile.units.foo_list_size()
//		   ?true:false);
//    XtSetSensitive(pdialog.show_units_command,
//                   pcity.tile.units.foo_list_size()
//		   ?true:false);
//    XtSetSensitive(pdialog.cma_command, true);
//    XtSetSensitive(pdialog.cityopt_command, true);
//  }
//  if(pcity.owner == Game.game.player_idx)  {
//    city_report_dialog_update_city(pcity);
//    economy_report_dialog_update();
//  } else {
//    if(pdialog)  {
//      /* Set the buttons we do not want live while a Diplomat investigates */
//      XtSetSensitive(pdialog.buy_command, false);
//      XtSetSensitive(pdialog.change_command, false);
//      XtSetSensitive(pdialog.worklist_command, false);
//      XtSetSensitive(pdialog.sell_command, false);
//      XtSetSensitive(pdialog.rename_command, false);
//      XtSetSensitive(pdialog.activate_command, false);
//      XtSetSensitive(pdialog.show_units_command, false);
//      XtSetSensitive(pdialog.cma_command, false);
//      XtSetSensitive(pdialog.cityopt_command, false);
//    }
//  }
//}
//
///****************************************************************
//...
//*****************************************************************/
//void refresh_unit_city_dialogs(unit punit)
//{
//  city pcity_sup, *pcity_pre;
//  city_dialog pdialog;
//
//  pcity_sup=Player_P.player_find_city_by_id(Game.game.player_ptr, punit.homecity);
//  pcity_pre=Map.map_get_city(punit.tile);
//  
//  if(pcity_sup && (pdialog=get_city_dialog(pcity_sup)))
//    city_dialog_update_supported_units(pdialog, 0);
//  
//  if(pcity_pre && (pdialog=get_city_dialog(pcity_pre)))
//    city_dialog_update_present_units(pdialog, 0);
//}
//
///****************************************************************
//popup the dialog 10% inside the main-window 
//*****************************************************************/
//void popup_city_dialog(city pcity, boolean make_modal)
//{
//  city_dialog pdialog;
//  
//  if(!(pdialog=get_city_dialog(pcity)))
//    pdialog=create_city_dialog(pcity, make_modal);
//
//  xaw_set_relative_position(toplevel, pdialog.shell, 10, 10);
//  XtPopup(pdialog.shell, XtGrabNone);
//}
//
///****************************************************************
//popdown the dialog 
//*****************************************************************/
//void popdown_city_dialog(city pcity)
//{
//  city_dialog pdialog;
//  
//  if((pdialog=get_city_dialog(pcity)))
//    close_city_dialog(pdialog);
//}
//
///****************************************************************
//popdown all dialogs
//*****************************************************************/
//void popdown_all_city_dialogs()
//{
//  if(!dialog_list_has_been_initialised) {
//    return;
//  }
//  while (dialog_list.foo_list_size() > 0) {
//    close_city_dialog(dialog_list_get(&dialog_list, 0));
//  }
//  popdown_cityopt_dialog();
//  popdown_cma_dialog();
//}
//
//
///****************************************************************
//...
//*****************************************************************/
//static void city_map_canvas_expose(Widget w, XEvent *event, Region exposed, 
//				   void *client_data)
//{
//  city_dialog pdialog = client_data;
//  struct canvas store = {XtWindow(pdialog.map_canvas)};
//  
//  city_dialog_redraw_map(pdialog.pcity, &store);
//}
//
//
///****************************************************************
//...
//*****************************************************************/
//
//public static final int LAYOUT_DEBUG = 0;
//
//city_dialog create_city_dialog(city pcity, boolean make_modal)
//{
//  char *dummy_improvement_list[]={ 
//    "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX",
//    "2",
//    "3",
//    "4",
//    "5",
//    "6",
//    "7",
//    "8",
//    "9",
//    0
//  };
//  static Pixmap icon_pixmap = 0;
//
//  int i, itemWidth;
//  city_dialog pdialog;
//  char lblbuf[512];
//  Widget first_citizen, first_support, first_present;
//  XtWidgetGeometry geom;
//  Dimension widthTotal;
//  Dimension widthCitizen, borderCitizen, internalCitizen, spaceCitizen;
//  Dimension widthUnit, borderUnit, internalUnit, spaceUnit;
//  Dimension widthNext, borderNext, internalNext, spaceNext;
//  Dimension widthPrev, borderPrev, internalPrev, spacePrev;
//  Widget relative;
//  struct citizen_type c = {.type = CITIZEN_SPECIALIST,
//			   .spec_type = specialist_type.SP_TAXMAN};
//
//  if (NORMAL_TILE_HEIGHT<45) dummy_improvement_list[5]=0;
//
//  if (concise_city_production) {
//    dummy_improvement_list[0] = "XXXXXXXXXXXXXXXXXXXXXXXXXXXX";
//  }
//
//  pdialog=fc_malloc(sizeof(struct city_dialog));
//  pdialog.pcity=pcity;
//  pdialog.support_unit_base=0;
//  pdialog.present_unit_base=0;
//  pdialog.worklist_shell = null;
//
//  if (!icon_pixmap) {
//    icon_pixmap =
//	XCreateBitmapFromData(display,
//			      RootWindowOfScreen(XtScreen(toplevel)),
//			      cityicon_bits,
//			      cityicon_width, cityicon_height);
//  }
//
//
//  pdialog.shell=
//    XtVaCreatePopupShell(pcity.name,
//			 make_modal ? transientShellWidgetClass :
//			 topLevelShellWidgetClass,
//			 toplevel, 
//			 XtNallowShellResize, true, 
//			 null);
//
//  pdialog.main_form=
//    XtVaCreateManagedWidget("citymainform", 
//			    formWidgetClass, 
//			    pdialog.shell, 
//			    null);
//
//  pdialog.cityname_label=
//    XtVaCreateManagedWidget("citynamelabel", 
//			    labelWidgetClass,
//			    pdialog.main_form,
//			    null);
//
//
//  first_citizen=
//    XtVaCreateManagedWidget("citizenlabels",
//			    commandWidgetClass,
//			    pdialog.main_form,
//			    XtNfromVert, 
//			    pdialog.cityname_label,
//			    XtNbitmap,
//			    get_citizen_pixmap(c, 0, pcity),
//			    null);
//
//
//  pdialog.sub_form=
//    XtVaCreateManagedWidget("citysubform", 
//			    formWidgetClass, 
//			    pdialog.main_form, 
//			    XtNfromVert, 
//			    (XtArgVal)first_citizen,
//			    null);
//
//
//  pdialog.left_form=
//    XtVaCreateManagedWidget("cityleftform", 
//			    formWidgetClass, 
//			    pdialog.sub_form, 
//			    null);
//
//  get_contents_of_production(null, lblbuf, sizeof(lblbuf));
//  pdialog.production_label=
//    XtVaCreateManagedWidget("cityprodlabel", 
//			    labelWidgetClass,
//			    pdialog.left_form,
//			    XtNlabel, lblbuf,
//			    null);
//
//  get_contents_of_output(null, lblbuf, sizeof(lblbuf));
//  pdialog.output_label=
//    XtVaCreateManagedWidget("cityoutputlabel", 
//			    labelWidgetClass,
//			    pdialog.left_form,
//			    XtNlabel, lblbuf,
//			    XtNfromVert, 
//			    (XtArgVal)pdialog.production_label,
//			    null);
//
//  get_contents_of_storage(null, lblbuf, sizeof(lblbuf));
//  pdialog.storage_label=
//    XtVaCreateManagedWidget("citystoragelabel", 
//			    labelWidgetClass,
//			    pdialog.left_form,
//			    XtNlabel, lblbuf,
//			    XtNfromVert, 
//			    (XtArgVal)pdialog.output_label,
//			    null);
//
//  get_contents_of_pollution(null, lblbuf, sizeof(lblbuf));
//  pdialog.pollution_label=
//    XtVaCreateManagedWidget("citypollutionlabel", 
//			    labelWidgetClass,
//			    pdialog.left_form,
//			    XtNlabel, lblbuf,
//			    XtNfromVert, 
//			    (XtArgVal)pdialog.storage_label,
//			    null);
//
//
//  pdialog.map_canvas=
//    XtVaCreateManagedWidget("citymapcanvas", 
//			    xfwfcanvasWidgetClass,
//			    pdialog.sub_form,
//			    "exposeProc", (XtArgVal)city_map_canvas_expose,
//			    "exposeProcData", (XtArgVal)pdialog,
//			    XtNfromHoriz, (XtArgVal)pdialog.left_form,
//			    XtNwidth, get_citydlg_canvas_width(),
//			    XtNheight, get_citydlg_canvas_height(),
//			    null);
//
//
//  pdialog.building_label=
//    XtVaCreateManagedWidget("citybuildinglabel",
//			    labelWidgetClass,
//			    pdialog.sub_form,
//			    XtNfromHoriz, 
//			    (XtArgVal)pdialog.map_canvas,
//			    XtNlabel,
//			    concise_city_production
//				? "XXXXXXXXXXXXXXXXXXXXXXXXXXXX"
//				: "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX",
//			    null);
//
//  get_contents_of_progress(null, lblbuf, sizeof(lblbuf));
//  pdialog.progress_label=
//    XtVaCreateManagedWidget("cityprogresslabel",
//			    labelWidgetClass,
//			    pdialog.sub_form,
//			    XtNfromHoriz, 
//			    (XtArgVal)pdialog.map_canvas,
//			    XtNfromVert, 
//			    pdialog.building_label,
//			    XtNlabel, lblbuf,
//			    null);
//
//  pdialog.buy_command=
//    I_L(XtVaCreateManagedWidget("citybuycommand", 
//				commandWidgetClass,
//				pdialog.sub_form,
//				XtNfromVert, 
//				pdialog.building_label,
//				XtNfromHoriz, 
//				pdialog.progress_label,
//				null));
//
//  pdialog.change_command=
//    I_L(XtVaCreateManagedWidget("citychangecommand", 
//			    commandWidgetClass,
//			    pdialog.sub_form,
//			    XtNfromVert, 
//			    pdialog.building_label,
//			    XtNfromHoriz, 
//			    pdialog.buy_command,
//			    null));
// 
//  pdialog.improvement_viewport=
//    XtVaCreateManagedWidget("cityimprovview", 
//			    viewportWidgetClass,
//			    pdialog.sub_form,
//			    XtNfromHoriz, 
//			    (XtArgVal)pdialog.map_canvas,
//			    XtNfromVert, 
//			    pdialog.change_command,
//			    null);
//
//  pdialog.improvement_list=
//    XtVaCreateManagedWidget("cityimprovlist", 
//			    listWidgetClass,
//			    pdialog.improvement_viewport,
//			    XtNforceColumns, 1,
//			    XtNdefaultColumns,1, 
//			    XtNlist,
//			      (XtArgVal)dummy_improvement_list,
//			    XtNverticalList, false,
//			    null);
//
//  pdialog.sell_command=
//    I_L(XtVaCreateManagedWidget("citysellcommand", 
//			    commandWidgetClass,
//			    pdialog.sub_form,
//			    XtNfromVert, 
//			    pdialog.improvement_viewport,
//			    XtNfromHoriz, 
//			    (XtArgVal)pdialog.map_canvas,
//			    null));
//
//  pdialog.worklist_command=
//    I_L(XtVaCreateManagedWidget("cityworklistcommand", 
//			    commandWidgetClass,
//			    pdialog.sub_form,
//			    XtNfromVert, 
//			    pdialog.improvement_viewport,
//			    XtNfromHoriz, 
//			    pdialog.sell_command,
//			    null));
//
//  get_contents_of_worklist(null, lblbuf, sizeof(lblbuf));
//  pdialog.worklist_label=
//    XtVaCreateManagedWidget("cityworklistlabel",
//			    labelWidgetClass,
//			    pdialog.sub_form,
//			    XtNfromVert,
//			    pdialog.improvement_viewport,
//			    XtNfromHoriz,
//			    pdialog.worklist_command,
//			    XtNlabel, lblbuf,
//			    null);
//
//
//  pdialog.support_unit_label=
//    I_L(XtVaCreateManagedWidget("supportunitlabel",
//			    labelWidgetClass,
//			    pdialog.main_form,
//			    XtNfromVert, 
//			    pdialog.sub_form,
//			    null));
//
//  first_support=
//    XtVaCreateManagedWidget("supportunitcanvas",
//			    pixcommWidgetClass,
//			    pdialog.main_form,
//			    XtNfromVert, pdialog.support_unit_label,
//			    XtNwidth, UNIT_TILE_WIDTH,
//			    XtNheight, 3 * NORMAL_TILE_HEIGHT / 2,
//			    null);
//
//  pdialog.present_unit_label=
//    I_L(XtVaCreateManagedWidget("presentunitlabel",
//			    labelWidgetClass,
//			    pdialog.main_form,
//			    XtNfromVert, 
//			    first_support,
//			    null));
//
//  first_present=
//    XtVaCreateManagedWidget("presentunitcanvas",
//    			    pixcommWidgetClass,
//			    pdialog.main_form,
//			    XtNfromVert, pdialog.present_unit_label,
//			    XtNwidth, UNIT_TILE_WIDTH,
//			    XtNheight, UNIT_TILE_HEIGHT,
//			    null);
//
//
//  pdialog.support_unit_next_command=
//    XtVaCreateManagedWidget("supportunitnextcommand",
//			    commandWidgetClass,
//			    pdialog.main_form,
//			    null);
//  pdialog.support_unit_prev_command=
//    XtVaCreateManagedWidget("supportunitprevcommand",
//			    commandWidgetClass,
//			    pdialog.main_form,
//			    null);
//
//  pdialog.present_unit_next_command=
//    XtVaCreateManagedWidget("presentunitnextcommand",
//			    commandWidgetClass,
//			    pdialog.main_form,
//			    null);
//  pdialog.present_unit_prev_command=
//    XtVaCreateManagedWidget("presentunitprevcommand",
//			    commandWidgetClass,
//			    pdialog.main_form,
//			    null);
//
//
//  pdialog.close_command=
//    I_L(XtVaCreateManagedWidget("cityclosecommand", 
//			    commandWidgetClass,
//			    pdialog.main_form,
//			    XtNfromVert, first_present,
//			    null));
//
//  pdialog.rename_command=
//    I_L(XtVaCreateManagedWidget("cityrenamecommand", 
//			    commandWidgetClass,
//			    pdialog.main_form,
//			    XtNfromVert, first_present,
//			    XtNfromHoriz, pdialog.close_command,
//			    null));
//
//  pdialog.trade_command=
//    I_L(XtVaCreateManagedWidget("citytradecommand", 
//			    commandWidgetClass,
//			    pdialog.main_form,
//			    XtNfromVert, first_present,
//			    XtNfromHoriz, pdialog.rename_command,
//			    null));
//
//  pdialog.activate_command=
//    I_L(XtVaCreateManagedWidget("cityactivatecommand",
//    			    commandWidgetClass,
//			    pdialog.main_form,
//			    XtNfromVert, first_present,
//			    XtNfromHoriz, pdialog.trade_command,
//			    null));
//
//  pdialog.show_units_command=
//    I_L(XtVaCreateManagedWidget("cityshowunitscommand",
//			    commandWidgetClass,
//			    pdialog.main_form,
//			    XtNfromVert, first_present,
//			    XtNfromHoriz, pdialog.activate_command,
//			    null));
//
//  pdialog.cma_command =
//    I_L(XtVaCreateManagedWidget("cmacommand",
//                            commandWidgetClass,
//                            pdialog.main_form,
//                            XtNfromVert, first_present,
//                            XtNfromHoriz, pdialog.show_units_command,
//                            null));
//
//  pdialog.cityopt_command=
//    I_L(XtVaCreateManagedWidget("cityoptionscommand",
//			    commandWidgetClass,
//			    pdialog.main_form,
//			    XtNfromVert, first_present,
//			    XtNfromHoriz, pdialog.cma_command,
//			    null));
//
//
//  XtRealizeWidget(pdialog.shell);
//  XtQueryGeometry (pdialog.sub_form, null, &geom);
//  widthTotal=geom.width;
//  if (widthTotal>0) {
//    XtQueryGeometry (first_citizen, null, &geom);
//    widthCitizen=geom.width;
//    borderCitizen=geom.border_width;
//    XtVaGetValues(first_citizen,
//		  XtNinternalWidth, &internalCitizen,
//		  XtNhorizDistance, &spaceCitizen,
//		  null);
//    XtQueryGeometry (first_support, null, &geom);
//    widthUnit=geom.width;
//    borderUnit=geom.border_width;
//    XtVaGetValues(first_support,
//		  XtNinternalWidth, &internalUnit,
//		  XtNhorizDistance, &spaceUnit,
//		  null);
//    XtQueryGeometry (pdialog.support_unit_next_command, null, &geom);
//    widthNext=geom.width;
//    borderNext=geom.border_width;
//    XtVaGetValues(pdialog.support_unit_next_command,
//		  XtNinternalWidth, &internalNext,
//		  XtNhorizDistance, &spaceNext,
//		  null);
//    XtQueryGeometry (pdialog.support_unit_prev_command, null, &geom);
//    widthPrev=geom.width;
//    borderPrev=geom.border_width;
//    XtVaGetValues(pdialog.support_unit_prev_command,
//		  XtNinternalWidth, &internalPrev,
//		  XtNhorizDistance, &spacePrev,
//		  null);
//#if LAYOUT_DEBUG >= 3
//    printf
//    (
//     "T: w: %d\n" +
//     "C: wbis: %d %d %d %d\n" +
//     "U: wbis: %d %d %d %d\n" +
//     "N: wbis: %d %d %d %d\n" +
//     "P: wbis: %d %d %d %d\n"
//     ,
//     widthTotal,
//     widthCitizen, borderCitizen, internalCitizen, spaceCitizen,
//     widthUnit, borderUnit, internalUnit, spaceUnit,
//     widthNext, borderNext, internalNext, spaceNext,
//     widthPrev, borderPrev, internalPrev, spacePrev
//    );
//#endif
//    itemWidth=widthCitizen+2*borderCitizen+2*internalCitizen+spaceCitizen;
//    if (itemWidth>0) {
//      pdialog.num_citizens_shown=widthTotal/itemWidth;
//      if (pdialog.num_citizens_shown<MIN_NUM_CITIZENS)
//	pdialog.num_citizens_shown=MIN_NUM_CITIZENS;
//      else if (pdialog.num_citizens_shown>MAX_NUM_CITIZENS)
//	pdialog.num_citizens_shown=MAX_NUM_CITIZENS;
//    } else {
//      pdialog.num_citizens_shown=MIN_NUM_CITIZENS;
//    }
//#if LAYOUT_DEBUG >= 2
//    printf
//    (
//     "C: wT iW nC: %d %d %d\n"
//     ,
//     widthTotal,
//     itemWidth,
//     pdialog.num_citizens_shown
//    );
//#endif
//    if (widthNext<widthPrev) widthNext=widthPrev;
//    if (borderNext<borderPrev) borderNext=borderPrev;
//    if (internalNext<internalPrev) internalNext=internalPrev;
//    if (spaceNext<spacePrev) spaceNext=spacePrev;
//    widthTotal-=(widthNext+2*borderNext+2*internalNext+spaceNext);
//    itemWidth=widthUnit+2*borderUnit+2*internalUnit+spaceUnit;
//    if (itemWidth>0) {
//      pdialog.num_units_shown=widthTotal/itemWidth;
//      if (pdialog.num_units_shown<MIN_NUM_UNITS)
//	pdialog.num_units_shown=MIN_NUM_UNITS;
//      else if (pdialog.num_units_shown>MAX_NUM_UNITS)
//	pdialog.num_units_shown=MAX_NUM_UNITS;
//    } else {
//      pdialog.num_units_shown=MIN_NUM_UNITS;
//    }
//#if LAYOUT_DEBUG >= 2
//    printf
//    (
//     "U: wT iW nU: %d %d %d\n"
//     ,
//     widthTotal,
//     itemWidth,
//     pdialog.num_units_shown
//    );
//#endif
//  } else {
//    pdialog.num_citizens_shown=DEFAULT_NUM_CITIZENS;
//    pdialog.num_units_shown=DEFAULT_NUM_UNITS;
//    if (NORMAL_TILE_HEIGHT<45) {
//      pdialog.num_citizens_shown-=5;
//      pdialog.num_units_shown+=3;
//    }
//  }
//#if LAYOUT_DEBUG >= 1
//    printf
//    (
//     "nC nU: %d %d\n"
//     ,
//     pdialog.num_citizens_shown, pdialog.num_units_shown
//    );
//#endif
//
//  pdialog.citizen_labels=
//    fc_malloc(pdialog.num_citizens_shown * sizeof(Widget));
//
//  pdialog.support_unit_pixcomms=
//    fc_malloc(pdialog.num_units_shown * sizeof(Widget));
//  pdialog.present_unit_pixcomms=
//    fc_malloc(pdialog.num_units_shown * sizeof(Widget));
//
//
//  pdialog.citizen_labels[0]=first_citizen;
//  for(i=1; i<pdialog.num_citizens_shown; i++)
//    pdialog.citizen_labels[i]=
//    XtVaCreateManagedWidget("citizenlabels",
//			    commandWidgetClass,
//			    pdialog.main_form,
//			    XtNfromVert, pdialog.cityname_label,
//			    XtNfromHoriz, 
//			      (XtArgVal)pdialog.citizen_labels[i-1],
//			    XtNbitmap,
//			    get_citizen_pixmap(c, 0, pcity),
//			    null);
//
//
//  pdialog.support_unit_pixcomms[0]=first_support;
//  for(i=1; i<pdialog.num_units_shown; i++) {
//    pdialog.support_unit_pixcomms[i]=
//      XtVaCreateManagedWidget("supportunitcanvas",
//			      pixcommWidgetClass,
//			      pdialog.main_form,
//			      XtNfromVert, pdialog.support_unit_label,
//			      XtNfromHoriz,
//			        (XtArgVal)pdialog.support_unit_pixcomms[i-1],
//			      XtNwidth, UNIT_TILE_WIDTH,
//			      XtNheight, 3 * NORMAL_TILE_HEIGHT / 2,
//			      null);
//  }
//
//  relative=pdialog.support_unit_pixcomms[pdialog.num_units_shown-1];
//  XtVaSetValues(pdialog.support_unit_next_command,
//		XtNfromVert, pdialog.support_unit_label,
//		XtNfromHoriz, (XtArgVal)relative,
//		null);
//  XtVaSetValues(pdialog.support_unit_prev_command,
//		XtNfromVert, pdialog.support_unit_next_command,
//		XtNfromHoriz, (XtArgVal)relative,
//		null);
//
//  pdialog.present_unit_pixcomms[0]=first_present;
//  for(i=1; i<pdialog.num_units_shown; i++) {
//    pdialog.present_unit_pixcomms[i]=
//      XtVaCreateManagedWidget("presentunitcanvas",
//			      pixcommWidgetClass,
//			      pdialog.main_form,
//			      XtNfromVert, pdialog.present_unit_label,
//			      XtNfromHoriz, 
//			        (XtArgVal)pdialog.support_unit_pixcomms[i-1],
//			      XtNwidth, UNIT_TILE_WIDTH,
//			      XtNheight, UNIT_TILE_HEIGHT,
//			      null);
//  }
//
//  relative=pdialog.present_unit_pixcomms[pdialog.num_units_shown-1];
//  XtVaSetValues(pdialog.present_unit_next_command,
//		XtNfromVert, pdialog.present_unit_label,
//		XtNfromHoriz, (XtArgVal)relative,
//		null);
//  XtVaSetValues(pdialog.present_unit_prev_command,
//		XtNfromVert, pdialog.present_unit_next_command,
//		XtNfromHoriz, (XtArgVal)relative,
//		null);
//
//  
//  XtVaSetValues(pdialog.shell, XtNiconPixmap, icon_pixmap, null);
//
//
//  XtAddCallback(pdialog.sell_command, XtNcallback, sell_callback,
//		(XtPointer)pdialog);
//
//  XtAddCallback(pdialog.buy_command, XtNcallback, buy_callback,
//		(XtPointer)pdialog);
//
//  XtAddCallback(pdialog.change_command, XtNcallback, change_callback,
//		(XtPointer)pdialog);
//
//  XtAddCallback(pdialog.worklist_command, XtNcallback, worklist_callback,
//		(XtPointer)pdialog);
//
//  XtAddCallback(pdialog.close_command, XtNcallback, close_callback,
//		(XtPointer)pdialog);
//
//  XtAddCallback(pdialog.rename_command, XtNcallback, rename_callback,
//		(XtPointer)pdialog);
//
//  XtAddCallback(pdialog.trade_command, XtNcallback, trade_callback,
//		(XtPointer)pdialog);
//
//  XtAddCallback(pdialog.activate_command, XtNcallback, activate_callback,
//		(XtPointer)pdialog);
//
//  XtAddCallback(pdialog.show_units_command, XtNcallback, show_units_callback,
//		(XtPointer)pdialog);
//
//  XtAddCallback(pdialog.support_unit_next_command, XtNcallback,
//		units_next_prev_callback, (XtPointer)pdialog);
//  XtAddCallback(pdialog.support_unit_prev_command, XtNcallback,
//		units_next_prev_callback, (XtPointer)pdialog);
//  XtAddCallback(pdialog.present_unit_next_command, XtNcallback,
//		units_next_prev_callback, (XtPointer)pdialog);
//  XtAddCallback(pdialog.present_unit_prev_command, XtNcallback,
//		units_next_prev_callback, (XtPointer)pdialog);
//
//  XtAddCallback(pdialog.cityopt_command, XtNcallback, cityopt_callback,
//		(XtPointer)pdialog);
//
//  XtAddCallback(pdialog.cma_command, XtNcallback, cma_callback,
//                (XtPointer)pdialog);
//
//  &dialog_list.foo_list_insert(pdialog);
//
//  for(i=0; i<Improvement.B_LAST+1; i++)
//    pdialog.improvlist_names_ptrs[i]=0;
//
//  XtRealizeWidget(pdialog.shell);
//
//  refresh_city_dialog(pdialog.pcity);
//
//  if(make_modal)
//    XtSetSensitive(toplevel, false);
//  
//  pdialog.is_modal=make_modal;
//
//  XSetWMProtocols(display, XtWindow(pdialog.shell), &wm_delete_window, 1);
//  XtOverrideTranslations(pdialog.shell, 
//    XtParseTranslationTable ("<Message>WM_PROTOCOLS: msg-close-city()"));
//
//  XtSetKeyboardFocus(pdialog.shell, pdialog.close_command);
//
//
//  return pdialog;
//}
//
///****************************************************************
//...
//*****************************************************************/
//void activate_callback(Widget w, XtPointer client_data,
//		       XtPointer call_data)
//{
//  city_dialog pdialog = (city_dialog )client_data;
//
//  activate_all_units(pdialog.pcity.tile);
//}
//
//
///****************************************************************
//...
//*****************************************************************/
//void show_units_callback(Widget w, XtPointer client_data,
//                        XtPointer call_data)
//{
//  city_dialog pdialog = (city_dialog )client_data;
//  tile ptile = pdialog.pcity.tile;
//
//  if( ptile.units.foo_list_size() )
//    popup_unit_select_dialog(ptile);
//}
//
//
///****************************************************************
//...
//*****************************************************************/
//void units_next_prev_callback(Widget w, XtPointer client_data,
//			      XtPointer call_data)
//{
//  city_dialog pdialog = (city_dialog )client_data;
//
//  if (w==pdialog.support_unit_next_command) {
//    (pdialog.support_unit_base)++;
//    city_dialog_update_supported_units(pdialog, 0);
//  } else if (w==pdialog.support_unit_prev_command) {
//    (pdialog.support_unit_base)--;
//    city_dialog_update_supported_units(pdialog, 0);
//  } else if (w==pdialog.present_unit_next_command) {
//    (pdialog.present_unit_base)++;
//    city_dialog_update_present_units(pdialog, 0);
//  } else if (w==pdialog.present_unit_prev_command) {
//    (pdialog.present_unit_base)--;
//    city_dialog_update_present_units(pdialog, 0);
//  }
//}
//
//
//#ifdef UNUSED
///****************************************************************
//...
//*****************************************************************/
//static void present_units_ok_callback(Widget w, XtPointer client_data, 
//				      XtPointer call_data)
//{
//  destroy_message_dialog(w);
//}
//#endif
//
//
///****************************************************************
//...
//*****************************************************************/
//static void present_units_activate_callback(Widget w, XtPointer client_data, 
//					    XtPointer call_data)
//{
//  unit punit;
//  city pcity;
//  city_dialog pdialog;
//
//  if((punit=Player_P.player_find_unit_by_id(Game.game.player_ptr, (size_t)client_data)))  {
//    set_unit_focus(punit);
//    if((pcity=Map.map_get_city(punit.tile)))
//      if((pdialog=get_city_dialog(pcity)))
//	city_dialog_update_present_units(pdialog, 0);
//  }
//
//  destroy_message_dialog(w);
//}
//
//
///****************************************************************
//...
//*****************************************************************/
//static void supported_units_activate_callback(Widget w, XtPointer client_data, 
//					      XtPointer call_data)
//{
//  unit punit;
//  city pcity;
//  city_dialog pdialog;
//
//  if((punit=Player_P.player_find_unit_by_id(Game.game.player_ptr, (size_t)client_data)))  {
//    set_unit_focus(punit);
//    if((pcity=Map.map_get_city(punit.tile)))
//      if((pdialog=get_city_dialog(pcity)))
//	city_dialog_update_supported_units(pdialog, 0);
//  }
//
//  destroy_message_dialog(w);
//}
//
//
///****************************************************************
//...
//*****************************************************************/
//static void present_units_activate_close_callback(Widget w,
//						  XtPointer client_data, 
//						  XtPointer call_data)
//{
//  unit punit;
//  city pcity;
//  city_dialog pdialog;
//
//  destroy_message_dialog(w);
//
//  if((punit=Player_P.player_find_unit_by_id(Game.game.player_ptr, (size_t)client_data)))  {
//    set_unit_focus(punit);
//    if((pcity=Map.map_get_city(punit.tile)))
//      if((pdialog=get_city_dialog(pcity)))
//	close_city_dialog(pdialog);
//  }
//}
//
///****************************************************************
//...
//*****************************************************************/
//static void supported_units_activate_close_callback(Widget w,
//						    XtPointer client_data, 
//						    XtPointer call_data)
//{
//  unit punit;
//  city pcity;
//  city_dialog pdialog;
//
//  destroy_message_dialog(w);
//
//  if((punit=Player_P.player_find_unit_by_id(Game.game.player_ptr, (size_t)client_data)))  {
//    set_unit_focus(punit);
//    if((pcity=Player_P.player_find_city_by_id(Game.game.player_ptr, punit.homecity)))
//      if((pdialog=get_city_dialog(pcity)))
//	close_city_dialog(pdialog);
//  }
//}
//
//
///****************************************************************
//...
//*****************************************************************/
//static void present_units_sentry_callback(Widget w, XtPointer client_data, 
//					   XtPointer call_data)
//{
//  unit punit;
//
//  if((punit=Player_P.player_find_unit_by_id(Game.game.player_ptr, (size_t)client_data)))
//    request_unit_sentry(punit);
//
//  destroy_message_dialog(w);
//}
//
//
///****************************************************************
//...
//*****************************************************************/
//static void present_units_fortify_callback(Widget w, XtPointer client_data, 
//					   XtPointer call_data)
//{
//  unit punit;
//
//  if((punit=Player_P.player_find_unit_by_id(Game.game.player_ptr, (size_t)client_data)))
//    request_unit_fortify(punit);
//
//  destroy_message_dialog(w);
//}
//
//
///****************************************************************
//...
//*****************************************************************/
//static void present_units_disband_callback(Widget w, XtPointer client_data, 
//					   XtPointer call_data)
//{
//  unit punit;
//
//  if((punit=Player_P.player_find_unit_by_id(Game.game.player_ptr, (size_t)client_data)))
//    request_unit_disband(punit);
//
//  destroy_message_dialog(w);
//}
//
//
///****************************************************************
//...
//*****************************************************************/
//static void present_units_homecity_callback(Widget w, XtPointer client_data, 
//					    XtPointer call_data)
//{
//  unit punit;
//  
//  if((punit=Player_P.player_find_unit_by_id(Game.game.player_ptr, (size_t)client_data)))
//    request_unit_change_homecity(punit);
//
//  destroy_message_dialog(w);
//}
//
//
///****************************************************************
//...
//*****************************************************************/
//static void present_units_cancel_callback(Widget w, XtPointer client_data, 
//					  XtPointer call_data)
//{
//  destroy_message_dialog(w);
//}
//
//
///****************************************************************
//...
//*****************************************************************/
//void present_units_callback(Widget w, XtPointer client_data, 
//			    XtPointer call_data)
//{
//  unit punit;
//  city pcity;
//  city_dialog pdialog;
//  Widget wd;
//  XEvent *e = (XEvent*)call_data;
//  
//  if((punit=Player_P.player_find_unit_by_id(Game.game.player_ptr, (size_t)client_data)) &&
//     (pcity=Map.map_get_city(punit.tile)) &&
//     (pdialog=get_city_dialog(pcity))) {
//    
//    if(e.type==ButtonRelease && e.xbutton.button==Button2)  {
//      set_unit_focus(punit);
//      close_city_dialog(pdialog);
//      return;
//    }
//    if(e.type==ButtonRelease && e.xbutton.button==Button3)  {
//      set_unit_focus(punit);
//      return;
//    }
//
//    wd=popup_message_dialog(pdialog.shell, 
//			    "presentunitsdialog", 
//			    unit_description(punit),
//			    present_units_activate_callback, punit.id, 1,
//			    present_units_activate_close_callback, punit.id, 1,
//			    present_units_sentry_callback, punit.id, 1,
//			    present_units_fortify_callback, punit.id, 1,
//			    present_units_disband_callback, punit.id, 1,
//			    present_units_homecity_callback, punit.id, 1,
//			    upgrade_callback, punit.id, 1,
//			    present_units_cancel_callback, 0, 0, 
//			    null);
//
//    if (punit.activity == ACTIVITY_SENTRY
//	|| !can_unit_do_activity(punit, ACTIVITY_SENTRY)) {
//      XtSetSensitive(XtNameToWidget(wd, "*button2"), false);
//    }
//    if (punit.activity == ACTIVITY_FORTIFYING
//	|| !can_unit_do_activity(punit, ACTIVITY_FORTIFYING)) {
//      XtSetSensitive(XtNameToWidget(wd, "*button3"), false);
//    }
//    if (unit_flag(punit, F_UNDISBANDABLE)) {
//      XtSetSensitive(XtNameToWidget(wd, "*button4"), false);
//    }
//    if (punit.homecity == pcity.id) {
//      XtSetSensitive(XtNameToWidget(wd, "*button5"), false);
//    }
//    if (can_upgrade_unittype(Game.game.player_ptr,punit.type) == -1) {
//      XtSetSensitive(XtNameToWidget(wd, "*button6"), false);
//    }
//  }
//}
//
///****************************************************************
//...
//*****************************************************************/
//static void rename_city_callback(Widget w, XtPointer client_data, 
//				 XtPointer call_data)
//{
//  city_dialog pdialog = client_data;
//
//  if (pdialog) {
//    city_rename(pdialog.pcity, input_dialog_get_input(w));
//  }
//  input_dialog_destroy(w);
//}
//
//
//
//
//
///****************************************************************
//...
//*****************************************************************/
//void rename_callback(Widget w, XtPointer client_data, XtPointer call_data)
//{
//  city_dialog pdialog;
//
//  pdialog=(city_dialog )client_data;
//  
//  input_dialog_create(pdialog.shell, 
//		      "shellrenamecity", 
//		      "What should we rename the city to?",
//		      pdialog.pcity.name,
//		      rename_city_callback, (XtPointer)pdialog,
//		      rename_city_callback, (XtPointer)0);
//}
//
///****************************************************************
//...
//*****************************************************************/
//static void trade_message_dialog_callback(Widget w, XtPointer client_data, 
//					  XtPointer call_data)
//{
//  destroy_message_dialog(w);
//}
//
///****************************************************************
//...
//*****************************************************************/
//void trade_callback(Widget w, XtPointer client_data, XtPointer call_data)
//{
//  int i;
//  int x=0,total=0;
//  char buf[512], *bptr=buf;
//  int nleft = sizeof(buf);
//  city_dialog pdialog;
//
//  pdialog=(city_dialog )client_data;
//
//  buf = util.my_snprintf(
//	      "These trade routes have been established with %s:\n",
//	      pdialog.pcity.name);
//  bptr = end_of_strn(bptr, &nleft);
//  
//  for (i = 0; i < City_H.NUM_TRADEROUTES; i++)
//    if(pdialog.pcity.trade[i]) {
//      city pcity;
//      x=1;
//      total+=pdialog.pcity.trade_value[i];
//      if((pcity=Game.find_city_by_id(pdialog.pcity.trade[i]))) {
//	bptr = String.format "%32s: %2d Trade/Year\n",
//		    pcity.name, pdialog.pcity.trade_value[i]);
//	bptr = end_of_strn(bptr, &nleft);
//      } else {	
//	bptr = String.format "%32s: %2d Trade/Year\n", "Unknown",
//		    pdialog.pcity.trade_value[i]);
//	bptr = end_of_strn(bptr, &nleft);
//      }
//    }
//  if (!x) {
//    mystrlcpy(bptr, "No trade routes exist.\n", nleft);
//  } else {
//    bptr = String.format "\nTotal trade %d Trade/Year\n", total);
//  }
//  
//  popup_message_dialog(pdialog.shell, 
//		       "citytradedialog", 
//		       buf, 
//		       trade_message_dialog_callback, 0, 0,
//		       null);
//}
//
//
///****************************************************************
//...
//*****************************************************************/
//void city_dialog_update_pollution(city_dialog pdialog)
//{
//  char buf[512];
//
//  get_contents_of_pollution(pdialog, buf, sizeof(buf));
//  xaw_set_label(pdialog.pollution_label, buf);
//}
//
///****************************************************************
//...
//*****************************************************************/
//void city_dialog_update_storage(city_dialog pdialog)
//{
//  char buf[512];
//
//  get_contents_of_storage(pdialog, buf, sizeof(buf));
//  xaw_set_label(pdialog.storage_label, buf);
//}
//
///****************************************************************
//...
//*****************************************************************/
//void city_dialog_update_building(city_dialog pdialog)
//{
//  char buf[32];
//  city pcity=pdialog.pcity;
//
//  XtSetSensitive(pdialog.buy_command, city_can_buy(pcity));
//  XtSetSensitive(pdialog.sell_command, !pcity.did_sell);
//
//  xaw_set_label(pdialog.building_label,
//		pcity.is_building_unit ?
//		  get_unit_type(pcity.currently_building).name :
//		  City.get_impr_name_ex(pcity, pcity.currently_building));
//
//  get_contents_of_progress(pdialog, buf, sizeof(buf));
//  xaw_set_label(pdialog.progress_label, buf);
//
//  get_contents_of_worklist(pdialog, buf, sizeof(buf));
//  xaw_set_label(pdialog.worklist_label, buf);
//}
//
///****************************************************************
//...
//*****************************************************************/
//void city_dialog_update_production(city_dialog pdialog)
//{
//  char buf[512];
//
//  get_contents_of_production(pdialog, buf, sizeof(buf));
//  xaw_set_label(pdialog.production_label, buf);
//}
//
///****************************************************************
//...
//*****************************************************************/
//void city_dialog_update_output(city_dialog pdialog)
//{
//  char buf[512];
//
//  get_contents_of_output(pdialog, buf, sizeof(buf));
//  xaw_set_label(pdialog.output_label, buf);
//}
//
///****************************************************************************
//  Handle the callback when a citizen sprite widget is clicked.
//****************************************************************************/
//static void citizen_callback(Widget w, XtPointer client_data,
//			     XtPointer call_data)
//{
//  city_dialog pdialog = client_data;
//  int i;
//
//  /* HACK: figure out which figure was clicked. */
//  for (i = 0; i < pdialog.pcity.size; i++) {
//    if (pdialog.citizen_labels[i] == w) {
//      break;
//    }
//  }
//  assert(i < pdialog.pcity.size);
//
//  city_rotate_specialist(pdialog.pcity, i);
//}
//
///****************************************************************
//...
//*****************************************************************/
//void city_dialog_update_citizens(city_dialog pdialog)
//{
//  int i;
//  city pcity=pdialog.pcity;
//  struct citizen_type citizens[MAX_CITY_SIZE];
//
//  get_city_citizen_types(pcity, 4, citizens);
//
//  for (i = 0; i < pcity.size && i < pdialog.num_citizens_shown; i++) {
//    xaw_set_bitmap(pdialog.citizen_labels[i],
//		   get_citizen_pixmap(citizens[i], i, pcity));
//
//    /* HACK: set sensitivity/callbacks on the widget */
//    XtRemoveAllCallbacks(pdialog.citizen_labels[i], XtNcallback);
//    XtAddCallback(pdialog.citizen_labels[i], XtNcallback,
//		  citizen_callback, (XtPointer)pdialog);
//    XtSetSensitive(pdialog.citizen_labels[i], true);
//  }
//
//  if (i >= pdialog.num_citizens_shown && i < pcity.size) {
//    i = pdialog.num_citizens_shown - 1;
//    xaw_set_bitmap(pdialog.citizen_labels[i], sprites.right_arrow.pixmap);
//    XtSetSensitive(pdialog.citizen_labels[i], false);
//    XtRemoveAllCallbacks(pdialog.citizen_labels[i], XtNcallback);
//    return;
//  }
//
//  for(; i<pdialog.num_citizens_shown; i++) {
//    xaw_set_bitmap(pdialog.citizen_labels[i], None);
//    XtSetSensitive(pdialog.citizen_labels[i], false);
//    XtRemoveAllCallbacks(pdialog.citizen_labels[i], XtNcallback);
//  }
//}
//
///****************************************************************
//...
//*****************************************************************/
//static void support_units_callback(Widget w, XtPointer client_data, 
//				   XtPointer call_data)
//{
//  unit punit;
//  city pcity;
//  city_dialog pdialog;
//  XEvent *e = (XEvent*)call_data;
//  Widget wd;
//
//  if((punit=Player_P.player_find_unit_by_id(Game.game.player_ptr, (size_t)client_data)))
//    if((pcity=Game.find_city_by_id(punit.homecity)))
//      if((pdialog=get_city_dialog(pcity)))  {
//	if(e.type==ButtonRelease && e.xbutton.button==Button2)  {
//	  set_unit_focus(punit);
//	  close_city_dialog(pdialog);
//	  return;
//	}
//	if(e.type==ButtonRelease && e.xbutton.button==Button3)  {
//	  set_unit_focus(punit);
//	  return;
//	}
//	wd = popup_message_dialog(pdialog.shell,
//			     "supportunitsdialog", 
//			     unit_description(punit),
//			     supported_units_activate_callback, punit.id, 1,
//			     supported_units_activate_close_callback,
//			                                     punit.id, 1,
//			     present_units_disband_callback, punit.id, 1,
//			     present_units_cancel_callback, 0, 0,
//			     null);
//        if (unit_flag(punit, F_UNDISBANDABLE)) {
//          XtSetSensitive(XtNameToWidget(wd, "*button3"), false);
//        }
//      }
//}
//
//
///****************************************************************
//...
//*****************************************************************/
//static int units_scroll_maintenance(int nunits, int nshow, int *base,
//				    Widget next, Widget prev)
//{
//  int adj_base=false;
//  int nextra;
//
//  nextra=nunits-nshow;
//  if (nextra<0) nextra=0;
//
//  if (*base>nextra) {
//    *base=nextra;
//    adj_base=true;
//  }
//  if (*base<0) {
//    *base=0;
//    adj_base=true;
//  }
//
//  if (nextra<=0) {
//    XtUnmapWidget(next);
//    XtUnmapWidget(prev);
//  } else {
//    XtMapWidget(next);
//    XtMapWidget(prev);
//    XtSetSensitive(next, *base<nextra);
//    XtSetSensitive(prev, *base>0);
//  }
//
//  return (adj_base);
//}
//
///****************************************************************
//...
//*****************************************************************/
//void city_dialog_update_supported_units(city_dialog pdialog, 
//					int unitid)
//{
//  unit_list plist;
//  int i, j, adj_base;
//  Widget pixcomm;
//
//  if(pdialog.pcity.owner != Game.game.player_idx) {
//    plist = &(pdialog.pcity.info_units_supported);
//  } else {
//    plist = &(pdialog.pcity.units_supported);
//  }
//
//  adj_base=
//    units_scroll_maintenance
//    (
//     plist.list.nelements,
//     pdialog.num_units_shown,
//     &(pdialog.support_unit_base),
//     pdialog.support_unit_next_command,
//     pdialog.support_unit_prev_command
//    );
//
//  i = 0; /* number of displayed units */
//  j = 0; /* index into list */
//  unit_list_iterate(*plist, punit) {
//    struct canvas store;
//
//    if (j++ < pdialog.support_unit_base) {
//      continue;
//    }
//    if (i >= pdialog.num_units_shown) {
//      break;
//    }
//
//    pixcomm=pdialog.support_unit_pixcomms[i];
//    store.pixmap = XawPixcommPixmap(pixcomm);
//
//    if(!adj_base && unitid && punit.id!=unitid)
//      continue;
//
//    XawPixcommClear(pixcomm); /* STG */
//    put_unit(punit, &store, 0, 0);
//    put_unit_pixmap_city_overlays(punit,
//				  XawPixcommPixmap(pixcomm));
//
//    xaw_expose_now(pixcomm);
//
//    XtRemoveAllCallbacks(pixcomm, XtNcallback);
//    XtAddCallback(pixcomm, XtNcallback,
//		  support_units_callback, INT_TO_XTPOINTER(punit.id));
//    XtSetSensitive(pixcomm, true);
//    i++;
//  } }
//
//  /* Disable any empty slots */
//  for(; i<pdialog.num_units_shown; i++) {
//    XawPixcommClear(pdialog.support_unit_pixcomms[i]);
//    XtSetSensitive(pdialog.support_unit_pixcomms[i], false);
//  }
//}
//
///****************************************************************
//...
//*****************************************************************/
//void city_dialog_update_present_units(city_dialog pdialog, int unitid)
//{
//  unit_list plist;
//  int i, j, adj_base;
//  Widget pixcomm;
//
//  if(pdialog.pcity.owner != Game.game.player_idx) {
//    plist = &(pdialog.pcity.info_units_present);
//  } else {
//    plist = &pdialog.pcity.tile.units;
//  }
//
//  adj_base=
//    units_scroll_maintenance
//    (
//     plist.list.nelements,
//     pdialog.num_units_shown,
//     &(pdialog.present_unit_base),
//     pdialog.present_unit_next_command,
//     pdialog.present_unit_prev_command
//    );
//
//  i = 0; /* number of displayed units */
//  j = 0; /* index into list */
//  unit_list_iterate(*plist, punit) {
//    struct canvas store;
//
//    if (j++ < pdialog.present_unit_base) {
//      continue;
//    }
//    if (i >= pdialog.num_units_shown) {
//      break;
//    }
//
//    pixcomm=pdialog.present_unit_pixcomms[i];
//    store.pixmap = XawPixcommPixmap(pixcomm);
//
//    if(!adj_base && unitid && punit.id!=unitid)
//      continue;
//
//    XawPixcommClear(pixcomm); /* STG */
//    put_unit(punit, &store, 0, 0);
//
//    xaw_expose_now(pixcomm);
//
//    XtRemoveAllCallbacks(pixcomm, XtNcallback);
//    XtAddCallback(pixcomm, XtNcallback, 
//		  present_units_callback, INT_TO_XTPOINTER(punit.id));
//    XtSetSensitive(pixcomm, true);
//    i++;
//  } }
//
//  for(; i<pdialog.num_units_shown; i++) {
//    XawPixcommClear(pdialog.present_unit_pixcomms[i]);
//    XtSetSensitive(pdialog.present_unit_pixcomms[i], false);
//  }
//}
//
//
///****************************************************************
//...
//*****************************************************************/
//void city_dialog_update_title(city_dialog pdialog)
//{
//  char buf[512];
//  String now;
//  
//  buf = util.my_snprintf( "%s - %s citizens  CMA: %s",
//	      pdialog.pcity.name,
//	      population_to_text(city_population(pdialog.pcity)),
//                   cmafec_get_short_descr_of_city(pdialog.pcity));
//
//  XtVaGetValues(pdialog.cityname_label, XtNlabel, &now, null);
//  if(strcmp(now, buf) != 0) {
//    XtVaSetValues(pdialog.cityname_label, XtNlabel, (XtArgVal)buf, null);
//    xaw_horiz_center(pdialog.cityname_label);
//    XtVaSetValues(pdialog.shell, XtNtitle, (XtArgVal)pdialog.pcity.name, null);
//  }
//}
//
///****************************************************************
//...
//*****************************************************************/
//void city_dialog_update_improvement_list(city_dialog pdialog)
//{
//  int n = 0, flag = 0;
//
//	for (int i = 0; i < Game.game.num_impr_types; i++) {
//		if((pdialog.pcity).improvements[i] == Improvement.I_NONE) {
//			continue;
//		}
//    if (!pdialog.improvlist_names_ptrs[n] ||
//	strcmp(pdialog.improvlist_names_ptrs[n],
//	       City.get_impr_name_ex(pdialog.pcity, i)) != 0)
//      flag = 1;
//    sz_strlcpy(pdialog.improvlist_names[n],
//	       City.get_impr_name_ex(pdialog.pcity, i));
//    pdialog.improvlist_names_ptrs[n] = pdialog.improvlist_names[n];
//    n++;
//  } ;
//  
//  if(pdialog.improvlist_names_ptrs[n]!=0) {
//    pdialog.improvlist_names_ptrs[n]=0;
//    flag=1;
//  }
//  
//  if(flag || n==0) {
//    XawListChange(pdialog.improvement_list, pdialog.improvlist_names_ptrs, 
//		  n, 0, false);  
//    /* force refresh of viewport so the scrollbar is added.
//     * Buggy sun athena requires this */
//    XtVaSetValues(pdialog.improvement_viewport, XtNforceBars, false, null);
//    XtVaSetValues(pdialog.improvement_viewport, XtNforceBars, true, null);
//  }
//}
//
//
///**************************************************************************
//...
//**************************************************************************/
//void citydlg_btn_select_citymap(Widget w, XEvent *event)
//{
//  XButtonEvent *ev=&event.xbutton;
//  city pcity = null;
//
//  for (dialog pdialog : dialog_list.data) {
//    if (pdialog.map_canvas == w) {
//      pcity = pdialog.pcity;
//      break;
//    }
//  } }
//
//  if (pcity) {
//    if (!cma_is_city_under_agent(pcity, null)) {
//      int xtile, ytile;
//
//      if (canvas_to_city_pos(&xtile, &ytile, ev.x, ev.y)) {
//	city_toggle_worker(pcity, xtile, ytile);
//      }
//    }
//  }
//}
//
///****************************************************************
//...
//*****************************************************************/
//static void buy_callback_yes(Widget w, XtPointer client_data,
//			     XtPointer call_data)
//{
//  city_dialog pdialog = client_data;
//
//  city_buy_production(pdialog.pcity);
//
//  destroy_message_dialog(w);
//}
//
//
///****************************************************************
//...
//*****************************************************************/
//static void buy_callback_no(Widget w, XtPointer client_data,
//			    XtPointer call_data)
//{
//  destroy_message_dialog(w);
//}
//
//
///****************************************************************
//...
//*****************************************************************/
//void buy_callback(Widget w, XtPointer client_data, XtPointer call_data)
//{
//  city_dialog pdialog;
//  int value;
//  final String name;
//  char buf[512];
//  
//  pdialog=(city_dialog )client_data;
//
//  if(pdialog.pcity.is_building_unit) {
//    name=get_unit_type(pdialog.pcity.currently_building).name;
//  }
//  else {
//    name=City.get_impr_name_ex(pdialog.pcity, pdialog.pcity.currently_building);
//  }
//  value=City.city_buy_cost(pdialog.pcity);
// 
//  if(Game.game.player_ptr.economic.gold>=value) {
//    buf = util.my_snprintf(
//		"Buy %s for %d gold?\nTreasury contains %d gold.", 
//		name, value, Game.game.player_ptr.economic.gold);
//    popup_message_dialog(pdialog.shell, "buydialog", buf,
//			 buy_callback_yes, pdialog, 0,
//			 buy_callback_no, 0, 0,
//			 null);
//  }
//  else {
//    buf = util.my_snprintf(
//		"%s costs %d gold.\nTreasury contains %d gold.", 
//		name, value, Game.game.player_ptr.economic.gold);
//    popup_message_dialog(pdialog.shell, "buynodialog", buf,
//			 buy_callback_no, 0, 0,
//			 null);
//  }
//  
//}
//
//
///****************************************************************
//...
//*****************************************************************/
//void unitupgrade_callback_yes(Widget w, XtPointer client_data, XtPointer call_data)
//{
//  unit punit;
//
//  if((punit=Player_P.player_find_unit_by_id(Game.game.player_ptr, (size_t)client_data))) {
//    request_unit_upgrade(punit);
//  }
//  destroy_message_dialog(w);
//}
//
//
///****************************************************************
//...
//*****************************************************************/
//void unitupgrade_callback_no(Widget w, XtPointer client_data, XtPointer call_data)
//{
//  destroy_message_dialog(w);
//}
//
//
///****************************************************************
//...
//*****************************************************************/
//void upgrade_callback(Widget w, XtPointer client_data, XtPointer call_data)
//{
//  unit punit = Player_P.player_find_unit_by_id(Game.game.player_ptr,
//					      (size_t)client_data);
//  char buf[512];
//
//  if (!punit) {
//    return;
//  }
//
//  if (get_unit_upgrade_info(buf, sizeof(buf), punit) == UR_OK) {
//    popup_message_dialog(toplevel, "upgradedialog", buf,
//			 unitupgrade_callback_yes,
//			 INT_TO_XTPOINTER(punit.id), 0,
//			 unitupgrade_callback_no, 0, 0, null);
//  } else {
//    popup_message_dialog(toplevel, "upgradenodialog", buf,
//			 unitupgrade_callback_no, 0, 0,
//			 null);
//  }
//
//  destroy_message_dialog(w);
//}
//
//
///****************************************************************
//...
//*****************************************************************/
//static void change_to_callback(Widget w, XtPointer client_data,
//			       XtPointer call_data)
//{
//  city_dialog pdialog;
//  XawListReturnStruct *ret;
//
//  pdialog=(city_dialog )client_data;
//
//  ret=XawListShowCurrent(pdialog.change_list);
//
//  if(ret.list_index!=XAW_LIST_NONE) {
//    boolean is_unit = ret.list_index >= pdialog.change_list_num_improvements;
//    int build_id = pdialog.change_list_ids[ret.list_index];
//
//    city_change_production(pdialog.pcity, is_unit, build_id);
//  }
//  
//  XtDestroyWidget(XtParent(XtParent(w)));
//  XtSetSensitive(pdialog.shell, true);
//}
//
///****************************************************************
//...
//*****************************************************************/
//static void change_no_callback(Widget w, XtPointer client_data,
//			       XtPointer call_data)
//{
//  city_dialog pdialog;
//  
//  pdialog=(city_dialog )client_data;
//  
//  XtDestroyWidget(XtParent(XtParent(w)));
//  XtSetSensitive(pdialog.shell, true);
//}
//
///****************************************************************
//...
//*****************************************************************/
//static void change_help_callback(Widget w, XtPointer client_data,
//				 XtPointer call_data)
//{
//  city_dialog pdialog;
//  XawListReturnStruct *ret;
//
//  pdialog=(city_dialog )client_data;
//
//  ret=XawListShowCurrent(pdialog.change_list);
//  if(ret.list_index!=XAW_LIST_NONE) {
//    int idx = pdialog.change_list_ids[ret.list_index];
//    boolean is_unit = (ret.list_index >= pdialog.change_list_num_improvements);
//
//    if (is_unit) {
//      popup_help_dialog_typed(get_unit_type(idx).name, HELP_UNIT);
//    } else if(Improvement.is_wonder(idx)) {
//      popup_help_dialog_typed(Improvement.get_improvement_name(idx), HELP_WONDER);
//    } else {
//      popup_help_dialog_typed(Improvement.get_improvement_name(idx), HELP_IMPROVEMENT);
//    }
//  }
//  else
//    popup_help_dialog_string(HELP_IMPROVEMENTS_ITEM);
//}
//
//
///****************************************************************
//...
//*****************************************************************/
//void change_callback(Widget w, XtPointer client_data, XtPointer call_data)
//{
//  static char *dummy_change_list[]={ 
//    "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX  888 turns",
//    "2",
//    "3",
//    "4",
//    "5",
//    "6",
//    "7",
//    "8",
//    "9",
//    "0",
//    "1",
//    "2",
//    "3",
//    "4",
//    "5",
//    0
//  };
//
//  Widget cshell, cform, clabel, cview, button_change, button_cancel, button_help;
//  Position x, y;
//  Dimension width, height;
//  city_dialog pdialog;
//  int n;
//  
//  pdialog=(city_dialog )client_data;
//  
//  I_T(cshell=XtCreatePopupShell("changedialog", transientShellWidgetClass,
//				pdialog.shell, null, 0));
//  
//  cform=XtVaCreateManagedWidget("dform", formWidgetClass, cshell, null);
//  
//  I_L(clabel=XtVaCreateManagedWidget("dlabel", labelWidgetClass, cform,
//				     null));
//
//  cview=XtVaCreateManagedWidget("dview", viewportWidgetClass,
//				cform,
//				XtNfromVert, 
//				clabel,
//				null);
//
//  pdialog.change_list=XtVaCreateManagedWidget("dlist", listWidgetClass, 
//					       cview, 
//					       XtNforceColumns, 1,
//					       XtNdefaultColumns,1, 
//					       XtNlist, 
//					       (XtArgVal)dummy_change_list,
//					       XtNverticalList, false,
//					       null);
//
//  
//  button_change = I_L(XtVaCreateManagedWidget("buttonchange",
//					commandWidgetClass,
//					cform,
//					XtNfromVert, 
//					cview,
//					null));
//
//  button_cancel = I_L(XtVaCreateManagedWidget("buttoncancel",
//				    commandWidgetClass,
//				    cform,
//				    XtNfromVert, 
//				    cview,
//				    XtNfromHoriz,
//				    button_change,
//				    null));
//
//  button_help = I_L(XtVaCreateManagedWidget("buttonhelp",
//				    commandWidgetClass,
//				    cform,
//				    XtNfromVert, 
//				    cview,
//				    XtNfromHoriz,
//				    button_cancel,
//				    null));
//
//  XtAddCallback(button_change, XtNcallback, 
//		change_to_callback, (XtPointer)pdialog);
//  XtAddCallback(button_cancel, XtNcallback, 
//		change_no_callback, (XtPointer)pdialog);
//  XtAddCallback(button_help, XtNcallback, 
//		change_help_callback, (XtPointer)pdialog);
//
//  
//
//  XtVaGetValues(pdialog.shell, XtNwidth, &width, XtNheight, &height, null);
//  XtTranslateCoords(pdialog.shell, (Position) width/6, (Position) height/10,
//		    &x, &y);
//  XtVaSetValues(cshell, XtNx, x, XtNy, y, null);
//  
//  XtPopup(cshell, XtGrabNone);
//  
//  XtSetSensitive(pdialog.shell, false);
//
//  n = 0;
//  for (int i = 0; i < Game.game.num_impr_types; i++) {
//    if(City.can_build_improvement(pdialog.pcity, i)) {
//      get_city_dialog_production_full(pdialog.change_list_names[n],
//                                      sizeof(pdialog.change_list_names[n]),
//                                      i, false, pdialog.pcity);
//      pdialog.change_list_names_ptrs[n]=pdialog.change_list_names[n];
//      pdialog.change_list_ids[n++]=i;
//    }
//  } ;
//  
//  pdialog.change_list_num_improvements=n;
//
//
//  unit_type_iterate(i) {
//    if(City.can_build_unit(pdialog.pcity, i)) {
//      get_city_dialog_production_full(pdialog.change_list_names[n],
//                                      sizeof(pdialog.change_list_names[n]),
//                                      i, true, pdialog.pcity);
//      pdialog.change_list_names_ptrs[n]=pdialog.change_list_names[n];
//      pdialog.change_list_ids[n++]=i;
//    }
//  } unit_type_iterate_end;
//  
//  pdialog.change_list_names_ptrs[n]=0;
//
//  XawListChange(pdialog.change_list, pdialog.change_list_names_ptrs, 
//		0, 0, false);
//  /* force refresh of viewport so the scrollbar is added.
//   * Buggy sun athena requires this */
//  XtVaSetValues(cview, XtNforceBars, true, null);
//}
//
//
///****************************************************************
//  Display the city's worklist.
//*****************************************************************/
//void worklist_callback(Widget w, XtPointer client_data, XtPointer call_data)
//{
//  city_dialog pdialog;
//  
//  pdialog = (city_dialog )client_data;
//
//  if (pdialog.worklist_shell) {
//    XtPopup(pdialog.worklist_shell, XtGrabNone);
//  } else {
//    pdialog.worklist_shell = 
//      popup_worklist(&pdialog.pcity.worklist, pdialog.pcity,
//		     pdialog.shell, (void *) pdialog, commit_city_worklist,
//		     cancel_city_worklist);
//  }
//}
//
///****************************************************************
//  Commit the changes to the worklist for the city.
//*****************************************************************/
//void commit_city_worklist(worklist pwl, void *data)
//{
//  city_dialog pdialog = data;
//  int k, id;
//  boolean is_unit;
//
//  /* Update the worklist.  Remember, though -- the current build 
//     target really isn't in the worklist; don't send it to the 
//     server as part of the worklist.  Of course, we have to
//     search through the current worklist to find the first
//     _now_available_ build target (to cope with players who try
//     mean things like adding a Battleship to a city worklist when
//     the player doesn't even yet have the Map Making tech).  */
//
//  for (k = 0; k < MAX_LEN_WORKLIST; k++) {
//    int same_as_current_build;
//    if (! worklist_peek_ith(pwl, &id, &is_unit, k))
//      break;
//    same_as_current_build = id == pdialog.pcity.currently_building
//      && is_unit == pdialog.pcity.is_building_unit;
//
//    /* Very special case: If we are currently building a wonder we
//       allow the finalruction to continue, even if we the wonder is
//       finished elsewhere, ie unbuildable. */
//    if (k == 0 && !is_unit && Improvement.is_wonder(id) && same_as_current_build) {
//      worklist_remove(pwl, k);
//      break;
//    }
//
//    /* If it can be built... */
//    if (( is_unit && City.can_build_unit(pdialog.pcity, id)) ||
//	(!is_unit && City.can_build_improvement(pdialog.pcity, id))) {
//      /* ...but we're not yet building it, then switch. */
//      if (!same_as_current_build) {
//	/* Change the current target */
//	city_change_production(pdialog.pcity, is_unit, id);
//      }
//
//      /* This item is now (and may have always been) the current
//	 build target.  Drop it out of the worklist. */
//      worklist_remove(pwl, k);
//      break;
//    }
//  }
//
//  /* Send the rest of the worklist on its way. */
//  city_set_worklist(pdialog.pcity, pwl);
//
//  pdialog.worklist_shell = null;
//}
//
//void cancel_city_worklist(void *data) {
//  city_dialog pdialog = (city_dialog )data;
//  pdialog.worklist_shell = null;
//}
//
//
///****************************************************************
//...
//*****************************************************************/
//static void sell_callback_yes(Widget w, XtPointer client_data,
//			      XtPointer call_data)
//{
//  city_dialog pdialog = client_data;
//
//  city_sell_improvement(pdialog.pcity, pdialog.sell_id);
//
//  destroy_message_dialog(w);
//}
//
//
///****************************************************************
//...
//*****************************************************************/
//static void sell_callback_no(Widget w, XtPointer client_data,
//			     XtPointer call_data)
//{
//  destroy_message_dialog(w);
//}
//
//
///****************************************************************
//...
//*****************************************************************/
//void sell_callback(Widget w, XtPointer client_data, XtPointer call_data)
//{
//  city_dialog pdialog;
//  XawListReturnStruct *ret;
//  
//  pdialog=(city_dialog )client_data;
//
//  ret=XawListShowCurrent(pdialog.improvement_list);
//
//  if(ret.list_index!=XAW_LIST_NONE) {
//    int n = 0;
//	for (int i = 0; i < Game.game.num_impr_types; i++) {
//	if((pdialog.pcity).improvements[i] == Improvement.I_NONE) {
//		continue;
//	}
//      if (n == ret.list_index) {
//	char buf[512];
//
//	if (Improvement.is_wonder(i)) {
//	  return;
//	}
//
//	pdialog.sell_id = i;
//	buf = util.my_snprintf( "Sell %s for %d gold?",
//		    City.get_impr_name_ex(pdialog.pcity, i),
//		    Improvement.impr_sell_gold(i));
//
//	popup_message_dialog(pdialog.shell, "selldialog", buf,
//			     sell_callback_yes, pdialog, 0,
//			     sell_callback_no, pdialog, 0, null);
//
//	return;
//      }
//      n++;
//    } ;
//  }
//}
//
///****************************************************************
//...
//*****************************************************************/
//void close_city_dialog(city_dialog pdialog)
//{
//  if (pdialog.worklist_shell)
//    XtDestroyWidget(pdialog.worklist_shell);
//
//  XtDestroyWidget(pdialog.shell);
//  dialog_list_unlink(&dialog_list, pdialog);
//
//  free(pdialog.citizen_labels);
//
//  free(pdialog.support_unit_pixcomms);
//  free(pdialog.present_unit_pixcomms);
//
//  for (unit psunit : pdialog.pcity.info_units_supported.data) {
//    free(psunit);
//  } }
//  unit_list_unlink_all(&(pdialog.pcity.info_units_supported));
//  for (unit psunit : pdialog.pcity.info_units_present.data) {
//    free(psunit);
//  } }
//  unit_list_unlink_all(&(pdialog.pcity.info_units_present));
//
//  if(pdialog.is_modal)
//    XtSetSensitive(toplevel, true);
//  free(pdialog);
//  popdown_cma_dialog();
//}
//
///****************************************************************
//...
//*****************************************************************/
//void citydlg_key_close(Widget w)
//{
//  citydlg_msg_close(XtParent(XtParent(w)));
//}
//
///****************************************************************
//...
//*****************************************************************/
//void citydlg_msg_close(Widget w)
//{
//  for (dialog pdialog : dialog_list.data) {
//    if (pdialog.shell == w) {
//      close_city_dialog(pdialog);
//      return;
//    }
//  } }
//}
//
///****************************************************************
//...
//*****************************************************************/
//void close_callback(Widget w, XtPointer client_data, XtPointer call_data)
//{
//  close_city_dialog((city_dialog )client_data);
//}
//
//
///****************************************************************
//								 
// City Options dialog:  (current only auto-attack options)
// 
//Note, there can only be one such dialog at a time, because
//I'm lazy.  That could be fixed, similar to way you can have
//multiple city dialogs.
//
//triggle = tri_toggle (three way toggle button)
//
//*****************************************************************/
//								  
//public static final int NUM_CITYOPT_TOGGLES = 5;
//
//Widget create_cityopt_dialog(char *city_name);
//void cityopt_ok_command_callback(Widget w, XtPointer client_data, 
//				XtPointer call_data);
//void cityopt_cancel_command_callback(Widget w, XtPointer client_data, 
//				    XtPointer call_data);
//void cityopt_newcit_triggle_callback(Widget w, XtPointer client_data,
//					XtPointer call_data);
//
//char *newcitizen_labels[] = { N"Workers", N"Scientists", N"Taxmen" };
//
//static Widget cityopt_shell = 0;
//static Widget cityopt_triggle;
//static Widget cityopt_toggles[NUM_CITYOPT_TOGGLES];
//static int cityopt_city_id = 0;
//static int newcitizen_index;
//
///****************************************************************
//...
//*****************************************************************/
//void cityopt_callback(Widget w, XtPointer client_data,
//                        XtPointer call_data)
//{
//  city_dialog pdialog = (city_dialog )client_data;
//  city pcity = pdialog.pcity;
//  int i;
//
//  if(cityopt_shell) {
//    XtDestroyWidget(cityopt_shell);
//  }
//  cityopt_shell=create_cityopt_dialog(pcity.name);
//  /* Doing this here makes the "No"'s centered consistently */
//  for(i=0; i<NUM_CITYOPT_TOGGLES; i++) {
//    boolean state = is_city_option_set(pcity, i);
//    XtVaSetValues(cityopt_toggles[i], XtNstate, state,
//		  XtNlabel, state?"Yes":"No", null);
//  }
//  if (is_city_option_set(pcity, CITYO_NEW_EINSTEIN)) {
//    newcitizen_index = 1;
//  } else if (is_city_option_set(pcity, CITYO_NEW_TAXMAN)) {
//    newcitizen_index = 2;
//  } else {
//    newcitizen_index = 0;
//  }
//  XtVaSetValues(cityopt_triggle, XtNstate, 1,
//		XtNlabel, _(newcitizen_labels[newcitizen_index]),
//		null);
//  
//  cityopt_city_id = pcity.id;
//
//  xaw_set_relative_position(pdialog.shell, cityopt_shell, 15, 15);
//  XtPopup(cityopt_shell, XtGrabNone);
//}
//
//
///**************************************************************************
//...
//**************************************************************************/
//Widget create_cityopt_dialog(char *city_name)
//{
//  Widget shell, form, label, ok, cancel;
//  int i;
//
//  shell = I_T(XtCreatePopupShell("cityoptpopup",
//				 transientShellWidgetClass,
//				 toplevel, null, 0));
//  form = XtVaCreateManagedWidget("cityoptform", 
//				 formWidgetClass, 
//				 shell, null);
//  label = XtVaCreateManagedWidget("cityoptlabel", labelWidgetClass,
//				  form, XtNlabel, city_name, null);
//
//
//  I_L(XtVaCreateManagedWidget("cityoptnewcitlabel", labelWidgetClass, 
//			      form, null));
//  
//  cityopt_triggle = XtVaCreateManagedWidget("cityoptnewcittriggle", 
//					    toggleWidgetClass, 
//					    form, null);
//
//  /* NOTE: the ordering here is deliberately out of order;
//     want toggles[] to be in enum city_options order, but
//     want display in different order. --dwp
//     - disband and workers options at top
//     - helicopters (special case air) at bottom
//  */
//
//  I_L(XtVaCreateManagedWidget("cityoptdisbandlabel", labelWidgetClass, 
//			      form, null));
//  
//  cityopt_toggles[4] = XtVaCreateManagedWidget("cityoptdisbandtoggle", 
//					      toggleWidgetClass, 
//					      form, null);
//
//  I_L(XtVaCreateManagedWidget("cityoptvlandlabel", labelWidgetClass, 
//			      form, null));
//  
//  cityopt_toggles[0] = XtVaCreateManagedWidget("cityoptvlandtoggle", 
//					      toggleWidgetClass, 
//					      form, null);
//  
//  I_L(XtVaCreateManagedWidget("cityoptvsealabel", labelWidgetClass, 
//			      form, null));
//  
//  cityopt_toggles[1] = XtVaCreateManagedWidget("cityoptvseatoggle", 
//					      toggleWidgetClass, 
//					      form, null);
//  
//  I_L(XtVaCreateManagedWidget("cityoptvairlabel", labelWidgetClass, 
//			      form, null));
//  
//  cityopt_toggles[3] = XtVaCreateManagedWidget("cityoptvairtoggle", 
//					      toggleWidgetClass, 
//					      form, null);
//  
//  I_L(XtVaCreateManagedWidget("cityoptvhelilabel", labelWidgetClass, 
//			      form, null));
//  
//  cityopt_toggles[2] = XtVaCreateManagedWidget("cityoptvhelitoggle", 
//					      toggleWidgetClass, 
//					      form, null);
//
//  ok = I_L(XtVaCreateManagedWidget("cityoptokcommand",
//				   commandWidgetClass,
//				   form, null));
//  
//  cancel = I_L(XtVaCreateManagedWidget("cityoptcancelcommand",
//				       commandWidgetClass,
//				       form, null));
//
//  XtAddCallback(ok, XtNcallback, cityopt_ok_command_callback, 
//                (XtPointer)shell);
//  XtAddCallback(cancel, XtNcallback, cityopt_cancel_command_callback, 
//                (XtPointer)shell);
//  for(i=0; i<NUM_CITYOPT_TOGGLES; i++) {
//    XtAddCallback(cityopt_toggles[i], XtNcallback, toggle_callback, null);
//  }
//  XtAddCallback(cityopt_triggle, XtNcallback,
//		cityopt_newcit_triggle_callback, null);
//
//  XtRealizeWidget(shell);
//
//  xaw_horiz_center(label);
//  return shell;
//}
//  
///**************************************************************************
//...
//**************************************************************************/
//void cityopt_cancel_command_callback(Widget w, XtPointer client_data, 
//				    XtPointer call_data)
//{
//  XtDestroyWidget(cityopt_shell);
//  cityopt_shell = 0;
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void cityopt_ok_command_callback(Widget w, XtPointer client_data, 
//				XtPointer call_data)
//{
//  city pcity = Game.find_city_by_id(cityopt_city_id);
//
//  if (pcity) {
//    int i, new_options;
//    Boolean b;
//    
//    new_options = 0;
//    for(i=0; i<NUM_CITYOPT_TOGGLES; i++)  {
//      XtVaGetValues(cityopt_toggles[i], XtNstate, &b, null);
//      if (b) new_options |= (1<<i);
//    }
//    if (newcitizen_index == 1) {
//      new_options |= (1<<CITYO_NEW_EINSTEIN);
//    } else if (newcitizen_index == 2) {
//      new_options |= (1<<CITYO_NEW_TAXMAN);
//    }
//    dsend_packet_city_options_req(&aconnection, cityopt_city_id,new_options);
//  }
//  XtDestroyWidget(cityopt_shell);
//  cityopt_shell = 0;
//}
//
///**************************************************************************
// Changes the label of the toggle widget to between newcitizen_labels
// and increments (mod 3) newcitizen_index.
//**************************************************************************/
//void cityopt_newcit_triggle_callback(Widget w, XtPointer client_data,
//					XtPointer call_data)
//{
//  newcitizen_index++;
//  if (newcitizen_index>=3) {
//    newcitizen_index = 0;
//  }
//  XtVaSetValues(cityopt_triggle, XtNstate, 1,
//		XtNlabel, _(newcitizen_labels[newcitizen_index]),
//		null);
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void popdown_cityopt_dialog()
//{
//  if(cityopt_shell) {
//    XtDestroyWidget(cityopt_shell);
//    cityopt_shell = 0;
//  }
//}
//
///****************************************************************
//  The user clicked on the "CMA..." button in the citydialog.
//*****************************************************************/
//void cma_callback(Widget w, XtPointer client_data,
//                  XtPointer call_data)
//{
//  city_dialog pdialog = (city_dialog )client_data;
//  popdown_cma_dialog();
//  show_cma_dialog(pdialog.pcity, pdialog.shell);
//}
//
//
}