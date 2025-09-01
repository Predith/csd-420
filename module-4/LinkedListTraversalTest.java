//Kristopher Kuenning
//08/31/2025
//CSD420
//Module 4 Programming Assignment


import java.util.LinkedList;
import java.util.Iterator;

public class LinkedListTraversalTest {
    public static void main(String[] args) {
        // Test with 50,000 integers
        runTest(50_000);

        // Test with 500,000 integers
        runTest(500_000);
    }

    public static void runTest(int size) {
        System.out.println("\nTesting with " + size + " integers:");

        // Create and populate LinkedList
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            list.add(i);
        }

        // Traverse using Iterator
        long startTime = System.nanoTime();
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            iterator.next();
        }
        long endTime = System.nanoTime();
        long iteratorTime = endTime - startTime;
        System.out.println("Iterator traversal time: " + iteratorTime / 1_000_000.0 + " ms");

        // Traverse using get(index)
        startTime = System.nanoTime();
        for (int i = 0; i < list.size(); i++) {
            list.get(i);
        }
        endTime = System.nanoTime();
        long getTime = endTime - startTime;
        System.out.println("get(index) traversal time: " + getTime / 1_000_000.0 + " ms");

        // Compare
        System.out.println("Difference (get - iterator): " + (getTime - iteratorTime) / 1_000_000.0 + " ms");
    }
}

/*
Notes:
Running an integrator to traverse a LinkedList is efficient due to the internal pointers, for both 50,000 and 500,000 integers.
The get(index) method of LinkedList is inefficient due to no providing random access like ArrayList.
Both integers run quickly unless using get(index) traversal which will take longer to approach the intended target.
 */
