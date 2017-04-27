package cc.jennylam.cs146;

import java.util.Arrays;

public class Heaps {
    public static void main(String[] args) {
        int[] a = {4, 6, 8, 1, 2, 0, 3, 2};
        System.out.println(Arrays.toString(a));
        heapSort(a);
        System.out.println(Arrays.toString(a));
    }

    private static void selectionSort(int[] a) {
        // invariant: a[i:n] sorted
        // and a[0:i] unsorted, all values <= a[i]
        for (int i = a.length; i > 0; i--) {
            int m = indexOfMax(a, 0, i);
            swap(a, m, i-1);
        }
    }

    private static void swap(int[] a, int i, int j) {
        int ai = a[i];
        a[i] = a[j];
        a[j] = ai;
    }

    private static int indexOfMax(int[] a, int i, int j) {
        int m = i;
        int max = a[i];
        for (int k = i+1; k < j; k++) {
            if (max < a[k]) {
                m = k;
                max = a[k];
            }
        }
        return m;
    }

    private static void heapSort(int[] a) {
        heapify(a); // make-queue
        // invariant: a[i:n] sorted
        // a[0:i] is unsorted and a heap, all values <= a[i]
        for (int i = a.length; i > 0; i--) {
            swap(a, 0, i-1);
            siftDown(a, 0, i-1);
        }
    }

    private static void heapify(int[] a) {
        for (int i = a.length-1; i >= 0; i--)
            siftDown(a, i, a.length);
    }

    private static void siftDown(int[] a, int i, int n) {
        // sift-down starting at index i in the part of the array
        // that is a[0:n], n <= a.length
        int p = i;
        int lc = leftChild(p);
        int rc = lc + 1;
        while (true) {
            int m = p;
            int max = a[p];
            if (lc < n && a[lc] > max) {
                m = lc;
                max = a[lc];
            }
            if (rc < n && a[rc] > max) {
                m = rc;
                max = a [rc];
            }
            if (m == p)
                break;
            swap(a, p, m);
            p = m;
            lc = leftChild(p);
            rc = lc + 1;
        }
    }

    private static int leftChild(int p) {
        return 2*p + 1;
    }
}
