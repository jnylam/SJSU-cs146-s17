package cc.jennylam.cs146;

import java.util.ArrayList;
import java.util.List;

public class Recursion1Practice {
	
	/* Clean the rooms in order of decreasing room number.
	 */
	public static void cleanHotelHiToLo(int lo, int hi) {
		
	}
	
	/* Clean the rooms in an order that follows the pattern:
	 * lo, hi, lo+1, hi-1, lo+2, hi-2, ...
	 * 
	 * Hint: do a little bit *more* work at each step
	 */
	public static void cleanHotelInZigzagOrder(int lo, int hi) {
		
	}
	
	/* Given a string, find the number of times an upper or lower case b
	 * appears in the string. 
	 * 
	 * Suggestion: check out the String API by searching "java8 string"
	 * and fill in the blanks, which are supposed to be code.
	 * ...: returns the i-th character of a string called str
	 * ...: returns the substring of a string called str, starting at index i (inclusive) and 
	 * 			ending at index j (exclusive).
	 */
	public static int countBs(String str) {
		return 0;
	}
	
	/* Given a string and a character, find the number of times the character
	 * appears in the string. This time, matches are case-sensitive.
	 */
	public static int countChar(String str, char c) {
		return 0;
	}
	
	/* Find the maximum value in an array of integers, using a for-loop.
	 */
	public static int loopyMax(int[] a) {
		return 0;
	}

	/* Find the maximum value in a list of Integers, using recursion.
	 * 
	 * Hint: to keep track of which parts of the list still need to be visited,
	 * which works a lot like cleanHotel(int lo, int hi)
	 * use a recursive helper function: recursiveMaxHelper(List<Integer> a, int lo, int hi)
	 * 
	 */
	public static int recursiveMax(int[] a) {
		return 0;
	}
		
	/* Reimplement the primeFactors() function, but this time, recursively.
	 * You still call findAFactor() to find a single prime factor for you.
	 * (We're using it as a building block)
	 * 
	 * Java tip: since findAFactor is in a different class, we need to let the compiler know.
	 * We do this with dot notation: Recursive1.findAFactor()
	 */
	public static List<Integer> recursivePrimeFactors(int n) {
		return null;
	}
	
	/* Clean the rooms in increasing order of room number and
	 * collect trash along the way (the way a regular person would do). 
	 * So whatever trash is currently accumulated
	 * gets passed down as an input parameter to the remainder of the rooms.
	 * On the return, the trash just gets passed up the levels of calls,
	 * without any additions.
	 */
	public static String cleanHotelCollectTrashOnTheWayDown(int lo, int hi, String trash) {
		return null;
	}

	
	public static void main(String[] args) {
		// write code here to test your functions
	}
}
