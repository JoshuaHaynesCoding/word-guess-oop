public class GameContext {
    private final WordProvider wordProvider;
    private final GuessEvaluator guessEvaluator;
    private final GameConfig gameConfig;
    private final String secretWord;

    public GameContext(WordProvider wordProvider, GuessEvaluator guessEvaluator, GameConfig gameConfig, String secretWord) {
        this.wordProvider = wordProvider;
        this.guessEvaluator = guessEvaluator;
        this.gameConfig = gameConfig;
        this.secretWord = secretWord;
    }

    public WordProvider getWordProvider() {
        return wordProvider;
    }

    public GuessEvaluator getGuessEvaluator() {
        return guessEvaluator;
    }

    public GameConfig getGameConfig() {
        return gameConfig;
    }

    public String getSecretWord() {
        return secretWord;
    }
}