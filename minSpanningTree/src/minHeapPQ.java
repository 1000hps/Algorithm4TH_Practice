import java.util.*;

public class minHeapPQ<Item extends Comparable<Item>> {

    private Item[] pq;
    private int N;

    public minHeapPQ() {
        pq = (Item[]) new Comparable[8];
    }

    public void insert(Item item) {
        N += 1;
        pq[N] = item;
        if (N == pq.length - 1) {
            pq = resize(pq, pq.length * 2);
        }
        swim(N);
    }

    public Item getMin() {
        return pq[1];
    }

    public Item delMin() {
        Item result = pq[1];
        exchange(1, N);
        pq[N] = null;
        N -= 1;
        sink(1);
        return result;
    }

    public int size() {
        return N;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    /** main */
    public static void main(String[] args) {
        minHeapPQ<Double> minpq = new minHeapPQ<>();
        for (double d = 8; d >= 4; d -= 0.5)
            minpq.insert(d);
        for (int i = 0; i < 10; i++) {
            if (!minpq.isEmpty())
                System.out.println(minpq.delMin());
            else
                System.out.println("queue is empty!");
        }
    }


    /** ------------------------------------------- */

    private Item[] resize(Item[] arr, int n) {
        Item[] temp = (Item[]) new Comparable[n];
        System.arraycopy(arr, 0, temp, 0, arr.length);
        return temp;
    }

    private void swim(int k) {
        while (k > 1 && pq[k/2].compareTo(pq[k]) > 0) {
            exchange(k/2, k);
            k = k/2;
        }
    }

    private void exchange(int i, int j) {
        Item temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
    }

    private void sink(int k) {
        while (2 * k <= N) {
            int j = 2 * k;
            if (j + 1 <= N && pq[j + 1].compareTo(pq[j]) < 0)
                j += 1;
            if (pq[k].compareTo(pq[j]) <= 0)
                break;
            exchange(k, j);
            k = j;
        }
    }
}
