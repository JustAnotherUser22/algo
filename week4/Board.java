package w4;
import edu.princeton.cs.algs4.Stack;

public class Board 
{
   public int[][] board;
   public static int N;
   
   public Board(int[][] blocks)           // construct a board from an n-by-n array of blocks (where blocks[i][j] = block in row i, column j)
   {
      N = blocks.length;
      board = new int[N][N];
      for(int i = 0; i < N; i++)
         for(int j = 0; j < N ; j++)
            board[i][j] = blocks[i][j];
   }
   
   
   public int dimension()                 // board dimension n
   { return N; }
   
   public int hamming()                   // number of blocks out of place
   {
      int numberOfBlocKsOutOfSpace = 0;
      /*if (board[0][0] != 1)
         numberOfBlocKsOutOfSpace++;
      if (board[0][1] != 2)
         numberOfBlocKsOutOfSpace++;
      if (board[0][2] != 3)
         numberOfBlocKsOutOfSpace++;
      if (board[1][0] != 4)
         numberOfBlocKsOutOfSpace++;
      if (board[1][1] != 5)
         numberOfBlocKsOutOfSpace++;
      if (board[1][2] != 6)
         numberOfBlocKsOutOfSpace++;
      if (board[2][0] != 7)
         numberOfBlocKsOutOfSpace++;
      if (board[2][1] != 8)
         numberOfBlocKsOutOfSpace++;
      
      return numberOfBlocKsOutOfSpace;*/
      
      //for a board with dimensions N*N use the following
      for(int row = 0; row < N; row++)
         for(int col = 0; col < N; col++)
         {
            if(row == N-1 && col == N-1)
               break;
            if(board[row][col] != row * N + col + 1)
               numberOfBlocKsOutOfSpace++;
         }
               
      return numberOfBlocKsOutOfSpace;
   }
   
   /*questa qua e la funzione sotto servono per ritornare in un colpo solo 
    * entrambe le coordinate, ma ho qualche problema a instanziare la classe
    * private class Coordinates
   {
      public int row;
      public int col;
      
      public Coordinates(int row, int col)
      {
         this.row = row;
         this.col = col;
      }
   }
   
   private static Coordinates findPositionOfValue(Board b,int value)
   {
      int n = b.N;
      for(int i = 0; i < n; i++)
         for(int j = 0; j < n; j++)
            if(b.board[i][j] == value)
               {
               //return new Coordinates(i, j);
               Coordinates c = new Coordinates(1, 1);
               return c;
               }
      return new Coordinates(-1, -1);
   }*/
   
   //return the row of a certain value in the correct board
   private static int GetRow(int value)
   {
      /*if (value == 1 || value == 2 || value == 3)
         return 0;
      if (value == 4 || value == 5 || value == 6)
         return 1;
      if (value == 7 || value == 8)
         return 2;
      return -1;*/
      
      //per una board di lato N usa questo
      /*int toReturn = 0;
      if(value < N*N)
         toReturn = value / N;
      if (value % N == 0)
         toReturn--;
      if(toReturn != -1)
         return toReturn;
      return -1;  
      */
      return (value - 1) / N;                
   }
   
   //return the column of a certain value in the correct board
   private static int GetColumn(int value)
   {
      /*if (value == 1 || value == 4 || value == 7)
         return 0;
      if (value == 2 || value == 5 || value == 8)
         return 1;
      if (value == 3 || value == 6)
         return 2;
      return -1;*/
      
      //per una board di lato N usa questo
      if(value < N*N)
         return (value - 1) % N;
      return -1;
   }
   
   public int manhattan()                 // sum of Manhattan distances between blocks and goal
   {
      int manhattandDistance = 0;
      int[][] correctBoard = {{1,2,3},{4,5,6},{7,8,0}};      
      
      for(int i = 0; i < N; i++)
         for(int j = 0; j < N ; j++)
         {
            if (board[i][j] != correctBoard[i][j] && board[i][j] != 0 )
            {
               int row = GetRow(board[i][j]);
               int col = GetColumn(board[i][j]);
               
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
   
   public Board twin()                    // a board that is obtained by exchanging any pair of blocks
   {
      Board toReturn = new Board(this.board);
      int n = this.N;
      
      for (int i = 0; i < n; i++)
         for(int j = 0; j < n; j++)
         {
            if (toReturn.board[i][j] != 0)
            {
               for(int k = 0; k < n; k++)
                  for(int l = 0; l < n; l++)
                     if(toReturn.board[k][l] != 0 && (i != k || j != l) )
                     {
                        int tmp = toReturn.board[i][j];
                        toReturn.board[i][j]= toReturn.board[k][l];
                        toReturn.board[k][l] = tmp;
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
      
      Board that = (Board)y;
      
      int n = this.N;
      for(int i = 0; i < n; i++)
         for(int j = 0; j < n; j++)
            if(this.board[i][j]!= that.board[i][j])
               return false;
      return true;
   }
   
   public Iterable<Board> neighbors()     // all neighboring boards
   {
      Stack<Board> stack = new Stack<Board>();
      
      int row = 0;
      int col = 0;      
      
      for(int i = 0; i < N; i++)
         for(int j = 0; j < N ; j++)
         {
            if(board[i][j] == 0)
            {
               row = i; 
               col = j;
               break;
            }
         }
      
      if(row - 1 >= 0)
      {
         Board local = new Board(board);
         local.swap(row, col, row -1, col);
         stack.push(local);
      }
      
      if(row + 1 < N)
      {
         Board local = new Board(board);
         local.swap(row, col, row + 1, col);
         stack.push(local);
      }
      
      if(col - 1 >= 0)
      {
         Board local = new Board(board);
         local.swap(row, col, row, col-1);
         stack.push(local);        
      }
      
      if(col + 1 < N)
      {
         Board local = new Board(board);
         local.swap(row, col, row, col+1);
         stack.push(local);         
      }
            
      return stack;
   }
   
   private void swap(int row1, int col1, int row2, int col2)
   {
      int tmp = board[row1][col1];
      board[row1][col1] = board[row2][col2];
      board[row2][col2] = tmp;
   }
   
   public String toString()               // string representation of this board (in the output format specified below)
   {
      StringBuilder s = new StringBuilder();
      s.append(this.N + "\n");
      for (int i = 0; i < N; i++) {
          for (int j = 0; j < N; j++) {
              s.append(String.format("%2d ", board[i][j]));
          }
          s.append("\n");
      }
      return s.toString();
   }
   
   public static void main(String[] args) // unit tests (not graded)
   {}
   
}
