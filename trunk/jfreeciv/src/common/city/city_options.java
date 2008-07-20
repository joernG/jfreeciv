package common.city;
public enum city_options {
  /* The first 4 are whether to auto-attack versus each unit move_type
   * from with auto-attack units within this city.  Note that these
   * should stay the first four, and must stay in the same order as
   * enum unit_move_type.  
   *
   * The next is whether building a settler at size 1 disbands a city.
   *
   * The following 2 are what to do of new citizens when the city grows:
   * make them workers, scientists, or taxmen. Should have only one set,
   * or if neither is set, that means make workers.
   *
   * Any more than 8 options requires a protocol extension, since
   * we only send 8 bits.
   */
  CITYO_ATT_LAND, //=0, 
  CITYO_ATT_SEA, CITYO_ATT_HELI, CITYO_ATT_AIR,
  CITYO_DISBAND, CITYO_NEW_EINSTEIN, CITYO_NEW_TAXMAN
};
