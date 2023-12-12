import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        String rawData = "A a b c  d e";
        String[] wordList = rawData.split("\\s+");
        for (String s : wordList) {
            System.out.println(s);
        }

        System.out.println(rawData.matches("[a-zA-Z]+"));

        for (char letter : rawData.toCharArray()) {
            if (Character.isLetter(letter)) {
                System.out.print(Character.toUpperCase(letter));
            }

            if (Character.isDigit(letter)) {

            }
        }
    }

    // Phương thức kiểm tra xem chuỗi có phải là số không
    private static boolean isNumber(String str) {
        try {
            // Sử dụng Double.parseDouble() để kiểm tra
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Phương thức kiểm tra xem chuỗi có chứa duy nhất các chữ cái không
    private static boolean isAlphabetic(String str) {
        return str.matches("[a-zA-Z]+");
    }

    // Phương thức kiểm tra xem ký tự có phải là in hoa
    private static boolean isUpperCase(char ch) {
        return Character.isUpperCase(ch);
    }

    // Phương thức kiểm tra xem ký tự có phải là in thường
    private static boolean isLowerCase(char ch) {
        return Character.isLowerCase(ch);
    }

    // Su diung String Builder duoc java cung cap san merhod reverse()
    public static boolean isAnagram1(String first, String second) {
        if (first == null || second == null) {
            return false;
        }

        if (first.length() != second.length()) {
            return false;
        }

        StringBuilder builder = new StringBuilder(second);
        return first.equals(builder.reverse().toString());
    }


    // So sanh tung doi i va len - i neu tat ca cac cap deu trung nhau thi chung nguoc nhau(len do dai str)
    public static boolean isAnagram2(String first, String second) {

        if (first == null || second == null) {
            return false;
        }

        if (first.length() != second.length()) {
            return false;
        }

        int len = first.length();
        for (int i = 0; i < len; i++) {
            if (first.charAt(i) != second.charAt(len - 1 - i)){
                return false;
            }
        }
        return true;
    }

    // kiểm tra nguyên âm
    private static boolean isVowel(char ch) {
        return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u';
    }

    // xóa ký tự dup
    public static String removeDuplicateChar(String str) {
        Set<Character> charsPresent = new HashSet<>();
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < str.length(); i++) {
            if (!charsPresent.contains(str.charAt(i))) {
                stringBuilder.append(str.charAt(i));
                charsPresent.add(str.charAt(i));
            }
        }

        return stringBuilder.toString();
    }
}
