package GUI_Package;

import MapDraw.CreateHTML;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

/**
 * The main frame of the app
 * @author oğuz, saad
 */
public class MainFrame extends JFrame
{
    // constants
    final String unorderedNodesPath = "src/Txt_Files/Unordered_Nodes.txt";
    final String backgroundPath = "src\\Images\\currentback.png";
    final String mapHTMLPath = "src/Map_Files/HTML/simple_map.html";

    // properties
    private JFrame thisFrame;
    private JPanel mainPanel;
    private JLabel titleLabel, backgroundLabel;
    private JButton enterLocationsButton, settingsButton, quitButton;
    private ButtonActionListener actionListener;
    private File unorderedNodes;

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
        titleLabel = new JLabel( "Route Optimizer " );
        titleLabel.setBounds( 40, 20, 300, 40);
        titleLabel.setForeground( Color.WHITE );
        titleLabel.setFont( titleLabel.getFont().deriveFont(30f));

        enterLocationsButton = new JButton( "Enter Locations" );
        enterLocationsButton.setBounds( 40, 140, 125, 40);
        enterLocationsButton.addActionListener( actionListener );

        settingsButton = new JButton( "Settings" );
        settingsButton.setBounds( 40, 200, 125, 40);
        settingsButton.addActionListener( actionListener );

        quitButton = new JButton( "Quit" );
        quitButton.setBounds(40, 500, 125, 40);
        quitButton.addActionListener( actionListener );
        // -------end of creating components------

        // creating the background
        ImageIcon icon = new ImageIcon(backgroundPath);
        backgroundLabel = new JLabel( "" );
        backgroundLabel.setIcon( icon );
        backgroundLabel.setBounds(0,0,500,625);


        // adding every component to the main panel
        mainPanel.add( quitButton );
        mainPanel.add( settingsButton );
        mainPanel.add( titleLabel );
        mainPanel.add( enterLocationsButton );
        mainPanel.add( backgroundLabel );



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
            if( actionEvent.getSource() == quitButton )
            {
                // creates an event that happens when the user clicks the close button on the top right
                dispatchEvent(new WindowEvent(thisFrame, WindowEvent.WINDOW_CLOSING));
            }
            else if( actionEvent.getSource() == enterLocationsButton )
            {
                new EnterLocationsFrame( thisFrame );
                setVisible( false );
                dispose();
            }
            else if( actionEvent.getSource() == settingsButton )
            {
                new SettingsFrame( thisFrame );
                setVisible( false );
                dispose();
            }
        }
    }
}

