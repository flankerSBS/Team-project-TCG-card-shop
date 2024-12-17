//Each card has a card name and its price, combat power, stats, and edge color. The price of the same card varies from
// side to side. The above can be displayed when the card name and card side type are entered. The card edge color is
// divided into: black,grey,silver,gold four kinds, respectively corresponding to the card 1,2,3,4 four levels.
// Combat effectiveness determines the basic value of the card price, and the higher the combat effectiveness of the
// same level, the more expensive the price. The base value of the card price is 0.43 times the combat power.
// Cards with a higher level are up to five times more expensive, and depending on the actual situation,
// the price of the card will vary, and the price will be about 20 percent higher in February-June than in November,
// December, January, and 10 percent higher in July-October than in November, December, and January.
// The fighting power of the same card on different sides is the same.

//FUNCTION:
//1. inquire card information
//Create a card information database, enter the card name and card side type through the keyboard to query
// the corresponding card data in the card database.
//2. Draw cards
//draw a bag of cards ,which contains eight random cards among the 30 cards in the card database and the card level is
// random, when you click the card pack, the information of each of the eight cards will be displayed,
// including name, attribute, price and card side information.
// Level 1 has a 76% probability of occurrence, Level 2 has a 20% probability, Level 3 has a 3.9% probability,
// and Level 4 has a 0.1% probability. Calculate the total price of the eight cards at the end.


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

    // 根据卡边的乘算系数计算最终价格
    private double calculateFinalPrice() {
        double multiplier = borderColor.getMultiplier();
        return this.basePrice * multiplier;
    }

    // adjust Price For Season
    public double adjustPriceForSeason(int month) {
        double seasonalMultiplier = 1.0;
        if (month >= 2 && month <= 6) {
            seasonalMultiplier = 1.2;  // 2月到6月价格提高20%
        } else if (month >= 7 && month <= 10) {
            seasonalMultiplier = 1.1;  // 7月到10月价格提高10%
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

    // 根据卡边颜色进行乘算后的价格
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

    // 初始化卡牌数据库
    public CardDatabase() {
        cards = new ArrayList<>();
        loadCards();
    }

    // 录入卡牌信息
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

    // 查询卡牌信息，根据卡牌名称
    public Card searchCard(String name) {
        for (Card card : cards) {
            if (card.name.equalsIgnoreCase(name)) {
                return card;
            }
        }
        return null;  // 没有找到符合条件的卡牌
    }

    // 获取所有卡牌的名称
    public List<String> getAllCardNames() {
        List<String> cardNames = new ArrayList<>();
        for (Card card : cards) {
            cardNames.add(card.name);
        }
        return cardNames;
    }

    // 随机生成卡包（8张卡）
    public List<Card> generatePack() {
        Random rand = new Random();
        List<Card> pack = new ArrayList<>();
        String[] borderColors = {"black", "grey", "silver", "gold"};
        for (int i = 0; i < 8; i++) {
            String randomBorderColor = getRandomBorderColor(rand);
            Card card = cards.get(rand.nextInt(cards.size()));
            // 在卡包中随机分配卡边颜色
            pack.add(new Card(card.name, card.attribute, card.attackPower, CardBorder.getBorderByColor(randomBorderColor)));
        }
        return pack;
    }

    // 根据概率选择卡边颜色
    private String getRandomBorderColor(Random rand) {
        int randomValue = rand.nextInt(10000);  // 获取一个0到9999之间的随机数

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

    // 打印卡牌数据库中的所有卡
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

        // 显示所有卡牌名称
        System.out.println("卡牌数据库中的所有卡牌名称：");
        List<String> cardNames = cardDatabase.getAllCardNames();
        for (String cardName : cardNames) {
            System.out.println(cardName);
        }

        // 询问用户当前月份
        System.out.println("请输入当前月份（1-12）：");
        int currentMonth = scanner.nextInt();
        scanner.nextLine();  // 吸收换行符

        while (true) {
            // 提供用户选择功能
            System.out.println("请选择功能：");
            System.out.println("1. 查询卡牌信息");
            System.out.println("2. 随机抽卡包（8张卡）");
            System.out.println("3. 退出");

            int choice = scanner.nextInt();
            scanner.nextLine();  // 吸收换行符

            if (choice == 1) {
                // 查询卡牌信息
                System.out.println("请输入卡牌名称查询：");
                String cardName = scanner.nextLine();

                // 查询卡牌
                Card card = cardDatabase.searchCard(cardName);
                if (card != null) {
                    // 显示无卡边颜色（基础数值）
                    System.out.println("基础数值（黑色卡边）：" + card.getBaseData());

                    // 输入卡边颜色并查询
                    System.out.println("请输入卡边颜色（black, grey, silver, gold）：");
                    String borderColor = scanner.nextLine();
                    CardBorder border = CardBorder.getBorderByColor(borderColor);

                    if (border != null) {
                        System.out.println("查询到的卡牌数据：");
                        // 显示卡牌根据卡边颜色乘算后的结果
                        System.out.println(card.getPriceAfterBorderColor(border));
                    } else {
                        System.out.println("无效的卡边颜色！");
                    }
                } else {
                    System.out.println("未找到该卡牌！");
                }
            } else if (choice == 2) {
                // 随机抽卡包
                System.out.println("生成一个随机卡包（包含8张卡）：");
                List<Card> pack = cardDatabase.generatePack();
                double totalPrice = 0;
                for (Card card : pack) {
                    System.out.println(card);
                    totalPrice += card.getFinalPrice();
                }
                System.out.println("八张卡牌的总价格为: ￥" + totalPrice);
            } else if (choice == 3) {
                // 退出程序
                System.out.println("退出程序");
                break;
            } else {
                System.out.println("无效选择，请重新输入！");
            }
        }

        scanner.close();
    }
}
