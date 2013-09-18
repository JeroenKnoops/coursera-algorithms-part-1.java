import java.util.Iterator;

/******************************************************************************
 * Author: Jeroen Knoops
 * Written: 2013-09-18
 *
 * Compilation: $ javac Deque.java
 * Testing: $ java Deque
 *
 * Data type to model a Deque aka Deck.
 ******************************************************************************/
public class Deque<Item> implements Iterable<Item> {
    private Node first, last;
    private int size;
    
    private class Node
    {
        private Item item;
        private Node next;
        private Node prev; 
    }
    
   public Deque()                     // construct an empty deque
   {
   }
   
   public boolean isEmpty()           // is the deque empty?
   {
       return size == 0;
   }
   
   public int size()                  // return the number of items on the deque
   {
       return size;
   }
   
   public void addFirst(Item item)    // insert the item at the front
   { 
       if (item == null) { throw new java.lang.NullPointerException(
                                                   "Not allowed to add null"); }
       Node oldfirst = first;
       first = new Node();
       first.item = item;
       first.next = oldfirst;
       first.prev = null;
       if (oldfirst != null) oldfirst.prev = first;
       else                  last = first;
       size++;
   }
   
   public void addLast(Item item)     // insert the item at the end
   { 
       if (item == null) { throw new java.lang.NullPointerException(
                                                    "Not allowed to add null"); }
       Node oldlast = last;
       last = new Node();
       last.item = item;
       last.next = null;
       last.prev = oldlast;
       if (isEmpty()) first = last;
       else if (oldlast != null) oldlast.next = last;
       size++;
   }

   public Item removeFirst()          // delete and return the item at the front
   {
       if (isEmpty()) {
           throw new java.util.NoSuchElementException("Deque is empty");
       } 
       Item item = first.item;
       first = first.next;
       if (first != null) first.prev = null;
       if (isEmpty()) last = null;
       size--;
       return item;
   }

   public Item removeLast()           // delete and return the item at the end
   {
       if (isEmpty()) {
           throw new java.util.NoSuchElementException("Deque is empty");
       } 
       Node oldlast = last;
       Item item = last.item;
       last = oldlast.prev;
       if (last != null) last.next = null;
       oldlast.prev = null;
       oldlast.item = null;
       if (isEmpty()) first = null;
       size--;
       return item;
   }
   
   // return an iterator over items in order from front to end
   public Iterator<Item> iterator()   
   {
       return new DequeIterator();
   }
   
   private class DequeIterator implements Iterator<Item>
   {
       private Node current = first;
   
       public boolean hasNext() { return current != null; }
       
       public void remove() { 
           throw new java.lang.UnsupportedOperationException(
                                                "remove is not premitted");
       }
       
       public Item next() 
       {
           if (!hasNext()) {
               throw new java.util.NoSuchElementException("No items in deque");
           }
           Item item = current.item;
           current = current.next;
           return item;
       }
   }
   
    public static void main(String[] args) 
    {
        // Test case: NullPointer when adding null to deque.
       Deque<String> d = new Deque<String>();
       try { 
           String str = null;
           d.addFirst(str); 
           StdOut.println("Test should throw java.lang.NullPointerException: "
                          + "FAIL!");
       } catch (java.lang.NullPointerException e)
       {
           StdOut.println("Test should throw java.lang.NullPointerException: "
                          + "Successfull!");
       }

       // Test case: Throw java.util.NoSuchElementException when deque is 
       //            empty and someone tries to remove an item. 
       d = new Deque<String>();
       try {
           d.removeFirst();
           StdOut.println("Test should throw java.util.NoSuchElementException: " 
                          + "FAIL!");
       } catch (java.util.NoSuchElementException e)
       {
           StdOut.println("Test should throw java.util.NoSuchElementException: "
                          + "Successfull!");
       }

       try {
           d.removeLast();
           StdOut.println("Test should throw java.util.NoSuchElementException: "
                           + "FAIL!");
       } catch (java.util.NoSuchElementException e)
       {
           StdOut.println("Test should throw java.util.NoSuchElementException: "
                          + "Successfull!");
       }

       // Test case: Add stuff at the front, end and fetch the 
       //            data from front and end. 
       d = new Deque<String>();
       // Make deque 1,2,3,4
       d.addFirst("3");
       d.addFirst("2");
       d.addLast("4");
       d.addFirst("1");
       StdOut.println("1 == " + d.removeFirst() + ": size: " + d.size());
       d.addFirst("0");
       // deque: 0,2,3,4
       StdOut.println("4 == " + d.removeLast() + ": size: " + d.size());
       StdOut.println("3 == " + d.removeLast() + ": size: " + d.size());
       StdOut.println("0 == " + d.removeFirst() + ": size: " + d.size());
       StdOut.println("2 == " + d.removeLast() + ": size: " + d.size());
       try { 
           d.removeLast(); 
       } catch (java.util.NoSuchElementException e) 
         { StdOut.println("deque is empty"); }
       
       d.addLast("5");
       StdOut.println("5 == " + d.removeFirst() + ": size: " + d.size());
    }       
}