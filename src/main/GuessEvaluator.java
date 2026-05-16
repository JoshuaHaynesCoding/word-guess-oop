public class GuessEvaluator {
    private final GuessEvaluationStrategy evaluationStrategy;

    public GuessEvaluator(GuessEvaluationStrategy evaluationStrategy) {
        this.evaluationStrategy = evaluationStrategy;
    }

    public String evaluateGuess(String guess, String secretWord) {
        return evaluationStrategy.evaluate(guess, secretWord);
    }
}