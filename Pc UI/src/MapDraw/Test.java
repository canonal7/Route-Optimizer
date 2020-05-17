package MapDraw;

import Node_Package.Node;
import Node_Package.NodeList;

public class Test {
    public static void main( String[] args ){
        NodeList nodeList = new NodeList();
        nodeList.add( new Node( 12, 24) );
        nodeList.add( new Node(9, 4) );
        CreateHTML trial = new CreateHTML();
        trial.overwriteFile( "src\\Txt_Files\\Ordered_Nodes.txt", nodeList );
    }
}
