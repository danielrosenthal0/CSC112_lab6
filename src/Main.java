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
        int middle = (left + right)/2;

        //while (length != 0) {
        if (left < right) {
            mergeSort(a, tmp, left, middle);
            mergeSort(a, tmp, middle + 1, right);
            mergeSortedLists(a,tmp,left,middle + 1,right);

        }
    }
    public static void mergeSortedLists(ArrayList<Iris> a, ArrayList<Iris> tmp, int left, int middle, int right){
        //fix me
        int leftEnd = middle - 1;
        int tempPos = left;
        int numElements = right - left + 1;

        while (left <= leftEnd && right <= middle) {
            if (a.get(left).compareTo(a.get(right)) < 0 ) {
                tmp.add(tempPos, a.get(left));
                tempPos++;
                left++;
            } else {
                tmp.add(tempPos, a.get(right));
                tempPos++;
                right++;
            }

        }

        while (left <= leftEnd) {
            tmp.add(tempPos, a.get(left));
            tempPos++;
            left++;
        }
        while (right <= middle) {
            tmp.add(tempPos, a.get(right));
            tempPos++;
            right++;
        }

        for (int i = 0; i < numElements; i++, --middle) {
            a[middle] = tmp[middle];
        }
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
