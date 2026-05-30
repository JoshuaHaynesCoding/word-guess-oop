public class CommandParser {
    public GameCommand parse(String input) {
        String cleanedInput = input.trim().toLowerCase();

        return switch (cleanedInput) {
            case "help" -> new HelpCommand();
            case "quit", "exit" -> new QuitCommand();
            default -> new GuessCommand(cleanedInput);
        };
    }
}