import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class Make11Game {

    private static ArrayList<Round> roundsHistory = new ArrayList<>();
    private static Score myScore = new Score(0);
    private static Scanner scanner = new Scanner(System.in);
    private static int roundNumber = 1;
    private static HighScoreManager highScoreManager = new HighScoreManager(scanner);

    public static void main(String[] args){
        highScoreManager.loadHighScoreTable();


        while (true){
            System.out.println("-------------------------");
            System.out.println("--- WELCOME TO MAKE11 ---");
            System.out.println("-------------------------");

            System.out.println("HIGH SCORES");
            System.out.println("-------------------------");
            displayHighScoreTable();

            playGame(scanner);
            roundNumber = 1;
            if (myScore.getValue() > 0){
                highScoreManager.updateHighScoreTable(myScore.getValue());
                highScoreManager.saveHighScoreTable();
                displayHighScoreTable();
            }
            displayReplay();

            System.out.print("Do you want to start a new game? (yes/no): ");
            String playAgain = scanner.next().toLowerCase();
            System.out.println();

            if (!playAgain.equals("yes")){
                System.out.println("Thanks for playing!");
                break;
            }
        }

        scanner.close();
    }

    private static void playGame(Scanner scanner) {
        //Initialise the deck and deal a 5 card hand
        System.out.println("-------Round " + roundNumber + "-------");
        Deck deck = new Deck();
        ArrayList<Card> myHand = deck.dealHand(5);

        //display my hand
        System.out.println("My Hand:");
        for (int i = 0; i < myHand.size(); i++) {
            System.out.println((char) ('A' + i) + ". " + myHand.get(i));
        }

        //Game Loop
        Score myScore = new Score(0);

        Card computerCard = new Card();
        while (!myHand.isEmpty() && (deck.cardsRemaining() > 0 || make11Possible(myHand))) {
            computerCard = deck.deal();
            System.out.println("\nComputer's deals the : " + computerCard);

            Round round = new Round(roundNumber, new ArrayList<>(myHand), computerCard);
            displayRoundInfo(round);

            if (!make11Move(myHand, computerCard, deck, myScore, round)) {
                break;
            }
            roundNumber++;
            System.out.print("\n-------Round " + roundNumber + "-------");
            System.out.println("\nUpdated Hand:");
            for (int i = 0; i < myHand.size(); i++) {
                System.out.println((char) ('A' + i) + ". " + myHand.get(i));
            }
            roundsHistory.add(round);
            System.out.println("\nTotal Score: " + myScore.getValue());
        }
        Round finalRound = new Round(roundNumber, new ArrayList<>(myHand), computerCard);
        roundsHistory.add(finalRound);

        highScoreManager.updateHighScoreTable(myScore.getValue());
        highScoreManager.saveHighScoreTable();
        System.out.println("Game over. Final Score: " + myScore.getValue());
    }
    private static boolean make11Possible(ArrayList<Card> hand){
        for (int i = 0; i < hand.size(); i++){
            for (int j = i + 1; j < hand.size(); j++){
                if (hand.get(i).getRankValue() + hand.get(j).getRankValue() == 11){
                    return true;
                }
            }
        }
        return false;
    }

    private static void displayRoundInfo(Round round){
        round.getRoundNumber();
        round.getPlayerHand();
        round.getComputerCard();
    }

    public static boolean make11Move(ArrayList<Card> myHand, Card computerCard, Deck deck, Score myScore, Round round){
        System.out.println("Select a card from your hand to Make 11 (A-E)");
        char selectedCardLetter = scanner.next().toUpperCase().charAt(0);

        if (selectedCardLetter >= 'A' && selectedCardLetter <= 'E'){
            int selectedCardIndex = selectedCardLetter - 'A';
            Card selectedCard = myHand.get(selectedCardIndex);

            if (selectedCard.getRankValue() + computerCard.getRankValue() == 11){
                System.out.println("Made 11! +1 point.");
                round.setMake11Successful(true);
                round.setCardPlayedByPlayer(selectedCard);

                boolean containsPictureCard = myHand.stream().anyMatch(card ->
                        card.getRank().equals("Jack") ||
                        card.getRank().equals("Queen") ||
                        card.getRank().equals("King"));

                if (containsPictureCard){
                    System.out.print("Do you want to replace a picture card? (yes/no): ");
                    String replacePictureCard = scanner.next().toLowerCase();

                    if (replacePictureCard.equals("yes")){
                        replacePictureCards(myHand, deck);
                    }
                }

                if (isPictureCard(selectedCard)){
                    myHand.set(selectedCardIndex, deck.deal());
                }else {
                    myHand.set(selectedCardIndex, deck.deal());
                }
                myScore.increment();
            } else if (selectedCard.getSuit().equals(computerCard.getSuit())) {
                System.out.println("Same suit played. No point scored.");
                round.setCardPlayedByPlayer(selectedCard);
                myHand.set(selectedCardIndex, deck.deal());

                if (isPictureCard(selectedCard)) {
                    myHand.set(selectedCardIndex, deck.deal());
                }
            }else {
                System.out.println("Make 11 not achieved. No points scored");
                round.setMake11Successful(false);
                return false;
            }
            return true;
        }else {
            System.out.println("Invalid card selection. Please choose a letter (A-E)");
            return true;
        }
    }

    private static void replacePictureCards(ArrayList<Card> myHand, Deck deck){
        System.out.print("Choose a picture card to replace (A-E):");
        char selectedCardLetter = scanner.next().toUpperCase().charAt(0);

        if (selectedCardLetter >= 'A' && selectedCardLetter <= 'E'){
            int selectedCardIndex = selectedCardLetter - 'A';
            Card selectedCard = myHand.get(selectedCardIndex);

            if (isPictureCard(selectedCard)){
                myHand.set(selectedCardIndex, deck.deal());
                System.out.println("Picture card has been replaced");
            }else {
                System.out.println("Selected card is not a picture card. No replacement");
            }
        }else {
            System.out.println("Invalid card selection. Please choose a letter (A-E). No replacement has been made");
        }
    }

    private static boolean isPictureCard(Card card){
        String rank = card.getRank();
        return rank.equals("Jack") || rank.equals("Queen") || rank.equals("King");
    }

    private static void displayHighScoreTable(){
        highScoreManager.displayHighScoreTable();
    }

    private static void displayReplay(){
        for (Round round : roundsHistory){
            System.out.println("\n--------------------------");
            System.out.println("Replay for Round " + round.getRoundNumber());
            System.out.println("Player's Hand: " + round.getPlayerHand());
            System.out.println("Computer's Card: " + round.getComputerCard());
            System.out.println("Card Played by Player: " + round.getCardPlayedByPlayer());
            System.out.println("Make 11 Successful: " + round.isMake11Successful());
            System.out.println("--------------------------");
        }
    }
}
