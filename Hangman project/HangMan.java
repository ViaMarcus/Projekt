package com.mycompany.hangman;
 
import static java.lang.System.out;
import java.util.Scanner;
 
/**
 * The main class of the application we run the game through here.
 *
 * @author Roner
 */
public class HangMan {
 
    private String secretWord;
    private final String[] setHangMansBody = new String[5];
    private final int MAX_TRIES = 5;
    private int tries;
 
    /**
     * Starts the application here by deciding the games secret word.<p>
     * Also checks if the player did guess the secret word or not.
     */
    public void start() {
        secretWord = SecretGameWords.getSecretWord();
        out.println("You have " + MAX_TRIES + " tries, goodluck.\n");
        if (guessedSecretWord()) {
            out.println("You won!\n");
            out.println("The word was: " + secretWord);
        } else {
            out.println("You lost!\n");
            out.println("The word was: " + secretWord);
        }
    }
 
    /**
     * @return true if player manages to guess the secret word within 5
     * tries.<p>
     * false if player fails to guess the secret word.
     */
    private boolean guessedSecretWord() {
        Scanner input = new Scanner(System.in);
        char guessedLetter = 0;
        char[] hiddenWord = secretWord.toCharArray();
        StringBuilder revealWord = new StringBuilder(secretWord.replaceAll("\\S", "-"));
        out.println(revealWord);
        while (tries < MAX_TRIES) {
            out.print("Guess the word or just a letter: ");
            String guessWord = input.nextLine().toUpperCase();
            try {
                guessedLetter = guessWord.charAt(0);
            } catch (StringIndexOutOfBoundsException e) {
            }
            if (secretWord.indexOf(guessedLetter) != -1) {
                for (int charPos = 0; charPos < hiddenWord.length; charPos++) {
                    if (hiddenWord[charPos] == guessedLetter) {
                        revealWord.setCharAt(charPos, guessedLetter);
                    }
                }
                if (revealWord.toString().equals(secretWord) || guessWord.equals(secretWord)) {
                    return true;
                }
                out.println(revealWord);
                out.println("\nYou got it right!");
            } else {
                tries++;
                out.println("You guessed wrong letter, you have " + (MAX_TRIES - tries) + " tries left.");
                drawHangMan();
            }
        }
        return false;
    }
 
    /**
     * Resets the game variables to default.
     */
    public void resetGame() {
        tries = 0;
        secretWord = null;
        for (int bodyParts = 0; bodyParts < setHangMansBody.length; bodyParts++) {
            setHangMansBody[bodyParts] = " |                 ";
        }
    }
 
    /**
     * Reveals hang man's body depending on failed tries.
     */
    private void drawHangMan() {
        switch (tries) {
            case 1:
                setHangMansBody[0] = " | /      |           ";
                break;
            case 2:
                setHangMansBody[1] = " |      (*_*)      ";
                break;
            case 3:
                setHangMansBody[2] = " |       \\|/       ";
                break;
            case 4:
                setHangMansBody[3] = " |        |        ";
                break;
            case 5:
                setHangMansBody[4] = " |       / \\      ";
                break;
            default:
                resetGame();
 
        }
        out.println(" __________");
        for (String revealBody : setHangMansBody) {
            out.println(revealBody);
        }
        out.println(" |                 ");
        out.println(" |________________ ");
    }
}