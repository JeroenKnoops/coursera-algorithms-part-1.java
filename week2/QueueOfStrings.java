public class QueueOfStrings 
{
    public static void main(String[] args)
    {
        LinkedQueueOfStrings stack = new LinkedQueueOfStrings();
        while (!StdIn.isEmpty())
        {
            String s = StdIn.readString();
            if (s.equals("-")) StdOut.print(stack.dequeue());
            else               stack.enqueue(s);
        }
    }
}