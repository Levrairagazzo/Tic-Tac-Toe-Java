import GUI.Player;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class GameClient extends JFrame {
    private final int WIDTH = 1000;
    private final int HEIGHT = 600;

    JPanel mainPanel;
    JButton[] buttons;
    JTextArea message;
    private int numPlayer;
    ClientConnection CC;

    public GameClient() {
        CC = new ClientConnection();
        CC.connectToServer();
//        displayGUI();
    }


    private void displayGUI() {

        setSize(WIDTH, HEIGHT);
        setTitle("Sydney's Tic Tac Toe " + "\uD83D\uDE00");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));

        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(4, 3));
        message = new JTextArea("this is the text area");
        message.setEditable(false);

        addButtons();
        mainPanel.add(message);
        add(mainPanel);
        pack();

    }

    private void addButtons() {
        buttons = new JButton[9];
        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton("i");
            mainPanel.add(buttons[i]);

        }
    }

    private class ClientConnection {
        private DataOutputStream clientOutStream;
        private DataInputStream clientInStream;
        Socket socket;

        private void connectToServer() {
            try {
                socket = new Socket("localhost", GameServer.PORT);
                System.out.println("Connected!");

                clientOutStream = new DataOutputStream(socket.getOutputStream());
                clientInStream = new DataInputStream(socket.getInputStream());
                while (true) {
                    int myint = clientInStream.readInt();
                    if (myint == 0) break;
                    System.out.println(myint);
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


    }


    public static void main(String[] args) {
        GameClient myClient = new GameClient();
    }


}