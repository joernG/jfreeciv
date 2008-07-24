package utility.shared;

public class Shared_H {
	/* Want to use GCC's __attribute__ keyword to check variadic
	 * parameters to printf-like functions, without upsetting other
	 * compilers: put any required defines magic here.
	 * If other compilers have something equivalent, could also
	 * work that out here.   Should this use configure stuff somehow?
	 * --dwp
	 */
//	#if defined(__GNUC__)
//	#define fc__attribute(x)  __attribute__(x)
//	#else
//	#define fc__attribute(x)
//	#endif


	/* Note: the capability string is now in capstr.c --dwp */
	/* Version stuff is now in version.h --dwp */

	public static String BUG_EMAIL_ADDRESS = "bugs@freeciv.org";
	public static String WEBSITE_URL = "http://www.freeciv.org/";

	/* MAX_NUM_PLAYERS + MAX_NUM_BARBARIANS <= 32 !!!! */
	public static final int MAX_NUM_PLAYERS = 30;
	public static final int MAX_NUM_BARBARIANS = 2;
	public static final int MAX_NUM_CONNECTIONS =(2 * (MAX_NUM_PLAYERS + MAX_NUM_BARBARIANS));
	public static final int MAX_NUM_ITEMS = 200;	/* eg, Unittype_P.unit_types */
	public static final int MAX_NUM_TECH_LIST = 10;
	public static final int MAX_NUM_BUILDING_LIST = 10;
	public static final int MAX_LEN_NAME = 32;
	public static final int MAX_LEN_ADDR = 256;	/* see also MAXHOSTNAMELEN and RFC 1123 2.1 */
	public static final int MAX_LEN_VET_SHORT_NAME = 8;
	public static final int MAX_VET_LEVELS = 10;
	public static final int MAX_LEN_PATH = 4095;

	/* Use FC_INFINITY to denote that a certain event will never occur or
	   another unreachable condition. */
	public static final int INFINITY   = 	(1000 * 1000 * 1000);

//	#ifndef MAX
//	#define MAX(x,y) (((x)>(y))?(x):(y))
//	#define Math.min(x,y) (((x)<(y))?(x):(y))
//	#endif
//	#define CLIP(lower,this,upper) \
//	    ((this)<(lower)?(lower):(this)>(upper)?(upper):(this))
//
//	/* Note: Solaris already has a WRAP macro that is completely different. */
//	#define FC_WRAP(value, range)                                               \
//	    ((value) < 0                                                            \
//	     ? ((value) % (range) != 0 ? (value) % (range) + (range) : 0)           \
//	     : ((value) >= (range) ? (value) % (range) : (value)))
//
	public static int FC_WRAP(int value, int range) {
		return (value) < 0 
				? ((value) % (range) != 0 
						? (value) % (range)	+ (range) 
						: 0) 
				: ((value) >= (range) 
						? (value) % (range)
				        : (value));
	}
// #define BOOL_VAL(x) ((x) != 0)
	public static boolean BOOL_VAL(int x){
		return ((x) != 0);
	}
// #define XOR(p, q) (!(p) != !(q))
//
//	/*
//	 * DIVIDE() divides and rounds down, rather than just divides and
//	 * rounds toward 0.  It is assumed that the divisor is positive.
//	 */
//	#define DIVIDE(n, d) \
//	    ( (n) / (d) - (( (n) < 0 && (n) % (d) < 0 ) ? 1 : 0) )
//
//	/* Deletes bit no in val,
//	   moves all bits larger than no one down,
//	   and inserts a zero at the top. */
//	#define WIPEBIT(val, no) ( ((~(-1<<(no)))&(val)) \
//	                           | (( (-1<<((no)+1)) & (val)) >>1) )
	/*
	 * Yields TRUE iff the bit bit_no is set in val.
	 */
//	#define TEST_BIT(val, bit_no)      	(((val) & (1u << (bit_no))) == (1u << (bit_no)))
	public static boolean TEST_BIT(int val, int bit_no){
		return (((val) & (1 << (bit_no))) == (1 << (bit_no)));
	}
	
//	/*
//	 * If cond is TRUE it yields a value where only the bit bit_no is
//	 * set. If cond is FALSE it yields 0.
//	 */
//	#define COND_SET_BIT(cond, bit_no) 	((cond) ? (1u << (bit_no)) : 0)

