public class ClassicEvaluationStrategy implements GuessEvaluationStrategy {
    @Override
    public String evaluate(String guess, String secretWord) {
        StringBuilder feedback = new StringBuilder();

        for (int i = 0; i < guess.length(); i++) {
            char guessLetter = guess.charAt(i);
            char secretLetter = secretWord.charAt(i);

            if (guessLetter == secretLetter) {
                feedback.append("[GREEN ").append(guessLetter).append("] ");
            } else if (secretWord.indexOf(guessLetter) >= 0) {
                feedback.append("[YELLOW ").append(guessLetter).append("] ");
            } else {
                feedback.append("[GRAY ").append(guessLetter).append("] ");
            }
        }

        return feedback.toString();
    }

    @Override
    public String getInstructions() {
        return """
                GREEN = correct letter and position
                YELLOW = correct letter, wrong position
                GRAY = letter not in word
                """;
    }
}