package cc.jennylam.cs146;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
        Node successor = node.successor();
        if (successor == null)
            throw new NoSuchElementException();
        return successor.value;
    }

    @Override
    public E ceiling(E e) {
        if (isEmpty())
            throw new NoSuchElementException();
        Node node = root.find(e);
        if (node.value.compareTo(e) >= 0)
            return node.value;
        Node predecessor = node.predecessor();
        if (predecessor == null)
            throw new NoSuchElementException();
        return predecessor.value;
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

        // Return a per-level list of the nodes in left to right order
        List<List<E>> levels() {
            throw new UnsupportedOperationException("not yet implemented");
        }
    }

    @Override
    public String toString() {
        if (isEmpty())
            return "()";
        List<List<E>> levels = root.levels();
        int maxWidth = levels.get(levels.size() - 1).size();
        return levels.stream()
                .map(level -> levelToString(level, maxWidth))
                .collect(Collectors.joining());
    }

    private String levelToString(List<E> level, int maxWidth) {
        int numGaps = maxWidth / level.size();
        String gap = repeat(" ", numGaps);
        String result = gap;
        for (E e : level) {
            result += e + gap;
        }
        return result;
    }

    // return a string consisting of the given string, repeated the given number of times.
    private static String repeat(String str, int repeat) {
        return IntStream.range(0, repeat)
                .mapToObj(i -> str)
                .collect(Collectors.joining());
    }
}
