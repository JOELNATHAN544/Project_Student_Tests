package com.yourname;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class HangmanGameTest {

    @Test
    void testSingleCorrectLetter() {
        StringBuilder guessedWord = new StringBuilder("____");
        int points = HangmanGame.updateGuessedWord("java", guessedWord, 'j');
        assertEquals(1, points);
        assertEquals("j___", guessedWord.toString());
    }

    @Test
    void testMultipleCorrectLetters() {
        StringBuilder guessedWord = new StringBuilder("____");
        int points = HangmanGame.updateGuessedWord("java", guessedWord, 'a');
        assertEquals(2, points);
        assertEquals("_a_a", guessedWord.toString());
    }

    @Test
    void testNoCorrectLetter() {
        StringBuilder guessedWord = new StringBuilder("____");
        int points = HangmanGame.updateGuessedWord("java", guessedWord, 'z');
        assertEquals(0, points);
        assertEquals("____", guessedWord.toString());
    }

    @Test
    void testGuessedWordUnchangedIfAlreadyGuessed() {
        StringBuilder guessedWord = new StringBuilder("j___");
        int points = HangmanGame.updateGuessedWord("java", guessedWord, 'j');
        assertEquals(0, points);
        assertEquals("j___", guessedWord.toString());
    }

    @Test
    void testWordBankLoading() {
        assertNotNull(HangmanGame.WORD_BANK);
        assertTrue(HangmanGame.WORD_BANK.length >= 3);
        for (String[] category : HangmanGame.WORD_BANK) {
            assertTrue(category.length > 0);
        }
    }
}
