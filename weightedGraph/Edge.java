package weightedGraph;

public class Edge<Item extends Comparable> implements Comparable<Edge<Item>> {
    private int a,b;
    private Item weight;

    public Edge(int a,int b,Item weight){
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

    public Item getWeight(){
        return weight;
    }

    public int another(int x){
        assert x == a || x == b;
        return x == a?b:a;
    }

    public void show(){
        System.out.println(a + "*-" + weight + "-*" + b);
    }

    @Override
    //Edge类的比较方式是比较其权值大小
    public int compareTo(Edge<Item> that) {
        if (this.weight.compareTo(that.weight) < 0)
            return -1;
        else if (this.weight.compareTo(that.weight) > 0)
            return 1;
        else
            return 0;
    }
}
