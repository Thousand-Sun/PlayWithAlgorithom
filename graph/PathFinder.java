package graph;

import java.util.Stack;
import java.util.Vector;

public class PathFinder {
    private Graph G;
    private int s;
    private boolean[] visited;
    private int[] from;

    private void dfs(int v){
        visited[v] = true;

//        Iterable adj = G.adjIterator(v);
        for (Object i:G.adjIterator(v)
        ) {
            if (!visited[(int) i]) {
                from[(int) i] = v;
                dfs((Integer) i);
            }
        }
    }

    //表示从G的s出发的所有点的可行路径
    public PathFinder(Graph graph, int s){
        G = graph;
        assert s >= 0 && s < G.V();
        visited = new boolean[G.V()];
        from = new int[G.V()];
        for (int i = 0; i < G.V(); i++) {
            visited[i] = false;
            from[i] = -1;
        }
        this.s = s;

        //寻路算法（非最短）
        dfs(s);
    }



    public boolean hasPath(int w){
        assert w >= 0 && w < G.V();
        return visited[w];
    }

    private Vector<Integer> path(int w){
        assert hasPath(w);
        Stack<Integer> stack = new Stack<>();

        int p = w;
        while(p != -1){
            stack.push(p);
            p = from[p];
        }

        Vector<Integer> vector = new Vector<>();
        vector.clear();
        while (!stack.empty()){
            vector.add(stack.pop());
        }

        return vector;
    }

    void showPath(int w){
        Vector<Integer> vector = path(w);
        for (int i = 0; i < vector.size(); i++) {
            System.out.print(vector.elementAt(i));
            if (i == vector.size() - 1)
                System.out.println();
            else
                System.out.print("->");
        }
    }
}
