package DirectedGraph;

import edu.princeton.cs.algs4.In;

import java.util.*;

public class DirectedOrder {

    private boolean[] marked;
    private ArrayList<Integer> pre_order;
    private ArrayList<Integer> post_order;
    private ArrayList<Integer> reverse_post;

    public DirectedOrder(Digraph DG) {
        int N = DG.V();
        marked = new boolean[N];
        pre_order = new ArrayList<>();
        post_order = new ArrayList<>();
        reverse_post = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            if (!marked[i])
                search(DG, i);
        }
    }

    private void search(Digraph DG, int v) {
        marked[v] = true;
        pre_order.add(v);
        for (int w: DG.adj(v)) {
            if (!marked[w])
                search(DG, w);
        }
        post_order.add(v);
        reverse_post.add(0, v);
    }

    public Iterable<Integer> pre() {
        return pre_order;
    }

    public Iterable<Integer> post() {
        return post_order;
    }

    public Iterable<Integer> reversePost() {
        return reverse_post;
    }

    public static void main(String[] arg) {
        Digraph d = new Digraph(new In(arg[0]));
        DirectedOrder dor = new DirectedOrder(d);
        System.out.println(dor.reversePost().toString());
        System.out.println(dor.post().toString());
        System.out.println(dor.pre().toString());
    }
}
