package cc.jennylam.cs146;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Quicksort {
    public static void main(String[] args) {
        int[] a = {4, 6, 2, 42, 1, 9000, 263, 42, 146, -5};
        System.out.println(Arrays.toString(a));
        sort(a, 0, a.length);
        System.out.println(Arrays.toString(a));
    }

    // sort a from index i (inclusive) to index j (exclusive)
    public static void sort(int[] a, int i, int j) {
        if (j - i <= 1) return;
        int p = hoarePartition(a, i, j);
        sort(a, i, p);
        sort(a, p+1, j);
    }

    // partition using a[i] as pivot, from i to j
    // and return the index of the pivot's new location
    private static int partition(int[] a, int i, int j) {
        // Step 1: move all the elements into 1 of 2 lists
        int pivot = a[i];
        List<Integer> le = new ArrayList<>(); // all elements <= pivot
        List<Integer> g = new ArrayList<>(); // elements > pivot
        for (int k = i+1; k < j; k++)
            if (a[k] <= pivot) le.add(a[k]);
            else g.add(a[k]);
        // Step 2: copy all back into a, first le, then pivot, then g
        int k = i;
        for (int e : le)
            a[k++] = e;
        int p = k;
        a[k++] = pivot;
        for (int e : g)
            a[k++] = e;
        return p;
    }

    // does the same as partition, but in-place, without extra space
    private static int hoarePartition(int[] a, int i, int j) {
        int pivot = a[i];
        int k = i + 1;
        int l = j;
        while (k < l) {
            if (a[k] <= pivot)
                k++;
            else if (a[l-1] > pivot)
                l--;
            else {
                swap(a, k, l-1);
                k++; l--;
            }
        }
        swap(a, i, k-1);
        return k-1;
    }

    // swaps elements a[i] and a[j]
    private static void swap(int[] a, int i, int j) {
        int ai = a[i];
        a[i] = a[j];
        a[j] = ai;
    }
}

