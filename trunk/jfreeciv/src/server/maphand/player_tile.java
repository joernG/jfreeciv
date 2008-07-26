package server.maphand;

public class player_tile {
//	struct player_tile {
		  public int terrain;
		  public int special;
		  public short seen;
		  public  short own_seen;
		  /* If you build a city with an unknown square within city radius
		     the square stays unknown. However, we still have to keep count
		     of the seen points, so they are kept in here. When the tile
		     then becomes known they are moved to seen. */
		  public  int pending_seen;
		  public dumb_city city;
		  public short last_updated;
//		};

}
