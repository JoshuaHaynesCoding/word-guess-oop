public class HelpCommand implements GameCommand {
    @Override
    public CommandResult execute(GameContext context) {
        String message = "Available commands:\n"
            + "- Type a " + GameSettings.getInstance().getWordLength() + "-letter guess to attempt the phrase.\n"
            + "- Type intel to show this message.\n"
            + "- Type abort to exit the mission.\n"
            + "- Type reset to restart the mission.\n\n"
            + "Feedback guide:\n"
            + context.getGameConfig().getEvaluationStrategy().getInstructions();

        return new CommandResult(message, false, false, false);
    }
}
