package NumericalMethods.T5_NumericalIntegration;

import NumericalMethods.T3_Interpolation.Interpolation;

import java.lang.Math;

/**
 * Authors : Matheusz Korusiewicz, Sergio Barbero
 *
 * Rectangular, trapezoid, Simpson and Chebishev methods for aproximation of functions from a given points, it works for every case
 * Cases:
 *  - Only 2 points (a, b)
 *  - Set of n points [a, ......, b]
 *
 *  Note: Important to note that for Simpson rule number of points must be uneven, consequently the number of intervals must be an even number
 */
public class NumericalIntegration {
	/*
	 * Function f'(x) = e^(-x)*sin(x)(2cos(x)-sin(x)) f(x) = sin²(x)*e^(-x)
	 */

    private double a, b;
    private double realArea;

    public NumericalIntegration(double a, double b) {
        this.a = a;
        this.b = b;
        double Fa = Math.pow(Math.sin(a), 2) * Math.pow(Math.E, -a);
        double Fb = Math.pow(Math.sin(b), 2) * Math.pow(Math.E, -b);
        this.realArea = Math.abs(Fb - Fa);
    }

    public double Rect(double[] points) {
        double[] f = new double[points.length];
        double aproxArea = 0;
        for (int i = 0, k = -1; i < points.length; i++, k++) {
            f[i] = Math.pow(Math.E, -points[i]) * Math.sin(points[i]) * (2 * Math.cos(points[i]) - Math.sin(points[i]));
            if (i != 0){
                    aproxArea += f[k] * (points[i] - points[k]);
            }
        }
        double error = Math.abs(realArea - aproxArea);

        System.out.println("Rectangular aproximate area: " + aproxArea);
        System.out.println("Error: " + error);
        System.out.println();

        return error;
    }

    public double Trap(double[] points) {
        double[] f = new double[points.length];
        double aproxArea = 0;
        for (int i = 0, k = -1; i < points.length; i++, k++) {
            f[i] = Math.pow(Math.E, -points[i]) * Math.sin(points[i]) * (2 * Math.cos(points[i]) - Math.sin(points[i]));
            if (i != 0) {
                if(f[k] > 0)
                    aproxArea += (points[i] - points[k]) * ((f[k] + Math.abs(f[i])) / 2);
                else
                    aproxArea += -1* (points[i] - points[k]) * ((Math.abs(f[k]) + Math.abs(f[i])) / 2);
            }
        }
        double error = Math.abs(realArea - aproxArea);

        System.out.println("Trapezoid aproximate area: " + aproxArea);
        System.out.println("Error: " + error);
        System.out.println();
        return error;

    }

    public double Simpson(double[] points){
        int n = points.length - 1;
        double h = points[1] - points[0];
        double f[] = new double[points.length];
        double aproxArea = 0; double error = 0;
        if(n % 2 == 0){
            double brackets = 0;
            for(int i=0; i <= n; i++){
                f[i] = Math.pow(Math.E, -points[i]) * Math.sin(points[i]) * (2 * Math.cos(points[i]) - Math.sin(points[i]));
                if(i % 3 == 0){
                    brackets += f[i];
                }else if(i % 3 == 1){
                    brackets += 4*f[i];
                }else if(i % 3 == 2){
                    brackets += 2*f[i];
                }
            }
            aproxArea = (h*brackets)/3;
            error = Math.abs(aproxArea - this.realArea);
            System.out.println("Simpson's aproximate area: " + aproxArea);
            System.out.println("Error: " + error);
            System.out.println();
            return error;
        }else{
            System.out.println("For Sympson's rule number of intervals must be even!");
            System.out.println();
            return 0;
        }
    }


    public double Chebyshev(double[] points){
        int n = points.length;
        double f[] = new double[points.length];
        double x[] = new double[points.length];
        double t = 0;
        double sum = 0;
        double aproxArea = 0; double error = 0;
        for(int index=0, i=1; index < points.length; index++, i++){
            if(n == 2 ){
                if(i == 1)
                    t = -0.577350;
                else
                    t = 0.577350;
            }else if(n == 3){
                if(i == 1)
                    t = -0.707107;
                else if(n == 3)
                    t = 0.707107;
                else
                    t = 0;
            }else if(n == 4){
                if(i == 1)
                    t = -0.794654;
                else if(i == 4)
                    t = 0.794654;
                else if(i == 2)
                    t = -0.832498;
                else
                    t = 0.832498;
            }else if(n == 5){
                if(i == 1)
                    t = -0.832498;
                else if(i == 5)
                    t = 0.832498;
                else if(i == 2)
                    t = -0.374541;
                else if(i == 4)
                    t = 0.374541;
                else
                    t = 0;
            }else if(n == 6){
                if(i == 1)
                    t = -0.866247;
                else if(i == 6)
                    t = 0.866247;
                else if(i == 2)
                    t = -0.422519;
                else if(i == 5)
                    t = 0.422519;
                else if(i == 3)
                    t = -0.266635;
                else
                    t = 0.266635;
            }else if(n == 7){
                if(i == 1)
                    t = -0.883862;
                else if(i == 7)
                    t = 0.883863;
                else if(i == 2)
                    t = -0.529657;
                else if(i == 6)
                    t = 0.529657;
                else if(i == 3)
                    t = -0.323912;
                else if(i == 5)
                    t = 0.323912;
                else
                    t = 0;
            }
            x[index] = 1*(a+b)/2+1*(a-b)*t/2;
            //System.out.println(" a: " + a + " b: "+ b + " t: "+t + " x" + index + " = " + x[index]);
            f[index] = Math.pow(Math.E, -x[index]) * Math.sin(x[index]) * (2 * Math.cos(x[index]) - Math.sin(x[index]));
            sum += f[index];
        }
        aproxArea = (b - a)*sum / (double) n;
        error = Math.abs(aproxArea - this.realArea);
        System.out.println("Chebyshev's aproximate area: " + aproxArea);
        System.out.println("Error: " + error);
        System.out.println();
        return error;

    }


    public static void main(String[] args) {
        NumericalIntegration function = new NumericalIntegration(-3, -1); //Initialize the problem given a and b

        System.out.println("Exact area: " + function.realArea); //Returns the exact area

        Interpolation problem = new Interpolation(2);

        double[] eqNodes = problem.equallySpaced(31, -3, -1);

        double[] points = { -3, -2, -1 }; //Here it's posible to change the points whatever you want

        //double[] points = { -3, -1 };

        function.Rect(eqNodes); //Rectangular function

        function.Trap(eqNodes); //Trapezoid function

        function.Simpson(eqNodes); //Simpson formula

        function.Chebyshev(eqNodes); //Chebyshev method




    }

}
