package common.player;
import static utility.shared.Shared_H.*;
import utility.shared.Shared_H;

public class player_ai {
	/*
	 * pplayer->ai.barbarian_type uses this enum. Note that the values
	 * have to stay since they are used in savegames.
	 */
//	enum barbarian_type {
	public static final int NOT_A_BARBARIAN = 0;
	public static final int LAND_BARBARIAN = 1;
	public static final int SEA_BARBARIAN = 2;
//	};

//	struct player_ai {
		  public boolean control;

		  /* 
		   * Valid values for tech_goal are:
		   *  - any existing tech but not A_NONE or
		   *  - A_UNSET.
		   */
		  public int tech_goal;
		  public int prev_gold;
		  public int maxbuycost;
		  public int est_upkeep; /* estimated upkeep of buildings in cities */
//		  public int tech_want[]= new int[A_LAST+1];
		  public int handicap;			/* sum of enum handicap_type */
		  public int skill_level;		/* 0-10 value for save/load/display */
		  public int fuzzy;			/* chance in 1000 to mis-decide */
		  public int expand;			/* percentage factor to value new cities */
		  public int science_cost;             /* Cost in bulbs to get new tech, relative
		                                   to non-AI players (100: Equal cost) */
		  public int warmth; /* threat of global warming */
		  public int barbarian_type;

		  public int[] love = new int[Shared_H.MAX_NUM_PLAYERS + Shared_H.MAX_NUM_BARBARIANS];
//		};

}
