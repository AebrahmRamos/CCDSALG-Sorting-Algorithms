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

    public void merge(Record[] arr, int l, int m, int r) {

        //find subsizes of two subarrays to be merged
        int n1 = m - l + 1;
        int n2 = r - m;

        //create temp arrays
        Record[] L = new Record[n1];
        Record[] R = new Record[n2];

        //copy data to temp arrays
        for(int i = 0; i < n1; i++) {
            L[i] = arr[l + i];
        }
        for(int j = 0; j < n2; j++) {
            R[j] = arr[m + 1 + j];
        }

        //merge the temp arrays

        //initial indexes of first and second subarrays
        int i = 0, j = 0;

        //initial index of merged subarray array
        int k = l;
        while(i < n1 && j < n2) {
            if(L[i].getIdNumber() <= R[j].getIdNumber()) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        //copy remaining elements of L[] if any
        while(i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        //copy remaining elements of R[] if any
        while(j < n2) {
            arr[k] = R[j];
            j++;
            k++;
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
    public void mergeSort(Record[] arr, int p, int r) {
        if(p < r) {
            //find the middle point
            int m = (p + r) / 2;

            //sort first and second halves
            mergeSort(arr, p, m);
            mergeSort(arr, m + 1, r);

            //merge the sorted halves
            merge(arr, p, m, r);
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
