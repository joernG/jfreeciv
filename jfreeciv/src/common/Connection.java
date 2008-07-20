package common;

import static common.Game.*;
import utility.shared.m_pre_result;

import common.player.player;

public class Connection{
//	#include "fcintl.h"
//	#include "game.h"		/* game.all_connections */
//	#include "hash.h"
//	#include "log.h"
//	#include "mem.h"
//	#include "netintf.h"
//	#include "packets.h"
//	#include "support.h"		/* mystr(n)casecmp */

//	#include "connection.h"
	/***********************************************************
	  The connection struct represents a single client or server
	  at the other end of a network connection.
	 ***********************************************************/
//	struct connection {
	public int id;			/* used for server/client communication */
	public int sock;
	public boolean used;
	public boolean established; /* have negotiated initial packets */
	public player player; /* NULL for connections not yet associated
	 with a specific player */
	/*
	 * connection is "observer", not controller; may be observing
	 * specific player, or all (implementation incomplete).
	 */
	public boolean observer;
	// struct socket_packet_buffer *buffer;
	// struct socket_packet_buffer *send_buffer;
	// time_t last_write;

	// double ping_time;

	// struct Speclists<Connection> self; /* list with this connection as single element */
	
	// char username[MAX_LEN_NAME];
	public String username;
	// char addr[MAX_LEN_ADDR];
	public String addr;

	// /*
	// * "capability" gives the capability string of the executable (be it
	// * a client or server) at the other end of the connection.
	// */
	// char capability[MAX_LEN_CAPSTR];

	// /*
	// * "access_level" stores the access granted to the client
	// * corresponding to this connection.
	// */
	// enum cmdlevel_id access_level;

	// /*
	// * Something has occurred that means the connection should be
	// * closed, but the closing has been postponed.
	// */
	// public boolean delayed_disconnect;

	// void (*notify_of_writable_data) (struct connection * pc,
	// public boolean data_available_and_socket_full);

	// struct {
	// /*
	// * Increases for every packet send to the server.
	// */
	// int last_request_id_used;

	// /*
	// * Increases for every received PACKET_PROCESSING_FINISHED packet.
	// */
	// int last_processed_request_id_seen;

	// /*
	// * Holds the id of the request which caused this packet. Can be
	// * zero.
	// */
	// int request_id_of_currently_handled_packet;
	// } client;

	// struct {
	// /*
	// * Holds the id of the request which is processed now. Can be
	// * zero.
	// */
	// int currently_processed_request_id;

	// /*
	// * Will increase for every received packet.
	// */
	// int last_request_id_seen;

	// /*
	// * The start times of the PACKET_CONN_PING which have been sent
	// * but weren't PACKET_CONN_PONGed yet?
	// */
	// struct timer_list *ping_timers;

	// /* Holds number of tries for authentication from client. */
	// int auth_tries;

	// /* the time that the server will respond after receiving an auth reply.
	// * this is used to throttle the connection. Also used to reject a
	// * connection if we've waited too long for a password. */
	// time_t auth_settime;

	// /* used to follow where the connection is in the authentication process
	// */
	// enum auth_status status;
	// char password[MAX_LEN_PASSWORD];

	// /* for reverse lookup and blacklisting in db */
	// char ipaddr[MAX_LEN_ADDR];
	// } server;

	// /*
	// * Called before an incoming packet is processed. The packet_type
	// * argument should really be a "enum packet_type". However due
	// * circular dependency this is impossible.
	// */
	// void (*incoming_packet_notify) (struct connection * pc,
	// int packet_type, int size);

	// /*
	// * Called before a packet is sent. The packet_type argument should
	// * really be a "enum packet_type". However due circular dependency
	// * this is impossible.
	// */
	// void (*outgoing_packet_notify) (struct connection * pc,
	// int packet_type, int size,
	// int request_id);
	// struct {
	// struct hash_table **sent;
	// struct hash_table **received;
	// int *variant;
	// } phs;

	// #ifdef USE_COMPRESSION
	// struct {
	// int frozen_level;

