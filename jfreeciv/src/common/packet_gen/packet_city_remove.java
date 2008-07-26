package common.packet_gen;

import utility.Speclists;

import common.Connection;

public class packet_city_remove {
	//
	//#define hash_packet_city_remove_100 hash_final
	//
	//#define cmp_packet_city_remove_100 cmp_final
	//
	//BV_DEFINE(packet_city_remove_100_fields, 1);
	//
	//static struct packet_city_remove *receive_packet_city_remove_100(Connection pc, enum packet_type type)
	//{
	//  packet_city_remove_100_fields fields;
	//  struct packet_city_remove *old;
	//  struct hash_table **hash = &pc->phs.received[type];
	//  struct packet_city_remove *clone;
	//  RECEIVE_PACKET_START(packet_city_remove, real_packet);
	//
	//  DIO_BV_GET(&din, fields);
	//
	//
	//  if (!*hash) {
//	    *hash = hash_new(hash_packet_city_remove_100, cmp_packet_city_remove_100);
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
	//static int send_packet_city_remove_100(Connection pc, final struct packet_city_remove *packet)
	//{
	//  final struct packet_city_remove *real_packet = packet;
	//  packet_city_remove_100_fields fields;
	//  struct packet_city_remove *old, *clone;
	//  boolean differ, old_from_hash, force_send_of_unchanged = true;
	//  struct hash_table **hash = &pc->phs.sent[PACKET_CITY_REMOVE];
	//  int different = 0;
	//  SEND_PACKET_START(PACKET_CITY_REMOVE);
	//
	//  if (!*hash) {
//	    *hash = hash_new(hash_packet_city_remove_100, cmp_packet_city_remove_100);
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
	//  if (different == 0 && !force_send_of_unchanged) {
//	    return 0;
	//  }
	//
	//  DIO_BV_PUT(&dout, fields);
	//
	//  if (BV_ISSET(fields, 0)) {
//	    dio_put_uint16(&dout, real_packet->city_id);
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
	//static void ensure_valid_variant_packet_city_remove(Connection pc)
	//{
	//  int variant = -1;
	//
	//  if(pc->phs.variant[PACKET_CITY_REMOVE] != -1) {
//	    return;
	//  }
	//
	//  if(false) {
	//  } else if(true) {
//	    variant = 100;
	//  } else {
//	    util.die("unknown variant");
	//  }
	//  pc->phs.variant[PACKET_CITY_REMOVE] = variant;
	//}
	//
	//struct packet_city_remove *receive_packet_city_remove(Connection pc, enum packet_type type)
	//{
	//  if(!pc->used) {
//	    util.freelog(Log.LOG_ERROR,
//		    "WARNING: trying to read data from the closed connection %s",
//		    pc.conn_description());
//	    return null;
	//  }
	//  assert(pc->phs.variant != null);
	//  if(is_server) {
//	    util.freelog(Log.LOG_ERROR, "Receiving packet_city_remove at the server.");
	//  }
	//  ensure_valid_variant_packet_city_remove(pc);
	//
	//  switch(pc->phs.variant[PACKET_CITY_REMOVE]) {
//	    case 100: return receive_packet_city_remove_100(pc, type);
//	    default: util.die("unknown variant"); return null;
	//  }
	//}
	//
	//int send_packet_city_remove(Connection pc, final struct packet_city_remove *packet)
	//{
	//  if(!pc->used) {
//	    util.freelog(Log.LOG_ERROR,
//		    "WARNING: trying to send data to the closed connection %s",
//		    pc.conn_description());
//	    return -1;
	//  }
	//  assert(pc->phs.variant != null);
	//  if(!is_server) {
//	    util.freelog(Log.LOG_ERROR, "Sending packet_city_remove from the client.");
	//  }
	//  ensure_valid_variant_packet_city_remove(pc);
	//
	//  switch(pc->phs.variant[PACKET_CITY_REMOVE]) {
//	    case 100: return send_packet_city_remove_100(pc, packet);
//	    default: util.die("unknown variant"); return -1;
	//  }
	//}
	//
	//void lsend_packet_city_remove(Speclists<conn> *dest, final struct packet_city_remove *packet)
	//{
	//  conn_list_iterate(*dest, pconn) {
//	    send_packet_city_remove(pconn, packet);
	//  } }
	//}
	//
	//int dsend_packet_city_remove(Connection pc, int city_id)
	//{
	//  struct packet_city_remove packet, *real_packet = &packet;
	//
	//  real_packet->city_id = city_id;
	//  
	//  return send_packet_city_remove(pc, real_packet);
	//}
	//
	public static void dlsend_packet_city_remove(Speclists<Connection> dest, int city_id)
	{
	//  struct packet_city_remove packet, *real_packet = &packet;
	//
	//  real_packet->city_id = city_id;
	//  
	//  lsend_packet_city_remove(dest, real_packet);
	}
	
}
