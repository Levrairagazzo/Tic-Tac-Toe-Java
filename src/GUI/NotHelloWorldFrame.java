package GUI;

import javax.swing.*;
import java.awt.*;

public class NotHelloWorldFrame  extends JFrame {
    public NotHelloWorldFrame(){
        add(new NotHelloWorldComponent());
        pack();
    }

}

class NotHelloWorldComponent extends JComponent {
    public static final int MESSAGE_X = 75;
    public static final int MESSAGE_Y = 100;

    private static final int WIDTH = 300;
    private static final int HEIGHT = 200;

    public void paintComponent(Graphics g){
        g.drawString("This is not Hello World :)", MESSAGE_X, MESSAGE_Y);
    }

    public Dimension getPreferredSize(){
        return new Dimension(WIDTH, HEIGHT);
    }


}
