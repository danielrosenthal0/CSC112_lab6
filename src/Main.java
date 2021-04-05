import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    //swap method from prof alqahtani's notes
    public static void switchMethod(ArrayList<Iris>list, int f, int s) {
        Iris temp = list.get(f);
        list.set(f,list.get(s));
        list.set(s,temp);
    }




    //Method declarations
    public static void BubbleSort(ArrayList<Iris> a, int size){
        //bubble sort
        while (size -1 != 0) { //while loop checks size of arraylist to make sure the size is not 1, then it can split
            for(int i = 0; i < size - 1; i++) {
                if (a.get(i).compareTo(a.get(i+1)) < 0 ) { //compares number at i to number at i + 1
                    switchMethod(a, i, (i + 1)); //then uses switchmethod to swap each other
                }
            }
            size--; //updates the size
        }
    }
    public static void mergeSort(ArrayList<Iris> a, ArrayList<Iris> tmp, int left, int right){

        if (left < right) { //making sure numbers are not equal
            int middle = (left + right) / 2; //splits numbers in half to find midpoint
            mergeSort(a, tmp, left, middle); //recursively runs mergesort for left side
            mergeSort(a, tmp, middle + 1, right); //recursively runs mergesort for right side
            mergeSortedLists(a,tmp,left,middle + 1,right); //sends to mergeSortedLists to merge the arraylists

        }
    }
    public static void mergeSortedLists(ArrayList<Iris> a, ArrayList<Iris> tmp, int left, int middle, int right){

        int leftEnd = middle - 1; //creates left end from the middle
        int tempPos = left; //index position
        int numElements = right - left + 1;
        int l = left; //keeps track of original left


        //System.out.println(leftEnd + " " + tempPos + " " + left + middle + right + " " + numElements);

        while (left <= leftEnd && middle <= right) {
            if (a.get(left).isLessThan(a.get(middle)) ) { //compares first element of first array to first of second array
                tmp.add(tempPos, a.get(left)); //adds to merged array
                tempPos++; //updates temp position and left to iterate thru array
                left++;
            } else {
                //adds number from second array since first element of first array was larger
                tmp.add(tempPos, a.get(middle));
                tempPos++;
                middle++;
            }

        }
        //adds number at left to temp array
        while (left <= leftEnd) {
            tmp.add(tempPos, a.get(left));
            tempPos++;
            left++;
        }
        //adds number from right array to temp array
        while (middle <= right) {
            tmp.add(tempPos, a.get(middle));
            tempPos++;
            middle++;
        }
        //adds numbers from temp array back to original array
        for (int i = l; i < right; i++) {
            a.set(i, tmp.get(i));
        }
    }

    //made loop method, took most of stuff out from main
    //did this so I could loop thru and add multiple runtimes to one text file
    public static String loop(int listSize) {
        //file reader
        FileInputStream fis = null;
        String lines[];
        //try and catch for file not found, initializes fis
        try {
            fis = new FileInputStream("fish-iris.csv.txt");
        } catch (FileNotFoundException e){
            System.out.println("File not found.");
            System.exit(1);
        }

        ArrayList<Iris> list=new ArrayList<Iris>();   // list to be sorted
        ArrayList<Iris> tmp=new ArrayList<Iris>();   // temporary workspace
        //fill list
        Scanner reader = new Scanner(fis);
        reader.nextLine(); //skips column headers
        //reader iterates thru certain number of times, splits by comma and parses
        for (int i = 0; i < listSize; i++) {
            lines = reader.nextLine().split(",");
            list.add(new Iris(Double.parseDouble(lines[0]), Double.parseDouble(lines[1]), Double.parseDouble(lines[2]),
                    Double.parseDouble(lines[3]), lines[4]));
        }

        reader.close();
        //....
        //...
        //Create a copy from list for Bubble sort
        ArrayList <Iris> list2=new ArrayList<Iris>();
        for(int i=0;i<list.size();i++)
            list2.add(list.get(i));
        //runtime
        long startTimeMergeSort = System.nanoTime();
        // sort list using mergesort
        mergeSort(list, tmp, 0, list.size()-1);

        long endTimeMergeSort   = System.nanoTime();
        long totalTimeMergeSort = endTimeMergeSort - startTimeMergeSort;

        long startTimeBubbleSort = System.nanoTime();
        //sort list2 using Bubble sort
        BubbleSort(list2, list2.size());
        long endTimeBubbleSort   = System.nanoTime();
        long totalTimeBubbleSort = endTimeBubbleSort - startTimeBubbleSort;

        //code for printing everything in list, not used because there is too much data
//        System.out.println("List 1 (Merge Sort) for " + listSize + " data points:");
//        for(int i = 0; i < list.size(); i++){
//            System.out.println(list.get(i).toString());
//        }
//
//        System.out.println("List 2 (Bubble Sort) for " + listSize + " data points:");
//        for(Iris i: list2){
//            System.out.println(i.toString());
//        }
        //returns line to be written on new file
        return (listSize + "," + totalTimeMergeSort + "," + totalTimeBubbleSort);
    }
    public static void main(String [] args){
        //.....
        //....


        //fileoutputstream to write new file
        FileOutputStream fos = null;
        File csvFile = new File("runtime.txt");
        try {
            csvFile.createNewFile();
            PrintWriter writer = new PrintWriter(csvFile);
            writer.println(loop(1000));
            writer.println(loop(5000));
            writer.println(loop(10000));
            writer.println(loop(20000));
            writer.println(loop(50000));
            writer.println(loop(75000));
            writer.println(loop(100000));

            writer.close();
           } catch (IOException e){
            System.exit(1);
        }
} }

