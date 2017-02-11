package cc.jennylam.cs146;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class TowersOfHanoi {
	private int n;
	private List<Stack<Integer>> pegs;
	private boolean verbose;
	
	public TowersOfHanoi(int n, boolean verbose) {
		this.n = n;
		this.verbose = verbose;
		
		pegs = new ArrayList<>();
		// each peg is represented by a stack
		for (int i = 0; i < 3; i++)
			pegs.add(new Stack<>());
		// push disks onto peg 0, from biggest to smallest
		for (int i = n; i > 0; i--)
			pegs.get(0).push(i);
	} 
	
	public void solve() {
		// TODO: solve the problem by making calls to moveTopDisk
		// Tip 1: you may find it helpful to break your code into multiple functions
		// Tip 2: start by running the code as is
		// Then add a single call to moveTopDisk to see what happens.
		// Hint: make an initial call to moveStack with the correct parameter values. 
		// Then finish implementing moveStack.
	}
	
	public void moveStack(int n, int fromPeg, int toPeg) {
		int otherPeg = 3 - fromPeg - toPeg; // sum of the peg indices is 0 + 1 + 2 = 3
		// finish this implementation
	}
	
	public void moveTopDisk(int fromPeg, int toPeg) {
		int disk = pegs.get(fromPeg).pop();
		Stack<Integer> peg = pegs.get(toPeg);
		assert (peg.isEmpty() || peg.peek() > disk); // valid move
		peg.push(disk);
		if (verbose)
			System.out.println(this);
	}
	
	public String toString() {
		String s = "";
		for (int i = n - 1; i >= 0; i--) {
			s += "| ";
			for (List<Integer> peg : pegs) {
				if (i < peg.size()) {
					int d = peg.get(i);
					s += space(n-d) + disk(2*d) + space(n-d);
				} else {
					s += space(2*n);
				}
				s += " | ";
			}
			s += "\n";
		}
		return s;
	}
	
	public static String disk(int size) {
		String result = "";
		for (int i = 0; i < size; i++)
			result += "=";
		return result;
	}
	
	public static String space(int size) {
		String result = "";
		for (int i = 0; i < size; i++)
			result += " ";
		return result;
	}
	
	public static void main(String[] args) {
		TowersOfHanoi towers = new TowersOfHanoi(4, true);
		System.out.println(towers);
		towers.solve();
	}
}
