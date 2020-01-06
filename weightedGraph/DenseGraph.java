package weightedGraph;

import java.util.Vector;

public class DenseGraph implements Graph {
    //邻接矩阵实现稠密图
    private int n,m;
    private boolean directed;
    private Edge[][] g;

    public DenseGraph(int n,boolean directed){
        assert n >= 0;
        this.n = n;
        this.m = 0;
        this.directed = directed;
        g = new Edge[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                g[i][j] = null;
            }
        }
    }

    public int V(){
        return n;
    }
    public int E(){
        return m;
    }

    public void show() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (g[i][j] != null)
                    System.out.print(g[i][j].getWeight() + "\t");
                else
                    System.out.print("-\t");
            }
            System.out.println();
        }
    }

    public boolean hasEdge(int v , int w){
        assert v >= 0 && v < n;
        assert w >= 0 && w < n;
        return g[v][w] != null;
    }

    public void addEdge(int v,int w,double weight){
        assert v >= 0 && v < n;
        assert w >= 0 && w < n;

        //消除平行边,带权图不能直接返回，要除去原来的边，后面重新赋予权值
        if (hasEdge(v,w)){
            g[v][w] = null;
            if (!directed)
                g[w][v] = null;
            m--;
        }
        g[v][w] = new Edge(v,w,weight);
        if (!directed)
            g[w][v] = new Edge(w,v,weight);
        m++;
    }

    public Iterable<Edge> adjIterator(int v){
        assert v >= 0 && v < n;
        Vector<Edge> adjV = new Vector<>();
        for (int i = 0; i < n; i++) {
                if (g[v][i] != null){
                    adjV.addElement(g[v][i]);
                }
            }
        return adjV;
    }

    public void traverseAdjacentEdge(int v){
        Iterable<Edge> adj = adjIterator(0);
        for (Edge a:adj
        ) {
            System.out.print(a.w() + " ");
        }
    }
}
