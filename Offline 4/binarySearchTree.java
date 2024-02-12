package mypackage;

import java.util.Scanner;

public class binarySearchTree {
    class Node{
        int key;
        Node left;
        Node right;

        public Node(int key) {
            this.key = key;
            left=right=null;
        }
    }

    Node root;

    boolean isEmpty(){
       return root==null;
    }

     public void insertItem(int value){
            root=insertNew(value,root);
            System.out.println(value + " inserted!");
    }
    Node insertNew(int value, Node root){
        if(root==null){
            root= new Node(value);
        }
        else if(value>root.key){
             root.right=insertNew(value, root.right);
        }
        else if(value<root.key){
            root.left=insertNew(value, root.left);
        }
        return root;
    }

    public void searchItem(int value){
        if(findValue(value, root)){
            System.out.println(value+ " has been found!");
        }
        else{
            System.out.println(value + " has not been found!");
        }
    }

    public boolean findValue(int value, Node node){
        if(node.key==value)
            return true;
        else if(value>node.key)
            return findValue(value, node.right);
        else if(value<node.key)
            return findValue(value, node.left);
        else return false;
    }


    public int getInOrderSuccessor(int value){
        Node current = root;
        Node parent = null;
        Node target= null;
        int successor=-100000;
        while(current!=null){
            if(value<current.key){
                parent=current;
                current=current.left;
            }
            else if(value>current.key){
                current=current.right;
            }
            else {
                target=current;
                break;
            }
        }
        if(target!=null){
            if(target.right!=null){
                successor = getMin(target.right);
            }
            else if(parent!=null){
                successor = parent.key;
            }
            else successor = -100000;
        }

        return successor;
    }

    public int getInOrderPredecessor(int value){
        Node current = root;
        Node parent = null;
        Node target= null;
        int predecessor=-100000;
        while(current!=null){
            if(value<current.key){
                current=current.left;
            }
            else if(value>current.key){
                parent=current;
                current=current.right;
            }
            else {
                target=current;
                break;
            }
        }
        if(target!=null){
            if(target.left!=null){
                predecessor = getMax(target.left);
            }
            else if(parent!=null){
                predecessor = parent.key;
            }
            else predecessor = -100000;
        }

        return predecessor;
    }

    public void printDelete(int value){
        if(deleteItem(value, root)!=null){
            System.out.println(value + " has been deleted!");
            System.out.println("in order sequence of the tree: ");
            getInorder(root);
            System.out.println(" ");
        }
        else System.out.println("cannot find the value!");
    }

    public Node deleteItem(int value, Node root){
        if(root==null) return root;
        if(value>root.key) root.right = deleteItem(value, root.right);
        else if(value<root.key) root.left=deleteItem(value, root.left);
        else{
            if(root.right==null && root.left==null) return null;
            else if(root.right==null) return root.left;
            else if(root.left==null) return root.right;
            else{
                root.key=getMax(root.left);
                root.left = deleteItem(root.key, root.left);
            }
        }
        return root;
    }

    public int getItemDepth(int value){
        int dep = 0;
        return findValueDepth(dep, value,root);
    }

    public int findValueDepth(int depth, int value, Node node){
        if(node.key==value)
            return depth;
        else if(value>node.key){
            depth+=1;
            return findValueDepth(depth, value, node.right);
        }
        else if(value<node.key){
            depth+=1;
            return findValueDepth(depth, value, node.left);
        }
        else return -1;
    }

     public int getMax(Node root){
        while(root.right!=null){
           root=root.right;
        }
        return root.key;
     }

    public int getMin(Node root){
        while(root.left!=null){
            root=root.left;
        }
        return root.key;
    }

    public int getHeight(Node root){
        if(root==null||(root.left==null&&root.right==null)) return 0;
        else{
            int leftHeight = getHeight(root.left);
            int rightHeight = getHeight(root.right);
            if(leftHeight>rightHeight)
                return (leftHeight+1);
            else return (rightHeight+1);
        }
    }

    public void getInorder(Node root){

        if(root!=null){
            getInorder(root.left);
            System.out.print(root.key + " ");
            getInorder(root.right);
        }

    }

    public void getPreOrder(Node root){

        if(root==null) return;
            System.out.print(root.key+ " ");
            getPreOrder(root.left);
            getPreOrder(root.right);


    }

    public void getPostOrder(Node root){

        if(root==null) return;
        getPostOrder(root.left);
        getPostOrder(root.right);
        System.out.print(root.key+" ");

    }

    public int getSize(Node root){
        if(root==null) return 0;
        else{
            return getSize(root.left)+getSize(root.right)+1;
        }
    }
    
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        binarySearchTree tree = new binarySearchTree();
        tree.insertItem(23);
        tree.insertItem(20);
        tree.insertItem(12);
        tree.insertItem(21);
        tree.insertItem(50);
        tree.insertItem(45);
        tree.insertItem(55);
        while(true){
            System.out.println("Enter a number to get appropriate output:\n1.Insert Item\n2.Search Item\n3.Get In Order Successor\n" +
                    "4.Get In Order Predecessor\n5.Delete Item\n6.Get Item Depth\n7.Get Max Item\n8.Get Min Item\n" +
                    "9.Get Height\n10.Print In Order\n11.Print Pre Order\n12.Print Post Order\n13.Get Size");
            int number = sc.nextInt();
            if(number==1){
                System.out.println("enter value of Item:");
                tree.insertItem(sc.nextInt());
            }
            else if(number==2){
                System.out.println("enter value of Item:");
                tree.searchItem(sc.nextInt());
            }
            else if(number==3){
                System.out.println("enter value of Item:");
                int value = sc.nextInt();
                int successor = tree.getInOrderSuccessor(value);
                if(successor!=-100000)
                    System.out.println("In order successor of " + value + " is "+successor);
                else System.out.println("there is no in order successor of "+value);
            }
            else if(number==4){
                System.out.println("enter value of Item:");
                int value = sc.nextInt();
                int predecessor = tree.getInOrderPredecessor(value);
                if(predecessor!=-100000)
                    System.out.println("In order predecessor of " + value + " is "+predecessor);
                else System.out.println("there is no in order predecessor of "+value);
            }
            else if(number==5){
                System.out.println("enter value of Item:");
                tree.printDelete(sc.nextInt());
            }
            else if(number==6){
                System.out.println("enter value of Item:");
                int value = sc.nextInt();
                int depth = tree.getItemDepth(value);
                if(depth>=0){
                    System.out.println("depth of " +  value + " is " + depth);
                }
                else{
                    System.out.println(value + " has not been found!");
                }
            }
            else if(number==7){
                int node = tree.getMax(tree.root);
                System.out.println("Item with maximum value is: " + node);
            }
            else if(number==8){
                int node = tree.getMin(tree.root);
                System.out.println("Item with minimum value is: " + node);
            }
            else if(number==9){
                System.out.println("height of the tree is " + tree.getHeight(tree.root));
            }
            else if(number==10){
                System.out.println("in order sequence of the tree: ");
                tree.getInorder(tree.root);
                System.out.println(" ");
            }
            else if(number==11){
                System.out.println("pre order sequence of the tree: ");
                tree.getPreOrder(tree.root);
                System.out.println(" ");
            }
            else if(number==12){
                System.out.println("post order sequence of the tree: ");
                tree.getPostOrder(tree.root);
                System.out.println(" ");
            }
            else if(number==13){
                System.out.println("size of the tree is " + tree.getSize(tree.root));
            }
            else{
                System.out.println("enter a number between 1 to 13!");
            }
        }
    }
}
