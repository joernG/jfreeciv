package common.game;

import utility.shared.Shared_H;

public class Game_H {
	public static final int GAME_START_YEAR = -4000;

	public static final int GAME_DEFAULT_SEED = 0;
	public static final int GAME_MIN_SEED = 0;
	public static final int GAME_MAX_SEED = Integer.MAX_VALUE >> 1;//(MAX_UINT32 >> 1);

	public static final int GAME_DEFAULT_GOLD = 50;
	public static final int GAME_MIN_GOLD = 0;
	public static final int GAME_MAX_GOLD = 5000;

	public static final String GAME_DEFAULT_START_UNITS = "ccx";

	public static final int GAME_DEFAULT_DISPERSION = 0;
	public static final int GAME_MIN_DISPERSION = 0;
	public static final int GAME_MAX_DISPERSION = 10;

	public static final int GAME_DEFAULT_TECHLEVEL = 0;
	public static final int GAME_MIN_TECHLEVEL = 0;
	public static final int GAME_MAX_TECHLEVEL = 50;

	public static final int GAME_DEFAULT_UNHAPPYSIZE = 4;
	public static final int GAME_MIN_UNHAPPYSIZE = 1;
	public static final int GAME_MAX_UNHAPPYSIZE = 6;

	public static final boolean GAME_DEFAULT_ANGRYCITIZEN = true;

	public static final int GAME_DEFAULT_END_YEAR = 2000;
	public static final int GAME_MIN_END_YEAR = GAME_START_YEAR;
	public static final int GAME_MAX_END_YEAR = 5000;

	public static final int GAME_DEFAULT_MIN_PLAYERS = 1;
	public static final int GAME_MIN_MIN_PLAYERS = 1;
	public static final int GAME_MAX_MIN_PLAYERS = Shared_H.MAX_NUM_PLAYERS;

	public static final int GAME_DEFAULT_MAX_PLAYERS = Shared_H.MAX_NUM_PLAYERS;
	public static final int GAME_MIN_MAX_PLAYERS = 1;
	public static final int GAME_MAX_MAX_PLAYERS = Shared_H.MAX_NUM_PLAYERS;

	public static final int GAME_DEFAULT_AIFILL = 0;
	public static final int GAME_MIN_AIFILL = 0;
	public static final int GAME_MAX_AIFILL = GAME_MAX_MAX_PLAYERS;

	public static final int GAME_DEFAULT_RESEARCHCOST = 20;
	public static final int GAME_MIN_RESEARCHCOST = 4;
	public static final int GAME_MAX_RESEARCHCOST = 100;

	public static final int GAME_DEFAULT_DIPLCOST = 0;
	public static final int GAME_MIN_DIPLCOST = 0;
	public static final int GAME_MAX_DIPLCOST = 100;

	public static final boolean GAME_DEFAULT_FOGOFWAR = true;

	/* 0 means no national borders.  Performance dropps quickly as the border
	 * distance increases (o(n^2) or worse). */
	public static final int GAME_DEFAULT_BORDERS = 7;
	public static final int GAME_MIN_BORDERS = 0;
	public static final int GAME_MAX_BORDERS = 24;

	public static final boolean GAME_DEFAULT_HAPPYBORDERS = true;

	public static final boolean GAME_DEFAULT_SLOW_INVASIONS = true;

	public static final int GAME_DEFAULT_DIPLOMACY = 0;
	public static final int GAME_MIN_DIPLOMACY = 0;
	public static final int GAME_MAX_DIPLOMACY = 4;

	public static final int GAME_DEFAULT_DIPLCHANCE = 80;
	public static final int GAME_MIN_DIPLCHANCE = 1;
	public static final int GAME_MAX_DIPLCHANCE = 99;

	public static final int GAME_DEFAULT_FREECOST = 0;
	public static final int GAME_MIN_FREECOST = 0;
	public static final int GAME_MAX_FREECOST = 100;

