package mypackage;

public class linkedList<T> {

    public class Node<T>{
        T data;
        Node<T> next;

        public Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    public Node<T> head=null;
    public Node<T> tail=null;
    private int len=0;

    public void setLen(int len) {
        this.len = len;
    }

    public int getLen() {
        return len;
    }


    public void addNode(T data){
        Node<T> temp= new Node<T>(data);

        if(head==null){
            head=temp;
            tail=temp;

        }

        else{
            temp.next=tail;
            tail=temp;
        }
        len++;
    }

    public void addEnd(T data){
        Node<T> temp= new Node<T>(data);

        if(head==null){
            head=temp;
            tail=temp;
        }
        else{
            tail.next=temp;
            tail=tail.next;
        }
        len++;
    }

    public  T peekValue(){
        return tail.data;
    }

    public Node<T> getHead() {
        return head;
    }

    public Node<T> getTail() {
        return tail;
    }

    public T removeStack(){
        T temp= tail.data;
        tail=(tail).next;
        len--;
        return temp;
    }

    public T removeQueue(){
        T temp = head.data;
        head=head.next;
        len--;
        return temp;
    }

    @Override
    public String toString() {
        if(head == null) return "[]";

        StringBuffer printBuffer = new StringBuffer();
        Node<T> current = head;
        while(current.next!=null){
            printBuffer.append(current.data);
            current = current.next;
        }
        printBuffer.append(current.data);

        return String.valueOf(printBuffer);
    }
}
