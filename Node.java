import java.util.ArrayList;
import java.util.List;
import java.awt.Point;

public class Node implements Comparable<Node> 
{
    public Node parent = null;

    public List<Edge> neighbors;

    public Point f;
    public Point g;
    
    public Point h; 

    Node(Point h)
    {
        this.h = h;
        this.neighbors = new ArrayList<>();
    }

    @Override
    public int compareTo(Node n)
    {
        int x = Double.compare(this.f.getX(), n.f.getX());
        int y = Double.compare(this.f.getY(), n.f.getY());
        
        return (x,y);
         
         
    }

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
        return this.h;
    }
}
