/* This file contains implementations of sorting algorithms.
 * You are NOT allowed to modify any of the given method headers.
 */

public class SortingAlgorithms {

    // May create helper functions, use 'private' access modifier
    // - - - - - - - HELPER FUNCTIONS - - - - - - - 

    /*
     * Swaps the values of index i and and j
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

    
    // - - - - - - - SORTING ALGORITHMS - - - - - - - 
    public void insertionSort(Record[] arr, int n) {
        for(int i = 1; i < n; i++)
            for (int j = i; j > 0 && arr[j-1].getIdNumber() > arr[j].getIdNumber(); j--)
                swap(arr, j-1, j);
    }

    public void selectionSort(Record[] arr, int n) {
        // TODO: Implement this sorting algorithm here.

    }

    public void mergeSort(Record[] arr, int n) {
        // TODO: Implement this sorting algorithm here.
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