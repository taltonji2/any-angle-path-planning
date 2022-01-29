package ArtificialIntel.Data;

import java.awt.Color;


public class Agent extends Vertex{
    protected Color color = Color.RED;
    public int gridHeight = 0;
    public int gridWidth = 0;
    public Agent(int x, int y)
    {
        super(x, y);
    }

    void north(Vertex v){
        //Vertex v = vertex the agent is trying to move to
        int y = v.y - 1;
        if(y<0)
            return;
        else
        {
            this.y = y;
        }
    }
    void east(Vertex v){
        int x = v.x + 1;
        if(x > this.gridWidth)
            return;
        else
        {
            this.x = x;
        }
    }
    void south(Vertex v){
        int y = v.y + 1;
        if(y > gridHeight - 1)
            return;
        this.y = y;
    }
    void west(Vertex v){
        int x = v.x - 1;
        if(x < 0)
            return;
        this.x = x;
    }
    void northEast(Vertex v){
        int y = v.y - 1;
        int x = v.x + 1;
        if(y < 0 || x > gridWidth - 1)
            return;
        this.y = y;
        this.x = x;
    }
    void northWest(Vertex v){
        int y = v.y - 1;
        int x = v.x - 1;
        if(y < 0 || x < 0)
            return;
        this.y = y;
        this.x = x;
    }
    void southEast(Vertex v){
        int y = v.y + 1;
        int x = v.x + 1;
        if(y > gridHeight - 1 || x > gridWidth - 1)
            return;
        this.y = y;
        this.x = x;
    }
    void southWest(Vertex v){
        int y = v.y + 1;
        int x = v.x - 1;
        if(y > gridHeight - 1 || x < 0)
            return;
        this.y = y;
        this.x = x;
    }
}
