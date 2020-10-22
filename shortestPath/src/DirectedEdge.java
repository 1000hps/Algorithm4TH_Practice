/** A class for weighted, directed edges. */



public class DirectedEdge {
    private final int v; // edge source
    private final int w; // edge target
    private final double weight; // edge weight

    public DirectedEdge(int v, int w, double weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    // weight of this edge.
    public double weight() {
        return weight;
    }

    // vertex this edge points from.
    public int from() {
        return v;
    }

    // vertex this edge points to.
    public int to() {
        return w;
    }

    // string representation
    public String toString() {
        return String.format("%d -> %d   %.2f", v, w, weight);
    }

    public static void main(String[] args) {
        DirectedEdge de1 = new DirectedEdge(1, 3, 0.25);
        System.out.println(de1);
    }
}
