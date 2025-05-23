package com.yourname;

/**
 * Hello world!
 *
 */
import java.util.Scanner;

public class HangmanGame {
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_PURPLE = "\u001B[35m";

    public static String[][] getWORD_BANK() {
        return WORD_BANK;
    }

    public static final String[][] WORD_BANK = {
            { "java", "banana", "loop", "data" },
            { "python", "github", "method", "object" },
            { "javascript", "algorithm", "programming" }
    };

    private static final int[] ATTEMPTS_BY_LEVEL = { 8, 6, 5 };
    private static final int[] POINTS_BY_LEVEL = { 100, 200, 300 };
    private static final int POINTS_PER_LETTER = 50;
    private static final int BONUS_MULTIPLIER = 10;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int totalScore = 0;

        System.out.println(ANSI_GREEN + "\n\t\tHANGMAN WITH LEVELS & SCORING!" + ANSI_RESET);
        System.out.println(ANSI_PURPLE + "\tLetters: +50 pts  |  Bonus: +10 per attempt left\n" + ANSI_RESET);

        while (true) {
            int level = selectLevel(scanner);
            String secretWord = WORD_BANK[level][(int) (Math.random() * WORD_BANK[level].length)];
            int score = playRound(scanner, secretWord, level);
            totalScore += score;

            System.out.println("\nRound Score: " + ANSI_YELLOW + score + ANSI_RESET);
            System.out.println("Total Score: " + ANSI_YELLOW + totalScore + ANSI_RESET);

            if (!askToPlayAgain(scanner))
                break;
        }
        scanner.close();
    }

    private static int selectLevel(Scanner scanner) {
        while (true) {
            System.out.print("\nChoose level (1-Easy, 2-Medium, 3-Hard): ");
            try {
                int level = scanner.nextInt() - 1;
                scanner.nextLine();
                if (level >= 0 && level <= 2)
                    return level;
            } catch (Exception e) {
                scanner.nextLine();
            }
            System.out.println(ANSI_RED + "Invalid input. Enter 1, 2, or 3." + ANSI_RESET);
        }
    }

    private static int playRound(Scanner scanner, String secretWord, int level) {
        StringBuilder guessedWord = new StringBuilder("_".repeat(secretWord.length()));
        int attempts = ATTEMPTS_BY_LEVEL[level];
        boolean[] guessedLetters = new boolean[26];
        int letterScore = 0;

        System.out.println("\nWord: " + ANSI_BLUE + guessedWord + ANSI_RESET +
                " (Length: " + secretWord.length() + ")");

        while (attempts > 0 && !guessedWord.toString().equals(secretWord)) {
            System.out.print("\nGuess a letter: ");
            char guess = scanner.nextLine().toLowerCase().charAt(0);

            if (!Character.isLetter(guess)) {
                System.out.println(ANSI_RED + "Invalid input!" + ANSI_RESET);
                continue;
            }

            if (guessedLetters[guess - 'a']) {
                System.out.println(ANSI_YELLOW + "Already guessed!" + ANSI_RESET);
                continue;
            }
            guessedLetters[guess - 'a'] = true;

            if (secretWord.contains(String.valueOf(guess))) {
                int occurrences = updateGuessedWord(secretWord, guessedWord, guess);
                letterScore += occurrences * POINTS_PER_LETTER;
                System.out.println(ANSI_GREEN + "Correct! " + ANSI_BLUE + guessedWord + ANSI_RESET);
            } else {
                attempts--;
                System.out.println(ANSI_RED + "Wrong! Attempts left: " + attempts + ANSI_RESET);
            }
        }

        if (guessedWord.toString().equals(secretWord)) {
            int bonus = attempts * BONUS_MULTIPLIER;
            int total = POINTS_BY_LEVEL[level] + letterScore + bonus;
            System.out.println(ANSI_GREEN + "\nYOU WIN! +" + bonus + " bonus!" + ANSI_RESET);
            return total;
        } else {
            System.out.println(ANSI_RED + "\nGAME OVER! Word: " + secretWord + ANSI_RESET);
            return letterScore; // Only keep letter points if lost
        }
    }

    public static int updateGuessedWord(String word, StringBuilder guessedWord, char guess) {
        int points = 0;
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == guess && guessedWord.charAt(i) != guess) {
                guessedWord.setCharAt(i, guess);
                points++;
            }
        }
        return points;
    }

    private static boolean askToPlayAgain(Scanner scanner) {
        System.out.print("\nPlay again? (yes/no): ");
        return scanner.nextLine().equalsIgnoreCase("yes");
    }
}
