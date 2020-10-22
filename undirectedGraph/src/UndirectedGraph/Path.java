package UndirectedGraph;

import java.util.*;

public class Path {

    private boolean[] marked;
    private int[] edgeTo;
    private int s;


    // find the paths in G from source s
    // An extension of depth-first search.
    public Path(Graph G, int s) {
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        this.s = s;
        depthFirstSearch(G, s);
    }

    // mark every vertex that is connected to vertex v
    // if marked[v] = true, then v is connected to s.
    private void depthFirstSearch(Graph G, int v) {
        marked[v] = true;
        for (int w: G.adj(v)) {
            if (!marked[w]) {
                edgeTo[w] = v;
                depthFirstSearch(G, w);
            }
        }
    }

    // is there a path from s to v?
    // if v is connected to s, then true. otherwise false.
    public boolean hasPathTo(int v) {
        return marked[v];
    }

    // get path from s to v, null of no such path
    public Iterable<Integer> pathTo(int v) {
        if (!marked[v])
            return null;
        ArrayList<Integer> path = new ArrayList<>();
        for (int i = v; i != s; i = edgeTo[i])
            path.add(0, i);
        path.add(0, s);
        return path;
    }

    public static void main(String[] args) {
//        In input = new In(args[0]);
//        UndirectedGraph.Graph g = new UndirectedGraph.Graph(input);
//        int s = Integer.parseInt(args[1]);
//        System.out.println(g.toString());
//        UndirectedGraph.Path my_path = new UndirectedGraph.Path(g, s);
//        Iterable<Integer> pathToAny = my_path.pathTo(1);
//        System.out.println(pathToAny.toString());
        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < 10; i++) {
            q.add(i);
        }
        for (int i: q)
            System.out.println(i);
    }
}
