package common;

public class Version {
	public static final Object VERSION_STRING = "2.0.9";

	/**********************************************************************
	...
	 ***********************************************************************/
	public static final String freeciv_name_version()
	{
//		static char msgbuf[128];
//
//		#if IS_BETA_VERSION
//		my_snprintf(msgbuf, sizeof (msgbuf), "Freeciv version %s %s",
//				VERSION_STRING, "(beta version)");
//		#else
//			my_snprintf(msgbuf, sizeof (msgbuf), "Freeciv version %s",
//					VERSION_STRING);
//		#endif
//
//		return msgbuf;
		return String.format("Freeciv version %s", VERSION_STRING);
	}

	/**********************************************************************
	...
	 ***********************************************************************/
	public static final String word_version()
	{
//		#if IS_BETA_VERSION
//		return "betatest version ";
//		#else
//		return "version ";
//		#endif
		return "version ";
	}

	/**********************************************************************
	Return the BETA message.
	If returns null, not a beta version.
	 ***********************************************************************/
	public static final String beta_message()
	{
//		#if IS_BETA_VERSION
//		static char msgbuf[128];
//		static final String month[] =
//		{
//			null,
//			N"January",
//			N"February",
//			N"March",
//			N"April",
//			N"May",
//			N"June",
//			N"July",
//			N"August",
//			N"September",
//			N"October",
//			N"November",
//			N"December"
//		};
//		my_snprintf (msgbuf, sizeof (msgbuf),
//				("THIS IS A BETA VERSION\n" +
//						"Freeciv %s will be released in\n" +
//				"%s, at %s"), /* No full stop here since it would be
//				  immediately following a URL, which
//				  would only cause confusion. */
//				NEXT_STABLE_VERSION,
//				_(NEXT_RELEASE_MONTH),
//				WEBSITE_URL);
//		return msgbuf;
//		#else
//			return null;
//		#endif
		return "";
	}

	/***************************************************************************
	Return the Freeciv motto.
	(The motto is common code:
	only one instance of the string in the source;
	only one time gettext needs to translate it. --jjm)
	 ***************************************************************************/
	public static final String freeciv_motto() {
		return "'Cause civilization should be free!";
	}
}