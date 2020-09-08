import java.util.Comparator;
import java.util.LinkedList;
import java.util.Random;

/**
 * Your implementation of various sorting algorithms.
 *
 * @author Rachel Mills
 * @version 1.0
 * @userid rmills30
 * @GTID 903394578
 *
 * Collaborators: I did not work with anyone else.
 *
 * Resources: I used Tahlee Jayne's JUnits
 */
public class Sorting {

    /**
     * Implement insertion sort.
     * <p>
     * It should be:
     * in-place
     * stable
     * adaptive
     * <p>
     * Have a worst case running time of:
     * O(n^2)
     * <p>
     * And a best case running time of:
     * O(n)
     *
     * @param <T>        data type to sort
     * @param arr        the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     * @throws java.lang.IllegalArgumentException if the array or comparator is
     *                                            null
     */
    public static <T> void insertionSort(T[] arr, Comparator<T> comparator) {
        if (arr == null || comparator == null) {
            throw new java.lang.IllegalArgumentException("The array may not be sorted if it is"
                   + " null or if the comparator is null.");
        }
        for (int i = 1; i < arr.length; i++) {
            int j = i - 1;
            T key = arr[i];
            while (j >= 0 && comparator.compare(key, arr[j]) < 0) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    /**
     * Implement merge sort.
     * <p>
     * It should be:
     * out-of-place
     * stable
     * not adaptive
     * <p>
     * Have a worst case running time of:
     * O(n log n)
     * <p>
     * And a best case running time of:
     * O(n log n)
     * <p>
     * You can create more arrays to run merge sort, but at the end, everything
     * should be merged back into the original T[] which was passed in.
     * <p>
     * When splitting the array, if there is an odd number of elements, put the
     * extra data on the right side.
     * <p>
     * Hint: If two data are equal when merging, think about which subarray
     * you should pull from first
     *
     * @param <T>        data type to sort
     * @param arr        the array to be sorted
     * @param comparator the Comparator used to compare the data in arr
     * @throws java.lang.IllegalArgumentException if the array or comparator is
     *                                            null
     */
    public static <T> void mergeSort(T[] arr, Comparator<T> comparator) {
        if (arr == null || comparator == null) {
            throw new java.lang.IllegalArgumentException("The array may not be "
                    + "sorted if it is null or if the comparator is null.");
        }
        int length = arr.length;
        if (length < 2) {
            return;
        }
        int mid = (arr.length + (arr.length % 2)) / 2;
        T[] leftArr = (T[]) new Object[mid];
        T[] rightArr = (T[]) new Object[length - mid];
        for (int i = 0; i < length; i++) {
            if (i < mid) {
                leftArr[i] = arr[i];
            } else {
                rightArr[i - mid] = arr[i];
            }
        }
        mergeSort(leftArr, comparator);
        mergeSort(rightArr, comparator);
        int leftInd = 0;
        int rightInd = 0;
        int currInd = 0;
        while (leftInd < mid && rightInd < length - mid) {
            if (comparator.compare(leftArr[leftInd], rightArr[rightInd]) < 0) {
                arr[currInd] = leftArr[leftInd];
                leftInd++;
            } else {
                arr[currInd] = rightArr[rightInd];
                rightInd++;
            }
            currInd++;
        }
        while (leftInd < mid) {
            arr[currInd] = leftArr[leftInd];
            leftInd++;
            currInd++;
        }
        while (rightInd < length - mid) {
            arr[currInd] = rightArr[rightInd];
            rightInd++;
            currInd++;
        }
    }

    /**
     * Implement LSD (least significant digit) radix sort.
     * <p>
     * Make sure you code the algorithm as you have been taught it in class.
     * There are several versions of this algorithm and you may not get full
     * credit if you do not implement the one we have taught you!
     * <p>
     * Remember you CANNOT convert the ints to strings at any point in your
     * code! Doing so may result in a 0 for the implementation.
     * <p>
     * It should be:
     * out-of-place
     * stable
     * not adaptive
     * <p>
     * Have a worst case running time of:
     * O(kn)
     * <p>
     * And a best case running time of:
     * O(kn)
     * <p>
     * You are allowed to make an initial O(n) passthrough of the array to
     * determine the number of iterations you need.
     * <p>
     * At no point should you find yourself needing a way to exponentiate a
     * number; any such method would be non-O(1). Think about how how you can
     * get each power of BASE naturally and efficiently as the algorithm
     * progresses through each digit.
     * <p>
     * Refer to the PDF for more information on LSD Radix Sort.
     * <p>
     * You may use ArrayList or LinkedList if you wish, but it may only be
     * used inside radix sort and any radix sort helpers. Do NOT use these
     * classes with other sorts. However, be sure the List implementation you
     * choose allows for stability while being as efficient as possible.
     * <p>
     * Do NOT use anything from the Math class except Math.abs().
     *
     * @param arr the array to be sorted
     * @throws java.lang.IllegalArgumentException if the array is null
     */
    public static void lsdRadixSort(int[] arr) {
        if (arr == null) {
            throw new java.lang.IllegalArgumentException("Array must not be null in order to be sorted.");
        }
        int mod = 10;
        int div = 1;
        boolean cont = true;
        LinkedList<Integer>[] counter = (LinkedList<Integer>[]) new LinkedList[19];
        for (int i = 0; i < arr.length; i++) {
            counter[i] = new LinkedList<>();
        }
        while (cont) {
            cont = false;
            for (int num : arr) {
                int bucket = num / div;
                if ((bucket / 10) != 0) {
                    cont = true;
                }
                if (counter[bucket % mod + 9] == null) {
                    counter[bucket % mod + 9] = new LinkedList<Integer>();
                }
                counter[bucket % mod + 9].add(num);
            }
            int arrIdx = 0;
            for (int i = 0; i < counter.length; i++) {
                if (counter[i] != null) {
                    for (int num : counter[i]) {
                        arr[arrIdx++] = num;
                    }
                    counter[i].clear();
                }
            }
            div *= 10;
        }
    }

    /**
     * Implement kth select.
     * <p>
     * Use the provided random object to select your pivots. For example if you
     * need a pivot between a (inclusive) and b (exclusive) where b > a, use
     * the following code:
     * <p>
     * int pivotIndex = rand.nextInt(b - a) + a;
     * <p>
     * If your recursion uses an inclusive b instead of an exclusive one,
     * the formula changes by adding 1 to the nextInt() call:
     * <p>
     * int pivotIndex = rand.nextInt(b - a + 1) + a;
     * <p>
     * It should be:
     * in-place
     * <p>
     * Have a worst case running time of:
     * O(n^2)
     * <p>
     * And a best case running time of:
     * O(n)
     * <p>
     * You may assume that the array doesn't contain any null elements.
     * <p>
     * Make sure you code the algorithm as you have been taught it in class.
     * There are several versions of this algorithm and you may not get full
     * credit if you do not implement the one we have taught you!
     *
     * @param <T>        data type to sort
     * @param k          the index to retrieve data from + 1 (due to
     *                   0-indexing) if the array was sorted; the 'k' in "kth
     *                   select"; e.g. if k == 1, return the smallest element
     *                   in the array
     * @param arr        the array that should be modified after the method
     *                   is finished executing as needed
     * @param comparator the Comparator used to compare the data in arr
     * @param rand       the Random object used to select pivots
     * @return the kth smallest element
     * @throws java.lang.IllegalArgumentException if the array or comparator
     *                                            or rand is null or k is not
     *                                            in the range of 1 to arr
     *                                            .length
     */
    public static <T> T kthSelect(int k, T[] arr, Comparator<T> comparator,
                                  Random rand) {
        if (arr == null || comparator == null || rand == null) {
            throw new java.lang.IllegalArgumentException("The array may not be sorted "
                    + "if it is null or if the comparator is null.");
        }
        return quickSelect(arr, 0, arr.length - 1, k, comparator, rand);
    }

    /**
     * A Recursive KthSelect helper method which continues to swap the data
     * around the pivot point until all data is sorted
     * through several iterations over increasingly smaller arrays
     * @param arr the array that should be modified after the method
     *      *                   is finished executing as needed
     * @param start where to start sorting in the array
     * @param end where to stop sorting in the array
     * @param k the index to retrieve data from + 1 (due to
     *      0-indexing) if the array was sorted; the 'k' in "kth
     *      select"; e.g. if k == 1, return the smallest element
     *      in the array
     * @param comparator the Comparator used to compare the data in arr
     * @param rand the Random object used to select pivots
     * @param <T> data type to sort
     * @return the kth smallest element
     */
    private static <T> T quickSelect(T[] arr, int start, int end, int k, Comparator<T> comparator,
                                   Random rand) {
        if (k > arr.length || k < 1) {
            throw new java.lang.IllegalArgumentException("Value of k or the random is invalid.");
        }
        int pivotIndex = rand.nextInt(end - start + 1) + start;
        T pivotVal = arr[pivotIndex];
        arr[pivotIndex] = arr[start];
        arr[start] = pivotVal;
        int i = start + 1;
        int j = end;
        while (i <= j) {
            while (i <= j && comparator.compare(arr[i], pivotVal) <= 0) {
                i++;
            }
            while (i <= j && comparator.compare(arr[j], pivotVal) >= 0) {
                j--;
            }
            if (i <= j) {
                T temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
                j--;
            }
        }
        T temp = arr[start];
        arr[start] = arr[j];
        arr[j] = temp;
        if (j > (k - 1)) {
            return quickSelect(arr, start, j - 1, k, comparator, rand);
        } else if (j < k - 1) {
            return quickSelect(arr, j + 1, end, k, comparator, rand);
        } else if (j == (k - 1)) {
            return arr[j];
        }
        return arr[start];
    }
}

