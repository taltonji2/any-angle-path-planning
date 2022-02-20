import java.util.ArrayList;
import java.util.PriorityQueue;
/*
*       start       Node       goal
*         |----------|---------|
*             g(n)      h(n)
*         |--------------------|
*           f(n) = g(n) + h(n)
*/
public class AStar 
{
    Cell start, goal;
    PriorityQueue<Cell> fringe = new PriorityQueue<Cell>();
    ArrayList<Cell> closed = new ArrayList<Cell>();
    //ArrayList<Cell> dump = new ArrayList<Cell>();
    Double newCost;
    Double minF;
    public ArrayList<Cell> doAStar(Grid grid)
    {
        start = grid.getStart();
        goal = grid.getGoal();
        start.setParent(start);
        start.setGCost(0);
        start.setHCost(start);
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
                    if(newCost < successor.getGCost())
                    {
                        successor.setGCost(newCost);
                        successor.setHCost(successor); 
                        successor.setFCost();
                        successor.setParent(current);
                        if(fringe.contains(successor))
                            fringe.remove(successor);
                    }
                    
                    successor.visit();
                    fringe.add(successor);

                    
                    if(fringe.size() > 1)
                    {
                        if(successor.getFCost() == fringe.peek().getFCost())
                        {
                            Double sucD = Grid.getDistanceFromStartGoalLine(successor);
                            Double fringeD = Grid.getDistanceFromStartGoalLine(fringe.peek());
                            compareDistance(sucD, fringeD, successor, fringe.peek());
                        }
                    }
                    System.out.println("Added " + "("+successor.getX() + ", " + successor.getY()+ ")" + " " + successor.getFCost() + " to fringe");
                }
            }
            System.out.print("Fringe at end of iteration: ");
                for (Cell ce : fringe) {
                    System.out.println("");
                    System.out.println("(" + ce.getX() + "," + ce.getY() + ") " + ce.getFCost() + "  ");
                }
                System.out.println();
        }
        return closed;
    }
   
    
    public void compareDistance(Double d1, Double d2, Cell successor, Cell fringeMin) {
        if (d1 > d2) { // successor is further than min
            fringe.remove(successor); 
        } else if (d1 < d2) {
            // successor is closer than min
            fringe.remove();
        } 
        else if (successor.getGCost() > fringeMin.getGCost())
        {
            fringe.remove();
        }
    }

    
    public double c(Cell current, Cell successor) 
    {
        if (successor.getX() < current.getX() && successor.getY() == current.getY()) //Successor is to the left
            return 1;   
        if (successor.getX() > current.getX() && successor.getY() == current.getY()) //Successor is to the right
            return 1;
        if (successor.getX() == current.getX() && successor.getY() < current.getY()) //Successor is above
            return 1;
        if (successor.getX() == current.getX() && successor.getY() > current.getY()) //Successor is under
            return 1;
        if (successor.getX() == current.getX() && successor.getY() == current.getY()) //Successor is Current
            return 0;
        return Math.sqrt(2); //diagonal
    }
}
