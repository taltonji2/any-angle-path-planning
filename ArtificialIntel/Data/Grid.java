package ArtificialIntel.Data;

public class Grid{
    public Cell [][] cells;

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
        int ix = cell.getX() - 1;
        int iy = cell.getY() - 1;
        cells[ix][iy] = cell;
    }
    
    Cell north(Cell cell){
        int y = cell.y - 1;
        if(y<0)
            return null;
        return cells[cell.x][y];
    }
    Cell east(Cell cell){
        int x = cell.x + 1;
        if(x > cells.length)
            return null;
        return cells[x][cell.y];
    }
    Cell south(Cell cell){
        int y = cell.y + 1;
        if(y > cells.length - 1)
            return null;
        return cells[cell.x][y];
    }
    Cell west(Cell cell){
        int x = cell.x - 1;
        if(x < 0)
            return null;
        return cells[x][cell.y];
    }
    Cell northEast(Cell cell){
        int y = cell.y - 1;
        int x = cell.x + 1;
        if(y < 0 || x > cells.length - 1)
            return null;
        return cells[x][y];
    }
    Cell northWest(Cell cell){
        int y = cell.y - 1;
        int x = cell.x - 1;
        if(y < 0 || x < 0)
            return null;
        return cells[x][y];
    }
    Cell southEast(Cell cell){
        int y = cell.y + 1;
        int x = cell.x + 1;
        if(y > cells.length - 1 || x > cells.length - 1)
            return null;
        return cells[x][y];
    }
    Cell southWest(Cell cell){
        int y = cell.y + 1;
        int x = cell.x - 1;
        if(y > cells.length - 1 || x < 0)
            return null;
        return cells[x][y];
    }
}
