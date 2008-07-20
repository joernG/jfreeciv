package utility.ftwl;

public class Be_common_24_sprite{
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
//#include <png.h>
//
//#include "fcintl.h"
//#include "log.h"
//#include "mem.h"
//
//#include "back_end.h"
//#include "be_common_24.h"
//
//struct color {
//  int red, green, blue;
//};
//
///*************************************************************************
//  ...
//*************************************************************************/
//static Sprite ctor_sprite(image image)
//{
//  Sprite result = fc_malloc(sizeof(struct Sprite));
//  result.image = image;
//  return result;
//}
//
///*************************************************************************
//  ...
//*************************************************************************/
//
//void be_free_sprite(Sprite sprite)
//{
//  free(sprite);
//}
//
///*************************************************************************
//  ...
//*************************************************************************/
//Sprite be_crop_sprite(Sprite source,
//			      int x, int y, int width, int height)
//{
//  Sprite result = ctor_sprite(image_create(width, height));
//  struct ct_rect region = { x, y, width, height };
//
//  ct_clip_rect(&region, &source.image.full_rect);
//
//  image_set_mask(result.image, &result.image.full_rect, 0);
//
//  image_copy_full(source.image, result.image, &region);
//
//  return result;
//}
//
///*************************************************************************
//  ...
//*************************************************************************/
//Sprite be_load_gfxfile(final String filename)
//{
//  png_structp pngp;
//  png_infop infop;
//  png_int_32 width, height, x, y;
//  FILE *fp;
//  Sprite mysprite;
//  image xi;
//
//  fp = fopen(filename, "rb");
//  if (!fp) {
//    util.freelog(LOG_FATAL, "Failed reading PNG file: %s", filename);
//    exit(EXIT_FAILURE);
//  }
//
//  pngp = png_create_read_struct(PNG_LIBPNG_VER_STRING, null, null, null);
//  if (!pngp) {
//    util.freelog(LOG_FATAL, "Failed creating PNG struct");
//    exit(EXIT_FAILURE);
//  }
//
//  infop = png_create_info_struct(pngp);
//  if (!infop) {
//    util.freelog(LOG_FATAL, "Failed creating PNG struct");
//    exit(EXIT_FAILURE);
//  }
//  
//  if (setjmp(pngp.jmpbuf)) {
//    util.freelog(LOG_FATAL, "Failed while reading PNG file: %s", filename);
//    exit(EXIT_FAILURE);
//  }
//
//  png_init_io(pngp, fp);
//  png_read_info(pngp, infop);
//
//  width = png_get_image_width(pngp, infop);
//  height = png_get_image_height(pngp, infop);
//
//  util.freelog(Log.LOG_NORMAL, "reading '%s' (%ldx%ld) bit depth=%d color_type=%d",
//	  filename, width, height, png_get_bit_depth(pngp, infop),
//	  png_get_color_type(pngp, infop));
//
//  if (png_get_color_type(pngp,infop) == PNG_COLOR_TYPE_PALETTE) {
//    png_set_palette_to_rgb(pngp);
//  }
//
//  if (png_get_valid(pngp, infop, PNG_INFO_tRNS)) {
//    png_set_tRNS_to_alpha(pngp);
//  }
//
//  if (png_get_bit_depth(pngp,infop) == 16) {
//    png_set_strip_16(pngp);
//  }
//
//  if (png_get_bit_depth(pngp,infop) < 8) {
//    png_set_packing(pngp);
//  }
//
//  /* Add an alpha channel for RGB images */
//  if ((png_get_color_type(pngp, infop) & PNG_COLOR_MASK_ALPHA) == 0) {
//    png_set_filler(pngp, 0xff, PNG_FILLER_AFTER);
//  }
//
//  mysprite = fc_malloc(sizeof(*mysprite));
//
//  xi = image_create(width, height);
//
//  png_read_update_info(pngp, infop);
//
//  {
//    png_bytep pb;
//    png_uint_32 stride = png_get_rowbytes(pngp, infop);
//    png_bytep buf = fc_malloc(stride * height);
//    png_bytep *row_pointers = fc_malloc(height * sizeof(png_bytep));
//
//    assert(stride >= width * 4);
//
//    for (y = 0, pb = buf; y < height; y++, pb += stride) {
//      row_pointers[y] = pb;
//    }
//
//    png_read_image(pngp, row_pointers);
//    png_read_end(pngp, infop);
//    free(row_pointers);
//    png_destroy_read_struct(&pngp, &infop, null);
//    fclose(fp);
//
//    pb = buf;
//
//    for (y = 0; y < height; y++) {
//      for (x = 0; x < width; x++) {
//	png_bytep src = pb + 4 * x;
//	unsigned char *dest = IMAGE_GET_ADDRESS(xi, x, y);
//
//	dest[0] = src[0];
//	dest[1] = src[1];
//	dest[2] = src[2];
//	dest[3] = (src[3] != 0) ? 255 : 0;
//      }
//      pb += stride;
//    }
//    free(buf);
//  }
//
//  return ctor_sprite(xi);
//}
//
///*************************************************************************
//  ...
//*************************************************************************/
//void be_sprite_get_size(ct_size size, final Sprite sprite)
//{
//  size.width = sprite.image.width;
//  size.height = sprite.image.height;
//}
}