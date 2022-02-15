package src;

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
    PriorityQueue<Cell> fringe = new PriorityQueue<Cell>(1, (Cell c1, Cell c2) -> Double.compare(c1.getFCost(), c2.getFCost()));
    ArrayList<Cell> closed = new ArrayList<Cell>();
    
    public void doAStar(Grid grid)
    {
        start = grid.getStart();
        goal = grid.getGoal();
        start.setParent(start);
        start.setGCost(0);
        start.setHCost(h(start));
        //start.neighbors.remove(start);          //this 
        start.getNeighbors().remove(start);     //to this 
        System.out.println("Start: (" + start.getX() + ", " + start.getY() + ")");
        System.out.println("Goal: (" + goal.getX() + ", " + goal.getY() + ")");
        fringe.add(start);
        while (!fringe.isEmpty())
        {
            start = fringe.poll();
            System.out.println("Visiting (" + start.getX() + ", " + start.getY() + ")");
            if (start.equals(goal))     //when on same vertex does not fire;
            {
                System.out.println("Found it!");
            }
            System.out.println("Goal not found yet");
            closed.add(start);
            System.out.println(start.getNeighbors().size());
            for (Cell c : start.getNeighbors()) 
            {
                if (c.getIsCellBlocked())
                {
                    System.out.println("Visiting neighbor (" + c.getX() + ", " + c.getY() + ")");
                    if (!fringe.contains(c))
                    {
                        c.setGCost(Integer.MAX_VALUE);
                        c.setParent(null);
                    }
                    updateVertex(start, c);
                    System.out.print("Fringe at end of iteration: ");
                    for (Cell ce : fringe) {
                        System.out.print("(" + ce.getX() + ", " + ce.getY() + ") ");
                    }
                    System.out.println();
                }
            }
        }
        System.out.println("Goal not reached");
    }

    public void updateVertex(Cell s, Cell c)
    {
        System.out.println(s.getGCost());
        System.out.println(c(s, c));
        System.out.println(c.getGCost());
        if (s.getGCost() + c(s, c) < c.getGCost())
        {
            fringe.remove(c);
            c.setGCost(s.getGCost() + c(s, c));
            c.setParent(s);
            c.setHCost(h(c)); 
            fringe.add(c);
            System.out.println("Added " + c.getX() + ", " + c.getY());
        }

    }

    public double c(Cell c1, Cell c2) 
    {
        if (c1.getX() < c2.getX() && c1.getY() == c2.getY()) //c1 is to the left of c2
        {
            return 1;   
        }
        if (c1.getX() > c2.getX() && c1.getY() == c2.getY()) //c1 is to the right of c2
        {
            return 1;
        }
        if (c1.getX() == c2.getX() && c1.getY() < c2.getY()) //c1 is above c2
        {
            return 1;
        }
        if (c1.getX() == c2.getX() && c1.getY() > c2.getY()) //c1 is under c2
        {
            return 1;
        }
        if (c1.getX() == c2.getX() && c1.getY() == c2.getY()) //c1 is c2
        {
            return 0;
        }
        return Math.sqrt(2);
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
