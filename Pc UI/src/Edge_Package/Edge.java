package Edge_Package;

import Node_Package.Node;
public class Edge implements EdgeInterface
{
    // properties
    Node startNode;
    Node endNode;
    double distance;

    // constructors
    public Edge(Node startNode, Node endNode )
    {
        this.startNode = startNode;
        this.endNode = endNode;

        distance = startNode.calculateDistanceBTW( endNode );

    }


    // methods

    /**
     * Returns the Node at the end of the Edge.
     * @return Node
     */
    public Node getEndNode() {
        return endNode;
    }

    /**
     * Returns the Node at the beginning of the Edge.
     * @return Node
     */
    public Node getStartNode() {
        return startNode;
    }

    /**
     * Returns the length of the Edge. (The distance btw. start Node and end Node of the Edge)
     * @return double
     */
    public double getDistance() {
        return distance;
    }

    /**
     * Returns the String representation of the Edge.
     * @return String
     */
    public String toString()
    {
        return "(" + startNode + "," + endNode + ")";
    }


}
