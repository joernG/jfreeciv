package server;

import common.connection.cmdlevel_id;

public class Commands{
	/**************************************************************************
	  Commands - can be recognised by unique prefix
	**************************************************************************/
//	struct command {
	  public String name;       /* name - will be matched by unique prefix   */
	  cmdlevel_id game_level; /* access level to use the command, in-game  */
	  cmdlevel_id pregame_level; /* access level to use, in pregame */
	  public String synopsis;	  /* one or few-line summary of usage */
	  public String short_help; /* one line (about 70 chars) description */
	  public String extra_help; /* extra help information; will be line-wrapped */
//	};

	  /* Order here is important: for ambiguous abbreviations the first
	   match is used.  Arrange order to:
	   - allow old commands 's', 'h', 'l', 'q', 'c' to work.
	   - reduce harm for ambiguous cases, where "harm" includes inconvenience,
	     eg accidently removing a player in a running game.
	*/
	enum command_id {
	  /* old one-letter commands: */
	  CMD_START_GAME ,//= 0,
	  CMD_HELP,
	  CMD_LIST,
	  CMD_QUIT,
	  CMD_CUT,

	  /* completely non-harmful: */
	  CMD_EXPLAIN,
	  CMD_SHOW,
	  CMD_SCORE,
	  CMD_WALL,
	  CMD_VOTE,
	  
	  /* mostly non-harmful: */
	  CMD_DEBUG,
	  CMD_SET,
	  CMD_TEAM,
	  CMD_RULESETDIR,
	  CMD_METAMESSAGE,
	  CMD_METATOPIC,
	  CMD_METAPATCHES,
	  CMD_METACONN,
	  CMD_METASERVER,
	  CMD_AITOGGLE,
	  CMD_TAKE,
	  CMD_OBSERVE,
	  CMD_DETACH,
	  CMD_CREATE,
	  CMD_AWAY,
	  CMD_NOVICE,
	  CMD_EASY,
	  CMD_NORMAL,
	  CMD_HARD,
	  CMD_EXPERIMENTAL,
	  CMD_CMDLEVEL,
	  CMD_FIRSTLEVEL,
	  CMD_TIMEOUT,

	  /* potentially harmful: */
	  CMD_END_GAME,
	  CMD_REMOVE,
	  CMD_SAVE,
	  CMD_LOAD,
	  CMD_READ_SCRIPT,
	  CMD_WRITE_SCRIPT,

	  /* undocumented */
	  CMD_RFCSTYLE,
	  CMD_SRVID,

