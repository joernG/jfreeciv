package common.player;
/* Diplomatic states (how one player views another).
 * (Some diplomatic states are "pacts" (mutual agreements), others aren't.)
 *
 * Adding to or reordering this array will break many things.
 */
public enum diplstate_type {
  DS_NEUTRAL, // = 0,
  DS_WAR,
  DS_CEASEFIRE,
  DS_PEACE,
  DS_ALLIANCE,
  DS_NO_CONTACT,
  DS_TEAM,
  DS_LAST	/* leave this last */
};
