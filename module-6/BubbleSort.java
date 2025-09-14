import java.util.Comparator;

/**
 * BubbleSort.java
 *
 * This program demonstrates two generic bubble sort methods:
 * 1. bubbleSort using the Comparable interface
 * 2. bubbleSort using the Comparator interface
 *
 * Key concepts:
 *  - Practice implementing sorting algorithms
 *  - Understanding generics in Java
 *  - Using interfaces like Comparable and Comparator
 *
 * Kristopher Kuenning
 * 09/14/2025
 * CSD420
 * Module 6 Programming Assignment
 */
public class BubbleSort {

    /**
     * Generic bubble sort using Comparable interface.
     * Sorts the array in ascending order.
     *
     * @param <E>   The type of elements in the array
     * @param list  The array to be sorted
     */
    public static <E extends Comparable<E>> void bubbleSort(E[] list) {
        for (int i = 0; i < list.length - 1; i++) {
            for (int j = 0; j < list.length - 1 - i; j++) {
                if (list[j].compareTo(list[j + 1]) > 0) {
                    // Swap elements
                    E temp = list[j];
                    list[j] = list[j + 1];
                    list[j + 1] = temp;
                }
            }
        }
    }

    /**
     * Generic bubble sort using Comparator interface.
     * Sorts the array based on the provided comparator.
     *
     * @param <E>        The type of elements in the array
     * @param list       The array to be sorted
     * @param comparator The comparator defining the sort order
     */
    public static <E> void bubbleSort(E[] list, Comparator<? super E> comparator) {
        for (int i = 0; i < list.length - 1; i++) {
            for (int j = 0; j < list.length - 1 - i; j++) {
                if (comparator.compare(list[j], list[j + 1]) > 0) {
                    // Swap elements
                    E temp = list[j];
                    list[j] = list[j + 1];
                    list[j + 1] = temp;
                }
            }
        }
    }

    /**
     * Helper method to print an array.
     *
     * @param <E>   The type of elements in the array
     * @param array The array to be printed
     */
    public static <E> void printArray(E[] array) {
        for (E element : array) {
            System.out.print(element + " ");
        }
        System.out.println();
    }

    /**
     * Main method to test both bubble sort methods.
     */
    public static void main(String[] args) {
        // Example array of integers
        Integer[] intArray = {5, 3, 4, 9, 0, 1, 2, 7, 6, 8};
        System.out.println("Original Integer Array:");
        printArray(intArray);

        // Sorting using Comparable
        bubbleSort(intArray);
        System.out.println("Sorted with Comparable:");
        printArray(intArray);

        // Example array of strings
        String[] stringArray = {"Banana", "Apple", "Orange", "Mango"};
        System.out.println("\nOriginal String Array:");
        printArray(stringArray);

        // Sorting using Comparator (alphabetical order)
        bubbleSort(stringArray, Comparator.naturalOrder());
        System.out.println("Sorted with Comparator:");
        printArray(stringArray);

        // Sorting using Comparator (reverse order)
        bubbleSort(stringArray, Comparator.reverseOrder());
        System.out.println("Sorted with Comparator (Reverse Order):");
        printArray(stringArray);
    }
}
