package graph;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.Vector;

public class ShortestPathFinder {
    private Graph G;
    private int s;
    private boolean[] visited;
    private int[] from;
    private int[] ord;

    public ShortestPathFinder(Graph g,int s){
        G = g;
        assert s >= 0 && s < G.V();

        visited = new boolean[G.V()];
        from = new int[G.V()];
        ord = new int[G.V()];
        for (int i = 0; i < G.V(); i++) {
            visited[i] = false;
            from[i] = -1;
            ord[i] = -1;
        }
        this.s = s;

        Queue<Integer> queue = new LinkedList<>();

        queue.offer(s);
        visited[s] = true;
        ord[s] = 0;

        while (!queue.isEmpty()){
            int v = queue.poll();
            for (Object i: G.adjIterator(v)
                 ) {
                if (!visited[(int) i]){
                    queue.offer((Integer) i);
                    visited[(int) i] = true;
                    from[(int) i] = v;
                    ord[(int) i] = ord[v] + 1;
                }
            }
        }
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

    int length(int w){
        assert w >= 0 && w < G.V();
        return ord[w];
    }
}

