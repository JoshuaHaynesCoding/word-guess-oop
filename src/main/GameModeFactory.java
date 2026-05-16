public class GameModeFactory {
    public GameConfig createGameConfig(String modeChoice) {
        if (modeChoice == null) {
            return createTrainingMode();
        }

        return switch (modeChoice.toLowerCase()) {
            case "1", "training" -> createTrainingMode();
            case "2", "hard" -> createHardMode();
            case "3", "classic" -> createClassicMode();
            default -> createTrainingMode();
        };
    }

    private GameConfig createTrainingMode() {
        return new GameConfig("Training Mode", 6, new MilitaryEvaluationStrategy());
    }

    private GameConfig createHardMode() {
        return new GameConfig("Hard Mode", 4, new MilitaryEvaluationStrategy());
    }

    private GameConfig createClassicMode() {
        return new GameConfig("Classic Mode", 6, new ClassicEvaluationStrategy());
    }
}