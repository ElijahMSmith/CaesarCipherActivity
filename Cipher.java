public class Cipher {
    public static void main(String[] args) {
        int d1 = 7;
        int d2 = 23;
        int d3 = 15;
        int d4 = 3;

        int d5 = 12;
        int d6 = 5;

        String[] strings1 = new String[] { "Hello", "Happy Birthday", "I am an egg", "I bet you will not get this" };
        String[] strings2 = new String[] { "Meet me at the toga party!" };
        String[] strings3 = new String[] { "Do you feel like a computer hacker yet?" };
        String[] strings4 = new String[] { "You solved it!" };

        String[] strings5 = new String[] { "You Solved This Too!" };
        String[] strings6 = new String[] {"KAG EAXHQP FTUE FAA!"};
        // Double encrypted: "PFL JFCMVU KYZJ KFF!"

        encrypt(strings1, d1);
        encrypt(strings2, d2);
        encrypt(strings3, d3);
        encrypt(strings4, d4);

        encrypt(strings5, d5);
        encrypt(strings6, d6);

        decrypt("BRX VROYHG LW!");
    }

    public static void encrypt(String[] plaintext, int d) {
        String cipher = "ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZ";

        System.out.println("Encrypted with d = " + d);
        for (String s : plaintext) {
            s = s.toUpperCase();
            StringBuilder build = new StringBuilder();
            for (char c : s.toCharArray()) {
                if (Character.isAlphabetic(c))
                    build.append(cipher.charAt(cipher.indexOf(c) + d));
                else
                    build.append(c);
            }
            System.out.println(build.toString());
        }
        System.out.println();
    }

    public static void decrypt(String encrypted) {
        String cipher = "ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZ";

        encrypted = encrypted.toUpperCase();
        for (int d = 1; d <= 25; d++) {
            StringBuilder build = new StringBuilder();
            for (char c : encrypted.toCharArray()) {
                if (Character.isAlphabetic(c))
                    build.append(cipher.charAt(cipher.indexOf(c, 26) - d));
                else
                    build.append(c);
            }
            System.out.println("Trying with d=" + d + " -> " + build.toString());
        }
        System.out.println();
    }

}
