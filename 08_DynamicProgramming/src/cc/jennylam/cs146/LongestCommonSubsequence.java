package cc.jennylam.cs146;

import java.util.HashMap;
import java.util.Map;

public class LongestCommonSubsequence {

    public static void main(String[] args) {
        String w1 = "polynomial";
        String w2 = "exponential";
        System.out.printf("brute force edit(%s, %s) = %d\n", w1, w2, bruteForceLongestCommonSubsequence(w1, w2));
        System.out.printf("memoized edit(%s, %s) = %d\n", w1, w2, new LongestCommonSubsequence().memoizedLongestCommonSubsequence(w1, w2));
        System.out.printf("dp edit(%s, %s) = %d\n", w1, w2, dynamicProgrammingLongestCommonSubsequence(w1, w2));
        // TODO: add some more test cases, include some with a different solution than in the Edit Distance problem
    }

    public static int bruteForceLongestCommonSubsequence(String w1, String w2) {
        // TODO: translate the recursive solution into code
        // base cases

        // general case
        return 0;
    }

    public int memoizedLongestCommonSubsequence(String w1, String w2) {
        // TODO: write an efficient implementation of LongestCommonSubsequence by modifying bruteForceLongestCommonSubsequence to use the cache.

        // Hints: think about...
        // what data type should be the key in the cache?
        // how will you handle the fact that there are 2 input params into the problem, but only 1 key can be stored?
        // (How can you encode 2 inputs as a single string in such a way that you can recover both strings from
        // the encoded string? Assume that the input strings contain only alphabet characters.)
        // what data type should be the value in the cache?

        // Step 1: instantiate the cache as a private field int this class, using the appropriate dictionary types.
        // Step 2: write this function, using bruteForceLongestCommonSubsequence as a starting point, and using the cache as needed
        return 0;
   }

    public static int dynamicProgrammingLongestCommonSubsequence(String w1, String w2) {
        // TODO: write an efficient implementation of LongestCommonSubsequence by storing subproblem solutions in a table

        // Hints: think about...
        // should the table be a 1d-array, 2d-array or 3d-array?
        // how are you going to encode all possible inputs as indices for the array?
        // what data type should be stored in the table? (it's the output)
        // Step 1: instantiate the table

        // Step 2: which cells correspond to the base cases? fill those out according to the rules for the base case

        // Step 3: which cells correspond to the other cases? which cells does cell[i][j]'s computation depend on?
        // fill out according to the rules for the non-base cases.
        // all remaining subproblems must be of the form edit(w1[0:i], w2[0,j]) where i> 0 and j>0

        // Step 4: which cell corresponds to the original problem (as opposed to the subproblems). Return the value of
        // that cell
        return 0;
    }
}
