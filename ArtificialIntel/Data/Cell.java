package ArtificialIntel.Data;

public class Cell extends Vertex{ 
    Grid grid;

    protected boolean bFree = true;
    public boolean IsFree(){
        return bFree;
    }
    Cell(Grid grid, int x, int y, int free){
        super(x, y);
        this.grid = grid;
        bFree = free == 0;
    }
    Cell(int x, int y, int free){
        super(x, y);
        bFree = free == 0;
    }
    boolean getFree(){
        return bFree;
    }
}