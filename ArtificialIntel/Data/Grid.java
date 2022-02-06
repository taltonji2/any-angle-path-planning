package ArtificialIntel.Data;

public class Grid{
    public Cell [][] cells;
    public Cell start;
    public Cell goal; 
    public int numOfVertices;

    public int getWidth(){
        return cells.length;
    }
    public int getHeight(){
        return cells[0].length;
    }
    Grid(int width, int height){
        cells = new Cell[width][height];
        numOfVertices = (width + 1) * (height + 1);
    }

    void add(Cell cell){
        // convert the cell position identifiers to array index
        // int ix = cell.getX() - 1;
        // int iy = cell.getY() - 1;
        int ix = cell.x;
        int iy = cell.y;
        cells[ix][iy] = cell;
    }

    static double returnDistance (Cell one, Cell two) 
    {
        return Math.sqrt (((two.x - one.x)^2) + (two.y - one.y)^2);
    }
    
    public Cell north(Cell cell){
        int y = cell.y - 1;
        if(y<0)
            return null;
        return cells[cell.x][y];
    }
    public Cell east(Cell cell){
        int x = cell.x + 1;
        if(x > cells.length - 1)
            return null;
        return cells[x][cell.y];
    }
    public Cell south(Cell cell){
        int y = cell.y + 1;
        if(y > cells.length - 1)
            return null;
        return cells[cell.x][y];
    }
    public Cell west(Cell cell){
        int x = cell.x - 1;
        if(x < 0)
            return null;
        return cells[x][cell.y];
    }
    public Cell northEast(Cell cell){
        int y = cell.y - 1;
        int x = cell.x + 1;
        if(y < 0 || x > cells.length - 1)
            return null;
        return cells[x][y];
    }
    public Cell northWest(Cell cell){
        int y = cell.y - 1;
        int x = cell.x - 1;
        if(y < 0 || x < 0)
            return null;
        return cells[x][y];
    }
    public Cell southEast(Cell cell){
        int y = cell.y + 1;
        int x = cell.x + 1;
        if(y > cells.length - 1|| x > cells.length - 1)
            return null;
        return cells[x][y];
    }
    public Cell southWest(Cell cell){
        int y = cell.y + 1;
        int x = cell.x - 1;
        if(y > cells.length - 1|| x < 0)
            return null;
        return cells[x][y];
    }
}
