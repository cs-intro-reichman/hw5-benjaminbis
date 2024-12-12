public class MyString {
    public static void main(String args[]) {
        String greeting = "hello";
        System.out.println(countChar(greeting, 'h'));
        System.out.println(countChar(greeting, 'l'));
        System.out.println(countChar(greeting, 'z'));
        System.out.println(spacedString(greeting));
    }

    public static int countChar(String inputStr, char targetChar) {
        int count = 0;
        if (inputStr == null || inputStr.isEmpty()) {
            return 0;
        }
        for (int i = 0; i < inputStr.length(); i++) {
            if (inputStr.charAt(i) == targetChar) {
                count++;
            }
        }
        return count;
    }

    public static boolean subsetOf(String smallStr, String largeStr) {
        if (smallStr.length() > largeStr.length()) {
            return false;
        }
        if (smallStr.isEmpty()) {
            return true;
        }
        for (int i = 0; i < smallStr.length(); i++) {
            char currentChar = smallStr.charAt(i);
            if (!largeStr.contains(Character.toString(currentChar))) {
                return false;
            }
            largeStr = largeStr.replaceFirst(Character.toString(currentChar), "");
        }
        return true;
    }

    public static String spacedString(String inputStr) {
        StringBuilder spacedStr = new StringBuilder();
        for (int i = 0; i < inputStr.length(); i++) {
            spacedStr.append(inputStr.charAt(i));
            if (i < inputStr.length() - 1) {
                spacedStr.append(" ");
            }
        }
        return spacedStr.toString();
    }

    public static String randomStringOfLetters(int length) {
        StringBuilder randomLetters = new StringBuilder();
        for (int i = 0; i < length; i++) {
            char randomLetter = (char) ('a' + (Math.random() * 26));
            randomLetters.append(randomLetter);
        }
        return randomLetters.toString();
    }

    public static String remove(String inputStr1, String inputStr2) {
        for (int i = 0; i < inputStr2.length(); i++) {
            char currentChar = inputStr2.charAt(i);
            inputStr1 = inputStr1.replaceFirst(Character.toString(currentChar), "");
        }
        return inputStr1;
    }

    public static String insertCharRandomly(char randomChar, String inputStr) {
        int randomIndex = (int) (Math.random() * (inputStr.length() + 1));
        return inputStr.substring(0, randomIndex) + randomChar + inputStr.substring(randomIndex);
    }
}
