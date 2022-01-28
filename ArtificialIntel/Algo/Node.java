package ArtificialIntel.Algo;
import java.util.ArrayList;
import java.util.List;
import java.awt.Point;

public class Node implements Comparable<Node> 
{
    public Node parent = null;
    public Point point;
    public List<Edge> neighbors;

    //heuristic value
    public Double h;
    
    //f-value ƒ (s) = g(s) + h(s) for every vertex s, which is an estimate of
    //the distance from the start vertex via vertex s to the goal vertex.
    public Double f = Double.MAX_VALUE;  
                                  
    //sets the g-value of every vertex to infinity and the parent of every vertex to NULL when it
    //is encountered for the first time [Lines 1-1]. It sets the g-value of the start vertex to zero and
    //the parent of the start vertex to itself 
    public Double g = Double.MAX_VALUE; 

    Node()
    {
        
    }

    Node(Double h)
    {
        this.h = h;
        this.neighbors = new ArrayList<>();
    }

    @Override
    public int compareTo(Node n)
    {
        return Double.compare(this.f, n.f);
    }

    public static class Edge 
    {
        private Point point;
        public Node node;
        Edge(Point point, Node node)
        {
              this.point = point;
              this.node = node;
        }
        public Point getPoint()
        {
            return point;
        }
    }

    public void addBranch(int weight, Node node)
    {
        Edge newEdge = new Edge(point, node);
        neighbors.add(newEdge);
    }

    public double calculateHeuristic(Node target) 
    {
        return this.h;
    }
}
