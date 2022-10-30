import GUI.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class GameClient extends JFrame {
    private final int WIDTH = 1000;
    private final int HEIGHT = 600;
    private final int MAX_TURNS = 9;

    JPanel mainPanel;
    MyButton[] buttons;
    JTextArea GUIMessage;
    private int playerID, otherPlayerID;
    ClientConnection CC;
    private int turnsTaken;
    private String[] clientBoard;
    private boolean buttonsEnabled;
    private WinChecker winChecker;




    public GameClient() {
        winChecker = new WinChecker();
        buttonsEnabled = true;
        clientBoard = new String[9];
        for (int i = 0; i < 9; i++) {
            clientBoard[i] = "";
        }
        CC = new ClientConnection();
        CC.connectToServer();
//        displayGUI();
    }


    private void displayGUI() {

        setSize(WIDTH, HEIGHT);
        setTitle("(Player#" + playerID + ") " + "Sydney's Tic Tac Toe " + "\uD83D\uDE00");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));

        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(4, 3));
        GUIMessage = new JTextArea("It's your turn, please pick a cell");
        GUIMessage.setEditable(false);
        addButtons();
        mainPanel.add(GUIMessage);
        add(mainPanel);
        pack();

        if(playerID == 1){
            GUIMessage.setText("You are player #1, you go first.");
            otherPlayerID = 2;
            buttonsEnabled = true;
        }else{
            GUIMessage.setText("You are player#2, wait for the other player. ");
            otherPlayerID = 1;
            toggleButtons();
            Thread t = new Thread (() -> {
                int otherPlayerPick = CC.receiveButtonIndex();
                buttons[otherPlayerPick].setText("X");
                buttons[otherPlayerPick].setPlayed(true);
                clientBoard[otherPlayerPick] = "X";
                winChecker.checkWinner(buttons);
                toggleButtons();
            });
            t.start();
        }
        CC.setUpActionListeners();

    }




    private void addButtons() {
        buttons = new MyButton[9];
        for (int i = 0; i < 9; i++) {
            buttons[i] = new MyButton(i,clientBoard[i]);
            mainPanel.add(buttons[i]);

        }
    }



    private void toggleButtons(){
        buttonsEnabled = !buttonsEnabled;
        for (int i = 0; i < 9; i++) {
            if(!buttons[i].isPlayed())
                buttons[i].setEnabled(buttonsEnabled);
        }
    }

    private class ClientConnection {
        private DataOutputStream clientOutStream;
        private DataInputStream clientInStream;
        private String playerSymbol, otherPlayerSymbol;
        Socket socket;


        private void connectToServer() {
            try {
                socket = new Socket("localhost", GameServer.PORT);
                System.out.println("Connected!");

                clientOutStream = new DataOutputStream(socket.getOutputStream());
                clientInStream = new DataInputStream(socket.getInputStream());
                playerID =clientInStream.readInt();
                System.out.println("Player ID = " + playerID);
                if(playerID == 1){
                    playerSymbol = "X";
                    otherPlayerSymbol = "O";
                }
                else {
                    playerSymbol = "0";
                    otherPlayerSymbol = "X";
                }


            } catch (IOException e) {
                System.out.println("IO error on the client side !");
            }

        }

        private void closingConnection() {
            try {
                socket.close();
                System.out.println("Connection to port " + GameServer.PORT + "closed.");
            } catch (IOException e) {
                System.out.println("Cannot close connection");
            }

        }

        public void sendButtonIndex(int n) {
            try {
                clientOutStream.writeInt(n);
                clientOutStream.flush();
            } catch (IOException e) {
                System.out.println("Error in sendButtonID from player#" );
            }
        }
        public int receiveButtonIndex() {
            int n = -1;
            try {
                System.out.println("Waiting for button index from other player");
                n = clientInStream.readInt();
                buttons[n].setText(playerSymbol);
                clientBoard[n] = playerSymbol;

            } catch (IOException e) {
                System.out.println("Error from receiveButtonNum() csc");
            }
            return n;
        }

        private void setUpActionListeners(){
            for (int i = 0; i < 9; i++) {
                buttons[i].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        MyButton b1 = (MyButton) e.getSource();
                        buttons[b1.getButtonID()].setText(playerSymbol);
                        buttons[b1.getButtonID()].setPlayed(true);
                        clientBoard[b1.getButtonID()] = playerSymbol;
                        winChecker.checkWinner(buttons);
                        toggleButtons();
                        sendButtonIndex(b1.getButtonID());
                        GUIMessage.setText("You played your turn. Now wait for you opponent.");
                        Thread t = new Thread (() -> {
                            int otherPlayerPick = CC.receiveButtonIndex();
                            buttons[otherPlayerPick].setText(otherPlayerSymbol);
                            buttons[otherPlayerPick].setPlayed(true);
                            clientBoard[otherPlayerPick] = otherPlayerSymbol;
                            winChecker.checkWinner(buttons);
                            toggleButtons();
                        });
                        t.start();
                    }

                });
            }

        }

    }
    public static void main(String[] args) {
        GameClient myClient = new GameClient();
        myClient.displayGUI();


    }


}