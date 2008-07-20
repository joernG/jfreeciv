package utility.shared;
/*** matching prefixes: ***/

public enum m_pre_result {
  M_PRE_EXACT,		/* matches with exact length */
  M_PRE_ONLY,		/* only matching prefix */
  M_PRE_AMBIGUOUS,	/* first of multiple matching prefixes */
  M_PRE_EMPTY,		/* prefix is empty string (no match) */
  M_PRE_LONG,		/* prefix is too long (no match) */
  M_PRE_FAIL,		/* no match at all */
  M_PRE_LAST		/* flag value */
};
