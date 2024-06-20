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

    /**
     * Merges two subarrays of arr[]
     *
     * @param arr - the original array containing both subarrays
     * @param p   - the starting index of the first subarray
     * @param q   - the ending index of the first subarray and the mid-point
     * @param r   - the ending index of the second subarray
     */
    private void merge(Record[] arr, int p, int q, int r) {
    int n1 = q - p + 1;
    int n2 = r - q;

    Record[] left = new Record[n1];
    Record[] right = new Record[n2];

    System.arraycopy(arr, p, left, 0, n1);
    System.arraycopy(arr, q + 1, right, 0, n2);

    int i = 0, j = 0, k = p;

    while (i < n1 && j < n2) {
        if (left[i].getIdNumber() <= right[j].getIdNumber()) {
            arr[k++] = left[i++];
        } else {
            arr[k++] = right[j++];
        }
    }

    while (i < n1) {
        arr[k++] = left[i++];
    }
    while (j < n2) {
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
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j].getIdNumber() < arr[minIndex].getIdNumber()) {
                    minIndex = j;
                }
            }
            swap(arr, i, minIndex);
        }
    }

    /**
     * Sorts the array arr[] using the merge sort algorithm.
     *
     * @param arr - the array to be sorted
     * @param p   - the starting index
     * @param r   - the ending index
     */
    public void mergeSort(Record[] arr, int p, int r) {
    if (p < r) {
        int q = (p + r) / 2;

        mergeSort(arr, p, q);
        mergeSort(arr, q + 1, r);

        merge(arr, p, q, r);
    }
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
