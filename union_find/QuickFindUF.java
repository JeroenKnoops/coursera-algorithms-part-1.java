public class QuickFindUF {
    
    private int[] id;
    
    public QuickFindUF(int N) {
        id = new int[N];
        for (int i = 0; i < N; i++) 
            id[i] = i;
    }
    
    public void union(int p, int q) {
        int pid = id[p];
        int qid = id[q];
        if (!connected(p,q)) {    
            for (int i = 0;  i < id.length; i++) {
                if (id[i] == pid) {
                    id[i] = qid;
                }
            }
        } 
    }
    
    public boolean connected(int p, int q) {
        return id[p] == id[q];
    }
    
    private int find(int p) {
        return 0;
    }
    
    private int count() {
        return 0;
    }
}