package com.example;

import java.util.Random;
import java.util.Scanner;

public class RPSGame {

    private static final String[] choices = { "rock", "paper", "scissors" };

    public static String getComputerChoice() {
        Random random = new Random();
        return choices[random.nextInt(3)];
    }

    public static String determineWinner(String userChoice, String computerChoice) {
        if (userChoice.equals(computerChoice)) {
            return "tie";
        } else if ((userChoice.equals("rock") && computerChoice.equals("scissors")) ||
                   (userChoice.equals("paper") && computerChoice.equals("rock")) ||
                   (userChoice.equals("scissors") && computerChoice.equals("paper"))) {
            return "user";
        } else {
            return "computer";
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int userScore = 0;
        int computerScore = 0;

        System.out.println("🎮 Welcome to Rock, Paper, Scissors!");

        while (true) {
            System.out.print("\nEnter your choice (rock, paper, scissors), 'score' to see the current score or 'exit' to quit: ");
            String userChoice = scanner.nextLine().toLowerCase();

            if (userChoice.equals("exit")) {
                break;
            }

            if (userChoice.equals("score")) {
                System.out.println("Current Score = You: " + userScore + " - Computer: " + computerScore);
                continue;
            }

            if (!userChoice.equals("rock") && !userChoice.equals("paper") && !userChoice.equals("scissors")) {
                System.out.println("Invalid choice. Please try again.");
                continue;
            }

            String computerChoice = getComputerChoice();
            System.out.println("Computer chose: " + computerChoice);

            String winner = determineWinner(userChoice, computerChoice);
            switch (winner) {
                case "tie":
                    System.out.println("It's a tie!");
                    break;
                case "user":
                    System.out.println("You win!");
                    userScore++;
                    break;
                case "computer":
                    System.out.println("Computer wins!");
                    computerScore++;
                    break;
            }

            System.out.println("Score = You: " + userScore + " - Computer: " + computerScore);
        }

        System.out.println("Thanks for playing! Final Score - You: " + userScore + ", Computer: " + computerScore);
        scanner.close();
    }
}
