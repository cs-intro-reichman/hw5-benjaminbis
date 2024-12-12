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

    public static boolean isWordInDictionary(String word) {
        for (int i = 0; i < DICTIONARY.length; i++) {
            if (word.equals(DICTIONARY[i])) {
                return true;
            }
        }
        return false;
    }

    public static int wordScore(String word) {
        int sum = 0;
        char c;
        for (int i = 0; i < word.length(); i++) {
            c = word.charAt(i);
            sum += SCRABBLE_LETTER_VALUES[(int) word.charAt(i) - 97];
        }
        sum *= word.length();
        sum += (word.length() == HAND_SIZE ? 50 : 0);
        sum += (MyString.subsetOf("runi", word) ? 1000 : 0);
        return sum;
    }

    public static String frequencyOfCharBasedRandomStringOfLetters(int size) {
        int[] frequencyArr = new int[26];
        int currCharFrequencySum;
        for (int i = 97; i <= 122; i++) {
            currCharFrequencySum = 0;
            for (int j = 0; j < DICTIONARY.length; j++) {
                currCharFrequencySum += (MyString.countChar(DICTIONARY[j], ((char) i)));
            }
            frequencyArr[i - 97] = currCharFrequencySum;
        }
        int[] cumalativeWeightsOfChars = new int[26];
        cumalativeWeightsOfChars[0] = frequencyArr[0];
        for (int i = 1; i < 26; i++) {
            cumalativeWeightsOfChars[i] = cumalativeWeightsOfChars[i - 1] + frequencyArr[i];
        }
        int totalSum = cumalativeWeightsOfChars[25];
        String randomStringBasedOnFrequency = "";
        for (int i = 0; i < size; i++) {
            int randNum = (int) (Math.random() * totalSum);
            for (int j = 0; j < 25; j++) {
                if (randNum <= cumalativeWeightsOfChars[j]) {
                    randomStringBasedOnFrequency = randomStringBasedOnFrequency + ((char) (j + 97));
                    break;
                }
            }
        }
        return randomStringBasedOnFrequency;
    }

    public static String createHand() {
        String newHand = frequencyOfCharBasedRandomStringOfLetters(HAND_SIZE - 2);
        newHand = MyString.insertCharRandomly('a', newHand);
        newHand = MyString.insertCharRandomly('e', newHand);
        return newHand;
    }

    public static void playHand(String hand) {
        int score = 0;
        In in = new In();
        while (hand.length() > 0) {
            System.out.println("Current Hand: " + MyString.spacedString(hand));
            System.out.println("Enter a word, or '.' to finish playing this hand:");
            String input = in.readString();
            if (input.length() == 1 && input.charAt(0) == '.') {
                break;
            }
            if (MyString.subsetOf(input, hand)) {
                if (isWordInDictionary(input)) {
                    score += wordScore(input);
                    hand = MyString.remove(hand, input);
                    System.out.println(input + " earned " + wordScore(input) + " points. Score: " + score + " points");
                } else {
                    System.out.println("No such word in the dictionary. Try again.");
                }
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
            if (input.length() == 1 && input.charAt(0) == 'n') {
                playHand(createHand());
            } else if (input.length() == 1 && input.charAt(0) == 'e') {
                break;
            } else {
                System.out.println("Invalid input. Please try again.");
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
}
