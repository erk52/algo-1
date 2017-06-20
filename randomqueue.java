

import java.util.*;
import java.lang.*;
import java.io.*;

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
       if (size+1 > arrsize){
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
           int loc = size; //replace with random selection
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
       Item value = data[size-1];
       size--;
       if (size == arrsize/4){
           Item[] newarr = (Item[]) new Object[arrsize/2];
           for (int i=0; i<size;i++){
               newarr[i] = data[i];
           }
           data = newarr;
           arrsize = arrsize/2;
       }
       return value;
   }
   //public Item sample()                     // return (but do not remove) a random item
   
   private class ListIterator implements Iterator<Item>{
        private int current;
        public ListIterator() { current = 0; }
        public boolean hasNext() { return current < size; }
        
        public Item next()
        {
            Item item = data[current];
            current++;
            return item;
        }
   }
   
   public Iterator<Item> iterator()         // return an independent iterator over items in random order
   { return new ListIterator(); }
   //public static void main(String[] args)   // unit testing (optional)
}