	  /* pseudo-commands: */
	  CMD_NUM,		/* the number of commands - for iterations */
	  CMD_UNRECOGNIZED,	/* used as a possible iteration result */
	  CMD_AMBIGUOUS		/* used as a possible iteration result */
	};

/* Commands must match the values in enum command_id. */
public static final Commands commands[] = {
  new Commands("start",	cmdlevel_id.ALLOW_INFO, cmdlevel_id.ALLOW_INFO,
   "start",
   "Start the game, or restart after loading a savegame.",
   ("This command starts the game.  When starting a new game, " +
      "it should be used after all human players have connected, and " +
      "AI players have been created (if required), and any desired "+
      "changes to initial server options have been made.  "+
      "After 'start', each human player will be able to "+
      "choose their nation, and then the game will begin.  "+
      "This command is also required after loading a savegame "+
      "for the game to recommence.  Once the game is running this command "+
      "is no longer available, since it would have no effect.")
  ),

  new Commands("help",	cmdlevel_id.ALLOW_INFO, cmdlevel_id.ALLOW_INFO,
   /* TRANS: translate text between <> only */
   ("help\n" +
      "help commands\n" +
      "help options\n"+
      "help <command-name>\n"+
      "help <option-name>"),
   "Show help about server commands and server options.",
   ("With no arguments gives some introductory help.  "+
      "With argument \"commands\" or \"options\" gives respectively "+
      "a list of all commands or all options.  "+
      "Otherwise the argument is taken as a command name or option name, "+
      "and help is given for that command or option.  For options, the help "+
      "information includes the current and default values for that option.  "+
      "The argument may be abbreviated where unambiguous.")
),

  new Commands("list",	cmdlevel_id.ALLOW_INFO, cmdlevel_id.ALLOW_INFO,
   "list\n"+
   "list players\n"+
   "list connections",
   "Show a list of players or connections.",
   ("Show a list of players, or a list of connections to the server.  "+
      "The argument may be abbreviated, and defaults to 'players' if absent.")
),
  new Commands("quit",	cmdlevel_id.ALLOW_HACK, cmdlevel_id.ALLOW_HACK,
   "quit",
   "Quit the game and shutdown the server.", null
),
  new Commands("cut",	cmdlevel_id.ALLOW_CTRL, cmdlevel_id.ALLOW_CTRL,
   /* TRANS: translate text between <> only */
   "cut <connection-name>",
   "Cut a client's connection to server.",
   ("Cut specified client's connection to the server, removing that client "+
      "from the game.  If the game has not yet started that client's player "+
      "is removed from the game, otherwise there is no effect on the player.  "+
      "Note that this command now takes connection names, not player names.")
),
 new Commands("explain",	cmdlevel_id.ALLOW_INFO, cmdlevel_id.ALLOW_INFO,
   /* TRANS: translate text between <> only */
   ("explain\n"+
      "explain <option-name>"),
   "Explain server options.",
   ("The 'explain' command gives a subset of the functionality of 'help', "+
      "and is included for backward compatibility.  With no arguments it "+
      "gives a list of options (like 'help options'), and with an argument "+
      "it gives help for a particular option (like 'help <option-name>').")
),
 new Commands("show",	cmdlevel_id.ALLOW_INFO, cmdlevel_id.ALLOW_INFO,
   /* TRANS: translate text between <> only */
   ("show\n"+
      "show <option-name>\n"+
      "show <option-prefix>"),
   "Show server options.",
   ("With no arguments, shows all server options (or available options, when "+
      "used by clients).  With an argument, show only the named option, "+
      "or options with that prefix.")
),
  new Commands("score",	cmdlevel_id.ALLOW_CTRL, cmdlevel_id.ALLOW_CTRL,
   "score",
   "Show current scores.",
   ("For each connected client, pops up a window showing the current "+
      "player scores.")
),
  new Commands("wall",	cmdlevel_id.ALLOW_HACK, cmdlevel_id.ALLOW_HACK,
   "wall <message>",
   "Send message to all connections.",
   ("For each connected client, pops up a window showing the message "+
      "entered.")
),
  new Commands("vote",	cmdlevel_id.ALLOW_INFO, cmdlevel_id.ALLOW_INFO,
   "vote yes|no [vote number]",
   "Cast a vote.",
      /* xgettext:no-c-format */
   ("A player with info level access issuing a control level command "+
      "starts a new vote for the command.  The /vote command followed by "+
      "\"yes\" or \"no\", and optionally a vote number, "+
      "gives your vote.  If you do not add a vote number, your vote applies "+
      "to the latest command.  You can only suggest one vote at a time.  "+
      "The vote will pass immediately if more than half of the players "+
      "vote for it, or fail immediately if at least half of the players "+
      "vote against it.  If one full turn elapses the vote may pass in any "+
      "case if nobody votes against it.")
),
  new Commands("debug",	cmdlevel_id.ALLOW_CTRL, cmdlevel_id.ALLOW_CTRL,
   "debug [ player <player> | city <x> <y> | units <x> <y> | unit <id> ]",
   "Turn on or off AI debugging of given entity.",
   ("Print AI debug information about given entity and turn continous "+
      "debugging output for this entity on or off.")
),
 new Commands("set",	cmdlevel_id.ALLOW_CTRL, cmdlevel_id.ALLOW_INFO,
   "set <option-name> <value>",
   "Set server option.", null
),
  new Commands("team",	cmdlevel_id.ALLOW_CTRL, cmdlevel_id.ALLOW_INFO,
   "team <player> [team]",
   "Change, add or remove a player's team affiliation.",
   ("Sets a player as member of a team. If no team specified, the "+
      "player is set teamless. Use \"\" if names contain whitespace. "+
      "A team is a group of players that start out allied, with shared "+
      "vision and embassies, and fight together to achieve team victory "+
      "with averaged individual scores.")
),
  new Commands("rulesetdir", cmdlevel_id.ALLOW_CTRL, cmdlevel_id.ALLOW_INFO,
   "rulesetdir <directory>",
   "Choose new ruleset directory or modpack.",
   ("Choose new ruleset directory or modpack. Calling this\n "+
      "without any arguments will show you the currently selected "+
      "ruleset.")
),
  new Commands("metamessage", cmdlevel_id.ALLOW_CTRL, cmdlevel_id.ALLOW_INFO,
   /* TRANS: translate text between <> only */
   "metainfo <meta-line>",
   "Set metaserver info line.", null
),
 new Commands("metatopic", cmdlevel_id.ALLOW_CTRL, cmdlevel_id.ALLOW_INFO,
   /* TRANS: translate text between <> only */
   "metatopic <meta-line>",
   "Set metaserver topic line.", null
),
  new Commands("metapatches", cmdlevel_id.ALLOW_HACK, cmdlevel_id.ALLOW_HACK,
   /* TRANS: translate text between <> only */
   "metapatch <meta-line>",
   "Set metaserver patches line.", null
),
  new Commands("metaconnection",	cmdlevel_id.ALLOW_HACK, cmdlevel_id.ALLOW_HACK,
   "metaconnection u|up\n"+
   "metaconnection d|down\n"+
   "metaconnection ?",
   "Control metaserver connection.",
   ("'metaconnection ?' reports on the status of the connection to metaserver.\n"+
      "'metaconnection down' or 'metac d' brings the metaserver connection down.\n"+
      "'metaconnection up' or 'metac u' brings the metaserver connection up.")
),
  new Commands("metaserver",cmdlevel_id.ALLOW_HACK, cmdlevel_id.ALLOW_HACK,
   /* TRANS: translate text between <> only */
   "metaserver <address>",
   "Set address (URL) for metaserver to report to.", null
),
  new Commands("aitoggle",	cmdlevel_id.ALLOW_CTRL, cmdlevel_id.ALLOW_INFO,
   /* TRANS: translate text between <> only */
   "aitoggle <player-name>",
   "Toggle AI status of player.", null
),
  new Commands("take",    cmdlevel_id.ALLOW_INFO, cmdlevel_id.ALLOW_INFO,
   /* TRANS: translate text between [] and <> only */
   "take [connection-name] <player-name>",
   "Take over a player's place in the game.",
   ("Only the console and connections with cmdlevel 'hack' can force "+
      "other connections to take over a player. If you're not one of these, "+
      "only the <player-name> argument is allowed")
),
  new Commands("observe",    cmdlevel_id.ALLOW_INFO, cmdlevel_id.ALLOW_INFO,
   /* TRANS: translate text between [] only */
   "observe [connection-name] [player-name]",
   "Observe a player or the whole game.",
   ("Only the console and connections with cmdlevel 'hack' can force "+
      "other connections to observe a player. If you're not one of these, "+
      "only the [player-name] argument is allowed. If the console gives no "+
      "player-name or the connection uses no arguments, then the connection "+
      "is attached to a global observer.")
),
  new Commands("detach",    cmdlevel_id.ALLOW_INFO, cmdlevel_id.ALLOW_INFO,
   /* TRANS: translate text between <> only */
   "detach <connection-name>",
   "detach from a player.",
   ("Only the console and connections with cmdlevel 'hack' can force "+
      "other connections to detach from a player.")
),
 new Commands("create",	cmdlevel_id.ALLOW_CTRL, cmdlevel_id.ALLOW_INFO,
   /* TRANS: translate text between <> only */
   "create <player-name>",
   "Create an AI player with a given name.",
   ("The 'create' command is only available before the game has "+
      "been started.")
),
  new Commands("away",	cmdlevel_id.ALLOW_INFO, cmdlevel_id.ALLOW_INFO,
   ("away\n" +
      "away"),
   "Set yourself in away mode. The AI will watch your back.",
   "The AI will govern your nation but do minimal changes."
),
  new Commands("novice",	cmdlevel_id.ALLOW_CTRL, cmdlevel_id.ALLOW_INFO,
   /* TRANS: translate text between <> only */
   ("novice\n"+
      "novice <player-name>"),
   "Set one or all AI players to 'novice'.",
   ("With no arguments, sets all AI players to skill level 'novice', and "+
      "sets the default level for any new AI players to 'novice'.  With an "+
      "argument, sets the skill level for that player only.")
),
  new Commands("easy",	cmdlevel_id.ALLOW_CTRL, cmdlevel_id.ALLOW_INFO,
   /* TRANS: translate text between <> only */
   ("easy\n"+
      "easy <player-name>"),
   "Set one or all AI players to 'easy'.",
   ("With no arguments, sets all AI players to skill level 'easy', and "+
      "sets the default level for any new AI players to 'easy'.  With an "+
      "argument, sets the skill level for that player only.")
),
  new Commands("normal",	cmdlevel_id.ALLOW_CTRL, cmdlevel_id.ALLOW_INFO,
   /* TRANS: translate text between <> only */
   ("normal\n" +
      "normal <player-name>"),
   "Set one or all AI players to 'normal'.",
   ("With no arguments, sets all AI players to skill level 'normal', and "+
      "sets the default level for any new AI players to 'normal'.  With an "+
      "argument, sets the skill level for that player only.")
),
 new Commands("hard",	cmdlevel_id.ALLOW_CTRL, cmdlevel_id.ALLOW_INFO,
   /* TRANS: translate text between <> only */
   ("hard\n"+
      "hard <player-name>"),
   "Set one or all AI players to 'hard'.",
   ("With no arguments, sets all AI players to skill level 'hard', and "+
      "sets the default level for any new AI players to 'hard'.  With an "+
      "argument, sets the skill level for that player only.")
),
 new Commands("experimental",	cmdlevel_id.ALLOW_CTRL, cmdlevel_id.ALLOW_INFO,
   /* TRANS: translate text between <> only */
   ("experimental\n"+
      "experimental <player-name>"),
   "Set one or all AI players to 'experimental'.",
   ("With no arguments, sets all AI players to skill 'experimental', and "+
      "sets the default level for any new AI players to this.  With an "+
      "argument, sets the skill level for that player only. THIS IS ONLY "+
      "FOR TESTING OF NEW AI FEATURES! For ordinary servers, this option "+
      "has no effect.")
),
 new Commands("cmdlevel",	cmdlevel_id.ALLOW_HACK, cmdlevel_id.ALLOW_HACK, /* confusing at cmdlevel_id.ALLOW_CTRL */
   /* TRANS: translate text between <> only */
   ("cmdlevel\n"+
      "cmdlevel <level>\n"+
      "cmdlevel <level> new\n"+
      "cmdlevel <level> first\n"+
      "cmdlevel <level> <connection-name>"),
   "Query or set command access level access.",
  ("The command access level controls which server commands are available\n"+
      "to users via the client chatline.  The available levels are:\n"+
      "    none  -  no commands\n"+
      "    info  -  informational commands only\n"+
      "    ctrl  -  commands that affect the game and users\n"+
      "    hack  -  *all* commands - dangerous!\n"+
      "With no arguments, the current command access levels are reported.\n"+
      "With a single argument, the level is set for all existing "+
      "connections,\nand the default is set for future connections.\n"+
      "If 'new' is specified, the level is set for newly connecting clients.\n"+
      "If 'first come' is specified, the 'first come' level is set; it will be\n"+
      "granted to the first client to connect, or if there are connections\n"+
      "already, the first client to issue the 'firstlevel' command.\n"+
      "If a connection name is specified, the level is set for that "+
      "connection only.\n"+
      "Command access levels do not persist if a client disconnects, "+
      "because some untrusted person could reconnect with the same name.  "+
      "Note that this command now takes connection names, not player names."
      )
),
  new Commands("firstlevel", cmdlevel_id.ALLOW_INFO, cmdlevel_id.ALLOW_INFO,
   "firstlevel",
   "Grab the 'first come' command access level.",
   ("If 'cmdlevel first come' has been used to set a special 'first come'\n"+
      "command access level, this is the command to grab it with.")
),
  new Commands("timeoutincrease", cmdlevel_id.ALLOW_CTRL, cmdlevel_id.ALLOW_INFO,
   /* TRANS: translate text between <> only */
   "timeoutincrease <turn> <turninc> <value> <valuemult>", 
   ("See \"help timeoutincrease\"."),
   ("Every <turn> turns, add <value> to timeout timer, then add <turninc> "+
      "to <turn> and multiply <value> by <valuemult>.  Use this command in "+
      "concert with the option \"timeout\". Defaults are 0 0 0 1")
),
  new Commands("endgame",	cmdlevel_id.ALLOW_CTRL, cmdlevel_id.ALLOW_CTRL,
   /* TRANS: translate text between <> only */
   "endgame <player1 player2 player3 ...>",
   "End the game.  If players are listed, these win the game.",
   ("This command ends the game immediately and credits the given players, "+
      "if any, with winning it.")
),
  new Commands("remove",	cmdlevel_id.ALLOW_CTRL, cmdlevel_id.ALLOW_INFO,
   /* TRANS: translate text between <> only */
   "remove <player-name>",
   "Fully remove player from game.",
   ("This *completely* removes a player from the game, including "+
      "all cities and units etc.  Use with care!")
),
  new Commands("save",	cmdlevel_id.ALLOW_HACK, cmdlevel_id.ALLOW_HACK,
   /* TRANS: translate text between <> only */
   ("save\n"+
      "save <file-name>"),
   "Save game to file.",
   ("Save the current game to file <file-name>.  If no file-name "+
      "argument is given saves to \"<auto-save name prefix><year>m.sav[.gz]\".\n"+
      "To reload a savegame created by 'save', start the server with "+
      "the command-line argument:\n"+
      "    --file <filename>\n"+
      "and use the 'start' command once players have reconnected.")
),
  new Commands("load",      cmdlevel_id.ALLOW_HACK, cmdlevel_id.ALLOW_HACK,
   /* TRANS: translate text between <> only */
   ("load\n"+
      "load <file-name>"),
   "Load game from file.",
   ("Load a game from <file-name>. Any current data including players, "+
      "rulesets and server options are lost.\n")
),
  new Commands("read",	cmdlevel_id.ALLOW_HACK, cmdlevel_id.ALLOW_HACK,
   /* TRANS: translate text between <> only */
   "read <file-name>",
   "Process server commands from file.", null
),
  new Commands("write",	cmdlevel_id.ALLOW_HACK, cmdlevel_id.ALLOW_HACK,
   /* TRANS: translate text between <> only */
   "write <file-name>",
   "Write current settings as server commands to file.", null
),
  new Commands("rfcstyle",	cmdlevel_id.ALLOW_HACK, cmdlevel_id.ALLOW_HACK,
   "rfcstyle",
   "Switch server output between 'RFC-style' and normal style.", null
),
  new Commands("serverid",	cmdlevel_id.ALLOW_INFO, cmdlevel_id.ALLOW_INFO,
   "serverid",
   "Simply returns the id of the server.", null)
  };
//};


public Commands(String name, cmdlevel_id game_level, cmdlevel_id pregame_level,
		String synopsis, String short_help, String extra_help) {
	super();
	this.name = name;
	this.game_level = game_level;
	this.pregame_level = pregame_level;
	this.synopsis = synopsis;
	this.short_help = short_help;
	this.extra_help = extra_help;
}}