package ArtificialIntel.GUI;

import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.*;


   
    @SuppressWarnings("serial")
    public class ColorGrid extends JPanel {
       private MyColor[][] myColors;
       private JLabel[][] myLabels;
    
       public ColorGrid(int rows, int cols, int cellWidth) {
          myColors = new MyColor[rows][cols];
          myLabels = new JLabel[rows][cols];
    
          MyMouseListener myListener = new MyMouseListener(this);
          Dimension labelPrefSize = new Dimension(cellWidth, cellWidth);
          setLayout(new GridLayout(rows, cols));
          for (int row = 0; row < myLabels.length; row++) {
             for (int col = 0; col < myLabels[row].length; col++) {
                JLabel myLabel = new JLabel();
                myLabel = new JLabel();
                myLabel.setOpaque(true);
                MyColor myColor = MyColor.GREEN;
                myColors[row][col] = myColor;
                myLabel.setBackground(myColor.getColor());
                myLabel.addMouseListener(myListener);
                myLabel.setPreferredSize(labelPrefSize);
                add(myLabel);
                myLabels[row][col] = myLabel;
             }
          }
       }
    
       public MyColor[][] getMyColors() {
          return myColors;
       }
    
       public void labelPressed(JLabel label) {
          for (int row = 0; row < myLabels.length; row++) {
             for (int col = 0; col < myLabels[row].length; col++) {
                if (label == myLabels[row][col]) {
                   MyColor myColor = myColors[row][col].next();
                   myColors[row][col] = myColor;
                   myLabels[row][col].setBackground(myColor.getColor());
                }
             }
          }
       }
    }    

