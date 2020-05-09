package NodePack;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class NodeList
{
    // properties
    private ArrayList<Node> nodes;
    private double distance;

    // constructors

    /**
     * Constructor with an initialization. An ArrayList is passed as a parameter initializing the list of nodes
     * and the distance is set to the distance of the parameter list.
     * @param ndList
     */
    public NodeList( ArrayList<Node> ndList ){
        nodes = ndList;
        distance = this.calculateDistance();
    }

    /**
     * Constructor without any initialization. An empty ArrayList is created and distance is set to 0.
     */
    public NodeList(){
        nodes = new ArrayList<Node>();
        distance = 0;
    }

    // methods
    /**
     * Adds a Node to the NodeList.
     * @param node
     */
    public void add( Node node ){
        nodes.add( node );
    }

    /**
     * Returns the number of Nodes in the nodeList.
     * @return int
     */
    public int size(){
        return nodes.size();
    }

    /**
     * Returns the Node at a specific index from the NodeList.
     * @param index
     * @return Node
     */
    public Node get( int index ) {
        return nodes.get( index );
    }

    /**
     * Calculates the total distance between each node and adds them up.
     * @return double
     */
    public double calculateDistance(){
        distance = 0;
        for( int n = 0; n < size(); n++)
        {
            if( n+1 < size())
                distance += get(n).calculateDistanceBTW(get(n+1) );
            //        distance += Math.sqrt(Math.pow(get(n).x - get(n + 1).x, 2) + Math.pow(get(n).y - get(n + 1).y, 2));
        }
        return distance;
    }

    /**
     * Returns the String representation of the NodeList.
     * @return String
     */
    public String toString(){
        String total = "";
        for( int n = 0; n < nodes.size(); n++) {
            total += nodes.get(n) + ", ";
        }
        return total;
    }

    /**
     * This is a PRIVATE METHOD and should only be used in the 2-opt calculating method.
     * Swaps the place of two specified indices at the given NodeList.
     * @param ndList
     * @param index1
     * @param index2
     * @return ArrayList<Node>
     */
    private NodeList swapPlaces( NodeList ndList, int index1, int index2 ){
        NodeList newNdList = new NodeList();
        //take the ndList up to index1 and add it to the newTour.
        for( int i = 0; i < index1; i++ ){
            newNdList.add( ndList.get( i ) );
        }

        //change the order of the nodes btw. index1 and index2 and add them to newTour.
        int temp = 0;//used to count back from index2 and place them in newNdList.
        for( int k = index1; k <= index2; k++ ){
            newNdList.add( ndList.get( index2 - temp ) );
            temp++;
        }

        //add the elements of the ndList from index2 to end to newTour.
        for( int x = index2 + 1; x < ndList.size(); x++ ){
            newNdList.add( ndList.get( x ) );
        }

        //return the newNdList.
        return newNdList;
    }

    /**
     * REQUIRES FIXING.(This one works but it is integrated completely wrong in the class.
     * It takes a NodeList as a parameter and returns a NodeList and it is a method of NodeList fix it pls.)
     * Finds the crossings and uses the swap method to fix them according to the 2-opt algorithm idea.
     * @param ndList
     * @return NodeList
     */
    public NodeList calculateTwoOpt( NodeList ndList ){

        NodeList newNdList = new NodeList();//2-opt route will be saved here and this will be returned.
        int nOfSwaps = -27;//initializes number of swaps to a non-zero integer so the while loop starts.
        double currentLength = ndList.calculateDistance();//this is the length of the tour without the 2-opt.
        double newNdListLength = 0;//this is the length of the route after the 2-opt is run on the parameter route.

        while( nOfSwaps != 0 ){//quits when there is no more swaps to be made.

            nOfSwaps = 0;//number of swaps is reset to zero so while loop will break if there is no swaps.

            for (int i = 1; i < ndList.size() - 2; i++){//iterates the list.

                for( int k = i + 1; k < ndList.size() - 1; k++){//iterates the list relative to the parent for loop.

                    //checks if the Edge(i, i-1) + Edge(k+1, k) is larger that Edge(i, k+1) + Edge(i-1, k).
                    if(ndList.get(i).calculateDistanceBTW(ndList.get(i-1))+ndList.get(k+1).calculateDistanceBTW(ndList.get(k))
                            > (ndList.get(i)).calculateDistanceBTW(ndList.get(k+1))+(ndList.get(i-1)).calculateDistanceBTW(ndList.get(k))){
                        //Switches the places of i and k and saves it to the another NodeList.
                        newNdList = swapPlaces( ndList, i, k );
                        //Sets the main NodeList as the swapped one.
                        ndList = newNdList;
                        //increments the number of swaps.
                        nOfSwaps++;
                    }
                }
            }
        }
        return ndList;
    }

    /**
     * REQUIRES FIXING.
     * (It's kinda stupid as it returns a NodeList from a NodeList better if it's void and changes the NodeList directly.)
     * Method which reads a set of xy values from a txt file and saves them as nodes in a NodeList.
     * @return NodeList
     * @throws Exception
     */
    public static NodeList readNodesFromFile() throws Exception
    {
        int startIndex;
        int endIndex;
        String s;
        String s1;
        NodeList temp = new NodeList();
        File file = new File( "src\\Test_Files\\test3.txt" );
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



