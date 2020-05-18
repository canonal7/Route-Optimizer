package GUI_Package;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;

/**
 * The enter coordinates frame of the app that lets the user enter coordinates and saves them to a file
 * @author oğuz, saad
 */
public class EnterCoordinatesFrame extends JFrame
{
    // constants
    final String unorderedNodesPath = "src/Txt_Files/Unordered_Nodes.txt";
    final String backgroundPath = "src\\Images\\currentback.png";

    // properties
    private ActionListener actionListener;
    private JFrame thisFrame, parentFrame;
    private JPanel mainPanel;
    private JTextField latitudeField, longitudeField;
    private JLabel titleLabel,backgroundLabel;
    private JButton backButton, enterButton;
    private File unorderedNodes;


    // constructor
    public EnterCoordinatesFrame( JFrame parentFrame )
    {
        super("Route Optimizer");

        thisFrame = this;
        this.parentFrame = parentFrame;

        actionListener = new ButtonActionListener();

        setBounds(750, 200, 500, 625);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // creating the main panel that everything is on
        mainPanel = new JPanel();
        mainPanel.setLayout( null );

        // -------start of creating components------
        titleLabel = new JLabel( "Enter Coordinates" );
        titleLabel.setBounds( 40, 20, 300, 40 );
        titleLabel.setForeground( Color.WHITE );
        titleLabel.setFont( titleLabel.getFont().deriveFont(30f));

        latitudeField = new JTextField( "Latitude" );
        latitudeField.setBounds( 80, 240, 140, 40);
        latitudeField.addActionListener( actionListener );

        longitudeField = new JTextField( "Longitude" );
        longitudeField.setBounds( 260, 240, 140, 40);
        longitudeField.addActionListener( actionListener );

        backButton = new JButton( "Back" );
        backButton.setBounds( 140, 500, 85, 40);
        backButton.addActionListener( actionListener );

        enterButton = new JButton( "Enter" );
        enterButton.setBounds(  240, 500, 85, 40);
        enterButton.addActionListener( actionListener );
        // -------end of creating components------

        // creating the background
        ImageIcon icon = new ImageIcon( backgroundPath );
        backgroundLabel = new JLabel( "" );
        backgroundLabel.setIcon( icon );
        backgroundLabel.setBounds(0,0,500,625);

        // adding every component into the main component
        mainPanel.add( titleLabel );
        mainPanel.add( latitudeField );
        mainPanel.add( longitudeField );
        mainPanel.add( backButton );
        mainPanel.add( enterButton );
        mainPanel.add( backgroundLabel );

        createFile();

        add( mainPanel );
        setResizable(false);
        setVisible( true );

    }

    /**
     * creates the unorderedFiles.txt file if it doesn't exist
     */
    public void createFile()
    {
        try
        {
            unorderedNodes = new File( unorderedNodesPath );
            if( !unorderedNodes.exists())
                unorderedNodes.createNewFile();
        } catch ( java.io.IOException e)
        {
            System.out.println( "There was an error creating a file" );
            e.printStackTrace();
        }
    }

    /**
     * Writes to the unorderdNodes file the nodes line by line, in the format of "y coordinate" + " " + "x coordinate"
     * @param latitude the y coordinate
     * @param longitude the x coordinate
     */
    public void writeFile( String latitude, String longitude )
    {
        if( unorderedNodes == null)
            createFile();
        try
        {
            FileWriter myWriter = new FileWriter( unorderedNodes.getPath(), true);
            myWriter.write( latitude + " " + longitude + "\n" );
            myWriter.close();
            System.out.println( "Wrote files" );
        } catch( java.io.IOException e )
        {
            System.out.println( "There was an error writing a file" );
            e.printStackTrace();
        }
    }


    public class ButtonActionListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent actionEvent )
        {
            if( actionEvent.getSource() == enterButton )
            {
                writeFile( latitudeField.getText(), longitudeField.getText());
            }
            else if( actionEvent.getSource() == backButton )
            {
                parentFrame.setVisible( true );
                setVisible( false );
                dispose();
            }
        }
    }


}
