package client.gui_xaw;

public class Graphics{

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
//#include <ctype.h>
//#include <limits.h>
//#include <stdio.h>
//#include <stdlib.h>
//#include <string.h>
//
//#include <X11/Xlib.h>
//#include <X11/Intrinsic.h>
//
//#include <png.h>
//
//#include "fcintl.h"
//#include "game.h"
//#include "log.h"
//#include "mem.h"
//#include "shared.h"
//#include "support.h"
//#include "unit.h"
//#include "version.h"
//
//#include "climisc.h"
//#include "colors.h"
//#include "gui_main.h"
//#include "mapview_g.h"
//#include "options.h"
//#include "tilespec.h"
//
//#include "graphics.h"
//
//#include "goto_cursor.xbm"
//#include "goto_cursor_mask.xbm"
//#include "drop_cursor.xbm"
//#include "drop_cursor_mask.xbm"
//#include "nuke_cursor.xbm"
//#include "nuke_cursor_mask.xbm"
//#include "patrol_cursor.xbm"
//#include "patrol_cursor_mask.xbm"
//
//Sprite intro_gfx_sprite;
//Sprite radar_gfx_sprite;
//
//Cursor goto_cursor;
//Cursor drop_cursor;
//Cursor nuke_cursor;
//Cursor patrol_cursor;
//
//static Sprite ctor_sprite(Pixmap mypixmap, int width, int height);
//static Sprite ctor_sprite_mask(Pixmap mypixmap, Pixmap mask, 
// 				       int width, int height);
//
///***************************************************************************
//...
//***************************************************************************/
//boolean isometric_view_supported()
//{
//  return true;
//}
//
///***************************************************************************
//...
//***************************************************************************/
//boolean overhead_view_supported()
//{
//  return true;
//}
//
///***************************************************************************
//...
//***************************************************************************/
//#define COLOR_MOTTO_FACE    "#2D71E3"
//
//void load_intro_gfx()
//{
//  int tot, lin, y, w;
//  char s[64];
//  XColor face;
//  int have_face;
//  final String motto = freeciv_motto();
//  XFontSetExtents *exts;
//
//  /* metrics */
//
//  exts = XExtentsOfFontSet(main_font_set);
//  lin = exts.max_logical_extent.height;
//
//  /* get colors */
//
//  if(XParseColor(display, cmap, COLOR_MOTTO_FACE, &face) &&
//     XAllocColor(display, cmap, &face)) {
//    have_face = true;
//  } else {
//    face.pixel = colors_standard[COLOR_STD_WHITE];
//    have_face = false;
//  }
//
//  /* Main graphic */
//
//  intro_gfx_sprite=load_gfxfile(main_intro_filename);
//  tot=intro_gfx_sprite.width;
//
//  y=intro_gfx_sprite.height-(2*lin);
//
//  w = XmbTextEscapement(main_font_set, motto, motto.length());
//  XSetForeground(display, font_gc, face.pixel);
//  XmbDrawString(display, intro_gfx_sprite.pixmap,
//      	      main_font_set, font_gc, 
//	      tot/2-w/2, y, 
//	      motto, motto.length());
//
//  /* Minimap graphic */
//
//  radar_gfx_sprite=load_gfxfile(minimap_intro_filename);
//  tot=radar_gfx_sprite.width;
//
//  y = radar_gfx_sprite.height - (lin +
//      1.5 * (exts.max_logical_extent.height + exts.max_logical_extent.y));
//
//  w = XmbTextEscapement(main_font_set, word_version(), strlen(word_version()));
//  XSetForeground(display, font_gc, colors_standard[COLOR_STD_BLACK]);
//  XmbDrawString(display, radar_gfx_sprite.pixmap,
//      	      main_font_set, font_gc, 
//	      (tot/2-w/2)+1, y+1, 
//	      word_version(), strlen(word_version()));
//  XSetForeground(display, font_gc, colors_standard[COLOR_STD_WHITE]);
//  XmbDrawString(display, radar_gfx_sprite.pixmap,
//      	      main_font_set, font_gc, 
//	      tot/2-w/2, y, 
//	      word_version(), strlen(word_version()));
//
//  y+=lin;
//
//  my_snprintf(s, sizeof(s), "%d.%d.%d%s",
//	      MAJOR_VERSION, MINOR_VERSION,
//	      PATCH_VERSION, VERSION_LABEL);
//  w = XmbTextEscapement(main_font_set, s, s.length());
//  XSetForeground(display, font_gc, colors_standard[COLOR_STD_BLACK]);
//  XmbDrawString(display, radar_gfx_sprite.pixmap,
//      	      main_font_set, font_gc, 
//	      (tot/2-w/2)+1, y+1, s, s.length());
//  XSetForeground(display, font_gc, colors_standard[COLOR_STD_WHITE]);
//  XmbDrawString(display, radar_gfx_sprite.pixmap,
//      	      main_font_set, font_gc, 
//	      tot/2-w/2, y, s, s.length());
//
//  /* free colors */
//
//  if (have_face)
//    XFreeColors(display, cmap, &(face.pixel), 1, 0);
//
//  /* done */
//
//  return;
//}
//
///****************************************************************************
//  Create a new sprite by cropping and taking only the given portion of
//  the image.
//****************************************************************************/
//Sprite crop_sprite(Sprite source,
//			   int x, int y, int width, int height,
//			   Sprite mask,
//			   int mask_offset_x, int mask_offset_y)
//{
//  Pixmap mypixmap, mymask;
//  GC plane_gc;
//
//  mypixmap = XCreatePixmap(display, root_window,
//			   width, height, display_depth);
//  XCopyArea(display, source.pixmap, mypixmap, civ_gc, 
//	    x, y, width, height, 0, 0);
//
//  if (source.has_mask) {
//    mymask = XCreatePixmap(display, root_window, width, height, 1);
//
//    plane_gc = XCreateGC(display, mymask, 0, null);
//    XCopyArea(display, source.mask, mymask, plane_gc, 
//	      x, y, width, height, 0, 0);
//    XFreeGC(display, plane_gc);
//
//    if (mask) {
//      XGCValues values;
//
//      values.function = GXand;
//
//      plane_gc = XCreateGC(display, mymask, GCFunction, &values);
//      XCopyArea(display, mask.mask, mymask, plane_gc,
//		x - mask_offset_x, y - mask_offset_y, width, height, 0, 0);
//      XFreeGC(display, plane_gc);
//    }
//
//    return ctor_sprite_mask(mypixmap, mymask, width, height);
//  } else if (mask) {
//    mymask = XCreatePixmap(display, root_window, width, height, 1);
//
//    plane_gc = XCreateGC(display, mymask, 0, null);
//    XCopyArea(display, source.mask, mymask, plane_gc, 
//	      x, y, width, height, 0, 0);
//    XFreeGC(display, plane_gc);
//    return ctor_sprite_mask(mypixmap, mymask, width, height);
//  } else {
//    return ctor_sprite(mypixmap, width, height);
//  }
//}
//
///****************************************************************************
//  Find the dimensions of the sprite.
//****************************************************************************/
//void get_sprite_dimensions(Sprite sprite, int *width, int *height)
//{
//  *width = sprite.width;
//  *height = sprite.height;
//}
//
///***************************************************************************
//...
//***************************************************************************/
//void load_cursors()
//{
//  Pixmap pixmap, mask;
//  XColor white, black;
//
//  white.pixel = colors_standard[COLOR_STD_WHITE];
//  black.pixel = colors_standard[COLOR_STD_BLACK];
//  XQueryColor(display, cmap, &white);
//  XQueryColor(display, cmap, &black);
//
//  /* goto */
//  pixmap =
//      XCreateBitmapFromData(display, root_window, goto_cursor_bits,
//			    goto_cursor_width, goto_cursor_height);
//  mask =
//      XCreateBitmapFromData(display, root_window,
//			    goto_cursor_mask_bits,
//			    goto_cursor_mask_width, goto_cursor_mask_height);
//  goto_cursor = XCreatePixmapCursor(display, pixmap, mask,
//				    &white, &black,
//				    goto_cursor_x_hot, goto_cursor_y_hot);
//  XFreePixmap(display, pixmap);
//  XFreePixmap(display, mask);
//
//  /* drop */
//  pixmap =
//      XCreateBitmapFromData(display, root_window, drop_cursor_bits,
//			    drop_cursor_width, drop_cursor_height);
//  mask =
//      XCreateBitmapFromData(display, root_window,
//			    drop_cursor_mask_bits,
//			    drop_cursor_mask_width, drop_cursor_mask_height);
//  drop_cursor = XCreatePixmapCursor(display, pixmap, mask,
//				    &white, &black,
//				    drop_cursor_x_hot, drop_cursor_y_hot);
//  XFreePixmap(display, pixmap);
//  XFreePixmap(display, mask);
//
//  /* nuke */
//  pixmap =
//      XCreateBitmapFromData(display, root_window, nuke_cursor_bits,
//			    nuke_cursor_width, nuke_cursor_height);
//  mask =
//      XCreateBitmapFromData(display, root_window,
//			    nuke_cursor_mask_bits,
//			    nuke_cursor_mask_width, nuke_cursor_mask_height);
//  nuke_cursor = XCreatePixmapCursor(display, pixmap, mask,
//				    &white, &black,
//				    nuke_cursor_x_hot, nuke_cursor_y_hot);
//  XFreePixmap(display, pixmap);
//  XFreePixmap(display, mask);
//
//  /* patrol */
//  pixmap =
//      XCreateBitmapFromData(display, root_window,
//			    patrol_cursor_bits, patrol_cursor_width,
//			    patrol_cursor_height);
//  mask =
//      XCreateBitmapFromData(display, root_window,
//			    patrol_cursor_mask_bits,
//			    patrol_cursor_mask_width,
//			    patrol_cursor_mask_height);
//  patrol_cursor = XCreatePixmapCursor(display, pixmap, mask,
//				      &white, &black,
//				      patrol_cursor_x_hot, patrol_cursor_y_hot);
//  XFreePixmap(display, pixmap);
//  XFreePixmap(display, mask);
//}
//
///***************************************************************************
//...
//***************************************************************************/
//static Sprite ctor_sprite(Pixmap mypixmap, int width, int height)
//{
//  Sprite mysprite=fc_malloc(sizeof(struct Sprite));
//  mysprite.pixmap=mypixmap;
//  mysprite.width=width;
//  mysprite.height=height;
//  mysprite.pcolorarray = null;
//  mysprite.has_mask=0;
//  return mysprite;
//}
//
///***************************************************************************
//...
//***************************************************************************/
//static Sprite ctor_sprite_mask(Pixmap mypixmap, Pixmap mask, 
//				       int width, int height)
//{
//  Sprite mysprite=fc_malloc(sizeof(struct Sprite));
//  mysprite.pixmap=mypixmap;
//  mysprite.mask=mask;
//
//  mysprite.width=width;
//  mysprite.height=height;
//  mysprite.pcolorarray = null;
//  mysprite.has_mask=1;
//  return mysprite;
//}
//
///***************************************************************************
// Returns the filename extensions the client supports
// Order is important.
//***************************************************************************/
//final String*gfx_fileextensions()
//{
//  static final String ext[] =
//  {
//    "png",
//    null
//  };
//
//  return ext;
//}
//
///***************************************************************************
//  Converts an image to a pixmap...
//***************************************************************************/
//static Pixmap image2pixmap(XImage *xi)
//{
//  Pixmap ret;
//  XGCValues values;
//  GC gc;
//  
//  ret = XCreatePixmap(display, root_window, xi.width, xi.height, xi.depth);
//
//  values.foreground = 1;
//  values.background = 0;
//  gc = XCreateGC(display, ret, GCForeground | GCBackground, &values);
//
//  XPutImage(display, ret, gc, xi, 0, 0, 0, 0, xi.width, xi.height);
//  XFreeGC(display, gc);
//  return ret;
//}
//
///***************************************************************************
//...
//***************************************************************************/
//Sprite load_gfxfile(final String filename)
//{
//  png_structp pngp;
//  png_infop infop;
//  png_int_32 width, height, x, y;
//  FILE *fp;
//  int npalette, ntrans;
//  png_colorp palette;
//  png_bytep trans;
//  png_bytep buf, pb;
//  png_uint_32 stride;
//  unsigned long *pcolorarray;
//  boolean *ptransarray;
//  Sprite mysprite;
//  XImage *xi;
//  int has_mask;
//
//  fp = fopen(filename, "rb");
//  if (!fp) {
//    freelog(LOG_FATAL, "Failed reading PNG file: %s", filename);
//    exit(EXIT_FAILURE);
//  }
//
//  pngp = png_create_read_struct(PNG_LIBPNG_VER_STRING, null, null, null);
//  if (!pngp) {
//    freelog(LOG_FATAL, "Failed creating PNG struct");
//    exit(EXIT_FAILURE);
//  }
//
//  infop = png_create_info_struct(pngp);
//  if (!infop) {
//    freelog(LOG_FATAL, "Failed creating PNG struct");
//    exit(EXIT_FAILURE);
//  }
//  
//  if (setjmp(pngp.jmpbuf)) {
//    freelog(LOG_FATAL, "Failed while reading PNG file: %s", filename);
//    exit(EXIT_FAILURE);
//  }
//
//  png_init_io(pngp, fp);
//
//  png_set_strip_16(pngp);
//  png_set_packing(pngp);
//
//  png_read_info(pngp, infop);
//  width = png_get_image_width(pngp, infop);
//  height = png_get_image_height(pngp, infop);
//
//  if (png_get_PLTE(pngp, infop, &palette, &npalette)) {
//    int i;
//    XColor *mycolors;
//
//    pcolorarray = fc_malloc(npalette * sizeof(*pcolorarray));
//
//    mycolors = fc_malloc(npalette * sizeof(*mycolors));
//
//    for (i = 0; i < npalette; i++) {
//      mycolors[i].red  = palette[i].red << 8;
//      mycolors[i].green = palette[i].green << 8;
//      mycolors[i].blue = palette[i].blue << 8;
//    }
//
//    alloc_colors(mycolors, npalette);
//
//    for (i = 0; i < npalette; i++) {
//      pcolorarray[i] = mycolors[i].pixel;
//    }
//
//    free(mycolors);
//  } else {
//    freelog(LOG_FATAL, "PNG file has no palette: %s", filename);
//    exit(EXIT_FAILURE);
//  }
//
//  has_mask = png_get_tRNS(pngp, infop, &trans, &ntrans, null);
//
//  if (has_mask) {
//    int i;
//
//    ptransarray = fc_calloc(npalette, sizeof(*ptransarray));
//
//    for (i = 0; i < ntrans; i++) {
//      ptransarray[trans[i]] = true;
//    }
//  } else {
//    ptransarray = null;
//  }
//
//  png_read_update_info(pngp, infop);
//
//  {
//    png_bytep *row_pointers;
//
//    stride = png_get_rowbytes(pngp, infop);
//    buf = fc_malloc(stride * height);
//
//    row_pointers = fc_malloc(height * sizeof(png_bytep));
//
//    for (y = 0, pb = buf; y < height; y++, pb += stride) {
//      row_pointers[y] = pb;
//    }
//
//    png_read_image(pngp, row_pointers);
//    png_read_end(pngp, infop);
//    fclose(fp);
//
//    free(row_pointers);
//    png_destroy_read_struct(&pngp, &infop, null);
//  }
//
//  mysprite = fc_malloc(sizeof(*mysprite));
//
//
//  xi = XCreateImage(display, DefaultVisual(display, screen_number),
//		    display_depth, ZPixmap, 0, null, width, height, 32, 0);
//  xi.data = fc_calloc(xi.bytes_per_line * xi.height, 1);
//
//  pb = buf;
//  for (y = 0; y < height; y++) {
//    for (x = 0; x < width; x++) {
//      XPutPixel(xi, x, y, pcolorarray[pb[x]]);
//    }
//    pb += stride;
//  }
//  mysprite.pixmap = image2pixmap(xi);
//  XDestroyImage(xi);
//
//  if (has_mask) {
//    XImage *xm;
//
//    xm = XCreateImage(display, DefaultVisual(display, screen_number),
//		      1, XYBitmap, 0, null, width, height, 8, 0);
//    xm.data = fc_calloc(xm.bytes_per_line * xm.height, 1);
//
//    pb = buf;
//    for (y = 0; y < height; y++) {
//      for (x = 0; x < width; x++) {
//	XPutPixel(xm, x, y, !ptransarray[pb[x]]);
//      }
//      pb += stride;
//    }
//    mysprite.mask = image2pixmap(xm);
//    XDestroyImage(xm);
//  }
//
//  mysprite.has_mask = has_mask;
//  mysprite.width = width;
//  mysprite.height = height;
//  mysprite.pcolorarray = pcolorarray;
//  mysprite.ncols = npalette;
//
//  if (has_mask) {
//    free(ptransarray);
//  }
//  free(buf);
//  return mysprite;
//}
//
///***************************************************************************
//   Deletes a sprite.  These things can use a lot of memory.
//***************************************************************************/
//void free_sprite(Sprite s)
//{
//  XFreePixmap(display, s.pixmap);
//  if (s.has_mask) {
//    XFreePixmap(display, s.mask);
//  }
//  if (s.pcolorarray) {
//    free_colors(s.pcolorarray, s.ncols);
//    free(s.pcolorarray);
//    s.pcolorarray = null;
//  }
//  free(s);
//}
//
///***************************************************************************
//...
//***************************************************************************/
//Pixmap create_overlay_unit(int i)
//{
//  Pixmap pm;
//  enum color_std bg_color;
//  
//  pm=XCreatePixmap(display, root_window, 
//		   UNIT_TILE_WIDTH, UNIT_TILE_HEIGHT, display_depth);
//
//  /* Give tile a background color, based on the type of unit */
//  switch (get_unit_type(i).move_type) {
//    case LAND_MOVING: bg_color = COLOR_STD_GROUND; break;
//    case SEA_MOVING:  bg_color = COLOR_STD_OCEAN; break;
//    case HELI_MOVING: bg_color = COLOR_STD_YELLOW; break;
//    case AIR_MOVING:  bg_color = COLOR_STD_CYAN; break;
//    default:          bg_color = COLOR_STD_BLACK; break;
//  }
//  XSetForeground(display, fill_bg_gc, colors_standard[bg_color]);
//  XFillRectangle(display, pm, fill_bg_gc, 0,0, 
//		 UNIT_TILE_WIDTH, UNIT_TILE_HEIGHT);
//
//  /* If we're using flags, put one on the tile */
//  if(!solid_color_behind_units)  {
//    Sprite flag=get_nation_by_plr(game.player_ptr).flag_sprite;
//
//    XSetClipOrigin(display, civ_gc, 0,0);
//    XSetClipMask(display, civ_gc, flag.mask);
//    XCopyArea(display, flag.pixmap, pm, civ_gc, 0,0, 
//    	      flag.width,flag.height, 0,0);
//    XSetClipMask(display, civ_gc, None);
//  }
//
//  /* Finally, put a picture of the unit in the tile */
//  if(i<game.num_unit_types) {
//    Sprite s=get_unit_type(i).sprite;
//
//    XSetClipOrigin(display,civ_gc,0,0);
//    XSetClipMask(display,civ_gc,s.mask);
//    XCopyArea(display, s.pixmap, pm, civ_gc,
//	      0,0, s.width,s.height, 0,0 );
//    XSetClipMask(display,civ_gc,None);
//  }
//
//  return(pm);
//}
//
//
///***************************************************************************
//  This function is so that packhand.c can be gui-independent, and
//  not have to deal with Sprites itself.
//***************************************************************************/
//void free_intro_radar_sprites()
//{
//  if (intro_gfx_sprite) {
//    free_sprite(intro_gfx_sprite);
//    intro_gfx_sprite=null;
//  }
//  if (radar_gfx_sprite) {
//    free_sprite(radar_gfx_sprite);
//    radar_gfx_sprite=null;
//  }
//}
}