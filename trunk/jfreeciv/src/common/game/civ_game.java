package common.game;

import utility.Speclists;
import utility.shared.Shared_H;

import common.Connection;
import common.player.player;

public class civ_game {
//	struct civ_game {
		  public boolean is_new_game;		/* 1 for games never started */
		  public int version;
//		  char id[MAX_ID_LEN];		/* server only */
		  public int civstyle;
		  public int gold;
//		  char start_units[MAX_LEN_STARTUNIT];
		  public int dispersion;
		  public int tech;
		  public int skill_level;
		  public int timeout;
//		  public int timeoutpublic int;     /* increase timeout every N turns... */
		  public int timeoutinc;     /* ... by this amount ... */
		  public int timeoutincmult; /* ... and multiply timeoutinc by this amount ... */
//		  public int timeoutpublic intinc;  /* ... and increase timeoutpublic int by this amount */
		  public int timeoutcounter; /* timeoutcounter - timeoutpublic int = turns to next inc. */
		  public int timeoutaddenemymove; /* increase to, when enemy move seen */
		  public int tcptimeout;
		  public int netwait;
//		  time_t last_ping;
		  public int pingtimeout;
		  public int pingtime;
//		  time_t turn_start;
		  public int end_year;
		  public int year;
		  public int turn;
		  public int researchcost; /* Multiplier on cost of new research */
		  public int diplcost, freecost, conquercost;
		  public int diplchance;
		  public int cityfactor;
		  public int citymindist;
		  public int civilwarsize;
		  public int contactturns;
		  public int rapturedelay;
		  public int min_players, max_players, nplayers;
		  public int aifill;
		  public int notradesize, fulltradesize;
		  public int barbarianrate;
		  public int onsetbarbarian;
		  public int nbarbarians;
		  public int occupychance;
		  public int unhappysize;
		  public boolean angrycitizen;
//		  char *startmessage;
		  public int player_idx;
//		  struct player *player_ptr;
		  public player[]  players = new player[Shared_H.MAX_NUM_PLAYERS + Shared_H.MAX_NUM_BARBARIANS];
		  public Speclists<Connection> all_connections;        /* including not yet established */
		  public Speclists<Connection> est_connections;        /* all established client conns */
		  public Speclists<Connection> game_connections;       /* involved in Game.game; send map etc */
//		  public int global_advances[A_LAST];             /* a counter */
//		  public int global_wonders[B_LAST];              /* contains city id's */
//		         /* global_wonders[] may also be (-1), or the id of a city
//			    which no longer exists, if the wonder has been destroyed */
//		  Impr_Status improvements[B_LAST];        /* impr. with equiv_range==World */
//
		  public int heating; /* Number of polluted squares. */
		  public int globalwarming; /* Total damage done. (counts towards a warming event.) */
		  public int warminglevel; /* If globalwarming is higher than this number there is
				       a chance of a warming event. */

		  public int cooling; /* Number of irradiated squares. */
//		  public int nuclearwpublic inter; /* Total damage done. (counts towards a cooling event.) */
		  public int coolinglevel; /* If nuclearwpublic inter is higher than this number there is
				       a chance of a cooling event. */

//		  char save_name[MAX_LEN_NAME];
		  public String save_name;
		  public int save_nturns;
		  public int save_compress_level;
		  public int foodbox;
		  public int aqueductloss;
		  public int killcitizen;
		  public int techpenalty;
		  public int razechance;
		  public boolean scorelog;
		  public int seed;
		  public int aqueduct_size;
		  public int add_to_size_limit;
		  public boolean savepalace;
		  public boolean natural_city_names;
		  public boolean spacerace;
		  public boolean turnblock;
		  public boolean fixedlength;
		  public boolean auto_ai_toggle;
		  public boolean fogofwar;
		  public boolean fogofwar_old;	/* as the fog_of_war bit get changed by setting
					   the server we need to remember the old setting */

