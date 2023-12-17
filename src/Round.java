import java.util.ArrayList;

public class Round {
    private int roundNumber;
    private ArrayList<Card> playerHand;
    private Card computerCard;
    private Card cardPlayedByPlayer;
    private boolean make11Successful;

    public Round(int roundNumber, ArrayList<Card> playerHand, Card computerCard) {
        this.roundNumber = roundNumber;
        this.playerHand = playerHand;
        this.computerCard = computerCard;
    }

    public int getRoundNumber() {
        return roundNumber;
    }

    public ArrayList<Card> getPlayerHand() {
        return playerHand;
    }

    public Card getComputerCard() {
        return computerCard;
    }

    public Card getCardPlayedByPlayer() {
        return cardPlayedByPlayer;
    }

    public void setCardPlayedByPlayer(Card cardPlayedByPlayer) {
        this.cardPlayedByPlayer = cardPlayedByPlayer;
    }

    public boolean isMake11Successful() {
        return make11Successful;
    }

    public void setMake11Successful(boolean make11Successful) {
        this.make11Successful = make11Successful;
    }
}
