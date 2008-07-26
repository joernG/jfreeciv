package common.improvement;

public class impr_type {
	/* Type of improvement. (Read from buildings.ruleset file.) */
	// struct impr_type {
	// const char *name; /* Translated string - doesn't need freeing. */
	public String name;
	// char name_orig[MAX_LEN_NAME]; /* untranslated */
	// char graphic_str[MAX_LEN_NAME]; /* city icon of improv. */
	// char graphic_alt[MAX_LEN_NAME]; /* city icon of improv. */
	// Tech_Type_id tech_req; /* A_LAST = never; A_NONE = always */
	// Impr_Type_id bldg_req; /* B_LAST = none required */
	// Terrain_type_id *terr_gate; /* list; T_NONE terminated */
	// enum tile_special_type *spec_gate; /* list; S_NO_SPECIAL terminated */
	// enum impr_range equiv_range;
	// Impr_Type_id *equiv_dupl; /* list; B_LAST terminated */
	// Impr_Type_id *equiv_repl; /* list; B_LAST terminated */
	// Tech_Type_id obsolete_by; /* A_LAST = never obsolete */
	// Impr_Type_id replaced_by; /* B_LAST = never replaced */
	public boolean is_wonder;
	public int build_cost; /* Use wrappers to access this. */
	public int upkeep;
	public int sabotage; /* Base chance of diplomat sabotage succeeding. */
	// struct Sprite *sprite; /* icon of the improvement */
	// char *helptext;
	// char soundtag[MAX_LEN_NAME];
	// char soundtag_alt[MAX_LEN_NAME];
	// };
}
