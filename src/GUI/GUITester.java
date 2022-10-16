package GUI;

import javax.swing.*;
import java.awt.*;

public class GUITester {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            var frame = new NotHelloWorldFrame();
            frame.setTitle("Not a Hello World program");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
