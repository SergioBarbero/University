package Algorithms.SimpleSort;

import java.util.Random;

/**
 *
 * @author Sergio Barbero Bascones
 */
public class L1_SimpleSort_ints {

    /**
     * Selection sort
     *
     * @param array
     *            to sort
     * @return array sorted
     */
    public static Integer[] selection(Integer[] array) {
        long startTime = System.nanoTime();

        for (int i = 0; i < array.length; i++) {
            int k = i;
            Integer x = array[i];
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < x) {
                    k = j;
                    x = array[j];
                }
            }
            array[k] = array[i];
            array[i] = x;
        }
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);

        System.out.println("Selection sort: " + duration/1000 + " ms");
//		printArray(array);
        return array;
    }

    /**
     * Insertion sort
     *
     * @param array
     *            to sort
     * @return array sorted
     */
    public static Integer[] insertion(Integer[] array) {
        long startTime = System.nanoTime();
        for (int i = 1; i < array.length; i++) {
            int x = array[i];
            int j = i - 1;
            while (j >= 0 && x < array[j]) {
                array[j + 1] = array[j];
                j = j - 1;
            }
            array[j + 1] = x;
        }
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);

//        System.out.println("Insertion sort: " + duration/1000 + " ms");
//		printArray(array);
        return array;
    }

    /**
     * Insertion sort
     *
     * @param array
     *            to sort
     * @return array sorted
     */
    public static Integer[] insertionImproved(Integer[] array) {
        Integer[] array2 = new Integer[array.length+1];
        array2[0] = 0;
        for(int i= 1; i < array2.length; i++){
            array2[i] = array[i-1];
        }

        long startTime = System.nanoTime();
        for (int i = 1; i < array2.length; i++) {
            int x = array2[i];
            array2[0] = x;
            int j = i - 1;
            while (x < array2[j]) {
                array2[j + 1] = array2[j];
                j = j - 1;
            }
            array2[j + 1] = x;

        }
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);

        for(int i= 1; i < array2.length; i++){
            array[i-1] = array2[i];
        }

        System.out.println("Insertion sort imp: " + duration/1000 + " ms");
//		printArray(array);
        return array;
    }

    /**
     * Bubble sort original
     *
     * @param array
     *            to sort
     * @return array sorted
     */
    public static Integer[] bubbleSortBasic(Integer[] array) {
        long startTime = System.nanoTime();
        for (int i = 0; i < array.length; i++) {
            for (int j = 1; j < array.length; j++) {
                if (array[j - 1] > array[j]) {
                    swap(array, j - 1, j);
                }
            }
        }
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);

        System.out.println("Bubble sort: " + duration/1000 + " ms");
//		printArray(array);
        return array;
    }

    /**
     * Bubble sort improved. If in one row nothing was changed then the next
     * iteration is useless, so increment i
     *
     * @param array
     *            to sort
     * @return array sorted
     */
    public static Integer[] bubbleSortImproved1(Integer[] array) {
        long startTime = System.nanoTime();
        for (int i = 0; i < array.length; i++) {
            boolean swapped = false;
            for (int j = 1; j < array.length; j++) {
                if (array[j - 1] > array[j]) {
                    swap(array, j - 1, j);
                    swapped = true;
                }
            }
            if (!swapped)
                i++;
        }
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);

        System.out.println("Bubble sort imp1: " + duration/1000 + " ms");
//		printArray(array);
        return array;
    }

    /**
     * Bubble sort with less checkings, one check less per row
     *
     * @param array
     *            to sort
     * @return array sorted
     */
    public static Integer[] bubbleSortImproved2(Integer[] array) {
        long startTime = System.nanoTime();
        for (int i = 0; i < array.length; i++) {
            boolean swapped = false;
            for (int j = 1; j < array.length - i; j++) {
                if (array[j - 1] > array[j]) {
                    swap(array, j - 1, j);
                    swapped = true;
                }
            }
            if (!swapped)
                i++;
        }
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);

        System.out.println("Bubble sort imp2: " + duration/1000 + " ms");
//		printArray(array);
        return array;
    }

    /**
     * It swaps a position i and j of the array a
     *
     * @param a
     *            array
     * @param i
     *            position
     * @param j
     *            position
     */
    public static void swap(Integer[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    /**
     * It generates a random array of a size
     *
     * @param size
     *            of the array
     * @return array generated
     */
    public static Integer[] generateArrayRandom(int size) {
        Integer[] array = new Integer[size];
        System.out.println("Original Array: ");
        for (int i = 0; i < size; i++) {
            Random rn = new Random();
            int answer = rn.nextInt(10) + 1;
            array[i] = answer;
            System.out.print(array[i] + " ");

        }
        System.out.println();
        System.out.println();

        return array;

    }

    /**
     * Print a array
     *
     * @param array
     *            to print
     */
    public static void printArray(Integer[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");

        }
        System.out.println();
    }

    public static void main(String[] args) {

        Integer[] original = generateArrayRandom(10);
        Integer[] array = original;

   //     selection(array);
   /*     array = original;

        bubbleSortBasic(array);
        array = original;

        insertionImproved(array);
        array = original;
*/
        insertion(array);
        array = original;
/*
        bubbleSortImproved1(array);
        array = original;

        bubbleSortImproved2(array);
        array = original;
        */
    }

}