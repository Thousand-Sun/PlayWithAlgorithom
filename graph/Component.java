package graph;

public class Component {
    private Graph G;
    private boolean[] visited;
    private int ccount;
    private int[] id;

    Component(Graph G){
        this.G = G;
        this.visited = new boolean[G.V()];
        ccount = 0;
        id = new int[G.V()];
        for (int i = 0; i < G.V(); i++) {
            visited[i] = false;
            id[i] = -1;
        }
        for (int i = 0; i < G.V(); i++) {
            if (!visited[i]){
                dfs(i);//深度优先遍历所有连着的点
                ccount++;//遍历之后增加一个连通分量
            }
        }
    }

    private void dfs(int v){
        visited[v] = true;
        id[v] = ccount;
        Iterable adj = G.adjIterator(v);
        for (Object i:adj
             ) {
            if (!visited[(int) i])
                dfs((int) i);
        }
    }

    public int getCcount(){
        return ccount;
    }

    public boolean isConnected(int v,int w){
        assert v >= 0 && v < G.V();
        assert w >= 0 && w < G.V();
        return id[v] == id[w];
    }
}
