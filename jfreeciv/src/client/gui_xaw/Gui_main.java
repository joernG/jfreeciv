package client.gui_xaw;

public class Gui_main{

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
//
//***********************************************************************/
//
//#ifdef HAVE_CONFIG_H
//#include <config.h>
//#endif
//
//#include <assert.h>
//#include <stdio.h>
//#include <stdlib.h>
//
//#include <X11/Intrinsic.h>
//#include <X11/StringDefs.h>
//
//#include <X11/Xaw/Command.h>
//#include <X11/Xaw/Form.h>
//#include <X11/Xaw/Box.h>
//#include <X11/Xaw/Label.h>
//#include <X11/Xaw/AsciiText.h>
//#include <X11/Xaw/Scrollbar.h>
//#include <X11/Xaw/MenuButton.h>
//#include <X11/Xaw/SimpleMenu.h>
//#include <X11/Xaw/Paned.h>
//#include <X11/Xatom.h>
//
//#include "canvas.h"
//#include "pixcomm.h"
//
//#include "fciconv.h"
//#include "fcintl.h"
//#include "Game.game.h"
//#include "government.h"
//#include "log.h"
//#include "map.h"
//#include "support.h"
//#include "version.h"
//
//#include "actions.h"
//#include "civclient.h"
//#include "climisc.h"
//#include "clinet.h"
//#include "colors.h"
//#include "control.h"
//#include "dialogs.h"
//#include "graphics.h"
//#include "gui_stuff.h"		/* I_SW() */
//#include "helpdata.h"		/* boot_help_texts() */
//#include "mapview.h"
//#include "menu.h"
//#include "optiondlg.h"
//#include "options.h"
//#include "resources.h"
//#include "tilespec.h"
//
//#include "gui_main.h"
//
//#include "freeciv.ico"
//
//final String client_string = "gui-xaw";
//
//client_option gui_options[] = {
//  /* None. */
//};
//final int num_gui_options = ARRAY_SIZE(gui_options);
//
//static AppResources appResources;
//
///* ids of the units icons in information display: (or 0) */
//static int unit_ids[MAX_NUM_UNITS_BELOW];  
//
//static void setup_widgets();
//void fill_econ_label_pixmaps();
//void fill_unit_below_pixmaps();
//
///**************************************************************************
//...
//**************************************************************************/
//XtResource resources[] = {
//    { "GotAppDefFile", "gotAppDefFile", XtRBoolean, sizeof(Boolean),
//      XtOffset(AppResources *,gotAppDefFile), XtRImmediate, (XtPointer)false},
//    { "version", "Version", XtRString, sizeof(String),
//      XtOffset(AppResources *,version), XtRImmediate, (XtPointer)false},
//#ifdef UNUSED
//    { "log", "Log", XtRString, sizeof(String),
//      XtOffset(AppResources *,logfile), XtRImmediate, (XtPointer)false},
//    { "name", "name", XtRString, sizeof(String),
//      XtOffset(AppResources *,name), XtRImmediate, (XtPointer)false},
//    { "port", "Port", XtRInt, sizeof(int),
//      XtOffset(AppResources *,port), XtRImmediate, (XtPointer)false},
//    { "server", "Server", XtRString, sizeof(String),
//      XtOffset(AppResources *,server), XtRImmediate, (XtPointer)false},
//    { "metaserver", "Metaserver", XtRString, sizeof(String),
//      XtOffset(AppResources *,metaserver), XtRImmediate, (XtPointer)false},
//    { "logLevel", "LogLevel", XtRString, sizeof(String),
//      XtOffset(AppResources *,loglevel_str), XtRImmediate, (XtPointer)false},
//    { "showHelp", "ShowHelp", XtRBoolean, sizeof(Boolean),
//      XtOffset(AppResources *,showHelp), XtRImmediate, (XtPointer)false},
//    { "showVersion", "ShowVersion", XtRBoolean, sizeof(Boolean),
//      XtOffset(AppResources *,showVersion), XtRImmediate, (XtPointer)false},
//    { "tileset", "TileSet", XtRString, sizeof(String),
//      XtOffset(AppResources *,tileset), XtRImmediate, (XtPointer)false},
//#endif
//};
//
///**************************************************************************
//...
//**************************************************************************/
//#ifdef UNUSED
//static XrmOptionDescRec cmd_options[] = {
///* { "-help",    ".showHelp",    XrmoptionNoArg,  (XPointer)"true" },*/
// { "-log",     ".log",         XrmoptionSepArg, (XPointer)"true" },
// { "-name",    ".name",        XrmoptionSepArg, (XPointer)"true" },
// { "-port",    ".port",        XrmoptionSepArg, (XPointer)"true" },
// { "-server",  ".server",      XrmoptionSepArg, (XPointer)"true" },
// { "-metaserver",".metaserver",XrmoptionSepArg, (XPointer)"true" },
// { "-debug",   ".logLevel",    XrmoptionSepArg, (XPointer)"true" },
// { "-tiles",   ".tileset",     XrmoptionSepArg, (XPointer)"true" },
///* { "-version", ".showVersion", XrmoptionNoArg,  (XPointer)"true" },*/
///* { "--help",    ".showHelp",    XrmoptionNoArg,  (XPointer)"true" },*/
// { "--log",     ".log",         XrmoptionSepArg, (XPointer)"true" },
// { "--name",    ".name",        XrmoptionSepArg, (XPointer)"true" },
// { "--port",    ".port",        XrmoptionSepArg, (XPointer)"true" },
// { "--debug",   ".logLevel",    XrmoptionSepArg, (XPointer)"true" },
// { "--server",  ".server",      XrmoptionSepArg, (XPointer)"true" },
// { "--metaserver",".metaserver",XrmoptionSepArg, (XPointer)"true" },
// { "--tiles",   ".tileset",     XrmoptionSepArg, (XPointer)"true" }
///* { "--version", ".showVersion", XrmoptionNoArg,  (XPointer)"true" }*/
//};
//#endif
//
///**************************************************************************/
//
//static void end_turn_callback(Widget w, XtPointer client_data,
//			      XtPointer call_data);
//
//static void timer_callback(XtPointer client_data, XtIntervalId *id);
//
///**************************************************************************/
//
//struct game_data {
//  int year;
//  int population;
//  int researchedpoints;
//  int money;
//  int tax, luxurary, research;
//};
//
///**************************************************************************/
//
//Display	*display;
//int display_depth;
//int screen_number;
//enum Display_color_type display_color_type;
//XtAppContext app_context;
//
///* this GC will be the default one all thru freeciv */
//GC civ_gc; 
//
///* This is for drawing border lines */
//GC border_line_gc; 
//
///* and this one is used for filling with the bg color */
//GC fill_bg_gc;
//GC fill_tile_gc;
//Pixmap gray50,gray25;
//
///* overall font GC                                    */
//GC font_gc;
//XFontSet main_font_set;
///* productions font GC                                */
//GC prod_font_gc;
//XFontSet prod_font_set;
//
//Widget toplevel, main_form, menu_form, below_menu_form, left_column_form;
//Widget bottom_form;
//Widget map_form;
//Widget map_canvas;
//Widget overview_canvas;
//Widget map_vertical_scrollbar, map_horizontal_scrollbar;
//Widget inputline_text, outputwindow_text;
//Widget econ_label[10];
//Widget turn_done_button;
//Widget info_command;
//Widget bulb_label, sun_label, flake_label, government_label, timeout_label;
//Widget unit_info_label;
//Widget unit_pix_canvas;
//Widget unit_below_canvas[MAX_NUM_UNITS_BELOW];
//Widget main_vpane;
//Pixmap unit_below_pixmap[MAX_NUM_UNITS_BELOW];
//Widget more_arrow_label;
//Window root_window;
//
//XtInputId x_input_id;
//XtIntervalId x_interval_id;
//Atom wm_delete_window;
//
//
//#ifdef UNUSED
///**************************************************************************
//...
//**************************************************************************/
///* This is used below in ui_main(), in commented out code. */
//static int myerr(Display *p, XErrorEvent *e)
//{
//  puts("error");
//  return 0;
//}
//#endif
//
///**************************************************************************
//  Print extra usage information, including one line help on each option,
//  to stderr.
//**************************************************************************/
//static void print_usage(final String argv0)
//{
//  /* add client-specific usage information here */
//  fc_fprintf(stderr, "Report bugs to <%s>.\n", BUG_EMAIL_ADDRESS);
//}
//
///**************************************************************************
//...
//**************************************************************************/
//static void parse_options(int argc, char **argv)
//{
//  int i;
//
//  i = 1;
//  while (i < argc)
//  {
//    if (is_option("--help", argv[i]))
//    {
//      print_usage(argv[0]);
//      exit(EXIT_SUCCESS);
//    }
//    i += 1;
//  }
//}
//
///**************************************************************************
//...
//**************************************************************************/
//static Boolean toplevel_work_proc(XtPointer client_data)
//{
//  /* This will cause the connect dialog to pop-up.
//     We do it here so that the main window exists when that happens,
//     so that the connect dialog can position itself relative to it. */
//  set_client_state(CLIENT_PRE_GAME_STATE);
//  return (true);
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void ui_init()
//{
//  init_character_encodings(null, true);
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void ui_main(int argc, char *argv[])
//{
//  int i;
//  Pixmap icon_pixmap; 
//
//  parse_options(argc, argv);
//
//  /* include later - pain to see the warning at every run */
//  XtSetLanguageProc(null, null, null);
//  
//  toplevel = XtVaAppInitialize(
//	       &app_context,               /* Application context */
//	       "Freeciv",                  /* application class name */
//#ifdef UNUSED
//	       cmd_options, XtNumber(cmd_options),
//#else
//	       null, 0,
//#endif
//	                                   /* command line option list */
//	       &argc, argv,                /* command line args */
//	       &fallback_resources[1],     /* for missing app-defaults file */
//	       XtNallowShellResize, true,
//	       null);              
//
//  XtGetApplicationResources(toplevel, &appResources, resources,
//                            XtNumber(resources), null, 0);
//
///*  XSynchronize(display, 1); 
//  XSetErrorHandler(myerr);*/
//
//  if(appResources.version==null)  {
//    util.freelog(LOG_FATAL, "No version number in resources.");
//    util.freelog(LOG_FATAL, ("You probably have an old (circa V1.0)" +
//			 " Freeciv resource file somewhere."));
//    exit(EXIT_FAILURE);
//  }
//
//  /* TODO: Use capabilities here instead of version numbers */
//  if (0 != strncmp(appResources.version, VERSION_STRING,
//		   appResources.version.length())) {
//    util.freelog(LOG_FATAL, "Game version does not match Resource version.");
//    util.freelog(LOG_FATAL, "Game version: %s - Resource version: %s", 
//	    VERSION_STRING, appResources.version);
//    util.freelog(LOG_FATAL, ("You might have an old Freeciv resourcefile" +
//			 " in /usr/lib/X11/app-defaults"));
//    exit(EXIT_FAILURE);
//  }
//  
//  if(!appResources.gotAppDefFile) {
//    util.freelog(Log.LOG_NORMAL, "Using fallback resources - which is OK");
//  }
//
//  display = XtDisplay(toplevel);
//  screen_number=XScreenNumberOfScreen(XtScreen(toplevel));
//  display_depth=DefaultDepth(display, screen_number);
//  root_window=DefaultRootWindow(display);
//
//  display_color_type=get_visual(); 
//  
//  if(display_color_type!=COLOR_DISPLAY) {
//    util.freelog(LOG_FATAL, "Only color displays are supported for now...");
//    /*    exit(EXIT_FAILURE); */
//  }
//  
//  icon_pixmap = XCreateBitmapFromData(display,
//				      RootWindowOfScreen(XtScreen(toplevel)),
//				      freeciv_bits,
//				      freeciv_width, freeciv_height);
//  XtVaSetValues(toplevel, XtNiconPixmap, icon_pixmap, null);
//
//  init_color_system();
//
//  {
//    XGCValues values;
//    char **missing_charset_list_return;
//    int missing_charset_count_return;
//    char *def_string_return;
//
//    values.graphics_exposures = false;
//    civ_gc = XCreateGC(display, root_window, GCGraphicsExposures, &values);
//
//    free(city_names_font);
//    city_names_font = ("-*-*-*-*-*--14-*");
//
//    free(city_productions_font_name);
//    city_productions_font_name = ("-*-*-*-*-*--14-*");
//
//    main_font_set = XCreateFontSet(display, city_names_font,
//	&missing_charset_list_return,
//	&missing_charset_count_return,
//	&def_string_return);
//    if(!main_font_set) {
//      util.freelog(LOG_FATAL, "Unable to open fontset: %s",
//	  city_names_font);
//      exit(EXIT_FAILURE);
//    }
//    for(i = 0; i < missing_charset_count_return; i++) {
//      util.freelog(Log.LOG_ERROR, "Font for charset %s is lacking",
//	  missing_charset_list_return[i]);
//    }
//    values.foreground = colors_standard[COLOR_STD_WHITE];
//    values.background = colors_standard[COLOR_STD_BLACK];
//    font_gc= XCreateGC(display, root_window, 
//		       GCForeground|GCBackground|GCGraphicsExposures, 
//		       &values);
//
//    prod_font_set = XCreateFontSet(display, city_productions_font_name,
//	&missing_charset_list_return,
//	&missing_charset_count_return,
//	&def_string_return);
//    if(!prod_font_set) {
//      util.freelog(LOG_FATAL, "Unable to open fontset: %s",
//	  city_productions_font_name);
//      exit(EXIT_FAILURE);
//    }
//    for(i = 0; i < missing_charset_count_return; i++) {
//      util.freelog(Log.LOG_ERROR, "Font for charset %s is lacking",
//	  missing_charset_list_return[i]);
//    }
//    values.foreground = colors_standard[COLOR_STD_WHITE];
//    values.background = colors_standard[COLOR_STD_BLACK];
//    prod_font_gc= XCreateGC(display, root_window,
//			    GCForeground|GCBackground|GCGraphicsExposures,
//			    &values);
//
//    values.line_width = BORDER_WIDTH;
//    values.line_style = LineOnOffDash;
//    values.cap_style = CapNotLast;
//    values.join_style = JoinMiter;
//    values.fill_style = FillSolid;
//    border_line_gc = XCreateGC(display, root_window,
//			       GCGraphicsExposures|GCLineWidth|GCLineStyle
//			       |GCCapStyle|GCJoinStyle|GCFillStyle, &values);
//
//    values.foreground = 0;
//    values.background = 0;
//    fill_bg_gc= XCreateGC(display, root_window, 
//			  GCForeground|GCBackground|GCGraphicsExposures,
//			  &values);
//
//    values.fill_style=FillStippled;
//    fill_tile_gc= XCreateGC(display, root_window, 
//    			    GCForeground|GCBackground|GCFillStyle|GCGraphicsExposures,
//			    &values);
//  }
//
//  {
//    char d1[]={0x03,0x0c,0x03,0x0c};
//    char d2[]={0x08,0x02,0x08,0x02};
//    gray50 = XCreateBitmapFromData(display, root_window, d1, 4, 4);
//    gray25 = XCreateBitmapFromData(display, root_window, d2, 4, 4);
//  }
//  
//  /* 135 below is rough value (could be more intelligent) --dwp */
//  num_units_below = 135 / UNIT_TILE_WIDTH;
//  num_units_below = Math.min(num_units_below,MAX_NUM_UNITS_BELOW);
//  num_units_below = MAX(num_units_below,1);
//  
//  /* do setup_widgets before loading the rest of graphics to ensure that
//     setup_widgets() has enough colors available:  (on 256-colour systems)
//  */
//  setup_widgets();
//  tilespec_load_tiles();
//  load_intro_gfx();
//  load_cursors();
//
//  XtSetKeyboardFocus(bottom_form, inputline_text);
//  XtSetKeyboardFocus(below_menu_form, map_canvas);
//
//  InitializeActions(app_context);
//
//  /* Do this outside setup_widgets() so after tiles are loaded */
//
//  fill_econ_label_pixmaps();
//		
//  XtAddCallback(map_horizontal_scrollbar, XtNjumpProc, 
//		scrollbar_jump_callback, null);
//  XtAddCallback(map_vertical_scrollbar, XtNjumpProc, 
//		scrollbar_jump_callback, null);
//  XtAddCallback(map_horizontal_scrollbar, XtNscrollProc, 
//		scrollbar_scroll_callback, null);
//  XtAddCallback(map_vertical_scrollbar, XtNscrollProc, 
//		scrollbar_scroll_callback, null);
//  XtAddCallback(turn_done_button, XtNcallback, end_turn_callback, null);
//
//  XtAppAddWorkProc(app_context, toplevel_work_proc, null);
//
//  XtRealizeWidget(toplevel);
//
//  x_interval_id = XtAppAddTimeOut(app_context, TIMER_INTERVAL,
//				  timer_callback, null);
//
//  init_mapcanvas_and_overview();
//
//  fill_unit_below_pixmaps();
//
//  set_indicator_icons(0, 0, 0, 0);
//
//  wm_delete_window = XInternAtom(XtDisplay(toplevel), "WM_DELETE_WINDOW", 0);
//  XSetWMProtocols(display, XtWindow(toplevel), &wm_delete_window, 1);
//  XtOverrideTranslations(toplevel,
//    XtParseTranslationTable ("<Message>WM_PROTOCOLS: msg-quit-freeciv()"));
//
//  XtSetSensitive(toplevel, false);
//
//  XtAppMainLoop(app_context);
//}
//
//
///**************************************************************************
//  Callack for when user clicks one of the unit icons on left hand side
//  (units on same square as current unit).  Use unit_ids[] data and change
//  focus to clicked unit.
//**************************************************************************/
//static void unit_icon_callback(Widget w, XtPointer client_data,
//			       XtPointer call_data) 
//{
//  unit punit;
//  int i = (size_t)client_data;
//
//  assert(i>=0 && i<num_units_below);
//  if (unit_ids[i] == 0) /* no unit displayed at this place */
//    return;
//  punit=Game.find_unit_by_id(unit_ids[i]);
//  if(punit) { /* should always be true at this point */
//    if (punit.owner == Game.game.player_idx) {  /* may be non-true if alliance */
//      set_unit_focus(punit);
//    }
//  }
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void setup_widgets()
//{
//  long i;
//  int econ_label_count=10, econ_label_space=1;
//
//  main_form = XtVaCreateManagedWidget("mainform", formWidgetClass, 
//				      toplevel, 
//				      null);   
//
//  menu_form = XtVaCreateManagedWidget("menuform", formWidgetClass,	
//				      main_form,        
//				      null);	        
//  setup_menus(menu_form); 
//
///*
// main_vpane= XtVaCreateManagedWidget("mainvpane", 
//				      panedWidgetClass, 
//				      main_form,
//				      null);
//  
//  below_menu_form = XtVaCreateManagedWidget("belowmenuform", 
//					    formWidgetClass, 
//					    main_vpane,
//					    null);
//*/
//  below_menu_form = XtVaCreateManagedWidget("belowmenuform", 
//					    formWidgetClass, 
//					    main_form,
//					    null);
//
//  left_column_form = XtVaCreateManagedWidget("leftcolumnform", 
//					     formWidgetClass, 
//					     below_menu_form, 
//					     null);
//
//  map_form = XtVaCreateManagedWidget("mapform", 
//				     formWidgetClass, 
//				     below_menu_form, 
//				     null);
//
//  bottom_form = XtVaCreateManagedWidget("bottomform", 
//					formWidgetClass, 
//					/*main_vpane,*/ main_form, 
//					null);
//  
//  overview_canvas = XtVaCreateManagedWidget("overviewcanvas", 
//					    xfwfcanvasWidgetClass,
//					    left_column_form,
//					    "exposeProc", 
//					    (XtArgVal)overview_canvas_expose,
//					    "exposeProcData", 
//					    (XtArgVal)null,
//					    null);
//  
//  info_command = XtVaCreateManagedWidget("infocommand", 
//				       commandWidgetClass, 
//				       left_column_form, 
//				       XtNfromVert, 
//				       (XtArgVal)overview_canvas,
//				       null);   
//
//
//  /* Don't put the citizens in here yet because not loaded yet */
//  for(i=0;i<econ_label_count;i++)  {
//    econ_label[i] = XtVaCreateManagedWidget("econlabels",
//					    commandWidgetClass,
//					    left_column_form,
//					    XtNwidth, SMALL_TILE_WIDTH,
//					    XtNheight, SMALL_TILE_HEIGHT,
//					    i?XtNfromHoriz:null, 
//					    i?econ_label[i-1]:null,
//					    XtNhorizDistance, econ_label_space,
//					    null);  
//  }
//  
//  bulb_label = XtVaCreateManagedWidget("bulblabel", 
//				       labelWidgetClass,
//				       left_column_form,
//				       null);
//
//  sun_label = XtVaCreateManagedWidget("sunlabel", 
//				      labelWidgetClass, 
//				      left_column_form,
//				      null);
//
//  flake_label = XtVaCreateManagedWidget("flakelabel", 
//					labelWidgetClass, 
//					left_column_form,
//					null);
//
//  government_label = XtVaCreateManagedWidget("governmentlabel", 
//					    labelWidgetClass, 
//					    left_column_form,
//					    null);
//
//  timeout_label = XtVaCreateManagedWidget("timeoutlabel", 
//					  labelWidgetClass, 
//					  left_column_form,
//					  null);
//
//
//  turn_done_button =
//    I_LW(XtVaCreateManagedWidget("turndonebutton", 
//				 commandWidgetClass,
//				 left_column_form,
//				 XtNwidth, econ_label_count*
//						(SMALL_TILE_WIDTH+econ_label_space),
//				 null));
//
//  
//  unit_info_label = XtVaCreateManagedWidget("unitinfolabel", 
//					    labelWidgetClass, 
//					    left_column_form, 
//					    null);
//
//  unit_pix_canvas = XtVaCreateManagedWidget("unitpixcanvas", 
//					   pixcommWidgetClass,
//					   left_column_form, 
//					   XtNwidth, UNIT_TILE_WIDTH,
//					   XtNheight, UNIT_TILE_HEIGHT,
//					   null);
//
//  for(i=0; i<num_units_below; i++) {
//    char unit_below_name[32];
//    unit_below_name = util.my_snprintf(
//		"unitbelowcanvas%ld", i);
//    unit_below_canvas[i] = XtVaCreateManagedWidget(unit_below_name,
//						   pixcommWidgetClass,
//						   left_column_form, 
//						   XtNwidth,
//						   UNIT_TILE_WIDTH,
//						   XtNheight,
//						   UNIT_TILE_HEIGHT,
//						   null);
//    XtAddCallback(unit_below_canvas[i], XtNcallback, unit_icon_callback,
//		  (XtPointer)i);  
//  }
//
//  more_arrow_label =
//    XtVaCreateManagedWidget("morearrowlabel", 
//			    labelWidgetClass,
//			    left_column_form,
//			    XtNfromHoriz,
//			    (XtArgVal)unit_below_canvas[num_units_below-1],
//			    null);
//
//  map_vertical_scrollbar = XtVaCreateManagedWidget("mapvertiscrbar", 
//						   scrollbarWidgetClass, 
//						   map_form,
//						   null);
//
//  map_canvas = XtVaCreateManagedWidget("mapcanvas", 
//				       xfwfcanvasWidgetClass,
//				       map_form,
//				       "exposeProc", 
//				       (XtArgVal)map_canvas_expose,
//				       "exposeProcData", 
//				       (XtArgVal)null,
//				       null);
//
//  map_horizontal_scrollbar = XtVaCreateManagedWidget("maphorizscrbar", 
//						     scrollbarWidgetClass, 
//						     map_form,
//						     null);
//
//
//
//  outputwindow_text= I_SW(XtVaCreateManagedWidget("outputwindowtext", 
//						  asciiTextWidgetClass, 
//						  bottom_form,
//						  null));
//
//
//  inputline_text= XtVaCreateManagedWidget("inputlinetext", 
//					  asciiTextWidgetClass, 
//					  bottom_form,
//					  null);
//
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void xaw_ui_exit()
//{
//  tilespec_free_tiles();
//  ui_exit();
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void main_show_info_popup(XEvent *event)
//{
//  XButtonEvent *ev=(XButtonEvent *)event;
//  if(ev.button==Button1) {
//    Widget  p;
//    Position x, y;
//    Dimension w, h;
//    char buf[512];
//
//    buf = util.my_snprintf(
//		("%s People\n" +
//		  "Year: %s Turn: %d\n" +
//		  "Gold: %d\n" +
//		  "Net Income: %d\n" +
//		  "Tax:%d Lux:%d Sci:%d\n" +
//		  "Researching %s: %d/%d\n" +
//		  "Government: %s"),
//		population_to_text(civ_population(Game.game.player_ptr)),
//		Shared.textyear(Game.game.year), Game.game.turn,
//		Game.game.player_ptr.economic.gold,
//		player_get_expected_income(Game.game.player_ptr),
//		Game.game.player_ptr.economic.tax,
//		Game.game.player_ptr.economic.luxury,
//		Game.game.player_ptr.economic.science,
//		(Game.game.player_ptr.research.researching == A_UNSET) ?
//		  advances[A_NONE].name :
//		  advances[Game.game.player_ptr.research.researching].name,
//		Game.game.player_ptr.research.bulbs_researched,
//		total_bulbs_required(Game.game.player_ptr),
//		get_government_name(Game.game.player_ptr.government));
//
//    p=XtCreatePopupShell("popupinfo", 
//			 overrideShellWidgetClass, 
//			 info_command, null, 0);
//
//    XtAddCallback(p,XtNpopdownCallback,destroy_me_callback,null);
//
//    XtVaCreateManagedWidget("fullinfopopuplabel", 
//			    labelWidgetClass,
//			    p,
//			    XtNlabel, buf,
//			    null);
//
//    XtRealizeWidget(p);
//
//    XtVaGetValues(p, XtNwidth, &w, XtNheight, &h,  null);
//    XtTranslateCoords(info_command, ev.x, ev.y, &x, &y);
//    XtVaSetValues(p, XtNx, MAX(0, x - w / 2), XtNy, MAX(0, y - h / 2), null);
//    XtPopupSpringLoaded(p);
//  }
//}
//
///**************************************************************************
// Update the connected users list at pregame state.
//**************************************************************************/
//void update_conn_list_dialog()
//{
//  /* PORTME */
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void sound_bell()
//{
//  XBell(display, 100);
//}
//
///**************************************************************************
//...
//**************************************************************************/
//static void get_net_input(XtPointer client_data, int *fid, XtInputId *id)
//{
//  input_from_server(*fid);
//}
//
///**************************************************************************
//...
//**************************************************************************/
//static void set_wait_for_writable_socket(connection pc,
//					 boolean socket_writable)
//{
//  static boolean previous_state = false;
//
//  if (previous_state == socket_writable)
//    return;
//  util.freelog(Log.LOG_DEBUG, "set_wait_for_writable_socket(%d)", socket_writable);
//  XtRemoveInput(x_input_id);
//  x_input_id = XtAppAddInput(app_context, aconnection.sock,
//			     (XtPointer) (XtInputReadMask |
//					  (socket_writable ?
//					   XtInputWriteMask : 0) |
//					  XtInputExceptMask),
//			     (XtInputCallbackProc) get_net_input, null);
//  previous_state = socket_writable;
//}
//
///**************************************************************************
// This function is called after the client succesfully
// has connected to the server
//**************************************************************************/
//void add_net_input(int sock)
//{
//  x_input_id = XtAppAddInput(app_context, sock,
//			     (XtPointer) (XtInputReadMask |
//					  XtInputExceptMask),
//			     (XtInputCallbackProc) get_net_input, null);
//  aconnection.notify_of_writable_data = set_wait_for_writable_socket;
//}
//
///**************************************************************************
// This function is called if the client disconnects
// from the server
//**************************************************************************/
//void remove_net_input()
//{
//  XtRemoveInput(x_input_id);
//  XUndefineCursor(display, XtWindow(map_canvas));
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void end_turn_callback(Widget w, XtPointer client_data, XtPointer call_data)
//{ 
//  XtSetSensitive(turn_done_button, false);
//
//  user_ended_turn();
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void timer_callback(XtPointer client_data, XtIntervalId * id)
//{
//  x_interval_id = XtAppAddTimeOut(app_context, TIMER_INTERVAL,
//				  timer_callback, null);
//  real_timer_callback();
//}
//
///**************************************************************************
//  Set one of the unit icons in information area based on punit.
//  Use punit==null to clear icon.
//  Index 'idx' is -1 for "active unit", or 0 to (num_units_below-1) for
//  units below.  Also updates unit_ids[idx] for idx>=0.
//**************************************************************************/
//void set_unit_icon(int idx, unit punit)
//{
//  Widget w;
//  
//  assert(idx>=-1 && idx<num_units_below);
//  if (idx == -1) {
//    w = unit_pix_canvas;
//  } else {
//    w = unit_below_canvas[idx];
//    unit_ids[idx] = punit ? punit.id : 0;
//  }
//  
//  XawPixcommClear(w);
//  if (punit) {
//    struct canvas store = {XawPixcommPixmap(w)};
//
//    put_unit(punit, &store, 0, 0);
//    xaw_expose_now(w);
//  }
//}
//
///**************************************************************************
//  Set the "more arrow" for the unit icons to on(1) or off(0).
//  Maintains a static record of current state to avoid unnecessary redraws.
//  Note initial state should match initial gui setup (off).
//**************************************************************************/
//void set_unit_icons_more_arrow(boolean onoff)
//{
//  static boolean showing = false;
//
//  if (onoff && !showing) {
//    xaw_set_bitmap(more_arrow_label, sprites.right_arrow.pixmap);
//    showing = true;
//  }
//  else if(!onoff && showing) {
//    xaw_set_bitmap(more_arrow_label, None);
//    showing = false;
//  }
//}
//
///**************************************************************************
//  Called to fill econ_label pixmaps (showing tax/lux/sci rates).
//
//  It may be called again if the tileset changes.
//**************************************************************************/
//void fill_econ_label_pixmaps()
//{
//  int i;
//  int econ_label_count = 10;
//
//  for(i = 0; i < econ_label_count; i++) {
//    Sprite s = i < 5 ? sprites.tax_science : sprites.tax_gold;
//
//    XtVaSetValues(econ_label[i], XtNbitmap,
//		  s.pixmap, null);
//    XtAddCallback(econ_label[i], XtNcallback, taxrates_callback,
//		  INT_TO_XTPOINTER(i));
//  }
//}
//
///**************************************************************************
//  Called to fill unit_below pixmaps. They are on the left of the
//  screen that shows all of the inactive units in the current tile.
//
//  It may be called again if the tileset changes.
//**************************************************************************/
//void fill_unit_below_pixmaps()
//{
//  long i;
//
//  for (i = 0; i < num_units_below; i++) {
//    unit_below_pixmap[i] = XCreatePixmap(display, XtWindow(overview_canvas),
//					 UNIT_TILE_WIDTH, UNIT_TILE_HEIGHT,
//					 display_depth);
//  }
//}
//
///**************************************************************************
//  Called when the tileset is changed to reset indicators pixmaps.
//**************************************************************************/
//void reset_econ_label_pixmaps()
//{
//  fill_econ_label_pixmaps();
//}
//
///**************************************************************************
//  Called when the tileset is changed to reset unit pixmaps.
//**************************************************************************/
//void reset_unit_below_pixmaps()
//{
//  long i;
//
//  for (i = 0; i < num_units_below; i++) {
//    XFreePixmap(display, unit_below_pixmap[i]);
//  }
//  fill_unit_below_pixmaps();
//}
}