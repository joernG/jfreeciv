package server;
import static common.Version.freeciv_name_version;
import static utility.shared.Shared_H.BUG_EMAIL_ADDRESS;
import static utility.shared.Shared_H.WEBSITE_URL;

import java.io.PrintStream;
public class Civserver {

	private static final int EXIT_SUCCESS = 0;

	/***************************************************************************
	 * Entry point for Freeciv server. Basically, does two things: 1. Parses
	 * command-line arguments (possibly dialog, on mac). 2. Calls the main
	 * server-loop routine.
	 **************************************************************************/
	public static void main(String[] args) {
		Srv_main server = new Srv_main();
		/* initialize server */
		server.srv_init();

		Option op = new Option();
		op.parse(args);

		if (op.showvers && !op.showhelp) {
			fc_fprintf(System.err, "%s \n", freeciv_name_version());
			System.exit(EXIT_SUCCESS);
		}
		Console.con_write(Erfc_status.C_VERSION, "This is the server for %s",
				freeciv_name_version());
		Console.con_write(Erfc_status.C_COMMENT, "You can learn a lot about Freeciv at %s",
				WEBSITE_URL);

		if (op.showhelp) {
			fc_fprintf(System.err,
					"Usage: %s [option ...]\nValid options are:\n", args[0]);
//			#ifdef HAVE_AUTH
			fc_fprintf(System.err, " -a --auth\t\tEnable server authentication.\n");
			fc_fprintf(System.err, (" -G --Guests\t\tAllow guests to "+
			"login if auth is enabled.\n"));
			fc_fprintf(System.err, (" -N --Newusers\tAllow new users to "+
			"login if auth is enabled.\n"));
//			#endif
			fc_fprintf(System.err, " -b --bind ADDR\tListen for clients on ADDR\n");
//			#ifdef DEBUG
			fc_fprintf(System.err, (" -d, --debug NUM\tSet debug log level (0 to 4,"+
			" or 4:file1,min,max:...)\n"));
//			#else
				fc_fprintf(System.err,
				" -d, --debug NUM\tSet debug log level (0 to 3)\n");
//			#endif
			fc_fprintf(System.err, " -f, --file FILE\tLoad saved Game.game FILE\n");
			fc_fprintf(System.err,
			" -g, --Gamelog.gamelog FILE\tUse FILE as Game.game logfile\n");
			fc_fprintf(System.err,
			" -h, --help\t\tPrint a summary of the options\n");
			fc_fprintf(System.err, " -l, --log FILE\tUse FILE as logfile\n");
			fc_fprintf(System.err, (" -m, --meta\t\tNotify metaserver and " +
			"send server's info\n"));
			fc_fprintf(System.err, (" -M, --Metaserver ADDR\tSet ADDR " +
			"as metaserver address\n"));

			fc_fprintf(System.err, (" -p, --port PORT\tListen for clients on " +
			"port PORT\n"));
			fc_fprintf(System.err, (" -q, --quitidle TIME\tQuit if no players " +
			"for TIME seconds\n"));
			fc_fprintf(System.err, (" -e, --exit-on-end\t" +
			"When a Game.game ends, exit instead of restarting\n"));
			fc_fprintf(System.err,
			" -s, --saves DIR\tSave games to directory DIR\n");
			fc_fprintf(System.err,
			" -S, --Serverid ID\tSets the server id to ID\n");
			fc_fprintf(System.err,
			" -P, --Ppm\t\tSave ppms of the map when saving the Game.game.\n");
			fc_fprintf(System.err, " -r, --read FILE\tRead startup script FILE\n");
			fc_fprintf(System.err, " -v, --version\t\tPrint the version number\n");
			fc_fprintf(System.err, "Report bugs to <%s>.\n", BUG_EMAIL_ADDRESS);
			System.exit(EXIT_SUCCESS);
		}

		/* disallow running as root -- too dangerous */
//		dont_run_as_root(args[0], "freeciv_server");

		/* have arguments, call the main server loop... */
		server.srv_main();

		/* Technically, we won't ever get here. We exit via server_quit. */

		/* done */
		System.exit(EXIT_SUCCESS);
	}

	private static void fc_fprintf(PrintStream p, String format, Object ... args) {
		// TODO Auto-generated method stub
		p.println(String.format(format, args));
	}
}