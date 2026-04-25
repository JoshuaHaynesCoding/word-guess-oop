import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        WordProvider wordProvider = new WordProvider();
        GuessEvaluator guessEvaluator = new GuessEvaluator();
        Scanner scanner = new Scanner(System.in);

        String secretWord = wordProvider.getRandomWord();
        int maxGuesses = 6;
        int guessesUsed = 0;
        boolean hasWon = false;

        System.out.println("Word Ops");
        System.out.println("Identify the 4-letter challenge phrase. You have " + maxGuesses + " tries.");
        System.out.println("HIT = correct letter and position");
        System.out.println("PING = correct letter, wrong position");
        System.out.println("MISS = letter not in phrase");

        while (guessesUsed < maxGuesses && !hasWon) {
            System.out.print("Enter guess #" + (guessesUsed + 1) + ": ");
            String guess = scanner.nextLine().trim().toLowerCase();

            if (!wordProvider.isValidGuess(guess)) {
                System.out.println("Negative. The phrase is exactly 4 letters.");
                continue;
            }

            guessesUsed++;

            String feedback = guessEvaluator.evaluateGuess(guess, secretWord);
            System.out.println(feedback);

            if (guess.equals(secretWord)) {
                hasWon = true;
                System.out.println("Affirmative. Mission complete.");
            } else {
                System.out.println("Negative. Try again.");
            }
        }

        if (!hasWon) {
            System.out.println("Mission failed, we'll get 'em next time. The phrase was: " + secretWord);
        }

        scanner.close();
    }
}