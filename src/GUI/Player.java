package GUI;

public class Player {
    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    String symbol;
    public Player(String symbol){
        setSymbol(symbol);
    }
}
