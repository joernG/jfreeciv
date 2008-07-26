package common.tech;

import utility.shared.Shared_H;

public class Tech_H {
//	typedef int Tech_Type_id;
	/*
	  Above typedef replaces old "enum tech_type_id"; see comments about
	  Unit_Type_id in unit.h, since mainly apply here too, except don't
	  use Tech_Type_id very widely, and don't use (-1) flag values. (?)
	*/

	public static final int A_NONE = 0;
	public static final int A_FIRST = 1;
	public static final int A_LAST = Shared_H.MAX_NUM_ITEMS;
	public static final int  A_UNSET = (A_LAST-1);
	public static final int  A_FUTURE= (A_LAST-2);
	public static final int  A_NOINFO= (A_LAST-3);
	public static final int A_LAST_REAL = A_NOINFO;

	/*
	   A_NONE is the root tech. All players always know this tech. It is
	   used as a flag in various cases where there is no tech-requirement.

	   A_FIRST is the first real tech id value

	   A_UNSET is a value which indicates that no tech is selected (for
	   research).

	   A_FUTURE is a value which indicates that the player is researching
	   a future tech.

	   A_LAST is a value which is guaranteed to be larger than all
	   actual tech id values.  It is used as a flag value; it can
	   also be used for fixed allocations to ensure able to hold
	   full number of techs.
	*/


}
