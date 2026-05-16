import java.util.Scanner;

public class Game {
    private final WordProvider wordProvider;
    private final GuessEvaluator guessEvaluator;
    private final GameConfig gameConfig;
    private final Scanner scanner;

    public Game(WordProvider wordProvider, GuessEvaluator guessEvaluator, GameConfig gameConfig, Scanner scanner) {
        this.wordProvider = wordProvider;
        this.guessEvaluator = guessEvaluator;
        this.gameConfig = gameConfig;
        this.scanner = scanner;
    }

    public void play() {
        String secretWord = wordProvider.getRandomWord();
        int guessesUsed = 0;
        boolean hasWon = false;

        printIntro();

        while (guessesUsed < gameConfig.getMaxGuesses() && !hasWon) {
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
    }

    private void printIntro() {
        System.out.println("Word Ops");
        System.out.println("Mode: " + gameConfig.getModeName());
        System.out.println("Identify the 4-letter challenge phrase. You have " + gameConfig.getMaxGuesses() + " tries.");
        System.out.println(gameConfig.getEvaluationStrategy().getInstructions());
    }
}