package server;

import java.util.Date;

import port.util;
import utility.Log;
import utility.Md5;

import common.Connection;
import common.connection.auth_status;
import common.packet_gen.authentication_type;
import common.packet_gen.packet_authentication_req;

public class Auth {
	/* where our mysql database is located and how to get to it */
	public static final String HOST = "localhost";
	public static final String USER = "anonymous";
	public static final String PASSWORD = "";

	/* the database where our table is located */
	public static final String DATABASE = "test";

	/*
	 * the tables where we will do our lookups and inserts. the tables can be
	 * created with the following:
	 * 
	 * CREATE TABLE auth ( id int(11) NOT null auto_increment, name varchar(32)
	 * default null, password varchar(32) default null, email varchar(128)
	 * default null, createtime int(11) default null, accesstime int(11) default
	 * null, address varchar(255) default null, createaddress varchar(15)
	 * default null, logincount int(11) default '0', PRIMARY KEY (id), UNIQUE
	 * KEY name (name) ) TYPE=MyISAM;
	 * 
	 * CREATE TABLE loginlog ( id int(11) NOT null auto_increment, name
	 * varchar(32) default null, logintime int(11) default null, address
	 * varchar(255) default null, succeed enum('S','F') default 'S', PRIMARY KEY
	 * (id) ) TYPE=MyISAM;
	 * 
	 * N.B. if the tables are not of this format, then the select,insert, and
	 * update syntax in the auth_db_* functions below must be changed.
	 */
	public static final String AUTH_TABLE = "auth";
	public static final String LOGIN_TABLE = "loginlog";

	public static final String GUEST_NAME = "guest";

	public static final int MIN_PASSWORD_LEN = 6; /*
													 * minimum length of
													 * password
													 */
	public static final int MIN_PASSWORD_CAPS = 0; /*
													 * minimum number of capital
													 * letters required
													 */
	public static final int MIN_PASSWORD_NUMS = 0; /*
													 * minimum number of numbers
													 * required
													 */

	public static final int MAX_AUTH_TRIES = 3;
	public static final int MAX_WAIT_TIME = 300; /*
													 * max time we'll wait on a
													 * password
													 */

	/*
	 * after each wrong guess for a password, the server waits this many seconds
	 * to reply to the client
	 */
	static final int auth_fail_wait[] = { 1, 1, 2, 3 };

	/***************************************************************************
	 * The auth db statuses are:
	 * 
	 * 1: an error occurred, possibly we couldn't access the database file. 2:
	 * we were able to successfully insert an entry. or we found the entry we
	 * were searching for 3: the user we were searching for was not found.
	 **************************************************************************/
	enum authdb_status {
		AUTH_DB_ERROR, AUTH_DB_SUCCESS, AUTH_DB_NOT_FOUND
	};

