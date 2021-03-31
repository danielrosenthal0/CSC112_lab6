import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    //Method declarations
    public static void BubbleSort(ArrayList<Iris> a, int size){
        //fix me
    }
    public static void mergeSort(ArrayList<Iris> a, ArrayList<Iris> tmp, int left, int right){
        //fix me
    }
    public static void mergeSortedLists(ArrayList<Iris> a, ArrayList<Iris> tmp, int left, int middle, int right){
        //fix me
    }

    public static void main(String [] args){
        //.....
        //....
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
        while (reader.hasNextLine()) {
            lines = reader.nextLine().split(",");
            list.add(new Iris(Double.parseDouble(lines[0]), Double.parseDouble(lines[1]), Double.parseDouble(lines[2]),
                    Double.parseDouble(lines[3]), lines[4]));
        }
        reader.close();
        //testing reader
        int size = list.size();
        for (int i = 0; i < size; i++) {
            System.out.println(list.get(i));
        }
        //....
        //...
        //Create a copy from list for Bubble sort
        ArrayList <Iris> list2=new ArrayList<Iris>();
        for(int i=0;i<list.size();i++)
            list2.add(list.get(i));

        // sort list using mergesort
        mergeSort(list, tmp, 0, list.size());
        //sort list2 using Bubble sort
        BubbleSort(list2, list2.size());

           }
}
