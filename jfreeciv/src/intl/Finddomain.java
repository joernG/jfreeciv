package intl;

public class Finddomain{
///* Handle list of needed message catalogs
//   Copyright (C) 1995-1999, 2000, 2001 Free Software Foundation, Inc.
//   Written by Ulrich Drepper <drepper@gnu.org>, 1995.
//
//   This program is free software; you can redistribute it and/or modify
//   it under the terms of the GNU General Public License as published by
//   the Free Software Foundation; either version 2, or (at your option)
//   any later version.
//
//   This program is distributed in the hope that it will be useful,
//   but WITHOUT ANY WARRANTY; without even the implied warranty of
//   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//   GNU General Public License for more details.
//
//   You should have received a copy of the GNU General Public License
//   along with this program; if not, write to the Free Software Foundation,
//   Inc., 59 Temple Place - Suite 330, Boston, MA 02111-1307, USA.  */
//
//#ifdef HAVE_CONFIG_H
//# include <config.h>
//#endif
//
//#include <stdio.h>
//#include <sys/types.h>
//#include <stdlib.h>
//#include <string.h>
//
//#if defined HAVE_UNISTD_H || defined _LIBC
//# include <unistd.h>
//#endif
//
//#include "gettextP.h"
//#ifdef _LIBC
//# include <libintl.h>
//#else
//# include "libgnuintl.h"
//#endif
//
///* @@ end of prolog @@ */
///* List of already loaded domains.  */
//static loaded_l10nfile _nl_loaded_domains;
//
//
///* Return a data structure describing the message catalog described by
//   the DOMAINNAME and CATEGORY parameters with respect to the currently
//   established bindings.  */
//loaded_l10nfile 
//internal_function
//_nl_find_domain (dirname, locale, domainname, domainbinding)
//     final String dirname;
//     char *locale;
//     final String domainname;
//     binding domainbinding;
//{
//  loaded_l10nfile retval;
//  final String language;
//  final String modifier;
//  final String territory;
//  final String codeset;
//  final String normalized_codeset;
//  final String special;
//  final String sponsor;
//  final String revision;
//  final String alias_value;
//  int mask;
//
//  /* LOCALE can consist of up to four recognized parts for the XPG syntax:
//
//		language[_territory[.codeset]][@modifier]
//
//     and six parts for the CEN syntax:
//
//	language[_territory][+audience][+special][,[sponsor][_revision]]
//
//     Beside the first part all of them are allowed to be missing.  If
//     the full specified locale is not found, the less specific one are
//     looked for.  The various parts will be stripped off according to
//     the following order:
//		(1) revision
//		(2) sponsor
//		(3) special
//		(4) codeset
//		(5) normalized codeset
//		(6) territory
//		(7) audience/modifier
//   */
//
//  /* If we have already tested for this locale entry there has to
//     be one data set in the list of loaded domains.  */
//  retval = _nl_make_l10nflist (&_nl_loaded_domains, dirname,
//			       strlen (dirname) + 1, 0, locale, null, null,
//			       null, null, null, null, null, domainname, 0);
//  if (retval != null)
//    {
//      /* We know something about this locale.  */
//      int cnt;
//
//      if (retval.decided == 0)
//	_nl_load_domain (retval, domainbinding);
//
//      if (retval.data != null)
//	return retval;
//
//      for (cnt = 0; retval.successor[cnt] != null; ++cnt)
//	{
//	  if (retval.successor[cnt].decided == 0)
//	    _nl_load_domain (retval.successor[cnt], domainbinding);
//
//	  if (retval.successor[cnt].data != null)
//	    break;
//	}
//      return cnt >= 0 ? retval : null;
//      /* NOTREACHED */
//    }
//
//  /* See whether the locale value is an alias.  If yes its value
//     *overwrites* the alias name.  No test for the original value is
//     done.  */
//  alias_value = _nl_expand_alias (locale);
//  if (alias_value != null)
//    {
//#if defined _LIBC || defined HAVE_STRDUP
//      locale = strdup (alias_value);
//      if (locale == null)
//	return null;
//#else
//      size_t len = strlen (alias_value) + 1;
//      locale = (char *) malloc (len);
//      if (locale == null)
//	return null;
//
//      memcpy (locale, alias_value, len);
//#endif
//    }
//
//  /* Now we determine the single parts of the locale name.  First
//     look for the language.  Termination symbols are `_' and `@' if
//     we use XPG4 style, and `_', `+', and `,' if we use CEN syntax.  */
//  mask = _nl_explode_name (locale, &language, &modifier, &territory,
//			   &codeset, &normalized_codeset, &special,
//			   &sponsor, &revision);
//
//  /* Create all possible locale entries which might be interested in
//     generalization.  */
//  retval = _nl_make_l10nflist (&_nl_loaded_domains, dirname,
//			       strlen (dirname) + 1, mask, language, territory,
//			       codeset, normalized_codeset, modifier, special,
//			       sponsor, revision, domainname, 1);
//  if (retval == null)
//    /* This means we are out of core.  */
//    return null;
//
//  if (retval.decided == 0)
//    _nl_load_domain (retval, domainbinding);
//  if (retval.data == null)
//    {
//      int cnt;
//      for (cnt = 0; retval.successor[cnt] != null; ++cnt)
//	{
//	  if (retval.successor[cnt].decided == 0)
//	    _nl_load_domain (retval.successor[cnt], domainbinding);
//	  if (retval.successor[cnt].data != null)
//	    break;
//	}
//    }
//
//  /* The room for an alias was dynamically allocated.  Free it now.  */
//  if (alias_value != null)
//    free (locale);
//
//  /* The space for normalized_codeset is dynamically allocated.  Free it.  */
//  if (mask & XPG_NORM_CODESET)
//    free ((void *) normalized_codeset);
//
//  return retval;
//}
//
//
//#ifdef _LIBC
//static void __attribute__ ((unused))
//free_mem ()
//{
//  loaded_l10nfile runp = _nl_loaded_domains;
//
//  while (runp != null)
//    {
//      loaded_l10nfile here = runp;
//      if (runp.data != null)
//	_nl_unload_domain ((loaded_domain ) runp.data);
//      runp = runp.next;
//      free ((char *) here.filename);
//      free (here);
//    }
//}
//
//text_set_element (__libc_subfreeres, free_mem);
//#endif
}