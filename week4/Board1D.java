package w4;
import edu.princeton.cs.algs4.Stack;

//is the same as the "Board" class, but in 1 dimension to reduce the space allocated
public class Board1D 
{
   public int[] board;
   public static int N;
   
   public Board1D(int[][] blocks)           // construct a board from an n-by-n array of blocks (where blocks[i][j] = block in row i, column j)
   {
      N = blocks.length;
      board = new int[N*N];
      for(int row = 0; row < N; row++)
         for(int col = 0; col < N ; col++)
            board[xy21D(row, col)] = blocks[row][col];
   }
   
   public Board1D(int[] blocks)           // construct a board from an n-by-n array of blocks (where blocks[i][j] = block in row i, column j)
   {
      N = blocks.length;
      board = new int[N];
      for(int i = 0; i < N; i++)
         board[i] = blocks[i];
   }
   
   private int xy21D (int row, int col)
   { return row * N + col + 1; }
   
   public int dimension()                 // board dimension n
   { return N; }
   
   public int hamming()                   // number of blocks out of place
   {
      int numberOfBlocKsOutOfSpace = 0;
      
      for(int i = 0; i < N-1; i++)  //N-1 because the last cell is the empty one
         if(board[i] != i+1 )
            numberOfBlocKsOutOfSpace++;
               
      return numberOfBlocKsOutOfSpace;
   }
   
   
   //return the row of a certain value in the correct board
   private static int GetRow(int value)
   {
      if(value < N*N)
         return (value - 1) / N;
      return -1;               
   }
   
   //return the column of a certain value in the correct board
   private static int GetColumn(int value)
   {
      if(value < N*N)
         return (value - 1) % N;
      return -1;
   }
   
   public int manhattan()                 // sum of Manhattan distances between blocks and goal
   {
      int manhattandDistance = 0;
      int[] correctBoard = new int[N];   
      
      for(int i = 0; i < N; i++)
         correctBoard[i] = i+1;
      
      correctBoard[N] = 0;
      
      for(int i = 0; i < N; i++)
         for(int j = 0; j < N ; j++)
         {
            if (board[xy21D(i,j)] != correctBoard[xy21D(i,j)] && board[xy21D(i,j)] != 0 )
            {
               int row = GetRow(board[xy21D(i,j)]);
               int col = GetColumn(board[xy21D(i,j)]);
               
               if(row-i > 0)
                  manhattandDistance += row-i;
               else
                  manhattandDistance += i-row;
               
               if (col-j > 0)
                  manhattandDistance += col-j;
               else
                  manhattandDistance += j-col;
            }
         }
      
      return manhattandDistance;
   }
   
   public boolean isGoal()                // is this board the goal board?
   { return hamming() == 0; }
   
   public Board1D twin()                    // a board that is obtained by exchanging any pair of blocks
   {
      Board1D toReturn = new Board1D(this.board);
      int n = this.N;
      
      for (int i = 0; i < n; i++)
         for(int j = 0; j < n; j++)
         {
            if (toReturn.board[xy21D(i, j)] != 0)
            {
               for(int k = 0; k < n; k++)
                  for(int l = 0; l < n; l++)
                     if(toReturn.board[xy21D(k, l)] != 0 && (i != k || j != l) )
                     {
                        int tmp = toReturn.board[xy21D(i, j)];
                        toReturn.board[xy21D(i, j)]= toReturn.board[xy21D(k, l)];
                        toReturn.board[xy21D(k, l)] = tmp;
                        return toReturn;
                     }
            }
         }
      
      return toReturn;
   }
   
   public boolean equals(Object y)        // does this board equal y?
   {
      if (y == this)
         return true;
      
      if (y == null)
         return false;
      
      if (y.getClass() != this.getClass())
         return false;
      
      Board1D that = (Board1D)y;
      
      int n = this.N;
      for(int i = 0; i < n; i++)
         for(int j = 0; j < n; j++)
            if(this.board[xy21D(i,j)] != that.board[xy21D(i,j)])
               return false;
      return true;
   }
   
   public Iterable<Board1D> neighbors()     // all neighboring boards
   {
      Stack<Board1D> stack = new Stack<Board1D>();
      
      int row = 0;
      int col = 0;      
      
      for(int i = 0; i < N; i++)
         for(int j = 0; j < N ; j++)
         {
            if(board[xy21D(i,j)] == 0)
            {
               row = i; 
               col = j;
               break;
            }
         }
      
      if(row - 1 >= 0)
      {
         Board1D local = new Board1D(board);
         local.swap(row, col, row -1, col);
         stack.push(local);
      }
      
      if(row + 1 < N)
      {
         Board1D local = new Board1D(board);
         local.swap(row, col, row + 1, col);
         stack.push(local);
      }
      
      if(col - 1 >= 0)
      {
         Board1D local = new Board1D(board);
         local.swap(row, col, row, col-1);
         stack.push(local);        
      }
      
      if(col + 1 < N)
      {
         Board1D local = new Board1D(board);
         local.swap(row, col, row, col+1);
         stack.push(local);         
      }
            
      return stack;
   }
   
   private void swap(int row1, int col1, int row2, int col2)
   {
      int tmp = board[xy21D(row1,col1)];
      board[xy21D(row1,col1)] = board[xy21D(row2,col2)];
      board[xy21D(row2,col2)] = tmp;
   }
   
   private void swap(int i, int j)
   {
      int tmp = board[i];
      board[i] = board[j];
      board[j] = tmp;
   }
   
   public String toString()               // string representation of this board (in the output format specified below)
   {
      StringBuilder s = new StringBuilder();
      s.append(this.N + "\n");
      for (int i = 0; i < N; i++) {
          for (int j = 0; j < N; j++) {
              s.append(String.format("%2d ", board[xy21D(i,j)]));
          }
          s.append("\n");
      }
      return s.toString();
   }
   
   public static void main(String[] args) // unit tests (not graded)
   {}
}
