/*
 * RUNI version of the Scrabble game.
 */
public class Scrabble {

    static final String WORDS_FILE = "dictionary.txt";
    static final int[] SCRABBLE_LETTER_VALUES = { 1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3,
                                                  1, 1, 3, 10, 1, 1, 1, 1, 4, 4, 8, 4, 10 };
    static int HAND_SIZE = 10;
    static int MAX_NUMBER_OF_WORDS = 100000;
    static String[] DICTIONARY = new String[MAX_NUMBER_OF_WORDS];
    static int NUM_OF_WORDS;

    public static void init() {
        In in = new In(WORDS_FILE);
        System.out.println("Loading word list from file...");
        NUM_OF_WORDS = 0;
        while (!in.isEmpty()) {
            DICTIONARY[NUM_OF_WORDS++] = in.readString().toLowerCase();
        }
        System.out.println(NUM_OF_WORDS + " words loaded.");
    }

    public static boolean isWordInDictionary(String word) {
        for (int i = 0; i < NUM_OF_WORDS; i++) {
            if (DICTIONARY[i].equals(word)) {
                return true;
            }
        }
        return false;
    }

    public static int wordScore(String word) {
        int score = 0;
        for (int i = 0; i < word.length(); i++) {
            char letter = word.charAt(i);
            score += SCRABBLE_LETTER_VALUES[letter - 'a'];
        }
        if (word.length() == HAND_SIZE) {
            score += 50;
        }
        if (word.contains("runi")) {
            score += 1000;
        }
        return score;
    }

    public static String createHand() {
        String letters = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder hand = new StringBuilder();
        for (int i = 0; i < HAND_SIZE - 2; i++) {
            hand.append(letters.charAt((int) (Math.random() * letters.length())));
        }
        hand.append("ae");
        return hand.toString();
    }

    public static void playHand(String hand) {
        int score = 0;
        In in = new In();
        while (hand.length() > 0) {
            System.out.println("Current Hand: " + formatHand(hand));
            System.out.println("Enter a word, or '.' to finish playing this hand:");
            String input = in.readString();
            if (input.equals(".")) {
                break;
            }
            if (isWordInDictionary(input) && isSubsetOf(input, hand)) {
                int wordScore = wordScore(input);
                score += wordScore;
                System.out.println("Word score: " + wordScore + " Total score: " + score);
                hand = removeLetters(hand, input);
            } else {
                System.out.println("Invalid word.");
            }
        }
        System.out.println("End of hand. Total score: " + score);
    }

    public static String removeLetters(String hand, String word) {
        StringBuilder updatedHand = new StringBuilder(hand);
        for (int i = 0; i < word.length(); i++) {
            char letter = word.charAt(i);
            int index = updatedHand.indexOf(String.valueOf(letter));
            if (index != -1) {
                updatedHand.deleteCharAt(index);
            }
        }
        return updatedHand.toString();
    }

    public static boolean isSubsetOf(String word, String hand) {
        StringBuilder handCopy = new StringBuilder(hand);
        for (int i = 0; i < word.length(); i++) {
            char letter = word.charAt(i);
            int index = handCopy.indexOf(String.valueOf(letter));
            if (index == -1) {
                return false;
            }
            handCopy.deleteCharAt(index);
        }
        return true;
    }

    public static String formatHand(String hand) {
        StringBuilder formattedHand = new StringBuilder();
        for (int i = 0; i < hand.length(); i++) {
            formattedHand.append(hand.charAt(i)).append(" ");
        }
        return formattedHand.toString().trim();
    }

    public static void playGame() {
        init();
        In in = new In();
        while (true) {
            System.out.println("Enter 'n' to deal a new hand, or 'e' to end the game:");
            String input = in.readString();
            if (input.equals("e")) {
                System.out.println("Thanks for playing!");
                break;
            } else if (input.equals("n")) {
                String hand = createHand();
                playHand(hand);
            } else {
                System.out.println("Invalid command.");
            }
        }
    }

    public static void testBuildingTheDictionary() {
        init();
        for (int i = 0; i < 5; i++) {
            System.out.println(DICTIONARY[i]);
        }
        System.out.println(isWordInDictionary("mango"));
    }

    public static void testScrabbleScore() {
        System.out.println(wordScore("bee"));
        System.out.println(wordScore("babe"));
        System.out.println(wordScore("friendship"));
        System.out.println(wordScore("running"));
    }

    public static void testCreateHands() {
        System.out.println(createHand());
        System.out.println(createHand());
        System.out.println(createHand());
    }

    public static void testPlayHands() {
        init();
        playHand("ocostrza");
        playHand("arbffip");
        playHand("aretiin");
    }

    public static void main(String[] args) {
        testBuildingTheDictionary();
        testScrabbleScore();
        testCreateHands();
        testPlayHands();
        playGame();
    }
}
