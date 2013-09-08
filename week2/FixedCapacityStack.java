public class FixedCapacityStack<Item> 
{
    private Item[] s;
    private int N = 0;
    
    public FixedCapacityStack(int capacity)
    { s = (Item[]) new Object[capacity]; }
    
    public boolean isEmpty() 
    { return N == 0; }
    
    public void push(Item item)
    { s[N++] = item; }
    
    public Item pop() 
    { 
        Item item = s[--N];
        s[N] = null;
        return item;
    }
}