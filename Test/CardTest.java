import org.junit.Test;
import static org.junit.Assert.*;
public class CardTest {

    @Test
    public void testDefaultConstructor(){
        Card card = new Card();
        assertNotNull(card.getRank());
        assertNotNull(card.getSuit());
        assertTrue(card.getRankValue() >= 1 && card.getRankValue() <= 10);
    }

    @Test
    public void testParameterizedConstructors(){
        Card card = new Card(3, 2);
        assertEquals("4", card.getRank());
        assertEquals("Hearts", card.getSuit());
        assertEquals(4, card.getRankValue());
    }

    @Test
    public void testToString(){
        Card card = new Card(11, 3);
        assertEquals("King of Spades", card.toString());
    }

    @Test
    public void testGetRankValue(){
        Card numericCard = new Card(7, 1);
        assertEquals(8, numericCard.getRankValue());

        Card faceCard = new Card(10, 2);
        assertEquals(10, faceCard.getRankValue());
    }
}
