package GUI_Package;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The enter locations frame of the app that has its components
 * @author oğuz, saad
 */
public class SettingsFrame extends JFrame
{
    // constants
    final String backgroundPath = "src\\Images\\currentback.png";

    // properties
    private JFrame parentFrame;
    private JLabel titleLabel, backgroundLabel;
    private JPanel mainPanel;
    private JButton accuracyLevelButton, mapLocationButton, backButton;
    private JRadioButton downloadMapButton, satelliteViewButton;
    private ActionListener actionListener;

    // constructor
    public SettingsFrame( JFrame parentFrame )
    {
        super( "Settings" );

        this.parentFrame = parentFrame;

        actionListener = new ButtonActionListener();

        setBounds( 750, 200, 500, 625 );
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // creating the main panel that everything is on
        mainPanel = new JPanel();
        mainPanel.setLayout( null );


        // -------start of creating components------
        titleLabel = new JLabel( "Settings" );
        titleLabel.setBounds( 40, 20, 300, 40);
        titleLabel.setForeground( Color.WHITE );
        titleLabel.setFont( titleLabel.getFont().deriveFont(30f));


        downloadMapButton = new JRadioButton( "Download Map" );
        downloadMapButton.setBounds(40, 140, 125, 40);

        satelliteViewButton = new JRadioButton( "Satellite view" );
        satelliteViewButton.setBounds( 40, 200, 125, 40);

        accuracyLevelButton = new JButton( "Accuracy Level" );
        accuracyLevelButton.setBounds( 40, 260, 125, 40);

        mapLocationButton= new JButton( "Map Location" );
        mapLocationButton.setBounds( 40, 320, 125, 40);

        backButton = new JButton( "Back" );
        backButton.setBounds( 190, 500, 85, 40);
        backButton.addActionListener( actionListener );
        // -------end of creating components------

        // creating the backgroundLabel
        ImageIcon icon = new ImageIcon( backgroundPath );
        backgroundLabel = new JLabel( "" );
        backgroundLabel.setIcon( icon );
        backgroundLabel.setBounds(0,0,500,625);


        // putting every component into the main panel
        mainPanel.add( downloadMapButton );
        mainPanel.add( satelliteViewButton );
        mainPanel.add( titleLabel );
        mainPanel.add( accuracyLevelButton );
        mainPanel.add( mapLocationButton );
        mainPanel.add( backButton );
        mainPanel.add( backgroundLabel );

        add( mainPanel );
        setResizable(false);
        setVisible( true );
    }

    public class ButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if (actionEvent.getSource() == accuracyLevelButton)
            {

            }
            else if (actionEvent.getSource() == mapLocationButton)
            {

            }
            else if (actionEvent.getSource() == backButton)
            {
                parentFrame.setVisible(true);
                setVisible(false);
                dispose();
            }
        }
    }
}
