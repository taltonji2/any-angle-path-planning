package ArtificialIntel.GUI;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Stack;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

import ArtificialIntel.Data.Cell;
import ArtificialIntel.Data.Grid;
import ArtificialIntel.Data.Vertex;

import java.awt.*;



   @SuppressWarnings("serial")
public class ColorGrid extends JPanel {
   private JLabel[][] myLabels;
   
   public ColorGrid(int rows, int cols, int cellWidth, Cell[][] cells) {
      myLabels = new JLabel[cols][rows];
   
      MyMouseListener myListener = new MyMouseListener(this);
      Dimension labelPrefSize = new Dimension(cellWidth, cellWidth);
      setLayout(new GridLayout(rows, cols));
      //pass in grid and iterate over cells
      for (int row = 0; row < cells.length; row++) 
      {             
         for (int col = 0; col < cells[row].length; col++) 
         {     
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
   }

   
   public void paintComponent(Graphics g, int cellWidth) {  
      g.setColor(Color.GREEN);
      g.fillOval((Grid.Instance().getStart().x * cellWidth) - (cellWidth/8), (Grid.Instance().getStart().y * cellWidth)- (cellWidth/8), cellWidth/4, cellWidth/4);
      g.setColor(Color.RED);
      g.fillOval((Grid.Instance().getGoal().x * cellWidth) - (cellWidth/8), (Grid.Instance().getGoal().y * cellWidth)- (cellWidth/8), cellWidth/4, cellWidth/4); // Draw on g here e.g.
   }

   public void labelPressed(JLabel label) {  //want reference to grid to pass and let Pop know about
      PopUp.Pop();
   }

   private void paintPath(Grid g,  Stack<Vertex> path, int cellWidth)
   {
      Vertex v1 = new Vertex(g.getGoal().x, g.getGoal().y);
      g2.setColor(Color.RED);  
      while(!path.isEmpty())
      {
         g2.drawString(String.valueOf(v1.x + " " + v1.y), v1.x  * cellWidth + cellWidth/g.getWidth(), v1.y  * cellWidth);
         Vertex v2 = path.pop();
         g2.drawLine(v1.x * cellWidth, v1.y * cellWidth, v2.x * cellWidth, v2.y * cellWidth); 
         g2.fillOval((v2.x * cellWidth) - (cellWidth/16), (v2.y * cellWidth)- (cellWidth/16), cellWidth/8, cellWidth/8);
         
         v1 = v2;
      }
   }
}    

