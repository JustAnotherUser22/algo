/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myprintbinarytree;

import java.util.ArrayList;
import java.util.List;
import jdk.nashorn.internal.parser.TokenType;

/**
 *
 * @author Mirko
 */
public class MyPrintBinaryTree
{
    public static void main(String[] args)
    {       
        /*int[] tree = {0, 1, 2, 3, 4,5 ,6,7,8,9,-1,-1, 2, 3, 4, 5};
        System.out.println("tree depth is : " + FindMaxDepth(tree));
        PrintTree(tree);
        PrintTreewithBrenches(tree);*/
        
        Node root = new Node(0);
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        
        
        root.left = n1;
        root.left.left = n2;
        root.left.left.left = n3;
        //root.left.left.left.left = new Node(4);
        //root.left.left = new Node(5);
        //root.left.left.right = new Node(6);
        
        System.out.println( Integer.toString(FindMaxDepth(root) -1));
        PrintTreeWithBranches(root);       
    }
    
    
    /*
    example: consider the following vector:
                [0,1,2,3,4,5,6,7,8,9]
        start     0 | 2 |   4     |9
        stop      1 | 3 |   8     |9    
        depth     0 | 1 |   2     |3 
    
    
        depth   dist    init dist
          0       //        7                   |       x
          1       7         3                   |   x       x
          2       3         1                   | x   x   x   x
          3       1         0                   |x x x x x x x x
    
          dist = distance between a number and the next value
          init dist = distance between the "edge" and the first number
    */
    
    static void PrintTree(int [] tree)
    {
        int depth = 0;  //livello di profondità nell'albero
        int start = 1;  //indice del primo numero da stampare
        int stop = 1;   //indice dell'ultimo numero da stampare
        int maxDepth = FindMaxDepth(tree);
        boolean isFirstNumber = true;   //the first number need a different amount of spaces
        
        while(start < tree.length)
        {
            while(start <= stop && start < tree.length)
            {
                if(isFirstNumber)
                {
                    PrintSpaces((int)Math.pow(2, maxDepth-depth) - 1);
                    isFirstNumber = false;
                }
                else
                  PrintSpaces((int)Math.pow(2, maxDepth-(depth-1))-1);  
                
                
                if(tree[start] == -1)   //-1 is the "null" value
                    System.out.print(' ');
                else
                    System.out.print(tree[start]);
                
                start++;
            }
                        
            System.out.println();
            depth++;
            isFirstNumber = true;
            
            int end = (int)Math.pow(2, depth);
            
            start = end;
            stop = 2*end -1;
        }            
    }
    
