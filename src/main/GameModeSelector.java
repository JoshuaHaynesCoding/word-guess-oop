public class GameModeSelector {
    public static GameModeCreator selectCreator(String modeChoice) {
        if (modeChoice == null) {
            return new TrainingModeCreator();
        }

        return switch (modeChoice.toLowerCase()) {
            case "1", "training" -> new TrainingModeCreator();
            case "2", "hard" -> new HardModeCreator();
            case "3", "classic" -> new ClassicModeCreator();
            default -> new TrainingModeCreator();
        };
    }
}