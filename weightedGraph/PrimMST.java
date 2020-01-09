package weightedGraph;

import heap.IndexMinHeap;

import java.util.Vector;

public class PrimMST<Item extends Number & Comparable> {
    private Graph G;
    private IndexMinHeap<Item> ipq;
    private Edge<Item>[] edgeTo;
    private boolean[] marked;
    private Vector<Edge<Item>> mst;
    private Number mstWeight;//为了后面能计算这里一定要用Number类

    public PrimMST(Graph g){
        this.G = g;
        ipq = new IndexMinHeap<Item>(g.E());
        edgeTo = new Edge[g.V()];
        marked = new boolean[g.V()];//标记和每标记分别为两个不同的切分
        mst = new Vector<>(g.V());

        visit(0);//访问0号节点（初始情况）
        while(!ipq.isEmpty()){
            int minEdgeIndex = ipq.pushOutIndex();//最小索引堆是通过操作索引控制数据,索引被取对应数据也相当于被取出了
            assert edgeTo[minEdgeIndex] != null;
            mst.add(edgeTo[minEdgeIndex]);
            visit(minEdgeIndex);
        }

        mstWeight = mst.elementAt(0).getWeight();
        for( int i = 1 ; i < mst.size() ; i ++ )
            mstWeight = mstWeight.doubleValue() + mst.elementAt(i).getWeight().doubleValue();
    }

    void visit(int v) {
        assert !marked[v];
        marked[v] = true;//转换切分（标记）

        Iterable adj = G.adjIterator(v);//（取出访问点的所有邻边）
        for (Object a: adj
        ) {
            Edge<Item> adjEdge = (Edge<Item>) a;
            int w = adjEdge.another(v);
            if (!marked[w]) {//v在这条边另一个节点未被标记的才是横切边
                if (edgeTo[w] == null) { //如果与w对应横切边没有被标记
                    ipq.insert(w, adjEdge.getWeight());//将这条横切边对面的节点号作为索引，权值作为数据插入最小堆
                    edgeTo[w] = adjEdge;//节点号对应的索引处存储这条横切边
                }
                else if (edgeTo[w].getWeight().compareTo(adjEdge.getWeight()) > 0){//如果新的横切边比
                    edgeTo[w] = adjEdge;
                    ipq.change(w,edgeTo[w].getWeight());
                }
            }
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
