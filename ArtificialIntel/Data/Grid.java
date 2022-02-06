package ArtificialIntel.Data;

public class Grid{
    public Cell [][] cells;
    public Cell start;
    public Cell goal; 
    
    public int getWidth(){
        return cells.length;
    }
    public int getHeight(){
        return cells[0].length;
    }
    Grid(int width, int height){
        cells = new Cell[width][height];
    }

    void add(Cell cell){
        // convert the cell position identifiers to array index
        // int ix = cell.getX() - 1;
        // int iy = cell.getY() - 1;
        int ix = cell.x - 1;
        int iy = cell.y - 1;
        cells[ix][iy] = cell;
    }
    
    Cell north(Cell cell){
        int y = cell.y - 1;
        if(y<1)
            return null;
        return cells[cell.x-1][y-1];
    }
    Cell east(Cell cell){
        int x = cell.x + 1;
        if(x > cells.length)
            return null;
        return cells[x-1][cell.y-1];
    }
    Cell south(Cell cell){
        int y = cell.y + 1;
        if(y > cells.length)
            return null;
        return cells[cell.x-1][y-1];
    }
    Cell west(Cell cell){
        int x = cell.x - 1;
        if(x < 1)
            return null;
        return cells[x-1][cell.y-1];
    }
    Cell northEast(Cell cell){
        int y = cell.y - 1;
        int x = cell.x + 1;
        if(y < 1 || x > cells.length)
            return null;
        return cells[x-1][y-1];
    }
    Cell northWest(Cell cell){
        int y = cell.y - 1;
        int x = cell.x - 1;
        if(y < 1 || x < 1)
            return null;
        return cells[x-1][y-1];
    }
    Cell southEast(Cell cell){
        int y = cell.y + 1;
        int x = cell.x + 1;
        if(y > cells.length || x > cells.length)
            return null;
        return cells[x-1][y-1];
    }
    Cell southWest(Cell cell){
        int y = cell.y + 1;
        int x = cell.x - 1;
        if(y > cells.length || x < 1)
            return null;
        return cells[x-1][y-1];
    }
}
