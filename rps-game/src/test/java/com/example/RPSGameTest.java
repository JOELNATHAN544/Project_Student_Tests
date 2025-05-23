package com.example;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class RPSGameTest {

    @Test
    public void testTieScenarios() {
        assertEquals("tie", RPSGame.determineWinner("rock", "rock"));
        assertEquals("tie", RPSGame.determineWinner("paper", "paper"));
        assertEquals("tie", RPSGame.determineWinner("scissors", "scissors"));
    }

    @Test
    public void testUserWins() {
        assertEquals("user", RPSGame.determineWinner("rock", "scissors"));
        assertEquals("user", RPSGame.determineWinner("paper", "rock"));
        assertEquals("user", RPSGame.determineWinner("scissors", "paper"));
    }

    @Test
    public void testComputerWins() {
        assertEquals("computer", RPSGame.determineWinner("rock", "paper"));
        assertEquals("computer", RPSGame.determineWinner("paper", "scissors"));
        assertEquals("computer", RPSGame.determineWinner("scissors", "rock"));
    }

    @Test
    public void testGetComputerChoiceReturnsValidChoice() {
        for (int i = 0; i < 100; i++) {
            String choice = RPSGame.getComputerChoice();
            assertTrue(choice.equals("rock") || choice.equals("paper") || choice.equals("scissors"),
                    "Invalid choice returned: " + choice);
        }
    }
}
