package cc.jennylam.cs146;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;

public class LinearTimeSorting {
    public static void main(String[] args) {
        int n = 11;
        int k = 10000;
        List<Integer> li = new Random().ints(n, 0, k).boxed().collect(Collectors.toList());
//        System.out.println(li);
//        List<Integer> sortedLi = countingSort(li, k);
//        System.out.println(sortedLi);

//        Function<Integer, Integer> zerothDigit = x -> x % 10;
//        Function<Integer, Integer> firstDigit = x -> x/10 % 10;
//        System.out.println(li);
//        List<Integer> sortedBy0th = bucketSort(li, zerothDigit, 10);
//        System.out.println(sortedBy0th);
//        List<Integer> sortedBy1st = bucketSort(sortedBy0th, firstDigit, 10);
//        System.out.println(sortedBy1st);

        System.out.println(li);
        li = radixSort(li, k);
        System.out.println(li);
    }

    // sorts li, where values are between 0 (inclusive)
    // and k (exclusive)
    private static List<Integer> countingSort(List<Integer> li, int k) {
        int[] counters = new int[k];
        for (int v : li)
            counters[v]++;
        List<Integer> result = new ArrayList<>();
        for (int v = 0; v < k; v++)
            for (int i = 0; i < counters[v]; i++)
                result.add(v);
        return result;
    }

    // sorts element e of li by their values sortBy(e)
    // values sortBy(e) are between 0 (inclusive)
    // and b (exclusive)
    public static List<Integer> bucketSort(List<Integer> li, Function<Integer, Integer> sortBy, int b) {
        List<List<Integer>> buckets = new ArrayList<>();
        for (int i = 0; i < b; i++)
            buckets.add(new ArrayList<>());
        for (int v : li) {
            int bucket = sortBy.apply(v);
            buckets.get(bucket).add(v);
        }
        List<Integer> result = new ArrayList<>();
        for (List<Integer> bucket : buckets)
            result.addAll(bucket);
        return result;
    }

    // sorts li, where values are between 0 (inclusive)
    // and k (exclusive)
    private static List<Integer> radixSort(List<Integer> li, int k) {
        int b = 10;
        long numDigits = Math.round(Math.ceil(Math.log(k)/Math.log(b)));
        for (int i = 0; i < numDigits; i++) {
            int I = i;
            Function<Integer, Integer> ithDigit = v -> {
                BigInteger V = BigInteger.valueOf(v);
                BigInteger B = BigInteger.valueOf(b);
                return V.divide(B.pow(I)).mod(B).intValue();
            };
            li = bucketSort(li, ithDigit, b);
            System.out.println(li);
        }
        return li;
    }

}
