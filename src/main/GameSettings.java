public class GameSettings {
    private static GameSettings instance;

    private final int wordLength;
    private final String themeName;
    private final boolean debugMode;

    private GameSettings() {
        this.wordLength = 5;
        this.themeName = "Military Intelligence";
        this.debugMode = false;
    }

    public static GameSettings getInstance() {
        if (instance == null) {
            instance = new GameSettings();
        }

        return instance;
    }

    public int getWordLength() {
        return wordLength;
    }

    public String getThemeName() {
        return themeName;
    }

    public boolean isDebugMode() {
        return debugMode;
    }
} 
    

