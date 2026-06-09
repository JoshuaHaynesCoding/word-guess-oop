public class HelpCommand implements GameCommand {
    @Override
    public CommandResult execute(GameContext context) {
        String message = "Available commands:\n"
            + "- Type a " + GameSettings.getInstance().getWordLength() + "-letter guess to attempt the phrase.\n"
            + "- Type help to show this message.\n"
            + "- Type quit to exit the mission.\n\n"
            + "Feedback guide:\n"
            + context.getGameConfig().getEvaluationStrategy().getInstructions();

        return new CommandResult(message, false, false, false);
    }
}