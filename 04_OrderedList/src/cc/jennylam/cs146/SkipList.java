package cc.jennylam.cs146;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SkipList<E extends Comparable<E>> implements SortedSet<E> {

    private class Node {
        Extended<E> xvalue; // a value or negative infinity or positive infinity
                            // allows for a convenient way to compare against the beginning and end of the skiplist
                            // without having to make those a special case;
                            // use as with regular comparisons (see Extended.compareTo() for details)
        E value; // if xvalue is some infinity, value is null
        Node up;
        Node down;
        Node left;
        Node right;
        Node(Extended<E> xvalue) {
            this.xvalue = xvalue;
            this.value = xvalue.value();
        }

        @Override
        public String toString() {
            return xvalue.toString();
        }
    }

    private Node head;
    private Node tail;
    private int pInverse;
    private Random rng; // random number generator
    private int size;

    public SkipList(int pInverse) {
        head = new Node(Extended.negativeInfinity());
        tail = new Node(Extended.positiveInfinity());
        linkLR(head, tail);
        this.pInverse = pInverse;
        rng = new Random();
        size = 0;
    }

    public SkipList() {
        this(2);
    }

    @Override
    public E first() {
        if (isEmpty())
            throw new NoSuchElementException();
        Node node = head;
        while (node.down != null)
            node = node.down;
        return node.right.value;
    }

    @Override
    public E last() {
        if (isEmpty())
            throw new NoSuchElementException();
        Node node = tail;
        while (node.down != null)
            node = node.down;
        return node.left.value;
    }

    @Override
    public E floor(E e) {
        Node node = find(e);
        if (node.right.xvalue.equals(e))
            return e;
        if (node.xvalue.compareTo(Extended.negativeInfinity()) == 0)
            throw new NoSuchElementException();
        return node.value;
    }

    @Override
    public E ceiling(E e) {
        Node node = find(e);
        if (node.right.xvalue.compareTo(Extended.positiveInfinity()) == 0)
            throw new NoSuchElementException();
        return node.right.value;
    }

    @Override
    public boolean contains(E e) {
        return find(e).right.xvalue.compareTo(e) == 0;
    }

    @Override
    public boolean isEmpty() {
        return head.right == tail;
    }

    @Override
    public int size() {
        return size;
    }

    public int numberOfLevels() {
        int h = 0;
        for (Node n = head; n != null; n = n.down)
            h++;
        return h;
    }

    @Override
    public boolean add(E e) {
        Node node = find(e);
        if (node.right.xvalue.compareTo(e) == 0)
            return false;
        Node newNode = addNodeHere(node, null, e);
        while (coinFlipsHeads()) {
            while (node.up == null && node.left != null)
                node = node.left;
            node = node.up == null ? addLevel() : node.up;
            newNode = addNodeHere(node, newNode, e);
        }
        size++;
        return true;
    }

    @Override
    public boolean remove(E e) {
        Node node = find(e).right;
        if (node.xvalue.compareTo(e) != 0)
            return false;
        while (node.up != null) {
            node = node.up;
            remove(node.down);
        }
        remove(node);
        return true;
    }

    // Return a node such that node.xvalue < e <= node.right.xvalue && node.down == null
    private Node find(E e) {
        // TODO (homework)
        throw new UnsupportedOperationException("not yet implemented");
    }

    private Node addLevel() {
        linkUD(new Node(Extended.negativeInfinity()), head);
        linkUD(new Node(Extended.positiveInfinity()), tail);
        head = head.up;
        tail = tail.up;
        linkLR(head, tail);
        return head;
    }

    private Node addNodeHere(Node left, Node down, E e) {
        Node node = new Node(new Extended<>(e));
        linkLR(node, left.right);
        linkLR(left, node);
        linkUD(node, down);
        return node;
    }

    private void remove(Node node) {
        linkUD(node.up, node.down);
        linkLR(node.left, node.right);
        if (node.left.left == null && node.right.right == null) {
            linkUD(node.left.up, node.left.down);
            linkUD(node.right.up, node.right.down);
            if (node.left.up == null && node.left.down != null) {
                head = node.left.down;
                tail = node.right.down;
            }
        }
    }

    private void linkLR(Node left, Node right) {
        left.right = right;
        right.left = left;
    }

    private void linkUD(Node up, Node down) {
        if (up != null)
            up.down = down;
        if (down != null)
            down.up = up;
    }

    // Return true with probability 1/pInverse
    private boolean coinFlipsHeads() {
        return rng.nextInt(pInverse) == 0;
    }

    @Override
    public String toString() {
        List<String> levels = new ArrayList<>();
        Node node = head;
        levels.add("");
        while (node.down != null) {
            node = node.down;
            levels.add("");
        }
        addColumnAsText(node, levels, true);
        while (node.right != null) {
            node = node.right;
            addColumnAsText(node, levels, false);
        }
        return String.join("\n", levels) + "\n";
    }

    private void addColumnAsText(Node node, List<String> levels, boolean isFirstCol) {
        String front = isFirstCol ? "|" : "->|";
        String back = "|";

        int width = node.toString().length();
        String noNode = repeat("-", width + front.length() + back.length());
        int i = levels.size() - 1;
        for (; node != null; node = node.up, i--)
            levels.set(i, levels.get(i) + front + node + back);
        for (; i >= 0; i--)
            levels.set(i, levels.get(i) + noNode);
    }

    // return a string consisting of the given string, repeated the given number of times.
    private static String repeat(String str, int repeat) {
        return IntStream.range(0, repeat)
                .mapToObj(i -> str)
                .collect(Collectors.joining());
    }
}
