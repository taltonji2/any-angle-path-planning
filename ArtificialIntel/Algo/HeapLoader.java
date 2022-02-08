package ArtificialIntel.Algo;

import ArtificialIntel.Data.Cell;
import ArtificialIntel.Data.Grid;

public class HeapLoader {
 
    /* public static MinHeap LoadFully(Grid grid){

        IBinaryHeapElement [] arrayHeapElements = new IBinaryHeapElement[grid.getWidth()*grid.getHeight()];
        MinHeap heap = new MinHeap(arrayHeapElements);
        for (int i = 0; i < grid.cells.length; i++) 
        {
            for (int j = 0; j < grid.cells[i].length; j++)
            {
                Cell cell = grid.cells[i][j];
                if(cell != grid.getStart())
                    heap.insert(cell);
            }
        }
        return heap;
    } */

    public static MinHeap AllocateHeap(Grid grid){
        IBinaryHeapElement [] arrayHeapElements = new IBinaryHeapElement[grid.getWidth()*grid.getHeight()];
        MinHeap heap = new MinHeap(arrayHeapElements);
        return heap;
    }

    public static void LoadSuccessors(MinHeap heap, Grid grid, Cell current){
        Cell cellStart = grid.getStart();

        Cell cellNorth = grid.north(current);
        if(cellNorth != null && cellNorth !=cellStart)
            heap.insert(cellNorth);

        Cell cellSouth = grid.south(current);
        if(cellSouth != null && cellSouth !=cellStart)
            heap.insert(cellSouth);
            
        Cell cellEast = grid.east(current);
        if(cellEast != null && cellEast !=cellStart)
            heap.insert(cellEast);

        Cell cellWest = grid.west(current);
        if(cellWest != null && cellWest !=cellStart)
            heap.insert(cellWest);

        Cell cellNorthEast = grid.northEast(current);
        if(cellNorthEast != null && cellNorthEast !=cellStart)
            heap.insert(cellNorthEast);
            
        Cell cellNorthWest = grid.northWest(current);
        if(cellNorthWest != null && cellNorthWest !=cellStart)
            heap.insert(cellNorthWest);

        Cell cellSouthEast = grid.southEast(current);
        if(cellSouthEast != null && cellSouthEast !=cellStart)
            heap.insert(cellSouthEast);

        Cell cellSouthWest = grid.southWest(current);
        if(cellSouthWest != null && cellSouthWest !=cellStart)
            heap.insert(cellSouthWest);
    }

}

//Create 8 loadSuccessors for propensity Directionals