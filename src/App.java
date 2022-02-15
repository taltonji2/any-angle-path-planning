

public class App {
    public static void main(String[] args) throws Exception {
        
        String currentDirectory = System.getProperty("user.dir");
        String fileName = currentDirectory + "/grid0.txt";
        Grid grid = GridStorage.restoreGrid(fileName);
        grid.LoadAdj();
        
        DisplayGrid displayGrid = new DisplayGrid(grid);
        BFS bfs = new BFS();
        AStar a = new AStar();
        ThetaStar t = new ThetaStar();
        if(bfs.doBFS(grid))
        {
            //a.doAStar(grid);
            t.doThetaStar(grid);
        }
    }
}
