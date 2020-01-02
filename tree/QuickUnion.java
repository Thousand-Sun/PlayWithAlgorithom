package tree;

import java.util.Arrays;

public class QuickUnion {

    private int[] parent;
//    private int[] sz;//以i为根的树的元素个数
    private int[] rank;//以i为根的树的高度
    private int count;

    QuickUnion(int n){
        count = n;
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }
    QuickUnion(int[] arr,int[] rank){
        count = arr.length;
        parent = arr;
        this.rank = rank;
    }

    void getElements(){
        System.out.println(Arrays.toString(parent));
        System.out.println(Arrays.toString(rank));
    }

    public void unionElements(int p,int q){
        int root_p = findRoot(p);
        int root_q = findRoot(q);
        if (root_p == root_q)
            return;
        if (rank[root_p] > rank[root_q]){
            parent[root_q] = root_p;
//            rank[root_p] = Math.max(rank[root_p],rank[root_q]+1);
        }
        else if (rank[root_p] < rank[root_q]){
            parent[root_p] = root_q;
//            rank[root_q] = Math.max(rank[root_q],rank[root_p]+1);
        }
        else{
            parent[root_q] = root_p;
            rank[root_p] += 1;
        }

    }

    public int findRoot(int p){
        assert p >= 0 && p < count;
//        int i = parent[p];
//        while (parent[i] != i){
//            parent[i] = parent[parent[i]];
//            i = parent[i];
//        }
//        return i;
        if (parent[p] != p)
            parent[p] = findRoot(parent[p]);//依次将根节点返回给其所有子节点
//        rank[p] = 2;
        return parent[p];
    }

    public boolean isConnect(int p,int q){
        return findRoot(p) == findRoot(q);
    }


    public static void main(String[] args){
        QuickUnion quickUnion = new QuickUnion(new int[]{2,1,2,3,3,2,3,3},new int[]{0,1,2,2,0,0,0,0});
        System.out.println(quickUnion.findRoot(0));
        quickUnion.unionElements(5,6);
        System.out.println(quickUnion.findRoot(6));
        quickUnion.getElements();
    }
}
