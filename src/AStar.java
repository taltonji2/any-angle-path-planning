import java.util.ArrayList;
import java.util.PriorityQueue;
/*
*       start       Node       goal
*         |----------|---------|
*             g(n)      h(n)
*         |--------------------|
*                  f() 
*/
public class AStar 
{
    Cell start, goal;
    PriorityQueue<Cell> fringe = new PriorityQueue<Cell>();
    ArrayList<Cell> closed = new ArrayList<Cell>();
    
    public void doAStar(Grid grid)
    {
        start = grid.getStart();
        goal = grid.getGoal();
        start.setParent(start);
        start.setGCost(0);
        start.setHCost(h(start));
        start.setFCost();
        //start.neighbors.remove(start);          //this 
        start.getNeighbors().remove(start);     //to this 
        System.out.println("Start: (" + start.getX() + ", " + start.getY() + ")");
        System.out.println("Goal: (" + goal.getX() + ", " + goal.getY() + ")");
        fringe.add(start);
        while (!fringe.isEmpty())
        {
            Cell current = fringe.poll();
            System.out.println("Visiting (" + current.getX() + ", " + current.getY() + ")");
            if (current.equals(goal))     //when on same vertex does not fire;
            {
                closed.add(current);
                System.out.println("Found it!");
                for(Cell c : closed)
                {
                    System.out.println("Path: " + c.getX() + " " + c.getY());
                }
                break;
            }
            closed.add(current);
            System.out.println(current.getNeighbors().size());
            for (Cell successor : current.getNeighbors()) 
            {
                if (!fringe.contains(successor) && !closed.contains(successor))
                {
                    updateCostAndAddToFringe(current, successor);
                }
            }
            System.out.print("Fringe at end of iteration: ");
                for (Cell ce : fringe) {
                    System.out.print("(" + ce.getX() + ", " + ce.getY() + ") " + ce.getFCost() + "  ");
                }
                System.out.println();
        }
    }
    public void updateCostAndAddToFringe(Cell current, Cell successor)
    {
        System.out.println("Cost from current to successor: " + c(current, successor));
        System.out.println("Current g " + current.getGCost());
        System.out.println("Successor g " +successor.getGCost());
        if (current.getGCost() + c(current, successor) < successor.getGCost())
        {
            successor.setGCost(current.getGCost() + c(current, successor));
            successor.setParent(current);
            successor.setHCost(h(successor)); 
            successor.setFCost();
            fringe.add(successor);
            System.out.println("Added " + successor.getX() + ", " + successor.getY() + " to fringe");
        }
    }

    public double c(Cell current, Cell successor) 
    {
        if (successor.getX() < current.getX() && successor.getY() == current.getY()) //Successor is to the left
        {
            return 1;   
        }
        if (successor.getX() > current.getX() && successor.getY() == current.getY()) //Successor is to the right
        {
            return 1;
        }
        if (successor.getX() == current.getX() && successor.getY() < current.getY()) //Successor is above
        {
            return 1;
        }
        if (successor.getX() == current.getX() && successor.getY() > current.getY()) //Successor is under
        {
            return 1;
        }
        if (successor.getX() == current.getX() && successor.getY() == current.getY()) //Successor is Current
        {
            return 0;
        }
        return Math.sqrt(2); //diagonal
    }

    //determines cost of cell relative to start
    private static double g(Cell cell)
    {
        Cell start = Grid.Instance().getStart();
        if (cell.equals(start))
        {
            start.setGCost(0);
        }
        return cell.getGCost();
    }

    public static double h(Cell c)
    {
        Cell goal = Grid.Instance().getGoal();
        return (Math.sqrt(2) * Math.min(Math.abs(c.getX() - goal.getX()), Math.abs(c.getY() - goal.getY())) + Math.max(Math.abs(c.getX() - goal.getX()), Math.abs(c.getY() - goal.getY())) + Math.min(Math.abs(c.getX() - goal.getX()), Math.abs(c.getY() - goal.getY())));
    }

    public static double f(Cell c)
    {
        return h(c) + g(c);
        
    }

}
