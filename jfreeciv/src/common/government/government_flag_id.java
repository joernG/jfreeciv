package common.government;

public enum government_flag_id {
  G_BUILD_VETERAN_DIPLOMAT,//=0,	/* and Spies (in general: all F_DIPLOMAT) */
  G_REVOLUTION_WHEN_UNHAPPY,
  G_HAS_SENATE,			/* not implemented */
  G_UNBRIBABLE,
  G_INSPIRES_PARTISANS,
  G_RAPTURE_CITY_GROWTH,        /* allows city to grow by celebrating */
  G_FANATIC_TROOPS,             /* for building troops with F_FANATIC flag */
  G_NO_UNHAPPY_CITIZENS,        /* no unhappy citizen, needed by
				   fundamentism */
  G_CONVERT_TITHES_TO_MONEY,    /* tithes to money, needed by fundamentalism */
  G_REDUCED_RESEARCH,           /* penalty for research, needed by
				   fundamentalism */
  G_LAST_FLAG
};
