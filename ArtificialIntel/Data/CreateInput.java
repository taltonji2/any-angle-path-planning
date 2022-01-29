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
                WriteToFile(fileName);
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
        Vertex v = new Vertex(randIntX, randIntY); 
        return v;
    }

    private void WriteToFile(String filename)
    {
        try {
            FileWriter myWriter = new FileWriter(filename);
            Vertex v = RandomPoint();
            myWriter.write(v.x + " " + v.y + "\n");
            v = RandomPoint();
            myWriter.write(v.x + " " + v.y + "\n");
            myWriter.write(columns + " " + rows + "\n");
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
          } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }
    }
    
    

}
