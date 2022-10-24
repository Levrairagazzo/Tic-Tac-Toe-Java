import GUI.Player;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class GameClient extends JFrame {
    private final int WIDTH = 1000;
    private final int HEIGHT = 600;
    private OutputStream outStream;
    private InputStream inStream;
    Socket socket;
    JPanel mainPanel;
    JButton[] buttons;
    JTextArea message;


    public static void main(String[] args) {
        GameClient myClient = new GameClient();
    }

    public GameClient() {
        connectToServer();
        displayGUI();
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


    private void connectToServer() {
        try {
            socket = new Socket("localhost", GameServer.PORT);
            System.out.println("Connected!");

            outStream = socket.getOutputStream();
            inStream = socket.getInputStream();

            System.out.println("Closing socket and terminating program.");
            socket.close();
        } catch (IOException e) {
            System.out.println("IO error on the client side !");
        }

    }


}


