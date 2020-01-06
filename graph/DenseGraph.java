package graph;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Vector;

public class DenseGraph implements Graph{
    //邻接矩阵实现稠密图
    private int n,m;
    private boolean directed;
    private boolean[][] g;

    public DenseGraph(int n, boolean directed){
        assert n >= 0;
        this.n = n;
        this.m = 0;
        this.directed = directed;
        g = new boolean[n][n];
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
                int symbol = 0;
                if (g[i][j])
                    symbol = 1;
                System.out.print(symbol + "  ");
            }
            System.out.println();
        }
    }

    public boolean hasEdge(int v , int w){
        assert v >= 0 && v < n;
        assert w >= 0 && w < n;
        return g[v][w];
    }

    public void addEdge(int v,int w){
        assert v >= 0 && v < n;
        assert w >= 0 && w < n;

        //消除平行边
        if (hasEdge(v,w))
            return;
        g[v][w] = true;
        if (!directed)
            g[w][v] = true;
        m++;
    }

    //制作遍历临近点用的迭代器
    static class AdjIterator{
        private DenseGraph G;
        private int v;
        private int index;
        public AdjIterator(DenseGraph graph, int v){
            G = graph;
            this.v = v;
            this.index = -1;
        }

        int begin(){
            index = -1;
            return next();
        }

        int next(){
            for (index += 1;index < G.n;index++){
                if (G.g[v][index])
                    return index;
            }
            return -1;
        }

        boolean end(){
            return index >= G.n;
        }
    }

    public Iterable<Integer> adjIterator(int v){
        assert v >= 0 && v < n;
        Vector<Integer> adjV = new Vector<>();
        for (int i = 0; i < n; i++) {
                if (g[v][i]){
                    adjV.add(i);
                }
            }
        return adjV;
    }

    // 返回图中一个顶点的所有邻边
    // 由于java使用引用机制，返回一个Vector不会带来额外开销,
    public Iterable<Integer> adj(int v) {
        assert v >= 0 && v < n;
        Vector<Integer> adjV = new Vector<Integer>();
        for(int i = 0 ; i < n ; i ++ )
            if( g[v][i] )
                adjV.add(i);
        return adjV;
    }

    public void traverseAdjacentEdge(int v){
        Iterable adj = adjIterator(0);
        for (Object a:adj
        ) {
            System.out.print(a + " ");
        }
    }


    public static void main(String[] args){
        DenseGraph denseGraph = new DenseGraph(4,false);
        denseGraph.addEdge(0,1);
        denseGraph.addEdge(0,2);
        denseGraph.addEdge(0,3);
        denseGraph.addEdge(1,2);
//        System.out.println(Arrays.deepToString(denseGraph.show());
        DenseGraph.AdjIterator adjIterator = new DenseGraph.AdjIterator(denseGraph,1);
        for (int i = adjIterator.begin(); !adjIterator.end(); i = adjIterator.next()) {
            System.out.print(i + " ");
        }
        System.out.println();
        denseGraph.traverseAdjacentEdge(0);
    }
}
