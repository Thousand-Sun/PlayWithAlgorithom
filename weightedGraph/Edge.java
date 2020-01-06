package weightedGraph;

public class Edge {
    private int a,b;
    private double weight;

    public Edge(int a,int b,double weight){
        this.a = a;
        this.b = b;
        this.weight = weight;
    }

    public Edge(){}

    public int v(){
        return a;
    }

    public int w(){
        return b;
    }

    public double getWeight(){
        return weight;
    }

    public int another(int x){
        assert x == a || x == b;
        return x == a?b:a;
    }

    public void show(){
        System.out.println(a + "*-" + weight + "-*" + b);
    }

    boolean less(Edge edge){
        return weight < edge.weight;
    }
    boolean notMore(Edge edge){
        return weight <= edge.weight;
    }
    boolean more(Edge edge){
        return weight > edge.weight;
    }
    boolean notLess(Edge edge){
        return weight >= edge.weight;
    }
    boolean equal(Edge edge){
        return weight == edge.weight;
    }
}
