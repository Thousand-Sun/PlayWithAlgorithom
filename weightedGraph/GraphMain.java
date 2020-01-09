package weightedGraph;

import java.util.Arrays;

public class GraphMain {
    public static void main(String[] args){
        SparseGraph sparseGraph = new SparseGraph(7,false);
        sparseGraph.addEdge(0,1,2);
        sparseGraph.addEdge(0,2,1);
        sparseGraph.addEdge(1,2,3);
        sparseGraph.addEdge(2,3,2);
        sparseGraph.addEdge(1,3,1.5);
        sparseGraph.addEdge(4,2,3);
        sparseGraph.addEdge(4,3,2);
        sparseGraph.addEdge(4,6,2.5);
        sparseGraph.show();
        sparseGraph.traverseAdjacentEdge(0);
        System.out.println(sparseGraph.hasEdge(1,2));

        LazyPrimMST lazyprimMST = new LazyPrimMST(sparseGraph);
        lazyprimMST.showMinScanTree();
        long startTime=System.nanoTime();
        System.out.println(lazyprimMST.getMstWeight());
        long endTime=System.nanoTime();
        System.out.println((endTime-startTime)+"ns");

        PrimMST primMST = new PrimMST(sparseGraph);
        primMST.showMinScanTree();
        startTime=System.nanoTime();
        System.out.println(primMST.getMstWeight());
        endTime=System.nanoTime();
        System.out.println((endTime-startTime)+"ns");

//        DenseGraph denseGraph = new DenseGraph(7,false);
//        denseGraph.addEdge(0,1,2);
//        denseGraph.addEdge(0,2,1);
//        denseGraph.addEdge(1,2,3);
//        denseGraph.addEdge(2,3,2);
//        denseGraph.addEdge(1,3,1.5);
//        denseGraph.addEdge(4,2,3);
//        denseGraph.addEdge(4,3,2);
//        denseGraph.addEdge(4,6,2.5);
//        denseGraph.show();
//        denseGraph.traverseAdjacentEdge(1);

    }
}
