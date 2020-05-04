package Edge_Package;

import java.lang.reflect.Array;
import java.util.ArrayList;
import Node_Package.*;

public class EdgeList
{
    // properties

    // list of edges will be used to store routes depending on their start point
    // listOfEdges.get(0) will have all the edges that start from the first point in the nodeList in constructor
    // listOfEdges.get(1) will have all the edges that start from the second point in the nodeList
    ArrayList<ArrayList<Edge>> listOfEdges;

    // this is the final ordered route, and is only initialized when the final, ordered route is calculated
    ArrayList<Edge> finalRoute;

    // constructors
    // the constructor takes the nodeList that contains all the nodes
    // and creates all the possible edges from these nodes
    // and organizes them into the listOfEdges explained above
    // !! -- the start point is always the first node in the NodeList n, so put the beginning of the route at the
    // first position of the nodeList before this constructor -- !!
    public EdgeList( NodeList n )
    {
        listOfEdges = new ArrayList<ArrayList<Edge>>();
        for( int counter1 = 0; counter1 < n.size(); counter1++)
        {
            listOfEdges.add( new ArrayList<Edge>());
            for( int counter2 = 0; counter2 < n.size(); counter2++)
                    listOfEdges.get(counter1).add(new Edge(n.get(counter1), n.get(counter2)));

        }

        System.out.println( listOfEdges );
        finalRoute = new ArrayList<>();
    }


    // methods

    // this method is only for the gui here meant for testing, creates a node list in the same order as the edge list
    // taking the start node of every edge in the final route and the end node of the last edge
    public NodeList extractNodeList()
    {
        if( finalRoute != null )
        {
            // following 3 lines of code is just for the gui, makes sure that the colors of the first and last node are right
            NodeList temp = new NodeList();
            Node temp1 = new Node(0,0);
            temp1.resetCounter();

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
    public void nearestNeighborWithStartPoint( int start )
    {
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
        System.out.println( finalRoute );

    }

    public void nearestNeighbor()
    {
        nearestNeighborWithStartPoint(0);
    }

    public void nearestNeighbor( int start )
    {
        nearestNeighborWithStartPoint(start);
    }


}
