package tree;

import java.util.Arrays;

public class UnionFind {

    private int[] id;
    private int count;

    UnionFind(int n){
        count = n;
        id = new int[n];
        for (int i = 0; i < n; i++) {
            id[i] = i;
        }
    }

    int[] getId(){
        return id;
    }

    int find(int p){
        assert p >= 0 && p <= count;
        return id[p];
    }

    boolean isConnect(int p,int q){
        return find(p) == find(q);
    }

    void unionElements(int p, int q){
        int pId = find(p);
        int qId = find(q);
        if (pId == qId)
            return;
        for (int i = 0; i < count; i++) {
            if (find(i) == qId){
                id[i] = pId;
            }
        }
    }

    public static void main(String[] args){
        UnionFind unionFind = new UnionFind(10);
        System.out.println(Arrays.toString(unionFind.getId()));
        unionFind.unionElements(1,2);
        System.out.println(Arrays.toString(unionFind.getId()));
        unionFind.unionElements(2,4);
        System.out.println(Arrays.toString(unionFind.getId()));
    }
}
