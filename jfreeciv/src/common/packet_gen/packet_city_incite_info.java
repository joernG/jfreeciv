package common.packet_gen;

import common.Connection;

public class packet_city_incite_info {
	//#define hash_packet_city_incite_info_100 hash_final
	//
	//#define cmp_packet_city_incite_info_100 cmp_final
	//
	//BV_DEFINE(packet_city_incite_info_100_fields, 2);
	//
	//static struct packet_city_incite_info *receive_packet_city_incite_info_100(Connection pc, enum packet_type type)
	//{
	//  packet_city_incite_info_100_fields fields;
	//  struct packet_city_incite_info *old;
	//  struct hash_table **hash = &pc->phs.received[type];
	//  struct packet_city_incite_info *clone;
	//  RECEIVE_PACKET_START(packet_city_incite_info, real_packet);
	//
	//  DIO_BV_GET(&din, fields);
	//
	//
	//  if (!*hash) {
//	    *hash = hash_new(hash_packet_city_incite_info_100, cmp_packet_city_incite_info_100);
	//  }
	//  old = hash_delete_entry(*hash, real_packet);
	//
	//  if (old) {
//	    *real_packet = *old;
	//  } else {
//	    memset(real_packet, 0, sizeof(*real_packet));
	//  }
	//
	//  if (BV_ISSET(fields, 0)) {
//	    {
//	      int readin;
	//    
//	      dio_get_uint16(&din, &readin);
//	      real_packet->city_id = readin;
//	    }
	//  }
	//  if (BV_ISSET(fields, 1)) {
//	    {
//	      int readin;
	//    
//	      dio_get_uint32(&din, &readin);
//	      real_packet->cost = readin;
//	    }
	//  }
	//
	//  clone = fc_malloc(sizeof(*clone));
	//  *clone = *real_packet;
	//  if (old) {
//	    free(old);
	//  }
	//  hash_insert(*hash, clone, clone);
	//
	//  RECEIVE_PACKET_END(real_packet);
	//}
	//
	//static int send_packet_city_incite_info_100(Connection pc, final struct packet_city_incite_info *packet)
	//{
	//  final struct packet_city_incite_info *real_packet = packet;
	//  packet_city_incite_info_100_fields fields;
	//  struct packet_city_incite_info *old, *clone;
	//  boolean differ, old_from_hash, force_send_of_unchanged = true;
	//  struct hash_table **hash = &pc->phs.sent[PACKET_CITY_INCITE_INFO];
	//  int different = 0;
	//  SEND_PACKET_START(PACKET_CITY_INCITE_INFO);
	//
	//  if (!*hash) {
//	    *hash = hash_new(hash_packet_city_incite_info_100, cmp_packet_city_incite_info_100);
	//  }
	//  BV_CLR_ALL(fields);
	//
	//  old = hash_lookup_data(*hash, real_packet);
	//  old_from_hash = (old != null);
	//  if (!old) {
//	    old = fc_malloc(sizeof(*old));
//	    memset(old, 0, sizeof(*old));
//	    force_send_of_unchanged = true;
	//  }
	//
	//  differ = (old->city_id != real_packet->city_id);
	//  if(differ) {different++;}
	//  if(differ) {BV_SET(fields, 0);}
	//
	//  differ = (old->cost != real_packet->cost);
	//  if(differ) {different++;}
	//  if(differ) {BV_SET(fields, 1);}
	//
	//  if (different == 0 && !force_send_of_unchanged) {
//	    return 0;
	//  }
	//
	//  DIO_BV_PUT(&dout, fields);
	//
	//  if (BV_ISSET(fields, 0)) {
//	    dio_put_uint16(&dout, real_packet->city_id);
	//  }
	//  if (BV_ISSET(fields, 1)) {
//	    dio_put_uint32(&dout, real_packet->cost);
	//  }
	//
	//
	//  if (old_from_hash) {
//	    hash_delete_entry(*hash, old);
	//  }
	//
	//  clone = old;
	//
	//  *clone = *real_packet;
	//  hash_insert(*hash, clone, clone);
	//  SEND_PACKET_END;
	//}
	//
	//static void ensure_valid_variant_packet_city_incite_info(Connection pc)
	//{
	//  int variant = -1;
	//
	//  if(pc->phs.variant[PACKET_CITY_INCITE_INFO] != -1) {
//	    return;
	//  }
	//
	//  if(false) {
	//  } else if(true) {
//	    variant = 100;
	//  } else {
//	    util.die("unknown variant");
	//  }
	//  pc->phs.variant[PACKET_CITY_INCITE_INFO] = variant;
	//}
	//
	//struct packet_city_incite_info *receive_packet_city_incite_info(Connection pc, enum packet_type type)
	//{
	//  if(!pc->used) {
//	    util.freelog(Log.LOG_ERROR,
//		    "WARNING: trying to read data from the closed connection %s",
//		    pc.conn_description());
//	    return null;
	//  }
	//  assert(pc->phs.variant != null);
	//  if(is_server) {
//	    util.freelog(Log.LOG_ERROR, "Receiving packet_city_incite_info at the server.");
	//  }
	//  ensure_valid_variant_packet_city_incite_info(pc);
	//
	//  switch(pc->phs.variant[PACKET_CITY_INCITE_INFO]) {
//	    case 100: return receive_packet_city_incite_info_100(pc, type);
//	    default: util.die("unknown variant"); return null;
	//  }
	//}
	//
	//int send_packet_city_incite_info(Connection pc, final struct packet_city_incite_info *packet)
	//{
	//  if(!pc->used) {
//	    util.freelog(Log.LOG_ERROR,
//		    "WARNING: trying to send data to the closed connection %s",
//		    pc.conn_description());
//	    return -1;
	//  }
	//  assert(pc->phs.variant != null);
	//  if(!is_server) {
//	    util.freelog(Log.LOG_ERROR, "Sending packet_city_incite_info from the client.");
	//  }
	//  ensure_valid_variant_packet_city_incite_info(pc);
	//
	//  switch(pc->phs.variant[PACKET_CITY_INCITE_INFO]) {
//	    case 100: return send_packet_city_incite_info_100(pc, packet);
//	    default: util.die("unknown variant"); return -1;
	//  }
	//}
	//
	public static int dsend_packet_city_incite_info(Connection pc, int city_id, int cost)
	{
	//  struct packet_city_incite_info packet, *real_packet = &packet;
	//
	//  real_packet->city_id = city_id;
	//  real_packet->cost = cost;
	//  
	//  return send_packet_city_incite_info(pc, real_packet);
		return 0;
	}
	
}
