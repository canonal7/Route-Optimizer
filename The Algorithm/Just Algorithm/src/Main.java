import Node_Package.*;
import Edge_Package.*;
import java.io.File;
import java.util.Scanner;
import java.io.FileReader;

public class Main
{

    static long startTime;
    static long endTime;

    public static void main(String[] args) throws Exception
    {
        NodeList n = new NodeList();
        NodeList route = new NodeList();
        EdgeList edges = EdgeList.createFromText();
        n.add( new Node(0,0) );
        n.add( new Node(0,10) );
        n.add( new Node(0,20) );
        n.add( new Node(0,30) );

        route.readNodesFromFile();
        // distance between the nodes + the distance between the last node and the first node ( connecting the route ends )
        System.out.println( "In random order " + (double)(route.calculateDistance() + route.get(0).calculateDistanceBTW(route.get(route.size()-1))) );

        startTime = System.currentTimeMillis();
        edges.nearestNeighbor();
        edges.calculateTwoOpt();
        endTime = System.currentTimeMillis();

        System.out.println( "Time taken = " + ((double)(endTime - startTime)/1000));
        System.out.println( "Distance after algorithm " + edges.getDistanceOfFinalRoute() );
        // System.out.println( "Route " + route );
        route = edges.extractNodeList();
        // System.out.println( route );
        System.out.println( "Size " + route.size() );


    }

    /**
     * A helper method that may be used as an example
     * @param n Nodelist
     * @return Nodelist that has been processed
     */
    public static NodeList algorithmNN( NodeList n )
    {
        EdgeList temp = new EdgeList( n);
        temp.nearestNeighbor();
        return temp.extractNodeList();
    }






}
