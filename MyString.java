public class MyString {
    public static void main(String args[]) {
        String greeting = "hello";
        System.out.println(countChar(greeting, 'h'));
        System.out.println(countChar(greeting, 'l'));
        System.out.println(countChar(greeting, 'z'));
        System.out.println(spacedString(greeting));
    }

    public static int countChar(String inputStr, char targetChar) {
        int occurrenceCount = 0;
        if (!(inputStr instanceof String) || (inputStr.length() == 0)) {
            return 0;
        }
        for (int i = 0; i < inputStr.length(); i++) {
            if (inputStr.charAt(i) == targetChar) {
                occurrenceCount++;
            }
        }
        return occurrenceCount;
    }

    public static boolean subsetOf(String smallStr, String largeStr) {
        if (smallStr.length() > largeStr.length()) {
            return false;
        }
        if (smallStr.isEmpty()) return true;
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
        String spacedStr = "";
        for (int i = 0; i < inputStr.length(); i++) {
            spacedStr += inputStr.charAt(i);
            if (i < inputStr.length() - 1) {
                spacedStr += " ";
            }
        }
        return spacedStr;
    }

    public static String randomStringOfLetters(int length) {
        String randomLetters = "";
        for (int i = 0; i < length; i++) {
            int randomCharCode = (int) (Math.random() * (122 - 97 + 1)) + 97;
            char randomLetter = (char) randomCharCode;
            randomLetters += randomLetter;
        }
        return randomLetters;
    }

    public static String remove(String inputStr1, String inputStr2) {
        for (int i = 0; i < inputStr2.length(); i++) {
            char currentChar = inputStr2.charAt(i);
            if (inputStr1.contains(Character.toString(currentChar))) {
                inputStr1 = inputStr1.replaceFirst(Character.toString(currentChar), "");
            }
        }
        return inputStr1;
    }

    public static String insertCharRandomly(char randomChar, String inputStr) {
        int randomIndex = (int) (Math.random() * (inputStr.length() + 1));
        String resultStr = inputStr.substring(0, randomIndex) + randomChar + inputStr.substring(randomIndex);
        return resultStr;
    }
}
