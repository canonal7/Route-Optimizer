package Node_Package;
import Edge_Package.Edge;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class NodeList
{
    // properties
    ArrayList<Node> nodes;
    double distance;

    // constructors

    /**
     * Constructor without any initialization. An empty ArrayList is created and distance is set to 0.
     */
    public NodeList()
    {
        nodes = new ArrayList<Node>();
        distance = 0;
    }

    /**
     * Constructor with an initialization. An ArrayList is passed as a parameter initializing the list of nodes
     * and the distance is set to the distance of the parameter list.
     * @param ndList nodeList to initialize
     */
    public NodeList( ArrayList<Node> ndList ){
        nodes = ndList;
        distance = calculateDistance();
    }

    // methods
    /**
     * Adds a Node to the NodeList.
     * @param node the node to add to the nodelist
     */
    public void add( Node node)
    {
        nodes.add(node);
    }

    /**
     * Returns the number of Nodes in the nodeList.
     * @return int
     */
    public int size()
    {
        return nodes.size();
    }

    /**
     * Returns the Node at a specific index from the NodeList.
     * @param index index of the node to get
     * @return Node
     */
    public Node get( int index)
    {
        return nodes.get(index);
    }

    /**
     * Calculates the total distance between each node and adds them up.
     * @return double
     */
    public double calculateDistance()
    {
        for( int n = 0; n < size(); n++)
        {
            if( n+1 < size())
                distance += get(n).calculateDistanceBTW(get(n+1) );
        }
        return distance;

    }

    /**
     * Returns the String representation of the NodeList.
     * @return String
     */
    public String toString()
    {
        String total = "";
        for( int n = 0; n < nodes.size(); n++)
            total += nodes.get(n) + ", ";

        return total;
    }
    /**
     * This is a PRIVATE METHOD and should only be used in the 2-opt calculating method.
     * Swaps the place of two specified indices at the given NodeList.
     * @param index1 first index to be swapped with the second one
     * @param index2 second index to be swapped with the first one
     */
    private void swapPlaces(  int index1, int index2 ){
        NodeList newNdList = new NodeList();
        //take the ndList up to index1 and add it to the newTour.
        for( int i = 0; i < index1; i++ ){
            newNdList.add( get( i ) );
        }

        //change the order of the nodes btw. index1 and index2 and add them to newTour.
        int temp = 0;//used to count back from index2 and place them in newNdList.
        for( int k = index1; k <= index2; k++ ){
            newNdList.add( get( index2 - temp ) );
            temp++;
        }

        //add the elements of the ndList from index2 to end to newTour.
        for( int x = index2 + 1; x < size(); x++ ){
            newNdList.add( get( x ) );
        }

        //copies the reference point of the array list that has been ordered
        nodes = newNdList.nodes;
    }

    /**
     * Finds the crossings and uses the swap method to fix them according to the 2-opt algorithm idea.
     */
    public void calculateTwoOpt(){
        int nOfSwaps = -27;//initializes number of swaps to a non-zero integer so the while loop starts.

        while( nOfSwaps != 0 ){//quits when there is no more swaps to be made.

            nOfSwaps = 0;//number of swaps is reset to zero so while loop will break if there is no swaps.

            for (int i = 1; i < size() - 2; i++){//iterates the list.

                for( int k = i + 1; k < size() - 1; k++){//iterates the list relative to the parent for loop.

                    //checks if the Edge(i, i-1) + Edge(k+1, k) is larger that Edge(i, k+1) + Edge(i-1, k).
                    if(get(i).calculateDistanceBTW(get(i-1))+get(k+1).calculateDistanceBTW(get(k))
                            > (get(i)).calculateDistanceBTW(get(k+1))+(get(i-1)).calculateDistanceBTW(get(k))){
                        //Switches the places of i and k and saves it to the another NodeList.
                        swapPlaces( i, k );
                        //Sets the main NodeList as the swapped one.
                        //increments the number of swaps.
                        nOfSwaps++;
                    }
                }
            }
        }
    }

    /**
     * Method which reads a set of xy values from a txt file and saves them as nodes in a NodeList.
     */
    public void readNodesFromFile()
    {
        int startIndex;
        int endIndex;
        String s;
        String s1;
        NodeList temp = new NodeList();

        File file = new File( "C:\\Users\\halit\\Desktop\\Pc UI\\src\\Txt_Files\\Unordered_Nodes.txt" );
        FileReader fileReader = null;
        try
        {
            fileReader = new FileReader( file );
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        Scanner scan = new Scanner( fileReader);

        while( scan.hasNext() )
        {
            s = scan.nextLine();
            if( s.equals( "" ))
                break;
            temp.add( new Node( Double.parseDouble(s.substring(0, s.indexOf(" "))) , Double.parseDouble(s.substring( s.indexOf( " " ) + 1)) ));
        }
        nodes = temp.nodes;
    }

    /**
     * returns an arrayList of the edges containing the route
     * @return arrayList of edge containing the route
     */
    public ArrayList<Edge> getEdgeArrayList()
    {
        ArrayList<Edge> edges = new ArrayList<>();
        for( int n = 0; n < size() - 1; n++)
            edges.add( new Edge( get(n), get(n+1) ));
        return edges;
    }

    /**
     * Extracts the nodes to a file line by line, in the format of "y coordinate" + " " + "x coordinate"
     * @param pathway the pathway to the file
     */
    public void extractToFile( String pathway )
    {
        File orderedFile = new File( pathway );
        try {
            orderedFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            FileWriter myWriter = new FileWriter( orderedFile );
            for( int n = 0; n < size(); n++)
            {
                System.out.println("Wrote");
                myWriter.write( nodes.get(n).getX() + " " + nodes.get(n).getY() + "\n" );
            }
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }





}