	public static final int GAME_DEFAULT_CONQUERCOST = 0;
	public static final int GAME_MIN_CONQUERCOST = 0;
	public static final int GAME_MAX_CONQUERCOST = 100;

	public static final int GAME_DEFAULT_CITYFACTOR = 14;
	public static final int GAME_MIN_CITYFACTOR = 6;
	public static final int GAME_MAX_CITYFACTOR = 100;

	public static final int GAME_DEFAULT_CITYMINDIST = 0;
	public static final int GAME_MIN_CITYMINDIST = 0; /* if 0, ruleset will overwrite this */
	public static final int GAME_MAX_CITYMINDIST = 5;

	public static final int GAME_DEFAULT_CIVILWARSIZE = 10;
	public static final int GAME_MIN_CIVILWARSIZE = 6;
	public static final int GAME_MAX_CIVILWARSIZE = 1000;

	public static final int GAME_DEFAULT_CONTACTTURNS = 20;
	public static final int GAME_MIN_CONTACTTURNS = 0;
	public static final int GAME_MAX_CONTACTTURNS = 100;

	public static final int GAME_DEFAULT_RAPTUREDELAY = 1;
	public static final int GAME_MIN_RAPTUREDELAY = 1;
	public static final int GAME_MAX_RAPTUREDELAY = 99; /* 99 practicaly disables rapturing */
	 
	public static final boolean GAME_DEFAULT_SAVEPALACE = true;

	public static final boolean GAME_DEFAULT_NATURALCITYNAMES = true;

	public static final int GAME_DEFAULT_FOODBOX = 10;
	public static final int GAME_MIN_FOODBOX = 5;
	public static final int GAME_MAX_FOODBOX = 30;

	public static final int GAME_DEFAULT_AQUEDUCTLOSS = 0;
	public static final int GAME_MIN_AQUEDUCTLOSS = 0;
	public static final int GAME_MAX_AQUEDUCTLOSS = 100;

	public static final int GAME_DEFAULT_KILLCITIZEN = 1;
	public static final int GAME_MIN_KILLCITIZEN = 0;
	public static final int GAME_MAX_KILLCITIZEN = 15;

	public static final int GAME_DEFAULT_TECHPENALTY = 100;
	public static final int GAME_MIN_TECHPENALTY = 0;
	public static final int GAME_MAX_TECHPENALTY = 100;

	public static final int GAME_DEFAULT_RAZECHANCE = 20;
	public static final int GAME_MIN_RAZECHANCE = 0;
	public static final int GAME_MAX_RAZECHANCE = 100;

	public static final int GAME_DEFAULT_CIVSTYLE = 2;
	public static final int GAME_MIN_CIVSTYLE = 1;
	public static final int GAME_MAX_CIVSTYLE = 2;

	public static final boolean GAME_DEFAULT_SCORELOG = false;

	public static final boolean GAME_DEFAULT_SPACERACE = true;

	public static final boolean GAME_DEFAULT_TURNBLOCK = true;

	public static final boolean GAME_DEFAULT_AUTO_AI_TOGGLE = false;

	public static final int GAME_DEFAULT_TIMEOUT = 0;
	public static final int GAME_DEFAULT_TIMEOUTINT = 0;
	public static final int GAME_DEFAULT_TIMEOUTINTINC = 0;
	public static final int GAME_DEFAULT_TIMEOUTINC = 0;
	public static final int GAME_DEFAULT_TIMEOUTINCMULT = 1;
	public static final int GAME_DEFAULT_TIMEOUTADDEMOVE = 0;

//	#ifndef NDEBUG
	public static final int GAME_MIN_TIMEOUT = -1;
//	#else
//	public static final int GAME_MIN_TIMEOUT = 0;
//	#endif
	public static final int GAME_MAX_TIMEOUT = 8639999;

	public static final int GAME_DEFAULT_TCPTIMEOUT = 10;
	public static final int GAME_MIN_TCPTIMEOUT = 0;
	public static final int GAME_MAX_TCPTIMEOUT = 120;

