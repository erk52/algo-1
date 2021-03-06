

import java.util.*;
import java.lang.*;
import java.io.*;
public class Program
{
	public static void main(String[] args) {
		Deque<String> myQ = new Deque<String>();
		myQ.addFirst("first_add");
		myQ.addFirst("second_add");
		myQ.addLast("added last");
		for (String s : myQ){
		    System.out.println(s);
		}
	}
}

class Deque<Item> implements Iterable<Item> {
    private class Node<Item> {
        Item data;
        Node next;
        Node prev;
    }
    
    private int size;
    Node<Item> head;
    Node<Item> tail;
   public Deque()                           // construct an empty deque
   {
       size = 0;
   }
   public boolean isEmpty()                 // is the deque empty?
   { return size == 0; }
   public int size()                        // return the number of items on the deque
   { return size; }
   
   public void addFirst(Item item)          // add the item to the front
   {
       Node<Item> newhead = new Node<Item>();
       newhead.next = head;
       newhead.data = item;
       size++;
       if (size > 1){
           head.prev = newhead;
       }
       head = newhead;
       
       if (size == 1){
           tail = head;
       }
   }
   
   
   public void addLast(Item item)           // add the item to the end
    {
        Node<Item> newtail = new Node<Item>();
        newtail.prev = tail;
        newtail.data = item;
        size++;
        if (size > 1){
            tail.next = newtail;
        }
        tail = newtail;
        
        if (size == 1){
            head = tail;
            
        }
    }  
   public Item removeFirst()                // remove and return the item from the front
   {
       Item output = head.data;
       head = head.next;
       size--;
       if (size == 0){
           tail = null;
       }
       return output;
   }
   
   public Item removeLast()                 // remove and return the item from the end
   {
       Item output = tail.data;
       tail = tail.prev;
       size--;
       if (size == 0){
           head = null;
       }
       return output;
   }


    private class ListIterator implements Iterator<Item>
    {
        private Node<Item> current;// = head;
        public ListIterator() { current = head; }
        public boolean hasNext() { return current != null; }
        public void remove() { /* not supported */ }
        public Item next()
        {
            Item item = current.data;
            current = current.next;
            return item;
        }
    }
    public Iterator<Item> iterator()
    { return new ListIterator(); }
   //public static void main(String[] args)   // unit testing (optional)*/
}
