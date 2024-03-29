package utility;

public class Inputfile{

// Freeciv - Copyright (C) 1996 - A Kjeldberg, L Gregersen, P Unold
//   This program is free software; you can redistribute it and/or modify
//   it under the terms of the GNU General Public License as published by
//   the Free Software Foundation; either version 2, or (at your option)
//   any later version.
//
//   This program is distributed in the hope that it will be useful,
//   but WITHOUT ANY WARRANTY; without even the implied warranty of
//   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//   GNU General Public License for more details.
//***********************************************************************/
//

//  A low-level object for reading a registry-format file.
//  original author: David Pfitzner <dwp@mso.anu.edu.au>
//
//  This module implements an object which is useful for reading/parsing
//  a file in the registry format of registry.c.  It takes care of the
//  low-level file-reading details, and provides functions to return
//  specific "tokens" from the file.  Probably this should really use
//  higher-level tools... (flex/lex bison/yacc?)
//
//  When the user tries to read a token, we return a (final char*)
//  pointing to some data if the token was found, or null otherwise.
//  The data pointed to should not be modified.  The retuned pointer
//  is valid _only_ until another inputfile is performed.  (So should
//  be used immediately, or -ed etc.)
//  
//  The tokens recognised are as follows:
//  (Single quotes are delimiters used here, but are not part of the
//  actual tokens/strings.)
//  Most tokens can be preceeded by optional whitespace; exceptions
//  are section_name and entry_name.
//
//  section_name:  '[foo]'
//  returned token: 'foo'
//  
//  entry_name:  'foo =' (optional whitespace allowed before '=')
//  returned token: 'foo'
//  
//  end_of_line: newline, or optional '#' or ';' (comment characters)
//               followed by any other chars, then newline.
//  returned token: should not be used except to check non-null.
//  
//  table_start: '{'  
//  returned token: should not be used except to check non-null.
//  
//  table_end: '}'  
//  returned token: should not be used except to check non-null.
//
//  comma:  literal ','  
//  returned token: should not be used except to check non-null.
//  
//  value:  a signed integer, or a double-quoted string, or a
//          gettext-marked double quoted string.  Strings _may_ contain
//	  raw embedded newlines, and escaped doublequotes, or \.
//	  eg:  '123', '-999', '"foo"', '("foo")'
//  returned token: string containing number, for numeric, or string
//          starting at first doublequote for strings, but ommiting
//	  trailing double-quote.  Note this does _not_ translate
//	  escaped doublequotes etc back to normal.
//
//***********************************************************************/
//
//#ifdef HAVE_CONFIG_H
//#include <config.h>
//#endif
//
//#include <assert.h>
//#include <stdio.h>
//#include <string.h>
//
//#include "astring.h"
//#include "ioz.h"
//#include "log.h"
//#include "mem.h"
//#include "shared.h"		/* true, false */
//#include "support.h"
//
//#include "inputfile.h"
//
//public static final int INF_DEBUG_FOUND = false;
//public static final int INF_DEBUG_NOT_FOUND = false;
//
//#define INF_MAGIC (0xabdc0132)	/* arbitrary */
//
//struct inputfile {
//  unsigned int magic;		/* memory check */
//  char *filename;		/* filename as passed to fopen */
//  fz_FILE *fp;			/* read from this */
//  boolean at_eof;			/* flag for end-of-file */
//  struct astring cur_line;	/* data from current line, or .n==0 if
//				   have not yet read in the current line */
//  struct astring copy_line;	/* original cur_line (sometimes insert nulls
//				   in cur_line for processing) */
//  int cur_line_pos;		/* position in current line */
//  int line_num;			/* line number from file in cur_line */
//  struct astring token;		/* data returned to user */
//  struct astring partial;	/* used in accumulating multi-line strings;
//				   used only in get_token_value, but put
//				   here so it gets freed when file closed */
//  datafilename_fn_t datafn;	/* function like datafilename(); use a
//				   function pointer just to keep this
//				   inputfile module "generic" */
//  boolean in_string;		/* set when reading multi-line strings,
//				   to know not to handle *include at start
//				   of line as include mechanism */
//  int string_start_line;	/* when in_string is true, this is the
//				   start line of current string */
//  inputfile included_from; /* null for toplevel file, otherwise
//				      points back to files which this one
//				      has been included from */
//};
//
///* A function to get a specific token type: */
//typedef final String(*get_token_fn_t)(inputfile inf);
//
//static final String get_token_section_name(inputfile inf);
//static final String get_token_entry_name(inputfile inf);
//static final String get_token_eol(inputfile inf);
//static final String get_token_table_start(inputfile inf);
//static final String get_token_table_end(inputfile inf);
//static final String get_token_comma(inputfile inf);
//static final String get_token_value(inputfile inf);
//
//static struct {
//  final String name;
//  get_token_fn_t func;
//}
//tok_tab[INF_TOK_LAST] =
//{
//  { "section_name", get_token_section_name },
//  { "entry_name",   get_token_entry_name },
//  { "end_of_line",  get_token_eol },
//  { "table_start",  get_token_table_start },
//  { "table_end",    get_token_table_end },
//  { "comma",        get_token_comma },
//  { "value",        get_token_value },
//};
//
//static boolean read_a_line(inputfile inf);
//static void inf_warn(inputfile inf, final String message);
//

//  Return true if c is a 'comment' character: '#' or ';'
//***********************************************************************/
//static boolean is_comment(int c)
//{
//  return (c == '#' || c == ';');
//}
//

//  Set values to zeros; should have free'd/closed everything before
//  this if appropriate.
//***********************************************************************/
//static void init_zeros(inputfile inf)
//{
//  assert(inf != null);
//  inf.magic = INF_MAGIC;
//  inf.filename = null;
//  inf.fp = null;
//  inf.datafn = null;
//  inf.included_from = null;
//  inf.line_num = inf.cur_line_pos = 0;
//  inf.at_eof = inf.in_string = false;
//  inf.string_start_line = 0;
//  astr_init(&inf.cur_line);
//  astr_init(&inf.copy_line);
//  astr_init(&inf.token);
//  astr_init(&inf.partial);
//}
//

//  Check sensible values for an opened inputfile.
//***********************************************************************/
//static void assert_sanity(inputfile inf)
//{
//  assert(inf != null);
//  assert(inf.magic==INF_MAGIC);
//  assert(inf.fp != null);
//  assert(inf.line_num >= 0);
//  assert(inf.cur_line_pos >= 0);
//  assert(inf.at_eof == false || inf.at_eof == true);
//  assert(inf.in_string == false || inf.in_string == true);
//#ifdef DEBUG
//  assert(inf.string_start_line >= 0);
//  assert(inf.cur_line.n >= 0);
//  assert(inf.copy_line.n >= 0);
//  assert(inf.token.n >= 0);
//  assert(inf.partial.n >= 0);
//  assert(inf.cur_line.n_alloc >= 0);
//  assert(inf.copy_line.n_alloc >= 0);
//  assert(inf.token.n_alloc >= 0);
//  assert(inf.partial.n_alloc >= 0);
//  if(inf.included_from) {
//    assert_sanity(inf.included_from);
//  }
//#endif
//}
//
///**************************************************************************
//  Return the filename the inputfile was loaded as, or "(anonymous)"
//  if this inputfile was loaded from a stream rather than from a file.
//**************************************************************************/
//static final String inf_filename(inputfile inf)
//{
//  if (inf.filename) {
//    return inf.filename;
//  } else {
//    return "(anonymous)";
//  }
//}
//

//  Open the file, and return an allocated, initialized structure.
//  Returns null if the file could not be opened.
//***********************************************************************/
//inputfile inf_from_file(final String filename,
//				datafilename_fn_t datafn)
//{
//  inputfile inf;
//  fz_FILE *fp;
//
//  assert(filename != null);
//  assert(filename.length() > 0);
//  fp = fz_from_file(filename, "r", FZ_NOT_USED, FZ_NOT_USED);
//  if (!fp) {
//    return null;
//  }
//  util.freelog(Log.LOG_DEBUG, "inputfile: opened \"%s\" ok", filename);
//  inf = inf_from_stream(fp, datafn);
//  inf.filename = (filename);
//  return inf;
//}
//

//  Open the stream, and return an allocated, initialized structure.
//  Returns null if the file could not be opened.
//***********************************************************************/
//inputfile inf_from_stream(fz_FILE * stream, datafilename_fn_t datafn)
//{
//  inputfile inf;
//
//  assert(stream != null);
//  inf = fc_malloc(sizeof(*inf));
//  init_zeros(inf);
//  
//  inf.filename = null;
//  inf.fp = stream;
//  inf.datafn = datafn;
//
//  util.freelog(Log.LOG_DEBUG, "inputfile: opened \"%s\" ok", inf_filename(inf));
//  return inf;
//}
//
//

//  Close the file and free associated memory, but don't recurse
//  included_from files, and don't free the actual memory where
//  the inf record is stored (ie, the memory where the users pointer
//  points to).  This is used when closing an included file.
//***********************************************************************/
//static void inf_close_partial(inputfile inf)
//{
//  assert_sanity(inf);
//
//  util.freelog(Log.LOG_DEBUG, "inputfile: sub-closing \"%s\"", inf_filename(inf));
//
//  if (fz_ferror(inf.fp) != 0) {
//    util.freelog(Log.LOG_ERROR, "Error before closing %s: %s", inf_filename(inf),
//	    fz_strerror(inf.fp));
//    fz_fclose(inf.fp);
//    inf.fp = null;
//  }
//  else if (fz_fclose(inf.fp) != 0) {
//    util.freelog(Log.LOG_ERROR, "Error closing %s", inf_filename(inf));
//  }
//  if (inf.filename) {
//    free(inf.filename);
//  }
//  inf.filename = null;
//  astr_free(&inf.cur_line);
//  astr_free(&inf.copy_line);
//  astr_free(&inf.token);
//  astr_free(&inf.partial);
//
//  /* assign zeros for safety if accidently re-use etc: */
//  init_zeros(inf);
//  inf.magic = ~INF_MAGIC;
//
//  util.freelog(Log.LOG_DEBUG, "inputfile: sub-closed ok");
//}
//

//  Close the file and free associated memory, included any partially
//  recursed included files, and the memory allocated for 'inf' itself.
//  Should only be used on an actually open inputfile.
//  After this, the pointer should not be used.
//***********************************************************************/
//void inf_close(inputfile inf)
//{
//  assert_sanity(inf);
//
//  util.freelog(Log.LOG_DEBUG, "inputfile: closing \"%s\"", inf_filename(inf));
//  if (inf.included_from) {
//    inf_close(inf.included_from);
//  }
//  inf_close_partial(inf);
//  free(inf);
//  util.freelog(Log.LOG_DEBUG, "inputfile: closed ok");
//}
//

//  Return 1 if have data for current line.
//***********************************************************************/
//static boolean have_line(inputfile inf)
//{
//  assert_sanity(inf);
//  return (inf.cur_line.n > 0);
//}
//

//  Return 1 if current pos is at end of current line.
//***********************************************************************/
//static boolean at_eol(inputfile inf)
//{
//  assert_sanity(inf);
//  assert(inf.cur_line_pos <= inf.cur_line.n);
//  return (inf.cur_line_pos >= inf.cur_line.n - 1);
//}
//

//  ...
//***********************************************************************/
//boolean inf_at_eof(inputfile inf)
//{
//  assert_sanity(inf);
//  return inf.at_eof;
//}
//

//  Check for an include command, which is an isolated line with:
//     *include "filename"
//  If a file is included via this mechanism, returns 1, and sets up
//  data appropriately: (*inf) will now correspond to the new file,
//  which is opened but no data read, and inf.included_from is set
//  to newly malloced memory which corresponds to the old file.
//***********************************************************************/
//static boolean check_include(inputfile inf)
//{
//  final String include_prefix = "*include";
//  static size_t len = 0;
//  char *bare_name, *full_name, *c;
//  inputfile new_inf, temp;
//
//  if (len==0) {
//    len = include_prefix.length();
//  }
//  assert_sanity(inf);
//  if (inf.at_eof || inf.in_string || inf.cur_line.n <= len
//      || inf.cur_line_pos > 0) {
//    return false;
//  }
//  if (!inf.cur_line.str.equals(include_prefix)) {
//    return false;
//  }
//  /* from here, the include-line must be well formed or we util.die */
//  /* keep inf.cur_line_pos accurate just so error messages are useful */
//
//  /* skip any whitespace: */
//  inf.cur_line_pos = len;
//  c = inf.cur_line.str + len;
//  while (*c != '\0' && my_isspace(*c)) c++;
//
//  if (*c != '\"') {
//    inf_log(inf, Log.LOG_ERROR, 
//            "Did not find opening doublequote for '*include' line");
//    return false;
//  }
//  c++;
//  inf.cur_line_pos = c - inf.cur_line.str;
//
//  bare_name = c;
//  while (*c != '\0' && *c != '\"') c++;
//  if (*c != '\"') {
//    inf_log(inf, Log.LOG_ERROR, 
//            "Did not find closing doublequote for '*include' line");
//    return false;
//  }
//  *c++ = '\0';
//  inf.cur_line_pos = c - inf.cur_line.str;
//
//  /* check rest of line is well-formed: */
//  while (*c != '\0' && my_isspace(*c) && !is_comment(*c)) c++;
//  if (!(*c=='\0' || is_comment(*c))) {
//    inf_log(inf, Log.LOG_ERROR, "Junk after filename for '*include' line");
//    return false;
//  }
//  inf.cur_line_pos = inf.cur_line.n-1;
//
//  full_name = inf.datafn(bare_name);
//  if (!full_name) {
//    util.freelog(Log.LOG_ERROR, "Could not find included file \"%s\"", bare_name);
//    return false;
//  }
//
//  /* avoid recursion: (first filename may not have the same path,
//     but will at least stop infinite recursion) */
//  {
//    inputfile inc = inf;
//    do {
//      if (inc.filename && full_name.equals(inc.filename)) {
//        util.freelog(Log.LOG_ERROR, 
//                "Recursion trap on '*include' for \"%s\"", full_name);
//        return false;
//      }
//    } while((inc=inc.included_from));
//  }
//  
//  new_inf = inf_from_file(full_name, inf.datafn);
//
//  /* Swap things around so that memory pointed to by inf (user pointer,
//     and pointer in calling functions) contains the new inputfile,
//     and newly allocated memory for new_inf contains the old inputfile.
//     This is pretty scary, lets hope it works...
//  */
//  temp = *new_inf;
//  *new_inf = *inf;
//  *inf = temp;
//  inf.included_from = new_inf;
//  return true;
//}
//

//  Read a new line into cur_line; also copy to copy_line.
//  Increments line_num and cur_line_pos.
//  Returns 0 if didn't read or other problem: treat as EOF.
//  Strips newline from input.
//***********************************************************************/
//static boolean read_a_line(inputfile inf)
//{
//  astring line;
//  char *ret;
//  int pos;
//  
//  assert_sanity(inf);
//
//  if (inf.at_eof)
//    return false;
//  
//  /* abbreviation: */
//  line = &inf.cur_line;
//  
//  /* minimum initial line length: */
//  astr_minsize(line, 80);
//  pos = 0;
//
//  /* don't print "orig line" in warnings until we have it: */
//  inf.copy_line.n = 0;
//  
//  /* Read until we get a full line:
//   * At start of this loop, pos is index to trailing null
//   * (or first position) in line.
//   */
//  for(;;) {
//    ret = fz_fgets(line.str + pos, line.n_alloc - pos, inf.fp);
//    
//    if (!ret) {
//      /* fgets failed */
//      inf.at_eof = true;
//      if (pos != 0) {
//	inf_warn(inf, "missing newline at EOF, or failed read");
//	/* treat as simple EOF, ignoring last line: */
//	pos = 0;
//      }
//      line.str[0] = '\0';
//      line.n = 0;
//      if (inf.in_string) {
//	/* Note: Don't allow multi-line strings to cross "include"
//	   boundaries */
//	inf_log(inf, Log.LOG_ERROR, "Multi-line string went to end-of-file");
//        return false;
//      }
//      if (inf.included_from) {
//	/* Pop the include, and get next line from file above instead. */
//	inputfile inc = inf.included_from;
//	inf_close_partial(inf);
//	*inf = *inc;		/* so the user pointer in still valid
//				   (and inf pointers in calling functions) */
//	free(inc);
//	return read_a_line(inf);
//      }
//      break;
//    }
//    
//    pos += strlen(line.str + pos);
//    line.n = pos + 1;
//    
//    if (line.str[pos-1] == '\n') {
//      line.str[pos-1] = '\0';
//      line.n--;
//      break;
//    }
//    if (line.n != line.n_alloc) {
//      util.freelog(Log.LOG_VERBOSE, "inputfile: expect missing newline at EOF");
//    }
//    astr_minsize(line, line.n*2);
//  }
//  inf.line_num++;
//  inf.cur_line_pos = 0;
//
//  astr_minsize(&inf.copy_line, inf.cur_line.n + ((inf.cur_line.n == 0) ? 1 : 0));
//  strcpy(inf.copy_line.str, inf.cur_line.str);
//
//  if (check_include(inf)) {
//    return read_a_line(inf);
//  }
//  return (!inf.at_eof);
//}
//

//  Set "flag" token when we don't really want to return anything,
//  except non-null.
//***********************************************************************/
//static void assign_flag_token(astring astr, char val)
//{
//  static char flag_token[2];
//
//  assert(astr != null);
//  flag_token[0] = val;
//  flag_token[1] = '\0';
//  astr_minsize(astr, 2);
//  strcpy(astr.str, flag_token);
//}
//

//  Give a detailed log message, including information on
//  current line number etc.  Message can be null: then just logs
//  information on where we are in the file.
//***********************************************************************/
//void inf_log(inputfile inf, int loglevel, final String message)
//{
//  assert_sanity(inf);
//
//  if (message) {
//    util.freelog(loglevel, "%s", message);
//  }
//  util.freelog(loglevel, "  file \"%s\", line %d, pos %d%s",
//	  inf_filename(inf), inf.line_num, inf.cur_line_pos,
//	  (inf.at_eof ? ", EOF" : ""));
//  if (inf.cur_line.str && inf.cur_line.n > 0) {
//    util.freelog(loglevel, "  looking at: '%s'",
//	    inf.cur_line.str+inf.cur_line_pos);
//  }
//  if (inf.copy_line.str && inf.copy_line.n > 0) {
//    util.freelog(loglevel, "  original line: '%s'", inf.copy_line.str);
//  }
//  if (inf.in_string) {
//    util.freelog(loglevel, "  processing string starting at line %d",
//	    inf.string_start_line);
//  }
//  while ((inf=inf.included_from)) {    /* local pointer assignment */
//    util.freelog(loglevel, "  included from file \"%s\", line %d",
//	    inf_filename(inf), inf.line_num);
//  }
//}
//

//  ...
//***********************************************************************/
//static void inf_warn(inputfile inf, final String message)
//{
//  inf_log(inf, Log.LOG_NORMAL, message);
//}
//

//  ...
//***********************************************************************/
//static final String get_token(inputfile inf,
//			     enum inf_token_type type,
//			     boolean required)
//{
//  final String c;
//  final String name;
//  get_token_fn_t func;
//  
//  assert_sanity(inf);
//  assert(type>=INF_TOK_FIRST && type<INF_TOK_LAST);
//
//  name = tok_tab[type].name ? tok_tab[type].name : "(unnamed)";
//  func = tok_tab[type].func;
//  
//  if (!func) {
//    util.freelog(Log.LOG_NORMAL, "token type %d (%s) not supported yet", type, name);
//    c = null;
//  } else {
//    if (!have_line(inf))
//      () read_a_line(inf);
//    if (!have_line(inf)) {
//      c = null;
//    } else {
//      c = func(inf);
//    }
//  }
//  if (c) {
//    if (INF_DEBUG_FOUND) {
//      util.freelog(Log.LOG_DEBUG, "inputfile: found %s '%s'", name, inf.token.str);
//    }
//  } else if (required) {
//    util.freelog(LOG_FATAL, "Did not find token %s in %s line %d", 
//            name, inf.filename, inf.line_num);
//    exit(EXIT_FAILURE);
//  }
//  return c;
//}
//  
//final String inf_token(inputfile inf, enum inf_token_type type)
//{
//  return get_token(inf, type, false);
//}
//
//final String inf_token_required(inputfile inf, enum inf_token_type type)
//{
//  return get_token(inf, type, true);
//}
//

//  Read as many tokens of specified type as possible, discarding
//  the results; returns number of such tokens read and discarded.
//***********************************************************************/
//int inf_discard_tokens(inputfile inf, enum inf_token_type type)
//{
//  int count = 0;
//  
//  while(inf_token(inf, type))
//    count++;
//  return count;
//}
//

//  ...
//***********************************************************************/
//static final String get_token_section_name(inputfile inf)
//{
//  char *c, *start;
//  
//  assert(have_line(inf));
//
//  c = inf.cur_line.str + inf.cur_line_pos;
//  if (*c++ != '[')
//    return null;
//  start = c;
//  while (*c != '\0' && *c != ']') {
//    c++;
//  }
//  if (*c != ']')
//    return null;
//  *c++ = '\0';
//  inf.cur_line_pos = c - inf.cur_line.str;
//  astr_minsize(&inf.token, start.length()+1);
//  strcpy(inf.token.str, start);
//  return inf.token.str;
//}
//

//  ...
//***********************************************************************/
//static final String get_token_entry_name(inputfile inf)
//{
//  char *c, *start, *end;
//  
//  assert(have_line(inf));
//
//  c = inf.cur_line.str + inf.cur_line_pos;
//  while(*c != '\0' && my_isspace(*c)) {
//    c++;
//  }
//  if (*c == '\0')
//    return null;
//  start = c;
//  while (*c != '\0' && !my_isspace(*c) && *c != '=' && !is_comment(*c)) {
//    c++;
//  }
//  if (!(*c != '\0' && (my_isspace(*c) || *c == '='))) 
//    return null;
//  end = c;
//  while (*c != '\0' && *c != '=' && !is_comment(*c)) {
//    c++;
//  }
//  if (*c != '=') {
//    return null;
//  }
//  *end = '\0';
//  inf.cur_line_pos = c + 1 - inf.cur_line.str;
//  astr_minsize(&inf.token, start.length()+1);
//  strcpy(inf.token.str, start);
//  return inf.token.str;
//}
//

//  ...
//***********************************************************************/
//static final String get_token_eol(inputfile inf)
//{
//  char *c;
//  
//  assert(have_line(inf));
//
//  if (!at_eol(inf)) {
//    c = inf.cur_line.str + inf.cur_line_pos;
//    while(*c != '\0' && my_isspace(*c)) {
//      c++;
//    }
//    if (*c != '\0' && !is_comment(*c))
//      return null;
//  }
//
//  /* finished with this line: say that we don't have it any more: */
//  inf.cur_line.n = 0;
//  inf.cur_line_pos = 0;
//  
//  assign_flag_token(&inf.token, ' ');
//  return inf.token.str;
//}
//

//  Get a flag token of a single character, with optional
//  preceeding whitespace.
//***********************************************************************/
//static final String get_token_white_char(inputfile inf,
//					char target)
//{
//  char *c;
//  
//  assert(have_line(inf));
//
//  c = inf.cur_line.str + inf.cur_line_pos;
//  while(*c != '\0' && my_isspace(*c)) {
//    c++;
//  }
//  if (*c != target)
//    return null;
//  inf.cur_line_pos = c + 1 - inf.cur_line.str;
//  assign_flag_token(&inf.token, target);
//  return inf.token.str;
//}
//

//  ...
//***********************************************************************/
//static final String get_token_table_start(inputfile inf)
//{
//  return get_token_white_char(inf, '{');
//}
//

//  ...
//***********************************************************************/
//static final String get_token_table_end(inputfile inf)
//{
//  return get_token_white_char(inf, '}');
//}
//

//  ...
//***********************************************************************/
//static final String get_token_comma(inputfile inf)
//{
//  return get_token_white_char(inf, ',');
//}
//

//  This one is more complicated; note that it may read in multiple lines.
//***********************************************************************/
//static final String get_token_value(inputfile inf)
//{
//  astring partial;
//  char *c, *start;
//  char trailing;
//  boolean has_i18n_marking = false;
//  
//  assert(have_line(inf));
//
//  c = inf.cur_line.str + inf.cur_line_pos;
//  while(*c != '\0' && my_isspace(*c)) {
//    c++;
//  }
//  if (*c == '\0')
//    return null;
//
//  if (*c == '-' || Character.isDigit(*c)) {
//    /* a number: */
//    start = c++;
//    while(*c != '\0' && Character.isDigit(*c)) {
//      c++;
//    }
//    /* check that the trailing stuff is ok: */
//    if (!(*c == '\0' || *c == ',' || my_isspace(*c) || is_comment(*c))) {
//      return null;
//    }
//    /* If its a comma, we don't want to obliterate it permanently,
//     * so rememeber it: */
//    trailing = *c;
//    *c = '\0';
//    
//    inf.cur_line_pos = c - inf.cur_line.str;
//    astr_minsize(&inf.token, start.length()+1);
//    strcpy(inf.token.str, start);
//    
//    *c = trailing;
//    return inf.token.str;
//  }
//
//  /* allow gettext marker: */
//  if (*c == '_' && *(c+1) == '(') {
//    has_i18n_marking = true;
//    c += 2;
//    while(*c != '\0' && my_isspace(*c)) {
//      c++;
//    }
//    if (*c == '\0')
//      return null;
//  }
//
//  if (!(*c == '\"'))
//    return null;
//
//  /* From here, we know we have a string, we just have to find the
//     trailing (un-escaped) double-quote.  We read in extra lines if
//     necessary to find it.  If we _don't_ find the end-of-string
//     (that is, we come to end-of-file), we return null, but we
//     leave the file in at_eof, and don't try to back-up to the
//     current point.  (That would be more difficult, and probably
//     not necessary: at that point we probably have a malformed
//     string/file.)
//
//     As we read extra lines, the string value from previous
//     lines is placed in partial.
//  */
//
//  /* prepare for possibly multi-line string: */
//  inf.string_start_line = inf.line_num;
//  inf.in_string = true;
//
//  partial = &inf.partial;	/* abbreviation */
//  astr_minsize(partial, 1);
//  partial.str[0] = '\0';
//  
//  start = c++;			/* start includes the initial \", to
//				   distinguish from a number */
//  for(;;) {
//    int pos;
//    
//    while(*c != '\0' && *c != '\"') {
//      /* skip over escaped chars, including backslash-doublequote,
//	 and backslash-backslash: */
//      if (*c == '\\' && *(c+1) != '\0') {  
//	c++;
//      }
//      c++;
//    }
//    if (*c == '\"') 
//      break;      /* found end of string */
//
//    /* Accumulate to partial string and try more lines;
//     * note partial.n must be _exactly_ the right size, so we
//     * can strcpy instead of strcat-ing all the way to the end
//     * each time. */
//    pos = partial.n - 1;
//    astr_minsize(partial, partial.n + c - start + 1);
//    strcpy(partial.str + pos, start);
//    strcpy(partial.str + partial.n - 2, "\n");
//    
//    if (!read_a_line(inf)) {
//      /* shouldn't happen */
//      inf_log(inf, Log.LOG_ERROR, 
//              "Bad return for multi-line string from read_a_line");
//      return null;
//    }
//    c = start = inf.cur_line.str;
//  }
//
//  /* found end of string */
//  *c = '\0';
//  inf.cur_line_pos = c + 1 - inf.cur_line.str;
//  astr_minsize(&inf.token, partial.n + start.length());
//  strcpy(inf.token.str, partial.str);
//  strcpy(inf.token.str + partial.n - 1, start);
//
//  /* check gettext tag at end: */
//  if (has_i18n_marking) {
//    if (*++c == ')') {
//      inf.cur_line_pos++;
//    } else {
//      inf_warn(inf, "Missing end of i18n string marking");
//    }
//  }
//  inf.in_string = false;
//  return inf.token.str;
//}
}