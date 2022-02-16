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
            for(Cell c : current.getNeighbors()){
                if(c.isVisited() == false){
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
        }
        return false;
    }
}