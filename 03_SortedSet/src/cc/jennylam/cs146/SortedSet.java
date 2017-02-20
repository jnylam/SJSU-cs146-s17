package cc.jennylam.cs146;

/**
 * Note: this interface is meant to capture the essential aspects of a SortedSet ADT and
 * is based on the following java.util interfaces: Set, SortedSet, NavigableSet
 *
 * A SortedSet contains no repeated elements (unlike a multi-set).
 * @param <E>
 */
public interface SortedSet<E extends Comparable<E>> {
    /**
     * @return the first or smallest element
     * @throws java.util.NoSuchElementException if the SortedSet is empty
     */
    E first();

    /**
     * @return the last or largest element.
     * @throws java.util.NoSuchElementException if the SortedSet is empty
     */
    E last();

    /**
     * @param e
     * @return the largest value in the SortedSet that is equal to or less than e
     * @throws java.util.NoSuchElementException if there are no elements in SortedSet less than or equal to e
     */
    E floor(E e);

    /**
     * @param e
     * @return the smallest value in the SortedSet that is equal to or greater than e
     * @throws java.util.NoSuchElementException if there are no elements in SortedSet greater than or equal to e
     */
    E ceiling(E e);

    /**
     * @param e
     * @return true if the SortedSet contains e
     */
    boolean contains(E e);

    /**
     * @return true if the SortedSet is empty
     */
    boolean isEmpty();

    /**
     * @return the number of elements in the SortedSet
     */
    int size();

    /**
     * Add e if e is not already in the SortedSet.
     * @param e
     * @return true if e is successfully added
     */
    boolean add(E e);

    /**
     * Remove e if it is the SortedSet.
     * @param e
     * @return true if e is successfully removed
     */
    boolean remove(E e);
}
