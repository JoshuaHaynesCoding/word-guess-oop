import java.util.Scanner;

public class Game {
    private final WordProvider wordProvider;
    private final GuessEvaluator guessEvaluator;
    private final GameConfig gameConfig;
    private final Scanner scanner;
    private final CommandParser commandParser;

    public Game(WordProvider wordProvider, GuessEvaluator guessEvaluator, GameConfig gameConfig, Scanner scanner) {
        this.wordProvider = wordProvider;
        this.guessEvaluator = guessEvaluator;
        this.gameConfig = gameConfig;
        this.scanner = scanner;
        this.commandParser = new CommandParser();
    }

    public void play() {
        String secretWord = wordProvider.getRandomWord();
        int guessesUsed = 0;
        boolean hasWon = false;
        boolean hasQuit = false;

        GameContext context = new GameContext(wordProvider, guessEvaluator, gameConfig, secretWord);

        printIntro();

        while (guessesUsed < gameConfig.getMaxGuesses() && !hasWon && !hasQuit) {
            System.out.print("Enter guess #" + (guessesUsed + 1) + " or command: ");
            String input = scanner.nextLine();

            GameCommand command = commandParser.parse(input);
            CommandResult result = command.execute(context);

            System.out.println(result.getMessage());

            if (result.countsAsGuess()) {
                guessesUsed++;
            }

            if (result.isGameWon()) {
                hasWon = true;
            }

            if (result.isGameQuit()) {
                hasQuit = true;
            }
        }

        if (!hasWon && !hasQuit) {
            System.out.println("Mission failed, we'll get 'em next time. The phrase was: " + secretWord);
        }
    }

    private void printIntro() {
        System.out.println("Word Ops");
        System.out.println("Mode: " + gameConfig.getModeName());
        System.out.println("Identify the 4-letter challenge phrase. You have " + gameConfig.getMaxGuesses() + " tries.");
        System.out.println(gameConfig.getEvaluationStrategy().getInstructions());
        System.out.println("Type help for commands or quit to exit.");
    }
}