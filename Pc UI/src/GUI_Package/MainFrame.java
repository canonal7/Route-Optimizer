package GUI_Package;

import MapDraw.CreateHTML;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

public class MainFrame extends JFrame
{
    // constants
    final String unorderedNodesPath = "src/Txt_Files/Unordered_Nodes.txt";
    final String backgroundPath = "src\\Images\\currentback.png";
    final String mapHTMLPath = "src/Map_Files/HTML/simple_map.html";

    // properties
    JFrame thisFrame;
    JPanel mainPanel;
    JLabel title, background;
    JButton enterLocations, settings, quit;
    ButtonActionListener actionListener;
    File unorderedNodes;

    // constructor
    public MainFrame()
    {
        super( "Route Optimizer" );

        thisFrame = this;

        // clears the map and resets the file
        CreateHTML createHTML = new CreateHTML();
        createHTML.returnToOgHTML( mapHTMLPath );
        resetFile();

        setBounds( 750, 200, 500, 625 );
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        actionListener = new ButtonActionListener();

        // creating the main panel that everything is on
        mainPanel = new JPanel();
        mainPanel.setLayout(null);

        // -------start of creating components------
        title = new JLabel( "Route Optimizer " );
        title.setBounds( 40, 20, 300, 40);
        title.setForeground( Color.WHITE );
        title.setFont( title.getFont().deriveFont(30f));

        enterLocations = new JButton( "Enter Locations" );
        enterLocations.setBounds( 40, 140, 125, 40);
        enterLocations.addActionListener( actionListener );

        settings = new JButton( "Settings" );
        settings.setBounds( 40, 200, 125, 40);
        settings.addActionListener( actionListener );

        quit = new JButton( "Quit" );
        quit.setBounds(40, 500, 125, 40);
        quit.addActionListener( actionListener );
        // -------end of creating components------

        // creating the background
        ImageIcon icon = new ImageIcon(backgroundPath);
        background = new JLabel( "" );
        background.setIcon( icon );
        background.setBounds(0,0,500,625);


        // adding every component to the main panel
        mainPanel.add( quit );
        mainPanel.add( settings );
        mainPanel.add( title );
        mainPanel.add( enterLocations );
        mainPanel.add( background );



        add( mainPanel );
        setResizable(false);
        setVisible( true );
    }

    /**
     * deletes and creates a new file to reset its contents
     */
    public void resetFile()
    {
        unorderedNodes = new File( unorderedNodesPath );
        unorderedNodes.delete();
        try {
            unorderedNodes.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println( "There was an error creating the file" );
        }
    }


    public class ButtonActionListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent actionEvent )
        {
            if( actionEvent.getSource() == quit )
            {
                // creates an event that happens when the user clicks the close button on the top right
                dispatchEvent(new WindowEvent(thisFrame, WindowEvent.WINDOW_CLOSING));
            }
            else if( actionEvent.getSource() == enterLocations )
            {
                new EnterLocationsFrame( thisFrame );
                setVisible( false );
                dispose();
            }
            else if( actionEvent.getSource() == settings )
            {
                new SettingsFrame( thisFrame );
                setVisible( false );
                dispose();
            }
        }
    }
}
