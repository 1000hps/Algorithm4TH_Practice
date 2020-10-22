package DirectedGraph;

import edu.princeton.cs.algs4.In;

public class DirectedDFS {

    private boolean[] marked;

    /** Find vertices in G that are reachable from vertex s.
     *  Use depth-first search.
     */
    public DirectedDFS(Digraph G, int s) {
        marked = new boolean[G.V()];
        dfs(G, s);
    }

    /** Find vertices in G that are reachable from sources. */
    public DirectedDFS(Digraph G, Iterable<Integer> sources) {
        marked = new boolean[G.V()];
        for (int w: sources)
            if (!marked[w])
                dfs(G, w);
    }

    private void dfs(Digraph G, int v) {
        marked[v] = true;
        for (int w: G.adj(v))
            if (!marked[w])
                dfs(G, w);
    }

    /** is v reachable from s? */
    public boolean marked(int v) {
        return marked[v];
    }

    public static void main(String[] arg) {
        Digraph dg = new Digraph(new In(arg[0]));
        System.out.println(dg.toString());
    }
}
