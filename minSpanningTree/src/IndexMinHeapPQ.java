/** An implementation of 1-indexed min heap priority queue. */

import java.util.*;

public class IndexMinHeapPQ<Item> {
    private int N;    // number of items in the queue.
    private int[] pq; // pq[1] has the item with the min priority
    private int[] qp; // locations of indices of items in pq
    private Node[] nodes; // store all items.

    private class Node {
        private Item item;
        private double priority;

        public Node(Item key, double priority) {
            item = key;
            this.priority = priority;
        }
    }

    /** Build a index min heap priority queue of size maxN. */
    public IndexMinHeapPQ(int maxN) {
        pq = new int[maxN + 1];
        qp = new int[maxN + 1];
        Arrays.fill(qp, -1);
        nodes = new IndexMinHeapPQ.Node[maxN + 1];
    }

    /** Is k associated with any item? */
    public boolean contains(int k) {
        return qp[k] != -1;
    }

    /** Insert Node n and associate it with int k. */
    public void insert(int k, Item item, double priority) {
        if (k < 0 || k > pq.length)
            throw new IllegalArgumentException("Index k out of bounds!");
        if (contains(k))
            throw new IllegalArgumentException("Index k already used!");
        Node n = new Node(item, priority);
        N += 1;
        pq[N] = k;
        qp[k] = N;
        nodes[k] = n;
        swim(N);
    }

    /** Change the item priority associated with k. */
    public void change(int k, Item item, double priority) {
        if (k < 0 || k > pq.length)
            throw new IllegalArgumentException("Index k out of bounds!");
        if (!contains(k))
            throw new IllegalArgumentException("Index k is not used!");
        nodes[k].priority = priority;
        swim(qp[k]);
        sink(qp[k]);
    }

    public int size() {
        return N;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    /** Get the item with the min priority. */
    public Item getSmallest() {
        if (isEmpty())
            throw new NoSuchElementException("The queue is empty!");
        return nodes[pq[1]].item;
    }

    /** Get and remove the item with the min priority. */
    public Item removeSmallest() {
        if (isEmpty())
            throw new NoSuchElementException("The queue is empty!");
        Item result = nodes[pq[1]].item;
        exchange(1, N);
        N -= 1;
        sink(1);
        nodes[pq[N + 1]] = null;
        qp[pq[N + 1]] = -1;
        return result;
    }

    /** Get the min item's index. */
    public int minIndex() {
        return pq[1];
    }



    public static void main(String[] args) {
        IndexMinHeapPQ<Integer> inpq = new IndexMinHeapPQ<>(6);
        inpq.insert(0, 0, 2.5);
        inpq.insert(2, 2, 1.5);
        inpq.insert(3, 3, 4.5);
        inpq.insert(5, 5,  2.1);
        System.out.println("size: " + inpq.size());
        inpq.change(3, 3, 0.35);
        inpq.change(5, 5, 0.16);
        for (int i = 0; i < 4; i++) {
            System.out.println("current smallest item: " + inpq.removeSmallest());
        }

    }

    /** --------------------------------------------------- */
    /** --------------------------------------------------- */
    private Comparator<Node> pqComparator() {
        return new comparePriority();
    }

    private class comparePriority implements Comparator<Node>{

        public int compare(Node n1, Node n2) {
            return Double.compare(n1.priority, n2.priority);
        }
    }

    private void swim(int k) {
        while (k > 1 && isLarger(k / 2, k)) {
            exchange(k / 2, k);
            k = k / 2;
        }
    }

    private void sink(int k) {
        while (2 * k <= N) {
            int j = 2 * k;
            if (j + 1 <= N && isLarger(j, j + 1))
                j += 1;
            if (!isLarger(k, j))
                break;
            exchange(k, j);
            k = j;
        }
    }

    private void exchange(int i, int j) {
        int k = pq[i];
        pq[i] = pq[j];
        pq[j] = k;
        qp[pq[i]] = i;
        qp[pq[j]] = j;
    }

    private boolean isLarger(int i, int j) {
        return pqComparator().compare(nodes[pq[i]], nodes[pq[j]]) > 0;
    }

}
