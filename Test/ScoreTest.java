import org.junit.Test;
import static org.junit.Assert.*;
public class ScoreTest {

    @Test
    public void testInitialValue(){
        Score score = new Score(5);
        assertEquals(5, score.getValue());
    }

    @Test
    public void testIncrement(){
        Score score = new Score(0);
        score.increment();
        assertEquals(1, score.getValue());

        score.increment();
        assertEquals(2, score.getValue());
    }

    @Test
    public void testIncrementWithInitialValue(){
        Score score = new Score(10);
        score.increment();
        assertEquals(11, score.getValue());
    }

    @Test
    public void testMultipleIncrements(){
        Score score = new Score(3);
        score.increment();
        score.increment();
        score.increment();
        assertEquals(6, score.getValue());
    }
}
