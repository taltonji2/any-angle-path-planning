package ArtificialIntel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.Stack;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import ArtificialIntel.Data.Cell;
import ArtificialIntel.Data.Grid;
import ArtificialIntel.Data.GridStorage;
import ArtificialIntel.Data.Vertex;
import ArtificialIntel.Algo.AStar;
import ArtificialIntel.Algo.Graph;
import ArtificialIntel.Algo.ThetaStar;


/**
 *
 */
public class Assignment1
{
    private static int sizeTile;
    private static int algo;
    private Image image;
    private ImageIcon imageIcon;
    private JLabel jLabel;
    private static JFrame jFrame;
    private static Grid g;

    Assignment1()
    {

        sizeTile = 50;
        //sizeTile = getInt( "How many pixels per square? [1 - 100]?" );
        //algo = getAlgo( "Which operation?   \'0\' A*     \'1\' Theta*" );
    }
    
    /**
     * @param args the command line arguments: Unused.
     */
    public static void main(String[] args) 
    {
        Assignment1 assignment1 = new Assignment1();
        Assignment1.g = assignment1.restoreGrid();
        assignment1.InitializeGUI(g);
        assignment1.paint(g);
        assignment1.view();
        Graph graph = new Graph();
        graph.Load(g);
        System.out.println(graph.BFS(g.getStart(), g.getGoal()));
        graph.BFS(g.getStart(), g.getGoal());
        //assignment1.doAStar();
        //if (graph.BFS(g.getStart(), g.getGoal()))
        {
            // assignment1.doAStar();
        }
    }
    private void view() { jFrame.setVisible( true ); }

    public void doAStar()
    {
        AStar as = new AStar();
        as.doAStar(g.getStart(), g.getGoal(), g);
    }
    public void doThetaStar()
    {
        ThetaStar as = new ThetaStar();
        as.doThetaStar(g.getStart(), g.getGoal(), g);
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
    private int getAlgo( String question )
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
        
        
        Stack<Vertex> path = new Stack<Vertex>();
        Vertex v; 
        v = new Vertex(g.getStart().x,g.getStart().y); 
        path.push(v);
        v = new Vertex(3,6); 
        path.push(v);
        v = new Vertex(3,5); 
        path.push(v);
        v = new Vertex(3,4);
        path.push(v);
        v = new Vertex(3,3); 
        path.push(v);
        v = new Vertex(3,2); 
        path.push(v);
        paintPath(graphics, g, path); 
        graphics.drawString(String.valueOf(grid.getStart().x + " " + grid.getStart().y), grid.getStart().x  * sizeTile + sizeTile/g.getWidth(), grid.getStart().y  * sizeTile);
        

        graphics.setColor(Color.BLUE);
        graphics.drawLine(grid.getStart().x * sizeTile, grid.getStart().y * sizeTile, grid.getGoal().x * sizeTile, grid.getGoal().y * sizeTile);
        graphics.setColor(Color.GREEN);
        graphics.fillOval((grid.getStart().x * sizeTile) - (sizeTile/8), (grid.getStart().y * sizeTile)- (sizeTile/8), sizeTile/4, sizeTile/4);
        graphics.setColor(Color.RED);
        graphics.fillOval((grid.getGoal().x * sizeTile) - (sizeTile/8), (grid.getGoal().y * sizeTile)- (sizeTile/8), sizeTile/4, sizeTile/4);       
        jFrame.pack();
        jFrame.setVisible(true);
    }

    private void paintPath(Graphics g2, Grid g,  Stack<Vertex> path)
    {
        Vertex v1 = new Vertex(g.getGoal().x, g.getGoal().y);
        g2.setColor(Color.RED);  
        while(!path.empty())
        {
            g2.drawString(String.valueOf(v1.x + " " + v1.y), v1.x  * sizeTile + sizeTile/g.getWidth(), v1.y  * sizeTile);
            Vertex v2 = path.pop();
            g2.drawLine(v1.x * sizeTile, v1.y * sizeTile, v2.x * sizeTile, v2.y * sizeTile); 
            g2.fillOval((v2.x * sizeTile) - (sizeTile/16), (v2.y * sizeTile)- (sizeTile/16), sizeTile/8, sizeTile/8);
            
            v1 = v2;
        }
    }
}
