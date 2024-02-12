package mypackage;

public class stack<T> {
    private  linkedList<T> list;


    public stack() {
        this.list= new linkedList<>();
    }

    public stack(T[] data, int n){
        this.list= new linkedList<>();
        for(int i=0;i<n;i++){
            list.addNode(data[i]);
        }
        list.setLen(n);
    }

    public T peekStack(){return list.getTail().data;}

    public void push(T data){
        list.addNode(data);
    }

    public boolean isEmpty()
    {
        return list.tail== null;
    }

    public T pop(){
        if(!isEmpty()) {
           return list.removeStack();
        }
        else {
            System.out.println("stack is empty!");
            return null;
        }
    }

    public linkedList<T> getList() {
        return list;
    }
}
