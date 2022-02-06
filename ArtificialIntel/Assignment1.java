package ArtificialIntel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.Console;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.plaf.basic.BasicSliderUI.ComponentHandler;

import ArtificialIntel.Data.Cell;
import ArtificialIntel.Data.Grid;
import ArtificialIntel.Data.GridStorage;
import ArtificialIntel.Algo.AStar;
import ArtificialIntel.Data.Agent;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.*;


/**
 *
 */
public class Assignment1
{
    private static int sizeTile;
    private Image image;
    private ImageIcon imageIcon;
    private JLabel jLabel;
    private static JFrame jFrame;
    public Agent agent; //should be displayed using a circle on the grid
    public Grid g;

    Assignment1()
    {
        sizeTile = getInt( "How many pixels per square? [1 - 100]?" );
    }
    
    /**
     * @param args the command line arguments: Unused.
     */
    public static void main(String[] args) 
    {
        Assignment1 assignment1 = new Assignment1();
        Grid grid = assignment1.restoreGrid();
        assignment1.InitializeGUI(grid);
        assignment1.paint(grid);
        assignment1.view();
        assignment1.doAStar();
    }

    public void doAStar()
    {
        AStar as = new AStar();
        as.doAStar(g.start, g.goal, g);
    }
    
    protected void InitializeGUI(Grid grid){
        int imageSize = Math.max(grid.getWidth(), grid.getHeight()) * sizeTile;
        image = new BufferedImage( imageSize, imageSize, BufferedImage.TYPE_INT_ARGB );
        imageIcon = new ImageIcon( image );
        jFrame = new JFrame( "Artificial Intel" );
        
        JLabel picLabel = new JLabel(new ImageIcon(image));
        JPanel gridPanel = new JPanel();
        gridPanel.setLayout(new BorderLayout());
        gridPanel.add(picLabel, BorderLayout.CENTER);

        JScrollPane scrollFrame = new JScrollPane(gridPanel);
        gridPanel.setAutoscrolls(true);
        
        jFrame.add(scrollFrame);
        jFrame.setSize(1000,500); 
        jFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    }

    protected Grid restoreGrid(){
        Grid grid = null;
        try{
            String currentDirectory = System.getProperty("user.dir");
            String fileName = currentDirectory + "/resources/" + "grid0.txt";
            grid = GridStorage.restoreGrid(fileName);
        } catch(Exception ex){
            ex.printStackTrace();
        }
        return grid;
    }
    private int getInt( String question )
    {
        String intString = JOptionPane.showInputDialog( question );
        return Integer.parseInt( intString );
    }

   
    private void paint(Grid grid)
    {
        g = grid;
        Graphics graphics = image.getGraphics();
        // paint the cells
        graphics.setColor( Color.white );
        for ( int row = 0; row < grid.getHeight(); row++ )
        {
            for ( int col = 0; col < grid.getWidth(); col ++ )
            {
                Cell cell = grid.cells[col][row];
                if(cell.IsFree()){
                    graphics.setColor( Color.white );
                }
                else{
                    graphics.setColor( Color.gray );
                }
                graphics.fillRect( col * sizeTile, row * sizeTile, sizeTile, sizeTile );
            }
        }

        // this loop just draws lines around every cell
        for ( int row = 0; row < grid.getHeight(); row++ )
        {
            for ( int col = 0; col < grid.getWidth(); col++ )
            {

                graphics.setColor(Color.orange);
                int x = col * sizeTile;
                int y = row * sizeTile;
                int x2 = (col+1) * sizeTile;
                int y2 = (row+1) * sizeTile;

                graphics.drawLine(x, y, x2, y);
                graphics.drawLine(x2, y, x2, y2);
                graphics.drawLine(x2, y2, x, y2);
                graphics.drawLine(x, y2, x, y);
            }
        }
        
        graphics.setColor(Color.GREEN);
        graphics.fillOval((grid.start.x * sizeTile) - (sizeTile/8), (grid.start.y * sizeTile)- (sizeTile/8), sizeTile/4, sizeTile/4);

        graphics.setColor(Color.RED);
        graphics.fillOval((grid.goal.x * sizeTile) - (sizeTile/8), (grid.goal.y * sizeTile)- (sizeTile/8), sizeTile/4, sizeTile/4);
        jFrame.revalidate();
        jFrame.repaint();

    }
   
    private void view() { jFrame.setVisible( true ); }
}
