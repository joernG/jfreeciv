package client.gui_win32;

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
//#include <windows.h>
//
//#include "fcintl.h"
//#include "game.h"
//#include "Map.map.h"
//#include "mem.h"
//#include "packets.h"
//#include "player.h"
//#include "shared.h"
//#include "support.h"
//
//#include "clinet.h"
//#include "climisc.h"
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
//#include "spaceship.h"
//#include "tilespec.h"
//#include "text.h"
//
//
//#include "spaceshipdlg.h"
//
//struct spaceship_dialog {
//  player pplayer;
//  HWND mainwin;
//  HWND info_label;
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
//void spaceship_dialog_update_image(HDC hdc,spaceship_dialog pdialog);
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
//  HDC hdc;
//  spaceship_dialog pdialog;
//  player_spaceship pship;
//
//  if(!(pdialog=get_spaceship_dialog(pplayer)))
//    return;
//
//  pship=&(pdialog.pplayer.spaceship);
//
//  if(game.spacerace
//     && pplayer.player_no == game.player_idx
//     && pship.state == spaceship_state.SSHIP_STARTED
//     && pship.success_rate > 0) {
//    EnableWindow(GetDlgItem(pdialog.mainwin,IDOK),true);
//  } else {
//    EnableWindow(GetDlgItem(pdialog.mainwin,IDOK),false);
//  }
//  
//  spaceship_dialog_update_info(pdialog);
//  hdc=GetDC(pdialog.mainwin);
//  spaceship_dialog_update_image(hdc,pdialog);
//  ReleaseDC(pdialog.mainwin,hdc);
//}
//
///****************************************************************
//popup the dialog
//*****************************************************************/
//void
//popup_spaceship_dialog(player pplayer)
//{
//  spaceship_dialog pdialog;
//  
//  if(!(pdialog=get_spaceship_dialog(pplayer)))
//    pdialog=create_spaceship_dialog(pplayer);
//  ShowWindow(pdialog.mainwin,SW_SHOWNORMAL);
//}
//
///***************************************************************
//
//****************************************************************/
//void
//popdown_spaceship_dialog(player pplayer)
//{
//  spaceship_dialog pdialog;
//  
//  if((pdialog=get_spaceship_dialog(pplayer)))
//    close_spaceship_dialog(pdialog);
//  
//}
//
///****************************************************************
//
//*****************************************************************/
//static LONG CALLBACK spaceship_proc(HWND dlg,UINT message,
//				    WPARAM wParam,LPARAM lParam)
//{
// 
//  spaceship_dialog pdialog;
//  pdialog=fcwin_get_user_data(dlg);
//  switch(message) {
//  case WM_SIZE:
//  case WM_GETMINMAXINFO:
//    break;
//  case WM_CREATE:
//    break;
//  case WM_PAINT: 
//    { 
//      HDC hdc;
//      PAINTSTRUCT ps;
//      hdc=BeginPaint(dlg,(LPPAINTSTRUCT)&ps);
//      spaceship_dialog_update_image(hdc,pdialog);
//      EndPaint(dlg,(LPPAINTSTRUCT)&ps);
//    }
//    break;
//  case WM_CLOSE:
//    DestroyWindow(dlg);
//    break;
//  case WM_DESTROY:
//    dialog_list_unlink(&dialog_list, pdialog);
//    free(pdialog);
//    break;
//  case WM_COMMAND:
//    switch(LOWORD(wParam)) {
//    case IDOK: {
//      send_packet_spaceship_launch(&aconnection);
//    }
//    break;
//    case IDCANCEL:
//      DestroyWindow(dlg);
//      break;
//    }
//  default:
//    return DefWindowProc(dlg,message,wParam,lParam);
//  }
//  return 0;
//}
//
///****************************************************************
//
//*****************************************************************/
//static void image_minsize(POINT *minsize,void *data)
//{
//  minsize.x=sprites.spaceship.habitation.width*7;
//  minsize.y=sprites.spaceship.habitation.height*7;
//}
//
///****************************************************************
//
//*****************************************************************/
//static void image_setsize(RECT *size,void *data)
//{
//}
//
///****************************************************************
//...
//*****************************************************************/
//spaceship_dialog create_spaceship_dialog(player pplayer)
//{
//  spaceship_dialog pdialog;
//  fcwin_box hbox;
//  fcwin_box vbox;
//  
//  pdialog=fc_malloc(sizeof(struct spaceship_dialog));
//  pdialog.pplayer=pplayer;
//  pdialog.mainwin=fcwin_create_layouted_window(spaceship_proc,pplayer.name,
//						WS_OVERLAPPEDWINDOW,
//						CW_USEDEFAULT,CW_USEDEFAULT,
//						root_window,null,
//						JUST_CLEANUP,
//						pdialog);
//  vbox=fcwin_vbox_new(pdialog.mainwin,false);
//  hbox=fcwin_hbox_new(pdialog.mainwin,false);
//  fcwin_box_add_generic(hbox,image_minsize,image_setsize,null,
//			null,true,true,3);
//  pdialog.info_label =
//      fcwin_box_add_static(hbox, get_spaceship_descr(null), 0, SS_LEFT,
//			   false, false, 2);
//  fcwin_box_add_box(vbox,hbox,true,true,5);
//  hbox=fcwin_hbox_new(pdialog.mainwin,true);
//  fcwin_box_add_button(hbox,"Close",IDCANCEL,0,true,true,20);
//  fcwin_box_add_button(hbox,"Launch",IDOK,0,true,true,20);
//  fcwin_box_add_box(vbox,hbox,true,true,5);
//  fcwin_set_box(pdialog.mainwin,vbox);
//  
//  dialog_list_insert(&dialog_list, pdialog);
//  refresh_spaceship_dialog(pdialog.pplayer);
//
//  return pdialog;
//}
//
///****************************************************************
//...
//*****************************************************************/
//void spaceship_dialog_update_info(spaceship_dialog pdialog)
//{
//  SetWindowText(pdialog.info_label,
//		get_spaceship_descr(&pdialog.pplayer.spaceship));
//}
//
///****************************************************************
//...
//Should also check connectedness, and show non-connected
//parts differently.
//*****************************************************************/
//void spaceship_dialog_update_image(HDC hdc,spaceship_dialog pdialog)
//{
//  RECT rc;
//  int i, j, k, x, y;  
//  Sprite sprite = sprites.spaceship.habitation;   /* for size */
//  player_spaceship ship = &pdialog.pplayer.spaceship;
//  rc.left=0;
//  rc.top=0;
//  rc.right=sprite.width*7;
//  rc.bottom=sprite.height*7;
//  FillRect(hdc,&rc,brush_std[COLOR_STD_BLACK]);
//  
//  for (i=0; i < player_spaceship.NUM_SS_MODULES; i++) {
//    j = i/3;
//    k = i%3;
//    if ((k==0 && j >= ship.habitation)
//        || (k==1 && j >= ship.life_support)
//        || (k==2 && j >= ship.solar_panels)) {
//      continue;
//    }
//    x = Spaceship.modules_info[i].x * sprite.width  / 4 - sprite.width / 2;
//    y = Spaceship.modules_info[i].y * sprite.height / 4 - sprite.height / 2;
//
//    sprite = (k==0 ? sprites.spaceship.habitation :
//              k==1 ? sprites.spaceship.life_support :
//                     sprites.spaceship.solar_panels);
//    draw_sprite(sprite,hdc,x,y);
//  
//  }
//
//  for (i=0; i < player_spaceship.NUM_SS_COMPONENTS; i++) {
//    j = i/2;
//    k = i%2;
//    if ((k==0 && j >= ship.fuel)
//        || (k==1 && j >= ship.propulsion)) {
//      continue;
//    }
//    x = Spaceship.components_info[i].x * sprite.width  / 4 - sprite.width / 2;
//    y = Spaceship.components_info[i].y * sprite.height / 4 - sprite.height / 2;
//
//    sprite = (k==0) ? sprites.spaceship.fuel : sprites.spaceship.propulsion;
//    draw_sprite(sprite,hdc,x,y);
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
//    draw_sprite(sprite,hdc,x,y);
//  }
//}
//
///****************************************************************
//...
//*****************************************************************/
//void close_spaceship_dialog(spaceship_dialog pdialog)
//{
//  DestroyWindow(pdialog.mainwin);
//}
}