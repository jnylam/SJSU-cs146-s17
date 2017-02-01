package cc.jennylam.cs146;

import java.util.ArrayList;
import java.util.List;

public class Recursion1 {
	public static int findAFactor(int n) {
		for (int f = 2; f < n; f++)
			if (n % f == 0)
				return f;
		return n;
	}
	
	public static List<Integer> loopyPrimeFactors(int n) {
		List<Integer> factors = new ArrayList<Integer>();
		while (n > 1) {
			int f = findAFactor(n);
			factors.add(f);
			n /= f;
		}
		return factors;
	}
	
	/*
	 * Clean rooms numbered lo (inclusive), up to hi (inclusive)
	 */
	public static void cleanHotel(int lo, int hi) {
		for (int i = lo; i <= hi; i++)
			System.out.printf("cleaning room %d\n", i);
	}
	
	public static void recursiveCleanHotel(int lo, int hi) {
		// base case: when there's one room left
		if (lo == hi) {
			System.out.printf("cleaning room %d\n", lo);
			return;
		}
		// do a little bit of work: clean 1 room
		System.out.printf("cleaning room %d\n", lo);
		// let the recursion do the rest
		recursiveCleanHotel(lo+1, hi);
	}
	
	public static String cleanHotelCollectTrash(int lo, int hi) {
		// base case: when there's one room left
		if (lo == hi) {
			System.out.printf("cleaning room %d\n", lo);
			return "trash from room " + lo;
		}
		// do a little bit of work: clean 1 room
		System.out.printf("cleaning room %d\n", lo);
		String myTrash = "trash from room " + lo;
		// let the recursion do the rest
		String othersTrash = cleanHotelCollectTrash(lo+1, hi);
		return myTrash + ", " + othersTrash;
	}
	
	public static void main(String[] args) {
		String trash = cleanHotelCollectTrash(1, 10);
		System.out.printf(trash);
	}
}
