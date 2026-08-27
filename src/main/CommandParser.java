public class CommandParser {
    public GameCommand parse(String input) {
        String cleanedInput = input.trim().toLowerCase();

        return switch (cleanedInput) {
            case "intel", "help" -> new HelpCommand();
            case "abort", "quit", "exit" -> new QuitCommand();
            default -> new GuessCommand(cleanedInput);
        };
    }
}
