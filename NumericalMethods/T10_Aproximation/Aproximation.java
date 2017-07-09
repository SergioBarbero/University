package NumericalMethods.T10_Aproximation;

/**
 * Created by sergio on 4/05/17.
 */
public class Aproximation {

    public static double Chebyshev(int n, double[] a){
        double[] x = calculateIntervals(n, -1, 1);
        double T = 0;
        for(int i = 0; i < n; i++){
            T += a[i] * Math.pow(x[i], i);
        }
        return T;
    }

//    public static double aproximateFunction(int n){}

    public static void uniform(int k, int n, double[] a){
        double[] x = calculateIntervals(n, -1, 1);
        double max = 0;
        double current = 0;
        //q = c0 + c1*x

        double T = 0;
        for(int i = 0; i < n; i++){
            T +=  a[i]*x[i];
        }

        for(int i = 0; i < n; i++){
            System.out.print(x[i] + " ");

       //     current = Math.abs(Math.pow(x[i], k)- );
            if(max < current){
                max = current;
            }
        }

        System.out.println();
        System.out.print("Error: " + max);


    }

    public static double[] calculateIntervals(int n, double a, double b) {
        double[] x = new double[n + 1];
        double interval = b - a;
        double h = interval / n;
        double pos = a;
        x[0] = a;
        for (int i = 1; i < x.length; i++) {
            pos += h;
            x[i] = pos;
        }
        x[x.length-1] = b;
        return x;
    }

  //  public static void main(String[] args) {
       // uniform(5, 7, 0, 2);
    }

//    }
