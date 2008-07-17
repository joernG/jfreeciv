package server;

public enum Erfc_status {
	C_IGNORE (-1),                /* never print RFC-style number prefix */
	C_COMMENT (0),                /* for human eyes only */
	C_VERSION (1),                /* version info */
	C_DEBUG (2),                  /* debug info */
	C_LOG_BASE (10),              /* 10, 11, 12 depending on log level */
	C_OK (100),                   /* success of requested operation */
	C_CONNECTION (101),           /* new client */
	C_DISCONNECTED (102),         /* client gone */
	C_REJECTED (103),             /* client rejected */
	C_FAIL (200),                 /* failure of requested operation */
	C_METAERROR (201),            /* failure of meta server */
	C_SYNTAX (300),               /* syntax error or value out of range */
	C_BOUNCE (301),               /* option no longer available */
	C_GENFAIL (400),              /* failure not caused by a requested operation */
	C_WARNING (500),              /* something may be wrong */
	C_READY (999);                 /* waiting for input */

	private final int index;
	Erfc_status(int index) {
		this.index = index;
	}
	public int getIndex() {
		return index;
	}

};
