import java.util.Random;
public class Card {
    private final int rank;
    private final int suit;

    private static final String[] ranks = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "King", "Queen"};
    private static final String[] suits = {"Clubs", "Diamonds", "Hearts", "Spades"};

    public Card(){
        Random random = new Random();
        this.rank = random.nextInt(Card.ranks.length) % Card.ranks.length;
        this.suit = random.nextInt(Card.suits.length);
    }

    public Card(int rank, int suit){
        this.rank = rank;
        this.suit = suit;
    }

    public String getRank(){
        return Card.ranks[this.rank];
    }

//    public String getRank(){
//        if (this.rank < 0 || this.rank >= ranks.length){
//            System.out.println("Invalid rank value: " + this.rank);
//            return "Invalid Rank";
//        }
//        return Card.ranks[this.rank];
//    }
    public String getSuit(){
        return Card.suits[this.suit];
    }

    public int getRankValue(){
        int validRank = this.rank % ranks.length;

        if (validRank >= 0 && validRank <= 9){
            return validRank + 1;
        }else{
            return 10;
        }
    }

    public String toString(){
        return getRank() + " of " + getSuit();
    }


}
