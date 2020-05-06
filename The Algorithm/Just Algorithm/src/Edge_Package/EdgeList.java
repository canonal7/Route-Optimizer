package Edge_Package;

import java.util.ArrayList;
import Node_Package.*;

public class EdgeList
{
    // properties

    // list of edges will be used to store routes depending on their start point
    // listOfEdges.get(0) will have all the edges that start from the first point in the nodeList in constructor
    // listOfEdges.get(1) will have all the edges that start from the second point in the nodeList
    ArrayList<ArrayList<Edge>> listOfEdges;
    NodeList nodes;

    // this is the final ordered route, and is only initialized when the final, ordered route is calculated
    ArrayList<Edge> finalRoute;

    // constructors
    // the constructor takes the nodeList that contains all the nodes
    // and creates all the possible edges from these nodes
    // and organizes them into the listOfEdges explained above
    public EdgeList( NodeList n )
    {
        nodes = n;
        listOfEdges = new ArrayList<ArrayList<Edge>>();
        for( int counter1 = 0; counter1 < n.size(); counter1++)
        {
            listOfEdges.add( new ArrayList<Edge>());
            for( int counter2 = 0; counter2 < n.size(); counter2++)
                    listOfEdges.get(counter1).add(new Edge(n.get(counter1), n.get(counter2)));

        }
        finalRoute = new ArrayList<>();
    }


    // methods

    // taking the start node of every edge in the final route and the end node of the last edge
    public NodeList extractNodeList()
    {
        if( finalRoute != null )
        {
            NodeList temp = new NodeList();

            // adds every edge's startNode to a node list and adds the last points endNode to the list to get all the nodes
            for (int n = 0; n < finalRoute.size() ; n++)
                temp.add( new Node( finalRoute.get(n).getStartNode().getX(), finalRoute.get(n).getStartNode().getY()));
            temp.add( new Node( finalRoute.get(finalRoute.size()-1).endNode.getX(), finalRoute.get(finalRoute.size()-1).endNode.getY()) );
            return temp;
        }
        else {
            System.out.println("The final route has not been calculated, and the node list has been returned empty");
            return null;
        }
    }

    // -- the variable that determines the start point of the array is startNode, if 0, will start from the first node, if 1 will start from the second node... --
    private void nearestNeighborWithStartPoint( int start )
    {
        resetParameters();
        Edge temp = null;
        int indexOfSmallestDistance = 0;
        int startNode = start;

        while( finalRoute.size() < listOfEdges.size() - 1  )
        {
            // selecting a valid first edge that starts with the endpoint of the last edge
            for( int i = 0; i < listOfEdges.get(startNode).size(); i++)
            {
                if( !listOfEdges.get(startNode).get(i).getEndNode().isVisited() && i != startNode )
                {
                    temp = listOfEdges.get(startNode).get(i);
                    indexOfSmallestDistance = i;
                    temp.startNode.setVisited( true );
                    break;
                }
            }

            for (int n = 0; n < listOfEdges.get(startNode).size(); n++)
            {
                if( temp == null)
                    System.out.println( "Temp is null, something went wrong");
                else if (listOfEdges.get(startNode).get(n).getDistance() <= temp.getDistance() && !listOfEdges.get(startNode).get(n).endNode.isVisited() )
                {
                    temp = listOfEdges.get(startNode).get(n);
                    indexOfSmallestDistance = n ;
                    temp.startNode.setVisited( true );
                }
            }
            finalRoute.add(temp);
            startNode = indexOfSmallestDistance;
        }
        // adding the edge from the last point to the start node to complete the route
        finalRoute.add( new Edge (finalRoute.get(finalRoute.size()-1).getEndNode(), finalRoute.get(0).getStartNode()) );
        System.out.println( finalRoute ); // you can delete this to go faster

    }

    public void nearestNeighbor()
    {
        nearestNeighborWithStartPoint(0);
    }

    public void nearestNeighbor( int start )
    {
        nearestNeighborWithStartPoint(start);
    }

    public int size()
    {
        return listOfEdges.size();
    }

    public void resetParameters()
    {
        finalRoute = new ArrayList<>();
        for( int n = 0; n < nodes.size(); n++)
        {
            nodes.get(n).setVisited(false);
        }
    }

    public double getDistanceOfFinalRoute()
    {
        double total = 0;
        if( !(finalRoute == null) )
        {
            for (Edge edge : finalRoute) {
                total += edge.getDistance();
            }
        }
        return total;
    }


}
