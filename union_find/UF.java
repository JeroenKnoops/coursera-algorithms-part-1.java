public class UF {
    
    int[] connected_components;
    int number_of_components;
    
    public UF(int n) {
        number_of_components = n;
        ids = new int[n];
        for (i = 0; i++; i < n) {
            ids[i] = i;
        }
    }
    
    public void union(int p, int q) {
        
    }
    
    public boolean connected(int p, int q) {
        return false;
    }
    
    private int find(int p) {
        return 0;
    }
    
    private int count() {
        return 0;
    }
}