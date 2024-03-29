package utility;

public class Registry{

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
///**************************************************************************
//  the idea with this file is to create something similar to the ms-windows
//  .ini files functions.
//  it also demonstrates how ugly code using the genlist class looks.
//  however the interface is nice. ie:
//  section_file_lookup_string(file, "player%d.unit%d.name", plrno, unitno); 
//***************************************************************************/
//
///**************************************************************************
//  Description of the file format:
//  (This is based on a format by the original authors, with
//  various incremental extensions. --dwp)
//  
//  - Whitespace lines are ignored, as are lines where the first
//  non-whitespace character is ';' (comment lines).
//  Optionally '#' can also be used for comments.
//
//  - A line of the form:
//       *include "filename"
//  includes the named file at that point.  (The '*' must be the
//  first character on the line.) The file is found by looking in
//  FREECIV_PATH.  Non-infinite recursive includes are allowed.
//  
//  - A line with "[name]" labels the start of a section with
//  that name; one of these must be the first non-comment line in
//  the file.  Any spaces within the brackets are included in the
//  name, but this feature (?) should probably not be used...
//
//  - Within a section, lines have one of the following forms:
//      subname = "stringvalue"
//      subname = -digits
//      subname = digits
//  for a value with given name and string, negative integer, and
//  positive integer values, respectively.  These entries are
//  referenced in the following functions as "sectionname.subname".
//  The section name should not contain any dots ('.'); the subname
//  can, but they have no particular significance.  There can be
//  optional whitespace before and/or after the equals sign.
//  You can put a newline after (but not before) the equals sign.
//  
//  Backslash is an escape character in strings (double-quoted strings
//  only, not names); recognised escapes are \n, \\, and \".
//  (Any other \<char> is just treated as <char>.)
//
//  - Gettext markings:  You can surround strings like so:
//      foo = "stringvalue"
//  The registry just ignores these extra markings, but this is
//  useful for marking strings for translations via gettext tools.
//
//  - Multiline strings:  Strings can have embeded newlines, eg:
//    foo = "
//    This is a string
//    over multiple lines
//    "
//  This is equivalent to:
//    foo = "\nThis is a string\nover multiple lines\n"
//  Note that if you missplace the trailing doublequote you can
//  easily end up with strange errors reading the file...
//
//  - Vector format: An entry can have multiple values separated
//  by commas, eg:
//      foo = 10, 11, "x"
//  These are accessed by names "foo", "foo,1" and "foo,2"
//  (with section prefix as above).  So the above is equivalent to:
//      foo   = 10
//      foo,1 = 11
//      foo,2 = "x"
//  As in the example, in principle you can mix integers and strings,
//  but the calling program will probably require elements to be the
//  same type.   Note that the first element of a vector is not "foo,0",
//  in order that the name of the first element is the same whether or
//  not there are subsequent elements.  However as a convenience, if
//  you try to lookup "foo,0" then you get back "foo".  (So you should
//  never have "foo,0" as a real name in the datafile.)
//
//  - Tabular format:  The lines:
//      foo = { "bar",  "baz",   "bax" +
//              "wow",   10,     -5
//              "cool",  "str" +
//              "hmm",    314,   99, 33, 11
//      }
//  are equivalent to the following:
//      foo0.bar = "wow"
//      foo0.baz = 10
//      foo0.bax = -5
//      foo1.bar = "cool"
//      foo1.baz = "str"
//      foo2.bar = "hmm"
//      foo2.baz = 314
//      foo2.bax = 99
//      foo2.bax,1 = 33
//      foo2.bax,2 = 11
//  The first line specifies the base name and the column names, and the
//  subsequent lines have data.  Again it is possible to mix string and
//  integer values in a column, and have either more or less values
//  in a row than there are column headings, but the code which uses
//  this information (via the registry) may set more stringent conditions.
//  If a row has more entries than column headings, the last column is
//  treated as a vector (as above).  You can optionally put a newline
//  after '=' and/or after '{'.
//
//  The equivalence above between the new and old formats is fairly
//  direct: internally, data is converted to the old format.
//  In principle it could be a good idea to represent the data
//  as a table (2-d array) internally, but the current method
//  seems sufficient and relatively simple...
//  
//  There is a limited ability to save data in tabular:
//  So long as the section_file is finalructed in an expected way,
//  tabular data (with no missing or extra values) can be saved
//  in tabular form.  (See section_file_save().)
//
//  - Multiline vectors: if the last non-comment non-whitespace
//  character in a line is a comma, the line is considered to
//  continue on to the next line.  Eg:
//      foo = 10,
//            11,
//            "x"
//  This is equivalent to the original "vector format" example above.
//  Such multi-lines can occur for column headings, vectors, or
//  table rows, again with some potential for strange errors...
//
//***************************************************************************/
//
///**************************************************************************
//  Hashing registry lookups: (by dwp)
//  - Have a hash table direct to entries, bypassing sections division.
//  - For convenience, store the key (the full section+entry name)
//    in the hash table (some memory overhead).
//  - The number of entries is fixed when the hash table is built.
//  - Now uses hash.c
//**************************************************************************/
//
//#ifdef HAVE_CONFIG_H
//#include <config.h>
//#endif
//
//#include <assert.h>
//#include <stdarg.h>
//#include <stdlib.h>
//#include <string.h>
//
//#include "astring.h"
//#include "genlist.h"
//#include "hash.h"
//#include "inputfile.h"
//#include "ioz.h"
//#include "log.h"
//#include "mem.h"
//#include "sbuffer.h"
//#include "shared.h"
//#include "support.h"
//
//#include "registry.h"
//
//public static final int MAX_LEN_BUFFER = 1024;
//
//public static final int SAVE_TABLES = true;	/* set to 0 for old-style savefiles */
//public static final int SECF_DEBUG_ENTRIES = false;/* Log.LOG_DEBUG each entry value */
//
//#define SPECVEC_TAG astring
//#include "specvec.h"
//
///* An 'entry' is a string, integer or string vector;
// * Whether it is string or int or string vector is determined by whether
// * svalue/vec_values is null.
// */
//struct entry {
//  char *name;			/* name, not including section prefix */
//  int  ivalue;			/* value if integer */
//  char *svalue;			/* value if string (in sbuffer) */
//  char **vec_values;		/* string vector values */
//  int dim;			/* vector's size */
//  int  used;			/* number of times entry looked up */
//  char *comment;                /* comment, may be null */
//};
//
///* create a 'Speclists<entry>' and related functions: */
//#define SPECLIST_TAG entry
//#include "speclist.h"
//
//#define entry_list_iterate(entlist, pentry) \
//       TYPED_LIST_ITERATE(struct entry, entlist, pentry)
//#define entry_list_iterate_end  LIST_ITERATE_END
//
//
//struct section {
//  char *name;
//  Speclists<entry> entries;
//};
//
///* create a 'Speclists<section>' and related functions: */
//#define SPECLIST_TAG section
//#include "speclist.h"
//
//#define section_list_iterate(seclist, psection) \
//       TYPED_LIST_ITERATE(struct section, seclist, psection)
//#define section_list_iterate_end  LIST_ITERATE_END
//
//#define section_list_iterate_rev(seclist, psection) \
//       TYPED_LIST_ITERATE_REV(struct section, seclist, psection)
//#define section_list_iterate_rev_end  LIST_ITERATE_REV_END
//
///* The hash table and some extra data: */
//struct hash_data {
//  hash_table htbl;
//  int num_entries_hashbuild;
//  boolean allow_duplicates;
//  int num_duplicates;
//};
//
//static void secfilehash_check(section_file file);
//static void secfilehash_insert(section_file file,
//			       char *key, entry data);
//
//static char *minstrdup(sbuffer sb, final String str);
//static char *moutstr(char *str);
//
//static struct entry*
//section_file_lookup_internal(section_file my_section_file,  
//			     char *fullpath);
//static struct entry*
//section_file_insert_internal(section_file my_section_file, 
//			     char *fullpath);
//
///**************************************************************************
//  Return the filename the sectionfile was loaded as, or "(anonymous)"
//  if this sectionfile was created rather than loaded from file.
//  The memory is managed internally, and should not be altered,
//  nor used after section_file_free() called for the sectionfile.
//**************************************************************************/
//final String secfile_filename(final section_file file)
//{
//  if (file.filename) {
//    return file.filename;
//  } else {
//    return "(anonymous)";
//  }
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void section_file_init(section_file file)
//{
//  file.filename = null;
//  file.sections = fc_malloc(sizeof(Speclists<section>));
//  section_list_init(file.sections);
//  file.num_entries = 0;
//  file.hashd = null;
//  file.sb = sbuf_new();
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void section_file_free(section_file file)
//{
//  /* all the real data is stored in the sbuffer;
//     just free the list meta-data:
//  */
//  section_list_iterate(*file.sections, psection) {
//    entry_list_unlink_all(&psection.entries);
//  }
//  }
//  
//  section_list_unlink_all(file.sections);
//  
//  free(file.sections);
//  file.sections = null;
//
//  /* free the hash data: */
//  if(secfilehash_hashash(file)) {
//    secfilehash_free(file);
//  }
//  
//  /* free the real data: */
//  sbuf_free(file.sb);
//  file.sb = null;
//
//  if (file.filename) {
//    free(file.filename);
//  }
//  file.filename = null;
//}
//
///**************************************************************************
//  Print log messages for any entries in the file which have
//  not been looked up -- ie, unused or unrecognised entries.
//  To mark an entry as used without actually doing anything with it,
//  you could do something like:
//     section_file_lookup(&file, "foo.bar");  / * unused * /
//**************************************************************************/
//void section_file_check_unused(section_file file, final String filename)
//{
//  int any = 0;
//
//  section_list_iterate(*file.sections, psection) {
//    for (entry pentry : psection.entries.data) {
//      if (pentry.used == 0) {
//	if (any == 0 && filename) {
//	  util.freelog(Log.LOG_VERBOSE, "Unused entries in file %s:", filename);
//	  any = 1;
//	}
//	util.freelog(Log.LOG_VERBOSE, "  unused entry: %s.%s",
//		psection.name, pentry.name);
//      }
//    }
//    }
//  }
//  }
//}
//
///**************************************************************************
//  Return a new entry struct, allocated from sb, with given name,
//  and where tok is a "value" return token from inputfile.
//  The entry value has any escaped double-quotes etc removed.
//**************************************************************************/
//static entry new_entry(sbuffer sb, final String name,
//			       final String tok)
//{
//  entry pentry;
//
//  pentry = sbuf_malloc(sb, sizeof(struct entry));
//  pentry.name = sbuf_strdup(sb, name);
//  pentry.comment = null;
//  if (tok[0] == '\"') {
//    pentry.svalue = minstrdup(sb, tok+1);
//    pentry.ivalue = 0;
//    if (SECF_DEBUG_ENTRIES) {
//      util.freelog(Log.LOG_DEBUG, "entry %s '%s'", name, pentry.svalue);
//    }
//  } else {
//    pentry.svalue = null;
//    if (sscanf(tok, "%d", &pentry.ivalue) != 1) {
//      util.freelog(Log.LOG_ERROR, "'%s' isn't an integer", tok);
//    }
//    if (SECF_DEBUG_ENTRIES) {
//      util.freelog(Log.LOG_DEBUG, "entry %s %d", name, pentry.ivalue);
//    }
//  }
//  pentry.used = false;
//  return pentry;
//}
//
///****************************************************************************
//  Return the section with the given name, or null if there is none.
//****************************************************************************/
//static section find_section_by_name(section_file sf,
//					    final String name)
//{
//  /*
//   * Search backwards since on insertion the requested section is most
//   * likely at the end (on lookup it doesn't matter).
//   *
//   * Nonetheless this is slow if there are lots of sections.  We could have
//   * a hash on section names to speed it up.
//   */
//  section_list_iterate_rev(*sf.sections, psection) {
//    if (psection.name.equals(name)) {
//      return psection;
//    }
//  } section_list_iterate_rev_end;
//
//  return null;
//}	
//
///**************************************************************************
//...
//**************************************************************************/
//static boolean section_file_read_dup(section_file sf,
//      	      	      	      	  final String filename,
//      	      	      	      	  inputfile inf,
//				  boolean allow_duplicates)
//{
//  section psection = null;
//  entry pentry;
//  boolean table_state = false;	/* 1 when within tabular format */
//  int table_lineno = 0;		/* row number in tabular, 0=top data row */
//  sbuffer sb;
//  final String tok;
//  int i;
//  struct astring base_name = ASTRING_INIT;    /* for table or single entry */
//  struct astring entry_name = ASTRING_INIT;
//  struct astring_vector columns;    /* astrings for column headings */
//
//  if (!inf) {
//    return false;
//  }
//  section_file_init(sf);
//  if (filename) {
//    sf.filename = (filename);
//  } else {
//    sf.filename = null;
//  }
//  astring_vector_init(&columns);
//  sb = sf.sb;
//
//  if (filename) {
//    util.freelog(Log.LOG_VERBOSE, "Reading registry from \"%s\"", filename);
//  } else {
//    util.freelog(Log.LOG_VERBOSE, "Reading registry");
//  }
//
//  while(!inf_at_eof(inf)) {
//    if (inf_token(inf, INF_TOK_EOL))
//      continue;
//    if (inf_at_eof(inf)) {
//      /* may only realise at eof after trying to read eol above */
//      break;
//    }
//    tok = inf_token(inf, INF_TOK_SECTION_NAME);
//    if (tok) {
//      if (table_state) {
//	inf_log(inf, Log.LOG_ERROR, "new section during table");
//        return false;
//      }
//      /* Check if we already have a section with this name.
//	 (Could ignore this and have a duplicate sections internally,
//	 but then secfile_get_secnames_prefix would return duplicates.)
//	 Duplicate section in input are likely to be useful for includes.
//      */
//      psection = find_section_by_name(sf, tok);
//      if (!psection) {
//	psection = sbuf_malloc(sb, sizeof(struct section));
//	psection.name = sbuf_strdup(sb, tok);
//	entry_list_init(&psection.entries);
//	section_list_insert_back(sf.sections, psection);
//      }
//      () inf_token_required(inf, INF_TOK_EOL);
//      continue;
//    }
//    if (!psection) {
//      inf_log(inf, Log.LOG_ERROR, "data before first section");
//      return false;
//    }
//    if (inf_token(inf, INF_TOK_TABLE_END)) {
//      if (!table_state) {
//	inf_log(inf, Log.LOG_ERROR, "misplaced \"}\"");
//        return false;
//      }
//      () inf_token_required(inf, INF_TOK_EOL);
//      table_state = false;
//      continue;
//    }
//    if (table_state) {
//      i = -1;
//      do {
//	int num_columns = astring_vector_size(&columns);
//
//	i++;
//	inf_discard_tokens(inf, INF_TOK_EOL);  	/* allow newlines */
//	if (!(tok = inf_token_required(inf, INF_TOK_VALUE))) {
//          return false;
//        }
//
//	if (i < num_columns) {
//	  astr_minsize(&entry_name, base_name.n + 10 + columns.p[i].n);
//	  entry_name.str = String.format "%s%d.%s",
//		      base_name.str, table_lineno, columns.p[i].str);
//	} else {
//	  astr_minsize(&entry_name,
//		       base_name.n + 20 + columns.p[num_columns - 1].n);
//	  entry_name.str = String.format "%s%d.%s,%d",
//		      base_name.str, table_lineno,
//		      columns.p[num_columns - 1].str,
//		      (int) (i - num_columns + 1));
//	}
//	pentry = new_entry(sb, entry_name.str, tok);
//	entry_list_insert_back(&psection.entries, pentry);
//	sf.num_entries++;
//      } while(inf_token(inf, INF_TOK_COMMA));
//      
//      () inf_token_required(inf, INF_TOK_EOL);
//      table_lineno++;
//      continue;
//    }
//    
//    if (!(tok = inf_token_required(inf, INF_TOK_ENTRY_NAME))) {
//      return false;
//    }
//
//    /* need to store tok before next calls: */
//    astr_minsize(&base_name, tok.length()+1);
//    strcpy(base_name.str, tok);
//
//    inf_discard_tokens(inf, INF_TOK_EOL);  	/* allow newlines */
//    
//    if (inf_token(inf, INF_TOK_TABLE_START)) {
//      i = -1;
//      do {
//	i++;
//	inf_discard_tokens(inf, INF_TOK_EOL);  	/* allow newlines */
//	if (!(tok = inf_token_required(inf, INF_TOK_VALUE))) {
//          return false;
//        }
//	if( tok[0] != '\"' ) {
//	  inf_log(inf, Log.LOG_ERROR, "table column header non-string");
//          return false;
//	}
//	{ 	/* expand columns: */
//	  int j, n_prev;
//	  n_prev = astring_vector_size(&columns);
//	  for (j = i + 1; j < n_prev; j++) {
//	    astr_free(&columns.p[j]);
//	  }
//	  astring_vector_reserve(&columns, i + 1);
//	  for (j = n_prev; j < i + 1; j++) {
//	    astr_init(&columns.p[j]);
//	  }
//	}
//	astr_minsize(&columns.p[i], tok.length());
//	strcpy(columns.p[i].str, tok+1);
//	
//      } while(inf_token(inf, INF_TOK_COMMA));
//      
//      () inf_token_required(inf, INF_TOK_EOL);
//      table_state = true;
//      table_lineno=0;
//      continue;
//    }
//    /* ordinary value: */
//    i = -1;
//    do {
//      i++;
//      inf_discard_tokens(inf, INF_TOK_EOL);  	/* allow newlines */
//      if (!(tok = inf_token_required(inf, INF_TOK_VALUE))) {
//        return false;
//      }
//      if (i==0) {
//	pentry = new_entry(sb, base_name.str, tok);
//      } else {
//	astr_minsize(&entry_name, base_name.n + 20);
//	entry_name.str = String.format
//		    "%s,%d", base_name.str, i);
//	pentry = new_entry(sb, entry_name.str, tok);
//      }
//      entry_list_insert_back(&psection.entries, pentry);
//      sf.num_entries++;
//    } while(inf_token(inf, INF_TOK_COMMA));
//    () inf_token_required(inf, INF_TOK_EOL);
//  }
//  
//  if (table_state) {
//    if (filename) {
//      util.freelog(LOG_FATAL, "finished registry %s before end of table\n", filename);
//    } else {
//      util.freelog(LOG_FATAL, "finished registry before end of table\n");
//    }
//    exit(EXIT_FAILURE);
//  }
//
//  inf_close(inf);
//  inf = null;
//  
//  astr_free(&base_name);
//  astr_free(&entry_name);
//  for (i = 0; i < astring_vector_size(&columns); i++) {
//    astr_free(&columns.p[i]);
//  }
//  astring_vector_free(&columns);
//  
//  secfilehash_build(sf, allow_duplicates);
//    
//  return true;
//}
//
///**************************************************************************
//...
//**************************************************************************/
//boolean section_file_load(section_file my_section_file,
//		      final String filename)
//{
//  char real_filename[1024];
//  inputfile inf;
//
//  interpret_tilde(real_filename, sizeof(real_filename), filename);
//  inf = inf_from_file(real_filename, datafilename);
//
//  return section_file_read_dup(my_section_file, real_filename, inf, true);
//}
//
///**************************************************************************
//  Load a section_file, but disallow (util.die on) duplicate entries.
//**************************************************************************/
//boolean section_file_load_nodup(section_file my_section_file,
//			    final String filename)
//{
//  char real_filename[1024];
//  inputfile inf;
//
//  interpret_tilde(real_filename, sizeof(real_filename), filename);
//  inf = inf_from_file(real_filename, datafilename);
//
//  return section_file_read_dup(my_section_file, real_filename, inf, false);
//}
//
///**************************************************************************
//...
//**************************************************************************/
//boolean section_file_load_from_stream(section_file my_section_file,
//				   fz_FILE * stream)
//{
//  inputfile inf = inf_from_stream(stream, datafilename);
//
//  return section_file_read_dup(my_section_file, null, inf, true);
//}
//
///**************************************************************************
// Save the previously filled in section_file to disk.
// 
// There is now limited ability to save in the new tabular format
// (to give smaller savefiles).
// The start of a table is detected by an entry with name of the form:
//    (alphabetical_component)(zero)(period)(alphanumeric_component)
// Eg: u0.id, or c0.id, in the freeciv savefile.
// The alphabetical component is taken as the "name" of the table,
// and the component after the period as the first column name.
// This should be followed by the other column values for u0,
// and then subsequent u1, u2, etc, in strict order with no omissions,
// and with all of the columns for all uN in the same order as for u0.
//
// If compression_level is non-zero, then compress using zlib.  (Should
// only supply non-zero compression_level if already know that HAVE_LIBZ.)
// Below simply specifies FZ_ZLIB method, since fz_fromFile() automatically
// changes to FZ_PLAIN method when level==0.
//**************************************************************************/
//boolean section_file_save(section_file my_section_file,
//                       final String filename,
//		       int compression_level)
//{
//  char real_filename[1024];
//  fz_FILE *fs;
//  genlist_link ent_iter, *save_iter, *col_iter;
//  entry pentry, *col_pentry;
//  int i;
//  
//  interpret_tilde(real_filename, sizeof(real_filename), filename);
//  fs = fz_from_file(real_filename, "w", FZ_ZLIB, compression_level);
//
//  if (!fs)
//    return false;
//
//  section_list_iterate(*my_section_file.sections, psection) {
//    fz_fprintf(fs, "\n[%s]\n", psection.name);
//
//    /* Following doesn't use entry_list_iterate() because we want to do
//     * tricky things with the iterators...
//     */
//    for(ent_iter = psection.entries.list.head_link;
//	(pentry = ITERATOR_PTR(ent_iter));
//	ITERATOR_NEXT(ent_iter)) {
//
//      /* Tables: break out of this loop if this is a non-table
//       * entry (pentry and ent_iter unchanged) or after table (pentry
//       * and ent_iter suitably updated, pentry possibly null).
//       * After each table, loop again in case the next entry
//       * is another table.
//       */
//      for(;;) {
//	char *c, *first, base[64];
//	int offset, irow, icol, ncol;
//	
//	/* Example: for first table name of "xyz0.blah":
//	 *  first points to the original string pentry.name
//	 *  base contains "xyz";
//	 *  offset=5 (so first+offset gives "blah")
//	 *  note base.length()=offset-2
//	 */
//
//	if(!SAVE_TABLES) break;
//	
//	c = first = pentry.name;
//	if(*c == '\0' || !my_isalpha(*c)) break;
//	for (; *c != '\0' && my_isalpha(*c); c++) {
//	  /* nothing */
//	}
//	if(strncmp(c,"0.",2) != 0) break;
//	c+=2;
//	if(*c == '\0' || !my_isalnum(*c)) break;
//
//	offset = c - first;
//	first[offset-2] = '\0';
//	base = first;
//	first[offset-2] = '0';
//	fz_fprintf(fs, "%s={", base);
//
//	/* Save an iterator at this first entry, which we can later use
//	 * to repeatedly iterate over column names:
//	 */
//	save_iter = ent_iter;
//
//	/* write the column names, and calculate ncol: */
//	ncol = 0;
//	col_iter = save_iter;
//	for( ; (col_pentry = ITERATOR_PTR(col_iter)); ITERATOR_NEXT(col_iter)) {
//	  if(!col_pentry.name.equals(first))
//	    break;
//	  fz_fprintf(fs, "%c\"%s\"", (ncol==0?' ':','), col_pentry.name+offset);
//	  ncol++;
//	}
//	fz_fprintf(fs, "\n");
//
//	/* Iterate over rows and columns, incrementing ent_iter as we go,
//	 * and writing values to the table.  Have a separate iterator
//	 * to the column names to check they all match.
//	 */
//	irow = icol = 0;
//	col_iter = save_iter;
//	for(;;) {
//	  char expect[128];	/* pentry.name we're expecting */
//
//	  pentry = ITERATOR_PTR(ent_iter);
//	  col_pentry = ITERATOR_PTR(col_iter);
//
//	  expect = util.my_snprintf( "%s%d.%s",
//		      base, irow, col_pentry.name+offset);
//
//	  /* break out of tabular if doesn't match: */
//	  if((!pentry) || (strcmp(pentry.name, expect) != 0)) {
//	    if(icol != 0) {
//	      /* If the second or later row of a table is missing some
//	       * entries that the first row had, we drop out of the tabular
//	       * format.  This is inefficient so we print a warning message;
//	       * the calling code probably needs to be fixed so that it can
//	       * use the more efficient tabular format.
//	       *
//	       * FIXME: If the first row is missing some entries that the
//	       * second or later row has, then we'll drop out of tabular
//	       * format without an error message. */
//	      util.freelog(Log.LOG_ERROR,
//		      "In file %s, there is no entry in the registry for \n" +
//		      "%s (or the entries are out of order. This means a \n" +
//		      "less efficient non-tabular format will be used. To\n" +
//		      "avoid this make sure all rows of a table are filled\n" +
//		      "out with an entry for every column.  This is surely\n" +
//		      "a bug so if you're reading this message, report it\n" +
//		      "to bugs@freeciv.org.",
//		      real_filename, expect);
//	      fz_fprintf(fs, "\n");
//	    }
//	    fz_fprintf(fs, "}\n");
//	    break;
//	  }
//
//	  if(icol>0)
//	    fz_fprintf(fs, ",");
//	  if(pentry.svalue) 
//	    fz_fprintf(fs, "\"%s\"", moutstr(pentry.svalue));
//	  else
//	    fz_fprintf(fs, "%d", pentry.ivalue);
//	  
//	  ITERATOR_NEXT(ent_iter);
//	  ITERATOR_NEXT(col_iter);
//	  
//	  icol++;
//	  if(icol==ncol) {
//	    fz_fprintf(fs, "\n");
//	    irow++;
//	    icol = 0;
//	    col_iter = save_iter;
//	  }
//	}
//	if(!pentry) break;
//      }
//      if(!pentry) break;
//      
//      if (pentry.vec_values) {
//        fz_fprintf(fs, "%s=\"%s\"", pentry.name,
//	           moutstr(pentry.vec_values[0]));
//	for (i = 1; i < pentry.dim; i++) {
//	  fz_fprintf(fs, ", \"%s\"", moutstr(pentry.vec_values[i]));
//	}
//      } else if (pentry.svalue) {
//	fz_fprintf(fs, "%s=\"%s\"", pentry.name, moutstr(pentry.svalue));
//      } else {
//	fz_fprintf(fs, "%s=%d", pentry.name, pentry.ivalue);
//      }
//      
//      if (pentry.comment) {
//	fz_fprintf(fs, "  # %s\n", pentry.comment);
//      } else {
//	fz_fprintf(fs, "\n");
//      }
//    }
//  }
//  }
//  
//  () moutstr(null);		/* free internal buffer */
//
//  if (fz_ferror(fs) != 0) {
//    util.freelog(Log.LOG_ERROR, "Error before closing %s: %s", real_filename,
//	    fz_strerror(fs));
//    fz_fclose(fs);
//    return false;
//  }
//  if (fz_fclose(fs) != 0) {
//    util.freelog(Log.LOG_ERROR, "Error closing %s", real_filename);
//    return false;
//  }
//
//  return true;
//}
//
///**************************************************************************
//...
//**************************************************************************/
//char *secfile_lookup_str(section_file my_section_file, final String path)
//{
//  entry pentry;
//  char buf[MAX_LEN_BUFFER];
//  va_list ap;
//
//  va_start(ap, path);
//  my_vsnprintf(buf, sizeof(buf), path, ap);
//  va_end(ap);
//
//  if(!(pentry=section_file_lookup_internal(my_section_file, buf))) {
//    util.freelog(LOG_FATAL, "sectionfile %s doesn't contain a '%s' entry",
//	    secfile_filename(my_section_file), buf);
//    exit(EXIT_FAILURE);
//  }
//
//  if(!pentry.svalue) {
//    util.freelog(LOG_FATAL, "sectionfile %s entry '%s' doesn't contain a string",
//	    secfile_filename(my_section_file), buf);
//    exit(EXIT_FAILURE);
//  }
//  
//  return pentry.svalue;
//}
//
///**************************************************************************
//  Lookup string or int value; if (char*) return is null, int value is
//  put into (*ival).
//**************************************************************************/
//char *secfile_lookup_str_int(section_file my_section_file, 
//			     int *ival, final String path)
//{
//  entry pentry;
//  char buf[MAX_LEN_BUFFER];
//  va_list ap;
//
//  assert(ival != null);
//  
//  va_start(ap, path);
//  my_vsnprintf(buf, sizeof(buf), path, ap);
//  va_end(ap);
//
//  if(!(pentry=section_file_lookup_internal(my_section_file, buf))) {
//    util.freelog(LOG_FATAL, "sectionfile %s doesn't contain a '%s' entry",
//	    secfile_filename(my_section_file), buf);
//    exit(EXIT_FAILURE);
//  }
//
//  if(pentry.svalue) {
//    return pentry.svalue;
//  } else {
//    *ival = pentry.ivalue;
//    return null;
//  }
//}
//      
///**************************************************************************
//...
//**************************************************************************/
//void secfile_insert_int(section_file my_section_file,
//			int val, final String path)
//{
//  entry pentry;
//  char buf[MAX_LEN_BUFFER];
//  va_list ap;
//
//  va_start(ap, path);
//  my_vsnprintf(buf, sizeof(buf), path, ap);
//  va_end(ap);
//
//  pentry=section_file_insert_internal(my_section_file, buf);
//
//  pentry.ivalue=val;
//  pentry.svalue = null;
//  pentry.vec_values = null;
//  pentry.dim = 0;
//  pentry.comment = null;
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void secfile_insert_int_comment(section_file my_section_file,
//				int val, final String final comment,
//				final String path)
//{
//  entry pentry;
//  char buf[MAX_LEN_BUFFER];
//  va_list ap;
//
//  va_start(ap, path);
//  my_vsnprintf(buf, sizeof(buf), path, ap);
//  va_end(ap);
//
//  pentry = section_file_insert_internal(my_section_file, buf);
//
//  pentry.ivalue = val;
//  pentry.svalue = null;
//  pentry.vec_values = null;
//  pentry.dim = 0;
//  pentry.comment = sbuf_strdup(my_section_file.sb, comment);
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void secfile_insert_bool(section_file my_section_file,
//			 boolean val, final String path)
//{
//  entry pentry;
//  char buf[MAX_LEN_BUFFER];
//  va_list ap;
//
//  va_start(ap, path);
//  my_vsnprintf(buf, sizeof(buf), path, ap);
//  va_end(ap);
//
//  pentry=section_file_insert_internal(my_section_file, buf);
//
//  if (val != true && val != false) {
//    util.freelog(Log.LOG_ERROR, "Trying to insert a non-boolean (%d) at key %s",
//	    (int) val, buf);
//    val = true;
//  }
//
//  pentry.ivalue = val ? 1 : 0;
//  pentry.svalue = null;
//  pentry.vec_values = null;
//  pentry.dim = 0;
//  pentry.comment = null;
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void secfile_insert_str(section_file my_section_file,
//			final String sval, final String path)
//{
//  entry pentry;
//  char buf[MAX_LEN_BUFFER];
//  va_list ap;
//
//  va_start(ap, path);
//  my_vsnprintf(buf, sizeof(buf), path, ap);
//  va_end(ap);
//
//  pentry = section_file_insert_internal(my_section_file, buf);
//  pentry.svalue = sbuf_strdup(my_section_file.sb, sval);
//  pentry.vec_values = null;
//  pentry.dim = 0;
//  pentry.comment = null;
//}
//
///**************************************************************************
//...
//**************************************************************************/
//void secfile_insert_str_comment(section_file my_section_file,
//				char *sval, final String final comment,
//				final String path)
//{
//  entry pentry;
//  char buf[MAX_LEN_BUFFER];
//  va_list ap;
//
//  va_start(ap, path);
//  my_vsnprintf(buf, sizeof(buf), path, ap);
//  va_end(ap);
//
//  pentry = section_file_insert_internal(my_section_file, buf);
//  pentry.svalue = sbuf_strdup(my_section_file.sb, sval);
//  pentry.vec_values = null;
//  pentry.dim = 0;
//  pentry.comment = sbuf_strdup(my_section_file.sb, comment);
//}
//
///**************************************************************************
//  Insert string vector into section_file. It will be writen out as:
//    name = "value1", "value2", "value3"
//  The vector must have at least one element in it.
//
//  This function is little tricky, because values inserted here can't
//  be immediately recovered by secfile_lookup_str_vec. Luckily we never use
//  section_file for both reading and writing.
//**************************************************************************/
//void secfile_insert_str_vec(section_file my_section_file,
//			    final String*values, int dim,
//			    final String path)
//{
//  entry pentry;
//  char buf[MAX_LEN_BUFFER];
//  int i;
//  va_list ap;
//
//  va_start(ap, path);
//  my_vsnprintf(buf, sizeof(buf), path, ap);
//  va_end(ap);
//
//  assert(dim > 0);
//  
//  pentry = section_file_insert_internal(my_section_file, buf);
//  pentry.dim = dim;
//  pentry.vec_values = sbuf_malloc(my_section_file.sb,
//                                   sizeof(char*) * dim);
//  for (i = 0; i < dim; i++) {
//    pentry.vec_values[i] = sbuf_strdup(my_section_file.sb, values[i]);
//  }
//				 
//  pentry.svalue = null;
//  pentry.comment = null;
//}
//
///**************************************************************************
//...
//**************************************************************************/
//int secfile_lookup_int(section_file my_section_file, 
//		       final String path)
//{
//  entry pentry;
//  char buf[MAX_LEN_BUFFER];
//  va_list ap;
//
//  va_start(ap, path);
//  my_vsnprintf(buf, sizeof(buf), path, ap);
//  va_end(ap);
//
//  if(!(pentry=section_file_lookup_internal(my_section_file, buf))) {
//    util.freelog(LOG_FATAL, "sectionfile %s doesn't contain a '%s' entry",
//	    secfile_filename(my_section_file), buf);
//    exit(EXIT_FAILURE);
//  }
//
//  if(pentry.svalue) {
//    util.freelog(LOG_FATAL, "sectionfile %s entry '%s' doesn't contain an integer",
//	    secfile_filename(my_section_file), buf);
//    exit(EXIT_FAILURE);
//  }
//  
//  return pentry.ivalue;
//}
//
//
///**************************************************************************
//  As secfile_lookup_int(), but return a specified default value if the
//  entry does not exist.  If the entry exists as a string, then util.die.
//**************************************************************************/
//int secfile_lookup_int_default(section_file my_section_file,
//			       int def, final String path)
//{
//  entry pentry;
//  char buf[MAX_LEN_BUFFER];
//  va_list ap;
//
//  va_start(ap, path);
//  my_vsnprintf(buf, sizeof(buf), path, ap);
//  va_end(ap);
//
//  if(!(pentry=section_file_lookup_internal(my_section_file, buf))) {
//    return def;
//  }
//  if(pentry.svalue) {
//    util.freelog(LOG_FATAL, "sectionfile %s contains a '%s', but string not integer",
//	    secfile_filename(my_section_file), buf);
//    exit(EXIT_FAILURE);
//  }
//  return pentry.ivalue;
//}
//
///**************************************************************************
//...
//**************************************************************************/
//boolean secfile_lookup_bool(section_file my_section_file, 
//		       final String path)
//{
//  entry pentry;
//  char buf[MAX_LEN_BUFFER];
//  va_list ap;
//
//  va_start(ap, path);
//  my_vsnprintf(buf, sizeof(buf), path, ap);
//  va_end(ap);
//
//  if(!(pentry=section_file_lookup_internal(my_section_file, buf))) {
//    util.freelog(LOG_FATAL, "sectionfile %s doesn't contain a '%s' entry",
//	    secfile_filename(my_section_file), buf);
//    exit(EXIT_FAILURE);
//  }
//
//  if(pentry.svalue) {
//    util.freelog(LOG_FATAL, "sectionfile %s entry '%s' doesn't contain an integer",
//	    secfile_filename(my_section_file), buf);
//    exit(EXIT_FAILURE);
//  }
//
//  if (pentry.ivalue != 0 && pentry.ivalue != 1) {
//    util.freelog(Log.LOG_ERROR, "Value read for key %s isn't boolean: %d", buf,
//	    pentry.ivalue);
//    pentry.ivalue = 1;
//  }
//  
//  return pentry.ivalue != 0;
//}
//
//
///**************************************************************************
//  As secfile_lookup_bool(), but return a specified default value if the
//  entry does not exist.  If the entry exists as a string, then util.die.
//**************************************************************************/
//boolean secfile_lookup_bool_default(section_file my_section_file,
//				 boolean def, final String path)
//{
//  entry pentry;
//  char buf[MAX_LEN_BUFFER];
//  va_list ap;
//
//  va_start(ap, path);
//  my_vsnprintf(buf, sizeof(buf), path, ap);
//  va_end(ap);
//
//  if(!(pentry=section_file_lookup_internal(my_section_file, buf))) {
//    return def;
//  }
//  if(pentry.svalue) {
//    util.freelog(LOG_FATAL, "sectionfile %s contains a '%s', but string not integer",
//	    secfile_filename(my_section_file), buf);
//    exit(EXIT_FAILURE);
//  }
//
//  if (pentry.ivalue != 0 && pentry.ivalue != 1) {
//    util.freelog(Log.LOG_ERROR, "Value read for key %s isn't boolean: %d", buf,
//	    pentry.ivalue);
//    pentry.ivalue = 1;
//  }
//  
//  return pentry.ivalue != 0;
//}
//
///**************************************************************************
//  As secfile_lookup_str(), but return a specified default (char*) if the
//  entry does not exist.  If the entry exists as an int, then util.die.
//**************************************************************************/
//char *secfile_lookup_str_default(section_file my_section_file, 
//				 final String def, final String path)
//{
//  entry pentry;
//  char buf[MAX_LEN_BUFFER];
//  va_list ap;
//
//  va_start(ap, path);
//  my_vsnprintf(buf, sizeof(buf), path, ap);
//  va_end(ap);
//
//  if(!(pentry=section_file_lookup_internal(my_section_file, buf))) {
//    return (char *) def;
//  }
//
//  if(!pentry.svalue) {
//    util.freelog(LOG_FATAL, "sectionfile %s contains a '%s', but integer not string",
//	    secfile_filename(my_section_file), buf);
//    exit(EXIT_FAILURE);
//  }
//  
//  return pentry.svalue;
//}
//
///**************************************************************************
//...
//**************************************************************************/
//boolean section_file_lookup(section_file my_section_file, 
//			 final String path)
//{
//  char buf[MAX_LEN_BUFFER];
//  va_list ap;
//
//  va_start(ap, path);
//  my_vsnprintf(buf, sizeof(buf), path, ap);
//  va_end(ap);
//
//  return section_file_lookup_internal(my_section_file, buf) != null;
//}
//
//
///**************************************************************************
//...
//**************************************************************************/
//static struct entry*
//section_file_lookup_internal(section_file my_section_file,  
//			     char *fullpath) 
//{
//  char *pdelim;
//  char sec_name[MAX_LEN_BUFFER];
//  char ent_name[MAX_LEN_BUFFER];
//  char mod_fullpath[2*MAX_LEN_BUFFER];
//  int len;
//  entry result;
//  section psection;
//
//  /* util.freelog(Log.LOG_DEBUG, "looking up: %s", fullpath); */
//  
//  /* treat "sec.foo,0" as "sec.foo": */
//  len = fullpath.length();
//  if(len>2 && fullpath[len-2]==',' && fullpath[len-1]=='0') {
//    assert(len<sizeof(mod_fullpath));
//    strcpy(mod_fullpath, fullpath);
//    fullpath = mod_fullpath;	/* reassign local pointer 'fullpath' */
//    fullpath[len-2] = '\0';
//  }
//  
//  if (secfilehash_hashash(my_section_file)) {
//    result = hash_lookup_data(my_section_file.hashd.htbl, fullpath);
//    if (result) {
//      result.used++;
//    }
//    return result;
//  }
//
//  /* i dont like strtok */
//  pdelim = strchr(fullpath, '.');
//  if (!pdelim) {
//    return null;
//  }
//
//  () mystrlcpy(sec_name, fullpath,
//		   Math.min(pdelim - fullpath + 1, sizeof(sec_name)));
//  ent_name = String.format( pdelim+1);
//
//  psection = find_section_by_name(my_section_file, sec_name);
//  if (psection) {
//    for (entry pentry : psection.entries.data) {
//      if (pentry.name.equals(ent_name)) {
//	result = pentry;
//	result.used++;
//	return result;
//      }
//    } }
//  }
//
//  return null;
//}
//
//
///**************************************************************************
// The caller should ensure that "fullpath" should not refer to an entry
// which already exists in "my_section_file".  (Actually, in some cases
// now it is ok to have duplicate entries, but be careful...)
//**************************************************************************/
//static struct entry*
//section_file_insert_internal(section_file my_section_file, 
//			     char *fullpath)
//{
//  char *pdelim;
//  char sec_name[MAX_LEN_BUFFER];
//  char ent_name[MAX_LEN_BUFFER];
//  section psection;
//  entry pentry;
//  sbuffer sb = my_section_file.sb;
//
//  if(!(pdelim=strchr(fullpath, '.'))) { /* d dont like strtok */
//    util.freelog(LOG_FATAL,
//	    "Insertion fullpath \"%s\" missing '.' for sectionfile %s",
//	    fullpath, secfile_filename(my_section_file));
//    exit(EXIT_FAILURE);
//  }
//  () mystrlcpy(sec_name, fullpath,
//		   Math.min(pdelim - fullpath + 1, sizeof(sec_name)));
//  ent_name = String.format( pdelim+1);
//  my_section_file.num_entries++;
//  
//  if(sec_name.length()==0 || ent_name.length()==0) {
//    util.freelog(LOG_FATAL,
//	    "Insertion fullpath \"%s\" missing %s for sectionfile %s",
//	    fullpath, (sec_name.length()==0 ? "section" : "entry"),
//	    secfile_filename(my_section_file));
//    exit(EXIT_FAILURE);
//  }
//
//  psection = find_section_by_name(my_section_file, sec_name);
//  if (psection) {
//    /* This DOES NOT check whether the entry already exists in
//     * the section, to avoid O(N^2) behaviour.
//     */
//    pentry = sbuf_malloc(sb, sizeof(struct entry));
//    pentry.name = sbuf_strdup(sb, ent_name);
//    entry_list_insert_back(&psection.entries, pentry);
//    return pentry;
//  }
//
//  psection = sbuf_malloc(sb, sizeof(struct section));
//  psection.name = sbuf_strdup(sb, sec_name);
//  entry_list_init(&psection.entries);
//  section_list_insert_back(my_section_file.sections, psection);
//  
//  pentry = sbuf_malloc(sb, sizeof(struct entry));
//  pentry.name = sbuf_strdup(sb, ent_name);
//  entry_list_insert_back(&psection.entries, pentry);
//
//  return pentry;
//}
//
//
///**************************************************************************
// Return 0 if the section_file has not been setup for hashing.
//**************************************************************************/
//boolean secfilehash_hashash(section_file file)
//{
//  return (file.hashd && hash_num_buckets(file.hashd.htbl) != 0);
//}
//
///**************************************************************************
//  Basic checks for existence/integrity of hash data and fail if bad.
//**************************************************************************/
//static void secfilehash_check(section_file file)
//{
//  if (!secfilehash_hashash(file)) {
//    util.freelog(LOG_FATAL, "sectionfile %s hash operation before setup",
//	    secfile_filename(file));
//    exit(EXIT_FAILURE);
//  }
//  if (file.num_entries != file.hashd.num_entries_hashbuild) {
//    util.freelog(LOG_FATAL, "sectionfile %s has more entries than when hash built",
//	    secfile_filename(file));
//    exit(EXIT_FAILURE);
//  }
//}
//
///**************************************************************************
// Insert a entry into the hash table.  The key is malloced here (using sbuf;
// malloc somewhere required by hash implementation).
//**************************************************************************/
//static void secfilehash_insert(section_file file,
//			       char *key, entry data)
//{
//  entry hentry;
//
//  key = sbuf_strdup(file.sb, key);
//  hentry = hash_replace(file.hashd.htbl, key, data);
//  if (hentry) {
//    if (file.hashd.allow_duplicates) {
//      hentry.used = 1;
//      file.hashd.num_duplicates++;
//      /* Subsequent entries replace earlier ones; could do differently so
//	 that first entry would be used and following ones ignored.
//	 Need to mark the replaced one as used or else it will show
//	 up when we iterate the sections and entries (since hash
//	 lookup will never find it to mark it as used).
//      */
//    } else {
//      util.freelog(LOG_FATAL, "Tried to insert same value twice: %s (sectionfile %s)",
//	      key, secfile_filename(file));
//      exit(EXIT_FAILURE);
//    }
//  }
//}
//
///**************************************************************************
// Build a hash table for the file.  Note that the section_file should
// not be modified (except to free it) subsequently.
// If allow_duplicates is true, then relax normal condition that
// all entries must have unique names; in this case for duplicates
// the hash ref will be to the _last_ entry.
//**************************************************************************/
//void secfilehash_build(section_file file, boolean allow_duplicates)
//{
//  hash_data hashd;
//  char buf[256];
//
//  hashd = file.hashd = fc_malloc(sizeof(struct hash_data));
//  hashd.htbl = hash_new_nentries(hash_fval_string, hash_fcmp_string,
//				  file.num_entries);
//  
//  hashd.num_entries_hashbuild = file.num_entries;
//  hashd.allow_duplicates = allow_duplicates;
//  hashd.num_duplicates = 0;
//  
//  section_list_iterate(*file.sections, psection) {
//    for (entry pentry : psection.entries.data) {
//      buf = util.my_snprintf( "%s.%s", psection.name, pentry.name);
//      secfilehash_insert(file, buf, pentry);
//    }
//    }
//  }
//  }
//  
//  if (hashd.allow_duplicates) {
//    util.freelog(Log.LOG_DEBUG, "Hash duplicates during build: %d",
//	    hashd.num_duplicates);
//  }
//}
//
//
///**************************************************************************
// Free the memory allocated for the hash table.
//**************************************************************************/
//void secfilehash_free(section_file file)
//{
//  secfilehash_check(file);
//  hash_free(file.hashd.htbl);
//  free(file.hashd);
//  file.hashd = null;
//}
//
///**************************************************************************
// Returns the number of elements in a "vector".
// That is, returns the number of consecutive entries in the sequence:
// "path,0" "path,1", "path,2", ...
// If none, returns 0.
//**************************************************************************/
//int secfile_lookup_vec_dimen(section_file my_section_file, 
//			     final String path)
//{
//  char buf[MAX_LEN_BUFFER];
//  va_list ap;
//  int j=0;
//
//  va_start(ap, path);
//  my_vsnprintf(buf, sizeof(buf), path, ap);
//  va_end(ap);
//
//  while(section_file_lookup(my_section_file, "%s,%d", buf, j)) {
//    j++;
//  }
//  return j;
//}
//
///**************************************************************************
// Return a pointer for a list of integers for a "vector".
// The return value is malloced here, and should be freed by the user.
// The size of the returned list is returned in (*dimen).
// If the vector does not exist, returns null ands sets (*dimen) to 0.
//**************************************************************************/
//int *secfile_lookup_int_vec(section_file my_section_file,
//			    int *dimen, final String path)
//{
//  char buf[MAX_LEN_BUFFER];
//  va_list ap;
//  int j, *res;
//
//  va_start(ap, path);
//  my_vsnprintf(buf, sizeof(buf), path, ap);
//  va_end(ap);
//
//  *dimen = secfile_lookup_vec_dimen(my_section_file, buf);
//  if (*dimen == 0) {
//    return null;
//  }
//  res = fc_malloc((*dimen)*sizeof(int));
//  for(j=0; j<(*dimen); j++) {
//    res[j] = secfile_lookup_int(my_section_file, "%s,%d", buf, j);
//  }
//  return res;
//}
//
///**************************************************************************
// Return a pointer for a list of "strings" for a "vector".
// The return value is malloced here, and should be freed by the user,
// but the strings themselves are contained in the sectionfile
// (as when a single string is looked up) and should not be altered
// or freed by the user.
// The size of the returned list is returned in (*dimen).
// If the vector does not exist, returns null ands sets (*dimen) to 0.
//**************************************************************************/
//char **secfile_lookup_str_vec(section_file my_section_file,
//			      int *dimen, final String path)
//{
//  char buf[MAX_LEN_BUFFER];
//  va_list ap;
//  int j;
//  char **res;
//
//  va_start(ap, path);
//  my_vsnprintf(buf, sizeof(buf), path, ap);
//  va_end(ap);
//
//  *dimen = secfile_lookup_vec_dimen(my_section_file, buf);
//  if (*dimen == 0) {
//    return null;
//  }
//  res = fc_malloc((*dimen)*sizeof(char*));
//  for(j=0; j<(*dimen); j++) {
//    res[j] = secfile_lookup_str(my_section_file, "%s,%d", buf, j);
//  }
//  return res;
//}
//
///***************************************************************
// Copies a string and does '\n' . newline translation.
// Other '\c' sequences (any character 'c') are just passed
// through with the '\' removed (eg, includes '\\', '\"')
// Backslash followed by a genuine newline removes the newline.
//***************************************************************/
//static char *minstrdup(sbuffer sb, final String str)
//{
//  char *dest = sbuf_malloc(sb, str.length()+1);
//  char *d2=dest;
//  if(dest) {
//    while (*str != '\0') {
//      if (*str=='\\') {
//	str++;
//	if (*str=='\\') {
//	  *dest++='\\';
//	  str++;
//	} else if (*str=='n') {
//	  *dest++='\n';
//	  str++;
//	} else if (*str=='\n') {
//	  /* skip */
//	  str++;
//	}
//      } else {
//	*dest++=*str++;
//      }
//
//    }
//
//    *dest='\0';
//  }
//  return d2;
//}
//
///***************************************************************
// Returns a pointer to an internal buffer (can only get one
// string at a time) with str escaped the opposite of minstrdup.
// Specifically, any newline, backslash, or double quote is
// escaped with a backslash.
// The internal buffer is grown as necessary, and not normally
// freed (since this will be called frequently.)  A call with
// str=null frees the buffer and does nothing else (returns null).
//***************************************************************/
//static char *moutstr(char *str)
//{
//  static char *buf = null;
//  static int nalloc = 0;
//
//  int len;			/* required length, including terminator */
//  char *c, *dest;
//
//  if (!str) {
//    util.freelog(Log.LOG_DEBUG, "moutstr alloc was %d", nalloc);
//    free(buf);
//    buf = null;
//    nalloc = 0;
//    return null;
//  }
//  
//  len = str.length()+1;
//  for(c=str; *c != '\0'; c++) {
//    if (*c == '\n' || *c == '\\' || *c == '\"') {
//      len++;
//    }
//  }
//  if (len > nalloc) {
//    nalloc = 2 * len + 1;
//    buf = fc_realloc(buf, nalloc);
//  }
//  
//  dest = buf;
//  while(*str != '\0') {
//    if (*str == '\n' || *str == '\\' || *str == '\"') {
//      *dest++ = '\\';
//      if (*str == '\n') {
//	*dest++ = 'n';
//	str++;
//      } else {
//	*dest++ = *str++;
//      }
//    } else {
//      *dest++ = *str++;
//    }
//  }
//  *dest = '\0';
//  return buf;
//}
//
///***************************************************************
//  Returns pointer to list of strings giving all section names
//  which start with prefix, and sets the number of such sections
//  in (*num).  If there are none such, returns null and sets
//  (*num) to zero.  The returned pointer is malloced, and it is
//  the responsibilty of the caller to free this pointer, but the
//  actual strings pointed to are part of the section_file data,
//  and should not be freed by the caller (nor used after the
//  section_file has been freed or changed).  The section names
//  returned are in the order they appeared in the original file.
//***************************************************************/
//char **secfile_get_secnames_prefix(section_file my_section_file,
//				   final String prefix, int *num)
//{
//  char **ret;
//  int len, i;
//
//  len = prefix.length();
//
//  /* count 'em: */
//  i = 0;
//  section_list_iterate(*my_section_file.sections, psection) {
//    if (!psection.name.equals(prefix)) {
//      i++;
//    }
//  }
//  }
//  (*num) = i;
//
//  if (i==0) {
//    return null;
//  }
//  
//  ret = fc_malloc((*num)*sizeof(char*));
//
//  i = 0;
//  section_list_iterate(*my_section_file.sections, psection) {
//    if (!psection.name.equals(prefix)) {
//      ret[i++] = psection.name;
//    }
//  }
//  }
//  return ret;
//}
//
///****************************************************************************
//  Returns a pointer to a list of strings giving all keys in the given section
//  and sets the number of such sections in (*num).  If there are no keys
//  found, the function returns null and sets (*num) to zero.  The returned
//  pointer is malloced, and it is the responsibilty of the caller to free this
//  pointer (unless it's null), but the actual strings pointed to are part of
//  the section_file data, and should not be freed by the caller (nor used
//  after the section_file has been freed or changed, so the caller may need to
//  strdup them to keep them around).  The order of the returned names is
//  unspecified.
//****************************************************************************/
//char **secfile_get_section_entries(section_file my_section_file,
//				   final String section, int *num)
//{
//  char **ret;
//  int i;
//  section psection = find_section_by_name(my_section_file,section);
//
//  if (!psection) {
//    *num = 0;
//    return null;
//  }
//
//  *num = psection.entries.foo_list_size();
//
//  if (*num == 0) {
//    return null;
//  }
//
//  ret = fc_malloc((*num) * sizeof(*ret));
//
//  i = 0;  
//  for (entry pentry : psection.entries.data) {
//    ret[i++] = pentry.name;
//  } }
//
//  return ret;
//}
}