    /*
        depth   dist    init dist
          0       //        7                   |       x                  brenchLength    space between '/' and '\'       space between '\' and '/'
                                                       / \                      4                       1                               //
                                                      /   \                     3                       3                               //
                                                     /     \                    2                       5                               //
                                                    /       \                   1                       7                               //
          1       7         3                   |   x       x
                                                   / \     / \                  2                       1                               5
                                                  /   \   /   \                 1                       3                               3
          2       3         1                   | x   x   x   x
                                                 / \ / \ / \ / \                1                       1                               1
          3       1         0                   |x x x x x x x x
    
    
    
      depth   dist    init dist 
        0      //         15                                  x               brenchLength    space between '/' and '\'       space between '\' and '/'
                                                             / \                    8                       1                                                           
                                                            /   \                   7                       3
                                                           /     \                  6                       5
                                                          /       \                 5                       7
                                                         /         \                4                       9
                                                        /           \               3                       11
                                                       /             \              2                       13
                                                      /               \             1                       15
        1       15        7                   |       x               x      
                                                     / \             / \            4                       1                               13
                                                    /   \           /   \           3                       3                               11
                                                   /     \         /     \          2                       5                               9
                                                  /       \       /       \         1                       7                               7
        2       7         3                   |   x       x       x       x
                                                 / \     / \     / \     / \        2                       1                               5
                                                /   \   /   \   /   \   /   \       1                       3                               3
        3       3         1                   | x   x   x   x   x   x   x   x
                                               / \ / \ / \ / \ / \ / \ / \ / \      1                       1                               1
        4       1         0                   |x x x x x x x x x x x x x x x x

      distance of first '/' = distance of the first number - 1
    */
    static void PrintTreewithBrenches(int [] tree)
    {
        int depth = 0;  //livello di profondità nell'albero
        int start = 1;  //indice del primo numero da stampare
        int stop = 1;   //indice dell'ultimo numero da stampare
        int maxDepth = FindMaxDepth(tree);
        boolean isFirstNumber = true;   //the first number need a different amount of spaces
        int fromBottom = 1;
        
        //form the example above the distance between " / -> \ " and " \ -> / " can be expressed using a vector and moving along it upwords or downwards
        int[] distanceArray = new int[(int)Math.pow(2, maxDepth-1)];   
        distanceArray[0] = 1;
        for(int i = 1; i < distanceArray.length; i++)
            distanceArray[i] = distanceArray[i-1] + 2;
        
        //distance from between the edge and the first number to print
        int[] distanceFromEdge = new int[maxDepth+1];
        distanceFromEdge[0] = 0;
        for(int i = 1; i < distanceFromEdge.length; i++)
            distanceFromEdge[i] = 2 * distanceFromEdge[i-1] + 1;
        
        ReverseArray(distanceFromEdge);
        
        //distance between a number and the next one
        int[] distanceBetweenNumbers = new int[maxDepth+1];
        distanceBetweenNumbers[0] = 1;
        for(int i = 1; i < distanceBetweenNumbers.length; i++)
            distanceBetweenNumbers[i] = 2 * distanceBetweenNumbers[i-1] + 1;
        
        ReverseArray(distanceBetweenNumbers);
                
        while(start < tree.length)
        {
            //print the values
            while(start <= stop && start < tree.length)
            {
                if(isFirstNumber)
                {
                    PrintSpaces(distanceFromEdge[depth]);
                    isFirstNumber = false;
                }
                else
                    PrintSpaces(distanceBetweenNumbers[depth]);  
                
                PrintValue(tree[start]);
                
                start++;
            }
                      
            System.out.println();
            
            //print the brencehs
            int numberOfBrenches;                                           //numbers ob brenches to be print ( 2 for the root node, 4 for its children...)
            int distanceBetweenbrenches;                                    //distance between '/' and '/' or between '/' and '/'
            int brenchLength = (int)Math.pow(2, maxDepth - depth - 1);      //"depth" of brenches           
            int maxbrenchLength = (int)Math.pow(2, maxDepth - depth - 1); 
            boolean first = true;           //true if i need to print the fitst '/' character
            char charToBePrint;// = ' ';            
            
            while (brenchLength > 0)
            {
                numberOfBrenches = (int)Math.pow(2, depth + 1);
                
                while (numberOfBrenches > 0)
                {
                    
                    if(numberOfBrenches % 2 == 0)
                    {
                        distanceBetweenbrenches = distanceArray[distanceArray.length - 1 - fromBottom];
                        charToBePrint = '/';
                    } 
                    else
                    {
                        distanceBetweenbrenches = distanceArray[maxbrenchLength - brenchLength];
                        charToBePrint = '\\';
                    }
                                        
                    if(first)
                    {
                        
                        //distanceFromEdge[depth] = distance from the edge to the first number
                        //-1 = the first '/' is one space before the first character
                        //numberOfBrenches - maxDepth = every time I move down across the brenches I should move everything another step on the left
                                                
                        int distanceToEdge = distanceFromEdge[depth] - 1 + brenchLength - maxbrenchLength;
                        PrintSpaces(distanceToEdge);
                        first = false;
                    }
                    else
                        PrintSpaces(distanceBetweenbrenches);

                    System.out.print(charToBePrint);

                    numberOfBrenches--;                    
                }
                
                brenchLength--;
                
                if(depth != 0)      //when depth == 0 I don't have to increment this value
                    fromBottom++;
                
                first = true;
                
                if(brenchLength != 0)
                    System.out.println();
            }
            
            System.out.println();
            depth++;
            isFirstNumber = true;
            
            int end = (int)Math.pow(2, depth);
            
            start = end;
            stop = 2*end -1;
        }            
    }
    
    static void PrintValue(int v)
    {
        if(v == -1)   //-1 is the "null" value
            System.out.print(' ');
        else
            System.out.print(v);
    }
    
    //it is the log with base 2 of the tree length (there is no log2 function so I changed the base, "floor" is used to round down the result), "-1" because the first element of the array is not considered
    static int FindMaxDepth(int [] tree)
    {
        return (int)Math.floor(Math.log((tree.length-1)) / Math.log(2));
    }
    
    static void PrintSpaces(int depth)
    {
        for(int i = 0; i < depth; i++)
            System.out.print(' ');
    }
    
    static void PrintTree(Node n)
    {
        List<Node> nodes = new ArrayList<Node>();
       
        int maxDepth = FindMaxDepth(n) - 1;
        int currentDepth = 0;
        int numberOfnodesInTheCurrentLevel = (int)Math.pow(2, currentDepth);
        int counter = 0;
        boolean isFirstNumber = true;   //the first number need a different amount of spaces
        
        nodes.add(n);
        
        while(!nodes.isEmpty())
        {
            Node currentNode = nodes.remove(0);
            
            if(currentNode != null)
            {
                if(isFirstNumber)
                {
                    PrintSpaces((int)Math.pow(2, maxDepth-currentDepth) - 1);
                    isFirstNumber = false;
                }
                else
                  PrintSpaces((int)Math.pow(2, maxDepth-(currentDepth-1)) -1);  
                
                System.out.print( Integer.toString(currentNode.value));
                nodes.add(currentNode.left);
                nodes.add(currentNode.right);
            }
            else
            {
                if(isFirstNumber)
                {
                    PrintSpaces((int)Math.pow(2, maxDepth-currentDepth) - 1);
                    isFirstNumber = false;
                }
                else
                  PrintSpaces((int)Math.pow(2, maxDepth-(currentDepth-1))-1);  
                
                System.out.print(' ');
            }
            
            counter++;
            
            if(counter == numberOfnodesInTheCurrentLevel)
            {
                counter = 0;
                currentDepth++;
                numberOfnodesInTheCurrentLevel = (int)Math.pow(2, currentDepth);
                System.out.println();
                isFirstNumber = true;
            }
        }
    }
    
