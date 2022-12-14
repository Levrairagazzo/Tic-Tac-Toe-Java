import java.io.DataInputStream;
import java.io.InputStream;
import java.util.Scanner;

public class Game {
    Player p1;
    Player p2;
    boolean stillGoing;
    Board gameBoard;
    int numberOfTurn = 0;
    Scanner myScanner = new Scanner(System.in);

    public Game(Player player1, Player player2, Board gameBoard) {
        p1 = player1;
        p2 = player2;
        this.gameBoard = gameBoard;
    }

    public Game(DataInputStream dataInput) {
        this.gameBoard = new Board();
        this.stillGoing = false;
    }

//    public void run() {
//        stillGoing = true;
//        System.out.println("The game starts!");
//        System.out.println("Initial Board:");
//        gameBoard.printBoard();
//        String userInput = "";
//        int inputRow;
//        int inputColumn;
//        while (isStillGoing()) {
//            System.out.println("Pick a row");
//            inputRow = Integer.parseInt(myScanner.nextLine());
//            System.out.println("You picked row: " + inputRow);
//            System.out.println("Pick a column");
//            inputColumn = Integer.parseInt(myScanner.nextLine());
//            System.out.println("You picked row: " + inputColumn);
//            if(!validInput(inputRow,inputColumn)){
//                gameBoard.updateBoard(inputRow, inputColumn);
//                gameBoard.printBoard();
//                System.out.println("-----------------------------");
//                numberOfTurn++;
//            }
//
//
//
//
//        }
//    }



    public boolean isStillGoing(){
        return (numberOfTurn < 8) && stillGoing;
    }

    public boolean validInput(int rowValue, int columnValue) {
        return columnValue < 1 || columnValue > 3 || rowValue < 1 || rowValue > 3;
    };




}
