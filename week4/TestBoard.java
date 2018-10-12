package w4;

public class TestBoard 
{
   public static void main(String[] args)
   {
      /*int[][] correctBoard = {{1,2,3},{4,5,6},{7,8,0}}; 
      Board board = new Board(correctBoard);
      printBoard(board);*/
      testNeighbors();
   }

   public static void testNeighbors()
   {
      int[][] correctBoard = {{1,2,3},{4,0,6},{7,8,5}}; 
      Board board = new Board(correctBoard);
      Iterable<Board> stack = board.neighbors();
      int i = 1;
      for(Board b : stack)
      {
         System.out.println("neighbor n: " + Integer.toString(i));
         printBoard(b);
         System.out.println("--------------");
         i++;
      }
   }
   
   public static void testEqual()
   {
      int[][] correctBoard = {{1,2,3},{4,5,6},{7,8,0}}; 
      Board board = new Board(correctBoard);
      
      int[][] anotherBoard = {{1,2,3},{4,5,6},{7,8,0}}; 
      Board board2 = new Board(anotherBoard);
      
      if(board.equals(board2))
         System.out.println("ok");
      else
         System.out.println("no...");
   }
   
   public static void testManhattan()
   {
      /*int[][] array = {{1,2,3},{4,5,6},{7,8,0}}; 
      Board board = new Board(array);
      System.out.println(board.hamming());
      
      array = new int [][] {{8,1,3},{4,0,2},{7,6,5}}; 
      board = new Board(array);
      System.out.println(board.hamming());*/
      

      int[][] array = {{8,1,3},{4,0,2},{7,6,5}}; 
      Board board = new Board(array);
      System.out.println(board.manhattan());
   }
   
   public static void testTwin()
   {
      int[][] correctBoard = {{1,2,3},{4,5,6},{7,8,0}}; 
      Board board = new Board(correctBoard);
      printBoard(board);
      System.out.println("--------------");
      printBoard(board.twin());
   }
   
   public static void printBoard(Board b)
   {
      for(int i = 0; i < 3; i++)
      {
         for (int j = 0; j < 3; j++)
            System.out.print(b.board[i][j] + " ");
         System.out.println();
      }
   }
}
