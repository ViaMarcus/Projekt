/**
 *
 * @author Roner
 */
public class BlackJack {

    public static void main(String[] args) {

        Player player = new Player();
        Dealer dealer = new Dealer();
        Wallet wallet = new Wallet();
        GameEngine game = new GameEngine();

        game.InitializeReferences(player, dealer, wallet);

        game.shuffleCardList(); // At the start of the game we shuffle the deck

        wallet.checkWallet(); // Checks and updates the wallet for this round
        wallet.makeBet(); // Asks player to place a bet depending on wallet size
        wallet.writeMoneyToFile(); // Updates the wallet after the bet has been placed

        player.dealFirstHand(); // Deals player hand
        dealer.dealFirstHand(); // Deals dealer's hand
        game.start(); // Tells the amount dealt to both the dealer and player
        player.hitStay(); // Asks player depending on hand to hit or stay
        game.newGame(); // Asks player if wants to go for another round
    }
}