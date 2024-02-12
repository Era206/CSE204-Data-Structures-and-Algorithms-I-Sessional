package mypackage;

public class linkedList {
    public static Node start;
    public static Node end;
    public static Node current;
    public static boolean direction= true;
    public static boolean flag=false;

    public static void addElement(Player player){
        Node newNode = new Node(player, null, null);
        if(start==null){
            start=end=newNode;
           start.prev = end;
           end.next=start;
           start.next = end;
            end.prev=start;
            current=start;
        }
        else{
            newNode.next=start;
            start.prev=newNode;
            newNode.prev=end;
            end.next=newNode;
            end=newNode;
        }

    }

    public static void display(){
        Node temp = start;
        if(direction==true){
            while (temp.next != start)
            {
                System.out.println(temp.data);
                temp = temp.next;
            }
            System.out.println(temp.data);
        }
        else{
            while (temp.prev != start)
            {
                System.out.println(temp.data);
                temp = temp.prev;
            }
            System.out.println(temp.data);
        }
    }


    public static void remove(Node newNode){
        Node curr=newNode;


        if(curr==start) {
            start=curr.next;
            start.prev= end;
        }
        else if(curr==end){
            end=curr.prev;
            end.next=start;
        }
        if(direction==true)curr=curr.next;
        else curr=curr.prev;

        start=curr;
        newNode.next.prev=newNode.prev;
        newNode.prev.next=newNode.next;


    }
    public static Node currentHolder(int time){
        //System.out.println(time);
        int tot=0;
        Node temp=start;
            while (tot < time) {

                if (temp.data.getRest() != 0) {
                    tot += temp.data.getRest();
                        temp.data.flagg=true;

                } else if(temp.data.getRest()==0&&temp.data.flagg==true) {
                    if(direction==true){
                        temp = temp.next;
                        tot += temp.data.getReflexTime();
                        temp = temp.next;
                    }
                    else{
                        temp = temp.prev;
                        tot += temp.data.getReflexTime();
                        temp = temp.prev;
                    }
                }
                else{
                    tot += temp.data.getReflexTime();
                    if(direction==true)temp = temp.next;
                    else temp=temp.prev;

                }

            }
            //System.out.println(temp.data.getRest());
            if (time < temp.data.getRest() && time == 0) {
                current = temp;
                tot = temp.data.getRest();
               // System.out.println(tot);
            } else if (time <= temp.data.getRest())
                current = temp;
            else {
                if(direction==true)current = temp.prev;
                else current = temp.next;
            }

        start=current;
       start.data.setRest(tot-time);

       return start;
    }



    public static void insert(Node newNode, int reflex, int serial){
        Player player = new Player(reflex, serial);
        Node temp = new Node(player, null, null);
        current=newNode;

        if(direction==true){
            temp.next=current;
            temp.prev=current.prev;
            current.prev.next=temp;
            current.prev=temp;
        }
        else{
            temp.next=current.next;
            temp.prev=current;
            current.next.prev=temp;
            current.next=temp;
        }


    }


}
