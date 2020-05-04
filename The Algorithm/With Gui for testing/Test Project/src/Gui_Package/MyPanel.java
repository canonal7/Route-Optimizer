package Gui_Package;


import Edge_Package.EdgeList;
import Node_Package.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MyPanel extends JPanel
{
    // properties
    NodeList l;
    NodeList l2;
    int radius;
    double distance;



    // constructor
    public MyPanel()
    {
        l = new NodeList();
        // where to add the first locations in random order
//        l.add( new Node(100, 200));
  //      l.add( new Node( 100, 300));
    //    l.add( new Node( 120, 700));
      //  l.add( new Node( 22, 821));
  //      l.add( new Node( 459, 192));
    //    l.add( new Node( 792, 623));

        l.add( new Node( 100,100 ));
        l.add( new Node( 200,100 ));
        l.add( new Node( 100,200 ));
        l.add( new Node( 200,200 ));
        l.add( new Node( 500, 500 ));
        l2.add( new Node( 100,100 ));
        l2.add( new Node( 200,100 ));
        l2.add( new Node( 100,200 ));
        l2.add( new Node( 200,200 ));
        l2.add( new Node( 500, 500 ));
       //   l.add( new Node( 700, 700 ));
   //     l.add( new Node( 500, 510 ));
 //       l.add( new Node( 510, 500 ));





        l = algorithm(l); //                where we order the arraylist of locations

        distance = l.calculateDistance();

        System.out.println( "Distance is: "+ distance);

        addMouseMotionListener( new MyMouseMotionListener(l) );

    }


    // methods
    public void paintComponent( Graphics g)
    {
        for( int n = 0; n < l.size(); n++)
        {
            l.get(n).draw( g );
            radius = l.get(n).RADIUS;
            if( n != l.size() - 1) // draws a between the current location and the next, if there is a next location
                g.drawLine(l.get(n).getX() + radius, l.get(n).getY() + radius , l.get(n + 1).getX() + radius, l.get(n + 1).getY() + radius );
        }
        for( int n = 0; n < l2.size(); n++)
        {
            l2.get(n).draw( g );
            radius = l2.get(n).RADIUS;
            if( n != l2.size() - 1) // draws a between the current location and the next, if there is a next location
                g.drawLine(l2.get(n).getX() + radius, l2.get(n).getY() + radius , l2.get(n + 1).getX() + radius, l2.get(n + 1).getY() + radius );
        }
    }


    public NodeList algorithm(NodeList nodeList )
    {
        EdgeList e = new EdgeList( nodeList );
        e.nearestNeighbor();
        System.out.println( e.extractNodeList() );
        return e.extractNodeList();
    }






    // inner class
    // mouse listener to update the tooltip depending on where the mouse is
    public class MyMouseMotionListener extends MouseAdapter
    {
        // properties
        int x;
        int y;
        NodeList l;
        Node temp;

        // constructor
        public MyMouseMotionListener( NodeList l )
        {
            super();
            this.l = l;
        }

        // methods
        // when the mouse moves, it gets the position of the mouse and updates the tooltips
        public void mouseMoved(MouseEvent e)
        {
            x = e.getX();
            y = e.getY();
            setToolTipText( "X: "+ x + " Y: "+ y);

            temp = l.findNodeAtxy(x,y);
            if( temp != null )
                setToolTipText( "X: "+ x + " Y: "+ y + " Node no: " + temp.getOrder() + " of " + l.size());
        }

    }



}
