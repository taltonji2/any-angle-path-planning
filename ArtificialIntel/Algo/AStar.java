package ArtificialIntel.Algo;
import java.util.ArrayList;
import java.util.Comparator;
import java.awt.Point;
import java.util.PriorityQueue;
/*
*       start       Node       target
*         |----------|---------|
*              g(Node)      h(Node)
*         |--------------------|
*                  f() 
*/

import javax.lang.model.util.ElementScanner6;
import javax.swing.JFrame;

import ArtificialIntel.Data.Cell;
import ArtificialIntel.Data.Grid;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class AStar
{
    Cell start, goal;
    Grid g;
    PriorityQueue<Cell> fringe = new PriorityQueue<Cell>(1, (Cell c1, Cell c2) -> Double.compare(f(c1), f(c2)));
    ArrayList<Cell> closed = new ArrayList<Cell>();
    Image image;
    Graphics graphics;
    public boolean doAStar(Cell s, Cell g, Grid grid, int sizeTile, JFrame jFrame)
    {
        this.g = grid;
        start = s;
        goal = g;
        start.parent = start;
        s = start;
        System.out.println("Start: (" + start.getX() + ", " + start.getY() + ")");
        System.out.println("Goal: (" + goal.getX() + ", " + goal.getY() + ")");
        fringe.add(s);
        int imageSize = Math.max(grid.getWidth(), grid.getHeight()) * sizeTile;
        image = new BufferedImage( imageSize, imageSize, BufferedImage.TYPE_INT_ARGB );
        graphics = image.getGraphics();
        while (!fringe.isEmpty())
        {
            s = fringe.poll();
            System.out.println("Visiting (" + s.getX() + ", " + s.getY() + ")");
            graphics.setColor(Color.ORANGE);
            graphics.fillOval(s.getX() * sizeTile, s.getY() * sizeTile, 5, 5);
            if (s.equals(goal))
            {
                System.out.println("Found it!");
                return true;
            }
            System.out.println("Goal not found yet");
            closed.add(s);
            System.out.println(s.neighbors.size());
            for (Cell c : s.neighbors) 
            {
                if (c.IsFree())
                {
                    System.out.println("Visiting neighbor (" + c.getX() + ", " + c.getY() + ")");
                    graphics.fillOval(c.getX() * sizeTile, c.getY() * sizeTile, 5, 5);
                    jFrame.revalidate();
                    jFrame.repaint();
                    if (!fringe.contains(c))
                    {
                        c.setCost(Integer.MAX_VALUE);
                        c.parent = null;
                    }
                    updateVertex(s, c);
                }
            }
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
        graphics.fillOval(grid.start.x * sizeTile, grid.start.y * sizeTile, 5, 5);

        graphics.setColor(Color.RED);
        graphics.fillOval(grid.goal.x * sizeTile, grid.goal.y * sizeTile, 5, 5);
        jFrame.revalidate();
        jFrame.repaint();
        }
        System.out.println("Goal not reached :(");
        return false;
    }

    public void updateVertex(Cell s, Cell c)
    {
        if (g(s) + c(s, c) < g(c))
        {
            c.setCost(g(s) + c(s, c));
            c.parent = s;
            if (fringe.contains(c))
            {
                fringe.remove(c);
            }
            fringe.add(c);
        }

    }
    public double c(Cell c1, Cell c2)
    {
        if (c1.getX() < c2.getX() && c1.getY() == c2.getY()) //c1 is to the left of c2
        {
            return 1;   
        }
        if (c1.getX() > c2.getX() && c1.getY() == c2.getY()) //c1 is to the right of c2
        {
            return 1;
        }
        if (c1.getX() == c2.getX() && c1.getY() < c2.getY()) //c1 is under c2
        {
            return 1;
        }
        if (c1.getX() == c2.getX() && c1.getY() > c2.getY()) //c1 is above c2
        {
            return 1;
        }
        if (c1.getX() == c2.getX() && c1.getY() == c2.getY()) //c1 is c2
        {
            return 0;
        }
        return Math.sqrt(2);
    }

    //determines cost of cell relative to start
    private double g(Cell cell)
    {
        if (cell.equals(this.start))
        {
            this.start.setCost(0);
        }
        return cell.cost;
    }

    private int h(Cell c)
    {
        return (int) (Math.sqrt(2) * Math.min(Math.abs(c.getX() - goal.getX()), Math.abs(c.getY() - goal.getY())) + Math.max(Math.abs(c.getX() - goal.getX()), Math.abs(c.getY() - goal.getY())) + Math.min(Math.abs(c.getX() - goal.getX()), Math.abs(c.getY() - goal.getY())));
    }

    private double f(Cell c)
    {
        return h(start) + g(start);
        
    }

}
