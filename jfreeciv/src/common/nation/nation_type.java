package common.nation;

public class nation_type {
//	struct nation_type {
		  /* Pointer values are allocated on load then freed in free_nations(). */
//		  const char *name; /* Translated string - doesn't need freeing. */
	public String name;
//		  const char *name_plural; /* Translated string - doesn't need freeing. */
	public String name_plural;
//		  char flag_graphic_str[MAX_LEN_NAME];
	public String flag_graphic_str;
//		  char flag_graphic_alt[MAX_LEN_NAME];
	public String flag_graphic_alt;
//		  int  leader_count;
//		  struct leader *leaders;
//		  int city_style;
public city_name city_names[];		/* The default city names. */
//		  struct Sprite *flag_sprite;
//		  char *class;				/* may be empty */
	public String classclass;
//		  char *legend;				/* may be empty */
	public String legend;

		  /* civilwar_nations is a Nation_H.NO_NATION_SELECTED-terminated list of index of
		   * the nations that can fork from this one.  parent_nations is the inverse
		   * of this array.  Server only. */
//		  Nation_Type_id *civilwar_nations;
	public int civilwar_nations[];
//		  Nation_Type_id *parent_nations;
	public int parent_nations[];
//
//		  /* untranslated copies: */
//		  char name_orig[MAX_LEN_NAME];
	public String name_orig;
//		  char name_plural_orig[MAX_LEN_NAME];
	public String name_plural_orig;

		  /* Items given to this nation at game start.  Server only. */
		  public int init_techs[];//[MAX_NUM_TECH_LIST];
		  public int init_buildings[];//[Shared_H.MAX_NUM_BUILDING_LIST];

		  /* Following basically disabled -- Syela */
		  /* Note the client doesn't use/have these. */
//		  struct {
//		    public int tech[MAX_NUM_TECH_GOALS];               /* tech goals     */
//		    public int wonder;                                 /* primary Wonder */
//		    public int government;
//		  } goals;
//		};
}
