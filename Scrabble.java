public class Scrabble {

    static final String WORDS_FILE = "dictionary.txt";
    static final int[] SCRABBLE_LETTER_VALUES = {1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3,
            1, 1, 3, 10, 1, 1, 1, 1, 4, 4, 8, 4, 10};
    static int HAND_SIZE = 10;
    static int MAX_NUMBER_OF_WORDS = 100000;
    static String[] DICTIONARY = new String[MAX_NUMBER_OF_WORDS];
    static int NUM_OF_WORDS;

    public static void init() {
        In inputFile = new In(WORDS_FILE);
        System.out.println("Loading word list from file...");
        NUM_OF_WORDS = 0;
        while (!inputFile.isEmpty()) {
            DICTIONARY[NUM_OF_WORDS++] = inputFile.readString().toLowerCase();
        }
        System.out.println(NUM_OF_WORDS + " words loaded.");
    }

    public static boolean isInHand(String input, String hand) {
        boolean found = false;
        for (char letter : input.toCharArray()) {
            for (int i = 0; i < hand.length(); i++) {
                if (letter == hand.charAt(i)) {
                    found = true;
                    hand = hand.substring(0, i) + hand.substring(i + 1);
                    break;
                }
            }
            if (!found) {
                return false;
            }
            found = false;
        }
        return true;
    }

    public static String remove(String str1, String str2) {
        for (char letter : str2.toCharArray()) {
            str1 = str1.replaceFirst(Character.toString(letter), "");
        }
        return str1;
    }

    public static boolean subsetOf(String str1, String str2) {
        for (char letter : str1.toCharArray()) {
            if (!str2.contains(Character.toString(letter))) {
                return false;
            }
            str2 = str2.replaceFirst(Character.toString(letter), "");
        }
        return true;
    }

    public static boolean isWordInDictionary(String word) {
        for (String dictWord : DICTIONARY) {
            if (dictWord != null && dictWord.equals(word)) {
                return true;
            }
        }
        return false;
    }

    public static int wordScore(String word) {
        word = word.toLowerCase();
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
        StringBuilder hand = new StringBuilder();
        int randomIndexA = (int) (Math.random() * HAND_SIZE);
        int randomIndexE;
        do {
            randomIndexE = (int) (Math.random() * HAND_SIZE);
        } while (randomIndexE == randomIndexA);

        for (int i = 0; i < HAND_SIZE; i++) {
            if (i == randomIndexA) {
                hand.append('a');
            } else if (i == randomIndexE) {
                hand.append('e');
            } else {
                hand.append((char) ('a' + Math.random() * 26));
            }
        }
        return hand.toString();
    }

    public static void playHand(String hand) {
        int score = 0;
        In input = new In();
        while (!hand.isEmpty()) {
            System.out.println("Current Hand: " + hand);
            System.out.println("Enter a word, or '.' to finish playing this hand:");
            String inputWord = input.readString();
            if (inputWord.equals(".")) {
                break;
            }
            if (isWordInDictionary(inputWord) && isInHand(inputWord, hand)) {
                int wordScore = wordScore(inputWord);
                score += wordScore;
                hand = remove(hand, inputWord);
                System.out.println(inputWord + " earned " + wordScore + " points. Total score: " + score + " points.");
            } else {
                System.out.println("Invalid word. Try again.");
            }
        }
        System.out.println("End of hand. Total score: " + score + " points.");
    }

    public static void playGame() {
        init();
        In input = new In();
        while (true) {
            System.out.println("Enter 'n' to deal a new hand, or 'e' to end the game:");
            String command = input.readString();
            if (command.equals("e")) {
                break;
            } else if (command.equals("n")) {
                String hand = createHand();
                playHand(hand);
            } else {
                System.out.println(command + " is not a valid command.");
            }
        }
    }

    public static void main(String[] args) {
        playGame();
    }
}
