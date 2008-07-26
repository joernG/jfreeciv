package common.tech;
public enum tech_flag_id {
  TF_BONUS_TECH, /* player gets extra tech if rearched first */
  TF_BOAT_FAST,  /* all sea units get one extra move point */
  TF_BRIDGE,    /* "Settler" unit types can build bridges over rivers */
  TF_RAILROAD,  /* "Settler" unit types can build rail roads */
  TF_FORTRESS,  /* "Settler" unit types can build fortress */
  TF_WATCHTOWER, /* Units get enhanced visionrange in a fortress (=fortress acts also as a watchtower) */
  TF_POPULATION_POLLUTION_INC,  /* Increase the pollution factor created by popultaion by one */
  TF_TRADE_REVENUE_REDUCE, /* When known by the player establishing a trade route 
                              reduces the initial revenue by cumulative factors of 2/3 */
  TF_AIRBASE,   /* "Airbase" unit types can build Airbases */
  TF_FARMLAND,  /* "Settler" unit types can build farmland */
  TF_REDUCE_TRIREME_LOSS1, /* Reduces chance of Trireme being lost at sea */
  TF_REDUCE_TRIREME_LOSS2, /* Reduces chance of Trireme being lost at sea */
  TF_BUILD_AIRBORNE, /* Player can build air units */
  TF_LAST
};