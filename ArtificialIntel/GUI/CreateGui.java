package ArtificialIntel.GUI;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class CreateGui {
    private static void createAndShowGui() {
        int rows = 20;
        int cols = 40;
        int cellWidth = 20;
        ColorGrid mainPanel = new ColorGrid(rows, cols, cellWidth);
  
        JFrame frame = new JFrame("Color Grid Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(mainPanel);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }
  
    public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
        public void run() {
            createAndShowGui();
        }
    });
    }
}