	// struct byte_vector queue;
	//	  } compression;
	//	  #endif
	//	  struct {
	//	  int bytes_send;
	//	  } statistics;
//	};



//	/* String used for connection.addr and related cases to indicate
//	* blank/unknown/not-applicable address:
//	*/
//	final char blank_addr_str[] = "---.---.---.---";

//	/* This is only used by the server.
//	If it is set the disconnection of conns is posponed. This is sometimes
//	neccesary as removing a random connection while we are iterating through
//	a connection list might corrupt the list. */
//	int delayed_disconnect = 0;

//	connection current_connection;

//	/**************************************************************************
//	Command access levels for client-side use; at present, they are only
//	used to control access to server commands typed at the client chatline.
//	**************************************************************************/
//	static final String levelnames[] = {
//	"none",
//	"info",
//	"ctrl",
//	"hack"
//	};

//	/**************************************************************************
//	Get name associated with given level.  These names are used verbatim in
//	commands, so should not be translated here.
//	**************************************************************************/
//	final String cmdlevel_name(enum cmdlevel_id lvl)
//	{
//	assert (lvl >= 0 && lvl < ALLOW_NUM);
//	return levelnames[lvl];
//	}

//	/**************************************************************************
//	Lookup level assocated with token, or ALLOW_UNRECOGNISED if no match.
//	Only as many characters as in token need match, so token may be
//	abbreviated -- current level names are unique at first character.
//	Empty token will match first, i.e. level 'none'.
//	**************************************************************************/
//	enum cmdlevel_id cmdlevel_named(final String token)
//	{
//	enum cmdlevel_id i;
//	size_t len = token.length();

//	for (i = 0; i < ALLOW_NUM; i++) {
//	if (strncmp(levelnames[i], token, len) == 0) {
//	return i;
//	}
//	}

//	return ALLOW_UNRECOGNIZED;
//	}


//	/**************************************************************************
//	This callback is used when an error occurs trying to write to the
//	connection.  The effect of the callback should be to close the
//	connection.  This is here so that the server and client can take
//	appropriate (different) actions: server lost a client, client lost
//	connection to server.
//	**************************************************************************/
//	static CLOSE_FUN close_callback;

//	/**************************************************************************
//	Register the close_callback:
//	**************************************************************************/
//	void close_socket_set_callback(CLOSE_FUN fun)
//	{
//	close_callback = fun;
//	}

//	/**************************************************************************
//	Return the the close_callback.
//	**************************************************************************/
//	CLOSE_FUN close_socket_get_callback()
//	{
//	return close_callback;
//	}

//	/**************************************************************************
//	...
//	**************************************************************************/
//	static boolean buffer_ensure_free_extra_space(socket_packet_buffer buf,
//	int extra_space)
//	{
//	/* room for more? */
//	if (buf.nsize - buf.ndata < extra_space) {
//	buf.nsize = buf.ndata + extra_space;

//	/* added this check so we don't gobble up too much mem */
//	if (buf.nsize > MAX_LEN_BUFFER) {
//	return false;
//	}
//	buf.data = (unsigned char *) fc_realloc(buf.data, buf.nsize);
//	}
//	return true;
//	}

//	/**************************************************************************
//	Read data from socket, and check if a packet is ready.
//	Returns:
//	-1  :  an error occurred - you should close the socket
//	>0  :  number of bytes read
//	=0  :  non-blocking sockets only; no data read, would block
//	**************************************************************************/
//	int read_socket_data(int sock, socket_packet_buffer buffer)
//	{
//	int didget;

//	if (!buffer_ensure_free_extra_space(buffer, MAX_LEN_PACKET)) {
//	util.freelog(Log.LOG_ERROR, "can't grow buffer");
//	return -1;
//	}

//	util.freelog(LOG_DEBUG, "try reading %d bytes", buffer.nsize - buffer.ndata);
//	didget = my_readsocket(sock, (char *) (buffer.data + buffer.ndata),
//	buffer.nsize - buffer.ndata);

//	if (didget > 0) {
//	buffer.ndata+=didget;
//	util.freelog(LOG_DEBUG, "didget:%d", didget);
//	return didget;
//	}
//	else if (didget == 0) {
//	util.freelog(LOG_DEBUG, "EOF on socket read");
//	return -1;
//	}
//	#ifdef NONBLOCKING_SOCKETS
//	else if (errno == EWOULDBLOCK || errno == EAGAIN) {
//	util.freelog(LOG_DEBUG, "EGAIN on socket read");
//	return 0;
//	}
//	#endif
//	return -1;
//	}

//	/**************************************************************************
//	write wrapper function -vasc
//	**************************************************************************/
//	static int write_socket_data(connection pc,
//	socket_packet_buffer buf, int limit)
//	{
//	int start, nput, nblock;

//	if (pc.delayed_disconnect) {
//	if (delayed_disconnect > 0) {
//	return 0;
//	} else {
//	if (close_callback) {
//	(*close_callback)(pc);
//	}
//	return -1;
//	}
//	}

//	for (start=0; buf.ndata-start>limit;) {
//	fd_set writefs, exceptfs;
//	struct timeval tv;

//	MY_FD_ZERO(&writefs);
//	MY_FD_ZERO(&exceptfs);
//	FD_SET(pc.sock, &writefs);
//	FD_SET(pc.sock, &exceptfs);

//	tv.tv_sec = 0; tv.tv_usec = 0;

//	if (select(pc.sock+1, null, &writefs, &exceptfs, &tv) <= 0) {
//	if (errno != EINTR) {
//	break;
//	} else {
//	/* EINTR can happen sometimes, especially when compiling with -pg.
//	* Generally we just want to run select again. */
//	continue;
//	}
//	}

//	if (FD_ISSET(pc.sock, &exceptfs)) {
//	if (delayed_disconnect > 0) {
//	pc.delayed_disconnect = true;
//	return 0;
//	} else {
//	if (close_callback) {
//	(*close_callback)(pc);
//	}
//	return -1;
//	}
//	}

//	if (FD_ISSET(pc.sock, &writefs)) {
//	nblock=Math.min(buf.ndata-start, MAX_LEN_PACKET);
//	util.freelog(LOG_DEBUG,"trying to write %d limit=%d",nblock,limit);
//	if((nput=my_writesocket(pc.sock, 
//	(final String)buf.data+start, nblock)) == -1) {
//	#ifdef NONBLOCKING_SOCKETS
//	if (errno == EWOULDBLOCK || errno == EAGAIN) {
//	break;
//	}
//	#endif
//	if (delayed_disconnect > 0) {
//	pc.delayed_disconnect = true;
//	return 0;
//	} else {
//	if (close_callback) {
//	(*close_callback)(pc);
//	}
//	return -1;
//	}
//	}
//	start += nput;
//	}
//	}

//	if (start > 0) {
//	buf.ndata -= start;
//	memmove(buf.data, buf.data+start, buf.ndata);
//	() time(&pc.last_write);
//	}
//	return 0;
//	}


//	/**************************************************************************
//	flush'em
//	**************************************************************************/
//	void flush_connection_send_buffer_all(connection pc)
//	{
//	if(pc && pc.used && pc.send_buffer.ndata > 0) {
//	write_socket_data(pc, pc.send_buffer, 0);
//	if (pc.notify_of_writable_data) {
//	pc.notify_of_writable_data(pc, pc.send_buffer
//	&& pc.send_buffer.ndata > 0);
//	}
//	}
//	}

//	/**************************************************************************
//	flush'em
//	**************************************************************************/
//	static void flush_connection_send_buffer_packets(connection pc)
//	{
//	if(pc && pc.used && pc.send_buffer.ndata >= MAX_LEN_PACKET) {
//	write_socket_data(pc, pc.send_buffer, MAX_LEN_PACKET-1);
//	if (pc.notify_of_writable_data) {
//	pc.notify_of_writable_data(pc, pc.send_buffer
//	&& pc.send_buffer.ndata > 0);
//	}
//	}
//	}

//	/**************************************************************************
//	...
//	**************************************************************************/
//	static boolean add_connection_data(connection pc,
//	final unsigned char *data, int len)
//	{
//	if (pc && pc.delayed_disconnect) {
//	if (delayed_disconnect > 0) {
//	return true;
//	} else {
//	if (close_callback) {
//	(*close_callback)(pc);
//	}
//	return false;
//	}
//	}

//	if (pc && pc.used) {
//	socket_packet_buffer buf;

//	buf = pc.send_buffer;

//	util.freelog(LOG_DEBUG, "add %d bytes to %d (space=%d)", len, buf.ndata,
//	buf.nsize);
//	if (!buffer_ensure_free_extra_space(buf, len)) {
//	if (delayed_disconnect > 0) {
//	pc.delayed_disconnect = true;
//	return true;
//	} else {
//	if (close_callback) {
//	(*close_callback) (pc);
//	}
//	return false;
//	}
//	}
//	memcpy(buf.data + buf.ndata, data, len);
//	buf.ndata += len;
//	return true;
//	}
//	return true;
//	}

//	/**************************************************************************
//	write data to socket
//	**************************************************************************/
//	void send_connection_data(connection pc, final unsigned char *data,
//	int len)
//	{
//	if (pc && pc.used) {
//	pc.statistics.bytes_send += len;
//	if(pc.send_buffer.do_buffer_sends > 0) {
//	flush_connection_send_buffer_packets(pc);
//	if (!add_connection_data(pc, data, len)) {
//	util.freelog(Log.LOG_ERROR, "cut connection %s due to huge send buffer (1)",
//	conn_description(pc));
//	}
//	flush_connection_send_buffer_packets(pc);
//	}
//	else {
//	flush_connection_send_buffer_all(pc);
//	if (!add_connection_data(pc, data, len)) {
//	util.freelog(Log.LOG_ERROR, "cut connection %s due to huge send buffer (2)",
//	conn_description(pc));
//	}
//	flush_connection_send_buffer_all(pc);
//	}
//	}
//	}

//	/**************************************************************************
//	Turn on buffering, using a counter so that calls may be nested.
//	**************************************************************************/
//	void connection_do_buffer(connection pc)
//	{
//	if (pc && pc.used) {
//	pc.send_buffer.do_buffer_sends++;
//	}
//	}

//	/**************************************************************************
//	Turn off buffering if internal counter of number of times buffering
//	was turned on falls to zero, to handle nested buffer/unbuffer pairs.
//	When counter is zero, flush any pending data.
//	**************************************************************************/
//	void connection_do_unbuffer(connection pc)
//	{
//	if (pc && pc.used) {
//	pc.send_buffer.do_buffer_sends--;
//	if (pc.send_buffer.do_buffer_sends < 0) {
//	util.freelog(Log.LOG_ERROR, "Too many calls to unbuffer %s!", pc.username);
//	pc.send_buffer.do_buffer_sends = 0;
//	}
//	if(pc.send_buffer.do_buffer_sends == 0)
//	flush_connection_send_buffer_all(pc);
//	}
//	}

//	/**************************************************************************
//	Convenience functions to buffer/unbuffer a list of connections:
//	**************************************************************************/
//	void conn_list_do_buffer(Speclists<Connection> dest)
//	{
//	conn_list_iterate(*dest, pconn)
//	connection_do_buffer(pconn);
//	}
//	}
//	void conn_list_do_unbuffer(Speclists<Connection> dest)
//	{
//	conn_list_iterate(*dest, pconn)
//	connection_do_unbuffer(pconn);
//	}
//	}

//	/***************************************************************
//	Find connection by exact user name, from game.all_connections,
//	case-insensitve.  Returns null if not found.
//	***************************************************************/
//	connection find_conn_by_user(final String user_name)
//	{
//	for (conn pconn : game.all_connections.data) {
//	if (mystrcasecmp(user_name, pconn.username)==0) {
//	return pconn;
//	}
//	} }
//	return null;
//	}

//	/***************************************************************
//	Like find_conn_by_username(), but allow unambigous prefix
//	(ie abbreviation).
//	Returns null if could not match, or if ambiguous or other
//	problem, and fills *result with characterisation of
//	match/non-match (see shared.[ch])
//	***************************************************************/
//	static final String connection_accessor(int i) {
//	return conn_list_get(&game.all_connections, i).username;
//	}

