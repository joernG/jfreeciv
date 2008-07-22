package client.gui_win32;

public class Dialogs{
///**********************************************************************
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
//#include <string.h>
//
//#include <windows.h>
//#include <windowsx.h>
// 
//#include "capability.h"
//#include "fcintl.h"
//#include "game.h"
//#include "government.h"
//#include "Map.map.h"
//#include "log.h"
//#include "mem.h"
//#include "packets.h"
//#include "player.h"
//#include "rand.h"
//#include "support.h"
// 
//#include "civclient.h"
//#include "clinet.h"
//#include "control.h"
//#include "tilespec.h"
//#include "packhand.h"
//#include "text.h"
//
//#include "chatline.h"
//#include "dialogs.h"
//#include "gui_stuff.h"
//#include "graphics.h"
//#include "mapctrl.h"
//#include "mapview.h"
//#include "gui_main.h" 
//                             
//public static final int POPUP_MESSAGEDLG_IDBASE = 500;
//public static final int UNITSELECT_CLOSE = 300;
//public static final int UNITSELECT_READY_ALL = 301;
//public static final int UNITSELECT_UNITS_BASE = 305;
//public static final int MAX_NUM_GOVERNMENTS = 10;
//public static final int ID_RACESDLG_NATION_BASE = 2000;
//public static final int ID_RACESDLG_STYLE_BASE = 1000;
//public static final int ID_PILLAGE_BASE = 1000;
//
//static HWND unit_select_main;
//static HWND unit_select_ready;
//static HWND unit_select_close;
//static HWND unit_select_labels[100];
//static HWND unit_select_but[100];
//static HBITMAP unit_select_bitmaps[100];
//static int unit_select_ids[100];
//static int unit_select_no;
//
//static HWND caravan_dialog;
//static int caravan_city_id;
//static int caravan_unit_id;
//
//static boolean is_showing_pillage_dialog = false;
//static int unit_to_use_to_pillage;
//
//static HWND spy_tech_dialog;
//static int advance_type[A_LAST+1];
//
//static HWND spy_sabotage_dialog;
//static int improvement_type[B_LAST+1];
//
//                            
//struct message_dialog_button
//{
//  int id;
//  HWND buttwin;
//  void *data;
//  void (*callback)(HWND , void *);
//  message_dialog_button next;
//  
//};
//struct message_dialog {
//  HWND label;
//  HWND main;
//  fcwin_box vbox;
//  message_dialog_button firstbutton; 
//};
//
//static HWND races_dlg;
//static HWND races_class;
//static HWND races_legend;
//int selected_leader_sex;
//int selected_style;
//fcwin_box government_box;
//int selected_nation;
//int selected_leader;
//static int city_style_idx[64];        /* translation table basic style.city_style  */
//static int city_style_ridx[64];       /* translation table the other way            */
//                               /* they in fact limit the num of styles to 64 */
//static int b_s_num; /* number of basic city styles, i.e. those that you can start with */
//
//
//
//int diplomat_dialog_open = 0;
//int diplomat_id;
//int diplomat_target_id;
//static LONG APIENTRY msgdialog_proc(HWND hWnd,
//			       UINT message,
//			       UINT wParam,
//			       LONG lParam);                         
//
//
///**************************************************************************
//
//**************************************************************************/
//static LONG CALLBACK notify_goto_proc(HWND dlg,UINT message,
//				      WPARAM wParam,
//				      LPARAM lParam)
//{
//  switch(message) {
//  case WM_DESTROY:
//    break;
//  case WM_CREATE:
//  case WM_SIZE:
//  case WM_GETMINMAXINFO:
//    break;
//  case WM_CLOSE:
//    DestroyWindow(dlg);
//    break;
//  case WM_COMMAND:
//    switch(LOWORD(wParam)) {
//    case IDOK:
//      {
//	tile ptile = fcwin_get_user_data(dlg);
//	center_tile_mapcanvas(ptile);
//      }
//    case IDCANCEL:
//      DestroyWindow(dlg);
//      break;
//    }
//    break;
//  default:
//    return DefWindowProc(dlg,message,wParam,lParam);
//  }
//  return 0;
//}
//
///**************************************************************************
//  Popup a dialog to display information about an event that has a
//  specific location.  The user should be given the option to goto that
//  location.
//**************************************************************************/
//void popup_notify_goto_dialog(final String headline, final String lines,
//			      tile ptile)
//{
//  fcwin_box hbox;
//  fcwin_box vbox;
//  HWND dlg;
//  dlg=fcwin_create_layouted_window(notify_goto_proc,
//				   headline,WS_OVERLAPPEDWINDOW,
//				   CW_USEDEFAULT,CW_USEDEFAULT,
//				   root_window,null,
//				   REAL_CHILD,
//				   ptile);
//  vbox=fcwin_vbox_new(dlg,false);
//  fcwin_box_add_static(vbox,lines,0,SS_LEFT,
//		       true,true,10);
//  hbox=fcwin_hbox_new(dlg,true);
//  fcwin_box_add_button(hbox,"Close",IDCANCEL,0,true,true,10);
//  fcwin_box_add_button(hbox,"Goto and Close",IDOK,0,true,true,10);
//  fcwin_box_add_box(vbox,hbox,false,false,10);
//  fcwin_set_box(dlg,vbox);
//  ShowWindow(dlg,SW_SHOWNORMAL);
//}
//
///**************************************************************************
//
//**************************************************************************/
//static LONG CALLBACK notify_proc(HWND hWnd,
//				 UINT message,
//				 WPARAM wParam,
//				 LPARAM lParam)  
//{
//  switch(message)
//    {
//    case WM_CREATE:
//      break;
//    case WM_CLOSE:
//      DestroyWindow(hWnd);
//      
//      break;
//    case WM_COMMAND:
//      if (LOWORD(wParam)==ID_NOTIFY_CLOSE)
//	DestroyWindow(hWnd);
//      break;
//    case WM_DESTROY:
//      break;
//    case WM_GETMINMAXINFO:
//      break;
//    case WM_SIZE:
//      break;
//    default:
//      return DefWindowProc(hWnd,message,wParam,lParam);
//    }
//  return 0;
//}
//
///**************************************************************************
//  Popup a generic dialog to display some generic information.
//**************************************************************************/
//void popup_notify_dialog(final String caption, final String headline,
//			 final String lines)
//{
//  HWND dlg;
//  dlg=fcwin_create_layouted_window(notify_proc,caption,WS_OVERLAPPEDWINDOW,
//				   CW_USEDEFAULT,CW_USEDEFAULT,
//				   root_window,null,
//				   REAL_CHILD,
//				   null);
//  if (dlg)
//    {
//      HWND edit;
//      fcwin_box vbox;
//      char *buf;
//      vbox=fcwin_vbox_new(dlg,false);
//      fcwin_box_add_static(vbox,headline,ID_NOTIFY_HEADLINE,SS_LEFT,
//			   false,false,5);
//      
//      edit=CreateWindow("EDIT","", ES_WANTRETURN | ES_READONLY | WS_VSCROLL | ES_LEFT | ES_MULTILINE | ES_AUTOVSCROLL | WS_CHILD | WS_VISIBLE,0,0,30,30,dlg,
//      (HMENU)ID_NOTIFY_MSG,freecivhinst,null); 
//      fcwin_box_add_win(vbox,edit,true,true,5);
//      buf=convertnl2crnl(lines);
//      SendMessage(GetDlgItem(dlg,ID_NOTIFY_MSG),
//		  WM_SETFONT,(WPARAM) font_12courier, MAKELPARAM(true,0)); 
//
//      SetWindowText(GetDlgItem(dlg,ID_NOTIFY_MSG),buf);
//      fcwin_box_add_button(vbox,"Close",ID_NOTIFY_CLOSE,WS_VISIBLE,
//			   false,false,5);
//      fcwin_set_box(dlg,vbox);
//      free(buf);
//      ShowWindow(dlg,SW_SHOWNORMAL);
//    }
//}
//
//
///**************************************************************************
// WS_GROUP seems not to work here
//**************************************************************************/
//static void update_radio_buttons(int id)
//{  
//  /* if (id!=selected_leader_sex)  */
//  CheckRadioButton(races_dlg,ID_RACESDLG_MALE,
//		   ID_RACESDLG_FEMALE,selected_leader_sex);
//  /*  if (id!=selected_style) */
//  CheckRadioButton(races_dlg,ID_RACESDLG_STYLE_BASE,
//		   ID_RACESDLG_STYLE_BASE+b_s_num-1,selected_style);
//  if (id!=(selected_nation+ID_RACESDLG_NATION_BASE)) 
//    CheckRadioButton(races_dlg,ID_RACESDLG_NATION_BASE,
//		     ID_RACESDLG_NATION_BASE+game.playable_nation_count-1,
//		     selected_nation+ID_RACESDLG_NATION_BASE);   
//}
//
//
//
///**************************************************************************
//
//**************************************************************************/
//static void update_nation_info()
//{
//  SetWindowText(races_class, 
//		get_nation_by_idx(selected_nation).class);
//  SetWindowText(races_legend,
//		get_nation_by_idx(selected_nation).legend);
//}
//
//
///**************************************************************************
//
//**************************************************************************/
//static void select_random_race(HWND hWnd)
//{
//  selected_nation=myrand(game.playable_nation_count);
//  update_nation_info();
//  update_radio_buttons(0);
//}
//
///**************************************************************************
//
//**************************************************************************/
//static void select_random_leader(HWND hWnd)
//{
//  int j,leader_num;
//  leader leaders = get_nation_leaders(selected_nation, &leader_num);
//
//  ComboBox_ResetContent(GetDlgItem(hWnd,ID_RACESDLG_LEADER));
//  for (j = 0; j < leader_num; j++) {
//    ComboBox_AddString(GetDlgItem(hWnd,ID_RACESDLG_LEADER), leaders[j].name);
//  }
//  selected_leader=myrand(leader_num);
//  ComboBox_SetCurSel(GetDlgItem(hWnd,ID_RACESDLG_LEADER),selected_leader);
//  SetWindowText(GetDlgItem(hWnd,ID_RACESDLG_LEADER),
//		leaders[selected_leader].name);
//  if (leaders[selected_leader].is_male) {
//    selected_leader_sex=ID_RACESDLG_MALE;
//    CheckRadioButton(hWnd,ID_RACESDLG_MALE,ID_RACESDLG_FEMALE,
//		     ID_RACESDLG_MALE);
//  } else {
//    selected_leader_sex=ID_RACESDLG_FEMALE;
//    CheckRadioButton(hWnd,ID_RACESDLG_MALE,ID_RACESDLG_FEMALE,
//		     ID_RACESDLG_FEMALE);
//  }
//}
//
///**************************************************************************
//
//**************************************************************************/
//static void do_select(HWND hWnd)
//{
//  boolean is_male = (selected_leader_sex == ID_RACESDLG_MALE);
//  int city_style = city_style_idx[selected_style - ID_RACESDLG_STYLE_BASE];
//  char name[MAX_LEN_NAME];
// 
//  ComboBox_GetText(GetDlgItem(hWnd,ID_RACESDLG_LEADER),
//		   name,MAX_LEN_NAME);
// 
//  if (name.length() == 0) {
//    append_output_window("You must type a legal name.");
//    return;
//  }
//  dsend_packet_nation_select_req(&aconnection, selected_nation, is_male,
//				 name, city_style);
//}
//
//
///**************************************************************************
//
//**************************************************************************/
//static LONG CALLBACK racesdlg_proc(HWND hWnd,
//				   UINT message,
//				   WPARAM wParam,
//				   LPARAM lParam)  
//{
//  static boolean name_edited;
//  int id;
//  switch(message)
//    {
//    case WM_CREATE:
//      name_edited=false;
//      break;
//    case WM_CLOSE:
//      popdown_races_dialog();
//      break;
//    case WM_DESTROY:
//      races_dlg=null;
//      break;
//    case WM_SIZE:
//      break;
//    case WM_GETMINMAXINFO:
//      break;
//    case WM_COMMAND:
//      id=LOWORD(wParam);
//      switch(id)
//	{ 
//	case ID_RACESDLG_MALE:
//	case ID_RACESDLG_FEMALE:
//	    selected_leader_sex=id;	    
//	    update_radio_buttons(id);
//	  break;
//	case ID_RACESDLG_LEADER:
//	  switch(HIWORD(wParam)) {
//	  case CBN_SELCHANGE:
//	    name_edited=false;
//	    break; 
//	  case CBN_EDITCHANGE:
//	    name_edited=true;
//	    break;
//	  }
//	  break;
//	case ID_RACESDLG_QUIT:
//	  exit(EXIT_SUCCESS);
//	  break;
//	case ID_RACESDLG_DISCONNECT:
//	  popdown_races_dialog();
//	  disconnect_from_server();
//	  break;
//	case ID_RACESDLG_OK:
//	  do_select(hWnd);
//	  break;
//	default:
//	
//	  if ((id>=ID_RACESDLG_STYLE_BASE)&&
//	      (id<ID_RACESDLG_STYLE_BASE+b_s_num)) {
//	    selected_style=id;
//	    update_radio_buttons(id);
//	  } else if ((id>=ID_RACESDLG_NATION_BASE)&&
//		     (id<ID_RACESDLG_NATION_BASE+game.playable_nation_count)) {
//	    selected_nation=id-ID_RACESDLG_NATION_BASE;
//	    update_nation_info();
//	    if (!name_edited) {
//	      select_random_leader(hWnd);
//	    }
//	    update_radio_buttons(id);
//    
//	  }
//
//	  break;
//	}
//      break;
//    default:
//      return DefWindowProc(hWnd,message,wParam,lParam);      
//    }
//  return 0;
//}
//
//
///****************************************************************
//...
//*****************************************************************/
//static int cmp_func(final void * a_p, final void * b_p)
//{
//  return strcmp(get_nation_name((*(int *)a_p)-ID_RACESDLG_NATION_BASE),
//                get_nation_name((*(int *)b_p)-ID_RACESDLG_NATION_BASE));
//}
//
//public static final int NATIONS_PER_ROW = 5;
///**************************************************************************
//
//**************************************************************************/
//static void add_nations(fcwin_box vbox)
//{
//  int i;
//  fcwin_box hbox;
//  fcwin_box vboxes[NATIONS_PER_ROW];
//  struct genlist nation_list;
//  genlist_link myiter;
//  genlist_init(&nation_list);
//  for(i=0; i<game.playable_nation_count; i++) { 
//    /* Don't use a null pointer */
//    genlist_insert(&nation_list,(void *)(i+ID_RACESDLG_NATION_BASE),0);
//  }
//  genlist_sort(&nation_list,cmp_func);
//  for(i=0;i<NATIONS_PER_ROW;i++) {  
//    vboxes[i]=fcwin_vbox_new(races_dlg,true);
//  }
//  myiter = nation_list.head_link;
//  i=0;
//  for(;ITERATOR_PTR(myiter);ITERATOR_NEXT(myiter),i++) {
//    int id;
//    if (i>=NATIONS_PER_ROW)
//      i=0;
//    id=(int)ITERATOR_PTR(myiter);
//    fcwin_box_add_radiobutton(vboxes[i],
//			      get_nation_name(id-ID_RACESDLG_NATION_BASE),
//			      id,0,false,false,0);			      
//  }
//  genlist_unlink_all(&nation_list);
//  hbox=fcwin_hbox_new(races_dlg,true);
//  for(i=0;i<NATIONS_PER_ROW;i++)
//    fcwin_box_add_box(hbox,vboxes[i],true,true,10);
//  fcwin_box_add_box(vbox,hbox,true,true,10);
//}
//
///**************************************************************************
//
//**************************************************************************/
//void popup_races_dialog()
//{
//  fcwin_box vbox;
//  fcwin_box hbox;
//  fcwin_box grp_box;
//  int i;
//  races_dlg=fcwin_create_layouted_window(racesdlg_proc,
//					 "What nation will you be?",
//					 WS_OVERLAPPEDWINDOW,
//					 CW_USEDEFAULT,
//					 CW_USEDEFAULT,
//					 root_window,
//					 null,
//					 REAL_CHILD,
//					 null);
//  vbox=fcwin_vbox_new(races_dlg,false);
//  grp_box=fcwin_vbox_new(races_dlg,false);
//  fcwin_box_add_groupbox(vbox,"Select nation and name",
//			 grp_box,WS_GROUP,true,true,5);
//  add_nations(grp_box);
//  
//  hbox = fcwin_hbox_new(races_dlg, false);
//  fcwin_box_add_static(hbox, "Class:", 0, SS_LEFT, false,false, 0);
//  
//  races_class = fcwin_box_add_static(hbox, "content", 0, SS_LEFT, true, true,5);
//  
//  fcwin_box_add_box(vbox, hbox, false, false, 0);
// 
//  grp_box = fcwin_vbox_new(races_dlg, false);
//  races_legend = fcwin_box_add_static(grp_box, "content\n\n\nc", SS_LEFT,
//				      0, false, false, 5);
//  fcwin_box_add_groupbox(vbox, "Description", grp_box,
//			 0, false, false, 5);
//
//  grp_box=fcwin_vbox_new(races_dlg,false);  
//  fcwin_box_add_groupbox(vbox,"Your leader name",grp_box,
//			 0,false,false,5);
//  fcwin_box_add_combo(grp_box, 10, ID_RACESDLG_LEADER, CBS_DROPDOWN
//		      | WS_VSCROLL, false, false, 0);
//  grp_box=fcwin_hbox_new(races_dlg,true);
//  fcwin_box_add_groupbox(vbox,"Select your sex",grp_box,WS_GROUP,
//			 false,false,5);
//  fcwin_box_add_radiobutton(grp_box,"Male",ID_RACESDLG_MALE,0,
//			    true,true,25);
//  fcwin_box_add_radiobutton(grp_box,"Female",ID_RACESDLG_FEMALE,
//			    WS_GROUP,true,true,25);
// 
//  grp_box=fcwin_vbox_new(races_dlg,false);
//  fcwin_box_add_groupbox(vbox,"Select your city style",grp_box,WS_GROUP,
//			 false,false,5);
//  hbox=fcwin_hbox_new(races_dlg,true);
//  for(i=0,b_s_num=0; i<game.styles_count && i<64; i++) {
//    if(city_styles[i].techreq == A_NONE) {
//      city_style_idx[b_s_num] = i;
//      city_style_ridx[i] = b_s_num;
//      b_s_num++;
//    }
//  }
//  for(i=0; i<b_s_num; i++) {
//    fcwin_box_add_radiobutton(hbox,city_styles[city_style_idx[i]].name,
//			      ID_RACESDLG_STYLE_BASE+i,0,true,true,25);
//    if (i%2) {
//      fcwin_box_add_box(grp_box,hbox,false,false,0);
//      hbox=fcwin_hbox_new(races_dlg,true);
//    }
//  }
//  /* if (i%2)
//     fcwin_box_add_box(grp_box,hbox,false,false,20); */
//  fcwin_box_add_box(grp_box,hbox,false,false,0);
//  hbox=fcwin_hbox_new(races_dlg,true);
//  fcwin_box_add_button(hbox,"Ok",ID_RACESDLG_OK,WS_GROUP,true,true,25);
//  fcwin_box_add_button(hbox,"Disconnect",ID_RACESDLG_DISCONNECT,0,
//		       true,true,25);
//  fcwin_box_add_button(hbox,"Quit",ID_RACESDLG_QUIT,0,true,true,25);
//  fcwin_box_add_box(vbox,hbox,false,false,5);
//  selected_style=ID_RACESDLG_STYLE_BASE;
//  CheckRadioButton(races_dlg,
//		   ID_RACESDLG_STYLE_BASE,ID_RACESDLG_STYLE_BASE+b_s_num-1,
//		   ID_RACESDLG_STYLE_BASE);
//  fcwin_set_box(races_dlg, vbox);
//  select_random_race(races_dlg);
//  select_random_leader(races_dlg);
//  ShowWindow(races_dlg,SW_SHOWNORMAL);
//}
//
///**************************************************************************
//
//**************************************************************************/
//void
//popdown_races_dialog()
//{
//  DestroyWindow(races_dlg);
//  SetFocus(root_window);
//}
//
///**************************************************************************
//
//**************************************************************************/
//static int number_of_columns(int n)
//{
//#if 0
//  /* This would require libm, which isn't worth it for this one little
//   * function.  Since the number of units is limited to 100 already, the ifs
//   * work fine.  */
//  double sqrt(); double ceil();
//  return ceil(sqrt((double)n/5.0));
//#else
//  if(n<=5) return 1;
//  else if(n<=20) return 2;
//  else if(n<=45) return 3;
//  else if(n<=80) return 4;
//  else return 5;
//#endif
//}
//
///**************************************************************************
//
//**************************************************************************/
//static int number_of_rows(int n)
//{
//  int c=number_of_columns(n);
//  return (n+c-1)/c;
//}                                                                          
//
///**************************************************************************
//
//**************************************************************************/     
//static void popdown_unit_select_dialog()
//{
//  if (unit_select_main)
//    DestroyWindow(unit_select_main);
//}
//
///**************************************************************************
//
//**************************************************************************/
//static LONG APIENTRY unitselect_proc(HWND hWnd, UINT message,
//				     UINT wParam, LONG lParam)
//{
//  int id;
//  int i;
//  switch(message)
//    {
//    case WM_CLOSE:
//      popdown_unit_select_dialog();
//      return true;
//      break;
//    case WM_DESTROY:
//      for (i=0;i<unit_select_no;i++) {
//	DeleteObject(unit_select_bitmaps[i]);
//      }
//      unit_select_main=null;
//      break;
//    case WM_COMMAND:
//      id=LOWORD(wParam);
//      switch(id)
//	{
//	case UNITSELECT_CLOSE:
//	  break;
//	case UNITSELECT_READY_ALL:
//	  for(i=0; i<unit_select_no; i++) {
//	    unit punit = player_find_unit_by_id(game.player_ptr,
//							unit_select_ids[i]);
//	    if(punit) {
//	      set_unit_focus(punit);
//	    }
//	  }  
//	  break;
//	default:
//	  id-=UNITSELECT_UNITS_BASE;
//	  if ((id>=0)&&(id<100))
//	    {
//	      unit punit=player_find_unit_by_id(game.player_ptr,
//							unit_select_ids[id]);
//	      if(punit && punit.owner == game.player_idx) {
//		set_unit_focus(punit);
//	      }   
//	    }
//	  break;
//	}
//      popdown_unit_select_dialog();
//      break;
//    default:
//      return DefWindowProc(hWnd,message,wParam,lParam);
//    }
//  return false;
//}
//
//
///**************************************************************************
//
//**************************************************************************/
//boolean unitselect_init(HINSTANCE hInstance)
//{
//  
//  HANDLE hMemory;
//  LPWNDCLASS pWndClass;
//  boolean bSuccess;
//  hMemory=LocalAlloc(LPTR,sizeof(WNDCLASS));
//  if (!hMemory)
//    {
//      return(false);
//    }
//  pWndClass=(LPWNDCLASS)LocalLock(hMemory);
//  pWndClass.style=0;
//  pWndClass.lpfnWndProc=(WNDPROC) unitselect_proc;
//  pWndClass.hIcon=null;
//  pWndClass.hCursor=LoadCursor(null,IDC_ARROW);
//  pWndClass.hInstance=hInstance;
//  pWndClass.hbrBackground=CreateSolidBrush(GetSysColor(4));
//  pWndClass.lpszClassName=(LPSTR)"freecivunitselect";
//  pWndClass.lpszMenuName=(LPSTR)null;
//  bSuccess=RegisterClass(pWndClass);
//  LocalUnlock(hMemory);
//  LocalFree(hMemory);
//  return bSuccess;
//}
//
///**************************************************************************
//
//**************************************************************************/
//void
//popup_unit_select_dialog(tile ptile)
//{
//  int i,n,r,c;
//  int max_width,max_height;
//  int win_height,win_width;
//  char buffer[512];
//  char *but1="Ready all";
//  char *but2="Close";
//  HDC hdc;
//  POINT pt;
//  RECT rc,rc2;
//  HBITMAP old;
//  HDC unitsel_dc;
//  unit unit_list[unit_list_size(&ptile.units)];
//  
//  fill_tile_unit_list(ptile, unit_list);
//  
//  /* unit select box might already be open; if so, close it */
//  if (unit_select_main) {
//    popdown_unit_select_dialog ();
//  }
//  
//  GetCursorPos(&pt);
//  if (!(unit_select_main=CreateWindow("freecivunitselect",
//				      "Unit Selection",
//				      WS_POPUP | WS_CAPTION | WS_SYSMENU,
//				      pt.x+20,
//				      pt.y+20,
//				      40,40,
//				      null,
//				      null,
//				      freecivhinst,
//				      null)))
//    return;
//  hdc=GetDC(unit_select_main);
//  unitsel_dc=CreateCompatibleDC(null);
//  n=unit_list_size(&ptile.units);
//  r=number_of_rows(n);
//  c=number_of_columns(n);
//  max_width=0;
//  max_height=0;
//  for(i=0;i<n;i++)
//    {
//      unit punit = unit_list[i];
//      unit_type punittemp=punit.unit_type();
//      city pcity;
//      unit_select_ids[i]=punit.id;
//      pcity=player_find_city_by_id(game.player_ptr, punit.homecity);
//      buffer = util.my_snprintf( "%s(%s)\n%s",
//		  punittemp.name,
//		  pcity ? pcity.name : "",
//		  unit_activity_text(punit));
//      DrawText(hdc,buffer,buffer.length(),&rc,DT_CALCRECT);
//      if ((rc.right-rc.left)>max_width)
//	max_width=rc.right-rc.left;
//      if ((rc.bottom-rc.top)>max_height)
//	max_height=rc.bottom-rc.top;
//      unit_select_bitmaps[i]=CreateCompatibleBitmap(hdc,UNIT_TILE_WIDTH,
//						    UNIT_TILE_HEIGHT);
//    }
//  ReleaseDC(unit_select_main,hdc);
//  old=SelectObject(unitsel_dc,unit_select_bitmaps[0]);
//  max_width+=UNIT_TILE_WIDTH;
//  max_width+=4;
//  if (max_height<UNIT_TILE_WIDTH)
//    {
//      max_height=UNIT_TILE_HEIGHT;
//    }
//  max_height+=4;
//  for (i=0;i<n;i++)
//    {
//      struct canvas canvas_store = {unitsel_dc, null};
//      unit punit=unit_list[i];
//      unit_type punittemp=punit.unit_type();
//      city pcity;
//      pcity=player_find_city_by_id(game.player_ptr, punit.homecity);
//      buffer = util.my_snprintf( "%s(%s)\n%s",
//		  punittemp.name,
//		  pcity ? pcity.name : "",
//		  unit_activity_text(punit));
//      unit_select_labels[i]=CreateWindow("STATIC",buffer,
//					 WS_CHILD | WS_VISIBLE | SS_LEFT,
//					 (i/r)*max_width+UNIT_TILE_WIDTH,
//					 (i%r)*max_height,
//					 max_width-UNIT_TILE_WIDTH,
//					 max_height,
//					 unit_select_main,
//					 null,
//					 freecivhinst,
//					 null);
//      SelectObject(unitsel_dc,unit_select_bitmaps[i]);
//      BitBlt(unitsel_dc,0,0,UNIT_TILE_WIDTH,UNIT_TILE_HEIGHT,null,
//	     0,0,WHITENESS);
//      put_unit(punit,&canvas_store,0,0);
//      unit_select_but[i]=CreateWindow("BUTTON",null,
//				      WS_CHILD | WS_VISIBLE | BS_BITMAP,
//				      (i/r)*max_width,
//				      (i%r)*max_height,
//				      UNIT_TILE_WIDTH,
//				      UNIT_TILE_HEIGHT,
//				      unit_select_main,
//				      (HMENU)(UNITSELECT_UNITS_BASE+i),
//				      freecivhinst,
//				      null);
//      SendMessage(unit_select_but[i],BM_SETIMAGE,(WPARAM)0,
//		  (LPARAM)unit_select_bitmaps[i]);
//      
//    }
//  SelectObject(unitsel_dc,old);
//  DeleteDC(unitsel_dc);
//  unit_select_no=i;
//  win_height=r*max_height;
//  win_width=c*max_width;
//  hdc=GetDC(unit_select_main);
//  DrawText(hdc,but1,but1.length(),&rc,DT_CALCRECT);
//  if (rc.right-rc.left>(win_width/2-4))
//    win_width=(rc.right-rc.left+4)*2;
//  DrawText(hdc,but2,but2.length(),&rc,DT_CALCRECT);
//  if (rc.right-rc.left>(win_width/2-4))
//    win_width=(rc.right-rc.left+4)*2;
//  ReleaseDC(unit_select_main,hdc);
//  unit_select_close=CreateWindow("BUTTON",but2,
//				 WS_CHILD | WS_VISIBLE | BS_CENTER,
//				 win_width/2,win_height+2,
//				 win_width/2-2,rc.bottom-rc.top,
//				 unit_select_main,
//				 (HMENU)UNITSELECT_CLOSE,
//				 freecivhinst,
//				 null);
//  unit_select_ready=CreateWindow("BUTTON",but1,
//				 WS_CHILD | WS_VISIBLE | BS_CENTER,
//				 2,win_height+2,
//				 win_width/2-2,rc.bottom-rc.top,
//				 unit_select_main,
//				 (HMENU)UNITSELECT_READY_ALL,
//				 freecivhinst,
//				 null);
//  win_height+=rc.bottom-rc.top;
//  win_height+=4;
//  GetWindowRect(unit_select_main,&rc);
//  GetClientRect(unit_select_main,&rc2);
//  win_height+=rc.bottom-rc.top-rc2.bottom+rc2.top;
//  win_width+=rc.right-rc.left+rc2.left-rc2.right;
//  MoveWindow(unit_select_main,rc.left,rc.top,win_width,win_height,true);
//  ShowWindow(unit_select_main,SW_SHOWNORMAL);
//  
//}
//
///**************************************************************************
//
//**************************************************************************/
//void races_toggles_set_sensitive(boolean *nations_used)
//{
//  int i;
//
//  for (i = 0; i < game.playable_nation_count; i++) {
//    EnableWindow(GetDlgItem(races_dlg, ID_RACESDLG_NATION_BASE + i), true);
//  }
//
//  for (i = 0; i < game.playable_nation_count; i++) {
//    int nation = i;
//
//    if (!nations_used[i]) {
//      continue;
//    }
//
//    util.freelog(Log.LOG_DEBUG, "  [%d]: %d", i, nation);
//
//    EnableWindow(GetDlgItem(races_dlg, ID_RACESDLG_NATION_BASE + nation),
//		 false);
//
//    if (nation == selected_nation) {
//      select_random_race(races_dlg);
//    }
//  }
//}
//
///****************************************************************
//...
//*****************************************************************/
//static void revolution_callback_yes(HWND w, void * data)
//{
//  if ((int)data == -1) {
//    start_revolution();
//  } else {
//    set_government_choice((int)data);
//  }
//  destroy_message_dialog(w);
//}
// 
///****************************************************************
//...
//*****************************************************************/
//static void revolution_callback_no(HWND w, void * data)
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
//  if (game.player_ptr.revolution_finishes < game.turn) {
//    popup_message_dialog(null, "Revolution!",
//			 "You say you wanna revolution?",
//			 "_Yes",revolution_callback_yes, government,
//			 "_No",revolution_callback_no, 0,
//			 0);
//  } else {
//    if (government == -1) {
//      start_revolution();
//    } else {
//      set_government_choice(government);
//    }
//  }
//}
// 
///****************************************************************
//...
//*****************************************************************/
//static void caravan_establish_trade_callback(HWND w, void * data)
//{
//  dsend_packet_unit_establish_trade(&aconnection, caravan_unit_id);
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
//static void caravan_help_build_wonder_callback(HWND w, void * data)
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
//static void caravan_keep_moving_callback(HWND w, void * data)
//{
//  destroy_message_dialog(w);
//  caravan_dialog = 0;
//  process_caravan_arrival(null);
//}
// 
// 
// 
///****************************************************************
//...
//*****************************************************************/  
//void
//popup_caravan_dialog(unit punit,
//                          city phomecity, city pdestcity)
//{
//  char buf[128];
//  boolean can_establish, can_trade;
//  
//  buf = util.my_snprintf(
//              "Your caravan from %s reaches the city of %s.\nWhat now?",
//              phomecity.name, pdestcity.name);
// 
//  caravan_city_id=pdestcity.id; /* callbacks need these */
//  caravan_unit_id=punit.id;
// 
//  can_trade = can_cities_trade(phomecity, pdestcity);
//  can_establish = can_trade
//  		  && can_establish_trade_route(phomecity, pdestcity);
//  
//  caravan_dialog=popup_message_dialog(null,
//                           /*"caravandialog"*/"Your Caravan Has Arrived",
//                           buf,
//                           (can_establish ? "Establish _Traderoute" :
//  			   "Enter Marketplace"),caravan_establish_trade_callback, 0,
//                           "Help build _Wonder",caravan_help_build_wonder_callback, 0,
//                           "_Keep moving",caravan_keep_moving_callback, 0,
//                           0);
// 
//  if (!can_trade)
//    {
//      message_dialog_button_set_sensitive(caravan_dialog,0,false);
//    }
// 
//  if(!unit_can_help_build_wonder(punit, pdestcity))
//    {
//      message_dialog_button_set_sensitive(caravan_dialog,1,false);
//    } 
//  
//}
//
//boolean caravan_dialog_is_open()
//{
//  return BOOL_VAL(caravan_dialog);          
//}
///****************************************************************
//...
//*****************************************************************/
//static void diplomat_investigate_callback(HWND w, void * data)
//{
//  destroy_message_dialog(w);
//  diplomat_dialog_open=0;
// 
//  if(find_unit_by_id(diplomat_id) &&
//     (find_city_by_id(diplomat_target_id))) {
//    request_diplomat_action(DIPLOMAT_INVESTIGATE, diplomat_id,
//			    diplomat_target_id, 0);
//  }
// 
//  process_diplomat_arrival(null, 0);
//}
///****************************************************************
//...
//*****************************************************************/
//static void diplomat_steal_callback(HWND w, void * data)
//{
//  destroy_message_dialog(w);
//  diplomat_dialog_open=0;
// 
//  if(find_unit_by_id(diplomat_id) &&
//     find_city_by_id(diplomat_target_id)) {
//    request_diplomat_action(DIPLOMAT_STEAL, diplomat_id,
//			    diplomat_target_id, 0);
//  }
// 
//  process_diplomat_arrival(null, 0);
//}
///****************************************************************
//...
//*****************************************************************/
//static void diplomat_sabotage_callback(HWND w, void * data)
//{
//  destroy_message_dialog(w);
//  diplomat_dialog_open=0;
// 
//  if(find_unit_by_id(diplomat_id) &&
//     find_city_by_id(diplomat_target_id)) {
//    request_diplomat_action(DIPLOMAT_SABOTAGE, diplomat_id,
//			    diplomat_target_id, -1);
//  }
// 
//  process_diplomat_arrival(null, 0);
//}                  
///*****************************************************************/
//static void diplomat_embassy_callback(HWND w, void * data)
//{
//  destroy_message_dialog(w);
//  diplomat_dialog_open=0;
// 
//  if(find_unit_by_id(diplomat_id) &&
//     (find_city_by_id(diplomat_target_id))) {
//    request_diplomat_action(DIPLOMAT_EMBASSY, diplomat_id,
//			    diplomat_target_id, 0);
//  }
// 
//  process_diplomat_arrival(null, 0);
//}    
///****************************************************************
//...
//*****************************************************************/
//static void spy_sabotage_unit_callback(HWND w, void * data)
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
//static void spy_poison_callback(HWND w, void * data)
//{
//  destroy_message_dialog(w);
//  diplomat_dialog_open=0;
//
//  if(find_unit_by_id(diplomat_id) &&
//     (find_city_by_id(diplomat_target_id))) {
//    request_diplomat_action(SPY_POISON, diplomat_id, diplomat_target_id, 0);
//  }
//
//  process_diplomat_arrival(null, 0);
//}
//
///****************************************************************
//...
//*****************************************************************/
//static void create_advances_list(player pplayer,
//                                player pvictim, HWND lb)
//{
//  int i, j;
//  j = 0;
//  advance_type[j] = -1;
//  
//  if (pvictim) { /* you don't want to know what lag can do -- Syela */
//    
//    for(i=A_FIRST; i<game.num_tech_types; i++) {
//      if(get_invention(pvictim, i)==TECH_KNOWN && 
//         (get_invention(pplayer, i)==TECH_UNKNOWN || 
//          get_invention(pplayer, i)==TECH_REACHABLE)) {
//	ListBox_AddString(lb,advances[i].name);
//        advance_type[j++] = i;
//      }
//    }
//    
//    if(j > 0) {
//      ListBox_AddString(lb,"At Spy's Discretion");
//      advance_type[j++] = game.num_tech_types;
//    }
//  }
//  if(j == 0) {
//    ListBox_AddString(lb,"NONE");
//    j++;
//  }
//  
//  
//}
//
//public static final int ID_SPY_LIST = 100;
///****************************************************************
//
//*****************************************************************/
//static LONG CALLBACK spy_tech_proc(HWND dlg,UINT message,WPARAM wParam,
//				   LPARAM lParam)
//{
//  switch(message)
//    {
//    case WM_CREATE:
//    case WM_SIZE:
//    case WM_GETMINMAXINFO:
//      break;
//    case WM_DESTROY:
//      spy_tech_dialog=null;
//      process_diplomat_arrival(null, 0);
//      break;
//    case WM_CLOSE:
//      DestroyWindow(dlg);
//      break;
//    case WM_COMMAND:
//      switch(LOWORD(wParam)) {
//      case ID_SPY_LIST:
//	if (ListBox_GetCurSel(GetDlgItem(dlg,ID_SPY_LIST))!=LB_ERR) {
//	  EnableWindow(GetDlgItem(dlg,IDOK),true);
//	}
//	break;
//      case IDOK:
//	{
//	  int steal_advance;
//	  steal_advance=ListBox_GetCurSel(GetDlgItem(dlg,ID_SPY_LIST));
//	  if (steal_advance==LB_ERR)
//	    break;
//	  steal_advance=advance_type[steal_advance];
//	  if(find_unit_by_id(diplomat_id) && 
//	     find_city_by_id(diplomat_target_id)) { 
//	    request_diplomat_action(DIPLOMAT_STEAL, diplomat_id,
//				    diplomat_target_id, steal_advance);
//	  }
//	  
//	  
//	}
//      case IDCANCEL:
//	DestroyWindow(dlg);
//	break;
//      }
//      break;
//    default:
//      return DefWindowProc(dlg,message,wParam,lParam);
//    }
//  return 0;
//}
//
///****************************************************************
//...
//*****************************************************************/
//static void spy_steal_popup(HWND w, void * data)
//{
//  city pvcity = find_city_by_id(diplomat_target_id);
//  player pvictim = null;
//
//  if(pvcity)
//    pvictim = city_owner(pvcity);
//
///* it is concievable that pvcity will not be found, because something
//has happened to the city during latency.  Therefore we must initialize
//pvictim to null and account for !pvictim in create_advances_list. -- Syela */
//  
//  destroy_message_dialog(w);
//  diplomat_dialog_open=0;
//  if(!spy_tech_dialog){
//    HWND lb;
//    fcwin_box hbox;
//    fcwin_box vbox;
//    spy_tech_dialog=fcwin_create_layouted_window(spy_tech_proc,
//						 "Steal Technology",
//						 WS_OVERLAPPEDWINDOW,
//						 CW_USEDEFAULT,
//						 CW_USEDEFAULT,
//						 root_window,
//						 null,
//						 REAL_CHILD,
//						 null);
//    hbox=fcwin_hbox_new(spy_tech_dialog,true);
//    vbox=fcwin_vbox_new(spy_tech_dialog,false);
//    fcwin_box_add_static(vbox,"Select Advance to Steal",
//			 0,SS_LEFT,false,false,5);
//    lb=fcwin_box_add_list(vbox,5,ID_SPY_LIST,WS_VSCROLL,true,true,5);
//    fcwin_box_add_button(hbox,"Close",IDCANCEL,0,true,true,10);
//    fcwin_box_add_button(hbox,"Steal",IDOK,0,true,true,10);
//    EnableWindow(GetDlgItem(spy_tech_dialog,IDOK),false);
//    fcwin_box_add_box(vbox,hbox,false,false,5);
//    create_advances_list(game.player_ptr, pvictim, lb);
//    fcwin_set_box(spy_tech_dialog,vbox);
//    ShowWindow(spy_tech_dialog,SW_SHOWNORMAL);
//  }
// 
//}
//
///****************************************************************
// Requests up-to-date list of improvements, the return of
// which will trigger the popup_sabotage_dialog() function.
//*****************************************************************/
//static void spy_request_sabotage_list(HWND w, void * data)
//{
//  destroy_message_dialog(w);
//  diplomat_dialog_open=0;
//
//  if(find_unit_by_id(diplomat_id) &&
//     (find_city_by_id(diplomat_target_id))) {
//    request_diplomat_action(SPY_GET_SABOTAGE_LIST, diplomat_id,
//			    diplomat_target_id, 0);
//  }
//}
//
//
///**************************************************************************
//
//**************************************************************************/
//static void diplomat_bribe_yes_callback(HWND w, void * data)
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
//static void diplomat_bribe_no_callback(HWND w, void * data)
//{
//  destroy_message_dialog(w);
//}
//
//
//
///****************************************************************
//...  Ask the server how much the bribe is
//*****************************************************************/
//static void diplomat_bribe_callback(HWND w, void * data)
//{
//
//  destroy_message_dialog(w);
//  
//  if (find_unit_by_id(diplomat_id)
//      && find_unit_by_id(diplomat_target_id)) { 
//    dsend_packet_unit_bribe_inq(&aconnection, diplomat_target_id);
//   }
//
//}
// 
///****************************************************************
//...
//*****************************************************************/
//void popup_bribe_dialog(unit punit)
//{
//  char buf[128];
//  if (unit_flag(punit, F_UNBRIBABLE)) {
//    popup_message_dialog(root_window, "Ooops...",
//                         "This unit cannot be bribed!",
//                         diplomat_bribe_no_callback, 0, 0);
//  } else if(game.player_ptr.economic.gold>=punit.bribe_cost) {
//    buf = util.my_snprintf(
//                "Bribe unit for %d gold?\nTreasury contains %d gold.", 
//                punit.bribe_cost, game.player_ptr.economic.gold);
//    popup_message_dialog(root_window, /*"diplomatbribedialog"*/("Bribe Enemy Unit"
//), buf,
//                        "_Yes", diplomat_bribe_yes_callback, 0,
//                        "_No", diplomat_bribe_no_callback, 0, 0);
//  } else {
//    buf = util.my_snprintf(
//                ("Bribing the unit costs %d gold.\n" +
//                  "Treasury contains %d gold."), 
//                punit.bribe_cost, game.player_ptr.economic.gold);
//    popup_message_dialog(root_window, /*"diplomatnogolddialog"*/
//	    	"Traitors Demand Too Much!", buf, "Darn",
//		diplomat_bribe_no_callback, 0, 0);
//  }
//}
//
///****************************************************************
//
//****************************************************************/
//static void create_improvements_list(player pplayer,
//                                    city pcity, HWND lb)
//{
//  int j;
//  j=0;
//  ListBox_AddString(lb,"City Production");
//  improvement_type[j++] = -1;
//  
//  impr_type_iterate(i) {
//    if (get_improvement_type(i).sabotage > 0) {
//      ListBox_AddString(lb,get_impr_name_ex(pcity,i));
//      improvement_type[j++] = i;
//    }  
//  } impr_type_iterate_end;
//
//  if(j > 1) {
//    ListBox_AddString(lb,"At Spy's Discretion");
//    improvement_type[j++] = B_LAST;
//    } else {
//    improvement_type[0] = B_LAST; 
//    /* fake "discretion", since must be production */
//  }
//  
//  
//}
//
///****************************************************************
//
//*****************************************************************/
//static LONG CALLBACK spy_sabotage_proc(HWND dlg,UINT message,WPARAM wParam,
//				       LPARAM lParam)
//{
//  switch(message)
//    {
//    case WM_CREATE:
//    case WM_SIZE:
//    case WM_GETMINMAXINFO:
//      break;
//    case WM_DESTROY:
//      spy_sabotage_dialog=null;
//      process_diplomat_arrival(null, 0);
//      break;
//    case WM_CLOSE:
//      DestroyWindow(dlg);
//      break;
//    case WM_COMMAND:
//      switch(LOWORD(wParam)) {
//      case ID_SPY_LIST:
//	if (ListBox_GetCurSel(GetDlgItem(dlg,ID_SPY_LIST))!=LB_ERR) {
//	  EnableWindow(GetDlgItem(dlg,IDOK),true);
//	}
//	break;
//      case IDOK:
//	{
//	  int sabotage_improvement;
//	  sabotage_improvement=ListBox_GetCurSel(GetDlgItem(dlg,ID_SPY_LIST));
//	  if (sabotage_improvement==LB_ERR)
//	    break;
//	  sabotage_improvement=improvement_type[sabotage_improvement];
//	  if(find_unit_by_id(diplomat_id) && 
//	     find_city_by_id(diplomat_target_id)) { 
//	    request_diplomat_action(DIPLOMAT_SABOTAGE, diplomat_id,
//				    diplomat_target_id,
//				    sabotage_improvement + 1);
//	  }
//	  
//	}
//      case IDCANCEL:
//	DestroyWindow(dlg);
//	break;
//      }
//      break;
//    default:
//      return DefWindowProc(dlg,message,wParam,lParam);
//    }
//  return 0;
//}
//
///****************************************************************
// Pops-up the Spy sabotage dialog, upon return of list of
// available improvements requested by the above function.
//*****************************************************************/
//void popup_sabotage_dialog(city pcity)
//{
//  
//  if(!spy_sabotage_dialog){
//    HWND lb;
//    fcwin_box hbox;
//    fcwin_box vbox;
//    spy_sabotage_dialog=fcwin_create_layouted_window(spy_sabotage_proc,
//						     "Sabotage Improvements",
//						     WS_OVERLAPPEDWINDOW,
//						     CW_USEDEFAULT,
//						     CW_USEDEFAULT,
//						     root_window,
//						     null,
//						     REAL_CHILD,
//						     null);
//    hbox=fcwin_hbox_new(spy_sabotage_dialog,true);
//    vbox=fcwin_vbox_new(spy_sabotage_dialog,false);
//    fcwin_box_add_static(vbox,"Select Improvement to Sabotage",
//			 0,SS_LEFT,false,false,5);
//    lb=fcwin_box_add_list(vbox,5,ID_SPY_LIST,WS_VSCROLL,true,true,5);
//    fcwin_box_add_button(hbox,"Close",IDCANCEL,0,true,true,10);
//    fcwin_box_add_button(hbox,"Sabotage",IDOK,0,true,true,10);
//    EnableWindow(GetDlgItem(spy_sabotage_dialog,IDOK),false);
//    fcwin_box_add_box(vbox,hbox,false,false,5);
//    create_improvements_list(game.player_ptr, pcity, lb);
//    fcwin_set_box(spy_sabotage_dialog,vbox);
//    ShowWindow(spy_sabotage_dialog,SW_SHOWNORMAL);
//  }
//}
//
///****************************************************************
//...
//*****************************************************************/
//static void diplomat_incite_yes_callback(HWND w, void * data)
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
//static void diplomat_incite_no_callback(HWND w, void * data)
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
//static void diplomat_incite_callback(HWND w, void * data)
//{
//  destroy_message_dialog(w);
//  diplomat_dialog_open = 0;
//
//  if (find_unit_by_id(diplomat_id) && find_city_by_id(diplomat_target_id)) {
//    dsend_packet_city_incite_inq(&aconnection, diplomat_target_id);
//  }
//}
//
///****************************************************************
//Popup the yes/no dialog for inciting, since we know the cost now
//*****************************************************************/
//void popup_incite_dialog(city pcity)
//{
//  char buf[128];
//
//  if (pcity.incite_revolt_cost == INCITE_IMPOSSIBLE_COST) {
//    buf = util.my_snprintf( "You can't incite a revolt in %s.",
//		pcity.name);
//    popup_message_dialog(root_window, "City can't be incited!", buf,
//			 "Darn", diplomat_incite_no_callback, 0, 0);
//  } else if (game.player_ptr.economic.gold >= pcity.incite_revolt_cost) {
//    buf = util.my_snprintf(
//		"Incite a revolt for %d gold?\nTreasury contains %d gold.", 
//		pcity.incite_revolt_cost, game.player_ptr.economic.gold);
//   diplomat_target_id = pcity.id;
//   popup_message_dialog(root_window, /*"diplomatrevoltdialog"*/"Incite a Revolt!", buf,
//		       "_Yes", diplomat_incite_yes_callback, 0,
//		       "_No", diplomat_incite_no_callback, 0, 0);
//  } else {
//    buf = util.my_snprintf(
//		("Inciting a revolt costs %d gold.\n" +
//		  "Treasury contains %d gold."), 
//		pcity.incite_revolt_cost, game.player_ptr.economic.gold);
//   popup_message_dialog(root_window, /*"diplomatnogolddialog"*/"Traitors Demand Too Much!", buf,
//		       "Darn", diplomat_incite_no_callback, 0, 
//		       0);
//  }
//}
//
//
///****************************************************************
//...
//*****************************************************************/
//static void diplomat_cancel_callback(HWND w, void * data)
//{
//  destroy_message_dialog(w);
//  diplomat_dialog_open=0;
//
//  process_diplomat_arrival(null, 0);
//}
//
//
///****************************************************************
//...
//*****************************************************************/
//void popup_diplomat_dialog(unit punit, tile ptile)
//{
//  city pcity;
//  unit ptunit;
//  HWND shl;
//  char buf[128];
//
//  diplomat_id=punit.id;
//
//  if ((pcity = map_get_city(ptile))){
//    /* Spy/Diplomat acting against a city */
//
//    diplomat_target_id=pcity.id;
//    buf = util.my_snprintf(
//		"Your %s has arrived at %s.\nWhat is your command?",
//		unit_name(punit.type), pcity.name);
//
//    if(!unit_flag(punit, F_SPY)){
//      shl=popup_message_dialog(root_window, /*"diplomatdialog"*/
//			       " Choose Your Diplomat's Strategy", buf,
//         		     "Establish _Embassy", diplomat_embassy_callback, 0,
//         		     "_Investigate City", diplomat_investigate_callback, 0,
//         		     "_Sabotage City", diplomat_sabotage_callback, 0,
//         		     "Steal _Technology", diplomat_steal_callback, 0,
//         		     "Incite a _Revolt", diplomat_incite_callback, 0,
//         		     "_Cancel", diplomat_cancel_callback, 0,
//         		     0);
//      
//      if (!diplomat_can_do_action(punit, DIPLOMAT_EMBASSY, ptile))
//       message_dialog_button_set_sensitive(shl,0,false);
//      if (!diplomat_can_do_action(punit, DIPLOMAT_INVESTIGATE, ptile))
//       message_dialog_button_set_sensitive(shl,1,false);
//      if (!diplomat_can_do_action(punit, DIPLOMAT_SABOTAGE, ptile))
//       message_dialog_button_set_sensitive(shl,2,false);
//      if (!diplomat_can_do_action(punit, DIPLOMAT_STEAL, ptile))
//       message_dialog_button_set_sensitive(shl,3,false);
//      if (!diplomat_can_do_action(punit, DIPLOMAT_INCITE, ptile))
//       message_dialog_button_set_sensitive(shl,4,false);
//    }else{
//       shl = popup_message_dialog(root_window, /*"spydialog"*/
//		"Choose Your Spy's Strategy", buf,
// 		"Establish _Embassy", diplomat_embassy_callback, 0,
//		"_Investigate City", diplomat_investigate_callback, 0,
//		"_Poison City", spy_poison_callback,0,
// 		"Industrial _Sabotage", spy_request_sabotage_list, 0,
// 		"Steal _Technology", spy_steal_popup, 0,
// 		"Incite a _Revolt", diplomat_incite_callback, 0,
// 		"_Cancel", diplomat_cancel_callback, 0,
//		0);
// 
//      if (!diplomat_can_do_action(punit, DIPLOMAT_EMBASSY, ptile))
//       message_dialog_button_set_sensitive(shl,0,false);
//      if (!diplomat_can_do_action(punit, DIPLOMAT_INVESTIGATE, ptile))
//       message_dialog_button_set_sensitive(shl,1,false);
//      if (!diplomat_can_do_action(punit, SPY_POISON, ptile))
//       message_dialog_button_set_sensitive(shl,2,false);
//      if (!diplomat_can_do_action(punit, DIPLOMAT_SABOTAGE, ptile))
//       message_dialog_button_set_sensitive(shl,3,false);
//      if (!diplomat_can_do_action(punit, DIPLOMAT_STEAL, ptile))
//       message_dialog_button_set_sensitive(shl,4,false);
//      if (!diplomat_can_do_action(punit, DIPLOMAT_INCITE, ptile))
//       message_dialog_button_set_sensitive(shl,5,false);
//     }
//
//    diplomat_dialog_open=1;
//   }else{ 
//     if ((ptunit = unit_list_get(&ptile.units, 0))){
//       /* Spy/Diplomat acting against a unit */ 
//       
//       diplomat_target_id=ptunit.id;
// 
//       shl=popup_message_dialog(root_window, /*"spybribedialog"*/"Subvert Enemy Unit",
//                              (!unit_flag(punit, F_SPY))?
// 			      "Sir, the diplomat is waiting for your command":
// 			      "Sir, the spy is waiting for your command",
// 			      "_Bribe Enemy Unit", diplomat_bribe_callback, 0,
// 			      "_Sabotage Enemy Unit", spy_sabotage_unit_callback, 0,
// 			      "_Cancel", diplomat_cancel_callback, 0,
// 			      0);
//        
//       if (!diplomat_can_do_action(punit, DIPLOMAT_BRIBE, ptile))
//        message_dialog_button_set_sensitive(shl,0,false);
//       if (!diplomat_can_do_action(punit, SPY_SABOTAGE_UNIT, ptile))
//        message_dialog_button_set_sensitive(shl,1,false);
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
///**************************************************************************
//
//**************************************************************************/
//static LONG CALLBACK pillage_proc(HWND dlg,UINT message,
//				  WPARAM wParam,LPARAM lParam)
//{
//  int id;
//  switch(message) {
//  case WM_DESTROY:
//    is_showing_pillage_dialog=false;
//    break;
//  case WM_CLOSE:
//    DestroyWindow(dlg);
//    break;
//  case WM_GETMINMAXINFO:
//  case WM_SIZE:
//    break;
//  case WM_COMMAND:
//    id=LOWORD(wParam);
//    if (id==IDCANCEL) {
//      DestroyWindow(dlg);
//    } else if (id>=ID_PILLAGE_BASE) {
//      unit punit=find_unit_by_id(unit_to_use_to_pillage);
//      if (punit) {
//	request_new_unit_activity_targeted(punit,
//					   ACTIVITY_PILLAGE,
//					   id-ID_PILLAGE_BASE);
//	DestroyWindow(dlg);
//      }
//    }
//    break;
//  default:
//    return DefWindowProc(dlg,message,wParam,lParam);
//  }
//  return 0;
//}
//
///**************************************************************************
//
//**************************************************************************/
//void popup_pillage_dialog(unit punit,
//			  enum int may_pillage)
//{
//  HWND dlg;
//  fcwin_box vbox;
//  if (!is_showing_pillage_dialog) {
//    is_showing_pillage_dialog = true;   
//    unit_to_use_to_pillage = punit.id;
//    dlg=fcwin_create_layouted_window(pillage_proc,"What To Pillage",
//				     WS_OVERLAPPEDWINDOW,
//				     CW_USEDEFAULT,CW_USEDEFAULT,
//				     root_window,null,
//				     REAL_CHILD,
//				     null);
//    vbox=fcwin_vbox_new(dlg,false);
//    fcwin_box_add_static(vbox,"Select what to pillage:",0,SS_LEFT,
//			 false,false,10);
//    while(may_pillage != S_NO_SPECIAL) {
//      enum int what = get_preferred_pillage(may_pillage);
//
//      fcwin_box_add_button(vbox,map_get_infrastructure_text(what),
//			   ID_PILLAGE_BASE+what,0,true,false,5);
//      may_pillage &= (~(what | map_get_infrastructure_prerequisite (what)));
//    }
//    fcwin_box_add_button(vbox,"Cancel",IDCANCEL,0,true,false,5);
//    fcwin_set_box(dlg,vbox);
//    ShowWindow(dlg,SW_SHOWNORMAL);
//  }
//}
///**************************************************************************
//
//**************************************************************************/
//HWND popup_message_dialog(HWND parent, char *dialogname,
//			  final String text)
//{
//  int idcount;
//  va_list args;
//  void (*fcb)(HWND, void *);
//  void *data;
//  char *name;
//  message_dialog md;
//  message_dialog_button mb;
//  md=fc_malloc(sizeof(struct message_dialog));
//  if (!(md.main=fcwin_create_layouted_window(msgdialog_proc,
//					      dialogname,
//					      WS_OVERLAPPED,
//					      CW_USEDEFAULT,
//					      CW_USEDEFAULT,
//					      parent,
//					      null,
//					      REAL_CHILD,
//					      null)))
//    {
//      free(md);
//      return null;
//    }
//  md.firstbutton=null;
//  md.vbox=fcwin_vbox_new(md.main,false);
//  va_start (args,text);
//  md.label=fcwin_box_add_static_default(md.vbox,text,0,SS_LEFT);
//  idcount=POPUP_MESSAGEDLG_IDBASE;
//  while ((name=va_arg(args, char *)))
//    {
//      char *replacepos;
//      char converted_name[512];
//      converted_name = name;
//      replacepos=converted_name;
//      while ((replacepos=strchr(replacepos,'_')))
//	{
//	  replacepos[0]='&';          /* replace _ by & */
//	  replacepos++;
//	}
//      fcb=va_arg(args,void *);
//      data=va_arg(args,void *);
//      mb=fc_malloc(sizeof(struct message_dialog_button));
//      mb.buttwin=fcwin_box_add_button_default(md.vbox,converted_name,
//					       idcount,0);
//      mb.id=idcount;
//      mb.data=data;
//      mb.callback=fcb;
//      idcount++;
//      mb.next=md.firstbutton;
//      md.firstbutton=mb;
//    }
//  fcwin_set_user_data(md.main,md);
//  fcwin_set_box(md.main,md.vbox);
// 
//  ShowWindow(md.main,SW_SHOWNORMAL);
//  return md.main;
//}
//
//
//void destroy_message_dialog(HWND dlg)
//{
//  DestroyWindow(dlg);
//}
//
//void message_dialog_button_set_sensitive(HWND dlg,int id,int state)
//{
//  
//  EnableWindow(GetDlgItem(dlg, POPUP_MESSAGEDLG_IDBASE+id),state);
//  
//}
//
//static LONG APIENTRY msgdialog_proc (
//                           HWND hWnd,
//                           UINT message,
//                           UINT wParam,
//                           LONG lParam)
//{
//  message_dialog md;
//  message_dialog_button mb;
//  int id;
//  switch (message)
//    {
//    case WM_CLOSE:
//      DestroyWindow(hWnd);
//      break;
//    case WM_DESTROY:      {
//      message_dialog md;
//      message_dialog_button mb;
//      md=(message_dialog )fcwin_get_user_data(hWnd);
//      mb=md.firstbutton;
//      while (mb)
//	{
//	  message_dialog_button mbprev;
//	  
//	  mbprev=mb;
//	  mb=mb.next;
//	  free(mbprev);
//	}
//      free(md);
//    }
//      break;
//    case WM_SIZE:
//      break;
//    case WM_GETMINMAXINFO:
//      break;
//    case WM_COMMAND:
//      id=LOWORD(wParam);
//      md=(message_dialog )fcwin_get_user_data(hWnd);
//      mb=md.firstbutton;
//      while(mb)
//	{
//	  if (mb.id==id)
//	    {
//	      mb.callback(hWnd,mb.data);
//	      break;
//	    }
//	  mb=mb.next;
//	}
//    default:
//      return DefWindowProc(hWnd,message,wParam,lParam);
//    }
//  return (0);  
//  
//}
}