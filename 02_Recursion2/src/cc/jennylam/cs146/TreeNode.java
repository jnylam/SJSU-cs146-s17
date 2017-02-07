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
	
	public E max() {
		return null;
	}
	
	public int size() {
		return 0;
	}
	
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
	
	public List<E> postorder() {
		return null;
	}
	
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
