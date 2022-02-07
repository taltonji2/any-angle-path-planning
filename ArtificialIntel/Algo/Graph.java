package ArtificialIntel.Algo;

import java.util.ArrayList;
import java.util.List;

import ArtificialIntel.Data.Cell;
import ArtificialIntel.Data.Grid;

public class Graph {
 
    // lists
    List<Cell> adj = new ArrayList<Cell>();  
 
    Grid grid = null;

    public Graph(){
       
    }
    
    void addEdge(Cell v, Cell w){
        if(w.IsFree())
        {
            v.getNeighbors().add(w); // Add w to v’s list.
        }
         
    }
 
    public void Load(Grid grid){
        this.grid = grid;
        for (int i = 0; i < grid.cells.length; i++) 
        {
            for (int j = 0; j < grid.cells[i].length; j++)
            {
                Cell cell = grid.cells[i][j];
                
                cell.visited = false;

                Cell north = grid.north(cell);
                if(north!=null){
                    addEdge(cell, north);
                }
                Cell south = grid.south(cell);
                if(south!=null){
                    addEdge(cell, south);
                }
                Cell east = grid.east(cell);
                if(east!=null){
                    addEdge(cell, east);
                }
                Cell west = grid.west(cell);
                if(west!=null){
                    addEdge(cell, west);
                }
                Cell northeast = grid.northEast(cell);
                if(northeast!=null){
                    addEdge(cell, northeast);
                }
                Cell northwest = grid.northWest(cell);
                if(northwest!=null){
                    addEdge(cell, northwest);
                }
                Cell southeast = grid.southEast(cell);
                if(southeast!=null){
                    addEdge(cell, southeast);
                }
                Cell southwest = grid.southWest(cell);
                if(southwest!=null){
                    addEdge(cell, southwest);
                }
            }
        }
    }

    void clear(){
        for (int i = 0; i < grid.cells.length; i++) 
        {
            for (int j = 0; j < grid.cells[i].length; j++)
            {
                Cell cell = grid.cells[i][j];
                
                cell.visited = false;
            }
        }
    }

    public Boolean BFS(Cell start, Cell goal)
    {
        // Mark all the vertices as not visited
        clear();
    
        // Create a queue for BFS
        List<Cell> queue = new ArrayList<Cell>();  
    
        // Mark the current node as visited and enqueue it
        start.visited = true;

        queue.add(start);
    
        while(!queue.isEmpty())
        {
            // Dequeue a vertex from queue and print it
            start = queue.get(0);
            queue.remove(0);
    
            // Get all adjacent vertices of the dequeued
            // vertex s. If a adjacent has not been visited,
            // then mark it visited and enqueue it
            for(Cell c : start.getNeighbors()){
                if(c.visited == false){
                    if(c.equals(goal))
                        return true;
                    c.visited = true;
                    queue.add(c);
                }
            }
        }
        return false;
    }
}