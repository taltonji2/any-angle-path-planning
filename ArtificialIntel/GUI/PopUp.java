package ArtificialIntel.GUI;

import java.awt.*;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import ArtificialIntel.Data.Cell;
import ArtificialIntel.Data.Grid;

public class PopUp {
    private static final DecimalFormat df = new DecimalFormat("0.0000");
    public static void Pop(ArrayList<Cell> path) {         //Needs access to singleton grid to pull cell data    
        JFrame jFrame = new JFrame();
        jFrame.setPreferredSize(new Dimension(250,700));
        JPanel jPanel = new JPanel();
        jFrame.add(jPanel);
        jPanel.setLayout(new BorderLayout());
        JTextPane jTextPane = new JTextPane();
        //Loop through cell data and set text to 4 vertext information returned from Cell and Grid.cells[][]
        String pathInfo = "x       y             g()             h()             f()\n"; 
        for(int i = 0; i < path.size(); i++)
        {
            pathInfo += path.get(i).x + "    " + path.get(i).y + "     " + df.format(path.get(i).g) + "     " + df.format(path.get(i).h)  + "     " + df.format(path.get(i).h) + "\n";
        }
        jTextPane.setText(pathInfo);
        jTextPane.setEditable(false);
        jPanel.add(jTextPane, BorderLayout.CENTER);
        jFrame.pack();
        jFrame.setVisible(true);
    }
}
