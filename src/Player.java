import java.util.Scanner;

/**
 *
 * @author Roner
 */
public class Player extends GameEngine implements blackjack.PlayerAndDealer {

    private int value,
            card1,
            card2,
            newCard,
            money,
            bet;



    //Calls this once the first hand is being dealt, adds up the sum here.
    @Override
    public void dealFirstHand() {
        card1 = nextCard();
        card2 = nextCard();
        value = card1 + card2;
    }

    //returns the total amount of ur hand
    @Override
    public int cardValue() {
        return value;
    }

    //Creates the ability to hit or stay during the game, wont call this if busted or blackjack.
    public void hitStay() {
        Scanner y = new Scanner(System.in);
        System.out.println("Would you like to 'hit' or 'stay'?");
        String hitOrStay = y.nextLine().toLowerCase().replaceAll(" ", "");
        switch (hitOrStay) {
            case "hit":
                hit();
                break;
            case "stay":
                dealer.hit();
                break;
            default:
                outPrintWithDelay("Wrong input please use 'hit' or 'stay'.", 1000);
                hitStay();
        }
    }


    //If player chooses to hit during the game this method gets called, if player busts the game ends.
    @Override
    public void hit() {
        newCard = nextCard();
        value += newCard;
        outPrintWithDelay("You drew a: " + newCard, 1000);
        outPrintWithDelay("Your total is " + cardValue() + "\n", 1000);
        if (cardValue() > 21) {
            outPrintWithDelay("BUST, the dealer wins", 1000);
            return;
        }
        hitStay();
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void setBet(int bet) {
        this.bet = bet;
    }

    public int getMoney() {
        return money;
    }

    public int getBet() {
        return bet;
    }

    public int getCard1() {
        return card1;
    }

    public int getCard2() {
        return card2;
    }

}