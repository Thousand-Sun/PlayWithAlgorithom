package weightedGraph;

import heap.IndexMinHeap;

import java.util.Stack;
import java.util.Vector;

public class Dijkstra<Item extends Number & Comparable> {

    private Graph graph;
    int s;
    private IndexMinHeap<Item> indexMinHeap;
    private Edge<Item>[] from;
    private Number[] distTo;//存储s到某一点的最短路径长度
    private boolean[] marked;

    Dijkstra(Graph g,int source){
        this.graph = g;
        assert source >= 0 && source < g.V();
        this.s = source;
        indexMinHeap = new IndexMinHeap<Item>(graph.V());
        from = new Edge[g.V()];
        distTo = new Number[g.V()];
        marked = new boolean[g.V()];
        for (int i = 0; i < g.V(); i++) {
            distTo[i] = 0;
            marked[i] = false;
            from[i] = null;
        }

        /*---dijkstra算法---*/
        //初始化源点
        marked[s] = true;
        indexMinHeap.insert(s,(Item) distTo[s]);
        while( !indexMinHeap.isEmpty() ){
            int v = indexMinHeap.pushOutIndex();//推出最小权边索引
            marked[v] = true;
            for (Object e: graph.adjIterator(v) //遍历该索引所有邻边
                 ) {
                Edge<Item> edge = (Edge<Item>) e;
                int w = edge.another(v);//邻边对面的点
                if( !marked[w] ){//如果对面点未标记
                    //更新distTo列表
                    if( from[w] == null || distTo[v].doubleValue() + edge.getWeight().doubleValue() < distTo[w].doubleValue()){
                        distTo[w] = distTo[v].doubleValue() + edge.getWeight().doubleValue();
                        from[w] = edge;//修改到达w最近的边
                        //调整最小索引树
                        if (indexMinHeap.contain(w))
                            indexMinHeap.change(w,(Item)distTo[w]);
                        else
                            indexMinHeap.insert(w,(Item)distTo[w]);
                    }
                }
            }
        }
    }



    public Number getDistTo(int w){
        return distTo[w];
    }

    public boolean hasPath(int w){
        return marked[w];
    }

    void shortestPath(int w,Vector<Edge<Item>> vector){
        Stack<Edge<Item>> stack = new Stack<Edge<Item>>();
        Edge<Item> edge = from[w];
        while (edge.v() != this.s){//当终点为w的边的始点不为源点时
            stack.push(edge);
            edge = from[edge.v()];
        }
        stack.push(edge);//这里还要将源点出发的边加入栈中

        while (!stack.isEmpty()){
            edge = stack.pop();
            vector.add(edge);
        }
    }

    public void showPath(int w){
        assert w >= 0 && w < graph.V();
        assert hasPath(w);

        Vector<Edge<Item>> vector = new Vector<>();
        shortestPath(w,vector);
        for (int i = 0; i < vector.size(); i++) {
            vector.elementAt(i).show();
        }
    }
}