	/***************************************************************************
	 * handle authentication of a user; called by handle_login_request() if
	 * authentication is enabled.
	 * 
	 * if the connection is rejected right away, return false, otherwise return
	 * true
	 **************************************************************************/
	boolean authenticate_user(Connection pconn, String username) {
		// String tmpname = "\0";
		String tmpname = "";

		/* assign the client a unique guest name/reject if guests aren't allowed */
		if (is_guest_name(username)) {
			if (Srv_main.srvarg.auth_allow_guests) {

				tmpname = username;
				get_unique_guest_name(username);

				if (tmpname.equals(username)) {
					Plrhand.notify_conn(pconn.self,
							("Warning: the guest name '%s' has been "
									+ "taken, renaming to user '%s'."),
							tmpname, username);
				}
				pconn.username = username;
				Connecthand.establish_new_connection(pconn);
			} else {
				Connecthand.reject_new_connection(
						("Guests are not allowed on this server. " + "Sorry."),
						pconn);
				util.freelog(Log.LOG_NORMAL,
						"%s was rejected: Guests not allowed.", username);
				return false;
			}
		} else {
			/*
			 * we are not a guest, we need an extra check as to whether a
			 * connection can be established: the client must authenticate
			 * itself
			 */
			// char buffer[MAX_LEN_MSG];
			String buffer = "";

			pconn.username = username;

			switch (auth_db_load(pconn)) {
			case AUTH_DB_ERROR:
				if (Srv_main.srvarg.auth_allow_guests) {
					tmpname = pconn.username;
					get_unique_guest_name(tmpname); /*
													 * don't pass pconn.username
													 * here
													 */
					pconn.username = tmpname;

					util.freelog(Log.LOG_ERROR,
							"Error reading database; connection . guest");
					Plrhand
							.notify_conn(
									pconn.self,
									("There was an error reading the user "
											+ "database, logging in as guest connection '%s'."),
									pconn.username);
					Connecthand.establish_new_connection(pconn);
				} else {
					Connecthand
							.reject_new_connection(
									("There was an error reading the user database "
											+ "and guest logins are not allowed. Sorry"),
									pconn);
					util
							.freelog(
									Log.LOG_NORMAL,
									"%s was rejected: Database error and guests not allowed.",
									pconn.username);
					return false;
				}
				break;
			case AUTH_DB_SUCCESS:
				/* we found a user */
				buffer = util.my_snprintf("Enter password for %s:",
						pconn.username);
				packet_authentication_req.dsend_packet_authentication_req(pconn, authentication_type.AUTH_LOGIN_FIRST, buffer);
				pconn.server.auth_settime = new Date();// time(null);
				pconn.server.status = auth_status.AS_REQUESTING_OLD_PASS;
				break;
			case AUTH_DB_NOT_FOUND:
				/* we couldn't find the user, he is new */
				if (Srv_main.srvarg.auth_allow_newusers) {
					buffer = "Enter a new password (and remember it).";
					packet_authentication_req.dsend_packet_authentication_req(pconn, authentication_type.AUTH_NEWUSER_FIRST,
							buffer);
					pconn.server.auth_settime = new Date(); // time(null);
					pconn.server.status = auth_status.AS_REQUESTING_NEW_PASS;
				} else {
					Connecthand.reject_new_connection(
							("This server allows only preregistered "
									+ "users. Sorry."), pconn);
					util.freelog(Log.LOG_NORMAL,
							"%s was rejected: Only preregister users allowed.",
							pconn.username);

					return false;
				}
				break;
			default:
				assert (0 != 1);
				break;
			}
			return true;
		}

		return true;
	}

	/***************************************************************************
	 * Receives a password from a client and verifies it.
	 **************************************************************************/
	boolean handle_authentication_reply(Connection pconn, String password)
	{
// char msg[MAX_LEN_MSG];
		String msg = "";

		if (pconn.server.status == auth_status.AS_REQUESTING_NEW_PASS) {

			/* check if the new password is acceptable */
			if (!is_good_password(password, msg)) {
				if (pconn.server.auth_tries++ >= MAX_AUTH_TRIES) {
					Connecthand.reject_new_connection("Sorry, too many wrong tries...", pconn);
					util.freelog(Log.LOG_NORMAL, ("%s was rejected: Too many wrong password " +
					"verifies for new user."), pconn.username);

					return false;
				} else {
					packet_authentication_req.dsend_packet_authentication_req(pconn, authentication_type.AUTH_NEWUSER_RETRY, msg);
					return true;
				}
			}

			/*
			 * the new password is good, create a database entry for this user;
			 * we establish the connection in handle_db_lookup
			 */
			pconn.server.password = password;

			if (!auth_db_save(pconn)) {
				Plrhand.notify_conn(pconn.self, 
						("Warning: There was an error in saving to the database. " +
						"Continuing, but your stats will not be saved."));
				util.freelog(Log.LOG_ERROR, "Error writing to database for: %s", pconn.username);
			}

			Connecthand.establish_new_connection(pconn);
		} else if (pconn.server.status == auth_status.AS_REQUESTING_OLD_PASS) { 
			if (authdb_check_password(pconn, password, password.length())) {
				Connecthand.establish_new_connection(pconn);
			} else {
				pconn.server.status = auth_status.AS_FAILED;
				pconn.server.auth_tries++;
				pconn.server.auth_settime = new Date(auth_fail_wait[pconn.server.auth_tries]);
			}
		} else {
			util.freelog(Log.LOG_VERBOSE, "%s is sending unrequested auth packets", 
					pconn.username);
			return false;
		}

		return true;
	}

