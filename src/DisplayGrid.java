<<<<<<< HEAD
=======

>>>>>>> cc6b5e9cdf01138176b2ef94cf19148063f06662

import java.awt.*;
import java.io.*;
import javax.swing.*;
import java.awt.image.BufferedImage;

public class DisplayGrid {

    private int sizeTile;
    private Grid grid;
    private Image image;
    private ImageIcon imageIcon;
    private JLabel jLabel;
    private static JFrame jFrame;
    
    public DisplayGrid (Grid grid)
    {
        this.sizeTile = 30;
        this.grid = grid;
        initializeGUI(this.grid);
        paint(grid);
    }

    
    protected Graphics initializeGUI(Grid grid){
        int imageSize = Math.max(grid.getWidth() * sizeTile, grid.getHeight()) * sizeTile;
        image = new BufferedImage( imageSize, imageSize, BufferedImage.TYPE_INT_ARGB );
        imageIcon = new ImageIcon( image );
        jFrame = new JFrame( "Artificial Intel" );        
        JLabel imageJLabel = new JLabel(new ImageIcon(image));
        
        JScrollPane scrollFrame = new JScrollPane(imageJLabel);
        jFrame.add(scrollFrame);
        jFrame.setPreferredSize(new Dimension ((grid.getWidth() * sizeTile)*2, (grid.getHeight() * sizeTile)*2)); 
        jFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

        JPanel main = new JPanel(new BorderLayout());

        main.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel name = new JLabel("A*");
        JLabel heuristicInfo = new JLabel("[PROGRAM Heuristic]");
        heuristicInfo.setAutoscrolls(true);

        name.setFont(new Font(name.getFont().getFamily(), Font.BOLD, 18));

        heuristicInfo.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 10));

        int eb = 20;
        scrollFrame.setBorder(BorderFactory.createEmptyBorder(eb, eb, eb, eb));

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.PAGE_AXIS));
        leftPanel.add(name);
        leftPanel.add(Box.createVerticalGlue());
        leftPanel.add(heuristicInfo);
        leftPanel.add(Box.createVerticalGlue());

        main.add(scrollFrame, BorderLayout.CENTER);
        main.add(leftPanel, BorderLayout.LINE_START);

        jFrame.add(main);
        jFrame.pack();

        return jFrame.getGraphics();
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
                if(cell.getIsCellBlocked()){
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
        graphics.setColor(Color.BLUE);
        graphics.drawLine(grid.getStart().getX() * sizeTile, grid.getStart().getY() * sizeTile, grid.getGoal().getX()
         * sizeTile, grid.getGoal().getY() * sizeTile);
        graphics.setColor(Color.GREEN);
        graphics.fillOval((grid.getStart().getX() * sizeTile) - (sizeTile/8), (grid.getStart().getY() * sizeTile
        )- (sizeTile/8), sizeTile/4, sizeTile/4);
        graphics.setColor(Color.RED);
        graphics.fillOval((grid.getGoal().getX() * sizeTile) - (sizeTile/8), (grid.getGoal().getY() * sizeTile) 
        - (sizeTile/8), sizeTile/4, sizeTile/4);      
        graphics.setColor( Color.blue );
        jFrame.pack();
        jFrame.setVisible(true);
    }

    public void showAStar(Graphics graphics)
    {
        graphics.setColor(Color.RED);
        for (Cell[] row : grid.cells)
        {
            for (Cell c : row)
            {
                if(c.isVisited())
                {
                    graphics.fillOval((grid.getGoal().getX() * sizeTile) - (sizeTile/8), 
                    (grid.getGoal().getY() * sizeTile)- (sizeTile/8), sizeTile/4, sizeTile/4);      
                }
            }
        }
    }
}
