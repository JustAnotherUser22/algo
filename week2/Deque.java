package w2;
import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> 
{
   
   private Node first = null;
   private Node last = null;
   private int numberOfNodes = 0;
   
   private class Node
   {
       Item item;
       Node nextNode;
       Node previousNode;
       
       public Node()
       {
           item = null;
           nextNode = null;
           previousNode = null;
       }
   }

   public Deque()                           // construct an empty deque
   {}
   
   public boolean isEmpty()                 // is the deque empty?
   { 
      return (first == null && last == null);
      //return (first == last);
   }
   
   public int size()                        // return the number of items on the deque
   { return numberOfNodes; }
   
   public void addFirst(Item item)          // add the item to the front
   {
       if (item == null)
           throw new java.lang.IllegalArgumentException();
       
       Node oldFirst = first;
       first = new Node();
       first.item = item;
       first.nextNode = oldFirst;
       
       if (oldFirst != null)
          oldFirst.previousNode = first;   //--
       
       numberOfNodes++;
       
       if (last == null)
          last = first;
 
   }
   
   public void addLast(Item item)           // add the item to the end
   {
       if (item == null)
           throw new java.lang.IllegalArgumentException();
       
       Node oldLast = last;
       last = new Node();
       last.item = item;
       last.nextNode = null;
       last.previousNode = oldLast;     //--
       
       if (oldLast != null)
          oldLast.nextNode = last;
       
       numberOfNodes++;
       
       if (first == null)
          first = last;
   }
   
   public Item removeFirst()                // remove and return the item from the front
   {
       if (isEmpty())
           throw new java.util.NoSuchElementException();
       
       Item toReturn = first.item;
       first = first.nextNode;
       
       if (first != null)  //to avoid proble when i remove the last item in the deque
          first.previousNode = null;   //--
       else
          last = null;
       
       numberOfNodes--;
       return toReturn;
   }
   
   public Item removeLast()                 // remove and return the item from the end
   {
       if (isEmpty())
           throw new java.util.NoSuchElementException();
       
       Item toReturn = last.item;
       last = last.previousNode;
       
       if (last != null)
          last.nextNode = null;
       else
          first = null;
       
       numberOfNodes--;
       return toReturn;
   }
   
   public Iterator<Item> iterator()         // return an iterator over items in order from front to end
   { return new ListIterator(); }
   
   private class ListIterator implements Iterator<Item>
   {
       private Node current = first;
       
       public boolean hasNext()
       { return current != null; }
       
       public void remove()
       { throw new java.lang.UnsupportedOperationException(); }
       
       public Item next()
       {
           if (!hasNext())
               throw new java.util.NoSuchElementException();
           
           Item item = current.item;
           current = current.nextNode;
           return item;
       }
   }
   
   private void debug()
   {
       Deque<Integer> d = new Deque<Integer>();
       d.addFirst(1);
       d.addFirst(2);
       d.addFirst(3);
       d.addLast(4);
       d.addLast(5);
       d.addLast(6);
       print(d);
       System.out.println("remove first: " + d.removeFirst());
       print(d);
       System.out.println("remove last: " + d.removeLast());
       print(d);
       
       /*foreach (Item i in d)
        foreach (Item s in d)
            System.out.print(s + "  ");*/
   }
   
   private static void print (Deque<Integer> d)
   {
      /*Node current = new Node();
      
      current = d.first;
      
      while (current != null)
      {
         System.out.print(d.current.item + "  ");
         current = current.nextNode;
      }
            
      System.out.print(i + "  ");
      System.out.println();*/
      
      for( Integer i : d)
         System.out.print(i + "  ");
   }
   
   public static void main(String[] args)   // unit testing (optional)
   {}
}