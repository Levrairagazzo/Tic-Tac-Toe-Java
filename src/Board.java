public class Board {
    char[][] myBoard;


    public Board() {
        this.myBoard = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.myBoard[i][j] = '-';
            }

        }

    }

    public void printBoard(){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(this.myBoard[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void updateBoard(int row, int column){
        myBoard[row][column] = 'X';

    }


    public boolean didSomebodyWin() {
        //horizontal
        for (int i = 0; i < 3; i++) {
            if (myBoard[i][0] == myBoard[i][1] && myBoard[i][1] == myBoard[i][2] && myBoard[i][2] == myBoard[i][0] &&
                    myBoard[i][0] != '-') {
                return true;
            }
        }
        //vertical
        for (int j = 0; j < 3; j++) {
            if (myBoard[0][j] == myBoard[1][j] && myBoard[1][j] == myBoard[2][j] && myBoard[2][j] == myBoard[0][j] &&
                    myBoard[0][j] != '-') {
                return true;
            }
        }

        //Diagonals
        if (myBoard[0][0] == myBoard[1][1] && myBoard[1][1] == myBoard[2][2] && myBoard[0][0] == myBoard[2][2] && myBoard[2][2] != '-') {
            return true;
        }
        if (myBoard[2][0] == myBoard[1][1] && myBoard[1][1] == myBoard[0][2] && myBoard[2][0] == myBoard[0][2] && myBoard[0][2] != '-') {
            return true;
        }
        return false;

    }
}
