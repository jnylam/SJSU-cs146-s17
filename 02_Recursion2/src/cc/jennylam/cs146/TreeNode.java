package cc.jennylam.cs146;

import java.util.List;

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
		return null;
	}
	
	public List<E> postorder() {
		return null;
	}
	
	public static TreeNode<Integer> generateTree(int size, int bound) {
		return null;
	}
	
	public static void main(String[] args) {		
	}
}
