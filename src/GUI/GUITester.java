package GUI;

import javax.swing.*;
import java.awt.*;

public class GUITester {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
          var  grid = new MyGrid();
          grid.startGUI();
        });
    }
}
