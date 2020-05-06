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
/*        n.add( new Node(0,0) );
        n.add( new Node(0,10) );
        n.add( new Node(0,20) );
        n.add( new Node(0,30) );*/

        route = readNodesFromFile();
        // distance between the nodes + the distance between the last node and the first node ( connecting the route ends )
        System.out.println( "In random order " + (double)(route.calculateDistance() + route.get(0).calculateDistanceBTW(route.get(route.size()-1))) );

        startTime = System.currentTimeMillis();
        route = algorithmNN( route );
        endTime = System.currentTimeMillis();

        System.out.println( "Time taken = " + ((double)(endTime - startTime)/1000));
        System.out.println( "Distance after algorithm " + route.calculateDistance() );
        System.out.println( "Route " + route );
        System.out.println( "Size " + route.size() );
    }

    public static NodeList algorithmNN( NodeList n )
    {
        EdgeList temp = new EdgeList( n);
        temp.nearestNeighbor();
        return temp.extractNodeList();
    }




    public static NodeList readNodesFromFile() throws Exception
    {
        int startIndex;
        int endIndex;
        String s;
        String s1;
        NodeList temp = new NodeList();
        File file = new File( "Test_Files/test3.txt" );
        FileReader fileReader = new FileReader( file );
        Scanner scan = new Scanner( fileReader);

        while( scan.hasNext() )
        {
            s = scan.nextLine();
            startIndex = s.indexOf(' ') + 1;
            s1 = s.substring( startIndex + 1);
            endIndex = s1.indexOf(' ') + startIndex + 1;
            temp.add( new Node( Double.parseDouble(s.substring(startIndex, endIndex)) , Double.parseDouble(s.substring( endIndex + 1)) ));
        }
        return temp;
    }


}
