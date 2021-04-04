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
                tempPos++;
                left++;
            } else {

                tmp.add(tempPos, a.get(middle));
                tempPos++;
                middle++;
            }

        }
        //test
        //System.out.println(leftEnd + " " + tempPos + " " + left + middle + right);

        while (left <= leftEnd) {
            tmp.add(tempPos, a.get(left));
            tempPos++;
            left++;
        }

        while (middle <= right) {
            tmp.add(tempPos, a.get(middle));
            tempPos++;
            middle++;
        }
        for (int i = l; i < right; i++) {
            a.set(i, tmp.get(i));
        }
    }


    public static void main(String [] args){

        //.....
        //....
        //file reader
        FileInputStream fis = null;
        String lines[];
        //try and catch for file not found, initializes fis
        try {
            fis = new FileInputStream("fish-iris2.csv.txt");
        } catch (FileNotFoundException e){
            System.out.println("File not found.");
            System.exit(1);
        }

        ArrayList<Iris> list=new ArrayList<Iris>();   // list to be sorted
        ArrayList<Iris> tmp=new ArrayList<Iris>();   // temporary workspace
        //fill list
        Scanner reader = new Scanner(fis);
        reader.nextLine(); //skips column headers
        while (reader.hasNextLine()) {
            lines = reader.nextLine().split(",");
            list.add(new Iris(Double.parseDouble(lines[0]), Double.parseDouble(lines[1]), Double.parseDouble(lines[2]),
                    Double.parseDouble(lines[3]), lines[4]));
        }

        //test
//        for(int i = 0; i < list.size(); i++){
//            System.out.println(list.get(i).toString());
//        }

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

        System.out.println("List 1 (Merge Sort):");
        for(int i = 0; i < list.size(); i++){
            System.out.println(list.get(i).toString());
        }

        System.out.println("List 2 (Bubble Sort):");
        for(Iris i: list2){
            System.out.println(i.toString());
        }



        //System.out.println("Run time: " + totalTime + " nanoseconds.");


        //fileoutputstream to create new file
        FileOutputStream fos = null;
        File csvFile = new File("runtime.txt");
        try {
            csvFile.createNewFile();
            PrintWriter writer = new PrintWriter(csvFile);
            writer.println("Merge sort run time: " + totalTimeMergeSort + " nanoseconds.");
            writer.println("Bubble sort run time: " + totalTimeBubbleSort + " nanoseconds.");
            writer.close();
           } catch (IOException e){
            System.exit(1);
        }
}
}
