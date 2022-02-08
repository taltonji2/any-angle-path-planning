package ArtificialIntel.GUI;

import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.*;
import javax.swing.border.Border;

import ArtificialIntel.Data.Cell;
import ArtificialIntel.Data.Grid;

import java.awt.*;


   
    @SuppressWarnings("serial")
    public class ColorGrid extends JPanel {
       private JLabel[][] myLabels;
    
       public ColorGrid(int rows, int cols, int cellWidth, Cell[][] cells) {
          myLabels = new JLabel[cols][rows];
    
          MyMouseListener myListener = new MyMouseListener(this);
          Dimension labelPrefSize = new Dimension(cellWidth, cellWidth);
          setLayout(new GridLayout(rows, cols));
          for (int row = 0; row < cells.length; row++) {             //pass in grid and iterate over cells
             for (int col = 0; col < cells[row].length; col++) {     
                
               JLabel myLabel = new JLabel();
                myLabel = new JLabel();
                myLabel.setOpaque(true);
                Border blackline = BorderFactory.createLineBorder(Color.black);
                myLabel.setBorder(blackline);
                if(cells[row][col].IsFree())
                  myLabel.setBackground(Color.WHITE); 
                  else
                  myLabel.setBackground(Color.GRAY);                  //if cell is blocked set color to grey
                myLabel.addMouseListener(myListener);
                myLabel.setPreferredSize(labelPrefSize);
                add(myLabel);
                myLabels[row][col] = myLabel;
             }
          }
          Graphics g = this.getGraphics();
          //paintComponent(g, cellWidth);
       }
       public void paintComponent(Graphics g, int cellWidth) {
         g.setColor(Color.GREEN);
         g.fillOval((Grid.Instance().getStart().x * cellWidth) - (cellWidth/8), (Grid.Instance().getStart().y * cellWidth)- (cellWidth/8), cellWidth/4, cellWidth/4);
         g.setColor(Color.RED);
         g.fillOval((Grid.Instance().getGoal().x * cellWidth) - (cellWidth/8), (Grid.Instance().getGoal().y * cellWidth)- (cellWidth/8), cellWidth/4, cellWidth/4); // Draw on g here e.g.
     }
    
       public void labelPressed(JLabel label) {
            PopUp.Pop();
       }

    }    

