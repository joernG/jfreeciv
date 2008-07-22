package utility.ftwl;

public class Widget_label{

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
//#include "mem.h"
//
///*************************************************************************
//  ...
//*************************************************************************/
//static void draw(sw_widget widget)
//{
//  struct ct_point pos;
//
//  pos.x = widget.inner_bounds.x + 1;
//  pos.y = widget.inner_bounds.y + 1;
//
//  be_draw_string(get_osda(widget), BE_OPAQUE, &pos, widget.data.label.text);
//}
//
///*************************************************************************
//  ...
//*************************************************************************/
//static void destroy(sw_widget widget)
//{
//  ct_string_destroy(widget.data.label.text);
//}
//
///*************************************************************************
//  ...
//*************************************************************************/
//sw_widget sw_label_create_text(sw_widget parent,
//				       ct_string string)
//{
//  sw_widget result = create_widget(parent, WT_LABEL);
//
//  result.destroy = destroy;
//  result.draw = draw;
//
//  result.data.label.text = string;
//
//  result.inner_bounds.width = string.size.width + 2;
//  result.inner_bounds.height = string.size.height + 2;
//  result.outer_bounds = result.inner_bounds;
//
//  return result;
//}
//
///*************************************************************************
//  ...
//*************************************************************************/
//sw_widget sw_label_create_text_bounded(sw_widget parent,
//					       ct_string string,
//					       ct_rect bounds,
//					       enum ws_alignment alignment)
//{
//  int size;
//
//  for (size = string.font_size; size > 0; size--) {
//    ct_string string2 = ct_string_clone1(string, size);
//    if (string2.size.width <= bounds.width &&
//	string2.size.height <= bounds.height) {
//      sw_widget result = sw_label_create_text(parent, string2);
//
//      sw_widget_align_box(result, alignment, bounds);
//      return result;
//    } else {
//      ct_string_destroy(string2);
//    }
//  }
//  assert(0!=1);
//  return null;
//}
}