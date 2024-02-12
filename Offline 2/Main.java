package mypackage;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner snc = new Scanner(System.in);
        System.out.println("for main menu press l, for exit press x:");
        while (snc.next().equalsIgnoreCase("l")) {
            System.out.println("give proper input to get required output!");
            Scanner s = new Scanner(System.in);
            linkedList.start = null;
            int num = s.nextInt();
            int[] arr = new int[num];
            int serial = 0;
            for (int i = 0; i < num; i++) {
                arr[i] = s.nextInt();
                Player player = new Player(arr[i], i + 1);
                serial += 1;
                linkedList.addElement(player);
                linkedList.current = linkedList.current.next;
            }
            int time = 0;
            int tot = 0;
            int flag = 0;
            boolean direction = true;
            boolean arrow = true;
            Scanner newScan = new Scanner(System.in);
            while (arrow) {
                String st = new String();
                st = newScan.nextLine();
                String[] str = st.split(" ");
                int add_time = Integer.parseInt(str[0]);
                if (tot == 0) {
                    time += add_time;
                    flag = time;
                } else {
                    time = add_time - flag;
                    flag += time;
                }
                if (str[1].equals("P")) {
                    Node newNode = linkedList.currentHolder(time);
                    System.out.println("player " + newNode.data.getSerial() + " is holding the pillow at t=" + flag);

                }
                else if (str[1].equals("M")) {
                    Node newNode = linkedList.currentHolder(time);
                    System.out.println("Player " + newNode.data.getSerial() + " has been eliminated at t =" + flag);
                    linkedList.remove(newNode);

                }
                else if (str[1].equals("R")) {
                    Node newNode = linkedList.currentHolder(time);
                    if (linkedList.direction == true) linkedList.direction = false;
                    else linkedList.direction = true;
                    linkedList.start = newNode;
                    System.out.println("direction of the linkedList has been reversed!");

                }
                else if (str[1].equals("I")) {
                    int reflex = Integer.parseInt(str[2]);
                    Node newNode = linkedList.currentHolder(time);
                    serial += 1;
                    System.out.println("Player " + serial + " has been added!");
                    linkedList.insert(newNode, reflex, serial);

                }
                else if (str[1].equals("F")) {
                    arrow = false;
                    System.out.println("Game over : player " + linkedList.currentHolder(time).data.getSerial() + " is holding the pillow at t=" + flag);
                    linkedList.display();
                    break;
                }
                if ((!str[1].equals("P") && (linkedList.start.next == linkedList.start))) {
                    System.out.println("Game over: player " + linkedList.start.data.getSerial() + " wins!");
                    arrow=false;
                    break;
                }

                tot += 1;
            }
            linkedList.start=linkedList.end=linkedList.current=null;
            //System.out.println("for main menu press l, for exit press x:");
        }
        if(snc.next().equalsIgnoreCase("x"))
            return;
    }
}