	public static Connection find_conn_by_user_prefix(final String user_name,
			m_pre_result result)
	{
		int ind  = 0;

//		result = match_prefix(connection_accessor,
//				game.all_connections.foo_list_size(),
//				MAX_LEN_NAME-1, mystrncasecmp, user_name, &ind);

		if (result.ordinal() < m_pre_result.M_PRE_AMBIGUOUS.ordinal()) {
			return game.all_connections.foo_list_get(ind);
		} else {
			return null;
		}
	}

//	/***************************************************************
//	Find connection by id, from game.all_connections.
//	Returns null if not found.
//	Number of connections will always be relatively small given
//	current implementation, so linear search should be fine.
//	***************************************************************/
//	connection find_conn_by_id(int id)
//	{
//	for (conn pconn : game.all_connections.data) {
//	if (pconn.id == id) {
//	return pconn;
//	}
//	}
//	}
//	return null;
//	}

//	/**************************************************************************
//	Return malloced struct, appropriately initialized.
//	**************************************************************************/
//	socket_packet_buffer new_socket_packet_buffer()
//	{
//	socket_packet_buffer buf;

//	buf = (socket_packet_buffer )fc_malloc(sizeof(*buf));
//	buf.ndata = 0;
//	buf.do_buffer_sends = 0;
//	buf.nsize = 10*MAX_LEN_PACKET;
//	buf.data = (unsigned char *)fc_malloc(buf.nsize);
//	return buf;
//	}

//	/**************************************************************************
//	Free malloced struct
//	**************************************************************************/
//	static void free_socket_packet_buffer(socket_packet_buffer buf)
//	{
//	if (buf) {
//	if (buf.data) {
//	free(buf.data);
//	}
//	free(buf);
//	}
//	}

//	/**************************************************************************
//	Return pointer to static string containing a description for this
//	connection, based on pconn.name, pconn.addr, and (if applicable)
//	pconn.player.name.  (Also pconn.established and pconn.observer.)

//	Note that if pconn is client's aconnection (connection to server),
//	pconn.name and pconn.addr contain empty string, and pconn.player
//	is null: in this case return string "server".
//	**************************************************************************/
//	final String conn_description(final connection pconn)
//	{
//	static char buffer[MAX_LEN_NAME*2 + MAX_LEN_ADDR + 128];

//	buffer[0] = '\0';

//	if (*pconn.username != '\0') {
//	buffer = util.my_snprintf( "%s from %s",
//	pconn.username, pconn.addr); 
//	} else {
//	sz_strlcpy(buffer, "server");
//	}
//	if (!pconn.established) {
//	sz_strlcat(buffer, " (connection incomplete)");
//	return buffer;
//	}
//	if (pconn.player) {
//	cat_snprintf(buffer, sizeof(buffer), " (player %s)",
//	pconn.player.name);
//	}
//	if (pconn.observer) {
//	sz_strlcat(buffer, " (observer)");
//	}
//	return buffer;
//	}

//	/**************************************************************************
//	Get next request id. Takes wrapping of the 16 bit wide unsigned int
//	into account.
//	**************************************************************************/
//	int get_next_request_id(int old_request_id)
//	{
//	int result = old_request_id + 1;

//	if ((result & 0xffff) == 0) {
//	util.freelog(Log.LOG_NORMAL,
//	"INFORMATION: request_id has wrapped around; "
//	"setting from %d to 2", result);
//	result = 2;
//	}
//	assert(result);
//	return result;
//	}

//	/**************************************************************************
//	...
//	**************************************************************************/
//	void free_compression_queue(connection pc)
//	{
//	#ifdef USE_COMPRESSION
//	byte_vector_free(&pc.compression.queue);
//	#endif
//	}

//	/**************************************************************************
//	...
//	**************************************************************************/
//	static void init_packet_hashs(connection pc)
//	{
//	enum packet_type i;

//	pc.phs.sent = fc_malloc(sizeof(*pc.phs.sent) * PACKET_LAST);
//	pc.phs.received = fc_malloc(sizeof(*pc.phs.received) * PACKET_LAST);
//	pc.phs.variant = fc_malloc(sizeof(*pc.phs.variant) * PACKET_LAST);

//	for (i = 0; i < PACKET_LAST; i++) {
//	pc.phs.sent[i] = null;
//	pc.phs.received[i] = null;
//	pc.phs.variant[i] = -1;
//	}
//	}

//	/**************************************************************************
//	...
//	**************************************************************************/
//	static void free_packet_hashes(connection pc)
//	{
//	int i;

//	conn_clear_packet_cache(pc);

//	if (pc.phs.sent) {
//	for (i = 0; i < PACKET_LAST; i++) {
//	if (pc.phs.sent[i] != null) {
//	hash_free(pc.phs.sent[i]);
//	pc.phs.sent[i] = null;
//	}
//	}
//	free(pc.phs.sent);
//	pc.phs.sent = null;
//	}

//	if (pc.phs.received) {
//	for (i = 0; i < PACKET_LAST; i++) {
//	if (pc.phs.received[i] != null) {
//	hash_free(pc.phs.received[i]);
//	pc.phs.received[i] = null;
//	}
//	}
//	free(pc.phs.received);
//	pc.phs.received = null;
//	}

//	if (pc.phs.variant) {
//	free(pc.phs.variant);
//	pc.phs.variant = null;
//	}
//	}

//	/**************************************************************************
//	...
//	**************************************************************************/
//	void connection_common_init(connection pconn)
//	{
//	pconn.established = false;
//	pconn.used = true;
//	pconn.last_write = 0;
//	pconn.buffer = new_socket_packet_buffer();
//	pconn.send_buffer = new_socket_packet_buffer();
//	pconn.statistics.bytes_send = 0;

//	init_packet_hashs(pconn);

//	#ifdef USE_COMPRESSION
//	byte_vector_init(&pconn.compression.queue);
//	#endif
//	}

//	/**************************************************************************
//	...
//	**************************************************************************/
//	void connection_common_close(connection pconn)
//	{
//	if (!pconn.used) {
//	util.freelog(Log.LOG_ERROR, "WARNING: Trying to close already closed connection");
//	} else {
//	my_closesocket(pconn.sock);
//	pconn.used = false;
//	pconn.established = false;

//	free_socket_packet_buffer(pconn.buffer);
//	pconn.buffer = null;

//	free_socket_packet_buffer(pconn.send_buffer);
//	pconn.send_buffer = null;

//	free_compression_queue(pconn);
//	free_packet_hashes(pconn);
//	}
//	}

//	/**************************************************************************
//	Remove all cached packets from the connection. This resets the
//	delta-state.
//	**************************************************************************/
//	void conn_clear_packet_cache(connection pc)
//	{
//	int i;

//	for (i = 0; i < PACKET_LAST; i++) {
//	if (pc.phs.sent != null && pc.phs.sent[i] != null) {
//	hash_table hash = pc.phs.sent[i];
//	while (hash_num_entries(hash) > 0) {
//	final void *key = hash_key_by_number(hash, 0);
//	hash_delete_entry(hash, key);
//	free((void *) key);
//	}
//	}
//	if (pc.phs.received != null && pc.phs.received[i] != null) {
//	hash_table hash = pc.phs.received[i];
//	while (hash_num_entries(hash) > 0) {
//	final void *key = hash_key_by_number(hash, 0);
//	hash_delete_entry(hash, key);
//	free((void *) key);
//	}
//	}
//	}
//	}
}