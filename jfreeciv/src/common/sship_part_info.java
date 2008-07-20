package common;
public class sship_part_info {
	public int x, y;			/* position of tile centre */
	public int required;			/* required for struct connection */
	public sship_part_info(int x, int y, int required) {
		super();
		this.x = x;
		this.y = y;
		this.required = required;
	}

}
