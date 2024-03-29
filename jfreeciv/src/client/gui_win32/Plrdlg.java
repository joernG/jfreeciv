package client.gui_win32;

public class Plrdlg{

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
//#include <windows.h>
//#include <windowsx.h>
//#include <commctrl.h>
//
//#include "diptreaty.h"
//#include "fcintl.h"
//#include "Game.game.h"
//#include "packets.h"
//#include "nation.h"
//#include "player.h"
//#include "support.h"
//
//#include "chatline.h"
//#include "civclient.h"
//#include "climisc.h"
//#include "clinet.h"
//#include "gui_main.h"
//#include "gui_stuff.h"
//#include "inteldlg.h"
//#include "spaceshipdlg.h"
//#include "colors.h"
//#include "graphics.h"
//#include "log.h"
//
//#include "plrdlg.h"
//
//
//static HWND players_dialog;
//static int sort_dir=1;
//static int sort_column=2;
//
//public static final int ID_PLAYERS_LIST = 101;
//public static final int ID_PLAYERS_INT = 102;
//public static final int ID_PLAYERS_MEET = 103;
//public static final int ID_PLAYERS_WAR = 104;
//public static final int ID_PLAYERS_VISION = 105;
//public static final int ID_PLAYERS_SSHIP = 106;
//
//public static final int NUM_COLUMNS = 10;
//
///******************************************************************
//
//*******************************************************************/
//static void players_meet(int player_index)
//{
//  if (can_meet_with_player(&Game.game.players[player_index])) {
//    dsend_packet_diplomacy_init_meeting_req(&aconnection, player_index);
//
//  } else {
//    append_output_window(("Game: You need an embassy to " +
//			   "establish a diplomatic meeting."));
//  }
//}
//
///******************************************************************
//
//*******************************************************************/
//static void players_war(int player_index)
//{
//  dsend_packet_diplomacy_cancel_pact(&aconnection, player_index,
//				     CLAUSE_CEASEFIRE);
//}
//
///******************************************************************
//
//*******************************************************************/
//static void players_vision(int player_index)
//{
//  dsend_packet_diplomacy_cancel_pact(&aconnection, player_index,
//				     CLAUSE_VISION);
//}
//
///******************************************************************
//
//*******************************************************************/
//static void players_intel(int player_index)
//{
//  if (can_intel_with_player(&Game.game.players[player_index])) {
//    popup_intel_dialog(&Game.game.players[player_index]);
//  } 
//}
//
///******************************************************************
//
//*******************************************************************/
//static void players_sship(int player_index)
//{
//  popup_spaceship_dialog(&Game.game.players[player_index]);
//}
//
///* 
// * Builds the text for the cells of a row in the player report. If
// * update is 1, only the changable entries are build.
// */
//static void build_row(final String*row, int i, int update)
//{
//  static String namebuf,  aibuf[2], dsbuf[32],
//      repbuf[32], statebuf[32], idlebuf[32];
//  final player_diplstate pds;
//
//  /* we cassume that neither name nor the nation of a player changes */
//  if (update == 0) {
//    /* the playername */
//    namebuf = util.my_snprintf( "%-16s", Game.game.players[i].name);
//    row[0] = namebuf;
//
//
//    /* the nation */
//    row[1] = get_nation_name(Game.game.players[i].nation);
//  }
//
//  /* text for name, plus AI marker */
//  aibuf[0] = (Game.game.players[i].ai.control ? '*' : '\0');
//  aibuf[1] = '\0';
//
//  /* text for diplstate type and turns -- not applicable if this is me */
//  if (i == Game.game.player_idx) {
//    strcpy(dsbuf, "-");
//  } else {
//    pds = pplayer_get_diplstate(Game.game.player_ptr, get_player(i));
//    if (pds.type == DS_CEASEFIRE) {
//      dsbuf = util.my_snprintf( "%s (%d)",
//		  diplstate_text(pds.type), pds.turns_left);
//    } else {
//      dsbuf = util.my_snprintf( "%s", diplstate_text(pds.type));
//    }
//  }
//
//  /* text for state */
//  if (Game.game.players[i].is_alive) {
//    if (Game.game.players[i].is_connected) {
//      if (Game.game.players[i].turn_done) {
//	statebuf = String.format( "done");
//      } else {
//	statebuf = String.format( "moving");
//      }
//    } else {
//      statebuf[0] = '\0';
//    }
//  } else {
//    statebuf = String.format( "R.I.P");
//  }
//
//  /* text for idleness */
//  if (Game.game.players[i].nturns_idle > 3) {
//    idlebuf = util.my_snprintf(
//		PL("(idle %d turn)", "(idle %d turns)",
//		    Game.game.players[i].nturns_idle - 1),
//		Game.game.players[i].nturns_idle - 1);
//  } else {
//    idlebuf[0] = '\0';
//  }
//
//  /* text for reputation */
//  repbuf = util.my_snprintf(
//	      reputation_text(Game.game.players[i].reputation));
//
//  /* assemble the whole lot */
//  row[2] = aibuf;
//  row[3] = get_embassy_status(Game.game.player_ptr, &Game.game.players[i]);
//  row[4] = dsbuf;
//  row[5] = get_vision_status(Game.game.player_ptr, &Game.game.players[i]);
//  row[6] = repbuf;
//  row[7] = statebuf;
//  row[8] = (char *) player_addr_hack(&Game.game.players[i]);	/* Fixme */
//  row[9] = idlebuf;
//}
//
//
//
///******************************************************************
//
//*******************************************************************/
//static int CALLBACK sort_proc(LPARAM lParam1, LPARAM lParam2,
//			      LPARAM lParamSort)
//{
//  char text1[128];
//  char text2[128];
//  final String row_texts[NUM_COLUMNS];
//  build_row(row_texts,lParam1,0);
//  text1 = String.format(row_texts[lParamSort]);
//  build_row(row_texts,lParam2,0);
//  text2 = String.format(row_texts[lParamSort]);
//  return sort_dir*mystrcasecmp(text1,text2);
//}
//
///******************************************************************
//
// ******************************************************************/
//static void enable_buttons(int player_index)
//{
//  player pplayer=&Game.game.players[player_index];
//  if (pplayer.spaceship.state!=spaceship_state.SSHIP_NONE)
//    EnableWindow(GetDlgItem(players_dialog,ID_PLAYERS_SSHIP),
//		 true);
//  else
//    EnableWindow(GetDlgItem(players_dialog,ID_PLAYERS_SSHIP),
//		 false);
//  switch (pplayer_get_diplstate
//	  (Game.game.player_ptr, get_player(player_index)).type) {
//  case diplstate_type.DS_WAR:
//  case diplstate_type.DS_NO_CONTACT:
//    EnableWindow(GetDlgItem(players_dialog,ID_PLAYERS_WAR), false);
//    break;
//  default:
//    EnableWindow(GetDlgItem(players_dialog,ID_PLAYERS_WAR),true);
//  }
//  
//  EnableWindow(GetDlgItem(players_dialog, ID_PLAYERS_VISION),
//	       gives_shared_vision(Game.game.player_ptr, pplayer));
//
//  EnableWindow(GetDlgItem(players_dialog, ID_PLAYERS_MEET),
//               can_meet_with_player(pplayer));
//  EnableWindow(GetDlgItem(players_dialog, ID_PLAYERS_INT),
//               can_intel_with_player(pplayer));
//}
//
///******************************************************************
//
//*******************************************************************/
//static LONG CALLBACK players_proc(HWND dlg,UINT message,
//				  WPARAM wParam,LPARAM lParam)
//{
//  int sel,i,n;
//  int player_index;
//  LV_ITEM lvi;
//
//  sel=-1;
//  if ((message==WM_COMMAND) || (message==WM_NOTIFY)) {
//    n=ListView_GetItemCount(GetDlgItem(dlg,ID_PLAYERS_LIST));
//    for(i=0;i<n;i++) {
//      if (ListView_GetItemState(GetDlgItem(dlg,ID_PLAYERS_LIST),i,
//				LVIS_SELECTED)) {
//	sel=i;
//	break;
//      }
//    }
//  }
//  switch(message) {
//  case WM_CREATE:
//  case WM_GETMINMAXINFO:
//  case WM_SIZE:
//    break;
//  case WM_DESTROY:
//    ListView_SetImageList(GetDlgItem(players_dialog,ID_PLAYERS_LIST),
//			  null,LVSIL_SMALL);
//    players_dialog=null;
//    break;
//  case WM_CLOSE:
//    DestroyWindow(dlg);
//    break;
//  case WM_COMMAND:
//    
//    if (LOWORD(wParam)==IDCANCEL) {
//      DestroyWindow(dlg);
//      break;
//    }
//    if (sel<0)
//      break;
//    lvi.iItem=sel;
//    lvi.mask=LVIF_PARAM;
//    ListView_GetItem(GetDlgItem(players_dialog,ID_PLAYERS_LIST),&lvi);
//    player_index=lvi.lParam;
//    switch(LOWORD(wParam)) {
//    case ID_PLAYERS_MEET:
//      players_meet(player_index);
//      break;
//    case ID_PLAYERS_WAR:
//      players_war(player_index);
//      break;
//    case ID_PLAYERS_VISION:
//      players_vision(player_index);
//      break;
//    case ID_PLAYERS_INT:
//      players_intel(player_index);
//      break;
//    case ID_PLAYERS_SSHIP:
//      players_sship(player_index);
//      break;
//    }
//    
//    break;
//  case WM_NOTIFY:
//    if (LOWORD(wParam)==ID_PLAYERS_LIST) {
//      NM_LISTVIEW *nmlv=(NM_LISTVIEW *)lParam;
//      if (nmlv.hdr.code==LVN_COLUMNCLICK) {
//	if (nmlv.iSubItem==sort_column)
//	  sort_dir=-sort_dir;
//	else
//	  sort_dir=1;
//	sort_column=nmlv.iSubItem;
//	ListView_SortItems(GetDlgItem(dlg,ID_PLAYERS_LIST),
//			   sort_proc,sort_column);
//      } else if (nmlv.hdr.code==LVN_ITEMCHANGED) {
//	if (sel>=0) {
//      
//	  lvi.iItem=sel;
//	  lvi.mask=LVIF_PARAM;
//	  ListView_GetItem(GetDlgItem(players_dialog,ID_PLAYERS_LIST),&lvi);
//	  player_index=lvi.lParam;
//	  enable_buttons(player_index);
//	}
//      }
//    } /* fall through */
//  default:
//    return DefWindowProc(dlg,message,wParam,lParam);
//  }
//  return 0;
//}
//
///******************************************************************
//
//*******************************************************************/
//static void create_players_dialog()
//{
//  int i;
//  static char *titles_[NUM_COLUMNS] =
//    { N"Name", N"Nation", N"AI",
//      N"Embassy", N"Dipl.State", N"Vision", N"Reputation",
//      N"State", N"Host", N"Idle"
//    };
//  fcwin_box vbox;
//  fcwin_box hbox;
//  players_dialog=fcwin_create_layouted_window(players_proc,"Players",
//					      WS_OVERLAPPEDWINDOW,
//					      CW_USEDEFAULT,CW_USEDEFAULT,
//					      root_window,null,
//					      JUST_CLEANUP,
//					      null);
//  hbox=fcwin_hbox_new(players_dialog,false);
//  vbox=fcwin_vbox_new(players_dialog,false);
//  fcwin_box_add_listview(vbox,5,ID_PLAYERS_LIST,LVS_REPORT | LVS_SINGLESEL,
//			 true,true,0);
//  fcwin_box_add_button(hbox,"Close",IDCANCEL,0,true,true,5);
//  fcwin_box_add_button(hbox,"Intelligence",ID_PLAYERS_INT,0,true,true,5);
//  fcwin_box_add_button(hbox,"Meet",ID_PLAYERS_MEET,0,true,true,5);
//  fcwin_box_add_button(hbox,"Cancel Treaty",ID_PLAYERS_WAR,0,true,true,5);
//  fcwin_box_add_button(hbox,"Withdraw vision",ID_PLAYERS_VISION,
//		       0,true,true,5);
//  fcwin_box_add_button(hbox,"Spaceship",ID_PLAYERS_SSHIP,0,true,true,5);
//  EnableWindow(GetDlgItem(players_dialog,ID_PLAYERS_INT),false);
//  EnableWindow(GetDlgItem(players_dialog,ID_PLAYERS_MEET),false);
//  EnableWindow(GetDlgItem(players_dialog,ID_PLAYERS_WAR),false);
//  EnableWindow(GetDlgItem(players_dialog,ID_PLAYERS_VISION),false);
//  EnableWindow(GetDlgItem(players_dialog,ID_PLAYERS_SSHIP),false);
//  fcwin_box_add_box(vbox,hbox,false,false,5);
//  for(i=0;i<NUM_COLUMNS;i++) {
//    LV_COLUMN lvc;
//    lvc.pszText=_(titles_[i]);
//    lvc.mask=LVCF_TEXT;
//    ListView_InsertColumn(GetDlgItem(players_dialog,ID_PLAYERS_LIST),i,&lvc);
//  }
//
//  fcwin_set_box(players_dialog,vbox);
//  update_players_dialog();
//}
//
///******************************************************************
//
//*******************************************************************/      
//void
//popup_players_dialog()
//{
//  if (!players_dialog)
//    create_players_dialog();
//  ShowWindow(players_dialog,SW_SHOWNORMAL);
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void
//update_players_dialog()
//{
//  if (players_dialog && !is_plrdlg_frozen()) {
//    LV_ITEM lvi;
//    HWND lv;
//    final String row_texts[NUM_COLUMNS];
//    int i,row;
//    lv=GetDlgItem(players_dialog,ID_PLAYERS_LIST);
//    ListView_DeleteAllItems(lv);
//    for (i = 0; i < Game.game.nplayers; i++) {
//      build_row(row_texts, i, 0);
//      row=fcwin_listview_add_row(lv,i,NUM_COLUMNS, (char **)row_texts);
//      lvi.iItem=row;
//      lvi.iSubItem=0;
//      lvi.lParam=i;
//      lvi.mask=LVIF_PARAM;
//      ListView_SetItem(lv,&lvi);
//    }
//    ListView_SetColumnWidth(lv,0,LVSCW_AUTOSIZE);
//    for (i=1;i<NUM_COLUMNS;i++) {
//      ListView_SetColumnWidth(lv,i,LVSCW_AUTOSIZE_USEHEADER); 
//    }
//    fcwin_redo_layout(players_dialog);
//    ListView_SortItems(lv,sort_proc,sort_column);
//  }
//}
}