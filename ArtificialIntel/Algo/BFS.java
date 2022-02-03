package ArtificialIntel.Algo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class BFS {
    //to do: use cell instead of node
    private int num_nodes;   // # of nodes
    private ArrayList<ArrayList<Node2>> adj; //Adjacency Lists
    

    BFS(int v)
    {
        this.num_nodes = v;
        adj = new ArrayList<ArrayList<Node2>>();
        for (int i=0; i<this.num_nodes; ++i)
            adj.set(i, new ArrayList<Node2>()); 
    }

    // prints BFS traversal from a given source s
    boolean doBFS(Node2 start, Node2 goal)
    {
        // Mark all the vertices as not visited(By default
        // set as false)
        ArrayList<Node2> list = new ArrayList<Node2>(); //list of all nodes in grid
        boolean visited[] = new boolean[num_nodes];
  
        // Create a queue for BFS
        LinkedList<Node2> queue = new LinkedList<Node2>();
  
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
  
            // Get all adjacent vertices of the dequeued vertex s
            // If a adjacent has not been visited, then mark it
            // visited and enqueue it
            Iterator<Node2> i = adj.get(list.indexOf(start)).listIterator();
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
