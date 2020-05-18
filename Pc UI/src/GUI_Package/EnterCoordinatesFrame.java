package GUI_Package;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;


public class EnterCoordinatesFrame extends JFrame
{
    // properties
    ActionListener actionListener;
    JFrame thisFrame, parent;
    JPanel mainPanel;
    JTextField latitudeField, longitudeField;
    JLabel title,background;
    JButton back, enter;
    File unorderedNodes;


    // constructor
    public EnterCoordinatesFrame( JFrame parent )
    {
        super("Route Optimizer");

        thisFrame = this;
        this.parent = parent;

        actionListener = new ButtonActionListener();

        setBounds(750, 200, 500, 625);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // creating the main panel that everything is on
        mainPanel = new JPanel();
        mainPanel.setLayout( null );

        // -------start of creating components------
        title = new JLabel( "Enter Coordinates" );
        title.setBounds( 40, 20, 300, 40 );
        title.setForeground( Color.WHITE );
        title.setFont( title.getFont().deriveFont(30f));

        latitudeField = new JTextField( "Latitude" );
        latitudeField.setBounds( 80, 240, 140, 40);
        latitudeField.addActionListener( actionListener );

        longitudeField = new JTextField( "Longitude" );
        longitudeField.setBounds( 260, 240, 140, 40);
        longitudeField.addActionListener( actionListener );

        back = new JButton( "Back" );
        back.setBounds( 140, 500, 85, 40);
        back.addActionListener( actionListener );

        enter = new JButton( "Enter" );
        enter.setBounds(  240, 500, 85, 40);
        enter.addActionListener( actionListener );
        // -------end of creating components------

        // creating the background
        ImageIcon icon = new ImageIcon("src\\Images\\currentback.png");
        background = new JLabel( "" );
        background.setIcon( icon );
        background.setBounds(0,0,500,625);

        // adding every component into the main component
        mainPanel.add( title );
        mainPanel.add( latitudeField );
        mainPanel.add( longitudeField );
        mainPanel.add( back );
        mainPanel.add( enter );
        mainPanel.add( background );

        createFile();

        add( mainPanel );
        setResizable(false);
        setVisible( true );

    }

    public void createFile()
    {
        try
        {
            unorderedNodes = new File( "src\\Txt_Files\\Unordered_Nodes.txt" );
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
            if( actionEvent.getSource() == enter )
            {
                writeFile( latitudeField.getText(), longitudeField.getText());
            }
            else if( actionEvent.getSource() == back )
            {
                parent.setVisible( true );
                setVisible( false );
                dispose();
            }
        }
    }


}
