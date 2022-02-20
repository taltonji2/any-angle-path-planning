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

        grid = Grid.Instance(dimensions.getKey()+1,dimensions.getValue()+1);
        
        Cell cell = restoreCell(bufferedReader); 
        int cellCount = 0;
        while(cell != null){
            ++cellCount;
            grid.add(cell);
            cell = restoreCell(bufferedReader);
        }
        grid.setStart(grid.cells[vertexStart.getX()-1][vertexStart.getY()-1]);
        grid.setGoal(grid.cells[vertexGoal.getX()-1][vertexGoal.getY()-1]); 

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