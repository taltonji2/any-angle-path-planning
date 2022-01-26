package src;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
/**
 *
 */
public class Assignment1 
{
    private int sizeTile;
    private Image image;
    private ImageIcon imageIcon;
    private JLabel jLabel;
    private JFrame jFrame;
    
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
    }
    
    protected void InitializeGUI(Grid grid){
        int imageSize = Math.max(grid.getWidth(), grid.getHeight()) * sizeTile;
        image = new BufferedImage( imageSize, imageSize, BufferedImage.TYPE_INT_ARGB );
        imageIcon = new ImageIcon( image );
        jLabel = new JLabel( imageIcon );
        jFrame = new JFrame( "Artificial Intel" );
        jFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        Container container = jFrame.getContentPane();
        container.setLayout( new BorderLayout() );
        container.add( jLabel, BorderLayout.CENTER );
        jFrame.pack();
    }

    protected Grid restoreGrid(){
        Grid grid = null;
        try{
            String currentDirectory = System.getProperty("user.dir");
            String fileName = currentDirectory + "\\" + "grid.txt";
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

    }
   
    private void view() { jFrame.setVisible( true ); }
}