	public static final int GAME_DEFAULT_NETWAIT = 4;
	public static final int GAME_MIN_NETWAIT = 0;
	public static final int GAME_MAX_NETWAIT = 20;

	public static final int GAME_DEFAULT_PINGTIME = 20;
	public static final int GAME_MIN_PINGTIME = 1;
	public static final int GAME_MAX_PINGTIME = 1800;

	public static final int GAME_DEFAULT_PINGTIMEOUT = 60;
	public static final int GAME_MIN_PINGTIMEOUT = 60;
	public static final int GAME_MAX_PINGTIMEOUT = 1800;

	public static final int GAME_DEFAULT_NOTRADESIZE = 0;
	public static final int GAME_MIN_NOTRADESIZE = 0;
	public static final int GAME_MAX_NOTRADESIZE = 49;

	public static final int GAME_DEFAULT_FULLTRADESIZE = 1;
	public static final int GAME_MIN_FULLTRADESIZE = 1;
	public static final int GAME_MAX_FULLTRADESIZE = 50;

	public static final int GAME_DEFAULT_BARBARIANRATE = 2;
	public static final int GAME_MIN_BARBARIANRATE = 0;
	public static final int GAME_MAX_BARBARIANRATE = 4;

	public static final int GAME_DEFAULT_ONSETBARBARIAN = (GAME_START_YEAR+ ((GAME_DEFAULT_END_YEAR-(GAME_START_YEAR))/3));
	public static final int GAME_MIN_ONSETBARBARIAN = GAME_START_YEAR;
	public static final int GAME_MAX_ONSETBARBARIAN = GAME_MAX_END_YEAR;

	public static final int GAME_DEFAULT_OCCUPYCHANCE = 0;
	public static final int GAME_MIN_OCCUPYCHANCE = 0;
	public static final int GAME_MAX_OCCUPYCHANCE = 100;

	public static final String  GAME_DEFAULT_RULESETDIR =     "default";

	public static final String  GAME_DEFAULT_SAVE_NAME  =     "civgame";
	
	public static final int GAME_DEFAULT_SKILL_LEVEL = 3;      /* easy */
	public static final int GAME_OLD_DEFAULT_SKILL_LEVEL = 5;  /* normal; for old save games */

	public static final String  GAME_DEFAULT_DEMOGRAPHY  =    "NASRLPEMOqrb";
	public static final String  GAME_DEFAULT_ALLOW_TAKE  =    "HAhadOo";

	public static final int GAME_DEFAULT_COMPRESS_LEVEL = 6;    /* if we have compression */
	public static final int GAME_MIN_COMPRESS_LEVEL = 0;
	public static final int GAME_MAX_COMPRESS_LEVEL = 9;
	public static final int GAME_NO_COMPRESS_LEVEL = 0;

	public static final int GAME_DEFAULT_REPUTATION = 1000;
	public static final int GAME_MAX_REPUTATION = 1000;
	public static final int GAME_REPUTATION_INCR = 2;

	public static final int GAME_DEFAULT_WATCHTOWER_VISION = 2;
	public static final int GAME_MIN_WATCHTOWER_VISION = 1;
	public static final int GAME_MAX_WATCHTOWER_VISION = 3;

	public static final int GAME_DEFAULT_WATCHTOWER_EXTRA_VISION = 0;
	public static final int GAME_MIN_WATCHTOWER_EXTRA_VISION = 0;
	public static final int GAME_MAX_WATCHTOWER_EXTRA_VISION = 2;

	public static final int GAME_DEFAULT_ALLOWED_CITY_NAMES = 1;
	public static final int GAME_MIN_ALLOWED_CITY_NAMES = 0;
	public static final int GAME_MAX_ALLOWED_CITY_NAMES = 3;

	public static final int GAME_DEFAULT_REVOLUTION_LENGTH = 0;
	public static final int GAME_MIN_REVOLUTION_LENGTH = 0;
	public static final int GAME_MAX_REVOLUTION_LENGTH = 10;

}
