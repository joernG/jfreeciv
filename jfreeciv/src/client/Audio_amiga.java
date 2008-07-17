package client;

public class Audio_amiga{

// Freeciv - Copyright (C) 2002 - R. Falke
//  This program is free software; you can redistribute it and/or modify
//  it under the terms of the GNU General Public License as published by
//  the Free Software Foundation; either version 2, or (at your option)
//  any later version.
//
//  This program is distributed in the hope that it will be useful,
//  but WITHOUT ANY WARRANTY; without even the implied warranty of
//  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//  GNU General Public License for more details.
//***********************************************************************/
//
//#ifdef HAVE_CONFIG_H
//#include <config.h>
//#endif
//
//#include <stdio.h>
//#include <stdlib.h>
//#include <string.h>
//
//#include <clib/alib_protos.h>
//#include <datatypes/soundclass.h>
//#include <intuition/classusr.h>
//#include <proto/datatypes.h>
//#include <proto/intuition.h>
//
//#include "log.h"
//#include "support.h"
//
//#include "audio.h"
//#include "gui_main_g.h"
//
//#include "audio_amiga.h"
//
//public static final int MAX_SAMPLES = 8;
//
//struct MySample
//{
//  Object    *obj;
//  final String tag;
//  ULONG     time;
//};
//
//static struct MySample samples[ MAX_SAMPLES ];
//static Object *MusicSample  = null;
//
///**************************************************************************
//  Clean up
//**************************************************************************/
//static void my_shutdown()
//{
//  int i;
//
//  for (i = 0; i < MAX_SAMPLES; i++)
//  {
//    DisposeDTObject( samples[i].obj );
//  }
//
//}
//
///**************************************************************************
//  Stop music
//**************************************************************************/
//static void my_stop()
//{
//  if ( MusicSample != null )
//    DoMethod(MusicSample, DTM_TRIGGER, 0, STM_STOP, 0);
//}
//
///**************************************************************************
//  Wait
//**************************************************************************/
//static void my_wait()
//{
//}
//
///**************************************************************************
//  Play sound sample
//**************************************************************************/
//static boolean my_play(final String const tag, final String const fullpath, boolean repeat)
//{
//  Object  *CurrentSample  = null;
//  ULONG time, dummy;
//  int slot, i;
//
//  /*  Find cached sample and alternatively look for the oldest slot */
//
//  time  = -1;
//
//  for (i = 0; i < MAX_SAMPLES; i++)
//  {
//    if ( samples[i].tag && (strcmp( samples[i].tag, tag) == 0 ))
//    {
//      CurrentSample = samples[i].obj;
//      slot  = i;
//      break;
//    }
//
//    if ( samples[ i ].time <= time )
//      slot = i;
//  }
//
//  if ( CurrentSample == null )
//  {
//    if ( fullpath == null )
//      return( false );
//
//    DisposeDTObject( samples[ slot ].obj );
//
//    CurrentSample = NewDTObject((APTR)fullpath,
//                DTA_GroupID, GID_SOUND,
//                SDTA_Cycles, repeat ? -1 : 1,
//                DTA_Repeat, repeat,
//                TAG_DONE);
//
//    samples[ slot ].obj = CurrentSample;
//    samples[ slot ].tag = tag;
//
//    if ( CurrentSample == null )
//      return( false );
//  }
//
//  /* DateStamp the latest sample. This way samples that were not used for
//   * a while get expunged from the memory.  */
//
//  CurrentTime( &samples[ slot ].time, &dummy );
//
//  if ( repeat )
//    MusicSample = CurrentSample;
//
//  DoMethod(CurrentSample, DTM_TRIGGER, 0, STM_PLAY, 0);
//  return( true );
//}
//
///**************************************************************************
//  Initialize.
//**************************************************************************/
//static boolean my_init()
//{
//  int i;
//
//  for (i = 0; i < MAX_SAMPLES; i++)
//  {
//    samples[i].obj    = null;
//    samples[i].tag    = null;
//    samples[i].time = 0;
//  }
//
//  return true;
//}
//
///**************************************************************************
//  Initialize.
//**************************************************************************/
//void audio_amiga_init()
//{
//  struct audio_plugin self;
//
//  sz_strlcpy(self.name, "amiga");
//  sz_strlcpy(self.descr, "Amiga audio plugin");
//  self.init = my_init;
//  self.shutdown = my_shutdown;
//  self.stop = my_stop;
//  self.wait = my_wait;
//  self.play = my_play;
//  audio_add_plugin(&self);
//}
}