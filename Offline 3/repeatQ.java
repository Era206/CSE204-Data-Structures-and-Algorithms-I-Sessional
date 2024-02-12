package mypackage;

import java.util.Scanner;

public class repeatQ {
    public static String finalString(String old) {
        char[] ch = old.toCharArray();
        int l = ch.length;
       queue<Character> qInput = new queue<Character>();
       queue<Character> qOutput = new queue<Character>();
       int[] len = new int[26];

       for(int i=0;i<l;i++){
           int current = ch[i]-'a';
           qInput.enQueue(ch[i]);
           len[current]+=1;
           while(qInput.getList().getLen()!=0){
               if(len[qInput.peekQueue()-'a']==1){
                   qOutput.enQueue(qInput.peekQueue());
                  break;
               }
               else{
                   qInput.deQueue();
               }
           }

          if(qInput.isEmpty())
               qOutput.enQueue('#');

       }
       return qOutput.toString();
    }

    public static void main (String[]args){
        Scanner sc = new Scanner(System.in);
        System.out.println("enter old array: ");
        String s = sc.next();
        System.out.println("New array is: " + finalString(s));
    }
}
