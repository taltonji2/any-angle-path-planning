package ArtificialIntel.Algo;
import java.util.ArrayList;
import java.util.PriorityQueue;
/*
*       start       Node       target
*         |----------|---------|
*              g(Node)      h(Node)
*         |--------------------|
*                  f() 
*/

import ArtificialIntel.Data.Cell;
import ArtificialIntel.Data.Grid;

public class AStar
{
    Cell start, goal;
    Grid grid;
    PriorityQueue<Cell> fringe = new PriorityQueue<Cell>(1, (Cell c1, Cell c2) -> Double.compare(c1.f, c2.f));
    ArrayList<Cell> closed = new ArrayList<Cell>();
    
    public boolean doAStar(Cell s, Cell g, Grid grid)
    {
        this.grid = grid;
        start = grid.getStart();
        goal = grid.getGoal();
        start.parent = start;
        s = start;
        s.g = 0;
        s.neighbors.remove(start);
        System.out.println("Start: (" + start.getX() + ", " + start.getY() + ")");
        System.out.println("Goal: (" + goal.getX() + ", " + goal.getY() + ")");
        fringe.add(s);
        s.h = h(s);
        s.f = s.g + s.h;
        while (!fringe.isEmpty())
        {
            s = fringe.poll();
            System.out.println("Visiting (" + s.getX() + ", " + s.getY() + ")");
            if (s.equals(goal))     //when on same vertex does not fire;
            {
                System.out.println("Found it!");
                return true;
            }
            System.out.println("Goal not found yet");
            closed.add(s);
            System.out.println(s.neighbors.size());
            
            for (Cell c : s.neighbors) 
            {
                if (c.IsFree())
                {
                    System.out.println("Visiting neighbor (" + c.getX() + ", " + c.getY() + ")");
                    if (!fringe.contains(c))
                    {
                        c.g = Integer.MAX_VALUE;
                        c.parent = null;
                    }
                    updateVertex(s, c);
                    System.out.print("Fringe at end of iteration: ");
                    for (Cell ce : fringe) {
                        System.out.print("(" + ce.getX() + ", " + ce.getY() + ") ");
                    }
                    System.out.println();
                }
            }
        }
        System.out.println("Goal not reached");
        return false;
    }

    public void updateVertex(Cell s, Cell c)
    {
        System.out.println(s.g);
        System.out.println(c(s, c));
        System.out.println(c.g);
        if (s.g + c(s, c) < c.g)
        {
            fringe.remove(c);
            c.g = s.g + c(s, c);
            c.parent = s;
            c.h = h(c);
            c.f = c.g + c.h;
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
        if (c1.getX() == c2.getX() && c1.getY() < c2.getY()) //c1 is under c2
        {
            return 1;
        }
        if (c1.getX() == c2.getX() && c1.getY() > c2.getY()) //c1 is above c2
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
            start.setCost(0);
        }
        return cell.g;
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
