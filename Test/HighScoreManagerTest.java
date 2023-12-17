import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

public class HighScoreManagerTest extends HighScoreManager {

    private HighScoreManager highScoreManager;

    public HighScoreManagerTest(Scanner scanner) {
        super(scanner);
    }

    @BeforeEach
    public void setUp(){
        String testInput = "TestPlayer";
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(testInput.getBytes());
        Scanner testScanner = new Scanner(byteArrayInputStream);

        highScoreManager = new HighScoreManager(testScanner);
        highScoreManager.loadHighScoreTable();
    }

    @Test
    public void testUpdatedHighScoreTable() {
        int existingScore = 90;
        String playerName = "Test";
        highScoreManager.updateHighScoreTable(existingScore);
        highScoreManager.saveHighScoreTable();

        HighScoreManager loadedHighScoreManager = new HighScoreManager(new Scanner(System.in));
        loadedHighScoreManager.loadHighScoreTable();

        boolean containsExistingScore = loadedHighScoreManager.highScoreTable.stream().anyMatch
                (highScoreEntry -> highScoreEntry.getScore() == existingScore);

        assertTrue(containsExistingScore);

        int newScore = 100;
        highScoreManager.updateHighScoreTable(newScore);
        highScoreManager.saveHighScoreTable();

        loadedHighScoreManager.loadHighScoreTable();

        boolean containsNewScore = loadedHighScoreManager.highScoreTable.stream().anyMatch
                (highScoreEntry -> highScoreEntry.getScore() == newScore);

        assertTrue(containsNewScore);
    }

    @Test
    public void testDisplayHighScoreTable(){
        assertDoesNotThrow(() -> highScoreManager.displayHighScoreTable());
    }

    @Test
    public void testAddHighScoreEntry() {
        int testScore = 50;
        String testName = "TestPlayer";
        assertNotNull(highScoreManager);
        highScoreManager.addHighScoreEntry(testScore);

        boolean containsTestEntry = highScoreManager.highScoreTable.stream().anyMatch(highScoreEntry -> highScoreEntry.getName().equals(testName) && highScoreEntry.getScore() == testScore);

        assertTrue(containsTestEntry);
    }
}