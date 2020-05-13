package NodePack;

public class Node
{
    // properties
    private double x;
    private double y;
    private boolean visited;


    // constructor
    public Node( double x, double y){
        this.x = x;
        this.y = y;
        visited = false;
    }

    // methods

    /**
     * Calculates the distance between two nodes with respect to the Taxicab metric.
     * (It probably resembles the road structure in real life better as there are not many hypotenuses in roads.)
     * @param n
     * @return double.
     */
    public double calculateDistanceBTWTaxicab( Node n ){
        return Math.abs( x - n.getX() ) + Math.abs( y - n.getY() );
    }

    /**
     * Calculates the distance between two nodes.
     * @param n
     * @return double
     */
    public double calculateDistanceBTW( Node n ){
        return Math.sqrt( Math.pow( x - n.getX(), 2) + Math.pow( y - n.getY() , 2 ) );
    }

    /**
     * Returns the String representation of the Node.
     * @return String
     */
    public String toString(){
        return "X:" + x + " Y:" + y;
    }

    /**
     * Returns whether a node is equal to another or not.
     * @param node
     * @return boolean
     */
    public boolean equals( Node node ){
        return x == node.getX() && y == node.getY();
    }

    /**
     * Sets a Node as visited or not visited.
     * @param visited
     */
    public void setVisited( boolean visited ){
        this.visited = visited;
    }

    /**
     * Returns the information if the Node is visited or not.
     * @return boolean
     */
    public boolean isVisited(){
        return visited;
    }

    /**
     * Returns the x value of the Node.
     * @return double
     */
    public double getX(){
        return x;
    }

    /**
     * Returns the y value of the Node.
     * @return double
     */
    public double getY(){
        return y;
    }
}