		  public int num_unit_types;
		  public int num_impr_types;
		  public int num_tech_types;  /* including A_NONE */

		  public int government_count;
		  public int default_government;
		  public int government_when_anarchy;
		  public int ai_goal_government;	/* kludge */

		  public int nation_count;
		  public int playable_nation_count;
		  public int styles_count;

		  public int terrain_count;

		  public int watchtower_extra_vision;
		  public int watchtower_vision;
		  public int allowed_city_names;

		  public int borders;		/* distance of border from city; 0=disabled. */
		  public boolean happyborders;
		  public int diplomacy;        /* who can do it */
		  public boolean slow_invasions;  /* land units lose all movement landing on shores */

//		  char rulesetdir[MAX_LEN_NAME];
//		  public int firepower_factor;		/* See README.rulesets */
//
//		  Impr_Type_id default_building;
//		  Impr_Type_id palace_building;
//		  Impr_Type_id land_defend_building;
//
//		  struct {
//		    public int cathedral_plus;		/* eg Theology */
//		    public int cathedral_minus;	/* eg Communism */
//		    public int colosseum_plus;		/* eg Electricity */
//		    public int temple_plus;		/* eg Mysticism */
//		    public int nav;			/* AI convenience: tech_req for first
//						   non-trireme ferryboat */
//		    public int u_partisan;		/* convenience: tech_req for first
//						   Partisan unit */
//		    /* Following tech list is A_LAST terminated if shorter than
//		       max len, and the techs listed are guaranteed to exist;
//		       this could be better implemented as a new field in the
//		       units.ruleset
//		    */
//		    public int partisan_req[MAX_NUM_TECH_LIST];       /* all required for uprisings */
//		  } rtech;
//
//		  /* values from Game.game.ruleset */
//		  struct {
//		    struct {
//		      char name[MAX_LEN_NAME];
//		      public int min_size, bonus;
//		    } specialists[SP_COUNT];
//		    public boolean changable_tax;
//		    public int forced_science; /* only relevant if !changable_tax */
//		    public int forced_luxury;
//		    public int forced_gold;
//		    public int min_city_center_food;
//		    public int min_city_center_shield;
//		    public int min_city_center_trade;
//		    public int min_dist_bw_cities;
//		    public int init_vis_radius_sq;
//		    public int hut_overflight;
//		    public boolean pillage_select;
//		    public int nuke_contamination;
//		    public int granary_food_ini[MAX_GRANARY_INIS];
//		    public int granary_num_inis;
//		    public int granary_food_inc;
//		    public int tech_cost_style;
//		    public int tech_leakage;
//		    public int tech_cost_double_year;
//
//		    /* Items given to all players at Game.game start.  Server only. */
//		    public int global_init_techs[MAX_NUM_TECH_LIST];
//		    public int global_init_buildings[MAX_NUM_BUILDING_LIST];
//
//		    public boolean killstack;
//		  } rgame;
//		  
//		  struct {
//		    public int improvement_factor;
//		    public int unit_factor;
//		    public int total_factor;
//		  } incite_cost;
//
//		  char demography[MAX_LEN_DEMOGRAPHY];
//		  char allow_take[MAX_LEN_ALLOW_TAKE];
//
//		  /* used by the map editor to control game_save; could be used by the server too */
//		  struct {
//		    public boolean save_random;
//		    public boolean save_players;
//		    public boolean save_known; /* loading will just reveal the squares around cities and units */
//		    public boolean save_starts; /* start positions will be auto generated */
//		    public boolean save_private_map; /* FoW map; will be created if not saved */
//		  } save_options;
//
//		  public int trireme_loss_chance[MAX_VET_LEVELS];
//		  public int work_veteran_chance[MAX_VET_LEVELS];
//		  public int veteran_chance[MAX_VET_LEVELS];
//		  public int revolution_length; /* 0=> random length, else the fixated length */
//		};
}
