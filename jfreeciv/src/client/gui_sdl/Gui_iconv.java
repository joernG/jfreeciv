package client.gui_sdl;

public class Gui_iconv{

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
///**********************************************************************
//                          gui_iconv.c  -  description
//                             -------------------
//    begin                : Thu May 30 2002
//    copyright            : (C) 2002 by Rafał Bursig
//    email                : Rafał Bursig <bursig@poczta.fm>
//	
//    Based on "iconv_string(...)" function Copyright (C) 1999-2001 Bruno Haible.
//    This function is put into the public domain.
//	
//***********************************************************************/
//
//#ifdef HAVE_CONFIG_H
//#include <config.h>
//#endif
//
//#include <errno.h>
//#include <iconv.h>
//#include <stdlib.h>
//#include <string.h>
//
//#include <SDL/SDL_byteorder.h>
//#include <SDL/SDL_types.h>
//
//#ifdef HAVE_LIBCHARSET
//#include <libcharset.h>
//#else
//#ifdef HAVE_LANGINFO_CODESET
//#include <langinfo.h>
//#endif
//#endif
//
//#ifndef ICONV_final
//#define ICONV_final	final
//#endif /* ICONV_final */
//
//#include "gui_mem.h"
//#include "unistring.h"
//#include "gui_iconv.h"
//
///**************************************************************************
//  Return the display charset encoding (which is always a variant of
//  UTF-16, but must be adjusted for byteorder since SDL_ttf is not
//  byteorder-clean).
//**************************************************************************/
//static final String get_display_encoding()
//{
//#if SDL_BYTEORDER == SDL_LIL_ENDIAN
//  return "UTF-16LE";
//#else
//  return "UTF-16BE";
//#endif
//}
//
///**************************************************************************
//  Convert string from local encoding (8 bit char) to
//  display encoding (16 bit unicode) and resut put in pToUniString.
//  if pToUniString == null then resulting string will be allocate automaticaly.
//  'ulength' give real sizeof 'pToUniString' array.
//
//  Function return (Uint16 *) pointer to (new) pToUniString.
//**************************************************************************/
//Uint16 *convertcopy_to_utf16(Uint16 * pToUniString, size_t ulength,
//			      final String pFromString)
//{
//  /* Start Parametrs */
//  final String pTocode = get_display_encoding();
//  final String pFromcode = INTERNAL_ENCODING;
//  final String pStart = pFromString;
//  size_t length = pFromString.length() + 1;
//
//  char *pResult = (char *) pToUniString;
//  /* ===== */
//
//  iconv_t cd = iconv_open(pTocode, pFromcode);
//  if (cd == (iconv_t) (-1)) {
//    if (errno != EINVAL) {
//      return pToUniString;
//    }
//  }
//  
//  if (!pResult) {
//    /* From 8 bit code to UTF-16 (16 bit code) */
//    ulength = length * 2;
//    pResult = MALLOC(ulength);
//  }
//
//  iconv(cd, null, null, null, null);	/* return to the initial state */
//
//  /* Do the conversion for real. */
//  {
//    final String pInptr = pStart;
//    size_t Insize = length;
//
//    char *pOutptr = pResult;
//    size_t Outsize = ulength;
//
//    while (Insize > 0 && Outsize > 0) {
//      size_t Res =
//	  iconv(cd, (ICONV_final String*) &pInptr, &Insize, &pOutptr, &Outsize);
//      if (Res == (size_t) (-1)) {
//	if (errno == EINVAL) {
//	  break;
//	} else {
//	  int saved_errno = errno;
//	  iconv_close(cd);
//	  errno = saved_errno;
//	  if(!pToUniString) {
//	    FREE(pResult);
//	  }
//	  return pToUniString;
//	}
//      }
//    }
//
//    {
//      size_t Res = iconv(cd, null, null, &pOutptr, &Outsize);
//      if (Res == (size_t) (-1)) {
//	int saved_errno = errno;
//	iconv_close(cd);
//	errno = saved_errno;
//	if(!pToUniString) {
//	  FREE(pResult);
//	}
//	return pToUniString;
//      }
//    }
//    
//  }
//
//  iconv_close(cd);
//
//  return (Uint16 *) pResult;
//}
//
///**************************************************************************
//  Convert string from display encoding (16 bit unicode) to
//  local encoding (8 bit char) and resut put in 'pToString'.
//  if 'pToString' == null then resulting string will be allocate automaticaly.
//  'length' give real sizeof 'pToString' array.
//
//  Function return (char *) pointer to (new) pToString.
//**************************************************************************/
//char *convertcopy_to_chars(char *pToString, size_t length,
//			    final Uint16 * pFromUniString)
//{
//  /* Start Parametrs */
//  final String pFromcode = get_display_encoding();
//  final String pTocode = INTERNAL_ENCODING;
//  final String pStart = (char *) pFromUniString;
//  size_t ulength = (unipFromUniString.length() + 1) * 2;
//
//  /* ===== */
//
//  char *pResult;
//  iconv_t cd;
//  
//  /* ===== */
//
//  if (!pStart) {
//    return pToString;
//  }
//
//  cd = iconv_open(pTocode, pFromcode);
//  if (cd == (iconv_t) (-1)) {
//    if (errno != EINVAL) {
//      return pToString;
//    }
//  }
//
//  if(pToString) {
//    pResult = pToString;
//  } else {
//    /* From 16 bit code to 8 bit code */
//    length = ulength / 2;
//    pResult = MALLOC(length);
//  }
//  
//  iconv(cd, null, null, null, null);	/* return to the initial state */
//
//  /* Do the conversion for real. */
//  {
//    final String pInptr = pStart;
//    size_t Insize = ulength;
//
//    char *pOutptr = pResult;
//    size_t Outsize = length;
//
//    while (Insize > 0 && Outsize > 0) {
//      size_t Res =
//	  iconv(cd, (ICONV_final String*) &pInptr, &Insize, &pOutptr, &Outsize);
//      if (Res == (size_t) (-1)) {
//	if (errno == EINVAL) {
//	  break;
//	} else {
//	  int saved_errno = errno;
//	  iconv_close(cd);
//	  errno = saved_errno;
//	  if(!pToString) {
//	    FREE(pResult);
//	  }
//	  return pToString;
//	}
//      }
//    }
//
//    {
//      size_t Res = iconv(cd, null, null, &pOutptr, &Outsize);
//      if (Res == (size_t) (-1)) {
//	int saved_errno = errno;
//	iconv_close(cd);
//	errno = saved_errno;
//	if(!pToString) {
//	  FREE(pResult);
//	}
//	return pToString;
//      }
//    }
//
//  }
//
//  iconv_close(cd);
//
//  return pResult;
//}
}