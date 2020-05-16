package GUI_Package;

import Edge_Package.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EnterLocationsFrame extends JFrame
{
    // properties
    JFrame thisFrame, parent;
    JPanel mainPanel;
    JLabel title;
    JButton selectFromMap, enterCoordinates, enterLink, done, edit, back;
    ActionListener actionListener;
    EdgeList edges;

    // constructor
    public EnterLocationsFrame( JFrame parent)
    {
        super( "Route Optimizer" );

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
        title.setBounds( 40, 20, 300, 20 );

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

        // adding everything into the main panel
        mainPanel.add( title );
        mainPanel.add( selectFromMap );
        mainPanel.add( enterCoordinates );
        mainPanel.add( enterLink );
        mainPanel.add( back );
        mainPanel.add( edit );
        mainPanel.add( done );


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
                edges.extractToFile( "C:\\Users\\halit\\Desktop\\Pc UI\\src\\Txt_Files\\Ordered_Nodes.txt");

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
}
