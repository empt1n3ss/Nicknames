public class SameLetters {
    public static boolean isSameLetters(String text) {
        for (int i = 1; i < text.length(); i++) {
            if (text.charAt(i) != text.charAt(0)) {
                return false;
            }
        }
        return true;
    }
}
