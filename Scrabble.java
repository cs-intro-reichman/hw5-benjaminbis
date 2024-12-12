public class Scrabble {

    static final String WORDS_FILE = "dictionary.txt";

    static final int[] SCRABBLE_LETTER_VALUES = {1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3, 1, 1, 3, 10, 1, 1, 1, 1, 4, 4, 8, 4, 10};

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

    public static boolean isInHand(String input, String hand) {
        boolean found = false;
        for (char str1Letter : input.toCharArray()) {
            for (int i = 0; i < hand.length(); i++) {
                if (str1Letter == hand.charAt(i)) {
                    found = true;
                    hand = hand.substring(0, i) + hand.substring(i + 1);
                    i = hand.length();
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
            for (int i = 0; i < str1.length(); i++) {
                if (letter == str1.charAt(i)) {
                    str1 = str1.substring(0, i) + str1.substring(i + 1);
                    i = str1.length();
                }
            }
        }
        return str1;
    }

    public static boolean subsetOf(String str1, String str2) {
        boolean found = false;
        for (char str1Letter : str1.toCharArray()) {
            for (int i = 0; i < str2.length(); i++) {
                if (str1Letter == str2.charAt(i)) {
                    found = true;
                    str2 = str2.substring(0, i) + str2.substring(i + 1);
                    i = str2.length();
                }
            }
            if (!found) {
                return false;
            }
            found = false;
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
        word = word.toLowerCase();
        int score = 0;
        for (char letter : word.toCharArray()) {
            score += SCRABBLE_LETTER_VALUES[(int) letter - 'a'];
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
        char[] hand = new char[HAND_SIZE];
        int randomIndexOfA = (int) (Math.random() * HAND_SIZE);
        int randomIndexOfE = (int) (Math.random() * HAND_SIZE);
        hand[randomIndexOfA] = 'a';
        while (randomIndexOfA == randomIndexOfE) {
            randomIndexOfE = (int) (Math.random() * HAND_SIZE);
        }
        hand[randomIndexOfE] = 'e';
        for (int i = 0; i < HAND_SIZE; i++) {
            if (i != randomIndexOfA && i != randomIndexOfE) {
                hand[i] = (char) ((int) (Math.random() * 26) + 'a');
            }
        }
        String retStr = "";
        for (char letter : hand) {
            retStr += letter;
        }
        return retStr;
    }

    public static void playHand(String hand) {
        int n = hand.length();
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
                int inputScore = wordScore(input);
                score += inputScore;
                hand = remove(hand, input);
                System.out.println(input + " earned " + inputScore + " points. Score: " + score + " points");
            } else {
                System.out.println("Invalid word. Try again.");
            }
        }
        if (hand.length() == 0) {
            System.out.println("Ran out of letters. Total score: " + score + " points");
        } else {
            System.out.println("End of hand. Total score: " + score + " points");
        }
    }

    public static void playGame() {
        init();
        In in = new In();
        while (true) {
            System.out.println("Enter n to deal a new hand, or e to end the game:");
            String input = in.readString();
            if (input.equals("e")) {
                break;
            } else if (input.equals("n")) {
                String hand = createHand();
                playHand(hand);
            } else {
                System.out.println(input + " is not a valid input.");
            }
        }
    }

    public static void main(String[] args) {
        playGame();
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
} fait moi des petits changement 
