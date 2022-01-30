package ArtificialIntel.Data;

import java.io.File;  
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;



//Java Class to create 50 grid.txt files with randomized closed cells
public class CreateInput {
    int numOfGridFiles = 1;
    int getNumOfGridFiles(){ return this.numOfGridFiles;}
    int rows = 50; 
    int columns = 100; 
    int numOfCells = rows * columns;
    double numOfCellsBlocked = numOfCells * .10; //10% blocked
    
    //check if has path with algo
    //if no valid path then regenerate random bocked.

    public static void main(String[] args) {
        CreateInput createInput = new CreateInput(50,100);
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
                String currentDirectory = System.getProperty("user.dir");
                String fileName = currentDirectory + "\\" + "resources\\" + "grid" + i + ".txt";
                File myObj = new File(fileName);
                int[][] abstractGrid = AbstractGrid();
                WriteToFile(fileName, abstractGrid);
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

    private Vertex RandomPoint ()
    {
        Random rand = new Random();
        int randIntX = rand.nextInt(100); //x
        int randIntY = rand.nextInt(50); //y
        Vertex vertex = new Vertex(randIntX, randIntY); 
        return vertex;
    }

    private void RandomBlockedCells (int[][] abstractGird) 
    {         
        for (int i = 0; i < this.numOfCellsBlocked; i++)
        {
            Vertex randomVertex = RandomPoint(); 
            if (abstractGird[randomVertex.x][randomVertex.y] == 0)
                abstractGird[randomVertex.x][randomVertex.y] = 1;
            else {
                while(abstractGird[randomVertex.x][randomVertex.y] != 0) 
                {
                    randomVertex = RandomPoint();   
                }
                abstractGird[randomVertex.x][randomVertex.y] = 1;  
            }     
        }
    }

    private int[][] AbstractGrid ()
    {
        int[][] abstractGrid = new int[this.columns][this.rows];
        return abstractGrid; 
    }

    

    private void WriteToFile(String filename, int[][] abstractGrid)
    {
        try {
            FileWriter myWriter = new FileWriter(filename);
            Vertex v = RandomPoint();
            myWriter.write(v.x + " " + v.y + "\n");
            v = RandomPoint();
            myWriter.write(v.x + " " + v.y + "\n");
            myWriter.write(columns + " " + rows + "\n");
            
            for( int i = 0; i < abstractGrid.length-1; i++)
            {
                for( int j = 0; j < abstractGrid[i].length-1; j++)
                {
                    myWriter.write(i + " " + j + abstractGrid[i][j] + "\n");
                }
            }
            //loop through abstract grid and return indecies with their boolean value

            myWriter.close();
            System.out.println("Successfully wrote to the file.");
          } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }
    }



}
