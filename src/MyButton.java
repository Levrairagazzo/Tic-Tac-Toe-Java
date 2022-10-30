import javax.swing.*;

public class MyButton extends JButton {
    private final int buttonID;
    private boolean played;

    public MyButton(int buttonID, String buttonValue){
        this.buttonID = buttonID;
        this.played = false;
    }

    public boolean isPlayed() {
        return played;
    }

    public void setPlayed(boolean played) {
        this.played = played;
        setEnabled(false);
    }

    public int getButtonID(){
        return buttonID;
    }

}
