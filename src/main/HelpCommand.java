public class HelpCommand implements GameCommand {
    @Override
    public CommandResult execute(GameContext context) {
        String message = """
                Available commands:
                - Type a 4-letter guess to attempt the phrase.
                - Type help to show this message.
                - Type quit to exit the mission.

                Feedback guide:
                """ + context.getGameConfig().getEvaluationStrategy().getInstructions();

        return new CommandResult(message, false, false, false);
    }
}