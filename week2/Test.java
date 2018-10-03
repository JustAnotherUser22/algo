package w2;


public class Test
{
   static Deque<Integer> dq = new Deque<Integer>();
   
   public static void addToTop()
   {
      dq.addFirst(1);
      dq.addFirst(2);
      dq.addFirst(3);
      print(dq);
      dq.removeFirst();
      print(dq);
      dq.removeFirst();
      print(dq);
      dq.removeFirst();
      print(dq);      
   }
   
   public static void addToBottom() 
   {
      dq.addLast(1);
      dq.addLast(2);
      dq.addLast(3);
      print(dq);
      dq.removeFirst();
      print(dq);
      dq.removeFirst();
      print(dq);
      dq.removeFirst();
      print(dq);
   }
     
   public static void addRandom()
   {
      dq.addFirst(1);
      dq.addFirst(2);
      dq.addFirst(3);
      dq.addLast(4);
      dq.addLast(5);
      dq.addLast(6);
      print(dq);
      
      /*dq.removeFirst();
      print(dq);
      dq.removeFirst();
      print(dq);
      dq.removeFirst();
      print(dq);
      dq.removeFirst();
      print(dq);
      dq.removeFirst();
      print(dq);
      dq.removeFirst();
      print(dq);*/
      
      dq.removeLast();
      print(dq);
      dq.removeLast();
      print(dq);
      dq.removeLast();
      print(dq);
      dq.removeLast();
      print(dq);
      dq.removeLast();
      print(dq);
      dq.removeLast();
      print(dq);
   }
   
   private static void print (Deque<Integer> d)
   {
      if (d.isEmpty())
         System.out.print("\\\\  ");
      else
      {
         for( Integer i : d)
            System.out.print(i + "  ");         
      }
      
      System.out.print("(" + d.size() + ")");
      
      System.out.println();
   }
   
   public static void main(String[] args)   // unit testing (optional)
   {
      Test.addRandom();
   }
}
