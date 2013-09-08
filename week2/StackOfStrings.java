public class StackOfStrings 
{
    public static void main(String[] args)
    {
        LinkedStackOfStrings stack = new LinkedStackOfStrings();
        while (!StdIn.isEmpty())
        {
            String s = StdIn.readString();
            if (s.equals("-")) StdOut.print(stack.pop());
            else               stack.push(s);
        }
    }
}