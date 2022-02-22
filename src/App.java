import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class App {
    public static void main(String[] args) throws Exception {
        // System.out.println("\n");
        // System.out.println("Choose an option: ");
        // System.out.println("1: A*");
        // System.out.println("2: Theta*");
        // System.out.println("\n");

        // BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        // String response = reader.readLine();
        // if(response.equals("1"))
        // {
        //     reader.close();
            String currentDirectory = System.getProperty("user.dir");
            String fileName = currentDirectory + "/grid0.txt";
            Grid grid = GridStorage.restoreGrid(fileName);
            grid.LoadAdj();
            
            BFS bfs = new BFS();
            AStar a = new AStar();
            if(bfs.doBFS(grid))
            {
                ArrayList<Cell> closedList = a.doAStar(grid);
                DisplayGrid displayGrid = new DisplayGrid(grid, "1", closedList);
                displayGrid.paintPath(closedList);
            } else {
                System.out.println("No Such Path");
            }
        // }
        // if(response.equals("2"))
        // {
        //     reader.close();
    //         String currentDirectory = System.getProperty("user.dir");
    //         String fileName = currentDirectory + "/grid0.txt";
    //         Grid grid = GridStorage.restoreGrid(fileName);
    //         grid.createEdgeCells();
    //         grid.LoadAdj();
            
            
    //         BFS bfs = new BFS();
    //         ThetaStar t = new ThetaStar();
    //         if(bfs.doBFS(grid))
    //         {
    //             ArrayList<Cell> closedList = t.doThetaStar(grid);
    //             DisplayGrid displayGrid = new DisplayGrid(grid, "2", closedList);
    //             displayGrid.paintPath(closedList);
    //         } else {
    //             System.out.println("No Such Path");
    //         }
    //     // }
     }
}
