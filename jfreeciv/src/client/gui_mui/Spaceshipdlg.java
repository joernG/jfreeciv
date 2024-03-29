package client.gui_mui;

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
//#include <string.h>
//
//#include <libraries/mui.h>
//#include <mui/NListview_MCC.h>
//
//#include <clib/alib_protos.h>
//#include <proto/exec.h>
//#include <proto/muimaster.h>
//
//#include "fcintl.h"
//#include "game.h"
//#include "map.h"
//#include "mem.h"
//#include "packets.h"
//#include "player.h"
//#include "shared.h"
//#include "support.h"
//
//#include "clinet.h"
//#include "colors.h"
//#include "dialogs.h"
//#include "graphics.h"
//#include "gui_main.h"
//#include "helpdlg.h"
//#include "mapctrl.h"
//#include "mapview.h"
//#include "repodlgs.h"
//#include "spaceship.h"
//#include "tilespec.h"
//
//#include "spaceshipdlg.h"
//
///* Amiga Client Stuff */
//#include "muistuff.h"
//#include "mapclass.h"
//
//struct spaceship_dialog
//{
//  player pplayer;
//
//  Object *wnd;
//  Object *info_text;
//  Object *spaceship_area;
//  Object *launch_button;
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
//  if (!(pdialog = get_spaceship_dialog(pplayer)))
//    return;
//
//  pship = &(pdialog.pplayer.spaceship);
//
//  if (Game.game.spacerace
//      && pplayer.player_no == Game.game.player_idx
//      && pship.state == spaceship_state.SSHIP_STARTED
//      && pship.success_rate > 0)
//  {
//    set(pdialog.launch_button, MUIA_Disabled, false);
//  }
//  else
//  {
//    set(pdialog.launch_button, MUIA_Disabled, true);
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
//  if (!(pdialog = get_spaceship_dialog(pplayer)))
//    pdialog = create_spaceship_dialog(pplayer);
//
//  if (pdialog)
//    set(pdialog.wnd, MUIA_Window_Open, true);
//}
//
///****************************************************************
//popdown the dialog 
//*****************************************************************/
//void popdown_spaceship_dialog(player pplayer)
//{
//  spaceship_dialog pdialog;
//
//  if ((pdialog = get_spaceship_dialog(pplayer)))
//    close_spaceship_dialog(pdialog);
//}
//
///****************************************************************
// Must be called from the Application object so it is safe to
// dispose the window
//*****************************************************************/
//static void space_close_real(spaceship_dialog *ppdialog)
//{
//  close_spaceship_dialog(*ppdialog);
//}
//
///****************************************************************
// Close the spaceship window
//*****************************************************************/
//static void space_close(spaceship_dialog * ppdialog)
//{
//  set((*ppdialog).wnd, MUIA_Window_Open, false);
//  DoMethod(app, MUIM_Application_PushMethod, app, 4, MUIM_CallHook, &civstandard_hook, space_close_real, *ppdialog);
//}
//
///****************************************************************
// Callback for the launch button
//*****************************************************************/
//static void space_launch(spaceship_dialog * ppdialog)
//{
//  struct packet_spaceship_action packet;
//
//  packet.action = SSHIP_ACT_LAUNCH;
//  packet.num = 0;
//  send_packet_spaceship_action(&aconnection, &packet);
//}
//
///****************************************************************
//...
//*****************************************************************/
//spaceship_dialog create_spaceship_dialog(player pplayer)
//{
//  Object *close_button;
//
//  spaceship_dialog pdialog;
//  pdialog = (spaceship_dialog ) AllocVec(sizeof(struct spaceship_dialog), 0x10000);
//  if (!pdialog)
//    return null;
//
//  pdialog.pplayer = pplayer;
//
//  pdialog.wnd = WindowObject,
//    MUIA_Window_Title, pplayer.name,
//    MUIA_Window_ID, MAKE_ID('S','P','A',(pplayer.player_no & 0xff)),
//    WindowContents, VGroup,
//	Child, HGroup,
//	    Child, ScrollgroupObject,
//		MUIA_Scrollgroup_Contents, HGroupV,
//		    VirtualFrame,
//		    Child, pdialog.spaceship_area = MakeSpaceShip(&pplayer.spaceship),
//		    End,
//		End,
//            Child, BalanceObject, End,
//	    Child, VGroup,
//	        MUIA_HorizWeight, 0, 
//		Child, HVSpace,
//		Child, HGroup,
//		    TextFrame,
//		    Child, MakeLabel(("Population:\n" +
//				     "Support:\n" +
//				     "Energy:\n" +
//				     "Mass:\n" +
//				     "Travel time:\n" +
//				     "Success prob.:\n" +
//				     "Year of arrival:")),
//		    Child, pdialog.info_text = TextObject,
//			End,
//		    End,
//		Child, pdialog.launch_button = MakeButton("_Launch"),
//		Child, HVSpace,
//		End,
//	    End,
//	Child, HGroup,
//	    Child, close_button = MakeButton("_Close"),
//	    End,
//	End,
//    End;
//
//  if (pdialog.wnd)
//  {
//    DoMethod(pdialog.wnd, MUIM_Notify, MUIA_Window_CloseRequest, true, pdialog.wnd, 4, MUIM_CallHook, &civstandard_hook, space_close, pdialog);
//    DoMethod(close_button, MUIM_Notify, MUIA_Pressed, false, app, 4, MUIM_CallHook, &civstandard_hook, space_close, pdialog);
//    DoMethod(pdialog.launch_button, MUIM_Notify, MUIA_Pressed, false, app, 4, MUIM_CallHook, &civstandard_hook, space_launch, pdialog);
//
//    DoMethod(app, OM_ADDMEMBER, pdialog.wnd);
//
//    &dialog_list.foo_list_insert(pdialog);
//    refresh_spaceship_dialog(pdialog.pplayer);
//    return pdialog;
//  }
//  FreeVec(pdialog);
//  return null;
//}
//
///****************************************************************
//...
//*****************************************************************/
//void spaceship_dialog_update_info(spaceship_dialog pdialog)
//{
//  char buf[512], arrival[16] = "-   ";
//  player_spaceship pship = &(pdialog.pplayer.spaceship);
//
//  if (pship.state == spaceship_state.SSHIP_LAUNCHED)
//  {
//    arrival = String.format( Shared.textyear((int) (pship.launch_year
//				    + (int) pship.travel_time)));
//  }
//  buf = util.my_snprintf(
//	  ("%5d\n" +
//	  "%5d %%\n" +
//	  "%5d %%\n" +
//	  "%5d tons\n" +
//	  "%5.1f years\n" +
//	  "%5d %%\n" +
//	  "%8s"),
//	  pship.population,
//	  (int) (pship.support_rate * 100.0),
//	  (int) (pship.energy_rate * 100.0),
//	  pship.mass,
//	  (float) (0.1 * ((int) (pship.travel_time * 10.0))),
//	  (int) (pship.success_rate * 100.0),
//	  arrival);
//
//  set(pdialog.info_text, MUIA_Text_Contents, buf);
//}
//
///****************************************************************
// Should also check connectedness, and show non-connected
// parts differently.
//*****************************************************************/
//void spaceship_dialog_update_image(spaceship_dialog pdialog)
//{
//}
//
///****************************************************************
//...
//*****************************************************************/
//void close_spaceship_dialog(spaceship_dialog pdialog)
//{
//  if (pdialog)
//  {
//    set(pdialog.wnd, MUIA_Window_Open, false);
//    DoMethod(app, OM_REMMEMBER, pdialog.wnd);
//    MUI_DisposeObject(pdialog.wnd);
//    FreeVec(pdialog);
//  }
//}
}