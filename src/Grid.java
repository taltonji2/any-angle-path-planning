

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
        int ix = cell.getX() - 1;
        int iy = cell.getY() - 1;
        cells[ix][iy] = cell;
    }
    
    public Cell getNorth(Cell cell){
        int ix = cell.getX() - 1;
        int iy = cell.getY() -1;
        int y = iy - 1;
        if(y<0)
            return null;
        return cells[ix][y];
    }
    public Cell getEast(Cell cell){
        int ix = cell.getX() - 1;
        int iy = cell.getY() -1;
        int x = ix + 1;
        if(x > cells.length-1)
            return null;
        return cells[x][iy];
    }
    public Cell getSouth(Cell cell){
        int ix = cell.getX() - 1;
        int iy = cell.getY() -1;
        int y = iy + 1;
        if(y > cells[0].length-1)
            return null;
        return cells[ix][y];
    }
    public Cell getWest(Cell cell){
        int ix = cell.getX() - 1;
        int iy = cell.getY() -1;
        int x = ix - 1;
        if(x < 0)
            return null;
        return cells[x][iy];
    }
    public Cell getNorthEast(Cell cell){
        int ix = cell.getX() - 1;
        int iy = cell.getY() -1;
        int x = ix + 1;
        int y = iy - 1;
        if(y < 0 || x > cells.length-1)
            return null;
        return cells[x][y];
    }
    public Cell getNorthWest(Cell cell){
        int ix = cell.getX() - 1;
        int iy = cell.getY() -1;
        int x = ix - 1;
        int y = iy - 1;
        if(y < 0 || x < 0)
            return null;
        return cells[x][y];
    }
    public Cell getSouthEast(Cell cell){
        int ix = cell.getX() - 1;
        int iy = cell.getY() -1;
        int x = ix + 1;
        int y = iy + 1;
        if(y > cells[0].length-1 || x > cells.length-1)
            return null;
        return cells[x][y];
    }
    public Cell getSouthWest(Cell cell){
        int ix = cell.getX() - 1;
        int iy = cell.getY() -1;
        int x = ix - 1;
        int y = iy + 1;
        if(y > cells[0].length-1 || x < 0)
            return null;
        return cells[x][y];
    }
    private void addEdge(Cell v, Cell w)
    {
        if(w.getIsCellBlocked())
        {
            v.getNeighbors().add(w); 
        }
    }
    
    public void LoadAdj(){
        for (int i = 0; i < this.cells.length; i++) 
        {
            for (int j = 0; j < this.cells[i].length; j++)
            {
                Cell cell = this.cells[i][j];
                
                cell.setVisited(false);

                Cell north = this.getNorth(cell);
                if(north!=null){
                    addEdge(cell, north);
                }
                Cell south = this.getSouth(cell);
                if(south!=null){
                    addEdge(cell, south);
                }
                Cell east = this.getEast(cell);
                if(east!=null){
                    addEdge(cell, east);
                }
                Cell west = this.getWest(cell);
                if(west!=null){
                    addEdge(cell, west);
                }
                Cell northeast = this.getNorthEast(cell);
                if(northeast!=null){
                    addEdge(cell, northeast);
                }
                Cell northwest = this.getNorthWest(cell);
                if(northwest!=null){
                    addEdge(cell, northwest);
                }
                Cell southeast = this.getSouthEast(cell);
                if(southeast!=null){
                    addEdge(cell, southeast);
                }
                Cell southwest = this.getSouthWest(cell);
                if(southwest!=null){
                    addEdge(cell, southwest);
                }
            }
        }
    }
}