	/***************************************************************************
	 * checks on where in the authentication process we are.
	 **************************************************************************/
	void process_authentication_status(Connection pconn)
	{
		switch(pconn.server.status) {
		case AS_NOT_ESTABLISHED:
			/* nothing, we're not ready to do anything here yet. */
			break;
		case AS_FAILED:
			/*
			 * the Connection gave the wrong password, we kick 'em off or we're
			 * throttling the Connection to avoid password guessing
			 */
			if (pconn.server.auth_settime!=null
					&& new Date().getTime() >= pconn.server.auth_settime.getTime()) {

				if (pconn.server.auth_tries >= MAX_AUTH_TRIES) {
					pconn.server.status = auth_status.AS_NOT_ESTABLISHED;
					Connecthand.reject_new_connection("Sorry, too many wrong tries...", pconn);
					util.freelog(Log.LOG_NORMAL,
							"%s was rejected: Too many wrong password tries.",
							pconn.username);
					pconn.close_connection();
				} else {
					packet_authentication_req request = new packet_authentication_req();

					pconn.server.status = auth_status.AS_REQUESTING_OLD_PASS;
					request.type = authentication_type.AUTH_LOGIN_RETRY;
					request.message = String.format(
					"Your password is incorrect. Try again.");
					request.send_packet_authentication_req(pconn);
				}
			}
			break;
		case AS_REQUESTING_OLD_PASS:
		case AS_REQUESTING_NEW_PASS:
			/*
			 * waiting on the client to send us a password... don't wait too
			 * long
			 */
			if (new Date().getTime() >= pconn.server.auth_settime.getTime() + MAX_WAIT_TIME) {
				pconn.server.status = auth_status.AS_NOT_ESTABLISHED;
				Connecthand.reject_new_connection("Sorry, your connection timed out...", pconn);
				util.freelog(Log.LOG_NORMAL,
						"%s was rejected: Connection timeout waiting for password.",
						pconn.username);

				pconn.close_connection();
			}
			break;
		case AS_ESTABLISHED:
			/* this better fail bigtime */
			assert(pconn.server.status != auth_status.AS_ESTABLISHED);
			break;
		}
	}

	/***************************************************************************
	 * see if the name qualifies as a guest login name
	 **************************************************************************/
	boolean is_guest_name(final String name) {
//		return (mystrncasecmp(name, GUEST_NAME, GUEST_NAME.length()) == 0);
		return name.equals(GUEST_NAME);
	}

	/***************************************************************************
	 * return a unique guest name WARNING: do not pass pconn.username to this
	 * function: it won't return!
	 **************************************************************************/
	void get_unique_guest_name(String name) {
		int i;

		/* first see if the given name is suitable */
		if (is_guest_name(name) && null==Connection.find_conn_by_user(name)) {
			return;
		}

		/* next try bare guest name */
		name = GUEST_NAME;
		if (null==Connection.find_conn_by_user(name)) {
			return;
		}

		/* bare name is taken, append numbers */
		for (i = 1;; i++) {
			name = String.format( "%s%u", GUEST_NAME, i);

			/* attempt to find this name; if we can't we're good to go */
			if (null==Connection.find_conn_by_user(name)) {
				break;
			}
		}
	}

