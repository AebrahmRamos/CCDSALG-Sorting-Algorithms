import java.io.*;

public class Main {
    public static void main(String[] args) {
        FileReader fileReader = new FileReader();
        SortingAlgorithms sortingAlgorithms = new SortingAlgorithms();
        Record[] records = fileReader.readFile("data/random100000.txt");
        long startTime = System.currentTimeMillis(), endTime, executionTime;

        // Uncomment whichever sorting algorithm you want to test, edit if necessary
        sortingAlgorithms.insertionSort(records, records.length);
        // sortingAlgorithms.selectionSort(records, 0);
        // sortingAlgorithms.mergeSort(records, records.length);
        // sortingAlgorithms.quickSort(records, 0, records.length-1);

        endTime = System.currentTimeMillis();
        executionTime = endTime - startTime;

        System.out.println("Execution time " + executionTime + " milliseconds");
        
        // Write sorted records to a file
        writeRecords("sortedData.txt", records);
        // Uncomment the code below to print sorted records
        printRecords(records);
    }

    public static void writeRecords(String filename, Record[] records) {
        try {
            PrintWriter writer = new PrintWriter(new File(filename));

            writer.println(records.length);

            for (Record record : records) {
                writer.println(record.getIdNumber() + " " + record.getName());
            }

            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("error: " + e.getMessage());
        }
    }

    public static void printRecords(Record[] records) {
        for (Record record : records) {
            System.out.println(record.getIdNumber() + " " + record.getName());
        }
    }
}