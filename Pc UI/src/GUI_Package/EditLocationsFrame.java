package GUI_Package;

import MapDraw.CreateHTML;
import Node_Package.NodeList;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class EditLocationsFrame extends JFrame
{
    // constants
    final String unorderedNodesPath = "src/Txt_Files/Unordered_Nodes.txt";

    // properties
    private JFrame parentFrame, mapFrame;
    private CreateHTML createHTML;
    private JPanel mainPanel, mapPanel, listPanel;
    private JButton backButton, deleteButton;
    private JList<String> nodeJList;
    private NodeList nodes;
    private Dimension dim;
    private JScrollPane scrollPane;
    private FileWriter fileWriter;


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


        nodeJList = new JList<String>( nodes.getStringList());
        nodeJList.setFont( nodeJList.getFont().deriveFont(15f));
        nodeJList.setLayoutOrientation( JList.VERTICAL );
        nodeJList.setBounds( 0, 0, (int)dim.getWidth()*2/10, (int)dim.getHeight() - 40);

        deleteButton = new JButton( "Delete Location" );
        deleteButton.setBounds( (int)dim.getWidth()/10 - 100,(int)dim.getHeight() - 40 , 200, 40);

        scrollPane = new JScrollPane();
        //scrollPane.setViewportView( nodeJList );
        scrollPane.setBounds( 0, 0, (int)(dim.getWidth()*2/10), (int)dim.getHeight() );
        scrollPane.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS );
        scrollPane.setLayout( null );

        scrollPane.add( deleteButton );
        scrollPane.add( nodeJList );

        listPanel = new JPanel();
        listPanel.setBounds( (int)(dim.getWidth()*8/10), 0, (int)(dim.getWidth()*2/10), (int)dim.getHeight() );
        listPanel.setLayout( null );
        listPanel.add( scrollPane );


        mainPanel = new JPanel();
        mainPanel.setLayout( null);

        backButton = new JButton( "Back" );
        backButton.setBounds( 100, 100, 20, 20);



        // -------end of creating components------


        //deleteLines( 2, 4, unorderedNodesPath );





        mainPanel.setLayout( null );
        mainPanel.add( listPanel );
        mainPanel.add( mapPanel );



        add( mainPanel );

        setVisible( true );
    }

    /**
     * deletes the lines between the start and end index of a file
     * @param start the start index
     * @param end the end index
     */
    public void deleteLines( int start, int end, String path )
    {
        ArrayList<String> lines = getAllLines( path );

        try
        {
            fileWriter = new FileWriter( path, false);
        } catch (IOException e) {
            e.printStackTrace();
        }



        for( int n = 0; n < lines.size(); n++)
        {
            if( !( n >= start && n <= end) )
            {
                try {
                    fileWriter.write( lines.get(n) + "\n" );
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        try {
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public ArrayList<String> getAllLines( String path )
    {
        ArrayList<String> lines = null;
        try
        {
            lines = new ArrayList<String>();
            Scanner scan = new Scanner( new File( path ) );
            while( scan.hasNextLine() )
                lines.add( scan.nextLine() );
            System.out.println( "Final lines: " + lines );
            scan.close();
        } catch( FileNotFoundException e )
        {
            System.out.println( "Fine cant be found" );
        }

        return lines;
    }
}
