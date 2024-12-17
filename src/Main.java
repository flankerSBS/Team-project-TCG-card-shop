//Each card has a card name and its price, combat power, stats, and border color. The price of the same card varies from
// side to side. The above can be displayed when the card name and card side type are entered. The card border color is
// divided into: black,grey,silver,gold four kinds, respectively corresponding to the card 1,2,3,4 four levels.
// Combat power determines the basic value of the card price, and the higher the combat power of the
// same level, the more expensive the price. The base value of the card price is 0.43 times the combat power.
// Cards with a higher level are up to five times more expensive, and depending on the actual situation,
// the price of the card will vary, and the price will be about 20 percent higher in February-June than in November,
// December, January, and 10 percent higher in July-October than in November, December, and January.
// The fighting power of the same card on different sides is the same.

//FUNCTION:
//1. inquire card information
//Create a card information database, enter the card name and card border type through the keyboard to query
// the corresponding card data in the card database.
//2. Draw cards
//draw a pack of cards ,which contains eight random cards among the 30 cards in the card database and the card level is
// random, when you click the card pack, the information of each of the eight cards will be displayed,
// including name, attribute, price and card side information.
// Level 1 has a 76% probability of occurrence, Level 2 has a 20% probability, Level 3 has a 3.9% probability,
// and Level 4 has a 0.1% probability. Calculate the total price of the eight cards at the end.
//Display all card name at first.
//————by Tao Ji

//It's my part—————Tao JI
import java.util.*;


//CardBorder class
class CardBorder {
    private String borderColor;
    private double multiplier;
    private int level;

    // constructor function
    public CardBorder(String borderColor, double multiplier, int level) {
        this.borderColor = borderColor;
        this.multiplier = multiplier;
        this.level = level;
    }

    public String getBorderColor() {
        return borderColor;
    }

    public double getMultiplier() {
        return multiplier;
    }

    public int getLevel() {
        return level;
    }

    // Static method, return the card edge color corresponding to the multiplier factor and grade
    public static CardBorder getBorderByColor(String color) {
        switch (color.toLowerCase()) {
            case "black":
                return new CardBorder("black", 1, 1);  // Black border: Level 1
            case "grey":
                return new CardBorder("grey", 5, 2);   // Grey border: Level 2
            case "silver":
                return new CardBorder("silver", 25, 3); // Silver border: Level 3
            case "gold":
                return new CardBorder("gold", 125, 4);  // Gold border: Level 4
            default:
                return null;
        }
    }
}

class Card {
    String name;
    String attribute;
    int attackPower;
    CardBorder borderColor;
    double basePrice;
    double finalPrice;

    // constructor function
    public Card(String name, String attribute, int attackPower, CardBorder borderColor) {
        this.name = name;
        this.attribute = attribute;
        this.attackPower = attackPower;
        this.borderColor = borderColor;
        this.basePrice = calculateBasePrice();
        this.finalPrice = calculateFinalPrice();
    }

    // the basic price of cards，battlePower * 0.43
    private double calculateBasePrice() {
        return this.attackPower * 0.43;
    }

    // Calculate the final price based on the card border's multiplication factor
    private double calculateFinalPrice() {
        double multiplier = borderColor.getMultiplier();
        return this.basePrice * multiplier;
    }

    // adjust Price For Season
    public double adjustPriceForSeason(int month) {
        double seasonalMultiplier = 1.0;
        if (month >= 2 && month <= 6) {
            seasonalMultiplier = 1.2;  // Prices will increase by 20% from February to June
        } else if (month >= 7 && month <= 10) {
            seasonalMultiplier = 1.1;  // Prices will increase by 10% from July to October
        }
        return this.finalPrice * seasonalMultiplier;
    }

    @Override
    public String toString() {
        return String.format("Card{name='%s', attribute='%s', attackPower=%d, borderColor='%s', level=%d, basePrice=%.2f, finalPrice=%.2f}",
                name, attribute, attackPower, borderColor.getBorderColor(), borderColor.getLevel(), basePrice, finalPrice);
    }

