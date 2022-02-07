package ArtificialIntel.Data;

import java.util.ArrayList;
import java.util.Comparator;
import ArtificialIntel.Algo.*;

public class Cell extends Vertex implements IBinaryHeapElement{ 
    //Grid grid;
    
    protected boolean bFree = true;
    public Cell parent;
    public double cost = Integer.MAX_VALUE;
    public ArrayList<Cell> neighbors = new ArrayList<Cell>();
    public boolean visited; 
    public boolean IsFree(){
        return bFree;
    }
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
    public ArrayList<Cell> getNeighbors ()
    {
        return this.neighbors;
    }
    

    //
    // cell < othercell
    // cell.LessThan(othercell)
    public boolean LessThan(IBinaryHeapElement element){
        Cell cell = (Cell) element;
        double thisF = AStar.f(this);
        double cellF = AStar.f(cell);
        return thisF < cellF;
    }
    public boolean GreaterThan(IBinaryHeapElement element){
        Cell cell = (Cell) element;
        double thisF = AStar.f(this);
        double cellF = AStar.f(cell);
        return thisF > cellF;
    }
    public boolean Equals(IBinaryHeapElement element){
        Cell cell = (Cell) element;
        double thisF = AStar.f(this);
        double cellF = AStar.f(cell);
        return thisF == cellF;
    }
}