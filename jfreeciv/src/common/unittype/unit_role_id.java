package common.unittype;
//#define F_MAX 64
/* Unit "roles": these are similar to unit flags but differ in that
   they don't represent intrinsic properties or abilities of units,
   but determine which units are used (mainly by the server or AI)
   in various circumstances, or "roles".
   Note that in some cases flags can act as roles, eg, we don't need
   a role for "settlers", because we can just use F_SETTLERS.
   (Now have to consider Eunit_flag_id.F_CITIES too)
   So we make sure flag values and role values are distinct,
   so some functions can use them interchangably.
   See data/default/units.ruleset for documentation of their effects.
*/
//#define L_FIRST F_MAX
public enum unit_role_id {
  L_FIRSTBUILD,//=L_FIRST, /* is built first when city established */
  L_EXPLORER,           /* initial explorer unit */
  L_HUT,                /* can be found in hut */
  L_HUT_TECH,           /* can be found in hut, global tech required */
  L_PARTISAN,           /* is created in Partisan circumstances */
  L_DEFEND_OK,          /* ok on defense (AI) */
  L_DEFEND_GOOD,        /* primary purpose is defense (AI) */
  L_ATTACK_FAST,        /* quick attacking unit (Horse..Armor) (unused)*/
  L_ATTACK_STRONG,      /* powerful attacking unit (Catapult..) (unused) */
  L_FERRYBOAT,	        /* is useful for ferrying (AI) */
  L_BARBARIAN,          /* barbarians unit, land only */
  L_BARBARIAN_TECH,     /* barbarians unit, global tech required */
  L_BARBARIAN_BOAT,     /* barbarian boat */
  L_BARBARIAN_BUILD,    /* what barbarians should build */
  L_BARBARIAN_BUILD_TECH, /* barbarians build when global tech */
  L_BARBARIAN_LEADER,   /* barbarian leader */
  L_BARBARIAN_SEA,      /* sea raider unit */
  L_BARBARIAN_SEA_TECH, /* sea raider unit, global tech required */
  L_CITIES,		/* can found cities */
  L_SETTLERS,		/* can improve terrain */
  L_GAMELOSS,		/* loss results in loss of game */
  L_DIPLOMAT,		/* can do diplomat actions */
  L_LAST
};