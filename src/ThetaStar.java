import java.util.ArrayList;
import java.util.PriorityQueue;
/*
*       start       Node       goal
*         |----------|---------|
*             g(n)      h(n)
*         |--------------------|
*                  f() 
*/
public class ThetaStar
{
    Cell start, goal;
    PriorityQueue<Cell> fringe = new PriorityQueue<Cell>(1, (Cell c1, Cell c2) -> Double.compare(c1.getFCost(), c2.getFCost()));
    ArrayList<Cell> closed = new ArrayList<Cell>();
    Grid g;
    
    public ArrayList<Cell> doThetaStar(Grid grid)
    {
        g = grid;
        start = grid.getStart();
        goal = grid.getGoal();
        start.setParent(start);
        start.setGCost(0);
        //start.setHCost(start); 
        start.getNeighbors().remove(start); 
        System.out.println("Start: (" + start.getX() + ", " + start.getY() + ")");
        System.out.println("Goal: (" + goal.getX() + ", " + goal.getY() + ")");
        fringe.add(start);
        int n = grid.cells.length;
        int m = grid.cells[1].length;
        for (int i = 0; i < m; i++)
        {
            for (int j = 0; j < n; j++)
            {
                if (grid.cells[j][i].getIsCellFree() == false)
                {
                    System.out.print("(" + grid.cells[j][i].getX() + " " + grid.cells[j][i].getY() + ") B ");
                }
                else
                {
                    System.out.print("(" + grid.cells[j][i].getX() + " " + grid.cells[j][i].getY() + ") O ");
                }
            }
            System.out.println();
        }

        while (!fringe.isEmpty())
        {
            start = fringe.poll();
            System.out.println("Visiting (" + start.getX() + ", " + start.getY() + ")");
            if (start.equals(goal))     //when on same vertex does not fire;
            {
                System.out.println("Found it!");
                System.out.println(start.getFCost() + " " + start.getGCost() + " " + start.getHCost());
                return closed;
            }
            else if (closed.contains(start))
            {
                continue;
            }
            System.out.println("Goal not found yet");
            closed.add(start);
            System.out.println(start.getNeighbors().size());
            for (Cell c : start.getNeighbors()) 
            {
                if (c.getIsCellFree())
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
        return closed;
    }

    public void updateVertex(Cell s, Cell c)
    {
        System.out.println("S: (" + s.getX() + ", " + s.getY() + ") ");
        System.out.println("S Parent: (" + s.getParent().getX() + ", " + s.getParent().getY() + ") ");
        System.out.println("C: (" + c.getX() + ", " + c.getY() + ") ");
        if (lineOfSight(s.getParent(), c))
        {
            if (s.getParent().getGCost() + c(s.getParent(), c) < c.getGCost())
            {
                fringe.remove(c);
                c.setGCost(s.getParent().getGCost() + c(s.getParent(), c));
                c.setParent(s.getParent());
               // c.setHCost(c);
                c.setFCost(c.getGCost() + c.getHCost());
                fringe.add(c);
            }
        }
        else
        {
            if (s.getGCost() + c(s, c) < c.getGCost())
            {
                fringe.remove(c);
                c.setGCost(s.getGCost() + c(s, c));
                c.setParent(s);
               // c.setHCost(c);
                c.setFCost(c.getGCost() + c.getHCost());
                fringe.add(c);
            }
        }
    }

    private boolean lineOfSight(Cell parent, Cell c) {
        int x0 = parent.getX();
        int y0 = parent.getY();
        int x1 = c.getX();
        int y1 = c.getY();
        int f = 0;
        int dy = y1 - y0;
        int dx = x1 - x0;
        int sx = 0;
        int sy = 0;
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
                    if (g.cells[x0 + ((sx - 1)/2)][y0 + ((sy - 1)/2)].bFree == false) //if the cell is blocked
                    {
                        return false;
                    }
                    y0 = y0 + sy;
                    f = f - dx;
                }
                if (f != 0 && g.cells[x0 + ((sx - 1)/2)][y0 + ((sy - 1)/2)].bFree == false) //if the cell is blocked
                {
                    return false;
                }
                if (dy == 0 && g.cells[x0 + ((sx - 1)/2)][y0].bFree == false && g.cells[x0 + ((sx - 1)/2)][y0 - 1].bFree == false) //if the cell is blocked
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
                    if (g.cells[x0 + ((sx - 1)/2)][y0 + ((sy - 1)/2)].bFree == false)
                    {
                        return false;
                    }
                    x0 = x0 + sx;
                    f = f - dy;
                }
                if (f != 0 && g.cells[x0 + ((sx - 1)/2)][y0 + ((sy - 1)/2)].bFree == false)
                {
                    return false;
                }
                if (dx == 0 && g.cells[x0][y0 + ((sy - 1)/2)].bFree == false && g.cells[x0 - 1][y0 + ((sy - 1)/2)].bFree == false)
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
        return Math.sqrt(Math.pow((c.getX() - goal.getX()), 2) + Math.pow((c.getY() - goal.getY()), 2));
    }

    public static double f(Cell c)
    {
        return h(c) + g(c);
        
    }

}

