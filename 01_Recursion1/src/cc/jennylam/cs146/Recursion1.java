package cc.jennylam.cs146;

import java.util.ArrayList;
import java.util.List;

public class Recursion1 {
	public static int findAFactor(int n) {
		for (int j = 2; j < n; j++)
			if (n%j == 0)
				return j;
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
	

	
	
	
	public static void cleanHotel(int lo, int hi) {
		// base case
		if (lo > hi)
			return;
		// do a little work: clean a single room; here we choose room # from
		System.out.printf("Cleaning room %d\n", lo);
		// don't do all the work: trust the recursion to do what it's supposed to do.
		cleanHotel(lo + 1, hi);
	}
	
	public static String cleanHotelCollectTrash(int lo, int hi) {
		if (lo > hi)
			return "";
		System.out.printf("Cleaning room %d\n", lo);
		String trashFromOtherRooms = cleanHotelCollectTrash(lo + 1, hi);
		return trashFromOtherRooms + ", trash from room " + lo;
	}
	
	public static void main(String[] args) {
		System.out.printf("prime factors of %d: %s\n", 20, loopyPrimeFactors(20));
		cleanHotel(2, 10);
		String trash = cleanHotelCollectTrash(2, 10);
		System.out.printf("Collected trash: %s\n", trash);
	}
}
