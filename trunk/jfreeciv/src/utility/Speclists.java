package utility;

import java.util.Iterator;
import java.util.List;

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
