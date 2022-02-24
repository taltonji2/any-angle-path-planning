

import java.util.ArrayList;

public class Cell extends Vertex implements Comparable<Cell>{ 
    private Cell parent;
    private double g = Integer.MAX_VALUE;
    private double h;
    private double f = 0;
    private boolean visited; 
    protected boolean bFree;
    protected boolean bEdgeCell;
    private ArrayList<Cell> neighbors = new ArrayList<Cell>();

    public void setFCost()
    {
        f = g + h;
    }
    public Cell getParent()
    {
        return parent;
    }
    public void setParent(Cell parentCell)
    {
        this.parent = parentCell;
    }
    public void setGCost(double cost)
    {
        g = cost;
    }
    public double getGCost()
    {
        return g;
    }
    public void setHCostAstar(Cell cell) 
    {
        h = Math.sqrt(2) * Math.min(Math.abs(cell.getX() - Grid.Instance().getGoal().getX()), 
        Math.abs(cell.getY() - Grid.Instance().getGoal().getY())) 
        + Math.max(Math.abs(cell.getX() - Grid.Instance().getGoal().getX()), 
        Math.abs(cell.getY() - Grid.Instance().getGoal().getY())) 
        - Math.min(Math.abs(cell.getX() - Grid.Instance().getGoal().getX()), 
        Math.abs(cell.getY() - Grid.Instance().getGoal().getY()));
    }
    public void setHCostThetaStar(Cell cell)
    {
        h = Math.sqrt(Math.pow((cell.getX() - Grid.Instance().getGoal().getX()), 2) + Math.pow((cell.getY() - Grid.Instance().getGoal().getY()), 2));
    }
    public double getHCost()
    {
        return h;
    }
    public double getFCost()
    {
        return f;
    }
    public void setFCost(double cost)
    {
        f = cost;
    }
    public void visit()
    {
        visited = true;
    }
    public boolean isVisited()
    {
       return visited;
    }
    public void setVisited(Boolean b)
    {
        visited = b;
    }
    public boolean getIsCellFree()
    {
        return bFree;
    }
    public ArrayList<Cell> getNeighbors ()
    {
        return this.neighbors;
    }
    public void setNeighbors (ArrayList<Cell> neighbors)
    {
        this.neighbors = neighbors;
    }

    protected Cell(int x, int y, int free){
        super(x, y);
        if(free == 0)
        {bFree = true;}
        else {bFree = false;}
    }
    //
    // cell < othercell
    // cell.LessThan(othercell)
    public boolean LessThan(IBinaryHeapElement element){
        Cell cell = (Cell) element;
        double thisF = this.f;
        double cellF = cell.f;
        return thisF < cellF;
    }
    public boolean GreaterThan(IBinaryHeapElement element){
        Cell cell = (Cell) element;
        double thisF = this.f;
        double cellF = cell.f;
        return thisF > cellF;
    }
    public boolean Equals(IBinaryHeapElement element){
        Cell cell = (Cell) element;
        double thisF = this.f;
        double cellF = cell.f;
        return thisF == cellF;
    }

    @Override
    public int compareTo(Cell otherCell) {
        if (this.getFCost() < otherCell.getFCost())
        {
            return -1;
        }
        if (this.getFCost() > otherCell.getFCost())
        {
            return 1;
        }
        return 0;
    }

    
}