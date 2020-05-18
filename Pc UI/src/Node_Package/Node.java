package Node_Package;

public class Node implements NodeInterface
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

    /**
     * Calculates the distance between two nodes with respect to the Taxicab metric.
     * (It probably resembles the road structure in real life better as there are not many hypotenuses in roads.)
     * @param n Node to calculate the distance between
     * @return double.
     */
    public double calculateDistanceBTWTaxicab( Node n ){
        return Math.abs( x - n.getX() ) + Math.abs( y - n.getY() );
    }

    /**
     * Calculates the distance between two nodes.
     * @param n Node to calculate the distance between
     * @return double
     */
    public double calculateDistanceBTW( Node n )
    {
        return Math.sqrt( Math.pow( x - n.x, 2) + Math.pow( y - n.y , 2 ) );
    }

    /**
     * Returns the String representation of the Node.
     * @return String
     */
    public String toString()
    {
        return "X:" + x + " Y:" + y;
    }

    /**
     * Returns whether a node is equal to another or not.
     * @param node the node to check if this equals
     * @return boolean
     */
    public boolean equals( Node node )
    {
        return x == node.x && y == node.y;
    }

    /**
     * Sets a Node as visited or not visited.
     * @param visited state of isVisited
     */
    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    /**
     * Returns the information if the Node is visited or not.
     * @return boolean
     */
    public boolean isVisited()
    {
        return visited;
    }

    /**
     * Returns the x value of the Node.
     * @return double
     */
    public double getX()
    {
        return x;
    }

    /**
     * Returns the y value of the Node.
     * @return double
     */
    public double getY()
    {
        return y;
    }
}
