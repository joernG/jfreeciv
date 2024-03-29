package utility.ftwl;

public class Widget_core{

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
//static enum widget_type sw_widget_get_type(final sw_widget widget)
//{
//  return widget.type;
//}
//
///*************************************************************************
//  ...
//*************************************************************************/
//sw_widget create_widget(sw_widget parent,
//				enum widget_type type)
//{
//  sw_widget result = fc_malloc(sizeof(*result));
//
//  result.parent = null;
//  if (parent) {
//    assert(sw_widget_get_type(parent) == WT_WINDOW);
//    sw_window_add(parent, result);
//  }
//
//  result.type = type;
//  result.pressed = false;
//  result.selected = false;
//  result.disabled = false;
//  result.dragged=false;
//  result.accepts_events[EV_MOUSE] = true;
//  result.accepts_events[EV_KEYBOARD] = true;
//
//  result.can_be_pressed = false;
//  result.can_be_selected = false;
//  result.can_be_dragged[BE_MB_LEFT] = false;
//  result.can_be_dragged[BE_MB_RIGHT] = false;
//  result.can_be_dragged[BE_MB_MIDDLE] = false;
//
//  result.needs_repaint = true;
//
//  result.pos.x = -1;
//  result.pos.y = -1;
//  result.inner_bounds.width = 0;
//  result.inner_bounds.height = 0;
//
//  result.destroy = null;
//  result.entered = null;
//  result.left = null;
//  result.click = null;
//  result.click_start = null;
//  result.key = null;
//  result.draw = null;
//  result.draw_extra_background = null;
//  result.drag_start=null;
//  result.drag_move=null;
//  result.drag_end=null;
//
//  result.tooltip = null;
//
//  result.has_background_color = false;
//  result.background_sprite = null;
//  result.has_border_color = false;
//
//  return result;
//}
//
///*************************************************************************
//  ...
//*************************************************************************/
//void sw_widget_set_tooltip(sw_widget widget,
//			   final ct_tooltip tooltip)
//{
//  if (widget.tooltip) {
//    untooltip(widget);
//    ct_tooltip_destroy(widget.tooltip);
//  }
//
//  widget.tooltip = ct_tooltip_clone(tooltip);
//  widget.tooltip_shown = false;
//  widget.tooltip_callback_id = 0;
//
//  widget_needs_paint(widget);
//}
//
///*************************************************************************
//  input is pos and inner_bounds.{width,height}
//*************************************************************************/
//static void update_bounds(sw_widget widget)
//{
//  int border = 0;
//
//  if (widget.pos.x == -1 && widget.pos.y == -1) {
//    return;
//  }
//
//  assert(ct_point_valid(&widget.pos));
//
//  if (widget.has_border_color) {
//    border = BORDER_WIDTH;
//  }
//
//  widget.outer_bounds.x = widget.pos.x;
//  widget.outer_bounds.y = widget.pos.y;
//  widget.outer_bounds.width = widget.inner_bounds.width + 2 * border;
//  widget.outer_bounds.height = widget.inner_bounds.height + 2 * border;
//
//  if (widget.type != WT_WINDOW) {
//    widget.inner_bounds.x = widget.pos.x + border;
//    widget.inner_bounds.y = widget.pos.y + border;
//  } else {
//    widget.inner_bounds.x = border;
//    widget.inner_bounds.y = border;
//
//    widget.data.window.children_bounds.x = border;
//    widget.data.window.children_bounds.y =
//	border + widget.data.window.inner_deco_height;
//    widget.data.window.children_bounds.width = widget.inner_bounds.width;
//    widget.data.window.children_bounds.height =
//	widget.inner_bounds.height - widget.data.window.inner_deco_height;
//    if (!ct_rect_valid(&widget.data.window.children_bounds)) {
//      printf("children bounds of %p are %s and so invalid\n", widget,
//	     ct_rect_to_string(&widget.data.window.children_bounds));
//      assert(0!=1);
//    }
//  }
// 
//  if (!ct_rect_valid(&widget.outer_bounds)) {
//    printf("outer_bounds of %p are %s and so invalid\n", widget,
//	   ct_rect_to_string(&widget.outer_bounds));
//    assert(0!=1);
//  }
//  if (!ct_rect_valid(&widget.inner_bounds)) {
//    printf("inner_bounds of %p are %s and so invalid\n", widget,
//	   ct_rect_to_string(&widget.inner_bounds));
//    assert(0!=1);
//  }
//}
//
///*************************************************************************
//  ...
//*************************************************************************/
//static void border_changed(sw_widget widget)
//{
//  update_bounds(widget);
//}
//
///*************************************************************************
//  ...
//*************************************************************************/
//void inner_size_changed(sw_widget widget)
//{
//  update_bounds(widget);
//}
//
///*************************************************************************
//  ...
//*************************************************************************/
//void sw_widget_set_position(sw_widget widget, int x, int y)
//{
//  widget.pos.x = x;
//  widget.pos.y = y;
//
//  assert(widget.pos.x >= 0 && widget.pos.y >= 0);
//
//  if (widget.parent) {
//    widget.pos.x += widget.parent.data.window.children_bounds.x;
//    widget.pos.y += widget.parent.data.window.children_bounds.y;
//  }
//  assert(widget.pos.x >= 0 && widget.pos.y >= 0);
//
//  update_bounds(widget);
//
//  assert(ct_rect_valid(&widget.outer_bounds));
//  assert(ct_rect_valid(&widget.inner_bounds));
//
//  if (widget.parent && !ct_rect_in_rect
//      (&widget.outer_bounds,
//       &widget.parent.data.window.children_bounds)) {
//    printf("child %p (%s) ", widget,
//	   ct_rect_to_string(&widget.outer_bounds));
//    printf("is outside of parent %p (%s)\n", widget.parent,
//	   ct_rect_to_string(&widget.parent.data.window.children_bounds));
//    assert(0!=1);
//  }
//  parent_needs_paint(widget);
//}
//
///*************************************************************************
//  ...
//*************************************************************************/
//void sw_widget_hcenter(sw_widget widget)
//{
//  sw_widget_set_position(widget,
//			 (widget.parent.data.window.children_bounds.width -
//			  widget.outer_bounds.width) / 2,
//			 widget.outer_bounds.y);
//}
//
///*************************************************************************
//  ...
//*************************************************************************/
//void sw_widget_vcenter(sw_widget widget)
//{
//  sw_widget_set_position(widget, widget.outer_bounds.x,
//			 (widget.parent.data.window.children_bounds.
//			  height - widget.outer_bounds.height) / 2);
//}
//
///*************************************************************************
//  ...
//*************************************************************************/
//void align(final ct_rect bb, ct_rect item,
//	   enum ws_alignment alignment)
//{
//  assert(item.width <= bb.width && item.height <= bb.height);
//
//  switch (alignment) {
//  case A_W:
//  case A_NW:
//  case A_SW:
//  case A_WC:
//    item.x = bb.x;
//    break;
//  case A_E:
//  case A_NE:
//  case A_SE:
//      case A_EC:
//    item.x = bb.x+bb.width - item.width;
//    break;
//  case A_WE:
//  case A_CENTER:
//  case A_NC:
//  case A_SC:
//    item.x = bb.x+(bb.width - item.width) / 2;
//    break;
//  case A_N:
//  case A_S:
//    break;
//  default:
//    assert(0!=1);
//  }
//
//  switch (alignment) {
//  case A_N:
//  case A_NW:
//  case A_NE:
//      case A_NC:
//    item.y = bb.y;
//    break;
//  case A_S:
//  case A_SW:
//  case A_SE:
//      case A_SC:
//    item.y = bb.y+bb.height - item.height;
//    break;
//  case A_NS:
//  case A_CENTER:
//      case A_WC:
//      case A_EC:
//    item.y = bb.y+(bb.height - item.height) / 2;
//    break;
//  case A_W:
//  case A_E:
//    break;
//  default:
//    assert(0!=1);
//  }
//}
//
///*************************************************************************
//  ...
//*************************************************************************/
//void sw_widget_align_parent(sw_widget widget, enum ws_alignment alignment)
//{
//  sw_widget window = widget.parent;
//  struct ct_rect rect, bb;
//
//  if (widget.pos.x == -1 && widget.pos.y == -1) {
//    sw_widget_set_position(widget, 0, 0);
//  }
//
//  update_bounds(widget);
//  rect = widget.outer_bounds;
//  bb = window.data.window.children_bounds;
//  bb.x = 0;
//  bb.y = 0;
//
//  align(&bb, &rect, alignment);
//  sw_widget_set_position(widget, rect.x, rect.y);
//}
//
///*************************************************************************
//  ...
//*************************************************************************/
//void sw_widget_align_box(sw_widget widget,
//			 enum ws_alignment alignment, final struct ct_rect
//			 *box)
//{
//  struct ct_rect rect, bb;
//
//  if (widget.pos.x == -1 && widget.pos.y == -1) {
//    sw_widget_set_position(widget, 0, 0);
//  }
//
//  update_bounds(widget);
//  rect = widget.outer_bounds;
//  bb = *box;
//
//  align(&bb, &rect, alignment);
//  sw_widget_set_position(widget, rect.x, rect.y);
//}
//
///*************************************************************************
//  ...
//*************************************************************************/
//void sw_widget_set_background_sprite(sw_widget widget,
//				     Sprite sprite)
//{
//  widget.background_sprite = sprite;
//  widget_needs_paint(widget);
//}
//
///*************************************************************************
//  ...
//*************************************************************************/
//void sw_widget_set_background_color(sw_widget widget,
//				    be_color background_color)
//{
//  widget.has_background_color = true;
//  widget.background_color = background_color;
//  widget_needs_paint(widget);
//}
//
///*************************************************************************
//  ...
//*************************************************************************/
//void sw_widget_set_border_color(sw_widget widget,
//				be_color border_color)
//{
//  widget.has_border_color = true;
//  widget.border_color = border_color;
//  border_changed(widget);
//  widget_needs_paint(widget);
//}
//
///*************************************************************************
//  ...
//*************************************************************************/
//void sw_widget_get_bounds(sw_widget widget, ct_rect bounds)
//{
//  *bounds = widget.outer_bounds;
//  bounds.y -= widget.parent.data.window.inner_deco_height;
//}
//
///*************************************************************************
//  ...
//*************************************************************************/
//void sw_widget_destroy(sw_widget widget)
//{
//  assert(widget);
//  &deferred_destroyed_widgets.foo_list_insert(widget);
//  parent_needs_paint(widget);
//  // FIXME this is unsafe if
//  // deferred_destroyed_widgets.foo_list_size() > 1
//}
//
///*************************************************************************
//  ...
//*************************************************************************/
//void sw_widget_disable_mouse_events(sw_widget widget)
//{
//  widget.accepts_events[EV_MOUSE] = false;
//}
//
///*************************************************************************
//  ...
//*************************************************************************/
//void sw_widget_set_enabled(sw_widget widget, boolean enabled)
//{
//  widget.disabled = !enabled;
//}
}