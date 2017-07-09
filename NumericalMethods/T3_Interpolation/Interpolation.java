package NumericalMethods.T3_Interpolation;


public class Interpolation {

    int n;
    double x;
    double fx;
    double a, b;
    int function;
    protected double Wx;
    protected String W;

    /**
     * Authors: Sergio Barbero, Matheusz Korusiewicz
     * @param function 0 -> Polynomial function, 1 -> rational function, 2 -> Modulus function, 3 -> trigonometric function;
     */
    public Interpolation(int function){
        this.function = function;

    }

    /**
     *
     * @param a 0 -> Polynomial function, 1 -> rational function, 2 -> Modulus function, 3 -> trigonometric function;
     * @param x value of x
     * @return f(x) of the chosen function
     */
    public double chooseFunction(int a, double x){
        switch (a){
            case 0:
                fx = Math.pow(x, 2) + 2*x - 1;
                break;
            case 1:
                fx = 5 / (Math.pow(x, 2) + 2);
                break;
            case 2:
                fx = Math.abs(x);
                break;
            case 3:
                fx = Math.sin(x);
                break;
        }
        return fx;
    }


    /**
     * Description: It calculates nodes equally spaced between a and b for a number of nodes n
     * @return array of nodes x
     */
    public double[] equallySpaced(int nPoints, double a, double b) {
        n = nPoints;
        this.a = a;
        this.b = b;

        double[] x = new double[n];
        double interval = b - a;
        double h = interval / (n-1);
        double pos = a;
        for (int i = 0; i < n; i++) {

            x[i] = pos;
            pos += h;
        }
        return x;
    }

    /**
     * Description: It calculates chebyshev nodes between a and b for a number of nodes n
     * @return array of nodes x
     */
    public double[] chebyshevNodes(int nPoints, double a, double b){
        n = nPoints;
        this.a = a;
        this.b = b;

        double[] x = new double[n];
        for(int i = 0, k=1; i < n;k++, i++){
            x[i] = (a - b)/2.0+(b - a)*(Math.cos((2*k - 1)*Math.PI/(double)(2*n)))/2.0;
        }
        return x;
    }

    /**
     * Description: It calculates the lagrange formula Wi(x) with i = n-1 and the value for Wi(x) for a given x
     * @param xi array of points x
     * @param x value to apply the general formula Wi(x)
     * @return approximated value for the lagrange formula in x
     */
    public double lagrange(double[] xi, double x) {
        this.x = x;
		this.fx = chooseFunction(function, x);
        String W = "";
        String[] Wi = new String[xi.length];
        double[] fx = new double[xi.length];
        double[] Wix = new double[xi.length];
        double Wx = 0;

        //Calculates the f(x) values
        int k = 0;
        for (double xik : xi) {
            fx[k] = chooseFunction(function, xik);
            k++;
            //System.out.println(xik);
        }

        //Lagrange function W(x) and W(x) for our given x
        for(int i = 0; i < n; i++){
            Wix[i] = 1;
            for(int j = 0; j < n; j++){
                if(j != i){
                    Wix[i] *= (x - xi[j]) / (xi[i] - xi[j]);
                    if(Wi[i] == null)
                        Wi[i] = "(x - " + xi[j] + ") / (" + xi[i] + " - " + xi[j] + ")";
                    else
                        Wi[i] += " * (x - " + xi[j] + ") / (" + xi[i] + " - " + xi[j] + ")";
                }
            }
            Wx += Wix[i]*fx[i];
            if(i != n - 1)
                W += Wi[i] + "* " + fx[i] + " + ";
            else
                W += Wi[i] + "* " + fx[i];

        }
        //
        this.Wx = Wx;
        this.W = W;
        return Wx;

    }



    /**
     * Desctiption: It calculates the difference between the analytical value and our approximated value in absolute terms
     * @param Wx approximated value
     * @return error
     */
    public double error(double Wx){
        System.out.println();
        double error = Math.abs(fx - Wx);
        System.out.println("f(" + x + ") = " + fx);
        System.out.println("Error: " + "|" + fx + " - " + Wx + "| = " + error);
        return error;
    }

    /**
     * Main function
     * @param args nothing
     */
    public static void main(String[] args) {
        Interpolation problem = new Interpolation(2);

        double[] eqNodes = problem.equallySpaced(4, -3, 10);
        double[] chebNodes = problem.chebyshevNodes(4, -3, 10);

        for(int i = 0; i < chebNodes.length; i++){
            System.out.print(eqNodes[i] + " ");
        }
        System.out.println();

        double solution = problem.lagrange(eqNodes, 0);

        System.out.println("W(x) = " + problem.W);

        System.out.println("W" + "(" + problem.x + ") = " + problem.Wx);

        problem.error(solution);


    }
}