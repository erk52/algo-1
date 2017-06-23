import edu.princeton.cs.algs4.StdRandom.*;
//import java.util.iterator;
import java.util.*;
import java.lang.*;

public class RandomizedQueue<Item> implements Iterable<Item> {
   private Item[] data;
   private int size = 0;
   private int arrsize = 0;
   
   public RandomizedQueue()                 // construct an empty randomized queue
   {
    data = (Item[]) new Object[2];
    arrsize = 2;    
       
   }
   public boolean isEmpty()                 // is the queue empty?
   { return size == 0;}
   public int size()                        // return the number of items on the queue
   { return size; }
   public void enqueue(Item item)           // add the item
   {
	   if (item == null) {
		throw new IllegalArgumentException("Can't add null item");
	   }
	   if (size+1 >= arrsize){
           Item[] newarr = (Item[]) new Object[arrsize*2];
           for (int i=0; i<arrsize;i++){
               newarr[i] = data[i];
           }
           data = newarr;
           arrsize = arrsize*2;
       }
       if (size == 0){
           data[0] = item;
           size++;
       }
       else{
           int loc = edu.princeton.cs.algs4.StdRandom.uniform(size+1); //replace with random selection
           if (loc == size){
               data[size] = item;
           }
           else{
               Item temp = data[loc];
               data[loc] = item;
               data[size] = temp;
           }
           size++;
       }
   }
   public Item dequeue()                    // remove and return a random item
   {
	   if (size == 0) {
			throw new NoSuchElementException("Cannot dequeue from empty queue");
	   }
       Item value = data[size-1];
       size--;
       if (size <= arrsize/4){
           Item[] newarr = (Item[]) new Object[arrsize/2];
           for (int i=0; i<size;i++){
               newarr[i] = data[i];
           }
           data = newarr;
           arrsize = arrsize/2;
       }
       return value;
   }
   public Item sample()                     // return (but do not remove) a random item
   {
	   if (size == 0) {
			throw new NoSuchElementException("Cannot dequeue from empty queue");
	   }
		int sampler = edu.princeton.cs.algs4.StdRandom.uniform(size);
		return data[sampler];
   }
   
   private class ListIterator implements Iterator<Item>{
        private int current;
		private int[] order;
        public ListIterator() 
		{
			current = 0; 
			order = new int[size];
			for (int i=0;i<size;i++){
				order[i] = i;
			}
			edu.princeton.cs.algs4.StdRandom.shuffle(order);
		}
        public boolean hasNext() { return current < size; }
        public void remove() { throw new UnsupportedOperationException("Remove not supported"); }
        public Item next()
        {
			if (current >= size){
				throw new NoSuchElementException("No more items");
			}
            Item item = data[order[current]];
            current++;
            return item;
        }
   }
   
   public Iterator<Item> iterator()         // return an independent iterator over items in random order
   { return new ListIterator(); }
   
   //public static void main(String[] args)   // unit testing (optional)
}