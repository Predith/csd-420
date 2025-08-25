//Kristopher Kuenning
//8/24/2025
//CSD420
//Module 2 Programming Assignment

import java.io.*;
import java.util.Random;

public class WriteDataFile {
    public static void main(String[] args) {
        String filename = "KuenningDataFile.dat";
        Random rand = new Random();

        // Generate arrays
        int[] intArray = new int[5];
        double[] doubleArray = new double[5];

        for (int i = 0; i < 5; i++) {
            intArray[i] = rand.nextInt(100);
            doubleArray[i] = rand.nextDouble() * 100;
        }

        // Append to file
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename, true))) {
            writer.println("Integers:");
            for (int val : intArray) {
                writer.print(val + " ");
            }
            writer.println();

            writer.println("Doubles:");
            for (double val : doubleArray) {
                writer.printf("%.2f ", val);
            }
            writer.println();
            writer.println("------");
            System.out.println("Data written to " + filename);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}
