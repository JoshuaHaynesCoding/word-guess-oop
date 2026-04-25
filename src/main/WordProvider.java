import java.util.List;
import java.util.Random;

public class WordProvider {
    private final List<String> words;
    private final Random random;

    public WordProvider() {
        this.words = List.of(
            "code", "data", "file", "hack", "base",
            "plan", "unit", "zone", "mark", "grid",
            "scan", "mask", "lock", "keys", "node",
            "ping", "root", "trap", "path", "wire"
        );
        this.random = new Random();
    }

    public String getRandomWord() {
        return words.get(random.nextInt(words.size()));
    }

    public boolean isValidGuess(String guess) {
        return guess != null && guess.length() == 4 && guess.matches("[a-zA-Z]+");
    }
}