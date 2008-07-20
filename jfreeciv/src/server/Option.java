package server;

public class Option {
	boolean showhelp = false;
	boolean showvers = false;
	// char *option = null;


	//TODO: 
	public void parse(String[] args){
//		int inx;
		/* parse command-line arguments... */

		/* no we don't use GNU's getopt or even the "standard" getopt */
		/* yes we do have reasons ;) */
//		inx = 1;
//		while (inx < argc) {
//			if ((option = get_option("--file", argv, &inx, argc)))
//				sz_strlcpy(server.server.srvarg.load_filename, option);
//			else if (is_option("--help", argv[inx])) {
//				showhelp = true;
//				break;
//			} else if ((option = get_option("--log", argv, &inx, argc)))
//				server.srvarg.log_filename = option;
//			else if ((option = get_option("--Gamelog.gamelog", argv, &inx, argc)))
//				server.srvarg.gamelog_filename = option;
//			else if (is_option("--nometa", argv[inx])) {
//				fc_fprintf(stderr, ("Warning: the %s option is obsolete. "
//				"Use -m to enable the metaserver.\n"), argv[inx]);
//				showhelp = true;
//			} else if (is_option("--meta", argv[inx]))
//				server.srvarg.metaserver_no_send = false;
//			else if ((option = get_option("--Metaserver", argv, &inx, argc))) {
//				sz_strlcpy(server.server.srvarg.metaserver_addr, argv[inx]);
//				/*
//				 * --Metaserver implies
//				 * --meta
//				 */
//				server.srvarg.metaserver_no_send = false; 
//			} else if ((option = get_option("--port", argv, &inx, argc))) {
//				if (sscanf(option, "%d", &server.srvarg.port) != 1) {
//					showhelp = true;
//					break;
//				}
//			} else if ((option = get_option("--bind", argv, &inx, argc))) {
//				server.srvarg.bind_addr = option;
//			} else if ((option = get_option("--read", argv, &inx, argc)))
//				server.srvarg.script_filename = option;
//			else if ((option = get_option("--quitidle", argv, &inx, argc))) {
//				if (sscanf(option, "%d", &server.srvarg.quitidle) != 1) {
//					showhelp = true;
//					break;
//				}
//			} else if (is_option("--exit-on-end", argv[inx])) {
//				server.srvarg.exit_on_end = true;
//			} else if ((option = get_option("--debug", argv, &inx, argc))) {
//				server.srvarg.loglevel = log_parse_level_str(option);
//				if (server.srvarg.loglevel == -1) {
//					server.srvarg.loglevel = Log.LOG_NORMAL;
//					showhelp = true;
//					break;
//				}
////				#ifdef HAVE_AUTH
//			} else if (is_option("--auth", argv[inx])) {
//				server.srvarg.auth_enabled = true;
//			} else if (is_option("--Guests", argv[inx])) {
//				server.srvarg.auth_allow_guests = true;
//			} else if (is_option("--Newusers", argv[inx])) {
//				server.srvarg.auth_allow_newusers = true;
////				#endif
//			} else if (is_option("--Ppm", argv[inx])) {
//				server.srvarg.save_ppm = true;
//			} else if ((option = get_option("--Serverid", argv, &inx, argc))) {
//				sz_strlcpy(server.srvarg.serverid, option);
//			} else if ((option = get_option("--saves", argv, &inx, argc))) {
//				server.srvarg.saves_pathname = option;
//			} else if (is_option("--version", argv[inx]))
//				showvers = true;
//			else {
//				fc_fprintf(stderr, "Error: unknown option '%s'\n", argv[inx]);
//				showhelp = true;
//				break;
//			}
//			inx++;
//		}
	}
}
