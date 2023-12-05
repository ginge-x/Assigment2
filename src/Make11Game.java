import java.util.ArrayList;
import java.util.Scanner;


public class Make11Game {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args){
        while (true){
            playGame(scanner);

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

    private static void playGame(Scanner scanner){
        //Initialise the deck and deal a 5 card hand
        Deck deck = new Deck();
        ArrayList<Card> myHand = deck.dealHand(5);

        //display my hand
        System.out.println("My Hand:");
        for (int i = 0; i < myHand.size(); i++){
            System.out.println((char)('A' + i) + ". " + myHand.get(i));
        }

        //Game Loop
        Score myScore = new Score(0);

        while (!myHand.isEmpty() && (deck.cardsRemaining() > 0 || make11Possible(myHand))){
            Card computerCard = deck.deal();
            System.out.println("Computer's card: " + computerCard);

            if (!make11Move(myHand, computerCard, deck, myScore)){
                break;
            }

            System.out.println("\nUpdated Hand:");
            for (Card card : myHand){
                System.out.println(card);
            }
            System.out.println("Total Score: " + myScore.getValue());
        }
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

    private static boolean make11Move(ArrayList<Card> myHand, Card computerCard, Deck deck, Score myScore){
        System.out.println("Select a card from your hand to Make 11 (A-E)");
        char selectedCardLetter = scanner.next().toUpperCase().charAt(0);

        if (selectedCardLetter >= 'A' && selectedCardLetter <= 'E'){
            int selectedCardIndex = selectedCardLetter - 'A';
            Card selectedCard = myHand.get(selectedCardIndex);

            if (selectedCard.getRankValue() + computerCard.getRankValue() == 11){
                System.out.println("Made 11! +1 point.");

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
                    myHand.set(selectedCardIndex, computerCard);
                }
                myScore.increment();
            } else if (selectedCard.getSuit().equals(computerCard.getSuit())) {
                System.out.println("Same suit played. No point scored.");
                myHand.set(selectedCardIndex, deck.deal());

                if (isPictureCard(selectedCard)) {
                    myHand.set(selectedCardIndex, deck.deal());
                }
            }else {
                System.out.println("Make 11 not achieved. No points scored");
                return false;
            }
            return true;
        }else {
            System.out.println("Invalid card selection. Please choose a letter (A-E)");
            return true;
        }
    }

    private static void replacePictureCards(ArrayList<Card> myHand, Deck deck){
        for (int i = 0; i < myHand.size(); i++){
            Card currentCard = myHand.get(i);
            if (isPictureCard(currentCard)){
                myHand.set(i, deck.deal());
            }
        }
    }

    private static boolean isPictureCard(Card card){
        String rank = card.getRank();
        return rank.equals("Jack") || rank.equals("Queen") || rank.equals("King");
    }
}
