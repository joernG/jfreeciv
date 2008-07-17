package client.gui_mui;

public class Colortextclass{

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
//#include <errno.h>
//#include <stdio.h>
//#include <stdlib.h>
//#include <string.h>
//
//#include <support.h>
//
//#include <intuition/sghooks.h>
//#include <libraries/mui.h>
//
//#include <clib/alib_protos.h>
//
//#include <proto/exec.h>
//#include <proto/intuition.h>
//#include <proto/graphics.h>
//#include <proto/muimaster.h>
//#include <proto/utility.h>
//
//#include "colortextclass.h"
//#include "muistuff.h"
//
//struct ColorText_Data
//{
//  LONG fgpen;
//  LONG bgpen;
//
//  LONG fgcol;
//  LONG bgcol;
//  LONG ownbg;
//
//  STRPTR contents;
//  LONG align;
//
//  LONG innerleft,innertop,innerright,innerbottom;
//  LONG setup; /* true if between MUIM_Setup and MUIM_Cleanup */
//};
//
//
//static ULONG ColorText_New(IClass cl, Object * o, opSet msg)
//{
//  if ((o = (Object *) DoSuperNew(cl, o,
//  				 MUIA_InnerLeft,0,
//  				 MUIA_InnerTop,0,
//  				 MUIA_InnerRight,0,
//  				 MUIA_InnerBottom,0,
//				 TAG_MORE, msg.ops_AttrList)))
//  {
//    ColorText_Data data = (ColorText_Data ) INST_DATA(cl, o);
//    TagItem tl = msg.ops_AttrList;
//    TagItem ti;
//
//    data.fgpen = -1;
//    data.bgpen = -1;
//
//    while ((ti = NextTagItem(&tl)))
//    {
//      switch (ti.ti_Tag)
//      {
//	case  MUIA_ColorText_Contents:
//	      data.contents = StrCopy((STRPTR)ti.ti_Data);
//      	      break;
//
//        case  MUIA_ColorText_Background:
//              data.bgcol = ti.ti_Data;
//              data.ownbg = true;
//	      break;
//
//	case  MUIA_ColorText_Align:
//	      data.align = ti.ti_Data;
//	      break;
//      }
//    }
//
//    if (data.ownbg) set(o,MUIA_FillArea,false);
//  }
//  return (ULONG) o;
//}
//
//static ULONG ColorText_Dispose(IClass  cl, Object * o, Msg msg)
//{
//  ColorText_Data data = (ColorText_Data ) INST_DATA(cl, o);
//  if (data.contents) FreeVec(data.contents);
//  return DoSuperMethodA(cl, o, msg);
//}
//
//static ULONG ColorText_Set(IClass cl, Object * o, opSet msg)
//{
//  ColorText_Data data = (ColorText_Data ) INST_DATA(cl, o);
//  TagItem tl = msg.ops_AttrList;
//  TagItem ti;
//  boolean redraw = false;
//  boolean pen_changed = false;
//
//  while ((ti = NextTagItem(&tl)))
//  {
//      switch (ti.ti_Tag)
//      {
//	case  MUIA_ColorText_Contents:
//	      if(data.contents) FreeVec(data.contents);
//	      data.contents = StrCopy((STRPTR)ti.ti_Data);
//	      redraw = true;
//      	      break;
//
//        case  MUIA_ColorText_Background:
//              if (data.bgcol != ti.ti_Data)
//              {
//	        redraw = pen_changed = true;
//	        data.bgcol = ti.ti_Data;
//	        data.ownbg = true;
//              }
//	      break;
//
//	case  MUIA_ColorText_Align:
//	      if (data.align != ti.ti_Data)
//	      {
//	      	data.align = ti.ti_Data;
//	      	redraw = true;
//	      }
//	      break;
//      }
//  }
//
//  if (redraw && data.setup)
//  {
//    ColorMap cm;
//    LONG old_pen;
//
//    cm = _screen(o).ViewPort.ColorMap;
//    old_pen = data.bgpen;
//    if (pen_changed)
//    {
//      data.bgpen = ObtainBestPenA(cm,
//             MAKECOLOR32(((data.bgcol >> 16)&0xff)),
//             MAKECOLOR32(((data.bgcol >> 8)&0xff)),
//             MAKECOLOR32((data.bgcol & 0xff)), null);
//    }
//    MUI_Redraw(o, MADF_DRAWOBJECT);
//    if (old_pen != -1 && pen_changed) ReleasePen(cm,old_pen);
//  }
//  return DoSuperMethodA(cl,o,(Msg)msg);
//}
//
//static ULONG ColorText_Get(IClass  cl, Object * o, opGet  msg)
//{
//  ColorText_Data data = (ColorText_Data ) INST_DATA(cl, o);
//  switch (msg.opg_AttrID)
//  {
//    case  MUIA_ColorText_Contents:
//          *msg.opg_Storage = (ULONG)data.contents;
//          break;
//
//    default:
//          return DoSuperMethodA(cl, o, (Msg) msg);
//  }
//  return 1;
//}
//
//
//static ULONG ColorText_Setup(IClass  cl, Object * o, Msg msg)
//{
//  ColorText_Data data = (ColorText_Data ) INST_DATA(cl, o);
//  ColorMap cm;
//
//  if (!DoSuperMethodA(cl, o, msg))
//    return false;
//
//  cm = _screen(o).ViewPort.ColorMap;
//
//  if (data.ownbg)
//  {
//    data.bgpen = ObtainBestPenA(cm,
//                                 MAKECOLOR32(((data.bgcol >> 16)&0xff)),
//                                 MAKECOLOR32(((data.bgcol >> 8)&0xff)),
//                                 MAKECOLOR32((data.bgcol & 0xff)), null);
//  } else data.bgpen = -1;
//
//  data.innerleft=2;
//  data.innertop=1;
//  data.innerright=2;
//  data.innerbottom=1;
//  data.setup = true;
//
//  return true;
//}
//
//static ULONG ColorText_Cleanup(IClass  cl, Object * o, Msg msg)
//{
//  ColorText_Data data = (ColorText_Data ) INST_DATA(cl, o);
//  ColorMap cm;
//
//  data.setup = false;
//
//  cm = _screen(o).ViewPort.ColorMap;
//
//  if (data.bgpen != -1) ReleasePen(cm, data.bgpen);
//
//  return DoSuperMethodA(cl, o, msg);
//}
//
//static ULONG ColorText_AskMinMax(IClass  cl, Object * o, MUIP_AskMinMax  msg)
//{
//  LONG width,height;
//  struct RastPort rp;
//  ColorText_Data data = (ColorText_Data ) INST_DATA(cl, o);
//  DoSuperMethodA(cl, o, (Msg) msg);
//
//  InitRastPort(&rp);
//  SetFont(&rp,_font(o));
//
//  if(data.contents)
//    width = TextLength(&rp,data.contents, strlen(data.contents)) + data.innerleft + data.innerright;
//  else
//    width = 10; /* something higher than 0 */
//
//  height = _font(o).tf_YSize + data.innertop + data.innerbottom;;
//
//  msg.MinMaxInfo.MinWidth += width;
//  msg.MinMaxInfo.DefWidth += width;
//  msg.MinMaxInfo.MaxWidth += MUI_MAXMAX;
//
//  msg.MinMaxInfo.MinHeight += height;
//  msg.MinMaxInfo.DefHeight += height;
//  msg.MinMaxInfo.MaxHeight += height;
//  return 0;
//}
//
//static ULONG ColorText_Draw(IClass  cl, Object * o, MUIP_Draw  msg)
//{
//  ColorText_Data data = (ColorText_Data ) INST_DATA(cl, o);
//
//  DoSuperMethodA(cl,o,(Msg)msg);
//
//  if (data.bgpen != -1)
//  {
//    SetAPen(_rp(o), data.bgpen);
//    RectFill(_rp(o),_mleft(o), _mtop(o), _mright(o), _mbottom(o));
//  }
//
//  if (data.contents)
//  {
//    struct TextExtent te;
//    LONG x_off;
//    LONG count = strlen(data.contents);
//    LONG w = _mwidth(o) - data.innerleft - data.innerright;
//    LONG h = _mheight(o) - data.innertop - data.innerbottom;
//
//    count = TextFit(_rp(o),data.contents,count,&te, null, 1, w, h);
//
//    if (data.align == MUIV_ColorText_Align_Left) x_off = 0;
//    else x_off = (w - te.te_Width)/2;
//
//    SetAPen(_rp(o), _pens(o)[MPEN_TEXT]);
//    Move(_rp(o),_mleft(o) + data.innerleft + x_off,
//         _mtop(o) + _font(o).tf_Baseline + data.innertop);
//    Text(_rp(o),data.contents, count);
//  }
//  return 0;
//}
//
//DISPATCHERPROTO(ColorText_Dispatcher)
//{
//  switch (msg.MethodID)
//  {
//  case OM_NEW:
//    return ColorText_New(cl, obj, (opSet ) msg);
//  case OM_DISPOSE:
//    return ColorText_Dispose(cl, obj, msg);
//  case OM_GET:
//    return ColorText_Get(cl, obj, (opGet ) msg);
//  case OM_SET:
//    return ColorText_Set(cl, obj, (opSet ) msg);
//  case MUIM_Setup:
//    return ColorText_Setup(cl, obj, msg);
//  case MUIM_Cleanup:
//    return ColorText_Cleanup(cl, obj, msg);
//  case MUIM_AskMinMax:
//    return ColorText_AskMinMax(cl, obj, (MUIP_AskMinMax ) msg);
//  case MUIM_Draw:
//    return ColorText_Draw(cl, obj, (MUIP_Draw ) msg);
//  }
//  return (DoSuperMethodA(cl, obj, msg));
//}
//
//MUI_CustomClass CL_ColorText;
//
//boolean create_colortext_class()
//{
//  if ((CL_ColorText = MUI_CreateCustomClass(null, MUIC_Area, null, sizeof(struct ColorText_Data), (APTR) ColorText_Dispatcher)))
//      return true;
//  return false;
//}
//
//VOID delete_colortext_class()
//{
//  if (CL_ColorText)
//    MUI_DeleteCustomClass(CL_ColorText);
//}
}