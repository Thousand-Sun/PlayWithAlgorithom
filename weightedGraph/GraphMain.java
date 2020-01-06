package weightedGraph;


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
//        Component component1 = new Component(sparseGraph);
//        System.out.println(component1.getCcount());

        System.out.println();
        DenseGraph denseGraph = new DenseGraph(7,false);
        denseGraph.addEdge(0,1,2);
        denseGraph.addEdge(0,2,1);
        denseGraph.addEdge(1,2,3);
        denseGraph.addEdge(2,3,2);
        denseGraph.addEdge(1,3,1.5);
        denseGraph.addEdge(4,2,3);
        denseGraph.addEdge(4,3,2);
        denseGraph.addEdge(4,6,2.5);
        denseGraph.show();
        denseGraph.traverseAdjacentEdge(1);

//        Component component2 = new Component(denseGraph);
//        System.out.println(component2.getCcount());

//        Path path = new Path(denseGraph,0);
//        path.showPath(3);
//        PathFinder pathFinder = new PathFinder(denseGraph,0);
//        pathFinder.showPath(3);
//        ShortestPathFinder shortestPathFinder = new ShortestPathFinder(denseGraph,0);
//        shortestPathFinder.showPath(3);
    }
}
