import java.util.*;

/**
 *
 * @author Roner
 */
public class GameEngine {

    static Player player; // Player
    static Dealer dealer; // Dealer
    static Wallet wallet; // Wallet

    public void InitializeReferences(Player player, Dealer dealer, Wallet wallet) {
        GameEngine.player = player;
        GameEngine.dealer = dealer;
        GameEngine.wallet = wallet;
    }

    //List that contains 4 deck of cards, picks one at random whenever dealing card.
    private final static List<Integer> deck = Arrays.asList(
            2, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 4, 5,
            5, 5, 5, 6, 6, 6, 6, 7, 7, 7, 7, 8, 8,
            8, 8, 9, 9, 9, 9, 10, 10, 10, 10, 11);

    private static int nextCard = 0;

    //Shuffles the list with the deck of cards so whenever dealer draws a card its a random one.
    public void shuffleCardList() {
        Collections.shuffle(deck);
        nextCard = 0;
    }

    //Uses this method so the deal card method doesn't deal the same card everytime.
    Integer nextCard() {
        return deck.get(nextCard++);
    }

    //When the game ends player gets the option to play again.
    //this method is the last one to get called once a round is finished.
    public void newGame() {
        Scanner s = new Scanner(System.in);
        wallet.writeMoneyToFile();
        System.out.println("Would you like to play again? 'yes' or 'no'");
        String yesorno = s.nextLine().toLowerCase().replaceAll(" ", "");
        switch (yesorno) {
            case "yes":
            case "ye":
            case "y":
                BlackJack.main(null);
                break;
            default:
                System.out.println("Thank you for playing.");
                System.exit(0);
        }
    }

    //The start of the game where both dealer and player draws 2 cards.
    public void start() {
        outPrintWithDelay("Dealer draws a: " + dealer.getCard3() + " and a hidden card.", 1500);
        //outPrintWithDelay("His total is hidden, too.\n", 1000);
        outPrintWithDelay("Deals you: " + player.getCard1() + " - " + player.getCard2() + ".", 1500);
        if (player.cardValue() == 21) {
            System.out.println("BLACKJACK! You win!");
            player.setMoney(player.getMoney() + player.getBet() * 3);
            newGame();
        }
        outPrintWithDelay("Your total is: " + player.cardValue() + "\n", 1500);
    }

    //Uses this to smooth out the outprint to console whenever a action is called making it look cleaner.
    public void outPrintWithDelay(String outprint, int delay) {
        System.out.println(outprint);
        try {
            Thread.sleep(delay);
        } catch (InterruptedException ex) {}
    }
}