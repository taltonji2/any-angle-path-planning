package ArtificialIntel.Data;
import java.io.*;
import java.util.ArrayList;

class GridFileFormatException extends Exception{
    String message;
    GridFileFormatException(){
    }
    GridFileFormatException(String message){
        this.message = message;
    }
    public String toString(){
        return message + super.toString();
    }
}

public class GridStorage {
    public static Grid restoreGrid(String fileName) throws IOException{
    
    Grid grid = null;
    BufferedReader bufferedReader = null;
    try {
        FileReader reader = new FileReader(fileName);
        bufferedReader = new BufferedReader(reader);

        Pair<Integer, Integer> pair = restoreIntegerPair(bufferedReader);
        if(pair == null)
            throw new GridFileFormatException("missing vertex start");
        Vertex vertexStart = new Vertex(pair.getKey(), pair.getValue());
        
        pair = restoreIntegerPair(bufferedReader);
        if(pair == null)
            throw new GridFileFormatException("missing vertex goal");
        Vertex vertexGoal = new Vertex(pair.getKey(), pair.getValue());

        Pair<Integer, Integer> dimensions = restoreIntegerPair(bufferedReader);

        grid = new Grid(dimensions.getKey(),dimensions.getValue());
        
        grid.start = new Cell(vertexStart.getX(), vertexStart.getY(), 0);
        grid.goal = new Cell(vertexGoal.getX(), vertexGoal.getY(), 0); 

        Cell cell = restoreCell(bufferedReader); 
        int cellCount = 0;
        while(cell != null){
            ++cellCount;
            grid.add(cell);
            cell = restoreCell(bufferedReader);
        }
        reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch(GridFileFormatException e){
            e.printStackTrace();

        }
        finally{
            if(bufferedReader!=null)
                bufferedReader.close();
        }
        
        for (int i = 0; i < grid.cells.length; i++) 
        {
            for (int j = 0; j < grid.cells[i].length; j++)
            {
                //c.neighbors.clear();
                
                if ( grid.north(grid.cells[i][j]) != null)
                {
                    grid.cells[i][j].neighbors.add(grid.north(grid.cells[i][j]));
                }

                if (grid.south(grid.cells[i][j]) != null)
                {
                    grid.cells[i][j].neighbors.add(grid.south(grid.cells[i][j]));
                }

                if (grid.east(grid.cells[i][j]) != null)
                {
                    grid.cells[i][j].neighbors.add(grid.east(grid.cells[i][j]));
                } 
                if (grid.west(grid.cells[i][j]) != null)
                {
                    grid.cells[i][j].neighbors.add(grid.west(grid.cells[i][j]));
                }
                if (grid.northEast(grid.cells[i][j]) != null)
                {
                    grid.cells[i][j].neighbors.add(grid.northEast(grid.cells[i][j]));
                } 
            
                if (grid.northWest(grid.cells[i][j]) != null)
                {
                    grid.cells[i][j].neighbors.add(grid.northWest(grid.cells[i][j]));
                }
           
                if (grid.southEast(grid.cells[i][j]) != null)
                {
                    grid.cells[i][j].neighbors.add(grid.southEast(grid.cells[i][j]));
                } 
            
                if (grid.southWest(grid.cells[i][j]) != null)
                {
                    grid.cells[i][j].neighbors.add(grid.southWest(grid.cells[i][j]));
                } 
            
            }
        }

        Grid temp = grid;
        for (Cell[] eachRowTemp : temp.cells) {
            for (Cell c : eachRowTemp) {
                ArrayList<Cell> neighborTemp = c.neighbors;
                for (Cell[] eachRowGrid : grid.cells) {     //traverses every cell in grid
                    for (Cell cGrid : eachRowGrid) {
                        if(cGrid.neighbors.contains(c))
                        {
                            cGrid.neighbors.get(cGrid.neighbors.indexOf(c)).neighbors = neighborTemp;
                        }
                    }
                }
            }
        }

        return grid;
    }
    
    //reads grid txt file and restores integer pairs for start and target vertex
    static Pair<Integer,Integer> restoreIntegerPair(BufferedReader bufferedReader) 
        throws IOException, GridFileFormatException{
        Pair<Integer,Integer> pair = null;
        String line = bufferedReader.readLine();
        if(line!=null){
            String parts[] = line.split(" ");
            if(parts.length > 1){
                int width = Integer.parseInt(parts[0]);
                int height = Integer.parseInt(parts[1]);
                pair = new Pair<Integer,Integer>(width,height);
            }
            else{
                throw new GridFileFormatException();
            }
        }
        return pair;
    }

    //reads grid txt file and restores the cells described
    static Cell restoreCell(BufferedReader bufferedReader) 
        throws IOException, GridFileFormatException{
        Cell cell = null;
        String line = bufferedReader.readLine();
        if(line!=null){
            String parts[] = line.split(" ");
            if(parts.length > 2){
                int x = Integer.parseInt(parts[0]);
                int y = Integer.parseInt(parts[1]);
                int free = Integer.parseInt(parts[2]);

                cell = new Cell(x,y,free);
            }
            else{
                throw new GridFileFormatException();
            }
        }
        return cell;
    }
}