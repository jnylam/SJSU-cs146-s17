package cc.jennylam.cs146;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TreeNode<E extends Comparable<E>> {
	private E value;
	private TreeNode<E> left;
	private TreeNode<E> right;
	
	public TreeNode(E value, TreeNode<E> left, TreeNode<E> right) {
		this.value = value;
		this.left = left;
		this.right = right;
	}
	
	/*
	 * Return the maximum value among all nodes in the tree rooted at the current node.
	 * 
	 * Hint: because it may not make sense to use comparison symbols '<' or '>' with the generic type E,
	 * we instead use the fact that E is of type Comparable<E>. Specifically, use
	 * "a.compareTo(b) > 0" to mean a > b
	 * "a.compareTo(b) < 0" to mean a < b
	 * "a.compareTo(b) == 0" to mean a == b.
	 */
	public E max() {
		return null;
	}
	
	/*
	 * Return the number of nodes in the tree rooted at the current node. The current node is counted.
	 */
	public int size() {
		return 0;
	}
	
	/*
	 * Return a list of the nodes in the tree rooted at the current node, ordered by in-order traversal.
	 */
	public List<E> inorder() {
		List<E> list = new ArrayList<>();
		// left subtree
		if (left != null) {
			List<E> leftList = left.inorder();
			list.addAll(leftList);
		}
		// do a little work
		list.add(value);
		// right subtree
		if (right != null) {
			List<E> rightList = right.inorder();
			list.addAll(rightList);
		}
		return list;
	}
	
	/*
	 * Return a list of the nodes in the tree rooted at the current node, ordered by post-order traversal.
	 */
	public List<E> postorder() {
		return null;
	}
	
	/*
	 * Generate a full binary tree with the given number of levels, and whose nodes contain integer values
	 * less than the value given by bound.
	 */
	public static TreeNode<Integer> generateTree(int levels, int bound) {
		// base case
		if (levels == 0)
			return null;
		// recursive calls
		TreeNode<Integer> leftSubtreeRoot = generateTree(levels-1, bound);
		TreeNode<Integer> rightSubtreeRoot = generateTree(levels-1, bound);
		// do a little work
		int value = (new Random()).nextInt(bound);
		return new TreeNode<>(value, leftSubtreeRoot, rightSubtreeRoot);
	}
	
	public static void main(String[] args) {	
		TreeNode<Integer> node = generateTree(4, 100);
		System.out.println(node.inorder());
	}
}
