package mypackage;

public class Node {
    public Player data;
    public Node prev;
    public Node next;

    public Node(Player data, Node prev, Node next) {
        this.data = data;
        this.prev = prev;
        this.next = next;
    }
}
