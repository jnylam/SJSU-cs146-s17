package cc.jennylam.cs146;

import java.util.HashMap;
import java.util.Map;

public class EditDistance {

    public static void main(String[] args) {
        String w1 = "polynomial";
        String w2 = "exponential";
        System.out.printf("brute force edit(%s, %s) = %d\n", w1, w2, bruteForceEditDistance(w1, w2));
        System.out.printf("memoized edit(%s, %s) = %d\n", w1, w2, new EditDistance().memoizedEditDistance(w1, w2));
        System.out.printf("dp edit(%s, %s) = %d\n", w1, w2, dynamicProgrammingEditDistance(w1, w2));
    }

    public static int bruteForceEditDistance(String w1, String w2) {
        return 0;
    }

    public int memoizedEditDistance(String w1, String w2) {
        // TODO: write an efficient implementation of EditDistance by modifying bruteForceEditDistance to use the cache.

        // Hints: think about...
        // what data type should be the key in the cache?
        // how will you handle the fact that there are 2 input params into the problem, but only 1 key can be stored?
        // (How can you encode 2 inputs as a single string in such a way that you can recover both strings from
        // the encoded string? Assume that the input strings contain only alphabet characters.)
        // what data type should be the value in the cache?

        // Step 1: instantiate the cache as a private field int this class, using the appropriate dictionary types.
        // Step 2: write this function, using bruteForceEditDistance as a starting point, and using the cache as needed
        return 0;
    }

    public static int dynamicProgrammingEditDistance(String w1, String w2) {
        // TODO: write an efficient implementation of EditDistance by storing subproblem solutions in a table

        // Hints: think about...
        // should the table be a 1d-array, 2d-array or 3d-array?
        // how are you going to encode all possible inputs as indices for the array?
        // what data type should be stored in the table? (it's the output)
        // Step 1: instantiate the table

        // Step 2: which cells correspond to the base cases? fill those out according to the rules for the base case

        // Step 3: which cells correspond to the other cases? which cells does cell[i][j]'s computation depend on?
        // fill out according to the rules for the non-base cases.

        // Step 4: which cell corresponds to the original problem (as opposed to the subproblems). Return the value of
        // that cell
        return 0;
    }
}
