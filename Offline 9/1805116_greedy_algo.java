package mypackage;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static int price(int[] arr, int frans){
        int len = arr.length;
        int iteration = len/frans;
        int total=0;
        int serial = len-1;
        if(frans<=len){
            for(int i=0; i<iteration; i++){
                for(int j=0; j<frans; j++){
                    total += (i+1) * arr[serial];
                    serial--;
                }
            }
            for(int k=serial; k>=0; k--){
                total+=(iteration+1)*arr[k];
            }
        }
        else{
            for(int l=0; l<len; l++){
                total+=arr[l];
            }
        }

        return total;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] one = sc.nextLine().split(" ", 2);
        int n = Integer.parseInt(one[0]);
        int k = Integer.parseInt(one[1]);
        String[] two = sc.nextLine().split(" ", n);
        int[] prices = new int[n];
        for(int i=0; i<n; i++){
            prices[i] = Integer.parseInt(two[i]);
        }
        Arrays.sort(prices);
        System.out.println(price(prices, k));
    }
}
