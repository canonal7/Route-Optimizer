package GUI_Package;

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
        for (String s : nodeArray)
            jListModel.addElement(s);

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


        backButton = new JButton( "Back" );
        backButton.setBounds( 100, 100, 20, 20);

        jListModel = (DefaultListModel<String>)nodeJList.getModel();

        mainPanel = new JPanel();
        mainPanel.setLayout( null);
        // -------end of creating components------

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
    private void deleteLines( int start, int end, String path )
    {
        ArrayList<String> lines = getAllLines( path );

        try
        {
            fileWriter = new FileWriter( path, false);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // writes all of the lines except the line nums that are betweent the start and end index so that it essentially deletes them
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

    /**
     * Gets all the lines of a file and returns them in an arraylist
     * @param path the path of the file
     * @return ArrayList<String> lines
     */
    private ArrayList<String> getAllLines( String path )
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

    /**
     * Disposes this frame and goes back to its parent
     */
    public void back()
    {
        parentFrame.setVisible( true );
        setVisible( false );
        dispose();
    }

    // inner class
    public class ListSelectionListener implements ActionListener
    {
        public void actionPerformed(ActionEvent arg0)
        {
            // getting the array of selected indices from the JList
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
