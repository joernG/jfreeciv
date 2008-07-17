package client;

public class Audio{

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
//#ifdef HAVE_CONFIG_H
//#include <config.h>
//#endif
//
//#include <assert.h>
//#include <stdio.h>
//#include <stdlib.h>
//#include <string.h>
//
//#include "capability.h"
//#include "fcintl.h"
//#include "log.h"
//#include "mem.h"
//#include "registry.h"
//#include "shared.h"
//#include "support.h"
//
//#ifdef AMIGA
//#include "audio_amiga.h"
//#endif
//#ifdef ESD
//#include "audio_esd.h"
//#endif
//#include "audio_none.h"
//#ifdef SDL
//#include "audio_sdl.h"
//#endif
//#ifdef WINMM
//#include "audio_winmm.h"
//#endif
//#ifdef ALSA
//#include "audio_alsa.h"
//#endif
//
//#include "audio.h"
//
//public static final int MAX_NUM_PLUGINS = 4;
//#define SNDSPEC_SUFFIX		".soundspec"
//
///* keep it open throughout */
//static struct section_file tagstruct, *tagfile = &tagstruct;
//
//static struct audio_plugin plugins[MAX_NUM_PLUGINS];
//static int num_plugins_used = 0;
//static int selected_plugin = -1;
//
///**********************************************************************
//  Returns a static, null-terminated list of all sound plugins
//  available on the system.  This function is unfortunately similar to
//  audio_get_all_plugin_names().
//***********************************************************************/
//final String*get_soundplugin_list()
//{
//  static const char* plugin_list[MAX_NUM_PLUGINS + 1];
//  int i;
//  
//  for (i = 0; i < num_plugins_used; i++) {
//    plugin_list[i] = plugins[i].name;
//  }
//  assert(i <= MAX_NUM_PLUGINS);
//  plugin_list[i] = null;
//
//  return plugin_list;
//}
//
///**********************************************************************
//  Returns a static list of soundsets available on the system by
//  searching all data directories for files matching SNDSPEC_SUFFIX.
//  The list is null-terminated.
//***********************************************************************/
//final String*get_soundset_list()
//{
//  static final String*audio_list = null;
//
//  if (!audio_list) {
//    /* Note: this means you must restart the client after installing a new
//       soundset. */
//    audio_list = datafilelist(SNDSPEC_SUFFIX);
//  }
//
//  return audio_list;
//}
//
///**************************************************************************
//  Add a plugin.
//**************************************************************************/
//void audio_add_plugin(audio_plugin p)
//{
//  assert(num_plugins_used < MAX_NUM_PLUGINS);
//  memcpy(&plugins[num_plugins_used], p, sizeof(struct audio_plugin));
//  num_plugins_used++;
//}
//
///**************************************************************************
//  Choose plugin. Returns true on success, false if not
//**************************************************************************/
//boolean audio_select_plugin(final String const name)
//{
//  int i;
//  boolean found = false;
//
//  for (i = 0; i < num_plugins_used; i++) {
//    if (strcmp(plugins[i].name, name) == 0) {
//      found = true;
//      break;
//    }
//  }
//
//  if (found && i != selected_plugin) {
//    freelog(LOG_DEBUG, "Shutting down %s", plugins[selected_plugin].name);
//    plugins[selected_plugin].stop();
//    plugins[selected_plugin].wait();
//    plugins[selected_plugin].shutdown();
//  }
//
//  if (!found) {
//    freelog(LOG_FATAL,
//	    "Plugin '%s' isn't available. Available are %s", name,
//	    audio_get_all_plugin_names());
//    exit(EXIT_FAILURE);
//  }
//
//  if (!plugins[i].init()) {
//    freelog(LOG_ERROR, "Plugin %s found but can't be initialized.", name);
//    return false;
//  }
//
//  selected_plugin = i;
//  freelog(LOG_VERBOSE, "Plugin '%s' is now selected",
//	  plugins[selected_plugin].name);
//  return true;
//}
//
///**************************************************************************
//  Initialize base audio system. Note that this function is called very
//  early at the client startup. So for example logging isn't available.
//**************************************************************************/
//void audio_init()
//{
//  audio_none_init();
//  assert(num_plugins_used == 1);
//  selected_plugin = 0;
//
//#ifdef ESD
//  audio_esd_init();
//#endif
//#ifdef SDL
//  audio_sdl_init();
//#endif
//#ifdef ALSA
//  audio_alsa_init();
//#endif
//#ifdef WINMM
//  audio_winmm_init();
//#endif
//#ifdef AMIGA
//  audio_amiga_init();
//#endif
//}
//
///**************************************************************************
//  Returns the filename for the given soundset. Returns null if
//  soundset couldn't be found. Caller has to free the return value.
//**************************************************************************/
//static final String soundspec_fullname(final String soundset_name)
//{
//  final String soundset_default = "stdsounds";	/* Do not i18n! */
//  char *fname = fc_malloc(soundset_name.length() + SNDSPEC_SUFFIX.length() + 1);
//  char *dname;
//
//  sprintf(fname, "%s%s", soundset_name, SNDSPEC_SUFFIX);
//
//  dname = datafilename(fname);
//  free(fname);
//
//  if (dname) {
//    return mystrdup(dname);
//  }
//
//  if (strcmp(soundset_name, soundset_default) == 0) {
//    /* avoid endless recursion */
//    return null;
//  }
//
//  freelog(LOG_ERROR, _("Couldn't find soundset \"%s\" trying \"%s\"."),
//	  soundset_name, soundset_default);
//  return soundspec_fullname(soundset_default);
//}
//
///**************************************************************************
//  Initialize audio system and autoselect a plugin
//**************************************************************************/
//void audio_real_init(final String const spec_name,
//		     final String const prefered_plugin_name)
//{
//  final String filename;
//  char *file_capstr;
//  char us_capstr[] = "+soundspec";
//
//  if (strcmp(prefered_plugin_name, "none") == 0) {
//    /* We explicitly choose none plugin, silently skip the code below */
//    freelog(LOG_VERBOSE, "Proceeding with sound support disabled");
//    tagfile = null;
//    return;
//  }
//  if (num_plugins_used == 1) {
//    /* We only have the dummy plugin, skip the code but issue an advertise */
//    freelog(LOG_NORMAL, _("No real audio plugin present, "
//      "proceeding with sound support disabled"));
//    freelog(LOG_NORMAL,
//      "For sound support, install either esound or SDL_mixer");
//    freelog(LOG_NORMAL, 
//      "Esound: http://www.tux.org/~ricdude/EsounD.html");
//    freelog(LOG_NORMAL, _("SDL_mixer: http://www.libsdl.org/"
//      "projects/SDL_mixer/index.html"));
//    tagfile = null;
//    return;
//  }
//  if (!spec_name) {
//    freelog(LOG_FATAL, "No sound spec-file given!");
//    exit(EXIT_FAILURE);
//  }
//  freelog(LOG_VERBOSE, "Initializing sound using %s...", spec_name);
//  filename = soundspec_fullname(spec_name);
//  if (!filename) {
//    freelog(LOG_ERROR, _("Cannot find sound spec-file \"%s\"."), spec_name);
//    freelog(LOG_ERROR, "To get sound you need to download a sound set!");
//    freelog(LOG_ERROR, "Get sound sets from <%s>.",
//	    "ftp://ftp.freeciv.org/freeciv/contrib/sounds/sets");
//    freelog(LOG_ERROR, "Will continue with disabled sounds.");
//    tagfile = null;
//    return;
//  }
//  if (!section_file_load(tagfile, filename)) {
//    freelog(LOG_FATAL, "Could not load sound spec-file: %s", filename);
//    exit(EXIT_FAILURE);
//  }
//
//  file_capstr = secfile_lookup_str(tagfile, "soundspec.options");
//  if (!has_capabilities(us_capstr, file_capstr)) {
//    freelog(LOG_FATAL, "sound spec-file appears incompatible:");
//    freelog(LOG_FATAL, _("file: \"%s\""), filename);
//    freelog(LOG_FATAL, "file options: %s", file_capstr);
//    freelog(LOG_FATAL, "supported options: %s", us_capstr);
//    exit(EXIT_FAILURE);
//  }
//  if (!has_capabilities(file_capstr, us_capstr)) {
//    freelog(LOG_FATAL, _("sound spec-file claims required option(s)"
//			 " which we don't support:"));
//    freelog(LOG_FATAL, _("file: \"%s\""), filename);
//    freelog(LOG_FATAL, "file options: %s", file_capstr);
//    freelog(LOG_FATAL, "supported options: %s", us_capstr);
//    exit(EXIT_FAILURE);
//  }
//
//  free((void *) filename);
//
//  atexit(audio_shutdown);
//
//  if (prefered_plugin_name[0] != '\0') {
//    if (!audio_select_plugin(prefered_plugin_name))
//      freelog(LOG_NORMAL, "Proceeding with sound support disabled");
//    return;
//  }
//
//#ifdef SDL
//  if (audio_select_plugin("sdl")) return; 
//#endif
//#ifdef ESD
//  if (audio_select_plugin("esd")) return; 
//#endif
//#ifdef ALSA
//  if (audio_select_plugin("alsa")) return;
//#endif
//#ifdef WINMM
//  if (audio_select_plugin("winmm")) return;
//#endif
//#ifdef AMIGA
//  if (audio_select_plugin("amiga")) return;
//#endif
//  freelog(LOG_ERROR,
//    "No real audio subsystem managed to initialize!");
//  freelog(LOG_ERROR,
//    "Perhaps there is some misconfigurationg or bad permissions");
//  freelog(LOG_NORMAL, "Proceeding with sound support disabled");
//}
//
///**************************************************************************
//  INTERNAL. Returns true for success.
//**************************************************************************/
//static boolean audio_play_tag(final String tag, boolean repeat)
//{
//  char *soundfile, *fullpath = null;
//
//  if (!tag || strcmp(tag, "-") == 0) {
//    return false;
//  }
//
//  if (tagfile) {
//    soundfile = secfile_lookup_str_default(tagfile, "-", "files.%s", tag);
//    if (strcmp(soundfile, "-") == 0) {
//      freelog(LOG_VERBOSE, "No sound file for tag %s (file %s)", tag,
//	      soundfile);
//    } else {
//      fullpath = datafilename(soundfile);
//      if (!fullpath) {
//	freelog(LOG_ERROR, "Cannot find audio file %s", soundfile);
//      }
//    }
//  }
//
//  return plugins[selected_plugin].play(tag, fullpath, repeat);
//}
//
///**************************************************************************
//  Play an audio sample as suggested by sound tags
//**************************************************************************/
//void audio_play_sound(final String const tag, char *const alt_tag)
//{
//  char *pretty_alt_tag = alt_tag ? alt_tag : "(null)";
//
//  assert(tag != null);
//
//  freelog(LOG_DEBUG, "audio_play_sound('%s', '%s')", tag, pretty_alt_tag);
//
//  /* try playing primary tag first, if not go to alternative tag */
//  if (!audio_play_tag(tag, false) && !audio_play_tag(alt_tag, false)) {
//    freelog(LOG_VERBOSE, "Neither of tags %s or %s found", tag,
//	    pretty_alt_tag);
//  }
//}
//
///**************************************************************************
//  Loop sound sample as suggested by sound tags
//**************************************************************************/
//void audio_play_music(final String const tag, char *const alt_tag)
//{
//  char *pretty_alt_tag = alt_tag ? alt_tag : "(null)";
//
//  assert(tag != null);
//
//  freelog(LOG_DEBUG, "audio_play_music('%s', '%s')", tag, pretty_alt_tag);
//
//  /* try playing primary tag first, if not go to alternative tag */
//  if (!audio_play_tag(tag, true) && !audio_play_tag(alt_tag, true)) {
//    freelog(LOG_VERBOSE, "Neither of tags %s or %s found", tag,
//	    pretty_alt_tag);
//  }
//}
//
///**************************************************************************
//  Stop looping sound. Music should die down in a few seconds.
//**************************************************************************/
//void audio_stop()
//{
//  plugins[selected_plugin].stop();
//}
//
///**************************************************************************
//  Call this at end of program only.
//**************************************************************************/
//void audio_shutdown()
//{
//  /* avoid infinite loop at end of game */
//  audio_stop();
//
//  audio_play_sound("e_game_quit", null);
//  plugins[selected_plugin].wait();
//  plugins[selected_plugin].shutdown();
//
//  if (tagfile) {
//    section_file_free(tagfile);
//    tagfile = null;
//  }
//}
//
///**************************************************************************
//  Returns a string which list all available plugins. You don't have to
//  free the string.
//**************************************************************************/
//final String audio_get_all_plugin_names()
//{
//  static char buffer[100];
//  int i;
//
//  sz_strlcpy(buffer, "[");
//
//  for (i = 0; i < num_plugins_used; i++) {
//    sz_strlcat(buffer, plugins[i].name);
//    if (i != num_plugins_used - 1) {
//      sz_strlcat(buffer, ", ");
//    }
//  }
//  sz_strlcat(buffer, "]");
//  return buffer;
//}
}