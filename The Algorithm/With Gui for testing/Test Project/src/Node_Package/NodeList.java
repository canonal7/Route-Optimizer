package Node_Package;
import java.util.ArrayList;

public class NodeList
{
    // properties
    ArrayList<Node> nodes;
    double distance;

    // constructors
    public NodeList()
    {
        nodes = new ArrayList<Node>();
        distance = 0;
    }

    // methods
    public void add( Node l)
    {
        nodes.add(l);
        l.setOrder( size() );
        if( size() == 1)
            l.setIsFirst(true);
    }

    public int size()
    {
        return nodes.size();
    }

    public Node get( int n)
    {
        return nodes.get(n);
    }

    // a method for the gui
    public Node findNodeAtxy(int x, int y)
    {
        for( int n = 0; n < nodes.size(); n++)
        {
            if( nodes.get(n).contains(x,y))
                return nodes.get(n);
        }
        return null;
    }

    // calculates the total distance between each node and adds them up
    public double calculateDistance()
    {
        for( int n = 0; n < size(); n++)
        {
            if( n+1 < size())
                distance += get(n).calculateDistanceBTW(get(n+1) );
        //        distance += Math.sqrt(Math.pow(get(n).x - get(n + 1).x, 2) + Math.pow(get(n).y - get(n + 1).y, 2));
        }
        return distance;

    }

    public String toString()
    {
        String total = "";
        for( int n = 0; n < nodes.size(); n++)
            total += nodes.get(n) + ", ";

        return total;
    }



}
