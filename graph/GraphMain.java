package graph;

public class GraphMain {
    public static void main(String[] args){
        SparseGraph sparseGraph = new SparseGraph(6,false);
        sparseGraph.addEdge(0,1);
        sparseGraph.addEdge(0,3);
        sparseGraph.addEdge(1,3);
        sparseGraph.addEdge(4,5);
        sparseGraph.addEdge(4,3);
        sparseGraph.show();
        Component component1 = new Component(sparseGraph);
        System.out.println(component1.getCcount());

        System.out.println();
        DenseGraph denseGraph = new DenseGraph(6,false);
        denseGraph.addEdge(0,1);
        denseGraph.addEdge(0,3);
        denseGraph.addEdge(1,3);
        denseGraph.addEdge(3,4);
        denseGraph.addEdge(4,5);
        denseGraph.show();

        Component component2 = new Component(denseGraph);
        System.out.println(component2.getCcount());

        Path path = new Path(denseGraph,0);
        path.showPath(3);
        PathFinder pathFinder = new PathFinder(sparseGraph,0);
        path.showPath(5);

    }
}
