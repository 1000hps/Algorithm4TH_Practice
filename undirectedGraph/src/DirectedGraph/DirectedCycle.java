package DirectedGraph;

import edu.princeton.cs.algs4.In;

import java.util.*;

public class DirectedCycle {

    private boolean[] marked;
    private int[] edgeTo;
    private ArrayList<Integer> cycle;
    private boolean[] onStack;

    /** cycle-finding constructor. */
    public DirectedCycle(Digraph G) {
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        onStack = new boolean[G.V()];
        for (int i = 0; i < G.V(); i++) {
            if (!marked[i])
                searchCycle(G, i);
        }
    }

    private void searchCycle(Digraph dg, int v) {
        marked[v] = true;
        onStack[v] = true;
        for (int w: dg.adj(v)) {
            if (hasCycle())
                return;
            else if (!marked[w]) {
                edgeTo[w] = v;
                searchCycle(dg, w);
            } else if (onStack[w]) { // find a cycle
                cycle = new ArrayList<>();
                for (int i = v; i != w; i = edgeTo[i])
                    cycle.add(0, i);
                cycle.add(0, w);
                cycle.add(0, v);
            }
        }
        onStack[v] = false; // clear onStack label for subbranch, very important
    }


    /** Does this digraph have directed cycle? */
    public boolean hasCycle() {
        return cycle != null;
    }

    /** vertices on a directed cycle (if one exists) */
    public Iterable<Integer> cycle() {
        return cycle;
    }

    public static void main(String[] args) {
        Digraph dg = new Digraph(new In(args[0]));
        DirectedCycle dcyc = new DirectedCycle(dg);
        System.out.println(dg.toString());
        System.out.println("");
        if (dcyc.hasCycle()) {
            System.out.println("This digraph has cycle: ");
            System.out.println(dcyc.cycle().toString());
        }


    }

}
