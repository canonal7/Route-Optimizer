package GUI_Package;

import MapDraw.CreateHTML;
import Node_Package.NodeList;
import javax.swing.*;
import java.awt.*;


public class EditLocationsFrame extends JFrame
{
    // properties
    JFrame parentFrame, mapFrame;
    CreateHTML createHTML;
    JPanel mainPanel, mapPanel, listPanel;
    JButton backButton, deleteButton;
    JList<Object> nodeJList;
    NodeList nodes;
    Dimension dim;


    // constructor
    public EditLocationsFrame( JFrame parentFrame )
    {
        super("Route Optimizer");

        this.parentFrame = parentFrame;

        setExtendedState( MAXIMIZED_BOTH );
        setDefaultCloseOperation( EXIT_ON_CLOSE );

        // -------start of creating components------
        dim = Toolkit.getDefaultToolkit().getScreenSize();

        mapFrame = new MapFrame( this );
        mapPanel = (JPanel)mapFrame.getContentPane();
        mapPanel.setBounds( 0,0, (int)(dim.getWidth()*(double)(9/10)), (int)dim.getHeight() );

/*        listPanel = new JPanel();
        listPanel.setBounds( (int)(dim.getWidth()*(double)(3/4)), 0, (int)(dim.getWidth()*(double)(1/4)), (int)dim.getHeight() );
        listPanel.setBackground( Color.BLUE );*/

        mainPanel = new JPanel();
        mainPanel.setLayout( null);

        backButton = new JButton( "Back" );
        //backButton.setBounds();

        deleteButton = new JButton( "Delete" );
        //deleteButton.setBounds();

        nodes = new NodeList();
        nodes.readNodesFromFile();

        nodeJList = new JList<Object>( nodes.getStringList().toArray());
        //nodeJList.setBounds();








        //mainPanel.add( listPanel );
        mainPanel.add( mapPanel );
        mainPanel.setLayout( null );



        add( mainPanel );

        setVisible( true );
    }
}
