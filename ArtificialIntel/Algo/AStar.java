package ArtificialIntel.Algo;
import java.util.ArrayList;
import java.util.Comparator;
import java.awt.Point;
import java.util.PriorityQueue;
/*
*       start       Node       target
*         |----------|---------|
*              g(Node)      h(Node)
*         |--------------------|
*                  f() 
*/

import javax.lang.model.util.ElementScanner6;

import ArtificialIntel.Data.Cell;
import ArtificialIntel.Data.Grid; 


public class AStar
{
    Cell start, goal;
    Grid g;
    PriorityQueue<Cell> fringe = new PriorityQueue<Cell>(0, (Cell c1, Cell c2) -> Double.compare(f(c1), f(c2)));
    ArrayList<Cell> closed = new ArrayList<Cell>();
    public boolean doAStar(Cell s, Cell g, Grid grid)
    {
        this.g = grid;
        start = s;
        goal = g;
        start.parent = start;
        s = start;
        fringe.add(s);
        while (!fringe.isEmpty())
        {
            s = fringe.poll();
            if (s.equals(goal))
            {
                return true;
            }
            closed.add(s);
            for (Cell c : getNeighbors(s)) {
                if (c.IsFree())
                {
                    if (!fringe.contains(c))
                    {
                        c.setCost(Integer.MAX_VALUE);
                        c.parent = null;
                    }
                    updateVertex(s, c);
                }
            }
        }
        return false;
    }

    public void updateVertex(Cell s, Cell c)
    {
        if (g(s) + c(s, c) < g(c))
        {
            c.setCost(g(s) + c(s, c));
            c.parent = s;
            if (fringe.contains(c))
            {
                fringe.remove(c);
            }
            fringe.add(c);
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
    private double g(Cell cell)
    {
        if (cell.equals(this.start))
        {
            this.start.setCost(0);
        }
        return cell.cost;
    }

    private int h(Cell c)
    {
        return (int) (Math.sqrt(2) * Math.min(Math.abs(c.getX() - goal.getX()), Math.abs(c.getY() - goal.getY())) + Math.max(Math.abs(c.getX() - goal.getX()), Math.abs(c.getY() - goal.getY())) + Math.min(Math.abs(c.getX() - goal.getX()), Math.abs(c.getY() - goal.getY())));
    }

    private double f(Cell c)
    {
        return h(start) + g(start);
        
    }

    public ArrayList<Cell> getNeighbors(Cell c)
    {
        for (int i = c.getY() + 1; i >= c.getY() - 1; i--)
        {
            for (int j = c.getX() - 1; j <= c.getX() + 1; j++)
            {
                c.neighbors.add(g.cells[i][j]);
            }   
        }
        return c.neighbors;
    }
    
}