	/* This is duplicated in rand.h to avoid extra includes: */
	public static final int MAX_UINT32 = 0xFFFFFFFF;
	public static final int MAX_UINT16 = 0xFFFF;
	public static final int MAX_UINT8 = 0xFF;

//	#define ARRAY_SIZE(x) (sizeof(x) / sizeof((x)[0]))
//	#define ADD_TO_POINTER(p, n) ((void *)((char *)(p)+(n)))
//
//	/****************************************************************************
//	  Used to initialize an array 'a' of size 'size' with value 'val' in each
//	  element. Note that the value is evaluated for each element.
//	****************************************************************************/
//	#define INITIALIZE_ARRAY(array, size, value)				    \
//	  {									    \
//	    int _ini_index;							    \
//	    									    \
//	    for (_ini_index = 0; _ini_index < (size); _ini_index++) {		    \
//	      (array)[_ini_index] = (value);					    \
//	    }									    \
//	  }
//
//
//	/* Bitvectors. */
//	#define _BV_BYTES(bits)		((((bits) - 1) / 8) + 1)
//	#define _BV_BYTE_INDEX(bits)	((bits) / 8)
//	#define _BV_BITMASK(bit)	(1u << ((bit) & 0x7))
//	#ifdef DEBUG
//	#  define _BV_ASSERT(bv, bit) assert((bit) >= 0 \
//	                                     && (bit) < sizeof((bv).vec) * 8)
//	#else
//	#  define _BV_ASSERT(bv, bit) (void)0
//	#endif
//	#define BV_ISSET(bv, bit) \
//	  (_BV_ASSERT(bv, bit), \
//	   ((bv).vec[_BV_BYTE_INDEX(bit)] & _BV_BITMASK(bit)) != 0)
//	#define BV_SET(bv, bit) \
//	  do { _BV_ASSERT(bv, bit); \
//	       (bv).vec[_BV_BYTE_INDEX(bit)] |= _BV_BITMASK(bit); } while(FALSE)
//	#define BV_CLR(bv, bit) \
//	  do { _BV_ASSERT(bv, bit); \
//	       (bv).vec[_BV_BYTE_INDEX(bit)] &= ~_BV_BITMASK(bit); } while(FALSE)
//	#define BV_CLR_ALL(bv) \
//	  do { memset((bv).vec, 0, sizeof((bv).vec)); } while(FALSE)
//	#define BV_SET_ALL(bv) \
//	  do { memset((bv).vec, 0xff, sizeof((bv).vec)); } while(FALSE)
//	bool bv_check_mask(final unsigned char *vec1, final unsigned char *vec2,
//			   size_t size1, size_t size2);
//	#define BV_CHECK_MASK(vec1, vec2) \
//	  bv_check_mask((vec1).vec, (vec2).vec, sizeof((vec1).vec), sizeof((vec2).vec))
//	#define BV_ISSET_ANY(vec) BV_CHECK_MASK(vec, vec)
//
//	bool bv_are_equal(final unsigned char *vec1, final unsigned char *vec2,
//			  size_t size1, size_t size2);
//	#define BV_ARE_EQUAL(vec1, vec2) \
//	  bv_are_equal((vec1).vec, (vec2).vec, sizeof((vec1).vec), sizeof((vec2).vec))
//
//	#define BV_DEFINE(name, bits) \
//	  typedef struct { unsigned char vec[_BV_BYTES(bits)]; } name
//
//	char *create_centered_string(final char *s);
//
//	char * get_option(final char *option_name,char **argv,int *i,int argc);
//	bool is_option(final char *option_name,char *option);
//	int get_tokens(final char *str, char **tokens, size_t num_tokens,
//		       final char *delimiterset);
//
//	final char *big_int_to_text(unsigned int mantissa, unsigned int exponent);
//	final char *int_to_text(unsigned int number);
//
//	bool is_ascii_name(final char *name);
//	final char *Shared.textyear(int year);
//	int compare_strings(final void *first, final void *second);
//	int compare_strings_ptrs(final void *first, final void *second);
//
//	char *skip_leading_spaces(char *s);
//	void remove_leading_trailing_spaces(char *s);
//	int wordwrap_string(char *s, int len);
//
//	bool check_strlen(final char *str, size_t len, final char *errmsg);
//	size_t loud_strlcpy(char *buffer, final char *str, size_t len,
//			   final char *errmsg);
//	/* Convenience macro. */
//	#define sz_loud_strlcpy(buffer, str, errmsg) \
//	    loud_strlcpy(buffer, str, sizeof(buffer), errmsg)
//
//	char *end_of_strn(char *str, int *nleft);
//	int cat_snprintf(char *str, size_t n, final char *format, ...)
//	     fc__attribute((__format__ (__printf__, 3, 4)));
//
//	#define util.die(...) real_util.die(__FILE__, __LINE__, __VA_ARGS__)
//	void real_util.die(final char *file, int line, final char *format, ...)
//	      fc__attribute((__format__ (__printf__, 3, 4)));
//
//	/**************************************************************************
//	...
//	**************************************************************************/
//	struct datafile {
//	  char *name;		/* descriptive file name string */
//	  char *fullname;	/* full absolute filename */
//	  time_t mtime;		/* last modification time  */
//	};
//
//	#define SPECLIST_TAG datafile
//	#define SPECLIST_TYPE struct datafile
//	#include "speclist.h"
//	#define datafile_list_iterate(list, pnode) \
//	  TYPED_LIST_ITERATE(struct datafile, list, pnode)
//	#define datafile_list_iterate_end LIST_ITERATE_END
//	                                                                               
//	char *user_home_dir(void);
//	final char *user_username(void);
//	final char **datafilelist(final char *suffix);
//	Speclists<datafile> datafilelist_infix(final char *subpath,
//	    final char *infix, bool nodups);
//	char *datafilename(final char *filename);
//	char **datafilenames(final char *filename);
//	char *datafilename_required(final char *filename);
//
//	void init_nls(void);
//	void dont_run_as_root(final char *argv0, final char *fallback);
//
//	/*** matching prefixes: ***/
//
//	enum m_pre_result {
//	  M_PRE_EXACT,		/* matches with exact length */
//	  M_PRE_ONLY,		/* only matching prefix */
//	  m_pre_result.M_PRE_AMBIGUOUS,	/* first of multiple matching prefixes */
//	  M_PRE_EMPTY,		/* prefix is empty string (no match) */
//	  M_PRE_LONG,		/* prefix is too long (no match) */
//	  M_PRE_FAIL,		/* no match at all */
//	  M_PRE_LAST		/* flag value */
//	};
//
//	final char *m_pre_description(enum m_pre_result result);
//
//	/* function type to access a name from an index: */
//	typedef final char *(*m_pre_accessor_fn_t)(int);
//
//	/* function type to compare prefix: */
//	typedef int (*m_pre_strncmp_fn_t)(final char *, final char *, size_t n);
//
//	enum m_pre_result match_prefix(m_pre_accessor_fn_t accessor_fn,
//				       size_t n_names,
//				       size_t max_len_name,
//				       m_pre_strncmp_fn_t cmp_fn,
//				       final char *prefix,
//				       int *ind_result);
//
//	char *get_multicast_group(void);
//	void interpret_tilde(char* buf, size_t buf_size, final char* filename);
//
//	bool make_dir(final char *pathname);
//	bool path_is_absolute(final char *filename);
}
