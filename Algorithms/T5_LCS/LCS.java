package Algorithms.T5_LCS;

/**
 * Created by sergio on 11/06/17.
 */
public class LCS {
    char[] A;
    char[] B;

    public LCS(char[] string1, char[] string2){
        A = string1;
        B = string2;
    }

    public char[][] calculateLCS(){
        int n = A.length;
        int m = B.length;
        int[][] c = new int[n+1][m+1];
        char[][] b  = new char[n+1][m+1];

        for(int i=1; i < n; i++)
            c[i][0] = 0;

        for(int j=1; j < m; j++)
            c[0][j] = 0;

        for(int i = 0, ii = 1; i < n; ii++, i++){
            for(int j = 0,jj= 1; j < m; jj++, j++){
                if(A[i] == B[j]){
                    c[ii][jj] = c[ii-1][jj-1] + 1;
                    b[ii][jj] = '¬';
                }
                else{
                    if(c[ii-1][jj] >= c[ii][jj-1]){
                        c[ii][jj] = c[ii-1][jj];
                        b[ii][jj] = '|';
                    }else{
                        c[ii][jj] = c[ii][jj-1];
                        b[ii][jj] = '-';
                    }
                }
            }
        }
        return b;
    }

    public void printLCS(String sol, char[][] b, int i, int j) {
        //System.out.println("-" + A[j]);
        if (i == 0 || j == 0) {
            return;
        }
        if (b[i][j] == '¬') {
            int k = i;
            k--;
            printLCS(sol, b, i - 1, j - 1);
            System.out.print(A[k]);

        } else {
            if (b[i][j] == '|') {

                printLCS(sol, b, i - 1, j);
            } else {
                printLCS(sol, b, i, j - 1);
            }
        }
    }

    public char[][] editDistance(){
        int[][] d = new int[A.length+1][B.length+1];
        char[][] sol = new char[A.length+1][B.length+1];
        for(int i = 0; i <= A.length; i++){
           d[i][0] = i;
        }
        for(int j = 0; j <= B.length; j++){
            d[0][j] = j;
        }


        for(int i = 1, ii = 0; i <= A.length; ii++, i++){
            for(int j = 1, jj = 0; j <= B.length; jj++, j++){
                int del = d[i-1][j] + 1;
                int ins = d[i][j-1] + 1;
                int sub = d[i-1][j-1];
                if(A[ii] != B[jj])
                    sub += 1;

                int distance = Math.min(del, Math.min(sub, ins));
                d[i][j] = distance;

                if (d[i][j] == del)
                    sol[i][j] = '|';
                else if(d[i][j] == ins){
                    sol[i][j] = '-';
                }else
                    sol[i][j] = '¬';
            }
        }





        for(int i=0; i <= A.length; i++){
            for(int j = 0; j <= B.length; j++){
                System.out.print(d[i][j] + ":" + sol[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println(d[A.length][B.length]);
        return sol;
    }

    public void printEditDistance(char[][] sol, int i, int j) {
        //System.out.println("-" + A[j]);
        if (i == 0 || j == 0) {
            return;
        }
        int k = i;
        k--;
        int m = j;
        m--;
        if (sol[i][j] == '¬') {
            printEditDistance(sol,i - 1, j - 1);
            if(A[k] != B[m])
                System.out.print("sub(" + A[k] + "," + B[m] + ") ");
            else
                System.out.print(A[k]);
        } else {
            if (sol[i][j] == '|') {
                printEditDistance(sol,i - 1, j);
                System.out.print("del(" + A[k] + ") " );
            } else {
                printEditDistance(sol, i, j - 1);
                System.out.print("ins(" + B[m] + ") " );
            }
        }
    }

    public static void main(String[] args) {
        char[] string1 = {'A','B','C','B','D','A','B'};
        char[] string2 = {'B','D','C','A','B','A'};

        LCS problem =  new LCS(string1, string2);

        char[][] b = problem.calculateLCS();

        problem.printLCS("", b, string1.length, string2.length);

        System.out.println();

        char[] string3 = {'I','N','T','E','N','T','I', 'O', 'N'};
        char[] string4 = {'E','X','E','C','U','T', 'I','O','N'};

        LCS problem2 =  new LCS(string3, string4);

        char[][] sol = problem2.editDistance();

        problem2.printEditDistance(sol, string3.length, string4.length);


    }





}
