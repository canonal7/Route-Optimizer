package Edge_Package;

import Node_Package.NodeList;

public interface EdgeListInterface
{
    void nearestNeighbor();

    double getDistanceOfFinalRoute();

    int size();

    NodeList extractNodeList();
}
