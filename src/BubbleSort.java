import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

public class BubbleSort {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
                
        System.out.print("Enter the length of the array: ");
        int arrayLength = scanner.nextInt();
        
        int[] randomArray = createRandomArray(arrayLength);
        
        System.out.print("Enter the filename to write the array to: ");
        String writeFilename = scanner.next();
        
        writeArrayToFile(randomArray, writeFilename);
        System.out.println("Array written to file: " + writeFilename);
        
        System.out.print("Enter the filename to read the array from: ");
        String readFilename = scanner.next();
        
        int[] readArray = readFileToArray(readFilename);
        if (readArray != null) {
            System.out.println("Array read from file: ");
            for (int num : readArray) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
        
        System.out.println("Sorting the array...");
        
        bubbleSort(readArray);
        
        System.out.println("Sorted array: ");
        for (int num : readArray) {
            System.out.print(num + " ");
        }
        System.out.println();
        
        scanner.close();
    }

    // Create a random array of integers between 0 and 100
    public static int[] createRandomArray(int arrayLength) {
        Random rand = new Random();
        int[] randomArray = new int[arrayLength];
        for (int i = 0; i < arrayLength; i++) {
            randomArray[i] = rand.nextInt(101);
        }
        return randomArray;
    }

    // Write the array to a file
    public static void writeArrayToFile(int[] array, String filename) {
        try (PrintWriter pw = new PrintWriter(filename)) {
            for (int val : array) {
                pw.println(val);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found. Please try again.");
        }
    }

    // Read an array from a file to an int[] variable
    public static int[] readFileToArray(String filename) {
        int[] array = null;
        try (Scanner scanner = new Scanner(new File(filename))) {
            // First, count the number of lines to determine the array size
            int lineCount = 0;
            while (scanner.hasNextLine()) {
                scanner.nextLine();
                lineCount++;
            }

            array = new int[lineCount];

            Scanner scnr = new Scanner(new File(filename));

            int i = 0;
            while (scnr.hasNextLine()) {
                array[i] = Integer.parseInt(scnr.nextLine());
                i++;
            }
            scnr.close();
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found. Please try again.");
        }
        return array;
    }

    // Bubble sort implementation
    public static void bubbleSort(int[] array) {
        int n = array.length;
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    // Swap array[j] and array[j+1]
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    swapped = true;
                }
            }
            // If no two elements were swapped, break
            if (!swapped) break;
        }
    }
}
