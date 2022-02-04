package ArtificialIntel.Data;

import java.util.ArrayList;
import java.util.Comparator;

public class Cell extends Vertex { 
    //Grid grid;
    
    protected boolean bFree = true;
    public Cell parent;
    public double cost;
    public ArrayList<Cell> neighbors = new ArrayList<Cell>();
    public boolean IsFree(){
        return bFree;
    }
    // Cell(Grid grid, int x, int y, int free){
    //     super(x, y);
    //     this.grid = grid;
    //     bFree = free == 0;
    // }
    protected Cell(int x, int y, int free){
        super(x, y);
        bFree = free == 0;
    }
    boolean getFree(){
        return bFree;
    }
    public void setCost(double c)
    {
        this.cost = c;
    }
    
}