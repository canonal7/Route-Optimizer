package GUI_Package;

import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class MainFrame extends JFrame
{
    // properties
    JFrame thisFrame;
    JPanel mainPanel;
    JLabel title;
    JButton enterLocations, settings, quit;
    ButtonActionListener actionListener;

    // constructor
    public MainFrame()
    {
        super( "Route Optimizer" );

        thisFrame = this;

        setBounds( 750, 200, 500, 625 );
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        actionListener = new ButtonActionListener();

        // creating the main panel that everything is on
        mainPanel = new JPanel();
        mainPanel.setLayout(null);

        // -------start of creating components------
        title = new JLabel( "Route optimizer " );
        title.setBounds( 40, 20, 300, 20);

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

        // adding every component to the main panel
        mainPanel.add( quit );
        mainPanel.add( settings );
        mainPanel.add( title );
        mainPanel.add( enterLocations );

        add( mainPanel );
        setResizable(false);
        setVisible( true );
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