	/***************************************************************************
	 * Verifies that a password is valid. Does some [very] rudimentary safety
	 * checks. TODO: do we want to frown on non-printing characters? Fill the
	 * msg (length MAX_LEN_MSG) with any worthwhile information that the client
	 * ought to know.
	 **************************************************************************/
	static boolean is_good_password(final String password, String msg) {
		int i, num_caps = 0, num_nums = 0;

		/* check password length */
		if (password.length() < MIN_PASSWORD_LEN) {
			msg = String.format(
					("Your password is too short, the minimum length is %d. "
							+ "Try again."), MIN_PASSWORD_LEN);
			return false;
		}

		msg = String.format(
				("The password must have at least %d capital letters, %d "
						+ "numbers, and be at minimum %d [printable] characters long. "
						+ "Try again."), MIN_PASSWORD_CAPS, MIN_PASSWORD_NUMS,
				MIN_PASSWORD_LEN);
		for (i = 0; i < password.length(); i++) {
			if (Character.isUpperCase(password.charAt(i))) {
				num_caps++;
			}
			if (Character.isDigit(password.charAt(i))) {
				num_nums++;
			}
		}
		
		/* check number of capital letters */
		if (num_caps < MIN_PASSWORD_CAPS) {
			return false;
		}

		/* check number of numbers */
		if (num_nums < MIN_PASSWORD_NUMS) {
			return false;
		}

		if (!util.isLetter(password)) {
			return false;
		}

		return true;
	}

	/***************************************************************************
	 * Check if the password with length len matches that given in
	 * pconn.server.password.
	 **************************************************************************/
	private boolean authdb_check_password(Connection pconn, 
			final String password, int len)
	{
// #ifdef HAVE_AUTH
		boolean ok = false;
// char buffer[512] = "";
		String buffer = "";
		char checksum[]= new char[Md5.DIGEST_HEX_BYTES];
//		MYSQL *sock, mysql;
		//TODO: use jdbc to rewrite this part

		/* do the password checking right here */
		Md5.create_md5sum(password, len, checksum);
		ok = (!checksum.equals(pconn.server.password))
		? true : false;

		/*
		 * we don't really need the stuff below here to verify password, this is
		 * just logging
		 */
//		mysql_init(mysql);
//
//		/* attempt to connect to the server */
//		if ((sock = mysql_real_connect(mysql, HOST, USER, PASSWORD, 
//				DATABASE, 0, null, 0))) {
//			/* insert an entry into our log */
//			buffer = util.my_snprintf(
//					"insert into %s (name, logintime, address, succeed) " +
//					"values ('%s',unix_timestamp(),'%s','%s')", LOGIN_TABLE,
//					pconn.username, pconn.server.ipaddr, ok ? "S" : "F");
//
//			if (mysql_query(sock, buffer)) {
//				util.freelog(Log.LOG_ERROR, "check_pass insert loginlog failed for user: %s (%s)",
//						pconn.username, mysql_error(sock));
//			}
//			mysql_close(sock);
//		} else {
//			util.freelog(Log.LOG_ERROR, "Can't connect to server! (%s)", mysql_error(mysql));
//		}

		return ok;
// #else
// return true;
// #endif
	}

