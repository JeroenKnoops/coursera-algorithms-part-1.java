public class UnionFind {
    public static void main(String[] args) { 
        int N = StdIn.readInt();
        QuickUnionImprovementUF uf = new QuickUnionImprovementUF(N);
        while (!StdIn.isEmpty())
        {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (!uf.connected(p, q))
            {
                uf.union(p,q);
                StdOut.println(p + " " + q);
            }
        }
    }
           
}