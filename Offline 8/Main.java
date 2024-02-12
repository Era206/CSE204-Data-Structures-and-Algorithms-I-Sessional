package mypackage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Scanner;

public class Main {
    static int [] l_index = new int[2];
    static int [] second_l_index = new int[2];

    public static double distance(House c1, House c2){
        return Math.sqrt((c1.x-c2.x)*(c1.x-c2.x) + (c1.y-c2.y)*(c1.y-c2.y));
    }

    public static double[] bruteForce(House cities[], int n){
        double[] dist = new double[2];
        if(n==1) {
            dist[0] = 0;
            dist[1] = 0;
        }
        else if(n==2){
           dist[0] =  distance(cities[0], cities[1]);
            dist[1] =  distance(cities[0], cities[1]);
        }
        else{
            double min = 10000;
            double min2nd = 10000;
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++){
                    double distant = distance(cities[i], cities[j]);

                    if (distant < min2nd) {
                        if(distant< min){
                            min2nd = min;
                            min = distant;

                            second_l_index[0]= l_index[0];
                            second_l_index[1]= l_index[1];

                            l_index[0]=cities[i].index;
                            l_index[1]=cities[j].index;
                        }
                        else if(distant == min){
                            min = min;
                            min2nd = min2nd;
                        }
                        else {
                            min2nd= distant;

                            second_l_index[0]=cities[i].index;
                            second_l_index[1]=cities[j].index;
                        }

                    }
                }

            }

            dist[0]=min;
            dist[1]=min2nd;
        }

        return dist;
    }

    public static double[] minimum(double[] distances){
        int len = distances.length;
        for(int i=0;i<len; i++){
            //System.out.println(" distances  " + distances[i]);
        }
        double min = 10000;
        double min2nd = 10000;
        for(int i=0; i<len; i++){
            if(distances[i]< min2nd){
                if(distances[i]< min){
                    min2nd = min;
                    min = distances[i];
                }
                else if(distances[i] == min){
                    min = min;
                    min2nd = min2nd;
                }
                else min2nd= distances[i];
            }
        }
        double[] dist = new double[2];

        dist[0]=min;
        dist[1]=min2nd;
        return dist;
    }

    public static double[] strip2ndClosest(House strip[], int size, double d, double d1){
        double min = d;
        double min2nd = d1;
        for (int i = 0; i < size; ++i)
            for (int j = i+1; j < size && (strip[j].y - strip[i].y) < min2nd; ++j){
                double dist =distance(strip[i],strip[j]);
                if (dist < min2nd){
                    if(dist< min){
                        min2nd = min;
                        min = dist;

                        second_l_index[0]= l_index[0];
                        second_l_index[1]= l_index[1];

                        l_index[0] = strip[i].index;
                        l_index[1] = strip[j].index;
                    }
                    else if(dist == min){
                        min = min;
                        min2nd = min2nd;
                    }
                    else {
                        min2nd= dist;

                        second_l_index[0] = strip[i].index;
                        second_l_index[1] =strip[j].index;
                    }
                }
            }

        //return min;
        double[] dis = new double[2];
        dis[0]=min;
        dis[1]=min2nd;
        return dis;
    }

    static double[] closest2ndDistances(House Hx[], House Hy[], int n, int midvalue){
        if (n <= 3)
            return bruteForce(Hx, n);

        int mid =  n/2;
        House midHouse = Hx[mid+midvalue];
        midvalue = mid;
        int same=0;
        for(int i=midvalue; i<(mid+midvalue); i++){
            if(Hx[i].x == midHouse.x);
            same++;
        }

        House[] Hyl= new House[mid];
        House[] Hyr= new House[n-mid];
        int li=0, ri=0;
        for(int i=0; i<n; i++){
            if((Hy[i].x <= midHouse.x) && (li<mid)){
                if((Hy[i].x== midHouse.x) && (same>0)){
                    Hyl[li]=Hy[i];
                    li++;
                    same--;
                }
                else if(Hy[i].x< midHouse.x){
                    Hyl[li]=Hy[i];
                    li++;
                }
            }
            else{
                Hyr[ri]=Hy[i];
                ri++;
            }
        }


        double[] l_distances = new double[2];
        l_distances = closest2ndDistances(Hx, Hyl, mid, 0);
        double dl= l_distances[0], d2l = l_distances[1];

        double[] r_distances = new double[2];
        r_distances = closest2ndDistances(Hx , Hyl, n-mid, midvalue);
        double dr= r_distances[0], d2r = r_distances[1];

        double[] distances = new double[4];
        distances[0] = dl;
        distances[1] = dr;
        distances[2] = d2l;
        distances[3] = d2r;

        double[] mins = new double[2];
        mins = minimum(distances);
        double d = mins[0], d1 = mins[1];


        House[] strip = new House[n];
        int j = 0;
        for (int i = 0; i < n; i++)
            if (Math.abs(Hy[i].x - midHouse.x) < d1){
                strip[j] = Hy[i]; j++;
            }

        return strip2ndClosest(strip, j, d, d1);
    }

    static double secondClosest(House H[], int n){

        House[] Hx = new House[n];
        for(int i=0; i<n; i++){
            Hx[i]= new House(0,0, i);
        }
        mergeSort_x xMarr = new mergeSort_x(n, H);

        Hx = xMarr.mArray;

        House[] Hy = new House[n];
        for(int i=0; i<n; i++){
            Hy[i]= new House(0,0, i);
        }

        mergeSort_y yMarr = new mergeSort_y(n, H);

        Hy = yMarr.mArray;

        double[] dist = new double[2];
        dist = closest2ndDistances(Hx, Hy, n, 0);

        if(second_l_index[0]> second_l_index[1])
            System.out.println( second_l_index[1] + " "+ second_l_index[0]);

        if(second_l_index[0]< second_l_index[1])
            System.out.println( second_l_index[0] + " "+ second_l_index[1]);

        //System.out.println("closest index: "+ lowestindex[0] + " "+ lowestindex[1]);

        return dist[1];
    }

    public static void main(String[] args) throws FileNotFoundException {

        Scanner sc = new Scanner(new BufferedReader(new FileReader("era.txt")));
        int n= sc.nextInt();
        House C[]=new House[n];

        sc.nextLine();

        int x, y;

        //System.out.println(n);
        while(sc.hasNext()) {
            for (int i = 0; i < n; i++) {
                String[] mystr = new String[2];
                mystr = sc.nextLine().split(" ", 2);

                x = Integer.parseInt(mystr[0]);
                y = Integer.parseInt(mystr[1]);

                C[i] = new House(x, y, i);

            }

        }

        DecimalFormat df = new DecimalFormat("#.0000");
        df.setRoundingMode(RoundingMode.CEILING);

        System.out.println(df.format(secondClosest(C, n)));
    }
}
