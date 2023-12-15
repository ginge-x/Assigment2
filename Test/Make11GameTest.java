import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;

import static org.junit.Assert.*;
public class Make11GameTest {
    private Make11Game make11Game;
    private ByteArrayInputStream inputStream;
    private ByteArrayOutputStream outputStream;

    @Before
    public void setUp() {
        make11Game = new Make11Game();
        File testFile = new File(HighScoreManager.HIGH_SCORE_FILE);
        if (testFile.exists()) {
            assertTrue(testFile.delete());
        }

        // Redirect System.in and System.out to capture input and output
        inputStream = new ByteArrayInputStream("yes\nno\n".getBytes());
        outputStream = new ByteArrayOutputStream();
        System.setIn(inputStream);
        System.setOut(new PrintStream(outputStream));
    }

    @After
    public void tearDown() {
        File testFile = new File(HighScoreManager.HIGH_SCORE_FILE);
        if (testFile.exists()) {
            assertTrue(testFile.delete());
        }

        // Reset System.in and System.out
        System.setIn(System.in);
        System.setOut(System.out);
    }

    @Test
    public void testGameFlow(){
        inputStream = new ByteArrayInputStream("yes\nA\nno\n".getBytes());
        System.setIn(inputStream);
        Make11Game.main(null);
        assertTrue(outputStream.toString().contains("HIGH SCORES"));
        assertTrue(outputStream.toString().contains("Do you want to start a new game? (yes/no): "));
        assertTrue(outputStream.toString().contains("Thanks for playing!"));
    }
}
