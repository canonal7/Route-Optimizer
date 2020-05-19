package GUI_Package;

import MapDraw.CreateHTML;
import Node_Package.NodeList;
import javax.swing.*;
import java.awt.*;


public class EditLocationsFrame extends JFrame
{
    // properties
    private JFrame parentFrame, mapFrame;
    private CreateHTML createHTML;
    private JPanel mainPanel, mapPanel, listPanel;
    private JButton backButton, deleteButton;
    private JList<Object> nodeJList;
    private NodeList nodes;
    private Dimension dim;


    // constructor
    public EditLocationsFrame( JFrame parentFrame )
    {
        super("Route Optimizer");

        this.parentFrame = parentFrame;

        setExtendedState( MAXIMIZED_BOTH );
        setDefaultCloseOperation( EXIT_ON_CLOSE );
        setUndecorated( true );

        // -------start of creating components------
        dim = Toolkit.getDefaultToolkit().getScreenSize();

        mapFrame = new MapFrame( this );
        mapPanel = (JPanel)mapFrame.getContentPane();
        mapPanel.setBounds( 0,0, (int)dim.getWidth()*8/10, (int)dim.getHeight() );

        nodes = new NodeList();
        nodes.readNodesFromFile();
        nodeJList = new JList<Object>( nodes.getStringList().toArray());


        listPanel = new JPanel();
        listPanel.setBounds( (int)(dim.getWidth()*(8/10)), 0, (int)(dim.getWidth()*(2/10)), (int)dim.getHeight() );
        listPanel.setLayout( new BorderLayout() );
        listPanel.add( nodeJList );


        mainPanel = new JPanel();
        mainPanel.setLayout( null);

        backButton = new JButton( "Back" );
        //backButton.setBounds();

        deleteButton = new JButton( "Delete" );
        //deleteButton.setBounds();
        // -------end of creating components------








        mainPanel.setLayout( null );
        mainPanel.add( listPanel );
        mainPanel.add( mapPanel );



        add( mainPanel );

        setVisible( true );
    }
}
