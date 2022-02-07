package ArtificialIntel.Algo;

import java.util.ArrayList;
import java.util.PriorityQueue;

import ArtificialIntel.Data.Cell;
import ArtificialIntel.Data.Grid;

public class ThetaStar {
    Cell start, goal;
    Grid g;
    PriorityQueue<Cell> fringe = new PriorityQueue<Cell>(1, (Cell c1, Cell c2) -> Double.compare(f(c1), f(c2)));
    ArrayList<Cell> closed = new ArrayList<Cell>();
    
    public boolean doThetaStar(Cell s, Cell g, Grid grid)
    {
        this.g = grid;
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
            if (s.equals(goal))
            {
                System.out.println("Found it!");
                return true;
            }
            //System.out.println("Goal not found yet");
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
                        System.out.print("(" + c.getX() + ", " + c.getY() + ") ");
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
        System.out.println(s.parent == null);
        if (lineOfSight(s.parent, c))
        {
             if (s.parent.g + c(s.parent, c) < c.g)
             {
                 c.g = s.parent.g + c(s.parent, c);
                 c.parent = s.parent;
                 if (fringe.contains(c))
                 {
                     fringe.remove(c);
                 }
                 c.h = h(c);
                 c.f = c.g + c.h;
                 fringe.add(c);
             }
        }
        else
        {
            if (s.g + c(s, c) < c.g)
            {
                c.g = s.g + c(s, c);
                c.parent = s;
                if (fringe.contains(c))
                {
                    fringe.remove(c);
                }
                c.h = h(c);
                c.f = c.g + c.h;
                fringe.add(c);
            }
        }
    }

    public boolean lineOfSight(Cell s, Cell s1)
    {
        int x0 = s.getX();
        int y0 = s.getY();
        int x1 = s1.getX();
        int y1 = s1.getY();
        int f = 0;
        int dy = y1 - y0;
        int dx = x1 - x0;
        int sy = 0;
        int sx = 0;
        if (dy < 0)
        {
            dy *= -1;
            sy = -1;
        }
        else
        {
            sy = 1;
        }
        if (dx < 0)
        {
            dx *= -1;
            sx = -1;
        }
        else
        {
            sx = 1;
        }
        if (dx >= dy)
        {
            while (x0 != x1)
            {
                f = f + dy;
                if (f >= dx)
                {
                    if (g.cells[x0 + ((sx - 1)/2)][y0 + (sy - 1)/2] != null)
                    {
                        return false;
                    }
                    y0 = y0 + sy;
                    f = f - dx;
                }
                if (f != 0 && g.cells[x0 + ((sx - 1)/2)][y0 + (sy - 1)/2] != null)
                {
                    return false;
                }
                if (dy == 0 && g.cells[x0 + ((sx - 1)/2)][y0] != null && g.cells[x0 + ((sx - 1)/2)][y0 - 1] != null)
                {
                    return false;
                }
                x0 = x0 + sx;
            }
        }
        else
        {
            while (y0 != y1)
            {
                f = f + dx;
                if (f >= dy)
                {
                    if (g.cells[x0 + ((sx - 1)/2)][y0 + (sy - 1)/2] != null)
                    {
                        return false;
                    }
                    x0 = x0 + sx;
                    f = f - dy;
                    
                }
                if (f != 0 && g.cells[x0 + ((sx - 1)/2)][y0 + (sy - 1)/2] != null)
                {
                    return false;
                }
                if (dx == 0 && g.cells[x0][y0 + (sy - 1)/2] != null && g.cells[x0 - 1][y0 + (sy - 1)/2] != null)
                {
                    return false;
                }
                y0 = y0 + sy;
            }
        }
        return true;
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
        return cell.g;
    }

    private int h(Cell c)
    {
        return (int) (Math.sqrt(2) * Math.min(Math.abs(c.getX() - goal.getX()), Math.abs(c.getY() - goal.getY())) + Math.max(Math.abs(c.getX() - goal.getX()), Math.abs(c.getY() - goal.getY())) + Math.min(Math.abs(c.getX() - goal.getX()), Math.abs(c.getY() - goal.getY())));
    }

    private double f(Cell c)
    {
        return h(start) + g(start);
        
    }
}
