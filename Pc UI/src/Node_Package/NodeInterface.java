package Node_Package;

/**
 * A class that helps structure the Node class
 * @author oğuz
 */
public interface NodeInterface
{
    double calculateDistanceBTW( Node n );

    double getX();
    double getY();

    void setVisited( boolean visited );
    boolean isVisited();
}
