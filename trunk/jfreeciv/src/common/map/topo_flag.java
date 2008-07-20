package common.map;
enum topo_flag {
  /* Bit-values. */
  /* Changing these values will break map_init_topology. */
  TF_WRAPX ,//= 1,
  TF_WRAPY,// = 2,
  TF_ISO ,//= 4,
  TF_HEX //= 8
};
