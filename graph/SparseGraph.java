package graph;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;
import java.util.Vector;

public class SparseGraph implements Graph{
    //邻接表表示稀疏图
    private int n,m;
    private boolean directed;
    private Vector<Vector<Integer>> g;

    public SparseGraph(int n , boolean directed){
        assert n >= 0;
        this.n = n;
        this.m = 0;
        g = new Vector<>(n);
        for (int i = 0; i < n; i++) {
            g.addElement(new Vector<>());
        }
    }


    public void show(){
        for( int i = 0 ; i < n ; i ++ ){
            System.out.print("vertex " + i + ":\t");
            for( int j = 0 ; j < g.get(i).size() ; j ++ )
                System.out.print(g.get(i).elementAt(j) + "\t");
            System.out.println();
        }
    }

    public int V(){
        return n;
    }
    public int E(){
        return m;
    }

    public boolean hasEdge(int v,int w){
        assert v >= 0 && v < n;
        assert w >= 0 && w < n;
        for (int i = 0; i < g.get(v).size(); i++) {//复杂度为O(n)
            if (g.get(v).get(i) == w)
                return true;
        }
        return false;
    }

    public void addEdge(int v,int w){
        assert v >= 0 && v < n;
        assert w >= 0 && w < n;

        g.get(v).add(w);
        if (!directed && v != w)//去掉自环边
            g.get(w).add(v);
        m++;
    }

    //制作遍历临近点用的迭代器
    static class AdjIterator{
        private SparseGraph G;
        private int v;
        private int index;
        public AdjIterator(SparseGraph graph, int v){
            G = graph;
            this.v = v;
            this.index = 0;
        }

        int begin(){
            index = 0;
            if (G.g.get(v).size() >= 0)
                return G.g.get(v).get(index);
            return -1;
        }

        int next(){
            index++;
            if (index < G.g.get(v).size())
                return G.g.get(v).get(index);
            return -1;
        }

        boolean end(){
            return index >= G.g.get(v).size();
        }
    }

    public Iterable<Integer> adjIterator(int v){
        assert v >= 0 && v < n;
        return g.get(v);
    }
    public void traverseAdjacentEdge(int v){
        Iterable adj = adjIterator(0);
        for (Object a:adj
        ) {
            System.out.print(a + " ");
        }
    }

    public static void main(String[] args){
        SparseGraph sparseGraph = new SparseGraph(4,false);
        sparseGraph.addEdge(0,1);
        sparseGraph.addEdge(0,2);
        sparseGraph.addEdge(0,3);
        sparseGraph.addEdge(1,2);
//        System.out.println(sparseGraph.getG());
        sparseGraph.show();
        System.out.println(sparseGraph.hasEdge(2,1));
        SparseGraph.AdjIterator adjIterator = new SparseGraph.AdjIterator(sparseGraph,1);
        for (int i = adjIterator.begin(); !adjIterator.end(); i = adjIterator.next()) {
            System.out.print(i + " ");
        }
        System.out.println();
        sparseGraph.traverseAdjacentEdge(0);
    }
}
