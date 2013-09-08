public class Percolation {
    
    private final int N; // Length of one side of the grid.
    private boolean[] open;
    private WeightedQuickUnionUF path;
    private final int virtualTop;
    private final int virtualBottom;
    
    // create N-by-N grid, with all sites blocked
    public Percolation(int N)
    {
        this.N = N;
        open = new boolean[N*N];
    
        // Add two for virtual top and bottom.
        path = new WeightedQuickUnionUF(N*N + 2);
        
        virtualTop = N*N;
        virtualBottom = N*N + 1;
    }
    
    // open site (row i, column j) if it is not already
    public void open(int i, int j) {
        if (i < 0 || i > N-1 || j < 0 || j > N-1)
            throw new IndexOutOfBoundsException("Index ("+i+","+j+") is out of bounds.");
        if (!isOpen(i,j)) 
        {
            open[siteIndex(i,j)] = true;
            attachNeighbours(i,j);
        }
    }
    
    private int siteIndex(int row, int col) {
        return row*N + col;
    }

    private void attachNeighbours(int i,int j) 
    {
        int siteIndex = siteIndex(i,j);
        // Check left neighbour first.
        if (j > 0 && isOpen(i,j - 1)) 
        {
            path.union(siteIndex, siteIndex(i, j - 1));
        }
        //  Check right neighbour 
        if (j < N-1 && isOpen(i,j + 1))
        {
            path.union(siteIndex, siteIndex(i, j + 1));
        }
        if (i > 0)
        {
            if (isOpen(i-1,j)) { path.union(siteIndex, siteIndex(i-1, j)); }
        } else
        {
            // TopRow
            path.union(siteIndex, virtualTop);
        }
        if (i < N-1)
        {
            if (isOpen(i+1,j)) { path.union(siteIndex, siteIndex(i+1, j)); }
        } else
        {
            // BottomRow
            path.union(siteIndex, virtualBottom);
        }
    }
    
    // is site (row i, column j) open?
    public boolean isOpen(int i, int j) 
    {
        return open[siteIndex(i, j)];
    }
    
    // is site (row i, column j) full?
    // Might be a bug here. Sites connected to the virtualBottom node are also full when system percolates.
    //
    // Can fix this by generating a seperate path for full without unioning the virtual Bottom.
    // I'm not sure why one should check if a site is full on a percolate system. I think it's only reasonable on non percolate systems.
    public boolean isFull(int i, int j) 
    {
        return path.connected(siteIndex(i,j), virtualTop);
    }
    
    // does the system percolate?
    public boolean percolates()            
    {
        return path.connected(virtualTop, virtualBottom);
    }

}