/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mergesort;

/**
 *
 * @author Mirko
 */
public class Mergesort
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        char[] array = "examplemergesort".toCharArray();
        
        System.out.println(array);
        
        sort(array);
        
        System.out.println(array);
    }
    


    private static void merge(char[] a, char[] aux, int lo, int mid, int hi)
    {
        assert isSorted(a, lo, mid); // precondition: a[lo..mid] sorted
        assert isSorted(a, mid+1, hi); // precondition: a[mid+1..hi] sorted

        for (int k = lo; k <= hi; k++)
            aux[k] = a[k];
        
        int i = lo, j = mid+1;
        
        for (int k = lo; k <= hi; k++)
        {
            if (i > mid) a[k] = aux[j++];
            else if (j > hi) a[k] = aux[i++];
            else if (less(aux[j], aux[i])) a[k] = aux[j++];
            else a[k] = aux[i++];
        }

        assert isSorted(a, lo, hi); // postcondition: a[lo..hi] sorted
    }

    
    /*
    recursive call to the function is troublesome to understand (for me...)
    supposing a.length = 11 the values would be:
    ( lo and hi are passed to the function, mid is computed inside the function)
    
                    lo  |   hi  |   mid
    first call      0   |   10  |   5
        first sort      0   |   5   |   2
            first sort      0   |   2   |   1
                first sort      0   |   1   |   0
                    first sort      0   |   0   
                    second sort     1   |   1   
                    merge           0   |   0   |   1       merge character 0 and 1
                second sort     2   |   2                  
                merge           0   |   1   |   2       erore(?)
            second sort    3   |   5   |   4
                first sort      3   |   4   |   3
                    first sort      3   |   3
                    second sort     4   |   4
                    merge           3   |   3   |   4       merge character 3 and 4
                second sort     5   |   5
                merge           3   |   4   |   5   error(?)
            merge           0   |   2   |   5       error(?)
        second sort    6   |   10   |   8
            first sort      6   |   8   |   7
                first sort      6   |   7   |   6
                    first sort
                    second sort
                    merge
                second sort     8   |   8
                merge
            second sort     8   |   10  |   9
            merge           6   |   8   |   10
            
    
      example with real array
    
                        9-8-7-6-5-4-3-2-1-0
                   9-8-7-6-5       4-3-2-1-0
                 9-8-7    6-5
                9-8  7
                9 8
                8-9 
                     7
                 7-8-9
                         6 5
                         5-6
                  5-6-7-8-9
                                    4-3-2  1-0
                                   4-3  2
                                   4 3
                                   3-4
                                        2
                                    2-3-4
                                           1 0
                                           0-1
                                     
                                    0-1-2-3-4    
                        0-1-2-3-4-5-6-7-8-9
    
    
    */
    private static void sort(char[] a, char[] aux, int lo, int hi)
    {
        if (hi <= lo) return;           //if hi <= lo something is wrong and I stop the computation
        
        int mid = lo + (hi - lo) / 2;   //compute point in the middle
        
        //recursive call to the function, it keeps splitting the array in half and start to merge the first half
        //at the beginnign it sorts only a couple of elements and then continue to increase the array size
        sort(a, aux, lo, mid);          
        sort(a, aux, mid+1, hi);
        
        merge(a, aux, lo, mid, hi);     //merge the array
    }
    
    
    public static void sort(char[] a)
    {
        char[] aux = new char[a.length];    //define an auxiliary array which is as long as the initial array
        sort(a, aux, 0, a.length - 1);
    }


    private static boolean isSorted(char[] a, int lo, int hi)
    {
        for (int i = lo; i < hi; i++)
            if (less(a[i], a[i-1])) 
                return false;
        return true;
    }

    
    private static boolean less(char a, char b)
    {
        return a < b;
    }
}
