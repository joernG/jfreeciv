package common.packet_gen;

import utility.Speclists;
import common.Connection;
import common.worklist;
import common.city.city_tile_type;

public class packet_city_info {
//	struct packet_city_info {
		  public int  id;
		  public int  owner;
		  public int  x;
		  public int  y;
//		  char name[MAX_LEN_NAME];
		  public String name;
		  public int  size;
		  public int  ppl_happy[]=new int[5];
		  public int  ppl_content[]=new int[5];
		  public int  ppl_unhappy[]=new int[5];
		  public int  ppl_angry[]=new int[5];
		  public int  specialists[];//=new int[SP_COUNT];
		  public int  food_prod;
		  public int  shield_prod;
		  public int  trade_prod;
		  public int  food_surplus;
		  public int  shield_surplus;
		  public int  tile_trade;
		  public int  food_stock;
		  public int  shield_stock;
		  public int  corruption;
		  public int  trade[];//=new int[NUM_TRADEROUTES];
		  public int  trade_value[];//=new int[NUM_TRADEROUTES];
		  public int  luxury_total;
		  public int  tax_total;
		  public int  science_total;
		  public int  pollution;
		  public int  shield_waste;
		  public int  currently_building;
		  public boolean  is_building_unit;
		  public int  turn_last_built;
		  public int  changed_from_id;
		  public boolean  changed_from_is_unit;
		  public int  before_change_shields;
		  public int  disbanded_shields;
		  public int  caravan_shields;
		  public int  last_turns_shield_surplus;
		  public worklist worklist;
		  public char improvements[];//[B_LAST+1];
		  public city_tile_type city_map[];//=new city_tile_type[CITY_MAP_SIZE * CITY_MAP_SIZE];
		  public boolean  did_buy;
		  public boolean  did_sell;
		  public boolean  was_happy;
		  public boolean  airlift;
		  public boolean  diplomat_investigate;
		  public int  city_options;
		  public int  turn_founded;
//		};
		public void lsend_packet_city_info(Speclists<Connection> connections) {
			// TODO Auto-generated method stub
			
		}
		public void send_packet_city_info(Connection pconn) {
			// TODO Auto-generated method stub
			
		}
}
