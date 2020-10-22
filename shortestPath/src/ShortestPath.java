import java.util.*;

public class ShortestPath {

    private double[] dist;
    private DirectedEdge[] edgeTo;
    private IndexMinHeapPQ<Integer> pri_queue;


    /** Build a shortestpath object using an edgeweighted digraph and a source vertex s. */
    public ShortestPath(EdgeWeightedDigraph G, int s) {
        dist = new double[G.V()];
        Arrays.fill(dist, Double.POSITIVE_INFINITY);
        dist[s] = 0.0;
        edgeTo = new DirectedEdge[G.V()];
        pri_queue = new IndexMinHeapPQ<>(G.V());
        pri_queue.insert(s, s, dist[s]);
        while (!pri_queue.isEmpty()) {
            int v = pri_queue.removeSmallest();
            System.out.println("Vertex " + v + " is removed from the PQ");
            relaxVertex(G, v);
        }
    }

    /** shortest distance from source s to vertex v. infinity if no path. */
    public double distTo(int v) {
        return dist[v];
    }

    /** Is there a path from source to v? */
    public boolean hasPathTo(int v) {
        return dist[v] < Double.POSITIVE_INFINITY;
    }

    /** Return the shortest path from source to v. */
    public Iterable<DirectedEdge> pathTo(int v) {
        if (!hasPathTo(v))
            return null;
        ArrayList<DirectedEdge> path = new ArrayList<>();

        for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()]) {
            path.add(0, e);
        }
        return path;
    }

    public static void main(String[] args) {
        ShortestPath sp =
                new ShortestPath(new EdgeWeightedDigraph(new In(args[0])), 0);
        Iterable<DirectedEdge> pathTo = sp.pathTo(6);
        for (DirectedEdge de: pathTo)
            System.out.println(de);
    }


    /** ----------------------------------------------------- */

    // relax an edge e from v to w, see if it creates a shorter path.
    private void relaxEdge(DirectedEdge e) {
        int v = e.from();
        int w = e.to();
        double new_dist = dist[v] + e.weight();
        if (new_dist < dist[w]) {
            dist[w] = new_dist;
            edgeTo[w] = e;
        }
    }

    // relax a vertex, check all outgoing edges from v.
    // add to the pri_queue if necessary.
    private void relaxVertex(EdgeWeightedDigraph G, int v) {
        for (DirectedEdge e: G.adj(v)) {
            int w = e.to();
            double new_dist = dist[v] + e.weight();
            if (new_dist < dist[w]) {
                dist[w] = new_dist;
                edgeTo[w] = e;
                if (pri_queue.contains(w)) {
                    pri_queue.change(w, w, dist[w]);
                    System.out.println("Vertex " + w + " 's dist value is changed");
                }
                else {
                    pri_queue.insert(w, w, dist[w]);
                    System.out.println("Vertex " + w + " is added to the PQ");
                }
            }
        }
    }


}
