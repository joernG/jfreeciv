package utility;

import port.util;

public class Shared{
//#include "astring.h"
//#include "fciconv.h"
//#include "fcintl.h"
//#include "log.h"
//#include "mem.h"
//#include "support.h"
//
//#include "shared.h"
//
//#ifndef PATH_SEPARATOR
//#if defined _WIN32 || defined __WIN32__ || defined __EMX__ || defined __DJGPP__
//  /* Win32, OS/2, DOS */
//# define PATH_SEPARATOR ";"
//#else
//  /* Unix */
//# define PATH_SEPARATOR ":"
//#endif
//#endif
//
///* If no default data path is defined use the default default one */
//#ifndef DEFAULT_DATA_PATH
//#define DEFAULT_DATA_PATH "." PATH_SEPARATOR "data" PATH_SEPARATOR \
//                          "~/.freeciv"
//#endif
//
///* Both of these are stored in the local encoding.  The grouping_sep must
// * be converted to the internal encoding when it's used. */
//static char *grouping = null;
//static char *grouping_sep = null;
//
///***************************************************************
//  Take a string containing multiple lines and create a copy where
//  each line is padded to the length of the longest line and centered.
//  We do not cope with tabs etc.  Note that we're assuming that the
//  last line does _not_ end with a newline.  The caller should
//  free() the result.
//
//  FIXME: This is only used in the Xaw client, and so probably does
//  not belong in common.
//***************************************************************/
//char *create_centered_string(final String s)
//{
//  /* Points to the part of the source that we're looking at. */
//  final String cp;
//
//  /* Points to the beginning of the line in the source that we're
//   * looking at. */
//  final String cp0;
//
//  /* Points to the result. */
//  char *r;
//
//  /* Points to the part of the result that we're filling in right
//     now. */
//  char *rn;
//
//  int i;
//
//  int maxlen = 0;
//  int curlen = 0;
//  int nlines = 1;
//
//  for(cp=s; *cp != '\0'; cp++) {
//    if(*cp!='\n')
//      curlen++;
//    else {
//      if(maxlen<curlen)
//	maxlen=curlen;
//      curlen=0;
//      nlines++;
//    }
//  }
//  if(maxlen<curlen)
//    maxlen=curlen;
//  
//  r=rn=fc_malloc(nlines*(maxlen+1));
//  
//  curlen=0;
//  for(cp0=cp=s; *cp != '\0'; cp++) {
//    if(*cp!='\n')
//      curlen++;
//    else {
//      for(i=0; i<(maxlen-curlen)/2; i++)
//	*rn++=' ';
//      memcpy(rn, cp0, curlen);
//      rn+=curlen;
//      *rn++='\n';
//      curlen=0;
//      cp0=cp+1;
//    }
//  }
//  for(i=0; i<(maxlen-curlen)/2; i++)
//    *rn++=' ';
//  strcpy(rn, cp0);
//
//  return r;
//}
//
///**************************************************************************
//  return a char * to the parameter of the option or null.
//  *i can be increased to get next string in the array argv[].
//  It is an error for the option to exist but be an empty string.
//  This doesn't use util.freelog() because it is used before logging is set up.
//**************************************************************************/
//char *get_option(final String option_name, char **argv, int *i, int argc)
//{
//  int len = option_name.length();
//
//  if (strcmp(option_name, argv[*i]) == 0 ||
//      (strncmp(option_name, argv[*i], len) == 0 && argv[*i][len] == '=') ||
//      strncmp(option_name + 1, argv[*i], 2) == 0) {
//    char *opt = argv[*i] + (argv[*i][1] != '-' ? 0 : len);
//
//    if (*opt == '=') {
//      opt++;
//    } else {
//      if (*i < argc - 1) {
//	(*i)++;
//	opt = argv[*i];
//	if (opt.length()==0) {
//	  fc_fprintf(stderr, ("Empty argument for \"%s\".\n"), option_name);
//	  exit(EXIT_FAILURE);
//	}
//      }	else {
//	fc_fprintf(stderr, ("Missing argument for \"%s\".\n"), option_name);
//	exit(EXIT_FAILURE);
//      }
//    }
//
//    return opt;
//  }
//
//  return null;
//}
//
///***************************************************************
//...
//***************************************************************/
//boolean is_option(final String option_name,char *option)
//{
//  return (option_name.equals(option) ||
//	  strncmp(option_name + 1, option, 2) == 0);
//}
//
///***************************************************************
//  Like strcspn but also handles quotes, i.e. *reject chars are 
//  ignored if they are inside single or double quotes.
//***************************************************************/
//static size_t my_strcspn(final String s, final String reject)
//{
//  boolean in_single_quotes = false, in_double_quotes = false;
//  size_t i, len = s.length();
//
//  for (i = 0; i < len; i++) {
//    if (s[i] == '"' && !in_single_quotes) {
//      in_double_quotes = !in_double_quotes;
//    } else if (s[i] == '\'' && !in_double_quotes) {
//      in_single_quotes = !in_single_quotes;
//    }
//
//    if (in_single_quotes || in_double_quotes) {
//      continue;
//    }
//
//    if (strchr(reject, s[i])) {
//      break;
//    }
//  }
//
//  return i;
//}
//
///***************************************************************
// Splits the string into tokens. The individual tokens are
// returned. The delimiterset can freely be chosen.
//
// i.e. "34 abc 54 87" with a delimiterset of " " will yield 
//      tokens={"34", "abc", "54", "87"}
// 
// Part of the input string can be quoted (single or double) to embedded
// delimiter into tokens. For example,
//   command 'a name' hard "1,2,3,4,5" 99
//   create 'Mack "The Knife"'
// will yield 5 and 2 tokens respectively using the delimiterset " ,".
//
// Tokens which aren't used aren't modified (and memory is not
// allocated). If the string would yield more tokens only the first
// num_tokens are extracted.
//
// The user has the responsiblity to free the memory allocated by
// **tokens.
//***************************************************************/
//int get_tokens(final String str, char **tokens, size_t num_tokens,
//	       final String delimiterset)
//{
//  int token = 0;
//
//  assert(str != null);
//
//  for(;;) {
//    size_t len, padlength = 0;
//
//    /* skip leading delimiters */
//    str += strspn(str, delimiterset);
//
//    if (*str == '\0') {
//      break;
//    }
//
//    len = my_strcspn(str, delimiterset);
//
//    if (token >= num_tokens) {
//      break;
//    }
//
//    /* strip start/end quotes if they exist */
//    if (len >= 2) {
//      if ((str[0] == '"' && str[len - 1] == '"')
//	  || (str[0] == '\'' && str[len - 1] == '\'')) {
//	len -= 2;
//	padlength = 1;		/* to set the string past the end quote */
//	str++;
//      }
//    }
//  
//    tokens[token] = fc_malloc(len + 1);
//    () mystrlcpy(tokens[token], str, len + 1);	/* adds the '\0' */
//
//    token++;
//
//    str += len + padlength;
//  }
//
//  return token;
//}
//
///***************************************************************
//  Returns a statically allocated string containing a nicely-formatted
//  version of the given number according to the user's locale.  (Only
//  works for numbers >= zero.)  The number is given in scientific notation
//  as mantissa * 10^exponent.
//***************************************************************/
//final String big_int_to_text(unsigned int mantissa, unsigned int exponent)
//{
//  static char buf[64]; /* Note that we'll be filling this in right to left. */
//  char *grp = grouping;
//  char *ptr;
//  unsigned int cnt = 0;
//  char sep[64];
//  size_t seplen;
//
//  /* We have to convert the encoding here (rather than when the locale
//   * is initialized) because it can't be done before the charsets are
//   * initialized. */
//  local_to_internal_string_buffer(grouping_sep, sep, sizeof(sep));
//  seplen = sep.length();
//
//#if 0 /* Not needed while the values are unsigned. */
//  assert(mantissa >= 0);
//  assert(exponent >= 0);
//#endif
//
//  if (mantissa == 0) {
//    return "0";
//  }
//
//  /* We fill the string in backwards, starting from the right.  So the first
//   * thing we do is terminate it. */
//  ptr = &buf[sizeof(buf)];
//  *(--ptr) = '\0';
//
//  while (mantissa != 0 && exponent >= 0) {
//    int dig;
//
//    if (ptr <= buf + seplen) {
//      /* Avoid a buffer overflow. */
//      assert(ptr > buf + seplen);
//      return ptr;
//    }
//
//    /* Add on another character. */
//    if (exponent > 0) {
//      dig = 0;
//      exponent--;
//    } else {
//      dig = mantissa % 10;
//      mantissa /= 10;
//    }
//    *(--ptr) = '0' + dig;
//
//    cnt++;
//    if (mantissa != 0 && cnt == *grp) {
//      /* Reached count of digits in group: insert separator and reset count. */
//      cnt = 0;
//      if (*grp == CHAR_MAX) {
//	/* This test is unlikely to be necessary since we would need at
//	   least 421-bit ints to break the 127 digit barrier, but why not. */
//	break;
//      }
//      ptr -= seplen;
//      assert(ptr >= buf);
//      memcpy(ptr, sep, seplen);
//      if (*(grp + 1) != 0) {
//	/* Zero means to repeat the present group-size indefinitely. */
//        grp++;
//      }
//    }
//  }
//
//  return ptr;
//}
//
//
///****************************************************************************
//  Return a prettily formatted string containing the given number.
//****************************************************************************/
//final String int_to_text(unsigned int number)
//{
//  return big_int_to_text(number, 0);
//}
//
///****************************************************************************
//  Check whether or not the given char is a valid ascii character.  The
//  character can be in any charset so long as it is a superset of ascii.
//****************************************************************************/
//static boolean is_ascii(char ch)
//{
//  /* this works with both signed and unsigned char's. */
//  return ch >= ' ' && ch <= '~';
//}
//
///***************************************************************
//  This is used in sundry places to make sure that names of cities,
//  players etc. do not contain yucky characters of various sorts.
//  Returns true iff the name is acceptable.
//  FIXME:  Not internationalised.
//***************************************************************/
//boolean is_ascii_name(final String name)
//{
//  final char illegal_chars[] = {'|', '%', '"', ',', '*', '<', '>', '\0'};
//  int i, j;
//
//  /* must not be null or empty */
//  if (!name || *name == '\0') {
//    return false; 
//  }
//
//  /* must begin and end with some non-space character */
//  if ((*name == ' ') || (*(strchr(name, '\0') - 1) == ' ')) {
//    return false;
//  }
//
//  /* must be composed entirely of printable ascii characters,
//   * and no illegal characters which can break ranking scripts. */
//  for (i = 0; name[i]; i++) {
//    if (!is_ascii(name[i])) {
//      return false;
//    }
//    for (j = 0; illegal_chars[j]; j++) {
//      if (name[i] == illegal_chars[j]) {
//	return false;
//      }
//    }
//  }
//
//  /* otherwise, it's okay... */
//  return true;
//}

/***************************************************************
  Produce a statically allocated textual representation of the given
  year.
***************************************************************/
public static final String textyear(int year)
{
//  static char y[32];
	String y= null;
  if (year<0) 
    y = util.my_snprintf("%d BC", -year);
  else
    y = util.my_snprintf("%d AD", year);
  return y;
}

///**************************************************************************
//  Compares two strings, in the collating order of the current locale,
//  given pointers to the two strings (i.e., given "char *"s).
//  Case-sensitive.  Designed to be called from qsort().
//**************************************************************************/
//int compare_strings(final void *first, final void *second)
//{
//#if defined(ENABLE_NLS) && defined(HAVE_STRCOLL)
//  return strcoll((final String)first, (final String)second);
//#else
//  return strcmp((final String)first, (final String)second);
//#endif
//}
//
///**************************************************************************
//  Compares two strings, in the collating order of the current locale,
//  given pointers to the two string pointers (i.e., given "char **"s).
//  Case-sensitive.  Designed to be called from qsort().
//**************************************************************************/
//int compare_strings_ptrs(final void *first, final void *second)
//{
//#if defined(ENABLE_NLS) && defined(HAVE_STRCOLL)
//  return strcoll(*((final String*)first), *((final String*)second));
//#else
//  return strcmp(*((final String*)first), *((final String*)second));
//#endif
//}
//
///***************************************************************************
//  Returns 's' incremented to first non-space character.
//***************************************************************************/
//char *skip_leading_spaces(char *s)
//{
//  assert(s!=null);
//  while(*s != '\0' && my_isspace(*s)) {
//    s++;
//  }
//  return s;
//}
//
///***************************************************************************
//  Removes leading spaces in string pointed to by 's'.
//  Note 's' must point to writeable memory!
//***************************************************************************/
//static void remove_leading_spaces(char *s)
//{
//  char *t;
//  
//  assert(s!=null);
//  t = skip_leading_spaces(s);
//  if (t != s) {
//    while (*t != '\0') {
//      *s++ = *t++;
//    }
//    *s = '\0';
//  }
//}
//
///***************************************************************************
//  Terminates string pointed to by 's' to remove traling spaces;
//  Note 's' must point to writeable memory!
//***************************************************************************/
//static void remove_trailing_spaces(char *s)
//{
//  char *t;
//  size_t len;
//  
//  assert(s!=null);
//  len = s.length();
//  if (len > 0) {
//    t = s + len -1;
//    while(my_isspace(*t)) {
//      *t = '\0';
//      if (t == s) {
//	break;
//      }
//      t--;
//    }
//  }
//}
//
///***************************************************************************
//  Removes leading and trailing spaces in string pointed to by 's'.
//  Note 's' must point to writeable memory!
//***************************************************************************/
//void remove_leading_trailing_spaces(char *s)
//{
//  remove_leading_spaces(s);
//  remove_trailing_spaces(s);
//}
//
///***************************************************************************
//  As remove_trailing_spaces(), for specified char.
//***************************************************************************/
//static void remove_trailing_char(char *s, char trailing)
//{
//  char *t;
//  
//  assert(s!=null);
//  t = s + s.length() -1;
//  while(t>=s && (*t) == trailing) {
//    *t = '\0';
//    t--;
//  }
//}
//
///***************************************************************************
//  Change spaces in s into newlines, so as to keep lines length len
//  or shorter.  That is, modifies s.
//  Returns number of lines in modified s.
//***************************************************************************/
//int wordwrap_string(char *s, int len)
//{
//  int num_lines = 0;
//  int slen = s.length();
//  
//  /* At top of this loop, s points to the rest of string,
//   * either at start or after inserted newline: */
// top:
//  if (s && *s != '\0' && slen > len) {
//    char *c;
//
//    num_lines++;
//    
//    /* check if there is already a newline: */
//    for(c=s; c<s+len; c++) {
//      if (*c == '\n') {
//	slen -= c+1 - s;
//	s = c+1;
//	goto top;
//      }
//    }
//    
//    /* find space and break: */
//    for(c=s+len; c>s; c--) {
//      if (my_isspace(*c)) {
//	*c = '\n';
//	slen -= c+1 - s;
//	s = c+1;
//	goto top;
//      }
//    }
//
//    /* couldn't find a good break; settle for a bad one... */
//    for (c = s + len + 1; *c != '\0'; c++) {
//      if (my_isspace(*c)) {
//	*c = '\n';
//	slen -= c+1 - s;
//	s = c+1;
//	goto top;
//      }
//    }
//  }
//  return num_lines;
//}
//
///***************************************************************************
//  Returns pointer to '\0' at end of string 'str', and decrements
//  *nleft by the length of 'str'.  This is intended to be useful to
//  allow strcat-ing without traversing the whole string each time,
//  while still keeping track of the buffer length.
//  Eg:
//     char buf[128];
//     int n = sizeof(buf);
//     char *p = buf;
//
//     p = String.format "foo%p", p);
//     p = end_of_strn(p, &n);
//     mystrlcpy(p, "yyy", n);
//***************************************************************************/
//char *end_of_strn(char *str, int *nleft)
//{
//  int len = str.length();
//  *nleft -= len;
//  assert((*nleft)>0);		/* space for the terminating nul */
//  return str + len;
//}
//

//  Check the length of the given string.  If the string is too long,
//  log errmsg, which should be a string in printf-format taking up to
//  two arguments: the string and the length.
//**********************************************************************/ 
//boolean check_strlen(final String str, size_t len, final String errmsg)
//{
//  if (str.length() >= len) {
//    util.freelog(Log.LOG_ERROR, errmsg, str, len);
//    assert(0!=1);
//    return true;
//  }
//  return false;
//}
//

//  Call check_strlen() on str and then strlcpy() it into buffer.
//**********************************************************************/
//size_t loud_strlcpy(char *buffer, final String str, size_t len,
//		   final String errmsg)
//{
//  () check_strlen(str, len, errmsg);
//  return mystrlcpy(buffer, str, len);
//}
//

// cat_snprintf is like a combination of my_snprintf and mystrlcat;
// it does snprintf to the end of an existing string.
// 
// Like mystrlcat, n is the total length available for str, including
// existing contents and trailing nul.  If there is no extra room
// available in str, does not change the string. 
//
// Also like mystrlcat, returns the final length that str would have
// had without truncation.  I.e., if return is >= n, truncation occurred.
//**********************************************************************/ 
//int cat_snprintf(char *str, size_t n, final String format)
//{
//  size_t len;
//  int ret;
//  va_list ap;
//
//  assert(format != null);
//  assert(str != null);
//  assert(n>0);
//  
//  len = str.length();
//  assert(len < n);
//  
//  va_start(ap, format);
//  ret = my_vsnprintf(str+len, n-len, format, ap);
//  va_end(ap);
//  return (int) (ret + len);
//}
//
///***************************************************************************
//  Exit because of a fatal error after printing a message about it.  This
//  should only be called for code errors - user errors (like not being able
//  to find a tileset) should just exit rather than dumping core.
//***************************************************************************/
//void real_util.die(final String file, int line, final String format)
//{
//  va_list ap;
//
//  util.freelog(LOG_FATAL, "Detected fatal error in %s line %d:", file, line);
//  va_start(ap, format);
//  vreal_util.freelog(LOG_FATAL, format, ap);
//  va_end(ap);
//
//  assert(false);
//
//  exit(EXIT_FAILURE);
//}
//
///***************************************************************************
//  Returns string which gives users home dir, as specified by $HOME.
//  Gets value once, and then caches result.
//  If $HOME is not set, give a log message and returns null.
//  Note the caller should not mess with the returned string.
//***************************************************************************/
//char *user_home_dir()
//{
//#ifdef AMIGA
//  return "PROGDIR:";
//#else
//  static boolean init = false;
//  static char *home_dir = null;
//  
//  if (!init) {
//    char *env = getenv("HOME");
//    if (env) {
//      home_dir = (env);	        /* never free()d */
//      util.freelog(Log.LOG_VERBOSE, "HOME is %s", home_dir);
//    } else {
//#ifdef WIN32_NATIVE
//      home_dir=fc_malloc(PATH_MAX);
//      if (!getcwd(home_dir,PATH_MAX)) {
//	free(home_dir);
//	home_dir=null;
//	util.freelog(Log.LOG_ERROR, "Could not find home directory (HOME is not set)");
//      }
//#else
//      util.freelog(Log.LOG_ERROR, "Could not find home directory (HOME is not set)");
//      home_dir = null;
//#endif
//    }
//    init = true;
//  }
//  return home_dir;
//#endif
//}
//
///***************************************************************************
//  Returns string which gives user's username, as specified by $USER or
//  as given in password file for this user's uid, or a made up name if
//  we can't get either of the above. 
//  Gets value once, and then caches result.
//  Note the caller should not mess with returned string.
//***************************************************************************/
//final String user_username()
//{
//  static String username;
//
//  /* This function uses a number of different methods to try to find a
//   * username.  This username then has to be truncated to MAX_LEN_NAME
//   * characters (including terminator) and checked for sanity.  Note that
//   * truncating a sane name can leave you with an insane name under some
//   * charsets. */
//
//  if (username[0] != '\0') {
//    /* Username is already known; just return it. */
//    return username;
//  }
//
//  /* If the environment variable $USER is present and sane, use it. */
//  {
//    char *env = getenv("USER");
//
//    if (env) {
//      username = env;
//      if (util.isLetter(username)) {
//	util.freelog(Log.LOG_VERBOSE, "USER username is %s", username);
//	return username;
//      }
//    }
//  }
//
//#ifdef HAVE_GETPWUID
//  /* Otherwise if getpwuid() is available we can use it to find the true
//   * username. */
//  {
//    passwd pwent = getpwuid(getuid());
//
//    if (pwent) {
//      username = pwent.pw_name;
//      if (util.isLetter(username)) {
//	util.freelog(Log.LOG_VERBOSE, "getpwuid username is %s", username);
//	return username;
//      }
//    }
//  }
//#endif
//
//#ifdef WIN32_NATIVE
//  /* On win32 the GetUserName function will give us the login name. */
//  {
//    char name[UNLEN + 1];
//    DWORD length = sizeof(name);
//
//    if (GetUserName(name, &length)) {
//      username = name;
//      if (util.isLetter(username)) {
//	util.freelog(Log.LOG_VERBOSE, "GetUserName username is %s", username);
//	return username;
//      }
//    }
//  }
//#endif
//
//#ifdef ALWAYS_ROOT
//  username = String.format( "name");
//#else
//  username = String.format "name%d", (int)getuid());
//#endif
//  util.freelog(Log.LOG_VERBOSE, "fake username is %s", username);
//  assert(util.isLetter(username));
//  return username;
//}
//
///***************************************************************************
//  Returns a list of data directory paths, in the order in which they should
//  be searched.  These paths are specified internally or may be set as the
//  environment variable $FREECIV_PATH (a separated list of directories,
//  where the separator itself is specified internally, platform-dependent).
//  '~' at the start of a component (provided followed by '/' or '\0') is
//  expanded as $HOME.
//
//  The returned value is a static null-terminated list of strings.
//
//  num_dirs, if not null, will be set to the number of entries in the list.
//***************************************************************************/
//static final String*get_data_dirs(int *num_dirs)
//{
//  final String path;
//  char *path2, *tok;
//  static int num = 0;
//  static final String*dirs = null;
//
//  /* The first time this function is called it will search and
//   * allocate the directory listing.  Subsequently we will already
//   * know the list and can just return it. */
//  if (dirs) {
//    if (num_dirs) {
//      *num_dirs = num;
//    }
//    return dirs;
//  }
//
//  path = getenv("FREECIV_PATH");
//  if (!path) {
//    path = DEFAULT_DATA_PATH;
//  } else if (*path == '\0') {
//    util.freelog(Log.LOG_ERROR, ("FREECIV_PATH is set but empty; " +
//			 "using default path instead."));
//    path = DEFAULT_DATA_PATH;
//  }
//  assert(path != null);
//  
//  path2 = (path);	/* something we can strtok */
//    
//  tok = strtok(path2, PATH_SEPARATOR);
//  do {
//    int i;			/* tok.length(), or -1 as flag */
//
//    tok = skip_leading_spaces(tok);
//    remove_trailing_spaces(tok);
//    if (strcmp(tok, "/") != 0) {
//      remove_trailing_char(tok, '/');
//    }
//      
//    i = tok.length();
//    if (tok[0] == '~') {
//      if (i > 1 && tok[1] != '/') {
//	util.freelog(Log.LOG_ERROR, "For \"%s\" in data path cannot expand '~'" +
//		" except as '~/'; ignoring", tok);
//	i = 0;   /* skip this one */
//      } else {
//	char *home = user_home_dir();
//
//	if (!home) {
//	  util.freelog(Log.LOG_VERBOSE,
//		  "No HOME, skipping data path component %s", tok);
//	  i = 0;
//	} else {
//	  int len = home.length() + i;	   /* +1 -1 */
//	  char *tmp = fc_malloc(len);
//
//	  tmp = String.format "%s%s", home, tok + 1);
//	  tok = tmp;
//	  i = -1;		/* flag to free tok below */
//	}
//      }
//    }
//
//    if (i != 0) {
//      /* We could check whether the directory exists and
//       * is readable etc?  Don't currently. */
//      num++;
//      dirs = fc_realloc(dirs, num * sizeof(char*));
//      dirs[num - 1] = (tok);
//      util.freelog(Log.LOG_VERBOSE, "Data path component (%d): %s", num - 1, tok);
//      if (i == -1) {
//	free(tok);
//	tok = null;
//      }
//    }
//
//    tok = strtok(null, PATH_SEPARATOR);
//  } while(tok);
//
//  /* null-terminate the list. */
//  dirs = fc_realloc(dirs, (num + 1) * sizeof(char*));
//  dirs[num] = null;
//
//  free(path2);
//  
//  if (num_dirs) {
//    *num_dirs = num;
//  }
//  return dirs;
//}
//
///***************************************************************************
//  Returns a null-terminated list of filenames in the data directories
//  matching the given suffix.
//
//  The list is allocated when the function is called; it should either
//  be stored permanently or de-allocated (by free'ing each element and
//  the whole list).
//
//  The suffixes are removed from the filenames before the list is
//  returned.
//***************************************************************************/
//final String*datafilelist(final char* suffix)
//{
//  final String*dirs = get_data_dirs(null);
//  char **file_list = null;
//  int num_matches = 0;
//  int list_size = 0;
//  int dir_num, i, j;
//  size_t suffix_len = suffix.length();
//
//  assert(!strchr(suffix, '/'));
//
//  /* First assemble a full list of names. */
//  for (dir_num = 0; dirs[dir_num]; dir_num++) {
//    DIR* dir;
//    dirent entry;
//
//    /* Open the directory for reading. */
//    dir = opendir(dirs[dir_num]);
//    if (!dir) {
//      if (errno == ENOENT) {
//	util.freelog(Log.LOG_VERBOSE, "Skipping non-existing data directory %s.",
//		dirs[dir_num]);
//      } else {
//	util.freelog(Log.LOG_ERROR, "Could not read data directory %s: %s.",
//		dirs[dir_num], mystrerror());
//      }
//      continue;
//    }
//
//    /* Scan all entries in the directory. */
//    while ((entry = readdir(dir))) {
//      size_t len = strlen(entry.d_name);
//
//      /* Make sure the file name matches. */
//      if (len > suffix_len
//	  && strcmp(suffix, entry.d_name + len - suffix_len) == 0) {
//	/* Strdup the entry so we can safely write to it. */
//	char *match = (entry.d_name);
//
//	/* Make sure the list is big enough; grow exponentially to keep
//	   finalant ammortized overhead. */
//	if (num_matches >= list_size) {
//	  list_size = list_size > 0 ? list_size * 2 : 10;
//	  file_list = fc_realloc(file_list, list_size * sizeof(*file_list));
//	}
//
//	/* Clip the suffix. */
//	match[len - suffix_len] = '\0';
//
//	file_list[num_matches++] = (match);
//
//	free(match);
//      }
//    }
//
//    closedir(dir);
//  }
//
//  /* Sort the list. */
//  qsort(file_list, num_matches, sizeof(*file_list), compare_strings_ptrs);
//
//  /* Remove duplicates (easy since it's sorted). */
//  i = j = 0;
//  while (j < num_matches) {
//    char *this = file_list[j];
//
//    for (j++; j < num_matches && strcmp(this, file_list[j]) == 0; j++) {
//      free(file_list[j]);
//    }
//
//    file_list[i] = this;
//
//    i++;
//  }
//  num_matches = i;
//
//  /* null-terminate the whole thing. */
//  file_list = fc_realloc(file_list, (num_matches + 1) * sizeof(*file_list));
//  file_list[num_matches] = null;
//
//  return (final String*)file_list;
//}
//
///***************************************************************************
//  Returns a filename to access the specified file from a data
//  directory by searching all data directories (as specified by
//  get_data_dirs) for the file.
//
//  If the specified 'filename' is null, the returned string contains
//  the effective data path.  (But this should probably only be used for
//  debug output.)
//  
//  Returns null if the specified filename cannot be found in any of the
//  data directories.  (A file is considered "found" if it can be
//  read-opened.)  The returned pointer points to static memory, so this
//  function can only supply one filename at a time.
//***************************************************************************/
//char *datafilename(final String filename)
//{
//  int num_dirs, i;
//  final String*dirs = get_data_dirs(&num_dirs);
//  static struct astring realfile = ASTRING_INIT;
//
//  if (!filename) {
//    size_t len = 1;		/* in case num_dirs==0 */
//    size_t seplen = PATH_SEPARATOR.length();
//
//    for (i = 0; i < num_dirs; i++) {
//      len += strlen(dirs[i]) + MAX(1, seplen);	/* separator or '\0' */
//    }
//    astr_minsize(&realfile, len);
//    realfile.str[0] = '\0';
//
//    for (i = 0; i < num_dirs; i++) {
//      () mystrlcat(realfile.str, dirs[i], len);
//      if (i < num_dirs) {
//	() mystrlcat(realfile.str, PATH_SEPARATOR, len);
//      }
//    }
//    return realfile.str;
//  }
//  
//  for (i = 0; i < num_dirs; i++) {
//    struct stat buf;		/* see if we can open the file or directory */
//    size_t len = strlen(dirs[i]) + filename.length() + 2;
//    
//    astr_minsize(&realfile, len);
//    realfile.str = String.format "%s/%s", dirs[i], filename);
//    if (stat(realfile.str, &buf) == 0) {
//      return realfile.str;
//    }
//  }
//
//  util.freelog(Log.LOG_VERBOSE, "Could not find readable file \"%s\" in data path.",
//	  filename);
//
//  return null;
//}
//
///**************************************************************************
//  Compare modification times.
//**************************************************************************/
//static int compare_file_mtime_ptrs(final void *a, final void *b)
//{
//  datafile  final *ppa = a;
//  datafile  final *ppb = b;
//
//  return ((*ppa).mtime < (*ppb).mtime);
//}
//
///**************************************************************************
//  Compare names.
//**************************************************************************/
//static int compare_file_name_ptrs(final void *a, final void *b)
//{
//  datafile  final *ppa = a;
//  datafile  final *ppb = b;
//
//  return compare_strings((*ppa).name, (*ppb).name);
//}
//
///**************************************************************************
//  Search for filenames with the "infix" substring in the "subpath"
//  subdirectory of the data path.
//  "nodups" removes duplicate names.
//  The returned list will be sorted by name first and modification time
//  second. Returned "name"s will be truncated starting at the "infix"
//  substring. The returned list must be freed.
//**************************************************************************/
//Speclists<datafile> datafilelist_infix(final String subpath,
//    final String infix, boolean nodups)
//{
//  final String*dirs = get_data_dirs(null);
//  int num_matches = 0;
//  int dir_num;
//  Speclists<datafile> res;
//
//  datafile_list_init(&res);
//
//  /* First assemble a full list of names. */
//  for (dir_num = 0; dirs[dir_num]; dir_num++) {
//    char path[PATH_MAX];
//    DIR *dir;
//    dirent entry;
//
//    if (subpath) {
//      path = util.my_snprintf( "%s/%s", dirs[dir_num], subpath);
//    } else {
//      path = String.format( dirs[dir_num]);
//    }
//
//    /* Open the directory for reading. */
//    dir = opendir(path);
//    if (!dir) {
//      continue;
//    }
//
//    /* Scan all entries in the directory. */
//    while ((entry = readdir(dir))) {
//      datafile file;
//      char *ptr;
//      /* Strdup the entry so we can safely write to it. */
//      char *filename = (entry.d_name);
//
//      /* Make sure the file name matches. */
//      if ((ptr = strstr(filename, infix))) {
//	struct stat buf;
//	char *fullname;
//	size_t len = path.length() + filename.length() + 2;
//
//	fullname = fc_malloc(len);
//	fullname = String.format "%s/%s", path, filename);
//
//	if (stat(fullname, &buf) == 0) {
//	  file = fc_malloc(sizeof(*file));
//
//	  /* Clip the suffix. */
//	  *ptr = '\0';
//
//	  file.name = (filename);
//	  file.fullname = (fullname);
//	  file.mtime = buf.st_mtime;
//
//	  datafile_list_insert_back(&res, file);
//	  num_matches++;
//	}
//
//	free(fullname);
//      }
//
//      free(filename);
//    }
//
//    closedir(dir);
//  }
//
//  /* Sort the list by name. */
//  datafile_list_sort(&res, compare_file_name_ptrs);
//
//  if (nodups) {
//    char *name = "";
//
//    for (datafile pfile : res.data) {
//      if (compare_strings(name, pfile.name) != 0) {
//	name = pfile.name;
//      } else {
//	free(pfile.name);
//	free(pfile.fullname);
//	datafile_list_unlink(&res, pfile);
//      }
//    } }
//  }
//
//  /* Sort the list by last modification time. */
//  datafile_list_sort(&res, compare_file_mtime_ptrs);
//
//  return res;
//}
//
//
//
///***************************************************************************
//  As datafilename(), above, except util.die with an appropriate log
//  message if we can't find the file in the datapath.
//***************************************************************************/
//char *datafilename_required(final String filename)
//{
//  char *dname;
//  
//  assert(filename!=null);
//  dname = datafilename(filename);
//
//  if (dname) {
//    return dname;
//  } else {
//    util.freelog(Log.LOG_ERROR, ("The data path may be set via" +
//			 " the environment variable FREECIV_PATH."));
//    util.freelog(Log.LOG_ERROR, ("Current data path is: \"%s\""), datafilename(null));
//    util.freelog(LOG_FATAL,
//		 ("The \"%s\" file is required ... aborting!"), filename);
//    exit(EXIT_FAILURE);
//  }
//}

/***************************************************************************
  Setup for Native Language Support, if configured to use it.
  (Call this only once, or it may leak memory.)
***************************************************************************/
public static void init_nls()
{
  /* 
   * Setup the cached locale numeric formatting information. Defaults
   * are as appropriate for the US.
   */
//  grouping = ("\3");
//  grouping_sep = (",");
//
//#ifdef ENABLE_NLS
//#ifdef WIN32_NATIVE
//  /* set LANG by hand if it is not set */
//  if (!getenv("LANG")) {
//    char *langname = null;
//
//    switch (PRIMARYLANGID(LANGIDFROMLCID(GetUserDefaultLCID()))) {
//    case LANG_SPANISH:
//      langname = "es";
//      break;
//    case LANG_GERMAN:
//      langname = "de";
//      break;
//    case LANG_ENGLISH:
//      langname = "en";
//      break;
//    case LANG_FRENCH:
//      langname = "fr";
//      break;
//    case LANG_DUTCH:
//      langname = "nl";
//      break;
//    case LANG_POLISH:
//      langname = "pl";
//      break;
//    case LANG_HUNGARIAN:
//      langname = "hu";
//      break;
//    case LANG_NORWEGIAN:
//      langname = "no";
//      break;
//    case LANG_JAPANESE:
//      langname = "ja";
//      break;
//    case LANG_PORTUGUESE:
//      langname = "pt";
//      break;
//    case LANG_ROMANIAN:
//      langname = "ro";
//      break;
//    case LANG_RUSSIAN:
//      langname = "ru";
//      break;
//    }
//    if (langname) {
//      static char envstr[40];
//
//      envstr = util.my_snprintf( "LANG=%s", langname);
//      putenv(envstr);
//    }
//  }
//#endif
//
//  () setlocale(LC_ALL, "");
//  () bindtextdomain(PACKAGE, LOCALEDIR);
//  () textdomain(PACKAGE);
//
//  /* Don't touch the defaults when LC_NUMERIC == "C".
//     This is intended to cater to the common case where:
//       1) The user is from North America. ;-)
//       2) The user has not set the proper environment variables.
//	  (Most applications are (unfortunately) US-centric
//	  by default, so why bother?)
//     This would result in the "C" locale being used, with grouping ""
//     and thousands_sep "", where we really want "\3" and ",". */
//
//  if (strcmp(setlocale(LC_NUMERIC, null), "C") != 0) {
//    lconv lc = localeconv();
//
//    if (lc.grouping[0] == '\0') {
//      /* This actually indicates no grouping at all. */
//      static char m = CHAR_MAX;
//      grouping = &m;
//    } else {
//      size_t len;
//      for (len = 0;
//	   lc.grouping[len] != '\0' && lc.grouping[len] != CHAR_MAX; len++) {
//	/* nothing */
//      }
//      len++;
//      free(grouping);
//      grouping = fc_malloc(len);
//      memcpy(grouping, lc.grouping, len);
//    }
//    free(grouping_sep);
//    grouping_sep = (lc.thousands_sep);
//  }
//#endif
}

///***************************************************************************
//  If we have root privileges, util.die with an error.
//  (Eg, for security reasons.)
//  Param argv0 should be argv[0] or similar; fallback is
//  used instead if argv0 is null.
//  But don't util.die on systems where the user is always root...
//  (a general test for this would be better).
//  Doesn't use util.freelog() because gets called before logging is setup.
//***************************************************************************/
//void dont_run_as_root(final String argv0, final String fallback)
//{
//#if (defined(ALWAYS_ROOT) || defined(__EMX__) || defined(__BEOS__))
//  return;
//#else
//  if (getuid()==0 || geteuid()==0) {
//    fc_fprintf(stderr,
//	       "%s: Fatal error: you're trying to run me as superuser!\n",
//	       (argv0 ? argv0 : fallback ? fallback : "freeciv"));
//    fc_fprintf(stderr, "Use a non-privileged account instead.\n");
//    exit(EXIT_FAILURE);
//  }
//#endif
//}
//
///***************************************************************************
//  Return a description string of the result.
//  In English, form of description is suitable to substitute in, eg:
//     prefix is <description>
//  (N.B.: The description is always in English, but they have all been marked
//   for translation.  If you want a localized version, use _() on the return.)
//***************************************************************************/
//final String m_pre_description(enum m_pre_result result)
//{
//  static final String final descriptions[] = {
//    N"exact match",
//    N"only match",
//    N"ambiguous",
//    N"empty",
//    N"too long",
//    N"non-match"
//  };
//  assert(result >= 0 && result < ARRAY_SIZE(descriptions));
//  return descriptions[result];
//}
//
///***************************************************************************
//  Given n names, with maximum length max_len_name, accessed by
//  accessor_fn(0) to accessor_fn(n-1), look for matching prefix
//  according to given comparison function.
//  Returns type of match or fail, and for return <= m_pre_result.M_PRE_AMBIGUOUS
//  sets *ind_result with matching index (or for ambiguous, first match).
//  If max_len_name==0, treat as no maximum.
//***************************************************************************/
//enum m_pre_result match_prefix(m_pre_accessor_fn_t accessor_fn,
//			       size_t n_names,
//			       size_t max_len_name,
//			       m_pre_strncmp_fn_t cmp_fn,
//			       final String prefix,
//			       int *ind_result)
//{
//  int i, len, nmatches;
//
//  len = prefix.length();
//  if (len == 0) {
//    return M_PRE_EMPTY;
//  }
//  if (len > max_len_name && max_len_name > 0) {
//    return M_PRE_LONG;
//  }
//
//  nmatches = 0;
//  for(i=0; i<n_names; i++) {
//    final String name = accessor_fn(i);
//    if (cmp_fn(name, prefix, len)==0) {
//      if (name.length() == len) {
//	*ind_result = i;
//	return M_PRE_EXACT;
//      }
//      if (nmatches==0) {
//	*ind_result = i;	/* first match */
//      }
//      nmatches++;
//    }
//  }
//
//  if (nmatches == 1) {
//    return M_PRE_ONLY;
//  } else if (nmatches > 1) {
//    return m_pre_result.M_PRE_AMBIGUOUS;
//  } else {
//    return M_PRE_FAIL;
//  }
//}
//
///***************************************************************************
// Return whether two vectors: vec1 and vec2 have common
// bits. I.e. (vec1 & vec2) != 0.
//
// Don't call this function directly, use BV_CHECK_MASK macro
// instead. Don't call this function with two different bitvectors.
//***************************************************************************/
//boolean bv_check_mask(final unsigned char *vec1, final unsigned char *vec2,
//		   size_t size1, size_t size2)
//{
//  size_t i;
//  assert(size1 == size2);
//
//  for (i = 0; i < size1; i++) {
//    if ((vec1[0] & vec2[0]) != 0) {
//      return true;
//    }
//    vec1++;
//    vec2++;
//  }
//  return false;
//}
//
//boolean bv_are_equal(final unsigned char *vec1, final unsigned char *vec2,
//		  size_t size1, size_t size2)
//{
//  size_t i;
//  assert(size1 == size2);
//
//  for (i = 0; i < size1; i++) {
//    if (vec1[0] != vec2[0]) {
//      return false;
//    }
//    vec1++;
//    vec2++;
//  }
//  return true;
//}
//
///***************************************************************************
//  Returns string which gives the multicast group IP address for finding
//  servers on the LAN, as specified by $FREECIV_MULTICAST_GROUP.
//  Gets value once, and then caches result.
//***************************************************************************/
//char *get_multicast_group()
//{
//  static boolean init = false;
//  static char *group = null;
//  static char *default_multicast_group = "225.0.0.1";
//  
//  if (!init) {
//    char *env = getenv("FREECIV_MULTICAST_GROUP");
//    if (env) {
//      group = (env);	        
//    } else {
//      group = (default_multicast_group);
//    }
//    init = true;
//  }
//  return group;
//}
//
///***************************************************************************
//  Interpret ~/ in filename as home dir
//  New path is returned in buf of size buf_size
//***************************************************************************/
//void interpret_tilde(char* buf, size_t buf_size, final char* filename)
//{
//  if (filename[0] == '~' && filename[1] == '/') {
//    buf = String.format "%s/%s", user_home_dir(), filename + 2);
//  } else if (filename[0] == '~' && filename[1] == '\0') {
//    strncpy(buf, user_home_dir(), buf_size);
//  } else  {
//    strncpy(buf, filename, buf_size);
//  }
//}
//
///**************************************************************************
//  If the directory "pathname" does not exist, recursively create all
//  directories until it does.
//**************************************************************************/
//boolean make_dir(final String pathname)
//{
//  char *dir;
//  char file[PATH_MAX];
//  char path[PATH_MAX];
//
//  interpret_tilde(file, sizeof(file), pathname);
//  path[0] = '\0';
//
//#ifndef WIN32_NATIVE
//  /* Ensure we are starting from the root in absolute pathnames. */
//  if (file[0] == '/') {
//    sz_strlcat(path, "/");
//  }
//#endif
//
//  for (dir = strtok(file, "/"); dir; dir = strtok(null, "/")) {
//    sz_strlcat(path, dir);
//
//#ifdef WIN32_NATIVE
//    mkdir(path);
//#else
//    mkdir(path, 0755);
//#endif
//
//    sz_strlcat(path, "/");
//  }
//
//  return true;
//}
//
///**************************************************************************
//  Returns true if the filename's path is absolute.
//**************************************************************************/
//boolean path_is_absolute(final String filename)
//{
//  if (!filename) {
//    return false;
//  }
//
//  if (filename[0] == '/') {
//    return true;
//  }
//
//  return false;
//}
//
}