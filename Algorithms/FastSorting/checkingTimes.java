package Algorithms.FastSorting;

import NumericalMethods.T3_Interpolation.Interpolation;

import java.io.FileWriter;
import java.io.PrintWriter;

/**
 * Created by sergio on 19/06/17.
 */
public class checkingTimes {

    /**
     * Description: exports the results to csv
     * @param x point x
     * @param a interval [-a, a]
     */
    public static void exportToCSV(double x, double a){
        FileWriter proofs = null;
        PrintWriter pw = null;

        try
        {
            proofs = new FileWriter("src/Algorithms/FastSorting/times.csv");
            pw = new PrintWriter(proofs);

            int[] sizes = {375, 1187, 3000, 6000, 12000};

            for(int i = 0; i < sizes.length; i++) {
                Integer[] original = FastSorting_int.generateArrayRandom(sizes[i]);
                Integer[] array = new Integer[original.length];


                System.arraycopy( original, 0, array, 0, original.length );


                pw.write(FastSorting_int.quickSortFirst(array)+ ",");

                System.arraycopy( original, 0, array, 0, original.length );

                pw.write(FastSorting_int.quickSortRandom(array)+ ",");

                System.arraycopy( original, 0, array, 0, original.length );

                pw.write(FastSorting_int.quickSortMedian(array)+ ",");

                System.arraycopy( original, 0, array, 0, original.length );

                pw.write(FastSorting_int.quickSortInsertion(array, 0 , array.length - 1)+ ",");

                System.arraycopy( original, 0, array, 0, original.length );

                pw.write( FastSorting_int.sellSort9(array) + ",");

                System.arraycopy( original, 0, array, 0, original.length );

                pw.write(FastSorting_int.sellSort50(array)+ "\n");



            }



        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != proofs)
                    proofs.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }




    }

    public static void main(String[] args) {
        exportToCSV(7, 10);

    }
}
