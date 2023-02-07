import java.util.Scanner;

public class RailFence {
    public static void main(String[] args) {
        if (args.length < 1 || !(args[0].equals("-e") || args[0].equals("-d")))
            throw new Error("ERROR: Flag -e or -d must be the first argument provided.");

        int rails = 0;
        boolean encrypt = args[0].equals("-e");

        if (encrypt) {
            if (args.length < 2)
                throw new Error("ERROR: -e flag must be followed by a valid shift index.");
            rails = Integer.parseInt(args[1]);
        }

        StringBuilder build = new StringBuilder();

        for (int i = encrypt ? 2 : 1; i < args.length; i++)
            build.append(args[i] + " ");

        if (encrypt)
            encrypt(build.toString().trim(), rails);
        else
            decrypt(build.toString().trim());
    }

    public static void encrypt(String plaintext, int rails) {
        System.out.println("Encrypting '" + plaintext + "' with " + rails + " rails");
        plaintext = plaintext.toUpperCase();

        String[] railText = new String[rails];
        for (int i = 0; i < railText.length; i++)
            railText[i] = "";

        int index = 0;
        for (char c : plaintext.toCharArray()) {
            for (int i = 0; i < rails; i++)
                if (i == index)
                    railText[i] += c;
                else
                    railText[i] += '.';

            index = (index + 1) % rails;
        }

        index = 0;
        while (index < rails) {
            String thisRail = railText[index];

            for (char c : thisRail.toCharArray())
                System.out.print(" " + (c == ' ' ? '_' : c) + " ");

            System.out.println();
            index++;
        }

        System.out.println("Fully encrypted: " + String.join("", railText).replace(".", ""));
    }

    public static void decrypt(String encrypted) {
        Scanner sc = new Scanner(System.in);
        int numRails = 2;

        while (true) {
            // Turn encrypted into rails array with numRails
            int mod = encrypted.length() % numRails;
            int div = encrypted.length() / numRails;

            String[] railText = new String[numRails];
            int index = 0;
            for (int railNum = 1; railNum <= numRails; railNum++) {
                railText[railNum - 1] = "";

                int add = div + (railNum <= mod ? 1 : 0);
                railText[railNum - 1] = encrypted.substring(index, index + add);
                index += add;
            }

            // Print the rails out like before
            index = 0;
            int trailingCount = mod;
            while (index < numRails) {
                trailingCount--;
                if (trailingCount < 0)
                    trailingCount = numRails - 1;

                String thisRail = railText[index];

                // Add leading periods
                for (int i = 0; i < index; i++)
                    System.out.print(" . ");

                char[] railChars = thisRail.toCharArray();
                for (int i = 0; i < railChars.length; i++) {
                    char c = railChars[i];
                    System.out.print(" " + (c == ' ' ? '_' : c) + " ");

                    // Print spacing periods between characters
                    if (i != railChars.length - 1)
                        for (int j = 0; j < numRails - 1; j++)
                            System.out.print(" . ");
                }

                for (int i = 0; i < trailingCount; i++)
                    System.out.print(" . ");

                System.out.println();
                index++;
            }

            System.out.print("\nPossible plaintext: ");
            for (int c = 0; c < railText[0].length(); c++) {
                for (int r = 0; r < railText.length; r++) {
                    if (railText[r].length() <= c)
                        continue;
                    System.out.print(railText[r].charAt(c));
                }
            }

            System.out.println("\n\nTry Again?");
            sc.nextLine();
            numRails++;
        }
    }

}