	/***************************************************************************
	 * Loads a user from the database.
	 **************************************************************************/
	static authdb_status auth_db_load(Connection pconn)
	{
// #ifdef HAVE_AUTH
// char buffer[512] = "";
		String buffer = "";
		int num_rows = 0;
//		MYSQL *sock, mysql;
//		MYSQL_RES *res;
//		MYSQL_ROW row;
//
//		mysql_init(&mysql);
//
//		/* attempt to connect to the server */
//		if (!(sock = mysql_real_connect(mysql, HOST, USER, PASSWORD, DATABASE,
//				0, null, 0))) {
//			util.freelog(Log.LOG_ERROR, "Can't connect to server! (%s)", mysql_error(&mysql));
//			return authdb_status.AUTH_DB_ERROR;
//		} 
//
//		/* select the password from the entry */
//		buffer = util.my_snprintf( 
//				"select password from %s where name = '%s'",
//				AUTH_TABLE, pconn.username);
//
//		if (mysql_query(sock, buffer)) {
//			util.freelog(Log.LOG_ERROR, "db_load query failed for user: %s (%s)",
//					pconn.username, mysql_error(sock));
//			return authdb_status.AUTH_DB_ERROR;
//		} 
//
//		res = mysql_store_result(sock);
//		num_rows = mysql_num_rows(res);
//
//		/* if num_rows = 0, then we could find no such user */
//		if (num_rows < 1) {
//			mysql_free_result(res);
//			mysql_close(sock);
//
//			return authdb_status.AUTH_DB_NOT_FOUND;
//		}
//
//		/*
//		 * if there are more than one row that matches this name, it's an error
//		 * continue anyway though
//		 */
//		if (num_rows > 1) {
//			util.freelog(Log.LOG_ERROR, "db_load query found multiple entries (%d) for user: %s",
//					num_rows, pconn.username);
//		}
//
//		/* if there are rows, then fetch them and use the first one */
//		row = mysql_fetch_row(res);
//		mystrlcpy(pconn.server.password, row[0], sizeof(pconn.server.password));
//		mysql_free_result(res);
//
//		/* update the access time for this user */
//		buffer = util.my_snprintf(
//				"update %s set accesstime=unix_timestamp(), address='%s', " +
//				"logincount=logincount+1 where strcmp(name, '%s') = 0",
//				AUTH_TABLE, pconn.server.ipaddr, pconn.username);
//
//		if (mysql_query(sock, buffer)) {
//			util.freelog(Log.LOG_ERROR, "db_load update accesstime failed for user: %s (%s)",
//					pconn.username, mysql_error(sock));
//		}
//
//		mysql_close(sock);
// #endif
		return authdb_status.AUTH_DB_SUCCESS;
	}

	/***************************************************************************
	 * Saves pconn fields to the database. If the username already exists,
	 * replace the data.
	 **************************************************************************/
	static boolean auth_db_save(Connection pconn)
	{
// #ifdef HAVE_AUTH
// char buffer[1024] = "";
//		String buffer ="";
//		MYSQL *sock, mysql;
//
//		mysql_init(mysql);
//
//		/* attempt to connect to the server */
//		if (!(sock = mysql_real_connect(mysql, HOST, USER, PASSWORD, DATABASE,
//				0, null, 0))) {
//			util.freelog(Log.LOG_ERROR, "Can't connect to server! (%s)", mysql_error(mysql));
//			return false;
//		}
//
//		/*
//		 * insert new user into table. we insert the following things: name
//		 * md5sum of the password, the creation time in seconds, the accesstime
//		 * also in seconds from 1970, the users address (twice) and the
//		 * logincount
//		 */
//		buffer = util.my_snprintf(
//				"insert into %s values "+
//				"(null, '%s', md5('%s'), null, unix_timestamp(), unix_timestamp(),"+
//				"'%s', '%s', 0)", AUTH_TABLE, pconn.username,
//				pconn.server.password, pconn.server.ipaddr, pconn.server.ipaddr);
//
//		if (mysql_query(sock, buffer)) {
//			util.freelog(Log.LOG_ERROR, "db_save insert failed for new user: %s (%s)",
//					pconn.username, mysql_error(sock));
//			mysql_close(sock);
//			return false;
//		}
//
//		/* insert an entry into our log */
//		memset(buffer, 0, sizeof(buffer));
//		buffer = util.my_snprintf(
//				"insert into %s (name, logintime, address, succeed) "+
//				"values ('%s',unix_timestamp(),'%s', 'S')", LOGIN_TABLE,
//				pconn.username, pconn.server.ipaddr);
//
//		if (mysql_query(sock, buffer)) {
//			util.freelog(Log.LOG_ERROR, "db_load insert loginlog failed for user: %s (%s)",
//					pconn.username, mysql_error(sock));
//		}
//
//		mysql_close(sock);
//		#endif
		return true;
	}
}