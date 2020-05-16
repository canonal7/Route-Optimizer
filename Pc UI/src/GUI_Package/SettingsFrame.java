package GUI_Package;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SettingsFrame extends JFrame
{
    // properties
    JFrame parent;
    JLabel title, background;
    JPanel mainPanel;
    JButton accuracyLevel, mapLocation, back;
    JRadioButton downloadMap, satelliteView;
    ActionListener actionListener;

    // constructor
    public SettingsFrame( JFrame parent )
    {
        super( "Settings" );

        this.parent = parent;

        actionListener = new ButtonActionListener();

        setBounds( 750, 200, 500, 625 );
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // creating the main panel that everything is on
        mainPanel = new JPanel();
        mainPanel.setLayout( null );


        // -------start of creating components------
        title = new JLabel( "Settings" );
        title.setBounds( 40, 20, 300, 40);
        title.setForeground( Color.WHITE );
        title.setFont( title.getFont().deriveFont(30f));


        downloadMap = new JRadioButton( "Download Map" );
        downloadMap.setBounds(40, 140, 125, 40);

        satelliteView = new JRadioButton( "Satellite view" );
        satelliteView.setBounds( 40, 200, 125, 40);

        accuracyLevel = new JButton( "Accuracy Level" );
        accuracyLevel.setBounds( 40, 260, 125, 40);

        mapLocation = new JButton( "Map Location" );
        mapLocation.setBounds( 40, 320, 125, 40);

        back = new JButton( "Back" );
        back.setBounds( 190, 500, 85, 40);
        back.addActionListener( actionListener );
        // -------end of creating components------

        // creating the background
        ImageIcon icon = new ImageIcon("src\\Images\\currentback.png");
        background = new JLabel( "" );
        background.setIcon( icon );
        background.setBounds(0,0,500,625);


        // putting every component into the main panel
        mainPanel.add( downloadMap );
        mainPanel.add( satelliteView );
        mainPanel.add( title );
        mainPanel.add( accuracyLevel );
        mainPanel.add( mapLocation );
        mainPanel.add( back );
        mainPanel.add( background );

        add( mainPanel );
        setResizable(false);
        setVisible( true );
    }

    public class ButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if (actionEvent.getSource() == accuracyLevel)
            {

            }
            else if (actionEvent.getSource() == mapLocation)
            {

            }
            else if (actionEvent.getSource() == back)
            {
                parent.setVisible(true);
                setVisible(false);
                dispose();
            }
        }
    }
}
