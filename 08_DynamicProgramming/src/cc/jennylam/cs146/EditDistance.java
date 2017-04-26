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
        // base cases
        // edit("", w2) = length(w2)
        if (w1.equals(""))
            return w2.length();
        // edit(w1, "") = length(w1)
        if (w2.equals(""))
            return w1.length();

        // if w1[0] == w2[0], then edit(w1, w2) = edit(w1[1:], w2[1:])
        if (w1.charAt(0) == w2.charAt(0))
            return bruteForceEditDistance(w1.substring(1), w2.substring(1));

        // else...
        return Math.min(Math.min(
                bruteForceEditDistance(w1.substring(1), w2) + 1,
                bruteForceEditDistance(w1, w2.substring(1)) + 1),
                bruteForceEditDistance(w1.substring(1), w2.substring(1)) + 1);
    }

    private Map<String, Integer> cache = new HashMap<>();

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

        String input = w1 + "," + w2;
        int output;

        if (cache.containsKey(input))
            return cache.get(input);

        // base cases
        // edit("", w2) = length(w2)
        if (w1.equals(""))
            output = w2.length();
        // edit(w1, "") = length(w1)
        else if (w2.equals(""))
            output = w1.length();

        // if w1[0] == w2[0], then edit(w1, w2) = edit(w1[1:], w2[1:])
        else if (w1.charAt(0) == w2.charAt(0))
            output = bruteForceEditDistance(w1.substring(1), w2.substring(1));

        else
            output = Math.min(Math.min(
                bruteForceEditDistance(w1.substring(1), w2) + 1,
                bruteForceEditDistance(w1, w2.substring(1)) + 1),
                bruteForceEditDistance(w1.substring(1), w2.substring(1)) + 1);

        cache.put(input, output);
        return output;
    }

    public static int dynamicProgrammingEditDistance(String w1, String w2) {
        // TODO: write an efficient implementation of EditDistance by storing subproblem solutions in a table

        // Hints: think about...
        // should the table be a 1d-array, 2d-array or 3d-array?
        // how are you going to encode all possible inputs as indices for the array?
        // what data type should be stored in the table? (it's the output)
        // Step 1: instantiate the table
        int n1 = w1.length();
        int n2 = w2.length();
        // edit[i][j] represents the subproblem edit(w1[0:i], w2[0:j]) (slice notation)
        // or edit(w1.substring(0,i), w2.substring(0,j)) (equivalent Java notation)
        int[][] edit = new int[n1+1][n2+1];

        // Step 2: which cells correspond to the base cases? fill those out according to the rules for the base case
        // edit("", v2) = length(v2), where v2 is any substring of the form w2[0:j]
        for (int j = 0; j < n2+1; j++)
            edit[0][j] = j;
        // edit(v1, "") = length(v1), where v1 is an substring of the form w1[0:i]
        for (int i = 0; i < n1+1; i++)
            edit[i][0] = i;

        // Step 3: which cells correspond to the other cases? which cells does cell[i][j]'s computation depend on?
        // fill out according to the rules for the non-base cases.
        // all remaining subproblems must be of the form edit(w1[0:i], w2[0,j]) where i> 0 and j>0
        for (int i = 1; i < n1+1; i++)
            for (int j = 1; j < n2+1; j++)
                // if the last characters of w1[0:i] and w2[0:j] match, then ...
                edit[i][j] = (w1.charAt(i-1) == w2.charAt(i-1)) ?
                        edit[i-1][j-1] :
                        // otherwise ...
                        Math.min(Math.min(edit[i-1][j] + 1, edit[i][j-1] + 1), edit[i-1][j-1] + 1);

        // Step 4: which cell corresponds to the original problem (as opposed to the subproblems). Return the value of
        // that cell
        return edit[n1][n2]; // since that represents the subproblem edit(w1[0:n1], w2[0:n2]) or edit(w1, w2)
    }
}
