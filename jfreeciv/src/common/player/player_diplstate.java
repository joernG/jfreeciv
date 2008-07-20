package common.player;

public class player_diplstate {
//	struct player_diplstate {
	public  diplstate_type type;	/* this player's disposition towards other */
	/* the following are for "pacts" */
	public int turns_left;		/* until pact (e.g., cease-fire) ends */
	public int has_reason_to_cancel;	/* 0: no, 1: this turn, 2: this or next turn */
	public int contact_turns_left;	/* until contact ends */
//	};

}
