package tree;

public class QuickUnion {

    private int[] parent;
    private int count;

    QuickUnion(int n){
        count = n;
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }
    
    public void unionElements(){

    }
}
