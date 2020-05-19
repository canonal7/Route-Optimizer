package GUI_Package;

import Edge_Package.*;
import MapDraw.CreateHTML;
import Node_Package.NodeList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The enter locations frame of the app that has its components
 * @author oğuz, saad
 */
public class EnterLocationsFrame extends JFrame
{
    // constants
    final String backgroundPath = "src\\Images\\currentback.png";
    final String mapHTMLPath = "src/Map_Files/HTML/simple_map.html";

    // properties
    private JFrame thisFrame, parentFrame, mapFrame;
    private JPanel mainPanel;
    private JLabel titleLabel, backgroundLabel;
    private JButton selectFromMapButton, enterCoordinatesButton, enterLinkButton, doneButton, editButton, backButton;
    private ActionListener actionListener;
    private EdgeList edges;
    private CreateHTML createHTML;
    private NodeList nodes;

    // constructor
    public EnterLocationsFrame( JFrame parentFrame)
    {
        super( "Route Optimizer" );
        createHTML = new CreateHTML();

        thisFrame = this;
        this.parentFrame = parentFrame;

        actionListener = new ButtonActionListener();

        setBounds( 750, 200, 500, 625 );
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // creating the main panel that everything is on
        mainPanel = new JPanel();
        mainPanel.setLayout( null );

        // -------start of creating components------
        titleLabel = new JLabel( "Enter Locations" );
        titleLabel.setBounds( 40, 20, 300, 40 );
        titleLabel.setForeground( Color.WHITE );
        titleLabel.setFont( titleLabel.getFont().deriveFont(30f));

        selectFromMapButton = new JButton( "Select From Map" );
        selectFromMapButton.setBounds( 40, 140, 140, 40);
        selectFromMapButton.addActionListener( actionListener );

        enterCoordinatesButton = new JButton( "Enter Coordinates" );
        enterCoordinatesButton.setBounds( 40, 200, 140, 40 );
        enterCoordinatesButton.addActionListener( actionListener );

        enterLinkButton = new JButton( "Enter Link" );
        enterLinkButton.setBounds( 40, 260, 140, 40 );
        enterLinkButton.addActionListener( actionListener );

        backButton = new JButton( "Back" );
        backButton.setBounds( 40, 500, 85, 40);
        backButton.addActionListener( actionListener );

        editButton = new JButton( "Edit" );
        editButton.setBounds( 200, 500, 85, 40);
        editButton.addActionListener( actionListener );

        doneButton = new JButton( "Done" );
        doneButton.setBounds( 360, 500, 85, 40);
        doneButton.addActionListener( actionListener );
        // -------end of creating components------

        // creating the background
        ImageIcon icon = new ImageIcon(backgroundPath);
        backgroundLabel = new JLabel( "" );
        backgroundLabel.setIcon( icon );
        backgroundLabel.setBounds(0,0,500,625);

        // adding everything into the main panel
        mainPanel.add( titleLabel );
        mainPanel.add( selectFromMapButton );
        mainPanel.add( enterCoordinatesButton );
        mainPanel.add( enterLinkButton );
        mainPanel.add( backButton );
        mainPanel.add( editButton );
        mainPanel.add( doneButton );
        mainPanel.add( backgroundLabel );


        add( mainPanel );
        setResizable(false);
        setVisible( true );
    }




    public class ButtonActionListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent actionEvent )
        {
            if( actionEvent.getSource() == selectFromMapButton )
            {
                createHTML.returnToOgHTML( mapHTMLPath );
                createMap( thisFrame );
                setVisible( false );
                dispose();

            }
            else if( actionEvent.getSource() == enterCoordinatesButton )
            {
                new EnterCoordinatesFrame( thisFrame );
                setVisible( false );
                dispose();
            }
            else if( actionEvent.getSource() == enterLinkButton )
            {

            }
            else if( actionEvent.getSource() == doneButton )
            {
                edges = EdgeList.createFromText();
                edges.nearestNeighbor();
                edges.calculateTwoOpt();
                //System.out.println( "nodes are " + edges.extractNodeList() );
                createHTML.overwriteFile( mapHTMLPath, edges.extractNodeList());
                createMap( thisFrame );
                setVisible( false );
                dispose();

            }
            else if( actionEvent.getSource() == editButton )
            {
                nodes = new NodeList();
                nodes.readNodesFromFile();
                createHTML.showMarkersOnHTML( mapHTMLPath, nodes );
                new EditLocationsFrame( thisFrame );
                setVisible( false );
                dispose();
            }
            else if( actionEvent.getSource() == backButton )
            {
                parentFrame.setVisible( true );
                setVisible( false );
                dispose();
            }
        }
    }

    public void createMap( JFrame parentFrame )
    {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MapFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                mapFrame = new MapFrame( parentFrame );
                mapFrame.setVisible( true );
                setVisible( false );
                dispose();
            }
        });
    }
}
