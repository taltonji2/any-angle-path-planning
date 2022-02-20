
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.image.BufferedImage;

public class DisplayGrid {

    private int sizeTile;
    private Grid grid;
    private Image image;
    private ImageIcon imageIcon;
    private JLabel jLabel;
    private static JFrame jFrame;
    
    public DisplayGrid (Grid grid, String algoNum, ArrayList<Cell> closedList)
    {
        this.sizeTile = 30;
        this.grid = grid;
        initializeGUI(this.grid, algoNum, closedList);
        inizializeHeuristics(closedList);
        paint(grid);
    }

    protected Graphics initializeGUI(Grid grid, String algoNum, ArrayList<Cell> closedList){
        int imageSize = Math.max((grid.getWidth() + 4) * sizeTile, (grid.getHeight() + 4) * sizeTile);
        image = new BufferedImage( imageSize, imageSize, BufferedImage.TYPE_INT_ARGB );
        imageIcon = new ImageIcon( image );
        jFrame = new JFrame( "Any Angle Path Planning" );        
        JLabel imageJLabel = new JLabel(new ImageIcon(image));
        
        JScrollPane scrollFrame = new JScrollPane();
        scrollFrame.setViewportView(imageJLabel);
        jFrame.add(scrollFrame);
        jFrame.setPreferredSize(new Dimension ((grid.getWidth() + 15) * sizeTile, (grid.getHeight() + 10) * sizeTile)); 
        jFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

        JPanel main = new JPanel(new BorderLayout());
        main.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel name;
        if(algoNum.equals("1"))
            name = new JLabel("A*"); 
        else 
            name = new JLabel("Theta*");
        JLabel heuristicInfo = new JLabel();       
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
    
    public void inizializeHeuristics(ArrayList<Cell> closedList)
    {
        final JPanel mainPanel2 = new JPanel();
        JPanel firstLine = new JPanel();
        JPanel secLine = new JPanel();
    
        mainPanel2.setLayout(new BoxLayout(mainPanel2, BoxLayout.Y_AXIS));
        mainPanel2.setBorder(new EmptyBorder(new Insets(5, 5, 5, 5)));
        mainPanel2.add(Box.createVerticalGlue());
    
        firstLine.setLayout(new BoxLayout(firstLine, BoxLayout.X_AXIS));
        firstLine.setBorder(new EmptyBorder(new Insets(5, 5, 5, 5)));
        firstLine.add(Box.createVerticalGlue());
    
        secLine.setLayout(new BoxLayout(secLine, BoxLayout.X_AXIS));
        secLine.setBorder(new EmptyBorder(new Insets(5, 5, 5, 5)));
        secLine.add(Box.createVerticalGlue());
    
        JTextArea textArea = new JTextArea(addHeuristicInfo(closedList), 10, 40);
        textArea.setLineWrap(true);
        JScrollPane scrollPane = new JScrollPane(textArea); 
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        textArea.setEditable(false);
    
        JLabel label1 = new JLabel("Processing results:");
        
        firstLine.add(Box.createRigidArea(new Dimension(5,0)));
        firstLine.add(label1);
        firstLine.add(Box.createRigidArea(new Dimension(5,0)));
    
        secLine.add(textArea);
        secLine.add(Box.createRigidArea(new Dimension(5,0)));
    
        mainPanel2.add(firstLine);
        mainPanel2.add(Box.createRigidArea(new Dimension(0, 30)));
        mainPanel2.add(secLine);
        mainPanel2.add(Box.createRigidArea(new Dimension(0, 20)));
    
        JFrame frame = new JFrame("Test results");
        frame.setSize(400, 300);
        frame.setLocation(50,50);
        frame.setVisible( true );
        frame.add(mainPanel2);
        frame.pack();
    }
    void paintPath(ArrayList<Cell> closedList)
    {
        Graphics graphics = image.getGraphics();
        graphics.setColor(Color.RED);
        for(Cell c : closedList)
        {
            if(c.isVisited())
                graphics.fillOval(((c.getX()) * sizeTile) - (sizeTile/8), 
                ((c.getY()) * sizeTile) - (sizeTile/8), sizeTile/4, sizeTile/4);      
        }
            
    }

    private String addHeuristicInfo(ArrayList<Cell> closedList)
    {
        String Heur = "";
        for(Cell c : closedList)
        {
            double g = changeDecimal(c.getGCost(), 4);
            double h = changeDecimal(c.getHCost(), 4);
            double f = changeDecimal(c.getFCost(), 4);
            if(c.isVisited())
                
                Heur += "("+c.getX()+","+c.getY()+")     g(): "+g+"     h(): "+h+"    f(): "+f+ " \n"; 
        }
        return Heur;
    }
    static double changeDecimal(double value, int decimalpoint)
    {
        value = value * Math.pow(10, decimalpoint);
        value = Math.floor(value);
        value = value / Math.pow(10, decimalpoint);
        return value;
    }
    private void paint(Grid grid)
    {
        Graphics graphics = image.getGraphics();
        // paint the cells
        graphics.setColor( Color.white );
        for ( int row = 0; row < grid.getHeight()-1; row++ )
        {
            for ( int col = 0; col < grid.getWidth()-1; col ++ )
            {
                int displayRow = row+1;
                int displayCol = col+1;
                Cell cell = grid.cells[col][row];
                if(cell.getIsCellFree()){
                    graphics.setColor( Color.white );
                }
                else{
                    graphics.setColor( Color.gray );
                }
                graphics.fillRect( displayCol * sizeTile, displayRow * sizeTile, sizeTile, sizeTile );
              
            }
        }

        // this loop just draws lines around every cell
        for ( int row = 0; row < grid.getHeight()-1; row++ )
        {
            for ( int col = 0; col < grid.getWidth()-1; col++ )
            {
                int displayRow = row+1;
                int displayCol = col+1;

                graphics.setColor(Color.orange);
                int x = displayCol * sizeTile;
                int y = displayRow * sizeTile;
                int x2 = (displayCol+1) * sizeTile;
                int y2 = (displayRow+1) * sizeTile;
        
                graphics.drawLine(x, y, x2, y);
                graphics.drawLine(x2, y, x2, y2);
                graphics.drawLine(x2, y2, x, y2);
                graphics.drawLine(x, y2, x, y);
            }
        }

        for (int row = 0; row < grid.getHeight(); row++)
        {
            int displayRow = row + 1;
            int displayCol = 1; 
            graphics.drawString(Integer.toString(displayRow), (displayCol * sizeTile - sizeTile) , (displayRow) * sizeTile);
        }
        for(int col = 0; col < grid.getWidth(); col++)
        {
            int displayRow = 1;
            int displayCol = col+1;    
            graphics.drawString(Integer.toString(displayCol), displayCol * sizeTile , (displayRow) * sizeTile);
        }

        graphics.setColor(Color.BLUE);
        graphics.drawLine((grid.getStart().getX()) * sizeTile, (grid.getStart().getY())
        * sizeTile, (grid.getGoal().getX())
         * sizeTile, (grid.getGoal().getY()) * sizeTile);
        graphics.setColor(Color.GREEN);
        graphics.fillOval(((grid.getStart().getX()) * sizeTile) - (sizeTile/8), ((grid.getStart().getY()) * sizeTile
        )- (sizeTile/8), sizeTile/4, sizeTile/4);
        graphics.setColor(Color.RED);
        graphics.fillOval(((grid.getGoal().getX()) * sizeTile) - (sizeTile/8), ((grid.getGoal().getY()) * sizeTile) 
        - (sizeTile/8), sizeTile/4, sizeTile/4);      
        graphics.setColor( Color.blue );
        jFrame.pack();
        jFrame.setVisible(true);
    }
}
