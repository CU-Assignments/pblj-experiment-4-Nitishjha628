import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

class Card {
    private String symbol;
    private String value;
    public Card(String symbol, String value) {
        this.symbol = symbol;
        this.value = value;
    }
    public String getSymbol() {
        return symbol;
    }
    public String getValue() {
        return value;
    }
    public String toString() {
        return symbol + " " + value;
    }
}
public class CardCol {
    public static void main(String[] args) {
        Collection<Card> cards = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        String[] symbols = {"Hearts", "Diamonds", "Clubs", "Spades"};
        String[] values = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen", "king"};

        for (String symbol : symbols) {
            for (String value : values) {
                cards.add(new Card(symbol, value));
            }
        }
        cards.add(new Card("Joker", "1"));
        cards.add(new Card("Joker", "2"));
        System.out.println("Only 2 Jokers in the deck.");
        System.out.print("Enter the symbol to search for (e.g., Hearts, Diamonds, Clubs, Spades, Joker): ");
        String searchSymbol = scanner.nextLine();
        System.out.println("Cards with symbol " + searchSymbol + ":");
        boolean found = false;
        for (Card card : cards) {
            if (card.getSymbol().equalsIgnoreCase(searchSymbol)) {
                System.out.println(card);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No cards found with symbol " + searchSymbol);
        }
        scanner.close();
    }
}