package common.player;
import server.spaceace.player_spaceship;
import utility.Speclists;
import utility.shared.Shared_H;

import common.Connection;
import common.city.city;
import common.unit.unit;

public class player {
	//	/***************************************************************************
	//	  On the distinction between nations(formerly races), players, and users,
	//	  see doc/HACKING
	//	***************************************************************************/
	//
	//	struct player {
	public int player_no;
	//	  char name[MAX_LEN_NAME];
	public String name;
	//	  char username[MAX_LEN_NAME];
	public String  username= "";
	public boolean is_male;
	public int government;
	public int target_government;
	//	  Nation_Type_id nation;
	public int nation;
	//	  Team_Type_id team;
	public boolean is_started; /* Did the player click "start" yet? */
	public boolean turn_done;
	public int nturns_idle;
	public boolean is_alive;
	public boolean is_observer; /* is the player a global observer */ 
	public boolean is_dying; /* set once the player is in the process of dying */
	public boolean got_tech; /* set once the player is fully dead */

	/* Turn in which the player's revolution is over; see update_revolution. */
	public int revolution_finishes;

	public boolean capital; /* used to give player init_buildings in first city. */
	public int embassy;
	public int reputation;
	public player_diplstate diplstates[] = new player_diplstate[Shared_H.MAX_NUM_PLAYERS + Shared_H.MAX_NUM_BARBARIANS];
	public int city_style;
	public Speclists<unit> units;
	public Speclists<city> cities;
	//	  struct player_score score;
	public player_economic economic;
	//	  struct player_research research;
	public player_spaceship spaceship;
	public int future_tech;
	public player_ai ai;
	public boolean was_created;                    /* if the player was /created */
	public boolean is_connected;		       /* observers don't count */
	//	  Connection current_conn;     /* non-null while handling packet */
	public Speclists<Connection> connections;	       /* will replace conn */
	//	  struct worklist worklists[MAX_NUM_WORKLISTS];
	//	  struct player_tile *private_map;
	//	  unsigned public int gives_shared_vision; /* bitvector those that give you shared vision */
	//	  unsigned public int really_gives_vision; /* takes into account that p3 may see what p1 has via p2 */
	//	  Impr_Status improvements[B_LAST]; /* improvements with equiv_range==Player */
	//	  Impr_Status *island_improv; /* improvements with equiv_range==Island, dimensioned to
	//				 	 [Map.map.num_continents][Game.game.num_impr_types] */
	//	  struct {
	//	    public int length;
	//	    void *data;
	//	  } attribute_block;
	//	  struct {
	//	    public int length;
	//	    void *data;
	//	  } attribute_block_buffer;
	//	  
	//	  public boolean debug;
	//	};
	//
	/***************************************************************************
	 * Locate the city where the players palace is located, (null Otherwise)
	 **************************************************************************/
	public city find_palace()
	{
		for (city pcity : cities.data) {
			if (pcity.is_capital()) {
				return pcity;
			}
		} 
		return null;
	}


}
