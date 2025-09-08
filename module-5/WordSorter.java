//Kristopher Kuenning
//09/07/2025
//CSD420
//Module 5 Programming Assignment

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.Iterator;

public class WordSorter {

    public static void main(String[] args) {
        // Use a TreeSet to automatically sort words and remove duplicates
        Set<String> words = new TreeSet<>();

        try {
            // Reference the file directly
            File file = new File("collection_of_words.txt");
            Scanner scanner = new Scanner(file);

            // Read each word and add to TreeSet
            while (scanner.hasNext()) {
                String word = scanner.next().toLowerCase().replaceAll("[^a-z]", "");
                if (!word.isEmpty()) {
                    words.add(word);
                }
            }
            scanner.close();

            // Display in ascending order
            System.out.println("Words in Ascending Order:");
            for (String word : words) {
                System.out.println(word);
            }

            // Display in descending order
            System.out.println("\nWords in Descending Order:");
            Iterator<String> descIterator = ((TreeSet<String>) words).descendingIterator();
            while (descIterator.hasNext()) {
                System.out.println(descIterator.next());
            }

        } catch (FileNotFoundException e) {
            System.out.println("The file 'collection_of_words.txt' was not found.");
        }
    }
}
