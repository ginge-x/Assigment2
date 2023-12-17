import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class HighScoreManager {
    private static Score myScore = new Score(0);
    public static String HIGH_SCORE_FILE = "highscores.txt";
    private static Scanner scanner = new Scanner(System.in);
    private static final int HIGH_SCORE_TABLE_SIZE = 5;
    public ArrayList<HighScoreEntry> highScoreTable = new ArrayList<>();

    public void loadHighScoreTable(){
        try (BufferedReader reader = new BufferedReader(new FileReader(HIGH_SCORE_FILE))){
            String line;
            while ((line = reader.readLine()) != null){
                String[] parts = line.split(",");
                if (parts.length == 2){
                    String name = parts[0].trim();
                    int score = Integer.parseInt(parts[1].trim());
                    highScoreTable.add(new HighScoreEntry(name, score));
                }
            }
        }catch (IOException | NumberFormatException e){
            System.out.println("Error loading high scores. Starting with an empty table.");
        }
    }

    public void saveHighScoreTable(){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(HIGH_SCORE_FILE))){
            for (HighScoreEntry entry : highScoreTable){
                writer.write(entry.getName() + "," + entry.getScore());
                writer.newLine();
            }
        }catch (IOException e){
            System.out.println("Error saving high scores.");
        }
    }

    public void updateHighScoreTable(int newScore) {
        String playerName = null;
        if (newScore >= highScoreTable.get(HIGH_SCORE_TABLE_SIZE -1).getScore()) {
            System.out.print("Enter your name: ");
            playerName = scanner.next();
            System.out.println("Congratulations!, " + playerName + "! You have made it to the high score table");
        }
        HighScoreEntry newEntry = new HighScoreEntry(playerName, newScore);
        highScoreTable.add(newEntry);
        Collections.sort(highScoreTable, Comparator.reverseOrder());

        if (highScoreTable.size() > HIGH_SCORE_TABLE_SIZE) {
            highScoreTable.subList(HIGH_SCORE_TABLE_SIZE, highScoreTable.size()).clear();
        }

    }

    public HighScoreManager(Scanner scanner){
        HighScoreManager.scanner = scanner;
    }
    void addHighScoreEntry(int newScore){
        System.out.println("Congratulations! You have made it to the highscore table!");
        System.out.print("Enter your name: ");
        String playerName = scanner.next();

        HighScoreEntry newEntry = new HighScoreEntry(playerName, newScore);
        highScoreTable.add(newEntry);
    }

    public void displayHighScoreTable(){
        if (highScoreTable.isEmpty()){
            System.out.println("No high scores yet");
        }else {
            for (int i = 0; i < highScoreTable.size(); i++){
                HighScoreEntry entry = highScoreTable.get(i);
                System.out.println((i + 1) + ". " + entry.getName() + " - " + entry.getScore());
            }
        }
        System.out.println();
    }

    static class HighScoreEntry implements Comparable<HighScoreEntry>{

        private String name;
        private int score;

        public HighScoreEntry(String name, int score){
            this.name = name;
            this.score = score;
        }

        public String getName(){
            return name;
        }

        public int getScore(){
            return score;
        }

        public int compareTo(HighScoreEntry other){
            return Integer.compare(this.score, other.score);
        }
    }
}
