package client;

public class Attribute{

// Freeciv - Copyright (C) 2001 - R. Falke
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
//
//#include "dataio.h"
//#include "fcintl.h"
//#include "Game.game.h"
//#include "hash.h"
//#include "log.h"
//#include "mem.h"
//#include "packets.h"
//
//#include "clinet.h"
//
//#include "attribute.h"
//
//#define ATTRIBUTE_LOG_LEVEL	Log.LOG_DEBUG
//
//static hash_table attribute_hash = null;
//
//struct attr_key {
//  int key, id, x, y;
//};
//
///****************************************************************************
// Hash function for attribute_hash.
//*****************************************************************************/
//static unsigned int attr_hash_val_fn(final void *key,
//				     unsigned int num_buckets)
//{
//  final attr_key pkey = (final attr_key ) key;
//
//  return (pkey.id ^ pkey.x ^ pkey.y ^ pkey.key) % num_buckets;
//}
//
///****************************************************************************
// Compare-function for the keys in the hash table.
//*****************************************************************************/
//static int attr_hash_cmp_fn(final void *key1, final void *key2)
//{
//  return memcmp(key1, key2, sizeof(struct attr_key));
//}
//
///****************************************************************************
//...
//*****************************************************************************/
//void attribute_init()
//{
//  assert(attribute_hash == null);
//  attribute_hash = hash_new(attr_hash_val_fn, attr_hash_cmp_fn);
//}
//
///****************************************************************************
//...
//*****************************************************************************/
//void attribute_free()
//{
//  int i, entries = hash_num_entries(attribute_hash);
//
//  assert(attribute_hash != null);
//
//  for (i = 0; i < entries; i++) {
//    final void *pkey = hash_key_by_number(attribute_hash, 0);
//    void *pvalue = hash_delete_entry(attribute_hash, pkey);
//
//    free((void *) pkey);
//    free(pvalue);
//  }
//
//  hash_free(attribute_hash);
//  attribute_hash = null;
//}
//
///****************************************************************************
// This method isn't endian safe and there will also be problems if
// sizeof(int) at serialization time is different from sizeof(int) at
// deserialization time.
//*****************************************************************************/
//static void serialize_hash(hash_table hash, void **pdata,
//			   int *pdata_length)
//{
//  /*
//   * Layout of version 2:
//   *
//   * struct {
//   *   uint32 0;   always != 0 in version 1
//   *   uint8 2;
//   *   uint32 entries;
//   *   uint32 total_size_in_bytes;
//   * } preamble;
//   * 
//   * struct {
//   *   uint32 value_size;
//   *   char key[], char value[];
//   * } body[entries];
//   */
//  int total_length, i, entries = hash_num_entries(hash);
//  void *result;
//  int *value_lengths;
//  struct data_out dout;
//
//  value_lengths = fc_malloc(sizeof(int) * entries);
//
//  /*
//   * Step 1: loop through all keys and fill value_lengths
//   */
//  for (i = 0; i < entries; i++) {
//    final void *pvalue = hash_value_by_number(hash, i);
//    struct data_in din;
//    int tmp_len;
//
//    dio_input_init(&din, pvalue, 4);
//    dio_get_uint32(&din, &tmp_len);
//
//    value_lengths[i] = tmp_len;
//  }
//
//  /*
//   * Step 2: calculate the *_length variables
//   */
//  /* preamble */
//  total_length = 4 * 4;
//  /* value_size and key */
//  total_length += entries * (4 + 4 * 4);
//
//  for (i = 0; i < entries; i++) {
//    total_length += value_lengths[i];
//  }
//
//  /*
//   * Step 3: allocate memory
//   */
//  result = fc_malloc(total_length);
//  dio_output_init(&dout, result, total_length);
//
//  /*
//   * Step 4: fill out the preamble
//   */
//  dio_put_uint32(&dout, 0);
//  dio_put_uint8(&dout, 2);
//  dio_put_uint32(&dout, entries);
//  dio_put_uint32(&dout, total_length);
//
//  /*
//   * Step 5: fill out the body
//   */
//  for (i = 0; i < entries; i++) {
//    final attr_key pkey = hash_key_by_number(hash, i);
//    final void *pvalue = hash_value_by_number(hash, i);
//
//    dio_put_uint32(&dout, value_lengths[i]);
//
//    dio_put_uint32(&dout, pkey.key);
//    dio_put_uint32(&dout, pkey.id);
//    dio_put_sint16(&dout, pkey.x);
//    dio_put_sint16(&dout, pkey.y);
//
//    dio_put_memory(&dout, ADD_TO_POINTER(pvalue, 4), value_lengths[i]);
//  }
//
//  assert(!dout.too_short);
//
//  /*
//   * Step 6: cleanup
//   */
//  *pdata = result;
//  *pdata_length = total_length;
//  free(value_lengths);
//  util.freelog(Log.LOG_DEBUG, "serialized %d entries in %d bytes", entries,
//	  total_length);
//}
//
///****************************************************************************
//...
//*****************************************************************************/
//static boolean unserialize_hash(hash_table hash, void *data,
//			     size_t data_length)
//{
//  int entries, i, dummy;
//  struct data_in din;
//
//  hash_delete_all_entries(hash);
//
//  dio_input_init(&din, data, data_length);
//
//  dio_get_uint32(&din, &dummy);
//  if (dummy != 0) {
//    return false;
//  }
//  dio_get_uint8(&din, &dummy);
//  if (dummy != 2) {
//    return false;
//  }
//  dio_get_uint32(&din, &entries);
//  dio_get_uint32(&din, &dummy);
//  assert(data_length == dummy);
//
//  util.freelog(Log.LOG_DEBUG, "try to unserialized %d entries from %d bytes",
//	  entries, (unsigned int) data_length);
//
//  for (i = 0; i < entries; i++) {
//    attr_key pkey = fc_malloc(sizeof(*pkey));
//    void *pvalue;
//    int value_length;
//    struct data_out dout;
//
//    dio_get_uint32(&din, &value_length);
//    
//    pvalue = fc_malloc(value_length + 4);
//    
//    dio_get_uint32(&din, &pkey.key);
//    dio_get_uint32(&din, &pkey.id);
//    dio_get_sint16(&din, &pkey.x);
//    dio_get_sint16(&din, &pkey.y);
//
//    dio_output_init(&dout, pvalue, 4);
//    dio_put_uint32(&dout, value_length);
//    dio_get_memory(&din, ADD_TO_POINTER(pvalue, 4), value_length);
//
//    if (!hash_insert(hash, pkey, pvalue)) {
//      /* There are some untraceable attribute bugs caused by the CMA that
//       * can cause this to happen.  I think the only safe thing to do is
//       * to delete all attributes.  Another symptom of the bug is the
//       * value_length (above) is set to a random value, which can also
//       * cause a bug. */
//      util.freelog(Log.LOG_ERROR, ("There has been a CMA error.  " +
//			   "Your CMA settings may be broken."));
//      free(pvalue);
//      free(pkey);
//      hash_delete_all_entries(hash);
//      return false;
//    }
//  }
//  return true;
//}
//
///****************************************************************************
// Send current state to the server. Note that the current
// implementation will send all attributes to the server.
//*****************************************************************************/
//void attribute_flush()
//{
//  player pplayer = Game.game.player_ptr;
//
//  assert(attribute_hash != null);
//
//  if (hash_num_entries(attribute_hash) == 0)
//    return;
//
//  if (pplayer.attribute_block.data) {
//    free(pplayer.attribute_block.data);
//    pplayer.attribute_block.data = null;
//  }
//
//  serialize_hash(attribute_hash, &(pplayer.attribute_block.data),
//		 &(pplayer.attribute_block.length));
//  send_attribute_block(pplayer, &aconnection);
//}
//
///****************************************************************************
// Recreate the attribute set from the player's
// attribute_block. Shouldn't be used by normal code.
//*****************************************************************************/
//void attribute_restore()
//{
//  player pplayer = Game.game.player_ptr;
//
//  assert(attribute_hash != null);
//
//  if (!unserialize_hash(attribute_hash, pplayer.attribute_block.data,
//			pplayer.attribute_block.length)) {
//    util.freelog(Log.LOG_ERROR, "Old attributes detected and removed.");
//  }
//}
//
///****************************************************************************
// Low-level function to set an attribute.  If data_length is zero the
// attribute is removed.
//*****************************************************************************/
//void attribute_set(int key, int id, int x, int y, size_t data_length,
//		   final void *final data)
//{
//  attr_key pkey;
//  void *pvalue = null;
//
//  util.freelog(ATTRIBUTE_LOG_LEVEL, "attribute_set(key=%d, id=%d, x=%d, y=%d, " +
//	  "data_length=%d, data=%p)", key, id, x, y,
//	  (unsigned int) data_length, data);
//
//  assert(attribute_hash != null);
//
//  pkey = fc_malloc(sizeof(struct attr_key));
//  pkey.key = key;
//  pkey.id = id;
//  pkey.x = x;
//  pkey.y = y;
//
//  if (data_length != 0) {
//    struct data_out dout;
//
//    pvalue = fc_malloc(data_length + 4);
//
//    dio_output_init(&dout, pvalue, data_length + 4);
//    dio_put_uint32(&dout, data_length);
//    dio_put_memory(&dout, data, data_length);
//  }
//
//  if (hash_key_exists(attribute_hash, pkey)) {
//    void *old_key;
//    void *old_value = hash_delete_entry_full(attribute_hash, pkey, &old_key);
//
//    free(old_value);
//    free(old_key);
//  }
//
//  if (data_length != 0) {
//    if (!hash_insert(attribute_hash, pkey, pvalue)) {
//      assert(false);
//    }
//  }
//}
//
///****************************************************************************
// Low-level function to get an attribute. If data hasn't enough space
// to hold the attribute data isn't set to the attribute. Returns the
// actual size of the attribute. Can be zero if the attribute is
// unset. To get the size of an attribute use 
//   size = attribute_get(key, id, x, y, 0, null)
//*****************************************************************************/
//size_t attribute_get(int key, int id, int x, int y, size_t max_data_length,
//		  void *data)
//{
//
//  struct attr_key pkey;
//  void *pvalue;
//  int length;
//  struct data_in din;
//
//  util.freelog(ATTRIBUTE_LOG_LEVEL, "attribute_get(key=%d, id=%d, x=%d, y=%d, " +
//	  "max_data_length=%d, data=%p)", key, id, x, y,
//	  (unsigned int) max_data_length, data);
//
//  assert(attribute_hash != null);
//
//  pkey.key = key;
//  pkey.id = id;
//  pkey.x = x;
//  pkey.y = y;
//
//  pvalue = hash_lookup_data(attribute_hash, &pkey);
//
//  if (!pvalue) {
//    util.freelog(ATTRIBUTE_LOG_LEVEL, "  not found");
//    return 0;
//  }
//
//  dio_input_init(&din, pvalue, 0xffffffff);
//  dio_get_uint32(&din, &length);
//
//  if (length <= max_data_length) {
//    dio_get_memory(&din, data, length);
//  }
//
//  util.freelog(ATTRIBUTE_LOG_LEVEL, "  found length=%d", length);
//  return length;
//}
//
///****************************************************************************
//...
//*****************************************************************************/
//void attr_unit_set(enum attr_unit what, int unit_id, size_t data_length,
//		   final void *final data)
//{
//  attribute_set(what, unit_id, -1, -2, data_length, data);
//}
//
///****************************************************************************
//...
//*****************************************************************************/
//size_t attr_unit_get(enum attr_unit what, int unit_id, size_t max_data_length,
//		  void *data)
//{
//  return attribute_get(what, unit_id, -1, -2, max_data_length, data);
//}
//
///****************************************************************************
//...
//*****************************************************************************/
//void attr_unit_set_int(enum attr_unit what, int unit_id, int data)
//{
//  attr_unit_set(what, unit_id, sizeof(int), &data);
//}
//
///****************************************************************************
//...
//*****************************************************************************/
//size_t attr_unit_get_int(enum attr_unit what, int unit_id, int *data)
//{
//  return attr_unit_get(what, unit_id, sizeof(int), data);
//}
//
///****************************************************************************
//...
//*****************************************************************************/
//void attr_city_set(enum attr_city what, int city_id, size_t data_length,
//		   final void *final data)
//{
//  attribute_set(what, city_id, -1, -1, data_length, data);
//}
//
///****************************************************************************
//...
//*****************************************************************************/
//size_t attr_city_get(enum attr_city what, int city_id, size_t max_data_length,
//		  void *data)
//{
//  return attribute_get(what, city_id, -1, -1, max_data_length, data);
//}
//
///****************************************************************************
//...
//*****************************************************************************/
//void attr_city_set_int(enum attr_city what, int city_id, int data)
//{
//  attr_city_set(what, city_id, sizeof(int), &data);
//}
//
///****************************************************************************
//...
//*****************************************************************************/
//size_t attr_city_get_int(enum attr_city what, int city_id, int *data)
//{
//  return attr_city_get(what, city_id, sizeof(int), data);
//}
//
///****************************************************************************
//...
//*****************************************************************************/
//void attr_player_set(enum attr_player what, int player_id, size_t data_length,
//		     final void *final data)
//{
//  attribute_set(what, player_id, -1, -1, data_length, data);
//}
//
///****************************************************************************
//...
//*****************************************************************************/
//size_t attr_player_get(enum attr_player what, int player_id,
//		    size_t max_data_length, void *data)
//{
//  return attribute_get(what, player_id, -1, -1, max_data_length, data);
//}
//
///****************************************************************************
//...
//*****************************************************************************/
//void attr_tile_set(enum attr_tile what, int x, int y, size_t data_length,
//		   final void *final data)
//{
//  attribute_set(what, -1, x, y, data_length, data);
//}
//
///****************************************************************************
//...
//*****************************************************************************/
//size_t attr_tile_get(enum attr_tile what, int x, int y, size_t max_data_length,
//		  void *data)
//{
//  return attribute_get(what, -1, x, y, max_data_length, data);
//}
}