import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    //swap
    public static void switchMethod(ArrayList<Iris>list, int f, int s) {
        Iris temp = list.get(f);
        list.set(f,list.get(s));
        list.set(s,temp);
    }




    //Method declarations
    public static void BubbleSort(ArrayList<Iris> a, int size){
        //fix me

        while (size -1 != 0) {
            for(int i = 0; i < size - 1; i++) {
                if (a.get(i).compareTo(a.get(i+1)) < 0 ) {
                    switchMethod(a, i, (i + 1));
                }
            }
            size--;
        }
    }
    public static void mergeSort(ArrayList<Iris> a, ArrayList<Iris> tmp, int left, int right){
        //fix me



        if (left < right) {
            int middle = (left + right)/2;
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
        int l = left;


        //System.out.println(leftEnd + " " + tempPos + " " + left + middle + right + " " + numElements);

        while (left <= leftEnd && middle <= right) {
            if (a.get(left).isLessThan(a.get(middle)) ) {
                tmp.add(tempPos, a.get(left));
                tempPos++;
                left++;
            } else {
                //System.out.println(tempPos);
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
            fis = new FileInputStream("src/fish-iris2.csv.txt");
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

        // sort list using mergesort
        mergeSort(list, tmp, 0, list.size()-1);
        //sort list2 using Bubble sort
        BubbleSort(list2, list2.size());

        System.out.println("List 1 (Merge Sort):");
        for(int i = 0; i < list.size(); i++){
            System.out.println(list.get(i).toString());
        }

        System.out.println("List 2 (Bubble Sort):");
        for(Iris i: list2){
            System.out.println(i.toString());
        }

           }
}
