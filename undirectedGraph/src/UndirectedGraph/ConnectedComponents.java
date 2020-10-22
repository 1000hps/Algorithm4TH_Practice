package UndirectedGraph;

import edu.princeton.cs.algs4.In;

/** find the number of connected components of a graph G.
 *  Use depth-first search(DFS).
 *  store the vertices in different components.
 */

public class ConnectedComponents {

    private int count;
    private boolean[] marked;
    private int[] id; // store the id of each different connected parts. from 0 to count - 1
    private boolean hasCycle;

    // constructor
    public ConnectedComponents(Graph G) {
        marked = new boolean[G.V()];
        id = new int[G.V()];
        for (int i = 0; i < G.V(); i++) {
            if (!marked[i]) {
                dfs(G, i);
                count += 1;
            }
        }

        // check if there is a cycle
        for (int i = 0; i < G.V(); i++) {
            dfs(G, 0, 0);
        }
    }

    private void dfs(Graph G, int v) {
        marked[v] = true;
        id[v] = count;
        for (int w: G.adj(v)) {
            if (!marked[w])
                dfs(G, w);
        }
    }

    public boolean isHasCycle() {
        return hasCycle;
    }

    /** Return if graph G has cycle or not.
     *  nextChild must be adjacent to lastParent.
     * @param G
     * @param nextChild
     * @param lastParent
     */
    private void dfs(Graph G, int nextChild, int lastParent) {
        marked[nextChild] = true;
        for (int w: G.adj(nextChild)) {
            if (!marked[w]) {
                dfs(G, w, nextChild);
            } else if (w != lastParent)
                // in this case, w is adjacent to nextChild and marked (connected to lastParent)
                // but not lastParent, there must be a cycle.
                hasCycle = true;
        }
    }

    // is v and w connected?
    public boolean connected(int v, int w) {
        return id[v] == id[w];
    }

    // return the number of connected components of a given graph.
    public int count() {
        return count;
    }

    // return the component identifier for vertex v
    // should be between 0 and count() - 1.
    public int id(int v) {
        return id[v];
    }


    public static void main(String[] args) {
        Graph g = new Graph(new In(args[0]));
        ConnectedComponents cc = new ConnectedComponents(g);
//        System.out.println(cc.connected(7, 8));
//        System.out.println(cc.connected(9, 12));
//        System.out.println(cc.connected(0, 12));
//        System.out.println(cc.count());
//        System.out.println(cc.id(5));
        System.out.println(cc.isHasCycle());
    }

}
