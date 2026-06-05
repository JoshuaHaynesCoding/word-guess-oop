import java.util.Scanner;

public class Game {
    private final WordProvider wordProvider;
    private final GuessEvaluator guessEvaluator;
    private final GameConfig gameConfig;
    private final Scanner scanner;
    private final CommandParser commandParser;
    private final GameEventManager eventManager;
    private final ScoreTracker scoreTracker;

    public Game(WordProvider wordProvider, GuessEvaluator guessEvaluator, GameConfig gameConfig, Scanner scanner) {
        this.wordProvider = wordProvider;
        this.guessEvaluator = guessEvaluator;
        this.gameConfig = gameConfig;
        this.scanner = scanner;
        this.commandParser = new CommandParser();

        this.eventManager = new GameEventManager();
        this.scoreTracker = new ScoreTracker();

        this.eventManager.addListener(scoreTracker);
    }

    public void play() {
        String secretWord = wordProvider.getRandomWord();
        int guessesUsed = 0;
        boolean hasWon = false;
        boolean hasQuit = false;

        GameContext context = new GameContext(wordProvider, guessEvaluator, gameConfig, secretWord);

        printIntro();
        eventManager.notifyListeners("GAME_STARTED", "Started " + gameConfig.getModeName());

        while (guessesUsed < gameConfig.getMaxGuesses() && !hasWon && !hasQuit) {
            System.out.print("Enter guess #" + (guessesUsed + 1) + " or command: ");
            String input = scanner.nextLine();

            GameCommand command = commandParser.parse(input);
            CommandResult result = command.execute(context);

            System.out.println(result.getMessage());

            if (result.countsAsGuess()) {
                guessesUsed++;
                eventManager.notifyListeners("GUESS_SUBMITTED", "Player submitted a valid guess.");
            } else if (!result.isGameQuit() && !input.trim().equalsIgnoreCase("help")) {
                eventManager.notifyListeners("INVALID_GUESS", "Player submitted an invalid guess.");
            }

            if (result.isGameWon()) {
                hasWon = true;
                eventManager.notifyListeners("GAME_WON", "Player guessed the secret word.");
            }

            if (result.isGameQuit()) {
                hasQuit = true;
                eventManager.notifyListeners("GAME_QUIT", "Player quit the mission.");
            }
        }

        if (!hasWon && !hasQuit) {
            System.out.println("Mission failed, we'll get 'em next time. The phrase was: " + secretWord);
            eventManager.notifyListeners("GAME_LOST", "Player ran out of guesses.");
        }

        System.out.println(scoreTracker.getSummary());
    }

    private void printIntro() {
        System.out.println("Word Ops");
        System.out.println("Mode: " + gameConfig.getModeName());
        System.out.println("Theme: " + GameSettings.getInstance().getThemeName());
        System.out.println("Identify the " + GameSettings.getInstance().getWordLength() + "-letter challenge phrase. You have " + gameConfig.getMaxGuesses() + " tries.");
        System.out.println(gameConfig.getEvaluationStrategy().getInstructions());
        System.out.println("Type help for commands or quit to exit.");
    }
}