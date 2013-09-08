/******************************************************************************
 * Author: Jeroen Knoops
 * Written: 2013-09-08
 *
 * Compilation: $ javac PercolationStats.java
 * Testing: $ java PercolationStats 10 300
 *
 * Client to determine the p in our Percolation System. 
 ******************************************************************************/
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
            while (!p.percolates()) 
            {
                do {
                    i = StdRandom.uniform(1, N+1);
                    j = StdRandom.uniform(1, N+1);
                } while(p.isOpen(i, j));
                p.open(i, j);
                n++;
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
       StdOut.println("95% confidence interval = " + ps.confidenceLo() + ", " 
                                                   + ps.confidenceHi());
   }
   
   // method to calculate confidence interval.
   private double confidenceInterval()
   {
       return (1.96 * stddev()) / Math.sqrt(results.length);
   }
}