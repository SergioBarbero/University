package Algorithms.GraphAlgorithms;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sergio on 21/06/17.
 */
public class readFromFileBFS {
    public String readFile(String path){
        String chain = "";
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;

        int root = 0;
        int destination = 11;

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
                graph[source.get(i)][target.get(i)] = 1;
            }

            GraphAlgorithms problem = new GraphAlgorithms(graph);

            Node goal = problem.BreathFirstSearch( root, destination);

            System.out.println("Breath First Search algorithm");
            //Output
            problem.retrieveTrace(goal, root );



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
        readFromFileBFS reader = new readFromFileBFS();
        String path = "src/Algorithms/GraphAlgorithms/graph1.txt";
        reader.readFile(path);

    }
}
