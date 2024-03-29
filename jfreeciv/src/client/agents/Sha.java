package client.agents;

public class Sha{

// Freeciv - Copyright (C) 2004 - A. Gorshenev
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
//#include "log.h"
//#include "Map.map.h"
//#include "support.h"
//
//#include "agents.h"
//
//#include "sha.h"
//
///**************************************************************************
//This is the simple historian agent.
//It just saves the last states of all tiles and units.
//The trick is just to call this agent the last of all
//so it still keeps old values whereas all other agents 
//allready got the new ones.
//**************************************************************************/
//
//static tile previous_tiles = null;
//static Speclists<unit> previous_units;
//
///**************************************************************************
//...
//**************************************************************************/
//static void sha_tile_update(tile ptile)
//{
//  util.freelog(Log.LOG_DEBUG, "sha got tile: %d ~= (%d, %d)",
//	  ptile.index, TILE_XY(ptile));
//
//#if 0
//  previous_tiles[ptile.index] = *ptile;
//#endif
//}
//
///**************************************************************************
//...
//**************************************************************************/
//static void sha_unit_change(int id)
//{
//  unit punit = Game.find_unit_by_id(id);
//  unit pold_unit = unit_list_find(&previous_units, id);
//
//  util.freelog(Log.LOG_DEBUG, "sha got unit: %d", id);
//
//  assert(pold_unit);
//  *pold_unit = *punit;
//}
//
///**************************************************************************
//...
//**************************************************************************/
//static void sha_unit_new(int id)
//{
//  unit punit = Game.find_unit_by_id(id);
//  unit pold_unit = create_unit_virtual(get_player(punit.owner),
//					       null, 0, 0);
//
//  util.freelog(Log.LOG_DEBUG, "sha got unit: %d", id);
//
//  *pold_unit = *punit;
//  &previous_units.foo_list_insert(pold_unit);
//}
//
///**************************************************************************
//...
//**************************************************************************/
//static void sha_unit_remove(int id)
//{
//  unit pold_unit = unit_list_find(&previous_units, id);;
//
//  util.freelog(Log.LOG_DEBUG, "sha got unit: %d", id);
//
//  assert(pold_unit);
//  unit_list_unlink(&previous_units, pold_unit);
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void simple_historian_init()
//{
//  struct agent self;
//
//  previous_tiles = fc_malloc(Map_H.MAX_MAP_INDEX * sizeof(*previous_tiles));
//  memset(previous_tiles, 0, Map_H.MAX_MAP_INDEX * sizeof(*previous_tiles));
//
//  previous_units.foo_list_init();
//
//  memset(&self, 0, sizeof(self));
//  self.name = String.format( "Simple Historian");
//
//  self.level = LAST_AGENT_LEVEL;
//
//  self.unit_callbacks[CB_REMOVE] = sha_unit_remove;
//  self.unit_callbacks[CB_CHANGE] = sha_unit_change;
//  self.unit_callbacks[CB_NEW] = sha_unit_new;
//  self.tile_callbacks[CB_REMOVE] = sha_tile_update;
//  self.tile_callbacks[CB_CHANGE] = sha_tile_update;
//  self.tile_callbacks[CB_NEW] = sha_tile_update;
//  register_agent(&self);
//}
//
///**************************************************************************
//Public interface
//**************************************************************************/
//
///**************************************************************************
//...
//**************************************************************************/
//tile sha_tile_recall(tile ptile)
//{
//  return &previous_tiles[ptile.index];
//}
//
///**************************************************************************
//...
//**************************************************************************/
//unit sha_unit_recall(int id)
//{
//  return unit_list_find(&previous_units, id);
//}
}