import static java.lang.System.out;

/**
 *
 * @author Roner
 */
public class Dealer extends GameEngine implements blackjack.PlayerAndDealer {

    private int value,
            card3,
            card4,
            newCard;

    @Override
    public void dealFirstHand() {
        card3 = nextCard();
        card4 = nextCard();
        value = card3 + card4;
    }

    @Override
    public int cardValue() {
        return value;
    }

    //Calls this method whenever player wants to "hit" or if dealer's total is under 16
    @Override
    public void hit() {
        outPrintWithDelay("Dealer turn.", 1500);
        outPrintWithDelay("Dealer hidden card is a " + getCard4(), 1500);
        outPrintWithDelay("Dealer total is " + cardValue() + "\n", 1500);
        while (cardValue() < 17) {
            newCard = nextCard();
            value += newCard;
            outPrintWithDelay("Dealer hit.", 1500);
            outPrintWithDelay("Dealer draws a " + newCard, 1500);
            outPrintWithDelay("Dealer total is " + cardValue() + "\n", 1500);
            if (cardValue() > 21) {
                out.println("Dealer BUST, you win.\n");
                player.setMoney(player.getMoney() + player.getBet() * 2);
                return;
            } else if (player.cardValue() == cardValue() && cardValue() > 16) {
                out.println("Both you and the dealer has the same amount therefore the dealer wins.\n");
            }
        }
        if (player.cardValue() < cardValue()) {
            outPrintWithDelay("The dealer has a higher amount and wins.\n", 1500);
        } else {
            outPrintWithDelay("You won!", 1500);
            player.setMoney(player.getMoney() + player.getBet() * 2);
        }
    }

    //Returns a card from deck
    public int getCard3() {
        return card3;
    }

    //Returns a card from deck
    public int getCard4() {
        return card4;
    }
}