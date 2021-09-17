package com.mycompany.hangman;
 
import java.util.Scanner;
 
/**
 * Starts the game; once finished game asks the player to play again. Also exits
 * the application here.
 *
 * @author Roner
 */
public class GameRunner {
 
    public static void main(String[] args) {
        HangMan game = new HangMan();
 
        System.out.println("Welcome to Hangman.");
        do {
            game.resetGame();
            game.start();
        } while (replay());
        System.out.println("Thank-you, for playing.");
    }
 
    /**
     * @return true if user input starts with a "y" else return false.
     */
    private static boolean replay() {
        Scanner input = new Scanner(System.in);
        System.out.println("Would you like to play again?");
        String playAgain = input.nextLine().toUpperCase();
        return playAgain.charAt(0) == 'Y';
    }
}