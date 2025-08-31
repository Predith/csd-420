//Kristopher Kuenning
//08/21/2025
//CSD420
//Module 3 Programming Assignment

import java.util.ArrayList;
import java.util.Random;

public class RemoveDuplicatesTest {

    // Static method that returns a new ArrayList without duplicates
    public static <E> ArrayList<E> removeDuplicates(ArrayList<E> list) {
        ArrayList<E> newList = new ArrayList<>();
        for (E element : list) {
            if (!newList.contains(element)) {
                newList.add(element);
            }
        }
        return newList;
    }

    public static void main(String[] args) {
        ArrayList<Integer> originalList = new ArrayList<>();
        Random rand = new Random();

        // Fill with 50 random numbers between 1 and 20
        for (int i = 0; i < 50; i++) {
            originalList.add(rand.nextInt(20) + 1);
        }

        // Print original list
        System.out.println("Original List:");
        System.out.println(originalList);

        // Remove duplicates
        ArrayList<Integer> noDuplicatesList = removeDuplicates(originalList);

        // Print deduplicated list
        System.out.println("\nList without duplicates:");
        System.out.println(noDuplicatesList);
    }
}
