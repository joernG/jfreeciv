package common.connection;

public enum cmdlevel_id {
	/**************************************************************************
	  Command access levels for client-side use; at present, they are only
	  used to control access to server commands typed at the client chatline.
	***************************************************************************/
//	enum cmdlevel_id {    /* access levels for users to issue commands        */
	  ALLOW_NONE,// = 0,     /* user may issue no commands at all                */
	  ALLOW_INFO,         /* user may issue informational commands            */
	  ALLOW_CTRL,         /* user may issue commands that affect Game.game & users */
	  ALLOW_HACK,         /* user may issue *all* commands - dangerous!       */

	  ALLOW_NUM,          /* the number of levels                             */
	  ALLOW_UNRECOGNIZED  /* used as a failure return code                    */
//	};

}
