package utility;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
/* speclists: "specific genlists", by dwp.
(A generalisation of previous city_list and unit_list stuff.)

This file is used to implement a "specific" genlist.
That is, a (sometimes) type-checked genlist.  (Or at least a
genlist with related functions with distinctly typed parameters.)
(Or, maybe, what you end up doing when you don't use C++ ?)

Before including this file, you must define the following:
  SPECLIST_TAG - this tag will be used to form names for functions etc.
You may also define:  
  SPECLIST_TYPE - the typed genlist will contain pointers to this type;
If SPECLIST_TYPE is not defined, then 'struct SPECLIST_TAG' is used.
At the end of this file, these (and other defines) are undef-ed.

Assuming SPECLIST_TAG were 'foo', and SPECLIST_TYPE were 'foo_t',
including this file would provide a struct definition for:
   Speclists<foo>;
and prototypes for the following functions:
   void foo_list_init(struct foo_list *This);
   int  foo_list_size(struct foo_list *This);
   foo_t *foo_list_get(struct foo_list *This, int index);
   void foo_list_insert(struct foo_list *This, foo_t *pfoo);
   void foo_list_insert_back(struct foo_list *This, foo_t *pfoo);
   void foo_list_unlink(struct foo_list *This, foo_t *pfoo);
   void foo_list_unlink_all(struct foo_list *This);
   void foo_list_sort(struct foo_list *This, 
      int (*compar)(final void *, final void *));

You should also define yourself:  (this file cannot do this for you)

#define foo_list_iterate(foolist, pfoo) \
    TYPED_LIST_ITERATE(foo_t, foolist, pfoo)
#define foo_list_iterate_end  LIST_ITERATE_END

Also, in a single .c file, you should include speclist_c.h,
with the same SPECLIST_TAG and SPECLIST_TYPE defined, to
provide the function implementations.

Note this is not protected against multiple inclusions; this is
so that you can have multiple different speclists.  For each
speclist, this file should be included _once_, inside a .h file
which _is_ itself protected against multiple inclusions.
*/

public class Speclists<E>{
	public List<E> data;
	public   void foo_list_init(){
		
	}
	public   int  foo_list_size(){
		return data.size();
	}
	public   E foo_list_get(int index){
		return data.get(index);
	}
	public   void foo_list_insert(E pfoo){
		data.add(0, pfoo);
	}
	public   void foo_list_insert_back(E pfoo){
		data.add(pfoo);
	}
	public   void foo_list_unlink(E pfoo){
		data.remove(pfoo);
	}
	public void foo_list_unlink_all(){
		data.clear();
	}
	interface ICompar{
		int compar(Object l, Object r);
	}
	public void foo_list_sort(ICompar comp){
		//TODO
	}
	public Iterator<E> iterator(){
		return data.iterator();
	}
}
