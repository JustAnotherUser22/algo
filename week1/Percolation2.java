package w1p1;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

//versione che coregge il problema del backwash
public class Percolation2
{
   private WeightedQuickUnionUF top;
   private WeightedQuickUnionUF bottom;
   private boolean[] status;
   private int DIM;
   private static final boolean BLOCKED = false;
   private static final boolean OPEN = true;
   
   public Percolation2(int n)                // create n-by-n grid, with all sites blocked
   {
      if(n <= 0)
         throw new IllegalArgumentException();
      
      top = new WeightedQuickUnionUF(n*n + 2);
      bottom  = new WeightedQuickUnionUF(n*n + 2);
      
      status = new boolean[n*n+2];
      for (int j=0; j < n*n+2; j++)
         status[j]= BLOCKED; 
      
      DIM = n;
   }
   
   public void open(int row, int col)    // open site (row, col) if it is not open already
   {
      validate(row, col);
      if(!isOpen(row, col))
      {
         //open the site
         status[xy21D(row, col)] = OPEN;
         
         int currentCell = xy21D(row, col);
         
         //check its neighbors
         int newRow = row - 1;
         int newCol = col;
                  
         if (!(newCol < 1 || newCol > DIM || newRow < 1 || newRow > DIM))     //if the neighbor is in the grid
            if (isOpen( newRow, newCol))                                  //if the neighbor is open
            {
               top.union(/*xy21D(row, col)*/currentCell, xy21D(newRow, newCol));
               bottom.union(/*xy21D(row, col)*/currentCell, xy21D(newRow, newCol));
            }
         
         newRow = row + 1;
         newCol = col;
         
         if (!(newCol < 1 || newCol > DIM || newRow < 1 || newRow > DIM))     //if the neighbor is in the grid
            if (isOpen( newRow, newCol))                                  //if the neighbor is open
            {
               top.union(/*xy21D(row, col)*/currentCell, xy21D(newRow, newCol));
               bottom.union(/*xy21D(row, col)*/currentCell, xy21D(newRow, newCol));
            }
             

         newRow = row;
         newCol = col + 1;
         
         if (!(newCol < 1 || newCol > DIM || newRow < 1 || newRow > DIM))     //if the neighbor is in the grid
            if (isOpen( newRow, newCol))                                  //if the neighbor is open
            {
               top.union(/*xy21D(row, col)*/currentCell, xy21D(newRow, newCol));
               bottom.union(/*xy21D(row, col)*/currentCell, xy21D(newRow, newCol));
            }
         
         newRow = row;
         newCol = col - 1;
         
         if (!(newCol < 1 || newCol > DIM || newRow < 1 || newRow > DIM))     //if the neighbor is in the grid
            if (isOpen( newRow, newCol))                                  //if the neighbor is open
            {
               top.union(/*xy21D(row, col)*/currentCell, xy21D(newRow, newCol));
               bottom.union(/*xy21D(row, col)*/currentCell, xy21D(newRow, newCol));
            }
         
         if (isTopSite(xy21D(row, col)))
            top.union(0, xy21D(row, col));
         
         if (isBottomSite(xy21D(row, col)))
            bottom.union(DIM*DIM-1, xy21D(row, col));         
      }
   }
      
   public boolean isOpen(int row, int col)  // is site (row, col) open?
   {
      validate(row, col);
      return status[xy21D(row, col)] == OPEN;
   }
   
   
   private int xy21D(int row, int col)
   {
     // return row * DIM + col;
     return (row-1) * DIM + col;
   }
      
   public boolean isFull(int row, int col)  // is site (row, col) full?
   {
      validate(row, col);
      //if the site is open and it is connected to the top site return true
      return (status[xy21D(row, col)] == OPEN && top.connected(0, xy21D(row, col)));
   }
   
   
   public int numberOfOpenSites()       // number of open sites
   {
      int counter = 0;
      
      for (int i = 0; i < DIM*DIM+2; i++)
         if(status[i] == OPEN)
            counter++;
      
      return counter;
   }
   
   private boolean isTopSite(int index) 
   {
      return index <= DIM;
   }

  private boolean isBottomSite(int index) 
  {
      return index >= (DIM - 1) * DIM+ 1;
  }
   
   public boolean percolates()              // does the system percolate?
   {
      return top.connected(0, DIM*DIM+1);
   }
   
   
   private void validate(int row, int col)
   {
      if (col < 1 || col > DIM || row < 1 || row > DIM)
         throw new IllegalArgumentException();
   }
   
   public static void main(String[] args) 
   {}
}
