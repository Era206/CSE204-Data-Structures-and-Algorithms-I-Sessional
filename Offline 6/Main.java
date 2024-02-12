package mypackage;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {
     static int tot=0;//global value using for dfs algorithm

    //creating a vertex class
     static class Vertex {
        private LinkedList<Integer> adj[];
        private int values[];

        public Vertex(int num) {
            adj = new LinkedList[num];
            values = new int[num];
            for (int i = 0; i < num; ++i){
                adj[i] = new LinkedList();
                values[i] = 0;
            }
        }

        public LinkedList<Integer>[] getAdj() {
            return adj;
        }

        public int getValue(int index) {
            return values[index];
        }

        public void setAdj(LinkedList<Integer>[] adj) {
            this.adj = adj;
        }

        public void setValue(int index, int value) {
            values[index]=value;
        }
    }

    static class Graph {
        private int V;
        static boolean[] isVisitedBFS;
        static boolean[] isVisitedDFS;
        private Vertex vertex;

        //Creating a graph
        Graph(int v) {
            V = v;
            vertex = new Vertex(v);
            isVisitedBFS = new boolean[v];
            isVisitedDFS = new boolean[v];
        }

        // Adding edges to the graph
        void addRoad(int c1, int c2) {
            vertex.getAdj()[c1].add(c2);
            vertex.getAdj()[c2].add(c1);
        }

        void setValue(int c, int p) {
            vertex.setValue(c, p);
        }

        // BFS algorithm
        int BFS(int s) {
            int tot = 0;
            if(!isVisitedBFS[s]) {
                LinkedList<Integer> queue = new LinkedList();
                isVisitedBFS[s] = true;
                tot+= vertex.getValue(s);
                queue.add(s);

                while (queue.size() != 0) {
                    s = queue.poll();

                    Iterator<Integer> i = vertex.getAdj()[s].listIterator();
                    while (i.hasNext()) {
                        int n = i.next();
                        if (!isVisitedBFS[n]) {
                            isVisitedBFS[n] = true;
                            queue.add(n);
                            tot += vertex.getValue(n);
                        }
                    }
                }
            }
            return tot;
        }

        // DFS algorithm
       void DFS(int s){
            if(!isVisitedDFS[s]) {
                isVisitedDFS[s] = true;
                tot += vertex.getValue(s);

                    Iterator<Integer> i = vertex.getAdj()[s].listIterator();
                    while (i.hasNext()) {
                        int n = i.next();
                        if (!isVisitedDFS[n]) {
                            DFS(n);
                        }
                    }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner s = new Scanner(System.in);
        System.out.println("enter the numbers of cities, roads, locations and friends serially: ");
        int[] arrInfo = new int[4];
        String[] str = s.nextLine().split(" ");
        for(int i=0; i<4; i++){
            arrInfo[i] = Integer.parseInt(str[i]);
        }

        Graph g = new Graph(arrInfo[0]);

        System.out.println("enter city no's that connect the roads for " + arrInfo[1] + " roads respectively: ");
        for(int i=0;i<arrInfo[1];i++){
            String[] str2 = s.nextLine().split(" ");
            int[] arr = new int[2];
            for(int j=0; j<2; j++){
                arr[j] = Integer.parseInt(str2[j]);
            }
            g.addRoad(arr[0], arr[1]);
        }

        System.out.println("enter city no and the number of pieces hidden in that city for " + arrInfo[2] + " locations respectively: ");
        int pieces =0;
        for(int i=0;i<arrInfo[2];i++){
            String[] str3 = s.nextLine().split(" ");
            int[] arr2 = new int[2];
            for(int j=0; j<2; j++){
                arr2[j] = Integer.parseInt(str3[j]);
            }
            pieces+=arr2[1];
            g.setValue(arr2[0], arr2[1]);
        }

        System.out.println("enter city no. and serial no. for " + arrInfo[3] + " to complete the mission: ");
        int[] start= new int[arrInfo[3]];
        int[] index=new int[arrInfo[3]];
        for(int i=0;i<arrInfo[3];i++){
            String[] str4 = s.nextLine().split(" ");
            int serial = Integer.parseInt(str4[1]);
            index[serial]=serial;
            start[serial] = Integer.parseInt(str4[0]);
        }

        System.out.println("in which way do you want to complete the mission? For BFS press 1, for DFS press 2:");
        int num = s.nextInt();
        int[] values = new int[arrInfo[3]];
        int tot_pieces=0;

        if(num==1){
            for(int i=0;i<arrInfo[3];i++){
                int l = index[i];
                values[l]=g.BFS(start[i]);
                tot_pieces+=values[l];
            }
        }
        else if(num==2){
            for(int i=0;i<arrInfo[3];i++){
                int l = index[i];
                tot=0;
                g.DFS(start[i]);
                values[l]=tot;
                tot_pieces+=values[l];
            }
        }

        String output = "Output.txt";
        BufferedWriter bw = new BufferedWriter(new FileWriter(output));

        if(tot_pieces==pieces){
            System.out.println("Mission Accomplished");
            bw.write("Mission Accomplished");
            bw.write("\n");
        }
        else{
            System.out.println("Mission Impossible");
            bw.write("Mission Impossible");
            bw.write("\n");
        }

        System.out.println(tot_pieces + " out of " + pieces + " pieces are collected");
        bw.write(tot_pieces + " out of " + pieces + " are collected");
        bw.write("\n");

        for(int i=0;i<arrInfo[3];i++){
            System.out.println(i + " collected " + values[i] + " pieces");
            bw.write(i + " collected " + values[i] + " pieces");
            bw.write("\n");
        }
        bw.close();

    }
}
