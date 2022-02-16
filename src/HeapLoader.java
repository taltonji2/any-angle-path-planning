<<<<<<< HEAD
=======

>>>>>>> cc6b5e9cdf01138176b2ef94cf19148063f06662


// public class HeapLoader {
 
//     /* public static MinHeap LoadFully(Grid grid){

//         IBinaryHeapElement [] arrayHeapElements = new IBinaryHeapElement[grid.getWidth()*grid.getHeight()];
//         MinHeap heap = new MinHeap(arrayHeapElements);
//         for (int i = 0; i < grid.cells.length; i++) 
//         {
//             for (int j = 0; j < grid.cells[i].length; j++)
//             {
//                 Cell cell = grid.cells[i][j];
//                 if(cell != grid.getStart())
//                     heap.insert(cell);
//             }
//         }
//         return heap;
//     } */

//     public static MinHeap AllocateHeap(Grid grid){
//         IBinaryHeapElement [] arrayHeapElements = new IBinaryHeapElement[grid.getWidth()*grid.getHeight()];
//         MinHeap heap = new MinHeap(arrayHeapElements);
//         return heap;
//     }
//     //Create 8 loadSuccessors for propensity Directionals
//     public static void LoadSuccessors(MinHeap heap, Grid grid, Cell current){
//         Cell cellStart = grid.getStart();
//         Cell cellNorth = grid.getNorth(current);
//         if(cellNorth != null && cellNorth !=cellStart)
//             heap.insert(cellNorth);

//         Cell cellSouth = grid.getSouth(current);
//         if(cellSouth != null && cellSouth !=cellStart)
//             heap.insert(cellSouth);
            
//         Cell cellEast = grid.getEast(current);
//         if(cellEast != null && cellEast !=cellStart)
//             heap.insert(cellEast);

//         Cell cellWest = grid.getWest(current);
//         if(cellWest != null && cellWest !=cellStart)
//             heap.insert(cellWest);

//         Cell cellNorthEast = grid.getNorthEast(current);
//         if(cellNorthEast != null && cellNorthEast !=cellStart)
//             heap.insert(cellNorthEast);
            
//         Cell cellNorthWest = grid.getNorthWest(current);
//         if(cellNorthWest != null && cellNorthWest != cellStart)
//             heap.insert(cellNorthWest);

//         Cell cellSouthEast = grid.getSouthEast(current);
//         if(cellSouthEast != null && cellSouthEast != cellStart)
//             heap.insert(cellSouthEast);

//         Cell cellSouthWest = grid.getSouthWest(current);
//         if(cellSouthWest != null && cellSouthWest !=cellStart)
//             heap.insert(cellSouthWest);
//     }
// }

