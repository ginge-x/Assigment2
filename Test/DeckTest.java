import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;
public class DeckTest {

    @Test
    public void testDeckInitialization(){
        Deck deck = new Deck();
        assertEquals(52, deck.cardsRemaining());
    }

    @Test
    public void testDeal(){
        Deck deck = new Deck();
        assertEquals(52, deck.cardsRemaining());
    }

    @Test
    public void testDealHand(){
       Deck deck = new Deck();
       int numCards = 5;
        ArrayList<Card> hand = deck.dealHand(numCards);
        assertNotNull(hand);
        assertEquals(numCards, hand.size());
        assertEquals(52 - numCards, deck.cardsRemaining());
    }

    @Test
    public void testDeckShuffling(){
        Deck deck1 = new Deck();
        Deck deck2 = new Deck();
        assertNotEquals(deck1.toString(), deck2.toString());
    }

}
