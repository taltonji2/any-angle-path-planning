import java.util.ArrayList;
import java.util.List;
public class BFS { 
    public Boolean doBFS(Grid grid)
    {
        List<Cell> queue = new ArrayList<Cell>();  
        grid.getStart().setVisited(true);
        Cell current = grid.getStart();
        queue.add(current);
        while(!queue.isEmpty())
        {
            //System.out.println(current.getX() + " " + current.getY());
            current = queue.get(0);
            //System.out.println("Visiting: " + current.getX() + " " + current.getY());
            for(Cell c : current.getNeighbors()){
                if(c.isVisited() == false){
                    //System.out.println("Visiting neighbor: " + c.getX() + " " + c.getY());
                    if(c.equals(grid.getGoal()))
                    {
                        queue.add(c);
                        return true;
                    }
                    c.setVisited(true);
                    queue.add(c);
                }
            }
            queue.remove(0);
            //System.out.print("Queue after iteration: ");
            for (Cell cell : queue) {
                //System.out.print("(" + cell.getX() + " " + cell.getY() + ")");
            }
            //System.out.println();
        }
        return false;
    }
}