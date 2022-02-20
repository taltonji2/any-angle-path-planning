import java.util.ArrayList;

public class App {
    public static void main(String[] args) throws Exception {
        
        String currentDirectory = System.getProperty("user.dir");
        String fileName = currentDirectory + "/grid0.txt";
        Grid grid = GridStorage.restoreGrid(fileName);
        grid.createEdgeCells();
        grid.LoadAdj();
        
        DisplayGrid displayGrid = new DisplayGrid(grid);
        BFS bfs = new BFS();
        AStar a = new AStar();
        if(bfs.doBFS(grid))
        {
            ArrayList<Cell> openlist = a.doAStar(grid);
            displayGrid.paintPath(openlist);
        } else {
            System.out.println("No Such Path");
        }
    }
}
