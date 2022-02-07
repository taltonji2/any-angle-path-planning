package ArtificialIntel.Data;

import java.util.Objects;

public class Vertex{
    
    public int x;
    public int y;

    public Vertex(int x, int y){
        this.x=x;
        this.y=y;
    }
    Vertex(int x, int y, int bFree){
        this.x=x;
        this.y=y;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }

}


