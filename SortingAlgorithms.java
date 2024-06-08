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

    private void merge(Record[] arr, Record[] left, Record[] right, int nLeft, int nRight) {
        int leftCounter = 0, rightCounter = 0, arrCounter = 0;

        while (leftCounter < nLeft && rightCounter < nRight) {
            if (left[leftCounter].getIdNumber() < right[rightCounter].getIdNumber()) {
                arr[arrCounter++] = left[leftCounter++];
            } else if (left[leftCounter].getIdNumber() > right[rightCounter].getIdNumber()) {
                arr[arrCounter++] = right[rightCounter++];
            }
        }

        while (leftCounter < nLeft) {
            arr[arrCounter++] = left[leftCounter++];
        }

        while (rightCounter < nRight) {
            arr[arrCounter++] = right[rightCounter++];
        }
    }

    
    // - - - - - - - SORTING ALGORITHMS - - - - - - - 
    public void insertionSort(Record[] arr, int n) {
        int lastSorted = 0, i, j;

        for(i = 1; i < n; i++){
            for(j = lastSorted; j > 0; j--){
                if (arr[i].getIdNumber() < arr[j].getIdNumber()) {
                    arr[j+1] = arr[j];
                } else {
                    arr[j] = arr[i];
                }
            }
        }
    }

    public void selectionSort(Record[] arr, int n) {
        // TODO: Implement this sorting algorithm here.

    }

    public void mergeSort(Record[] arr, int n) {
        if (n<2) {
            return;
        }

        int mid = n/2, ctr;
        Record left[] = new Record[mid];
        Record right[] = new Record[n-mid];

        for(ctr = 0; ctr < mid; ctr++){
            left[ctr] = arr[ctr];
        }

        for(ctr = mid; ctr < n; ctr++){
            right[ctr - mid] = arr[ctr];
        }

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