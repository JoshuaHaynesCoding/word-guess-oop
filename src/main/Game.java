import java.util.Scanner;

public class Game {
    private final WordProvider wordProvider;
    private final GuessEvaluator guessEvaluator;
    private final GameConfig gameConfig;
    private final Scanner scanner;
    private final CommandParser commandParser;
    private final GameEventManager eventManager;
    private final ScoreTracker scoreTracker;
    private final GameDisplay display;

    public Game(WordProvider wordProvider, GuessEvaluator guessEvaluator, GameConfig gameConfig, Scanner scanner, GameDisplay display) {
        this.wordProvider = wordProvider;
        this.guessEvaluator = guessEvaluator;
        this.gameConfig = gameConfig;
        this.scanner = scanner;
        this.display = display;
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
            display.showPrompt("Enter guess #" + (guessesUsed + 1) + " or command: ");
            String input = scanner.nextLine();

            GameCommand command = commandParser.parse(input);
            CommandResult result = command.execute(context);

            display.showMessage(result.getMessage());

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
            display.showMessage("Mission failed, we'll get 'em next time. The phrase was: " + secretWord);
            eventManager.notifyListeners("GAME_LOST", "Player ran out of guesses.");
        }

        display.showMessage(scoreTracker.getSummary());
    }

    private void printIntro() {
        display.showMessage("Word Ops");
        display.showMessage("Mode: " + gameConfig.getModeName());
        display.showMessage("Theme: " + GameSettings.getInstance().getThemeName());
        display.showMessage("Identify the " + GameSettings.getInstance().getWordLength() + "-letter challenge phrase. You have " + gameConfig.getMaxGuesses() + " tries.");
        display.showMessage(gameConfig.getEvaluationStrategy().getInstructions());
        display.showMessage("Type help for commands or quit to exit.");
    }
}