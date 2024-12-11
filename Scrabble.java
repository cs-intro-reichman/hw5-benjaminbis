public class Scrabble {

    static final String WORDS_FILE = "dictionary.txt";
    static final int HAND_SIZE = 10;
    static String[] DICTIONARY = new String[100000];
    static int NUM_OF_WORDS;

    public static void init() {
        In in = new In(WORDS_FILE);
        NUM_OF_WORDS = 0;
        while (!in.isEmpty()) {
            DICTIONARY[NUM_OF_WORDS++] = in.readString().toLowerCase();
        }
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
        int[] SCRABBLE_LETTER_VALUES = {
            1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3,
            1, 1, 3, 10, 1, 1, 1, 1, 4, 4, 8, 4, 10
        };
        for (int i = 0; i < word.length(); i++) {
            char letter = word.charAt(i);
            score += SCRABBLE_LETTER_VALUES[letter - 'a'];
        }
        return score;
    }

    public static String createHand() {
        String letters = "abcdefghijklmnopqrstuvwxyz";
        String hand = "";
        for (int i = 0; i < HAND_SIZE - 2; i++) {
            hand += letters.charAt((int) (Math.random() * letters.length()));
        }
        hand += "ae";
        return hand;
    }

    public static void playHand(String hand) {
        int score = 0;
        In in = new In();
        while (hand.length() > 0) {
            System.out.println("Current Hand: " + hand);
            System.out.println("Enter a word, or '.' to finish playing this hand:");
            String input = in.readString();
            if (input.equals(".")) {
                break;
            }
            if (isWordInDictionary(input)) {
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
        for (int i = 0; i < word.length(); i++) {
            char letter = word.charAt(i);
            hand = hand.replaceFirst(String.valueOf(letter), "");
        }
        return hand;
    }

    public static void playGame() {
        init();
        In in = new In();
        while (true) {
            System.out.println("Enter 'n' to deal a new hand, or 'e' to end the game:");
            String input = in.readString();
            if (input.equals("e")) {
                break;
            } else if (input.equals("n")) {
                String hand = createHand();
                playHand(hand);
            } else {
                System.out.println("Invalid command.");
            }
        }
    }

    public static void main(String[] args) {
        playGame();
    }
}
