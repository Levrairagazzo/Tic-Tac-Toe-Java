

import javax.swing.*;
import java.awt.*;

public class WinChecker {
    public WinChecker() {

    }


    public static void infoBox(String infoMessage, String titleBar) {
        JOptionPane.showMessageDialog(null, infoMessage, titleBar, JOptionPane.INFORMATION_MESSAGE); //https://stackoverflow.com/questions/7080205/popup-message-boxes
    }

    public void checkWinner(MyButton myButtons[]) {
        //Horizontal
        if (checkAlignment(myButtons[0].getText(), myButtons[1].getText(), myButtons[2].getText())) {
            displayWinCombinaison(myButtons[0], myButtons[1], myButtons[2]);
            infoBox("We have a winner!", "Congratulation! ");
        }
        if (checkAlignment(myButtons[3].getText(), myButtons[4].getText(), myButtons[5].getText())) {
            displayWinCombinaison(myButtons[3], myButtons[4], myButtons[5]);
            infoBox("We have a winner!", "Congratulation!");


        }
        if (checkAlignment(myButtons[6].getText(), myButtons[7].getText(), myButtons[8].getText())) {
            displayWinCombinaison(myButtons[6], myButtons[7], myButtons[8]);
            infoBox("We have a winner!", "Congratulation!");


        }

        //Vertical
        if (checkAlignment(myButtons[0].getText(), myButtons[3].getText(), myButtons[6].getText())) {
            displayWinCombinaison(myButtons[0], myButtons[3], myButtons[6]);
            infoBox("We have a winner!", "Congratulation!");
        }
        if (checkAlignment(myButtons[1].getText(), myButtons[4].getText(), myButtons[7].getText())) {
            displayWinCombinaison(myButtons[1], myButtons[4], myButtons[7]);
            infoBox("We have a winner!", "Congratulation!");
        }
        if (checkAlignment(myButtons[2].getText(), myButtons[5].getText(), myButtons[8].getText())) {
            displayWinCombinaison(myButtons[2], myButtons[5], myButtons[8]);
            infoBox("We have a winner!", "Congratulation!");


        }

        //Diagonal
        if (checkAlignment(myButtons[0].getText(), myButtons[4].getText(), myButtons[8].getText())) {
            displayWinCombinaison(myButtons[0], myButtons[4], myButtons[8]);
            infoBox("We have a winner!", "Congratulation!");

        }
        if (checkAlignment(myButtons[2].getText(), myButtons[4].getText(), myButtons[6].getText())) {
            displayWinCombinaison(myButtons[2], myButtons[4], myButtons[6]);
            infoBox("We have a winner!", "Congratulation!");

        }


    }

    private boolean checkAlignment(String s1, String s2, String s3) {
        if (s1.equalsIgnoreCase("") || s2.equalsIgnoreCase("") || s3.equalsIgnoreCase(""))
            return false;
        return (s1.equalsIgnoreCase(s2) && s2.equalsIgnoreCase(s3));
    }

    private void displayWinCombinaison(MyButton b1, MyButton b2, MyButton b3){

        paintButton(b1, Color.GREEN);
        paintButton(b2, Color.GREEN);
        paintButton(b3, Color.GREEN);

    }

    private void paintButton(MyButton btn, Color c){
        btn.setBorderPainted(false);
        btn.setOpaque(true);
        btn.setBackground(c);
        btn.setBorder(BorderFactory.createEtchedBorder());
        btn.setBorderPainted(true);
    }


}
