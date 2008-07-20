package common.unittype;

public enum Eunit_flag_id {
	/* Unit "special effects" flags:
	   Note this is now an enumerated type, and not power-of-two integers
	   for bits, though unit_type.flags is still a bitfield, and code
	   which uses unit_flag() without twiddling bits is unchanged.
	   (It is easier to go from i to (1<<i) than the reverse.)
	   See data/default/units.ruleset for documentation of their effects.
	*/
//	enum unit_flag_id { 
	  F_TRADE_ROUTE,//=0,
	  F_HELP_WONDER,
	  F_MISSILE,   
	  F_IGZOC,     
	  F_NONMIL,      
	  F_IGTER,       
	  F_CARRIER,     
	  F_ONEATTACK,   
	  F_PIKEMEN,     
	  F_HORSE,       
	  F_IGWALL,      
	  F_FIELDUNIT,   
	  F_AEGIS,       
	  F_FIGHTER,     
	  F_MARINES,     
	  F_PARTIAL_INVIS,    /* Invisibile except when adjacent (Submarine) */   
	  F_SETTLERS,         /* Does not include ability to found cities */
	  F_DIPLOMAT,    
	  F_TRIREME,          /* Trireme sinking effect */
	  F_NUCLEAR,          /* Nuclear attack effect */
	  F_SPY,              /* Enhanced spy abilities */
	  F_TRANSFORM,        /* Can transform terrain types (Engineers) */
	  F_PARATROOPERS,
	  F_AIRBASE,          /* Can build Airbases */
	  F_CITIES,           /* Can build cities */
	  F_IGTIRED,          /* Ignore tired negative bonus when attacking */
	  F_MISSILE_CARRIER,  /* Like F_CARRIER, but missiles only (Submarine) */
	  F_NO_LAND_ATTACK,   /* Cannot attack vs land squares (Submarine) */
	  F_ADD_TO_CITY,      /* unit can add to city population */
	  F_FANATIC,          /* Only Fundamentalist government can build
				 these units */
	  F_GAMELOSS,         /* Losing this unit means losing the game */
	  F_UNIQUE,           /* A player can only have one unit of this type */
	  F_UNBRIBABLE,       /* Cannot be bribed */
	  F_UNDISBANDABLE,    /* Cannot be disbanded, won't easily go away */
	  F_SUPERSPY,         /* Always wins diplomatic contests */
	  F_NOHOME,           /* Has no homecity */
	  F_NO_VETERAN,       /* Cannot increase veteran level */
	  F_BOMBARDER,        /* Has the ability to bombard */
	  F_CITYBUSTER,       /* Gets double firepower against cities */
	  F_NOBUILD,          /* Unit cannot be built (barb leader etc) */
	  F_LAST
//	};
}
