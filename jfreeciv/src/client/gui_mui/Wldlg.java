package client.gui_mui;

public class Wldlg{

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
//#include <string.h>
//
//#include <libraries/mui.h>
//#include <mui/NListview_MCC.h>
//
//#include <clib/alib_protos.h>
//#include <proto/exec.h>
//#include <proto/muimaster.h>
//#include <proto/graphics.h>
//#include <proto/utility.h>
//
//#include "city.h"
//#include "fcintl.h"
//#include "Game.game.h"
//#include "helpdlg.h"
//#include "mem.h"
//#include "packets.h"
//#include "support.h"
//#include "worklist.h"
//
//#include "clinet.h"
//#include "gui_main.h"
//#include "wldlg.h"
//
///* MUI Stuff */
//#include "muistuff.h"
//#include "worklistclass.h"
//
//public static final int WORKLIST_ADVANCED_TARGETS = 1;
//public static final int WORKLIST_CURRENT_TARGETS = 0;
//public static final int WORKLIST_LIST_WIDTH = 220;
//public static final int WORKLIST_LIST_HEIGHT = 150;
//
//static Object *popup_worklist(worklist pwl,city pcity,
//		       void *parent_data, WorklistOkCallback ok_cb,
//		       WorklistCancelCallback cancel_cb);
//
///*
//  The Worklist Report dialog shows all the global worklists that the
//  player has defined.  There can be at most MAX_NUM_WORKLISTS global
//  worklists.
//  */
//struct worklist_report_dialog {
//  Object *wnd;
//  Object *listview;
//  Object *name_string;
//  Object *edit_button;
//  struct Hook listview_display_hook;
//  player pplr;
//
//  int wl_idx;
//  Object *wl_wnd; /* current worklist window */
//};
//
//static worklist_report_dialog report_dialog;
//
//static void commit_player_worklist(worklist pwl, void *data);
//static void cancel_player_worklist(void *data);
//static void populate_worklist_report_list(worklist_report_dialog pdialog);
//
///****************************************************************
//  Display function for the worklist listview
//*****************************************************************/
//HOOKPROTONH(worklist_report_display, void, char **array, APTR msg)
//{
//  LONG num = (LONG) msg;
//
//  if (num)
//  {
//    num--;
//    if (report_dialog)
//    {
//      *array = report_dialog.pplr.worklists[num].name;
//    }  else *array = null;
//  }
//}
//
///****************************************************************
//  Create a new worklist
//*****************************************************************/
//static void worklist_report_insert( worklist_report_dialog *ppdialog)
//{
//  worklist_report_dialog pdialog = *ppdialog;
//  int j;
//
//  /* Find the next free worklist for this player */
//  for (j = 0; j < MAX_NUM_WORKLISTS; j++)
//    if (!pdialog.pplr.worklists[j].is_valid)
//      break;
//
//  /* No more worklist slots free.  (!!!Maybe we should tell the user?) */
//  if (j == MAX_NUM_WORKLISTS)
//    return;
//
//  /* Validate this slot. */
//  init_worklist(&pdialog.pplr.worklists[j]);
//  pdialog.pplr.worklists[j].is_valid = true;
//  strcpy(pdialog.pplr.worklists[j].name, "empty worklist");
//
//  update_worklist_report_dialog();
//}
//
///****************************************************************
//  Remove the current worklist.  This request is made by sliding
//  up all lower worklists to fill in the slot that's being deleted.
//*****************************************************************/
//static void worklist_report_delete( worklist_report_dialog *ppdialog)
//{
//  worklist_report_dialog pdialog = *ppdialog;
//  int i, j, selection;
//
//  DoMethod(pdialog.listview, MUIM_NList_GetEntry, MUIV_NList_GetEntry_Active, &selection);
//
//  if (! selection)
//    return;
//  selection--;
//
//  /* Look for the last free worklist */
//  for (i = 0; i < MAX_NUM_WORKLISTS; i++)
//    if (!pdialog.pplr.worklists[i].is_valid)
//      break;
//
//  for (j = selection; j < i-1; j++) {
//    worklist.copy_worklist(&pdialog.pplr.worklists[j],
//                  &pdialog.pplr.worklists[j+1]);
//  }
//
//  /* The last worklist in the set is no longer valid -- it's been slid up
//   * one slot. */
//  pdialog.pplr.worklists[i-1].is_valid = false;
//  strcpy(pdialog.pplr.worklists[i-1].name, "\0");
//
//  update_worklist_report_dialog();
//}
//
///****************************************************************
//  Edit the current worklist
//*****************************************************************/
//static void worklist_report_edit(worklist_report_dialog *ppdialog)
//{
//  worklist_report_dialog pdialog = *ppdialog;
//  int selection;
//
//  DoMethod(pdialog.listview, MUIM_NList_GetEntry, MUIV_NList_GetEntry_Active, &selection);
//
//  if (!selection)
//    return;
//
//  /* Currently we allow only one worklist opened */
//  if (pdialog.wl_wnd)
//  {
//     set(pdialog.wl_wnd,MUIA_Window_Open,false);
//     DoMethod(app, OM_REMMEMBER, pdialog.wl_wnd);
//     MUI_DisposeObject(pdialog.wl_wnd);
//     pdialog.wl_wnd = null;
//  }
//
//  selection--;
//
//  pdialog.wl_idx = selection;
//  pdialog.wl_wnd = popup_worklist(&pdialog.pplr.worklists[selection], null, 
//		 pdialog, commit_player_worklist, cancel_player_worklist);
//
//  if (pdialog.wl_wnd)
//  {
//    DoMethod(app, OM_ADDMEMBER, pdialog.wl_wnd);
//    set(pdialog.wl_wnd, MUIA_Window_Open, true);
//  }
//}
//
///****************************************************************
// ...
//*****************************************************************/
//static void worklist_report_list( worklist_report_dialog *ppdialog)
//{
//  worklist_report_dialog pdialog = *ppdialog;
//  int selection;
//
//  DoMethod(pdialog.listview, MUIM_NList_GetEntry, MUIV_NList_GetEntry_Active, &selection);
//
//  if (!selection)
//    return;
//  selection--;
//
//  SetAttrs(pdialog.name_string,
//     MUIA_String_Contents, pdialog.pplr.worklists[selection].name, TAG_DONE);
//}
//
///****************************************************************
// Rename the worklist
//*****************************************************************/
//static void worklist_report_rename( worklist_report_dialog *ppdialog)
//{
//  worklist_report_dialog pdialog = *ppdialog;
//  int selection;
//
//  DoMethod(pdialog.listview, MUIM_NList_GetEntry, MUIV_NList_GetEntry_Active, &selection);
//
//  if (!selection)
//    return;
//  selection--;
//
//  sz_strlcpy(pdialog.pplr.worklists[selection].name, 
//             getstring(pdialog.name_string));
//
//  update_worklist_report_dialog();
//}
//
///****************************************************************
//  Bring up the worklist report.
//*****************************************************************/
//void popup_worklists_dialog(player pplr)
//{
//  worklist_report_dialog pdialog;
//  Object *close_button,*insert_button,*delete_button, *edit_button;
//  if (report_dialog)
//  {
//    set(report_dialog.wnd, MUIA_Window_Open, true);
//    return;
//  }
//
//  pdialog = malloc(sizeof(struct worklist_report_dialog));
//  if (!pdialog) return;
//
//  memset(pdialog,0,sizeof(struct worklist_report_dialog));
//
//  pdialog.listview_display_hook.h_Entry = (HOOKFUNC)worklist_report_display;
//
//  pdialog.pplr = pplr;
//  pdialog.wnd = WindowObject,
//    MUIA_Window_Title, "Freeciv - Edit Worklists",
//    WindowContents, VGroup,
//	Child, pdialog.listview = NListviewObject,
//	    MUIA_CycleChain, 1,
//	    MUIA_NListview_NList, NListObject,
//	        MUIA_NList_Input, true,
//		MUIA_NList_DisplayHook, &pdialog.listview_display_hook,
//		MUIA_NList_Title, "Available Worklists",
//		End,
//	    End,
//	Child, HGroup,
//	    Child, pdialog.name_string = MakeString(null,MAX_LEN_NAME-1),
//	    Child, edit_button = MakeButton("_Edit"),
//	    Child, insert_button = MakeButton("_New"),
//	    Child, delete_button = MakeButton("_Remove"),
//	    End,
//	Child, close_button = MakeButton("_Ok"),
//	End,
//    End;
//
//  if (pdialog.wnd)
//  {
//    SetAttrs(pdialog.name_string, MUIA_String_AttachedList, pdialog.listview,
//    				   MUIA_Weight, 500, TAG_DONE);
//
//    DoMethod(app,OM_ADDMEMBER,pdialog.wnd);
//    DoMethod(pdialog.wnd, MUIM_Notify, MUIA_Window_CloseRequest, true, pdialog.wnd, 3, MUIM_Set, MUIA_Window_Open, false);
//    DoMethod(close_button, MUIM_Notify, MUIA_Pressed, false, pdialog.wnd, 3, MUIM_Set, MUIA_Window_Open, false);
//    DoMethod(insert_button, MUIM_Notify, MUIA_Pressed, false, pdialog.wnd, 4, MUIM_CallHook, &civstandard_hook, worklist_report_insert, pdialog);
//    DoMethod(delete_button, MUIM_Notify, MUIA_Pressed, false, pdialog.wnd, 4, MUIM_CallHook, &civstandard_hook, worklist_report_delete, pdialog);
//    DoMethod(edit_button, MUIM_Notify, MUIA_Pressed, false, pdialog.wnd, 4, MUIM_CallHook, &civstandard_hook, worklist_report_edit, pdialog);
//    DoMethod(pdialog.listview, MUIM_Notify, MUIA_NList_Active, MUIV_EveryTime, pdialog.wnd, 4, MUIM_CallHook, &civstandard_hook, worklist_report_list, pdialog);
//    DoMethod(pdialog.listview, MUIM_Notify, MUIA_NList_DoubleClick, true, pdialog.wnd, 4, MUIM_CallHook, &civstandard_hook, worklist_report_edit, pdialog);
//    DoMethod(pdialog.name_string, MUIM_Notify, MUIA_String_Acknowledge, MUIV_EveryTime, pdialog.wnd, 4, MUIM_CallHook, &civstandard_hook, worklist_report_rename, pdialog);
//
//    report_dialog = pdialog;
//    populate_worklist_report_list(pdialog);
//    set(pdialog.wnd, MUIA_Window_Open, true);
//  }	else
//  {
//    free(pdialog);
//  }
//}
//
///*************************************************************************
//  Bring up a dialog box to edit the given worklist.  If pcity is
//  non-null, then use pcity to determine the set of units and improvements
//  that can be made.  Otherwise, just list everything that technology
//  will allow.
//*************************************************************************/
//static Object *popup_worklist(worklist pwl,city pcity,
//		       void *parent_data, WorklistOkCallback ok_cb,
//		       WorklistCancelCallback cancel_cb)
//{
//  Object *wl;
//  Object *workwnd = WindowObject,
//    MUIA_Window_Title, "Freeciv - Edit Worklist",
//    MUIA_Window_ID, MAKE_ID('W','R','K','L'),
//    WindowContents, VGroup,
//      Child, wl = WorklistObject,
//  	MUIA_Worklist_Worklist, pwl,
//  	MUIA_Worklist_City, pcity,
//	MUIA_Worklist_PatentData, parent_data,
//  	MUIA_Worklist_OkCallBack, ok_cb,
//  	MUIA_Worklist_CancelCallBack, cancel_cb,
//  	MUIA_Worklist_Embedded, false,
//  	End,
//     End,
//   End;
//
//  if (workwnd)
//  {
//    DoMethod(workwnd, MUIM_Notify, MUIA_Window_CloseRequest, true, wl, 1, MUIM_Worklist_Cancel);
//  }
//  return workwnd;
//}
//
///****************************************************************
//
//*****************************************************************/
//void update_worklist_report_dialog()
//{
//  /* If the worklist report is open, force its contents to be 
//     update. */
//  if (report_dialog)
//  {
//    populate_worklist_report_list(report_dialog);
//  }
//}
//
///****************************************************************
// Commit the changes to the worklist for this player.
//*****************************************************************/
//static void commit_player_worklist(worklist pwl, void *data)
//{
//  worklist_report_dialog pdialog;
//  pdialog = (worklist_report_dialog )data;
//
//  worklist.copy_worklist(&pdialog.pplr.worklists[pdialog.wl_idx], pwl);
//  set(pdialog.wl_wnd,MUIA_Window_Open,false);
//}
//
///****************************************************************
// Called when canceled
//*****************************************************************/
//static void cancel_player_worklist(void *data)
//{
//  worklist_report_dialog pdialog;
//  pdialog = (worklist_report_dialog )data;
//  set(pdialog.wl_wnd,MUIA_Window_Open,false);
//}
//
//
///****************************************************************
//  Fill in the worklist arrays in the pdialog.
//*****************************************************************/
//static void populate_worklist_report_list(worklist_report_dialog pdialog)
//{
//  int i;
//  int last_active = xget(pdialog.listview, MUIA_NList_Active);
//
//  set(pdialog.listview, MUIA_NList_Quiet, true);
//
//  DoMethod(pdialog.listview, MUIM_NList_Clear);
//
//  for (i = 0; i < MAX_NUM_WORKLISTS; i++)
//  {
//    if (pdialog.pplr.worklists[i].is_valid)
//    {
//      DoMethod(pdialog.listview, MUIM_NList_InsertSingle, i+1, MUIV_NList_Insert_Bottom);
//    }
//  }
//  SetAttrs(pdialog.listview,
//      MUIA_NList_Active, last_active,
//      MUIA_NList_Quiet, false,
//      TAG_DONE);
//}
}