package graph;

public interface Graph{
    public int V();
    public int E();
    public boolean hasEdge(int v , int w);
    public void addEdge(int v,int w);
    public void show();
    public Iterable adjIterator(int v);
}
