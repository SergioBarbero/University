package Algorithms.DataCompression;

import java.io.*;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.util.Arrays;
import java.util.LongSummaryStatistics;

/**
 * Created by sergio on 3/06/17.
 */
public class compressFile {
    String dictionary;

    public compressFile(String dict){
        dictionary = dict;
    }

    public void deleteFile(String path){
        File file = new File (path);
        try {
            Files.delete(file.toPath());
        } catch (NoSuchFileException x) {
            System.err.format("%s: no such" + " file or directory%n", path);
        } catch (DirectoryNotEmptyException x) {
            System.err.format("%s not empty%n", path);
        } catch (IOException x) {
            // File permission problems are caught here.
            System.err.println(x);
        }
    }

    public String readFile(String path){
        String chain = "";
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;

        try {

            archivo = new File (path);
            fr = new FileReader (archivo);
            br = new BufferedReader(fr);

            String linea;
            while((linea=br.readLine())!=null)
                chain += linea + "\n" ;
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

    public void writeFile(String chain, String path){
        File file = null;
        FileWriter fw = null;
        PrintWriter pw = null;

        try
        {
            fw = new FileWriter(path, true);
            pw = new PrintWriter(fw);

            pw.print(chain + "\n");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != fw)
                    fw.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

    }


    public void compressFile(String fileToCompress){
        LZSS comp = new LZSS(dictionary);

        String[] compressed = comp.compress(readFile(fileToCompress));
        compressed = Arrays.copyOf(compressed, compressed.length-1);

        for(String word : compressed){
            writeFile(word, "src/Algorithms/DataCompression/compressed.txt");
        }


    }

    public void decompressFile(String fileToDecompress){
        LZSS comp = new LZSS(dictionary);


        String compressedText = readFile(fileToDecompress);

        String[] splitedText = compressedText.split("[\\r\\n]+");

        for(int i=0; i < splitedText.length; i++){
            if(splitedText[i].length() == 1)
                System.out.print(i + " " + splitedText[i] + " ");
        }

        String decompressed = comp.decompress(splitedText);

        writeFile(decompressed.substring(dictionary.length()), "src/Algorithms/DataCompression/decompressed.txt");

    }


    public static void main(String [] arg) {
        String dictionary = "abcdefghijklmnopqrstuvwxyz";

        String file = "src/Algorithms/DataCompression/file.txt";
        compressFile fileCompresser = new compressFile(dictionary);

        fileCompresser.deleteFile("src/Algorithms/DataCompression/compressed.txt");
        fileCompresser.deleteFile("src/Algorithms/DataCompression/decompressed.txt");


        fileCompresser.compressFile(file);


        String fileCompressed = "src/Algorithms/DataCompression/compressed.txt";
        fileCompresser.decompressFile(fileCompressed);
    }

}