    static  void PrintTreeWithBranches(Node n)
    {
        /*List<Node> nodes = new ArrayList<Node>();
       
        int maxDepth = FindMaxDepth(n) - 1;
        int currentDepth = 0;
        int numberOfnodesInTheCurrentLevel = (int)Math.pow(2, currentDepth);
        int counter = 0;
        boolean isFirstNumber = true;   //the first number need a different amount of spaces
        
        //NB: why "+1+1"? the first is because maxDepth does not take into account the root note (which is layer 0)
        // the second one is because even if a node has no other nodes connected to it, it still add two "null" node to the list
        // which are placed in a new layer
        
        //distance from between the edge and the first number to print
        int[] distanceFromEdge = new int[maxDepth+1+1];
        distanceFromEdge[0] = 0;
        for(int i = 1; i < distanceFromEdge.length; i++)
            distanceFromEdge[i] = 2 * distanceFromEdge[i-1] + 1;
        
        ReverseArray(distanceFromEdge);
        
        //distance between a number and the next one
        int[] distanceBetweenNumbers = new int[maxDepth+1+1];
        distanceBetweenNumbers[0] = 1;
        for(int i = 1; i < distanceBetweenNumbers.length; i++)
            distanceBetweenNumbers[i] = 2 * distanceBetweenNumbers[i-1] + 1;
        
        ReverseArray(distanceBetweenNumbers);
        
        
        nodes.add(n);
        
        while(!nodes.isEmpty())
        {
            Node currentNode = nodes.remove(0);
            numberOfnodesInTheCurrentLevel = (int)Math.pow(2, currentDepth);
            
            if(isFirstNumber)
            {
                PrintSpaces(distanceFromEdge[currentDepth]);
                isFirstNumber = false;
            }
            else
                PrintSpaces(distanceBetweenNumbers[currentDepth]);
            
            if(currentNode != null)
            {
                System.out.print( Integer.toString(currentNode.value));
                nodes.add(currentNode.left);
                nodes.add(currentNode.right);
            }
            else
                System.out.print(' ');
            
            counter++;
            
            if(counter == numberOfnodesInTheCurrentLevel)
            {
                counter = 0;
                currentDepth++;
                System.out.println();
                isFirstNumber = true;
            }
        }*/
        
        List<Node> nodes = new ArrayList<Node>();
        int maxDepth = FindMaxDepth(n) - 1;
        
        //NB: why "+1+1"? the first is because maxDepth does not take into account the root note (which is layer 0)
        // the second one is because even if a node has no other nodes connected to it, it still add two "null" node to the list
        // which are placed in a new layer
        int[] treeArray = new int[ (int)Math.pow(2, maxDepth + 1 + 1) ];
        int i = 1;
        
        for (int j = 0; j < treeArray.length; j++)
            treeArray[j] = -1;
        
        treeArray[0] = 0;
        
        nodes.add(n);
        
        while (!nodes.isEmpty() && !ListIsNull(nodes))  //"ListIsNull" occorre perchè nel ciclo continuo ad aggiungere nodi anche se il nodo "padre" ha un valore "null"
        {
            Node currentNode = nodes.remove(0);
            
            if(currentNode != null)
            {
                nodes.add(currentNode.left);
                nodes.add(currentNode.right);
            }
            else
            {
                nodes.add(null);
                nodes.add(null);
            }
            
            if(currentNode == null)
                treeArray[i] = -1;
            else
                treeArray[i] = currentNode.value;
            i++;
        }
        
        //because nodes with no children still add empty nodes, i should remove them to from the final tree
        //to detect the empty nodes I scan the array from the end to the begin and stop as soon as i find a value
        int numberOfUsedCells = treeArray.length - 1;
        for(int j = treeArray.length -1; j > 0; j--)
            if( treeArray[j] == -1)
                numberOfUsedCells--;
            else
                break;
        
        numberOfUsedCells++;
        
        int[] newArray = new int[numberOfUsedCells];
        
        for(int j = 0; j < numberOfUsedCells; j++)
            newArray[j] = treeArray[j];
        
        PrintTreewithBrenches(newArray);
    }
    
    
    static int FindMaxDepth(Node n)
    {
        if(n != null)
            return (Math.max(FindMaxDepth(n.left) + 1, FindMaxDepth(n.right) + 1));
        else
            return 0;
    }
    
    static void ReverseArray(int [] array)
    {
        for(int i = 0; i < array.length / 2; i++)
        {
            int tmp = array[i];
            int j = array.length - 1 -i;
            array[i] = array[j];
            array[j] = tmp;
        }
    }
    
    static boolean ListIsNull (List<Node> l)
    {
        boolean toReturn = true;
        
        for(Node n : l)
            if (n != null)
            {
                toReturn = false;
                break;
            }
        
        return toReturn;
    }
}
