public class MyString {
    public static void main(String args[]) {
        String hello = "hello";
        System.out.println("Count 'h': " + countChar(hello, 'h'));
        System.out.println("Count 'l': " + countChar(hello, 'l'));
        System.out.println("Count 'z': " + countChar(hello, 'z'));
        System.out.println("Spaced string: " + spacedString(hello));
        System.out.println("Subset: " + subsetOf("sap", "space"));
        System.out.println("Subset: " + subsetOf("pass", "space"));
        System.out.println("Random String of 5 letters: " + randomStringOfLetters(5));
        System.out.println("Remove 'meet' from 'committee': " + remove("committee", "meet"));
        System.out.println("Insert 'z' randomly into 'hello': " + insertRandomly('z', "hello"));
    }

    public static int countChar(String str, char ch) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ch) {
                count++;
            }
        }
        return count;
    }

    public static boolean subsetOf(String str1, String str2) {
        for (int i = 0; i < str1.length(); i++) {
            boolean found = false;
            for (int j = 0; j < str2.length(); j++) {
                if (str1.charAt(i) == str2.charAt(j)) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                return false;
            }
        }
        return true;
    }

    public static String spacedString(String str) {
        String result = "";
        for (int i = 0; i < str.length(); i++) {
            result += str.charAt(i);
            if (i < str.length() - 1) {
                result += " ";
            }
        }
        return result;
    }

    public static String randomStringOfLetters(int n) {
        String result = "";
        for (int i = 0; i < n; i++) {
            char randomChar = (char) ('a' + (int) (Math.random() * 26));
            result += randomChar;
        }
        return result;
    }

    public static String remove(String str1, String str2) {
        String result = "";
        for (int i = 0; i < str1.length(); i++) {
            boolean found = false;
            for (int j = 0; j < str2.length(); j++) {
                if (str1.charAt(i) == str2.charAt(j)) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                result += str1.charAt(i);
            }
        }
        return result;
    }

    public static String insertRandomly(char ch, String str) {
        int randomIndex = (int) (Math.random() * (str.length() + 1));
        String result = "";
        for (int i = 0; i < str.length(); i++) {
            if (i == randomIndex) {
                result += ch;
            }
            result += str.charAt(i);
        }
        if (randomIndex == str.length()) {
            result += ch;
        }
        return result;
    }
}
