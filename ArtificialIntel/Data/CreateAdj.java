package ArtificialIntel.Data;

import java.util.ArrayList;

public class CreateAdj {
    CreateAdj()
    {

    }
    public static Grid AddNeighbors(Grid g)             
    {
        for (int i = 0; i < g.cells.length; i++) 
        {
            for (int j = 0; j < g.cells[0].length; j++)
            {
                //c.neighbors.clear();
                //System.out.println("Analyzing (" + i + ", " + j + ")");
                if ( g.north(g.cells[i][j]) != null)
                {
                    //System.out.println("North");
                    //System.out.println("(" + g.north(g.cells[i][j]).getX() + ", " + g.north(g.cells[i][j]).getY() + ")");
                    g.cells[i][j].neighbors.add(g.north(g.cells[i][j]));
                }

                if (g.south(g.cells[i][j]) != null)
                {
                    //System.out.println("South");
                    //System.out.println("(" + g.south(g.cells[i][j]).getX() + ", " + g.south(g.cells[i][j]).getY() + ")");
                    g.cells[i][j].neighbors.add(g.south(g.cells[i][j]));
                }

                if (g.east(g.cells[i][j]) != null)
                {
                    //System.out.println("East");
                    //System.out.println("(" + g.east(g.cells[i][j]).getX() + ", " + g.east(g.cells[i][j]).getY() + ")");
                    g.cells[i][j].neighbors.add(g.east(g.cells[i][j]));
                } 
                if (g.west(g.cells[i][j]) != null)
                {
                    //System.out.println("West");
                    //System.out.println("(" + g.west(g.cells[i][j]).getX() + ", " + g.west(g.cells[i][j]).getY() + ")");
                    g.cells[i][j].neighbors.add(g.west(g.cells[i][j]));
                }
                if (g.northEast(g.cells[i][j]) != null)
                {
                    //System.out.println("NE");
                    //System.out.println("(" + g.northEast(g.cells[i][j]).getX() + ", " + g.northEast(g.cells[i][j]).getY() + ")");
                    g.cells[i][j].neighbors.add(g.northEast(g.cells[i][j]));
                } 
            
                if (g.northWest(g.cells[i][j]) != null)
                {
                    //System.out.println("NW");
                    //System.out.println("(" + g.northWest(g.cells[i][j]).getX() + ", " + g.northWest(g.cells[i][j]).getY() + ")");
                    g.cells[i][j].neighbors.add(g.northWest(g.cells[i][j]));
                }
           
                if (g.southEast(g.cells[i][j]) != null)
                {
                    //System.out.println("SE");
                    //System.out.println("(" + g.southEast(g.cells[i][j]).getX() + ", " + g.southEast(g.cells[i][j]).getY() + ")");
                    g.cells[i][j].neighbors.add(g.southEast(g.cells[i][j]));
                } 
            
                if (g.southWest(g.cells[i][j]) != null)
                {
                    //System.out.println("SW");
                    //System.out.println("(" + g.southWest(g.cells[i][j]).getX() + ", " + g.southWest(g.cells[i][j]).getY() + ")");
                    g.cells[i][j].neighbors.add(g.southWest(g.cells[i][j]));
                } 
            
            }
        }
        
        for (Cell[] eachRowTemp : g.cells) {
            for (Cell c : eachRowTemp) {
                ArrayList<Cell> neighborTemp = c.neighbors;
                for (Cell[] eachRowGrid : g.cells) {     //traverses every cell in grid
                    for (Cell cGrid : eachRowGrid) {
                        if(cGrid.neighbors.contains(c))
                        {
                            cGrid.neighbors.get(cGrid.neighbors.indexOf(c)).neighbors = neighborTemp;
                        }
                    }
                }
            }
        }
        return g;
    }
}