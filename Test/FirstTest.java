import org.junit.Assert;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;


import java.util.ArrayList;

public class FirstTest {
    @Test
    public void testMake11MoveSameSuit(){
        Deck deck = new Deck();
        ArrayList<Card> myHand = deck.dealHand(5);
        Score myScore = new Score(0);

        myHand.set(0, new Card(5, 2));
        Card computerCard = new Card(5,2);

        boolean result = Make11Game.make11Move(myHand, computerCard, deck, myScore);

        assertTrue(result, "the move should be successful");
        assertEquals(1, myScore.getValue(), "One point should be scored");
        assertEquals(computerCard, myHand.get(0), "Players card should be replaced with the computers card");
    }

}
