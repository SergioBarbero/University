package Algorithms.FastSorting;

import Algorithms.SimpleSort.L1_SimpleSort_ints;

import java.util.Random;

/**
 * Created by sergio on 21/04/17.
 */
public class FastSorting_int {



    public static double sellSort9(Integer[] array) {
        long startTime = System.nanoTime();
        int h = 1;
        while (h <= array.length / 9)
            h = 3 * h + 1;
        while (h > 0) {
            for (int i = h; i < array.length; i++) {
                int x = array[i];
                int j = i;
                while (j >= h && x <= array[j - h]) {
                    array[j] = array[j - h];
                    j = j - h;
                }
                array[j] = x;
            }
            h = (h - 1)/ 3;
        }

        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        System.out.println("Shell sort 9: " + duration/1000 + " ms");

        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        return duration/1000;
    }


    public static double sellSort50(Integer[] array){
        long startTime = System.nanoTime();
        int h = 1;
        while(h <= array.length/50)
            h = 3*h + 1;
        while(h > 0){
            for(int i = h; i < array.length; i++){
                int x = array[i];
                int j = i;
                while(j >= h && x < array[j - h]){
                    array[j] = array[j - h];
                    j = j - h;
                }
                array[j] = x;
            }
            h = h / 3;
        }

        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        System.out.println("Shell sort: " + duration/1000 + " ms");

        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        return duration/1000;


    }

    /**
     *
     * @param array array to sort
     */
    public static void quickSort(Integer[] array, int l, int r) {
        if (l < r) {
            int s = l;
            int t = array[s];
            for (int i = l+1; i <= r; i++) {
                if (array[i] < t) {
                    s = s + 1;
                    swap(array, s, i);
                }
            }
            swap(array, l, s);
            quickSort(array, l, s - 1);
            quickSort(array, s + 1, r);
        }
    }

    public static double quickSortInsertion(Integer[] array, int l, int r) {
        long startTime = System.nanoTime();
        if (l < r) {
            if(r - l < 9) {
                L1_SimpleSort_ints.insertion(array);
            }else {
                int s = l;
                int t = array[s];
                for (int i = l + 1; i <= r; i++) {
                    if (array[i] < t) {
                        s = s + 1;
                        swap(array, s, i);
                    }
                }
                swap(array, l, s);
                quickSort(array, l, s - 1);
                quickSort(array, s + 1, r);
            }
        }
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        System.out.println("Quick sort + insertion: " + duration/1000 + " ms");
        return duration/1000;
    }



    public static double quickSortFirst(Integer[] array){
        long startTime = System.nanoTime();

        quickSort(array, 0, array.length-1);

        long endTime = System.nanoTime();
        long duration = (endTime - startTime);

        System.out.println("Quick sort first pivot: " + duration/1000 + " ms");
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        return duration/1000;
    }


    public static double quickSortRandom(Integer[] array){
        long startTime = System.nanoTime();

        int pivot = new Random().nextInt(array.length);

        swap(array, pivot, 0 );
        quickSort(array, 0, array.length-1);

        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        System.out.println("Quick sort random pivot: " + duration/1000 + " ms");

        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        return duration/1000;

    }

    public static double quickSortMedian(Integer[] array){
        long startTime = System.nanoTime();

        int median = median3(array);

        swap(array, median, 0 );
        quickSort(array, 0, array.length-1);

        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        System.out.println("Quick sort median pivot: " + duration/1000 + " ms");

        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        return duration/1000;
    }

    public static void swap(Integer[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

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

    public static int median3(Integer[] array){

        int first = array[0];
        int mid = array[array.length/2];
        int last = array[array.length-1];
        int median = 0;
        if (first > mid) {
            if (mid > last) {
                median = array.length/2;
            } else if (first > last) {
                median = array.length-1;
            } else {
                median = 0;
            }
        } else {
            if (first > last) {
                median = 0;
            } else if (mid > last) {
                median = array.length-1;
            } else {
                median = array.length/2;
            }
        }
        return median;

    }

    public static boolean isSorted(Integer[] array){
        for(int i=0, j=1; j < array.length; i++, j++){
            if(array[i] > array[j])
                return false;
        }
        return true;
    }

    public static void main(String[] args) {

        Integer[] original = generateArrayRandom(50);
        Integer[] array = new Integer[original.length];

        System.arraycopy( original, 0, array, 0, original.length );

        sellSort9(array);

        //System.out.print("\n" + isSorted(array));
        System.arraycopy( original, 0, array, 0, original.length );

       // System.out.println();

/*         for(int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }*/

        System.out.println();

        sellSort50(array);

        System.arraycopy( original, 0, array, 0, original.length );

        System.out.println();

        quickSortFirst(array);

        System.arraycopy( original, 0, array, 0, original.length );

        System.out.println();

        quickSortRandom(array);

        System.arraycopy( original, 0, array, 0, original.length );

        System.out.println();

    /*   for(int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
*/

        quickSortMedian(array);

        System.arraycopy( original, 0, array, 0, original.length );

        System.out.println();

        System.arraycopy( original, 0, array, 0, original.length );

        quickSortInsertion(array, 0 , array.length - 1);

        for(int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }



    }

}
