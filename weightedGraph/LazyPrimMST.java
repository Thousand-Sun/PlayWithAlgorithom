package weightedGraph;

import heap.MinHeap;

import java.util.Vector;

public class LazyPrimMST<Item extends Number & Comparable> {
    private Graph G;
    private MinHeap<Edge<Item>> pq;
    private boolean[] marked;
    private Vector<Edge<Item>> mst;
    private Number mstWeight;//为了后面能计算这里一定要用Number类

    public LazyPrimMST(Graph g){
        this.G = g;
        pq = new MinHeap<>(g.E());
        marked = new boolean[g.V()];//标记和每标记分别为两个不同的切分
        mst = new Vector<>(g.V());

        visit(0);//访问0号节点（初始情况）
        while(!pq.isEmpty()){
            Edge<Item> minEdge = pq.pushOut();
            if (marked[minEdge.v()] == marked[minEdge.w()])
                continue;

            mst.add(minEdge);
            if (!marked[minEdge.v()])
                visit(minEdge.v());
            else
                visit(minEdge.w());
        }

        mstWeight = mst.elementAt(0).getWeight();
        for( int i = 1 ; i < mst.size() ; i ++ )
            mstWeight = mstWeight.doubleValue() + mst.elementAt(i).getWeight().doubleValue();
    }

    void visit(int v) {
        assert !marked[v];
        marked[v] = true;//转换切分（标记）

        Iterable<Edge> adj = G.adjIterator(v);//（取出访问点的所有邻边）
        for (Edge adjEdge: adj
             ) {
            if (!marked[adjEdge.another(v)])//v在这条边另一个节点未被标记的才是横切边
                pq.insert(adjEdge);//将横切边放入最小堆中
        }
    }

    Vector<Edge<Item>> getMst(){
        return mst;
    }

    Number getMstWeight(){
        return mstWeight;
    }

    void showMinScanTree(){
        for (Edge e:mst
        ) {
            e.show();
        }
    }
}
