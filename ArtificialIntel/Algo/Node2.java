package ArtificialIntel.Algo;

import java.util.ArrayList;

public class Node2 {
    public int x, y;
    public ArrayList<Node2> neighbors = new ArrayList<Node2>();

    public Node2(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public void addNeighbor(Node2 n)
    {
        neighbors.add(n);
    }    
    
}
