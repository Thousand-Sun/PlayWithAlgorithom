package weightedGraph;

import heap.MinHeap;
import tree.UnionFind;
import java.util.Vector;

public class KruskalMST<Item extends Number & Comparable> {

    private Vector<Edge<Item>> mst;
    private Number mstWeight;

    public KruskalMST(Graph graph){
        mst = new Vector<Edge<Item>>();
        MinHeap<Edge<Item>> pq = new MinHeap<Edge<Item>>(graph.E());
        for (int i = 0; i < graph.V(); i++) {
            for (Object e:graph.adjIterator(i)
            ) {
                Edge<Item> edge = (Edge<Item>) e;
                if (edge.v() <= edge.w())
                    pq.insert(edge);
            }
        }

        UnionFind uf = new UnionFind(graph.V());
        while (!pq.isEmpty() && mst.size() < graph.V() - 1){
            Edge<Item> edge = pq.pushOut();
            if (uf.isConnect(edge.v(),edge.w())){
                continue;
            }

            mst.add(edge);
            uf.unionElements(edge.v(),edge.w());
        }

        mstWeight = mst.elementAt(0).getWeight();
        for( int i = 1 ; i < mst.size() ; i ++ )
            mstWeight = mstWeight.doubleValue() + mst.elementAt(i).getWeight().doubleValue();


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
