package Edge_Package;

import Node_Package.Node;

/**
 * A class to structure the edge class
 * @author oğuz
 */
public interface EdgeInterface
{
    Node getStartNode();
    Node getEndNode();
    double getDistance();
}
