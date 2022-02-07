package ArtificialIntel.Algo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import ArtificialIntel.Data.Cell;
import ArtificialIntel.Data.Grid;

public class BFS {
    private int V;                              //number of nodes in the graph
    private Queue<Cell> queue;                   //maintaining a queue
    private Grid g;                
 
    public BFS(Grid grid)
    {
        this.g = grid;
        V = g.numOfVertices;
        // adj = new LinkedList[g.getWidth()][g.getHeight()];
        // for (int i=0; i < g.getWidth(); ++i)
        // { 
        //     for (int j=0; i < g.getHeight(); ++j)
        //     {
        //         adj[i][j] = new LinkedList<Cell>();
        //     }
        // }
        queue = new LinkedList<Cell>();
        doBFS();
    }

   
 
    public boolean doBFS()
    {
        boolean visited[][] = new boolean[g.getWidth()][g.getHeight()];       //initialize boolean array for holding the data
        Cell c; 
        
        visited[g.getStart().getX()][g.getStart().getY()] = true;                  
        queue.add(g.getStart());                   //root node is added to the top of the queue
        while (queue.size() != 0)
        {
            System.out.print("Queue at beginning of iteration: ");
            for (Cell cell : queue) {
                System.out.print("(" + cell.getX() + ", " + cell.getY() + ")");
            }
            System.out.println();
            c = queue.poll();             //remove the top element of the queue
            System.out.println(c.x+" "+ c.y);           //print the top element of the queue
            if (!c.IsFree())
            {
                System.out.println("Vertex is blocked");
                continue;
            }


            System.out.print("Visiting neighbors: ");
            for (Cell neighbor : g.cells[c.x-1][c.y-1].getNeighbors())  //iterate through the linked list and push all neighbors into queue
            {
                System.out.print("(" + neighbor.getX() + ", " + neighbor.getY() + ") ");
                if (!neighbor.IsFree())
                {
                    //System.out.println("Neighbor is blocked");
                    continue;
                }
                if(neighbor.x == g.getGoal().x && neighbor.y == g.getGoal().y)
                { 
                    System.out.println("Found!" + c.x + " " + c.y); //target is 2 5 
                    return true;
                
                }
                if (!visited[neighbor.x-1][neighbor.y-1])                    //only insert nodes into queue if they have not been explored already
                {
                    visited[neighbor.x-1][neighbor.y-1] = true;
                    queue.add(neighbor);
                }
            }  




            System.out.println();
            System.out.print("Queue at beginning of iteration: ");
            for (Cell cell : queue) {
                System.out.print("(" + cell.getX() + ", " + cell.getY() + ")");
            }
            System.out.println();
        }
        System.out.println("Not Found!");
        return false;
    }
    
    /* //to do: use cell instead of node
    private int num_nodes;   // # of nodes
    private ArrayList<ArrayList<Cell>> adj; //Adjacency Lists
    public Grid grid;
    public Cell start, goal;

    public BFS(int v, Grid grid)
    {
        this.num_nodes = v;
        this.grid = grid;
        this.start = grid.cells[this.start.getX()][this.start.getY()];
        this.goal = grid.cells[this.goal.getX()][this.goal.getY()];
        adj = new ArrayList<ArrayList<Cell>>();
        for (int i=0; i<this.num_nodes; ++i)
            adj.set(i, new ArrayList<Cell>());
        if (doBFS(this.start, this.goal))
        {
            System.out.println("Found");
        } 
        else
        {
            System.out.println("Not Found");
        }
    }
    
    // prints BFS traversal from a given source s
    boolean doBFS(Cell start, Cell goal)
    {
        // Mark all the vertices as not visited(By default
        // set as false)
        ArrayList<Cell> list = new ArrayList<Cell>(); //list of all nodes in grid
        boolean visited[] = new boolean[num_nodes];
  
        // Create a queue for BFS
        LinkedList<Cell> queue = new LinkedList<Cell>();
  
        // Mark the current node as visited and enqueue it
        visited[list.indexOf(start)]=true;
        queue.add(start);
  
        while (queue.size() != 0)
        {
            // Dequeue a vertex from queue and print it
            start = queue.poll();
            if (start.equals(goal))
            {
                return true;
            }
            if (!start.IsFree()) //if start is blocked this code just continues
            {
                continue;
            }
  
            // Get all adjacent vertices of the dequeued vertex s
            // If a adjacent has not been visited, then mark it
            // visited and enqueue it
            Iterator<Cell> i = adj.get(list.indexOf(start)).listIterator();
            while (i.hasNext())
            {
                start = i.next();
                if (!visited[list.indexOf(start)])
                {
                    visited[list.indexOf(start)] = true;
                    queue.add(start);
                }
            }
        }
        return false;
    } */

}