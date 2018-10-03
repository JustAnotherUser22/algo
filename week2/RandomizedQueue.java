package w2;
import java.util.Iterator;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> 
{
   
   private Item[] s;
   private int head = 0;
   private int tail = 0;   
   
   public RandomizedQueue()                 // construct an empty randomized queue
   { s = (Item[]) new Object[1]; }

   public boolean isEmpty()                 // is the randomized queue empty?
   { return tail == head; }

   public int size()                        // return the number of items on the randomized queue
   { return tail - head; }

   public void enqueue(Item item)           // add the item
   {
       if (item == null)
           throw new java.lang.IllegalArgumentException();
       
       if (tail == s.length)
           resize(2 * s.length);

       s[tail++] = item;
   }

   public Item dequeue()                    // remove and return a random item
   {
       if (isEmpty())
           throw new java.util.NoSuchElementException();
       
       int rand = StdRandom.uniform(size());   //select random index
       Item toReturn = s[rand];  //select the item at the random index   //Item toReturn = new Item ( s[rand] );
       s[rand] = s[--tail];   //swap the selected item with the last available
       s[tail] = null;  //remove last element
       
       return toReturn;
       
       //if (tail < s.length / 4)      //se occupo meno di 1/4 del vettore
      // {
           /*for (int i = head; i < tail; i++)    //trasla tutto sul primo quarto
               s[i - head] = s[i];
           head = 0;
           tail = tail - head;*/
        //  resize(s.length / 2);
      // }
       
       //return toReturn;
   }

   public Item sample()                     // return a random item (but do not remove it)
   {
       if (isEmpty())
           throw new java.util.NoSuchElementException();
       
       int rand = StdRandom.uniform(size());
       return s[rand];
   }

    private void resize(int capacity)
    {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < tail; i++)
            copy[i] = s[i];
        s = copy;
    }
       
   public Iterator<Item> iterator()         // return an independent iterator over items in random order
   { return new IteratorList(); }

   private class IteratorList implements Iterator<Item>
   {
       /*int currentIndex = 0;
       
       public boolean hasNext()
       { return s[currentIndex] != null; }
       
       public void remove()
       { throw new java.lang.UnsupportedOperationException(); }
       
       public Item next()
       {
           if (!hasNext())
               throw new java.util.NoSuchElementException();
           
           return s[currentIndex++];
       }*/
      
      Item[] local = s.clone();
      
      
   } 
   
   public static void main(String[] args)   // unit testing (optional)
   {}
   
}