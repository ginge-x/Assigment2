import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;
public class RoundTest {
    @Test
    public void testCardPlayedByPlayer(){
        Round round = new Round(2, generateSampleHand(), generateSampleComputerCard());
        Card cardPlayedByPlayer = generateSampleHand().get(0);
        round.setCardPlayedByPlayer(cardPlayedByPlayer);
        assertEquals(cardPlayedByPlayer, round.getCardPlayedByPlayer());
    }

    private Card generateSampleComputerCard() {
        // Replace with actual logic to generate a sample computer card
        return new Card(1, 1);
    }

    private ArrayList<Card> generateSampleHand() {
        // Replace with actual logic to generate a sample hand
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(new Card(3, 3));
        hand.add(new Card(2, 2));
        return hand;
    }
}
