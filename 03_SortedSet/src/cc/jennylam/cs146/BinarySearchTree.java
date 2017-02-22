package cc.jennylam.cs146;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class BinarySearchTree<E extends Comparable<E>> implements SortedSet<E> {
    private Node root;
    private int size;

    public BinarySearchTree() {
        size = 0;
    }

    @Override
    public E first() {
        if (isEmpty())
            throw new NoSuchElementException();
        return root.min().value;
    }

    @Override
    public E last() {
        if (isEmpty())
            throw new NoSuchElementException();
        return root.max().value;
    }

    @Override
    public E floor(E e) {
        if (isEmpty())
            throw new NoSuchElementException();
        Node node = root.find(e);
        if (node.value.compareTo(e) <= 0)
            return node.value;
        Node predecessor = node.predecessor();
        if (predecessor == null)
            throw new NoSuchElementException();
        return predecessor.value;
    }

    @Override
    public E ceiling(E e) {
        if (isEmpty())
            throw new NoSuchElementException();
        Node node = root.find(e);
        if (node.value.compareTo(e) >= 0)
            return node.value;
        Node successor = node.successor();
        if (successor == null)
            throw new NoSuchElementException();
        return successor.value;
    }

    @Override
    public boolean contains(E e) {
        if (isEmpty())
            return false;
        return root.find(e).value.equals(e);
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean add(E e) {
        if (isEmpty()) {
            root = new Node(e, null);
            size++;
            return true;
        }
        if (root.add(e)) {
            size++;
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(E e) {
        if (isEmpty())
            return false;
        Node node = root.find(e);
        if (!node.value.equals(e))
            return false;
        node.remove(); // <-- TODO: implement, see skeleton code lower down
        size--;
        return true;
    }

    public int height() {
        if (isEmpty())
            return -1;
        return root.height();
    }

    private class Node {
        E value;
        Node parent;
        Node left;
        Node right;

        Node(E e, Node parent) {
            this.value = e;
            this.parent = parent;
        }

        Node min() {
            // TODO (homework)
            throw new UnsupportedOperationException("not yet implemented");
        }

        Node max() {
            // TODO (optional)
            throw new UnsupportedOperationException("not yet implemented");
        }

        Node successor() {
            // TODO (homework)
            throw new UnsupportedOperationException("not yet implemented");
        }

        Node predecessor() {
            // TODO (optional)
            throw new UnsupportedOperationException("not yet implemented");
        }

        boolean add(E e) {
            if (this.value.compareTo(e) == 0)
                return false;
            if (this.value.compareTo(e) > 0) {
                if (left == null) {
                    left = new Node(e, this);
                    return true;
                } else
                    return left.add(e);
            } else {
                if (right == null) {
                    right = new Node(e, this);
                    return true;
                } else
                    return right.add(e);
            }
        }

        void remove() {
            // TODO (homework)
            throw new UnsupportedOperationException("not yet implemented");
        }

        // return the node which contains e, if it exists, or the node whose child would contain e otherwise.
        Node find(E e) {
            // TODO (homework)
            throw new UnsupportedOperationException("not yet implemented");
        }

        // May be useful for some implementations
        boolean isLeftChild() {
            return parent != null && this == parent.left;
        }

        // May be useful for some implementations
        boolean isRightChild() {
            return parent != null && this == parent.right;
        }

        int height() {
            int max = -1;
            if (left != null)
                max = Integer.max(max, left.height());
            if (right != null)
                max = Integer.max(max, right.height());
            return max + 1;
        }
    }

    @Override
    public String toString() {
        if (isEmpty())
            return "()";
        List<List<String>> levels = levels();
        int height = height();
        return IntStream.range(0, levels.size())
                .mapToObj(i -> levelToString(levels.get(i), height - i))
                .collect(Collectors.joining());
    }

    private List<List<String>> levels() {
        List<List<String>> levels = new ArrayList<>();
        Queue<Node> queue = new LinkedList<>();
        Node separator = new Node(null, null);
        Node dummy = new Node(null, null);
        int height = height();

        queue.add(separator);
        queue.add(root);
        while (!queue.isEmpty()) {
            Node node = queue.remove();
            if (node == separator) {
                levels.add(new ArrayList<>());
                if (levels.size() - 1 < height)
                    queue.add(separator);
            } else {
                levels.get(levels.size() - 1).add(node == dummy ? "" : node.value.toString());
                if (levels.size() - 1 < height) {
                    queue.add(node.left == null ? dummy : node.left);
                    queue.add(node.right == null ? dummy : node.right);
                }
            }
        }
        return levels;
    }

    private String levelToString(List<String> level, int height) {
        long gapWidth = Math.round(Math.pow(2, height + 1) - 1);
        long halfGapWidth = Math.round(Math.pow(2, height) - 1);
        String gap = repeat(" ", gapWidth);
        String halfGap = repeat(" ", halfGapWidth);
        String BLANK = "";

        String branches = halfGap;
        String nodes = halfGap;
        boolean left = true;
        for (String e : level) {
            if (e.equals(BLANK)) {
                branches += " " + gap;
                nodes += " " + gap;
            } else {
                branches += (left ? "/" : "\\") + gap;
                nodes += e + gap;
            }
            left = !left;
        }
        branches += "\n";
        nodes += "\n";
        return (level.size() <= 1) ? nodes : branches + nodes;
    }

    // return a string consisting of the given string, repeated the given number of times.
    private static String repeat(String str, long repeat) {
        return LongStream.range(0, repeat)
                .mapToObj(i -> str)
                .collect(Collectors.joining());
    }
}

