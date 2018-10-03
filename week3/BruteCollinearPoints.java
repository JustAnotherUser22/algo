package w3;

import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints 
{
   
   private ArrayList<LineSegment> ls = new ArrayList<LineSegment>();
   
   public BruteCollinearPoints(Point[] points)    // finds all line segments containing 4 points
   {
      int i, j, k ,l;
      
      if (points == null)
         throw new java.lang.IllegalArgumentException();
      
      for (i = 0; i < points.length; i++)
         if(points[i] == null)
            throw new java.lang.IllegalArgumentException();
      
      for(i = 0; i < points.length; i++)
         for (j = i+1; j < points.length; j++)
            if(points[i].equals(points[j]))
               throw new java.lang.IllegalArgumentException();
      
      Point[] local = points;
      
      //order local according to "compareTo" method in "Point"
      Arrays.sort(local);
      
      for (i = 0; i < local.length; i++)
         for (j = i+1; j < local.length; j++)
            for (k = j+1; k < local.length; k++)
               for (l = k+1; l < local.length; l++)
                  if (local[i].slopeTo(local[j]) == local[i].slopeTo(local[k]) && 
                      local[i].slopeTo(local[k]) == local[i].slopeTo(local[l]) )     
                     ls.add(new LineSegment(local[i], local[l]));
   }
   
   public int numberOfSegments()        // the number of line segments
   {
      //controlla se un valore è presente più volte nella lista, se si lo elimina
      for(int i = 0; i < ls.size(); i++)
         for(int j = i+1; j < ls.size(); j++)
            if (ls.get(i) == ls.get(j))
            {
               ls.remove(j);
               j--;  //così non aggiorno l'indice
            }
      
      return ls.size();
   }
   
   
   public LineSegment[] segments()                // the line segments
   {
      LineSegment[] local = new LineSegment[ls.size()];
      
      for (int i = 0; i < ls.size(); i++)
         local[i] = ls.get(i);
      
      return local;
   }
}
