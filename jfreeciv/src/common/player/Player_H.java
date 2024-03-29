package common.player;

public class Player_H {
	public static final int  PLAYER_DEFAULT_TAX_RATE =0;
	public static final int  PLAYER_DEFAULT_SCIENCE_RATE =100;
	public static final int  PLAYER_DEFAULT_LUXURY_RATE =0;

	public static final String  ANON_PLAYER_NAME ="noname";
	public static final String  ANON_USER_NAME = "Unassigned";
	public static final String  OBSERVER_NAME	="Observer";

//	enum handicap_type {
		  public static  int H_NONE = 0;         /* No handicaps */
		  public static  int H_DIPLOMAT = 1;     /* Can't build offensive diplomats */
		  public static  int H_AWAY = 2;         /* Away mode */
		  public static  int H_LIMITEDHUTS = 4;  /* Can get only 25 gold and barbs from huts */
		  public static  int H_DEFENSIVE = 8;    /* Build defensive buildings without calculating need */
		  public static  int H_EXPERIMENTAL = 16;/* Enable experimental AI features (for testing) */
		  public static  int H_RATES = 32;       /* Can't set its rates beyond government limits */
		  public static  int H_TARGETS = 64;     /* Can't target anything it doesn't know exists */
		  public static  int H_HUTS = 128;       /* Doesn't know which unseen tiles have huts on them */
		  public static  int H_FOG = 256;        /* Can't see through fog of war */
		  public static  int H_NOPLANES = 512;   /* Doesn't build air units */
		  public static  int H_MAP = 1024;       /* Only knows Maphand.map_is_known tiles */
		  public static  int H_DIPLOMACY = 2048; /* Not very good at diplomacy */
		  public static  int H_REVOLUTION = 4096; /* Cannot skip anarchy */
//		};

}
