package mypackage;

public class queue<T> {
    private linkedList<T> list;

    public queue() {
        this.list=new linkedList<>();
    }

    public queue(T[] data, int n){
        this.list=new linkedList<>();
        for(int i=0;i<n;i++){
            list.addEnd(data[i]);
        }
        list.setLen(n);
    }

    public void enQueue(T data){
        list.addEnd(data);
    }

    public boolean isEmpty()
    {
        return list.head== null;
    }
    public T peekQueue(){return list.getHead().data;}

    public T deQueue(){
        if(!isEmpty()) {
            return list.removeQueue();
        }
        else {
            System.out.println("queue is empty!");
            return null;
        }
    }

    public linkedList<T> getList() {
        return list;
    }

    @Override
    public String toString() {
        return list.toString();
    }
}
