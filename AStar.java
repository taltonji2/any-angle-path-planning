import java.util.ArrayList;
import java.awt.Point;
import java.util.PriorityQueue;
/*
*       start        s       target
*         |----------|---------|
*              g(s)      h(s)
*         |--------------------|
*                  f(s) 
*/ 


public class AStar extends Node
{
    private  Point start;
    private  Point target;
    private  PriorityQueue fringe; 
    private  ArrayList<Point> neighbors; 

    AStar()
    {
        this.neighbors = neighbors; 
    }
}


