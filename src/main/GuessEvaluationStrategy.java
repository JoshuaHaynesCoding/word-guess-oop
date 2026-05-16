public interface GuessEvaluationStrategy {
    String evaluate(String guess, String secretWord);
    String getInstructions();
}