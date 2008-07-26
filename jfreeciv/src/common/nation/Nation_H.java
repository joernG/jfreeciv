package common.nation;

import utility.shared.Shared_H;
import common.Game;

public class Nation_H {
	public static final int MAX_NUM_TECH_GOALS = 10;

	/* Changing this value will break network compatibility. */
	public static final int NO_NATION_SELECTED = (-1);

	public static final int OBSERVER_NATION = (Game.game.nation_count - 2);

	/* 
	 * Purpose of this constant is to catch invalid ruleset and network
	 * data and to allow static allocation of the nation_info packet.
	 */
	public static final int MAX_NUM_LEADERS = Shared_H.MAX_NUM_ITEMS;

	public static final int MAX_NUM_TEAMS = Shared_H.MAX_NUM_PLAYERS;
	public static final int TEAM_NONE = 255;
}
