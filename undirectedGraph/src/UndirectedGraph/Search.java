package UndirectedGraph;

import edu.princeton.cs.algs4.In;

public class Search {

    private boolean[] marked;
    private int count;

    // search the vertices connected to s
    public Search(Graph G, int s) {
        marked = new boolean[G.V()];
        depthFirstSearch(G, s);
    }

    private void depthFirstSearch(Graph G, int s) {
        marked[s] = true;
        count += 1;
        for (int w: G.adj(s)) {
            if (!marked[w]) {
                depthFirstSearch(G, w);
            }
        }
    }

    // is v marked? every marked vertex is connected to s
    public boolean marked(int v) {
        return marked[v];
    }

    // count the number of connected vertices to s
    public int count() {
        return count;
    }

    public static void main(String[] args) {
        Graph g = new Graph(new In(args[0]));
        System.out.println(g.toString());
        Search dfs = new Search(g, 7);
        System.out.println("# of vertices connected to 7: " + dfs.count());
    }
}
