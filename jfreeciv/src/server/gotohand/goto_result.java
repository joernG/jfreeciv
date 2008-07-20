package server.gotohand;

public enum goto_result {
	/* 
	 * The below GOTO result values are ordered by priority, e.g. if unit
	 * fought and run out of movepoints, GR_OUT_OF_MOVEPOINTS should be
	 * returned.  Not that it is of any importance...
	 */
//	goto_result {
	  GR_DIED,               /* pretty obvious that */ 
	  GR_ARRIVED,            /* arrived to the destination */
	  GR_OUT_OF_MOVEPOINTS,  /* no moves left */ 
	  GR_WAITING,            /* waiting due to danger, has moves */
	  GR_FOUGHT,             /* was stopped due to fighting, has moves */
	  GR_FAILED              /* failed for some other reason, has moves */
//	};

}
