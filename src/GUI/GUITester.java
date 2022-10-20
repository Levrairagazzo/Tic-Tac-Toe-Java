package GUI;

import javax.swing.*;
import java.awt.*;

public class GUITester {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            var frame = new ButtonFrame();
//            frame.add(new MouseComponent());
            frame.setTitle("My window");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
