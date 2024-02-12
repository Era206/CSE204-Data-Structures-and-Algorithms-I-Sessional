package mypackage;


import java.util.Arrays;

class Array {
    private String[] array;
    private int len=0;
    private int capacity=0;

    public Array(){
        this(20);
    }

    public Array(int capacity){
        if (capacity < 0) throw new IllegalArgumentException("Illegal Capacity: " + capacity);
        this.capacity = capacity;
        array = new String[capacity];
    }

    public Array(String[] s){
        len = s.length;
        capacity = s.length;
        array= new String[capacity];
        for(int i=0;i<s.length;i++){
            array[i]=s[i];
        }
    }

    @Override
    public String toString() {
        return  Arrays.toString(array) ;
    }

    public String[] getArray() {
        return this.array;
    }

    public String getAnElement(int i){
        return array[i];
    }

    public void add(String element){
        if(len+1>=capacity){
            if(capacity==0) capacity=1;
            else capacity+=6;
            String[] newArray = new String[capacity];
            for(int i=0; i< len; i++){
                newArray[i]=array[i];
            }
            array = newArray;
        }
        array[len++]=element;
    }

    public void add(int i, String element) {
        if(i>len+1) throw new IndexOutOfBoundsException();
        if (len + 1 >= capacity) {
            if (capacity == 0) capacity = 1;
            else capacity += 6;
        }
        String[] newArray = new String[capacity];
        for (int j = 0; j < len + 1; j++) {
            if(j<i) newArray[j]=array[j];
            else if (j==i) newArray[j]=element;
            else newArray[j]=array[j-1];
        }
        len++;
        array = newArray;
    }

    public void remove(String element){
        if(element==null) throw new NullPointerException();
        int tot=0;
        for(int i=0;i<len;i++) {
            if (element.equals(array[i])) {
                tot++;
            }
        }
            String[] newArray = new String[len-tot];
            for(int k=0, j=0;k<len;k++, j++){
                if(array[k].equals(element)) j--;
                else newArray[j]= array[k];
            }
            array=newArray;
            len=newArray.length;
    }

    public int[] findIndex(String element){
        if(element==null) throw new NullPointerException();
        int tot=0;
        for(int i=0;i<len;i++){
            if(element.equals(array[i]))
                tot++;
        }
        int[] arr = new int[tot];
        for(int i=0, j=0; i<len;i++, j++){
            if(array[i].equals(element)) arr[j]=i;
            else j--;
        }
        return arr;
    }

    public String[] subArray(int start, int end){
        if (end >= len || start < 0) throw new IndexOutOfBoundsException();
        int newLen = end-start+1;
        String[] newArr = new String[newLen];
        for(int i=start, j=0;i<=end;i++, j++){
            newArr[j]=array[i];
        }
        return newArr;
    }

    public void merge(String[] A1, String[] A2){
        int lenA1=A1.length, lenA2=A2.length;
        int lenFinal=lenA1+lenA2;
        String[] mergeArr=new String[lenFinal];
        int i=0, j=0, k=0;
        while(i<lenA1 && j<lenA2){
            if(A1[i].compareTo(A2[j])<0)
                mergeArr[k++]=A1[i++];
            else mergeArr[k++]=A2[j++];
        }
        while(i<lenA1) mergeArr[k++]=A1[i++];
        while(j<lenA2) mergeArr[k++]=A2[j++];

        array=mergeArr;
        len=lenFinal;
        capacity=lenFinal;
    }

    public int length(){
        return len;
    }

    public boolean isEmpty() {
        return length() == 0;
    }

}

public class Main {

    public static void main(String[] args) {
            Array A1 = new Array();
            Array A2 = new Array(10);
            String[] S1={"TOFA","ERA", "IME","ERA","NAIM", "RIYO", "NOSHIN","ABA", "SWARNA","RIYO", "RAZIN", "IME","ERA"};
            Array A3 = new Array(S1);
        System.out.println(A1);
        System.out.println(A2);
        System.out.println(A1.isEmpty());
        System.out.println(A3);
        System.out.println(A3.length());
        System.out.println(A3.getAnElement(3));
        A3.add("NAIM");
        System.out.println(A3);
        System.out.println(A3.length());
        A3.add(5,"AYESHA");
        System.out.println(A3);
        System.out.println(A3.length());
        A3.remove("NAIM");
        System.out.println(A3);
        System.out.println(A3.length());
        int[] index= A3.findIndex("ERA");
        System.out.println(Arrays.toString(index));
        System.out.println(Arrays.toString(A3.subArray(7, 10)));
        String[] S2 = {"Black", "White", "Green", "Red"};
        A2 = new Array(S2);
        System.out.println(A2);
        System.out.println(A2.length());
        String[] STR1 = {"A", "D", "E", "L"};
        String[] STR2 = {"B", "C", "E", "I"};
        A1.merge(STR1, STR2);
        System.out.println(A1);
        System.out.println(A1.length());
    }
}
