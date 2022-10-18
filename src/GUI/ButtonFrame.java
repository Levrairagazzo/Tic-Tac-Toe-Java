package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ButtonFrame extends JFrame {
    public JPanel buttonPanel;
    private static final int DEFAULT_WIDTH = 300;
    private static final int DEFAULT_HEIGHT = 200;

    public ButtonFrame(){
        setSize(DEFAULT_WIDTH,DEFAULT_HEIGHT);
//        //Creating buttons
//        var yellowButton = new JButton("Yellow");
//        var blueButton = new JButton("Blue");
//        var redButton = new JButton("Red");
        buttonPanel = new JPanel();
        add(buttonPanel);
//
//        //adding buttons
//
//        buttonPanel.add(yellowButton);
//        buttonPanel.add(blueButton);
//        buttonPanel.add(redButton);
//

//
//        //Creating actions
//
//        var yellowAction = new ColorAction(Color.YELLOW);
//        var blueAction = new ColorAction(Color.BLUE);
//        var redAction = new ColorAction(Color.RED);
//
//        //associate everything
//
//        yellowButton.addActionListener(yellowAction);
//        blueButton.addActionListener(blueAction);
//        redButton.addActionListener(redAction);
        makeButton("Red", Color.RED);
        makeButton("Yellow", Color.YELLOW);
        makeButton("Gray", Color.lightGray);
//
//        var blueAction = new ColorAction("Blue",null, Color.BLUE);
//        var blueButton = new JButton(blueAction);
//        buttonPanel.add(blueButton);
//




    }

    public void makeButton(String name, Color backgroundColor){
        var button = new JButton(name);
        button.addActionListener(event ->
                buttonPanel.setBackground(backgroundColor));
        buttonPanel.add(button);

    }

    private class ColorAction extends AbstractAction{

        public ColorAction(String name, Icon icon, Color c){
            putValue(Action.NAME, name);
            putValue(Action.SMALL_ICON, icon);
            putValue("color", c);
            putValue(Action.SHORT_DESCRIPTION, "Set panel color to " + name.toLowerCase());
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Color c = (Color) getValue("color");
            buttonPanel.setBackground(c);
        }
    }


}



//private class ColorAction implements ActionListener{
//
//    private Color backgroundColor;
//
//    public ColorAction(Color c){backgroundColor = c;}
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        buttonPanel.setBackground(backgroundColor);
//    }
//}
