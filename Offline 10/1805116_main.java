import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static long ways(int n, int sum, int faces[], long totalSum[][]){
        if(sum<=0) return 0;
        if(n<0) return 0;
        if(n==0){
            if(faces[n]<sum) {
                totalSum[n][sum]=0;
                return 0;
            }
            else{
                totalSum[n][sum] =1;
                return 1;
            }
        }
        int modulo = 1000000007;

        totalSum[n][sum] = (ways(n, sum-1,faces, totalSum)%modulo + ways(n-1, sum-1, faces, totalSum)%modulo - ways(n-1, (sum-1-faces[n]), faces, totalSum)%modulo)% modulo;
        return totalSum[n][sum];

    }
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new BufferedReader(new FileReader("era.txt")));
        String[] one = sc.nextLine().split(" ", 2);
        int n = Integer.parseInt(one[0]);
        int s = Integer.parseInt(one[1]);
        String[] two = sc.nextLine().split(" ", n);
        int[] dice_faces = new int[n];
        for(int i=0; i<n; i++){
            dice_faces[i] = Integer.parseInt(two[i]);
        }
        long [][] totalSum = new long[n+1][s+1];
        for(int i=0; i<=n;i++){
            for(int j=0; j<=s;j++){
                totalSum[i][j] = -1;
            }
        }
        System.out.println(ways(n-1, s ,dice_faces, totalSum));
    }
}