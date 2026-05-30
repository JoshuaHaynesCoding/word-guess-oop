public class CommandResult {
    private final String message;
    private final boolean countsAsGuess;
    private final boolean gameWon;
    private final boolean gameQuit;

    public CommandResult(String message, boolean countsAsGuess, boolean gameWon, boolean gameQuit) {
        this.message = message;
        this.countsAsGuess = countsAsGuess;
        this.gameWon = gameWon;
        this.gameQuit = gameQuit;
    }

    public String getMessage() {
        return message;
    }

    public boolean countsAsGuess() {
        return countsAsGuess;
    }

    public boolean isGameWon() {
        return gameWon;
    }

    public boolean isGameQuit() {
        return gameQuit;
    }
}