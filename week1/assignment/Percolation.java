public class Percolation {
    
    private final int N; // Length of one side of the grid.
    private boolean[] open;            // Array with sites
    private WeightedQuickUnionUF path; // UF for percolation
    private WeightedQuickUnionUF full; // UF for full
    private final int virtualTop;      // virtual top site.
    private final int virtualBottom;   // virtual bottom site.
    
    // create N-by-N grid, with all sites blocked
    public Percolation(int N)
    {
        this.N = N;
        open = new boolean[N*N];
    
        // Add two for virtual top and bottom.
        path = new WeightedQuickUnionUF(N*N + 2);
        full = new WeightedQuickUnionUF(N*N + 2);
        
        virtualTop = N*N;
        virtualBottom = N*N + 1;
    }
    
    // open site (row i, column j) if it is not already
    public void open(int i, int j) {
        checkIndices(i, j);
        if (!isOpen(i, j)) 
        {
            open[siteIndex(i, j)] = true;
            attachNeighbours(i, j);
        }
    }
        
    // is site (row i, column j) open?
    public boolean isOpen(int i, int j) 
    {
        checkIndices(i, j);
        return open[siteIndex(i, j)];
    }
    
    // is site (row i, column j) full?
    public boolean isFull(int i, int j) 
    {
        checkIndices(i, j);
        return full.connected(siteIndex(i, j), virtualTop);
    }
    
    // does the system percolate?
    public boolean percolates()            
    {
        return path.connected(virtualTop, virtualBottom);
    }

    // returns the siteIndex for a given row and col
    private int siteIndex(int row, int col) {
        return (row-1)*N + col - 1;
    }

    // attaches open neighbours
    private void attachNeighbours(int i, int j) 
    {
        int siteIndex = siteIndex(i, j);
        // Check left neighbour first.
        if (j > 1 && isOpen(i, j - 1)) 
        {
            union(siteIndex, siteIndex(i, j - 1));
        }
        //  Check right neighbour 
        if (j < N && isOpen(i, j + 1))
        {
            union(siteIndex, siteIndex(i, j + 1));
        }
        if (i > 1)
        {
            if (isOpen(i-1, j)) 
            { 
                union(siteIndex, siteIndex(i-1, j)); 
            }
        } else
        {
            // TopRow
            union(siteIndex, virtualTop);
        }
        if (i < N)
        {
            if (isOpen(i+1, j)) 
            { 
                union(siteIndex, siteIndex(i+1, j)); 
            }
        } else
        {
            // BottomRow
            union(siteIndex, virtualBottom);
        }
    }
    
    // Adds connection in path and if target is not the bottom row, in full_path.
    private void union(int i, int j)
    {
        path.union(i, j);
        if (j != virtualBottom)
            full.union(i, j);
    }

    // Checks Indices. Will throw an exception otherwise.
    private void checkIndices(int i, int j) {
        if (i < 1 || i > N || j < 1 || j > N)
            throw new IndexOutOfBoundsException("Index (" + i + ","
                                                + j +") is out of bounds.");

    }

    public static void main(String[] args) 
    {
        // Bad test case
        Percolation p = new Percolation(2);
        p.open(1, 1);
        p.open(2, 2);
        StdOut.println("Test should fail: " + p.percolates());
       
        // Good test case
        p = new Percolation(2);
        p.open(1, 1);
        p.open(2, 1);
        StdOut.println("Test should pass: " + p.percolates());
 
        // Good test case
        p = new Percolation(4);
        p.open(1, 1);
        p.open(2, 1);
        p.open(2, 2);
        p.open(3, 2);
        p.open(4, 2);
       StdOut.println("Test should pass: " + p.percolates());
 
    }

 
}