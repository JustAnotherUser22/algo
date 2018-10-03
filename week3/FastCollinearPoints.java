package w3;

import java.util.ArrayList;
import java.util.Arrays;



public class FastCollinearPoints {

   private ArrayList<LineSegment> ls = new ArrayList<LineSegment>();

   public FastCollinearPoints(Point[] points) // finds all line segments containing 4 or more points
   {
      checkIfInputVectorIsOk(points);
      
      
      Point[] local = points.clone();
      ArrayList<MinAndMax> addedSegment = new ArrayList<>();

      // sort available points according to y-coordinate
      Point[] localByOrder = local.clone();

      while (localByOrder.length > 2) 
      {

         Arrays.sort(localByOrder);

         Point currentZero = localByOrder[0];

         // sort available point according to the slope with the point with the smallest
         // y-coordinate
         ArrayList<Point> collinearPoints = new ArrayList<Point>();
         // collinearPoints.add(currentZero);

         Point[] sortBySlope = localByOrder.clone();
         Arrays.sort(sortBySlope, currentZero.slopeOrder());

         // check for at least 3 collinear points, then add the "biggest" to the list
         /******************
         per qualche motivo questa è troppo lenta, quella scritta da me più sotto invece va bene
         for (int i = 1; i < sortBySlope.length - 2; i++) 
         {
            if (threePointsAreCollinear(currentZero, sortBySlope[i], sortBySlope[i + 1], sortBySlope[i + 2]))
               collinearPoints.add(sortBySlope[i + 2]);
         }
         *******************/
         
         //controlla tutto l'array, se trova che due valori hanno la stessa "slope"
         //controlla quanti di questi ce ne sono e ritorna solo l'ultimo (cioè il più grande)
         for (int i = 1; i < sortBySlope.length - 2; ) 
         {
            if (currentZero.slopeTo(sortBySlope[i]) == currentZero.slopeTo(sortBySlope[i+1]) )
            {
               //due punti sono allineati, portebbero essercene altri
               int count = 2;
               while (currentZero.slopeTo(sortBySlope[i]) == currentZero.slopeTo(sortBySlope[i+count]) )
               {
                  count++;
                  if (i+count >= sortBySlope.length)
                     break;
               }
               i += (count);
               if (count >= 3)
                  collinearPoints.add(sortBySlope[i-1]);
            }
            else
               i++;
         }
         
         
         
         
         if (!collinearPoints.isEmpty()) 
         {
            for (int i = collinearPoints.size() - 1; i >= 0; i--) 
            {
               Point localMin = currentZero;
               Point localMax = collinearPoints.get(i);
               
               //ls.add(new LineSegment(localMin, localMax));
               if (ls.isEmpty()) 
               {
                  ls.add(new LineSegment(localMin, localMax));
                  addedSegment.add(new MinAndMax(localMin, localMax));
               } 
               else 
               {
                  boolean theFuckingPointIsAlreadyInList = false;

                  for (MinAndMax line : addedSegment)
                     if (line.max.compareTo(localMax) == 0)
                        // if( localMin.slopeTo(line.max) == localMin.slopeTo(localMax) )
                        if (line.min.slopeTo(line.max) == localMin.slopeTo(localMax)) 
                        {
                           theFuckingPointIsAlreadyInList = true;
                           break;
                        }

                  if (!theFuckingPointIsAlreadyInList) 
                  {
                     ls.add(new LineSegment(localMin, localMax));
                     addedSegment.add(new MinAndMax(localMin, localMax));
                     
                  }
               }
            }

         }
         
         Point[] tmp = localByOrder.clone();

         localByOrder = new Point[tmp.length - 1];

         for (int i = 0; i < localByOrder.length; i++)
            localByOrder[i] = tmp[i + 1];
      }
      /*
       * ArrayList<Point> collinearPoints = new ArrayList<Point>();
       * 
       * for (int i = 0; i < points.length; i++) { //order local array according to
       * the slope between the current points and the others Point[] local =
       * points.clone(); Arrays.sort(local); Point currentZero = local[i];
       * collinearPoints.clear(); Arrays.sort(local, currentZero.slopeOrder());
       * collinearPoints.add(currentZero);
       * 
       * for(int j = 1; j < local.length - 2; j++) //salto il primo elemento perchè è
       * l'elemento stesso è ha come slope -infinito { if(
       * threePointsAreCollinear(currentZero, local[j], local[j+1], local[j+2]) ) { if
       * (!collinearPoints.contains(local[j])) collinearPoints.add(local[j]);
       * 
       * if (!collinearPoints.contains(local[j+1])) collinearPoints.add(local[j+1]);
       * 
       * if (!collinearPoints.contains(local[j+2])) collinearPoints.add(local[j+2]); }
       * }
       * 
       * if( !collinearPoints.isEmpty() ) if (collinearPoints.size() >= 4) { Point[]
       * tmp = new Point[collinearPoints.size()];
       * 
       * for (int q = 0; q < tmp.length; q++) tmp[q] = collinearPoints.get(q);
       * 
       * Arrays.sort(tmp);
       * 
       * LineSegment toAdd = new LineSegment(tmp[0], tmp[tmp.length-1]);
       * 
       * if (!ls.contains(toAdd)) ls.add(toAdd); } }
       */
   }

   private boolean threePointsAreCollinear(Point origin, Point a, Point b, Point c) {
      return (origin.slopeTo(a) == origin.slopeTo(b) && origin.slopeTo(a) == origin.slopeTo(c));
   }

   private void checkIfInputVectorIsOk(Point[] points)
   {
      int j, k;
      
      if (points == null)
         throw new java.lang.IllegalArgumentException();
      
      for (j = 0; j < points.length; j++)
         if(points[j] == null)
            throw new java.lang.IllegalArgumentException();
      
      for(j = 0; j < points.length; j++)
         for (k = j+1; k < points.length; k++)
            if(points[j].equals(points[k]))
               throw new java.lang.IllegalArgumentException();
   }
   
   private class MinAndMax {
      public Point min;
      public Point max;
      public double slope;
      
      public MinAndMax(Point min, Point max) {
         this.min = min;
         this.max = max;
         slope = min.slopeTo(max);
      }
   }

   public int numberOfSegments() // the number of line segments
   {
      return ls.size();
   }

   public LineSegment[] segments() // the line segments
   {
      LineSegment[] local = ls.toArray(new LineSegment[ls.size()]);
      return local;
   }
}
