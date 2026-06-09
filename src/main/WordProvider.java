import java.util.List;
import java.util.Random;

public class WordProvider {
    private final List<String> words;
    private final Random random;

    public WordProvider() {
        this.words = List.of(
    "alpha", "pilot", "armor", "eagle", "blade",
    "beret", "delta", "drone", "flank", "force",
    "ghost", "havoc", "intel", "knife", "laser",
    "medic", "night", "omega", "patch", "radar",
    "recon", "scout", "sigma", "snipe", "squad",
    "storm", "tiger", "tango", "toxic", "valor",
    "viper", "watch", "bravo", "cargo", "chain",
    "cloak", "coast", "cobra", "comet", "craft",
    "crest", "decoy", "depot", "field", "flare",
    "front", "globe", "guard", "heavy", "hoist",
    "honor", "hydro", "judge", "luger", "march",
    "merit", "might", "motor", "mount", "naval",
    "nexus", "north", "optic", "orbit", "overt",
    "pivot", "plank", "plate", "point", "polar",
    "pouch", "prowl", "radio", "rapid", "ranks",
    "refit", "relic", "rifle", "rival", "rotor",
    "route", "saber", "salvo", "savvy", "seize",
    "shaft", "shift", "shoal", "shore", "sight",
    "skirt", "slant", "snout", "sonar", "spark",
    "spear", "spike", "spire", "spoil", "stern"
);
        this.random = new Random();
    }

    public String getRandomWord() {
        return words.get(random.nextInt(words.size()));
    }

    public boolean isValidGuess(String guess) {
        return guess != null
    && guess.length() == GameSettings.getInstance().getWordLength()
    && guess.matches("[a-zA-Z]+");
    }
}