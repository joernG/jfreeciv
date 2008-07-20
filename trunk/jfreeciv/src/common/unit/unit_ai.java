package common.unit;

public class unit_ai {
//	struct unit_ai {
		  public boolean control; /* 0: not automated    1: automated */
//		  enum ai_unit_task ai_role;
//		  /* The following are all unit ids */
//		  int ferryboat; /* the ferryboat assigned to us */
//		  int passenger; /* the unit assigned to this ferryboat */
//		  int bodyguard; /* the unit bodyguarding us */
//		  int charge; /* the unit this unit is bodyguarding */
//
//		  struct tile *prev_struct, *cur_struct;
//		  struct tile **prev_pos, **cur_pos;

		  int target; /* target we hunt */
		  int hunted; /* if a player is hunting us, set by that player */
//		};

}
