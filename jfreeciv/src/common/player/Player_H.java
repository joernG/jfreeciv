package common.player;

public class Player_H {
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
		  public static  int H_MAP = 1024;       /* Only knows map_is_known tiles */
		  public static  int H_DIPLOMACY = 2048; /* Not very good at diplomacy */
		  public static  int H_REVOLUTION = 4096; /* Cannot skip anarchy */
//		};

}
