public class GuessCommand implements GameCommand {
    private final String guess;

    public GuessCommand(String guess) {
        this.guess = guess;
    }

    @Override
    public CommandResult execute(GameContext context) {
        String cleanedGuess = guess.trim().toLowerCase();

        if (!context.getWordProvider().isValidGuess(cleanedGuess)) {
            return new CommandResult(
                "Negative. The phrase is exactly " + context.getSecretWord().length() + " letters.",
                false,
                false,
                false
            );
        }

        String feedback = context.getGuessEvaluator().evaluateGuess(cleanedGuess, context.getSecretWord());

        if (cleanedGuess.equals(context.getSecretWord())) {
            return new CommandResult(
                feedback + "\nAffirmative. Mission complete.",
                true,
                true,
                false
            );
        }

        return new CommandResult(
            feedback + "\nNegative. Try again.",
            true,
            false,
            false
        );
    }
}