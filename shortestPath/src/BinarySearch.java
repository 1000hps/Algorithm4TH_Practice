public class BinarySearch {

    // assume array is sorted in ascending order.
    private static int search(int[] arr, int n) {
        return biSearch(arr, n, 0, arr.length - 1);
    }

    private static int biSearch(int[] arr, int n, int low, int high) {
        if (low > high)
            return -1;
        if (n < arr[low + (high - low) / 2])
            return biSearch(arr, n, low, low + (high - low) / 2 - 1);
        else if (n > arr[low + (high - low) / 2])
            return biSearch(arr, n, low + (high - low) / 2 + 1, high);
        else
            return low + (high - low) / 2;
    }

    public static void main(String[] args) {
        int[] arr = {2, 4, 5, 6, 10, 12, 15};
        System.out.println(search(arr, 212));
    }
}
