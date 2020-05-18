package GUI_Package;

import Edge_Package.*;
import MapDraw.CreateHTML;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EnterLocationsFrame extends JFrame
{
    // properties
    JFrame thisFrame, parent, mapFrame;
    JPanel mainPanel;
    JLabel title, background;
    JButton selectFromMap, enterCoordinates, enterLink, done, edit, back;
    ActionListener actionListener;
    EdgeList edges;
    CreateHTML createHTML;

    // constructor
    public EnterLocationsFrame( JFrame parent)
    {
        super( "Route Optimizer" );
        createHTML = new CreateHTML();

        thisFrame = this;
        this.parent = parent;

        actionListener = new ButtonActionListener();

        setBounds( 750, 200, 500, 625 );
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // creating the main panel that everything is on
        mainPanel = new JPanel();
        mainPanel.setLayout( null );

        // -------start of creating components------
        title = new JLabel( "Enter Locations" );
        title.setBounds( 40, 20, 300, 40 );
        title.setForeground( Color.WHITE );
        title.setFont( title.getFont().deriveFont(30f));

        selectFromMap = new JButton( "Select From Map" );
        selectFromMap.setBounds( 40, 140, 140, 40);
        selectFromMap.addActionListener( actionListener );

        enterCoordinates = new JButton( "Enter Coordinates" );
        enterCoordinates.setBounds( 40, 200, 140, 40 );
        enterCoordinates.addActionListener( actionListener );

        enterLink = new JButton( "Enter Link" );
        enterLink.setBounds( 40, 260, 140, 40 );
        enterLink.addActionListener( actionListener );

        back = new JButton( "Back" );
        back.setBounds( 40, 500, 85, 40);
        back.addActionListener( actionListener );

        edit = new JButton( "Edit" );
        edit.setBounds( 200, 500, 85, 40);
        edit.addActionListener( actionListener );

        done = new JButton( "Done" );
        done.setBounds( 360, 500, 85, 40);
        done.addActionListener( actionListener );
        // -------end of creating components------

        // creating the background
        ImageIcon icon = new ImageIcon("src\\Images\\currentback.png");
        background = new JLabel( "" );
        background.setIcon( icon );
        background.setBounds(0,0,500,625);

        // adding everything into the main panel
        mainPanel.add( title );
        mainPanel.add( selectFromMap );
        mainPanel.add( enterCoordinates );
        mainPanel.add( enterLink );
        mainPanel.add( back );
        mainPanel.add( edit );
        mainPanel.add( done );
        mainPanel.add( background );


        add( mainPanel );
        setResizable(false);
        setVisible( true );
    }




    public class ButtonActionListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent actionEvent )
        {
            if( actionEvent.getSource() == selectFromMap )
            {
                createMap( thisFrame );
                setVisible( false );
                dispose();

            }
            else if( actionEvent.getSource() == enterCoordinates )
            {
                new EnterCoordinatesFrame( thisFrame );
                setVisible( false );
                dispose();
            }
            else if( actionEvent.getSource() == enterLink )
            {

            }
            else if( actionEvent.getSource() == done )
            {
                edges = EdgeList.createFromText();
                edges.nearestNeighbor();
                edges.calculateTwoOpt();
                System.out.println( "nodes are " + edges.extractNodeList() );
                createHTML.overwriteFile( "src/Map_Files/HTML/simple_map.html", edges.extractNodeList());
                createMap( thisFrame );
                setVisible( false );
                dispose();

            }
            else if( actionEvent.getSource() == edit )
            {

            }
            else if( actionEvent.getSource() == back )
            {
                parent.setVisible( true );
                setVisible( false );
                dispose();
            }
        }
    }

    public void createMap( JFrame parent )
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
                mapFrame = new MapFrame( parent );
                mapFrame.setVisible( true );
                setVisible( false );
                dispose();
            }
        });
    }
}
