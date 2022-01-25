import java.util.ArrayList;
import java.util.List;
import java.awt.Point;
import java.util.PriorityQueue;

public class Node implements Comparable<Node> 
{
    public Node parent = null;
    public List<Edge> neighbors;
    public Point h; 
    
    public Point f;
    public Point g;

    Node(Point h)
    {
        this.h = h;
        this.neighbors = new ArrayList<>();
    }

    //allow overide to see target and start points
    @Override
    public int compareTo(Node n)
    {
        int x = Double.compare(this.f.getX(), n.f.getX());
        int y = Double.compare(this.f.getY(), n.f.getY());
        
        return (x,y);
    }

    //shortest grid path
    public static class Edge 
    {
        Edge(int weight, Node node)
        {
              this.weight = weight;
              this.node = node;
        }

        public int weight;
        public Node node;
    }

    public void addBranch(int weight, Node node)
    {
        Edge newEdge = new Edge(weight, node);
        neighbors.add(newEdge);
    }

    //h()
    public double calculateHeuristic(Node target) 
    {
        //sequential measuring of distance between points to target.
        return this.h;
    }
}
