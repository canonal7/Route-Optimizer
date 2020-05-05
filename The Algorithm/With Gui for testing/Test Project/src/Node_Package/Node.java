package Node_Package;
import java.awt.*;


public class Node
{
    // properties

    // properties for the algorithm
    int x;
    int y;
    boolean visited;

    // properties for the gui
    public final int RADIUS = 4;
    final Color START_NODE_COLOR = Color.CYAN;
    final Color END_NODE_COLOR = Color.RED;
    final Color DEFAULT_NODE_COLOR = Color.BLACK;
    static int totalCount = 0;
    int count;
    int order;
    boolean isFirst;


    // constructor
    public Node( int x, int y)
    {
        this.x = x;
        this.y = y;
        totalCount++;
        count = totalCount;
        visited = false;
    }

    // methods
    // method for the gui
    public void draw(Graphics g)
    {
        if( count == 1 )
            g.setColor( START_NODE_COLOR);
        else if( this.count == totalCount )
            g.setColor( END_NODE_COLOR );
        g.fillOval( x, y, RADIUS*2,RADIUS*2);
        g.setColor( DEFAULT_NODE_COLOR );
    }

    // method for the gui
    public void setOrder( int n)
    {
        order = n;
    }

    // method for the gui
    public void setIsFirst( boolean isFirst )
    {
        this.isFirst = isFirst;
    }

    // method for the gui
    public int getOrder()
    {
        return order;
    }


    public double calculateDistanceBTW( Node n )
    {
        return Math.sqrt( Math.pow( x - n.x, 2) + Math.pow( y - n.y , 2 ) );
    }

    // method for the gui
    public boolean contains(int mouseX, int mouseY )
    {
        if( Math.sqrt( Math.pow( mouseX - x,2) + Math.pow(mouseY-y,2)) < RADIUS + 10)
            return true;
        return false;
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

    // method for the gui
    public void resetCounter()
    {
        totalCount = 0;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }
}
