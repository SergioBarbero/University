package Algorithms.HashTables;

import java.util.*;

/**
 * Created by sergio on 21/04/17.
 */
public class DoubleHashing<K, V> {
    List<K> keys;
    List<V> values;
    V error;
    int size;
    public static int averageHits = 0;

    public List<K> getKeys(){
        return keys;
    }


    public DoubleHashing(int size){
        keys =  new ArrayList<>(size);
        values = new ArrayList<>(size);
        this.size = size;
        for(int i = 0; i < size; i++){
            keys.add(null);
            values.add(null);
        }
    }

    public int hashFunction(K key){
        return Math.abs(key.hashCode() % size);
    }

    public int hashFunction2(K key){
        return Math.abs(2*key.hashCode() % (size - 1) + 1);
    }

    public V search(K key){
        int index = this.hashFunction(key);
        //  System.out.println("Hash code of " + key + ": " + index);
        int tries = 1;
        int i = 0;
        while(keys.get(index) != key){
            tries++; i++;
            index +=  hashFunction2(key);
            index = index % size;
            if(tries == size || keys.get(index) == null) {
                averageHits += tries;
                return null;
            }
            index = (index + 1) % size;
        }
        //System.out.println("i: " + index + " = " + keys.get(index) + ", tries: " + tries);
        averageHits += tries;
        return values.get(index);
    }

    public void insertion(K key, V value){
        int tries = 0;
        int index = this.hashFunction(key);
        while(keys.get(index) != null){
            index = (index + 1) % size;
            tries++;
        }
        System.out.println("Number of tries: " + tries + ", K: " + key + " V: " + value);
        keys.set(index, key);
        values.set(index, value);
    }

    public void restartHits(){
        averageHits = 0;
    }

    public static String generateRandomString(int length) {
        String array = ""; // String generated
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        char[] text = new char[length];
        Random rn = new Random();
        for (int j = 0; j < length; j++) {
            text[j] = characters.charAt(rn.nextInt(characters.length()));
            array += text[j];
        }

        return array;

    }

    public static void initStrings(int size, int capacity){

       DoubleHashing<String, String> strings = new DoubleHashing<>(size);

        System.out.println("Filling the hash table with Strings");
        for(int i = 0; i < capacity; i++){
            Random generator = new Random();
            strings.insertion(generateRandomString(7),generateRandomString(6));
        }

        Random generator = new Random();
        int initial = generator.nextInt(size);
        List<String> subarray = new LinkedList<>();
        int k = 0; int m =  0;
        while(k < size/10){
            String e = strings.getKeys().get(m);
            if(e != null ){
                subarray.add(e);
                k++;
            }
            m++;
        }
        Collections.shuffle(subarray);


        System.out.println();
        System.out.println("Searching contained elements");
        for(int i = 0; i < subarray.size(); i++) {
            String element = subarray.get(i);
            strings.search(element);
        }
        double searchHits = averageHits / (double) subarray.size();
        System.out.println("Average number of search hits: " + searchHits);

        subarray.clear();
        int j = 0;
        while(j < capacity/10){
            String randomKey = generateRandomString(7);
            if(!strings.getKeys().contains(randomKey)) {
                subarray.add(randomKey);
                j++;
            }

        }

        System.out.println();
        System.out.println("Searching not contained elements");
        strings.restartHits();
        for(int i = 0; i < capacity/10; i++) {
            String element = subarray.get(i);
            strings.search(element);
        }
        double searchMiss = averageHits / (double) subarray.size();
        System.out.println("Average number of search miss: " + searchMiss);

    }


    public static void initIntegers(int size, int capacity){


        DoubleHashing<Integer, Integer> integers = new DoubleHashing<>(size);

        System.out.println("Filling the hash table");
        for(int i = 0; i < capacity; i++){
            Random generator = new Random();
            integers.insertion(generator.nextInt(5000), generator.nextInt(50));
        }

        Random generator = new Random();
        int initial = generator.nextInt(capacity);
        List<Integer> subarray = new LinkedList<>();
        int k = 0; int m =  0;
        while(k < capacity/10){
            Integer e = integers.getKeys().get(m);
            if(e != null ){
                subarray.add(e);
                k++;
            }
            m++;
        }
        Collections.shuffle(subarray);


        System.out.println();
        System.out.println("Searching contained elements");
        for(int i = 0; i < subarray.size(); i++) {
            Integer element = subarray.get(i);
            integers.search(element);
        }
        double searchHits = averageHits / (double) subarray.size();
        System.out.println("Average number of search hits: " + searchHits);

        subarray.clear();
        int j = 0;
        while(j < capacity/10){
            Integer randomKey = generator.nextInt(500000);
            if(!integers.getKeys().contains(randomKey)) {
                subarray.add(randomKey);
                j++;
            }

        }

        System.out.println();
        System.out.println("Searching not contained elements");
        integers.restartHits();
        for(int i = 0; i < capacity/10; i++) {
            Integer element = subarray.get(i);
            integers.search(element);
        }
        double searchMiss = averageHits / (double) subarray.size();
        System.out.println("Average number of search miss: " + searchMiss);

    }




    public static void main(String[] args) {

        int size = 100000;

        int capacity = (int) (size*0.9); //50% load

        //initIntegers(size, capacity);

        initStrings(size, capacity);
    }

}
