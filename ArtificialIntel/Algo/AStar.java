package ArtificialIntel.Algo;
import java.util.ArrayList;
import java.awt.Point;
import java.util.PriorityQueue;
/*
*       start       Node       target
*         |----------|---------|
*              g(Node)      h(Node)
*         |--------------------|
*                  f() 
*/

import ArtificialIntel.Data.Cell; 


public class AStar extends Cell
{
    Cell start, goal;
    AStar(int x, int y, int free)
    {
        super(x, y, free);
    }
    /* 
    private int g(Node node)
    {

    }*/

    private int h(Cell c)
    {
        return (int) (Math.sqrt(2) * Math.min(Math.abs(c.getX() - goal.getX()), Math.abs(c.getY() - goal.getY())) + Math.max(Math.abs(c.getX() - goal.getX()), Math.abs(c.getY() - goal.getY())) + Math.min(Math.abs(c.getX() - goal.getX()), Math.abs(c.getY() - goal.getY())));
    }

    /*private int f()
    {
        
    } */
}


