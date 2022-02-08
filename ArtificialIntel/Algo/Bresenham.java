package ArtificialIntel.Algo;

import java.util.ArrayList;
import java.util.List;

import ArtificialIntel.Data.Cell;

public class Bresenham {
    /** function findLine() - to find that belong to line connecting the two points **/ 
    // https://stackoverflow.com/questions/8113629/simplified-bresenhams-line-algorithm-what-does-it-exactly-do 
    public static List<Cell> findLine(Cell[][] grid, int x0, int y0, int x1, int y1) 
    {                    

        List<Cell> line = new ArrayList<Cell>();
        int dx = Math.abs(x1 - x0);
        int dy = Math.abs(y1 - y0);
        int sx = x0 < x1 ? 1 : -1; 
        int sy = y0 < y1 ? 1 : -1; 
        int err = dx-dy;
        int e2;

        while (true) 
        {
            line.add(grid[x0-1][y0-1]);

            if (x0 == x1 && y0 == y1) 
                break;
            e2 = 2 * err;

            if (e2 > -dy) 
            {
                err = err - dy;
                x0 = x0 + sx;
            }

            if (e2 < dx) 
            {
                err = err + dx;
                y0 = y0 + sy;
            }

        }                                

        return line;
    }
    
}
