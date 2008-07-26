package common.packet_gen;

import common.Connection;

import utility.Speclists;

public class packet_city_short_info {
//	struct packet_city_short_info {
		  public int  id;
		  public int  owner;
		  public int  x;
		  public int  y;
//		  char name[MAX_LEN_NAME];
		  public String name;
		  public int  size;
		  public boolean  happy;
		  public boolean  unhappy;
		  public boolean  capital;
		  public boolean  walls;
		  public boolean  occupied;
		  public int  tile_trade;
//		};
		//struct packet_city_short_info *receive_packet_city_short_info(Connection pc, enum packet_type type)
		//{
		//  if(!pc->used) {
//		    util.freelog(Log.LOG_ERROR,
//			    "WARNING: trying to read data from the closed connection %s",
//			    pc.conn_description());
//		    return null;
		//  }
		//  assert(pc->phs.variant != null);
		//  if(is_server) {
//		    util.freelog(Log.LOG_ERROR, "Receiving packet_city_short_info at the server.");
		//  }
		//  ensure_valid_variant_packet_city_short_info(pc);
		//
		//  switch(pc->phs.variant[PACKET_CITY_SHORT_INFO]) {
//		    case 100: return receive_packet_city_short_info_100(pc, type);
//		    default: util.die("unknown variant"); return null;
		//  }
		//}
		//
		//int send_packet_city_short_info(Connection pc, final struct packet_city_short_info *packet)
		//{
		//  if(!pc->used) {
//		    util.freelog(Log.LOG_ERROR,
//			    "WARNING: trying to send data to the closed connection %s",
//			    pc.conn_description());
//		    return -1;
		//  }
		//  assert(pc->phs.variant != null);
		//  if(!is_server) {
//		    util.freelog(Log.LOG_ERROR, "Sending packet_city_short_info from the client.");
		//  }
		//  ensure_valid_variant_packet_city_short_info(pc);
		//
		//  switch(pc->phs.variant[PACKET_CITY_SHORT_INFO]) {
//		    case 100: return send_packet_city_short_info_100(pc, packet);
//		    default: util.die("unknown variant"); return -1;
		//  }
		//}
		//
		public void lsend_packet_city_short_info(Speclists<Connection> dest)
		{
		//  conn_list_iterate(*dest, pconn) {
//		    send_packet_city_short_info(pconn, packet);
		//  } 
		}
		//
}
