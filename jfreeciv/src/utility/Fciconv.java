package utility;

public class Fciconv{
	public static final String FC_DEFAULT_DATA_ENCODING = "UTF-8";
//#include "fciconv.h"
//#include "fcintl.h"
//#include "log.h"
//#include "mem.h"
//#include "support.h"
//
static boolean is_init = false;
//static char convert_buffer[4096];
//
//#ifdef HAVE_ICONV
//static final String local_encoding, *data_encoding, *internal_encoding;
//static final String transliteration_string;
//#else
///* Hack to confuse the compiler into working. */
//#  define local_encoding get_local_encoding()
//#  define data_encoding get_local_encoding()
//#  define internal_encoding get_local_encoding()
//#endif

/***************************************************************************
  Must be called during the initialization phase of server and client to
  initialize the character encodings to be used.

  Pass an internal encoding of null to use the local encoding internally.
***************************************************************************/
public static void init_character_encodings(String my_internal_encoding,
			      boolean my_use_transliteration)
{
//#ifdef HAVE_ICONV
//  if (my_use_transliteration) {
//    transliteration_string = "//TRANSLIT";
//  } else {
//    transliteration_string = "";
//  }
//
//  /* Set the data encoding - first check $FREECIV_DATA_ENCODING,
//   * then fall back to the default. */
//  data_encoding = getenv("FREECIV_DATA_ENCODING");
//  if (!data_encoding) {
//    /* Currently the rulesets are in latin1 (ISO-8859-1). */
//    data_encoding = FC_DEFAULT_DATA_ENCODING;
//  }
//
//  /* Set the local encoding - first check $FREECIV_LOCAL_ENCODING,
//   * then ask the system. */
//  local_encoding = getenv("FREECIV_LOCAL_ENCODING");
//  if (!local_encoding) {
//#ifdef HAVE_LIBCHARSET
//    local_encoding = locale_charset();
//#else
//#ifdef HAVE_LANGINFO_CODESET
//    local_encoding = nl_langinfo(CODESET);
//#else
//    local_encoding = "";
//#endif
//#endif
//    if (mystrcasecmp(local_encoding, "ANSI_X3.4-1968") == 0
//	|| mystrcasecmp(local_encoding, "ASCII") == 0
//	|| mystrcasecmp(local_encoding, "US-ASCII") == 0) {
//      /* HACK: use latin1 instead of ascii in typical cases when the
//       * encoding is unconfigured. */
//      local_encoding = "ISO-8859-1";
//    }
//
//    if (mystrcasecmp(local_encoding, "646") == 0) {
//      /* HACK: On Solaris the encoding always comes up as "646" (ascii),
//       * which iconv doesn't understand.  Work around it by using UTF-8
//       * instead. */
//      local_encoding = "UTF-8";
//    }
//  }
//
//  /* Set the internal encoding - first check $FREECIV_INTERNAL_ENCODING,
//   * then check the passed-in default value, then fall back to the local
//   * encoding. */
//  internal_encoding = getenv("FREECIV_INTERNAL_ENCODING");
//  if (!internal_encoding) {
//    internal_encoding = my_internal_encoding;
//
//    if (!internal_encoding) {
//      internal_encoding = local_encoding;
//    }
//  }
//
//#ifdef ENABLE_NLS
//  bind_textdomain_codeset(PACKAGE, internal_encoding);
//#endif
//
//#ifdef DEBUG
//  /* FIXME: Remove this output when this code has stabilized. */
//  fprintf(stderr, "Encodings: Data=%s, Local=%s, Internal=%s\n",
//	     data_encoding, local_encoding, internal_encoding);
//#endif
//
//#else
//#  error No iconv present!
//#endif
//
  is_init = true;
}

///***************************************************************************
//  Return the data encoding (usually UTF-8).
//***************************************************************************/
//final String get_data_encoding()
//{
//  assert(is_init);
//  return data_encoding;
//}
//
///***************************************************************************
//  Return the local encoding (dependent on the system).
//***************************************************************************/
//final String get_local_encoding()
//{
//#ifdef HAVE_ICONV
//  assert(is_init);
//  return local_encoding;
//#else
//#  ifdef HAVE_LIBCHARSET
//  return locale_charset();
//#  else
//#    ifdef HAVE_LANGINFO_CODESET
//  return nl_langinfo(CODESET);
//#    else
//  return "";
//#    endif
//#  endif
//#endif
//}
//
///***************************************************************************
//  Return the internal encoding.  This depends on the server or GUI being
//  used.
//***************************************************************************/
//final String get_internal_encoding()
//{
//  assert(is_init);
//  return internal_encoding;
//}
//
///***************************************************************************
//  Convert the text.  Both 'from' and 'to' must be 8-bit charsets.  The
//  result will be put into the buf buffer unless it is null, in which case it
//  will be allocated on demand.
//***************************************************************************/
//static char *convert_string(final String text,
//			    final String from,
//			    final String to,
//			    char *buf, size_t bufsz)
//{
//#ifdef HAVE_ICONV
//  iconv_t cd = iconv_open(to, from);
//  size_t from_len = text.length() + 1, to_len;
//  boolean alloc = (buf == null);
//
//  assert(is_init && from != null && to != null);
//  assert(text != null);
//
//  if (cd == (iconv_t) (-1)) {
//    util.freelog(Log.LOG_ERROR,
//	    "Could not convert text from %s to %s: %s",
//	    from, to, strerror(errno));
//    /* The best we can do? */
//    if (alloc) {
//      return (text);
//    } else {
//      buf = String.format "%s", text);
//      return buf;
//    }
//  }
//
//  if (alloc) {
//    to_len = from_len;
//  } else {
//    to_len = bufsz;
//  }
//
//  do {
//    size_t flen = from_len, tlen = to_len, res;
//    final String mytext = text;
//    char *myresult;
//
//    if (alloc) {
//      buf = fc_malloc(to_len);
//    }
//
//    myresult = buf;
//
//    /* Since we may do multiple translations, we may need to reset iconv
//     * in between. */
//    iconv(cd, null, null, null, null);
//
//    res = iconv(cd, (ICONV_final String*)&mytext, &flen, &myresult, &tlen);
//    if (res == (size_t) (-1)) {
//      if (errno != E2BIG) {
//	/* Invalid input. */
//	util.freelog(Log.LOG_ERROR, "Invalid string conversion from %s to %s.",
//		from, to);
//	iconv_close(cd);
//	if (alloc) {
//	  free(buf);
//	  return (text); /* The best we can do? */
//	} else {
//	  buf = String.format "%s", text);
//	  return buf;
//	}
//      }
//    } else {
//      /* Success. */
//      iconv_close(cd);
//
//      /* There may be wasted space here, but there's nothing we can do
//       * about it. */
//      return buf;
//    }
//
//    if (alloc) {
//      /* Not enough space; try again. */
//      buf[to_len - 1] = 0;
//      util.freelog(Log.LOG_VERBOSE, "   Result was '%s'.", buf);
//
//      free(buf);
//      to_len *= 2;
//    }
//  } while (alloc);
//
//  return buf;
//#else /* HAVE_ICONV */
//  if (buf) {
//    strncpy(buf, text, bufsz);
//    buf[bufsz - 1] = '\0';
//    return buf;
//  } else {
//    return (text);
//  }
//#endif /* HAVE_ICONV */
//}
//
//#define CONV_FUNC_MALLOC(src, dst)                                          \
//char *src ## _to_ ## dst ## _string_malloc(final String text)                \
//{                                                                           \
//  final String encoding1 = (dst ## _encoding);				    \
//  char encoding[encoding1.length() + transliteration_string.length() + 1];    \
//									    \
//  encoding = util.my_snprintf(				    \
//	      "%s%s", encoding1, transliteration_string);		    \
//  return convert_string(text, (src ## _encoding),			    \
//			(encoding), null, 0);				    \
//}
//
//#define CONV_FUNC_BUFFER(src, dst)                                          \
//char *src ## _to_ ## dst ## _string_buffer(final String text,                \
//					   char *buf, size_t bufsz)         \
//{                                                                           \
//  final String encoding1 = (dst ## _encoding);				    \
//  char encoding[encoding1.length() + transliteration_string.length() + 1];    \
//									    \
//  encoding = util.my_snprintf(				    \
//	      "%s%s", encoding1, transliteration_string);		    \
//  return convert_string(text, (src ## _encoding),			    \
//                        encoding, buf, bufsz);				    \
//}
//
//#define CONV_FUNC_STATIC(src, dst)                                          \
//char *src ## _to_ ## dst ## _string_static(final String text)                \
//{                                                                           \
//  (src ## _to_ ## dst ## _string_buffer)(text,                              \
//					convert_buffer,                     \
//					sizeof(convert_buffer));            \
//  return convert_buffer;                                                    \
//}
//
//CONV_FUNC_MALLOC(data, internal)
//CONV_FUNC_MALLOC(internal, data)
//CONV_FUNC_MALLOC(internal, local)
//CONV_FUNC_MALLOC(local, internal)
//
//CONV_FUNC_BUFFER(local, internal)
//
//static CONV_FUNC_BUFFER(internal, local)
//static CONV_FUNC_STATIC(internal, local)
//
///***************************************************************************
//  Do a fprintf from the internal charset into the local charset.
//***************************************************************************/
//void fc_fprintf(FILE *stream, final String format)
//{
//  va_list ap;
//  char string[4096];
//  final String output;
//  static boolean recursion = false;
//
//  /* The recursion variable is used to prevent a recursive loop.  If
//   * an iconv conversion fails, then util.freelog will be called and an
//   * fc_fprintf will be done.  But below we do another iconv conversion
//   * on the error messages, which is of course likely to fail also. */
//  if (recursion) {
//    return;
//  }
//
//  va_start(ap, format);
//  my_vsnprintf(string, sizeof(string), format, ap);
//  va_end(ap);
//
//  recursion = true;
//  if (is_init) {
//    output = internal_to_local_string_static(string);
//  } else {
//    output = string;
//  }
//  recursion = false;
//
//  fputs(output, stream);
//  fflush(stream);
//}
//
///****************************************************************************
//  Return the length, in *characters*, of the string.  This can be used in
//  place of strlen in some places because it returns the number of characters
//  not the number of bytes (with multi-byte characters in UTF-8, the two
//  may not be the same).
//
//  Use of this function outside of GUI layout code is probably a hack.  For
//  instance the demographics code uses it, but this should instead pass the
//  data directly to the GUI library for formatting.
//****************************************************************************/
//size_t get_internal_string_length(final String text)
//{
//  int text2[(text.length() + 1)]; /* UCS-4 text */
//  int i = 0;
//
//  convert_string(text, internal_encoding, "UCS-4",
//		 (char *)text2, sizeof(text2));
//  for (i = 0; ; i++) {
//    if (text2[i] == 0) {
//      return i;
//    }
//  }
//}
}