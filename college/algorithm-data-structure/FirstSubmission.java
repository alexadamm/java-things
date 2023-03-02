import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class FirstSubmission {
    public static void main(String args[]) {
        try (Scanner scanner = new Scanner(System.in);
                Scanner wordScanner = new Scanner(new File("stopwords_id.txt"))) {

            List<String> wordsToDelete = new ArrayList<>();

            while (wordScanner.hasNextLine()) {
                wordsToDelete.add(wordScanner.nextLine());
            }

            String inputString = scanner.tokens().collect(Collectors.joining(" ")).trim();
            String processedText = preprocessText(inputString, wordsToDelete);

            System.out.println(processedText);

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    public static String preprocessText(String text, List<String> wordsToDelete) {
        List<String> words = Arrays.asList(text.replaceAll("[^a-zA-Z\\s]", "")
                .toLowerCase()
                .split("\\s+"));

        Set<String> uniqueWords = new LinkedHashSet<>(words);
        uniqueWords.removeAll(wordsToDelete);

        return String.join(System.lineSeparator(), uniqueWords);
    }
}
