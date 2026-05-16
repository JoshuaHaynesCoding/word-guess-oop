public class GameConfig {
    private final String modeName;
    private final int maxGuesses;
    private final GuessEvaluationStrategy evaluationStrategy;

    public GameConfig(String modeName, int maxGuesses, GuessEvaluationStrategy evaluationStrategy) {
        this.modeName = modeName;
        this.maxGuesses = maxGuesses;
        this.evaluationStrategy = evaluationStrategy;
    }

    public String getModeName() {
        return modeName;
    }

    public int getMaxGuesses() {
        return maxGuesses;
    }

    public GuessEvaluationStrategy getEvaluationStrategy() {
        return evaluationStrategy;
    }
}