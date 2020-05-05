package Edge_Package;

import Node_Package.Node;
public class Edge
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

    public Node getEndNode() {
        return endNode;
    }

    public double getDistance() {
        return distance;
    }

    public Node getStartNode() {
        return startNode;
    }

    public String toString()
    {
        return "(" + startNode + "," + endNode + ")";
    }

    public boolean isValid()
    {
        return !endNode.isVisited();
    }
}
