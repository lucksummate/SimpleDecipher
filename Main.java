import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static List<String> swapHistory = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Encoded Message (Type 'exit' to quit):");
            String encoded = scanner.nextLine();
            swapHistory.add(encoded);

            if (encoded.equalsIgnoreCase("exit")) {
                break;
            }

            fullCount(encoded, scanner);
        }

        System.out.println("Program terminated.");
        scanner.close();
    }

    public static void fullCount(String encoded, Scanner scanner) {
        String filteredString = encoded.toLowerCase();

        int[] charCount = new int[26];
        for (char c : filteredString.toCharArray()) {
            if (Character.isLetter(c)) {
                charCount[c - 'a']++;
            }
        }

        for (int i = 0; i < charCount.length; i++) {
            if (charCount[i] > 0) {
                float n = 26;
                Float percentage = (charCount[i]/n) * 100;
                System.out.println((char) ('a' + i) + " " + percentage + "%");
            }
        }

        while (true) {
            System.out.println("Enter the letter you want to replace (or 'undo' to undo the last swap): ");
            String input = scanner.nextLine().toLowerCase();

            if (input.equals("undo")) {
                undoLastSwap();
                return;
            }
            if (input.equals("exit")) {
                System.exit(0);
            }

            char letterToReplace = input.charAt(0);

            System.out.println("Enter the character to replace with: ");
            char replacementChar = scanner.nextLine().charAt(0);

            String swapped = swapSpecificLetter(swapHistory.get(swapHistory.size() - 1), letterToReplace, replacementChar);
            saveSwap(swapped);
            System.out.println("Swapped Message: " + swapped);
        }
    }

    public static String swapSpecificLetter(String input, char letterToReplace, char replacementChar) {
        char[] charArray = input.toCharArray();

        for (int i = 0; i < charArray.length; i++) {
            if (Character.toLowerCase(charArray[i]) == letterToReplace) {
                charArray[i] = replacementChar;
            }
        }

        return new String(charArray);
    }

    public static void saveSwap(String swappedMessage) {
        swapHistory.add(swappedMessage);
    }

    public static void undoLastSwap() {
        if (swapHistory.isEmpty()) {
            System.out.println("No swaps to undo.");
            return;
        }

        // Remove the last swapped message from history
        swapHistory.remove(swapHistory.size() - 1);

        if (swapHistory.isEmpty()) {
            System.out.println("Undo complete. Original message restored.");
        } else {
            System.out.println("Undo complete. Last swapped message: " + swapHistory.get(swapHistory.size() - 1));
        }
    }
}
