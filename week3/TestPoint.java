package w3;

import java.util.Arrays;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class TestPoint 
{
   public static void SinglePointTest()
   {
      /*Point a = new Point(10, 10);
      Point b = new Point(10, 11);
      Point c = new Point(11, 10);
      Point d = new Point(10, 9);
      Point e = new Point(9, 9);
      Point f = new Point(10, 10);
      Point g = new Point(321, 456);

      Point[] list = new Point[7];
      
      list[0] = b;
      list[1] = a;
      list[2] = c;
      list[3] = d;
      list[4] = e;
      list[5] = f;
      list[6] = g;
      
      
      Arrays.sort(list, a.slopeOrder());

      for(int i=0;i<7;i++)
         System.out.println(list[i].toString() + "slope: " + a.slopeTo(list[i]));
      
      Arrays.sort(list);
      
      for(int i=0;i<7;i++)
         System.out.println(list[i].toString());
      */
      
      In in = new In("horizontal5.txt");      // input file
      int n = in.readInt();         // n-by-n percolation system
      
      Point[] points = new Point[n];
      
      for (int i = 0; i < n; i++)
      {
         int n1 = in.readInt();
         int n2 = in.readInt();
         points[i] = new Point(n1,  n2); 
      }
      
      Arrays.sort(points, points[0].slopeOrder());
      
      for(int i=0;i<n;i++)
         System.out.println(points[i].toString() + "slope: " + points[0].slopeTo(points[i]));
      
   }
   
   public static void FastTest()
   {
      In in = new In("horizontal5-2.txt");      // input file
      int n = in.readInt();         // n-by-n percolation system
      Point[] points = new Point[n];
      
      for (int i = 0; i < n; i++)
      {
         int n1 = in.readInt();
         int n2 = in.readInt();
         points[i] = new Point(n1,  n2);
      }
      
      // draw the points
      StdDraw.enableDoubleBuffering();
      //StdDraw.setXscale(0, 32768);
      //StdDraw.setYscale(0, 32768);
      StdDraw.setXscale(0, 5);
      StdDraw.setYscale(0, 5);
    
      for (Point p : points) 
          p.draw();
      
      StdDraw.show();
      
      //FastCollinearPoints fcp = new FastCollinearPoints(points);
      //System.out.println(fcp.numberOfSegments());
      
      // print and draw the line segments
      FastCollinearPoints collinear = new FastCollinearPoints(points);
      for (LineSegment segment : collinear.segments())
      {
          StdOut.println(segment);
          segment.draw();
      }
      StdDraw.show();
   }
   
   public static void main(String[] args)
   {
      FastTest();
      //SinglePointTest();
   }

}
