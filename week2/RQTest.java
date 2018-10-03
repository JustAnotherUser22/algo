package w2;

public class RQTest 
{
   public static void main(String[] args)   // unit testing (optional)
   {
      testIterator();
   }
   
   private static void testIterator()
   {
      RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();
      
      rq.enqueue(1);
      rq.enqueue(2);
      rq.enqueue(3);
      rq.enqueue(4);
      rq.enqueue(5);
      rq.enqueue(6);
      
      for (int n : rq)
         System.out.println(n);
   }
   
   private static void testRandomQueue()
   {
RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();
      
      rq.enqueue(1);
      rq.enqueue(2);
      rq.enqueue(3);
      rq.enqueue(4);
      rq.enqueue(5);
      rq.enqueue(6);
      print(rq);
      rq.dequeue();
      print(rq);
      rq.dequeue();
      print(rq);
      rq.dequeue();
      print(rq);
      rq.dequeue();
      print(rq);
      rq.dequeue();
      print(rq);
      rq.dequeue();
      print(rq);
   }
   
   private static void print (RandomizedQueue<Integer> rq)
   {
      if (rq.isEmpty())
         System.out.print("\\\\  ");
      else
      {
         for( Integer i : rq)
            System.out.print(i + "  ");         
      }
      
      System.out.print("(" + rq.size() + ")");
      
      System.out.println();
   }
}
