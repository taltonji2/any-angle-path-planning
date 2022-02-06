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
        createAdjacencies();
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

    void createAdjacencies()              //adjacency list err.. well not a list but they are there :)
    {
        for (int i = 0; i < g.cells.length; i++) 
        {
            for (int j = 0; j < g.cells[i].length; j++)
            {
                //c.neighbors.clear();
                
                if ( g.north(g.cells[i][j]) != null)
                {
                    g.cells[i][j].neighbors.add(g.north(g.cells[i][j]));
                }

                if (g.south(g.cells[i][j]) != null)
                {
                    g.cells[i][j].neighbors.add(g.south(g.cells[i][j]));
                }

                if (g.east(g.cells[i][j]) != null)
                {
                    g.cells[i][j].neighbors.add(g.east(g.cells[i][j]));
                } 
                if (g.west(g.cells[i][j]) != null)
                {
                    g.cells[i][j].neighbors.add(g.west(g.cells[i][j]));
                }
                if (g.northEast(g.cells[i][j]) != null)
                { 
                    System.out.println("added " + g.northEast(g.cells[i][j]).x + " " + g.northEast(g.cells[i][j]).y);
                    g.cells[i][j].neighbors.add(g.northEast(g.cells[i][j]));
                } 
            
                if (g.northWest(g.cells[i][j]) != null)
                {
                    g.cells[i][j].neighbors.add(g.northWest(g.cells[i][j]));
                }
           
                if (g.southEast(g.cells[i][j]) != null)
                {
                    g.cells[i][j].neighbors.add(g.southEast(g.cells[i][j]));
                } 
            
                if (g.southWest(g.cells[i][j]) != null)
                {
                    g.cells[i][j].neighbors.add(g.southWest(g.cells[i][j]));
                } 
            
            }
        }

        Grid temp = g;
        for (Cell[] eachRowTemp : temp.cells) {
            for (Cell c : eachRowTemp) {
                ArrayList<Cell> neighborTemp = c.neighbors;
                for (Cell[] eachRowGrid : g.cells) {     //traverses every cell in grid
                    for (Cell cGrid : eachRowGrid) {
                        if(cGrid.neighbors.contains(c))
                        {
                            if(!cGrid.neighbors.get(cGrid.neighbors.indexOf(c)).neighbors.contains(c))
                            {
                                cGrid.neighbors.get(cGrid.neighbors.indexOf(c)).neighbors = neighborTemp;
                            }
                        }
                    }
                }
            }
        }
    }
 
    boolean doBFS()
    {
        boolean visited[][] = new boolean[g.getWidth()][g.getHeight()];       //initialize boolean array for holding the data
        Cell c; 
        
        visited[g.start.getX()][g.start.getY()] = true;                  
        queue.add(g.start);                   //root node is added to the top of the queue
 
        while (queue.size() != 0)
        {
            c = queue.poll();             //remove the top element of the queue
            System.out.println(c.x+" "+ c.y);           //print the top element of the queue
 
            for (Cell neighbor : g.cells[c.x][c.y].neighbors)  //iterate through the linked list and push all neighbors into queue
            {
                if (!visited[neighbor.x][neighbor.y])                    //only insert nodes into queue if they have not been explored already
                {
                    visited[neighbor.x][neighbor.y] = true;
                    queue.add(neighbor);
                }
            }  
            if(c.x == g.goal.x && c.y == g.goal.y)
            { 
                System.out.println("Found!"); //target is 0 2 
                return true;    
            }
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
