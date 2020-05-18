
package Edge_Package;

import Node_Package.NodeList;

/**
 * A class to help structure the EdgeList class
 * @author oğuz
 */
public interface EdgeListInterface
{
    void nearestNeighbor();

    double getDistanceOfFinalRoute();

    int size();

    NodeList extractNodeList();
}
