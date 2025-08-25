//Kristopher Kuenning
//8/24/2025
//CSD420
//Module 2 Programming Assignment

import java.io.*;

public class ReadDataFile {
    public static void main(String[] args) {
        String filename = "KuenningDataFile.dat";

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            System.out.println("Contents of " + filename + ":");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}
