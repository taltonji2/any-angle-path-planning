package ArtificialIntel.GUI;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;

import ArtificialIntel.Data.Cell;
import ArtificialIntel.Data.Grid;

public class PopUp {
    public static void Pop() {         //Needs access to singleton grid to pull cell data    
        JFrame jFrame = new JFrame();
        jFrame.setPreferredSize(new Dimension(250,200));
        JPanel jPanel = new JPanel();
        jFrame.add(jPanel);
        jPanel.setLayout(new BorderLayout());
        JTextPane jTextPane = new JTextPane();
        //Loop through cell data and set text to 4 vertext information returned from Cell and Grid.cells[][] 
        
        jTextPane.setText("t");
        jTextPane.setEditable(false);
        jPanel.add(jTextPane, BorderLayout.CENTER);
        jFrame.pack();
        jFrame.setVisible(true);
    }
}
