package ArtificialIntel.Data;

public class Grid{
    private static Grid s_THIS;

    public Cell [][] cells;
    private Cell start;
    private Cell goal;
    public int numOfVertices; 
    public int getWidth(){
        return cells.length;
    }
    public int getHeight(){
        return cells[0].length;
    }
    
    public static Grid Instance(int width, int height){
        if( s_THIS == null)
            s_THIS = new Grid(width, height);
        return s_THIS;
    }

    public static Grid Instance(){
        return s_THIS;
    }

    private Grid(int width, int height){
        cells = new Cell[width][height];
        this.numOfVertices = (width + 1) * (height +1);
    }

    public Cell getStart()
    {
        return this.start;
    }
    public Cell getGoal()
    {
        return this.goal;
    }
    public void setStart(Cell c)
    {
        this.start = c;
    }
    public void setGoal(Cell c)
    {
        this.goal = c;
    }

    void add(Cell cell){
        // convert the cell position identifiers to array index
        // int ix = cell.getX() - 1;
        // int iy = cell.getY() - 1;
        int ix = cell.x - 1;
        int iy = cell.y - 1;
        cells[ix][iy] = cell;
    }
    
    public Cell north(Cell cell){
        int ix = cell.x - 1;
        int iy = cell.y -1;
        int y = iy - 1;
        if(y<0)
            return null;
        return cells[ix][iy];
    }
    public Cell east(Cell cell){
        int ix = cell.x - 1;
        int iy = cell.y -1;
        int x = ix + 1;
        if(x > cells.length-1)
            return null;
        return cells[x][iy];
    }
    public Cell south(Cell cell){
        int ix = cell.x - 1;
        int iy = cell.y -1;
        int y = iy + 1;
        if(y > cells[0].length-1)
            return null;
        return cells[ix][y];
    }
    public Cell west(Cell cell){
        int ix = cell.x - 1;
        int iy = cell.y -1;
        int x = ix - 1;
        if(x < 0)
            return null;
        return cells[x][iy];
    }
    public Cell northEast(Cell cell){
        int ix = cell.x - 1;
        int iy = cell.y -1;
        int x = ix + 1;
        int y = iy - 1;
        if(y < 0 || x > cells.length-1)
            return null;
        return cells[x][y];
    }
    public Cell northWest(Cell cell){
        int ix = cell.x - 1;
        int iy = cell.y -1;
        int x = ix - 1;
        int y = iy - 1;
        if(y < 0 || x < 0)
            return null;
        return cells[x][y];
    }
    public Cell southEast(Cell cell){
        int ix = cell.x - 1;
        int iy = cell.y -1;
        int x = ix + 1;
        int y = iy + 1;
        if(y > cells[0].length-1 || x > cells.length-1)
            return null;
        return cells[x][y];
    }
    public Cell southWest(Cell cell){
        int ix = cell.x - 1;
        int iy = cell.y -1;
        int x = ix - 1;
        int y = iy + 1;
        if(y > cells[0].length-1 || x < 0)
            return null;
        return cells[x][y];
    }


   

}
