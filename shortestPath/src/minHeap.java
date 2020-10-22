import java.util.*;

public class minHeap<Item> {
    private Node[] pq;
    private int N;

    private class Node {
        private Item item;
        private double priority;

        public Node(Item key, double priority) {
            item = key;
            this.priority = priority;
        }
    }

    public minHeap() {
        pq = new minHeap.Node[4];
    }

    /** Return if the queue contains the key.
     *  Current run time is linear!
     * @param key
     * @return
     */
    public boolean contains(Item key) {
        for (int i = 1; i <= N; i++) {
            if (pq[i].item.equals(key))
                return true;
        }
        return false;
    }

    /** Add the key with priority into the queue.
     *  If the key already exists, throw a illegalargumentexception.
     * @param key
     * @param priority
     */
    public void add(Item key, double priority) {
        if (contains(key))
            throw new IllegalArgumentException("Key already in the queue!");
        N += 1;
        pq[N] = new Node(key, priority);
        swim(N);
        if (N == pq.length - 1)
            resize(pq.length * 2);

    }

    /** Retrieve the key with the min priority.
     *  If no items exist, throw a nosuchelementexception.
     * @return
     */
    public Item getSmallest() {
        if (N == 0)
            throw new NoSuchElementException("The queue is empty!");
        return pq[1].item;
    }

    /** Retrieve and remove the key with the min priority.
     *  If no item exists, throw a nosuchelementexception.
     * @return
     */
    public Item removeSmallest() {
        if (N == 0)
            throw new NoSuchElementException("The queue is empty!");
        Item result = pq[1].item;
        exchange(1, N);
        pq[N] = null;
        N -= 1;
        sink(1);
        return result;
    }

    /** Return the number of keys in the queue. */
    public int size() {
        return N;
    }


    public static void main(String[] args) {
        minHeap<String> pq1 = new minHeap<>();
        pq1.add("todo 1", 0.5);
        pq1.add("todo 2", 0.3);
        pq1.add("todo 3", 0.33);
        pq1.add("what", 0.8);
        pq1.add("this", 0.2);
        pq1.add("when", 1.2);
        pq1.add("check", 2.8);
        pq1.add("find", 0.55);
        System.out.println(pq1.size());
        System.out.println(pq1.contains("todo 3"));
        System.out.println(pq1.removeSmallest());
        System.out.println(pq1.size());
        for (int i = 0; i <= 6; i++) {
            System.out.println(pq1.removeSmallest());
        }
    }


    /** ---------------------------------------------- */
    /** ---------------------------------------------- */
    /** Private methods. */

    private void resize(int R) {
        Node[] temp = new minHeap.Node[R];
        System.arraycopy(pq, 0, temp, 0, pq.length);
        pq = temp;
    }

    private Comparator<Node> pqComparator() {
        return new comparePriority();
    }


    private class comparePriority implements Comparator<Node>{

        public int compare(Node n1, Node n2) {
            return Double.compare(n1.priority, n2.priority);
        }
    }

    private boolean isLarger(int i, int j) {
        return pqComparator().compare(pq[i], pq[j]) > 0;
    }

    private void swim(int k) {
        while ( k > 1 && isLarger(k / 2, k)) {
            exchange(k / 2, k);
            k = k / 2;
        }
    }

    private void sink(int k) {
        while (2 * k <= size()) {
            int j = 2 * k;
            if (j + 1 <= size() && isLarger(j, j + 1))
                j += 1;
            if (!isLarger(k, j))
                break;
            exchange(k, j);
            k = j;
        }
    }

    private void exchange(int i, int j) {
        Node temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
    }




}
