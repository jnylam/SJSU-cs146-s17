package cc.jennylam.cs146;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BacktrackingProblems {

    /**
     * Given a list of integers, is there a subset of these integers whose sum is equal to the specified target value?
     * An empty set of integers is considered to have a sum of 0.
     * @return true if such a subset exists
     */
    public static boolean subsetSum(List<Integer> list, int target) {
        if (list.isEmpty())
            return target == 0;
        List<Integer> restOfTheList = list.subList(1, list.size());
        boolean canMakeSubsetUsingFirstElement = subsetSum(restOfTheList, target - list.get(0));
        boolean canMakeSubsetWithoutFirstElement = subsetSum(restOfTheList, target);
        return canMakeSubsetUsingFirstElement || canMakeSubsetWithoutFirstElement;
    }


    /**
     * Give a list of integers, is there a subset of these integers whose sum is equal to the specified target value,
     * with the additional condition that all elements that make up the sum must be odd?
     * An empty set of integers is considered to have a sum of 0, and have only odd integers (a vacuously true statement)
     * Solve this problem without using any loops.
     * @return true if such a subset exists
     */
    public static boolean oddSubsetSum(List<Integer> list, int target) {
        return false;
    }

    /**
     * Given a list of integers, is there a subset of exactly 8 integers whose sum is equal to the specified target
     * value? If the list has 7 or fewer numbers, the answer is no.
     *
     * Solve this problem without using any loops.
     *
     * Hint: to solve the problem, you will need the information of how many numbers have already been used, so you will
     * need to solve a more general problem, which will implemented in a helper function,
     * @return true if such a subset exists
     */
    public static boolean subsetOf8Sum(List<Integer> list, int target) {
        return false;
    }

    /**
     * Given a non-empty list of integers, is there a non-empty subset whose sum is equal to the specified target?
     * Hint: you will need the information of whether any number has already been used (same idea as in subset8Sum, but
     * with "exactly 8" replaced by "at least 1"), so you will also need a helper function.
     * Solve this problem without using any loops.
     */
    public static boolean nonEmptySubsetSum(List<Integer> list, int target) {
        return false;
    }

    /**
     * Given a list of doubles, is there a (possibly empty) subset of doubles whose product is in the range between the
     * give lower and upper bound (bounds included)?
     *
     * The product of an empty set is considered to be 1.
     *
     * The answer is no if the value given by the parameter upperBound is strictly less than the parameter lowerBound.
     *
     * Solve this problem without using any loops.
     *
     * Hint 1: sum is to subtraction as product is to ...
     * Hint 2: what happens to the order of three values 0 < a < b < c if you divide them all by the same negative number?
     * what about if you divide by the same number between 0 and 1?
     * Hint 3: use inequalities (<, >, <=, >=) to compare doubles, do not use equality (==).
     * @return true if such a subset exists
     */
    public static boolean subsetProduct(List<Double> list, double lowerBound, double upperBound) {
        return false;
    }

    /**
     * Given a list of non-negative integers and a positive upperBound, what is the maximum value of any subsets of the
     * set that is less than or equal to the specified upperBound?
     *
     * For example
     * {6, 7, 8}, 18 returns 15
     * {}, 10 returns 0
     */
    public static int maxSubsetSum(List<Integer> list, int upperBound) {
        return 0;
    }

    public static void main(String[] args) {
        // odd subset sum
        List<Integer> list1 = new ArrayList<>(Arrays.asList(5, 13, 9, 8, 2));
        System.out.println(list1 + ", 8: oddSubsetSum " + oddSubsetSum(list1, 8));
        System.out.println(list1 + ", 13: oddSubsetSum " + oddSubsetSum(list1, 13));
        System.out.println(list1 + ", 14: oddSubsetSum " + oddSubsetSum(list1, 14));
        System.out.println(list1 + ", 15: oddSubsetSum " + oddSubsetSum(list1, 15));
        // write more test code here
    }

}