    // acquire the basic data of cards（black）
    public String getBaseData() {
        return String.format("Card{name='%s', attribute='%s', attackPower=%d, borderColor='black', level=1, basePrice=%.2f, finalPrice=%.2f}",
                name, attribute, attackPower, basePrice, basePrice);
    }

    // The price is multiplied according to the color of the card brder
    public String getPriceAfterBorderColor(CardBorder border) {
        double adjustedPrice = basePrice * border.getMultiplier();
        return String.format("Card{name='%s', attribute='%s', attackPower=%d, borderColor='%s', level=%d, basePrice=%.2f, finalPrice=%.2f}",
                name, attribute, attackPower, border.getBorderColor(), border.getLevel(), basePrice, adjustedPrice);
    }

    public double getFinalPrice() {
        return finalPrice;
    }
}

class CardDatabase {
    private List<Card> cards;

    // Initializes the card database
    public CardDatabase() {
        cards = new ArrayList<>();
        loadCards();
    }

    //Enter the card information  
    private void loadCards() {
        cards.add(new Card("Bulbasaur", "wood", 20, CardBorder.getBorderByColor("black")));
        cards.add(new Card("Ivysaur", "wood", 42, CardBorder.getBorderByColor("black")));
        cards.add(new Card("Venusaur", "wood", 76, CardBorder.getBorderByColor("black")));
        cards.add(new Card("Charmander", "fire", 32, CardBorder.getBorderByColor("black")));
        cards.add(new Card("Charmeleon", "fire", 56, CardBorder.getBorderByColor("black")));
        cards.add(new Card("Charizard", "fire", 75, CardBorder.getBorderByColor("black")));
        cards.add(new Card("Squirtle", "water", 31, CardBorder.getBorderByColor("black")));
        cards.add(new Card("Wartortle", "water", 54, CardBorder.getBorderByColor("black")));
        cards.add(new Card("Blastoise", "water", 87, CardBorder.getBorderByColor("black")));
        cards.add(new Card("Caterpie", "wood", 51, CardBorder.getBorderByColor("black")));
        cards.add(new Card("Metapod", "soil", 73, CardBorder.getBorderByColor("black")));
        cards.add(new Card("Butterfree", "wind", 58, CardBorder.getBorderByColor("black")));
        cards.add(new Card("Weedle", "soil", 84, CardBorder.getBorderByColor("black")));
        cards.add(new Card("Kakuna", "soil", 100, CardBorder.getBorderByColor("black")));
        cards.add(new Card("Beedrill", "wind", 32, CardBorder.getBorderByColor("black")));
        cards.add(new Card("Pidgey", "wind", 58, CardBorder.getBorderByColor("black")));
        cards.add(new Card("Pidgeotto", "wind", 89, CardBorder.getBorderByColor("black")));
        cards.add(new Card("Pidgeot", "wind", 35, CardBorder.getBorderByColor("black")));
        cards.add(new Card("Rattata", "soil", 36, CardBorder.getBorderByColor("black")));
        cards.add(new Card("Raticate", "soil", 80, CardBorder.getBorderByColor("black")));
        cards.add(new Card("Spearow", "wind", 49, CardBorder.getBorderByColor("black")));
        cards.add(new Card("Fearow", "wind", 99, CardBorder.getBorderByColor("black")));
        cards.add(new Card("Ekans", "soil", 39, CardBorder.getBorderByColor("black")));
        cards.add(new Card("Arbok", "soil", 34, CardBorder.getBorderByColor("black")));
        cards.add(new Card("Pikachu", "thunder", 100, CardBorder.getBorderByColor("black")));
        cards.add(new Card("Sandshrew", "soil", 43, CardBorder.getBorderByColor("black")));
        cards.add(new Card("Sandslash", "soil", 82, CardBorder.getBorderByColor("black")));
        cards.add(new Card("Nidoran", "water", 88, CardBorder.getBorderByColor("black")));
        cards.add(new Card("Clefairy", "water", 98, CardBorder.getBorderByColor("black")));
        cards.add(new Card("Ninetales", "water", 76, CardBorder.getBorderByColor("black")));
    }

