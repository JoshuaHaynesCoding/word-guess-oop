public class ScoreTracker implements GameEventListener {
    private int guessesSubmitted;
    private int invalidGuesses;
    private int gamesWon;
    private int gamesLost;

    @Override
    public void onGameEvent(String eventType, String message) {
        switch (eventType) {
            case "GUESS_SUBMITTED" -> guessesSubmitted++;
            case "INVALID_GUESS" -> invalidGuesses++;
            case "GAME_WON" -> gamesWon++;
            case "GAME_LOST" -> gamesLost++;
            default -> {
                // no score change needed for other events
            }
        }
    }

    public String getSummary() {
        return "Mission Summary: "
            + guessesSubmitted + " valid guesses, "
            + invalidGuesses + " invalid guesses, "
            + gamesWon + " wins, "
            + gamesLost + " losses.";
    }
}