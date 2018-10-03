package w1p1;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class PercolationStats 
{
   private double[] percolationThreshold;
   private int T;
   private double localMean;
   private double localStdDev;
   
   public PercolationStats(int n, int trials)    // perform trials independent experiments on an n-by-n grid
   {
      if (n <= 0 || trials <= 0)
         throw new IllegalArgumentException();
      
      percolationThreshold = new double[trials];
      T = trials;
      
      for (int i = 0; i < trials; i++)
      {
         Percolation p = new Percolation(n);
         //System.out.println("t: " + Integer.toString(i));
         while (!p.percolates())
         {
            int randRow = StdRandom.uniform(n) + 1;
            int randCol = StdRandom.uniform(n) + 1;
            
            if (!p.isOpen(randRow, randCol))
            {
               p.open(randRow, randCol);
            }
         }
         
         percolationThreshold[i] = (double)p.numberOfOpenSites() / (n*n);
      }
   }
   
   public double mean()                          // sample mean of percolation threshold
   {
      localMean = StdStats.mean(percolationThreshold);
      return localMean;
   }
   
   public double stddev()                        // sample standard deviation of percolation threshold
   {
      localStdDev = StdStats.stddev(percolationThreshold);
      return localStdDev;
   }
   
   public double confidenceLo()                  // low  endpoint of 95% confidence interval
   {
      return localMean - 1.96 * localStdDev / Math.sqrt(T);
   }
   
   public double confidenceHi()                  // high endpoint of 95% confidence interval
   {
      return localMean + 1.96 * localStdDev / Math.sqrt(T);
   }
   
   public static void main(String[] args)        // test client (described below)
   {
      int n = Integer.parseInt(args[0]); //grid dimension
      int T = Integer.parseInt(args[1]); //number of tests to perform
      
      if (n < 0 || T < 0)
         throw new java.lang.IllegalArgumentException();
      
      //System.out.println("n: " + n);
      //System.out.println("T: " + T);
      
      PercolationStats ps = new PercolationStats(n, T);
      
      System.out.println("mean = " + ps.mean());
      System.out.println("stddev = " + ps.stddev());
      System.out.println("95% confidence interval = [" + ps.confidenceLo() + ", " + ps.confidenceHi() + " ]");
   }
}
