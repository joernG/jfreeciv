package common.player;

public class player {
//	/***************************************************************************
//	  On the distinction between nations(formerly races), players, and users,
//	  see doc/HACKING
//	***************************************************************************/
//
//	struct player {
//	  int player_no;
//	  char name[MAX_LEN_NAME];
	public String name;
//	  char username[MAX_LEN_NAME];
//	  bool is_male;
//	  int government;
//	  int target_government;
//	  Nation_Type_id nation;
//	  Team_Type_id team;
//	  bool is_started; /* Did the player click "start" yet? */
//	  bool turn_done;
//	  int nturns_idle;
//	  bool is_alive;
//	  bool is_observer; /* is the player a global observer */ 
//	  bool is_dying; /* set once the player is in the process of dying */
//	  bool got_tech; /* set once the player is fully dead */
//
//	  /* Turn in which the player's revolution is over; see update_revolution. */
//	  int revolution_finishes;
//
//	  bool capital; /* used to give player init_buildings in first city. */
//	  int embassy;
//	  int reputation;
//	  struct player_diplstate diplstates[MAX_NUM_PLAYERS + MAX_NUM_BARBARIANS];
//	  int city_style;
//	  struct unit_list units;
//	  struct city_list cities;
//	  struct player_score score;
//	  struct player_economic economic;
//	  struct player_research research;
//	  struct player_spaceship spaceship;
//	  int future_tech;
//	  struct player_ai ai;
//	  bool was_created;                    /* if the player was /created */
//	  bool is_connected;		       /* observers don't count */
//	  struct connection *current_conn;     /* non-null while handling packet */
//	  struct conn_list connections;	       /* will replace conn */
//	  struct worklist worklists[MAX_NUM_WORKLISTS];
//	  struct player_tile *private_map;
//	  unsigned int gives_shared_vision; /* bitvector those that give you shared vision */
//	  unsigned int really_gives_vision; /* takes into account that p3 may see what p1 has via p2 */
//	  Impr_Status improvements[B_LAST]; /* improvements with equiv_range==Player */
//	  Impr_Status *island_improv; /* improvements with equiv_range==Island, dimensioned to
//				 	 [map.num_continents][game.num_impr_types] */
//	  struct {
//	    int length;
//	    void *data;
//	  } attribute_block;
//	  struct {
//	    int length;
//	    void *data;
//	  } attribute_block_buffer;
//	  
//	  bool debug;
//	};
//
}
