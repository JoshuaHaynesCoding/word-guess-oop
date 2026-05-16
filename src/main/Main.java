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

        GameModeFactory gameModeFactory = new GameModeFactory();
        GameConfig gameConfig = gameModeFactory.createGameConfig(modeChoice);

        WordProvider wordProvider = new WordProvider();
        GuessEvaluator guessEvaluator = new GuessEvaluator(gameConfig.getEvaluationStrategy());

        Game game = new Game(wordProvider, guessEvaluator, gameConfig, scanner);
        game.play();

        scanner.close();
    }
}