import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Word Ops");
        System.out.println("Choose a game mode:");
        System.out.println("1. Training Mode");
        System.out.println("2. Hard Mode");
        System.out.println("3. Classic Mode");
        System.out.print("Enter choice: ");

        String modeChoice = scanner.nextLine().trim();

        GameModeCreator gameModeCreator = GameModeSelector.selectCreator(modeChoice);
        GameConfig gameConfig = gameModeCreator.createGameConfig();

        WordProvider wordProvider = new WordProvider();
        GuessEvaluator guessEvaluator = new GuessEvaluator(gameConfig.getEvaluationStrategy());

        TerminalOutput terminalOutput = new TerminalOutput();
        GameDisplay gameDisplay = new ConsoleDisplayAdapter(terminalOutput);

        Game game = new Game(wordProvider, guessEvaluator, gameConfig, scanner, gameDisplay);
        game.play();

        scanner.close();
    }
}