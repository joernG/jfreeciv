package client.gui_mui;

public class Scrollbuttonclass{

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
//#include <stdio.h>
//#include <stdlib.h>
//#include <string.h>
//
//#include <intuition/intuitionbase.h>
//#include <libraries/mui.h>
//
//#include <clib/alib_protos.h>
//#include <proto/exec.h>
//#include <proto/utility.h>
//#include <proto/graphics.h>
//#include <proto/intuition.h>
//#include <proto/muimaster.h>
//
//#include "scrollbuttonclass.h"
//#include "muistuff.h"
//
//struct ScrollButton_Data
//{
//  WORD mx,my;
//  WORD cx,cy;
//  ULONG pos;
//};
//
//static ULONG ScrollButton_New(IClass  cl, Object * o, opSet  msg)
//{
//  return (Object *) DoSuperNew(cl, o,
//  		ButtonFrame,
//  		MUIA_InputMode,MUIV_InputMode_RelVerify,
//  		MUIA_Background, MUII_ButtonBack,
//		TAG_MORE, msg.ops_AttrList);
//}
//
//static ULONG ScrollButton_Get(IClass  cl, Object * o, opGet  msg)
//{
//  ScrollButton_Data data = (ScrollButton_Data ) INST_DATA(cl, o);
//  switch (msg.opg_AttrID)
//  {
//    case  MUIA_ScrollButton_NewPosition:
//          *msg.opg_Storage = data.pos;
//          return true;
//          break;
//
//    default:
//	  return DoSuperMethodA(cl, o, (Msg) msg);
//  }
//}
//
//static ULONG ScrollButton_Set(IClass  cl, Object * o, opSet  msg)
//{
//  ScrollButton_Data data = (ScrollButton_Data ) INST_DATA(cl, o);
//  TagItem tl = msg.ops_AttrList;
//  TagItem ti;
//
//  while ((ti = NextTagItem(&tl)))
//  {
//    switch (ti.ti_Tag)
//    {
//      case  MUIA_ScrollButton_XPosition:
//	    data.cx = ti.ti_Data;
//	    break;
//
//      case  MUIA_ScrollButton_YPosition:
//	    data.cy = ti.ti_Data;
//	    break;
//    }
//  }
//  return DoSuperMethodA(cl, o, (Msg) msg);
//}
//
//static ULONG ScrollButton_AskMinMax(IClass cl, Object *o, MUIP_AskMinMax msg)
//{
//  DoSuperMethodA(cl, o, (Msg) msg);
//
//  msg.MinMaxInfo.MinWidth += 2;
//  msg.MinMaxInfo.DefWidth += 2;
//  msg.MinMaxInfo.MaxWidth += MUI_MAXMAX;
//
//  msg.MinMaxInfo.MinHeight += 2;
//  msg.MinMaxInfo.DefHeight += 2;
//  msg.MinMaxInfo.MaxHeight += MUI_MAXMAX;
//  return 0;
//}
//
//static ULONG ScrollButton_Setup(IClass  cl, Object * o, Msg msg)
//{
//  if (!DoSuperMethodA(cl, o, msg))
//    return false;
//
//  MUI_RequestIDCMP(o, IDCMP_MOUSEBUTTONS);
//
//  return true;
//}
//
//static ULONG ScrollButton_Cleanup(IClass  cl, Object * o, Msg msg)
//{
//  MUI_RejectIDCMP(o, IDCMP_MOUSEBUTTONS);
//
//  DoSuperMethodA(cl, o, msg);
//  return 0;
//}
//
//static ULONG ScrollButton_HandleInput(IClass  cl, Object * o, MUIP_HandleInput  msg)
//{
//  ScrollButton_Data data = (ScrollButton_Data ) INST_DATA(cl, o);
//  if (msg.imsg)
//  {
//    switch (msg.imsg.Class)
//    {
//      case  IDCMP_MOUSEBUTTONS:
//	    if (msg.imsg.Code == SELECTDOWN)
//	    {
//	      if (_isinwholeobject(msg.imsg.MouseX, msg.imsg.MouseY))
//              {
//              	MUI_RequestIDCMP(o,IDCMP_MOUSEMOVE);
//              	set(o,MUIA_Selected, true);
//                set(o,MUIA_Pressed, true);
//              	data.mx = msg.imsg.MouseX;
//              	data.my = msg.imsg.MouseY;
//	      }
//	    } else
//	    {
//              MUI_RejectIDCMP(o,IDCMP_MOUSEMOVE);
//              set(o,MUIA_Selected, false);
//              set(o,MUIA_Pressed, false);
//	    }
//	    break;
//
//      case  IDCMP_MOUSEMOVE:
//            if (xget(o,MUIA_Selected))
//            {
//              UWORD x = (msg.imsg.MouseX - data.mx) + data.cx;
//              UWORD y = (msg.imsg.MouseY - data.my) + data.cy;
//              ULONG pos = x << 16 | y;
//              data.pos = pos;
//              set(o,MUIA_ScrollButton_NewPosition,pos);
//            }
//	    break;
//    }
//  }
//  return 0;
//}
//
//DISPATCHERPROTO(ScrollButton_Dispatcher)
//{
//  switch (msg.MethodID)
//  {
//    case  OM_NEW:
//	  return ScrollButton_New(cl, obj, (opSet ) msg);
//
//    case  OM_GET:
//	  return ScrollButton_Get(cl, obj, (opGet ) msg);
//
//    case  OM_SET:
//	  return ScrollButton_Set(cl, obj, (opSet ) msg);
//
//    case  MUIM_AskMinMax:
//	  return ScrollButton_AskMinMax(cl, obj, (MUIP_AskMinMax ) msg);
//
//    case  MUIM_Setup:
//	  return ScrollButton_Setup(cl, obj, msg);
//
//    case  MUIM_Cleanup:
//	  return ScrollButton_Cleanup(cl, obj, msg);
//
//    case  MUIM_HandleInput:
//	  return ScrollButton_HandleInput(cl, obj, (MUIP_HandleInput ) msg);
//  }
//  return (DoSuperMethodA(cl, obj, msg));
//}
//
//
//MUI_CustomClass CL_ScrollButton;
//
//int create_scrollbutton_class()
//{
//  if ((CL_ScrollButton = MUI_CreateCustomClass(null, MUIC_Area, null, sizeof(struct ScrollButton_Data), (APTR) ScrollButton_Dispatcher)))
//      return true;
//  return false;
//}
//
//void delete_scrollbutton_class()
//{
//  if (CL_ScrollButton)
//  {
//    MUI_DeleteCustomClass(CL_ScrollButton);
//    CL_ScrollButton = null;
//  }
//}
}