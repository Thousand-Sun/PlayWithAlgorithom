package weightedGraph;

import heap.MinHeap;

import javax.xml.stream.events.EndDocument;
import java.util.Vector;

public class KruskalMST<Item extends Number & Comparable> {

    private Vector<Edge<Item>> mst;
    private Number mstWeight;

    public KruskalMST(Graph graph){
        MinHeap<Edge<Item>> pq = new MinHeap(graph.E());
        for (int i = 0; i < graph.V(); i++) {
            for (Object e:graph.adjIterator(i)
            ) {
                Edge<Item> edge = (Edge<Item>) e;
                if (edge.v() < edge.w())
                    pq.insert(edge);
            }
        }

    }
}
