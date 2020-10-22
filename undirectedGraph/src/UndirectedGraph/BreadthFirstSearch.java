package UndirectedGraph;

import edu.princeton.cs.algs4.In;

import java.util.ArrayDeque;

public class BreadthFirstSearch {

    private boolean[] marked;
    private int[] edgeTo;
    private Graph g;
    private int S;  // source vertex

    public BreadthFirstSearch(Graph G, int s) {
        this.g = G;
        this.S = s;
        marked = new boolean[g.V()];
        edgeTo = new int[g.V()];
        bfs(g, S);
    }

    private void bfs(Graph G, int v) {
        marked[v] = true;
        ArrayDeque<Integer> ade = new ArrayDeque<>();
        ade.addFirst(v);
        while (!ade.isEmpty()) {
            int x = ade.removeFirst();
            for (int w: G.adj(x)) {
                if (!marked[w]) {
                    ade.addLast(w);
                    marked[w] = true;
                    edgeTo[w] = x;
                }
            }
        }
    }

    // does a path from s to v exist?
    public boolean hasPathTo(int v) {
        return marked[v];
    }

    // find a path from s to v if it exists, return null otherwise
    public Iterable<Integer> PathTo(int v) {
        if (!hasPathTo(v))
            return null;
        ArrayDeque<Integer> path = new ArrayDeque<>();
        for (int i = v; i != S; i = edgeTo[i]) {
            path.addFirst(i);
        }
        path.addFirst(S);
        return path;
    }

    public static void main(String[] args) {
        Graph g = new Graph(new In(args[0]));
        System.out.println(g.toString());
        BreadthFirstSearch bfs = new BreadthFirstSearch(g, 0);
        System.out.println(bfs.hasPathTo(4));
        System.out.println(bfs.PathTo(0).toString());
    }



}
