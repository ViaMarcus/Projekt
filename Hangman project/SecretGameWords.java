package com.mycompany.hangman;
 
import java.util.*;
 
/**
 * Utility class that handles hangman's secret words.
 *
 * @author Roner Reked
 */
public class SecretGameWords {
 
    private SecretGameWords() {
    }
 
    private static final List<String> secretWords = new ArrayList<>();
 
    /**
     * Creating a new array with the game words so we can add them to our
     * secretWord list and then shuffle it.
     */
    static {
        String[] gameWords = {"JAVA", "COMPUTER", "UMBRELLA", "STAR",
            "SLEEPY", "TRASH", "GOLD", "IMPOSTER"};
        secretWords.addAll(Arrays.asList(gameWords));
        Collections.shuffle(secretWords);
    }
 
    /**
     * @return a new word from the secretWords list every time this method is
     * called.
     */
    public static String getSecretWord() {
        return secretWords.get(nextWord++);
    }
 
    private static int nextWord = 0;
}