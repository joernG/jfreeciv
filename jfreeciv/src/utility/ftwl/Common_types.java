package utility.ftwl;

public class Common_types{
///**********************************************************************
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
//#include <assert.h>
//#include <stdio.h>
//
//#include "mem.h"
//#include "support.h"
//
//#include "back_end.h"
//#include "text_renderer.h"
//
//#include "common_types.h"
//
//static struct {
//  int type;
//  char *name;
//} keymap[] = {
//  {
//  BE_KEY_LEFT, "Left"}, {
//  BE_KEY_RIGHT, "Right"}, {
//  BE_KEY_UP, "Up"}, {
//  BE_KEY_DOWN, "Down"}, {
//  BE_KEY_RETURN, "Return"}, {
//  BE_KEY_ENTER, "Enter"}, {
//  BE_KEY_BACKSPACE, "Backspace"}, {
//  BE_KEY_DELETE, "Del"}, {
//  BE_KEY_ESCAPE, "Esc"}, {
//  BE_KEY_SPACE, "Space"}, {
//  BE_KEY_KP_0, "Kp0"}, {
//  BE_KEY_KP_1, "Kp1"}, {
//  BE_KEY_KP_2, "Kp2"}, {
//  BE_KEY_KP_3, "Kp3"}, {
//  BE_KEY_KP_4, "Kp4"}, {
//  BE_KEY_KP_5, "Kp5"}, {
//  BE_KEY_KP_6, "Kp6"}, {
//  BE_KEY_KP_7, "Kp7"}, {
//  BE_KEY_KP_8, "Kp8"}, {
//  BE_KEY_KP_9, "Kp9"}, {
//  BE_KEY_PRINT, "Print"}
//};
//
///*************************************************************************
//  ...
//*************************************************************************/
//boolean ct_rect_valid(final ct_rect rect)
//{
//  return rect.x >= 0 && rect.y >= 0 && rect.width >= 0 && rect.height >= 0;
//}
//
///*************************************************************************
//  ...
//*************************************************************************/
//boolean ct_point_valid(final ct_point point)
//{
//  return point.x >= 0 && point.y >= 0;
//}
//
///*************************************************************************
//  ...
//*************************************************************************/
//boolean ct_rect_in_rect_list(final ct_rect item,
//			  final region_list region_list)
//{
//  region_list_iterate(*region_list, container) {
//    if (ct_rect_in_rect(item, container)) {
//      return true;
//    }
//  } }
//  return false;
//}
//
///*************************************************************************
//  ...
//*************************************************************************/
//boolean ct_rect_in_rect(final ct_rect item,
//		     final ct_rect container)
//		    
//{
//    assert(ct_rect_valid(container) && ct_rect_valid(item));
//
//    return (item.x >= container.x && item.y >= container.y &&
//	    item.x + item.width <= container.x + container.width &&
//	    item.y + item.height <= container.y + container.height);
//}
//
///*************************************************************************
//  ...
//*************************************************************************/
//boolean ct_point_in_rect(final ct_point item,
//		      final ct_rect container)
//{
//    assert(ct_rect_valid(container) && ct_point_valid(item));
//
//    return (item.x >= container.x && item.y >= container.y &&
//	    item.x < container.x + container.width &&
//	    item.y < container.y + container.height);
//}
//
///*************************************************************************
//  ...
//*************************************************************************/
//ct_string ct_string_create(final String font, int size,
//				   be_color foreground,
//				   be_color background,
//				   final String text, boolean anti_alias,
//				   int outline_width,
//				   be_color outline_color,
//				   enum cts_transform transform)
//{
//  ct_string result = fc_malloc(sizeof(*result));
//
//  result.font = (font);
//  result.font_size = size;
//  result.foreground = foreground;
//  result.background = background;
//  result.text = (text);
//  result.anti_alias = anti_alias;
//  result.outline_width = outline_width;
//  result.outline_color = outline_color;
//  result.transform = transform;
//
//  if (transform == CTS_TRANSFORM_NONE) {
//    /* nothing */
//  } else if (transform == CTS_TRANSFORM_UPPER) {
//    size_t len = strlen(result.text);
//    int i;
//
//    for (i = 0; i < len; i++) {
//      result.text[i] = my_toupper(result.text[i]);
//    }
//  } else {
//    assert(0!=1);
//  }
//      
//  /* split the string */
//  {
//      int row;
//      char *s, *tmp = (result.text);
//
//      result.rows = 1;
//      for (s = tmp; *s != '\0'; s++) {
//	if (*s == '\n') {
//	  result.rows++;
//	}
//      }
//      result.row = fc_malloc(sizeof(*result.row) * result.rows);
//      s = tmp;
//
//      for (row = 0; row < result.rows; row++) {
//	char *end = strchr(s, '\n');
//
//	if (end) {
//	  *end = '\0';
//	  result.row[row].text = (s);
//	  s = end + 1;
//	} else {
//	  result.row[row].text = (s);
//	}
//	//printf("[%d]='%s'\n", row, result.row[row].text);
//      }
//      free(tmp);
//  }
//  
//  tr_prepare_string(result);
//  be_string_get_size(&result.size, result);
//
//  return result;
//}
//
///*************************************************************************
//  ...
//*************************************************************************/
//void ct_string_destroy(ct_string string)
//{
//  int row;
//
//  free(string.font);
//  free(string.text);
//  tr_free_string(string);
//  for (row = 0; row < string.rows; row++) {
//    free(string.row[row].text);
//  }
//  free(string.row);
//  free(string);
//}
//
///*************************************************************************
//  ...
//*************************************************************************/
//ct_string ct_string_clone(final ct_string orig)
//{
//  return ct_string_create(orig.font, orig.font_size, orig.foreground,
//			  orig.background, orig.text, orig.anti_alias,
//			  orig.outline_width, orig.outline_color,
//			  orig.transform);
//}
//
///*************************************************************************
//  ...
//*************************************************************************/
//ct_string ct_string_clone1(final ct_string orig,
//				   int new_size)
//{
//  return ct_string_create(orig.font, new_size, orig.foreground,
//			  orig.background, orig.text, orig.anti_alias,
//			  orig.outline_width, orig.outline_color,
//			  orig.transform);
//}
//
///*************************************************************************
//  ...
//*************************************************************************/
//ct_string ct_string_clone2(final ct_string orig,
//				   int new_size, final String new_text)
//{
//  return ct_string_create(orig.font, new_size, orig.foreground,
//			  orig.background, new_text, orig.anti_alias,
//			  orig.outline_width, orig.outline_color,
//			  orig.transform);
//}
//
///*************************************************************************
//  ...
//*************************************************************************/
//ct_string ct_string_clone3(final ct_string orig,
//				   final String new_text)
//{
//  return ct_string_create(orig.font, orig.font_size, orig.foreground,
//			  orig.background, new_text, orig.anti_alias,
//			  orig.outline_width, orig.outline_color,
//			  orig.transform);
//}
//
///*************************************************************************
//  ...
//*************************************************************************/
//ct_string ct_string_clone4(final ct_string orig,
//				   final String new_text, be_color new_color)
//{
//  return ct_string_create(orig.font, orig.font_size, new_color,
//			  orig.background, new_text, orig.anti_alias,
//			  orig.outline_width, orig.outline_color,
//			  orig.transform);
//}
//
///*************************************************************************
//  ...
//*************************************************************************/
//ct_string ct_string_wrap(final ct_string orig, int max_width)
//{
//  ct_string result;
//  int columns;
//
//  for (columns = 100; columns > 1; columns--) {
//    char *copy = (orig.text);
//
//    wordwrap_string(copy, columns);
//    result = ct_string_clone3(orig, copy);
//    free(copy);
//    if (result.size.width <= max_width) {
//      return result;
//    }
//  }
//  assert(0!=1);
//  return null;
//}
//
///*************************************************************************
//  ...
//*************************************************************************/
//final String ct_rect_to_string(final ct_rect rect)
//{
//  static char buffer[100];
//
//  snprintf(buffer, 100, "{x=%d, y=%d, width=%d, height=%d}", rect.x,
//	   rect.y, rect.width, rect.height);
//  return buffer;
//}
//
///*************************************************************************
//  ...
//*************************************************************************/
//boolean ct_rect_equal(final ct_rect rect1, final ct_rect rect2)
//{
//  return ct_rect_in_rect(rect1, rect2) && ct_rect_in_rect(rect2, rect1);
//}
//
///*************************************************************************
//  ...
//*************************************************************************/
//void ct_rect_intersect(ct_rect dest, final ct_rect src)
//{
//  int last_x = Math.min(dest.x + dest.width, src.x + src.width);
//  int last_y = Math.min(dest.y + dest.height, src.y + src.height);
//
//  assert(ct_rect_valid(dest) && ct_rect_valid(src));
//  dest.x = MAX(dest.x, src.x);
//  dest.y = MAX(dest.y, src.y);
//
//  dest.width = MAX(0, last_x - dest.x);
//  dest.height = MAX(0, last_y - dest.y);
//  assert(ct_rect_valid(dest));
//}
//
///*************************************************************************
//  ...
//*************************************************************************/
//void ct_clip_point(ct_point to_draw, final ct_rect available)
//{
//  to_draw.x =
//      CLIP(available.x, to_draw.x, available.x + available.width-1);
//  to_draw.y =
//      CLIP(available.y, to_draw.y, available.y + available.height-1);
//  assert(ct_point_in_rect(to_draw, available));
//}
//
///*************************************************************************
//  ...
//*************************************************************************/
//void ct_clip_rect(ct_rect to_draw, final ct_rect available)
//{
//  struct ct_point p1 = { to_draw.x, to_draw.y };
//  struct ct_point p2 =
//      { to_draw.x + to_draw.width-1, to_draw.y + to_draw.height-1 };
//
//  ct_clip_point(&p1, available);
//  ct_clip_point(&p2, available);
//
//  ct_rect_fill_on_2_points(to_draw, &p1, &p2);
//  assert(ct_rect_in_rect(to_draw, available));
//}
//
///*************************************************************************
//  ...
//*************************************************************************/
//ct_rect ct_rect_clone(final ct_rect src)
//{
//  ct_rect result = fc_malloc(sizeof(*result));
//
//  *result = *src;
//  return result;
//}
//
///*************************************************************************
//  ...
//*************************************************************************/
//boolean ct_rect_empty(final ct_rect rect)
//{
//  return rect.width == 0 || rect.height == 0;
//}
//
///*************************************************************************
//  ...
//*************************************************************************/
//boolean ct_size_empty(final ct_size size)
//{
//  return size.width == 0 || size.height == 0;
//}
//
///*************************************************************************
//  ...
//*************************************************************************/
//void ct_rect_fill_on_2_points(ct_rect rect,
//			      final ct_point point1,
//			      final ct_point point2)
//{
//  rect.x = Math.min(point1.x, point2.x);
//  rect.y = Math.min(point1.y, point2.y);
//  rect.width = MAX(point1.x, point2.x) - rect.x+1;
//  rect.height = MAX(point1.y, point2.y) - rect.y+1;
//}
//
///*************************************************************************
//  ...
//*************************************************************************/
//ct_tooltip ct_tooltip_create(final ct_string text,
//				     int delay, int shadow,
//				     be_color shadow_color)
//{
//  ct_tooltip result = fc_malloc(sizeof(*result));
//
//  result.text = ct_string_clone(text);
//  result.delay = delay;
//  result.shadow = shadow;
//  result.shadow_color = shadow_color;
//
//  return result;
//}
//
///*************************************************************************
//  ...
//*************************************************************************/
//void ct_tooltip_destroy(ct_tooltip tooltip)
//{
//    ct_string_destroy(tooltip.text);
//    free(tooltip);
//}
//
///*************************************************************************
//  ...
//*************************************************************************/
//ct_tooltip ct_tooltip_clone(final ct_tooltip orig)
//{
//  return ct_tooltip_create(orig.text, orig.delay, orig.shadow,
//			   orig.shadow_color);
//}
//
///*************************************************************************
//  ...
//*************************************************************************/
//ct_tooltip ct_tooltip_clone1(final ct_tooltip orig,
//				     final String new_text)
//{
//  ct_tooltip result;
//  ct_string s = ct_string_clone3(orig.text, new_text);
//
//  result = ct_tooltip_create(s, orig.delay, orig.shadow,
//			     orig.shadow_color);
//  ct_string_destroy(s);
//  return result;
//}
//
///*************************************************************************
//  ...
//*************************************************************************/
//void ct_get_placement(final ct_placement placement,
//		      ct_point dest, int i, int num)
//{
//  if (placement.type == PL_LINE) {
//    dest.x = placement.data.line.start_x + i * placement.data.line.dx;
//    dest.y = placement.data.line.start_y + i * placement.data.line.dy;
//  } else if (placement.type == PL_GRID) {
//    int x = i / placement.data.grid.height;
//    int y = i % placement.data.grid.height;
//
//    if (placement.data.grid.last == PL_S) {
//      y = placement.data.grid.height - y-1;
//    }
//    dest.x = placement.data.grid.x + x * placement.data.grid.dx;
//    dest.y = placement.data.grid.y + y * placement.data.grid.dy;
//  } else {
//    assert(0!=1);
//  }
//}
//
///*************************************************************************
//  ...
//*************************************************************************/
//final String ct_key_format(final be_key key)
//{
//  static char out[100];
//  char buffer[100];
//  if (key.type == BE_KEY_NORMAL) {
//    snprintf(buffer, sizeof(buffer), "%c", key.key);
//  } else {
//    int i;
//    boolean found = false;
//
//    for (i = 0; i < ARRAY_SIZE(keymap); i++) {
//      if (keymap[i].type == key.type) {
//	snprintf(buffer, sizeof(buffer), "%s", keymap[i].name);
//	found = true;
//	break;
//      }
//    }
//    assert(found);
//  }
//
//  snprintf(out, sizeof(out), "%s%s%s%s",
//	   (key.alt ? "Alt-" : ""),
//	   (key.control ? "Ctrl-" : ""),
//	   (key.shift ? "Shift-" : ""), buffer);
//  return out;
//}
//
///*************************************************************************
//  ...
//*************************************************************************/
//be_key ct_key_parse(final String string)
//{
//  be_key result = fc_malloc(sizeof(*result));
//  final char alt[] = "Alt-";
//  final char ctrl[] = "Ctrl-";
//  final char shift[] = "Shift-";
//  final String orig=string;
//  int i;
//  boolean found = false;
//
//
//  result.alt = false;
//  result.control = false;
//  result.shift = false;
//
//  if (strncmp(string, alt, alt.length()) == 0) {
//    result.alt = true;
//    string += alt.length();
//  }
//  if (strncmp(string, ctrl, ctrl.length()) == 0) {
//    result.control = true;
//    string += ctrl.length();
//  }
//  if (strncmp(string, shift, shift.length()) == 0) {
//    result.shift = true;
//    string += shift.length();
//  }
//
//  for (i = 0; i < ARRAY_SIZE(keymap); i++) {
//    if (strcmp(string, keymap[i].name) == 0) {
//      result.type = keymap[i].type;
//      found = true;
//      break;
//    }
//  }
//  if (!found) {
//    if (string.length() != 1) {
//      util.die("key description '%s' can't be parsed", orig);
//    }
//    result.type = BE_KEY_NORMAL;
//    result.key = string[0];
//  }
//
//  if (!ct_key_is_valid(result)) {
//    util.die("key description '%s' isn't valid", orig);
//  }
//  return result;
//}
//
///*************************************************************************
//  ...
//*************************************************************************/
//boolean ct_key_matches(final be_key template,
//		    final be_key actual_key)
//{
//  if (template.alt && !actual_key.alt)
//    return false;
//  if (template.control && !actual_key.control)
//    return false;
//  if (template.shift && !actual_key.shift)
//    return false;
//  if (template.type != actual_key.type)
//    return false;
//  if (template.type == BE_KEY_NORMAL && template.key != actual_key.key)
//    return false;
//  return true;
//}
//
///*************************************************************************
//  ...
//*************************************************************************/
//be_key ct_key_clone(final be_key key)
//{
//  be_key result = fc_malloc(sizeof(*result));
//  *result = *key;
//  return result;
//}
//
///*************************************************************************
//  ...
//*************************************************************************/
//void ct_key_destroy(be_key key)
//{
//  free(key);
//}
//
///*************************************************************************
//  ...
//*************************************************************************/
//boolean ct_key_is_valid(final be_key key)
//{
//  if (key.shift && key.type == BE_KEY_NORMAL && key.key != ' ') {
//    return false;
//  }
//
//  return true;
//}
}