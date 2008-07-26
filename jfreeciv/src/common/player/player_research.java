package common.player;

public class player_research {
//	struct player_research {
	public int bulbs_last_turn;    /* # bulbs researched last turn only */
	public int bulbs_researched;   /* # bulbs reseached for the current tech */    
	public int techs_researched;   /* # techs the player has researched/acquired */
		  /* 
		   * Invention being researched in. Valid values for researching are:
		   *  - any existing tech but not A_NONE or
		   *  - A_FUTURE.
		   * In addition A_NOINFO is allowed at the client for enemies.
		   */
	public   int researching;        
	public   int changed_from;       /* if the player changed techs, which one
					     changed from */
		  public int bulbs_researched_before;  /* if the player changed techs, how
						   many points they had before the
						   change */
//		  struct {
//		    /* One of TECH_UNKNOWN, TECH_KNOWN or TECH_REACHABLE. */
//		    enum tech_state state;
//
//		    /* 
//		     * required_techs, num_required_techs and bulbs_required are
//		     * cached values. Updated from build_required_techs (which is
//		     * called by update_research).
//		     */
//		    tech_vector required_techs;
//		    int num_required_techs, bulbs_required;
//		  } inventions[A_LAST];

		  /*
		   * Cached values. Updated by update_research.
		   */
		  public int num_known_tech_with_flag[];//[TF_LAST];
//		};

}
