package thniti.Services;

/**
 *
 * @author Lenovo
 */
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BadWordsFilter {
    private List<String> badWords;

    public BadWordsFilter() {
        badWords = new ArrayList<>();
        // Load bad words from a text file
        try {
            File file = new File("C:\\Users\\21692\\Desktop\\badwords.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String word = scanner.nextLine();
                badWords.add(word);
            }
            scanner.close();
        } catch (IOException e) {
            System.out.println("Error loading bad words: " + e.getMessage());
        }
    }

    public boolean containsBadWord(String text) {
        for (String badWord : badWords) {
            if (text.toLowerCase().contains(badWord.toLowerCase())) {
                return true;
            }
        }
        return false;
    }
}