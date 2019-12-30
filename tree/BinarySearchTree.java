package tree;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

public class BinarySearchTree {
    //节点内部类
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
        Node(Node node){
            this.key = node.key;
            this.value = node.value;
            this.left = node.left;
            this.right = node.right;
        }
    }
    private Node root;
    private int count;

    public BinarySearchTree(){
        root = null;
        count = 0;
    }

    //二分搜索树大小
    int size(){
        return count;
    }

    //判断二分搜索树是否为空
    boolean isEmpty(){
        return count == 0;
    }

    //插入节点
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

    //插入节点（非递归）
    public void insertV2(){

    }

    //判断树中是否包含某个元素
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

    //搜索某个键对应的值
    public Node search(int key){
        return search(root,key);
    }
    private Node search(Node node,int key){
        if (node == null)
            return null;

        if (key == node.key)
            return node;

        if (key < node.key)
            return search(node.left,key);
        else
            return search(node.right,key);
    }

    //前序遍历
    public void preOrder(){
        preOrder(root);
    }
    private void preOrder(Node node){
        if (node != null){
            System.out.print(node.key + " ");
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    //中序遍历
    public void midOrder(){
        midOrder(root);
    }
    private void midOrder(Node node){
        if (node != null){
            midOrder(node.left);
            System.out.print(node.key + " ");
            midOrder(node.right);
        }
    }

    //后序遍历
    public void aftOrder(){
        aftOrder(root);
    }
    private void aftOrder(Node node){
        if (node != null){
            aftOrder(node.left);
            aftOrder(node.right);
            System.out.print(node.key + " ");
        }
    }

    //层序遍历
    public void levelOrder(){
        Queue<Node> queue = new LinkedList<>();
        //Queue是LinkedList类的一个接口
        queue.offer(root);
        while(queue.peek() != null){
            if (queue.peek().left != null) //不能推入空元素进队列，否则后面循环到这里会停止
                queue.offer(queue.peek().left);
            if (queue.peek().right != null)
                queue.offer(queue.peek().right);

            System.out.print(queue.poll().key + " ");
        }
    }

    //寻找最小值
    public Node minimum(){
        assert count >= 0;
        return minimum(root);
    }
    private Node minimum(Node node){
        if (node.left == null)
            return node;

        return minimum(node.left);
    }

    public void deleteMin(){
        if (root != null)
        deleteMin(root);
    }
    private void deleteMin(Node node){
        if (node.left == null){
            node = node.right;
            if(node != null)
                node = null;
            count--;
        }
        else
            deleteMin(node.left);
    }

    public void removeMin(){
        if (root != null)
            root = removeMin(root);
    }
    //删除以node为根的最小节点
    //返回删除节点后新树的根
    //如果不返回根节点对根节点重定向的话，更改的只是函数中的临时变量的指向
    private Node removeMin(Node node){
        if (node.left == null){
            Node rightNode = node.right;
            node.right = null;
            count--;
            return rightNode;
        }

        node.left = removeMin(node.left);
        return node;
    }

    public void remove(int key){
        root = remove(root,key);//递归初始条件
    }
    //删除以node为根的节点
    //返回删除节点后树的根节点
    private Node remove(Node node,int key){
        if (node == null)
            //节点为空返回空值
            return null;

        if (key > node.key){
            node.right = remove(node.right,key);
            return node;//对node的孩子树操作后返回这个节点
        }
        else if (key < node.key){
            node.left = remove(node.left,key);
            return node;//对node的孩子树操作后返回这个节点
        }
        else{     //key == node.key
            if (node.left == null){
                //左孩子为空时，将其替换为右孩子为根的树
                Node rightNode = node.right;
                node.right = null;
                count--;
                return rightNode;
            }
            if (node.right == null){
                //右孩子为空时，将其替换为左孩子为根的树
                Node leftNode = node.left;
                node.left = null;
                count--;
                return leftNode;
            }
            //左右孩子都不为空时，将其替换为右子树中key值最小的节点
            Node successor = new Node(minimum(node.right));//removeMin中会删除最小节点的右孩子，所以这里选择拷贝
            count++;
            successor.right = removeMin(node.right);
            successor.left = node.left;
            node = null;
            count --;
            return successor;
        }
    }

    //测试函数
    public static void main(String[] args){
        BinarySearchTree binarySearchTree = new BinarySearchTree();
        int[] keys = new int[]{20,18,24,13,19,30,22};
        for (int i = 0; i < keys.length; i++) {
            binarySearchTree.insert(keys[i],i);
        }
        binarySearchTree.remove(24);
        binarySearchTree.preOrder();
    }
}
