package server.srv_main;

public class server_arguments {
//	struct server_arguments {
	/* metaserver information */
	public boolean  metaserver_no_send;
//	char metaserver_addr[256];
	public  String metaserver_addr;
	public int metaserver_port;
	/* address this server is to listen on (NULL => INADDR_ANY) */
//	char *bind_addr;
	public String bind_addr;
	/* this server's listen port */
	public int port;
	/* the log level */
	public int loglevel;
	/* filenames */
//	char *log_filename;
	public String log_filename;
//	char *gamelog_filename;
	public String gamelog_filename;
//	char load_filename[512]; /* FIXME: may not be long enough? use MAX_PATH? */
	public String load_filename;
//	char *script_filename;
	public String script_filename;
//	char *saves_pathname;
	public String saves_pathname;
//	char serverid[256];
	public String serverid;
	/* save a ppm of the map? */
	public boolean  save_ppm;
	/* quit if there no players after a given time interval */
	public int quitidle;
	/* exit the server on Game.game ending */
	public boolean  exit_on_end;
	/* what kind of end Game.game we should use */
//	bv_draw draw;
	/* authentication options */
	public boolean  auth_enabled;            /* defaults to FALSE */
	public boolean  auth_allow_guests;       /* defaults to TRUE */
	public boolean  auth_allow_newusers;     /* defaults to TRUE */
//	};

}
