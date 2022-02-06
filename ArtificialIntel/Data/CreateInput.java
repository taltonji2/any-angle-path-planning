package ArtificialIntel.Data;

import java.io.File;  
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;



//Java Class to create 50 grid.txt files with randomized closed cells
public class CreateInput {
    int numOfGridFiles = 1;
    int getNumOfGridFiles(){ return this.numOfGridFiles;}
    int columns = 8; //x
    int rows = 4; //y
    int numOfCells = rows * columns;
    double numOfCellsBlocked = numOfCells * .10; //10% blocked
    
    //check if has path with algo
    //if no valid path then regenerate random bocked.

    public static void main(String[] args) {
        CreateInput createInput = new CreateInput(8,4);
        
    }

    CreateInput(int rows, int columns) 
    {
        this.rows = rows;
        this.columns = columns;
        CreateFile();   
    }

    private void CreateFile() 
    {
        int n = this.getNumOfGridFiles();
        for (int i = 0; i < n; i++)
        {
            try{
                String currentDirectory = System.getProperty("user.dir"); //pc
                String fileName = currentDirectory + "\\" + "resources\\" + "grid" + i + ".txt"; //pc
                //String fileName = "/Users/timothy/Any-Angle-Path-Planning/resources/" + "grid" + i + ".txt"; //mac
                File myObj = new File(fileName);
                Grid grid = createGrid ();
                WriteToFile(fileName, grid);
                if (myObj.createNewFile())
                {
                    System.out.println("File Created: " + myObj.getName());
                } else {
                    System.out.println("File already exists.");
                }
            } catch (IOException e) {
                System.out.println("An error occured.");
                e.printStackTrace();
            }
        }
    }

    private Vertex RandomPoint (int columns, int rows)
    {
        Random rand = new Random();
        int randIntX = rand.nextInt(columns); //x
        int randIntY = rand.nextInt(rows); //y
        Vertex vertex = new Vertex(randIntX, randIntY); 
        return vertex;
    }
     
    private Grid createGrid () 
    {
        Grid grid = new Grid(this.columns, this.rows);

        int cellCount = 0;
        for(int i = 0; i < this.columns; i++)
            {
                for(int j = 0; j < this.rows; j++)
                {
                    Cell cell =  new Cell(i, j, 0); //was i+1 j+1

                    while(cellCount < numOfCells){
                        ++cellCount;
                        grid.add(cell);   
                        break;
                    }
                }
            }

        int count = 0;
        while(count < numOfCellsBlocked)
        {
            Vertex v = RandomPoint(columns, rows);
            if(v.y == rows - 1)
            {
                continue;
            }
            if(v.x == columns - 1)
            {
                continue;
            }
            if(grid.cells[v.x][v.y].bFree == true)
            {
                grid.cells[v.x][v.y].bFree = false;
                count++; 
            } 
        }
        return grid;
    }

    private void WriteToFile(String filename, Grid grid)
    {
        try {
            FileWriter myWriter = new FileWriter(filename);
            Vertex v = RandomPoint(columns, rows);
            myWriter.write(v.x + " " + v.y + "\n");
            v = RandomPoint(columns, rows);
            myWriter.write(v.x + " " + v.y + "\n");
            myWriter.write(columns + " " + rows + "\n");
            for(int i = 0; i < grid.cells.length; i++)
            {
                for(int j = 0; j < grid.cells[i].length; j++)
                {
                    int booleanInt = grid.cells[i][j].bFree ? 0 : 1;
                    myWriter.write(grid.cells[i][j].x + " " + grid.cells[i][j].y + " " + booleanInt + "\n");
                }
            }
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
          } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }
    }



}
