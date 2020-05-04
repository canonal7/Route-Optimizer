package Gui_Package;
import javax.swing.*;

public class MyFrame extends JFrame
{
    public MyFrame()
    {
        add( new MyPanel());



        setBounds( 0,0, 1000, 1000);
        setVisible( true );
    }
}