    // Inquire card information, according to card name
    public Card searchCard(String name) {
        for (Card card : cards) {
            if (card.name.equalsIgnoreCase(name)) {
                return card;
            }
        }
        return null;  // No eligible cards were found
    }

    // Get the names of all cards
    public List<String> getAllCardNames() {
        List<String> cardNames = new ArrayList<>();
        for (Card card : cards) {
            cardNames.add(card.name);
        }
        return cardNames;
    }

    // Randomly generated card pack (8 cards)
    public List<Card> generatePack() {
        Random rand = new Random();
        List<Card> pack = new ArrayList<>();
        String[] borderColors = {"black", "grey", "silver", "gold"};
        for (int i = 0; i < 8; i++) {
            String randomBorderColor = getRandomBorderColor(rand);
            Card card = cards.get(rand.nextInt(cards.size()));
            //Assign the card border color randomly in the card pack
            pack.add(new Card(card.name, card.attribute, card.attackPower, CardBorder.getBorderByColor(randomBorderColor)));
        }
        return pack;
    }

    // Select the card border color according to probability
    //This is my part.————Wenchao Dai
    private String getRandomBorderColor(Random rand) {
        int randomValue = rand.nextInt(10000);  // Get a random number between 0 and 9999

        if (randomValue < 7600) {
            return "black";  // 76% for black
        } else if (randomValue < 9800) {
            return "grey";   // 20% for grey
        } else if (randomValue < 9990) {
            return "silver"; // 3.9% for silver
        } else {
            return "gold";   // 0.1% for gold
        }
    }

    // Print all cards in the card database
    public void printAllCards() {
        for (Card card : cards) {
            System.out.println(card);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CardDatabase cardDatabase = new CardDatabase();

        // Displays all card names
        //This is also my part.————Wenchao Dai
        System.out.println("All card names in the card database:");
        List<String> cardNames = cardDatabase.getAllCardNames();
        for (String cardName : cardNames) {
            System.out.println(cardName);
        }

        // Ask the user for the current month
        System.out.println("Please enter the current month (1-12) :");
        int currentMonth = scanner.nextInt();
        scanner.nextLine();  

        while (true) {
            // Provide user selection function
            System.out.println("Please select the function：");
            System.out.println("1. Inquire card information");
            System.out.println("2. Random card pack (8 cards)");
            System.out.println("3.Quit");

            int choice = scanner.nextInt();
            scanner.nextLine();  

            if (choice == 1) {
                // Inquire card information
                System.out.println("Please enter the card name to inquire:");
                String cardName = scanner.nextLine();

                // Inquire for cards
                Card card = cardDatabase.searchCard(cardName);
                if (card != null) {
                    // Display base value
                    System.out.println("Base value (black card border) :" + card.getBaseData());

                    // Enter the card border color and query
                    System.out.println("Please enter the card edge color（black, grey, silver, gold）：");
                    String borderColor = scanner.nextLine();
                    CardBorder border = CardBorder.getBorderByColor(borderColor);

                    if (border != null) {
                        System.out.println("The card data is queried：");
                        // Show the result of multiplying the card according to the color of the card border
                        System.out.println(card.getPriceAfterBorderColor(border));
                    } else {
                        System.out.println("Invalid card edge color!");
                    }
                } else {
                    System.out.println("This card is not found!");
                }
            } else if (choice == 2) {
                // Random card pack
                System.out.println("Generate a random card pack (containing 8 cards) :");
                List<Card> pack = cardDatabase.generatePack();
                double totalPrice = 0;
                for (Card card : pack) {
                    System.out.println(card);
                    totalPrice += card.getFinalPrice();
                }
                System.out.println("The total price of the eight cards is : $" + totalPrice);
            } else if (choice == 3) {
                // quit
                System.out.println("Quit");
                break;
            } else {
                System.out.println("Invalid selection, please re-enter!");
            }
        }

        scanner.close();
    }
}
