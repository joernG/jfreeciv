package client.gui_mui;

public class Mapview{

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
//#include <assert.h>
//#include <errno.h>
//#include <stdio.h>
//#include <stdlib.h>
//#include <string.h>
//
//#include <libraries/mui.h>
//
//#include <clib/alib_protos.h>
//#include <proto/exec.h>
//#include <proto/muimaster.h>
//#include <proto/intuition.h>
//
//#include "fcintl.h"
//#include "Game.game.h"
//#include "government.h"		/* government_graphic() */
//#include "Map.map.h"
//#include "player.h"
//#include "rand.h"
//#include "support.h"		/* myusleep() */
//#include "timing.h"
//
//#include "civclient.h"
//#include "control.h"
//#include "goto.h"
//#include "graphics.h"
//#include "gui_main.h"
//#include "options.h"
//#include "tilespec.h"
//#include "climisc.h"
//#include "mapctrl_g.h"
//
//#include "mapview.h"
//
///* Amiga Client stuff */
//
//#include "autogroupclass.h"
//#include "muistuff.h"
//#include "mapclass.h"
//#include "overviewclass.h"
//
///**************************************************************************
// Some support functions
//**************************************************************************/
//int get_map_x_start()
//{
//  return xget(main_map_area, MUIA_Map_HorizFirst);
//}
//
//int get_map_y_start()
//{
//  return xget(main_map_area, MUIA_Map_VertFirst);
//}
//
//int get_map_x_visible()
//{
//  return xget(main_map_area, MUIA_Map_HorizVisible);
//}
//
//int get_map_y_visible()
//{
//  return xget(main_map_area, MUIA_Map_VertVisible);
//}
//
///**************************************************************************
// This function is called to decrease a unit's HP smoothly in battle
// when combat_animation is turned on.
//**************************************************************************/
//void decrease_unit_hp_smooth(unit punit0, int hp0,
//			     unit punit1, int hp1)
//{
//  static timer anim_timer = null; 
//  unit losing_unit = (hp0 == 0 ? punit0 : punit1);
//
//  set_units_in_combat(punit0, punit1);
//
//  do
//  {
//    anim_timer = renew_timer_start(anim_timer, TIMER_USER, TIMER_ACTIVE);
//
//    if (punit0.hp > hp0
//	&& Rand.myrand((punit0.hp - hp0) + (punit1.hp - hp1)) < punit0.hp - hp0)
//      punit0.hp--;
//    else if (punit1.hp > hp1)
//      punit1.hp--;
//    else
//      punit0.hp--;
//
//    refresh_tile_mapcanvas(punit0.x, punit0.y, true);
//    refresh_tile_mapcanvas(punit1.x, punit1.y, true);
//
//    usleep_since_timer_start(anim_timer, 10000);
//
//  } while (punit0.hp > hp0 || punit1.hp > hp1);
//
//  DoMethod(main_map_area, MUIM_Map_ExplodeUnit, losing_unit);
//
//  set_units_in_combat(null, null);
//
//  refresh_tile_mapcanvas(punit0.x, punit0.y, true);
//  refresh_tile_mapcanvas(punit1.x, punit1.y, true);
//}
//
///**************************************************************************
// Set the dimensions of the overviewmap
//**************************************************************************/
//void set_overview_dimensions(int x, int y)
//{
//  if (main_overview_area)
//  {
//    if ((xget(main_overview_area, MUIA_Overview_Width) == x) &&
//	(xget(main_overview_area, MUIA_Overview_Height) == y))
//    {
//      /* If nothing has changed simply return */
//      return;
//    }
//  }
//
//  DoMethod(main_overview_group, MUIM_Group_InitChange);
//
//  if (main_overview_area)
//  {
//    DoMethod(main_overview_group, OM_REMMEMBER, main_overview_area);
//    MUI_DisposeObject(main_overview_area);
//  }
//
//  if ((main_overview_area = MakeOverview(x, y)))
//  {
//    set(main_map_area, MUIA_Map_Overview, main_overview_area);
//    DoMethod(main_overview_group, OM_ADDMEMBER, main_overview_area);
//  }
//
//  DoMethod(main_overview_group, MUIM_Group_ExitChange);
//}
//
//
///**************************************************************************
//...
//**************************************************************************/
//void update_turn_done_button(boolean do_restore)
//{
//  static boolean flip = false;
//
//  if (!get_turn_done_button_state()) {
//    return;
//  }
//
//  if ((do_restore && flip) || !do_restore) {
//    if (!flip) {
//      set(main_turndone_button, MUIA_Background, MUII_FILL);
//    } else {
//      set(main_turndone_button, MUIA_Background, MUII_ButtonBack);
//    }
//    flip = !flip;
//  }
//}
//
///**************************************************************************
// Update the timeout label
//**************************************************************************/
//void update_timeout_label()
//{
//  settext(main_timeout_text, get_timeout_label_text());
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void update_info_label()
//{
//  int d;
//
//  settextf(main_people_text, "Population: %s",
//	   population_to_text(civ_population(Game.game.player_ptr)));
//  settextf(main_year_text, "Year: %s", Shared.textyear(Game.game.year));
//  settextf(main_gold_text, "Gold: %d", Game.game.player_ptr.economic.gold);
//  settextf(main_tax_text, "Tax:%d Lux:%d Sci:%d",
//	   Game.game.player_ptr.economic.tax,
//	   Game.game.player_ptr.economic.luxury,
//	   Game.game.player_ptr.economic.science);
//
//  set_indicator_icons(client_research_sprite(),
//		      client_warming_sprite(),
//		      client_cooling_sprite(),
//		      Game.game.player_ptr.government);
//
//  d = 0;
//  for (; d < (Game.game.player_ptr.economic.luxury) / 10; d++)
//    set(main_econ_sprite[d], MUIA_Sprite_Sprite, sprites.tax_luxury);
//
//  for (; d < (Game.game.player_ptr.economic.science + Game.game.player_ptr.economic.luxury) / 10; d++)
//    set(main_econ_sprite[d], MUIA_Sprite_Sprite, sprites.tax_science);
//
//  for (; d < 10; d++)
//    set(main_econ_sprite[d], MUIA_Sprite_Sprite, sprites.tax_gold);
//
//  update_timeout_label();
//}
//
///**************************************************************************
// Callback if a below unit was selected
//**************************************************************************/
//void activate_below_unit (int *id)
//{
//  unit punit = Game.find_unit_by_id(*id);
//  if (punit) set_unit_focus_and_select(punit);
//}
//
///**************************************************************************
// Update the information label which gives info on the current unit and the
// square under the current unit, for specified unit.  Note that in practice
// punit is almost always (or maybe strictly always?) the focus unit.
// Clears label if punit is null.
// Also updates the cursor for the map_canvas (this is related because the
// info label includes a "select destination" prompt etc).
// Also calls update_unit_pix_label() to update the icons for units on this
// square.
// (Note, that in the Mui client the last part is handled different)
//**************************************************************************/
//void update_unit_info_label(unit punit)
//{
//  if (punit)
//  {
//    city pcity;
//    int units = 0;
//    pcity = Player_P.player_find_city_by_id(Game.game.player_ptr, punit.homecity);
//
//    settextf(main_unitname_text, "%s%s", punit.unit_type().name,
//	     (punit.veteran) ? " (veteran)" : "");
//    settext(main_moves_text, (hover_unit == punit.id) ? "Select destination" : unit_activity_text(punit));
//    settext(main_terrain_text, map_get_tile_info_text(punit.tile));
//    settext(main_hometown_text, pcity ? pcity.name : "");
//
//
//    /* Count the number of units */
//    unit_list_iterate(map_get_tile(punit.tile).units, aunit) {
//      if (aunit != punit) {
//	units++;
//      }
//    }
//    }
//
//    if (xget(main_below_group, MUIA_AutoGroup_NumObjects) == units + 1)
//    {
//      List child_list = (struct List*)xget(main_below_group,MUIA_Group_ChildList);
//      Object *cstate = (Object *)child_list.lh_Head;
//      Object *child;
//
//      unit_list_iterate(map_get_tile(punit.tile).units, aunit) {
//        if (aunit != punit) {
//          if ((child = (Object*)NextObject(&cstate))) {
//            set(child, MUIA_Unit_Unit, aunit);
//            DoMethod(child, MUIM_KillNotify, MUIA_Pressed);
//	    /* Activate the unit if pressed, note that the object may get's disposed */
//	    DoMethod(child, MUIM_Notify, MUIA_Pressed, false, app, 7, MUIM_Application_PushMethod, app, 4, MUIM_CallHook, &civstandard_hook, activate_below_unit, aunit.id);
//          }
//        }
//      }
//      }
//    } else
//    {
//      Object *obj;
//      DoMethod(main_below_group, MUIM_Group_InitChange);
//      DoMethod(main_below_group, MUIM_AutoGroup_DisposeChilds);
//
//      unit_list_iterate(map_get_tile(punit.tile).units, aunit) {
//        if (aunit != punit) {
//	  if ((obj = UnitObject, MUIA_InputMode, MUIV_InputMode_RelVerify, MUIA_Unit_Unit, aunit, End)) {
//	    DoMethod(main_below_group, OM_ADDMEMBER, obj);
//	    /* Activate the unit if pressed, note that the object may get's disposed */
//	    DoMethod(obj, MUIM_Notify, MUIA_Pressed, false, app, 7, MUIM_Application_PushMethod, app, 4, MUIM_CallHook, &civstandard_hook, activate_below_unit, aunit.id);
//	  }
//        }
//      }
//      }
//
//      DoMethod(main_below_group, OM_ADDMEMBER, RectangleObject, MUIA_Weight,0,End);
//      DoMethod(main_below_group, MUIM_Group_ExitChange);
//    }
//  }
//  else
//  {
//    settext(main_unitname_text, "");
//    settext(main_moves_text, "");
//    settext(main_terrain_text, "");
//    settext(main_hometown_text, "");
//
//    if (xget(main_below_group, MUIA_AutoGroup_NumObjects) > 1)
//    {
//      DoMethod(main_below_group, MUIM_Group_InitChange);
//      DoMethod(main_below_group, MUIM_AutoGroup_DisposeChilds);
//      DoMethod(main_below_group, OM_ADDMEMBER, RectangleObject, MUIA_Weight,0,End);
//      DoMethod(main_below_group, MUIM_Group_ExitChange);
//    }
//
//  }
//
//  set(main_unit_unit, MUIA_Unit_Unit, punit);
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void set_indicator_icons(int bulb, int sol, int flake, int gov)
//{
//  Sprite gov_sprite;
//
//  bulb = CLIP(0, bulb, NUM_TILES_PROGRESS - 1);
//  sol = CLIP(0, sol, NUM_TILES_PROGRESS - 1);
//  flake = CLIP(0, flake, NUM_TILES_PROGRESS-1);
//
//  set(main_bulb_sprite, MUIA_Sprite_Sprite, sprites.bulb[bulb]);
//  set(main_sun_sprite, MUIA_Sprite_Sprite, sprites.warming[sol]);
//  set(main_flake_sprite, MUIA_Sprite_Sprite, sprites.cooling[flake]);
//
//  if (Game.game.government_count == 0)
//  {
//    /* not sure what to do here */
//    /* HACK: the UNHAPPY citizen is used for the government
//     * when we don't know any better. */
//    struct citizen_type c = {.type = CITIZEN_UNHAPPY};
//
//    gov_sprite = get_citizen_sprite(c, 0, null);
//  }
//  else
//  {
//    gov_sprite = get_government(gov).sprite;
//  }
//  set(main_government_sprite, MUIA_Sprite_Sprite, gov_sprite);
//}
//
///**************************************************************************
//  Draw a single frame of animation.  This function needs to clear the old
//  image and draw the new one.  It must flush output to the display.
//**************************************************************************/
//void draw_unit_animation_frame(unit punit,
//			       boolean first_frame, boolean last_frame,
//			       int old_canvas_x, int old_canvas_y,
//			       int new_canvas_x, int new_canvas_y)
//{
//	DoMethod(main_map_area, MUIM_Map_DrawUnitAnimationFrame,
//		punit, first_frame, last_frame, old_canvas_x, old_canvas_y, new_canvas_x, new_canvas_y);
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void set_map_xy_start(int new_map_view_x0, int new_map_view_y0)
//{
//  SetAttrs(main_map_area,
//	   MUIA_Map_HorizFirst, new_map_view_x0,
//	   MUIA_Map_VertFirst, new_map_view_y0,
//	   TAG_DONE);
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void refresh_overview_viewrect()
//{
//  DoMethod(main_overview_area, MUIM_Overview_Refresh);
//}
//
///****************************************************************************
//  Draw a description for the given city.  This description may include the
//  name, turns-to-grow, production, and city turns-to-build (depending on
//  client options).
//
//  (canvas_x, canvas_y) gives the location on the given canvas at which to
//  draw the description.  This is the location of the city itself so the
//  text must be drawn underneath it.  pcity gives the city to be drawn,
//  while (*width, *height) should be set by show_ctiy_desc to contain the
//  width and height of the text block (centered directly underneath the
//  city's tile).
//****************************************************************************/
//void show_city_desc(city pcity, int canvas_x, int canvas_y)
//{
//	DoMethod(main_map_area, MUIM_Map_ShowCityDesc, pcity, canvas_x, canvas_y);
//}
//
///**************************************************************************
//Refresh and draw to sceen all the tiles in a rectangde width,height (as
//seen in overhead ciew) with the top corner at x,y.
//All references to "left","right", "top" and "bottom" refer to the sides of
//the rectangle width, height as it would be seen in top-down view, unless
//said otherwise.
//The trick is to draw tiles furthest up on the map first, since we will be
//drawing on top of them when we draw tiles further down.
//
//Works by first refreshing map_canvas_store and then drawing the result to
//the screen.
//**************************************************************************/
//void update_map_canvas(int x, int y, int width, int height, 
//		       boolean write_to_screen)
//{
//  DoMethod(main_map_area, MUIM_Map_Refresh, x, y, width, height, write_to_screen);
//}
//
///**************************************************************************
//  Mark the rectangular region as 'dirty' so that we know to flush it
//  later.
//**************************************************************************/
//void dirty_rect(int canvas_x, int canvas_y,
//		int pixel_width, int pixel_height)
//{
//  /* PORTME */
//}
//
///**************************************************************************
//  Mark the entire screen area as "dirty" so that we can flush it later.
//**************************************************************************/
//void dirty_all()
//{
//  /* PORTME */
//}
//
///**************************************************************************
//  Flush all regions that have been previously marked as dirty.  See
//  dirty_rect and dirty_all.  This function is generally called after we've
//  processed a batch of drawing operations.
//**************************************************************************/
//void flush_dirty()
//{
//  /* PORTME */
//}
//
///**************************************************************************
// Update display of descriptions associated with cities on the main Map.map.
//**************************************************************************/
//void update_city_descriptions()
//{
//  update_map_canvas_visible();
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void put_nuke_mushroom_pixmaps(int abs_x0, int abs_y0)
//{
//  DoMethod(main_map_area, MUIM_Map_DrawMushroom, abs_x0, abs_y0);
//}
//
///**************************************************************************
// Draws a cross-hair overlay on a tile
//**************************************************************************/
//void put_cross_overlay_tile(int x, int y)
//{
//  DoMethod(main_map_area, MUIM_Map_PutCrossTile, x, y);
//}
//
///**************************************************************************
// Shade the tiles around a city to indicate the location of workers
//**************************************************************************/
//void put_city_workers(city pcity, int color)
//{
//  DoMethod(main_map_area, MUIM_Map_PutCityWorkers, pcity, color);
//}
//
///**************************************************************************
//draw a line from src_x,src_y . dest_x,dest_y on both map_canvas and
//map_canvas_store
//**************************************************************************/
//void draw_segment(int src_x, int src_y, int dir)
//{
//  DoMethod(main_map_area, MUIM_Map_DrawSegment, src_x, src_y, dir);
//}
//
///**************************************************************************
// Area Selection
//**************************************************************************/
//void draw_selection_rectangle(int canvas_x, int canvas_y, int w, int h)
//{
//  /* PORTME */
//}
//
///**************************************************************************
//  This function is called when the tileset is changed.
//**************************************************************************/
//void tileset_changed()
//{
//  /* PORTME */
//  /* Here you should do any necessary redraws (for instance, the city
//   * dialogs usually need to be resized).
//   */
//}
}