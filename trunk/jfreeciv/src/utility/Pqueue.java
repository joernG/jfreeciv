package utility;

public class Pqueue {
	//TODO
	// struct Pqueue {
	// int size; /* number of occupied cells */
	// int avail; /* total number of cells */
	// int step; /* additional memory allocation step */
	// Short *cells; /* array containing data */
	// int *priorities; /* backup priorities (in case data is changed) */
	// };

	/***************************************************************************
	 * Initialize the queue.
	 * 
	 * initial_size is the numer of queue items for which memory should be
	 * preallocated, that is, the initial size of the item array the queue uses.
	 * If you insert more than n items to the queue, another n items will be
	 * allocated automatically.
	 **************************************************************************/
	public static Pqueue pq_create(int initial_size) {
		// Pqueue q = fc_malloc(sizeof(struct Pqueue));
		//
		// q.cells = fc_malloc(sizeof(short) * initial_size);
		// q.priorities = fc_malloc(sizeof(int) * initial_size);
		// q.avail = initial_size;
		// q.step = initial_size;
		// q.size = 1;
		Pqueue q = new Pqueue();
		return q;
	}

	/***************************************************************************
	 * Destructor for queue structure.
	 **************************************************************************/
	public void pq_destroy() {
		// assert (q != null);
		// free(q.cells);
		// free(q.priorities);
		// free(q);
	}

	 /********************************************************************
	 Insert an item into the queue.
	 *********************************************************************/
	 public void pq_insert(short datum, int datum_priority)
	 {
	// int i;
	//
	// assert(q != null);
	//
	// /* allocate more memory if necessary */
	// if (q.size >= q.avail) {
	// int newsize = q.size + q.step;
	//
	// q.cells = fc_realloc(q.cells, sizeof(short) * newsize);
	// q.priorities = fc_realloc(q.priorities, sizeof(int) * newsize);
	// q.avail = newsize;
	// }
	//
	// /* insert item */
	// i = q.size++;
	// while (i > 1 && q.priorities[i / 2] < datum_priority) {
	// q.cells[i] = q.cells[i / 2];
	// q.priorities[i] = q.priorities[i / 2];
	// i /= 2;
	// }
	// q.cells[i] = datum;
	// q.priorities[i] = datum_priority;
	 }
	
	/***************************************************************************
	 * Remove the highest-ranking item from the queue and store it in dest. dest
	 * maybe null.
	 * 
	 * Return value: true The value of the item that has been removed. false No
	 * item could be removed, because the queue was empty.
	 **************************************************************************/
	public boolean pq_remove(Short dest) {
		// short tmp;
		// int tmp_priority;
		// short top;
		// int i = 1;
		//
		// assert(q != null);
		//
		// if (q.size == 1) {
		// return false;
		// }
		//
		// assert(q.size <= q.avail);
		// top = q.cells[1];
		// q.size--;
		// tmp = q.cells[q.size];
		// tmp_priority = q.priorities[q.size];
		// while (i <= q.size / 2) {
		// int j = 2 * i;
		// if (j < q.size && q.priorities[j] < q.priorities[j + 1]) {
		// j++;
		// }
		// if (q.priorities[j] <= tmp_priority) {
		// break;
		// }
		// q.cells[i] = q.cells[j];
		// q.priorities[i] = q.priorities[j];
		// i = j;
		// }
		// q.cells[i] = tmp;
		// q.priorities[i] = tmp_priority;
		// if(dest) {
		// *dest = top;
		// }
		return true;
	}

	/***************************************************************************
	 * Store the highest-ranking item in dest without removing it
	 * 
	 * Return values: true dest was set. false The queue is empty.
	 **************************************************************************/
	public boolean pq_peek(Short dest)
	 {
//		 assert(q != null);
//		 if (q.size == 1) {
//			 return false;
//		 }
//
//		 *dest = q.cells[1];
		 return true;
	 }
}