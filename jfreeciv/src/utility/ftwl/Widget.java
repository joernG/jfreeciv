package utility.ftwl;

public class Widget{

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
//#include "text_renderer.h"
//
///*************************************************************************
//  ...
//*************************************************************************/
//osda get_osda(sw_widget widget)
//{
//  if (widget.type == WT_WINDOW) {
//    return widget.data.window.target;
//  }
//  assert(widget.parent && widget.parent.type == WT_WINDOW);
//  return widget.parent.data.window.target;
//}
//
///*************************************************************************
//  ...
//*************************************************************************/
//enum widget_face get_widget_face(sw_widget widget)
//{
//  if (widget.disabled) {
//    return WF_DISABLED;
//  }
//  if (widget.pressed) {
//    return WF_PRESSED;
//  }
//  if (widget.selected) {
//    return WF_SELECTED;
//  }
//  return WF_NORMAL;
//}
//
///*************************************************************************
//  ...
//*************************************************************************/
//void untooltip(sw_widget widget)
//{
//  if (widget.tooltip && widget.tooltip_callback_id != 0) {
//    sw_remove_timeout(widget.tooltip_callback_id);
//    widget.tooltip_callback_id = 0;
//  }
//
//  if (!widget.tooltip || (widget.tooltip && !widget.tooltip_shown)) {
//    return;
//  }
//  widget.tooltip_shown = false;
//
//  parent_needs_paint(widget);
//}
//
///*************************************************************************
//  ...
//*************************************************************************/
//static void popup_tooltip(void *data)
//{
//  sw_widget widget = data;
//
//  widget.tooltip_shown = true;
//
//  widget.tooltip_callback_id = 0;
//
//  parent_needs_paint(widget);
//}
//
//static sw_widget dragged_widget = null;
//static enum be_mouse_button drag_button;
//
///*************************************************************************
//  ...
//*************************************************************************/
//static void mouse_released(sw_widget widget,
//			   final ct_point position,
//			   boolean released_inside)
//{
//  if (widget.can_be_pressed && widget.pressed) {
//    widget.pressed = false;
//
//    if (released_inside) {
//      if (widget.click) {
//	widget.click(widget);
//      } else {
//	printf("WARNING: no callback set %p\n", widget);
//      }
//    }
//  }
//
//  if (widget.dragged) {
//    widget.dragged = false;
//    widget.drag_end(widget, drag_button);
//    assert(dragged_widget == widget);
//    dragged_widget = null;
//  }
//}
//
///*************************************************************************
//  ...
//*************************************************************************/
//static void mouse_leaves(sw_widget widget,
//			 final ct_point position)
//{
//  untooltip(widget);
//
//  mouse_released(widget, position, false);
//
//  if (widget.selected || widget.left) {
//    widget_needs_paint(widget);
//  }
//  widget.selected = false;
//  if (widget.left) {
//    widget.left(widget);
//  }
//}
//
///*************************************************************************
//  ...
//*************************************************************************/
//static void mouse_enters(sw_widget widget,
//			 final ct_point position)
//{
//  if (widget.can_be_selected) {
//    widget.selected = true;
//    widget_needs_paint(widget);
//  }
//  if (widget.entered) {
//    widget.entered(widget);
//    widget_needs_paint(widget);
//  }
//
//  if (widget.tooltip) {
//    assert(widget.tooltip_callback_id == 0);
//    widget.tooltip_callback_id =
//	sw_add_timeout(widget.tooltip.delay, popup_tooltip, widget);
//  }
//}
//
//static struct ct_point drag_start_pos;
//
///*************************************************************************
//  ...
//*************************************************************************/
//static void mouse_pressed(sw_widget widget,
//			  final ct_point position,
//			  enum be_mouse_button button, int state)
//{
//  if (widget.click_start) {
//    struct ct_point pos = *position;
//
//    pos.x -= widget.outer_bounds.x;
//    pos.y -= widget.outer_bounds.y;
//
//    pos.x -= widget.parent.outer_bounds.x;
//    pos.y -= widget.parent.outer_bounds.y;
//
//    widget.click_start(widget, &pos, button, state,
//			widget.click_start_data);
//  }
//
//  if (widget.can_be_pressed) {
//    widget.pressed = true;
//    widget_needs_paint(widget);
//  }
//
//  if (widget.can_be_dragged[button]) {
//    drag_start_pos = *position;
//    widget.dragged = true;
//    assert(widget.drag_start);
//    widget.drag_start(widget, &drag_start_pos, button);
//    dragged_widget = widget;
//    drag_button = button;
//  }
//}
//
///*************************************************************************
//  ...
//*************************************************************************/
//static void mouse_moved(sw_widget widget,
//			final ct_point position)
//{
//  if (widget && widget.dragged) {
//    assert(widget == dragged_widget);
//    assert(widget.drag_move);
//    widget.drag_move(widget, &drag_start_pos, position, drag_button);
//  }
//}
//
//static sw_widget selected_widget = null;
//static boolean selected_widget_gets_keyboard = false;
//
///*************************************************************************
//  ...
//*************************************************************************/
//static void handle_mouse_motion(sw_widget widget,
//				final ct_point position)
//{
//  selected_widget_gets_keyboard = false;
//
//  if (selected_widget == widget) {
//    mouse_moved(widget, position);
//    return;
//  }
//
//  if (selected_widget) {
//    mouse_leaves(selected_widget, position);
//  }
//  if (widget) {
//    mouse_enters(widget, position);
//  }
//  selected_widget = widget;
//}
//
///*************************************************************************
//  ...
//*************************************************************************/
//void sw_widget_select(sw_widget widget)
//{
//  struct ct_point pos = { widget.outer_bounds.x, widget.outer_bounds.y };
//  if (widget.type != WT_WINDOW) {
//    assert(widget.parent);
//    pos.x += widget.parent.outer_bounds.x;
//    pos.y += widget.parent.outer_bounds.y;
//  }
//  handle_mouse_motion(widget, &pos);
//  selected_widget_gets_keyboard = true;
//}
//
//static sw_widget pressed_widget = null;
//static enum be_mouse_button pressed_button = 0;
//
///*************************************************************************
//  ...
//*************************************************************************/
//static void handle_mouse_press(sw_widget widget,
//			       final ct_point position,
//			       enum be_mouse_button button, int state)
//{
//  if (pressed_widget == widget) {
//    /* another mouse button pressed */
//    return;
//  }
//
//  if (pressed_widget) {
//    mouse_released(pressed_widget, position, false);
//  }
//  if (widget) {
//    mouse_pressed(widget, position, button, state);
//  }
//  pressed_widget = widget;
//  pressed_button = button;
//}
//
///*************************************************************************
//  ...
//*************************************************************************/
//static void handle_mouse_release(sw_widget widget,
//				 final ct_point position,
//				 enum be_mouse_button button)
//{
//  if (pressed_widget == widget && pressed_button == button) {
//    mouse_released(pressed_widget, position, true);
//  } else {
//    mouse_released(pressed_widget, position, false);
//  }
//
//  pressed_widget = null;
//  pressed_button = 0;
//}
//
///*************************************************************************
//  ...
//*************************************************************************/
//void handle_destroyed_widgets()
//{
//  if (deferred_destroyed_widgets.foo_list_size() == 0) {
//    return;
//  }
//
//  util.freelog(Log.LOG_NORMAL, "handle destroy");
//  for (widget pwidget : deferred_destroyed_widgets.data) {
//    if ((pwidget.tooltip && pwidget.tooltip_shown)
//	|| dragged_widget == pwidget || selected_widget == pwidget
//	|| pressed_widget == pwidget) {
//      struct ct_point pos = { 32000, 32000 };
//
//      printf("  move mouse away tooltip=%d drag=%d select=%d press=%d\n",
//	     (pwidget.tooltip
//	      && pwidget.tooltip_shown), dragged_widget == pwidget,
//	     selected_widget == pwidget, pressed_widget == pwidget);
//      //be_write_osda_to_file(get_osda(pwidget), "destroyed_widget.pnm");
//
//      handle_mouse_motion(null, &pos);
//    }
//
//    real_widget_destroy(pwidget);
//    widget_list_unlink(&deferred_destroyed_widgets, pwidget);
//  } }
//}
//
///*************************************************************************
//  ...
//*************************************************************************/
//void sw_mainloop(void (*input_callback)(int socket))
//{
//  sw_paint_all();
//
//  while (1) {
//    struct be_event event;
//    struct timeval timeout;
//
//    handle_callbacks();
//
//    get_select_timeout(&timeout);    
//    be_next_event(&event, &timeout);
//
//    switch (event.type) {
//    case BE_DATA_OTHER_FD:
//      input_callback(event.socket);
//      break;
//    case BE_EXPOSE:
//      flush_all_to_screen();
//      break;
//    case BE_TIMEOUT:
//      handle_callbacks();
//      break;
//
//    case BE_MOUSE_MOTION:
//      {
//	sw_widget widget =
//	    search_widget(&event.position, EV_MOUSE);
//
//	handle_mouse_motion(widget, &event.position);
//      }
//      break;
//
//    case BE_MOUSE_PRESSED:
//      {
//	sw_widget widget =
//	    search_widget(&event.position, EV_MOUSE);
//
//	handle_mouse_press(widget, &event.position, event.button, event.state);
//      }
//      break;
//
//    case BE_MOUSE_RELEASED:
//      {
//	sw_widget widget =
//	    search_widget(&event.position, EV_MOUSE);
//
//	handle_mouse_release(widget, &event.position, event.button);
//      }
//      break;
//
//    case BE_KEY_PRESSED:
//      {
//	boolean handled = false;
//	sw_widget widget;
//
//	assert(ct_key_is_valid(&event.key));
//
//	if (selected_widget_gets_keyboard && selected_widget) {
//	  widget = selected_widget;
//	} else {
//	  widget = search_widget(&event.position, EV_KEYBOARD);
//	}
//	if (widget && widget.key) {
//	  handled = widget.key(widget, &event.key, widget.key_data);
//	}
//	if (!handled) {
//	  handled = deliver_key(&event.key);
//	}
//	if (!handled) {
//	  printf("WARNING: unhandled key stroke\n");
//	}
//      }
//      break;	    
//    default:
//      assert(0!=1);
//    }
//  }
//}
//
///*************************************************************************
//  ...
//*************************************************************************/
//void parent_needs_paint(sw_widget widget)
//{
//  if (widget.parent) {
//    widget.parent.needs_repaint = true;
//  } else {
//    widget.needs_repaint = true;
//  }
//}
//
///*************************************************************************
//  ...
//*************************************************************************/
//void widget_needs_paint(sw_widget widget)
//{
//  widget.needs_repaint = true;
//}
//
///*************************************************************************
//  ...
//*************************************************************************/
//void real_widget_destroy(sw_widget widget)
//{
//  assert(widget.type <= WT_LAST);
//  if (widget.destroy) {
//    widget.destroy(widget);
//  } else {
//    printf("WARNING: no destroy for type %d\n", widget.type);
//  }
//  if (widget.parent) {
//    sw_window_remove(widget);
//  }
//  memset(widget, 0x54, sizeof(*widget));
//
//  /* Ensure no dangling globals */
//  if (selected_widget == widget) {
//    selected_widget = null;
//  } else if (pressed_widget == widget) {
//    pressed_widget = null;
//  } else if (dragged_widget == widget) {
//    dragged_widget = null;
//  }
//
//  free(widget);
//}
//
///*************************************************************************
//  ...
//*************************************************************************/
//void sw_update_vslider_from_list(sw_widget slider,
//				 sw_widget list)
//{
//  struct ct_size view_size, window_size;
//  struct ct_point position;
//
//  sw_list_get_view_size(list, &view_size);
//  sw_list_get_window_size(list, &window_size);
//  sw_list_get_offset(list, &position);
//
//  if(0)
//  printf("LIST width=%d offset=%d size=%d\n", view_size.height, position.y,
//	 window_size.height);
//  sw_slider_set_width(slider, (float) view_size.height / window_size.height);
//  sw_slider_set_offset(slider, (float) position.y / window_size.height);
//}
//
///*************************************************************************
//  ...
//*************************************************************************/
//void sw_update_hslider_from_list(sw_widget slider,
//				 sw_widget list)
//{
//  struct ct_size view_size, window_size;
//  struct ct_point position;
//
//  sw_list_get_view_size(list, &view_size);
//  sw_list_get_window_size(list, &window_size);
//  sw_list_get_offset(list, &position);
//
//  sw_slider_set_width(slider, (float) view_size.width / window_size.width);
//  sw_slider_set_offset(slider, (float) position.x / window_size.width);
//}
//
///*************************************************************************
//  ...
//*************************************************************************/
//void sw_update_list_from_vslider(sw_widget list,
//				 sw_widget slider)
//{
//  float offset = sw_slider_get_offset(slider);
//  struct ct_size view_size, window_size;
//  struct ct_point position;
//
//  sw_list_get_view_size(list, &view_size);
//  sw_list_get_window_size(list, &window_size);
//  sw_list_get_offset(list, &position);
//  position.y = offset * window_size.height;
//  sw_list_set_offset(list, &position);
//}
//
///*************************************************************************
//  ...
//*************************************************************************/
//void sw_update_list_from_hslider(sw_widget list,
//				 sw_widget slider)
//{
//  float offset = sw_slider_get_offset(slider);
//  struct ct_size view_size, window_size;
//  struct ct_point position;
//
//  sw_list_get_view_size(list, &view_size);
//  sw_list_get_window_size(list, &window_size);
//  sw_list_get_offset(list, &position);
//  position.x = offset * window_size.width;
//  sw_list_set_offset(list, &position);
//}
//
///*************************************************************************
//  ...
//*************************************************************************/
//void sw_init()
//{
//  tr_init();
//}
}