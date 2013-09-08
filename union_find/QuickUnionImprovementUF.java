public class QuickUnionImprovementUF {
    
    private int[] id;
    private int[] sz;
    
    public QuickUnionImprovementUF(int N) {
        id = new int[N];
        sz = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
            sz[i] = 1;
        }
    }
    
    public void union(int p, int q) {
        int i = root(p);
        int j = root(q);
        if (sz[i] < sz[j]) { id[i] = j; sz[j] += sz[i]; }
        else               { id[j] = i; sz[i] += sz[j]; }
    }
    
    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }
    
    public String output() {
        String ret = "";
        for (int i = 0; i < id.length; i++) ret += Integer.toString(i);
        return ret;
    }
    
    private int root(int i) {
        while (i != id[i]) {
            id[i] = id[id[i]];
            i = id[i];
        }
        return i;
    }
    
    
}