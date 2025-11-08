package auditoriski.aud1;

public class StringPrefix {

    public static boolean isPrefix(String first, String second){
        boolean isPrefix = true;
        if (first.length() > second.length()) return false;
        for (int i = 0; i < first.length(); i++) {
            if (first.charAt(i) != second.charAt(i)){
                isPrefix = false;
                break;
            }
        }
        return isPrefix;
    }

    public static void main(String[] args) {

        System.out.println("TestCase1: " + isPrefix("test", "Test"));
        System.out.println("TestCase2: " + isPrefix("test", "test"));
        System.out.println("TestCase3: " + isPrefix("test", "tset"));
    }
}
