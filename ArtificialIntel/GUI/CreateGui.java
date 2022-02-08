package ArtificialIntel.GUI;

import java.awt.*;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import ArtificialIntel.Data.Grid;

public class CreateGui {
    public static void createAndShowGui(Grid g) {
        int rows =  Grid.Instance().getHeight();
        int cols = Grid.Instance().getWidth();
        int cellWidth = 100;
        ColorGrid mainPanel = new ColorGrid(rows, cols, cellWidth, g.cells);
  
        JFrame frame = new JFrame("Color Grid Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(mainPanel);       
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }
  
    /* public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
        public void run() {
            createAndShowGui(Grid.Instance());
        }
    });
    } */
}

