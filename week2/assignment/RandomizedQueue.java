import java.util.Iterator;

/******************************************************************************
 * Author: Jeroen Knoops
 * Written: 2013-09-18
 *
 * Compilation: $ javac RandomizedQueue.java
 * Testing: $ java RandomizedQueue
 *
 * Data type represent a RandomizeQueue. 
 ******************************************************************************/
public class RandomizedQueue<Item> implements Iterable<Item> {
   private Node first, last;
   private int size;
    
   private class Node
   {
        private Item item;
        private Node next;
        private Node prev;
   }
    
   public RandomizedQueue()         // construct an empty randomized queue
   { }
   
   public boolean isEmpty()           // is the deque empty?
   {
       return size == 0;
   }
   
   public int size()                  // return the number of items on the queue
   {   
       return size;   
   } 
   
   public void enqueue(Item item)     // add the item
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
   
   public Item dequeue()              // delete and return a random item
   {
       if (isEmpty()) {
           throw new java.util.NoSuchElementException("RandomizedQueue is empty");
       } 

       int index = StdRandom.uniform(0, size);
       Node node = findNode(index); 
       
       Item item = node.item;
       Node prevnode = node.prev;
       Node nextnode = node.next;
       
       if (prevnode != null) {
           prevnode.next = nextnode;
       } else {
           first = nextnode;
       }
       
       if (nextnode != null) {
           nextnode.prev = prevnode;
       } else {
           last = prevnode;
       }
       
       node.item = null;
       node.next = null;
       node.prev = null;
       
       size--;
       return item;
   }
   
   public Item sample()               // return (but do not delete) a random item
   {
       if (isEmpty()) {
           throw new java.util.NoSuchElementException("RandomizedQueue is empty");
       } 
       int index = StdRandom.uniform(0, size);
       Node node = findNode(index); 
       return node.item;
   }
   
   // return an independent iterator over items in random order
   public Iterator<Item> iterator()   
   {
       return new RandomizeQueueIterator();
   }
   
   private class RandomizeQueueIterator implements Iterator<Item>
   {
       public boolean hasNext() { return size > 0; }
       
       public void remove() { 
           throw new java.lang.UnsupportedOperationException(
                         "remove is not premitted");
       }
       
       public Item next() 
       {
           return dequeue();
       }
   }

   
   private Node findNode(int index) 
   {
       Node node = null;
       if (index >= size / 2) {
           node = last;
           for (int i = 0; i < size - index - 1; i++) {
               node = node.prev;     
           } 
       } else {
           node = first;
           for (int i = 0; i < index; i++) {
               node = node.next;     
           }
       }
       return node;
   }
   
   public static void main(String[] args) 
   {
       // Test case: NullPointer when adding null to RandomizedQueue.
       RandomizedQueue<String> rq = new RandomizedQueue<String>();
       try { 
           String str = null;
           rq.enqueue(str); 
           StdOut.println("Test should throw java.lang.NullPointerException: "
                          + "FAIL!");
       } catch (java.lang.NullPointerException e)
       {
           StdOut.println("Test should throw java.lang.NullPointerException: "
                          + "Successfull!");
       }

       // Test case: Throw java.util.NoSuchElementException when randomizedQueue 
       //            is empty and someone tries to remove an item. 
       rq = new RandomizedQueue<String>();
       try {
           rq.dequeue();
           StdOut.println("Test should throw " 
                          + "java.util.NoSuchElementException: FAIL!");
       } catch (java.util.NoSuchElementException e)
       {
           StdOut.println("Test should throw " 
                          + "java.util.NoSuchElementException: Successfull!");
       }
       
       // Test case: Add some stuff and show samples. 
       rq = new RandomizedQueue<String>();
       // Make  0,1,2,3,4,5,6,7
       rq.enqueue("7");
       rq.enqueue("6");
       rq.enqueue("5");
       rq.enqueue("4");
       rq.enqueue("3");
       rq.enqueue("2");
       rq.enqueue("1");
       rq.enqueue("0");
       
       // Sample should not remove entries.
       for (int i = 0; i < 10; i++) {
           StdOut.println(rq.sample());
       }
       StdOut.println("Next case.");
       // dequeue should remove entries in random order.
       for (int i = 0; i < 8; i++) {
           String item = rq.dequeue();
           StdOut.println(item);
       }
       
       try {
           rq.dequeue();
           StdOut.println("Test should throw "  
                          + "java.util.NoSuchElementException: FAIL!");
       } catch (java.util.NoSuchElementException e)
       {
           StdOut.println("Test should throw java.util." 
                          + "NoSuchElementException: Successfull!");
       }
    
       for (int i = 0; i < 2; i++) {
           // Make  0,1,2,3,4,5,6,7
           rq.enqueue("7");
           rq.enqueue("6");
           rq.enqueue("5");
           rq.enqueue("4");
           rq.enqueue("3");
           rq.enqueue("2");
           rq.enqueue("1");
           rq.enqueue("0");
       
           for (String s : rq)
               StdOut.println(s);
           StdOut.println();
       }
       
  
   }

}