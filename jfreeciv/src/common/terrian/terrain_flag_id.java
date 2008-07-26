package common.terrian;

/* Must match with terrain_flag_from_str in terrain.c. */
public enum terrain_flag_id {
  TER_NO_BARBS, /* No barbarians summoned on this terrain. */
  TER_NO_POLLUTION, /* This terrain cannot be polluted. */
  TER_NO_CITIES, /* No cities on this terrain. */
  TER_STARTER, /* Players will start on this terrain type. */
  TER_CAN_HAVE_RIVER, /* Terrains with this type can have Terrain_H.S_RIVER on them. */
  TER_UNSAFE_COAST,/*this tile is not safe as coast, (all ocean / ice) */ 
  TER_UNSAFE,  /*unsafe for all units (ice,...) */
  TER_OCEANIC, /* This is an ocean terrain. */
  TER_LAST
};