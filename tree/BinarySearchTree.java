package tree;

public class BinarySearchTree {
    class Node{
        int key;
        int value;
        Node left;
        Node right;

        Node(int key,int value){
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }
    private Node root;
    private int count;

    public BinarySearchTree(){
        root = null;
        count = 0;
    }

    int size(){
        return count;
    }

    boolean isEmpty(){
        return count == 0;
    }

    public void insert(int key,int newData){
        root = insert(root,key,newData);
    }

    //向以node为根的二叉搜索树中，插入节点（key,value）
    //返回插入新节点后的二叉搜索树的根
    //对其子节点进行操作，返回的是其子节点的根节点
    //对于添加的或者更新的节点返回其本身
    private Node insert(Node node,int key,int newData){
        if (node == null){
            count++;
            return new Node(key,newData);
        }

        if (node.key == key){
            node.value = newData;
        }
        else if (node.key < key){
            node.right = insert(node.right,key,newData);
        }
        else {
            node.left = insert(node.left,key,newData);
        }
        return node;
    }

    public void insertV2(){

    }

    public boolean contain(int key){
        return contain(root,key);
    }
    private boolean contain(Node node,int key){
        if (node == null)
            return false;

        if (key == node.key)
            return true;

        else if (key < node.key){
            return contain(node.left,key);
        }
        else{
            return contain(node.right,key);
        }
    }

    public int search(int key){
        return search(root,key);
    }
    private int search(Node node,int key){
        if (node == null)
            return -1;

        if (key == node.key)
            return node.value;

        if (key < node.key)
            return search(node.left,key);
        else
            return search(node.right,key);
    }

    public static void main(String[] args){
        BinarySearchTree binarySearchTree = new BinarySearchTree();
        int[] keys = new int[]{20,18,24,13,19,30};
        for (int i = 0; i < keys.length; i++) {
            binarySearchTree.insert(keys[i],i);
        }
//        System.out.println(binarySearchTree.root.key);
//        System.out.println(binarySearchTree.root.left.key);
//        System.out.println(binarySearchTree.root.right.right.key);
        System.out.println(binarySearchTree.contain(24));
        System.out.println(binarySearchTree.search(30));

    }
}
