package utility.ftwl;

public class Widget_window{

// Freeciv - Copyright (C) 2004 - The Freeciv Project
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
//#include <stdlib.h>
//#include <string.h>
//
//#include <assert.h>
//#include <stdio.h>
//
//#include "widget_p.h"
//
//#include "log.h"
//#include "mem.h"
//
///* REMOVE ME */
//#include "timing.h"
//
//public static final int DEBUG_UPDATES = 0;
//public static final int DUMP_UPDATES = 0;
//public static final int DUMP_WINDOWS = 0;
//public static final int DEBUG_PAINT_ALL = false;
//
//sw_widget root_window;
//static Speclists<widget> windows_back_to_front;
//static Speclists<widget> windows_front_to_back;
//Speclists<widget> deferred_destroyed_widgets;
//static boolean dump_screen = false;
//
//public static final int TITLE_PADDING = 3;
//
///*************************************************************************
//  ...
//*************************************************************************/
//static void draw_extra_background(sw_widget widget,
//				  final ct_rect region)
//{
//  if (widget.data.window.canvas_background) {
//    struct ct_size size = { region.width,
//			    region.height
//    };
//    struct ct_point pos={region.x,region.y};
//
//    be_copy_osda_to_osda(get_osda(widget),
//			 widget.data.window.canvas_background,
//			 &size, &pos, &pos, 0);
//  }
//}
//
///*************************************************************************
//  ...
//*************************************************************************/
//static void draw(sw_widget widget)
//{
//  if (widget.data.window.title) {
//    struct ct_point pos;
//    struct ct_rect rect = widget.inner_bounds;
//
//    rect.height = widget.data.window.title.size.height + 2 * TITLE_PADDING;
//
//    be_draw_region(widget.data.window.target, BE_OPAQUE, &rect,
//		   widget.data.window.title.background);
//    be_draw_rectangle(get_osda(widget), BE_OPAQUE, &rect, 1,
//		      widget.data.window.title.foreground);
//    pos.x = widget.inner_bounds.x+TITLE_PADDING;
//    pos.y = widget.inner_bounds.y+TITLE_PADDING;
//    be_draw_string(get_osda(widget), BE_OPAQUE, &pos,
//		   widget.data.window.title);
//  }
//}
//
///*************************************************************************
//  ...
//*************************************************************************/
//static void drag_start(sw_widget widget,
//		       final ct_point mouse,
//		       enum be_mouse_button button)
//{
//  if (button == BE_MB_LEFT) {
//    widget.data.window.pos_at_drag_start.x = widget.outer_bounds.x;
//    widget.data.window.pos_at_drag_start.y = widget.outer_bounds.y;
//
//    sw_widget_set_border_color(widget, be_get_color(255, 255, 0));
//  } else if (widget.data.window.user_drag_start) {
//    widget.data.window.user_drag_start(widget, mouse, button);
//  }
//}
//
///*************************************************************************
//  ...
//*************************************************************************/
//static void drag_move(sw_widget widget,
//		      final ct_point start_position,
//		      final ct_point current_position,
//		      enum be_mouse_button button)
//{
//  if (button == BE_MB_LEFT) {
//    int dx = current_position.x - start_position.x;
//    int dy = current_position.y - start_position.y;
//    struct ct_rect new = widget.outer_bounds;
//
//    new.x = widget.data.window.pos_at_drag_start.x + dx;
//    new.y = widget.data.window.pos_at_drag_start.y + dy;
//
//    if (ct_rect_valid(&new)
//	&& ct_rect_in_rect(&new, &root_window.data.window.children_bounds)) {
//      sw_widget_set_position(widget, new.x, new.y);
//    }
//  } else if (widget.data.window.user_drag_move) {
//    widget.data.window.user_drag_move(widget, start_position,
//				       current_position, button);
//  }
//}
//
///*************************************************************************
//  ...
//*************************************************************************/
//static void drag_end(sw_widget widget, enum be_mouse_button button)
//{
//  if (button == BE_MB_LEFT) {
//    sw_widget_set_border_color(widget, be_get_color(255, 255, 255));
//  } else if (widget.data.window.user_drag_end) {
//    widget.data.window.user_drag_end(widget, button);
//  }
//}
//
///*************************************************************************
//  ...
//*************************************************************************/
//static void destroy(sw_widget widget)
//{
//  for (widget pwidget : widget.data.window.children.data) {
//    real_widget_destroy(pwidget);
//  } }
//
//
//  be_free_osda(widget.data.window.target);
//  if (widget.data.window.title) {
//    ct_string_destroy(widget.data.window.title);
//  }
//
//  widget_list_unlink(&windows_front_to_back, widget);
//  widget_list_unlink(&windows_back_to_front, widget);
//}
//
///*************************************************************************
//  ...
//*************************************************************************/
//static int my_sort_back_to_front(final void *p1, final void *p2)
//{
//  final sw_widget w1 =
//      (final sw_widget ) *(final void **) p1;
//  final sw_widget w2 =
//      (final sw_widget ) *(final void **) p2;
//
//  return w1.data.window.depth - w2.data.window.depth;
//}
//
///*************************************************************************
//  ...
//*************************************************************************/
//static int my_sort_front_to_back(final void *p1, final void *p2)
//{
//  final sw_widget w1 =
//      (final sw_widget ) *(final void **) p1;
//  final sw_widget w2 =
//      (final sw_widget ) *(final void **) p2;
//
//  return w2.data.window.depth - w1.data.window.depth;
//}
//
///*************************************************************************
//  ...
//*************************************************************************/
//static void sort()
//{
//  widget_list_sort(&windows_front_to_back, my_sort_front_to_back);
//  widget_list_sort(&windows_back_to_front, my_sort_back_to_front);
//}
//
//static void sw_window_set_depth(sw_widget widget, int depth)
//{
//  widget.data.window.depth = depth;
//  sort();
//}
//
///*************************************************************************
//  ...
//*************************************************************************/
//sw_widget sw_window_create(sw_widget parent, int width,
//				   int height, ct_string title,
//				   int transparency, boolean has_border,
//				   int depth)
//{
//  sw_widget result = create_widget(parent, WT_WINDOW);
//  int border_width = has_border ? BORDER_WIDTH : 0;
//
//  if (width == 0 && height == 0) {
//    struct ct_size size;
//
//    be_screen_get_size(&size);
//    width = size.width;
//    height = size.height;
//  }
//
//  if (depth == 0) {
//    depth = parent ? parent.data.window.depth + 1 : DEPTH_MIN;
//  }
//  assert(depth == DEPTH_HIDDEN
//	 || (depth >= DEPTH_MIN && depth <= DEPTH_MAX));
//
//  result.destroy = destroy;
//  result.draw = draw;
//  result.draw_extra_background = draw_extra_background;
//  result.drag_start = drag_start;
//  result.drag_move = drag_move;
//  result.drag_end = drag_end;
//
//  result.can_be_dragged[BE_MB_LEFT] = true;
//
//  widget_list_init(&result.data.window.children);
//  widget_list_insert_back(&windows_front_to_back, result);
//  widget_list_insert_back(&windows_back_to_front, result);
//
//  region_list_init(&result.data.window.to_flush);
//
//  result.inner_bounds.x = border_width;
//  result.inner_bounds.y = border_width;
//  result.inner_bounds.width = width;
//  result.inner_bounds.height = height;
//
//  result.outer_bounds.width = 2 * border_width + width;
//  result.outer_bounds.height = 2 * border_width + height;
//
//  result.data.window.target =
//      be_create_osda(2 * border_width + width, 2 * border_width + height);
//  result.data.window.title = title;
//  result.data.window.transparency = (transparency * MAX_TRANSPARENCY) / 100;
//  result.data.window.shown = true;
//  result.data.window.list = null;
//
//  result.data.window.canvas_background = null;
//
//  result.data.window.inner_deco_height = 0;
//  if (title) {
//    result.data.window.inner_deco_height =
//	result.data.window.title.size.height + 2 * TITLE_PADDING;
//  }
//
//  if (has_border) {
//    sw_widget_set_border_color(result, be_get_color(255, 255, 255));
//  }
//
//  sw_window_set_depth(result, depth);
//
//  return result;
//}
//
//static osda whole_osda = null;
//
///*************************************************************************
//  ...
//*************************************************************************/
//sw_widget sw_create_root_window()
//{
//  struct ct_size size;
//  sw_widget result;
//
//  assert(!root_window);
//  widget_list_init(&windows_front_to_back);
//  widget_list_init(&windows_back_to_front);
//  widget_list_init(&deferred_destroyed_widgets);
//
//  be_screen_get_size(&size);
//
//  whole_osda = be_create_osda(size.width, size.height);
//
//  result =
//      sw_window_create(null, size.width, size.height, null, 0, false,
//		       DEPTH_MIN + 1);
//  sw_widget_set_position(result, 0, 0);
//  sw_widget_set_background_color(result, be_get_color(22, 44, 88));
//  sw_window_set_draggable(result, false);
//
//  root_window = result;
//  return result;
//}
//
///*************************************************************************
//  ...
//*************************************************************************/
//void sw_window_add(sw_widget window, sw_widget widget)
//{
//  assert(window && widget);
//  assert(!widget.parent);
//
//  for (widget pwidget : window.data.window.children.data) {
//    assert(pwidget != widget);
//  } }
//
//  widget_list_insert(&window.data.window.children, widget);
//  widget.parent = window;
//}
//
///*************************************************************************
//  ...
//*************************************************************************/
//void remove_all_from_window(sw_widget widget)
//{
//  while (widget.data.window.children.foo_list_size() > 0) {
//    sw_window_remove(widget_list_get(&widget.data.window.children, 0));
//  }
//}
//
///*************************************************************************
//  ...
//*************************************************************************/
//void sw_window_remove(sw_widget widget)
//{
//  sw_widget old_parent = widget.parent;
//  int found = 0;
//
//  assert(old_parent);
//
//  for (widget pwidget : old_parent.data.window.children.data) {
//    if (pwidget == widget) {
//      found++;
//    }
//  } }
//
//  assert(found == 1);
//  widget_list_unlink(&old_parent.data.window.children, widget);
//  widget.parent = null;
//}
//
///*************************************************************************
//  ...
//*************************************************************************/
//static void flush_one_window(sw_widget widget,
//			     final ct_rect screen_rect)
//{
//  struct ct_size size;
//  struct ct_point src_pos, dest_pos;
//
//  if (!widget.data.window.shown) {
//    return;
//  }
//
//  size.width = screen_rect.width;
//  size.height = screen_rect.height;
//  dest_pos.x = screen_rect.x;
//  dest_pos.y = screen_rect.y;
//  src_pos.x = screen_rect.x - widget.outer_bounds.x;
//  src_pos.y = screen_rect.y - widget.outer_bounds.y;
//
//  be_copy_osda_to_osda(whole_osda, widget.data.window.target, &size,
//		       &dest_pos, &src_pos,
//		       widget.data.window.transparency);
//}
//
///*************************************************************************
//  ...
//*************************************************************************/
//void flush_all_to_screen()
//{
//  be_copy_osda_to_screen(whole_osda);
//
//  if (dump_screen) {
//    static int counter = -1;
//    char b[100];
//
//    counter++;
//
//    util.freelog(Log.LOG_NORMAL, "flush to screen %d",counter);
//
//    sprintf(b, "screen-%04d.ppm", counter);
//    be_write_osda_to_file(whole_osda, b);
//  }
//}
//
///*************************************************************************
//  ...
//*************************************************************************/
//void sw_window_resize(sw_widget widget, int width, int height)
//{
//  be_free_osda(widget.data.window.target);
//  widget.data.window.target = be_create_osda(width, height);
//  widget.inner_bounds.width = width;
//  widget.inner_bounds.height = height;
//  inner_size_changed(widget);
//  parent_needs_paint(widget);
//}
//
///*************************************************************************
//  ...
//*************************************************************************/
//static void draw_background_region(sw_widget widget,
//				   final ct_rect  rect)
//{
//  if (widget.draw_extra_background) {
//    widget.draw_extra_background(widget, rect);
//  }
//  if (widget.background_sprite) {
//    struct ct_point pos;
//    struct ct_size size;
//
//    pos.x = rect.x;
//    pos.y = rect.y;
//
//    size.width = rect.width;
//    size.height = rect.height;
//
//    be_draw_sprite(get_osda(widget), BE_OPAQUE, widget.background_sprite,
//		   &size, &pos, &pos);
//  } else if (widget.has_background_color) {
//    be_draw_region(get_osda(widget), BE_ALPHA, rect,
//		   widget.background_color);
//  } else {
//    if (widget.parent && widget.type != WT_WINDOW) {
//      draw_background_region(widget.parent, rect);
//    }
//  }
//}
//
///*************************************************************************
//  ...
//*************************************************************************/
//static void draw_background(sw_widget widget)
//{
//  be_set_transparent(get_osda(widget), &widget.inner_bounds);
//
//  draw_background_region(widget, &widget.inner_bounds);
//
//  if (widget.has_border_color) {
//    struct ct_rect rect = widget.outer_bounds;
//
//    if (widget.type == WT_WINDOW) {
//      rect.x = 0;
//      rect.y = 0;
//    }
//
//    be_draw_rectangle(get_osda(widget), BE_OPAQUE, &rect, BORDER_WIDTH,
//		      widget.border_color);
//  }
//}
//
///*************************************************************************
//  ...
//*************************************************************************/
//static void add_flush_region(sw_widget widget, final struct ct_rect
//			     *region)
//{
//  sw_widget window;
//
//  if (widget.type == WT_WINDOW) {
//    window = widget;
//  } else {
//    window = widget.parent;
//  }
//
//  for (region pregion : window.data.window.to_flush.data) {
//    if (ct_rect_in_rect(region, pregion)) {
//      return;
//    }
//  } }
//  
//  region_list_insert(&window.data.window.to_flush, ct_rect_clone(region));
//}
//
///*************************************************************************
//  ...
//*************************************************************************/
//static void draw_tooltip(sw_widget widget)
//{
//  struct ct_point pos;
//  struct ct_rect rect;
//  final int PADDING = 5;
//  enum be_draw_type draw_type = BE_OPAQUE;
//  osda osda;
//  int i, extra = 2 * PADDING + widget.tooltip.shadow;
//
//  rect.width = widget.tooltip.text.size.width + extra;
//  rect.height = widget.tooltip.text.size.height + extra;
//
//  rect.x =
//      MAX(0,
//	  widget.outer_bounds.x + (widget.outer_bounds.width -
//				    rect.width) / 2);
//  rect.y = MAX(0,widget.outer_bounds.y - rect.height - PADDING);
//
//  pos.x = rect.x + PADDING;
//  pos.y = rect.y + PADDING;
//
//  if (widget.type == WT_WINDOW) {
//    osda = get_osda(widget.parent);
//  } else {
//    osda = get_osda(widget);
//  }
//
//  for (i = 1; i < widget.tooltip.shadow; i++) {
//    struct ct_rect rect2 = rect;
//
//    rect2.x += i;
//    rect2.y += i;
//    be_draw_region(osda, draw_type, &rect2, widget.tooltip.shadow_color);
//  }
//
//  be_draw_region(osda, draw_type, &rect, widget.tooltip.text.background);
//  be_draw_string(osda, draw_type, &pos, widget.tooltip.text);
//  be_draw_rectangle(osda, draw_type, &rect, 1,
//		    widget.tooltip.text.foreground);
//}
//
///*************************************************************************
//  ...
//*************************************************************************/
//static void draw_one(sw_widget widget)
//{
//  draw_background(widget);
//  assert(widget.draw);
//  widget.draw(widget);
//}
//
///*************************************************************************
//  ...
//*************************************************************************/
//void update_window(sw_widget widget)
//{
//  assert(widget.type == WT_WINDOW);
//  if (widget.needs_repaint) {
//    draw_one(widget);
//
//    for (widget pwidget : widget.data.window.children.data) {
//      if (pwidget.type != WT_WINDOW) {
//	draw_one(pwidget);
//      }
//    }
//    }
//
//    for (widget pwidget : widget.data.window.children.data) {
//      if (pwidget.tooltip && pwidget.tooltip_shown) {
//	draw_tooltip(pwidget);
//      }
//    } }
//    if (widget.tooltip && widget.tooltip_shown) {
//      draw_tooltip(widget);
//    }
//
//    widget.needs_repaint = false;
//
//    {
//      struct ct_rect tmp =
//	  { 0, 0, widget.outer_bounds.width, widget.outer_bounds.height };
//      add_flush_region(widget, &tmp);
//    }
//  } else {
//    for (widget pwidget : widget.data.window.children.data) {
//      if (pwidget.type != WT_WINDOW && pwidget.needs_repaint) {
//	draw_one(pwidget);
//	pwidget.needs_repaint = false;
//	add_flush_region(widget, &pwidget.outer_bounds);
//      }
//    }
//    }
//  }
//}
//
///*************************************************************************
//  ...
//*************************************************************************/
//static boolean is_opaque(sw_widget widget)
//{
//  if (widget.type == WT_WINDOW) {
//    if (widget.data.window.transparency > 0) {
//      return false;
//    }
//    if (widget.data.window.canvas_background) {
//      return true;
//    }
//  }
//  return false;
//}
//
///*************************************************************************
//  ...
//*************************************************************************/
//static void merge_regions(region_list list)
//{
//  Speclists<region> tmp, *orig, *copy;
//
//  orig = list;
//  copy = &tmp;
//  region_list_init(copy);
//
//  region_list_iterate(*orig, region) {
//    if (ct_rect_in_rect_list(region, copy)) {
//	//printf("####### 1\n");
//      free(region);
//    } else {
//      region_list_insert(copy, region);
//    }
//  } }
//
//  orig = &tmp;
//  copy = list;
//  region_list_init(copy);
//
//  region_list_iterate(*orig, region) {
//    if (ct_rect_in_rect_list(region, copy)) {
//	//printf("####### 2\n");
//      free(region);
//    } else {
//      region_list_insert(copy, region);
//    }
//  } }
//}
//
///*************************************************************************
//  ...
//*************************************************************************/
//void sw_paint_all()
//{
//  int regions=0;
//  timer timer1 = new_timer(TIMER_USER, TIMER_ACTIVE);
//  timer timer2 = new_timer(TIMER_USER, TIMER_ACTIVE);
//  timer timer3 = new_timer(TIMER_USER, TIMER_ACTIVE);
//  timer timer4 = new_timer(TIMER_USER, TIMER_ACTIVE);
//  Speclists<region> normalized_regions;
//  static int call = -1;
//#if DUMP_UPDATES
//  char filename[100];
//  int region_nr;
//#endif
//
//  handle_destroyed_widgets();
//
//  call++;
//  if(DEBUG_PAINT_ALL)
//  printf("%%%%%%%% starting sw_paint_all %d\n",call);
//
//  region_list_init(&normalized_regions);
//
//  start_timer(timer1);
//  for (widget widget : windows_back_to_front.data) {
//    update_window(widget);
//    regions += widget.data.window.to_flush.foo_list_size();
//  } }
//  stop_timer(timer1);
//
//  if (regions == 0) {
//    return;
//  }
//
//  if(DEBUG_PAINT_ALL)  printf("%%%%%%%% updated windows; %d regions have to be flushed\n",regions);
//
//  if (DUMP_WINDOWS) {
//    for (widget widget : windows_back_to_front.data) {
//      char name[100];
//
//      if (widget.data.window.depth > -1) {
//	sprintf(name, "window-c%03d-%p.ppm", call, widget);
//	be_write_osda_to_file(widget.data.window.target, name);
//      }
//    } }
//  }
//
//  start_timer(timer2);
//  for (widget widget : windows_back_to_front.data) {
//    if (DEBUG_PAINT_ALL) {
//      printf("window=%s d=%d is_opaque=%d\n",
//	     ct_rect_to_string(&widget.outer_bounds),
//	     widget.data.window.depth, is_opaque(widget));
//      for (region region : widget.data.window.to_flush.data) {
//	printf("  region=%s\n", ct_rect_to_string(region));
//      } }
//    }
//
//    for (region region : widget.data.window.to_flush.data) {
//      region.x += widget.outer_bounds.x;
//      region.y += widget.outer_bounds.y;
//      region_list_unlink(&widget.data.window.to_flush, region);
//      region_list_insert(&normalized_regions, region);
//    } }
//  } }
//  stop_timer(timer2);
//
//  if (DEBUG_PAINT_ALL) {
//    printf("  normalized_regions\n");
//    for (region region : normalized_regions.data) {
//      printf("    region=%s\n", ct_rect_to_string(region));
//    } }
//  }
//
//  merge_regions(&normalized_regions);
//
//  if(DEBUG_PAINT_ALL)
//  printf("%%%%%%%% starting flushing of %d regions\n",
//	 normalized_regions.foo_list_size());
//
//#if DUMP_UPDATES
//  sprintf(filename,"whole-c%03d-r000-before.ppm",call);
//  be_write_osda_to_file(whole_osda,filename);
//  region_nr = 1;
//#endif
//
//  start_timer(timer3);
//  for (region region : normalized_regions.data) {
//    int window_nr = 0;
//
//    if (DEBUG_UPDATES)
//      printf("region = %s\n", ct_rect_to_string(region));
//
//    for (widget widget : windows_back_to_front.data) {
//      struct ct_rect rect = *region;
//
//      ct_rect_intersect(&rect, &widget.outer_bounds);
//      if (DEBUG_UPDATES)
//	printf("  window=%p %s depth=%d\n", widget,
//	       ct_rect_to_string(&widget.outer_bounds),
//	       widget.data.window.depth);
//      if (!ct_rect_empty(&rect)) {
//	if (DEBUG_UPDATES)
//	  printf("    window intersects with dirty region = %s\n",
//		 ct_rect_to_string(&rect));
//#if DUMP_UPDATES
//	sprintf(filename, "whole-c%03d-r%03d-w%03d-0-before.ppm", call,
//		region_nr, window_nr);
//	be_write_osda_to_file(whole_osda,filename);
//#endif
//	flush_one_window(widget, &rect);
//#if DUMP_UPDATES
//	sprintf(filename, "whole-c%03d-r%03d-w%03d-1-after.ppm", call,
//		region_nr, window_nr);
//	be_write_osda_to_file(whole_osda,filename);
//#endif
//      }else {
//	if(DEBUG_UPDATES)
//	  printf("    disjunkt\n");
//      }
//      window_nr++;
//    } }
//    region_list_unlink(&normalized_regions, region);
//    free(region);
//#if DUMP_UPDATES
//    region_nr++;
//#endif
//  } }
//  stop_timer(timer3);
//#if DUMP_UPDATES
//  sprintf(filename,"whole-c%03d-r999-after.ppm",call);
//  be_write_osda_to_file(whole_osda,filename);
//#endif
//
//  start_timer(timer4);
//  flush_all_to_screen();
//  stop_timer(timer4);
//
//  if(0)
//  printf("PAINT-ALL: update=%fs normalize=%fs flushs=%fs flush-all=%fs\n",
//	 read_timer_seconds(timer1), read_timer_seconds(timer2),
//	 read_timer_seconds(timer3), read_timer_seconds(timer4));
//}
//
///*************************************************************************
//  ...
//*************************************************************************/
//sw_widget search_widget(final ct_point pos,
//				enum event_type event_type)
//{
//  struct ct_point tmp;
//  sw_widget window = null;
//
//  if (!ct_point_valid(pos)) {
//    return null;
//  }
//
//  if (0) {
//    for (widget pwidget : windows_front_to_back.data) {
//      printf("%p: shown=%d accepts=%d inside=%d depth=%d size=%dx%d\n",
//	     pwidget, pwidget.data.window.shown,
//	     pwidget.accepts_events[event_type], ct_point_in_rect(pos,
//								   &pwidget.
//								   outer_bounds),
//	     pwidget.data.window.depth, pwidget.outer_bounds.width,
//	     pwidget.outer_bounds.height);
//    } }
//  }
//
//  for (widget pwidget : windows_front_to_back.data) {
//    if (pwidget.data.window.shown && pwidget.accepts_events[event_type]
//	&& ct_point_in_rect(pos, &pwidget.outer_bounds)) {
//	boolean is_transparent;
//      tmp = *pos;
//      tmp.x -= pwidget.outer_bounds.x;
//      tmp.y -= pwidget.outer_bounds.y;
//
//      is_transparent =
//	  be_is_transparent_pixel(pwidget.data.window.target, &tmp);
//      /*printf("%d %d | %d . %d\n", tmp.x, tmp.y,
//	pwidget.data.window.depth, is_transparent);*/
//      if (!is_transparent) {
//	window = pwidget;
//	break;
//      }
//    }
//  } }
//
//  if (!window) {
//    return null;
//  }
//  assert(ct_point_valid(&tmp));
//
//  for (widget pwidget : window.data.window.children.data) {
//    if (pwidget.type != WT_WINDOW && pwidget.accepts_events[event_type]
//	&& ct_point_in_rect(&tmp, &pwidget.outer_bounds)) {
//	assert(pwidget.type<=WT_LAST);
//      return pwidget;
//    }
//  } }
//
//  assert(window.type<=WT_LAST);
//  return window;
//}
//
///*************************************************************************
//  ...
//*************************************************************************/
//sw_widget sw_window_create_by_clone(sw_widget widget,
//					    int depth)
//{
//  sw_widget result =
//      sw_window_create(widget, widget.outer_bounds.width,
//		       widget.outer_bounds.height, null, 0, false, depth);
//
//  result.can_be_dragged[BE_MB_LEFT] = widget.can_be_dragged;
//  return result;
//}
//
///*************************************************************************
//  ...
//*************************************************************************/
//void sw_window_set_canvas_background(sw_widget widget, boolean yes)
//{
//  if (widget.data.window.canvas_background) {
//    be_free_osda(widget.data.window.canvas_background);
//    widget.data.window.canvas_background = null;
//  }
//  if (yes) {
//    widget.data.window.canvas_background =
//	be_create_osda(widget.outer_bounds.width,
//		       widget.outer_bounds.height);
//  }
//}
//
///*************************************************************************
//  ...
//*************************************************************************/
//osda sw_window_get_canvas_background(sw_widget widget)
//{
//    return widget.data.window.canvas_background;
//}
//
///*************************************************************************
//  ...
//*************************************************************************/
//void sw_window_set_mouse_press_notify(sw_widget widget,
//				      void (*callback) (sw_widget 
//							widget,
//							final struct ct_point
//							* pos, enum be_mouse_button button,
//							int state,
//							void *data),
//				      void *data)
//{
//  widget.click_start = callback;
//  widget.click_start_data = data;
//}
//
///*************************************************************************
//  ...
//*************************************************************************/
//void sw_window_canvas_background_region_needs_repaint(struct sw_widget
//						      *widget, final struct ct_rect
//						      *region)
//{
//#if 0
//  struct ct_size size = { region.width,
//    region.height
//  };
//  struct ct_point pos = { region.x, region.y };
//
//  be_copy_osda_to_osda(get_osda(widget),
//		       widget.data.window.canvas_background,
//		       &size, &pos, &pos, 0);
//#endif
//  //add_flush_region(widget, region);
//  widget_needs_paint(widget);
//}
//
///*************************************************************************
//  ...
//*************************************************************************/
//void sw_set_dump_screen(boolean v)
//{
//  dump_screen = v;
//}
//
///*************************************************************************
//  ...
//*************************************************************************/
//boolean sw_get_dump_screen()
//{
//  return dump_screen;
//}
//
///*************************************************************************
//  ...
//*************************************************************************/
//void sw_window_set_draggable(sw_widget widget, boolean draggable)
//{
//  widget.can_be_dragged[BE_MB_LEFT] = draggable;
//}
//
///*************************************************************************
//  ...
//*************************************************************************/
//void sw_window_set_key_notify(sw_widget widget,
//			      boolean(*callback) (sw_widget 
//					       widget,
//					       final be_key  key,
//					       void *data), void *data)
//{
//  widget.key = callback;
//  widget.key_data = data;
//}
//
///*************************************************************************
//  ...
//*************************************************************************/
//boolean deliver_key(final be_key key)
//{
//  for (widget pwidget : windows_front_to_back.data) {
//    if (!pwidget.data.window.shown || !pwidget.accepts_events[EV_KEYBOARD]) {
//      continue;
//    }
//    if (pwidget.key && pwidget.key(pwidget, key,pwidget.key_data)) {
//      // printf("deliver key %s to window %p\n", ct_key_format(key), pwidget);
//      return true;
//    }
//
//    for (widget pwidget2 : pwidget.data.window.children.data) {
//      if (pwidget2.key && pwidget2.key(pwidget2, key, pwidget2.key_data)) {
//        // printf("deliver key %s to widget %p\n", ct_key_format(key), pwidget2);
//	return true;
//      }
//    } }
//  } }
//
//  return false;
//}
//
///*************************************************************************
//  ...
//*************************************************************************/
//void sw_window_set_user_drag(sw_widget  widget,void (*drag_start)
//			      (sw_widget  widget,
//			       final ct_point  mouse,
//			       enum be_mouse_button button),
//			     void (*drag_move) (sw_widget  widget,
//						final ct_point 
//						start_position,
//						final ct_point 
//						current_position,
//						enum be_mouse_button button),
//			     void (*drag_end) (sw_widget  widget,
//					       enum be_mouse_button button))
//{
//  widget.data.window.user_drag_start = drag_start;
//  widget.data.window.user_drag_move = drag_move;
//  widget.data.window.user_drag_end = drag_end;
//  widget.can_be_dragged[BE_MB_RIGHT] = true;
//}
}