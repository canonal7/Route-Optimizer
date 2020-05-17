package Node_Package;

public interface NodeInterface
{
    double calculateDistanceBTW( Node n );

    double getX();
    double getY();

    void setVisited( boolean visited );
    boolean isVisited();
}
