package common.packet_gen;

import port.util;
import utility.Log;
import common.Connection;

public class packet_authentication_req {
	private static final int PACKET_AUTHENTICATION_REQ = 0;
	//TODO
	  public authentication_type type;
//	  char message[MAX_LEN_MSG];
	  public String message;
	  public static int dsend_packet_authentication_req(Connection pc, authentication_type type, String message)
	  {
//	    packet_authentication_req packet, real_packet = packet;
	//
		  packet_authentication_req real_packet = new packet_authentication_req();
	    real_packet.type = type;
//	    sz_strlcpy(real_packet->message, message);
	//    
	    return real_packet.send_packet_authentication_req(pc);
	  }
	  public int send_packet_authentication_req(Connection pc) {
		if(!pc.used) {
			util.freelog(Log.LOG_ERROR,
					"WARNING: trying to send data to the closed connection %s",
					pc.conn_description());
			return -1;
		}
		assert(pc.phs.variant != null);
//		if(!is_server) {
//			util.freelog(Log.LOG_ERROR, "Sending packet_authentication_req from the client.");
//		}
		ensure_valid_variant_packet_authentication_req(pc);

		switch(pc.phs.variant[PACKET_AUTHENTICATION_REQ]) {
		case 100: return send_packet_authentication_req_100(pc);
		default: util.die("unknown variant"); return -1;
		}
	}

	void ensure_valid_variant_packet_authentication_req(Connection pc)
	{
		int variant = -1;

		if(pc.phs.variant[PACKET_AUTHENTICATION_REQ] != -1) {
			return;
		}

		if(false) {
		} else if(true) {
			variant = 100;
		} else {
			util.die("unknown variant");
		}
		pc.phs.variant[PACKET_AUTHENTICATION_REQ] = variant;
	}
	
	int send_packet_authentication_req_100(Connection pc)
	{
	//  final struct packet_authentication_req *real_packet = packet;
	//  packet_authentication_req_100_fields fields;
	//  struct packet_authentication_req *old, *clone;
	//  boolean differ, old_from_hash, force_send_of_unchanged = true;
	//  struct hash_table **hash = &pc->phs.sent[PACKET_AUTHENTICATION_REQ];
	//  int different = 0;
	//  SEND_PACKET_START(PACKET_AUTHENTICATION_REQ);
	//
	//  if (!*hash) {
//	    *hash = hash_new(hash_packet_authentication_req_100, cmp_packet_authentication_req_100);
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
	//  differ = (old->type != real_packet->type);
	//  if(differ) {different++;}
	//  if(differ) {BV_SET(fields, 0);}
	//
	//  differ = (strcmp(old->message, real_packet->message) != 0);
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
//	    dio_put_uint8(&dout, real_packet->type);
	//  }
	//  if (BV_ISSET(fields, 1)) {
//	    dio_put_string(&dout, real_packet->message);
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
		return 0;
	}
	
}
