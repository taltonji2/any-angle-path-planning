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
    PriorityQueue<Cell> fringe = new PriorityQueue<Cell>();
    ArrayList<Cell> closed = new ArrayList<Cell>();
    Double newCost;
    Double minF;
    
    public ArrayList<Cell> doThetaStar(Grid grid)
    {
        System.out.println("Theta Star");
        start = grid.getStart();
        goal = grid.getGoal();
        start.setParent(start);
        start.setGCost(0);
        start.setHCostThetaStar(start);
        start.setFCost();
        start.getNeighbors().remove(start);     
        System.out.println("Start: (" + start.getX() + ", " + start.getY() + ")");
        System.out.println("Goal: (" + goal.getX() + ", " + goal.getY() + ")");
        fringe.add(start);
        while (!fringe.isEmpty())
        {
            Cell current = fringe.poll();
            
            System.out.println("");
            System.out.println("Visiting (" + current.getX() + ", " + current.getY() + ")");
            if (current.equals(goal))     //when on same vertex does not fire;
            {
                closed.add(current);
                System.out.println("Found it!");
                for(Cell c : closed)
                {System.out.println("(" + c.getX() + ", " + c.getY() + ")");}
                break;
            }

            closed.add(current);
            fringe.removeIf(n -> (n.isVisited() == true));

            System.out.println(current.getNeighbors().size());
            for (Cell successor : current.getNeighbors()) 
            {
                if(!closed.contains(successor))
                {
                    newCost = current.getGCost() + c(current,successor);
                    if (!fringe.contains(successor))
                    {
                        successor.setGCost(Integer.MAX_VALUE);
                        successor.setParent(null);
                    }
                    if (lineOfSight(current.getParent(), successor))
                    {
                        newCost = current.getParent().getGCost() + c(current.getParent(),successor);
                        if(newCost < successor.getGCost())
                        {
                            successor.setGCost(newCost);
                            successor.setHCostThetaStar(successor); 
                            successor.setFCost();
                            successor.setParent(current.getParent());
                            if(fringe.contains(successor))
                                fringe.remove(successor);
                        }
                    }
                    else
                    {
                        newCost = current.getGCost() + c(current,successor);
                        if(newCost < successor.getGCost())
                        {
                            successor.setGCost(newCost);
                            successor.setHCostThetaStar(successor); 
                            successor.setFCost();
                            successor.setParent(current);
                            if(fringe.contains(successor))
                                fringe.remove(successor);
                        }
                    }
                    successor.visit();
                    fringe.add(successor);                    
                    if(fringe.size() > 1 && !fringe.peek().equals(successor) && thresholdBasedFloatsComparison(successor.getFCost(), fringe.peek().getFCost()))
                    {
                        // ... compare g() cost
                        compareGCost(successor);
                    }
                    System.out.println("Added " + "("+successor.getX() + ", " + successor.getY()+ ")" + " " + successor.getFCost() + " to fringe");
                }
            }
            System.out.print("----------------------------------------");
        }
        return closed;
    }

    //Tie Break based on higher g()
    public void compareGCost(Cell successor)
    {
        if(!thresholdBasedFloatsComparison(successor.getGCost(), fringe.peek().getGCost())) 
        {
            if (successor.getGCost() > fringe.peek().getGCost())
            {
                //... Successor value is larger ... Successor is closer to Line
                fringe.remove();
            } else
            {
                //... Fringe value is larger
                fringe.remove(successor); 
            }
        } else
        {
            //next level compare
            //isSuccessorCloserThanMinInDistance(Grid.getDistanceFromStartGoalLine(successor), Grid.getDistanceFromStartGoalLine(fringe.peek()), successor);
        }
    }

    public int isSuccessorCloserThanMinInDistance(Double d1, Double d2, Cell successor) {
        if (d1 > d2) 
        { // successor is further than min
            return 0;
        } else {
            // successor is closer than min
            return 1;
        }
   }

    private static Boolean thresholdBasedFloatsComparison(double d1, double d2) 
    {
    final double THRESHOLD = .0001;
    if (Math.abs(d1 - d2) < THRESHOLD)
    //... float values are the same  
        return true;
    else
    //... float values are NOT the same
        return false;
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
                c.setHCostThetaStar(c);
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
                c.setHCostThetaStar(c);
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
                    if (Grid.Instance().cells[x0 + ((sx - 1)/2)][y0 + ((sy - 1)/2)].bFree == false) //if the cell is blocked
                    {
                        return false;
                    }
                    y0 = y0 + sy;
                    f = f - dx;
                }
                if (f != 0 && Grid.Instance().cells[x0 + ((sx - 1)/2)][y0 + ((sy - 1)/2)].bFree == false) //if the cell is blocked
                {
                    return false;
                }
                if (dy == 0 && Grid.Instance().cells[x0 + ((sx - 1)/2)][y0].bFree == false && Grid.Instance().cells[x0 + ((sx - 1)/2)][y0 - 1].bFree == false) //if the cell is blocked
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
                    if (Grid.Instance().cells[x0 + ((sx - 1)/2)][y0 + ((sy - 1)/2)].bFree == false)
                    {
                        return false;
                    }
                    x0 = x0 + sx;
                    f = f - dy;
                }
                if (f != 0 && Grid.Instance().cells[x0 + ((sx - 1)/2)][y0 + ((sy - 1)/2)].bFree == false)
                {
                    return false;
                }
                if (dx == 0 && Grid.Instance().cells[x0][y0 + ((sy - 1)/2)].bFree == false && Grid.Instance().cells[x0 - 1][y0 + ((sy - 1)/2)].bFree == false)
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

