public class Grid{
    private static Grid s_THIS;
    public Cell [][] cells;
    private static Cell start;
    private static Cell goal;
    public int numOfVertices; 
    
    
    public static double getDistanceFromStartGoalLine(Cell cell) {
    {
        //returns the distance between the infinite line(x1,y1)(x2,y2) and a point(x,y)
        

            double A = cell.getX() - start.getX(); // position of point rel one end of line
            double B = cell.getY() - start.getY();
            double C = goal.getX() - start.getX(); // vector along line
            double D = goal.getY() - start.getY();
            double E = -D; // orthogonal vector
            double F = C;

            double dot = A * E + B * F;
            double len_sq = E * E + F * F;

            return Math.abs(dot) / Math.sqrt(len_sq);
        }
    }
    

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
        return Grid.start;
    }
    public Cell getGoal()
    {
        return Grid.goal;
    }
    public void setStart(Cell c)
    {
        Grid.start = c;
    }
    public void setGoal(Cell c)
    {
        Grid.goal = c;
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
        if(getWest(cell) != null)
        {
            Cell west = getWest(cell);
            if(west.getIsCellFree() == false)
            return null;
        }
        if(y > cells[0].length-1 || x < 0)
            return null;
        
        
        return cells[x][y];
    }
    private void addEdge(Cell v, Cell w)
    {
            v.getNeighbors().add(w); 
    }
    
    public void createEdgeCells()
    {
        //Vertical
        for (int j = 0; j < this.cells[this.getWidth()-1].length; j++)
        {
            int x  = this.getWidth();
            Cell c = new Cell(x, j+1, 1);
            c.bEdgeCell = true;
            this.cells[x-1][j] = c;
        }

        for (int i = 0; i < this.cells.length; i++) 
        {
            int y = this.getHeight();
            Cell c = new Cell(i+1, y, 1);
            c.bEdgeCell = true;
            this.cells[i][y-1] = c;
        }
    }

    public void LoadAdj()
    {
        for (int i = 0; i < this.cells.length; i++) 
        {
            for (int j = 0; j < this.cells[i].length; j++)
            {
                Cell cell = this.cells[i][j];
                
                cell.setVisited(false);

                Cell north = this.getNorth(cell);
                if(north!=null){
                    if(cell.getX() > 1)
                    {
                        if(north.getIsCellFree() == true || cell.getIsCellFree() == true)
                        {
                            if(getWest(north).bFree == true)
                                addEdge(cell, north);
                            else if (getWest(north).bFree == false && north.getIsCellFree() == true)
                                addEdge(cell, north);
                        } else if(north.bEdgeCell == true)
                        {
                            addEdge(cell, north);
                        } else if(getWest(north).getIsCellFree() == true && getWest(cell).getIsCellFree() == true)
                        {
                            addEdge(cell, north);
                        }
                    }
                    //Cases where x <= 1
                    else if(north.getIsCellFree() == true || cell.getIsCellFree() == true)
                        addEdge(cell, north);
                    else if(getNorthWest(cell) != null)
                    {
                        if(cell.bEdgeCell == true && getNorthWest(cell).bFree == true)
                            addEdge(cell, north);
                    }  
                }
                Cell south = this.getSouth(cell);
                if(south!=null){
                    if(getWest(cell) != null)
                    {
                        if(getWest(cell).getIsCellFree() == true || cell.getIsCellFree() == true)
                        {
                            addEdge(cell, south);
                        }
                    }
                    else if(cell.getIsCellFree() == true)
                        addEdge(cell, south);
                }
                Cell east = this.getEast(cell);
                if(east!=null){
                    if(getNorth(cell) != null)
                    {
                        if(cell.getIsCellFree() == true || getNorth(cell).getIsCellFree() == true)
                        {
                            addEdge(cell, east);
                        }   
                    } else {
                        if(cell.getIsCellFree() == true)
                            addEdge(cell, east);
                    }
                }

                Cell west = this.getWest(cell);
                if(west!=null){
                    if(getNorth(west) != null)
                    {
                        if(west.getIsCellFree() == true || getNorth(west).getIsCellFree() == true)
                            addEdge(cell, west);
                    }
                    else if(west.getIsCellFree() == true)
                    {
                        addEdge(cell, west);
                    }
                }
                Cell northeast = this.getNorthEast(cell);
                if(northeast!=null){
                    if(this.getNorth(cell).bFree == true)
                        addEdge(cell, northeast);
                }
                Cell northwest = this.getNorthWest(cell);
                if(northwest!=null && northwest.bFree == true){
                    addEdge(cell, northwest);
                }
                Cell southeast = this.getSouthEast(cell);
                if(southeast!=null && cell.bFree == true){
                    addEdge(cell, southeast);
                }
                Cell southwest = this.getSouthWest(cell);
                if(southwest!=null && west.bFree == true){
                    addEdge(cell, southwest);
                } 
            }
        }
    }
}
