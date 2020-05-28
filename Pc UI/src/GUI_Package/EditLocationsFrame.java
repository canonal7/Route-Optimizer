package GUI_Package;

import MapDraw.CreateHTML;
import Node_Package.NodeList;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

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
    private DefaultListModel<String> jListModel;
    private String[] nodeArray;
    private int[] selectedIndices;


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

        mapFrame = new MapFrame( this, true );

        mapPanel = (JPanel)mapFrame.getContentPane();
        mapPanel.setBounds( 0,0, (int)dim.getWidth()*8/10, (int)dim.getHeight() );

        nodes = new NodeList();
        nodes.readNodesFromFile();

        nodeArray = nodes.getStringList();

        jListModel = new DefaultListModel<>();
        for( int n = 0; n <  nodeArray.length; n++ )
            jListModel.addElement( nodeArray[n] );


        nodeJList = new JList<String>( jListModel );
        nodeJList.setFont( nodeJList.getFont().deriveFont(15f));
        nodeJList.setLayoutOrientation( JList.VERTICAL );
        nodeJList.setBounds( 0, 0, (int)dim.getWidth()*2/10, (int)dim.getHeight() - 40);

        deleteButton = new JButton( "Delete Location" );
        deleteButton.setBounds( (int)dim.getWidth()*17/20,(int)dim.getHeight() - 40 , (int)dim.getWidth()/10, 40);
        deleteButton.addActionListener( new ListSelectionListener() );

        scrollPane = new JScrollPane();
        scrollPane.setViewportView( nodeJList );
        scrollPane.setBounds( 0, 0, (int)(dim.getWidth()*2/10), (int)dim.getHeight() - 40 );
        scrollPane.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS );

        listPanel = new JPanel();
        listPanel.setBounds( (int)(dim.getWidth()*8/10), 0, (int)(dim.getWidth()*2/10), (int)dim.getHeight() - 40);
        listPanel.setLayout( null );
        listPanel.add( scrollPane );


        mainPanel = new JPanel();
        mainPanel.setLayout( null);

        backButton = new JButton( "Back" );
        backButton.setBounds( 100, 100, 20, 20);

        jListModel = (DefaultListModel<String>)nodeJList.getModel();
        // -------end of creating components------


        //deleteLines( 2, 4, unorderedNodesPath );





        mainPanel.setLayout( null );
        mainPanel.add( listPanel );
        mainPanel.add( mapPanel );
        mainPanel.add( deleteButton );



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
            scan.close();
        } catch( FileNotFoundException e )
        {
            System.out.println( "Fine cant be found" );
        }

        return lines;
    }

    public void back()
    {
        parentFrame.setVisible( true );
        setVisible( false );
        dispose();
    }

    public class ListSelectionListener implements ActionListener
    {
        public void actionPerformed(ActionEvent arg0)
        {
            selectedIndices = nodeJList.getSelectedIndices();

            // deleting the nodes at the text file
            if( selectedIndices != null && selectedIndices.length > 0)
            deleteLines( selectedIndices[0], selectedIndices[selectedIndices.length - 1], unorderedNodesPath);

            // get a list with selected objects
            List<String> selectedItems = nodeJList.getSelectedValuesList();

            for (String node: selectedItems)
                jListModel.removeElement(node);

            // reloads the map for the change of markers to take effect
            ((MapFrame)mapFrame).reloadButtonActionPerformed( arg0 );
        }
    }
}
