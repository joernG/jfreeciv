package common.unit;

public class Unit_H {
	public static final int  SINGLE_MOVE =3;
	public static final int  MOVE_COST_RIVER =1;
	public static final int  MOVE_COST_RAIL =0;
	public static final int  MOVE_COST_ROAD= 1;
	public static final int  MOVE_COST_AIR =1;
//	#define COULD_OCCUPY(punit) \
//	  ((is_ground_unit(punit) || is_heli_unit(punit)) && is_military_unit(punit))
	public static boolean COULD_OCCUPY(unit punit) {
		// TODO Auto-generated method stub
		return false;
	}

}
