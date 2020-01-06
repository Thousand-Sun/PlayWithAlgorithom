package weightedGraph;

import java.util.Iterator;
import java.util.Vector;

public class SparseGraph implements Graph {
    //邻接表表示稀疏图
    private int n,m;
    private boolean directed;
    private Vector<Vector<Edge>> g;

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
                System.out.print("{to:" + g.get(i).elementAt(j).w() +"," + g.get(i).elementAt(j).getWeight() + "} ");
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
            if (g.get(v).get(i).another(v) == w)
                return true;
        }
        return false;
    }

    public void addEdge(int v,int w,double weight){
        assert v >= 0 && v < n;
        assert w >= 0 && w < n;

        g.get(v).add(new Edge(v,w,weight));
        if (!directed && v != w)//去掉自环边
            g.get(w).add(new Edge(w,v,weight));
        m++;
    }


    public Iterable<Edge> adjIterator(int v){
        assert v >= 0 && v < n;
        return g.get(v);
    }
    public void traverseAdjacentEdge(int v){
        Iterable<Edge> adj = adjIterator(0);
        for (Iterator<Edge> it = adj.iterator(); it.hasNext(); ) {
            Edge i = it.next();
            System.out.print(i.w() + " ");
        }
    }
}
