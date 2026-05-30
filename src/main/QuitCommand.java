public class QuitCommand implements GameCommand {
    @Override
    public CommandResult execute(GameContext context) {
        return new CommandResult("Mission aborted.", false, false, true);
    }
}