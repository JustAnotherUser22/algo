package w4;
import javax.swing.plaf.basic.BasicTreeUI.TreeCancelEditingAction;

import edu.princeton.cs.algs4.*;

public class Solver 
{
   boolean isSolvable = false;
   Node solution;
   
   private class Node
   {
      Board board;
      int moves;
      Node predecessor;
      
      public Node(Board b,int m, Node p)
      {
         board = b;
         moves = m;
         predecessor = p;
      }
   }
   
   public Solver(Board initial)           // find a solution to the initial board (using the A* algorithm)
   {
      if (initial == null)
         throw new java.lang.IllegalArgumentException();
      
      boolean found = false;
      
      MinPQ<Node> priorityQueue = new MinPQ<Node>();
      MinPQ<Node> priorityQueueTwin = new MinPQ<Node>();
      MinPQ<Node> tree = new MinPQ<Node>();
      MinPQ<Node> treeTwin = new MinPQ<Node>();
  
      priorityQueue.insert(new Node(initial, 0, null));
      tree.insert(new Node(initial, 0, null));
      priorityQueueTwin.insert(new Node(initial.twin(), 0, null));
      treeTwin.insert(new Node(initial.twin(), 0, null));
      
      while (!found)
      {
         //remove first node
         Node currentNode = priorityQueue.delMin();
         Node currentNodeTwin = priorityQueueTwin.delMin();
         
         //if it is the goal board, stop
         if(currentNode.board.isGoal())
         {
            found = true;
            isSolvable = true;
            solution = currentNode;
            break;
         }
         
         if(currentNodeTwin.board.isGoal())
         {
            found = true;
            isSolvable = false;
            break;
         }
         
         //compute its neighbors
         Stack<Board> neighbors = (Stack<Board>)currentNode.board.neighbors();
         Stack<Board> neighborsTwin = (Stack<Board>)currentNodeTwin.board.neighbors();
         
         //add them to the priority queue
         while (!neighbors.isEmpty())
         {
            Board local = neighbors.pop();
            
            //add a board only if it is different form its parent
            if(!local.equals(currentNode.board))
            {
               priorityQueue.insert(new Node(local, currentNode.moves + 1, currentNode));
               tree.insert(new Node(local, currentNode.moves + 1, currentNode));
            }
         }
         
         while (!neighborsTwin.isEmpty())
         {
            Board local = neighborsTwin.pop();
            
            //add a board only if it is different form its parent
            if(!local.equals(currentNodeTwin.board))
            {
               priorityQueueTwin.insert(new Node(local, currentNodeTwin.moves + 1, currentNodeTwin));
               treeTwin.insert(new Node(local, currentNodeTwin.moves + 1, currentNodeTwin));
            }
         }
      }
      
      /*if(isSolvable)
      {
         Stack<Board> path = new Stack<Board>();
         
         path.push();
      }*/
     
   }
   
   public boolean isSolvable()            // is the initial board solvable?
   {
      return isSolvable;
   }
   
   public int moves()                     // min number of moves to solve initial board; -1 if unsolvable
   {
      if(!isSolvable())
         return -1;
      
      return solution.moves;
   }
   
   public Iterable<Board> solution()      // sequence of boards in a shortest solution; null if unsolvable
   {
      Stack<Board> path = new Stack<Board>();
      
      if(isSolvable())
      {
         Node local = solution.predecessor;
         while (local != null)
         {
            path.push(local.board);
            local = solution.predecessor;
         }
      }
      
      return path;
   }
   
   public static void main(String[] args) // solve a slider puzzle (given below)
   {
   // create initial board from file
      In in = new In(args[0]);
      int n = in.readInt();
      int[][] blocks = new int[n][n];
      for (int i = 0; i < n; i++)
          for (int j = 0; j < n; j++)
              blocks[i][j] = in.readInt();
      Board initial = new Board(blocks);

      // solve the puzzle
      Solver solver = new Solver(initial);

      // print solution to standard output
      if (!solver.isSolvable())
          StdOut.println("No solution possible");
      else {
          StdOut.println("Minimum number of moves = " + solver.moves());
          for (Board board : solver.solution())
              StdOut.println(board);
      }
   }
}
