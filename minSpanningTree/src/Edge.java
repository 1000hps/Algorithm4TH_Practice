public class Edge implements Comparable<Edge>  {
    private final int end_1;
    private final int end_2;
    private final double weight;

    public Edge(int v, int w, double weight) {
        this.end_1 = v;
        this.end_2 = w;
        this.weight = weight;
    }

    public int either() {
        return end_1;
    }

    public int other(int v) {
        if (v == end_1)
            return end_2;
        else if (v == end_2)
            return end_1;
        else
            throw new IllegalArgumentException("End number not correct!");
    }

    public double weight() {
        return weight;
    }

    public int compareTo(Edge that) {
        return Double.compare(this.weight(), that.weight());
    }

    public String toString() {
        return String.format("%d - %d  %.2f", end_1, end_2, weight);
    }

}
