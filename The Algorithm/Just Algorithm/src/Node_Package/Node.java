package Node_Package;
import java.awt.*;


public class Node
{
    // properties

    // properties for the algorithm
    double x;
    double y;
    boolean visited;


    // constructor
    public Node( double x, double y)
    {
        this.x = x;
        this.y = y;
        visited = false;
    }

    // methods

    public double calculateDistanceBTW( Node n )
    {
        return Math.sqrt( Math.pow( x - n.x, 2) + Math.pow( y - n.y , 2 ) );
    }


    public String toString()
    {
        return "X:" + x + " Y:" + y;
    }

    public boolean equals( Node n )
    {
        return x == n.x && y == n.y;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public boolean isVisited()
    {
        return visited;
    }

    public double getX()
    {
        return x;
    }

    public double getY()
    {
        return y;
    }
}
