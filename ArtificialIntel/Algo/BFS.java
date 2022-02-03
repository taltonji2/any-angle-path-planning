package ArtificialIntel.Algo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import ArtificialIntel.Data.Cell;
import ArtificialIntel.Data.Grid;

public class BFS {
    //to do: use cell instead of node
    private int num_nodes;   // # of nodes
    private ArrayList<ArrayList<Cell>> adj; //Adjacency Lists
    public Grid grid;
    public Cell start, goal;

    BFS(int v, Grid grid)
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
            if (!start.IsFree())
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
    }

}
