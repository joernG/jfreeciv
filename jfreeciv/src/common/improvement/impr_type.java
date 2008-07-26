package common.improvement;

public class impr_type {
	/* Type of improvement. (Read from buildings.ruleset file.) */
	// struct impr_type {
	// const char *name; /* Translated string - doesn't need freeing. */
	public String name;
	// String name_orig; /* untranslated */
	// String graphic_str; /* city icon of improv. */
	// String graphic_alt; /* city icon of improv. */
	// Tech_Type_id tech_req; /* Tech_H.A_LAST = never; A_NONE = always */
	// Impr_Type_id bldg_req; /* Improvement.B_LAST = none required */
//	Terrain_type_id *terr_gate; /* list; Terrain_H.T_NONE terminated */
	public int terr_gate[];
	// enum tile_special_type *spec_gate; /* list; S_NO_SPECIAL terminated */
	// enum impr_range equiv_range;
	// Impr_Type_id *equiv_dupl; /* list; Improvement.B_LAST terminated */
	// Impr_Type_id *equiv_repl; /* list; Improvement.B_LAST terminated */
	// Tech_Type_id obsolete_by; /* Tech_H.A_LAST = never obsolete */
	// Impr_Type_id replaced_by; /* Improvement.B_LAST = never replaced */
	public boolean is_wonder;
	public int build_cost; /* Use wrappers to access this. */
	public int upkeep;
	public int sabotage; /* Base chance of diplomat sabotage succeeding. */
	// struct Sprite *sprite; /* icon of the improvement */
	// char *helptext;
	// String soundtag;
	// String soundtag_alt;
	// };
}
