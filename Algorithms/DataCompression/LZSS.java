package Algorithms.DataCompression;

import NumericalMethods.T3_Interpolation.Interpolation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by sergio on 2/06/17.
 */
public class LZSS {
    String dict;

    public LZSS(String dictionary){
        dict = dictionary;
    }

    public String[] compress(String word){
        String chain = dict + word;
        List<String> compressed = new ArrayList<>();
        for(int i = dict.length(); i < chain.length(); i++){
            int next = 0;
            int times = 1;
            int offset = 1;
            boolean match = false;
            int startPoint = 0;
            /*    if(i > 9) //Starting point to check matches, if it's commented the startPoint is the beggining of the String
                startPoint = i - 9; */

            for(int k = startPoint; k < i; k++){
                if(chain.charAt(k) == chain.charAt(i)) {
                    next = i;
                    int coinc = 1;
                    for (int m = k + 1; m < chain.length() && next < chain.length() - 1 && chain.charAt(m) == chain.charAt(next + 1) && coinc < 4; m++, next++) {
                        coinc++;
                    }
                    if (coinc >= times) {
                        times = coinc;
                        offset = i - k;
                    }
                    if(coinc >= 3){ //Minimum encoded length, now 3
                        match = true;
                    }
                }
            }
            if(match == true){
                String litWord = "0 " + offset + " " + times;
                compressed.add(litWord);
                i += times - 1;
            }else{
                String litWord = "1 " + (int) chain.charAt(i);
                compressed.add(litWord);
            }

        }

        for(String x : compressed){
            System.out.println(x + " ");
        }

        String[] arrayComp = new String[compressed.size()];
        arrayComp = compressed.toArray(arrayComp);
        return arrayComp;
    }



    public String decompress(String[] compressed){
        String chain = dict;
        for(int i=0; i < compressed.length; i++){
            String[] parts = compressed[i].split(" ");
                if(parts[0].equals("0")) { //We have a match
                    int offset = Integer.parseInt(parts[1]);
                    int times = Integer.parseInt(parts[2]);
                    int pointer = chain.length() - offset;
                    for(int k = pointer; k < pointer + times; k++){
                        int a = chain.length();
                        chain += chain.charAt(k);
                    }
                }else{
                    char x = (char) Integer.parseInt(parts[1]);
                    chain += x;
                }

        }
        System.out.println(chain.substring(6));
        return chain;
    }


    public static void main(String[] args) {
        String dictionary = "aaaaaa";
        LZSS comp = new LZSS(dictionary);

        String text = "abracadabradabraca";

     /*   String[] compressed = new String[12];
        compressed[0] = "011";
        compressed[1] = "1b";
        compressed[2] = "1r";
        compressed[3] = "031";
        compressed[4] = "1c";
        compressed[5] = "021";
        compressed[6] = "1d";
        compressed[7] = "074";
        compressed[8] = "054";
        compressed[9] = "031";
        compressed[10] = "1c";
        compressed[11] = "021"; */

     //   String word = comp.decompress(compressed);

        String[] compressed = comp.compress(text);

        comp.decompress(compressed);

    }
}
