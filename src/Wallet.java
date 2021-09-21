import java.io.*;
import static java.lang.System.out;
import java.util.*;

/**
 *
 * @author Roner
 */
public class Wallet extends Player {

    private Scanner scanWallet;
    private final String pathToWallet = "C:\\Users\\roner\\IdeaProjects\\blackjack\\src\\Wallet.txt";

    //Formatter to update the txt file which is the local database for wallet
    public void writeMoneyToFile() {
        try (Formatter file = new Formatter(pathToWallet)) {
            file.format("%d\n", player.getMoney()); // %d for ints and %s for strings
        } catch (FileNotFoundException ex) {
            System.out.println("Unwrittable file! Make sure it exists and is not a directory.");
        }
    }

    /**
     * Blackjack is gambling so there has to be a bet involved, this method is using a local database,
     * Wallet.txt that holds the amount of money you have that's being used to make a bet.
     * This method automatically refills the wallet if you gone broke.
     * You cannot bet more money then your wallet contains.
     * Depending if you win or lose the wallet balance updates after the game.
     */
    public void makeBet() {
        final Scanner s = new Scanner(System.in);
        if (player.getMoney() <= 0) {
            out.println("You're out of chipmarks, refilling with 100.");
            player.setMoney(100);
            writeMoneyToFile();
            scanWallet.close();
            BlackJack.main(null);
        } else {
            out.println("Wallet: " + player.getMoney() + " Place your bet: ");
            try {
                player.setBet(s.nextInt());
            } catch (InputMismatchException e) {
                out.println("Bad input, use numbers only! 1-" + player.getMoney());
                BlackJack.main(null);
            }
            scanWallet.close();
        }
        if (player.getBet() <= player.getMoney() && player.getBet() > 0) {
            player.setMoney(player.getMoney() - player.getBet());
            out.println("Placing bet: " + player.getBet() + "\n");
        } else {
            out.println("insufficient fund.");
            makeBet();
        }
    }

    //Calls this to check the balance in the wallet before you place a bet so you cannot bet more then you have eg.
    public void checkWallet() {
        try {
            scanWallet = new Scanner(new File(pathToWallet));
        } catch (FileNotFoundException ex) {
            out.println("Cannot find file! Make sure it exists and is not a directory.");
        }
        while (scanWallet.hasNext()) {
            player.setMoney(scanWallet.nextInt());
        }
    }
}