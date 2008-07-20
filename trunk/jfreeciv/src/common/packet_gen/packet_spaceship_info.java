package common.packet_gen;

import common.Connection;

import server.spaceace.player_spaceship;
import utility.Speclists;

public class packet_spaceship_info {
	  public int player_num;
	  public int sship_state;
	  public int structurals;
	  public int components;
	  public int modules;
	  public int fuel;
	  public int propulsion;
	  public int habitation;
	  public int life_support;
	  public int solar_panels;
	  public int launch_year;
	  public int population;
	  public int mass;
	  public char structure[] = new char[player_spaceship.NUM_SS_STRUCTURALS+1];
	  public float support_rate;
	  public float energy_rate;
	  public float success_rate;
	  public float travel_time;
	  public void lsend_packet_spaceship_info(Speclists<Connection> dest)
	  {
//	    conn_list_iterate(*dest, pconn) {
//	      send_packet_spaceship_info(pconn, packet);
//	    } conn_list_iterate_end;
	  }
  
}
