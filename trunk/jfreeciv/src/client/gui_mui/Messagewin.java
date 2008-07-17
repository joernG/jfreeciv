package client.gui_mui;

public class Messagewin{

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
//#include <mui/NListview_MCC.h>
//#include <libraries/mui.h>
//
//#include <clib/alib_protos.h>
//#include <proto/exec.h>
//#include <proto/utility.h>
//#include <proto/muimaster.h>
//
//#include "events.h"
//#include "fcintl.h"
//#include "game.h"
//#include "map.h"
//#include "mem.h"
//#include "packets.h"
//#include "player.h"
//#include "support.h"
//
//#include "chatline.h"
//#include "citydlg.h"
//#include "clinet.h"
//#include "colors.h"
//#include "gui_main.h"
//#include "mapview.h"
//#include "options.h"
//
//#include "messagewin.h"
//
///* Amiga client stuff */
//#include "muistuff.h"
//
//static Object *mes_wnd;
//static struct Hook mes_consthook;
//static struct Hook mes_desthook;
//static struct Hook mes_disphook;
//static Object *mes_listview;
//static Object *mes_close_button;
//static Object *mes_goto_button;
//static Object *mes_popcity_button;
//
//static void create_meswin_dialog();
//
///****************************************************************
//popup the dialog 10% inside the main-window 
//*****************************************************************/
//void popup_meswin_dialog()
//{
//  if (!mes_wnd)
//    create_meswin_dialog();
//  if (mes_wnd)
//  {
//    real_update_meswin_dialog();
//    set(mes_wnd, MUIA_Window_Open, true);
//  }
//}
//
///****************************************************************
//...
//*****************************************************************/
//boolean is_meswin_open()
//{
//  return mes_wnd != null;
//}
//
//struct message_entry
//{
//  char *message;
//  int row;
//};
//
///****************************************************************
// Constructor of a new entry in the message listview
//*****************************************************************/
//HOOKPROTONHNO(mes_construct, message_entry , message_entry entry)
//{
//  message_entry newentry = (message_entry ) AllocVec(sizeof(*newentry), 0);
//  if (newentry)
//  {
//    int len = strlen(entry.message);
//
//    *newentry = *entry;
//
//    if ((newentry.message = (char *) AllocVec(len + 1, 0)))
//      mystrlcpy(newentry.message, entry.message, len + 1);
//  }
//  return newentry;
//}
//
///**************************************************************************
// Destructor of a entry in the message listview
//**************************************************************************/
//HOOKPROTONHNO(mes_destruct, void, message_entry entry)
//{
//  if (entry.message)
//    FreeVec(entry.message);
//  FreeVec(entry);
//}
//
///**************************************************************************
// Display function for the message listview
//**************************************************************************/
//HOOKPROTONH(mes_display, void, char **array, message_entry entry)
//{
//  if (entry)
//  {
//    *array = entry.message;
//  }
//  else
//  {
//    *array = "Messages";
//  }
//}
//
///**************************************************************************
// Callback if a message is selected inside the listview
//**************************************************************************/
//static void mes_active()
//{
//  message_entry entry;
//  DoMethod(mes_listview, MUIM_NList_GetEntry, MUIV_NList_GetEntry_Active, &entry);
//  if (entry)
//  {
//    message message = get_message(entry.row);
//    set(mes_goto_button, MUIA_Disabled, !message.location_ok);
//    set(mes_popcity_button, MUIA_Disabled, !message.city_ok);
//  }
//  else
//  {
//    set(mes_goto_button, MUIA_Disabled, true);
//    set(mes_popcity_button, MUIA_Disabled, true);
//  }
//}
//
///**************************************************************************
// Callback for the goto button
//**************************************************************************/
//static void mes_goto()
//{
//  message_entry entry;
//  DoMethod(mes_listview, MUIM_NList_GetEntry, MUIV_NList_GetEntry_Active, &entry);
//  if (entry) meswin_goto(entry.row);
//}
//
///**************************************************************************
// Callback for the Popcity button
//**************************************************************************/
//static void mes_popcity()
//{
//  message_entry entry;
//  DoMethod(mes_listview, MUIM_NList_GetEntry, MUIV_NList_GetEntry_Active, &entry);
//  if (entry) meswin_popup_city(entry.row);
//}
//
///**************************************************************************
//...
//**************************************************************************/
//static void mes_doubleclick()
//{
//  message_entry entry;
//  DoMethod(mes_listview, MUIM_NList_GetEntry, MUIV_NList_GetEntry_Active, &entry);
//  if (entry) meswin_double_click(entry.row);
//}
//
///**************************************************************************
//...
//**************************************************************************/
//static void create_meswin_dialog()
//{
//  if (mes_wnd)
//    return;
//
//  mes_consthook.h_Entry = (HOOKFUNC) mes_construct;
//  mes_desthook.h_Entry = (HOOKFUNC) mes_destruct;
//  mes_disphook.h_Entry = (HOOKFUNC) mes_display;
//
//  mes_wnd = WindowObject,
//    MUIA_Window_Title, "Messages",
//    MUIA_Window_ID, MAKE_ID('M','E','S','S'),
//    WindowContents, VGroup,
//        Child, mes_listview = NListviewObject,
//	    MUIA_NListview_NList, NListObject,
//		MUIA_NList_ConstructHook, &mes_consthook,
//		MUIA_NList_DestructHook, &mes_desthook,
//		MUIA_NList_DisplayHook, &mes_disphook,
//		End,
//	    End,
//	Child, HGroup,
//	    Child, mes_close_button = MakeButton("_Close"),
//	    Child, mes_goto_button = MakeButton("_Goto Location"),
//	    Child, mes_popcity_button = MakeButton("_Popup City"),
//	    End,
//	End,
//    End;
//
//  if (mes_wnd)
//  {
//    set(mes_goto_button, MUIA_Disabled, true);
//    set(mes_popcity_button, MUIA_Disabled, true);
//    DoMethod(mes_wnd, MUIM_Notify, MUIA_Window_CloseRequest, true, mes_wnd, 3, MUIM_Set, MUIA_Window_Open, false);
//    DoMethod(mes_close_button, MUIM_Notify, MUIA_Pressed, false, mes_wnd, 3, MUIM_Set, MUIA_Window_Open, false);
//    DoMethod(mes_goto_button, MUIM_Notify, MUIA_Pressed, false, mes_wnd, 3, MUIM_CallHook, &civstandard_hook, mes_goto);
//    DoMethod(mes_popcity_button, MUIM_Notify, MUIA_Pressed, false, mes_wnd, 3, MUIM_CallHook, &civstandard_hook, mes_popcity);
//
//    DoMethod(mes_listview, MUIM_Notify, MUIA_NList_Active, MUIV_EveryTime, app, 3, MUIM_CallHook, &civstandard_hook, mes_active);
//    DoMethod(mes_listview, MUIM_Notify, MUIA_NList_DoubleClick, true, app, 3, MUIM_CallHook, &civstandard_hook, mes_doubleclick);
//
//    DoMethod(app, OM_ADDMEMBER, mes_wnd);
//  }
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void real_update_meswin_dialog()
//{
//  int i, num = get_num_messages();
//
//  DoMethod(mes_listview, MUIM_NList_Clear);
//
//  for (i = 0; i < num; i++)
//  {
//    struct message_entry entry;
//    entry.message = get_message(i).descr;
//    entry.row = i;
//    DoMethod(mes_listview, MUIM_NList_InsertSingle, &entry, MUIV_NList_Insert_Bottom);
////    meswin_not_visited_item(i);
//  }
//}
}