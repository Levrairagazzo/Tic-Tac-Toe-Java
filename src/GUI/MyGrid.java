package GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.text.html.HTML.Tag.U;

public class MyGrid extends JFrame {
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 600;
    JPanel firstPanel, secondPanel, thirdPanel;
    JPanel topLabel;
    private final ActionButtonClicked[] myClickedEvents = new ActionButtonClicked[9];
    private final TicTacButton myButtons[] = new TicTacButton[9];
    Player player1;
    Player player2;
    Player currentPlayer;
    private int counter;
    private String happy = "\uD83D\uDE00";

    public MyGrid() {



    }

    public void startGUI(){
        EventQueue.invokeLater(() ->
        {

            setSize(WIDTH, HEIGHT);
            setTitle("Sydney's Tic Tac Toe " + "\uD83D\uDE00");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setVisible(true);
            setResizable(false);
            setPreferredSize(new Dimension(WIDTH, HEIGHT));

            player1 = new Player("X");
            player2 = new Player("O");
            currentPlayer = player1;
            counter = 0;
            secondPanel = new JPanel();
            secondPanel.setLayout(new GridLayout(3, 3));
            Start();

            add(secondPanel);
            pack();
        });
    }

    private void Start(){

        for (int i = 0; i < 9; i++) {
            TicTacButton B = new TicTacButton("");
            B.setBorder(BorderFactory.createEtchedBorder());
            secondPanel.add(B);
            myClickedEvents[i] = new ActionButtonClicked(B);
            B.addActionListener(myClickedEvents[i]);
            myButtons[i] = B;
        }

    }

    private void reStart(){
        for (TicTacButton btn:myButtons) {
            paintButton(btn, null);
            btn.setText("");


        }
    }

    class ActionButtonClicked implements ActionListener {
        TicTacButton myBtn;

        public ActionButtonClicked(TicTacButton myBtn) {
            this.myBtn = myBtn;

        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String currentSymbol = getCurrentPlayer().getSymbol();

            if (!myBtn.getText().equalsIgnoreCase("")) {
                System.out.println("Error cell already picked");
                infoBox("Cell is not available, please pick another one.", "Error!");
            } else {
                myBtn.setText(currentSymbol);
                counter++;
                checkWinner();
                switchPlayer();
            }

        }

    }

    public static void infoBox(String infoMessage, String titleBar) {
        JOptionPane.showMessageDialog(null, infoMessage, titleBar, JOptionPane.INFORMATION_MESSAGE); //https://stackoverflow.com/questions/7080205/popup-message-boxes
    }

    public void checkWinner() {
        //Horizontal
        if (checkAlignment(myButtons[0].getText(), myButtons[1].getText(), myButtons[2].getText())) {
            displayWinCombinaison(myButtons[0], myButtons[1], myButtons[2]);
            infoBox("We have a winner!", "Congratulation! " + happy);
            counter = 10;
            reStart();
        }
        if (checkAlignment(myButtons[3].getText(), myButtons[4].getText(), myButtons[5].getText())) {
            displayWinCombinaison(myButtons[3], myButtons[4], myButtons[5]);
            infoBox("We have a winner!", "Congratulation!");
            counter = 10;
            reStart();

        }
        if (checkAlignment(myButtons[6].getText(), myButtons[7].getText(), myButtons[8].getText())) {
            displayWinCombinaison(myButtons[6], myButtons[7], myButtons[8]);
            infoBox("We have a winner!" , "Congratulation!");
            counter = 10;
            reStart();

        }

        //Vertical
        if (checkAlignment(myButtons[0].getText(), myButtons[3].getText(), myButtons[6].getText())) {
            displayWinCombinaison(myButtons[0], myButtons[3], myButtons[6]);
            infoBox("We have a winner!", "Congratulation!");
            counter = 10;
            reStart();
        }
        if (checkAlignment(myButtons[1].getText(), myButtons[4].getText(), myButtons[7].getText())) {
            displayWinCombinaison(myButtons[1], myButtons[4], myButtons[7]);
            infoBox("We have a winner!", "Congratulation!");
            counter = 10;
            reStart();

        }
        if (checkAlignment(myButtons[2].getText(), myButtons[5].getText(), myButtons[8].getText())) {
            displayWinCombinaison(myButtons[2], myButtons[5], myButtons[8]);
            infoBox("We have a winner!", "Congratulation!");
            counter = 10;
            reStart();

        }

        //Diagonal
        if (checkAlignment(myButtons[0].getText(), myButtons[4].getText(), myButtons[8].getText())) {
            displayWinCombinaison(myButtons[0], myButtons[4], myButtons[8]);
            infoBox("We have a winner!", "Congratulation!");
            counter = 10;
            reStart();
        }
        if (checkAlignment(myButtons[2].getText(), myButtons[4].getText(), myButtons[6].getText())) {
            displayWinCombinaison(myButtons[2], myButtons[4], myButtons[6]);
            infoBox("We have a winner!", "Congratulation!");
            counter = 10;
            reStart();

        }



    }
    private void displayWinCombinaison(TicTacButton b1, TicTacButton b2, TicTacButton b3){
        System.out.println("Hello");

        paintButton(b1, Color.GREEN);
        paintButton(b2, Color.GREEN);
        paintButton(b3, Color.GREEN);

    }

    private void paintButton(TicTacButton btn, Color c){
        btn.setBorderPainted(false);
        btn.setOpaque(true);
        btn.setBackground(c);
        btn.setBorder(BorderFactory.createEtchedBorder());
        btn.setBorderPainted(true);
    }


    private boolean checkAlignment(String s1, String s2, String s3) {
        if (s1.equalsIgnoreCase("") || s2.equalsIgnoreCase("") || s3.equalsIgnoreCase(""))
            return false;
        return (s1.equalsIgnoreCase(s2) && s2.equalsIgnoreCase(s3));
    }




    public Player getCurrentPlayer() {
        return this.currentPlayer;
    }

    public void switchPlayer() {
        if (player1 == currentPlayer)
            currentPlayer = player2;
        else
            currentPlayer = player1;
    }


    public static void main(String[] args) {
        var grid = new MyGrid();
    }
}

class TicTacButton extends JButton {
    public static int IDs;
    int uniqueID;

    public TicTacButton(String text) {
        super(text);
        int uniqueID = ++IDs;
    }
}

