public class MyString {
    public static void main(String args[]) {
        String hello = "hello";
        System.out.println(countChar(hello, 'h'));
        System.out.println(countChar(hello, 'l'));
        System.out.println(countChar(hello, 'z'));
        System.out.println(spacedString(hello));
        System.out.println(subsetOf("sap", "space"));
        System.out.println(subsetOf("spa", "space"));
        System.out.println(subsetOf("pass", "space"));
        System.out.println(subsetOf("c", "space"));
        System.out.println(randomStringOfLetters(10));
        System.out.println(remove("committee", "meet"));
        System.out.println(insertRandomly('z', "hello"));
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
            if (countChar(str1, str1.charAt(i)) > countChar(str2, str1.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static String spacedString(String str) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            result.append(str.charAt(i));
            if (i < str.length() - 1) {
                result.append(" ");
            }
        }
        return result.toString();
    }

    public static String randomStringOfLetters(int n) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < n; i++) {
            char randomChar = (char) ('a' + (int) (Math.random() * 26));
            result.append(randomChar);
        }
        return result.toString();
    }

    public static String remove(String str1, String str2) {
        StringBuilder result = new StringBuilder(str1);
        for (int i = 0; i < str2.length(); i++) {
            char c = str2.charAt(i);
            int index = result.indexOf(String.valueOf(c));
            if (index != -1) {
                result.deleteCharAt(index);
            }
        }
        return result.toString();
    }

    public static String insertRandomly(char ch, String str) {
        int randomIndex = (int) (Math.random() * (str.length() + 1));
        StringBuilder result = new StringBuilder(str);
        result.insert(randomIndex, ch);
        return result.toString();
    }
}
