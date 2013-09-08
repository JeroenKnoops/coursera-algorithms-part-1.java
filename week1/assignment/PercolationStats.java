public class PercolationStats {

   private double[] results;
   
   // perform T independent computational experiments on an N-by-N grid
   public PercolationStats(int N, int T)    
   {
       if (N <= 0 || T <= 0)
            throw new IllegalArgumentException("Negative numbers are not allowed");
        int i, j, n;
        Percolation p;
        results = new double[T];
        for (int t = 0; t < T; t++) 
        {
            n = 0;
            p = new Percolation(N);
            // StdArrayIO.print(p.openArray());
            while (!p.percolates()) 
            {
                do {
                    i = StdRandom.uniform(0, N);
                    j = StdRandom.uniform(0, N);
                } while(p.isOpen(i, j));
                p.open(i, j);
                n++;
                //StdOut.println("---======== " + n + " ========---");
                //StdArrayIO.print(p.openArray());
            }
            results[t] = ((double) n) / (N*N);
        }
   }
   
   // sample mean of percolation threshold
   public double mean() { return StdStats.mean(results); }                     

       // sample standard deviation of percolation threshold
   public double stddev() { return StdStats.stddev(results); }                  

   // returns lower bound of the 95% confidence interval
   public double confidenceLo() { return mean() - confidenceInterval(); }
       
   // returns upper bound of the 95% confidence interval
   public double confidenceHi() { return mean() + confidenceInterval(); }            
   
   // test client, described below    
   public static void main(String[] args) 
   { 
       int N = Integer.parseInt(args[0]);
       int T = Integer.parseInt(args[1]);
       PercolationStats ps = new PercolationStats(N, T);
       StdOut.println("mean                    = " + ps.mean());
       StdOut.println("stddev                  = " + ps.stddev());
       StdOut.println("95% confidence interval = " + ps.confidenceLo() + ", " + ps.confidenceHi());
   }
   
   private double confidenceInterval()
   {
       return (1.96 * stddev()) / Math.sqrt(results.length);
   }
}