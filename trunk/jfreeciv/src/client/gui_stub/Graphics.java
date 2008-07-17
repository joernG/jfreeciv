package client.gui_stub;

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
//#include <stdlib.h>
//
//#include "tilespec.h"
//
//#include "graphics.h"
//
//Sprite intro_gfx_sprite;
//Sprite radar_gfx_sprite;
//
///****************************************************************************
//  Return whether the client supports isometric view (isometric tilesets).
//****************************************************************************/
//boolean isometric_view_supported()
//{
//  /* PORTME */
//  return false;
//}
//
///****************************************************************************
//  Return whether the client supports "overhead" (non-isometric) view.
//****************************************************************************/
//boolean overhead_view_supported()
//{
//  /* PORTME */
//  return false;
//}
//
///****************************************************************************
//  Load the introductory graphics.
//****************************************************************************/
//void load_intro_gfx()
//{
//  /* PORTME */
//  intro_gfx_sprite = load_gfxfile(main_intro_filename);
//  radar_gfx_sprite = load_gfxfile(minimap_intro_filename);
//}
//
///****************************************************************************
//  Load the cursors (mouse substitute sprites), including a goto cursor,
//  an airdrop cursor, a nuke cursor, and a patrol cursor.
//****************************************************************************/
//void load_cursors()
//{
//  /* PORTME */
//}
//
///****************************************************************************
//  Frees the introductory sprites.
//****************************************************************************/
//void free_intro_radar_sprites()
//{
//  if (intro_gfx_sprite) {
//    free_sprite(intro_gfx_sprite);
//    intro_gfx_sprite = null;
//  }
//  if (radar_gfx_sprite) {
//    free_sprite(radar_gfx_sprite);
//    radar_gfx_sprite = null;
//  }
//}
//
///****************************************************************************
//  Return a null-terminated, permanently allocated array of possible
//  graphics types extensions.  Extensions listed first will be checked
//  first.
//****************************************************************************/
//final String*gfx_fileextensions()
//{
//  /* PORTME */
//
//  /* hack to allow stub to run */
//  static final String ext[] = {
//    "png",	/* png should be the default. */
//    /* ...etc... */
//    null
//  };
//
//  return ext;
//}
//
///****************************************************************************
//  Load the given graphics file into a sprite.  This function loads an
//  entire image file, which may later be broken up into individual sprites
//  with crop_sprite.
//****************************************************************************/
//Sprite load_gfxfile(final String filename)
//{
//  /* PORTME */
//  return null;
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
//  /* PORTME */
//  return null;
//}
//
///****************************************************************************
//  Find the dimensions of the sprite.
//****************************************************************************/
//void get_sprite_dimensions(Sprite sprite, int *width, int *height)
//{
//  /* PORTME */
//#if 0
//  *width = sprite.width;
//  *height = sprite.height;
//#endif
//}
//
///****************************************************************************
//  Free a sprite and all associated image data.
//****************************************************************************/
//void free_sprite(Sprite s)
//{
//  /* PORTME */
//}
}