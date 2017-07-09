package NumericalMethods.SystemsEcuations;

import java.util.ArrayList;

/**
 * Created by sergio on 27/04/17.
 */
public class T9_NonLinearSystemsEcuations {

    public static double regulaMethod(double a, double b, double acc){
        //Function: x² - 25
        double fa = a*a - 25;
        double fb = b*b - 25;
        ArrayList<Double> x = new ArrayList<>();
        ArrayList<Double> fx = new ArrayList<>();
        double current = (a - (fa * (b - a) / (fb - fa)));
        x.add(current);
        double fcurrent = current*current - 25;
        fx.add(fcurrent);

        while(Math.abs(fcurrent) > acc){
            current = (current - (fcurrent * (b - current) / (fb - fcurrent)));
            fcurrent = current*current - 25;
            x.add(current);
            fx.add(fcurrent);
        }

        System.out.println(fcurrent);
        System.out.println();

        for(Double xi : x){
            System.out.println(xi);
        }

        return current;
    }

   // public static void accuracy

    public static void main(String[] args) {
        regulaMethod(-50,-1, 0.001);
    }

}
