package utility.ftwl;

public class Theme_engine{
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
//
//#include "fcintl.h"
//#include "log.h"
//#include "hash.h"
//#include "mem.h"
//#include "registry.h"
//#include "support.h"
//
//#include "widget.h"
//
//#include "theme_engine.h"
//
//struct keybinding {
//  be_key key;
//  char *action;
//};
//
//#define SPECLIST_TAG keybinding
//#define SPECLIST_TYPE struct keybinding
//#include "speclist.h"
//
//#define keybinding_list_iterate(calllist, pcall) \
//    TYPED_LIST_ITERATE(struct keybinding, calllist, pcall)
//#define keybinding_list_iterate_end  LIST_ITERATE_END
//
//struct info_data {
//  boolean is_text;
//  struct ct_rect bounds;
//  ct_tooltip tooltip;
//  sw_widget widget;
//  struct {
//    enum ws_alignment alignment;
//    ct_string template;
//  } text;
//};
//
//struct button_callback {
//  te_screen screen;
//  char *id;
//};
//
//static char *current_theme;
//static char current_res[30];
//static int theme_bytes_per_pixel;
//
///*************************************************************************
//  Initialize theme engine and look for theme directory containing the
//  given example file.
//*************************************************************************/
//void te_init(final String theme, char *example_file)
//{
//  struct ct_size size;
//  be_screen_get_size(&size);
//  char filename[512];
//
//  current_theme = mystrdup(theme);
//  my_snprintf(current_res, sizeof(current_res), "%dx%d", size.width,
//	      size.height);
//
//  my_snprintf(filename, sizeof(filename), "themes/%s/%s/%s",
//	      current_theme, current_res, example_file);
//  if (!datafilename(filename)) {
//    freelog(LOG_FATAL, "ERROR: There is no theme '%s' in resolution '%s'.",
//	    current_theme, current_res);
//    exit(EXIT_FAILURE);
//  }
//}
//
///*************************************************************************
//  Initialize colour model from a palette file.
//*************************************************************************/
//void te_init_colormodel(char *name)
//{
//  char filename[512];
//  struct section_file file;
//  char *realname;
//
//  my_snprintf(filename, sizeof(filename), "themes/%s/%s/%s",
//	      current_theme, current_res, name);
//
//  section_file_init(&file);
//  realname = datafilename(filename);
//  if (!realname) {
//    freelog(LOG_FATAL, "Could not find required file %s", name);
//    assert(0);
//    exit(EXIT_FAILURE);
//  }
//  if (!section_file_load(&file, realname)) {
//    freelog(LOG_FATAL, "Could not find required file %s", filename);
//    assert(0);
//    exit(EXIT_FAILURE);
//  }
//  theme_bytes_per_pixel = secfile_lookup_int(&file, "meta.bpp") / 8;
//  section_file_free(&file);
//}
//
///*************************************************************************
//  ...
//*************************************************************************/
//Sprite te_load_gfx(final String filename)
//{
//  final int prefixes = 6;
//  char prefix[prefixes][512];
//  int i;
//
//  my_snprintf(prefix[0], sizeof(prefix[0]), "themes/%s/%s/", current_theme,
//	      current_res);
//  my_snprintf(prefix[1], sizeof(prefix[1]), "themes/%s/", current_theme);
//  my_snprintf(prefix[2], sizeof(prefix[2]), "themes/common/%s/",
//	      current_res);
//  my_snprintf(prefix[3], sizeof(prefix[3]), "themes/common/");
//  my_snprintf(prefix[4], sizeof(prefix[4]), "themes/");
//  my_snprintf(prefix[5], sizeof(prefix[5]), "%s", "");
//
//  for (i = 0; i < prefixes; i++) {
//    char fullname[512];
//    char *tmp;
//
//    my_snprintf(fullname, sizeof(fullname), "%s%s", prefix[i], filename);
//
//    tmp = datafilename(fullname);
//    if (tmp) {
//      return be_load_gfxfile(tmp);
//
//    }
//  }
//  die("Can't find %s",filename);
//  return null;
//}
//
///*************************************************************************
//  Transform a colour string to a be_color primitive.
//*************************************************************************/
//static boolean str_color_to_be_color(be_color *col, final String s)
//{
//  int values[theme_bytes_per_pixel];
//  int i;
//
//  if (s.length() != (theme_bytes_per_pixel * 2 + 1) || s[0] != '#') {
//    return false;
//  }
//
//  s++;
//  for (i = 0; i < theme_bytes_per_pixel; i++) {
//    char b[3];
//    int scanned;
//
//    b[0] = s[0];
//    b[1] = s[1];
//    b[2] = '\0';
//
//    scanned = sscanf(b, "%x", &values[i]);
//    assert(scanned == 1);
//    s += 2;
//  }
//  /* FIXME: this needs to be generalized for other bitdepths */
//  *col = be_get_color(values[0], values[1], values[2]);
//  return true;
//}
//
///*************************************************************************
//  ...
//*************************************************************************/
//be_color te_read_color(section_file file, final String section,
//		       final String prefix, final String suffix)
//{
//  be_color col;
//  if (str_color_to_be_color(&col, secfile_lookup_str(file, 
//                                   "%s.%s%s", section, prefix, suffix))) {
//    return col;
//  } else {
//    freelog(LOG_FATAL, "Wrong colour string in %s, %s.%s%s",
//            file.filename, section, prefix, suffix);
//    assert(0);
//    exit(EXIT_FAILURE);
//    return 0;
//  }
//}
//
///*************************************************************************
//  ...
//*************************************************************************/
//be_key te_read_key(section_file file, final String section,
//			   final String name)
//{
//  char *line =
//      secfile_lookup_str_default(file, null, "%s.%s", section, name);
//
//  if (line) {
//    return ct_key_parse(line);
//  } else {
//    return null;
//  }
//}
//
///*************************************************************************
//  ...
//*************************************************************************/
//ct_string te_read_string(section_file file,
//				 final String part1, final String part2,
//				 boolean need_background, boolean need_text)
//{
//  int size, outline_width;
//  char *text, *font,*transform_str;
//  boolean anti_alias;
//  be_color color, background, outline_color, dummy_color =
//      be_get_color(0, 0, 0);
//  enum cts_transform transform;
//
//  if (need_text) {
//    text = _(secfile_lookup_str(file, "%s.%s", part1, part2));
//  } else {
//    text = "unset text";
//  }
//  color = te_read_color(file, part1, part2, "-color");
//  font = secfile_lookup_str(file, "%s.%s-font", part1, part2);
//  size = secfile_lookup_int(file, "%s.%s-size", part1, part2);
//  anti_alias = secfile_lookup_bool(file, "%s.%s-antialias", part1, part2);
//  transform_str = secfile_lookup_str(file, "%s.%s-transform", part1, part2);
//
//  if (need_background) {
//    background = te_read_color(file, part1, part2, "-background");
//  } else {
//    background = dummy_color;
//  }
//
//  outline_width =
//      secfile_lookup_int(file, "%s.%s-outline-width", part1, part2);
//  if (outline_width != 0) {
//    outline_color = te_read_color(file, part1, part2, "-outline-color");
//  } else {
//    outline_color = dummy_color;
//  }
//
//  if (strcmp(transform_str, "") == 0 || strcmp(transform_str, "none") == 0) {
//    transform = CTS_TRANSFORM_NONE;
//  } else if (strcmp(transform_str, "upper") == 0) {
//    transform = CTS_TRANSFORM_UPPER;
//  } else {
//    assert(0);
//    transform = CTS_TRANSFORM_NONE;
//  }
//  return ct_string_create(font, size, color, background, text, anti_alias,
//			  outline_width, outline_color, transform);
//}
//
///*************************************************************************
//  ...
//*************************************************************************/
//ct_placement te_read_placement(section_file file,
//				       final String section)
//{
//  ct_placement result = fc_malloc(sizeof(*result));
//  char *type =
//      secfile_lookup_str(file, "%s.placement", section);
//
//  if (strcmp(type, "line") == 0) {
//    result.type = PL_LINE;
//    result.data.line.start_x =
//	secfile_lookup_int(file, "%s.placement-line-start_x", section);
//    result.data.line.start_y =
//	secfile_lookup_int(file, "%s.placement-line-start_y", section);
//    result.data.line.dx =
//	secfile_lookup_int(file, "%s.placement-line-dx", section);
//    result.data.line.dy =
//	secfile_lookup_int(file, "%s.placement-line-dy", section);
//  } else if (strcmp(type, "grid") == 0) {
//    char *last = secfile_lookup_str(file, "%s.placement-grid-last", section);
//
//    result.type = PL_GRID;
//    result.data.grid.x =
//	secfile_lookup_int(file, "%s.placement-grid-x", section);
//    result.data.grid.y =
//	secfile_lookup_int(file, "%s.placement-grid-y", section);
//    result.data.grid.dx =
//	secfile_lookup_int(file, "%s.placement-grid-dx", section);
//    result.data.grid.dy =
//	secfile_lookup_int(file, "%s.placement-grid-dy", section);
//    result.data.grid.width =
//	secfile_lookup_int(file, "%s.placement-grid-width", section);
//    result.data.grid.height =
//	secfile_lookup_int(file, "%s.placement-grid-height", section);
//    if (strcmp(last, "n") == 0) {
//      result.data.grid.last = PL_N;
//    } else if (strcmp(last, "s") == 0) {
//      result.data.grid.last = PL_S;
//    } else {
//      assert(0);
//    }
//  } else {
//    assert(0);
//  }
//  return result;
//}
//
///*************************************************************************
//  ...
//*************************************************************************/
//static void customize_window(section_file file,
//			     sw_widget window)
//{
//  char *background =
//      secfile_lookup_str_default(file, null, "screen.background");
//
//  if (background) {
//    sw_widget_set_background_sprite(window, te_load_gfx(background));
//  }
//}
//
///*************************************************************************
//  ...
//*************************************************************************/
//ct_tooltip te_read_tooltip(section_file file,
//				   final String section, boolean need_text)
//{
//  ct_tooltip result = null;
//  char *text =
//      secfile_lookup_str_default(file, null, "%s.tooltip-color", section);
//
//  if (text) {
//    ct_string string =
//	te_read_string(file, section, "tooltip", true, need_text);
//    int delay = secfile_lookup_int(file, "%s.tooltip-delay", section);
//    int shadow = secfile_lookup_int(file, "%s.tooltip-shadow", section);
//    be_color color = be_get_color(0, 0, 0);
//
//    if (shadow > 0) {
//      color = te_read_color(file, section, "", "tooltip-shadow-color");
//    }
//
//    result = ct_tooltip_create(string, delay, shadow, color);
//    ct_string_destroy(string);
//  }
//  return result;
//}
//
///*************************************************************************
//  ...
//*************************************************************************/
//static void customize_widget(section_file file, final String name,
//			     sw_widget widget)
//{
//  ct_tooltip tooltip = te_read_tooltip(file, name, true);
//
//  if (tooltip) {
//    sw_widget_set_tooltip(widget, tooltip);
//    ct_tooltip_destroy(tooltip);
//  }
//}
//
///*************************************************************************
//  ...
//*************************************************************************/
//static enum ws_alignment str_alignment_to_enum(final String s)
//{
//  if (mystrcasecmp(s, "n") == 0)
//    return A_N;
//  if (mystrcasecmp(s, "s") == 0)
//    return A_S;
//  if (mystrcasecmp(s, "w") == 0)
//    return A_W;
//  if (mystrcasecmp(s, "e") == 0)
//    return A_E;
//
//  if (mystrcasecmp(s, "nc") == 0)
//    return A_NC;
//  if (mystrcasecmp(s, "sc") == 0)
//    return A_SC;
//  if (mystrcasecmp(s, "wc") == 0)
//    return A_WC;
//  if (mystrcasecmp(s, "ec") == 0)
//    return A_EC;
//
//  if (mystrcasecmp(s, "nw") == 0)
//    return A_NW;
//  if (mystrcasecmp(s, "ne") == 0)
//    return A_NE;
//  if (mystrcasecmp(s, "sw") == 0)
//    return A_SW;
//  if (mystrcasecmp(s, "se") == 0)
//    return A_SE;
//
//  if (mystrcasecmp(s, "ns") == 0)
//    return A_NS;
//  if (mystrcasecmp(s, "we") == 0)
//    return A_WE;
//  if (mystrcasecmp(s, "center") == 0)
//    return A_CENTER;
//  assert(0);
//  return 0;
//}
//
///*************************************************************************
//  ...
//*************************************************************************/
//void te_read_bounds_alignment(section_file file,
//			      final String part1, ct_rect bounds,
//			      enum ws_alignment *alignment)
//{
//  bounds.x = secfile_lookup_int(file, "%s.x", part1);
//  bounds.y = secfile_lookup_int(file, "%s.y", part1);
//  bounds.width = secfile_lookup_int(file, "%s.width", part1);
//  bounds.height = secfile_lookup_int(file, "%s.height", part1);
//  if (alignment) {
//    *alignment =
//	str_alignment_to_enum(secfile_lookup_str(file, "%s.align", part1));
//  }
//}
//
///*************************************************************************
//  ...
//*************************************************************************/
//static void finalruct_labels(section_file file,
//			     te_screen screen)
//{
//  char **sec;
//  int num;
//  int i;
//
//  sec = secfile_get_secnames_prefix(file, "label_", &num);
//
//  for (i = 0; i < num; i++) {
//    struct ct_rect bounds;
//    enum ws_alignment alignment;
//    ct_string string;
//    sw_widget widget;
//
//    te_read_bounds_alignment(file, sec[i], &bounds, &alignment);
//    string = te_read_string(file, sec[i], "text", false, true);
//    widget =
//	sw_label_create_text_bounded(screen.window, string, &bounds,
//				     alignment);
//    customize_widget(file, sec[i], widget);
//  }
//  free(sec);
//}
//
///*************************************************************************
//  ...
//*************************************************************************/
//static void finalruct_infos_text(section_file file,
//				 te_screen screen)
//{
//  char **sec;
//  int num;
//  int i;
//  final char prefix[] = "info_text_";
//
//  sec = secfile_get_secnames_prefix(file, prefix, &num);
//
//  for (i = 0; i < num; i++) {
//    info_data data = fc_malloc(sizeof(*data));
//    char *id = mystrdup(sec[i] + prefix.length());
//    boolean inserted;
//
//    data.is_text = true;
//
//    te_read_bounds_alignment(file, sec[i], &data.bounds,
//			     &data.text.alignment);
//    data.text.template = te_read_string(file, sec[i], "text", false, false);
//    data.tooltip = te_read_tooltip(file, sec[i], true);
//    data.widget = null;
//    inserted = hash_insert(screen.widgets, id, data);
//    assert(inserted);
//
//    te_info_update(screen, id);
//  }
//  free(sec);
//}
//
///*************************************************************************
//  ...
//*************************************************************************/
//static void finalruct_infos_tooltip(section_file file,
//				    te_screen screen)
//{
//  char **sec;
//  int num;
//  int i;
//  final char prefix[] = "info_tooltip_";
//
//  sec = secfile_get_secnames_prefix(file, prefix, &num);
//
//  for (i = 0; i < num; i++) {
//    info_data data = fc_malloc(sizeof(*data));
//    char *id = mystrdup(sec[i] + prefix.length());
//    boolean inserted;
//
//    data.is_text = false;
//
//    te_read_bounds_alignment(file, sec[i], &data.bounds, null);
//
//    data.tooltip = te_read_tooltip(file, sec[i], false);
//    if (data.tooltip == null) {
//      printf("didn't found tooltip data for '%s'\n", sec[i]);
//      assert(0);
//    }
//    data.widget =
//	sw_window_create(screen.window, data.bounds.width,
//			 data.bounds.height, null, 100,
//			 false, DEPTH_MAX);
//    sw_widget_set_position(data.widget, data.bounds.x, data.bounds.y);
//    sw_window_set_draggable(data.widget, false);
//    sw_widget_set_background_color(data.widget,be_get_color(0,0,0));
//
//    inserted = hash_insert(screen.widgets, id, data);
//    assert(inserted);
//
//    te_info_update(screen, id);
//  }
//  free(sec);
//}
//
///*************************************************************************
//  ...
//*************************************************************************/
//static void finalruct_edits(section_file file,
//			    te_screen screen)
//{
//  char **sec;
//  int num;
//  int i;
//
//  sec = secfile_get_secnames_prefix(file, "edit_", &num);
//
//  for (i = 0; i < num; i++) {
//    struct ct_rect bounds;
//    enum ws_alignment alignment;
//    ct_string template, *string;
//    sw_widget widget;
//    char *id = mystrdup(strchr(sec[i], '_') + 1);
//    boolean inserted;
//
//    te_read_bounds_alignment(file, sec[i], &bounds, &alignment);
//    template = te_read_string(file, sec[i], "text", true, false);
//    string =
//	ct_string_clone3(template, screen.env.edit_get_initial_value(id));
//    widget =
//	sw_edit_create_bounded(screen.window,
//			       screen.env.edit_get_width(id), string,
//			       &bounds, alignment);
//    customize_widget(file, sec[i], widget);
//    inserted = hash_insert(screen.widgets, id, widget);
//    assert(inserted);
//  }
//  free(sec);
//}
//
///*************************************************************************
//  ...
//*************************************************************************/
//static void my_button_callback(sw_widget list, void *data)
//{
//  button_callback callback = data;
//
//  callback.screen.env.button_callback(callback.id);
//}
//
///*************************************************************************
//  ...
//*************************************************************************/
//static void finalruct_buttons(section_file file,
//			      te_screen screen)
//{
//  char **sec;
//  int num;
//  int i;
//
//  sec = secfile_get_secnames_prefix(file, "button_", &num);
//
//  for (i = 0; i < num; i++) {
//    struct ct_rect bounds;
//    enum ws_alignment alignment;
//    ct_string string = null;
//    sw_widget widget;
//    char *id = mystrdup(strchr(sec[i], '_') + 1);
//    char *background;
//    button_callback callback = fc_malloc(sizeof(*callback));
//
//    te_read_bounds_alignment(file, sec[i], &bounds, &alignment);
//    if (secfile_lookup_str_default(file, null, "%s.text", sec[i])) {
//      string = te_read_string(file, sec[i], "text", false, true);
//    }
//    background = secfile_lookup_str(file, "%s.background", sec[i]);
//    widget =
//	sw_button_create_bounded(screen.window, string,
//				 te_load_gfx(background), &bounds,
//				 alignment);
//    customize_widget(file, sec[i], widget);
//
//    callback.screen = screen;
//    callback.id = id;
//    sw_button_set_callback(widget, my_button_callback, callback);
//  }
//  free(sec);
//}
//
///*************************************************************************
//  ...
//*************************************************************************/
//section_file te_open_themed_file(final String name)
//{
//  section_file result = fc_malloc(sizeof(*result));
//  char filename[512],*tmp;
//
//  my_snprintf(filename, sizeof(filename), "themes/%s/%s/%s",
//	      current_theme, current_res, name);
//  tmp = datafilename_required(filename);
//
//  if (!section_file_load(result, tmp)) {
//    free(result);
//    freelog(LOG_FATAL, _("Could not load screen file \"%s\"."), filename);
//    exit(EXIT_FAILURE);
//    return null;
//  }
//  return result;
//}
//
///*************************************************************************
//  ...
//*************************************************************************/
//static boolean my_key_handler(sw_widget widget,
//			   final be_key key, void *data)
//{
//  te_screen screen = data;
//
//  keybinding_list_iterate(*screen.keybindings, pbinding) {
//    if (ct_key_matches(pbinding.key, key)) {
//      screen.env.action_callback(pbinding.action);
//      return true;
//    }
//  } }
//
//  return false;
//}
//
///*************************************************************************
//  ...
//*************************************************************************/
//static void read_keybindings(section_file file,
//			     te_screen screen)
//{
//  int num;
//  int i;
//  char **sec = secfile_get_section_entries(file, "key_bindings", &num);
//
//  screen.keybindings = fc_malloc(sizeof(*(screen.keybindings)));
//
//  keybinding_list_init(screen.keybindings);
//
//  for (i = 0; i < num; i++) {
//    char *key = sec[i];
//    char *action = secfile_lookup_str(file, "key_bindings.%s", sec[i]);
//    keybinding binding = fc_malloc(sizeof(*binding));
//
//    binding.key = ct_key_parse(key);
//    binding.action = mystrdup(action);
//    keybinding_list_insert(screen.keybindings, binding);
//  }
//
//  sw_window_set_key_notify(screen.window, my_key_handler, screen);
//}
//
///*************************************************************************
//  ...
//*************************************************************************/
//te_screen te_get_screen(sw_widget parent_window,
//				final String screen_name,
//				final te_screen_env env, int depth)
//{
//  te_screen result = fc_malloc(sizeof(*result));
//  section_file file;
//  char filename[512];
//
//  result.env = *env;
//  result.window = sw_window_create_by_clone(parent_window, depth);
//  result.widgets = hash_new(hash_fval_string, hash_fcmp_string);
//  sw_widget_set_position(result.window, 0, 0);
//
//  my_snprintf(filename, sizeof(filename), "%s.screen", screen_name);
//
//  file = te_open_themed_file(filename);
//
//  customize_window(file, result.window);
//  finalruct_labels(file, result);
//  finalruct_infos_text(file, result);
//  finalruct_infos_tooltip(file, result);
//  finalruct_edits(file, result);
//  finalruct_buttons(file, result);
//  read_keybindings(file, result);
//
//  section_file_check_unused(file, filename);
//  section_file_free(file);
//  free(file);
//
//  return result;
//}
//
///*************************************************************************
//  ...
//*************************************************************************/
//void te_info_update(te_screen screen, final String id)
//{
//  info_data data = hash_lookup_data(screen.widgets, id);
//
//  assert(data);
//
//  if (data.is_text) {
//    ct_string string;
//
//    if (data.widget) {
//      sw_widget_destroy(data.widget);
//      data.widget = null;
//    }
//    string =
//	ct_string_clone3(data.text.template,
//			 screen.env.info_get_value(id));
//
//    data.widget =
//	sw_label_create_text_bounded(screen.window, string, &data.bounds,
//				     data.text.alignment);
//    if (data.tooltip) {
//      sw_widget_set_tooltip(data.widget, data.tooltip);
//    }
//  } else {
//    sw_widget_set_tooltip(data.widget,
//			  ct_tooltip_clone1(data.tooltip,
//					    screen.env.info_get_value(id)));
//  }
//}
//
///*************************************************************************
//  ...
//*************************************************************************/
//final String te_edit_get_current_value(te_screen screen,
//				      final String id)
//{
//  sw_widget widget = hash_lookup_data(screen.widgets, id);
//  return sw_edit_get_text(widget);
//}
//
///*************************************************************************
//  ...
//*************************************************************************/
//void te_destroy_screen(te_screen screen)
//{
//  // FIXME free info and button structs
//
//  sw_widget_destroy(screen.window);
//  free(screen);
//}
}