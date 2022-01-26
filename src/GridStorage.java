package src;
import java.io.*;

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

class Vertex{
    protected int x;
    protected int y;
    Vertex(int x, int y){
        this.x=x;
        this.y=y;
    }
    int getX(){
        return x;
    }
    int getY(){
        return y;
    }
}

//likely the class we will have as a node in the priority que
class Cell extends Vertex{ 
    Grid grid;

    protected boolean bFree = true;
    boolean IsFree(){
        return bFree;
    }
    Cell(Grid grid, int x, int y, int free){
        super(x, y);
        this.grid = grid;
        bFree = free == 0;
    }
    Cell(int x, int y, int free){
        super(x, y);
        bFree = free == 0;
    }
    boolean getFree(){
        return bFree;
    }

    Cell north(){
        int yNorth = y-1;
        if(yNorth<0)
            return null;
        return grid.cells[x][yNorth];
    }
    Cell northEast(){
        int yNorth = y-1;
        int xEast = x+1;
        if(yNorth<0 || xEast > grid.getWidth()-1)
            return null;
        return grid.cells[xEast][yNorth];
    }
    //add cartesian directionals
}

class Grid{
    Cell [][] cells;
    int getWidth(){
        return cells.length;
    }
    int getHeight(){
        return cells[0].length;
    }
    Grid(int width, int height){
        cells = new Cell[width][height];
    }
    void add(Cell cell){
        // convert the cell position identifiers to array index
        int ix = cell.getX()-1;
        int iy = cell.getY()-1;
        cells[ix][iy] = cell;
    }
}

class GridStorage {
    static Grid restoreGrid(String fileName) throws IOException{
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

        return grid;
    }

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