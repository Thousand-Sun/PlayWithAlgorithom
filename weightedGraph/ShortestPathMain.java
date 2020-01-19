package weightedGraph;

public class ShortestPathMain {
    public static void main(String[] args){
        SparseGraph sparseGraph = new SparseGraph(5,true);
        sparseGraph.addEdge(0,1,2);
        sparseGraph.addEdge(0,2,5);
        sparseGraph.addEdge(0,3,8);
        sparseGraph.addEdge(1,2,2);
        sparseGraph.addEdge(1,4,2);
        sparseGraph.addEdge(2,3,2);
        sparseGraph.addEdge(2,4,1);
        sparseGraph.addEdge(3,4,2);

        Dijkstra dijkstra = new Dijkstra(sparseGraph,0);
        dijkstra.showPath(3);

        SparseGraph sparseGraph1 = new SparseGraph(5,true);
        sparseGraph1.addEdge(0,1,5);
        sparseGraph1.addEdge(0,2,2);
        sparseGraph1.addEdge(0,3,6);
        sparseGraph1.addEdge(1,2,-4);
        sparseGraph1.addEdge(1,4,2);
        sparseGraph1.addEdge(2,3,3);
        sparseGraph1.addEdge(2,4,5);
        sparseGraph1.addEdge(4,3,-3);

        BellmanFord bellmanFord = new BellmanFord(sparseGraph1,0);
        bellmanFord.showPath(4);
    }
}
