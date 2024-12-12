public class Scrabble {

    static final String WORDS_FILE = "dictionary.txt";

    static final int[] SCRABBLE_LETTER_VALUES = {1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3, 1, 1, 3, 10, 1, 1, 1, 1, 4, 4, 8, 4, 10};

    static final int HAND_SIZE = 10;

    static final int MAX_NUMBER_OF_WORDS = 100000;

    static String[] DICTIONARY = new String[MAX_NUMBER_OF_WORDS];

    static int NUM_OF_WORDS;

    public static void init() {
        In in = new In(WORDS_FILE);
        System.out.println("Loading word list from file...");
        NUM_OF_WORDS = 0;
        while (!in.isEmpty()) {
            DICTIONARY[NUM_OF_WORDS++] = in.readString().toLowerCase();
        }
        System.out.println(NUM_OF_WORDS + " words loaded successfully.");
    }

    public static boolean isInHand(String input, String hand) {
        boolean found;
        for (char str1Letter : input.toCharArray()) {
            found = false;
            for (int i = 0; i < hand.length(); i++) {
                if (str1Letter == hand.charAt(i)) {
                    found = true;
                    hand = hand.substring(0, i) + hand.substring(i + 1);
                    break;
                }
            }
            if (!found) {
                return false;
            }
        }
        return true;
    }

    public static String remove(String str1, String str2) {
        for (char letter : str2.toCharArray()) {
            int index = str1.indexOf(letter);
            if (index != -1) {
                str1 = str1.substring(0, index) + str1.substring(index + 1);
            }
        }
        return str1;
    }

    public static boolean subsetOf(String str1, String str2) {
        for (char str1Letter : str1.toCharArray()) {
            int index = str2.indexOf(str1Letter);
            if (index == -1) {
                return false;
            }
            str2 = str2.substring(0, index) + str2.substring(index + 1);
        }
        return true;
    }

    public static boolean isWordInDictionary(String word) {
        for (String wordInDic : DICTIONARY) {
            if (wordInDic != null && wordInDic.equals(word)) {
                return true;
            }
        }
        return false;
    }

    public static int wordScore(String word) {
        int score = 0;
        for (char letter : word.toCharArray()) {
            score += SCRABBLE_LETTER_VALUES[letter - 'a'];
        }
        score *= word.length();
        if (word.length() == HAND_SIZE) {
            score += 50;
        }
        if (subsetOf("runi", word)) {
            score += 1000;
        }
        return score;
    }

    public static String createHand() {
        StringBuilder hand = new StringBuilder(HAND_SIZE);
        int indexA = (int) (Math.random() * HAND_SIZE);
        int indexE;
        do {
            indexE = (int) (Math.random() * HAND_SIZE);
        } while (indexE == indexA);

        for (int i = 0; i < HAND_SIZE; i++) {
            if (i == indexA) {
                hand.append('a');
            } else if (i == indexE) {
                hand.append('e');
            } else {
                hand.append((char) ((int) (Math.random() * 26) + 'a'));
            }
        }
        return hand.toString();
    }

    public static void playHand(String hand) {
        int score = 0;
        In in = new In();
        while (hand.length() > 0) {
            System.out.println("Current Hand: " + MyString.spacedString(hand));
            System.out.println("Enter a word, or '.' to finish playing this hand:");
            String input = in.readString();
            if (input.equals(".")) {
                break;
            }
            if (isWordInDictionary(input) && isInHand(input, hand)) {
                int wordScore = wordScore(input);
                score += wordScore;
                hand = remove(hand, input);
                System.out.println(input + " earned " + wordScore + " points. Total: " + score + " points.");
            } else {
                System.out.println("Invalid word. Try again.");
            }
        }
        System.out.println("End of hand. Total score: " + score + " points.");
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
                playHand(createHand());
            } else {
                System.out.println("Invalid input. Please try again.");
            }
        }
    }

    public static void main(String[] args) {
        playGame();
    }
}
