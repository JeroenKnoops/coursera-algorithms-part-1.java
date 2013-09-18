/******************************************************************************
 * Author: Jeroen Knoops
 * Written: 2013-09-08
 *
 * Compilation: $ javac Subset.java
 * Testing: $ java Subset
 *
 * Client to test the RandomizedQueue.
 ******************************************************************************/
public class Subset {
    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        RandomizedQueue<String> rq = new RandomizedQueue<String>();
        while (!StdIn.isEmpty())
        {
            String item = StdIn.readString();
            rq.enqueue(item);
        }
        for (int i = 0; i < N; i++) {
            String item = rq.dequeue();
            StdOut.println(item);
        }
    }
}