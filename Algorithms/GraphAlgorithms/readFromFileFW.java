package Algorithms.GraphAlgorithms;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sergio on 21/06/17.
 */
public class readFromFileFW {
    public String readFile(String path){
        String chain = "";
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;

        try {

            archivo = new File (path);
            fr = new FileReader (archivo);
            br = new BufferedReader(fr);

            String line;
            line=br.readLine();
            String[] header = line.split(" ");
            List<Integer> source = new ArrayList<>();
            List<Integer> target = new ArrayList<>();
            List<Integer> weight = new ArrayList<>();
            String[] body;
            while((line=br.readLine())!=null){
                body = line.split("\\s+");
                source.add(Integer.parseInt(body[0]));
                target.add(Integer.parseInt(body[1]));
                weight.add(Integer.parseInt(body[2]));
            }
            int[][] graph = new int[Integer.parseInt(header[0])][Integer.parseInt(header[0])];
            for(int i=0; i < graph.length; i++) {
                for (int j=0; j< graph.length; j++){
                    if(i != j)
                        graph[i][j] = 1000;
                    else
                        graph[i][j] = 0;
                }
            }
            for(int i = 0; i < source.size(); i++){
                graph[source.get(i)-1][target.get(i)-1] = weight.get(i);
            }

            GraphAlgorithms problem = new GraphAlgorithms(graph);

            int[][] paths = problem.floydWarshall();

            //Output
            for(int i=0, ii= 1; i < paths.length; ii++, i++){
                for(int j=0, jj=1; j < paths.length;jj++, j++) {
                    if (i != j) {
                        System.out.print("d[" + ii  + "," + jj + "] = " + paths[i][j] + "  ");
                        List<Integer> bestPath = problem.path(i, j);
                        System.out.print("PATH: ");
                        for(Integer x: bestPath){
                            System.out.print(x+1 + " ");
                        }
                        System.out.println();

                    }
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if( null != fr ){
                    fr.close();
                }
            }catch (Exception e2){
                e2.printStackTrace();
            }
        }
        return chain;
    }


    public static void main(String[] args) {
        readFromFileFW reader = new readFromFileFW();
        String path = "src/Algorithms/GraphAlgorithms/floyd1.txt";
        reader.readFile(path);

    }
}
