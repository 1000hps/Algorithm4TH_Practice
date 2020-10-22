package UndirectedGraph;

import edu.princeton.cs.algs4.In;

/** Given a graph G, check if it satisfies the 'two color' condition. */

public class TwoColor {

    private int[] EO; // an array of 0s and 1s to represent two colors
    private boolean[] marked;
    private boolean isTwoColor = true;

    public TwoColor(Graph G) {
        EO = new int[G.V()];
        marked = new boolean[G.V()];
        for (int i = 0; i < G.V(); i++) {
            if (!marked[i]) {
                checkTwoColor(G, i);
            }
        }
    }

    /** Check all connected vertices starting at v in graph G.
     *  Use depth-first search.
     * @param G
     * @param v
     */
    private void checkTwoColor(Graph G, int v) {
        marked[v] = true;
        for (int w: G.adj(v)) {
            if (!marked[w]) {
                EO[w] = 1 - EO[v];
                checkTwoColor(G, w);
            } else if (EO[w] == EO[v])
                isTwoColor = false;
        }
    }

    public boolean isTwoColor() {
        return isTwoColor;
    }

    public static void main(String[] args) {
        Graph g = new Graph(new In(args[0]));
        TwoColor tc = new TwoColor(g);
        System.out.println("Is graph G 2-colored? : " + tc.isTwoColor());

    }
}
