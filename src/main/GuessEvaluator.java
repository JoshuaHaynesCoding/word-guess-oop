public class GuessEvaluator {
    public String evaluateGuess(String guess, String secretWord) {
        StringBuilder feedback = new StringBuilder();

        for (int i = 0; i < guess.length(); i++) {
            char guessLetter = guess.charAt(i);
            char secretLetter = secretWord.charAt(i);

            if (guessLetter == secretLetter) {
                feedback.append("[HIT ").append(guessLetter).append("] ");
            } else if (secretWord.indexOf(guessLetter) >= 0) {
                feedback.append("[PING ").append(guessLetter).append("] ");
            } else {
                feedback.append("[MISS ").append(guessLetter).append("] ");
            }
        }

        return feedback.toString();
    }
}