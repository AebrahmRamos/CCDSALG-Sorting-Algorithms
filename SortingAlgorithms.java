/* This file contains implementations of sorting algorithms.
 * You are NOT allowed to modify any of the given method headers.
 */

public class SortingAlgorithms {

    // May create helper functions, use 'private' access modifier
    // - - - - - - - HELPER FUNCTIONS - - - - - - - 

    /*
     * Swaps the values of index i and j
     * Record[] arr - an array consisting of objects we are swapping
     * int p - element to swap
     * int r - element to swap
     */
    private void swap(Record[] arr, int i, int j) {
        Record temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /*
     * Returns the correct position/index of the pivot element
     * Record[] arr - an array consisting of objects we are sorting
     * int p - is the index of the first element of the partition
     * int r - is the last element of the partition
     *  
     * @returns i+1 as the index of the pivot element
     */
    private int partition(Record[] arr, int p, int r){
        int pivotIndex = getPivotElement(arr, p, r); // "median of three" to determine the pivot element
        swap(arr, pivotIndex, r); // Shift the pivot to the last element
        int pivot = arr[r].getIdNumber(), i = p-1, j;

        for ( j = p; j < r; j++) {
            if (arr[j].getIdNumber() < pivot) {
                i++;
                swap(arr, i, j); 
            }
        }
        
        swap(arr, i+1, j);
        return(i+1);
    }

    private int getPivotElement(Record[] arr, int p, int r) {
        int mid = (p + r) / 2; // (low + high) / 2

        // if start element is greater than mid element
        if (arr[p].getIdNumber() > arr[mid].getIdNumber()) {
            swap(arr, p, mid);
        }

        // if start element is greater than last element
        if (arr[p].getIdNumber() > arr[r].getIdNumber()) {
            swap(arr, p, r);
        }

        // if mid element is greater than last elemetn
        if (arr[mid].getIdNumber() > arr[r].getIdNumber()) {
            swap(arr, mid, r);
        }
        return mid;
    }

    /*
     * Merges two subarrays of Record[] into a single sorted subarray.
     * This function assumes that the subarrays are already sorted.
     * First subarray is records from left to mid (inclusive).
     * Second subarray is records from mid+1 to right (inclusive).
     * It merges them in a sorted order back into the original array.
     *
     *   Record[] arr - the original array containing both subarrays
     *   Record[] left - the first sorted subarray
     *   Record[] right - the second sorted subarray
     *   int leftSize - the size of the first subarray
     *   int rightSize - the size of the second subarray
     */
    private void merge(Record[] arr, Record[] left, Record[] right, int leftSize, int rightSize) {
        int i = 0, j = 0, k = 0;

        while (i < leftSize && j < rightSize) {
            if (left[i].getIdNumber() <= right[j].getIdNumber()) {
                arr[k++] = left[i++];
            } else {
                arr[k++] = right[j++];
            }
        }
        while (i < leftSize) {
            arr[k++] = left[i++];
        }
        while (j < rightSize) {
            arr[k++] = right[j++];
        }
    }

    
    // - - - - - - - SORTING ALGORITHMS - - - - - - - 
    public void insertionSort(Record[] arr, int n) {
        for(int i = 1; i < n; i++)
            for (int j = i; j > 0 && arr[j-1].getIdNumber() > arr[j].getIdNumber(); j--)
                swap(arr, j-1, j);
    }

    public void selectionSort(Record[] arr, int n) {
        // TODO: Implement this sorting algorithm here.

    }

    /*
     * Implementation of the Merge Sort algorithm.
     * Merge Sort is a divide-and-conquer algorithm that divides the array
     * into two halves, recursively sorts each half, and then merges the
     * sorted halves back together.
     *
     * Parameters:
     *   Record[] arr - an array of records to be sorted
     *   int n - the number of records in the array
     */
    public void mergeSort(Record[] arr, int n) {
        if (n<2) {
            return;
        }

        int mid = n/2;

        Record[] left = new Record[mid];
        Record[] right = new Record[n-mid];

        System.arraycopy(arr, 0, left, 0, mid);
        System.arraycopy(arr, mid, right, 0, n-mid);

        mergeSort(left, mid);
        mergeSort(right, n-mid);

        merge(arr, left, right, mid, n-mid);
    }

    /*
     * Define AT LEAST ONE more sorting algorithm here, apart from the
     * ones given above. Make sure that the method accepts an array of
     * records
     */

    /*
     * Implementation of Quick Sort algorithm
     * Record[] arr - contains the array of objects we are sorting
     * int p - is the index of the first element of the array
     * int r - is the last element of the array
     */
    public void quickSort(Record[] arr, int p, int r) {
        if (p < r) {
            int partitionIndex = partition(arr, p, r);

            quickSort(arr, p, partitionIndex-1);
            quickSort(arr, partitionIndex+1, r);
        }
    }
}