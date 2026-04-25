public class Main {
    public static void main(String[] args) {
        WordProvider wordProvider = new WordProvider();
        String secretWord = wordProvider.getRandomWord();

        System.out.println("Word Ops");
        System.out.println("A secret 4-letter intel word has been selected.");
        System.out.println("Temporary test word: " + secretWord);
    }
}