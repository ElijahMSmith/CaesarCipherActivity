public class Cipher {
    public static void main(String[] args) {
        if (args.length < 1 || !(args[0].equals("-e") || args[0].equals("-d")))
            throw new Error("ERROR: Flag -e or -d must be the first argument provided.");

        int shift = 0;
        boolean encrypt = args[0].equals("-e");

        if (encrypt) {
            if (args.length < 2)
                throw new Error("ERROR: -e flag must be followed by a valid shift index.");
            shift = Integer.parseInt(args[1]);
        }

        StringBuilder build = new StringBuilder();

        for (int i = encrypt ? 2 : 1; i < args.length; i++)
            build.append(args[i] + " ");

        if (encrypt)
            encrypt(build.toString().trim(), shift);
        else
            decrypt(build.toString().trim());
    }

    public static void encrypt(String plaintext, int shift) {
        String cipher = "ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZ";

        System.out.println("Encrypted with shift = " + shift);
        plaintext = plaintext.toUpperCase();
        StringBuilder build = new StringBuilder();
        for (char c : plaintext.toCharArray()) {
            if (Character.isAlphabetic(c))
                build.append(cipher.charAt(cipher.indexOf(c) + shift));
            else
                build.append(c);
        }
        System.out.println(build.toString());
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
